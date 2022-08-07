package android.support.v7.widget.helper;

import android.graphics.Canvas;
import android.support.v4.view.ViewCompat;
import android.support.v7.recyclerview.R;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/* loaded from: classes.dex */
class ItemTouchUIUtilImpl {
    ItemTouchUIUtilImpl() {
    }

    /* loaded from: classes.dex */
    static class Api21Impl extends BaseImpl {
        /* JADX INFO: Multiple debug info for r1v1 java.lang.Float: [D('originalElevation' java.lang.Float), D('originalElevation' java.lang.Object)] */
        @Override // android.support.v7.widget.helper.ItemTouchUIUtilImpl.BaseImpl, android.support.v7.widget.helper.ItemTouchUIUtil
        public void onDraw(Canvas c, RecyclerView recyclerView, View view, float dX, float dY, int actionState, boolean isCurrentlyActive) {
            if (isCurrentlyActive && view.getTag(R.id.item_touch_helper_previous_elevation) == null) {
                Object originalElevation = Float.valueOf(ViewCompat.getElevation(view));
                ViewCompat.setElevation(view, 1.0f + findMaxElevation(recyclerView, view));
                view.setTag(R.id.item_touch_helper_previous_elevation, originalElevation);
            }
            super.onDraw(c, recyclerView, view, dX, dY, actionState, isCurrentlyActive);
        }

        private float findMaxElevation(RecyclerView recyclerView, View itemView) {
            int childCount = recyclerView.getChildCount();
            float max = 0.0f;
            for (int i = 0; i < childCount; i++) {
                View child = recyclerView.getChildAt(i);
                if (child != itemView) {
                    float elevation = ViewCompat.getElevation(child);
                    if (elevation > max) {
                        max = elevation;
                    }
                }
            }
            return max;
        }

        @Override // android.support.v7.widget.helper.ItemTouchUIUtilImpl.BaseImpl, android.support.v7.widget.helper.ItemTouchUIUtil
        public void clearView(View view) {
            Object tag = view.getTag(R.id.item_touch_helper_previous_elevation);
            if (tag != null && (tag instanceof Float)) {
                ViewCompat.setElevation(view, ((Float) tag).floatValue());
            }
            view.setTag(R.id.item_touch_helper_previous_elevation, null);
            super.clearView(view);
        }
    }

    /* loaded from: classes.dex */
    static class BaseImpl implements ItemTouchUIUtil {
        @Override // android.support.v7.widget.helper.ItemTouchUIUtil
        public void clearView(View view) {
            view.setTranslationX(0.0f);
            view.setTranslationY(0.0f);
        }

        @Override // android.support.v7.widget.helper.ItemTouchUIUtil
        public void onSelected(View view) {
        }

        @Override // android.support.v7.widget.helper.ItemTouchUIUtil
        public void onDraw(Canvas c, RecyclerView recyclerView, View view, float dX, float dY, int actionState, boolean isCurrentlyActive) {
            view.setTranslationX(dX);
            view.setTranslationY(dY);
        }

        @Override // android.support.v7.widget.helper.ItemTouchUIUtil
        public void onDrawOver(Canvas c, RecyclerView recyclerView, View view, float dX, float dY, int actionState, boolean isCurrentlyActive) {
        }
    }
}
