package com.amap.api.col;

import android.content.Context;
import android.graphics.Bitmap;
import android.text.Html;
import android.text.Spanned;
import com.amap.api.col.fc;
import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.MapView;
import com.amap.api.maps.model.LatLng;
import com.amap.api.navi.AMapNavi;
import com.amap.api.navi.INavi;
import com.amap.api.navi.MyNaviListener;
import com.amap.api.navi.model.AMapLaneInfo;
import com.amap.api.navi.model.AMapModelCross;
import com.amap.api.navi.model.AMapNaviCameraInfo;
import com.amap.api.navi.model.AMapNaviCross;
import com.amap.api.navi.model.AMapNaviInfo;
import com.amap.api.navi.model.AMapNaviLocation;
import com.amap.api.navi.model.AMapNaviPath;
import com.amap.api.navi.model.AMapNaviTrafficFacilityInfo;
import com.amap.api.navi.model.AMapServiceAreaInfo;
import com.amap.api.navi.model.AMapTrafficStatus;
import com.amap.api.navi.model.AimLessModeCongestionInfo;
import com.amap.api.navi.model.AimLessModeStat;
import com.amap.api.navi.model.AmapCarLocation;
import com.amap.api.navi.model.NaviInfo;
import com.amap.api.navi.model.NaviLatLng;
import com.amap.api.navi.model.RouteOverlayOptions;
import com.amap.api.navi.view.AmapCameraOverlay;
import com.amap.api.navi.view.RouteOverLay;
import com.amap.api.navi.view.TrafficBarView;
import com.autonavi.amap.mapcore.IPoint;
import com.autonavi.amap.mapcore.MapProjection;
import com.autonavi.tbt.TrafficFacilityInfo;
import com.hyphenate.util.HanziToPinyin;
import java.util.List;

/* compiled from: NaviUIController.java */
/* loaded from: classes.dex */
public class fd implements fc.a, MyNaviListener {
    private LatLng A;
    private LatLng B;
    private NaviInfo a;
    private RouteOverLay j;
    private fb k;
    private AmapCameraOverlay l;
    private INavi m;
    private AMap n;
    private Context o;
    private es p;
    private AMapNaviPath r;
    private AMapNaviPath t;

    /* renamed from: u  reason: collision with root package name */
    private int f20u;
    private fc v;
    private boolean w;
    private boolean b = false;
    private String c = "#ffffff";
    private String d = "#ffffff";
    private int e = 1;
    private boolean f = true;
    private boolean g = true;
    private boolean h = true;
    private float i = 0.0f;
    private boolean q = false;
    private int s = -1;
    private float x = 0.0f;
    private boolean y = false;
    private boolean z = false;

    public fd(Context context, MapView mapView, es esVar) {
        this.m = null;
        if (esVar != null) {
            this.o = context.getApplicationContext();
            this.j = new RouteOverLay(mapView.getMap(), null, this.o);
            this.k = new fb(mapView, esVar);
            this.l = new AmapCameraOverlay(context);
            this.m = AMapNavi.getInstance(this.o);
            this.p = esVar;
            this.n = mapView.getMap();
            if (this.n == null) {
                fr.d("NaviUIControl-->构造函数 amap==null");
            }
            this.v = new fc(this.o);
            this.v.a(this);
        }
    }

    @Override // com.amap.api.navi.AMapNaviListener
    public void onArriveDestination() {
        fr.a("NaviUIControl-->onArriveDestination()");
        if (this.e != 2) {
            if (this.j != null) {
                this.j.removeFromMap();
            }
            if (this.l != null) {
                this.l.destroy();
            }
            c(false);
            this.p.d();
            this.a = null;
            if (this.k != null) {
                this.k.e();
            }
            this.b = false;
        }
    }

    @Override // com.amap.api.navi.AMapNaviListener
    public void onArrivedWayPoint(int i) {
        fr.a("NaviUIControl-->onArrivedWayPoint(" + i + ")");
    }

