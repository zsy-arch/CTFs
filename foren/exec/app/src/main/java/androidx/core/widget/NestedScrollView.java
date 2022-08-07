package androidx.core.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.FocusFinder;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.accessibility.AccessibilityEvent;
import android.view.animation.AnimationUtils;
import android.widget.EdgeEffect;
import android.widget.FrameLayout;
import android.widget.OverScroller;
import android.widget.ScrollView;
import androidx.appcompat.app.AlertController;
import c.a.a.C0023d;
import c.e.h.d;
import c.e.h.f;
import c.e.h.g;
import c.e.h.i;
import c.e.h.k;
import c.e.h.n;
import com.tencent.smtt.utils.TbsLog;
import java.util.ArrayList;

/* loaded from: classes.dex */
public class NestedScrollView extends FrameLayout implements g, d, k {

    /* renamed from: a */
    public static final a f211a = new a();

    /* renamed from: b */
    public static final int[] f212b = {16843130};
    public float A;
    public b B;

    /* renamed from: c */
    public long f213c;

    /* renamed from: d */
    public final Rect f214d;

    /* renamed from: e */
    public OverScroller f215e;
    public EdgeEffect f;
    public EdgeEffect g;
    public int h;
    public boolean i;
    public boolean j;
    public View k;
    public boolean l;
    public VelocityTracker m;
    public boolean n;
    public boolean o;
    public int p;
    public int q;
    public int r;
    public int s;
    public final int[] t;
    public final int[] u;
    public int v;
    public int w;
    public SavedState x;
    public final i y;
    public final f z;

    /* loaded from: classes.dex */
    public static class SavedState extends View.BaseSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new c.e.i.d();

        /* renamed from: a */
        public int f216a;

        public SavedState(Parcelable parcelable) {
            super(parcelable);
        }

        public String toString() {
            StringBuilder a2 = e.a.a.a.a.a("HorizontalScrollView.SavedState{");
            a2.append(Integer.toHexString(System.identityHashCode(this)));
            a2.append(" scrollPosition=");
            a2.append(this.f216a);
            a2.append("}");
            return a2.toString();
        }

