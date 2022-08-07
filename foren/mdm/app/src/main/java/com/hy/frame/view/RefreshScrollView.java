package com.hy.frame.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ScrollView;
import com.hy.frame.util.MyLog;
import com.hyphenate.util.HanziToPinyin;

/* loaded from: classes2.dex */
public class RefreshScrollView extends ScrollView {
    private static final int RATIO = 1;
    private int height;
    private boolean isRecord;
    private RefreshListener listener;
    private int max;
    private int startY;

    /* loaded from: classes2.dex */
    public interface OnPagerChangeListener {
        void onPagerChange(int i);
    }

    /* loaded from: classes2.dex */
    public interface RefreshListener {
        void onRefreshEvent(int i, int i2);
    }

    public RefreshScrollView(Context context) {
        this(context, null);
    }

    public RefreshScrollView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RefreshScrollView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void init() {
        setVerticalScrollBarEnabled(false);
    }

    @Override // android.widget.ScrollView, android.view.View
    protected void onOverScrolled(int scrollX, int scrollY, boolean clampedX, boolean clampedY) {
        if (scrollY >= 0) {
            super.onOverScrolled(scrollX, scrollY, clampedX, clampedY);
        }
    }

    @Override // android.widget.ScrollView, android.view.View
    public boolean onTouchEvent(MotionEvent ev) {
        if (this.listener != null) {
            MyLog.e("getScrollY 记录当前位置:" + getScrollY());
            int y = (int) ev.getY();
            switch (ev.getAction()) {
                case 0:
                case 2:
                    if (this.isRecord) {
                        this.height += 10;
                        MyLog.e("ACTION_MOVE 记录当前位置:" + y + HanziToPinyin.Token.SEPARATOR + this.height);
                        this.listener.onRefreshEvent(2, this.height);
                        break;
                    } else {
                        this.isRecord = true;
                        this.startY = y;
                        this.height = 0;
                        MyLog.e("ACTION_DOWN 记录当前位置:" + this.startY);
                        this.listener.onRefreshEvent(0, 0);
                        break;
                    }
                case 1:
                case 3:
                    this.isRecord = false;
                    this.listener.onRefreshEvent(1, this.height);
                    MyLog.e("ACTION_UP 记录当前位置:" + this.height);
                    break;
            }
        }
        return super.onTouchEvent(ev);
    }

    public void setListener(RefreshListener listener) {
        this.listener = listener;
    }
}
