package com.alipay.android.phone.mrpc.core;

import android.content.ContentResolver;
import android.content.Context;
import android.net.SSLCertificateSocketFactory;
import android.net.SSLSessionCache;
import android.os.Looper;
import android.util.Log;
import com.alipay.sdk.cons.b;
import com.hy.http.HttpEntity;
import com.yolanda.nohttp.Headers;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.security.Security;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;
import javax.net.ssl.HttpsURLConnection;
import org.apache.http.Header;
import org.apache.http.HttpHost;
import org.apache.http.HttpRequest;
import org.apache.http.HttpRequestInterceptor;
import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.client.HttpClient;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.client.RedirectHandler;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.params.HttpClientParams;
import org.apache.http.client.protocol.ClientContext;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.ConnectionKeepAliveStrategy;
import org.apache.http.conn.params.ConnManagerParams;
import org.apache.http.conn.params.ConnPerRouteBean;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.entity.AbstractHttpEntity;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.DefaultRedirectHandler;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.BasicHttpProcessor;
import org.apache.http.protocol.HTTP;
import org.apache.http.protocol.HttpContext;

/* loaded from: classes.dex */
public final class AndroidHttpClient implements HttpClient {
    private static final int CONNECTION_POOL_TIMEOUT = 60000;
    private static final int CONNECTION_TIMEOUT = 20000;
    private static final int MAX_CONNECTIONS = 50;
    private static final int SOCKET_OPERATION_TIMEOUT = 30000;
    private static final String TAG = "AndroidHttpClient";
    private volatile LoggingConfiguration curlConfiguration;
    private final HttpClient delegate;
    private RuntimeException mLeakedException = new IllegalStateException("AndroidHttpClient created and never closed");
    public static long DEFAULT_SYNC_MIN_GZIP_BYTES = 160;
    private static String[] textContentTypes = {"text/", HttpEntity.APPLICATION_XML, "application/json"};
    private static final HttpRequestInterceptor sThreadCheckInterceptor = new HttpRequestInterceptor() { // from class: com.alipay.android.phone.mrpc.core.AndroidHttpClient.1
        @Override // org.apache.http.HttpRequestInterceptor
        public final void process(HttpRequest httpRequest, HttpContext httpContext) {
            if (Looper.myLooper() != null && Looper.myLooper() == Looper.getMainLooper()) {
                throw new RuntimeException("This thread forbids HTTP requests");
            }
        }
    };

    /* loaded from: classes.dex */
    private class CurlLogger implements HttpRequestInterceptor {
        private CurlLogger() {
            AndroidHttpClient.this = r1;
        }

        @Override // org.apache.http.HttpRequestInterceptor
        public void process(HttpRequest httpRequest, HttpContext httpContext) {
            LoggingConfiguration loggingConfiguration = AndroidHttpClient.this.curlConfiguration;
            if (loggingConfiguration != null && loggingConfiguration.isLoggable() && (httpRequest instanceof HttpUriRequest)) {
                loggingConfiguration.println(AndroidHttpClient.toCurl((HttpUriRequest) httpRequest, false));
            }
        }
    }

    /* loaded from: classes.dex */
    private static class LoggingConfiguration {
        private final int level;
        private final String tag;

        private LoggingConfiguration(String str, int i) {
            this.tag = str;
            this.level = i;
        }

        public boolean isLoggable() {
            return Log.isLoggable(this.tag, this.level);
        }

        public void println(String str) {
            Log.println(this.level, this.tag, str);
        }
    }

