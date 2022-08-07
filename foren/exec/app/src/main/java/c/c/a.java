package c.c;

import java.util.Map;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class a extends h<K, V> {

    /* renamed from: d  reason: collision with root package name */
    public final /* synthetic */ b f688d;

    public a(b bVar) {
        this.f688d = bVar;
    }

    @Override // c.c.h
    public Object a(int i, int i2) {
        return this.f688d.f[(i << 1) + i2];
    }

    @Override // c.c.h
    public int b(Object obj) {
        return this.f688d.b(obj);
    }

    @Override // c.c.h
    public int c() {
        return this.f688d.g;
    }

    @Override // c.c.h
    public int a(Object obj) {
        return this.f688d.a(obj);
    }

    @Override // c.c.h
    public Map<K, V> b() {
        return this.f688d;
    }

    @Override // c.c.h
    public void a(K k, V v) {
        this.f688d.put(k, v);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // c.c.h
    public V a(int i, V v) {
        int i2 = (i << 1) + 1;
        Object[] objArr = this.f688d.f;
        V v2 = objArr[i2];
        objArr[i2] = v;
        return v2;
    }

    @Override // c.c.h
    public void a(int i) {
        this.f688d.c(i);
    }

    @Override // c.c.h
    public void a() {
        this.f688d.clear();
    }
}
