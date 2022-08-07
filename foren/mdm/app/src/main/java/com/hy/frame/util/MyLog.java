package com.hy.frame.util;

import android.util.Log;

/* loaded from: classes.dex */
public class MyLog {
    public static final String TAG = "HyLog";
    public static boolean isLoggable = true;

    public static void i(Object msg) {
        i("", msg);
    }

    public static void i(Class<?> cls, Object msg) {
        i(cls.getSimpleName(), msg);
    }

    public static void i(String tag, Object msg) {
        println(4, tag, msg, null);
    }

    public static void d(Object msg) {
        d("", msg);
    }

    public static void d(Class<?> cls, Object msg) {
        d(cls.getSimpleName(), msg);
    }

    public static void d(String tag, Object msg) {
        println(3, tag, msg, null);
    }

    public static void w(Object msg) {
        w("", msg);
    }

    public static void w(Class<?> cls, Object msg) {
        w(cls.getSimpleName(), msg);
    }

    public static void w(String tag, Object msg) {
        println(5, tag, msg, null);
    }

    public static void e(Object msg) {
        e("", msg);
    }

    public static void e(Class<?> cls, Object msg) {
        e(cls.getSimpleName(), msg);
    }

    public static void e(String tag, Object msg) {
        println(6, tag, msg, null);
    }

    public static void et(String tag, Object msg) {
        println(6, tag, msg, null);
    }

    private static void println(int priority, String tag, Object msg, Throwable tr) {
        if (isLoggable) {
            Log.println(priority, TAG, tag + ": " + msg);
        }
    }
}
