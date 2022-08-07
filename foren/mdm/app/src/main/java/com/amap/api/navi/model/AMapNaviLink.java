package com.amap.api.navi.model;

import java.util.List;

/* loaded from: classes.dex */
public class AMapNaviLink {
    private List<NaviLatLng> mCoords;
    private boolean mIsTrafficLightIn;
    private int mLength;
    private int mRoadClass;
    private String mRoadName;
    private int mRoadType;
    private int mTime;

    public String getRoadName() {
        return this.mRoadName;
    }

    public void setRoadName(String str) {
        this.mRoadName = str;
    }

    public int getLength() {
        return this.mLength;
    }

    public void setLength(int i) {
        this.mLength = i;
    }

    public List<NaviLatLng> getCoords() {
        return this.mCoords;
    }

    public void setCoords(List<NaviLatLng> list) {
        this.mCoords = list;
    }

    public int getTime() {
        return this.mTime;
    }

    public void setTime(int i) {
        this.mTime = i;
    }

    public int getRoadClass() {
        return this.mRoadClass;
    }

    public void setRoadClass(int i) {
        this.mRoadClass = i;
    }

    public boolean getTrafficLights() {
        return this.mIsTrafficLightIn;
    }

    public void setTrafficLights(boolean z) {
        this.mIsTrafficLightIn = z;
    }

    public int getRoadType() {
        return this.mRoadType;
    }

    public void setRoadType(int i) {
        this.mRoadType = i;
    }
}
