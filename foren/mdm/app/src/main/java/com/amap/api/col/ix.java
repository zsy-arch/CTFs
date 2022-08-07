package com.amap.api.col;

import android.content.Context;
import android.os.Build;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import com.amap.api.services.geocoder.GeocodeSearch;
import com.autonavi.amap.mapcore.Inner_3dMap_location;
import com.autonavi.amap.mapcore.Inner_3dMap_locationListener;
import com.autonavi.amap.mapcore.Inner_3dMap_locationManagerBase;
import com.autonavi.amap.mapcore.Inner_3dMap_locationOption;
import java.util.ArrayList;
import java.util.Iterator;

/* compiled from: MapLocationManager.java */
/* loaded from: classes.dex */
public class ix implements Inner_3dMap_locationManagerBase {
    Context a;
    ArrayList<Inner_3dMap_locationListener> b = new ArrayList<>();
    Object c = new Object();
    Handler d = null;
    a e = null;
    Handler f = null;
    Inner_3dMap_locationOption g = new Inner_3dMap_locationOption();
    ja h = null;
    Inner_3dMap_locationOption.Inner_3dMap_Enum_LocationMode i = Inner_3dMap_locationOption.Inner_3dMap_Enum_LocationMode.Hight_Accuracy;
    boolean j = false;

    /* compiled from: MapLocationManager.java */
    /* loaded from: classes.dex */
    public static class a extends HandlerThread {
        ix a;

        public a(String str, ix ixVar) {
            super(str);
            this.a = ixVar;
        }

        @Override // android.os.HandlerThread
        protected final void onLooperPrepared() {
            try {
                this.a.h = new ja(this.a.a, this.a.d);
                super.onLooperPrepared();
            } catch (Throwable th) {
            }
        }
    }

    public ix(Context context) {
        this.a = null;
        if (context == null) {
            throw new IllegalArgumentException("Context参数不能为null");
        }
        this.a = context.getApplicationContext();
        e();
    }

    private Handler a(Looper looper) {
        Handler handler;
        synchronized (this.c) {
            this.f = new iy(looper, this);
            handler = this.f;
        }
        return handler;
    }

    private void a(int i) {
        synchronized (this.c) {
            if (this.f != null) {
                this.f.removeMessages(i);
            }
        }
    }

    private void a(int i, Object obj, long j) {
        synchronized (this.c) {
            if (this.f != null) {
                Message obtain = Message.obtain();
                obtain.what = i;
                obtain.obj = obj;
                this.f.sendMessageDelayed(obtain, j);
            }
        }
    }

    private void e() {
        try {
            if (Looper.myLooper() == null) {
                this.d = new iz(this.a.getMainLooper(), this);
            } else {
                this.d = new iz(this);
            }
        } catch (Throwable th) {
            jn.a(th, "LocationClientManager", "initResultHandler");
        }
        try {
            this.e = new a("locaitonClientActionThread", this);
            this.e.setPriority(5);
            this.e.start();
            this.f = a(this.e.getLooper());
        } catch (Throwable th2) {
            jn.a(th2, "LocationClientManager", "initActionThreadAndActionHandler");
        }
    }

    private void f() {
        synchronized (this.c) {
            if (this.f != null) {
                this.f.removeCallbacksAndMessages(null);
            }
            this.f = null;
        }
    }

    public final void a() {
        try {
            if (!this.j) {
                this.j = true;
                a(1005, null, 0L);
            }
        } catch (Throwable th) {
            jn.a(th, "LocationClientManager", "doStartLocation");
        }
    }

    public final void a(Inner_3dMap_location inner_3dMap_location) {
        try {
            if (this.j) {
                if (!GeocodeSearch.GPS.equalsIgnoreCase(inner_3dMap_location.getProvider())) {
                    inner_3dMap_location.setProvider("lbs");
                }
                inner_3dMap_location.setAltitude(jq.b(inner_3dMap_location.getAltitude()));
                inner_3dMap_location.setBearing(jq.a(inner_3dMap_location.getBearing()));
                inner_3dMap_location.setSpeed(jq.a(inner_3dMap_location.getSpeed()));
                Iterator<Inner_3dMap_locationListener> it = this.b.iterator();
                while (it.hasNext()) {
                    try {
                        it.next().onLocationChanged(inner_3dMap_location);
                    } catch (Throwable th) {
                    }
                }
            }
            if (this.g.isOnceLocation()) {
                c();
            }
        } catch (Throwable th2) {
            jn.a(th2, "LocationClientManger", "callBackLocation");
        }
    }

