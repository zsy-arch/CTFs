package com.baidu.mobstat;

import android.content.Context;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class bw implements Runnable {
    final /* synthetic */ Context a;
    final /* synthetic */ bs b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public bw(bs bsVar, Context context) {
        this.b = bsVar;
        this.a = context;
    }

    /* JADX WARN: Code restructure failed: missing block: B:6:0x0025, code lost:
        r4 = r7.b.b(r7.a, com.baidu.mobstat.cl.a(r7.a, r3));
     */
    @Override // java.lang.Runnable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void run() {
        /*
            r7 = this;
            com.baidu.mobstat.DataCore r0 = com.baidu.mobstat.DataCore.instance()     // Catch: Exception -> 0x003d
            android.content.Context r1 = r7.a     // Catch: Exception -> 0x003d
            r0.sendLogData(r1)     // Catch: Exception -> 0x003d
            android.content.Context r0 = r7.a     // Catch: Exception -> 0x003d
            java.io.File r0 = r0.getFilesDir()     // Catch: Exception -> 0x003d
            java.io.File[] r1 = r0.listFiles()     // Catch: Exception -> 0x003d
            int r2 = r1.length     // Catch: Exception -> 0x003d
            r0 = 0
        L_0x0015:
            if (r0 >= r2) goto L_0x0041
            r3 = r1[r0]     // Catch: Exception -> 0x003d
            java.lang.String r3 = r3.getName()     // Catch: Exception -> 0x003d
            java.lang.String r4 = "__send_data_"
            boolean r4 = r3.startsWith(r4)     // Catch: Exception -> 0x003d
            if (r4 == 0) goto L_0x003a
            android.content.Context r4 = r7.a     // Catch: Exception -> 0x003d
            java.lang.String r4 = com.baidu.mobstat.cl.a(r4, r3)     // Catch: Exception -> 0x003d
            com.baidu.mobstat.bs r5 = r7.b     // Catch: Exception -> 0x003d
            android.content.Context r6 = r7.a     // Catch: Exception -> 0x003d
            boolean r4 = com.baidu.mobstat.bs.a(r5, r6, r4)     // Catch: Exception -> 0x003d
            if (r4 == 0) goto L_0x003a
            android.content.Context r4 = r7.a     // Catch: Exception -> 0x003d
            com.baidu.mobstat.cl.b(r4, r3)     // Catch: Exception -> 0x003d
        L_0x003a:
            int r0 = r0 + 1
            goto L_0x0015
        L_0x003d:
            r0 = move-exception
            com.baidu.mobstat.cr.b(r0)
        L_0x0041:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.mobstat.bw.run():void");
    }
}
