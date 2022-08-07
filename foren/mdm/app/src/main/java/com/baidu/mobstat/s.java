package com.baidu.mobstat;

import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
class s {
    private String a;
    private String b;
    private String c;

    public s(String str, String str2, String str3) {
        this.a = str == null ? "" : str;
        this.b = str2 == null ? "" : str2;
        this.c = str3 == null ? "" : str3;
    }

    public JSONObject a() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("n", this.a);
            jSONObject.put("v", this.b);
            jSONObject.put("w", this.c);
            return jSONObject;
        } catch (JSONException e) {
            bb.b(e);
            return null;
        }
    }

    public String b() {
        return this.a;
    }
}
