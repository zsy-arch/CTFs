package com.hyphenate.a;

import android.annotation.SuppressLint;
import android.net.TrafficStats;
import android.os.Build;
import android.os.Process;
import com.hyphenate.util.EMLog;

@SuppressLint({"NewApi"})
/* loaded from: classes2.dex */
public class a {
    static final String a = "net";
    static long k;
    static long l;
    static int s;
    protected static a b = null;
    static long c = 0;
    static long d = 0;
    static long e = 0;
    static long f = 0;
    static long g = 0;
    static long h = 0;
    static long i = 0;
    static long j = 0;
    static long m = 0;
    static long n = 0;
    static long o = 0;
    static long p = 0;
    static long q = 0;
    static long r = 0;
    static long t = 0;

    /* renamed from: u */
    static long f30u = 0;
    static boolean v = false;

    public static void a() {
        s = Process.myUid();
        b();
        v = true;
    }

    public static void b() {
        c = TrafficStats.getUidRxBytes(s);
        d = TrafficStats.getUidTxBytes(s);
        if (Build.VERSION.SDK_INT >= 12) {
            e = TrafficStats.getUidRxPackets(s);
            f = TrafficStats.getUidTxPackets(s);
        } else {
            e = 0L;
            f = 0L;
        }
        k = 0L;
        l = 0L;
        m = 0L;
        n = 0L;
        o = 0L;
        p = 0L;
        q = 0L;
        r = 0L;
        f30u = System.currentTimeMillis();
        t = System.currentTimeMillis();
    }

    public static void c() {
        v = false;
        b();
    }

    public static void d() {
        if (v) {
            Long valueOf = Long.valueOf(System.currentTimeMillis());
            long longValue = (valueOf.longValue() - t) / 1000;
            if (longValue == 0) {
                longValue = 1;
            }
            o = TrafficStats.getUidRxBytes(s);
            p = TrafficStats.getUidTxBytes(s);
            k = o - c;
            l = p - d;
            g += k;
            h += l;
            if (Build.VERSION.SDK_INT >= 12) {
                q = TrafficStats.getUidRxPackets(s);
                r = TrafficStats.getUidTxPackets(s);
                m = q - e;
                n = r - f;
                i += m;
                j += n;
            }
            if (k == 0 && l == 0) {
                EMLog.d("net", "no network traffice");
                return;
            }
            EMLog.d("net", l + " bytes send; " + k + " bytes received in " + longValue + " sec");
            if (Build.VERSION.SDK_INT >= 12 && n > 0) {
                EMLog.d("net", n + " packets send; " + m + " packets received in " + longValue + " sec");
            }
            EMLog.d("net", "total:" + h + " bytes send; " + g + " bytes received");
            if (Build.VERSION.SDK_INT >= 12 && j > 0) {
                EMLog.d("net", "total:" + j + " packets send; " + i + " packets received in " + ((System.currentTimeMillis() - f30u) / 1000));
            }
            c = o;
            d = p;
            e = q;
            f = r;
            t = valueOf.longValue();
        }
    }
}