    @Override // com.amap.api.navi.AMapNaviListener
    public void onCalculateRouteFailure(int i) {
        fr.b("NaviUIControl-->onCalculateRouteFailure(),errorCode=" + i);
        i();
    }

    @Override // com.amap.api.navi.AMapNaviListener
    public void onCalculateRouteSuccess() {
        fr.b("NaviUIControl-->onCalculateRouteSuccess(),算路成功start");
        i();
        if (this.n == null || this.m == null) {
            fr.b(new StringBuilder().append("NaviUIControl-->").append(this.n).toString() == null ? "true" : "false");
            fr.b(new StringBuilder().append("NaviUIControl-->").append(this.m).toString() == null ? "true" : "false");
            return;
        }
        AMapNaviPath naviPath = this.m.getNaviPath();
        if (naviPath != null) {
            this.f20u = naviPath.getAllLength();
            a(naviPath);
            if (this.k != null) {
                this.k.b(this.m.getEngineType());
            }
            a();
            this.s = -1;
            hideCross();
        }
        fr.b("NaviUIControl-->onCalculateRouteSuccess(),算路成功end");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a() {
        if (this.m != null) {
            this.r = this.m.getNaviPath();
            if (this.p != null && this.m.getEngineType() == 0) {
                a(this.p.getLazyTrafficBarView());
                a(this.p.p);
            }
        }
    }

    private void a(TrafficBarView trafficBarView) {
        if (this.r != null && trafficBarView != null) {
            List<AMapTrafficStatus> trafficStatuses = this.m.getTrafficStatuses(this.r.getAllLength() - this.f20u, this.r.getAllLength());
            fr.a("AE8", "trafficBarView.update~");
            trafficBarView.update(trafficStatuses, this.f20u);
        }
    }

    public void a(boolean z) {
        this.g = z;
    }

    public void b(boolean z) {
        this.h = z;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a(AMapNaviPath aMapNaviPath) {
        if (aMapNaviPath != this.t && this.g && aMapNaviPath != null) {
            if (this.j != null) {
                this.j.setAMapNaviPath(aMapNaviPath);
                this.j.addToMap();
            }
            LatLng latLng = null;
            if (!(aMapNaviPath.getStartPoint() == null || aMapNaviPath.getEndPoint() == null)) {
                latLng = new LatLng(aMapNaviPath.getStartPoint().getLatitude(), aMapNaviPath.getStartPoint().getLongitude());
            }
            if (latLng != null) {
                this.k.c();
                fb fbVar = this.k;
                AMap aMap = this.n;
                this.B = latLng;
                fbVar.a(aMap, latLng, this.i);
                if (aMapNaviPath.getEndPoint() != null) {
                    this.k.a(new LatLng(aMapNaviPath.getEndPoint().getLatitude(), aMapNaviPath.getEndPoint().getLongitude()));
                }
            }
            if (this.p.f16u != null) {
                this.p.f16u.setText(Html.fromHtml(fn.a(aMapNaviPath.getAllLength(), this.c, this.d)));
            }
            if (this.p.v != null) {
                this.p.v.setText(Html.fromHtml(fn.a(fn.b(aMapNaviPath.getAllTime()), this.c, this.d)));
            }
            this.t = aMapNaviPath;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void b() {
        if (this.j == null) {
            return;
        }
        if (!this.g) {
            this.j.zoomToSpan(100, this.r);
            return;
        }
        this.j.setAMapNaviPath(this.r);
        this.j.zoomToSpan();
    }

    @Override // com.amap.api.navi.AMapNaviListener
    public void onEndEmulatorNavi() {
        fr.b("NaviUIControl-->onEndEmulatorNavi()");
        if (this.l != null) {
            this.l.destroy();
        }
    }

    @Override // com.amap.api.navi.AMapNaviListener
    public void onGetNavigationText(int i, String str) {
        fr.b("NaviUIControl-->onGetNavigationText(),msg=" + str);
    }

    @Override // com.amap.api.navi.AMapNaviListener
    public void onInitNaviFailure() {
        fr.b("NaviUIControl-->onInitNaviFailure()");
    }

    @Override // com.amap.api.navi.AMapNaviListener
    public void onInitNaviSuccess() {
        fr.b("NaviUIControl-->onInitNaviSuccess()");
    }

    public void c() {
        this.j.setEmulateGPSLocationVisible();
    }

    @Override // com.amap.api.navi.AMapNaviListener
    public void onLocationChange(AMapNaviLocation aMapNaviLocation) {
        if (aMapNaviLocation != null && this.m != null) {
            if (this.A != null && this.m.getEngineType() == 1 && this.m.getNaviType() == 1) {
                LatLng latLng = new LatLng(aMapNaviLocation.getCoord().getLatitude(), aMapNaviLocation.getCoord().getLongitude());
                if (!aMapNaviLocation.isMatchNaviPath()) {
                    this.j.drawGuideLink(latLng, this.A, true);
                } else {
                    this.j.drawGuideLink(latLng, this.A, false);
                    this.A = null;
                }
            }
            NaviLatLng coord = aMapNaviLocation.getCoord();
            float bearing = aMapNaviLocation.getBearing();
            LatLng latLng2 = new LatLng(coord.getLatitude(), coord.getLongitude());
            if (this.m.getEngineType() == 1 || this.m.getEngineType() == 2) {
                if (!this.w || !this.y) {
                    this.k.a(this.n, latLng2, bearing);
                } else {
                    this.B = latLng2;
                }
            } else if (this.m.getEngineType() == 0) {
                this.k.a(this.n, latLng2, bearing);
            }
        }
    }

    float a(List<NaviLatLng> list) {
        if (list == null || list.size() < 2) {
            return 0.0f;
        }
        NaviLatLng naviLatLng = null;
        NaviLatLng naviLatLng2 = null;
        int size = list.size();
        if (size == 2) {
            naviLatLng = list.get(0);
            naviLatLng2 = list.get(1);
        } else if (size > 2) {
            naviLatLng = list.get(0);
            naviLatLng2 = list.get(size - 1);
        }
        IPoint iPoint = new IPoint();
        IPoint iPoint2 = new IPoint();
        MapProjection.lonlat2Geo(naviLatLng.getLongitude(), naviLatLng.getLatitude(), iPoint2);
        MapProjection.lonlat2Geo(naviLatLng2.getLongitude(), naviLatLng2.getLatitude(), iPoint);
        double d = iPoint2.x - iPoint.x;
        double d2 = iPoint2.y - iPoint.y;
        double acos = 180.0d / (3.141592653589793d / Math.acos(d / Math.sqrt(Math.pow(d, 2.0d) + Math.pow(d2, 2.0d))));
        if (d2 < 0.0d) {
            acos = -acos;
        } else if (d2 == 0.0d && d < 0.0d) {
            acos = 180.0d;
        }
        if (acos < 0.0d) {
            acos = 360.0d - Math.abs(acos);
        }
        return (float) (acos - 90.0d);
    }

    @Override // com.amap.api.navi.AMapNaviListener
    public void onNaviInfoUpdate(NaviInfo naviInfo) {
        if (naviInfo != null && this.m != null) {
            try {
                if (this.m.getEngineType() == 1 || this.m.getEngineType() == 2) {
                    this.k.a(a(this.m.getNaviPath().getSteps().get(naviInfo.getCurStep()).getLinks().get(naviInfo.getCurLink()).getCoords()));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            this.a = naviInfo;
            this.f20u = this.a.getPathRetainDistance();
            b(naviInfo);
            e(naviInfo);
            if (this.p != null) {
                if (!this.p.f()) {
                }
                if (this.p.isAutoChangeZoom()) {
                    c(naviInfo);
                }
                a(naviInfo);
                d(naviInfo);
            }
        }
    }

    @Override // com.amap.api.navi.AMapNaviListener
    public void onNaviInfoUpdated(AMapNaviInfo aMapNaviInfo) {
    }

    @Override // com.amap.api.navi.AMapNaviListener
    public void updateCameraInfo(AMapNaviCameraInfo[] aMapNaviCameraInfoArr) {
        try {
            if (this.z && aMapNaviCameraInfoArr != null) {
                RouteOverlayOptions routeOverlayOptions = this.j.getRouteOverlayOptions();
                if (routeOverlayOptions == null || routeOverlayOptions.isShowCameOnRoute()) {
                    for (AMapNaviCameraInfo aMapNaviCameraInfo : aMapNaviCameraInfoArr) {
                        this.l.draw(this.n, aMapNaviCameraInfo, this.a.getCurrentSpeed());
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // com.amap.api.navi.AMapNaviListener
    public void onServiceAreaUpdate(AMapServiceAreaInfo[] aMapServiceAreaInfoArr) {
    }

    private void a(NaviInfo naviInfo) {
        if (this.p.c != null) {
            this.p.c.setIconType(naviInfo.getIconType());
        }
        if (this.p.getLazyNextTurnTipView() != null) {
            this.p.getLazyNextTurnTipView().setIconType(naviInfo.getIconType());
        }
    }

    private void b(NaviInfo naviInfo) {
        if (this.g || this.s != naviInfo.getCurStep()) {
            try {
                List<NaviLatLng> arrowPoints = this.j.getArrowPoints(naviInfo.getCurStep());
                if (arrowPoints != null && arrowPoints.size() > 0) {
                    this.j.drawArrow(arrowPoints);
                    this.s = naviInfo.getCurStep();
                }
            } catch (Throwable th) {
                th.printStackTrace();
                gr.b(th, "NaviUIController", "drawArrow(NaviInfo naviInfo)");
            }
        }
    }

    private void c(NaviInfo naviInfo) {
        if (naviInfo.getCurStep() > 0 && !this.p.isRouteOverviewNow()) {
            if (naviInfo.getCurStepRetainDistance() < 50 && !this.b) {
                this.n.moveCamera(CameraUpdateFactory.zoomIn());
                this.p.setLockZoom(this.p.getLockZoom() + 1);
                this.b = true;
            }
            if (naviInfo.getCurStepRetainDistance() > 50 && this.b) {
                this.n.moveCamera(CameraUpdateFactory.zoomOut());
                this.p.setLockZoom(this.p.getLockZoom() - 1);
                this.b = false;
            }
        }
    }

    private void d(NaviInfo naviInfo) {
        if (this.p.d != null) {
            this.p.d.setText(fn.a(naviInfo.getCurStepRetainDistance()));
        }
        if (this.p.e != null) {
            this.p.e.setText(naviInfo.getNextRoadName());
        }
        String b = fn.b(naviInfo.getPathRetainTime());
        Spanned fromHtml = Html.fromHtml(fn.a(b, this.c, this.d));
        Spanned fromHtml2 = Html.fromHtml(fn.a(naviInfo.getPathRetainDistance(), this.c, this.d));
        Spanned fromHtml3 = Html.fromHtml("<big>距离终点:</big><big><big>" + fn.a(b) + HanziToPinyin.Token.SEPARATOR + fn.a(naviInfo.getPathRetainDistance()) + "</big></big>");
        if (this.p.f != null) {
            this.p.f.setText(fromHtml3);
        }
        if (this.p.f16u != null) {
            this.p.f16u.setText(fromHtml2);
        }
        if (this.p.v != null) {
            this.p.v.setText(fromHtml);
        }
    }

    private void e(NaviInfo naviInfo) {
        if (this.m.getEngineType() != 0 && this.m.getNaviType() != 1) {
            NaviLatLng coord = naviInfo.getCoord();
            float direction = naviInfo.getDirection();
            LatLng latLng = new LatLng(coord.getLatitude(), coord.getLongitude(), false);
            this.i = direction;
            if (this.k != null) {
                this.k.a(this.n, latLng, direction);
            }
        }
    }

    @Override // com.amap.api.navi.AMapNaviListener
    public void showCross(AMapNaviCross aMapNaviCross) {
        if (this.p.getLazyZoomInIntersectionView() != null) {
            this.p.getLazyZoomInIntersectionView().setImageBitmap(aMapNaviCross.getBitmap());
            this.p.getLazyZoomInIntersectionView().setVisibility(0);
        }
        this.p.a(aMapNaviCross);
    }

    @Override // com.amap.api.navi.AMapNaviListener
    public void hideCross() {
        if (this.p.getLazyZoomInIntersectionView() != null) {
            this.p.getLazyZoomInIntersectionView().setVisibility(8);
        }
        this.p.b();
    }

    @Override // com.amap.api.navi.MyNaviListener
    public void showModeCross(AMapModelCross aMapModelCross) {
        this.p.a(aMapModelCross);
    }

    @Override // com.amap.api.navi.MyNaviListener
    public void hideModeCross() {
        this.p.c();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a(String str, String str2) {
        this.c = str;
        this.d = str2;
        if (this.a != null) {
            if (this.p.f16u != null) {
                this.p.f16u.setText(Html.fromHtml(fn.a(this.a.getPathRetainDistance(), this.c, this.d)));
            }
            if (this.p.v != null) {
                this.p.v.setText(Html.fromHtml(fn.a(fn.b(this.a.getPathRetainTime()), this.c, this.d)));
            }
        }
    }

    @Override // com.amap.api.navi.AMapNaviListener
    public void onReCalculateRouteForTrafficJam() {
        fr.b("NaviUIControl-->onReCalculateRouteForTrafficJam()");
        this.a = null;
        this.s = -1;
        if (this.l != null) {
            this.l.removeAllCamera();
        }
    }

    @Override // com.amap.api.navi.AMapNaviListener
    public void onReCalculateRouteForYaw() {
        fr.b("NaviUIControl-->onReCalculateRouteForYaw()");
        this.a = null;
        this.s = -1;
        if (this.p.getViewOptions().isReCalculateRouteForYaw()) {
            h();
        }
        if (this.l != null) {
            this.l.removeAllCamera();
        }
    }

    private void h() {
    }

    private void i() {
    }

    @Override // com.amap.api.navi.AMapNaviListener
    public void onStartNavi(int i) {
        fr.b("NaviUIControl-->onStartNavi()");
        this.e = i;
        this.p.a(true);
        this.p.e();
        this.p.a();
        this.p.J = false;
        if (this.m != null && this.m.getEngineType() != 0 && 1 == this.e && this.w) {
            this.v.a();
        }
    }

    @Override // com.amap.api.navi.AMapNaviListener
    public void onTrafficStatusUpdate() {
        a();
        if (this.q) {
            d(this.q);
        }
    }

    public void d() {
        if (this.k != null) {
            this.k.a();
        }
    }

    public void e() {
        if (this.k != null) {
            this.k.b();
        }
    }

    public void c(boolean z) {
        if (this.f != z) {
            this.f = z;
            if (this.k != null) {
                this.k.a(z);
            }
        }
    }

    public void f() {
        fr.b("NaviUIControl-->destroy()");
        i();
        if (this.j != null) {
            this.j.destroy();
        }
        if (this.k != null) {
            this.k.d();
        }
        if (this.l != null) {
            this.l.destroy();
        }
        if (this.v != null) {
            this.v.b();
            this.v = null;
        }
    }

    public void a(Bitmap bitmap) {
        if (this.j != null && bitmap != null) {
            this.j.setStartPointBitmap(bitmap);
        }
    }

    public void b(Bitmap bitmap) {
        if (this.j != null && bitmap != null) {
            this.j.setEndPointBitmap(bitmap);
        }
    }

    public void c(Bitmap bitmap) {
        if (this.j != null && bitmap != null) {
            this.j.setWayPointBitmap(bitmap);
        }
    }

    public void d(Bitmap bitmap) {
        if (this.l != null && bitmap != null) {
            this.l.setCameraBitmap(bitmap);
        }
    }

    @Override // com.amap.api.navi.AMapNaviListener
    public void onGpsOpenStatus(boolean z) {
    }

    @Override // com.amap.api.navi.AMapNaviListener
    public void showLaneInfo(AMapLaneInfo[] aMapLaneInfoArr, byte[] bArr, byte[] bArr2) {
        if (this.p.getLazyDriveWayView() != null) {
            this.p.getLazyDriveWayView().loadDriveWayBitmap(bArr, bArr2);
            this.p.getLazyDriveWayView().setVisibility(0);
        }
        if (this.p.K && this.h && !this.p.L && bArr != null && bArr2 != null && this.p.B != null && this.p.a.getVisibility() != 0) {
            this.p.B.loadDriveWayBitmap(bArr, bArr2);
            this.p.B.setDefaultTopMargin(this.p.e.getHeight());
            this.p.B.setVisibility(0);
        }
    }

    @Override // com.amap.api.navi.AMapNaviListener
    public void hideLaneInfo() {
        if (this.p.getLazyDriveWayView() != null) {
            this.p.getLazyDriveWayView().setVisibility(8);
            this.p.getLazyDriveWayView().recycleResource();
        }
        if (this.p.K && this.h && this.p.B != null) {
            this.p.B.setVisibility(8);
            this.p.B.recycleResource();
        }
    }

    @Override // com.amap.api.navi.AMapNaviListener
    public void onCalculateMultipleRoutesSuccess(int[] iArr) {
        fr.b("NaviUIControl-->onCalculateMultipleRoutesSuccess()");
    }

    @Override // com.amap.api.navi.AMapNaviListener
    public void notifyParallelRoad(int i) {
    }

    @Override // com.amap.api.navi.AMapNaviListener
    public void OnUpdateTrafficFacility(AMapNaviTrafficFacilityInfo aMapNaviTrafficFacilityInfo) {
    }

    @Override // com.amap.api.navi.AMapNaviListener
    public void OnUpdateTrafficFacility(AMapNaviTrafficFacilityInfo[] aMapNaviTrafficFacilityInfoArr) {
    }

    @Override // com.amap.api.navi.AMapNaviListener
    public void OnUpdateTrafficFacility(TrafficFacilityInfo trafficFacilityInfo) {
    }

    @Override // com.amap.api.navi.AMapNaviListener
    public void updateAimlessModeStatistics(AimLessModeStat aimLessModeStat) {
    }

    @Override // com.amap.api.navi.AMapNaviListener
    public void updateAimlessModeCongestionInfo(AimLessModeCongestionInfo aimLessModeCongestionInfo) {
    }

    @Override // com.amap.api.navi.AMapNaviListener
    public void onPlayRing(int i) {
    }

    @Override // com.amap.api.navi.MyNaviListener
    public void carProjectionChange(AmapCarLocation amapCarLocation) {
        this.A = new LatLng(amapCarLocation.m_Latitude, amapCarLocation.m_Longitude);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void d(boolean z) {
        this.q = z;
        if (this.j != null) {
            this.j.setTrafficLine(Boolean.valueOf(this.q));
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a(int i) {
        if (this.k != null) {
            this.k.a(i);
        }
    }

    public void g() {
        if (this.k != null) {
            this.k.f();
        }
    }

    public void e(Bitmap bitmap) {
        if (this.k != null && bitmap != null) {
            this.k.a(bitmap);
        }
    }

    public void f(Bitmap bitmap) {
        boolean z = true;
        boolean z2 = this.k != null;
        if (bitmap == null) {
            z = false;
        }
        if (z && z2) {
            this.k.b(bitmap);
        }
    }

    public void a(RouteOverlayOptions routeOverlayOptions) {
        if (this.j != null) {
            this.j.setRouteOverlayOptions(routeOverlayOptions);
        }
    }

    public void e(boolean z) {
        this.w = z;
    }

    public void f(boolean z) {
        this.z = z;
    }

    @Override // com.amap.api.col.fc.a
    public void a(boolean z, int i, float f) {
        this.y = z;
        if (this.k != null) {
            this.k.a(this.n, this.B, f);
        }
    }
}
