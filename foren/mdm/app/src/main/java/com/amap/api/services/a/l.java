package com.amap.api.services.a;

import android.content.Context;
import com.amap.api.services.core.AMapException;
import com.amap.api.services.geocoder.GeocodeAddress;
import com.amap.api.services.geocoder.GeocodeQuery;
import java.util.ArrayList;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: GeocodingHandler.java */
/* loaded from: classes.dex */
public class l extends b<GeocodeQuery, ArrayList<GeocodeAddress>> {
    public l(Context context, GeocodeQuery geocodeQuery) {
        super(context, geocodeQuery);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: d */
    public ArrayList<GeocodeAddress> a(String str) throws AMapException {
        ArrayList<GeocodeAddress> arrayList = new ArrayList<>();
        try {
            JSONObject jSONObject = new JSONObject(str);
            if (jSONObject.has("count") && jSONObject.getInt("count") > 0) {
                return n.l(jSONObject);
            }
            return arrayList;
        } catch (JSONException e) {
            i.a(e, "GeocodingHandler", "paseJSONJSONException");
            return arrayList;
        } catch (Exception e2) {
            i.a(e2, "GeocodingHandler", "paseJSONException");
            return arrayList;
        }
    }

    @Override // com.amap.api.services.a.cz
    public String g() {
        return h.a() + "/geocode/geo?";
    }

    @Override // com.amap.api.services.a.b
    protected String e() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("output=json").append("&address=").append(b(((GeocodeQuery) this.a).getLocationName()));
        String city = ((GeocodeQuery) this.a).getCity();
        if (!n.i(city)) {
            stringBuffer.append("&city=").append(b(city));
        }
        stringBuffer.append("&key=" + aw.f(this.d));
        stringBuffer.append("&language=").append(h.c());
        return stringBuffer.toString();
    }
}
