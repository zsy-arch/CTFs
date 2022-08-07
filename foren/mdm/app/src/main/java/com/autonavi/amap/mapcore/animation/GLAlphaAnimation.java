package com.autonavi.amap.mapcore.animation;

/* loaded from: classes.dex */
public class GLAlphaAnimation extends GLAnimation {
    public float mCurAlpha = 0.0f;
    public float mFromAlpha;
    public float mToAlpha;

    public GLAlphaAnimation(float f, float f2) {
        this.mFromAlpha = 0.0f;
        this.mToAlpha = 1.0f;
        this.mFromAlpha = f;
        this.mToAlpha = f2;
    }

    @Override // com.autonavi.amap.mapcore.animation.GLAnimation
    protected void applyTransformation(float f, GLTransformation gLTransformation) {
        float f2 = this.mFromAlpha;
        this.mCurAlpha = f2 + ((this.mToAlpha - f2) * f);
        gLTransformation.alpha = this.mCurAlpha;
    }
}
