package com.autonavi.ae.guide.observer;

import com.autonavi.ae.guide.model.CongestionInfo;
import com.autonavi.ae.guide.model.GuideBoardInfo;
import com.autonavi.ae.guide.model.ManeuverIconConfig;
import com.autonavi.ae.guide.model.NaviCamera;
import com.autonavi.ae.guide.model.NaviInfo;
import com.autonavi.ae.guide.model.RouteTrafficEventInfo;
import com.autonavi.ae.guide.model.ServiceAreaInfo;
import com.autonavi.ae.guide.model.TrafficEventInfo;
import com.autonavi.ae.route.model.TmcBarItem;

/* loaded from: classes.dex */
public interface GNaviObserver {
    void ThreeDLastPass();

    void arrayViaPoint(int i);

    int get3DDataVersion(int i);

    void hideCross();

    void hideLaneInfo();

    void navigationEnd(int i);

    void onCarOnGuideRouteAgain();

    int onCheckNaviVoiceCfg(int i);

    void onExitDirectionInfo(GuideBoardInfo guideBoardInfo);

    void onNaviEtaIncidentReport(int i, int i2, int i3);

    void onNaviEtaIncidentReportHide(int i);

    void onNaviRenderManeuverIcon(byte[] bArr, ManeuverIconConfig maneuverIconConfig);

    void onNaviShowManeuver(int i, int i2, byte[] bArr, int i3);

    void onReroute(int i);

    void onServiceAreaUpdate(ServiceAreaInfo[] serviceAreaInfoArr);

    void onTmcUpdate(TmcBarItem[] tmcBarItemArr, int i, int i2);

    void onfinishRecover3DPath(int i);

    void showCross(int i, byte[] bArr, byte[] bArr2);

    void showLaneInfo(byte[] bArr, byte[] bArr2);

    void updateCameraInfo(NaviCamera[] naviCameraArr);

    void updateCongestion(CongestionInfo congestionInfo);

    void updateCruiseInfo(String str);

    void updateDataMiningTrafficEvent(TrafficEventInfo trafficEventInfo);

    void updateNaviInfo(NaviInfo naviInfo);

    void updateRouteTrafficEvent(RouteTrafficEventInfo routeTrafficEventInfo);

    void updateSoundFlag(int i, int i2);

    void updateTrafficEvent(TrafficEventInfo[] trafficEventInfoArr, int i);
}
