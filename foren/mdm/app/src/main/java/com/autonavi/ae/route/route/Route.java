package com.autonavi.ae.route.route;

import com.autonavi.ae.route.model.AbnormalSec;
import com.autonavi.ae.route.model.AvoidJamArea;
import com.autonavi.ae.route.model.GeoPoint;
import com.autonavi.ae.route.model.GroupSegment;
import com.autonavi.ae.route.model.JamInfo;
import com.autonavi.ae.route.model.JamSegment;
import com.autonavi.ae.route.model.LabelInfo;
import com.autonavi.ae.route.model.LineItem;
import com.autonavi.ae.route.model.RestAreaInfo;
import com.autonavi.ae.route.model.RestrictionInfo;
import com.autonavi.ae.route.model.RouteCamera;
import com.autonavi.ae.route.model.RouteIncident;
import com.autonavi.ae.route.model.TipInfo;
import com.autonavi.ae.route.model.TmcBarItem;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes.dex */
public class Route {
    private long mPtr;
    public Map<Object, Object> mRouteInfo = new HashMap();
    private TmcBarItem[] tmcBarItem;

    private native TmcBarItem[] getTmcBar();

    private native RouteSegment nativeGetSegment(int i);

    private native int nativeUpdateTmcBar(TmcBarItem[] tmcBarItemArr);

    public native void addRef();

    public native double[] buildRarefyPoint();

    public native double[] buildRarefyPoint(int i, double d, double d2);

    public native void decreaseRefAndRelease();

    public native void destroy();

    public native GeoPoint findCarToFootPoint();

    public native AbnormalSec getAbnormalSection(int i);

    public native int getAbnormalSectionCount();

    public native int getAbnormalState();

    public native RouteCamera[] getAllCamera();

    public native GeoPoint[] getAllTrafficLight();

    public native long[] getAlongRoadID(int i, int i2);

    public native AvoidJamArea getAvoidJamArea();

    public native int getBypassLimitedRoad();

    public native int[] getCityAdcodeList();

    public native GeoPoint getClosestPoint(double d, double d2);

    public native int getDiffToTMCRoute();

    public native GeoPoint getEndPoint();

    public native GroupSegment[] getGroupSegmentList();

    public native RouteIncident[] getInRouteIncident();

    public native JamInfo[] getJamInfoList();

    public native JamSegment[] getJamSegment();

    public native LineItem[] getLineItems();

    public native String getNaviID();

    public native LineItem[] getNaviLineItems();

    public native LabelInfo[] getPathLabel();

    public native RestAreaInfo[] getRestAreas(int i, int i2);

    public native RestrictionInfo getRestrictionInfo();

    public native Route3D getRoute3D();

    public native double[] getRouteBound();

    public native RouteIncident[] getRouteIncident();

    public native int getRouteLength();

    public native int getRouteStrategy();

    public native int getRouteTime();

    public native int getSegmentCount();

    public native GeoPoint getStartPoint();

    public native TipInfo getTip();

    public native int getTollCost();

    public native int getTrafficLightNum();

    public native GeoPoint[] getVIAPoints();

    public native boolean isOnline();

    public native boolean isTruckPath();

    public native void setTruckPathFlag(boolean z);

    public Route() {
    }

    public Route(long j) {
        this.mPtr = j;
    }

    public long getRouteId() {
        return this.mPtr;
    }

    public TmcBarItem[] getTmcBarItem() {
        return this.tmcBarItem != null ? this.tmcBarItem : getTmcBar();
    }

    public int updateTmcBar(TmcBarItem[] tmcBarItemArr) {
        this.tmcBarItem = tmcBarItemArr;
        return nativeUpdateTmcBar(tmcBarItemArr);
    }

    public RouteSegment getSegment(int i) {
        return nativeGetSegment(i);
    }

    public LineItem[] getLineItems(TmcBarItem[] tmcBarItemArr) {
        updateTmcBar(this.tmcBarItem);
        return getNaviLineItems();
    }
}
