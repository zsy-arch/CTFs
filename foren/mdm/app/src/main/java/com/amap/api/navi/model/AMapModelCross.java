package com.amap.api.navi.model;

/* loaded from: classes.dex */
public class AMapModelCross {
    private byte[] picBuf1;
    private byte[] picBuf2;
    private int picFormat;

    public AMapModelCross(int i, byte[] bArr, byte[] bArr2) {
        this.picFormat = i;
        this.picBuf1 = bArr;
        this.picBuf2 = bArr2;
    }

    public byte[] getPicBuf1() {
        return this.picBuf1;
    }

    public byte[] getPicBuf2() {
        return this.picBuf2;
    }

    public int getPicFormat() {
        return this.picFormat;
    }
}
