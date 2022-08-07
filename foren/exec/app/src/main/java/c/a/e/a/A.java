package c.a.e.a;

import android.view.View;
import android.view.ViewTreeObserver;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class A implements View.OnAttachStateChangeListener {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ B f405a;

    public A(B b2) {
        this.f405a = b2;
    }

    @Override // android.view.View.OnAttachStateChangeListener
    public void onViewAttachedToWindow(View view) {
    }

    @Override // android.view.View.OnAttachStateChangeListener
    public void onViewDetachedFromWindow(View view) {
        ViewTreeObserver viewTreeObserver = this.f405a.q;
        if (viewTreeObserver != null) {
            if (!viewTreeObserver.isAlive()) {
                this.f405a.q = view.getViewTreeObserver();
            }
            B b2 = this.f405a;
            b2.q.removeGlobalOnLayoutListener(b2.k);
        }
        view.removeOnAttachStateChangeListener(this);
    }
}
