package com.umeng.update.net;

import org.json.JSONObject;

/* compiled from: ReportResponse.java */
/* loaded from: classes2.dex */
public class i extends u.upd.i {
    public a a;

    /* compiled from: ReportResponse.java */
    /* loaded from: classes2.dex */
    public enum a {
        SUCCESS,
        FAIL
    }

    public i(JSONObject jSONObject) {
        super(jSONObject);
        if ("ok".equalsIgnoreCase(jSONObject.optString("status")) || "ok".equalsIgnoreCase(jSONObject.optString("success"))) {
            this.a = a.SUCCESS;
        } else {
            this.a = a.FAIL;
        }
    }
}
