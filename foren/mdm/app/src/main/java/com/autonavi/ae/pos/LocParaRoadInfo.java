package com.autonavi.ae.pos;

import java.io.Serializable;

/* loaded from: classes.dex */
public class LocParaRoadInfo implements Serializable {
    public int nType;
    public LocObjectId stRoadId;
    public char u8FromWay;
    public char u8LinkType;
    public int unRoadID;

    public LocParaRoadInfo(LocObjectId locObjectId, int i, int i2, char c, char c2) {
        this.stRoadId = locObjectId;
        this.unRoadID = i;
        this.nType = i2;
        this.u8FromWay = c;
        this.u8LinkType = c2;
    }

    public LocParaRoadInfo() {
    }
}
