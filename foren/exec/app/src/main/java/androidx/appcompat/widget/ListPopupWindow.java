package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.database.DataSetObserver;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.PopupWindow;
import c.a.e.a.y;
import c.a.f.C0050s;
import c.a.f.F;
import c.a.j;
import c.e.h.n;
import java.lang.reflect.Method;

/* loaded from: classes.dex */
public class ListPopupWindow implements y {

    /* renamed from: a */
    public static Method f146a;

    /* renamed from: b */
    public static Method f147b;

    /* renamed from: c */
    public static Method f148c;
    public final d A;
    public final c B;
    public final a C;
    public final Handler D;
    public final Rect E;
    public Rect F;
    public boolean G;
    public PopupWindow H;

    /* renamed from: d */
    public Context f149d;

    /* renamed from: e */
    public ListAdapter f150e;
    public F f;
    public int g;
    public int h;
    public int i;
    public int j;
    public int k;
    public boolean l;
    public boolean m;
    public boolean n;
    public int o;
    public boolean p;
    public boolean q;
    public int r;
    public View s;
    public int t;
    public DataSetObserver u;
    public View v;
    public Drawable w;
    public AdapterView.OnItemClickListener x;
    public AdapterView.OnItemSelectedListener y;
    public final e z;

    /* loaded from: classes.dex */
    public class a implements Runnable {
        public a() {
            ListPopupWindow.this = r1;
        }

        @Override // java.lang.Runnable
        public void run() {
            ListPopupWindow.this.a();
        }
    }

    /* loaded from: classes.dex */
    public class b extends DataSetObserver {
        public b() {
            ListPopupWindow.this = r1;
        }

        @Override // android.database.DataSetObserver
        public void onChanged() {
            if (ListPopupWindow.this.b()) {
                ListPopupWindow.this.c();
            }
        }

        @Override // android.database.DataSetObserver
        public void onInvalidated() {
            ListPopupWindow.this.dismiss();
        }
    }

    /* loaded from: classes.dex */
    public class c implements AbsListView.OnScrollListener {
        public c() {
            ListPopupWindow.this = r1;
        }

        @Override // android.widget.AbsListView.OnScrollListener
        public void onScroll(AbsListView absListView, int i, int i2, int i3) {
        }

        @Override // android.widget.AbsListView.OnScrollListener
        public void onScrollStateChanged(AbsListView absListView, int i) {
            if (i == 1 && !ListPopupWindow.this.j() && ListPopupWindow.this.H.getContentView() != null) {
                ListPopupWindow listPopupWindow = ListPopupWindow.this;
                listPopupWindow.D.removeCallbacks(listPopupWindow.z);
                e eVar = ListPopupWindow.this.z;
                F f = ListPopupWindow.this.f;
                if (f != null && n.o(f) && ListPopupWindow.this.f.getCount() > ListPopupWindow.this.f.getChildCount()) {
                    int childCount = ListPopupWindow.this.f.getChildCount();
                    ListPopupWindow listPopupWindow2 = ListPopupWindow.this;
                    if (childCount <= listPopupWindow2.r) {
                        listPopupWindow2.H.setInputMethodMode(2);
                        ListPopupWindow.this.c();
                    }
                }
            }
        }
    }

    /* loaded from: classes.dex */
    public class d implements View.OnTouchListener {
        public d() {
            ListPopupWindow.this = r1;
        }

        @Override // android.view.View.OnTouchListener
        public boolean onTouch(View view, MotionEvent motionEvent) {
            PopupWindow popupWindow;
            int action = motionEvent.getAction();
            int x = (int) motionEvent.getX();
            int y = (int) motionEvent.getY();
            if (action == 0 && (popupWindow = ListPopupWindow.this.H) != null && popupWindow.isShowing() && x >= 0 && x < ListPopupWindow.this.H.getWidth() && y >= 0 && y < ListPopupWindow.this.H.getHeight()) {
                ListPopupWindow listPopupWindow = ListPopupWindow.this;
                listPopupWindow.D.postDelayed(listPopupWindow.z, 250L);
                return false;
            } else if (action != 1) {
                return false;
            } else {
                ListPopupWindow listPopupWindow2 = ListPopupWindow.this;
                listPopupWindow2.D.removeCallbacks(listPopupWindow2.z);
                return false;
            }
        }
    }

    /* loaded from: classes.dex */
    public class e implements Runnable {
        public e() {
            ListPopupWindow.this = r1;
        }

        @Override // java.lang.Runnable
        public void run() {
            F f = ListPopupWindow.this.f;
            if (f != null && n.o(f) && ListPopupWindow.this.f.getCount() > ListPopupWindow.this.f.getChildCount()) {
                int childCount = ListPopupWindow.this.f.getChildCount();
                ListPopupWindow listPopupWindow = ListPopupWindow.this;
                if (childCount <= listPopupWindow.r) {
                    listPopupWindow.H.setInputMethodMode(2);
                    ListPopupWindow.this.c();
                }
            }
        }
    }

