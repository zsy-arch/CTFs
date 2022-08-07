package c.c;

import java.util.Map;

/* loaded from: classes.dex */
public class c extends h<E, E> {

    /* renamed from: d  reason: collision with root package name */
    public final /* synthetic */ d f689d;

    public c(d dVar) {
        this.f689d = dVar;
    }

    @Override // c.c.h
    public Object a(int i, int i2) {
        return this.f689d.h[i];
    }

    @Override // c.c.h
    public int b(Object obj) {
        return this.f689d.indexOf(obj);
    }

    @Override // c.c.h
    public int c() {
        return this.f689d.i;
    }

    @Override // c.c.h
    public int a(Object obj) {
        return this.f689d.indexOf(obj);
    }

    @Override // c.c.h
    public Map<E, E> b() {
        throw new UnsupportedOperationException("not a map");
    }

    @Override // c.c.h
    public void a(E e2, E e3) {
        this.f689d.add(e2);
    }

    @Override // c.c.h
    public E a(int i, E e2) {
        throw new UnsupportedOperationException("not a map");
    }

    @Override // c.c.h
    public void a(int i) {
        this.f689d.b(i);
    }

    @Override // c.c.h
    public void a() {
        d dVar = this.f689d;
        int i = dVar.i;
        if (i != 0) {
            d.a(dVar.g, dVar.h, i);
            dVar.g = d.f690a;
            dVar.h = d.f691b;
            dVar.i = 0;
        }
    }
}
