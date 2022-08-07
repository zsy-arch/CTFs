package com.amap.api.col;

import android.content.Context;
import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.navi.IGpsCallback;

/* compiled from: SDKLocationManager.java */
/* loaded from: classes.dex */
public class ff {
    public AMapLocationClient a;
    private IGpsCallback b;

    public ff(Context context) {
        this.a = null;
        this.a = new AMapLocationClient(context);
    }

    public void a(IGpsCallback iGpsCallback) {
        this.b = iGpsCallback;
    }

    public void a() {
        if (this.a != null) {
            AMapLocationClientOption aMapLocationClientOption = new AMapLocationClientOption();
            aMapLocationClientOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Device_Sensors);
            aMapLocationClientOption.setNeedAddress(false);
            aMapLocationClientOption.setInterval(1000L);
            aMapLocationClientOption.setOffset(true);
            this.a.setLocationOption(aMapLocationClientOption);
            this.a.setLocationListener(new AMapLocationListener() { // from class: com.amap.api.col.ff.1
                @Override // com.amap.api.location.AMapLocationListener
                public void onLocationChanged(AMapLocation aMapLocation) {
                    ff.this.a(aMapLocation);
                }
            });
            this.a.startLocation();
            if (this.b != null) {
                this.b.onGpsStarted();
            }
        }
    }

    public void a(long j) {
        if (this.a != null) {
            AMapLocationClientOption aMapLocationClientOption = new AMapLocationClientOption();
            aMapLocationClientOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Device_Sensors);
            aMapLocationClientOption.setNeedAddress(false);
            aMapLocationClientOption.setInterval(j);
            aMapLocationClientOption.setOffset(true);
            this.a.setLocationOption(aMapLocationClientOption);
            this.a.setLocationListener(new AMapLocationListener() { // from class: com.amap.api.col.ff.2
                @Override // com.amap.api.location.AMapLocationListener
                public void onLocationChanged(AMapLocation aMapLocation) {
                    ff.this.a(aMapLocation);
                }
            });
            this.a.startLocation();
            if (this.b != null) {
                this.b.onGpsStarted();
            }
        }
    }

    public void b() {
        if (this.a != null) {
            this.a.stopLocation();
        }
    }

    public void c() {
        if (this.a != null) {
            this.a.onDestroy();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(AMapLocation aMapLocation) {
        if (this.b != null) {
            this.b.onLocationChanged(2, aMapLocation);
            fr.a("SDKLOCATION", "定位sdk-" + aMapLocation.getErrorCode());
        }
    }
}
