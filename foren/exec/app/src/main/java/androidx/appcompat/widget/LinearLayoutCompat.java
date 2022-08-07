package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import c.a.f.ka;
import c.a.f.xa;
import c.a.j;

/* loaded from: classes.dex */
public class LinearLayoutCompat extends ViewGroup {

    /* renamed from: a  reason: collision with root package name */
    public boolean f139a;

    /* renamed from: b  reason: collision with root package name */
    public int f140b;

    /* renamed from: c  reason: collision with root package name */
    public int f141c;

    /* renamed from: d  reason: collision with root package name */
    public int f142d;

    /* renamed from: e  reason: collision with root package name */
    public int f143e;
    public int f;
    public float g;
    public boolean h;
    public int[] i;
    public int[] j;
    public Drawable k;
    public int l;
    public int m;
    public int n;
    public int o;

    public LinearLayoutCompat(Context context) {
        this(context, null, 0);
    }

    public int a(View view) {
        return 0;
    }

    public int a(View view, int i) {
        return 0;
    }

    public void a(Canvas canvas) {
        int i;
        int i2;
        int i3;
        int i4;
        int virtualChildCount = getVirtualChildCount();
        boolean a2 = xa.a(this);
        for (int i5 = 0; i5 < virtualChildCount; i5++) {
            View a3 = a(i5);
            if (!(a3 == null || a3.getVisibility() == 8 || !b(i5))) {
                a aVar = (a) a3.getLayoutParams();
                if (a2) {
                    i4 = a3.getRight() + ((ViewGroup.MarginLayoutParams) aVar).rightMargin;
                } else {
                    i4 = (a3.getLeft() - ((ViewGroup.MarginLayoutParams) aVar).leftMargin) - this.l;
                }
                b(canvas, i4);
            }
        }
        if (b(virtualChildCount)) {
            View a4 = a(virtualChildCount - 1);
            if (a4 != null) {
                a aVar2 = (a) a4.getLayoutParams();
                if (a2) {
                    i3 = a4.getLeft() - ((ViewGroup.MarginLayoutParams) aVar2).leftMargin;
                    i2 = this.l;
                    i = i3 - i2;
                    b(canvas, i);
                }
                i = a4.getRight() + ((ViewGroup.MarginLayoutParams) aVar2).rightMargin;
                b(canvas, i);
            } else if (a2) {
                i = getPaddingLeft();
                b(canvas, i);
            } else {
                i3 = getWidth() - getPaddingRight();
                i2 = this.l;
                i = i3 - i2;
                b(canvas, i);
            }
        }
    }

    public int b(View view) {
        return 0;
    }

    public void b(Canvas canvas) {
        int i;
        int virtualChildCount = getVirtualChildCount();
        for (int i2 = 0; i2 < virtualChildCount; i2++) {
            View a2 = a(i2);
            if (!(a2 == null || a2.getVisibility() == 8 || !b(i2))) {
                a(canvas, (a2.getTop() - ((ViewGroup.MarginLayoutParams) ((a) a2.getLayoutParams())).topMargin) - this.m);
            }
        }
        if (b(virtualChildCount)) {
            View a3 = a(virtualChildCount - 1);
            if (a3 == null) {
                i = (getHeight() - getPaddingBottom()) - this.m;
            } else {
                i = a3.getBottom() + ((ViewGroup.MarginLayoutParams) ((a) a3.getLayoutParams())).bottomMargin;
            }
            a(canvas, i);
        }
    }

    public int c(int i) {
        return 0;
    }

