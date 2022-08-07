package com.hy.frame.view.recycler.xRefreshView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.support.annotation.LayoutRes;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.animation.LinearInterpolator;
import android.widget.AbsListView;
import android.widget.LinearLayout;
import android.widget.Scroller;
import com.alimama.mobile.csdk.umupdate.a.f;
import com.hy.frame.R;
import com.hy.frame.view.recycler.callback.XRefreshViewListener;
import java.util.Calendar;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

/* loaded from: classes.dex */
public class XRefreshView extends LinearLayout {
    private float OFFSET_RADIO;
    private int SCROLLBACK_DURATION;
    private boolean autoLoadMore;
    private boolean autoRefresh;
    private boolean enablePullUp;
    private boolean enableRecyclerViewPullUp;
    private boolean enableReleaseToLoadMore;
    private boolean isCanFunction;
    private boolean isForHorizontalMove;
    private boolean isHeightMatchParent;
    private boolean isIntercepted;
    private boolean isWidthMatchParent;
    private long lastRefreshTime;
    private boolean mCanMoveFooterWhenDisablePullLoadMore;
    private boolean mCanMoveHeaderWhenDisablePullRefresh;
    private XRefreshContentView mContentView;
    private View mEmptyView;
    private boolean mEnablePullLoad;
    private boolean mEnablePullRefresh;
    private boolean mEnablePullUpWhenLoadCompleted;
    private int mFootHeight;
    private IFooterCallBack mFooterCallBack;
    private View mFooterView;
    private boolean mHasSendCancelEvent;
    private boolean mHasSendDownEvent;
    private int mHeadMoveDistence;
    private IHeaderCallBack mHeaderCallBack;
    private int mHeaderGap;
    private View mHeaderView;
    private int mHeaderViewHeight;
    private XRefreshHolder mHolder;
    protected int mInitScrollY;
    private int mInitialMotionY;
    private boolean mIsIntercept;
    private boolean mIsPinnedContentWhenRefreshing;
    private MotionEvent mLastMoveEvent;
    private int mLastX;
    private int mLastY;
    private boolean mLayoutReady;
    private boolean mMoveForHorizontal;
    private boolean mNeedToRefresh;
    private int mPinnedTime;
    public boolean mPullLoading;
    public boolean mPullRefreshing;
    private XRefreshViewListener mRefreshViewListener;
    private boolean mReleaseToLoadMore;
    private ScrollRunner mRunnable;
    private Scroller mScroller;
    private XRefreshViewState mState;
    private boolean mStopingRefresh;
    private View mTempTarget;
    private final CopyOnWriteArrayList<TouchLifeCycle> mTouchLifeCycles;
    private int mTouchSlop;
    private int waitForShowEmptyView;

    /* loaded from: classes2.dex */
    public interface TouchLifeCycle {
        void onTouch(MotionEvent motionEvent);
    }

    public XRefreshView(Context context) {
        this(context, null);
    }

