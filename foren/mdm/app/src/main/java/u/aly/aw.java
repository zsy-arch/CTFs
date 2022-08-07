package u.aly;

import android.os.Build;
import android.text.TextUtils;
import com.umeng.analytics.AnalyticsConfig;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* compiled from: UMEntry.java */
/* loaded from: classes2.dex */
public class aw implements Serializable {
    public static long c = 0;
    private static final long d = -5254997387189944418L;
    public n a = new n();
    public m b = new m();

    /* compiled from: UMEntry.java */
    /* loaded from: classes2.dex */
    public static class a {
        public static final int a = 0;
        public static final int b = 1;
        public static final int c = 2;
    }

    /* compiled from: UMEntry.java */
    /* loaded from: classes2.dex */
    public static class b implements Serializable {
        private static final long b = 395432415169525323L;
        public long a = 0;
    }

    /* compiled from: UMEntry.java */
    /* loaded from: classes2.dex */
    public static class c implements Serializable {
        private static final long c = -6648526015472635581L;
        public String a = null;
        public String b = null;
    }

    /* compiled from: UMEntry.java */
    /* loaded from: classes2.dex */
    public static class d implements Serializable {
        private static final long c = -4761083466478982295L;
        public Map<String, List<e>> a = new HashMap();
        public Map<String, List<f>> b = new HashMap();
    }

    /* compiled from: UMEntry.java */
    /* loaded from: classes2.dex */
    public static class e implements Serializable {
        private static final long f = 8614138410597604223L;
        public long a = 0;
        public long b = 0;
        public int c = 0;
        public int d = 0;
        public List<String> e = new ArrayList();
    }

    /* compiled from: UMEntry.java */
    /* loaded from: classes2.dex */
    public static final class f implements Serializable {
        private static final long d = -7569163627707250811L;
        public int a = 0;
        public long b = 0;
        public String c = null;
    }

    /* compiled from: UMEntry.java */
    /* loaded from: classes2.dex */
    public static class g implements Serializable {
        private static final long d = -1010993116426830703L;
        public Integer a = 0;
        public long b = 0;
        public boolean c = false;
    }

    /* compiled from: UMEntry.java */
    /* loaded from: classes2.dex */
    public static class h implements Serializable {
        private static final long c = -7833224895044623144L;
        public String a = null;
        public List<j> b = new ArrayList();
    }

    /* compiled from: UMEntry.java */
    /* loaded from: classes2.dex */
    public static final class k implements Serializable {
        private static final long d = -1397960951960451474L;
        public double a = 0.0d;
        public double b = 0.0d;
        public long c = 0;
    }

    /* compiled from: UMEntry.java */
    /* loaded from: classes2.dex */
    public static final class l implements Serializable {
        private static final long e = 2506525905874738341L;
        public String a = null;
        public long b = 0;
        public long c = 0;
        public long d = 0;
    }

    /* compiled from: UMEntry.java */
    /* loaded from: classes2.dex */
    public static class m implements Serializable {
        private static final long k = 5703014667657688269L;
        public List<h> a = new ArrayList();
        public List<h> b = new ArrayList();
        public List<o> c = new ArrayList();
        public b d = new b();
        public g e = new g();
        public Map<String, Integer> f = new HashMap();
        public c g = new c();
        public d h = new d();
        public List<i> i = new ArrayList();
        public String j = null;
    }

    /* compiled from: UMEntry.java */
    /* loaded from: classes2.dex */
    public static class n implements Serializable {
        private static final long U = 4568484649280698573L;
        public String a = null;
        public String b = null;
        public String c = null;
        public String d = null;
        public String e = null;
        public String f = null;
        public String g = null;
        public int h = 0;
        public String i = AnalyticsConfig.mWrapperType;
        public String j = AnalyticsConfig.mWrapperVersion;
        public String k = "Android";
        public String l = null;
        public int m = 0;
        public int n = 0;
        public String o = null;
        public String p = null;
        public String q = null;
        public String r = null;
        public String s = null;
        public String t = bl.a();

        /* renamed from: u */
        public String f55u = "Android";
        public String v = Build.VERSION.RELEASE;
        public String w = null;
        public String x = null;
        public String y = null;
        public String z = Build.MODEL;
        public String A = Build.BOARD;
        public String B = Build.BRAND;
        public long C = Build.TIME;
        public String D = Build.MANUFACTURER;
        public String E = Build.ID;
        public String F = Build.DEVICE;
        public String G = null;
        public String H = null;
        public long I = 8;
        public String J = null;
        public String K = null;
        public String L = null;
        public String M = null;
        public String N = null;
        public String O = null;
        public long P = 0;
        public long Q = 0;
        public long R = 0;
        public String S = null;
        public String T = null;
    }

