package com.baidu.mobstat;

import android.content.Context;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;

/* loaded from: classes.dex */
public class bp {
    private static final bp a = new bp();
    private Context b;
    private volatile boolean c = false;
    private volatile boolean d = false;
    private volatile boolean e = false;
    private HandlerThread f;
    private Handler g;

    private bp() {
    }

    public static bp a() {
        return a;
    }

    private synchronized void d() {
        br brVar = new br(this, null);
        brVar.setPriority(10);
        brVar.start();
    }

    public void a(Context context) {
        if (!this.e && context != null) {
            if (this.f == null || !this.f.isAlive()) {
                this.f = new HandlerThread("dataAnalyzeThread");
                this.f.start();
                Looper looper = this.f.getLooper();
                if (looper != null) {
                    this.g = new Handler(looper);
                }
            }
            if (this.g != null) {
                this.g.post(new bq(this, context));
                this.e = true;
            }
        }
    }

    public void b(Context context) {
        if (context != null && !this.c) {
            this.b = context.getApplicationContext();
            d();
            this.c = true;
        }
    }

    public synchronized boolean b() {
        return this.c;
    }

    public void c() {
        if (!this.d) {
            synchronized (this) {
                while (!this.d) {
                    try {
                        wait(50L);
                    } catch (InterruptedException e) {
                        cr.b(e.getMessage());
                    }
                }
            }
        }
    }

    public void c(Context context) {
        if (!this.d) {
            bs.a().a(context);
            DataCore.instance().loadStatData(context);
            DataCore.instance().loadLastSession(context);
            DataCore.instance().installHeader(context);
            this.d = true;
        }
    }
}
