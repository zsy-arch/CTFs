package com.vsf2f.f2f.ui.utils.area;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.ColorInt;
import android.support.annotation.IntRange;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import com.hy.frame.util.MyLog;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/* loaded from: classes2.dex */
public class WheelView extends ScrollView {
    private static final int DELAY = 50;
    public static final int LINE_COLOR = -8139290;
    public static final int OFF_SET = 1;
    public static final int TEXT_COLOR_FOCUS = -16611122;
    public static final int TEXT_COLOR_NORMAL = -4473925;
    public static final int TEXT_SIZE = 20;
    private Context context;
    private int displayItemCount;
    private int initialY;
    private OnWheelViewListener onWheelViewListener;
    private Paint paint;
    private int[] selectedAreaBorder;
    private int viewWidth;
    private LinearLayout views;
    private LinkedList<String> items = new LinkedList<>();
    private int offset = 1;
    private int selectedIndex = 1;
    private Runnable scrollerTask = new ScrollerTask();
    private int itemHeight = 0;
    private int textSize = 20;
    private int textColorNormal = TEXT_COLOR_NORMAL;
    private int textColorFocus = TEXT_COLOR_FOCUS;
    private int lineColor = LINE_COLOR;
    private boolean lineVisible = true;
    private boolean isUserScroll = false;
    private float previousY = 0.0f;

    /* loaded from: classes2.dex */
    public interface OnWheelViewListener {
        void onSelected(boolean z, int i, String str);
    }

    public WheelView(Context context) {
        super(context);
        init(context);
    }

