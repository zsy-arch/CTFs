package com.autonavi.ae.pos;

import java.io.Serializable;

/* loaded from: classes.dex */
public class LocInfo2D implements Serializable {
    public double alt;
    public double altAcc;
    public byte bindFlag;
    public double course;
    public double courseAcc;
    public int day;
    public double distFromHeadLine;
    public byte fromWay;
    public int hour;
    public int isHLocData;
    public byte isOnGuideRoad;
    public int isSimulate;
    public int isUse;
    public byte lineType;
    public int linkCur;
    public byte linkType;
    public int minute;
    public int mouth;
    public LocObjectId nearRoadId;
    public double posAcc;
    public int postCur;
    public byte proType;
    public short rev;
    public int roadDir;
    public int roadID;
    public LocObjectId roadId;
    public int roadLevel;
    public int second;
    public int segIdx;
    public int segmCur;
    public double showPosAcc;
    public int sourType;
    public double speed;
    public LocMapPoint stDoorInPos;
    public LocMapPoint stPos;
    public LocMapPoint stPrjPos;
    public String strFloor;
    public String strPoiid;
    public long ticktime;
    public int turnFlag;
    public int year;

    public LocInfo2D() {
    }

    public LocInfo2D(int i, int i2, int i3, double d, double d2, double d3, double d4, double d5, double d6, double d7, int i4, int i5, int i6, int i7, int i8, byte b, byte b2, byte b3, byte b4, byte b5, byte b6, short s, double d8, int i9, int i10, int i11, int i12, long j, int i13, int i14, int i15, int i16, int i17, int i18) {
        this.isUse = i;
        this.isSimulate = i2;
        this.sourType = i3;
        this.stPos = this.stPos;
        this.stPrjPos = this.stPrjPos;
        this.course = d;
        this.alt = d2;
        this.speed = d3;
        this.posAcc = d4;
        this.showPosAcc = d5;
        this.courseAcc = d6;
        this.altAcc = d7;
        this.roadLevel = i4;
        this.turnFlag = i5;
        this.roadDir = i6;
        this.roadId = this.roadId;
        this.nearRoadId = this.nearRoadId;
        this.roadID = i7;
        this.segIdx = i8;
        this.fromWay = b;
        this.lineType = b2;
        this.linkType = b3;
        this.proType = b4;
        this.bindFlag = b5;
        this.isOnGuideRoad = b6;
        this.rev = s;
        this.distFromHeadLine = d8;
        this.isHLocData = i9;
        this.segmCur = i10;
        this.linkCur = i11;
        this.postCur = i12;
        this.ticktime = j;
        this.year = i13;
        this.mouth = this.mouth;
        this.day = i15;
        this.hour = i16;
        this.minute = i17;
        this.second = i18;
    }

    public void setStringValues(String str, String str2) {
        this.strPoiid = str;
        this.strFloor = str2;
    }

    public void setObjectValue(LocMapPoint locMapPoint, LocMapPoint locMapPoint2, LocObjectId locObjectId, LocObjectId locObjectId2) {
        this.stPos = locMapPoint;
        this.stPrjPos = locMapPoint2;
        this.roadId = locObjectId;
        this.nearRoadId = locObjectId2;
    }

    public void setStDoorInPos(LocMapPoint locMapPoint) {
        this.stDoorInPos = locMapPoint;
    }
}
