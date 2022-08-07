package com.vsf2f.f2f.ui.utils.area;

import android.content.Context;
import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

/* loaded from: classes2.dex */
public abstract class ConfirmPopup<V extends View> extends BottomPopup<View> {
    protected CharSequence cancelText;
    protected CharSequence submitText;
    protected boolean topLineVisible = true;
    protected int topLineColor = -2236963;
    protected int topBackgroundColor = -1;
    protected boolean cancelVisible = true;
    protected CharSequence titleText = "";
    protected int cancelTextColor = -16777216;
    protected int submitTextColor = -16777216;
    protected int titleTextColor = -16777216;

    @NonNull
    protected abstract V makeCenterView();

    public ConfirmPopup(Context context) {
        super(context);
        this.cancelText = "";
        this.submitText = "";
        this.cancelText = context.getString(17039360);
        this.submitText = context.getString(17039370);
    }

    public void setTopLineColor(@ColorInt int topLineColor) {
        this.topLineColor = topLineColor;
    }

    public void setTopBackgroundColor(@ColorInt int topBackgroundColor) {
        this.topBackgroundColor = topBackgroundColor;
    }

    public void setTopLineVisible(boolean topLineVisible) {
        this.topLineVisible = topLineVisible;
    }

    public void setCancelVisible(boolean cancelVisible) {
        this.cancelVisible = cancelVisible;
    }

    public void setCancelText(CharSequence cancelText) {
        this.cancelText = cancelText;
    }

    public void setCancelText(@StringRes int textRes) {
        this.cancelText = this.context.getString(textRes);
    }

    public void setSubmitText(CharSequence submitText) {
        this.submitText = submitText;
    }

    public void setSubmitText(@StringRes int textRes) {
        this.submitText = this.context.getString(textRes);
    }

    public void setTitleText(CharSequence titleText) {
        this.titleText = titleText;
    }

    public void setTitleText(@StringRes int textRes) {
        this.titleText = this.context.getString(textRes);
    }

    public void setCancelTextColor(@ColorInt int cancelTextColor) {
        this.cancelTextColor = cancelTextColor;
    }

    public void setSubmitTextColor(@ColorInt int submitTextColor) {
        this.submitTextColor = submitTextColor;
    }

    public void setTitleTextColor(@ColorInt int titleTextColor) {
        this.titleTextColor = titleTextColor;
    }

    @Override // com.vsf2f.f2f.ui.utils.area.BottomPopup
    protected final View makeContentView() {
        LinearLayout rootLayout = new LinearLayout(this.context);
        rootLayout.setLayoutParams(new LinearLayout.LayoutParams(-1, -1));
        rootLayout.setBackgroundColor(-1);
        rootLayout.setOrientation(1);
        rootLayout.setGravity(17);
        rootLayout.setPadding(0, 0, 0, 0);
        rootLayout.setClipToPadding(false);
        View headerView = makeHeaderView();
        if (headerView != null) {
            rootLayout.addView(headerView);
        }
        if (this.topLineVisible) {
            View lineView = new View(this.context);
            lineView.setLayoutParams(new LinearLayout.LayoutParams(-1, 1));
            lineView.setBackgroundColor(this.topLineColor);
            rootLayout.addView(lineView);
        }
        rootLayout.addView(makeCenterView(), new LinearLayout.LayoutParams(-1, 0, 1.0f));
        View footerView = makeFooterView();
        if (footerView != null) {
            rootLayout.addView(footerView);
        }
        return rootLayout;
    }

    @Nullable
    protected View makeHeaderView() {
        RelativeLayout topButtonLayout = new RelativeLayout(this.context);
        topButtonLayout.setLayoutParams(new RelativeLayout.LayoutParams(-1, ConvertUtils.toPx(this.context, 40.0f)));
        topButtonLayout.setBackgroundColor(this.topBackgroundColor);
        topButtonLayout.setGravity(16);
        Button cancelButton = new Button(this.context);
        cancelButton.setVisibility(this.cancelVisible ? 0 : 8);
        RelativeLayout.LayoutParams cancelButtonLayoutParams = new RelativeLayout.LayoutParams(-2, -2);
        cancelButtonLayoutParams.addRule(9, -1);
        cancelButtonLayoutParams.addRule(15, -1);
        cancelButton.setLayoutParams(cancelButtonLayoutParams);
        cancelButton.setBackgroundColor(0);
        cancelButton.setGravity(17);
        if (!TextUtils.isEmpty(this.cancelText)) {
            cancelButton.setText(this.cancelText);
        }
        cancelButton.setTextColor(this.cancelTextColor);
        cancelButton.setOnClickListener(new View.OnClickListener() { // from class: com.vsf2f.f2f.ui.utils.area.ConfirmPopup.1
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                ConfirmPopup.this.dismiss();
                ConfirmPopup.this.onCancel();
            }
        });
        topButtonLayout.addView(cancelButton);
        TextView titleView = new TextView(this.context);
        RelativeLayout.LayoutParams titleLayoutParams = new RelativeLayout.LayoutParams(-2, -2);
        int margin = ConvertUtils.toPx(this.context, 20.0f);
        titleLayoutParams.leftMargin = margin;
        titleLayoutParams.rightMargin = margin;
        titleLayoutParams.addRule(14, -1);
        titleLayoutParams.addRule(15, -1);
        titleView.setLayoutParams(titleLayoutParams);
        titleView.setGravity(17);
        if (!TextUtils.isEmpty(this.titleText)) {
            titleView.setText(this.titleText);
        }
        titleView.setTextColor(this.titleTextColor);
        topButtonLayout.addView(titleView);
        Button submitButton = new Button(this.context);
        RelativeLayout.LayoutParams submitButtonLayoutParams = new RelativeLayout.LayoutParams(-2, -2);
        submitButtonLayoutParams.addRule(11, -1);
        submitButtonLayoutParams.addRule(15, -1);
        submitButton.setLayoutParams(submitButtonLayoutParams);
        submitButton.setBackgroundColor(0);
        submitButton.setGravity(17);
        if (!TextUtils.isEmpty(this.submitText)) {
            submitButton.setText(this.submitText);
        }
        submitButton.setTextColor(this.submitTextColor);
        submitButton.setOnClickListener(new View.OnClickListener() { // from class: com.vsf2f.f2f.ui.utils.area.ConfirmPopup.2
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                ConfirmPopup.this.dismiss();
                ConfirmPopup.this.onSubmit();
            }
        });
        topButtonLayout.addView(submitButton);
        return topButtonLayout;
    }

    @Nullable
    protected View makeFooterView() {
        return null;
    }

    protected void onSubmit() {
    }

    protected void onCancel() {
    }
}
