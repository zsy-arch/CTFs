package com.autonavi.amap.mapcore.message;

import com.autonavi.ae.gmap.GLMapState;
import com.autonavi.ae.gmap.maploader.Pools;
import com.autonavi.amap.mapcore.FPoint;

/* loaded from: classes.dex */
public class MoveGestureMapMessage extends GestureMapMessage {
    public float touch_delta_x;
    public float touch_delta_y;
    static int newCount = 0;
    private static final Pools.SynchronizedPool<MoveGestureMapMessage> mPool = new Pools.SynchronizedPool<>(1024);

    public static synchronized MoveGestureMapMessage obtain(int i, float f, float f2) {
        MoveGestureMapMessage acquire;
        synchronized (MoveGestureMapMessage.class) {
            acquire = mPool.acquire();
            if (acquire == null) {
                acquire = new MoveGestureMapMessage(i, f, f2);
            } else {
                acquire.reset();
                acquire.setParams(i, f, f2);
            }
        }
        return acquire;
    }

    public void recycle() {
        mPool.release(this);
    }

    public static void destory() {
        mPool.destory();
    }

    private void setParams(int i, float f, float f2) {
        setState(i);
        this.touch_delta_x = f;
        this.touch_delta_y = f2;
    }

    public MoveGestureMapMessage(int i, float f, float f2) {
        super(i);
        this.touch_delta_x = 0.0f;
        this.touch_delta_y = 0.0f;
        this.touch_delta_x = f;
        this.touch_delta_y = f2;
        newCount++;
    }

    @Override // com.autonavi.amap.mapcore.message.GestureMapMessage, com.autonavi.ae.gmap.MapMessage
    public int getType() {
        return 0;
    }

    @Override // com.autonavi.amap.mapcore.message.GestureMapMessage
    public void runCameraUpdate(GLMapState gLMapState) {
        FPoint obtain = FPoint.obtain();
        gLMapState.win2Map((int) ((this.width >> 1) - ((int) this.touch_delta_x)), (int) ((this.height >> 1) - ((int) this.touch_delta_y)), obtain);
        gLMapState.setMapGlCenter(obtain.x, obtain.y);
        gLMapState.recalculate();
        obtain.recycle();
    }
}
