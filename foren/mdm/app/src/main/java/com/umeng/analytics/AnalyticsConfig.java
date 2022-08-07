package com.umeng.analytics;

import android.content.Context;
import android.text.TextUtils;
import u.aly.bl;
import u.aly.bo;
import u.aly.bs;

/* loaded from: classes2.dex */
public class AnalyticsConfig {
    public static final boolean FLAG_DPLUS = false;
    public static final boolean FLAG_INTERNATIONAL = false;
    public static boolean sEncrypt;
    public static int sLatentWindow;
    private static String b = null;
    private static String c = null;
    private static String d = null;
    public static String mWrapperType = null;
    public static String mWrapperVersion = null;
    private static int e = 0;
    public static String GPU_VENDER = "";
    public static String GPU_RENDERER = "";
    public static boolean ACTIVITY_DURATION_OPEN = true;
    public static boolean CATCH_EXCEPTION = true;
    public static long kContinueSessionMillis = 30000;
    static double[] a = null;

    static {
        sEncrypt = false;
        sEncrypt = false;
    }

    public static void a(boolean z) {
        sEncrypt = z;
    }

    public static void a(Context context, String str) {
        if (context == null) {
            b = str;
            return;
        }
        String p = bl.p(context);
        if (!TextUtils.isEmpty(p)) {
            b = p;
            if (!p.equals(str)) {
                bo.d("Appkey和AndroidManifest.xml中配置的不一致 ");
                return;
            }
            return;
        }
        String c2 = bs.a(context).c();
        if (TextUtils.isEmpty(c2)) {
            bs.a(context).a(str);
        } else if (!c2.equals(str)) {
            bo.d("Appkey和上次配置的不一致 ");
            bs.a(context).a(str);
        }
        b = str;
    }

    public static void a(String str) {
        c = str;
    }

    public static String getAppkey(Context context) {
        if (TextUtils.isEmpty(b)) {
            b = bl.p(context);
            if (TextUtils.isEmpty(b)) {
                b = bs.a(context).c();
            }
        }
        return b;
    }

    public static String getChannel(Context context) {
        if (TextUtils.isEmpty(c)) {
            c = bl.s(context);
        }
        return c;
    }

    public static double[] getLocation() {
        return a;
    }

    public static void b(Context context, String str) {
        if (!TextUtils.isEmpty(str)) {
            d = str;
            bs.a(context).c(d);
        }
    }

    public static String getSecretKey(Context context) {
        if (TextUtils.isEmpty(d)) {
            d = bs.a(context).e();
        }
        return d;
    }

    public static void a(Context context, int i) {
        e = i;
        bs.a(context).a(e);
    }

    public static int getVerticalType(Context context) {
        if (e == 0) {
            e = bs.a(context).f();
        }
        return e;
    }

    public static String getSDKVersion(Context context) {
        return a.c;
    }
}
