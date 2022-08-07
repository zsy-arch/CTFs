package com.amap.api.navi.model;

/* loaded from: classes.dex */
public class AMapLaneInfo {
    int laneTypeId = 0;

    public boolean isRecommended() {
        return !getLaneTypeIdHexString().endsWith("F");
    }

    public int getLaneTypeId() {
        return this.laneTypeId;
    }

    public void setLaneTypeId(int i) {
        this.laneTypeId = i;
    }

    public String getLaneTypeIdHexString() {
        return String.format("%1$02X", Integer.valueOf(this.laneTypeId));
    }
}
