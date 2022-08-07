package com.hy.frame.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/* loaded from: classes2.dex */
public class MyViewPager extends ViewPager {
    private boolean canScroll = false;

    public MyViewPager(Context context) {
        super(context);
    }

    public boolean isCanScroll() {
        return this.canScroll;
    }

    public void setCanScroll(boolean canScroll) {
        this.canScroll = canScroll;
    }

    public MyViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override // android.support.v4.view.ViewPager, android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent event) {
        if (isCanScroll()) {
            return super.onInterceptTouchEvent(event);
        }
        return false;
    }

    @Override // android.support.v4.view.ViewPager, android.view.View
    public boolean onTouchEvent(MotionEvent event) {
        if (isCanScroll()) {
            return super.onTouchEvent(event);
        }
        return false;
    }
}
