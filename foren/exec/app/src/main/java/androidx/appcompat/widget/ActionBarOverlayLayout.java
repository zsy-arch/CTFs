package androidx.appcompat.widget;

import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewPropertyAnimator;
import android.view.Window;
import android.widget.OverScroller;
import c.a.a.I;
import c.a.e.a.l;
import c.a.e.a.v;
import c.a.f;
import c.a.f.C;
import c.a.f.C0036d;
import c.a.f.D;
import c.a.f.RunnableC0037e;
import c.a.f.RunnableC0038f;
import c.a.f.ra;
import c.a.f.xa;
import c.e.h.h;
import c.e.h.i;
import c.e.h.n;
import java.lang.reflect.Method;

/* loaded from: classes.dex */
public class ActionBarOverlayLayout extends ViewGroup implements C, h {

    /* renamed from: a */
    public static final int[] f70a = {c.a.a.actionBarSize, 16842841};
    public final Runnable A;
    public final i B;

    /* renamed from: b */
    public int f71b;

    /* renamed from: c */
    public int f72c;

    /* renamed from: d */
    public ContentFrameLayout f73d;

    /* renamed from: e */
    public ActionBarContainer f74e;
    public D f;
    public Drawable g;
    public boolean h;
    public boolean i;
    public boolean j;
    public boolean k;
    public boolean l;
    public int m;
    public int n;
    public final Rect o;
    public final Rect p;
    public final Rect q;
    public final Rect r;
    public final Rect s;
    public final Rect t;
    public final Rect u;
    public a v;
    public OverScroller w;
    public ViewPropertyAnimator x;
    public final AnimatorListenerAdapter y;
    public final Runnable z;

    /* loaded from: classes.dex */
    public interface a {
    }

    /* loaded from: classes.dex */
    public static class b extends ViewGroup.MarginLayoutParams {
        public b(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
        }

        public b(int i, int i2) {
            super(i, i2);
        }

        public b(ViewGroup.LayoutParams layoutParams) {
            super(layoutParams);
        }
    }

    public ActionBarOverlayLayout(Context context) {
        this(context, null);
    }

    public final void a(Context context) {
        TypedArray obtainStyledAttributes = getContext().getTheme().obtainStyledAttributes(f70a);
        boolean z = false;
        this.f71b = obtainStyledAttributes.getDimensionPixelSize(0, 0);
        this.g = obtainStyledAttributes.getDrawable(1);
        setWillNotDraw(this.g == null);
        obtainStyledAttributes.recycle();
        if (context.getApplicationInfo().targetSdkVersion < 19) {
            z = true;
        }
        this.h = z;
        this.w = new OverScroller(context);
    }

    @Override // c.a.f.C
    public void b() {
        j();
        ((ra) this.f).f635a.d();
    }

    @Override // c.a.f.C
    public void c() {
        j();
        ((ra) this.f).m = true;
    }

