package c.c;

/* loaded from: classes.dex */
public class j<E> implements Cloneable {

    /* renamed from: a  reason: collision with root package name */
    public static final Object f728a = new Object();

    /* renamed from: b  reason: collision with root package name */
    public boolean f729b;

    /* renamed from: c  reason: collision with root package name */
    public int[] f730c;

    /* renamed from: d  reason: collision with root package name */
    public Object[] f731d;

    /* renamed from: e  reason: collision with root package name */
    public int f732e;

    public j() {
        this(10);
    }

    public E a(int i) {
        return b(i, null);
    }

    public E b(int i, E e2) {
        int a2 = e.a(this.f730c, this.f732e, i);
        if (a2 >= 0) {
            Object[] objArr = this.f731d;
            if (objArr[a2] != f728a) {
                return (E) objArr[a2];
            }
        }
        return e2;
    }

    public void c(int i) {
        Object[] objArr;
        Object obj;
        int a2 = e.a(this.f730c, this.f732e, i);
        if (a2 >= 0 && (objArr = this.f731d)[a2] != (obj = f728a)) {
            objArr[a2] = obj;
            this.f729b = true;
        }
    }

    public E d(int i) {
        if (this.f729b) {
            a();
        }
        return (E) this.f731d[i];
    }

    public String toString() {
        if (b() <= 0) {
            return "{}";
        }
        StringBuilder sb = new StringBuilder(this.f732e * 28);
        sb.append('{');
        for (int i = 0; i < this.f732e; i++) {
            if (i > 0) {
                sb.append(", ");
            }
            sb.append(b(i));
            sb.append('=');
            E d2 = d(i);
            if (d2 != this) {
                sb.append(d2);
            } else {
                sb.append("(this Map)");
            }
        }
        sb.append('}');
        return sb.toString();
    }

    public j(int i) {
        this.f729b = false;
        if (i == 0) {
            this.f730c = e.f695a;
            this.f731d = e.f697c;
        } else {
            int b2 = e.b(i);
            this.f730c = new int[b2];
            this.f731d = new Object[b2];
        }
        this.f732e = 0;
    }

    public final void a() {
        int i = this.f732e;
        int[] iArr = this.f730c;
        Object[] objArr = this.f731d;
        int i2 = 0;
        for (int i3 = 0; i3 < i; i3++) {
            Object obj = objArr[i3];
            if (obj != f728a) {
                if (i3 != i2) {
                    iArr[i2] = iArr[i3];
                    objArr[i2] = obj;
                    objArr[i3] = null;
                }
                i2++;
            }
        }
        this.f729b = false;
        this.f732e = i2;
    }

    public j<E> clone() {
        try {
            j<E> jVar = (j) super.clone();
            jVar.f730c = (int[]) this.f730c.clone();
            jVar.f731d = (Object[]) this.f731d.clone();
            return jVar;
        } catch (CloneNotSupportedException e2) {
            throw new AssertionError(e2);
        }
    }

    public int b() {
        if (this.f729b) {
            a();
        }
        return this.f732e;
    }

    public void c(int i, E e2) {
        int a2 = e.a(this.f730c, this.f732e, i);
        if (a2 >= 0) {
            this.f731d[a2] = e2;
            return;
        }
        int i2 = ~a2;
        if (i2 < this.f732e) {
            Object[] objArr = this.f731d;
            if (objArr[i2] == f728a) {
                this.f730c[i2] = i;
                objArr[i2] = e2;
                return;
            }
        }
        if (this.f729b && this.f732e >= this.f730c.length) {
            a();
            i2 = ~e.a(this.f730c, this.f732e, i);
        }
        int i3 = this.f732e;
        if (i3 >= this.f730c.length) {
            int b2 = e.b(i3 + 1);
            int[] iArr = new int[b2];
            Object[] objArr2 = new Object[b2];
            int[] iArr2 = this.f730c;
            System.arraycopy(iArr2, 0, iArr, 0, iArr2.length);
            Object[] objArr3 = this.f731d;
            System.arraycopy(objArr3, 0, objArr2, 0, objArr3.length);
            this.f730c = iArr;
            this.f731d = objArr2;
        }
        int i4 = this.f732e;
        if (i4 - i2 != 0) {
            int[] iArr3 = this.f730c;
            int i5 = i2 + 1;
            System.arraycopy(iArr3, i2, iArr3, i5, i4 - i2);
            Object[] objArr4 = this.f731d;
            System.arraycopy(objArr4, i2, objArr4, i5, this.f732e - i2);
        }
        this.f730c[i2] = i;
        this.f731d[i2] = e2;
        this.f732e++;
    }

    public int b(int i) {
        if (this.f729b) {
            a();
        }
        return this.f730c[i];
    }

    public void a(int i, E e2) {
        int i2 = this.f732e;
        if (i2 == 0 || i > this.f730c[i2 - 1]) {
            if (this.f729b && this.f732e >= this.f730c.length) {
                a();
            }
            int i3 = this.f732e;
            if (i3 >= this.f730c.length) {
                int b2 = e.b(i3 + 1);
                int[] iArr = new int[b2];
                Object[] objArr = new Object[b2];
                int[] iArr2 = this.f730c;
                System.arraycopy(iArr2, 0, iArr, 0, iArr2.length);
                Object[] objArr2 = this.f731d;
                System.arraycopy(objArr2, 0, objArr, 0, objArr2.length);
                this.f730c = iArr;
                this.f731d = objArr;
            }
            this.f730c[i3] = i;
            this.f731d[i3] = e2;
            this.f732e = i3 + 1;
            return;
        }
        c(i, e2);
    }
}
