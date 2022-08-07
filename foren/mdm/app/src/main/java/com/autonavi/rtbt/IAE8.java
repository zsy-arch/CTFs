package com.autonavi.rtbt;

import com.amap.api.col.ex;
import com.amap.api.navi.model.AMapCarInfo;
import com.amap.api.navi.model.AMapNaviPath;
import com.amap.api.navi.model.AMapTrafficStatus;
import com.amap.api.navi.model.NaviLatLng;
import java.util.HashMap;
import java.util.List;

/* loaded from: classes.dex */
public interface IAE8 extends ex {
    boolean calculateDriveRoute(List<NaviLatLng> list, List<NaviLatLng> list2, int i);

    boolean calculateDriveRoute(List<NaviLatLng> list, List<NaviLatLng> list2, List<NaviLatLng> list3, int i);

    int[] getAllRouteID();

    HashMap<Integer, AMapNaviPath> getMultipleNaviPathsCalculated();

    List<AMapTrafficStatus> getTrafficStatuses(int i, int i2);

    boolean isCalculateMultipleRoutes();

    boolean reCalculateRoute(int i);

    boolean readNaviInfo();

    boolean readTrafficInfo(int i);

    void refreshTrafficStatuses();

    boolean setBroadcastMode(int i);

    void setCameraInfoUpdateEnabled(boolean z);

    void setCarInfo(AMapCarInfo aMapCarInfo);

    @Deprecated
    void setCarNumber(String str, String str2);

    void setDetectedMode(int i);

    void setReCalculateRouteForTrafficJam(boolean z);

    void setReCalculateRouteForYaw(boolean z);

    void setTrafficInfoUpdateEnabled(boolean z);

    void setTrafficStatusUpdateEnabled(boolean z);

    void startAimlessMode(int i);

    void stopAimlessMode();

    int strategyConvert(boolean z, boolean z2, boolean z3, boolean z4, boolean z5);

    void switchParallelRoad();
}
