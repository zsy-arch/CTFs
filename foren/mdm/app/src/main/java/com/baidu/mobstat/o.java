package com.baidu.mobstat;

import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
class o {
    private String a;
    private String b;
    private String c;
    private String d;

    public o(String str, String str2, String str3, String str4) {
        str = str == null ? "" : str;
        str2 = str2 == null ? "" : str2;
        str3 = str3 == null ? "" : str3;
        str4 = str4 == null ? "" : str4;
        this.a = str;
        this.b = str2;
        this.c = str3;
        this.d = str4;
    }

    public JSONObject a() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("n", this.a);
            jSONObject.put("v", this.b);
            jSONObject.put("c", this.c);
            jSONObject.put("a", this.d);
            return jSONObject;
        } catch (JSONException e) {
            bb.b(e);
            return null;
        }
    }
}
