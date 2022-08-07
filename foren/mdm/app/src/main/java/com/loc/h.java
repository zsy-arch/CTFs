package com.loc;

import android.content.Context;
import android.text.TextUtils;
import com.amap.api.location.AMapLocation;
import java.util.List;
import java.util.concurrent.ExecutorService;
import org.json.JSONObject;

/* compiled from: LastLocationManager.java */
/* loaded from: classes2.dex */
public final class h {
    private Context b;
    private String c;
    private af f;
    private cj d = null;
    private cj e = null;
    private ExecutorService g = null;
    private long h = 0;
    Runnable a = new Runnable() { // from class: com.loc.h.1
        @Override // java.lang.Runnable
        public final void run() {
            h.this.c();
        }
    };

    public h(Context context) {
        this.c = null;
        this.f = null;
        this.b = context.getApplicationContext();
        try {
            this.c = ce.a("MD5", n.q(this.b));
            ae a = af.a((Class<? extends ae>) ck.class);
            if (a != null) {
                this.f = new af(context, a, cx.i());
            }
        } catch (Throwable th) {
            f.a(th, "LastLocationManager", "<init>:DBOperation");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void c() {
        String str;
        String str2 = null;
        synchronized (this) {
            if (!(this.d == null || !cx.a(this.d.a()) || this.f == null || this.d == this.e)) {
                String str3 = this.d.a().toStr();
                String b = this.d.b();
                this.e = this.d;
                if (!TextUtils.isEmpty(str3)) {
                    str = o.a(ce.c(str3.getBytes("UTF-8"), this.c));
                    if (!TextUtils.isEmpty(b)) {
                        str2 = o.a(ce.c(b.getBytes("UTF-8"), this.c));
                    }
                } else {
                    str = null;
                }
                if (!TextUtils.isEmpty(str)) {
                    cj cjVar = new cj();
                    cjVar.b(str);
                    cjVar.a(cx.b());
                    cjVar.a(str2);
                    this.f.a(cjVar, "_id=1");
                    this.h = cx.b();
                }
            }
        }
    }

    private cj d() {
        cj cjVar;
        byte[] d;
        byte[] d2;
        String str = null;
        if (this.b == null) {
            return null;
        }
        try {
            if (this.f == null) {
                return null;
            }
            List b = this.f.b("_id=1", cj.class);
            if (b == null || b.size() <= 0) {
                cjVar = null;
            } else {
                cjVar = (cj) b.get(0);
                try {
                    byte[] b2 = o.b(cjVar.c());
                    str = (b2 == null || b2.length <= 0 || (d2 = ce.d(b2, this.c)) == null || d2.length <= 0) ? null : new String(d2, "UTF-8");
                    byte[] b3 = o.b(cjVar.b());
                    if (b3 != null && b3.length > 0 && (d = ce.d(b3, this.c)) != null && d.length > 0) {
                        str = new String(d, "UTF-8");
                    }
                    cjVar.a(str);
                } catch (Throwable th) {
                    th = th;
                    f.a(th, "LastLocationManager", "readLastFix");
                    return cjVar;
                }
            }
            if (TextUtils.isEmpty(str)) {
                return cjVar;
            }
            AMapLocation aMapLocation = new AMapLocation("");
            f.a(aMapLocation, new JSONObject(str));
            if (!cx.b(aMapLocation)) {
                return cjVar;
            }
            cjVar.a(aMapLocation);
            return cjVar;
        } catch (Throwable th2) {
            th = th2;
            cjVar = null;
        }
    }

    public final AMapLocation a() {
        if (this.d == null) {
            this.d = d();
        }
        if (this.d != null && cx.a(this.d.a())) {
            return this.d.a();
        }
        return null;
    }

    public final AMapLocation a(String str) {
        if (this.d == null) {
            this.d = d();
        }
        if (this.d == null || this.d.a() == null) {
            return null;
        }
        if (!TextUtils.isEmpty(str)) {
            cf.a();
            if (!cf.a(this.d.b(), str)) {
                return null;
            }
            AMapLocation a = this.d.a();
            a.setLocationType(4);
            return a;
        } else if (cx.b() - this.d.d() > 30000) {
            return null;
        } else {
            AMapLocation a2 = this.d.a();
            a2.setLocationType(4);
            return a2;
        }
    }

    public final void a(cj cjVar) {
        if (this.b != null && cjVar != null && cx.a(cjVar.a()) && cjVar.a().getLocationType() != 2) {
            try {
                this.d = cjVar;
                if ((this.e == null || cx.a(this.e.a(), cjVar.a()) > 50.0f) && cx.b() - this.h > 30000) {
                    if (this.g == null) {
                        this.g = z.b();
                    }
                    if (!this.g.isShutdown()) {
                        this.g.submit(this.a);
                    }
                }
            } catch (Throwable th) {
                f.a(th, "LastLocationManager", "setLastFix");
            }
        }
    }

    public final synchronized void b() {
        c();
        if (this.g != null) {
            this.g.shutdown();
            this.g = null;
        }
        this.h = 0L;
    }
}