        @Override // android.view.View.BaseSavedState, android.view.AbsSavedState, android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeInt(this.f216a);
        }

        public SavedState(Parcel parcel) {
            super(parcel);
            this.f216a = parcel.readInt();
        }
    }

    /* loaded from: classes.dex */
    public interface b {
    }

    public NestedScrollView(Context context) {
        this(context, null, 0);
    }

    public static int a(int i, int i2, int i3) {
        if (i2 >= i3 || i < 0) {
            return 0;
        }
        return i2 + i > i3 ? i3 - i2 : i;
    }

    private float getVerticalScrollFactorCompat() {
        if (this.A == 0.0f) {
            TypedValue typedValue = new TypedValue();
            Context context = getContext();
            if (context.getTheme().resolveAttribute(16842829, typedValue, true)) {
                this.A = typedValue.getDimension(context.getResources().getDisplayMetrics());
            } else {
                throw new IllegalStateException("Expected theme to define listPreferredItemHeight.");
            }
        }
        return this.A;
    }

    public boolean a(int i, int i2, int i3, int i4, int[] iArr, int i5) {
        ViewParent a2;
        int i6;
        int i7;
        f fVar = this.z;
        if (!fVar.f858d || (a2 = fVar.a(i5)) == null) {
            return false;
        }
        if (i != 0 || i2 != 0 || i3 != 0 || i4 != 0) {
            if (iArr != null) {
                fVar.f857c.getLocationInWindow(iArr);
                i7 = iArr[0];
                i6 = iArr[1];
            } else {
                i7 = 0;
                i6 = 0;
            }
            View view = fVar.f857c;
            if (a2 instanceof g) {
                ((g) a2).a(view, i, i2, i3, i4, i5);
            } else if (i5 == 0) {
                int i8 = Build.VERSION.SDK_INT;
                try {
                    a2.onNestedScroll(view, i, i2, i3, i4);
                } catch (AbstractMethodError unused) {
                    e.a.a.a.a.a("ViewParent ", a2, " does not implement interface ", "method onNestedScroll");
                }
            }
            if (iArr != null) {
                fVar.f857c.getLocationInWindow(iArr);
                iArr[0] = iArr[0] - i7;
                iArr[1] = iArr[1] - i6;
            }
            return true;
        } else if (iArr == null) {
            return false;
        } else {
            iArr[0] = 0;
            iArr[1] = 0;
            return false;
        }
    }

    @Override // c.e.h.g
    public boolean a(View view, View view2, int i, int i2) {
        return (i & 2) != 0;
    }

    @Override // android.view.ViewGroup
    public void addView(View view) {
        if (getChildCount() <= 0) {
            super.addView(view);
            return;
        }
        throw new IllegalStateException("ScrollView can host only one direct child");
    }

    @Override // c.e.h.g
    public void b(View view, View view2, int i, int i2) {
        this.y.f860a = i;
        c(2, i2);
    }

    public boolean c(int i, int i2) {
        boolean z;
        f fVar = this.z;
        if (fVar.b(i2)) {
            return true;
        }
        if (fVar.f858d) {
            View view = fVar.f857c;
            for (ViewParent parent = fVar.f857c.getParent(); parent != null; parent = parent.getParent()) {
                View view2 = fVar.f857c;
                boolean z2 = parent instanceof g;
                if (z2) {
                    z = ((g) parent).a(view, view2, i, i2);
                } else {
                    if (i2 == 0) {
                        int i3 = Build.VERSION.SDK_INT;
                        try {
                            z = parent.onStartNestedScroll(view, view2, i);
                        } catch (AbstractMethodError unused) {
                            e.a.a.a.a.a("ViewParent ", parent, " does not implement interface ", "method onStartNestedScroll");
                        }
                    }
                    z = false;
                }
                if (z) {
                    fVar.a(i2, parent);
                    View view3 = fVar.f857c;
                    if (z2) {
                        ((g) parent).b(view, view3, i, i2);
                        return true;
                    } else if (i2 != 0) {
                        return true;
                    } else {
                        int i4 = Build.VERSION.SDK_INT;
                        try {
                            parent.onNestedScrollAccepted(view, view3, i);
                            return true;
                        } catch (AbstractMethodError unused2) {
                            e.a.a.a.a.a("ViewParent ", parent, " does not implement interface ", "method onNestedScrollAccepted");
                            return true;
                        }
                    }
                } else {
                    if (parent instanceof View) {
                        view = (View) parent;
                    }
                }
            }
        }
        return false;
    }

    @Override // android.view.View
    public int computeHorizontalScrollExtent() {
        return super.computeHorizontalScrollExtent();
    }

    @Override // android.view.View
    public int computeHorizontalScrollOffset() {
        return super.computeHorizontalScrollOffset();
    }

    @Override // android.view.View
    public int computeHorizontalScrollRange() {
        return super.computeHorizontalScrollRange();
    }

    @Override // android.view.View
    public void computeScroll() {
        if (this.f215e.computeScrollOffset()) {
            this.f215e.getCurrX();
            int currY = this.f215e.getCurrY();
            int i = currY - this.w;
            if (a(0, i, this.u, (int[]) null, 1)) {
                i -= this.u[1];
            }
            if (i != 0) {
                int scrollRange = getScrollRange();
                int scrollY = getScrollY();
                a(0, i, getScrollX(), scrollY, 0, scrollRange, 0, 0, false);
                int scrollY2 = getScrollY() - scrollY;
                if (!a(0, scrollY2, 0, i - scrollY2, (int[]) null, 1)) {
                    int overScrollMode = getOverScrollMode();
                    if (overScrollMode == 0 || (overScrollMode == 1 && scrollRange > 0)) {
                        a();
                        if (currY <= 0 && scrollY > 0) {
                            this.f.onAbsorb((int) this.f215e.getCurrVelocity());
                        } else if (currY >= scrollRange && scrollY < scrollRange) {
                            this.g.onAbsorb((int) this.f215e.getCurrVelocity());
                        }
                    }
                }
            }
            this.w = currY;
            n.q(this);
            return;
        }
        if (f(1)) {
            h(1);
        }
        this.w = 0;
    }

    @Override // android.view.View
    public int computeVerticalScrollExtent() {
        return super.computeVerticalScrollExtent();
    }

    @Override // android.view.View
    public int computeVerticalScrollOffset() {
        return Math.max(0, super.computeVerticalScrollOffset());
    }

    @Override // android.view.View
    public int computeVerticalScrollRange() {
        int childCount = getChildCount();
        int height = (getHeight() - getPaddingBottom()) - getPaddingTop();
        if (childCount == 0) {
            return height;
        }
        View childAt = getChildAt(0);
        int bottom = childAt.getBottom() + ((FrameLayout.LayoutParams) childAt.getLayoutParams()).bottomMargin;
        int scrollY = getScrollY();
        int max = Math.max(0, bottom - height);
        return scrollY < 0 ? bottom - scrollY : scrollY > max ? bottom + (scrollY - max) : bottom;
    }

    public final void d(int i) {
        int scrollY = getScrollY();
        boolean z = (scrollY > 0 || i > 0) && (scrollY < getScrollRange() || i < 0);
        float f = i;
        if (!dispatchNestedPreFling(0.0f, f)) {
            dispatchNestedFling(0.0f, f, z);
            c(i);
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        return super.dispatchKeyEvent(keyEvent) || a(keyEvent);
    }

    @Override // android.view.View
    public boolean dispatchNestedFling(float f, float f2, boolean z) {
        ViewParent a2;
        f fVar = this.z;
        if (!fVar.f858d || (a2 = fVar.a(0)) == null) {
            return false;
        }
        View view = fVar.f857c;
        int i = Build.VERSION.SDK_INT;
        try {
            return a2.onNestedFling(view, f, f2, z);
        } catch (AbstractMethodError unused) {
            e.a.a.a.a.a("ViewParent ", a2, " does not implement interface ", "method onNestedFling");
            return false;
        }
    }

    @Override // android.view.View
    public boolean dispatchNestedPreFling(float f, float f2) {
        ViewParent a2;
        f fVar = this.z;
        if (!fVar.f858d || (a2 = fVar.a(0)) == null) {
            return false;
        }
        View view = fVar.f857c;
        int i = Build.VERSION.SDK_INT;
        try {
            return a2.onNestedPreFling(view, f, f2);
        } catch (AbstractMethodError unused) {
            e.a.a.a.a.a("ViewParent ", a2, " does not implement interface ", "method onNestedPreFling");
            return false;
        }
    }

    @Override // android.view.View
    public boolean dispatchNestedPreScroll(int i, int i2, int[] iArr, int[] iArr2) {
        return a(i, i2, iArr, iArr2, 0);
    }

    @Override // android.view.View
    public boolean dispatchNestedScroll(int i, int i2, int i3, int i4, int[] iArr) {
        return a(i, i2, i3, i4, iArr, 0);
    }

    @Override // android.view.View
    public void draw(Canvas canvas) {
        int i;
        super.draw(canvas);
        if (this.f != null) {
            int scrollY = getScrollY();
            int i2 = 0;
            if (!this.f.isFinished()) {
                int save = canvas.save();
                int width = getWidth();
                int height = getHeight();
                int min = Math.min(0, scrollY);
                int i3 = Build.VERSION.SDK_INT;
                if (getClipToPadding()) {
                    width -= getPaddingRight() + getPaddingLeft();
                    i = getPaddingLeft() + 0;
                } else {
                    i = 0;
                }
                int i4 = Build.VERSION.SDK_INT;
                if (getClipToPadding()) {
                    height -= getPaddingBottom() + getPaddingTop();
                    min += getPaddingTop();
                }
                canvas.translate(i, min);
                this.f.setSize(width, height);
                if (this.f.draw(canvas)) {
                    n.q(this);
                }
                canvas.restoreToCount(save);
            }
            if (!this.g.isFinished()) {
                int save2 = canvas.save();
                int width2 = getWidth();
                int height2 = getHeight();
                int max = Math.max(getScrollRange(), scrollY) + height2;
                int i5 = Build.VERSION.SDK_INT;
                if (getClipToPadding()) {
                    width2 -= getPaddingRight() + getPaddingLeft();
                    i2 = 0 + getPaddingLeft();
                }
                int i6 = Build.VERSION.SDK_INT;
                if (getClipToPadding()) {
                    height2 -= getPaddingBottom() + getPaddingTop();
                    max -= getPaddingBottom();
                }
                canvas.translate(i2 - width2, max);
                canvas.rotate(180.0f, width2, 0.0f);
                this.g.setSize(width2, height2);
                if (this.g.draw(canvas)) {
                    n.q(this);
                }
                canvas.restoreToCount(save2);
            }
        }
    }

    public boolean e(int i) {
        int childCount;
        boolean z = i == 130;
        int height = getHeight();
        Rect rect = this.f214d;
        rect.top = 0;
        rect.bottom = height;
        if (z && (childCount = getChildCount()) > 0) {
            View childAt = getChildAt(childCount - 1);
            this.f214d.bottom = getPaddingBottom() + childAt.getBottom() + ((FrameLayout.LayoutParams) childAt.getLayoutParams()).bottomMargin;
            Rect rect2 = this.f214d;
            rect2.top = rect2.bottom - height;
        }
        Rect rect3 = this.f214d;
        return b(i, rect3.top, rect3.bottom);
    }

    public boolean f(int i) {
        return this.z.a(i) != null;
    }

    public boolean g(int i) {
        boolean z = i == 130;
        int height = getHeight();
        if (z) {
            this.f214d.top = getScrollY() + height;
            int childCount = getChildCount();
            if (childCount > 0) {
                View childAt = getChildAt(childCount - 1);
                int paddingBottom = getPaddingBottom() + childAt.getBottom() + ((FrameLayout.LayoutParams) childAt.getLayoutParams()).bottomMargin;
                Rect rect = this.f214d;
                if (rect.top + height > paddingBottom) {
                    rect.top = paddingBottom - height;
                }
            }
        } else {
            this.f214d.top = getScrollY() - height;
            Rect rect2 = this.f214d;
            if (rect2.top < 0) {
                rect2.top = 0;
            }
        }
        Rect rect3 = this.f214d;
        int i2 = rect3.top;
        rect3.bottom = height + i2;
        return b(i, i2, rect3.bottom);
    }

    @Override // android.view.View
    public float getBottomFadingEdgeStrength() {
        if (getChildCount() == 0) {
            return 0.0f;
        }
        View childAt = getChildAt(0);
        int verticalFadingEdgeLength = getVerticalFadingEdgeLength();
        int bottom = ((childAt.getBottom() + ((FrameLayout.LayoutParams) childAt.getLayoutParams()).bottomMargin) - getScrollY()) - (getHeight() - getPaddingBottom());
        if (bottom < verticalFadingEdgeLength) {
            return bottom / verticalFadingEdgeLength;
        }
        return 1.0f;
    }

    public int getMaxScrollAmount() {
        return (int) (getHeight() * 0.5f);
    }

    @Override // android.view.ViewGroup
    public int getNestedScrollAxes() {
        return this.y.f860a;
    }

    public int getScrollRange() {
        if (getChildCount() <= 0) {
            return 0;
        }
        View childAt = getChildAt(0);
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) childAt.getLayoutParams();
        return Math.max(0, ((childAt.getHeight() + layoutParams.topMargin) + layoutParams.bottomMargin) - ((getHeight() - getPaddingTop()) - getPaddingBottom()));
    }

    @Override // android.view.View
    public float getTopFadingEdgeStrength() {
        if (getChildCount() == 0) {
            return 0.0f;
        }
        int verticalFadingEdgeLength = getVerticalFadingEdgeLength();
        int scrollY = getScrollY();
        if (scrollY < verticalFadingEdgeLength) {
            return scrollY / verticalFadingEdgeLength;
        }
        return 1.0f;
    }

    public void h(int i) {
        f fVar = this.z;
        ViewParent a2 = fVar.a(i);
        if (a2 != null) {
            View view = fVar.f857c;
            if (a2 instanceof g) {
                ((g) a2).a(view, i);
            } else if (i == 0) {
                int i2 = Build.VERSION.SDK_INT;
                try {
                    a2.onStopNestedScroll(view);
                } catch (AbstractMethodError unused) {
                    e.a.a.a.a.a("ViewParent ", a2, " does not implement interface ", "method onStopNestedScroll");
                }
            }
            fVar.a(i, null);
        }
    }

    @Override // android.view.View
    public boolean hasNestedScrollingParent() {
        return f(0);
    }

    @Override // android.view.View
    public boolean isNestedScrollingEnabled() {
        return this.z.f858d;
    }

    @Override // android.view.ViewGroup
    public void measureChild(View view, int i, int i2) {
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        view.measure(FrameLayout.getChildMeasureSpec(i, getPaddingRight() + getPaddingLeft(), layoutParams.width), View.MeasureSpec.makeMeasureSpec(0, 0));
    }

    @Override // android.view.ViewGroup
    public void measureChildWithMargins(View view, int i, int i2, int i3, int i4) {
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        view.measure(FrameLayout.getChildMeasureSpec(i, getPaddingRight() + getPaddingLeft() + marginLayoutParams.leftMargin + marginLayoutParams.rightMargin + i2, marginLayoutParams.width), View.MeasureSpec.makeMeasureSpec(marginLayoutParams.topMargin + marginLayoutParams.bottomMargin, 0));
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.j = false;
    }

    @Override // android.view.View
    public boolean onGenericMotionEvent(MotionEvent motionEvent) {
        if ((motionEvent.getSource() & 2) != 0 && motionEvent.getAction() == 8 && !this.l) {
            float axisValue = motionEvent.getAxisValue(9);
            if (axisValue != 0.0f) {
                int scrollRange = getScrollRange();
                int scrollY = getScrollY();
                int verticalScrollFactorCompat = scrollY - ((int) (axisValue * getVerticalScrollFactorCompat()));
                if (verticalScrollFactorCompat < 0) {
                    verticalScrollFactorCompat = 0;
                } else if (verticalScrollFactorCompat > scrollRange) {
                    verticalScrollFactorCompat = scrollRange;
                }
                if (verticalScrollFactorCompat != scrollY) {
                    super.scrollTo(getScrollX(), verticalScrollFactorCompat);
                    return true;
                }
            }
        }
        return false;
    }

    /* JADX WARN: Removed duplicated region for block: B:49:0x00db  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x00e1  */
    @Override // android.view.ViewGroup
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean onInterceptTouchEvent(android.view.MotionEvent r12) {
        /*
            Method dump skipped, instructions count: 272
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.core.widget.NestedScrollView.onInterceptTouchEvent(android.view.MotionEvent):boolean");
    }

    @Override // android.widget.FrameLayout, android.view.ViewGroup, android.view.View
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        int i5 = 0;
        this.i = false;
        View view = this.k;
        if (view != null && a(view, this)) {
            a(this.k);
        }
        this.k = null;
        if (!this.j) {
            if (this.x != null) {
                scrollTo(getScrollX(), this.x.f216a);
                this.x = null;
            }
            if (getChildCount() > 0) {
                View childAt = getChildAt(0);
                FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) childAt.getLayoutParams();
                i5 = childAt.getMeasuredHeight() + layoutParams.topMargin + layoutParams.bottomMargin;
            }
            int paddingTop = ((i4 - i2) - getPaddingTop()) - getPaddingBottom();
            int scrollY = getScrollY();
            int a2 = a(scrollY, paddingTop, i5);
            if (a2 != scrollY) {
                scrollTo(getScrollX(), a2);
            }
        }
        scrollTo(getScrollX(), getScrollY());
        this.j = true;
    }

    @Override // android.widget.FrameLayout, android.view.View
    public void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        if (this.n && View.MeasureSpec.getMode(i2) != 0 && getChildCount() > 0) {
            View childAt = getChildAt(0);
            FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) childAt.getLayoutParams();
            int measuredHeight = childAt.getMeasuredHeight();
            int measuredHeight2 = (((getMeasuredHeight() - getPaddingTop()) - getPaddingBottom()) - layoutParams.topMargin) - layoutParams.bottomMargin;
            if (measuredHeight < measuredHeight2) {
                childAt.measure(FrameLayout.getChildMeasureSpec(i, getPaddingRight() + getPaddingLeft() + layoutParams.leftMargin + layoutParams.rightMargin, layoutParams.width), View.MeasureSpec.makeMeasureSpec(measuredHeight2, 1073741824));
            }
        }
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public boolean onNestedFling(View view, float f, float f2, boolean z) {
        if (z) {
            return false;
        }
        d((int) f2);
        return true;
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public boolean onNestedPreFling(View view, float f, float f2) {
        return dispatchNestedPreFling(f, f2);
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
    public void onOverScrolled(int i, int i2, boolean z, boolean z2) {
        super.scrollTo(i, i2);
    }

    @Override // android.view.ViewGroup
    public boolean onRequestFocusInDescendants(int i, Rect rect) {
        View view;
        if (i == 2) {
            i = 130;
        } else if (i == 1) {
            i = 33;
        }
        if (rect == null) {
            view = FocusFinder.getInstance().findNextFocus(this, null, i);
        } else {
            view = FocusFinder.getInstance().findNextFocusFromRect(this, rect, i);
        }
        if (view != null && !(true ^ a(view, 0, getHeight()))) {
            return view.requestFocus(i, rect);
        }
        return false;
    }

    @Override // android.view.View
    public void onRestoreInstanceState(Parcelable parcelable) {
        if (!(parcelable instanceof SavedState)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        SavedState savedState = (SavedState) parcelable;
        super.onRestoreInstanceState(savedState.getSuperState());
        this.x = savedState;
        requestLayout();
    }

    @Override // android.view.View
    public Parcelable onSaveInstanceState() {
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        savedState.f216a = getScrollY();
        return savedState;
    }

    @Override // android.view.View
    public void onScrollChanged(int i, int i2, int i3, int i4) {
        super.onScrollChanged(i, i2, i3, i4);
        b bVar = this.B;
        if (bVar != null) {
            C0023d dVar = (C0023d) bVar;
            AlertController.a(this, dVar.f343a, dVar.f344b);
        }
    }

    @Override // android.view.View
    public void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        View findFocus = findFocus();
        if (findFocus != null && this != findFocus && a(findFocus, 0, i4)) {
            findFocus.getDrawingRect(this.f214d);
            offsetDescendantRectToMyCoords(findFocus, this.f214d);
            b(a(this.f214d));
        }
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public boolean onStartNestedScroll(View view, View view2, int i) {
        return a(view, view2, i, 0);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public void onStopNestedScroll(View view) {
        a(view, 0);
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        ViewParent parent;
        if (this.m == null) {
            this.m = VelocityTracker.obtain();
        }
        MotionEvent obtain = MotionEvent.obtain(motionEvent);
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 0) {
            this.v = 0;
        }
        obtain.offsetLocation(0.0f, this.v);
        if (actionMasked != 0) {
            if (actionMasked == 1) {
                VelocityTracker velocityTracker = this.m;
                velocityTracker.computeCurrentVelocity(TbsLog.TBSLOG_CODE_SDK_BASE, this.r);
                int yVelocity = (int) velocityTracker.getYVelocity(this.s);
                if (Math.abs(yVelocity) > this.q) {
                    d(-yVelocity);
                } else if (this.f215e.springBack(getScrollX(), getScrollY(), 0, 0, 0, getScrollRange())) {
                    n.q(this);
                }
                this.s = -1;
                this.l = false;
                b();
                h(0);
                EdgeEffect edgeEffect = this.f;
                if (edgeEffect != null) {
                    edgeEffect.onRelease();
                    this.g.onRelease();
                }
            } else if (actionMasked == 2) {
                int findPointerIndex = motionEvent.findPointerIndex(this.s);
                if (findPointerIndex == -1) {
                    StringBuilder a2 = e.a.a.a.a.a("Invalid pointerId=");
                    a2.append(this.s);
                    a2.append(" in onTouchEvent");
                    a2.toString();
                } else {
                    int y = (int) motionEvent.getY(findPointerIndex);
                    int i = this.h - y;
                    if (a(0, i, this.u, this.t, 0)) {
                        i -= this.u[1];
                        obtain.offsetLocation(0.0f, this.t[1]);
                        this.v += this.t[1];
                    }
                    if (!this.l && Math.abs(i) > this.p) {
                        ViewParent parent2 = getParent();
                        if (parent2 != null) {
                            parent2.requestDisallowInterceptTouchEvent(true);
                        }
                        this.l = true;
                        if (i > 0) {
                            i -= this.p;
                        } else {
                            i += this.p;
                        }
                    }
                    if (this.l) {
                        this.h = y - this.t[1];
                        int scrollY = getScrollY();
                        int scrollRange = getScrollRange();
                        int overScrollMode = getOverScrollMode();
                        boolean z = overScrollMode == 0 || (overScrollMode == 1 && scrollRange > 0);
                        if (a(0, i, 0, getScrollY(), 0, scrollRange, 0, 0, true) && !f(0)) {
                            this.m.clear();
                        }
                        int scrollY2 = getScrollY() - scrollY;
                        if (a(0, scrollY2, 0, i - scrollY2, this.t, 0)) {
                            int i2 = this.h;
                            int[] iArr = this.t;
                            this.h = i2 - iArr[1];
                            obtain.offsetLocation(0.0f, iArr[1]);
                            this.v += this.t[1];
                        } else if (z) {
                            a();
                            int i3 = scrollY + i;
                            if (i3 < 0) {
                                float x = motionEvent.getX(findPointerIndex) / getWidth();
                                int i4 = Build.VERSION.SDK_INT;
                                this.f.onPull(i / getHeight(), x);
                                if (!this.g.isFinished()) {
                                    this.g.onRelease();
                                }
                            } else if (i3 > scrollRange) {
                                float x2 = 1.0f - (motionEvent.getX(findPointerIndex) / getWidth());
                                int i5 = Build.VERSION.SDK_INT;
                                this.g.onPull(i / getHeight(), x2);
                                if (!this.f.isFinished()) {
                                    this.f.onRelease();
                                }
                            }
                            EdgeEffect edgeEffect2 = this.f;
                            if (edgeEffect2 != null && (!edgeEffect2.isFinished() || !this.g.isFinished())) {
                                n.q(this);
                            }
                        }
                    }
                }
            } else if (actionMasked == 3) {
                if (this.l && getChildCount() > 0 && this.f215e.springBack(getScrollX(), getScrollY(), 0, 0, 0, getScrollRange())) {
                    n.q(this);
                }
                this.s = -1;
                this.l = false;
                b();
                h(0);
                EdgeEffect edgeEffect3 = this.f;
                if (edgeEffect3 != null) {
                    edgeEffect3.onRelease();
                    this.g.onRelease();
                }
            } else if (actionMasked == 5) {
                int actionIndex = motionEvent.getActionIndex();
                this.h = (int) motionEvent.getY(actionIndex);
                this.s = motionEvent.getPointerId(actionIndex);
            } else if (actionMasked == 6) {
                a(motionEvent);
                this.h = (int) motionEvent.getY(motionEvent.findPointerIndex(this.s));
            }
        } else if (getChildCount() == 0) {
            return false;
        } else {
            boolean z2 = !this.f215e.isFinished();
            this.l = z2;
            if (z2 && (parent = getParent()) != null) {
                parent.requestDisallowInterceptTouchEvent(true);
            }
            if (!this.f215e.isFinished()) {
                this.f215e.abortAnimation();
            }
            this.h = (int) motionEvent.getY();
            this.s = motionEvent.getPointerId(0);
            c(2, 0);
        }
        VelocityTracker velocityTracker2 = this.m;
        if (velocityTracker2 != null) {
            velocityTracker2.addMovement(obtain);
        }
        obtain.recycle();
        return true;
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public void requestChildFocus(View view, View view2) {
        if (!this.i) {
            a(view2);
        } else {
            this.k = view2;
        }
        super.requestChildFocus(view, view2);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public boolean requestChildRectangleOnScreen(View view, Rect rect, boolean z) {
        rect.offset(view.getLeft() - view.getScrollX(), view.getTop() - view.getScrollY());
        int a2 = a(rect);
        boolean z2 = a2 != 0;
        if (z2) {
            if (z) {
                scrollBy(0, a2);
            } else {
                a(0, a2);
            }
        }
        return z2;
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public void requestDisallowInterceptTouchEvent(boolean z) {
        if (z) {
            b();
        }
        super.requestDisallowInterceptTouchEvent(z);
    }

    @Override // android.view.View, android.view.ViewParent
    public void requestLayout() {
        this.i = true;
        super.requestLayout();
    }

    @Override // android.view.View
    public void scrollTo(int i, int i2) {
        if (getChildCount() > 0) {
            View childAt = getChildAt(0);
            FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) childAt.getLayoutParams();
            int a2 = a(i, (getWidth() - getPaddingLeft()) - getPaddingRight(), childAt.getWidth() + layoutParams.leftMargin + layoutParams.rightMargin);
            int a3 = a(i2, (getHeight() - getPaddingTop()) - getPaddingBottom(), childAt.getHeight() + layoutParams.topMargin + layoutParams.bottomMargin);
            if (a2 != getScrollX() || a3 != getScrollY()) {
                super.scrollTo(a2, a3);
            }
        }
    }

    public void setFillViewport(boolean z) {
        if (z != this.n) {
            this.n = z;
            requestLayout();
        }
    }

    @Override // android.view.View
    public void setNestedScrollingEnabled(boolean z) {
        f fVar = this.z;
        if (fVar.f858d) {
            n.s(fVar.f857c);
        }
        fVar.f858d = z;
    }

    public void setOnScrollChangeListener(b bVar) {
        this.B = bVar;
    }

    public void setSmoothScrollingEnabled(boolean z) {
        this.o = z;
    }

    @Override // android.widget.FrameLayout, android.view.ViewGroup
    public boolean shouldDelayChildPressedState() {
        return true;
    }

    @Override // android.view.View
    public boolean startNestedScroll(int i) {
        return c(i, 0);
    }

    @Override // android.view.View
    public void stopNestedScroll() {
        h(0);
    }

    public NestedScrollView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public NestedScrollView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f214d = new Rect();
        this.i = true;
        this.j = false;
        this.k = null;
        this.l = false;
        this.o = true;
        this.s = -1;
        this.t = new int[2];
        this.u = new int[2];
        this.f215e = new OverScroller(getContext());
        setFocusable(true);
        setDescendantFocusability(262144);
        setWillNotDraw(false);
        ViewConfiguration viewConfiguration = ViewConfiguration.get(getContext());
        this.p = viewConfiguration.getScaledTouchSlop();
        this.q = viewConfiguration.getScaledMinimumFlingVelocity();
        this.r = viewConfiguration.getScaledMaximumFlingVelocity();
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, f212b, i, 0);
        setFillViewport(obtainStyledAttributes.getBoolean(0, false));
        obtainStyledAttributes.recycle();
        this.y = new i(this);
        this.z = new f(this);
        setNestedScrollingEnabled(true);
        n.a(this, f211a);
    }

    @Override // android.view.ViewGroup
    public void addView(View view, int i) {
        if (getChildCount() <= 0) {
            super.addView(view, i);
            return;
        }
        throw new IllegalStateException("ScrollView can host only one direct child");
    }

    public final void b() {
        VelocityTracker velocityTracker = this.m;
        if (velocityTracker != null) {
            velocityTracker.recycle();
            this.m = null;
        }
    }

    @Override // android.view.ViewGroup, android.view.ViewManager
    public void addView(View view, ViewGroup.LayoutParams layoutParams) {
        if (getChildCount() <= 0) {
            super.addView(view, layoutParams);
            return;
        }
        throw new IllegalStateException("ScrollView can host only one direct child");
    }

    public final boolean b(int i, int i2, int i3) {
        boolean z;
        int height = getHeight();
        int scrollY = getScrollY();
        int i4 = height + scrollY;
        boolean z2 = i == 33;
        ArrayList focusables = getFocusables(2);
        int size = focusables.size();
        View view = null;
        boolean z3 = false;
        for (int i5 = 0; i5 < size; i5++) {
            View view2 = (View) focusables.get(i5);
            int top = view2.getTop();
            int bottom = view2.getBottom();
            if (i2 < bottom && top < i3) {
                boolean z4 = i2 < top && bottom < i3;
                if (view == null) {
                    view = view2;
                    z3 = z4;
                } else {
                    boolean z5 = (z2 && top < view.getTop()) || (!z2 && bottom > view.getBottom());
                    if (z3) {
                        if (z4) {
                            if (!z5) {
                            }
                            view = view2;
                        }
                    } else if (z4) {
                        view = view2;
                        z3 = true;
                    } else {
                        if (!z5) {
                        }
                        view = view2;
                    }
                }
            }
        }
        if (view == null) {
            view = this;
        }
        if (i2 < scrollY || i3 > i4) {
            b(z2 ? i2 - scrollY : i3 - i4);
            z = true;
        } else {
            z = false;
        }
        if (view != findFocus()) {
            view.requestFocus(i);
        }
        return z;
    }

    @Override // android.view.ViewGroup
    public void addView(View view, int i, ViewGroup.LayoutParams layoutParams) {
        if (getChildCount() <= 0) {
            super.addView(view, i, layoutParams);
            return;
        }
        throw new IllegalStateException("ScrollView can host only one direct child");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static class a extends c.e.h.a {
        @Override // c.e.h.a
        public boolean a(View view, int i, Bundle bundle) {
            int i2 = Build.VERSION.SDK_INT;
            if (c.e.h.a.f841a.performAccessibilityAction(view, i, bundle)) {
                return true;
            }
            NestedScrollView nestedScrollView = (NestedScrollView) view;
            if (!nestedScrollView.isEnabled()) {
                return false;
            }
            if (i == 4096) {
                int min = Math.min(nestedScrollView.getScrollY() + ((nestedScrollView.getHeight() - nestedScrollView.getPaddingBottom()) - nestedScrollView.getPaddingTop()), nestedScrollView.getScrollRange());
                if (min == nestedScrollView.getScrollY()) {
                    return false;
                }
                nestedScrollView.b(0, min);
                return true;
            } else if (i != 8192) {
                return false;
            } else {
                int max = Math.max(nestedScrollView.getScrollY() - ((nestedScrollView.getHeight() - nestedScrollView.getPaddingBottom()) - nestedScrollView.getPaddingTop()), 0);
                if (max == nestedScrollView.getScrollY()) {
                    return false;
                }
                nestedScrollView.b(0, max);
                return true;
            }
        }

        @Override // c.e.h.a
        public void b(View view, AccessibilityEvent accessibilityEvent) {
            c.e.h.a.f841a.onInitializeAccessibilityEvent(view, accessibilityEvent);
            NestedScrollView nestedScrollView = (NestedScrollView) view;
            accessibilityEvent.setClassName(ScrollView.class.getName());
            accessibilityEvent.setScrollable(nestedScrollView.getScrollRange() > 0);
            accessibilityEvent.setScrollX(nestedScrollView.getScrollX());
            accessibilityEvent.setScrollY(nestedScrollView.getScrollY());
            int scrollX = nestedScrollView.getScrollX();
            int i = Build.VERSION.SDK_INT;
            accessibilityEvent.setMaxScrollX(scrollX);
            int scrollRange = nestedScrollView.getScrollRange();
            int i2 = Build.VERSION.SDK_INT;
            accessibilityEvent.setMaxScrollY(scrollRange);
        }

        @Override // c.e.h.a
        public void a(View view, c.e.h.a.a aVar) {
            int scrollRange;
            super.a(view, aVar);
            NestedScrollView nestedScrollView = (NestedScrollView) view;
            aVar.f844a.setClassName(ScrollView.class.getName());
            if (nestedScrollView.isEnabled() && (scrollRange = nestedScrollView.getScrollRange()) > 0) {
                aVar.f844a.setScrollable(true);
                if (nestedScrollView.getScrollY() > 0) {
                    aVar.f844a.addAction(8192);
                }
                if (nestedScrollView.getScrollY() < scrollRange) {
                    aVar.f844a.addAction(4096);
                }
            }
        }
    }

    public boolean a(int i, int i2, int[] iArr, int[] iArr2, int i3) {
        ViewParent a2;
        int i4;
        int i5;
        int[] iArr3;
        f fVar = this.z;
        if (!fVar.f858d || (a2 = fVar.a(i3)) == null) {
            return false;
        }
        if (i != 0 || i2 != 0) {
            if (iArr2 != null) {
                fVar.f857c.getLocationInWindow(iArr2);
                i5 = iArr2[0];
                i4 = iArr2[1];
            } else {
                i5 = 0;
                i4 = 0;
            }
            if (iArr == null) {
                if (fVar.f859e == null) {
                    fVar.f859e = new int[2];
                }
                iArr3 = fVar.f859e;
            } else {
                iArr3 = iArr;
            }
            iArr3[0] = 0;
            iArr3[1] = 0;
            View view = fVar.f857c;
            if (a2 instanceof g) {
                ((g) a2).a(view, i, i2, iArr3, i3);
            } else if (i3 == 0) {
                int i6 = Build.VERSION.SDK_INT;
                try {
                    a2.onNestedPreScroll(view, i, i2, iArr3);
                } catch (AbstractMethodError unused) {
                    e.a.a.a.a.a("ViewParent ", a2, " does not implement interface ", "method onNestedPreScroll");
                }
            }
            if (iArr2 != null) {
                fVar.f857c.getLocationInWindow(iArr2);
                iArr2[0] = iArr2[0] - i5;
                iArr2[1] = iArr2[1] - i4;
            }
            return (iArr3[0] == 0 && iArr3[1] == 0) ? false : true;
        } else if (iArr2 == null) {
            return false;
        } else {
            iArr2[0] = 0;
            iArr2[1] = 0;
            return false;
        }
    }

    public final void b(int i) {
        if (i == 0) {
            return;
        }
        if (this.o) {
            a(0, i);
        } else {
            scrollBy(0, i);
        }
    }

    public final void b(int i, int i2) {
        a(i - getScrollX(), i2 - getScrollY());
    }

    public void c(int i) {
        if (getChildCount() > 0) {
            c(2, 1);
            this.f215e.fling(getScrollX(), getScrollY(), 0, i, 0, 0, Integer.MIN_VALUE, Integer.MAX_VALUE, 0, 0);
            this.w = getScrollY();
            n.q(this);
        }
    }

    @Override // c.e.h.g
    public void a(View view, int i) {
        this.y.f860a = 0;
        h(i);
    }

    @Override // c.e.h.g
    public void a(View view, int i, int i2, int i3, int i4, int i5) {
        int scrollY = getScrollY();
        scrollBy(0, i4);
        int scrollY2 = getScrollY() - scrollY;
        a(0, scrollY2, 0, i4 - scrollY2, (int[]) null, i5);
    }

    @Override // c.e.h.g
    public void a(View view, int i, int i2, int[] iArr, int i3) {
        a(i, i2, iArr, (int[]) null, i3);
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0038  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x0062  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean a(android.view.KeyEvent r6) {
        /*
            r5 = this;
            android.graphics.Rect r0 = r5.f214d
            r0.setEmpty()
            int r0 = r5.getChildCount()
            r1 = 1
            r2 = 0
            if (r0 <= 0) goto L_0x0033
            android.view.View r0 = r5.getChildAt(r2)
            android.view.ViewGroup$LayoutParams r3 = r0.getLayoutParams()
            android.widget.FrameLayout$LayoutParams r3 = (android.widget.FrameLayout.LayoutParams) r3
            int r0 = r0.getHeight()
            int r4 = r3.topMargin
            int r0 = r0 + r4
            int r3 = r3.bottomMargin
            int r0 = r0 + r3
            int r3 = r5.getHeight()
            int r4 = r5.getPaddingTop()
            int r3 = r3 - r4
            int r4 = r5.getPaddingBottom()
            int r3 = r3 - r4
            if (r0 <= r3) goto L_0x0033
            r0 = 1
            goto L_0x0034
        L_0x0033:
            r0 = 0
        L_0x0034:
            r3 = 130(0x82, float:1.82E-43)
            if (r0 != 0) goto L_0x0062
            boolean r0 = r5.isFocused()
            if (r0 == 0) goto L_0x0061
            int r6 = r6.getKeyCode()
            r0 = 4
            if (r6 == r0) goto L_0x0061
            android.view.View r6 = r5.findFocus()
            if (r6 != r5) goto L_0x004c
            r6 = 0
        L_0x004c:
            android.view.FocusFinder r0 = android.view.FocusFinder.getInstance()
            android.view.View r6 = r0.findNextFocus(r5, r6, r3)
            if (r6 == 0) goto L_0x005f
            if (r6 == r5) goto L_0x005f
            boolean r6 = r6.requestFocus(r3)
            if (r6 == 0) goto L_0x005f
            goto L_0x0060
        L_0x005f:
            r1 = 0
        L_0x0060:
            return r1
        L_0x0061:
            return r2
        L_0x0062:
            int r0 = r6.getAction()
            if (r0 != 0) goto L_0x00a6
            int r0 = r6.getKeyCode()
            r1 = 19
            r4 = 33
            if (r0 == r1) goto L_0x0097
            r1 = 20
            if (r0 == r1) goto L_0x0087
            r1 = 62
            if (r0 == r1) goto L_0x007b
            goto L_0x00a6
        L_0x007b:
            boolean r6 = r6.isShiftPressed()
            if (r6 == 0) goto L_0x0083
            r3 = 33
        L_0x0083:
            r5.g(r3)
            goto L_0x00a6
        L_0x0087:
            boolean r6 = r6.isAltPressed()
            if (r6 != 0) goto L_0x0092
            boolean r2 = r5.a(r3)
            goto L_0x00a6
        L_0x0092:
            boolean r2 = r5.e(r3)
            goto L_0x00a6
        L_0x0097:
            boolean r6 = r6.isAltPressed()
            if (r6 != 0) goto L_0x00a2
            boolean r2 = r5.a(r4)
            goto L_0x00a6
        L_0x00a2:
            boolean r2 = r5.e(r4)
        L_0x00a6:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.core.widget.NestedScrollView.a(android.view.KeyEvent):boolean");
    }

    public final void a(MotionEvent motionEvent) {
        int actionIndex = motionEvent.getActionIndex();
        if (motionEvent.getPointerId(actionIndex) == this.s) {
            int i = actionIndex == 0 ? 1 : 0;
            this.h = (int) motionEvent.getY(i);
            this.s = motionEvent.getPointerId(i);
            VelocityTracker velocityTracker = this.m;
            if (velocityTracker != null) {
                velocityTracker.clear();
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:35:0x0057  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x005a  */
    /* JADX WARN: Removed duplicated region for block: B:41:0x0061  */
    /* JADX WARN: Removed duplicated region for block: B:46:0x0083 A[ADDED_TO_REGION] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean a(int r13, int r14, int r15, int r16, int r17, int r18, int r19, int r20, boolean r21) {
        /*
            r12 = this;
            r0 = r12
            int r1 = r12.getOverScrollMode()
            int r2 = r12.computeHorizontalScrollRange()
            int r3 = r12.computeHorizontalScrollExtent()
            r4 = 0
            r5 = 1
            if (r2 <= r3) goto L_0x0013
            r2 = 1
            goto L_0x0014
        L_0x0013:
            r2 = 0
        L_0x0014:
            int r3 = r12.computeVerticalScrollRange()
            int r6 = r12.computeVerticalScrollExtent()
            if (r3 <= r6) goto L_0x0020
            r3 = 1
            goto L_0x0021
        L_0x0020:
            r3 = 0
        L_0x0021:
            if (r1 == 0) goto L_0x002a
            if (r1 != r5) goto L_0x0028
            if (r2 == 0) goto L_0x0028
            goto L_0x002a
        L_0x0028:
            r2 = 0
            goto L_0x002b
        L_0x002a:
            r2 = 1
        L_0x002b:
            if (r1 == 0) goto L_0x0034
            if (r1 != r5) goto L_0x0032
            if (r3 == 0) goto L_0x0032
            goto L_0x0034
        L_0x0032:
            r1 = 0
            goto L_0x0035
        L_0x0034:
            r1 = 1
        L_0x0035:
            int r3 = r15 + r13
            if (r2 != 0) goto L_0x003b
            r2 = 0
            goto L_0x003d
        L_0x003b:
            r2 = r19
        L_0x003d:
            int r6 = r16 + r14
            if (r1 != 0) goto L_0x0043
            r1 = 0
            goto L_0x0045
        L_0x0043:
            r1 = r20
        L_0x0045:
            int r7 = -r2
            int r2 = r2 + r17
            int r8 = -r1
            int r1 = r1 + r18
            if (r3 <= r2) goto L_0x0050
            r7 = r2
        L_0x004e:
            r2 = 1
            goto L_0x0055
        L_0x0050:
            if (r3 >= r7) goto L_0x0053
            goto L_0x004e
        L_0x0053:
            r7 = r3
            r2 = 0
        L_0x0055:
            if (r6 <= r1) goto L_0x005a
            r6 = r1
        L_0x0058:
            r1 = 1
            goto L_0x005f
        L_0x005a:
            if (r6 >= r8) goto L_0x005e
            r6 = r8
            goto L_0x0058
        L_0x005e:
            r1 = 0
        L_0x005f:
            if (r1 == 0) goto L_0x007e
            boolean r3 = r12.f(r5)
            if (r3 != 0) goto L_0x007e
            android.widget.OverScroller r3 = r0.f215e
            r8 = 0
            r9 = 0
            r10 = 0
            int r11 = r12.getScrollRange()
            r13 = r3
            r14 = r7
            r15 = r6
            r16 = r8
            r17 = r9
            r18 = r10
            r19 = r11
            r13.springBack(r14, r15, r16, r17, r18, r19)
        L_0x007e:
            r12.onOverScrolled(r7, r6, r2, r1)
            if (r2 != 0) goto L_0x0085
            if (r1 == 0) goto L_0x0086
        L_0x0085:
            r4 = 1
        L_0x0086:
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.core.widget.NestedScrollView.a(int, int, int, int, int, int, int, int, boolean):boolean");
    }

    public boolean a(int i) {
        View findFocus = findFocus();
        if (findFocus == this) {
            findFocus = null;
        }
        View findNextFocus = FocusFinder.getInstance().findNextFocus(this, findFocus, i);
        int maxScrollAmount = getMaxScrollAmount();
        if (findNextFocus == null || !a(findNextFocus, maxScrollAmount, getHeight())) {
            if (i == 33 && getScrollY() < maxScrollAmount) {
                maxScrollAmount = getScrollY();
            } else if (i == 130 && getChildCount() > 0) {
                View childAt = getChildAt(0);
                maxScrollAmount = Math.min((childAt.getBottom() + ((FrameLayout.LayoutParams) childAt.getLayoutParams()).bottomMargin) - ((getHeight() + getScrollY()) - getPaddingBottom()), maxScrollAmount);
            }
            if (maxScrollAmount == 0) {
                return false;
            }
            if (i != 130) {
                maxScrollAmount = -maxScrollAmount;
            }
            b(maxScrollAmount);
        } else {
            findNextFocus.getDrawingRect(this.f214d);
            offsetDescendantRectToMyCoords(findNextFocus, this.f214d);
            b(a(this.f214d));
            findNextFocus.requestFocus(i);
        }
        if (findFocus != null && findFocus.isFocused() && (!a(findFocus, 0, getHeight()))) {
            int descendantFocusability = getDescendantFocusability();
            setDescendantFocusability(131072);
            requestFocus();
            setDescendantFocusability(descendantFocusability);
        }
        return true;
    }

    public final boolean a(View view, int i, int i2) {
        view.getDrawingRect(this.f214d);
        offsetDescendantRectToMyCoords(view, this.f214d);
        return this.f214d.bottom + i >= getScrollY() && this.f214d.top - i <= getScrollY() + i2;
    }

    public final void a(int i, int i2) {
        if (getChildCount() != 0) {
            if (AnimationUtils.currentAnimationTimeMillis() - this.f213c > 250) {
                View childAt = getChildAt(0);
                FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) childAt.getLayoutParams();
                int height = childAt.getHeight() + layoutParams.topMargin + layoutParams.bottomMargin;
                int height2 = (getHeight() - getPaddingTop()) - getPaddingBottom();
                int scrollY = getScrollY();
                this.w = getScrollY();
                OverScroller overScroller = this.f215e;
                int scrollX = getScrollX();
                overScroller.startScroll(scrollX, scrollY, 0, Math.max(0, Math.min(i2 + scrollY, Math.max(0, height - height2))) - scrollY);
                n.q(this);
            } else {
                if (!this.f215e.isFinished()) {
                    this.f215e.abortAnimation();
                }
                scrollBy(i, i2);
            }
            this.f213c = AnimationUtils.currentAnimationTimeMillis();
        }
    }

    public final void a(View view) {
        view.getDrawingRect(this.f214d);
        offsetDescendantRectToMyCoords(view, this.f214d);
        int a2 = a(this.f214d);
        if (a2 != 0) {
            scrollBy(0, a2);
        }
    }

    public int a(Rect rect) {
        int i;
        int i2;
        if (getChildCount() == 0) {
            return 0;
        }
        int height = getHeight();
        int scrollY = getScrollY();
        int i3 = scrollY + height;
        int verticalFadingEdgeLength = getVerticalFadingEdgeLength();
        if (rect.top > 0) {
            scrollY += verticalFadingEdgeLength;
        }
        View childAt = getChildAt(0);
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) childAt.getLayoutParams();
        int i4 = rect.bottom < (childAt.getHeight() + layoutParams.topMargin) + layoutParams.bottomMargin ? i3 - verticalFadingEdgeLength : i3;
        if (rect.bottom > i4 && rect.top > scrollY) {
            if (rect.height() > height) {
                i2 = rect.top - scrollY;
            } else {
                i2 = rect.bottom - i4;
            }
            return Math.min(i2 + 0, (childAt.getBottom() + layoutParams.bottomMargin) - i3);
        } else if (rect.top >= scrollY || rect.bottom >= i4) {
            return 0;
        } else {
            if (rect.height() > height) {
                i = 0 - (i4 - rect.bottom);
            } else {
                i = 0 - (scrollY - rect.top);
            }
            return Math.max(i, -getScrollY());
        }
    }

    public static boolean a(View view, View view2) {
        if (view == view2) {
            return true;
        }
        ViewParent parent = view.getParent();
        return (parent instanceof ViewGroup) && a((View) parent, view2);
    }

    public final void a() {
        if (getOverScrollMode() == 2) {
            this.f = null;
            this.g = null;
        } else if (this.f == null) {
            Context context = getContext();
            this.f = new EdgeEffect(context);
            this.g = new EdgeEffect(context);
        }
    }
}
