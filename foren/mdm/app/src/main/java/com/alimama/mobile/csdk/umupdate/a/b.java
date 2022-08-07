package com.alimama.mobile.csdk.umupdate.a;

import android.os.Build;
import android.os.Handler;
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
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

/* compiled from: AsyncTask.java */
/* loaded from: classes.dex */
public abstract class b<Params, Progress, Result> {
    public static final Executor b;
    public static final Executor c;
    public static final Executor d;
    private static final String e = "AsyncTask";
    private static final int f = 5;
    private static final int g = 128;
    private static final int h = 1;
    private static final int k = 1;
    private static final int l = 2;
    private static final HandlerC0005b m;
    private static volatile Executor n;
    private static final ThreadFactory i = new ThreadFactory() { // from class: com.alimama.mobile.csdk.umupdate.a.b.1
        private final AtomicInteger a = new AtomicInteger(1);

        @Override // java.util.concurrent.ThreadFactory
        public Thread newThread(Runnable runnable) {
            return new Thread(runnable, "AsyncTask #" + this.a.getAndIncrement());
        }
    };
    private static final BlockingQueue<Runnable> j = new LinkedBlockingQueue(10);
    public static final Executor a = new ThreadPoolExecutor(5, 128, 1, TimeUnit.SECONDS, j, i, new ThreadPoolExecutor.DiscardOldestPolicy());
    private volatile d q = d.PENDING;
    private final AtomicBoolean r = new AtomicBoolean();
    private final AtomicBoolean s = new AtomicBoolean();
    private final e<Params, Result> o = new e<Params, Result>() { // from class: com.alimama.mobile.csdk.umupdate.a.b.2
        /* JADX WARN: Multi-variable type inference failed */
        @Override // java.util.concurrent.Callable
        public Result call() throws Exception {
            b.this.s.set(true);
            Process.setThreadPriority(10);
            return (Result) b.this.d((b) b.this.a(this.b));
        }
    };
    private final FutureTask<Result> p = new FutureTask<Result>(this.o) { // from class: com.alimama.mobile.csdk.umupdate.a.b.3
        @Override // java.util.concurrent.FutureTask
        protected void done() {
            try {
                b.this.c((b) get());
            } catch (InterruptedException e2) {
                Log.w(b.e, e2);
            } catch (CancellationException e3) {
                b.this.c((b) null);
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
        Executor newSingleThreadExecutor;
        Executor newSingleThreadExecutor2;
        if (g()) {
            newSingleThreadExecutor = new c();
        } else {
            newSingleThreadExecutor = Executors.newSingleThreadExecutor(i);
        }
        b = newSingleThreadExecutor;
        if (g()) {
            newSingleThreadExecutor2 = new c();
        } else {
            newSingleThreadExecutor2 = Executors.newSingleThreadExecutor(i);
        }
        c = newSingleThreadExecutor2;
        d = b;
        m = new HandlerC0005b();
        n = b;
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
            this.a.offer(new Runnable() { // from class: com.alimama.mobile.csdk.umupdate.a.b.c.1
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
                b.a.execute(this.b);
            }
        }
    }

    public static void a() {
        m.getLooper();
    }

    public static void a(Executor executor) {
        n = executor;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(Result result) {
        if (!this.s.get()) {
            d((b<Params, Progress, Result>) result);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Result d(Result result) {
        m.obtainMessage(1, new a(this, result)).sendToTarget();
        return result;
    }

    public final d b() {
        return this.q;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void c() {
    }

    protected void a(Result result) {
    }

    protected void b(Progress... progressArr) {
    }

    protected void b(Result result) {
        d();
    }

    protected void d() {
    }

    public final boolean e() {
        return this.r.get();
    }

    public final boolean a(boolean z) {
        this.r.set(true);
        return this.p.cancel(z);
    }

    public final Result f() throws InterruptedException, ExecutionException {
        return this.p.get();
    }

    public final Result a(long j2, TimeUnit timeUnit) throws InterruptedException, ExecutionException, TimeoutException {
        return this.p.get(j2, timeUnit);
    }

    public final b<Params, Progress, Result> c(Params... paramsArr) {
        return a(n, paramsArr);
    }

    public final b<Params, Progress, Result> a(Executor executor, Params... paramsArr) {
        if (this.q != d.PENDING) {
            switch (this.q) {
                case RUNNING:
                    throw new IllegalStateException("Cannot execute task: the task is already running.");
                case FINISHED:
                    throw new IllegalStateException("Cannot execute task: the task has already been executed (a task can be executed only once)");
            }
        }
        this.q = d.RUNNING;
        c();
        this.o.b = paramsArr;
        executor.execute(this.p);
        return this;
    }

    public static void a(Runnable runnable) {
        n.execute(runnable);
    }

    protected final void d(Progress... progressArr) {
        if (!e()) {
            m.obtainMessage(2, new a(this, progressArr)).sendToTarget();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(Result result) {
        if (e()) {
            b((b<Params, Progress, Result>) result);
        } else {
            a((b<Params, Progress, Result>) result);
        }
        this.q = d.FINISHED;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: AsyncTask.java */
    /* renamed from: com.alimama.mobile.csdk.umupdate.a.b$b  reason: collision with other inner class name */
    /* loaded from: classes.dex */
    public static class HandlerC0005b extends Handler {
        private HandlerC0005b() {
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
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
        final b a;
        final Data[] b;

        a(b bVar, Data... dataArr) {
            this.a = bVar;
            this.b = dataArr;
        }
    }

    public static boolean g() {
        return Build.VERSION.SDK_INT >= 11;
    }
}
