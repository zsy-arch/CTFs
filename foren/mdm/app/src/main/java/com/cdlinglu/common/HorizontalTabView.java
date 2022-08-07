package com.cdlinglu.common;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.hy.frame.util.HyUtil;
import com.vsf2f.f2f.R;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
public class HorizontalTabView extends HorizontalScrollView implements View.OnClickListener {
    private int clickIndex = 0;
    private Context context;
    private LinearLayout lly_parent;
    private String[] tabArray;
    private int tabBgRid;
    private TabClickListener tabClickListener;
    private int tabCount;
    private List<TextView> tabList;
    private LinearLayout.LayoutParams tabParams;
    private int tab_height;
    private int tab_margin;
    private int tab_padding;
    private ColorStateList tab_text_color;
    private int tab_width;
    private float textSize;

    /* loaded from: classes.dex */
    public interface TabClickListener {
        void tabClick(int i);
    }

    public TabClickListener getTabClickListener() {
        return this.tabClickListener;
    }

    public void setTabClickListener(TabClickListener tabClickListener) {
        this.tabClickListener = tabClickListener;
    }

    public HorizontalTabView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.HorizontalTabView, 0, 0);
        this.tabBgRid = a.getResourceId(0, R.drawable.tab_item_selector);
        this.tab_text_color = a.getColorStateList(6);
        this.tab_margin = a.getDimensionPixelSize(1, 20);
        this.tab_padding = a.getDimensionPixelSize(4, 10);
        this.tab_height = a.getDimensionPixelSize(2, 28);
        this.tab_width = a.getDimensionPixelSize(3, 72);
        this.textSize = a.getDimensionPixelSize(5, 14);
        a.recycle();
        FrameLayout.LayoutParams parentParams = new FrameLayout.LayoutParams(-1, -1);
        this.tabParams = new LinearLayout.LayoutParams(this.tab_width, this.tab_height);
        this.lly_parent = new LinearLayout(context);
        parentParams.gravity = 16;
        this.lly_parent.setLayoutParams(parentParams);
        this.lly_parent.setOrientation(0);
        this.lly_parent.setGravity(16);
        addView(this.lly_parent);
    }

    public String[] getTabArray() {
        return this.tabArray;
    }

    public void setTabArray(String[] tabArray) {
        if (this.tabList == null) {
            this.tabList = new ArrayList();
        } else {
            this.lly_parent.removeAllViews();
            this.tabList.clear();
        }
        this.clickIndex = 0;
        this.tabArray = tabArray;
        this.tabCount = tabArray.length;
        float ts = HyUtil.floatToSpDimension(this.textSize, this.context);
        for (int i = 0; i < this.tabCount; i++) {
            TextView tv_tab = new TextView(this.context);
            tv_tab.setTextColor(this.tab_text_color);
            tv_tab.setTextSize(ts);
            tv_tab.setBackgroundResource(this.tabBgRid);
            tv_tab.setLayoutParams(this.tabParams);
            tv_tab.setText(tabArray[i]);
            tv_tab.setGravity(17);
            tv_tab.setOnClickListener(this);
            tv_tab.setTag(Integer.valueOf(i));
            if (i == 0) {
                tv_tab.setSelected(true);
            } else {
                tv_tab.setSelected(false);
            }
            this.lly_parent.addView(tv_tab);
            this.tabList.add(tv_tab);
        }
        invalidate();
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View v) {
        int index = ((Integer) v.getTag()).intValue();
        if (this.clickIndex < this.tabCount) {
            this.tabList.get(this.clickIndex).setSelected(false);
        }
        v.setSelected(true);
        this.clickIndex = index;
        if (this.tabClickListener != null) {
            this.tabClickListener.tabClick(index);
        }
    }
}
