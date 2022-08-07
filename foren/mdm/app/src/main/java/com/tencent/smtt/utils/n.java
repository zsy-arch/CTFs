package com.tencent.smtt.utils;

import android.os.Build;
import com.yolanda.nohttp.Headers;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;
import java.util.zip.Inflater;
import java.util.zip.InflaterInputStream;

/* loaded from: classes2.dex */
public class n {

    /* loaded from: classes2.dex */
    public interface a {
        void a(int i);
    }

    public static String a(String str, Map<String, String> map, byte[] bArr, a aVar, boolean z) {
        HttpURLConnection a2;
        if (bArr == null || (a2 = a(str, map)) == null) {
            return null;
        }
        if (z) {
            a(a2, bArr);
        } else {
            b(a2, bArr);
        }
        return a(a2, aVar, false);
    }

    public static String a(String str, byte[] bArr, a aVar, boolean z) {
        byte[] bArr2;
        String str2;
        try {
            String str3 = str + (z ? q.a().c() : p.a().b());
            try {
                bArr2 = z ? q.a().a(bArr) : p.a().a(bArr);
            } catch (Exception e) {
                e.printStackTrace();
                bArr2 = bArr;
            }
            if (bArr2 == null) {
                return null;
            }
            HashMap hashMap = new HashMap();
            hashMap.put("Content-Type", "application/x-www-form-urlencoded");
            hashMap.put("Content-Length", String.valueOf(bArr2.length));
            HttpURLConnection a2 = a(str3, hashMap);
            if (a2 != null) {
                b(a2, bArr2);
                str2 = a(a2, aVar, z);
            } else {
                str2 = null;
            }
            return str2;
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    private static String a(HttpURLConnection httpURLConnection, a aVar, boolean z) {
        InputStream inputStream;
        InputStream inputStream2;
        Throwable th;
        InputStream inputStream3;
        Closeable closeable;
        String str;
        InputStream inputStream4 = null;
        try {
            int responseCode = httpURLConnection.getResponseCode();
            if (aVar != null) {
                aVar.a(responseCode);
            }
            if (responseCode == 200) {
                InputStream inputStream5 = httpURLConnection.getInputStream();
                String contentEncoding = httpURLConnection.getContentEncoding();
                inputStream3 = (contentEncoding == null || !contentEncoding.equalsIgnoreCase("gzip")) ? (contentEncoding == null || !contentEncoding.equalsIgnoreCase("deflate")) ? inputStream5 : new InflaterInputStream(inputStream5, new Inflater(true)) : new GZIPInputStream(inputStream5);
                try {
                    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                    try {
                        byte[] bArr = new byte[128];
                        while (true) {
                            int read = inputStream3.read(bArr);
                            if (read == -1) {
                                break;
                            }
                            byteArrayOutputStream.write(bArr, 0, read);
                        }
                        if (z) {
                            str = new String(byteArrayOutputStream.toByteArray(), "utf-8");
                            inputStream4 = inputStream3;
                            closeable = byteArrayOutputStream;
                        } else {
                            str = new String(p.a().c(byteArrayOutputStream.toByteArray()));
                            inputStream4 = inputStream3;
                            closeable = byteArrayOutputStream;
                        }
                    } catch (Throwable th2) {
                        th = th2;
                        inputStream4 = byteArrayOutputStream;
                        a(inputStream3);
                        a(inputStream4);
                        throw th;
                    }
                } catch (Throwable th3) {
                    th = th3;
                    inputStream = inputStream3;
                    inputStream2 = null;
                }
            } else {
                closeable = null;
                str = null;
            }
            a(inputStream4);
            a(closeable);
            return str;
        } catch (Throwable th4) {
            th = th4;
            inputStream2 = null;
            inputStream = null;
        }
    }

    private static HttpURLConnection a(String str, Map<String, String> map) {
        Exception e;
        HttpURLConnection httpURLConnection = null;
        try {
            httpURLConnection = (HttpURLConnection) new URL(str).openConnection();
            try {
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                httpURLConnection.setUseCaches(false);
                httpURLConnection.setConnectTimeout(20000);
                if (Build.VERSION.SDK_INT > 13) {
                    httpURLConnection.setRequestProperty("Connection", Headers.HEAD_VALUE_CONNECTION_CLOSE);
                } else {
                    httpURLConnection.setRequestProperty("http.keepAlive", "false");
                }
                for (Map.Entry<String, String> entry : map.entrySet()) {
                    httpURLConnection.setRequestProperty(entry.getKey(), entry.getValue());
                }
            } catch (Exception e2) {
                e = e2;
                e.printStackTrace();
                return httpURLConnection;
            }
        } catch (Exception e3) {
            e = e3;
        }
        return httpURLConnection;
    }

    private static void a(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (Exception e) {
            }
        }
    }

    private static void a(HttpURLConnection httpURLConnection, byte[] bArr) {
        GZIPOutputStream gZIPOutputStream;
        Exception e;
        try {
        } catch (Throwable th) {
            th = th;
        }
        try {
            gZIPOutputStream = new GZIPOutputStream(new BufferedOutputStream(httpURLConnection.getOutputStream(), 204800));
            try {
                gZIPOutputStream.write(bArr);
                gZIPOutputStream.flush();
                a(null);
                a(gZIPOutputStream);
            } catch (Exception e2) {
                e = e2;
                e.printStackTrace();
                a(null);
                a(gZIPOutputStream);
            }
        } catch (Exception e3) {
            e = e3;
            gZIPOutputStream = null;
        } catch (Throwable th2) {
            th = th2;
            gZIPOutputStream = null;
            a(null);
            a(gZIPOutputStream);
            throw th;
        }
    }

    private static void b(HttpURLConnection httpURLConnection, byte[] bArr) {
        try {
            OutputStream outputStream = httpURLConnection.getOutputStream();
            outputStream.write(bArr);
            outputStream.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
