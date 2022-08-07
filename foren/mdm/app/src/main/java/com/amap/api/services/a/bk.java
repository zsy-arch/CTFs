package com.amap.api.services.a;

import android.content.Context;
import android.os.Looper;
import java.lang.Thread;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.RejectedExecutionException;

/* compiled from: SDKLogHandler.java */
/* loaded from: classes.dex */
public class bk extends bh implements Thread.UncaughtExceptionHandler {
    private static ExecutorService e;
    private Context d;

    public static synchronized bk a(Context context, be beVar) throws av {
        bk bkVar;
        synchronized (bk.class) {
            if (beVar == null) {
                throw new av("sdk info is null");
            } else if (beVar.a() == null || "".equals(beVar.a())) {
                throw new av("sdk name is invalid");
            } else {
                if (bh.a == null) {
                    bh.a = new bk(context, beVar);
                } else {
                    bh.a.c = false;
                }
                bh.a.a(context, beVar, bh.a.c);
                bkVar = (bk) bh.a;
            }
        }
        return bkVar;
    }

    public static synchronized bk a() {
        bk bkVar;
        synchronized (bk.class) {
            bkVar = (bk) bh.a;
        }
        return bkVar;
    }

    public static void b(Throwable th, String str, String str2) {
        if (bh.a != null) {
            bh.a.a(th, 1, str, str2);
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
    @Override // com.amap.api.services.a.bh
    public void a(Throwable th, int i, String str, String str2) {
        bi.a(this.d, th, i, str, str2);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.amap.api.services.a.bh
    public void a(final Context context, final be beVar, final boolean z) {
        try {
            ExecutorService b = b();
            if (b != null && !b.isShutdown()) {
                b.submit(new Runnable() { // from class: com.amap.api.services.a.bk.1
                    @Override // java.lang.Runnable
                    public void run() {
                        try {
                            synchronized (Looper.getMainLooper()) {
                                new ca(context, true).a(beVar);
                            }
                            if (z) {
                                synchronized (Looper.getMainLooper()) {
                                    cb cbVar = new cb(context);
                                    cc ccVar = new cc();
                                    ccVar.c(true);
                                    ccVar.a(true);
                                    ccVar.b(true);
                                    cbVar.a(ccVar);
                                }
                                bi.a(bk.this.d);
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

    private bk(Context context, be beVar) {
        this.d = context;
        cw.a(new a(context));
        c();
    }

    private void c() {
        try {
            this.b = Thread.getDefaultUncaughtExceptionHandler();
            if (this.b == null) {
                Thread.setDefaultUncaughtExceptionHandler(this);
                this.c = true;
            } else {
                String obj = this.b.toString();
                if (obj.indexOf("com.amap.api") == -1 && obj.indexOf("com.amap.loc") == -1) {
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

    public static synchronized ExecutorService b() {
        ExecutorService executorService;
        synchronized (bk.class) {
            if (e == null || e.isShutdown()) {
                e = Executors.newSingleThreadExecutor();
            }
            executorService = e;
        }
        return executorService;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: SDKLogHandler.java */
    /* loaded from: classes.dex */
    public static class a implements cx {
        private Context a;

        a(Context context) {
            this.a = context;
        }

        @Override // com.amap.api.services.a.cx
        public void a() {
            try {
                bi.b(this.a);
            } catch (Throwable th) {
                bh.a(th, "LogNetListener", "onNetCompleted");
            }
        }
    }
}
