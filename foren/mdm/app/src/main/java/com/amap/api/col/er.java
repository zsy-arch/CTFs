package com.amap.api.col;

import android.content.Context;
import android.location.Location;
import com.amap.api.col.gb;
import com.amap.api.navi.AMapNaviListener;
import com.amap.api.navi.IGpsCallback;
import com.amap.api.navi.INavi;
import com.amap.api.navi.NaviSetting;
import com.amap.api.navi.model.AMapCarInfo;
import com.amap.api.navi.model.AMapNaviGuide;
import com.amap.api.navi.model.AMapNaviPath;
import com.amap.api.navi.model.AMapTrafficStatus;
import com.amap.api.navi.model.NaviInfo;
import com.amap.api.navi.model.NaviLatLng;
import com.autonavi.rtbt.IAE8;
import java.util.HashMap;
import java.util.List;

/* compiled from: AMapNaviCore.java */
/* loaded from: classes.dex */
public class er implements IGpsCallback, INavi {
    private fa c;
    private ez d;
    private IAE8 e;
    private NaviSetting f;
    private ew i;
    private Context j;
    private int a = -1;
    private int b = -1;
    private boolean g = false;
    private boolean h = false;
    private int k = 40;

    public er(Context context) {
        this.j = context.getApplicationContext();
        a();
        fo.a(context.getApplicationContext());
        this.i = new ew(this.j);
        this.i.a(this);
        this.e = new el(this.j);
        this.e.a();
        this.f = new NaviSetting(this.j, this.e);
        this.c = new fg(this.j);
        this.c.a();
        this.d = new fe(this.j);
        this.d.a();
    }

    @Override // com.amap.api.navi.INavi
    public int strategyConvert(boolean z, boolean z2, boolean z3, boolean z4, boolean z5) {
        if (this.e != null) {
            return this.e.strategyConvert(z, z2, z3, z4, z5);
        }
        return 0;
    }

    @Override // com.amap.api.navi.INavi
    public synchronized void destroy() {
        if (this.i != null) {
            this.i.b();
            this.i.c();
            this.i = null;
        }
        this.f.destroy();
        if (this.c != null) {
            fr.b("AMapNaviCore-->IWalk destroy()");
            this.c.b();
            this.c = null;
        }
        if (this.d != null) {
            fr.b("AMapNaviCore-->IRide destroy()");
            this.d.b();
            this.d = null;
        }
        if (this.e != null) {
            fr.b("AMapNaviCore-->IAe8 destroy()");
            this.e.b();
            this.e = null;
        }
    }

    @Override // com.amap.api.navi.INavi
    public boolean startNavi(int i) {
        this.b = i;
        try {
            switch (this.a) {
                case 0:
                    if (i != 2) {
                        this.e.a(0);
                        fr.a("--------------------------开始驾车GPS导航------------------------------------------");
                        if (!this.g) {
                            startGPS();
                            break;
                        }
                    } else {
                        fr.a("-------------------------开始驾车模拟导航-------------------------------------------");
                        this.e.b(this.k);
                        this.e.a(1);
                        break;
                    }
                    break;
                case 1:
                    if (i != 2) {
                        fr.a("----------------------------开始步行GPS导航---------------------------------------");
                        this.c.a(1);
                        if (!this.g) {
                            startGPS();
                            break;
                        }
                    } else {
                        fr.a("----------------------------开始步行模拟导航----------------------------------------");
                        this.c.b(this.k);
                        this.c.a(2);
                        break;
                    }
                    break;
                case 2:
                    if (i != 2) {
                        fr.a("---------------------------开始骑行GPS模拟导航----------------------------------------");
                        this.d.a(1);
                        if (!this.g) {
                            startGPS();
                            break;
                        }
                    } else {
                        fr.a("---------------------------开始驾车模拟导航-----------------------------------------");
                        this.d.b(this.k);
                        this.d.a(2);
                        break;
                    }
                    break;
            }
        } catch (Throwable th) {
            fn.a(th);
            gr.b(th, "AMapNaviCore", "startNavi(int naviType)");
        }
        return true;
    }

