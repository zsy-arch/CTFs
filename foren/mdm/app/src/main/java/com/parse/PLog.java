package com.parse;

import android.util.Log;

/* loaded from: classes2.dex */
class PLog {
    public static final int LOG_LEVEL_NONE = Integer.MAX_VALUE;
    private static int logLevel = Integer.MAX_VALUE;

    PLog() {
    }

    public static void setLogLevel(int logLevel2) {
        logLevel = logLevel2;
    }

    public static int getLogLevel() {
        return logLevel;
    }

    private static void log(int messageLogLevel, String tag, String message, Throwable tr) {
        if (messageLogLevel < logLevel) {
            return;
        }
        if (tr == null) {
            Log.println(logLevel, tag, message);
        } else {
            Log.println(logLevel, tag, message + '\n' + Log.getStackTraceString(tr));
        }
    }

    static void v(String tag, String message, Throwable tr) {
        log(2, tag, message, tr);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void v(String tag, String message) {
        v(tag, message, null);
    }

    static void d(String tag, String message, Throwable tr) {
        log(3, tag, message, tr);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void d(String tag, String message) {
        d(tag, message, null);
    }

    static void i(String tag, String message, Throwable tr) {
        log(4, tag, message, tr);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void i(String tag, String message) {
        i(tag, message, null);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void w(String tag, String message, Throwable tr) {
        log(5, tag, message, tr);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void w(String tag, String message) {
        w(tag, message, null);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void e(String tag, String message, Throwable tr) {
        log(6, tag, message, tr);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void e(String tag, String message) {
        e(tag, message, null);
    }
}
