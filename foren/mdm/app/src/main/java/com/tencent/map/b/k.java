package com.tencent.map.b;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.telephony.TelephonyManager;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.Iterator;

/* compiled from: ProGuard */
/* loaded from: classes2.dex */
public final class k {
    private static int a;
    private static int b;
    private static int c;
    private static int d;
    private static int e;
    private static int f;
    private static ArrayList<a> g;
    private static long h;
    private static long i;
    private static long j;
    private static long k;
    private static long l;
    private static long m;
    private static long n;
    private static long o;
    private static long p;
    private static long q;
    private static int r;
    private static int s;
    private static int t;

    /* renamed from: u  reason: collision with root package name */
    private static int f43u;

    /* compiled from: ProGuard */
    /* loaded from: classes2.dex */
    public static class a {
        public long a;
        public long b;
        public long c;
        public long d;
        public int e;
        public long f;
        public int g;
        public int h;
    }

    static {
        NetworkInfo activeNetworkInfo;
        String subscriberId;
        a = 10000;
        b = 15000;
        c = 5000;
        d = 20000;
        e = 25000;
        f = 15000;
        a = 12000;
        b = 20000;
        c = 8000;
        d = 20000;
        e = 25000;
        f = 15000;
        ConnectivityManager connectivityManager = (ConnectivityManager) l.b().getSystemService("connectivity");
        if (connectivityManager != null && (activeNetworkInfo = connectivityManager.getActiveNetworkInfo()) != null) {
            int type = activeNetworkInfo.getType();
            if (activeNetworkInfo.isConnected() && type == 0 && (subscriberId = ((TelephonyManager) l.b().getSystemService("phone")).getSubscriberId()) != null && subscriberId.length() > 3 && !subscriberId.startsWith("46000") && !subscriberId.startsWith("46002")) {
                a = 15000;
                b = 25000;
                c = 10000;
                d = 25000;
                e = 35000;
                f = 15000;
            }
        }
    }

    public static int a() {
        NetworkInfo activeNetworkInfo;
        int max = (j <= 0 || k <= 0) ? a : (int) ((Math.max(m, j) + k) - l);
        ConnectivityManager connectivityManager = (ConnectivityManager) l.b().getSystemService("connectivity");
        if (!(connectivityManager == null || (activeNetworkInfo = connectivityManager.getActiveNetworkInfo()) == null)) {
            if (!activeNetworkInfo.isConnected() && activeNetworkInfo.isAvailable()) {
                max = b;
            } else if (k > 0 && k < c) {
                max = c;
            }
        }
        int i2 = (f43u * c) + max;
        if (i2 <= c) {
            i2 = c;
        }
        if (i2 <= k) {
            i2 = (int) (k + c);
        }
        if (i2 >= b) {
            i2 = b;
        }
        a b2 = b(Thread.currentThread().getId());
        if (b2 == null) {
            b2 = a(Thread.currentThread().getId());
        }
        if (i2 < b2.g + c) {
            i2 = b2.g + c;
        }
        b2.g = i2;
        return i2;
    }

    public static int b() {
        NetworkInfo activeNetworkInfo;
        int max = (n <= 0 || o <= 0) ? d : (int) ((Math.max(q, n) + o) - p);
        ConnectivityManager connectivityManager = (ConnectivityManager) l.b().getSystemService("connectivity");
        if (!(connectivityManager == null || (activeNetworkInfo = connectivityManager.getActiveNetworkInfo()) == null)) {
            if (!activeNetworkInfo.isConnected() && activeNetworkInfo.isAvailable()) {
                max = e;
            } else if (o > 0 && o < f) {
                max = f;
            }
        }
        int i2 = (f43u * c) + max;
        if (i2 <= f) {
            i2 = f;
        }
        if (i2 <= o) {
            i2 = (int) (o + f);
        }
        if (i2 >= e) {
            i2 = e;
        }
        a b2 = b(Thread.currentThread().getId());
        if (b2 != null) {
            if (i2 < b2.h + f) {
                i2 = b2.h + f;
            }
            if (i2 < b2.g + f) {
                i2 = b2.g + f;
            }
            b2.h = i2;
        }
        return i2;
    }

    public static void a(boolean z) {
        if (!z) {
            f43u++;
        }
        a c2 = c(Thread.currentThread().getId());
        if (c2 != null) {
            long j2 = c2.b;
        }
    }

    public static void a(HttpURLConnection httpURLConnection) {
        a b2 = b(Thread.currentThread().getId());
        if (b2 == null) {
            b2 = a(Thread.currentThread().getId());
        }
        if (b2 != null) {
            b2.b = System.currentTimeMillis();
        }
    }

