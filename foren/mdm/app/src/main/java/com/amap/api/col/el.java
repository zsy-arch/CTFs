package com.amap.api.col;

import android.content.Context;
import android.location.Location;
import android.text.TextUtils;
import com.amap.api.navi.AMapNaviListener;
import com.amap.api.navi.model.AMapCarInfo;
import com.amap.api.navi.model.AMapNaviGuide;
import com.amap.api.navi.model.AMapNaviPath;
import com.amap.api.navi.model.AMapTrafficStatus;
import com.amap.api.navi.model.NaviInfo;
import com.amap.api.navi.model.NaviLatLng;
import com.amap.api.navi.model.NaviPath;
import com.autonavi.ae.guide.GuideControl;
import com.autonavi.ae.guide.GuideService;
import com.autonavi.ae.guide.model.GuideConfig;
import com.autonavi.ae.pos.GpsInfo;
import com.autonavi.ae.pos.LocManager;
import com.autonavi.ae.route.RouteService;
import com.autonavi.ae.route.model.RouteConfig;
import com.autonavi.rtbt.IAE8;
import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/* compiled from: AE8Control.java */
/* loaded from: classes.dex */
public class el implements IAE8 {
    private Context a;
    private GuideService b;
    private RouteService c;
    private eo d;
    private ep e;
    private List<AMapNaviListener> f;
    private NaviPath g;
    private boolean h = true;
    private boolean i = true;
    private int j = 12;

    public el(Context context) {
        if (context != null) {
            try {
                this.a = context.getApplicationContext();
                this.d = new eo(this);
                this.e = new ep(this);
                this.f = new ArrayList();
                String q = ge.q(this.a);
                q = TextUtils.isEmpty(q) ? "00000000" : q;
                GuideConfig guideConfig = new GuideConfig();
                guideConfig.userBatch = "0";
                guideConfig.userCode = "0";
                guideConfig.UUID = q;
                guideConfig.workPath = m();
                this.b = new GuideService(guideConfig, context);
                this.b.setNaviObserver(this.d);
                this.b.setSoundPlayObserver(this.d);
                this.b.addStatusObserver(this.d);
                this.b.setElecEyeObserver(this.d);
                this.b.registerHttpProcesser(this.d);
                LocManager.init();
                LocManager.setMatchMode(0);
                LocManager.addLocListener(this.d, 1);
                LocManager.addParallelRoadObserver(this.d);
                LocManager.setLogSwitch(1);
                RouteConfig routeConfig = new RouteConfig();
                routeConfig.mDeviceId = q;
                routeConfig.mVehicleId = "";
                routeConfig.mEtaRestrictionSet = 0;
                this.c = new RouteService(routeConfig, this.a);
                this.c.setPathRequestObserver(this.e);
                this.c.setRouteObserver(this.e);
                this.c.registerHttpProcesser(this.e);
            } catch (Exception e) {
                e.printStackTrace();
                gr.b(e, "A8C", "constructor");
            }
        }
    }

    @Override // com.amap.api.col.ex
    public void a() {
        if (this.b != null) {
            this.b.control(GuideControl.GC_TMC, "1");
            this.b.control(GuideControl.GC_TMC_CONGESTION, "1");
            this.b.control(GuideControl.GC_CROSS_DISPLAY_MODE, "1");
            this.b.control("TROPEN", "1");
            this.b.control(GuideControl.GC_EMULATOR_SPEED, "60");
        }
    }

    @Override // com.amap.api.col.ex
    public void b() {
        if (this.f != null) {
            this.f.clear();
            this.f = null;
        }
        LocManager.saveLocStorage();
        LocManager.uninit();
        if (this.c != null) {
            this.c.setPathRequestObserver(null);
            this.c.setRouteObserver(null);
            this.c.destroy();
            this.c = null;
        }
        if (this.b != null) {
            this.b.removeStatusObserver(this.d);
            this.b.stopNavi();
            fr.b("AE8Control-->guideService destroy()");
            this.b.destroy();
            this.b = null;
        }
        if (this.d != null) {
            fr.b("AE8Control-->guideObserver destroy()");
            this.d.b();
            this.d = null;
        }
        if (this.e != null) {
            fr.b("AE8Control-->routeObserver destroy()");
            this.e.d();
            this.e = null;
        }
        this.a = null;
        this.g = null;
    }

