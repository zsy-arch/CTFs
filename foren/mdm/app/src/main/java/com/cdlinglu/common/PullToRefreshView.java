package com.cdlinglu.common;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;
import com.vsf2f.f2f.R;
import java.util.Date;

/* loaded from: classes.dex */
public class PullToRefreshView extends LinearLayout {
    private AdapterView<?> mAdapterView;
    private RotateAnimation mFlipAnimation;
    private ImageView mFooterImageView;
    private ProgressBar mFooterProgressBar;
    private int mFooterState;
    private TextView mFooterTextView;
    private View mFooterView;
    private int mFooterViewHeight;
    private ImageView mHeaderImageView;
    private ProgressBar mHeaderProgressBar;
    private int mHeaderState;
    private TextView mHeaderTextView;
    private TextView mHeaderUpdateTextView;
    private View mHeaderView;
    private int mHeaderViewHeight;
    private LayoutInflater mInflater;
    private int mLastMotionY;
    private boolean mLock;
    private OnFooterRefreshListener mOnFooterRefreshListener;
    private OnHeaderRefreshListener mOnHeaderRefreshListener;
    private int mPullState;
    private RotateAnimation mReverseFlipAnimation;
    private ScrollView mScrollView;
    private int rlHeight;
    private final String TAG = "PullToRefreshView";
    private final int PULL_TO_REFRESH = 2;
    private final int RELEASE_TO_REFRESH = 3;
    private final int REFRESHING = 4;
    private final int PULL_UP_STATE = 0;
    private final int PULL_DOWN_STATE = 1;
    private boolean enablePullTorefresh = true;
    private boolean enablePullLoadMoreDataStatus = true;
    private boolean stopFunction = false;

    /* loaded from: classes.dex */
    public interface OnFooterRefreshListener {
        void onFooterRefresh(PullToRefreshView pullToRefreshView);
    }

    /* loaded from: classes.dex */
    public interface OnHeaderRefreshListener {
        void onHeaderRefresh(PullToRefreshView pullToRefreshView);
    }

    public PullToRefreshView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public PullToRefreshView(Context context) {
        super(context);
        init();
    }

    private void init() {
        this.mInflater = LayoutInflater.from(getContext());
        this.mFlipAnimation = new RotateAnimation(0.0f, -180.0f, 1, 0.5f, 1, 0.5f);
        this.mFlipAnimation.setInterpolator(new LinearInterpolator());
        this.mFlipAnimation.setDuration(250L);
        this.mFlipAnimation.setFillAfter(true);
        this.mReverseFlipAnimation = new RotateAnimation(-180.0f, 0.0f, 1, 0.5f, 1, 0.5f);
        this.mReverseFlipAnimation.setInterpolator(new LinearInterpolator());
        this.mReverseFlipAnimation.setDuration(250L);
        this.mReverseFlipAnimation.setFillAfter(true);
        addHeaderView();
    }

    public void setStopFunction(boolean stopFunction) {
        this.stopFunction = stopFunction;
    }

    private void addHeaderView() {
        this.mHeaderView = this.mInflater.inflate(R.layout.refresh_header, (ViewGroup) this, false);
        this.mHeaderTextView = (TextView) this.mHeaderView.findViewById(R.id.refresh_header_txtHint);
        this.mHeaderImageView = (ImageView) this.mHeaderView.findViewById(R.id.refresh_header_imgArrow);
        this.mHeaderUpdateTextView = (TextView) this.mHeaderView.findViewById(R.id.refresh_header_txtTime);
        this.mHeaderProgressBar = (ProgressBar) this.mHeaderView.findViewById(R.id.refresh_header_progressBar);
        measureView(this.mHeaderView);
        this.mHeaderViewHeight = this.mHeaderView.getMeasuredHeight();
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(-1, this.mHeaderViewHeight);
        params.topMargin = -this.mHeaderViewHeight;
        addView(this.mHeaderView, params);
    }

    public void setNull() {
        clearAnimation();
        this.mReverseFlipAnimation = null;
        this.mFlipAnimation = null;
        removeView(this.mFooterView);
        removeView(this.mHeaderView);
        this.mFooterProgressBar = null;
        this.mFooterTextView = null;
        this.mScrollView = null;
        this.mHeaderView = null;
        this.mFooterView = null;
        this.mAdapterView = null;
        this.mHeaderImageView = null;
        this.mFooterImageView = null;
        this.mHeaderTextView = null;
        this.mHeaderUpdateTextView = null;
        this.mHeaderProgressBar = null;
        this.mOnFooterRefreshListener = null;
        this.mOnHeaderRefreshListener = null;
    }

