package c.a.e.a;

import android.content.Context;
import android.content.res.Resources;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import c.a.d;
import c.a.e.a.v;
import c.a.f.L;
import c.a.g;

/* loaded from: classes.dex */
public final class B extends s implements PopupWindow.OnDismissListener, AdapterView.OnItemClickListener, v, View.OnKeyListener {

    /* renamed from: b */
    public static final int f406b = g.abc_popup_menu_item_layout;

    /* renamed from: c */
    public final Context f407c;

    /* renamed from: d */
    public final l f408d;

    /* renamed from: e */
    public final k f409e;
    public final boolean f;
    public final int g;
    public final int h;
    public final int i;
    public final L j;
    public PopupWindow.OnDismissListener m;
    public View n;
    public View o;
    public v.a p;
    public ViewTreeObserver q;
    public boolean r;
    public boolean s;
    public int t;
    public boolean v;
    public final ViewTreeObserver.OnGlobalLayoutListener k = new z(this);
    public final View.OnAttachStateChangeListener l = new A(this);
    public int u = 0;

    public B(Context context, l lVar, View view, int i, int i2, boolean z) {
        this.f407c = context;
        this.f408d = lVar;
        this.f = z;
        this.f409e = new k(lVar, LayoutInflater.from(context), this.f, f406b);
        this.h = i;
        this.i = i2;
        Resources resources = context.getResources();
        this.g = Math.max(resources.getDisplayMetrics().widthPixels / 2, resources.getDimensionPixelSize(d.abc_config_prefDialogWidth));
        this.n = view;
        this.j = new L(this.f407c, null, this.h, this.i);
        lVar.a(this, context);
    }

    @Override // c.a.e.a.s
    public void a(int i) {
        this.u = i;
    }

    @Override // c.a.e.a.s
    public void a(l lVar) {
    }

    @Override // c.a.e.a.v
    public boolean a() {
        return false;
    }

    @Override // c.a.e.a.s
    public void b(boolean z) {
        this.f409e.f447c = z;
    }

    @Override // c.a.e.a.y
    public void c() {
        View view;
        boolean z = true;
        if (!b()) {
            if (this.r || (view = this.n) == null) {
                z = false;
            } else {
                this.o = view;
                this.j.a((PopupWindow.OnDismissListener) this);
                this.j.a((AdapterView.OnItemClickListener) this);
                this.j.a(true);
                View view2 = this.o;
                boolean z2 = this.q == null;
                this.q = view2.getViewTreeObserver();
                if (z2) {
                    this.q.addOnGlobalLayoutListener(this.k);
                }
                view2.addOnAttachStateChangeListener(this.l);
                this.j.a(view2);
                this.j.c(this.u);
                if (!this.s) {
                    this.t = s.a(this.f409e, null, this.f407c, this.g);
                    this.s = true;
                }
                this.j.b(this.t);
                this.j.e(2);
                this.j.a(f());
                this.j.c();
                ListView d2 = this.j.d();
                d2.setOnKeyListener(this);
                if (this.v && this.f408d.o != null) {
                    FrameLayout frameLayout = (FrameLayout) LayoutInflater.from(this.f407c).inflate(g.abc_popup_menu_header_item_layout, (ViewGroup) d2, false);
                    TextView textView = (TextView) frameLayout.findViewById(16908310);
                    if (textView != null) {
                        textView.setText(this.f408d.o);
                    }
                    frameLayout.setEnabled(false);
                    d2.addHeaderView(frameLayout, null, false);
                }
                this.j.a((ListAdapter) this.f409e);
                this.j.c();
            }
        }
        if (!z) {
            throw new IllegalStateException("StandardMenuPopup cannot be used without an anchor");
        }
    }

    @Override // c.a.e.a.y
    public ListView d() {
        return this.j.d();
    }

    @Override // c.a.e.a.y
    public void dismiss() {
        if (b()) {
            this.j.dismiss();
        }
    }

    @Override // android.widget.PopupWindow.OnDismissListener
    public void onDismiss() {
        this.r = true;
        this.f408d.a(true);
        ViewTreeObserver viewTreeObserver = this.q;
        if (viewTreeObserver != null) {
            if (!viewTreeObserver.isAlive()) {
                this.q = this.o.getViewTreeObserver();
            }
            this.q.removeGlobalOnLayoutListener(this.k);
            this.q = null;
        }
        this.o.removeOnAttachStateChangeListener(this.l);
        PopupWindow.OnDismissListener onDismissListener = this.m;
        if (onDismissListener != null) {
            onDismissListener.onDismiss();
        }
    }

