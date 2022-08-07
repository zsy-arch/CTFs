package com.alimama.mobile.csdk.umupdate.b;

import com.alimama.mobile.csdk.umupdate.a.g;
import org.json.JSONException;
import org.json.JSONObject;
import u.upd.i;

/* compiled from: XpResponse.java */
/* loaded from: classes.dex */
public class f extends i {
    public int a;
    public JSONObject b;

    public f(JSONObject jSONObject) {
        super(jSONObject);
        this.b = jSONObject;
        a(jSONObject);
    }

    private void a(JSONObject jSONObject) {
        if (jSONObject == null) {
            g.d("failed requesting", new Object[0]);
            return;
        }
        try {
            this.a = jSONObject.getInt("status");
        } catch (JSONException e) {
            g.d("Parse json error", e);
        }
    }
}
