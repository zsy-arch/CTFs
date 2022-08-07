package androidx.lifecycle;

import c.j.c;
import c.j.e;
import c.j.f;
import c.j.h;

/* loaded from: classes.dex */
public class FullLifecycleObserverAdapter implements e {

    /* renamed from: a  reason: collision with root package name */
    public final c f267a;

    @Override // c.j.e
    public void a(h hVar, f.a aVar) {
        switch (aVar.ordinal()) {
            case 0:
                this.f267a.b(hVar);
                return;
            case 1:
                this.f267a.f(hVar);
                return;
            case 2:
                this.f267a.a(hVar);
                return;
            case 3:
                this.f267a.c(hVar);
                return;
            case 4:
                this.f267a.d(hVar);
                return;
            case 5:
                this.f267a.e(hVar);
                return;
            case 6:
                throw new IllegalArgumentException("ON_ANY must not been send by anybody");
            default:
                return;
        }
    }
}