    @Override // android.view.View.OnKeyListener
    public boolean onKey(View view, int i, KeyEvent keyEvent) {
        if (keyEvent.getAction() != 1 || i != 82) {
            return false;
        }
        dismiss();
        return true;
    }

    @Override // c.a.e.a.v
    public void a(boolean z) {
        this.s = false;
        k kVar = this.f409e;
        if (kVar != null) {
            kVar.notifyDataSetChanged();
        }
    }

    @Override // c.a.e.a.y
    public boolean b() {
        return !this.r && this.j.b();
    }

    @Override // c.a.e.a.s
    public void b(int i) {
        this.j.d(i);
    }

    @Override // c.a.e.a.v
    public void a(v.a aVar) {
        this.p = aVar;
    }

    /* JADX WARN: Removed duplicated region for block: B:18:0x006e  */
    @Override // c.a.e.a.v
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean a(c.a.e.a.C r10) {
        /*
            r9 = this;
            boolean r0 = r10.hasVisibleItems()
            r1 = 0
            if (r0 == 0) goto L_0x0076
            c.a.e.a.u r0 = new c.a.e.a.u
            android.content.Context r3 = r9.f407c
            android.view.View r5 = r9.o
            boolean r6 = r9.f
            int r7 = r9.h
            int r8 = r9.i
            r2 = r0
            r4 = r10
            r2.<init>(r3, r4, r5, r6, r7, r8)
            c.a.e.a.v$a r2 = r9.p
            r0.a(r2)
            boolean r2 = c.a.e.a.s.b(r10)
            r0.h = r2
            c.a.e.a.s r3 = r0.j
            if (r3 == 0) goto L_0x002a
            r3.b(r2)
        L_0x002a:
            android.widget.PopupWindow$OnDismissListener r2 = r9.m
            r0.k = r2
            r2 = 0
            r9.m = r2
            c.a.e.a.l r2 = r9.f408d
            r2.a(r1)
            c.a.f.L r2 = r9.j
            int r2 = r2.g()
            c.a.f.L r3 = r9.j
            int r3 = r3.h()
            int r4 = r9.u
            android.view.View r5 = r9.n
            int r5 = c.e.h.n.g(r5)
            int r4 = android.view.Gravity.getAbsoluteGravity(r4, r5)
            r4 = r4 & 7
            r5 = 5
            if (r4 != r5) goto L_0x005a
            android.view.View r4 = r9.n
            int r4 = r4.getWidth()
            int r2 = r2 + r4
        L_0x005a:
            boolean r4 = r0.c()
            r5 = 1
            if (r4 == 0) goto L_0x0062
            goto L_0x006b
        L_0x0062:
            android.view.View r4 = r0.f
            if (r4 != 0) goto L_0x0068
            r0 = 0
            goto L_0x006c
        L_0x0068:
            r0.a(r2, r3, r5, r5)
        L_0x006b:
            r0 = 1
        L_0x006c:
            if (r0 == 0) goto L_0x0076
            c.a.e.a.v$a r0 = r9.p
            if (r0 == 0) goto L_0x0075
            r0.a(r10)
        L_0x0075:
            return r5
        L_0x0076:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: c.a.e.a.B.a(c.a.e.a.C):boolean");
    }

    @Override // c.a.e.a.v
    public void a(l lVar, boolean z) {
        if (lVar == this.f408d) {
            dismiss();
            v.a aVar = this.p;
            if (aVar != null) {
                aVar.a(lVar, z);
            }
        }
    }

    @Override // c.a.e.a.s
    public void a(View view) {
        this.n = view;
    }

    @Override // c.a.e.a.s
    public void a(PopupWindow.OnDismissListener onDismissListener) {
        this.m = onDismissListener;
    }

    @Override // c.a.e.a.s
    public void c(int i) {
        this.j.h(i);
    }

    @Override // c.a.e.a.s
    public void c(boolean z) {
        this.v = z;
    }
}
