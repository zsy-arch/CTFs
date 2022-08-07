package com.cdlinglu.utils;

import android.content.Context;
import android.graphics.Color;
import android.text.SpannableString;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.BackgroundColorSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.ScaleXSpan;
import android.text.style.StrikethroughSpan;
import android.text.style.StyleSpan;
import android.text.style.SubscriptSpan;
import android.text.style.SuperscriptSpan;
import android.text.style.TypefaceSpan;
import android.text.style.UnderlineSpan;
import com.hy.frame.util.HyUtil;

/* loaded from: classes.dex */
public class SpanUtil {
    public SpannableString getSizeSpanUsePx(Context context, String str, int start, int end, int pxSize) {
        SpannableString ss = new SpannableString(str);
        ss.setSpan(new AbsoluteSizeSpan(pxSize), 4, str.length(), 33);
        return ss;
    }

    public SpannableString getSizeSpanUseDip(Context context, String str, int start, int end, int dipSize) {
        SpannableString ss = new SpannableString(str);
        ss.setSpan(new AbsoluteSizeSpan(dipSize, true), 4, str.length(), 33);
        return ss;
    }

    public SpannableString getSizeSpanSpToPx(Context context, String str, int start, int end, int spSize) {
        SpannableString ss = new SpannableString(str);
        ss.setSpan(new AbsoluteSizeSpan(HyUtil.sp2px(context, spSize)), start, end, 33);
        return ss;
    }

    public SpannableString getRelativeSizeSpan(Context context, String str, int start, int end, float relativeSize) {
        SpannableString ss = new SpannableString(str);
        ss.setSpan(new RelativeSizeSpan(relativeSize), start, end, 33);
        return ss;
    }

    public SpannableString getTypeFaceSpan(Context context, String str, int start, int end, String typeface) {
        SpannableString ss = new SpannableString(str);
        ss.setSpan(new TypefaceSpan(typeface), start, end, 33);
        return ss;
    }

    public SpannableString getStyleSpan(Context context, String str, int start, int end, int style) {
        SpannableString ss = new SpannableString(str);
        ss.setSpan(new StyleSpan(style), start, end, 33);
        return ss;
    }

    public SpannableString getUnderLineSpan(Context context, String str, int start, int end) {
        SpannableString ss = new SpannableString(str);
        ss.setSpan(new UnderlineSpan(), start, end, 33);
        return ss;
    }

    public SpannableString getDeleteLineSpan(Context context, String str, int start, int end) {
        SpannableString ss = new SpannableString(str);
        ss.setSpan(new StrikethroughSpan(), start, end, 33);
        return ss;
    }

    public SpannableString getSuperscriptSpan(Context context, String str, int start, int end) {
        SpannableString ss = new SpannableString(str);
        ss.setSpan(new SuperscriptSpan(), start, end, 33);
        return ss;
    }

    public SpannableString getScaleSpan(Context context, String str, int start, int end, float scale) {
        SpannableString ss = new SpannableString(str);
        ss.setSpan(new ScaleXSpan(scale), start, end, 33);
        return ss;
    }

    public SpannableString getSubscriptSpan(Context context, String str, int start, int end) {
        SpannableString ss = new SpannableString(str);
        ss.setSpan(new SubscriptSpan(), start, end, 33);
        return ss;
    }

    public SpannableString getBackGroundColorSpan(Context context, String str, int start, int end, int color) {
        SpannableString ss = new SpannableString(str);
        ss.setSpan(new BackgroundColorSpan(color), start, end, 33);
        return ss;
    }

    public SpannableString getBackGroundColorSpan(Context context, String str, int start, int end, String color) {
        SpannableString ss = new SpannableString(str);
        ss.setSpan(new BackgroundColorSpan(Color.parseColor(color)), start, end, 33);
        return ss;
    }

    public SpannableString getForegroundColorSpan(String str, int start, int end, int color) {
        SpannableString ss = new SpannableString(str);
        ss.setSpan(new ForegroundColorSpan(color), start, end, 33);
        return ss;
    }

    public SpannableString getForegroundColorSpan(Context context, String str, int start, int end, String color) {
        SpannableString ss = new SpannableString(str);
        ss.setSpan(new ForegroundColorSpan(Color.parseColor(color)), start, end, 33);
        return ss;
    }
}
