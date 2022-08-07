package d.a.a;

import c.a.a.C;
import d.a.a.c.a.g;

/* loaded from: classes.dex */
class d implements g {

    /* renamed from: a */
    public final /* synthetic */ e f1618a;

    public d(e eVar) {
        this.f1618a = eVar;
    }

    @Override // d.a.a.c.a.g
    public void a() {
        h.c(this.f1618a.f1653a);
        C.a(this.f1618a.f1653a.getString(e.b.a.g.not_get_permission), 0).show();
    }

    @Override // d.a.a.c.a.g
    public void onSuccess() {
        this.f1618a.f1653a.n();
    }
}
