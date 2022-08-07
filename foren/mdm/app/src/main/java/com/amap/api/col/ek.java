package com.amap.api.col;

import android.content.ComponentName;
import android.content.Context;
import android.content.pm.ServiceInfo;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.autonavi.amap.mapcore.Inner_3dMap_locationListener;
import com.autonavi.amap.mapcore.Inner_3dMap_locationManagerBase;
import com.autonavi.amap.mapcore.Inner_3dMap_locationOption;

/* compiled from: AMapLocationClient.java */
/* loaded from: classes.dex */
public class ek {
    Context a;
    Inner_3dMap_locationManagerBase b = null;
    Object c = null;
    boolean d = false;
    Object e = null;

    public ek(Context context) {
        a(context);
    }

    private void a(Context context) {
        try {
            if (context == null) {
                throw new IllegalArgumentException("Context参数不能为null");
            }
            this.a = context.getApplicationContext();
            Class<?> cls = Class.forName("com.amap.api.location.AMapLocationClient");
            ServiceInfo serviceInfo = null;
            try {
                serviceInfo = this.a.getPackageManager().getServiceInfo(new ComponentName(this.a, "com.amap.api.location.APSService"), 128);
            } catch (Throwable th) {
            }
            if (!(cls == null || serviceInfo == null)) {
                this.d = true;
            }
            if (this.d) {
                this.c = new AMapLocationClient(this.a);
            } else {
                this.b = b(this.a);
            }
        } catch (Throwable th2) {
            jn.a(th2, "AMapLocationClient", "AMapLocationClient 1");
        }
    }

    private static Inner_3dMap_locationManagerBase b(Context context) {
        Inner_3dMap_locationManagerBase ixVar;
        try {
            ixVar = (Inner_3dMap_locationManagerBase) hm.a(context, jd.a(), "com.amap.api.wrapper.Inner_3dMap_locationManagerWrapper", ix.class, new Class[]{Context.class}, new Object[]{context});
        } catch (Throwable th) {
            ixVar = new ix(context);
        }
        return ixVar == null ? new ix(context) : ixVar;
    }

    public void a() {
        try {
            if (this.d) {
                ((AMapLocationClient) this.c).startLocation();
            } else {
                this.b.startLocation();
            }
        } catch (Throwable th) {
            jn.a(th, "AMapLocationClient", "startLocation");
        }
    }

    public void a(Inner_3dMap_locationListener inner_3dMap_locationListener) {
        try {
            if (inner_3dMap_locationListener == null) {
                throw new IllegalArgumentException("listener参数不能为null");
            } else if (this.d) {
                is.a(this.c, inner_3dMap_locationListener);
            } else {
                this.b.setLocationListener(inner_3dMap_locationListener);
            }
        } catch (Throwable th) {
            jn.a(th, "AMapLocationClient", "setLocationListener");
        }
    }

    public void a(Inner_3dMap_locationOption inner_3dMap_locationOption) {
        try {
            if (inner_3dMap_locationOption == null) {
                throw new IllegalArgumentException("LocationManagerOption参数不能为null");
            } else if (this.d) {
                AMapLocationClientOption a = is.a();
                is.a(a, inner_3dMap_locationOption);
                ((AMapLocationClient) this.c).setLocationOption(a);
            } else {
                this.b.setLocationOption(inner_3dMap_locationOption);
            }
        } catch (Throwable th) {
            jn.a(th, "AMapLocationClient", "setLocationOption");
        }
    }

    public void b() {
        try {
            if (this.d) {
                ((AMapLocationClient) this.c).stopLocation();
            } else {
                this.b.stopLocation();
            }
        } catch (Throwable th) {
            jn.a(th, "AMapLocationClient", "stopLocation");
        }
    }

    public void c() {
        try {
            if (this.d) {
                ((AMapLocationClient) this.c).onDestroy();
            } else {
                this.b.destroy();
            }
        } catch (Throwable th) {
            jn.a(th, "AMapLocationClient", "onDestroy");
        }
    }
}
