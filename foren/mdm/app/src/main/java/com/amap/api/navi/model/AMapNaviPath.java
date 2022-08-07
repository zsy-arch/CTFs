package com.amap.api.navi.model;

import com.amap.api.maps.model.LatLngBounds;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
public class AMapNaviPath {
    private int allLength;
    private int allTime;
    private LatLngBounds bounds;
    private List<AMapNaviCameraInfo> cameras;
    private NaviLatLng center;
    private int[] cityAdcodeList;
    private NaviLatLng endPoi;
    private String labels;
    private List<NaviLatLng> list;
    private List<AMapNaviStep> mSteps;
    private AMapRestrictionInfo restrictionInfo;
    private NaviLatLng startPoi;
    private int stepsCount;
    private int strategy;
    private List<NaviLatLng> wayPoi;
    public int[] wayPointIndex = null;
    private List<AMapTrafficStatus> trafficStatuses = new ArrayList();
    private int tollCost = 0;

    public void setCameras(List<AMapNaviCameraInfo> list) {
        this.cameras = list;
    }

    public List<AMapNaviCameraInfo> getAllCameras() {
        return this.cameras;
    }

    public int[] getWayPointIndex() {
        return this.wayPointIndex;
    }

    public List<NaviLatLng> getWayPoint() {
        return this.wayPoi;
    }

    public void setWayPoint(List<NaviLatLng> list) {
        this.wayPoi = list;
    }

    public NaviLatLng getStartPoint() {
        return this.startPoi;
    }

    public void setStartPoint(NaviLatLng naviLatLng) {
        this.startPoi = naviLatLng;
    }

    public NaviLatLng getEndPoint() {
        return this.endPoi;
    }

    public void setEndPoint(NaviLatLng naviLatLng) {
        this.endPoi = naviLatLng;
    }

    public NaviLatLng getCenterForPath() {
        return this.center;
    }

    public void setCenter(NaviLatLng naviLatLng) {
        this.center = naviLatLng;
    }

    public LatLngBounds getBoundsForPath() {
        return this.bounds;
    }

    public void setBounds(LatLngBounds latLngBounds) {
        this.bounds = latLngBounds;
    }

    public List<AMapNaviStep> getSteps() {
        return this.mSteps;
    }

    public void setSteps(List<AMapNaviStep> list) {
        this.mSteps = list;
    }

    public List<NaviLatLng> getCoordList() {
        return this.list;
    }

    public void setList(List<NaviLatLng> list) {
        this.list = list;
    }

    AMapNaviStep getStep(int i) {
        return null;
    }

    public int getAllLength() {
        return this.allLength;
    }

    public void setAllLength(int i) {
        this.allLength = i;
    }

    public int getStrategy() {
        return this.strategy;
    }

    public void setStrategy(int i) {
        this.strategy = i;
    }

    public int getAllTime() {
        return this.allTime;
    }

    public void setAllTime(int i) {
        this.allTime = i;
    }

    public int getStepsCount() {
        return this.stepsCount;
    }

    public void setStepsCount(int i) {
        this.stepsCount = i;
    }

    public int getTollCost() {
        return this.tollCost;
    }

    public void setTollCost(int i) {
        this.tollCost = i;
    }

    public void setTrafficStatus(List<AMapTrafficStatus> list) {
        this.trafficStatuses = list;
    }

    public List<AMapTrafficStatus> getTrafficStatuses() {
        return this.trafficStatuses;
    }

    public void setLabels(String str) {
        this.labels = str;
    }

    public String getLabels() {
        return this.labels;
    }

    public void setRestrictionInfo(AMapRestrictionInfo aMapRestrictionInfo) {
        this.restrictionInfo = aMapRestrictionInfo;
    }

    public AMapRestrictionInfo getRestrictionInfo() {
        return this.restrictionInfo;
    }

    public void setCityAdcodeList(int[] iArr) {
        this.cityAdcodeList = iArr;
    }

    public int[] getCityAdcodeList() {
        return this.cityAdcodeList;
    }
}
