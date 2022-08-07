package com.amap.api.services.a;

import android.content.Context;
import android.text.TextUtils;
import java.lang.Thread;

/* compiled from: DynamicExceptionHandler.java */
/* loaded from: classes.dex */
public class cl implements Thread.UncaughtExceptionHandler {
    private static cl a;
    private Thread.UncaughtExceptionHandler b = Thread.getDefaultUncaughtExceptionHandler();
    private Context c;
    private be d;

    private cl(Context context, be beVar) {
        this.c = context.getApplicationContext();
        this.d = beVar;
        Thread.setDefaultUncaughtExceptionHandler(this);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static synchronized cl a(Context context, be beVar) {
        cl clVar;
        synchronized (cl.class) {
            if (a == null) {
                a = new cl(context, beVar);
            }
            clVar = a;
        }
        return clVar;
    }

    void a(Throwable th) {
        String a2 = bf.a(th);
        try {
            if (TextUtils.isEmpty(a2)) {
                return;
            }
            if ((a2.contains("amapdynamic") || a2.contains("admic")) && a2.contains("com.amap.api")) {
                bq bqVar = new bq(this.c, cm.c());
                if (a2.contains("loc")) {
                    cj.a(bqVar, this.c, "loc");
                }
                if (a2.contains("navi")) {
                    cj.a(bqVar, this.c, "navi");
                }
                if (a2.contains("sea")) {
                    cj.a(bqVar, this.c, "sea");
                }
                if (a2.contains("2dmap")) {
                    cj.a(bqVar, this.c, "2dmap");
                }
                if (a2.contains("3dmap")) {
                    cj.a(bqVar, this.c, "3dmap");
                }
            } else if (a2.contains("com.autonavi.aps.amapapi.offline")) {
                cj.a(new bq(this.c, cm.c()), this.c, "OfflineLocation");
            } else if (a2.contains("com.data.carrier_v4")) {
                cj.a(new bq(this.c, cm.c()), this.c, "Collection");
            } else if (a2.contains("com.autonavi.aps.amapapi.httpdns") || a2.contains("com.autonavi.httpdns")) {
                cj.a(new bq(this.c, cm.c()), this.c, "HttpDNS");
            }
        } catch (Throwable th2) {
            bh.a(th2, "DynamicExceptionHandler", "uncaughtException");
        }
    }

    @Override // java.lang.Thread.UncaughtExceptionHandler
    public void uncaughtException(Thread thread, Throwable th) {
        a(th);
        if (this.b != null) {
            this.b.uncaughtException(thread, th);
        }
    }
}
