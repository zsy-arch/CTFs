package c.a.a;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.util.TypedValue;
import android.view.ContextThemeWrapper;
import android.view.KeyCharacterMap;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import androidx.appcompat.widget.ActionBarContainer;
import androidx.appcompat.widget.ActionBarContextView;
import androidx.appcompat.widget.ActionBarOverlayLayout;
import androidx.appcompat.widget.Toolbar;
import c.a.a.AbstractC0020a;
import c.a.b;
import c.a.e.a;
import c.a.e.a.l;
import c.a.e.f;
import c.a.e.h;
import c.a.f.D;
import c.a.f.P;
import c.a.f.ra;
import c.a.j;
import c.e.h.n;
import c.e.h.r;
import c.e.h.s;
import c.e.h.u;
import java.lang.ref.WeakReference;
import java.util.ArrayList;

/* loaded from: classes.dex */
public class I extends AbstractC0020a implements ActionBarOverlayLayout.a {

    /* renamed from: a */
    public static final Interpolator f333a = new AccelerateInterpolator();

    /* renamed from: b */
    public static final Interpolator f334b = new DecelerateInterpolator();

    /* renamed from: c */
    public Context f335c;

    /* renamed from: d */
    public Context f336d;

    /* renamed from: e */
    public ActionBarOverlayLayout f337e;
    public ActionBarContainer f;
    public D g;
    public ActionBarContextView h;
    public View i;
    public P j;
    public boolean k;
    public a l;
    public c.a.e.a m;
    public a.AbstractC0005a n;
    public boolean o;
    public boolean q;
    public boolean t;
    public boolean u;
    public boolean v;
    public h x;
    public boolean y;
    public boolean z;
    public ArrayList<AbstractC0020a.b> p = new ArrayList<>();
    public int r = 0;
    public boolean s = true;
    public boolean w = true;
    public final s A = new F(this);
    public final s B = new G(this);
    public final u C = new H(this);

    /* loaded from: classes.dex */
    public class a extends c.a.e.a implements l.a {

        /* renamed from: c */
        public final Context f338c;

        /* renamed from: d */
        public final l f339d;

        /* renamed from: e */
        public a.AbstractC0005a f340e;
        public WeakReference<View> f;

        public a(Context context, a.AbstractC0005a aVar) {
            I.this = r1;
            this.f338c = context;
            this.f340e = aVar;
            l lVar = new l(context);
            lVar.m = 1;
            this.f339d = lVar;
            this.f339d.a(this);
        }

        @Override // c.a.e.a
        public void a(boolean z) {
            this.f404b = z;
            I.this.h.setTitleOptional(z);
        }

        @Override // c.a.e.a
        public void b(CharSequence charSequence) {
            I.this.h.setTitle(charSequence);
        }

        @Override // c.a.e.a
        public Menu c() {
            return this.f339d;
        }

        @Override // c.a.e.a
        public MenuInflater d() {
            return new f(this.f338c);
        }

        @Override // c.a.e.a
        public CharSequence e() {
            return I.this.h.getSubtitle();
        }

        @Override // c.a.e.a
        public CharSequence f() {
            return I.this.h.getTitle();
        }

        @Override // c.a.e.a
        public void g() {
            if (I.this.l == this) {
                this.f339d.i();
                try {
                    this.f340e.b(this, this.f339d);
                } finally {
                    this.f339d.h();
                }
            }
        }

        @Override // c.a.e.a
        public boolean h() {
            return I.this.h.c();
        }

        @Override // c.a.e.a
        public void b(int i) {
            I.this.h.setTitle(I.this.f335c.getResources().getString(i));
        }

        @Override // c.a.e.a
        public void a() {
            I i = I.this;
            if (i.l == this) {
                if (!I.a(i.t, i.u, false)) {
                    I i2 = I.this;
                    i2.m = this;
                    i2.n = this.f340e;
                } else {
                    this.f340e.a(this);
                }
                this.f340e = null;
                I.this.d(false);
                I.this.h.a();
                ((ra) I.this.g).f635a.sendAccessibilityEvent(32);
                I i3 = I.this;
                i3.f337e.setHideOnContentScrollEnabled(i3.z);
                I.this.l = null;
            }
        }

        @Override // c.a.e.a
        public View b() {
            WeakReference<View> weakReference = this.f;
            if (weakReference != null) {
                return weakReference.get();
            }
            return null;
        }

