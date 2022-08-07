package com.amap.api.col;

import android.os.Handler;
import android.os.Message;
import android.support.v4.view.MotionEventCompat;
import com.amap.api.navi.AMapNaviListener;
import com.amap.api.navi.MyNaviListener;
import com.amap.api.navi.model.AMapLaneInfo;
import com.amap.api.navi.model.AMapModelCross;
import com.amap.api.navi.model.AMapNaviCameraInfo;
import com.amap.api.navi.model.AMapNaviCross;
import com.amap.api.navi.model.AMapNaviInfo;
import com.amap.api.navi.model.AMapNaviLocation;
import com.amap.api.navi.model.AMapNaviTrafficFacilityInfo;
import com.amap.api.navi.model.AMapServiceAreaInfo;
import com.amap.api.navi.model.AMapTrafficStatus;
import com.amap.api.navi.model.AimLessModeCongestionInfo;
import com.amap.api.navi.model.AimLessModeStat;
import com.amap.api.navi.model.NaviInfo;
import com.amap.api.navi.model.NaviLatLng;
import com.autonavi.ae.guide.GuideService;
import com.autonavi.ae.guide.model.CongestionInfo;
import com.autonavi.ae.guide.model.GuideBoardInfo;
import com.autonavi.ae.guide.model.ManeuverIconConfig;
import com.autonavi.ae.guide.model.NaviCamera;
import com.autonavi.ae.guide.model.NoNaviCongestionInfo;
import com.autonavi.ae.guide.model.NoNaviInfor;
import com.autonavi.ae.guide.model.RouteTrafficEventInfo;
import com.autonavi.ae.guide.model.ServiceAreaInfo;
import com.autonavi.ae.guide.model.TrafficEventInfo;
import com.autonavi.ae.guide.observer.GElecEyeObserver;
import com.autonavi.ae.guide.observer.GNaviObserver;
import com.autonavi.ae.guide.observer.GSoundPlayObserver;
import com.autonavi.ae.guide.observer.GStatusObserver;
import com.autonavi.ae.pos.LocInfo2D;
import com.autonavi.ae.pos.LocInfo3D;
import com.autonavi.ae.pos.LocListener;
import com.autonavi.ae.pos.LocParallelRoadObserver;
import com.autonavi.ae.pos.LocParallelRoads;
import com.autonavi.ae.route.model.TmcBarItem;
import com.autonavi.ae.route.observer.HttpInterface;
import com.autonavi.tbt.TrafficFacilityInfo;
import java.util.ArrayList;
import java.util.List;
import u.aly.dc;

