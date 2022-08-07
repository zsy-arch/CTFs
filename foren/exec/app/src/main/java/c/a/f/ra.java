package c.a.f;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import androidx.appcompat.widget.ActionMenuPresenter;
import androidx.appcompat.widget.Toolbar;
import c.a.a;
import c.a.e;
import c.a.h;
import c.a.j;
import c.e.h.n;
import c.e.h.r;

/* loaded from: classes.dex */
public class ra implements D {

    /* renamed from: a */
    public Toolbar f635a;

    /* renamed from: b */
    public int f636b;

    /* renamed from: c */
    public View f637c;

    /* renamed from: d */
    public View f638d;

    /* renamed from: e */
    public Drawable f639e;
    public Drawable f;
    public Drawable g;
    public boolean h;
    public CharSequence i;
    public CharSequence j;
    public CharSequence k;
    public Window.Callback l;
    public boolean m;
    public ActionMenuPresenter n;
    public int o = 0;
    public int p;
    public Drawable q;

    public ra(Toolbar toolbar, boolean z) {
        int i;
        Drawable drawable;
        int i2 = h.abc_action_bar_up_description;
        int i3 = e.abc_ic_ab_back_material;
        this.p = 0;
        this.f635a = toolbar;
        this.i = toolbar.getTitle();
        this.j = toolbar.getSubtitle();
        this.h = this.i != null;
        this.g = toolbar.getNavigationIcon();
        String str = null;
        ka a2 = ka.a(toolbar.getContext(), null, j.ActionBar, a.actionBarStyle, 0);
        this.q = a2.b(j.ActionBar_homeAsUpIndicator);
        if (z) {
            CharSequence e2 = a2.e(j.ActionBar_title);
            if (!TextUtils.isEmpty(e2)) {
                this.h = true;
                a(e2);
            }
            CharSequence e3 = a2.e(j.ActionBar_subtitle);
            if (!TextUtils.isEmpty(e3)) {
                this.j = e3;
                if ((this.f636b & 8) != 0) {
                    this.f635a.setSubtitle(e3);
                }
            }
            Drawable b2 = a2.b(j.ActionBar_logo);
            if (b2 != null) {
                a(b2);
            }
            Drawable b3 = a2.b(j.ActionBar_icon);
            if (b3 != null) {
                this.f639e = b3;
                f();
            }
            if (this.g == null && (drawable = this.q) != null) {
                this.g = drawable;
                e();
            }
            a(a2.d(j.ActionBar_displayOptions, 0));
            int f = a2.f(j.ActionBar_customNavigationLayout, 0);
            if (f != 0) {
                View inflate = LayoutInflater.from(this.f635a.getContext()).inflate(f, (ViewGroup) this.f635a, false);
                View view = this.f638d;
                if (!(view == null || (this.f636b & 16) == 0)) {
                    this.f635a.removeView(view);
                }
                this.f638d = inflate;
                if (!(inflate == null || (this.f636b & 16) == 0)) {
                    this.f635a.addView(this.f638d);
                }
                a(this.f636b | 16);
            }
            int e4 = a2.e(j.ActionBar_height, 0);
            if (e4 > 0) {
                ViewGroup.LayoutParams layoutParams = this.f635a.getLayoutParams();
                layoutParams.height = e4;
                this.f635a.setLayoutParams(layoutParams);
            }
            int b4 = a2.b(j.ActionBar_contentInsetStart, -1);
            int b5 = a2.b(j.ActionBar_contentInsetEnd, -1);
            if (b4 >= 0 || b5 >= 0) {
                this.f635a.a(Math.max(b4, 0), Math.max(b5, 0));
            }
            int f2 = a2.f(j.ActionBar_titleTextStyle, 0);
            if (f2 != 0) {
                Toolbar toolbar2 = this.f635a;
                toolbar2.b(toolbar2.getContext(), f2);
            }
            int f3 = a2.f(j.ActionBar_subtitleTextStyle, 0);
            if (f3 != 0) {
                Toolbar toolbar3 = this.f635a;
                toolbar3.a(toolbar3.getContext(), f3);
            }
            int f4 = a2.f(j.ActionBar_popupTheme, 0);
            if (f4 != 0) {
                this.f635a.setPopupTheme(f4);
            }
        } else {
            if (this.f635a.getNavigationIcon() != null) {
                i = 15;
                this.q = this.f635a.getNavigationIcon();
            } else {
                i = 11;
            }
            this.f636b = i;
        }
        a2.f605b.recycle();
        if (i2 != this.p) {
            this.p = i2;
            if (TextUtils.isEmpty(this.f635a.getNavigationContentDescription())) {
                int i4 = this.p;
                this.k = i4 != 0 ? a().getString(i4) : str;
                d();
            }
        }
        this.k = this.f635a.getNavigationContentDescription();
        this.f635a.setNavigationOnClickListener(new pa(this));
    }

