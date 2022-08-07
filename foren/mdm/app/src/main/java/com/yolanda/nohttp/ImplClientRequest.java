package com.yolanda.nohttp;

import com.yolanda.nohttp.able.FinishAble;
import com.yolanda.nohttp.able.QueueAble;
import com.yolanda.nohttp.able.SignCancelAble;
import com.yolanda.nohttp.able.StartAble;
import com.yolanda.nohttp.cache.CacheMode;
import java.net.Proxy;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSocketFactory;

/* loaded from: classes2.dex */
public interface ImplClientRequest extends QueueAble, StartAble, SignCancelAble, FinishAble {
    void addHeader(String str, String str2);

    void removeAllHeader();

    void removeHeader(String str);

    void setCacheKey(String str);

    void setCacheMode(CacheMode cacheMode);

    void setConnectTimeout(int i);

    void setHeader(String str, String str2);

    void setHostnameVerifier(HostnameVerifier hostnameVerifier);

    void setProxy(Proxy proxy);

    void setReadTimeout(int i);

    void setRedirectHandler(RedirectHandler redirectHandler);

    void setRequestBody(String str);

    void setRequestBody(byte[] bArr);

    void setSSLSocketFactory(SSLSocketFactory sSLSocketFactory);

    void setTag(Object obj);
}
