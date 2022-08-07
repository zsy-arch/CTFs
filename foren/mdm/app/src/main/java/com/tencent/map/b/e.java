package com.tencent.map.b;

import android.content.Context;
import android.location.GpsSatellite;
import android.location.GpsStatus;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import com.amap.api.services.geocoder.GeocodeSearch;
import java.util.Iterator;

/* loaded from: classes2.dex */
public final class e {
    private static LocationManager b = null;
    private static float d = 0.0f;
    private Context a = null;
    private a c = null;
    private c e = null;
    private b f = null;
    private boolean g = false;
    private byte[] h = new byte[0];
    private int i = 1024;
    private long j = 0;
    private boolean k = false;
    private int l = 0;
    private int m = 0;

    /* loaded from: classes2.dex */
    class a implements GpsStatus.Listener, LocationListener {
        private a() {
        }

        /* synthetic */ a(e eVar, byte b) {
            this();
        }

        @Override // android.location.GpsStatus.Listener
        public final void onGpsStatusChanged(int i) {
            switch (i) {
                case 1:
                    e.a(e.this, 1);
                    break;
                case 2:
                    e.a(e.this, 0);
                    break;
                case 3:
                    e.a(e.this, 2);
                    break;
            }
            e.this.b();
        }

        @Override // android.location.LocationListener
        public final void onLocationChanged(Location location) {
            boolean z = false;
            if (location != null) {
                double latitude = location.getLatitude();
                double longitude = location.getLongitude();
                if (latitude != 29.999998211860657d && longitude != 103.99999916553497d && Math.abs(latitude) >= 1.0E-8d && Math.abs(longitude) >= 1.0E-8d && latitude >= -90.0d && latitude <= 90.0d && longitude >= -180.0d && longitude <= 180.0d) {
                    z = true;
                }
                if (z) {
                    e.this.j = System.currentTimeMillis();
                    e.this.b();
                    e.a(e.this, 2);
                    e.this.f = new b(e.this, location, e.this.l, e.this.m, e.this.i, e.this.j);
                    if (e.this.e != null) {
                        e.this.e.a(e.this.f);
                    }
                }
            }
        }

        @Override // android.location.LocationListener
        public final void onProviderDisabled(String str) {
            if (str != null) {
                try {
                    if (str.equals(GeocodeSearch.GPS)) {
                        e.this.l = e.this.m = 0;
                        e.this.i = 0;
                        if (e.this.e != null) {
                            e.this.e.a(e.this.i);
                        }
                    }
                } catch (Exception e) {
                }
            }
        }

        @Override // android.location.LocationListener
        public final void onProviderEnabled(String str) {
            if (str != null) {
                try {
                    if (str.equals(GeocodeSearch.GPS)) {
                        e.this.i = 4;
                        if (e.this.e != null) {
                            e.this.e.a(e.this.i);
                        }
                    }
                } catch (Exception e) {
                }
            }
        }

        @Override // android.location.LocationListener
        public final void onStatusChanged(String str, int i, Bundle bundle) {
        }
    }

    /* loaded from: classes2.dex */
    public class b implements Cloneable {
        private Location a;
        private long b;
        private int c;

        public b(e eVar, Location location, int i, int i2, int i3, long j) {
            this.a = null;
            this.b = 0L;
            this.c = 0;
            if (location != null) {
                this.a = new Location(location);
                this.c = i2;
                this.b = j;
            }
        }

        public final boolean a() {
            if (this.a == null) {
                return false;
            }
            return (this.c <= 0 || this.c >= 3) && System.currentTimeMillis() - this.b <= 30000;
        }

        public final Location b() {
            return this.a;
        }

        public final Object clone() {
            b bVar = null;
            try {
                bVar = (b) super.clone();
            } catch (Exception e) {
            }
            if (this.a != null) {
                bVar.a = new Location(this.a);
            }
            return bVar;
        }
    }

    /* loaded from: classes2.dex */
    public interface c {
        void a(int i);

        void a(b bVar);
    }

    static /* synthetic */ int a(e eVar, int i) {
        int i2 = eVar.i | i;
        eVar.i = i2;
        return i2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b() {
        this.m = 0;
        this.l = 0;
        GpsStatus gpsStatus = b.getGpsStatus(null);
        if (gpsStatus != null) {
            int maxSatellites = gpsStatus.getMaxSatellites();
            Iterator<GpsSatellite> it = gpsStatus.getSatellites().iterator();
            if (it != null) {
                while (it.hasNext() && this.l <= maxSatellites) {
                    this.l++;
                    if (it.next().usedInFix()) {
                        this.m++;
                    }
                }
            }
        }
    }

    public final void a() {
        synchronized (this.h) {
            if (this.g) {
                if (!(b == null || this.c == null)) {
                    b.removeGpsStatusListener(this.c);
                    b.removeUpdates(this.c);
                }
                this.g = false;
            }
        }
    }

    public final boolean a(c cVar, Context context) {
        synchronized (this.h) {
            if (this.g) {
                return true;
            }
            if (context == null || cVar == null) {
                return false;
            }
            this.a = context;
            this.e = cVar;
            try {
                b = (LocationManager) this.a.getSystemService("location");
                this.c = new a(this, (byte) 0);
                if (b != null) {
                    if (this.c != null) {
                        try {
                            b.requestLocationUpdates(GeocodeSearch.GPS, 1000L, 0.0f, this.c);
                            b.addGpsStatusListener(this.c);
                            if (b.isProviderEnabled(GeocodeSearch.GPS)) {
                                this.i = 4;
                            } else {
                                this.i = 0;
                            }
                            this.g = true;
                            return this.g;
                        } catch (Exception e) {
                            return false;
                        }
                    }
                }
                return false;
            } catch (Exception e2) {
                return false;
            }
        }
    }
}
