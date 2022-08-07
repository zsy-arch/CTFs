package c.a.f;

import androidx.appcompat.widget.ActionBarOverlayLayout;

/* JADX INFO: Access modifiers changed from: package-private */
/* renamed from: c.a.f.e  reason: case insensitive filesystem */
/* loaded from: classes.dex */
public class RunnableC0037e implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ ActionBarOverlayLayout f590a;

    public RunnableC0037e(ActionBarOverlayLayout actionBarOverlayLayout) {
        this.f590a = actionBarOverlayLayout;
    }

    @Override // java.lang.Runnable
    public void run() {
        this.f590a.h();
        ActionBarOverlayLayout actionBarOverlayLayout = this.f590a;
        actionBarOverlayLayout.x = actionBarOverlayLayout.f74e.animate().translationY(0.0f).setListener(this.f590a.y);
    }
}
