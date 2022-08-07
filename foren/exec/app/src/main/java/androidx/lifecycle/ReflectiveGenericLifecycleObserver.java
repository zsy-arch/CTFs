package androidx.lifecycle;

import c.j.a;
import c.j.e;
import c.j.f;
import c.j.h;

/* loaded from: classes.dex */
public class ReflectiveGenericLifecycleObserver implements e {

    /* renamed from: a  reason: collision with root package name */
    public final Object f278a;

    /* renamed from: b  reason: collision with root package name */
    public final a f279b;

    @Override // c.j.e
    public void a(h hVar, f.a aVar) {
        a aVar2 = this.f279b;
        Object obj = this.f278a;
        a.a(aVar2.f1017a.get(aVar), hVar, aVar, obj);
        a.a(aVar2.f1017a.get(f.a.ON_ANY), hVar, aVar, obj);
    }
}
