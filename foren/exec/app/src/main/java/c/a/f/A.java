package c.a.f;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.text.method.PasswordTransformationMethod;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.widget.TextView;
import c.a.a.C;
import c.a.j;
import c.e.i.b;
import e.a.a.a.a;
import java.lang.ref.WeakReference;
import java.util.Arrays;

/* loaded from: classes.dex */
public class A {

    /* renamed from: a */
    public final TextView f516a;

    /* renamed from: b */
    public ia f517b;

    /* renamed from: c */
    public ia f518c;

    /* renamed from: d */
    public ia f519d;

    /* renamed from: e */
    public ia f520e;
    public ia f;
    public ia g;
    public final B h;
    public int i = 0;
    public Typeface j;
    public boolean k;

    public A(TextView textView) {
        this.f516a = textView;
        this.h = new B(this.f516a);
    }

    @SuppressLint({"NewApi"})
    public void a(AttributeSet attributeSet, int i) {
        ColorStateList colorStateList;
        boolean z;
        boolean z2;
        int resourceId;
        Context context = this.f516a.getContext();
        C0049q a2 = C0049q.a();
        ka a3 = ka.a(context, attributeSet, j.AppCompatTextHelper, i, 0);
        int f = a3.f(j.AppCompatTextHelper_android_textAppearance, -1);
        if (a3.f(j.AppCompatTextHelper_android_drawableLeft)) {
            this.f517b = a(context, a2, a3.f(j.AppCompatTextHelper_android_drawableLeft, 0));
        }
        if (a3.f(j.AppCompatTextHelper_android_drawableTop)) {
            this.f518c = a(context, a2, a3.f(j.AppCompatTextHelper_android_drawableTop, 0));
        }
        if (a3.f(j.AppCompatTextHelper_android_drawableRight)) {
            this.f519d = a(context, a2, a3.f(j.AppCompatTextHelper_android_drawableRight, 0));
        }
        if (a3.f(j.AppCompatTextHelper_android_drawableBottom)) {
            this.f520e = a(context, a2, a3.f(j.AppCompatTextHelper_android_drawableBottom, 0));
        }
        int i2 = Build.VERSION.SDK_INT;
        if (a3.f(j.AppCompatTextHelper_android_drawableStart)) {
            this.f = a(context, a2, a3.f(j.AppCompatTextHelper_android_drawableStart, 0));
        }
        if (a3.f(j.AppCompatTextHelper_android_drawableEnd)) {
            this.g = a(context, a2, a3.f(j.AppCompatTextHelper_android_drawableEnd, 0));
        }
        a3.f605b.recycle();
        boolean z3 = this.f516a.getTransformationMethod() instanceof PasswordTransformationMethod;
        ColorStateList colorStateList2 = null;
        if (f != -1) {
            ka a4 = ka.a(context, f, j.TextAppearance);
            if (z3 || !a4.f(j.TextAppearance_textAllCaps)) {
                z2 = false;
                z = false;
            } else {
                z = a4.a(j.TextAppearance_textAllCaps, false);
                z2 = true;
            }
            a(context, a4);
            if (Build.VERSION.SDK_INT < 23) {
                colorStateList2 = a4.f(j.TextAppearance_android_textColor) ? a4.a(j.TextAppearance_android_textColor) : null;
                colorStateList = a4.f(j.TextAppearance_android_textColorHint) ? a4.a(j.TextAppearance_android_textColorHint) : null;
                if (a4.f(j.TextAppearance_android_textColorLink)) {
                    colorStateList2 = a4.a(j.TextAppearance_android_textColorLink);
                }
            } else {
                colorStateList2 = null;
                colorStateList = null;
            }
            a4.f605b.recycle();
        } else {
            colorStateList2 = null;
            colorStateList = null;
            z2 = false;
            z = false;
        }
        ka a5 = ka.a(context, attributeSet, j.TextAppearance, i, 0);
        if (!z3 && a5.f(j.TextAppearance_textAllCaps)) {
            z = a5.a(j.TextAppearance_textAllCaps, false);
            z2 = true;
        }
        if (Build.VERSION.SDK_INT < 23) {
            if (a5.f(j.TextAppearance_android_textColor)) {
                colorStateList2 = a5.a(j.TextAppearance_android_textColor);
            }
            if (a5.f(j.TextAppearance_android_textColorHint)) {
                colorStateList = a5.a(j.TextAppearance_android_textColorHint);
            }
            if (a5.f(j.TextAppearance_android_textColorLink)) {
                colorStateList2 = a5.a(j.TextAppearance_android_textColorLink);
            }
        }
        if (Build.VERSION.SDK_INT >= 28 && a5.f(j.TextAppearance_android_textSize) && a5.c(j.TextAppearance_android_textSize, -1) == 0) {
            this.f516a.setTextSize(0, 0.0f);
        }
        a(context, a5);
        a5.f605b.recycle();
        if (colorStateList2 != null) {
            this.f516a.setTextColor(colorStateList2);
        }
        if (colorStateList != null) {
            this.f516a.setHintTextColor(colorStateList);
        }
        if (colorStateList2 != null) {
            this.f516a.setLinkTextColor(colorStateList2);
        }
        if (!z3 && z2) {
            this.f516a.setAllCaps(z);
        }
        Typeface typeface = this.j;
        if (typeface != null) {
            this.f516a.setTypeface(typeface, this.i);
        }
        B b2 = this.h;
        TypedArray obtainStyledAttributes = b2.l.obtainStyledAttributes(attributeSet, j.AppCompatTextView, i, 0);
        if (obtainStyledAttributes.hasValue(j.AppCompatTextView_autoSizeTextType)) {
            b2.f523c = obtainStyledAttributes.getInt(j.AppCompatTextView_autoSizeTextType, 0);
        }
        float dimension = obtainStyledAttributes.hasValue(j.AppCompatTextView_autoSizeStepGranularity) ? obtainStyledAttributes.getDimension(j.AppCompatTextView_autoSizeStepGranularity, -1.0f) : -1.0f;
        float dimension2 = obtainStyledAttributes.hasValue(j.AppCompatTextView_autoSizeMinTextSize) ? obtainStyledAttributes.getDimension(j.AppCompatTextView_autoSizeMinTextSize, -1.0f) : -1.0f;
        float dimension3 = obtainStyledAttributes.hasValue(j.AppCompatTextView_autoSizeMaxTextSize) ? obtainStyledAttributes.getDimension(j.AppCompatTextView_autoSizeMaxTextSize, -1.0f) : -1.0f;
        if (obtainStyledAttributes.hasValue(j.AppCompatTextView_autoSizePresetSizes) && (resourceId = obtainStyledAttributes.getResourceId(j.AppCompatTextView_autoSizePresetSizes, 0)) > 0) {
            TypedArray obtainTypedArray = obtainStyledAttributes.getResources().obtainTypedArray(resourceId);
            int length = obtainTypedArray.length();
            int[] iArr = new int[length];
            if (length > 0) {
                for (int i3 = 0; i3 < length; i3++) {
                    iArr[i3] = obtainTypedArray.getDimensionPixelSize(i3, -1);
                }
                b2.h = b2.a(iArr);
                b2.h();
            }
            obtainTypedArray.recycle();
        }
        obtainStyledAttributes.recycle();
        if (!b2.i()) {
            b2.f523c = 0;
        } else if (b2.f523c == 1) {
            if (!b2.i) {
                DisplayMetrics displayMetrics = b2.l.getResources().getDisplayMetrics();
                if (dimension2 == -1.0f) {
                    dimension2 = TypedValue.applyDimension(2, 12.0f, displayMetrics);
                }
                if (dimension3 == -1.0f) {
                    dimension3 = TypedValue.applyDimension(2, 112.0f, displayMetrics);
                }
                if (dimension == -1.0f) {
                    dimension = 1.0f;
                }
                b2.a(dimension2, dimension3, dimension);
            }
            b2.g();
        }
        if (b.f888a) {
            B b3 = this.h;
            if (b3.f523c != 0) {
                int[] iArr2 = b3.h;
                if (iArr2.length > 0) {
                    if (this.f516a.getAutoSizeStepGranularity() != -1.0f) {
                        this.f516a.setAutoSizeTextTypeUniformWithConfiguration(Math.round(this.h.f), Math.round(this.h.g), Math.round(this.h.f525e), 0);
                    } else {
                        this.f516a.setAutoSizeTextTypeUniformWithPresetSizes(iArr2, 0);
                    }
                }
            }
        }
        ka a6 = ka.a(context, attributeSet, j.AppCompatTextView);
        int c2 = a6.c(j.AppCompatTextView_firstBaselineToTopHeight, -1);
        int c3 = a6.c(j.AppCompatTextView_lastBaselineToBottomHeight, -1);
        int c4 = a6.c(j.AppCompatTextView_lineHeight, -1);
        a6.f605b.recycle();
        if (c2 != -1) {
            C.a(this.f516a, c2);
        }
        if (c3 != -1) {
            C.b(this.f516a, c3);
        }
        if (c4 != -1) {
            C.c(this.f516a, c4);
        }
    }

