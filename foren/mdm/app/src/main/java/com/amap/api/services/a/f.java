package com.amap.api.services.a;

import android.content.Context;
import com.amap.api.services.cloud.CloudItemDetail;
import com.amap.api.services.core.AMapException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: CloudSearchIdHandler.java */
/* loaded from: classes.dex */
public class f extends e<y, CloudItemDetail> {
    public f(Context context, y yVar) {
        super(context, yVar);
    }

    @Override // com.amap.api.services.a.cz
    public String g() {
        return h.b() + "/datasearch/id?";
    }

    /* renamed from: e */
    public CloudItemDetail a(String str) throws AMapException {
        if (str == null || str.equals("")) {
            return null;
        }
        try {
            return b(new JSONObject(str));
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    private CloudItemDetail b(JSONObject jSONObject) throws JSONException {
        if (jSONObject == null || !jSONObject.has("datas")) {
            return null;
        }
        JSONArray optJSONArray = jSONObject.optJSONArray("datas");
        if (optJSONArray.length() <= 0) {
            return null;
        }
        JSONObject jSONObject2 = optJSONArray.getJSONObject(0);
        CloudItemDetail a = a(jSONObject2);
        a(a, jSONObject2);
        return a;
    }

    @Override // com.amap.api.services.a.b
    protected String e() {
        StringBuilder sb = new StringBuilder();
        sb.append("key=" + aw.f(this.d));
        sb.append("&tableid=" + ((y) this.a).a);
        sb.append("&output=json");
        sb.append("&_id=" + ((y) this.a).b);
        return sb.toString();
    }
}
