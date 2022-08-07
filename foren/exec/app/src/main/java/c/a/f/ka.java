package c.a.f;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.TypedValue;
import c.a.b.a.a;

/* loaded from: classes.dex */
public class ka {

    /* renamed from: a */
    public final Context f604a;

    /* renamed from: b */
    public final TypedArray f605b;

    /* renamed from: c */
    public TypedValue f606c;

    public ka(Context context, TypedArray typedArray) {
        this.f604a = context;
        this.f605b = typedArray;
    }

    public static ka a(Context context, AttributeSet attributeSet, int[] iArr) {
        return new ka(context, context.obtainStyledAttributes(attributeSet, iArr));
    }

    public Drawable b(int i) {
        int resourceId;
        if (!this.f605b.hasValue(i) || (resourceId = this.f605b.getResourceId(i, 0)) == 0) {
            return this.f605b.getDrawable(i);
        }
        return a.c(this.f604a, resourceId);
    }

    public Drawable c(int i) {
        int resourceId;
        if (!this.f605b.hasValue(i) || (resourceId = this.f605b.getResourceId(i, 0)) == 0) {
            return null;
        }
        return C0049q.a().a(this.f604a, resourceId, true);
    }

    public String d(int i) {
        return this.f605b.getString(i);
    }

    public CharSequence e(int i) {
        return this.f605b.getText(i);
    }

    public int f(int i, int i2) {
        return this.f605b.getResourceId(i, i2);
    }

    public static ka a(Context context, AttributeSet attributeSet, int[] iArr, int i, int i2) {
        return new ka(context, context.obtainStyledAttributes(attributeSet, iArr, i, i2));
    }

    public int d(int i, int i2) {
        return this.f605b.getInt(i, i2);
    }

    public int e(int i, int i2) {
        return this.f605b.getLayoutDimension(i, i2);
    }

    public boolean f(int i) {
        return this.f605b.hasValue(i);
    }

    public static ka a(Context context, int i, int[] iArr) {
        return new ka(context, context.obtainStyledAttributes(i, iArr));
    }

    public int c(int i, int i2) {
        return this.f605b.getDimensionPixelSize(i, i2);
    }

    /* JADX WARN: Removed duplicated region for block: B:37:0x00b1  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x00b6 A[ADDED_TO_REGION] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public android.graphics.Typeface a(int r12, int r13, c.e.b.a.j r14) {
        /*
            Method dump skipped, instructions count: 257
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: c.a.f.ka.a(int, int, c.e.b.a.j):android.graphics.Typeface");
    }

    public int b(int i, int i2) {
        return this.f605b.getDimensionPixelOffset(i, i2);
    }

    public boolean a(int i, boolean z) {
        return this.f605b.getBoolean(i, z);
    }

    public int a(int i, int i2) {
        return this.f605b.getColor(i, i2);
    }

    public ColorStateList a(int i) {
        int resourceId;
        ColorStateList b2;
        return (!this.f605b.hasValue(i) || (resourceId = this.f605b.getResourceId(i, 0)) == 0 || (b2 = a.b(this.f604a, resourceId)) == null) ? this.f605b.getColorStateList(i) : b2;
    }
}
