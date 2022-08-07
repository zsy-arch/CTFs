package com.vsf2f.f2f.ui.view.richEditor;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.LinearLayout;

/* loaded from: classes2.dex */
public class InterceptLinearLayout extends LinearLayout {
    private boolean intercept = false;

    public InterceptLinearLayout(Context context) {
        super(context);
    }

    public InterceptLinearLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public InterceptLinearLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setIntercept(boolean b) {
        this.intercept = b;
    }

    @Override // android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if (this.intercept) {
            return true;
        }
        return super.onInterceptTouchEvent(ev);
    }
}
