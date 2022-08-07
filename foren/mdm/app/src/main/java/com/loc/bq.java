package com.loc;

import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import org.json.JSONObject;

/* compiled from: OfflineLocManager.java */
/* loaded from: classes2.dex */
public class bq {
    static int a = 1000;
    static boolean b = false;
    private static be c;

    private static String a(String str) {
        StringBuilder sb = new StringBuilder();
        boolean z = true;
        c = null;
        try {
            c = be.a(new File(str), 2097152L);
            File file = new File(str);
            if (file.exists()) {
                String[] list = file.list();
                for (String str2 : list) {
                    if (str2.contains(".0")) {
                        String a2 = t.a(bt.a(c, str2.split("\\.")[0], false));
                        if (z) {
                            z = false;
                        } else {
                            sb.append(",");
                        }
                        sb.append("{\"log\":\"").append(a2).append("\"}");
                    }
                }
            }
        } catch (IOException e) {
            w.a(e, "StatisticsManager", "getContent");
        } catch (Throwable th) {
            w.a(th, "StatisticsManager", "getContent");
        }
        return sb.toString();
    }

    public static synchronized void a(int i, boolean z) {
        synchronized (bq.class) {
            a = i;
            b = z;
        }
    }

    public static void a(Context context) {
        Throwable th;
        String str = null;
        try {
            if (c(context)) {
                String a2 = a(x.a(context, x.h));
                if (!TextUtils.isEmpty(a2)) {
                    String b2 = m.b(t.a(d(context)));
                    StringBuilder sb = new StringBuilder();
                    sb.append("{\"pinfo\":\"").append(b2).append("\",\"els\":[");
                    sb.append(a2);
                    sb.append("]}");
                    str = sb.toString();
                }
                if (!TextUtils.isEmpty(str)) {
                    y yVar = new y(t.c(t.a(str)), "6");
                    bi.a();
                    JSONObject jSONObject = new JSONObject(new String(bi.a(yVar)));
                    if (jSONObject.has("code") && jSONObject.getInt("code") == 1 && c != null) {
                        try {
                            c.d();
                        } catch (Throwable th2) {
                            w.a(th2, "StatisticsManager", "getContent");
                        }
                    }
                    if (c != null) {
                        try {
                            try {
                                c.close();
                                try {
                                    c = null;
                                    return;
                                } catch (Throwable th3) {
                                    th = th3;
                                }
                            } catch (Throwable th4) {
                                th = th4;
                            }
                        } catch (Throwable th5) {
                            th = th5;
                        }
                    } else {
                        return;
                    }
                } else if (c != null) {
                    try {
                        try {
                            c.close();
                            try {
                                c = null;
                                return;
                            } catch (Throwable th6) {
                                th = th6;
                            }
                        } catch (Throwable th7) {
                            th = th7;
                        }
                    } catch (Throwable th8) {
                        th = th8;
                    }
                } else {
                    return;
                }
            } else if (c != null) {
                try {
                    try {
                        c.close();
                        try {
                            c = null;
                            return;
                        } catch (Throwable th9) {
                            th = th9;
                        }
                    } catch (Throwable th10) {
                        th = th10;
                    }
                } catch (Throwable th11) {
                    th = th11;
                }
            } else {
                return;
            }
            th.printStackTrace();
        } finally {
            if (c != null) {
                try {
                    c.close();
                    c = null;
                } catch (Throwable th12) {
                    th12.printStackTrace();
                }
            }
        }
    }

