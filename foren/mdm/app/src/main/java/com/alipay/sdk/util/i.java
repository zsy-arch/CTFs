package com.alipay.sdk.util;

import android.content.Context;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import com.alipay.sdk.app.statistic.a;
import com.alipay.sdk.app.statistic.c;
import com.alipay.sdk.encrypt.e;

/* loaded from: classes.dex */
public final class i {
    private static String a = null;

    private static boolean b(Context context, String str) {
        try {
            return PreferenceManager.getDefaultSharedPreferences(context).contains(str);
        } catch (Throwable th) {
            return false;
        }
    }

    public static void a(Context context, String str) {
        try {
            PreferenceManager.getDefaultSharedPreferences(context).edit().remove(str).commit();
        } catch (Throwable th) {
        }
    }

    public static void a(Context context, String str, String str2) {
        try {
            String a2 = e.a(a(context), str2);
            if (!TextUtils.isEmpty(str2) && TextUtils.isEmpty(a2)) {
                a.a(c.c, c.w, String.format("%s,%s", str, str2));
            }
            PreferenceManager.getDefaultSharedPreferences(context).edit().putString(str, a2).commit();
        } catch (Throwable th) {
        }
    }

    public static String b(Context context, String str, String str2) {
        String str3 = null;
        try {
            String string = PreferenceManager.getDefaultSharedPreferences(context).getString(str, str2);
            if (!TextUtils.isEmpty(string)) {
                str3 = e.b(a(context), string);
            }
            if (!TextUtils.isEmpty(string) && TextUtils.isEmpty(str3)) {
                a.a(c.c, c.v, String.format("%s,%s", str, string));
            }
        } catch (Exception e) {
        }
        return str3;
    }

    private static String a(Context context) {
        if (TextUtils.isEmpty(a)) {
            String str = "";
            try {
                str = context.getApplicationContext().getPackageName();
            } catch (Throwable th) {
            }
            a = (str + "0000000000000000000000000000").substring(0, 24);
        }
        return a;
    }
}
