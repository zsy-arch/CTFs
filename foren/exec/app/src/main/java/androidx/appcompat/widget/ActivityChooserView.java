package androidx.appcompat.widget;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.database.DataSetObserver;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import c.a.d;
import c.a.f;
import c.a.f.C0041i;
import c.a.f.C0042j;
import c.a.f.C0044l;
import c.a.f.C0045m;
import c.a.f.C0046n;
import c.a.f.ViewTreeObserver$OnGlobalLayoutListenerC0043k;
import c.a.f.ka;
import c.a.g;
import c.a.h;
import c.a.j;

/* loaded from: classes.dex */
public class ActivityChooserView extends ViewGroup implements C0041i.a {

    /* renamed from: a */
    public final a f85a;

    /* renamed from: b */
    public final b f86b;

    /* renamed from: c */
    public final View f87c;

    /* renamed from: d */
    public final Drawable f88d;

    /* renamed from: e */
    public final FrameLayout f89e;
    public final ImageView f;
    public final FrameLayout g;
    public final ImageView h;
    public final int i;
    public c.e.h.b j;
    public final DataSetObserver k;
    public final ViewTreeObserver.OnGlobalLayoutListener l;
    public ListPopupWindow m;
    public PopupWindow.OnDismissListener n;
    public boolean o;
    public int p;
    public boolean q;
    public int r;

    /* loaded from: classes.dex */
    public static class InnerLayout extends LinearLayout {

        /* renamed from: a */
        public static final int[] f90a = {16842964};

        public InnerLayout(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            ka a2 = ka.a(context, attributeSet, f90a);
            setBackgroundDrawable(a2.b(0));
            a2.f605b.recycle();
        }
    }

    /* loaded from: classes.dex */
    public class a extends BaseAdapter {

        /* renamed from: a */
        public C0041i f91a;

        /* renamed from: b */
        public int f92b = 4;

        /* renamed from: c */
        public boolean f93c;

        /* renamed from: d */
        public boolean f94d;

        /* renamed from: e */
        public boolean f95e;

        public a() {
            ActivityChooserView.this = r1;
        }

        @Override // android.widget.Adapter
        public int getCount() {
            int a2 = this.f91a.a();
            if (!this.f93c && this.f91a.b() != null) {
                a2--;
            }
            int min = Math.min(a2, this.f92b);
            return this.f95e ? min + 1 : min;
        }

        @Override // android.widget.Adapter
        public Object getItem(int i) {
            int itemViewType = getItemViewType(i);
            if (itemViewType == 0) {
                if (!this.f93c && this.f91a.b() != null) {
                    i++;
                }
                return this.f91a.b(i);
            } else if (itemViewType == 1) {
                return null;
            } else {
                throw new IllegalArgumentException();
            }
        }

        @Override // android.widget.Adapter
        public long getItemId(int i) {
            return i;
        }

        @Override // android.widget.BaseAdapter, android.widget.Adapter
        public int getItemViewType(int i) {
            return (!this.f95e || i != getCount() - 1) ? 0 : 1;
        }

        @Override // android.widget.Adapter
        public View getView(int i, View view, ViewGroup viewGroup) {
            ResolveInfo resolveInfo;
            int itemViewType = getItemViewType(i);
            if (itemViewType == 0) {
                if (view == null || view.getId() != f.list_item) {
                    view = LayoutInflater.from(ActivityChooserView.this.getContext()).inflate(g.abc_activity_chooser_view_list_item, viewGroup, false);
                }
                PackageManager packageManager = ActivityChooserView.this.getContext().getPackageManager();
                ImageView imageView = (ImageView) view.findViewById(f.icon);
                int itemViewType2 = getItemViewType(i);
                if (itemViewType2 == 0) {
                    resolveInfo = this.f91a.b((this.f93c || this.f91a.b() == null) ? i : i + 1);
                } else if (itemViewType2 == 1) {
                    resolveInfo = null;
                } else {
                    throw new IllegalArgumentException();
                }
                imageView.setImageDrawable(resolveInfo.loadIcon(packageManager));
                ((TextView) view.findViewById(f.title)).setText(resolveInfo.loadLabel(packageManager));
                if (!this.f93c || i != 0 || !this.f94d) {
                    view.setActivated(false);
                } else {
                    view.setActivated(true);
                }
                return view;
            } else if (itemViewType != 1) {
                throw new IllegalArgumentException();
            } else if (view != null && view.getId() == 1) {
                return view;
            } else {
                View inflate = LayoutInflater.from(ActivityChooserView.this.getContext()).inflate(g.abc_activity_chooser_view_list_item, viewGroup, false);
                inflate.setId(1);
                ((TextView) inflate.findViewById(f.title)).setText(ActivityChooserView.this.getContext().getString(h.abc_activity_chooser_view_see_all));
                return inflate;
            }
        }

