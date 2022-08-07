package com.autonavi.ae.pos;

/* loaded from: classes.dex */
public class LocObjectId {
    public short adareaID;
    public char layerID;
    public int meshID;
    public int objectID;
    public char rev;

    public LocObjectId(char c, char c2, short s, int i, int i2) {
        this.layerID = c;
        this.rev = c2;
        this.adareaID = s;
        this.meshID = i;
        this.objectID = i2;
    }
}
