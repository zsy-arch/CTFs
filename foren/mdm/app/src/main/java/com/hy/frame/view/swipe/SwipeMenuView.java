package com.hy.frame.view.swipe;

import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/* loaded from: classes2.dex */
public class SwipeMenuView extends LinearLayout implements View.OnClickListener {
    private SwipeMenuLayout mLayout;
    private DeletItemListView mListView;
    private SwipeMenu mMenu;
    private OnSwipeItemClickListener onItemClickListener;
    private int position;

    /* loaded from: classes2.dex */
    public interface OnSwipeItemClickListener {
        void onItemClick(SwipeMenuView swipeMenuView, SwipeMenu swipeMenu, int i);
    }

    public int getPosition() {
        return this.position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public SwipeMenuView(SwipeMenu menu, DeletItemListView listView) {
        super(menu.getContext());
        this.mListView = listView;
        this.mMenu = menu;
        int id = 0;
        for (SwipeMenuItem item : menu.getMenuItems()) {
            addItem(item, id);
            id++;
        }
    }

    private void addItem(SwipeMenuItem item, int id) {
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(item.getWidth(), -1);
        LinearLayout parent = new LinearLayout(getContext());
        parent.setId(id);
        parent.setGravity(17);
        parent.setOrientation(1);
        parent.setLayoutParams(params);
        parent.setBackgroundDrawable(item.getBackground());
        parent.setOnClickListener(this);
        addView(parent);
        if (item.getIcon() != null) {
            parent.addView(createIcon(item));
        }
        if (!TextUtils.isEmpty(item.getTitle())) {
            parent.addView(createTitle(item));
        }
    }

    private ImageView createIcon(SwipeMenuItem item) {
        ImageView iv = new ImageView(getContext());
        iv.setImageDrawable(item.getIcon());
        return iv;
    }

    private TextView createTitle(SwipeMenuItem item) {
        TextView tv = new TextView(getContext());
        tv.setText(item.getTitle());
        tv.setGravity(17);
        tv.setTextSize(item.getTitleSize());
        tv.setTextColor(item.getTitleColor());
        return tv;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View v) {
        if (this.onItemClickListener != null && this.mLayout.isOpen()) {
            this.onItemClickListener.onItemClick(this, this.mMenu, v.getId());
        }
    }

    public OnSwipeItemClickListener getOnSwipeItemClickListener() {
        return this.onItemClickListener;
    }

    public void setOnSwipeItemClickListener(OnSwipeItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public void setLayout(SwipeMenuLayout mLayout) {
        this.mLayout = mLayout;
    }
}
