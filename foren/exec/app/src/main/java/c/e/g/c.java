package c.e.g;

/* loaded from: classes.dex */
public class c<T> extends b<T> {

    /* renamed from: c  reason: collision with root package name */
    public final Object f840c = new Object();

    public c(int i) {
        super(i);
    }

    @Override // c.e.g.b
    public T a() {
        T t;
        synchronized (this.f840c) {
            int i = this.f839b;
            t = null;
            if (i > 0) {
                int i2 = i - 1;
                Object[] objArr = this.f838a;
                t = (T) objArr[i2];
                objArr[i2] = null;
                this.f839b = i2;
            }
        }
        return t;
    }

    @Override // c.e.g.b
    public boolean a(T t) {
        boolean z;
        boolean z2;
        synchronized (this.f840c) {
            z = false;
            int i = 0;
            while (true) {
                try {
                    if (i >= this.f839b) {
                        z2 = false;
                        break;
                    } else if (this.f838a[i] == t) {
                        z2 = true;
                        break;
                    } else {
                        i++;
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
            if (!z2) {
                int i2 = this.f839b;
                Object[] objArr = this.f838a;
                if (i2 < objArr.length) {
                    objArr[i2] = t;
                    this.f839b = i2 + 1;
                    z = true;
                }
            } else {
                throw new IllegalStateException("Already in the pool!");
            }
        }
        return z;
    }
}
