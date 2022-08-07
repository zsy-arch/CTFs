package com.loc;

import android.text.TextUtils;
import com.amap.api.services.geocoder.GeocodeSearch;
import com.autonavi.aps.amapapi.model.AMapLocationServer;

/* compiled from: LocFilter.java */
/* loaded from: classes2.dex */
public final class bx {
    private static bx c = null;
    private AMapLocationServer d = null;
    private long e = 0;
    private long f = 0;
    private boolean g = true;
    int a = 0;
    long b = 0;

    private bx() {
    }

    public static synchronized bx a() {
        bx bxVar;
        synchronized (bx.class) {
            if (c == null) {
                c = new bx();
            }
            bxVar = c;
        }
        return bxVar;
    }

    public static AMapLocationServer b(AMapLocationServer aMapLocationServer) {
        return aMapLocationServer;
    }

    private AMapLocationServer c(AMapLocationServer aMapLocationServer) {
        if (cx.a(aMapLocationServer)) {
            if (!this.g || !cq.b(aMapLocationServer.getTime())) {
                aMapLocationServer.setLocationType(this.a);
            } else if (aMapLocationServer.getLocationType() == 5 || aMapLocationServer.getLocationType() == 6) {
                aMapLocationServer.setLocationType(2);
            }
        }
        return aMapLocationServer;
    }

    public final AMapLocationServer a(AMapLocationServer aMapLocationServer) {
        if (cx.b() - this.b > 30000) {
            this.d = aMapLocationServer;
            this.b = cx.b();
            return this.d;
        }
        this.b = cx.b();
        if (!cx.a(this.d) || !cx.a(aMapLocationServer)) {
            this.e = cx.b();
            this.d = aMapLocationServer;
            return this.d;
        } else if (aMapLocationServer.getTime() == this.d.getTime() && aMapLocationServer.getAccuracy() < 300.0f) {
            return aMapLocationServer;
        } else {
            if (aMapLocationServer.getProvider().equals(GeocodeSearch.GPS)) {
                this.e = cx.b();
                this.d = aMapLocationServer;
                return this.d;
            } else if (aMapLocationServer.b() != this.d.b()) {
                this.e = cx.b();
                this.d = aMapLocationServer;
                return this.d;
            } else if (aMapLocationServer.getBuildingId().equals(this.d.getBuildingId()) || TextUtils.isEmpty(aMapLocationServer.getBuildingId())) {
                this.a = aMapLocationServer.getLocationType();
                float a = cx.a(aMapLocationServer, this.d);
                float accuracy = this.d.getAccuracy();
                float accuracy2 = aMapLocationServer.getAccuracy();
                float f = accuracy2 - accuracy;
                long b = cx.b();
                long j = b - this.e;
                if ((accuracy < 101.0f && accuracy2 > 299.0f) || (accuracy > 299.0f && accuracy2 > 299.0f)) {
                    if (this.f == 0) {
                        this.f = b;
                    } else if (b - this.f > 30000) {
                        this.e = b;
                        this.d = aMapLocationServer;
                        this.f = 0L;
                        return this.d;
                    }
                    this.d = c(this.d);
                    return this.d;
                } else if (accuracy2 >= 100.0f || accuracy <= 299.0f) {
                    if (accuracy2 <= 299.0f) {
                        this.f = 0L;
                    }
                    if (a >= 10.0f || a <= 0.1d || accuracy2 <= 5.0f) {
                        if (f < 300.0f) {
                            this.e = cx.b();
                            this.d = aMapLocationServer;
                            return this.d;
                        } else if (j >= 30000) {
                            this.e = cx.b();
                            this.d = aMapLocationServer;
                            return this.d;
                        } else {
                            this.d = c(this.d);
                            return this.d;
                        }
                    } else if (f >= -300.0f) {
                        this.d = c(this.d);
                        return this.d;
                    } else if (accuracy / accuracy2 >= 2.0f) {
                        this.e = b;
                        this.d = aMapLocationServer;
                        return this.d;
                    } else {
                        this.d = c(this.d);
                        return this.d;
                    }
                } else {
                    this.e = b;
                    this.d = aMapLocationServer;
                    this.f = 0L;
                    return this.d;
                }
            } else {
                this.e = cx.b();
                this.d = aMapLocationServer;
                return this.d;
            }
        }
    }

    public final void a(boolean z) {
        this.g = z;
    }

    public final synchronized void b() {
        this.d = null;
        this.e = 0L;
        this.f = 0L;
    }
}
