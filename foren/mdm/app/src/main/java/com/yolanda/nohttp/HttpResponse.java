package com.yolanda.nohttp;

/* loaded from: classes2.dex */
public class HttpResponse {
    public final Exception exception;
    public final boolean isFromCache;
    public final byte[] responseBody;
    public final Headers responseHeaders;

    public HttpResponse(boolean isFromCache, Headers responseHeaders, byte[] responseBody, Exception exception) {
        this.isFromCache = isFromCache;
        this.responseHeaders = responseHeaders;
        this.responseBody = responseBody;
        this.exception = exception;
    }
}
