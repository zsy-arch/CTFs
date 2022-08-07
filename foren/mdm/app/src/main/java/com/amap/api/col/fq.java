package com.amap.api.col;

import com.autonavi.amap.mapcore.DPoint;
import com.autonavi.amap.mapcore.IPoint;

/* compiled from: VEarthProjection.java */
/* loaded from: classes.dex */
public class fq {
    private static int a = 1;

    public static double a(double d, double d2, double d3) {
        return Math.min(Math.max(d, d2), d3);
    }

    public static IPoint a(double d, double d2, int i) {
        IPoint iPoint = new IPoint();
        double sin = Math.sin((a(d, -85.0511287798d, 85.0511287798d) * 3.141592653589793d) / 180.0d);
        long j = 256 << i;
        double d3 = 4.007501668557849E7d / j;
        iPoint.x = (int) a((((((a(d2, -180.0d, 180.0d) * 3.141592653589793d) / 180.0d) * 6378137.0d) + 2.0037508342789244E7d) / d3) + 0.5d, 0.0d, j - 1);
        iPoint.y = (int) a((((long) (2.0037508342789244E7d - (3189068.0d * Math.log((1.0d + sin) / (1.0d - sin))))) / d3) + 0.5d, 0.0d, j - 1);
        return iPoint;
    }

    public static DPoint a(long j, long j2, int i) {
        DPoint dPoint = new DPoint();
        double d = 4.007501668557849E7d / ((1 << i) * 256);
        dPoint.y = 1.5707963267948966d - (Math.atan(Math.exp((-(2.0037508342789244E7d - (d * j2))) / 6378137.0d)) * 2.0d);
        dPoint.y *= 57.29577951308232d;
        dPoint.x = ((j * d) - 2.0037508342789244E7d) / 6378137.0d;
        dPoint.x *= 57.29577951308232d;
        return dPoint;
    }
}
