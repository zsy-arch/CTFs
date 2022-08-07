package androidx.coordinatorlayout.widget;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Region;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import androidx.customview.view.AbsSavedState;
import c.a.a.C;
import c.e.h.i;
import c.e.h.j;
import c.e.h.n;
import c.e.h.v;
import com.tencent.smtt.sdk.WebView;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/* loaded from: classes.dex */
public class CoordinatorLayout extends ViewGroup implements c.e.h.g {

    /* renamed from: a */
    public static final String f187a;

    /* renamed from: b */
    public static final Class<?>[] f188b;

    /* renamed from: c */
    public static final ThreadLocal<Map<String, Constructor<b>>> f189c;

    /* renamed from: d */
    public static final Comparator<View> f190d;

    /* renamed from: e */
    public static final c.e.g.b<Rect> f191e;
    public final List<View> f;
    public final c.d.a.c<View> g;
    public final List<View> h;
    public final int[] i;
    public Paint j;
    public boolean k;
    public boolean l;
    public int[] m;
    public View n;
    public View o;
    public f p;
    public boolean q;
    public v r;
    public boolean s;
    public Drawable t;
    public ViewGroup.OnHierarchyChangeListener u;
    public j v;
    public final i w;

    /* loaded from: classes.dex */
    public interface a {
        b a();
    }

    /* loaded from: classes.dex */
    public static abstract class b<V extends View> {
        public v a(CoordinatorLayout coordinatorLayout, V v, v vVar) {
            return vVar;
        }

        public void a() {
        }

        public void a(e eVar) {
        }

        public void a(CoordinatorLayout coordinatorLayout, V v, Parcelable parcelable) {
        }

        @Deprecated
        public void a(CoordinatorLayout coordinatorLayout, V v, View view, int i, int i2, int i3, int i4) {
        }

        @Deprecated
        public void a(CoordinatorLayout coordinatorLayout, V v, View view, int i, int i2, int[] iArr) {
        }

        @Deprecated
        public void a(CoordinatorLayout coordinatorLayout, V v, View view, View view2, int i) {
        }

        public boolean a(CoordinatorLayout coordinatorLayout, V v) {
            return c(coordinatorLayout, v) > 0.0f;
        }

        public boolean a(CoordinatorLayout coordinatorLayout, V v, int i) {
            return false;
        }

        public boolean a(CoordinatorLayout coordinatorLayout, V v, int i, int i2, int i3, int i4) {
            return false;
        }

        public boolean a(CoordinatorLayout coordinatorLayout, V v, Rect rect) {
            return false;
        }

        public boolean a(CoordinatorLayout coordinatorLayout, V v, Rect rect, boolean z) {
            return false;
        }

        public boolean a(CoordinatorLayout coordinatorLayout, V v, MotionEvent motionEvent) {
            return false;
        }

        public boolean a(CoordinatorLayout coordinatorLayout, V v, View view) {
            return false;
        }

        public boolean a(CoordinatorLayout coordinatorLayout, V v, View view, float f, float f2) {
            return false;
        }

        public boolean a(CoordinatorLayout coordinatorLayout, V v, View view, float f, float f2, boolean z) {
            return false;
        }

        public int b(CoordinatorLayout coordinatorLayout, V v) {
            return WebView.NIGHT_MODE_COLOR;
        }

        public boolean b(CoordinatorLayout coordinatorLayout, V v, MotionEvent motionEvent) {
            return false;
        }

        public boolean b(CoordinatorLayout coordinatorLayout, V v, View view) {
            return false;
        }

        @Deprecated
        public boolean b(CoordinatorLayout coordinatorLayout, V v, View view, View view2, int i) {
            return false;
        }

        public boolean b(CoordinatorLayout coordinatorLayout, V v, View view, View view2, int i, int i2) {
            if (i2 == 0) {
                return b(coordinatorLayout, v, view, view2, i);
            }
            return false;
        }

        public float c(CoordinatorLayout coordinatorLayout, V v) {
            return 0.0f;
        }

        public void c(CoordinatorLayout coordinatorLayout, V v, View view) {
        }

        public Parcelable d(CoordinatorLayout coordinatorLayout, V v) {
            return View.BaseSavedState.EMPTY_STATE;
        }

        @Deprecated
        public void d(CoordinatorLayout coordinatorLayout, V v, View view) {
        }

        public void a(CoordinatorLayout coordinatorLayout, V v, View view, View view2, int i, int i2) {
            if (i2 == 0) {
                a(coordinatorLayout, (CoordinatorLayout) v, view, view2, i);
            }
        }

        public void a(CoordinatorLayout coordinatorLayout, V v, View view, int i) {
            if (i == 0) {
                d(coordinatorLayout, v, view);
            }
        }

        public void a(CoordinatorLayout coordinatorLayout, V v, View view, int i, int i2, int i3, int i4, int i5) {
            if (i5 == 0) {
                a(coordinatorLayout, (CoordinatorLayout) v, view, i, i2, i3, i4);
            }
        }

        public void a(CoordinatorLayout coordinatorLayout, V v, View view, int i, int i2, int[] iArr, int i3) {
            if (i3 == 0) {
                a(coordinatorLayout, (CoordinatorLayout) v, view, i, i2, iArr);
            }
        }
    }

    @Retention(RetentionPolicy.RUNTIME)
    @Deprecated
    /* loaded from: classes.dex */
    public @interface c {
        Class<? extends b> value();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public class d implements ViewGroup.OnHierarchyChangeListener {
        public d() {
            CoordinatorLayout.this = r1;
        }

        @Override // android.view.ViewGroup.OnHierarchyChangeListener
        public void onChildViewAdded(View view, View view2) {
            ViewGroup.OnHierarchyChangeListener onHierarchyChangeListener = CoordinatorLayout.this.u;
            if (onHierarchyChangeListener != null) {
                onHierarchyChangeListener.onChildViewAdded(view, view2);
            }
        }

        @Override // android.view.ViewGroup.OnHierarchyChangeListener
        public void onChildViewRemoved(View view, View view2) {
            CoordinatorLayout.this.b(2);
            ViewGroup.OnHierarchyChangeListener onHierarchyChangeListener = CoordinatorLayout.this.u;
            if (onHierarchyChangeListener != null) {
                onHierarchyChangeListener.onChildViewRemoved(view, view2);
            }
        }
    }

    /* loaded from: classes.dex */
    public class f implements ViewTreeObserver.OnPreDrawListener {
        public f() {
            CoordinatorLayout.this = r1;
        }

