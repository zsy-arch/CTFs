package com.yolanda.nohttp;

import android.os.SystemClock;

/* loaded from: classes2.dex */
public class HttpRestParser implements ImplRestParser {
    private static HttpRestParser _INSTANCE;
    private final ImplRestExecutor mImplRestExecutor;

    public static HttpRestParser getInstance(ImplRestExecutor implRestExecutor) {
        if (_INSTANCE == null) {
            _INSTANCE = new HttpRestParser(implRestExecutor);
        }
        return _INSTANCE;
    }

    private HttpRestParser(ImplRestExecutor mImplRestExecutor) {
        this.mImplRestExecutor = mImplRestExecutor;
    }

    @Override // com.yolanda.nohttp.ImplRestParser
    public <T> Response<T> parserRequest(Request<T> request) {
        long startTime = SystemClock.elapsedRealtime();
        HttpResponse httpResponse = this.mImplRestExecutor.executeRequest(request);
        String url = request.url();
        boolean isFromCache = httpResponse.isFromCache;
        Headers responseHeaders = httpResponse.responseHeaders;
        Exception exception = httpResponse.exception;
        byte[] responseBody = httpResponse.responseBody;
        if (exception != null) {
            return new RestResponse(url, request.getRequestMethod(), isFromCache, responseHeaders, responseBody, request.getTag(), null, SystemClock.elapsedRealtime() - startTime, exception);
        }
        return new RestResponse(url, request.getRequestMethod(), isFromCache, responseHeaders, responseBody, request.getTag(), request.parseResponse(url, responseHeaders, responseBody), SystemClock.elapsedRealtime() - startTime, exception);
    }
}
