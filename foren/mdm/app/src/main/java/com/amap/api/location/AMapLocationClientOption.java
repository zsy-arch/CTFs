package com.amap.api.location;

import com.loc.f;

/* loaded from: classes.dex */
public class AMapLocationClientOption implements Cloneable {
    private long b = 2000;
    private long c = f.c;
    private boolean d = false;
    private boolean e = false;
    private boolean f = true;
    private boolean g = true;
    private boolean h = true;
    private AMapLocationMode i = AMapLocationMode.Hight_Accuracy;
    private boolean k = false;
    private boolean l = false;
    private boolean m = true;
    private boolean n = true;
    private boolean o = false;
    private boolean p = false;
    private boolean q = true;
    private static AMapLocationProtocol j = AMapLocationProtocol.HTTP;
    static String a = "";

    /* loaded from: classes.dex */
    public enum AMapLocationMode {
        Battery_Saving,
        Device_Sensors,
        Hight_Accuracy
    }

    /* loaded from: classes.dex */
    public enum AMapLocationProtocol {
        HTTP(0),
        HTTPS(1);
        
        private int a;

        AMapLocationProtocol(int i) {
            this.a = i;
        }

        public final int getValue() {
            return this.a;
        }
    }

    public static String getAPIKEY() {
        return a;
    }

    public static void setLocationProtocol(AMapLocationProtocol aMapLocationProtocol) {
        j = aMapLocationProtocol;
    }

    public AMapLocationClientOption clone() {
        try {
            super.clone();
        } catch (Throwable th) {
            th.printStackTrace();
        }
        AMapLocationClientOption aMapLocationClientOption = new AMapLocationClientOption();
        aMapLocationClientOption.b = this.b;
        aMapLocationClientOption.d = this.d;
        aMapLocationClientOption.i = this.i;
        aMapLocationClientOption.e = this.e;
        aMapLocationClientOption.k = this.k;
        aMapLocationClientOption.l = this.l;
        aMapLocationClientOption.f = this.f;
        aMapLocationClientOption.g = this.g;
        aMapLocationClientOption.c = this.c;
        aMapLocationClientOption.m = this.m;
        aMapLocationClientOption.n = this.n;
        aMapLocationClientOption.o = this.o;
        aMapLocationClientOption.p = isSensorEnable();
        aMapLocationClientOption.q = isWifiScan();
        return aMapLocationClientOption;
    }

    public long getHttpTimeOut() {
        return this.c;
    }

    public long getInterval() {
        return this.b;
    }

    public AMapLocationMode getLocationMode() {
        return this.i;
    }

    public AMapLocationProtocol getLocationProtocol() {
        return j;
    }

    public boolean isGpsFirst() {
        return this.l;
    }

    public boolean isKillProcess() {
        return this.k;
    }

    public boolean isLocationCacheEnable() {
        return this.n;
    }

    public boolean isMockEnable() {
        return this.e;
    }

    public boolean isNeedAddress() {
        return this.f;
    }

    public boolean isOffset() {
        return this.m;
    }

    public boolean isOnceLocation() {
        if (this.o) {
            return true;
        }
        return this.d;
    }

    public boolean isOnceLocationLatest() {
        return this.o;
    }

    public boolean isSensorEnable() {
        return this.p;
    }

    public boolean isWifiActiveScan() {
        return this.g;
    }

    public boolean isWifiScan() {
        return this.q;
    }

    public AMapLocationClientOption setGpsFirst(boolean z) {
        this.l = z;
        return this;
    }

    public void setHttpTimeOut(long j2) {
        this.c = j2;
    }

    public AMapLocationClientOption setInterval(long j2) {
        if (j2 <= 800) {
            j2 = 800;
        }
        this.b = j2;
        return this;
    }

    public AMapLocationClientOption setKillProcess(boolean z) {
        this.k = z;
        return this;
    }

    public void setLocationCacheEnable(boolean z) {
        this.n = z;
    }

    public AMapLocationClientOption setLocationMode(AMapLocationMode aMapLocationMode) {
        this.i = aMapLocationMode;
        return this;
    }

    public void setMockEnable(boolean z) {
        this.e = z;
    }

    public AMapLocationClientOption setNeedAddress(boolean z) {
        this.f = z;
        return this;
    }

    public AMapLocationClientOption setOffset(boolean z) {
        this.m = z;
        return this;
    }

    public AMapLocationClientOption setOnceLocation(boolean z) {
        this.d = z;
        return this;
    }

    public void setOnceLocationLatest(boolean z) {
        this.o = z;
    }

    public void setSensorEnable(boolean z) {
        this.p = z;
    }

    public void setWifiActiveScan(boolean z) {
        this.g = z;
        this.h = z;
    }

    public void setWifiScan(boolean z) {
        this.q = z;
        if (this.q) {
            this.g = this.h;
        } else {
            this.g = false;
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("interval:").append(String.valueOf(this.b)).append("#");
        sb.append("isOnceLocation:").append(String.valueOf(this.d)).append("#");
        sb.append("locationMode:").append(String.valueOf(this.i)).append("#");
        sb.append("isMockEnable:").append(String.valueOf(this.e)).append("#");
        sb.append("isKillProcess:").append(String.valueOf(this.k)).append("#");
        sb.append("isGpsFirst:").append(String.valueOf(this.l)).append("#");
        sb.append("isNeedAddress:").append(String.valueOf(this.f)).append("#");
        sb.append("isWifiActiveScan:").append(String.valueOf(this.g)).append("#");
        sb.append("httpTimeOut:").append(String.valueOf(this.c)).append("#");
        sb.append("isOffset:").append(String.valueOf(this.m)).append("#");
        sb.append("isLocationCacheEnable:").append(String.valueOf(this.n)).append("#");
        sb.append("isLocationCacheEnable:").append(String.valueOf(this.n)).append("#");
        sb.append("isOnceLocationLatest:").append(String.valueOf(this.o)).append("#");
        sb.append("sensorEnable:").append(String.valueOf(this.p)).append("#");
        return sb.toString();
    }
}
