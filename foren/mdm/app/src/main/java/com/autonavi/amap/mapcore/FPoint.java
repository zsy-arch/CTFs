package com.autonavi.amap.mapcore;

import android.graphics.PointF;
import com.autonavi.ae.gmap.maploader.Pools;

/* loaded from: classes.dex */
public class FPoint extends PointF {
    private static final Pools.SynchronizedPool<FPoint> mPool = new Pools.SynchronizedPool<>(32);

    public static FPoint obtain() {
        FPoint acquire = mPool.acquire();
        if (acquire == null) {
            return new FPoint();
        }
        acquire.set(0.0f, 0.0f);
        return acquire;
    }

    public static FPoint obtain(float f, float f2) {
        FPoint acquire = mPool.acquire();
        if (acquire == null) {
            return new FPoint(f, f2);
        }
        acquire.set(f, f2);
        return acquire;
    }

    public void recycle() {
        mPool.release(this);
    }

    public FPoint() {
    }

    public FPoint(float f, float f2) {
        this.x = f;
        this.y = f2;
    }

    @Override // android.graphics.PointF
    public boolean equals(Object obj) {
        FPoint fPoint = (FPoint) obj;
        return fPoint != null && this.x == fPoint.x && this.y == fPoint.y;
    }

    @Override // android.graphics.PointF
    public int hashCode() {
        int floatToIntBits = Float.floatToIntBits(this.x) * 37;
        return Float.floatToIntBits(this.y) * 37;
    }
}