    /* compiled from: UMEntry.java */
    /* loaded from: classes2.dex */
    public static final class p implements Serializable {
        private static final long c = -7629272972021970177L;
        public long a = 0;
        public long b = 0;
    }

    /* compiled from: UMEntry.java */
    /* loaded from: classes2.dex */
    public static class o implements Serializable, aj {
        private static final long k = 8683938900576888953L;
        public int a = 0;
        public String b = null;
        public long c = 0;
        public long d = 0;
        public long e = 0;
        public boolean f = false;
        public List<l> g = new ArrayList();
        public List<l> h = new ArrayList();
        public p i = new p();
        public k j = new k();

        @Override // u.aly.aj
        public void a(aw awVar) {
            if (awVar.b.c != null) {
                awVar.b.c.add(this);
            }
        }
    }

    /* compiled from: UMEntry.java */
    /* loaded from: classes2.dex */
    public static class j implements Serializable, aj {
        private static final long h = -1062440179015494286L;
        public int a = 0;
        public String b = null;
        public String c = null;
        public long d = 0;
        public long e = 0;
        public int f = 0;
        public Map<String, Object> g = new HashMap();

        @Override // u.aly.aj
        public void a(aw awVar) {
            int i = 0;
            if (this.b == null) {
                this.b = as.a();
            }
            if (awVar.b.a != null) {
                try {
                    if (this.a == 1) {
                        int size = awVar.b.a.size();
                        if (size > 0) {
                            for (int i2 = 0; i2 < size; i2++) {
                                h hVar = awVar.b.a.get(i2);
                                if (!TextUtils.isEmpty(hVar.a) && hVar.a.equals(this.b)) {
                                    awVar.b.a.remove(hVar);
                                    hVar.b.add(this);
                                    awVar.b.a.add(hVar);
                                    return;
                                }
                            }
                            h hVar2 = new h();
                            hVar2.a = this.b;
                            hVar2.b.add(this);
                            if (!awVar.b.a.contains(hVar2)) {
                                awVar.b.a.add(hVar2);
                            }
                        } else {
                            h hVar3 = new h();
                            hVar3.a = this.b;
                            hVar3.b.add(this);
                            awVar.b.a.add(hVar3);
                        }
                    }
                } catch (Throwable th) {
                    bo.e(th);
                }
            }
            if (awVar.b.b != null) {
                try {
                    if (this.a == 2) {
                        int size2 = awVar.b.b.size();
                        if (size2 > 0) {
                            while (true) {
                                if (i >= size2) {
                                    h hVar4 = new h();
                                    hVar4.a = this.b;
                                    hVar4.b.add(this);
                                    awVar.b.b.add(hVar4);
                                    break;
                                }
                                h hVar5 = awVar.b.b.get(i);
                                if (!TextUtils.isEmpty(hVar5.a) && hVar5.a.equals(this.b)) {
                                    awVar.b.b.remove(hVar5);
                                    hVar5.b.add(this);
                                    awVar.b.b.add(hVar5);
                                    break;
                                }
                                i++;
                            }
                        } else {
                            h hVar6 = new h();
                            hVar6.a = this.b;
                            hVar6.b.add(this);
                            awVar.b.b.add(hVar6);
                        }
                    }
                } catch (Throwable th2) {
                    bo.e(th2);
                }
            }
        }
    }

    /* compiled from: UMEntry.java */
    /* loaded from: classes2.dex */
    public static class i implements Serializable, aj {
        private static final long d = -7911804253674023187L;
        public long a = 0;
        public long b = 0;
        public String c = null;

        @Override // u.aly.aj
        public void a(aw awVar) {
            if (awVar.b.i != null) {
                awVar.b.i.add(this);
            }
        }
    }

    public boolean a() {
        return (this.a.y == null || this.a.x == null || this.a.w == null || this.a.a == null || this.a.b == null || this.a.f == null || this.a.e == null || this.a.d == null) ? false : true;
    }

    public void b() {
        this.a = new n();
        this.b = new m();
        c = 0L;
    }
}
