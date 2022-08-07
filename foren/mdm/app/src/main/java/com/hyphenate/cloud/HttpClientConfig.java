package com.hyphenate.cloud;

import com.alipay.sdk.cons.b;
import com.hyphenate.chat.EMClient;
import java.util.Map;
import org.apache.http.HttpHost;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.DefaultConnectionReuseStrategy;
import org.apache.http.impl.client.DefaultConnectionKeepAliveStrategy;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpProtocolParams;

/* loaded from: classes2.dex */
public class HttpClientConfig {
    private static final String EASEMOB_PLATFORM = "Android";
    private static final String EASEMOB_USERSERVER_DOMAIN_ID = "hyphenate.com";
    public static String EM_TIME_OUT_KEY = "em_timeout";
    public static int EM_DEFAULT_TIMEOUT = 30000;

    public static String getBaseUrlByAppKey() {
        return EMHttpClient.getInstance().chatConfig().f();
    }

    public static DefaultHttpClient getDefaultHttpClient() {
        return getDefaultHttpClient(EM_DEFAULT_TIMEOUT);
    }

    public static DefaultHttpClient getDefaultHttpClient(int i) {
        BasicHttpParams basicHttpParams = new BasicHttpParams();
        HttpConnectionParams.setConnectionTimeout(basicHttpParams, i);
        HttpConnectionParams.setSoTimeout(basicHttpParams, 20000);
        HttpConnectionParams.setTcpNoDelay(basicHttpParams, true);
        HttpProtocolParams.setUserAgent(basicHttpParams, getDefaultUserAgent());
        SchemeRegistry schemeRegistry = new SchemeRegistry();
        schemeRegistry.register(new Scheme(HttpHost.DEFAULT_SCHEME_NAME, PlainSocketFactory.getSocketFactory(), 80));
        schemeRegistry.register(new Scheme(b.a, SSLSocketFactory.getSocketFactory(), 443));
        DefaultHttpClient defaultHttpClient = new DefaultHttpClient(new ThreadSafeClientConnManager(basicHttpParams, schemeRegistry), basicHttpParams);
        defaultHttpClient.setKeepAliveStrategy(new DefaultConnectionKeepAliveStrategy());
        defaultHttpClient.setReuseStrategy(new DefaultConnectionReuseStrategy());
        return defaultHttpClient;
    }

    private static String getDefaultUserAgent() {
        StringBuilder append = new StringBuilder().append("Easemob-SDK(Android) ");
        EMClient.getInstance();
        return append.append(EMClient.VERSION).toString();
    }

    public static String getEaseMobUserServerDomainId() {
        return EASEMOB_USERSERVER_DOMAIN_ID;
    }

    public static String getFileDirRemoteUrl() {
        return getBaseUrlByAppKey() + "/chatfiles/";
    }

    public static String getFileRemoteUrl(String str) {
        if (str.startsWith(HttpHost.DEFAULT_SCHEME_NAME)) {
            return str;
        }
        return getFileDirRemoteUrl() + str;
    }

    public static int getTimeout(Map<String, String> map) {
        int i = EM_DEFAULT_TIMEOUT;
        if (map == null || map.get(EM_TIME_OUT_KEY) == null) {
            return i;
        }
        int intValue = Integer.valueOf(map.get(EM_TIME_OUT_KEY)).intValue();
        map.remove(EM_TIME_OUT_KEY);
        return intValue;
    }
}
