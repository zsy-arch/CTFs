package d.a.a;

import c.a.a.C;
import d.a.a.c.a.g;

/* loaded from: classes.dex */
class f implements g {

    /* renamed from: a */
    public final /* synthetic */ g f1654a;

    public f(g gVar) {
        this.f1654a = gVar;
    }

    @Override // d.a.a.c.a.g
    public void a() {
        h.c(this.f1654a.f1655a);
        C.a(this.f1654a.f1655a.getString(e.b.a.g.not_get_permission), 0).show();
    }

    @Override // d.a.a.c.a.g
    public void onSuccess() {
        this.f1654a.f1655a.o();
    }
}
