package c.a.a;

import android.os.Build;
import android.view.View;
import android.view.WindowInsets;
import androidx.appcompat.app.AppCompatDelegateImpl;
import c.e.h.j;
import c.e.h.n;
import c.e.h.v;

/* loaded from: classes.dex */
public class r implements j {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ AppCompatDelegateImpl f369a;

    public r(AppCompatDelegateImpl appCompatDelegateImpl) {
        this.f369a = appCompatDelegateImpl;
    }

    @Override // c.e.h.j
    public v a(View view, v vVar) {
        int d2 = vVar.d();
        int g = this.f369a.g(d2);
        if (d2 != g) {
            int b2 = vVar.b();
            int c2 = vVar.c();
            int a2 = vVar.a();
            int i = Build.VERSION.SDK_INT;
            vVar = new v(((WindowInsets) vVar.f876a).replaceSystemWindowInsets(b2, g, c2, a2));
        }
        return n.a(view, vVar);
    }
}
