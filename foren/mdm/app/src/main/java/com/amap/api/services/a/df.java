package com.amap.api.services.a;

import android.content.Context;
import android.os.Build;
import com.alibaba.sdk.android.oss.common.OSSConstants;
import com.hyphenate.util.HanziToPinyin;
import com.umeng.analytics.a;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/* compiled from: StatisticsManager.java */
/* loaded from: classes.dex */
public class df {
    private static boolean a = true;

    public static synchronized void a(final de deVar, final Context context) {
        synchronized (df.class) {
            bk.b().submit(new Runnable() { // from class: com.amap.api.services.a.df.1
                /* JADX WARN: Removed duplicated region for block: B:26:0x0086 A[Catch: Throwable -> 0x008f, all -> 0x007f, TRY_ENTER, TRY_LEAVE, TryCatch #10 {, blocks: (B:4:0x0004, B:8:0x005c, B:10:0x0061, B:11:0x0064, B:17:0x0071, B:19:0x0076, B:26:0x0086, B:28:0x008b, B:29:0x008e), top: B:35:0x0004 }] */
                /* JADX WARN: Removed duplicated region for block: B:28:0x008b A[Catch: Throwable -> 0x0094, all -> 0x007f, TRY_ENTER, TRY_LEAVE, TryCatch #10 {, blocks: (B:4:0x0004, B:8:0x005c, B:10:0x0061, B:11:0x0064, B:17:0x0071, B:19:0x0076, B:26:0x0086, B:28:0x008b, B:29:0x008e), top: B:35:0x0004 }] */
                @Override // java.lang.Runnable
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                    To view partially-correct code enable 'Show inconsistent code' option in preferences
                */
                public void run() {
                    /*
                        r10 = this;
                        r1 = 0
                        java.lang.Class<com.amap.api.services.a.df> r3 = com.amap.api.services.a.df.class
                        monitor-enter(r3)
                        java.util.Random r0 = new java.util.Random     // Catch: all -> 0x007f
                        r0.<init>()     // Catch: all -> 0x007f
                        com.amap.api.services.a.de r2 = com.amap.api.services.a.de.this     // Catch: Throwable -> 0x0066, all -> 0x0082
                        byte[] r4 = r2.a()     // Catch: Throwable -> 0x0066, all -> 0x0082
                        android.content.Context r2 = r2     // Catch: Throwable -> 0x0066, all -> 0x0082
                        java.lang.String r5 = com.amap.api.services.a.bi.e     // Catch: Throwable -> 0x0066, all -> 0x0082
                        java.lang.String r2 = com.amap.api.services.a.bi.a(r2, r5)     // Catch: Throwable -> 0x0066, all -> 0x0082
                        java.io.File r5 = new java.io.File     // Catch: Throwable -> 0x0066, all -> 0x0082
                        r5.<init>(r2)     // Catch: Throwable -> 0x0066, all -> 0x0082
                        r2 = 1
                        r6 = 1
                        r8 = 102400(0x19000, double:5.05923E-319)
                        com.amap.api.services.a.cp r2 = com.amap.api.services.a.cp.a(r5, r2, r6, r8)     // Catch: Throwable -> 0x0066, all -> 0x0082
                        java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch: Throwable -> 0x00aa, all -> 0x00a8
                        r5.<init>()     // Catch: Throwable -> 0x00aa, all -> 0x00a8
                        r6 = 100
                        int r0 = r0.nextInt(r6)     // Catch: Throwable -> 0x00aa, all -> 0x00a8
                        java.lang.String r0 = java.lang.Integer.toString(r0)     // Catch: Throwable -> 0x00aa, all -> 0x00a8
                        java.lang.StringBuilder r0 = r5.append(r0)     // Catch: Throwable -> 0x00aa, all -> 0x00a8
                        long r6 = java.lang.System.nanoTime()     // Catch: Throwable -> 0x00aa, all -> 0x00a8
                        java.lang.String r5 = java.lang.Long.toString(r6)     // Catch: Throwable -> 0x00aa, all -> 0x00a8
                        java.lang.StringBuilder r0 = r0.append(r5)     // Catch: Throwable -> 0x00aa, all -> 0x00a8
                        java.lang.String r0 = r0.toString()     // Catch: Throwable -> 0x00aa, all -> 0x00a8
                        com.amap.api.services.a.cp$a r0 = r2.b(r0)     // Catch: Throwable -> 0x00aa, all -> 0x00a8
                        r5 = 0
                        java.io.OutputStream r1 = r0.a(r5)     // Catch: Throwable -> 0x00aa, all -> 0x00a8
                        r1.write(r4)     // Catch: Throwable -> 0x00aa, all -> 0x00a8
                        r0.a()     // Catch: Throwable -> 0x00aa, all -> 0x00a8
                        r2.b()     // Catch: Throwable -> 0x00aa, all -> 0x00a8
                        if (r1 == 0) goto L_0x005f
                        r1.close()     // Catch: Throwable -> 0x009e, all -> 0x007f
                    L_0x005f:
                        if (r2 == 0) goto L_0x0064
                        r2.close()     // Catch: Throwable -> 0x00a3, all -> 0x007f
                    L_0x0064:
                        monitor-exit(r3)     // Catch: all -> 0x007f
                        return
                    L_0x0066:
                        r0 = move-exception
                        r2 = r1
                    L_0x0068:
                        java.lang.String r4 = "StatisticsManager"
                        java.lang.String r5 = "applyStatics"
                        com.amap.api.services.a.bh.a(r0, r4, r5)     // Catch: all -> 0x00a8
                        if (r1 == 0) goto L_0x0074
                        r1.close()     // Catch: Throwable -> 0x0099, all -> 0x007f
                    L_0x0074:
                        if (r2 == 0) goto L_0x0064
                        r2.close()     // Catch: Throwable -> 0x007a, all -> 0x007f
                        goto L_0x0064
                    L_0x007a:
                        r0 = move-exception
                        r0.printStackTrace()     // Catch: all -> 0x007f
                        goto L_0x0064
                    L_0x007f:
                        r0 = move-exception
                        monitor-exit(r3)     // Catch: all -> 0x007f
                        throw r0
                    L_0x0082:
                        r0 = move-exception
                        r2 = r1
                    L_0x0084:
                        if (r1 == 0) goto L_0x0089
                        r1.close()     // Catch: Throwable -> 0x008f, all -> 0x007f
                    L_0x0089:
                        if (r2 == 0) goto L_0x008e
                        r2.close()     // Catch: Throwable -> 0x0094, all -> 0x007f
                    L_0x008e:
                        throw r0     // Catch: all -> 0x007f
                    L_0x008f:
                        r1 = move-exception
                        r1.printStackTrace()     // Catch: all -> 0x007f
                        goto L_0x0089
                    L_0x0094:
                        r1 = move-exception
                        r1.printStackTrace()     // Catch: all -> 0x007f
                        goto L_0x008e
                    L_0x0099:
                        r0 = move-exception
                        r0.printStackTrace()     // Catch: all -> 0x007f
                        goto L_0x0074
                    L_0x009e:
                        r0 = move-exception
                        r0.printStackTrace()     // Catch: all -> 0x007f
                        goto L_0x005f
                    L_0x00a3:
                        r0 = move-exception
                        r0.printStackTrace()     // Catch: all -> 0x007f
                        goto L_0x0064
                    L_0x00a8:
                        r0 = move-exception
                        goto L_0x0084
                    L_0x00aa:
                        r0 = move-exception
                        goto L_0x0068
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.amap.api.services.a.df.AnonymousClass1.run():void");
                }
            });
        }
    }

    private static byte[] b(Context context) {
        byte[] c = c(context);
        byte[] e = e(context);
        byte[] bArr = new byte[c.length + e.length];
        System.arraycopy(c, 0, bArr, 0, c.length);
        System.arraycopy(e, 0, bArr, c.length, e.length);
        return a(context, bArr);
    }

    public static void a(Context context) {
        try {
            if (g(context)) {
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append(new SimpleDateFormat("yyyyMMdd HHmmss").format(new Date()));
                stringBuffer.append(HanziToPinyin.Token.SEPARATOR);
                stringBuffer.append(UUID.randomUUID().toString());
                stringBuffer.append(HanziToPinyin.Token.SEPARATOR);
                if (stringBuffer.length() == 53) {
                    byte[] a2 = bf.a(stringBuffer.toString());
                    byte[] b = b(context);
                    byte[] bArr = new byte[a2.length + b.length];
                    System.arraycopy(a2, 0, bArr, 0, a2.length);
                    System.arraycopy(b, 0, bArr, a2.length, b.length);
                    ct.a().b(new bj(bf.c(bArr), "2"));
                }
            }
        } catch (Throwable th) {
            bh.a(th, "StatisticsManager", "updateStaticsData");
        }
    }

    private static byte[] a(Context context, byte[] bArr) {
        try {
            return az.a(context, bArr);
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
    }

    private static byte[] c(Context context) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] bArr = new byte[0];
        try {
            bf.a(byteArrayOutputStream, "1.2.13.6");
            bf.a(byteArrayOutputStream, "Android");
            bf.a(byteArrayOutputStream, ba.q(context));
            bf.a(byteArrayOutputStream, ba.i(context));
            bf.a(byteArrayOutputStream, ba.f(context));
            bf.a(byteArrayOutputStream, Build.MANUFACTURER);
            bf.a(byteArrayOutputStream, Build.MODEL);
            bf.a(byteArrayOutputStream, Build.DEVICE);
            bf.a(byteArrayOutputStream, ba.r(context));
            bf.a(byteArrayOutputStream, aw.c(context));
            bf.a(byteArrayOutputStream, aw.d(context));
            bf.a(byteArrayOutputStream, aw.f(context));
            byteArrayOutputStream.write(new byte[]{0});
            return byteArrayOutputStream.toByteArray();
        } finally {
            try {
                byteArrayOutputStream.close();
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
    }

    private static int d(Context context) {
        try {
            File file = new File(bi.a(context, bi.e));
            if (!file.exists()) {
                return 0;
            }
            return file.list().length;
        } catch (Throwable th) {
            bh.a(th, "StatisticsManager", "getFileNum");
            return 0;
        }
    }

    private static byte[] e(Context context) {
        cp cpVar;
        ByteArrayOutputStream byteArrayOutputStream;
        try {
            byteArrayOutputStream = new ByteArrayOutputStream();
            byte[] bArr = new byte[0];
            String a2 = bi.a(context, bi.e);
            cpVar = null;
            try {
                cpVar = cp.a(new File(a2), 1, 1, OSSConstants.MIN_PART_SIZE_LIMIT);
                File file = new File(a2);
                if (file != null && file.exists()) {
                    String[] list = file.list();
                    for (String str : list) {
                        if (str.contains(".0")) {
                            byteArrayOutputStream.write(dg.a(cpVar, str.split("\\.")[0]));
                        }
                    }
                }
                bArr = byteArrayOutputStream.toByteArray();
                if (byteArrayOutputStream != null) {
                    try {
                        byteArrayOutputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (cpVar != null) {
                    try {
                        cpVar.close();
                    } catch (Throwable th) {
                        th = th;
                        th.printStackTrace();
                        return bArr;
                    }
                }
            } catch (IOException e2) {
                bh.a(e2, "StatisticsManager", "getContent");
                if (byteArrayOutputStream != null) {
                    try {
                        byteArrayOutputStream.close();
                    } catch (IOException e3) {
                        e3.printStackTrace();
                    }
                }
                if (cpVar != null) {
                    try {
                        cpVar.close();
                    } catch (Throwable th2) {
                        th = th2;
                        th.printStackTrace();
                        return bArr;
                    }
                }
            } catch (Throwable th3) {
                bh.a(th3, "StatisticsManager", "getContent");
                if (byteArrayOutputStream != null) {
                    try {
                        byteArrayOutputStream.close();
                    } catch (IOException e4) {
                        e4.printStackTrace();
                    }
                }
                if (cpVar != null) {
                    try {
                        cpVar.close();
                    } catch (Throwable th4) {
                        th = th4;
                        th.printStackTrace();
                        return bArr;
                    }
                }
            }
            return bArr;
        } catch (Throwable th5) {
            if (byteArrayOutputStream != null) {
                try {
                    byteArrayOutputStream.close();
                } catch (IOException e5) {
                    e5.printStackTrace();
                }
            }
            if (cpVar != null) {
                try {
                    cpVar.close();
                } catch (Throwable th6) {
                    th6.printStackTrace();
                }
            }
            throw th5;
        }
    }

    private static void a(Context context, long j) {
        FileOutputStream fileOutputStream;
        IOException e;
        FileNotFoundException e2;
        File file = new File(bi.a(context, "c.log"));
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        try {
        } catch (Throwable th) {
            th = th;
        }
        try {
            fileOutputStream = new FileOutputStream(file);
            try {
                fileOutputStream.write(bf.a(String.valueOf(j)));
                if (fileOutputStream != null) {
                    try {
                        fileOutputStream.close();
                    } catch (Throwable th2) {
                        th = th2;
                        th.printStackTrace();
                    }
                }
            } catch (FileNotFoundException e3) {
                e2 = e3;
                e2.printStackTrace();
                if (fileOutputStream != null) {
                    try {
                        fileOutputStream.close();
                    } catch (Throwable th3) {
                        th = th3;
                        th.printStackTrace();
                    }
                }
            } catch (IOException e4) {
                e = e4;
                e.printStackTrace();
                if (fileOutputStream != null) {
                    try {
                        fileOutputStream.close();
                    } catch (Throwable th4) {
                        th = th4;
                        th.printStackTrace();
                    }
                }
            }
        } catch (FileNotFoundException e5) {
            e2 = e5;
            fileOutputStream = null;
        } catch (IOException e6) {
            e = e6;
            fileOutputStream = null;
        } catch (Throwable th5) {
            th = th5;
            fileOutputStream = null;
            if (fileOutputStream != null) {
                try {
                    fileOutputStream.close();
                } catch (Throwable th6) {
                    th6.printStackTrace();
                }
            }
            throw th;
        }
    }

    private static long f(Context context) {
        Throwable th;
        FileInputStream fileInputStream;
        FileInputStream fileInputStream2;
        Throwable th2;
        IOException e;
        FileNotFoundException e2;
        long j = 0;
        File file = new File(bi.a(context, "c.log"));
        if (file.exists()) {
            try {
                fileInputStream = null;
            } catch (Throwable th3) {
                th = th3;
            }
            try {
                fileInputStream2 = new FileInputStream(file);
                try {
                    byte[] bArr = new byte[fileInputStream2.available()];
                    fileInputStream2.read(bArr);
                    j = Long.parseLong(bf.a(bArr));
                    if (fileInputStream2 != null) {
                        try {
                            fileInputStream2.close();
                        } catch (Throwable th4) {
                            th = th4;
                            th.printStackTrace();
                            return j;
                        }
                    }
                } catch (FileNotFoundException e3) {
                    e2 = e3;
                    bh.a(e2, "StatisticsManager", "getUpdateTime");
                    if (fileInputStream2 != null) {
                        try {
                            fileInputStream2.close();
                        } catch (Throwable th5) {
                            th = th5;
                            th.printStackTrace();
                            return j;
                        }
                    }
                    return j;
                } catch (IOException e4) {
                    e = e4;
                    bh.a(e, "StatisticsManager", "getUpdateTime");
                    if (fileInputStream2 != null) {
                        try {
                            fileInputStream2.close();
                        } catch (Throwable th6) {
                            th = th6;
                            th.printStackTrace();
                            return j;
                        }
                    }
                    return j;
                } catch (Throwable th7) {
                    th2 = th7;
                    bh.a(th2, "StatisticsManager", "getUpdateTime");
                    if (file != null && file.exists()) {
                        file.delete();
                    }
                    if (fileInputStream2 != null) {
                        try {
                            fileInputStream2.close();
                        } catch (Throwable th8) {
                            th = th8;
                            th.printStackTrace();
                            return j;
                        }
                    }
                    return j;
                }
            } catch (FileNotFoundException e5) {
                e2 = e5;
                fileInputStream2 = null;
            } catch (IOException e6) {
                e = e6;
                fileInputStream2 = null;
            } catch (Throwable th9) {
                th2 = th9;
                fileInputStream2 = null;
            }
        }
        return j;
    }

    private static boolean g(Context context) {
        try {
            if (ba.m(context) != 1 || !a || d(context) < 100) {
                return false;
            }
            long f = f(context);
            long time = new Date().getTime();
            if (time - f < a.k) {
                return false;
            }
            a(context, time);
            a = false;
            return true;
        } catch (Throwable th) {
            bh.a(th, "StatisticsManager", "isUpdate");
            return false;
        }
    }
}
