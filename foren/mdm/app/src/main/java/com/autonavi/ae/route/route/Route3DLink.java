package com.autonavi.ae.route.route;

import com.autonavi.ae.route.model.Geo3DPoint;

/* loaded from: classes.dex */
public class Route3DLink {
    private long mPtr;

    public native Geo3DPoint[] getCoor();

    public native int getDist();

    public Route3DLink(long j) {
        this.mPtr = j;
    }

    public Route3DLink() {
    }

    public long getRoute3DLinkId() {
        return this.mPtr;
    }
}
