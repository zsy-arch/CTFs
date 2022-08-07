package c.a.f;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.animation.DecelerateInterpolator;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatSpinner;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.LinearLayoutCompat;
import c.a.a.AbstractC0020a;
import c.a.a.C;
import c.a.d;
import c.a.j;

/* loaded from: classes.dex */
public class P extends HorizontalScrollView implements AdapterView.OnItemSelectedListener {

    /* renamed from: a */
    public Runnable f551a;

    /* renamed from: b */
    public b f552b;

    /* renamed from: c */
    public LinearLayoutCompat f553c;

    /* renamed from: d */
    public Spinner f554d;

    /* renamed from: e */
    public boolean f555e;
    public int f;
    public int g;
    public int h;
    public int i;

    /* loaded from: classes.dex */
    private class a extends BaseAdapter {
        public a() {
            P.this = r1;
        }

        @Override // android.widget.Adapter
        public int getCount() {
            return P.this.f553c.getChildCount();
        }

        @Override // android.widget.Adapter
        public Object getItem(int i) {
            return ((c) P.this.f553c.getChildAt(i)).f559b;
        }

        @Override // android.widget.Adapter
        public long getItemId(int i) {
            return i;
        }

        @Override // android.widget.Adapter
        public View getView(int i, View view, ViewGroup viewGroup) {
            if (view == null) {
                P p = P.this;
                return p.a(((c) p.f553c.getChildAt(i)).f559b, true);
            }
            c cVar = (c) view;
            cVar.f559b = ((c) P.this.f553c.getChildAt(i)).f559b;
            cVar.a();
            return view;
        }
    }

    /* loaded from: classes.dex */
    public class b implements View.OnClickListener {
        public b() {
            P.this = r1;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            ((c) view).f559b.e();
            int childCount = P.this.f553c.getChildCount();
            for (int i = 0; i < childCount; i++) {
                View childAt = P.this.f553c.getChildAt(i);
                childAt.setSelected(childAt == view);
            }
        }
    }

    /* loaded from: classes.dex */
    public class c extends LinearLayout {

        /* renamed from: a */
        public final int[] f558a = {16842964};

        /* renamed from: b */
        public AbstractC0020a.c f559b;

        /* renamed from: c */
        public TextView f560c;

        /* renamed from: d */
        public ImageView f561d;

        /* renamed from: e */
        public View f562e;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public c(Context context, AbstractC0020a.c cVar, boolean z) {
            super(context, null, c.a.a.actionBarTabStyle);
            P.this = r4;
            this.f559b = cVar;
            ka a2 = ka.a(context, null, this.f558a, c.a.a.actionBarTabStyle, 0);
            if (a2.f(0)) {
                setBackgroundDrawable(a2.b(0));
            }
            a2.f605b.recycle();
            if (z) {
                setGravity(8388627);
            }
            a();
        }

