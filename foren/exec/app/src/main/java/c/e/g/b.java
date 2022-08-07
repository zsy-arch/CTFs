package c.e.g;

/* loaded from: classes.dex */
public class b<T> {

    /* renamed from: a  reason: collision with root package name */
    public final Object[] f838a;

    /* renamed from: b  reason: collision with root package name */
    public int f839b;

    public b(int i) {
        if (i > 0) {
            this.f838a = new Object[i];
            return;
        }
        throw new IllegalArgumentException("The max pool size must be > 0");
    }

    public T a() {
        int i = this.f839b;
        if (i <= 0) {
            return null;
        }
        int i2 = i - 1;
        Object[] objArr = this.f838a;
        T t = (T) objArr[i2];
        objArr[i2] = null;
        this.f839b = i - 1;
        return t;
    }

    public boolean a(T t) {
        boolean z;
        int i = 0;
        while (true) {
            if (i >= this.f839b) {
                z = false;
                break;
            } else if (this.f838a[i] == t) {
                z = true;
                break;
            } else {
                i++;
            }
        }
        if (!z) {
            int i2 = this.f839b;
            Object[] objArr = this.f838a;
            if (i2 >= objArr.length) {
                return false;
            }
            objArr[i2] = t;
            this.f839b = i2 + 1;
            return true;
        }
        throw new IllegalStateException("Already in the pool!");
    }
}
