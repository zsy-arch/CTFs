package c.e.h;

import android.animation.ValueAnimator;
import android.view.View;
import c.a.a.H;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class q implements ValueAnimator.AnimatorUpdateListener {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ u f870a;

    /* renamed from: b  reason: collision with root package name */
    public final /* synthetic */ View f871b;

    public q(r rVar, u uVar, View view) {
        this.f870a = uVar;
        this.f871b = view;
    }

    @Override // android.animation.ValueAnimator.AnimatorUpdateListener
    public void onAnimationUpdate(ValueAnimator valueAnimator) {
        u uVar = this.f870a;
        View view = this.f871b;
        ((View) ((H) uVar).f332a.f.getParent()).invalidate();
    }
}
