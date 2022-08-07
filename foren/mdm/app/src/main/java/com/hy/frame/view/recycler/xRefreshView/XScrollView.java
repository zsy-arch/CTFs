package com.hy.frame.view.recycler.xRefreshView;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewConfiguration;
import android.widget.ScrollView;
import com.hy.frame.view.recycler.xRefreshView.XRefreshView;

/* loaded from: classes2.dex */
public class XScrollView extends ScrollView {
    private boolean inTouch;
    private int lastT;
    private float lastY;
    private XRefreshView mParent;
    private Runnable mRunnable;
    private OnScrollListener mScrollListener;
    private int mTouchSlop;
    private OnScrollListener onScrollListener;

    /* loaded from: classes2.dex */
    public interface OnScrollListener {
        public static final int SCROLL_STATE_FLING = 2;
        public static final int SCROLL_STATE_IDLE = 0;
        public static final int SCROLL_STATE_TOUCH_SCROLL = 1;

        void onScroll(int i, int i2, int i3, int i4);

        void onScrollStateChanged(ScrollView scrollView, int i, boolean z);
    }

    public XScrollView(Context context) {
        super(context, null);
        this.inTouch = false;
        this.lastT = 0;
        this.mRunnable = new Runnable() { // from class: com.hy.frame.view.recycler.xRefreshView.XScrollView.1
            @Override // java.lang.Runnable
            public void run() {
                if (XScrollView.this.lastT == XScrollView.this.getScrollY() && !XScrollView.this.inTouch) {
                    XScrollView.this.onScrollListener.onScrollStateChanged(XScrollView.this, 0, XScrollView.this.isBottom());
                    if (XScrollView.this.mScrollListener != null) {
                        XScrollView.this.mScrollListener.onScrollStateChanged(XScrollView.this, 0, XScrollView.this.isBottom());
                    }
                }
            }
        };
    }

    public XScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.inTouch = false;
        this.lastT = 0;
        this.mRunnable = new Runnable() { // from class: com.hy.frame.view.recycler.xRefreshView.XScrollView.1
            @Override // java.lang.Runnable
            public void run() {
                if (XScrollView.this.lastT == XScrollView.this.getScrollY() && !XScrollView.this.inTouch) {
                    XScrollView.this.onScrollListener.onScrollStateChanged(XScrollView.this, 0, XScrollView.this.isBottom());
                    if (XScrollView.this.mScrollListener != null) {
                        XScrollView.this.mScrollListener.onScrollStateChanged(XScrollView.this, 0, XScrollView.this.isBottom());
                    }
                }
            }
        };
        this.mTouchSlop = ViewConfiguration.get(getContext()).getScaledTouchSlop();
    }

    @Override // android.view.View
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        if (this.onScrollListener != null) {
            if (this.inTouch) {
                if (t != oldt) {
                    this.onScrollListener.onScrollStateChanged(this, 1, isBottom());
                    if (this.mScrollListener != null) {
                        this.mScrollListener.onScrollStateChanged(this, 1, isBottom());
                    }
                }
            } else if (t != oldt) {
                this.onScrollListener.onScrollStateChanged(this, 2, isBottom());
                if (this.mScrollListener != null) {
                    this.mScrollListener.onScrollStateChanged(this, 2, isBottom());
                }
                this.lastT = t;
                removeCallbacks(this.mRunnable);
                postDelayed(this.mRunnable, 20L);
            }
            this.onScrollListener.onScroll(l, t, oldl, oldt);
            if (this.mScrollListener != null) {
                this.mScrollListener.onScroll(l, t, oldl, oldt);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isBottom() {
        return getScrollY() + getHeight() >= computeVerticalScrollRange();
    }

    public void setOnScrollListener(XRefreshView parent, OnScrollListener scrollListener) {
        this.mParent = parent;
        this.onScrollListener = scrollListener;
        this.mParent.addTouchLifeCycle(new XRefreshView.TouchLifeCycle() { // from class: com.hy.frame.view.recycler.xRefreshView.XScrollView.2
            /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
            @Override // com.hy.frame.view.recycler.xRefreshView.XRefreshView.TouchLifeCycle
            public void onTouch(MotionEvent event) {
                switch (event.getAction()) {
                    case 0:
                        XScrollView.this.lastY = event.getRawY();
                        break;
                    case 1:
                    case 3:
                        XScrollView.this.inTouch = false;
                        XScrollView.this.lastT = XScrollView.this.getScrollY();
                        if (XScrollView.this.lastY - event.getRawY() >= XScrollView.this.mTouchSlop) {
                            XScrollView.this.removeCallbacks(XScrollView.this.mRunnable);
                            XScrollView.this.postDelayed(XScrollView.this.mRunnable, 20L);
                            return;
                        }
                        return;
                    case 2:
                        break;
                    default:
                        return;
                }
                XScrollView.this.inTouch = true;
            }
        });
    }

    public void setOnScrollListener(OnScrollListener scrollListener) {
        this.mScrollListener = scrollListener;
    }
}
