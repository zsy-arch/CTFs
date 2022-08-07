package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import c.a.f;
import c.a.j;
import c.e.h.n;
import com.tencent.smtt.sdk.WebView;

/* loaded from: classes.dex */
public class ButtonBarLayout extends LinearLayout {

    /* renamed from: a  reason: collision with root package name */
    public boolean f129a;

    /* renamed from: b  reason: collision with root package name */
    public int f130b = -1;

    /* renamed from: c  reason: collision with root package name */
    public int f131c = 0;

    public ButtonBarLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, j.ButtonBarLayout);
        this.f129a = obtainStyledAttributes.getBoolean(j.ButtonBarLayout_allowStacking, true);
        obtainStyledAttributes.recycle();
    }

    private void setStacked(boolean z) {
        setOrientation(z ? 1 : 0);
        setGravity(z ? 5 : 80);
        View findViewById = findViewById(f.spacer);
        if (findViewById != null) {
            findViewById.setVisibility(z ? 8 : 4);
        }
        for (int childCount = getChildCount() - 2; childCount >= 0; childCount--) {
            bringChildToFront(getChildAt(childCount));
        }
    }

    public final int a(int i) {
        int childCount = getChildCount();
        while (i < childCount) {
            if (getChildAt(i).getVisibility() == 0) {
                return i;
            }
            i++;
        }
        return -1;
    }

    @Override // android.view.View
    public int getMinimumHeight() {
        return Math.max(this.f131c, super.getMinimumHeight());
    }

    @Override // android.widget.LinearLayout, android.view.View
    public void onMeasure(int i, int i2) {
        boolean z;
        int i3;
        int size = View.MeasureSpec.getSize(i);
        int i4 = 0;
        if (this.f129a) {
            if (size > this.f130b && a()) {
                setStacked(false);
            }
            this.f130b = size;
        }
        if (a() || View.MeasureSpec.getMode(i) != 1073741824) {
            i3 = i;
            z = false;
        } else {
            i3 = View.MeasureSpec.makeMeasureSpec(size, Integer.MIN_VALUE);
            z = true;
        }
        super.onMeasure(i3, i2);
        if (this.f129a && !a()) {
            if ((getMeasuredWidthAndState() & WebView.NIGHT_MODE_COLOR) == 16777216) {
                setStacked(true);
                z = true;
            }
        }
        if (z) {
            super.onMeasure(i, i2);
        }
        int a2 = a(0);
        if (a2 >= 0) {
            View childAt = getChildAt(a2);
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) childAt.getLayoutParams();
            int measuredHeight = childAt.getMeasuredHeight() + getPaddingTop() + layoutParams.topMargin + layoutParams.bottomMargin + 0;
            if (a()) {
                int a3 = a(a2 + 1);
                i4 = a3 >= 0 ? getChildAt(a3).getPaddingTop() + ((int) (getResources().getDisplayMetrics().density * 16.0f)) + measuredHeight : measuredHeight;
            } else {
                i4 = getPaddingBottom() + measuredHeight;
            }
        }
        if (n.h(this) != i4) {
            setMinimumHeight(i4);
        }
    }

    public void setAllowStacking(boolean z) {
        if (this.f129a != z) {
            this.f129a = z;
            if (!this.f129a && getOrientation() == 1) {
                setStacked(false);
            }
            requestLayout();
        }
    }

    public final boolean a() {
        return getOrientation() == 1;
    }
}
