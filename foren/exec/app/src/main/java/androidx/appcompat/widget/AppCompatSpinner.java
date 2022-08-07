package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.database.DataSetObserver;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ListAdapter;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.ThemedSpinnerAdapter;
import androidx.appcompat.widget.ListPopupWindow;
import c.a.f.C0047o;
import c.a.f.C0054w;
import c.a.f.C0056y;
import c.a.f.F;
import c.a.f.H;
import c.a.f.ViewTreeObserver$OnGlobalLayoutListenerC0055x;
import c.a.f.ga;
import c.a.f.xa;
import c.e.h.l;
import c.e.h.n;

/* loaded from: classes.dex */
public class AppCompatSpinner extends Spinner implements l {

    /* renamed from: a */
    public static final int[] f119a = {16843505};

    /* renamed from: b */
    public final C0047o f120b;

    /* renamed from: c */
    public final Context f121c;

    /* renamed from: d */
    public H f122d;

    /* renamed from: e */
    public SpinnerAdapter f123e;
    public final boolean f;
    public b g;
    public int h;
    public final Rect i;

    /* loaded from: classes.dex */
    public static class a implements ListAdapter, SpinnerAdapter {

        /* renamed from: a */
        public SpinnerAdapter f124a;

        /* renamed from: b */
        public ListAdapter f125b;

        public a(SpinnerAdapter spinnerAdapter, Resources.Theme theme) {
            this.f124a = spinnerAdapter;
            if (spinnerAdapter instanceof ListAdapter) {
                this.f125b = (ListAdapter) spinnerAdapter;
            }
            if (theme == null) {
                return;
            }
            if (Build.VERSION.SDK_INT >= 23 && (spinnerAdapter instanceof ThemedSpinnerAdapter)) {
                ThemedSpinnerAdapter themedSpinnerAdapter = (ThemedSpinnerAdapter) spinnerAdapter;
                if (themedSpinnerAdapter.getDropDownViewTheme() != theme) {
                    themedSpinnerAdapter.setDropDownViewTheme(theme);
                }
            } else if (spinnerAdapter instanceof ga) {
                ga gaVar = (ga) spinnerAdapter;
                if (gaVar.getDropDownViewTheme() == null) {
                    gaVar.setDropDownViewTheme(theme);
                }
            }
        }

        @Override // android.widget.ListAdapter
        public boolean areAllItemsEnabled() {
            ListAdapter listAdapter = this.f125b;
            if (listAdapter != null) {
                return listAdapter.areAllItemsEnabled();
            }
            return true;
        }

        @Override // android.widget.Adapter
        public int getCount() {
            SpinnerAdapter spinnerAdapter = this.f124a;
            if (spinnerAdapter == null) {
                return 0;
            }
            return spinnerAdapter.getCount();
        }

        @Override // android.widget.SpinnerAdapter
        public View getDropDownView(int i, View view, ViewGroup viewGroup) {
            SpinnerAdapter spinnerAdapter = this.f124a;
            if (spinnerAdapter == null) {
                return null;
            }
            return spinnerAdapter.getDropDownView(i, view, viewGroup);
        }

        @Override // android.widget.Adapter
        public Object getItem(int i) {
            SpinnerAdapter spinnerAdapter = this.f124a;
            if (spinnerAdapter == null) {
                return null;
            }
            return spinnerAdapter.getItem(i);
        }

        @Override // android.widget.Adapter
        public long getItemId(int i) {
            SpinnerAdapter spinnerAdapter = this.f124a;
            if (spinnerAdapter == null) {
                return -1L;
            }
            return spinnerAdapter.getItemId(i);
        }

        @Override // android.widget.Adapter
        public int getItemViewType(int i) {
            return 0;
        }

        @Override // android.widget.Adapter
        public View getView(int i, View view, ViewGroup viewGroup) {
            SpinnerAdapter spinnerAdapter = this.f124a;
            if (spinnerAdapter == null) {
                return null;
            }
            return spinnerAdapter.getDropDownView(i, view, viewGroup);
        }

        @Override // android.widget.Adapter
        public int getViewTypeCount() {
            return 1;
        }

        @Override // android.widget.Adapter
        public boolean hasStableIds() {
            SpinnerAdapter spinnerAdapter = this.f124a;
            return spinnerAdapter != null && spinnerAdapter.hasStableIds();
        }

