package c.a.a;

import android.content.Context;
import android.location.Location;
import android.location.LocationManager;
import java.util.Calendar;

/* loaded from: classes.dex */
public class E {

    /* renamed from: a */
    public static E f324a;

    /* renamed from: b */
    public final Context f325b;

    /* renamed from: c */
    public final LocationManager f326c;

    /* renamed from: d */
    public final a f327d = new a();

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class a {

        /* renamed from: a */
        public boolean f328a;

        /* renamed from: b */
        public long f329b;
    }

    public E(Context context, LocationManager locationManager) {
        this.f325b = context;
        this.f326c = locationManager;
    }

    public boolean a() {
        long j;
        a aVar = this.f327d;
        boolean z = true;
        if (aVar.f329b > System.currentTimeMillis()) {
            return aVar.f328a;
        }
        Location location = null;
        Location a2 = C.a(this.f325b, "android.permission.ACCESS_COARSE_LOCATION") == 0 ? a("network") : null;
        if (C.a(this.f325b, "android.permission.ACCESS_FINE_LOCATION") == 0) {
            location = a("gps");
        }
        if (location == null || a2 == null ? location != null : location.getTime() > a2.getTime()) {
            a2 = location;
        }
        if (a2 != null) {
            a aVar2 = this.f327d;
            long currentTimeMillis = System.currentTimeMillis();
            if (D.f320a == null) {
                D.f320a = new D();
            }
            D d2 = D.f320a;
            d2.a(currentTimeMillis - 86400000, a2.getLatitude(), a2.getLongitude());
            long j2 = d2.f321b;
            d2.a(currentTimeMillis, a2.getLatitude(), a2.getLongitude());
            if (d2.f323d != 1) {
                z = false;
            }
            long j3 = d2.f322c;
            long j4 = d2.f321b;
            d2.a(currentTimeMillis + 86400000, a2.getLatitude(), a2.getLongitude());
            long j5 = d2.f322c;
            if (j3 == -1 || j4 == -1) {
                j = currentTimeMillis + 43200000;
            } else {
                j = (currentTimeMillis > j4 ? j5 + 0 : currentTimeMillis > j3 ? j4 + 0 : j3 + 0) + 60000;
            }
            aVar2.f328a = z;
            aVar2.f329b = j;
            return aVar.f328a;
        }
        int i = Calendar.getInstance().get(11);
        return i < 6 || i >= 22;
    }

    public final Location a(String str) {
        try {
            if (this.f326c.isProviderEnabled(str)) {
                return this.f326c.getLastKnownLocation(str);
            }
            return null;
        } catch (Exception unused) {
            return null;
        }
    }
}
