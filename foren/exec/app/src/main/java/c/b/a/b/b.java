package c.b.a.b;

import java.util.Iterator;
import java.util.Map;
import java.util.WeakHashMap;

/* loaded from: classes.dex */
public class b<K, V> implements Iterable<Map.Entry<K, V>> {

    /* renamed from: a */
    public c<K, V> f675a;

    /* renamed from: b */
    public c<K, V> f676b;

    /* renamed from: c */
    public WeakHashMap<f<K, V>, Boolean> f677c = new WeakHashMap<>();

    /* renamed from: d */
    public int f678d = 0;

    /* loaded from: classes.dex */
    public static class a<K, V> extends e<K, V> {
        public a(c<K, V> cVar, c<K, V> cVar2) {
            super(cVar, cVar2);
        }

        @Override // c.b.a.b.b.e
        public c<K, V> b(c<K, V> cVar) {
            return cVar.f682d;
        }

        @Override // c.b.a.b.b.e
        public c<K, V> c(c<K, V> cVar) {
            return cVar.f681c;
        }
    }

    /* renamed from: c.b.a.b.b$b */
    /* loaded from: classes.dex */
    public static class C0007b<K, V> extends e<K, V> {
        public C0007b(c<K, V> cVar, c<K, V> cVar2) {
            super(cVar, cVar2);
        }

        @Override // c.b.a.b.b.e
        public c<K, V> b(c<K, V> cVar) {
            return cVar.f681c;
        }

        @Override // c.b.a.b.b.e
        public c<K, V> c(c<K, V> cVar) {
            return cVar.f682d;
        }
    }

    /* loaded from: classes.dex */
    public static class c<K, V> implements Map.Entry<K, V> {

        /* renamed from: a */
        public final K f679a;

        /* renamed from: b */
        public final V f680b;

        /* renamed from: c */
        public c<K, V> f681c;

        /* renamed from: d */
        public c<K, V> f682d;

