package c.a.a;

import androidx.appcompat.app.AppCompatDelegateImpl;
import c.e.h.n;
import c.e.h.r;

/* loaded from: classes.dex */
public class v implements Runnable {

    /* renamed from: a */
    public final /* synthetic */ AppCompatDelegateImpl f373a;

    public v(AppCompatDelegateImpl appCompatDelegateImpl) {
        this.f373a = appCompatDelegateImpl;
    }

    @Override // java.lang.Runnable
    public void run() {
        AppCompatDelegateImpl appCompatDelegateImpl = this.f373a;
        appCompatDelegateImpl.r.showAtLocation(appCompatDelegateImpl.q, 55, 0, 0);
        this.f373a.d();
        if (this.f373a.j()) {
            this.f373a.q.setAlpha(0.0f);
            AppCompatDelegateImpl appCompatDelegateImpl2 = this.f373a;
            r a2 = n.a(appCompatDelegateImpl2.q);
            a2.a(1.0f);
            appCompatDelegateImpl2.t = a2;
            this.f373a.t.a(new u(this));
            return;
        }
        this.f373a.q.setAlpha(1.0f);
        this.f373a.q.setVisibility(0);
    }
}
