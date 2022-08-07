package com.loc;

import android.content.Context;
import android.os.Looper;
import java.lang.Thread;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/* compiled from: SDKLogHandler.java */
/* loaded from: classes2.dex */
public final class z extends w implements Thread.UncaughtExceptionHandler {
    private static ExecutorService e;
    private static Set<Integer> f = Collections.synchronizedSet(new HashSet());
    private static final ThreadFactory g = new ThreadFactory() { // from class: com.loc.z.2
        private final AtomicInteger a = new AtomicInteger(1);

        @Override // java.util.concurrent.ThreadFactory
        public final Thread newThread(Runnable runnable) {
            return new Thread(runnable, "pama#" + this.a.getAndIncrement());
        }
    };
    private Context d;

    /* compiled from: SDKLogHandler.java */
    /* loaded from: classes2.dex */
    private static class a implements bm {
        private Context a;

        a(Context context) {
            this.a = context;
        }

        @Override // com.loc.bm
        public final void a() {
            try {
                x.b(this.a);
            } catch (Throwable th) {
                w.a(th, "LogNetListener", "onNetCompleted");
            }
        }
    }

    private z(Context context) {
        this.d = context;
        bl.a(new a(context));
        try {
            this.b = Thread.getDefaultUncaughtExceptionHandler();
            if (this.b == null) {
                Thread.setDefaultUncaughtExceptionHandler(this);
                this.c = true;
            } else {
                String obj = this.b.toString();
                if (obj.indexOf("com.amap.api") == -1 && obj.indexOf("com.loc") == -1) {
                    Thread.setDefaultUncaughtExceptionHandler(this);
                    this.c = true;
                } else {
                    this.c = false;
                }
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public static synchronized z a(Context context, s sVar) throws j {
        z zVar;
        synchronized (z.class) {
            if (sVar == null) {
                throw new j("sdk info is null");
            } else if (sVar.a() == null || "".equals(sVar.a())) {
                throw new j("sdk name is invalid");
            } else if (!f.add(Integer.valueOf(sVar.hashCode()))) {
                zVar = (z) w.a;
            } else {
                if (w.a == null) {
                    w.a = new z(context);
                } else {
                    w.a.c = false;
                }
                w.a.a(context, sVar, w.a.c);
                zVar = (z) w.a;
            }
        }
        return zVar;
    }

    public static synchronized void a() {
        synchronized (z.class) {
            if (e != null) {
                e.shutdown();
            }
            be.a();
            if (!(w.a == null || Thread.getDefaultUncaughtExceptionHandler() != w.a || w.a.b == null)) {
                Thread.setDefaultUncaughtExceptionHandler(w.a.b);
            }
            w.a = null;
        }
    }

    public static synchronized ExecutorService b() {
        ExecutorService executorService;
        synchronized (z.class) {
            if (e == null || e.isShutdown()) {
                e = Executors.newSingleThreadExecutor(g);
            }
            executorService = e;
        }
        return executorService;
    }

    public static void b(s sVar, String str, String str2) {
        if (w.a != null) {
            w.a.a(sVar, str, str2);
        }
    }

    public static void b(Throwable th, String str, String str2) {
        if (w.a != null) {
            w.a.a(th, 1, str, str2);
        }
    }

    @Override // com.loc.w
    protected final void a(final Context context, final s sVar, final boolean z) {
        try {
            ExecutorService b = b();
            if (b != null && !b.isShutdown()) {
                b.submit(new Runnable() { // from class: com.loc.z.1
                    @Override // java.lang.Runnable
                    public final void run() {
                        try {
                            synchronized (Looper.getMainLooper()) {
                                new ap(context, true).a(sVar);
                            }
                            if (z) {
                                synchronized (Looper.getMainLooper()) {
                                    aq aqVar = new aq(context);
                                    ar arVar = new ar();
                                    arVar.c(true);
                                    arVar.a(true);
                                    arVar.b(true);
                                    aqVar.a(arVar);
                                }
                                x.a(z.this.d);
                            }
                        } catch (Throwable th) {
                            th.printStackTrace();
                        }
                    }
                });
            }
        } catch (RejectedExecutionException e2) {
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    @Override // com.loc.w
    protected final void a(s sVar, String str, String str2) {
        x.a(this.d, sVar, str, str2);
    }

    @Override // com.loc.w
    protected final void a(Throwable th, int i, String str, String str2) {
        x.a(this.d, th, i, str, str2);
    }

    @Override // java.lang.Thread.UncaughtExceptionHandler
    public final void uncaughtException(Thread thread, Throwable th) {
        if (th != null) {
            a(th, 0, null, null);
            if (this.b != null) {
                try {
                    Thread.setDefaultUncaughtExceptionHandler(this.b);
                } catch (Throwable th2) {
                }
                this.b.uncaughtException(thread, th);
            }
        }
    }
}
