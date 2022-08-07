package com.baidu.mobstat;

import android.content.Context;
import android.os.Handler;
import android.os.HandlerThread;
import android.support.v4.app.Fragment;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class ca {
    private static final ca a = new ca();
    private static HashMap<String, cf> p = new HashMap<>();
    private Handler b;
    private WeakReference<Context> g;
    private WeakReference<Fragment> h;
    private WeakReference<Object> i;
    private long c = 0;
    private long d = 0;
    private long e = 0;
    private long f = 0;
    private by j = new by();
    private int k = -1;
    private boolean l = true;
    private boolean m = false;
    private boolean n = false;
    private boolean o = false;

    private ca() {
        HandlerThread handlerThread = new HandlerThread("SessionAnalysisThread");
        handlerThread.start();
        handlerThread.setPriority(10);
        this.b = new Handler(handlerThread.getLooper());
    }

    public static Context a(Object obj) {
        try {
            return (Context) obj.getClass().getMethod("getActivity", new Class[0]).invoke(obj, new Object[0]);
        } catch (Throwable th) {
            cr.a(th.getMessage());
            return null;
        }
    }

    public static ca a() {
        return a;
    }

    public void a(Context context) {
        if (context == null) {
            cr.a("clearLastSession(Context context):context=null");
            return;
        }
        cl.a(context, cu.p(context) + "__local_last_session.json", new JSONObject().toString(), false);
    }

    private void a(String str) {
        synchronized (p) {
            if (str == null) {
                cr.c("page Object is null");
                return;
            }
            cf cfVar = new cf(str);
            if (!p.containsKey(str)) {
                p.put(str, cfVar);
            }
        }
    }

    private void a(boolean z) {
        this.l = z;
    }

    public cf b(String str) {
        cf cfVar;
        synchronized (p) {
            if (str == null) {
                cr.c("pageName is null");
                cfVar = null;
            } else {
                if (!p.containsKey(str)) {
                    a(str);
                }
                cfVar = p.get(str);
            }
        }
        return cfVar;
    }

    public void c(Context context, long j) {
        cr.a("flush current session to last_session.json");
        new JSONObject();
        JSONObject c = this.j.c();
        try {
            c.put("e", j);
        } catch (JSONException e) {
            cr.a("StatSession.flushSession() failed");
        }
        String jSONObject = c.toString();
        cr.a("cacheString = " + jSONObject);
        cl.a(context, cu.p(context) + "__local_last_session.json", jSONObject, false);
    }

    public void c(String str) {
        synchronized (p) {
            if (str == null) {
                cr.c("pageName is null");
                return;
            }
            if (p.containsKey(str)) {
                p.remove(str);
            }
        }
    }

    private boolean e() {
        return this.l;
    }

    public void a(int i) {
        this.k = i * 1000;
    }

    public void a(Context context, long j) {
        cr.a("AnalysisResume job");
        if (this.m) {
            cr.c("遗漏StatService.onPause() || missing StatService.onPause()");
        }
        this.m = true;
        if (e()) {
            cr.a("is_first_resume=true");
            a(false);
            this.b.post(new cc(this));
        } else {
            cr.a(" is_first_resume=false");
        }
        this.b.post(new ch(this, this.c, j, context, null, null, 1));
        this.g = new WeakReference<>(context);
        this.d = j;
    }

    public void a(Context context, long j, String str) {
        cr.a("AnalysisPageStart");
        if (b(str) == null) {
            cr.c("自定义页面 pageName 为 null");
            return;
        }
        if (b(str).b) {
            cr.c("遗漏StatService.onPageEnd() || missing StatService.onPageEnd()");
        }
        b(str).b = true;
        b(str).c = j;
        if (e()) {
            cr.b("is_first_resume=true");
            a(false);
            this.b.post(new cb(this));
        } else {
            cr.b(" is_first_resume=false");
        }
        this.b.post(new ch(this, this.c, j, context, null, null, 1));
        this.g = new WeakReference<>(context);
        this.d = j;
    }

    public void a(Fragment fragment, long j) {
        cr.a("post resume job");
        if (this.n) {
            cr.c("遗漏StatService.onPause() || missing StatService.onPause()");
        }
        this.n = true;
        if (e()) {
            cr.a("is_first_resume=true");
            a(false);
            this.b.post(new cd(this));
        } else {
            cr.a("is_first_resume=false");
        }
        this.b.post(new ch(this, this.c, j, null, fragment, null, 2));
        this.h = new WeakReference<>(fragment);
        this.e = j;
    }

    public void a(Object obj, long j) {
        cr.a("post resume job");
        if (this.o) {
            cr.c("遗漏StatService.onPause() || missing StatService.onPause()");
        }
        this.o = true;
        if (e()) {
            cr.a("is_first_resume=true");
            a(false);
            this.b.post(new ce(this));
        } else {
            cr.a("is_first_resume=false");
        }
        this.b.post(new ch(this, this.c, j, null, null, obj, 3));
        this.i = new WeakReference<>(obj);
        this.f = j;
    }

    public int b() {
        if (this.k == -1) {
            this.k = 30000;
        }
        return this.k;
    }

    public void b(Context context, long j) {
        cr.a("post pause job");
        if (!this.m) {
            cr.c("遗漏StatService.onResume() || missing StatService.onResume()");
            return;
        }
        this.m = false;
        this.b.post(new cg(this, j, context, null, this.d, this.g.get(), null, 1, null, null, null));
        this.c = j;
    }

    public void b(Context context, long j, String str) {
        cr.a("post pause job");
        cf b = b(str);
        if (b == null) {
            cr.c("自定义页面 pageName 为 null");
        } else if (!b.b) {
            cr.c("Please check (1)遗漏StatService.onPageStart() || missing StatService.onPageStart()");
        } else {
            b.b = false;
            b.d = j;
            this.b.post(new cg(this, j, context, null, b.c, this.g.get(), null, 1, str, null, null));
            this.c = j;
        }
    }

    public void b(Fragment fragment, long j) {
        cr.a("post pause job");
        if (!this.n) {
            cr.c("遗漏android.support.v4.app.Fragment StatService.onResume() || android.support.v4.app.Fragment missing StatService.onResume()");
            return;
        }
        this.n = false;
        this.b.post(new cg(this, j, null, fragment, this.e, null, this.h.get(), 2, null, null, null));
        this.c = j;
    }

    public void b(Object obj, long j) {
        cr.a("post pause job");
        if (!this.o) {
            cr.c("遗漏android.app.Fragment StatService.onResume() || android.app.Fragment missing StatService.onResume()");
            return;
        }
        this.o = false;
        this.b.post(new cg(this, j, null, null, this.f, null, null, 3, null, this.i.get(), obj));
        this.c = j;
    }

    public void c() {
        this.j.a(this.j.d() + 1);
    }

    public long d() {
        return this.j.a();
    }
}