        @Override // c.a.e.a
        public void a(View view) {
            I.this.h.setCustomView(view);
            this.f = new WeakReference<>(view);
        }

        @Override // c.a.e.a
        public void a(CharSequence charSequence) {
            I.this.h.setSubtitle(charSequence);
        }

        @Override // c.a.e.a
        public void a(int i) {
            I.this.h.setSubtitle(I.this.f335c.getResources().getString(i));
        }

        @Override // c.a.e.a.l.a
        public boolean a(l lVar, MenuItem menuItem) {
            a.AbstractC0005a aVar = this.f340e;
            if (aVar != null) {
                return aVar.a(this, menuItem);
            }
            return false;
        }

        @Override // c.a.e.a.l.a
        public void a(l lVar) {
            if (this.f340e != null) {
                g();
                I.this.h.e();
            }
        }
    }

    static {
        I.class.desiredAssertionStatus();
    }

    public I(Activity activity, boolean z) {
        new ArrayList();
        View decorView = activity.getWindow().getDecorView();
        a(decorView);
        if (!z) {
            this.i = decorView.findViewById(16908290);
        }
    }

    public static boolean a(boolean z, boolean z2, boolean z3) {
        if (z3) {
            return true;
        }
        return !z && !z2;
    }

    public final void a(View view) {
        D d2;
        this.f337e = (ActionBarOverlayLayout) view.findViewById(c.a.f.decor_content_parent);
        ActionBarOverlayLayout actionBarOverlayLayout = this.f337e;
        if (actionBarOverlayLayout != null) {
            actionBarOverlayLayout.setActionBarVisibilityCallback(this);
        }
        View findViewById = view.findViewById(c.a.f.action_bar);
        if (findViewById instanceof D) {
            d2 = (D) findViewById;
        } else if (findViewById instanceof Toolbar) {
            d2 = ((Toolbar) findViewById).getWrapper();
        } else {
            StringBuilder a2 = e.a.a.a.a.a("Can't make a decor toolbar out of ");
            a2.append(findViewById != null ? findViewById.getClass().getSimpleName() : "null");
            throw new IllegalStateException(a2.toString());
        }
        this.g = d2;
        this.h = (ActionBarContextView) view.findViewById(c.a.f.action_context_bar);
        this.f = (ActionBarContainer) view.findViewById(c.a.f.action_bar_container);
        D d3 = this.g;
        if (d3 == null || this.h == null || this.f == null) {
            throw new IllegalStateException(I.class.getSimpleName() + " can only be used with a compatible window decor layout");
        }
        this.f335c = ((ra) d3).a();
        boolean z = (((ra) this.g).f636b & 4) != 0;
        if (z) {
            this.k = true;
        }
        Context context = this.f335c;
        ((ra) this.g).a((context.getApplicationInfo().targetSdkVersion < 14) || z);
        e(context.getResources().getBoolean(b.abc_action_bar_embed_tabs));
        TypedArray obtainStyledAttributes = this.f335c.obtainStyledAttributes(null, j.ActionBar, c.a.a.actionBarStyle, 0);
        if (obtainStyledAttributes.getBoolean(j.ActionBar_hideOnContentScroll, false)) {
            if (this.f337e.i()) {
                this.z = true;
                this.f337e.setHideOnContentScrollEnabled(true);
            } else {
                throw new IllegalStateException("Action bar must be in overlay mode (Window.FEATURE_OVERLAY_ACTION_BAR) to enable hide on content scroll");
            }
        }
        int dimensionPixelSize = obtainStyledAttributes.getDimensionPixelSize(j.ActionBar_elevation, 0);
        if (dimensionPixelSize != 0) {
            n.a(this.f, dimensionPixelSize);
        }
        obtainStyledAttributes.recycle();
    }

    @Override // c.a.a.AbstractC0020a
    public int b() {
        return ((ra) this.g).f636b;
    }

    @Override // c.a.a.AbstractC0020a
    public void c(boolean z) {
        h hVar;
        this.y = z;
        if (!z && (hVar = this.x) != null) {
            hVar.a();
        }
    }

