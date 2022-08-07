package com.amap.api.services.interfaces;

import com.amap.api.services.core.AMapException;
import com.amap.api.services.route.BusRouteResult;
import com.amap.api.services.route.DriveRouteResult;
import com.amap.api.services.route.RideRouteResult;
import com.amap.api.services.route.RouteSearch;
import com.amap.api.services.route.WalkRouteResult;

/* loaded from: classes.dex */
public interface IRouteSearch {
    BusRouteResult calculateBusRoute(RouteSearch.BusRouteQuery busRouteQuery) throws AMapException;

    void calculateBusRouteAsyn(RouteSearch.BusRouteQuery busRouteQuery);

    DriveRouteResult calculateDriveRoute(RouteSearch.DriveRouteQuery driveRouteQuery) throws AMapException;

    void calculateDriveRouteAsyn(RouteSearch.DriveRouteQuery driveRouteQuery);

    RideRouteResult calculateRideRoute(RouteSearch.RideRouteQuery rideRouteQuery) throws AMapException;

    void calculateRideRouteAsyn(RouteSearch.RideRouteQuery rideRouteQuery);

    WalkRouteResult calculateWalkRoute(RouteSearch.WalkRouteQuery walkRouteQuery) throws AMapException;

    void calculateWalkRouteAsyn(RouteSearch.WalkRouteQuery walkRouteQuery);

    void setRouteSearchListener(RouteSearch.OnRouteSearchListener onRouteSearchListener);
}
