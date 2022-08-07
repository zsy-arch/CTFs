package com.cdlinglu.utils.Photo.zoom;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.support.v4.view.MotionEventCompat;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.VelocityTracker;
import android.view.ViewConfiguration;

/* loaded from: classes.dex */
public abstract class VersionedGestureDetector {
    static final String LOG_TAG = "VersionedGestureDetector";
    OnGestureListener mListener;

    /* loaded from: classes.dex */
    public interface OnGestureListener {
        void onDrag(float f, float f2);

        void onFling(float f, float f2, float f3, float f4);

        void onScale(float f, float f2, float f3);
    }

    public abstract boolean isScaling();

    public abstract boolean onTouchEvent(MotionEvent motionEvent);

    public static VersionedGestureDetector newInstance(Context context, OnGestureListener listener) {
        VersionedGestureDetector detector;
        int sdkVersion = Build.VERSION.SDK_INT;
        if (sdkVersion < 5) {
            detector = new CupcakeDetector(context);
        } else if (sdkVersion < 8) {
            detector = new EclairDetector(context);
        } else {
            detector = new FroyoDetector(context);
        }
        detector.mListener = listener;
        return detector;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class CupcakeDetector extends VersionedGestureDetector {
        private boolean mIsDragging;
        float mLastTouchX;
        float mLastTouchY;
        final float mMinimumVelocity;
        final float mTouchSlop;
        private VelocityTracker mVelocityTracker;

        public CupcakeDetector(Context context) {
            ViewConfiguration configuration = ViewConfiguration.get(context);
            this.mMinimumVelocity = configuration.getScaledMinimumFlingVelocity();
            this.mTouchSlop = configuration.getScaledTouchSlop();
        }

        float getActiveX(MotionEvent ev) {
            return ev.getX();
        }

        float getActiveY(MotionEvent ev) {
            return ev.getY();
        }

        @Override // com.cdlinglu.utils.Photo.zoom.VersionedGestureDetector
        public boolean isScaling() {
            return false;
        }

        @Override // com.cdlinglu.utils.Photo.zoom.VersionedGestureDetector
        public boolean onTouchEvent(MotionEvent ev) {
            boolean z = false;
            switch (ev.getAction()) {
                case 0:
                    this.mVelocityTracker = VelocityTracker.obtain();
                    this.mVelocityTracker.addMovement(ev);
                    this.mLastTouchX = getActiveX(ev);
                    this.mLastTouchY = getActiveY(ev);
                    this.mIsDragging = false;
                    break;
                case 1:
                    if (this.mIsDragging && this.mVelocityTracker != null) {
                        this.mLastTouchX = getActiveX(ev);
                        this.mLastTouchY = getActiveY(ev);
                        this.mVelocityTracker.addMovement(ev);
                        this.mVelocityTracker.computeCurrentVelocity(1000);
                        float vX = this.mVelocityTracker.getXVelocity();
                        float vY = this.mVelocityTracker.getYVelocity();
                        if (Math.max(Math.abs(vX), Math.abs(vY)) >= this.mMinimumVelocity) {
                            this.mListener.onFling(this.mLastTouchX, this.mLastTouchY, -vX, -vY);
                        }
                    }
                    if (this.mVelocityTracker != null) {
                        this.mVelocityTracker.recycle();
                        this.mVelocityTracker = null;
                        break;
                    }
                    break;
                case 2:
                    float x = getActiveX(ev);
                    float y = getActiveY(ev);
                    float dx = x - this.mLastTouchX;
                    float dy = y - this.mLastTouchY;
                    if (!this.mIsDragging) {
                        if (Math.sqrt((dx * dx) + (dy * dy)) >= this.mTouchSlop) {
                            z = true;
                        }
                        this.mIsDragging = z;
                    }
                    if (this.mIsDragging) {
                        this.mListener.onDrag(dx, dy);
                        this.mLastTouchX = x;
                        this.mLastTouchY = y;
                        if (this.mVelocityTracker != null) {
                            this.mVelocityTracker.addMovement(ev);
                            break;
                        }
                    }
                    break;
                case 3:
                    if (this.mVelocityTracker != null) {
                        this.mVelocityTracker.recycle();
                        this.mVelocityTracker = null;
                        break;
                    }
                    break;
            }
            return true;
        }
    }

    @TargetApi(5)
    /* loaded from: classes.dex */
    private static class EclairDetector extends CupcakeDetector {
        private static final int INVALID_POINTER_ID = -1;
        private int mActivePointerId = -1;
        private int mActivePointerIndex = 0;

        public EclairDetector(Context context) {
            super(context);
        }

        @Override // com.cdlinglu.utils.Photo.zoom.VersionedGestureDetector.CupcakeDetector
        float getActiveX(MotionEvent ev) {
            try {
                return ev.getX(this.mActivePointerIndex);
            } catch (Exception e) {
                return ev.getX();
            }
        }

        @Override // com.cdlinglu.utils.Photo.zoom.VersionedGestureDetector.CupcakeDetector
        float getActiveY(MotionEvent ev) {
            try {
                return ev.getY(this.mActivePointerIndex);
            } catch (Exception e) {
                return ev.getY();
            }
        }

        @Override // com.cdlinglu.utils.Photo.zoom.VersionedGestureDetector.CupcakeDetector, com.cdlinglu.utils.Photo.zoom.VersionedGestureDetector
        public boolean onTouchEvent(MotionEvent ev) {
            int i = 0;
            switch (ev.getAction() & 255) {
                case 0:
                    this.mActivePointerId = ev.getPointerId(0);
                    break;
                case 1:
                case 3:
                    this.mActivePointerId = -1;
                    break;
                case 6:
                    int pointerIndex = (ev.getAction() & MotionEventCompat.ACTION_POINTER_INDEX_MASK) >> 8;
                    if (ev.getPointerId(pointerIndex) == this.mActivePointerId) {
                        int newPointerIndex = pointerIndex == 0 ? 1 : 0;
                        this.mActivePointerId = ev.getPointerId(newPointerIndex);
                        this.mLastTouchX = ev.getX(newPointerIndex);
                        this.mLastTouchY = ev.getY(newPointerIndex);
                        break;
                    }
                    break;
            }
            if (this.mActivePointerId != -1) {
                i = this.mActivePointerId;
            }
            this.mActivePointerIndex = ev.findPointerIndex(i);
            return super.onTouchEvent(ev);
        }
    }

    @TargetApi(8)
    /* loaded from: classes.dex */
    private static class FroyoDetector extends EclairDetector {
        private final ScaleGestureDetector mDetector;
        private final ScaleGestureDetector.OnScaleGestureListener mScaleListener = new ScaleGestureDetector.OnScaleGestureListener() { // from class: com.cdlinglu.utils.Photo.zoom.VersionedGestureDetector.FroyoDetector.1
            @Override // android.view.ScaleGestureDetector.OnScaleGestureListener
            public boolean onScale(ScaleGestureDetector detector) {
                FroyoDetector.this.mListener.onScale(detector.getScaleFactor(), detector.getFocusX(), detector.getFocusY());
                return true;
            }

            @Override // android.view.ScaleGestureDetector.OnScaleGestureListener
            public boolean onScaleBegin(ScaleGestureDetector detector) {
                return true;
            }

            @Override // android.view.ScaleGestureDetector.OnScaleGestureListener
            public void onScaleEnd(ScaleGestureDetector detector) {
            }
        };

        public FroyoDetector(Context context) {
            super(context);
            this.mDetector = new ScaleGestureDetector(context, this.mScaleListener);
        }

        @Override // com.cdlinglu.utils.Photo.zoom.VersionedGestureDetector.CupcakeDetector, com.cdlinglu.utils.Photo.zoom.VersionedGestureDetector
        public boolean isScaling() {
            return this.mDetector.isInProgress();
        }

        @Override // com.cdlinglu.utils.Photo.zoom.VersionedGestureDetector.EclairDetector, com.cdlinglu.utils.Photo.zoom.VersionedGestureDetector.CupcakeDetector, com.cdlinglu.utils.Photo.zoom.VersionedGestureDetector
        public boolean onTouchEvent(MotionEvent ev) {
            this.mDetector.onTouchEvent(ev);
            return super.onTouchEvent(ev);
        }
    }
}
