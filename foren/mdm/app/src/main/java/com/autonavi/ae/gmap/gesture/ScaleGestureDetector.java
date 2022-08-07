package com.autonavi.ae.gmap.gesture;

import android.content.Context;
import android.os.Build;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.ViewConfiguration;

/* loaded from: classes.dex */
public class ScaleGestureDetector {
    private static final float PRESSURE_THRESHOLD = 0.67f;
    private boolean mActive0MostRecent;
    private int mActiveId0;
    private int mActiveId1;
    private float mBottomSlopEdge;
    private final Context mContext;
    private MotionEvent mCurrEvent;
    private float mCurrFingerDiffX;
    private float mCurrFingerDiffY;
    private float mCurrLen;
    private float mCurrPressure;
    private final float mEdgeSlop;
    private float mFocusX;
    private float mFocusY;
    private boolean mGestureInProgress;
    private boolean mInvalidGesture;
    private final OnScaleGestureListener mListener;
    private MotionEvent mPrevEvent;
    private float mPrevFingerDiffX;
    private float mPrevFingerDiffY;
    private float mPrevLen;
    private float mPrevPressure;
    private float mRightSlopEdge;
    private float mScaleFactor;
    private boolean mSloppyGesture;
    private long mTimeDelta;

    /* loaded from: classes.dex */
    public interface OnScaleGestureListener {
        boolean onScale(ScaleGestureDetector scaleGestureDetector);

        boolean onScaleBegin(ScaleGestureDetector scaleGestureDetector);

        void onScaleEnd(ScaleGestureDetector scaleGestureDetector);
    }

    /* loaded from: classes.dex */
    public static class SimpleOnScaleGestureListener implements OnScaleGestureListener {
        @Override // com.autonavi.ae.gmap.gesture.ScaleGestureDetector.OnScaleGestureListener
        public boolean onScale(ScaleGestureDetector scaleGestureDetector) {
            return false;
        }

        @Override // com.autonavi.ae.gmap.gesture.ScaleGestureDetector.OnScaleGestureListener
        public boolean onScaleBegin(ScaleGestureDetector scaleGestureDetector) {
            return true;
        }

        @Override // com.autonavi.ae.gmap.gesture.ScaleGestureDetector.OnScaleGestureListener
        public void onScaleEnd(ScaleGestureDetector scaleGestureDetector) {
        }
    }

