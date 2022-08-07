package c.m.a.a;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.animation.ArgbEvaluator;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import android.graphics.ColorFilter;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.drawable.AnimatedVectorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Xml;
import c.a.a.C;
import c.m.a.a.i;
import java.io.IOException;
import java.util.ArrayList;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

/* loaded from: classes.dex */
public class d extends g implements b {

    /* renamed from: b */
    public a f1045b;

    /* renamed from: c */
    public Context f1046c;

    /* renamed from: d */
    public ArgbEvaluator f1047d;

    /* renamed from: e */
    public final Drawable.Callback f1048e;

    /* loaded from: classes.dex */
    public static class a extends Drawable.ConstantState {

        /* renamed from: a */
        public int f1049a;

        /* renamed from: b */
        public i f1050b;

        /* renamed from: c */
        public AnimatorSet f1051c;

        /* renamed from: d */
        public ArrayList<Animator> f1052d;

        /* renamed from: e */
        public c.c.b<Animator, String> f1053e;

        public a(Context context, a aVar, Drawable.Callback callback, Resources resources) {
            Drawable.ConstantState constantState;
            if (aVar != null) {
                this.f1049a = aVar.f1049a;
                i iVar = aVar.f1050b;
                if (iVar != null) {
                    Drawable drawable = iVar.f1057a;
                    if (drawable == null || Build.VERSION.SDK_INT < 24) {
                        iVar.f1059c.f1077a = iVar.getChangingConfigurations();
                        constantState = iVar.f1059c;
                    } else {
                        constantState = new i.h(drawable.getConstantState());
                    }
                    if (resources != null) {
                        this.f1050b = (i) constantState.newDrawable(resources);
                    } else {
                        this.f1050b = (i) constantState.newDrawable();
                    }
                    i iVar2 = this.f1050b;
                    iVar2.mutate();
                    this.f1050b = iVar2;
                    this.f1050b.setCallback(callback);
                    this.f1050b.setBounds(aVar.f1050b.getBounds());
                    this.f1050b.g = false;
                }
                ArrayList<Animator> arrayList = aVar.f1052d;
                if (arrayList != null) {
                    int size = arrayList.size();
                    this.f1052d = new ArrayList<>(size);
                    this.f1053e = new c.c.b<>(size);
                    for (int i = 0; i < size; i++) {
                        Animator animator = aVar.f1052d.get(i);
                        Animator clone = animator.clone();
                        String str = aVar.f1053e.get(animator);
                        clone.setTarget(this.f1050b.f1059c.f1078b.q.get(str));
                        this.f1052d.add(clone);
                        this.f1053e.put(clone, str);
                    }
                    if (this.f1051c == null) {
                        this.f1051c = new AnimatorSet();
                    }
                    this.f1051c.playTogether(this.f1052d);
                }
            }
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public int getChangingConfigurations() {
            return this.f1049a;
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public Drawable newDrawable() {
            throw new IllegalStateException("No constant state support for SDK < 24.");
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public Drawable newDrawable(Resources resources) {
            throw new IllegalStateException("No constant state support for SDK < 24.");
        }
    }

    public d() {
        this(null, null, null);
    }

    public static d a(Context context, Resources resources, XmlPullParser xmlPullParser, AttributeSet attributeSet, Resources.Theme theme) {
        d dVar = new d(context, null, null);
        dVar.inflate(resources, xmlPullParser, attributeSet, theme);
        return dVar;
    }

    @Override // c.m.a.a.g, android.graphics.drawable.Drawable
    public void applyTheme(Resources.Theme theme) {
        Drawable drawable = this.f1057a;
        if (drawable != null) {
            int i = Build.VERSION.SDK_INT;
            drawable.applyTheme(theme);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public boolean canApplyTheme() {
        Drawable drawable = this.f1057a;
        if (drawable == null) {
            return false;
        }
        int i = Build.VERSION.SDK_INT;
        return drawable.canApplyTheme();
    }

    /* JADX WARN: Code restructure failed: missing block: B:40:0x00e3, code lost:
        if (r7 == 1) goto L_0x00e7;
     */
    @Override // android.graphics.drawable.Drawable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void draw(android.graphics.Canvas r13) {
        /*
            Method dump skipped, instructions count: 310
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: c.m.a.a.d.draw(android.graphics.Canvas):void");
    }

    @Override // android.graphics.drawable.Drawable
    public int getAlpha() {
        Drawable drawable = this.f1057a;
        if (drawable != null) {
            int i = Build.VERSION.SDK_INT;
            return drawable.getAlpha();
        }
        i iVar = this.f1045b.f1050b;
        Drawable drawable2 = iVar.f1057a;
        if (drawable2 == null) {
            return iVar.f1059c.f1078b.getRootAlpha();
        }
        int i2 = Build.VERSION.SDK_INT;
        return drawable2.getAlpha();
    }

    @Override // android.graphics.drawable.Drawable
    public int getChangingConfigurations() {
        Drawable drawable = this.f1057a;
        if (drawable != null) {
            return drawable.getChangingConfigurations();
        }
        return super.getChangingConfigurations() | this.f1045b.f1049a;
    }

    @Override // android.graphics.drawable.Drawable
    public Drawable.ConstantState getConstantState() {
        Drawable drawable = this.f1057a;
        if (drawable == null || Build.VERSION.SDK_INT < 24) {
            return null;
        }
        return new b(drawable.getConstantState());
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicHeight() {
        Drawable drawable = this.f1057a;
        if (drawable != null) {
            return drawable.getIntrinsicHeight();
        }
        i iVar = this.f1045b.f1050b;
        Drawable drawable2 = iVar.f1057a;
        if (drawable2 != null) {
            return drawable2.getIntrinsicHeight();
        }
        return (int) iVar.f1059c.f1078b.k;
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicWidth() {
        Drawable drawable = this.f1057a;
        if (drawable != null) {
            return drawable.getIntrinsicWidth();
        }
        i iVar = this.f1045b.f1050b;
        Drawable drawable2 = iVar.f1057a;
        if (drawable2 != null) {
            return drawable2.getIntrinsicWidth();
        }
        return (int) iVar.f1059c.f1078b.j;
    }

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        Drawable drawable = this.f1057a;
        if (drawable != null) {
            return drawable.getOpacity();
        }
        Drawable drawable2 = this.f1045b.f1050b.f1057a;
        if (drawable2 != null) {
            return drawable2.getOpacity();
        }
        return -3;
    }

    @Override // android.graphics.drawable.Drawable
    public void inflate(Resources resources, XmlPullParser xmlPullParser, AttributeSet attributeSet, Resources.Theme theme) {
        Animator animator;
        String str;
        XmlPullParserException e2;
        IOException e3;
        Drawable drawable = this.f1057a;
        if (drawable != null) {
            int i = Build.VERSION.SDK_INT;
            drawable.inflate(resources, xmlPullParser, attributeSet, theme);
            return;
        }
        int eventType = xmlPullParser.getEventType();
        int depth = xmlPullParser.getDepth() + 1;
        for (int i2 = 1; eventType != i2 && (xmlPullParser.getDepth() >= depth || eventType != 3); i2 = 1) {
            if (eventType == 2) {
                String name = xmlPullParser.getName();
                XmlResourceParser xmlResourceParser = null;
                if ("animated-vector".equals(name)) {
                    TypedArray a2 = C.a(resources, theme, attributeSet, a.f1043e);
                    int resourceId = a2.getResourceId(0, 0);
                    if (resourceId != 0) {
                        i a3 = i.a(resources, resourceId, theme);
                        a3.g = false;
                        a3.setCallback(this.f1048e);
                        i iVar = this.f1045b.f1050b;
                        if (iVar != null) {
                            iVar.setCallback(null);
                        }
                        this.f1045b.f1050b = a3;
                    }
                    a2.recycle();
                } else if ("target".equals(name)) {
                    TypedArray obtainAttributes = resources.obtainAttributes(attributeSet, a.f);
                    String string = obtainAttributes.getString(0);
                    int resourceId2 = obtainAttributes.getResourceId(i2, 0);
                    if (resourceId2 != 0) {
                        Context context = this.f1046c;
                        if (context != null) {
                            if (Build.VERSION.SDK_INT >= 24) {
                                animator = AnimatorInflater.loadAnimator(context, resourceId2);
                            } else {
                                Resources resources2 = context.getResources();
                                Resources.Theme theme2 = context.getTheme();
                                try {
                                    try {
                                        XmlResourceParser animation = resources2.getAnimation(resourceId2);
                                        str = "Can't load animation resource ID #0x";
                                        try {
                                            animator = C.a(context, resources2, theme2, animation, Xml.asAttributeSet(animation), null, 0, 1.0f);
                                            animation.close();
                                        } catch (IOException e4) {
                                            e3 = e4;
                                            Resources.NotFoundException notFoundException = new Resources.NotFoundException(str + Integer.toHexString(resourceId2));
                                            notFoundException.initCause(e3);
                                            throw notFoundException;
                                        } catch (XmlPullParserException e5) {
                                            e2 = e5;
                                            Resources.NotFoundException notFoundException2 = new Resources.NotFoundException(str + Integer.toHexString(resourceId2));
                                            notFoundException2.initCause(e2);
                                            throw notFoundException2;
                                        }
                                    } catch (Throwable th) {
                                        if (0 != 0) {
                                            xmlResourceParser.close();
                                        }
                                        throw th;
                                    }
                                } catch (IOException e6) {
                                    e3 = e6;
                                    str = "Can't load animation resource ID #0x";
                                } catch (XmlPullParserException e7) {
                                    e2 = e7;
                                    str = "Can't load animation resource ID #0x";
                                }
                            }
                            animator.setTarget(this.f1045b.f1050b.f1059c.f1078b.q.get(string));
                            int i3 = Build.VERSION.SDK_INT;
                            a aVar = this.f1045b;
                            if (aVar.f1052d == null) {
                                aVar.f1052d = new ArrayList<>();
                                this.f1045b.f1053e = new c.c.b<>();
                            }
                            this.f1045b.f1052d.add(animator);
                            this.f1045b.f1053e.put(animator, string);
                        } else {
                            obtainAttributes.recycle();
                            throw new IllegalStateException("Context can't be null when inflating animators");
                        }
                    }
                    obtainAttributes.recycle();
                } else {
                    continue;
                }
            }
            eventType = xmlPullParser.next();
        }
        a aVar2 = this.f1045b;
        if (aVar2.f1051c == null) {
            aVar2.f1051c = new AnimatorSet();
        }
        aVar2.f1051c.playTogether(aVar2.f1052d);
    }

    @Override // android.graphics.drawable.Drawable
    public boolean isAutoMirrored() {
        Drawable drawable = this.f1057a;
        if (drawable != null) {
            return C.a(drawable);
        }
        i iVar = this.f1045b.f1050b;
        Drawable drawable2 = iVar.f1057a;
        if (drawable2 != null) {
            return C.a(drawable2);
        }
        return iVar.f1059c.f1081e;
    }

    @Override // android.graphics.drawable.Animatable
    public boolean isRunning() {
        Drawable drawable = this.f1057a;
        if (drawable != null) {
            return ((AnimatedVectorDrawable) drawable).isRunning();
        }
        return this.f1045b.f1051c.isRunning();
    }

    @Override // android.graphics.drawable.Drawable
    public boolean isStateful() {
        Drawable drawable = this.f1057a;
        if (drawable != null) {
            return drawable.isStateful();
        }
        return this.f1045b.f1050b.isStateful();
    }

    @Override // android.graphics.drawable.Drawable
    public Drawable mutate() {
        Drawable drawable = this.f1057a;
        if (drawable != null) {
            drawable.mutate();
        }
        return this;
    }

    @Override // android.graphics.drawable.Drawable
    public void onBoundsChange(Rect rect) {
        Drawable drawable = this.f1057a;
        if (drawable != null) {
            drawable.setBounds(rect);
        } else {
            this.f1045b.f1050b.setBounds(rect);
        }
    }

    @Override // c.m.a.a.g, android.graphics.drawable.Drawable
    public boolean onLevelChange(int i) {
        Drawable drawable = this.f1057a;
        if (drawable != null) {
            return drawable.setLevel(i);
        }
        return this.f1045b.f1050b.setLevel(i);
    }

    @Override // android.graphics.drawable.Drawable
    public boolean onStateChange(int[] iArr) {
        Drawable drawable = this.f1057a;
        if (drawable != null) {
            return drawable.setState(iArr);
        }
        return this.f1045b.f1050b.setState(iArr);
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int i) {
        Drawable drawable = this.f1057a;
        if (drawable != null) {
            drawable.setAlpha(i);
            return;
        }
        i iVar = this.f1045b.f1050b;
        Drawable drawable2 = iVar.f1057a;
        if (drawable2 != null) {
            drawable2.setAlpha(i);
        } else if (iVar.f1059c.f1078b.getRootAlpha() != i) {
            iVar.f1059c.f1078b.setRootAlpha(i);
            iVar.invalidateSelf();
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
        i iVar = this.f1045b.f1050b;
        Drawable drawable2 = iVar.f1057a;
        if (drawable2 != null) {
            int i2 = Build.VERSION.SDK_INT;
            drawable2.setAutoMirrored(z);
            return;
        }
        iVar.f1059c.f1081e = z;
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter colorFilter) {
        Drawable drawable = this.f1057a;
        if (drawable != null) {
            drawable.setColorFilter(colorFilter);
            return;
        }
        i iVar = this.f1045b.f1050b;
        Drawable drawable2 = iVar.f1057a;
        if (drawable2 != null) {
            drawable2.setColorFilter(colorFilter);
            return;
        }
        iVar.f1061e = colorFilter;
        iVar.invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable
    public void setTint(int i) {
        Drawable drawable = this.f1057a;
        if (drawable != null) {
            int i2 = Build.VERSION.SDK_INT;
            drawable.setTint(i);
            return;
        }
        i iVar = this.f1045b.f1050b;
        Drawable drawable2 = iVar.f1057a;
        if (drawable2 != null) {
            int i3 = Build.VERSION.SDK_INT;
            drawable2.setTint(i);
            return;
        }
        iVar.setTintList(ColorStateList.valueOf(i));
    }

    @Override // android.graphics.drawable.Drawable
    public void setTintList(ColorStateList colorStateList) {
        Drawable drawable = this.f1057a;
        if (drawable != null) {
            int i = Build.VERSION.SDK_INT;
            drawable.setTintList(colorStateList);
            return;
        }
        this.f1045b.f1050b.setTintList(colorStateList);
    }

    @Override // android.graphics.drawable.Drawable
    public void setTintMode(PorterDuff.Mode mode) {
        Drawable drawable = this.f1057a;
        if (drawable != null) {
            int i = Build.VERSION.SDK_INT;
            drawable.setTintMode(mode);
            return;
        }
        i iVar = this.f1045b.f1050b;
        Drawable drawable2 = iVar.f1057a;
        if (drawable2 != null) {
            int i2 = Build.VERSION.SDK_INT;
            drawable2.setTintMode(mode);
            return;
        }
        i.g gVar = iVar.f1059c;
        if (gVar.f1080d != mode) {
            gVar.f1080d = mode;
            iVar.f1060d = iVar.a(iVar.f1060d, gVar.f1079c, mode);
            iVar.invalidateSelf();
        }
    }

    @Override // android.graphics.drawable.Drawable
    public boolean setVisible(boolean z, boolean z2) {
        Drawable drawable = this.f1057a;
        if (drawable != null) {
            return drawable.setVisible(z, z2);
        }
        this.f1045b.f1050b.setVisible(z, z2);
        return super.setVisible(z, z2);
    }

    @Override // android.graphics.drawable.Animatable
    public void start() {
        Drawable drawable = this.f1057a;
        if (drawable != null) {
            ((AnimatedVectorDrawable) drawable).start();
        } else if (!this.f1045b.f1051c.isStarted()) {
            this.f1045b.f1051c.start();
            invalidateSelf();
        }
    }

    @Override // android.graphics.drawable.Animatable
    public void stop() {
        Drawable drawable = this.f1057a;
        if (drawable != null) {
            ((AnimatedVectorDrawable) drawable).stop();
        } else {
            this.f1045b.f1051c.end();
        }
    }

    public d(Context context, a aVar, Resources resources) {
        this.f1047d = null;
        this.f1048e = new c(this);
        this.f1046c = context;
        if (aVar != null) {
            this.f1045b = aVar;
        } else {
            this.f1045b = new a(context, aVar, this.f1048e, resources);
        }
    }

    /* loaded from: classes.dex */
    private static class b extends Drawable.ConstantState {

        /* renamed from: a */
        public final Drawable.ConstantState f1054a;

        public b(Drawable.ConstantState constantState) {
            this.f1054a = constantState;
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public boolean canApplyTheme() {
            return this.f1054a.canApplyTheme();
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public int getChangingConfigurations() {
            return this.f1054a.getChangingConfigurations();
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public Drawable newDrawable() {
            d dVar = new d(null, null, null);
            dVar.f1057a = this.f1054a.newDrawable();
            dVar.f1057a.setCallback(dVar.f1048e);
            return dVar;
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public Drawable newDrawable(Resources resources) {
            d dVar = new d(null, null, null);
            dVar.f1057a = this.f1054a.newDrawable(resources);
            dVar.f1057a.setCallback(dVar.f1048e);
            return dVar;
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public Drawable newDrawable(Resources resources, Resources.Theme theme) {
            d dVar = new d(null, null, null);
            dVar.f1057a = this.f1054a.newDrawable(resources, theme);
            dVar.f1057a.setCallback(dVar.f1048e);
            return dVar;
        }
    }

    @Override // android.graphics.drawable.Drawable
    public void inflate(Resources resources, XmlPullParser xmlPullParser, AttributeSet attributeSet) {
        inflate(resources, xmlPullParser, attributeSet, null);
    }
}
