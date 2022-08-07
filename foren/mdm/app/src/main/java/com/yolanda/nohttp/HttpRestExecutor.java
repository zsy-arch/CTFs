package com.yolanda.nohttp;

import android.text.TextUtils;
import com.yolanda.nohttp.cache.Cache;
import com.yolanda.nohttp.cache.CacheEntity;
import com.yolanda.nohttp.cache.CacheMode;
import com.yolanda.nohttp.error.NotFoundCacheError;
import com.yolanda.nohttp.error.ServerError;
import com.yolanda.nohttp.tools.HeaderParser;
import com.yolanda.nohttp.tools.HttpDateTime;

/* loaded from: classes2.dex */
public class HttpRestExecutor implements ImplRestExecutor {
    private static HttpRestExecutor _INSTANCE;
    private Cache<CacheEntity> mCache;
    private ImplRestConnection mConnection;

    private HttpRestExecutor(Cache<CacheEntity> cache, ImplRestConnection connection) {
        this.mCache = cache;
        this.mConnection = connection;
    }

    public static HttpRestExecutor getInstance(Cache<CacheEntity> cache, ImplRestConnection connection) {
        if (_INSTANCE == null) {
            _INSTANCE = new HttpRestExecutor(cache, connection);
        }
        return _INSTANCE;
    }

    @Override // com.yolanda.nohttp.ImplRestExecutor
    public HttpResponse executeRequest(Request<?> request) {
        HttpResponse httpResponse;
        Request<?> redirectRequest;
        CacheMode cacheMode = request.getCacheMode();
        CacheEntity cacheEntity = this.mCache.get(request.getCacheKey());
        if (cacheMode == CacheMode.ONLY_READ_CACHE) {
            if (cacheEntity == null) {
                return new HttpResponse(false, null, null, new NotFoundCacheError("Could not find the cache."));
            }
            return new HttpResponse(true, cacheEntity.getResponseHeaders(), cacheEntity.getData(), null);
        } else if (cacheMode == CacheMode.IF_NONE_CACHE_REQUEST && cacheEntity != null) {
            return new HttpResponse(true, cacheEntity.getResponseHeaders(), cacheEntity.getData(), null);
        } else {
            if (cacheEntity == null || cacheEntity.getLocalExpire() < System.currentTimeMillis()) {
                if (cacheEntity != null) {
                    handleCacheHeader(request, cacheEntity);
                }
                httpResponse = this.mConnection.requestNetwork(request);
            } else {
                httpResponse = new HttpResponse(true, cacheEntity.getResponseHeaders(), cacheEntity.getData(), null);
            }
            boolean isFromCache = httpResponse.isFromCache;
            Headers responseHeaders = httpResponse.responseHeaders;
            byte[] responseBody = httpResponse.responseBody;
            Exception exception = httpResponse.exception;
            int responseCode = responseHeaders.getResponseCode();
            if (exception == null) {
                if (responseCode == 304) {
                    if (cacheEntity == null) {
                        exception = new ServerError("The server responseCode of 304, but not the client cache.");
                    } else {
                        isFromCache = true;
                        cacheEntity.getResponseHeaders().setAll(responseHeaders);
                        responseHeaders = cacheEntity.getResponseHeaders();
                        responseBody = cacheEntity.getData();
                    }
                } else if (responseCode == 302 || responseCode == 303) {
                    RedirectHandler redirectHandler = request.getRedirectHandler();
                    if (redirectHandler != null) {
                        redirectRequest = redirectHandler.onRedirect(responseHeaders);
                    } else {
                        redirectRequest = NoHttp.createStringRequest(responseHeaders.getLocation(), request.getRequestMethod());
                        redirectRequest.setSSLSocketFactory(request.getSSLSocketFactory());
                        redirectRequest.setProxy(request.getProxy());
                    }
                    if (redirectRequest != null) {
                        HttpResponse redirectHttpResponse = executeRequest(redirectRequest);
                        Headers redirectHeaders = redirectHttpResponse.responseHeaders;
                        responseBody = redirectHttpResponse.responseBody;
                        exception = redirectHttpResponse.exception;
                        String contentEncoding = redirectHeaders.getContentEncoding();
                        if (!TextUtils.isEmpty(contentEncoding)) {
                            responseHeaders.set((Headers) "Content-Encoding", contentEncoding);
                        }
                        responseHeaders.set((Headers) "Content-Length", Integer.toString(redirectHeaders.getContentLength()));
                        String contentType = redirectHeaders.getContentType();
                        if (!TextUtils.isEmpty(contentType)) {
                            responseHeaders.set((Headers) "Content-Type", contentType);
                        }
                    }
                }
                if (!(!request.needCache() || responseBody == null || responseCode == 302 || responseCode == 303)) {
                    if (cacheEntity == null) {
                        cacheEntity = HeaderParser.parseCacheHeaders(responseHeaders, responseBody, true);
                    }
                    if (cacheEntity != null) {
                        this.mCache.replace(request.getCacheKey(), cacheEntity);
                    }
                }
            } else if (cacheEntity != null && cacheMode == CacheMode.REQUEST_FAILED_READ_CACHE) {
                exception = null;
                isFromCache = true;
                responseHeaders = cacheEntity.getResponseHeaders();
                responseBody = cacheEntity.getData();
            }
            return new HttpResponse(isFromCache, responseHeaders, responseBody, exception);
        }
    }

    private void handleCacheHeader(Request<?> request, CacheEntity cacheEntity) {
        if (cacheEntity == null) {
            request.removeHeader("If-None-Match");
            request.removeHeader("If-Modified-Since");
            return;
        }
        Headers headers = cacheEntity.getResponseHeaders();
        String eTag = headers.getETag();
        if (eTag != null) {
            request.setHeader("If-None-Match", eTag);
        }
        long lastModified = headers.getLastModified();
        if (lastModified > 0) {
            request.setHeader("If-Modified-Since", HttpDateTime.formatMillisToGMT(lastModified));
        }
    }
}
