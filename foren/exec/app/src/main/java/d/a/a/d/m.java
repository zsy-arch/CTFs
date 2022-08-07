package d.a.a.d;

import android.animation.ValueAnimator;
import bccsejw.sxexrix.zaswnwt.widget.PromptView;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class m implements ValueAnimator.AnimatorUpdateListener {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ PromptView f1649a;

    public m(PromptView promptView) {
        this.f1649a = promptView;
    }

    @Override // android.animation.ValueAnimator.AnimatorUpdateListener
    public void onAnimationUpdate(ValueAnimator valueAnimator) {
        float f;
        PromptView promptView = this.f1649a;
        f = promptView.s;
        promptView.r = ((Float) valueAnimator.getAnimatedValue()).floatValue() * f;
        this.f1649a.invalidate();
    }
}
