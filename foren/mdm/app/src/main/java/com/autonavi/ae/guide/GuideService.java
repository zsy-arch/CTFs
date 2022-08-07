package com.autonavi.ae.guide;

import android.content.Context;
import com.autonavi.ae.guide.model.EventTip;
import com.autonavi.ae.guide.model.GuideConfig;
import com.autonavi.ae.guide.model.GuideGPSInfo;
import com.autonavi.ae.guide.model.ManeuverIconConfig;
import com.autonavi.ae.guide.model.MotionVector3D;
import com.autonavi.ae.guide.model.NaviStaticInfo;
import com.autonavi.ae.guide.observer.GElecEyeObserver;
import com.autonavi.ae.guide.observer.GNaviObserver;
import com.autonavi.ae.guide.observer.GSoundPlayObserver;
import com.autonavi.ae.guide.observer.GStatusObserver;
import com.autonavi.ae.guide.observer.GUpdateCityDataObserver;
import com.autonavi.ae.route.model.RoutePoi;
import com.autonavi.ae.route.observer.HttpInterface;
import java.util.HashSet;
import java.util.Iterator;

/* loaded from: classes.dex */
public class GuideService {
    public static final int DATA_ADD_OP = 2;
    public static final int DATA_DELETE_OP = 5;
    public static final int DATA_FINISH_OP = 4;
    public static final int DATA_UPDAE_OP = 3;
    private static final int FILE_TYPE_CHANGEPLAY = 3;
    private static final int FILE_TYPE_CITY = 1;
    private static final int FILE_TYPE_GUIDESAFE = 4;
    private static final int FILE_TYPE_NAVISOUND = 2;
    public static final int NET_ERROR_CANCEL = 2;
    public static final int NET_ERROR_NO_NETWORK_CONNECTION = 3;
    public static final int NET_ERROR_OTHER = -1;
    public static final int NET_ERROR_TIMEOUT = 1;
    public static final int OFFLINE_DATAMANAGER = 1;
    public static final int VERSION_GET_OP = 1;
    private Context mContext;
    private GElecEyeObserver mElecEyeObserver;
    private HttpInterface mHttpProcess;
    private GNaviObserver mNaviObserver;
    private long mPtr;
    private GSoundPlayObserver mSoundPlayObserver;
    private HashSet<GStatusObserver> mStatusListeners;
    private GUpdateCityDataObserver mUpdateCityDataObserver;

    public static native String getEngineVersion();

    private final native void init(GuideConfig guideConfig);

    public native int IOParam(int i, int i2, int i3);

    public native int SetMotionData(MotionVector3D motionVector3D, MotionVector3D motionVector3D2, MotionVector3D motionVector3D3, double d);

    public native int control(String str, String str2);

    public final native void destroy();

    public native String getDriveReport();

    public native EventTip[] getEventTips();

    public native GuideGPSInfo[] getRecentGPS(int i, int i2, int i3);

    public native NaviStaticInfo getStaticInfo();

    public native int pauseNavi();

    public native int playNaviManual();

    public native int playTrafficRadioManual(int i);

    public native void processHttpData(int i, int i2, byte[] bArr);

    public native void processHttpError(int i, int i2);

    public native void renderManeuverIcon(ManeuverIconConfig maneuverIconConfig);

    public native int reroute(int i, int i2, int i3, RoutePoi[] routePoiArr, RoutePoi[] routePoiArr2, RoutePoi[] routePoiArr3, double d);

    public native int resumeNavi();

    public native int setNaviPath(long j, int i, RoutePoi[] routePoiArr, RoutePoi[] routePoiArr2, RoutePoi[] routePoiArr3);

    public native void setPressure(double d);

    public native int startNavi(int i);

    public native int stopNavi();

    public GuideService(GuideConfig guideConfig, Context context) {
        this.mContext = context;
        init(guideConfig);
    }

    public void registerHttpProcesser(HttpInterface httpInterface) {
        this.mHttpProcess = httpInterface;
    }

    public void setNaviObserver(GNaviObserver gNaviObserver) {
        this.mNaviObserver = gNaviObserver;
    }

    public void setSoundPlayObserver(GSoundPlayObserver gSoundPlayObserver) {
        this.mSoundPlayObserver = gSoundPlayObserver;
    }

    public void setElecEyeObserver(GElecEyeObserver gElecEyeObserver) {
        this.mElecEyeObserver = gElecEyeObserver;
    }

    public void setUpdateCityDataObserver(GUpdateCityDataObserver gUpdateCityDataObserver) {
        this.mUpdateCityDataObserver = gUpdateCityDataObserver;
    }

    public void addStatusObserver(GStatusObserver gStatusObserver) {
        if (this.mStatusListeners == null) {
            this.mStatusListeners = new HashSet<>();
        }
        if (!this.mStatusListeners.contains(gStatusObserver)) {
            this.mStatusListeners.add(gStatusObserver);
        }
    }

    public void removeStatusObserver(GStatusObserver gStatusObserver) {
        if (this.mStatusListeners != null && this.mStatusListeners.contains(gStatusObserver)) {
            this.mStatusListeners.remove(gStatusObserver);
        }
    }

    private void notifyStatusChanged(int i) {
        if (this.mStatusListeners != null) {
            Iterator<GStatusObserver> it = this.mStatusListeners.iterator();
            while (it.hasNext()) {
                it.next().onTbtStatusChanged(i, 0);
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v1, types: [boolean] */
    /* JADX WARN: Type inference failed for: r2v12 */
    /* JADX WARN: Type inference failed for: r2v2, types: [java.io.ByteArrayOutputStream] */
    /* JADX WARN: Type inference failed for: r2v4 */
    /* JADX WARN: Unknown variable types count: 1 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public byte[] readAssetsFile(int r7, int r8) {
        /*
            Method dump skipped, instructions count: 190
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.autonavi.ae.guide.GuideService.readAssetsFile(int, int):byte[]");
    }
}
