package com.baidu.mobstat;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;
import java.lang.ref.WeakReference;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class cg implements Runnable {
    final /* synthetic */ ca a;
    private long b;
    private WeakReference<Context> c;
    private WeakReference<Fragment> d;
    private WeakReference<Object> e;
    private long f;
    private WeakReference<Context> g;
    private WeakReference<Fragment> h;
    private WeakReference<Object> i;
    private int j;
    private String k;

    public cg(ca caVar, long j, Context context, Fragment fragment, long j2, Context context2, Fragment fragment2, int i, String str, Object obj, Object obj2) {
        this.a = caVar;
        this.k = null;
        this.b = j;
        this.f = j2;
        this.c = new WeakReference<>(context);
        this.g = new WeakReference<>(context2);
        this.d = new WeakReference<>(fragment);
        this.h = new WeakReference<>(fragment2);
        this.i = new WeakReference<>(obj);
        this.e = new WeakReference<>(obj2);
        this.j = i;
        this.k = str;
    }

    @Override // java.lang.Runnable
    public void run() {
        by byVar;
        by byVar2;
        by byVar3;
        cf b;
        cf b2;
        if (this.j == 1) {
            Context context = this.c.get();
            Context context2 = this.g.get();
            if (context == null || context2 == null) {
                cr.c("onPause, WeakReference is already been released");
            } else if (context == context2) {
                long j = this.b - this.f;
                StringBuilder sb = new StringBuilder();
                if (this.k != null) {
                    sb.append(this.k);
                    b2 = this.a.b(this.k);
                    if (b2 != null) {
                        j = b2.d - b2.c;
                        cr.c("page time = " + b2.a + "; time = " + j);
                        if (j < 20) {
                            cr.c("page time little than 20 mills.");
                            return;
                        }
                    } else {
                        j = j;
                    }
                } else if (!(context instanceof Activity)) {
                    cr.c("onPause, pause is not a Activity");
                    return;
                } else {
                    sb.append(((Activity) context).getComponentName().getShortClassName());
                    if (sb.charAt(0) == '.') {
                        sb.deleteCharAt(0);
                    }
                }
                cr.a("new page view, page name = " + sb.toString() + ", stay time = " + j + "(ms)");
                byVar3 = this.a.j;
                byVar3.a(sb.toString(), j, this.f);
                if (this.k != null) {
                    b = this.a.b(this.k);
                    if (b != null) {
                        this.a.c(context, b.d);
                        this.a.c(this.k);
                        return;
                    }
                    return;
                }
                this.a.c(context, this.b);
            } else if (this.k != null) {
                cr.b("onPageStart() or onPageEnd() install error.");
            } else {
                cr.b("onPause() or onResume() install error.");
            }
        } else if (this.j == 2) {
            Fragment fragment = this.d.get();
            Fragment fragment2 = this.h.get();
            if (fragment == null || fragment2 == null) {
                cr.c("onPause, WeakReference is already been released");
            } else if (fragment != fragment2) {
                cr.c("onPause() or onResume() install error.");
            } else {
                long j2 = this.b - this.f;
                String name = fragment.getClass().getName();
                String substring = name.substring(name.lastIndexOf(".") + 1);
                cr.a("Fragment new page view, page name = " + name.toString() + ", stay time = " + j2 + "(ms)");
                byVar2 = this.a.j;
                byVar2.a(substring, j2, this.f);
                this.a.c(fragment.getActivity(), this.b);
            }
        } else if (this.j == 3) {
            Object obj = this.e.get();
            Object obj2 = this.i.get();
            if (obj == null || obj2 == null) {
                cr.c("onPause, WeakReference is already been released");
            } else if (obj != obj2) {
                cr.c("onPause() or onResume() install error.");
            } else {
                long j3 = this.b - this.f;
                Context a = ca.a(obj);
                if (a == null) {
                    cr.c("getContxtFromReverse faild.");
                    return;
                }
                String name2 = obj.getClass().getName();
                String substring2 = name2.substring(name2.lastIndexOf(".") + 1);
                cr.a("android.app.Fragment new page view, page name = " + name2.toString() + "; stay time = " + j3 + "(ms)");
                byVar = this.a.j;
                byVar.a(substring2, j3, this.f);
                this.a.c(a, this.b);
            }
        }
    }
}
