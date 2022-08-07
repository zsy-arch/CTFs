package com.autonavi.ae.route.route;

/* loaded from: classes.dex */
public class Route3D {
    private long mPtr;

    public native Route3DLink getRoute3DLink(int i);

    public native int getRoute3DLinkNum();

    public Route3D() {
        this.mPtr = 0L;
    }

    public Route3D(long j) {
        this.mPtr = 0L;
        this.mPtr = j;
    }

    public long getRoute3DId() {
        return this.mPtr;
    }
}
