package com.amap.api.col;

/* compiled from: ThreadTask.java */
/* loaded from: classes.dex */
public abstract class iq implements Runnable {
    a q;

    /* compiled from: ThreadTask.java */
    /* loaded from: classes.dex */
    interface a {
        void a(iq iqVar);

        void b(iq iqVar);

        void c(iq iqVar);
    }

    public abstract void a();

    @Override // java.lang.Runnable
    public final void run() {
        try {
            if (this.q != null) {
                this.q.a(this);
            }
            if (!Thread.interrupted()) {
                a();
                if (!Thread.interrupted() && this.q != null) {
                    this.q.b(this);
                }
            }
        } catch (Throwable th) {
            gr.b(th, "ThreadTask", "run");
            th.printStackTrace();
        }
    }

    public final void e() {
        try {
            if (this.q != null) {
                this.q.c(this);
            }
        } catch (Throwable th) {
            gr.b(th, "ThreadTask", "cancelTask");
            th.printStackTrace();
        }
    }
}