    private void addFooterView() {
        this.mFooterView = this.mInflater.inflate(R.layout.refresh_footer, (ViewGroup) this, false);
        this.mFooterView.setVisibility(8);
        this.mFooterTextView = (TextView) this.mFooterView.findViewById(R.id.refresh_footer_txtHint);
        this.mFooterImageView = (ImageView) this.mFooterView.findViewById(R.id.refresh_footer_imgArrow);
        this.mFooterProgressBar = (ProgressBar) this.mFooterView.findViewById(R.id.refresh_footer_progressBar);
        measureView(this.mFooterView);
        this.mFooterViewHeight = this.mFooterView.getMeasuredHeight();
        addView(this.mFooterView, new LinearLayout.LayoutParams(-1, this.mFooterViewHeight));
    }

    @Override // android.view.View
    protected void onFinishInflate() {
        super.onFinishInflate();
        addFooterView();
        initContentAdapterView();
    }

    private void initContentAdapterView() {
        int count = getChildCount();
        if (count < 3) {
            throw new IllegalArgumentException("this layout must contain 3 child views,and AdapterView or ScrollView must in the second position!");
        }
        for (int i = 0; i < count - 1; i++) {
            View view = getChildAt(i);
            if (view instanceof AdapterView) {
                this.mAdapterView = (AdapterView) view;
            }
            if (view instanceof ScrollView) {
                this.mScrollView = (ScrollView) view;
            }
        }
        if (this.mAdapterView == null && this.mScrollView == null) {
            throw new IllegalArgumentException("must contain a AdapterView or ScrollView in this layout!");
        }
    }

    private void measureView(View child) {
        int childHeightSpec;
        ViewGroup.LayoutParams p = child.getLayoutParams();
        if (p == null) {
            p = new ViewGroup.LayoutParams(-1, -2);
        }
        int childWidthSpec = ViewGroup.getChildMeasureSpec(0, 0, p.width);
        int lpHeight = p.height;
        if (lpHeight > 0) {
            childHeightSpec = View.MeasureSpec.makeMeasureSpec(lpHeight, 1073741824);
        } else {
            childHeightSpec = View.MeasureSpec.makeMeasureSpec(0, 0);
        }
        child.measure(childWidthSpec, childHeightSpec);
    }

