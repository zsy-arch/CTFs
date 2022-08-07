package androidx.appcompat.widget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.widget.ActionMenuPresenter;
import c.a.a;
import c.a.e.a.l;
import c.a.e.a.w;
import c.a.f;
import c.a.f.AbstractC0033a;
import c.a.f.View$OnClickListenerC0035c;
import c.a.f.ka;
import c.a.f.xa;
import c.a.g;
import c.a.j;
import c.e.h.n;

/* loaded from: classes.dex */
public class ActionBarContextView extends AbstractC0033a {
    public CharSequence i;
    public CharSequence j;
    public View k;
    public View l;
    public LinearLayout m;
    public TextView n;
    public TextView o;
    public int p;
    public int q;
    public boolean r;
    public int s;

    public ActionBarContextView(Context context) {
        this(context, null, a.actionModeStyle);
    }

    public void a(c.a.e.a aVar) {
        View view = this.k;
        if (view == null) {
            this.k = LayoutInflater.from(getContext()).inflate(this.s, (ViewGroup) this, false);
            addView(this.k);
        } else if (view.getParent() == null) {
            addView(this.k);
        }
        this.k.findViewById(f.action_mode_close_button).setOnClickListener(new View$OnClickListenerC0035c(this, aVar));
        l lVar = (l) aVar.c();
        ActionMenuPresenter actionMenuPresenter = this.f575d;
        if (actionMenuPresenter != null) {
            actionMenuPresenter.b();
        }
        this.f575d = new ActionMenuPresenter(getContext());
        ActionMenuPresenter actionMenuPresenter2 = this.f575d;
        actionMenuPresenter2.l = true;
        actionMenuPresenter2.m = true;
        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(-2, -1);
        lVar.a(this.f575d, this.f573b);
        ActionMenuPresenter actionMenuPresenter3 = this.f575d;
        w wVar = actionMenuPresenter3.h;
        if (wVar == null) {
            actionMenuPresenter3.h = (w) actionMenuPresenter3.f418d.inflate(actionMenuPresenter3.f, (ViewGroup) this, false);
            actionMenuPresenter3.h.a(actionMenuPresenter3.f417c);
            actionMenuPresenter3.a(true);
        }
        w wVar2 = actionMenuPresenter3.h;
        if (wVar != wVar2) {
            ((ActionMenuView) wVar2).setPresenter(actionMenuPresenter3);
        }
        this.f574c = (ActionMenuView) wVar2;
        n.a(this.f574c, (Drawable) null);
        addView(this.f574c, layoutParams);
    }

    public final void b() {
        if (this.m == null) {
            LayoutInflater.from(getContext()).inflate(g.abc_action_bar_title_item, this);
            this.m = (LinearLayout) getChildAt(getChildCount() - 1);
            this.n = (TextView) this.m.findViewById(f.action_bar_title);
            this.o = (TextView) this.m.findViewById(f.action_bar_subtitle);
            if (this.p != 0) {
                this.n.setTextAppearance(getContext(), this.p);
            }
            if (this.q != 0) {
                this.o.setTextAppearance(getContext(), this.q);
            }
        }
        this.n.setText(this.i);
        this.o.setText(this.j);
        boolean z = !TextUtils.isEmpty(this.i);
        boolean z2 = !TextUtils.isEmpty(this.j);
        int i = 0;
        this.o.setVisibility(z2 ? 0 : 8);
        LinearLayout linearLayout = this.m;
        if (!z && !z2) {
            i = 8;
        }
        linearLayout.setVisibility(i);
        if (this.m.getParent() == null) {
            addView(this.m);
        }
    }

    public boolean c() {
        return this.r;
    }

    public void d() {
        removeAllViews();
        this.l = null;
        this.f574c = null;
    }

    public boolean e() {
        ActionMenuPresenter actionMenuPresenter = this.f575d;
        if (actionMenuPresenter != null) {
            return actionMenuPresenter.e();
        }
        return false;
    }

