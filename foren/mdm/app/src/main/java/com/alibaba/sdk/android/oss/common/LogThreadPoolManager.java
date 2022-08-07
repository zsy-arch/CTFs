package com.alibaba.sdk.android.oss.common;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* loaded from: classes.dex */
public class LogThreadPoolManager {
    private static final int PERIOD_TASK_QOS = 1000;
    private static final int SIZE_CACHE_QUEUE = 200;
    private static final int SIZE_CORE_POOL = 1;
    private static final int SIZE_MAX_POOL = 1;
    private static final int SIZE_WORK_QUEUE = 500;
    private static final int TIME_KEEP_ALIVE = 5000;
    private static LogThreadPoolManager sThreadPoolManager = new LogThreadPoolManager();
    private final Queue<Runnable> mTaskQueue = new LinkedList();
    private final RejectedExecutionHandler mHandler = new RejectedExecutionHandler() { // from class: com.alibaba.sdk.android.oss.common.LogThreadPoolManager.1
        @Override // java.util.concurrent.RejectedExecutionHandler
        public void rejectedExecution(Runnable task, ThreadPoolExecutor executor) {
            if (LogThreadPoolManager.this.mTaskQueue.size() >= 200) {
                LogThreadPoolManager.this.mTaskQueue.poll();
            }
            LogThreadPoolManager.this.mTaskQueue.offer(task);
            OSSLog.logDebug("cache queue size: " + LogThreadPoolManager.this.mTaskQueue.size(), false);
        }
    };
    private final Runnable mAccessBufferThread = new Runnable() { // from class: com.alibaba.sdk.android.oss.common.LogThreadPoolManager.2
        @Override // java.lang.Runnable
        public void run() {
            if (LogThreadPoolManager.this.hasMoreAcquire()) {
                LogThreadPoolManager.this.mThreadPool.execute((Runnable) LogThreadPoolManager.this.mTaskQueue.poll());
            }
        }
    };
    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
    protected final ScheduledFuture<?> mTaskHandler = this.scheduler.scheduleAtFixedRate(this.mAccessBufferThread, 0, 1000, TimeUnit.MILLISECONDS);
    private final ThreadPoolExecutor mThreadPool = new ThreadPoolExecutor(1, 1, 5000, TimeUnit.SECONDS, new ArrayBlockingQueue(500), this.mHandler);

    public static LogThreadPoolManager newInstance() {
        return sThreadPoolManager;
    }

    private LogThreadPoolManager() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean hasMoreAcquire() {
        return !this.mTaskQueue.isEmpty();
    }

    public void addExecuteTask(Runnable task) {
        if (task != null) {
            this.mThreadPool.execute(task);
            OSSLog.logDebug("work queue size: " + this.mThreadPool.getQueue().size(), false);
        }
    }
}
