package androidx.appcompat.widget;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.widget.FrameLayout;
import androidx.appcompat.app.AppCompatDelegateImpl;
import c.a.a.t;
import c.a.e.a.l;
import c.a.f.C;
import c.e.h.n;

/* loaded from: classes.dex */
public class ContentFrameLayout extends FrameLayout {

    /* renamed from: a */
    public TypedValue f132a;

    /* renamed from: b */
    public TypedValue f133b;

    /* renamed from: c */
    public TypedValue f134c;

    /* renamed from: d */
    public TypedValue f135d;

    /* renamed from: e */
    public TypedValue f136e;
    public TypedValue f;
    public final Rect g = new Rect();
    public a h;

    /* loaded from: classes.dex */
    public interface a {
    }

    public ContentFrameLayout(Context context) {
        super(context, null, 0);
    }

    public void a(Rect rect) {
        fitSystemWindows(rect);
    }

    public TypedValue getFixedHeightMajor() {
        if (this.f136e == null) {
            this.f136e = new TypedValue();
        }
        return this.f136e;
    }

    public TypedValue getFixedHeightMinor() {
        if (this.f == null) {
            this.f = new TypedValue();
        }
        return this.f;
    }

    public TypedValue getFixedWidthMajor() {
        if (this.f134c == null) {
            this.f134c = new TypedValue();
        }
        return this.f134c;
    }

    public TypedValue getFixedWidthMinor() {
        if (this.f135d == null) {
            this.f135d = new TypedValue();
        }
        return this.f135d;
    }

    public TypedValue getMinWidthMajor() {
        if (this.f132a == null) {
            this.f132a = new TypedValue();
        }
        return this.f132a;
    }

    public TypedValue getMinWidthMinor() {
        if (this.f133b == null) {
            this.f133b = new TypedValue();
        }
        return this.f133b;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        a aVar = this.h;
        if (aVar != null) {
            ((t) aVar).a();
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        a aVar = this.h;
        if (aVar != null) {
            AppCompatDelegateImpl appCompatDelegateImpl = ((t) aVar).f371a;
            C c2 = appCompatDelegateImpl.m;
            if (c2 != null) {
                c2.b();
            }
            if (appCompatDelegateImpl.r != null) {
                appCompatDelegateImpl.f.getDecorView().removeCallbacks(appCompatDelegateImpl.s);
                if (appCompatDelegateImpl.r.isShowing()) {
                    try {
                        appCompatDelegateImpl.r.dismiss();
                    } catch (IllegalArgumentException unused) {
                    }
                }
                appCompatDelegateImpl.r = null;
            }
            appCompatDelegateImpl.d();
            l lVar = appCompatDelegateImpl.a(0, false).j;
            if (lVar != null) {
                lVar.a(true);
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:21:0x004a  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x0065  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x0088  */
    /* JADX WARN: Removed duplicated region for block: B:42:0x00ad  */
    /* JADX WARN: Removed duplicated region for block: B:43:0x00b0  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x00ba  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x00c0  */
    /* JADX WARN: Removed duplicated region for block: B:54:0x00ce  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x00d8  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x00e0  */
    /* JADX WARN: Removed duplicated region for block: B:61:? A[RETURN, SYNTHETIC] */
    @Override // android.widget.FrameLayout, android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void onMeasure(int r14, int r15) {
        /*
            Method dump skipped, instructions count: 228
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.widget.ContentFrameLayout.onMeasure(int, int):void");
    }

    public void setAttachListener(a aVar) {
        this.h = aVar;
    }

    public void a(int i, int i2, int i3, int i4) {
        this.g.set(i, i2, i3, i4);
        if (n.p(this)) {
            requestLayout();
        }
    }

    public ContentFrameLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet, 0);
    }

    public ContentFrameLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }
}
