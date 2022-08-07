package com.amap.api.services.a;

import android.content.Context;
import com.amap.api.services.core.AMapException;
import com.amap.api.services.geocoder.RegeocodeAddress;
import com.amap.api.services.geocoder.RegeocodeQuery;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: ReverseGeocodingHandler.java */
/* loaded from: classes.dex */
public class aa extends b<RegeocodeQuery, RegeocodeAddress> {
    public aa(Context context, RegeocodeQuery regeocodeQuery) {
        super(context, regeocodeQuery);
    }

    @Override // com.amap.api.services.a.cz
    public String g() {
        return h.a() + "/geocode/regeo?";
    }

    /* renamed from: d */
    public RegeocodeAddress a(String str) throws AMapException {
        RegeocodeAddress regeocodeAddress = new RegeocodeAddress();
        try {
            JSONObject optJSONObject = new JSONObject(str).optJSONObject("regeocode");
            if (optJSONObject != null) {
                regeocodeAddress.setFormatAddress(n.a(optJSONObject, "formatted_address"));
                JSONObject optJSONObject2 = optJSONObject.optJSONObject("addressComponent");
                if (optJSONObject2 != null) {
                    n.a(optJSONObject2, regeocodeAddress);
                }
                regeocodeAddress.setPois(n.c(optJSONObject));
                JSONArray optJSONArray = optJSONObject.optJSONArray("roads");
                if (optJSONArray != null) {
                    n.b(optJSONArray, regeocodeAddress);
                }
                JSONArray optJSONArray2 = optJSONObject.optJSONArray("roadinters");
                if (optJSONArray2 != null) {
                    n.a(optJSONArray2, regeocodeAddress);
                }
                JSONArray optJSONArray3 = optJSONObject.optJSONArray("aois");
                if (optJSONArray3 != null) {
                    n.c(optJSONArray3, regeocodeAddress);
                }
            }
        } catch (JSONException e) {
            i.a(e, "ReverseGeocodingHandler", "paseJSON");
        }
        return regeocodeAddress;
    }

    @Override // com.amap.api.services.a.b
    protected String e() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("output=json").append("&extensions=all").append("&location=").append(((RegeocodeQuery) this.a).getPoint().getLongitude()).append(",").append(((RegeocodeQuery) this.a).getPoint().getLatitude());
        stringBuffer.append("&radius=").append(((RegeocodeQuery) this.a).getRadius());
        stringBuffer.append("&coordsys=").append(((RegeocodeQuery) this.a).getLatLonType());
        stringBuffer.append("&key=" + aw.f(this.d));
        stringBuffer.append("&language=").append(h.c());
        return stringBuffer.toString();
    }
}
