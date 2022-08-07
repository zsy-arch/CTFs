package com.amap.api.services.a;

/* compiled from: ThreadTask.java */
/* loaded from: classes.dex */
public abstract class di implements Runnable {
    a d;

    /* compiled from: ThreadTask.java */
    /* loaded from: classes.dex */
    interface a {
        void a(di diVar);

        void b(di diVar);
    }

    public abstract void a();

    @Override // java.lang.Runnable
    public final void run() {
        try {
            if (this.d != null) {
                this.d.a(this);
            }
            if (!Thread.interrupted()) {
                a();
                if (!Thread.interrupted() && this.d != null) {
                    this.d.b(this);
                }
            }
        } catch (Throwable th) {
            bk.b(th, "ThreadTask", "run");
            th.printStackTrace();
        }
    }
}
