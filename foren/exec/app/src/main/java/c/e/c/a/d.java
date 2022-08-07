package c.e.c.a;

import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Outline;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.DrawableContainer;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.InsetDrawable;
import android.graphics.drawable.RippleDrawable;
import android.os.Build;
import c.e.c.a.c;
import java.lang.reflect.Method;

/* loaded from: classes.dex */
public class d extends c {
    public static Method h;

    /* loaded from: classes.dex */
    private static class a extends c.a {
        public a(c.a aVar, Resources resources) {
            super(aVar, resources);
        }

        @Override // c.e.c.a.c.a, android.graphics.drawable.Drawable.ConstantState
        public Drawable newDrawable(Resources resources) {
            return new d(this, resources);
        }
    }

    public d(Drawable drawable) {
        super(drawable);
        if (h == null) {
            try {
                h = Drawable.class.getDeclaredMethod("isProjected", new Class[0]);
            } catch (Exception unused) {
            }
        }
    }

    @Override // c.e.c.a.c
    public boolean a() {
        if (Build.VERSION.SDK_INT != 21) {
            return false;
        }
        Drawable drawable = this.g;
        return (drawable instanceof GradientDrawable) || (drawable instanceof DrawableContainer) || (drawable instanceof InsetDrawable) || (drawable instanceof RippleDrawable);
    }

    @Override // c.e.c.a.c
    public c.a b() {
        return new a(this.f769e, null);
    }

    @Override // android.graphics.drawable.Drawable
    public Rect getDirtyBounds() {
        return this.g.getDirtyBounds();
    }

    @Override // android.graphics.drawable.Drawable
    public void getOutline(Outline outline) {
        this.g.getOutline(outline);
    }

    @Override // android.graphics.drawable.Drawable
    public boolean isProjected() {
        Method method;
        Drawable drawable = this.g;
        if (!(drawable == null || (method = h) == null)) {
            try {
                return ((Boolean) method.invoke(drawable, new Object[0])).booleanValue();
            } catch (Exception unused) {
            }
        }
        return false;
    }

    @Override // android.graphics.drawable.Drawable
    public void setHotspot(float f, float f2) {
        this.g.setHotspot(f, f2);
    }

    @Override // android.graphics.drawable.Drawable
    public void setHotspotBounds(int i, int i2, int i3, int i4) {
        this.g.setHotspotBounds(i, i2, i3, i4);
    }

    @Override // c.e.c.a.c, android.graphics.drawable.Drawable
    public boolean setState(int[] iArr) {
        if (!(a(iArr) || this.g.setState(iArr))) {
            return false;
        }
        invalidateSelf();
        return true;
    }

    @Override // c.e.c.a.c, android.graphics.drawable.Drawable
    public void setTint(int i) {
        if (a()) {
            setTintList(ColorStateList.valueOf(i));
        } else {
            this.g.setTint(i);
        }
    }

    @Override // c.e.c.a.c, android.graphics.drawable.Drawable
    public void setTintList(ColorStateList colorStateList) {
        if (a()) {
            this.f769e.f772c = colorStateList;
            a(getState());
            return;
        }
        this.g.setTintList(colorStateList);
    }

    @Override // c.e.c.a.c, android.graphics.drawable.Drawable
    public void setTintMode(PorterDuff.Mode mode) {
        if (a()) {
            this.f769e.f773d = mode;
            a(getState());
            return;
        }
        this.g.setTintMode(mode);
    }

    public d(c.a aVar, Resources resources) {
        super(aVar, resources);
        if (h == null) {
            try {
                h = Drawable.class.getDeclaredMethod("isProjected", new Class[0]);
            } catch (Exception unused) {
            }
        }
    }
}
