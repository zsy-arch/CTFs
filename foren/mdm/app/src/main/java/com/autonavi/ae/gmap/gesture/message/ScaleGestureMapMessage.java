package com.autonavi.ae.gmap.gesture.message;

/* loaded from: classes.dex */
public class ScaleGestureMapMessage extends GestureMapMessage {
    public int mPivotX;
    public int mPivotY;
    public float mScaleDelta;

    public ScaleGestureMapMessage(int i, float f, int i2, int i3) {
        super(i);
        this.mScaleDelta = 0.0f;
        this.mPivotX = 0;
        this.mPivotY = 0;
        this.mScaleDelta = f;
        this.mPivotX = i2;
        this.mPivotY = i3;
    }

    @Override // com.autonavi.ae.gmap.MapMessage
    public int getType() {
        return 1;
    }
}
