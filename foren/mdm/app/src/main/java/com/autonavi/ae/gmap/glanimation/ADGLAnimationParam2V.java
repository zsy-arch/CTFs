package com.autonavi.ae.gmap.glanimation;

/* loaded from: classes.dex */
public class ADGLAnimationParam2V extends ADGLAnimationParam {
    public float _fromXValue;
    public float _fromYValue;
    public float _toXValue;
    public float _toYValue;

    public ADGLAnimationParam2V() {
        reset();
    }

    @Override // com.autonavi.ae.gmap.glanimation.ADGLAnimationParam
    public void reset() {
        super.reset();
        this._fromXValue = 0.0f;
        this._toXValue = 0.0f;
        this._fromYValue = 0.0f;
        this._toYValue = 0.0f;
    }

    public void setFromValue(float f, float f2) {
        this._fromXValue = f;
        this._fromYValue = f2;
        this._hasFromValue = true;
        this._hasCheckedParam = false;
    }

    public void setToValue(float f, float f2) {
        this._toXValue = f;
        this._toYValue = f2;
        this._hasToValue = true;
        this._hasCheckedParam = false;
    }

    public float getFromXValue() {
        return this._fromXValue;
    }

    public float getFromYValue() {
        return this._fromYValue;
    }

    public float getToXValue() {
        return this._toXValue;
    }

    public float getToYValue() {
        return this._toYValue;
    }

    public float getCurXValue() {
        return this._fromXValue + ((this._toXValue - this._fromXValue) * this._mult);
    }

    public float getCurYValue() {
        return this._fromYValue + ((this._toYValue - this._fromYValue) * this._mult);
    }

    @Override // com.autonavi.ae.gmap.glanimation.ADGLAnimationParam
    public void checkParam() {
        this._needToCaculate = false;
        if (this._hasFromValue && this._hasToValue) {
            float f = this._toXValue - this._fromXValue;
            float f2 = this._toYValue - this._fromYValue;
            if (Math.abs(f) > 1.0E-4d || Math.abs(f2) > 1.0E-4d) {
                this._needToCaculate = true;
            }
        }
        this._hasCheckedParam = true;
    }
}
