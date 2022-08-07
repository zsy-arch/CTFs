package com.amap.api.navi.model;

import com.autonavi.ae.guide.model.NoNaviCongestionInfo;

/* loaded from: classes.dex */
public class AimLessModeCongestionInfo {
    private final AMapCongestionLink[] amapCongestionLinks;
    private int congestionStatus;
    private double eventLat;
    private double eventLon;
    private int eventType;
    private int length;
    private String roadName;
    private int time;

    public AimLessModeCongestionInfo(NoNaviCongestionInfo noNaviCongestionInfo) {
        this.time = noNaviCongestionInfo.etaTime;
        this.length = noNaviCongestionInfo.length;
        this.roadName = noNaviCongestionInfo.roadName;
        this.congestionStatus = noNaviCongestionInfo.congestionStatus;
        this.eventType = noNaviCongestionInfo.eventType;
        this.eventLon = noNaviCongestionInfo.eventLon;
        this.eventLat = noNaviCongestionInfo.eventLat;
        this.amapCongestionLinks = new AMapCongestionLink[noNaviCongestionInfo.linkDatas.length];
        for (int i = 0; i < this.amapCongestionLinks.length; i++) {
            this.amapCongestionLinks[i] = new AMapCongestionLink(noNaviCongestionInfo.linkDatas[i]);
        }
    }

    public AMapCongestionLink[] getAmapCongestionLinks() {
        return this.amapCongestionLinks;
    }

    public int getEventType() {
        return this.eventType;
    }

    public void setEventType(int i) {
        this.eventType = i;
    }

    public String getRoadName() {
        return this.roadName;
    }

    public void setRoadName(String str) {
        this.roadName = str;
    }

    public int getLength() {
        return this.length;
    }

    public void setLength(int i) {
        this.length = i;
    }

    public int getCongestionStatus() {
        return this.congestionStatus;
    }

    public void setCongestionStatus(int i) {
        this.congestionStatus = i;
    }

    public double getEventLon() {
        return this.eventLon;
    }

    public void setEventLon(double d) {
        this.eventLon = d;
    }

    public double getEventLat() {
        return this.eventLat;
    }

    public void setEventLat(double d) {
        this.eventLat = d;
    }

    public int getTime() {
        return this.time;
    }

    public void setTime(int i) {
        this.time = i;
    }
}