    public ScaleGestureDetector(Context context, OnScaleGestureListener onScaleGestureListener) {
        ViewConfiguration viewConfiguration = ViewConfiguration.get(context);
        this.mContext = context;
        this.mListener = onScaleGestureListener;
        this.mEdgeSlop = viewConfiguration.getScaledEdgeSlop();
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        int findNewActiveIndex;
        boolean z;
        int i;
        int findNewActiveIndex2;
        int i2;
        int i3;
        int i4 = -1;
        boolean z2 = true;
        boolean z3 = false;
        int action = motionEvent.getAction() & 255;
        if (action == 0) {
            reset();
        }
        if (!this.mInvalidGesture) {
            if (this.mGestureInProgress) {
                switch (action) {
                    case 1:
                        reset();
                        break;
                    case 2:
                        setContext(motionEvent);
                        if (this.mCurrPressure / this.mPrevPressure > PRESSURE_THRESHOLD && this.mListener.onScale(this)) {
                            this.mPrevEvent.recycle();
                            this.mPrevEvent = MotionEvent.obtain(motionEvent);
                            break;
                        }
                        break;
                    case 3:
                        this.mListener.onScaleEnd(this);
                        reset();
                        break;
                    case 5:
                        this.mListener.onScaleEnd(this);
                        int i5 = this.mActiveId0;
                        int i6 = this.mActiveId1;
                        reset();
                        this.mPrevEvent = MotionEvent.obtain(motionEvent);
                        if (!this.mActive0MostRecent) {
                            i5 = i6;
                        }
                        this.mActiveId0 = i5;
                        if (Build.VERSION.SDK_INT >= 8) {
                            this.mActiveId1 = motionEvent.getPointerId(motionEvent.getActionIndex());
                        } else {
                            this.mActiveId1 = motionEvent.getPointerId(1);
                        }
                        this.mActive0MostRecent = false;
                        int findPointerIndex = motionEvent.findPointerIndex(this.mActiveId0);
                        if (findPointerIndex < 0 || this.mActiveId0 == this.mActiveId1) {
                            if (this.mActiveId0 != this.mActiveId1) {
                                i4 = this.mActiveId1;
                            }
                            this.mActiveId0 = motionEvent.getPointerId(findNewActiveIndex(motionEvent, i4, findPointerIndex));
                        }
                        setContext(motionEvent);
                        this.mGestureInProgress = this.mListener.onScaleBegin(this);
                        break;
                    case 6:
                        int pointerCount = motionEvent.getPointerCount();
                        int actionIndex = Build.VERSION.SDK_INT >= 8 ? motionEvent.getActionIndex() : 0;
                        int pointerId = motionEvent.getPointerId(actionIndex);
                        if (pointerCount > 2) {
                            if (pointerId == this.mActiveId0) {
                                int findNewActiveIndex3 = findNewActiveIndex(motionEvent, this.mActiveId1, actionIndex);
                                if (findNewActiveIndex3 >= 0) {
                                    this.mListener.onScaleEnd(this);
                                    this.mActiveId0 = motionEvent.getPointerId(findNewActiveIndex3);
                                    this.mActive0MostRecent = true;
                                    this.mPrevEvent = MotionEvent.obtain(motionEvent);
                                    setContext(motionEvent);
                                    this.mGestureInProgress = this.mListener.onScaleBegin(this);
                                } else {
                                    z3 = true;
                                }
                            } else if (pointerId == this.mActiveId1) {
                                int findNewActiveIndex4 = findNewActiveIndex(motionEvent, this.mActiveId0, actionIndex);
                                if (findNewActiveIndex4 >= 0) {
                                    this.mListener.onScaleEnd(this);
                                    this.mActiveId1 = motionEvent.getPointerId(findNewActiveIndex4);
                                    this.mActive0MostRecent = false;
                                    this.mPrevEvent = MotionEvent.obtain(motionEvent);
                                    setContext(motionEvent);
                                    this.mGestureInProgress = this.mListener.onScaleBegin(this);
                                } else {
                                    z3 = true;
                                }
                            }
                            this.mPrevEvent.recycle();
                            this.mPrevEvent = MotionEvent.obtain(motionEvent);
                            setContext(motionEvent);
                        } else {
                            z3 = true;
                        }
                        if (z3) {
                            setContext(motionEvent);
                            int i7 = pointerId == this.mActiveId0 ? this.mActiveId1 : this.mActiveId0;
                            int findPointerIndex2 = motionEvent.findPointerIndex(i7);
                            this.mFocusX = motionEvent.getX(findPointerIndex2);
                            this.mFocusY = motionEvent.getY(findPointerIndex2);
                            this.mListener.onScaleEnd(this);
                            reset();
                            this.mActiveId0 = i7;
                            this.mActive0MostRecent = true;
                            break;
                        }
                        break;
                }
            } else {
                switch (action) {
                    case 0:
                        this.mActiveId0 = motionEvent.getPointerId(0);
                        this.mActive0MostRecent = true;
                        break;
                    case 1:
                        reset();
                        break;
                    case 2:
                        if (this.mSloppyGesture) {
                            float f = this.mEdgeSlop;
                            float f2 = this.mRightSlopEdge;
                            float f3 = this.mBottomSlopEdge;
                            int findPointerIndex3 = motionEvent.findPointerIndex(this.mActiveId0);
                            int findPointerIndex4 = motionEvent.findPointerIndex(this.mActiveId1);
                            float rawX = getRawX(motionEvent, findPointerIndex3);
                            float rawY = getRawY(motionEvent, findPointerIndex3);
                            float rawX2 = getRawX(motionEvent, findPointerIndex4);
                            float rawY2 = getRawY(motionEvent, findPointerIndex4);
                            boolean z4 = rawX < f || rawY < f || rawX > f2 || rawY > f3;
                            boolean z5 = rawX2 < f || rawY2 < f || rawX2 > f2 || rawY2 > f3;
                            if (!z4 || (findNewActiveIndex2 = findNewActiveIndex(motionEvent, this.mActiveId1, findPointerIndex3)) < 0) {
                                z = z4;
                            } else {
                                this.mActiveId0 = motionEvent.getPointerId(findNewActiveIndex2);
                                getRawX(motionEvent, findNewActiveIndex2);
                                getRawY(motionEvent, findNewActiveIndex2);
                                findPointerIndex3 = findNewActiveIndex2;
                                z = false;
                            }
                            if (!z5 || (i = findNewActiveIndex(motionEvent, this.mActiveId0, findPointerIndex4)) < 0) {
                                i = findPointerIndex4;
                            } else {
                                this.mActiveId1 = motionEvent.getPointerId(i);
                                getRawX(motionEvent, i);
                                getRawY(motionEvent, i);
                                z5 = false;
                            }
                            if (!z || !z5) {
                                if (!z) {
                                    if (!z5) {
                                        this.mSloppyGesture = false;
                                        this.mGestureInProgress = this.mListener.onScaleBegin(this);
                                        break;
                                    } else {
                                        this.mFocusX = motionEvent.getX(findPointerIndex3);
                                        this.mFocusY = motionEvent.getY(findPointerIndex3);
                                        break;
                                    }
                                } else {
                                    this.mFocusX = motionEvent.getX(i);
                                    this.mFocusY = motionEvent.getY(i);
                                    break;
                                }
                            } else {
                                this.mFocusX = -1.0f;
                                this.mFocusY = -1.0f;
                                break;
                            }
                        }
                        break;
                    case 5:
                        DisplayMetrics displayMetrics = this.mContext.getResources().getDisplayMetrics();
                        this.mRightSlopEdge = displayMetrics.widthPixels - this.mEdgeSlop;
                        this.mBottomSlopEdge = displayMetrics.heightPixels - this.mEdgeSlop;
                        if (this.mPrevEvent != null) {
                            this.mPrevEvent.recycle();
                        }
                        this.mPrevEvent = MotionEvent.obtain(motionEvent);
                        this.mTimeDelta = 0L;
                        if (Build.VERSION.SDK_INT >= 8) {
                            int actionIndex2 = motionEvent.getActionIndex();
                            int findPointerIndex5 = motionEvent.findPointerIndex(this.mActiveId0);
                            this.mActiveId1 = motionEvent.getPointerId(actionIndex2);
                            if (findPointerIndex5 < 0 || findPointerIndex5 == actionIndex2) {
                                if (findPointerIndex5 != actionIndex2) {
                                    i4 = this.mActiveId1;
                                }
                                int findNewActiveIndex5 = findNewActiveIndex(motionEvent, i4, findPointerIndex5);
                                this.mActiveId0 = motionEvent.getPointerId(findNewActiveIndex5);
                                i3 = findNewActiveIndex5;
                                i2 = actionIndex2;
                            } else {
                                i2 = actionIndex2;
                                i3 = findPointerIndex5;
                            }
                        } else if (motionEvent.getPointerCount() > 0) {
                            i2 = motionEvent.findPointerIndex(1);
                            i3 = motionEvent.findPointerIndex(this.mActiveId0);
                            this.mActiveId1 = motionEvent.getPointerId(i2);
                        } else {
                            i2 = 0;
                            i3 = 0;
                        }
                        this.mActive0MostRecent = false;
                        setContext(motionEvent);
                        float f4 = this.mEdgeSlop;
                        float f5 = this.mRightSlopEdge;
                        float f6 = this.mBottomSlopEdge;
                        float rawX3 = getRawX(motionEvent, i3);
                        float rawY3 = getRawY(motionEvent, i3);
                        float rawX4 = getRawX(motionEvent, i2);
                        float rawY4 = getRawY(motionEvent, i2);
                        boolean z6 = rawX3 < f4 || rawY3 < f4 || rawX3 > f5 || rawY3 > f6;
                        boolean z7 = rawX4 < f4 || rawY4 < f4 || rawX4 > f5 || rawY4 > f6;
                        if (!z6 || !z7) {
                            if (!z6) {
                                if (!z7) {
                                    this.mSloppyGesture = false;
                                    this.mGestureInProgress = this.mListener.onScaleBegin(this);
                                    break;
                                } else {
                                    this.mFocusX = motionEvent.getX(i3);
                                    this.mFocusY = motionEvent.getY(i3);
                                    this.mSloppyGesture = true;
                                    break;
                                }
                            } else {
                                this.mFocusX = motionEvent.getX(i2);
                                this.mFocusY = motionEvent.getY(i2);
                                this.mSloppyGesture = true;
                                break;
                            }
                        } else {
                            this.mFocusX = -1.0f;
                            this.mFocusY = -1.0f;
                            this.mSloppyGesture = true;
                            break;
                        }
                        break;
                    case 6:
                        if (this.mSloppyGesture) {
                            int pointerCount2 = motionEvent.getPointerCount();
                            int actionIndex3 = Build.VERSION.SDK_INT >= 8 ? motionEvent.getActionIndex() : 0;
                            int pointerId2 = motionEvent.getPointerId(actionIndex3);
                            if (pointerCount2 > 2) {
                                if (pointerId2 != this.mActiveId0) {
                                    if (pointerId2 == this.mActiveId1 && (findNewActiveIndex = findNewActiveIndex(motionEvent, this.mActiveId0, actionIndex3)) >= 0) {
                                        this.mActiveId1 = motionEvent.getPointerId(findNewActiveIndex);
                                        break;
                                    }
                                } else {
                                    int findNewActiveIndex6 = findNewActiveIndex(motionEvent, this.mActiveId1, actionIndex3);
                                    if (findNewActiveIndex6 >= 0) {
                                        this.mActiveId0 = motionEvent.getPointerId(findNewActiveIndex6);
                                        break;
                                    }
                                }
                            } else {
                                int findPointerIndex6 = motionEvent.findPointerIndex(pointerId2 == this.mActiveId0 ? this.mActiveId1 : this.mActiveId0);
                                if (findPointerIndex6 >= 0) {
                                    this.mActiveId0 = motionEvent.getPointerId(findPointerIndex6);
                                    this.mActive0MostRecent = true;
                                    this.mActiveId1 = -1;
                                    this.mFocusX = motionEvent.getX(findPointerIndex6);
                                    this.mFocusY = motionEvent.getY(findPointerIndex6);
                                    break;
                                } else {
                                    this.mInvalidGesture = true;
                                    if (!this.mGestureInProgress) {
                                        return false;
                                    }
                                    this.mListener.onScaleEnd(this);
                                    return false;
                                }
                            }
                        }
                        break;
                }
            }
        } else {
            z2 = false;
        }
        return z2;
    }

