package com.amap.api.col;

import android.content.Context;
import com.alipay.sdk.util.j;
import com.amap.api.col.gb;
import com.amap.api.maps.AMapException;
import com.amap.api.maps.offlinemap.OfflineMapProvince;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: OfflineUpdateCityHandler.java */
/* loaded from: classes.dex */
public class ar extends bl<String, List<OfflineMapProvince>> {
    private Context d;

    public ar(Context context, String str) {
        super(context, str);
    }

    public void a(Context context) {
        this.d = context;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: a */
    public List<OfflineMapProvince> b(JSONObject jSONObject) throws AMapException {
        try {
            if (this.d != null) {
                bh.c(jSONObject.toString(), this.d);
            }
        } catch (Throwable th) {
            gr.b(th, "OfflineUpdateCityHandler", "loadData jsonInit");
            th.printStackTrace();
        }
        try {
            if (this.d != null) {
                return bh.a(jSONObject, this.d);
            }
            return null;
        } catch (JSONException e) {
            gr.b(e, "OfflineUpdateCityHandler", "loadData parseJson");
            e.printStackTrace();
            return null;
        }
    }

    @Override // com.amap.api.col.bl
    protected JSONObject a(gb.a aVar) {
        JSONObject jSONObject = aVar.e;
        if (!jSONObject.has(j.c)) {
            JSONObject jSONObject2 = new JSONObject();
            try {
                jSONObject2.put(j.c, new JSONObject().put("offlinemap_with_province_vfour", jSONObject));
                return jSONObject2;
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return jSONObject;
    }

    @Override // com.amap.api.col.bl
    protected String a() {
        return "013";
    }

    @Override // com.amap.api.col.bl
    protected Map<String, String> b() {
        HashMap hashMap = new HashMap();
        hashMap.put("mapver", this.a);
        return hashMap;
    }
}
