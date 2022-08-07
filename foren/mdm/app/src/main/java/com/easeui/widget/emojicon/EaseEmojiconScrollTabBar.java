package com.easeui.widget.emojicon;

import android.content.Context;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import com.hyphenate.util.DensityUtil;
import com.vsf2f.f2f.R;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
public class EaseEmojiconScrollTabBar extends RelativeLayout {
    private Context context;
    private EaseScrollTabBarItemClickListener itemClickListener;
    private HorizontalScrollView scrollView;
    private LinearLayout tabContainer;
    private List<ImageView> tabList;

    /* loaded from: classes.dex */
    public interface EaseScrollTabBarItemClickListener {
        void onItemClick(int i);
    }

    public EaseEmojiconScrollTabBar(Context context) {
        this(context, null);
    }

    public EaseEmojiconScrollTabBar(Context context, AttributeSet attrs, int defStyle) {
        this(context, attrs);
    }

    public EaseEmojiconScrollTabBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.tabList = new ArrayList();
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        this.context = context;
        LayoutInflater.from(context).inflate(R.layout.ease_widget_emojicon_tab_bar, this);
        this.scrollView = (HorizontalScrollView) findViewById(R.id.scroll_view);
        this.tabContainer = (LinearLayout) findViewById(R.id.tab_container);
    }

    public void addTab(int icon) {
        View tabView = View.inflate(this.context, R.layout.ease_scroll_tab_item, null);
        ImageView imageView = (ImageView) tabView.findViewById(R.id.iv_icon);
        imageView.setImageResource(icon);
        imageView.setLayoutParams(new LinearLayout.LayoutParams(DensityUtil.dip2px(this.context, 60), -1));
        this.tabContainer.addView(tabView);
        this.tabList.add(imageView);
        final int position = this.tabList.size() - 1;
        imageView.setOnClickListener(new View.OnClickListener() { // from class: com.easeui.widget.emojicon.EaseEmojiconScrollTabBar.1
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                if (EaseEmojiconScrollTabBar.this.itemClickListener != null) {
                    EaseEmojiconScrollTabBar.this.itemClickListener.onItemClick(position);
                }
            }
        });
    }

    public void removeTab(int position) {
        this.tabContainer.removeViewAt(position);
        this.tabList.remove(position);
    }

    public void selectedTo(int position) {
        scrollTo(position);
        for (int i = 0; i < this.tabList.size(); i++) {
            if (position == i) {
                this.tabList.get(i).setBackgroundColor(getResources().getColor(R.color.emojicon_tab_selected));
            } else {
                this.tabList.get(i).setBackgroundColor(getResources().getColor(R.color.emojicon_tab_nomal));
            }
        }
    }

    private void scrollTo(final int position) {
        if (position < this.tabContainer.getChildCount()) {
            this.scrollView.post(new Runnable() { // from class: com.easeui.widget.emojicon.EaseEmojiconScrollTabBar.2
                @Override // java.lang.Runnable
                public void run() {
                    int mScrollX = EaseEmojiconScrollTabBar.this.tabContainer.getScrollX();
                    int childX = (int) ViewCompat.getX(EaseEmojiconScrollTabBar.this.tabContainer.getChildAt(position));
                    if (childX < mScrollX) {
                        EaseEmojiconScrollTabBar.this.scrollView.scrollTo(childX, 0);
                        return;
                    }
                    int childRight = childX + EaseEmojiconScrollTabBar.this.tabContainer.getChildAt(position).getWidth();
                    int scrollRight = mScrollX + EaseEmojiconScrollTabBar.this.scrollView.getWidth();
                    if (childRight > scrollRight) {
                        EaseEmojiconScrollTabBar.this.scrollView.scrollTo(childRight - scrollRight, 0);
                    }
                }
            });
        }
    }

    public void setTabBarItemClickListener(EaseScrollTabBarItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }
}