        @Override // android.widget.Adapter
        public boolean isEmpty() {
            SpinnerAdapter spinnerAdapter = this.f124a;
            return (spinnerAdapter == null ? 0 : spinnerAdapter.getCount()) == 0;
        }

        @Override // android.widget.ListAdapter
        public boolean isEnabled(int i) {
            ListAdapter listAdapter = this.f125b;
            if (listAdapter != null) {
                return listAdapter.isEnabled(i);
            }
            return true;
        }

        @Override // android.widget.Adapter
        public void registerDataSetObserver(DataSetObserver dataSetObserver) {
            SpinnerAdapter spinnerAdapter = this.f124a;
            if (spinnerAdapter != null) {
                spinnerAdapter.registerDataSetObserver(dataSetObserver);
            }
        }

        @Override // android.widget.Adapter
        public void unregisterDataSetObserver(DataSetObserver dataSetObserver) {
            SpinnerAdapter spinnerAdapter = this.f124a;
            if (spinnerAdapter != null) {
                spinnerAdapter.unregisterDataSetObserver(dataSetObserver);
            }
        }
    }

    public AppCompatSpinner(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, c.a.a.spinnerStyle);
    }

    public int a(SpinnerAdapter spinnerAdapter, Drawable drawable) {
        int i = 0;
        if (spinnerAdapter == null) {
            return 0;
        }
        int makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(getMeasuredWidth(), 0);
        int makeMeasureSpec2 = View.MeasureSpec.makeMeasureSpec(getMeasuredHeight(), 0);
        int max = Math.max(0, getSelectedItemPosition());
        int min = Math.min(spinnerAdapter.getCount(), max + 15);
        View view = null;
        int i2 = 0;
        for (int max2 = Math.max(0, max - (15 - (min - max))); max2 < min; max2++) {
            int itemViewType = spinnerAdapter.getItemViewType(max2);
            if (itemViewType != i) {
                view = null;
                i = itemViewType;
            }
            view = spinnerAdapter.getView(max2, view, this);
            if (view.getLayoutParams() == null) {
                view.setLayoutParams(new ViewGroup.LayoutParams(-2, -2));
            }
            view.measure(makeMeasureSpec, makeMeasureSpec2);
            i2 = Math.max(i2, view.getMeasuredWidth());
        }
        if (drawable == null) {
            return i2;
        }
        drawable.getPadding(this.i);
        Rect rect = this.i;
        return i2 + rect.left + rect.right;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void drawableStateChanged() {
        super.drawableStateChanged();
        C0047o oVar = this.f120b;
        if (oVar != null) {
            oVar.a();
        }
    }

    @Override // android.widget.Spinner
    public int getDropDownHorizontalOffset() {
        b bVar = this.g;
        if (bVar != null) {
            return bVar.g();
        }
        int i = Build.VERSION.SDK_INT;
        return super.getDropDownHorizontalOffset();
    }

    @Override // android.widget.Spinner
    public int getDropDownVerticalOffset() {
        b bVar = this.g;
        if (bVar != null) {
            return bVar.h();
        }
        int i = Build.VERSION.SDK_INT;
        return super.getDropDownVerticalOffset();
    }

    @Override // android.widget.Spinner
    public int getDropDownWidth() {
        if (this.g != null) {
            return this.h;
        }
        int i = Build.VERSION.SDK_INT;
        return super.getDropDownWidth();
    }

    @Override // android.widget.Spinner
    public Drawable getPopupBackground() {
        b bVar = this.g;
        if (bVar != null) {
            return bVar.f();
        }
        int i = Build.VERSION.SDK_INT;
        return super.getPopupBackground();
    }

    @Override // android.widget.Spinner
    public Context getPopupContext() {
        if (this.g != null) {
            return this.f121c;
        }
        if (Build.VERSION.SDK_INT >= 23) {
            return super.getPopupContext();
        }
        return null;
    }

    @Override // android.widget.Spinner
    public CharSequence getPrompt() {
        b bVar = this.g;
        if (bVar != null) {
            return bVar.I;
        }
        return super.getPrompt();
    }

    public ColorStateList getSupportBackgroundTintList() {
        C0047o oVar = this.f120b;
        if (oVar != null) {
            return oVar.b();
        }
        return null;
    }

    public PorterDuff.Mode getSupportBackgroundTintMode() {
        C0047o oVar = this.f120b;
        if (oVar != null) {
            return oVar.c();
        }
        return null;
    }

    @Override // android.widget.Spinner, android.widget.AdapterView, android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        b bVar = this.g;
        if (bVar != null && bVar.b()) {
            this.g.dismiss();
        }
    }

    @Override // android.widget.Spinner, android.widget.AbsSpinner, android.view.View
    public void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        if (this.g != null && View.MeasureSpec.getMode(i) == Integer.MIN_VALUE) {
            setMeasuredDimension(Math.min(Math.max(getMeasuredWidth(), a(getAdapter(), getBackground())), View.MeasureSpec.getSize(i)), getMeasuredHeight());
        }
    }

    @Override // android.widget.Spinner, android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        H h = this.f122d;
        if (h == null || !h.onTouch(this, motionEvent)) {
            return super.onTouchEvent(motionEvent);
        }
        return true;
    }

    @Override // android.widget.Spinner, android.view.View
    public boolean performClick() {
        b bVar = this.g;
        if (bVar == null) {
            return super.performClick();
        }
        if (bVar.b()) {
            return true;
        }
        this.g.c();
        return true;
    }

    @Override // android.view.View
    public void setBackgroundDrawable(Drawable drawable) {
        super.setBackgroundDrawable(drawable);
        C0047o oVar = this.f120b;
        if (oVar != null) {
            oVar.f613c = -1;
            oVar.a((ColorStateList) null);
            oVar.a();
        }
    }

    @Override // android.view.View
    public void setBackgroundResource(int i) {
        super.setBackgroundResource(i);
        C0047o oVar = this.f120b;
        if (oVar != null) {
            oVar.a(i);
        }
    }

    @Override // android.widget.Spinner
    public void setDropDownHorizontalOffset(int i) {
        b bVar = this.g;
        if (bVar != null) {
            bVar.d(i);
            return;
        }
        int i2 = Build.VERSION.SDK_INT;
        super.setDropDownHorizontalOffset(i);
    }

    @Override // android.widget.Spinner
    public void setDropDownVerticalOffset(int i) {
        b bVar = this.g;
        if (bVar != null) {
            bVar.h(i);
            return;
        }
        int i2 = Build.VERSION.SDK_INT;
        super.setDropDownVerticalOffset(i);
    }

    @Override // android.widget.Spinner
    public void setDropDownWidth(int i) {
        if (this.g != null) {
            this.h = i;
            return;
        }
        int i2 = Build.VERSION.SDK_INT;
        super.setDropDownWidth(i);
    }

    @Override // android.widget.Spinner
    public void setPopupBackgroundDrawable(Drawable drawable) {
        b bVar = this.g;
        if (bVar != null) {
            bVar.a(drawable);
            return;
        }
        int i = Build.VERSION.SDK_INT;
        super.setPopupBackgroundDrawable(drawable);
    }

    @Override // android.widget.Spinner
    public void setPopupBackgroundResource(int i) {
        setPopupBackgroundDrawable(c.a.b.a.a.c(getPopupContext(), i));
    }

    @Override // android.widget.Spinner
    public void setPrompt(CharSequence charSequence) {
        b bVar = this.g;
        if (bVar != null) {
            bVar.I = charSequence;
        } else {
            super.setPrompt(charSequence);
        }
    }

    public void setSupportBackgroundTintList(ColorStateList colorStateList) {
        C0047o oVar = this.f120b;
        if (oVar != null) {
            oVar.b(colorStateList);
        }
    }

    public void setSupportBackgroundTintMode(PorterDuff.Mode mode) {
        C0047o oVar = this.f120b;
        if (oVar != null) {
            oVar.a(mode);
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:24:0x0056, code lost:
        if (r5 == null) goto L_0x005b;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public AppCompatSpinner(android.content.Context r8, android.util.AttributeSet r9, int r10) {
        /*
            r7 = this;
            r7.<init>(r8, r9, r10)
            android.graphics.Rect r0 = new android.graphics.Rect
            r0.<init>()
            r7.i = r0
            int[] r0 = c.a.j.Spinner
            r1 = 0
            c.a.f.ka r0 = c.a.f.ka.a(r8, r9, r0, r10, r1)
            c.a.f.o r2 = new c.a.f.o
            r2.<init>(r7)
            r7.f120b = r2
            r2 = 0
            int r3 = c.a.j.Spinner_popupTheme
            int r3 = r0.f(r3, r1)
            if (r3 == 0) goto L_0x0029
            c.a.e.c r4 = new c.a.e.c
            r4.<init>(r8, r3)
            r7.f121c = r4
            goto L_0x0034
        L_0x0029:
            int r3 = android.os.Build.VERSION.SDK_INT
            r4 = 23
            if (r3 >= r4) goto L_0x0031
            r3 = r8
            goto L_0x0032
        L_0x0031:
            r3 = r2
        L_0x0032:
            r7.f121c = r3
        L_0x0034:
            android.content.Context r3 = r7.f121c
            r4 = 1
            if (r3 == 0) goto L_0x0095
            r3 = -1
            int[] r5 = androidx.appcompat.widget.AppCompatSpinner.f119a     // Catch: Exception -> 0x0055, all -> 0x004e
            android.content.res.TypedArray r5 = r8.obtainStyledAttributes(r9, r5, r10, r1)     // Catch: Exception -> 0x0055, all -> 0x004e
            boolean r6 = r5.hasValue(r1)     // Catch: Exception -> 0x0056, all -> 0x004b
            if (r6 == 0) goto L_0x0058
            int r3 = r5.getInt(r1, r1)     // Catch: Exception -> 0x0056, all -> 0x004b
            goto L_0x0058
        L_0x004b:
            r8 = move-exception
            r2 = r5
            goto L_0x004f
        L_0x004e:
            r8 = move-exception
        L_0x004f:
            if (r2 == 0) goto L_0x0054
            r2.recycle()
        L_0x0054:
            throw r8
        L_0x0055:
            r5 = r2
        L_0x0056:
            if (r5 == 0) goto L_0x005b
        L_0x0058:
            r5.recycle()
        L_0x005b:
            if (r3 != r4) goto L_0x0095
            androidx.appcompat.widget.AppCompatSpinner$b r3 = new androidx.appcompat.widget.AppCompatSpinner$b
            android.content.Context r5 = r7.f121c
            r3.<init>(r5, r9, r10)
            android.content.Context r5 = r7.f121c
            int[] r6 = c.a.j.Spinner
            c.a.f.ka r1 = c.a.f.ka.a(r5, r9, r6, r10, r1)
            int r5 = c.a.j.Spinner_android_dropDownWidth
            r6 = -2
            int r5 = r1.e(r5, r6)
            r7.h = r5
            int r5 = c.a.j.Spinner_android_popupBackground
            android.graphics.drawable.Drawable r5 = r1.b(r5)
            r3.a(r5)
            int r5 = c.a.j.Spinner_android_prompt
            java.lang.String r5 = r0.d(r5)
            r3.a(r5)
            android.content.res.TypedArray r1 = r1.f605b
            r1.recycle()
            r7.g = r3
            c.a.f.v r1 = new c.a.f.v
            r1.<init>(r7, r7, r3)
            r7.f122d = r1
        L_0x0095:
            int r1 = c.a.j.Spinner_android_entries
            android.content.res.TypedArray r3 = r0.f605b
            java.lang.CharSequence[] r1 = r3.getTextArray(r1)
            if (r1 == 0) goto L_0x00af
            android.widget.ArrayAdapter r3 = new android.widget.ArrayAdapter
            r5 = 17367048(0x1090008, float:2.5162948E-38)
            r3.<init>(r8, r5, r1)
            int r8 = c.a.g.support_simple_spinner_dropdown_item
            r3.setDropDownViewResource(r8)
            r7.setAdapter(r3)
        L_0x00af:
            android.content.res.TypedArray r8 = r0.f605b
            r8.recycle()
            r7.f = r4
            android.widget.SpinnerAdapter r8 = r7.f123e
            if (r8 == 0) goto L_0x00bf
            r7.setAdapter(r8)
            r7.f123e = r2
        L_0x00bf:
            c.a.f.o r8 = r7.f120b
            r8.a(r9, r10)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.widget.AppCompatSpinner.<init>(android.content.Context, android.util.AttributeSet, int):void");
    }

    @Override // android.widget.Spinner, android.widget.AbsSpinner
    public void setAdapter(SpinnerAdapter spinnerAdapter) {
        if (!this.f) {
            this.f123e = spinnerAdapter;
            return;
        }
        super.setAdapter(spinnerAdapter);
        if (this.g != null) {
            Context context = this.f121c;
            if (context == null) {
                context = getContext();
            }
            b bVar = this.g;
            a aVar = new a(spinnerAdapter, context.getTheme());
            DataSetObserver dataSetObserver = bVar.u;
            if (dataSetObserver == null) {
                bVar.u = new ListPopupWindow.b();
            } else {
                ListAdapter listAdapter = bVar.f150e;
                if (listAdapter != null) {
                    listAdapter.unregisterDataSetObserver(dataSetObserver);
                }
            }
            bVar.f150e = aVar;
            aVar.registerDataSetObserver(bVar.u);
            F f = bVar.f;
            if (f != null) {
                f.setAdapter(bVar.f150e);
            }
            bVar.J = aVar;
        }
    }

    /* loaded from: classes.dex */
    public class b extends ListPopupWindow {
        public CharSequence I;
        public ListAdapter J;
        public final Rect K = new Rect();

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public b(Context context, AttributeSet attributeSet, int i) {
            super(context, attributeSet, i, 0);
            AppCompatSpinner.this = r2;
            a(r2);
            a(true);
            f(0);
            a(new C0054w(this, r2));
        }

        @Override // androidx.appcompat.widget.ListPopupWindow
        public void a(ListAdapter listAdapter) {
            DataSetObserver dataSetObserver = this.u;
            if (dataSetObserver == null) {
                this.u = new ListPopupWindow.b();
            } else {
                ListAdapter listAdapter2 = this.f150e;
                if (listAdapter2 != null) {
                    listAdapter2.unregisterDataSetObserver(dataSetObserver);
                }
            }
            this.f150e = listAdapter;
            if (listAdapter != null) {
                listAdapter.registerDataSetObserver(this.u);
            }
            F f = this.f;
            if (f != null) {
                f.setAdapter(this.f150e);
            }
            this.J = listAdapter;
        }

        public boolean b(View view) {
            return n.o(view) && view.getGlobalVisibleRect(this.K);
        }

        @Override // androidx.appcompat.widget.ListPopupWindow, c.a.e.a.y
        public void c() {
            ViewTreeObserver viewTreeObserver;
            boolean b2 = b();
            l();
            e(2);
            super.c();
            d().setChoiceMode(1);
            g(AppCompatSpinner.this.getSelectedItemPosition());
            if (!b2 && (viewTreeObserver = AppCompatSpinner.this.getViewTreeObserver()) != null) {
                ViewTreeObserver$OnGlobalLayoutListenerC0055x xVar = new ViewTreeObserver$OnGlobalLayoutListenerC0055x(this);
                viewTreeObserver.addOnGlobalLayoutListener(xVar);
                a(new C0056y(this, xVar));
            }
        }

        public void l() {
            Drawable f = f();
            int i = 0;
            if (f != null) {
                f.getPadding(AppCompatSpinner.this.i);
                i = xa.a(AppCompatSpinner.this) ? AppCompatSpinner.this.i.right : -AppCompatSpinner.this.i.left;
            } else {
                Rect rect = AppCompatSpinner.this.i;
                rect.right = 0;
                rect.left = 0;
            }
            int paddingLeft = AppCompatSpinner.this.getPaddingLeft();
            int paddingRight = AppCompatSpinner.this.getPaddingRight();
            int width = AppCompatSpinner.this.getWidth();
            AppCompatSpinner appCompatSpinner = AppCompatSpinner.this;
            int i2 = appCompatSpinner.h;
            if (i2 == -2) {
                int a2 = appCompatSpinner.a((SpinnerAdapter) this.J, f());
                int i3 = AppCompatSpinner.this.getContext().getResources().getDisplayMetrics().widthPixels;
                Rect rect2 = AppCompatSpinner.this.i;
                int i4 = (i3 - rect2.left) - rect2.right;
                if (a2 > i4) {
                    a2 = i4;
                }
                b(Math.max(a2, (width - paddingLeft) - paddingRight));
            } else if (i2 == -1) {
                b((width - paddingLeft) - paddingRight);
            } else {
                b(i2);
            }
            d(xa.a(AppCompatSpinner.this) ? ((width - paddingRight) - i()) + i : i + paddingLeft);
        }

        public void a(CharSequence charSequence) {
            this.I = charSequence;
        }
    }
}
