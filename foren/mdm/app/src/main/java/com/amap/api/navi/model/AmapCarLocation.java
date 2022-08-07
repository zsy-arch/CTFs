package com.amap.api.navi.model;

import com.autonavi.rtbt.CarLocation;

/* loaded from: classes.dex */
public class AmapCarLocation {
    public int m_CarDir;
    public double m_Latitude;
    public double m_Longitude;
    public int m_MatchStatus;
    public int m_Speed;

    public AmapCarLocation(CarLocation carLocation) {
        this.m_Longitude = carLocation.m_Longitude;
        this.m_Latitude = carLocation.m_Latitude;
        this.m_CarDir = carLocation.m_CarDir;
        this.m_Speed = carLocation.m_Speed;
        this.m_MatchStatus = carLocation.m_MatchStatus;
    }

    public AmapCarLocation(com.autonavi.wtbt.CarLocation carLocation) {
        this.m_Longitude = carLocation.m_Longitude;
        this.m_Latitude = carLocation.m_Latitude;
        this.m_CarDir = carLocation.m_CarDir;
        this.m_Speed = carLocation.m_Speed;
        this.m_MatchStatus = carLocation.m_MatchStatus;
    }
}