    @Override // com.amap.api.navi.INavi
    public void pauseNavi() {
        try {
            switch (this.a) {
                case 0:
                    this.e.h();
                    break;
                case 1:
                    this.c.h();
                    break;
                case 2:
                    this.d.h();
                    break;
            }
        } catch (Throwable th) {
            fn.a(th);
            gr.b(th, "AMapNavi", "pauseNavi()");
        }
    }

    @Override // com.amap.api.navi.INavi
    public void stopNavi() {
        try {
            switch (this.a) {
                case 0:
                    this.e.i();
                    break;
                case 1:
                    this.c.i();
                    break;
                case 2:
                    this.d.i();
                    break;
            }
            stopGPS();
        } catch (Throwable th) {
            fn.a(th);
            gr.b(th, "AMapNavi", "stopNavi()");
        }
    }

    @Override // com.amap.api.navi.INavi
    public void resumeNavi() {
        try {
            switch (this.a) {
                case 0:
                    this.e.j();
                    break;
                case 1:
                    this.c.j();
                    break;
                case 2:
                    this.d.j();
                    break;
            }
        } catch (Throwable th) {
            fn.a(th);
            gr.b(th, "AMapNavi", "resumeNavi()");
        }
    }

    @Override // com.amap.api.navi.INavi
    public boolean readNaviInfo() {
        try {
            if (this.e != null) {
                return this.e.readNaviInfo();
            }
        } catch (Throwable th) {
            fn.a(th);
            gr.b(th, "AMapNavi", "readNaviInfo() ");
        }
        return false;
    }

    @Override // com.amap.api.navi.INavi
    public boolean readTrafficInfo(int i) {
        try {
            if (this.e != null) {
                return this.e.readTrafficInfo(i);
            }
        } catch (Throwable th) {
            fn.a(th);
            gr.b(th, "AMapNavi", "readTrafficInfo(int frontDistance)");
        }
        return false;
    }

    @Override // com.amap.api.navi.INavi
    public boolean calculateDriveRoute(List<NaviLatLng> list, List<NaviLatLng> list2, List<NaviLatLng> list3, int i) {
        try {
            if (this.e == null) {
                return false;
            }
            this.a = 0;
            return this.e.calculateDriveRoute(list, list2, list3, i);
        } catch (Throwable th) {
            fn.a(th);
            gr.b(th, "AMapNavi", "calculateDriveRoute(List<NaviLatLng> from, List<NaviLatLng> to,\n                                       List<NaviLatLng> wayPoints, int strategy)");
            return false;
        }
    }

    @Override // com.amap.api.navi.INavi
    public void setCarNumber(String str, String str2) {
        try {
            if (this.e != null) {
                this.e.setCarNumber(str, str2);
            }
        } catch (Throwable th) {
            fn.a(th);
            gr.b(th, "AMapNavi", "setCarNumber(String province, String number)");
        }
    }

    @Override // com.amap.api.navi.INavi
    public void setCarInfo(AMapCarInfo aMapCarInfo) {
        if (this.e != null) {
            this.e.setCarInfo(aMapCarInfo);
        }
    }

    @Override // com.amap.api.navi.INavi
    public boolean calculateDriveRoute(List<NaviLatLng> list, List<NaviLatLng> list2, int i) {
        try {
            if (this.e == null) {
                return false;
            }
            this.a = 0;
            Boolean valueOf = Boolean.valueOf(this.e.calculateDriveRoute(list, list2, i));
            fr.b(valueOf.toString());
            return valueOf.booleanValue();
        } catch (Throwable th) {
            fn.a(th);
            gr.b(th, "AMapNavi", "calculateDriveRoute(java.util.List<NaviLatLng> to,\n                                       java.util.List<NaviLatLng> wayPoints, int strategy)");
            return false;
        }
    }

    @Override // com.amap.api.navi.INavi
    public boolean reCalculateRoute(int i) {
        try {
            if (this.e != null) {
                em.a(i);
                return this.e.reCalculateRoute(3);
            }
        } catch (Throwable th) {
            fn.a(th);
            gr.b(th, "AMapNavi", "reCalculateRoute(int strategy)");
        }
        return false;
    }

