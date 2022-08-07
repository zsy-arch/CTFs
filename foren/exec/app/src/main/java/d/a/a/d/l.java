package d.a.a.d;

import android.animation.ValueAnimator;
import bccsejw.sxexrix.zaswnwt.widget.PromptView;

/* loaded from: classes.dex */
public class l implements ValueAnimator.AnimatorUpdateListener {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ PromptView f1648a;

    public l(PromptView promptView) {
        this.f1648a = promptView;
    }

    @Override // android.animation.ValueAnimator.AnimatorUpdateListener
    public void onAnimationUpdate(ValueAnimator valueAnimator) {
        float f;
        float f2;
        PromptView promptView = this.f1648a;
        f = promptView.s;
        promptView.r = ((Float) valueAnimator.getAnimatedValue()).floatValue() * f;
        StringBuilder sb = new StringBuilder();
        sb.append("onAnimationUpdate: ");
        f2 = this.f1648a.r;
        sb.append(f2);
        sb.toString();
        this.f1648a.invalidate();
    }
}
