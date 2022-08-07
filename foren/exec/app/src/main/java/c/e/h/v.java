package c.e.h;

import android.os.Build;
import android.view.WindowInsets;

/* loaded from: classes.dex */
public class v {

    /* renamed from: a  reason: collision with root package name */
    public final Object f876a;

    public v(Object obj) {
        this.f876a = obj;
    }

    public int a() {
        int i = Build.VERSION.SDK_INT;
        return ((WindowInsets) this.f876a).getSystemWindowInsetBottom();
    }

    public int b() {
        int i = Build.VERSION.SDK_INT;
        return ((WindowInsets) this.f876a).getSystemWindowInsetLeft();
    }

    public int c() {
        int i = Build.VERSION.SDK_INT;
        return ((WindowInsets) this.f876a).getSystemWindowInsetRight();
    }

    public int d() {
        int i = Build.VERSION.SDK_INT;
        return ((WindowInsets) this.f876a).getSystemWindowInsetTop();
    }

    public boolean e() {
        int i = Build.VERSION.SDK_INT;
        return ((WindowInsets) this.f876a).isConsumed();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || v.class != obj.getClass()) {
            return false;
        }
        v vVar = (v) obj;
        Object obj2 = this.f876a;
        return obj2 == null ? vVar.f876a == null : obj2.equals(vVar.f876a);
    }

    public int hashCode() {
        Object obj = this.f876a;
        if (obj == null) {
            return 0;
        }
        return obj.hashCode();
    }
}
