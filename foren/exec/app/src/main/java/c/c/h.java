package c.c;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;

/* loaded from: classes.dex */
public abstract class h<K, V> {

    /* renamed from: a  reason: collision with root package name */
    public h<K, V>.b f708a;

    /* renamed from: b  reason: collision with root package name */
    public h<K, V>.c f709b;

    /* renamed from: c  reason: collision with root package name */
    public h<K, V>.e f710c;

    /* loaded from: classes.dex */
    final class a<T> implements Iterator<T> {

        /* renamed from: a  reason: collision with root package name */
        public final int f711a;

        /* renamed from: b  reason: collision with root package name */
        public int f712b;

        /* renamed from: c  reason: collision with root package name */
        public int f713c;

        /* renamed from: d  reason: collision with root package name */
        public boolean f714d = false;

        public a(int i) {
            this.f711a = i;
            this.f712b = h.this.c();
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return this.f713c < this.f712b;
        }

        @Override // java.util.Iterator
        public T next() {
            if (this.f713c < this.f712b) {
                T t = (T) h.this.a(this.f713c, this.f711a);
                this.f713c++;
                this.f714d = true;
                return t;
            }
            throw new NoSuchElementException();
        }

        @Override // java.util.Iterator
        public void remove() {
            if (this.f714d) {
                this.f713c--;
                this.f712b--;
                this.f714d = false;
                h.this.a(this.f713c);
                return;
            }
            throw new IllegalStateException();
        }
    }

    /* loaded from: classes.dex */
    final class b implements Set<Map.Entry<K, V>> {
        public b() {
        }

