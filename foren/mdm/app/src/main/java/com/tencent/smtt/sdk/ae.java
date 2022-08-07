package com.tencent.smtt.sdk;

import android.content.Context;
import java.io.File;
import java.io.IOException;
import java.util.Properties;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public class ae {
    private static ae a = null;
    private static Context b = null;

    private ae() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static ae a(Context context) {
        if (a == null) {
            synchronized (ae.class) {
                if (a == null) {
                    a = new ae();
                }
            }
        }
        b = context.getApplicationContext();
        return a;
    }

    /* JADX WARN: Removed duplicated region for block: B:40:0x003d A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private java.util.Properties e() {
        /*
            r5 = this;
            r1 = 0
            java.io.File r0 = r5.a()     // Catch: Exception -> 0x0027, all -> 0x003a
            java.util.Properties r2 = new java.util.Properties     // Catch: Exception -> 0x0027, all -> 0x003a
            r2.<init>()     // Catch: Exception -> 0x0027, all -> 0x003a
            if (r0 == 0) goto L_0x0059
            if (r2 == 0) goto L_0x0059
            java.io.FileInputStream r3 = new java.io.FileInputStream     // Catch: Exception -> 0x004e, all -> 0x003a
            r3.<init>(r0)     // Catch: Exception -> 0x004e, all -> 0x003a
            java.io.BufferedInputStream r0 = new java.io.BufferedInputStream     // Catch: Exception -> 0x004e, all -> 0x003a
            r0.<init>(r3)     // Catch: Exception -> 0x004e, all -> 0x003a
            r2.load(r0)     // Catch: Exception -> 0x0054, all -> 0x0046
        L_0x001b:
            if (r0 == 0) goto L_0x0020
            r0.close()     // Catch: IOException -> 0x0022
        L_0x0020:
            r0 = r2
        L_0x0021:
            return r0
        L_0x0022:
            r0 = move-exception
            r0.printStackTrace()
            goto L_0x0020
        L_0x0027:
            r0 = move-exception
            r2 = r1
            r4 = r1
            r1 = r0
            r0 = r4
        L_0x002c:
            r1.printStackTrace()     // Catch: all -> 0x004b
            if (r2 == 0) goto L_0x0021
            r2.close()     // Catch: IOException -> 0x0035
            goto L_0x0021
        L_0x0035:
            r1 = move-exception
            r1.printStackTrace()
            goto L_0x0021
        L_0x003a:
            r0 = move-exception
        L_0x003b:
            if (r1 == 0) goto L_0x0040
            r1.close()     // Catch: IOException -> 0x0041
        L_0x0040:
            throw r0
        L_0x0041:
            r1 = move-exception
            r1.printStackTrace()
            goto L_0x0040
        L_0x0046:
            r1 = move-exception
            r4 = r1
            r1 = r0
            r0 = r4
            goto L_0x003b
        L_0x004b:
            r0 = move-exception
            r1 = r2
            goto L_0x003b
        L_0x004e:
            r0 = move-exception
            r4 = r0
            r0 = r2
            r2 = r1
            r1 = r4
            goto L_0x002c
        L_0x0054:
            r1 = move-exception
            r4 = r2
            r2 = r0
            r0 = r4
            goto L_0x002c
        L_0x0059:
            r0 = r1
            goto L_0x001b
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.sdk.ae.e():java.util.Properties");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public File a() {
        aj.a();
        File file = new File(aj.j(b), "tbscoreinstall.txt");
        if (file == null || file.exists()) {
            return file;
        }
        try {
            file.createNewFile();
            return file;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a(int i) {
        a("dexopt_retry_num", i);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a(int i, int i2) {
        a("copy_core_ver", i);
        a("copy_status", i2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a(String str) {
        a("install_apk_path", str);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a(String str, int i) {
        a(str, String.valueOf(i));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Removed duplicated region for block: B:35:0x0051 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void a(java.lang.String r5, java.lang.String r6) {
        /*
            r4 = this;
            r2 = 0
            java.util.Properties r0 = r4.e()     // Catch: Exception -> 0x003d, all -> 0x004d
            if (r0 == 0) goto L_0x0032
            r0.setProperty(r5, r6)     // Catch: Exception -> 0x003d, all -> 0x004d
            java.io.File r3 = r4.a()     // Catch: Exception -> 0x003d, all -> 0x004d
            if (r3 == 0) goto L_0x0032
            java.io.FileOutputStream r1 = new java.io.FileOutputStream     // Catch: Exception -> 0x003d, all -> 0x004d
            r1.<init>(r3)     // Catch: Exception -> 0x003d, all -> 0x004d
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch: Exception -> 0x005c, all -> 0x005a
            r2.<init>()     // Catch: Exception -> 0x005c, all -> 0x005a
            java.lang.String r3 = "update "
            java.lang.StringBuilder r2 = r2.append(r3)     // Catch: Exception -> 0x005c, all -> 0x005a
            java.lang.StringBuilder r2 = r2.append(r5)     // Catch: Exception -> 0x005c, all -> 0x005a
            java.lang.String r3 = " and status!"
            java.lang.StringBuilder r2 = r2.append(r3)     // Catch: Exception -> 0x005c, all -> 0x005a
            java.lang.String r2 = r2.toString()     // Catch: Exception -> 0x005c, all -> 0x005a
            r0.store(r1, r2)     // Catch: Exception -> 0x005c, all -> 0x005a
            r2 = r1
        L_0x0032:
            if (r2 == 0) goto L_0x0037
            r2.close()     // Catch: IOException -> 0x0038
        L_0x0037:
            return
        L_0x0038:
            r0 = move-exception
            r0.printStackTrace()
            goto L_0x0037
        L_0x003d:
            r0 = move-exception
            r1 = r2
        L_0x003f:
            r0.printStackTrace()     // Catch: all -> 0x005a
            if (r1 == 0) goto L_0x0037
            r1.close()     // Catch: IOException -> 0x0048
            goto L_0x0037
        L_0x0048:
            r0 = move-exception
            r0.printStackTrace()
            goto L_0x0037
        L_0x004d:
            r0 = move-exception
            r1 = r2
        L_0x004f:
            if (r1 == 0) goto L_0x0054
            r1.close()     // Catch: IOException -> 0x0055
        L_0x0054:
            throw r0
        L_0x0055:
            r1 = move-exception
            r1.printStackTrace()
            goto L_0x0054
        L_0x005a:
            r0 = move-exception
            goto L_0x004f
        L_0x005c:
            r0 = move-exception
            goto L_0x003f
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.sdk.ae.a(java.lang.String, java.lang.String):void");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int b() {
        return c("install_core_ver");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int b(String str) {
        Properties e = e();
        if (e == null || e.getProperty(str) == null) {
            return -1;
        }
        return Integer.parseInt(e.getProperty(str));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void b(int i) {
        a("unzip_retry_num", i);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void b(int i, int i2) {
        a("install_core_ver", i);
        a("install_status", i2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int c() {
        return b("install_status");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int c(String str) {
        Properties e = e();
        if (e == null || e.getProperty(str) == null) {
            return 0;
        }
        return Integer.parseInt(e.getProperty(str));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void c(int i) {
        a("incrupdate_status", i);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int d() {
        return b("incrupdate_status");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public String d(String str) {
        Properties e = e();
        if (e == null || e.getProperty(str) == null) {
            return null;
        }
        return e.getProperty(str);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void d(int i) {
        a("unlzma_status", i);
    }
}
