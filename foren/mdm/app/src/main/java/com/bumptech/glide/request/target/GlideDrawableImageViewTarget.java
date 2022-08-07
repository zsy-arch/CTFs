package com.bumptech.glide.request.target;

import android.widget.ImageView;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;

/* loaded from: classes.dex */
public class GlideDrawableImageViewTarget extends ImageViewTarget<GlideDrawable> {
    private static final float SQUARE_RATIO_MARGIN = 0.05f;
    private int maxLoopCount;
    private GlideDrawable resource;

    @Override // com.bumptech.glide.request.target.ImageViewTarget, com.bumptech.glide.request.target.Target
    public /* bridge */ /* synthetic */ void onResourceReady(Object x0, GlideAnimation x1) {
        onResourceReady((GlideDrawable) x0, (GlideAnimation<? super GlideDrawable>) x1);
    }

    public GlideDrawableImageViewTarget(ImageView view) {
        this(view, -1);
    }

    public GlideDrawableImageViewTarget(ImageView view, int maxLoopCount) {
        super(view);
        this.maxLoopCount = maxLoopCount;
    }

    public void onResourceReady(GlideDrawable resource, GlideAnimation<? super GlideDrawable> animation) {
        if (!resource.isAnimated()) {
            float viewRatio = ((ImageView) this.view).getWidth() / ((ImageView) this.view).getHeight();
            float drawableRatio = resource.getIntrinsicWidth() / resource.getIntrinsicHeight();
            if (Math.abs(viewRatio - 1.0f) <= SQUARE_RATIO_MARGIN && Math.abs(drawableRatio - 1.0f) <= SQUARE_RATIO_MARGIN) {
                resource = new SquaringDrawable(resource, ((ImageView) this.view).getWidth());
            }
        }
        super.onResourceReady((GlideDrawableImageViewTarget) resource, (GlideAnimation<? super GlideDrawableImageViewTarget>) animation);
        this.resource = resource;
        resource.setLoopCount(this.maxLoopCount);
        resource.start();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void setResource(GlideDrawable resource) {
        ((ImageView) this.view).setImageDrawable(resource);
    }

    @Override // com.bumptech.glide.request.target.BaseTarget, com.bumptech.glide.manager.LifecycleListener
    public void onStart() {
        if (this.resource != null) {
            this.resource.start();
        }
    }

    @Override // com.bumptech.glide.request.target.BaseTarget, com.bumptech.glide.manager.LifecycleListener
    public void onStop() {
        if (this.resource != null) {
            this.resource.stop();
        }
    }
}
