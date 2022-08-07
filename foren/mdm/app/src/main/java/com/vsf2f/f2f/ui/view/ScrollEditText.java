package com.vsf2f.f2f.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.EditText;

/* loaded from: classes2.dex */
public class ScrollEditText extends EditText {
    private boolean mBottomFlag = false;
    private int mOffsetHeight;

    public ScrollEditText(Context context) {
        super(context);
        init();
    }

    public ScrollEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ScrollEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
    }

    @Override // android.widget.TextView, android.view.View
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int mLayoutHeight = getLayout().getHeight();
        int paddingTop = getTotalPaddingTop();
        this.mOffsetHeight = ((mLayoutHeight + paddingTop) + getTotalPaddingBottom()) - getHeight();
    }

    @Override // android.view.View
    public boolean dispatchTouchEvent(MotionEvent event) {
        if (event.getAction() == 0) {
            this.mBottomFlag = false;
        }
        if (this.mBottomFlag) {
            event.setAction(3);
        }
        return super.dispatchTouchEvent(event);
    }

    @Override // android.widget.TextView, android.view.View
    public boolean onTouchEvent(MotionEvent event) {
        boolean result = super.onTouchEvent(event);
        if (!this.mBottomFlag) {
            getParent().requestDisallowInterceptTouchEvent(true);
        }
        return result;
    }

    @Override // android.widget.TextView, android.view.View
    protected void onScrollChanged(int horiz, int vert, int oldHoriz, int oldVert) {
        super.onScrollChanged(horiz, vert, oldHoriz, oldVert);
        if (vert == this.mOffsetHeight || vert == 0) {
            getParent().requestDisallowInterceptTouchEvent(false);
            this.mBottomFlag = true;
        }
    }
}
