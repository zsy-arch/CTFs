package com.baidu.mobstat;

import android.content.Context;
import android.support.v4.app.Fragment;
import java.lang.ref.WeakReference;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class ch implements Runnable {
    final /* synthetic */ ca a;
    private long b;
    private long c;
    private WeakReference<Context> d;
    private WeakReference<Fragment> e;
    private WeakReference<Object> f;
    private int g;

    public ch(ca caVar, long j, long j2, Context context, Fragment fragment, Object obj, int i) {
        this.a = caVar;
        this.g = 1;
        this.b = j;
        this.c = j2;
        this.d = new WeakReference<>(context);
        this.e = new WeakReference<>(fragment);
        this.f = new WeakReference<>(obj);
        this.g = i;
    }

    @Override // java.lang.Runnable
    public void run() {
        by byVar;
        by byVar2;
        by byVar3;
        if (this.c - this.b >= this.a.b() && this.b > 0) {
            Context context = this.d.get();
            Fragment fragment = this.e.get();
            Object obj = this.f.get();
            if (context != null || fragment != null || obj != null) {
                byVar = this.a.j;
                byVar.b(this.b);
                byVar2 = this.a.j;
                String jSONObject = byVar2.c().toString();
                cr.a("new session:" + jSONObject);
                DataCore.instance().putSession(jSONObject);
                context = null;
                if (this.g == 1) {
                    DataCore.instance().flush(context);
                } else if (this.g == 2) {
                    context = fragment.getActivity();
                    DataCore.instance().flush(context);
                } else if (this.g == 3) {
                    context = ca.a(obj);
                    DataCore.instance().flush(context);
                }
                byVar3 = this.a.j;
                byVar3.b();
                this.a.a(context);
                if (context != null) {
                    bs.a().b(context);
                }
            }
        }
    }
}
