package c.a.e.a;

import android.content.Context;
import android.content.res.Resources;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ListView;
import android.widget.PopupWindow;
import c.a.a.C;
import c.a.d;
import c.a.e.a.v;
import c.a.f.K;
import c.a.f.L;
import c.a.g;
import c.e.h.n;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
public final class i extends s implements v, View.OnKeyListener, PopupWindow.OnDismissListener {

    /* renamed from: b */
    public static final int f431b = g.abc_cascading_menu_item_layout;
    public PopupWindow.OnDismissListener A;
    public boolean B;

    /* renamed from: c */
    public final Context f432c;

    /* renamed from: d */
    public final int f433d;

    /* renamed from: e */
    public final int f434e;
    public final int f;
    public final boolean g;
    public final Handler h;
    public View p;
    public View q;
    public int r;
    public boolean s;
    public boolean t;
    public int u;
    public int v;
    public boolean x;
    public v.a y;
    public ViewTreeObserver z;
    public final List<l> i = new ArrayList();
    public final List<a> j = new ArrayList();
    public final ViewTreeObserver.OnGlobalLayoutListener k = new e(this);
    public final View.OnAttachStateChangeListener l = new f(this);
    public final K m = new h(this);
    public int n = 0;
    public int o = 0;
    public boolean w = false;

    /* loaded from: classes.dex */
    public static class a {

        /* renamed from: a */
        public final L f435a;

        /* renamed from: b */
        public final l f436b;

        /* renamed from: c */
        public final int f437c;

        public a(L l, l lVar, int i) {
            this.f435a = l;
            this.f436b = lVar;
            this.f437c = i;
        }

        public ListView a() {
            return this.f435a.d();
        }
    }

    public i(Context context, View view, int i, int i2, boolean z) {
        this.f432c = context;
        this.p = view;
        this.f434e = i;
        this.f = i2;
        this.g = z;
        this.r = n.g(this.p) == 1 ? 0 : 1;
        Resources resources = context.getResources();
        this.f433d = Math.max(resources.getDisplayMetrics().widthPixels / 2, resources.getDimensionPixelSize(d.abc_config_prefDialogWidth));
        this.h = new Handler();
    }

    @Override // c.a.e.a.s
    public void a(l lVar) {
        lVar.a(this, this.f432c);
        if (b()) {
            c(lVar);
        } else {
            this.i.add(lVar);
        }
    }

    @Override // c.a.e.a.v
    public boolean a() {
        return false;
    }

    @Override // c.a.e.a.s
    public void b(boolean z) {
        this.w = z;
    }

    @Override // c.a.e.a.y
    public void c() {
        if (!b()) {
            for (l lVar : this.i) {
                c(lVar);
            }
            this.i.clear();
            this.q = this.p;
            if (this.q != null) {
                boolean z = this.z == null;
                this.z = this.q.getViewTreeObserver();
                if (z) {
                    this.z.addOnGlobalLayoutListener(this.k);
                }
                this.q.addOnAttachStateChangeListener(this.l);
            }
        }
    }

    @Override // c.a.e.a.y
    public ListView d() {
        if (this.j.isEmpty()) {
            return null;
        }
        List<a> list = this.j;
        return list.get(list.size() - 1).a();
    }

    @Override // c.a.e.a.y
    public void dismiss() {
        int size = this.j.size();
        if (size > 0) {
            a[] aVarArr = (a[]) this.j.toArray(new a[size]);
            for (int i = size - 1; i >= 0; i--) {
                a aVar = aVarArr[i];
                if (aVar.f435a.b()) {
                    aVar.f435a.dismiss();
                }
            }
        }
    }

    @Override // c.a.e.a.s
    public boolean e() {
        return false;
    }

