package com.amap.api.services.a;

import android.os.Build;
import com.alipay.sdk.sys.a;
import com.amap.api.maps.AMapException;
import com.amap.api.services.a.ct;
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
/* loaded from: classes.dex */
public class cw {
    private static cx a;
    private int b;
    private int c;
    private boolean d;
    private SSLContext e;
    private Proxy f;
    private volatile boolean g;
    private long h;
    private long i;
    private String j;
    private ct.a k;
    private HostnameVerifier l;

    private void a() {
        try {
            this.j = UUID.randomUUID().toString().replaceAll("-", "").toLowerCase();
        } catch (Throwable th) {
            bh.a(th, "HttpUrlUtil", "initCSID");
        }
    }

    public static void a(cx cxVar) {
        a = cxVar;
    }

    public cw(int i, int i2, Proxy proxy, boolean z) {
        this(i, i2, proxy, z, null);
    }

    cw(int i, int i2, Proxy proxy, boolean z, ct.a aVar) {
        this.g = false;
        this.h = -1L;
        this.i = 0L;
        this.l = new HostnameVerifier() { // from class: com.amap.api.services.a.cw.1
            @Override // javax.net.ssl.HostnameVerifier
            public boolean verify(String str, SSLSession sSLSession) {
                HostnameVerifier defaultHostnameVerifier = HttpsURLConnection.getDefaultHostnameVerifier();
                return defaultHostnameVerifier.verify("*.amap.com", sSLSession) || defaultHostnameVerifier.verify("*.apilocate.amap.com", sSLSession);
            }
        };
        this.b = i;
        this.c = i2;
        this.f = proxy;
        this.d = z;
        this.k = aVar;
        a();
        if (z) {
            try {
                SSLContext instance = SSLContext.getInstance("TLS");
                instance.init(null, null, null);
                this.e = instance;
            } catch (Throwable th) {
                bh.a(th, "HttpUtil", "HttpUtil");
            }
        }
    }

    public cw(int i, int i2, Proxy proxy) {
        this(i, i2, proxy, false);
    }

    public void a(long j) {
        this.i = j;
    }

    public void b(long j) {
        this.h = j;
    }

    /* JADX WARN: Removed duplicated region for block: B:102:0x011d A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:114:0x0118 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void a(java.lang.String r11, java.util.Map<java.lang.String, java.lang.String> r12, java.util.Map<java.lang.String, java.lang.String> r13, com.amap.api.services.a.cv.a r14) {
        /*
            Method dump skipped, instructions count: 403
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amap.api.services.a.cw.a(java.lang.String, java.util.Map, java.util.Map, com.amap.api.services.a.cv$a):void");
    }

    public db a(String str, Map<String, String> map, Map<String, String> map2) throws av {
        try {
            String a2 = a(map2);
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append(str);
            if (a2 != null) {
                stringBuffer.append("?").append(a2);
            }
            HttpURLConnection a3 = a(stringBuffer.toString(), map, false);
            a3.connect();
            return a(a3);
        } catch (av e) {
            throw e;
        } catch (ConnectException e2) {
            throw new av(AMapException.ERROR_CONNECTION);
        } catch (MalformedURLException e3) {
            throw new av("url异常 - MalformedURLException");
        } catch (SocketException e4) {
            throw new av(AMapException.ERROR_SOCKET);
        } catch (SocketTimeoutException e5) {
            throw new av("socket 连接超时 - SocketTimeoutException");
        } catch (InterruptedIOException e6) {
            throw new av("未知的错误");
        } catch (UnknownHostException e7) {
            throw new av("未知主机 - UnKnowHostException");
        } catch (IOException e8) {
            throw new av("IO 操作异常 - IOException");
        } catch (Throwable th) {
            th.printStackTrace();
            throw new av("未知的错误");
        }
    }

    public db a(String str, Map<String, String> map, byte[] bArr) throws av {
        try {
            HttpURLConnection a2 = a(str, map, true);
            if (bArr != null && bArr.length > 0) {
                DataOutputStream dataOutputStream = new DataOutputStream(a2.getOutputStream());
                dataOutputStream.write(bArr);
                dataOutputStream.close();
            }
            a2.connect();
            return a(a2);
        } catch (av e) {
            bh.a(e, "HttpUrlUtil", "makePostReqeust");
            throw e;
        } catch (SocketTimeoutException e2) {
            e2.printStackTrace();
            throw new av("socket 连接超时 - SocketTimeoutException");
        } catch (InterruptedIOException e3) {
            throw new av("未知的错误");
        } catch (ConnectException e4) {
            e4.printStackTrace();
            throw new av(AMapException.ERROR_CONNECTION);
        } catch (MalformedURLException e5) {
            e5.printStackTrace();
            throw new av("url异常 - MalformedURLException");
        } catch (SocketException e6) {
            e6.printStackTrace();
            throw new av(AMapException.ERROR_SOCKET);
        } catch (UnknownHostException e7) {
            e7.printStackTrace();
            throw new av("未知主机 - UnKnowHostException");
        } catch (IOException e8) {
            e8.printStackTrace();
            throw new av("IO 操作异常 - IOException");
        } catch (Throwable th) {
            bh.a(th, "HttpUrlUtil", "makePostReqeust");
            throw new av("未知的错误");
        }
    }

    HttpURLConnection a(String str, Map<String, String> map, boolean z) throws IOException {
        HttpURLConnection httpURLConnection;
        ba.a();
        URLConnection uRLConnection = null;
        URL url = new URL(str);
        if (this.k != null) {
            uRLConnection = this.k.a(this.f, url);
        }
        if (uRLConnection == null) {
            if (this.f != null) {
                uRLConnection = url.openConnection(this.f);
            } else {
                uRLConnection = url.openConnection();
            }
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

    /* JADX WARN: Removed duplicated region for block: B:101:0x006e A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:105:0x0082 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:109:0x0073 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:115:0x0078 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:99:0x007d A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private com.amap.api.services.a.db a(java.net.HttpURLConnection r10) throws com.amap.api.services.a.av, java.io.IOException {
        /*
            Method dump skipped, instructions count: 393
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amap.api.services.a.cw.a(java.net.HttpURLConnection):com.amap.api.services.a.db");
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
            bh.a(th, "HttpUrlUtil", "addHeaders");
        }
        httpURLConnection.setConnectTimeout(this.b);
        httpURLConnection.setReadTimeout(this.c);
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
}