    private int findNewActiveIndex(MotionEvent motionEvent, int i, int i2) {
        int pointerCount = motionEvent.getPointerCount();
        int findPointerIndex = motionEvent.findPointerIndex(i);
        for (int i3 = 0; i3 < pointerCount; i3++) {
            if (!(i3 == i2 || i3 == findPointerIndex)) {
                float f = this.mEdgeSlop;
                float f2 = this.mRightSlopEdge;
                float f3 = this.mBottomSlopEdge;
                float rawX = getRawX(motionEvent, i3);
                float rawY = getRawY(motionEvent, i3);
                if (rawX >= f && rawY >= f && rawX <= f2 && rawY <= f3) {
                    return i3;
                }
            }
        }
        return -1;
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

    private void setContext(MotionEvent motionEvent) {
        if (this.mCurrEvent != null) {
            this.mCurrEvent.recycle();
        }
        this.mCurrEvent = MotionEvent.obtain(motionEvent);
        this.mCurrLen = -1.0f;
        this.mPrevLen = -1.0f;
        this.mScaleFactor = -1.0f;
        MotionEvent motionEvent2 = this.mPrevEvent;
        int findPointerIndex = motionEvent2.findPointerIndex(this.mActiveId0);
        int findPointerIndex2 = motionEvent2.findPointerIndex(this.mActiveId1);
        int findPointerIndex3 = motionEvent.findPointerIndex(this.mActiveId0);
        int findPointerIndex4 = motionEvent.findPointerIndex(this.mActiveId1);
        if (findPointerIndex < 0 || findPointerIndex2 < 0 || findPointerIndex3 < 0 || findPointerIndex4 < 0) {
            this.mInvalidGesture = true;
            if (this.mGestureInProgress) {
                this.mListener.onScaleEnd(this);
                return;
            }
            return;
        }
        float x = motionEvent2.getX(findPointerIndex);
        float y = motionEvent2.getY(findPointerIndex);
        float x2 = motionEvent2.getX(findPointerIndex2);
        float y2 = motionEvent2.getY(findPointerIndex2);
        float x3 = motionEvent.getX(findPointerIndex3);
        float y3 = motionEvent.getY(findPointerIndex3);
        float f = x2 - x;
        float f2 = y2 - y;
        float x4 = motionEvent.getX(findPointerIndex4) - x3;
        float y4 = motionEvent.getY(findPointerIndex4) - y3;
        this.mPrevFingerDiffX = f;
        this.mPrevFingerDiffY = f2;
        this.mCurrFingerDiffX = x4;
        this.mCurrFingerDiffY = y4;
        this.mFocusX = (x4 * 0.5f) + x3;
        this.mFocusY = (y4 * 0.5f) + y3;
        this.mTimeDelta = motionEvent.getEventTime() - motionEvent2.getEventTime();
        this.mCurrPressure = motionEvent.getPressure(findPointerIndex3) + motionEvent.getPressure(findPointerIndex4);
        this.mPrevPressure = motionEvent2.getPressure(findPointerIndex2) + motionEvent2.getPressure(findPointerIndex);
    }

    private void reset() {
        if (this.mPrevEvent != null) {
            this.mPrevEvent.recycle();
            this.mPrevEvent = null;
        }
        if (this.mCurrEvent != null) {
            this.mCurrEvent.recycle();
            this.mCurrEvent = null;
        }
        this.mSloppyGesture = false;
        this.mGestureInProgress = false;
        this.mActiveId0 = -1;
        this.mActiveId1 = -1;
        this.mInvalidGesture = false;
    }

    public boolean isInProgress() {
        return this.mGestureInProgress;
    }

    public float getFocusX() {
        return this.mFocusX;
    }

    public float getFocusY() {
        return this.mFocusY;
    }

    public float getCurrentSpan() {
        if (this.mCurrLen == -1.0f) {
            float f = this.mCurrFingerDiffX;
            float f2 = this.mCurrFingerDiffY;
            this.mCurrLen = (float) Math.sqrt((f * f) + (f2 * f2));
        }
        return this.mCurrLen;
    }

    public float getCurrentSpanX() {
        return this.mCurrFingerDiffX;
    }

    public float getCurrentSpanY() {
        return this.mCurrFingerDiffY;
    }

    public float getPreviousSpan() {
        if (this.mPrevLen == -1.0f) {
            float f = this.mPrevFingerDiffX;
            float f2 = this.mPrevFingerDiffY;
            this.mPrevLen = (float) Math.sqrt((f * f) + (f2 * f2));
        }
        return this.mPrevLen;
    }

    public float getPreviousSpanX() {
        return this.mPrevFingerDiffX;
    }

    public float getPreviousSpanY() {
        return this.mPrevFingerDiffY;
    }

    public float getScaleFactor() {
        if (this.mScaleFactor == -1.0f) {
            this.mScaleFactor = getCurrentSpan() / getPreviousSpan();
        }
        return this.mScaleFactor;
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
}
