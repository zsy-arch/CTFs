package androidx.drawerlayout.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.WindowInsets;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import androidx.customview.view.AbsSavedState;
import c.a.a.C;
import c.e.h.a.a;
import c.e.h.n;
import c.g.b.c;
import com.tencent.smtt.sdk.TbsListener;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
public class DrawerLayout extends ViewGroup {

    /* renamed from: a */
    public static final int[] f219a = {16843828};

    /* renamed from: b */
    public static final int[] f220b = {16842931};

    /* renamed from: c */
    public static final boolean f221c = true;

    /* renamed from: d */
    public static final boolean f222d = true;
    public Drawable A;
    public Drawable B;
    public Drawable C;
    public CharSequence D;
    public CharSequence E;
    public Object F;
    public boolean G;
    public Drawable H;
    public Drawable I;
    public Drawable J;
    public Drawable K;
    public final ArrayList<View> L;
    public Rect M;
    public Matrix N;

    /* renamed from: e */
    public final b f223e;
    public float f;
    public int g;
    public int h;
    public float i;
    public Paint j;
    public final c.g.b.c k;
    public final c.g.b.c l;
    public final e m;
    public final e n;
    public int o;
    public boolean p;
    public boolean q;
    public int r;
    public int s;
    public int t;
    public int u;
    public boolean v;
    public c w;
    public List<c> x;
    public float y;
    public float z;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static final class b extends c.e.h.a {
        @Override // c.e.h.a
        public void a(View view, c.e.h.a.a aVar) {
            super.a(view, aVar);
            if (!DrawerLayout.g(view)) {
                aVar.f844a.setParent(null);
            }
        }
    }

    /* loaded from: classes.dex */
    public interface c {
        void a(int i);

        void a(View view);

        void a(View view, float f);

        void b(View view);
    }

    /* loaded from: classes.dex */
    public class e extends c.a {

        /* renamed from: a */
        public final int f235a;

        /* renamed from: b */
        public c.g.b.c f236b;

        /* renamed from: c */
        public final Runnable f237c = new c.h.a.c(this);

        public e(int i) {
            DrawerLayout.this = r1;
            this.f235a = i;
        }

        @Override // c.g.b.c.a
        public void a(View view, int i, int i2, int i3, int i4) {
            int width = view.getWidth();
            float width2 = (DrawerLayout.this.a(view, 3) ? i + width : DrawerLayout.this.getWidth() - i) / width;
            DrawerLayout.this.c(view, width2);
            view.setVisibility(width2 == 0.0f ? 4 : 0);
            DrawerLayout.this.invalidate();
        }

        public void b() {
            DrawerLayout.this.removeCallbacks(this.f237c);
        }

        @Override // c.g.b.c.a
        public boolean b(int i) {
            return false;
        }

        @Override // c.g.b.c.a
        public void c(int i) {
            DrawerLayout.this.a(this.f235a, i, this.f236b.t);
        }

        @Override // c.g.b.c.a
        public boolean b(View view, int i) {
            return DrawerLayout.this.j(view) && DrawerLayout.this.a(view, this.f235a) && DrawerLayout.this.d(view) == 0;
        }

        @Override // c.g.b.c.a
        public void b(int i, int i2) {
            DrawerLayout.this.postDelayed(this.f237c, 160L);
        }

        @Override // c.g.b.c.a
        public int b(View view, int i, int i2) {
            return view.getTop();
        }

        @Override // c.g.b.c.a
        public void a(View view, int i) {
            ((d) view.getLayoutParams()).f233c = false;
            a();
        }

        public final void a() {
            int i = 3;
            if (this.f235a == 3) {
                i = 5;
            }
            View a2 = DrawerLayout.this.a(i);
            if (a2 != null) {
                DrawerLayout.this.a(a2);
            }
        }

        @Override // c.g.b.c.a
        public void a(View view, float f, float f2) {
            int i;
            float f3 = DrawerLayout.this.f(view);
            int width = view.getWidth();
            if (DrawerLayout.this.a(view, 3)) {
                int i2 = (f > 0.0f ? 1 : (f == 0.0f ? 0 : -1));
                i = (i2 > 0 || (i2 == 0 && f3 > 0.5f)) ? 0 : -width;
            } else {
                i = DrawerLayout.this.getWidth();
                if (f < 0.0f || (f == 0.0f && f3 > 0.5f)) {
                    i -= width;
                }
            }
            this.f236b.b(i, view.getTop());
            DrawerLayout.this.invalidate();
        }

        @Override // c.g.b.c.a
        public void a(int i, int i2) {
            View view;
            if ((i & 1) == 1) {
                view = DrawerLayout.this.a(3);
            } else {
                view = DrawerLayout.this.a(5);
            }
            if (view != null && DrawerLayout.this.d(view) == 0) {
                this.f236b.a(view, i2);
            }
        }

        @Override // c.g.b.c.a
        public int a(View view) {
            if (DrawerLayout.this.j(view)) {
                return view.getWidth();
            }
            return 0;
        }

        @Override // c.g.b.c.a
        public int a(View view, int i, int i2) {
            if (DrawerLayout.this.a(view, 3)) {
                return Math.max(-view.getWidth(), Math.min(i, 0));
            }
            int width = DrawerLayout.this.getWidth();
            return Math.max(width - view.getWidth(), Math.min(i, width));
        }
    }

    static {
        int i = Build.VERSION.SDK_INT;
    }

    public DrawerLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public static boolean g(View view) {
        return (n.f(view) == 4 || n.f(view) == 2) ? false : true;
    }

    public void a(Object obj, boolean z) {
        this.F = obj;
        this.G = z;
        setWillNotDraw(!z && getBackground() == null);
        requestLayout();
    }

    @Override // android.view.ViewGroup, android.view.View
    public void addFocusables(ArrayList<View> arrayList, int i, int i2) {
        if (getDescendantFocusability() != 393216) {
            int childCount = getChildCount();
            boolean z = false;
            for (int i3 = 0; i3 < childCount; i3++) {
                View childAt = getChildAt(i3);
                if (!j(childAt)) {
                    this.L.add(childAt);
                } else if (i(childAt)) {
                    childAt.addFocusables(arrayList, i, i2);
                    z = true;
                }
            }
            if (!z) {
                int size = this.L.size();
                for (int i4 = 0; i4 < size; i4++) {
                    View view = this.L.get(i4);
                    if (view.getVisibility() == 0) {
                        view.addFocusables(arrayList, i, i2);
                    }
                }
            }
            this.L.clear();
        }
    }

