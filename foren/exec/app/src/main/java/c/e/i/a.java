package c.e.i;

import android.content.res.Resources;
import android.os.Build;
import android.os.SystemClock;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AnimationUtils;
import android.view.animation.Interpolator;
import android.widget.ListView;
import c.e.h.n;
import com.tencent.smtt.sdk.TbsListener;

/* loaded from: classes.dex */
public abstract class a implements View.OnTouchListener {

    /* renamed from: a */
    public static final int f877a = ViewConfiguration.getTapTimeout();

    /* renamed from: d */
    public final View f880d;

    /* renamed from: e */
    public Runnable f881e;
    public boolean m;
    public boolean n;
    public boolean o;
    public boolean p;
    public boolean q;
    public boolean r;

    /* renamed from: b */
    public final C0013a f878b = new C0013a();

    /* renamed from: c */
    public final Interpolator f879c = new AccelerateInterpolator();
    public float[] f = {0.0f, 0.0f};
    public float[] g = {Float.MAX_VALUE, Float.MAX_VALUE};
    public float[] j = {0.0f, 0.0f};
    public float[] k = {0.0f, 0.0f};
    public float[] l = {Float.MAX_VALUE, Float.MAX_VALUE};
    public int h = 1;
    public int i = f877a;

    /* loaded from: classes.dex */
    public class b implements Runnable {
        public b() {
            a.this = r1;
        }

        @Override // java.lang.Runnable
        public void run() {
            a aVar = a.this;
            if (aVar.p) {
                if (aVar.n) {
                    aVar.n = false;
                    aVar.f878b.b();
                }
                C0013a aVar2 = a.this.f878b;
                if ((aVar2.i > 0 && AnimationUtils.currentAnimationTimeMillis() > aVar2.i + ((long) aVar2.k)) || !a.this.c()) {
                    a.this.p = false;
                    return;
                }
                a aVar3 = a.this;
                if (aVar3.o) {
                    aVar3.o = false;
                    aVar3.a();
                }
                if (aVar2.f != 0) {
                    long currentAnimationTimeMillis = AnimationUtils.currentAnimationTimeMillis();
                    float a2 = aVar2.a(currentAnimationTimeMillis);
                    aVar2.f = currentAnimationTimeMillis;
                    float f = ((float) (currentAnimationTimeMillis - aVar2.f)) * ((a2 * 4.0f) + ((-4.0f) * a2 * a2));
                    aVar2.g = (int) (aVar2.f884c * f);
                    aVar2.h = (int) (f * aVar2.f885d);
                    int i = aVar2.g;
                    int i2 = aVar2.h;
                    ListView listView = ((c) a.this).s;
                    int i3 = Build.VERSION.SDK_INT;
                    listView.scrollListBy(i2);
                    n.a(a.this.f880d, this);
                    return;
                }
                throw new RuntimeException("Cannot compute scroll delta before calling start()");
            }
        }
    }

    public a(View view) {
        this.f880d = view;
        float f = Resources.getSystem().getDisplayMetrics().density;
        float[] fArr = this.l;
        float f2 = ((int) ((1575.0f * f) + 0.5f)) / 1000.0f;
        fArr[0] = f2;
        fArr[1] = f2;
        float[] fArr2 = this.k;
        float f3 = ((int) ((f * 315.0f) + 0.5f)) / 1000.0f;
        fArr2[0] = f3;
        fArr2[1] = f3;
        float[] fArr3 = this.g;
        fArr3[0] = Float.MAX_VALUE;
        fArr3[1] = Float.MAX_VALUE;
        float[] fArr4 = this.f;
        fArr4[0] = 0.2f;
        fArr4[1] = 0.2f;
        float[] fArr5 = this.j;
        fArr5[0] = 0.001f;
        fArr5[1] = 0.001f;
        C0013a aVar = this.f878b;
        aVar.f882a = TbsListener.ErrorCode.INFO_CODE_MINIQB;
        aVar.f883b = TbsListener.ErrorCode.INFO_CODE_MINIQB;
    }

    public static float a(float f, float f2, float f3) {
        return f > f3 ? f3 : f < f2 ? f2 : f;
    }

