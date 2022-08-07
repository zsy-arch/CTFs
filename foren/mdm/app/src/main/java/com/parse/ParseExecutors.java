package com.parse;

import bolts.Task;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

/* loaded from: classes2.dex */
class ParseExecutors {
    private static final Object SCHEDULED_EXECUTOR_LOCK = new Object();
    private static ScheduledExecutorService scheduledExecutor;

    ParseExecutors() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static ScheduledExecutorService scheduled() {
        synchronized (SCHEDULED_EXECUTOR_LOCK) {
            if (scheduledExecutor == null) {
                scheduledExecutor = Executors.newScheduledThreadPool(1);
            }
        }
        return scheduledExecutor;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Executor main() {
        return Task.UI_THREAD_EXECUTOR;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Executor io() {
        return Task.BACKGROUND_EXECUTOR;
    }
}