    @Override // com.amap.api.col.ex
    public NaviInfo c() {
        if (this.d != null) {
            return this.d.a();
        }
        return null;
    }

    private String m() {
        try {
            File file = new File(fn.a(this.a).getAbsolutePath() + "/AmapSdk");
            if (!file.exists()) {
                file.mkdirs();
            }
            return file.getAbsolutePath();
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public Context d() {
        return this.a;
    }

    public GuideService e() {
        return this.b;
    }

    public RouteService f() {
        return this.c;
    }

    @Override // com.amap.api.col.ex
    public void a(AMapNaviListener aMapNaviListener) {
        if (aMapNaviListener != null) {
            try {
                if (this.f != null && !this.f.contains(aMapNaviListener)) {
                    this.f.add(aMapNaviListener);
                }
            } catch (Throwable th) {
                fn.a(th);
                gr.b(th, "FrameForTBT", "addAMapNaviListener(AMapNaviListener naviListener)");
            }
        }
    }

    @Override // com.amap.api.col.ex
    public void b(AMapNaviListener aMapNaviListener) {
        try {
            if (this.f != null) {
                this.f.remove(aMapNaviListener);
            }
        } catch (Throwable th) {
            fn.a(th);
            gr.b(th, "FrameForTBT", "removeNaviListener(AMapNaviListener naviListener)");
        }
    }

    public List<AMapNaviListener> g() {
        return this.f;
    }

    @Override // com.amap.api.col.ex
    public boolean a(int i) {
        try {
            int[] c = this.e.c();
            if (this.g == null && !isCalculateMultipleRoutes() && c.length == 1) {
                c(c[0]);
            }
            if (this.g == null && isCalculateMultipleRoutes()) {
                c(this.j);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (this.d != null) {
            this.d.a(26, Integer.valueOf(i));
        }
        return true;
    }

    @Override // com.amap.api.col.ex
    public void h() {
        if (this.b != null) {
            this.b.pauseNavi();
        }
    }

    @Override // com.amap.api.col.ex
    public void i() {
        if (this.b != null) {
            this.b.stopNavi();
            this.g = null;
        }
    }

    @Override // com.amap.api.col.ex
    public void j() {
        if (this.b != null) {
            this.b.resumeNavi();
        }
    }

    @Override // com.autonavi.rtbt.IAE8
    public void refreshTrafficStatuses() {
    }

    @Override // com.autonavi.rtbt.IAE8
    public boolean readNaviInfo() {
        if (this.b == null) {
            return true;
        }
        this.b.playNaviManual();
        return true;
    }

    @Override // com.autonavi.rtbt.IAE8
    public boolean readTrafficInfo(int i) {
        if (this.b == null) {
            return true;
        }
        this.b.playTrafficRadioManual(i);
        return true;
    }

    @Override // com.autonavi.rtbt.IAE8
    public void setCarNumber(String str, String str2) {
        if (this.b != null && this.c != null) {
            this.b.control("VehicleID", str.concat(str2));
            this.c.control("VehicleID", str.concat(str2));
            this.b.control("ETARestrictionOpen", "1");
            this.c.control("ETARestrictionOpen", "1");
        }
    }

    @Override // com.autonavi.rtbt.IAE8
    public void setCarInfo(AMapCarInfo aMapCarInfo) {
        if (aMapCarInfo != null && this.b != null && this.c != null) {
            this.b.control("VehicleID", aMapCarInfo.getCarNumber());
            this.c.control("VehicleID", aMapCarInfo.getCarNumber());
            this.b.control("vehicleType", aMapCarInfo.getCarType());
            this.c.control("vehicleType", aMapCarInfo.getCarType());
            this.b.control("ETARestrictionOpen", aMapCarInfo.isRestriction() ? "1" : "0");
            this.c.control("ETARestrictionOpen", aMapCarInfo.isRestriction() ? "1" : "0");
            this.b.control("vehicleHeight", aMapCarInfo.getVehicleHeight());
            this.c.control("vehicleHeight", aMapCarInfo.getVehicleHeight());
            this.b.control("vehicleLoad", aMapCarInfo.getVehicleLoad());
            this.c.control("vehicleLoad", aMapCarInfo.getVehicleLoad());
            this.b.control("VehicleLoadSwitch", aMapCarInfo.isVehicleLoadSwitch() ? "1" : "0");
            this.c.control("VehicleLoadSwitch", aMapCarInfo.isVehicleLoadSwitch() ? "1" : "0");
        }
    }

    @Override // com.autonavi.rtbt.IAE8
    public boolean reCalculateRoute(int i) {
        boolean z = true;
        if (this.c == null) {
            return false;
        }
        new ArrayList().add(em.f());
        fr.a("AE8", "reCalculateRoute(" + i + ")");
        if (i == 6) {
            return 1 == this.b.reroute(i, 4, 0, em.b(), null, null, 0.0d);
        }
        if (1 != this.b.reroute(i, 4, 0, null, null, null, 0.0d)) {
            z = false;
        }
        return z;
    }

    @Override // com.autonavi.rtbt.IAE8
    public List<AMapTrafficStatus> getTrafficStatuses(int i, int i2) {
        try {
            if (this.d != null) {
                List<AMapTrafficStatus> c = this.d.c();
                if (c == null || c.size() <= 0) {
                    return k().getTrafficStatuses();
                }
                return c;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override // com.amap.api.col.ex
    public AMapNaviPath k() {
        try {
            if (this.g != null) {
                return this.g.amapNaviPath;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override // com.amap.api.col.ex
    public List<AMapNaviGuide> l() {
        try {
            if (this.g != null) {
                return this.g.getGuideList();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override // com.amap.api.col.ex
    public void b(int i) {
        if (this.b != null && i > 9 && i < 121) {
            this.b.control(GuideControl.GC_EMULATOR_SPEED, String.valueOf(i));
        }
    }

    @Override // com.amap.api.col.ex
    public int c(int i) {
        try {
            if (!(this.b == null || this.e == null)) {
                this.b.setNaviPath(this.e.a().get(Integer.valueOf(i)).longValue(), 1, em.b(), em.c(), em.d());
                this.g = this.e.b().get(Integer.valueOf(i));
                this.j = i;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return i;
    }

    @Override // com.autonavi.rtbt.IAE8
    public int[] getAllRouteID() {
        try {
            if (this.e != null) {
                return this.e.c();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override // com.autonavi.rtbt.IAE8
    public boolean setBroadcastMode(int i) {
        if (this.b == null) {
            return true;
        }
        this.b.control("PlayStyle", String.valueOf(i));
        return true;
    }

    @Override // com.autonavi.rtbt.IAE8
    public void switchParallelRoad() {
        LocManager.switchParallelRoad(null);
    }

    @Override // com.autonavi.rtbt.IAE8
    public void startAimlessMode(int i) {
        if (this.b != null) {
            this.b.control(GuideControl.GC_CRUISE, "1");
            this.b.control(GuideControl.GC_TRAFFIC_CAMERA_MODE, String.valueOf(i));
        }
    }

    @Override // com.autonavi.rtbt.IAE8
    public void stopAimlessMode() {
        if (this.b != null) {
            this.b.control(GuideControl.GC_CRUISE, "0");
        }
    }

    @Override // com.autonavi.rtbt.IAE8
    public int strategyConvert(boolean z, boolean z2, boolean z3, boolean z4, boolean z5) {
        try {
            int a = fp.a(z, z2, z3, z4);
            this.i = z5;
            return a;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override // com.autonavi.rtbt.IAE8
    public boolean isCalculateMultipleRoutes() {
        return this.h;
    }

    @Override // com.autonavi.rtbt.IAE8
    public HashMap<Integer, AMapNaviPath> getMultipleNaviPathsCalculated() {
        HashMap<Integer, AMapNaviPath> hashMap = new HashMap<>();
        try {
            Map<Integer, NaviPath> b = this.e.b();
            for (Integer num : b.keySet()) {
                int intValue = num.intValue();
                hashMap.put(Integer.valueOf(intValue), b.get(Integer.valueOf(intValue)).amapNaviPath);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return hashMap;
    }

    @Override // com.amap.api.col.ex
    public void a(int i, double d, double d2) {
    }

    @Override // com.amap.api.col.ex
    public void d(int i) {
        if (this.b != null) {
            this.b.control("TTSTimeForOneWord", String.valueOf(i));
        }
    }

    @Override // com.amap.api.col.ex
    public void a(int i, Location location) {
        try {
            fr.a("AE8", "offsetFlag=" + i + ",setGpsInfo(" + location.getLongitude() + "," + location.getLatitude() + ")");
            Calendar instance = Calendar.getInstance(Locale.CHINA);
            instance.setTimeInMillis(location.getTime());
            int i2 = instance.get(1);
            int i3 = instance.get(5);
            int i4 = instance.get(11);
            int i5 = instance.get(12);
            int i6 = instance.get(13);
            GpsInfo gpsInfo = new GpsInfo();
            gpsInfo.encrypted = (byte) i;
            gpsInfo.accuracy = location.getAccuracy();
            gpsInfo.alt = location.getAltitude();
            gpsInfo.angle = location.getBearing();
            gpsInfo.lat = (int) (location.getLatitude() * 1000000.0d);
            gpsInfo.lon = (int) (location.getLongitude() * 1000000.0d);
            gpsInfo.speed = location.getSpeed() * 3.6d;
            gpsInfo.hour = i4;
            gpsInfo.minute = i5;
            gpsInfo.second = i6;
            gpsInfo.year = i2;
            gpsInfo.month = instance.get(2) + 1;
            gpsInfo.day = i3;
            gpsInfo.ticktime = System.currentTimeMillis();
            gpsInfo.sourtype = 0;
            gpsInfo.status = 'A';
            gpsInfo.ew = 'E';
            gpsInfo.ns = 'N';
            gpsInfo.hdop = 0.9d;
            gpsInfo.pdop = 0.9d;
            gpsInfo.vdop = 0.9d;
            gpsInfo.satnum = 9;
            gpsInfo.mode = 'N';
            LocManager.setGpsInfo(gpsInfo);
        } catch (Exception e) {
            e.printStackTrace();
            gr.b(e, "A8C", "sgi");
        }
    }

    @Override // com.autonavi.rtbt.IAE8
    public void setReCalculateRouteForYaw(boolean z) {
    }

    @Override // com.autonavi.rtbt.IAE8
    public void setReCalculateRouteForTrafficJam(boolean z) {
    }

    @Override // com.autonavi.rtbt.IAE8
    public void setTrafficStatusUpdateEnabled(boolean z) {
        if (this.b != null) {
            this.b.control(GuideControl.GC_TMC_CONGESTION, z ? "1" : "0");
        }
    }

    @Override // com.autonavi.rtbt.IAE8
    public void setTrafficInfoUpdateEnabled(boolean z) {
        if (this.b != null) {
            this.b.control(GuideControl.GC_TMC, z ? "1" : "0");
        }
    }

    @Override // com.autonavi.rtbt.IAE8
    public void setCameraInfoUpdateEnabled(boolean z) {
        if (this.b != null) {
            this.b.control(GuideControl.GC_CAMERA_PLAY, z ? "1" : "0");
        }
    }

    @Override // com.autonavi.rtbt.IAE8
    public void setDetectedMode(int i) {
    }

    @Override // com.autonavi.rtbt.IAE8
    public boolean calculateDriveRoute(List<NaviLatLng> list, List<NaviLatLng> list2, int i) {
        ArrayList arrayList = new ArrayList();
        if (em.f() == null) {
            return false;
        }
        arrayList.add(em.f());
        return calculateDriveRoute(arrayList, list, list2, i);
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x0051  */
    /* JADX WARN: Removed duplicated region for block: B:23:? A[RETURN, SYNTHETIC] */
    @Override // com.autonavi.rtbt.IAE8
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean calculateDriveRoute(java.util.List<com.amap.api.navi.model.NaviLatLng> r11, java.util.List<com.amap.api.navi.model.NaviLatLng> r12, java.util.List<com.amap.api.navi.model.NaviLatLng> r13, int r14) {
        /*
            r10 = this;
            r9 = 1
            r8 = 0
            r0 = 9
            if (r14 > r0) goto L_0x0009
            r0 = 5
            if (r14 != r0) goto L_0x0064
        L_0x0009:
            boolean r0 = r10.i     // Catch: Exception -> 0x0057
            if (r0 != 0) goto L_0x0053
            r0 = 0
            r10.h = r0     // Catch: Exception -> 0x0057
        L_0x0010:
            r0 = 1
            r10.i = r0     // Catch: Exception -> 0x0057
            com.amap.api.col.em.a(r14)     // Catch: Exception -> 0x0057
            com.autonavi.ae.route.model.RoutePoi[] r0 = com.amap.api.col.en.a(r11)     // Catch: Exception -> 0x0057
            com.amap.api.col.em.a(r0)     // Catch: Exception -> 0x0057
            com.autonavi.ae.route.model.RoutePoi[] r0 = com.amap.api.col.en.a(r12)     // Catch: Exception -> 0x0057
            com.amap.api.col.em.c(r0)     // Catch: Exception -> 0x0057
            com.autonavi.ae.route.model.RoutePoi[] r0 = com.amap.api.col.en.a(r13)     // Catch: Exception -> 0x0057
            com.amap.api.col.em.b(r0)     // Catch: Exception -> 0x0057
            boolean r0 = com.amap.api.col.em.a()     // Catch: Exception -> 0x0057
            if (r0 == 0) goto L_0x0062
            java.lang.String r0 = "AE8"
            java.lang.String r1 = "calculateDriveRoute(from,to,waypoint,strategy)"
            com.amap.api.col.fr.a(r0, r1)     // Catch: Exception -> 0x0057
            com.autonavi.ae.route.RouteService r0 = r10.c     // Catch: Exception -> 0x0057
            r1 = 4
            r2 = 0
            com.autonavi.ae.route.model.RoutePoi[] r3 = com.amap.api.col.em.b()     // Catch: Exception -> 0x0057
            com.autonavi.ae.route.model.RoutePoi[] r4 = com.amap.api.col.em.c()     // Catch: Exception -> 0x0057
            com.autonavi.ae.route.model.RoutePoi[] r5 = com.amap.api.col.em.d()     // Catch: Exception -> 0x0057
            r6 = 0
            r7 = -1082130432(0xffffffffbf800000, float:-1.0)
            int r0 = r0.requestRoute(r1, r2, r3, r4, r5, r6, r7)     // Catch: Exception -> 0x0057
        L_0x004f:
            if (r0 != r9) goto L_0x0052
            r8 = r9
        L_0x0052:
            return r8
        L_0x0053:
            r0 = 1
            r10.h = r0     // Catch: Exception -> 0x0057
            goto L_0x0010
        L_0x0057:
            r0 = move-exception
            r0.printStackTrace()
            java.lang.String r1 = "A8C"
            java.lang.String r2 = "cdr"
            com.amap.api.col.gr.b(r0, r1, r2)
        L_0x0062:
            r0 = r8
            goto L_0x004f
        L_0x0064:
            r0 = 0
            r10.h = r0     // Catch: Exception -> 0x0057
            goto L_0x0010
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amap.api.col.el.calculateDriveRoute(java.util.List, java.util.List, java.util.List, int):boolean");
    }
}
