package com.autonavi.aps.amapapi.model;

import android.text.TextUtils;
import com.alimama.mobile.csdk.umupdate.a.f;
import com.amap.api.location.AMapLocation;
import com.amap.api.services.geocoder.GeocodeSearch;
import com.loc.cx;
import com.tencent.open.SocialConstants;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class AMapLocationServer extends AMapLocation {
    public static String d = null;
    private int g;
    private String f = "";
    private String h = "";
    private String i = f.bf;
    private JSONObject j = null;
    private String k = "";
    boolean e = true;
    private String l = "";
    private long m = 0;
    private String n = null;

    public AMapLocationServer(String str) {
        super(str);
    }

    public final String a() {
        return this.f;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    @Override // com.amap.api.location.AMapLocation
    public final JSONObject a(int i) {
        try {
            JSONObject a = super.a(i);
            switch (i) {
                case 1:
                    a.put("retype", this.h);
                    a.put("cens", this.l);
                    a.put("poiid", this.a);
                    a.put("floor", this.b);
                    a.put("coord", this.g);
                    a.put("mcell", this.k);
                    a.put(SocialConstants.PARAM_APP_DESC, this.c);
                    a.put("address", getAddress());
                    if (this.j != null && cx.a(a, "offpct")) {
                        a.put("offpct", this.j.getString("offpct"));
                        break;
                    }
                    break;
                case 2:
                case 3:
                    break;
                default:
                    return a;
            }
            a.put("type", this.i);
            a.put("isReversegeo", this.e);
            return a;
        } catch (Throwable th) {
            com.loc.f.a(th, "AmapLoc", "toStr");
            return null;
        }
    }

    public final void a(long j) {
        this.m = j;
    }

    public final void a(String str) {
        this.f = str;
    }

    public final void a(JSONObject jSONObject) {
        this.j = jSONObject;
    }

    public final void a(boolean z) {
        this.e = z;
    }

    public final int b() {
        return this.g;
    }

    public final void b(String str) {
        if (!TextUtils.isEmpty(str)) {
            if (getProvider().equals(GeocodeSearch.GPS)) {
                this.g = 0;
                return;
            } else if (str.equals("0")) {
                this.g = 0;
                return;
            } else if (str.equals("1")) {
                this.g = 1;
                return;
            }
        }
        this.g = -1;
    }

    public final void b(JSONObject jSONObject) {
        int i = 0;
        if (jSONObject != null) {
            try {
                com.loc.f.a(this, jSONObject);
                if (cx.a(jSONObject, "type")) {
                    this.i = jSONObject.getString("type");
                }
                if (cx.a(jSONObject, "retype")) {
                    this.h = jSONObject.getString("retype");
                }
                if (cx.a(jSONObject, "cens")) {
                    String string = jSONObject.getString("cens");
                    if (!TextUtils.isEmpty(string)) {
                        String[] split = string.split("\\*");
                        int length = split.length;
                        while (true) {
                            if (i >= length) {
                                break;
                            }
                            String str = split[i];
                            if (!TextUtils.isEmpty(str)) {
                                String[] split2 = str.split(",");
                                setLongitude(Double.parseDouble(split2[0]));
                                setLatitude(Double.parseDouble(split2[1]));
                                setAccuracy(Integer.parseInt(split2[2]));
                                break;
                            }
                            i++;
                        }
                        this.l = string;
                    }
                }
                if (cx.a(jSONObject, SocialConstants.PARAM_APP_DESC)) {
                    this.c = jSONObject.getString(SocialConstants.PARAM_APP_DESC);
                }
                if (cx.a(jSONObject, "poiid")) {
                    setBuildingId(jSONObject.getString("poiid"));
                }
                if (cx.a(jSONObject, "pid")) {
                    setBuildingId(jSONObject.getString("pid"));
                }
                if (cx.a(jSONObject, "floor")) {
                    setFloor(jSONObject.getString("floor"));
                }
                if (cx.a(jSONObject, "flr")) {
                    setFloor(jSONObject.getString("flr"));
                }
                if (cx.a(jSONObject, "coord")) {
                    b(jSONObject.getString("coord"));
                }
                if (cx.a(jSONObject, "mcell")) {
                    this.k = jSONObject.getString("mcell");
                }
                if (cx.a(jSONObject, "isReversegeo")) {
                    this.e = jSONObject.getBoolean("isReversegeo");
                }
            } catch (Throwable th) {
                com.loc.f.a(th, "AmapLoc", "AmapLoc");
            }
        }
    }

    public final String c() {
        return this.h;
    }

    public final void c(String str) {
        this.h = str;
    }

    public final String d() {
        return this.i;
    }

    public final void d(String str) {
        this.i = str;
    }

    public final JSONObject e() {
        return this.j;
    }

    public final void e(String str) {
        this.c = str;
    }

    public final String f() {
        return this.k;
    }

    public final void f(String str) {
        this.n = str;
    }

    public final AMapLocationServer g() {
        String str = this.k;
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        String[] split = str.split(",");
        if (split.length != 3) {
            return null;
        }
        AMapLocationServer aMapLocationServer = new AMapLocationServer("");
        aMapLocationServer.setProvider(getProvider());
        aMapLocationServer.setLongitude(Double.parseDouble(split[0]));
        aMapLocationServer.setLatitude(Double.parseDouble(split[1]));
        aMapLocationServer.setAccuracy(Float.parseFloat(split[2]));
        aMapLocationServer.setCityCode(getCityCode());
        aMapLocationServer.setAdCode(getAdCode());
        aMapLocationServer.setCountry(getCountry());
        aMapLocationServer.setProvince(getProvince());
        aMapLocationServer.setCity(getCity());
        aMapLocationServer.setTime(getTime());
        aMapLocationServer.i = this.i;
        aMapLocationServer.b(String.valueOf(this.g));
        if (cx.a(aMapLocationServer)) {
            return aMapLocationServer;
        }
        return null;
    }

    public final boolean h() {
        return this.e;
    }

    public final long i() {
        return this.m;
    }

    public final String j() {
        return this.n;
    }

    @Override // com.amap.api.location.AMapLocation
    public void setFloor(String str) {
        if (!TextUtils.isEmpty(str)) {
            str = str.replace("F", "");
            try {
                Integer.parseInt(str);
            } catch (Throwable th) {
                str = null;
                com.loc.f.a(th, "AmapLoc", "setFloor");
            }
        }
        this.b = str;
    }

    @Override // com.amap.api.location.AMapLocation
    public String toStr(int i) {
        JSONObject jSONObject;
        try {
            jSONObject = super.a(i);
            jSONObject.put("nb", this.n);
        } catch (Throwable th) {
            com.loc.f.a(th, "AMapLocation", "toStr part2");
            jSONObject = null;
        }
        if (jSONObject == null) {
            return null;
        }
        return jSONObject.toString();
    }
}
