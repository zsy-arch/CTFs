package com.amap.api.col;

import android.os.Build;
import com.alipay.sdk.sys.a;
import com.amap.api.col.ia;
import com.amap.api.maps.AMapException;
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
public class id {
    private static ie a;
    private int b;
    private int c;
    private boolean d;
    private SSLContext e;
    private Proxy f;
    private volatile boolean g;
    private long h;
    private long i;
    private String j;
    private ia.a k;
    private HostnameVerifier l;

    private void b() {
        try {
            this.j = UUID.randomUUID().toString().replaceAll("-", "").toLowerCase();
        } catch (Throwable th) {
            go.a(th, "HttpUrlUtil", "initCSID");
        }
    }

    public static void a(ie ieVar) {
        a = ieVar;
    }

    public id(int i, int i2, Proxy proxy, boolean z) {
        this(i, i2, proxy, z, null);
    }

    id(int i, int i2, Proxy proxy, boolean z, ia.a aVar) {
        this.g = false;
        this.h = -1L;
        this.i = 0L;
        this.l = new HostnameVerifier() { // from class: com.amap.api.col.id.1
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
        b();
        if (z) {
            try {
                SSLContext instance = SSLContext.getInstance("TLS");
                instance.init(null, null, null);
                this.e = instance;
            } catch (Throwable th) {
                go.a(th, "HttpUtil", "HttpUtil");
            }
        }
    }

    public id(int i, int i2, Proxy proxy) {
        this(i, i2, proxy, false);
    }

    public void a() {
        this.g = true;
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
    public void a(java.lang.String r11, java.util.Map<java.lang.String, java.lang.String> r12, java.util.Map<java.lang.String, java.lang.String> r13, com.amap.api.col.ic.a r14) {
        /*
            Method dump skipped, instructions count: 403
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amap.api.col.id.a(java.lang.String, java.util.Map, java.util.Map, com.amap.api.col.ic$a):void");
    }

    public ii a(String str, Map<String, String> map, Map<String, String> map2) throws fz {
        HttpURLConnection httpURLConnection;
        Throwable th;
        fz fzVar;
        try {
            try {
            } catch (Throwable th2) {
                th = th2;
            }
        } catch (Throwable th3) {
            httpURLConnection = null;
            th = th3;
        }
        try {
            try {
                try {
                    try {
                        String a2 = a(map2);
                        StringBuffer stringBuffer = new StringBuffer();
                        stringBuffer.append(str);
                        if (a2 != null) {
                            stringBuffer.append("?").append(a2);
                        }
                        HttpURLConnection a3 = a(stringBuffer.toString(), map, false);
                        try {
                            ii a4 = a(a3);
                            if (a3 != null) {
                                try {
                                    a3.disconnect();
                                } catch (Throwable th4) {
                                    go.a(th4, "HttpUrlUtil", "makeGetRequest");
                                }
                            }
                            return a4;
                        } catch (fz e) {
                            fzVar = e;
                            throw fzVar;
                        } catch (Throwable th5) {
                            th = th5;
                            th.printStackTrace();
                            throw new fz("未知的错误");
                        }
                    } catch (ConnectException e2) {
                        throw new fz(AMapException.ERROR_CONNECTION);
                    } catch (MalformedURLException e3) {
                        throw new fz("url异常 - MalformedURLException");
                    }
                } catch (InterruptedIOException e4) {
                    throw new fz("未知的错误");
                } catch (UnknownHostException e5) {
                    throw new fz("未知主机 - UnKnowHostException");
                }
            } catch (SocketException e6) {
                throw new fz(AMapException.ERROR_SOCKET);
            } catch (SocketTimeoutException e7) {
                throw new fz("socket 连接超时 - SocketTimeoutException");
            } catch (IOException e8) {
                throw new fz("IO 操作异常 - IOException");
            }
        } catch (fz e9) {
            fzVar = e9;
        } catch (Throwable th6) {
            th = th6;
        }
    }

    public ii a(String str, Map<String, String> map, byte[] bArr) throws fz {
        HttpURLConnection httpURLConnection;
        try {
            httpURLConnection = null;
            try {
                try {
                    try {
                        try {
                            try {
                                httpURLConnection = a(str, map, true);
                                if (bArr != null && bArr.length > 0) {
                                    DataOutputStream dataOutputStream = new DataOutputStream(httpURLConnection.getOutputStream());
                                    dataOutputStream.write(bArr);
                                    dataOutputStream.close();
                                }
                                return a(httpURLConnection);
                            } catch (IOException e) {
                                e.printStackTrace();
                                throw new fz("IO 操作异常 - IOException");
                            }
                        } catch (fz e2) {
                            go.a(e2, "HttpUrlUtil", "makePostReqeust");
                            throw e2;
                        } catch (ConnectException e3) {
                            e3.printStackTrace();
                            throw new fz(AMapException.ERROR_CONNECTION);
                        }
                    } catch (InterruptedIOException e4) {
                        throw new fz("未知的错误");
                    } catch (SocketException e5) {
                        e5.printStackTrace();
                        throw new fz(AMapException.ERROR_SOCKET);
                    }
                } catch (UnknownHostException e6) {
                    e6.printStackTrace();
                    throw new fz("未知主机 - UnKnowHostException");
                } catch (Throwable th) {
                    go.a(th, "HttpUrlUtil", "makePostReqeust");
                    throw new fz("未知的错误");
                }
            } catch (MalformedURLException e7) {
                e7.printStackTrace();
                throw new fz("url异常 - MalformedURLException");
            } catch (SocketTimeoutException e8) {
                e8.printStackTrace();
                throw new fz("socket 连接超时 - SocketTimeoutException");
            }
        } finally {
            if (httpURLConnection != null) {
                try {
                    httpURLConnection.disconnect();
                } catch (Throwable th2) {
                    go.a(th2, "HttpUrlUtil", "makePostReqeust");
                }
            }
        }
    }

    HttpURLConnection a(String str, Map<String, String> map, boolean z) throws IOException {
        HttpURLConnection httpURLConnection;
        ge.b();
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

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:100:0x0080 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:109:0x0085 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:91:0x008a A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:93:0x007b A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Type inference failed for: r5v13, types: [java.util.zip.GZIPInputStream] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private com.amap.api.col.ii a(java.net.HttpURLConnection r11) throws com.amap.api.col.fz, java.io.IOException {
        /*
            Method dump skipped, instructions count: 371
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amap.api.col.id.a(java.net.HttpURLConnection):com.amap.api.col.ii");
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
            go.a(th, "HttpUrlUtil", "addHeaders");
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
