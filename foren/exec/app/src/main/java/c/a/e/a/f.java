package c.a.e.a;

import android.view.View;
import android.view.ViewTreeObserver;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class f implements View.OnAttachStateChangeListener {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ i f425a;

    public f(i iVar) {
        this.f425a = iVar;
    }

    @Override // android.view.View.OnAttachStateChangeListener
    public void onViewAttachedToWindow(View view) {
    }

    @Override // android.view.View.OnAttachStateChangeListener
    public void onViewDetachedFromWindow(View view) {
        ViewTreeObserver viewTreeObserver = this.f425a.z;
        if (viewTreeObserver != null) {
            if (!viewTreeObserver.isAlive()) {
                this.f425a.z = view.getViewTreeObserver();
            }
            i iVar = this.f425a;
            iVar.z.removeGlobalOnLayoutListener(iVar.k);
        }
        view.removeOnAttachStateChangeListener(this);
    }
}
