package com.amap.api.navi;

import android.location.Location;
import com.amap.api.navi.model.AMapCarInfo;
import com.amap.api.navi.model.AMapNaviGuide;
import com.amap.api.navi.model.AMapNaviPath;
import com.amap.api.navi.model.AMapTrafficStatus;
import com.amap.api.navi.model.NaviInfo;
import com.amap.api.navi.model.NaviLatLng;
import java.util.HashMap;
import java.util.List;

/* loaded from: classes.dex */
public interface INavi {
    void addAMapNaviListener(AMapNaviListener aMapNaviListener);

    boolean calculateDriveRoute(List<NaviLatLng> list, List<NaviLatLng> list2, int i);

    boolean calculateDriveRoute(List<NaviLatLng> list, List<NaviLatLng> list2, List<NaviLatLng> list3, int i);

    boolean calculateRideRoute(NaviLatLng naviLatLng);

    boolean calculateRideRoute(NaviLatLng naviLatLng, NaviLatLng naviLatLng2);

    boolean calculateWalkRoute(NaviLatLng naviLatLng);

    boolean calculateWalkRoute(NaviLatLng naviLatLng, NaviLatLng naviLatLng2);

    void destroy();

    int getEngineType();

    boolean getIsUseExtraGPSData();

    List<AMapNaviGuide> getNaviGuideList();

    NaviInfo getNaviInfo();

    AMapNaviPath getNaviPath();

    HashMap<Integer, AMapNaviPath> getNaviPaths();

    NaviSetting getNaviSetting();

    int getNaviType();

    List<AMapTrafficStatus> getTrafficStatuses(int i, int i2);

    boolean isGpsReady();

    void pauseNavi();

    boolean reCalculateRoute(int i);

    boolean readNaviInfo();

    boolean readTrafficInfo(int i);

    void removeAMapNaviListener(AMapNaviListener aMapNaviListener);

    void resumeNavi();

    boolean selectRouteId(int i);

    boolean setBroadcastMode(int i);

    void setCarInfo(AMapCarInfo aMapCarInfo);

    void setCarNumber(String str, String str2);

    void setConnectionTimeout(int i);

    void setDetectedMode(int i);

    void setEmulatorNaviSpeed(int i);

    void setExtraGPSData(int i, Location location);

    void setExtraGPSData(Location location);

    void setIsUseExtraGPSData(boolean z);

    void setReCalculateRouteForTrafficJam(boolean z);

    void setReCalculateRouteForYaw(boolean z);

    void setSoTimeout(int i);

    void setTimeForOneWord(int i);

    void startAimlessMode(int i);

    boolean startGPS();

    boolean startGPS(long j, int i);

    boolean startNavi(int i);

    void stopAimlessMode();

    boolean stopGPS();

    void stopNavi();

    int strategyConvert(boolean z, boolean z2, boolean z3, boolean z4, boolean z5);

    void switchParallelRoad();
}
