package c.e.c.a;

import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.Region;
import android.graphics.drawable.Drawable;

/* loaded from: classes.dex */
public class c extends Drawable implements Drawable.Callback, b, a {

    /* renamed from: a  reason: collision with root package name */
    public static final PorterDuff.Mode f765a = PorterDuff.Mode.SRC_IN;

    /* renamed from: b  reason: collision with root package name */
    public int f766b;

    /* renamed from: c  reason: collision with root package name */
    public PorterDuff.Mode f767c;

    /* renamed from: d  reason: collision with root package name */
    public boolean f768d;

    /* renamed from: e  reason: collision with root package name */
    public a f769e;
    public boolean f;
    public Drawable g;

    /* JADX INFO: Access modifiers changed from: protected */
    /* loaded from: classes.dex */
    public static abstract class a extends Drawable.ConstantState {

        /* renamed from: a  reason: collision with root package name */
        public int f770a;

        /* renamed from: b  reason: collision with root package name */
        public Drawable.ConstantState f771b;

        /* renamed from: c  reason: collision with root package name */
        public ColorStateList f772c;

        /* renamed from: d  reason: collision with root package name */
        public PorterDuff.Mode f773d;

        public a(a aVar, Resources resources) {
            this.f772c = null;
            this.f773d = c.f765a;
            if (aVar != null) {
                this.f770a = aVar.f770a;
                this.f771b = aVar.f771b;
                this.f772c = aVar.f772c;
                this.f773d = aVar.f773d;
            }
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public int getChangingConfigurations() {
            int i = this.f770a;
            Drawable.ConstantState constantState = this.f771b;
            return i | (constantState != null ? constantState.getChangingConfigurations() : 0);
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public Drawable newDrawable() {
            return newDrawable(null);
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public abstract Drawable newDrawable(Resources resources);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class b extends a {
        public b(a aVar, Resources resources) {
            super(aVar, resources);
        }

        @Override // c.e.c.a.c.a, android.graphics.drawable.Drawable.ConstantState
        public Drawable newDrawable(Resources resources) {
            return new c(this, resources);
        }
    }

    public c(a aVar, Resources resources) {
        Drawable.ConstantState constantState;
        this.f769e = aVar;
        a aVar2 = this.f769e;
        if (aVar2 != null && (constantState = aVar2.f771b) != null) {
            a(constantState.newDrawable(resources));
        }
    }

    public boolean a() {
        return true;
    }

    public final boolean a(int[] iArr) {
        if (!a()) {
            return false;
        }
        a aVar = this.f769e;
        ColorStateList colorStateList = aVar.f772c;
        PorterDuff.Mode mode = aVar.f773d;
        if (colorStateList == null || mode == null) {
            this.f768d = false;
            clearColorFilter();
        } else {
            int colorForState = colorStateList.getColorForState(iArr, colorStateList.getDefaultColor());
            if (!(this.f768d && colorForState == this.f766b && mode == this.f767c)) {
                setColorFilter(colorForState, mode);
                this.f766b = colorForState;
                this.f767c = mode;
                this.f768d = true;
                return true;
            }
        }
        return false;
    }

    public a b() {
        return new b(this.f769e, null);
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        this.g.draw(canvas);
    }

    @Override // android.graphics.drawable.Drawable
    public int getChangingConfigurations() {
        int changingConfigurations = super.getChangingConfigurations();
        a aVar = this.f769e;
        int i = 0;
        if (aVar != null) {
            int i2 = aVar.f770a;
            Drawable.ConstantState constantState = aVar.f771b;
            if (constantState != null) {
                i = constantState.getChangingConfigurations();
            }
            i |= i2;
        }
        return changingConfigurations | i | this.g.getChangingConfigurations();
    }

    @Override // android.graphics.drawable.Drawable
    public Drawable.ConstantState getConstantState() {
        a aVar = this.f769e;
        if (aVar == null) {
            return null;
        }
        int i = 0;
        if (!(aVar.f771b != null)) {
            return null;
        }
        a aVar2 = this.f769e;
        int changingConfigurations = super.getChangingConfigurations();
        a aVar3 = this.f769e;
        if (aVar3 != null) {
            int i2 = aVar3.f770a;
            Drawable.ConstantState constantState = aVar3.f771b;
            if (constantState != null) {
                i = constantState.getChangingConfigurations();
            }
            i |= i2;
        }
        aVar2.f770a = i | changingConfigurations | this.g.getChangingConfigurations();
        return this.f769e;
    }

    @Override // android.graphics.drawable.Drawable
    public Drawable getCurrent() {
        return this.g.getCurrent();
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicHeight() {
        return this.g.getIntrinsicHeight();
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicWidth() {
        return this.g.getIntrinsicWidth();
    }

    @Override // android.graphics.drawable.Drawable
    public int getMinimumHeight() {
        return this.g.getMinimumHeight();
    }

    @Override // android.graphics.drawable.Drawable
    public int getMinimumWidth() {
        return this.g.getMinimumWidth();
    }

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        return this.g.getOpacity();
    }

    @Override // android.graphics.drawable.Drawable
    public boolean getPadding(Rect rect) {
        return this.g.getPadding(rect);
    }

    @Override // android.graphics.drawable.Drawable
    public int[] getState() {
        return this.g.getState();
    }

    @Override // android.graphics.drawable.Drawable
    public Region getTransparentRegion() {
        return this.g.getTransparentRegion();
    }

    @Override // android.graphics.drawable.Drawable.Callback
    public void invalidateDrawable(Drawable drawable) {
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable
    public boolean isAutoMirrored() {
        return this.g.isAutoMirrored();
    }

    @Override // android.graphics.drawable.Drawable
    public boolean isStateful() {
        a aVar;
        ColorStateList colorStateList = (!a() || (aVar = this.f769e) == null) ? null : aVar.f772c;
        return (colorStateList != null && colorStateList.isStateful()) || this.g.isStateful();
    }

    @Override // android.graphics.drawable.Drawable
    public void jumpToCurrentState() {
        this.g.jumpToCurrentState();
    }

    @Override // android.graphics.drawable.Drawable
    public Drawable mutate() {
        if (!this.f && super.mutate() == this) {
            this.f769e = b();
            Drawable drawable = this.g;
            if (drawable != null) {
                drawable.mutate();
            }
            a aVar = this.f769e;
            if (aVar != null) {
                Drawable drawable2 = this.g;
                aVar.f771b = drawable2 != null ? drawable2.getConstantState() : null;
            }
            this.f = true;
        }
        return this;
    }

    @Override // android.graphics.drawable.Drawable
    public void onBoundsChange(Rect rect) {
        Drawable drawable = this.g;
        if (drawable != null) {
            drawable.setBounds(rect);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public boolean onLevelChange(int i) {
        return this.g.setLevel(i);
    }

    @Override // android.graphics.drawable.Drawable.Callback
    public void scheduleDrawable(Drawable drawable, Runnable runnable, long j) {
        scheduleSelf(runnable, j);
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int i) {
        this.g.setAlpha(i);
    }

    @Override // android.graphics.drawable.Drawable
    public void setAutoMirrored(boolean z) {
        this.g.setAutoMirrored(z);
    }

    @Override // android.graphics.drawable.Drawable
    public void setChangingConfigurations(int i) {
        this.g.setChangingConfigurations(i);
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter colorFilter) {
        this.g.setColorFilter(colorFilter);
    }

    @Override // android.graphics.drawable.Drawable
    public void setDither(boolean z) {
        this.g.setDither(z);
    }

    @Override // android.graphics.drawable.Drawable
    public void setFilterBitmap(boolean z) {
        this.g.setFilterBitmap(z);
    }

    @Override // android.graphics.drawable.Drawable
    public boolean setState(int[] iArr) {
        return a(iArr) || this.g.setState(iArr);
    }

    @Override // android.graphics.drawable.Drawable
    public void setTint(int i) {
        setTintList(ColorStateList.valueOf(i));
    }

    @Override // android.graphics.drawable.Drawable
    public void setTintList(ColorStateList colorStateList) {
        this.f769e.f772c = colorStateList;
        a(this.g.getState());
    }

    @Override // android.graphics.drawable.Drawable
    public void setTintMode(PorterDuff.Mode mode) {
        this.f769e.f773d = mode;
        a(this.g.getState());
    }

    @Override // android.graphics.drawable.Drawable
    public boolean setVisible(boolean z, boolean z2) {
        return super.setVisible(z, z2) || this.g.setVisible(z, z2);
    }

    @Override // android.graphics.drawable.Drawable.Callback
    public void unscheduleDrawable(Drawable drawable, Runnable runnable) {
        unscheduleSelf(runnable);
    }

    public c(Drawable drawable) {
        this.f769e = b();
        a(drawable);
    }

    public final void a(Drawable drawable) {
        Drawable drawable2 = this.g;
        if (drawable2 != null) {
            drawable2.setCallback(null);
        }
        this.g = drawable;
        if (drawable != null) {
            drawable.setCallback(this);
            boolean isVisible = drawable.isVisible();
            if (!super.setVisible(isVisible, true)) {
                this.g.setVisible(isVisible, true);
            }
            setState(drawable.getState());
            setLevel(drawable.getLevel());
            setBounds(drawable.getBounds());
            a aVar = this.f769e;
            if (aVar != null) {
                aVar.f771b = drawable.getConstantState();
            }
        }
        invalidateSelf();
    }
}
