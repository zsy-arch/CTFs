package com.tencent.open.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Proxy;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import com.alipay.sdk.cons.b;
import com.tencent.connect.a.a;
import com.tencent.connect.auth.QQToken;
import com.tencent.open.a.f;
import com.tencent.open.utils.Util;
import com.tencent.tauth.IRequestListener;
import com.yolanda.nohttp.Headers;
import java.io.ByteArrayOutputStream;
import java.io.CharConversionException;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InvalidClassException;
import java.io.InvalidObjectException;
import java.io.NotActiveException;
import java.io.NotSerializableException;
import java.io.OptionalDataException;
import java.io.StreamCorruptedException;
import java.io.SyncFailedException;
import java.io.UTFDataFormatException;
import java.io.UnsupportedEncodingException;
import java.io.WriteAbortedException;
import java.net.BindException;
import java.net.ConnectException;
import java.net.HttpRetryException;
import java.net.MalformedURLException;
import java.net.NoRouteToHostException;
import java.net.PortUnreachableException;
import java.net.ProtocolException;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.net.URLEncoder;
import java.net.UnknownHostException;
import java.net.UnknownServiceException;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.FileLockInterruptionException;
import java.nio.charset.MalformedInputException;
import java.nio.charset.UnmappableCharacterException;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.InvalidPropertiesFormatException;
import java.util.zip.GZIPInputStream;
import java.util.zip.ZipException;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLHandshakeException;
import javax.net.ssl.SSLKeyException;
import javax.net.ssl.SSLPeerUnverifiedException;
import javax.net.ssl.SSLProtocolException;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;
import org.apache.http.ConnectionClosedException;
import org.apache.http.Header;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.MalformedChunkCodingException;
import org.apache.http.NoHttpResponseException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.HttpResponseException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.conn.params.ConnRoutePNames;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.protocol.HTTP;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: ProGuard */
/* loaded from: classes2.dex */
public class HttpUtils {
    private HttpUtils() {
    }

    /* compiled from: ProGuard */
    /* loaded from: classes2.dex */
    public static class HttpStatusException extends Exception {
        public static final String ERROR_INFO = "http status code error:";

        public HttpStatusException(String str) {
            super(str);
        }
    }

    /* compiled from: ProGuard */
    /* loaded from: classes2.dex */
    public static class NetworkUnavailableException extends Exception {
        public static final String ERROR_INFO = "network unavailable";

