package com.amap.api.navi.model;

import com.autonavi.wtbt.NaviGuideItem;

/* loaded from: classes.dex */
public class NaviGuide extends NaviGuideItem {
    public AMapNaviGuide aMapNaviGuide;
    private NaviLatLng coord;

    public NaviGuide(NaviGuideItem naviGuideItem) {
        this.m_Length = naviGuideItem.m_Length;
        this.m_Icon = naviGuideItem.m_Icon;
        this.m_Name = naviGuideItem.m_Name;
        this.m_UseTime = naviGuideItem.m_UseTime;
        this.coord = new NaviLatLng(naviGuideItem.m_Latitude, naviGuideItem.m_Longitude);
        this.aMapNaviGuide = new AMapNaviGuide(this);
    }

    public NaviGuide(com.autonavi.rtbt.NaviGuideItem naviGuideItem) {
        this.m_Length = naviGuideItem.m_Length;
        this.m_Icon = naviGuideItem.m_Icon;
        this.m_Name = naviGuideItem.m_Name;
        this.m_UseTime = naviGuideItem.m_UseTime;
        this.coord = new NaviLatLng(naviGuideItem.m_Latitude, naviGuideItem.m_Longitude);
        this.aMapNaviGuide = new AMapNaviGuide(this);
    }

    public NaviGuide() {
        this.aMapNaviGuide = new AMapNaviGuide();
    }

    public NaviLatLng getCoord() {
        return this.coord;
    }

    public void setCoord(NaviLatLng naviLatLng) {
        this.coord = naviLatLng;
        this.aMapNaviGuide.setCoord(this.coord);
        this.m_Latitude = naviLatLng.getLatitude();
        this.m_Longitude = naviLatLng.getLongitude();
    }

    public int getLength() {
        return this.m_Length;
    }

    public void setLength(int i) {
        this.m_Length = i;
        this.aMapNaviGuide.setLength(this.m_Length);
    }

    public int getIconType() {
        return this.m_Icon;
    }

    public void setIconType(int i) {
        this.m_Icon = i;
        this.aMapNaviGuide.setIconType(this.m_Icon);
    }

    public String getName() {
        return this.m_Name;
    }

    public void setName(String str) {
        this.m_Name = str;
        this.aMapNaviGuide.setName(this.m_Name);
    }

    public int getTime() {
        return this.m_UseTime;
    }

    public void setTime(int i) {
        this.m_UseTime = i;
        this.aMapNaviGuide.setTime(this.m_UseTime);
    }
}
