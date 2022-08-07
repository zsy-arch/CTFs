package com.amap.api.services.a;

import android.content.Context;
import com.amap.api.services.cloud.CloudImage;
import com.amap.api.services.cloud.CloudItem;
import com.amap.api.services.cloud.CloudItemDetail;
import com.tencent.open.utils.SystemUtils;
import com.yolanda.nohttp.Headers;
import com.yolanda.nohttp.db.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: CloudHandler.java */
/* loaded from: classes.dex */
public abstract class e<T, V> extends b<T, V> {
    public e(Context context, T t) {
        super(context, t);
    }

    protected CloudItemDetail a(JSONObject jSONObject) throws JSONException {
        CloudItemDetail cloudItemDetail = new CloudItemDetail(n.a(jSONObject, Field.ID), n.b(jSONObject, "_location"), n.a(jSONObject, "_name"), n.a(jSONObject, "_address"));
        cloudItemDetail.setCreatetime(n.a(jSONObject, "_createtime"));
        cloudItemDetail.setUpdatetime(n.a(jSONObject, "_updatetime"));
        if (jSONObject.has("_distance")) {
            String optString = jSONObject.optString("_distance");
            if (!d(optString)) {
                cloudItemDetail.setDistance(Integer.parseInt(optString));
            }
        }
        ArrayList arrayList = new ArrayList();
        JSONArray optJSONArray = jSONObject.optJSONArray("_image");
        if (optJSONArray == null || optJSONArray.length() == 0) {
            cloudItemDetail.setmCloudImage(arrayList);
            return cloudItemDetail;
        }
        for (int i = 0; i < optJSONArray.length(); i++) {
            JSONObject jSONObject2 = optJSONArray.getJSONObject(i);
            arrayList.add(new CloudImage(n.a(jSONObject2, Field.ID), n.a(jSONObject2, "_preurl"), n.a(jSONObject2, "_url")));
        }
        cloudItemDetail.setmCloudImage(arrayList);
        return cloudItemDetail;
    }

    @Override // com.amap.api.services.a.b, com.amap.api.services.a.a, com.amap.api.services.a.cz
    public Map<String, String> c() {
        HashMap hashMap = new HashMap();
        hashMap.put("Content-Type", "application/x-www-form-urlencoded");
        hashMap.put(Headers.HEAD_KEY_ACCEPT_ENCODING, "gzip");
        hashMap.put("User-Agent", "AMAP SDK Android Search 5.0.0");
        hashMap.put("X-INFO", az.a(this.d, h.b(false), null, false));
        hashMap.put("platinfo", String.format("platform=Android&sdkversion=%s&product=%s", SystemUtils.QQ_VERSION_NAME_5_0_0, "cloud"));
        hashMap.put("logversion", "2.1");
        return hashMap;
    }

    protected void a(CloudItem cloudItem, JSONObject jSONObject) {
        Iterator<String> keys = jSONObject.keys();
        HashMap<String, String> hashMap = new HashMap<>();
        if (keys != null) {
            while (keys.hasNext()) {
                String next = keys.next();
                if (next != null && !next.toString().startsWith("_")) {
                    hashMap.put(next.toString(), jSONObject.optString(next.toString()));
                }
            }
            cloudItem.setCustomfield(hashMap);
        }
    }

    protected boolean d(String str) {
        return str == null || str.equals("") || str.equals("[]");
    }
}
