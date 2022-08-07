package com.umeng.update;

import android.content.Context;
import com.umeng.update.util.DeltaUpdate;
import org.json.JSONObject;
import u.upd.a;
import u.upd.b;
import u.upd.h;
import u.upd.n;

/* compiled from: UpdateRequest.java */
/* loaded from: classes2.dex */
public class d extends h {
    private final String a = d.class.getName();
    private final String b = UpdateConfig.a;
    private JSONObject c;

    public d(Context context) {
        super(null);
        this.c = a(context);
    }

    private JSONObject a(Context context) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("type", UpdateConfig.a);
            jSONObject.put("appkey", UpdateConfig.getAppkey(context));
            jSONObject.put("version_code", a.c(context));
            jSONObject.put(a.d, a.u(context));
            jSONObject.put("idmd5", n.b(a.f(context)));
            jSONObject.put("channel", UpdateConfig.getChannel(context));
            jSONObject.put(a.j, UpdateConfig.c);
            jSONObject.put("sdk_version", UpdateConfig.b);
            jSONObject.put(a.k, DeltaUpdate.b(context));
            jSONObject.put(a.l, DeltaUpdate.a() && UpdateConfig.isDeltaUpdate());
            return jSONObject;
        } catch (Exception e) {
            b.b(this.a, "exception in updateInternal", e);
            return null;
        }
    }

    @Override // u.upd.h
    public JSONObject toJson() {
        return this.c;
    }

    @Override // u.upd.h
    public String toGetUrl() {
        return this.baseUrl;
    }
}