    public boolean b() {
        B b2 = this.h;
        return b2.i() && b2.f523c != 0;
    }

    public final void a(Context context, ka kaVar) {
        String string;
        this.i = kaVar.d(j.TextAppearance_android_textStyle, this.i);
        boolean z = true;
        if (kaVar.f(j.TextAppearance_android_fontFamily) || kaVar.f(j.TextAppearance_fontFamily)) {
            this.j = null;
            int i = kaVar.f(j.TextAppearance_fontFamily) ? j.TextAppearance_fontFamily : j.TextAppearance_android_fontFamily;
            if (!context.isRestricted()) {
                try {
                    this.j = kaVar.a(i, this.i, new C0057z(this, new WeakReference(this.f516a)));
                    if (this.j != null) {
                        z = false;
                    }
                    this.k = z;
                } catch (Resources.NotFoundException | UnsupportedOperationException unused) {
                }
            }
            if (this.j == null && (string = kaVar.f605b.getString(i)) != null) {
                this.j = Typeface.create(string, this.i);
            }
        } else if (kaVar.f(j.TextAppearance_android_typeface)) {
            this.k = false;
            int d2 = kaVar.d(j.TextAppearance_android_typeface, 1);
            if (d2 == 1) {
                this.j = Typeface.SANS_SERIF;
            } else if (d2 == 2) {
                this.j = Typeface.SERIF;
            } else if (d2 == 3) {
                this.j = Typeface.MONOSPACE;
            }
        }
    }

