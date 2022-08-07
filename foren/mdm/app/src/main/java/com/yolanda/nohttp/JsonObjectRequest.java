package com.yolanda.nohttp;

import com.google.gson.Gson;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public class JsonObjectRequest extends RestRequest<JSONObject> {
    public static final String ACCEPT = "application/json";

    public JsonObjectRequest(String url) {
        super(url);
    }

    public JsonObjectRequest(String url, RequestMethod requestMethod) {
        super(url, requestMethod);
    }

    @Override // com.yolanda.nohttp.ImplServerRequest
    public String getAccept() {
        return "application/json";
    }

    @Override // com.yolanda.nohttp.BasicRequest, com.yolanda.nohttp.ImplServerRequest
    public String getContentType() {
        return "application/json";
    }

    @Override // com.yolanda.nohttp.Request
    public JSONObject parseResponse(String url, Headers responseHeaders, byte[] responseBody) {
        try {
            return new JSONObject(StringRequest.parseResponseString(url, responseHeaders, responseBody));
        } catch (Exception e) {
            try {
                return new JSONObject("{}");
            } catch (JSONException jex) {
                jex.printStackTrace();
                return null;
            }
        }
    }

    @Override // com.yolanda.nohttp.BasicRequest
    protected StringBuffer buildCommonParams() {
        StringBuffer buffer = new StringBuffer();
        if (this.mParamMap != null) {
            buffer.append(new Gson().toJson(this.mParamMap));
        }
        return buffer;
    }
}
