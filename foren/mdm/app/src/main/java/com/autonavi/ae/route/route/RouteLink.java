package com.autonavi.ae.route.route;

import com.autonavi.ae.route.model.FormWay;
import com.autonavi.ae.route.model.LinkStatus;

/* loaded from: classes.dex */
public class RouteLink {
    private long mPtr;

    private native int getLinkCoorOffset();

    public native int get3DEndLinkIndex();

    public native int get3DStartLinkIndex();

    public native int getCurbParkingFlag();

    public native int getLinkCityCode();

    public native double[] getLinkCoor();

    public native int getLinkCoorNum();

    public native FormWay getLinkFormWay();

    public native int getLinkIsRestrict();

    public native int getLinkOwnership();

    public native int getLinkRoadClass();

    public native String getLinkRoadName();

    public native LinkStatus getLinkTrafficStatus();

    public native int getLinkType();

    public native long getTopoId64();

    public native boolean haveTrafficLights();

    public native boolean isToll();
}
