package com.baidu.mobstat;

/* loaded from: classes.dex */
public class as {
    private static k a;

    /* JADX WARN: Removed duplicated region for block: B:11:0x0024 A[Catch: all -> 0x0040, TRY_ENTER, TryCatch #0 {, blocks: (B:4:0x0003, B:6:0x000c, B:8:0x0014, B:9:0x001d, B:11:0x0024, B:12:0x002e, B:16:0x003b), top: B:23:0x0003 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static synchronized com.baidu.mobstat.k a(android.content.Context r5) {
        /*
            java.lang.Class<com.baidu.mobstat.as> r2 = com.baidu.mobstat.as.class
            monitor-enter(r2)
            java.lang.String r0 = "getBPStretegyController begin"
            com.baidu.mobstat.bb.a(r0)     // Catch: all -> 0x0040
            com.baidu.mobstat.k r1 = com.baidu.mobstat.as.a     // Catch: all -> 0x0040
            if (r1 != 0) goto L_0x0048
            java.lang.String r0 = "com.baidu.bottom.remote.BPStretegyController2"
            java.lang.Class r0 = com.baidu.mobstat.av.a(r5, r0)     // Catch: Exception -> 0x003a, all -> 0x0040
            if (r0 == 0) goto L_0x0048
            java.lang.Object r3 = r0.newInstance()     // Catch: Exception -> 0x003a, all -> 0x0040
            com.baidu.mobstat.au r0 = new com.baidu.mobstat.au     // Catch: Exception -> 0x003a, all -> 0x0040
            r0.<init>(r3)     // Catch: Exception -> 0x003a, all -> 0x0040
            java.lang.String r1 = "Get BPStretegyController load remote class v2"
            com.baidu.mobstat.bb.a(r1)     // Catch: Exception -> 0x0043, all -> 0x0040
        L_0x0022:
            if (r0 != 0) goto L_0x002e
            com.baidu.mobstat.at r0 = new com.baidu.mobstat.at     // Catch: all -> 0x0040
            r0.<init>()     // Catch: all -> 0x0040
            java.lang.String r1 = "Get BPStretegyController load local class"
            com.baidu.mobstat.bb.a(r1)     // Catch: all -> 0x0040
        L_0x002e:
            com.baidu.mobstat.as.a = r0     // Catch: all -> 0x0040
            com.baidu.mobstat.av.a(r5, r0)     // Catch: all -> 0x0040
            java.lang.String r1 = "getBPStretegyController end"
            com.baidu.mobstat.bb.a(r1)     // Catch: all -> 0x0040
            monitor-exit(r2)
            return r0
        L_0x003a:
            r0 = move-exception
        L_0x003b:
            com.baidu.mobstat.bb.a(r0)     // Catch: all -> 0x0040
            r0 = r1
            goto L_0x0022
        L_0x0040:
            r0 = move-exception
            monitor-exit(r2)
            throw r0
        L_0x0043:
            r1 = move-exception
            r4 = r1
            r1 = r0
            r0 = r4
            goto L_0x003b
        L_0x0048:
            r0 = r1
            goto L_0x0022
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.mobstat.as.a(android.content.Context):com.baidu.mobstat.k");
    }

    public static synchronized void a() {
        synchronized (as.class) {
            a = null;
        }
    }
}
