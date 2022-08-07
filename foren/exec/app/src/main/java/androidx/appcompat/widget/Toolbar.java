package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.widget.ActionMenuPresenter;
import androidx.appcompat.widget.ActionMenuView;
import androidx.customview.view.AbsSavedState;
import c.a.a.AbstractC0020a;
import c.a.e.a.C;
import c.a.e.a.l;
import c.a.e.a.p;
import c.a.e.a.v;
import c.a.e.f;
import c.a.f.D;
import c.a.f.N;
import c.a.f.ka;
import c.a.f.la;
import c.a.f.ma;
import c.a.f.na;
import c.a.f.oa;
import c.a.f.ra;
import c.a.j;
import c.e.h.n;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
public class Toolbar extends ViewGroup {
    public int A;
    public boolean B;
    public boolean C;
    public final ArrayList<View> D;
    public final ArrayList<View> E;
    public final int[] F;
    public final ActionMenuView.e G;
    public ra H;
    public ActionMenuPresenter I;
    public a J;
    public v.a K;
    public l.a L;
    public boolean M;
    public final Runnable N;

    /* renamed from: a */
    public ActionMenuView f172a;

    /* renamed from: b */
    public TextView f173b;

    /* renamed from: c */
    public TextView f174c;

    /* renamed from: d */
    public ImageButton f175d;

    /* renamed from: e */
    public ImageView f176e;
    public Drawable f;
    public CharSequence g;
    public ImageButton h;
    public View i;
    public Context j;
    public int k;
    public int l;
    public int m;
    public int n;
    public int o;
    public int p;
    public int q;
    public int r;
    public int s;
    public N t;
    public int u;
    public int v;
    public int w;
    public CharSequence x;
    public CharSequence y;
    public int z;

    /* loaded from: classes.dex */
    public interface c {
    }

    public Toolbar(Context context) {
        this(context, null, c.a.a.toolbarStyle);
    }

    private MenuInflater getMenuInflater() {
        return new f(getContext());
    }

