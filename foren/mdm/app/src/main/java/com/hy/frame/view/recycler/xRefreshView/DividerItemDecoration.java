package com.hy.frame.view.recycler.xRefreshView;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import com.hy.frame.R;

/* loaded from: classes2.dex */
public class DividerItemDecoration extends RecyclerView.ItemDecoration {
    public static final int HORIZONTAL_LIST = 0;
    public static final int VERTICAL_LIST = 1;
    private Drawable mDivider;
    private int mOrientation;

    public DividerItemDecoration(Context context, int orientation) {
        this.mDivider = context.getResources().getDrawable(R.drawable.shape_recycle_divider);
        setOrientation(orientation);
    }

    public void setOrientation(int orientation) {
        if (orientation == 0 || orientation == 1) {
            this.mOrientation = orientation;
            return;
        }
        throw new IllegalArgumentException("invalid orientation");
    }

    @Override // android.support.v7.widget.RecyclerView.ItemDecoration
    public void onDraw(Canvas c, RecyclerView parent) {
        Log.v("recycler - itemd", "onDraw()");
        if (this.mOrientation == 1) {
            drawVertical(c, parent);
        } else {
            drawHorizontal(c, parent);
        }
    }

    public void drawVertical(Canvas c, RecyclerView parent) {
        int left = parent.getPaddingLeft();
        int right = parent.getWidth() - parent.getPaddingRight();
        int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View child = parent.getChildAt(i);
            int top = child.getBottom() + ((RecyclerView.LayoutParams) child.getLayoutParams()).bottomMargin;
            this.mDivider.setBounds(left, top, right, top + this.mDivider.getIntrinsicHeight());
            this.mDivider.draw(c);
        }
    }

    public void drawHorizontal(Canvas c, RecyclerView parent) {
        int top = parent.getPaddingTop();
        int bottom = parent.getHeight() - parent.getPaddingBottom();
        int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View child = parent.getChildAt(i);
            int left = child.getRight() + ((RecyclerView.LayoutParams) child.getLayoutParams()).rightMargin;
            this.mDivider.setBounds(left, top, left + this.mDivider.getIntrinsicHeight(), bottom);
            this.mDivider.draw(c);
        }
    }

    @Override // android.support.v7.widget.RecyclerView.ItemDecoration
    public void getItemOffsets(Rect outRect, int itemPosition, RecyclerView parent) {
        if (this.mOrientation == 1) {
            outRect.set(0, 0, 0, this.mDivider.getIntrinsicHeight());
        } else {
            outRect.set(0, 0, this.mDivider.getIntrinsicWidth(), 0);
        }
    }
}
