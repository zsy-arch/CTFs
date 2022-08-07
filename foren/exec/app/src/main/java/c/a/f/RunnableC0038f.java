package c.a.f;

import androidx.appcompat.widget.ActionBarOverlayLayout;

/* JADX INFO: Access modifiers changed from: package-private */
/* renamed from: c.a.f.f  reason: case insensitive filesystem */
/* loaded from: classes.dex */
public class RunnableC0038f implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ ActionBarOverlayLayout f591a;

    public RunnableC0038f(ActionBarOverlayLayout actionBarOverlayLayout) {
        this.f591a = actionBarOverlayLayout;
    }

    @Override // java.lang.Runnable
    public void run() {
        this.f591a.h();
        ActionBarOverlayLayout actionBarOverlayLayout = this.f591a;
        actionBarOverlayLayout.x = actionBarOverlayLayout.f74e.animate().translationY(-this.f591a.f74e.getHeight()).setListener(this.f591a.y);
    }
}
