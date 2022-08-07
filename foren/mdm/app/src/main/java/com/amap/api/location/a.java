package com.amap.api.location;

import android.content.Context;
import android.text.TextUtils;
import com.loc.cy;
import com.loc.f;
import com.loc.r;
import java.io.File;
import java.math.BigDecimal;

/* compiled from: OffsetUtil.java */
/* loaded from: classes.dex */
public final class a {
    private static boolean b = false;
    static double a = 3.141592653589793d;

    private static double a(double d) {
        return Math.sin(3000.0d * d * (a / 180.0d)) * 2.0E-5d;
    }

    private static double a(double d, double d2) {
        return (Math.cos(d2 / 100000.0d) * (d / 18000.0d)) + (Math.sin(d / 100000.0d) * (d2 / 9000.0d));
    }

    public static DPoint a(Context context, double d, double d2) {
        if (context == null) {
            return null;
        }
        return a(context, new DPoint(d2, d));
    }

    public static DPoint a(Context context, DPoint dPoint) {
        if (context == null) {
            return null;
        }
        String a2 = r.a(context, "libwgs2gcj.so");
        if (!TextUtils.isEmpty(a2) && new File(a2).exists() && !b) {
            try {
                System.load(a2);
                b = true;
            } catch (UnsatisfiedLinkError e) {
            } catch (Throwable th) {
                f.a(th, "OffsetUtil", "offset");
            }
        }
        return a(dPoint, b);
    }

    public static DPoint a(DPoint dPoint) {
        if (dPoint == null) {
            return dPoint;
        }
        double d = 0.006401062d;
        double d2 = 0.0060424805d;
        DPoint dPoint2 = null;
        int i = 0;
        while (i < 2) {
            try {
                double longitude = dPoint.getLongitude();
                double latitude = dPoint.getLatitude();
                DPoint dPoint3 = new DPoint();
                double d3 = longitude - d;
                double d4 = latitude - d2;
                DPoint dPoint4 = new DPoint();
                dPoint4.setLongitude(c((Math.cos(b(d3) + Math.atan2(d4, d3)) * (a(d4) + Math.sqrt((d3 * d3) + (d4 * d4)))) + 0.0065d));
                dPoint4.setLatitude(c((Math.sin(b(d3) + Math.atan2(d4, d3)) * (a(d4) + Math.sqrt((d3 * d3) + (d4 * d4)))) + 0.006d));
                dPoint3.setLongitude(c((longitude + d3) - dPoint4.getLongitude()));
                dPoint3.setLatitude(c((latitude + d4) - dPoint4.getLatitude()));
                d = dPoint.getLongitude() - dPoint3.getLongitude();
                d2 = dPoint.getLatitude() - dPoint3.getLatitude();
                i++;
                dPoint2 = dPoint3;
            } catch (Throwable th) {
                f.a(th, "OffsetUtil", "B2G");
                return dPoint;
            }
        }
        return dPoint2;
    }

    private static DPoint a(DPoint dPoint, boolean z) {
        try {
            if (!f.a(dPoint.getLatitude(), dPoint.getLongitude())) {
                return dPoint;
            }
            double[] dArr = new double[2];
            if (!z) {
                dArr = cy.a(dPoint.getLongitude(), dPoint.getLatitude());
            } else if (CoordUtil.convertToGcj(new double[]{dPoint.getLongitude(), dPoint.getLatitude()}, dArr) != 0) {
                dArr = cy.a(dPoint.getLongitude(), dPoint.getLatitude());
            }
            return new DPoint(dArr[1], dArr[0]);
        } catch (Throwable th) {
            f.a(th, "OffsetUtil", "cover part2");
            return dPoint;
        }
    }

    private static double b(double d) {
        return Math.cos(3000.0d * d * (a / 180.0d)) * 3.0E-6d;
    }

    private static double b(double d, double d2) {
        return (Math.sin(d2 / 100000.0d) * (d / 18000.0d)) + (Math.cos(d / 100000.0d) * (d2 / 9000.0d));
    }

    public static DPoint b(Context context, DPoint dPoint) {
        try {
            double longitude = ((long) (dPoint.getLongitude() * 100000.0d)) % 36000000;
            double latitude = ((long) (dPoint.getLatitude() * 100000.0d)) % 36000000;
            int i = (int) ((-b(longitude, latitude)) + latitude);
            int i2 = (int) ((longitude > 0.0d ? 1 : -1) + (-a((int) ((-a(longitude, latitude)) + longitude), i)) + longitude);
            return a(context, new DPoint(((int) ((latitude > 0.0d ? 1 : -1) + ((-b(i2, i)) + latitude))) / 100000.0d, i2 / 100000.0d));
        } catch (Throwable th) {
            f.a(th, "OffsetUtil", "marbar2G");
            return dPoint;
        }
    }

    private static double c(double d) {
        return new BigDecimal(d).setScale(8, 4).doubleValue();
    }
}