    public static synchronized void a(final bp bpVar, final Context context) {
        synchronized (bq.class) {
            z.b().submit(new Runnable() { // from class: com.loc.bq.1
                /* JADX WARN: Removed duplicated region for block: B:24:0x0068 A[Catch: Throwable -> 0x0071, all -> 0x0061, TRY_ENTER, TRY_LEAVE, TryCatch #9 {, blocks: (B:7:0x003e, B:9:0x0043, B:10:0x0046, B:16:0x0053, B:18:0x0058, B:24:0x0068, B:26:0x006d, B:27:0x0070), top: B:30:0x0003 }] */
                /* JADX WARN: Removed duplicated region for block: B:26:0x006d A[Catch: Throwable -> 0x0076, all -> 0x0061, TRY_ENTER, TRY_LEAVE, TryCatch #9 {, blocks: (B:7:0x003e, B:9:0x0043, B:10:0x0046, B:16:0x0053, B:18:0x0058, B:24:0x0068, B:26:0x006d, B:27:0x0070), top: B:30:0x0003 }] */
                @Override // java.lang.Runnable
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                    To view partially-correct code enable 'Show inconsistent code' option in preferences
                */
                public final void run() {
                    /*
                        r8 = this;
                        r1 = 0
                        java.lang.Class<com.loc.bq> r3 = com.loc.bq.class
                        monitor-enter(r3)
                        com.loc.bp r0 = com.loc.bp.this     // Catch: Throwable -> 0x0048, all -> 0x0064
                        byte[] r0 = r0.a()     // Catch: Throwable -> 0x0048, all -> 0x0064
                        android.content.Context r2 = r2     // Catch: Throwable -> 0x0048, all -> 0x0064
                        java.lang.String r4 = com.loc.x.h     // Catch: Throwable -> 0x0048, all -> 0x0064
                        java.lang.String r2 = com.loc.x.a(r2, r4)     // Catch: Throwable -> 0x0048, all -> 0x0064
                        java.io.File r4 = new java.io.File     // Catch: Throwable -> 0x0048, all -> 0x0064
                        r4.<init>(r2)     // Catch: Throwable -> 0x0048, all -> 0x0064
                        r6 = 2097152(0x200000, double:1.0361308E-317)
                        com.loc.be r2 = com.loc.be.a(r4, r6)     // Catch: Throwable -> 0x0048, all -> 0x0064
                        int r4 = com.loc.bq.a     // Catch: Throwable -> 0x008c, all -> 0x008a
                        r2.a(r4)     // Catch: Throwable -> 0x008c, all -> 0x008a
                        long r4 = java.lang.System.currentTimeMillis()     // Catch: Throwable -> 0x008c, all -> 0x008a
                        java.lang.String r4 = java.lang.Long.toString(r4)     // Catch: Throwable -> 0x008c, all -> 0x008a
                        com.loc.be$a r4 = r2.b(r4)     // Catch: Throwable -> 0x008c, all -> 0x008a
                        java.io.OutputStream r1 = r4.a()     // Catch: Throwable -> 0x008c, all -> 0x008a
                        r1.write(r0)     // Catch: Throwable -> 0x008c, all -> 0x008a
                        r4.b()     // Catch: Throwable -> 0x008c, all -> 0x008a
                        r2.c()     // Catch: Throwable -> 0x008c, all -> 0x008a
                        if (r1 == 0) goto L_0x0041
                        r1.close()     // Catch: Throwable -> 0x0080, all -> 0x0061
                    L_0x0041:
                        if (r2 == 0) goto L_0x0046
                        r2.close()     // Catch: Throwable -> 0x0085, all -> 0x0061
                    L_0x0046:
                        monitor-exit(r3)     // Catch: all -> 0x0061
                        return
                    L_0x0048:
                        r0 = move-exception
                        r2 = r1
                    L_0x004a:
                        java.lang.String r4 = "OfflineLocManager"
                        java.lang.String r5 = "applyOfflineLocEntity"
                        com.loc.w.a(r0, r4, r5)     // Catch: all -> 0x008a
                        if (r1 == 0) goto L_0x0056
                        r1.close()     // Catch: Throwable -> 0x007b, all -> 0x0061
                    L_0x0056:
                        if (r2 == 0) goto L_0x0046
                        r2.close()     // Catch: Throwable -> 0x005c, all -> 0x0061
                        goto L_0x0046
                    L_0x005c:
                        r0 = move-exception
                        r0.printStackTrace()     // Catch: all -> 0x0061
                        goto L_0x0046
                    L_0x0061:
                        r0 = move-exception
                        monitor-exit(r3)
                        throw r0
                    L_0x0064:
                        r0 = move-exception
                        r2 = r1
                    L_0x0066:
                        if (r1 == 0) goto L_0x006b
                        r1.close()     // Catch: Throwable -> 0x0071, all -> 0x0061
                    L_0x006b:
                        if (r2 == 0) goto L_0x0070
                        r2.close()     // Catch: Throwable -> 0x0076, all -> 0x0061
                    L_0x0070:
                        throw r0     // Catch: all -> 0x0061
                    L_0x0071:
                        r1 = move-exception
                        r1.printStackTrace()     // Catch: all -> 0x0061
                        goto L_0x006b
                    L_0x0076:
                        r1 = move-exception
                        r1.printStackTrace()     // Catch: all -> 0x0061
                        goto L_0x0070
                    L_0x007b:
                        r0 = move-exception
                        r0.printStackTrace()     // Catch: all -> 0x0061
                        goto L_0x0056
                    L_0x0080:
                        r0 = move-exception
                        r0.printStackTrace()     // Catch: all -> 0x0061
                        goto L_0x0041
                    L_0x0085:
                        r0 = move-exception
                        r0.printStackTrace()     // Catch: all -> 0x0061
                        goto L_0x0046
                    L_0x008a:
                        r0 = move-exception
                        goto L_0x0066
                    L_0x008c:
                        r0 = move-exception
                        goto L_0x004a
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.loc.bq.AnonymousClass1.run():void");
                }
            });
        }
    }

    private static long b(Context context) {
        Throwable th;
        FileInputStream fileInputStream;
        Throwable th2;
        FileInputStream fileInputStream2;
        File file = new File(x.a(context, "f.log"));
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
            long parseLong = Long.parseLong(t.a(bArr));
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
        Throwable th;
        Throwable th2;
        FileOutputStream fileOutputStream;
        boolean z = false;
        synchronized (bq.class) {
            if (n.m(context) == 1 || b) {
                if (System.currentTimeMillis() - b(context) >= 14400000) {
                    long currentTimeMillis = System.currentTimeMillis();
                    FileOutputStream fileOutputStream2 = null;
                    try {
                        try {
                            File file = new File(x.a(context, "f.log"));
                            if (!file.getParentFile().exists()) {
                                file.getParentFile().mkdirs();
                            }
                            fileOutputStream = new FileOutputStream(file);
                        } catch (Throwable th3) {
                            th2 = th3;
                        }
                    } catch (Throwable th4) {
                        th = th4;
                    }
                    try {
                        fileOutputStream.write(t.a(String.valueOf(currentTimeMillis)));
                        if (fileOutputStream != null) {
                            try {
                                fileOutputStream.close();
                            } catch (Throwable th5) {
                                th5.printStackTrace();
                            }
                        }
                    } catch (Throwable th6) {
                        th = th6;
                        fileOutputStream2 = fileOutputStream;
                        if (fileOutputStream2 != null) {
                            try {
                                fileOutputStream2.close();
                            } catch (Throwable th7) {
                                th7.printStackTrace();
                            }
                        }
                        throw th;
                    }
                    z = true;
                }
            }
        }
        return z;
    }

    private static String d(Context context) {
        StringBuilder sb = new StringBuilder();
        try {
            sb.append("\"key\":\"").append(k.f(context)).append("\",\"platform\":\"android\",\"diu\":\"").append(n.q(context)).append("\",\"mac\":\"").append(n.i(context)).append("\",\"tid\":\"").append(n.f(context)).append("\",\"manufacture\":\"").append(Build.MANUFACTURER).append("\",\"device\":\"").append(Build.DEVICE).append("\",\"sim\":\"").append(n.r(context)).append("\",\"pkg\":\"").append(k.c(context)).append("\",\"model\":\"").append(Build.MODEL).append("\",\"appversion\":\"").append(k.d(context)).append("\"");
        } catch (Throwable th) {
            w.a(th, "CInfo", "getPublicJSONInfo");
        }
        return sb.toString();
    }
}
