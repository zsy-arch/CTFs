package c.c;

import c.c.h;
import java.util.Collection;
import java.util.ConcurrentModificationException;
import java.util.Map;
import java.util.Set;

/* loaded from: classes.dex */
public class b<K, V> extends i<K, V> implements Map<K, V> {
    public h<K, V> h;

    public b() {
    }

    public final h<K, V> b() {
        if (this.h == null) {
            this.h = new a(this);
        }
        return this.h;
    }

    @Override // java.util.Map
    public Set<Map.Entry<K, V>> entrySet() {
        h<K, V> b2 = b();
        if (b2.f708a == null) {
            b2.f708a = new h.b();
        }
        return b2.f708a;
    }

    @Override // java.util.Map
    public Set<K> keySet() {
        h<K, V> b2 = b();
        if (b2.f709b == null) {
            b2.f709b = new h.c();
        }
        return b2.f709b;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.util.Map
    public void putAll(Map<? extends K, ? extends V> map) {
        int size = map.size() + this.g;
        int i = this.g;
        int[] iArr = this.f727e;
        if (iArr.length < size) {
            Object[] objArr = this.f;
            a(size);
            if (this.g > 0) {
                System.arraycopy(iArr, 0, this.f727e, 0, i);
                System.arraycopy(objArr, 0, this.f, 0, i << 1);
            }
            i.a(iArr, objArr, i);
        }
        if (this.g == i) {
            for (Map.Entry<? extends K, ? extends V> entry : map.entrySet()) {
                put(entry.getKey(), entry.getValue());
            }
            return;
        }
        throw new ConcurrentModificationException();
    }

    @Override // java.util.Map
    public Collection<V> values() {
        h<K, V> b2 = b();
        if (b2.f710c == null) {
            b2.f710c = new h.e();
        }
        return b2.f710c;
    }

    public b(int i) {
        super(i);
    }
}