    public XRefreshView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mInitScrollY = 0;
        this.mLastY = -1;
        this.mLastX = -1;
        this.mEnablePullRefresh = true;
        this.mPullRefreshing = false;
        this.OFFSET_RADIO = 1.8f;
        this.autoRefresh = false;
        this.autoLoadMore = true;
        this.isHeightMatchParent = true;
        this.isWidthMatchParent = true;
        this.mHasSendCancelEvent = false;
        this.mHasSendDownEvent = false;
        this.mMoveForHorizontal = false;
        this.isForHorizontalMove = false;
        this.mCanMoveHeaderWhenDisablePullRefresh = true;
        this.mCanMoveFooterWhenDisablePullLoadMore = true;
        this.mIsIntercept = false;
        this.mPinnedTime = 1000;
        this.mState = null;
        this.mIsPinnedContentWhenRefreshing = false;
        this.enableReleaseToLoadMore = true;
        this.enableRecyclerViewPullUp = true;
        this.enablePullUp = true;
        this.mLayoutReady = false;
        this.mNeedToRefresh = false;
        this.isIntercepted = false;
        this.mTouchLifeCycles = new CopyOnWriteArrayList<>();
        this.isCanFunction = true;
        this.mReleaseToLoadMore = false;
        this.mEnablePullUpWhenLoadCompleted = true;
        this.mStopingRefresh = false;
        this.lastRefreshTime = -1L;
        this.SCROLLBACK_DURATION = 300;
        this.mRunnable = new ScrollRunner() { // from class: com.hy.frame.view.recycler.xRefreshView.XRefreshView.4
            @Override // java.lang.Runnable
            public void run() {
                if (XRefreshView.this.mScroller.computeScrollOffset()) {
                    int lastScrollY = XRefreshView.this.mHolder.mOffsetY;
                    int currentY = XRefreshView.this.mScroller.getCurrY();
                    int offsetY = currentY - lastScrollY;
                    XRefreshView.this.moveView(offsetY);
                    XRefreshView.this.mHeaderView.getLocationInWindow(new int[2]);
                    LogUtils.d("currentY=" + currentY + ";mHolder.mOffsetY=" + XRefreshView.this.mHolder.mOffsetY);
                    if (XRefreshView.this.enableReleaseToLoadMore && XRefreshView.this.mHolder.mOffsetY == 0 && XRefreshView.this.mReleaseToLoadMore && XRefreshView.this.mContentView != null && XRefreshView.this.mContentView.isBottom()) {
                        XRefreshView.this.mReleaseToLoadMore = false;
                        XRefreshView.this.mContentView.startLoadMore(false, null, null);
                    }
                    XRefreshView.this.post(this);
                    if (this.isStopLoadMore) {
                        XRefreshView.this.scrollback(offsetY);
                        return;
                    }
                    return;
                }
                int currentY2 = XRefreshView.this.mScroller.getCurrY();
                if (XRefreshView.this.mHolder.mOffsetY == 0) {
                    XRefreshView.this.enablePullUp(true);
                    XRefreshView.this.mStopingRefresh = false;
                    this.isStopLoadMore = false;
                } else if (XRefreshView.this.mStopingRefresh && !XRefreshView.this.mPullLoading && !XRefreshView.this.mPullRefreshing) {
                    XRefreshView.this.startScroll(-currentY2, Utils.computeScrollVerticalDuration(currentY2, XRefreshView.this.getHeight()));
                }
            }
        };
        this.waitForShowEmptyView = 0;
        setClickable(true);
        setLongClickable(true);
        this.mContentView = new XRefreshContentView();
        this.mHolder = new XRefreshHolder();
        this.mScroller = new Scroller(getContext(), new LinearInterpolator());
        setOrientation(1);
        initWithContext(context, attrs);
    }

    public void setOnTopRefreshTime(OnTopRefreshTime topListener) {
        this.mContentView.setOnTopRefreshTime(topListener);
    }

    public void setOnBottomLoadMoreTime(OnBottomLoadMoreTime bottomListener) {
        this.mContentView.setOnBottomLoadMoreTime(bottomListener);
    }

    public boolean needAddFooterView() {
        return !this.mContentView.isRecyclerView();
    }

    public void setMoveForHorizontal(boolean isForHorizontalMove) {
        this.isForHorizontalMove = isForHorizontalMove;
    }

    @Deprecated
    public void setSilenceLoadMore() {
        this.mContentView.setSilenceLoadMore(true);
        setPullLoadEnable(false);
    }

    public void setSilenceLoadMore(boolean enable) {
        if (enable) {
            this.mContentView.setSilenceLoadMore(true);
            setPullLoadEnable(false);
            return;
        }
        this.mContentView.setSilenceLoadMore(false);
    }

    public void notifyLayoutManagerChanged() {
        this.mContentView.setScrollListener();
        this.mContentView.notifyDatasetChanged();
    }

    private void initWithContext(Context context, AttributeSet attrs) {
        TypedArray a;
        if (attrs != null) {
            try {
                a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.XRefreshView, 0, 0);
                this.isHeightMatchParent = a.getBoolean(R.styleable.XRefreshView_isHeightMatchParent, true);
                this.isWidthMatchParent = a.getBoolean(R.styleable.XRefreshView_isHeightMatchParent, true);
                this.autoRefresh = a.getBoolean(R.styleable.XRefreshView_autoRefresh, false);
                this.autoLoadMore = a.getBoolean(R.styleable.XRefreshView_autoLoadMore, true);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                a.recycle();
            }
        }
        addHeaderView();
        getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() { // from class: com.hy.frame.view.recycler.xRefreshView.XRefreshView.1
            @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
            public void onGlobalLayout() {
                XRefreshView.this.mLayoutReady = true;
                if (XRefreshView.this.autoRefresh || XRefreshView.this.mNeedToRefresh) {
                    XRefreshView.this.startRefresh();
                }
                XRefreshView.this.setHeadMoveLargestDistence(XRefreshView.this.mHeadMoveDistence);
                XRefreshView.this.attachContentView();
                XRefreshView.this.addFooterView();
                if (XRefreshView.this.waitForShowEmptyView == 1) {
                    XRefreshView.this.enableEmptyView(true);
                    XRefreshView.this.waitForShowEmptyView = 0;
                }
                XRefreshView.this.removeViewTreeObserver(this);
            }
        });
        this.mTouchSlop = ViewConfiguration.get(getContext()).getScaledTouchSlop();
    }

    private void addHeaderView() {
        if (this.mHeaderView == null) {
            this.mHeaderView = new XRefreshViewHeader(getContext());
        }
        dealAddHeaderView();
    }

    private void dealAddHeaderView() {
        if (indexOfChild(this.mHeaderView) == -1) {
            Utils.removeViewFromParent(this.mHeaderView);
            addView(this.mHeaderView, 0);
            this.mHeaderCallBack = (IHeaderCallBack) this.mHeaderView;
            setRefreshTime();
            checkPullRefreshEnable();
        }
    }

    private void dealAddFooterView() {
        if (indexOfChild(this.mFooterView) == -1) {
            if (needAddFooterView()) {
                Utils.removeViewFromParent(this.mFooterView);
                try {
                    addView(this.mFooterView, 2);
                } catch (IndexOutOfBoundsException e) {
                    new RuntimeException("XRefreshView is allowed to have one and only one child");
                }
            }
            this.mFooterCallBack = (IFooterCallBack) this.mFooterView;
            this.mContentView.setmFooterCallBack(this.mFooterCallBack);
            checkPullLoadEnable();
        }
    }

    public void attachContentView() {
        this.mContentView.setContentView(getChildAt(1));
        this.mContentView.setContainer(this.autoLoadMore ? this : null);
        this.mContentView.setContentViewLayoutParams(this.isHeightMatchParent, this.isWidthMatchParent);
        this.mContentView.setHolder(this.mHolder);
        this.mContentView.setParent(this);
        this.mContentView.setScrollListener();
    }

    public void addFooterView() {
        if (this.mFooterView == null) {
            this.mFooterView = new XRefreshViewFooter(getContext());
        }
        dealAddFooterView();
    }

    @SuppressLint({"NewApi"})
    public void removeViewTreeObserver(ViewTreeObserver.OnGlobalLayoutListener listener) {
        if (Build.VERSION.SDK_INT < 16) {
            getViewTreeObserver().removeGlobalOnLayoutListener(listener);
        } else {
            getViewTreeObserver().removeOnGlobalLayoutListener(listener);
        }
    }

    public void setHeaderGap(int headerGap) {
        this.mHeaderGap = headerGap;
    }

    private void getHeaderHeight() {
        if (this.mHeaderCallBack != null) {
            this.mHeaderViewHeight = this.mHeaderCallBack.getHeaderHeight();
        }
    }

    private void getFooterHeight() {
        if (this.mFooterCallBack != null) {
            this.mFootHeight = this.mFooterCallBack.getFooterHeight();
        }
    }

    @Override // android.widget.LinearLayout, android.view.View
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = View.MeasureSpec.getSize(widthMeasureSpec);
        int height = View.MeasureSpec.getSize(heightMeasureSpec);
        int childCount = getChildCount();
        int finalHeight = 0;
        int paddingLeft = getPaddingLeft();
        int paddingRight = getPaddingRight();
        int paddingTop = getPaddingTop();
        int paddingBottom = getPaddingBottom();
        for (int i = 0; i < childCount; i++) {
            View child = getChildAt(i);
            LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) child.getLayoutParams();
            child.measure(View.MeasureSpec.makeMeasureSpec((((width - lp.leftMargin) - lp.rightMargin) - paddingLeft) - paddingRight, 1073741824), getChildMeasureSpec(heightMeasureSpec, paddingTop + paddingBottom + lp.topMargin + lp.bottomMargin, lp.height));
            finalHeight += child.getMeasuredHeight() + lp.topMargin + lp.bottomMargin;
        }
        setMeasuredDimension(width, height);
        hideUselessFooter();
        getHeaderHeight();
        getFooterHeight();
    }

    @Override // android.widget.LinearLayout, android.view.ViewGroup, android.view.View
    protected void onLayout(boolean changed, int l, int t2, int r, int b) {
        LogUtils.d("onLayout mHolder.mOffsetY=" + this.mHolder.mOffsetY);
        int childCount = getChildCount();
        int top = getPaddingTop() + this.mHolder.mOffsetY;
        int adHeight = 0;
        for (int i = 0; i < childCount; i++) {
            View child = getChildAt(i);
            LinearLayout.LayoutParams margins = (LinearLayout.LayoutParams) child.getLayoutParams();
            int topMargin = margins.topMargin;
            int bottomMargin = margins.bottomMargin;
            int leftMargin = margins.leftMargin;
            int i2 = margins.rightMargin;
            int l2 = leftMargin + getPaddingLeft();
            top += topMargin;
            int r2 = child.getMeasuredWidth();
            if (child.getVisibility() != 8) {
                if (i == 0) {
                    adHeight = child.getMeasuredHeight() - this.mHeaderViewHeight;
                    child.layout(l2, top - this.mHeaderViewHeight, l2 + r2, top + adHeight);
                    top += adHeight;
                } else if (i == 1) {
                    int childHeight = child.getMeasuredHeight() - adHeight;
                    child.layout(l2, top, l2 + r2, childHeight + top);
                    top += childHeight + bottomMargin;
                } else if (needAddFooterView()) {
                    child.layout(l2, top, l2 + r2, child.getMeasuredHeight() + top);
                    top += child.getMeasuredHeight();
                } else {
                    hideUselessFooter();
                }
            }
        }
    }

    private void hideUselessFooter() {
        if (!needAddFooterView() && this.mFooterView != null && this.mFooterView.getVisibility() != 8) {
            this.mFooterView.setVisibility(8);
        }
    }

    public void addTouchLifeCycle(TouchLifeCycle lifeCycle) {
        this.mTouchLifeCycles.add(lifeCycle);
    }

    public void removeTouchLifeCycle(TouchLifeCycle lifeCycle) {
        if (lifeCycle != null && this.mTouchLifeCycles.contains(lifeCycle)) {
            this.mTouchLifeCycles.remove(lifeCycle);
        }
    }

    private void updateTouchAction(MotionEvent event) {
        Iterator<TouchLifeCycle> it = this.mTouchLifeCycles.iterator();
        while (it.hasNext()) {
            TouchLifeCycle lifeCycle = it.next();
            if (lifeCycle != null) {
                lifeCycle.onTouch(event);
            }
        }
    }

    public boolean isCanFunction() {
        return this.isCanFunction;
    }

    public void setCanFunction(boolean canFunction) {
        this.isCanFunction = canFunction;
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchTouchEvent(MotionEvent ev) {
        int action = ev.getAction();
        if (!this.isCanFunction) {
            return super.dispatchTouchEvent(ev);
        }
        updateTouchAction(ev);
        switch (action) {
            case 0:
                this.mHasSendCancelEvent = false;
                this.mHasSendDownEvent = false;
                this.mLastY = (int) ev.getRawY();
                this.mLastX = (int) ev.getRawX();
                this.mInitialMotionY = this.mLastY;
                break;
            case 1:
            case 3:
                if (this.mHolder.hasHeaderPullDown()) {
                    if (this.mEnablePullRefresh && !this.mStopingRefresh && !this.mPullRefreshing && this.mHolder.mOffsetY > this.mHeaderViewHeight) {
                        this.mPullRefreshing = true;
                        this.mHeaderCallBack.onStateRefreshing();
                        this.mState = XRefreshViewState.STATE_REFRESHING;
                        if (this.mRefreshViewListener != null) {
                            this.mRefreshViewListener.onRefresh();
                        }
                    }
                    resetHeaderHeight();
                } else if (this.mHolder.hasFooterPullUp() && !this.mStopingRefresh) {
                    if (!this.mEnablePullLoad || isEmptyViewShowing() || !needAddFooterView() || this.mContentView.hasLoadCompleted()) {
                        int offset = 0 - this.mHolder.mOffsetY;
                        startScroll(offset, Utils.computeScrollVerticalDuration(offset, getHeight()));
                    } else {
                        invokeLoadMore();
                    }
                }
                this.mLastY = -1;
                this.mLastX = -1;
                this.mInitialMotionY = 0;
                this.isIntercepted = false;
                this.mMoveForHorizontal = false;
                break;
            case 2:
                this.mLastMoveEvent = ev;
                if (this.mStopingRefresh || !isEnabled() || this.mIsIntercept) {
                    return super.dispatchTouchEvent(ev);
                }
                if ((this.mPullLoading || this.mPullRefreshing) && this.mIsPinnedContentWhenRefreshing) {
                    sendCancelEvent();
                    return true;
                }
                int currentY = (int) ev.getRawY();
                int currentX = (int) ev.getRawX();
                int deltaY = currentY - this.mLastY;
                int deltaX = currentX - this.mLastX;
                this.mLastY = currentY;
                this.mLastX = currentX;
                if (!this.isIntercepted) {
                    if (Math.abs(currentY - this.mInitialMotionY) < this.mTouchSlop) {
                        return super.dispatchTouchEvent(ev);
                    }
                    this.isIntercepted = true;
                }
                if (!this.mMoveForHorizontal && Math.abs(deltaX) > this.mTouchSlop && Math.abs(deltaX) > Math.abs(deltaY) && this.mHolder.mOffsetY < 10) {
                    this.mMoveForHorizontal = true;
                }
                if (this.mMoveForHorizontal) {
                    return super.dispatchTouchEvent(ev);
                }
                LogUtils.d("isTop=" + this.mContentView.isTop() + ";isBottom=" + this.mContentView.isBottom());
                if ((deltaY <= 0 || this.mHolder.mOffsetY > this.mHeadMoveDistence) && deltaY >= 0) {
                    return super.dispatchTouchEvent(ev);
                }
                int deltaY2 = (int) (deltaY / this.OFFSET_RADIO);
                if (this.mPullLoading || this.mReleaseToLoadMore || !this.mContentView.isTop() || ((deltaY2 <= 0 || this.mHolder.hasFooterPullUp()) && (deltaY2 >= 0 || !this.mHolder.hasHeaderPullDown()))) {
                    if (this.mPullRefreshing || !this.mContentView.isBottom() || (deltaY2 >= 0 && (deltaY2 <= 0 || !this.mHolder.hasFooterPullUp()))) {
                        if (deltaY2 != 0 && ((this.mContentView.isTop() && !this.mHolder.hasHeaderPullDown()) || (this.mContentView.isBottom() && !this.mHolder.hasFooterPullUp()))) {
                            if (this.mReleaseToLoadMore) {
                                releaseToLoadMore(false);
                            }
                            if (Math.abs(deltaY2) > 0) {
                                sendDownEvent();
                                break;
                            }
                        }
                    } else if (this.mEnablePullLoad) {
                        sendCancelEvent();
                        updateFooterHeight(deltaY2);
                        break;
                    } else {
                        return super.dispatchTouchEvent(ev);
                    }
                } else if (this.mEnablePullRefresh) {
                    sendCancelEvent();
                    updateHeaderHeight(currentY, deltaY2, new int[0]);
                    break;
                } else {
                    return super.dispatchTouchEvent(ev);
                }
                break;
        }
        return super.dispatchTouchEvent(ev);
    }

    public XRefreshContentView getContentView() {
        return this.mContentView;
    }

    public boolean invokeLoadMore() {
        if (!this.mEnablePullLoad || isEmptyViewShowing() || this.mPullRefreshing || this.mStopingRefresh || this.mContentView.hasLoadCompleted()) {
            return false;
        }
        int offset = (0 - this.mHolder.mOffsetY) - this.mFootHeight;
        if (offset != 0) {
            startScroll(offset, Utils.computeScrollVerticalDuration(offset, getHeight()));
        }
        startLoadMore();
        return true;
    }

    public void notifyLoadMore() {
        if (needAddFooterView()) {
            startLoadMore();
        } else {
            this.mContentView.notifyRecyclerViewLoadMore();
        }
    }

    public void disallowInterceptTouchEvent(boolean isIntercept) {
        this.mIsIntercept = isIntercept;
    }

    private void sendCancelEvent() {
        if (!this.mHasSendCancelEvent) {
            LogUtils.d("sendCancelEvent");
            setRefreshTime();
            this.mHasSendCancelEvent = true;
            this.mHasSendDownEvent = false;
            MotionEvent last = this.mLastMoveEvent;
            dispatchTouchEventSupper(MotionEvent.obtain(last.getDownTime(), last.getEventTime() + ViewConfiguration.getLongPressTimeout(), 3, last.getX(), last.getY(), last.getMetaState()));
        }
    }

    public void setHeadMoveLargestDistence(int headMoveDistence) {
        if (headMoveDistence <= 0) {
            this.mHeadMoveDistence = Utils.getScreenSize(getContext()).y / 3;
        } else {
            this.mHeadMoveDistence = headMoveDistence;
        }
        this.mHeadMoveDistence = this.mHeadMoveDistence <= this.mHeaderViewHeight ? this.mHeaderViewHeight + 1 : this.mHeadMoveDistence;
    }

    private void sendDownEvent() {
        if (!this.mHasSendDownEvent) {
            LogUtils.d("sendDownEvent");
            this.mHasSendCancelEvent = false;
            this.mHasSendDownEvent = true;
            this.isIntercepted = false;
            MotionEvent last = this.mLastMoveEvent;
            if (last != null) {
                dispatchTouchEventSupper(MotionEvent.obtain(last.getDownTime(), last.getEventTime(), 0, last.getX(), last.getY(), last.getMetaState()));
            }
        }
    }

    public boolean dispatchTouchEventSupper(MotionEvent e) {
        return super.dispatchTouchEvent(e);
    }

    public void setPullLoadEnable(boolean enable) {
        this.mEnablePullLoad = enable;
        if (needAddFooterView()) {
            checkPullLoadEnable();
        } else {
            this.mContentView.setEnablePullLoad(enable);
        }
    }

    public boolean getPullLoadEnable() {
        return this.mEnablePullLoad;
    }

    public boolean getPullRefreshEnable() {
        return this.mEnablePullRefresh;
    }

    public void setPullRefreshEnable(boolean enable) {
        this.mEnablePullRefresh = enable;
        checkPullRefreshEnable();
    }

    private void checkPullRefreshEnable() {
        if (this.mHeaderCallBack != null) {
            if (!this.mEnablePullRefresh) {
                this.mHeaderCallBack.hide();
            } else {
                this.mHeaderCallBack.show();
            }
        }
    }

    private void checkPullLoadEnable() {
        if (this.mFooterCallBack != null) {
            if (!this.mEnablePullLoad) {
                this.mFooterCallBack.show(false);
                return;
            }
            this.mPullLoading = false;
            this.mFooterCallBack.show(true);
            this.mFooterCallBack.onStateRefreshing();
        }
    }

    private void startLoadMore() {
        if (!this.mPullLoading) {
            this.mFooterCallBack.onStateRefreshing();
            this.mPullLoading = true;
            if (this.mRefreshViewListener != null) {
                this.mRefreshViewListener.onLoadMore(false);
            }
        }
    }

    private void updateHeaderHeight(int currentY, int deltaY, int... during) {
        if (during != null && during.length > 0) {
            this.mHeaderCallBack.onStateRefreshing();
            startScroll(deltaY, during[0]);
            return;
        }
        if (this.mHolder.isOverHeader(deltaY)) {
            deltaY = -this.mHolder.mOffsetY;
        }
        if (this.mEnablePullRefresh || this.mCanMoveHeaderWhenDisablePullRefresh) {
            moveView(deltaY);
        }
        if (this.mEnablePullRefresh && !this.mPullRefreshing) {
            if (this.mHolder.mOffsetY > this.mHeaderViewHeight) {
                if (this.mState != XRefreshViewState.STATE_READY) {
                    this.mHeaderCallBack.onStateReady();
                    this.mState = XRefreshViewState.STATE_READY;
                }
            } else if (this.mState != XRefreshViewState.STATE_NORMAL) {
                this.mHeaderCallBack.onStateNormal();
                this.mState = XRefreshViewState.STATE_NORMAL;
            }
        }
    }

    public void setMoveHeadWhenDisablePullRefresh(boolean moveHeadWhenDisablePullRefresh) {
        this.mCanMoveHeaderWhenDisablePullRefresh = moveHeadWhenDisablePullRefresh;
    }

    public void setMoveFootWhenDisablePullLoadMore(boolean moveFootWhenDisablePullLoadMore) {
        this.mCanMoveFooterWhenDisablePullLoadMore = moveFootWhenDisablePullLoadMore;
    }

    private boolean canReleaseToLoadMore() {
        return this.enableReleaseToLoadMore && this.mEnablePullLoad && this.mContentView != null && !this.mContentView.hasLoadCompleted() && !this.mContentView.isLoading();
    }

    private void releaseToLoadMore(boolean loadMore) {
        this.mReleaseToLoadMore = loadMore;
        this.mContentView.releaseToLoadMore(this.mReleaseToLoadMore);
    }

    private void updateFooterHeight(int deltaY) {
        if (this.mEnablePullLoad && needAddFooterView() && !this.mPullLoading) {
            if (this.mHolder.mOffsetY > this.mFootHeight) {
                if (this.mState != XRefreshViewState.STATE_LOADING) {
                    this.mFooterCallBack.onStateReady();
                    this.mFooterCallBack.show(true);
                    this.mState = XRefreshViewState.STATE_LOADING;
                }
            } else if (!this.mContentView.hasLoadCompleted() && this.mState != XRefreshViewState.STATE_READY) {
                this.mFooterCallBack.onStateReady();
                this.mFooterCallBack.show(true);
                this.mState = XRefreshViewState.STATE_READY;
            }
            this.mFooterCallBack.show(true);
        }
        if (!needAddFooterView() && !this.enableRecyclerViewPullUp) {
            return;
        }
        if (!this.mEnablePullUpWhenLoadCompleted && this.mContentView.hasLoadCompleted()) {
            return;
        }
        if (this.mEnablePullLoad || this.mCanMoveFooterWhenDisablePullLoadMore) {
            moveView(deltaY);
        }
    }

    public void setAutoRefresh(boolean autoRefresh) {
        this.autoRefresh = autoRefresh;
    }

    public void setAutoLoadMore(boolean autoLoadMore) {
        this.autoLoadMore = autoLoadMore;
        if (this.mContentView != null) {
            this.mContentView.setContainer(autoLoadMore ? this : null);
        }
        if (autoLoadMore) {
            setPullLoadEnable(true);
        }
    }

    public void startRefresh() {
        if (this.mEnablePullRefresh && this.mHolder.mOffsetY == 0 && !this.mContentView.isLoading() && !this.mPullRefreshing && isEnabled()) {
            if (this.mLayoutReady) {
                this.mNeedToRefresh = false;
                updateHeaderHeight(0, this.mHeaderViewHeight, 0);
                this.mPullRefreshing = true;
                if (this.mRefreshViewListener != null) {
                    this.mRefreshViewListener.onRefresh();
                }
                this.mContentView.scrollToTop();
                return;
            }
            this.mNeedToRefresh = true;
        }
    }

    public void resetHeaderHeight() {
        int offsetY;
        float height = this.mHolder.mOffsetY;
        if (!this.mPullRefreshing || (height > this.mHeaderViewHeight && height != 0.0f)) {
            if (this.mPullRefreshing) {
                offsetY = this.mHeaderViewHeight - this.mHolder.mOffsetY;
                startScroll(offsetY, Utils.computeScrollVerticalDuration(offsetY, getHeight()));
            } else {
                offsetY = 0 - this.mHolder.mOffsetY;
                startScroll(offsetY, Utils.computeScrollVerticalDuration(offsetY, getHeight()));
            }
            LogUtils.d("resetHeaderHeight offsetY=" + offsetY);
        }
    }

    public void moveView(int deltaY) {
        this.mHolder.move(deltaY);
        this.mHeaderView.offsetTopAndBottom(deltaY);
        this.mContentView.offsetTopAndBottom(deltaY);
        if (needAddFooterView()) {
            this.mFooterView.offsetTopAndBottom(deltaY);
        }
        ViewCompat.postInvalidateOnAnimation(this);
        if (this.mRefreshViewListener == null) {
            return;
        }
        if (this.mContentView.isTop() || this.mPullRefreshing) {
            double headerMovePercent = (1.0d * this.mHolder.mOffsetY) / this.mHeaderViewHeight;
            this.mRefreshViewListener.onHeaderMove(headerMovePercent, this.mHolder.mOffsetY);
            this.mHeaderCallBack.onHeaderMove(headerMovePercent, this.mHolder.mOffsetY, deltaY);
        }
    }

    public void stopRefresh() {
        stopRefresh(true);
    }

    public void stopRefresh(boolean success) {
        LogUtils.d("stopRefresh mPullRefreshing=" + this.mPullRefreshing);
        if (this.mPullRefreshing) {
            this.mStopingRefresh = true;
            this.mHeaderCallBack.onStateFinish(success);
            this.mState = XRefreshViewState.STATE_COMPLETE;
            postDelayed(new Runnable() { // from class: com.hy.frame.view.recycler.xRefreshView.XRefreshView.2
                @Override // java.lang.Runnable
                public void run() {
                    XRefreshView.this.mPullRefreshing = false;
                    if (XRefreshView.this.mStopingRefresh) {
                        XRefreshView.this.resetHeaderHeight();
                    }
                    XRefreshView.this.lastRefreshTime = Calendar.getInstance().getTimeInMillis();
                }
            }, this.mPinnedTime);
        }
    }

    public void restoreLastRefreshTime(long lastRefreshTime) {
        this.lastRefreshTime = lastRefreshTime;
    }

    public long getLastRefreshTime() {
        return this.lastRefreshTime;
    }

    private void setRefreshTime() {
        if (this.lastRefreshTime > 0) {
            this.mHeaderCallBack.setRefreshTime(this.lastRefreshTime);
        }
    }

    public void stopLoadMore() {
        stopLoadMore(true);
    }

    public void stopLoadMore(boolean hideFooter) {
        this.mState = XRefreshViewState.STATE_FINISHED;
        stopLoadMore(hideFooter, this.SCROLLBACK_DURATION);
    }

    private void stopLoadMore(final boolean hideFooter, final int scrollBackDuration) {
        if (needAddFooterView() && this.mPullLoading) {
            this.mStopingRefresh = true;
            if (this.mState == XRefreshViewState.STATE_COMPLETE) {
                this.mFooterCallBack.onStateComplete();
            } else {
                this.mFooterCallBack.onStateFinish(hideFooter);
            }
            if (this.mPinnedTime >= 1000) {
                postDelayed(new Runnable() { // from class: com.hy.frame.view.recycler.xRefreshView.XRefreshView.3
                    @Override // java.lang.Runnable
                    public void run() {
                        XRefreshView.this.endLoadMore(hideFooter, scrollBackDuration);
                    }
                }, this.mPinnedTime);
            } else {
                endLoadMore(hideFooter, scrollBackDuration);
            }
        }
        this.mContentView.stopLoading(hideFooter);
    }

    public void setLoadComplete(boolean hasComplete) {
        if (needAddFooterView()) {
            if (hasComplete) {
                this.mState = XRefreshViewState.STATE_COMPLETE;
            } else {
                this.mState = XRefreshViewState.STATE_NORMAL;
            }
            stopLoadMore(true, this.SCROLLBACK_DURATION);
            if (!hasComplete && this.mEnablePullLoad && this.mFooterCallBack != null) {
                this.mFooterCallBack.onStateRefreshing();
                this.mFooterCallBack.show(true);
            }
        }
        this.mContentView.setLoadComplete(hasComplete);
    }

    public void scrollback(int offset) {
        View child = this.mContentView.getContentView();
        if (child instanceof AbsListView) {
            ((AbsListView) child).smoothScrollBy(offset, 0);
        }
    }

    public void resetLayout() {
        enablePullUp(false);
        if (this.mHolder.mOffsetY != 0 && !this.mStopingRefresh) {
            startScroll(-this.mHolder.mOffsetY, Utils.computeScrollVerticalDuration(this.mHolder.mOffsetY, getHeight()));
        }
    }

    public void enablePullUp(boolean enablePullUp) {
        this.enablePullUp = enablePullUp;
    }

    public void setScrollBackDuration(int duration) {
        this.SCROLLBACK_DURATION = duration;
    }

    public void endLoadMore(boolean hideFooter, int scrolbackduration) {
        this.mPullLoading = false;
        this.mRunnable.isStopLoadMore = true;
        startScroll(-this.mHolder.mOffsetY, scrolbackduration);
    }

    public void startScroll(int offsetY, int duration) {
        this.mScroller.startScroll(0, this.mHolder.mOffsetY, 0, offsetY, duration);
        post(this.mRunnable);
    }

    public boolean isStopLoadMore() {
        return this.mRunnable.isStopLoadMore;
    }

    public void setOnAbsListViewScrollListener(AbsListView.OnScrollListener scrollListener) {
        this.mContentView.setOnAbsListViewScrollListener(scrollListener);
    }

    public void setEmptyView(View emptyView) {
        Utils.removeViewFromParent(emptyView);
        this.mEmptyView = emptyView;
        addEmptyViewLayoutParams();
    }

    private void addEmptyViewLayoutParams() {
        if (this.mEmptyView != null) {
            LinearLayout.LayoutParams layoutparams = generateDefaultLayoutParams();
            layoutparams.height = -1;
            layoutparams.width = -1;
            this.mEmptyView.setLayoutParams(layoutparams);
        }
    }

    public void setEmptyView(@LayoutRes int emptyView) {
        if (!getContext().getResources().getResourceTypeName(emptyView).contains(f.bt)) {
            throw new RuntimeException(getContext().getResources().getResourceName(emptyView) + " is a illegal layoutid , please check your layout id first !");
        }
        setEmptyView(LayoutInflater.from(getContext()).inflate(emptyView, (ViewGroup) this, false));
    }

    public void enableEmptyView(boolean enable) {
        int i = 1;
        if (!this.mLayoutReady) {
            if (!enable) {
                i = 2;
            }
            this.waitForShowEmptyView = i;
            return;
        }
        View contentView = getChildAt(1);
        if (enable) {
            if (this.mEmptyView != null && contentView != this.mEmptyView) {
                this.mTempTarget = getChildAt(1);
                swapContentView(this.mEmptyView);
            }
        } else if (this.mTempTarget != null && contentView == this.mEmptyView) {
            swapContentView(this.mTempTarget);
        }
    }

    public boolean isEmptyViewShowing() {
        if (this.mEmptyView == null || getChildCount() < 2) {
            return false;
        }
        return getChildAt(1) == this.mEmptyView;
    }

    public View getEmptyView() {
        return this.mEmptyView;
    }

    private void swapContentView(View newContentView) {
        removeViewAt(1);
        addView(newContentView, 1);
        this.mContentView.setContentView(newContentView);
        this.mContentView.scrollToTop();
    }

    public void setOnRecyclerViewScrollListener(RecyclerView.OnScrollListener scrollListener) {
        this.mContentView.setOnRecyclerViewScrollListener(scrollListener);
    }

    public void setPreLoadCount(int count) {
        this.mContentView.setPreLoadCount(count);
    }

    public void setXRefreshViewListener(XRefreshViewListener xl) {
        this.mRefreshViewListener = xl;
        this.mContentView.setXRefreshViewListener(this.mRefreshViewListener);
    }

    public void enableReleaseToLoadMore(boolean enable) {
        this.enableReleaseToLoadMore = enable;
    }

    public void enablePullUpWhenLoadCompleted(boolean enable) {
        this.mEnablePullUpWhenLoadCompleted = enable;
    }

    public void enableRecyclerViewPullUp(boolean enable) {
        this.enableRecyclerViewPullUp = enable;
    }

    public void setFooterCallBack(IFooterCallBack footerCallBack) {
        this.mFooterCallBack = footerCallBack;
    }

    public void setDampingRatio(float ratio) {
        this.OFFSET_RADIO = ratio;
    }

    public void setPinnedTime(int pinnedTime) {
        this.mPinnedTime = pinnedTime;
        this.mContentView.setPinnedTime(pinnedTime);
    }

    public void setHideFooterWhenComplete(boolean hide) {
        this.mContentView.setHideFooterWhenComplete(hide);
    }

    public void setPinnedContent(boolean isPinned) {
        this.mIsPinnedContentWhenRefreshing = isPinned;
    }

    public void setCustomHeaderView(View headerView) {
        if (headerView instanceof IHeaderCallBack) {
            if (this.mHeaderView != null) {
                removeView(this.mHeaderView);
            }
            this.mHeaderView = headerView;
            dealAddHeaderView();
            return;
        }
        throw new RuntimeException("headerView must be implementes IHeaderCallBack!");
    }

    public void setCustomFooterView(View footerView) {
        if (footerView instanceof IFooterCallBack) {
            if (this.mFooterView != null) {
                removeView(this.mFooterView);
            }
            this.mFooterView = footerView;
            dealAddFooterView();
            return;
        }
        throw new RuntimeException("footerView must be implementes IFooterCallBack!");
    }
}
