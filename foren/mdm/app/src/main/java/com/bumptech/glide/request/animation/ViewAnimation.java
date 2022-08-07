package com.bumptech.glide.request.animation;

import android.view.View;
import android.view.animation.Animation;
import com.bumptech.glide.request.animation.GlideAnimation;

/* loaded from: classes.dex */
public class ViewAnimation<R> implements GlideAnimation<R> {
    private final AnimationFactory animationFactory;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public interface AnimationFactory {
        Animation build();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ViewAnimation(AnimationFactory animationFactory) {
        this.animationFactory = animationFactory;
    }

    @Override // com.bumptech.glide.request.animation.GlideAnimation
    public boolean animate(R current, GlideAnimation.ViewAdapter adapter) {
        View view = adapter.getView();
        if (view == null) {
            return false;
        }
        view.clearAnimation();
        view.startAnimation(this.animationFactory.build());
        return false;
    }
}
