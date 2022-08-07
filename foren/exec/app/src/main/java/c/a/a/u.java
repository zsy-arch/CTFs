package c.a.a;

import android.view.View;
import c.e.h.s;
import c.e.h.t;

/* loaded from: classes.dex */
class u extends t {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ v f372a;

    public u(v vVar) {
        this.f372a = vVar;
    }

    @Override // c.e.h.s
    public void b(View view) {
        this.f372a.f373a.q.setAlpha(1.0f);
        this.f372a.f373a.t.a((s) null);
        this.f372a.f373a.t = null;
    }

    @Override // c.e.h.t, c.e.h.s
    public void c(View view) {
        this.f372a.f373a.q.setVisibility(0);
    }
}
