package com.amap.api.col;

import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.autonavi.amap.mapcore.Inner_3dMap_locationListener;
import com.autonavi.amap.mapcore.Inner_3dMap_locationOption;

/* compiled from: AMapClientUtils.java */
/* loaded from: classes.dex */
public final class is {
    public static AMapLocationClientOption a() {
        return new AMapLocationClientOption();
    }

    public static void a(AMapLocationClientOption aMapLocationClientOption, Inner_3dMap_locationOption inner_3dMap_locationOption) {
        AMapLocationClientOption.AMapLocationMode aMapLocationMode;
        try {
            aMapLocationClientOption.setInterval(inner_3dMap_locationOption.getInterval());
            AMapLocationClientOption.AMapLocationMode aMapLocationMode2 = AMapLocationClientOption.AMapLocationMode.Hight_Accuracy;
            switch (inner_3dMap_locationOption.getLocationMode()) {
                case Battery_Saving:
                    aMapLocationMode = AMapLocationClientOption.AMapLocationMode.Battery_Saving;
                    break;
                case Device_Sensors:
                    aMapLocationMode = AMapLocationClientOption.AMapLocationMode.Device_Sensors;
                    break;
                case Hight_Accuracy:
                    aMapLocationMode = AMapLocationClientOption.AMapLocationMode.Hight_Accuracy;
                    break;
                default:
                    aMapLocationMode = AMapLocationClientOption.AMapLocationMode.Hight_Accuracy;
                    break;
            }
            aMapLocationClientOption.setLocationMode(aMapLocationMode);
            aMapLocationClientOption.setOnceLocation(inner_3dMap_locationOption.isOnceLocation());
            aMapLocationClientOption.setNeedAddress(inner_3dMap_locationOption.isNeedAddress());
        } catch (Throwable th) {
        }
    }

    public static void a(Object obj, Inner_3dMap_locationListener inner_3dMap_locationListener) {
        iu iuVar = new iu();
        iuVar.a(inner_3dMap_locationListener);
        ((AMapLocationClient) obj).setLocationListener(iuVar);
    }
}
