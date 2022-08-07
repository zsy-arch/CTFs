package android.support.v7.widget;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/* loaded from: classes.dex */
class ScrollbarHelper {
    ScrollbarHelper() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int computeScrollOffset(RecyclerView.State state, OrientationHelper orientation, View startChild, View endChild, RecyclerView.LayoutManager lm, boolean smoothScrollbarEnabled, boolean reverseLayout) {
        int itemsBefore;
        if (lm.getChildCount() == 0 || state.getItemCount() == 0 || startChild == null || endChild == null) {
            return 0;
        }
        int minPosition = Math.min(lm.getPosition(startChild), lm.getPosition(endChild));
        int maxPosition = Math.max(lm.getPosition(startChild), lm.getPosition(endChild));
        if (reverseLayout) {
            itemsBefore = Math.max(0, (state.getItemCount() - maxPosition) - 1);
        } else {
            itemsBefore = Math.max(0, minPosition);
        }
        if (!smoothScrollbarEnabled) {
            return itemsBefore;
        }
        return Math.round((itemsBefore * (Math.abs(orientation.getDecoratedEnd(endChild) - orientation.getDecoratedStart(startChild)) / (Math.abs(lm.getPosition(startChild) - lm.getPosition(endChild)) + 1))) + (orientation.getStartAfterPadding() - orientation.getDecoratedStart(startChild)));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int computeScrollExtent(RecyclerView.State state, OrientationHelper orientation, View startChild, View endChild, RecyclerView.LayoutManager lm, boolean smoothScrollbarEnabled) {
        if (lm.getChildCount() == 0 || state.getItemCount() == 0 || startChild == null || endChild == null) {
            return 0;
        }
        if (!smoothScrollbarEnabled) {
            return Math.abs(lm.getPosition(startChild) - lm.getPosition(endChild)) + 1;
        }
        return Math.min(orientation.getTotalSpace(), orientation.getDecoratedEnd(endChild) - orientation.getDecoratedStart(startChild));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int computeScrollRange(RecyclerView.State state, OrientationHelper orientation, View startChild, View endChild, RecyclerView.LayoutManager lm, boolean smoothScrollbarEnabled) {
        if (lm.getChildCount() == 0 || state.getItemCount() == 0 || startChild == null || endChild == null) {
            return 0;
        }
        if (!smoothScrollbarEnabled) {
            return state.getItemCount();
        }
        return (int) (((orientation.getDecoratedEnd(endChild) - orientation.getDecoratedStart(startChild)) / (Math.abs(lm.getPosition(startChild) - lm.getPosition(endChild)) + 1)) * state.getItemCount());
    }
}
