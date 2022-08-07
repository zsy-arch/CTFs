package com.autonavi.ae.gmap.gesture;

import android.content.Context;
import android.graphics.PointF;
import android.os.Build;
import android.view.MotionEvent;
import com.autonavi.amap.mapcore.FPoint;

/* loaded from: classes.dex */
public abstract class BaseGestureDetector {
    protected static final float PRESSURE_THRESHOLD = 0.67f;
    protected final Context mContext;
    protected MotionEvent mCurrEvent;
    protected float mCurrPressure;
    protected boolean mGestureInProgress;
    protected MotionEvent mPrevEvent;
    protected float mPrevPressure;
    protected long mTimeDelta;

    protected abstract void handleInProgressEvent(int i, MotionEvent motionEvent);

    protected abstract void handleStartProgressEvent(int i, MotionEvent motionEvent);

    public BaseGestureDetector(Context context) {
        this.mContext = context;
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        int action = motionEvent.getAction() & 255;
        if (!this.mGestureInProgress) {
            handleStartProgressEvent(action, motionEvent);
            return true;
        }
        handleInProgressEvent(action, motionEvent);
        return true;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void updateStateByEvent(MotionEvent motionEvent) {
        MotionEvent motionEvent2 = this.mPrevEvent;
        if (this.mCurrEvent != null) {
            this.mCurrEvent.recycle();
            this.mCurrEvent = null;
        }
        this.mCurrEvent = MotionEvent.obtain(motionEvent);
        this.mTimeDelta = motionEvent.getEventTime() - motionEvent2.getEventTime();
        if (Build.VERSION.SDK_INT >= 8) {
            this.mCurrPressure = motionEvent.getPressure(motionEvent.getActionIndex());
            this.mPrevPressure = motionEvent2.getPressure(motionEvent2.getActionIndex());
            return;
        }
        this.mCurrPressure = motionEvent.getPressure(0);
        this.mPrevPressure = motionEvent2.getPressure(0);
    }

    protected void resetState() {
        if (this.mPrevEvent != null) {
            this.mPrevEvent.recycle();
            this.mPrevEvent = null;
        }
        if (this.mCurrEvent != null) {
            this.mCurrEvent.recycle();
            this.mCurrEvent = null;
        }
        this.mGestureInProgress = false;
    }

    public boolean isInProgress() {
        return this.mGestureInProgress;
    }

    public long getTimeDelta() {
        return this.mTimeDelta;
    }

    public long getEventTime() {
        return this.mCurrEvent.getEventTime();
    }

    public MotionEvent getEvent() {
        return this.mCurrEvent;
    }

    public static FPoint determineFocalPoint(MotionEvent motionEvent) {
        float f = 0.0f;
        int pointerCount = motionEvent.getPointerCount();
        float f2 = 0.0f;
        for (int i = 0; i < pointerCount; i++) {
            f2 += motionEvent.getX(i);
            f += motionEvent.getY(i);
        }
        return FPoint.obtain(f2 / pointerCount, f / pointerCount);
    }

    public static PointF getFocalPoint(MotionEvent motionEvent) {
        float f = 0.0f;
        int pointerCount = motionEvent.getPointerCount();
        float f2 = 0.0f;
        for (int i = 0; i < pointerCount; i++) {
            f2 += getRawX(motionEvent, i);
            f += getRawY(motionEvent, i);
        }
        return new PointF(f2 / pointerCount, f / pointerCount);
    }

    private static float getRawX(MotionEvent motionEvent, int i) {
        if (i < 0) {
            return Float.MIN_VALUE;
        }
        if (i == 0) {
            return motionEvent.getRawX();
        }
        return (motionEvent.getRawX() - motionEvent.getX()) + motionEvent.getX(i);
    }

    private static float getRawY(MotionEvent motionEvent, int i) {
        if (i < 0) {
            return Float.MIN_VALUE;
        }
        if (i == 0) {
            return motionEvent.getRawY();
        }
        return (motionEvent.getRawY() - motionEvent.getY()) + motionEvent.getY(i);
    }
}
