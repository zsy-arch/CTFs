package u.upd;

import org.json.JSONObject;

/* compiled from: URequest.java */
/* loaded from: classes2.dex */
public abstract class h {
    protected String baseUrl;
    protected static String POST = "POST";
    protected static String GET = "GET";

    public abstract String toGetUrl();

    public abstract JSONObject toJson();

    /* JADX INFO: Access modifiers changed from: protected */
    public String getHttpMethod() {
        return POST;
    }

    public h(String str) {
        this.baseUrl = str;
    }

    public void setBaseUrl(String str) {
        this.baseUrl = str;
    }

    public String getBaseUrl() {
        return this.baseUrl;
    }
}
