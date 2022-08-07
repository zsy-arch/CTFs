package c.m.a.a;

import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.VectorDrawable;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Xml;
import c.a.a.C;
import com.tencent.smtt.sdk.WebView;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

/* loaded from: classes.dex */
public class i extends g {

    /* renamed from: b  reason: collision with root package name */
    public static final PorterDuff.Mode f1058b = PorterDuff.Mode.SRC_IN;

    /* renamed from: c  reason: collision with root package name */
    public g f1059c;

    /* renamed from: d  reason: collision with root package name */
    public PorterDuffColorFilter f1060d;

    /* renamed from: e  reason: collision with root package name */
    public ColorFilter f1061e;
    public boolean f;
    public boolean g;
    public final float[] h;
    public final Matrix i;
    public final Rect j;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class a extends e {
        public a() {
        }

        public void a(Resources resources, AttributeSet attributeSet, Resources.Theme theme, XmlPullParser xmlPullParser) {
            if (C.a(xmlPullParser, "pathData")) {
                TypedArray a2 = C.a(resources, theme, attributeSet, a.f1042d);
                String string = a2.getString(0);
                if (string != null) {
                    this.f1070b = string;
                }
                String string2 = a2.getString(1);
                if (string2 != null) {
                    this.f1069a = C.b(string2);
                }
                a2.recycle();
            }
        }

        @Override // c.m.a.a.i.e
        public boolean b() {
            return true;
        }

        public a(a aVar) {
            super(aVar);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static abstract class d {
        public d() {
        }

        public boolean a() {
            return false;
        }

        public boolean a(int[] iArr) {
            return false;
        }

        public /* synthetic */ d(h hVar) {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class g extends Drawable.ConstantState {

        /* renamed from: a  reason: collision with root package name */
        public int f1077a;

        /* renamed from: b  reason: collision with root package name */
        public f f1078b;

        /* renamed from: c  reason: collision with root package name */
        public ColorStateList f1079c;

        /* renamed from: d  reason: collision with root package name */
        public PorterDuff.Mode f1080d;

        /* renamed from: e  reason: collision with root package name */
        public boolean f1081e;
        public Bitmap f;
        public ColorStateList g;
        public PorterDuff.Mode h;
        public int i;
        public boolean j;
        public boolean k;
        public Paint l;

        public g(g gVar) {
            this.f1079c = null;
            this.f1080d = i.f1058b;
            if (gVar != null) {
                this.f1077a = gVar.f1077a;
                this.f1078b = new f(gVar.f1078b);
                Paint paint = gVar.f1078b.f;
                if (paint != null) {
                    this.f1078b.f = new Paint(paint);
                }
                Paint paint2 = gVar.f1078b.f1076e;
                if (paint2 != null) {
                    this.f1078b.f1076e = new Paint(paint2);
                }
                this.f1079c = gVar.f1079c;
                this.f1080d = gVar.f1080d;
                this.f1081e = gVar.f1081e;
            }
        }

        public void a(Canvas canvas, ColorFilter colorFilter, Rect rect) {
            canvas.drawBitmap(this.f, (Rect) null, rect, a(colorFilter));
        }

        public boolean b() {
            return this.f1078b.getRootAlpha() < 255;
        }

        public void c(int i, int i2) {
            this.f.eraseColor(0);
            this.f1078b.a(new Canvas(this.f), i, i2, null);
        }

        public void d() {
            this.g = this.f1079c;
            this.h = this.f1080d;
            this.i = this.f1078b.getRootAlpha();
            this.j = this.f1081e;
            this.k = false;
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public int getChangingConfigurations() {
            return this.f1077a;
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public Drawable newDrawable() {
            return new i(this);
        }

        public void b(int i, int i2) {
            if (this.f == null || !a(i, i2)) {
                this.f = Bitmap.createBitmap(i, i2, Bitmap.Config.ARGB_8888);
                this.k = true;
            }
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public Drawable newDrawable(Resources resources) {
            return new i(this);
        }

        public Paint a(ColorFilter colorFilter) {
            if (!b() && colorFilter == null) {
                return null;
            }
            if (this.l == null) {
                this.l = new Paint();
                this.l.setFilterBitmap(true);
            }
            this.l.setAlpha(this.f1078b.getRootAlpha());
            this.l.setColorFilter(colorFilter);
            return this.l;
        }

        public boolean c() {
            return this.f1078b.a();
        }

        public boolean a(int i, int i2) {
            return i == this.f.getWidth() && i2 == this.f.getHeight();
        }

        public boolean a() {
            return !this.k && this.g == this.f1079c && this.h == this.f1080d && this.j == this.f1081e && this.i == this.f1078b.getRootAlpha();
        }

        public g() {
            this.f1079c = null;
            this.f1080d = i.f1058b;
            this.f1078b = new f();
        }

        public boolean a(int[] iArr) {
            boolean a2 = this.f1078b.a(iArr);
            this.k |= a2;
            return a2;
        }
    }

    public i() {
        this.g = true;
        this.h = new float[9];
        this.i = new Matrix();
        this.j = new Rect();
        this.f1059c = new g();
    }

    public static i createFromXmlInner(Resources resources, XmlPullParser xmlPullParser, AttributeSet attributeSet, Resources.Theme theme) {
        i iVar = new i();
        iVar.inflate(resources, xmlPullParser, attributeSet, theme);
        return iVar;
    }

    public PorterDuffColorFilter a(PorterDuffColorFilter porterDuffColorFilter, ColorStateList colorStateList, PorterDuff.Mode mode) {
        if (colorStateList == null || mode == null) {
            return null;
        }
        return new PorterDuffColorFilter(colorStateList.getColorForState(getState(), 0), mode);
    }

    @Override // android.graphics.drawable.Drawable
    public boolean canApplyTheme() {
        Drawable drawable = this.f1057a;
        if (drawable == null) {
            return false;
        }
        int i = Build.VERSION.SDK_INT;
        drawable.canApplyTheme();
        return false;
    }

    /* JADX WARN: Code restructure failed: missing block: B:37:0x00d6, code lost:
        if (r6 == 1) goto L_0x00da;
     */
    @Override // android.graphics.drawable.Drawable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void draw(android.graphics.Canvas r12) {
        /*
            Method dump skipped, instructions count: 284
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: c.m.a.a.i.draw(android.graphics.Canvas):void");
    }

    @Override // android.graphics.drawable.Drawable
    public int getAlpha() {
        Drawable drawable = this.f1057a;
        if (drawable == null) {
            return this.f1059c.f1078b.getRootAlpha();
        }
        int i = Build.VERSION.SDK_INT;
        return drawable.getAlpha();
    }

    @Override // android.graphics.drawable.Drawable
    public int getChangingConfigurations() {
        Drawable drawable = this.f1057a;
        if (drawable != null) {
            return drawable.getChangingConfigurations();
        }
        return super.getChangingConfigurations() | this.f1059c.getChangingConfigurations();
    }

    @Override // android.graphics.drawable.Drawable
    public Drawable.ConstantState getConstantState() {
        Drawable drawable = this.f1057a;
        if (drawable != null && Build.VERSION.SDK_INT >= 24) {
            return new h(drawable.getConstantState());
        }
        this.f1059c.f1077a = getChangingConfigurations();
        return this.f1059c;
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicHeight() {
        Drawable drawable = this.f1057a;
        if (drawable != null) {
            return drawable.getIntrinsicHeight();
        }
        return (int) this.f1059c.f1078b.k;
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicWidth() {
        Drawable drawable = this.f1057a;
        if (drawable != null) {
            return drawable.getIntrinsicWidth();
        }
        return (int) this.f1059c.f1078b.j;
    }

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        Drawable drawable = this.f1057a;
        if (drawable != null) {
            return drawable.getOpacity();
        }
        return -3;
    }

    @Override // android.graphics.drawable.Drawable
    public void inflate(Resources resources, XmlPullParser xmlPullParser, AttributeSet attributeSet) {
        Drawable drawable = this.f1057a;
        if (drawable != null) {
            drawable.inflate(resources, xmlPullParser, attributeSet);
        } else {
            inflate(resources, xmlPullParser, attributeSet, null);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public void invalidateSelf() {
        Drawable drawable = this.f1057a;
        if (drawable != null) {
            drawable.invalidateSelf();
        } else {
            super.invalidateSelf();
        }
    }

    @Override // android.graphics.drawable.Drawable
    public boolean isAutoMirrored() {
        Drawable drawable = this.f1057a;
        if (drawable != null) {
            return C.a(drawable);
        }
        return this.f1059c.f1081e;
    }

    @Override // android.graphics.drawable.Drawable
    public boolean isStateful() {
        g gVar;
        ColorStateList colorStateList;
        Drawable drawable = this.f1057a;
        if (drawable != null) {
            return drawable.isStateful();
        }
        return super.isStateful() || ((gVar = this.f1059c) != null && (gVar.c() || ((colorStateList = this.f1059c.f1079c) != null && colorStateList.isStateful())));
    }

    @Override // android.graphics.drawable.Drawable
    public Drawable mutate() {
        Drawable drawable = this.f1057a;
        if (drawable != null) {
            drawable.mutate();
            return this;
        }
        if (!this.f && super.mutate() == this) {
            this.f1059c = new g(this.f1059c);
            this.f = true;
        }
        return this;
    }

    @Override // android.graphics.drawable.Drawable
    public void onBoundsChange(Rect rect) {
        Drawable drawable = this.f1057a;
        if (drawable != null) {
            drawable.setBounds(rect);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public boolean onStateChange(int[] iArr) {
        PorterDuff.Mode mode;
        Drawable drawable = this.f1057a;
        if (drawable != null) {
            return drawable.setState(iArr);
        }
        boolean z = false;
        g gVar = this.f1059c;
        ColorStateList colorStateList = gVar.f1079c;
        if (!(colorStateList == null || (mode = gVar.f1080d) == null)) {
            this.f1060d = a(this.f1060d, colorStateList, mode);
            invalidateSelf();
            z = true;
        }
        if (!gVar.c() || !gVar.a(iArr)) {
            return z;
        }
        invalidateSelf();
        return true;
    }

    @Override // android.graphics.drawable.Drawable
    public void scheduleSelf(Runnable runnable, long j) {
        Drawable drawable = this.f1057a;
        if (drawable != null) {
            drawable.scheduleSelf(runnable, j);
        } else {
            super.scheduleSelf(runnable, j);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int i) {
        Drawable drawable = this.f1057a;
        if (drawable != null) {
            drawable.setAlpha(i);
        } else if (this.f1059c.f1078b.getRootAlpha() != i) {
            this.f1059c.f1078b.setRootAlpha(i);
            invalidateSelf();
        }
    }

    @Override // android.graphics.drawable.Drawable
    public void setAutoMirrored(boolean z) {
        Drawable drawable = this.f1057a;
        if (drawable != null) {
            int i = Build.VERSION.SDK_INT;
            drawable.setAutoMirrored(z);
            return;
        }
        this.f1059c.f1081e = z;
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter colorFilter) {
        Drawable drawable = this.f1057a;
        if (drawable != null) {
            drawable.setColorFilter(colorFilter);
            return;
        }
        this.f1061e = colorFilter;
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable
    public void setTint(int i) {
        Drawable drawable = this.f1057a;
        if (drawable != null) {
            int i2 = Build.VERSION.SDK_INT;
            drawable.setTint(i);
            return;
        }
        setTintList(ColorStateList.valueOf(i));
    }

    @Override // android.graphics.drawable.Drawable
    public void setTintList(ColorStateList colorStateList) {
        Drawable drawable = this.f1057a;
        if (drawable != null) {
            int i = Build.VERSION.SDK_INT;
            drawable.setTintList(colorStateList);
            return;
        }
        g gVar = this.f1059c;
        if (gVar.f1079c != colorStateList) {
            gVar.f1079c = colorStateList;
            this.f1060d = a(this.f1060d, colorStateList, gVar.f1080d);
            invalidateSelf();
        }
    }

    @Override // android.graphics.drawable.Drawable
    public void setTintMode(PorterDuff.Mode mode) {
        Drawable drawable = this.f1057a;
        if (drawable != null) {
            int i = Build.VERSION.SDK_INT;
            drawable.setTintMode(mode);
            return;
        }
        g gVar = this.f1059c;
        if (gVar.f1080d != mode) {
            gVar.f1080d = mode;
            this.f1060d = a(this.f1060d, gVar.f1079c, mode);
            invalidateSelf();
        }
    }

    @Override // android.graphics.drawable.Drawable
    public boolean setVisible(boolean z, boolean z2) {
        Drawable drawable = this.f1057a;
        if (drawable != null) {
            return drawable.setVisible(z, z2);
        }
        return super.setVisible(z, z2);
    }

    @Override // android.graphics.drawable.Drawable
    public void unscheduleSelf(Runnable runnable) {
        Drawable drawable = this.f1057a;
        if (drawable != null) {
            drawable.unscheduleSelf(runnable);
        } else {
            super.unscheduleSelf(runnable);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static abstract class e extends d {

        /* renamed from: a  reason: collision with root package name */
        public c.e.c.b[] f1069a;

        /* renamed from: b  reason: collision with root package name */
        public String f1070b;

        /* renamed from: c  reason: collision with root package name */
        public int f1071c;

        public e() {
            super(null);
            this.f1069a = null;
        }

        public void a(Path path) {
            path.reset();
            c.e.c.b[] bVarArr = this.f1069a;
            if (bVarArr != null) {
                c.e.c.b.a(bVarArr, path);
            }
        }

        public boolean b() {
            return false;
        }

        public c.e.c.b[] getPathData() {
            return this.f1069a;
        }

        public String getPathName() {
            return this.f1070b;
        }

        public void setPathData(c.e.c.b[] bVarArr) {
            if (!C.a(this.f1069a, bVarArr)) {
                this.f1069a = C.a(bVarArr);
                return;
            }
            c.e.c.b[] bVarArr2 = this.f1069a;
            for (int i = 0; i < bVarArr.length; i++) {
                bVarArr2[i].f774a = bVarArr[i].f774a;
                for (int i2 = 0; i2 < bVarArr[i].f775b.length; i2++) {
                    bVarArr2[i].f775b[i2] = bVarArr[i].f775b[i2];
                }
            }
        }

        public e(e eVar) {
            super(null);
            this.f1069a = null;
            this.f1070b = eVar.f1070b;
            this.f1071c = eVar.f1071c;
            this.f1069a = C.a(eVar.f1069a);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class h extends Drawable.ConstantState {

        /* renamed from: a  reason: collision with root package name */
        public final Drawable.ConstantState f1082a;

        public h(Drawable.ConstantState constantState) {
            this.f1082a = constantState;
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public boolean canApplyTheme() {
            return this.f1082a.canApplyTheme();
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public int getChangingConfigurations() {
            return this.f1082a.getChangingConfigurations();
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public Drawable newDrawable() {
            i iVar = new i();
            iVar.f1057a = (VectorDrawable) this.f1082a.newDrawable();
            return iVar;
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public Drawable newDrawable(Resources resources) {
            i iVar = new i();
            iVar.f1057a = (VectorDrawable) this.f1082a.newDrawable(resources);
            return iVar;
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public Drawable newDrawable(Resources resources, Resources.Theme theme) {
            i iVar = new i();
            iVar.f1057a = (VectorDrawable) this.f1082a.newDrawable(resources, theme);
            return iVar;
        }
    }

    public static i a(Resources resources, int i, Resources.Theme theme) {
        int next;
        if (Build.VERSION.SDK_INT >= 24) {
            i iVar = new i();
            int i2 = Build.VERSION.SDK_INT;
            iVar.f1057a = resources.getDrawable(i, theme);
            new h(iVar.f1057a.getConstantState());
            return iVar;
        }
        try {
            XmlResourceParser xml = resources.getXml(i);
            AttributeSet asAttributeSet = Xml.asAttributeSet(xml);
            while (true) {
                next = xml.next();
                if (next == 2 || next == 1) {
                    break;
                }
            }
            if (next == 2) {
                return createFromXmlInner(resources, (XmlPullParser) xml, asAttributeSet, theme);
            }
            throw new XmlPullParserException("No start tag found");
        } catch (IOException | XmlPullParserException unused) {
            return null;
        }
    }

    @Override // android.graphics.drawable.Drawable
    public void inflate(Resources resources, XmlPullParser xmlPullParser, AttributeSet attributeSet, Resources.Theme theme) {
        Drawable drawable = this.f1057a;
        if (drawable != null) {
            int i = Build.VERSION.SDK_INT;
            drawable.inflate(resources, xmlPullParser, attributeSet, theme);
            return;
        }
        g gVar = this.f1059c;
        gVar.f1078b = new f();
        TypedArray a2 = C.a(resources, theme, attributeSet, a.f1039a);
        g gVar2 = this.f1059c;
        f fVar = gVar2.f1078b;
        int b2 = C.b(a2, xmlPullParser, "tintMode", 6, -1);
        PorterDuff.Mode mode = PorterDuff.Mode.SRC_IN;
        if (b2 == 3) {
            mode = PorterDuff.Mode.SRC_OVER;
        } else if (b2 != 5) {
            if (b2 != 9) {
                switch (b2) {
                    case 14:
                        mode = PorterDuff.Mode.MULTIPLY;
                        break;
                    case 15:
                        mode = PorterDuff.Mode.SCREEN;
                        break;
                    case 16:
                        mode = PorterDuff.Mode.ADD;
                        break;
                }
            } else {
                mode = PorterDuff.Mode.SRC_ATOP;
            }
        }
        gVar2.f1080d = mode;
        int i2 = 1;
        ColorStateList colorStateList = a2.getColorStateList(1);
        if (colorStateList != null) {
            gVar2.f1079c = colorStateList;
        }
        boolean z = gVar2.f1081e;
        if (C.a(xmlPullParser, "autoMirrored")) {
            z = a2.getBoolean(5, z);
        }
        gVar2.f1081e = z;
        fVar.l = C.a(a2, xmlPullParser, "viewportWidth", 7, fVar.l);
        fVar.m = C.a(a2, xmlPullParser, "viewportHeight", 8, fVar.m);
        if (fVar.l <= 0.0f) {
            throw new XmlPullParserException(a2.getPositionDescription() + "<vector> tag requires viewportWidth > 0");
        } else if (fVar.m > 0.0f) {
            fVar.j = a2.getDimension(3, fVar.j);
            int i3 = 2;
            fVar.k = a2.getDimension(2, fVar.k);
            if (fVar.j <= 0.0f) {
                throw new XmlPullParserException(a2.getPositionDescription() + "<vector> tag requires width > 0");
            } else if (fVar.k > 0.0f) {
                fVar.setAlpha(C.a(a2, xmlPullParser, "alpha", 4, fVar.getAlpha()));
                String string = a2.getString(0);
                if (string != null) {
                    fVar.o = string;
                    fVar.q.put(string, fVar);
                }
                a2.recycle();
                gVar.f1077a = getChangingConfigurations();
                gVar.k = true;
                g gVar3 = this.f1059c;
                f fVar2 = gVar3.f1078b;
                ArrayDeque arrayDeque = new ArrayDeque();
                arrayDeque.push(fVar2.i);
                int eventType = xmlPullParser.getEventType();
                int depth = xmlPullParser.getDepth() + 1;
                boolean z2 = true;
                while (eventType != i2 && (xmlPullParser.getDepth() >= depth || eventType != 3)) {
                    if (eventType == i3) {
                        String name = xmlPullParser.getName();
                        c cVar = (c) arrayDeque.peek();
                        if ("path".equals(name)) {
                            b bVar = new b();
                            bVar.a(resources, attributeSet, theme, xmlPullParser);
                            cVar.f1065b.add(bVar);
                            if (bVar.getPathName() != null) {
                                fVar2.q.put(bVar.getPathName(), bVar);
                            }
                            gVar3.f1077a = bVar.f1071c | gVar3.f1077a;
                            z2 = false;
                        } else if ("clip-path".equals(name)) {
                            a aVar = new a();
                            aVar.a(resources, attributeSet, theme, xmlPullParser);
                            cVar.f1065b.add(aVar);
                            if (aVar.getPathName() != null) {
                                fVar2.q.put(aVar.getPathName(), aVar);
                            }
                            gVar3.f1077a = aVar.f1071c | gVar3.f1077a;
                        } else if ("group".equals(name)) {
                            c cVar2 = new c();
                            cVar2.a(resources, attributeSet, theme, xmlPullParser);
                            cVar.f1065b.add(cVar2);
                            arrayDeque.push(cVar2);
                            if (cVar2.getGroupName() != null) {
                                fVar2.q.put(cVar2.getGroupName(), cVar2);
                            }
                            gVar3.f1077a = cVar2.k | gVar3.f1077a;
                        }
                    } else if (eventType == 3 && "group".equals(xmlPullParser.getName())) {
                        arrayDeque.pop();
                    }
                    eventType = xmlPullParser.next();
                    i2 = 1;
                    i3 = 2;
                }
                if (!z2) {
                    this.f1060d = a(this.f1060d, gVar.f1079c, gVar.f1080d);
                    return;
                }
                throw new XmlPullParserException("no path defined");
            } else {
                throw new XmlPullParserException(a2.getPositionDescription() + "<vector> tag requires height > 0");
            }
        } else {
            throw new XmlPullParserException(a2.getPositionDescription() + "<vector> tag requires viewportHeight > 0");
        }
    }

    public i(g gVar) {
        this.g = true;
        this.h = new float[9];
        this.i = new Matrix();
        this.j = new Rect();
        this.f1059c = gVar;
        this.f1060d = a(this.f1060d, gVar.f1079c, gVar.f1080d);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class b extends e {

        /* renamed from: d  reason: collision with root package name */
        public int[] f1062d;

        /* renamed from: e  reason: collision with root package name */
        public c.e.b.a.a f1063e;
        public float f;
        public c.e.b.a.a g;
        public float h;
        public int i;
        public float j;
        public float k;
        public float l;
        public float m;
        public Paint.Cap n;
        public Paint.Join o;
        public float p;

        public b() {
            this.f = 0.0f;
            this.h = 1.0f;
            this.i = 0;
            this.j = 1.0f;
            this.k = 0.0f;
            this.l = 1.0f;
            this.m = 0.0f;
            this.n = Paint.Cap.BUTT;
            this.o = Paint.Join.MITER;
            this.p = 4.0f;
        }

        public void a(Resources resources, AttributeSet attributeSet, Resources.Theme theme, XmlPullParser xmlPullParser) {
            TypedArray a2 = C.a(resources, theme, attributeSet, a.f1041c);
            this.f1062d = null;
            if (C.a(xmlPullParser, "pathData")) {
                String string = a2.getString(0);
                if (string != null) {
                    this.f1070b = string;
                }
                String string2 = a2.getString(2);
                if (string2 != null) {
                    this.f1069a = C.b(string2);
                }
                this.g = C.a(a2, xmlPullParser, theme, "fillColor", 1, 0);
                this.j = C.a(a2, xmlPullParser, "fillAlpha", 12, this.j);
                int b2 = C.b(a2, xmlPullParser, "strokeLineCap", 8, -1);
                Paint.Cap cap = this.n;
                if (b2 == 0) {
                    cap = Paint.Cap.BUTT;
                } else if (b2 == 1) {
                    cap = Paint.Cap.ROUND;
                } else if (b2 == 2) {
                    cap = Paint.Cap.SQUARE;
                }
                this.n = cap;
                int b3 = C.b(a2, xmlPullParser, "strokeLineJoin", 9, -1);
                Paint.Join join = this.o;
                if (b3 == 0) {
                    join = Paint.Join.MITER;
                } else if (b3 == 1) {
                    join = Paint.Join.ROUND;
                } else if (b3 == 2) {
                    join = Paint.Join.BEVEL;
                }
                this.o = join;
                this.p = C.a(a2, xmlPullParser, "strokeMiterLimit", 10, this.p);
                this.f1063e = C.a(a2, xmlPullParser, theme, "strokeColor", 3, 0);
                this.h = C.a(a2, xmlPullParser, "strokeAlpha", 11, this.h);
                this.f = C.a(a2, xmlPullParser, "strokeWidth", 4, this.f);
                this.l = C.a(a2, xmlPullParser, "trimPathEnd", 6, this.l);
                this.m = C.a(a2, xmlPullParser, "trimPathOffset", 7, this.m);
                this.k = C.a(a2, xmlPullParser, "trimPathStart", 5, this.k);
                this.i = C.b(a2, xmlPullParser, "fillType", 13, this.i);
            }
            a2.recycle();
        }

        public float getFillAlpha() {
            return this.j;
        }

        public int getFillColor() {
            return this.g.f749c;
        }

        public float getStrokeAlpha() {
            return this.h;
        }

        public int getStrokeColor() {
            return this.f1063e.f749c;
        }

        public float getStrokeWidth() {
            return this.f;
        }

        public float getTrimPathEnd() {
            return this.l;
        }

        public float getTrimPathOffset() {
            return this.m;
        }

        public float getTrimPathStart() {
            return this.k;
        }

        public void setFillAlpha(float f) {
            this.j = f;
        }

        public void setFillColor(int i) {
            this.g.f749c = i;
        }

        public void setStrokeAlpha(float f) {
            this.h = f;
        }

        public void setStrokeColor(int i) {
            this.f1063e.f749c = i;
        }

        public void setStrokeWidth(float f) {
            this.f = f;
        }

        public void setTrimPathEnd(float f) {
            this.l = f;
        }

        public void setTrimPathOffset(float f) {
            this.m = f;
        }

        public void setTrimPathStart(float f) {
            this.k = f;
        }

        public b(b bVar) {
            super(bVar);
            this.f = 0.0f;
            this.h = 1.0f;
            this.i = 0;
            this.j = 1.0f;
            this.k = 0.0f;
            this.l = 1.0f;
            this.m = 0.0f;
            this.n = Paint.Cap.BUTT;
            this.o = Paint.Join.MITER;
            this.p = 4.0f;
            this.f1062d = bVar.f1062d;
            this.f1063e = bVar.f1063e;
            this.f = bVar.f;
            this.h = bVar.h;
            this.g = bVar.g;
            this.i = bVar.i;
            this.j = bVar.j;
            this.k = bVar.k;
            this.l = bVar.l;
            this.m = bVar.m;
            this.n = bVar.n;
            this.o = bVar.o;
            this.p = bVar.p;
        }

        @Override // c.m.a.a.i.d
        public boolean a() {
            return this.g.c() || this.f1063e.c();
        }

        @Override // c.m.a.a.i.d
        public boolean a(int[] iArr) {
            return this.f1063e.a(iArr) | this.g.a(iArr);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class c extends d {

        /* renamed from: a  reason: collision with root package name */
        public final Matrix f1064a;

        /* renamed from: b  reason: collision with root package name */
        public final ArrayList<d> f1065b;

        /* renamed from: c  reason: collision with root package name */
        public float f1066c;

        /* renamed from: d  reason: collision with root package name */
        public float f1067d;

        /* renamed from: e  reason: collision with root package name */
        public float f1068e;
        public float f;
        public float g;
        public float h;
        public float i;
        public final Matrix j;
        public int k;
        public int[] l;
        public String m;

        public c(c cVar, c.c.b<String, Object> bVar) {
            super(null);
            e eVar;
            this.f1064a = new Matrix();
            this.f1065b = new ArrayList<>();
            this.f1066c = 0.0f;
            this.f1067d = 0.0f;
            this.f1068e = 0.0f;
            this.f = 1.0f;
            this.g = 1.0f;
            this.h = 0.0f;
            this.i = 0.0f;
            this.j = new Matrix();
            this.m = null;
            this.f1066c = cVar.f1066c;
            this.f1067d = cVar.f1067d;
            this.f1068e = cVar.f1068e;
            this.f = cVar.f;
            this.g = cVar.g;
            this.h = cVar.h;
            this.i = cVar.i;
            this.l = cVar.l;
            this.m = cVar.m;
            this.k = cVar.k;
            String str = this.m;
            if (str != null) {
                bVar.put(str, this);
            }
            this.j.set(cVar.j);
            ArrayList<d> arrayList = cVar.f1065b;
            for (int i = 0; i < arrayList.size(); i++) {
                d dVar = arrayList.get(i);
                if (dVar instanceof c) {
                    this.f1065b.add(new c((c) dVar, bVar));
                } else {
                    if (dVar instanceof b) {
                        eVar = new b((b) dVar);
                    } else if (dVar instanceof a) {
                        eVar = new a((a) dVar);
                    } else {
                        throw new IllegalStateException("Unknown object in the tree!");
                    }
                    this.f1065b.add(eVar);
                    String str2 = eVar.f1070b;
                    if (str2 != null) {
                        bVar.put(str2, eVar);
                    }
                }
            }
        }

        public void a(Resources resources, AttributeSet attributeSet, Resources.Theme theme, XmlPullParser xmlPullParser) {
            TypedArray a2 = C.a(resources, theme, attributeSet, a.f1040b);
            this.l = null;
            this.f1066c = C.a(a2, xmlPullParser, "rotation", 5, this.f1066c);
            this.f1067d = a2.getFloat(1, this.f1067d);
            this.f1068e = a2.getFloat(2, this.f1068e);
            this.f = C.a(a2, xmlPullParser, "scaleX", 3, this.f);
            this.g = C.a(a2, xmlPullParser, "scaleY", 4, this.g);
            this.h = C.a(a2, xmlPullParser, "translateX", 6, this.h);
            this.i = C.a(a2, xmlPullParser, "translateY", 7, this.i);
            String string = a2.getString(0);
            if (string != null) {
                this.m = string;
            }
            b();
            a2.recycle();
        }

        public final void b() {
            this.j.reset();
            this.j.postTranslate(-this.f1067d, -this.f1068e);
            this.j.postScale(this.f, this.g);
            this.j.postRotate(this.f1066c, 0.0f, 0.0f);
            this.j.postTranslate(this.h + this.f1067d, this.i + this.f1068e);
        }

        public String getGroupName() {
            return this.m;
        }

        public Matrix getLocalMatrix() {
            return this.j;
        }

        public float getPivotX() {
            return this.f1067d;
        }

        public float getPivotY() {
            return this.f1068e;
        }

        public float getRotation() {
            return this.f1066c;
        }

        public float getScaleX() {
            return this.f;
        }

        public float getScaleY() {
            return this.g;
        }

        public float getTranslateX() {
            return this.h;
        }

        public float getTranslateY() {
            return this.i;
        }

        public void setPivotX(float f) {
            if (f != this.f1067d) {
                this.f1067d = f;
                b();
            }
        }

        public void setPivotY(float f) {
            if (f != this.f1068e) {
                this.f1068e = f;
                b();
            }
        }

        public void setRotation(float f) {
            if (f != this.f1066c) {
                this.f1066c = f;
                b();
            }
        }

        public void setScaleX(float f) {
            if (f != this.f) {
                this.f = f;
                b();
            }
        }

        public void setScaleY(float f) {
            if (f != this.g) {
                this.g = f;
                b();
            }
        }

        public void setTranslateX(float f) {
            if (f != this.h) {
                this.h = f;
                b();
            }
        }

        public void setTranslateY(float f) {
            if (f != this.i) {
                this.i = f;
                b();
            }
        }

        @Override // c.m.a.a.i.d
        public boolean a() {
            for (int i = 0; i < this.f1065b.size(); i++) {
                if (this.f1065b.get(i).a()) {
                    return true;
                }
            }
            return false;
        }

        @Override // c.m.a.a.i.d
        public boolean a(int[] iArr) {
            boolean z = false;
            for (int i = 0; i < this.f1065b.size(); i++) {
                z |= this.f1065b.get(i).a(iArr);
            }
            return z;
        }

        public c() {
            super(null);
            this.f1064a = new Matrix();
            this.f1065b = new ArrayList<>();
            this.f1066c = 0.0f;
            this.f1067d = 0.0f;
            this.f1068e = 0.0f;
            this.f = 1.0f;
            this.g = 1.0f;
            this.h = 0.0f;
            this.i = 0.0f;
            this.j = new Matrix();
            this.m = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class f {

        /* renamed from: a  reason: collision with root package name */
        public static final Matrix f1072a = new Matrix();

        /* renamed from: b  reason: collision with root package name */
        public final Path f1073b;

        /* renamed from: c  reason: collision with root package name */
        public final Path f1074c;

        /* renamed from: d  reason: collision with root package name */
        public final Matrix f1075d;

        /* renamed from: e  reason: collision with root package name */
        public Paint f1076e;
        public Paint f;
        public PathMeasure g;
        public int h;
        public final c i;
        public float j;
        public float k;
        public float l;
        public float m;
        public int n;
        public String o;
        public Boolean p;
        public final c.c.b<String, Object> q;

        public f() {
            this.f1075d = new Matrix();
            this.j = 0.0f;
            this.k = 0.0f;
            this.l = 0.0f;
            this.m = 0.0f;
            this.n = WebView.NORMAL_MODE_ALPHA;
            this.o = null;
            this.p = null;
            this.q = new c.c.b<>();
            this.i = new c();
            this.f1073b = new Path();
            this.f1074c = new Path();
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r11v0 */
        /* JADX WARN: Type inference failed for: r11v1, types: [boolean] */
        /* JADX WARN: Type inference failed for: r11v2 */
        /* JADX WARN: Unknown variable types count: 1 */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final void a(c.m.a.a.i.c r17, android.graphics.Matrix r18, android.graphics.Canvas r19, int r20, int r21, android.graphics.ColorFilter r22) {
            /*
                Method dump skipped, instructions count: 548
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: c.m.a.a.i.f.a(c.m.a.a.i$c, android.graphics.Matrix, android.graphics.Canvas, int, int, android.graphics.ColorFilter):void");
        }

        public float getAlpha() {
            return getRootAlpha() / 255.0f;
        }

        public int getRootAlpha() {
            return this.n;
        }

        public void setAlpha(float f) {
            setRootAlpha((int) (f * 255.0f));
        }

        public void setRootAlpha(int i) {
            this.n = i;
        }

        public f(f fVar) {
            this.f1075d = new Matrix();
            this.j = 0.0f;
            this.k = 0.0f;
            this.l = 0.0f;
            this.m = 0.0f;
            this.n = WebView.NORMAL_MODE_ALPHA;
            this.o = null;
            this.p = null;
            this.q = new c.c.b<>();
            this.i = new c(fVar.i, this.q);
            this.f1073b = new Path(fVar.f1073b);
            this.f1074c = new Path(fVar.f1074c);
            this.j = fVar.j;
            this.k = fVar.k;
            this.l = fVar.l;
            this.m = fVar.m;
            this.h = fVar.h;
            this.n = fVar.n;
            this.o = fVar.o;
            String str = fVar.o;
            if (str != null) {
                this.q.put(str, this);
            }
            this.p = fVar.p;
        }

        public void a(Canvas canvas, int i, int i2, ColorFilter colorFilter) {
            a(this.i, f1072a, canvas, i, i2, colorFilter);
        }

        public boolean a() {
            if (this.p == null) {
                this.p = Boolean.valueOf(this.i.a());
            }
            return this.p.booleanValue();
        }

        public boolean a(int[] iArr) {
            return this.i.a(iArr);
        }
    }

    public static int a(int i, float f2) {
        return (i & 16777215) | (((int) (Color.alpha(i) * f2)) << 24);
    }
}
