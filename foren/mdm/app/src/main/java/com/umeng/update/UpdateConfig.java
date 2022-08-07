package com.umeng.update;

import android.content.Context;
import u.upd.a;
import u.upd.b;

/* loaded from: classes2.dex */
public class UpdateConfig {
    public static final String a = "update";
    public static final String b = "2.6.0.1.20150312";
    public static final String c = "1.4";
    public static final String d = "com.umeng.update.net.DownloadingService";
    public static final String e = "com.umeng.update.UpdateDialogActivity";
    public static final String f = "android.permission.WRITE_EXTERNAL_STORAGE";
    public static final String g = "android.permission.ACCESS_NETWORK_STATE";
    public static final String h = "android.permission.INTERNET";
    public static final String i = "UMUpdateCheck";
    private static final String j = "umeng_update";
    private static final String k = "ignore";
    private static String l;
    private static String m;
    private static String n;
    private static boolean o = true;
    private static boolean p = true;
    private static boolean q = false;
    private static boolean r = true;
    private static boolean s = false;
    private static boolean t = true;

    /* renamed from: u */
    private static boolean f52u = true;
    private static int v = 0;

    public static void setAppkey(String str) {
        l = str;
    }

    public static void setChannel(String str) {
        m = str;
    }

    public static void setDebug(boolean z) {
        b.a = z;
    }

    public static String getAppkey(Context context) {
        if (l == null) {
            l = a.o(context);
        }
        return l;
    }

    public static String getChannel(Context context) {
        if (m == null) {
            m = a.t(context);
        }
        return m;
    }

    public static void saveIgnoreMd5(Context context, String str) {
        context.getApplicationContext().getSharedPreferences(j, 0).edit().putString(k, str).commit();
    }

    public static String getIgnoreMd5(Context context) {
        String string = context.getApplicationContext().getSharedPreferences(j, 0).getString(k, "");
        if ("".equals(string)) {
            return null;
        }
        return string;
    }

    public static boolean isUpdateOnlyWifi() {
        return o;
    }

    public static void setUpdateOnlyWifi(boolean z) {
        o = z;
    }

    public static boolean isUpdateAutoPopup() {
        return p;
    }

    public static void setUpdateAutoPopup(boolean z) {
        p = z;
    }

    public static boolean isUpdateForce() {
        return q;
    }

    public static void setUpdateForce(boolean z) {
        q = z;
    }

    public static boolean isDeltaUpdate() {
        return r;
    }

    public static void setDeltaUpdate(boolean z) {
        r = z;
    }

    public static boolean isSilentDownload() {
        return s;
    }

    public static void setSilentDownload(boolean z) {
        s = z;
    }

    public static int getStyle() {
        return v;
    }

    public static void setStyle(int i2) {
        v = i2;
    }

    public static boolean isUpdateCheck() {
        return t;
    }

    public static void setUpdateCheck(boolean z) {
        t = z;
    }

    public static boolean isRichNotification() {
        return f52u;
    }

    public static void setRichNotification(boolean z) {
        f52u = z;
    }

    public static String getSlotId() {
        return n;
    }

    public static void setSlotId(String str) {
        n = str;
    }
}
