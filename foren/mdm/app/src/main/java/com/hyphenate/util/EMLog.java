package com.hyphenate.util;

import com.hyphenate.chat.adapter.EMAChatConfig;

/* loaded from: classes2.dex */
public class EMLog {
    public static boolean debugMode = false;

    public static void d(String str, String str2) {
        if (str2 != null && debugMode) {
            EMAChatConfig.logD(str, str2);
        }
    }

    public static void e(String str, String str2) {
        if (str2 != null && debugMode) {
            EMAChatConfig.logE(str, str2);
        }
    }

    public static void i(String str, String str2) {
        if (str2 != null && debugMode) {
            EMAChatConfig.logI(str, str2);
        }
    }

    public static void v(String str, String str2) {
        if (str2 != null && debugMode) {
            EMAChatConfig.logV(str, str2);
        }
    }

    public static void w(String str, String str2) {
        if (str2 != null && debugMode) {
            EMAChatConfig.logW(str, str2);
        }
    }
}
