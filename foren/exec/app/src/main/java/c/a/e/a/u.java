package c.a.e.a;

import android.content.Context;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Build;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.PopupWindow;
import c.a.a.C;
import c.a.d;
import c.a.e.a.v;
import c.e.h.n;

/* loaded from: classes.dex */
public class u implements n {

    /* renamed from: a */
    public final Context f474a;

    /* renamed from: b */
    public final l f475b;

    /* renamed from: c */
    public final boolean f476c;

    /* renamed from: d */
    public final int f477d;

    /* renamed from: e */
    public final int f478e;
    public View f;
    public boolean h;
    public v.a i;
    public s j;
    public PopupWindow.OnDismissListener k;
    public int g = 8388611;
    public final PopupWindow.OnDismissListener l = new t(this);

    public u(Context context, l lVar, View view, boolean z, int i, int i2) {
        this.f474a = context;
        this.f475b = lVar;
        this.f = view;
        this.f476c = z;
        this.f477d = i;
        this.f478e = i2;
    }

    public final void a(int i, int i2, boolean z, boolean z2) {
        s b2 = b();
        b2.c(z2);
        if (z) {
            if ((C.a(this.g, n.g(this.f)) & 7) == 5) {
                i -= this.f.getWidth();
            }
            b2.b(i);
            b2.c(i2);
            int i3 = (int) ((this.f474a.getResources().getDisplayMetrics().density * 48.0f) / 2.0f);
            b2.f472a = new Rect(i - i3, i2 - i3, i + i3, i2 + i3);
        }
        b2.c();
    }

    public s b() {
        s sVar;
        if (this.j == null) {
            Display defaultDisplay = ((WindowManager) this.f474a.getSystemService("window")).getDefaultDisplay();
            Point point = new Point();
            int i = Build.VERSION.SDK_INT;
            defaultDisplay.getRealSize(point);
            if (Math.min(point.x, point.y) >= this.f474a.getResources().getDimensionPixelSize(d.abc_cascading_menus_min_smallest_width)) {
                sVar = new i(this.f474a, this.f, this.f477d, this.f478e, this.f476c);
            } else {
                sVar = new B(this.f474a, this.f475b, this.f, this.f477d, this.f478e, this.f476c);
            }
            sVar.a(this.f475b);
            sVar.a(this.l);
            sVar.a(this.f);
            sVar.a(this.i);
            sVar.b(this.h);
            sVar.a(this.g);
            this.j = sVar;
        }
        return this.j;
    }

    public boolean c() {
        s sVar = this.j;
        return sVar != null && sVar.b();
    }

    public void d() {
        this.j = null;
        PopupWindow.OnDismissListener onDismissListener = this.k;
        if (onDismissListener != null) {
            onDismissListener.onDismiss();
        }
    }

    public boolean e() {
        if (c()) {
            return true;
        }
        if (this.f == null) {
            return false;
        }
        a(0, 0, false, false);
        return true;
    }

    public void a() {
        if (c()) {
            this.j.dismiss();
        }
    }

    public void a(v.a aVar) {
        this.i = aVar;
        s sVar = this.j;
        if (sVar != null) {
            sVar.a(aVar);
        }
    }
}
