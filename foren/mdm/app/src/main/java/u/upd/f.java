package u.upd;

import org.json.JSONObject;

/* compiled from: ReportResponse.java */
/* loaded from: classes2.dex */
public class f extends i {
    public a a;

    /* compiled from: ReportResponse.java */
    /* loaded from: classes2.dex */
    public enum a {
        SUCCESS,
        FAIL
    }

    public f(JSONObject jSONObject) {
        super(jSONObject);
        if ("ok".equalsIgnoreCase(jSONObject.optString("status")) || "ok".equalsIgnoreCase(jSONObject.optString("success"))) {
            this.a = a.SUCCESS;
        } else {
            this.a = a.FAIL;
        }
    }
}
