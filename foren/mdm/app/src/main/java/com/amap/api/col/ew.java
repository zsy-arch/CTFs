package com.amap.api.col;

import android.content.ComponentName;
import android.content.Context;
import android.content.pm.ServiceInfo;
import com.amap.api.navi.IGpsCallback;

/* compiled from: GpsAdapter.java */
/* loaded from: classes.dex */
public class ew {
    private ev a;
    private ff b;
    private boolean c;

    public ew(Context context) {
        this.c = false;
        this.c = a(context);
        if (this.c) {
            this.b = new ff(context);
            fr.a(context, "定位SDK定位～");
            return;
        }
        this.a = new ev(context);
        fr.a(context, "系统GPS定位～");
    }

    public void a(IGpsCallback iGpsCallback) {
        if (this.c) {
            this.b.a(iGpsCallback);
        } else {
            this.a.a(iGpsCallback);
        }
    }

    public void a() {
        if (this.c) {
            fr.d("开始SDK定位~");
            this.b.a();
            return;
        }
        fr.d("开始系统定位~");
        this.a.c();
    }

    public void a(long j) {
        if (this.c) {
            this.b.a(j);
        } else {
            this.a.a(j, 0);
        }
    }

    public void b() {
        if (this.c) {
            this.b.b();
        } else {
            this.a.d();
        }
    }

    public void c() {
        if (this.c) {
            this.b.c();
        } else {
            this.a.a();
        }
    }

    public boolean a(Context context) {
        try {
            Class<?> cls = Class.forName("com.amap.api.location.AMapLocationClient");
            ServiceInfo serviceInfo = context.getPackageManager().getServiceInfo(new ComponentName(context, "com.amap.api.location.APSService"), 128);
            if (cls == null || serviceInfo == null) {
                return false;
            }
            return true;
        } catch (Throwable th) {
            return false;
        }
    }
}
