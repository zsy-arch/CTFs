package cn.jpush.android.a;

import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import cn.jpush.android.util.ac;

/* loaded from: classes.dex */
public final class g {
    private static final String[] z;
    public double a;
    public double b;
    private Context c;
    private LocationManager d;
    private Location e;
    private String f;
    private long g;
    private final LocationListener h = new h(this);

    /* JADX WARN: Removed duplicated region for block: B:12:0x002b A[LOOP:1: B:7:0x001a->B:12:0x002b, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:15:0x0033  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x0069  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x006c  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x006f  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0072  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x002f A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:9:0x0021  */
    static {
        /*
            r4 = 3
            r3 = 2
            r2 = 1
            r1 = 0
            r0 = 5
            java.lang.String[] r6 = new java.lang.String[r0]
            java.lang.String r5 = "\\\u0017Z\u001b\u001e\u0005\u000f\u0013\u001cF\u0005YX\u001dH\u001d\u0010PVHFYBXP\u0002S\u001aX\bBTDXP\u0002S\u001aX\u000bDV\u0014@OAH"
            r0 = -1
            r7 = r6
            r8 = r6
            r6 = r1
        L_0x000d:
            char[] r5 = r5.toCharArray()
            int r9 = r5.length
            if (r9 > r2) goto L_0x0075
            r10 = r1
        L_0x0015:
            r11 = r5
            r12 = r10
            r15 = r9
            r9 = r5
            r5 = r15
        L_0x001a:
            char r14 = r9[r10]
            int r13 = r12 % 5
            switch(r13) {
                case 0: goto L_0x0069;
                case 1: goto L_0x006c;
                case 2: goto L_0x006f;
                case 3: goto L_0x0072;
                default: goto L_0x0021;
            }
        L_0x0021:
            r13 = 106(0x6a, float:1.49E-43)
        L_0x0023:
            r13 = r13 ^ r14
            char r13 = (char) r13
            r9[r10] = r13
            int r10 = r12 + 1
            if (r5 != 0) goto L_0x002f
            r9 = r11
            r12 = r10
            r10 = r5
            goto L_0x001a
        L_0x002f:
            r9 = r5
            r5 = r11
        L_0x0031:
            if (r9 > r10) goto L_0x0015
            java.lang.String r9 = new java.lang.String
            r9.<init>(r5)
            java.lang.String r5 = r9.intern()
            switch(r0) {
                case 0: goto L_0x0048;
                case 1: goto L_0x0051;
                case 2: goto L_0x005a;
                case 3: goto L_0x0064;
                default: goto L_0x003f;
            }
        L_0x003f:
            r7[r6] = r5
            java.lang.String r0 = "WTE\t\u0003QP"
            r5 = r0
            r6 = r2
            r7 = r8
            r0 = r1
            goto L_0x000d
        L_0x0048:
            r7[r6] = r5
            java.lang.String r0 = "IPB\r\u0005U^"
            r5 = r0
            r6 = r3
            r7 = r8
            r0 = r2
            goto L_0x000d
        L_0x0051:
            r7[r6] = r5
            java.lang.String r0 = "@EE"
            r5 = r0
            r6 = r4
            r7 = r8
            r0 = r3
            goto L_0x000d
        L_0x005a:
            r7[r6] = r5
            r5 = 4
            java.lang.String r0 = "KZU\u001b\u001eNZX"
            r6 = r5
            r7 = r8
            r5 = r0
            r0 = r4
            goto L_0x000d
        L_0x0064:
            r7[r6] = r5
            cn.jpush.android.a.g.z = r8
            return
        L_0x0069:
            r13 = 39
            goto L_0x0023
        L_0x006c:
            r13 = 53
            goto L_0x0023
        L_0x006f:
            r13 = 54
            goto L_0x0023
        L_0x0072:
            r13 = 122(0x7a, float:1.71E-43)
            goto L_0x0023
        L_0x0075:
            r10 = r1
            goto L_0x0031
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.jpush.android.a.g.<clinit>():void");
    }

    public g(Context context) {
        this.c = context;
        this.d = (LocationManager) this.c.getSystemService(z[4]);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(Location location) {
        if (location != null) {
            try {
                this.a = location.getLatitude();
                this.b = location.getLongitude();
                this.g = System.currentTimeMillis();
                this.f = String.format(z[0], Double.valueOf(this.a), Double.valueOf(this.b), Double.valueOf(location.getAltitude()), Float.valueOf(location.getBearing()), Float.valueOf(location.getAccuracy()));
                return;
            } catch (Exception e) {
                e.getMessage();
                ac.e();
            }
        }
        this.f = "";
    }

    public final boolean a() {
        try {
            if (this.d == null) {
                return false;
            }
            if (!this.d.isProviderEnabled(z[3]) && !this.d.isProviderEnabled(z[2])) {
                if (!this.d.isProviderEnabled(z[1])) {
                    return false;
                }
            }
            return true;
        } catch (IllegalArgumentException e) {
            ac.d();
            return false;
        } catch (SecurityException e2) {
            ac.d();
            return false;
        }
    }

    public final void b() {
        try {
            Criteria criteria = new Criteria();
            criteria.setAccuracy(1);
            criteria.setAltitudeRequired(false);
            criteria.setBearingRequired(false);
            criteria.setCostAllowed(true);
            criteria.setPowerRequirement(1);
            String bestProvider = this.d.getBestProvider(criteria, true);
            if (bestProvider != null) {
                this.e = this.d.getLastKnownLocation(bestProvider);
                if (this.e != null) {
                    a(this.e);
                }
                this.d.requestLocationUpdates(bestProvider, 2000L, 10.0f, this.h);
            }
        } catch (SecurityException e) {
            ac.d();
        } catch (Exception e2) {
            ac.d();
        }
    }

    public final void c() {
        try {
            if (this.h != null) {
                this.d.removeUpdates(this.h);
            } else {
                ac.d();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public final String d() {
        return this.f;
    }

    public final long e() {
        return this.g;
    }
}
