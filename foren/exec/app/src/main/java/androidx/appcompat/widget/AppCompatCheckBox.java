package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.CheckBox;
import c.a.a;
import c.a.f.A;
import c.a.f.C0048p;
import c.a.f.ha;
import c.e.i.f;

/* loaded from: classes.dex */
public class AppCompatCheckBox extends CheckBox implements f {

    /* renamed from: a  reason: collision with root package name */
    public final C0048p f102a;

    /* renamed from: b  reason: collision with root package name */
    public final A f103b;

    public AppCompatCheckBox(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, a.checkboxStyle);
    }

    @Override // android.widget.CompoundButton, android.widget.TextView
    public int getCompoundPaddingLeft() {
        int compoundPaddingLeft = super.getCompoundPaddingLeft();
        C0048p pVar = this.f102a;
        if (pVar != null) {
            pVar.a(compoundPaddingLeft);
        }
        return compoundPaddingLeft;
    }

    public ColorStateList getSupportButtonTintList() {
        C0048p pVar = this.f102a;
        if (pVar != null) {
            return pVar.f617b;
        }
        return null;
    }

    public PorterDuff.Mode getSupportButtonTintMode() {
        C0048p pVar = this.f102a;
        if (pVar != null) {
            return pVar.f618c;
        }
        return null;
    }

    @Override // android.widget.CompoundButton
    public void setButtonDrawable(Drawable drawable) {
        super.setButtonDrawable(drawable);
        C0048p pVar = this.f102a;
        if (pVar == null) {
            return;
        }
        if (pVar.f) {
            pVar.f = false;
            return;
        }
        pVar.f = true;
        pVar.a();
    }

    public void setSupportButtonTintList(ColorStateList colorStateList) {
        C0048p pVar = this.f102a;
        if (pVar != null) {
            pVar.f617b = colorStateList;
            pVar.f619d = true;
            pVar.a();
        }
    }

    public void setSupportButtonTintMode(PorterDuff.Mode mode) {
        C0048p pVar = this.f102a;
        if (pVar != null) {
            pVar.f618c = mode;
            pVar.f620e = true;
            pVar.a();
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AppCompatCheckBox(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        ha.a(context);
        this.f102a = new C0048p(this);
        this.f102a.a(attributeSet, i);
        this.f103b = new A(this);
        this.f103b.a(attributeSet, i);
    }

    @Override // android.widget.CompoundButton
    public void setButtonDrawable(int i) {
        setButtonDrawable(c.a.b.a.a.c(getContext(), i));
    }
}
