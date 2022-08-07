package com.amap.api.services.a;

import android.content.Context;
import java.lang.Thread;

/* compiled from: BasicLogHandler.java */
/* loaded from: classes.dex */
public class bh {
    protected static bh a;
    protected Thread.UncaughtExceptionHandler b;
    protected boolean c = true;

    public static void a(Throwable th, String str, String str2) {
        th.printStackTrace();
        if (a != null) {
            a.a(th, 1, str, str2);
        }
    }

    public void a(Throwable th, int i, String str, String str2) {
    }

    public void a(Context context, be beVar, boolean z) {
    }
}
