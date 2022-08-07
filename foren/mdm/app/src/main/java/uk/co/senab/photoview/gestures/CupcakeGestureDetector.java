package uk.co.senab.photoview.gestures;

import android.content.Context;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.ViewConfiguration;
import com.hy.frame.util.MyLog;

/* loaded from: classes2.dex */
public class CupcakeGestureDetector implements GestureDetector {
    private static final String LOG_TAG = "CupcakeGestureDetector";
    private boolean mIsDragging;
    float mLastTouchX;
    float mLastTouchY;
    protected OnGestureListener mListener;
    final float mMinimumVelocity;
    final float mTouchSlop;
    private VelocityTracker mVelocityTracker;

    @Override // uk.co.senab.photoview.gestures.GestureDetector
    public void setOnGestureListener(OnGestureListener listener) {
        this.mListener = listener;
    }

    public CupcakeGestureDetector(Context context) {
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

    @Override // uk.co.senab.photoview.gestures.GestureDetector
    public boolean isScaling() {
        return false;
    }

    @Override // uk.co.senab.photoview.gestures.GestureDetector
    public boolean isDragging() {
        return this.mIsDragging;
    }

    @Override // uk.co.senab.photoview.gestures.GestureDetector
    public boolean onTouchEvent(MotionEvent ev) {
        boolean z = false;
        switch (ev.getAction()) {
            case 0:
                this.mVelocityTracker = VelocityTracker.obtain();
                if (this.mVelocityTracker != null) {
                    this.mVelocityTracker.addMovement(ev);
                } else {
                    MyLog.i(LOG_TAG, "Velocity tracker is null");
                }
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
