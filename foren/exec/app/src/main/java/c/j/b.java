package c.j;

import c.j.f;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/* loaded from: classes.dex */
public class b {

    /* renamed from: a  reason: collision with root package name */
    public final int f1018a;

    /* renamed from: b  reason: collision with root package name */
    public final Method f1019b;

    public void a(h hVar, f.a aVar, Object obj) {
        try {
            int i = this.f1018a;
            if (i == 0) {
                this.f1019b.invoke(obj, new Object[0]);
            } else if (i == 1) {
                this.f1019b.invoke(obj, hVar);
            } else if (i == 2) {
                this.f1019b.invoke(obj, hVar, aVar);
            }
        } catch (IllegalAccessException e2) {
            throw new RuntimeException(e2);
        } catch (InvocationTargetException e3) {
            throw new RuntimeException("Failed to call observer method", e3.getCause());
        }
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || b.class != obj.getClass()) {
            return false;
        }
        b bVar = (b) obj;
        return this.f1018a == bVar.f1018a && this.f1019b.getName().equals(bVar.f1019b.getName());
    }

    public int hashCode() {
        return this.f1019b.getName().hashCode() + (this.f1018a * 31);
    }
}