    public Context a() {
        return this.f635a.getContext();
    }

    public void a(boolean z) {
    }

    public void b() {
    }

    public void c() {
    }

    public final void d() {
        if ((this.f636b & 4) == 0) {
            return;
        }
        if (TextUtils.isEmpty(this.k)) {
            this.f635a.setNavigationContentDescription(this.p);
        } else {
            this.f635a.setNavigationContentDescription(this.k);
        }
    }

    public final void e() {
        if ((this.f636b & 4) != 0) {
            Toolbar toolbar = this.f635a;
            Drawable drawable = this.g;
            if (drawable == null) {
                drawable = this.q;
            }
            toolbar.setNavigationIcon(drawable);
            return;
        }
        this.f635a.setNavigationIcon((Drawable) null);
    }

    public final void f() {
        Drawable drawable;
        int i = this.f636b;
        if ((i & 2) == 0) {
            drawable = null;
        } else if ((i & 1) != 0) {
            drawable = this.f;
            if (drawable == null) {
                drawable = this.f639e;
            }
        } else {
            drawable = this.f639e;
        }
        this.f635a.setLogo(drawable);
    }

    public final void a(CharSequence charSequence) {
        this.i = charSequence;
        if ((this.f636b & 8) != 0) {
            this.f635a.setTitle(charSequence);
        }
    }

    public void a(Drawable drawable) {
        this.f = drawable;
        f();
    }

    public void a(int i) {
        View view;
        int i2 = this.f636b ^ i;
        this.f636b = i;
        if (i2 != 0) {
            if ((i2 & 4) != 0) {
                if ((i & 4) != 0) {
                    d();
                }
                e();
            }
            if ((i2 & 3) != 0) {
                f();
            }
            if ((i2 & 8) != 0) {
                if ((i & 8) != 0) {
                    this.f635a.setTitle(this.i);
                    this.f635a.setSubtitle(this.j);
                } else {
                    this.f635a.setTitle((CharSequence) null);
                    this.f635a.setSubtitle((CharSequence) null);
                }
            }
            if ((i2 & 16) != 0 && (view = this.f638d) != null) {
                if ((i & 16) != 0) {
                    this.f635a.addView(view);
                } else {
                    this.f635a.removeView(view);
                }
            }
        }
    }

    public void a(P p) {
        Toolbar toolbar;
        View view = this.f637c;
        if (view != null && view.getParent() == (toolbar = this.f635a)) {
            toolbar.removeView(this.f637c);
        }
        this.f637c = p;
        if (p != null && this.o == 2) {
            this.f635a.addView(this.f637c, 0);
            Toolbar.b bVar = (Toolbar.b) this.f637c.getLayoutParams();
            ((ViewGroup.MarginLayoutParams) bVar).width = -2;
            ((ViewGroup.MarginLayoutParams) bVar).height = -2;
            bVar.f341a = 8388691;
            p.setAllowCollapse(true);
        }
    }

    public r a(int i, long j) {
        r a2 = n.a(this.f635a);
        a2.a(i == 0 ? 1.0f : 0.0f);
        a2.a(j);
        a2.a(new qa(this, i));
        return a2;
    }
}
