package com.tencent.stat.a;

import android.content.Context;
import java.util.Map;
import java.util.Properties;
import org.json.JSONArray;
import org.json.JSONObject;
import u.aly.av;

/* loaded from: classes2.dex */
public class b extends e {
    protected c a = new c();
    private long l = -1;

    public b(Context context, int i, String str) {
        super(context, i);
        this.a.a = str;
    }

    @Override // com.tencent.stat.a.e
    public f a() {
        return f.CUSTOM;
    }

    public void a(long j) {
        this.l = j;
    }

    public void a(Properties properties) {
        if (properties != null) {
            this.a.c = (Properties) properties.clone();
        }
    }

    public void a(String[] strArr) {
        if (strArr != null) {
            this.a.b = (String[]) strArr.clone();
        }
    }

    @Override // com.tencent.stat.a.e
    public boolean a(JSONObject jSONObject) {
        Object obj;
        jSONObject.put("ei", this.a.a);
        if (this.l > 0) {
            jSONObject.put(av.aO, this.l);
        }
        if (this.a.c == null && this.a.b == null) {
            jSONObject.put("kv", new JSONObject());
        }
        if (this.a.b != null) {
            JSONArray jSONArray = new JSONArray();
            for (String str : this.a.b) {
                jSONArray.put(str);
            }
            jSONObject.put("ar", jSONArray);
        }
        if (this.a.c == null) {
            return true;
        }
        JSONObject jSONObject2 = new JSONObject();
        try {
            for (Map.Entry entry : this.a.c.entrySet()) {
                jSONObject2.put(entry.getKey().toString(), entry.getValue().toString());
            }
            obj = jSONObject2;
        } catch (Exception e) {
            obj = new JSONObject(this.a.c);
        }
        jSONObject.put("kv", obj);
        return true;
    }
}
