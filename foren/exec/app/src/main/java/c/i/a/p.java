package c.i.a;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;

/* loaded from: classes.dex */
public class p extends AnimatorListenerAdapter {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ ViewGroup f979a;

    /* renamed from: b  reason: collision with root package name */
    public final /* synthetic */ View f980b;

    /* renamed from: c  reason: collision with root package name */
    public final /* synthetic */ Fragment f981c;

    /* renamed from: d  reason: collision with root package name */
    public final /* synthetic */ s f982d;

    public p(s sVar, ViewGroup viewGroup, View view, Fragment fragment) {
        this.f982d = sVar;
        this.f979a = viewGroup;
        this.f980b = view;
        this.f981c = fragment;
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public void onAnimationEnd(Animator animator) {
        this.f979a.endViewTransition(this.f980b);
        Animator f = this.f981c.f();
        this.f981c.a((Animator) null);
        if (f != null && this.f979a.indexOfChild(this.f980b) < 0) {
            s sVar = this.f982d;
            Fragment fragment = this.f981c;
            sVar.a(fragment, fragment.o(), 0, 0, false);
        }
    }
}
