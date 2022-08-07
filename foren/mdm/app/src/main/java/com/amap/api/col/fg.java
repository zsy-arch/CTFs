package com.amap.api.col;

import android.content.Context;
import android.location.Location;
import android.location.LocationManager;
import android.text.TextUtils;
import com.amap.api.maps.MapsInitializer;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.LatLngBounds;
import com.amap.api.navi.AMapNaviListener;
import com.amap.api.navi.model.AMapNaviGuide;
import com.amap.api.navi.model.AMapNaviLink;
import com.amap.api.navi.model.AMapNaviPath;
import com.amap.api.navi.model.AMapNaviStep;
import com.amap.api.navi.model.NaviGuide;
import com.amap.api.navi.model.NaviInfo;
import com.amap.api.navi.model.NaviLatLng;
import com.amap.api.navi.model.NaviPath;
import com.amap.api.services.geocoder.GeocodeSearch;
import com.autonavi.wtbt.IFrameForWTBT;
import com.autonavi.wtbt.NaviGuideItem;
import com.autonavi.wtbt.WTBT;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

/* compiled from: WTBTControl.java */
/* loaded from: classes.dex */
public class fg implements fa {
    private NaviPath b;
    private Context d;
    private IFrameForWTBT e;
    private NaviLatLng f;
    private int c = -1;
    private List<AMapNaviGuide> g = new ArrayList();
    private WTBT a = new WTBT();

    public fg(Context context) {
        this.d = context;
        fr.a("TbtControl-->WTBTControl(构造函数)");
        this.e = new eu(this.d, this);
    }

    public IFrameForWTBT e() {
        return this.e;
    }