        public void a() {
            AbstractC0020a.c cVar = this.f559b;
            View b2 = cVar.b();
            CharSequence charSequence = null;
            if (b2 != null) {
                ViewParent parent = b2.getParent();
                if (parent != this) {
                    if (parent != null) {
                        ((ViewGroup) parent).removeView(b2);
                    }
                    addView(b2);
                }
                this.f562e = b2;
                TextView textView = this.f560c;
                if (textView != null) {
                    textView.setVisibility(8);
                }
                ImageView imageView = this.f561d;
                if (imageView != null) {
                    imageView.setVisibility(8);
                    this.f561d.setImageDrawable(null);
                    return;
                }
                return;
            }
            View view = this.f562e;
            if (view != null) {
                removeView(view);
                this.f562e = null;
            }
            Drawable c2 = cVar.c();
            CharSequence d2 = cVar.d();
            if (c2 != null) {
                if (this.f561d == null) {
                    AppCompatImageView appCompatImageView = new AppCompatImageView(getContext(), null, 0);
                    LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -2);
                    layoutParams.gravity = 16;
                    appCompatImageView.setLayoutParams(layoutParams);
                    addView(appCompatImageView, 0);
                    this.f561d = appCompatImageView;
                }
                this.f561d.setImageDrawable(c2);
                this.f561d.setVisibility(0);
            } else {
                ImageView imageView2 = this.f561d;
                if (imageView2 != null) {
                    imageView2.setVisibility(8);
                    this.f561d.setImageDrawable(null);
                }
            }
            boolean z = !TextUtils.isEmpty(d2);
            if (z) {
                if (this.f560c == null) {
                    AppCompatTextView appCompatTextView = new AppCompatTextView(getContext(), null, c.a.a.actionBarTabTextStyle);
                    appCompatTextView.setEllipsize(TextUtils.TruncateAt.END);
                    LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(-2, -2);
                    layoutParams2.gravity = 16;
                    appCompatTextView.setLayoutParams(layoutParams2);
                    addView(appCompatTextView);
                    this.f560c = appCompatTextView;
                }
                this.f560c.setText(d2);
                this.f560c.setVisibility(0);
            } else {
                TextView textView2 = this.f560c;
                if (textView2 != null) {
                    textView2.setVisibility(8);
                    this.f560c.setText((CharSequence) null);
                }
            }
            ImageView imageView3 = this.f561d;
            if (imageView3 != null) {
                imageView3.setContentDescription(cVar.a());
            }
            if (!z) {
                charSequence = cVar.a();
            }
            C.a((View) this, charSequence);
        }

