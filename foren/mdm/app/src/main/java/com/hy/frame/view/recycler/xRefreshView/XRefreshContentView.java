package com.hy.frame.view.recycler.xRefreshView;

import android.support.v4.view.ViewCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.AbsListView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import com.hy.frame.view.recycler.callback.XRefreshViewListener;
import com.hy.frame.view.recycler.xRefreshView.XScrollView;

/* loaded from: classes2.dex */
public class XRefreshContentView implements AbsListView.OnScrollListener, OnTopRefreshTime, OnBottomLoadMoreTime {
    private static final String RECYCLERVIEW_ADAPTER_WARIN = "Recylerview的adapter请继承 BaseRecyclerAdapter,否则不能使用封装的Recyclerview的相关特性";
    private View child;
    private LAYOUT_MANAGER_TYPE layoutManagerType;
    private AbsListView.OnScrollListener mAbsListViewScrollListener;
    private OnBottomLoadMoreTime mBottomLoadMoreTime;
    private XRefreshView mContainer;
    private int mFirstVisibleItem;
    private IFooterCallBack mFooterCallBack;
    private XRefreshHolder mHolder;
    private boolean mIsLoadingMore;
    private int mLastVisibleItemPosition;
    private RecyclerView.OnScrollListener mOnScrollListener;
    private XRefreshView mParent;
    private int mPinnedTime;
    private int mPreLoadCount;
    private RecyclerViewHolder mRecyclerApdater;
    private RecyclerView.OnScrollListener mRecyclerViewScrollListener;
    private XRefreshViewListener mRefreshViewListener;
    private OnTopRefreshTime mTopRefreshTime;
    private int mTotalItemCount;
    private int mVisibleItemCount = 0;
    private int previousTotal = 0;
    private XRefreshViewState mState = XRefreshViewState.STATE_NORMAL;
    private boolean mHasLoadComplete = false;
    private boolean hasIntercepted = false;
    private boolean mSilenceLoadMore = false;
    private boolean mHideFooter = true;
    private boolean addingFooter = false;
    private boolean mRefreshAdapter = false;
    private boolean isHideFooterWhenComplete = true;
    private boolean isForbidLoadMore = true;

    /* loaded from: classes2.dex */
    public enum LAYOUT_MANAGER_TYPE {
        LINEAR,
        GRID,
        STAGGERED_GRID
    }

    public void setParent(XRefreshView parent) {
        this.mParent = parent;
    }

