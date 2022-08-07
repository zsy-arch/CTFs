package com.amap.api.col;

import android.content.Context;

/* compiled from: OfflineLocEntity.java */
/* loaded from: classes.dex */
public class ik {
    private Context a;
    private gj b;
    private String c;

    public ik(Context context, gj gjVar, String str) {
        this.a = context.getApplicationContext();
        this.b = gjVar;
        this.c = str;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public byte[] a() {
        return gk.a(a(this.a, this.b, this.c));
    }

    private static String a(Context context, gj gjVar, String str) {
        return gd.b(context, gk.b(gk.a(b(context, gjVar, str))));
    }

    private static String b(Context context, gj gjVar, String str) {
        StringBuilder sb = new StringBuilder();
        try {
            sb.append("\"sdkversion\":\"").append(gjVar.c()).append("\",\"product\":\"").append(gjVar.a()).append("\",\"nt\":\"").append(ge.c(context)).append("\",\"details\":").append(str);
        } catch (Throwable th) {
            th.printStackTrace();
        }
        return sb.toString();
    }
}
