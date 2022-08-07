package c.i.a;

import android.view.View;
import android.view.ViewTreeObserver;

/* loaded from: classes.dex */
public class M implements ViewTreeObserver.OnPreDrawListener, View.OnAttachStateChangeListener {

    /* renamed from: a  reason: collision with root package name */
    public final View f945a;

    /* renamed from: b  reason: collision with root package name */
    public ViewTreeObserver f946b;

    /* renamed from: c  reason: collision with root package name */
    public final Runnable f947c;

    public M(View view, Runnable runnable) {
        this.f945a = view;
        this.f946b = view.getViewTreeObserver();
        this.f947c = runnable;
    }

    public static M a(View view, Runnable runnable) {
        M m = new M(view, runnable);
        view.getViewTreeObserver().addOnPreDrawListener(m);
        view.addOnAttachStateChangeListener(m);
        return m;
    }

    @Override // android.view.ViewTreeObserver.OnPreDrawListener
    public boolean onPreDraw() {
        a();
        this.f947c.run();
        return true;
    }

    @Override // android.view.View.OnAttachStateChangeListener
    public void onViewAttachedToWindow(View view) {
        this.f946b = view.getViewTreeObserver();
    }

    @Override // android.view.View.OnAttachStateChangeListener
    public void onViewDetachedFromWindow(View view) {
        a();
    }

    public void a() {
        if (this.f946b.isAlive()) {
            this.f946b.removeOnPreDrawListener(this);
        } else {
            this.f945a.getViewTreeObserver().removeOnPreDrawListener(this);
        }
        this.f945a.removeOnAttachStateChangeListener(this);
    }
}
