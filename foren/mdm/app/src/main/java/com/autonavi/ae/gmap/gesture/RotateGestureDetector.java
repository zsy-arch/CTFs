package com.autonavi.ae.gmap.gesture;

import android.content.Context;
import android.view.MotionEvent;

/* loaded from: classes.dex */
public class RotateGestureDetector extends TwoFingerGestureDetector {
    private final OnRotateGestureListener mListener;
    private boolean mSloppyGesture;

    /* loaded from: classes.dex */
    public interface OnRotateGestureListener {
        boolean onRotate(RotateGestureDetector rotateGestureDetector);

        boolean onRotateBegin(RotateGestureDetector rotateGestureDetector);

        void onRotateEnd(RotateGestureDetector rotateGestureDetector);
    }

    /* loaded from: classes.dex */
    public static class SimpleOnRotateGestureListener implements OnRotateGestureListener {
        @Override // com.autonavi.ae.gmap.gesture.RotateGestureDetector.OnRotateGestureListener
        public boolean onRotate(RotateGestureDetector rotateGestureDetector) {
            return false;
        }

        @Override // com.autonavi.ae.gmap.gesture.RotateGestureDetector.OnRotateGestureListener
        public boolean onRotateBegin(RotateGestureDetector rotateGestureDetector) {
            return true;
        }

        @Override // com.autonavi.ae.gmap.gesture.RotateGestureDetector.OnRotateGestureListener
        public void onRotateEnd(RotateGestureDetector rotateGestureDetector) {
        }
    }

    public RotateGestureDetector(Context context, OnRotateGestureListener onRotateGestureListener) {
        super(context);
        this.mListener = onRotateGestureListener;
    }

    @Override // com.autonavi.ae.gmap.gesture.TwoFingerGestureDetector, com.autonavi.ae.gmap.gesture.BaseGestureDetector
    protected void handleStartProgressEvent(int i, MotionEvent motionEvent) {
        switch (i) {
            case 2:
                if (this.mSloppyGesture) {
                    this.mSloppyGesture = isSloppyGesture(motionEvent);
                    if (!this.mSloppyGesture) {
                        this.mGestureInProgress = this.mListener.onRotateBegin(this);
                        return;
                    }
                    return;
                }
                return;
            case 3:
            case 4:
            default:
                return;
            case 5:
                resetState();
                this.mPrevEvent = MotionEvent.obtain(motionEvent);
                this.mTimeDelta = 0L;
                updateStateByEvent(motionEvent);
                this.mSloppyGesture = isSloppyGesture(motionEvent);
                if (!this.mSloppyGesture) {
                    this.mGestureInProgress = this.mListener.onRotateBegin(this);
                    return;
                }
                return;
            case 6:
                if (!this.mSloppyGesture) {
                }
                return;
        }
    }

    @Override // com.autonavi.ae.gmap.gesture.TwoFingerGestureDetector, com.autonavi.ae.gmap.gesture.BaseGestureDetector
    protected void handleInProgressEvent(int i, MotionEvent motionEvent) {
        switch (i) {
            case 2:
                updateStateByEvent(motionEvent);
                if (this.mCurrPressure / this.mPrevPressure > 0.67f && this.mListener.onRotate(this)) {
                    this.mPrevEvent.recycle();
                    this.mPrevEvent = MotionEvent.obtain(motionEvent);
                    return;
                }
                return;
            case 3:
                if (!this.mSloppyGesture) {
                    this.mListener.onRotateEnd(this);
                }
                resetState();
                return;
            case 4:
            case 5:
            default:
                return;
            case 6:
                updateStateByEvent(motionEvent);
                if (!this.mSloppyGesture) {
                    this.mListener.onRotateEnd(this);
                }
                resetState();
                return;
        }
    }

    @Override // com.autonavi.ae.gmap.gesture.BaseGestureDetector
    protected void resetState() {
        super.resetState();
        this.mSloppyGesture = false;
    }

    public float getRotationDegreesDelta() {
        return (float) (((Math.atan2(this.mPrevFingerDiffY, this.mPrevFingerDiffX) - Math.atan2(this.mCurrFingerDiffY, this.mCurrFingerDiffX)) * 180.0d) / 3.141592653589793d);
    }
}
