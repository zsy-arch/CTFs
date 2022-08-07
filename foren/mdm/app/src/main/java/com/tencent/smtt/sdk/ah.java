package com.tencent.smtt.sdk;

import android.os.HandlerThread;

/* loaded from: classes2.dex */
class ah extends HandlerThread {
    private static ah a;

    public ah(String str) {
        super(str);
    }

    public static synchronized ah a() {
        ah ahVar;
        synchronized (ah.class) {
            if (a == null) {
                a = new ah("TbsHandlerThread");
                a.start();
            }
            ahVar = a;
        }
        return ahVar;
    }
}
