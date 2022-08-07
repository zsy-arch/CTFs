package c.c;

/* loaded from: classes.dex */
public class f<E> implements Cloneable {

    /* renamed from: a  reason: collision with root package name */
    public static final Object f698a = new Object();

    /* renamed from: c  reason: collision with root package name */
    public long[] f700c;

    /* renamed from: d  reason: collision with root package name */
    public Object[] f701d;

    /* renamed from: b  reason: collision with root package name */
    public boolean f699b = false;

    /* renamed from: e  reason: collision with root package name */
    public int f702e = 0;

    public f() {
        int c2 = e.c(10);
        this.f700c = new long[c2];
        this.f701d = new Object[c2];
    }

    public final void a() {
        int i = this.f702e;
        long[] jArr = this.f700c;
        Object[] objArr = this.f701d;
        int i2 = 0;
        for (int i3 = 0; i3 < i; i3++) {
            Object obj = objArr[i3];
            if (obj != f698a) {
                if (i3 != i2) {
                    jArr[i2] = jArr[i3];
                    objArr[i2] = obj;
                    objArr[i3] = null;
                }
                i2++;
            }
        }
        this.f699b = false;
        this.f702e = i2;
    }

    public E b(long j, E e2) {
        int a2 = e.a(this.f700c, this.f702e, j);
        if (a2 >= 0) {
            Object[] objArr = this.f701d;
            if (objArr[a2] != f698a) {
                return (E) objArr[a2];
            }
        }
        return e2;
    }

    public void c(long j, E e2) {
        int a2 = e.a(this.f700c, this.f702e, j);
        if (a2 >= 0) {
            this.f701d[a2] = e2;
            return;
        }
        int i = ~a2;
        if (i < this.f702e) {
            Object[] objArr = this.f701d;
            if (objArr[i] == f698a) {
                this.f700c[i] = j;
                objArr[i] = e2;
                return;
            }
        }
        if (this.f699b && this.f702e >= this.f700c.length) {
            a();
            i = ~e.a(this.f700c, this.f702e, j);
        }
        int i2 = this.f702e;
        if (i2 >= this.f700c.length) {
            int c2 = e.c(i2 + 1);
            long[] jArr = new long[c2];
            Object[] objArr2 = new Object[c2];
            long[] jArr2 = this.f700c;
            System.arraycopy(jArr2, 0, jArr, 0, jArr2.length);
            Object[] objArr3 = this.f701d;
            System.arraycopy(objArr3, 0, objArr2, 0, objArr3.length);
            this.f700c = jArr;
            this.f701d = objArr2;
        }
        int i3 = this.f702e;
        if (i3 - i != 0) {
            long[] jArr3 = this.f700c;
            int i4 = i + 1;
            System.arraycopy(jArr3, i, jArr3, i4, i3 - i);
            Object[] objArr4 = this.f701d;
            System.arraycopy(objArr4, i, objArr4, i4, this.f702e - i);
        }
        this.f700c[i] = j;
        this.f701d[i] = e2;
        this.f702e++;
    }

    public String toString() {
        if (this.f699b) {
            a();
        }
        int i = this.f702e;
        if (i <= 0) {
            return "{}";
        }
        StringBuilder sb = new StringBuilder(i * 28);
        sb.append('{');
        for (int i2 = 0; i2 < this.f702e; i2++) {
            if (i2 > 0) {
                sb.append(", ");
            }
            if (this.f699b) {
                a();
            }
            sb.append(this.f700c[i2]);
            sb.append('=');
            if (this.f699b) {
                a();
            }
            Object obj = this.f701d[i2];
            if (obj != this) {
                sb.append(obj);
            } else {
                sb.append("(this Map)");
            }
        }
        sb.append('}');
        return sb.toString();
    }

    public f<E> clone() {
        try {
            f<E> fVar = (f) super.clone();
            fVar.f700c = (long[]) this.f700c.clone();
            fVar.f701d = (Object[]) this.f701d.clone();
            return fVar;
        } catch (CloneNotSupportedException e2) {
            throw new AssertionError(e2);
        }
    }

    public void a(long j, E e2) {
        int i = this.f702e;
        if (i == 0 || j > this.f700c[i - 1]) {
            if (this.f699b && this.f702e >= this.f700c.length) {
                a();
            }
            int i2 = this.f702e;
            if (i2 >= this.f700c.length) {
                int c2 = e.c(i2 + 1);
                long[] jArr = new long[c2];
                Object[] objArr = new Object[c2];
                long[] jArr2 = this.f700c;
                System.arraycopy(jArr2, 0, jArr, 0, jArr2.length);
                Object[] objArr2 = this.f701d;
                System.arraycopy(objArr2, 0, objArr, 0, objArr2.length);
                this.f700c = jArr;
                this.f701d = objArr;
            }
            this.f700c[i2] = j;
            this.f701d[i2] = e2;
            this.f702e = i2 + 1;
            return;
        }
        c(j, e2);
    }
}
