package com.tencent.map.b;

import android.net.Proxy;
import com.hy.http.HttpEntity;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/* compiled from: ProGuard */
/* loaded from: classes2.dex */
public final class q {
    private static int a = 0;
    private static boolean b;

    /* JADX WARN: Removed duplicated region for block: B:36:0x009f A[Catch: IOException -> 0x00e6, TryCatch #2 {IOException -> 0x00e6, blocks: (B:34:0x009a, B:35:0x009c, B:36:0x009f, B:39:0x00ab, B:53:0x00e0), top: B:67:0x009a }] */
    /* JADX WARN: Removed duplicated region for block: B:51:0x00dc  */
    /* JADX WARN: Removed duplicated region for block: B:53:0x00e0 A[Catch: IOException -> 0x00e6, TRY_ENTER, TRY_LEAVE, TryCatch #2 {IOException -> 0x00e6, blocks: (B:34:0x009a, B:35:0x009c, B:36:0x009f, B:39:0x00ab, B:53:0x00e0), top: B:67:0x009a }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static java.net.HttpURLConnection a(java.lang.String r8, boolean r9) {
        /*
            Method dump skipped, instructions count: 254
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.map.b.q.a(java.lang.String, boolean):java.net.HttpURLConnection");
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:49:0x00b2  */
    /* JADX WARN: Removed duplicated region for block: B:51:0x00b7  */
    /* JADX WARN: Type inference failed for: r2v1 */
    /* JADX WARN: Type inference failed for: r2v14 */
    /* JADX WARN: Type inference failed for: r2v4 */
    /* JADX WARN: Type inference failed for: r3v0, types: [boolean] */
    /* JADX WARN: Type inference failed for: r3v1, types: [java.net.HttpURLConnection] */
    /* JADX WARN: Type inference failed for: r3v3 */
    /* JADX WARN: Type inference failed for: r3v5 */
    /* JADX WARN: Type inference failed for: r3v6, types: [java.net.HttpURLConnection] */
    /* JADX WARN: Unknown variable types count: 1 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static com.tencent.map.b.n a(boolean r6, java.lang.String r7, java.lang.String r8, java.lang.String r9, byte[] r10, boolean r11, boolean r12) throws java.lang.Exception {
        /*
            Method dump skipped, instructions count: 290
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.map.b.q.a(boolean, java.lang.String, java.lang.String, java.lang.String, byte[], boolean, boolean):com.tencent.map.b.n");
    }

    /* JADX WARN: Removed duplicated region for block: B:37:0x005f A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static com.tencent.map.b.n a(java.net.HttpURLConnection r11, boolean r12) throws java.io.IOException {
        /*
            r2 = 1
            r0 = 0
            r1 = 0
            com.tencent.map.b.n r5 = new com.tencent.map.b.n     // Catch: all -> 0x0083
            r5.<init>()     // Catch: all -> 0x0083
            java.lang.String r6 = r11.getContentType()     // Catch: all -> 0x0083
            java.lang.String r3 = "GBK"
            if (r6 == 0) goto L_0x001a
            java.lang.String r4 = ";"
            java.lang.String[] r7 = r6.split(r4)     // Catch: all -> 0x0083
            int r8 = r7.length     // Catch: all -> 0x0083
            r4 = r0
        L_0x0018:
            if (r4 < r8) goto L_0x0063
        L_0x001a:
            r5.b = r3     // Catch: all -> 0x0083
            if (r12 == 0) goto L_0x0030
            if (r6 == 0) goto L_0x0081
            java.lang.String r3 = "vnd.wap.wml"
            boolean r3 = r6.contains(r3)     // Catch: all -> 0x0083
            if (r3 == 0) goto L_0x0081
        L_0x0028:
            if (r2 == 0) goto L_0x0030
            r11.disconnect()     // Catch: all -> 0x0083
            r11.connect()     // Catch: all -> 0x0083
        L_0x0030:
            java.io.InputStream r1 = r11.getInputStream()     // Catch: all -> 0x0083
            if (r1 == 0) goto L_0x005d
            r2 = 0
            byte[] r2 = new byte[r2]     // Catch: all -> 0x0083
            r5.a = r2     // Catch: all -> 0x0083
            r2 = 1024(0x400, float:1.435E-42)
            byte[] r2 = new byte[r2]     // Catch: all -> 0x0083
        L_0x003f:
            int r3 = r1.read(r2)     // Catch: all -> 0x0083
            if (r3 <= 0) goto L_0x005b
            int r0 = r0 + r3
            byte[] r4 = new byte[r0]     // Catch: all -> 0x0083
            byte[] r6 = r5.a     // Catch: all -> 0x0083
            r7 = 0
            r8 = 0
            byte[] r9 = r5.a     // Catch: all -> 0x0083
            int r9 = r9.length     // Catch: all -> 0x0083
            java.lang.System.arraycopy(r6, r7, r4, r8, r9)     // Catch: all -> 0x0083
            r6 = 0
            byte[] r7 = r5.a     // Catch: all -> 0x0083
            int r7 = r7.length     // Catch: all -> 0x0083
            java.lang.System.arraycopy(r2, r6, r4, r7, r3)     // Catch: all -> 0x0083
            r5.a = r4     // Catch: all -> 0x0083
        L_0x005b:
            if (r3 > 0) goto L_0x003f
        L_0x005d:
            if (r1 == 0) goto L_0x0062
            r1.close()     // Catch: IOException -> 0x008a
        L_0x0062:
            return r5
        L_0x0063:
            r9 = r7[r4]     // Catch: all -> 0x0083
            java.lang.String r10 = "charset"
            boolean r10 = r9.contains(r10)     // Catch: all -> 0x0083
            if (r10 == 0) goto L_0x007e
            java.lang.String r4 = "="
            java.lang.String[] r4 = r9.split(r4)     // Catch: all -> 0x0083
            int r7 = r4.length     // Catch: all -> 0x0083
            if (r7 <= r2) goto L_0x001a
            r3 = 1
            r3 = r4[r3]     // Catch: all -> 0x0083
            java.lang.String r3 = r3.trim()     // Catch: all -> 0x0083
            goto L_0x001a
        L_0x007e:
            int r4 = r4 + 1
            goto L_0x0018
        L_0x0081:
            r2 = r0
            goto L_0x0028
        L_0x0083:
            r0 = move-exception
            if (r1 == 0) goto L_0x0089
            r1.close()     // Catch: IOException -> 0x008c
        L_0x0089:
            throw r0
        L_0x008a:
            r0 = move-exception
            goto L_0x0062
        L_0x008c:
            r1 = move-exception
            goto L_0x0089
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.map.b.q.a(java.net.HttpURLConnection, boolean):com.tencent.map.b.n");
    }

    private static void a(int i) {
        if (a != i) {
            a = i;
        }
    }

    private static boolean a(HttpURLConnection httpURLConnection) throws IOException {
        InputStream inputStream = null;
        try {
            inputStream = httpURLConnection.getInputStream();
            if (!httpURLConnection.getContentType().equals(HttpEntity.TEXT_HTML)) {
                if (inputStream != null) {
                    inputStream.close();
                }
                return false;
            }
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            while (inputStream.available() > 0) {
                byteArrayOutputStream.write(inputStream.read());
            }
            boolean equals = new String(byteArrayOutputStream.toByteArray()).trim().equals("1");
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
        }
    }

    private static HttpURLConnection a(URL url, String str) throws IOException {
        String str2;
        int i = 80;
        String defaultHost = Proxy.getDefaultHost();
        int defaultPort = Proxy.getDefaultPort();
        if (defaultPort == -1) {
            defaultPort = 80;
        }
        String host = url.getHost();
        int port = url.getPort();
        if (port != -1) {
            i = port;
        }
        if (str.indexOf(String.valueOf(host) + ":" + i) != -1) {
            str2 = str.replaceFirst(String.valueOf(host) + ":" + i, String.valueOf(defaultHost) + ":" + defaultPort);
        } else {
            str2 = str.replaceFirst(host, String.valueOf(defaultHost) + ":" + defaultPort);
        }
        try {
            HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(str2).openConnection();
            httpURLConnection.setRequestProperty("X-Online-Host", String.valueOf(host) + ":" + i);
            return httpURLConnection;
        } catch (MalformedURLException e) {
            return null;
        }
    }
}
