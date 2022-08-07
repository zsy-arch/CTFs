package com.amap.api.navi.model;

/* loaded from: classes.dex */
public class AMapNaviGuide {
    private NaviLatLng coord;
    private int m_Icon;
    private int m_Length;
    private String m_Name;
    private int m_UseTime;
    public static int IconTypeNone = 0;
    public static int IconTypeDefault = 1;
    public static int IconTypeLeft = 2;
    public static int IconTypeRight = 3;
    public static int IconTypeLeftFront = 4;
    public static int IconTypeRightFront = 5;
    public static int IconTypeLeftBack = 6;
    public static int IconTypeRightBack = 7;
    public static int IconTypeLeftAndAround = 8;
    public static int IconTypeStraight = 9;
    public static int IconTypeArrivedWayPoint = 10;
    public static int IconTypeEnterRoundabout = 11;
    public static int IconTypeOutRoundabout = 12;
    public static int IconTypeArrivedServiceArea = 13;
    public static int IconTypeArrivedTollGate = 14;
    public static int IconTypeArrivedDestination = 15;
    public static int IconTypeArrivedTunnel = 16;
    public static int IconTypeCrosswalk = 17;
    public static int IconTypeOverpass = 18;
    public static int IconTypeUnderpass = 19;
    public static int IconTypeSquare = 20;
    public static int IconTypePark = 21;
    public static int IconTypeStaircase = 22;
    public static int IconTypeLift = 23;

    public AMapNaviGuide(NaviGuide naviGuide) {
        this.m_Length = naviGuide.m_Length;
        this.m_Icon = naviGuide.m_Icon;
        this.m_Name = naviGuide.m_Name;
        this.coord = naviGuide.getCoord();
    }

    public AMapNaviGuide() {
    }

    public NaviLatLng getCoord() {
        return this.coord;
    }

    public void setCoord(NaviLatLng naviLatLng) {
        this.coord = naviLatLng;
    }

    public int getLength() {
        return this.m_Length;
    }

    public void setLength(int i) {
        this.m_Length = i;
    }

    public int getIconType() {
        return this.m_Icon;
    }

    public void setIconType(int i) {
        this.m_Icon = i;
    }

    public String getName() {
        return this.m_Name;
    }

    public void setName(String str) {
        this.m_Name = str;
    }

    public int getTime() {
        return this.m_UseTime;
    }

    public void setTime(int i) {
        this.m_UseTime = i;
    }
}