    @Override // android.view.ViewGroup
    public boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return layoutParams instanceof b;
    }

    @Override // c.a.f.C
    public boolean d() {
        j();
        return ((ra) this.f).f635a.b();
    }

    @Override // android.view.View
    public void draw(Canvas canvas) {
        int i;
        super.draw(canvas);
        if (this.g != null && !this.h) {
            if (this.f74e.getVisibility() == 0) {
                i = (int) (this.f74e.getTranslationY() + this.f74e.getBottom() + 0.5f);
            } else {
                i = 0;
            }
            this.g.setBounds(0, i, getWidth(), this.g.getIntrinsicHeight() + i);
            this.g.draw(canvas);
        }
    }

    @Override // c.a.f.C
    public boolean e() {
        j();
        return ((ra) this.f).f635a.l();
    }

    @Override // c.a.f.C
    public boolean f() {
        j();
        return ((ra) this.f).f635a.k();
    }

    @Override // android.view.View
    public boolean fitSystemWindows(Rect rect) {
        j();
        n.k(this);
        boolean a2 = a(this.f74e, rect, true, true, false, true);
        this.r.set(rect);
        Rect rect2 = this.r;
        Rect rect3 = this.o;
        Method method = xa.f662a;
        if (method != null) {
            try {
                method.invoke(this, rect2, rect3);
            } catch (Exception unused) {
            }
        }
        if (!this.s.equals(this.r)) {
            this.s.set(this.r);
            a2 = true;
        }
        if (!this.p.equals(this.o)) {
            this.p.set(this.o);
            a2 = true;
        }
        if (a2) {
            requestLayout();
        }
        return true;
    }

    @Override // c.a.f.C
    public boolean g() {
        j();
        return ((ra) this.f).f635a.o();
    }

    public int getActionBarHideOffset() {
        ActionBarContainer actionBarContainer = this.f74e;
        if (actionBarContainer != null) {
            return -((int) actionBarContainer.getTranslationY());
        }
        return 0;
    }

    @Override // android.view.ViewGroup
    public int getNestedScrollAxes() {
        return this.B.f860a;
    }

    public CharSequence getTitle() {
        j();
        return ((ra) this.f).f635a.getTitle();
    }

    public void h() {
        removeCallbacks(this.z);
        removeCallbacks(this.A);
        ViewPropertyAnimator viewPropertyAnimator = this.x;
        if (viewPropertyAnimator != null) {
            viewPropertyAnimator.cancel();
        }
    }

    public boolean i() {
        return this.i;
    }

    public void j() {
        D d2;
        if (this.f73d == null) {
            this.f73d = (ContentFrameLayout) findViewById(f.action_bar_activity_content);
            this.f74e = (ActionBarContainer) findViewById(f.action_bar_container);
            View findViewById = findViewById(f.action_bar);
            if (findViewById instanceof D) {
                d2 = (D) findViewById;
            } else if (findViewById instanceof Toolbar) {
                d2 = ((Toolbar) findViewById).getWrapper();
            } else {
                StringBuilder a2 = e.a.a.a.a.a("Can't make a decor toolbar out of ");
                a2.append(findViewById.getClass().getSimpleName());
                throw new IllegalStateException(a2.toString());
            }
            this.f = d2;
        }
    }

    @Override // android.view.View
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        a(getContext());
        n.r(this);
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        h();
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        int childCount = getChildCount();
        int paddingLeft = getPaddingLeft();
        getPaddingRight();
        int paddingTop = getPaddingTop();
        getPaddingBottom();
        for (int i5 = 0; i5 < childCount; i5++) {
            View childAt = getChildAt(i5);
            if (childAt.getVisibility() != 8) {
                b bVar = (b) childAt.getLayoutParams();
                int measuredWidth = childAt.getMeasuredWidth();
                int measuredHeight = childAt.getMeasuredHeight();
                int i6 = ((ViewGroup.MarginLayoutParams) bVar).leftMargin + paddingLeft;
                int i7 = ((ViewGroup.MarginLayoutParams) bVar).topMargin + paddingTop;
                childAt.layout(i6, i7, measuredWidth + i6, measuredHeight + i7);
            }
        }
    }

    @Override // android.view.View
    public void onMeasure(int i, int i2) {
        int i3;
        j();
        measureChildWithMargins(this.f74e, i, 0, i2, 0);
        b bVar = (b) this.f74e.getLayoutParams();
        int max = Math.max(0, this.f74e.getMeasuredWidth() + ((ViewGroup.MarginLayoutParams) bVar).leftMargin + ((ViewGroup.MarginLayoutParams) bVar).rightMargin);
        int max2 = Math.max(0, this.f74e.getMeasuredHeight() + ((ViewGroup.MarginLayoutParams) bVar).topMargin + ((ViewGroup.MarginLayoutParams) bVar).bottomMargin);
        int combineMeasuredStates = View.combineMeasuredStates(0, this.f74e.getMeasuredState());
        boolean z = (n.k(this) & 256) != 0;
        if (z) {
            i3 = this.f71b;
            if (this.j && this.f74e.getTabContainer() != null) {
                i3 += this.f71b;
            }
        } else {
            i3 = this.f74e.getVisibility() != 8 ? this.f74e.getMeasuredHeight() : 0;
        }
        this.q.set(this.o);
        this.t.set(this.r);
        if (this.i || z) {
            Rect rect = this.t;
            rect.top += i3;
            rect.bottom += 0;
        } else {
            Rect rect2 = this.q;
            rect2.top += i3;
            rect2.bottom += 0;
        }
        a(this.f73d, this.q, true, true, true, true);
        if (!this.u.equals(this.t)) {
            this.u.set(this.t);
            this.f73d.a(this.t);
        }
        measureChildWithMargins(this.f73d, i, 0, i2, 0);
        b bVar2 = (b) this.f73d.getLayoutParams();
        int max3 = Math.max(max, this.f73d.getMeasuredWidth() + ((ViewGroup.MarginLayoutParams) bVar2).leftMargin + ((ViewGroup.MarginLayoutParams) bVar2).rightMargin);
        int max4 = Math.max(max2, this.f73d.getMeasuredHeight() + ((ViewGroup.MarginLayoutParams) bVar2).topMargin + ((ViewGroup.MarginLayoutParams) bVar2).bottomMargin);
        int combineMeasuredStates2 = View.combineMeasuredStates(combineMeasuredStates, this.f73d.getMeasuredState());
        setMeasuredDimension(View.resolveSizeAndState(Math.max(getPaddingRight() + getPaddingLeft() + max3, getSuggestedMinimumWidth()), i, combineMeasuredStates2), View.resolveSizeAndState(Math.max(getPaddingBottom() + getPaddingTop() + max4, getSuggestedMinimumHeight()), i2, combineMeasuredStates2 << 16));
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public boolean onNestedFling(View view, float f, float f2, boolean z) {
        boolean z2 = false;
        if (!this.k || !z) {
            return false;
        }
        this.w.fling(0, 0, 0, (int) f2, 0, 0, Integer.MIN_VALUE, Integer.MAX_VALUE);
        if (this.w.getFinalY() > this.f74e.getHeight()) {
            z2 = true;
        }
        if (z2) {
            h();
            this.A.run();
        } else {
            h();
            this.z.run();
        }
        this.l = true;
        return true;
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public boolean onNestedPreFling(View view, float f, float f2) {
        return false;
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public void onNestedPreScroll(View view, int i, int i2, int[] iArr) {
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public void onNestedScroll(View view, int i, int i2, int i3, int i4) {
        this.m += i2;
        setActionBarHideOffset(this.m);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public void onNestedScrollAccepted(View view, View view2, int i) {
        I i2;
        c.a.e.h hVar;
        this.B.f860a = i;
        this.m = getActionBarHideOffset();
        h();
        a aVar = this.v;
        if (aVar != null && (hVar = (i2 = (I) aVar).x) != null) {
            hVar.a();
            i2.x = null;
        }
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public boolean onStartNestedScroll(View view, View view2, int i) {
        if ((i & 2) == 0 || this.f74e.getVisibility() != 0) {
            return false;
        }
        return this.k;
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public void onStopNestedScroll(View view) {
        if (this.k && !this.l) {
            if (this.m <= this.f74e.getHeight()) {
                h();
                postDelayed(this.z, 600L);
            } else {
                h();
                postDelayed(this.A, 600L);
            }
        }
        a aVar = this.v;
        if (aVar != null) {
            ((I) aVar).e();
        }
    }

    @Override // android.view.View
    public void onWindowSystemUiVisibilityChanged(int i) {
        int i2 = Build.VERSION.SDK_INT;
        super.onWindowSystemUiVisibilityChanged(i);
        j();
        int i3 = this.n ^ i;
        this.n = i;
        boolean z = (i & 4) == 0;
        boolean z2 = (i & 256) != 0;
        a aVar = this.v;
        if (aVar != null) {
            ((I) aVar).s = !z2;
            if (z || !z2) {
                I i4 = (I) this.v;
                if (i4.u) {
                    i4.u = false;
                    i4.f(true);
                }
            } else {
                I i5 = (I) aVar;
                if (!i5.u) {
                    i5.u = true;
                    i5.f(true);
                }
            }
        }
        if ((i3 & 256) != 0 && this.v != null) {
            n.r(this);
        }
    }

    @Override // android.view.View
    public void onWindowVisibilityChanged(int i) {
        super.onWindowVisibilityChanged(i);
        this.f72c = i;
        a aVar = this.v;
        if (aVar != null) {
            ((I) aVar).r = i;
        }
    }

    public void setActionBarHideOffset(int i) {
        h();
        this.f74e.setTranslationY(-Math.max(0, Math.min(i, this.f74e.getHeight())));
    }

    public void setActionBarVisibilityCallback(a aVar) {
        this.v = aVar;
        if (getWindowToken() != null) {
            ((I) this.v).r = this.f72c;
            int i = this.n;
            if (i != 0) {
                onWindowSystemUiVisibilityChanged(i);
                n.r(this);
            }
        }
    }

    public void setHasNonEmbeddedTabs(boolean z) {
        this.j = z;
    }

    public void setHideOnContentScrollEnabled(boolean z) {
        if (z != this.k) {
            this.k = z;
            if (!z) {
                h();
                setActionBarHideOffset(0);
            }
        }
    }

    public void setIcon(int i) {
        j();
        ra raVar = (ra) this.f;
        raVar.f639e = i != 0 ? c.a.b.a.a.c(raVar.a(), i) : null;
        raVar.f();
    }

    public void setLogo(int i) {
        j();
        ra raVar = (ra) this.f;
        raVar.a(i != 0 ? c.a.b.a.a.c(raVar.a(), i) : null);
    }

    public void setOverlayMode(boolean z) {
        this.i = z;
        this.h = z && getContext().getApplicationInfo().targetSdkVersion < 19;
    }

    public void setShowingForActionMode(boolean z) {
    }

    public void setUiOptions(int i) {
    }

    @Override // c.a.f.C
    public void setWindowCallback(Window.Callback callback) {
        j();
        ((ra) this.f).l = callback;
    }

    @Override // c.a.f.C
    public void setWindowTitle(CharSequence charSequence) {
        j();
        ra raVar = (ra) this.f;
        if (!raVar.h) {
            raVar.a(charSequence);
        }
    }

    @Override // android.view.ViewGroup
    public boolean shouldDelayChildPressedState() {
        return false;
    }

    public ActionBarOverlayLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f72c = 0;
        this.o = new Rect();
        this.p = new Rect();
        this.q = new Rect();
        this.r = new Rect();
        this.s = new Rect();
        this.t = new Rect();
        this.u = new Rect();
        this.y = new C0036d(this);
        this.z = new RunnableC0037e(this);
        this.A = new RunnableC0038f(this);
        a(context);
        this.B = new i(this);
    }

    @Override // android.view.ViewGroup
    public b generateDefaultLayoutParams() {
        return new b(-1, -1);
    }

    @Override // android.view.ViewGroup
    public b generateLayoutParams(AttributeSet attributeSet) {
        return new b(getContext(), attributeSet);
    }

    @Override // android.view.ViewGroup
    public ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return new b(layoutParams);
    }

    public void setIcon(Drawable drawable) {
        j();
        ra raVar = (ra) this.f;
        raVar.f639e = drawable;
        raVar.f();
    }

    public final boolean a(View view, Rect rect, boolean z, boolean z2, boolean z3, boolean z4) {
        boolean z5;
        int i;
        int i2;
        int i3;
        int i4;
        b bVar = (b) view.getLayoutParams();
        if (!z || ((ViewGroup.MarginLayoutParams) bVar).leftMargin == (i4 = rect.left)) {
            z5 = false;
        } else {
            ((ViewGroup.MarginLayoutParams) bVar).leftMargin = i4;
            z5 = true;
        }
        if (z2 && ((ViewGroup.MarginLayoutParams) bVar).topMargin != (i3 = rect.top)) {
            ((ViewGroup.MarginLayoutParams) bVar).topMargin = i3;
            z5 = true;
        }
        if (z4 && ((ViewGroup.MarginLayoutParams) bVar).rightMargin != (i2 = rect.right)) {
            ((ViewGroup.MarginLayoutParams) bVar).rightMargin = i2;
            z5 = true;
        }
        if (!z3 || ((ViewGroup.MarginLayoutParams) bVar).bottomMargin == (i = rect.bottom)) {
            return z5;
        }
        ((ViewGroup.MarginLayoutParams) bVar).bottomMargin = i;
        return true;
    }

    @Override // c.a.f.C
    public void a(int i) {
        j();
        if (i == 2) {
            ((ra) this.f).c();
        } else if (i == 5) {
            ((ra) this.f).b();
        } else if (i == 109) {
            setOverlayMode(true);
        }
    }

    @Override // c.a.f.C
    public boolean a() {
        j();
        return ((ra) this.f).f635a.m();
    }

    @Override // c.a.f.C
    public void a(Menu menu, v.a aVar) {
        j();
        ra raVar = (ra) this.f;
        if (raVar.n == null) {
            raVar.n = new ActionMenuPresenter(raVar.f635a.getContext());
            raVar.n.a(f.action_menu_presenter);
        }
        raVar.n.a(aVar);
        raVar.f635a.a((l) menu, raVar.n);
    }
}
