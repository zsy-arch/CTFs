package u.aly;

import android.content.Context;

/* compiled from: ReportPolicy.java */
/* loaded from: classes2.dex */
public class bq {
    public static final int a = 0;
    public static final int b = 1;
    static final int c = 2;
    static final int d = 3;
    public static final int e = 4;
    public static final int f = 5;
    public static final int g = 6;
    public static final int h = 8;

    public static boolean a(int i2) {
        switch (i2) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 8:
                return true;
            case 7:
            default:
                return false;
        }
    }

    /* compiled from: ReportPolicy.java */
    /* loaded from: classes2.dex */
    public static class i {
        public boolean a(boolean z) {
            return true;
        }

        public boolean a() {
            return true;
        }
    }

    /* compiled from: ReportPolicy.java */
    /* loaded from: classes2.dex */
    public static class h extends i {
        @Override // u.aly.bq.i
        public boolean a(boolean z) {
            return true;
        }
    }

    /* compiled from: ReportPolicy.java */
    /* loaded from: classes2.dex */
    public static class d extends i {
        @Override // u.aly.bq.i
        public boolean a(boolean z) {
            return z;
        }
    }

    /* compiled from: ReportPolicy.java */
    /* loaded from: classes2.dex */
    public static class e extends i {
        private static long a = 90000;
        private static long b = com.umeng.analytics.a.j;
        private long c;
        private at d;

        public e(at atVar, long j) {
            this.d = atVar;
            a(j);
        }

        @Override // u.aly.bq.i
        public boolean a(boolean z) {
            return System.currentTimeMillis() - this.d.c >= this.c;
        }

        public void a(long j) {
            if (j < a || j > b) {
                this.c = a;
            } else {
                this.c = j;
            }
        }

        public long b() {
            return this.c;
        }

        public static boolean a(int i) {
            return ((long) i) >= a;
        }
    }

    /* compiled from: ReportPolicy.java */
    /* loaded from: classes2.dex */
    public static class g extends i {
        private long a = com.umeng.analytics.a.j;
        private at b;

        public g(at atVar) {
            this.b = atVar;
        }

        @Override // u.aly.bq.i
        public boolean a(boolean z) {
            return System.currentTimeMillis() - this.b.c >= this.a;
        }
    }

    /* compiled from: ReportPolicy.java */
    /* loaded from: classes2.dex */
    public static class j extends i {
        private Context a;

        public j(Context context) {
            this.a = null;
            this.a = context;
        }

        @Override // u.aly.bq.i
        public boolean a(boolean z) {
            return bl.k(this.a);
        }
    }

    /* compiled from: ReportPolicy.java */
    /* loaded from: classes2.dex */
    public static class b extends i {
        private ay a;
        private at b;

        public b(at atVar, ay ayVar) {
            this.b = atVar;
            this.a = ayVar;
        }

        @Override // u.aly.bq.i
        public boolean a(boolean z) {
            return System.currentTimeMillis() - this.b.c >= this.a.a();
        }

        @Override // u.aly.bq.i
        public boolean a() {
            return this.a.c();
        }
    }

    /* compiled from: ReportPolicy.java */
    /* loaded from: classes2.dex */
    public static class c extends i {
        private long a;
        private long b;

        public c(int i) {
            this.b = 0L;
            this.a = i;
            this.b = System.currentTimeMillis();
        }

        @Override // u.aly.bq.i
        public boolean a(boolean z) {
            return System.currentTimeMillis() - this.b >= this.a;
        }

        @Override // u.aly.bq.i
        public boolean a() {
            return System.currentTimeMillis() - this.b < this.a;
        }
    }

    /* compiled from: ReportPolicy.java */
    /* loaded from: classes2.dex */
    public static class f extends i {
        private final int a;
        private al b;

        public f(al alVar, int i) {
            this.a = i;
            this.b = alVar;
        }

        @Override // u.aly.bq.i
        public boolean a(boolean z) {
            return this.b.b() > this.a;
        }
    }

    /* compiled from: ReportPolicy.java */
    /* loaded from: classes2.dex */
    public static class k extends i {
        private final long a = 10800000;
        private at b;

        public k(at atVar) {
            this.b = atVar;
        }

        @Override // u.aly.bq.i
        public boolean a(boolean z) {
            return System.currentTimeMillis() - this.b.c >= 10800000;
        }
    }

    /* compiled from: ReportPolicy.java */
    /* loaded from: classes2.dex */
    public static class a extends i {
        private final long a = 15000;
        private at b;

        public a(at atVar) {
            this.b = atVar;
        }

        @Override // u.aly.bq.i
        public boolean a(boolean z) {
            return System.currentTimeMillis() - this.b.c >= 15000;
        }
    }
}
