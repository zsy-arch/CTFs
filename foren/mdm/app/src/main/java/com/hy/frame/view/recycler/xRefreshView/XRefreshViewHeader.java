package com.hy.frame.view.recycler.xRefreshView;

import android.content.Context;
import android.content.res.Resources;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.hy.frame.R;
import java.util.Calendar;

/* loaded from: classes2.dex */
public class XRefreshViewHeader extends LinearLayout implements IHeaderCallBack {
    private final int ROTATE_ANIM_DURATION = 180;
    private ImageView mArrowImageView;
    private ViewGroup mContent;
    private TextView mHeaderTimeTextView;
    private TextView mHintTextView;
    private ImageView mOkImageView;
    private ProgressBar mProgressBar;
    private Animation mRotateDownAnim;
    private Animation mRotateUpAnim;

    public XRefreshViewHeader(Context context) {
        super(context);
        initView(context);
    }

    public XRefreshViewHeader(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    private void initView(Context context) {
        this.mContent = (ViewGroup) LayoutInflater.from(context).inflate(R.layout.xrefreshview_header, this);
        this.mArrowImageView = (ImageView) this.mContent.findViewById(R.id.xrefreshview_header_arrow);
        this.mOkImageView = (ImageView) this.mContent.findViewById(R.id.xrefreshview_header_ok);
        this.mHintTextView = (TextView) this.mContent.findViewById(R.id.xrefreshview_header_hint_textview);
        this.mHeaderTimeTextView = (TextView) this.mContent.findViewById(R.id.xrefreshview_header_time);
        this.mProgressBar = (ProgressBar) this.mContent.findViewById(R.id.xrefreshview_header_progressbar);
        this.mRotateUpAnim = new RotateAnimation(0.0f, -180.0f, 1, 0.5f, 1, 0.5f);
        this.mRotateUpAnim.setDuration(180L);
        this.mRotateUpAnim.setFillAfter(true);
        this.mRotateDownAnim = new RotateAnimation(-180.0f, 0.0f, 1, 0.5f, 1, 0.5f);
        this.mRotateDownAnim.setDuration(0L);
        this.mRotateDownAnim.setFillAfter(true);
    }

    @Override // com.hy.frame.view.recycler.xRefreshView.IHeaderCallBack
    public void setRefreshTime(long lastRefreshTime) {
        String refreshTimeText;
        int minutes = (int) (((Calendar.getInstance().getTimeInMillis() - lastRefreshTime) / 1000) / 60);
        Resources resources = getContext().getResources();
        if (minutes < 1) {
            refreshTimeText = resources.getString(R.string.xrefreshview_refresh_justnow);
        } else if (minutes < 60) {
            refreshTimeText = Utils.format(resources.getString(R.string.xrefreshview_refresh_minutes_ago), minutes);
        } else if (minutes < 1440) {
            refreshTimeText = Utils.format(resources.getString(R.string.xrefreshview_refresh_hours_ago), minutes / 60);
        } else {
            refreshTimeText = Utils.format(resources.getString(R.string.xrefreshview_refresh_days_ago), (minutes / 60) / 24);
        }
        this.mHeaderTimeTextView.setText(refreshTimeText);
    }

    @Override // com.hy.frame.view.recycler.xRefreshView.IHeaderCallBack
    public void hide() {
        setVisibility(8);
    }

    @Override // com.hy.frame.view.recycler.xRefreshView.IHeaderCallBack
    public void show() {
        setVisibility(0);
    }

    @Override // com.hy.frame.view.recycler.xRefreshView.IHeaderCallBack
    public void onStateNormal() {
        this.mProgressBar.setVisibility(8);
        this.mArrowImageView.setVisibility(0);
        this.mOkImageView.setVisibility(8);
        this.mArrowImageView.startAnimation(this.mRotateDownAnim);
        this.mHintTextView.setText(R.string.xrefreshview_header_hint_normal);
    }

    @Override // com.hy.frame.view.recycler.xRefreshView.IHeaderCallBack
    public void onStateReady() {
        this.mProgressBar.setVisibility(8);
        this.mOkImageView.setVisibility(8);
        this.mArrowImageView.setVisibility(0);
        this.mArrowImageView.clearAnimation();
        this.mArrowImageView.startAnimation(this.mRotateUpAnim);
        this.mHintTextView.setText(R.string.xrefreshview_header_hint_ready);
        this.mHeaderTimeTextView.setVisibility(0);
    }

    @Override // com.hy.frame.view.recycler.xRefreshView.IHeaderCallBack
    public void onStateRefreshing() {
        this.mArrowImageView.clearAnimation();
        this.mArrowImageView.setVisibility(8);
        this.mOkImageView.setVisibility(8);
        this.mProgressBar.setVisibility(0);
        this.mHintTextView.setText(R.string.xrefreshview_header_hint_loading);
    }

    @Override // com.hy.frame.view.recycler.xRefreshView.IHeaderCallBack
    public void onStateFinish(boolean success) {
        this.mArrowImageView.setVisibility(8);
        this.mOkImageView.setVisibility(0);
        this.mProgressBar.setVisibility(8);
        this.mHintTextView.setText(success ? R.string.xrefreshview_header_hint_loaded : R.string.xrefreshview_header_hint_loaded_fail);
        this.mHeaderTimeTextView.setVisibility(8);
    }

    @Override // com.hy.frame.view.recycler.xRefreshView.IHeaderCallBack
    public void onHeaderMove(double headerMovePercent, int offsetY, int deltaY) {
    }

    @Override // com.hy.frame.view.recycler.xRefreshView.IHeaderCallBack
    public int getHeaderHeight() {
        return getMeasuredHeight();
    }
}
