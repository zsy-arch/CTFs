package com.amap.api.services.a;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.text.TextUtils;
import com.amap.api.services.a.cj;
import com.amap.api.services.a.cn;
import com.amap.api.services.a.cv;
import java.io.File;
import java.io.RandomAccessFile;
import java.util.List;

/* compiled from: DexDownLoad.java */
/* loaded from: classes.dex */
public class cd extends Thread implements cv.a {
    private ce a;
    private cv b;
    private be c;
    private String d;
    private RandomAccessFile e;
    private Context f;

    public cd(Context context, ce ceVar, be beVar) {
        try {
            this.f = context.getApplicationContext();
            this.c = beVar;
            if (ceVar != null) {
                this.a = ceVar;
                this.b = new cv(new ci(this.a));
                this.d = cj.a(context, this.a.a);
            }
        } catch (Throwable th) {
            co.a(th, "dDownLoad", "DexDownLoad()");
        }
    }

    public void a() {
        try {
            start();
        } catch (Throwable th) {
            co.a(th, "dDownLoad", "startDownload()");
        }
    }

    @Override // java.lang.Thread, java.lang.Runnable
    public void run() {
        try {
            if (c()) {
                de deVar = new de(this.f, this.c.a(), this.c.b(), "O008");
                deVar.a("{\"param_int_first\":0}");
                df.a(deVar, this.f);
                this.b.a(this);
            }
        } catch (Throwable th) {
            co.a(th, "dDownLoad", "run()");
        }
    }

    private boolean a(bq bqVar, cn cnVar, ce ceVar) {
        String str = ceVar.b;
        String str2 = ceVar.c;
        String str3 = ceVar.d;
        String str4 = ceVar.e;
        if ("errorstatus".equals(cnVar.f())) {
            b(bqVar);
            return true;
        } else if (!new File(this.d).exists()) {
            return false;
        } else {
            List b = bqVar.b(cn.a(cj.a(this.f, str, str2), str, str2, str3), cn.class);
            if (b != null && b.size() > 0) {
                return true;
            }
            try {
                cj.a(this.f, str, this.c.b());
                cj.a(this.f, bqVar, this.c, this.d, str3);
                cj.a(this.f, this.c);
                return true;
            } catch (Throwable th) {
                co.a(th, "dDownLoad", "processDownloadedFile()");
                return true;
            }
        }
    }

    private boolean a(bq bqVar) {
        try {
            List<cn> a = cj.a.a(bqVar, this.a.b, "used");
            if (a != null && a.size() > 0) {
                if (co.a(a.get(0).e(), this.a.d) > 0) {
                    return true;
                }
            }
        } catch (Throwable th) {
            co.a(th, "dDownLoad", "isUsed()");
        }
        return false;
    }

    private boolean f() {
        bq bqVar = new bq(this.f, cm.c());
        if (a(bqVar)) {
            return true;
        }
        cn a = cj.a.a(bqVar, this.a.a);
        if (a != null) {
            return a(bqVar, a, this.a);
        }
        return false;
    }

    boolean b() {
        return this.c != null && this.c.a().equals(this.a.b) && this.c.b().equals(this.a.c);
    }

    private boolean g() {
        return Build.VERSION.SDK_INT >= this.a.g && Build.VERSION.SDK_INT <= this.a.f;
    }

    private boolean a(Context context) {
        return ba.m(context) == 1;
    }

    boolean c() {
        try {
            if (!b() || !g() || !a(this.f) || f()) {
                return false;
            }
            cj.b(this.f, this.c.a());
            return true;
        } catch (Throwable th) {
            co.a(th, "dDownLoad", "isNeedDownload()");
            return false;
        }
    }

    @Override // com.amap.api.services.a.cv.a
    public void a(byte[] bArr, long j) {
        try {
            if (this.e == null) {
                File file = new File(this.d);
                File parentFile = file.getParentFile();
                if (!parentFile.exists()) {
                    parentFile.mkdirs();
                }
                this.e = new RandomAccessFile(file, "rw");
            }
            this.e.seek(j);
            this.e.write(bArr);
        } catch (Throwable th) {
            co.a(th, "dDownLoad", "onDownload()");
        }
    }

    @Override // com.amap.api.services.a.cv.a
    public void a(Throwable th) {
        try {
            co.a(this.e);
        } catch (Throwable th2) {
            th2.printStackTrace();
        }
    }

    @Override // com.amap.api.services.a.cv.a
    public void d() {
        try {
            if (this.e != null) {
                co.a(this.e);
                String b = this.a.b();
                if (co.b(this.d, b)) {
                    a(b);
                    de deVar = new de(this.f, this.c.a(), this.c.b(), "O008");
                    deVar.a("{\"param_int_first\":1}");
                    df.a(deVar, this.f);
                } else {
                    new File(this.d).delete();
                }
            }
        } catch (Throwable th) {
            co.a(th, "dDownLoad", "onFinish()");
        }
    }

    @Override // com.amap.api.services.a.cv.a
    public void e() {
    }

    private void b(bq bqVar) {
        if (!new File(cj.b(this.f, this.c.a(), this.c.b())).exists() && !TextUtils.isEmpty(cj.a(this.f, bqVar, this.c))) {
            try {
                cj.a(this.f, this.c);
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
    }

    private void a(String str) {
        String c = this.a.c();
        bq bqVar = new bq(this.f, cm.c());
        cj.a.a(bqVar, new cn.a(this.a.a, str, this.a.b, c, this.a.d).a("copy").a(), cn.a(this.a.a, this.a.b, c, this.a.d));
        a(this.f, this.a.b);
        try {
            cj.a(this.f, bqVar, this.c, this.d, this.a.d);
            cj.a(this.f, this.c);
        } catch (Throwable th) {
            co.a(th, "dDownLoad", "onFinish1");
        }
    }

    private void a(Context context, String str) {
        try {
            SharedPreferences.Editor edit = context.getSharedPreferences(str, 0).edit();
            edit.clear();
            edit.commit();
        } catch (Throwable th) {
            co.a(th, "dDownLoad", "clearMarker()");
        }
    }
}
