package c.a.c.a;

import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Outline;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.SparseArray;
import c.a.c.a.f;
import com.tencent.smtt.sdk.TbsListener;
import com.tencent.smtt.sdk.WebView;

/* loaded from: classes.dex */
public class d extends Drawable implements Drawable.Callback {

    /* renamed from: a */
    public b f390a;

    /* renamed from: b */
    public Rect f391b;

    /* renamed from: c */
    public Drawable f392c;

    /* renamed from: d */
    public Drawable f393d;
    public boolean f;
    public boolean h;
    public Runnable i;
    public long j;
    public long k;
    public a l;

    /* renamed from: e */
    public int f394e = WebView.NORMAL_MODE_ALPHA;
    public int g = -1;

    /* loaded from: classes.dex */
    public static class a implements Drawable.Callback {

        /* renamed from: a */
        public Drawable.Callback f395a;

        @Override // android.graphics.drawable.Drawable.Callback
        public void invalidateDrawable(Drawable drawable) {
        }

        @Override // android.graphics.drawable.Drawable.Callback
        public void scheduleDrawable(Drawable drawable, Runnable runnable, long j) {
            Drawable.Callback callback = this.f395a;
            if (callback != null) {
                callback.scheduleDrawable(drawable, runnable, j);
            }
        }

        @Override // android.graphics.drawable.Drawable.Callback
        public void unscheduleDrawable(Drawable drawable, Runnable runnable) {
            Drawable.Callback callback = this.f395a;
            if (callback != null) {
                callback.unscheduleDrawable(drawable, runnable);
            }
        }
    }

    /* renamed from: a */
    public b mo0a() {
        throw null;
    }

    public void a(b bVar) {
        throw null;
    }

