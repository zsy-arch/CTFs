package com.alipay.sdk.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.telephony.TelephonyManager;
import android.text.TextUtils;

/* loaded from: classes.dex */
public final class a {
    private static final String b = "00:00:00:00:00:00";
    private static a e = null;
    public String a;
    private String c;
    private String d;

    public static a a(Context context) {
        if (e == null) {
            e = new a(context);
        }
        return e;
    }

    private a(Context context) {
        try {
            try {
                TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
                b(telephonyManager.getDeviceId());
                String subscriberId = telephonyManager.getSubscriberId();
                this.c = subscriberId != null ? (subscriberId + "000000000000000").substring(0, 15) : subscriberId;
                this.a = ((WifiManager) context.getSystemService("wifi")).getConnectionInfo().getMacAddress();
                if (TextUtils.isEmpty(this.a)) {
                    this.a = b;
                }
            } catch (Exception e2) {
                e2.printStackTrace();
                if (TextUtils.isEmpty(this.a)) {
                    this.a = b;
                }
            }
        } catch (Throwable th) {
            if (TextUtils.isEmpty(this.a)) {
                this.a = b;
            }
            throw th;
        }
    }

    public final String a() {
        if (TextUtils.isEmpty(this.c)) {
            this.c = "000000000000000";
        }
        return this.c;
    }

    public final String b() {
        if (TextUtils.isEmpty(this.d)) {
            this.d = "000000000000000";
        }
        return this.d;
    }

    private void a(String str) {
        if (str != null) {
            str = (str + "000000000000000").substring(0, 15);
        }
        this.c = str;
    }

    private void b(String str) {
        if (str != null) {
            byte[] bytes = str.getBytes();
            for (int i = 0; i < bytes.length; i++) {
                if (bytes[i] < 48 || bytes[i] > 57) {
                    bytes[i] = 48;
                }
            }
            str = (new String(bytes) + "000000000000000").substring(0, 15);
        }
        this.d = str;
    }

    private String c() {
        String str = b() + "|";
        String a = a();
        if (TextUtils.isEmpty(a)) {
            return str + "000000000000000";
        }
        return str + a;
    }

    private String d() {
        return this.a;
    }

    public static d b(Context context) {
        d dVar;
        try {
            NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
            if (activeNetworkInfo != null && activeNetworkInfo.getType() == 0) {
                dVar = d.a(activeNetworkInfo.getSubtype());
            } else if (activeNetworkInfo == null || activeNetworkInfo.getType() != 1) {
                dVar = d.NONE;
            } else {
                dVar = d.WIFI;
            }
            return dVar;
        } catch (Exception e2) {
            return d.NONE;
        }
    }

    public static String c(Context context) {
        a a = a(context);
        String str = a.b() + "|";
        String a2 = a.a();
        return (TextUtils.isEmpty(a2) ? str + "000000000000000" : str + a2).substring(0, 8);
    }

    public static String d(Context context) {
        if (context == null) {
            return "";
        }
        try {
            return context.getResources().getConfiguration().locale.toString();
        } catch (Throwable th) {
            return "";
        }
    }
}
