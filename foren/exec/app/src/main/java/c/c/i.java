package c.c;

import java.util.ConcurrentModificationException;
import java.util.Map;

/* loaded from: classes.dex */
public class i<K, V> {

    /* renamed from: a  reason: collision with root package name */
    public static Object[] f723a;

    /* renamed from: b  reason: collision with root package name */
    public static int f724b;

    /* renamed from: c  reason: collision with root package name */
    public static Object[] f725c;

    /* renamed from: d  reason: collision with root package name */
    public static int f726d;

    /* renamed from: e  reason: collision with root package name */
    public int[] f727e;
    public Object[] f;
    public int g;

    public i() {
        this.f727e = e.f695a;
        this.f = e.f697c;
        this.g = 0;
    }

    public int a(Object obj, int i) {
        int i2 = this.g;
        if (i2 == 0) {
            return -1;
        }
        try {
            int a2 = e.a(this.f727e, i2, i);
            if (a2 < 0 || obj.equals(this.f[a2 << 1])) {
                return a2;
            }
            int i3 = a2 + 1;
            while (i3 < i2 && this.f727e[i3] == i) {
                if (obj.equals(this.f[i3 << 1])) {
                    return i3;
                }
                i3++;
            }
            for (int i4 = a2 - 1; i4 >= 0 && this.f727e[i4] == i; i4--) {
                if (obj.equals(this.f[i4 << 1])) {
                    return i4;
                }
            }
            return ~i3;
        } catch (ArrayIndexOutOfBoundsException unused) {
            throw new ConcurrentModificationException();
        }
    }

    public int b(Object obj) {
        int i = this.g * 2;
        Object[] objArr = this.f;
        if (obj == null) {
            for (int i2 = 1; i2 < i; i2 += 2) {
                if (objArr[i2] == null) {
                    return i2 >> 1;
                }
            }
            return -1;
        }
        for (int i3 = 1; i3 < i; i3 += 2) {
            if (obj.equals(objArr[i3])) {
                return i3 >> 1;
            }
        }
        return -1;
    }

    public V c(int i) {
        int i2;
        Object[] objArr = this.f;
        int i3 = i << 1;
        V v = (V) objArr[i3 + 1];
        int i4 = this.g;
        if (i4 <= 1) {
            a(this.f727e, objArr, i4);
            this.f727e = e.f695a;
            this.f = e.f697c;
            i2 = 0;
        } else {
            i2 = i4 - 1;
            int[] iArr = this.f727e;
            int i5 = 8;
            if (iArr.length <= 8 || i4 >= iArr.length / 3) {
                if (i < i2) {
                    int[] iArr2 = this.f727e;
                    int i6 = i + 1;
                    int i7 = i2 - i;
                    System.arraycopy(iArr2, i6, iArr2, i, i7);
                    Object[] objArr2 = this.f;
                    System.arraycopy(objArr2, i6 << 1, objArr2, i3, i7 << 1);
                }
                Object[] objArr3 = this.f;
                int i8 = i2 << 1;
                objArr3[i8] = null;
                objArr3[i8 + 1] = null;
            } else {
                if (i4 > 8) {
                    i5 = i4 + (i4 >> 1);
                }
                int[] iArr3 = this.f727e;
                Object[] objArr4 = this.f;
                a(i5);
                if (i4 == this.g) {
                    if (i > 0) {
                        System.arraycopy(iArr3, 0, this.f727e, 0, i);
                        System.arraycopy(objArr4, 0, this.f, 0, i3);
                    }
                    if (i < i2) {
                        int i9 = i + 1;
                        int i10 = i2 - i;
                        System.arraycopy(iArr3, i9, this.f727e, i, i10);
                        System.arraycopy(objArr4, i9 << 1, this.f, i3, i10 << 1);
                    }
                } else {
                    throw new ConcurrentModificationException();
                }
            }
        }
        if (i4 == this.g) {
            this.g = i2;
            return v;
        }
        throw new ConcurrentModificationException();
    }

