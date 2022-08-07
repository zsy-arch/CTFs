package com.tencent.smtt.utils;

import android.annotation.TargetApi;
import android.content.Context;
import android.text.TextUtils;
import java.io.File;
import java.io.PrintWriter;
import java.io.StringWriter;

/* loaded from: classes.dex */
public class m {

    /* renamed from: c  reason: collision with root package name */
    public static m f1580c;

    /* renamed from: a  reason: collision with root package name */
    public Context f1581a;

    /* renamed from: b  reason: collision with root package name */
    public File f1582b = null;

    /* renamed from: d  reason: collision with root package name */
    public String f1583d = "https://log.tbs.qq.com/ajax?c=pu&v=2&k=";

    /* renamed from: e  reason: collision with root package name */
    public String f1584e = "https://log.tbs.qq.com/ajax?c=pu&tk=";
    public String f = "https://log.tbs.qq.com/ajax?c=dl&k=";
    public String g = "https://cfg.imtt.qq.com/tbs?v=2&mk=";
    public String h = "https://log.tbs.qq.com/ajax?c=ul&v=2&k=";
    public String i = "https://mqqad.html5.qq.com/adjs";
    public String j = "https://log.tbs.qq.com/ajax?c=ucfu&k=";

    @TargetApi(11)
    public m(Context context) {
        this.f1581a = null;
        TbsLog.w("TbsCommonConfig", "TbsCommonConfig constructing...");
        this.f1581a = context.getApplicationContext();
        g();
    }

    public static synchronized m a() {
        m mVar;
        synchronized (m.class) {
            mVar = f1580c;
        }
        return mVar;
    }

    public static synchronized m a(Context context) {
        m mVar;
        synchronized (m.class) {
            if (f1580c == null) {
                f1580c = new m(context);
            }
            mVar = f1580c;
        }
        return mVar;
    }

    /* JADX WARN: Removed duplicated region for block: B:61:0x00e9 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private synchronized void g() {
        /*
            Method dump skipped, instructions count: 245
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.utils.m.g():void");
    }

    private File h() {
        Throwable th;
        File file;
        File file2 = null;
        try {
            if (this.f1582b == null) {
                String str = this.f1581a.getApplicationContext().getApplicationInfo().packageName;
                if (!TextUtils.isEmpty(str)) {
                    boolean z = true;
                    boolean z2 = this.f1581a.getPackageManager().checkPermission("android.permission.READ_EXTERNAL_STORAGE", str) == 0;
                    if (this.f1581a.getPackageManager().checkPermission("android.permission.WRITE_EXTERNAL_STORAGE", str) != 0) {
                        z = false;
                    }
                    if (!z2 && !z) {
                        file = new File(FileUtil.a(this.f1581a, 8));
                    }
                    TbsLog.i("TbsCommonConfig", "no permission,use sdcard default folder");
                    file = new File(FileUtil.a(this.f1581a, 5));
                } else {
                    file = new File(FileUtil.a(this.f1581a, 8));
                }
                this.f1582b = file;
                if (this.f1582b == null || !this.f1582b.isDirectory()) {
                    return null;
                }
            }
            File file3 = new File(this.f1582b, "tbsnet.conf");
            if (!file3.exists()) {
                TbsLog.e("TbsCommonConfig", "Get file(" + file3.getCanonicalPath() + ") failed!");
                return null;
            }
            try {
                TbsLog.w("TbsCommonConfig", "pathc:" + file3.getCanonicalPath());
                return file3;
            } catch (Throwable th2) {
                th = th2;
                file2 = file3;
                StringWriter stringWriter = new StringWriter();
                th.printStackTrace(new PrintWriter(stringWriter));
                TbsLog.e("TbsCommonConfig", "exceptions occurred2:" + stringWriter.toString());
                return file2;
            }
        } catch (Throwable th3) {
            th = th3;
        }
    }

    public String b() {
        return this.f1583d;
    }

    public String c() {
        return this.f;
    }

    public String d() {
        return this.g;
    }

    public String e() {
        return this.h;
    }

    public String f() {
        return this.f1584e;
    }
}
