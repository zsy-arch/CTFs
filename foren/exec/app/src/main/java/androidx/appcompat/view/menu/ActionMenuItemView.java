package androidx.appcompat.view.menu;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import androidx.appcompat.widget.ActionMenuPresenter;
import androidx.appcompat.widget.ActionMenuView;
import androidx.appcompat.widget.AppCompatTextView;
import c.a.a.C;
import c.a.e.a.l;
import c.a.e.a.p;
import c.a.e.a.s;
import c.a.e.a.w;
import c.a.e.a.y;
import c.a.f.H;
import c.a.j;

/* loaded from: classes.dex */
public class ActionMenuItemView extends AppCompatTextView implements w.a, View.OnClickListener, ActionMenuView.a {

    /* renamed from: d */
    public p f55d;

    /* renamed from: e */
    public CharSequence f56e;
    public Drawable f;
    public l.b g;
    public H h;
    public b i;
    public boolean j;
    public boolean k;
    public int l;
    public int m;
    public int n;

    /* loaded from: classes.dex */
    private class a extends H {
        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public a() {
            super(r1);
            ActionMenuItemView.this = r1;
        }

        @Override // c.a.f.H
        public y b() {
            ActionMenuPresenter.a aVar;
            b bVar = ActionMenuItemView.this.i;
            if (bVar == null || (aVar = ActionMenuPresenter.this.y) == null) {
                return null;
            }
            return aVar.b();
        }

        @Override // c.a.f.H
        public boolean c() {
            ActionMenuPresenter.a aVar;
            ActionMenuItemView actionMenuItemView = ActionMenuItemView.this;
            l.b bVar = actionMenuItemView.g;
            if (bVar == null || !bVar.a(actionMenuItemView.f55d)) {
                return false;
            }
            b bVar2 = ActionMenuItemView.this.i;
            s sVar = null;
            if (!(bVar2 == null || (aVar = ActionMenuPresenter.this.y) == null)) {
                sVar = aVar.b();
            }
            return sVar != null && sVar.b();
        }
    }

    /* loaded from: classes.dex */
    public static abstract class b {
    }

    public ActionMenuItemView(Context context) {
        this(context, null, 0);
    }

    @Override // c.a.e.a.w.a
    public void a(p pVar, int i) {
        this.f55d = pVar;
        setIcon(pVar.getIcon());
        setTitle(pVar.a(this));
        setId(pVar.f460a);
        setVisibility(pVar.isVisible() ? 0 : 8);
        setEnabled(pVar.isEnabled());
        if (pVar.hasSubMenu() && this.h == null) {
            this.h = new a();
        }
    }

    @Override // androidx.appcompat.widget.ActionMenuView.a
    public boolean b() {
        return d() && this.f55d.getIcon() == null;
    }

    @Override // c.a.e.a.w.a
    public boolean c() {
        return true;
    }

    public boolean d() {
        return !TextUtils.isEmpty(getText());
    }

    public final boolean e() {
        Configuration configuration = getContext().getResources().getConfiguration();
        int i = configuration.screenWidthDp;
        return i >= 480 || (i >= 640 && configuration.screenHeightDp >= 480) || configuration.orientation == 2;
    }

    public final void f() {
        boolean z = true;
        boolean z2 = !TextUtils.isEmpty(this.f56e);
        if (this.f != null) {
            if (!((this.f55d.z & 4) == 4) || (!this.j && !this.k)) {
                z = false;
            }
        }
        boolean z3 = z2 & z;
        CharSequence charSequence = null;
        setText(z3 ? this.f56e : null);
        CharSequence charSequence2 = this.f55d.r;
        if (TextUtils.isEmpty(charSequence2)) {
            setContentDescription(z3 ? null : this.f55d.f464e);
        } else {
            setContentDescription(charSequence2);
        }
        CharSequence charSequence3 = this.f55d.s;
        if (TextUtils.isEmpty(charSequence3)) {
            if (!z3) {
                charSequence = this.f55d.f464e;
            }
            C.a((View) this, charSequence);
            return;
        }
        C.a((View) this, charSequence3);
    }

