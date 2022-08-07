package com.parse;

import bolts.Continuation;
import bolts.Task;
import com.parse.ParseObject;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public class OfflineObjectStore<T extends ParseObject> implements ParseObjectStore<T> {
    private final String className;
    private final ParseObjectStore<T> legacy;
    private final String pinName;

    private static ParseObjectSubclassingController getSubclassingController() {
        return ParseCorePlugins.getInstance().getSubclassingController();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static <T extends ParseObject> Task<T> migrate(final ParseObjectStore<T> from, final ParseObjectStore<T> to) {
        return (Task<T>) from.getAsync().onSuccessTask(new Continuation<T, Task<T>>() { // from class: com.parse.OfflineObjectStore.1
            @Override // bolts.Continuation
            public Task<T> then(Task<T> task) throws Exception {
                final T object = task.getResult();
                return object == null ? task : (Task<T>) Task.whenAll(Arrays.asList(ParseObjectStore.this.deleteAsync(), to.setAsync(object))).continueWith(new Continuation<Void, T>() { // from class: com.parse.OfflineObjectStore.1.1
                    /* JADX WARN: Can't rename method to resolve collision */
                    @Override // bolts.Continuation
                    public T then(Task<Void> task2) throws Exception {
                        return (T) object;
                    }
                });
            }
        });
    }

    public OfflineObjectStore(Class<T> clazz, String pinName, ParseObjectStore<T> legacy) {
        this(getSubclassingController().getClassName(clazz), pinName, legacy);
    }

    public OfflineObjectStore(String className, String pinName, ParseObjectStore<T> legacy) {
        this.className = className;
        this.pinName = pinName;
        this.legacy = legacy;
    }

    @Override // com.parse.ParseObjectStore
    public Task<Void> setAsync(final T object) {
        return ParseObject.unpinAllInBackground(this.pinName).continueWithTask(new Continuation<Void, Task<Void>>() { // from class: com.parse.OfflineObjectStore.2
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // bolts.Continuation
            public Task<Void> then(Task<Void> task) throws Exception {
                return object.pinInBackground(OfflineObjectStore.this.pinName, false);
            }
        });
    }

    @Override // com.parse.ParseObjectStore
    public Task<T> getAsync() {
        return ParseQuery.getQuery(this.className).fromPin(this.pinName).ignoreACLs().findInBackground().onSuccessTask(new Continuation<List<T>, Task<T>>() { // from class: com.parse.OfflineObjectStore.4
            @Override // bolts.Continuation
            public Task<T> then(Task<List<T>> task) throws Exception {
                List<T> results = task.getResult();
                if (results == null) {
                    return Task.forResult(null);
                }
                if (results.size() == 1) {
                    return Task.forResult(results.get(0));
                }
                return (Task<T>) ParseObject.unpinAllInBackground(OfflineObjectStore.this.pinName).cast();
            }
        }).onSuccessTask(new Continuation<T, Task<T>>() { // from class: com.parse.OfflineObjectStore.3
            @Override // bolts.Continuation
            public Task<T> then(Task<T> task) throws Exception {
                if (task.getResult() != null) {
                    return task;
                }
                return OfflineObjectStore.migrate(OfflineObjectStore.this.legacy, OfflineObjectStore.this).cast();
            }
        });
    }

    @Override // com.parse.ParseObjectStore
    public Task<Boolean> existsAsync() {
        return ParseQuery.getQuery(this.className).fromPin(this.pinName).ignoreACLs().countInBackground().onSuccessTask(new Continuation<Integer, Task<Boolean>>() { // from class: com.parse.OfflineObjectStore.5
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // bolts.Continuation
            public Task<Boolean> then(Task<Integer> task) throws Exception {
                if (task.getResult().intValue() == 1) {
                    return Task.forResult(true);
                }
                return OfflineObjectStore.this.legacy.existsAsync();
            }
        });
    }

    @Override // com.parse.ParseObjectStore
    public Task<Void> deleteAsync() {
        final Task<Void> ldsTask = ParseObject.unpinAllInBackground(this.pinName);
        return Task.whenAll(Arrays.asList(this.legacy.deleteAsync(), ldsTask)).continueWithTask(new Continuation<Void, Task<Void>>() { // from class: com.parse.OfflineObjectStore.6
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // bolts.Continuation
            public Task<Void> then(Task<Void> task) throws Exception {
                return ldsTask;
            }
        });
    }
}
