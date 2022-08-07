package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.ContextThemeWrapper;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewDebug;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
import androidx.appcompat.view.menu.ActionMenuItemView;
import androidx.appcompat.widget.ActionMenuPresenter;
import androidx.appcompat.widget.LinearLayoutCompat;
import c.a.e.a.l;
import c.a.e.a.p;
import c.a.e.a.v;
import c.a.e.a.w;
import c.a.f.la;
import c.a.f.xa;

/* loaded from: classes.dex */
public class ActionMenuView extends LinearLayoutCompat implements l.b, w {
    public e A;
    public l p;
    public Context q;
    public int r;
    public boolean s;
    public ActionMenuPresenter t;
    public v.a u;
    public l.a v;
    public boolean w;
    public int x;
    public int y;
    public int z;

    /* loaded from: classes.dex */
    public interface a {
        boolean a();

        boolean b();
    }

    /* loaded from: classes.dex */
    public static class b implements v.a {
        @Override // c.a.e.a.v.a
        public void a(l lVar, boolean z) {
        }

        @Override // c.a.e.a.v.a
        public boolean a(l lVar) {
            return false;
        }
    }

    /* loaded from: classes.dex */
    public static class c extends LinearLayoutCompat.a {
        @ViewDebug.ExportedProperty

        /* renamed from: c */
        public boolean f81c;
        @ViewDebug.ExportedProperty

        /* renamed from: d */
        public int f82d;
        @ViewDebug.ExportedProperty

        /* renamed from: e */
        public int f83e;
        @ViewDebug.ExportedProperty
        public boolean f;
        @ViewDebug.ExportedProperty
        public boolean g;
        public boolean h;

        public c(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
        }

        public c(ViewGroup.LayoutParams layoutParams) {
            super(layoutParams);
        }

        public c(c cVar) {
            super(cVar);
            this.f81c = cVar.f81c;
        }

        public c(int i, int i2) {
            super(i, i2);
            this.f81c = false;
        }
    }

    /* loaded from: classes.dex */
    public interface e {
    }

    public ActionMenuView(Context context) {
        this(context, null);
    }

