package com.amap.api.location;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import com.loc.au;
import com.loc.cv;
import com.loc.d;
import com.loc.f;
import com.loc.s;

/* loaded from: classes.dex */
public class AMapLocationClient {
    Context a;
    LocationManagerBase b;

    public AMapLocationClient(Context context) {
        try {
            if (context == null) {
                throw new IllegalArgumentException("Context参数不能为null");
            }
            this.a = context.getApplicationContext();
            this.b = a(this.a, null);
        } catch (Throwable th) {
            f.a(th, "AMapLocationClient", "AMapLocationClient 1");
        }
    }

    public AMapLocationClient(Context context, Intent intent) {
        try {
            if (context == null) {
                throw new IllegalArgumentException("Context参数不能为null");
            }
            this.a = context.getApplicationContext();
            this.b = a(this.a, intent);
        } catch (Throwable th) {
            f.a(th, "AMapLocationClient", "AMapLocationClient 2");
        }
    }

    private static LocationManagerBase a(Context context, Intent intent) {
        LocationManagerBase dVar;
        try {
            s a = f.a("loc");
            cv.a(context, a);
            boolean c = cv.c(context);
            cv.a(context);
            dVar = c ? (LocationManagerBase) au.a(context, a, "com.amap.api.location.LocationManagerWrapper", d.class, new Class[]{Context.class, Intent.class}, new Object[]{context, intent}) : new d(context, intent);
        } catch (Throwable th) {
            dVar = new d(context, intent);
        }
        return dVar == null ? new d(context, intent) : dVar;
    }

    public static void setApiKey(String str) {
        try {
            AMapLocationClientOption.a = str;
        } catch (Throwable th) {
            f.a(th, "AMapLocationClient", "setApiKey");
        }
    }

    public void addGeoFenceAlert(String str, double d, double d2, float f, long j, PendingIntent pendingIntent) {
        try {
            this.b.addGeoFenceAlert(str, d, d2, f, j, pendingIntent);
        } catch (Throwable th) {
            f.a(th, "AMapLocationClient", "addGeoFenceAlert");
        }
    }

    public AMapLocation getLastKnownLocation() {
        try {
            if (this.b != null) {
                return this.b.getLastKnownLocation();
            }
        } catch (Throwable th) {
            f.a(th, "AMapLocationClient", "getLastKnownLocation");
        }
        return null;
    }

    public String getVersion() {
        return "3.3.0";
    }

    public boolean isStarted() {
        try {
            if (this.b != null) {
                return this.b.isStarted();
            }
        } catch (Throwable th) {
            f.a(th, "AMapLocationClient", "isStarted");
        }
        return false;
    }

    public void onDestroy() {
        try {
            this.b.onDestroy();
        } catch (Throwable th) {
            f.a(th, "AMapLocationClient", "onDestroy");
        }
    }

    public void removeGeoFenceAlert(PendingIntent pendingIntent) {
        try {
            this.b.removeGeoFenceAlert(pendingIntent);
        } catch (Throwable th) {
            f.a(th, "AMapLocationClient", "removeGeoFenceAlert 2");
        }
    }

    public void removeGeoFenceAlert(PendingIntent pendingIntent, String str) {
        try {
            this.b.removeGeoFenceAlert(pendingIntent, str);
        } catch (Throwable th) {
            f.a(th, "AMapLocationClient", "removeGeoFenceAlert 1");
        }
    }

    public void setLocationListener(AMapLocationListener aMapLocationListener) {
        try {
            if (aMapLocationListener == null) {
                throw new IllegalArgumentException("listener参数不能为null");
            }
            this.b.setLocationListener(aMapLocationListener);
        } catch (Throwable th) {
            f.a(th, "AMapLocationClient", "setLocationListener");
        }
    }

    public void setLocationOption(AMapLocationClientOption aMapLocationClientOption) {
        try {
            if (aMapLocationClientOption == null) {
                throw new IllegalArgumentException("LocationManagerOption参数不能为null");
            }
            this.b.setLocationOption(aMapLocationClientOption);
        } catch (Throwable th) {
            f.a(th, "AMapLocationClient", "setLocationOption");
        }
    }

    public void startAssistantLocation() {
        try {
            this.b.startAssistantLocation();
        } catch (Throwable th) {
            f.a(th, "AMapLocationClient", "startAssistantLocation");
        }
    }

    public void startLocation() {
        try {
            this.b.startLocation();
        } catch (Throwable th) {
            f.a(th, "AMapLocationClient", "startLocation");
        }
    }

    public void stopAssistantLocation() {
        try {
            this.b.startAssistantLocation();
        } catch (Throwable th) {
            f.a(th, "AMapLocationClient", "stopAssistantLocation");
        }
    }

    public void stopLocation() {
        try {
            this.b.stopLocation();
        } catch (Throwable th) {
            f.a(th, "AMapLocationClient", "stopLocation");
        }
    }

    public void unRegisterLocationListener(AMapLocationListener aMapLocationListener) {
        try {
            this.b.unRegisterLocationListener(aMapLocationListener);
        } catch (Throwable th) {
            f.a(th, "AMapLocationClient", "unRegisterLocationListener");
        }
    }
}
