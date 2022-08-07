package c.a.a;

import android.view.View;
import androidx.appcompat.app.AppCompatDelegateImpl;
import c.e.h.n;
import c.e.h.s;
import c.e.h.t;

/* loaded from: classes.dex */
public class w extends t {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ AppCompatDelegateImpl f374a;

    public w(AppCompatDelegateImpl appCompatDelegateImpl) {
        this.f374a = appCompatDelegateImpl;
    }

    @Override // c.e.h.s
    public void b(View view) {
        this.f374a.q.setAlpha(1.0f);
        this.f374a.t.a((s) null);
        this.f374a.t = null;
    }

    @Override // c.e.h.t, c.e.h.s
    public void c(View view) {
        this.f374a.q.setVisibility(0);
        this.f374a.q.sendAccessibilityEvent(32);
        if (this.f374a.q.getParent() instanceof View) {
            n.r((View) this.f374a.q.getParent());
        }
    }
}
