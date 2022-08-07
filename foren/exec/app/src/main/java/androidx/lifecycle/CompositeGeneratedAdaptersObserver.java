package androidx.lifecycle;

import c.j.d;
import c.j.e;
import c.j.f;
import c.j.h;
import c.j.l;

/* loaded from: classes.dex */
public class CompositeGeneratedAdaptersObserver implements e {

    /* renamed from: a  reason: collision with root package name */
    public final d[] f266a;

    @Override // c.j.e
    public void a(h hVar, f.a aVar) {
        l lVar = new l();
        for (d dVar : this.f266a) {
            dVar.a(hVar, aVar, false, lVar);
        }
        for (d dVar2 : this.f266a) {
            dVar2.a(hVar, aVar, true, lVar);
        }
    }
}