    public void clear() {
        int i = this.g;
        if (i > 0) {
            int[] iArr = this.f727e;
            Object[] objArr = this.f;
            this.f727e = e.f695a;
            this.f = e.f697c;
            this.g = 0;
            a(iArr, objArr, i);
        }
        if (this.g > 0) {
            throw new ConcurrentModificationException();
        }
    }

    public boolean containsKey(Object obj) {
        return a(obj) >= 0;
    }

    public boolean containsValue(Object obj) {
        return b(obj) >= 0;
    }

    public V d(int i) {
        return (V) this.f[(i << 1) + 1];
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof i) {
            i iVar = (i) obj;
            if (this.g != iVar.g) {
                return false;
            }
            for (int i = 0; i < this.g; i++) {
                try {
                    K b2 = b(i);
                    V d2 = d(i);
                    Object obj2 = iVar.get(b2);
                    if (d2 == null) {
                        if (obj2 != null || !iVar.containsKey(b2)) {
                            return false;
                        }
                    } else if (!d2.equals(obj2)) {
                        return false;
                    }
                } catch (ClassCastException | NullPointerException unused) {
                    return false;
                }
            }
            return true;
        }
        if (obj instanceof Map) {
            Map map = (Map) obj;
            if (this.g != map.size()) {
                return false;
            }
            for (int i2 = 0; i2 < this.g; i2++) {
                try {
                    K b3 = b(i2);
                    V d3 = d(i2);
                    Object obj3 = map.get(b3);
                    if (d3 == null) {
                        if (obj3 != null || !map.containsKey(b3)) {
                            return false;
                        }
                    } else if (!d3.equals(obj3)) {
                        return false;
                    }
                } catch (ClassCastException | NullPointerException unused2) {
                }
            }
            return true;
        }
        return false;
    }

    public V get(Object obj) {
        int a2 = a(obj);
        if (a2 >= 0) {
            return (V) this.f[(a2 << 1) + 1];
        }
        return null;
    }

    public int hashCode() {
        int[] iArr = this.f727e;
        Object[] objArr = this.f;
        int i = this.g;
        int i2 = 0;
        int i3 = 0;
        int i4 = 1;
        while (i2 < i) {
            Object obj = objArr[i4];
            i3 += (obj == null ? 0 : obj.hashCode()) ^ iArr[i2];
            i2++;
            i4 += 2;
        }
        return i3;
    }

    public boolean isEmpty() {
        return this.g <= 0;
    }

    public V put(K k, V v) {
        int i;
        int i2;
        int i3 = this.g;
        if (k == null) {
            i2 = a();
            i = 0;
        } else {
            int hashCode = k.hashCode();
            i = hashCode;
            i2 = a(k, hashCode);
        }
        if (i2 >= 0) {
            int i4 = (i2 << 1) + 1;
            Object[] objArr = this.f;
            V v2 = (V) objArr[i4];
            objArr[i4] = v;
            return v2;
        }
        int i5 = ~i2;
        if (i3 >= this.f727e.length) {
            int i6 = 4;
            if (i3 >= 8) {
                i6 = (i3 >> 1) + i3;
            } else if (i3 >= 4) {
                i6 = 8;
            }
            int[] iArr = this.f727e;
            Object[] objArr2 = this.f;
            a(i6);
            if (i3 == this.g) {
                int[] iArr2 = this.f727e;
                if (iArr2.length > 0) {
                    System.arraycopy(iArr, 0, iArr2, 0, iArr.length);
                    System.arraycopy(objArr2, 0, this.f, 0, objArr2.length);
                }
                a(iArr, objArr2, i3);
            } else {
                throw new ConcurrentModificationException();
            }
        }
        if (i5 < i3) {
            int[] iArr3 = this.f727e;
            int i7 = i5 + 1;
            System.arraycopy(iArr3, i5, iArr3, i7, i3 - i5);
            Object[] objArr3 = this.f;
            System.arraycopy(objArr3, i5 << 1, objArr3, i7 << 1, (this.g - i5) << 1);
        }
        int i8 = this.g;
        if (i3 == i8) {
            int[] iArr4 = this.f727e;
            if (i5 < iArr4.length) {
                iArr4[i5] = i;
                Object[] objArr4 = this.f;
                int i9 = i5 << 1;
                objArr4[i9] = k;
                objArr4[i9 + 1] = v;
                this.g = i8 + 1;
                return null;
            }
        }
        throw new ConcurrentModificationException();
    }

    public V remove(Object obj) {
        int a2 = a(obj);
        if (a2 >= 0) {
            return c(a2);
        }
        return null;
    }

    public int size() {
        return this.g;
    }

    public String toString() {
        if (isEmpty()) {
            return "{}";
        }
        StringBuilder sb = new StringBuilder(this.g * 28);
        sb.append('{');
        for (int i = 0; i < this.g; i++) {
            if (i > 0) {
                sb.append(", ");
            }
            K b2 = b(i);
            if (b2 != this) {
                sb.append(b2);
            } else {
                sb.append("(this Map)");
            }
            sb.append('=');
            V d2 = d(i);
            if (d2 != this) {
                sb.append(d2);
            } else {
                sb.append("(this Map)");
            }
        }
        sb.append('}');
        return sb.toString();
    }

    public i(int i) {
        if (i == 0) {
            this.f727e = e.f695a;
            this.f = e.f697c;
        } else {
            a(i);
        }
        this.g = 0;
    }

    public K b(int i) {
        return (K) this.f[i << 1];
    }

    public int a() {
        int i = this.g;
        if (i == 0) {
            return -1;
        }
        try {
            int a2 = e.a(this.f727e, i, 0);
            if (a2 < 0 || this.f[a2 << 1] == null) {
                return a2;
            }
            int i2 = a2 + 1;
            while (i2 < i && this.f727e[i2] == 0) {
                if (this.f[i2 << 1] == null) {
                    return i2;
                }
                i2++;
            }
            for (int i3 = a2 - 1; i3 >= 0 && this.f727e[i3] == 0; i3--) {
                if (this.f[i3 << 1] == null) {
                    return i3;
                }
            }
            return ~i2;
        } catch (ArrayIndexOutOfBoundsException unused) {
            throw new ConcurrentModificationException();
        }
    }

    public final void a(int i) {
        if (i == 8) {
            synchronized (b.class) {
                if (f725c != null) {
                    Object[] objArr = f725c;
                    this.f = objArr;
                    f725c = (Object[]) objArr[0];
                    this.f727e = (int[]) objArr[1];
                    objArr[1] = null;
                    objArr[0] = null;
                    f726d--;
                    return;
                }
            }
        } else if (i == 4) {
            synchronized (b.class) {
                if (f723a != null) {
                    Object[] objArr2 = f723a;
                    this.f = objArr2;
                    f723a = (Object[]) objArr2[0];
                    this.f727e = (int[]) objArr2[1];
                    objArr2[1] = null;
                    objArr2[0] = null;
                    f724b--;
                    return;
                }
            }
        }
        this.f727e = new int[i];
        this.f = new Object[i << 1];
    }

    public static void a(int[] iArr, Object[] objArr, int i) {
        if (iArr.length == 8) {
            synchronized (b.class) {
                if (f726d < 10) {
                    objArr[0] = f725c;
                    objArr[1] = iArr;
                    for (int i2 = (i << 1) - 1; i2 >= 2; i2--) {
                        objArr[i2] = null;
                    }
                    f725c = objArr;
                    f726d++;
                }
            }
        } else if (iArr.length == 4) {
            synchronized (b.class) {
                if (f724b < 10) {
                    objArr[0] = f723a;
                    objArr[1] = iArr;
                    for (int i3 = (i << 1) - 1; i3 >= 2; i3--) {
                        objArr[i3] = null;
                    }
                    f723a = objArr;
                    f724b++;
                }
            }
        }
    }

    public int a(Object obj) {
        return obj == null ? a() : a(obj, obj.hashCode());
    }
}
