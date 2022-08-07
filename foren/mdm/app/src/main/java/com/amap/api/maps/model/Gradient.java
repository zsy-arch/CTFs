package com.amap.api.maps.model;

import android.graphics.Color;
import android.util.Log;
import com.amap.api.maps.AMapException;
import java.util.HashMap;

/* loaded from: classes.dex */
public class Gradient {
    private int a;
    private int[] b;
    private float[] c;
    private boolean d;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public class a {
        private final int b;
        private final int c;
        private final float d;

        private a(int i, int i2, float f) {
            this.b = i;
            this.c = i2;
            this.d = f;
        }
    }

    public Gradient(int[] iArr, float[] fArr) {
        this(iArr, fArr, 1000);
    }

    private Gradient(int[] iArr, float[] fArr, int i) {
        this.d = true;
        try {
            if (iArr == null || fArr == null) {
                throw new AMapException("colors and startPoints should not be null");
            } else if (iArr.length != fArr.length) {
                throw new AMapException("colors and startPoints should be same length");
            } else if (iArr.length == 0) {
                throw new AMapException("No colors have been defined");
            } else {
                for (int i2 = 1; i2 < fArr.length; i2++) {
                    if (fArr[i2] <= fArr[i2 - 1]) {
                        throw new AMapException("startPoints should be in increasing order");
                    }
                }
                this.a = i;
                this.b = new int[iArr.length];
                this.c = new float[fArr.length];
                System.arraycopy(iArr, 0, this.b, 0, iArr.length);
                System.arraycopy(fArr, 0, this.c, 0, fArr.length);
                this.d = true;
            }
        } catch (AMapException e) {
            this.d = false;
            Log.e("amap", e.getErrorMessage());
            e.printStackTrace();
        }
    }

    private HashMap<Integer, a> a() {
        HashMap<Integer, a> hashMap = new HashMap<>();
        if (this.c[0] != 0.0f) {
            hashMap.put(0, new a(Color.argb(0, Color.red(this.b[0]), Color.green(this.b[0]), Color.blue(this.b[0])), this.b[0], this.c[0] * this.a));
        }
        for (int i = 1; i < this.b.length; i++) {
            hashMap.put(Integer.valueOf((int) (this.a * this.c[i - 1])), new a(this.b[i - 1], this.b[i], (this.c[i] - this.c[i - 1]) * this.a));
        }
        if (this.c[this.c.length - 1] != 1.0f) {
            int length = this.c.length - 1;
            hashMap.put(Integer.valueOf((int) (this.a * this.c[length])), new a(this.b[length], this.b[length], this.a * (1.0f - this.c[length])));
        }
        return hashMap;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public int[] generateColorMap(double d) {
        HashMap<Integer, a> a2 = a();
        int[] iArr = new int[this.a];
        a aVar = a2.get(0);
        int i = 0;
        int i2 = 0;
        while (i < this.a) {
            if (a2.containsKey(Integer.valueOf(i))) {
                aVar = a2.get(Integer.valueOf(i));
                i2 = i;
            }
            iArr[i] = a(aVar.b, aVar.c, (i - i2) / aVar.d);
            i++;
            i2 = i2;
            aVar = aVar;
        }
        if (d != 1.0d) {
            for (int i3 = 0; i3 < this.a; i3++) {
                int i4 = iArr[i3];
                iArr[i3] = Color.argb((int) (Color.alpha(i4) * d), Color.red(i4), Color.green(i4), Color.blue(i4));
            }
        }
        return iArr;
    }

    static int a(int i, int i2, float f) {
        int alpha = (int) (((Color.alpha(i2) - Color.alpha(i)) * f) + Color.alpha(i));
        float[] fArr = new float[3];
        Color.RGBToHSV(Color.red(i), Color.green(i), Color.blue(i), fArr);
        float[] fArr2 = new float[3];
        Color.RGBToHSV(Color.red(i2), Color.green(i2), Color.blue(i2), fArr2);
        if (fArr[0] - fArr2[0] > 180.0f) {
            fArr2[0] = fArr2[0] + 360.0f;
        } else if (fArr2[0] - fArr[0] > 180.0f) {
            fArr[0] = fArr[0] + 360.0f;
        }
        float[] fArr3 = new float[3];
        for (int i3 = 0; i3 < 3; i3++) {
            fArr3[i3] = ((fArr2[i3] - fArr[i3]) * f) + fArr[i3];
        }
        return Color.HSVToColor(alpha, fArr3);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean isAvailable() {
        return this.d;
    }
}
