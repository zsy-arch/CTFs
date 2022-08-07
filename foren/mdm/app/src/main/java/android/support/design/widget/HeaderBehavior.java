package android.support.design.widget;

import android.content.Context;
import android.support.v4.math.MathUtils;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.OverScroller;

/* loaded from: classes.dex */
abstract class HeaderBehavior<V extends View> extends ViewOffsetBehavior<V> {
    private static final int INVALID_POINTER = -1;
    private Runnable mFlingRunnable;
    private boolean mIsBeingDragged;
    private int mLastMotionY;
    OverScroller mScroller;
    private VelocityTracker mVelocityTracker;
    private int mActivePointerId = -1;
    private int mTouchSlop = -1;

    public HeaderBehavior() {
    }

    public HeaderBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override // android.support.design.widget.CoordinatorLayout.Behavior
    public boolean onInterceptTouchEvent(CoordinatorLayout parent, V child, MotionEvent ev) {
        int pointerIndex;
        if (this.mTouchSlop < 0) {
            this.mTouchSlop = ViewConfiguration.get(parent.getContext()).getScaledTouchSlop();
        }
        if (ev.getAction() == 2 && this.mIsBeingDragged) {
            return true;
        }
        switch (ev.getActionMasked()) {
            case 0:
                this.mIsBeingDragged = false;
                int x = (int) ev.getX();
                int y = (int) ev.getY();
                if (canDragView(child) && parent.isPointInChildBounds(child, x, y)) {
                    this.mLastMotionY = y;
                    this.mActivePointerId = ev.getPointerId(0);
                    ensureVelocityTracker();
                    break;
                }
                break;
            case 1:
            case 3:
                this.mIsBeingDragged = false;
                this.mActivePointerId = -1;
                if (this.mVelocityTracker != null) {
                    this.mVelocityTracker.recycle();
                    this.mVelocityTracker = null;
                    break;
                }
                break;
            case 2:
                int activePointerId = this.mActivePointerId;
                if (!(activePointerId == -1 || (pointerIndex = ev.findPointerIndex(activePointerId)) == -1)) {
                    int y2 = (int) ev.getY(pointerIndex);
                    if (Math.abs(y2 - this.mLastMotionY) > this.mTouchSlop) {
                        this.mIsBeingDragged = true;
                        this.mLastMotionY = y2;
                        break;
                    }
                }
                break;
        }
        if (this.mVelocityTracker != null) {
            this.mVelocityTracker.addMovement(ev);
        }
        return this.mIsBeingDragged;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Removed duplicated region for block: B:36:0x00c9  */
    @Override // android.support.design.widget.CoordinatorLayout.Behavior
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean onTouchEvent(android.support.design.widget.CoordinatorLayout r15, V r16, android.view.MotionEvent r17) {
        /*
            r14 = this;
            int r1 = r14.mTouchSlop
            if (r1 >= 0) goto L_0x0012
            android.content.Context r1 = r15.getContext()
            android.view.ViewConfiguration r1 = android.view.ViewConfiguration.get(r1)
            int r1 = r1.getScaledTouchSlop()
            r14.mTouchSlop = r1
        L_0x0012:
            int r1 = r17.getActionMasked()
            switch(r1) {
                case 0: goto L_0x0026;
                case 1: goto L_0x0096;
                case 2: goto L_0x0051;
                case 3: goto L_0x00bf;
                default: goto L_0x0019;
            }
        L_0x0019:
            android.view.VelocityTracker r1 = r14.mVelocityTracker
            if (r1 == 0) goto L_0x0024
            android.view.VelocityTracker r1 = r14.mVelocityTracker
            r0 = r17
            r1.addMovement(r0)
        L_0x0024:
            r1 = 1
        L_0x0025:
            return r1
        L_0x0026:
            float r1 = r17.getX()
            int r12 = (int) r1
            float r1 = r17.getY()
            int r13 = (int) r1
            r0 = r16
            boolean r1 = r15.isPointInChildBounds(r0, r12, r13)
            if (r1 == 0) goto L_0x004f
            r0 = r16
            boolean r1 = r14.canDragView(r0)
            if (r1 == 0) goto L_0x004f
            r14.mLastMotionY = r13
            r1 = 0
            r0 = r17
            int r1 = r0.getPointerId(r1)
            r14.mActivePointerId = r1
            r14.ensureVelocityTracker()
            goto L_0x0019
        L_0x004f:
            r1 = 0
            goto L_0x0025
        L_0x0051:
            int r1 = r14.mActivePointerId
            r0 = r17
            int r11 = r0.findPointerIndex(r1)
            r1 = -1
            if (r11 != r1) goto L_0x005e
            r1 = 0
            goto L_0x0025
        L_0x005e:
            r0 = r17
            float r1 = r0.getY(r11)
            int r13 = (int) r1
            int r1 = r14.mLastMotionY
            int r4 = r1 - r13
            boolean r1 = r14.mIsBeingDragged
            if (r1 != 0) goto L_0x007d
            int r1 = java.lang.Math.abs(r4)
            int r2 = r14.mTouchSlop
            if (r1 <= r2) goto L_0x007d
            r1 = 1
            r14.mIsBeingDragged = r1
            if (r4 <= 0) goto L_0x0092
            int r1 = r14.mTouchSlop
            int r4 = r4 - r1
        L_0x007d:
            boolean r1 = r14.mIsBeingDragged
            if (r1 == 0) goto L_0x0019
            r14.mLastMotionY = r13
            r0 = r16
            int r5 = r14.getMaxDragOffset(r0)
            r6 = 0
            r1 = r14
            r2 = r15
            r3 = r16
            r1.scroll(r2, r3, r4, r5, r6)
            goto L_0x0019
        L_0x0092:
            int r1 = r14.mTouchSlop
            int r4 = r4 + r1
            goto L_0x007d
        L_0x0096:
            android.view.VelocityTracker r1 = r14.mVelocityTracker
            if (r1 == 0) goto L_0x00bf
            android.view.VelocityTracker r1 = r14.mVelocityTracker
            r0 = r17
            r1.addMovement(r0)
            android.view.VelocityTracker r1 = r14.mVelocityTracker
            r2 = 1000(0x3e8, float:1.401E-42)
            r1.computeCurrentVelocity(r2)
            android.view.VelocityTracker r1 = r14.mVelocityTracker
            int r2 = r14.mActivePointerId
            float r10 = r1.getYVelocity(r2)
            r0 = r16
            int r1 = r14.getScrollRangeForDragFling(r0)
            int r8 = -r1
            r9 = 0
            r5 = r14
            r6 = r15
            r7 = r16
            r5.fling(r6, r7, r8, r9, r10)
        L_0x00bf:
            r1 = 0
            r14.mIsBeingDragged = r1
            r1 = -1
            r14.mActivePointerId = r1
            android.view.VelocityTracker r1 = r14.mVelocityTracker
            if (r1 == 0) goto L_0x0019
            android.view.VelocityTracker r1 = r14.mVelocityTracker
            r1.recycle()
            r1 = 0
            r14.mVelocityTracker = r1
            goto L_0x0019
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.design.widget.HeaderBehavior.onTouchEvent(android.support.design.widget.CoordinatorLayout, android.view.View, android.view.MotionEvent):boolean");
    }

    int setHeaderTopBottomOffset(CoordinatorLayout parent, V header, int newOffset) {
        return setHeaderTopBottomOffset(parent, header, newOffset, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    int setHeaderTopBottomOffset(CoordinatorLayout parent, V header, int newOffset, int minOffset, int maxOffset) {
        int newOffset2;
        int curOffset = getTopAndBottomOffset();
        if (minOffset == 0 || curOffset < minOffset || curOffset > maxOffset || curOffset == (newOffset2 = MathUtils.clamp(newOffset, minOffset, maxOffset))) {
            return 0;
        }
        setTopAndBottomOffset(newOffset2);
        return curOffset - newOffset2;
    }

    int getTopBottomOffsetForScrollingSibling() {
        return getTopAndBottomOffset();
    }

    final int scroll(CoordinatorLayout coordinatorLayout, V header, int dy, int minOffset, int maxOffset) {
        return setHeaderTopBottomOffset(coordinatorLayout, header, getTopBottomOffsetForScrollingSibling() - dy, minOffset, maxOffset);
    }

    final boolean fling(CoordinatorLayout coordinatorLayout, V layout, int minOffset, int maxOffset, float velocityY) {
        if (this.mFlingRunnable != null) {
            layout.removeCallbacks(this.mFlingRunnable);
            this.mFlingRunnable = null;
        }
        if (this.mScroller == null) {
            this.mScroller = new OverScroller(layout.getContext());
        }
        this.mScroller.fling(0, getTopAndBottomOffset(), 0, Math.round(velocityY), 0, 0, minOffset, maxOffset);
        if (this.mScroller.computeScrollOffset()) {
            this.mFlingRunnable = new FlingRunnable(coordinatorLayout, layout);
            ViewCompat.postOnAnimation(layout, this.mFlingRunnable);
            return true;
        }
        onFlingFinished(coordinatorLayout, layout);
        return false;
    }

    void onFlingFinished(CoordinatorLayout parent, V layout) {
    }

    boolean canDragView(V view) {
        return false;
    }

    int getMaxDragOffset(V view) {
        return -view.getHeight();
    }

    int getScrollRangeForDragFling(V view) {
        return view.getHeight();
    }

    private void ensureVelocityTracker() {
        if (this.mVelocityTracker == null) {
            this.mVelocityTracker = VelocityTracker.obtain();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Incorrect field signature: TV; */
    /* loaded from: classes.dex */
    public class FlingRunnable implements Runnable {
        private final View mLayout;
        private final CoordinatorLayout mParent;

        FlingRunnable(CoordinatorLayout parent, V layout) {
            this.mParent = parent;
            this.mLayout = layout;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // java.lang.Runnable
        public void run() {
            if (this.mLayout != null && HeaderBehavior.this.mScroller != null) {
                if (HeaderBehavior.this.mScroller.computeScrollOffset()) {
                    HeaderBehavior.this.setHeaderTopBottomOffset(this.mParent, this.mLayout, HeaderBehavior.this.mScroller.getCurrY());
                    ViewCompat.postOnAnimation(this.mLayout, this);
                    return;
                }
                HeaderBehavior.this.onFlingFinished(this.mParent, this.mLayout);
            }
        }
    }
}
