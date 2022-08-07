package com.amap.api.maps;

import android.content.Context;
import com.amap.api.col.ab;
import com.amap.api.col.dp;
import com.amap.api.col.gr;
import com.amap.api.maps.model.LatLng;

/* loaded from: classes.dex */
public class CoordinateConverter {
    private Context a;
    private CoordType b = null;
    private LatLng c = null;

    /* loaded from: classes.dex */
    public enum CoordType {
        BAIDU,
        MAPBAR,
        GPS,
        MAPABC,
        SOSOMAP,
        ALIYUN,
        GOOGLE
    }

    public CoordinateConverter(Context context) {
        this.a = context;
    }

    public CoordinateConverter from(CoordType coordType) {
        this.b = coordType;
        return this;
    }

    public CoordinateConverter coord(LatLng latLng) {
        this.c = latLng;
        return this;
    }

    public LatLng convert() {
        LatLng latLng = null;
        if (this.b == null || this.c == null) {
            return null;
        }
        try {
            switch (this.b) {
                case BAIDU:
                    latLng = ab.a(this.c);
                    break;
                case MAPBAR:
                    latLng = ab.b(this.a, this.c);
                    break;
                case MAPABC:
                case SOSOMAP:
                case ALIYUN:
                case GOOGLE:
                    latLng = this.c;
                    break;
                case GPS:
                    latLng = ab.a(this.a, this.c);
                    break;
            }
            return latLng;
        } catch (Throwable th) {
            th.printStackTrace();
            gr.b(th, "CoordinateConverter", "convert");
            return this.c;
        }
    }

    public static boolean isAMapDataAvailable(double d, double d2) {
        return dp.a(d, d2);
    }
}
