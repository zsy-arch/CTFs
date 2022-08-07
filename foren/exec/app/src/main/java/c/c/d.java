package c.c;

import c.c.h;
import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

/* loaded from: classes.dex */
public final class d<E> implements Collection<E>, Set<E> {

    /* renamed from: a */
    public static final int[] f690a = new int[0];

    /* renamed from: b */
    public static final Object[] f691b = new Object[0];

    /* renamed from: c */
    public static Object[] f692c;

    /* renamed from: d */
    public static int f693d;

    /* renamed from: e */
    public static Object[] f694e;
    public static int f;
    public int[] g = f690a;
    public Object[] h = f691b;
    public int i = 0;
    public h<E, E> j;

    public final int a(Object obj, int i) {
        int i2 = this.i;
        if (i2 == 0) {
            return -1;
        }
        int a2 = e.a(this.g, i2, i);
        if (a2 < 0 || obj.equals(this.h[a2])) {
            return a2;
        }
        int i3 = a2 + 1;
        while (i3 < i2 && this.g[i3] == i) {
            if (obj.equals(this.h[i3])) {
                return i3;
            }
            i3++;
        }
        for (int i4 = a2 - 1; i4 >= 0 && this.g[i4] == i; i4--) {
            if (obj.equals(this.h[i4])) {
                return i4;
            }
        }
        return ~i3;
    }

