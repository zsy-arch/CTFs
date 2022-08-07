package com.yolanda.nohttp;

import java.net.HttpCookie;
import java.util.List;

/* loaded from: classes2.dex */
public class RestResponse<T> implements Response<T> {
    private final byte[] byteArray;
    private final Headers headers;
    private final boolean isFromCache;
    private Exception mException;
    private final long mNetworkMillis;
    private final RequestMethod method;
    private final T result;
    private final Object tag;
    private final String url;

    public RestResponse(String url, RequestMethod requestMethod, boolean isFromCache, Headers headers, byte[] byteArray, Object tag, T result, long millis, Exception exception) {
        this.url = url;
        this.method = requestMethod;
        this.isFromCache = isFromCache;
        this.headers = headers;
        this.byteArray = byteArray;
        this.tag = tag;
        this.result = result;
        this.mNetworkMillis = millis;
        this.mException = exception;
    }

    @Override // com.yolanda.nohttp.Response
    public String url() {
        return this.url;
    }

    @Override // com.yolanda.nohttp.Response
    public RequestMethod getRequestMethod() {
        return this.method;
    }

    @Override // com.yolanda.nohttp.Response
    public boolean isSucceed() {
        return this.mException == null;
    }

    @Override // com.yolanda.nohttp.Response
    public boolean isFromCache() {
        return this.isFromCache;
    }

    @Override // com.yolanda.nohttp.Response
    public Headers getHeaders() {
        return this.headers;
    }

    @Override // com.yolanda.nohttp.Response
    public List<HttpCookie> getCookies() {
        return this.headers.getCookies();
    }

    @Override // com.yolanda.nohttp.Response
    public byte[] getByteArray() {
        return this.byteArray;
    }

    @Override // com.yolanda.nohttp.Response
    public Object getTag() {
        return this.tag;
    }

    @Override // com.yolanda.nohttp.Response
    public T get() {
        return this.result;
    }

    @Override // com.yolanda.nohttp.Response
    public Exception getException() {
        return this.mException;
    }

    @Override // com.yolanda.nohttp.Response
    public long getNetworkMillis() {
        return this.mNetworkMillis;
    }

    public String toString() {
        StringBuilder builder = new StringBuilder();
        Headers headers = getHeaders();
        if (headers != null) {
            for (String key : headers.keySet()) {
                for (String value : headers.getValues(key)) {
                    if (key != null) {
                        builder.append(key).append(": ");
                    }
                    builder.append(value).append("\n");
                }
            }
        }
        T result = get();
        if (result != null) {
            builder.append(result.toString());
        }
        return builder.toString();
    }
}
