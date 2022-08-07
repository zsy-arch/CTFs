package com.loc;

import android.content.Context;
import android.location.GpsSatellite;
import android.location.GpsStatus;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.CoordinateConverter;
import com.amap.api.location.DPoint;
import com.amap.api.services.geocoder.GeocodeSearch;
import com.em.db.UserDao;
import com.loc.d;
import java.util.Iterator;

/* compiled from: GPSLocation.java */
/* loaded from: classes2.dex */
public final class g {
    Handler a;
    Context b;
    LocationManager c;
    AMapLocationClientOption d;
    ct e;
    CoordinateConverter f;
    private long l = 0;
    boolean g = false;
    long h = 0;
    LocationListener i = new LocationListener() { // from class: com.loc.g.1
        @Override // android.location.LocationListener
        public final void onLocationChanged(Location location) {
            int i = 0;
            if (g.this.a != null) {
                g.this.a.removeMessages(8);
            }
            if (location != null) {
                try {
                    AMapLocation aMapLocation = new AMapLocation(location);
                    Bundle extras = location.getExtras();
                    if (extras != null) {
                        i = extras.getInt("satellites");
                    }
                    if (cx.a(location, g.this.j) && !g.this.d.isMockEnable()) {
                        aMapLocation.setErrorCode(15);
                        location.setLatitude(0.0d);
                        location.setLongitude(0.0d);
                    }
                    g.this.e.b(cx.b());
                    if (!f.a(location.getLatitude(), location.getLongitude()) || !g.this.d.isOffset()) {
                        aMapLocation.setLatitude(location.getLatitude());
                        aMapLocation.setLongitude(location.getLongitude());
                        aMapLocation.setLocationType(1);
                    } else {
                        aMapLocation.setLocationType(1);
                        DPoint convert = g.this.f.coord(new DPoint(location.getLatitude(), location.getLongitude())).from(CoordinateConverter.CoordType.GPS).convert();
                        aMapLocation.setLatitude(convert.getLatitude());
                        aMapLocation.setLongitude(convert.getLongitude());
                    }
                    aMapLocation.setSatellites(i);
                    long b = cx.b();
                    if (b - g.this.h >= g.this.d.getInterval() - 200) {
                        g.this.h = cx.b();
                        if (g.this.a != null) {
                            Message obtain = Message.obtain();
                            obtain.obj = aMapLocation;
                            obtain.what = 2;
                            g.this.a.sendMessage(obtain);
                        }
                    } else if (g.this.a != null) {
                        Message obtain2 = Message.obtain();
                        if (g.this.d.getInterval() > 8000 && b - g.this.h > g.this.d.getInterval() - 8000) {
                            obtain2.obj = aMapLocation;
                        } else if (g.this.d.getInterval() <= 8000) {
                            obtain2.obj = aMapLocation;
                        }
                        obtain2.what = 5;
                        if (aMapLocation.getErrorCode() == 0) {
                            g.this.a.sendMessage(obtain2);
                        }
                    }
                    if (!f.l && !cw.b(g.this.b, UserDao.PREF_TABLE_NAME, "colde", false)) {
                        f.l = true;
                        cw.a(g.this.b, UserDao.PREF_TABLE_NAME, "colde", true);
                    }
                    if (g.this.l != 0 && !g.this.g) {
                        cu.b(g.this.b, g.this.e);
                        g.this.g = true;
                    }
                } catch (Throwable th) {
                    f.a(th, "GPSLocation", "onLocationChanged");
                }
            }
        }

        @Override // android.location.LocationListener
        public final void onProviderDisabled(String str) {
            try {
                if (GeocodeSearch.GPS.equals(str) && g.this.a != null) {
                    g.this.a.sendEmptyMessage(3);
                }
            } catch (Throwable th) {
                f.a(th, "GPSLocation", "onProviderDisabled");
            }
        }

        @Override // android.location.LocationListener
        public final void onProviderEnabled(String str) {
        }

        @Override // android.location.LocationListener
        public final void onStatusChanged(String str, int i, Bundle bundle) {
            if (i == 0 || i == 1) {
                try {
                    if (g.this.a != null) {
                        g.this.a.sendEmptyMessage(3);
                    }
                } catch (Throwable th) {
                    f.a(th, "GPSLocation", "onStatusChanged");
                }
            }
        }
    };
    public int j = 0;
    GpsStatus k = null;
    private GpsStatus.Listener m = new GpsStatus.Listener() { // from class: com.loc.g.2
        @Override // android.location.GpsStatus.Listener
        public final void onGpsStatusChanged(int i) {
            try {
                g.this.k = g.this.c.getGpsStatus(g.this.k);
                switch (i) {
                    case 4:
                        Iterator<GpsSatellite> it = g.this.k.getSatellites().iterator();
                        int i2 = 0;
                        int maxSatellites = g.this.k.getMaxSatellites();
                        while (it.hasNext() && i2 < maxSatellites) {
                            i2 = it.next().usedInFix() ? i2 + 1 : i2;
                        }
                        g.this.j = i2;
                        return;
                    default:
                        return;
                }
            } catch (Throwable th) {
                f.a(th, "GPSLocation", "onGpsStatusChanged");
            }
        }
    };

