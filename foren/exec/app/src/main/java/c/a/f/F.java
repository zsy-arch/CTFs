package c.a.f;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ListAdapter;
import android.widget.ListView;
import c.a.c.a.e;
import c.e.h.r;
import c.e.i.c;
import java.lang.reflect.Field;

/* loaded from: classes.dex */
public class F extends ListView {

    /* renamed from: a */
    public final Rect f528a = new Rect();

    /* renamed from: b */
    public int f529b = 0;

    /* renamed from: c */
    public int f530c = 0;

    /* renamed from: d */
    public int f531d = 0;

    /* renamed from: e */
    public int f532e = 0;
    public int f;
    public Field g;
    public a h;
    public boolean i;
    public boolean j;
    public boolean k;
    public r l;
    public c m;
    public b n;

    /* loaded from: classes.dex */
    public static class a extends e {

        /* renamed from: b */
        public boolean f533b = true;

        public a(Drawable drawable) {
            super(drawable);
        }

        @Override // c.a.c.a.e, android.graphics.drawable.Drawable
        public void draw(Canvas canvas) {
            if (this.f533b) {
                this.f401a.draw(canvas);
            }
        }

        @Override // c.a.c.a.e, android.graphics.drawable.Drawable
        public void setHotspot(float f, float f2) {
            if (this.f533b) {
                Drawable drawable = this.f401a;
                int i = Build.VERSION.SDK_INT;
                drawable.setHotspot(f, f2);
            }
        }

        @Override // c.a.c.a.e, android.graphics.drawable.Drawable
        public void setHotspotBounds(int i, int i2, int i3, int i4) {
            if (this.f533b) {
                Drawable drawable = this.f401a;
                int i5 = Build.VERSION.SDK_INT;
                drawable.setHotspotBounds(i, i2, i3, i4);
            }
        }

        @Override // c.a.c.a.e, android.graphics.drawable.Drawable
        public boolean setState(int[] iArr) {
            if (this.f533b) {
                return this.f401a.setState(iArr);
            }
            return false;
        }

        @Override // c.a.c.a.e, android.graphics.drawable.Drawable
        public boolean setVisible(boolean z, boolean z2) {
            if (this.f533b) {
                return super.setVisible(z, z2);
            }
            return false;
        }
    }

    /* loaded from: classes.dex */
    public class b implements Runnable {
        public b() {
            F.this = r1;
        }

        @Override // java.lang.Runnable
        public void run() {
            F f = F.this;
            f.n = null;
            f.drawableStateChanged();
        }
    }

    public F(Context context, boolean z) {
        super(context, null, c.a.a.dropDownListViewStyle);
        this.j = z;
        setCacheColorHint(0);
        try {
            this.g = AbsListView.class.getDeclaredField("mIsChildViewEnabled");
            this.g.setAccessible(true);
        } catch (NoSuchFieldException e2) {
            e2.printStackTrace();
        }
    }

    private void setSelectorEnabled(boolean z) {
        a aVar = this.h;
        if (aVar != null) {
            aVar.f533b = z;
        }
    }

    public int a(int i, int i2, int i3, int i4, int i5) {
        int i6;
        int listPaddingTop = getListPaddingTop();
        int listPaddingBottom = getListPaddingBottom();
        getListPaddingLeft();
        getListPaddingRight();
        int dividerHeight = getDividerHeight();
        Drawable divider = getDivider();
        ListAdapter adapter = getAdapter();
        if (adapter == null) {
            return listPaddingTop + listPaddingBottom;
        }
        int i7 = listPaddingTop + listPaddingBottom;
        if (dividerHeight <= 0 || divider == null) {
            dividerHeight = 0;
        }
        int count = adapter.getCount();
        View view = null;
        int i8 = 0;
        int i9 = 0;
        int i10 = 0;
        while (i8 < count) {
            int itemViewType = adapter.getItemViewType(i8);
            if (itemViewType != i9) {
                view = null;
                i9 = itemViewType;
            }
            view = adapter.getView(i8, view, this);
            ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
            if (layoutParams == null) {
                layoutParams = generateDefaultLayoutParams();
                view.setLayoutParams(layoutParams);
            }
            int i11 = layoutParams.height;
            if (i11 > 0) {
                i6 = View.MeasureSpec.makeMeasureSpec(i11, 1073741824);
            } else {
                i6 = View.MeasureSpec.makeMeasureSpec(0, 0);
            }
            view.measure(i, i6);
            view.forceLayout();
            if (i8 > 0) {
                i7 += dividerHeight;
            }
            i7 += view.getMeasuredHeight();
            if (i7 >= i4) {
                return (i5 < 0 || i8 <= i5 || i10 <= 0 || i7 == i4) ? i4 : i10;
            }
            if (i5 >= 0 && i8 >= i5) {
                i10 = i7;
            }
            i8++;
        }
        return i7;
    }

