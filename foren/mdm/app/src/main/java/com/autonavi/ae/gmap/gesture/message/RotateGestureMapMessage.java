package com.autonavi.ae.gmap.gesture.message;

/* loaded from: classes.dex */
public class RotateGestureMapMessage extends GestureMapMessage {
    public float mAngleDelta;
    public int mPivotX;
    public int mPivotY;

    public RotateGestureMapMessage(int i, float f, int i2, int i3) {
        super(i);
        this.mPivotX = 0;
        this.mPivotY = 0;
        this.mAngleDelta = 0.0f;
        this.mAngleDelta = f;
        this.mPivotX = i2;
        this.mPivotY = i3;
    }

    @Override // com.autonavi.ae.gmap.MapMessage
    public int getType() {
        return 2;
    }
}
