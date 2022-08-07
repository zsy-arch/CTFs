package c.a.c.a;

import android.animation.ObjectAnimator;
import android.animation.TimeInterpolator;
import android.content.res.Resources;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.AnimatedVectorDrawable;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.StateSet;
import c.a.c.a.d;
import c.a.c.a.f;
import c.c.j;

/* loaded from: classes.dex */
public class b extends f {
    public C0004b o;
    public f p;
    public int q;
    public int r;
    public boolean s;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class a extends f {

        /* renamed from: a  reason: collision with root package name */
        public final Animatable f382a;

        public a(Animatable animatable) {
            super(null);
            this.f382a = animatable;
        }

        @Override // c.a.c.a.b.f
        public void c() {
            this.f382a.start();
        }

        @Override // c.a.c.a.b.f
        public void d() {
            this.f382a.stop();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: c.a.c.a.b$b  reason: collision with other inner class name */
    /* loaded from: classes.dex */
    public static class C0004b extends f.a {
        public c.c.f<Long> K;
        public j<Integer> L;

        public C0004b(C0004b bVar, b bVar2, Resources resources) {
            super(bVar, bVar2, resources);
            if (bVar != null) {
                this.K = bVar.K;
                this.L = bVar.L;
                return;
            }
            this.K = new c.c.f<>();
            this.L = new j<>(10);
        }

        public static long a(int i, int i2) {
            return i2 | (i << 32);
        }

        public int a(int i, int i2, Drawable drawable, boolean z) {
            int a2 = super.a(drawable);
            long a3 = a(i, i2);
            long j = z ? 8589934592L : 0L;
            long j2 = a2;
            this.K.a(a3, Long.valueOf(j2 | j));
            if (z) {
                this.K.a(a(i2, i), Long.valueOf(4294967296L | j2 | j));
            }
            return a2;
        }

        public int b(int[] iArr) {
            int a2 = super.a(iArr);
            return a2 >= 0 ? a2 : super.a(StateSet.WILD_CARD);
        }

        @Override // c.a.c.a.f.a, c.a.c.a.d.b
        public void d() {
            this.K = this.K.clone();
            this.L = this.L.clone();
        }

        @Override // c.a.c.a.f.a, android.graphics.drawable.Drawable.ConstantState
        public Drawable newDrawable() {
            return new b(this, null);
        }

        @Override // c.a.c.a.f.a, android.graphics.drawable.Drawable.ConstantState
        public Drawable newDrawable(Resources resources) {
            return new b(this, resources);
        }

        public int b(int i) {
            if (i < 0) {
                return 0;
            }
            return this.L.b(i, 0).intValue();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class c extends f {

        /* renamed from: a  reason: collision with root package name */
        public final c.m.a.a.d f383a;

        public c(c.m.a.a.d dVar) {
            super(null);
            this.f383a = dVar;
        }

        @Override // c.a.c.a.b.f
        public void c() {
            c.m.a.a.d dVar = this.f383a;
            Drawable drawable = dVar.f1057a;
            if (drawable != null) {
                ((AnimatedVectorDrawable) drawable).start();
            } else if (!dVar.f1045b.f1051c.isStarted()) {
                dVar.f1045b.f1051c.start();
                dVar.invalidateSelf();
            }
        }

        @Override // c.a.c.a.b.f
        public void d() {
            c.m.a.a.d dVar = this.f383a;
            Drawable drawable = dVar.f1057a;
            if (drawable != null) {
                ((AnimatedVectorDrawable) drawable).stop();
            } else {
                dVar.f1045b.f1051c.end();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class d extends f {

        /* renamed from: a  reason: collision with root package name */
        public final ObjectAnimator f384a;

        /* renamed from: b  reason: collision with root package name */
        public final boolean f385b;

        public d(AnimationDrawable animationDrawable, boolean z, boolean z2) {
            super(null);
            int numberOfFrames = animationDrawable.getNumberOfFrames();
            int i = z ? numberOfFrames - 1 : 0;
            int i2 = z ? 0 : numberOfFrames - 1;
            e eVar = new e(animationDrawable, z);
            ObjectAnimator ofInt = ObjectAnimator.ofInt(animationDrawable, "currentIndex", i, i2);
            int i3 = Build.VERSION.SDK_INT;
            ofInt.setAutoCancel(true);
            ofInt.setDuration(eVar.f388c);
            ofInt.setInterpolator(eVar);
            this.f385b = z2;
            this.f384a = ofInt;
        }

        @Override // c.a.c.a.b.f
        public boolean a() {
            return this.f385b;
        }

        @Override // c.a.c.a.b.f
        public void b() {
            this.f384a.reverse();
        }

        @Override // c.a.c.a.b.f
        public void c() {
            this.f384a.start();
        }

        @Override // c.a.c.a.b.f
        public void d() {
            this.f384a.cancel();
        }
    }

    /* loaded from: classes.dex */
    private static class e implements TimeInterpolator {

        /* renamed from: a  reason: collision with root package name */
        public int[] f386a;

        /* renamed from: b  reason: collision with root package name */
        public int f387b;

        /* renamed from: c  reason: collision with root package name */
        public int f388c;

        public e(AnimationDrawable animationDrawable, boolean z) {
            int numberOfFrames = animationDrawable.getNumberOfFrames();
            this.f387b = numberOfFrames;
            int[] iArr = this.f386a;
            if (iArr == null || iArr.length < numberOfFrames) {
                this.f386a = new int[numberOfFrames];
            }
            int[] iArr2 = this.f386a;
            int i = 0;
            for (int i2 = 0; i2 < numberOfFrames; i2++) {
                int duration = animationDrawable.getDuration(z ? (numberOfFrames - i2) - 1 : i2);
                iArr2[i2] = duration;
                i += duration;
            }
            this.f388c = i;
        }

        @Override // android.animation.TimeInterpolator
        public float getInterpolation(float f) {
            int i = (int) ((f * this.f388c) + 0.5f);
            int i2 = this.f387b;
            int[] iArr = this.f386a;
            int i3 = 0;
            while (i3 < i2 && i >= iArr[i3]) {
                i -= iArr[i3];
                i3++;
            }
            return (i3 / i2) + (i3 < i2 ? i / this.f388c : 0.0f);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static abstract class f {
        public /* synthetic */ f(a aVar) {
        }

        public boolean a() {
            return false;
        }

        public void b() {
        }

        public abstract void c();

        public abstract void d();
    }

    static {
        b.class.getSimpleName();
    }

    public b() {
        this(null, null);
    }

    /* JADX WARN: Code restructure failed: missing block: B:85:0x01f8, code lost:
        r5.onStateChange(r5.getState());
     */
    /* JADX WARN: Code restructure failed: missing block: B:86:0x01ff, code lost:
        return r5;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static c.a.c.a.b a(android.content.Context r17, android.content.res.Resources r18, org.xmlpull.v1.XmlPullParser r19, android.util.AttributeSet r20, android.content.res.Resources.Theme r21) {
        /*
            Method dump skipped, instructions count: 542
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: c.a.c.a.b.a(android.content.Context, android.content.res.Resources, org.xmlpull.v1.XmlPullParser, android.util.AttributeSet, android.content.res.Resources$Theme):c.a.c.a.b");
    }

    @Override // c.a.c.a.f, android.graphics.drawable.Drawable
    public boolean isStateful() {
        return true;
    }

    @Override // c.a.c.a.d, android.graphics.drawable.Drawable
    public void jumpToCurrentState() {
        boolean z;
        Drawable drawable = this.f393d;
        if (drawable != null) {
            drawable.jumpToCurrentState();
            this.f393d = null;
            z = true;
        } else {
            z = false;
        }
        Drawable drawable2 = this.f392c;
        if (drawable2 != null) {
            drawable2.jumpToCurrentState();
            if (this.f) {
                this.f392c.setAlpha(this.f394e);
            }
        }
        if (this.k != 0) {
            this.k = 0L;
            z = true;
        }
        if (this.j != 0) {
            this.j = 0L;
            z = true;
        }
        if (z) {
            invalidateSelf();
        }
        f fVar = this.p;
        if (fVar != null) {
            fVar.d();
            this.p = null;
            a(this.q);
            this.q = -1;
            this.r = -1;
        }
    }

    @Override // c.a.c.a.f, c.a.c.a.d, android.graphics.drawable.Drawable
    public Drawable mutate() {
        if (!this.s) {
            super.mutate();
            if (this == this) {
                C0004b bVar = this.o;
                bVar.K = bVar.K.clone();
                bVar.L = bVar.L.clone();
                this.s = true;
            }
        }
        return this;
    }

    /* JADX WARN: Code restructure failed: missing block: B:41:0x00e6, code lost:
        if (a(r2) == false) goto L_0x00e9;
     */
    /* JADX WARN: Removed duplicated region for block: B:40:0x00e2  */
    /* JADX WARN: Removed duplicated region for block: B:45:0x00ee  */
    /* JADX WARN: Removed duplicated region for block: B:47:? A[RETURN, SYNTHETIC] */
    @Override // c.a.c.a.f, android.graphics.drawable.Drawable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean onStateChange(int[] r19) {
        /*
            Method dump skipped, instructions count: 244
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: c.a.c.a.b.onStateChange(int[]):boolean");
    }

    @Override // c.a.c.a.d, android.graphics.drawable.Drawable
    public boolean setVisible(boolean z, boolean z2) {
        boolean visible = super.setVisible(z, z2);
        if (this.p != null && (visible || z2)) {
            if (z) {
                this.p.c();
            } else {
                jumpToCurrentState();
            }
        }
        return visible;
    }

    public b(C0004b bVar, Resources resources) {
        super(null);
        this.q = -1;
        this.r = -1;
        C0004b bVar2 = new C0004b(bVar, this, resources);
        this.f390a = bVar2;
        int i = this.g;
        if (i >= 0) {
            this.f392c = bVar2.a(i);
            Drawable drawable = this.f392c;
            if (drawable != null) {
                a(drawable);
            }
        }
        this.f393d = null;
        this.m = bVar2;
        this.o = bVar2;
        onStateChange(getState());
        jumpToCurrentState();
    }

    @Override // c.a.c.a.f, c.a.c.a.d
    /* renamed from: a */
    public d.b mo0a() {
        return new C0004b(this.o, this, null);
    }

    @Override // c.a.c.a.f, c.a.c.a.d
    /* renamed from: a  reason: collision with other method in class */
    public f.a mo0a() {
        return new C0004b(this.o, this, null);
    }

    @Override // c.a.c.a.f, c.a.c.a.d
    public void a(d.b bVar) {
        this.f390a = bVar;
        int i = this.g;
        if (i >= 0) {
            this.f392c = bVar.a(i);
            Drawable drawable = this.f392c;
            if (drawable != null) {
                a(drawable);
            }
        }
        this.f393d = null;
        if (bVar instanceof f.a) {
            this.m = (f.a) bVar;
        }
        if (bVar instanceof C0004b) {
            this.o = (C0004b) bVar;
        }
    }
}
