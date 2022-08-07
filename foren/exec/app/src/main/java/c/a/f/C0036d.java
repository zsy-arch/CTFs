package c.a.f;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import androidx.appcompat.widget.ActionBarOverlayLayout;

/* JADX INFO: Access modifiers changed from: package-private */
/* renamed from: c.a.f.d  reason: case insensitive filesystem */
/* loaded from: classes.dex */
public class C0036d extends AnimatorListenerAdapter {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ ActionBarOverlayLayout f584a;

    public C0036d(ActionBarOverlayLayout actionBarOverlayLayout) {
        this.f584a = actionBarOverlayLayout;
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public void onAnimationCancel(Animator animator) {
        ActionBarOverlayLayout actionBarOverlayLayout = this.f584a;
        actionBarOverlayLayout.x = null;
        actionBarOverlayLayout.l = false;
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public void onAnimationEnd(Animator animator) {
        ActionBarOverlayLayout actionBarOverlayLayout = this.f584a;
        actionBarOverlayLayout.x = null;
        actionBarOverlayLayout.l = false;
    }
}
