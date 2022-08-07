package com.autonavi.amap.mapcore;

import android.graphics.Point;

/* loaded from: classes.dex */
public class MapProjection {
    public static void lonlat2Geo(double d, double d2, IPoint iPoint) {
        Point LatLongToPixels = VirtualEarthProjection.LatLongToPixels(d2, d, 20);
        iPoint.x = LatLongToPixels.x;
        iPoint.y = LatLongToPixels.y;
    }

    public static void geo2LonLat(int i, int i2, DPoint dPoint) {
        DPoint PixelsToLatLong = VirtualEarthProjection.PixelsToLatLong(i, i2, 20);
        dPoint.x = PixelsToLatLong.x;
        dPoint.y = PixelsToLatLong.y;
        PixelsToLatLong.recycle();
    }
}