        @Override // java.util.Map.Entry
        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof c)) {
                return false;
            }
            c cVar = (c) obj;
            return this.f679a.equals(cVar.f679a) && this.f680b.equals(cVar.f680b);
        }

        @Override // java.util.Map.Entry
        public K getKey() {
            return this.f679a;
        }

        @Override // java.util.Map.Entry
        public V getValue() {
            return this.f680b;
        }

        @Override // java.util.Map.Entry
        public int hashCode() {
            return this.f679a.hashCode() ^ this.f680b.hashCode();
        }

        @Override // java.util.Map.Entry
        public V setValue(V v) {
            throw new UnsupportedOperationException("An entry modification is not supported");
        }

        public String toString() {
            return this.f679a + "=" + this.f680b;
        }
    }

    /* loaded from: classes.dex */
    public class d implements Iterator<Map.Entry<K, V>>, f<K, V> {

        /* renamed from: a */
        public c<K, V> f683a;

        /* renamed from: b */
        public boolean f684b = true;

        public d() {
            b.this = r1;
        }

        @Override // c.b.a.b.b.f
        public void a(c<K, V> cVar) {
            c<K, V> cVar2 = this.f683a;
            if (cVar == cVar2) {
                this.f683a = cVar2.f682d;
                this.f684b = this.f683a == null;
            }
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            if (this.f684b) {
                return b.this.f675a != null;
            }
            c<K, V> cVar = this.f683a;
            return (cVar == null || cVar.f681c == null) ? false : true;
        }

        @Override // java.util.Iterator
        public Object next() {
            if (this.f684b) {
                this.f684b = false;
                this.f683a = b.this.f675a;
            } else {
                c<K, V> cVar = this.f683a;
                this.f683a = cVar != null ? cVar.f681c : null;
            }
            return this.f683a;
        }
    }

    /* loaded from: classes.dex */
    public interface f<K, V> {
        void a(c<K, V> cVar);
    }

    public c<K, V> a(K k) {
        c<K, V> cVar = this.f675a;
        while (cVar != null && !cVar.f679a.equals(k)) {
            cVar = cVar.f681c;
        }
        return cVar;
    }

    public Iterator<Map.Entry<K, V>> descendingIterator() {
        C0007b bVar = new C0007b(this.f676b, this.f675a);
        this.f677c.put(bVar, false);
        return bVar;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof b)) {
            return false;
        }
        b bVar = (b) obj;
        if (this.f678d != bVar.f678d) {
            return false;
        }
        Iterator<Map.Entry<K, V>> it = iterator();
        Iterator<Map.Entry<K, V>> it2 = bVar.iterator();
        while (it.hasNext() && it2.hasNext()) {
            Map.Entry<K, V> next = it.next();
            Map.Entry<K, V> next2 = it2.next();
            if ((next == null && next2 != null) || (next != null && !next.equals(next2))) {
                return false;
            }
        }
        return !it.hasNext() && !it2.hasNext();
    }

    public int hashCode() {
        Iterator<Map.Entry<K, V>> it = iterator();
        int i = 0;
        while (it.hasNext()) {
            i += it.next().hashCode();
        }
        return i;
    }

    @Override // java.lang.Iterable
    public Iterator<Map.Entry<K, V>> iterator() {
        a aVar = new a(this.f675a, this.f676b);
        this.f677c.put(aVar, false);
        return aVar;
    }

    public V remove(K k) {
        c<K, V> a2 = a(k);
        if (a2 == null) {
            return null;
        }
        this.f678d--;
        if (!this.f677c.isEmpty()) {
            for (f<K, V> fVar : this.f677c.keySet()) {
                fVar.a(a2);
            }
        }
        c<K, V> cVar = a2.f682d;
        if (cVar != null) {
            cVar.f681c = a2.f681c;
        } else {
            this.f675a = a2.f681c;
        }
        c<K, V> cVar2 = a2.f681c;
        if (cVar2 != null) {
            cVar2.f682d = a2.f682d;
        } else {
            this.f676b = a2.f682d;
        }
        a2.f681c = null;
        a2.f682d = null;
        return a2.f680b;
    }

    public String toString() {
        StringBuilder a2 = e.a.a.a.a.a("[");
        Iterator<Map.Entry<K, V>> it = iterator();
        while (it.hasNext()) {
            a2.append(it.next().toString());
            if (it.hasNext()) {
                a2.append(", ");
            }
        }
        a2.append("]");
        return a2.toString();
    }

    public b<K, V>.d a() {
        b<K, V>.d dVar = new d();
        this.f677c.put(dVar, false);
        return dVar;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static abstract class e<K, V> implements Iterator<Map.Entry<K, V>>, f<K, V> {

        /* renamed from: a */
        public c<K, V> f686a;

        /* renamed from: b */
        public c<K, V> f687b;

        public e(c<K, V> cVar, c<K, V> cVar2) {
            this.f686a = cVar2;
            this.f687b = cVar;
        }

        @Override // c.b.a.b.b.f
        public void a(c<K, V> cVar) {
            c<K, V> cVar2 = null;
            if (this.f686a == cVar && cVar == this.f687b) {
                this.f687b = null;
                this.f686a = null;
            }
            c<K, V> cVar3 = this.f686a;
            if (cVar3 == cVar) {
                this.f686a = b(cVar3);
            }
            c<K, V> cVar4 = this.f687b;
            if (cVar4 == cVar) {
                c<K, V> cVar5 = this.f686a;
                if (!(cVar4 == cVar5 || cVar5 == null)) {
                    cVar2 = c(cVar4);
                }
                this.f687b = cVar2;
            }
        }

        public abstract c<K, V> b(c<K, V> cVar);

        public abstract c<K, V> c(c<K, V> cVar);

        @Override // java.util.Iterator
        public boolean hasNext() {
            return this.f687b != null;
        }

        @Override // java.util.Iterator
        public Object next() {
            c<K, V> cVar = this.f687b;
            this.f687b = a();
            return cVar;
        }

        public final c<K, V> a() {
            c<K, V> cVar = this.f687b;
            c<K, V> cVar2 = this.f686a;
            if (cVar == cVar2 || cVar2 == null) {
                return null;
            }
            return c(cVar);
        }
    }
}
