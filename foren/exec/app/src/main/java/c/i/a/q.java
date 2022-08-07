package c.i.a;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;

/* loaded from: classes.dex */
public class q extends AnimatorListenerAdapter {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ ViewGroup f983a;

    /* renamed from: b  reason: collision with root package name */
    public final /* synthetic */ View f984b;

    /* renamed from: c  reason: collision with root package name */
    public final /* synthetic */ Fragment f985c;

    public q(s sVar, ViewGroup viewGroup, View view, Fragment fragment) {
        this.f983a = viewGroup;
        this.f984b = view;
        this.f985c = fragment;
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public void onAnimationEnd(Animator animator) {
        this.f983a.endViewTransition(this.f984b);
        animator.removeListener(this);
        View view = this.f985c.K;
        if (view != null) {
            view.setVisibility(8);
        }
    }
}
