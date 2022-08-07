package com.hy.frame.view.swipe;

import android.content.Context;
import android.support.v4.view.MotionEventCompat;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Interpolator;
import android.widget.ListAdapter;
import android.widget.ListView;

/* loaded from: classes2.dex */
public class DeletItemListView extends ListView {
    private static final int TOUCH_STATE_NONE = 0;
    private static final int TOUCH_STATE_X = 1;
    private static final int TOUCH_STATE_Y = 2;
    private Interpolator mCloseInterpolator;
    private float mDownX;
    private float mDownY;
    private SwipeMenuCreator mMenuCreator;
    private OnMenuItemClickListener mOnMenuItemClickListener;
    private OnSwipeListener mOnSwipeListener;
    private Interpolator mOpenInterpolator;
    private int mTouchPosition;
    private int mTouchState;
    private SwipeMenuLayout mTouchView;
    private int MAX_Y = 5;
    private int MAX_X = 3;

    /* loaded from: classes2.dex */
    public interface OnMenuItemClickListener {
        void onMenuItemClick(int i, SwipeMenu swipeMenu, int i2);
    }

    /* loaded from: classes2.dex */
    public interface OnSwipeListener {
        void onSwipeEnd(int i);

        void onSwipeStart(int i);
    }

    public DeletItemListView(Context context) {
        super(context);
        init();
    }

    public DeletItemListView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public DeletItemListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        this.MAX_X = dp2px(this.MAX_X);
        this.MAX_Y = dp2px(this.MAX_Y);
        this.mTouchState = 0;
    }

    @Override // android.widget.ListView, android.widget.AbsListView
    public void setAdapter(ListAdapter adapter) {
        if (adapter != null) {
            super.setAdapter((ListAdapter) new SwipeMenuAdapter(getContext(), adapter) { // from class: com.hy.frame.view.swipe.DeletItemListView.1
                @Override // com.hy.frame.view.swipe.SwipeMenuAdapter
                public void createMenu(SwipeMenu menu) {
                    if (DeletItemListView.this.mMenuCreator != null) {
                        DeletItemListView.this.mMenuCreator.create(menu);
                    }
                }

                @Override // com.hy.frame.view.swipe.SwipeMenuAdapter, com.hy.frame.view.swipe.SwipeMenuView.OnSwipeItemClickListener
                public void onItemClick(SwipeMenuView view, SwipeMenu menu, int index) {
                    if (DeletItemListView.this.mOnMenuItemClickListener != null) {
                        DeletItemListView.this.mOnMenuItemClickListener.onMenuItemClick(view.getPosition(), menu, index);
                    }
                    if (DeletItemListView.this.mTouchView != null) {
                        DeletItemListView.this.mTouchView.smoothCloseMenu();
                    }
                }
            });
        }
    }

    public void setCloseInterpolator(Interpolator interpolator) {
        this.mCloseInterpolator = interpolator;
    }

    public void setOpenInterpolator(Interpolator interpolator) {
        this.mOpenInterpolator = interpolator;
    }

    public Interpolator getOpenInterpolator() {
        return this.mOpenInterpolator;
    }

    public Interpolator getCloseInterpolator() {
        return this.mCloseInterpolator;
    }

    @Override // android.widget.AbsListView, android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return super.onInterceptTouchEvent(ev);
    }

    @Override // android.widget.AbsListView, android.view.View
    public boolean onTouchEvent(MotionEvent ev) {
        if (ev.getAction() != 0 && this.mTouchView == null) {
            return super.onTouchEvent(ev);
        }
        MotionEventCompat.getActionMasked(ev);
        switch (ev.getAction()) {
            case 0:
                int oldPos = this.mTouchPosition;
                this.mDownX = ev.getX();
                this.mDownY = ev.getY();
                this.mTouchState = 0;
                this.mTouchPosition = pointToPosition((int) ev.getX(), (int) ev.getY());
                if (this.mTouchPosition != oldPos || this.mTouchView == null || !this.mTouchView.isOpen()) {
                    View view = getChildAt(this.mTouchPosition - getFirstVisiblePosition());
                    if (this.mTouchView == null || !this.mTouchView.isOpen()) {
                        if (view instanceof SwipeMenuLayout) {
                            this.mTouchView = (SwipeMenuLayout) view;
                        }
                        if (this.mTouchView != null) {
                            this.mTouchView.onSwipe(ev);
                            break;
                        }
                    } else {
                        this.mTouchView.smoothCloseMenu();
                        this.mTouchView = null;
                        return super.onTouchEvent(ev);
                    }
                } else {
                    this.mTouchState = 1;
                    this.mTouchView.onSwipe(ev);
                    return true;
                }
                break;
            case 1:
                if (this.mTouchState == 1) {
                    if (this.mTouchView != null) {
                        this.mTouchView.onSwipe(ev);
                        if (!this.mTouchView.isOpen()) {
                            this.mTouchPosition = -1;
                            this.mTouchView = null;
                        }
                    }
                    if (this.mOnSwipeListener != null) {
                        this.mOnSwipeListener.onSwipeEnd(this.mTouchPosition);
                    }
                    ev.setAction(3);
                    super.onTouchEvent(ev);
                    return true;
                }
                break;
            case 2:
                float dy = Math.abs(ev.getY() - this.mDownY);
                float dx = Math.abs(ev.getX() - this.mDownX);
                if (this.mTouchState != 1) {
                    if (this.mTouchState == 0) {
                        if (Math.abs(dy) <= this.MAX_Y) {
                            if (dx > this.MAX_X) {
                                this.mTouchState = 1;
                                if (this.mOnSwipeListener != null) {
                                    this.mOnSwipeListener.onSwipeStart(this.mTouchPosition);
                                    break;
                                }
                            }
                        } else {
                            this.mTouchState = 2;
                            break;
                        }
                    }
                } else {
                    if (this.mTouchView != null) {
                        this.mTouchView.onSwipe(ev);
                    }
                    getSelector().setState(new int[]{0});
                    ev.setAction(3);
                    super.onTouchEvent(ev);
                    return true;
                }
                break;
        }
        return super.onTouchEvent(ev);
    }

    public void smoothOpenMenu(int position) {
        if (position >= getFirstVisiblePosition() && position <= getLastVisiblePosition()) {
            View view = getChildAt(position - getFirstVisiblePosition());
            if (view instanceof SwipeMenuLayout) {
                this.mTouchPosition = position;
                if (this.mTouchView != null && this.mTouchView.isOpen()) {
                    this.mTouchView.smoothCloseMenu();
                }
                this.mTouchView = (SwipeMenuLayout) view;
                this.mTouchView.smoothOpenMenu();
            }
        }
    }

    private int dp2px(int dp) {
        return (int) TypedValue.applyDimension(1, dp, getContext().getResources().getDisplayMetrics());
    }

    public void setMenuCreator(SwipeMenuCreator menuCreator) {
        this.mMenuCreator = menuCreator;
    }

    public void setOnMenuItemClickListener(OnMenuItemClickListener onMenuItemClickListener) {
        this.mOnMenuItemClickListener = onMenuItemClickListener;
    }

    public void setOnSwipeListener(OnSwipeListener onSwipeListener) {
        this.mOnSwipeListener = onSwipeListener;
    }
}
