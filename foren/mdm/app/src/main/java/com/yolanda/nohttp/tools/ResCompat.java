package com.yolanda.nohttp.tools;

import android.annotation.TargetApi;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.text.SpannableString;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.ImageSpan;
import android.text.style.StrikethroughSpan;
import android.view.View;
import android.widget.TextView;
import com.yolanda.nohttp.NoHttp;

/* loaded from: classes2.dex */
public class ResCompat {
    public static Drawable getDrawable(int resId) {
        return getDrawable(resId, null);
    }

    @TargetApi(21)
    public static Drawable getDrawable(int resId, Resources.Theme theme) {
        Resources resources = NoHttp.getContext().getResources();
        return Build.VERSION.SDK_INT >= 21 ? resources.getDrawable(resId, theme) : resources.getDrawable(resId);
    }

    public static void setLeftDrawable(TextView textView, Drawable leftDrawable) {
        setDrawableBounds(leftDrawable);
        textView.setCompoundDrawables(leftDrawable, textView.getCompoundDrawables()[1], textView.getCompoundDrawables()[2], textView.getCompoundDrawables()[3]);
    }

    public static void setLeftDrawable(TextView textView, int resId) {
        setLeftDrawable(textView, getDrawable(resId));
    }

    public static void setTopDrawable(TextView textView, Drawable topDrawable) {
        setDrawableBounds(topDrawable);
        textView.setCompoundDrawables(textView.getCompoundDrawables()[0], topDrawable, textView.getCompoundDrawables()[2], textView.getCompoundDrawables()[3]);
    }

    public static void setTopDrawable(TextView textView, int resId) {
        setTopDrawable(textView, getDrawable(resId));
    }

    public static void setRightDrawable(TextView textView, Drawable rightDrawable) {
        setDrawableBounds(rightDrawable);
        textView.setCompoundDrawables(textView.getCompoundDrawables()[0], textView.getCompoundDrawables()[1], rightDrawable, textView.getCompoundDrawables()[3]);
    }

    public static void setRightDrawable(TextView textView, int resId) {
        setRightDrawable(textView, getDrawable(resId));
    }

    public static void setBottomDrawable(TextView textView, Drawable bottomDrawable) {
        setDrawableBounds(bottomDrawable);
        textView.setCompoundDrawables(textView.getCompoundDrawables()[0], textView.getCompoundDrawables()[1], textView.getCompoundDrawables()[2], bottomDrawable);
    }

    public static void setBottomDrawable(TextView textView, int resId) {
        setBottomDrawable(textView, getDrawable(resId));
    }

    public static void setCompoundDrawables(TextView textView, Drawable leftDrawable, Drawable topDrawable, Drawable rightDrawable, Drawable bottoDrawable) {
        setDrawableBounds(leftDrawable);
        setDrawableBounds(topDrawable);
        setDrawableBounds(rightDrawable);
        setDrawableBounds(bottoDrawable);
        textView.setCompoundDrawables(leftDrawable, topDrawable, rightDrawable, bottoDrawable);
    }

    public static void setCompoundDrawables(TextView textView, int resLeftId, int resRightId, int resTopId, int resBottomId) {
        setCompoundDrawables(textView, getDrawable(resLeftId), getDrawable(resRightId), getDrawable(resTopId), getDrawable(resBottomId));
    }

    public static void setDrawableBounds(Drawable drawable) {
        if (drawable != null) {
            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        }
    }

    public static int getColor(int resId) {
        return getColor(resId, null);
    }

    public static int getColor(int resId, Resources.Theme theme) {
        return getColor(NoHttp.getContext().getResources(), resId, theme);
    }

    @TargetApi(23)
    public static int getColor(Resources resources, int resId, Resources.Theme theme) {
        return Build.VERSION.SDK_INT >= 23 ? resources.getColor(resId, theme) : resources.getColor(resId);
    }

    public static ColorStateList getColorStateList(int resId) {
        return getColorStateList(resId, null);
    }

    @TargetApi(23)
    public static ColorStateList getColorStateList(int resId, Resources.Theme theme) {
        Resources resources = NoHttp.getContext().getResources();
        return Build.VERSION.SDK_INT >= 23 ? resources.getColorStateList(resId, theme) : resources.getColorStateList(resId);
    }

    @TargetApi(16)
    public static void setBackground(View view, Drawable background) {
        if (Build.VERSION.SDK_INT >= 16) {
            view.setBackground(background);
        } else {
            view.setBackgroundDrawable(background);
        }
    }

    public static SpannableString getScaleText(String content, int start, int end, int px) {
        SpannableString stringSpan = new SpannableString(content);
        stringSpan.setSpan(new AbsoluteSizeSpan(px), start, end, 33);
        return stringSpan;
    }

    public static SpannableString getColorText(String content, int start, int end, int color) {
        SpannableString stringSpan = new SpannableString(content);
        stringSpan.setSpan(new ForegroundColorSpan(color), start, end, 33);
        return stringSpan;
    }

    public static SpannableString getDeleteText(String content) {
        return getDeleteText(content, 0, content.length());
    }

    public static SpannableString getDeleteText(String content, int start, int end) {
        SpannableString stringSpan = new SpannableString(content);
        stringSpan.setSpan(new StrikethroughSpan(), start, end, 33);
        return stringSpan;
    }

    public static SpannableString getImageSpanText(String content, Drawable drawable, int start, int end) {
        SpannableString stringSpan = new SpannableString(content);
        setDrawableBounds(drawable);
        stringSpan.setSpan(new ImageSpan(drawable, 1), start, end, 33);
        return stringSpan;
    }
}
