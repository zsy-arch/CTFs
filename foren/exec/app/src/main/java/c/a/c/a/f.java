package c.a.c.a;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.util.StateSet;
import c.a.c.a.d;

/* loaded from: classes.dex */
public class f extends d {
    public a m;
    public boolean n;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static class a extends d.b {
        public int[][] J;

        public a(a aVar, f fVar, Resources resources) {
            super(aVar, fVar, resources);
            if (aVar != null) {
                this.J = aVar.J;
            } else {
                this.J = new int[this.g.length];
            }
        }

        public int a(int[] iArr) {
            int[][] iArr2 = this.J;
            int i = this.h;
            for (int i2 = 0; i2 < i; i2++) {
                if (StateSet.stateSetMatches(iArr2[i2], iArr)) {
                    return i2;
                }
            }
            return -1;
        }

        @Override // c.a.c.a.d.b
        public void d() {
            int[][] iArr = this.J;
            int[][] iArr2 = new int[iArr.length];
            for (int length = iArr.length - 1; length >= 0; length--) {
                int[][] iArr3 = this.J;
                iArr2[length] = iArr3[length] != null ? (int[]) iArr3[length].clone() : null;
            }
            this.J = iArr2;
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public Drawable newDrawable() {
            return new f(this, null);
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public Drawable newDrawable(Resources resources) {
            return new f(this, resources);
        }
    }

    public f(a aVar, Resources resources) {
        a(new a(aVar, this, resources));
        onStateChange(getState());
    }

    @Override // android.graphics.drawable.Drawable
    public void applyTheme(Resources.Theme theme) {
        this.f390a.a(theme);
        onStateChange(getState());
    }

    @Override // android.graphics.drawable.Drawable
    public boolean isStateful() {
        return true;
    }

    @Override // c.a.c.a.d, android.graphics.drawable.Drawable
    public Drawable mutate() {
        if (!this.n) {
            super.mutate();
            if (this == this) {
                this.m.d();
                this.n = true;
            }
        }
        return this;
    }

    @Override // android.graphics.drawable.Drawable
    public boolean onStateChange(int[] iArr) {
        boolean z;
        Drawable drawable = this.f393d;
        if (drawable != null) {
            z = drawable.setState(iArr);
        } else {
            Drawable drawable2 = this.f392c;
            z = drawable2 != null ? drawable2.setState(iArr) : false;
        }
        int a2 = this.m.a(iArr);
        if (a2 < 0) {
            a2 = this.m.a(StateSet.WILD_CARD);
        }
        return a(a2) || z;
    }

    @Override // c.a.c.a.d
    /* renamed from: a */
    public a mo0a() {
        return new a(this.m, this, null);
    }

    @Override // c.a.c.a.d
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
        if (bVar instanceof a) {
            this.m = (a) bVar;
        }
    }

    public f(a aVar) {
        if (aVar != null) {
            a(aVar);
        }
    }
}