        public NetworkUnavailableException(String str) {
            super(str);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:58:0x01bd A[LOOP:0: B:9:0x00da->B:58:0x01bd, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:68:0x011e A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static org.json.JSONObject request(com.tencent.connect.auth.QQToken r20, android.content.Context r21, java.lang.String r22, android.os.Bundle r23, java.lang.String r24) throws java.io.IOException, org.json.JSONException, com.tencent.open.utils.HttpUtils.NetworkUnavailableException, com.tencent.open.utils.HttpUtils.HttpStatusException {
        /*
            Method dump skipped, instructions count: 455
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.open.utils.HttpUtils.request(com.tencent.connect.auth.QQToken, android.content.Context, java.lang.String, android.os.Bundle, java.lang.String):org.json.JSONObject");
    }

    /* JADX WARN: Type inference failed for: r0v1, types: [com.tencent.open.utils.HttpUtils$1] */
    public static void requestAsync(final QQToken qQToken, final Context context, final String str, final Bundle bundle, final String str2, final IRequestListener iRequestListener) {
        f.a("openSDK_LOG.HttpUtils", "OpenApi requestAsync");
        new Thread() { // from class: com.tencent.open.utils.HttpUtils.1
            @Override // java.lang.Thread, java.lang.Runnable
            public void run() {
                try {
                    JSONObject request = HttpUtils.request(qQToken, context, str, bundle, str2);
                    if (iRequestListener != null) {
                        iRequestListener.onComplete(request);
                        f.b("openSDK_LOG.HttpUtils", "OpenApi onComplete");
                    }
                } catch (HttpStatusException e) {
                    if (iRequestListener != null) {
                        iRequestListener.onHttpStatusException(e);
                        f.b("openSDK_LOG.HttpUtils", "OpenApi requestAsync onHttpStatusException", e);
                    }
                } catch (NetworkUnavailableException e2) {
                    if (iRequestListener != null) {
                        iRequestListener.onNetworkUnavailableException(e2);
                        f.b("openSDK_LOG.HttpUtils", "OpenApi requestAsync onNetworkUnavailableException", e2);
                    }
                } catch (MalformedURLException e3) {
                    if (iRequestListener != null) {
                        iRequestListener.onMalformedURLException(e3);
                        f.b("openSDK_LOG.HttpUtils", "OpenApi requestAsync MalformedURLException", e3);
                    }
                } catch (SocketTimeoutException e4) {
                    if (iRequestListener != null) {
                        iRequestListener.onSocketTimeoutException(e4);
                        f.b("openSDK_LOG.HttpUtils", "OpenApi requestAsync onSocketTimeoutException", e4);
                    }
                } catch (ConnectTimeoutException e5) {
                    if (iRequestListener != null) {
                        iRequestListener.onConnectTimeoutException(e5);
                        f.b("openSDK_LOG.HttpUtils", "OpenApi requestAsync onConnectTimeoutException", e5);
                    }
                } catch (IOException e6) {
                    if (iRequestListener != null) {
                        iRequestListener.onIOException(e6);
                        f.b("openSDK_LOG.HttpUtils", "OpenApi requestAsync IOException", e6);
                    }
                } catch (JSONException e7) {
                    if (iRequestListener != null) {
                        iRequestListener.onJSONException(e7);
                        f.b("openSDK_LOG.HttpUtils", "OpenApi requestAsync JSONException", e7);
                    }
                } catch (Exception e8) {
                    if (iRequestListener != null) {
                        iRequestListener.onUnknowException(e8);
                        f.b("openSDK_LOG.HttpUtils", "OpenApi requestAsync onUnknowException", e8);
                    }
                }
            }
        }.start();
    }

    private static void a(Context context, QQToken qQToken, String str) {
        if (str.indexOf("add_share") > -1 || str.indexOf("upload_pic") > -1 || str.indexOf("add_topic") > -1 || str.indexOf("set_user_face") > -1 || str.indexOf("add_t") > -1 || str.indexOf("add_pic_t") > -1 || str.indexOf("add_pic_url") > -1 || str.indexOf("add_video") > -1) {
            a.a(context, qQToken, "requireApi", str);
        }
    }

    public static int getErrorCodeFromException(IOException iOException) {
        if (iOException instanceof CharConversionException) {
            return -20;
        }
        if (iOException instanceof MalformedInputException) {
            return -21;
        }
        if (iOException instanceof UnmappableCharacterException) {
            return -22;
        }
        if (iOException instanceof HttpResponseException) {
            return -23;
        }
        if (iOException instanceof ClosedChannelException) {
            return -24;
        }
        if (iOException instanceof ConnectionClosedException) {
            return -25;
        }
        if (iOException instanceof EOFException) {
            return -26;
        }
        if (iOException instanceof FileLockInterruptionException) {
            return -27;
        }
        if (iOException instanceof FileNotFoundException) {
            return -28;
        }
        if (iOException instanceof HttpRetryException) {
            return -29;
        }
        if (iOException instanceof ConnectTimeoutException) {
            return -7;
        }
        if (iOException instanceof SocketTimeoutException) {
            return -8;
        }
        if (iOException instanceof InvalidPropertiesFormatException) {
            return -30;
        }
        if (iOException instanceof MalformedChunkCodingException) {
            return -31;
        }
        if (iOException instanceof MalformedURLException) {
            return -3;
        }
        if (iOException instanceof NoHttpResponseException) {
            return -32;
        }
        if (iOException instanceof InvalidClassException) {
            return -33;
        }
        if (iOException instanceof InvalidObjectException) {
            return -34;
        }
        if (iOException instanceof NotActiveException) {
            return -35;
        }
        if (iOException instanceof NotSerializableException) {
            return -36;
        }
        if (iOException instanceof OptionalDataException) {
            return -37;
        }
        if (iOException instanceof StreamCorruptedException) {
            return -38;
        }
        if (iOException instanceof WriteAbortedException) {
            return -39;
        }
        if (iOException instanceof ProtocolException) {
            return -40;
        }
        if (iOException instanceof SSLHandshakeException) {
            return -41;
        }
        if (iOException instanceof SSLKeyException) {
            return -42;
        }
        if (iOException instanceof SSLPeerUnverifiedException) {
            return -43;
        }
        if (iOException instanceof SSLProtocolException) {
            return -44;
        }
        if (iOException instanceof BindException) {
            return -45;
        }
        if (iOException instanceof ConnectException) {
            return -46;
        }
        if (iOException instanceof NoRouteToHostException) {
            return -47;
        }
        if (iOException instanceof PortUnreachableException) {
            return -48;
        }
        if (iOException instanceof SyncFailedException) {
            return -49;
        }
        if (iOException instanceof UTFDataFormatException) {
            return -50;
        }
        if (iOException instanceof UnknownHostException) {
            return -51;
        }
        if (iOException instanceof UnknownServiceException) {
            return -52;
        }
        if (iOException instanceof UnsupportedEncodingException) {
            return -53;
        }
        if (iOException instanceof ZipException) {
            return -54;
        }
        return -2;
    }

    public static Util.Statistic openUrl2(Context context, String str, String str2, Bundle bundle) throws MalformedURLException, IOException, NetworkUnavailableException, HttpStatusException {
        Bundle bundle2;
        HttpGet httpGet;
        int i;
        String str3;
        ConnectivityManager connectivityManager;
        NetworkInfo activeNetworkInfo;
        if (context == null || (connectivityManager = (ConnectivityManager) context.getSystemService("connectivity")) == null || ((activeNetworkInfo = connectivityManager.getActiveNetworkInfo()) != null && activeNetworkInfo.isAvailable())) {
            if (bundle != null) {
                bundle2 = new Bundle(bundle);
            } else {
                bundle2 = new Bundle();
            }
            String string = bundle2.getString("appid_for_getting_config");
            bundle2.remove("appid_for_getting_config");
            HttpClient httpClient = getHttpClient(context, string, str);
            if (str2.equals("GET")) {
                String encodeUrl = encodeUrl(bundle2);
                i = 0 + encodeUrl.length();
                f.a("openSDK_LOG.HttpUtils", "-->openUrl2 before url =" + str);
                if (str.indexOf("?") == -1) {
                    str3 = str + "?";
                } else {
                    str3 = str + com.alipay.sdk.sys.a.b;
                }
                f.a("openSDK_LOG.HttpUtils", "-->openUrl2 encodedParam =" + encodeUrl + " -- url = " + str3);
                HttpGet httpGet2 = new HttpGet(str3 + encodeUrl);
                httpGet2.addHeader(Headers.HEAD_KEY_ACCEPT_ENCODING, "gzip");
                httpGet = httpGet2;
            } else if (str2.equals("POST")) {
                HttpPost httpPost = new HttpPost(str);
                httpPost.addHeader(Headers.HEAD_KEY_ACCEPT_ENCODING, "gzip");
                Bundle bundle3 = new Bundle();
                for (String str4 : bundle2.keySet()) {
                    Object obj = bundle2.get(str4);
                    if (obj instanceof byte[]) {
                        bundle3.putByteArray(str4, (byte[]) obj);
                    }
                }
                if (!bundle2.containsKey("method")) {
                    bundle2.putString("method", str2);
                }
                httpPost.setHeader("Content-Type", "multipart/form-data; boundary=3i2ndDfv2rTHiSisAbouNdArYfORhtTPEefj3q2f");
                httpPost.setHeader("Connection", HTTP.CONN_KEEP_ALIVE);
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                byteArrayOutputStream.write(Util.getBytesUTF8("--3i2ndDfv2rTHiSisAbouNdArYfORhtTPEefj3q2f\r\n"));
                byteArrayOutputStream.write(Util.getBytesUTF8(encodePostBody(bundle2, "3i2ndDfv2rTHiSisAbouNdArYfORhtTPEefj3q2f")));
                if (!bundle3.isEmpty()) {
                    int size = bundle3.size();
                    byteArrayOutputStream.write(Util.getBytesUTF8("\r\n--3i2ndDfv2rTHiSisAbouNdArYfORhtTPEefj3q2f\r\n"));
                    int i2 = -1;
                    for (String str5 : bundle3.keySet()) {
                        i2++;
                        byteArrayOutputStream.write(Util.getBytesUTF8("Content-Disposition: form-data; name=\"" + str5 + "\"; filename=\"" + str5 + "\"\r\n"));
                        byteArrayOutputStream.write(Util.getBytesUTF8("Content-Type: content/unknown\r\n\r\n"));
                        byte[] byteArray = bundle3.getByteArray(str5);
                        if (byteArray != null) {
                            byteArrayOutputStream.write(byteArray);
                        }
                        if (i2 < size - 1) {
                            byteArrayOutputStream.write(Util.getBytesUTF8("\r\n--3i2ndDfv2rTHiSisAbouNdArYfORhtTPEefj3q2f\r\n"));
                        }
                    }
                }
                byteArrayOutputStream.write(Util.getBytesUTF8("\r\n--3i2ndDfv2rTHiSisAbouNdArYfORhtTPEefj3q2f--\r\n"));
                byte[] byteArray2 = byteArrayOutputStream.toByteArray();
                i = byteArray2.length + 0;
                byteArrayOutputStream.close();
                httpPost.setEntity(new ByteArrayEntity(byteArray2));
                httpGet = httpPost;
            } else {
                httpGet = null;
                i = 0;
            }
            HttpResponse execute = httpClient.execute(httpGet);
            int statusCode = execute.getStatusLine().getStatusCode();
            if (statusCode == 200) {
                return new Util.Statistic(a(execute), i);
            }
            throw new HttpStatusException(HttpStatusException.ERROR_INFO + statusCode);
        }
        throw new NetworkUnavailableException(NetworkUnavailableException.ERROR_INFO);
    }

    private static String a(HttpResponse httpResponse) throws IllegalStateException, IOException {
        InputStream content = httpResponse.getEntity().getContent();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        Header firstHeader = httpResponse.getFirstHeader("Content-Encoding");
        InputStream gZIPInputStream = (firstHeader == null || firstHeader.getValue().toLowerCase().indexOf("gzip") <= -1) ? content : new GZIPInputStream(content);
        byte[] bArr = new byte[512];
        while (true) {
            int read = gZIPInputStream.read(bArr);
            if (read != -1) {
                byteArrayOutputStream.write(bArr, 0, read);
            } else {
                String str = new String(byteArrayOutputStream.toByteArray(), "UTF-8");
                gZIPInputStream.close();
                return str;
            }
        }
    }

    public static HttpClient getHttpClient(Context context, String str, String str2) {
        int i;
        int i2 = 0;
        OpenConfig openConfig = null;
        SchemeRegistry schemeRegistry = new SchemeRegistry();
        schemeRegistry.register(new Scheme(HttpHost.DEFAULT_SCHEME_NAME, PlainSocketFactory.getSocketFactory(), 80));
        if (Build.VERSION.SDK_INT < 16) {
            try {
                KeyStore instance = KeyStore.getInstance(KeyStore.getDefaultType());
                instance.load(null, null);
                CustomSSLSocketFactory customSSLSocketFactory = new CustomSSLSocketFactory(instance);
                customSSLSocketFactory.setHostnameVerifier(SSLSocketFactory.STRICT_HOSTNAME_VERIFIER);
                schemeRegistry.register(new Scheme(b.a, customSSLSocketFactory, 443));
            } catch (Exception e) {
                schemeRegistry.register(new Scheme(b.a, SSLSocketFactory.getSocketFactory(), 443));
            }
        } else {
            schemeRegistry.register(new Scheme(b.a, SSLSocketFactory.getSocketFactory(), 443));
        }
        BasicHttpParams basicHttpParams = new BasicHttpParams();
        if (context != null) {
            openConfig = OpenConfig.getInstance(context, str);
        }
        if (openConfig != null) {
            i = openConfig.getInt("Common_HttpConnectionTimeout");
            i2 = openConfig.getInt("Common_SocketConnectionTimeout");
        } else {
            i = 0;
        }
        if (i == 0) {
            i = 15000;
        }
        if (i2 == 0) {
            i2 = 30000;
        }
        HttpConnectionParams.setConnectionTimeout(basicHttpParams, i);
        HttpConnectionParams.setSoTimeout(basicHttpParams, i2);
        HttpProtocolParams.setVersion(basicHttpParams, HttpVersion.HTTP_1_1);
        HttpProtocolParams.setContentCharset(basicHttpParams, "UTF-8");
        HttpProtocolParams.setUserAgent(basicHttpParams, "AndroidSDK_" + Build.VERSION.SDK + "_" + Build.DEVICE + "_" + Build.VERSION.RELEASE);
        DefaultHttpClient defaultHttpClient = new DefaultHttpClient(new ThreadSafeClientConnManager(basicHttpParams, schemeRegistry), basicHttpParams);
        NetworkProxy proxy = getProxy(context);
        if (proxy != null) {
            defaultHttpClient.getParams().setParameter(ConnRoutePNames.DEFAULT_PROXY, new HttpHost(proxy.host, proxy.port));
        }
        return defaultHttpClient;
    }

    public static String encodeUrl(Bundle bundle) {
        if (bundle == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        boolean z = true;
        for (String str : bundle.keySet()) {
            Object obj = bundle.get(str);
            if ((obj instanceof String) || (obj instanceof String[])) {
                if (obj instanceof String[]) {
                    if (z) {
                        z = false;
                    } else {
                        sb.append(com.alipay.sdk.sys.a.b);
                    }
                    sb.append(URLEncoder.encode(str) + "=");
                    String[] stringArray = bundle.getStringArray(str);
                    if (stringArray != null) {
                        for (int i = 0; i < stringArray.length; i++) {
                            if (i == 0) {
                                sb.append(URLEncoder.encode(stringArray[i]));
                            } else {
                                sb.append(URLEncoder.encode("," + stringArray[i]));
                            }
                        }
                    }
                } else {
                    if (z) {
                        z = false;
                    } else {
                        sb.append(com.alipay.sdk.sys.a.b);
                    }
                    sb.append(URLEncoder.encode(str) + "=" + URLEncoder.encode(bundle.getString(str)));
                }
            }
        }
        return sb.toString();
    }

    public static String encodePostBody(Bundle bundle, String str) {
        if (bundle == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        int size = bundle.size();
        int i = -1;
        for (String str2 : bundle.keySet()) {
            int i2 = i + 1;
            Object obj = bundle.get(str2);
            if (!(obj instanceof String)) {
                i = i2;
            } else {
                sb.append("Content-Disposition: form-data; name=\"" + str2 + "\"\r\n\r\n" + ((String) obj));
                if (i2 < size - 1) {
                    sb.append("\r\n--" + str + "\r\n");
                }
                i = i2;
            }
        }
        return sb.toString();
    }

    public static NetworkProxy getProxy(Context context) {
        ConnectivityManager connectivityManager;
        NetworkInfo activeNetworkInfo;
        if (!(context == null || (connectivityManager = (ConnectivityManager) context.getSystemService("connectivity")) == null || (activeNetworkInfo = connectivityManager.getActiveNetworkInfo()) == null)) {
            if (activeNetworkInfo.getType() == 0) {
                String b = b(context);
                int a = a(context);
                if (!TextUtils.isEmpty(b) && a >= 0) {
                    return new NetworkProxy(b, a);
                }
            }
            return null;
        }
        return null;
    }

    /* compiled from: ProGuard */
    /* loaded from: classes2.dex */
    public static class NetworkProxy {
        public final String host;
        public final int port;

        private NetworkProxy(String str, int i) {
            this.host = str;
            this.port = i;
        }
    }

    private static int a(Context context) {
        if (Build.VERSION.SDK_INT >= 11) {
            String property = System.getProperty("http.proxyPort");
            if (TextUtils.isEmpty(property)) {
                return -1;
            }
            try {
                return Integer.parseInt(property);
            } catch (NumberFormatException e) {
                return -1;
            }
        } else if (context == null) {
            return Proxy.getDefaultPort();
        } else {
            int port = Proxy.getPort(context);
            if (port < 0) {
                return Proxy.getDefaultPort();
            }
            return port;
        }
    }

    private static String b(Context context) {
        if (Build.VERSION.SDK_INT >= 11) {
            return System.getProperty("http.proxyHost");
        }
        if (context == null) {
            return Proxy.getDefaultHost();
        }
        String host = Proxy.getHost(context);
        if (TextUtils.isEmpty(host)) {
            return Proxy.getDefaultHost();
        }
        return host;
    }

    /* compiled from: ProGuard */
    /* loaded from: classes2.dex */
    public static class CustomSSLSocketFactory extends SSLSocketFactory {
        private final SSLContext a = SSLContext.getInstance("TLS");

        public CustomSSLSocketFactory(KeyStore keyStore) throws NoSuchAlgorithmException, KeyManagementException, KeyStoreException, UnrecoverableKeyException {
            super(keyStore);
            MyX509TrustManager myX509TrustManager;
            try {
                myX509TrustManager = new MyX509TrustManager();
            } catch (Exception e) {
                myX509TrustManager = null;
            }
            this.a.init(null, new TrustManager[]{myX509TrustManager}, null);
        }

        @Override // org.apache.http.conn.ssl.SSLSocketFactory, org.apache.http.conn.scheme.LayeredSocketFactory
        public Socket createSocket(Socket socket, String str, int i, boolean z) throws IOException, UnknownHostException {
            return this.a.getSocketFactory().createSocket(socket, str, i, z);
        }

        @Override // org.apache.http.conn.ssl.SSLSocketFactory, org.apache.http.conn.scheme.SocketFactory
        public Socket createSocket() throws IOException {
            return this.a.getSocketFactory().createSocket();
        }
    }

    /* compiled from: ProGuard */
    /* loaded from: classes2.dex */
    public static class MyX509TrustManager implements X509TrustManager {
        X509TrustManager a;

        MyX509TrustManager() throws Exception {
            KeyStore keyStore;
            FileInputStream fileInputStream;
            Throwable th;
            FileInputStream fileInputStream2;
            TrustManager[] trustManagers;
            try {
                keyStore = KeyStore.getInstance("JKS");
            } catch (Exception e) {
                keyStore = null;
            }
            TrustManager[] trustManagerArr = new TrustManager[0];
            if (keyStore != null) {
                try {
                    fileInputStream2 = new FileInputStream("trustedCerts");
                } catch (Throwable th2) {
                    fileInputStream = null;
                    th = th2;
                }
                try {
                    keyStore.load(fileInputStream2, "passphrase".toCharArray());
                    TrustManagerFactory instance = TrustManagerFactory.getInstance("SunX509", "SunJSSE");
                    instance.init(keyStore);
                    trustManagers = instance.getTrustManagers();
                    if (fileInputStream2 != null) {
                        fileInputStream2.close();
                    }
                } catch (Throwable th3) {
                    th = th3;
                    fileInputStream = fileInputStream2;
                    if (fileInputStream != null) {
                        fileInputStream.close();
                    }
                    throw th;
                }
            } else {
                TrustManagerFactory instance2 = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
                instance2.init((KeyStore) null);
                trustManagers = instance2.getTrustManagers();
            }
            for (int i = 0; i < trustManagers.length; i++) {
                if (trustManagers[i] instanceof X509TrustManager) {
                    this.a = (X509TrustManager) trustManagers[i];
                    return;
                }
            }
            throw new Exception("Couldn't initialize");
        }

        @Override // javax.net.ssl.X509TrustManager
        public void checkClientTrusted(X509Certificate[] x509CertificateArr, String str) throws CertificateException {
            this.a.checkClientTrusted(x509CertificateArr, str);
        }

        @Override // javax.net.ssl.X509TrustManager
        public void checkServerTrusted(X509Certificate[] x509CertificateArr, String str) throws CertificateException {
            this.a.checkServerTrusted(x509CertificateArr, str);
        }

        @Override // javax.net.ssl.X509TrustManager
        public X509Certificate[] getAcceptedIssuers() {
            return this.a.getAcceptedIssuers();
        }
    }
}
