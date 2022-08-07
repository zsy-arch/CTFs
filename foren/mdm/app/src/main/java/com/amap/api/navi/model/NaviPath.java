package com.amap.api.navi.model;

import com.amap.api.maps.model.LatLngBounds;
import com.autonavi.ae.route.model.RestrictionInfo;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
public class NaviPath {
    private int allLength;
    private int allTime;
    public LatLngBounds bounds;
    private List<AMapNaviCameraInfo> cameras;
    public NaviLatLng center;
    private NaviLatLng endPoi;
    private long id;
    private List<NaviLatLng> list;
    private List<AMapNaviStep> listStep;
    private NaviLatLng startPoi;
    private int stepsCount;
    private int strategy;
    private List<NaviLatLng> wayPoi;
    public AMapNaviPath amapNaviPath = new AMapNaviPath();
    private NaviLatLng maxCoordForPath = new NaviLatLng(0.0d, 0.0d);
    private NaviLatLng minCoordForPath = new NaviLatLng(2.147483647E9d, 2.147483647E9d);
    private int tollCost = 0;
    private List<AMapNaviGuide> guideList = new ArrayList();

    public void setCameras(List<AMapNaviCameraInfo> list) {
        this.cameras = list;
        this.amapNaviPath.setCameras(list);
    }

    public List<AMapNaviCameraInfo> getCameras() {
        return this.cameras;
    }

    public long getId() {
        return this.id;
    }

    public void setId(long j) {
        this.id = j;
    }

    public List<NaviLatLng> getWayPoint() {
        return this.wayPoi;
    }

    public void setWayPoint(List<NaviLatLng> list) {
        this.wayPoi = list;
        this.amapNaviPath.setWayPoint(list);
    }

    public NaviLatLng getStartPoint() {
        return this.startPoi;
    }

    public void setStartPoint(NaviLatLng naviLatLng) {
        this.startPoi = naviLatLng;
        this.amapNaviPath.setStartPoint(naviLatLng);
    }

    public NaviLatLng getEndPoint() {
        return this.endPoi;
    }

    public void setEndPoint(NaviLatLng naviLatLng) {
        this.endPoi = naviLatLng;
        this.amapNaviPath.setEndPoint(naviLatLng);
    }

    public NaviLatLng getMaxCoordForPath() {
        return this.maxCoordForPath;
    }

    public NaviLatLng getMinCoordForPath() {
        return this.minCoordForPath;
    }

    public NaviLatLng getCenterForPath() {
        return this.center;
    }

    public void setCenter(NaviLatLng naviLatLng) {
        this.center = naviLatLng;
        this.amapNaviPath.setCenter(naviLatLng);
    }

    public LatLngBounds getBoundsForPath() {
        return this.bounds;
    }

    public void setBounds(LatLngBounds latLngBounds) {
        this.bounds = latLngBounds;
        this.amapNaviPath.setBounds(latLngBounds);
    }

    public List<AMapNaviStep> getSteps() {
        return this.listStep;
    }

    public void setListStep(List<AMapNaviStep> list) {
        this.listStep = list;
        this.amapNaviPath.setSteps(list);
    }

    public List<NaviLatLng> getCoordList() {
        return this.list;
    }

    public void setList(List<NaviLatLng> list) {
        this.list = list;
        this.amapNaviPath.setList(list);
    }

    public int getAllLength() {
        return this.allLength;
    }

    public void setAllLength(int i) {
        this.allLength = i;
        this.amapNaviPath.setAllLength(i);
    }

    public int getStrategy() {
        return this.strategy;
    }

    public void setStrategy(int i) {
        this.strategy = i;
        this.amapNaviPath.setStrategy(i);
    }

    public void setLabels(String str) {
        this.amapNaviPath.setLabels(str);
    }

    public int getAllTime() {
        return this.allTime;
    }

    public void setAllTime(int i) {
        this.allTime = i;
        this.amapNaviPath.setAllTime(i);
    }

    public int getStepsCount() {
        return this.stepsCount;
    }

    public void setStepsCount(int i) {
        this.stepsCount = i;
        this.amapNaviPath.setStepsCount(i);
    }

    public int getTollCost() {
        return this.tollCost;
    }

    public void setTollCost(int i) {
        this.tollCost = i;
        this.amapNaviPath.setTollCost(this.tollCost);
    }

    public void setGuideList(List<AMapNaviGuide> list) {
        this.guideList.clear();
        this.guideList.addAll(list);
    }

    public List<AMapNaviGuide> getGuideList() {
        return this.guideList;
    }

    public void setTrafficStatus(List<AMapTrafficStatus> list) {
        this.amapNaviPath.setTrafficStatus(list);
    }

    public void setRestrictionInfo(RestrictionInfo restrictionInfo) {
        this.amapNaviPath.setRestrictionInfo(new AMapRestrictionInfo(restrictionInfo));
    }

    public void setCityAdcodeList(int[] iArr) {
        this.amapNaviPath.setCityAdcodeList(iArr);
    }
}
