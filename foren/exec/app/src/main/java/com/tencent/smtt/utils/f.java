package com.tencent.smtt.utils;

import android.os.Build;
import java.io.BufferedOutputStream;
import java.io.Closeable;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.GZIPOutputStream;

/* loaded from: classes.dex */
public class f {

    /* loaded from: classes.dex */
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
        try {
            String str2 = str + (z ? h.a().c() : g.a().b());
            try {
                bArr = z ? h.a().a(bArr) : g.a().a(bArr);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            if (bArr == null) {
                return null;
            }
            HashMap hashMap = new HashMap();
            hashMap.put("Content-Type", "application/x-www-form-urlencoded");
            hashMap.put("Content-Length", String.valueOf(bArr.length));
            HttpURLConnection a2 = a(str2, hashMap);
            if (a2 == null) {
                return null;
            }
            b(a2, bArr);
            return a(a2, aVar, z);
        } catch (Exception e3) {
            e3.printStackTrace();
            return null;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:68:0x00b4 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:74:0x00af A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.lang.String a(java.net.HttpURLConnection r5, com.tencent.smtt.utils.f.a r6, boolean r7) {
        /*
            r0 = 0
            int r1 = r5.getResponseCode()     // Catch: Throwable -> 0x0099, all -> 0x0096
            if (r6 == 0) goto L_0x000a
            r6.a(r1)     // Catch: Throwable -> 0x0099, all -> 0x0096
        L_0x000a:
            r6 = 200(0xc8, float:2.8E-43)
            if (r1 != r6) goto L_0x0089
            java.io.InputStream r6 = r5.getInputStream()     // Catch: Throwable -> 0x0099, all -> 0x0096
            java.lang.String r5 = r5.getContentEncoding()     // Catch: Throwable -> 0x0099, all -> 0x0096
            if (r5 == 0) goto L_0x0026
            java.lang.String r1 = "gzip"
            boolean r1 = r5.equalsIgnoreCase(r1)     // Catch: Throwable -> 0x0099, all -> 0x0096
            if (r1 == 0) goto L_0x0026
            java.util.zip.GZIPInputStream r5 = new java.util.zip.GZIPInputStream     // Catch: Throwable -> 0x0099, all -> 0x0096
            r5.<init>(r6)     // Catch: Throwable -> 0x0099, all -> 0x0096
            goto L_0x003d
        L_0x0026:
            if (r5 == 0) goto L_0x003c
            java.lang.String r1 = "deflate"
            boolean r5 = r5.equalsIgnoreCase(r1)     // Catch: Throwable -> 0x0099, all -> 0x0096
            if (r5 == 0) goto L_0x003c
            java.util.zip.InflaterInputStream r5 = new java.util.zip.InflaterInputStream     // Catch: Throwable -> 0x0099, all -> 0x0096
            java.util.zip.Inflater r1 = new java.util.zip.Inflater     // Catch: Throwable -> 0x0099, all -> 0x0096
            r2 = 1
            r1.<init>(r2)     // Catch: Throwable -> 0x0099, all -> 0x0096
            r5.<init>(r6, r1)     // Catch: Throwable -> 0x0099, all -> 0x0096
            goto L_0x003d
        L_0x003c:
            r5 = r6
        L_0x003d:
            java.io.ByteArrayOutputStream r6 = new java.io.ByteArrayOutputStream     // Catch: Throwable -> 0x0083, all -> 0x007d
            r6.<init>()     // Catch: Throwable -> 0x0083, all -> 0x007d
            r1 = 128(0x80, float:1.794E-43)
            byte[] r1 = new byte[r1]     // Catch: Throwable -> 0x0077, all -> 0x0073
        L_0x0046:
            int r2 = r5.read(r1)     // Catch: Throwable -> 0x0077, all -> 0x0073
            r3 = -1
            if (r2 == r3) goto L_0x0052
            r3 = 0
            r6.write(r1, r3, r2)     // Catch: Throwable -> 0x0077, all -> 0x0073
            goto L_0x0046
        L_0x0052:
            if (r7 == 0) goto L_0x0061
            java.lang.String r7 = new java.lang.String     // Catch: Throwable -> 0x0077, all -> 0x0073
            byte[] r1 = r6.toByteArray()     // Catch: Throwable -> 0x0077, all -> 0x0073
            java.lang.String r2 = "utf-8"
            r7.<init>(r1, r2)     // Catch: Throwable -> 0x0077, all -> 0x0073
        L_0x005f:
            r0 = r7
            goto L_0x008b
        L_0x0061:
            java.lang.String r7 = new java.lang.String     // Catch: Throwable -> 0x0077, all -> 0x0073
            com.tencent.smtt.utils.g r1 = com.tencent.smtt.utils.g.a()     // Catch: Throwable -> 0x0077, all -> 0x0073
            byte[] r2 = r6.toByteArray()     // Catch: Throwable -> 0x0077, all -> 0x0073
            byte[] r1 = r1.c(r2)     // Catch: Throwable -> 0x0077, all -> 0x0073
            r7.<init>(r1)     // Catch: Throwable -> 0x0077, all -> 0x0073
            goto L_0x005f
        L_0x0073:
            r7 = move-exception
            r0 = r5
            r5 = r7
            goto L_0x00ad
        L_0x0077:
            r7 = move-exception
            r4 = r6
            r6 = r5
            r5 = r7
            r7 = r4
            goto L_0x009c
        L_0x007d:
            r6 = move-exception
            r4 = r0
            r0 = r5
            r5 = r6
            r6 = r4
            goto L_0x00ad
        L_0x0083:
            r6 = move-exception
            r7 = r0
            r4 = r6
            r6 = r5
            r5 = r4
            goto L_0x009c
        L_0x0089:
            r5 = r0
            r6 = r5
        L_0x008b:
            if (r5 == 0) goto L_0x0090
            r5.close()     // Catch: Exception -> 0x0090
        L_0x0090:
            if (r6 == 0) goto L_0x00a9
            r6.close()     // Catch: Exception -> 0x00a9
            goto L_0x00a9
        L_0x0096:
            r5 = move-exception
            r6 = r0
            goto L_0x00ad
        L_0x0099:
            r5 = move-exception
            r6 = r0
            r7 = r6
        L_0x009c:
            r5.printStackTrace()     // Catch: all -> 0x00aa
            if (r6 == 0) goto L_0x00a4
            r6.close()     // Catch: Exception -> 0x00a4
        L_0x00a4:
            if (r7 == 0) goto L_0x00a9
            r7.close()     // Catch: Exception -> 0x00a9
        L_0x00a9:
            return r0
        L_0x00aa:
            r5 = move-exception
            r0 = r6
            r6 = r7
        L_0x00ad:
            if (r0 == 0) goto L_0x00b2
            r0.close()     // Catch: Exception -> 0x00b2
        L_0x00b2:
            if (r6 == 0) goto L_0x00b7
            r6.close()     // Catch: Exception -> 0x00b7
        L_0x00b7:
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.utils.f.a(java.net.HttpURLConnection, com.tencent.smtt.utils.f$a, boolean):java.lang.String");
    }

    public static HttpURLConnection a(String str, Map<String, String> map) {
        Exception e2;
        HttpURLConnection httpURLConnection = null;
        try {
            HttpURLConnection httpURLConnection2 = (HttpURLConnection) new URL(str).openConnection();
            try {
                httpURLConnection2.setRequestMethod("POST");
                httpURLConnection2.setDoOutput(true);
                httpURLConnection2.setDoInput(true);
                httpURLConnection2.setUseCaches(false);
                httpURLConnection2.setConnectTimeout(20000);
                int i = Build.VERSION.SDK_INT;
                httpURLConnection2.setRequestProperty("Connection", "close");
                for (Map.Entry<String, String> entry : map.entrySet()) {
                    httpURLConnection2.setRequestProperty(entry.getKey(), entry.getValue());
                }
                return httpURLConnection2;
            } catch (Exception e3) {
                e2 = e3;
                httpURLConnection = httpURLConnection2;
                e2.printStackTrace();
                return httpURLConnection;
            }
        } catch (Exception e4) {
            e2 = e4;
        }
    }

    public static void a(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (Exception unused) {
            }
        }
    }

    public static void b(HttpURLConnection httpURLConnection, byte[] bArr) {
        OutputStream outputStream;
        try {
            outputStream = null;
            try {
                outputStream = httpURLConnection.getOutputStream();
                outputStream.write(bArr);
                outputStream.flush();
            } catch (Exception e2) {
                e2.printStackTrace();
                if (outputStream == null) {
                    return;
                }
            }
            try {
                outputStream.close();
            } catch (Exception unused) {
            }
        } catch (Throwable th) {
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (Exception unused2) {
                }
            }
            throw th;
        }
    }

    public static void a(HttpURLConnection httpURLConnection, byte[] bArr) {
        Throwable th;
        GZIPOutputStream gZIPOutputStream;
        Exception e2;
        GZIPOutputStream gZIPOutputStream2;
        try {
            try {
                gZIPOutputStream = null;
                try {
                    gZIPOutputStream2 = new GZIPOutputStream(new BufferedOutputStream(httpURLConnection.getOutputStream(), 204800));
                } catch (Exception e3) {
                    e2 = e3;
                }
            } catch (Throwable th2) {
                th = th2;
            }
            try {
                gZIPOutputStream2.write(bArr);
                gZIPOutputStream2.flush();
                gZIPOutputStream2.close();
            } catch (Exception e4) {
                e2 = e4;
                gZIPOutputStream = gZIPOutputStream2;
                e2.printStackTrace();
                if (gZIPOutputStream != null) {
                    gZIPOutputStream.close();
                }
            } catch (Throwable th3) {
                th = th3;
                gZIPOutputStream = gZIPOutputStream2;
                if (gZIPOutputStream != null) {
                    try {
                        gZIPOutputStream.close();
                    } catch (Exception unused) {
                    }
                }
                throw th;
            }
        } catch (Exception unused2) {
        }
    }
}
