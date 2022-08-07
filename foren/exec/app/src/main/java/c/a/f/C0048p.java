package c.a.f;

import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.CompoundButton;
import c.a.a.C;
import c.a.b.a.a;
import c.a.j;

/* renamed from: c.a.f.p  reason: case insensitive filesystem */
/* loaded from: classes.dex */
public class C0048p {

    /* renamed from: a  reason: collision with root package name */
    public final CompoundButton f616a;

    /* renamed from: b  reason: collision with root package name */
    public ColorStateList f617b = null;

    /* renamed from: c  reason: collision with root package name */
    public PorterDuff.Mode f618c = null;

    /* renamed from: d  reason: collision with root package name */
    public boolean f619d = false;

    /* renamed from: e  reason: collision with root package name */
    public boolean f620e = false;
    public boolean f;

    public C0048p(CompoundButton compoundButton) {
        this.f616a = compoundButton;
    }

    public void a(AttributeSet attributeSet, int i) {
        int resourceId;
        TypedArray obtainStyledAttributes = this.f616a.getContext().obtainStyledAttributes(attributeSet, j.CompoundButton, i, 0);
        try {
            if (obtainStyledAttributes.hasValue(j.CompoundButton_android_button) && (resourceId = obtainStyledAttributes.getResourceId(j.CompoundButton_android_button, 0)) != 0) {
                this.f616a.setButtonDrawable(a.c(this.f616a.getContext(), resourceId));
            }
            if (obtainStyledAttributes.hasValue(j.CompoundButton_buttonTint)) {
                CompoundButton compoundButton = this.f616a;
                ColorStateList colorStateList = obtainStyledAttributes.getColorStateList(j.CompoundButton_buttonTint);
                int i2 = Build.VERSION.SDK_INT;
                compoundButton.setButtonTintList(colorStateList);
            }
            if (obtainStyledAttributes.hasValue(j.CompoundButton_buttonTintMode)) {
                CompoundButton compoundButton2 = this.f616a;
                PorterDuff.Mode a2 = E.a(obtainStyledAttributes.getInt(j.CompoundButton_buttonTintMode, -1), null);
                int i3 = Build.VERSION.SDK_INT;
                compoundButton2.setButtonTintMode(a2);
            }
        } finally {
            obtainStyledAttributes.recycle();
        }
    }

    public void a() {
        Drawable a2 = C.a(this.f616a);
        if (a2 == null) {
            return;
        }
        if (this.f619d || this.f620e) {
            Drawable mutate = C.b(a2).mutate();
            if (this.f619d) {
                ColorStateList colorStateList = this.f617b;
                int i = Build.VERSION.SDK_INT;
                mutate.setTintList(colorStateList);
            }
            if (this.f620e) {
                PorterDuff.Mode mode = this.f618c;
                int i2 = Build.VERSION.SDK_INT;
                mutate.setTintMode(mode);
            }
            if (mutate.isStateful()) {
                mutate.setState(this.f616a.getDrawableState());
            }
            this.f616a.setButtonDrawable(mutate);
        }
    }

    public int a(int i) {
        int i2 = Build.VERSION.SDK_INT;
        return i;
    }
}
