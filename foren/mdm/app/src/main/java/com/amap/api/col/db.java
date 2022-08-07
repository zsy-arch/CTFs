package com.amap.api.col;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.Process;
import android.util.Log;
import java.util.ArrayDeque;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

/* compiled from: AsyncTask.java */
/* loaded from: classes.dex */
public abstract class db<Params, Progress, Result> {
    public static final Executor b;
    public static final Executor c;
    private static final b f;
    private static volatile Executor g;
    private static final ThreadFactory d = new ThreadFactory() { // from class: com.amap.api.col.db.1
        private final AtomicInteger a = new AtomicInteger(1);

        @Override // java.util.concurrent.ThreadFactory
        public Thread newThread(Runnable runnable) {
            return new Thread(runnable, "AsyncTask #" + this.a.getAndIncrement());
        }
    };
    private static final BlockingQueue<Runnable> e = new LinkedBlockingQueue(10);
    public static final Executor a = new ThreadPoolExecutor(5, 128, 1, TimeUnit.SECONDS, e, d, new ThreadPoolExecutor.DiscardOldestPolicy());
    private volatile d j = d.PENDING;
    private final AtomicBoolean k = new AtomicBoolean();
    private final AtomicBoolean l = new AtomicBoolean();
    private final e<Params, Result> h = new e<Params, Result>() { // from class: com.amap.api.col.db.2
        /* JADX WARN: Multi-variable type inference failed */
        @Override // java.util.concurrent.Callable
        public Result call() throws Exception {
            db.this.l.set(true);
            Process.setThreadPriority(10);
            return (Result) db.this.d(db.this.a(this.b));
        }
    };
    private final FutureTask<Result> i = new FutureTask<Result>(this.h) { // from class: com.amap.api.col.db.3
        @Override // java.util.concurrent.FutureTask
        protected void done() {
            try {
                db.this.c((db) db.this.i.get());
            } catch (InterruptedException e2) {
                Log.w("AsyncTask", e2);
            } catch (CancellationException e3) {
                db.this.c((db) null);
            } catch (ExecutionException e4) {
                throw new RuntimeException("An error occured while executing doInBackground()", e4.getCause());
            }
        }
    };

    /* compiled from: AsyncTask.java */
    /* loaded from: classes.dex */
    public enum d {
        PENDING,
        RUNNING,
        FINISHED
    }

    protected abstract Result a(Params... paramsArr);

    static {
        b = dt.c() ? new c() : Executors.newSingleThreadExecutor(d);
        c = Executors.newFixedThreadPool(2, d);
        f = new b(Looper.getMainLooper());
        g = b;
    }

    /* compiled from: AsyncTask.java */
    /* loaded from: classes.dex */
    private static class c implements Executor {
        final ArrayDeque<Runnable> a;
        Runnable b;

        private c() {
            this.a = new ArrayDeque<>();
        }

        @Override // java.util.concurrent.Executor
        public synchronized void execute(final Runnable runnable) {
            this.a.offer(new Runnable() { // from class: com.amap.api.col.db.c.1
                @Override // java.lang.Runnable
                public void run() {
                    try {
                        runnable.run();
                    } finally {
                        c.this.a();
                    }
                }
            });
            if (this.b == null) {
                a();
            }
        }

        protected synchronized void a() {
            Runnable poll = this.a.poll();
            this.b = poll;
            if (poll != null) {
                db.a.execute(this.b);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(Result result) {
        if (!this.l.get()) {
            d(result);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Result d(Result result) {
        f.obtainMessage(1, new a(this, result)).sendToTarget();
        return result;
    }

    public final d a() {
        return this.j;
    }

    protected void b() {
    }

    protected void a(Result result) {
    }

    protected void b(Progress... progressArr) {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void b(Result result) {
        c();
    }

    protected void c() {
    }

    public final boolean d() {
        return this.k.get();
    }

    public final boolean a(boolean z) {
        this.k.set(true);
        return this.i.cancel(z);
    }

    public final db<Params, Progress, Result> c(Params... paramsArr) {
        return a(g, paramsArr);
    }

    public final db<Params, Progress, Result> a(Executor executor, Params... paramsArr) {
        if (this.j != d.PENDING) {
            switch (this.j) {
                case RUNNING:
                    throw new IllegalStateException("Cannot execute task: the task is already running.");
                case FINISHED:
                    throw new IllegalStateException("Cannot execute task: the task has already been executed (a task can be executed only once)");
            }
        }
        this.j = d.RUNNING;
        b();
        this.h.b = paramsArr;
        executor.execute(this.i);
        return this;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(Result result) {
        if (d()) {
            b((db<Params, Progress, Result>) result);
        } else {
            a((db<Params, Progress, Result>) result);
        }
        this.j = d.FINISHED;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: AsyncTask.java */
    /* loaded from: classes.dex */
    public static class b extends Handler {
        public b(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            if (message.obj != null && (message.obj instanceof a)) {
                a aVar = (a) message.obj;
                switch (message.what) {
                    case 1:
                        aVar.a.e(aVar.b[0]);
                        return;
                    case 2:
                        aVar.a.b((Object[]) aVar.b);
                        return;
                    default:
                        return;
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: AsyncTask.java */
    /* loaded from: classes.dex */
    public static abstract class e<Params, Result> implements Callable<Result> {
        Params[] b;

        private e() {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: AsyncTask.java */
    /* loaded from: classes.dex */
    public static class a<Data> {
        final db a;
        final Data[] b;

        a(db dbVar, Data... dataArr) {
            this.a = dbVar;
            this.b = dataArr;
        }
    }
}
