package com.amap.api.maps.model;

/* loaded from: classes.dex */
public class PoiPara {
    private LatLng a;
    private String b;

    public LatLng getCenter() {
        return this.a;
    }

    public void setCenter(LatLng latLng) {
        this.a = latLng;
    }

    public String getKeywords() {
        return this.b;
    }

    public void setKeywords(String str) {
        this.b = str;
    }
}
