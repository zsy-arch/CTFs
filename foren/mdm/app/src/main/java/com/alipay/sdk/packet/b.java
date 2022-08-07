package com.alipay.sdk.packet;

import android.text.TextUtils;
import org.json.JSONObject;

/* loaded from: classes.dex */
public final class b {
    String a;
    public String b;

    public b(String str, String str2) {
        this.a = str;
        this.b = str2;
    }

    private String b() {
        return this.a;
    }

    private void a(String str) {
        this.a = str;
    }

    private String c() {
        return this.b;
    }

    private void b(String str) {
        this.b = str;
    }

    public final JSONObject a() {
        JSONObject jSONObject;
        if (TextUtils.isEmpty(this.b)) {
            return null;
        }
        try {
            jSONObject = new JSONObject(this.b);
        } catch (Exception e) {
            jSONObject = null;
        }
        return jSONObject;
    }

    public final String toString() {
        return "\nenvelop:" + this.a + "\nbody:" + this.b;
    }
}
