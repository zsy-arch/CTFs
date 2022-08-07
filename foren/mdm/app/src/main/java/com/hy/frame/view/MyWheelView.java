package com.hy.frame.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import com.hy.frame.R;
import com.hy.frame.util.HyUtil;
import com.hy.frame.util.MyLog;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes2.dex */
public class MyWheelView extends ScrollView {
    public static final int OFF_SET_DEFAULT = 2;
    private static final int SCROLL_DIRECTION_DOWN = 1;
    private static final int SCROLL_DIRECTION_UP = 0;
    public static final String TAG = MyWheelView.class.getSimpleName();
    private Context context;
    private int count;
    int displayItemCount;
    int initialY;
    List items;
    private int lineColor;
    private OnWheelViewListener onWheelViewListener;
    Paint paint;
    Runnable scrollerTask;
    int[] selectedAreaBorder;
    private int textSize;
    int viewWidth;
    private LinearLayout views;
    int offset = 2;
    int selectedIndex = 2;
    int newCheck = 50;
    int itemHeight = 0;
    private int scrollDirection = -1;

    /* loaded from: classes2.dex */
    public static class OnWheelViewListener {
        public void onSelected(int selectedIndex, Object item) {
        }
    }

    public MyWheelView(Context context) {
        super(context);
        init(context);
    }

