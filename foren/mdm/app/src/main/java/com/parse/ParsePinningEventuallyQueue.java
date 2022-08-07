package com.parse;

import android.content.Context;
import android.content.Intent;
import bolts.Continuation;
import bolts.Task;
import bolts.TaskCompletionSource;
import com.parse.ConnectivityNotifier;
import com.umeng.update.UpdateConfig;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import org.json.JSONObject;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public class ParsePinningEventuallyQueue extends ParseEventuallyQueue {
    private static final String TAG = "ParsePinningEventuallyQueue";
    private final ParseHttpClient httpClient;
    private ConnectivityNotifier notifier;
    private HashMap<String, TaskCompletionSource<JSONObject>> pendingOperationSetUUIDTasks = new HashMap<>();
    private TaskQueue taskQueue = new TaskQueue();
    private TaskQueue operationSetTaskQueue = new TaskQueue();
    private ArrayList<String> eventuallyPinUUIDQueue = new ArrayList<>();
    private TaskCompletionSource<Void> connectionTaskCompletionSource = new TaskCompletionSource<>();
    private final Object connectionLock = new Object();
    private ConnectivityNotifier.ConnectivityListener listener = new ConnectivityNotifier.ConnectivityListener() { // from class: com.parse.ParsePinningEventuallyQueue.1
        @Override // com.parse.ConnectivityNotifier.ConnectivityListener
        public void networkConnectivityStatusChanged(Context context, Intent intent) {
            if (intent.getBooleanExtra("noConnectivity", false)) {
                ParsePinningEventuallyQueue.this.setConnected(false);
            } else {
                ParsePinningEventuallyQueue.this.setConnected(ConnectivityNotifier.isConnected(context));
            }
        }
    };
    private final Object taskQueueSyncLock = new Object();
    private HashMap<String, TaskCompletionSource<JSONObject>> pendingEventuallyTasks = new HashMap<>();
    private HashMap<String, ParseOperationSet> uuidToOperationSet = new HashMap<>();
    private HashMap<String, EventuallyPin> uuidToEventuallyPin = new HashMap<>();

    public ParsePinningEventuallyQueue(Context context, ParseHttpClient client) {
        setConnected(ConnectivityNotifier.isConnected(context));
        this.httpClient = client;
        this.notifier = ConnectivityNotifier.getNotifier(context);
        this.notifier.addListener(this.listener);
        resume();
    }

    @Override // com.parse.ParseEventuallyQueue
    public void onDestroy() {
        this.notifier.removeListener(this.listener);
    }

    @Override // com.parse.ParseEventuallyQueue
    public void setConnected(boolean connected) {
        synchronized (this.connectionLock) {
            if (isConnected() != connected) {
                super.setConnected(connected);
                if (connected) {
                    this.connectionTaskCompletionSource.trySetResult(null);
                    this.connectionTaskCompletionSource = Task.create();
                    this.connectionTaskCompletionSource.trySetResult(null);
                } else {
                    this.connectionTaskCompletionSource = Task.create();
                }
            }
        }
    }

    @Override // com.parse.ParseEventuallyQueue
    public int pendingCount() {
        try {
            return ((Integer) ParseTaskUtils.wait(pendingCountAsync())).intValue();
        } catch (ParseException e) {
            throw new IllegalStateException(e);
        }
    }

    public Task<Integer> pendingCountAsync() {
        final TaskCompletionSource<Integer> tcs = new TaskCompletionSource<>();
        this.taskQueue.enqueue(new Continuation<Void, Task<Void>>() { // from class: com.parse.ParsePinningEventuallyQueue.2
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // bolts.Continuation
            public Task<Void> then(Task<Void> toAwait) throws Exception {
                return ParsePinningEventuallyQueue.this.pendingCountAsync(toAwait).continueWithTask(new Continuation<Integer, Task<Void>>() { // from class: com.parse.ParsePinningEventuallyQueue.2.1
                    /* JADX WARN: Can't rename method to resolve collision */
                    @Override // bolts.Continuation
                    public Task<Void> then(Task<Integer> task) throws Exception {
                        tcs.setResult(Integer.valueOf(task.getResult().intValue()));
                        return Task.forResult(null);
                    }
                });
            }
        });
        return tcs.getTask();
    }

    public Task<Integer> pendingCountAsync(Task<Void> toAwait) {
        return toAwait.continueWithTask(new Continuation<Void, Task<Integer>>() { // from class: com.parse.ParsePinningEventuallyQueue.3
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // bolts.Continuation
            public Task<Integer> then(Task<Void> task) throws Exception {
                return EventuallyPin.findAllPinned().continueWithTask(new Continuation<List<EventuallyPin>, Task<Integer>>() { // from class: com.parse.ParsePinningEventuallyQueue.3.1
                    /* JADX WARN: Can't rename method to resolve collision */
                    @Override // bolts.Continuation
                    public Task<Integer> then(Task<List<EventuallyPin>> task2) throws Exception {
                        return Task.forResult(Integer.valueOf(task2.getResult().size()));
                    }
                });
            }
        });
    }

    @Override // com.parse.ParseEventuallyQueue
    public void pause() {
        synchronized (this.connectionLock) {
            this.connectionTaskCompletionSource.trySetError(new PauseException());
            this.connectionTaskCompletionSource = Task.create();
            this.connectionTaskCompletionSource.trySetError(new PauseException());
        }
        synchronized (this.taskQueueSyncLock) {
            for (String key : this.pendingEventuallyTasks.keySet()) {
                this.pendingEventuallyTasks.get(key).trySetError(new PauseException());
            }
            this.pendingEventuallyTasks.clear();
            this.uuidToOperationSet.clear();
            this.uuidToEventuallyPin.clear();
        }
        try {
            ParseTaskUtils.wait(whenAll(Arrays.asList(this.taskQueue, this.operationSetTaskQueue)));
        } catch (ParseException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override // com.parse.ParseEventuallyQueue
    public void resume() {
        if (isConnected()) {
            this.connectionTaskCompletionSource.trySetResult(null);
            this.connectionTaskCompletionSource = Task.create();
            this.connectionTaskCompletionSource.trySetResult(null);
        } else {
            this.connectionTaskCompletionSource = Task.create();
        }
        populateQueueAsync();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Task<Void> waitForConnectionAsync() {
        Task<Void> task;
        synchronized (this.connectionLock) {
            task = this.connectionTaskCompletionSource.getTask();
        }
        return task;
    }

    @Override // com.parse.ParseEventuallyQueue
    public Task<JSONObject> enqueueEventuallyAsync(final ParseRESTCommand command, final ParseObject object) {
        Parse.requirePermission(UpdateConfig.g);
        final TaskCompletionSource<JSONObject> tcs = new TaskCompletionSource<>();
        this.taskQueue.enqueue(new Continuation<Void, Task<Void>>() { // from class: com.parse.ParsePinningEventuallyQueue.4
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // bolts.Continuation
            public Task<Void> then(Task<Void> toAwait) throws Exception {
                return ParsePinningEventuallyQueue.this.enqueueEventuallyAsync(command, object, toAwait, tcs);
            }
        });
        return tcs.getTask();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.parse.ParsePinningEventuallyQueue$5  reason: invalid class name */
    /* loaded from: classes2.dex */
    public class AnonymousClass5 implements Continuation<Void, Task<Void>> {
        final /* synthetic */ ParseRESTCommand val$command;
        final /* synthetic */ ParseObject val$object;
        final /* synthetic */ TaskCompletionSource val$tcs;

        AnonymousClass5(ParseObject parseObject, ParseRESTCommand parseRESTCommand, TaskCompletionSource taskCompletionSource) {
            this.val$object = parseObject;
            this.val$command = parseRESTCommand;
            this.val$tcs = taskCompletionSource;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // bolts.Continuation
        public Task<Void> then(Task<Void> toAwait) throws Exception {
            return EventuallyPin.pinEventuallyCommand(this.val$object, this.val$command).continueWithTask(new Continuation<EventuallyPin, Task<Void>>() { // from class: com.parse.ParsePinningEventuallyQueue.5.1
                /* JADX WARN: Can't rename method to resolve collision */
                @Override // bolts.Continuation
                public Task<Void> then(Task<EventuallyPin> task) throws Exception {
                    EventuallyPin pin = task.getResult();
                    Exception error = task.getError();
                    if (error != null) {
                        if (5 >= Parse.getLogLevel()) {
                            PLog.w(ParsePinningEventuallyQueue.TAG, "Unable to save command for later.", error);
                        }
                        ParsePinningEventuallyQueue.this.notifyTestHelper(4);
                        return Task.forResult(null);
                    }
                    ParsePinningEventuallyQueue.this.pendingOperationSetUUIDTasks.put(pin.getUUID(), AnonymousClass5.this.val$tcs);
                    ParsePinningEventuallyQueue.this.populateQueueAsync().continueWithTask(new Continuation<Void, Task<Void>>() { // from class: com.parse.ParsePinningEventuallyQueue.5.1.1
                        /* JADX WARN: Can't rename method to resolve collision */
                        @Override // bolts.Continuation
                        public Task<Void> then(Task<Void> task2) throws Exception {
                            ParsePinningEventuallyQueue.this.notifyTestHelper(3);
                            return task2;
                        }
                    });
                    return task.makeVoid();
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Task<Void> enqueueEventuallyAsync(ParseRESTCommand command, ParseObject object, Task<Void> toAwait, TaskCompletionSource<JSONObject> tcs) {
        return toAwait.continueWithTask(new AnonymousClass5(object, command, tcs));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Task<Void> populateQueueAsync() {
        return this.taskQueue.enqueue(new Continuation<Void, Task<Void>>() { // from class: com.parse.ParsePinningEventuallyQueue.6
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // bolts.Continuation
            public Task<Void> then(Task<Void> toAwait) throws Exception {
                return ParsePinningEventuallyQueue.this.populateQueueAsync(toAwait);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Task<Void> populateQueueAsync(Task<Void> toAwait) {
        return toAwait.continueWithTask(new Continuation<Void, Task<List<EventuallyPin>>>() { // from class: com.parse.ParsePinningEventuallyQueue.8
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // bolts.Continuation
            public Task<List<EventuallyPin>> then(Task<Void> task) throws Exception {
                return EventuallyPin.findAllPinned(ParsePinningEventuallyQueue.this.eventuallyPinUUIDQueue);
            }
        }).onSuccessTask(new Continuation<List<EventuallyPin>, Task<Void>>() { // from class: com.parse.ParsePinningEventuallyQueue.7
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // bolts.Continuation
            public Task<Void> then(Task<List<EventuallyPin>> task) throws Exception {
                for (EventuallyPin pin : task.getResult()) {
                    ParsePinningEventuallyQueue.this.runEventuallyAsync(pin);
                }
                return task.makeVoid();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Task<Void> runEventuallyAsync(final EventuallyPin eventuallyPin) {
        final String uuid = eventuallyPin.getUUID();
        if (this.eventuallyPinUUIDQueue.contains(uuid)) {
            return Task.forResult(null);
        }
        this.eventuallyPinUUIDQueue.add(uuid);
        this.operationSetTaskQueue.enqueue(new Continuation<Void, Task<Void>>() { // from class: com.parse.ParsePinningEventuallyQueue.9
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // bolts.Continuation
            public Task<Void> then(Task<Void> toAwait) throws Exception {
                return ParsePinningEventuallyQueue.this.runEventuallyAsync(eventuallyPin, toAwait).continueWithTask(new Continuation<Void, Task<Void>>() { // from class: com.parse.ParsePinningEventuallyQueue.9.1
                    /* JADX WARN: Can't rename method to resolve collision */
                    @Override // bolts.Continuation
                    public Task<Void> then(Task<Void> task) throws Exception {
                        ParsePinningEventuallyQueue.this.eventuallyPinUUIDQueue.remove(uuid);
                        return task;
                    }
                });
            }
        });
        return Task.forResult(null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Task<Void> runEventuallyAsync(final EventuallyPin eventuallyPin, Task<Void> toAwait) {
        return toAwait.continueWithTask(new Continuation<Void, Task<Void>>() { // from class: com.parse.ParsePinningEventuallyQueue.11
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // bolts.Continuation
            public Task<Void> then(Task<Void> task) throws Exception {
                return ParsePinningEventuallyQueue.this.waitForConnectionAsync();
            }
        }).onSuccessTask(new Continuation<Void, Task<Void>>() { // from class: com.parse.ParsePinningEventuallyQueue.10
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // bolts.Continuation
            public Task<Void> then(Task<Void> task) throws Exception {
                return ParsePinningEventuallyQueue.this.waitForOperationSetAndEventuallyPin(null, eventuallyPin).continueWithTask(new Continuation<JSONObject, Task<Void>>() { // from class: com.parse.ParsePinningEventuallyQueue.10.1
                    /* JADX WARN: Can't rename method to resolve collision */
                    @Override // bolts.Continuation
                    public Task<Void> then(Task<JSONObject> task2) throws Exception {
                        Exception error = task2.getError();
                        if (error == null) {
                            ParsePinningEventuallyQueue.this.notifyTestHelper(1);
                        } else if (error instanceof PauseException) {
                            return task2.makeVoid();
                        } else {
                            if (6 >= Parse.getLogLevel()) {
                                PLog.e(ParsePinningEventuallyQueue.TAG, "Failed to run command.", error);
                            }
                            ParsePinningEventuallyQueue.this.notifyTestHelper(2, error);
                        }
                        TaskCompletionSource<JSONObject> tcs = (TaskCompletionSource) ParsePinningEventuallyQueue.this.pendingOperationSetUUIDTasks.remove(eventuallyPin.getUUID());
                        if (tcs != null) {
                            if (error != null) {
                                tcs.setError(error);
                            } else {
                                tcs.setResult(task2.getResult());
                            }
                        }
                        return task2.makeVoid();
                    }
                });
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.parse.ParseEventuallyQueue
    public Task<JSONObject> waitForOperationSetAndEventuallyPin(ParseOperationSet operationSet, EventuallyPin eventuallyPin) {
        final String uuid;
        TaskCompletionSource<JSONObject> tcs;
        if (eventuallyPin != null && eventuallyPin.getType() != 1) {
            return process(eventuallyPin, null);
        }
        synchronized (this.taskQueueSyncLock) {
            if (operationSet != null && eventuallyPin == null) {
                uuid = operationSet.getUUID();
                this.uuidToOperationSet.put(uuid, operationSet);
            } else if (operationSet != null || eventuallyPin == null) {
                throw new IllegalStateException("Either operationSet or eventuallyPin must be set.");
            } else {
                uuid = eventuallyPin.getOperationSetUUID();
                this.uuidToEventuallyPin.put(uuid, eventuallyPin);
            }
            EventuallyPin eventuallyPin2 = this.uuidToEventuallyPin.get(uuid);
            ParseOperationSet operationSet2 = this.uuidToOperationSet.get(uuid);
            if (eventuallyPin2 == null || operationSet2 == null) {
                if (this.pendingEventuallyTasks.containsKey(uuid)) {
                    tcs = this.pendingEventuallyTasks.get(uuid);
                } else {
                    tcs = Task.create();
                    this.pendingEventuallyTasks.put(uuid, tcs);
                }
                return tcs.getTask();
            }
            final TaskCompletionSource<JSONObject> tcs2 = this.pendingEventuallyTasks.get(uuid);
            return process(eventuallyPin2, operationSet2).continueWithTask(new Continuation<JSONObject, Task<JSONObject>>() { // from class: com.parse.ParsePinningEventuallyQueue.12
                /* JADX WARN: Can't rename method to resolve collision */
                @Override // bolts.Continuation
                public Task<JSONObject> then(Task<JSONObject> task) throws Exception {
                    synchronized (ParsePinningEventuallyQueue.this.taskQueueSyncLock) {
                        ParsePinningEventuallyQueue.this.pendingEventuallyTasks.remove(uuid);
                        ParsePinningEventuallyQueue.this.uuidToOperationSet.remove(uuid);
                        ParsePinningEventuallyQueue.this.uuidToEventuallyPin.remove(uuid);
                    }
                    Exception error = task.getError();
                    if (error != null) {
                        tcs2.trySetError(error);
                    } else if (task.isCancelled()) {
                        tcs2.trySetCancelled();
                    } else {
                        tcs2.trySetResult(task.getResult());
                    }
                    return tcs2.getTask();
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.parse.ParsePinningEventuallyQueue$13  reason: invalid class name */
    /* loaded from: classes2.dex */
    public class AnonymousClass13 implements Continuation<Void, Task<JSONObject>> {
        final /* synthetic */ EventuallyPin val$eventuallyPin;
        final /* synthetic */ ParseOperationSet val$operationSet;

        AnonymousClass13(EventuallyPin eventuallyPin, ParseOperationSet parseOperationSet) {
            this.val$eventuallyPin = eventuallyPin;
            this.val$operationSet = parseOperationSet;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // bolts.Continuation
        public Task<JSONObject> then(Task<Void> task) throws Exception {
            Task executeAsync;
            final int type = this.val$eventuallyPin.getType();
            final ParseObject object = this.val$eventuallyPin.getObject();
            String sessionToken = this.val$eventuallyPin.getSessionToken();
            if (type == 1) {
                executeAsync = object.saveAsync(ParsePinningEventuallyQueue.this.httpClient, this.val$operationSet, sessionToken);
            } else if (type == 2) {
                executeAsync = object.deleteAsync(sessionToken).cast();
            } else {
                ParseRESTCommand command = this.val$eventuallyPin.getCommand();
                if (command == null) {
                    executeAsync = Task.forResult(null);
                    ParsePinningEventuallyQueue.this.notifyTestHelper(8);
                } else {
                    executeAsync = command.executeAsync(ParsePinningEventuallyQueue.this.httpClient);
                }
            }
            return executeAsync.continueWithTask(new Continuation<JSONObject, Task<JSONObject>>() { // from class: com.parse.ParsePinningEventuallyQueue.13.1
                /* JADX WARN: Can't rename method to resolve collision */
                @Override // bolts.Continuation
                public Task<JSONObject> then(final Task<JSONObject> executeTask) throws Exception {
                    Exception error = executeTask.getError();
                    if (error == null || !(error instanceof ParseException) || ((ParseException) error).getCode() != 100) {
                        return AnonymousClass13.this.val$eventuallyPin.unpinInBackground(EventuallyPin.PIN_NAME).continueWithTask(new Continuation<Void, Task<Void>>() { // from class: com.parse.ParsePinningEventuallyQueue.13.1.2
                            /* JADX WARN: Can't rename method to resolve collision */
                            @Override // bolts.Continuation
                            public Task<Void> then(Task<Void> task2) throws Exception {
                                JSONObject result = (JSONObject) executeTask.getResult();
                                if (type == 1) {
                                    return object.handleSaveEventuallyResultAsync(result, AnonymousClass13.this.val$operationSet);
                                }
                                if (type != 2 || executeTask.isFaulted()) {
                                    return task2;
                                }
                                return object.handleDeleteEventuallyResultAsync();
                            }
                        }).continueWithTask(new Continuation<Void, Task<JSONObject>>() { // from class: com.parse.ParsePinningEventuallyQueue.13.1.1
                            /* JADX WARN: Can't rename method to resolve collision */
                            @Override // bolts.Continuation
                            public Task<JSONObject> then(Task<Void> task2) throws Exception {
                                return executeTask;
                            }
                        });
                    }
                    ParsePinningEventuallyQueue.this.setConnected(false);
                    ParsePinningEventuallyQueue.this.notifyTestHelper(7);
                    return ParsePinningEventuallyQueue.this.process(AnonymousClass13.this.val$eventuallyPin, AnonymousClass13.this.val$operationSet);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Task<JSONObject> process(EventuallyPin eventuallyPin, ParseOperationSet operationSet) {
        return waitForConnectionAsync().onSuccessTask(new AnonymousClass13(eventuallyPin, operationSet));
    }

    @Override // com.parse.ParseEventuallyQueue
    void simulateReboot() {
        pause();
        this.pendingOperationSetUUIDTasks.clear();
        this.pendingEventuallyTasks.clear();
        this.uuidToOperationSet.clear();
        this.uuidToEventuallyPin.clear();
        resume();
    }

    @Override // com.parse.ParseEventuallyQueue
    public void clear() {
        pause();
        try {
            ParseTaskUtils.wait(this.taskQueue.enqueue(new Continuation<Void, Task<Void>>() { // from class: com.parse.ParsePinningEventuallyQueue.14
                /* JADX WARN: Can't rename method to resolve collision */
                @Override // bolts.Continuation
                public Task<Void> then(Task<Void> toAwait) throws Exception {
                    return toAwait.continueWithTask(new Continuation<Void, Task<Void>>() { // from class: com.parse.ParsePinningEventuallyQueue.14.1
                        /* JADX WARN: Can't rename method to resolve collision */
                        @Override // bolts.Continuation
                        public Task<Void> then(Task<Void> task) throws Exception {
                            return EventuallyPin.findAllPinned().onSuccessTask(new Continuation<List<EventuallyPin>, Task<Void>>() { // from class: com.parse.ParsePinningEventuallyQueue.14.1.1
                                /* JADX WARN: Can't rename method to resolve collision */
                                @Override // bolts.Continuation
                                public Task<Void> then(Task<List<EventuallyPin>> task2) throws Exception {
                                    List<Task<Void>> tasks = new ArrayList<>();
                                    for (EventuallyPin pin : task2.getResult()) {
                                        tasks.add(pin.unpinInBackground(EventuallyPin.PIN_NAME));
                                    }
                                    return Task.whenAll(tasks);
                                }
                            });
                        }
                    });
                }
            }));
            simulateReboot();
            resume();
        } catch (ParseException e) {
            throw new IllegalStateException(e);
        }
    }

    private Task<Void> whenAll(Collection<TaskQueue> taskQueues) {
        List<Task<Void>> tasks = new ArrayList<>();
        for (TaskQueue taskQueue : taskQueues) {
            tasks.add(taskQueue.enqueue(new Continuation<Void, Task<Void>>() { // from class: com.parse.ParsePinningEventuallyQueue.15
                /* JADX WARN: Can't rename method to resolve collision */
                @Override // bolts.Continuation
                public Task<Void> then(Task<Void> toAwait) throws Exception {
                    return toAwait;
                }
            }));
        }
        return Task.whenAll(tasks);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public static class PauseException extends Exception {
        private PauseException() {
        }
    }
}