    @Override // java.util.Collection, java.util.Set
    public boolean add(E e2) {
        int i;
        int i2;
        if (e2 == null) {
            i2 = a();
            i = 0;
        } else {
            int hashCode = e2.hashCode();
            i = hashCode;
            i2 = a(e2, hashCode);
        }
        if (i2 >= 0) {
            return false;
        }
        int i3 = ~i2;
        int i4 = this.i;
        if (i4 >= this.g.length) {
            int i5 = 4;
            if (i4 >= 8) {
                i5 = (i4 >> 1) + i4;
            } else if (i4 >= 4) {
                i5 = 8;
            }
            int[] iArr = this.g;
            Object[] objArr = this.h;
            a(i5);
            int[] iArr2 = this.g;
            if (iArr2.length > 0) {
                System.arraycopy(iArr, 0, iArr2, 0, iArr.length);
                System.arraycopy(objArr, 0, this.h, 0, objArr.length);
            }
            a(iArr, objArr, this.i);
        }
        int i6 = this.i;
        if (i3 < i6) {
            int[] iArr3 = this.g;
            int i7 = i3 + 1;
            System.arraycopy(iArr3, i3, iArr3, i7, i6 - i3);
            Object[] objArr2 = this.h;
            System.arraycopy(objArr2, i3, objArr2, i7, this.i - i3);
        }
        this.g[i3] = i;
        this.h[i3] = e2;
        this.i++;
        return true;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.util.Collection, java.util.Set
    public boolean addAll(Collection<? extends E> collection) {
        int size = collection.size() + this.i;
        int[] iArr = this.g;
        boolean z = false;
        if (iArr.length < size) {
            Object[] objArr = this.h;
            a(size);
            int i = this.i;
            if (i > 0) {
                System.arraycopy(iArr, 0, this.g, 0, i);
                System.arraycopy(objArr, 0, this.h, 0, this.i);
            }
            a(iArr, objArr, this.i);
        }
        Iterator<? extends E> it = collection.iterator();
        while (it.hasNext()) {
            z |= add(it.next());
        }
        return z;
    }

    public E b(int i) {
        Object[] objArr = this.h;
        E e2 = (E) objArr[i];
        int i2 = this.i;
        if (i2 <= 1) {
            a(this.g, objArr, i2);
            this.g = f690a;
            this.h = f691b;
            this.i = 0;
        } else {
            int[] iArr = this.g;
            int i3 = 8;
            if (iArr.length <= 8 || i2 >= iArr.length / 3) {
                this.i--;
                int i4 = this.i;
                if (i < i4) {
                    int[] iArr2 = this.g;
                    int i5 = i + 1;
                    System.arraycopy(iArr2, i5, iArr2, i, i4 - i);
                    Object[] objArr2 = this.h;
                    System.arraycopy(objArr2, i5, objArr2, i, this.i - i);
                }
                this.h[this.i] = null;
            } else {
                if (i2 > 8) {
                    i3 = i2 + (i2 >> 1);
                }
                int[] iArr3 = this.g;
                Object[] objArr3 = this.h;
                a(i3);
                this.i--;
                if (i > 0) {
                    System.arraycopy(iArr3, 0, this.g, 0, i);
                    System.arraycopy(objArr3, 0, this.h, 0, i);
                }
                int i6 = this.i;
                if (i < i6) {
                    int i7 = i + 1;
                    System.arraycopy(iArr3, i7, this.g, i, i6 - i);
                    System.arraycopy(objArr3, i7, this.h, i, this.i - i);
                }
            }
        }
        return e2;
    }

    @Override // java.util.Collection, java.util.Set
    public void clear() {
        int i = this.i;
        if (i != 0) {
            a(this.g, this.h, i);
            this.g = f690a;
            this.h = f691b;
            this.i = 0;
        }
    }

    @Override // java.util.Collection, java.util.Set
    public boolean contains(Object obj) {
        return indexOf(obj) >= 0;
    }

    @Override // java.util.Collection, java.util.Set
    public boolean containsAll(Collection<?> collection) {
        boolean z;
        Iterator<?> it = collection.iterator();
        do {
            z = true;
            if (!it.hasNext()) {
                return true;
            }
            if (indexOf(it.next()) < 0) {
                z = false;
                continue;
            }
        } while (z);
        return false;
    }

    @Override // java.util.Collection, java.util.Set
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof Set) {
            Set set = (Set) obj;
            if (this.i != set.size()) {
                return false;
            }
            for (int i = 0; i < this.i; i++) {
                try {
                    if (!set.contains(this.h[i])) {
                        return false;
                    }
                } catch (ClassCastException | NullPointerException unused) {
                }
            }
            return true;
        }
        return false;
    }

    @Override // java.util.Collection, java.util.Set
    public int hashCode() {
        int[] iArr = this.g;
        int i = this.i;
        int i2 = 0;
        for (int i3 = 0; i3 < i; i3++) {
            i2 += iArr[i3];
        }
        return i2;
    }

    public int indexOf(Object obj) {
        return obj == null ? a() : a(obj, obj.hashCode());
    }

    @Override // java.util.Collection, java.util.Set
    public boolean isEmpty() {
        return this.i <= 0;
    }

    @Override // java.util.Collection, java.lang.Iterable, java.util.Set
    public Iterator<E> iterator() {
        if (this.j == null) {
            this.j = new c(this);
        }
        h<E, E> hVar = this.j;
        if (hVar.f709b == null) {
            hVar.f709b = new h.c();
        }
        return hVar.f709b.iterator();
    }

    @Override // java.util.Collection, java.util.Set
    public boolean remove(Object obj) {
        int indexOf = indexOf(obj);
        if (indexOf < 0) {
            return false;
        }
        b(indexOf);
        return true;
    }

    @Override // java.util.Collection, java.util.Set
    public boolean removeAll(Collection<?> collection) {
        boolean z;
        Iterator<?> it = collection.iterator();
        boolean z2 = false;
        while (it.hasNext()) {
            int indexOf = indexOf(it.next());
            if (indexOf >= 0) {
                b(indexOf);
                z = true;
            } else {
                z = false;
            }
            z2 |= z;
        }
        return z2;
    }

    @Override // java.util.Collection, java.util.Set
    public boolean retainAll(Collection<?> collection) {
        boolean z = false;
        for (int i = this.i - 1; i >= 0; i--) {
            if (!collection.contains(this.h[i])) {
                b(i);
                z = true;
            }
        }
        return z;
    }

    @Override // java.util.Collection, java.util.Set
    public int size() {
        return this.i;
    }

    @Override // java.util.Collection, java.util.Set
    public Object[] toArray() {
        int i = this.i;
        Object[] objArr = new Object[i];
        System.arraycopy(this.h, 0, objArr, 0, i);
        return objArr;
    }

    public String toString() {
        if (this.i <= 0) {
            return "{}";
        }
        StringBuilder sb = new StringBuilder(this.i * 14);
        sb.append('{');
        for (int i = 0; i < this.i; i++) {
            if (i > 0) {
                sb.append(", ");
            }
            Object obj = this.h[i];
            if (obj != this) {
                sb.append(obj);
            } else {
                sb.append("(this Set)");
            }
        }
        sb.append('}');
        return sb.toString();
    }

    @Override // java.util.Collection, java.util.Set
    public <T> T[] toArray(T[] tArr) {
        if (tArr.length < this.i) {
            tArr = (T[]) ((Object[]) Array.newInstance(tArr.getClass().getComponentType(), this.i));
        }
        System.arraycopy(this.h, 0, tArr, 0, this.i);
        int length = tArr.length;
        int i = this.i;
        if (length > i) {
            tArr[i] = null;
        }
        return tArr;
    }

    public final int a() {
        int i = this.i;
        if (i == 0) {
            return -1;
        }
        int a2 = e.a(this.g, i, 0);
        if (a2 < 0 || this.h[a2] == null) {
            return a2;
        }
        int i2 = a2 + 1;
        while (i2 < i && this.g[i2] == 0) {
            if (this.h[i2] == null) {
                return i2;
            }
            i2++;
        }
        for (int i3 = a2 - 1; i3 >= 0 && this.g[i3] == 0; i3--) {
            if (this.h[i3] == null) {
                return i3;
            }
        }
        return ~i2;
    }

    public final void a(int i) {
        if (i == 8) {
            synchronized (d.class) {
                if (f694e != null) {
                    Object[] objArr = f694e;
                    this.h = objArr;
                    f694e = (Object[]) objArr[0];
                    this.g = (int[]) objArr[1];
                    objArr[1] = null;
                    objArr[0] = null;
                    f--;
                    return;
                }
            }
        } else if (i == 4) {
            synchronized (d.class) {
                if (f692c != null) {
                    Object[] objArr2 = f692c;
                    this.h = objArr2;
                    f692c = (Object[]) objArr2[0];
                    this.g = (int[]) objArr2[1];
                    objArr2[1] = null;
                    objArr2[0] = null;
                    f693d--;
                    return;
                }
            }
        }
        this.g = new int[i];
        this.h = new Object[i];
    }

    public static void a(int[] iArr, Object[] objArr, int i) {
        if (iArr.length == 8) {
            synchronized (d.class) {
                if (f < 10) {
                    objArr[0] = f694e;
                    objArr[1] = iArr;
                    for (int i2 = i - 1; i2 >= 2; i2--) {
                        objArr[i2] = null;
                    }
                    f694e = objArr;
                    f++;
                }
            }
        } else if (iArr.length == 4) {
            synchronized (d.class) {
                if (f693d < 10) {
                    objArr[0] = f692c;
                    objArr[1] = iArr;
                    for (int i3 = i - 1; i3 >= 2; i3--) {
                        objArr[i3] = null;
                    }
                    f692c = objArr;
                    f693d++;
                }
            }
        }
    }
}
