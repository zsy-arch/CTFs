package com.amap.api.maps.model;

import com.amap.api.col.dt;
import com.autonavi.amap.mapcore.DPoint;

/* loaded from: classes.dex */
public class WeightedLatLng {
    public static final double DEFAULT_INTENSITY = 1.0d;
    private DPoint a;
    public final double intensity;
    public final LatLng latLng;

    public WeightedLatLng(LatLng latLng, double d) {
        if (latLng == null) {
            throw new IllegalArgumentException("latLng can not null");
        }
        this.latLng = latLng;
        this.a = dt.a(latLng);
        if (d >= 0.0d) {
            this.intensity = d;
        } else {
            this.intensity = 1.0d;
        }
    }

    public WeightedLatLng(LatLng latLng) {
        this(latLng, 1.0d);
    }

    public DPoint getPoint() {
        return this.a;
    }
}
