package com.amap.api.col;

import android.text.TextUtils;
import com.alimama.mobile.csdk.umupdate.a.f;
import com.amap.api.services.geocoder.GeocodeSearch;
import com.autonavi.amap.mapcore.Inner_3dMap_location;
import com.tencent.open.SocialConstants;
import org.json.JSONObject;

/* compiled from: AMapLocationModel.java */
/* loaded from: classes.dex */
public final class it extends Inner_3dMap_location {
    private int d;
    private String b = null;
    private String c = "";
    private String e = "";
    private String f = f.bf;
    private JSONObject g = null;
    private String h = "";
    boolean a = true;
    private String i = "";
    private long j = 0;
    private String k = null;

    public it(String str) {
        super(str);
    }

    public final String a() {
        return this.b;
    }

    public final void a(String str) {
        this.b = str;
    }

    public final String b() {
        return this.c;
    }

    public final void b(String str) {
        this.c = str;
    }

    public final int c() {
        return this.d;
    }

    public final void c(String str) {
        if (!TextUtils.isEmpty(str)) {
            if (getProvider().equals(GeocodeSearch.GPS)) {
                this.d = 0;
                return;
            } else if (str.equals("0")) {
                this.d = 0;
                return;
            } else if (str.equals("1")) {
                this.d = 1;
                return;
            }
        }
        this.d = -1;
    }

    public final String d() {
        return this.e;
    }

    public final void d(String str) {
        this.e = str;
    }

    public final JSONObject e() {
        return this.g;
    }

    public final void e(String str) {
        this.desc = str;
    }

    @Override // com.autonavi.amap.mapcore.Inner_3dMap_location
    public final void setFloor(String str) {
        if (!TextUtils.isEmpty(str)) {
            str = str.replace("F", "");
            try {
                Integer.parseInt(str);
            } catch (Throwable th) {
                str = null;
                jn.a(th, "AMapLocationModel", "setFloor");
            }
        }
        this.floor = str;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    @Override // com.autonavi.amap.mapcore.Inner_3dMap_location
    public final JSONObject toJson(int i) {
        try {
            JSONObject json = super.toJson(i);
            switch (i) {
                case 1:
                    json.put("retype", this.e);
                    json.put("cens", this.i);
                    json.put("poiid", this.buildingId);
                    json.put("floor", this.floor);
                    json.put("coord", this.d);
                    json.put("mcell", this.h);
                    json.put(SocialConstants.PARAM_APP_DESC, this.desc);
                    json.put("address", getAddress());
                    if (this.g != null && jq.a(json, "offpct")) {
                        json.put("offpct", this.g.getString("offpct"));
                        break;
                    }
                    break;
                case 2:
                case 3:
                    break;
                default:
                    return json;
            }
            json.put("type", this.f);
            json.put("isReversegeo", this.a);
            return json;
        } catch (Throwable th) {
            jn.a(th, "AMapLocationModel", "toStr");
            return null;
        }
    }

    @Override // com.autonavi.amap.mapcore.Inner_3dMap_location
    public final String toStr(int i) {
        JSONObject jSONObject;
        try {
            jSONObject = super.toJson(i);
            jSONObject.put("nb", this.k);
        } catch (Throwable th) {
            jn.a(th, "AMapLocationModel", "toStr part2");
            jSONObject = null;
        }
        if (jSONObject == null) {
            return null;
        }
        return jSONObject.toString();
    }
}