    public void a(Context context, int i) {
        ColorStateList a2;
        ka a3 = ka.a(context, i, j.TextAppearance);
        if (a3.f(j.TextAppearance_textAllCaps)) {
            this.f516a.setAllCaps(a3.a(j.TextAppearance_textAllCaps, false));
        }
        if (Build.VERSION.SDK_INT < 23 && a3.f(j.TextAppearance_android_textColor) && (a2 = a3.a(j.TextAppearance_android_textColor)) != null) {
            this.f516a.setTextColor(a2);
        }
        if (a3.f(j.TextAppearance_android_textSize) && a3.c(j.TextAppearance_android_textSize, -1) == 0) {
            this.f516a.setTextSize(0, 0.0f);
        }
        a(context, a3);
        a3.f605b.recycle();
        Typeface typeface = this.j;
        if (typeface != null) {
            this.f516a.setTypeface(typeface, this.i);
        }
    }

    public void a() {
        if (!(this.f517b == null && this.f518c == null && this.f519d == null && this.f520e == null)) {
            Drawable[] compoundDrawables = this.f516a.getCompoundDrawables();
            a(compoundDrawables[0], this.f517b);
            a(compoundDrawables[1], this.f518c);
            a(compoundDrawables[2], this.f519d);
            a(compoundDrawables[3], this.f520e);
        }
        int i = Build.VERSION.SDK_INT;
        if (this.f != null || this.g != null) {
            Drawable[] compoundDrawablesRelative = this.f516a.getCompoundDrawablesRelative();
            a(compoundDrawablesRelative[0], this.f);
            a(compoundDrawablesRelative[2], this.g);
        }
    }

    public final void a(Drawable drawable, ia iaVar) {
        if (drawable != null && iaVar != null) {
            C0049q.a(drawable, iaVar, this.f516a.getDrawableState());
        }
    }

    public static ia a(Context context, C0049q qVar, int i) {
        ColorStateList d2 = qVar.d(context, i);
        if (d2 == null) {
            return null;
        }
        ia iaVar = new ia();
        iaVar.f601d = true;
        iaVar.f598a = d2;
        return iaVar;
    }

    public void a(boolean z, int i, int i2, int i3, int i4) {
        if (!b.f888a) {
            this.h.a();
        }
    }

    public void a(int i, float f) {
        if (!b.f888a && !b()) {
            this.h.a(i, f);
        }
    }

    public void a(int i) {
        B b2 = this.h;
        if (!b2.i()) {
            return;
        }
        if (i == 0) {
            b2.f523c = 0;
            b2.f = -1.0f;
            b2.g = -1.0f;
            b2.f525e = -1.0f;
            b2.h = new int[0];
            b2.f524d = false;
        } else if (i == 1) {
            DisplayMetrics displayMetrics = b2.l.getResources().getDisplayMetrics();
            b2.a(TypedValue.applyDimension(2, 12.0f, displayMetrics), TypedValue.applyDimension(2, 112.0f, displayMetrics), 1.0f);
            if (b2.g()) {
                b2.a();
            }
        } else {
            throw new IllegalArgumentException("Unknown auto-size text type: " + i);
        }
    }

    public void a(int i, int i2, int i3, int i4) {
        B b2 = this.h;
        if (b2.i()) {
            DisplayMetrics displayMetrics = b2.l.getResources().getDisplayMetrics();
            b2.a(TypedValue.applyDimension(i4, i, displayMetrics), TypedValue.applyDimension(i4, i2, displayMetrics), TypedValue.applyDimension(i4, i3, displayMetrics));
            if (b2.g()) {
                b2.a();
            }
        }
    }

    public void a(int[] iArr, int i) {
        B b2 = this.h;
        if (b2.i()) {
            int length = iArr.length;
            if (length > 0) {
                int[] iArr2 = new int[length];
                if (i == 0) {
                    iArr2 = Arrays.copyOf(iArr, length);
                } else {
                    DisplayMetrics displayMetrics = b2.l.getResources().getDisplayMetrics();
                    for (int i2 = 0; i2 < length; i2++) {
                        iArr2[i2] = Math.round(TypedValue.applyDimension(i, iArr[i2], displayMetrics));
                    }
                }
                b2.h = b2.a(iArr2);
                if (!b2.h()) {
                    StringBuilder a2 = a.a("None of the preset sizes is valid: ");
                    a2.append(Arrays.toString(iArr));
                    throw new IllegalArgumentException(a2.toString());
                }
            } else {
                b2.i = false;
            }
            if (b2.g()) {
                b2.a();
            }
        }
    }
}
