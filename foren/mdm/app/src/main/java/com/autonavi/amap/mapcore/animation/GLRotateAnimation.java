package com.autonavi.amap.mapcore.animation;

/* loaded from: classes.dex */
public class GLRotateAnimation extends GLAnimation {
    public float mFromDegrees;
    private float mPivotX;
    private float mPivotY;
    public float mToDegrees;
    public float mCurDegrees = 0.0f;
    private float mPivotXValue = 0.0f;
    private float mPivotYValue = 0.0f;

    public GLRotateAnimation(float f, float f2, float f3, float f4, float f5) {
        this.mFromDegrees = 0.0f;
        this.mToDegrees = 1.0f;
        this.mFromDegrees = f;
        this.mToDegrees = f2;
    }

    @Override // com.autonavi.amap.mapcore.animation.GLAnimation
    protected void applyTransformation(float f, GLTransformation gLTransformation) {
        float f2 = this.mFromDegrees + ((this.mToDegrees - this.mFromDegrees) * f);
        getScaleFactor();
        this.mCurDegrees = f2;
        if (this.mPivotX == 0.0f && this.mPivotY == 0.0f) {
            gLTransformation.rotate = f2;
        } else {
            gLTransformation.rotate = f2;
        }
    }
}
