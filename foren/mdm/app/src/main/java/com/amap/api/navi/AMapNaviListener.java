package com.amap.api.navi;

import com.amap.api.navi.model.AMapLaneInfo;
import com.amap.api.navi.model.AMapNaviCameraInfo;
import com.amap.api.navi.model.AMapNaviCross;
import com.amap.api.navi.model.AMapNaviInfo;
import com.amap.api.navi.model.AMapNaviLocation;
import com.amap.api.navi.model.AMapNaviTrafficFacilityInfo;
import com.amap.api.navi.model.AMapServiceAreaInfo;
import com.amap.api.navi.model.AimLessModeCongestionInfo;
import com.amap.api.navi.model.AimLessModeStat;
import com.amap.api.navi.model.NaviInfo;
import com.autonavi.tbt.TrafficFacilityInfo;

/* loaded from: classes.dex */
public interface AMapNaviListener {
    @Deprecated
    void OnUpdateTrafficFacility(AMapNaviTrafficFacilityInfo aMapNaviTrafficFacilityInfo);

    @Deprecated
    void OnUpdateTrafficFacility(TrafficFacilityInfo trafficFacilityInfo);

    void OnUpdateTrafficFacility(AMapNaviTrafficFacilityInfo[] aMapNaviTrafficFacilityInfoArr);

    void hideCross();

    void hideLaneInfo();

    void notifyParallelRoad(int i);

    void onArriveDestination();

    void onArrivedWayPoint(int i);

    void onCalculateMultipleRoutesSuccess(int[] iArr);

    void onCalculateRouteFailure(int i);

    void onCalculateRouteSuccess();

    void onEndEmulatorNavi();

    void onGetNavigationText(int i, String str);

    void onGpsOpenStatus(boolean z);

    void onInitNaviFailure();

    void onInitNaviSuccess();

    void onLocationChange(AMapNaviLocation aMapNaviLocation);

    void onNaviInfoUpdate(NaviInfo naviInfo);

    @Deprecated
    void onNaviInfoUpdated(AMapNaviInfo aMapNaviInfo);

    void onPlayRing(int i);

    void onReCalculateRouteForTrafficJam();

    void onReCalculateRouteForYaw();

    void onServiceAreaUpdate(AMapServiceAreaInfo[] aMapServiceAreaInfoArr);

    void onStartNavi(int i);

    void onTrafficStatusUpdate();

    void showCross(AMapNaviCross aMapNaviCross);

    void showLaneInfo(AMapLaneInfo[] aMapLaneInfoArr, byte[] bArr, byte[] bArr2);

    void updateAimlessModeCongestionInfo(AimLessModeCongestionInfo aimLessModeCongestionInfo);

    void updateAimlessModeStatistics(AimLessModeStat aimLessModeStat);

    void updateCameraInfo(AMapNaviCameraInfo[] aMapNaviCameraInfoArr);
}
