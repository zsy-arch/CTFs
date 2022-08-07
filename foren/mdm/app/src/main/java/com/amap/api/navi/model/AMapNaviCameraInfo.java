package com.amap.api.navi.model;

import com.autonavi.ae.guide.model.NaviCamera;
import com.autonavi.ae.route.model.RouteCamera;

/* loaded from: classes.dex */
public class AMapNaviCameraInfo {
    private int cameraDistance;
    private int cameraSpeed;
    private int cameraType;
    private double x;
    private double y;

    public AMapNaviCameraInfo(NaviCamera naviCamera) {
        this.x = naviCamera.x;
        this.y = naviCamera.y;
        this.cameraType = naviCamera.cameraType;
        this.cameraSpeed = naviCamera.cameraSpeed;
        this.cameraDistance = naviCamera.cameraDistance;
    }

    public AMapNaviCameraInfo(RouteCamera routeCamera) {
        this.x = routeCamera.longitude;
        this.y = routeCamera.latitude;
        this.cameraType = routeCamera.cameraType;
        this.cameraSpeed = routeCamera.cameraSpeed;
        this.cameraDistance = 0;
    }

    public double getX() {
        return this.x;
    }

    public double getY() {
        return this.y;
    }

    public int getCameraDistance() {
        return this.cameraDistance;
    }

    public int getCameraSpeed() {
        return this.cameraSpeed;
    }

    public int getCameraType() {
        return this.cameraType;
    }
}
