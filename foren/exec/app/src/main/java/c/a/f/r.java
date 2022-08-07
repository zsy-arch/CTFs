package c.a.f;

import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.ImageView;
import c.a.b.a.a;
import c.a.j;

/* loaded from: classes.dex */
public class r {

    /* renamed from: a  reason: collision with root package name */
    public final ImageView f631a;

    /* renamed from: b  reason: collision with root package name */
    public ia f632b;

    /* renamed from: c  reason: collision with root package name */
    public ia f633c;

    /* renamed from: d  reason: collision with root package name */
    public ia f634d;

    public r(ImageView imageView) {
        this.f631a = imageView;
    }

    public void a(AttributeSet attributeSet, int i) {
        int f;
        ka a2 = ka.a(this.f631a.getContext(), attributeSet, j.AppCompatImageView, i, 0);
        try {
            Drawable drawable = this.f631a.getDrawable();
            if (!(drawable != null || (f = a2.f(j.AppCompatImageView_srcCompat, -1)) == -1 || (drawable = a.c(this.f631a.getContext(), f)) == null)) {
                this.f631a.setImageDrawable(drawable);
            }
            if (drawable != null) {
                E.b(drawable);
            }
            boolean z = true;
            if (a2.f(j.AppCompatImageView_tint)) {
                ImageView imageView = this.f631a;
                ColorStateList a3 = a2.a(j.AppCompatImageView_tint);
                int i2 = Build.VERSION.SDK_INT;
                imageView.setImageTintList(a3);
                if (Build.VERSION.SDK_INT == 21) {
                    Drawable drawable2 = imageView.getDrawable();
                    boolean z2 = (imageView.getImageTintList() == null || imageView.getImageTintMode() == null) ? false : true;
                    if (drawable2 != null && z2) {
                        if (drawable2.isStateful()) {
                            drawable2.setState(imageView.getDrawableState());
                        }
                        imageView.setImageDrawable(drawable2);
                    }
                }
            }
            if (a2.f(j.AppCompatImageView_tintMode)) {
                ImageView imageView2 = this.f631a;
                PorterDuff.Mode a4 = E.a(a2.d(j.AppCompatImageView_tintMode, -1), null);
                int i3 = Build.VERSION.SDK_INT;
                imageView2.setImageTintMode(a4);
                if (Build.VERSION.SDK_INT == 21) {
                    Drawable drawable3 = imageView2.getDrawable();
                    if (imageView2.getImageTintList() == null || imageView2.getImageTintMode() == null) {
                        z = false;
                    }
                    if (drawable3 != null && z) {
                        if (drawable3.isStateful()) {
                            drawable3.setState(imageView2.getDrawableState());
                        }
                        imageView2.setImageDrawable(drawable3);
                    }
                }
            }
            a2.f605b.recycle();
        } catch (Throwable th) {
            a2.f605b.recycle();
            throw th;
        }
    }

    public void a(int i) {
        if (i != 0) {
            Drawable c2 = a.c(this.f631a.getContext(), i);
            if (c2 != null) {
                E.b(c2);
            }
            this.f631a.setImageDrawable(c2);
        } else {
            this.f631a.setImageDrawable(null);
        }
        a();
    }

    public void a(ColorStateList colorStateList) {
        if (this.f633c == null) {
            this.f633c = new ia();
        }
        ia iaVar = this.f633c;
        iaVar.f598a = colorStateList;
        iaVar.f601d = true;
        a();
    }

    public void a(PorterDuff.Mode mode) {
        if (this.f633c == null) {
            this.f633c = new ia();
        }
        ia iaVar = this.f633c;
        iaVar.f599b = mode;
        iaVar.f600c = true;
        a();
    }

    public void a() {
        Drawable drawable = this.f631a.getDrawable();
        if (drawable != null) {
            E.b(drawable);
        }
        if (drawable != null) {
            int i = Build.VERSION.SDK_INT;
            boolean z = true;
            if (i <= 21 ? i == 21 : this.f632b != null) {
                if (this.f634d == null) {
                    this.f634d = new ia();
                }
                ia iaVar = this.f634d;
                iaVar.a();
                ImageView imageView = this.f631a;
                int i2 = Build.VERSION.SDK_INT;
                ColorStateList imageTintList = imageView.getImageTintList();
                if (imageTintList != null) {
                    iaVar.f601d = true;
                    iaVar.f598a = imageTintList;
                }
                ImageView imageView2 = this.f631a;
                int i3 = Build.VERSION.SDK_INT;
                PorterDuff.Mode imageTintMode = imageView2.getImageTintMode();
                if (imageTintMode != null) {
                    iaVar.f600c = true;
                    iaVar.f599b = imageTintMode;
                }
                if (iaVar.f601d || iaVar.f600c) {
                    C0049q.a(drawable, iaVar, this.f631a.getDrawableState());
                } else {
                    z = false;
                }
                if (z) {
                    return;
                }
            }
            ia iaVar2 = this.f633c;
            if (iaVar2 != null) {
                C0049q.a(drawable, iaVar2, this.f631a.getDrawableState());
                return;
            }
            ia iaVar3 = this.f632b;
            if (iaVar3 != null) {
                C0049q.a(drawable, iaVar3, this.f631a.getDrawableState());
            }
        }
    }
}
