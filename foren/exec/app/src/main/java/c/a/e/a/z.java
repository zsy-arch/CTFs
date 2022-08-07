package c.a.e.a;

import android.view.View;
import android.view.ViewTreeObserver;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class z implements ViewTreeObserver.OnGlobalLayoutListener {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ B f479a;

    public z(B b2) {
        this.f479a = b2;
    }

    @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
    public void onGlobalLayout() {
        if (this.f479a.b() && !this.f479a.j.k()) {
            View view = this.f479a.o;
            if (view == null || !view.isShown()) {
                this.f479a.dismiss();
            } else {
                this.f479a.j.c();
            }
        }
    }
}