    static {
        try {
            f146a = PopupWindow.class.getDeclaredMethod("setClipToScreenEnabled", Boolean.TYPE);
        } catch (NoSuchMethodException unused) {
        }
        try {
            f147b = PopupWindow.class.getDeclaredMethod("getMaxAvailableHeight", View.class, Integer.TYPE, Boolean.TYPE);
        } catch (NoSuchMethodException unused2) {
        }
        try {
            f148c = PopupWindow.class.getDeclaredMethod("setEpicenterBounds", Rect.class);
        } catch (NoSuchMethodException unused3) {
        }
    }

    public ListPopupWindow(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, c.a.a.listPopupWindowStyle, 0);
    }

    public void a(ListAdapter listAdapter) {
        DataSetObserver dataSetObserver = this.u;
        if (dataSetObserver == null) {
            this.u = new b();
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
    }

    public void b(int i) {
        Drawable background = this.H.getBackground();
        if (background != null) {
            background.getPadding(this.E);
            Rect rect = this.E;
            this.h = rect.left + rect.right + i;
            return;
        }
        i(i);
    }

    public void c(int i) {
        this.o = i;
    }

    public void d(int i) {
        this.i = i;
    }

    @Override // c.a.e.a.y
    public void dismiss() {
        this.H.dismiss();
        View view = this.s;
        if (view != null) {
            ViewParent parent = view.getParent();
            if (parent instanceof ViewGroup) {
                ((ViewGroup) parent).removeView(this.s);
            }
        }
        this.H.setContentView(null);
        this.f = null;
        this.D.removeCallbacks(this.z);
    }

    public View e() {
        return this.v;
    }

    public void f(int i) {
        this.t = i;
    }

    public int g() {
        return this.i;
    }

    public int h() {
        if (!this.l) {
            return 0;
        }
        return this.j;
    }

    public int i() {
        return this.h;
    }

    public boolean j() {
        return this.H.getInputMethodMode() == 2;
    }

    public boolean k() {
        return this.G;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:61:0x01ab  */
    /* JADX WARN: Removed duplicated region for block: B:99:0x0222  */
    @Override // c.a.e.a.y
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void c() {
        /*
            Method dump skipped, instructions count: 701
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.widget.ListPopupWindow.c():void");
    }

    @Override // c.a.e.a.y
    public ListView d() {
        return this.f;
    }

    public void e(int i) {
        this.H.setInputMethodMode(i);
    }

    public Drawable f() {
        return this.H.getBackground();
    }

    public void g(int i) {
        F f = this.f;
        if (b() && f != null) {
            f.setListSelectionHidden(false);
            f.setSelection(i);
            if (f.getChoiceMode() != 0) {
                f.setItemChecked(i, true);
            }
        }
    }

    public void i(int i) {
        this.h = i;
    }

    public ListPopupWindow(Context context, AttributeSet attributeSet, int i) {
        this(context, attributeSet, i, 0);
    }

    public void h(int i) {
        this.j = i;
        this.l = true;
    }

    public ListPopupWindow(Context context, AttributeSet attributeSet, int i, int i2) {
        this.g = -2;
        this.h = -2;
        this.k = 1002;
        this.o = 0;
        this.p = false;
        this.q = false;
        this.r = Integer.MAX_VALUE;
        this.t = 0;
        this.z = new e();
        this.A = new d();
        this.B = new c();
        this.C = new a();
        this.E = new Rect();
        this.f149d = context;
        this.D = new Handler(context.getMainLooper());
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, j.ListPopupWindow, i, i2);
        this.i = obtainStyledAttributes.getDimensionPixelOffset(j.ListPopupWindow_android_dropDownHorizontalOffset, 0);
        this.j = obtainStyledAttributes.getDimensionPixelOffset(j.ListPopupWindow_android_dropDownVerticalOffset, 0);
        if (this.j != 0) {
            this.l = true;
        }
        obtainStyledAttributes.recycle();
        this.H = new C0050s(context, attributeSet, i, i2);
        this.H.setInputMethodMode(1);
    }

    @Override // c.a.e.a.y
    public boolean b() {
        return this.H.isShowing();
    }

    public void b(boolean z) {
        this.n = true;
        this.m = z;
    }

    public void a(boolean z) {
        this.G = z;
        this.H.setFocusable(z);
    }

    public void a(Drawable drawable) {
        this.H.setBackgroundDrawable(drawable);
    }

    public void a(int i) {
        this.H.setAnimationStyle(i);
    }

    public void a(View view) {
        this.v = view;
    }

    public void a(Rect rect) {
        this.F = rect;
    }

    public void a(AdapterView.OnItemClickListener onItemClickListener) {
        this.x = onItemClickListener;
    }

    public void a(PopupWindow.OnDismissListener onDismissListener) {
        this.H.setOnDismissListener(onDismissListener);
    }

    public void a() {
        F f = this.f;
        if (f != null) {
            f.setListSelectionHidden(true);
            f.requestLayout();
        }
    }

    public F a(Context context, boolean z) {
        return new F(context, z);
    }
}
