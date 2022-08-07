package com.amap.api.col;

import android.content.Context;
import java.lang.Thread;

/* compiled from: BasicLogHandler.java */
/* loaded from: classes.dex */
public class go {
    protected static go a;
    protected Thread.UncaughtExceptionHandler b;
    protected boolean c = true;

    public static void a(Throwable th, String str, String str2) {
        th.printStackTrace();
        if (a != null) {
            a.a(th, 1, str, str2);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void a(Throwable th, int i, String str, String str2) {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void a(Context context, gj gjVar, boolean z) {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void a(gj gjVar, String str, String str2) {
    }
}
