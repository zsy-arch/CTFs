package com.hy.frame.view.recycler.xRefreshView;

import android.annotation.TargetApi;
import android.content.Context;
import android.util.AttributeSet;
import android.webkit.WebView;

/* loaded from: classes2.dex */
public class XWebView extends WebView {
    public XWebView(Context context) {
        super(context);
    }

    public XWebView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public XWebView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @TargetApi(16)
    public boolean isBottom() {
        return computeVerticalScrollRange() == getHeight() + getScrollY();
    }

    @Override // android.webkit.WebView, android.view.View
    public int computeVerticalScrollRange() {
        return super.computeVerticalScrollRange();
    }

    @Override // android.view.View
    public float getScaleY() {
        return super.getScaleY();
    }

    @Override // android.webkit.WebView, android.view.View
    public void computeScroll() {
        super.computeScroll();
    }
}