    @Override // c.a.e.a.w.a
    public p getItemData() {
        return this.f55d;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        l.b bVar = this.g;
        if (bVar != null) {
            bVar.a(this.f55d);
        }
    }

    @Override // android.widget.TextView, android.view.View
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        this.j = e();
        f();
    }

    @Override // androidx.appcompat.widget.AppCompatTextView, android.widget.TextView, android.view.View
    public void onMeasure(int i, int i2) {
        int i3;
        boolean d2 = d();
        if (d2 && (i3 = this.m) >= 0) {
            super.setPadding(i3, getPaddingTop(), getPaddingRight(), getPaddingBottom());
        }
        super.onMeasure(i, i2);
        int mode = View.MeasureSpec.getMode(i);
        int size = View.MeasureSpec.getSize(i);
        int measuredWidth = getMeasuredWidth();
        int min = mode == Integer.MIN_VALUE ? Math.min(size, this.l) : this.l;
        if (mode != 1073741824 && this.l > 0 && measuredWidth < min) {
            super.onMeasure(View.MeasureSpec.makeMeasureSpec(min, 1073741824), i2);
        }
        if (!d2 && this.f != null) {
            super.setPadding((getMeasuredWidth() - this.f.getBounds().width()) / 2, getPaddingTop(), getPaddingRight(), getPaddingBottom());
        }
    }

    @Override // android.widget.TextView, android.view.View
    public void onRestoreInstanceState(Parcelable parcelable) {
        super.onRestoreInstanceState(null);
    }

    @Override // android.widget.TextView, android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        H h;
        if (!this.f55d.hasSubMenu() || (h = this.h) == null || !h.onTouch(this, motionEvent)) {
            return super.onTouchEvent(motionEvent);
        }
        return true;
    }

    public void setCheckable(boolean z) {
    }

    public void setChecked(boolean z) {
    }

    public void setExpandedFormat(boolean z) {
        if (this.k != z) {
            this.k = z;
            p pVar = this.f55d;
            if (pVar != null) {
                l lVar = pVar.n;
                lVar.l = true;
                lVar.b(true);
            }
        }
    }

    public void setIcon(Drawable drawable) {
        this.f = drawable;
        if (drawable != null) {
            int intrinsicWidth = drawable.getIntrinsicWidth();
            int intrinsicHeight = drawable.getIntrinsicHeight();
            int i = this.n;
            if (intrinsicWidth > i) {
                intrinsicHeight = (int) (intrinsicHeight * (i / intrinsicWidth));
                intrinsicWidth = i;
            }
            int i2 = this.n;
            if (intrinsicHeight > i2) {
                intrinsicWidth = (int) (intrinsicWidth * (i2 / intrinsicHeight));
                intrinsicHeight = i2;
            }
            drawable.setBounds(0, 0, intrinsicWidth, intrinsicHeight);
        }
        setCompoundDrawables(drawable, null, null, null);
        f();
    }

    public void setItemInvoker(l.b bVar) {
        this.g = bVar;
    }

    @Override // android.widget.TextView, android.view.View
    public void setPadding(int i, int i2, int i3, int i4) {
        this.m = i;
        super.setPadding(i, i2, i3, i4);
    }

    public void setPopupCallback(b bVar) {
        this.i = bVar;
    }

    public void setTitle(CharSequence charSequence) {
        this.f56e = charSequence;
        f();
    }

    public ActionMenuItemView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public ActionMenuItemView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        Resources resources = context.getResources();
        this.j = e();
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, j.ActionMenuItemView, i, 0);
        this.l = obtainStyledAttributes.getDimensionPixelSize(j.ActionMenuItemView_android_minWidth, 0);
        obtainStyledAttributes.recycle();
        this.n = (int) ((resources.getDisplayMetrics().density * 32.0f) + 0.5f);
        setOnClickListener(this);
        this.m = -1;
        setSaveEnabled(false);
    }

    @Override // androidx.appcompat.widget.ActionMenuView.a
    public boolean a() {
        return d();
    }
}