    public void d(boolean z) {
        r rVar;
        r rVar2;
        if (z) {
            if (!this.v) {
                this.v = true;
                ActionBarOverlayLayout actionBarOverlayLayout = this.f337e;
                if (actionBarOverlayLayout != null) {
                    actionBarOverlayLayout.setShowingForActionMode(true);
                }
                f(false);
            }
        } else if (this.v) {
            this.v = false;
            ActionBarOverlayLayout actionBarOverlayLayout2 = this.f337e;
            if (actionBarOverlayLayout2 != null) {
                actionBarOverlayLayout2.setShowingForActionMode(false);
            }
            f(false);
        }
        if (n.p(this.f)) {
            if (z) {
                rVar = ((ra) this.g).a(4, 100L);
                rVar2 = this.h.a(0, 200L);
            } else {
                rVar2 = ((ra) this.g).a(0, 200L);
                rVar = this.h.a(8, 100L);
            }
            h hVar = new h();
            hVar.f510a.add(rVar);
            View view = rVar.f872a.get();
            long duration = view != null ? view.animate().getDuration() : 0L;
            View view2 = rVar2.f872a.get();
            if (view2 != null) {
                view2.animate().setStartDelay(duration);
            }
            hVar.f510a.add(rVar2);
            hVar.b();
        } else if (z) {
            ((ra) this.g).f635a.setVisibility(4);
            this.h.setVisibility(0);
        } else {
            ((ra) this.g).f635a.setVisibility(0);
            this.h.setVisibility(8);
        }
    }

    public void e() {
    }

    public final void e(boolean z) {
        this.q = z;
        if (!this.q) {
            ((ra) this.g).a((P) null);
            this.f.setTabContainer(this.j);
        } else {
            this.f.setTabContainer(null);
            ((ra) this.g).a(this.j);
        }
        boolean z2 = true;
        boolean z3 = ((ra) this.g).o == 2;
        P p = this.j;
        if (p != null) {
            if (z3) {
                p.setVisibility(0);
                ActionBarOverlayLayout actionBarOverlayLayout = this.f337e;
                if (actionBarOverlayLayout != null) {
                    n.r(actionBarOverlayLayout);
                }
            } else {
                p.setVisibility(8);
            }
        }
        ((ra) this.g).f635a.setCollapsible(!this.q && z3);
        ActionBarOverlayLayout actionBarOverlayLayout2 = this.f337e;
        if (this.q || !z3) {
            z2 = false;
        }
        actionBarOverlayLayout2.setHasNonEmbeddedTabs(z2);
    }

    public final void f(boolean z) {
        View view;
        View view2;
        View view3;
        if (a(this.t, this.u, this.v)) {
            if (!this.w) {
                this.w = true;
                h hVar = this.x;
                if (hVar != null) {
                    hVar.a();
                }
                this.f.setVisibility(0);
                if (this.r != 0 || (!this.y && !z)) {
                    this.f.setAlpha(1.0f);
                    this.f.setTranslationY(0.0f);
                    if (this.s && (view2 = this.i) != null) {
                        view2.setTranslationY(0.0f);
                    }
                    this.B.b(null);
                } else {
                    this.f.setTranslationY(0.0f);
                    float f = -this.f.getHeight();
                    if (z) {
                        int[] iArr = {0, 0};
                        this.f.getLocationInWindow(iArr);
                        f -= iArr[1];
                    }
                    this.f.setTranslationY(f);
                    h hVar2 = new h();
                    r a2 = n.a(this.f);
                    a2.b(0.0f);
                    a2.a(this.C);
                    if (!hVar2.f514e) {
                        hVar2.f510a.add(a2);
                    }
                    if (this.s && (view3 = this.i) != null) {
                        view3.setTranslationY(f);
                        r a3 = n.a(this.i);
                        a3.b(0.0f);
                        if (!hVar2.f514e) {
                            hVar2.f510a.add(a3);
                        }
                    }
                    hVar2.a(f334b);
                    hVar2.a(250L);
                    hVar2.a(this.B);
                    this.x = hVar2;
                    hVar2.b();
                }
                ActionBarOverlayLayout actionBarOverlayLayout = this.f337e;
                if (actionBarOverlayLayout != null) {
                    n.r(actionBarOverlayLayout);
                }
            }
        } else if (this.w) {
            this.w = false;
            h hVar3 = this.x;
            if (hVar3 != null) {
                hVar3.a();
            }
            if (this.r != 0 || (!this.y && !z)) {
                this.A.b(null);
                return;
            }
            this.f.setAlpha(1.0f);
            this.f.setTransitioning(true);
            h hVar4 = new h();
            float f2 = -this.f.getHeight();
            if (z) {
                int[] iArr2 = {0, 0};
                this.f.getLocationInWindow(iArr2);
                f2 -= iArr2[1];
            }
            r a4 = n.a(this.f);
            a4.b(f2);
            a4.a(this.C);
            if (!hVar4.f514e) {
                hVar4.f510a.add(a4);
            }
            if (this.s && (view = this.i) != null) {
                r a5 = n.a(view);
                a5.b(f2);
                if (!hVar4.f514e) {
                    hVar4.f510a.add(a5);
                }
            }
            hVar4.a(f333a);
            hVar4.a(250L);
            hVar4.a(this.A);
            this.x = hVar4;
            hVar4.b();
        }
    }

