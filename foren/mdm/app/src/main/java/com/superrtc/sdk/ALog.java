package com.superrtc.sdk;

import android.util.Log;

/* loaded from: classes2.dex */
public class ALog {
    private static final String ATAG = "alog";

    private static String buildMessage(String tag, String msg) {
        return "<" + Thread.currentThread().getId() + "> |" + tag + "| " + msg;
    }

    public static void i(String tag, String msg) {
        Log.i(ATAG, buildMessage(tag, msg));
    }

    public static void w(String tag, String msg) {
        Log.w(ATAG, buildMessage(tag, msg));
    }

    public static void e(String tag, String msg) {
        Log.e(ATAG, buildMessage(tag, msg));
    }

    public static void e(String tag, String msg, Throwable e) {
        e.printStackTrace();
        Log.e(ATAG, String.valueOf(buildMessage(tag, String.valueOf(msg) + " ,")) + e.getMessage());
    }
}
