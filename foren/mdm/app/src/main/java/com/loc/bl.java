package com.loc;

import android.os.Build;
import com.alipay.sdk.sys.a;
import com.amap.api.maps.AMapException;
import com.loc.bi;
import com.yolanda.nohttp.Headers;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.Proxy;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.net.UnknownHostException;
import java.util.Map;
import java.util.UUID;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;

/* compiled from: HttpUrlUtil.java */
/* loaded from: classes2.dex */
public final class bl {
    private static bm a;
    private int b;
    private int c;
    private boolean d;
    private SSLContext e;
    private Proxy f;
    private volatile boolean g;
    private long h;
    private long i;
    private String j;
    private bi.a k;
    private HostnameVerifier l;

    public bl(int i, int i2, Proxy proxy) {
        this(i, i2, proxy, false);
    }

    public bl(int i, int i2, Proxy proxy, boolean z) {
        this(i, i2, proxy, z, (byte) 0);
    }

    private bl(int i, int i2, Proxy proxy, boolean z, byte b) {
        this.g = false;
        this.h = -1L;
        this.i = 0L;
        this.l = new HostnameVerifier() { // from class: com.loc.bl.1
            @Override // javax.net.ssl.HostnameVerifier
            public final boolean verify(String str, SSLSession sSLSession) {
                HostnameVerifier defaultHostnameVerifier = HttpsURLConnection.getDefaultHostnameVerifier();
                return defaultHostnameVerifier.verify("*.amap.com", sSLSession) || defaultHostnameVerifier.verify("*.apilocate.amap.com", sSLSession);
            }
        };
        this.b = i;
        this.c = i2;
        this.f = proxy;
        this.d = z;
        this.k = null;
        try {
            this.j = UUID.randomUUID().toString().replaceAll("-", "").toLowerCase();
        } catch (Throwable th) {
            w.a(th, "HttpUrlUtil", "initCSID");
        }
        if (z) {
            try {
                SSLContext instance = SSLContext.getInstance("TLS");
                instance.init(null, null, null);
                this.e = instance;
            } catch (Throwable th2) {
                w.a(th2, "HttpUtil", "HttpUtil");
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:103:0x0080 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:107:0x0071 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:113:0x0076 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:119:0x007b A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:99:0x006c A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private com.loc.bo a(java.net.HttpURLConnection r10) throws com.loc.j, java.io.IOException {
        /*
            Method dump skipped, instructions count: 391
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.loc.bl.a(java.net.HttpURLConnection):com.loc.bo");
    }

    public static String a(Map<String, String> map) {
        if (map == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            if (value == null) {
                value = "";
            }
            if (sb.length() > 0) {
                sb.append(a.b);
            }
            sb.append(URLEncoder.encode(key));
            sb.append("=");
            sb.append(URLEncoder.encode(value));
        }
        return sb.toString();
    }

    private HttpURLConnection a(String str, Map<String, String> map, boolean z) throws IOException {
        HttpURLConnection httpURLConnection;
        n.a();
        URLConnection uRLConnection = null;
        URL url = new URL(str);
        if (this.k != null) {
            bi.a aVar = this.k;
            Proxy proxy = this.f;
            uRLConnection = aVar.a();
        }
        if (uRLConnection == null) {
            uRLConnection = this.f != null ? url.openConnection(this.f) : url.openConnection();
        }
        if (this.d) {
            httpURLConnection = (HttpsURLConnection) uRLConnection;
            ((HttpsURLConnection) httpURLConnection).setSSLSocketFactory(this.e.getSocketFactory());
            ((HttpsURLConnection) httpURLConnection).setHostnameVerifier(this.l);
        } else {
            httpURLConnection = (HttpURLConnection) uRLConnection;
        }
        if (Build.VERSION.SDK != null && Build.VERSION.SDK_INT > 13) {
            httpURLConnection.setRequestProperty("Connection", Headers.HEAD_VALUE_CONNECTION_CLOSE);
        }
        a(map, httpURLConnection);
        if (z) {
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setUseCaches(false);
            httpURLConnection.setDoInput(true);
            httpURLConnection.setDoOutput(true);
        } else {
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.setDoInput(true);
        }
        return httpURLConnection;
    }

    public static void a(bm bmVar) {
        a = bmVar;
    }

    private void a(Map<String, String> map, HttpURLConnection httpURLConnection) {
        if (map != null) {
            for (String str : map.keySet()) {
                httpURLConnection.addRequestProperty(str, map.get(str));
            }
        }
        try {
            httpURLConnection.addRequestProperty("csid", this.j);
        } catch (Throwable th) {
            w.a(th, "HttpUrlUtil", "addHeaders");
        }
        httpURLConnection.setConnectTimeout(this.b);
        httpURLConnection.setReadTimeout(this.c);
    }

    public final bo a(String str, Map<String, String> map, byte[] bArr) throws j {
        try {
            HttpURLConnection a2 = a(str, map, true);
            if (bArr != null && bArr.length > 0) {
                DataOutputStream dataOutputStream = new DataOutputStream(a2.getOutputStream());
                dataOutputStream.write(bArr);
                dataOutputStream.close();
            }
            return a(a2);
        } catch (j e) {
            w.a(e, "HttpUrlUtil", "makePostReqeust");
            throw e;
        } catch (InterruptedIOException e2) {
            throw new j("未知的错误");
        } catch (ConnectException e3) {
            e3.printStackTrace();
            throw new j(AMapException.ERROR_CONNECTION);
        } catch (MalformedURLException e4) {
            e4.printStackTrace();
            throw new j("url异常 - MalformedURLException");
        } catch (SocketException e5) {
            e5.printStackTrace();
            throw new j(AMapException.ERROR_SOCKET);
        } catch (SocketTimeoutException e6) {
            e6.printStackTrace();
            throw new j("socket 连接超时 - SocketTimeoutException");
        } catch (UnknownHostException e7) {
            e7.printStackTrace();
            throw new j("未知主机 - UnKnowHostException");
        } catch (IOException e8) {
            e8.printStackTrace();
            throw new j("IO 操作异常 - IOException");
        } catch (Throwable th) {
            w.a(th, "HttpUrlUtil", "makePostReqeust");
            throw new j("未知的错误");
        }
    }

    public final void a() {
        this.i = 0L;
    }

    /* JADX WARN: Removed duplicated region for block: B:90:0x00f0 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:94:0x00eb A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void a(java.lang.String r11, java.util.Map<java.lang.String, java.lang.String> r12, java.util.Map<java.lang.String, java.lang.String> r13, com.loc.bk.a r14) {
        /*
            Method dump skipped, instructions count: 367
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.loc.bl.a(java.lang.String, java.util.Map, java.util.Map, com.loc.bk$a):void");
    }

    public final void b() {
        this.h = -1L;
    }
}
