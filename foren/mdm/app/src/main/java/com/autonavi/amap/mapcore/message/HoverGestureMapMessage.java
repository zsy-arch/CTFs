package com.autonavi.amap.mapcore.message;

import com.autonavi.ae.gmap.GLMapState;
import com.autonavi.ae.gmap.maploader.Pools;

/* loaded from: classes.dex */
public class HoverGestureMapMessage extends GestureMapMessage {
    private static final Pools.SynchronizedPool<HoverGestureMapMessage> mPool = new Pools.SynchronizedPool<>(256);
    public float angle_delta;

    public static HoverGestureMapMessage obtain(int i, float f) {
        HoverGestureMapMessage acquire = mPool.acquire();
        if (acquire == null) {
            acquire = new HoverGestureMapMessage(i, f);
        } else {
            acquire.reset();
        }
        acquire.setParams(i, f);
        return acquire;
    }

    public void recycle() {
        mPool.release(this);
    }

    public static void destory() {
        mPool.destory();
    }

    private void setParams(int i, float f) {
        setState(i);
        this.angle_delta = f;
    }

    public HoverGestureMapMessage(int i, float f) {
        super(i);
        this.angle_delta = 0.0f;
        this.angle_delta = f;
    }

    @Override // com.autonavi.amap.mapcore.message.GestureMapMessage, com.autonavi.ae.gmap.MapMessage
    public int getType() {
        return 3;
    }

    @Override // com.autonavi.amap.mapcore.message.GestureMapMessage
    public void runCameraUpdate(GLMapState gLMapState) {
        float f = 0.0f;
        float cameraHeaderAngle = gLMapState.getCameraHeaderAngle() + this.angle_delta;
        if (cameraHeaderAngle >= 0.0f) {
            if (cameraHeaderAngle > 65.0f) {
                f = 65.0f;
            } else {
                f = (gLMapState.getCameraHeaderAngle() <= 40.0f || cameraHeaderAngle <= 40.0f || gLMapState.getCameraHeaderAngle() <= cameraHeaderAngle) ? cameraHeaderAngle : 40.0f;
            }
        }
        gLMapState.setCameraDegree(f);
        gLMapState.recalculate();
    }
}