    /* JADX WARN: Removed duplicated region for block: B:67:0x0196  */
    /* JADX WARN: Removed duplicated region for block: B:77:0x01ca  */
    /* JADX WARN: Removed duplicated region for block: B:80:0x01d5  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void c(int r39, int r40) {
        /*
            Method dump skipped, instructions count: 1301
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.widget.LinearLayoutCompat.c(int, int):void");
    }

    @Override // android.view.ViewGroup
    public boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return layoutParams instanceof a;
    }

    /* JADX WARN: Removed duplicated region for block: B:143:0x0332  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void d(int r34, int r35) {
        /*
            Method dump skipped, instructions count: 925
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.widget.LinearLayoutCompat.d(int, int):void");
    }

    @Override // android.view.View
    public int getBaseline() {
        int i;
        if (this.f140b < 0) {
            return super.getBaseline();
        }
        int childCount = getChildCount();
        int i2 = this.f140b;
        if (childCount > i2) {
            View childAt = getChildAt(i2);
            int baseline = childAt.getBaseline();
            if (baseline != -1) {
                int i3 = this.f141c;
                if (this.f142d == 1 && (i = this.f143e & 112) != 48) {
                    if (i == 16) {
                        i3 += ((((getBottom() - getTop()) - getPaddingTop()) - getPaddingBottom()) - this.f) / 2;
                    } else if (i == 80) {
                        i3 = ((getBottom() - getTop()) - getPaddingBottom()) - this.f;
                    }
                }
                return i3 + ((ViewGroup.MarginLayoutParams) ((a) childAt.getLayoutParams())).topMargin + baseline;
            } else if (this.f140b == 0) {
                return -1;
            } else {
                throw new RuntimeException("mBaselineAlignedChildIndex of LinearLayout points to a View that doesn't know how to get its baseline.");
            }
        } else {
            throw new RuntimeException("mBaselineAlignedChildIndex of LinearLayout set to an index that is out of bounds.");
        }
    }

    public int getBaselineAlignedChildIndex() {
        return this.f140b;
    }

    public Drawable getDividerDrawable() {
        return this.k;
    }

    public int getDividerPadding() {
        return this.o;
    }

    public int getDividerWidth() {
        return this.l;
    }

    public int getGravity() {
        return this.f143e;
    }

    public int getOrientation() {
        return this.f142d;
    }

    public int getShowDividers() {
        return this.n;
    }

    public int getVirtualChildCount() {
        return getChildCount();
    }

    public float getWeightSum() {
        return this.g;
    }

    @Override // android.view.View
    public void onDraw(Canvas canvas) {
        if (this.k != null) {
            if (this.f142d == 1) {
                b(canvas);
            } else {
                a(canvas);
            }
        }
    }

    @Override // android.view.View
    public void onInitializeAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        super.onInitializeAccessibilityEvent(accessibilityEvent);
        accessibilityEvent.setClassName(LinearLayoutCompat.class.getName());
    }

    @Override // android.view.View
    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        accessibilityNodeInfo.setClassName(LinearLayoutCompat.class.getName());
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        if (this.f142d == 1) {
            b(i, i2, i3, i4);
        } else {
            a(i, i2, i3, i4);
        }
    }

    @Override // android.view.View
    public void onMeasure(int i, int i2) {
        if (this.f142d == 1) {
            d(i, i2);
        } else {
            c(i, i2);
        }
    }

    public void setBaselineAligned(boolean z) {
        this.f139a = z;
    }

    public void setBaselineAlignedChildIndex(int i) {
        if (i < 0 || i >= getChildCount()) {
            StringBuilder a2 = e.a.a.a.a.a("base aligned child index out of range (0, ");
            a2.append(getChildCount());
            a2.append(")");
            throw new IllegalArgumentException(a2.toString());
        }
        this.f140b = i;
    }

    public void setDividerDrawable(Drawable drawable) {
        if (drawable != this.k) {
            this.k = drawable;
            boolean z = false;
            if (drawable != null) {
                this.l = drawable.getIntrinsicWidth();
                this.m = drawable.getIntrinsicHeight();
            } else {
                this.l = 0;
                this.m = 0;
            }
            if (drawable == null) {
                z = true;
            }
            setWillNotDraw(z);
            requestLayout();
        }
    }

    public void setDividerPadding(int i) {
        this.o = i;
    }

    public void setGravity(int i) {
        if (this.f143e != i) {
            if ((8388615 & i) == 0) {
                i |= 8388611;
            }
            if ((i & 112) == 0) {
                i |= 48;
            }
            this.f143e = i;
            requestLayout();
        }
    }

    public void setHorizontalGravity(int i) {
        int i2 = i & 8388615;
        int i3 = this.f143e;
        if ((8388615 & i3) != i2) {
            this.f143e = i2 | ((-8388616) & i3);
            requestLayout();
        }
    }

    public void setMeasureWithLargestChildEnabled(boolean z) {
        this.h = z;
    }

    public void setOrientation(int i) {
        if (this.f142d != i) {
            this.f142d = i;
            requestLayout();
        }
    }

    public void setShowDividers(int i) {
        if (i != this.n) {
            requestLayout();
        }
        this.n = i;
    }

    public void setVerticalGravity(int i) {
        int i2 = i & 112;
        int i3 = this.f143e;
        if ((i3 & 112) != i2) {
            this.f143e = i2 | (i3 & (-113));
            requestLayout();
        }
    }

    public void setWeightSum(float f) {
        this.g = Math.max(0.0f, f);
    }

    @Override // android.view.ViewGroup
    public boolean shouldDelayChildPressedState() {
        return false;
    }

    public LinearLayoutCompat(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    @Override // android.view.ViewGroup
    public a generateDefaultLayoutParams() {
        int i = this.f142d;
        if (i == 0) {
            return new a(-2, -2);
        }
        if (i == 1) {
            return new a(-1, -2);
        }
        return null;
    }

    public LinearLayoutCompat(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f139a = true;
        this.f140b = -1;
        this.f141c = 0;
        this.f143e = 8388659;
        ka a2 = ka.a(context, attributeSet, j.LinearLayoutCompat, i, 0);
        int d2 = a2.d(j.LinearLayoutCompat_android_orientation, -1);
        if (d2 >= 0) {
            setOrientation(d2);
        }
        int d3 = a2.d(j.LinearLayoutCompat_android_gravity, -1);
        if (d3 >= 0) {
            setGravity(d3);
        }
        boolean a3 = a2.a(j.LinearLayoutCompat_android_baselineAligned, true);
        if (!a3) {
            setBaselineAligned(a3);
        }
        this.g = a2.f605b.getFloat(j.LinearLayoutCompat_android_weightSum, -1.0f);
        this.f140b = a2.d(j.LinearLayoutCompat_android_baselineAlignedChildIndex, -1);
        this.h = a2.a(j.LinearLayoutCompat_measureWithLargestChild, false);
        setDividerDrawable(a2.b(j.LinearLayoutCompat_divider));
        this.n = a2.d(j.LinearLayoutCompat_showDividers, 0);
        this.o = a2.c(j.LinearLayoutCompat_dividerPadding, 0);
        a2.f605b.recycle();
    }

    @Override // android.view.ViewGroup
    public a generateLayoutParams(AttributeSet attributeSet) {
        return new a(getContext(), attributeSet);
    }

    @Override // android.view.ViewGroup
    public a generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return new a(layoutParams);
    }

    /* loaded from: classes.dex */
    public static class a extends ViewGroup.MarginLayoutParams {