    public static int a(int i, int i2, int i3) {
        return i > i3 ? i3 : i < i2 ? i2 : i;
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x003e A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:13:0x003f  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final float a(int r4, float r5, float r6, float r7) {
        /*
            r3 = this;
            float[] r0 = r3.f
            r0 = r0[r4]
            float[] r1 = r3.g
            r1 = r1[r4]
            float r0 = r0 * r6
            r2 = 0
            float r0 = a(r0, r2, r1)
            float r1 = r3.a(r5, r0)
            float r6 = r6 - r5
            float r5 = r3.a(r6, r0)
            float r5 = r5 - r1
            int r6 = (r5 > r2 ? 1 : (r5 == r2 ? 0 : -1))
            if (r6 >= 0) goto L_0x0026
            android.view.animation.Interpolator r6 = r3.f879c
            float r5 = -r5
            float r5 = r6.getInterpolation(r5)
            float r5 = -r5
            goto L_0x0030
        L_0x0026:
            int r6 = (r5 > r2 ? 1 : (r5 == r2 ? 0 : -1))
            if (r6 <= 0) goto L_0x0039
            android.view.animation.Interpolator r6 = r3.f879c
            float r5 = r6.getInterpolation(r5)
        L_0x0030:
            r6 = -1082130432(0xffffffffbf800000, float:-1.0)
            r0 = 1065353216(0x3f800000, float:1.0)
            float r5 = a(r5, r6, r0)
            goto L_0x003a
        L_0x0039:
            r5 = 0
        L_0x003a:
            int r6 = (r5 > r2 ? 1 : (r5 == r2 ? 0 : -1))
            if (r6 != 0) goto L_0x003f
            return r2
        L_0x003f:
            float[] r0 = r3.j
            r0 = r0[r4]
            float[] r1 = r3.k
            r1 = r1[r4]
            float[] r2 = r3.l
            r4 = r2[r4]
            float r0 = r0 * r7
            if (r6 <= 0) goto L_0x0056
            float r5 = r5 * r0
            float r4 = a(r5, r1, r4)
            return r4
        L_0x0056:
            float r5 = -r5
            float r5 = r5 * r0
            float r4 = a(r5, r1, r4)
            float r4 = -r4
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: c.e.i.a.a(int, float, float, float):float");
    }

    public final void b() {
        if (this.n) {
            this.p = false;
        } else {
            this.f878b.a();
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:21:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean c() {
        /*
            r9 = this;
            c.e.i.a$a r0 = r9.f878b
            float r1 = r0.f885d
            float r2 = java.lang.Math.abs(r1)
            float r1 = r1 / r2
            int r1 = (int) r1
            float r0 = r0.f884c
            float r2 = java.lang.Math.abs(r0)
            float r0 = r0 / r2
            int r0 = (int) r0
            r2 = 1
            r3 = 0
            if (r1 == 0) goto L_0x0053
            r4 = r9
            c.e.i.c r4 = (c.e.i.c) r4
            android.widget.ListView r4 = r4.s
            int r5 = r4.getCount()
            if (r5 != 0) goto L_0x0023
        L_0x0021:
            r1 = 0
            goto L_0x0051
        L_0x0023:
            int r6 = r4.getChildCount()
            int r7 = r4.getFirstVisiblePosition()
            int r8 = r7 + r6
            if (r1 <= 0) goto L_0x0041
            if (r8 < r5) goto L_0x0050
            int r6 = r6 - r2
            android.view.View r1 = r4.getChildAt(r6)
            int r1 = r1.getBottom()
            int r4 = r4.getHeight()
            if (r1 > r4) goto L_0x0050
            goto L_0x0021
        L_0x0041:
            if (r1 >= 0) goto L_0x0021
            if (r7 > 0) goto L_0x0050
            android.view.View r1 = r4.getChildAt(r3)
            int r1 = r1.getTop()
            if (r1 < 0) goto L_0x0050
            goto L_0x0021
        L_0x0050:
            r1 = 1
        L_0x0051:
            if (r1 != 0) goto L_0x0059
        L_0x0053:
            if (r0 == 0) goto L_0x0058
            r0 = r9
            c.e.i.c r0 = (c.e.i.c) r0
        L_0x0058:
            r2 = 0
        L_0x0059:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: c.e.i.a.c():boolean");
    }

    /* JADX WARN: Code restructure failed: missing block: B:11:0x0013, code lost:
        if (r0 != 3) goto L_0x0086;
     */
    @Override // android.view.View.OnTouchListener
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean onTouch(android.view.View r6, android.view.MotionEvent r7) {
        /*
            r5 = this;
            boolean r0 = r5.q
            r1 = 0
            if (r0 != 0) goto L_0x0006
            return r1
        L_0x0006:
            int r0 = r7.getActionMasked()
            r2 = 1
            if (r0 == 0) goto L_0x0023
            if (r0 == r2) goto L_0x0016
            r3 = 2
            if (r0 == r3) goto L_0x0027
            r6 = 3
            if (r0 == r6) goto L_0x0016
            goto L_0x0086
        L_0x0016:
            boolean r6 = r5.n
            if (r6 == 0) goto L_0x001d
            r5.p = r1
            goto L_0x0086
        L_0x001d:
            c.e.i.a$a r6 = r5.f878b
            r6.a()
            goto L_0x0086
        L_0x0023:
            r5.o = r2
            r5.m = r1
        L_0x0027:
            float r0 = r7.getX()
            int r3 = r6.getWidth()
            float r3 = (float) r3
            android.view.View r4 = r5.f880d
            int r4 = r4.getWidth()
            float r4 = (float) r4
            float r0 = r5.a(r1, r0, r3, r4)
            float r7 = r7.getY()
            int r6 = r6.getHeight()
            float r6 = (float) r6
            android.view.View r3 = r5.f880d
            int r3 = r3.getHeight()
            float r3 = (float) r3
            float r6 = r5.a(r2, r7, r6, r3)
            c.e.i.a$a r7 = r5.f878b
            r7.f884c = r0
            r7.f885d = r6
            boolean r6 = r5.p
            if (r6 != 0) goto L_0x0086
            boolean r6 = r5.c()
            if (r6 == 0) goto L_0x0086
            java.lang.Runnable r6 = r5.f881e
            if (r6 != 0) goto L_0x006a
            c.e.i.a$b r6 = new c.e.i.a$b
            r6.<init>()
            r5.f881e = r6
        L_0x006a:
            r5.p = r2
            r5.n = r2
            boolean r6 = r5.m
            if (r6 != 0) goto L_0x007f
            int r6 = r5.i
            if (r6 <= 0) goto L_0x007f
            android.view.View r7 = r5.f880d
            java.lang.Runnable r0 = r5.f881e
            long r3 = (long) r6
            c.e.h.n.a(r7, r0, r3)
            goto L_0x0084
        L_0x007f:
            java.lang.Runnable r6 = r5.f881e
            r6.run()
        L_0x0084:
            r5.m = r2
        L_0x0086:
            boolean r6 = r5.r
            if (r6 == 0) goto L_0x008f
            boolean r6 = r5.p
            if (r6 == 0) goto L_0x008f
            r1 = 1
        L_0x008f:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: c.e.i.a.onTouch(android.view.View, android.view.MotionEvent):boolean");
    }

    /* renamed from: c.e.i.a$a */
    /* loaded from: classes.dex */
    public static class C0013a {

        /* renamed from: a */
        public int f882a;

        /* renamed from: b */
        public int f883b;

        /* renamed from: c */
        public float f884c;

        /* renamed from: d */
        public float f885d;
        public float j;
        public int k;

        /* renamed from: e */
        public long f886e = Long.MIN_VALUE;
        public long i = -1;
        public long f = 0;
        public int g = 0;
        public int h = 0;

        public void a() {
            long currentAnimationTimeMillis = AnimationUtils.currentAnimationTimeMillis();
            this.k = a.a((int) (currentAnimationTimeMillis - this.f886e), 0, this.f883b);
            this.j = a(currentAnimationTimeMillis);
            this.i = currentAnimationTimeMillis;
        }

        public void b() {
            this.f886e = AnimationUtils.currentAnimationTimeMillis();
            this.i = -1L;
            this.f = this.f886e;
            this.j = 0.5f;
            this.g = 0;
            this.h = 0;
        }

        public final float a(long j) {
            if (j < this.f886e) {
                return 0.0f;
            }
            long j2 = this.i;
            if (j2 < 0 || j < j2) {
                return a.a(((float) (j - this.f886e)) / this.f882a, 0.0f, 1.0f) * 0.5f;
            }
            long j3 = j - j2;
            float f = this.j;
            return (a.a(((float) j3) / this.k, 0.0f, 1.0f) * f) + (1.0f - f);
        }
    }

    public final float a(float f, float f2) {
        if (f2 == 0.0f) {
            return 0.0f;
        }
        int i = this.h;
        if (i == 0 || i == 1) {
            if (f < f2) {
                if (f >= 0.0f) {
                    return 1.0f - (f / f2);
                }
                if (this.p && this.h == 1) {
                    return 1.0f;
                }
            }
        } else if (i == 2 && f < 0.0f) {
            return f / (-f2);
        }
        return 0.0f;
    }

    public void a() {
        long uptimeMillis = SystemClock.uptimeMillis();
        MotionEvent obtain = MotionEvent.obtain(uptimeMillis, uptimeMillis, 3, 0.0f, 0.0f, 0);
        this.f880d.onTouchEvent(obtain);
        obtain.recycle();
    }
}
