package com.amap.api.col;

import android.content.Context;
import android.text.TextUtils;
import com.amap.api.maps.model.LatLng;
import com.autonavi.amap.mapcore.CoordUtil;
import com.autonavi.amap.mapcore.DPoint;
import java.io.File;
import java.math.BigDecimal;

/* compiled from: OffsetUtil.java */
/* loaded from: classes.dex */
public class ab {
    private static boolean b = false;
    static double a = 3.141592653589793d;

    public static LatLng a(Context context, LatLng latLng) {
        if (context == null) {
            return null;
        }
        String a2 = gi.a(context, "libwgs2gcj.so");
        if (!TextUtils.isEmpty(a2) && new File(a2).exists() && !b) {
            try {
                System.load(a2);
                b = true;
            } catch (Throwable th) {
            }
        }
        DPoint a3 = a(DPoint.obtain(latLng.longitude, latLng.latitude), b);
        LatLng latLng2 = new LatLng(a3.y, a3.x, false);
        a3.recycle();
        return latLng2;
    }

    private static DPoint a(DPoint dPoint, boolean z) {
        try {
            if (!dp.a(dPoint.y, dPoint.x)) {
                return dPoint;
            }
            double[] dArr = new double[2];
            if (!z) {
                dArr = ir.a(dPoint.x, dPoint.y);
            } else if (CoordUtil.convertToGcj(new double[]{dPoint.x, dPoint.y}, dArr) != 0) {
                dArr = ir.a(dPoint.x, dPoint.y);
            }
            dPoint.recycle();
            dPoint = DPoint.obtain(dArr[0], dArr[1]);
            return dPoint;
        } catch (Throwable th) {
            return dPoint;
        }
    }

    public static LatLng b(Context context, LatLng latLng) {
        try {
            DPoint c = c(latLng.longitude, latLng.latitude);
            LatLng a2 = a(context, new LatLng(c.y, c.x, false));
            c.recycle();
            return a2;
        } catch (Throwable th) {
            th.printStackTrace();
            return latLng;
        }
    }

    public static double a(double d, double d2) {
        return (Math.cos(d2 / 100000.0d) * (d / 18000.0d)) + (Math.sin(d / 100000.0d) * (d2 / 9000.0d));
    }

    public static double b(double d, double d2) {
        return (Math.sin(d2 / 100000.0d) * (d / 18000.0d)) + (Math.cos(d / 100000.0d) * (d2 / 9000.0d));
    }

    private static DPoint c(double d, double d2) {
        double d3 = ((long) (100000.0d * d)) % 36000000;
        double d4 = ((long) (100000.0d * d2)) % 36000000;
        int i = (int) ((-b(d3, d4)) + d4);
        int i2 = (int) ((d3 > 0.0d ? 1 : -1) + (-a((int) ((-a(d3, d4)) + d3), i)) + d3);
        return DPoint.obtain(i2 / 100000.0d, ((int) ((d4 > 0.0d ? 1 : -1) + ((-b(i2, i)) + d4))) / 100000.0d);
    }

    public static LatLng a(LatLng latLng) {
        if (latLng != null) {
            try {
                DPoint a2 = a(latLng.longitude, latLng.latitude, 2);
                LatLng latLng2 = new LatLng(a2.y, a2.x, false);
                a2.recycle();
                return latLng2;
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
        return latLng;
    }

    private static double a(double d) {
        return Math.sin(3000.0d * d * (a / 180.0d)) * 2.0E-5d;
    }

    private static double b(double d) {
        return Math.cos(3000.0d * d * (a / 180.0d)) * 3.0E-6d;
    }

    private static DPoint d(double d, double d2) {
        DPoint obtain = DPoint.obtain();
        obtain.x = a((Math.cos(b(d) + Math.atan2(d2, d)) * (a(d2) + Math.sqrt((d * d) + (d2 * d2)))) + 0.0065d, 8);
        obtain.y = a((Math.sin(b(d) + Math.atan2(d2, d)) * (a(d2) + Math.sqrt((d * d) + (d2 * d2)))) + 0.006d, 8);
        return obtain;
    }

    private static double a(double d, int i) {
        return new BigDecimal(d).setScale(i, 4).doubleValue();
    }

    private static DPoint a(double d, double d2, int i) {
        double d3 = 0.006401062d;
        double d4 = 0.0060424805d;
        int i2 = 0;
        DPoint dPoint = null;
        while (i2 < i) {
            DPoint a2 = a(d, d2, d3, d4);
            d3 = d - a2.x;
            d4 = d2 - a2.y;
            i2++;
            dPoint = a2;
        }
        return dPoint;
    }

    private static DPoint a(double d, double d2, double d3, double d4) {
        DPoint obtain = DPoint.obtain();
        double d5 = d - d3;
        double d6 = d2 - d4;
        DPoint d7 = d(d5, d6);
        obtain.x = a((d5 + d) - d7.x, 8);
        obtain.y = a((d2 + d6) - d7.y, 8);
        return obtain;
    }
}
