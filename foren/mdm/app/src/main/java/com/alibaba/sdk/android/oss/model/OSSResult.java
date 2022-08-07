package com.alibaba.sdk.android.oss.model;

import java.util.Map;

/* loaded from: classes.dex */
public class OSSResult {
    private String requestId;
    private Map<String, String> responseHeader;
    private int statusCode;

    public int getStatusCode() {
        return this.statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public Map<String, String> getResponseHeader() {
        return this.responseHeader;
    }

    public void setResponseHeader(Map<String, String> responseHeader) {
        this.responseHeader = responseHeader;
    }

    public String getRequestId() {
        return this.requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }
}
