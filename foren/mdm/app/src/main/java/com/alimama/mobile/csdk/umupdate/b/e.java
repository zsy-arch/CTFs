package com.alimama.mobile.csdk.umupdate.b;

import com.alimama.mobile.csdk.umupdate.a.j;
import java.util.Map;
import org.json.JSONObject;
import u.upd.h;

/* compiled from: XpReqeust.java */
/* loaded from: classes.dex */
public class e extends h {
    private Map<String, Object> a;

    public e(Map<String, Object> map) {
        super("");
        this.a = map;
    }

    @Override // u.upd.h
    public JSONObject toJson() {
        return null;
    }

    @Override // u.upd.h
    public String getHttpMethod() {
        return h.GET;
    }

    @Override // u.upd.h
    public String toGetUrl() {
        return j.a(this.baseUrl, this.a).toString();
    }

    @Override // u.upd.h
    public void setBaseUrl(String str) {
        this.baseUrl = str;
    }
}
