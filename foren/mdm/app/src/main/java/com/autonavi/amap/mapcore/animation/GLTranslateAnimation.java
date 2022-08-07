package com.autonavi.amap.mapcore.animation;

import com.amap.api.maps.model.LatLng;

/* loaded from: classes.dex */
public class GLTranslateAnimation extends GLAnimation {
    public double mToXDelta;
    public double mToYDelta;
    public double mFromXDelta = 0.0d;
    public double mFromYDelta = 0.0d;
    public double mCurXDelta = 0.0d;
    public double mCurYDelta = 0.0d;

    public GLTranslateAnimation(LatLng latLng) {
        this.mToXDelta = 0.0d;
        this.mToYDelta = 0.0d;
        this.mToXDelta = latLng.longitude;
        this.mToYDelta = latLng.latitude;
    }

    public void setFromPoint(LatLng latLng) {
        this.mFromXDelta = latLng.longitude;
        this.mFromYDelta = latLng.latitude;
    }

    @Override // com.autonavi.amap.mapcore.animation.GLAnimation
    protected void applyTransformation(float f, GLTransformation gLTransformation) {
        this.mCurXDelta = this.mFromXDelta;
        this.mCurYDelta = this.mFromYDelta;
        if (this.mFromXDelta != this.mToXDelta) {
            this.mCurXDelta = this.mFromXDelta + ((this.mToXDelta - this.mFromXDelta) * f);
        }
        if (this.mFromYDelta != this.mToYDelta) {
            this.mCurYDelta = this.mFromYDelta + ((this.mToYDelta - this.mFromYDelta) * f);
        }
        gLTransformation.x = this.mCurXDelta;
        gLTransformation.y = this.mCurYDelta;
    }
}
