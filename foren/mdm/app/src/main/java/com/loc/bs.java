package com.loc;

import android.content.Context;
import android.os.Build;
import com.hyphenate.util.HanziToPinyin;
import com.umeng.analytics.a;
import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/* compiled from: StatisticsManager.java */
/* loaded from: classes2.dex */
public final class bs {
    private static boolean a = true;

    public static void a(Context context) {
        try {
            if (c(context)) {
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append(new SimpleDateFormat("yyyyMMdd HHmmss").format(new Date()));
                stringBuffer.append(HanziToPinyin.Token.SEPARATOR);
                stringBuffer.append(UUID.randomUUID().toString());
                stringBuffer.append(HanziToPinyin.Token.SEPARATOR);
                if (stringBuffer.length() == 53) {
                    byte[] a2 = t.a(stringBuffer.toString());
                    byte[] b = b(context);
                    byte[] a3 = bt.a(context, x.g);
                    byte[] bArr = new byte[b.length + a3.length];
                    System.arraycopy(b, 0, bArr, 0, b.length);
                    System.arraycopy(a3, 0, bArr, b.length, a3.length);
                    byte[] a4 = a(bArr);
                    byte[] bArr2 = new byte[a2.length + a4.length];
                    System.arraycopy(a2, 0, bArr2, 0, a2.length);
                    System.arraycopy(a4, 0, bArr2, a2.length, a4.length);
                    y yVar = new y(t.c(bArr2), "2");
                    bi.a();
                    bi.a(yVar);
                }
            }
        } catch (Throwable th) {
            w.a(th, "StatisticsManager", "updateStaticsData");
        }
    }

    public static synchronized void a(final br brVar, final Context context) {
        synchronized (bs.class) {
            z.b().submit(new Runnable() { // from class: com.loc.bs.1
                @Override // java.lang.Runnable
                public final void run() {
                    bt.a(context, x.g, brVar.a());
                }
            });
        }
    }

    public static synchronized void a(final List<br> list, final Context context) {
        synchronized (bs.class) {
            z.b().submit(new Runnable() { // from class: com.loc.bs.2
                /* JADX WARN: Removed duplicated region for block: B:32:0x004c A[EXC_TOP_SPLITTER, SYNTHETIC] */
                @Override // java.lang.Runnable
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                    To view partially-correct code enable 'Show inconsistent code' option in preferences
                */
                public final void run() {
                    /*
                        r5 = this;
                        r3 = 0
                        r0 = 0
                        byte[] r1 = new byte[r0]
                        java.io.ByteArrayOutputStream r2 = new java.io.ByteArrayOutputStream     // Catch: Throwable -> 0x005c, all -> 0x0048
                        r2.<init>()     // Catch: Throwable -> 0x005c, all -> 0x0048
                        java.util.List r0 = r1     // Catch: Throwable -> 0x0023, all -> 0x005a
                        java.util.Iterator r3 = r0.iterator()     // Catch: Throwable -> 0x0023, all -> 0x005a
                    L_0x000f:
                        boolean r0 = r3.hasNext()     // Catch: Throwable -> 0x0023, all -> 0x005a
                        if (r0 == 0) goto L_0x0039
                        java.lang.Object r0 = r3.next()     // Catch: Throwable -> 0x0023, all -> 0x005a
                        com.loc.br r0 = (com.loc.br) r0     // Catch: Throwable -> 0x0023, all -> 0x005a
                        byte[] r0 = r0.a()     // Catch: Throwable -> 0x0023, all -> 0x005a
                        r2.write(r0)     // Catch: Throwable -> 0x0023, all -> 0x005a
                        goto L_0x000f
                    L_0x0023:
                        r0 = move-exception
                    L_0x0024:
                        java.lang.String r3 = "StatisticsEntity"
                        java.lang.String r4 = "applyStaticsBatch"
                        com.loc.w.a(r0, r3, r4)     // Catch: all -> 0x005a
                        if (r2 == 0) goto L_0x0030
                        r2.close()     // Catch: Throwable -> 0x0055
                    L_0x0030:
                        r0 = r1
                    L_0x0031:
                        android.content.Context r1 = r2
                        java.lang.String r2 = com.loc.x.g
                        com.loc.bt.a(r1, r2, r0)
                        return
                    L_0x0039:
                        byte[] r0 = r2.toByteArray()     // Catch: Throwable -> 0x0023, all -> 0x005a
                        if (r2 == 0) goto L_0x0031
                        r2.close()     // Catch: Throwable -> 0x0043
                        goto L_0x0031
                    L_0x0043:
                        r1 = move-exception
                        r1.printStackTrace()
                        goto L_0x0031
                    L_0x0048:
                        r0 = move-exception
                        r2 = r3
                    L_0x004a:
                        if (r2 == 0) goto L_0x004f
                        r2.close()     // Catch: Throwable -> 0x0050
                    L_0x004f:
                        throw r0
                    L_0x0050:
                        r1 = move-exception
                        r1.printStackTrace()
                        goto L_0x004f
                    L_0x0055:
                        r0 = move-exception
                        r0.printStackTrace()
                        goto L_0x0030
                    L_0x005a:
                        r0 = move-exception
                        goto L_0x004a
                    L_0x005c:
                        r0 = move-exception
                        r2 = r3
                        goto L_0x0024
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.loc.bs.AnonymousClass2.run():void");
                }
            });
        }
    }

    private static byte[] a(byte[] bArr) {
        try {
            return m.a(bArr);
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
    }

    private static byte[] b(Context context) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] bArr = new byte[0];
        try {
            t.a(byteArrayOutputStream, "1.2.13.6");
            t.a(byteArrayOutputStream, "Android");
            t.a(byteArrayOutputStream, n.q(context));
            t.a(byteArrayOutputStream, n.i(context));
            t.a(byteArrayOutputStream, n.f(context));
            t.a(byteArrayOutputStream, Build.MANUFACTURER);
            t.a(byteArrayOutputStream, Build.MODEL);
            t.a(byteArrayOutputStream, Build.DEVICE);
            t.a(byteArrayOutputStream, n.r(context));
            t.a(byteArrayOutputStream, k.c(context));
            t.a(byteArrayOutputStream, k.d(context));
            t.a(byteArrayOutputStream, k.f(context));
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

    private static boolean c(Context context) {
        try {
            if (n.m(context) != 1 || !a || bt.b(context, x.g) < 30) {
                return false;
            }
            long c = bt.c(context, "c.log");
            long time = new Date().getTime();
            if (time - c < a.k) {
                return false;
            }
            bt.a(context, time, "c.log");
            a = false;
            return true;
        } catch (Throwable th) {
            w.a(th, "StatisticsManager", "isUpdate");
            return false;
        }
    }
}