    public g(Context context, d.HandlerC0046d dVar) {
        this.e = null;
        this.f = null;
        this.b = context;
        this.f = new CoordinateConverter(this.b.getApplicationContext());
        this.a = dVar;
        this.c = (LocationManager) this.b.getSystemService("location");
        this.e = new ct();
    }

    public final void a() {
        if (this.c != null) {
            if (this.i != null) {
                this.c.removeUpdates(this.i);
            }
            if (this.m != null) {
                this.c.removeGpsStatusListener(this.m);
            }
            if (this.a != null) {
                this.a.removeMessages(8);
            }
            this.j = 0;
            this.l = 0L;
            this.h = 0L;
            this.g = false;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void b() {
        long j = 1000;
        try {
            try {
                Looper myLooper = Looper.myLooper();
                if (myLooper == null) {
                    myLooper = this.b.getMainLooper();
                }
                this.l = cx.b();
                this.e.a(this.l);
                try {
                    this.c.sendExtraCommand(GeocodeSearch.GPS, "force_xtra_injection", new Bundle());
                } catch (Throwable th) {
                }
                if (this.d != null && this.d.getInterval() < 1000) {
                    j = this.d.getInterval();
                }
                this.c.requestLocationUpdates(GeocodeSearch.GPS, j, 0.0f, this.i, myLooper);
                this.c.addGpsStatusListener(this.m);
                if (this.a != null && this.d.getLocationMode() == AMapLocationClientOption.AMapLocationMode.Device_Sensors) {
                    Message obtain = Message.obtain();
                    AMapLocation aMapLocation = new AMapLocation("");
                    aMapLocation.setProvider(GeocodeSearch.GPS);
                    aMapLocation.setErrorCode(14);
                    aMapLocation.setLocationDetail("no enough satellites");
                    aMapLocation.setLocationType(1);
                    obtain.obj = aMapLocation;
                    obtain.what = 8;
                    this.a.sendMessageDelayed(obtain, this.d.getHttpTimeOut());
                }
            } catch (Throwable th2) {
                f.a(th2, "GPSLocation", "requestLocationUpdates part2");
            }
        } catch (SecurityException e) {
            if (AMapLocationClientOption.AMapLocationMode.Device_Sensors.equals(this.d.getLocationMode())) {
                Message obtain2 = Message.obtain();
                AMapLocation aMapLocation2 = new AMapLocation("");
                aMapLocation2.setProvider(GeocodeSearch.GPS);
                aMapLocation2.setErrorCode(12);
                aMapLocation2.setLocationDetail(e.getMessage());
                aMapLocation2.setLocationType(1);
                obtain2.what = 2;
                obtain2.obj = aMapLocation2;
                if (this.a != null) {
                    this.a.sendMessage(obtain2);
                }
            }
        }
    }
}