    public WheelView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public WheelView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context);
    }

    private void init(Context context) {
        this.context = context;
        setFadingEdgeLength(0);
        if (Build.VERSION.SDK_INT >= 9) {
            setOverScrollMode(2);
        }
        setVerticalScrollBarEnabled(false);
        this.views = new LinearLayout(context);
        this.views.setOrientation(1);
        addView(this.views);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void startScrollerTask() {
        this.initialY = getScrollY();
        postDelayed(this.scrollerTask, 50L);
    }

    private void initData() {
        this.displayItemCount = (this.offset * 2) + 1;
        this.views.removeAllViews();
        Iterator<String> it = this.items.iterator();
        while (it.hasNext()) {
            this.views.addView(createView(it.next()));
        }
        refreshItemView(this.itemHeight * (this.selectedIndex - this.offset));
    }

    private TextView createView(String item) {
        TextView tv = new TextView(this.context);
        tv.setLayoutParams(new FrameLayout.LayoutParams(-1, -2));
        tv.setSingleLine(true);
        tv.setEllipsize(TextUtils.TruncateAt.END);
        tv.setText(item);
        tv.setTextSize(this.textSize);
        tv.setGravity(17);
        int padding = dip2px(15.0f);
        tv.setPadding(padding, padding, padding, padding);
        if (this.itemHeight == 0) {
            this.itemHeight = getViewMeasuredHeight(tv);
            MyLog.d(getClass(), "itemHeight: " + this.itemHeight);
            this.views.setLayoutParams(new FrameLayout.LayoutParams(-1, this.itemHeight * this.displayItemCount));
            setLayoutParams(new LinearLayout.LayoutParams(((LinearLayout.LayoutParams) getLayoutParams()).width, this.itemHeight * this.displayItemCount));
        }
        return tv;
    }

    private void refreshItemView(int y) {
        int position = (y / this.itemHeight) + this.offset;
        int remainder = y % this.itemHeight;
        int divided = y / this.itemHeight;
        if (remainder == 0) {
            position = divided + this.offset;
        } else if (remainder > this.itemHeight / 2) {
            position = this.offset + divided + 1;
        }
        int childSize = this.views.getChildCount();
        for (int i = 0; i < childSize; i++) {
            TextView itemView = (TextView) this.views.getChildAt(i);
            if (itemView != null) {
                if (position == i) {
                    itemView.setTextColor(this.textColorFocus);
                } else {
                    itemView.setTextColor(this.textColorNormal);
                }
            } else {
                return;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int[] obtainSelectedAreaBorder() {
        if (this.selectedAreaBorder == null) {
            this.selectedAreaBorder = new int[2];
            this.selectedAreaBorder[0] = this.itemHeight * this.offset;
            this.selectedAreaBorder[1] = this.itemHeight * (this.offset + 1);
        }
        return this.selectedAreaBorder;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onSelectedCallBack() {
        if (this.onWheelViewListener != null) {
            int realIndex = this.selectedIndex - this.offset;
            MyLog.d("isUserScroll=" + this.isUserScroll + ",selectedIndex=" + this.selectedIndex + ",realIndex=" + realIndex);
            this.onWheelViewListener.onSelected(this.isUserScroll, realIndex, this.items.get(this.selectedIndex));
        }
    }

    private int dip2px(float dpValue) {
        return (int) ((dpValue * this.context.getResources().getDisplayMetrics().density) + 0.5f);
    }

    private int getViewMeasuredHeight(View view) {
        view.measure(View.MeasureSpec.makeMeasureSpec(0, 0), View.MeasureSpec.makeMeasureSpec(536870911, Integer.MIN_VALUE));
        return view.getMeasuredHeight();
    }

    @Override // android.view.View
    public void setBackground(Drawable background) {
        setBackgroundDrawable(background);
    }

    @Override // android.view.View
    public void setBackgroundDrawable(Drawable background) {
        if (this.viewWidth == 0) {
            this.viewWidth = ((Activity) this.context).getWindowManager().getDefaultDisplay().getWidth();
            MyLog.d(getClass(), "viewWidth: " + this.viewWidth);
        }
        if (this.lineVisible) {
            if (this.paint == null) {
                this.paint = new Paint();
                this.paint.setColor(this.lineColor);
                this.paint.setStrokeWidth(dip2px(1.0f));
            }
            super.setBackgroundDrawable(new Drawable() { // from class: com.vsf2f.f2f.ui.utils.area.WheelView.1
                @Override // android.graphics.drawable.Drawable
                public void draw(Canvas canvas) {
                    int[] areaBorder = WheelView.this.obtainSelectedAreaBorder();
                    canvas.drawLine(WheelView.this.viewWidth / 6, areaBorder[0], (WheelView.this.viewWidth * 5) / 6, areaBorder[0], WheelView.this.paint);
                    canvas.drawLine(WheelView.this.viewWidth / 6, areaBorder[1], (WheelView.this.viewWidth * 5) / 6, areaBorder[1], WheelView.this.paint);
                }

                @Override // android.graphics.drawable.Drawable
                public void setAlpha(int alpha) {
                }

                @Override // android.graphics.drawable.Drawable
                public void setColorFilter(ColorFilter cf) {
                }

                @Override // android.graphics.drawable.Drawable
                public int getOpacity() {
                    return 0;
                }
            });
        }
    }

    @Override // android.view.View
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        refreshItemView(t);
    }

    @Override // android.widget.ScrollView, android.view.View
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        MyLog.d(getClass(), "w: " + w + ", h: " + h + ", oldw: " + oldw + ", oldh: " + oldh);
        this.viewWidth = w;
        setBackgroundDrawable(null);
    }

    @Override // android.widget.ScrollView
    public void fling(int velocityY) {
        super.fling(velocityY / 3);
    }

    @Override // android.widget.ScrollView, android.view.View
    public boolean onTouchEvent(MotionEvent ev) {
        this.isUserScroll = true;
        switch (ev.getAction()) {
            case 0:
                this.previousY = ev.getY();
                break;
            case 1:
                MyLog.d(getClass(), String.format("items=%s, offset=%s", Integer.valueOf(this.items.size()), Integer.valueOf(this.offset)));
                MyLog.d(getClass(), "selectedIndex=" + this.selectedIndex);
                float delta = ev.getY() - this.previousY;
                MyLog.d(getClass(), "delta=" + delta);
                if (this.selectedIndex != this.offset || delta <= 0.0f) {
                    if (this.selectedIndex == (this.items.size() - this.offset) - 1 && delta < 0.0f) {
                        setSelectedIndex(0);
                        break;
                    } else {
                        startScrollerTask();
                        break;
                    }
                } else {
                    setSelectedIndex((this.items.size() - (this.offset * 2)) - 1);
                    break;
                }
                break;
        }
        return super.onTouchEvent(ev);
    }

    private void _setItems(List<String> list) {
        this.items.clear();
        this.items.addAll(list);
        for (int i = 0; i < this.offset; i++) {
            this.items.addFirst("");
            this.items.addLast("");
        }
        initData();
    }

    public void setItems(List<String> list) {
        _setItems(list);
        setSelectedIndex(0);
    }

    public void setItems(List<String> list, int index) {
        _setItems(list);
        setSelectedIndex(index);
    }

    public void setItems(List<String> list, String item) {
        _setItems(list);
        setSelectedItem(item);
    }

    public int getTextSize() {
        return this.textSize;
    }

    public void setTextSize(int textSize) {
        this.textSize = textSize;
    }

    public int getTextColor() {
        return this.textColorFocus;
    }

    public void setTextColor(@ColorInt int textColorNormal, @ColorInt int textColorFocus) {
        this.textColorNormal = textColorNormal;
        this.textColorFocus = textColorFocus;
    }

    public void setTextColor(@ColorInt int textColor) {
        this.textColorFocus = textColor;
    }

    public boolean isLineVisible() {
        return this.lineVisible;
    }

    public void setLineVisible(boolean lineVisible) {
        this.lineVisible = lineVisible;
    }

    public int getLineColor() {
        return this.lineColor;
    }

    public void setLineColor(@ColorInt int lineColor) {
        this.lineColor = lineColor;
    }

    public int getOffset() {
        return this.offset;
    }

    public void setOffset(@IntRange(from = 1, to = 4) int offset) {
        if (offset < 1 || offset > 4) {
            throw new IllegalArgumentException("Offset must between 1 and 4");
        }
        this.offset = offset;
    }

    private void setSelectedIndex(@IntRange(from = 0) final int index) {
        this.isUserScroll = false;
        post(new Runnable() { // from class: com.vsf2f.f2f.ui.utils.area.WheelView.2
            @Override // java.lang.Runnable
            public void run() {
                WheelView.this.smoothScrollTo(0, index * WheelView.this.itemHeight);
                WheelView.this.selectedIndex = index + WheelView.this.offset;
                WheelView.this.onSelectedCallBack();
            }
        });
    }

    public void setSelectedItem(String item) {
        for (int i = 0; i < this.items.size(); i++) {
            if (this.items.get(i).equals(item)) {
                setSelectedIndex(i - this.offset);
                return;
            }
        }
    }

    public String getSelectedItem() {
        return this.items.get(this.selectedIndex);
    }

    public int getSelectedIndex() {
        return this.selectedIndex - this.offset;
    }

    public void setOnWheelViewListener(OnWheelViewListener onWheelViewListener) {
        this.onWheelViewListener = onWheelViewListener;
    }

    /* loaded from: classes2.dex */
    private class ScrollerTask implements Runnable {
        private ScrollerTask() {
        }

        @Override // java.lang.Runnable
        public void run() {
            if (WheelView.this.itemHeight == 0) {
                MyLog.d(getClass(), "itemHeight is zero");
                return;
            }
            if (WheelView.this.initialY - WheelView.this.getScrollY() == 0) {
                final int remainder = WheelView.this.initialY % WheelView.this.itemHeight;
                final int divided = WheelView.this.initialY / WheelView.this.itemHeight;
                MyLog.d(getClass(), "initialY: " + WheelView.this.initialY + ", remainder: " + remainder + ", divided: " + divided);
                if (remainder == 0) {
                    WheelView.this.selectedIndex = WheelView.this.offset + divided;
                    WheelView.this.onSelectedCallBack();
                } else if (remainder > WheelView.this.itemHeight / 2) {
                    WheelView.this.post(new Runnable() { // from class: com.vsf2f.f2f.ui.utils.area.WheelView.ScrollerTask.1
                        @Override // java.lang.Runnable
                        public void run() {
                            WheelView.this.smoothScrollTo(0, (WheelView.this.initialY - remainder) + WheelView.this.itemHeight);
                            WheelView.this.selectedIndex = divided + WheelView.this.offset + 1;
                            WheelView.this.onSelectedCallBack();
                        }
                    });
                } else {
                    WheelView.this.post(new Runnable() { // from class: com.vsf2f.f2f.ui.utils.area.WheelView.ScrollerTask.2
                        @Override // java.lang.Runnable
                        public void run() {
                            WheelView.this.smoothScrollTo(0, WheelView.this.initialY - remainder);
                            WheelView.this.selectedIndex = divided + WheelView.this.offset;
                            WheelView.this.onSelectedCallBack();
                        }
                    });
                }
            } else {
                WheelView.this.startScrollerTask();
            }
        }
    }
}
