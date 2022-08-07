package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.RippleDrawable;
import android.net.Uri;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.ImageButton;
import c.a.a;
import c.a.f.C0047o;
import c.a.f.ha;
import c.a.f.ia;
import c.a.f.r;
import c.e.h.l;
import c.e.i.g;

/* loaded from: classes.dex */
public class AppCompatImageButton extends ImageButton implements l, g {

    /* renamed from: a  reason: collision with root package name */
    public final C0047o f108a;

    /* renamed from: b  reason: collision with root package name */
    public final r f109b;

    public AppCompatImageButton(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, a.imageButtonStyle);
    }

    @Override // android.widget.ImageView, android.view.View
    public void drawableStateChanged() {
        super.drawableStateChanged();
        C0047o oVar = this.f108a;
        if (oVar != null) {
            oVar.a();
        }
        r rVar = this.f109b;
        if (rVar != null) {
            rVar.a();
        }
    }

    public ColorStateList getSupportBackgroundTintList() {
        C0047o oVar = this.f108a;
        if (oVar != null) {
            return oVar.b();
        }
        return null;
    }

    public PorterDuff.Mode getSupportBackgroundTintMode() {
        C0047o oVar = this.f108a;
        if (oVar != null) {
            return oVar.c();
        }
        return null;
    }

    public ColorStateList getSupportImageTintList() {
        ia iaVar;
        r rVar = this.f109b;
        if (rVar == null || (iaVar = rVar.f633c) == null) {
            return null;
        }
        return iaVar.f598a;
    }

    public PorterDuff.Mode getSupportImageTintMode() {
        ia iaVar;
        r rVar = this.f109b;
        if (rVar == null || (iaVar = rVar.f633c) == null) {
            return null;
        }
        return iaVar.f599b;
    }

    @Override // android.widget.ImageView, android.view.View
    public boolean hasOverlappingRendering() {
        Drawable background = this.f109b.f631a.getBackground();
        int i = Build.VERSION.SDK_INT;
        return (!(background instanceof RippleDrawable)) && super.hasOverlappingRendering();
    }

    @Override // android.view.View
    public void setBackgroundDrawable(Drawable drawable) {
        super.setBackgroundDrawable(drawable);
        C0047o oVar = this.f108a;
        if (oVar != null) {
            oVar.f613c = -1;
            oVar.a((ColorStateList) null);
            oVar.a();
        }
    }

    @Override // android.view.View
    public void setBackgroundResource(int i) {
        super.setBackgroundResource(i);
        C0047o oVar = this.f108a;
        if (oVar != null) {
            oVar.a(i);
        }
    }

    @Override // android.widget.ImageView
    public void setImageBitmap(Bitmap bitmap) {
        super.setImageBitmap(bitmap);
        r rVar = this.f109b;
        if (rVar != null) {
            rVar.a();
        }
    }

    @Override // android.widget.ImageView
    public void setImageDrawable(Drawable drawable) {
        super.setImageDrawable(drawable);
        r rVar = this.f109b;
        if (rVar != null) {
            rVar.a();
        }
    }

    @Override // android.widget.ImageView
    public void setImageResource(int i) {
        this.f109b.a(i);
    }

    @Override // android.widget.ImageView
    public void setImageURI(Uri uri) {
        super.setImageURI(uri);
        r rVar = this.f109b;
        if (rVar != null) {
            rVar.a();
        }
    }

    public void setSupportBackgroundTintList(ColorStateList colorStateList) {
        C0047o oVar = this.f108a;
        if (oVar != null) {
            oVar.b(colorStateList);
        }
    }

    public void setSupportBackgroundTintMode(PorterDuff.Mode mode) {
        C0047o oVar = this.f108a;
        if (oVar != null) {
            oVar.a(mode);
        }
    }

    public void setSupportImageTintList(ColorStateList colorStateList) {
        r rVar = this.f109b;
        if (rVar != null) {
            rVar.a(colorStateList);
        }
    }

    public void setSupportImageTintMode(PorterDuff.Mode mode) {
        r rVar = this.f109b;
        if (rVar != null) {
            rVar.a(mode);
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AppCompatImageButton(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        ha.a(context);
        this.f108a = new C0047o(this);
        this.f108a.a(attributeSet, i);
        this.f109b = new r(this);
        this.f109b.a(attributeSet, i);
    }
}
