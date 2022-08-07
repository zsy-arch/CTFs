package c.e.b.a;

import android.content.res.ColorStateList;
import android.graphics.Shader;

/* loaded from: classes.dex */
public final class a {

    /* renamed from: a  reason: collision with root package name */
    public final Shader f747a;

    /* renamed from: b  reason: collision with root package name */
    public final ColorStateList f748b;

    /* renamed from: c  reason: collision with root package name */
    public int f749c;

    public a(Shader shader, ColorStateList colorStateList, int i) {
        this.f747a = shader;
        this.f748b = colorStateList;
        this.f749c = i;
    }

    public Shader a() {
        return this.f747a;
    }

    public boolean b() {
        return this.f747a != null;
    }

    public boolean c() {
        ColorStateList colorStateList;
        return this.f747a == null && (colorStateList = this.f748b) != null && colorStateList.isStateful();
    }

    public boolean d() {
        return b() || this.f749c != 0;
    }

    public boolean a(int[] iArr) {
        if (c()) {
            ColorStateList colorStateList = this.f748b;
            int colorForState = colorStateList.getColorForState(iArr, colorStateList.getDefaultColor());
            if (colorForState != this.f749c) {
                this.f749c = colorForState;
                return true;
            }
        }
        return false;
    }

    /* JADX WARN: Code restructure failed: missing block: B:39:0x0157, code lost:
        throw new org.xmlpull.v1.XmlPullParserException(r2.getPositionDescription() + ": <item> tag requires a 'color' attribute and a 'offset' attribute!");
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static c.e.b.a.a a(android.content.res.Resources r26, int r27, android.content.res.Resources.Theme r28) {
        /*
            Method dump skipped, instructions count: 560
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: c.e.b.a.a.a(android.content.res.Resources, int, android.content.res.Resources$Theme):c.e.b.a.a");
    }
}