        /* renamed from: a  reason: collision with root package name */
        public float f144a;

        /* renamed from: b  reason: collision with root package name */
        public int f145b;

        public a(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            this.f145b = -1;
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, j.LinearLayoutCompat_Layout);
            this.f144a = obtainStyledAttributes.getFloat(j.LinearLayoutCompat_Layout_android_layout_weight, 0.0f);
            this.f145b = obtainStyledAttributes.getInt(j.LinearLayoutCompat_Layout_android_layout_gravity, -1);
            obtainStyledAttributes.recycle();
        }

        public a(int i, int i2) {
            super(i, i2);
            this.f145b = -1;
            this.f144a = 0.0f;
        }

        public a(ViewGroup.LayoutParams layoutParams) {
            super(layoutParams);
            this.f145b = -1;
        }
    }

    public void b(Canvas canvas, int i) {
        this.k.setBounds(i, getPaddingTop() + this.o, this.l + i, (getHeight() - getPaddingBottom()) - this.o);
        this.k.draw(canvas);
    }

    public void a(Canvas canvas, int i) {
        this.k.setBounds(getPaddingLeft() + this.o, i, (getWidth() - getPaddingRight()) - this.o, this.m + i);
        this.k.draw(canvas);
    }

    public boolean b(int i) {
        if (i == 0) {
            return (this.n & 1) != 0;
        }
        if (i == getChildCount()) {
            return (this.n & 4) != 0;
        }
        if ((this.n & 2) == 0) {
            return false;
        }
        for (int i2 = i - 1; i2 >= 0; i2--) {
            if (getChildAt(i2).getVisibility() != 8) {
                return true;
            }
        }
        return false;
    }

    public View a(int i) {
        return getChildAt(i);
    }

    public final void a(int i, int i2) {
        int makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(getMeasuredHeight(), 1073741824);
        for (int i3 = 0; i3 < i; i3++) {
            View a2 = a(i3);
            if (a2.getVisibility() != 8) {
                a aVar = (a) a2.getLayoutParams();
                if (((ViewGroup.MarginLayoutParams) aVar).height == -1) {
                    int i4 = ((ViewGroup.MarginLayoutParams) aVar).width;
                    ((ViewGroup.MarginLayoutParams) aVar).width = a2.getMeasuredWidth();
                    measureChildWithMargins(a2, i2, 0, makeMeasureSpec, 0);
                    ((ViewGroup.MarginLayoutParams) aVar).width = i4;
                }
            }
        }
    }

    public final void b(int i, int i2) {
        int makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(getMeasuredWidth(), 1073741824);
        for (int i3 = 0; i3 < i; i3++) {
            View a2 = a(i3);
            if (a2.getVisibility() != 8) {
                a aVar = (a) a2.getLayoutParams();
                if (((ViewGroup.MarginLayoutParams) aVar).width == -1) {
                    int i4 = ((ViewGroup.MarginLayoutParams) aVar).height;
                    ((ViewGroup.MarginLayoutParams) aVar).height = a2.getMeasuredHeight();
                    measureChildWithMargins(a2, makeMeasureSpec, 0, i2, 0);
                    ((ViewGroup.MarginLayoutParams) aVar).height = i4;
                }
            }
        }
    }

    public void a(View view, int i, int i2, int i3, int i4, int i5) {
        measureChildWithMargins(view, i2, i3, i4, i5);
    }

    /* JADX WARN: Removed duplicated region for block: B:29:0x0096  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void b(int r12, int r13, int r14, int r15) {
        /*
            r11 = this;
            int r0 = r11.getPaddingLeft()
            int r14 = r14 - r12
            int r12 = r11.getPaddingRight()
            int r12 = r14 - r12
            int r14 = r14 - r0
            int r1 = r11.getPaddingRight()
            int r14 = r14 - r1
            int r1 = r11.getVirtualChildCount()
            int r2 = r11.f143e
            r3 = r2 & 112(0x70, float:1.57E-43)
            r4 = 8388615(0x800007, float:1.1754953E-38)
            r2 = r2 & r4
            r4 = 16
            if (r3 == r4) goto L_0x0035
            r4 = 80
            if (r3 == r4) goto L_0x002a
            int r13 = r11.getPaddingTop()
            goto L_0x0041
        L_0x002a:
            int r3 = r11.getPaddingTop()
            int r3 = r3 + r15
            int r3 = r3 - r13
            int r13 = r11.f
            int r13 = r3 - r13
            goto L_0x0041
        L_0x0035:
            int r3 = r11.getPaddingTop()
            int r15 = r15 - r13
            int r13 = r11.f
            int r15 = r15 - r13
            int r15 = r15 / 2
            int r13 = r3 + r15
        L_0x0041:
            r15 = 0
        L_0x0042:
            if (r15 >= r1) goto L_0x00b8
            android.view.View r3 = r11.a(r15)
            r4 = 1
            if (r3 != 0) goto L_0x0052
            int r3 = r11.c(r15)
            int r3 = r3 + r13
            r13 = r3
            goto L_0x00b6
        L_0x0052:
            int r5 = r3.getVisibility()
            r6 = 8
            if (r5 == r6) goto L_0x00b6
            int r5 = r3.getMeasuredWidth()
            int r6 = r3.getMeasuredHeight()
            android.view.ViewGroup$LayoutParams r7 = r3.getLayoutParams()
            androidx.appcompat.widget.LinearLayoutCompat$a r7 = (androidx.appcompat.widget.LinearLayoutCompat.a) r7
            int r8 = r7.f145b
            if (r8 >= 0) goto L_0x006d
            r8 = r2
        L_0x006d:
            int r9 = c.e.h.n.g(r11)
            int r8 = c.a.a.C.a(r8, r9)
            r8 = r8 & 7
            if (r8 == r4) goto L_0x0085
            r9 = 5
            if (r8 == r9) goto L_0x0080
            int r8 = r7.leftMargin
            int r8 = r8 + r0
            goto L_0x0090
        L_0x0080:
            int r8 = r12 - r5
            int r9 = r7.rightMargin
            goto L_0x008f
        L_0x0085:
            int r8 = r14 - r5
            int r8 = r8 / 2
            int r8 = r8 + r0
            int r9 = r7.leftMargin
            int r8 = r8 + r9
            int r9 = r7.rightMargin
        L_0x008f:
            int r8 = r8 - r9
        L_0x0090:
            boolean r9 = r11.b(r15)
            if (r9 == 0) goto L_0x0099
            int r9 = r11.m
            int r13 = r13 + r9
        L_0x0099:
            int r9 = r7.topMargin
            int r13 = r13 + r9
            int r9 = r11.a(r3)
            int r9 = r9 + r13
            int r5 = r5 + r8
            int r10 = r6 + r9
            r3.layout(r8, r9, r5, r10)
            int r5 = r7.bottomMargin
            int r6 = r6 + r5
            int r5 = r11.b(r3)
            int r5 = r5 + r6
            int r5 = r5 + r13
            int r13 = r11.a(r3, r15)
            int r15 = r15 + r13
            r13 = r5
        L_0x00b6:
            int r15 = r15 + r4
            goto L_0x0042
        L_0x00b8:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.widget.LinearLayoutCompat.b(int, int, int, int):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:29:0x00b0  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x00ba  */
    /* JADX WARN: Removed duplicated region for block: B:44:0x00e9  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x00fc  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void a(int r23, int r24, int r25, int r26) {
        /*
            Method dump skipped, instructions count: 301
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.widget.LinearLayoutCompat.a(int, int, int, int):void");
    }
}
