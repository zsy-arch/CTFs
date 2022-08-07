package com.tencent.smtt.sdk;

import android.os.HandlerThread;

/* loaded from: classes.dex */
public class l extends HandlerThread {

    /* renamed from: a  reason: collision with root package name */
    public static l f1370a;

    public l(String str) {
        super(str);
    }

    public static synchronized l a() {
        l lVar;
        synchronized (l.class) {
            if (f1370a == null) {
                f1370a = new l("TbsHandlerThread");
                f1370a.start();
            }
            lVar = f1370a;
        }
        return lVar;
    }
}
