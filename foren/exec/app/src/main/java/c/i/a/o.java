package c.i.a;

import android.view.ViewGroup;
import android.view.animation.Animation;
import androidx.fragment.app.Fragment;
import c.i.a.s;

/* loaded from: classes.dex */
public class o extends s.b {

    /* renamed from: b */
    public final /* synthetic */ ViewGroup f976b;

    /* renamed from: c */
    public final /* synthetic */ Fragment f977c;

    /* renamed from: d */
    public final /* synthetic */ s f978d;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public o(s sVar, Animation.AnimationListener animationListener, ViewGroup viewGroup, Fragment fragment) {
        super(animationListener);
        this.f978d = sVar;
        this.f976b = viewGroup;
        this.f977c = fragment;
    }

    @Override // android.view.animation.Animation.AnimationListener
    public void onAnimationEnd(Animation animation) {
        Animation.AnimationListener animationListener = this.f993a;
        if (animationListener != null) {
            animationListener.onAnimationEnd(animation);
        }
        this.f976b.post(new RunnableC0071n(this));
    }
}
