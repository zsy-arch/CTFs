package com.autonavi.ae.route.route;

import com.autonavi.ae.route.model.GeoPoint;

/* loaded from: classes.dex */
public class RouteSegment {
    private long mPtr;

    private native RouteLink nativeGetLink(int i);

    public native int getAssistAction();

    public native int getLinkCount();

    public native int getMainAction();

    public native int getSegChargeLength();

    public native double[] getSegCoor();

    public native int getSegLength();

    public native int getSegTime();

    public native int getSegTollCost();

    public native String getSegTollPathName();

    public native GeoPoint getStartPoint();

    public native int getTrafficLightNum();

    public native boolean isRightPassArea();

    public long getSegmentId() {
        return this.mPtr;
    }

    public RouteLink getLink(int i) {
        return nativeGetLink(i);
    }
}
