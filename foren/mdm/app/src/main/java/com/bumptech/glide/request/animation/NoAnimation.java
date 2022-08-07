package com.bumptech.glide.request.animation;

import com.bumptech.glide.request.animation.GlideAnimation;

/* loaded from: classes.dex */
public class NoAnimation<R> implements GlideAnimation<R> {
    private static final NoAnimation<?> NO_ANIMATION = new NoAnimation<>();
    private static final GlideAnimationFactory<?> NO_ANIMATION_FACTORY = new NoAnimationFactory();

    /* loaded from: classes.dex */
    public static class NoAnimationFactory<R> implements GlideAnimationFactory<R> {
        @Override // com.bumptech.glide.request.animation.GlideAnimationFactory
        public GlideAnimation<R> build(boolean isFromMemoryCache, boolean isFirstResource) {
            return NoAnimation.NO_ANIMATION;
        }
    }

    public static <R> GlideAnimationFactory<R> getFactory() {
        return (GlideAnimationFactory<R>) NO_ANIMATION_FACTORY;
    }

    public static <R> GlideAnimation<R> get() {
        return NO_ANIMATION;
    }

    @Override // com.bumptech.glide.request.animation.GlideAnimation
    public boolean animate(Object current, GlideAnimation.ViewAdapter adapter) {
        return false;
    }
}