    @Override // android.view.ViewGroup
    public ViewGroup.LayoutParams generateDefaultLayoutParams() {
        return new ViewGroup.MarginLayoutParams(-1, -2);
    }

    @Override // android.view.ViewGroup
    public ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new ViewGroup.MarginLayoutParams(getContext(), attributeSet);
    }

    @Override // c.a.f.AbstractC0033a
    public /* bridge */ /* synthetic */ int getAnimatedVisibility() {
        return super.getAnimatedVisibility();
    }

    @Override // c.a.f.AbstractC0033a
    public int getContentHeight() {
        return this.f576e;
    }

    public CharSequence getSubtitle() {
        return this.j;
    }

    public CharSequence getTitle() {
        return this.i;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        ActionMenuPresenter actionMenuPresenter = this.f575d;
        if (actionMenuPresenter != null) {
            actionMenuPresenter.c();
            ActionMenuPresenter.a aVar = this.f575d.y;
            if (aVar != null) {
                aVar.a();
            }
        }
    }

    @Override // android.view.View
    public void onInitializeAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        if (accessibilityEvent.getEventType() == 32) {
            accessibilityEvent.setSource(this);
            accessibilityEvent.setClassName(ActionBarContextView.class.getName());
            accessibilityEvent.setPackageName(getContext().getPackageName());
            accessibilityEvent.setContentDescription(this.i);
            return;
        }
        super.onInitializeAccessibilityEvent(accessibilityEvent);
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        int i5;
        boolean a2 = xa.a(this);
        int paddingRight = a2 ? (i3 - i) - getPaddingRight() : getPaddingLeft();
        int paddingTop = getPaddingTop();
        int paddingTop2 = ((i4 - i2) - getPaddingTop()) - getPaddingBottom();
        View view = this.k;
        if (view == null || view.getVisibility() == 8) {
            i5 = paddingRight;
        } else {
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) this.k.getLayoutParams();
            int i6 = a2 ? marginLayoutParams.rightMargin : marginLayoutParams.leftMargin;
            int i7 = a2 ? marginLayoutParams.leftMargin : marginLayoutParams.rightMargin;
            int a3 = AbstractC0033a.a(paddingRight, i6, a2);
            i5 = AbstractC0033a.a(a3 + a(this.k, a3, paddingTop, paddingTop2, a2), i7, a2);
        }
        LinearLayout linearLayout = this.m;
        if (!(linearLayout == null || this.l != null || linearLayout.getVisibility() == 8)) {
            i5 += a(this.m, i5, paddingTop, paddingTop2, a2);
        }
        View view2 = this.l;
        if (view2 != null) {
            a(view2, i5, paddingTop, paddingTop2, a2);
        }
        int paddingLeft = a2 ? getPaddingLeft() : (i3 - i) - getPaddingRight();
        ActionMenuView actionMenuView = this.f574c;
        if (actionMenuView != null) {
            a(actionMenuView, paddingLeft, paddingTop, paddingTop2, !a2);
        }
    }

    @Override // android.view.View
    public void onMeasure(int i, int i2) {
        int i3 = 1073741824;
        if (View.MeasureSpec.getMode(i) != 1073741824) {
            throw new IllegalStateException(ActionBarContextView.class.getSimpleName() + " can only be used with android:layout_width=\"match_parent\" (or fill_parent)");
        } else if (View.MeasureSpec.getMode(i2) != 0) {
            int size = View.MeasureSpec.getSize(i);
            int i4 = this.f576e;
            if (i4 <= 0) {
                i4 = View.MeasureSpec.getSize(i2);
            }
            int paddingBottom = getPaddingBottom() + getPaddingTop();
            int paddingLeft = (size - getPaddingLeft()) - getPaddingRight();
            int i5 = i4 - paddingBottom;
            int makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(i5, Integer.MIN_VALUE);
            View view = this.k;
            if (view != null) {
                int a2 = a(view, paddingLeft, makeMeasureSpec, 0);
                ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) this.k.getLayoutParams();
                paddingLeft = a2 - (marginLayoutParams.leftMargin + marginLayoutParams.rightMargin);
            }
            ActionMenuView actionMenuView = this.f574c;
            if (actionMenuView != null && actionMenuView.getParent() == this) {
                paddingLeft = a(this.f574c, paddingLeft, makeMeasureSpec, 0);
            }
            LinearLayout linearLayout = this.m;
            if (linearLayout != null && this.l == null) {
                if (this.r) {
                    this.m.measure(View.MeasureSpec.makeMeasureSpec(0, 0), makeMeasureSpec);
                    int measuredWidth = this.m.getMeasuredWidth();
                    boolean z = measuredWidth <= paddingLeft;
                    if (z) {
                        paddingLeft -= measuredWidth;
                    }
                    this.m.setVisibility(z ? 0 : 8);
                } else {
                    paddingLeft = a(linearLayout, paddingLeft, makeMeasureSpec, 0);
                }
            }
            View view2 = this.l;
            if (view2 != null) {
                ViewGroup.LayoutParams layoutParams = view2.getLayoutParams();
                int i6 = layoutParams.width != -2 ? 1073741824 : Integer.MIN_VALUE;
                int i7 = layoutParams.width;
                if (i7 >= 0) {
                    paddingLeft = Math.min(i7, paddingLeft);
                }
                if (layoutParams.height == -2) {
                    i3 = Integer.MIN_VALUE;
                }
                int i8 = layoutParams.height;
                if (i8 >= 0) {
                    i5 = Math.min(i8, i5);
                }
                this.l.measure(View.MeasureSpec.makeMeasureSpec(paddingLeft, i6), View.MeasureSpec.makeMeasureSpec(i5, i3));
            }
            if (this.f576e <= 0) {
                int childCount = getChildCount();
                int i9 = 0;
                for (int i10 = 0; i10 < childCount; i10++) {
                    int measuredHeight = getChildAt(i10).getMeasuredHeight() + paddingBottom;
                    if (measuredHeight > i9) {
                        i9 = measuredHeight;
                    }
                }
                setMeasuredDimension(size, i9);
                return;
            }
            setMeasuredDimension(size, i4);
        } else {
            throw new IllegalStateException(ActionBarContextView.class.getSimpleName() + " can only be used with android:layout_height=\"wrap_content\"");
        }
    }

    @Override // c.a.f.AbstractC0033a
    public void setContentHeight(int i) {
        this.f576e = i;
    }

    public void setCustomView(View view) {
        LinearLayout linearLayout;
        View view2 = this.l;
        if (view2 != null) {
            removeView(view2);
        }
        this.l = view;
        if (!(view == null || (linearLayout = this.m) == null)) {
            removeView(linearLayout);
            this.m = null;
        }
        if (view != null) {
            addView(view);
        }
        requestLayout();
    }

    public void setSubtitle(CharSequence charSequence) {
        this.j = charSequence;
        b();
    }

    public void setTitle(CharSequence charSequence) {
        this.i = charSequence;
        b();
    }

    public void setTitleOptional(boolean z) {
        if (z != this.r) {
            requestLayout();
        }
        this.r = z;
    }

    @Override // c.a.f.AbstractC0033a, android.view.View
    public /* bridge */ /* synthetic */ void setVisibility(int i) {
        super.setVisibility(i);
    }

    @Override // android.view.ViewGroup
    public boolean shouldDelayChildPressedState() {
        return false;
    }

    public ActionBarContextView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, a.actionModeStyle);
    }

    public ActionBarContextView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        ka a2 = ka.a(context, attributeSet, j.ActionMode, i, 0);
        n.a(this, a2.b(j.ActionMode_background));
        this.p = a2.f(j.ActionMode_titleTextStyle, 0);
        this.q = a2.f(j.ActionMode_subtitleTextStyle, 0);
        this.f576e = a2.e(j.ActionMode_height, 0);
        this.s = a2.f(j.ActionMode_closeItemLayout, g.abc_action_mode_close_item_material);
        a2.f605b.recycle();
    }

    public void a() {
        if (this.k == null) {
            d();
        }
    }
}