    @Override // com.amap.api.navi.INavi
    public List<AMapTrafficStatus> getTrafficStatuses(int i, int i2) {
        try {
            if (this.e != null) {
                return this.e.getTrafficStatuses(i, i2);
            }
        } catch (Throwable th) {
            fn.a(th);
            gr.b(th, "AMapNavi", "getTrafficStatuses(int startPos, int distance) ");
        }
        return null;
    }

    @Override // com.amap.api.navi.INavi
    public AMapNaviPath getNaviPath() {
        try {
        } catch (Throwable th) {
            fn.a(th);
            gr.b(th, "AMapNavi", "getNaviPath()");
        }
        switch (this.a) {
            case 0:
                return this.e.k();
            case 1:
                return this.c.k();
            case 2:
                return this.d.k();
            default:
                return null;
        }
    }

    @Override // com.amap.api.navi.INavi
    public HashMap<Integer, AMapNaviPath> getNaviPaths() {
        try {
            if (this.a != 0 || this.e == null) {
                return null;
            }
            return this.e.getMultipleNaviPathsCalculated();
        } catch (Throwable th) {
            fn.a(th);
            gr.b(th, "AMapNavi", "getNaviPaths()");
            return null;
        }
    }

    @Override // com.amap.api.navi.INavi
    public List<AMapNaviGuide> getNaviGuideList() {
        try {
        } catch (Throwable th) {
            fn.a(th);
            gr.b(th, "AMapNavi", "getNaviGuideList()");
        }
        switch (this.a) {
            case 0:
                return this.e.l();
            case 1:
                return this.c.l();
            case 2:
                return this.d.l();
            default:
                return null;
        }
    }

    @Override // com.amap.api.navi.INavi
    public NaviSetting getNaviSetting() {
        return this.f;
    }

    @Override // com.amap.api.navi.INavi
    public void setEmulatorNaviSpeed(int i) {
        this.k = i;
        if (this.e != null) {
            this.e.b(i);
        }
        if (this.c != null) {
            this.c.b(i);
        }
        if (this.d != null) {
            this.d.b(i);
        }
    }

    @Override // com.amap.api.navi.INavi
    public void setTimeForOneWord(int i) {
        try {
            switch (this.a) {
                case 0:
                    this.e.d(i);
                    break;
                case 1:
                    this.c.d(i);
                    break;
                case 2:
                    this.d.d(i);
                    break;
            }
        } catch (Throwable th) {
            fn.a(th);
            gr.b(th, "AMapNavi", "setTimeForOneWord(int time)");
        }
    }

    @Override // com.amap.api.navi.INavi
    public void addAMapNaviListener(AMapNaviListener aMapNaviListener) {
        try {
            if (this.e != null) {
                this.e.a(aMapNaviListener);
            }
            if (this.c != null) {
                this.c.a(aMapNaviListener);
            }
            if (this.d != null) {
                this.d.a(aMapNaviListener);
            }
        } catch (Throwable th) {
            fn.a(th);
            gr.b(th, "AMapNavi", "addAMapNaviListener(AMapNaviListener naviListener)");
        }
    }

    @Override // com.amap.api.navi.INavi
    public void removeAMapNaviListener(AMapNaviListener aMapNaviListener) {
        try {
            if (this.e != null) {
                this.e.b(aMapNaviListener);
            }
            if (this.c != null) {
                this.c.b(aMapNaviListener);
            }
            if (this.d != null) {
                this.d.b(aMapNaviListener);
            }
        } catch (Throwable th) {
            fn.a(th);
            gr.b(th, "AMapNavi", "removeAMapNaviListener(AMapNaviListener naviListener)");
        }
    }

    @Override // com.amap.api.navi.INavi
    public boolean startGPS() {
        try {
            if (this.i != null) {
                this.i.a();
            }
            return true;
        } catch (Throwable th) {
            fn.a(th);
            gr.b(th, "AMapNavi", "startGPS()");
            return false;
        }
    }

    @Override // com.amap.api.navi.INavi
    public boolean startGPS(long j, int i) {
        try {
            if (this.i != null) {
                this.i.a(j);
            }
            return true;
        } catch (Throwable th) {
            fn.a(th);
            gr.b(th, "AMapNavi", "startGPS(long time, int dis)");
            return false;
        }
    }

