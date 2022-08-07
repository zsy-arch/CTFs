package com.amap.api.col;

import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

/* compiled from: OfflineLocManager.java */
/* loaded from: classes.dex */
public class il {
    static int a = 1000;
    static boolean b = false;

    public static synchronized void a(int i, boolean z) {
        synchronized (il.class) {
            a = i;
            b = z;
        }
    }

    public static synchronized void a(final ik ikVar, final Context context) {
        synchronized (il.class) {
            gr.c().submit(new Runnable() { // from class: com.amap.api.col.il.1
                /* JADX WARN: Removed duplicated region for block: B:25:0x006b A[Catch: Throwable -> 0x0074, all -> 0x0064, TRY_ENTER, TRY_LEAVE, TryCatch #5 {, blocks: (B:7:0x0041, B:9:0x0046, B:10:0x0049, B:16:0x0056, B:18:0x005b, B:25:0x006b, B:27:0x0070, B:28:0x0073), top: B:36:0x0003 }] */
                /* JADX WARN: Removed duplicated region for block: B:27:0x0070 A[Catch: Throwable -> 0x0079, all -> 0x0064, TRY_ENTER, TRY_LEAVE, TryCatch #5 {, blocks: (B:7:0x0041, B:9:0x0046, B:10:0x0049, B:16:0x0056, B:18:0x005b, B:25:0x006b, B:27:0x0070, B:28:0x0073), top: B:36:0x0003 }] */
                @Override // java.lang.Runnable
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                    To view partially-correct code enable 'Show inconsistent code' option in preferences
                */
                public void run() {
                    /*
                        r8 = this;
                        r1 = 0
                        java.lang.Class<com.amap.api.col.il> r3 = com.amap.api.col.il.class
                        monitor-enter(r3)
                        com.amap.api.col.ik r0 = com.amap.api.col.ik.this     // Catch: Throwable -> 0x004b, all -> 0x0067
                        byte[] r0 = r0.a()     // Catch: Throwable -> 0x004b, all -> 0x0067
                        android.content.Context r2 = r2     // Catch: Throwable -> 0x004b, all -> 0x0067
                        java.lang.String r4 = com.amap.api.col.gp.h     // Catch: Throwable -> 0x004b, all -> 0x0067
                        java.lang.String r2 = com.amap.api.col.gp.a(r2, r4)     // Catch: Throwable -> 0x004b, all -> 0x0067
                        java.io.File r4 = new java.io.File     // Catch: Throwable -> 0x004b, all -> 0x0067
                        r4.<init>(r2)     // Catch: Throwable -> 0x004b, all -> 0x0067
                        r2 = 1
                        r5 = 1
                        r6 = 2097152(0x200000, double:1.0361308E-317)
                        com.amap.api.col.hw r2 = com.amap.api.col.hw.a(r4, r2, r5, r6)     // Catch: Throwable -> 0x004b, all -> 0x0067
                        int r4 = com.amap.api.col.il.a     // Catch: Throwable -> 0x008f, all -> 0x008d
                        r2.a(r4)     // Catch: Throwable -> 0x008f, all -> 0x008d
                        long r4 = java.lang.System.currentTimeMillis()     // Catch: Throwable -> 0x008f, all -> 0x008d
                        java.lang.String r4 = java.lang.Long.toString(r4)     // Catch: Throwable -> 0x008f, all -> 0x008d
                        com.amap.api.col.hw$a r4 = r2.b(r4)     // Catch: Throwable -> 0x008f, all -> 0x008d
                        r5 = 0
                        java.io.OutputStream r1 = r4.a(r5)     // Catch: Throwable -> 0x008f, all -> 0x008d
                        r1.write(r0)     // Catch: Throwable -> 0x008f, all -> 0x008d
                        r4.a()     // Catch: Throwable -> 0x008f, all -> 0x008d
                        r2.e()     // Catch: Throwable -> 0x008f, all -> 0x008d
                        if (r1 == 0) goto L_0x0044
                        r1.close()     // Catch: Throwable -> 0x0083, all -> 0x0064
                    L_0x0044:
                        if (r2 == 0) goto L_0x0049
                        r2.close()     // Catch: Throwable -> 0x0088, all -> 0x0064
                    L_0x0049:
                        monitor-exit(r3)     // Catch: all -> 0x0064
                        return
                    L_0x004b:
                        r0 = move-exception
                        r2 = r1
                    L_0x004d:
                        java.lang.String r4 = "OfflineLocManager"
                        java.lang.String r5 = "applyOfflineLocEntity"
                        com.amap.api.col.go.a(r0, r4, r5)     // Catch: all -> 0x008d
                        if (r1 == 0) goto L_0x0059
                        r1.close()     // Catch: Throwable -> 0x007e, all -> 0x0064
                    L_0x0059:
                        if (r2 == 0) goto L_0x0049
                        r2.close()     // Catch: Throwable -> 0x005f, all -> 0x0064
                        goto L_0x0049
                    L_0x005f:
                        r0 = move-exception
                        r0.printStackTrace()     // Catch: all -> 0x0064
                        goto L_0x0049
                    L_0x0064:
                        r0 = move-exception
                        monitor-exit(r3)     // Catch: all -> 0x0064
                        throw r0
                    L_0x0067:
                        r0 = move-exception
                        r2 = r1
                    L_0x0069:
                        if (r1 == 0) goto L_0x006e
                        r1.close()     // Catch: Throwable -> 0x0074, all -> 0x0064
                    L_0x006e:
                        if (r2 == 0) goto L_0x0073
                        r2.close()     // Catch: Throwable -> 0x0079, all -> 0x0064
                    L_0x0073:
                        throw r0     // Catch: all -> 0x0064
                    L_0x0074:
                        r1 = move-exception
                        r1.printStackTrace()     // Catch: all -> 0x0064
                        goto L_0x006e
                    L_0x0079:
                        r1 = move-exception
                        r1.printStackTrace()     // Catch: all -> 0x0064
                        goto L_0x0073
                    L_0x007e:
                        r0 = move-exception
                        r0.printStackTrace()     // Catch: all -> 0x0064
                        goto L_0x0059
                    L_0x0083:
                        r0 = move-exception
                        r0.printStackTrace()     // Catch: all -> 0x0064
                        goto L_0x0044
                    L_0x0088:
                        r0 = move-exception
                        r0.printStackTrace()     // Catch: all -> 0x0064
                        goto L_0x0049
                    L_0x008d:
                        r0 = move-exception
                        goto L_0x0069
                    L_0x008f:
                        r0 = move-exception
                        goto L_0x004d
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.amap.api.col.il.AnonymousClass1.run():void");
                }
            });
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:57:0x00a8 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static void a(android.content.Context r7) {
        /*
            r2 = 0
            r6 = 1
            r0 = 0
            boolean r1 = c(r7)     // Catch: Throwable -> 0x0087, all -> 0x00a4
            if (r1 != 0) goto L_0x0015
            if (r2 == 0) goto L_0x0014
            boolean r1 = r0.d()     // Catch: Throwable -> 0x00b6
            if (r1 != 0) goto L_0x0014
            r0.close()     // Catch: Throwable -> 0x00b8
        L_0x0014:
            return
        L_0x0015:
            java.io.File r0 = new java.io.File     // Catch: Throwable -> 0x0087, all -> 0x00a4
            java.lang.String r1 = com.amap.api.col.gp.h     // Catch: Throwable -> 0x0087, all -> 0x00a4
            java.lang.String r1 = com.amap.api.col.gp.a(r7, r1)     // Catch: Throwable -> 0x0087, all -> 0x00a4
            r0.<init>(r1)     // Catch: Throwable -> 0x0087, all -> 0x00a4
            r1 = 1
            r3 = 1
            r4 = 2097152(0x200000, double:1.0361308E-317)
            com.amap.api.col.hw r1 = com.amap.api.col.hw.a(r0, r1, r3, r4)     // Catch: Throwable -> 0x0087, all -> 0x00a4
            java.lang.String r0 = a(r7, r1)     // Catch: Throwable -> 0x00bc, all -> 0x00ba
            boolean r3 = android.text.TextUtils.isEmpty(r0)     // Catch: Throwable -> 0x00bc, all -> 0x00ba
            if (r3 == 0) goto L_0x0044
            if (r1 == 0) goto L_0x0014
            boolean r0 = r1.d()     // Catch: Throwable -> 0x00b4
            if (r0 != 0) goto L_0x0014
            r1.close()     // Catch: Throwable -> 0x003f
            goto L_0x0014
        L_0x003f:
            r0 = move-exception
        L_0x0040:
            r0.printStackTrace()
            goto L_0x0014
        L_0x0044:
            byte[] r0 = com.amap.api.col.gk.a(r0)     // Catch: Throwable -> 0x00bc, all -> 0x00ba
            byte[] r0 = com.amap.api.col.gk.c(r0)     // Catch: Throwable -> 0x00bc, all -> 0x00ba
            com.amap.api.col.gq r3 = new com.amap.api.col.gq     // Catch: Throwable -> 0x00bc, all -> 0x00ba
            java.lang.String r4 = "6"
            r3.<init>(r0, r4)     // Catch: Throwable -> 0x00bc, all -> 0x00ba
            com.amap.api.col.ia r0 = com.amap.api.col.ia.a()     // Catch: Throwable -> 0x00bc, all -> 0x00ba
            byte[] r0 = r0.b(r3)     // Catch: Throwable -> 0x00bc, all -> 0x00ba
            org.json.JSONObject r3 = new org.json.JSONObject     // Catch: Throwable -> 0x00bc, all -> 0x00ba
            java.lang.String r4 = new java.lang.String     // Catch: Throwable -> 0x00bc, all -> 0x00ba
            r4.<init>(r0)     // Catch: Throwable -> 0x00bc, all -> 0x00ba
            r3.<init>(r4)     // Catch: Throwable -> 0x00bc, all -> 0x00ba
            java.lang.String r0 = "code"
            boolean r0 = r3.has(r0)     // Catch: Throwable -> 0x00bc, all -> 0x00ba
            if (r0 == 0) goto L_0x0079
            java.lang.String r0 = "code"
            int r0 = r3.getInt(r0)     // Catch: Throwable -> 0x00bc, all -> 0x00ba
            if (r0 != r6) goto L_0x0079
            b(r1)     // Catch: Throwable -> 0x00bc, all -> 0x00ba
            r1 = r2
        L_0x0079:
            if (r1 == 0) goto L_0x0014
            boolean r0 = r1.d()     // Catch: Throwable -> 0x00b2
            if (r0 != 0) goto L_0x0014
            r1.close()     // Catch: Throwable -> 0x0085
            goto L_0x0014
        L_0x0085:
            r0 = move-exception
            goto L_0x0040
        L_0x0087:
            r0 = move-exception
            r1 = r2
        L_0x0089:
            java.lang.String r2 = "OfflineLocManager"
            java.lang.String r3 = "updateOfflineLocData"
            com.amap.api.col.go.a(r0, r2, r3)     // Catch: all -> 0x00ba
            if (r1 == 0) goto L_0x0014
            boolean r0 = r1.d()     // Catch: Throwable -> 0x009d
            if (r0 != 0) goto L_0x0014
            r1.close()     // Catch: Throwable -> 0x009d
            goto L_0x0014
        L_0x009d:
            r0 = move-exception
            goto L_0x0040
        L_0x009f:
            r1 = move-exception
            r1.printStackTrace()
        L_0x00a3:
            throw r0
        L_0x00a4:
            r0 = move-exception
            r1 = r2
        L_0x00a6:
            if (r1 == 0) goto L_0x00a3
            boolean r2 = r1.d()     // Catch: Throwable -> 0x009f
            if (r2 != 0) goto L_0x00a3
            r1.close()     // Catch: Throwable -> 0x009f
            goto L_0x00a3
        L_0x00b2:
            r0 = move-exception
            goto L_0x0040
        L_0x00b4:
            r0 = move-exception
            goto L_0x0040
        L_0x00b6:
            r0 = move-exception
            goto L_0x0040
        L_0x00b8:
            r0 = move-exception
            goto L_0x0040
        L_0x00ba:
            r0 = move-exception
            goto L_0x00a6
        L_0x00bc:
            r0 = move-exception
            goto L_0x0089
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amap.api.col.il.a(android.content.Context):void");
    }

    private static void a(Context context, long j) {
        Throwable th;
        FileOutputStream fileOutputStream;
        Throwable th2;
        Throwable th3;
        FileOutputStream fileOutputStream2;
        try {
            fileOutputStream = null;
            try {
                File file = new File(gp.a(context, "f.log"));
                if (!file.getParentFile().exists()) {
                    file.getParentFile().mkdirs();
                }
                fileOutputStream2 = new FileOutputStream(file);
            } catch (Throwable th4) {
                th3 = th4;
            }
        } catch (Throwable th5) {
            th = th5;
        }
        try {
            fileOutputStream2.write(gk.a(String.valueOf(j)));
            if (fileOutputStream2 != null) {
                try {
                    fileOutputStream2.close();
                } catch (Throwable th6) {
                    th2 = th6;
                    th2.printStackTrace();
                }
            }
        } catch (Throwable th7) {
            th3 = th7;
            fileOutputStream = fileOutputStream2;
            go.a(th3, "OfflineLocManager", "updateLogUpdateTime");
            if (fileOutputStream != null) {
                try {
                    fileOutputStream.close();
                } catch (Throwable th8) {
                    th2 = th8;
                    th2.printStackTrace();
                }
            }
        }
    }

    private static String a(hw hwVar) {
        StringBuilder sb = new StringBuilder();
        boolean z = true;
        try {
            File c = hwVar.c();
            if (c != null && c.exists()) {
                String[] list = c.list();
                for (String str : list) {
                    if (str.contains(".0")) {
                        String a2 = gk.a(io.a(hwVar, str.split("\\.")[0], false));
                        if (z) {
                            z = false;
                        } else {
                            sb.append(",");
                        }
                        sb.append("{\"log\":\"").append(a2).append("\"}");
                    }
                }
            }
        } catch (Throwable th) {
            go.a(th, "StatisticsManager", "getContent");
        }
        return sb.toString();
    }

    private static void b(hw hwVar) {
        if (hwVar != null) {
            try {
                hwVar.f();
            } catch (Throwable th) {
                go.a(th, "StatisticsManager", "getContent");
            }
        }
    }

    private static String a(Context context, hw hwVar) {
        String a2 = a(hwVar);
        if (TextUtils.isEmpty(a2)) {
            return null;
        }
        String d = d(context);
        StringBuilder sb = new StringBuilder();
        sb.append("{\"pinfo\":\"").append(d).append("\",\"els\":[");
        sb.append(a2);
        sb.append("]}");
        return sb.toString();
    }

    private static long b(Context context) {
        Throwable th;
        FileInputStream fileInputStream;
        Throwable th2;
        FileInputStream fileInputStream2;
        File file = new File(gp.a(context, "f.log"));
        if (!file.exists()) {
            return 0L;
        }
        try {
            fileInputStream = null;
            try {
                fileInputStream2 = new FileInputStream(file);
            } catch (Throwable th3) {
                th2 = th3;
            }
        } catch (Throwable th4) {
            th = th4;
        }
        try {
            byte[] bArr = new byte[fileInputStream2.available()];
            fileInputStream2.read(bArr);
            long parseLong = Long.parseLong(gk.a(bArr));
            if (fileInputStream2 == null) {
                return parseLong;
            }
            try {
                fileInputStream2.close();
                return parseLong;
            } catch (Throwable th5) {
                th5.printStackTrace();
                return parseLong;
            }
        } catch (Throwable th6) {
            th = th6;
            if (fileInputStream2 != null) {
                try {
                    fileInputStream2.close();
                } catch (Throwable th7) {
                    th7.printStackTrace();
                }
            }
            throw th;
        }
    }

    private static synchronized boolean c(Context context) {
        boolean z = false;
        synchronized (il.class) {
            if (ge.m(context) == 1 || b) {
                if (System.currentTimeMillis() - b(context) >= 14400000) {
                    a(context, System.currentTimeMillis());
                    z = true;
                }
            }
        }
        return z;
    }

    private static String d(Context context) {
        return gd.b(context, gk.a(e(context)));
    }

    private static String e(Context context) {
        StringBuilder sb = new StringBuilder();
        try {
            sb.append("\"key\":\"").append(ga.f(context)).append("\",\"platform\":\"android\",\"diu\":\"").append(ge.q(context)).append("\",\"mac\":\"").append(ge.i(context)).append("\",\"tid\":\"").append(ge.f(context)).append("\",\"umidt\":\"").append(ge.a()).append("\",\"manufacture\":\"").append(Build.MANUFACTURER).append("\",\"device\":\"").append(Build.DEVICE).append("\",\"sim\":\"").append(ge.r(context)).append("\",\"pkg\":\"").append(ga.c(context)).append("\",\"model\":\"").append(Build.MODEL).append("\",\"appversion\":\"").append(ga.d(context)).append("\",\"appname\":\"").append(ga.b(context)).append("\"");
        } catch (Throwable th) {
            go.a(th, "CInfo", "getPublicJSONInfo");
        }
        return sb.toString();
    }
}
