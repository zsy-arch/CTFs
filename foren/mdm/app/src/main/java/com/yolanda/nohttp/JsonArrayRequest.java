package com.yolanda.nohttp;

import org.json.JSONArray;
import org.json.JSONException;

/* loaded from: classes2.dex */
public class JsonArrayRequest extends RestRequest<JSONArray> {
    public JsonArrayRequest(String url) {
        this(url, RequestMethod.POST);
    }

    public JsonArrayRequest(String url, RequestMethod requestMethod) {
        super(url, requestMethod);
    }

    @Override // com.yolanda.nohttp.ImplServerRequest
    public String getAccept() {
        return "application/json";
    }

    @Override // com.yolanda.nohttp.Request
    public JSONArray parseResponse(String url, Headers responseHeaders, byte[] responseBody) {
        try {
            return new JSONArray(StringRequest.parseResponseString(url, responseHeaders, responseBody));
        } catch (Exception e) {
            try {
                return new JSONArray("[]");
            } catch (JSONException e2) {
                return null;
            }
        }
    }
}
