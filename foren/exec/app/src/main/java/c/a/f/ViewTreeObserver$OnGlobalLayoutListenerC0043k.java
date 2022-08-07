package c.a.f;

import android.view.ViewTreeObserver;
import androidx.appcompat.widget.ActivityChooserView;
import c.e.h.b;

/* JADX INFO: Access modifiers changed from: package-private */
/* renamed from: c.a.f.k  reason: case insensitive filesystem */
/* loaded from: classes.dex */
public class ViewTreeObserver$OnGlobalLayoutListenerC0043k implements ViewTreeObserver.OnGlobalLayoutListener {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ ActivityChooserView f603a;

    public ViewTreeObserver$OnGlobalLayoutListenerC0043k(ActivityChooserView activityChooserView) {
        this.f603a = activityChooserView;
    }

    @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
    public void onGlobalLayout() {
        if (!this.f603a.b()) {
            return;
        }
        if (!this.f603a.isShown()) {
            this.f603a.getListPopupWindow().dismiss();
            return;
        }
        this.f603a.getListPopupWindow().c();
        b bVar = this.f603a.j;
        if (bVar != null) {
            bVar.a(true);
        }
    }
}
