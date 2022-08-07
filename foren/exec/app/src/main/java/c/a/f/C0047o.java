package c.a.f;

import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import c.a.j;
import c.e.h.n;

/* renamed from: c.a.f.o */
/* loaded from: classes.dex */
public class C0047o {

    /* renamed from: a */
    public final View f611a;

    /* renamed from: d */
    public ia f614d;

    /* renamed from: e */
    public ia f615e;
    public ia f;

    /* renamed from: c */
    public int f613c = -1;

    /* renamed from: b */
    public final C0049q f612b = C0049q.a();

    public C0047o(View view) {
        this.f611a = view;
    }

    public void a(AttributeSet attributeSet, int i) {
        ka a2 = ka.a(this.f611a.getContext(), attributeSet, j.ViewBackgroundHelper, i, 0);
        try {
            if (a2.f(j.ViewBackgroundHelper_android_background)) {
                this.f613c = a2.f(j.ViewBackgroundHelper_android_background, -1);
                ColorStateList d2 = this.f612b.d(this.f611a.getContext(), this.f613c);
                if (d2 != null) {
                    a(d2);
                }
            }
            if (a2.f(j.ViewBackgroundHelper_backgroundTint)) {
                n.a(this.f611a, a2.a(j.ViewBackgroundHelper_backgroundTint));
            }
            if (a2.f(j.ViewBackgroundHelper_backgroundTintMode)) {
                n.a(this.f611a, E.a(a2.d(j.ViewBackgroundHelper_backgroundTintMode, -1), null));
            }
        } finally {
            a2.f605b.recycle();
        }
    }

    public void b(ColorStateList colorStateList) {
        if (this.f615e == null) {
            this.f615e = new ia();
        }
        ia iaVar = this.f615e;
        iaVar.f598a = colorStateList;
        iaVar.f601d = true;
        a();
    }

    public PorterDuff.Mode c() {
        ia iaVar = this.f615e;
        if (iaVar != null) {
            return iaVar.f599b;
        }
        return null;
    }

    public ColorStateList b() {
        ia iaVar = this.f615e;
        if (iaVar != null) {
            return iaVar.f598a;
        }
        return null;
    }

    public void a(int i) {
        this.f613c = i;
        C0049q qVar = this.f612b;
        a(qVar != null ? qVar.d(this.f611a.getContext(), i) : null);
        a();
    }

    public void a(PorterDuff.Mode mode) {
        if (this.f615e == null) {
            this.f615e = new ia();
        }
        ia iaVar = this.f615e;
        iaVar.f599b = mode;
        iaVar.f600c = true;
        a();
    }

    public void a() {
        Drawable background = this.f611a.getBackground();
        if (background != null) {
            int i = Build.VERSION.SDK_INT;
            boolean z = true;
            if (i <= 21 ? i == 21 : this.f614d != null) {
                if (this.f == null) {
                    this.f = new ia();
                }
                ia iaVar = this.f;
                iaVar.a();
                ColorStateList b2 = n.b(this.f611a);
                if (b2 != null) {
                    iaVar.f601d = true;
                    iaVar.f598a = b2;
                }
                PorterDuff.Mode c2 = n.c(this.f611a);
                if (c2 != null) {
                    iaVar.f600c = true;
                    iaVar.f599b = c2;
                }
                if (iaVar.f601d || iaVar.f600c) {
                    C0049q.a(background, iaVar, this.f611a.getDrawableState());
                } else {
                    z = false;
                }
                if (z) {
                    return;
                }
            }
            ia iaVar2 = this.f615e;
            if (iaVar2 != null) {
                C0049q.a(background, iaVar2, this.f611a.getDrawableState());
                return;
            }
            ia iaVar3 = this.f614d;
            if (iaVar3 != null) {
                C0049q.a(background, iaVar3, this.f611a.getDrawableState());
            }
        }
    }

    public void a(ColorStateList colorStateList) {
        if (colorStateList != null) {
            if (this.f614d == null) {
                this.f614d = new ia();
            }
            ia iaVar = this.f614d;
            iaVar.f598a = colorStateList;
            iaVar.f601d = true;
        } else {
            this.f614d = null;
        }
        a();
    }
}
