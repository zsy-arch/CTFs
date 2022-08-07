package com.tencent.smtt.sdk;

import android.content.Context;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes.dex */
public abstract class TbsBaseConfig {
    public static final String TAG = "TbsBaseConfig";

    /* renamed from: a  reason: collision with root package name */
    public Map<String, String> f1200a;

    /* renamed from: b  reason: collision with root package name */
    public Context f1201b;

    public static File a(Context context, String str) {
        m.a();
        File s = m.s(context);
        if (s == null) {
            return null;
        }
        File file = new File(s, str);
        if (file.exists()) {
            return file;
        }
        try {
            file.createNewFile();
            return file;
        } catch (IOException e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public void clear() {
        this.f1200a.clear();
        commit();
    }

    public synchronized void commit() {
        writeTbsDownloadInfo();
    }

    public abstract String getConfigFileName();

    public void init(Context context) {
        this.f1200a = new HashMap();
        this.f1201b = context.getApplicationContext();
        if (this.f1201b == null) {
            this.f1201b = context;
        }
        refreshSyncMap(context);
    }

    /* JADX WARN: Removed duplicated region for block: B:35:0x006b A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public synchronized void refreshSyncMap(android.content.Context r7) {
        /*
            r6 = this;
            monitor-enter(r6)
            r7 = 0
            android.content.Context r0 = r6.f1201b     // Catch: Throwable -> 0x0055, all -> 0x0050
            java.lang.String r1 = r6.getConfigFileName()     // Catch: Throwable -> 0x0055, all -> 0x0050
            java.io.File r0 = a(r0, r1)     // Catch: Throwable -> 0x0055, all -> 0x0050
            if (r0 != 0) goto L_0x0010
            monitor-exit(r6)
            return
        L_0x0010:
            java.util.Map<java.lang.String, java.lang.String> r1 = r6.f1200a     // Catch: Throwable -> 0x0055, all -> 0x0050
            r1.clear()     // Catch: Throwable -> 0x0055, all -> 0x0050
            java.io.FileInputStream r1 = new java.io.FileInputStream     // Catch: Throwable -> 0x0055, all -> 0x0050
            r1.<init>(r0)     // Catch: Throwable -> 0x0055, all -> 0x0050
            java.io.BufferedInputStream r0 = new java.io.BufferedInputStream     // Catch: Throwable -> 0x0055, all -> 0x0050
            r0.<init>(r1)     // Catch: Throwable -> 0x0055, all -> 0x0050
            java.util.Properties r7 = new java.util.Properties     // Catch: Throwable -> 0x004e, all -> 0x0068
            r7.<init>()     // Catch: Throwable -> 0x004e, all -> 0x0068
            r7.load(r0)     // Catch: Throwable -> 0x004e, all -> 0x0068
            java.util.Set r1 = r7.stringPropertyNames()     // Catch: Throwable -> 0x004e, all -> 0x0068
            java.util.Iterator r1 = r1.iterator()     // Catch: Throwable -> 0x004e, all -> 0x0068
        L_0x002f:
            boolean r2 = r1.hasNext()     // Catch: Throwable -> 0x004e, all -> 0x0068
            if (r2 == 0) goto L_0x0045
            java.lang.Object r2 = r1.next()     // Catch: Throwable -> 0x004e, all -> 0x0068
            java.lang.String r2 = (java.lang.String) r2     // Catch: Throwable -> 0x004e, all -> 0x0068
            java.util.Map<java.lang.String, java.lang.String> r3 = r6.f1200a     // Catch: Throwable -> 0x004e, all -> 0x0068
            java.lang.String r4 = r7.getProperty(r2)     // Catch: Throwable -> 0x004e, all -> 0x0068
            r3.put(r2, r4)     // Catch: Throwable -> 0x004e, all -> 0x0068
            goto L_0x002f
        L_0x0045:
            r0.close()     // Catch: Exception -> 0x0049, all -> 0x0062
            goto L_0x0066
        L_0x0049:
            r7 = move-exception
        L_0x004a:
            r7.printStackTrace()     // Catch: all -> 0x0062
            goto L_0x0066
        L_0x004e:
            r7 = move-exception
            goto L_0x0059
        L_0x0050:
            r0 = move-exception
            r5 = r0
            r0 = r7
            r7 = r5
            goto L_0x0069
        L_0x0055:
            r0 = move-exception
            r5 = r0
            r0 = r7
            r7 = r5
        L_0x0059:
            r7.printStackTrace()     // Catch: all -> 0x0068
            if (r0 == 0) goto L_0x0066
            r0.close()     // Catch: Exception -> 0x0064, all -> 0x0062
            goto L_0x0066
        L_0x0062:
            r7 = move-exception
            goto L_0x0074
        L_0x0064:
            r7 = move-exception
            goto L_0x004a
        L_0x0066:
            monitor-exit(r6)
            return
        L_0x0068:
            r7 = move-exception
        L_0x0069:
            if (r0 == 0) goto L_0x0073
            r0.close()     // Catch: Exception -> 0x006f, all -> 0x0062
            goto L_0x0073
        L_0x006f:
            r0 = move-exception
            r0.printStackTrace()     // Catch: all -> 0x0062
        L_0x0073:
            throw r7     // Catch: all -> 0x0062
        L_0x0074:
            monitor-exit(r6)
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.sdk.TbsBaseConfig.refreshSyncMap(android.content.Context):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:60:0x00ce A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:64:0x00d8 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public synchronized void writeTbsDownloadInfo() {
        /*
            Method dump skipped, instructions count: 228
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.sdk.TbsBaseConfig.writeTbsDownloadInfo():void");
    }
}
