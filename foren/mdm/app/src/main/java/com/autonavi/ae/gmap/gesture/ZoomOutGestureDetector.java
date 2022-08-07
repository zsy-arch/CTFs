package com.autonavi.ae.gmap.gesture;

import android.content.Context;
import android.graphics.PointF;
import android.view.MotionEvent;
import com.autonavi.amap.mapcore.FPoint;

/* loaded from: classes.dex */
public class ZoomOutGestureDetector extends TwoFingerGestureDetector {
    private static final PointF FOCUS_DELTA_ZERO = new PointF();
    private FPoint mCurrFocusInternal;
    private final OnZoomOutGestureListener mListener;
    private FPoint mPrevFocusInternal;
    private boolean mSloppyGesture;
    private PointF mFocusExternal = new PointF();
    private PointF mFocusDeltaExternal = new PointF();

    /* loaded from: classes.dex */
    public interface OnZoomOutGestureListener {
        void onZoomOut(ZoomOutGestureDetector zoomOutGestureDetector);

        boolean onZoomOutBegin(ZoomOutGestureDetector zoomOutGestureDetector);
    }

    /* loaded from: classes.dex */
    public static class SimpleOnZoomOutGestureListener implements OnZoomOutGestureListener {
        @Override // com.autonavi.ae.gmap.gesture.ZoomOutGestureDetector.OnZoomOutGestureListener
        public boolean onZoomOutBegin(ZoomOutGestureDetector zoomOutGestureDetector) {
            return true;
        }

        @Override // com.autonavi.ae.gmap.gesture.ZoomOutGestureDetector.OnZoomOutGestureListener
        public void onZoomOut(ZoomOutGestureDetector zoomOutGestureDetector) {
        }
    }

    public ZoomOutGestureDetector(Context context, OnZoomOutGestureListener onZoomOutGestureListener) {
        super(context);
        this.mListener = onZoomOutGestureListener;
    }

    @Override // com.autonavi.ae.gmap.gesture.TwoFingerGestureDetector, com.autonavi.ae.gmap.gesture.BaseGestureDetector
    protected void handleStartProgressEvent(int i, MotionEvent motionEvent) {
        switch (i) {
            case 5:
                resetState();
                this.mPrevEvent = MotionEvent.obtain(motionEvent);
                this.mTimeDelta = 0L;
                updateStateByEvent(motionEvent);
                this.mSloppyGesture = isSloppyGesture(motionEvent);
                if (!this.mSloppyGesture) {
                    this.mGestureInProgress = this.mListener.onZoomOutBegin(this);
                    return;
                }
                return;
            default:
                return;
        }
    }

    @Override // com.autonavi.ae.gmap.gesture.TwoFingerGestureDetector, com.autonavi.ae.gmap.gesture.BaseGestureDetector
    protected void handleInProgressEvent(int i, MotionEvent motionEvent) {
        switch (i) {
            case 3:
                resetState();
                return;
            case 4:
            case 5:
            default:
                return;
            case 6:
                updateStateByEvent(motionEvent);
                if (!this.mSloppyGesture) {
                    this.mListener.onZoomOut(this);
                }
                resetState();
                return;
        }
    }

    @Override // com.autonavi.ae.gmap.gesture.BaseGestureDetector
    protected void resetState() {
        super.resetState();
        this.mSloppyGesture = false;
        this.mFocusExternal.x = 0.0f;
        this.mFocusDeltaExternal.x = 0.0f;
        this.mFocusExternal.y = 0.0f;
        this.mFocusDeltaExternal.y = 0.0f;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.autonavi.ae.gmap.gesture.TwoFingerGestureDetector, com.autonavi.ae.gmap.gesture.BaseGestureDetector
    public void updateStateByEvent(MotionEvent motionEvent) {
        super.updateStateByEvent(motionEvent);
        MotionEvent motionEvent2 = this.mPrevEvent;
        this.mCurrFocusInternal = determineFocalPoint(motionEvent);
        this.mPrevFocusInternal = determineFocalPoint(motionEvent2);
        this.mFocusDeltaExternal = this.mPrevEvent.getPointerCount() != motionEvent.getPointerCount() ? FOCUS_DELTA_ZERO : new PointF(this.mCurrFocusInternal.x - this.mPrevFocusInternal.x, this.mCurrFocusInternal.y - this.mPrevFocusInternal.y);
        this.mCurrFocusInternal.recycle();
        this.mPrevFocusInternal.recycle();
        this.mFocusExternal.x += this.mFocusDeltaExternal.x;
        this.mFocusExternal.y += this.mFocusDeltaExternal.y;
    }

    public float getFocusX() {
        return this.mFocusExternal.x;
    }

    public float getFocusY() {
        return this.mFocusExternal.y;
    }
}