    public static void c() {
        long j2;
        a b2 = b(Thread.currentThread().getId());
        if (b2 != null) {
            b2.c = System.currentTimeMillis() - b2.b;
            b2.b = System.currentTimeMillis();
            m = b2.c;
            k = b2.c > k ? b2.c : k;
            if (b2.c < l) {
                j2 = b2.c;
            } else {
                j2 = l == 0 ? b2.c : l;
            }
            l = j2;
            if (g != null) {
                synchronized (g) {
                    Iterator<a> it = g.iterator();
                    int i2 = 0;
                    while (it.hasNext()) {
                        a next = it.next();
                        if (next.c > 0) {
                            j += next.c;
                            i2++;
                        }
                    }
                    if (i2 > 0) {
                        j /= i2;
                    }
                }
            }
        }
    }

    public static void d() {
        long j2;
        a b2 = b(Thread.currentThread().getId());
        if (b2 != null) {
            b2.d = System.currentTimeMillis() - b2.b;
            b2.b = System.currentTimeMillis();
            q = b2.d;
            o = b2.d > o ? b2.d : o;
            if (b2.d < p) {
                j2 = b2.d;
            } else {
                j2 = p == 0 ? b2.d : p;
            }
            p = j2;
            if (g != null) {
                synchronized (g) {
                    Iterator<a> it = g.iterator();
                    int i2 = 0;
                    while (it.hasNext()) {
                        a next = it.next();
                        if (next.d > 0) {
                            n += next.d;
                            i2++;
                        }
                    }
                    if (i2 > 0) {
                        n /= i2;
                    }
                }
            }
        }
    }

    public static void a(int i2) {
        int i3;
        a b2 = b(Thread.currentThread().getId());
        if (b2 != null) {
            b2.f = System.currentTimeMillis() - b2.b;
            b2.b = System.currentTimeMillis();
            b2.e = i2;
            int i4 = (int) ((i2 * 1000) / (b2.f == 0 ? 1L : b2.f));
            t = i4;
            r = i4 > r ? t : r;
            if (t < s) {
                i3 = t;
            } else {
                i3 = s == 0 ? t : s;
            }
            s = i3;
            if (g != null) {
                synchronized (g) {
                    Iterator<a> it = g.iterator();
                    while (it.hasNext()) {
                        a next = it.next();
                        int i5 = next.e;
                        long j2 = next.f;
                    }
                }
            }
            if (f43u > 0 && b2.c < c && b2.d < f) {
                f43u--;
            }
            b2.g = (int) b2.c;
        }
    }

    private static a a(long j2) {
        a aVar;
        if (g == null) {
            g = new ArrayList<>();
        }
        synchronized (g) {
            if (g.size() > 20) {
                int size = g.size();
                boolean z = false;
                int i2 = 0;
                for (int i3 = 0; i3 < size / 2; i3++) {
                    if (g.get(i2).f > 0 || System.currentTimeMillis() - g.get(i2).b > 600000) {
                        g.remove(i2);
                        z = true;
                        i2 = i2;
                    } else {
                        i2++;
                        z = z;
                    }
                }
                if (z) {
                    g.get(0);
                    h = 0L;
                    g.get(0);
                    i = 0L;
                    k = g.get(0).c;
                    l = g.get(0).c;
                    o = g.get(0).d;
                    p = g.get(0).d;
                    if (g.get(0).f > 0) {
                        r = (int) ((g.get(0).e * 1000) / g.get(0).f);
                    }
                    s = r;
                    Iterator<a> it = g.iterator();
                    while (it.hasNext()) {
                        a next = it.next();
                        if (0 > h) {
                            h = 0L;
                        }
                        if (0 < i) {
                            i = 0L;
                        }
                        if (next.c > k) {
                            k = next.c;
                        }
                        if (next.c < l) {
                            l = next.c;
                        }
                        if (next.d > o) {
                            o = next.d;
                        }
                        if (next.d < p) {
                            p = next.d;
                        }
                        if (next.f > 0) {
                            int i4 = (int) ((next.e * 1000) / next.f);
                            if (i4 > r) {
                                r = i4;
                            }
                            if (i4 < s) {
                                s = i4;
                            }
                        }
                    }
                }
            }
            aVar = new a();
            aVar.a = j2;
            g.add(aVar);
        }
        return aVar;
    }

    private static a b(long j2) {
        a aVar;
        if (g == null) {
            return null;
        }
        synchronized (g) {
            Iterator<a> it = g.iterator();
            while (true) {
                if (it.hasNext()) {
                    aVar = it.next();
                    if (aVar.a == j2) {
                        break;
                    }
                } else {
                    aVar = null;
                    break;
                }
            }
        }
        return aVar;
    }

    private static a c(long j2) {
        if (g != null) {
            synchronized (g) {
                for (int size = g.size() - 1; size >= 0; size--) {
                    if (g.get(size).a == j2) {
                        return g.remove(size);
                    }
                }
            }
        }
        return null;
    }
}
