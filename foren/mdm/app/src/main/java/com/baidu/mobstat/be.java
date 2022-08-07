package com.baidu.mobstat;

import android.content.Context;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class be extends bf {
    static be a = new be();

    private be() {
    }

    public static be a() {
        return a;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public int a(Context context) {
        return getInt(context, "sendLogtype", 0);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void a(Context context, int i) {
        putInt(context, "sendLogtype", i);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void a(Context context, long j) {
        putLong(context, "lastsendtime", j);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void a(Context context, String str) {
        putString(context, "device_id_1", str);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void a(Context context, boolean z) {
        putBoolean(context, "exceptionanalysisflag", z);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public int b(Context context) {
        return getInt(context, "timeinterval", 1);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void b(Context context, int i) {
        putInt(context, "timeinterval", i);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void b(Context context, String str) {
        if (getString(context, "cuid", null) != null) {
            removeString(context, "cuid");
        }
        putString(context, "cuidsec_1", str);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void b(Context context, boolean z) {
        putBoolean(context, "onlywifi", z);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void c(Context context, String str) {
        putString(context, "setchannelwithcodevalue", str);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void c(Context context, boolean z) {
        putBoolean(context, "setchannelwithcode", z);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean c(Context context) {
        return getBoolean(context, "onlywifi", false);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public String d(Context context) {
        return getString(context, "device_id_1", null);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void d(Context context, String str) {
        putString(context, "mtjsdkmacss_1", str);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void d(Context context, boolean z) {
        putBoolean(context, "mtjtv", z);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public String e(Context context) {
        return getString(context, "cuidsec_1", null);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void e(Context context, String str) {
        putString(context, "mtjsdkmacsstv_1", str);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public String f(Context context) {
        return getString(context, "setchannelwithcodevalue", null);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean g(Context context) {
        return getBoolean(context, "setchannelwithcode", false);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public String h(Context context) {
        return getString(context, "mtjsdkmacss_1", null);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean i(Context context) {
        return getBoolean(context, "mtjtv", false);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public String j(Context context) {
        return getString(context, "mtjsdkmacsstv_1", null);
    }
}