    /* JADX WARN: Removed duplicated region for block: B:33:0x006d  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x0075  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean a(int r10) {
        /*
            r9 = this;
            int r0 = r9.g
            r1 = 0
            if (r10 != r0) goto L_0x0006
            return r1
        L_0x0006:
            long r2 = android.os.SystemClock.uptimeMillis()
            c.a.c.a.d$b r0 = r9.f390a
            int r0 = r0.B
            r4 = 0
            r5 = 0
            if (r0 <= 0) goto L_0x0030
            android.graphics.drawable.Drawable r0 = r9.f393d
            if (r0 == 0) goto L_0x001a
            r0.setVisible(r1, r1)
        L_0x001a:
            android.graphics.drawable.Drawable r0 = r9.f392c
            if (r0 == 0) goto L_0x002b
            r9.f393d = r0
            int r0 = r9.g
            c.a.c.a.d$b r0 = r9.f390a
            int r0 = r0.B
            long r0 = (long) r0
            long r0 = r0 + r2
            r9.k = r0
            goto L_0x0037
        L_0x002b:
            r9.f393d = r4
            r9.k = r5
            goto L_0x0037
        L_0x0030:
            android.graphics.drawable.Drawable r0 = r9.f392c
            if (r0 == 0) goto L_0x0037
            r0.setVisible(r1, r1)
        L_0x0037:
            if (r10 < 0) goto L_0x0057
            c.a.c.a.d$b r0 = r9.f390a
            int r1 = r0.h
            if (r10 >= r1) goto L_0x0057
            android.graphics.drawable.Drawable r0 = r0.a(r10)
            r9.f392c = r0
            r9.g = r10
            if (r0 == 0) goto L_0x005c
            c.a.c.a.d$b r10 = r9.f390a
            int r10 = r10.A
            if (r10 <= 0) goto L_0x0053
            long r7 = (long) r10
            long r2 = r2 + r7
            r9.j = r2
        L_0x0053:
            r9.a(r0)
            goto L_0x005c
        L_0x0057:
            r9.f392c = r4
            r10 = -1
            r9.g = r10
        L_0x005c:
            long r0 = r9.j
            r10 = 1
            int r2 = (r0 > r5 ? 1 : (r0 == r5 ? 0 : -1))
            if (r2 != 0) goto L_0x0069
            long r0 = r9.k
            int r2 = (r0 > r5 ? 1 : (r0 == r5 ? 0 : -1))
            if (r2 == 0) goto L_0x007b
        L_0x0069:
            java.lang.Runnable r0 = r9.i
            if (r0 != 0) goto L_0x0075
            c.a.c.a.c r0 = new c.a.c.a.c
            r0.<init>(r9)
            r9.i = r0
            goto L_0x0078
        L_0x0075:
            r9.unscheduleSelf(r0)
        L_0x0078:
            r9.a(r10)
        L_0x007b:
            r9.invalidateSelf()
            return r10
        */
        throw new UnsupportedOperationException("Method not decompiled: c.a.c.a.d.a(int):boolean");
    }

    @Override // android.graphics.drawable.Drawable
    public boolean canApplyTheme() {
        b bVar = this.f390a;
        int i = bVar.h;
        Drawable[] drawableArr = bVar.g;
        for (int i2 = 0; i2 < i; i2++) {
            Drawable drawable = drawableArr[i2];
            if (drawable == null) {
                Drawable.ConstantState constantState = bVar.f.get(i2);
                if (constantState != null && constantState.canApplyTheme()) {
                    return true;
                }
            } else if (drawable.canApplyTheme()) {
                return true;
            }
        }
        return false;
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        Drawable drawable = this.f392c;
        if (drawable != null) {
            drawable.draw(canvas);
        }
        Drawable drawable2 = this.f393d;
        if (drawable2 != null) {
            drawable2.draw(canvas);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public int getAlpha() {
        return this.f394e;
    }

    @Override // android.graphics.drawable.Drawable
    public int getChangingConfigurations() {
        int changingConfigurations = super.getChangingConfigurations();
        b bVar = this.f390a;
        return changingConfigurations | bVar.f400e | bVar.f399d;
    }

    @Override // android.graphics.drawable.Drawable
    public final Drawable.ConstantState getConstantState() {
        if (!this.f390a.a()) {
            return null;
        }
        b bVar = this.f390a;
        int changingConfigurations = super.getChangingConfigurations();
        b bVar2 = this.f390a;
        bVar.f399d = changingConfigurations | bVar2.f399d | bVar2.f400e;
        return bVar2;
    }

    @Override // android.graphics.drawable.Drawable
    public Drawable getCurrent() {
        return this.f392c;
    }

    @Override // android.graphics.drawable.Drawable
    public void getHotspotBounds(Rect rect) {
        Rect rect2 = this.f391b;
        if (rect2 != null) {
            rect.set(rect2);
        } else {
            super.getHotspotBounds(rect);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicHeight() {
        b bVar = this.f390a;
        if (bVar.l) {
            if (!bVar.m) {
                bVar.b();
            }
            return bVar.o;
        }
        Drawable drawable = this.f392c;
        if (drawable != null) {
            return drawable.getIntrinsicHeight();
        }
        return -1;
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicWidth() {
        b bVar = this.f390a;
        if (bVar.l) {
            if (!bVar.m) {
                bVar.b();
            }
            return bVar.n;
        }
        Drawable drawable = this.f392c;
        if (drawable != null) {
            return drawable.getIntrinsicWidth();
        }
        return -1;
    }

    @Override // android.graphics.drawable.Drawable
    public int getMinimumHeight() {
        b bVar = this.f390a;
        if (bVar.l) {
            if (!bVar.m) {
                bVar.b();
            }
            return bVar.q;
        }
        Drawable drawable = this.f392c;
        if (drawable != null) {
            return drawable.getMinimumHeight();
        }
        return 0;
    }

    @Override // android.graphics.drawable.Drawable
    public int getMinimumWidth() {
        b bVar = this.f390a;
        if (bVar.l) {
            if (!bVar.m) {
                bVar.b();
            }
            return bVar.p;
        }
        Drawable drawable = this.f392c;
        if (drawable != null) {
            return drawable.getMinimumWidth();
        }
        return 0;
    }

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        Drawable drawable = this.f392c;
        int i = -2;
        if (drawable == null || !drawable.isVisible()) {
            return -2;
        }
        b bVar = this.f390a;
        if (bVar.r) {
            return bVar.s;
        }
        bVar.c();
        int i2 = bVar.h;
        Drawable[] drawableArr = bVar.g;
        if (i2 > 0) {
            i = drawableArr[0].getOpacity();
        }
        for (int i3 = 1; i3 < i2; i3++) {
            i = Drawable.resolveOpacity(i, drawableArr[i3].getOpacity());
        }
        bVar.s = i;
        bVar.r = true;
        return i;
    }

    @Override // android.graphics.drawable.Drawable
    public void getOutline(Outline outline) {
        Drawable drawable = this.f392c;
        if (drawable != null) {
            drawable.getOutline(outline);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public boolean getPadding(Rect rect) {
        boolean z;
        b bVar = this.f390a;
        Rect rect2 = null;
        boolean z2 = true;
        if (!bVar.i) {
            if (bVar.k != null || bVar.j) {
                rect2 = bVar.k;
            } else {
                bVar.c();
                Rect rect3 = new Rect();
                int i = bVar.h;
                Drawable[] drawableArr = bVar.g;
                Rect rect4 = null;
                for (int i2 = 0; i2 < i; i2++) {
                    if (drawableArr[i2].getPadding(rect3)) {
                        if (rect4 == null) {
                            rect4 = new Rect(0, 0, 0, 0);
                        }
                        int i3 = rect3.left;
                        if (i3 > rect4.left) {
                            rect4.left = i3;
                        }
                        int i4 = rect3.top;
                        if (i4 > rect4.top) {
                            rect4.top = i4;
                        }
                        int i5 = rect3.right;
                        if (i5 > rect4.right) {
                            rect4.right = i5;
                        }
                        int i6 = rect3.bottom;
                        if (i6 > rect4.bottom) {
                            rect4.bottom = i6;
                        }
                    }
                }
                bVar.j = true;
                bVar.k = rect4;
                rect2 = rect4;
            }
        }
        if (rect2 != null) {
            rect.set(rect2);
            z = (((rect2.left | rect2.top) | rect2.bottom) | rect2.right) != 0;
        } else {
            Drawable drawable = this.f392c;
            if (drawable != null) {
                z = drawable.getPadding(rect);
            } else {
                z = super.getPadding(rect);
            }
        }
        if (!this.f390a.C || getLayoutDirection() != 1) {
            z2 = false;
        }
        if (z2) {
            int i7 = rect.left;
            rect.left = rect.right;
            rect.right = i7;
        }
        return z;
    }

    @Override // android.graphics.drawable.Drawable.Callback
    public void invalidateDrawable(Drawable drawable) {
        b bVar = this.f390a;
        if (bVar != null) {
            bVar.r = false;
            bVar.t = false;
        }
        if (drawable == this.f392c && getCallback() != null) {
            getCallback().invalidateDrawable(this);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public boolean isAutoMirrored() {
        return this.f390a.C;
    }

    @Override // android.graphics.drawable.Drawable
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
    }

    @Override // android.graphics.drawable.Drawable
    public Drawable mutate() {
        if (!this.h && super.mutate() == this) {
            b a2 = mo0a();
            a2.d();
            a(a2);
            this.h = true;
        }
        return this;
    }

    @Override // android.graphics.drawable.Drawable
    public void onBoundsChange(Rect rect) {
        Drawable drawable = this.f393d;
        if (drawable != null) {
            drawable.setBounds(rect);
        }
        Drawable drawable2 = this.f392c;
        if (drawable2 != null) {
            drawable2.setBounds(rect);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public boolean onLayoutDirectionChanged(int i) {
        b bVar = this.f390a;
        int i2 = this.g;
        int i3 = bVar.h;
        Drawable[] drawableArr = bVar.g;
        boolean z = false;
        for (int i4 = 0; i4 < i3; i4++) {
            if (drawableArr[i4] != null) {
                z = Build.VERSION.SDK_INT >= 23 ? drawableArr[i4].setLayoutDirection(i) : false;
                if (i4 == i2) {
                }
            }
        }
        bVar.z = i;
        return z;
    }

    @Override // android.graphics.drawable.Drawable
    public boolean onLevelChange(int i) {
        Drawable drawable = this.f393d;
        if (drawable != null) {
            return drawable.setLevel(i);
        }
        Drawable drawable2 = this.f392c;
        if (drawable2 != null) {
            return drawable2.setLevel(i);
        }
        return false;
    }

    @Override // android.graphics.drawable.Drawable.Callback
    public void scheduleDrawable(Drawable drawable, Runnable runnable, long j) {
        if (drawable == this.f392c && getCallback() != null) {
            getCallback().scheduleDrawable(this, runnable, j);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int i) {
        if (!this.f || this.f394e != i) {
            this.f = true;
            this.f394e = i;
            Drawable drawable = this.f392c;
            if (drawable == null) {
                return;
            }
            if (this.j == 0) {
                drawable.setAlpha(i);
            } else {
                a(false);
            }
        }
    }

    @Override // android.graphics.drawable.Drawable
    public void setAutoMirrored(boolean z) {
        b bVar = this.f390a;
        if (bVar.C != z) {
            bVar.C = z;
            Drawable drawable = this.f392c;
            if (drawable != null) {
                boolean z2 = bVar.C;
                int i = Build.VERSION.SDK_INT;
                drawable.setAutoMirrored(z2);
            }
        }
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter colorFilter) {
        b bVar = this.f390a;
        bVar.E = true;
        if (bVar.D != colorFilter) {
            bVar.D = colorFilter;
            Drawable drawable = this.f392c;
            if (drawable != null) {
                drawable.setColorFilter(colorFilter);
            }
        }
    }

    @Override // android.graphics.drawable.Drawable
    public void setDither(boolean z) {
        b bVar = this.f390a;
        if (bVar.x != z) {
            bVar.x = z;
            Drawable drawable = this.f392c;
            if (drawable != null) {
                drawable.setDither(bVar.x);
            }
        }
    }

    @Override // android.graphics.drawable.Drawable
    public void setHotspot(float f, float f2) {
        Drawable drawable = this.f392c;
        if (drawable != null) {
            int i = Build.VERSION.SDK_INT;
            drawable.setHotspot(f, f2);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public void setHotspotBounds(int i, int i2, int i3, int i4) {
        Rect rect = this.f391b;
        if (rect == null) {
            this.f391b = new Rect(i, i2, i3, i4);
        } else {
            rect.set(i, i2, i3, i4);
        }
        Drawable drawable = this.f392c;
        if (drawable != null) {
            int i5 = Build.VERSION.SDK_INT;
            drawable.setHotspotBounds(i, i2, i3, i4);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public void setTintList(ColorStateList colorStateList) {
        b bVar = this.f390a;
        bVar.H = true;
        if (bVar.F != colorStateList) {
            bVar.F = colorStateList;
            Drawable drawable = this.f392c;
            int i = Build.VERSION.SDK_INT;
            drawable.setTintList(colorStateList);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public void setTintMode(PorterDuff.Mode mode) {
        b bVar = this.f390a;
        bVar.I = true;
        if (bVar.G != mode) {
            bVar.G = mode;
            Drawable drawable = this.f392c;
            int i = Build.VERSION.SDK_INT;
            drawable.setTintMode(mode);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public boolean setVisible(boolean z, boolean z2) {
        boolean visible = super.setVisible(z, z2);
        Drawable drawable = this.f393d;
        if (drawable != null) {
            drawable.setVisible(z, z2);
        }
        Drawable drawable2 = this.f392c;
        if (drawable2 != null) {
            drawable2.setVisible(z, z2);
        }
        return visible;
    }

    @Override // android.graphics.drawable.Drawable.Callback
    public void unscheduleDrawable(Drawable drawable, Runnable runnable) {
        if (drawable == this.f392c && getCallback() != null) {
            getCallback().unscheduleDrawable(this, runnable);
        }
    }

    /* loaded from: classes.dex */
    public static abstract class b extends Drawable.ConstantState {
        public int A;
        public int B;
        public boolean C;
        public ColorFilter D;
        public boolean E;
        public ColorStateList F;
        public PorterDuff.Mode G;
        public boolean H;
        public boolean I;

        /* renamed from: a */
        public final d f396a;

        /* renamed from: b */
        public Resources f397b;

        /* renamed from: c */
        public int f398c;

        /* renamed from: d */
        public int f399d;

        /* renamed from: e */
        public int f400e;
        public SparseArray<Drawable.ConstantState> f;
        public Drawable[] g;
        public int h;
        public boolean i;
        public boolean j;
        public Rect k;
        public boolean l;
        public boolean m;
        public int n;
        public int o;
        public int p;
        public int q;
        public boolean r;
        public int s;
        public boolean t;
        public boolean u;
        public boolean v;
        public boolean w;
        public boolean x;
        public boolean y;
        public int z;

        public b(b bVar, d dVar, Resources resources) {
            Resources resources2;
            this.f398c = TbsListener.ErrorCode.STARTDOWNLOAD_1;
            this.i = false;
            this.l = false;
            this.x = true;
            this.A = 0;
            this.B = 0;
            this.f396a = dVar;
            if (resources != null) {
                resources2 = resources;
            } else {
                resources2 = bVar != null ? bVar.f397b : null;
            }
            this.f397b = resources2;
            this.f398c = d.a(resources, bVar != null ? bVar.f398c : 0);
            if (bVar != null) {
                this.f399d = bVar.f399d;
                this.f400e = bVar.f400e;
                this.v = true;
                this.w = true;
                this.i = bVar.i;
                this.l = bVar.l;
                this.x = bVar.x;
                this.y = bVar.y;
                this.z = bVar.z;
                this.A = bVar.A;
                this.B = bVar.B;
                this.C = bVar.C;
                this.D = bVar.D;
                this.E = bVar.E;
                this.F = bVar.F;
                this.G = bVar.G;
                this.H = bVar.H;
                this.I = bVar.I;
                if (bVar.f398c == this.f398c) {
                    if (bVar.j) {
                        this.k = new Rect(bVar.k);
                        this.j = true;
                    }
                    if (bVar.m) {
                        this.n = bVar.n;
                        this.o = bVar.o;
                        this.p = bVar.p;
                        this.q = bVar.q;
                        this.m = true;
                    }
                }
                if (bVar.r) {
                    this.s = bVar.s;
                    this.r = true;
                }
                if (bVar.t) {
                    this.u = bVar.u;
                    this.t = true;
                }
                Drawable[] drawableArr = bVar.g;
                this.g = new Drawable[drawableArr.length];
                this.h = bVar.h;
                SparseArray<Drawable.ConstantState> sparseArray = bVar.f;
                if (sparseArray != null) {
                    this.f = sparseArray.clone();
                } else {
                    this.f = new SparseArray<>(this.h);
                }
                int i = this.h;
                for (int i2 = 0; i2 < i; i2++) {
                    if (drawableArr[i2] != null) {
                        Drawable.ConstantState constantState = drawableArr[i2].getConstantState();
                        if (constantState != null) {
                            this.f.put(i2, constantState);
                        } else {
                            this.g[i2] = drawableArr[i2];
                        }
                    }
                }
                return;
            }
            this.g = new Drawable[10];
            this.h = 0;
        }

        public final int a(Drawable drawable) {
            int i = this.h;
            if (i >= this.g.length) {
                int i2 = i + 10;
                f.a aVar = (f.a) this;
                Drawable[] drawableArr = new Drawable[i2];
                System.arraycopy(aVar.g, 0, drawableArr, 0, i);
                aVar.g = drawableArr;
                int[][] iArr = new int[i2];
                System.arraycopy(aVar.J, 0, iArr, 0, i);
                aVar.J = iArr;
            }
            drawable.mutate();
            drawable.setVisible(false, true);
            drawable.setCallback(this.f396a);
            this.g[i] = drawable;
            this.h++;
            this.f400e = drawable.getChangingConfigurations() | this.f400e;
            this.r = false;
            this.t = false;
            this.k = null;
            this.j = false;
            this.m = false;
            this.v = false;
            return i;
        }

        public void b() {
            this.m = true;
            c();
            int i = this.h;
            Drawable[] drawableArr = this.g;
            this.o = -1;
            this.n = -1;
            this.q = 0;
            this.p = 0;
            for (int i2 = 0; i2 < i; i2++) {
                Drawable drawable = drawableArr[i2];
                int intrinsicWidth = drawable.getIntrinsicWidth();
                if (intrinsicWidth > this.n) {
                    this.n = intrinsicWidth;
                }
                int intrinsicHeight = drawable.getIntrinsicHeight();
                if (intrinsicHeight > this.o) {
                    this.o = intrinsicHeight;
                }
                int minimumWidth = drawable.getMinimumWidth();
                if (minimumWidth > this.p) {
                    this.p = minimumWidth;
                }
                int minimumHeight = drawable.getMinimumHeight();
                if (minimumHeight > this.q) {
                    this.q = minimumHeight;
                }
            }
        }

        public final void c() {
            SparseArray<Drawable.ConstantState> sparseArray = this.f;
            if (sparseArray != null) {
                int size = sparseArray.size();
                for (int i = 0; i < size; i++) {
                    int keyAt = this.f.keyAt(i);
                    Drawable[] drawableArr = this.g;
                    Drawable newDrawable = this.f.valueAt(i).newDrawable(this.f397b);
                    if (Build.VERSION.SDK_INT >= 23) {
                        newDrawable.setLayoutDirection(this.z);
                    }
                    Drawable mutate = newDrawable.mutate();
                    mutate.setCallback(this.f396a);
                    drawableArr[keyAt] = mutate;
                }
                this.f = null;
            }
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public boolean canApplyTheme() {
            int i = this.h;
            Drawable[] drawableArr = this.g;
            for (int i2 = 0; i2 < i; i2++) {
                Drawable drawable = drawableArr[i2];
                if (drawable == null) {
                    Drawable.ConstantState constantState = this.f.get(i2);
                    if (constantState != null && constantState.canApplyTheme()) {
                        return true;
                    }
                } else if (drawable.canApplyTheme()) {
                    return true;
                }
            }
            return false;
        }

        public abstract void d();

        @Override // android.graphics.drawable.Drawable.ConstantState
        public int getChangingConfigurations() {
            return this.f399d | this.f400e;
        }

        public final Drawable a(int i) {
            int indexOfKey;
            Drawable drawable = this.g[i];
            if (drawable != null) {
                return drawable;
            }
            SparseArray<Drawable.ConstantState> sparseArray = this.f;
            if (sparseArray == null || (indexOfKey = sparseArray.indexOfKey(i)) < 0) {
                return null;
            }
            Drawable newDrawable = this.f.valueAt(indexOfKey).newDrawable(this.f397b);
            if (Build.VERSION.SDK_INT >= 23) {
                newDrawable.setLayoutDirection(this.z);
            }
            Drawable mutate = newDrawable.mutate();
            mutate.setCallback(this.f396a);
            this.g[i] = mutate;
            this.f.removeAt(indexOfKey);
            if (this.f.size() == 0) {
                this.f = null;
            }
            return mutate;
        }

        public final void a(Resources resources) {
            if (resources != null) {
                this.f397b = resources;
                int a2 = d.a(resources, this.f398c);
                int i = this.f398c;
                this.f398c = a2;
                if (i != a2) {
                    this.m = false;
                    this.j = false;
                }
            }
        }

        public final void a(Resources.Theme theme) {
            if (theme != null) {
                c();
                int i = this.h;
                Drawable[] drawableArr = this.g;
                for (int i2 = 0; i2 < i; i2++) {
                    if (drawableArr[i2] != null && drawableArr[i2].canApplyTheme()) {
                        drawableArr[i2].applyTheme(theme);
                        this.f400e |= drawableArr[i2].getChangingConfigurations();
                    }
                }
                a(theme.getResources());
            }
        }

        public synchronized boolean a() {
            if (this.v) {
                return this.w;
            }
            c();
            this.v = true;
            int i = this.h;
            Drawable[] drawableArr = this.g;
            for (int i2 = 0; i2 < i; i2++) {
                if (drawableArr[i2].getConstantState() == null) {
                    this.w = false;
                    return false;
                }
            }
            this.w = true;
            return true;
        }
    }

    public final void a(Drawable drawable) {
        if (this.l == null) {
            this.l = new a();
        }
        a aVar = this.l;
        aVar.f395a = drawable.getCallback();
        drawable.setCallback(aVar);
        try {
            if (this.f390a.A <= 0 && this.f) {
                drawable.setAlpha(this.f394e);
            }
            if (this.f390a.E) {
                drawable.setColorFilter(this.f390a.D);
            } else {
                if (this.f390a.H) {
                    ColorStateList colorStateList = this.f390a.F;
                    int i = Build.VERSION.SDK_INT;
                    drawable.setTintList(colorStateList);
                }
                if (this.f390a.I) {
                    PorterDuff.Mode mode = this.f390a.G;
                    int i2 = Build.VERSION.SDK_INT;
                    drawable.setTintMode(mode);
                }
            }
            drawable.setVisible(isVisible(), true);
            drawable.setDither(this.f390a.x);
            drawable.setState(getState());
            drawable.setLevel(getLevel());
            drawable.setBounds(getBounds());
            if (Build.VERSION.SDK_INT >= 23) {
                drawable.setLayoutDirection(getLayoutDirection());
            }
            int i3 = Build.VERSION.SDK_INT;
            drawable.setAutoMirrored(this.f390a.C);
            Rect rect = this.f391b;
            int i4 = Build.VERSION.SDK_INT;
            if (rect != null) {
                drawable.setHotspotBounds(rect.left, rect.top, rect.right, rect.bottom);
            }
        } finally {
            a aVar2 = this.l;
            Drawable.Callback callback = aVar2.f395a;
            aVar2.f395a = null;
            drawable.setCallback(callback);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x003f  */
    /* JADX WARN: Removed duplicated region for block: B:20:0x0065  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x006a A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:26:? A[ADDED_TO_REGION, RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void a(boolean r14) {
        /*
            r13 = this;
            r0 = 1
            r13.f = r0
            long r1 = android.os.SystemClock.uptimeMillis()
            android.graphics.drawable.Drawable r3 = r13.f392c
            r4 = 255(0xff, double:1.26E-321)
            r6 = 0
            r7 = 0
            if (r3 == 0) goto L_0x0038
            long r9 = r13.j
            int r11 = (r9 > r7 ? 1 : (r9 == r7 ? 0 : -1))
            if (r11 == 0) goto L_0x003a
            int r11 = (r9 > r1 ? 1 : (r9 == r1 ? 0 : -1))
            if (r11 > 0) goto L_0x0022
            int r9 = r13.f394e
            r3.setAlpha(r9)
            r13.j = r7
            goto L_0x003a
        L_0x0022:
            long r9 = r9 - r1
            long r9 = r9 * r4
            int r10 = (int) r9
            c.a.c.a.d$b r9 = r13.f390a
            int r9 = r9.A
            int r10 = r10 / r9
            int r9 = 255 - r10
            int r10 = r13.f394e
            int r9 = r9 * r10
            int r9 = r9 / 255
            r3.setAlpha(r9)
            r3 = 1
            goto L_0x003b
        L_0x0038:
            r13.j = r7
        L_0x003a:
            r3 = 0
        L_0x003b:
            android.graphics.drawable.Drawable r9 = r13.f393d
            if (r9 == 0) goto L_0x0065
            long r10 = r13.k
            int r12 = (r10 > r7 ? 1 : (r10 == r7 ? 0 : -1))
            if (r12 == 0) goto L_0x0067
            int r12 = (r10 > r1 ? 1 : (r10 == r1 ? 0 : -1))
            if (r12 > 0) goto L_0x0052
            r9.setVisible(r6, r6)
            r0 = 0
            r13.f393d = r0
            r13.k = r7
            goto L_0x0067
        L_0x0052:
            long r10 = r10 - r1
            long r10 = r10 * r4
            int r3 = (int) r10
            c.a.c.a.d$b r4 = r13.f390a
            int r4 = r4.B
            int r3 = r3 / r4
            int r4 = r13.f394e
            int r3 = r3 * r4
            int r3 = r3 / 255
            r9.setAlpha(r3)
            goto L_0x0068
        L_0x0065:
            r13.k = r7
        L_0x0067:
            r0 = r3
        L_0x0068:
            if (r14 == 0) goto L_0x0074
            if (r0 == 0) goto L_0x0074
            java.lang.Runnable r14 = r13.i
            r3 = 16
            long r1 = r1 + r3
            r13.scheduleSelf(r14, r1)
        L_0x0074:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: c.a.c.a.d.a(boolean):void");
    }

    public static int a(Resources resources, int i) {
        if (resources != null) {
            i = resources.getDisplayMetrics().densityDpi;
        }
        return i == 0 ? TbsListener.ErrorCode.STARTDOWNLOAD_1 : i;
    }
}