/* compiled from: MyGuideObserver.java */
/* loaded from: classes.dex */
public class eo implements GElecEyeObserver, GNaviObserver, GSoundPlayObserver, GStatusObserver, LocListener, LocParallelRoadObserver, HttpInterface {
    private el a;
    private AMapLaneInfo[] c;
    private byte[] d;
    private byte[] e;
    private AMapNaviCross f;
    private AMapModelCross g;
    private AMapNaviLocation h;
    private int j;
    private int p;
    private TmcBarItem[] q;
    private int r;
    private int k = 0;
    private AMapNaviTrafficFacilityInfo[] l = null;
    private AimLessModeStat m = null;
    private AimLessModeCongestionInfo n = null;
    private int o = 0;
    private int s = 0;
    private Handler t = new Handler() { // from class: com.amap.api.col.eo.1
        /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            switch (message.what) {
                case 1:
                    eo.this.a(1, new Object[0]);
                    return;
                case 2:
                    eo.this.a(2, new Object[0]);
                    return;
                case 3:
                    eo.this.a(3, new Object[0]);
                    return;
                case 4:
                    eo.this.a(4, new Object[0]);
                    return;
                case 5:
                    eo.this.a(5, new Object[0]);
                    break;
                case 6:
                    break;
                case 7:
                    eo.this.a(7, new Object[0]);
                    return;
                case 8:
                    eo.this.a(8, message.obj);
                    return;
                case 9:
                    eo.this.a(9, new Object[0]);
                    return;
                case 10:
                    eo.this.a(10, new Object[0]);
                    return;
                case 11:
                    eo.this.a(11, message.obj);
                    return;
                case 12:
                case 13:
                case 15:
                case 16:
                default:
                    return;
                case 14:
                    eo.this.a(14, new Object[0]);
                    return;
                case 17:
                    eo.this.a(17, Integer.valueOf(message.arg1), message.obj);
                    return;
                case 18:
                    eo.this.a(18, new Object[0]);
                    return;
                case 19:
                    eo.this.a(19, new Object[0]);
                    return;
                case 20:
                    eo.this.a(20, new Object[0]);
                    return;
                case 21:
                    if (message.obj != null) {
                        eq eqVar = (eq) message.obj;
                        if (eqVar.b() != null) {
                            eo.this.a.e().processHttpData(eqVar.a(), 200, eqVar.b());
                            return;
                        } else {
                            eo.this.a.e().processHttpError(eqVar.a(), 404);
                            return;
                        }
                    } else {
                        return;
                    }
                case 22:
                    eo.this.a(22, new Object[0]);
                    return;
                case 23:
                    eo.this.a(23, new Object[0]);
                    return;
                case 24:
                    eo.this.a(24, new Object[0]);
                    return;
                case 25:
                    eo.this.a(25, new Object[0]);
                    return;
                case 26:
                    eo.this.a(26, message.obj);
                    return;
            }
            eo.this.a(6, new Object[0]);
        }
    };
    private NaviInfo b = new NaviInfo();
    private List<AMapTrafficStatus> i = new ArrayList();

    public NaviInfo a() {
        return this.b;
    }

    public void b() {
        if (this.t != null) {
            this.t.removeCallbacksAndMessages(null);
        }
        this.a = null;
        this.b = null;
        this.c = null;
        this.d = null;
        this.e = null;
        this.f = null;
        this.h = null;
        this.i = null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(int i, Object... objArr) {
        List<AMapNaviListener> g;
        if (!(this.a == null || (g = this.a.g()) == null)) {
            try {
                switch (i) {
                    case 1:
                        if (this.b != null) {
                            for (AMapNaviListener aMapNaviListener : g) {
                                aMapNaviListener.onNaviInfoUpdate(this.b);
                                aMapNaviListener.onNaviInfoUpdated(new AMapNaviInfo(this.b.getPathRetainDistance(), this.b.getPathRetainTime()));
                            }
                            return;
                        }
                        return;
                    case 2:
                        for (AMapNaviListener aMapNaviListener2 : g) {
                            aMapNaviListener2.showCross(this.f);
                        }
                        return;
                    case 3:
                        for (AMapNaviListener aMapNaviListener3 : g) {
                            aMapNaviListener3.hideCross();
                        }
                        return;
                    case 4:
                        for (AMapNaviListener aMapNaviListener4 : g) {
                            aMapNaviListener4.showLaneInfo(this.c, this.d, this.e);
                        }
                        return;
                    case 5:
                        for (AMapNaviListener aMapNaviListener5 : g) {
                            aMapNaviListener5.hideLaneInfo();
                        }
                        return;
                    case 6:
                        for (AMapNaviListener aMapNaviListener6 : g) {
                            aMapNaviListener6.notifyParallelRoad(this.p);
                        }
                        return;
                    case 7:
                        for (AMapNaviListener aMapNaviListener7 : g) {
                            aMapNaviListener7.onTrafficStatusUpdate();
                        }
                        return;
                    case 8:
                        try {
                            AMapServiceAreaInfo[] aMapServiceAreaInfoArr = (AMapServiceAreaInfo[]) objArr[0];
                            for (AMapNaviListener aMapNaviListener8 : g) {
                                aMapNaviListener8.onServiceAreaUpdate(aMapServiceAreaInfoArr);
                            }
                            return;
                        } catch (Exception e) {
                            e.printStackTrace();
                            return;
                        }
                    case 9:
                        if (this.j == 1) {
                            for (AMapNaviListener aMapNaviListener9 : g) {
                                aMapNaviListener9.onEndEmulatorNavi();
                            }
                        }
                        if (this.j == 0) {
                            for (AMapNaviListener aMapNaviListener10 : g) {
                                aMapNaviListener10.onArriveDestination();
                            }
                            return;
                        }
                        return;
                    case 10:
                        for (AMapNaviListener aMapNaviListener11 : g) {
                            aMapNaviListener11.onArrivedWayPoint(this.k);
                        }
                        return;
                    case 11:
                        try {
                            AMapNaviCameraInfo[] aMapNaviCameraInfoArr = (AMapNaviCameraInfo[]) objArr[0];
                            for (AMapNaviListener aMapNaviListener12 : g) {
                                aMapNaviListener12.updateCameraInfo(aMapNaviCameraInfoArr);
                            }
                            return;
                        } catch (Exception e2) {
                            e2.printStackTrace();
                            return;
                        }
                    case 12:
                    case 13:
                    case 15:
                    case 16:
                    case 21:
                    case 27:
                    case 28:
                    case 29:
                    case 30:
                    case 31:
                    case 32:
                    default:
                        return;
                    case 14:
                        this.a.reCalculateRoute(this.o);
                        return;
                    case 17:
                        for (AMapNaviListener aMapNaviListener13 : g) {
                            aMapNaviListener13.onGetNavigationText(((Integer) objArr[0]).intValue(), (String) objArr[1]);
                        }
                        return;
                    case 18:
                        for (AMapNaviListener aMapNaviListener14 : g) {
                            aMapNaviListener14.OnUpdateTrafficFacility(this.l);
                            if (this.l != null && this.l.length > 0) {
                                aMapNaviListener14.OnUpdateTrafficFacility(this.l[0]);
                                aMapNaviListener14.OnUpdateTrafficFacility(new TrafficFacilityInfo(this.l[0]));
                            }
                        }
                        return;
                    case 19:
                        for (AMapNaviListener aMapNaviListener15 : g) {
                            aMapNaviListener15.updateAimlessModeStatistics(this.m);
                        }
                        return;
                    case 20:
                        for (AMapNaviListener aMapNaviListener16 : g) {
                            aMapNaviListener16.updateAimlessModeCongestionInfo(this.n);
                        }
                        return;
                    case 22:
                        if (this.h != null) {
                            for (AMapNaviListener aMapNaviListener17 : g) {
                                aMapNaviListener17.onLocationChange(this.h);
                            }
                            return;
                        }
                        return;
                    case 23:
                        for (AMapNaviListener aMapNaviListener18 : g) {
                            aMapNaviListener18.onPlayRing(this.r);
                        }
                        return;
                    case 24:
                        for (AMapNaviListener aMapNaviListener19 : g) {
                            if (aMapNaviListener19 instanceof MyNaviListener) {
                                ((MyNaviListener) aMapNaviListener19).showModeCross(this.g);
                            }
                        }
                        return;
                    case 25:
                        for (AMapNaviListener aMapNaviListener20 : g) {
                            if (aMapNaviListener20 instanceof MyNaviListener) {
                                ((MyNaviListener) aMapNaviListener20).hideModeCross();
                            }
                        }
                        return;
                    case 26:
                        int intValue = ((Integer) objArr[0]).intValue();
                        GuideService e3 = this.a.e();
                        if (e3 != null) {
                            fr.a("guideService.startNavi(" + intValue + ")");
                            e3.startNavi(intValue);
                        }
                        for (AMapNaviListener aMapNaviListener21 : g) {
                            aMapNaviListener21.onStartNavi(intValue);
                        }
                        return;
                    case 33:
                        for (AMapNaviListener aMapNaviListener22 : g) {
                            aMapNaviListener22.onArriveDestination();
                        }
                        return;
                }
            } catch (Exception e4) {
                e4.printStackTrace();
                gr.b(e4, "gObserver", "SendEvent()");
            }
            e4.printStackTrace();
            gr.b(e4, "gObserver", "SendEvent()");
        }
    }

    public eo(el elVar) {
        this.a = elVar;
    }

    public void a(int i, Object obj) {
        if (this.t != null) {
            this.t.obtainMessage(i, obj).sendToTarget();
        }
    }

    @Override // com.autonavi.ae.guide.observer.GNaviObserver
    public void updateCruiseInfo(String str) {
        fr.a("SHIXIN", "MyGuideObserver-->updateCruiseInfo(" + str + ")");
    }

    @Override // com.autonavi.ae.guide.observer.GNaviObserver
    public void updateNaviInfo(com.autonavi.ae.guide.model.NaviInfo naviInfo) {
        if (naviInfo != null) {
            try {
                this.b.m_CurRoadName = naviInfo.currentRoadName;
                this.b.m_NextRoadName = naviInfo.nextRoadName;
                this.b.m_RouteRemainDis = naviInfo.routeRemainDistance;
                this.b.m_RouteRemainTime = naviInfo.routeRemainTime;
                this.b.m_SegRemainDis = naviInfo.segmentRemainDistance;
                this.b.m_SegRemainTime = naviInfo.segmentRemainTime;
                this.b.m_CurSegNum = naviInfo.currentSegNumber;
                this.b.m_CurLinkNum = naviInfo.currentLinkNumber;
                this.b.m_Icon = naviInfo.iconId;
                if (this.t != null) {
                    this.t.obtainMessage(1).sendToTarget();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override // com.autonavi.ae.guide.observer.GNaviObserver
    public void showCross(int i, byte[] bArr, byte[] bArr2) {
        if (this.t != null) {
            this.s = i;
            if (i == 1) {
                fr.a("SHIXIN", "MyGuideObserver-->showCross(实景图),picFormat=" + i);
                this.f = new AMapNaviCross(i, bArr, bArr2);
                this.t.obtainMessage(2).sendToTarget();
            }
            if (i == 3) {
                fr.a("SHIXIN", "MyGuideObserver-->showCross(模型图),picFormat=" + i);
                this.g = new AMapModelCross(i, bArr, bArr2);
                this.t.obtainMessage(24).sendToTarget();
            }
        }
    }

    @Override // com.autonavi.ae.guide.observer.GNaviObserver
    public void hideCross() {
        if (this.t != null) {
            if (this.s == 1) {
                fr.a("SHIXIN", "MyGuideObserver-->hideCross(实景图)");
                this.t.obtainMessage(3).sendToTarget();
            }
            if (this.s == 3) {
                fr.a("SHIXIN", "MyGuideObserver-->hideCross(模型图)");
                this.t.obtainMessage(25).sendToTarget();
            }
        }
    }

    @Override // com.autonavi.ae.guide.observer.GNaviObserver
    public void showLaneInfo(byte[] bArr, byte[] bArr2) {
        if (bArr != null && bArr2 != null) {
            try {
                fr.a("SHIXIN", "MyGuideObserver-->showLaneInfo(),ThreadName=" + Thread.currentThread().getName());
                this.d = bArr;
                this.e = bArr2;
                this.c = a(bArr, bArr2);
                if (this.t != null) {
                    this.t.obtainMessage(4).sendToTarget();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override // com.autonavi.ae.guide.observer.GNaviObserver
    public void hideLaneInfo() {
        fr.a("SHIXIN", "MyGuideObserver-->hideLaneInfo()");
        if (this.t != null) {
            this.t.obtainMessage(5).sendToTarget();
        }
    }

    private AMapLaneInfo[] a(byte[] bArr, byte[] bArr2) throws Exception {
        int a;
        int a2 = a(bArr);
        AMapLaneInfo[] b = b(a2);
        for (int i = 0; i < a2; i++) {
            if (a(bArr[i])) {
                a = a((int) bArr[i], (int) bArr2[i]);
            } else {
                a = a(bArr[i], bArr2[i]);
            }
            b[i].setLaneTypeId(a);
        }
        return b;
    }

    private int a(byte b, byte b2) throws Exception {
        if (b2 == 15) {
            return (b * dc.n) + b2;
        }
        return (b * dc.n) + b;
    }

    private int a(int i, int i2) throws Exception {
        return (i * 16) + i2;
    }

    private boolean a(int i) throws Exception {
        return i == 14 || i == 2 || i == 4 || i == 9 || i == 10 || i == 11 || i == 12 || i == 6 || i == 7;
    }

    private AMapLaneInfo[] b(int i) throws Exception {
        AMapLaneInfo[] aMapLaneInfoArr = new AMapLaneInfo[i];
        for (int i2 = 0; i2 < aMapLaneInfoArr.length; i2++) {
            aMapLaneInfoArr[i2] = new AMapLaneInfo();
        }
        return aMapLaneInfoArr;
    }

    private int a(byte[] bArr) throws Exception {
        int length = bArr.length;
        for (int i = 0; i < length; i++) {
            if (bArr[i] == 15) {
                return i;
            }
        }
        return 0;
    }

    @Override // com.autonavi.ae.guide.observer.GNaviObserver
    public void onNaviShowManeuver(int i, int i2, byte[] bArr, int i3) {
        fr.a("SHIXIN", "MyGuideObserver-->onNaviShowManeuver()");
        try {
            this.a.e().renderManeuverIcon(new ManeuverIconConfig(255, 255, 2632759, 5790310, MotionEventCompat.ACTION_POINTER_INDEX_MASK, i2, i));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // com.autonavi.ae.guide.observer.GNaviObserver
    public void onNaviRenderManeuverIcon(byte[] bArr, ManeuverIconConfig maneuverIconConfig) {
        fr.a("SHIXIN", "MyGuideObserver-->onNaviRenderManeuverIcon(),maneuverId=" + maneuverIconConfig.maneuverId);
        try {
            if (this.t != null && maneuverIconConfig != null) {
                this.b.setIconType(maneuverIconConfig.maneuverId);
                this.t.obtainMessage(1).sendToTarget();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // com.autonavi.ae.guide.observer.GNaviObserver
    public void onTmcUpdate(TmcBarItem[] tmcBarItemArr, int i, int i2) {
        try {
            if (this.q != null && this.q.length == tmcBarItemArr.length) {
                for (int i3 = 0; i3 < tmcBarItemArr.length; i3++) {
                    TmcBarItem tmcBarItem = tmcBarItemArr[i3];
                    TmcBarItem tmcBarItem2 = this.q[i3];
                    if (tmcBarItem.length != tmcBarItem2.length || tmcBarItem.status != tmcBarItem2.status) {
                        return;
                    }
                }
            }
            fr.a("SHIXIN", "MyGuideObserver-->onTmcUpdate()");
            if (this.i != null) {
                this.i.clear();
                for (TmcBarItem tmcBarItem3 : tmcBarItemArr) {
                    this.i.add(new AMapTrafficStatus(tmcBarItem3));
                }
                this.q = tmcBarItemArr;
            }
            if (this.t != null) {
                this.t.obtainMessage(7).sendToTarget();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<AMapTrafficStatus> c() {
        return this.i;
    }

    @Override // com.autonavi.ae.guide.observer.GNaviObserver
    public void onServiceAreaUpdate(ServiceAreaInfo[] serviceAreaInfoArr) {
        if (serviceAreaInfoArr != null) {
            try {
                AMapServiceAreaInfo[] aMapServiceAreaInfoArr = new AMapServiceAreaInfo[serviceAreaInfoArr.length];
                for (int i = 0; i < serviceAreaInfoArr.length; i++) {
                    aMapServiceAreaInfoArr[i] = new AMapServiceAreaInfo(serviceAreaInfoArr[i]);
                    if (this.t != null) {
                        fr.a("SHIXIN", "MyGuideObserver-->onServiceAreaUpdate()");
                        this.t.obtainMessage(8, aMapServiceAreaInfoArr).sendToTarget();
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override // com.autonavi.ae.guide.observer.GNaviObserver
    public void navigationEnd(int i) {
        fr.a("SHIXIN", "MyGuideObserver-->navigationEnd(" + i + ")");
        if (this.t != null) {
            this.j = i;
            this.t.obtainMessage(9).sendToTarget();
        }
    }

    @Override // com.autonavi.ae.guide.observer.GNaviObserver
    public void arrayViaPoint(int i) {
        fr.a("SHIXIN", "MyGuideObserver-->arrayViaPoint(" + i + ")");
        this.k = i;
        if (this.t != null) {
            this.t.obtainMessage(10).sendToTarget();
        }
    }

    @Override // com.autonavi.ae.guide.observer.GNaviObserver
    public void updateCameraInfo(NaviCamera[] naviCameraArr) {
        try {
            AMapNaviCameraInfo[] aMapNaviCameraInfoArr = new AMapNaviCameraInfo[naviCameraArr.length];
            for (int i = 0; i < naviCameraArr.length; i++) {
                aMapNaviCameraInfoArr[i] = new AMapNaviCameraInfo(naviCameraArr[i]);
                if (this.t != null) {
                    fr.a("SHIXIN", "MyGuideObserver-->updateCameraInfo(),size=" + naviCameraArr.length);
                    this.t.obtainMessage(11, aMapNaviCameraInfoArr).sendToTarget();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            gr.b(e, "gObserver", "uci");
        }
    }

    @Override // com.autonavi.ae.guide.observer.GNaviObserver
    public void updateTrafficEvent(TrafficEventInfo[] trafficEventInfoArr, int i) {
        fr.b("SHIXIN", "MyGuideObserver-->updateTrafficEvent(" + i + ")");
    }

    @Override // com.autonavi.ae.guide.observer.GNaviObserver
    public void updateRouteTrafficEvent(RouteTrafficEventInfo routeTrafficEventInfo) {
        fr.b("SHIXIN", "MyGuideObserver-->updateRouteTrafficEvent()");
    }

    @Override // com.autonavi.ae.guide.observer.GNaviObserver
    public void updateCongestion(CongestionInfo congestionInfo) {
        fr.b("SHIXIN", "MyGuideObserver-->updateCongestion()");
    }

    @Override // com.autonavi.ae.guide.observer.GNaviObserver
    public void onReroute(int i) {
        fr.a("SHIXIN", "MyGuideObserver-->onReroute(" + i + ")");
        this.o = i;
        if (this.t != null) {
            this.t.obtainMessage(14).sendToTarget();
        }
    }

    @Override // com.autonavi.ae.guide.observer.GNaviObserver
    public void onfinishRecover3DPath(int i) {
        fr.b("SHIXIN", "MyGuideObserver-->onfinishRecover3DPath()");
    }

    @Override // com.autonavi.ae.guide.observer.GNaviObserver
    public int get3DDataVersion(int i) {
        fr.b("SHIXIN", "MyGuideObserver-->get3DDataVersion()");
        return 0;
    }

    @Override // com.autonavi.ae.guide.observer.GNaviObserver
    public int onCheckNaviVoiceCfg(int i) {
        fr.b("SHIXIN", "MyGuideObserver-->onCheckNaviVoiceCfg()");
        return 0;
    }

    @Override // com.autonavi.ae.guide.observer.GNaviObserver
    public void ThreeDLastPass() {
        fr.b("SHIXIN", "MyGuideObserver-->ThreeDLastPass()");
    }

    @Override // com.autonavi.ae.guide.observer.GNaviObserver
    public void onCarOnGuideRouteAgain() {
        fr.b("SHIXIN", "MyGuideObserver-->onCarOnGuideRouteAgain()");
    }

    @Override // com.autonavi.ae.guide.observer.GNaviObserver
    public void onNaviEtaIncidentReport(int i, int i2, int i3) {
        fr.b("SHIXIN", "MyGuideObserver-->onNaviEtaIncidentReport()");
    }

    @Override // com.autonavi.ae.guide.observer.GNaviObserver
    public void onNaviEtaIncidentReportHide(int i) {
        fr.b("SHIXIN", "MyGuideObserver-->onNaviEtaIncidentReportHide()");
    }

    @Override // com.autonavi.ae.guide.observer.GNaviObserver
    public void updateDataMiningTrafficEvent(TrafficEventInfo trafficEventInfo) {
        fr.b("SHIXIN", "MyGuideObserver-->updateDataMiningTrafficEvent()");
    }

    @Override // com.autonavi.ae.guide.observer.GNaviObserver
    public void updateSoundFlag(int i, int i2) {
        fr.b("SHIXIN", "MyGuideObserver-->updateSoundFlag()");
    }

    @Override // com.autonavi.ae.guide.observer.GNaviObserver
    public void onExitDirectionInfo(GuideBoardInfo guideBoardInfo) {
        fr.b("SHIXIN", "MyGuideObserver-->onExitDirectionInfo()");
    }

    @Override // com.autonavi.ae.guide.observer.GSoundPlayObserver
    public void onPlayTTS(String str, int i) {
        fr.a("SHIXIN", "MyGuideObserver-->onPlayTTS(),type=" + i + ",str=" + str);
        if (this.t != null) {
            this.t.obtainMessage(17, i, i, str).sendToTarget();
        }
    }

    @Override // com.autonavi.ae.guide.observer.GSoundPlayObserver
    public void onPlayRing(int i) {
        fr.b("SHIXIN", "MyGuideObserver-->onPlayRing()");
        if (this.t != null) {
            this.r = i;
            this.t.obtainMessage(23).sendToTarget();
        }
    }

    @Override // com.autonavi.ae.guide.observer.GSoundPlayObserver
    public boolean isNaviPlaying() {
        return fn.a;
    }

    @Override // com.autonavi.ae.guide.observer.GStatusObserver
    public void onTbtStatusChanged(int i, int i2) {
        fr.b("SHIXIN", "MyGuideObserver-->onTbtStatusChanged()");
    }

    @Override // com.autonavi.ae.guide.observer.GElecEyeObserver
    public void onTrafficFacilityUpdate(com.autonavi.ae.guide.model.TrafficFacilityInfo[] trafficFacilityInfoArr) {
        fr.a("SHIXIN", "MyGuideObserver-->onTrafficFacilityUpdate()");
        try {
            this.l = new AMapNaviTrafficFacilityInfo[trafficFacilityInfoArr.length];
            for (int i = 0; i < this.l.length; i++) {
                this.l[i] = new AMapNaviTrafficFacilityInfo(trafficFacilityInfoArr[i]);
            }
            if (this.t != null) {
                this.t.sendEmptyMessage(18);
            }
        } catch (Throwable th) {
            fn.a(th);
            gr.b(th, "gObserver", "updateTrafficFacility(TrafficFacilityInfo[] infoArray)");
        }
    }

    @Override // com.autonavi.ae.guide.observer.GElecEyeObserver
    public void onUpdateNoNaviInfor(NoNaviInfor noNaviInfor) {
        fr.a("SHIXIN", "MyGuideObserver-->onUpdateNoNaviInfor()-->已行驶距离:" + noNaviInfor.noNaviDriveDist + ",已行驶时间:" + noNaviInfor.noNaviDriveTime);
        try {
            this.m = new AimLessModeStat(noNaviInfor);
            if (this.t != null) {
                this.t.sendEmptyMessage(19);
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    @Override // com.autonavi.ae.guide.observer.GElecEyeObserver
    public void onUpdateNoNaviCongestionInfo(NoNaviCongestionInfo noNaviCongestionInfo) {
        fr.a("SHIXIN", "MyGuideObserver-->onUpdateNoNaviCongestionInfo()");
        try {
            this.n = new AimLessModeCongestionInfo(noNaviCongestionInfo);
            if (this.t != null) {
                this.t.sendEmptyMessage(20);
            }
        } catch (Throwable th) {
            fn.a(th);
            gr.b(th, "gObserver", "updateNoNaviCongestionInfo(NoNaviCongestionInfo info)");
        }
    }

    @Override // com.autonavi.ae.route.observer.HttpInterface
    public boolean requestHttpGet(int i, int i2, String str) {
        fr.a("SHIXIN", "MyGuideObserver-->requestHttpGet()");
        return false;
    }

    @Override // com.autonavi.ae.route.observer.HttpInterface
    public boolean requestHttpPost(int i, int i2, String str, byte[] bArr) {
        try {
            fr.a("SHIXIN", "MyGuideObserver-->requestHttpPost(),moduleId=" + i + ",TheadName=" + Thread.currentThread().getName() + ",reqId=" + i2 + ",data=" + new String(bArr, "UTF-8"));
            ii a = en.a(this.a.d(), i, en.a(2, "1.0", bArr));
            fr.a("SHIXIN", "MyGuideObserver-->requestHttpPost(),respData=" + new String(a.a, "UTF-8"));
            eq eqVar = new eq(i, i2, a.a);
            if (this.t == null) {
                return false;
            }
            this.t.obtainMessage(21, eqVar).sendToTarget();
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            gr.b(e, "gObserver", "requestHttpPost(" + i + "," + i2 + ")");
            return false;
        }
    }

    @Override // com.autonavi.ae.pos.LocListener
    public void updateNaviInfo(LocInfo2D locInfo2D, LocInfo3D locInfo3D) {
        try {
            double d = locInfo2D.stPos.lat / 3600000.0d;
            double d2 = locInfo2D.stPos.lon / 3600000.0d;
            float f = (float) locInfo2D.alt;
            int i = (int) locInfo2D.speed;
            byte b = locInfo2D.isOnGuideRoad;
            this.b.setCurrentSpeed(i);
            this.b.setCurPoint(locInfo2D.postCur);
            this.h = new AMapNaviLocation();
            this.h.setAccuracy((float) locInfo2D.altAcc);
            this.h.setAltitude(f);
            this.h.setBearing((int) locInfo2D.course);
            this.h.setSpeed(i);
            if (b == 1 || b == 2) {
                this.h.setMatchStatus(1);
            } else {
                this.h.setMatchStatus(0);
            }
            this.h.setCoord(new NaviLatLng(d, d2));
            this.h.setTime(System.currentTimeMillis());
            if (this.t != null) {
                this.t.obtainMessage(22).sendToTarget();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // com.autonavi.ae.pos.LocParallelRoadObserver
    public void updateParallelRoad(LocParallelRoads locParallelRoads) {
        if (locParallelRoads != null) {
            this.p = locParallelRoads.nFlag;
            if (this.t != null) {
                this.t.obtainMessage(6).sendToTarget();
            }
        }
    }
}
