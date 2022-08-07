package com.amap.api.col;

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
/* loaded from: classes.dex */
public class gr extends go implements Thread.UncaughtExceptionHandler {
    private static ExecutorService e;
    private static Set<Integer> f = Collections.synchronizedSet(new HashSet());
    private static final ThreadFactory g = new ThreadFactory() { // from class: com.amap.api.col.gr.2
        private final AtomicInteger a = new AtomicInteger(1);

        @Override // java.util.concurrent.ThreadFactory
        public Thread newThread(Runnable runnable) {
            return new Thread(runnable, "pama#" + this.a.getAndIncrement());
        }
    };
    private Context d;

    public static synchronized gr a(Context context, gj gjVar) throws fz {
        gr grVar;
        synchronized (gr.class) {
            if (gjVar == null) {
                throw new fz("sdk info is null");
            } else if (gjVar.a() == null || "".equals(gjVar.a())) {
                throw new fz("sdk name is invalid");
            } else if (!f.add(Integer.valueOf(gjVar.hashCode()))) {
                grVar = (gr) go.a;
            } else {
                if (go.a == null) {
                    go.a = new gr(context, gjVar);
                } else {
                    go.a.c = false;
                }
                go.a.a(context, gjVar, go.a.c);
                grVar = (gr) go.a;
            }
        }
        return grVar;
    }

    public static synchronized gr a() {
        gr grVar;
        synchronized (gr.class) {
            grVar = (gr) go.a;
        }
        return grVar;
    }

    public static void b(Throwable th, String str, String str2) {
        if (go.a != null) {
            go.a.a(th, 1, str, str2);
        }
    }

    public static void a(gj gjVar, String str, String str2, String str3, String str4) {
        if (go.a != null) {
            StringBuilder sb = new StringBuilder("path:");
            sb.append(str).append(",type:").append(str2).append(",gsid:").append(str3).append(",code:").append(str4);
            go.a.a(gjVar, sb.toString(), "networkError");
        }
    }

    public static void a(gj gjVar, String str, fz fzVar) {
        if (fzVar != null) {
            a(gjVar, str, fzVar.c(), fzVar.d(), fzVar.b());
        }
    }

    public static void b(gj gjVar, String str, String str2) {
        if (go.a != null) {
            go.a.a(gjVar, str, str2);
        }
    }

    public static synchronized void b() {
        synchronized (gr.class) {
            if (e != null) {
                e.shutdown();
            }
            hw.a();
            if (!(go.a == null || Thread.getDefaultUncaughtExceptionHandler() != go.a || go.a.b == null)) {
                Thread.setDefaultUncaughtExceptionHandler(go.a.b);
            }
            go.a = null;
        }
    }

    @Override // java.lang.Thread.UncaughtExceptionHandler
    public void uncaughtException(Thread thread, Throwable th) {
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

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.amap.api.col.go
    public void a(gj gjVar, String str, String str2) {
        gp.a(this.d, gjVar, str, str2);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.amap.api.col.go
    public void a(Throwable th, int i, String str, String str2) {
        gp.a(this.d, th, i, str, str2);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.amap.api.col.go
    public void a(final Context context, final gj gjVar, final boolean z) {
        try {
            ExecutorService c = c();
            if (c != null && !c.isShutdown()) {
                c.submit(new Runnable() { // from class: com.amap.api.col.gr.1
                    @Override // java.lang.Runnable
                    public void run() {
                        try {
                            synchronized (Looper.getMainLooper()) {
                                new hh(context, true).a(gjVar);
                            }
                            if (z) {
                                synchronized (Looper.getMainLooper()) {
                                    hi hiVar = new hi(context);
                                    hj hjVar = new hj();
                                    hjVar.c(true);
                                    hjVar.a(true);
                                    hjVar.b(true);
                                    hiVar.a(hjVar);
                                }
                                gp.a(gr.this.d);
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

    private gr(Context context, gj gjVar) {
        this.d = context;
        id.a(new a(context));
        d();
    }

    private void d() {
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

    public void c(Throwable th, String str, String str2) {
        if (th != null) {
            try {
                a(th, 1, str, str2);
            } catch (Throwable th2) {
                th2.printStackTrace();
            }
        }
    }

    public static synchronized ExecutorService c() {
        ExecutorService executorService;
        synchronized (gr.class) {
            if (e == null || e.isShutdown()) {
                e = Executors.newSingleThreadExecutor(g);
            }
            executorService = e;
        }
        return executorService;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: SDKLogHandler.java */
    /* loaded from: classes.dex */
    public static class a implements ie {
        private Context a;

        a(Context context) {
            this.a = context;
        }

        @Override // com.amap.api.col.ie
        public void a() {
            try {
                gp.b(this.a);
            } catch (Throwable th) {
                go.a(th, "LogNetListener", "onNetCompleted");
            }
        }
    }
}