        @Override // android.view.ViewTreeObserver.OnPreDrawListener
        public boolean onPreDraw() {
            CoordinatorLayout.this.b(0);
            return true;
        }
    }

    /* loaded from: classes.dex */
    static class g implements Comparator<View> {
        @Override // java.util.Comparator
        public int compare(View view, View view2) {
            float l = n.l(view);
            float l2 = n.l(view2);
            if (l > l2) {
                return -1;
            }
            return l < l2 ? 1 : 0;
        }
    }

    static {
        Package r0 = CoordinatorLayout.class.getPackage();
        f187a = r0 != null ? r0.getName() : null;
        int i = Build.VERSION.SDK_INT;
        f190d = new g();
        f188b = new Class[]{Context.class, AttributeSet.class};
        f189c = new ThreadLocal<>();
        f191e = new c.e.g.c(12);
    }

    public CoordinatorLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, c.d.a.coordinatorLayoutStyle);
    }

    public static Rect a() {
        Rect a2 = f191e.a();
        return a2 == null ? new Rect() : a2;
    }

    public static int c(int i) {
        if ((i & 7) == 0) {
            i |= 8388611;
        }
        return (i & 112) == 0 ? i | 48 : i;
    }

    public static int d(int i) {
        if (i == 0) {
            return 8388661;
        }
        return i;
    }

    public void b(View view, Rect rect) {
        rect.set(((e) view.getLayoutParams()).q);
    }

    public void c(View view, int i) {
        Rect a2;
        Rect a3;
        e eVar = (e) view.getLayoutParams();
        int i2 = 0;
        if (!(eVar.k == null && eVar.f != -1)) {
            View view2 = eVar.k;
            if (view2 != null) {
                a2 = a();
                a3 = a();
                try {
                    a(view2, a2);
                    a(view, i, a2, a3);
                    view.layout(a3.left, a3.top, a3.right, a3.bottom);
                } finally {
                    a2.setEmpty();
                    f191e.a(a2);
                    a3.setEmpty();
                    f191e.a(a3);
                }
            } else {
                int i3 = eVar.f198e;
                if (i3 >= 0) {
                    e eVar2 = (e) view.getLayoutParams();
                    int a4 = C.a(d(eVar2.f196c), i);
                    int i4 = a4 & 7;
                    int i5 = a4 & 112;
                    int width = getWidth();
                    int height = getHeight();
                    int measuredWidth = view.getMeasuredWidth();
                    int measuredHeight = view.getMeasuredHeight();
                    if (i == 1) {
                        i3 = width - i3;
                    }
                    int a5 = a(i3) - measuredWidth;
                    if (i4 == 1) {
                        a5 += measuredWidth / 2;
                    } else if (i4 == 5) {
                        a5 += measuredWidth;
                    }
                    if (i5 == 16) {
                        i2 = 0 + (measuredHeight / 2);
                    } else if (i5 == 80) {
                        i2 = measuredHeight + 0;
                    }
                    int max = Math.max(getPaddingLeft() + ((ViewGroup.MarginLayoutParams) eVar2).leftMargin, Math.min(a5, ((width - getPaddingRight()) - measuredWidth) - ((ViewGroup.MarginLayoutParams) eVar2).rightMargin));
                    int max2 = Math.max(getPaddingTop() + ((ViewGroup.MarginLayoutParams) eVar2).topMargin, Math.min(i2, ((height - getPaddingBottom()) - measuredHeight) - ((ViewGroup.MarginLayoutParams) eVar2).bottomMargin));
                    view.layout(max, max2, measuredWidth + max, measuredHeight + max2);
                    return;
                }
                e eVar3 = (e) view.getLayoutParams();
                a2 = a();
                a2.set(getPaddingLeft() + ((ViewGroup.MarginLayoutParams) eVar3).leftMargin, getPaddingTop() + ((ViewGroup.MarginLayoutParams) eVar3).topMargin, (getWidth() - getPaddingRight()) - ((ViewGroup.MarginLayoutParams) eVar3).rightMargin, (getHeight() - getPaddingBottom()) - ((ViewGroup.MarginLayoutParams) eVar3).bottomMargin);
                if (this.r != null && n.e(this) && !n.e(view)) {
                    a2.left = this.r.b() + a2.left;
                    a2.top = this.r.d() + a2.top;
                    a2.right -= this.r.c();
                    a2.bottom -= this.r.a();
                }
                a3 = a();
                int c2 = c(eVar3.f196c);
                int measuredWidth2 = view.getMeasuredWidth();
                int measuredHeight2 = view.getMeasuredHeight();
                int i6 = Build.VERSION.SDK_INT;
                Gravity.apply(c2, measuredWidth2, measuredHeight2, a2, a3, i);
                view.layout(a3.left, a3.top, a3.right, a3.bottom);
            }
        } else {
            throw new IllegalStateException("An anchor may not be changed after CoordinatorLayout measurement begins before layout is complete.");
        }
    }

    @Override // android.view.ViewGroup
    public boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return (layoutParams instanceof e) && super.checkLayoutParams(layoutParams);
    }

    /* JADX WARN: Code restructure failed: missing block: B:28:0x0073, code lost:
        if (r5 != false) goto L_0x00cc;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:128:0x016c A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:74:0x0112  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void d() {
        /*
            Method dump skipped, instructions count: 456
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.coordinatorlayout.widget.CoordinatorLayout.d():void");
    }

    @Override // android.view.ViewGroup
    public boolean drawChild(Canvas canvas, View view, long j) {
        e eVar = (e) view.getLayoutParams();
        b bVar = eVar.f194a;
        if (bVar != null) {
            float c2 = bVar.c(this, view);
            if (c2 > 0.0f) {
                if (this.j == null) {
                    this.j = new Paint();
                }
                this.j.setColor(eVar.f194a.b(this, view));
                Paint paint = this.j;
                int round = Math.round(c2 * 255.0f);
                if (round < 0) {
                    round = 0;
                } else if (round > 255) {
                    round = WebView.NORMAL_MODE_ALPHA;
                }
                paint.setAlpha(round);
                int save = canvas.save();
                if (view.isOpaque()) {
                    canvas.clipRect(view.getLeft(), view.getTop(), view.getRight(), view.getBottom(), Region.Op.DIFFERENCE);
                }
                canvas.drawRect(getPaddingLeft(), getPaddingTop(), getWidth() - getPaddingRight(), getHeight() - getPaddingBottom(), this.j);
                canvas.restoreToCount(save);
            }
        }
        return super.drawChild(canvas, view, j);
    }

    @Override // android.view.ViewGroup, android.view.View
    public void drawableStateChanged() {
        super.drawableStateChanged();
        int[] drawableState = getDrawableState();
        Drawable drawable = this.t;
        boolean z = false;
        if (drawable != null && drawable.isStateful()) {
            z = false | drawable.setState(drawableState);
        }
        if (z) {
            invalidate();
        }
    }

    public final void e(View view, int i) {
        e eVar = (e) view.getLayoutParams();
        int i2 = eVar.j;
        if (i2 != i) {
            n.d(view, i - i2);
            eVar.j = i;
        }
    }

    public final void f() {
        int i = Build.VERSION.SDK_INT;
        if (n.e(this)) {
            if (this.v == null) {
                this.v = new c.d.a.a(this);
            }
            n.a(this, this.v);
            setSystemUiVisibility(1280);
            return;
        }
        n.a(this, (j) null);
    }

    public final List<View> getDependencySortedChildren() {
        d();
        return Collections.unmodifiableList(this.f);
    }

    public final v getLastWindowInsets() {
        return this.r;
    }

    @Override // android.view.ViewGroup
    public int getNestedScrollAxes() {
        return this.w.f860a;
    }

    public Drawable getStatusBarBackground() {
        return this.t;
    }

    @Override // android.view.View
    public int getSuggestedMinimumHeight() {
        return Math.max(super.getSuggestedMinimumHeight(), getPaddingBottom() + getPaddingTop());
    }

    @Override // android.view.View
    public int getSuggestedMinimumWidth() {
        return Math.max(super.getSuggestedMinimumWidth(), getPaddingRight() + getPaddingLeft());
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        a(false);
        if (this.q) {
            if (this.p == null) {
                this.p = new f();
            }
            getViewTreeObserver().addOnPreDrawListener(this.p);
        }
        if (this.r == null && n.e(this)) {
            n.r(this);
        }
        this.l = true;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        a(false);
        if (this.q && this.p != null) {
            getViewTreeObserver().removeOnPreDrawListener(this.p);
        }
        View view = this.o;
        if (view != null) {
            onStopNestedScroll(view);
        }
        this.l = false;
    }

    @Override // android.view.View
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (this.s && this.t != null) {
            v vVar = this.r;
            int d2 = vVar != null ? vVar.d() : 0;
            if (d2 > 0) {
                this.t.setBounds(0, 0, getWidth(), d2);
                this.t.draw(canvas);
            }
        }
    }

    @Override // android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 0) {
            a(true);
        }
        boolean a2 = a(motionEvent, 0);
        if (actionMasked == 1 || actionMasked == 3) {
            a(true);
        }
        return a2;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        b bVar;
        int g2 = n.g(this);
        int size = this.f.size();
        for (int i5 = 0; i5 < size; i5++) {
            View view = this.f.get(i5);
            if (view.getVisibility() != 8 && ((bVar = ((e) view.getLayoutParams()).f194a) == null || !bVar.a(this, (CoordinatorLayout) view, g2))) {
                c(view, g2);
            }
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:47:0x0118, code lost:
        if (r0.a(r30, (androidx.coordinatorlayout.widget.CoordinatorLayout) r20, r11, r21, r23, 0) == false) goto L_0x0127;
     */
    /* JADX WARN: Removed duplicated region for block: B:46:0x00fa  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x011b  */
    @Override // android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void onMeasure(int r31, int r32) {
        /*
            Method dump skipped, instructions count: 388
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.coordinatorlayout.widget.CoordinatorLayout.onMeasure(int, int):void");
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public boolean onNestedFling(View view, float f2, float f3, boolean z) {
        b bVar;
        int childCount = getChildCount();
        boolean z2 = false;
        for (int i = 0; i < childCount; i++) {
            View childAt = getChildAt(i);
            if (childAt.getVisibility() != 8) {
                e eVar = (e) childAt.getLayoutParams();
                if (eVar.a(0) && (bVar = eVar.f194a) != null) {
                    z2 |= bVar.a(this, (CoordinatorLayout) childAt, view, f2, f3, z);
                }
            }
        }
        if (z2) {
            b(1);
        }
        return z2;
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public boolean onNestedPreFling(View view, float f2, float f3) {
        b bVar;
        int childCount = getChildCount();
        boolean z = false;
        for (int i = 0; i < childCount; i++) {
            View childAt = getChildAt(i);
            if (childAt.getVisibility() != 8) {
                e eVar = (e) childAt.getLayoutParams();
                if (eVar.a(0) && (bVar = eVar.f194a) != null) {
                    z |= bVar.a(this, (CoordinatorLayout) childAt, view, f2, f3);
                }
            }
        }
        return z;
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public void onNestedPreScroll(View view, int i, int i2, int[] iArr) {
        a(view, i, i2, iArr, 0);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public void onNestedScroll(View view, int i, int i2, int i3, int i4) {
        a(view, i, i2, i3, i4, 0);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public void onNestedScrollAccepted(View view, View view2, int i) {
        b(view, view2, i, 0);
    }

    @Override // android.view.View
    public void onRestoreInstanceState(Parcelable parcelable) {
        Parcelable parcelable2;
        if (!(parcelable instanceof SavedState)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        SavedState savedState = (SavedState) parcelable;
        super.onRestoreInstanceState(savedState.a());
        SparseArray<Parcelable> sparseArray = savedState.f192a;
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = getChildAt(i);
            int id = childAt.getId();
            b bVar = a(childAt).f194a;
            if (!(id == -1 || bVar == null || (parcelable2 = sparseArray.get(id)) == null)) {
                bVar.a(this, (CoordinatorLayout) childAt, parcelable2);
            }
        }
    }

    @Override // android.view.View
    public Parcelable onSaveInstanceState() {
        Parcelable d2;
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        SparseArray<Parcelable> sparseArray = new SparseArray<>();
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = getChildAt(i);
            int id = childAt.getId();
            b bVar = ((e) childAt.getLayoutParams()).f194a;
            if (!(id == -1 || bVar == null || (d2 = bVar.d(this, childAt)) == null)) {
                sparseArray.append(id, d2);
            }
        }
        savedState.f192a = sparseArray;
        return savedState;
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public boolean onStartNestedScroll(View view, View view2, int i) {
        return a(view, view2, i, 0);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public void onStopNestedScroll(View view) {
        a(view, 0);
    }

    /* JADX WARN: Code restructure failed: missing block: B:5:0x0012, code lost:
        if (r3 != false) goto L_0x0016;
     */
    /* JADX WARN: Removed duplicated region for block: B:13:0x002f  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0035  */
    /* JADX WARN: Removed duplicated region for block: B:17:0x004a  */
    @Override // android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean onTouchEvent(android.view.MotionEvent r18) {
        /*
            r17 = this;
            r0 = r17
            r1 = r18
            int r2 = r18.getActionMasked()
            android.view.View r3 = r0.n
            r4 = 1
            r5 = 0
            if (r3 != 0) goto L_0x0015
            boolean r3 = r0.a(r1, r4)
            if (r3 == 0) goto L_0x0029
            goto L_0x0016
        L_0x0015:
            r3 = 0
        L_0x0016:
            android.view.View r6 = r0.n
            android.view.ViewGroup$LayoutParams r6 = r6.getLayoutParams()
            androidx.coordinatorlayout.widget.CoordinatorLayout$e r6 = (androidx.coordinatorlayout.widget.CoordinatorLayout.e) r6
            androidx.coordinatorlayout.widget.CoordinatorLayout$b r6 = r6.f194a
            if (r6 == 0) goto L_0x0029
            android.view.View r7 = r0.n
            boolean r6 = r6.b(r0, r7, r1)
            goto L_0x002a
        L_0x0029:
            r6 = 0
        L_0x002a:
            android.view.View r7 = r0.n
            r8 = 0
            if (r7 != 0) goto L_0x0035
            boolean r1 = super.onTouchEvent(r18)
            r6 = r6 | r1
            goto L_0x0048
        L_0x0035:
            if (r3 == 0) goto L_0x0048
            long r11 = android.os.SystemClock.uptimeMillis()
            r13 = 3
            r14 = 0
            r15 = 0
            r16 = 0
            r9 = r11
            android.view.MotionEvent r8 = android.view.MotionEvent.obtain(r9, r11, r13, r14, r15, r16)
            super.onTouchEvent(r8)
        L_0x0048:
            if (r8 == 0) goto L_0x004d
            r8.recycle()
        L_0x004d:
            if (r2 == r4) goto L_0x0052
            r1 = 3
            if (r2 != r1) goto L_0x0055
        L_0x0052:
            r0.a(r5)
        L_0x0055:
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.coordinatorlayout.widget.CoordinatorLayout.onTouchEvent(android.view.MotionEvent):boolean");
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public boolean requestChildRectangleOnScreen(View view, Rect rect, boolean z) {
        b bVar = ((e) view.getLayoutParams()).f194a;
        if (bVar == null || !bVar.a(this, (CoordinatorLayout) view, rect, z)) {
            return super.requestChildRectangleOnScreen(view, rect, z);
        }
        return true;
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public void requestDisallowInterceptTouchEvent(boolean z) {
        super.requestDisallowInterceptTouchEvent(z);
        if (z && !this.k) {
            a(false);
            this.k = true;
        }
    }

    @Override // android.view.View
    public void setFitsSystemWindows(boolean z) {
        super.setFitsSystemWindows(z);
        f();
    }

    @Override // android.view.ViewGroup
    public void setOnHierarchyChangeListener(ViewGroup.OnHierarchyChangeListener onHierarchyChangeListener) {
        this.u = onHierarchyChangeListener;
    }

    public void setStatusBarBackground(Drawable drawable) {
        Drawable drawable2 = this.t;
        if (drawable2 != drawable) {
            Drawable drawable3 = null;
            if (drawable2 != null) {
                drawable2.setCallback(null);
            }
            if (drawable != null) {
                drawable3 = drawable.mutate();
            }
            this.t = drawable3;
            Drawable drawable4 = this.t;
            if (drawable4 != null) {
                if (drawable4.isStateful()) {
                    this.t.setState(getDrawableState());
                }
                C.a(this.t, n.g(this));
                this.t.setVisible(getVisibility() == 0, false);
                this.t.setCallback(this);
            }
            n.q(this);
        }
    }

    public void setStatusBarBackgroundColor(int i) {
        setStatusBarBackground(new ColorDrawable(i));
    }

    public void setStatusBarBackgroundResource(int i) {
        setStatusBarBackground(i != 0 ? c.e.b.a.b(getContext(), i) : null);
    }

    @Override // android.view.View
    public void setVisibility(int i) {
        super.setVisibility(i);
        boolean z = i == 0;
        Drawable drawable = this.t;
        if (drawable != null && drawable.isVisible() != z) {
            this.t.setVisible(z, false);
        }
    }

    @Override // android.view.View
    public boolean verifyDrawable(Drawable drawable) {
        return super.verifyDrawable(drawable) || drawable == this.t;
    }

    /* loaded from: classes.dex */
    public static class e extends ViewGroup.MarginLayoutParams {

        /* renamed from: a */
        public b f194a;

        /* renamed from: b */
        public boolean f195b;

        /* renamed from: c */
        public int f196c;

        /* renamed from: d */
        public int f197d;

        /* renamed from: e */
        public int f198e;
        public int f;
        public int g;
        public int h;
        public int i;
        public int j;
        public View k;
        public View l;
        public boolean m;
        public boolean n;
        public boolean o;
        public boolean p;
        public final Rect q;

        public e(int i, int i2) {
            super(i, i2);
            this.f195b = false;
            this.f196c = 0;
            this.f197d = 0;
            this.f198e = -1;
            this.f = -1;
            this.g = 0;
            this.h = 0;
            this.q = new Rect();
        }

        public void a(int i, boolean z) {
            if (i == 0) {
                this.n = z;
            } else if (i == 1) {
                this.o = z;
            }
        }

        public boolean a(int i) {
            if (i == 0) {
                return this.n;
            }
            if (i != 1) {
                return false;
            }
            return this.o;
        }

        public e(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            this.f195b = false;
            this.f196c = 0;
            this.f197d = 0;
            this.f198e = -1;
            this.f = -1;
            this.g = 0;
            this.h = 0;
            this.q = new Rect();
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, c.d.c.CoordinatorLayout_Layout);
            this.f196c = obtainStyledAttributes.getInteger(c.d.c.CoordinatorLayout_Layout_android_layout_gravity, 0);
            this.f = obtainStyledAttributes.getResourceId(c.d.c.CoordinatorLayout_Layout_layout_anchor, -1);
            this.f197d = obtainStyledAttributes.getInteger(c.d.c.CoordinatorLayout_Layout_layout_anchorGravity, 0);
            this.f198e = obtainStyledAttributes.getInteger(c.d.c.CoordinatorLayout_Layout_layout_keyline, -1);
            this.g = obtainStyledAttributes.getInt(c.d.c.CoordinatorLayout_Layout_layout_insetEdge, 0);
            this.h = obtainStyledAttributes.getInt(c.d.c.CoordinatorLayout_Layout_layout_dodgeInsetEdges, 0);
            this.f195b = obtainStyledAttributes.hasValue(c.d.c.CoordinatorLayout_Layout_layout_behavior);
            if (this.f195b) {
                this.f194a = CoordinatorLayout.a(context, attributeSet, obtainStyledAttributes.getString(c.d.c.CoordinatorLayout_Layout_layout_behavior));
            }
            obtainStyledAttributes.recycle();
            b bVar = this.f194a;
            if (bVar != null) {
                bVar.a(this);
            }
        }

        public e(e eVar) {
            super((ViewGroup.MarginLayoutParams) eVar);
            this.f195b = false;
            this.f196c = 0;
            this.f197d = 0;
            this.f198e = -1;
            this.f = -1;
            this.g = 0;
            this.h = 0;
            this.q = new Rect();
        }

        public e(ViewGroup.MarginLayoutParams marginLayoutParams) {
            super(marginLayoutParams);
            this.f195b = false;
            this.f196c = 0;
            this.f197d = 0;
            this.f198e = -1;
            this.f = -1;
            this.g = 0;
            this.h = 0;
            this.q = new Rect();
        }

        public e(ViewGroup.LayoutParams layoutParams) {
            super(layoutParams);
            this.f195b = false;
            this.f196c = 0;
            this.f197d = 0;
            this.f198e = -1;
            this.f = -1;
            this.g = 0;
            this.h = 0;
            this.q = new Rect();
        }
    }

    public CoordinatorLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        TypedArray typedArray;
        this.f = new ArrayList();
        this.g = new c.d.a.c<>();
        this.h = new ArrayList();
        new ArrayList();
        this.i = new int[2];
        this.w = new i(this);
        if (i == 0) {
            typedArray = context.obtainStyledAttributes(attributeSet, c.d.c.CoordinatorLayout, 0, c.d.b.Widget_Support_CoordinatorLayout);
        } else {
            typedArray = context.obtainStyledAttributes(attributeSet, c.d.c.CoordinatorLayout, i, 0);
        }
        int resourceId = typedArray.getResourceId(c.d.c.CoordinatorLayout_keylines, 0);
        if (resourceId != 0) {
            Resources resources = context.getResources();
            this.m = resources.getIntArray(resourceId);
            float f2 = resources.getDisplayMetrics().density;
            int length = this.m.length;
            for (int i2 = 0; i2 < length; i2++) {
                int[] iArr = this.m;
                iArr[i2] = (int) (iArr[i2] * f2);
            }
        }
        this.t = typedArray.getDrawable(c.d.c.CoordinatorLayout_statusBarBackground);
        typedArray.recycle();
        f();
        super.setOnHierarchyChangeListener(new d());
    }

    @Override // android.view.ViewGroup
    public e generateDefaultLayoutParams() {
        return new e(-2, -2);
    }

    public final v a(v vVar) {
        b bVar;
        v vVar2 = this.r;
        int i = Build.VERSION.SDK_INT;
        if (!Objects.equals(vVar2, vVar)) {
            this.r = vVar;
            boolean z = true;
            this.s = vVar != null && vVar.d() > 0;
            if (this.s || getBackground() != null) {
                z = false;
            }
            setWillNotDraw(z);
            if (!vVar.e()) {
                int childCount = getChildCount();
                for (int i2 = 0; i2 < childCount; i2++) {
                    View childAt = getChildAt(i2);
                    if (n.e(childAt) && (bVar = ((e) childAt.getLayoutParams()).f194a) != null) {
                        vVar = bVar.a(this, (CoordinatorLayout) childAt, vVar);
                        if (vVar.e()) {
                            break;
                        }
                    }
                }
            }
            requestLayout();
        }
        return vVar;
    }

    @Override // android.view.ViewGroup
    public e generateLayoutParams(AttributeSet attributeSet) {
        return new e(getContext(), attributeSet);
    }

    public final void b(int i) {
        int i2;
        boolean z;
        boolean z2;
        boolean z3;
        int width;
        int i3;
        int i4;
        int i5;
        int height;
        int i6;
        int i7;
        int i8;
        int g2 = n.g(this);
        int size = this.f.size();
        Rect a2 = a();
        Rect a3 = a();
        Rect a4 = a();
        for (int i9 = 0; i9 < size; i9++) {
            View view = this.f.get(i9);
            e eVar = (e) view.getLayoutParams();
            if (i == 0 && view.getVisibility() == 8) {
                size = size;
            } else {
                for (int i10 = 0; i10 < i9; i10++) {
                    if (eVar.l == this.f.get(i10)) {
                        b(view, g2);
                    }
                }
                a(view, true, a3);
                if (eVar.g != 0 && !a3.isEmpty()) {
                    int a5 = C.a(eVar.g, g2);
                    int i11 = a5 & 112;
                    if (i11 == 48) {
                        a2.top = Math.max(a2.top, a3.bottom);
                    } else if (i11 == 80) {
                        a2.bottom = Math.max(a2.bottom, getHeight() - a3.top);
                    }
                    int i12 = a5 & 7;
                    if (i12 == 3) {
                        a2.left = Math.max(a2.left, a3.right);
                    } else if (i12 == 5) {
                        a2.right = Math.max(a2.right, getWidth() - a3.left);
                    }
                }
                if (eVar.h == 0 || view.getVisibility() != 0 || !n.p(view) || view.getWidth() <= 0 || view.getHeight() <= 0) {
                    i2 = size;
                } else {
                    e eVar2 = (e) view.getLayoutParams();
                    b bVar = eVar2.f194a;
                    Rect a6 = a();
                    Rect a7 = a();
                    i2 = size;
                    a7.set(view.getLeft(), view.getTop(), view.getRight(), view.getBottom());
                    if (bVar == null || !bVar.a(this, (CoordinatorLayout) view, a6)) {
                        a6.set(a7);
                    } else if (!a7.contains(a6)) {
                        StringBuilder a8 = e.a.a.a.a.a("Rect should be within the child's bounds. Rect:");
                        a8.append(a6.toShortString());
                        a8.append(" | Bounds:");
                        a8.append(a7.toShortString());
                        throw new IllegalArgumentException(a8.toString());
                    }
                    a7.setEmpty();
                    f191e.a(a7);
                    if (a6.isEmpty()) {
                        a6.setEmpty();
                        f191e.a(a6);
                    } else {
                        int a9 = C.a(eVar2.h, g2);
                        if ((a9 & 48) != 48 || (i7 = (a6.top - ((ViewGroup.MarginLayoutParams) eVar2).topMargin) - eVar2.j) >= (i8 = a2.top)) {
                            z2 = false;
                        } else {
                            e(view, i8 - i7);
                            z2 = true;
                        }
                        if ((a9 & 80) == 80 && (height = ((getHeight() - a6.bottom) - ((ViewGroup.MarginLayoutParams) eVar2).bottomMargin) + eVar2.j) < (i6 = a2.bottom)) {
                            e(view, height - i6);
                            z2 = true;
                        }
                        if (!z2) {
                            e(view, 0);
                        }
                        if ((a9 & 3) != 3 || (i4 = (a6.left - ((ViewGroup.MarginLayoutParams) eVar2).leftMargin) - eVar2.i) >= (i5 = a2.left)) {
                            z3 = false;
                        } else {
                            d(view, i5 - i4);
                            z3 = true;
                        }
                        if ((a9 & 5) == 5 && (width = ((getWidth() - a6.right) - ((ViewGroup.MarginLayoutParams) eVar2).rightMargin) + eVar2.i) < (i3 = a2.right)) {
                            d(view, width - i3);
                            z3 = true;
                        }
                        if (!z3) {
                            d(view, 0);
                        }
                        a6.setEmpty();
                        f191e.a(a6);
                    }
                }
                if (i != 2) {
                    b(view, a4);
                    if (a4.equals(a3)) {
                        size = i2;
                    } else {
                        c(view, a3);
                    }
                }
                size = i2;
                for (int i13 = i9 + 1; i13 < size; i13++) {
                    View view2 = this.f.get(i13);
                    e eVar3 = (e) view2.getLayoutParams();
                    b bVar2 = eVar3.f194a;
                    if (bVar2 != null && bVar2.a(this, (CoordinatorLayout) view2, view)) {
                        if (i != 0 || !eVar3.p) {
                            if (i != 2) {
                                z = bVar2.b(this, (CoordinatorLayout) view2, view);
                            } else {
                                bVar2.c(this, view2, view);
                                z = true;
                            }
                            if (i == 1) {
                                eVar3.p = z;
                            }
                        } else {
                            eVar3.p = false;
                        }
                    }
                }
            }
        }
        a2.setEmpty();
        f191e.a(a2);
        a3.setEmpty();
        f191e.a(a3);
        a4.setEmpty();
        f191e.a(a4);
    }

    @Override // android.view.ViewGroup
    public e generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        if (layoutParams instanceof e) {
            return new e((e) layoutParams);
        }
        if (layoutParams instanceof ViewGroup.MarginLayoutParams) {
            return new e((ViewGroup.MarginLayoutParams) layoutParams);
        }
        return new e(layoutParams);
    }

    public void e() {
        if (this.l && this.p != null) {
            getViewTreeObserver().removeOnPreDrawListener(this.p);
        }
        this.q = false;
    }

    /* loaded from: classes.dex */
    protected static class SavedState extends AbsSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new c.d.a.b();

        /* renamed from: a */
        public SparseArray<Parcelable> f192a;

        public SavedState(Parcel parcel, ClassLoader classLoader) {
            super(parcel, classLoader);
            int readInt = parcel.readInt();
            int[] iArr = new int[readInt];
            parcel.readIntArray(iArr);
            Parcelable[] readParcelableArray = parcel.readParcelableArray(classLoader);
            this.f192a = new SparseArray<>(readInt);
            for (int i = 0; i < readInt; i++) {
                this.f192a.append(iArr[i], readParcelableArray[i]);
            }
        }

        @Override // androidx.customview.view.AbsSavedState, android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeParcelable(this.f218b, i);
            SparseArray<Parcelable> sparseArray = this.f192a;
            int size = sparseArray != null ? sparseArray.size() : 0;
            parcel.writeInt(size);
            int[] iArr = new int[size];
            Parcelable[] parcelableArr = new Parcelable[size];
            for (int i2 = 0; i2 < size; i2++) {
                iArr[i2] = this.f192a.keyAt(i2);
                parcelableArr[i2] = this.f192a.valueAt(i2);
            }
            parcel.writeIntArray(iArr);
            parcel.writeParcelableArray(parcelableArr, i);
        }

        public SavedState(Parcelable parcelable) {
            super(parcelable);
        }
    }

    public final void a(boolean z) {
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = getChildAt(i);
            b bVar = ((e) childAt.getLayoutParams()).f194a;
            if (bVar != null) {
                long uptimeMillis = SystemClock.uptimeMillis();
                MotionEvent obtain = MotionEvent.obtain(uptimeMillis, uptimeMillis, 3, 0.0f, 0.0f, 0);
                if (z) {
                    bVar.a(this, (CoordinatorLayout) childAt, obtain);
                } else {
                    bVar.b(this, (CoordinatorLayout) childAt, obtain);
                }
                obtain.recycle();
            }
        }
        for (int i2 = 0; i2 < childCount; i2++) {
            ((e) getChildAt(i2).getLayoutParams()).m = false;
        }
        this.n = null;
        this.k = false;
    }

    public final void a(List<View> list) {
        list.clear();
        boolean isChildrenDrawingOrderEnabled = isChildrenDrawingOrderEnabled();
        int childCount = getChildCount();
        for (int i = childCount - 1; i >= 0; i--) {
            list.add(getChildAt(isChildrenDrawingOrderEnabled ? getChildDrawingOrder(childCount, i) : i));
        }
        Comparator<View> comparator = f190d;
        if (comparator != null) {
            Collections.sort(list, comparator);
        }
    }

    public final boolean a(MotionEvent motionEvent, int i) {
        boolean z;
        int actionMasked = motionEvent.getActionMasked();
        List<View> list = this.h;
        a(list);
        int size = list.size();
        MotionEvent motionEvent2 = null;
        boolean z2 = false;
        boolean z3 = false;
        for (int i2 = 0; i2 < size; i2++) {
            View view = list.get(i2);
            e eVar = (e) view.getLayoutParams();
            b bVar = eVar.f194a;
            boolean z4 = true;
            if (!(z2 || z3) || actionMasked == 0) {
                if (!z2 && bVar != null) {
                    if (i == 0) {
                        z2 = bVar.a(this, (CoordinatorLayout) view, motionEvent);
                    } else if (i == 1) {
                        z2 = bVar.b(this, (CoordinatorLayout) view, motionEvent);
                    }
                    if (z2) {
                        this.n = view;
                    }
                }
                if (eVar.f194a == null) {
                    eVar.m = false;
                }
                boolean z5 = eVar.m;
                if (z5) {
                    z = true;
                } else {
                    b bVar2 = eVar.f194a;
                    z = (bVar2 != null ? bVar2.a(this, view) : false) | z5;
                    eVar.m = z;
                }
                if (!z || z5) {
                    z4 = false;
                }
                if (z && !z4) {
                    break;
                }
                z3 = z4;
            } else if (bVar != null) {
                if (motionEvent2 == null) {
                    long uptimeMillis = SystemClock.uptimeMillis();
                    motionEvent2 = MotionEvent.obtain(uptimeMillis, uptimeMillis, 3, 0.0f, 0.0f, 0);
                }
                if (i == 0) {
                    bVar.a(this, (CoordinatorLayout) view, motionEvent2);
                } else if (i == 1) {
                    bVar.b(this, (CoordinatorLayout) view, motionEvent2);
                }
            }
        }
        list.clear();
        return z2;
    }

    public final int a(int i) {
        int[] iArr = this.m;
        if (iArr == null) {
            String str = "No keylines defined for " + this + " - attempted index lookup " + i;
            return 0;
        } else if (i >= 0 && i < iArr.length) {
            return iArr[i];
        } else {
            String str2 = "Keyline index " + i + " out of range for " + this;
            return 0;
        }
    }

    public void c(View view, Rect rect) {
        ((e) view.getLayoutParams()).q.set(rect);
    }

    public static b a(Context context, AttributeSet attributeSet, String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        if (str.startsWith(".")) {
            str = context.getPackageName() + str;
        } else if (str.indexOf(46) < 0 && !TextUtils.isEmpty(f187a)) {
            str = f187a + '.' + str;
        }
        try {
            Map<String, Constructor<b>> map = f189c.get();
            if (map == null) {
                map = new HashMap<>();
                f189c.set(map);
            }
            Constructor<b> constructor = map.get(str);
            if (constructor == null) {
                constructor = context.getClassLoader().loadClass(str).getConstructor(f188b);
                constructor.setAccessible(true);
                map.put(str, constructor);
            }
            return (b) constructor.newInstance(context, attributeSet);
        } catch (Exception e2) {
            throw new RuntimeException(e.a.a.a.a.a("Could not inflate Behavior subclass ", str), e2);
        }
    }

    public void c() {
        boolean z;
        int childCount = getChildCount();
        boolean z2 = false;
        int i = 0;
        while (true) {
            if (i >= childCount) {
                break;
            }
            View childAt = getChildAt(i);
            c.d.a.c<View> cVar = this.g;
            int i2 = cVar.f735b.g;
            int i3 = 0;
            while (true) {
                if (i3 < i2) {
                    ArrayList<View> d2 = cVar.f735b.d(i3);
                    if (d2 != null && d2.contains(childAt)) {
                        z = true;
                        break;
                    }
                    i3++;
                } else {
                    z = false;
                    break;
                }
            }
            if (z) {
                z2 = true;
                break;
            }
            i++;
        }
        if (z2 == this.q) {
            return;
        }
        if (z2) {
            b();
        } else {
            e();
        }
    }

    public final void d(View view, int i) {
        e eVar = (e) view.getLayoutParams();
        int i2 = eVar.i;
        if (i2 != i) {
            n.c(view, i - i2);
            eVar.i = i;
        }
    }

    public void b() {
        if (this.l) {
            if (this.p == null) {
                this.p = new f();
            }
            getViewTreeObserver().addOnPreDrawListener(this.p);
        }
        this.q = true;
    }

    public e a(View view) {
        e eVar = (e) view.getLayoutParams();
        if (!eVar.f195b) {
            if (view instanceof a) {
                b a2 = ((a) view).a();
                b bVar = eVar.f194a;
                if (bVar != a2) {
                    if (bVar != null) {
                        bVar.a();
                    }
                    eVar.f194a = a2;
                    eVar.f195b = true;
                    if (a2 != null) {
                        a2.a(eVar);
                    }
                }
                eVar.f195b = true;
            } else {
                c cVar = null;
                for (Class<?> cls = view.getClass(); cls != null; cls = cls.getSuperclass()) {
                    cVar = (c) cls.getAnnotation(c.class);
                    if (cVar != null) {
                        break;
                    }
                }
                if (cVar != null) {
                    try {
                        b bVar2 = (b) cVar.value().getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
                        b bVar3 = eVar.f194a;
                        if (bVar3 != bVar2) {
                            if (bVar3 != null) {
                                bVar3.a();
                            }
                            eVar.f194a = bVar2;
                            eVar.f195b = true;
                            if (bVar2 != null) {
                                bVar2.a(eVar);
                            }
                        }
                    } catch (Exception unused) {
                        StringBuilder a3 = e.a.a.a.a.a("Default behavior class ");
                        a3.append(cVar.value().getName());
                        a3.append(" could not be instantiated. Did you forget");
                        a3.append(" a default constructor?");
                        a3.toString();
                    }
                }
                eVar.f195b = true;
            }
        }
        return eVar;
    }

    public void b(View view, int i) {
        b bVar;
        e eVar = (e) view.getLayoutParams();
        if (eVar.k != null) {
            Rect a2 = a();
            Rect a3 = a();
            Rect a4 = a();
            a(eVar.k, a2);
            boolean z = false;
            a(view, false, a3);
            int measuredWidth = view.getMeasuredWidth();
            int measuredHeight = view.getMeasuredHeight();
            a(view, i, a2, a4, eVar, measuredWidth, measuredHeight);
            if (!(a4.left == a3.left && a4.top == a3.top)) {
                z = true;
            }
            a(eVar, a4, measuredWidth, measuredHeight);
            int i2 = a4.left - a3.left;
            int i3 = a4.top - a3.top;
            if (i2 != 0) {
                n.c(view, i2);
            }
            if (i3 != 0) {
                n.d(view, i3);
            }
            if (z && (bVar = eVar.f194a) != null) {
                bVar.b(this, (CoordinatorLayout) view, eVar.k);
            }
            a2.setEmpty();
            f191e.a(a2);
            a3.setEmpty();
            f191e.a(a3);
            a4.setEmpty();
            f191e.a(a4);
        }
    }

    public void a(View view, Rect rect) {
        c.d.a.d.a(this, view, rect);
    }

    public void a(View view, int i, int i2, int i3, int i4) {
        measureChildWithMargins(view, i, i2, i3, i4);
    }

    public void a(View view, boolean z, Rect rect) {
        if (view.isLayoutRequested() || view.getVisibility() == 8) {
            rect.setEmpty();
        } else if (z) {
            a(view, rect);
        } else {
            rect.set(view.getLeft(), view.getTop(), view.getRight(), view.getBottom());
        }
    }

    @Override // c.e.h.g
    public void b(View view, View view2, int i, int i2) {
        b bVar;
        this.w.f860a = i;
        this.o = view2;
        int childCount = getChildCount();
        for (int i3 = 0; i3 < childCount; i3++) {
            View childAt = getChildAt(i3);
            e eVar = (e) childAt.getLayoutParams();
            if (eVar.a(i2) && (bVar = eVar.f194a) != null) {
                bVar.a(this, (CoordinatorLayout) childAt, view, view2, i, i2);
            }
        }
    }

    public final void a(View view, int i, Rect rect, Rect rect2, e eVar, int i2, int i3) {
        int i4;
        int i5;
        int i6 = eVar.f196c;
        if (i6 == 0) {
            i6 = 17;
        }
        int a2 = C.a(i6, i);
        int i7 = eVar.f197d;
        if ((i7 & 7) == 0) {
            i7 |= 8388611;
        }
        if ((i7 & 112) == 0) {
            i7 |= 48;
        }
        int a3 = C.a(i7, i);
        int i8 = a2 & 7;
        int i9 = a2 & 112;
        int i10 = a3 & 7;
        int i11 = a3 & 112;
        if (i10 == 1) {
            i4 = rect.left + (rect.width() / 2);
        } else if (i10 != 5) {
            i4 = rect.left;
        } else {
            i4 = rect.right;
        }
        if (i11 == 16) {
            i5 = rect.top + (rect.height() / 2);
        } else if (i11 != 80) {
            i5 = rect.top;
        } else {
            i5 = rect.bottom;
        }
        if (i8 == 1) {
            i4 -= i2 / 2;
        } else if (i8 != 5) {
            i4 -= i2;
        }
        if (i9 == 16) {
            i5 -= i3 / 2;
        } else if (i9 != 80) {
            i5 -= i3;
        }
        rect2.set(i4, i5, i2 + i4, i3 + i5);
    }

    public final void a(e eVar, Rect rect, int i, int i2) {
        int width = getWidth();
        int height = getHeight();
        int max = Math.max(getPaddingLeft() + ((ViewGroup.MarginLayoutParams) eVar).leftMargin, Math.min(rect.left, ((width - getPaddingRight()) - i) - ((ViewGroup.MarginLayoutParams) eVar).rightMargin));
        int max2 = Math.max(getPaddingTop() + ((ViewGroup.MarginLayoutParams) eVar).topMargin, Math.min(rect.top, ((height - getPaddingBottom()) - i2) - ((ViewGroup.MarginLayoutParams) eVar).bottomMargin));
        rect.set(max, max2, i + max, i2 + max2);
    }

    public void a(View view, int i, Rect rect, Rect rect2) {
        e eVar = (e) view.getLayoutParams();
        int measuredWidth = view.getMeasuredWidth();
        int measuredHeight = view.getMeasuredHeight();
        a(view, i, rect, rect2, eVar, measuredWidth, measuredHeight);
        a(eVar, rect2, measuredWidth, measuredHeight);
    }

    @Override // c.e.h.g
    public boolean a(View view, View view2, int i, int i2) {
        int childCount = getChildCount();
        boolean z = false;
        for (int i3 = 0; i3 < childCount; i3++) {
            View childAt = getChildAt(i3);
            if (childAt.getVisibility() != 8) {
                e eVar = (e) childAt.getLayoutParams();
                b bVar = eVar.f194a;
                if (bVar != null) {
                    boolean b2 = bVar.b(this, childAt, view, view2, i, i2);
                    z |= b2;
                    eVar.a(i2, b2);
                } else {
                    eVar.a(i2, false);
                }
            }
        }
        return z;
    }

    @Override // c.e.h.g
    public void a(View view, int i) {
        this.w.f860a = 0;
        int childCount = getChildCount();
        for (int i2 = 0; i2 < childCount; i2++) {
            View childAt = getChildAt(i2);
            e eVar = (e) childAt.getLayoutParams();
            if (eVar.a(i)) {
                b bVar = eVar.f194a;
                if (bVar != null) {
                    bVar.a(this, (CoordinatorLayout) childAt, view, i);
                }
                eVar.a(i, false);
                eVar.p = false;
            }
        }
        this.o = null;
    }

    @Override // c.e.h.g
    public void a(View view, int i, int i2, int i3, int i4, int i5) {
        b bVar;
        int childCount = getChildCount();
        boolean z = false;
        for (int i6 = 0; i6 < childCount; i6++) {
            View childAt = getChildAt(i6);
            if (childAt.getVisibility() != 8) {
                e eVar = (e) childAt.getLayoutParams();
                if (eVar.a(i5) && (bVar = eVar.f194a) != null) {
                    bVar.a(this, childAt, view, i, i2, i3, i4, i5);
                    z = true;
                }
            }
        }
        if (z) {
            b(1);
        }
    }

    @Override // c.e.h.g
    public void a(View view, int i, int i2, int[] iArr, int i3) {
        b bVar;
        int childCount = getChildCount();
        boolean z = false;
        int i4 = 0;
        int i5 = 0;
        for (int i6 = 0; i6 < childCount; i6++) {
            View childAt = getChildAt(i6);
            if (childAt.getVisibility() != 8) {
                e eVar = (e) childAt.getLayoutParams();
                if (eVar.a(i3) && (bVar = eVar.f194a) != null) {
                    int[] iArr2 = this.i;
                    iArr2[1] = 0;
                    iArr2[0] = 0;
                    bVar.a(this, (CoordinatorLayout) childAt, view, i, i2, iArr2, i3);
                    if (i > 0) {
                        i4 = Math.max(i4, this.i[0]);
                    } else {
                        i4 = Math.min(i4, this.i[0]);
                    }
                    if (i2 > 0) {
                        i5 = Math.max(i5, this.i[1]);
                    } else {
                        i5 = Math.min(i5, this.i[1]);
                    }
                    z = true;
                }
            }
        }
        iArr[0] = i4;
        iArr[1] = i5;
        if (z) {
            b(1);
        }
    }
}