    public void a(l lVar, ActionMenuPresenter actionMenuPresenter) {
        p pVar;
        if (!(lVar == null && this.f172a == null)) {
            h();
            l g = this.f172a.g();
            if (g != lVar) {
                if (g != null) {
                    g.a(this.I);
                    g.a(this.J);
                }
                if (this.J == null) {
                    this.J = new a();
                }
                boolean z = true;
                actionMenuPresenter.t = true;
                if (lVar != null) {
                    lVar.a(actionMenuPresenter, this.j);
                    lVar.a(this.J, this.j);
                } else {
                    Context context = this.j;
                    actionMenuPresenter.f416b = context;
                    LayoutInflater.from(actionMenuPresenter.f416b);
                    actionMenuPresenter.f417c = null;
                    Resources resources = context.getResources();
                    if (!actionMenuPresenter.m) {
                        int i = Build.VERSION.SDK_INT;
                        actionMenuPresenter.l = true;
                    }
                    int i2 = 2;
                    if (!actionMenuPresenter.s) {
                        actionMenuPresenter.n = context.getResources().getDisplayMetrics().widthPixels / 2;
                    }
                    if (!actionMenuPresenter.q) {
                        Configuration configuration = context.getResources().getConfiguration();
                        int i3 = configuration.screenWidthDp;
                        int i4 = configuration.screenHeightDp;
                        if (configuration.smallestScreenWidthDp > 600 || i3 > 600 || ((i3 > 960 && i4 > 720) || (i3 > 720 && i4 > 960))) {
                            i2 = 5;
                        } else if (i3 >= 500 || ((i3 > 640 && i4 > 480) || (i3 > 480 && i4 > 640))) {
                            i2 = 4;
                        } else if (i3 >= 360) {
                            i2 = 3;
                        }
                        actionMenuPresenter.p = i2;
                    }
                    int i5 = actionMenuPresenter.n;
                    if (actionMenuPresenter.l) {
                        if (actionMenuPresenter.i == null) {
                            actionMenuPresenter.i = new ActionMenuPresenter.d(actionMenuPresenter.f415a);
                            if (actionMenuPresenter.k) {
                                actionMenuPresenter.i.setImageDrawable(actionMenuPresenter.j);
                                actionMenuPresenter.j = null;
                                actionMenuPresenter.k = false;
                            }
                            int makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, 0);
                            actionMenuPresenter.i.measure(makeMeasureSpec, makeMeasureSpec);
                        }
                        i5 -= actionMenuPresenter.i.getMeasuredWidth();
                    } else {
                        actionMenuPresenter.i = null;
                    }
                    actionMenuPresenter.o = i5;
                    actionMenuPresenter.u = (int) (resources.getDisplayMetrics().density * 56.0f);
                    actionMenuPresenter.w = null;
                    a aVar = this.J;
                    Context context2 = this.j;
                    l lVar2 = aVar.f179a;
                    if (!(lVar2 == null || (pVar = aVar.f180b) == null)) {
                        lVar2.a(pVar);
                    }
                    aVar.f179a = null;
                    actionMenuPresenter.a(true);
                    a aVar2 = this.J;
                    if (aVar2.f180b != null) {
                        l lVar3 = aVar2.f179a;
                        if (lVar3 != null) {
                            int size = lVar3.size();
                            for (int i6 = 0; i6 < size; i6++) {
                                if (aVar2.f179a.getItem(i6) == aVar2.f180b) {
                                    break;
                                }
                            }
                        }
                        z = false;
                        if (!z) {
                            l lVar4 = aVar2.f179a;
                            p pVar2 = aVar2.f180b;
                            View view = Toolbar.this.i;
                            if (view instanceof c.a.e.b) {
                                ((c.a.e.b) view).onActionViewCollapsed();
                            }
                            Toolbar toolbar = Toolbar.this;
                            toolbar.removeView(toolbar.i);
                            Toolbar toolbar2 = Toolbar.this;
                            toolbar2.removeView(toolbar2.h);
                            Toolbar toolbar3 = Toolbar.this;
                            toolbar3.i = null;
                            toolbar3.a();
                            aVar2.f180b = null;
                            Toolbar.this.requestLayout();
                            pVar2.D = false;
                            pVar2.n.b(false);
                        }
                    }
                }
                this.f172a.setPopupTheme(this.k);
                this.f172a.setPresenter(actionMenuPresenter);
                this.I = actionMenuPresenter;
            }
        }
    }

    public boolean b() {
        ActionMenuView actionMenuView;
        return getVisibility() == 0 && (actionMenuView = this.f172a) != null && actionMenuView.f();
    }

    public void c() {
        a aVar = this.J;
        p pVar = aVar == null ? null : aVar.f180b;
        if (pVar != null && (pVar.z & 8) != 0 && pVar.A != null) {
            MenuItem.OnActionExpandListener onActionExpandListener = pVar.C;
            if (onActionExpandListener == null || onActionExpandListener.onMenuItemActionCollapse(pVar)) {
                pVar.n.a(pVar);
            }
        }
    }

    @Override // android.view.ViewGroup
    public boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return super.checkLayoutParams(layoutParams) && (layoutParams instanceof b);
    }

    public void d() {
        ActionMenuView actionMenuView = this.f172a;
        if (actionMenuView != null) {
            actionMenuView.a();
        }
    }

    public void e() {
        if (this.h == null) {
            this.h = new AppCompatImageButton(getContext(), null, c.a.a.toolbarNavigationButtonStyle);
            this.h.setImageDrawable(this.f);
            this.h.setContentDescription(this.g);
            b generateDefaultLayoutParams = generateDefaultLayoutParams();
            generateDefaultLayoutParams.f341a = 8388611 | (this.n & 112);
            generateDefaultLayoutParams.f182b = 2;
            this.h.setLayoutParams(generateDefaultLayoutParams);
            this.h.setOnClickListener(new na(this));
        }
    }

    public final void f() {
        if (this.t == null) {
            this.t = new N();
        }
    }

    public final void g() {
        h();
        if (this.f172a.g() == null) {
            l lVar = (l) this.f172a.getMenu();
            if (this.J == null) {
                this.J = new a();
            }
            this.f172a.setExpandedActionViewsExclusive(true);
            lVar.a(this.J, this.j);
        }
    }

    public int getContentInsetEnd() {
        N n = this.t;
        if (n != null) {
            return n.g ? n.f544a : n.f545b;
        }
        return 0;
    }

    public int getContentInsetEndWithActions() {
        int i = this.v;
        return i != Integer.MIN_VALUE ? i : getContentInsetEnd();
    }

    public int getContentInsetLeft() {
        N n = this.t;
        if (n != null) {
            return n.f544a;
        }
        return 0;
    }

    public int getContentInsetRight() {
        N n = this.t;
        if (n != null) {
            return n.f545b;
        }
        return 0;
    }

    public int getContentInsetStart() {
        N n = this.t;
        if (n != null) {
            return n.g ? n.f545b : n.f544a;
        }
        return 0;
    }

    public int getContentInsetStartWithNavigation() {
        int i = this.u;
        return i != Integer.MIN_VALUE ? i : getContentInsetStart();
    }

    public int getCurrentContentInsetEnd() {
        l g;
        ActionMenuView actionMenuView = this.f172a;
        if ((actionMenuView == null || (g = actionMenuView.g()) == null || !g.hasVisibleItems()) ? false : true) {
            return Math.max(getContentInsetEnd(), Math.max(this.v, 0));
        }
        return getContentInsetEnd();
    }

    public int getCurrentContentInsetLeft() {
        if (n.g(this) == 1) {
            return getCurrentContentInsetEnd();
        }
        return getCurrentContentInsetStart();
    }

    public int getCurrentContentInsetRight() {
        if (n.g(this) == 1) {
            return getCurrentContentInsetStart();
        }
        return getCurrentContentInsetEnd();
    }

    public int getCurrentContentInsetStart() {
        if (getNavigationIcon() != null) {
            return Math.max(getContentInsetStart(), Math.max(this.u, 0));
        }
        return getContentInsetStart();
    }

    public Drawable getLogo() {
        ImageView imageView = this.f176e;
        if (imageView != null) {
            return imageView.getDrawable();
        }
        return null;
    }

    public CharSequence getLogoDescription() {
        ImageView imageView = this.f176e;
        if (imageView != null) {
            return imageView.getContentDescription();
        }
        return null;
    }

    public Menu getMenu() {
        g();
        return this.f172a.getMenu();
    }

    public CharSequence getNavigationContentDescription() {
        ImageButton imageButton = this.f175d;
        if (imageButton != null) {
            return imageButton.getContentDescription();
        }
        return null;
    }

    public Drawable getNavigationIcon() {
        ImageButton imageButton = this.f175d;
        if (imageButton != null) {
            return imageButton.getDrawable();
        }
        return null;
    }

    public ActionMenuPresenter getOuterActionMenuPresenter() {
        return this.I;
    }

    public Drawable getOverflowIcon() {
        g();
        return this.f172a.getOverflowIcon();
    }

    public Context getPopupContext() {
        return this.j;
    }

    public int getPopupTheme() {
        return this.k;
    }

    public CharSequence getSubtitle() {
        return this.y;
    }

    public CharSequence getTitle() {
        return this.x;
    }

    public int getTitleMarginBottom() {
        return this.s;
    }

    public int getTitleMarginEnd() {
        return this.q;
    }

    public int getTitleMarginStart() {
        return this.p;
    }

    public int getTitleMarginTop() {
        return this.r;
    }

    public D getWrapper() {
        if (this.H == null) {
            this.H = new ra(this, true);
        }
        return this.H;
    }

    public final void h() {
        if (this.f172a == null) {
            this.f172a = new ActionMenuView(getContext(), null);
            this.f172a.setPopupTheme(this.k);
            this.f172a.setOnMenuItemClickListener(this.G);
            this.f172a.a(this.K, this.L);
            b generateDefaultLayoutParams = generateDefaultLayoutParams();
            generateDefaultLayoutParams.f341a = 8388613 | (this.n & 112);
            this.f172a.setLayoutParams(generateDefaultLayoutParams);
            a((View) this.f172a, false);
        }
    }

    public final void i() {
        if (this.f175d == null) {
            this.f175d = new AppCompatImageButton(getContext(), null, c.a.a.toolbarNavigationButtonStyle);
            b generateDefaultLayoutParams = generateDefaultLayoutParams();
            generateDefaultLayoutParams.f341a = 8388611 | (this.n & 112);
            this.f175d.setLayoutParams(generateDefaultLayoutParams);
        }
    }

    public boolean j() {
        a aVar = this.J;
        return (aVar == null || aVar.f180b == null) ? false : true;
    }

    public boolean k() {
        ActionMenuView actionMenuView = this.f172a;
        return actionMenuView != null && actionMenuView.c();
    }

    public boolean l() {
        ActionMenuView actionMenuView = this.f172a;
        return actionMenuView != null && actionMenuView.d();
    }

    public boolean m() {
        ActionMenuView actionMenuView = this.f172a;
        return actionMenuView != null && actionMenuView.e();
    }

    public void n() {
        for (int childCount = getChildCount() - 1; childCount >= 0; childCount--) {
            View childAt = getChildAt(childCount);
            if (!(((b) childAt.getLayoutParams()).f182b == 2 || childAt == this.f172a)) {
                removeViewAt(childCount);
                this.E.add(childAt);
            }
        }
    }

    public boolean o() {
        ActionMenuView actionMenuView = this.f172a;
        return actionMenuView != null && actionMenuView.h();
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        removeCallbacks(this.N);
    }

    @Override // android.view.View
    public boolean onHoverEvent(MotionEvent motionEvent) {
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 9) {
            this.C = false;
        }
        if (!this.C) {
            boolean onHoverEvent = super.onHoverEvent(motionEvent);
            if (actionMasked == 9 && !onHoverEvent) {
                this.C = true;
            }
        }
        if (actionMasked == 10 || actionMasked == 3) {
            this.C = false;
        }
        return true;
    }

    /* JADX WARN: Removed duplicated region for block: B:103:0x02a5 A[LOOP:0: B:102:0x02a3->B:103:0x02a5, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:106:0x02c7 A[LOOP:1: B:105:0x02c5->B:106:0x02c7, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:109:0x02ed A[LOOP:2: B:108:0x02eb->B:109:0x02ed, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:112:0x032f  */
    /* JADX WARN: Removed duplicated region for block: B:117:0x033e A[LOOP:3: B:116:0x033c->B:117:0x033e, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:19:0x005f  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x0076  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x00b3  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x00ca  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x00e7  */
    /* JADX WARN: Removed duplicated region for block: B:40:0x0100  */
    /* JADX WARN: Removed duplicated region for block: B:42:0x0105  */
    /* JADX WARN: Removed duplicated region for block: B:43:0x011d  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x012c  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x012f  */
    /* JADX WARN: Removed duplicated region for block: B:51:0x0133  */
    /* JADX WARN: Removed duplicated region for block: B:52:0x0136  */
    /* JADX WARN: Removed duplicated region for block: B:64:0x0169  */
    /* JADX WARN: Removed duplicated region for block: B:74:0x01a7  */
    /* JADX WARN: Removed duplicated region for block: B:76:0x01b8  */
    /* JADX WARN: Removed duplicated region for block: B:89:0x022a  */
    @Override // android.view.ViewGroup, android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void onLayout(boolean r20, int r21, int r22, int r23, int r24) {
        /*
            Method dump skipped, instructions count: 851
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.widget.Toolbar.onLayout(boolean, int, int, int, int):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:52:0x0297  */
    @Override // android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void onMeasure(int r18, int r19) {
        /*
            Method dump skipped, instructions count: 668
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.widget.Toolbar.onMeasure(int, int):void");
    }

    @Override // android.view.View
    public void onRestoreInstanceState(Parcelable parcelable) {
        MenuItem findItem;
        if (!(parcelable instanceof SavedState)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        SavedState savedState = (SavedState) parcelable;
        super.onRestoreInstanceState(savedState.a());
        ActionMenuView actionMenuView = this.f172a;
        l g = actionMenuView != null ? actionMenuView.g() : null;
        int i = savedState.f177a;
        if (!(i == 0 || this.J == null || g == null || (findItem = g.findItem(i)) == null)) {
            findItem.expandActionView();
        }
        if (savedState.f178b) {
            removeCallbacks(this.N);
            post(this.N);
        }
    }

    @Override // android.view.View
    public void onRtlPropertiesChanged(int i) {
        int i2 = Build.VERSION.SDK_INT;
        super.onRtlPropertiesChanged(i);
        f();
        N n = this.t;
        boolean z = true;
        if (i != 1) {
            z = false;
        }
        if (z != n.g) {
            n.g = z;
            if (!n.h) {
                n.f544a = n.f548e;
                n.f545b = n.f;
            } else if (z) {
                int i3 = n.f547d;
                if (i3 == Integer.MIN_VALUE) {
                    i3 = n.f548e;
                }
                n.f544a = i3;
                int i4 = n.f546c;
                if (i4 == Integer.MIN_VALUE) {
                    i4 = n.f;
                }
                n.f545b = i4;
            } else {
                int i5 = n.f546c;
                if (i5 == Integer.MIN_VALUE) {
                    i5 = n.f548e;
                }
                n.f544a = i5;
                int i6 = n.f547d;
                if (i6 == Integer.MIN_VALUE) {
                    i6 = n.f;
                }
                n.f545b = i6;
            }
        }
    }

    @Override // android.view.View
    public Parcelable onSaveInstanceState() {
        p pVar;
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        a aVar = this.J;
        if (!(aVar == null || (pVar = aVar.f180b) == null)) {
            savedState.f177a = pVar.f460a;
        }
        savedState.f178b = m();
        return savedState;
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 0) {
            this.B = false;
        }
        if (!this.B) {
            boolean onTouchEvent = super.onTouchEvent(motionEvent);
            if (actionMasked == 0 && !onTouchEvent) {
                this.B = true;
            }
        }
        if (actionMasked == 1 || actionMasked == 3) {
            this.B = false;
        }
        return true;
    }

    public void setCollapsible(boolean z) {
        this.M = z;
        requestLayout();
    }

    public void setContentInsetEndWithActions(int i) {
        if (i < 0) {
            i = Integer.MIN_VALUE;
        }
        if (i != this.v) {
            this.v = i;
            if (getNavigationIcon() != null) {
                requestLayout();
            }
        }
    }

    public void setContentInsetStartWithNavigation(int i) {
        if (i < 0) {
            i = Integer.MIN_VALUE;
        }
        if (i != this.u) {
            this.u = i;
            if (getNavigationIcon() != null) {
                requestLayout();
            }
        }
    }

    public void setLogo(int i) {
        setLogo(c.a.b.a.a.c(getContext(), i));
    }

    public void setLogoDescription(int i) {
        setLogoDescription(getContext().getText(i));
    }

    public void setNavigationContentDescription(int i) {
        setNavigationContentDescription(i != 0 ? getContext().getText(i) : null);
    }

    public void setNavigationIcon(int i) {
        setNavigationIcon(c.a.b.a.a.c(getContext(), i));
    }

    public void setNavigationOnClickListener(View.OnClickListener onClickListener) {
        i();
        this.f175d.setOnClickListener(onClickListener);
    }

    public void setOnMenuItemClickListener(c cVar) {
    }

    public void setOverflowIcon(Drawable drawable) {
        g();
        this.f172a.setOverflowIcon(drawable);
    }

    public void setPopupTheme(int i) {
        if (this.k != i) {
            this.k = i;
            if (i == 0) {
                this.j = getContext();
            } else {
                this.j = new ContextThemeWrapper(getContext(), i);
            }
        }
    }

    public void setSubtitle(int i) {
        setSubtitle(getContext().getText(i));
    }

    public void setSubtitleTextColor(int i) {
        this.A = i;
        TextView textView = this.f174c;
        if (textView != null) {
            textView.setTextColor(i);
        }
    }

    public void setTitle(int i) {
        setTitle(getContext().getText(i));
    }

    public void setTitleMarginBottom(int i) {
        this.s = i;
        requestLayout();
    }

    public void setTitleMarginEnd(int i) {
        this.q = i;
        requestLayout();
    }

    public void setTitleMarginStart(int i) {
        this.p = i;
        requestLayout();
    }

    public void setTitleMarginTop(int i) {
        this.r = i;
        requestLayout();
    }

    public void setTitleTextColor(int i) {
        this.z = i;
        TextView textView = this.f173b;
        if (textView != null) {
            textView.setTextColor(i);
        }
    }

    /* loaded from: classes.dex */
    public static class b extends AbstractC0020a.C0002a {

        /* renamed from: b */
        public int f182b;

        public b(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            this.f182b = 0;
        }

        public b(int i, int i2) {
            super(i, i2);
            this.f182b = 0;
            this.f341a = 8388627;
        }

        public b(b bVar) {
            super((AbstractC0020a.C0002a) bVar);
            this.f182b = 0;
            this.f182b = bVar.f182b;
        }

        public b(AbstractC0020a.C0002a aVar) {
            super(aVar);
            this.f182b = 0;
        }

        public b(ViewGroup.MarginLayoutParams marginLayoutParams) {
            super(marginLayoutParams);
            this.f182b = 0;
            ((ViewGroup.MarginLayoutParams) this).leftMargin = marginLayoutParams.leftMargin;
            ((ViewGroup.MarginLayoutParams) this).topMargin = marginLayoutParams.topMargin;
            ((ViewGroup.MarginLayoutParams) this).rightMargin = marginLayoutParams.rightMargin;
            ((ViewGroup.MarginLayoutParams) this).bottomMargin = marginLayoutParams.bottomMargin;
        }

        public b(ViewGroup.LayoutParams layoutParams) {
            super(layoutParams);
            this.f182b = 0;
        }
    }

    public Toolbar(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, c.a.a.toolbarStyle);
    }

    public void b(Context context, int i) {
        this.l = i;
        TextView textView = this.f173b;
        if (textView != null) {
            textView.setTextAppearance(context, i);
        }
    }

    @Override // android.view.ViewGroup
    public b generateDefaultLayoutParams() {
        return new b(-2, -2);
    }

    public void setLogo(Drawable drawable) {
        if (drawable != null) {
            if (this.f176e == null) {
                this.f176e = new AppCompatImageView(getContext(), null, 0);
            }
            if (!c(this.f176e)) {
                a((View) this.f176e, true);
            }
        } else {
            ImageView imageView = this.f176e;
            if (imageView != null && c(imageView)) {
                removeView(this.f176e);
                this.E.remove(this.f176e);
            }
        }
        ImageView imageView2 = this.f176e;
        if (imageView2 != null) {
            imageView2.setImageDrawable(drawable);
        }
    }

    public void setLogoDescription(CharSequence charSequence) {
        if (!TextUtils.isEmpty(charSequence) && this.f176e == null) {
            this.f176e = new AppCompatImageView(getContext(), null, 0);
        }
        ImageView imageView = this.f176e;
        if (imageView != null) {
            imageView.setContentDescription(charSequence);
        }
    }

    public void setNavigationContentDescription(CharSequence charSequence) {
        if (!TextUtils.isEmpty(charSequence)) {
            i();
        }
        ImageButton imageButton = this.f175d;
        if (imageButton != null) {
            imageButton.setContentDescription(charSequence);
        }
    }

    public void setNavigationIcon(Drawable drawable) {
        if (drawable != null) {
            i();
            if (!c(this.f175d)) {
                a((View) this.f175d, true);
            }
        } else {
            ImageButton imageButton = this.f175d;
            if (imageButton != null && c(imageButton)) {
                removeView(this.f175d);
                this.E.remove(this.f175d);
            }
        }
        ImageButton imageButton2 = this.f175d;
        if (imageButton2 != null) {
            imageButton2.setImageDrawable(drawable);
        }
    }

    public void setSubtitle(CharSequence charSequence) {
        if (!TextUtils.isEmpty(charSequence)) {
            if (this.f174c == null) {
                Context context = getContext();
                this.f174c = new AppCompatTextView(context);
                this.f174c.setSingleLine();
                this.f174c.setEllipsize(TextUtils.TruncateAt.END);
                int i = this.m;
                if (i != 0) {
                    this.f174c.setTextAppearance(context, i);
                }
                int i2 = this.A;
                if (i2 != 0) {
                    this.f174c.setTextColor(i2);
                }
            }
            if (!c(this.f174c)) {
                a((View) this.f174c, true);
            }
        } else {
            TextView textView = this.f174c;
            if (textView != null && c(textView)) {
                removeView(this.f174c);
                this.E.remove(this.f174c);
            }
        }
        TextView textView2 = this.f174c;
        if (textView2 != null) {
            textView2.setText(charSequence);
        }
        this.y = charSequence;
    }

    public void setTitle(CharSequence charSequence) {
        if (!TextUtils.isEmpty(charSequence)) {
            if (this.f173b == null) {
                Context context = getContext();
                this.f173b = new AppCompatTextView(context);
                this.f173b.setSingleLine();
                this.f173b.setEllipsize(TextUtils.TruncateAt.END);
                int i = this.l;
                if (i != 0) {
                    this.f173b.setTextAppearance(context, i);
                }
                int i2 = this.z;
                if (i2 != 0) {
                    this.f173b.setTextColor(i2);
                }
            }
            if (!c(this.f173b)) {
                a((View) this.f173b, true);
            }
        } else {
            TextView textView = this.f173b;
            if (textView != null && c(textView)) {
                removeView(this.f173b);
                this.E.remove(this.f173b);
            }
        }
        TextView textView2 = this.f173b;
        if (textView2 != null) {
            textView2.setText(charSequence);
        }
        this.x = charSequence;
    }

    /* loaded from: classes.dex */
    public static class SavedState extends AbsSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new oa();

        /* renamed from: a */
        public int f177a;

        /* renamed from: b */
        public boolean f178b;

        public SavedState(Parcel parcel, ClassLoader classLoader) {
            super(parcel, classLoader);
            this.f177a = parcel.readInt();
            this.f178b = parcel.readInt() != 0;
        }

        @Override // androidx.customview.view.AbsSavedState, android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeParcelable(super.f218b, i);
            parcel.writeInt(this.f177a);
            parcel.writeInt(this.f178b ? 1 : 0);
        }

        public SavedState(Parcelable parcelable) {
            super(parcelable);
        }
    }

    /* loaded from: classes.dex */
    public class a implements v {

        /* renamed from: a */
        public l f179a;

        /* renamed from: b */
        public p f180b;

        public a() {
            Toolbar.this = r1;
        }

        @Override // c.a.e.a.v
        public void a(Context context, l lVar) {
            p pVar;
            l lVar2 = this.f179a;
            if (!(lVar2 == null || (pVar = this.f180b) == null)) {
                lVar2.a(pVar);
            }
            this.f179a = lVar;
        }

        @Override // c.a.e.a.v
        public void a(l lVar, boolean z) {
        }

        @Override // c.a.e.a.v
        public boolean a() {
            return false;
        }

        @Override // c.a.e.a.v
        public boolean a(C c2) {
            return false;
        }

        @Override // c.a.e.a.v
        public boolean b(l lVar, p pVar) {
            Toolbar.this.e();
            ViewParent parent = Toolbar.this.h.getParent();
            Toolbar toolbar = Toolbar.this;
            if (parent != toolbar) {
                if (parent instanceof ViewGroup) {
                    ((ViewGroup) parent).removeView(toolbar.h);
                }
                Toolbar toolbar2 = Toolbar.this;
                toolbar2.addView(toolbar2.h);
            }
            Toolbar.this.i = pVar.getActionView();
            this.f180b = pVar;
            ViewParent parent2 = Toolbar.this.i.getParent();
            Toolbar toolbar3 = Toolbar.this;
            if (parent2 != toolbar3) {
                if (parent2 instanceof ViewGroup) {
                    ((ViewGroup) parent2).removeView(toolbar3.i);
                }
                b generateDefaultLayoutParams = Toolbar.this.generateDefaultLayoutParams();
                Toolbar toolbar4 = Toolbar.this;
                generateDefaultLayoutParams.f341a = 8388611 | (toolbar4.n & 112);
                generateDefaultLayoutParams.f182b = 2;
                toolbar4.i.setLayoutParams(generateDefaultLayoutParams);
                Toolbar toolbar5 = Toolbar.this;
                toolbar5.addView(toolbar5.i);
            }
            Toolbar.this.n();
            Toolbar.this.requestLayout();
            pVar.D = true;
            pVar.n.b(false);
            View view = Toolbar.this.i;
            if (view instanceof c.a.e.b) {
                ((c.a.e.b) view).onActionViewExpanded();
            }
            return true;
        }

        @Override // c.a.e.a.v
        public void a(boolean z) {
            boolean z2;
            if (this.f180b != null) {
                l lVar = this.f179a;
                if (lVar != null) {
                    int size = lVar.size();
                    for (int i = 0; i < size; i++) {
                        if (this.f179a.getItem(i) == this.f180b) {
                            z2 = true;
                            break;
                        }
                    }
                }
                z2 = false;
                if (!z2) {
                    l lVar2 = this.f179a;
                    p pVar = this.f180b;
                    View view = Toolbar.this.i;
                    if (view instanceof c.a.e.b) {
                        ((c.a.e.b) view).onActionViewCollapsed();
                    }
                    Toolbar toolbar = Toolbar.this;
                    toolbar.removeView(toolbar.i);
                    Toolbar toolbar2 = Toolbar.this;
                    toolbar2.removeView(toolbar2.h);
                    Toolbar toolbar3 = Toolbar.this;
                    toolbar3.i = null;
                    toolbar3.a();
                    this.f180b = null;
                    Toolbar.this.requestLayout();
                    pVar.a(false);
                }
            }
        }

        @Override // c.a.e.a.v
        public boolean a(l lVar, p pVar) {
            View view = Toolbar.this.i;
            if (view instanceof c.a.e.b) {
                ((c.a.e.b) view).onActionViewCollapsed();
            }
            Toolbar toolbar = Toolbar.this;
            toolbar.removeView(toolbar.i);
            Toolbar toolbar2 = Toolbar.this;
            toolbar2.removeView(toolbar2.h);
            Toolbar toolbar3 = Toolbar.this;
            toolbar3.i = null;
            toolbar3.a();
            this.f180b = null;
            Toolbar.this.requestLayout();
            pVar.D = false;
            pVar.n.b(false);
            return true;
        }
    }

    public Toolbar(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.w = 8388627;
        this.D = new ArrayList<>();
        this.E = new ArrayList<>();
        this.F = new int[2];
        this.G = new la(this);
        this.N = new ma(this);
        ka a2 = ka.a(getContext(), attributeSet, j.Toolbar, i, 0);
        this.l = a2.f(j.Toolbar_titleTextAppearance, 0);
        this.m = a2.f(j.Toolbar_subtitleTextAppearance, 0);
        this.w = a2.f605b.getInteger(j.Toolbar_android_gravity, this.w);
        this.n = a2.f605b.getInteger(j.Toolbar_buttonGravity, 48);
        int b2 = a2.b(j.Toolbar_titleMargin, 0);
        b2 = a2.f(j.Toolbar_titleMargins) ? a2.b(j.Toolbar_titleMargins, b2) : b2;
        this.s = b2;
        this.r = b2;
        this.q = b2;
        this.p = b2;
        int b3 = a2.b(j.Toolbar_titleMarginStart, -1);
        if (b3 >= 0) {
            this.p = b3;
        }
        int b4 = a2.b(j.Toolbar_titleMarginEnd, -1);
        if (b4 >= 0) {
            this.q = b4;
        }
        int b5 = a2.b(j.Toolbar_titleMarginTop, -1);
        if (b5 >= 0) {
            this.r = b5;
        }
        int b6 = a2.b(j.Toolbar_titleMarginBottom, -1);
        if (b6 >= 0) {
            this.s = b6;
        }
        this.o = a2.c(j.Toolbar_maxButtonHeight, -1);
        int b7 = a2.b(j.Toolbar_contentInsetStart, Integer.MIN_VALUE);
        int b8 = a2.b(j.Toolbar_contentInsetEnd, Integer.MIN_VALUE);
        int c2 = a2.c(j.Toolbar_contentInsetLeft, 0);
        int c3 = a2.c(j.Toolbar_contentInsetRight, 0);
        f();
        N n = this.t;
        n.h = false;
        if (c2 != Integer.MIN_VALUE) {
            n.f548e = c2;
            n.f544a = c2;
        }
        if (c3 != Integer.MIN_VALUE) {
            n.f = c3;
            n.f545b = c3;
        }
        if (!(b7 == Integer.MIN_VALUE && b8 == Integer.MIN_VALUE)) {
            this.t.a(b7, b8);
        }
        this.u = a2.b(j.Toolbar_contentInsetStartWithNavigation, Integer.MIN_VALUE);
        this.v = a2.b(j.Toolbar_contentInsetEndWithActions, Integer.MIN_VALUE);
        this.f = a2.b(j.Toolbar_collapseIcon);
        this.g = a2.e(j.Toolbar_collapseContentDescription);
        CharSequence e2 = a2.e(j.Toolbar_title);
        if (!TextUtils.isEmpty(e2)) {
            setTitle(e2);
        }
        CharSequence e3 = a2.e(j.Toolbar_subtitle);
        if (!TextUtils.isEmpty(e3)) {
            setSubtitle(e3);
        }
        this.j = getContext();
        setPopupTheme(a2.f(j.Toolbar_popupTheme, 0));
        Drawable b9 = a2.b(j.Toolbar_navigationIcon);
        if (b9 != null) {
            setNavigationIcon(b9);
        }
        CharSequence e4 = a2.e(j.Toolbar_navigationContentDescription);
        if (!TextUtils.isEmpty(e4)) {
            setNavigationContentDescription(e4);
        }
        Drawable b10 = a2.b(j.Toolbar_logo);
        if (b10 != null) {
            setLogo(b10);
        }
        CharSequence e5 = a2.e(j.Toolbar_logoDescription);
        if (!TextUtils.isEmpty(e5)) {
            setLogoDescription(e5);
        }
        if (a2.f(j.Toolbar_titleTextColor)) {
            setTitleTextColor(a2.a(j.Toolbar_titleTextColor, -1));
        }
        if (a2.f(j.Toolbar_subtitleTextColor)) {
            setSubtitleTextColor(a2.a(j.Toolbar_subtitleTextColor, -1));
        }
        a2.f605b.recycle();
    }

    public final boolean d(View view) {
        return (view == null || view.getParent() != this || view.getVisibility() == 8) ? false : true;
    }

    @Override // android.view.ViewGroup
    public b generateLayoutParams(AttributeSet attributeSet) {
        return new b(getContext(), attributeSet);
    }

    @Override // android.view.ViewGroup
    public b generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        if (layoutParams instanceof b) {
            return new b((b) layoutParams);
        }
        if (layoutParams instanceof AbstractC0020a.C0002a) {
            return new b((AbstractC0020a.C0002a) layoutParams);
        }
        if (layoutParams instanceof ViewGroup.MarginLayoutParams) {
            return new b((ViewGroup.MarginLayoutParams) layoutParams);
        }
        return new b(layoutParams);
    }

    public final int b(View view, int i, int[] iArr, int i2) {
        b bVar = (b) view.getLayoutParams();
        int i3 = ((ViewGroup.MarginLayoutParams) bVar).rightMargin - iArr[1];
        int max = i - Math.max(0, i3);
        iArr[1] = Math.max(0, -i3);
        int a2 = a(view, i2);
        int measuredWidth = view.getMeasuredWidth();
        view.layout(max - measuredWidth, a2, max, view.getMeasuredHeight() + a2);
        return max - (measuredWidth + ((ViewGroup.MarginLayoutParams) bVar).leftMargin);
    }

    public final boolean c(View view) {
        return view.getParent() == this || this.E.contains(view);
    }

    public final int b(View view) {
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        return marginLayoutParams.topMargin + marginLayoutParams.bottomMargin;
    }

    public void a(Context context, int i) {
        this.m = i;
        TextView textView = this.f174c;
        if (textView != null) {
            textView.setTextAppearance(context, i);
        }
    }

    public void a(int i, int i2) {
        f();
        this.t.a(i, i2);
    }

    public final void a(View view, boolean z) {
        b bVar;
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (layoutParams == null) {
            bVar = generateDefaultLayoutParams();
        } else if (!checkLayoutParams(layoutParams)) {
            bVar = generateLayoutParams(layoutParams);
        } else {
            bVar = (b) layoutParams;
        }
        bVar.f182b = 1;
        if (!z || this.i == null) {
            addView(view, bVar);
            return;
        }
        view.setLayoutParams(bVar);
        this.E.add(view);
    }

    public final void a(View view, int i, int i2, int i3, int i4, int i5) {
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        int childMeasureSpec = ViewGroup.getChildMeasureSpec(i, getPaddingRight() + getPaddingLeft() + marginLayoutParams.leftMargin + marginLayoutParams.rightMargin + i2, marginLayoutParams.width);
        int childMeasureSpec2 = ViewGroup.getChildMeasureSpec(i3, getPaddingBottom() + getPaddingTop() + marginLayoutParams.topMargin + marginLayoutParams.bottomMargin + i4, marginLayoutParams.height);
        int mode = View.MeasureSpec.getMode(childMeasureSpec2);
        if (mode != 1073741824 && i5 >= 0) {
            if (mode != 0) {
                i5 = Math.min(View.MeasureSpec.getSize(childMeasureSpec2), i5);
            }
            childMeasureSpec2 = View.MeasureSpec.makeMeasureSpec(i5, 1073741824);
        }
        view.measure(childMeasureSpec, childMeasureSpec2);
    }

    public final int a(View view, int i, int i2, int i3, int i4, int[] iArr) {
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        int i5 = marginLayoutParams.leftMargin - iArr[0];
        int i6 = marginLayoutParams.rightMargin - iArr[1];
        int max = Math.max(0, i6) + Math.max(0, i5);
        iArr[0] = Math.max(0, -i5);
        iArr[1] = Math.max(0, -i6);
        view.measure(ViewGroup.getChildMeasureSpec(i, getPaddingRight() + getPaddingLeft() + max + i2, marginLayoutParams.width), ViewGroup.getChildMeasureSpec(i3, getPaddingBottom() + getPaddingTop() + marginLayoutParams.topMargin + marginLayoutParams.bottomMargin + i4, marginLayoutParams.height));
        return view.getMeasuredWidth() + max;
    }

    public final int a(View view, int i, int[] iArr, int i2) {
        b bVar = (b) view.getLayoutParams();
        int i3 = ((ViewGroup.MarginLayoutParams) bVar).leftMargin - iArr[0];
        int max = Math.max(0, i3) + i;
        iArr[0] = Math.max(0, -i3);
        int a2 = a(view, i2);
        int measuredWidth = view.getMeasuredWidth();
        view.layout(max, a2, max + measuredWidth, view.getMeasuredHeight() + a2);
        return measuredWidth + ((ViewGroup.MarginLayoutParams) bVar).rightMargin + max;
    }

    public final int a(View view, int i) {
        b bVar = (b) view.getLayoutParams();
        int measuredHeight = view.getMeasuredHeight();
        int i2 = i > 0 ? (measuredHeight - i) / 2 : 0;
        int i3 = bVar.f341a & 112;
        if (!(i3 == 16 || i3 == 48 || i3 == 80)) {
            i3 = this.w & 112;
        }
        if (i3 == 48) {
            return getPaddingTop() - i2;
        }
        if (i3 == 80) {
            return (((getHeight() - getPaddingBottom()) - measuredHeight) - ((ViewGroup.MarginLayoutParams) bVar).bottomMargin) - i2;
        }
        int paddingTop = getPaddingTop();
        int paddingBottom = getPaddingBottom();
        int height = getHeight();
        int i4 = (((height - paddingTop) - paddingBottom) - measuredHeight) / 2;
        int i5 = ((ViewGroup.MarginLayoutParams) bVar).topMargin;
        if (i4 < i5) {
            i4 = i5;
        } else {
            int i6 = (((height - paddingBottom) - measuredHeight) - i4) - paddingTop;
            int i7 = ((ViewGroup.MarginLayoutParams) bVar).bottomMargin;
            if (i6 < i7) {
                i4 = Math.max(0, i4 - (i7 - i6));
            }
        }
        return paddingTop + i4;
    }

    public final void a(List<View> list, int i) {
        boolean z = n.g(this) == 1;
        int childCount = getChildCount();
        int a2 = c.a.a.C.a(i, n.g(this));
        list.clear();
        if (z) {
            for (int i2 = childCount - 1; i2 >= 0; i2--) {
                View childAt = getChildAt(i2);
                b bVar = (b) childAt.getLayoutParams();
                if (bVar.f182b == 0 && d(childAt) && a(bVar.f341a) == a2) {
                    list.add(childAt);
                }
            }
            return;
        }
        for (int i3 = 0; i3 < childCount; i3++) {
            View childAt2 = getChildAt(i3);
            b bVar2 = (b) childAt2.getLayoutParams();
            if (bVar2.f182b == 0 && d(childAt2) && a(bVar2.f341a) == a2) {
                list.add(childAt2);
            }
        }
    }

    public final int a(int i) {
        int g = n.g(this);
        int a2 = c.a.a.C.a(i, g) & 7;
        return (a2 == 1 || a2 == 3 || a2 == 5) ? a2 : g == 1 ? 5 : 3;
    }

    public final int a(View view) {
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        int i = Build.VERSION.SDK_INT;
        int marginStart = marginLayoutParams.getMarginStart();
        int i2 = Build.VERSION.SDK_INT;
        return marginStart + marginLayoutParams.getMarginEnd();
    }

    public void a() {
        for (int size = this.E.size() - 1; size >= 0; size--) {
            addView(this.E.get(size));
        }
        this.E.clear();
    }
}
