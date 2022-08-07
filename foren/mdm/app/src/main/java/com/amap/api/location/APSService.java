package com.amap.api.location;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import com.loc.au;
import com.loc.cv;
import com.loc.e;
import com.loc.f;

/* loaded from: classes.dex */
public class APSService extends Service {
    APSServiceBase a;

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        try {
            return this.a.onBind(intent);
        } catch (Throwable th) {
            f.a(th, "APSService", "onBind");
            return null;
        }
    }

    @Override // android.app.Service
    public void onCreate() {
        onCreate(this);
    }

    public void onCreate(Context context) {
        try {
            if (cv.d(context)) {
                this.a = (APSServiceBase) au.a(context, f.a("loc"), "com.amap.api.location.APSServiceWrapper", e.class, new Class[]{Context.class}, new Object[]{context});
            } else if (this.a == null) {
                this.a = new e(this);
            }
        } catch (Throwable th) {
        }
        try {
            if (this.a == null) {
                this.a = new e(this);
            }
            this.a.onCreate();
        } catch (Throwable th2) {
            f.a(th2, "APSService", "onCreate");
        }
        super.onCreate();
    }

    @Override // android.app.Service
    public void onDestroy() {
        try {
            this.a.onDestroy();
        } catch (Throwable th) {
            f.a(th, "APSService", "onDestroy");
        }
        super.onDestroy();
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int i, int i2) {
        try {
            return this.a.onStartCommand(intent, i, i2);
        } catch (Throwable th) {
            f.a(th, "APSService", "onStartCommand");
            return super.onStartCommand(intent, i, i2);
        }
    }
}