        @Override // android.view.View
        public void onInitializeAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
            super.onInitializeAccessibilityEvent(accessibilityEvent);
            accessibilityEvent.setClassName(AbstractC0020a.c.class.getName());
        }

        @Override // android.view.View
        public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
            super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
            accessibilityNodeInfo.setClassName(AbstractC0020a.c.class.getName());
        }

        @Override // android.widget.LinearLayout, android.view.View
        public void onMeasure(int i, int i2) {
            int i3;
            super.onMeasure(i, i2);
            if (P.this.f > 0 && getMeasuredWidth() > (i3 = P.this.f)) {
                super.onMeasure(View.MeasureSpec.makeMeasureSpec(i3, 1073741824), i2);
            }
        }

        @Override // android.view.View
        public void setSelected(boolean z) {
            boolean z2 = isSelected() != z;
            super.setSelected(z);
            if (z2 && z) {
                sendAccessibilityEvent(4);
            }
        }
    }

    static {
        new DecelerateInterpolator();
    }

    public final boolean a() {
        Spinner spinner = this.f554d;
        return spinner != null && spinner.getParent() == this;
    }

    public final boolean b() {
        Spinner spinner = this.f554d;
        if (!(spinner != null && spinner.getParent() == this)) {
            return false;
        }
        removeView(this.f554d);
        addView(this.f553c, new ViewGroup.LayoutParams(-2, -1));
        setTabSelected(this.f554d.getSelectedItemPosition());
        return false;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        Runnable runnable = this.f551a;
        if (runnable != null) {
            post(runnable);
        }
    }

    @Override // android.view.View
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        Context context = getContext();
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(null, j.ActionBar, c.a.a.actionBarStyle, 0);
        int layoutDimension = obtainStyledAttributes.getLayoutDimension(j.ActionBar_height, 0);
        Resources resources = context.getResources();
        if (!context.getResources().getBoolean(c.a.b.abc_action_bar_embed_tabs)) {
            layoutDimension = Math.min(layoutDimension, resources.getDimensionPixelSize(d.abc_action_bar_stacked_max_height));
        }
        obtainStyledAttributes.recycle();
        setContentHeight(layoutDimension);
        this.g = context.getResources().getDimensionPixelSize(d.abc_action_bar_stacked_tab_max_width);
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        Runnable runnable = this.f551a;
        if (runnable != null) {
            removeCallbacks(runnable);
        }
    }

    @Override // android.widget.AdapterView.OnItemSelectedListener
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long j) {
        ((c) view).f559b.e();
    }

    @Override // android.widget.HorizontalScrollView, android.widget.FrameLayout, android.view.View
    public void onMeasure(int i, int i2) {
        int mode = View.MeasureSpec.getMode(i);
        boolean z = true;
        boolean z2 = mode == 1073741824;
        setFillViewport(z2);
        int childCount = this.f553c.getChildCount();
        if (childCount <= 1 || !(mode == 1073741824 || mode == Integer.MIN_VALUE)) {
            this.f = -1;
        } else {
            if (childCount > 2) {
                this.f = (int) (View.MeasureSpec.getSize(i) * 0.4f);
            } else {
                this.f = View.MeasureSpec.getSize(i) / 2;
            }
            this.f = Math.min(this.f, this.g);
        }
        int makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(this.h, 1073741824);
        if (z2 || !this.f555e) {
            z = false;
        }
        if (z) {
            this.f553c.measure(0, makeMeasureSpec);
            if (this.f553c.getMeasuredWidth() <= View.MeasureSpec.getSize(i)) {
                b();
            } else if (!a()) {
                if (this.f554d == null) {
                    AppCompatSpinner appCompatSpinner = new AppCompatSpinner(getContext(), null, c.a.a.actionDropDownStyle);
                    appCompatSpinner.setLayoutParams(new LinearLayoutCompat.a(-2, -1));
                    appCompatSpinner.setOnItemSelectedListener(this);
                    this.f554d = appCompatSpinner;
                }
                removeView(this.f553c);
                addView(this.f554d, new ViewGroup.LayoutParams(-2, -1));
                if (this.f554d.getAdapter() == null) {
                    this.f554d.setAdapter((SpinnerAdapter) new a());
                }
                Runnable runnable = this.f551a;
                if (runnable != null) {
                    removeCallbacks(runnable);
                    this.f551a = null;
                }
                this.f554d.setSelection(this.i);
            }
        } else {
            b();
        }
        int measuredWidth = getMeasuredWidth();
        super.onMeasure(i, makeMeasureSpec);
        int measuredWidth2 = getMeasuredWidth();
        if (z2 && measuredWidth != measuredWidth2) {
            setTabSelected(this.i);
        }
    }

    @Override // android.widget.AdapterView.OnItemSelectedListener
    public void onNothingSelected(AdapterView<?> adapterView) {
    }

    public void setAllowCollapse(boolean z) {
        this.f555e = z;
    }

    public void setContentHeight(int i) {
        this.h = i;
        requestLayout();
    }

    public void setTabSelected(int i) {
        this.i = i;
        int childCount = this.f553c.getChildCount();
        int i2 = 0;
        while (i2 < childCount) {
            View childAt = this.f553c.getChildAt(i2);
            boolean z = i2 == i;
            childAt.setSelected(z);
            if (z) {
                a(i);
            }
            i2++;
        }
        Spinner spinner = this.f554d;
        if (spinner != null && i >= 0) {
            spinner.setSelection(i);
        }
    }

    public void a(int i) {
        View childAt = this.f553c.getChildAt(i);
        Runnable runnable = this.f551a;
        if (runnable != null) {
            removeCallbacks(runnable);
        }
        this.f551a = new O(this, childAt);
        post(this.f551a);
    }

    public c a(AbstractC0020a.c cVar, boolean z) {
        c cVar2 = new c(getContext(), cVar, z);
        if (z) {
            cVar2.setBackgroundDrawable(null);
            cVar2.setLayoutParams(new AbsListView.LayoutParams(-1, this.h));
        } else {
            cVar2.setFocusable(true);
            if (this.f552b == null) {
                this.f552b = new b();
            }
            cVar2.setOnClickListener(this.f552b);
        }
        return cVar2;
    }
}
