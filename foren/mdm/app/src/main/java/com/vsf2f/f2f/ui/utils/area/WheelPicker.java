package com.vsf2f.f2f.ui.utils.area;

import android.content.Context;
import android.support.annotation.ColorInt;
import android.support.annotation.IntRange;
import android.view.View;

/* loaded from: classes2.dex */
public abstract class WheelPicker extends ConfirmPopup<View> {
    protected int textSize = 20;
    protected int textColorNormal = WheelView.TEXT_COLOR_NORMAL;
    protected int textColorFocus = WheelView.TEXT_COLOR_FOCUS;
    protected int lineColor = WheelView.LINE_COLOR;
    protected boolean lineVisible = true;
    protected int offset = 1;

    public WheelPicker(Context context) {
        super(context);
    }

    public void setTextSize(int textSize) {
        this.textSize = textSize;
    }

    public void setTextColor(@ColorInt int textColorFocus, @ColorInt int textColorNormal) {
        this.textColorFocus = textColorFocus;
        this.textColorNormal = textColorNormal;
    }

    public void setTextColor(@ColorInt int textColor) {
        this.textColorFocus = textColor;
    }

    public void setLineVisible(boolean lineVisible) {
        this.lineVisible = lineVisible;
    }

    public void setLineColor(@ColorInt int lineColor) {
        this.lineColor = lineColor;
    }

    public void setOffset(@IntRange(from = 1, to = 4) int offset) {
        this.offset = offset;
    }
}
