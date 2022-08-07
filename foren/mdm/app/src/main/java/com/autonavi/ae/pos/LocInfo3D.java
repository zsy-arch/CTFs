package com.autonavi.ae.pos;

import java.io.Serializable;

/* loaded from: classes.dex */
public class LocInfo3D implements Serializable {
    public double alt;
    public double altAcc;
    public double course;
    public double courseAcc;
    public byte fromWay;
    public double height;
    public int isSimulate;
    public int isUse;
    public int linkCur;
    public byte linkType;
    public double posAcc;
    public int posCur;
    public short rev;
    public int roadDir;
    public int roadID;
    public int roadLevel;
    public double speed;
    public LocObjectId stNearRoadId;
    public LocMapPoint stPos;
    public LocMapPoint64 stPosEx;
    public LocObjectId stRoadId;
    public int turnFlag;

    public LocInfo3D() {
    }

    public LocInfo3D(int i, int i2, double d, double d2, double d3, double d4, double d5, double d6, double d7, int i3, int i4, int i5, int i6, byte b, byte b2, short s, int i7, int i8) {
        this.isUse = i;
        this.isSimulate = i2;
        this.stPos = this.stPos;
        this.stPosEx = this.stPosEx;
        this.course = d;
        this.alt = d2;
        this.speed = d3;
        this.height = d4;
        this.posAcc = d5;
        this.courseAcc = d6;
        this.altAcc = d7;
        this.roadLevel = i3;
        this.turnFlag = i4;
        this.roadDir = i5;
        this.stRoadId = this.stRoadId;
        this.stNearRoadId = this.stNearRoadId;
        this.roadID = i6;
        this.fromWay = b;
        this.linkType = b2;
        this.rev = s;
        this.linkCur = i7;
        this.posCur = i8;
    }

    public void setObjectValue(LocMapPoint locMapPoint, LocMapPoint64 locMapPoint64, LocObjectId locObjectId, LocObjectId locObjectId2) {
        this.stPos = locMapPoint;
        this.stPosEx = locMapPoint64;
        this.stRoadId = locObjectId;
        this.stNearRoadId = locObjectId2;
    }
}
