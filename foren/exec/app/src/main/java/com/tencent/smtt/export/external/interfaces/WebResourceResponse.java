package com.tencent.smtt.export.external.interfaces;

import java.io.InputStream;
import java.util.Map;

/* loaded from: classes.dex */
public class WebResourceResponse {
    public String mEncoding;
    public InputStream mInputStream;
    public String mMimeType;
    public String mReasonPhrase;
    public Map<String, String> mResponseHeaders;
    public int mStatusCode;

    public WebResourceResponse() {
    }

    public WebResourceResponse(String str, String str2, int i, String str3, Map<String, String> map, InputStream inputStream) {
        this.mMimeType = str;
        this.mEncoding = str2;
        setData(inputStream);
        setStatusCodeAndReasonPhrase(i, str3);
        setResponseHeaders(map);
    }

    public WebResourceResponse(String str, String str2, InputStream inputStream) {
        this.mMimeType = str;
        this.mEncoding = str2;
        setData(inputStream);
    }

    public InputStream getData() {
        return this.mInputStream;
    }

    public String getEncoding() {
        return this.mEncoding;
    }

    public String getMimeType() {
        return this.mMimeType;
    }

    public String getReasonPhrase() {
        return this.mReasonPhrase;
    }

    public Map<String, String> getResponseHeaders() {
        return this.mResponseHeaders;
    }

    public int getStatusCode() {
        return this.mStatusCode;
    }

    public void setData(InputStream inputStream) {
        this.mInputStream = inputStream;
    }

    public void setEncoding(String str) {
        this.mEncoding = str;
    }

    public void setMimeType(String str) {
        this.mMimeType = str;
    }

    public void setResponseHeaders(Map<String, String> map) {
        this.mResponseHeaders = map;
    }

    public void setStatusCodeAndReasonPhrase(int i, String str) {
        this.mStatusCode = i;
        this.mReasonPhrase = str;
    }
}
