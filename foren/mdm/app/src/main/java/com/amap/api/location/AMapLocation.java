package com.amap.api.location;

import android.location.Location;
import com.alimama.mobile.csdk.umupdate.a.f;
import com.amap.api.maps.model.MyLocationStyle;
import com.amap.api.services.district.DistrictSearchQuery;
import com.loc.cx;
import com.tencent.open.SocialConstants;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class AMapLocation extends Location {
    public static final int ERROR_CODE_FAILURE_AUTH = 7;
    public static final int ERROR_CODE_FAILURE_CELL = 11;
    public static final int ERROR_CODE_FAILURE_CONNECTION = 4;
    public static final int ERROR_CODE_FAILURE_INIT = 9;
    public static final int ERROR_CODE_FAILURE_LOCATION = 6;
    public static final int ERROR_CODE_FAILURE_LOCATION_PARAMETER = 3;
    public static final int ERROR_CODE_FAILURE_LOCATION_PERMISSION = 12;
    public static final int ERROR_CODE_FAILURE_NOENOUGHSATELLITES = 14;
    public static final int ERROR_CODE_FAILURE_NOWIFIANDAP = 13;
    public static final int ERROR_CODE_FAILURE_PARSER = 5;
    public static final int ERROR_CODE_FAILURE_SIMULATION_LOCATION = 15;
    public static final int ERROR_CODE_FAILURE_WIFI_INFO = 2;
    public static final int ERROR_CODE_INVALID_PARAMETER = 1;
    public static final int ERROR_CODE_SERVICE_FAIL = 10;
    public static final int ERROR_CODE_UNKNOWN = 8;
    public static final int GPS_ACCURACY_BAD = 0;
    public static final int GPS_ACCURACY_GOOD = 1;
    public static final int GPS_ACCURACY_UNKNOWN = -1;
    public static final int LOCATION_SUCCESS = 0;
    public static final int LOCATION_TYPE_AMAP = 7;
    public static final int LOCATION_TYPE_CELL = 6;
    public static final int LOCATION_TYPE_FAST = 3;
    public static final int LOCATION_TYPE_FIX_CACHE = 4;
    public static final int LOCATION_TYPE_GPS = 1;
    public static final int LOCATION_TYPE_OFFLINE = 8;
    public static final int LOCATION_TYPE_SAME_REQ = 2;
    public static final int LOCATION_TYPE_WIFI = 5;
    protected String a;
    protected String b;
    protected String c;
    private String d;
    private String e;
    private String f;
    private String g;
    private String h;
    private String i;
    private String j;
    private String k;
    private String l;
    private String m;
    private String n;
    private boolean o;
    private int p;
    private String q;
    private String r;
    private int s;
    private double t;

    /* renamed from: u */
    private double f24u;
    private int v;
    private String w;
    private int x;

    public AMapLocation(Location location) {
        super(location);
        this.d = "";
        this.e = "";
        this.f = "";
        this.g = "";
        this.h = "";
        this.i = "";
        this.j = "";
        this.k = "";
        this.l = "";
        this.m = "";
        this.n = "";
        this.o = true;
        this.p = 0;
        this.q = "success";
        this.r = "";
        this.s = 0;
        this.t = 0.0d;
        this.f24u = 0.0d;
        this.v = 0;
        this.w = "";
        this.x = -1;
        this.a = "";
        this.b = "";
        this.c = "";
        this.t = location.getLatitude();
        this.f24u = location.getLongitude();
    }

    public AMapLocation(String str) {
        super(str);
        this.d = "";
        this.e = "";
        this.f = "";
        this.g = "";
        this.h = "";
        this.i = "";
        this.j = "";
        this.k = "";
        this.l = "";
        this.m = "";
        this.n = "";
        this.o = true;
        this.p = 0;
        this.q = "success";
        this.r = "";
        this.s = 0;
        this.t = 0.0d;
        this.f24u = 0.0d;
        this.v = 0;
        this.w = "";
        this.x = -1;
        this.a = "";
        this.b = "";
        this.c = "";
    }

    public JSONObject a(int i) {
        try {
            JSONObject jSONObject = new JSONObject();
            switch (i) {
                case 1:
                    try {
                        jSONObject.put("altitude", getAltitude());
                        jSONObject.put("speed", getSpeed());
                        jSONObject.put("bearing", getBearing());
                    } catch (Throwable th) {
                    }
                    jSONObject.put("citycode", this.g);
                    jSONObject.put(SocialConstants.PARAM_APP_DESC, this.c);
                    jSONObject.put("adcode", this.h);
                    jSONObject.put("country", this.k);
                    jSONObject.put(DistrictSearchQuery.KEYWORDS_PROVINCE, this.d);
                    jSONObject.put(DistrictSearchQuery.KEYWORDS_CITY, this.e);
                    jSONObject.put(DistrictSearchQuery.KEYWORDS_DISTRICT, this.f);
                    jSONObject.put("road", this.l);
                    jSONObject.put("street", this.m);
                    jSONObject.put("number", this.n);
                    jSONObject.put("poiname", this.j);
                    jSONObject.put(MyLocationStyle.ERROR_CODE, this.p);
                    jSONObject.put(MyLocationStyle.ERROR_INFO, this.q);
                    jSONObject.put(MyLocationStyle.LOCATION_TYPE, this.s);
                    jSONObject.put("locationDetail", this.r);
                    jSONObject.put("aoiname", this.w);
                    jSONObject.put("address", this.i);
                    jSONObject.put("poiid", this.a);
                    jSONObject.put("floor", this.b);
                case 2:
                    jSONObject.put(f.az, getTime());
                    break;
                case 3:
                    break;
                default:
                    return jSONObject;
            }
            jSONObject.put("provider", getProvider());
            jSONObject.put("lon", getLongitude());
            jSONObject.put("lat", getLatitude());
            jSONObject.put("accuracy", getAccuracy());
            jSONObject.put("isOffset", this.o);
            return jSONObject;
        } catch (Throwable th2) {
            com.loc.f.a(th2, "AmapLoc", "toStr");
            return null;
        }
    }

    @Override // android.location.Location
    public float getAccuracy() {
        return super.getAccuracy();
    }

    public String getAdCode() {
        return this.h;
    }

    public String getAddress() {
        return this.i;
    }

    @Override // android.location.Location
    public double getAltitude() {
        return super.getAltitude();
    }

    public String getAoiName() {
        return this.w;
    }

    @Override // android.location.Location
    public float getBearing() {
        return super.getBearing();
    }

    public String getBuildingId() {
        return this.a;
    }

    public String getCity() {
        return this.e;
    }

    public String getCityCode() {
        return this.g;
    }

    public String getCountry() {
        return this.k;
    }

    public String getDistrict() {
        return this.f;
    }

    public int getErrorCode() {
        return this.p;
    }

    public String getErrorInfo() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.q);
        if (this.p != 0) {
            sb.append(" 请到http://lbs.amap.com/api/android-location-sdk/guide/utilities/errorcode/查看错误码说明");
            sb.append(",错误详细信息:" + this.r);
        }
        this.q = sb.toString();
        return this.q;
    }

    public String getFloor() {
        return this.b;
    }

    public int getGpsAccuracyStatus() {
        return this.x;
    }

    @Override // android.location.Location
    public double getLatitude() {
        return this.t;
    }

    public String getLocationDetail() {
        return this.r;
    }

    public int getLocationType() {
        return this.s;
    }

    @Override // android.location.Location
    public double getLongitude() {
        return this.f24u;
    }

    public String getPoiName() {
        return this.j;
    }

    @Override // android.location.Location
    public String getProvider() {
        return super.getProvider();
    }

    public String getProvince() {
        return this.d;
    }

    public String getRoad() {
        return this.l;
    }

    public int getSatellites() {
        return this.v;
    }

    @Override // android.location.Location
    public float getSpeed() {
        return super.getSpeed();
    }

    public String getStreet() {
        return this.m;
    }

    public String getStreetNum() {
        return this.n;
    }

    public boolean isOffset() {
        return this.o;
    }

    public void setAdCode(String str) {
        this.h = str;
    }

    public void setAddress(String str) {
        this.i = str;
    }

    public void setAoiName(String str) {
        this.w = str;
    }

    public void setBuildingId(String str) {
        this.a = str;
    }

    public void setCity(String str) {
        this.e = str;
    }

    public void setCityCode(String str) {
        this.g = str;
    }

    public void setCountry(String str) {
        this.k = str;
    }

    public void setDistrict(String str) {
        this.f = str;
    }

    public void setErrorCode(int i) {
        if (this.p == 0) {
            this.q = cx.b(i);
            this.p = i;
        }
    }

    public void setErrorInfo(String str) {
        this.q = str;
    }

    public void setFloor(String str) {
        this.b = str;
    }

    public void setGpsAccuracyStatus(int i) {
        this.x = i;
    }

    @Override // android.location.Location
    public void setLatitude(double d) {
        this.t = d;
    }

    public void setLocationDetail(String str) {
        this.r = str;
    }

    public void setLocationType(int i) {
        this.s = i;
    }

    @Override // android.location.Location
    public void setLongitude(double d) {
        this.f24u = d;
    }

    public void setNumber(String str) {
        this.n = str;
    }

    public void setOffset(boolean z) {
        this.o = z;
    }

    public void setPoiName(String str) {
        this.j = str;
    }

    public void setProvince(String str) {
        this.d = str;
    }

    public void setRoad(String str) {
        this.l = str;
    }

    public void setSatellites(int i) {
        this.v = i;
    }

    public void setStreet(String str) {
        this.m = str;
    }

    public String toStr() {
        return toStr(1);
    }

    public String toStr(int i) {
        JSONObject jSONObject;
        try {
            jSONObject = a(i);
        } catch (Throwable th) {
            com.loc.f.a(th, "AMapLocation", "toStr part2");
            jSONObject = null;
        }
        if (jSONObject == null) {
            return null;
        }
        return jSONObject.toString();
    }

    @Override // android.location.Location
    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        try {
            stringBuffer.append("latitude=" + this.t + "#");
            stringBuffer.append("longitude=" + this.f24u + "#");
            stringBuffer.append("province=" + this.d + "#");
            stringBuffer.append("city=" + this.e + "#");
            stringBuffer.append("district=" + this.f + "#");
            stringBuffer.append("cityCode=" + this.g + "#");
            stringBuffer.append("adCode=" + this.h + "#");
            stringBuffer.append("address=" + this.i + "#");
            stringBuffer.append("country=" + this.k + "#");
            stringBuffer.append("road=" + this.l + "#");
            stringBuffer.append("poiName=" + this.j + "#");
            stringBuffer.append("street=" + this.m + "#");
            stringBuffer.append("streetNum=" + this.n + "#");
            stringBuffer.append("aoiName=" + this.w + "#");
            stringBuffer.append("poiid=" + this.a + "#");
            stringBuffer.append("floor=" + this.b + "#");
            stringBuffer.append("errorCode=" + this.p + "#");
            stringBuffer.append("errorInfo=" + this.q + "#");
            stringBuffer.append("locationDetail=" + this.r + "#");
            stringBuffer.append("locationType=" + this.s);
        } catch (Throwable th) {
        }
        return stringBuffer.toString();
    }
}
