package com.amap.api.col;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Looper;
import com.amap.api.maps.CoordinateConverter;
import com.amap.api.maps.model.LatLng;
import com.amap.api.services.geocoder.GeocodeSearch;
import com.autonavi.amap.mapcore.Inner_3dMap_location;

/* compiled from: MapGPSLocation.java */
/* loaded from: classes.dex */
public final class iv {
    Context a;
    LocationManager b;
    CoordinateConverter g;
    volatile long c = 0;
    volatile boolean d = false;
    boolean e = false;
    volatile Inner_3dMap_location f = null;
    LocationListener h = new LocationListener() { // from class: com.amap.api.col.iv.1
        @Override // android.location.LocationListener
        public final void onLocationChanged(Location location) {
            if (location != null) {
                try {
                    Inner_3dMap_location inner_3dMap_location = new Inner_3dMap_location(location);
                    inner_3dMap_location.setLocationType(1);
                    Bundle extras = location.getExtras();
                    int i = 0;
                    if (extras != null) {
                        i = extras.getInt("satellites");
                    }
                    inner_3dMap_location.setSatellites(i);
                    iv.this.f = inner_3dMap_location;
                    iv.this.c = jq.b();
                    iv.this.d = true;
                } catch (Throwable th) {
                    jn.a(th, "MAPGPSLocation", "onLocationChanged");
                }
            }
        }

        @Override // android.location.LocationListener
        public final void onProviderDisabled(String str) {
            try {
                if (GeocodeSearch.GPS.equals(str)) {
                    iv.this.d = false;
                }
            } catch (Throwable th) {
                jn.a(th, "MAPGPSLocation", "onProviderDisabled");
            }
        }

        @Override // android.location.LocationListener
        public final void onProviderEnabled(String str) {
        }

        @Override // android.location.LocationListener
        public final void onStatusChanged(String str, int i, Bundle bundle) {
        }
    };

    public iv(Context context) {
        this.g = null;
        if (context != null) {
            this.a = context;
            if (this.g == null) {
                this.g = new CoordinateConverter(context);
            }
            if (this.b == null) {
                this.b = (LocationManager) this.a.getSystemService("location");
            }
        }
    }

    private void e() {
        try {
            try {
                Looper myLooper = Looper.myLooper();
                if (myLooper == null) {
                    myLooper = this.a.getMainLooper();
                }
                try {
                    this.b.sendExtraCommand(GeocodeSearch.GPS, "force_xtra_injection", new Bundle());
                } catch (Throwable th) {
                }
                this.b.requestLocationUpdates(GeocodeSearch.GPS, 800L, 0.0f, this.h, myLooper);
            } catch (Throwable th2) {
                jn.a(th2, "MAPGPSLocation", "requestLocationUpdates");
            }
        } catch (SecurityException e) {
        }
    }

    private void f() {
        this.d = false;
        this.c = 0L;
        this.f = null;
    }

    public final void a() {
        if (!this.e) {
            e();
            this.e = true;
        }
    }

    public final void b() {
        this.e = false;
        f();
        if (this.b != null && this.h != null) {
            this.b.removeUpdates(this.h);
        }
    }

    public final boolean c() {
        if (this.d) {
            if (jq.b() - this.c <= 10000) {
                return true;
            }
            this.f = null;
        }
        return false;
    }

    public final Inner_3dMap_location d() {
        Inner_3dMap_location inner_3dMap_location = this.f;
        if (this.f != null && this.f.getErrorCode() == 0) {
            try {
                if (this.g != null && jn.a(this.f.getLatitude(), this.f.getLongitude())) {
                    LatLng convert = this.g.coord(new LatLng(this.f.getLatitude(), this.f.getLongitude())).from(CoordinateConverter.CoordType.GPS).convert();
                    inner_3dMap_location.setLatitude(convert.latitude);
                    inner_3dMap_location.setLongitude(convert.longitude);
                }
            } catch (Throwable th) {
            }
        }
        return inner_3dMap_location;
    }
}
