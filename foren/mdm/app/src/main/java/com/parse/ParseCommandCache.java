package com.parse;

import android.content.Context;
import android.content.Intent;
import bolts.Capture;
import bolts.Continuation;
import bolts.Task;
import bolts.TaskCompletionSource;
import com.lidroid.xutils.bitmap.BitmapGlobalConfig;
import com.parse.ConnectivityNotifier;
import com.umeng.update.UpdateConfig;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.concurrent.Callable;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public class ParseCommandCache extends ParseEventuallyQueue {
    private static final String TAG = "com.parse.ParseCommandCache";
    private static int filenameCounter = 0;
    private static final Object lock = new Object();
    private final ParseHttpClient httpClient;
    ConnectivityNotifier notifier;
    private boolean unprocessedCommandsExist;
    private int timeoutMaxRetries = 5;
    private double timeoutRetryWaitSeconds = 600.0d;
    private int maxCacheSizeBytes = BitmapGlobalConfig.MIN_DISK_CACHE_SIZE;
    private HashMap<File, TaskCompletionSource<JSONObject>> pendingTasks = new HashMap<>();
    ConnectivityNotifier.ConnectivityListener listener = new ConnectivityNotifier.ConnectivityListener() { // from class: com.parse.ParseCommandCache.1
        @Override // com.parse.ConnectivityNotifier.ConnectivityListener
        public void networkConnectivityStatusChanged(Context context, Intent intent) {
            final boolean connectionLost = intent.getBooleanExtra("noConnectivity", false);
            final boolean isConnected = ConnectivityNotifier.isConnected(context);
            Task.call(new Callable<Void>() { // from class: com.parse.ParseCommandCache.1.1
                @Override // java.util.concurrent.Callable
                public Void call() throws Exception {
                    if (connectionLost) {
                        ParseCommandCache.this.setConnected(false);
                        return null;
                    }
                    ParseCommandCache.this.setConnected(isConnected);
                    return null;
                }
            }, ParseExecutors.io());
        }
    };
    private boolean shouldStop = false;
    private boolean running = false;
    private final Object runningLock = new Object();
    private Logger log = Logger.getLogger(TAG);
    private File cachePath = getCacheDir();

    private static File getCacheDir() {
        File cacheDir = new File(Parse.getParseDir(), "CommandCache");
        cacheDir.mkdirs();
        return cacheDir;
    }

    public static int getPendingCount() {
        int length;
        synchronized (lock) {
            String[] files = getCacheDir().list();
            length = files == null ? 0 : files.length;
        }
        return length;
    }

    public ParseCommandCache(Context context, ParseHttpClient client) {
        setConnected(false);
        this.httpClient = client;
        if (Parse.hasPermission(UpdateConfig.g)) {
            setConnected(ConnectivityNotifier.isConnected(context));
            this.notifier = ConnectivityNotifier.getNotifier(context);
            this.notifier.addListener(this.listener);
            resume();
        }
    }

    @Override // com.parse.ParseEventuallyQueue
    public void onDestroy() {
        this.notifier.removeListener(this.listener);
    }

    public void setTimeoutMaxRetries(int tries) {
        synchronized (lock) {
            this.timeoutMaxRetries = tries;
        }
    }

    @Override // com.parse.ParseEventuallyQueue
    public void setTimeoutRetryWaitSeconds(double seconds) {
        synchronized (lock) {
            this.timeoutRetryWaitSeconds = seconds;
        }
    }

    @Override // com.parse.ParseEventuallyQueue
    public void setMaxCacheSizeBytes(int bytes) {
        synchronized (lock) {
            this.maxCacheSizeBytes = bytes;
        }
    }

    /* JADX WARN: Type inference failed for: r1v2, types: [com.parse.ParseCommandCache$2] */
    @Override // com.parse.ParseEventuallyQueue
    public void resume() {
        synchronized (this.runningLock) {
            if (!this.running) {
                new Thread("ParseCommandCache.runLoop()") { // from class: com.parse.ParseCommandCache.2
                    @Override // java.lang.Thread, java.lang.Runnable
                    public void run() {
                        ParseCommandCache.this.runLoop();
                    }
                }.start();
                try {
                    this.runningLock.wait();
                } catch (InterruptedException e) {
                    synchronized (lock) {
                        this.shouldStop = true;
                        lock.notifyAll();
                    }
                }
            }
        }
    }

    @Override // com.parse.ParseEventuallyQueue
    public void pause() {
        synchronized (this.runningLock) {
            if (this.running) {
                synchronized (lock) {
                    this.shouldStop = true;
                    lock.notifyAll();
                }
            }
            while (this.running) {
                try {
                    this.runningLock.wait();
                } catch (InterruptedException e) {
                }
            }
        }
    }

    private void removeFile(File file) {
        synchronized (lock) {
            this.pendingTasks.remove(file);
            try {
                commandFromJSON(ParseFileUtils.readFileToJSONObject(file)).releaseLocalIds();
            } catch (Exception e) {
            }
            ParseFileUtils.deleteQuietly(file);
        }
    }

    @Override // com.parse.ParseEventuallyQueue
    void simulateReboot() {
        synchronized (lock) {
            this.pendingTasks.clear();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.parse.ParseEventuallyQueue
    public void fakeObjectUpdate() {
        notifyTestHelper(3);
        notifyTestHelper(1);
        notifyTestHelper(5);
    }

    @Override // com.parse.ParseEventuallyQueue
    public Task<JSONObject> enqueueEventuallyAsync(ParseRESTCommand command, ParseObject object) {
        return enqueueEventuallyAsync(command, false, object);
    }

    private Task<JSONObject> enqueueEventuallyAsync(ParseRESTCommand command, boolean preferOldest, ParseObject object) {
        Parse.requirePermission(UpdateConfig.g);
        TaskCompletionSource<JSONObject> tcs = new TaskCompletionSource<>();
        if (object != null) {
            try {
                if (object.getObjectId() == null) {
                    command.setLocalId(object.getOrCreateLocalId());
                }
            } catch (UnsupportedEncodingException e) {
                if (5 >= Parse.getLogLevel()) {
                    this.log.log(Level.WARNING, "UTF-8 isn't supported.  This shouldn't happen.", (Throwable) e);
                }
                notifyTestHelper(4);
                return Task.forResult(null);
            }
        }
        byte[] json = command.toJSONObject().toString().getBytes("UTF-8");
        if (json.length > this.maxCacheSizeBytes) {
            if (5 >= Parse.getLogLevel()) {
                this.log.warning("Unable to save command for later because it's too big.");
            }
            notifyTestHelper(4);
            return Task.forResult(null);
        }
        synchronized (lock) {
            try {
                String[] fileNames = this.cachePath.list();
                if (fileNames != null) {
                    Arrays.sort(fileNames);
                    int size = 0;
                    for (String fileName : fileNames) {
                        size += (int) new File(this.cachePath, fileName).length();
                    }
                    int size2 = size + json.length;
                    if (size2 > this.maxCacheSizeBytes) {
                        if (preferOldest) {
                            if (5 >= Parse.getLogLevel()) {
                                this.log.warning("Unable to save command for later because storage is full.");
                            }
                            Task<JSONObject> forResult = Task.forResult(null);
                            lock.notifyAll();
                            return forResult;
                        }
                        if (5 >= Parse.getLogLevel()) {
                            this.log.warning("Deleting old commands to make room in command cache.");
                        }
                        for (int indexToDelete = 0; size2 > this.maxCacheSizeBytes && indexToDelete < fileNames.length; indexToDelete++) {
                            File file = new File(this.cachePath, fileNames[indexToDelete]);
                            size2 -= (int) file.length();
                            removeFile(file);
                        }
                    }
                }
                String prefix1 = Long.toHexString(System.currentTimeMillis());
                if (prefix1.length() < 16) {
                    char[] zeroes = new char[16 - prefix1.length()];
                    Arrays.fill(zeroes, '0');
                    prefix1 = new String(zeroes) + prefix1;
                }
                int i = filenameCounter;
                filenameCounter = i + 1;
                String prefix2 = Integer.toHexString(i);
                if (prefix2.length() < 8) {
                    char[] zeroes2 = new char[8 - prefix2.length()];
                    Arrays.fill(zeroes2, '0');
                    prefix2 = new String(zeroes2) + prefix2;
                }
                File path = File.createTempFile("CachedCommand_" + prefix1 + "_" + prefix2 + "_", "", this.cachePath);
                this.pendingTasks.put(path, tcs);
                command.retainLocalIds();
                ParseFileUtils.writeByteArrayToFile(path, json);
                notifyTestHelper(3);
                this.unprocessedCommandsExist = true;
                lock.notifyAll();
            } catch (IOException e2) {
                if (5 >= Parse.getLogLevel()) {
                    this.log.log(Level.WARNING, "Unable to save command for later.", (Throwable) e2);
                }
                lock.notifyAll();
            }
            return tcs.getTask();
        }
    }

    @Override // com.parse.ParseEventuallyQueue
    public int pendingCount() {
        return getPendingCount();
    }

    @Override // com.parse.ParseEventuallyQueue
    public void clear() {
        synchronized (lock) {
            File[] files = this.cachePath.listFiles();
            if (files != null) {
                for (File file : files) {
                    removeFile(file);
                }
                this.pendingTasks.clear();
            }
        }
    }

    @Override // com.parse.ParseEventuallyQueue
    public void setConnected(boolean connected) {
        synchronized (lock) {
            if (isConnected() != connected && connected) {
                lock.notifyAll();
            }
            super.setConnected(connected);
        }
    }

    private <T> T waitForTaskWithoutLock(Task<T> task) throws ParseException {
        T t;
        synchronized (lock) {
            final Capture<Boolean> finished = new Capture<>(false);
            task.continueWith((Continuation<T, Void>) new Continuation<T, Void>() { // from class: com.parse.ParseCommandCache.3
                @Override // bolts.Continuation
                public Void then(Task<T> task2) throws Exception {
                    finished.set(true);
                    synchronized (ParseCommandCache.lock) {
                        ParseCommandCache.lock.notifyAll();
                    }
                    return null;
                }
            }, Task.BACKGROUND_EXECUTOR);
            while (!finished.get().booleanValue()) {
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    this.shouldStop = true;
                }
            }
            t = (T) ParseTaskUtils.wait(task);
        }
        return t;
    }

    private void maybeRunAllCommandsNow(int retriesRemaining) {
        Object continueWithTask;
        synchronized (lock) {
            this.unprocessedCommandsExist = false;
            if (isConnected()) {
                String[] fileNames = this.cachePath.list();
                if (!(fileNames == null || fileNames.length == 0)) {
                    Arrays.sort(fileNames);
                    for (String fileName : fileNames) {
                        File file = new File(this.cachePath, fileName);
                        try {
                            try {
                                JSONObject json = ParseFileUtils.readFileToJSONObject(file);
                                final TaskCompletionSource<JSONObject> tcs = this.pendingTasks.containsKey(file) ? this.pendingTasks.get(file) : null;
                                try {
                                    final ParseRESTCommand command = commandFromJSON(json);
                                    if (command == null) {
                                        try {
                                            continueWithTask = Task.forResult(null);
                                            if (tcs != null) {
                                                tcs.setResult(null);
                                            }
                                            notifyTestHelper(8);
                                        } catch (ParseException e) {
                                            if (e.getCode() != 100) {
                                                if (6 >= Parse.getLogLevel()) {
                                                    this.log.log(Level.SEVERE, "Failed to run command.", (Throwable) e);
                                                }
                                                removeFile(file);
                                                notifyTestHelper(2, e);
                                            } else if (retriesRemaining > 0) {
                                                if (4 >= Parse.getLogLevel()) {
                                                    this.log.info("Network timeout in command cache. Waiting for " + this.timeoutRetryWaitSeconds + " seconds and then retrying " + retriesRemaining + " times.");
                                                }
                                                long currentTime = System.currentTimeMillis();
                                                long waitUntil = currentTime + ((long) (this.timeoutRetryWaitSeconds * 1000.0d));
                                                while (currentTime < waitUntil) {
                                                    if (!isConnected() || this.shouldStop) {
                                                        if (4 >= Parse.getLogLevel()) {
                                                            this.log.info("Aborting wait because runEventually thread should stop.");
                                                        }
                                                        return;
                                                    }
                                                    try {
                                                        lock.wait(waitUntil - currentTime);
                                                    } catch (InterruptedException e2) {
                                                        this.shouldStop = true;
                                                    }
                                                    currentTime = System.currentTimeMillis();
                                                    if (currentTime < waitUntil - ((long) (this.timeoutRetryWaitSeconds * 1000.0d))) {
                                                        currentTime = waitUntil - ((long) (this.timeoutRetryWaitSeconds * 1000.0d));
                                                    }
                                                }
                                                maybeRunAllCommandsNow(retriesRemaining - 1);
                                            } else {
                                                setConnected(false);
                                                notifyTestHelper(7);
                                            }
                                        }
                                    } else {
                                        continueWithTask = command.executeAsync(this.httpClient).continueWithTask(new Continuation<JSONObject, Task<JSONObject>>() { // from class: com.parse.ParseCommandCache.4
                                            /* JADX WARN: Can't rename method to resolve collision */
                                            @Override // bolts.Continuation
                                            public Task<JSONObject> then(Task<JSONObject> task) throws Exception {
                                                String objectId;
                                                String localId = command.getLocalId();
                                                Exception error = task.getError();
                                                if (error == null) {
                                                    JSONObject json2 = task.getResult();
                                                    if (tcs != null) {
                                                        tcs.setResult(json2);
                                                    } else if (!(localId == null || (objectId = json2.optString("objectId", null)) == null)) {
                                                        ParseCorePlugins.getInstance().getLocalIdManager().setObjectId(localId, objectId);
                                                    }
                                                } else if ((!(error instanceof ParseException) || ((ParseException) error).getCode() != 100) && tcs != null) {
                                                    tcs.setError(error);
                                                }
                                                return task;
                                            }
                                        });
                                    }
                                    waitForTaskWithoutLock(continueWithTask);
                                    if (tcs != null) {
                                        waitForTaskWithoutLock(tcs.getTask());
                                    }
                                    removeFile(file);
                                    notifyTestHelper(1);
                                } catch (JSONException e3) {
                                    if (6 >= Parse.getLogLevel()) {
                                        this.log.log(Level.SEVERE, "Unable to create ParseCommand from JSON.", (Throwable) e3);
                                    }
                                    removeFile(file);
                                }
                            } catch (FileNotFoundException e4) {
                                if (6 >= Parse.getLogLevel()) {
                                    this.log.log(Level.SEVERE, "File disappeared from cache while being read.", (Throwable) e4);
                                }
                            }
                        } catch (IOException e5) {
                            if (6 >= Parse.getLogLevel()) {
                                this.log.log(Level.SEVERE, "Unable to read contents of file in cache.", (Throwable) e5);
                            }
                            removeFile(file);
                        } catch (JSONException e6) {
                            if (6 >= Parse.getLogLevel()) {
                                this.log.log(Level.SEVERE, "Error parsing JSON found in cache.", (Throwable) e6);
                            }
                            removeFile(file);
                        }
                    }
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void runLoop() {
        boolean shouldRun;
        if (4 >= Parse.getLogLevel()) {
            this.log.info("Parse command cache has started processing queued commands.");
        }
        synchronized (this.runningLock) {
            if (!this.running) {
                this.running = true;
                this.runningLock.notifyAll();
                synchronized (lock) {
                    shouldRun = !this.shouldStop && !Thread.interrupted();
                }
                while (shouldRun) {
                    synchronized (lock) {
                        try {
                            maybeRunAllCommandsNow(this.timeoutMaxRetries);
                            if (!this.shouldStop) {
                                try {
                                    if (!this.unprocessedCommandsExist) {
                                        lock.wait();
                                    }
                                } catch (InterruptedException e) {
                                    this.shouldStop = true;
                                }
                            }
                            shouldRun = !this.shouldStop;
                        } catch (Exception e2) {
                            if (6 >= Parse.getLogLevel()) {
                                this.log.log(Level.SEVERE, "saveEventually thread had an error.", (Throwable) e2);
                            }
                            shouldRun = !this.shouldStop;
                        }
                    }
                }
                synchronized (this.runningLock) {
                    this.running = false;
                    this.runningLock.notifyAll();
                }
                if (4 >= Parse.getLogLevel()) {
                    this.log.info("saveEventually thread has stopped processing commands.");
                }
            }
        }
    }
}
