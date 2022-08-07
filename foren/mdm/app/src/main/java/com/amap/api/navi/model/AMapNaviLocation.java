package com.amap.api.navi.model;

/* loaded from: classes.dex */
public class AMapNaviLocation {
    private float accuracy;
    private double altitude;
    private float bearing;
    private NaviLatLng coord;
    private int matchStatus;
    private float speed;
    private long time;

    public int getMatchStatus() {
        return this.matchStatus;
    }

    public void setMatchStatus(int i) {
        this.matchStatus = i;
    }

    public float getAccuracy() {
        return this.accuracy;
    }

    public void setAccuracy(float f) {
        this.accuracy = f;
    }

    public Double getAltitude() {
        return Double.valueOf(this.altitude);
    }

    public void setAltitude(double d) {
        this.altitude = d;
    }

    public float getBearing() {
        return this.bearing;
    }

    public void setBearing(float f) {
        this.bearing = f;
    }

    public float getSpeed() {
        return this.speed;
    }

    public void setSpeed(float f) {
        this.speed = f;
    }

    public Long getTime() {
        return Long.valueOf(this.time);
    }

    public void setTime(long j) {
        this.time = j;
    }

    public boolean isMatchNaviPath() {
        return this.matchStatus != 0;
    }

    public NaviLatLng getCoord() {
        return this.coord;
    }

    public void setCoord(NaviLatLng naviLatLng) {
        this.coord = naviLatLng;
    }
}