    public final void a(Inner_3dMap_locationListener inner_3dMap_locationListener) {
        try {
            if (inner_3dMap_locationListener == null) {
                throw new IllegalArgumentException("listener参数不能为null");
            }
            if (this.b == null) {
                this.b = new ArrayList<>();
            }
            if (!this.b.contains(inner_3dMap_locationListener)) {
                this.b.add(inner_3dMap_locationListener);
            }
        } catch (Throwable th) {
            jn.a(th, "LocationClientManager", "doSetLocationListener");
        }
    }

    public final void a(Inner_3dMap_locationOption inner_3dMap_locationOption) {
        this.g = inner_3dMap_locationOption;
        if (this.g == null) {
            this.g = new Inner_3dMap_locationOption();
        }
        if (this.h != null) {
            this.h.a(this.g);
        }
        if (this.j && !this.i.equals(inner_3dMap_locationOption.getLocationMode())) {
            c();
            a();
        }
        this.i = this.g.getLocationMode();
    }

    public final void b() {
        long j = 1000;
        try {
            if (this.h != null) {
                this.h.a();
            }
        } finally {
            if (!this.g.isOnceLocation()) {
                if (this.g.getInterval() >= 1000) {
                    j = this.g.getInterval();
                }
                a(1005, null, j);
            }
        }
    }

    public final void b(Inner_3dMap_locationListener inner_3dMap_locationListener) {
        if (inner_3dMap_locationListener != null) {
            try {
                if (!this.b.isEmpty() && this.b.contains(inner_3dMap_locationListener)) {
                    this.b.remove(inner_3dMap_locationListener);
                }
            } catch (Throwable th) {
                jn.a(th, "LocationClientManager", "doUnregisterListener");
                return;
            }
        }
        if (this.b.isEmpty()) {
            c();
        }
    }

    public final void c() {
        try {
            this.j = false;
            a(1004);
            a(1005);
            if (this.h != null) {
                this.h.c();
            }
        } catch (Throwable th) {
            jn.a(th, "LocationClientManager", "doStopLocation");
        }
    }

    public final void d() {
        c();
        if (this.h != null) {
            this.h.d();
        }
        f();
        if (this.e != null) {
            if (Build.VERSION.SDK_INT >= 18) {
                try {
                    jo.a(this.e, HandlerThread.class, "quitSafely", new Object[0]);
                } catch (Throwable th) {
                    this.e.quit();
                }
            } else {
                this.e.quit();
            }
        }
        this.e = null;
    }

    @Override // com.autonavi.amap.mapcore.Inner_3dMap_locationManagerBase
    public void destroy() {
        try {
            a(1007, null, 0L);
        } catch (Throwable th) {
            jn.a(th, "LocationClientManager", "stopLocation");
        }
    }

    @Override // com.autonavi.amap.mapcore.Inner_3dMap_locationManagerBase
    public void setLocationListener(Inner_3dMap_locationListener inner_3dMap_locationListener) {
        try {
            a(1002, inner_3dMap_locationListener, 0L);
        } catch (Throwable th) {
            jn.a(th, "LocationClientManager", "setLocationListener");
        }
    }

    @Override // com.autonavi.amap.mapcore.Inner_3dMap_locationManagerBase
    public void setLocationOption(Inner_3dMap_locationOption inner_3dMap_locationOption) {
        try {
            a(1001, inner_3dMap_locationOption, 0L);
        } catch (Throwable th) {
            jn.a(th, "LocationClientManager", "setLocationOption");
        }
    }

    @Override // com.autonavi.amap.mapcore.Inner_3dMap_locationManagerBase
    public void startLocation() {
        try {
            a(1004, null, 0L);
        } catch (Throwable th) {
            jn.a(th, "LocationClientManager", "startLocation");
        }
    }

    @Override // com.autonavi.amap.mapcore.Inner_3dMap_locationManagerBase
    public void stopLocation() {
        try {
            a(1006, null, 0L);
        } catch (Throwable th) {
            jn.a(th, "LocationClientManager", "stopLocation");
        }
    }

    @Override // com.autonavi.amap.mapcore.Inner_3dMap_locationManagerBase
    public void unRegisterLocationListener(Inner_3dMap_locationListener inner_3dMap_locationListener) {
        try {
            a(1006, inner_3dMap_locationListener, 0L);
        } catch (Throwable th) {
            jn.a(th, "LocationClientManager", "stopLocation");
        }
    }
}
