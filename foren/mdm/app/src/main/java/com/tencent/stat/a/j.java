package com.tencent.stat.a;

import android.content.Context;
import com.tencent.stat.common.k;
import org.json.JSONObject;
import u.aly.av;

/* loaded from: classes2.dex */
public class j extends e {
    Long a;
    String l;
    String m;

    public j(Context context, String str, String str2, int i, Long l) {
        super(context, i);
        this.a = null;
        this.m = str;
        this.l = str2;
        this.a = l;
    }

    @Override // com.tencent.stat.a.e
    public f a() {
        return f.PAGE_VIEW;
    }

    @Override // com.tencent.stat.a.e
    public boolean a(JSONObject jSONObject) {
        k.a(jSONObject, "pi", this.l);
        k.a(jSONObject, "rf", this.m);
        if (this.a == null) {
            return true;
        }
        jSONObject.put(av.aO, this.a);
        return true;
    }
}
