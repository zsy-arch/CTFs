package com.tencent.stat.a;

import android.content.Context;
import com.tencent.stat.common.a;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public class k extends e {
    private a a;
    private JSONObject l;

    public k(Context context, int i, JSONObject jSONObject) {
        super(context, i);
        this.l = null;
        this.a = new a(context);
        this.l = jSONObject;
    }

    @Override // com.tencent.stat.a.e
    public f a() {
        return f.SESSION_ENV;
    }

    @Override // com.tencent.stat.a.e
    public boolean a(JSONObject jSONObject) {
        if (this.e != null) {
            jSONObject.put("ut", this.e.getUserType());
        }
        if (this.l != null) {
            jSONObject.put("cfg", this.l);
        }
        this.a.a(jSONObject);
        return true;
    }
}
