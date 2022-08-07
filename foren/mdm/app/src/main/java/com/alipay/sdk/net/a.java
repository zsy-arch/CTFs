package com.alipay.sdk.net;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Proxy;
import android.os.Build;
import android.text.TextUtils;
import com.alipay.sdk.cons.b;
import java.net.URL;
import java.util.List;
import org.apache.http.Header;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.params.ConnRoutePNames;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HTTP;

/* loaded from: classes.dex */
public final class a {
    public static final String a = "application/octet-stream;binary/octet-stream";
    public String b;
    private Context c;

    private a(Context context) {
        this(context, null);
    }

    public a(Context context, String str) {
        this.c = context;
        this.b = str;
    }

    private void a(String str) {
        this.b = str;
    }

    private String a() {
        return this.b;
    }

    private URL b() {
        try {
            return new URL(this.b);
        } catch (Exception e) {
            return null;
        }
    }

    public final HttpResponse a(byte[] bArr, List<Header> list) throws Throwable {
        HttpUriRequest httpUriRequest;
        URL b;
        HttpResponse httpResponse = null;
        r1 = null;
        r1 = null;
        r1 = null;
        r1 = null;
        r1 = null;
        r1 = null;
        HttpHost httpHost = null;
        new StringBuilder("requestUrl : ").append(this.b);
        b a2 = b.a();
        if (a2 != null) {
            try {
                HttpParams params = a2.c.getParams();
                if (Build.VERSION.SDK_INT >= 11) {
                    String g = g();
                    if ((g == null || g.contains("wap")) && (b = b()) != null) {
                        b.a.equalsIgnoreCase(b.getProtocol());
                        String property = System.getProperty("https.proxyHost");
                        String property2 = System.getProperty("https.proxyPort");
                        if (!TextUtils.isEmpty(property)) {
                            httpHost = new HttpHost(property, Integer.parseInt(property2));
                        }
                    }
                } else {
                    NetworkInfo f = f();
                    if (f != null && f.isAvailable() && f.getType() == 0) {
                        String defaultHost = Proxy.getDefaultHost();
                        int defaultPort = Proxy.getDefaultPort();
                        if (defaultHost != null) {
                            httpHost = new HttpHost(defaultHost, defaultPort);
                        }
                    }
                }
                if (httpHost != null) {
                    params.setParameter(ConnRoutePNames.DEFAULT_PROXY, httpHost);
                }
                if (bArr == null || bArr.length == 0) {
                    httpUriRequest = new HttpGet(this.b);
                } else {
                    httpUriRequest = new HttpPost(this.b);
                    ByteArrayEntity byteArrayEntity = new ByteArrayEntity(bArr);
                    byteArrayEntity.setContentType(a);
                    ((HttpPost) httpUriRequest).setEntity(byteArrayEntity);
                    httpUriRequest.addHeader("Accept-Charset", "UTF-8");
                    httpUriRequest.addHeader("Connection", HTTP.CONN_KEEP_ALIVE);
                    httpUriRequest.addHeader(HTTP.CONN_KEEP_ALIVE, "timeout=180, max=100");
                }
                if (list != null) {
                    for (Header header : list) {
                        httpUriRequest.addHeader(header);
                    }
                }
                httpResponse = a2.a(httpUriRequest);
                Header[] headers = httpResponse.getHeaders("X-Hostname");
                if (!(headers == null || headers.length <= 0 || headers[0] == null)) {
                    httpResponse.getHeaders("X-Hostname")[0].toString();
                }
                Header[] headers2 = httpResponse.getHeaders("X-ExecuteTime");
                if (!(headers2 == null || headers2.length <= 0 || headers2[0] == null)) {
                    httpResponse.getHeaders("X-ExecuteTime")[0].toString();
                }
            } catch (Throwable th) {
                if (a2 != null) {
                    try {
                        ClientConnectionManager connectionManager = a2.c.getConnectionManager();
                        if (connectionManager != null) {
                            connectionManager.shutdown();
                            b.b = null;
                        }
                    } catch (Throwable th2) {
                    }
                }
                throw th;
            }
        }
        return httpResponse;
    }

    private HttpHost c() {
        URL b;
        if (Build.VERSION.SDK_INT >= 11) {
            String g = g();
            if ((g != null && !g.contains("wap")) || (b = b()) == null) {
                return null;
            }
            b.a.equalsIgnoreCase(b.getProtocol());
            String property = System.getProperty("https.proxyHost");
            String property2 = System.getProperty("https.proxyPort");
            if (!TextUtils.isEmpty(property)) {
                return new HttpHost(property, Integer.parseInt(property2));
            }
            return null;
        }
        NetworkInfo f = f();
        if (f == null || !f.isAvailable() || f.getType() != 0) {
            return null;
        }
        String defaultHost = Proxy.getDefaultHost();
        int defaultPort = Proxy.getDefaultPort();
        if (defaultHost != null) {
            return new HttpHost(defaultHost, defaultPort);
        }
        return null;
    }

    private HttpHost d() {
        NetworkInfo f = f();
        if (f == null || !f.isAvailable() || f.getType() != 0) {
            return null;
        }
        String defaultHost = Proxy.getDefaultHost();
        int defaultPort = Proxy.getDefaultPort();
        if (defaultHost != null) {
            return new HttpHost(defaultHost, defaultPort);
        }
        return null;
    }

    private HttpHost e() {
        URL b;
        String g = g();
        if ((g != null && !g.contains("wap")) || (b = b()) == null) {
            return null;
        }
        b.a.equalsIgnoreCase(b.getProtocol());
        String property = System.getProperty("https.proxyHost");
        String property2 = System.getProperty("https.proxyPort");
        if (!TextUtils.isEmpty(property)) {
            return new HttpHost(property, Integer.parseInt(property2));
        }
        return null;
    }

    private NetworkInfo f() {
        try {
            return ((ConnectivityManager) this.c.getSystemService("connectivity")).getActiveNetworkInfo();
        } catch (Exception e) {
            return null;
        }
    }

    private String g() {
        try {
            NetworkInfo f = f();
            if (f == null || !f.isAvailable()) {
                return "none";
            }
            if (f.getType() == 1) {
                return "wifi";
            }
            return f.getExtraInfo().toLowerCase();
        } catch (Exception e) {
            return "none";
        }
    }
}
