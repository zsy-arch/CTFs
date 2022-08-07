package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.ActionMode;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import c.a.f;
import c.a.f.C0034b;
import c.a.f.P;
import c.a.j;
import c.e.h.n;

/* loaded from: classes.dex */
public class ActionBarContainer extends FrameLayout {

    /* renamed from: a */
    public boolean f65a;

    /* renamed from: b */
    public View f66b;

    /* renamed from: c */
    public View f67c;

    /* renamed from: d */
    public View f68d;

    /* renamed from: e */
    public Drawable f69e;
    public Drawable f;
    public Drawable g;
    public boolean h;
    public boolean i;
    public int j;

    public ActionBarContainer(Context context) {
        this(context, null);
    }

    public final int a(View view) {
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) view.getLayoutParams();
        return view.getMeasuredHeight() + layoutParams.topMargin + layoutParams.bottomMargin;
    }

    public final boolean b(View view) {
        return view == null || view.getVisibility() == 8 || view.getMeasuredHeight() == 0;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void drawableStateChanged() {
        super.drawableStateChanged();
        Drawable drawable = this.f69e;
        if (drawable != null && drawable.isStateful()) {
            this.f69e.setState(getDrawableState());
        }
        Drawable drawable2 = this.f;
        if (drawable2 != null && drawable2.isStateful()) {
            this.f.setState(getDrawableState());
        }
        Drawable drawable3 = this.g;
        if (drawable3 != null && drawable3.isStateful()) {
            this.g.setState(getDrawableState());
        }
    }

    public View getTabContainer() {
        return this.f66b;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void jumpDrawablesToCurrentState() {
        super.jumpDrawablesToCurrentState();
        Drawable drawable = this.f69e;
        if (drawable != null) {
            drawable.jumpToCurrentState();
        }
        Drawable drawable2 = this.f;
        if (drawable2 != null) {
            drawable2.jumpToCurrentState();
        }
        Drawable drawable3 = this.g;
        if (drawable3 != null) {
            drawable3.jumpToCurrentState();
        }
    }

    @Override // android.view.View
    public void onFinishInflate() {
        super.onFinishInflate();
        this.f67c = findViewById(f.action_bar);
        this.f68d = findViewById(f.action_context_bar);
    }

    @Override // android.view.View
    public boolean onHoverEvent(MotionEvent motionEvent) {
        super.onHoverEvent(motionEvent);
        return true;
    }

    @Override // android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        return this.f65a || super.onInterceptTouchEvent(motionEvent);
    }

    @Override // android.widget.FrameLayout, android.view.ViewGroup, android.view.View
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        Drawable drawable;
        super.onLayout(z, i, i2, i3, i4);
        View view = this.f66b;
        boolean z2 = true;
        z2 = false;
        boolean z3 = (view == null || view.getVisibility() == 8) ? false : true;
        if (!(view == null || view.getVisibility() == 8)) {
            int measuredHeight = getMeasuredHeight();
            FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) view.getLayoutParams();
            view.layout(i, (measuredHeight - view.getMeasuredHeight()) - layoutParams.bottomMargin, i3, measuredHeight - layoutParams.bottomMargin);
        }
        if (this.h) {
            Drawable drawable2 = this.g;
            if (drawable2 != null) {
                drawable2.setBounds(0, 0, getMeasuredWidth(), getMeasuredHeight());
            } else {
                z2 = false;
            }
        } else {
            if (this.f69e != null) {
                if (this.f67c.getVisibility() == 0) {
                    this.f69e.setBounds(this.f67c.getLeft(), this.f67c.getTop(), this.f67c.getRight(), this.f67c.getBottom());
                } else {
                    View view2 = this.f68d;
                    if (view2 == null || view2.getVisibility() != 0) {
                        this.f69e.setBounds(0, 0, 0, 0);
                    } else {
                        this.f69e.setBounds(this.f68d.getLeft(), this.f68d.getTop(), this.f68d.getRight(), this.f68d.getBottom());
                    }
                }
                z2 = true;
            }
            this.i = z3;
            if (z3 && (drawable = this.f) != null) {
                drawable.setBounds(view.getLeft(), view.getTop(), view.getRight(), view.getBottom());
            }
        }
        if (z2) {
            invalidate();
        }
    }

    @Override // android.widget.FrameLayout, android.view.View
    public void onMeasure(int i, int i2) {
        int i3;
        int i4;
        if (this.f67c == null && View.MeasureSpec.getMode(i2) == Integer.MIN_VALUE && (i4 = this.j) >= 0) {
            i2 = View.MeasureSpec.makeMeasureSpec(Math.min(i4, View.MeasureSpec.getSize(i2)), Integer.MIN_VALUE);
        }
        super.onMeasure(i, i2);
        if (this.f67c != null) {
            int mode = View.MeasureSpec.getMode(i2);
            View view = this.f66b;
            if (view != null && view.getVisibility() != 8 && mode != 1073741824) {
                if (!b(this.f67c)) {
                    i3 = a(this.f67c);
                } else {
                    i3 = !b(this.f68d) ? a(this.f68d) : 0;
                }
                setMeasuredDimension(getMeasuredWidth(), Math.min(a(this.f66b) + i3, mode == Integer.MIN_VALUE ? View.MeasureSpec.getSize(i2) : Integer.MAX_VALUE));
            }
        }
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        super.onTouchEvent(motionEvent);
        return true;
    }

    public void setPrimaryBackground(Drawable drawable) {
        Drawable drawable2 = this.f69e;
        if (drawable2 != null) {
            drawable2.setCallback(null);
            unscheduleDrawable(this.f69e);
        }
        this.f69e = drawable;
        if (drawable != null) {
            drawable.setCallback(this);
            View view = this.f67c;
            if (view != null) {
                this.f69e.setBounds(view.getLeft(), this.f67c.getTop(), this.f67c.getRight(), this.f67c.getBottom());
            }
        }
        boolean z = true;
        if (!this.h ? !(this.f69e == null && this.f == null) : this.g != null) {
            z = false;
        }
        setWillNotDraw(z);
        invalidate();
    }

    public void setSplitBackground(Drawable drawable) {
        Drawable drawable2;
        Drawable drawable3 = this.g;
        if (drawable3 != null) {
            drawable3.setCallback(null);
            unscheduleDrawable(this.g);
        }
        this.g = drawable;
        boolean z = false;
        if (drawable != null) {
            drawable.setCallback(this);
            if (this.h && (drawable2 = this.g) != null) {
                drawable2.setBounds(0, 0, getMeasuredWidth(), getMeasuredHeight());
            }
        }
        if (!this.h ? this.f69e == null && this.f == null : this.g == null) {
            z = true;
        }
        setWillNotDraw(z);
        invalidate();
    }

    public void setStackedBackground(Drawable drawable) {
        Drawable drawable2;
        Drawable drawable3 = this.f;
        if (drawable3 != null) {
            drawable3.setCallback(null);
            unscheduleDrawable(this.f);
        }
        this.f = drawable;
        if (drawable != null) {
            drawable.setCallback(this);
            if (this.i && (drawable2 = this.f) != null) {
                drawable2.setBounds(this.f66b.getLeft(), this.f66b.getTop(), this.f66b.getRight(), this.f66b.getBottom());
            }
        }
        boolean z = true;
        if (!this.h ? !(this.f69e == null && this.f == null) : this.g != null) {
            z = false;
        }
        setWillNotDraw(z);
        invalidate();
    }

    public void setTabContainer(P p) {
        View view = this.f66b;
        if (view != null) {
            removeView(view);
        }
        this.f66b = p;
        if (p != null) {
            addView(p);
            ViewGroup.LayoutParams layoutParams = p.getLayoutParams();
            layoutParams.width = -1;
            layoutParams.height = -2;
            p.setAllowCollapse(false);
        }
    }

    public void setTransitioning(boolean z) {
        this.f65a = z;
        setDescendantFocusability(z ? 393216 : 262144);
    }

    @Override // android.view.View
    public void setVisibility(int i) {
        super.setVisibility(i);
        boolean z = i == 0;
        Drawable drawable = this.f69e;
        if (drawable != null) {
            drawable.setVisible(z, false);
        }
        Drawable drawable2 = this.f;
        if (drawable2 != null) {
            drawable2.setVisible(z, false);
        }
        Drawable drawable3 = this.g;
        if (drawable3 != null) {
            drawable3.setVisible(z, false);
        }
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public ActionMode startActionModeForChild(View view, ActionMode.Callback callback) {
        return null;
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public ActionMode startActionModeForChild(View view, ActionMode.Callback callback, int i) {
        if (i != 0) {
            return super.startActionModeForChild(view, callback, i);
        }
        return null;
    }

    @Override // android.view.View
    public boolean verifyDrawable(Drawable drawable) {
        return (drawable == this.f69e && !this.h) || (drawable == this.f && this.i) || ((drawable == this.g && this.h) || super.verifyDrawable(drawable));
    }

    public ActionBarContainer(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        n.a(this, new C0034b(this));
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, j.ActionBar);
        this.f69e = obtainStyledAttributes.getDrawable(j.ActionBar_background);
        this.f = obtainStyledAttributes.getDrawable(j.ActionBar_backgroundStacked);
        this.j = obtainStyledAttributes.getDimensionPixelSize(j.ActionBar_height, -1);
        if (getId() == f.split_action_bar) {
            this.h = true;
            this.g = obtainStyledAttributes.getDrawable(j.ActionBar_backgroundSplit);
        }
        obtainStyledAttributes.recycle();
        boolean z = false;
        if (!this.h ? this.f69e == null && this.f == null : this.g == null) {
            z = true;
        }
        setWillNotDraw(z);
    }
}
