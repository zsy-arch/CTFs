package com.hy.frame.view;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RoundRectShape;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TabWidget;
import android.widget.TextView;

/* loaded from: classes2.dex */
public class BadgeView extends TextView {
    private boolean mHideOnNull;

    public BadgeView(Context context) {
        this(context, null);
    }

    public BadgeView(Context context, AttributeSet attrs) {
        this(context, attrs, 16842884);
    }

    public BadgeView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.mHideOnNull = true;
        init();
    }

    private void init() {
        if (!(getLayoutParams() instanceof FrameLayout.LayoutParams)) {
            setLayoutParams(new FrameLayout.LayoutParams(-2, -2, 53));
        }
        setTextColor(-1);
        setTypeface(Typeface.DEFAULT_BOLD);
        setTextSize(2, 11.0f);
        setPadding(dip2Px(5.0f), dip2Px(1.0f), dip2Px(5.0f), dip2Px(1.0f));
        setBackground(9, Color.parseColor("#d3321b"));
        setGravity(17);
        setHideOnNull(true);
        setBadgeCount(0);
    }

    public void setBackground(int dipRadius, int badgeColor) {
        int radius = dip2Px(dipRadius);
        ShapeDrawable bgDrawable = new ShapeDrawable(new RoundRectShape(new float[]{radius, radius, radius, radius, radius, radius, radius, radius}, null, null));
        bgDrawable.getPaint().setColor(badgeColor);
        setBackgroundDrawable(bgDrawable);
    }

    public boolean isHideOnNull() {
        return this.mHideOnNull;
    }

    public void setHideOnNull(boolean hideOnNull) {
        this.mHideOnNull = hideOnNull;
        setText(getText());
    }

    @Override // android.widget.TextView
    public void setText(CharSequence text, TextView.BufferType type) {
        if (!isHideOnNull() || (text != null && !text.toString().equalsIgnoreCase("0"))) {
            setVisibility(0);
        } else {
            setVisibility(8);
        }
        super.setText(text, type);
    }

    public void setBadgeCount(int count) {
        setText(String.valueOf(count));
    }

    public Integer getBadgeCount() {
        if (getText() == null) {
            return null;
        }
        try {
            return Integer.valueOf(Integer.parseInt(getText().toString()));
        } catch (NumberFormatException e) {
            return null;
        }
    }

    public void setBadgeGravity(int gravity) {
        FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) getLayoutParams();
        params.gravity = gravity;
        setLayoutParams(params);
    }

    public int getBadgeGravity() {
        return ((FrameLayout.LayoutParams) getLayoutParams()).gravity;
    }

    public void setBadgeMargin(int dipMargin) {
        setBadgeMargin(dipMargin, dipMargin, dipMargin, dipMargin);
    }

    public void setBadgeMargin(int leftDipMargin, int topDipMargin, int rightDipMargin, int bottomDipMargin) {
        FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) getLayoutParams();
        params.leftMargin = dip2Px(leftDipMargin);
        params.topMargin = dip2Px(topDipMargin);
        params.rightMargin = dip2Px(rightDipMargin);
        params.bottomMargin = dip2Px(bottomDipMargin);
        setLayoutParams(params);
    }

    public int[] getBadgeMargin() {
        FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) getLayoutParams();
        return new int[]{params.leftMargin, params.topMargin, params.rightMargin, params.bottomMargin};
    }

    public void incrementBadgeCount(int increment) {
        Integer count = getBadgeCount();
        if (count == null) {
            setBadgeCount(increment);
        } else {
            setBadgeCount(count.intValue() + increment);
        }
    }

    public void decrementBadgeCount(int decrement) {
        incrementBadgeCount(-decrement);
    }

    public void setTargetView(TabWidget target, int tabIndex) {
        setTargetView(target.getChildTabViewAt(tabIndex));
    }

    public void setTargetView(View target) {
        if (getParent() != null) {
            ((ViewGroup) getParent()).removeView(this);
        }
        if (target == null) {
            return;
        }
        if (target.getParent() instanceof FrameLayout) {
            ((FrameLayout) target.getParent()).addView(this);
        } else if (target.getParent() instanceof ViewGroup) {
            ViewGroup parentContainer = (ViewGroup) target.getParent();
            int groupIndex = parentContainer.indexOfChild(target);
            parentContainer.removeView(target);
            FrameLayout badgeContainer = new FrameLayout(getContext());
            parentContainer.addView(badgeContainer, groupIndex, target.getLayoutParams());
            badgeContainer.addView(target);
            badgeContainer.addView(this);
        } else if (target.getParent() == null) {
            Log.e(getClass().getSimpleName(), "ParentView is needed");
        }
    }

    private int dip2Px(float dip) {
        return (int) ((getContext().getResources().getDisplayMetrics().density * dip) + 0.5f);
    }
}
