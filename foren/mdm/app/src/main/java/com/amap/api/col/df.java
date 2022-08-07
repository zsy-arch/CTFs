package com.amap.api.col;

/* compiled from: EarClippingTriangulator.java */
/* loaded from: classes.dex */
public class df {
    private short[] b;
    private float[] c;
    private int d;
    private final ds a = new ds();
    private final dk e = new dk();
    private final ds f = new ds();

    public ds a(float[] fArr) {
        return a(fArr, 0, fArr.length);
    }

    public ds a(float[] fArr, int i, int i2) {
        this.c = fArr;
        int i3 = i2 / 2;
        this.d = i3;
        int i4 = i / 2;
        ds dsVar = this.a;
        dsVar.a();
        dsVar.c(i3);
        dsVar.b = i3;
        short[] sArr = dsVar.a;
        this.b = sArr;
        int i5 = i3 - 1;
        for (int i6 = 0; i6 < i3; i6++) {
            sArr[i6] = (short) ((i4 + i5) - i6);
        }
        dk dkVar = this.e;
        dkVar.a();
        dkVar.c(i3);
        for (int i7 = 0; i7 < i3; i7++) {
            dkVar.a(a(i7));
        }
        ds dsVar2 = this.f;
        dsVar2.a();
        dsVar2.c(Math.max(0, i3 - 2) * 3);
        a();
        return dsVar2;
    }

    private void a() {
        int[] iArr = this.e.a;
        while (this.d > 3) {
            int b = b();
            c(b);
            int d = d(b);
            if (b == this.d) {
                b = 0;
            }
            iArr[d] = a(d);
            iArr[b] = a(b);
        }
        if (this.d == 3) {
            ds dsVar = this.f;
            short[] sArr = this.b;
            dsVar.a(sArr[0]);
            dsVar.a(sArr[1]);
            dsVar.a(sArr[2]);
        }
    }

    private int a(int i) {
        short[] sArr = this.b;
        int i2 = sArr[d(i)] * 2;
        int i3 = sArr[i] * 2;
        int i4 = sArr[e(i)] * 2;
        float[] fArr = this.c;
        return a(fArr[i2], fArr[i2 + 1], fArr[i3], fArr[i3 + 1], fArr[i4], fArr[i4 + 1]);
    }

    private int b() {
        int i = this.d;
        for (int i2 = 0; i2 < i; i2++) {
            if (b(i2)) {
                return i2;
            }
        }
        int[] iArr = this.e.a;
        for (int i3 = 0; i3 < i; i3++) {
            if (iArr[i3] != -1) {
                return i3;
            }
        }
        return 0;
    }

    private boolean b(int i) {
        int[] iArr = this.e.a;
        if (iArr[i] == -1) {
            return false;
        }
        int d = d(i);
        int e = e(i);
        short[] sArr = this.b;
        int i2 = sArr[d] * 2;
        int i3 = sArr[i] * 2;
        int i4 = sArr[e] * 2;
        float[] fArr = this.c;
        float f = fArr[i2];
        float f2 = fArr[i2 + 1];
        float f3 = fArr[i3];
        float f4 = fArr[i3 + 1];
        float f5 = fArr[i4];
        float f6 = fArr[i4 + 1];
        int e2 = e(e);
        while (e2 != d) {
            if (iArr[e2] != 1) {
                int i5 = sArr[e2] * 2;
                float f7 = fArr[i5];
                float f8 = fArr[i5 + 1];
                if (a(f5, f6, f, f2, f7, f8) >= 0 && a(f, f2, f3, f4, f7, f8) >= 0 && a(f3, f4, f5, f6, f7, f8) >= 0) {
                    return false;
                }
            }
            e2 = e(e2);
        }
        return true;
    }

    private void c(int i) {
        short[] sArr = this.b;
        ds dsVar = this.f;
        dsVar.a(sArr[d(i)]);
        dsVar.a(sArr[i]);
        dsVar.a(sArr[e(i)]);
        this.a.b(i);
        this.e.b(i);
        this.d--;
    }

    private int d(int i) {
        if (i == 0) {
            i = this.d;
        }
        return i - 1;
    }

    private int e(int i) {
        return (i + 1) % this.d;
    }

    private static int a(float f, float f2, float f3, float f4, float f5, float f6) {
        return (int) Math.signum(((f6 - f4) * f) + ((f2 - f6) * f3) + ((f4 - f2) * f5));
    }
}