    @Override // com.amap.api.col.ex
    public void a() {
        try {
            if (this.d != null) {
                if (this.a == null) {
                    this.a = new WTBT();
                }
                this.a.setEmulatorSpeed(60);
                if (this.e == null) {
                    this.e = new eu(this.d, this);
                }
                String q = ge.q(this.d);
                if (TextUtils.isEmpty(q)) {
                    q = "00000000";
                }
                int init = this.a.init(this.e, fn.a(this.d).getAbsolutePath() + "/navigation", "AN_AmapSdk_ADR_FC", "0", q, "");
                int param = this.a.setParam("userid", "AN_AmapSdk_ADR_FC");
                int param2 = this.a.setParam("userpwd", "amapsdk");
                String f = ga.f(this.d);
                if (!TextUtils.isEmpty(f)) {
                    MapsInitializer.setApiKey(f);
                }
                if (init == 0 || param == 0 || param2 == 0) {
                    this.e.c();
                    fr.a("WTBTControl-initSuccess()");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // com.amap.api.col.ex
    public synchronized void b() {
        ip.a();
        if (this.a != null) {
            this.a.destroy();
            this.a = null;
        }
        if (this.e != null) {
            this.e.a();
            this.e = null;
        }
        if (this.g != null) {
            this.g.clear();
            this.g = null;
        }
        this.b = null;
        this.d = null;
        this.f = null;
    }

    @Override // com.amap.api.col.ex
    public boolean a(int i) {
        boolean z = true;
        try {
            this.c = i;
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (i == 1) {
            if (this.a.startGPSNavi() != 1) {
                z = false;
            }
            if (!z || this.e == null) {
                return z;
            }
            this.e.a(i);
            return z;
        }
        if (i == 2) {
            if (this.a.startEmulatorNavi() != 1) {
                z = false;
            }
            if (!z || this.e == null) {
                return z;
            }
            this.e.a(i);
            return z;
        }
        return false;
    }

    @Override // com.amap.api.col.ex
    public void h() {
        if (this.a != null) {
            this.a.pauseNavi();
        }
    }

    @Override // com.amap.api.col.ex
    public void i() {
        if (this.a != null) {
            this.a.stopNavi();
        }
    }

    @Override // com.amap.api.col.ex
    public void j() {
        if (this.a != null) {
            this.a.resumeNavi();
        }
    }

    @Override // com.amap.api.col.ex
    public void a(int i, double d, double d2) {
        if (this.a != null) {
            fr.a("WTBTControl setCarLocation(WTBT)");
            this.a.setCarLocation(i, d, d2);
        }
    }

    @Override // com.amap.api.col.fa
    public boolean a(NaviLatLng naviLatLng, NaviLatLng naviLatLng2) {
        try {
            if (!(this.a == null || naviLatLng == null || naviLatLng2 == null)) {
                double[] dArr = {naviLatLng.getLongitude(), naviLatLng.getLatitude()};
                double[] dArr2 = {naviLatLng2.getLongitude(), naviLatLng2.getLatitude()};
                this.f = naviLatLng2;
                return this.a.requestRouteWithStart(0, 0, 1, dArr, 1, dArr2, 0, null) == 1;
            }
        } catch (Exception e) {
            e.printStackTrace();
            gr.b(e, "wtbt", "cwr");
        }
        return false;
    }

    @Override // com.amap.api.col.fa
    public boolean a(NaviLatLng naviLatLng) {
        if (naviLatLng != null) {
            try {
                double[] dArr = {naviLatLng.getLongitude(), naviLatLng.getLatitude()};
                this.f = naviLatLng;
                return this.a.requestRoute(0, 0, 1, dArr, 0, null) == 1;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public void f() {
        if (this.a != null) {
            this.a.reroute(0, 0);
        }
    }

    @Override // com.amap.api.col.ex
    public AMapNaviPath k() {
        if (this.b != null) {
            return this.b.amapNaviPath;
        }
        return null;
    }

    @Override // com.amap.api.col.ex
    public List<AMapNaviGuide> l() {
        NaviGuideItem[] naviGuideList;
        try {
            if (!(this.a == null || (naviGuideList = this.a.getNaviGuideList()) == null || naviGuideList.length <= 0)) {
                this.g.clear();
                for (NaviGuideItem naviGuideItem : naviGuideList) {
                    this.g.add(new NaviGuide(naviGuideItem).aMapNaviGuide);
                }
                return this.g;
            }
        } catch (Exception e) {
            e.printStackTrace();
            gr.b(e, "wtbt", "gngl");
        }
        return null;
    }

    @Override // com.amap.api.col.ex
    public void b(int i) {
        if (this.a != null && i > 9 && i < 121) {
            this.a.setEmulatorSpeed(i);
        }
    }

    @Override // com.amap.api.col.ex
    public void d(int i) {
        if (this.a != null) {
            this.a.setTimeForOneWord(i);
        }
    }

    @Override // com.amap.api.col.ex
    public void a(AMapNaviListener aMapNaviListener) {
        if (this.e != null) {
            this.e.a(aMapNaviListener);
        }
    }

    @Override // com.amap.api.col.ex
    public void b(AMapNaviListener aMapNaviListener) {
        if (this.e != null) {
            this.e.b(aMapNaviListener);
        }
    }

    @Override // com.amap.api.col.ex
    public int c(int i) {
        try {
            if (this.a != null) {
                int selectRoute = this.a.selectRoute(i);
                if (selectRoute == -1) {
                    return selectRoute;
                }
                m();
                return selectRoute;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

    private NaviPath m() {
        this.b = new NaviPath();
        try {
            this.b.setAllLength(this.a.getRouteLength());
            this.b.setAllTime(this.a.getRouteTime());
            this.b.setStepsCount(this.a.getSegNum());
            this.b.setEndPoint(this.f);
            this.b.setStrategy(3);
            int segNum = this.a.getSegNum();
            ArrayList arrayList = new ArrayList();
            ArrayList arrayList2 = new ArrayList();
            int i = -1;
            double d = Double.MIN_VALUE;
            double d2 = Double.MAX_VALUE;
            double d3 = Double.MIN_VALUE;
            double d4 = Double.MAX_VALUE;
            if (this.b.getWayPoint() != null) {
                this.b.amapNaviPath.wayPointIndex = new int[this.b.getWayPoint().size()];
            }
            int i2 = 0;
            for (int i3 = 0; i3 < segNum; i3++) {
                AMapNaviStep aMapNaviStep = new AMapNaviStep();
                aMapNaviStep.setChargeLength(this.a.getSegChargeLength(i3));
                i2 += this.a.getSegTollCost(i3);
                aMapNaviStep.setTime(this.a.getSegTime(i3));
                double[] segCoor = this.a.getSegCoor(i3);
                ArrayList arrayList3 = new ArrayList();
                if (segCoor != null) {
                    for (int i4 = 0; i4 < segCoor.length - 1; i4 += 2) {
                        arrayList3.add(new NaviLatLng(segCoor[i4 + 1], segCoor[i4]));
                    }
                }
                aMapNaviStep.setCoords(arrayList3);
                aMapNaviStep.setLength(this.a.getSegLength(i3));
                ArrayList arrayList4 = new ArrayList();
                int segLinkNum = this.a.getSegLinkNum(i3);
                aMapNaviStep.setStartIndex(i + 1);
                int i5 = 0;
                while (i5 < segLinkNum) {
                    AMapNaviLink aMapNaviLink = new AMapNaviLink();
                    aMapNaviLink.setLength(this.a.getLinkLength(i3, i5));
                    aMapNaviLink.setTime(this.a.getLinkTime(i3, i5));
                    aMapNaviLink.setRoadClass(this.a.getLinkRoadClass(i3, i5));
                    aMapNaviLink.setRoadType(this.a.getLinkFormWay(i3, i5));
                    aMapNaviLink.setRoadName(this.a.getLinkRoadName(i3, i5));
                    aMapNaviLink.setTrafficLights(this.a.haveTrafficLights(i3, i5) == 1);
                    double[] linkCoor = this.a.getLinkCoor(i3, i5);
                    ArrayList arrayList5 = new ArrayList();
                    int i6 = 0;
                    int i7 = i;
                    double d5 = d4;
                    while (i6 < linkCoor.length - 1) {
                        double d6 = linkCoor[i6 + 1];
                        double d7 = linkCoor[i6];
                        if (d < d6) {
                            d = d6;
                        }
                        if (d3 < d7) {
                            d3 = d7;
                        }
                        if (d2 > d6) {
                            d2 = d6;
                        }
                        if (d5 > d7) {
                            d5 = d7;
                        }
                        NaviLatLng naviLatLng = new NaviLatLng(d6, d7);
                        arrayList5.add(naviLatLng);
                        arrayList2.add(naviLatLng);
                        i6 += 2;
                        i7++;
                    }
                    aMapNaviLink.setCoords(arrayList5);
                    arrayList4.add(aMapNaviLink);
                    i5++;
                    d4 = d5;
                    i = i7;
                }
                aMapNaviStep.setEndIndex(i);
                this.b.setWayPoint(null);
                aMapNaviStep.setLinks(arrayList4);
                arrayList.add(aMapNaviStep);
            }
            this.b.getMaxCoordForPath().setLatitude(d);
            this.b.getMaxCoordForPath().setLongitude(d3);
            this.b.getMinCoordForPath().setLatitude(d2);
            this.b.getMinCoordForPath().setLongitude(d4);
            this.b.setTollCost(i2);
            this.b.setListStep(arrayList);
            if (arrayList2 != null && arrayList2.size() > 0) {
                this.b.setStartPoint((NaviLatLng) arrayList2.get(0));
            }
            this.b.setList(arrayList2);
            NaviLatLng a = fn.a(this.b.getMinCoordForPath().getLatitude(), this.b.getMinCoordForPath().getLongitude(), this.b.getMaxCoordForPath().getLatitude(), this.b.getMaxCoordForPath().getLongitude());
            this.b.setBounds(new LatLngBounds(new LatLng(this.b.getMinCoordForPath().getLatitude(), this.b.getMinCoordForPath().getLongitude()), new LatLng(this.b.getMaxCoordForPath().getLatitude(), this.b.getMaxCoordForPath().getLongitude())));
            this.b.setCenter(a);
        } catch (Throwable th) {
            th.printStackTrace();
            gr.b(th, "WTBTControl", "initNaviPath()");
        }
        return this.b;
    }

    @Override // com.amap.api.col.ex
    public void a(int i, Location location) {
        try {
            Calendar instance = Calendar.getInstance(Locale.CHINA);
            instance.setTimeInMillis(location.getTime());
            int i2 = instance.get(1);
            int i3 = instance.get(2) + 1;
            int i4 = instance.get(5);
            int i5 = instance.get(11);
            int i6 = instance.get(12);
            int i7 = instance.get(13);
            if (this.c == 1) {
                fr.a("WTBTControl setGpsInfo(WTBT)");
                this.a.setGPSInfo(i, (int) location.getAccuracy(), 0.0d, location.getLongitude(), location.getLatitude(), location.getSpeed() * 3.6d, location.getBearing(), i2, i3, i4, i5, i6, i7);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // com.amap.api.col.ex
    public NaviInfo c() {
        if (this.e != null) {
            return this.e.d();
        }
        return null;
    }

    public WTBT g() {
        return this.a;
    }

    @Override // com.amap.api.col.fa
    public void d() {
        try {
            if (((LocationManager) this.d.getSystemService("location")).isProviderEnabled(GeocodeSearch.GPS)) {
                this.e.a(true);
            } else {
                this.e.a(false);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