    @Override // c.a.a.AbstractC0020a
    public void b(boolean z) {
        if (!this.k) {
            int i = z ? 4 : 0;
            ra raVar = (ra) this.g;
            int i2 = raVar.f636b;
            this.k = true;
            raVar.a((i & 4) | (i2 & (-5)));
        }
    }

    @Override // c.a.a.AbstractC0020a
    public Context c() {
        if (this.f336d == null) {
            TypedValue typedValue = new TypedValue();
            this.f335c.getTheme().resolveAttribute(c.a.a.actionBarWidgetTheme, typedValue, true);
            int i = typedValue.resourceId;
            if (i != 0) {
                this.f336d = new ContextThemeWrapper(this.f335c, i);
            } else {
                this.f336d = this.f335c;
            }
        }
        return this.f336d;
    }

    public I(Dialog dialog) {
        new ArrayList();
        a(dialog.getWindow().getDecorView());
    }

    @Override // c.a.a.AbstractC0020a
    public void a(Configuration configuration) {
        e(this.f335c.getResources().getBoolean(b.abc_action_bar_embed_tabs));
    }

    @Override // c.a.a.AbstractC0020a
    public void a(boolean z) {
        if (z != this.o) {
            this.o = z;
            int size = this.p.size();
            for (int i = 0; i < size; i++) {
                this.p.get(i).onMenuVisibilityChanged(z);
            }
        }
    }

    @Override // c.a.a.AbstractC0020a
    public void a(CharSequence charSequence) {
        ra raVar = (ra) this.g;
        if (!raVar.h) {
            raVar.a(charSequence);
        }
    }

    @Override // c.a.a.AbstractC0020a
    public c.a.e.a a(a.AbstractC0005a aVar) {
        a aVar2 = this.l;
        if (aVar2 != null) {
            I i = I.this;
            if (i.l == aVar2) {
                if (!a(i.t, i.u, false)) {
                    I i2 = I.this;
                    i2.m = aVar2;
                    i2.n = aVar2.f340e;
                } else {
                    aVar2.f340e.a(aVar2);
                }
                aVar2.f340e = null;
                I.this.d(false);
                I.this.h.a();
                ((ra) I.this.g).f635a.sendAccessibilityEvent(32);
                I i3 = I.this;
                i3.f337e.setHideOnContentScrollEnabled(i3.z);
                I.this.l = null;
            }
        }
        this.f337e.setHideOnContentScrollEnabled(false);
        this.h.d();
        a aVar3 = new a(this.h.getContext(), aVar);
        aVar3.f339d.i();
        try {
            if (!aVar3.f340e.a(aVar3, aVar3.f339d)) {
                return null;
            }
            this.l = aVar3;
            aVar3.g();
            this.h.a(aVar3);
            d(true);
            this.h.sendAccessibilityEvent(32);
            return aVar3;
        } finally {
            aVar3.f339d.h();
        }
    }

    @Override // c.a.a.AbstractC0020a
    public boolean a() {
        D d2 = this.g;
        if (d2 == null || !((ra) d2).f635a.j()) {
            return false;
        }
        ((ra) this.g).f635a.c();
        return true;
    }

    @Override // c.a.a.AbstractC0020a
    public boolean a(int i, KeyEvent keyEvent) {
        l lVar;
        a aVar = this.l;
        if (aVar == null || (lVar = aVar.f339d) == null) {
            return false;
        }
        boolean z = true;
        if (KeyCharacterMap.load(keyEvent != null ? keyEvent.getDeviceId() : -1).getKeyboardType() == 1) {
            z = false;
        }
        lVar.setQwertyMode(z);
        return lVar.performShortcut(i, keyEvent, 0);
    }
}
