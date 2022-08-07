package com.amap.api.maps.model.animation;

import android.view.animation.Interpolator;
import com.autonavi.amap.mapcore.animation.GLAnimation;

/* loaded from: classes.dex */
public abstract class Animation {
    public GLAnimation glAnimation;

    /* loaded from: classes.dex */
    public interface AnimationListener {
        void onAnimationEnd();

        void onAnimationStart();
    }

    public abstract void setDuration(long j);

    public abstract void setInterpolator(Interpolator interpolator);

    public Animation() {
        this.glAnimation = null;
        this.glAnimation = new GLAnimation();
    }

    public void setAnimationListener(AnimationListener animationListener) {
        this.glAnimation.setAnimationListener(animationListener);
    }
}