    public static int a(View view, int i, int i2, int i3, int i4) {
        c cVar = (c) view.getLayoutParams();
        int makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.getSize(i3) - i4, View.MeasureSpec.getMode(i3));
        ActionMenuItemView actionMenuItemView = view instanceof ActionMenuItemView ? (ActionMenuItemView) view : null;
        boolean z = true;
        boolean z2 = actionMenuItemView != null && actionMenuItemView.d();
        int i5 = 2;
        if (i2 <= 0 || (z2 && i2 < 2)) {
            i5 = 0;
        } else {
            view.measure(View.MeasureSpec.makeMeasureSpec(i2 * i, Integer.MIN_VALUE), makeMeasureSpec);
            int measuredWidth = view.getMeasuredWidth();
            int i6 = measuredWidth / i;
            if (measuredWidth % i != 0) {
                i6++;
            }
            if (!z2 || i6 >= 2) {
                i5 = i6;
            }
        }
        if (cVar.f81c || !z2) {
            z = false;
        }
        cVar.f = z;
        cVar.f82d = i5;
        view.measure(View.MeasureSpec.makeMeasureSpec(i * i5, 1073741824), makeMeasureSpec);
        return i5;
    }

    public c b() {
        c generateDefaultLayoutParams = generateDefaultLayoutParams();
        generateDefaultLayoutParams.f81c = true;
        return generateDefaultLayoutParams;
    }

    public boolean c() {
        ActionMenuPresenter actionMenuPresenter = this.t;
        return actionMenuPresenter != null && actionMenuPresenter.c();
    }

    @Override // androidx.appcompat.widget.LinearLayoutCompat, android.view.ViewGroup
    public boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return layoutParams != null && (layoutParams instanceof c);
    }

    public boolean d() {
        ActionMenuPresenter actionMenuPresenter = this.t;
        if (actionMenuPresenter != null) {
            if (actionMenuPresenter.z != null || actionMenuPresenter.d()) {
                return true;
            }
        }
        return false;
    }

    @Override // android.view.View
    public boolean dispatchPopulateAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        return false;
    }

    public boolean e() {
        ActionMenuPresenter actionMenuPresenter = this.t;
        return actionMenuPresenter != null && actionMenuPresenter.d();
    }

    public boolean f() {
        return this.s;
    }

    public l g() {
        return this.p;
    }

    public Menu getMenu() {
        if (this.p == null) {
            Context context = getContext();
            this.p = new l(context);
            this.p.a(new d());
            this.t = new ActionMenuPresenter(context);
            ActionMenuPresenter actionMenuPresenter = this.t;
            actionMenuPresenter.l = true;
            actionMenuPresenter.m = true;
            v.a aVar = this.u;
            if (aVar == null) {
                aVar = new b();
            }
            actionMenuPresenter.f419e = aVar;
            this.p.a(this.t, this.q);
            ActionMenuPresenter actionMenuPresenter2 = this.t;
            actionMenuPresenter2.h = this;
            a(actionMenuPresenter2.f417c);
        }
        return this.p;
    }

    public Drawable getOverflowIcon() {
        getMenu();
        ActionMenuPresenter actionMenuPresenter = this.t;
        ActionMenuPresenter.d dVar = actionMenuPresenter.i;
        if (dVar != null) {
            return dVar.getDrawable();
        }
        if (actionMenuPresenter.k) {
            return actionMenuPresenter.j;
        }
        return null;
    }

    public int getPopupTheme() {
        return this.r;
    }

    public int getWindowAnimations() {
        return 0;
    }

    public boolean h() {
        ActionMenuPresenter actionMenuPresenter = this.t;
        return actionMenuPresenter != null && actionMenuPresenter.e();
    }

    @Override // android.view.View
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        ActionMenuPresenter actionMenuPresenter = this.t;
        if (actionMenuPresenter != null) {
            actionMenuPresenter.a(false);
            if (this.t.d()) {
                this.t.c();
                this.t.e();
            }
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        a();
    }

    @Override // androidx.appcompat.widget.LinearLayoutCompat, android.view.ViewGroup, android.view.View
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        int i5;
        int i6;
        int i7;
        int i8;
        if (this.w) {
            int childCount = getChildCount();
            int i9 = (i4 - i2) / 2;
            int dividerWidth = getDividerWidth();
            int i10 = i3 - i;
            int paddingRight = (i10 - getPaddingRight()) - getPaddingLeft();
            boolean a2 = xa.a(this);
            int i11 = 0;
            int i12 = 0;
            for (int i13 = 0; i13 < childCount; i13++) {
                View childAt = getChildAt(i13);
                if (childAt.getVisibility() != 8) {
                    c cVar = (c) childAt.getLayoutParams();
                    if (cVar.f81c) {
                        int measuredWidth = childAt.getMeasuredWidth();
                        if (d(i13)) {
                            measuredWidth += dividerWidth;
                        }
                        int measuredHeight = childAt.getMeasuredHeight();
                        if (a2) {
                            i7 = getPaddingLeft() + ((ViewGroup.MarginLayoutParams) cVar).leftMargin;
                            i8 = i7 + measuredWidth;
                        } else {
                            i8 = (getWidth() - getPaddingRight()) - ((ViewGroup.MarginLayoutParams) cVar).rightMargin;
                            i7 = i8 - measuredWidth;
                        }
                        int i14 = i9 - (measuredHeight / 2);
                        childAt.layout(i7, i14, i8, measuredHeight + i14);
                        paddingRight -= measuredWidth;
                        i11 = 1;
                    } else {
                        paddingRight -= (childAt.getMeasuredWidth() + ((ViewGroup.MarginLayoutParams) cVar).leftMargin) + ((ViewGroup.MarginLayoutParams) cVar).rightMargin;
                        d(i13);
                        i12++;
                    }
                }
            }
            if (childCount == 1 && i11 == 0) {
                View childAt2 = getChildAt(0);
                int measuredWidth2 = childAt2.getMeasuredWidth();
                int measuredHeight2 = childAt2.getMeasuredHeight();
                int i15 = (i10 / 2) - (measuredWidth2 / 2);
                int i16 = i9 - (measuredHeight2 / 2);
                childAt2.layout(i15, i16, measuredWidth2 + i15, measuredHeight2 + i16);
                return;
            }
            int i17 = i12 - (i11 ^ 1);
            if (i17 > 0) {
                i5 = paddingRight / i17;
                i6 = 0;
            } else {
                i6 = 0;
                i5 = 0;
            }
            int max = Math.max(i6, i5);
            if (a2) {
                int width = getWidth() - getPaddingRight();
                while (i6 < childCount) {
                    View childAt3 = getChildAt(i6);
                    c cVar2 = (c) childAt3.getLayoutParams();
                    if (childAt3.getVisibility() != 8 && !cVar2.f81c) {
                        int i18 = width - ((ViewGroup.MarginLayoutParams) cVar2).rightMargin;
                        int measuredWidth3 = childAt3.getMeasuredWidth();
                        int measuredHeight3 = childAt3.getMeasuredHeight();
                        int i19 = i9 - (measuredHeight3 / 2);
                        childAt3.layout(i18 - measuredWidth3, i19, i18, measuredHeight3 + i19);
                        width = i18 - ((measuredWidth3 + ((ViewGroup.MarginLayoutParams) cVar2).leftMargin) + max);
                    }
                    i6++;
                }
                return;
            }
            int paddingLeft = getPaddingLeft();
            while (i6 < childCount) {
                View childAt4 = getChildAt(i6);
                c cVar3 = (c) childAt4.getLayoutParams();
                if (childAt4.getVisibility() != 8 && !cVar3.f81c) {
                    int i20 = paddingLeft + ((ViewGroup.MarginLayoutParams) cVar3).leftMargin;
                    int measuredWidth4 = childAt4.getMeasuredWidth();
                    int measuredHeight4 = childAt4.getMeasuredHeight();
                    int i21 = i9 - (measuredHeight4 / 2);
                    childAt4.layout(i20, i21, i20 + measuredWidth4, measuredHeight4 + i21);
                    paddingLeft = measuredWidth4 + ((ViewGroup.MarginLayoutParams) cVar3).rightMargin + max + i20;
                }
                i6++;
            }
        } else if (this.f142d == 1) {
            b(i, i2, i3, i4);
        } else {
            a(i, i2, i3, i4);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:108:0x01ce  */
    /* JADX WARN: Removed duplicated region for block: B:120:0x020a  */
    /* JADX WARN: Removed duplicated region for block: B:121:0x0210  */
    /* JADX WARN: Removed duplicated region for block: B:124:0x0216  */
    /* JADX WARN: Removed duplicated region for block: B:148:0x026a  */
    /* JADX WARN: Removed duplicated region for block: B:157:0x0296  */
    /* JADX WARN: Removed duplicated region for block: B:158:0x0299  */
    /* JADX WARN: Type inference failed for: r6v15, types: [int, boolean] */
    /* JADX WARN: Type inference failed for: r6v18 */
    /* JADX WARN: Type inference failed for: r6v19 */
    /* JADX WARN: Unknown variable types count: 1 */
    @Override // androidx.appcompat.widget.LinearLayoutCompat, android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void onMeasure(int r31, int r32) {
        /*
            Method dump skipped, instructions count: 709
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.widget.ActionMenuView.onMeasure(int, int):void");
    }

    public void setExpandedActionViewsExclusive(boolean z) {
        this.t.t = z;
    }

    public void setOnMenuItemClickListener(e eVar) {
        this.A = eVar;
    }

    public void setOverflowIcon(Drawable drawable) {
        getMenu();
        ActionMenuPresenter actionMenuPresenter = this.t;
        ActionMenuPresenter.d dVar = actionMenuPresenter.i;
        if (dVar != null) {
            dVar.setImageDrawable(drawable);
            return;
        }
        actionMenuPresenter.k = true;
        actionMenuPresenter.j = drawable;
    }

    public void setOverflowReserved(boolean z) {
        this.s = z;
    }

    public void setPopupTheme(int i) {
        if (this.r != i) {
            this.r = i;
            if (i == 0) {
                this.q = getContext();
            } else {
                this.q = new ContextThemeWrapper(getContext(), i);
            }
        }
    }

    public void setPresenter(ActionMenuPresenter actionMenuPresenter) {
        this.t = actionMenuPresenter;
        ActionMenuPresenter actionMenuPresenter2 = this.t;
        actionMenuPresenter2.h = this;
        a(actionMenuPresenter2.f417c);
    }

    public ActionMenuView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet, 0);
        setBaselineAligned(false);
        float f = context.getResources().getDisplayMetrics().density;
        this.y = (int) (56.0f * f);
        this.z = (int) (f * 4.0f);
        this.q = context;
        this.r = 0;
    }

    /* loaded from: classes.dex */
    public class d implements l.a {
        public d() {
            ActionMenuView.this = r1;
        }

        @Override // c.a.e.a.l.a
        public boolean a(l lVar, MenuItem menuItem) {
            e eVar = ActionMenuView.this.A;
            if (eVar == null) {
                return false;
            }
            Toolbar toolbar = ((la) eVar).f607a;
            return false;
        }

        @Override // c.a.e.a.l.a
        public void a(l lVar) {
            l.a aVar = ActionMenuView.this.v;
            if (aVar != null) {
                aVar.a(lVar);
            }
        }
    }

    public boolean d(int i) {
        boolean z = false;
        if (i == 0) {
            return false;
        }
        View childAt = getChildAt(i - 1);
        View childAt2 = getChildAt(i);
        if (i < getChildCount() && (childAt instanceof a)) {
            z = false | ((a) childAt).a();
        }
        return (i <= 0 || !(childAt2 instanceof a)) ? z : z | ((a) childAt2).b();
    }

    @Override // androidx.appcompat.widget.LinearLayoutCompat, android.view.ViewGroup
    public c generateDefaultLayoutParams() {
        c cVar = new c(-2, -2);
        cVar.f145b = 16;
        return cVar;
    }

    @Override // androidx.appcompat.widget.LinearLayoutCompat, android.view.ViewGroup
    public c generateLayoutParams(AttributeSet attributeSet) {
        return new c(getContext(), attributeSet);
    }

    @Override // androidx.appcompat.widget.LinearLayoutCompat, android.view.ViewGroup
    public c generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        if (layoutParams == null) {
            return generateDefaultLayoutParams();
        }
        c cVar = layoutParams instanceof c ? new c((c) layoutParams) : new c(layoutParams);
        if (cVar.f145b <= 0) {
            cVar.f145b = 16;
        }
        return cVar;
    }

    @Override // c.a.e.a.l.b
    public boolean a(p pVar) {
        return this.p.a(pVar, (v) null, 0);
    }

    @Override // c.a.e.a.w
    public void a(l lVar) {
        this.p = lVar;
    }

    public void a(v.a aVar, l.a aVar2) {
        this.u = aVar;
        this.v = aVar2;
    }

    public void a() {
        ActionMenuPresenter actionMenuPresenter = this.t;
        if (actionMenuPresenter != null) {
            actionMenuPresenter.b();
        }
    }
}
