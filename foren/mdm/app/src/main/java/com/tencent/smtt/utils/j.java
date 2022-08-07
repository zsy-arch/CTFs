package com.tencent.smtt.utils;

import com.tencent.smtt.utils.e;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public final class j extends Thread {
    final /* synthetic */ String a;
    final /* synthetic */ e.a b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public j(String str, e.a aVar) {
        this.a = str;
        this.b = aVar;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v2, types: [java.io.OutputStream, java.io.InputStream] */
    /* JADX WARN: Type inference failed for: r1v3, types: [java.io.OutputStream] */
    /* JADX WARN: Type inference failed for: r1v4 */
    /* JADX WARN: Type inference failed for: r1v5, types: [java.io.OutputStream, java.io.FileOutputStream] */
    /* JADX WARN: Type inference failed for: r1v6 */
    /* JADX WARN: Unknown variable types count: 1 */
    @Override // java.lang.Thread, java.lang.Runnable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void run() {
        /*
            r7 = this;
            r1 = 0
            r3 = 0
            java.net.URL r0 = new java.net.URL     // Catch: Exception -> 0x0085, all -> 0x0064
            java.lang.String r2 = "http://soft.tbs.imtt.qq.com/17421/tbs_res_imtt_tbs_DebugPlugin_DebugPlugin.tbs"
            r0.<init>(r2)     // Catch: Exception -> 0x0085, all -> 0x0064
            java.net.URLConnection r0 = r0.openConnection()     // Catch: Exception -> 0x0085, all -> 0x0064
            java.net.HttpURLConnection r0 = (java.net.HttpURLConnection) r0     // Catch: Exception -> 0x0085, all -> 0x0064
            int r4 = r0.getContentLength()     // Catch: Exception -> 0x0085, all -> 0x0064
            r2 = 5000(0x1388, float:7.006E-42)
            r0.setConnectTimeout(r2)     // Catch: Exception -> 0x0085, all -> 0x0064
            r0.connect()     // Catch: Exception -> 0x0085, all -> 0x0064
            java.io.InputStream r2 = r0.getInputStream()     // Catch: Exception -> 0x0085, all -> 0x0064
            java.io.File r0 = new java.io.File     // Catch: Exception -> 0x0043, all -> 0x0083
            java.lang.String r5 = r7.a     // Catch: Exception -> 0x0043, all -> 0x0083
            r0.<init>(r5)     // Catch: Exception -> 0x0043, all -> 0x0083
            java.io.FileOutputStream r1 = com.tencent.smtt.utils.k.d(r0)     // Catch: Exception -> 0x0043, all -> 0x0083
            r0 = 8192(0x2000, float:1.14794E-41)
            byte[] r5 = new byte[r0]     // Catch: Exception -> 0x0043, all -> 0x0083
            r0 = r3
        L_0x002f:
            int r3 = r2.read(r5)     // Catch: Exception -> 0x0043, all -> 0x0083
            if (r3 <= 0) goto L_0x0053
            int r0 = r0 + r3
            r6 = 0
            r1.write(r5, r6, r3)     // Catch: Exception -> 0x0043, all -> 0x0083
            int r3 = r0 * 100
            int r3 = r3 / r4
            com.tencent.smtt.utils.e$a r6 = r7.b     // Catch: Exception -> 0x0043, all -> 0x0083
            r6.a(r3)     // Catch: Exception -> 0x0043, all -> 0x0083
            goto L_0x002f
        L_0x0043:
            r0 = move-exception
        L_0x0044:
            r0.printStackTrace()     // Catch: all -> 0x0083
            com.tencent.smtt.utils.e$a r3 = r7.b     // Catch: all -> 0x0083
            r3.a(r0)     // Catch: all -> 0x0083
            r2.close()     // Catch: Exception -> 0x0077
        L_0x004f:
            r1.close()     // Catch: Exception -> 0x007c
        L_0x0052:
            return
        L_0x0053:
            com.tencent.smtt.utils.e$a r0 = r7.b     // Catch: Exception -> 0x0043, all -> 0x0083
            r0.a()     // Catch: Exception -> 0x0043, all -> 0x0083
            r2.close()     // Catch: Exception -> 0x007e
        L_0x005b:
            r1.close()     // Catch: Exception -> 0x005f
            goto L_0x0052
        L_0x005f:
            r0 = move-exception
        L_0x0060:
            r0.printStackTrace()
            goto L_0x0052
        L_0x0064:
            r0 = move-exception
            r2 = r1
        L_0x0066:
            r2.close()     // Catch: Exception -> 0x006d
        L_0x0069:
            r1.close()     // Catch: Exception -> 0x0072
        L_0x006c:
            throw r0
        L_0x006d:
            r2 = move-exception
            r2.printStackTrace()
            goto L_0x0069
        L_0x0072:
            r1 = move-exception
            r1.printStackTrace()
            goto L_0x006c
        L_0x0077:
            r0 = move-exception
            r0.printStackTrace()
            goto L_0x004f
        L_0x007c:
            r0 = move-exception
            goto L_0x0060
        L_0x007e:
            r0 = move-exception
            r0.printStackTrace()
            goto L_0x005b
        L_0x0083:
            r0 = move-exception
            goto L_0x0066
        L_0x0085:
            r0 = move-exception
            r2 = r1
            goto L_0x0044
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.utils.j.run():void");
    }
}
