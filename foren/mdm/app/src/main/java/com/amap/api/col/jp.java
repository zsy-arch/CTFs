package com.amap.api.col;

import android.content.Context;

/* compiled from: SPUtil.java */
/* loaded from: classes.dex */
public final class jp {
    public static int a(Context context, String str, String str2) {
        try {
            return context.getSharedPreferences(str, 0).getInt(str2, 200);
        } catch (Throwable th) {
            jn.a(th, "SPUtil", "getPrefsInt");
            return 200;
        }
    }

    public static boolean b(Context context, String str, String str2) {
        try {
            return context.getSharedPreferences(str, 0).getBoolean(str2, true);
        } catch (Throwable th) {
            jn.a(th, "SPUtil", "getPrefsBoolean");
            return true;
        }
    }
}
