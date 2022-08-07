package com.amap.api.navi;

import android.content.Context;
import android.location.Location;
import android.text.TextUtils;
import com.amap.api.col.er;
import com.amap.api.col.fn;
import com.amap.api.col.fo;
import com.amap.api.col.gb;
import com.amap.api.col.gr;
import com.amap.api.col.hm;
import com.amap.api.navi.model.AMapCarInfo;
import com.amap.api.navi.model.AMapNaviGuide;
import com.amap.api.navi.model.AMapNaviPath;
import com.amap.api.navi.model.AMapTrafficStatus;
import com.amap.api.navi.model.NaviInfo;
import com.amap.api.navi.model.NaviLatLng;
import com.tencent.open.utils.SystemUtils;
import java.util.HashMap;
import java.util.List;

/* loaded from: classes.dex */
public class AMapNavi implements INavi {
    private static AMapNavi singletonAMapNavi;
    private INavi mINavi;
    public static int GPSNaviMode = 1;
    public static int EmulatorNaviMode = 2;

    static {
        System.loadLibrary("GNaviUtils");
        System.loadLibrary("GNaviData");
        System.loadLibrary("GNaviPos");
        System.loadLibrary("GNaviRoute");
        System.loadLibrary("GNaviGuide");
        System.loadLibrary("GNaviSearch");
        System.loadLibrary("RoadLineRebuildAPI");
    }

    protected AMapNavi() {
    }

    private AMapNavi(Context context) {
        try {
            fo.a(context.getApplicationContext());
            this.mINavi = (INavi) hm.a(context, fn.a(), "com.amap.api.navi.wrapper.AMapNaviWrapper", er.class, new Class[]{Context.class}, new Object[]{context});
        } catch (Throwable th) {
            th.printStackTrace();
            this.mINavi = new er(context);
        }
    }

    public static synchronized AMapNavi getInstance(Context context) {
        AMapNavi aMapNavi;
        synchronized (AMapNavi.class) {
            if (singletonAMapNavi == null) {
                singletonAMapNavi = new AMapNavi(context);
            }
            aMapNavi = singletonAMapNavi;
        }
        return aMapNavi;
    }

    public static String getVersion() {
        return SystemUtils.QQ_VERSION_NAME_5_1_0;
    }

    public static void setApiKey(Context context, String str) {
        if (!TextUtils.isEmpty(str)) {
            gb.a(str);
        }
    }

    public static void setTtsPlaying(boolean z) {
        fn.a = z;
    }

    @Override // com.amap.api.navi.INavi
    public int strategyConvert(boolean z, boolean z2, boolean z3, boolean z4, boolean z5) {
        if (this.mINavi != null) {
            return this.mINavi.strategyConvert(z, z2, z3, z4, z5);
        }
        return 0;
    }

    @Override // com.amap.api.navi.INavi
    public synchronized void destroy() {
        if (this.mINavi != null) {
            this.mINavi.destroy();
            this.mINavi = null;
        }
        singletonAMapNavi = null;
    }

    @Override // com.amap.api.navi.INavi
    public boolean startNavi(int i) {
        try {
            if (this.mINavi != null) {
                return this.mINavi.startNavi(i);
            }
        } catch (Throwable th) {
            fn.a(th);
            gr.b(th, "AMapNavi", "startNavi(naviType)");
        }
        return false;
    }

    @Override // com.amap.api.navi.INavi
    public void pauseNavi() {
        try {
            if (this.mINavi != null) {
                this.mINavi.pauseNavi();
            }
        } catch (Throwable th) {
            fn.a(th);
            gr.b(th, "AMapNavi", "pauseNavi()");
        }
    }

    @Override // com.amap.api.navi.INavi
    public void stopNavi() {
        try {
            if (this.mINavi != null) {
                this.mINavi.stopNavi();
            }
        } catch (Throwable th) {
            fn.a(th);
            gr.b(th, "AMapNavi", "stopNavi();");
        }
    }

    @Override // com.amap.api.navi.INavi
    public void resumeNavi() {
        try {
            if (this.mINavi != null) {
                this.mINavi.resumeNavi();
            }
        } catch (Throwable th) {
            fn.a(th);
            gr.b(th, "AMapNavi", "resumeNavi()");
        }
    }

    @Override // com.amap.api.navi.INavi
    public boolean readNaviInfo() {
        try {
            if (this.mINavi != null) {
                return this.mINavi.readNaviInfo();
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
            if (this.mINavi != null) {
                return this.mINavi.readTrafficInfo(i);
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
            if (this.mINavi != null) {
                return this.mINavi.calculateDriveRoute(list, list2, list3, i);
            }
        } catch (Throwable th) {
            fn.a(th);
            gr.b(th, "AMapNavi", "calculateDriveRoute(List<NaviLatLng> from, List<NaviLatLng> to,\n                                       List<NaviLatLng> wayPoints, int strategy)");
        }
        return false;
    }

    @Override // com.amap.api.navi.INavi
    public void setCarNumber(String str, String str2) {
        try {
            if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2) && this.mINavi != null && str2.length() < 7) {
                this.mINavi.setCarNumber(str, str2);
            }
        } catch (Throwable th) {
            fn.a(th);
            gr.b(th, "AMapNavi", "setCarNumber(String province,String number)");
        }
    }

