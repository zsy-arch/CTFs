package com.amap.api.col;

import android.content.Context;
import android.os.Build;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import com.autonavi.amap.mapcore.Inner_3dMap_location;
import com.autonavi.amap.mapcore.Inner_3dMap_locationOption;
import com.hyphenate.util.EMPrivateConstant;
import org.json.JSONArray;
import org.json.JSONObject;

/* compiled from: MapLocationService.java */
/* loaded from: classes.dex */
public final class ja {
    private static int k = 200;
    private static boolean l = true;
    Context a;
    b d;
    Handler e;
    Handler f;
    Inner_3dMap_locationOption i;
    iv b = null;
    jb c = null;
    boolean g = false;
    boolean h = false;
    private JSONArray m = null;
    Object j = new Object();

    /* compiled from: MapLocationService.java */
    /* loaded from: classes.dex */
    public class a extends Handler {
        public a(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public final void handleMessage(Message message) {
            switch (message.what) {
                case 1:
                    ja.this.b();
                    return;
                default:
                    return;
            }
        }
    }

    /* compiled from: MapLocationService.java */
    /* loaded from: classes.dex */
    class b extends HandlerThread {
        public b(String str) {
            super(str);
        }

        @Override // android.os.HandlerThread
        protected final void onLooperPrepared() {
            super.onLooperPrepared();
        }
    }

    public ja(Context context, Handler handler) {
        this.a = null;
        this.d = null;
        this.e = null;
        this.f = null;
        this.i = null;
        try {
            if (context == null) {
                throw new IllegalArgumentException("Context参数不能为null");
            }
            this.a = context.getApplicationContext();
            this.f = handler;
            this.i = new Inner_3dMap_locationOption();
            e();
            this.d = new b("locServiceAction");
            this.d.setPriority(5);
            this.d.start();
            this.e = new a(this.d.getLooper());
        } catch (Throwable th) {
            jn.a(th, "LocationService", "<init>");
        }
    }

    private void a(Inner_3dMap_location inner_3dMap_location) {
        try {
            if (l && inner_3dMap_location != null && inner_3dMap_location.getErrorCode() == 0 && inner_3dMap_location.getLocationType() == 1) {
                if (this.m == null) {
                    this.m = new JSONArray();
                }
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("lon", inner_3dMap_location.getLongitude());
                jSONObject.put("lat", inner_3dMap_location.getLatitude());
                jSONObject.put("type", 0);
                jSONObject.put("timestamp", jq.a());
                this.m = this.m.put(jSONObject);
                if (this.m.length() >= k) {
                    g();
                }
            }
        } catch (Throwable th) {
            jn.a(th, "LocationService", "recordOfflineLocLog");
        }
    }

    private void e() {
        try {
            if (this.i == null) {
                this.i = new Inner_3dMap_locationOption();
            }
            if (!this.h) {
                this.b = new iv(this.a);
                this.c = new jb(this.a);
                this.c.a(this.i);
                f();
                this.h = true;
            }
        } catch (Throwable th) {
            jn.a(th, "LocationService", "init");
        }
    }

    private void f() {
        try {
            l = jp.b(this.a, "maploc", "ue");
            int a2 = jp.a(this.a, "maploc", "opn");
            k = a2;
            if (a2 > 500) {
                k = 500;
            }
            if (k < 30) {
                k = 30;
            }
        } catch (Throwable th) {
            jn.a(th, "LocationService", "getSPConfig");
        }
    }

    private synchronized void g() {
        if (this.m != null && this.m.length() > 0) {
            il.a(new ik(this.a, jn.a("loc"), this.m.toString()), this.a);
            this.m = null;
        }
    }

    private void h() {
        synchronized (this.j) {
            if (this.e != null) {
                this.e.removeCallbacksAndMessages(null);
            }
            this.e = null;
        }
    }

    private void i() {
        synchronized (this.j) {
            if (this.e != null) {
                this.e.removeMessages(1);
            }
        }
    }

    public final void a() {
        try {
            e();
            if (!this.i.getLocationMode().equals(Inner_3dMap_locationOption.Inner_3dMap_Enum_LocationMode.Battery_Saving) && !this.g) {
                this.g = true;
                this.b.a();
            }
            if (this.e != null) {
                this.e.sendEmptyMessage(1);
            }
        } catch (Throwable th) {
            jn.a(th, "LocationService", "getLocation");
        }
    }

    public final void a(Inner_3dMap_locationOption inner_3dMap_locationOption) {
        this.i = inner_3dMap_locationOption;
        if (this.i == null) {
            this.i = new Inner_3dMap_locationOption();
        }
        if (this.c != null) {
            this.c.a(inner_3dMap_locationOption);
        }
    }

    final void b() {
        Inner_3dMap_location inner_3dMap_location = null;
        try {
            if (this.i.getLocationMode().equals(Inner_3dMap_locationOption.Inner_3dMap_Enum_LocationMode.Battery_Saving) && this.g) {
                this.b.b();
                this.g = false;
            }
            if (this.b.c()) {
                inner_3dMap_location = this.b.d();
            } else if (!this.i.getLocationMode().equals(Inner_3dMap_locationOption.Inner_3dMap_Enum_LocationMode.Device_Sensors)) {
                inner_3dMap_location = this.c.a();
            }
            if (!(this.f == null || inner_3dMap_location == null)) {
                Message obtain = Message.obtain();
                obtain.obj = inner_3dMap_location;
                obtain.what = 1;
                this.f.sendMessage(obtain);
            }
            a(inner_3dMap_location);
        } catch (Throwable th) {
            jn.a(th, "LocationService", "doGetLocation");
        }
    }

    public final void c() {
        this.g = false;
        try {
            i();
            if (this.b != null) {
                this.b.b();
            }
        } catch (Throwable th) {
            jn.a(th, "LocationService", "stopLocation");
        }
    }

    public final void d() {
        try {
            c();
            h();
            if (this.d != null) {
                if (Build.VERSION.SDK_INT >= 18) {
                    jo.a(this.d, HandlerThread.class, "quitSafely", new Object[0]);
                } else {
                    this.d.quit();
                }
            }
            this.d = null;
            this.c.b();
            g();
        } catch (Throwable th) {
            jn.a(th, "LocationService", EMPrivateConstant.EMMultiUserConstant.ITEM_DESTROY);
        }
    }
}