    private AndroidHttpClient(ClientConnectionManager clientConnectionManager, HttpParams httpParams) {
        this.delegate = new DefaultHttpClient(clientConnectionManager, httpParams) { // from class: com.alipay.android.phone.mrpc.core.AndroidHttpClient.2
            @Override // org.apache.http.impl.client.DefaultHttpClient, org.apache.http.impl.client.AbstractHttpClient
            protected ConnectionKeepAliveStrategy createConnectionKeepAliveStrategy() {
                return new ConnectionKeepAliveStrategy() { // from class: com.alipay.android.phone.mrpc.core.AndroidHttpClient.2.2
                    @Override // org.apache.http.conn.ConnectionKeepAliveStrategy
                    public long getKeepAliveDuration(HttpResponse httpResponse, HttpContext httpContext) {
                        return 180000L;
                    }
                };
            }

            @Override // org.apache.http.impl.client.DefaultHttpClient, org.apache.http.impl.client.AbstractHttpClient
            protected HttpContext createHttpContext() {
                BasicHttpContext basicHttpContext = new BasicHttpContext();
                basicHttpContext.setAttribute(ClientContext.AUTHSCHEME_REGISTRY, getAuthSchemes());
                basicHttpContext.setAttribute(ClientContext.COOKIESPEC_REGISTRY, getCookieSpecs());
                basicHttpContext.setAttribute(ClientContext.CREDS_PROVIDER, getCredentialsProvider());
                return basicHttpContext;
            }

            @Override // org.apache.http.impl.client.DefaultHttpClient, org.apache.http.impl.client.AbstractHttpClient
            public BasicHttpProcessor createHttpProcessor() {
                BasicHttpProcessor createHttpProcessor = super.createHttpProcessor();
                createHttpProcessor.addRequestInterceptor(AndroidHttpClient.sThreadCheckInterceptor);
                createHttpProcessor.addRequestInterceptor(new CurlLogger());
                return createHttpProcessor;
            }

            @Override // org.apache.http.impl.client.DefaultHttpClient, org.apache.http.impl.client.AbstractHttpClient
            protected RedirectHandler createRedirectHandler() {
                return new DefaultRedirectHandler() { // from class: com.alipay.android.phone.mrpc.core.AndroidHttpClient.2.1
                    int mRedirects;

                    @Override // org.apache.http.impl.client.DefaultRedirectHandler, org.apache.http.client.RedirectHandler
                    public boolean isRedirectRequested(HttpResponse httpResponse, HttpContext httpContext) {
                        this.mRedirects++;
                        boolean isRedirectRequested = super.isRedirectRequested(httpResponse, httpContext);
                        if (isRedirectRequested || this.mRedirects >= 5) {
                            return isRedirectRequested;
                        }
                        int statusCode = httpResponse.getStatusLine().getStatusCode();
                        if (statusCode == 301 || statusCode == 302) {
                            return true;
                        }
                        return isRedirectRequested;
                    }
                };
            }
        };
    }

