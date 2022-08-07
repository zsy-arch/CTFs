package com.amap.api.col;

import android.content.Context;
import android.text.TextUtils;
import java.lang.Thread;

/* compiled from: DynamicExceptionHandler.java */
/* loaded from: classes.dex */
public class hq implements Thread.UncaughtExceptionHandler {
    private static hq a;
    private Thread.UncaughtExceptionHandler b = Thread.getDefaultUncaughtExceptionHandler();
    private Context c;
    private gj d;

    private hq(Context context, gj gjVar) {
        this.c = context.getApplicationContext();
        this.d = gjVar;
        Thread.setDefaultUncaughtExceptionHandler(this);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static synchronized hq a(Context context, gj gjVar) {
        hq hqVar;
        synchronized (hq.class) {
            if (a == null) {
                a = new hq(context, gjVar);
            }
            hqVar = a;
        }
        return hqVar;
    }

    void a(Throwable th) {
        String a2 = gk.a(th);
        try {
            if (TextUtils.isEmpty(a2)) {
                return;
            }
            if ((a2.contains("amapdynamic") || a2.contains("admic")) && a2.contains("com.amap.api")) {
                gx gxVar = new gx(this.c, hr.a());
                if (a2.contains("loc")) {
                    hp.a(gxVar, this.c, "loc");
                }
                if (a2.contains("navi")) {
                    hp.a(gxVar, this.c, "navi");
                }
                if (a2.contains("sea")) {
                    hp.a(gxVar, this.c, "sea");
                }
                if (a2.contains("2dmap")) {
                    hp.a(gxVar, this.c, "2dmap");
                }
                if (a2.contains("3dmap")) {
                    hp.a(gxVar, this.c, "3dmap");
                }
            } else if (a2.contains("com.autonavi.aps.amapapi.offline")) {
                hp.a(new gx(this.c, hr.a()), this.c, "OfflineLocation");
            } else if (a2.contains("com.data.carrier_v4")) {
                hp.a(new gx(this.c, hr.a()), this.c, "Collection");
            } else if (a2.contains("com.autonavi.aps.amapapi.httpdns") || a2.contains("com.autonavi.httpdns")) {
                hp.a(new gx(this.c, hr.a()), this.c, "HttpDNS");
            }
        } catch (Throwable th2) {
            go.a(th2, "DynamicExceptionHandler", "uncaughtException");
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