    @Override // android.widget.ListView, android.widget.AbsListView, android.view.ViewGroup, android.view.View
    public void dispatchDraw(Canvas canvas) {
        Drawable selector;
        if (!this.f528a.isEmpty() && (selector = getSelector()) != null) {
            selector.setBounds(this.f528a);
            selector.draw(canvas);
        }
        super.dispatchDraw(canvas);
    }

    @Override // android.widget.AbsListView, android.view.ViewGroup, android.view.View
    public void drawableStateChanged() {
        if (this.n == null) {
            super.drawableStateChanged();
            setSelectorEnabled(true);
            a();
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean hasFocus() {
        return this.j || super.hasFocus();
    }

    @Override // android.view.View
    public boolean hasWindowFocus() {
        return this.j || super.hasWindowFocus();
    }

    @Override // android.view.View
    public boolean isFocused() {
        return this.j || super.isFocused();
    }

    @Override // android.view.View
    public boolean isInTouchMode() {
        return (this.j && this.i) || super.isInTouchMode();
    }

    @Override // android.widget.ListView, android.widget.AbsListView, android.widget.AdapterView, android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        this.n = null;
        super.onDetachedFromWindow();
    }

    @Override // android.view.View
    public boolean onHoverEvent(MotionEvent motionEvent) {
        if (Build.VERSION.SDK_INT < 26) {
            return super.onHoverEvent(motionEvent);
        }
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 10 && this.n == null) {
            this.n = new b();
            b bVar = this.n;
            F.this.post(bVar);
        }
        boolean onHoverEvent = super.onHoverEvent(motionEvent);
        if (actionMasked == 9 || actionMasked == 7) {
            int pointToPosition = pointToPosition((int) motionEvent.getX(), (int) motionEvent.getY());
            if (!(pointToPosition == -1 || pointToPosition == getSelectedItemPosition())) {
                View childAt = getChildAt(pointToPosition - getFirstVisiblePosition());
                if (childAt.isEnabled()) {
                    setSelectionFromTop(pointToPosition, childAt.getTop() - getTop());
                }
                a();
            }
        } else {
            setSelection(-1);
        }
        return onHoverEvent;
    }

    @Override // android.widget.AbsListView, android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (motionEvent.getAction() == 0) {
            this.f = pointToPosition((int) motionEvent.getX(), (int) motionEvent.getY());
        }
        b bVar = this.n;
        if (bVar != null) {
            F f = F.this;
            f.n = null;
            f.removeCallbacks(bVar);
        }
        return super.onTouchEvent(motionEvent);
    }

    public void setListSelectionHidden(boolean z) {
        this.i = z;
    }

    @Override // android.widget.AbsListView
    public void setSelector(Drawable drawable) {
        this.h = drawable != null ? new a(drawable) : null;
        super.setSelector(this.h);
        Rect rect = new Rect();
        if (drawable != null) {
            drawable.getPadding(rect);
        }
        this.f529b = rect.left;
        this.f530c = rect.top;
        this.f531d = rect.right;
        this.f532e = rect.bottom;
    }

    /* JADX WARN: Code restructure failed: missing block: B:5:0x0010, code lost:
        if (r3 != 3) goto L_0x0129;
     */
    /* JADX WARN: Code restructure failed: missing block: B:81:0x0179, code lost:
        if (r5 != 3) goto L_0x01f0;
     */
    /* JADX WARN: Removed duplicated region for block: B:103:0x01f4  */
    /* JADX WARN: Removed duplicated region for block: B:104:0x01f7  */
    /* JADX WARN: Removed duplicated region for block: B:65:0x0144  */
    /* JADX WARN: Removed duplicated region for block: B:68:0x014b  */
    /* JADX WARN: Removed duplicated region for block: B:70:0x0153  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean a(android.view.MotionEvent r17, int r18) {
        /*
            Method dump skipped, instructions count: 517
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: c.a.f.F.a(android.view.MotionEvent, int):boolean");
    }

    public final void a() {
        Drawable selector = getSelector();
        if (selector != null && this.k && isPressed()) {
            selector.setState(getDrawableState());
        }
    }
}