    @Override // android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent e) {
        if (this.stopFunction) {
            return true;
        }
        int y = (int) e.getRawY();
        switch (e.getAction()) {
            case 0:
                this.mLastMotionY = y;
                break;
            case 1:
            case 3:
                int deltaY1 = y - this.mLastMotionY;
                if ((deltaY1 < 20 && deltaY1 > 0) || (deltaY1 < 0 && deltaY1 > -20)) {
                    return false;
                }
                if (isRefreshViewScroll(deltaY1)) {
                    return true;
                }
                break;
            case 2:
                int deltaY = y - this.mLastMotionY;
                if ((deltaY < 20 && deltaY > 0) || (deltaY < 0 && deltaY > -20)) {
                    return false;
                }
                if (isRefreshViewScroll(deltaY)) {
                    return true;
                }
                break;
        }
        return false;
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent event) {
        if (this.stopFunction || this.mLock) {
            return true;
        }
        int y = (int) event.getRawY();
        switch (event.getAction()) {
            case 1:
            case 3:
                int topMargin = getHeaderTopMargin();
                if (this.mPullState != 1) {
                    if (this.mPullState == 0) {
                        if (Math.abs(topMargin) < this.mHeaderViewHeight + this.mFooterViewHeight) {
                            setHeaderTopMargin(-this.mHeaderViewHeight);
                            break;
                        } else {
                            footerRefreshing();
                            break;
                        }
                    }
                } else if (topMargin < 0) {
                    setHeaderTopMargin(-this.mHeaderViewHeight);
                    break;
                } else {
                    headerRefreshing();
                    break;
                }
                break;
            case 2:
                int deltaY = y - this.mLastMotionY;
                if (this.mPullState == 1) {
                    headerPrepareToRefresh(deltaY);
                } else if (this.mPullState == 0) {
                    footerPrepareToRefresh(deltaY);
                }
                this.mLastMotionY = y;
                break;
        }
        return super.onTouchEvent(event);
    }

    private boolean isRefreshViewScroll(int deltaY) {
        View lastChild;
        if (this.mHeaderState == 4 || this.mFooterState == 4) {
            return false;
        }
        if (this.mAdapterView != null) {
            if (deltaY > 0) {
                View child = this.mAdapterView.getChildAt(0);
                int pp = this.mAdapterView.getFirstVisiblePosition();
                if (!this.enablePullTorefresh || child == null) {
                    return false;
                }
                int tt = child.getTop();
                if (pp == 0 && tt >= 0) {
                    this.mPullState = 1;
                    return true;
                }
            } else if (deltaY < 0) {
                if (!this.enablePullLoadMoreDataStatus || (lastChild = this.mAdapterView.getChildAt(this.mAdapterView.getChildCount() - 1)) == null) {
                    return false;
                }
                if (lastChild.getBottom() <= getHeight() && this.mAdapterView.getLastVisiblePosition() == this.mAdapterView.getCount() - 1) {
                    this.mPullState = 0;
                    return true;
                }
            }
        }
        if (this.mScrollView == null) {
            return false;
        }
        View child2 = this.mScrollView.getChildAt(0);
        if (deltaY > 0 && this.mScrollView.getScrollY() == 0) {
            this.mPullState = 1;
            return true;
        } else if (deltaY >= 0 || child2.getMeasuredHeight() > getHeight() + this.mScrollView.getScrollY()) {
            return false;
        } else {
            this.mPullState = 0;
            return true;
        }
    }

    private void headerPrepareToRefresh(int deltaY) {
        int newTopMargin = changingHeaderViewTopMargin(deltaY);
        if (newTopMargin >= 0 && this.mHeaderState != 3) {
            this.mHeaderTextView.setText(R.string.pull_to_refresh_release_label);
            this.mHeaderUpdateTextView.setVisibility(8);
            this.mHeaderImageView.clearAnimation();
            this.mHeaderImageView.startAnimation(this.mFlipAnimation);
            this.mHeaderState = 3;
        } else if (newTopMargin < 0 && newTopMargin > (-this.mHeaderViewHeight)) {
            this.mHeaderImageView.clearAnimation();
            this.mHeaderImageView.startAnimation(this.mFlipAnimation);
            this.mHeaderTextView.setText(R.string.pull_to_refresh_pull_label);
            this.mHeaderState = 2;
        }
    }

    private void footerPrepareToRefresh(int deltaY) {
        int newTopMargin = changingHeaderViewTopMargin(deltaY);
        this.mFooterView.setVisibility(0);
        if (Math.abs(newTopMargin) >= this.mHeaderViewHeight + this.mFooterViewHeight && this.mFooterState != 3) {
            this.mFooterTextView.setText(R.string.pull_to_refresh_footer_release_label);
            this.mFooterImageView.clearAnimation();
            this.mFooterImageView.startAnimation(this.mFlipAnimation);
            this.mFooterState = 3;
        } else if (Math.abs(newTopMargin) < this.mHeaderViewHeight + this.mFooterViewHeight) {
            this.mFooterImageView.clearAnimation();
            this.mFooterImageView.startAnimation(this.mFlipAnimation);
            this.mFooterTextView.setText(R.string.pull_to_refresh_footer_pull_label);
            this.mFooterState = 2;
        }
    }

    private void changExtraView(int deltaY) {
    }

    private int changingHeaderViewTopMargin(int deltaY) {
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) this.mHeaderView.getLayoutParams();
        float newTopMargin = params.topMargin + (deltaY * 0.3f);
        if (deltaY > 0 && this.mPullState == 0 && Math.abs(params.topMargin) <= this.mHeaderViewHeight) {
            return params.topMargin;
        }
        if (deltaY < 0 && this.mPullState == 1 && Math.abs(params.topMargin) >= this.mHeaderViewHeight) {
            return params.topMargin;
        }
        params.topMargin = (int) newTopMargin;
        this.mHeaderView.setLayoutParams(params);
        invalidate();
        return params.topMargin;
    }

    public void headerRefreshing() {
        this.mHeaderState = 4;
        setHeaderTopMargin(0);
        this.mHeaderImageView.setVisibility(8);
        this.mHeaderImageView.clearAnimation();
        this.mHeaderImageView.setImageDrawable(null);
        this.mHeaderProgressBar.setVisibility(0);
        this.mHeaderTextView.setText(R.string.pull_to_refresh_refreshing_label);
        if (this.mOnHeaderRefreshListener != null) {
            this.mOnHeaderRefreshListener.onHeaderRefresh(this);
        }
    }

    private void footerRefreshing() {
        this.mFooterState = 4;
        setHeaderTopMargin(-(this.mHeaderViewHeight + this.mFooterViewHeight));
        this.mFooterImageView.setVisibility(8);
        this.mFooterImageView.clearAnimation();
        this.mFooterImageView.setImageDrawable(null);
        this.mFooterProgressBar.setVisibility(0);
        this.mFooterTextView.setText(R.string.pull_to_refresh_footer_refreshing_label);
        if (this.mOnFooterRefreshListener != null) {
            this.mOnFooterRefreshListener.onFooterRefresh(this);
        }
    }

    private void setHeaderTopMargin(int topMargin) {
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) this.mHeaderView.getLayoutParams();
        params.topMargin = topMargin;
        this.mHeaderView.setLayoutParams(params);
        invalidate();
    }

    public void onHeaderRefreshComplete() {
        setHeaderTopMargin(-this.mHeaderViewHeight);
        this.mHeaderImageView.setVisibility(8);
        this.mHeaderImageView.setImageResource(R.drawable.refresh_arrow_down);
        this.mHeaderTextView.setText(R.string.pull_to_refresh_pull_label);
        this.mHeaderProgressBar.setVisibility(8);
        this.mHeaderState = 2;
        setLastUpdated("最近更新:" + new Date().toLocaleString());
    }

    public void onHeaderRefreshComplete(CharSequence lastUpdated) {
        setLastUpdated(lastUpdated);
        onHeaderRefreshComplete();
    }

    public void onFooterRefreshComplete() {
        setHeaderTopMargin(-this.mHeaderViewHeight);
        this.mFooterView.setVisibility(4);
        this.mFooterImageView.setVisibility(8);
        this.mFooterImageView.setImageResource(R.drawable.refresh_arrow_up);
        this.mFooterTextView.setText(R.string.pull_to_refresh_footer_pull_label);
        this.mFooterProgressBar.setVisibility(8);
        this.mFooterState = 2;
    }

    public void onFooterRefreshComplete(int size) {
        if (size > 0) {
            this.mFooterView.setVisibility(0);
        } else {
            this.mFooterView.setVisibility(8);
        }
        setHeaderTopMargin(-this.mHeaderViewHeight);
        this.mFooterImageView.setVisibility(8);
        this.mFooterImageView.setImageResource(R.drawable.refresh_arrow_up);
        this.mFooterTextView.setText(R.string.pull_to_refresh_footer_pull_label);
        this.mFooterProgressBar.setVisibility(8);
        this.mFooterState = 2;
    }

    public void setLastUpdated(CharSequence lastUpdated) {
        if (lastUpdated != null) {
            this.mHeaderUpdateTextView.setVisibility(8);
            this.mHeaderUpdateTextView.setText(lastUpdated);
            return;
        }
        this.mHeaderUpdateTextView.setVisibility(8);
    }

    private int getHeaderTopMargin() {
        return ((LinearLayout.LayoutParams) this.mHeaderView.getLayoutParams()).topMargin;
    }

    public void setOnHeaderRefreshListener(OnHeaderRefreshListener headerRefreshListener) {
        this.mOnHeaderRefreshListener = headerRefreshListener;
    }

    public void setOnFooterRefreshListener(OnFooterRefreshListener footerRefreshListener) {
        this.mOnFooterRefreshListener = footerRefreshListener;
    }

    public boolean isEnablePullTorefresh() {
        return this.enablePullTorefresh;
    }

    public void setEnablePullTorefresh(boolean enablePullTorefresh) {
        this.enablePullTorefresh = enablePullTorefresh;
    }

    public boolean isEnablePullLoadMoreDataStatus() {
        return this.enablePullLoadMoreDataStatus;
    }

    public void setEnablePullLoadMoreDataStatus(boolean enablePullLoadMoreDataStatus) {
        this.enablePullLoadMoreDataStatus = enablePullLoadMoreDataStatus;
    }
}
