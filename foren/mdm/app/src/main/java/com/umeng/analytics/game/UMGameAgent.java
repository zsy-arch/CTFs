package com.umeng.analytics.game;

import android.content.Context;
import com.umeng.analytics.MobclickAgent;
import com.umeng.analytics.social.UMPlatformData;
import com.umeng.analytics.social.UMSocialService;
import com.umeng.analytics.social.e;
import u.aly.bo;

/* loaded from: classes2.dex */
public class UMGameAgent extends MobclickAgent {
    private static final String a = "Input string is null or empty";
    private static final String b = "Input string must be less than 64 chars";
    private static final String c = "Input value type is negative";
    private static final String d = "The int value for 'Pay Channels' ranges between 1 ~ 99 ";
    private static final c e = new c();
    private static Context f;

    public static void init(Context context) {
        e.a(context);
        f = context.getApplicationContext();
    }

    public static void setTraceSleepTime(boolean z) {
        e.a(z);
    }

    public static void setPlayerLevel(int i) {
        e.a(String.valueOf(i));
    }

    public static void startLevel(String str) {
        if (a(str)) {
            bo.e(a);
        } else if (str.length() > 64) {
            bo.e(b);
        } else {
            e.b(str);
        }
    }

    public static void finishLevel(String str) {
        if (a(str)) {
            bo.e(a);
        } else if (str.length() > 64) {
            bo.e(b);
        } else {
            e.c(str);
        }
    }

    public static void failLevel(String str) {
        if (a(str)) {
            bo.e(a);
        } else if (str.length() > 64) {
            bo.e(b);
        } else {
            e.d(str);
        }
    }

    public static void pay(double d2, double d3, int i) {
        if (i <= 0 || i >= 100) {
            bo.e(d);
        } else if (d2 < 0.0d || d3 < 0.0d) {
            bo.e(c);
        } else {
            e.a(d2, d3, i);
        }
    }

    public static void pay(double d2, String str, int i, double d3, int i2) {
        if (i2 <= 0 || i2 >= 100) {
            bo.e(d);
        } else if (d2 < 0.0d || i < 0 || d3 < 0.0d) {
            bo.e(c);
        } else if (a(str)) {
            bo.e(a);
        } else {
            e.a(d2, str, i, d3, i2);
        }
    }

    public static void exchange(double d2, String str, double d3, int i, String str2) {
        if (d2 < 0.0d || d3 < 0.0d) {
            bo.e(c);
        } else if (i <= 0 || i >= 100) {
            bo.e(d);
        } else {
            e.a(d2, str, d3, i, str2);
        }
    }

    public static void buy(String str, int i, double d2) {
        if (a(str)) {
            bo.e(a);
        } else if (i < 0 || d2 < 0.0d) {
            bo.e(c);
        } else {
            e.a(str, i, d2);
        }
    }

    public static void use(String str, int i, double d2) {
        if (a(str)) {
            bo.e(a);
        } else if (i < 0 || d2 < 0.0d) {
            bo.e(c);
        } else {
            e.b(str, i, d2);
        }
    }

    public static void bonus(double d2, int i) {
        if (d2 < 0.0d) {
            bo.e(c);
        } else if (i <= 0 || i >= 100) {
            bo.e(d);
        } else {
            e.a(d2, i);
        }
    }

    public static void bonus(String str, int i, double d2, int i2) {
        if (a(str)) {
            bo.e(a);
        } else if (i < 0 || d2 < 0.0d) {
            bo.e(c);
        } else if (i2 <= 0 || i2 >= 100) {
            bo.e(d);
        } else {
            e.a(str, i, d2, i2);
        }
    }

    private static boolean a(String str) {
        return str == null || str.trim().length() <= 0;
    }

    public static void onEvent(String str, String str2) {
        onEvent(f, str, str2);
    }

    public static void onSocialEvent(Context context, String str, UMPlatformData... uMPlatformDataArr) {
        if (context == null) {
            bo.e("context is null in onShareEvent");
            return;
        }
        e.e = "4";
        UMSocialService.share(context, str, uMPlatformDataArr);
    }

    public static void onSocialEvent(Context context, UMPlatformData... uMPlatformDataArr) {
        if (context == null) {
            bo.e("context is null in onShareEvent");
            return;
        }
        e.e = "4";
        UMSocialService.share(context, uMPlatformDataArr);
    }
}
