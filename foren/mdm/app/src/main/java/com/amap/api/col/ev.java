package com.amap.api.col;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import com.amap.api.maps.CoordinateConverter;
import com.amap.api.maps.model.LatLng;
import com.amap.api.navi.IGpsCallback;
import com.amap.api.services.geocoder.GeocodeSearch;
import java.io.File;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: GPSManager.java */
/* loaded from: classes.dex */
public class ev implements LocationListener {
    private String a;
    private LocationManager b;
    private Context c;
    private IGpsCallback d;
    private a e = new a();
    private boolean f = false;
    private long g = 1000;
    private int h = 5;
    private int i = 0;
    private int j = 0;
    private boolean k = false;
    private boolean l = false;
    private int m = 0;
    private long n = 0;
    private Location o = null;
    private Handler p = new Handler(new Handler.Callback() { // from class: com.amap.api.col.ev.1
        @Override // android.os.Handler.Callback
        public boolean handleMessage(Message message) {
            switch (message.what) {
                case 1:
                    Location location = (Location) message.obj;
                    if (location == null || ev.this.d == null) {
                        return false;
                    }
                    ev.this.d.onLocationChanged(1, location);
                    return false;
                default:
                    return false;
            }
        }
    });
    private JSONArray q;
    private CoordinateConverter r;

    public ev(Context context) {
        try {
            this.c = context;
            if (this.c != null) {
                this.a = fn.a(context).getAbsolutePath() + File.separator + "navigation" + File.separator;
                this.b = (LocationManager) this.c.getSystemService("location");
            }
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("android.location.PROVIDERS_CHANGED");
            if (this.c != null) {
                this.c.registerReceiver(this.e, intentFilter);
            }
        } catch (Throwable th) {
            fn.a(th);
            gr.b(th, "GPSManager", "GPSManager(Context context, WTBTControl tbtControl)");
        }
    }

    public void a(IGpsCallback iGpsCallback) {
        this.d = iGpsCallback;
    }

    public void a() {
        try {
            d();
            a(this.c);
            e();
            if (this.c != null) {
                this.c.unregisterReceiver(this.e);
            }
            this.e = null;
            this.k = false;
        } catch (Throwable th) {
            fn.a(th);
            gr.b(th, "GPSManager", "destroy()");
        }
    }

    private synchronized void e() {
        this.b = null;
    }

    @Override // android.location.LocationListener
    public void onLocationChanged(Location location) {
        if (location != null) {
            try {
                if (this.d != null) {
                    this.d.onLocationChanged(1, location);
                    fr.a("SDKLOCATION", "设备GPS");
                    a(location);
                }
            } catch (Throwable th) {
                fn.a(th);
                gr.b(th, "GPSManager", "onLocationChanged(Location location)");
            }
        }
    }

    @Override // android.location.LocationListener
    public void onStatusChanged(String str, int i, Bundle bundle) {
    }

    @Override // android.location.LocationListener
    public void onProviderEnabled(String str) {
    }

    @Override // android.location.LocationListener
    public void onProviderDisabled(String str) {
    }

    public boolean b() {
        return this.f;
    }

    public void c() {
        try {
            if (this.b != null) {
                if (!b()) {
                    this.b.removeUpdates(this);
                    this.b.requestLocationUpdates(GeocodeSearch.GPS, 1000L, 0.0f, this);
                    if (this.d != null) {
                        this.d.onGpsStarted();
                    }
                }
                this.f = true;
            }
        } catch (Throwable th) {
            fn.a(th);
            gr.b(th, "GPSManager", "startGPS()");
        }
    }

    public void a(long j, int i) {
        try {
            if (this.b != null) {
                if (!(b() && this.g == j && this.h == i)) {
                    this.b.removeUpdates(this);
                    this.b.requestLocationUpdates(GeocodeSearch.GPS, j, i, this);
                    this.g = j;
                    this.h = i;
                    if (this.d != null) {
                        this.d.onGpsStarted();
                    }
                }
                this.f = true;
            }
        } catch (Throwable th) {
            fn.a(th);
            gr.b(th, "GPSManager", "startGPS(long minTime, int minDis)");
        }
    }

    public void d() {
        if (this.b != null) {
            this.b.removeUpdates(this);
            this.f = false;
        }
    }

    /* compiled from: GPSManager.java */
    /* loaded from: classes.dex */
    private class a extends BroadcastReceiver {
        private a() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            try {
                if ("android.location.PROVIDERS_CHANGED".equals(intent.getAction()) && ev.this.b != null && ev.this.b.isProviderEnabled(GeocodeSearch.GPS) && ev.this.b()) {
                    ev.this.b.removeUpdates(ev.this);
                    ev.this.b.requestLocationUpdates(GeocodeSearch.GPS, ev.this.g, ev.this.h, ev.this);
                }
            } catch (Throwable th) {
                th.printStackTrace();
                gr.b(th, "GPSManager", "MyBroadcastReceiver.onReceive(Context ctx, android.content.Intent intent)");
            }
        }
    }

    public void a(Location location) {
        JSONObject jSONObject = new JSONObject();
        try {
            if (this.r == null) {
                this.r = new CoordinateConverter(this.c);
            }
            this.r.from(CoordinateConverter.CoordType.GPS);
            this.r.coord(new LatLng(location.getLatitude(), location.getLongitude()));
            LatLng convert = this.r.convert();
            jSONObject.put("lon", convert.longitude);
            jSONObject.put("lat", convert.latitude);
            jSONObject.put("type", 0);
            jSONObject.put("timestamp", System.currentTimeMillis());
            if (this.q == null) {
                this.q = new JSONArray();
            }
            this.q.put(jSONObject);
            if (this.q.length() >= 200) {
                a(this.c);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void a(Context context) {
        if (context != null) {
            try {
                if (this.q != null && this.q.length() > 0) {
                    il.a(new ik(context, fn.a(), this.q.toString()), context);
                    this.q = null;
                }
            } catch (Throwable th) {
                gr.b(th, "GPSManager", "writeOfflineLocLog()");
            }
        }
    }
}