    public static AbstractHttpEntity getCompressedEntity(byte[] bArr, ContentResolver contentResolver) {
        if (bArr.length < getMinGzipSize(contentResolver)) {
            return new ByteArrayEntity(bArr);
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        GZIPOutputStream gZIPOutputStream = new GZIPOutputStream(byteArrayOutputStream);
        gZIPOutputStream.write(bArr);
        gZIPOutputStream.close();
        ByteArrayEntity byteArrayEntity = new ByteArrayEntity(byteArrayOutputStream.toByteArray());
        byteArrayEntity.setContentEncoding("gzip");
        new StringBuilder("gzip size:").append(bArr.length).append("->").append(byteArrayEntity.getContentLength());
        return byteArrayEntity;
    }

    public static long getMinGzipSize(ContentResolver contentResolver) {
        return DEFAULT_SYNC_MIN_GZIP_BYTES;
    }

    public static InputStream getUngzippedContent(org.apache.http.HttpEntity httpEntity) {
        Header contentEncoding;
        String value;
        InputStream content = httpEntity.getContent();
        if (content == null || (contentEncoding = httpEntity.getContentEncoding()) == null || (value = contentEncoding.getValue()) == null) {
            return content;
        }
        return value.contains("gzip") ? new GZIPInputStream(content) : content;
    }

    private static boolean isBinaryContent(HttpUriRequest httpUriRequest) {
        Header[] headers = httpUriRequest.getHeaders("content-encoding");
        if (headers != null) {
            for (Header header : headers) {
                if ("gzip".equalsIgnoreCase(header.getValue())) {
                    return true;
                }
            }
        }
        Header[] headers2 = httpUriRequest.getHeaders("content-type");
        if (headers2 == null) {
            return true;
        }
        for (Header header2 : headers2) {
            for (String str : textContentTypes) {
                if (header2.getValue().startsWith(str)) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void modifyRequestToAcceptGzipResponse(HttpRequest httpRequest) {
        httpRequest.addHeader(Headers.HEAD_KEY_ACCEPT_ENCODING, "gzip");
    }

    public static void modifyRequestToKeepAlive(HttpRequest httpRequest) {
        httpRequest.addHeader("Connection", HTTP.CONN_KEEP_ALIVE);
    }

    public static AndroidHttpClient newInstance(String str) {
        return newInstance(str, null);
    }

    public static AndroidHttpClient newInstance(String str, Context context) {
        BasicHttpParams basicHttpParams = new BasicHttpParams();
        HttpProtocolParams.setVersion(basicHttpParams, HttpVersion.HTTP_1_1);
        HttpProtocolParams.setUseExpectContinue(basicHttpParams, false);
        HttpConnectionParams.setStaleCheckingEnabled(basicHttpParams, true);
        HttpConnectionParams.setConnectionTimeout(basicHttpParams, 20000);
        HttpConnectionParams.setSoTimeout(basicHttpParams, SOCKET_OPERATION_TIMEOUT);
        HttpConnectionParams.setSocketBufferSize(basicHttpParams, 8192);
        HttpClientParams.setRedirecting(basicHttpParams, true);
        HttpClientParams.setAuthenticating(basicHttpParams, false);
        HttpProtocolParams.setUserAgent(basicHttpParams, str);
        SchemeRegistry schemeRegistry = new SchemeRegistry();
        schemeRegistry.register(new Scheme(HttpHost.DEFAULT_SCHEME_NAME, PlainSocketFactory.getSocketFactory(), 80));
        schemeRegistry.register(new Scheme(b.a, SSLCertificateSocketFactory.getHttpSocketFactory(SOCKET_OPERATION_TIMEOUT, context == null ? null : new SSLSessionCache(context)), 443));
        ThreadSafeClientConnManager threadSafeClientConnManager = new ThreadSafeClientConnManager(basicHttpParams, schemeRegistry);
        ConnManagerParams.setTimeout(basicHttpParams, 60000L);
        ConnManagerParams.setMaxConnectionsPerRoute(basicHttpParams, new ConnPerRouteBean(10));
        ConnManagerParams.setMaxTotalConnections(basicHttpParams, 50);
        Security.setProperty("networkaddress.cache.ttl", "-1");
        setDefaultHostnameVerifier();
        return new AndroidHttpClient(threadSafeClientConnManager, basicHttpParams);
    }

    public static long parseDate(String str) {
        return HttpDateTime.parse(str);
    }

    private static void setDefaultHostnameVerifier() {
        HttpsURLConnection.setDefaultHostnameVerifier(SSLSocketFactory.STRICT_HOSTNAME_VERIFIER);
    }

    /* JADX WARN: Removed duplicated region for block: B:25:0x008a  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x00d7  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.lang.String toCurl(org.apache.http.client.methods.HttpUriRequest r8, boolean r9) {
        /*
            r2 = 0
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r0 = "curl "
            r3.append(r0)
            org.apache.http.Header[] r1 = r8.getAllHeaders()
            int r4 = r1.length
            r0 = r2
        L_0x0011:
            if (r0 >= r4) goto L_0x0047
            r5 = r1[r0]
            if (r9 != 0) goto L_0x002f
            java.lang.String r6 = r5.getName()
            java.lang.String r7 = "Authorization"
            boolean r6 = r6.equals(r7)
            if (r6 != 0) goto L_0x0044
            java.lang.String r6 = r5.getName()
            java.lang.String r7 = "Cookie"
            boolean r6 = r6.equals(r7)
            if (r6 != 0) goto L_0x0044
        L_0x002f:
            java.lang.String r6 = "--header \""
            r3.append(r6)
            java.lang.String r5 = r5.toString()
            java.lang.String r5 = r5.trim()
            r3.append(r5)
            java.lang.String r5 = "\" "
            r3.append(r5)
        L_0x0044:
            int r0 = r0 + 1
            goto L_0x0011
        L_0x0047:
            java.net.URI r1 = r8.getURI()
            boolean r0 = r8 instanceof org.apache.http.impl.client.RequestWrapper
            if (r0 == 0) goto L_0x00dd
            r0 = r8
            org.apache.http.impl.client.RequestWrapper r0 = (org.apache.http.impl.client.RequestWrapper) r0
            org.apache.http.HttpRequest r0 = r0.getOriginal()
            boolean r4 = r0 instanceof org.apache.http.client.methods.HttpUriRequest
            if (r4 == 0) goto L_0x00dd
            org.apache.http.client.methods.HttpUriRequest r0 = (org.apache.http.client.methods.HttpUriRequest) r0
            java.net.URI r0 = r0.getURI()
        L_0x0060:
            java.lang.String r1 = "\""
            r3.append(r1)
            r3.append(r0)
            java.lang.String r0 = "\""
            r3.append(r0)
            boolean r0 = r8 instanceof org.apache.http.HttpEntityEnclosingRequest
            if (r0 == 0) goto L_0x00be
            r0 = r8
            org.apache.http.HttpEntityEnclosingRequest r0 = (org.apache.http.HttpEntityEnclosingRequest) r0
            org.apache.http.HttpEntity r0 = r0.getEntity()
            if (r0 == 0) goto L_0x00be
            boolean r1 = r0.isRepeatable()
            if (r1 == 0) goto L_0x00be
            long r4 = r0.getContentLength()
            r6 = 1024(0x400, double:5.06E-321)
            int r1 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r1 >= 0) goto L_0x00d7
            java.io.ByteArrayOutputStream r1 = new java.io.ByteArrayOutputStream
            r1.<init>()
            r0.writeTo(r1)
            boolean r0 = isBinaryContent(r8)
            if (r0 == 0) goto L_0x00c3
            byte[] r0 = r1.toByteArray()
            r1 = 2
            java.lang.String r0 = android.util.Base64.encodeToString(r0, r1)
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r4 = "echo '"
            r1.<init>(r4)
            java.lang.StringBuilder r0 = r1.append(r0)
            java.lang.String r1 = "' | base64 -d > /tmp/$$.bin; "
            java.lang.StringBuilder r0 = r0.append(r1)
            java.lang.String r0 = r0.toString()
            r3.insert(r2, r0)
            java.lang.String r0 = " --data-binary @/tmp/$$.bin"
            r3.append(r0)
        L_0x00be:
            java.lang.String r0 = r3.toString()
            return r0
        L_0x00c3:
            java.lang.String r0 = r1.toString()
            java.lang.String r1 = " --data-ascii \""
            java.lang.StringBuilder r1 = r3.append(r1)
            java.lang.StringBuilder r0 = r1.append(r0)
            java.lang.String r1 = "\""
            r0.append(r1)
            goto L_0x00be
        L_0x00d7:
            java.lang.String r0 = " [TOO MUCH DATA TO INCLUDE]"
            r3.append(r0)
            goto L_0x00be
        L_0x00dd:
            r0 = r1
            goto L_0x0060
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alipay.android.phone.mrpc.core.AndroidHttpClient.toCurl(org.apache.http.client.methods.HttpUriRequest, boolean):java.lang.String");
    }

    public final void close() {
        if (this.mLeakedException != null) {
            getConnectionManager().shutdown();
            this.mLeakedException = null;
        }
    }

    public final void disableCurlLogging() {
        this.curlConfiguration = null;
    }

    public final void enableCurlLogging(String str, int i) {
        if (str == null) {
            throw new NullPointerException("name");
        } else if (i < 2 || i > 7) {
            throw new IllegalArgumentException("Level is out of range [2..7]");
        } else {
            this.curlConfiguration = new LoggingConfiguration(str, i);
        }
    }

    @Override // org.apache.http.client.HttpClient
    public final <T> T execute(HttpHost httpHost, HttpRequest httpRequest, ResponseHandler<? extends T> responseHandler) {
        return (T) this.delegate.execute(httpHost, httpRequest, responseHandler);
    }

    @Override // org.apache.http.client.HttpClient
    public final <T> T execute(HttpHost httpHost, HttpRequest httpRequest, ResponseHandler<? extends T> responseHandler, HttpContext httpContext) {
        return (T) this.delegate.execute(httpHost, httpRequest, responseHandler, httpContext);
    }

    @Override // org.apache.http.client.HttpClient
    public final <T> T execute(HttpUriRequest httpUriRequest, ResponseHandler<? extends T> responseHandler) {
        return (T) this.delegate.execute(httpUriRequest, responseHandler);
    }

    @Override // org.apache.http.client.HttpClient
    public final <T> T execute(HttpUriRequest httpUriRequest, ResponseHandler<? extends T> responseHandler, HttpContext httpContext) {
        return (T) this.delegate.execute(httpUriRequest, responseHandler, httpContext);
    }

    @Override // org.apache.http.client.HttpClient
    public final HttpResponse execute(HttpHost httpHost, HttpRequest httpRequest) {
        return this.delegate.execute(httpHost, httpRequest);
    }

    @Override // org.apache.http.client.HttpClient
    public final HttpResponse execute(HttpHost httpHost, HttpRequest httpRequest, HttpContext httpContext) {
        return this.delegate.execute(httpHost, httpRequest, httpContext);
    }

    @Override // org.apache.http.client.HttpClient
    public final HttpResponse execute(HttpUriRequest httpUriRequest) {
        return this.delegate.execute(httpUriRequest);
    }

    @Override // org.apache.http.client.HttpClient
    public final HttpResponse execute(HttpUriRequest httpUriRequest, HttpContext httpContext) {
        return this.delegate.execute(httpUriRequest, httpContext);
    }

    @Override // org.apache.http.client.HttpClient
    public final ClientConnectionManager getConnectionManager() {
        return this.delegate.getConnectionManager();
    }

    @Override // org.apache.http.client.HttpClient
    public final HttpParams getParams() {
        return this.delegate.getParams();
    }

    public final void setHttpRequestRetryHandler(HttpRequestRetryHandler httpRequestRetryHandler) {
        ((DefaultHttpClient) this.delegate).setHttpRequestRetryHandler(httpRequestRetryHandler);
    }
}
