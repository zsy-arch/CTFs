package com.loc;

/* compiled from: GeoHash.java */
/* loaded from: classes2.dex */
public final class by {
    private static final char[] a = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'j', 'k', 'm', 'n', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
    private static final int[] b = {16, 8, 4, 2, 1};

    public static final String a(double d, double d2) {
        StringBuilder sb = new StringBuilder();
        boolean z = true;
        int i = 0;
        int i2 = 0;
        double[] dArr = {-90.0d, 90.0d};
        double[] dArr2 = {-180.0d, 180.0d};
        while (sb.length() < 6) {
            if (z) {
                double d3 = (dArr2[0] + dArr2[1]) / 2.0d;
                if (d2 > d3) {
                    i2 |= b[i];
                    dArr2[0] = d3;
                } else {
                    dArr2[1] = d3;
                }
            } else {
                double d4 = (dArr[0] + dArr[1]) / 2.0d;
                if (d > d4) {
                    i2 |= b[i];
                    dArr[0] = d4;
                } else {
                    dArr[1] = d4;
                }
            }
            z = !z;
            if (i < 4) {
                i++;
            } else {
                sb.append(a[i2]);
                i = 0;
                i2 = 0;
            }
        }
        return sb.toString();
    }
}
