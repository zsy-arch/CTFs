package com.amap.api.col;

import android.content.ContentResolver;
import android.content.Context;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.text.TextUtils;
import com.umeng.analytics.a;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.TreeMap;

/* compiled from: WifiManagerWrapper.java */
/* loaded from: classes.dex */
public final class ji {
    static long e = 0;
    static long f = 0;
    static long g = 0;
    static long h = 0;
    WifiManager a;
    Context i;
    Object b = new Object();
    ArrayList<ScanResult> c = new ArrayList<>();
    ArrayList<ScanResult> d = new ArrayList<>();
    boolean j = false;
    StringBuilder k = null;
    boolean l = true;
    boolean m = true;
    private volatile WifiInfo r = null;
    String n = "isScanAlwaysAvailable";
    String o = null;
    TreeMap<Integer, ScanResult> p = null;
    public boolean q = true;

    public ji(Context context, WifiManager wifiManager) {
        this.a = wifiManager;
        this.i = context;
    }

    private static boolean a(int i) {
        int i2 = 20;
        try {
            i2 = WifiManager.calculateSignalLevel(i, 20);
        } catch (ArithmeticException e2) {
            jn.a(e2, "APS", "wifiSigFine");
        }
        return i2 > 0;
    }

    public static boolean a(WifiInfo wifiInfo) {
        return wifiInfo != null && !TextUtils.isEmpty(wifiInfo.getSSID()) && jq.a(wifiInfo.getBSSID());
    }

    public static String i() {
        return String.valueOf(jq.b() - h);
    }

    private List<ScanResult> j() {
        if (this.a != null) {
            try {
                List<ScanResult> scanResults = this.a.getScanResults();
                this.o = null;
                return scanResults;
            } catch (SecurityException e2) {
                this.o = e2.getMessage();
            } catch (Throwable th) {
                this.o = null;
                jn.a(th, "WifiManagerWrapper", "getScanResults");
            }
        }
        return null;
    }

    private WifiInfo k() {
        try {
            if (this.a != null) {
                return this.a.getConnectionInfo();
            }
        } catch (Throwable th) {
            jn.a(th, "WifiManagerWrapper", "getConnectionInfo");
        }
        return null;
    }

    private int l() {
        if (this.a != null) {
            return this.a.getWifiState();
        }
        return 4;
    }

    private boolean m() {
        if (jq.b() - e < 5000 || this.a == null) {
            return false;
        }
        e = jq.b();
        return this.a.startScan();
    }

    private boolean n() {
        boolean z = false;
        WifiManager wifiManager = this.a;
        if (wifiManager == null) {
            return false;
        }
        try {
            z = wifiManager.isWifiEnabled();
        } catch (Throwable th) {
            jn.a(th, "WifiManagerWrapper", "wifiEnabled1");
        }
        if (z || jq.c() <= 17) {
            return z;
        }
        try {
            return String.valueOf(jo.a(wifiManager, this.n, new Object[0])).equals("true");
        } catch (Throwable th2) {
            jn.a(th2, "WifiManagerWrapper", "wifiEnabled");
            return z;
        }
    }

    private void o() {
        if (!(this.c == null || this.c.isEmpty())) {
            if (jq.b() - h > a.k) {
                b();
                this.c.clear();
            }
            if (this.p == null) {
                this.p = new TreeMap<>(Collections.reverseOrder());
            }
            this.p.clear();
            int size = this.c.size();
            for (int i = 0; i < size; i++) {
                ScanResult scanResult = this.c.get(i);
                if (jq.a(scanResult != null ? scanResult.BSSID : "") && (size <= 20 || a(scanResult.level))) {
                    if (TextUtils.isEmpty(scanResult.SSID)) {
                        scanResult.SSID = "unkwn";
                    } else if (!"<unknown ssid>".equals(scanResult.SSID)) {
                        scanResult.SSID = String.valueOf(i);
                    }
                    this.p.put(Integer.valueOf((scanResult.level * 25) + i), scanResult);
                }
            }
            this.c.clear();
            for (ScanResult scanResult2 : this.p.values()) {
                this.c.add(scanResult2);
            }
            this.p.clear();
        }
    }

