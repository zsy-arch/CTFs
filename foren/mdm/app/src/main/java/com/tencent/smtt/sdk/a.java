package com.tencent.smtt.sdk;

/* loaded from: classes2.dex */
public class a {
    private static int b = 0;
    public static int a = 600;

    /* JADX WARN: Code restructure failed: missing block: B:12:0x0028, code lost:
        r1 = r1.substring(r3 + "MemTotal:".length()).trim();
     */
    /* JADX WARN: Code restructure failed: missing block: B:13:0x0037, code lost:
        if (r1 == null) goto L_0x005c;
     */
    /* JADX WARN: Code restructure failed: missing block: B:15:0x003d, code lost:
        if (r1.length() == 0) goto L_0x005c;
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x0045, code lost:
        if (r1.contains("k") == false) goto L_0x005c;
     */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x0047, code lost:
        r0 = java.lang.Integer.parseInt(r1.substring(0, r1.indexOf("k")).trim()) / 1024;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static int a() {
        /*
            r0 = 0
            int r1 = com.tencent.smtt.sdk.a.b
            if (r1 <= 0) goto L_0x0008
            int r0 = com.tencent.smtt.sdk.a.b
        L_0x0007:
            return r0
        L_0x0008:
            java.lang.String r1 = "/proc/meminfo"
            java.lang.String r2 = ""
            r3 = 0
            java.io.FileReader r4 = new java.io.FileReader     // Catch: IOException -> 0x006b, Throwable -> 0x007b, all -> 0x008b
            r4.<init>(r1)     // Catch: IOException -> 0x006b, Throwable -> 0x007b, all -> 0x008b
            java.io.BufferedReader r2 = new java.io.BufferedReader     // Catch: IOException -> 0x006b, Throwable -> 0x007b, all -> 0x008b
            r1 = 8192(0x2000, float:1.14794E-41)
            r2.<init>(r4, r1)     // Catch: IOException -> 0x006b, Throwable -> 0x007b, all -> 0x008b
        L_0x0019:
            java.lang.String r1 = r2.readLine()     // Catch: IOException -> 0x009c, Throwable -> 0x009a, all -> 0x0098
            if (r1 == 0) goto L_0x005c
            java.lang.String r3 = "MemTotal:"
            int r3 = r1.indexOf(r3)     // Catch: IOException -> 0x009c, Throwable -> 0x009a, all -> 0x0098
            r4 = -1
            if (r4 == r3) goto L_0x0019
            java.lang.String r4 = "MemTotal:"
            int r4 = r4.length()     // Catch: IOException -> 0x009c, Throwable -> 0x009a, all -> 0x0098
            int r3 = r3 + r4
            java.lang.String r1 = r1.substring(r3)     // Catch: IOException -> 0x009c, Throwable -> 0x009a, all -> 0x0098
            java.lang.String r1 = r1.trim()     // Catch: IOException -> 0x009c, Throwable -> 0x009a, all -> 0x0098
            if (r1 == 0) goto L_0x005c
            int r3 = r1.length()     // Catch: IOException -> 0x009c, Throwable -> 0x009a, all -> 0x0098
            if (r3 == 0) goto L_0x005c
            java.lang.String r3 = "k"
            boolean r3 = r1.contains(r3)     // Catch: IOException -> 0x009c, Throwable -> 0x009a, all -> 0x0098
            if (r3 == 0) goto L_0x005c
            r3 = 0
            java.lang.String r4 = "k"
            int r4 = r1.indexOf(r4)     // Catch: IOException -> 0x009c, Throwable -> 0x009a, all -> 0x0098
            java.lang.String r1 = r1.substring(r3, r4)     // Catch: IOException -> 0x009c, Throwable -> 0x009a, all -> 0x0098
            java.lang.String r1 = r1.trim()     // Catch: IOException -> 0x009c, Throwable -> 0x009a, all -> 0x0098
            int r1 = java.lang.Integer.parseInt(r1)     // Catch: IOException -> 0x009c, Throwable -> 0x009a, all -> 0x0098
            int r0 = r1 / 1024
        L_0x005c:
            if (r2 == 0) goto L_0x0061
            r2.close()     // Catch: IOException -> 0x0066
        L_0x0061:
            com.tencent.smtt.sdk.a.b = r0
            int r0 = com.tencent.smtt.sdk.a.b
            goto L_0x0007
        L_0x0066:
            r1 = move-exception
            r1.printStackTrace()
            goto L_0x0061
        L_0x006b:
            r1 = move-exception
            r2 = r3
        L_0x006d:
            r1.printStackTrace()     // Catch: all -> 0x0098
            if (r2 == 0) goto L_0x0061
            r2.close()     // Catch: IOException -> 0x0076
            goto L_0x0061
        L_0x0076:
            r1 = move-exception
            r1.printStackTrace()
            goto L_0x0061
        L_0x007b:
            r1 = move-exception
            r2 = r3
        L_0x007d:
            r1.printStackTrace()     // Catch: all -> 0x0098
            if (r2 == 0) goto L_0x0061
            r2.close()     // Catch: IOException -> 0x0086
            goto L_0x0061
        L_0x0086:
            r1 = move-exception
            r1.printStackTrace()
            goto L_0x0061
        L_0x008b:
            r0 = move-exception
            r2 = r3
        L_0x008d:
            if (r2 == 0) goto L_0x0092
            r2.close()     // Catch: IOException -> 0x0093
        L_0x0092:
            throw r0
        L_0x0093:
            r1 = move-exception
            r1.printStackTrace()
            goto L_0x0092
        L_0x0098:
            r0 = move-exception
            goto L_0x008d
        L_0x009a:
            r1 = move-exception
            goto L_0x007d
        L_0x009c:
            r1 = move-exception
            goto L_0x006d
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.sdk.a.a():int");
    }
}
