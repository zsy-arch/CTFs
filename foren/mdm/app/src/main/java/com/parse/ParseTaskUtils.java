package com.parse;

import bolts.AggregateException;
import bolts.Continuation;
import bolts.Task;
import bolts.TaskCompletionSource;
import java.util.concurrent.CancellationException;

/* loaded from: classes2.dex */
public class ParseTaskUtils {
    ParseTaskUtils() {
    }

    public static <T> T wait(Task<T> task) throws ParseException {
        try {
            task.waitForCompletion();
            if (task.isFaulted()) {
                Exception error = task.getError();
                if (error instanceof ParseException) {
                    throw ((ParseException) error);
                } else if (error instanceof AggregateException) {
                    throw new ParseException(error);
                } else if (error instanceof RuntimeException) {
                    throw ((RuntimeException) error);
                } else {
                    throw new RuntimeException(error);
                }
            } else if (!task.isCancelled()) {
                return task.getResult();
            } else {
                throw new RuntimeException(new CancellationException());
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static Task<Void> callbackOnMainThreadAsync(Task<Void> task, ParseCallback1<ParseException> callback) {
        return callbackOnMainThreadAsync(task, callback, false);
    }

    static Task<Void> callbackOnMainThreadAsync(Task<Void> task, final ParseCallback1<ParseException> callback, boolean reportCancellation) {
        return callback == null ? task : callbackOnMainThreadAsync(task, new ParseCallback2<Void, ParseException>() { // from class: com.parse.ParseTaskUtils.1
            public void done(Void aVoid, ParseException e) {
                callback.done(e);
            }
        }, reportCancellation);
    }

    public static <T> Task<T> callbackOnMainThreadAsync(Task<T> task, ParseCallback2<T, ParseException> callback) {
        return callbackOnMainThreadAsync((Task) task, (ParseCallback2) callback, false);
    }

    static <T> Task<T> callbackOnMainThreadAsync(Task<T> task, final ParseCallback2<T, ParseException> callback, final boolean reportCancellation) {
        if (callback == null) {
            return task;
        }
        final TaskCompletionSource<T> tcs = new TaskCompletionSource<>();
        task.continueWith((Continuation<T, Void>) new Continuation<T, Void>() { // from class: com.parse.ParseTaskUtils.2
            @Override // bolts.Continuation
            public Void then(final Task<T> task2) throws Exception {
                if (!task2.isCancelled() || reportCancellation) {
                    ParseExecutors.main().execute(new Runnable() { // from class: com.parse.ParseTaskUtils.2.1
                        @Override // java.lang.Runnable
                        public void run() {
                            try {
                                Exception error = task2.getError();
                                if (error != null && !(error instanceof ParseException)) {
                                    error = new ParseException(error);
                                }
                                callback.done(task2.getResult(), (ParseException) error);
                                if (task2.isCancelled()) {
                                    tcs.setCancelled();
                                } else if (task2.isFaulted()) {
                                    tcs.setError(task2.getError());
                                } else {
                                    tcs.setResult(task2.getResult());
                                }
                            } catch (Throwable th) {
                                if (task2.isCancelled()) {
                                    tcs.setCancelled();
                                } else if (task2.isFaulted()) {
                                    tcs.setError(task2.getError());
                                } else {
                                    tcs.setResult(task2.getResult());
                                }
                                throw th;
                            }
                        }
                    });
                } else {
                    tcs.setCancelled();
                }
                return null;
            }
        });
        return tcs.getTask();
    }
}
