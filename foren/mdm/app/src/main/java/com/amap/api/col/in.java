package com.amap.api.col;

import android.content.Context;
import android.os.Build;
import com.hyphenate.util.HanziToPinyin;
import com.umeng.analytics.a;
import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/* compiled from: StatisticsManager.java */
/* loaded from: classes.dex */
public class in {
    private static boolean a = true;

    public static synchronized void a(final im imVar, final Context context) {
        synchronized (in.class) {
            gr.c().submit(new Runnable() { // from class: com.amap.api.col.in.1
                @Override // java.lang.Runnable
                public void run() {
                    io.a(context, gp.g, 307200, imVar.a());
                }
            });
        }
    }

    private static byte[] b(Context context) {
        byte[] c = c(context);
        byte[] d = d(context);
        byte[] bArr = new byte[c.length + d.length];
        System.arraycopy(c, 0, bArr, 0, c.length);
        System.arraycopy(d, 0, bArr, c.length, d.length);
        return a(context, bArr);
    }

    public static void a(Context context) {
        try {
            if (e(context)) {
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append(new SimpleDateFormat("yyyyMMdd HHmmss").format(new Date()));
                stringBuffer.append(HanziToPinyin.Token.SEPARATOR);
                stringBuffer.append(UUID.randomUUID().toString());
                stringBuffer.append(HanziToPinyin.Token.SEPARATOR);
                if (stringBuffer.length() == 53) {
                    byte[] a2 = gk.a(stringBuffer.toString());
                    byte[] b = b(context);
                    byte[] bArr = new byte[a2.length + b.length];
                    System.arraycopy(a2, 0, bArr, 0, a2.length);
                    System.arraycopy(b, 0, bArr, a2.length, b.length);
                    ia.a().b(new gq(gk.c(bArr), "2"));
                }
            }
        } catch (Throwable th) {
            go.a(th, "StatisticsManager", "updateStaticsData");
        }
    }

    private static byte[] a(Context context, byte[] bArr) {
        try {
            return gd.a(context, bArr);
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
    }

    private static byte[] c(Context context) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] bArr = new byte[0];
        try {
            gk.a(byteArrayOutputStream, "1.2.13.6");
            gk.a(byteArrayOutputStream, "Android");
            gk.a(byteArrayOutputStream, ge.q(context));
            gk.a(byteArrayOutputStream, ge.i(context));
            gk.a(byteArrayOutputStream, ge.f(context));
            gk.a(byteArrayOutputStream, Build.MANUFACTURER);
            gk.a(byteArrayOutputStream, Build.MODEL);
            gk.a(byteArrayOutputStream, Build.DEVICE);
            gk.a(byteArrayOutputStream, ge.r(context));
            gk.a(byteArrayOutputStream, ga.c(context));
            gk.a(byteArrayOutputStream, ga.d(context));
            gk.a(byteArrayOutputStream, ga.f(context));
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

    private static byte[] d(Context context) {
        return io.a(context, gp.g, 307200);
    }

    private static boolean e(Context context) {
        try {
            if (ge.m(context) != 1 || !a || io.a(context, gp.g) < 30) {
                return false;
            }
            long b = io.b(context, "c.log");
            long time = new Date().getTime();
            if (time - b < a.k) {
                return false;
            }
            io.a(context, time, "c.log");
            a = false;
            return true;
        } catch (Throwable th) {
            go.a(th, "StatisticsManager", "isUpdate");
            return false;
        }
    }
}
