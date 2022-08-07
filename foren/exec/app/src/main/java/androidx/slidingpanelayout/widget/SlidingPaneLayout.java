package androidx.slidingpanelayout.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import androidx.customview.view.AbsSavedState;
import c.e.h.n;
import c.g.b.c;
import com.tencent.smtt.sdk.TbsListener;
import java.util.ArrayList;

/* loaded from: classes.dex */
public class SlidingPaneLayout extends ViewGroup {

    /* renamed from: a */
    public int f281a;

    /* renamed from: b */
    public int f282b;

    /* renamed from: c */
    public Drawable f283c;

    /* renamed from: d */
    public Drawable f284d;

    /* renamed from: e */
    public final int f285e;
    public boolean f;
    public View g;
    public float h;
    public float i;
    public int j;
    public boolean k;
    public int l;
    public float m;
    public float n;
    public final c.g.b.c o;
    public boolean p;
    public boolean q;
    public final Rect r;
    public final ArrayList<b> s;

    /* loaded from: classes.dex */
    public static class SavedState extends AbsSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new c.l.a.a();

        /* renamed from: a */
        public boolean f286a;

        public SavedState(Parcelable parcelable) {
            super(parcelable);
        }

        @Override // androidx.customview.view.AbsSavedState, android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeParcelable(this.f218b, i);
            parcel.writeInt(this.f286a ? 1 : 0);
        }

        public SavedState(Parcel parcel, ClassLoader classLoader) {
            super(parcel, classLoader);
            this.f286a = parcel.readInt() != 0;
        }
    }

    /* loaded from: classes.dex */
    public class b implements Runnable {

        /* renamed from: a */
        public final View f289a;

        public b(View view) {
            SlidingPaneLayout.this = r1;
            this.f289a = view;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (this.f289a.getParent() == SlidingPaneLayout.this) {
                this.f289a.setLayerType(0, null);
                SlidingPaneLayout.this.d(this.f289a);
            }
            SlidingPaneLayout.this.s.remove(this);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public class c extends c.a {
        public c() {
            SlidingPaneLayout.this = r1;
        }

        @Override // c.g.b.c.a
        public void a(View view, int i) {
            SlidingPaneLayout.this.f();
        }

        @Override // c.g.b.c.a
        public boolean b(View view, int i) {
            if (SlidingPaneLayout.this.k) {
                return false;
            }
            return ((d) view.getLayoutParams()).f294c;
        }

        @Override // c.g.b.c.a
        public void c(int i) {
            SlidingPaneLayout slidingPaneLayout = SlidingPaneLayout.this;
            if (slidingPaneLayout.o.f904b != 0) {
                return;
            }
            if (slidingPaneLayout.h == 0.0f) {
                slidingPaneLayout.f(slidingPaneLayout.g);
                SlidingPaneLayout slidingPaneLayout2 = SlidingPaneLayout.this;
                slidingPaneLayout2.a(slidingPaneLayout2.g);
                SlidingPaneLayout.this.p = false;
                return;
            }
            slidingPaneLayout.b(slidingPaneLayout.g);
            SlidingPaneLayout.this.p = true;
        }

        @Override // c.g.b.c.a
        public void a(View view, int i, int i2, int i3, int i4) {
            SlidingPaneLayout.this.a(i);
            SlidingPaneLayout.this.invalidate();
        }

        @Override // c.g.b.c.a
        public int b(View view, int i, int i2) {
            return view.getTop();
        }

        @Override // c.g.b.c.a
        public void a(View view, float f, float f2) {
            int i;
            d dVar = (d) view.getLayoutParams();
            if (SlidingPaneLayout.this.b()) {
                int paddingRight = SlidingPaneLayout.this.getPaddingRight() + ((ViewGroup.MarginLayoutParams) dVar).rightMargin;
                if (f < 0.0f || (f == 0.0f && SlidingPaneLayout.this.h > 0.5f)) {
                    paddingRight += SlidingPaneLayout.this.j;
                }
                i = (SlidingPaneLayout.this.getWidth() - paddingRight) - SlidingPaneLayout.this.g.getWidth();
            } else {
                i = ((ViewGroup.MarginLayoutParams) dVar).leftMargin + SlidingPaneLayout.this.getPaddingLeft();
                int i2 = (f > 0.0f ? 1 : (f == 0.0f ? 0 : -1));
                if (i2 > 0 || (i2 == 0 && SlidingPaneLayout.this.h > 0.5f)) {
                    i += SlidingPaneLayout.this.j;
                }
            }
            SlidingPaneLayout.this.o.b(i, view.getTop());
            SlidingPaneLayout.this.invalidate();
        }

        @Override // c.g.b.c.a
        public int a(View view) {
            return SlidingPaneLayout.this.j;
        }

        @Override // c.g.b.c.a
        public int a(View view, int i, int i2) {
            d dVar = (d) SlidingPaneLayout.this.g.getLayoutParams();
            if (SlidingPaneLayout.this.b()) {
                int width = SlidingPaneLayout.this.getWidth() - (SlidingPaneLayout.this.g.getWidth() + (SlidingPaneLayout.this.getPaddingRight() + ((ViewGroup.MarginLayoutParams) dVar).rightMargin));
                return Math.max(Math.min(i, width), width - SlidingPaneLayout.this.j);
            }
            int paddingLeft = SlidingPaneLayout.this.getPaddingLeft() + ((ViewGroup.MarginLayoutParams) dVar).leftMargin;
            return Math.min(Math.max(i, paddingLeft), SlidingPaneLayout.this.j + paddingLeft);
        }

        @Override // c.g.b.c.a
        public void a(int i, int i2) {
            SlidingPaneLayout slidingPaneLayout = SlidingPaneLayout.this;
            slidingPaneLayout.o.a(slidingPaneLayout.g, i2);
        }
    }

    /* loaded from: classes.dex */
    public interface e {
    }

    public SlidingPaneLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public void a(View view) {
        sendAccessibilityEvent(32);
    }

    public void b(View view) {
        sendAccessibilityEvent(32);
    }

    public void c(View view) {
    }

    public boolean c() {
        return !this.f || this.h == 1.0f;
    }

    @Override // android.view.ViewGroup
    public boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return (layoutParams instanceof d) && super.checkLayoutParams(layoutParams);
    }

    @Override // android.view.View
    public void computeScroll() {
        if (!this.o.a(true)) {
            return;
        }
        if (!this.f) {
            this.o.a();
        } else {
            n.q(this);
        }
    }

    public boolean d() {
        return this.f;
    }

    @Override // android.view.View
    public void draw(Canvas canvas) {
        Drawable drawable;
        int i;
        int i2;
        super.draw(canvas);
        if (b()) {
            drawable = this.f284d;
        } else {
            drawable = this.f283c;
        }
        View childAt = getChildCount() > 1 ? getChildAt(1) : null;
        if (childAt != null && drawable != null) {
            int top = childAt.getTop();
            int bottom = childAt.getBottom();
            int intrinsicWidth = drawable.getIntrinsicWidth();
            if (b()) {
                i2 = childAt.getRight();
                i = intrinsicWidth + i2;
            } else {
                int left = childAt.getLeft();
                i2 = left - intrinsicWidth;
                i = left;
            }
            drawable.setBounds(i2, top, i, bottom);
            drawable.draw(canvas);
        }
    }

    @Override // android.view.ViewGroup
    public boolean drawChild(Canvas canvas, View view, long j) {
        d dVar = (d) view.getLayoutParams();
        int save = canvas.save();
        if (this.f && !dVar.f294c && this.g != null) {
            canvas.getClipBounds(this.r);
            if (b()) {
                Rect rect = this.r;
                rect.left = Math.max(rect.left, this.g.getRight());
            } else {
                Rect rect2 = this.r;
                rect2.right = Math.min(rect2.right, this.g.getLeft());
            }
            canvas.clipRect(this.r);
        }
        boolean drawChild = super.drawChild(canvas, view, j);
        canvas.restoreToCount(save);
        return drawChild;
    }

    public boolean e() {
        View view = this.g;
        if (!this.q && !a(1.0f, 0)) {
            return false;
        }
        this.p = true;
        return true;
    }

    /* JADX WARN: Removed duplicated region for block: B:20:0x005e  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void f(android.view.View r18) {
        /*
            r17 = this;
            r0 = r18
            boolean r1 = r17.b()
            if (r1 == 0) goto L_0x0012
            int r2 = r17.getWidth()
            int r3 = r17.getPaddingRight()
            int r2 = r2 - r3
            goto L_0x0016
        L_0x0012:
            int r2 = r17.getPaddingLeft()
        L_0x0016:
            if (r1 == 0) goto L_0x001d
            int r3 = r17.getPaddingLeft()
            goto L_0x0026
        L_0x001d:
            int r3 = r17.getWidth()
            int r4 = r17.getPaddingRight()
            int r3 = r3 - r4
        L_0x0026:
            int r4 = r17.getPaddingTop()
            int r5 = r17.getHeight()
            int r6 = r17.getPaddingBottom()
            int r5 = r5 - r6
            if (r0 == 0) goto L_0x0053
            boolean r7 = r18.isOpaque()
            if (r7 == 0) goto L_0x003d
            r7 = 1
            goto L_0x0040
        L_0x003d:
            int r7 = android.os.Build.VERSION.SDK_INT
            r7 = 0
        L_0x0040:
            if (r7 == 0) goto L_0x0053
            int r7 = r18.getLeft()
            int r8 = r18.getRight()
            int r9 = r18.getTop()
            int r10 = r18.getBottom()
            goto L_0x0057
        L_0x0053:
            r7 = 0
            r8 = 0
            r9 = 0
            r10 = 0
        L_0x0057:
            int r11 = r17.getChildCount()
            r12 = 0
        L_0x005c:
            if (r12 >= r11) goto L_0x00b5
            r13 = r17
            android.view.View r14 = r13.getChildAt(r12)
            if (r14 != r0) goto L_0x0067
            goto L_0x00b7
        L_0x0067:
            int r15 = r14.getVisibility()
            r6 = 8
            if (r15 != r6) goto L_0x0072
            r16 = r1
            goto L_0x00ae
        L_0x0072:
            if (r1 == 0) goto L_0x0076
            r6 = r3
            goto L_0x0077
        L_0x0076:
            r6 = r2
        L_0x0077:
            int r15 = r14.getLeft()
            int r6 = java.lang.Math.max(r6, r15)
            int r15 = r14.getTop()
            int r15 = java.lang.Math.max(r4, r15)
            if (r1 == 0) goto L_0x008d
            r16 = r1
            r0 = r2
            goto L_0x0090
        L_0x008d:
            r16 = r1
            r0 = r3
        L_0x0090:
            int r1 = r14.getRight()
            int r0 = java.lang.Math.min(r0, r1)
            int r1 = r14.getBottom()
            int r1 = java.lang.Math.min(r5, r1)
            if (r6 < r7) goto L_0x00aa
            if (r15 < r9) goto L_0x00aa
            if (r0 > r8) goto L_0x00aa
            if (r1 > r10) goto L_0x00aa
            r6 = 4
            goto L_0x00ab
        L_0x00aa:
            r6 = 0
        L_0x00ab:
            r14.setVisibility(r6)
        L_0x00ae:
            int r12 = r12 + 1
            r0 = r18
            r1 = r16
            goto L_0x005c
        L_0x00b5:
            r13 = r17
        L_0x00b7:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.slidingpanelayout.widget.SlidingPaneLayout.f(android.view.View):void");
    }

    @Override // android.view.ViewGroup
    public ViewGroup.LayoutParams generateDefaultLayoutParams() {
        return new d();
    }

    @Override // android.view.ViewGroup
    public ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return layoutParams instanceof ViewGroup.MarginLayoutParams ? new d((ViewGroup.MarginLayoutParams) layoutParams) : new d(layoutParams);
    }

    public int getCoveredFadeColor() {
        return this.f282b;
    }

    public int getParallaxDistance() {
        return this.l;
    }

    public int getSliderFadeColor() {
        return this.f281a;
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
        int size = this.s.size();
        for (int i = 0; i < size; i++) {
            b bVar = this.s.get(i);
            if (bVar.f289a.getParent() == SlidingPaneLayout.this) {
                bVar.f289a.setLayerType(0, null);
                SlidingPaneLayout.this.d(bVar.f289a);
            }
            SlidingPaneLayout.this.s.remove(bVar);
        }
        this.s.clear();
    }

    @Override // android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        boolean z;
        View childAt;
        int actionMasked = motionEvent.getActionMasked();
        if (!this.f && actionMasked == 0 && getChildCount() > 1 && (childAt = getChildAt(1)) != null) {
            this.p = !this.o.a(childAt, (int) motionEvent.getX(), (int) motionEvent.getY());
        }
        if (!this.f || (this.k && actionMasked != 0)) {
            this.o.b();
            return super.onInterceptTouchEvent(motionEvent);
        } else if (actionMasked == 3 || actionMasked == 1) {
            this.o.b();
            return false;
        } else {
            if (actionMasked == 0) {
                this.k = false;
                float x = motionEvent.getX();
                float y = motionEvent.getY();
                this.m = x;
                this.n = y;
                if (this.o.a(this.g, (int) x, (int) y) && e(this.g)) {
                    z = true;
                    return this.o.c(motionEvent) || z;
                }
            } else if (actionMasked == 2) {
                float x2 = motionEvent.getX();
                float y2 = motionEvent.getY();
                float abs = Math.abs(x2 - this.m);
                float abs2 = Math.abs(y2 - this.n);
                c.g.b.c cVar = this.o;
                if (abs > cVar.f905c && abs2 > abs) {
                    cVar.b();
                    this.k = true;
                    return false;
                }
            }
            z = false;
            if (this.o.c(motionEvent)) {
                return true;
            }
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        int i5;
        int i6;
        int i7;
        int i8;
        int i9;
        boolean b2 = b();
        if (b2) {
            this.o.q = 2;
        } else {
            this.o.q = 1;
        }
        int i10 = i3 - i;
        int paddingRight = b2 ? getPaddingRight() : getPaddingLeft();
        int paddingLeft = b2 ? getPaddingLeft() : getPaddingRight();
        int paddingTop = getPaddingTop();
        int childCount = getChildCount();
        if (this.q) {
            this.h = (!this.f || !this.p) ? 0.0f : 1.0f;
        }
        int i11 = paddingRight;
        for (int i12 = 0; i12 < childCount; i12++) {
            View childAt = getChildAt(i12);
            if (childAt.getVisibility() != 8) {
                d dVar = (d) childAt.getLayoutParams();
                int measuredWidth = childAt.getMeasuredWidth();
                if (dVar.f294c) {
                    int i13 = i10 - paddingLeft;
                    int min = (Math.min(paddingRight, i13 - this.f285e) - i11) - (((ViewGroup.MarginLayoutParams) dVar).leftMargin + ((ViewGroup.MarginLayoutParams) dVar).rightMargin);
                    this.j = min;
                    int i14 = b2 ? ((ViewGroup.MarginLayoutParams) dVar).rightMargin : ((ViewGroup.MarginLayoutParams) dVar).leftMargin;
                    dVar.f295d = (measuredWidth / 2) + ((i11 + i14) + min) > i13;
                    int i15 = (int) (min * this.h);
                    i6 = i14 + i15 + i11;
                    this.h = i15 / this.j;
                    i5 = 0;
                } else if (!this.f || (i9 = this.l) == 0) {
                    i6 = paddingRight;
                    i5 = 0;
                } else {
                    i5 = (int) ((1.0f - this.h) * i9);
                    i6 = paddingRight;
                }
                if (b2) {
                    i7 = (i10 - i6) + i5;
                    i8 = i7 - measuredWidth;
                } else {
                    i8 = i6 - i5;
                    i7 = i8 + measuredWidth;
                }
                childAt.layout(i8, paddingTop, i7, childAt.getMeasuredHeight() + paddingTop);
                paddingRight = childAt.getWidth() + paddingRight;
                i11 = i6;
            }
        }
        if (this.q) {
            if (this.f) {
                if (this.l != 0) {
                    a(this.h);
                }
                if (((d) this.g.getLayoutParams()).f295d) {
                    a(this.g, this.h, this.f281a);
                }
            } else {
                for (int i16 = 0; i16 < childCount; i16++) {
                    a(getChildAt(i16), 0.0f, this.f281a);
                }
            }
            f(this.g);
        }
        this.q = false;
    }

    @Override // android.view.View
    public void onMeasure(int i, int i2) {
        int i3;
        int i4;
        int i5;
        int i6;
        int i7;
        int i8;
        int i9;
        int i10;
        int mode = View.MeasureSpec.getMode(i);
        int size = View.MeasureSpec.getSize(i);
        int mode2 = View.MeasureSpec.getMode(i2);
        int size2 = View.MeasureSpec.getSize(i2);
        if (mode != 1073741824) {
            if (!isInEditMode()) {
                throw new IllegalStateException("Width must have an exact value or MATCH_PARENT");
            } else if (mode != Integer.MIN_VALUE && mode == 0) {
                size = TbsListener.ErrorCode.ERROR_CODE_LOAD_BASE;
            }
        } else if (mode2 == 0) {
            if (!isInEditMode()) {
                throw new IllegalStateException("Height must not be UNSPECIFIED");
            } else if (mode2 == 0) {
                mode2 = Integer.MIN_VALUE;
                size2 = TbsListener.ErrorCode.ERROR_CODE_LOAD_BASE;
            }
        }
        boolean z = false;
        if (mode2 == Integer.MIN_VALUE) {
            i3 = (size2 - getPaddingTop()) - getPaddingBottom();
            i4 = 0;
        } else if (mode2 != 1073741824) {
            i4 = 0;
            i3 = 0;
        } else {
            i4 = (size2 - getPaddingTop()) - getPaddingBottom();
            i3 = i4;
        }
        int paddingLeft = (size - getPaddingLeft()) - getPaddingRight();
        int childCount = getChildCount();
        this.g = null;
        int i11 = paddingLeft;
        int i12 = 0;
        boolean z2 = false;
        float f = 0.0f;
        while (true) {
            i5 = 8;
            if (i12 >= childCount) {
                break;
            }
            View childAt = getChildAt(i12);
            d dVar = (d) childAt.getLayoutParams();
            if (childAt.getVisibility() == 8) {
                dVar.f295d = z;
            } else {
                float f2 = dVar.f293b;
                if (f2 > 0.0f) {
                    f += f2;
                    if (((ViewGroup.MarginLayoutParams) dVar).width == 0) {
                    }
                }
                int i13 = ((ViewGroup.MarginLayoutParams) dVar).leftMargin + ((ViewGroup.MarginLayoutParams) dVar).rightMargin;
                if (((ViewGroup.MarginLayoutParams) dVar).width == -2) {
                    i9 = View.MeasureSpec.makeMeasureSpec(paddingLeft - i13, Integer.MIN_VALUE);
                } else if (((ViewGroup.MarginLayoutParams) dVar).width == -1) {
                    i9 = View.MeasureSpec.makeMeasureSpec(paddingLeft - i13, 1073741824);
                } else {
                    i9 = View.MeasureSpec.makeMeasureSpec(((ViewGroup.MarginLayoutParams) dVar).width, 1073741824);
                }
                if (((ViewGroup.MarginLayoutParams) dVar).height == -2) {
                    i10 = View.MeasureSpec.makeMeasureSpec(i3, Integer.MIN_VALUE);
                } else if (((ViewGroup.MarginLayoutParams) dVar).height == -1) {
                    i10 = View.MeasureSpec.makeMeasureSpec(i3, 1073741824);
                } else {
                    i10 = View.MeasureSpec.makeMeasureSpec(((ViewGroup.MarginLayoutParams) dVar).height, 1073741824);
                }
                childAt.measure(i9, i10);
                int measuredWidth = childAt.getMeasuredWidth();
                int measuredHeight = childAt.getMeasuredHeight();
                if (mode2 == Integer.MIN_VALUE && measuredHeight > i4) {
                    i4 = Math.min(measuredHeight, i3);
                }
                i11 -= measuredWidth;
                boolean z3 = i11 < 0;
                dVar.f294c = z3;
                z2 = z3 | z2;
                if (dVar.f294c) {
                    this.g = childAt;
                }
            }
            i12++;
            z = false;
        }
        if (z2 || f > 0.0f) {
            int i14 = paddingLeft - this.f285e;
            int i15 = 0;
            while (i15 < childCount) {
                View childAt2 = getChildAt(i15);
                if (childAt2.getVisibility() != i5) {
                    d dVar2 = (d) childAt2.getLayoutParams();
                    if (childAt2.getVisibility() != i5) {
                        boolean z4 = ((ViewGroup.MarginLayoutParams) dVar2).width == 0 && dVar2.f293b > 0.0f;
                        int measuredWidth2 = z4 ? 0 : childAt2.getMeasuredWidth();
                        if (!z2 || childAt2 == this.g) {
                            if (dVar2.f293b > 0.0f) {
                                if (((ViewGroup.MarginLayoutParams) dVar2).width != 0) {
                                    i6 = View.MeasureSpec.makeMeasureSpec(childAt2.getMeasuredHeight(), 1073741824);
                                } else if (((ViewGroup.MarginLayoutParams) dVar2).height == -2) {
                                    i6 = View.MeasureSpec.makeMeasureSpec(i3, Integer.MIN_VALUE);
                                } else if (((ViewGroup.MarginLayoutParams) dVar2).height == -1) {
                                    i6 = View.MeasureSpec.makeMeasureSpec(i3, 1073741824);
                                } else {
                                    i6 = View.MeasureSpec.makeMeasureSpec(((ViewGroup.MarginLayoutParams) dVar2).height, 1073741824);
                                }
                                if (z2) {
                                    int i16 = paddingLeft - (((ViewGroup.MarginLayoutParams) dVar2).leftMargin + ((ViewGroup.MarginLayoutParams) dVar2).rightMargin);
                                    i14 = i14;
                                    int makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(i16, 1073741824);
                                    if (measuredWidth2 != i16) {
                                        childAt2.measure(makeMeasureSpec, i6);
                                    }
                                    i15++;
                                    i5 = 8;
                                } else {
                                    i14 = i14;
                                    childAt2.measure(View.MeasureSpec.makeMeasureSpec(measuredWidth2 + ((int) ((dVar2.f293b * Math.max(0, i11)) / f)), 1073741824), i6);
                                    i15++;
                                    i5 = 8;
                                }
                            }
                        } else if (((ViewGroup.MarginLayoutParams) dVar2).width < 0 && (measuredWidth2 > i14 || dVar2.f293b > 0.0f)) {
                            if (!z4) {
                                i7 = 1073741824;
                                i8 = View.MeasureSpec.makeMeasureSpec(childAt2.getMeasuredHeight(), 1073741824);
                            } else if (((ViewGroup.MarginLayoutParams) dVar2).height == -2) {
                                i8 = View.MeasureSpec.makeMeasureSpec(i3, Integer.MIN_VALUE);
                                i7 = 1073741824;
                            } else if (((ViewGroup.MarginLayoutParams) dVar2).height == -1) {
                                i7 = 1073741824;
                                i8 = View.MeasureSpec.makeMeasureSpec(i3, 1073741824);
                            } else {
                                i7 = 1073741824;
                                i8 = View.MeasureSpec.makeMeasureSpec(((ViewGroup.MarginLayoutParams) dVar2).height, 1073741824);
                            }
                            childAt2.measure(View.MeasureSpec.makeMeasureSpec(i14, i7), i8);
                        }
                    }
                }
                i14 = i14;
                i15++;
                i5 = 8;
            }
        }
        setMeasuredDimension(size, getPaddingBottom() + getPaddingTop() + i4);
        this.f = z2;
        c.g.b.c cVar = this.o;
        if (cVar.f904b != 0 && !z2) {
            cVar.a();
        }
    }

    @Override // android.view.View
    public void onRestoreInstanceState(Parcelable parcelable) {
        if (!(parcelable instanceof SavedState)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        SavedState savedState = (SavedState) parcelable;
        super.onRestoreInstanceState(savedState.a());
        if (savedState.f286a) {
            e();
        } else {
            a();
        }
        this.p = savedState.f286a;
    }

    @Override // android.view.View
    public Parcelable onSaveInstanceState() {
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        savedState.f286a = d() ? c() : this.p;
        return savedState;
    }

    @Override // android.view.View
    public void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        if (i != i3) {
            this.q = true;
        }
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (!this.f) {
            return super.onTouchEvent(motionEvent);
        }
        this.o.a(motionEvent);
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 0) {
            float x = motionEvent.getX();
            float y = motionEvent.getY();
            this.m = x;
            this.n = y;
        } else if (actionMasked == 1 && e(this.g)) {
            float x2 = motionEvent.getX();
            float y2 = motionEvent.getY();
            float f = x2 - this.m;
            float f2 = y2 - this.n;
            c.g.b.c cVar = this.o;
            int i = cVar.f905c;
            if ((f2 * f2) + (f * f) < i * i && cVar.a(this.g, (int) x2, (int) y2)) {
                a(this.g, 0);
            }
        }
        return true;
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public void requestChildFocus(View view, View view2) {
        super.requestChildFocus(view, view2);
        if (!isInTouchMode() && !this.f) {
            this.p = view == this.g;
        }
    }

    public void setCoveredFadeColor(int i) {
        this.f282b = i;
    }

    public void setPanelSlideListener(e eVar) {
    }

    public void setParallaxDistance(int i) {
        this.l = i;
        requestLayout();
    }

    @Deprecated
    public void setShadowDrawable(Drawable drawable) {
        setShadowDrawableLeft(drawable);
    }

    public void setShadowDrawableLeft(Drawable drawable) {
        this.f283c = drawable;
    }

    public void setShadowDrawableRight(Drawable drawable) {
        this.f284d = drawable;
    }

    @Deprecated
    public void setShadowResource(int i) {
        setShadowDrawable(getResources().getDrawable(i));
    }

    public void setShadowResourceLeft(int i) {
        setShadowDrawableLeft(c.e.b.a.b(getContext(), i));
    }

    public void setShadowResourceRight(int i) {
        setShadowDrawableRight(c.e.b.a.b(getContext(), i));
    }

    public void setSliderFadeColor(int i) {
        this.f281a = i;
    }

    /* loaded from: classes.dex */
    public static class d extends ViewGroup.MarginLayoutParams {

        /* renamed from: a */
        public static final int[] f292a = {16843137};

        /* renamed from: b */
        public float f293b;

        /* renamed from: c */
        public boolean f294c;

        /* renamed from: d */
        public boolean f295d;

        /* renamed from: e */
        public Paint f296e;

        public d() {
            super(-1, -1);
            this.f293b = 0.0f;
        }

        public d(ViewGroup.LayoutParams layoutParams) {
            super(layoutParams);
            this.f293b = 0.0f;
        }

        public d(ViewGroup.MarginLayoutParams marginLayoutParams) {
            super(marginLayoutParams);
            this.f293b = 0.0f;
        }

        public d(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            this.f293b = 0.0f;
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, f292a);
            this.f293b = obtainStyledAttributes.getFloat(0, 0.0f);
            obtainStyledAttributes.recycle();
        }
    }

    public SlidingPaneLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f281a = -858993460;
        this.q = true;
        this.r = new Rect();
        this.s = new ArrayList<>();
        float f = context.getResources().getDisplayMetrics().density;
        this.f285e = (int) ((32.0f * f) + 0.5f);
        setWillNotDraw(false);
        n.a(this, new a());
        n.e(this, 1);
        this.o = c.g.b.c.a(this, 0.5f, new c());
        this.o.o = f * 400.0f;
    }

    public final boolean a(View view, int i) {
        if (!this.q && !a(0.0f, i)) {
            return false;
        }
        this.p = false;
        return true;
    }

    public boolean b() {
        return n.g(this) == 1;
    }

    public void d(View view) {
        int i = Build.VERSION.SDK_INT;
        n.a(view, ((d) view.getLayoutParams()).f296e);
    }

    @Override // android.view.ViewGroup
    public ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new d(getContext(), attributeSet);
    }

    public boolean a() {
        return a(this.g, 0);
    }

    public boolean e(View view) {
        if (view == null) {
            return false;
        }
        return this.f && ((d) view.getLayoutParams()).f295d && this.h > 0.0f;
    }

    public void a(int i) {
        if (this.g == null) {
            this.h = 0.0f;
            return;
        }
        boolean b2 = b();
        d dVar = (d) this.g.getLayoutParams();
        int width = this.g.getWidth();
        if (b2) {
            i = (getWidth() - i) - width;
        }
        this.h = (i - ((b2 ? getPaddingRight() : getPaddingLeft()) + (b2 ? ((ViewGroup.MarginLayoutParams) dVar).rightMargin : ((ViewGroup.MarginLayoutParams) dVar).leftMargin))) / this.j;
        if (this.l != 0) {
            a(this.h);
        }
        if (dVar.f295d) {
            a(this.g, this.h, this.f281a);
        }
        c(this.g);
    }

    public final void a(View view, float f, int i) {
        d dVar = (d) view.getLayoutParams();
        if (f > 0.0f && i != 0) {
            int i2 = (((int) ((((-16777216) & i) >>> 24) * f)) << 24) | (i & 16777215);
            if (dVar.f296e == null) {
                dVar.f296e = new Paint();
            }
            dVar.f296e.setColorFilter(new PorterDuffColorFilter(i2, PorterDuff.Mode.SRC_OVER));
            if (view.getLayerType() != 2) {
                view.setLayerType(2, dVar.f296e);
            }
            d(view);
        } else if (view.getLayerType() != 0) {
            Paint paint = dVar.f296e;
            if (paint != null) {
                paint.setColorFilter(null);
            }
            b bVar = new b(view);
            this.s.add(bVar);
            n.a(this, bVar);
        }
    }

    public void f() {
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = getChildAt(i);
            if (childAt.getVisibility() == 4) {
                childAt.setVisibility(0);
            }
        }
    }

    public boolean a(float f, int i) {
        int i2;
        if (!this.f) {
            return false;
        }
        boolean b2 = b();
        d dVar = (d) this.g.getLayoutParams();
        if (b2) {
            int paddingRight = getPaddingRight() + ((ViewGroup.MarginLayoutParams) dVar).rightMargin;
            int width = this.g.getWidth();
            i2 = (int) (getWidth() - (((f * this.j) + paddingRight) + width));
        } else {
            i2 = (int) ((f * this.j) + getPaddingLeft() + ((ViewGroup.MarginLayoutParams) dVar).leftMargin);
        }
        c.g.b.c cVar = this.o;
        View view = this.g;
        if (!cVar.b(view, i2, view.getTop())) {
            return false;
        }
        f();
        n.q(this);
        return true;
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x0023  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void a(float r10) {
        /*
            r9 = this;
            boolean r0 = r9.b()
            android.view.View r1 = r9.g
            android.view.ViewGroup$LayoutParams r1 = r1.getLayoutParams()
            androidx.slidingpanelayout.widget.SlidingPaneLayout$d r1 = (androidx.slidingpanelayout.widget.SlidingPaneLayout.d) r1
            boolean r2 = r1.f295d
            r3 = 0
            if (r2 == 0) goto L_0x001c
            if (r0 == 0) goto L_0x0016
            int r1 = r1.rightMargin
            goto L_0x0018
        L_0x0016:
            int r1 = r1.leftMargin
        L_0x0018:
            if (r1 > 0) goto L_0x001c
            r1 = 1
            goto L_0x001d
        L_0x001c:
            r1 = 0
        L_0x001d:
            int r2 = r9.getChildCount()
        L_0x0021:
            if (r3 >= r2) goto L_0x005b
            android.view.View r4 = r9.getChildAt(r3)
            android.view.View r5 = r9.g
            if (r4 != r5) goto L_0x002c
            goto L_0x0058
        L_0x002c:
            float r5 = r9.i
            r6 = 1065353216(0x3f800000, float:1.0)
            float r5 = r6 - r5
            int r7 = r9.l
            float r8 = (float) r7
            float r5 = r5 * r8
            int r5 = (int) r5
            r9.i = r10
            float r8 = r6 - r10
            float r7 = (float) r7
            float r8 = r8 * r7
            int r7 = (int) r8
            int r5 = r5 - r7
            if (r0 == 0) goto L_0x0044
            int r5 = -r5
        L_0x0044:
            r4.offsetLeftAndRight(r5)
            if (r1 == 0) goto L_0x0058
            if (r0 == 0) goto L_0x004f
            float r5 = r9.i
            float r5 = r5 - r6
            goto L_0x0053
        L_0x004f:
            float r5 = r9.i
            float r5 = r6 - r5
        L_0x0053:
            int r6 = r9.f282b
            r9.a(r4, r5, r6)
        L_0x0058:
            int r3 = r3 + 1
            goto L_0x0021
        L_0x005b:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.slidingpanelayout.widget.SlidingPaneLayout.a(float):void");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class a extends c.e.h.a {

        /* renamed from: c */
        public final Rect f287c = new Rect();

        public a() {
            SlidingPaneLayout.this = r1;
        }

        @Override // c.e.h.a
        public void a(View view, c.e.h.a.a aVar) {
            c.e.h.a.a aVar2 = new c.e.h.a.a(AccessibilityNodeInfo.obtain(aVar.f844a));
            super.a(view, aVar2);
            Rect rect = this.f287c;
            aVar2.f844a.getBoundsInParent(rect);
            aVar.f844a.setBoundsInParent(rect);
            aVar2.f844a.getBoundsInScreen(rect);
            aVar.f844a.setBoundsInScreen(rect);
            int i = Build.VERSION.SDK_INT;
            boolean isVisibleToUser = aVar2.f844a.isVisibleToUser();
            int i2 = Build.VERSION.SDK_INT;
            aVar.f844a.setVisibleToUser(isVisibleToUser);
            aVar.f844a.setPackageName(aVar2.d());
            aVar.f844a.setClassName(aVar2.b());
            aVar.f844a.setContentDescription(aVar2.c());
            aVar.f844a.setEnabled(aVar2.f());
            aVar.f844a.setClickable(aVar2.e());
            aVar.f844a.setFocusable(aVar2.g());
            aVar.f844a.setFocused(aVar2.h());
            int i3 = Build.VERSION.SDK_INT;
            boolean isAccessibilityFocused = aVar2.f844a.isAccessibilityFocused();
            int i4 = Build.VERSION.SDK_INT;
            aVar.f844a.setAccessibilityFocused(isAccessibilityFocused);
            aVar.f844a.setSelected(aVar2.j());
            aVar.f844a.setLongClickable(aVar2.i());
            aVar.f844a.addAction(aVar2.a());
            int i5 = Build.VERSION.SDK_INT;
            int movementGranularities = aVar2.f844a.getMovementGranularities();
            int i6 = Build.VERSION.SDK_INT;
            aVar.f844a.setMovementGranularities(movementGranularities);
            aVar2.f844a.recycle();
            aVar.f844a.setClassName(SlidingPaneLayout.class.getName());
            aVar.f844a.setSource(view);
            ViewParent i7 = n.i(view);
            if (i7 instanceof View) {
                aVar.f844a.setParent((View) i7);
            }
            int childCount = SlidingPaneLayout.this.getChildCount();
            for (int i8 = 0; i8 < childCount; i8++) {
                View childAt = SlidingPaneLayout.this.getChildAt(i8);
                if (!SlidingPaneLayout.this.e(childAt) && childAt.getVisibility() == 0) {
                    n.e(childAt, 1);
                    aVar.f844a.addChild(childAt);
                }
            }
        }

        @Override // c.e.h.a
        public void b(View view, AccessibilityEvent accessibilityEvent) {
            c.e.h.a.f841a.onInitializeAccessibilityEvent(view, accessibilityEvent);
            accessibilityEvent.setClassName(SlidingPaneLayout.class.getName());
        }

        @Override // c.e.h.a
        public boolean a(ViewGroup viewGroup, View view, AccessibilityEvent accessibilityEvent) {
            if (!SlidingPaneLayout.this.e(view)) {
                return c.e.h.a.f841a.onRequestSendAccessibilityEvent(viewGroup, view, accessibilityEvent);
            }
            return false;
        }
    }
}
