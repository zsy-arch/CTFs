package com.amap.api.col;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.text.TextUtils;
import com.amap.api.col.hp;
import com.amap.api.col.ht;
import com.amap.api.col.ic;
import java.io.File;
import java.io.RandomAccessFile;
import java.util.List;

/* compiled from: DexDownLoad.java */
/* loaded from: classes.dex */
public class hk extends Thread implements ic.a {
    private hl a;
    private ic b;
    private gj c;
    private String d;
    private RandomAccessFile e;
    private Context f;

    public hk(Context context, hl hlVar, gj gjVar) {
        try {
            this.f = context.getApplicationContext();
            this.c = gjVar;
            if (hlVar != null) {
                this.a = hlVar;
                this.b = new ic(new ho(this.a));
                this.d = hp.a(context, this.a.a);
            }
        } catch (Throwable th) {
            hv.a(th, "dDownLoad", "DexDownLoad()");
        }
    }

    public void a() {
        try {
            start();
        } catch (Throwable th) {
            hv.a(th, "dDownLoad", "startDownload()");
        }
    }

    @Override // java.lang.Thread, java.lang.Runnable
    public void run() {
        try {
            if (c()) {
                im imVar = new im(this.f, this.c.a(), this.c.b(), "O008");
                imVar.a("{\"param_int_first\":0}");
                in.a(imVar, this.f);
                this.b.a(this);
            }
        } catch (Throwable th) {
            hv.a(th, "dDownLoad", "run()");
        }
    }

    private boolean a(gx gxVar, ht htVar, hl hlVar) {
        String str = hlVar.b;
        String str2 = hlVar.c;
        String str3 = hlVar.d;
        String str4 = hlVar.e;
        if ("errorstatus".equals(htVar.f())) {
            b(gxVar);
            return true;
        } else if (!new File(this.d).exists()) {
            return false;
        } else {
            List b = gxVar.b(ht.a(hp.a(this.f, str, str2), str, str2, str3), ht.class);
            if (b != null && b.size() > 0) {
                return true;
            }
            try {
                hp.a(this.f, str, this.c.b());
                hp.a(this.f, gxVar, this.c, this.d, str3);
                hp.a(this.f, this.c);
                return true;
            } catch (Throwable th) {
                hv.a(th, "dDownLoad", "processDownloadedFile()");
                return true;
            }
        }
    }

    private boolean a(gx gxVar) {
        try {
            List<ht> a = hp.a.a(gxVar, this.a.b, "used");
            if (a != null && a.size() > 0) {
                if (hv.a(a.get(0).e(), this.a.d) > 0) {
                    return true;
                }
            }
        } catch (Throwable th) {
            hv.a(th, "dDownLoad", "isUsed()");
        }
        return false;
    }

    private boolean f() {
        gx gxVar = new gx(this.f, hr.a());
        if (a(gxVar)) {
            return true;
        }
        ht a = hp.a.a(gxVar, this.a.a);
        if (a != null) {
            return a(gxVar, a, this.a);
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
        return ge.m(context) == 1;
    }

    boolean c() {
        try {
            if (!b() || !g() || !a(this.f) || f()) {
                return false;
            }
            hp.b(this.f, this.c.a());
            return true;
        } catch (Throwable th) {
            hv.a(th, "dDownLoad", "isNeedDownload()");
            return false;
        }
    }

    @Override // com.amap.api.col.ic.a
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
            hv.a(th, "dDownLoad", "onDownload()");
        }
    }

    @Override // com.amap.api.col.ic.a
    public void a(Throwable th) {
        try {
            hv.a(this.e);
        } catch (Throwable th2) {
            th2.printStackTrace();
        }
    }

    @Override // com.amap.api.col.ic.a
    public void e() {
        try {
            if (this.e != null) {
                hv.a(this.e);
                String b = this.a.b();
                if (hv.b(this.d, b)) {
                    a(b);
                    im imVar = new im(this.f, this.c.a(), this.c.b(), "O008");
                    imVar.a("{\"param_int_first\":1}");
                    in.a(imVar, this.f);
                } else {
                    new File(this.d).delete();
                }
            }
        } catch (Throwable th) {
            hv.a(th, "dDownLoad", "onFinish()");
        }
    }

    @Override // com.amap.api.col.ic.a
    public void d() {
    }

    private void b(gx gxVar) {
        if (!new File(hp.b(this.f, this.c.a(), this.c.b())).exists() && !TextUtils.isEmpty(hp.a(this.f, gxVar, this.c))) {
            try {
                hp.a(this.f, this.c);
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
    }

    private void a(String str) {
        String c = this.a.c();
        gx gxVar = new gx(this.f, hr.a());
        hp.a.a(gxVar, new ht.a(this.a.a, str, this.a.b, c, this.a.d).a("copy").a(), ht.a(this.a.a, this.a.b, c, this.a.d));
        a(this.f, this.a.b);
        try {
            hp.a(this.f, gxVar, this.c, this.d, this.a.d);
            hp.a(this.f, this.c);
        } catch (Throwable th) {
            hv.a(th, "dDownLoad", "onFinish1");
        }
    }

    private void a(Context context, String str) {
        try {
            SharedPreferences.Editor edit = context.getSharedPreferences(str, 0).edit();
            edit.clear();
            edit.commit();
        } catch (Throwable th) {
            hv.a(th, "dDownLoad", "clearMarker()");
        }
    }
}
