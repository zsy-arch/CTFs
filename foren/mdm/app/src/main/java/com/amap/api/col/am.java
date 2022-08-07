package com.amap.api.col;

import android.content.Context;
import com.amap.api.col.gb;
import com.amap.api.maps.AMapException;
import com.umeng.update.UpdateConfig;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

/* compiled from: OfflineInitHandler.java */
/* loaded from: classes.dex */
public class am extends bl<String, al> {
    private final String d = UpdateConfig.a;
    private final String e = "1";
    private final String f = "0";
    private final String g = "version";

    public am(Context context, String str) {
        super(context, str);
    }

    @Override // com.amap.api.col.bl
    protected JSONObject a(gb.a aVar) {
        return aVar.f;
    }

    @Override // com.amap.api.col.bl
    protected String a() {
        return "014";
    }

    @Override // com.amap.api.col.bl
    protected Map<String, String> b() {
        HashMap hashMap = new HashMap();
        hashMap.put("mapver", this.a);
        return hashMap;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: a */
    public al b(JSONObject jSONObject) throws AMapException {
        al alVar = new al();
        try {
            String optString = jSONObject.optString(UpdateConfig.a, "");
            if (optString.equals("0")) {
                alVar.a(false);
            } else if (optString.equals("1")) {
                alVar.a(true);
            }
            alVar.a(jSONObject.optString("version", ""));
        } catch (Throwable th) {
            gr.b(th, "OfflineInitHandler", "loadData parseJson");
        }
        return alVar;
    }
}