    @Override // com.amap.api.navi.INavi
    public void setCarInfo(AMapCarInfo aMapCarInfo) {
        if (this.mINavi != null) {
            this.mINavi.setCarInfo(aMapCarInfo);
        }
    }

    @Override // com.amap.api.navi.INavi
    public boolean calculateDriveRoute(List<NaviLatLng> list, List<NaviLatLng> list2, int i) {
        try {
            if (this.mINavi != null) {
                return this.mINavi.calculateDriveRoute(list, list2, i);
            }
        } catch (Throwable th) {
            fn.a(th);
            gr.b(th, "AMapNavi", "calculateDriveRoute(java.util.List<NaviLatLng> to,\n                                       java.util.List<NaviLatLng> wayPoints, int strategy)");
        }
        return false;
    }

    @Override // com.amap.api.navi.INavi
    public boolean reCalculateRoute(int i) {
        try {
            if (this.mINavi != null) {
                return this.mINavi.reCalculateRoute(i);
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
            if (this.mINavi != null) {
                return this.mINavi.getTrafficStatuses(i, i2);
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
            if (this.mINavi != null) {
                return this.mINavi.getNaviPath();
            }
        } catch (Throwable th) {
            fn.a(th);
            gr.b(th, "AMapNavi", "getNaviPath()");
        }
        return null;
    }

    @Override // com.amap.api.navi.INavi
    public HashMap<Integer, AMapNaviPath> getNaviPaths() {
        try {
            if (this.mINavi != null) {
                return this.mINavi.getNaviPaths();
            }
            return null;
        } catch (Throwable th) {
            fn.a(th);
            gr.b(th, "AMapNavi", "getNaviPaths()");
            return null;
        }
    }

    @Override // com.amap.api.navi.INavi
    public List<AMapNaviGuide> getNaviGuideList() {
        try {
            if (this.mINavi != null) {
                return this.mINavi.getNaviGuideList();
            }
        } catch (Throwable th) {
            fn.a(th);
            gr.b(th, "AMapNavi", "getNaviGuideList()");
        }
        return null;
    }

    @Override // com.amap.api.navi.INavi
    public NaviSetting getNaviSetting() {
        if (this.mINavi != null) {
            return this.mINavi.getNaviSetting();
        }
        return null;
    }

    @Override // com.amap.api.navi.INavi
    public void setEmulatorNaviSpeed(int i) {
        try {
            if (this.mINavi != null) {
                this.mINavi.setEmulatorNaviSpeed(i);
            }
        } catch (Throwable th) {
            fn.a(th);
            gr.b(th, "AMapNavi", "setEmulatorNaviSpeed(int speed)");
        }
    }

    @Override // com.amap.api.navi.INavi
    public void setTimeForOneWord(int i) {
        try {
            if (this.mINavi != null) {
                this.mINavi.setTimeForOneWord(i);
            }
        } catch (Throwable th) {
            fn.a(th);
            gr.b(th, "AMapNavi", "setTimeForOneWord(int time)");
        }
    }

    public void setAMapNaviListener(AMapNaviListener aMapNaviListener) {
        try {
            if (this.mINavi != null) {
                this.mINavi.addAMapNaviListener(aMapNaviListener);
            }
        } catch (Throwable th) {
            fn.a(th);
            gr.b(th, "AMapNavi", "setAMapNaviListener(AMapNaviListener naviListener) ");
        }
    }

    @Override // com.amap.api.navi.INavi
    public void addAMapNaviListener(AMapNaviListener aMapNaviListener) {
        try {
            if (this.mINavi != null) {
                this.mINavi.addAMapNaviListener(aMapNaviListener);
            }
        } catch (Throwable th) {
            fn.a(th);
            gr.b(th, "AMapNavi", "addAMapNaviListener(AMapNaviListener naviListener)");
        }
    }

    @Override // com.amap.api.navi.INavi
    public void removeAMapNaviListener(AMapNaviListener aMapNaviListener) {
        try {
            if (this.mINavi != null) {
                this.mINavi.removeAMapNaviListener(aMapNaviListener);
            }
        } catch (Throwable th) {
            fn.a(th);
            gr.b(th, "AMapNavi", "removeAMapNaviListener(AMapNaviListener naviListener)");
        }
    }

    @Override // com.amap.api.navi.INavi
    public boolean startGPS() {
        if (this.mINavi != null) {
            return this.mINavi.startGPS();
        }
        return false;
    }

    @Override // com.amap.api.navi.INavi
    public boolean startGPS(long j, int i) {
        if (this.mINavi != null) {
            return this.mINavi.startGPS(j, i);
        }
        return false;
    }

    @Override // com.amap.api.navi.INavi
    public boolean stopGPS() {
        if (this.mINavi != null) {
            return this.mINavi.stopGPS();
        }
        return false;
    }

    @Override // com.amap.api.navi.INavi
    public boolean calculateWalkRoute(NaviLatLng naviLatLng) {
        if (this.mINavi != null) {
            return this.mINavi.calculateWalkRoute(naviLatLng);
        }
        return false;
    }

    @Override // com.amap.api.navi.INavi
    public boolean calculateWalkRoute(NaviLatLng naviLatLng, NaviLatLng naviLatLng2) {
        if (this.mINavi != null) {
            return this.mINavi.calculateWalkRoute(naviLatLng, naviLatLng2);
        }
        return false;
    }

    @Override // com.amap.api.navi.INavi
    public boolean calculateRideRoute(NaviLatLng naviLatLng) {
        if (this.mINavi != null) {
            return this.mINavi.calculateRideRoute(naviLatLng);
        }
        return false;
    }

    @Override // com.amap.api.navi.INavi
    public boolean calculateRideRoute(NaviLatLng naviLatLng, NaviLatLng naviLatLng2) {
        if (this.mINavi != null) {
            return this.mINavi.calculateRideRoute(naviLatLng, naviLatLng2);
        }
        return false;
    }

    @Override // com.amap.api.navi.INavi
    public void setReCalculateRouteForYaw(boolean z) {
    }

    @Override // com.amap.api.navi.INavi
    public void setReCalculateRouteForTrafficJam(boolean z) {
    }

    @Override // com.amap.api.navi.INavi
    public int getEngineType() {
        if (this.mINavi != null) {
            return this.mINavi.getEngineType();
        }
        return 0;
    }

    @Override // com.amap.api.navi.INavi
    public int getNaviType() {
        if (this.mINavi != null) {
            return this.mINavi.getNaviType();
        }
        return 0;
    }

    @Override // com.amap.api.navi.INavi
    public NaviInfo getNaviInfo() {
        if (this.mINavi != null) {
            return this.mINavi.getNaviInfo();
        }
        return null;
    }

    @Override // com.amap.api.navi.INavi
    public void setDetectedMode(int i) {
        if (this.mINavi != null) {
            this.mINavi.setDetectedMode(i);
        }
    }

    @Override // com.amap.api.navi.INavi
    public void startAimlessMode(int i) {
        if (this.mINavi != null) {
            this.mINavi.startAimlessMode(i);
        }
    }

    @Override // com.amap.api.navi.INavi
    public void stopAimlessMode() {
        if (this.mINavi != null) {
            this.mINavi.stopAimlessMode();
        }
    }

    @Override // com.amap.api.navi.INavi
    public void setConnectionTimeout(int i) {
        if (this.mINavi != null) {
            this.mINavi.setConnectionTimeout(i);
        }
    }

    @Override // com.amap.api.navi.INavi
    public void setSoTimeout(int i) {
        if (this.mINavi != null) {
            this.mINavi.setSoTimeout(i);
        }
    }

    @Override // com.amap.api.navi.INavi
    public boolean selectRouteId(int i) {
        if (this.mINavi != null) {
            return this.mINavi.selectRouteId(i);
        }
        return false;
    }

    @Override // com.amap.api.navi.INavi
    public boolean isGpsReady() {
        if (this.mINavi != null) {
            return this.mINavi.isGpsReady();
        }
        return false;
    }

    @Override // com.amap.api.navi.INavi
    public boolean setBroadcastMode(int i) {
        if (this.mINavi != null) {
            return this.mINavi.setBroadcastMode(i);
        }
        return false;
    }

    @Override // com.amap.api.navi.INavi
    public void switchParallelRoad() {
        if (this.mINavi != null) {
            this.mINavi.switchParallelRoad();
        }
    }

    @Override // com.amap.api.navi.INavi
    public boolean getIsUseExtraGPSData() {
        if (this.mINavi != null) {
            return this.mINavi.getIsUseExtraGPSData();
        }
        return false;
    }

    @Override // com.amap.api.navi.INavi
    public void setIsUseExtraGPSData(boolean z) {
        if (this.mINavi != null) {
            this.mINavi.setIsUseExtraGPSData(z);
        }
    }

    @Override // com.amap.api.navi.INavi
    public void setExtraGPSData(int i, Location location) {
        if (this.mINavi != null) {
            this.mINavi.setExtraGPSData(i, location);
        }
    }

    @Override // com.amap.api.navi.INavi
    public void setExtraGPSData(Location location) {
        if (this.mINavi != null) {
            this.mINavi.setExtraGPSData(location);
        }
    }
}