    @Override // com.amap.api.navi.INavi
    public boolean stopGPS() {
        try {
            if (this.i == null) {
                return false;
            }
            this.i.b();
            return false;
        } catch (Throwable th) {
            fn.a(th);
            gr.b(th, "AMapNavi", "stopGPS() ");
            return false;
        }
    }

    @Override // com.amap.api.navi.INavi
    public boolean calculateWalkRoute(NaviLatLng naviLatLng) {
        try {
            if (this.c != null) {
                this.a = 1;
                return this.c.a(naviLatLng);
            }
        } catch (Throwable th) {
            fn.a(th);
            gr.b(th, "AMapNavi", "calculateWalkRoute(NaviLatLng to)");
        }
        return false;
    }

    @Override // com.amap.api.navi.INavi
    public boolean calculateWalkRoute(NaviLatLng naviLatLng, NaviLatLng naviLatLng2) {
        try {
            if (this.c != null) {
                this.a = 1;
                return this.c.a(naviLatLng, naviLatLng2);
            }
        } catch (Throwable th) {
            fn.a(th);
            gr.b(th, "AMapNavi", "calculateWalkRoute(NaviLatLng from, NaviLatLng to) ");
        }
        return false;
    }

    @Override // com.amap.api.navi.INavi
    public boolean calculateRideRoute(NaviLatLng naviLatLng) {
        try {
            if (this.d != null) {
                this.a = 2;
                return this.d.a(naviLatLng);
            }
        } catch (Throwable th) {
            fn.a(th);
            gr.b(th, "AMapNavi", "calculateRideRoute(NaviLatLng to)");
        }
        return false;
    }

    @Override // com.amap.api.navi.INavi
    public boolean calculateRideRoute(NaviLatLng naviLatLng, NaviLatLng naviLatLng2) {
        try {
            if (this.d != null) {
                this.a = 2;
                return this.d.a(naviLatLng, naviLatLng2);
            }
        } catch (Throwable th) {
            fn.a(th);
            gr.b(th, "AMapNavi", "calculateRideRoute(NaviLatLng from, NaviLatLng to) ");
        }
        return false;
    }

    @Override // com.amap.api.navi.INavi
    public void setReCalculateRouteForYaw(boolean z) {
        try {
            if (this.e != null) {
                this.e.setReCalculateRouteForYaw(z);
            }
        } catch (Throwable th) {
            fn.a(th);
            gr.b(th, "AMapNavi", "setReCalculateRouteForYaw(boolean isReroute)");
        }
    }

    @Override // com.amap.api.navi.INavi
    public void setReCalculateRouteForTrafficJam(boolean z) {
        try {
            if (this.e != null) {
                this.e.setReCalculateRouteForTrafficJam(z);
            }
        } catch (Throwable th) {
            fn.a(th);
            gr.b(th, "AMapNavi", "setReCalculateRouteForTrafficJam(boolean isReroute)");
        }
    }

    @Override // com.amap.api.navi.INavi
    public int getEngineType() {
        return this.a;
    }

    @Override // com.amap.api.navi.INavi
    public int getNaviType() {
        return this.b;
    }

    @Override // com.amap.api.navi.INavi
    public NaviInfo getNaviInfo() {
        try {
        } catch (Throwable th) {
            fn.a(th);
            gr.b(th, "AMapNaviCore", "getNaviInfo()");
        }
        if (this.e != null) {
            return this.e.c();
        }
        if (this.c != null) {
            return this.c.c();
        }
        if (this.d != null) {
            return this.d.c();
        }
        return null;
    }

    @Override // com.amap.api.navi.INavi
    public void setDetectedMode(int i) {
        if (this.e != null) {
            this.e.setDetectedMode(i);
        }
    }

    @Override // com.amap.api.navi.INavi
    public void startAimlessMode(int i) {
        if (this.e != null) {
            this.a = 0;
            this.e.startAimlessMode(i);
            startGPS();
        }
    }

    @Override // com.amap.api.navi.INavi
    public void stopAimlessMode() {
        if (this.e != null) {
            this.e.stopAimlessMode();
            stopGPS();
        }
    }

