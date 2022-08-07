package com.amap.api.maps.utils;

import android.util.Pair;
import com.amap.api.maps.model.LatLng;
import com.autonavi.amap.mapcore.DPoint;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
public class SpatialRelationUtil {
    public static Pair<Integer, LatLng> calShortestDistancePoint(List<LatLng> list, LatLng latLng) {
        if (list == null || latLng == null || list.size() == 0) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        int i = 0;
        for (LatLng latLng2 : list) {
            arrayList.add(DPoint.obtain(latLng2.latitude, latLng2.longitude));
            if (latLng2.equals(latLng)) {
                return new Pair<>(Integer.valueOf(i), latLng);
            }
            i++;
        }
        Pair<Integer, DPoint> calShortestDistancePoint = calShortestDistancePoint(arrayList, DPoint.obtain(latLng.latitude, latLng.longitude));
        if (calShortestDistancePoint != null) {
            return new Pair<>(calShortestDistancePoint.first, new LatLng(((DPoint) calShortestDistancePoint.second).x, ((DPoint) calShortestDistancePoint.second).y));
        }
        return null;
    }

    public static Pair<Integer, DPoint> calShortestDistancePoint(List<DPoint> list, DPoint dPoint) {
        if (list.size() < 2) {
            return null;
        }
        Pair<Integer, DPoint> pair = null;
        DPoint dPoint2 = null;
        double d = 0.0d;
        for (int i = 0; i < list.size() - 1; i++) {
            if (i == 0) {
                DPoint dPoint3 = list.get(i);
                if (dPoint3.equals(dPoint)) {
                    return new Pair<>(Integer.valueOf(i), dPoint);
                }
                dPoint2 = dPoint3;
            }
            dPoint2 = list.get(i + 1);
            if (dPoint2.equals(dPoint)) {
                return new Pair<>(Integer.valueOf(i + 1), dPoint);
            }
            Pair<Double, DPoint> pointToSegDist = pointToSegDist(dPoint.x, dPoint.y, dPoint2.x, dPoint2.y, dPoint2.x, dPoint2.y);
            if (pair == null) {
                d = ((Double) pointToSegDist.first).doubleValue();
                pair = new Pair<>(Integer.valueOf(i), pointToSegDist.second);
            } else if (d > ((Double) pointToSegDist.first).doubleValue()) {
                d = ((Double) pointToSegDist.first).doubleValue();
                pair = new Pair<>(Integer.valueOf(i), pointToSegDist.second);
            } else {
                d = d;
                pair = pair;
            }
        }
        return pair;
    }

    private static Pair<Double, DPoint> pointToSegDist(double d, double d2, double d3, double d4, double d5, double d6) {
        double d7 = ((d5 - d3) * (d - d3)) + ((d6 - d4) * (d2 - d4));
        if (d7 <= 0.0d) {
            return new Pair<>(Double.valueOf(Math.sqrt(((d - d3) * (d - d3)) + ((d2 - d4) * (d2 - d4)))), new DPoint(d3, d4));
        }
        double d8 = ((d5 - d3) * (d5 - d3)) + ((d6 - d4) * (d6 - d4));
        if (d7 >= d8) {
            return new Pair<>(Double.valueOf(Math.sqrt(((d - d5) * (d - d5)) + ((d2 - d6) * (d2 - d6)))), new DPoint(d5, d6));
        }
        double d9 = d7 / d8;
        double d10 = ((d5 - d3) * d9) + d3;
        double d11 = d4 + (d9 * (d6 - d4));
        return new Pair<>(Double.valueOf(Math.sqrt(((d - d10) * (d - d10)) + ((d11 - d2) * (d11 - d2)))), new DPoint(d10, d11));
    }
}
