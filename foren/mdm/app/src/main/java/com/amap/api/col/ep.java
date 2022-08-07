package com.amap.api.col;

import android.os.Handler;
import android.os.Message;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.LatLngBounds;
import com.amap.api.navi.AMapNaviListener;
import com.amap.api.navi.model.AMapNaviCameraInfo;
import com.amap.api.navi.model.AMapNaviGuide;
import com.amap.api.navi.model.AMapNaviLink;
import com.amap.api.navi.model.AMapNaviStep;
import com.amap.api.navi.model.AMapTrafficStatus;
import com.amap.api.navi.model.NaviGuide;
import com.amap.api.navi.model.NaviLatLng;
import com.amap.api.navi.model.NaviPath;
import com.autonavi.ae.route.model.GeoPoint;
import com.autonavi.ae.route.model.GroupSegment;
import com.autonavi.ae.route.model.LabelInfo;
import com.autonavi.ae.route.model.RouteCamera;
import com.autonavi.ae.route.model.RoutePoi;
import com.autonavi.ae.route.model.TmcBarItem;
import com.autonavi.ae.route.observer.HttpInterface;
import com.autonavi.ae.route.observer.PathRequestObserver;
import com.autonavi.ae.route.observer.RouteObserver;
import com.autonavi.ae.route.route.CalcRouteResult;
import com.autonavi.ae.route.route.Route;
import com.autonavi.ae.route.route.RouteLink;
import com.autonavi.ae.route.route.RouteSegment;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* compiled from: MyRouteObserver.java */
/* loaded from: classes.dex */
public class ep implements HttpInterface, PathRequestObserver, RouteObserver {
    private final el a;
    private CalcRouteResult b;
    private int[] c;
    private Map<Integer, NaviPath> d = new HashMap();
    private Map<Integer, Long> e = new HashMap();
    private final Handler f = new Handler() { // from class: com.amap.api.col.ep.1
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            List<AMapNaviListener> g = ep.this.a.g();
            if (g != null) {
                switch (message.what) {
                    case 1:
                        eq eqVar = (eq) message.obj;
                        if (eqVar.b() != null) {
                            ep.this.a.f().processHttpData(eqVar.a(), 200, eqVar.b());
                            return;
                        } else {
                            ep.this.a.f().processHttpError(eqVar.a(), 404);
                            return;
                        }
                    case 2:
                        fr.a("MyRouteObserver-->HANDER_FLAG_MULTIPLE_ROUTE__SUCESS-->Thread Name=" + Thread.currentThread().getName());
                        for (AMapNaviListener aMapNaviListener : g) {
                            aMapNaviListener.onCalculateMultipleRoutesSuccess(ep.this.c);
                        }
                        return;
                    case 3:
                        fr.a("MyRouteObserver-->HANDER_FLAG_SIGLE_ROUTE_SUCESS-->Thread Name=" + Thread.currentThread().getName());
                        for (AMapNaviListener aMapNaviListener2 : g) {
                            aMapNaviListener2.onCalculateRouteSuccess();
                        }
                        return;
                    case 4:
                        for (AMapNaviListener aMapNaviListener3 : g) {
                            aMapNaviListener3.onCalculateRouteFailure(message.arg2);
                        }
                        return;
                    case 5:
                        fr.a("MyRouteObserver-->HANDER_FLAG_RECALCULATE_YAW-->Thread Name=" + Thread.currentThread().getName());
                        for (AMapNaviListener aMapNaviListener4 : g) {
                            aMapNaviListener4.onReCalculateRouteForYaw();
                        }
                        return;
                    default:
                        return;
                }
            }
        }
    };

    public Map<Integer, Long> a() {
        return this.e;
    }

    public Map<Integer, NaviPath> b() {
        return this.d;
    }

    public int[] c() {
        return this.c;
    }

    public ep(el elVar) {
        this.a = elVar;
    }

    public void d() {
        if (this.b != null) {
            this.b.destroy();
        }
        if (this.d != null) {
            this.d.clear();
        }
        if (this.e != null) {
            this.e.clear();
        }
    }

    @Override // com.autonavi.ae.route.observer.PathRequestObserver
    public void onPathChanged(int i) {
        fr.d("MyRouteObserver-->onPathChanged,err=" + i);
    }

    protected void a(CalcRouteResult calcRouteResult, RoutePoi[] routePoiArr, boolean z) {
        if (calcRouteResult != null) {
            try {
                List<NaviLatLng> a = en.a(routePoiArr);
                int routeCount = calcRouteResult.getRouteCount();
                if (routeCount > 0) {
                    this.e.clear();
                    this.d.clear();
                    if (z) {
                        this.c = new int[routeCount];
                        for (int i = 0; i < routeCount; i++) {
                            NaviPath a2 = a(calcRouteResult.getRoute(i), a);
                            this.c[i] = i + 12;
                            this.e.put(Integer.valueOf(i + 12), Long.valueOf(a2.getId()));
                            this.d.put(Integer.valueOf(i + 12), a2);
                        }
                        return;
                    }
                    this.c = new int[1];
                    NaviPath a3 = a(calcRouteResult.getRoute(0), a);
                    this.c[0] = 12;
                    this.e.put(12, Long.valueOf(a3.getId()));
                    this.d.put(12, a3);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private NaviPath a(Route route, List<NaviLatLng> list) {
        try {
            LabelInfo[] pathLabel = route.getPathLabel();
            StringBuilder sb = new StringBuilder();
            for (LabelInfo labelInfo : pathLabel) {
                sb.append(labelInfo.mContent).append(",");
            }
            String sb2 = sb.toString();
            String substring = sb2.substring(0, sb2.length() - 1);
            ArrayList arrayList = new ArrayList();
            for (TmcBarItem tmcBarItem : route.getTmcBarItem()) {
                arrayList.add(new AMapTrafficStatus(tmcBarItem));
            }
            ArrayList arrayList2 = new ArrayList();
            GroupSegment[] groupSegmentList = route.getGroupSegmentList();
            for (GroupSegment groupSegment : groupSegmentList) {
                NaviGuide naviGuide = new NaviGuide();
                RouteSegment segment = route.getSegment(groupSegment.startSegId);
                naviGuide.setName(groupSegment.groupName);
                naviGuide.setLength(groupSegment.distance);
                naviGuide.setTime(segment.getSegTime());
                naviGuide.setCoord(new NaviLatLng(segment.getStartPoint().getLatitude(), segment.getStartPoint().getLongitude()));
                naviGuide.setIconType(segment.getMainAction());
                arrayList2.add(new AMapNaviGuide(naviGuide));
            }
            RouteCamera[] allCamera = route.getAllCamera();
            ArrayList arrayList3 = new ArrayList();
            if (allCamera != null) {
                for (RouteCamera routeCamera : allCamera) {
                    arrayList3.add(new AMapNaviCameraInfo(routeCamera));
                }
            }
            NaviPath naviPath = new NaviPath();
            naviPath.setGuideList(arrayList2);
            naviPath.setTrafficStatus(arrayList);
            naviPath.setId(route.getRouteId());
            naviPath.setAllLength(route.getRouteLength());
            naviPath.setAllTime(route.getRouteTime());
            naviPath.setStepsCount(route.getSegmentCount());
            naviPath.setTollCost(route.getTollCost());
            GeoPoint endPoint = route.getEndPoint();
            naviPath.setEndPoint(new NaviLatLng(endPoint.getLatitude(), endPoint.getLongitude()));
            naviPath.setWayPoint(list);
            naviPath.setStrategy(route.getRouteStrategy());
            naviPath.setLabels(substring);
            naviPath.setRestrictionInfo(route.getRestrictionInfo());
            naviPath.setCameras(arrayList3);
            naviPath.setCityAdcodeList(route.getCityAdcodeList());
            int i = -1;
            int segmentCount = route.getSegmentCount();
            ArrayList arrayList4 = new ArrayList();
            ArrayList arrayList5 = new ArrayList();
            for (int i2 = 0; i2 < segmentCount; i2++) {
                AMapNaviStep aMapNaviStep = new AMapNaviStep();
                RouteSegment segment2 = route.getSegment(i2);
                aMapNaviStep.setChargeLength(segment2.getSegChargeLength());
                aMapNaviStep.setTime(segment2.getSegTime());
                double[] segCoor = segment2.getSegCoor();
                ArrayList arrayList6 = new ArrayList();
                if (segCoor != null) {
                    for (int i3 = 0; i3 < segCoor.length - 1; i3 += 2) {
                        arrayList6.add(new NaviLatLng(segCoor[i3 + 1], segCoor[i3]));
                    }
                }
                aMapNaviStep.setCoords(arrayList6);
                aMapNaviStep.setLength(segment2.getSegLength());
                ArrayList arrayList7 = new ArrayList();
                int linkCount = segment2.getLinkCount();
                aMapNaviStep.setStartIndex(i + 1);
                for (int i4 = 0; i4 < linkCount; i4++) {
                    RouteLink link = segment2.getLink(i4);
                    AMapNaviLink aMapNaviLink = new AMapNaviLink();
                    aMapNaviLink.setRoadClass(link.getLinkRoadClass());
                    aMapNaviLink.setRoadName(link.getLinkRoadName());
                    aMapNaviLink.setTrafficLights(link.haveTrafficLights());
                    double[] linkCoor = link.getLinkCoor();
                    ArrayList arrayList8 = new ArrayList();
                    for (int i5 = 0; i5 < linkCoor.length - 1; i5 += 2) {
                        NaviLatLng naviLatLng = new NaviLatLng(linkCoor[i5 + 1], linkCoor[i5]);
                        arrayList8.add(naviLatLng);
                        arrayList5.add(naviLatLng);
                        i++;
                    }
                    aMapNaviLink.setCoords(arrayList8);
                    arrayList7.add(aMapNaviLink);
                }
                aMapNaviStep.setLinks(arrayList7);
                aMapNaviStep.setEndIndex(i);
                arrayList4.add(aMapNaviStep);
            }
            naviPath.setListStep(arrayList4);
            GeoPoint startPoint = route.getStartPoint();
            naviPath.setStartPoint(new NaviLatLng(startPoint.getLatitude(), startPoint.getLongitude()));
            naviPath.setList(arrayList5);
            double[] routeBound = route.getRouteBound();
            naviPath.setBounds(LatLngBounds.builder().include(new LatLng(routeBound[1], routeBound[0])).include(new LatLng(routeBound[3], routeBound[2])).build());
            return naviPath;
        } catch (Throwable th) {
            th.printStackTrace();
            gr.b(th, "WTBTControl", "initNaviPath()");
            return null;
        }
    }

    @Override // com.autonavi.ae.route.observer.RouteObserver
    public void onNewRoute(int i, CalcRouteResult calcRouteResult, Object obj, boolean z) {
        try {
            fr.a("MyRouteObserver-->onNewRoute(),type=" + i + ",count=" + calcRouteResult.getRouteCount());
            this.b = calcRouteResult;
            switch (i) {
                case 1:
                    a(this.b, em.c(), this.a.isCalculateMultipleRoutes());
                    break;
                case 2:
                case 3:
                case 4:
                case 5:
                case 7:
                case 8:
                case 9:
                case 10:
                case 11:
                    a(this.b, em.c(), false);
                    if (this.f != null) {
                        this.f.obtainMessage(5).sendToTarget();
                        break;
                    }
                    break;
                case 6:
                    a(this.b, em.c(), false);
                    break;
            }
            if (this.f == null || this.c == null || this.c.length <= 0) {
                if (this.f != null) {
                    this.f.obtainMessage(4, i, 404).sendToTarget();
                }
            } else if (!this.a.isCalculateMultipleRoutes() || i != 1) {
                this.a.c(this.c[0]);
                this.f.obtainMessage(3).sendToTarget();
            } else {
                this.f.obtainMessage(2).sendToTarget();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // com.autonavi.ae.route.observer.RouteObserver
    public void onNewRouteError(int i, int i2, Object obj, boolean z) {
        fr.d("MyRouteObserver-->onNewRouteError(),type=" + i + ",errorCode" + i2);
        if (i2 == 13) {
            i2 = 19;
        } else if (i2 == 16) {
            i2 = 2;
        } else if (i2 == 19) {
            i2 = 20;
        } else if (i2 == 23) {
            i2 = 27;
        }
        fi.a("v3/ae8/driving", i2);
        if (this.f != null && i2 != 27) {
            this.f.obtainMessage(4, i, i2).sendToTarget();
        }
    }

    @Override // com.autonavi.ae.route.observer.HttpInterface
    public boolean requestHttpGet(int i, int i2, String str) {
        return false;
    }

    @Override // com.autonavi.ae.route.observer.HttpInterface
    public boolean requestHttpPost(int i, int i2, String str, byte[] bArr) {
        try {
            int[] a = fp.a(em.e());
            String replaceAll = new String(bArr, "UTF-8").replaceAll("Type=\"4\"", "Type=\"" + a[0] + "\"").replaceAll("Flag=\"135352\"", "Flag=\"" + a[1] + "\"");
            fr.a("MyRouteObserver-->requestHttpPost(请求参数)" + new String(replaceAll.getBytes("UTF-8"), "UTF-8"));
            ii a2 = en.a(this.a.d(), 1, en.a(2, "1.0", replaceAll.getBytes("UTF-8")));
            int a3 = fi.a(en.a, a2);
            if (a3 < 0) {
                eq eqVar = new eq(i, i2, a2.a);
                if (this.f != null) {
                    this.f.obtainMessage(1, eqVar).sendToTarget();
                }
            } else if (this.f != null) {
                this.f.obtainMessage(4, 0, a3).sendToTarget();
            }
        } catch (Exception e) {
            e.printStackTrace();
            gr.b(e, "rObserver", "rhp");
        }
        return false;
    }
}
