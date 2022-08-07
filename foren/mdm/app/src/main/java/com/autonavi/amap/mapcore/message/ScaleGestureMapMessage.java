package com.autonavi.amap.mapcore.message;

import com.autonavi.ae.gmap.GLMapState;
import com.autonavi.ae.gmap.maploader.Pools;
import com.autonavi.amap.mapcore.IPoint;

/* loaded from: classes.dex */
public class ScaleGestureMapMessage extends GestureMapMessage {
    private static final Pools.SynchronizedPool<ScaleGestureMapMessage> mPool = new Pools.SynchronizedPool<>(256);
    public float scale_delta = 0.0f;
    public int pivot_x = 0;
    public int pivot_y = 0;

    public static ScaleGestureMapMessage obtain(int i, float f, int i2, int i3) {
        ScaleGestureMapMessage acquire = mPool.acquire();
        if (acquire == null) {
            return new ScaleGestureMapMessage(i, f, i2, i3);
        }
        acquire.reset();
        acquire.setParams(i, f, i2, i3);
        return acquire;
    }

    public void recycle() {
        mPool.release(this);
    }

    public static void destory() {
        mPool.destory();
    }

    private void setParams(int i, float f, int i2, int i3) {
        setState(i);
        this.scale_delta = f;
        this.pivot_x = i2;
        this.pivot_y = i3;
    }

    public ScaleGestureMapMessage(int i, float f, int i2, int i3) {
        super(i);
        setParams(i, f, i2, i3);
    }

    @Override // com.autonavi.amap.mapcore.message.GestureMapMessage, com.autonavi.ae.gmap.MapMessage
    public int getType() {
        return 1;
    }

    @Override // com.autonavi.amap.mapcore.message.GestureMapMessage
    public void runCameraUpdate(GLMapState gLMapState) {
        IPoint iPoint;
        IPoint iPoint2 = null;
        if (this.isGestureScaleByMapCenter) {
            setMapZoomer(gLMapState);
            return;
        }
        int i = this.pivot_x;
        int i2 = this.pivot_y;
        if (i > 0 || i2 > 0) {
            iPoint = IPoint.obtain();
            iPoint2 = IPoint.obtain();
            win2geo(gLMapState, i, i2, iPoint);
            gLMapState.setMapGeoCenter(iPoint.x, iPoint.y);
        } else {
            iPoint = null;
        }
        setMapZoomer(gLMapState);
        if (i > 0 || i2 > 0) {
            win2geo(gLMapState, i, i2, iPoint2);
            gLMapState.setMapGeoCenter((iPoint.x * 2) - iPoint2.x, (iPoint.y * 2) - iPoint2.y);
            gLMapState.recalculate();
        }
        if (iPoint != null) {
            iPoint.recycle();
        }
        if (iPoint2 != null) {
            iPoint2.recycle();
        }
    }

    private void setMapZoomer(GLMapState gLMapState) {
        gLMapState.setMapZoomer(this.scale_delta + gLMapState.getMapZoomer());
        gLMapState.recalculate();
    }
}