        @Override // java.util.Set, java.util.Collection
        public boolean add(Object obj) {
            Map.Entry entry = (Map.Entry) obj;
            throw new UnsupportedOperationException();
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // java.util.Set, java.util.Collection
        public boolean addAll(Collection<? extends Map.Entry<K, V>> collection) {
            int c2 = h.this.c();
            Iterator<? extends Map.Entry<K, V>> it = collection.iterator();
            while (it.hasNext()) {
                Map.Entry entry = (Map.Entry) it.next();
                h.this.a((h) entry.getKey(), entry.getValue());
            }
            return c2 != h.this.c();
        }

        @Override // java.util.Set, java.util.Collection
        public void clear() {
            h.this.a();
        }

        @Override // java.util.Set, java.util.Collection
        public boolean contains(Object obj) {
            if (!(obj instanceof Map.Entry)) {
                return false;
            }
            Map.Entry entry = (Map.Entry) obj;
            int a2 = h.this.a(entry.getKey());
            if (a2 < 0) {
                return false;
            }
            return e.a(h.this.a(a2, 1), entry.getValue());
        }

        /* JADX WARN: Removed duplicated region for block: B:14:0x0035 A[SYNTHETIC] */
        @Override // java.util.Set, java.util.Collection
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public boolean containsAll(java.util.Collection<?> r6) {
            /*
                r5 = this;
                java.util.Iterator r6 = r6.iterator()
            L_0x0004:
                boolean r0 = r6.hasNext()
                r1 = 1
                if (r0 == 0) goto L_0x0036
                java.lang.Object r0 = r6.next()
                boolean r2 = r0 instanceof java.util.Map.Entry
                r3 = 0
                if (r2 != 0) goto L_0x0016
            L_0x0014:
                r0 = 0
                goto L_0x0033
            L_0x0016:
                java.util.Map$Entry r0 = (java.util.Map.Entry) r0
                c.c.h r2 = c.c.h.this
                java.lang.Object r4 = r0.getKey()
                int r2 = r2.a(r4)
                if (r2 >= 0) goto L_0x0025
                goto L_0x0014
            L_0x0025:
                c.c.h r4 = c.c.h.this
                java.lang.Object r1 = r4.a(r2, r1)
                java.lang.Object r0 = r0.getValue()
                boolean r0 = c.c.e.a(r1, r0)
            L_0x0033:
                if (r0 != 0) goto L_0x0004
                return r3
            L_0x0036:
                return r1
            */
            throw new UnsupportedOperationException("Method not decompiled: c.c.h.b.containsAll(java.util.Collection):boolean");
        }

        @Override // java.util.Set, java.util.Collection
        public boolean equals(Object obj) {
            return h.a((Set) this, obj);
        }

        @Override // java.util.Set, java.util.Collection
        public int hashCode() {
            int i = 0;
            for (int c2 = h.this.c() - 1; c2 >= 0; c2--) {
                Object a2 = h.this.a(c2, 0);
                Object a3 = h.this.a(c2, 1);
                i += (a2 == null ? 0 : a2.hashCode()) ^ (a3 == null ? 0 : a3.hashCode());
            }
            return i;
        }

        @Override // java.util.Set, java.util.Collection
        public boolean isEmpty() {
            return h.this.c() == 0;
        }

        @Override // java.util.Set, java.util.Collection, java.lang.Iterable
        public Iterator<Map.Entry<K, V>> iterator() {
            return new d();
        }

        @Override // java.util.Set, java.util.Collection
        public boolean remove(Object obj) {
            throw new UnsupportedOperationException();
        }

        @Override // java.util.Set, java.util.Collection
        public boolean removeAll(Collection<?> collection) {
            throw new UnsupportedOperationException();
        }

        @Override // java.util.Set, java.util.Collection
        public boolean retainAll(Collection<?> collection) {
            throw new UnsupportedOperationException();
        }

        @Override // java.util.Set, java.util.Collection
        public int size() {
            return h.this.c();
        }

        @Override // java.util.Set, java.util.Collection
        public Object[] toArray() {
            throw new UnsupportedOperationException();
        }

        @Override // java.util.Set, java.util.Collection
        public <T> T[] toArray(T[] tArr) {
            throw new UnsupportedOperationException();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public final class c implements Set<K> {
        public c() {
        }

        @Override // java.util.Set, java.util.Collection
        public boolean add(K k) {
            throw new UnsupportedOperationException();
        }

        @Override // java.util.Set, java.util.Collection
        public boolean addAll(Collection<? extends K> collection) {
            throw new UnsupportedOperationException();
        }

        @Override // java.util.Set, java.util.Collection
        public void clear() {
            h.this.a();
        }

        @Override // java.util.Set, java.util.Collection
        public boolean contains(Object obj) {
            return h.this.a(obj) >= 0;
        }

        @Override // java.util.Set, java.util.Collection
        public boolean containsAll(Collection<?> collection) {
            Map<K, V> b2 = h.this.b();
            Iterator<?> it = collection.iterator();
            while (it.hasNext()) {
                if (!b2.containsKey(it.next())) {
                    return false;
                }
            }
            return true;
        }

        @Override // java.util.Set, java.util.Collection
        public boolean equals(Object obj) {
            return h.a((Set) this, obj);
        }

        @Override // java.util.Set, java.util.Collection
        public int hashCode() {
            int i = 0;
            for (int c2 = h.this.c() - 1; c2 >= 0; c2--) {
                Object a2 = h.this.a(c2, 0);
                i += a2 == null ? 0 : a2.hashCode();
            }
            return i;
        }

        @Override // java.util.Set, java.util.Collection
        public boolean isEmpty() {
            return h.this.c() == 0;
        }

        @Override // java.util.Set, java.util.Collection, java.lang.Iterable
        public Iterator<K> iterator() {
            return new a(0);
        }

        @Override // java.util.Set, java.util.Collection
        public boolean remove(Object obj) {
            int a2 = h.this.a(obj);
            if (a2 < 0) {
                return false;
            }
            h.this.a(a2);
            return true;
        }

        @Override // java.util.Set, java.util.Collection
        public boolean removeAll(Collection<?> collection) {
            Map<K, V> b2 = h.this.b();
            int size = b2.size();
            Iterator<?> it = collection.iterator();
            while (it.hasNext()) {
                b2.remove(it.next());
            }
            return size != b2.size();
        }

        @Override // java.util.Set, java.util.Collection
        public boolean retainAll(Collection<?> collection) {
            return h.a((Map) h.this.b(), collection);
        }

        @Override // java.util.Set, java.util.Collection
        public int size() {
            return h.this.c();
        }

        @Override // java.util.Set, java.util.Collection
        public Object[] toArray() {
            return h.this.b(0);
        }

        @Override // java.util.Set, java.util.Collection
        public <T> T[] toArray(T[] tArr) {
            return (T[]) h.this.a(tArr, 0);
        }
    }

    /* loaded from: classes.dex */
    final class d implements Iterator<Map.Entry<K, V>>, Map.Entry<K, V> {

        /* renamed from: a  reason: collision with root package name */
        public int f718a;

        /* renamed from: c  reason: collision with root package name */
        public boolean f720c = false;

        /* renamed from: b  reason: collision with root package name */
        public int f719b = -1;

        public d() {
            this.f718a = h.this.c() - 1;
        }

        @Override // java.util.Map.Entry
        public boolean equals(Object obj) {
            if (!this.f720c) {
                throw new IllegalStateException("This container does not support retaining Map.Entry objects");
            } else if (!(obj instanceof Map.Entry)) {
                return false;
            } else {
                Map.Entry entry = (Map.Entry) obj;
                return e.a(entry.getKey(), h.this.a(this.f719b, 0)) && e.a(entry.getValue(), h.this.a(this.f719b, 1));
            }
        }

        @Override // java.util.Map.Entry
        public K getKey() {
            if (this.f720c) {
                return (K) h.this.a(this.f719b, 0);
            }
            throw new IllegalStateException("This container does not support retaining Map.Entry objects");
        }

        @Override // java.util.Map.Entry
        public V getValue() {
            if (this.f720c) {
                return (V) h.this.a(this.f719b, 1);
            }
            throw new IllegalStateException("This container does not support retaining Map.Entry objects");
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return this.f719b < this.f718a;
        }

        @Override // java.util.Map.Entry
        public int hashCode() {
            if (this.f720c) {
                int i = 0;
                Object a2 = h.this.a(this.f719b, 0);
                Object a3 = h.this.a(this.f719b, 1);
                int hashCode = a2 == null ? 0 : a2.hashCode();
                if (a3 != null) {
                    i = a3.hashCode();
                }
                return hashCode ^ i;
            }
            throw new IllegalStateException("This container does not support retaining Map.Entry objects");
        }

        @Override // java.util.Iterator
        public Object next() {
            if (this.f719b < this.f718a) {
                this.f719b++;
                this.f720c = true;
                return this;
            }
            throw new NoSuchElementException();
        }

        @Override // java.util.Iterator
        public void remove() {
            if (this.f720c) {
                h.this.a(this.f719b);
                this.f719b--;
                this.f718a--;
                this.f720c = false;
                return;
            }
            throw new IllegalStateException();
        }

        @Override // java.util.Map.Entry
        public V setValue(V v) {
            if (this.f720c) {
                return (V) h.this.a(this.f719b, (int) v);
            }
            throw new IllegalStateException("This container does not support retaining Map.Entry objects");
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();
            if (this.f720c) {
                sb.append(h.this.a(this.f719b, 0));
                sb.append("=");
                if (this.f720c) {
                    sb.append(h.this.a(this.f719b, 1));
                    return sb.toString();
                }
                throw new IllegalStateException("This container does not support retaining Map.Entry objects");
            }
            throw new IllegalStateException("This container does not support retaining Map.Entry objects");
        }
    }

    /* loaded from: classes.dex */
    final class e implements Collection<V> {
        public e() {
        }

        @Override // java.util.Collection
        public boolean add(V v) {
            throw new UnsupportedOperationException();
        }

        @Override // java.util.Collection
        public boolean addAll(Collection<? extends V> collection) {
            throw new UnsupportedOperationException();
        }

        @Override // java.util.Collection
        public void clear() {
            h.this.a();
        }

        @Override // java.util.Collection
        public boolean contains(Object obj) {
            return h.this.b(obj) >= 0;
        }

        @Override // java.util.Collection
        public boolean containsAll(Collection<?> collection) {
            boolean z;
            Iterator<?> it = collection.iterator();
            do {
                z = true;
                if (!it.hasNext()) {
                    return true;
                }
                if (h.this.b(it.next()) < 0) {
                    z = false;
                    continue;
                }
            } while (z);
            return false;
        }

        @Override // java.util.Collection
        public boolean isEmpty() {
            return h.this.c() == 0;
        }

        @Override // java.util.Collection, java.lang.Iterable
        public Iterator<V> iterator() {
            return new a(1);
        }

        @Override // java.util.Collection
        public boolean remove(Object obj) {
            int b2 = h.this.b(obj);
            if (b2 < 0) {
                return false;
            }
            h.this.a(b2);
            return true;
        }

        @Override // java.util.Collection
        public boolean removeAll(Collection<?> collection) {
            int c2 = h.this.c();
            int i = 0;
            boolean z = false;
            while (i < c2) {
                if (collection.contains(h.this.a(i, 1))) {
                    h.this.a(i);
                    i--;
                    c2--;
                    z = true;
                }
                i++;
            }
            return z;
        }

        @Override // java.util.Collection
        public boolean retainAll(Collection<?> collection) {
            int c2 = h.this.c();
            int i = 0;
            boolean z = false;
            while (i < c2) {
                if (!collection.contains(h.this.a(i, 1))) {
                    h.this.a(i);
                    i--;
                    c2--;
                    z = true;
                }
                i++;
            }
            return z;
        }

        @Override // java.util.Collection
        public int size() {
            return h.this.c();
        }

        @Override // java.util.Collection
        public Object[] toArray() {
            return h.this.b(1);
        }

        @Override // java.util.Collection
        public <T> T[] toArray(T[] tArr) {
            return (T[]) h.this.a(tArr, 1);
        }
    }

    public static <K, V> boolean a(Map<K, V> map, Collection<?> collection) {
        int size = map.size();
        Iterator<K> it = map.keySet().iterator();
        while (it.hasNext()) {
            if (!collection.contains(it.next())) {
                it.remove();
            }
        }
        return size != map.size();
    }

    public abstract int a(Object obj);

    public abstract Object a(int i, int i2);

    public abstract V a(int i, V v);

    public abstract void a();

    public abstract void a(int i);

    public abstract void a(K k, V v);

    public abstract int b(Object obj);

    public abstract Map<K, V> b();

    public Object[] b(int i) {
        int c2 = c();
        Object[] objArr = new Object[c2];
        for (int i2 = 0; i2 < c2; i2++) {
            objArr[i2] = a(i2, i);
        }
        return objArr;
    }

    public abstract int c();

    /* JADX WARN: Multi-variable type inference failed */
    public <T> T[] a(T[] tArr, int i) {
        int c2 = c();
        if (tArr.length < c2) {
            tArr = (T[]) ((Object[]) Array.newInstance(tArr.getClass().getComponentType(), c2));
        }
        for (int i2 = 0; i2 < c2; i2++) {
            tArr[i2] = a(i2, i);
        }
        if (tArr.length > c2) {
            tArr[c2] = null;
        }
        return tArr;
    }

    public static <T> boolean a(Set<T> set, Object obj) {
        if (set == obj) {
            return true;
        }
        if (obj instanceof Set) {
            Set set2 = (Set) obj;
            try {
                if (set.size() == set2.size()) {
                    if (set.containsAll(set2)) {
                        return true;
                    }
                }
                return false;
            } catch (ClassCastException | NullPointerException unused) {
            }
        }
        return false;
    }
}