    private void p() {
        if (s()) {
            long b = jq.b();
            if (b - f >= 10000) {
                synchronized (this.b) {
                    this.d.clear();
                }
            }
            r();
            if (b - f >= 10000) {
                for (int i = 20; i > 0 && this.d.isEmpty(); i--) {
                    try {
                        Thread.sleep(150L);
                    } catch (Throwable th) {
                    }
                }
            }
            synchronized (this.b) {
            }
        }
    }

    private void q() {
        ArrayList<ScanResult> arrayList = this.c;
        ArrayList<ScanResult> arrayList2 = this.d;
        arrayList.clear();
        synchronized (this.b) {
            if (arrayList2 != null) {
                if (arrayList2.size() > 0) {
                    arrayList.addAll(arrayList2);
                }
            }
        }
    }

    private void r() {
        if (s()) {
            try {
                if (m()) {
                    g = jq.b();
                }
            } catch (Throwable th) {
                jn.a(th, "APS", "updateWifi");
            }
        }
    }

    private boolean s() {
        this.q = n();
        if (!this.q || !this.l) {
            return false;
        }
        if (g == 0) {
            return true;
        }
        if (jq.b() - g < 5000 || jq.b() - h < 1500) {
            return false;
        }
        return jq.b() - h > 5000 ? true : true;
    }

    public final ArrayList<ScanResult> a() {
        return this.c;
    }

    public final void a(boolean z) {
        Context context = this.i;
        if (this.a != null && context != null && z && jq.c() > 17) {
            ContentResolver contentResolver = context.getContentResolver();
            try {
                if (((Integer) jo.a("android.provider.Settings$Global", "getInt", new Object[]{contentResolver, "wifi_scan_always_enabled"}, new Class[]{ContentResolver.class, String.class})).intValue() == 0) {
                    jo.a("android.provider.Settings$Global", "putInt", new Object[]{contentResolver, "wifi_scan_always_enabled", 1}, new Class[]{ContentResolver.class, String.class, Integer.TYPE});
                }
            } catch (Throwable th) {
                jn.a(th, "WifiManagerWrapper", "enableWifiAlwaysScan");
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
            android.net.wifi.WifiManager r2 = r4.a
            if (r2 != 0) goto L_0x0007
        L_0x0006:
            return r1
        L_0x0007:
            android.net.NetworkInfo r3 = r5.getActiveNetworkInfo()     // Catch: Throwable -> 0x001d
            int r3 = com.amap.api.col.jq.a(r3)     // Catch: Throwable -> 0x001d
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
            com.amap.api.col.jn.a(r0, r2, r3)
        L_0x0025:
            r0 = r1
            goto L_0x001b
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amap.api.col.ji.a(android.net.ConnectivityManager):boolean");
    }

    public final void b() {
        this.r = null;
        synchronized (this.b) {
            this.d.clear();
        }
    }

    public final void b(boolean z) {
        if (z) {
            p();
        } else {
            r();
        }
        f = jq.b();
        if (this.d.isEmpty()) {
            h = jq.b();
            List<ScanResult> j = j();
            if (j != null) {
                synchronized (this.b) {
                    this.d.addAll(j);
                }
            }
            q();
            o();
        }
    }

    public final void c() {
        if (this.a != null && jq.b() - h > 5000) {
            List<ScanResult> list = null;
            try {
                list = j();
            } catch (Throwable th) {
                jn.a(th, "APS", "onReceive part1");
            }
            if (list != null) {
                synchronized (this.b) {
                    this.d.clear();
                    this.d.addAll(list);
                    h = jq.b();
                }
            } else {
                synchronized (this.b) {
                    this.d.clear();
                }
            }
            q();
            o();
        }
    }

    public final void c(boolean z) {
        this.l = z;
        this.m = true;
    }

    public final void d() {
        if (this.a != null) {
            int i = 4;
            try {
                i = l();
            } catch (Throwable th) {
                jn.a(th, "APS", "onReceive part");
            }
            if (this.d == null) {
                this.d = new ArrayList<>();
            }
            switch (i) {
                case 0:
                    b();
                    return;
                case 1:
                    b();
                    return;
                case 2:
                case 3:
                default:
                    return;
                case 4:
                    b();
                    return;
            }
        }
    }

    public final boolean e() {
        return this.q;
    }

    public final WifiInfo f() {
        this.r = k();
        return this.r;
    }

    public final boolean g() {
        return this.j;
    }

    public final void h() {
        b();
        this.c.clear();
    }
}