        @Override // android.widget.BaseAdapter, android.widget.Adapter
        public int getViewTypeCount() {
            return 3;
        }
    }

    /* loaded from: classes.dex */
    public class b implements AdapterView.OnItemClickListener, View.OnClickListener, View.OnLongClickListener, PopupWindow.OnDismissListener {
        public b() {
            ActivityChooserView.this = r1;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            ActivityChooserView activityChooserView = ActivityChooserView.this;
            if (view == activityChooserView.g) {
                activityChooserView.a();
                Intent a2 = ActivityChooserView.this.f85a.f91a.a(ActivityChooserView.this.f85a.f91a.a(ActivityChooserView.this.f85a.f91a.b()));
                if (a2 != null) {
                    a2.addFlags(524288);
                    ActivityChooserView.this.getContext().startActivity(a2);
                }
            } else if (view == activityChooserView.f89e) {
                activityChooserView.o = false;
                activityChooserView.a(activityChooserView.p);
            } else {
                throw new IllegalArgumentException();
            }
        }

        @Override // android.widget.PopupWindow.OnDismissListener
        public void onDismiss() {
            PopupWindow.OnDismissListener onDismissListener = ActivityChooserView.this.n;
            if (onDismissListener != null) {
                onDismissListener.onDismiss();
            }
            c.e.h.b bVar = ActivityChooserView.this.j;
            if (bVar != null) {
                bVar.a(false);
            }
        }

        @Override // android.widget.AdapterView.OnItemClickListener
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
            int itemViewType = ((a) adapterView.getAdapter()).getItemViewType(i);
            if (itemViewType == 0) {
                ActivityChooserView.this.a();
                ActivityChooserView activityChooserView = ActivityChooserView.this;
                if (!activityChooserView.o) {
                    if (!activityChooserView.f85a.f93c) {
                        i++;
                    }
                    Intent a2 = ActivityChooserView.this.f85a.f91a.a(i);
                    if (a2 != null) {
                        a2.addFlags(524288);
                        ActivityChooserView.this.getContext().startActivity(a2);
                    }
                } else if (i > 0) {
                    activityChooserView.f85a.f91a.c(i);
                }
            } else if (itemViewType == 1) {
                ActivityChooserView.this.a(Integer.MAX_VALUE);
            } else {
                throw new IllegalArgumentException();
            }
        }

