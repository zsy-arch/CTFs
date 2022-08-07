package c.a.f;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.TypedValue;
import c.e.c.a;

/* loaded from: classes.dex */
public class fa {

    /* renamed from: a  reason: collision with root package name */
    public static final ThreadLocal<TypedValue> f592a = new ThreadLocal<>();

    /* renamed from: b  reason: collision with root package name */
    public static final int[] f593b = {-16842910};

    /* renamed from: c  reason: collision with root package name */
    public static final int[] f594c = {16842908};

    /* renamed from: d  reason: collision with root package name */
    public static final int[] f595d = {16842919};

    /* renamed from: e  reason: collision with root package name */
    public static final int[] f596e = {16842912};
    public static final int[] f = new int[0];
    public static final int[] g = new int[1];

    static {
        new int[1][0] = 16843518;
        new int[1][0] = 16842913;
        int[] iArr = {-16842919, -16842908};
    }

    public static int a(Context context, int i) {
        ColorStateList c2 = c(context, i);
        if (c2 != null && c2.isStateful()) {
            return c2.getColorForState(f593b, c2.getDefaultColor());
        }
        TypedValue typedValue = f592a.get();
        if (typedValue == null) {
            typedValue = new TypedValue();
            f592a.set(typedValue);
        }
        context.getTheme().resolveAttribute(16842803, typedValue, true);
        float f2 = typedValue.getFloat();
        int b2 = b(context, i);
        return a.b(b2, Math.round(Color.alpha(b2) * f2));
    }

    public static int b(Context context, int i) {
        int[] iArr = g;
        iArr[0] = i;
        ka a2 = ka.a(context, (AttributeSet) null, iArr);
        try {
            return a2.a(0, 0);
        } finally {
            a2.f605b.recycle();
        }
    }

    public static ColorStateList c(Context context, int i) {
        int[] iArr = g;
        iArr[0] = i;
        ka a2 = ka.a(context, (AttributeSet) null, iArr);
        try {
            return a2.a(0);
        } finally {
            a2.f605b.recycle();
        }
    }
}
