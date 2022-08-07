package android.support.design.widget;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.util.AttributeSet;
import android.view.View;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class ViewOffsetBehavior<V extends View> extends CoordinatorLayout.Behavior<V> {
    private ViewOffsetHelper mViewOffsetHelper;
    private int mTempTopBottomOffset = 0;
    private int mTempLeftRightOffset = 0;

    public ViewOffsetBehavior() {
    }

    public ViewOffsetBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override // android.support.design.widget.CoordinatorLayout.Behavior
    public boolean onLayoutChild(CoordinatorLayout parent, V child, int layoutDirection) {
        layoutChild(parent, child, layoutDirection);
        if (this.mViewOffsetHelper == null) {
            this.mViewOffsetHelper = new ViewOffsetHelper(child);
        }
        this.mViewOffsetHelper.onViewLayout();
        if (this.mTempTopBottomOffset != 0) {
            this.mViewOffsetHelper.setTopAndBottomOffset(this.mTempTopBottomOffset);
            this.mTempTopBottomOffset = 0;
        }
        if (this.mTempLeftRightOffset == 0) {
            return true;
        }
        this.mViewOffsetHelper.setLeftAndRightOffset(this.mTempLeftRightOffset);
        this.mTempLeftRightOffset = 0;
        return true;
    }

    public void layoutChild(CoordinatorLayout parent, V child, int layoutDirection) {
        parent.onLayoutChild(child, layoutDirection);
    }

    public boolean setTopAndBottomOffset(int offset) {
        if (this.mViewOffsetHelper != null) {
            return this.mViewOffsetHelper.setTopAndBottomOffset(offset);
        }
        this.mTempTopBottomOffset = offset;
        return false;
    }

    public boolean setLeftAndRightOffset(int offset) {
        if (this.mViewOffsetHelper != null) {
            return this.mViewOffsetHelper.setLeftAndRightOffset(offset);
        }
        this.mTempLeftRightOffset = offset;
        return false;
    }

    public int getTopAndBottomOffset() {
        if (this.mViewOffsetHelper != null) {
            return this.mViewOffsetHelper.getTopAndBottomOffset();
        }
        return 0;
    }

    public int getLeftAndRightOffset() {
        if (this.mViewOffsetHelper != null) {
            return this.mViewOffsetHelper.getLeftAndRightOffset();
        }
        return 0;
    }
}
