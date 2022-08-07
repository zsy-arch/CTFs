package c.b.a.b;

import c.b.a.b.b;
import java.util.HashMap;

/* loaded from: classes.dex */
public class a<K, V> extends b<K, V> {

    /* renamed from: e */
    public HashMap<K, b.c<K, V>> f674e = new HashMap<>();

    @Override // c.b.a.b.b
    public b.c<K, V> a(K k) {
        return this.f674e.get(k);
    }

    public boolean contains(K k) {
        return this.f674e.containsKey(k);
    }

    @Override // c.b.a.b.b
    public V remove(K k) {
        b.c<K, V> a2 = a(k);
        V v = null;
        if (a2 != null) {
            this.f678d--;
            if (!this.f677c.isEmpty()) {
                for (b.f<K, V> fVar : this.f677c.keySet()) {
                    fVar.a(a2);
                }
            }
            b.c<K, V> cVar = a2.f682d;
            if (cVar != null) {
                cVar.f681c = a2.f681c;
            } else {
                this.f675a = a2.f681c;
            }
            b.c<K, V> cVar2 = a2.f681c;
            if (cVar2 != null) {
                cVar2.f682d = a2.f682d;
            } else {
                this.f676b = a2.f682d;
            }
            a2.f681c = null;
            a2.f682d = null;
            v = a2.f680b;
        }
        this.f674e.remove(k);
        return v;
    }
}