    @Override // com.amap.api.navi.INavi
    public void setConnectionTimeout(int i) {
        if (i < 3000) {
            i = 3000;
        }
        fh.b(i);
    }

    @Override // com.amap.api.navi.INavi
    public void setSoTimeout(int i) {
        if (i < 3000) {
            i = 3000;
        }
        fh.a(i);
    }

    @Override // com.amap.api.navi.INavi
    public boolean selectRouteId(int i) {
        try {
            if (this.a != 0 || this.e == null) {
                return false;
            }
            return this.e.c(i) != -1;
        } catch (Throwable th) {
            fn.a(th);
            gr.b(th, "AMapNavi", "selectRouteId(int id)");
            return false;
        }
    }

    @Override // com.amap.api.navi.INavi
    public boolean isGpsReady() {
        return this.h;
    }

    @Override // com.amap.api.navi.INavi
    public boolean setBroadcastMode(int i) {
        return this.e != null && this.e.setBroadcastMode(i);
    }

    @Override // com.amap.api.navi.INavi
    public void switchParallelRoad() {
        if (this.e != null) {
            this.e.switchParallelRoad();
        }
    }

    @Override // com.amap.api.navi.INavi
    public boolean getIsUseExtraGPSData() {
        return this.g;
    }

    @Override // com.amap.api.navi.INavi
    public void setIsUseExtraGPSData(boolean z) {
        this.g = z;
    }

    @Override // com.amap.api.navi.INavi
    public void setExtraGPSData(int i, Location location) {
        if (this.g && location != null) {
            try {
                a(i, location);
            } catch (Throwable th) {
                fn.a(th);
                gr.b(th, "AMapNavi", "setExtraGPSData(int type,Location location)");
            }
        }
    }

    @Override // com.amap.api.navi.INavi
    public void setExtraGPSData(Location location) {
        setExtraGPSData(1, location);
    }

    @Override // com.amap.api.navi.IGpsCallback
    public void onLocationChanged(int i, Location location) {
        fr.a("AmapNaviCore-->onLocationChanged(int type, Location location),mIsUseExtraGPSData=" + this.g + ",mEngineType=" + this.a);
        if (!this.g) {
            this.h = true;
            a(i, location);
        }
    }

    private void a(int i, Location location) {
        try {
            switch (this.a) {
                case 0:
                    int i2 = 0;
                    if (i == 2) {
                        i2 = 1;
                    }
                    this.e.a(i2, location.getLongitude(), location.getLatitude());
                    this.e.a(i2, location);
                    break;
                case 1:
                    this.c.a(i, location.getLongitude(), location.getLatitude());
                    this.c.a(i, location);
                    break;
                case 2:
                    this.d.a(i, location.getLongitude(), location.getLatitude());
                    this.d.a(i, location);
                    break;
            }
            em.a(new NaviLatLng(location.getLatitude(), location.getLongitude()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // com.amap.api.navi.IGpsCallback
    public void onGpsStarted() {
        if (this.c != null) {
            this.c.d();
        }
    }

    private void a() {
        try {
            Thread thread = new Thread() { // from class: com.amap.api.col.er.1
                @Override // java.lang.Thread, java.lang.Runnable
                public void run() {
                    try {
                        if (!fn.b) {
                            gj a = fn.a();
                            gb.a a2 = gb.a(er.this.j, a, "11K;001", null);
                            if (a2 != null) {
                                gb.a.C0014a aVar = a2.f21u;
                                if (aVar != null) {
                                    a.a(aVar.a);
                                }
                                gb.a.d dVar = a2.v;
                                if (dVar != null) {
                                    hm.a(er.this.j, new hl(dVar.a, dVar.b, dVar.c), a);
                                }
                            }
                            gr.a(er.this.j, a);
                            fn.b = true;
                        }
                    } catch (fz e) {
                        e.printStackTrace();
                        gr.b(e, "WTBTControl", "initAuth().run()");
                    }
                }
            };
            thread.setName("AuthThread");
            thread.start();
        } catch (Throwable th) {
            fn.a(th);
            gr.b(th, "WTBTControl", "initAuth()");
        }
    }
}
