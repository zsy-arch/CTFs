package com.amap.api.services.a;

import android.content.Context;
import com.amap.api.services.core.AMapException;
import com.amap.api.services.core.PoiItem;
import com.amap.api.services.core.ServiceSettings;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: PoiSearchIdHandler.java */
/* loaded from: classes.dex */
public class v extends u<String, PoiItem> {
    public v(Context context, String str) {
        super(context, str);
    }

    @Override // com.amap.api.services.a.cz
    public String g() {
        return h.a() + "/place/detail?";
    }

    /* renamed from: e */
    public PoiItem a(String str) throws AMapException {
        try {
            return a(new JSONObject(str));
        } catch (JSONException e) {
            i.a(e, "PoiSearchIdHandler", "paseJSONJSONException");
            return null;
        } catch (Exception e2) {
            i.a(e2, "PoiSearchIdHandler", "paseJSONException");
            return null;
        }
    }

    private PoiItem a(JSONObject jSONObject) throws JSONException {
        JSONArray optJSONArray;
        JSONObject optJSONObject;
        if (jSONObject == null || (optJSONArray = jSONObject.optJSONArray("pois")) == null || optJSONArray.length() <= 0 || (optJSONObject = optJSONArray.optJSONObject(0)) == null) {
            return null;
        }
        return n.d(optJSONObject);
    }

    @Override // com.amap.api.services.a.b
    protected String e() {
        return h();
    }

    private String h() {
        StringBuilder sb = new StringBuilder();
        sb.append("id=").append((String) this.a);
        sb.append("&output=json");
        sb.append("&extensions=all");
        sb.append("&children=1");
        sb.append("&language=").append(ServiceSettings.getInstance().getLanguage());
        sb.append("&key=" + aw.f(this.d));
        return sb.toString();
    }
}
