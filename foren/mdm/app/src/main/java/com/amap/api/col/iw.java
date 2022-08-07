package com.amap.api.col;

import android.text.TextUtils;
import com.amap.api.services.geocoder.GeocodeSearch;

/* compiled from: MapLocFilter.java */
/* loaded from: classes.dex */
public final class iw {
    private static iw c = null;
    private it d = null;
    private long e = 0;
    private long f = 0;
    int a = 0;
    long b = 0;

    private iw() {
    }

    public static synchronized iw a() {
        iw iwVar;
        synchronized (iw.class) {
            if (c == null) {
                c = new iw();
            }
            iwVar = c;
        }
        return iwVar;
    }

    public static it b(it itVar) {
        return itVar;
    }

    public final it a(it itVar) {
        if (jq.b() - this.b > 30000) {
            this.d = itVar;
            this.b = jq.b();
            return this.d;
        }
        this.b = jq.b();
        if (!jd.a(this.d) || !jd.a(itVar)) {
            this.e = jq.b();
            this.d = itVar;
            return this.d;
        } else if (itVar.getTime() == this.d.getTime() && itVar.getAccuracy() < 300.0f) {
            return itVar;
        } else {
            if (itVar.getProvider().equalsIgnoreCase(GeocodeSearch.GPS)) {
                this.e = jq.b();
                this.d = itVar;
                return this.d;
            } else if (itVar.c() != this.d.c()) {
                this.e = jq.b();
                this.d = itVar;
                return this.d;
            } else if (itVar.getBuildingId().equals(this.d.getBuildingId()) || TextUtils.isEmpty(itVar.getBuildingId())) {
                this.a = itVar.getLocationType();
                float a = jq.a(new double[]{itVar.getLatitude(), itVar.getLongitude(), this.d.getLatitude(), this.d.getLongitude()});
                float accuracy = this.d.getAccuracy();
                float accuracy2 = itVar.getAccuracy();
                float f = accuracy2 - accuracy;
                long b = jq.b();
                long j = b - this.e;
                if ((accuracy < 101.0f && accuracy2 > 299.0f) || (accuracy > 299.0f && accuracy2 > 299.0f)) {
                    if (this.f == 0) {
                        this.f = b;
                    } else if (b - this.f > 30000) {
                        this.e = b;
                        this.d = itVar;
                        this.f = 0L;
                        return this.d;
                    }
                    return this.d;
                } else if (accuracy2 >= 100.0f || accuracy <= 299.0f) {
                    if (accuracy2 <= 299.0f) {
                        this.f = 0L;
                    }
                    if (a < 10.0f && a > 0.1d && accuracy2 > 5.0f) {
                        if (f < -300.0f && accuracy / accuracy2 >= 2.0f) {
                            this.e = b;
                            this.d = itVar;
                            return this.d;
                        }
                        return this.d;
                    } else if (f < 300.0f) {
                        this.e = jq.b();
                        this.d = itVar;
                        return this.d;
                    } else if (j < 30000) {
                        return this.d;
                    } else {
                        this.e = jq.b();
                        this.d = itVar;
                        return this.d;
                    }
                } else {
                    this.e = b;
                    this.d = itVar;
                    this.f = 0L;
                    return this.d;
                }
            } else {
                this.e = jq.b();
                this.d = itVar;
                return this.d;
            }
        }
    }
}
