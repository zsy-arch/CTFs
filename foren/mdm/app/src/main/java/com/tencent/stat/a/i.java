package com.tencent.stat.a;

import android.content.Context;
import com.tencent.stat.common.k;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public class i extends e {
    private static String a = null;
    private String l;
    private String m = null;

    public i(Context context, int i) {
        super(context, i);
        this.l = null;
        this.l = k.p(context);
        if (a == null) {
            a = k.m(context);
        }
    }

    @Override // com.tencent.stat.a.e
    public f a() {
        return f.NETWORK_MONITOR;
    }

    public void a(String str) {
        this.m = str;
    }

    @Override // com.tencent.stat.a.e
    public boolean a(JSONObject jSONObject) {
        k.a(jSONObject, "op", a);
        k.a(jSONObject, "cn", this.l);
        jSONObject.put("sp", this.m);
        return true;
    }
}
