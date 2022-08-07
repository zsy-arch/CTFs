package com.autonavi.ae.gmap.gesture.message;

/* loaded from: classes.dex */
public class HoverGestureMapMessage extends GestureMapMessage {
    public float mAngleDelta;

    public HoverGestureMapMessage(int i, float f) {
        super(i);
        this.mAngleDelta = 0.0f;
        this.mAngleDelta = f;
    }

    @Override // com.autonavi.ae.gmap.MapMessage
    public int getType() {
        return 3;
    }
}
