package com.bumptech.glide.request.animation;

import android.graphics.drawable.Drawable;
import android.graphics.drawable.TransitionDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;

/* loaded from: classes.dex */
public class DrawableCrossFadeViewAnimation<T extends Drawable> implements GlideAnimation<T> {
    private final GlideAnimation<T> defaultAnimation;
    private final int duration;

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.bumptech.glide.request.animation.GlideAnimation
    public /* bridge */ /* synthetic */ boolean animate(Object x0, GlideAnimation.ViewAdapter x1) {
        return animate((DrawableCrossFadeViewAnimation<T>) ((Drawable) x0), x1);
    }

    public DrawableCrossFadeViewAnimation(GlideAnimation<T> defaultAnimation, int duration) {
        this.defaultAnimation = defaultAnimation;
        this.duration = duration;
    }

    public boolean animate(T current, GlideAnimation.ViewAdapter adapter) {
        Drawable previous = adapter.getCurrentDrawable();
        if (previous != null) {
            TransitionDrawable transitionDrawable = new TransitionDrawable(new Drawable[]{previous, current});
            transitionDrawable.setCrossFadeEnabled(true);
            transitionDrawable.startTransition(this.duration);
            adapter.setDrawable(transitionDrawable);
            return true;
        }
        this.defaultAnimation.animate(current, adapter);
        return false;
    }
}
