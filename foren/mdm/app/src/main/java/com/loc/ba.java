package com.loc;

import android.content.Context;
import android.text.TextUtils;
import java.lang.Thread;

/* compiled from: DynamicExceptionHandler.java */
/* loaded from: classes2.dex */
public final class ba implements Thread.UncaughtExceptionHandler {
    private static ba a;
    private Thread.UncaughtExceptionHandler b = Thread.getDefaultUncaughtExceptionHandler();
    private Context c;
    private s d;

    private ba(Context context, s sVar) {
        this.c = context.getApplicationContext();
        this.d = sVar;
        Thread.setDefaultUncaughtExceptionHandler(this);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static synchronized ba a(Context context, s sVar) {
        ba baVar;
        synchronized (ba.class) {
            if (a == null) {
                a = new ba(context, sVar);
            }
            baVar = a;
        }
        return baVar;
    }

    @Override // java.lang.Thread.UncaughtExceptionHandler
    public final void uncaughtException(Thread thread, Throwable th) {
        String a2 = t.a(th);
        try {
            if (!TextUtils.isEmpty(a2)) {
                if ((a2.contains("amapdynamic") || a2.contains("admic")) && a2.contains("com.amap.api")) {
                    af afVar = new af(this.c, bb.b());
                    if (a2.contains("loc")) {
                        ay.a(afVar, this.c, "loc");
                    }
                    if (a2.contains("navi")) {
                        ay.a(afVar, this.c, "navi");
                    }
                    if (a2.contains("sea")) {
                        ay.a(afVar, this.c, "sea");
                    }
                    if (a2.contains("2dmap")) {
                        ay.a(afVar, this.c, "2dmap");
                    }
                    if (a2.contains("3dmap")) {
                        ay.a(afVar, this.c, "3dmap");
                    }
                } else if (a2.contains("com.autonavi.aps.amapapi.offline")) {
                    ay.a(new af(this.c, bb.b()), this.c, "OfflineLocation");
                } else if (a2.contains("com.data.carrier_v4")) {
                    ay.a(new af(this.c, bb.b()), this.c, "Collection");
                } else if (a2.contains("com.autonavi.aps.amapapi.httpdns") || a2.contains("com.autonavi.httpdns")) {
                    ay.a(new af(this.c, bb.b()), this.c, "HttpDNS");
                }
            }
        } catch (Throwable th2) {
            w.a(th2, "DynamicExceptionHandler", "uncaughtException");
        }
        if (this.b != null) {
            this.b.uncaughtException(thread, th);
        }
    }
}
