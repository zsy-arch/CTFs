package com.loc;

import android.content.ContentResolver;
import android.content.Context;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.text.TextUtils;
import java.util.List;

/* compiled from: WifiManagerWrapper.java */
/* loaded from: classes2.dex */
public final class cd {
    private WifiManager c;
    private Context d;
    long a = 0;
    String b = "isScanAlwaysAvailable";
    private String e = null;

    public cd(Context context, WifiManager wifiManager) {
        this.c = wifiManager;
        this.d = context;
    }

    public static boolean a(WifiInfo wifiInfo) {
        return wifiInfo != null && !TextUtils.isEmpty(wifiInfo.getSSID()) && cx.b(wifiInfo.getBSSID());
    }

    public final List<ScanResult> a() {
        if (this.c != null) {
            try {
                List<ScanResult> scanResults = this.c.getScanResults();
                this.e = null;
                return scanResults;
            } catch (SecurityException e) {
                this.e = e.getMessage();
            } catch (Throwable th) {
                this.e = null;
                f.a(th, "WifiManagerWrapper", "getScanResults");
            }
        }
        return null;
    }

    public final void a(boolean z) {
        Context context = this.d;
        if (this.c != null && context != null && z && cx.c() > 17) {
            ContentResolver contentResolver = context.getContentResolver();
            try {
                if (((Integer) cs.a("android.provider.Settings$Global", "getInt", new Object[]{contentResolver, "wifi_scan_always_enabled"}, new Class[]{ContentResolver.class, String.class})).intValue() == 0) {
                    cs.a("android.provider.Settings$Global", "putInt", new Object[]{contentResolver, "wifi_scan_always_enabled", 1}, new Class[]{ContentResolver.class, String.class, Integer.TYPE});
                }
            } catch (Throwable th) {
                f.a(th, "WifiManagerWrapper", "enableWifiAlwaysScan");
            }
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:8:0x0019, code lost:
        if (a(r2.getConnectionInfo()) != false) goto L_0x001b;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean a(android.net.ConnectivityManager r5) {
        /*
            r4 = this;
            r0 = 1
            r1 = 0
            android.net.wifi.WifiManager r2 = r4.c
            if (r2 != 0) goto L_0x0007
        L_0x0006:
            return r1
        L_0x0007:
            android.net.NetworkInfo r3 = r5.getActiveNetworkInfo()     // Catch: Throwable -> 0x001d
            int r3 = com.loc.cx.a(r3)     // Catch: Throwable -> 0x001d
            if (r3 != r0) goto L_0x0025
            android.net.wifi.WifiInfo r2 = r2.getConnectionInfo()     // Catch: Throwable -> 0x001d
            boolean r2 = a(r2)     // Catch: Throwable -> 0x001d
            if (r2 == 0) goto L_0x0025
        L_0x001b:
            r1 = r0
            goto L_0x0006
        L_0x001d:
            r0 = move-exception
            java.lang.String r2 = "WifiManagerWrapper"
            java.lang.String r3 = "wifiAccess"
            com.loc.f.a(r0, r2, r3)
        L_0x0025:
            r0 = r1
            goto L_0x001b
        */
        throw new UnsupportedOperationException("Method not decompiled: com.loc.cd.a(android.net.ConnectivityManager):boolean");
    }

    public final WifiInfo b() {
        try {
            if (this.c != null) {
                return this.c.getConnectionInfo();
            }
        } catch (Throwable th) {
            f.a(th, "WifiManagerWrapper", "getConnectionInfo");
        }
        return null;
    }

    public final int c() {
        if (this.c != null) {
            return this.c.getWifiState();
        }
        return 4;
    }

    public final boolean d() {
        if (cx.b() - this.a < 5000 || this.c == null) {
            return false;
        }
        this.a = cx.b();
        return this.c.startScan();
    }

    public final boolean e() {
        boolean z = false;
        WifiManager wifiManager = this.c;
        if (wifiManager == null) {
            return false;
        }
        try {
            z = wifiManager.isWifiEnabled();
        } catch (Throwable th) {
            f.a(th, "WifiManagerWrapper", "wifiEnabled1");
        }
        if (z || cx.c() <= 17) {
            return z;
        }
        try {
            return String.valueOf(cs.a(wifiManager, this.b, new Object[0])).equals("true");
        } catch (Throwable th2) {
            f.a(th2, "WifiManagerWrapper", "wifiEnabled");
            return z;
        }
    }

    public final String f() {
        return this.e;
    }

    public final List<WifiConfiguration> g() {
        if (this.c != null) {
            return this.c.getConfiguredNetworks();
        }
        return null;
    }
}
