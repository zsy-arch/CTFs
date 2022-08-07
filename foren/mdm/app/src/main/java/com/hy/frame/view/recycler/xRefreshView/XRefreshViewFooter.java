package com.hy.frame.view.recycler.xRefreshView;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.hy.frame.R;

/* loaded from: classes2.dex */
public class XRefreshViewFooter extends LinearLayout implements IFooterCallBack {
    private TextView mClickView;
    private View mContentView;
    private Context mContext;
    private TextView mHintView;
    private View mProgressBar;
    private boolean showing = true;

    public XRefreshViewFooter(Context context) {
        super(context);
        initView(context);
    }

    public XRefreshViewFooter(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    @Override // com.hy.frame.view.recycler.xRefreshView.IFooterCallBack
    public void callWhenNotAutoLoadMore(final XRefreshView xRefreshView) {
        this.mClickView.setText(R.string.xrefreshview_footer_hint_click);
        this.mClickView.setOnClickListener(new View.OnClickListener() { // from class: com.hy.frame.view.recycler.xRefreshView.XRefreshViewFooter.1
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                xRefreshView.notifyLoadMore();
            }
        });
    }

    @Override // com.hy.frame.view.recycler.xRefreshView.IFooterCallBack
    public void onStateReady() {
        this.mHintView.setVisibility(8);
        this.mProgressBar.setVisibility(8);
        this.mClickView.setText(R.string.xrefreshview_footer_hint_click);
        this.mClickView.setVisibility(0);
    }

    @Override // com.hy.frame.view.recycler.xRefreshView.IFooterCallBack
    public void onStateRefreshing() {
        this.mHintView.setVisibility(8);
        this.mProgressBar.setVisibility(0);
        this.mClickView.setVisibility(8);
    }

    @Override // com.hy.frame.view.recycler.xRefreshView.IFooterCallBack
    public void onReleaseToLoadMore() {
        this.mHintView.setVisibility(8);
        this.mProgressBar.setVisibility(8);
        this.mClickView.setText(R.string.xrefreshview_footer_hint_release);
        this.mClickView.setVisibility(0);
    }

    @Override // com.hy.frame.view.recycler.xRefreshView.IFooterCallBack
    public void onStateFinish(boolean success) {
        if (success) {
            this.mHintView.setText(R.string.xrefreshview_footer_hint_normal);
        } else {
            this.mHintView.setText(R.string.xrefreshview_footer_hint_fail);
        }
        this.mHintView.setVisibility(0);
        this.mProgressBar.setVisibility(8);
        this.mClickView.setVisibility(8);
    }

    @Override // com.hy.frame.view.recycler.xRefreshView.IFooterCallBack
    public void onStateComplete() {
        this.mHintView.setText(R.string.xrefreshview_footer_hint_complete);
        this.mHintView.setVisibility(0);
        this.mProgressBar.setVisibility(8);
        this.mClickView.setVisibility(8);
    }

    @Override // com.hy.frame.view.recycler.xRefreshView.IFooterCallBack
    public void show(boolean show) {
        int i = 0;
        if (show != this.showing) {
            this.showing = show;
            LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) this.mContentView.getLayoutParams();
            lp.height = show ? -2 : 0;
            this.mContentView.setLayoutParams(lp);
            if (!show) {
                i = 8;
            }
            setVisibility(i);
        }
    }

    @Override // com.hy.frame.view.recycler.xRefreshView.IFooterCallBack
    public boolean isShowing() {
        return this.showing;
    }

    private void initView(Context context) {
        this.mContext = context;
        ViewGroup moreView = (ViewGroup) LayoutInflater.from(this.mContext).inflate(R.layout.xrefreshview_footer, this);
        moreView.setLayoutParams(new LinearLayout.LayoutParams(-1, -2));
        this.mContentView = moreView.findViewById(R.id.xrefreshview_footer_content);
        this.mProgressBar = moreView.findViewById(R.id.xrefreshview_footer_progressbar);
        this.mHintView = (TextView) moreView.findViewById(R.id.xrefreshview_footer_hint_textview);
        this.mClickView = (TextView) moreView.findViewById(R.id.xrefreshview_footer_click_textview);
    }

    @Override // com.hy.frame.view.recycler.xRefreshView.IFooterCallBack
    public int getFooterHeight() {
        return getMeasuredHeight();
    }
}