    @Override // android.view.ViewGroup
    public void addView(View view, int i, ViewGroup.LayoutParams layoutParams) {
        super.addView(view, i, layoutParams);
        if (c() != null || j(view)) {
            n.e(view, 4);
        } else {
            n.e(view, 1);
        }
        if (!f221c) {
            n.a(view, this.f223e);
        }
    }

    public void b(c cVar) {
        List<c> list;
        if (cVar != null && (list = this.x) != null) {
            list.remove(cVar);
        }
    }

    public CharSequence c(int i) {
        int a2 = C.a(i, n.g(this));
        if (a2 == 3) {
            return this.D;
        }
        if (a2 == 5) {
            return this.E;
        }
        return null;
    }

    @Override // android.view.ViewGroup
    public boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return (layoutParams instanceof d) && super.checkLayoutParams(layoutParams);
    }

    @Override // android.view.View
    public void computeScroll() {
        int childCount = getChildCount();
        float f = 0.0f;
        for (int i = 0; i < childCount; i++) {
            f = Math.max(f, ((d) getChildAt(i).getLayoutParams()).f232b);
        }
        this.i = f;
        boolean a2 = this.k.a(true);
        boolean a3 = this.l.a(true);
        if (a2 || a3) {
            n.q(this);
        }
    }

    public int d(View view) {
        if (j(view)) {
            return b(((d) view.getLayoutParams()).f231a);
        }
        throw new IllegalArgumentException(e.a.a.a.a.a("View ", view, " is not a drawer"));
    }

    @Override // android.view.View
    public boolean dispatchGenericMotionEvent(MotionEvent motionEvent) {
        boolean z;
        if ((motionEvent.getSource() & 2) == 0 || motionEvent.getAction() == 10 || this.i <= 0.0f) {
            return super.dispatchGenericMotionEvent(motionEvent);
        }
        int childCount = getChildCount();
        if (childCount == 0) {
            return false;
        }
        float x = motionEvent.getX();
        float y = motionEvent.getY();
        for (int i = childCount - 1; i >= 0; i--) {
            View childAt = getChildAt(i);
            if (this.M == null) {
                this.M = new Rect();
            }
            childAt.getHitRect(this.M);
            if (this.M.contains((int) x, (int) y) && !h(childAt)) {
                if (!childAt.getMatrix().isIdentity()) {
                    MotionEvent obtain = MotionEvent.obtain(motionEvent);
                    obtain.offsetLocation(getScrollX() - childAt.getLeft(), getScrollY() - childAt.getTop());
                    Matrix matrix = childAt.getMatrix();
                    if (!matrix.isIdentity()) {
                        if (this.N == null) {
                            this.N = new Matrix();
                        }
                        matrix.invert(this.N);
                        obtain.transform(this.N);
                    }
                    z = childAt.dispatchGenericMotionEvent(obtain);
                    obtain.recycle();
                } else {
                    float scrollX = getScrollX() - childAt.getLeft();
                    float scrollY = getScrollY() - childAt.getTop();
                    motionEvent.offsetLocation(scrollX, scrollY);
                    z = childAt.dispatchGenericMotionEvent(motionEvent);
                    motionEvent.offsetLocation(-scrollX, -scrollY);
                }
                if (z) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override // android.view.ViewGroup
    public boolean drawChild(Canvas canvas, View view, long j) {
        int i;
        int height = getHeight();
        boolean h = h(view);
        int width = getWidth();
        int save = canvas.save();
        int i2 = 0;
        if (h) {
            int childCount = getChildCount();
            i = width;
            int i3 = 0;
            for (int i4 = 0; i4 < childCount; i4++) {
                View childAt = getChildAt(i4);
                if (childAt != view && childAt.getVisibility() == 0) {
                    Drawable background = childAt.getBackground();
                    if ((background != null && background.getOpacity() == -1) && j(childAt) && childAt.getHeight() >= height) {
                        if (a(childAt, 3)) {
                            int right = childAt.getRight();
                            if (right > i3) {
                                i3 = right;
                            }
                        } else {
                            int left = childAt.getLeft();
                            if (left < i) {
                                i = left;
                            }
                        }
                    }
                }
            }
            canvas.clipRect(i3, 0, i, getHeight());
            i2 = i3;
        } else {
            i = width;
        }
        boolean drawChild = super.drawChild(canvas, view, j);
        canvas.restoreToCount(save);
        float f = this.i;
        if (f > 0.0f && h) {
            int i5 = this.h;
            this.j.setColor((i5 & 16777215) | (((int) ((((-16777216) & i5) >>> 24) * f)) << 24));
            canvas.drawRect(i2, 0.0f, i, getHeight(), this.j);
        } else if (this.B != null && a(view, 3)) {
            int intrinsicWidth = this.B.getIntrinsicWidth();
            int right2 = view.getRight();
            float max = Math.max(0.0f, Math.min(right2 / this.k.p, 1.0f));
            this.B.setBounds(right2, view.getTop(), intrinsicWidth + right2, view.getBottom());
            this.B.setAlpha((int) (max * 255.0f));
            this.B.draw(canvas);
        } else if (this.C != null && a(view, 5)) {
            int intrinsicWidth2 = this.C.getIntrinsicWidth();
            int left2 = view.getLeft();
            float max2 = Math.max(0.0f, Math.min((getWidth() - left2) / this.l.p, 1.0f));
            this.C.setBounds(left2 - intrinsicWidth2, view.getTop(), left2, view.getBottom());
            this.C.setAlpha((int) (max2 * 255.0f));
            this.C.draw(canvas);
        }
        return drawChild;
    }

    public int e(View view) {
        return C.a(((d) view.getLayoutParams()).f231a, n.g(this));
    }

    public float f(View view) {
        return ((d) view.getLayoutParams()).f232b;
    }

    @Override // android.view.ViewGroup
    public ViewGroup.LayoutParams generateDefaultLayoutParams() {
        return new d(-1, -1);
    }

    @Override // android.view.ViewGroup
    public ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return layoutParams instanceof d ? new d((d) layoutParams) : layoutParams instanceof ViewGroup.MarginLayoutParams ? new d((ViewGroup.MarginLayoutParams) layoutParams) : new d(layoutParams);
    }

    public float getDrawerElevation() {
        if (f222d) {
            return this.f;
        }
        return 0.0f;
    }

    public Drawable getStatusBarBackgroundDrawable() {
        return this.A;
    }

    public boolean h(View view) {
        return ((d) view.getLayoutParams()).f231a == 0;
    }

    public boolean i(View view) {
        if (j(view)) {
            return (((d) view.getLayoutParams()).f234d & 1) == 1;
        }
        throw new IllegalArgumentException(e.a.a.a.a.a("View ", view, " is not a drawer"));
    }

    public boolean j(View view) {
        int a2 = C.a(((d) view.getLayoutParams()).f231a, n.g(view));
        return ((a2 & 3) == 0 && (a2 & 5) == 0) ? false : true;
    }

    public boolean k(View view) {
        if (j(view)) {
            return ((d) view.getLayoutParams()).f232b > 0.0f;
        }
        throw new IllegalArgumentException(e.a.a.a.a.a("View ", view, " is not a drawer"));
    }

    public void l(View view) {
        b(view, true);
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.q = true;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.q = true;
    }

    @Override // android.view.View
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (this.G && this.A != null) {
            int i = Build.VERSION.SDK_INT;
            Object obj = this.F;
            int systemWindowInsetTop = obj != null ? ((WindowInsets) obj).getSystemWindowInsetTop() : 0;
            if (systemWindowInsetTop > 0) {
                this.A.setBounds(0, 0, getWidth(), systemWindowInsetTop);
                this.A.draw(canvas);
            }
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:8:0x001b, code lost:
        if (r0 != 3) goto L_0x006a;
     */
    /* JADX WARN: Removed duplicated region for block: B:19:0x0054 A[LOOP:0: B:10:0x0024->B:19:0x0054, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:47:0x0052 A[SYNTHETIC] */
    @Override // android.view.ViewGroup
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean onInterceptTouchEvent(android.view.MotionEvent r9) {
        /*
            r8 = this;
            int r0 = r9.getActionMasked()
            c.g.b.c r1 = r8.k
            boolean r1 = r1.c(r9)
            c.g.b.c r2 = r8.l
            boolean r2 = r2.c(r9)
            r1 = r1 | r2
            r2 = 1
            r3 = 0
            if (r0 == 0) goto L_0x006c
            if (r0 == r2) goto L_0x0065
            r9 = 2
            if (r0 == r9) goto L_0x001e
            r9 = 3
            if (r0 == r9) goto L_0x0065
            goto L_0x006a
        L_0x001e:
            c.g.b.c r9 = r8.k
            float[] r0 = r9.f907e
            int r0 = r0.length
            r4 = 0
        L_0x0024:
            if (r4 >= r0) goto L_0x0057
            boolean r5 = r9.b(r4)
            if (r5 != 0) goto L_0x002d
            goto L_0x004f
        L_0x002d:
            float[] r5 = r9.g
            r5 = r5[r4]
            float[] r6 = r9.f907e
            r6 = r6[r4]
            float r5 = r5 - r6
            float[] r6 = r9.h
            r6 = r6[r4]
            float[] r7 = r9.f
            r7 = r7[r4]
            float r6 = r6 - r7
            float r5 = r5 * r5
            float r6 = r6 * r6
            float r6 = r6 + r5
            int r5 = r9.f905c
            int r5 = r5 * r5
            float r5 = (float) r5
            int r5 = (r6 > r5 ? 1 : (r6 == r5 ? 0 : -1))
            if (r5 <= 0) goto L_0x004f
            r5 = 1
            goto L_0x0050
        L_0x004f:
            r5 = 0
        L_0x0050:
            if (r5 == 0) goto L_0x0054
            r9 = 1
            goto L_0x0058
        L_0x0054:
            int r4 = r4 + 1
            goto L_0x0024
        L_0x0057:
            r9 = 0
        L_0x0058:
            if (r9 == 0) goto L_0x006a
            androidx.drawerlayout.widget.DrawerLayout$e r9 = r8.m
            r9.b()
            androidx.drawerlayout.widget.DrawerLayout$e r9 = r8.n
            r9.b()
            goto L_0x006a
        L_0x0065:
            r8.a(r2)
            r8.v = r3
        L_0x006a:
            r9 = 0
            goto L_0x0094
        L_0x006c:
            float r0 = r9.getX()
            float r9 = r9.getY()
            r8.y = r0
            r8.z = r9
            float r4 = r8.i
            r5 = 0
            int r4 = (r4 > r5 ? 1 : (r4 == r5 ? 0 : -1))
            if (r4 <= 0) goto L_0x0091
            c.g.b.c r4 = r8.k
            int r0 = (int) r0
            int r9 = (int) r9
            android.view.View r9 = r4.a(r0, r9)
            if (r9 == 0) goto L_0x0091
            boolean r9 = r8.h(r9)
            if (r9 == 0) goto L_0x0091
            r9 = 1
            goto L_0x0092
        L_0x0091:
            r9 = 0
        L_0x0092:
            r8.v = r3
        L_0x0094:
            if (r1 != 0) goto L_0x00bb
            if (r9 != 0) goto L_0x00bb
            int r9 = r8.getChildCount()
            r0 = 0
        L_0x009d:
            if (r0 >= r9) goto L_0x00b2
            android.view.View r1 = r8.getChildAt(r0)
            android.view.ViewGroup$LayoutParams r1 = r1.getLayoutParams()
            androidx.drawerlayout.widget.DrawerLayout$d r1 = (androidx.drawerlayout.widget.DrawerLayout.d) r1
            boolean r1 = r1.f233c
            if (r1 == 0) goto L_0x00af
            r9 = 1
            goto L_0x00b3
        L_0x00af:
            int r0 = r0 + 1
            goto L_0x009d
        L_0x00b2:
            r9 = 0
        L_0x00b3:
            if (r9 != 0) goto L_0x00bb
            boolean r9 = r8.v
            if (r9 == 0) goto L_0x00ba
            goto L_0x00bb
        L_0x00ba:
            r2 = 0
        L_0x00bb:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.drawerlayout.widget.DrawerLayout.onInterceptTouchEvent(android.view.MotionEvent):boolean");
    }

    @Override // android.view.View, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i == 4) {
            if (d() != null) {
                keyEvent.startTracking();
                return true;
            }
        }
        return super.onKeyDown(i, keyEvent);
    }

    @Override // android.view.View, android.view.KeyEvent.Callback
    public boolean onKeyUp(int i, KeyEvent keyEvent) {
        if (i != 4) {
            return super.onKeyUp(i, keyEvent);
        }
        View d2 = d();
        if (d2 != null && d(d2) == 0) {
            b();
        }
        return d2 != null;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        float f;
        int i5;
        this.p = true;
        int i6 = i3 - i;
        int childCount = getChildCount();
        for (int i7 = 0; i7 < childCount; i7++) {
            View childAt = getChildAt(i7);
            if (childAt.getVisibility() != 8) {
                d dVar = (d) childAt.getLayoutParams();
                if (h(childAt)) {
                    int i8 = ((ViewGroup.MarginLayoutParams) dVar).leftMargin;
                    childAt.layout(i8, ((ViewGroup.MarginLayoutParams) dVar).topMargin, childAt.getMeasuredWidth() + i8, childAt.getMeasuredHeight() + ((ViewGroup.MarginLayoutParams) dVar).topMargin);
                } else {
                    int measuredWidth = childAt.getMeasuredWidth();
                    int measuredHeight = childAt.getMeasuredHeight();
                    if (a(childAt, 3)) {
                        float f2 = measuredWidth;
                        i5 = (-measuredWidth) + ((int) (dVar.f232b * f2));
                        f = (measuredWidth + i5) / f2;
                    } else {
                        float f3 = measuredWidth;
                        int i9 = i6 - ((int) (dVar.f232b * f3));
                        f = (i6 - i9) / f3;
                        i5 = i9;
                    }
                    boolean z2 = f != dVar.f232b;
                    int i10 = dVar.f231a & 112;
                    if (i10 == 16) {
                        int i11 = i4 - i2;
                        int i12 = (i11 - measuredHeight) / 2;
                        int i13 = ((ViewGroup.MarginLayoutParams) dVar).topMargin;
                        if (i12 < i13) {
                            i12 = i13;
                        } else {
                            int i14 = i12 + measuredHeight;
                            int i15 = ((ViewGroup.MarginLayoutParams) dVar).bottomMargin;
                            if (i14 > i11 - i15) {
                                i12 = (i11 - i15) - measuredHeight;
                            }
                        }
                        childAt.layout(i5, i12, measuredWidth + i5, measuredHeight + i12);
                    } else if (i10 != 80) {
                        int i16 = ((ViewGroup.MarginLayoutParams) dVar).topMargin;
                        childAt.layout(i5, i16, measuredWidth + i5, measuredHeight + i16);
                    } else {
                        int i17 = i4 - i2;
                        childAt.layout(i5, (i17 - ((ViewGroup.MarginLayoutParams) dVar).bottomMargin) - childAt.getMeasuredHeight(), measuredWidth + i5, i17 - ((ViewGroup.MarginLayoutParams) dVar).bottomMargin);
                    }
                    if (z2) {
                        c(childAt, f);
                    }
                    int i18 = dVar.f232b > 0.0f ? 0 : 4;
                    if (childAt.getVisibility() != i18) {
                        childAt.setVisibility(i18);
                    }
                }
            }
        }
        this.p = false;
        this.q = false;
    }

    @Override // android.view.View
    @SuppressLint({"WrongConstant"})
    public void onMeasure(int i, int i2) {
        String str;
        int mode = View.MeasureSpec.getMode(i);
        int mode2 = View.MeasureSpec.getMode(i2);
        int size = View.MeasureSpec.getSize(i);
        int size2 = View.MeasureSpec.getSize(i2);
        if (!(mode == 1073741824 && mode2 == 1073741824)) {
            if (isInEditMode()) {
                if (mode != Integer.MIN_VALUE && mode == 0) {
                    size = TbsListener.ErrorCode.ERROR_CODE_LOAD_BASE;
                }
                if (mode2 != Integer.MIN_VALUE && mode2 == 0) {
                    size2 = TbsListener.ErrorCode.ERROR_CODE_LOAD_BASE;
                }
            } else {
                throw new IllegalArgumentException("DrawerLayout must be measured with MeasureSpec.EXACTLY.");
            }
        }
        setMeasuredDimension(size, size2);
        int i3 = 0;
        boolean z = this.F != null && n.e(this);
        int g = n.g(this);
        int childCount = getChildCount();
        int i4 = 0;
        boolean z2 = false;
        boolean z3 = false;
        while (i4 < childCount) {
            View childAt = getChildAt(i4);
            if (childAt.getVisibility() != 8) {
                d dVar = (d) childAt.getLayoutParams();
                if (z) {
                    int a2 = C.a(dVar.f231a, g);
                    if (n.e(childAt)) {
                        int i5 = Build.VERSION.SDK_INT;
                        WindowInsets windowInsets = (WindowInsets) this.F;
                        if (a2 == 3) {
                            windowInsets = windowInsets.replaceSystemWindowInsets(windowInsets.getSystemWindowInsetLeft(), windowInsets.getSystemWindowInsetTop(), i3, windowInsets.getSystemWindowInsetBottom());
                        } else if (a2 == 5) {
                            windowInsets = windowInsets.replaceSystemWindowInsets(i3, windowInsets.getSystemWindowInsetTop(), windowInsets.getSystemWindowInsetRight(), windowInsets.getSystemWindowInsetBottom());
                        }
                        childAt.dispatchApplyWindowInsets(windowInsets);
                    } else {
                        int i6 = Build.VERSION.SDK_INT;
                        WindowInsets windowInsets2 = (WindowInsets) this.F;
                        if (a2 == 3) {
                            windowInsets2 = windowInsets2.replaceSystemWindowInsets(windowInsets2.getSystemWindowInsetLeft(), windowInsets2.getSystemWindowInsetTop(), i3, windowInsets2.getSystemWindowInsetBottom());
                        } else if (a2 == 5) {
                            windowInsets2 = windowInsets2.replaceSystemWindowInsets(i3, windowInsets2.getSystemWindowInsetTop(), windowInsets2.getSystemWindowInsetRight(), windowInsets2.getSystemWindowInsetBottom());
                        }
                        ((ViewGroup.MarginLayoutParams) dVar).leftMargin = windowInsets2.getSystemWindowInsetLeft();
                        ((ViewGroup.MarginLayoutParams) dVar).topMargin = windowInsets2.getSystemWindowInsetTop();
                        ((ViewGroup.MarginLayoutParams) dVar).rightMargin = windowInsets2.getSystemWindowInsetRight();
                        ((ViewGroup.MarginLayoutParams) dVar).bottomMargin = windowInsets2.getSystemWindowInsetBottom();
                    }
                }
                if (h(childAt)) {
                    childAt.measure(View.MeasureSpec.makeMeasureSpec((size - ((ViewGroup.MarginLayoutParams) dVar).leftMargin) - ((ViewGroup.MarginLayoutParams) dVar).rightMargin, 1073741824), View.MeasureSpec.makeMeasureSpec((size2 - ((ViewGroup.MarginLayoutParams) dVar).topMargin) - ((ViewGroup.MarginLayoutParams) dVar).bottomMargin, 1073741824));
                } else if (j(childAt)) {
                    if (f222d) {
                        float d2 = n.d(childAt);
                        float f = this.f;
                        if (d2 != f) {
                            n.a(childAt, f);
                        }
                    }
                    int e2 = e(childAt) & 7;
                    boolean z4 = e2 == 3;
                    if ((!z4 || !z2) && (z4 || !z3)) {
                        if (z4) {
                            z2 = true;
                        } else {
                            z3 = true;
                        }
                        childAt.measure(ViewGroup.getChildMeasureSpec(i, this.g + ((ViewGroup.MarginLayoutParams) dVar).leftMargin + ((ViewGroup.MarginLayoutParams) dVar).rightMargin, ((ViewGroup.MarginLayoutParams) dVar).width), ViewGroup.getChildMeasureSpec(i2, ((ViewGroup.MarginLayoutParams) dVar).topMargin + ((ViewGroup.MarginLayoutParams) dVar).bottomMargin, ((ViewGroup.MarginLayoutParams) dVar).height));
                        i4++;
                        i3 = 0;
                    } else {
                        StringBuilder a3 = e.a.a.a.a.a("Child drawer has absolute gravity ");
                        if ((e2 & 3) != 3) {
                            str = (e2 & 5) == 5 ? "RIGHT" : Integer.toHexString(e2);
                        } else {
                            str = "LEFT";
                        }
                        a3.append(str);
                        a3.append(" but this ");
                        a3.append("DrawerLayout");
                        a3.append(" already has a ");
                        a3.append("drawer view along that edge");
                        throw new IllegalStateException(a3.toString());
                    }
                } else {
                    throw new IllegalStateException("Child " + childAt + " at index " + i4 + " does not have a valid layout_gravity - must be Gravity.LEFT, Gravity.RIGHT or Gravity.NO_GRAVITY");
                }
            }
            i4++;
            i3 = 0;
        }
    }

    @Override // android.view.View
    public void onRestoreInstanceState(Parcelable parcelable) {
        View a2;
        if (!(parcelable instanceof SavedState)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        SavedState savedState = (SavedState) parcelable;
        super.onRestoreInstanceState(savedState.a());
        int i = savedState.f224a;
        if (!(i == 0 || (a2 = a(i)) == null)) {
            l(a2);
        }
        int i2 = savedState.f225b;
        if (i2 != 3) {
            a(i2, 3);
        }
        int i3 = savedState.f226c;
        if (i3 != 3) {
            a(i3, 5);
        }
        int i4 = savedState.f227d;
        if (i4 != 3) {
            a(i4, 8388611);
        }
        int i5 = savedState.f228e;
        if (i5 != 3) {
            a(i5, 8388613);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x0029  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0033  */
    @Override // android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void onRtlPropertiesChanged(int r2) {
        /*
            r1 = this;
            boolean r2 = androidx.drawerlayout.widget.DrawerLayout.f222d
            if (r2 == 0) goto L_0x0005
            goto L_0x0041
        L_0x0005:
            int r2 = c.e.h.n.g(r1)
            if (r2 != 0) goto L_0x0015
            android.graphics.drawable.Drawable r0 = r1.H
            if (r0 == 0) goto L_0x001f
            r1.a(r0, r2)
            android.graphics.drawable.Drawable r2 = r1.H
            goto L_0x0021
        L_0x0015:
            android.graphics.drawable.Drawable r0 = r1.I
            if (r0 == 0) goto L_0x001f
            r1.a(r0, r2)
            android.graphics.drawable.Drawable r2 = r1.I
            goto L_0x0021
        L_0x001f:
            android.graphics.drawable.Drawable r2 = r1.J
        L_0x0021:
            r1.B = r2
            int r2 = c.e.h.n.g(r1)
            if (r2 != 0) goto L_0x0033
            android.graphics.drawable.Drawable r0 = r1.I
            if (r0 == 0) goto L_0x003d
            r1.a(r0, r2)
            android.graphics.drawable.Drawable r2 = r1.I
            goto L_0x003f
        L_0x0033:
            android.graphics.drawable.Drawable r0 = r1.H
            if (r0 == 0) goto L_0x003d
            r1.a(r0, r2)
            android.graphics.drawable.Drawable r2 = r1.H
            goto L_0x003f
        L_0x003d:
            android.graphics.drawable.Drawable r2 = r1.K
        L_0x003f:
            r1.C = r2
        L_0x0041:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.drawerlayout.widget.DrawerLayout.onRtlPropertiesChanged(int):void");
    }

    @Override // android.view.View
    public Parcelable onSaveInstanceState() {
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            d dVar = (d) getChildAt(i).getLayoutParams();
            boolean z = true;
            boolean z2 = dVar.f234d == 1;
            if (dVar.f234d != 2) {
                z = false;
            }
            if (z2 || z) {
                savedState.f224a = dVar.f231a;
                break;
            }
        }
        savedState.f225b = this.r;
        savedState.f226c = this.s;
        savedState.f227d = this.t;
        savedState.f228e = this.u;
        return savedState;
    }

    /* JADX WARN: Code restructure failed: missing block: B:17:0x0059, code lost:
        if (d(r7) != 2) goto L_0x005c;
     */
    @Override // android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean onTouchEvent(android.view.MotionEvent r7) {
        /*
            r6 = this;
            c.g.b.c r0 = r6.k
            r0.a(r7)
            c.g.b.c r0 = r6.l
            r0.a(r7)
            int r0 = r7.getAction()
            r0 = r0 & 255(0xff, float:3.57E-43)
            r1 = 0
            r2 = 1
            if (r0 == 0) goto L_0x0060
            if (r0 == r2) goto L_0x0020
            r7 = 3
            if (r0 == r7) goto L_0x001a
            goto L_0x006e
        L_0x001a:
            r6.a(r2)
            r6.v = r1
            goto L_0x006e
        L_0x0020:
            float r0 = r7.getX()
            float r7 = r7.getY()
            c.g.b.c r3 = r6.k
            int r4 = (int) r0
            int r5 = (int) r7
            android.view.View r3 = r3.a(r4, r5)
            if (r3 == 0) goto L_0x005b
            boolean r3 = r6.h(r3)
            if (r3 == 0) goto L_0x005b
            float r3 = r6.y
            float r0 = r0 - r3
            float r3 = r6.z
            float r7 = r7 - r3
            c.g.b.c r3 = r6.k
            int r3 = r3.f905c
            float r0 = r0 * r0
            float r7 = r7 * r7
            float r7 = r7 + r0
            int r3 = r3 * r3
            float r0 = (float) r3
            int r7 = (r7 > r0 ? 1 : (r7 == r0 ? 0 : -1))
            if (r7 >= 0) goto L_0x005b
            android.view.View r7 = r6.c()
            if (r7 == 0) goto L_0x005b
            int r7 = r6.d(r7)
            r0 = 2
            if (r7 != r0) goto L_0x005c
        L_0x005b:
            r1 = 1
        L_0x005c:
            r6.a(r1)
            goto L_0x006e
        L_0x0060:
            float r0 = r7.getX()
            float r7 = r7.getY()
            r6.y = r0
            r6.z = r7
            r6.v = r1
        L_0x006e:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.drawerlayout.widget.DrawerLayout.onTouchEvent(android.view.MotionEvent):boolean");
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public void requestDisallowInterceptTouchEvent(boolean z) {
        super.requestDisallowInterceptTouchEvent(z);
        if (z) {
            a(true);
        }
    }

    @Override // android.view.View, android.view.ViewParent
    public void requestLayout() {
        if (!this.p) {
            super.requestLayout();
        }
    }

    public void setDrawerElevation(float f) {
        this.f = f;
        for (int i = 0; i < getChildCount(); i++) {
            View childAt = getChildAt(i);
            if (j(childAt)) {
                n.a(childAt, this.f);
            }
        }
    }

    @Deprecated
    public void setDrawerListener(c cVar) {
        c cVar2 = this.w;
        if (cVar2 != null) {
            b(cVar2);
        }
        if (cVar != null) {
            a(cVar);
        }
        this.w = cVar;
    }

    public void setDrawerLockMode(int i) {
        a(i, 3);
        a(i, 5);
    }

    public void setScrimColor(int i) {
        this.h = i;
        invalidate();
    }

    public void setStatusBarBackground(Drawable drawable) {
        this.A = drawable;
        invalidate();
    }

    public void setStatusBarBackgroundColor(int i) {
        this.A = new ColorDrawable(i);
        invalidate();
    }

    public DrawerLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f223e = new b();
        this.h = -1728053248;
        this.j = new Paint();
        this.q = true;
        this.r = 3;
        this.s = 3;
        this.t = 3;
        this.u = 3;
        this.H = null;
        this.I = null;
        this.J = null;
        this.K = null;
        setDescendantFocusability(262144);
        float f = getResources().getDisplayMetrics().density;
        this.g = (int) ((64.0f * f) + 0.5f);
        float f2 = 400.0f * f;
        this.m = new e(3);
        this.n = new e(5);
        this.k = c.g.b.c.a(this, 1.0f, this.m);
        c.g.b.c cVar = this.k;
        cVar.q = 1;
        cVar.o = f2;
        this.m.f236b = cVar;
        this.l = c.g.b.c.a(this, 1.0f, this.n);
        c.g.b.c cVar2 = this.l;
        cVar2.q = 2;
        cVar2.o = f2;
        this.n.f236b = cVar2;
        setFocusableInTouchMode(true);
        n.e(this, 1);
        n.a(this, new a());
        setMotionEventSplittingEnabled(false);
        if (n.e(this)) {
            int i2 = Build.VERSION.SDK_INT;
            setOnApplyWindowInsetsListener(new c.h.a.a(this));
            setSystemUiVisibility(1280);
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(f219a);
            try {
                this.A = obtainStyledAttributes.getDrawable(0);
            } finally {
                obtainStyledAttributes.recycle();
            }
        }
        this.f = f * 10.0f;
        this.L = new ArrayList<>();
    }

    @Override // android.view.ViewGroup
    public ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new d(getContext(), attributeSet);
    }

    public int b(int i) {
        int g = n.g(this);
        if (i == 3) {
            int i2 = this.r;
            if (i2 != 3) {
                return i2;
            }
            int i3 = g == 0 ? this.t : this.u;
            if (i3 != 3) {
                return i3;
            }
            return 0;
        } else if (i == 5) {
            int i4 = this.s;
            if (i4 != 3) {
                return i4;
            }
            int i5 = g == 0 ? this.u : this.t;
            if (i5 != 3) {
                return i5;
            }
            return 0;
        } else if (i == 8388611) {
            int i6 = this.t;
            if (i6 != 3) {
                return i6;
            }
            int i7 = g == 0 ? this.r : this.s;
            if (i7 != 3) {
                return i7;
            }
            return 0;
        } else if (i != 8388613) {
            return 0;
        } else {
            int i8 = this.u;
            if (i8 != 3) {
                return i8;
            }
            int i9 = g == 0 ? this.s : this.r;
            if (i9 != 3) {
                return i9;
            }
            return 0;
        }
    }

    public void setStatusBarBackground(int i) {
        this.A = i != 0 ? c.e.b.a.b(getContext(), i) : null;
        invalidate();
    }

    /* loaded from: classes.dex */
    public static class d extends ViewGroup.MarginLayoutParams {

        /* renamed from: a */
        public int f231a;

        /* renamed from: b */
        public float f232b;

        /* renamed from: c */
        public boolean f233c;

        /* renamed from: d */
        public int f234d;

        public d(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            this.f231a = 0;
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, DrawerLayout.f220b);
            this.f231a = obtainStyledAttributes.getInt(0, 0);
            obtainStyledAttributes.recycle();
        }

        public d(int i, int i2) {
            super(i, i2);
            this.f231a = 0;
        }

        public d(d dVar) {
            super((ViewGroup.MarginLayoutParams) dVar);
            this.f231a = 0;
            this.f231a = dVar.f231a;
        }

        public d(ViewGroup.LayoutParams layoutParams) {
            super(layoutParams);
            this.f231a = 0;
        }

        public d(ViewGroup.MarginLayoutParams marginLayoutParams) {
            super(marginLayoutParams);
            this.f231a = 0;
        }
    }

    public void a(c cVar) {
        if (cVar != null) {
            if (this.x == null) {
                this.x = new ArrayList();
            }
            this.x.add(cVar);
        }
    }

    public void c(View view) {
        d dVar = (d) view.getLayoutParams();
        if ((dVar.f234d & 1) == 0) {
            dVar.f234d = 1;
            List<c> list = this.x;
            if (list != null) {
                for (int size = list.size() - 1; size >= 0; size--) {
                    this.x.get(size).a(view);
                }
            }
            c(view, true);
            if (hasWindowFocus()) {
                sendAccessibilityEvent(32);
            }
        }
    }

    public View d() {
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = getChildAt(i);
            if (j(childAt) && k(childAt)) {
                return childAt;
            }
        }
        return null;
    }

    /* loaded from: classes.dex */
    protected static class SavedState extends AbsSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new c.h.a.b();

        /* renamed from: a */
        public int f224a;

        /* renamed from: b */
        public int f225b;

        /* renamed from: c */
        public int f226c;

        /* renamed from: d */
        public int f227d;

        /* renamed from: e */
        public int f228e;

        public SavedState(Parcel parcel, ClassLoader classLoader) {
            super(parcel, classLoader);
            this.f224a = 0;
            this.f224a = parcel.readInt();
            this.f225b = parcel.readInt();
            this.f226c = parcel.readInt();
            this.f227d = parcel.readInt();
            this.f228e = parcel.readInt();
        }

        @Override // androidx.customview.view.AbsSavedState, android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeParcelable(super.f218b, i);
            parcel.writeInt(this.f224a);
            parcel.writeInt(this.f225b);
            parcel.writeInt(this.f226c);
            parcel.writeInt(this.f227d);
            parcel.writeInt(this.f228e);
        }

        public SavedState(Parcelable parcelable) {
            super(parcelable);
            this.f224a = 0;
        }
    }

    public void a(int i, int i2) {
        View a2;
        int a3 = C.a(i2, n.g(this));
        if (i2 == 3) {
            this.r = i;
        } else if (i2 == 5) {
            this.s = i;
        } else if (i2 == 8388611) {
            this.t = i;
        } else if (i2 == 8388613) {
            this.u = i;
        }
        if (i != 0) {
            (a3 == 3 ? this.k : this.l).b();
        }
        if (i == 1) {
            View a4 = a(a3);
            if (a4 != null) {
                a(a4);
            }
        } else if (i == 2 && (a2 = a(a3)) != null) {
            l(a2);
        }
    }

    public void b(View view) {
        View rootView;
        d dVar = (d) view.getLayoutParams();
        if ((dVar.f234d & 1) == 1) {
            dVar.f234d = 0;
            List<c> list = this.x;
            if (list != null) {
                for (int size = list.size() - 1; size >= 0; size--) {
                    this.x.get(size).b(view);
                }
            }
            c(view, false);
            if (hasWindowFocus() && (rootView = getRootView()) != null) {
                rootView.sendAccessibilityEvent(32);
            }
        }
    }

    public final void c(View view, boolean z) {
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = getChildAt(i);
            if ((z || j(childAt)) && (!z || childAt != view)) {
                n.e(childAt, 4);
            } else {
                n.e(childAt, 1);
            }
        }
    }

    public void c(View view, float f) {
        d dVar = (d) view.getLayoutParams();
        if (f != dVar.f232b) {
            dVar.f232b = f;
            a(view, f);
        }
    }

    public void a(int i, int i2, View view) {
        int i3 = this.k.f904b;
        int i4 = this.l.f904b;
        int i5 = 2;
        if (i3 == 1 || i4 == 1) {
            i5 = 1;
        } else if (!(i3 == 2 || i4 == 2)) {
            i5 = 0;
        }
        if (view != null && i2 == 0) {
            float f = ((d) view.getLayoutParams()).f232b;
            if (f == 0.0f) {
                b(view);
            } else if (f == 1.0f) {
                c(view);
            }
        }
        if (i5 != this.o) {
            this.o = i5;
            List<c> list = this.x;
            if (list != null) {
                for (int size = list.size() - 1; size >= 0; size--) {
                    this.x.get(size).a(i5);
                }
            }
        }
    }

    public void b(View view, float f) {
        float f2 = f(view);
        float width = view.getWidth();
        int i = ((int) (width * f)) - ((int) (f2 * width));
        if (!a(view, 3)) {
            i = -i;
        }
        view.offsetLeftAndRight(i);
        c(view, f);
    }

    public View c() {
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = getChildAt(i);
            if ((((d) childAt.getLayoutParams()).f234d & 1) == 1) {
                return childAt;
            }
        }
        return null;
    }

    public void b() {
        a(false);
    }

    public void b(View view, boolean z) {
        if (j(view)) {
            d dVar = (d) view.getLayoutParams();
            if (this.q) {
                dVar.f232b = 1.0f;
                dVar.f234d = 1;
                c(view, true);
            } else if (z) {
                dVar.f234d |= 2;
                if (a(view, 3)) {
                    this.k.b(view, 0, view.getTop());
                } else {
                    this.l.b(view, getWidth() - view.getWidth(), view.getTop());
                }
            } else {
                b(view, 1.0f);
                a(dVar.f231a, 0, view);
                view.setVisibility(0);
            }
            invalidate();
            return;
        }
        throw new IllegalArgumentException(e.a.a.a.a.a("View ", view, " is not a sliding drawer"));
    }

    public void a(View view, float f) {
        List<c> list = this.x;
        if (list != null) {
            for (int size = list.size() - 1; size >= 0; size--) {
                this.x.get(size).a(view, f);
            }
        }
    }

    public boolean a(View view, int i) {
        return (e(view) & i) == i;
    }

    public View a(int i) {
        int a2 = C.a(i, n.g(this)) & 7;
        int childCount = getChildCount();
        for (int i2 = 0; i2 < childCount; i2++) {
            View childAt = getChildAt(i2);
            if ((e(childAt) & 7) == a2) {
                return childAt;
            }
        }
        return null;
    }

    public final boolean a(Drawable drawable, int i) {
        if (drawable == null || !C.a(drawable)) {
            return false;
        }
        C.a(drawable, i);
        return true;
    }

    public void a(boolean z) {
        boolean z2;
        int childCount = getChildCount();
        boolean z3 = false;
        for (int i = 0; i < childCount; i++) {
            View childAt = getChildAt(i);
            d dVar = (d) childAt.getLayoutParams();
            if (j(childAt) && (!z || dVar.f233c)) {
                int width = childAt.getWidth();
                if (a(childAt, 3)) {
                    z2 = this.k.b(childAt, -width, childAt.getTop());
                } else {
                    z2 = this.l.b(childAt, getWidth(), childAt.getTop());
                }
                z3 |= z2;
                dVar.f233c = false;
            }
        }
        this.m.b();
        this.n.b();
        if (z3) {
            invalidate();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class a extends c.e.h.a {

        /* renamed from: c */
        public final Rect f229c = new Rect();

        public a() {
            DrawerLayout.this = r1;
        }

        @Override // c.e.h.a
        public void a(View view, c.e.h.a.a aVar) {
            if (DrawerLayout.f221c) {
                super.a(view, aVar);
            } else {
                c.e.h.a.a aVar2 = new c.e.h.a.a(AccessibilityNodeInfo.obtain(aVar.f844a));
                super.a(view, aVar2);
                aVar.f844a.setSource(view);
                ViewParent i = n.i(view);
                if (i instanceof View) {
                    aVar.f844a.setParent((View) i);
                }
                Rect rect = this.f229c;
                aVar2.f844a.getBoundsInParent(rect);
                aVar.f844a.setBoundsInParent(rect);
                aVar2.f844a.getBoundsInScreen(rect);
                aVar.f844a.setBoundsInScreen(rect);
                int i2 = Build.VERSION.SDK_INT;
                boolean isVisibleToUser = aVar2.f844a.isVisibleToUser();
                int i3 = Build.VERSION.SDK_INT;
                aVar.f844a.setVisibleToUser(isVisibleToUser);
                aVar.f844a.setPackageName(aVar2.d());
                aVar.f844a.setClassName(aVar2.b());
                aVar.f844a.setContentDescription(aVar2.c());
                aVar.f844a.setEnabled(aVar2.f());
                aVar.f844a.setClickable(aVar2.e());
                aVar.f844a.setFocusable(aVar2.g());
                aVar.f844a.setFocused(aVar2.h());
                int i4 = Build.VERSION.SDK_INT;
                boolean isAccessibilityFocused = aVar2.f844a.isAccessibilityFocused();
                int i5 = Build.VERSION.SDK_INT;
                aVar.f844a.setAccessibilityFocused(isAccessibilityFocused);
                aVar.f844a.setSelected(aVar2.j());
                aVar.f844a.setLongClickable(aVar2.i());
                aVar.f844a.addAction(aVar2.a());
                aVar2.f844a.recycle();
                ViewGroup viewGroup = (ViewGroup) view;
                int childCount = viewGroup.getChildCount();
                for (int i6 = 0; i6 < childCount; i6++) {
                    View childAt = viewGroup.getChildAt(i6);
                    if (DrawerLayout.g(childAt)) {
                        aVar.f844a.addChild(childAt);
                    }
                }
            }
            aVar.f844a.setClassName(DrawerLayout.class.getName());
            aVar.f844a.setFocusable(false);
            aVar.f844a.setFocused(false);
            a.C0011a aVar3 = a.C0011a.f845a;
            int i7 = Build.VERSION.SDK_INT;
            aVar.f844a.removeAction((AccessibilityNodeInfo.AccessibilityAction) aVar3.f847c);
            a.C0011a aVar4 = a.C0011a.f846b;
            int i8 = Build.VERSION.SDK_INT;
            aVar.f844a.removeAction((AccessibilityNodeInfo.AccessibilityAction) aVar4.f847c);
        }

        @Override // c.e.h.a
        public void b(View view, AccessibilityEvent accessibilityEvent) {
            c.e.h.a.f841a.onInitializeAccessibilityEvent(view, accessibilityEvent);
            accessibilityEvent.setClassName(DrawerLayout.class.getName());
        }

        @Override // c.e.h.a
        public boolean a(View view, AccessibilityEvent accessibilityEvent) {
            CharSequence c2;
            if (accessibilityEvent.getEventType() != 32) {
                return c.e.h.a.f841a.dispatchPopulateAccessibilityEvent(view, accessibilityEvent);
            }
            List<CharSequence> text = accessibilityEvent.getText();
            View d2 = DrawerLayout.this.d();
            if (d2 == null || (c2 = DrawerLayout.this.c(DrawerLayout.this.e(d2))) == null) {
                return true;
            }
            text.add(c2);
            return true;
        }

        @Override // c.e.h.a
        public boolean a(ViewGroup viewGroup, View view, AccessibilityEvent accessibilityEvent) {
            if (DrawerLayout.f221c || DrawerLayout.g(view)) {
                return c.e.h.a.f841a.onRequestSendAccessibilityEvent(viewGroup, view, accessibilityEvent);
            }
            return false;
        }
    }

    public void a(View view) {
        a(view, true);
    }

    public void a(View view, boolean z) {
        if (j(view)) {
            d dVar = (d) view.getLayoutParams();
            if (this.q) {
                dVar.f232b = 0.0f;
                dVar.f234d = 0;
            } else if (z) {
                dVar.f234d |= 4;
                if (a(view, 3)) {
                    this.k.b(view, -view.getWidth(), view.getTop());
                } else {
                    this.l.b(view, getWidth(), view.getTop());
                }
            } else {
                b(view, 0.0f);
                a(dVar.f231a, 0, view);
                view.setVisibility(4);
            }
            invalidate();
            return;
        }
        throw new IllegalArgumentException(e.a.a.a.a.a("View ", view, " is not a sliding drawer"));
    }

    public void a() {
        if (!this.v) {
            long uptimeMillis = SystemClock.uptimeMillis();
            MotionEvent obtain = MotionEvent.obtain(uptimeMillis, uptimeMillis, 3, 0.0f, 0.0f, 0);
            int childCount = getChildCount();
            for (int i = 0; i < childCount; i++) {
                getChildAt(i).dispatchTouchEvent(obtain);
            }
            obtain.recycle();
            this.v = true;
        }
    }
}
