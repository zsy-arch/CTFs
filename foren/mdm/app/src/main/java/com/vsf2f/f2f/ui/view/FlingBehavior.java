package com.vsf2f.f2f.ui.view;

import android.content.Context;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/* loaded from: classes2.dex */
public final class FlingBehavior extends AppBarLayout.Behavior {
    private static final int TOP_CHILD_FLING_THRESHOLD = 3;
    private boolean isPositive;

    public FlingBehavior() {
    }

    public FlingBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public boolean onNestedFling(CoordinatorLayout coordinatorLayout, AppBarLayout child, View target, float velocityX, float velocityY, boolean consumed) {
        if ((velocityY > 0.0f && !this.isPositive) || (velocityY < 0.0f && this.isPositive)) {
            velocityY *= -1.0f;
        }
        Log.d("FlingBehavior", target + "");
        if ((target instanceof RecyclerView) && velocityY < 0.0f) {
            RecyclerView recyclerView = (RecyclerView) target;
            consumed = recyclerView.getChildAdapterPosition(recyclerView.getChildAt(0)) > 3;
        }
        return super.onNestedFling(coordinatorLayout, (CoordinatorLayout) child, target, velocityX, velocityY, consumed);
    }

    public void onNestedPreScroll(CoordinatorLayout coordinatorLayout, AppBarLayout child, View target, int dx, int dy, int[] consumed) {
        super.onNestedPreScroll(coordinatorLayout, (CoordinatorLayout) child, target, dx, dy, consumed);
        this.isPositive = dy > 0;
    }
}