    public MyWheelView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public MyWheelView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context);
    }

    private <T> List<T> getItems() {
        return this.items;
    }

    public <T> void setItems(List<T> list) {
        if (!(list == null || list.size() == 0)) {
            this.items = new ArrayList();
            this.items.addAll(list);
            this.count = this.items.size();
            Class cls = list.get(0).getClass();
            for (int i = 0; i < this.offset; i++) {
                if (cls == String.class) {
                    this.items.add(0, "");
                    this.items.add("");
                } else if (cls == Integer.class) {
                    this.items.add(0, 0);
                    this.items.add(0);
                } else {
                    try {
                        Object newInstance = cls.newInstance();
                        cls.getMethod("setName", String.class).invoke(newInstance, "");
                        this.items.add(0, newInstance);
                        Object newInstance2 = cls.newInstance();
                        cls.getMethod("setName", String.class).invoke(newInstance2, "");
                        this.items.add(newInstance2);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
            initData();
        }
    }

    public int getOffset() {
        return this.offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    private void init(Context context) {
        this.context = context;
        setVerticalScrollBarEnabled(false);
        this.views = new LinearLayout(context);
        this.views.setOrientation(1);
        this.views.setLayoutParams(new ViewGroup.LayoutParams(-1, -1));
        addView(this.views);
        this.scrollerTask = new Runnable() { // from class: com.hy.frame.view.MyWheelView.1
            @Override // java.lang.Runnable
            public void run() {
                if (MyWheelView.this.itemHeight != 0) {
                    if (MyWheelView.this.initialY - MyWheelView.this.getScrollY() == 0) {
                        final int remainder = MyWheelView.this.initialY % MyWheelView.this.itemHeight;
                        final int divided = MyWheelView.this.initialY / MyWheelView.this.itemHeight;
                        if (remainder == 0) {
                            MyWheelView.this.selectedIndex = MyWheelView.this.offset + divided;
                            MyWheelView.this.onSeletedCallBack();
                        } else if (remainder > MyWheelView.this.itemHeight / 2) {
                            MyWheelView.this.post(new Runnable() { // from class: com.hy.frame.view.MyWheelView.1.1
                                @Override // java.lang.Runnable
                                public void run() {
                                    MyWheelView.this.smoothScrollTo(0, (MyWheelView.this.initialY - remainder) + MyWheelView.this.itemHeight);
                                    MyWheelView.this.selectedIndex = divided + MyWheelView.this.offset + 1;
                                    MyWheelView.this.onSeletedCallBack();
                                }
                            });
                        } else {
                            MyWheelView.this.post(new Runnable() { // from class: com.hy.frame.view.MyWheelView.1.2
                                @Override // java.lang.Runnable
                                public void run() {
                                    MyWheelView.this.smoothScrollTo(0, MyWheelView.this.initialY - remainder);
                                    MyWheelView.this.selectedIndex = divided + MyWheelView.this.offset;
                                    MyWheelView.this.onSeletedCallBack();
                                }
                            });
                        }
                    } else {
                        MyWheelView.this.initialY = MyWheelView.this.getScrollY();
                        MyWheelView.this.postDelayed(MyWheelView.this.scrollerTask, MyWheelView.this.newCheck);
                    }
                }
            }
        };
    }

    public void startScrollerTask() {
        this.initialY = getScrollY();
        postDelayed(this.scrollerTask, this.newCheck);
    }

    private void initData() {
        this.displayItemCount = (this.offset * 2) + 1;
        this.views.removeAllViews();
        for (Object item : this.items) {
            this.views.addView(createView(item));
        }
        refreshItemView(0);
        getLayoutParams().height = this.itemHeight * this.displayItemCount;
    }

    public void setTextSize(int sp) {
        this.textSize = sp;
    }

    private TextView createView(Object item) {
        TextView tv = new TextView(this.context);
        tv.setLayoutParams(new FrameLayout.LayoutParams(-1, -2));
        tv.setSingleLine(true);
        tv.setTextSize(2, this.textSize > 0 ? this.textSize : 14.0f);
        tv.setText(getItemStr(item));
        tv.setGravity(17);
        int padding = getContext().getResources().getDimensionPixelSize(R.dimen.margin_normal);
        tv.setPadding(padding, padding, padding, padding);
        if (this.itemHeight == 0) {
            this.itemHeight = HyUtil.getViewMeasuredHeight(tv);
            this.views.setLayoutParams(new FrameLayout.LayoutParams(-1, this.itemHeight * this.displayItemCount));
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) getLayoutParams();
        }
        return tv;
    }

    @Override // android.view.View
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        refreshItemView(t);
        if (t > oldt) {
            this.scrollDirection = 1;
        } else {
            this.scrollDirection = 0;
        }
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
                    itemView.setTextColor(Color.parseColor("#454545"));
                } else {
                    itemView.setTextColor(Color.parseColor("#787878"));
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

    public void setLineColor(int lineColor) {
        this.lineColor = lineColor;
    }

    @Override // android.view.View
    public void setBackgroundDrawable(Drawable background) {
        if (this.viewWidth == 0) {
            this.viewWidth = (int) this.context.getResources().getDisplayMetrics().xdpi;
            Log.d(TAG, "viewWidth: " + this.viewWidth);
        }
        if (this.paint == null) {
            this.paint = new Paint();
            this.paint.setColor(this.lineColor == 0 ? getResources().getColor(R.color.txt_gray) : this.lineColor);
            this.paint.setStrokeWidth(HyUtil.dip2px(this.context, 1.0f));
        }
        super.setBackgroundDrawable(new Drawable() { // from class: com.hy.frame.view.MyWheelView.2
            @Override // android.graphics.drawable.Drawable
            public void draw(Canvas canvas) {
                canvas.drawLine(0.0f, MyWheelView.this.obtainSelectedAreaBorder()[0], MyWheelView.this.viewWidth, MyWheelView.this.obtainSelectedAreaBorder()[0], MyWheelView.this.paint);
                canvas.drawLine(0.0f, MyWheelView.this.obtainSelectedAreaBorder()[1], MyWheelView.this.viewWidth, MyWheelView.this.obtainSelectedAreaBorder()[1], MyWheelView.this.paint);
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

    @Override // android.widget.ScrollView, android.view.View
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        this.viewWidth = w;
        setBackgroundDrawable(null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onSeletedCallBack() {
        if (this.onWheelViewListener != null && this.selectedIndex < this.items.size()) {
            if (getItemStr(this.selectedIndex).length() < 1) {
                int size = this.selectedIndex + 1;
                int lines = this.count + this.offset;
                if (size > this.count + this.offset) {
                    smoothScrollTo(0, this.itemHeight * (lines - 1));
                    this.selectedIndex = lines - 1;
                    onSeletedCallBack();
                    return;
                }
                return;
            }
            this.onWheelViewListener.onSelected(this.selectedIndex, this.items.get(this.selectedIndex));
        }
    }

    public void setSeletion(final int position) {
        this.selectedIndex = this.offset + position;
        post(new Runnable() { // from class: com.hy.frame.view.MyWheelView.3
            @Override // java.lang.Runnable
            public void run() {
                MyWheelView.this.smoothScrollTo(0, position * MyWheelView.this.itemHeight);
            }
        });
    }

    private String getItemStr(int position) {
        return getItemStr(this.items.get(position));
    }

    private String getItemStr(Object obj) {
        if ((obj instanceof String) || (obj instanceof Integer)) {
            return obj + "";
        }
        try {
            return obj.getClass().getMethod("getName", new Class[0]).invoke(obj, new Object[0]) + "";
        } catch (Exception e) {
            MyLog.e(getClass(), "Object 如果不是 String or Integer 里面必须有getName和setName(String)");
            e.printStackTrace();
            return "";
        }
    }

    public String getSelectedItem() {
        return getItemStr(this.selectedIndex);
    }

    public Object getSelectedObject() {
        return this.items.get(this.selectedIndex);
    }

    public int getSelectedIndex() {
        return this.selectedIndex - this.offset;
    }

    @Override // android.widget.ScrollView
    public void fling(int velocityY) {
        super.fling(velocityY / 3);
    }

    @Override // android.widget.ScrollView, android.view.View
    public boolean onTouchEvent(MotionEvent ev) {
        if (ev.getAction() == 1) {
            startScrollerTask();
        }
        return super.onTouchEvent(ev);
    }

    public OnWheelViewListener getOnWheelViewListener() {
        return this.onWheelViewListener;
    }

    public void setOnWheelViewListener(OnWheelViewListener onWheelViewListener) {
        this.onWheelViewListener = onWheelViewListener;
    }
}