    public void setContentViewLayoutParams(boolean isHeightMatchParent, boolean isWidthMatchParent) {
        LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) this.child.getLayoutParams();
        if (isHeightMatchParent) {
            lp.height = -1;
        }
        if (isWidthMatchParent) {
            lp.height = -1;
        }
        this.child.setLayoutParams(lp);
    }

    public void setContentView(View child) {
        this.child = child;
        child.setOverScrollMode(2);
    }

    public View getContentView() {
        return this.child;
    }

    public void setHolder(XRefreshHolder holder) {
        this.mHolder = holder;
    }

    public void setContainer(XRefreshView container) {
        this.mContainer = container;
    }

    public void scrollToTop() {
        if (this.child instanceof AbsListView) {
            ((AbsListView) this.child).setSelection(0);
        } else if (this.child instanceof RecyclerView) {
            ((RecyclerView) this.child).getLayoutManager().scrollToPosition(0);
        }
    }

    public void setSilenceLoadMore(boolean silenceLoadMore) {
        this.mSilenceLoadMore = silenceLoadMore;
    }

    public void setmFooterCallBack(IFooterCallBack mFooterCallBack) {
        this.mFooterCallBack = mFooterCallBack;
    }

    public void setScrollListener() {
        if (this.child instanceof AbsListView) {
            ((AbsListView) this.child).setOnScrollListener(this);
        } else if (this.child instanceof ScrollView) {
            setScrollViewScrollListener();
        } else if (this.child instanceof RecyclerView) {
            setRecyclerViewScrollListener();
        }
    }

    private void setScrollViewScrollListener() {
        if (this.child instanceof XScrollView) {
            ((XScrollView) this.child).setOnScrollListener(this.mParent, new XScrollView.OnScrollListener() { // from class: com.hy.frame.view.recycler.xRefreshView.XRefreshContentView.1
                @Override // com.hy.frame.view.recycler.xRefreshView.XScrollView.OnScrollListener
                public void onScrollStateChanged(ScrollView view, int scrollState, boolean arriveBottom) {
                    if (scrollState == 0 && arriveBottom) {
                        if (XRefreshContentView.this.mSilenceLoadMore) {
                            if (XRefreshContentView.this.mRefreshViewListener != null) {
                                XRefreshContentView.this.mRefreshViewListener.onLoadMore(true);
                            }
                        } else if (XRefreshContentView.this.mContainer != null && !XRefreshContentView.this.hasLoadCompleted()) {
                            XRefreshContentView.this.mContainer.invokeLoadMore();
                        }
                    }
                }

                @Override // com.hy.frame.view.recycler.xRefreshView.XScrollView.OnScrollListener
                public void onScroll(int l, int t, int oldl, int oldt) {
                }
            });
            return;
        }
        throw new RuntimeException("please use XScrollView instead of ScrollView!");
    }

    public void onRecyclerViewScrolled(RecyclerView recyclerView, RecyclerViewHolder adapter, int dx, int dy, boolean force) {
        if (this.mRecyclerViewScrollListener != null) {
            this.mRecyclerViewScrollListener.onScrolled(recyclerView, dx, dy);
        }
        if ((this.mFooterCallBack != null || this.mSilenceLoadMore) && adapter != null) {
            RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
            getRecyclerViewInfo(layoutManager);
            refreshAdapter(adapter, layoutManager);
            LogUtils.d("test pre onScrolled mIsLoadingMore=" + this.mIsLoadingMore);
            if (onRecyclerViewTop()) {
                if (!Utils.isRecyclerViewFullscreen(recyclerView) && this.mHideFooter) {
                    this.mFooterCallBack.onStateReady();
                    this.mFooterCallBack.callWhenNotAutoLoadMore(this.mParent);
                }
            } else if (dy == 0 && !force) {
            } else {
                if (this.mSilenceLoadMore) {
                    doSilenceLoadMore(adapter, layoutManager);
                    return;
                }
                if (!isOnRecyclerViewBottom()) {
                    this.mHideFooter = true;
                }
                if (this.mParent != null && !this.mParent.getPullLoadEnable() && !this.hasIntercepted) {
                    addFooterView(false);
                    this.hasIntercepted = true;
                }
                if (!this.hasIntercepted) {
                    ensureFooterShowWhenScrolling();
                    if (this.mContainer != null) {
                        doAutoLoadMore(adapter, layoutManager);
                    } else {
                        doNormalLoadMore(adapter, layoutManager);
                    }
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public RecyclerViewHolder getRecyclerApdater(RecyclerView recyclerView) {
        RecyclerViewHolder adapter = null;
        if (recyclerView.getAdapter() instanceof RecyclerViewHolder) {
            adapter = (RecyclerViewHolder) recyclerView.getAdapter();
            RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
            if (layoutManager != null && (layoutManager instanceof GridLayoutManager)) {
                GridLayoutManager gridLayoutManager = (GridLayoutManager) layoutManager;
                gridLayoutManager.setSpanSizeLookup(new XSpanSizeLookup(adapter, gridLayoutManager.getSpanCount()));
            }
            adapter.insideEnableFooter(this.mParent.getPullLoadEnable());
            initFooterCallBack(adapter, this.mParent);
        }
        return adapter;
    }

    private void setRecyclerViewScrollListener() {
        this.layoutManagerType = null;
        RecyclerView recyclerView = (RecyclerView) this.child;
        if (recyclerView.getAdapter() != null) {
            if (recyclerView.getAdapter() instanceof RecyclerViewHolder) {
                this.mRecyclerApdater = getRecyclerApdater(recyclerView);
            } else {
                LogUtils.w(RECYCLERVIEW_ADAPTER_WARIN);
            }
        }
        recyclerView.removeOnScrollListener(this.mOnScrollListener);
        this.mOnScrollListener = new RecyclerView.OnScrollListener() { // from class: com.hy.frame.view.recycler.xRefreshView.XRefreshContentView.2
            @Override // android.support.v7.widget.RecyclerView.OnScrollListener
            public void onScrollStateChanged(RecyclerView recyclerView2, int newState) {
                super.onScrollStateChanged(recyclerView2, newState);
                if (XRefreshContentView.this.mRecyclerViewScrollListener != null) {
                    XRefreshContentView.this.mRecyclerViewScrollListener.onScrollStateChanged(recyclerView2, newState);
                }
            }

            @Override // android.support.v7.widget.RecyclerView.OnScrollListener
            public void onScrolled(RecyclerView recyclerView2, int dx, int dy) {
                if (XRefreshContentView.this.mRecyclerApdater == null && recyclerView2.getAdapter() != null && (recyclerView2.getAdapter() instanceof RecyclerViewHolder)) {
                    XRefreshContentView.this.mRecyclerApdater = XRefreshContentView.this.getRecyclerApdater(recyclerView2);
                }
                XRefreshContentView.this.onRecyclerViewScrolled(recyclerView2, XRefreshContentView.this.mRecyclerApdater, dx, dy, false);
            }
        };
        recyclerView.addOnScrollListener(this.mOnScrollListener);
    }

    public void initFooterCallBack(RecyclerViewHolder adapter, XRefreshView parent) {
        View footerView;
        if (!this.mSilenceLoadMore && adapter != null && (footerView = adapter.getCustomLoadMoreView()) != null) {
            this.mFooterCallBack = (IFooterCallBack) footerView;
            this.mFooterCallBack.onStateReady();
            this.mFooterCallBack.callWhenNotAutoLoadMore(parent);
            if (parent != null && !parent.getPullLoadEnable()) {
                this.mFooterCallBack.show(false);
            }
        }
    }

    private void doSilenceLoadMore(RecyclerViewHolder adapter, RecyclerView.LayoutManager layoutManager) {
        if (!this.mIsLoadingMore && isOnRecyclerViewBottom() && !hasLoadCompleted() && this.mRefreshViewListener != null) {
            this.mIsLoadingMore = true;
            this.mRefreshViewListener.onLoadMore(true);
        }
    }

    private void doAutoLoadMore(RecyclerViewHolder adapter, RecyclerView.LayoutManager layoutManager) {
        if (this.mIsLoadingMore || !isOnRecyclerViewBottom() || !this.mHideFooter) {
            setState(XRefreshViewState.STATE_NORMAL);
        } else {
            startLoadMore(false, adapter, layoutManager);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isFooterEnable() {
        return (this.mState == XRefreshViewState.STATE_COMPLETE || this.mParent == null || !this.mParent.getPullLoadEnable()) ? false : true;
    }

    private void doNormalLoadMore(RecyclerViewHolder adapter, RecyclerView.LayoutManager layoutManager) {
        if (this.mIsLoadingMore || !isOnRecyclerViewBottom() || !this.mHideFooter) {
            setState(XRefreshViewState.STATE_NORMAL);
        } else if (!hasLoadCompleted()) {
            doReadyState();
        } else {
            loadCompleted();
        }
    }

    public void startLoadMore(boolean silence, RecyclerViewHolder adapter, RecyclerView.LayoutManager layoutManager) {
        if (isFooterEnable() && !this.mIsLoadingMore && this.mFooterCallBack != null) {
            if (!hasLoadCompleted()) {
                this.mIsLoadingMore = true;
                this.previousTotal = this.mTotalItemCount;
                this.mFooterCallBack.onStateRefreshing();
                setState(XRefreshViewState.STATE_LOADING);
                if (this.mRefreshViewListener != null) {
                    this.mRefreshViewListener.onLoadMore(silence);
                    return;
                }
                return;
            }
            loadCompleted();
        }
    }

    public void onStateRefreshing() {
        if (this.mHasLoadComplete) {
            this.mFooterCallBack.onStateComplete();
        } else {
            this.mFooterCallBack.onStateRefreshing();
        }
    }

    public void notifyRecyclerViewLoadMore() {
        if (this.mIsLoadingMore) {
            return;
        }
        if (!hasLoadCompleted()) {
            if (this.mRefreshViewListener != null) {
                this.mRefreshViewListener.onLoadMore(false);
            }
            this.mIsLoadingMore = true;
            this.previousTotal = this.mTotalItemCount;
            this.mFooterCallBack.onStateRefreshing();
            setState(XRefreshViewState.STATE_LOADING);
            return;
        }
        loadCompleted();
    }

    public void releaseToLoadMore(boolean loadmore) {
        if (this.mFooterCallBack != null && !this.mIsLoadingMore) {
            if (loadmore) {
                if (this.mState != XRefreshViewState.STATE_RELEASE_TO_LOADMORE && !this.addingFooter) {
                    this.mFooterCallBack.onReleaseToLoadMore();
                    setState(XRefreshViewState.STATE_RELEASE_TO_LOADMORE);
                }
            } else if (this.mHideFooter) {
                doReadyState();
            } else if (this.mState != XRefreshViewState.STATE_READY) {
                this.mFooterCallBack.onStateFinish(true);
                setState(XRefreshViewState.STATE_READY);
            }
        }
    }

    private void doReadyState() {
        if (this.mState != XRefreshViewState.STATE_READY && !this.addingFooter) {
            this.mFooterCallBack.onStateReady();
            setState(XRefreshViewState.STATE_READY);
        }
    }

    public void notifyDatasetChanged() {
        RecyclerViewHolder adapter;
        if (isRecyclerView() && (adapter = getRecyclerViewAdapter((RecyclerView) this.child)) != null) {
            adapter.notifyDataSetChanged();
        }
    }

    private boolean onRecyclerViewTop() {
        return isTop() && this.mFooterCallBack != null && isFooterEnable();
    }

    public void setLoadComplete(boolean hasComplete) {
        this.mHasLoadComplete = hasComplete;
        this.mIsLoadingMore = false;
        this.hasIntercepted = false;
        if (!hasComplete && this.isHideFooterWhenComplete && this.mParent != null && this.mParent.getPullLoadEnable()) {
            addFooterView(true);
        }
        resetLayout();
        if (isRecyclerView()) {
            doRecyclerViewloadComplete(hasComplete);
        }
    }

    private void doRecyclerViewloadComplete(boolean hasComplete) {
        if (this.mFooterCallBack != null && isFooterEnable()) {
            RecyclerView recyclerView = (RecyclerView) this.child;
            if (hasComplete) {
                this.mHideFooter = true;
                this.mFooterCallBack.onStateFinish(true);
                if (!Utils.isRecyclerViewFullscreen(recyclerView)) {
                    this.child.postDelayed(new Runnable() { // from class: com.hy.frame.view.recycler.xRefreshView.XRefreshContentView.3
                        @Override // java.lang.Runnable
                        public void run() {
                            XRefreshContentView.this.loadCompleted();
                        }
                    }, 200L);
                    return;
                }
                int i = this.mTotalItemCount;
                getRecyclerViewInfo(recyclerView.getLayoutManager());
                RecyclerViewHolder adapter = getRecyclerViewAdapter(recyclerView);
                if (adapter != null) {
                    onRecyclerViewScrolled(recyclerView, adapter, 0, 0, true);
                }
            } else if (recyclerView != null && this.mFooterCallBack != null) {
                if (!Utils.isRecyclerViewFullscreen(recyclerView)) {
                    this.mFooterCallBack.onStateReady();
                    this.mFooterCallBack.callWhenNotAutoLoadMore(this.mParent);
                    if (!this.mFooterCallBack.isShowing()) {
                        this.mFooterCallBack.show(true);
                        return;
                    }
                    return;
                }
                doReadyState();
            }
        }
    }

    private RecyclerViewHolder getRecyclerViewAdapter(RecyclerView recyclerView) {
        if (recyclerView != null) {
            RecyclerView.Adapter adapter = recyclerView.getAdapter();
            if (adapter instanceof RecyclerViewHolder) {
                return (RecyclerViewHolder) adapter;
            }
            LogUtils.w(RECYCLERVIEW_ADAPTER_WARIN);
        }
        return null;
    }

    public void stopLoading(boolean hideFooter) {
        this.mIsLoadingMore = false;
        if (hideFooter && isRecyclerView()) {
            if (((RecyclerViewHolder) ((RecyclerView) this.child).getAdapter()) != null) {
                addFooterView(false);
                resetLayout();
                addFooterView(true);
            } else {
                return;
            }
        }
        this.mHideFooter = hideFooter;
        this.mState = XRefreshViewState.STATE_FINISHED;
    }

    private boolean isOnRecyclerViewBottom() {
        return (this.mTotalItemCount + (-1)) - this.mPreLoadCount <= this.mLastVisibleItemPosition;
    }

    public void ensureFooterShowWhenScrolling() {
        if (isFooterEnable() && this.mFooterCallBack != null && !this.mFooterCallBack.isShowing()) {
            this.mFooterCallBack.show(true);
        }
    }

    private void refreshAdapter(RecyclerViewHolder adapter, RecyclerView.LayoutManager manager) {
        View footerView;
        if (adapter != null && manager != null && !this.mRefreshAdapter && !hasLoadCompleted() && !(manager instanceof GridLayoutManager) && (footerView = adapter.getCustomLoadMoreView()) != null) {
            ViewGroup.LayoutParams layoutParams = footerView.getLayoutParams();
            if (layoutParams instanceof StaggeredGridLayoutManager.LayoutParams) {
                Utils.setFullSpan((StaggeredGridLayoutManager.LayoutParams) layoutParams);
                this.mRefreshAdapter = true;
            }
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public void getRecyclerViewInfo(RecyclerView.LayoutManager layoutManager) {
        int[] lastPositions = null;
        if (this.layoutManagerType == null) {
            if (layoutManager instanceof GridLayoutManager) {
                this.layoutManagerType = LAYOUT_MANAGER_TYPE.GRID;
            } else if (layoutManager instanceof LinearLayoutManager) {
                this.layoutManagerType = LAYOUT_MANAGER_TYPE.LINEAR;
            } else if (layoutManager instanceof StaggeredGridLayoutManager) {
                this.layoutManagerType = LAYOUT_MANAGER_TYPE.STAGGERED_GRID;
            } else {
                throw new RuntimeException("Unsupported LayoutManager used. Valid ones are LinearLayoutManager, GridLayoutManager and StaggeredGridLayoutManager");
            }
        }
        this.mTotalItemCount = layoutManager.getItemCount();
        switch (this.layoutManagerType) {
            case LINEAR:
                this.mVisibleItemCount = layoutManager.getChildCount();
                this.mLastVisibleItemPosition = ((LinearLayoutManager) layoutManager).findLastVisibleItemPosition();
                break;
            case GRID:
                break;
            case STAGGERED_GRID:
                StaggeredGridLayoutManager staggeredGridLayoutManager = (StaggeredGridLayoutManager) layoutManager;
                if (0 == 0) {
                    lastPositions = new int[staggeredGridLayoutManager.getSpanCount()];
                }
                staggeredGridLayoutManager.findLastVisibleItemPositions(lastPositions);
                this.mLastVisibleItemPosition = findMax(lastPositions);
                staggeredGridLayoutManager.findFirstVisibleItemPositions(lastPositions);
                this.mFirstVisibleItem = findMin(lastPositions);
                return;
            default:
                return;
        }
        this.mLastVisibleItemPosition = ((LinearLayoutManager) layoutManager).findLastVisibleItemPosition();
        this.mFirstVisibleItem = ((LinearLayoutManager) layoutManager).findFirstVisibleItemPosition();
    }

    public void setPreLoadCount(int count) {
        if (count < 0) {
            count = 0;
        }
        this.mPreLoadCount = count;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void setHideFooterWhenComplete(boolean isHideFooterWhenComplete) {
        this.isHideFooterWhenComplete = isHideFooterWhenComplete;
    }

    public void loadCompleted() {
        int i = 1000;
        this.mParent.enablePullUp(true);
        if (this.mState != XRefreshViewState.STATE_COMPLETE) {
            this.mFooterCallBack.onStateComplete();
            setState(XRefreshViewState.STATE_COMPLETE);
            if (this.mPinnedTime >= 1000) {
                i = this.mPinnedTime;
            }
            this.mPinnedTime = i;
            if (this.isHideFooterWhenComplete) {
                this.child.postDelayed(new Runnable() { // from class: com.hy.frame.view.recycler.xRefreshView.XRefreshContentView.4
                    @Override // java.lang.Runnable
                    public void run() {
                        XRefreshContentView.this.resetLayout();
                        XRefreshContentView.this.addFooterView(true);
                    }
                }, this.mPinnedTime);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void resetLayout() {
        if (this.mParent != null) {
            this.mParent.resetLayout();
        }
    }

    private void setState(XRefreshViewState state) {
        if (this.mState != XRefreshViewState.STATE_COMPLETE) {
            this.mState = state;
        }
    }

    public XRefreshViewState getState() {
        return this.mState;
    }

    public boolean hasLoadCompleted() {
        return this.mHasLoadComplete;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addFooterView(boolean add) {
        if (this.child instanceof RecyclerView) {
            final RecyclerView recyclerView = (RecyclerView) this.child;
            final RecyclerViewHolder adapter = getRecyclerViewAdapter(recyclerView);
            if (adapter != null && this.mFooterCallBack != null) {
                if (add) {
                    this.addingFooter = true;
                    recyclerView.post(new Runnable() { // from class: com.hy.frame.view.recycler.xRefreshView.XRefreshContentView.5
                        @Override // java.lang.Runnable
                        public void run() {
                            if (recyclerView.indexOfChild(adapter.getCustomLoadMoreView()) == -1) {
                                XRefreshContentView.this.addingFooter = false;
                                if (XRefreshContentView.this.isFooterEnable()) {
                                    adapter.addFooterView();
                                    return;
                                }
                                return;
                            }
                            recyclerView.post(this);
                        }
                    });
                    return;
                }
                adapter.removeFooterView();
            }
        } else if (this.mFooterCallBack != null) {
            this.mFooterCallBack.show(add);
        }
    }

    public void setEnablePullLoad(boolean enablePullLoad) {
        RecyclerViewHolder adapter;
        addFooterView(enablePullLoad);
        this.hasIntercepted = false;
        this.mIsLoadingMore = false;
        if (enablePullLoad) {
            dealRecyclerViewNotFullScreen();
        }
        if (isRecyclerView() && (adapter = getRecyclerViewAdapter((RecyclerView) this.child)) != null) {
            adapter.insideEnableFooter(enablePullLoad);
        }
    }

    private void dealRecyclerViewNotFullScreen() {
        RecyclerView recyclerView = (RecyclerView) this.child;
        if (onRecyclerViewTop() && !Utils.isRecyclerViewFullscreen(recyclerView) && (this.child instanceof RecyclerView) && this.mFooterCallBack != null && isFooterEnable()) {
            this.mFooterCallBack.onStateReady();
            this.mFooterCallBack.callWhenNotAutoLoadMore(this.mParent);
            if (!this.mFooterCallBack.isShowing()) {
                this.mFooterCallBack.show(true);
            }
        }
    }

    public void setPinnedTime(int pinnedTime) {
        this.mPinnedTime = pinnedTime;
    }

    public void setOnAbsListViewScrollListener(AbsListView.OnScrollListener listener) {
        this.mAbsListViewScrollListener = listener;
    }

    public void setOnRecyclerViewScrollListener(RecyclerView.OnScrollListener listener) {
        this.mRecyclerViewScrollListener = listener;
    }

    public void setXRefreshViewListener(XRefreshViewListener refreshViewListener) {
        this.mRefreshViewListener = refreshViewListener;
    }

    @Override // com.hy.frame.view.recycler.xRefreshView.OnTopRefreshTime
    public boolean isTop() {
        return this.mTopRefreshTime != null ? this.mTopRefreshTime.isTop() : hasChildOnTop();
    }

    @Override // com.hy.frame.view.recycler.xRefreshView.OnBottomLoadMoreTime
    public boolean isBottom() {
        return this.mBottomLoadMoreTime != null ? this.mBottomLoadMoreTime.isBottom() : hasChildOnBottom();
    }

    public void setOnTopRefreshTime(OnTopRefreshTime topRefreshTime) {
        this.mTopRefreshTime = topRefreshTime;
    }

    public void setOnBottomLoadMoreTime(OnBottomLoadMoreTime bottomLoadMoreTime) {
        this.mBottomLoadMoreTime = bottomLoadMoreTime;
    }

    @Override // android.widget.AbsListView.OnScrollListener
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        if (this.mParent.isStopLoadMore() && scrollState == 2) {
            this.isForbidLoadMore = true;
        }
        if (!this.isForbidLoadMore) {
            if (this.mSilenceLoadMore) {
                if (this.mRefreshViewListener != null && !hasLoadCompleted() && !this.mIsLoadingMore && this.mTotalItemCount - 1 <= view.getLastVisiblePosition() + this.mPreLoadCount) {
                    this.mRefreshViewListener.onLoadMore(true);
                    this.mIsLoadingMore = true;
                }
            } else if (this.mContainer != null && !hasLoadCompleted() && scrollState == 0) {
                if (this.mPreLoadCount == 0) {
                    if (isBottom() && !this.mIsLoadingMore) {
                        this.mIsLoadingMore = this.mContainer.invokeLoadMore();
                    }
                } else if (this.mTotalItemCount - 1 <= view.getLastVisiblePosition() + this.mPreLoadCount && !this.mIsLoadingMore) {
                    this.mIsLoadingMore = this.mContainer.invokeLoadMore();
                }
            }
            if (this.mAbsListViewScrollListener != null) {
                this.mAbsListViewScrollListener.onScrollStateChanged(view, scrollState);
            }
        } else if (!this.mParent.isStopLoadMore() && scrollState == 0) {
            this.isForbidLoadMore = false;
        }
    }

    @Override // android.widget.AbsListView.OnScrollListener
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        this.mTotalItemCount = totalItemCount;
        if (this.mAbsListViewScrollListener != null) {
            this.mAbsListViewScrollListener.onScroll(view, firstVisibleItem, visibleItemCount, totalItemCount);
        }
    }

    public int getTotalItemCount() {
        return this.mTotalItemCount;
    }

    public boolean hasChildOnTop() {
        return !canChildPullDown();
    }

    public boolean hasChildOnBottom() {
        return !canChildPullUp();
    }

    public boolean isLoading() {
        if (this.mSilenceLoadMore) {
            return false;
        }
        return this.mIsLoadingMore;
    }

    public boolean canChildPullDown() {
        if (!(this.child instanceof AbsListView)) {
            return canScrollVertically(this.child, -1) || this.child.getScrollY() > 0;
        }
        AbsListView absListView = (AbsListView) this.child;
        if (!canScrollVertically(this.child, -1)) {
            if (absListView.getChildCount() <= 0) {
                return false;
            }
            if (absListView.getFirstVisiblePosition() <= 0 && absListView.getChildAt(0).getTop() >= absListView.getPaddingTop()) {
                return false;
            }
        }
        return true;
    }

    public boolean canChildPullUp() {
        boolean z = false;
        if (this.child instanceof AbsListView) {
            AbsListView absListView = (AbsListView) this.child;
            if (canScrollVertically(this.child, 1) || absListView.getLastVisiblePosition() != this.mTotalItemCount - 1) {
                z = true;
            }
            return z;
        } else if (this.child instanceof WebView) {
            WebView webview = (WebView) this.child;
            return webview instanceof XWebView ? !((XWebView) webview).isBottom() : ((float) webview.getContentHeight()) * webview.getScale() != ((float) (webview.getHeight() + webview.getScrollY()));
        } else if (!(this.child instanceof ScrollView)) {
            return canScrollVertically(this.child, 1);
        } else {
            ScrollView scrollView = (ScrollView) this.child;
            View childView = scrollView.getChildAt(0);
            if (childView == null) {
                return true;
            }
            if (canScrollVertically(this.child, 1) || scrollView.getScrollY() < childView.getHeight() - scrollView.getHeight()) {
                z = true;
            }
            return z;
        }
    }

    public boolean canScrollVertically(View view, int direction) {
        return ViewCompat.canScrollVertically(view, direction);
    }

    public void offsetTopAndBottom(int offset) {
        this.child.offsetTopAndBottom(offset);
    }

    public boolean isRecyclerView() {
        if (this.mSilenceLoadMore || this.child == null || !(this.child instanceof RecyclerView)) {
            return false;
        }
        RecyclerView recyclerView = (RecyclerView) this.child;
        if (recyclerView.getAdapter() == null || (recyclerView.getAdapter() instanceof RecyclerViewHolder)) {
            return true;
        }
        return false;
    }

    private int findMax(int[] lastPositions) {
        int max = Integer.MIN_VALUE;
        for (int value : lastPositions) {
            if (value > max) {
                max = value;
            }
        }
        return max;
    }

    private int findMin(int[] lastPositions) {
        int min = Integer.MAX_VALUE;
        for (int value : lastPositions) {
            if (value != -1 && value < min) {
                min = value;
            }
        }
        return min;
    }
}
