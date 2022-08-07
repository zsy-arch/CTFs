package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.RadioButton;
import c.a.a;
import c.a.f.A;
import c.a.f.C0048p;
import c.a.f.ha;
import c.e.i.f;

/* loaded from: classes.dex */
public class AppCompatRadioButton extends RadioButton implements f {

    /* renamed from: a  reason: collision with root package name */
    public final C0048p f115a;

    /* renamed from: b  reason: collision with root package name */
    public final A f116b;

    public AppCompatRadioButton(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, a.radioButtonStyle);
    }

    @Override // android.widget.CompoundButton, android.widget.TextView
    public int getCompoundPaddingLeft() {
        int compoundPaddingLeft = super.getCompoundPaddingLeft();
        C0048p pVar = this.f115a;
        if (pVar != null) {
            pVar.a(compoundPaddingLeft);
        }
        return compoundPaddingLeft;
    }

    public ColorStateList getSupportButtonTintList() {
        C0048p pVar = this.f115a;
        if (pVar != null) {
            return pVar.f617b;
        }
        return null;
    }

    public PorterDuff.Mode getSupportButtonTintMode() {
        C0048p pVar = this.f115a;
        if (pVar != null) {
            return pVar.f618c;
        }
        return null;
    }

    @Override // android.widget.CompoundButton
    public void setButtonDrawable(Drawable drawable) {
        super.setButtonDrawable(drawable);
        C0048p pVar = this.f115a;
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
        C0048p pVar = this.f115a;
        if (pVar != null) {
            pVar.f617b = colorStateList;
            pVar.f619d = true;
            pVar.a();
        }
    }

    public void setSupportButtonTintMode(PorterDuff.Mode mode) {
        C0048p pVar = this.f115a;
        if (pVar != null) {
            pVar.f618c = mode;
            pVar.f620e = true;
            pVar.a();
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AppCompatRadioButton(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        ha.a(context);
        this.f115a = new C0048p(this);
        this.f115a.a(attributeSet, i);
        this.f116b = new A(this);
        this.f116b.a(attributeSet, i);
    }

    @Override // android.widget.CompoundButton
    public void setButtonDrawable(int i) {
        setButtonDrawable(c.a.b.a.a.c(getContext(), i));
    }
}
