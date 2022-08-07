package com.tencent.smtt.utils;

import android.annotation.TargetApi;
import android.content.Context;
import java.io.File;
import java.io.PrintWriter;
import java.io.StringWriter;

/* loaded from: classes2.dex */
public class x {
    private static x c = null;
    private Context a;
    private File b = null;
    private String d = "http://log.tbs.qq.com/ajax?c=pu&v=2&k=";
    private String e = "http://log.tbs.qq.com/ajax?c=pu&tk=";
    private String f = "http://wup.imtt.qq.com:8080";
    private String g = "http://log.tbs.qq.com/ajax?c=dl&k=";
    private String h = "http://cfg.imtt.qq.com/tbs?v=2&mk=";
    private String i = "http://log.tbs.qq.com/ajax?c=ul&v=2&k=";
    private String j = "http://mqqad.html5.qq.com/adjs";
    private String k = "http://log.tbs.qq.com/ajax?c=ucfu&k=";

    @TargetApi(11)
    private x(Context context) {
        this.a = null;
        TbsLog.w("TbsCommonConfig", "TbsCommonConfig constructing...");
        this.a = context.getApplicationContext();
        g();
    }

    public static synchronized x a() {
        x xVar;
        synchronized (x.class) {
            xVar = c;
        }
        return xVar;
    }

    public static synchronized x a(Context context) {
        x xVar;
        synchronized (x.class) {
            if (c == null) {
                c = new x(context);
            }
            xVar = c;
        }
        return xVar;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:64:0x0103 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Type inference failed for: r1v23, types: [boolean] */
    /* JADX WARN: Type inference failed for: r1v25 */
    /* JADX WARN: Type inference failed for: r1v26 */
    /* JADX WARN: Type inference failed for: r1v27 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private synchronized void g() {
        /*
            Method dump skipped, instructions count: 282
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.utils.x.g():void");
    }

    private File h() {
        File file;
        try {
            if (this.b == null) {
                this.b = new File(k.a(this.a, 5));
                if (this.b == null || !this.b.isDirectory()) {
                    return null;
                }
            }
            file = new File(this.b, "tbsnet.conf");
            if (!file.exists()) {
                TbsLog.e("TbsCommonConfig", "Get file(" + file.getCanonicalPath() + ") failed!");
                return null;
            }
            try {
                TbsLog.w("TbsCommonConfig", "pathc:" + file.getCanonicalPath());
                return file;
            } catch (Throwable th) {
                th = th;
                StringWriter stringWriter = new StringWriter();
                th.printStackTrace(new PrintWriter(stringWriter));
                TbsLog.e("TbsCommonConfig", "exceptions occurred2:" + stringWriter.toString());
                return file;
            }
        } catch (Throwable th2) {
            th = th2;
            file = null;
        }
    }

    public String b() {
        return this.d;
    }

    public String c() {
        return this.g;
    }

    public String d() {
        return this.h;
    }

    public String e() {
        return this.i;
    }

    public String f() {
        return this.e;
    }
}
