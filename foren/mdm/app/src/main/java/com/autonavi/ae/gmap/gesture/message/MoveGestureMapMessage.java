package com.autonavi.ae.gmap.gesture.message;

/* loaded from: classes.dex */
public class MoveGestureMapMessage extends GestureMapMessage {
    public float mTouchDeltaX;
    public float mTouchDeltaY;

    public MoveGestureMapMessage(int i, float f, float f2) {
        super(i);
        this.mTouchDeltaX = 0.0f;
        this.mTouchDeltaY = 0.0f;
        this.mTouchDeltaX = f;
        this.mTouchDeltaY = f2;
    }

    @Override // com.autonavi.ae.gmap.MapMessage
    public int getType() {
        return 0;
    }
}
