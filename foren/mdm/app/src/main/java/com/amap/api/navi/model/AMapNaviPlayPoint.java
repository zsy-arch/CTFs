package com.amap.api.navi.model;

/* loaded from: classes.dex */
public class AMapNaviPlayPoint implements Comparable<AMapNaviPlayPoint> {
    public int distancetoTurn;
    public int frontPtIndex;
    public boolean ispass;
    public NaviLatLng latLng;
    public String playText;
    public int playlevel;

    public int compareTo(AMapNaviPlayPoint aMapNaviPlayPoint) {
        return this.distancetoTurn - aMapNaviPlayPoint.distancetoTurn;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof AMapNaviPlayPoint) || ((AMapNaviPlayPoint) obj).frontPtIndex != this.frontPtIndex) {
            return super.equals(obj);
        }
        return true;
    }

    public int hashCode() {
        return super.hashCode();
    }
}