        @Override // android.view.View.OnLongClickListener
        public boolean onLongClick(View view) {
            ActivityChooserView activityChooserView = ActivityChooserView.this;
            if (view == activityChooserView.g) {
                if (activityChooserView.f85a.getCount() > 0) {
                    ActivityChooserView activityChooserView2 = ActivityChooserView.this;
                    activityChooserView2.o = true;
                    activityChooserView2.a(activityChooserView2.p);
                }
                return true;
            }
            throw new IllegalArgumentException();
        }
    }

    public ActivityChooserView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0069  */
    /* JADX WARN: Removed duplicated region for block: B:88:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void a(int r17) {
        /*
            Method dump skipped, instructions count: 432
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.widget.ActivityChooserView.a(int):void");
    }

    public boolean b() {
        return getListPopupWindow().b();
    }

    public boolean c() {
        if (b() || !this.q) {
            return false;
        }
        this.o = false;
        a(this.p);
        return true;
    }

    public void d() {
        if (this.f85a.getCount() > 0) {
            this.f89e.setEnabled(true);
        } else {
            this.f89e.setEnabled(false);
        }
        int a2 = this.f85a.f91a.a();
        int c2 = this.f85a.f91a.c();
        if (a2 == 1 || (a2 > 1 && c2 > 0)) {
            this.g.setVisibility(0);
            ResolveInfo b2 = this.f85a.f91a.b();
            PackageManager packageManager = getContext().getPackageManager();
            this.h.setImageDrawable(b2.loadIcon(packageManager));
            if (this.r != 0) {
                this.g.setContentDescription(getContext().getString(this.r, b2.loadLabel(packageManager)));
            }
        } else {
            this.g.setVisibility(8);
        }
        if (this.g.getVisibility() == 0) {
            this.f87c.setBackgroundDrawable(this.f88d);
        } else {
            this.f87c.setBackgroundDrawable(null);
        }
    }

    public C0041i getDataModel() {
        return this.f85a.f91a;
    }

    public ListPopupWindow getListPopupWindow() {
        if (this.m == null) {
            this.m = new ListPopupWindow(getContext(), null, c.a.a.listPopupWindowStyle, 0);
            this.m.a(this.f85a);
            this.m.a(this);
            this.m.a(true);
            this.m.a((AdapterView.OnItemClickListener) this.f86b);
            this.m.a((PopupWindow.OnDismissListener) this.f86b);
        }
        return this.m;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        C0041i iVar = this.f85a.f91a;
        if (iVar != null) {
            iVar.registerObserver(this.k);
        }
        this.q = true;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        C0041i iVar = this.f85a.f91a;
        if (iVar != null) {
            iVar.unregisterObserver(this.k);
        }
        ViewTreeObserver viewTreeObserver = getViewTreeObserver();
        if (viewTreeObserver.isAlive()) {
            viewTreeObserver.removeGlobalOnLayoutListener(this.l);
        }
        if (b()) {
            a();
        }
        this.q = false;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        this.f87c.layout(0, 0, i3 - i, i4 - i2);
        if (!b()) {
            a();
        }
    }

    @Override // android.view.View
    public void onMeasure(int i, int i2) {
        View view = this.f87c;
        if (this.g.getVisibility() != 0) {
            i2 = View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.getSize(i2), 1073741824);
        }
        measureChild(view, i, i2);
        setMeasuredDimension(view.getMeasuredWidth(), view.getMeasuredHeight());
    }

    public void setActivityChooserModel(C0041i iVar) {
        a aVar = this.f85a;
        ActivityChooserView activityChooserView = ActivityChooserView.this;
        C0041i iVar2 = activityChooserView.f85a.f91a;
        if (iVar2 != null && activityChooserView.isShown()) {
            iVar2.unregisterObserver(ActivityChooserView.this.k);
        }
        aVar.f91a = iVar;
        if (iVar != null && ActivityChooserView.this.isShown()) {
            iVar.registerObserver(ActivityChooserView.this.k);
        }
        aVar.notifyDataSetChanged();
        if (b()) {
            a();
            c();
        }
    }

    public void setDefaultActionButtonContentDescription(int i) {
        this.r = i;
    }

    public void setExpandActivityOverflowButtonContentDescription(int i) {
        this.f.setContentDescription(getContext().getString(i));
    }

    public void setExpandActivityOverflowButtonDrawable(Drawable drawable) {
        this.f.setImageDrawable(drawable);
    }

    public void setInitialActivityCount(int i) {
        this.p = i;
    }

    public void setOnDismissListener(PopupWindow.OnDismissListener onDismissListener) {
        this.n = onDismissListener;
    }

    public void setProvider(c.e.h.b bVar) {
        this.j = bVar;
    }

    public ActivityChooserView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.k = new C0042j(this);
        this.l = new ViewTreeObserver$OnGlobalLayoutListenerC0043k(this);
        this.p = 4;
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, j.ActivityChooserView, i, 0);
        this.p = obtainStyledAttributes.getInt(j.ActivityChooserView_initialActivityCount, 4);
        Drawable drawable = obtainStyledAttributes.getDrawable(j.ActivityChooserView_expandActivityOverflowButtonDrawable);
        obtainStyledAttributes.recycle();
        LayoutInflater.from(getContext()).inflate(g.abc_activity_chooser_view, (ViewGroup) this, true);
        this.f86b = new b();
        this.f87c = findViewById(f.activity_chooser_view_content);
        this.f88d = this.f87c.getBackground();
        this.g = (FrameLayout) findViewById(f.default_activity_button);
        this.g.setOnClickListener(this.f86b);
        this.g.setOnLongClickListener(this.f86b);
        this.h = (ImageView) this.g.findViewById(f.image);
        FrameLayout frameLayout = (FrameLayout) findViewById(f.expand_activities_button);
        frameLayout.setOnClickListener(this.f86b);
        frameLayout.setAccessibilityDelegate(new C0044l(this));
        frameLayout.setOnTouchListener(new C0045m(this, frameLayout));
        this.f89e = frameLayout;
        this.f = (ImageView) frameLayout.findViewById(f.image);
        this.f.setImageDrawable(drawable);
        this.f85a = new a();
        this.f85a.registerDataSetObserver(new C0046n(this));
        Resources resources = context.getResources();
        this.i = Math.max(resources.getDisplayMetrics().widthPixels / 2, resources.getDimensionPixelSize(d.abc_config_prefDialogWidth));
    }

    public boolean a() {
        if (!b()) {
            return true;
        }
        getListPopupWindow().dismiss();
        ViewTreeObserver viewTreeObserver = getViewTreeObserver();
        if (!viewTreeObserver.isAlive()) {
            return true;
        }
        viewTreeObserver.removeGlobalOnLayoutListener(this.l);
        return true;
    }
}
