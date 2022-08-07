package com.yolanda.nohttp;

import com.yolanda.nohttp.cache.CacheMode;
import com.yolanda.nohttp.tools.Writer;
import java.net.Proxy;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSocketFactory;

/* loaded from: classes2.dex */
public interface ImplServerRequest {
    boolean doOutPut();

    String getAccept();

    String getAcceptLanguage();

    String getCacheKey();

    CacheMode getCacheMode();

    int getConnectTimeout();

    long getContentLength();

    String getContentType();

    HostnameVerifier getHostnameVerifier();

    Proxy getProxy();

    int getReadTimeout();

    RedirectHandler getRedirectHandler();

    RequestMethod getRequestMethod();

    SSLSocketFactory getSSLSocketFactory();

    Object getTag();

    String getUserAgent();

    Headers headers();

    boolean needCache();

    void onPreExecute();

    void onWriteRequestBody(Writer writer);

    String url();
}