    @Override // android.widget.PopupWindow.OnDismissListener
    public void onDismiss() {
        a aVar;
        int size = this.j.size();
        int i = 0;
        while (true) {
            if (i >= size) {
                aVar = null;
                break;
            }
            aVar = this.j.get(i);
            if (!aVar.f435a.b()) {
                break;
            }
            i++;
        }
        if (aVar != null) {
            aVar.f436b.a(false);
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

    @Override // c.a.e.a.y
    public boolean b() {
        return this.j.size() > 0 && this.j.get(0).f435a.b();
    }

    @Override // c.a.e.a.s
    public void b(int i) {
        this.s = true;
        this.u = i;
    }

    @Override // c.a.e.a.v
    public void a(boolean z) {
        for (a aVar : this.j) {
            s.a(aVar.a().getAdapter()).notifyDataSetChanged();
        }
    }

    @Override // c.a.e.a.v
    public void a(v.a aVar) {
        this.y = aVar;
    }

    @Override // c.a.e.a.v
    public boolean a(C c2) {
        for (a aVar : this.j) {
            if (c2 == aVar.f436b) {
                aVar.a().requestFocus();
                return true;
            }
        }
        if (!c2.hasVisibleItems()) {
            return false;
        }
        c2.a(this, this.f432c);
        if (b()) {
            c(c2);
        } else {
            this.i.add(c2);
        }
        v.a aVar2 = this.y;
        if (aVar2 != null) {
            aVar2.a(c2);
        }
        return true;
    }

    /* JADX WARN: Removed duplicated region for block: B:41:0x00e9  */
    /* JADX WARN: Removed duplicated region for block: B:75:0x01a7  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void c(c.a.e.a.l r17) {
        /*
            Method dump skipped, instructions count: 511
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: c.a.e.a.i.c(c.a.e.a.l):void");
    }

    @Override // c.a.e.a.v
    public void a(l lVar, boolean z) {
        int size = this.j.size();
        int i = 0;
        while (true) {
            if (i >= size) {
                i = -1;
                break;
            } else if (lVar == this.j.get(i).f436b) {
                break;
            } else {
                i++;
            }
        }
        if (i >= 0) {
            int i2 = i + 1;
            if (i2 < this.j.size()) {
                this.j.get(i2).f436b.a(false);
            }
            a remove = this.j.remove(i);
            remove.f436b.a(this);
            if (this.B) {
                remove.f435a.a((Object) null);
                remove.f435a.a(0);
            }
            remove.f435a.dismiss();
            int size2 = this.j.size();
            if (size2 > 0) {
                this.r = this.j.get(size2 - 1).f437c;
            } else {
                this.r = n.g(this.p) == 1 ? 0 : 1;
            }
            if (size2 == 0) {
                dismiss();
                v.a aVar = this.y;
                if (aVar != null) {
                    aVar.a(lVar, true);
                }
                ViewTreeObserver viewTreeObserver = this.z;
                if (viewTreeObserver != null) {
                    if (viewTreeObserver.isAlive()) {
                        this.z.removeGlobalOnLayoutListener(this.k);
                    }
                    this.z = null;
                }
                this.q.removeOnAttachStateChangeListener(this.l);
                this.A.onDismiss();
            } else if (z) {
                this.j.get(0).f436b.a(false);
            }
        }
    }

    @Override // c.a.e.a.s
    public void a(int i) {
        if (this.n != i) {
            this.n = i;
            this.o = C.a(i, n.g(this.p));
        }
    }

    @Override // c.a.e.a.s
    public void a(View view) {
        if (this.p != view) {
            this.p = view;
            this.o = C.a(this.n, n.g(this.p));
        }
    }

    @Override // c.a.e.a.s
    public void a(PopupWindow.OnDismissListener onDismissListener) {
        this.A = onDismissListener;
    }

    @Override // c.a.e.a.s
    public void c(int i) {
        this.t = true;
        this.v = i;
    }

    @Override // c.a.e.a.s
    public void c(boolean z) {
        this.x = z;
    }
}
