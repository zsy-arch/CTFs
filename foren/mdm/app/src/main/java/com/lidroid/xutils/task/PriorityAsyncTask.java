package com.lidroid.xutils.task;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.Process;
import com.lidroid.xutils.util.LogUtils;
import java.util.concurrent.Callable;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: classes2.dex */
public abstract class PriorityAsyncTask<Params, Progress, Result> implements TaskHandler {
    private static final int MESSAGE_POST_PROGRESS = 2;
    private static final int MESSAGE_POST_RESULT = 1;
    private Priority priority;
    private static final InternalHandler sHandler = new InternalHandler();
    public static final Executor sDefaultExecutor = new PriorityExecutor();
    private volatile boolean mExecuteInvoked = false;
    private final AtomicBoolean mCancelled = new AtomicBoolean();
    private final AtomicBoolean mTaskInvoked = new AtomicBoolean();
    private final WorkerRunnable<Params, Result> mWorker = new WorkerRunnable<Params, Result>() { // from class: com.lidroid.xutils.task.PriorityAsyncTask.1
        /* JADX WARN: Multi-variable type inference failed */
        @Override // java.util.concurrent.Callable
        public Result call() throws Exception {
            PriorityAsyncTask.this.mTaskInvoked.set(true);
            Process.setThreadPriority(10);
            return (Result) PriorityAsyncTask.this.postResult(PriorityAsyncTask.this.doInBackground(this.mParams));
        }
    };
    private final FutureTask<Result> mFuture = new FutureTask<Result>(this.mWorker) { // from class: com.lidroid.xutils.task.PriorityAsyncTask.2
        @Override // java.util.concurrent.FutureTask
        protected void done() {
            try {
                PriorityAsyncTask.this.postResultIfNotInvoked(get());
            } catch (InterruptedException e) {
                LogUtils.d(e.getMessage());
            } catch (CancellationException e2) {
                PriorityAsyncTask.this.postResultIfNotInvoked(null);
            } catch (ExecutionException e3) {
                throw new RuntimeException("An error occured while executing doInBackground()", e3.getCause());
            }
        }
    };

    protected abstract Result doInBackground(Params... paramsArr);

    public Priority getPriority() {
        return this.priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void postResultIfNotInvoked(Result result) {
        if (!this.mTaskInvoked.get()) {
            postResult(result);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Result postResult(Result result) {
        sHandler.obtainMessage(1, new AsyncTaskResult(this, result)).sendToTarget();
        return result;
    }

    protected void onPreExecute() {
    }

    protected void onPostExecute(Result result) {
    }

    protected void onProgressUpdate(Progress... values) {
    }

    protected void onCancelled(Result result) {
        onCancelled();
    }

    protected void onCancelled() {
    }

    @Override // com.lidroid.xutils.task.TaskHandler
    public final boolean isCancelled() {
        return this.mCancelled.get();
    }

    public final boolean cancel(boolean mayInterruptIfRunning) {
        this.mCancelled.set(true);
        return this.mFuture.cancel(mayInterruptIfRunning);
    }

    @Override // com.lidroid.xutils.task.TaskHandler
    public boolean supportPause() {
        return false;
    }

    @Override // com.lidroid.xutils.task.TaskHandler
    public boolean supportResume() {
        return false;
    }

    @Override // com.lidroid.xutils.task.TaskHandler
    public boolean supportCancel() {
        return true;
    }

    @Override // com.lidroid.xutils.task.TaskHandler
    public void pause() {
    }

    @Override // com.lidroid.xutils.task.TaskHandler
    public void resume() {
    }

    @Override // com.lidroid.xutils.task.TaskHandler
    public void cancel() {
        cancel(true);
    }

    @Override // com.lidroid.xutils.task.TaskHandler
    public boolean isPaused() {
        return false;
    }

    public final Result get() throws InterruptedException, ExecutionException {
        return this.mFuture.get();
    }

    public final Result get(long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
        return this.mFuture.get(timeout, unit);
    }

    public final PriorityAsyncTask<Params, Progress, Result> execute(Params... params) {
        return executeOnExecutor(sDefaultExecutor, params);
    }

    public final PriorityAsyncTask<Params, Progress, Result> executeOnExecutor(Executor exec, Params... params) {
        if (this.mExecuteInvoked) {
            throw new IllegalStateException("Cannot execute task: the task is already executed.");
        }
        this.mExecuteInvoked = true;
        onPreExecute();
        this.mWorker.mParams = params;
        exec.execute(new PriorityRunnable(this.priority, this.mFuture));
        return this;
    }

    public static void execute(Runnable runnable) {
        execute(runnable, Priority.DEFAULT);
    }

    public static void execute(Runnable runnable, Priority priority) {
        sDefaultExecutor.execute(new PriorityRunnable(priority, runnable));
    }

    protected final void publishProgress(Progress... values) {
        if (!isCancelled()) {
            sHandler.obtainMessage(2, new AsyncTaskResult(this, values)).sendToTarget();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void finish(Result result) {
        if (isCancelled()) {
            onCancelled(result);
        } else {
            onPostExecute(result);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public static class InternalHandler extends Handler {
        private InternalHandler() {
            super(Looper.getMainLooper());
        }

        @Override // android.os.Handler
        public void handleMessage(Message msg) {
            AsyncTaskResult<?> result = (AsyncTaskResult) msg.obj;
            switch (msg.what) {
                case 1:
                    result.mTask.finish(result.mData[0]);
                    return;
                case 2:
                    result.mTask.onProgressUpdate(result.mData);
                    return;
                default:
                    return;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public static abstract class WorkerRunnable<Params, Result> implements Callable<Result> {
        Params[] mParams;

        private WorkerRunnable() {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public static class AsyncTaskResult<Data> {
        final Data[] mData;
        final PriorityAsyncTask mTask;

        AsyncTaskResult(PriorityAsyncTask task, Data... data) {
            this.mTask = task;
            this.mData = data;
        }
    }
}
