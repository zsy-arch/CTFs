package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import androidx.appcompat.view.menu.ActionMenuItemView;
import androidx.appcompat.widget.ActionMenuView;
import c.a.e.a.AbstractC0030b;
import c.a.e.a.C;
import c.a.e.a.l;
import c.a.e.a.p;
import c.a.e.a.s;
import c.a.e.a.u;
import c.a.e.a.v;
import c.a.e.a.w;
import c.a.f.C0039g;
import c.a.f.C0040h;
import c.a.g;
import c.e.h.b;
import java.util.ArrayList;

/* loaded from: classes.dex */
public class ActionMenuPresenter extends AbstractC0030b implements b.a {
    public b A;
    public d i;
    public Drawable j;
    public boolean k;
    public boolean l;
    public boolean m;
    public int n;
    public int o;
    public int p;
    public boolean q;
    public boolean r;
    public boolean s;
    public boolean t;
    public int u;
    public View w;
    public e x;
    public a y;
    public c z;
    public final SparseBooleanArray v = new SparseBooleanArray();
    public final f B = new f();

    /* loaded from: classes.dex */
    public static class SavedState implements Parcelable {
        public static final Parcelable.Creator<SavedState> CREATOR = new C0040h();

        /* renamed from: a */
        public int f75a;

        public SavedState() {
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeInt(this.f75a);
        }

        public SavedState(Parcel parcel) {
            this.f75a = parcel.readInt();
        }
    }

    /* loaded from: classes.dex */
    public class a extends u {
        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public a(Context context, C c2, View view) {
            super(context, c2, view, false, c.a.a.actionOverflowMenuStyle, 0);
            ActionMenuPresenter.this = r8;
            if (!c2.C.d()) {
                View view2 = r8.i;
                this.f = view2 == null ? (View) r8.h : view2;
            }
            a(r8.B);
        }

        @Override // c.a.e.a.u
        public void d() {
            ActionMenuPresenter.this.y = null;
            this.j = null;
            PopupWindow.OnDismissListener onDismissListener = this.k;
            if (onDismissListener != null) {
                onDismissListener.onDismiss();
            }
        }
    }

    /* loaded from: classes.dex */
    public class b extends ActionMenuItemView.b {
        public b() {
            ActionMenuPresenter.this = r1;
        }
    }

    /* loaded from: classes.dex */
    public class c implements Runnable {

        /* renamed from: a */
        public e f77a;

        public c(e eVar) {
            ActionMenuPresenter.this = r1;
            this.f77a = eVar;
        }

        @Override // java.lang.Runnable
        public void run() {
            l.a aVar;
            l lVar = ActionMenuPresenter.this.f417c;
            if (!(lVar == null || (aVar = lVar.f) == null)) {
                aVar.a(lVar);
            }
            View view = (View) ActionMenuPresenter.this.h;
            if (!(view == null || view.getWindowToken() == null || !this.f77a.e())) {
                ActionMenuPresenter.this.x = this.f77a;
            }
            ActionMenuPresenter.this.z = null;
        }
    }

    /* loaded from: classes.dex */
    public class d extends AppCompatImageView implements ActionMenuView.a {
        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public d(Context context) {
            super(context, null, c.a.a.actionOverflowButtonStyle);
            ActionMenuPresenter.this = r3;
            float[] fArr = new float[2];
            setClickable(true);
            setFocusable(true);
            setVisibility(0);
            setEnabled(true);
            c.a.a.C.a((View) this, getContentDescription());
            setOnTouchListener(new C0039g(this, this, r3));
        }

        @Override // androidx.appcompat.widget.ActionMenuView.a
        public boolean a() {
            return false;
        }

        @Override // androidx.appcompat.widget.ActionMenuView.a
        public boolean b() {
            return false;
        }

        @Override // android.view.View
        public boolean performClick() {
            if (super.performClick()) {
                return true;
            }
            playSoundEffect(0);
            ActionMenuPresenter.this.e();
            return true;
        }

        @Override // android.widget.ImageView
        public boolean setFrame(int i, int i2, int i3, int i4) {
            boolean frame = super.setFrame(i, i2, i3, i4);
            Drawable drawable = getDrawable();
            Drawable background = getBackground();
            if (!(drawable == null || background == null)) {
                int width = getWidth();
                int height = getHeight();
                int max = Math.max(width, height) / 2;
                int paddingLeft = (width + (getPaddingLeft() - getPaddingRight())) / 2;
                int paddingTop = (height + (getPaddingTop() - getPaddingBottom())) / 2;
                int i5 = paddingLeft - max;
                int i6 = paddingTop - max;
                int i7 = paddingLeft + max;
                int i8 = paddingTop + max;
                int i9 = Build.VERSION.SDK_INT;
                background.setHotspotBounds(i5, i6, i7, i8);
            }
            return frame;
        }
    }

    /* loaded from: classes.dex */
    public class e extends u {
        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public e(Context context, l lVar, View view, boolean z) {
            super(context, lVar, view, z, c.a.a.actionOverflowMenuStyle, 0);
            ActionMenuPresenter.this = r8;
            this.g = 8388613;
            a(r8.B);
        }

        @Override // c.a.e.a.u
        public void d() {
            l lVar = ActionMenuPresenter.this.f417c;
            if (lVar != null) {
                lVar.a(true);
            }
            ActionMenuPresenter.this.x = null;
            this.j = null;
            PopupWindow.OnDismissListener onDismissListener = this.k;
            if (onDismissListener != null) {
                onDismissListener.onDismiss();
            }
        }
    }

    public ActionMenuPresenter(Context context) {
        super(context, g.abc_action_menu_layout, g.abc_action_menu_item_layout);
    }

    @Override // c.a.e.a.v
    public void a(Context context, l lVar) {
        this.f416b = context;
        LayoutInflater.from(this.f416b);
        this.f417c = lVar;
        Resources resources = context.getResources();
        if (!this.m) {
            int i = Build.VERSION.SDK_INT;
            this.l = true;
        }
        int i2 = 2;
        if (!this.s) {
            this.n = context.getResources().getDisplayMetrics().widthPixels / 2;
        }
        if (!this.q) {
            Configuration configuration = context.getResources().getConfiguration();
            int i3 = configuration.screenWidthDp;
            int i4 = configuration.screenHeightDp;
            if (configuration.smallestScreenWidthDp > 600 || i3 > 600 || ((i3 > 960 && i4 > 720) || (i3 > 720 && i4 > 960))) {
                i2 = 5;
            } else if (i3 >= 500 || ((i3 > 640 && i4 > 480) || (i3 > 480 && i4 > 640))) {
                i2 = 4;
            } else if (i3 >= 360) {
                i2 = 3;
            }
            this.p = i2;
        }
        int i5 = this.n;
        if (this.l) {
            if (this.i == null) {
                this.i = new d(this.f415a);
                if (this.k) {
                    this.i.setImageDrawable(this.j);
                    this.j = null;
                    this.k = false;
                }
                int makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, 0);
                this.i.measure(makeMeasureSpec, makeMeasureSpec);
            }
            i5 -= this.i.getMeasuredWidth();
        } else {
            this.i = null;
        }
        this.o = i5;
        this.u = (int) (resources.getDisplayMetrics().density * 56.0f);
        this.w = null;
    }

    public boolean b() {
        boolean z;
        boolean c2 = c();
        a aVar = this.y;
        if (aVar != null) {
            aVar.a();
            z = true;
        } else {
            z = false;
        }
        return c2 | z;
    }

    public boolean c() {
        w wVar;
        c cVar = this.z;
        if (cVar == null || (wVar = this.h) == null) {
            e eVar = this.x;
            if (eVar == null) {
                return false;
            }
            if (eVar.c()) {
                eVar.j.dismiss();
            }
            return true;
        }
        ((View) wVar).removeCallbacks(cVar);
        this.z = null;
        return true;
    }

    public boolean d() {
        e eVar = this.x;
        return eVar != null && eVar.c();
    }

    public boolean e() {
        l lVar;
        if (!this.l || d() || (lVar = this.f417c) == null || this.h == null || this.z != null) {
            return false;
        }
        lVar.a();
        if (lVar.k.isEmpty()) {
            return false;
        }
        this.z = new c(new e(this.f416b, this.f417c, this.i, true));
        ((View) this.h).post(this.z);
        super.a((C) null);
        return true;
    }

    public void b(boolean z) {
        if (z) {
            super.a((C) null);
            return;
        }
        l lVar = this.f417c;
        if (lVar != null) {
            lVar.a(false);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public class f implements v.a {
        public f() {
            ActionMenuPresenter.this = r1;
        }

        @Override // c.a.e.a.v.a
        public boolean a(l lVar) {
            if (lVar == null) {
                return false;
            }
            ActionMenuPresenter actionMenuPresenter = ActionMenuPresenter.this;
            ((C) lVar).C.getItemId();
            v.a aVar = ActionMenuPresenter.this.f419e;
            if (aVar != null) {
                return aVar.a(lVar);
            }
            return false;
        }

        @Override // c.a.e.a.v.a
        public void a(l lVar, boolean z) {
            if (lVar instanceof C) {
                lVar.c().a(false);
            }
            v.a aVar = ActionMenuPresenter.this.f419e;
            if (aVar != null) {
                aVar.a(lVar, z);
            }
        }
    }

    @Override // c.a.e.a.AbstractC0030b, c.a.e.a.v
    public void a(boolean z) {
        w wVar;
        int i;
        boolean z2;
        ViewGroup viewGroup = (ViewGroup) this.h;
        boolean z3 = false;
        ArrayList<p> arrayList = null;
        if (viewGroup != null) {
            l lVar = this.f417c;
            if (lVar != null) {
                lVar.a();
                ArrayList<p> d2 = this.f417c.d();
                int size = d2.size();
                i = 0;
                for (int i2 = 0; i2 < size; i2++) {
                    p pVar = d2.get(i2);
                    if (pVar.d()) {
                        View childAt = viewGroup.getChildAt(i);
                        p itemData = childAt instanceof w.a ? ((w.a) childAt).getItemData() : null;
                        View a2 = a(pVar, childAt, viewGroup);
                        if (pVar != itemData) {
                            a2.setPressed(false);
                            a2.jumpDrawablesToCurrentState();
                        }
                        if (a2 != childAt) {
                            ViewGroup viewGroup2 = (ViewGroup) a2.getParent();
                            if (viewGroup2 != null) {
                                viewGroup2.removeView(a2);
                            }
                            ((ViewGroup) this.h).addView(a2, i);
                        }
                        i++;
                    }
                }
            } else {
                i = 0;
            }
            while (i < viewGroup.getChildCount()) {
                if (viewGroup.getChildAt(i) == this.i) {
                    z2 = false;
                } else {
                    viewGroup.removeViewAt(i);
                    z2 = true;
                }
                if (!z2) {
                    i++;
                }
            }
        }
        ((View) this.h).requestLayout();
        l lVar2 = this.f417c;
        if (lVar2 != null) {
            lVar2.a();
            ArrayList<p> arrayList2 = lVar2.j;
            int size2 = arrayList2.size();
            for (int i3 = 0; i3 < size2; i3++) {
                c.e.h.b bVar = arrayList2.get(i3).B;
                if (bVar != null) {
                    bVar.f849a = this;
                }
            }
        }
        l lVar3 = this.f417c;
        if (lVar3 != null) {
            lVar3.a();
            arrayList = lVar3.k;
        }
        if (this.l && arrayList != null) {
            int size3 = arrayList.size();
            if (size3 == 1) {
                z3 = !arrayList.get(0).D;
            } else if (size3 > 0) {
                z3 = true;
            }
        }
        if (z3) {
            if (this.i == null) {
                this.i = new d(this.f415a);
            }
            ViewGroup viewGroup3 = (ViewGroup) this.i.getParent();
            if (viewGroup3 != this.h) {
                if (viewGroup3 != null) {
                    viewGroup3.removeView(this.i);
                }
                ActionMenuView actionMenuView = (ActionMenuView) this.h;
                actionMenuView.addView(this.i, actionMenuView.b());
            }
        } else {
            d dVar = this.i;
            if (dVar != null && dVar.getParent() == (wVar = this.h)) {
                ((ViewGroup) wVar).removeView(this.i);
            }
        }
        ((ActionMenuView) this.h).setOverflowReserved(this.l);
    }

    @Override // c.a.e.a.AbstractC0030b
    public View a(p pVar, View view, ViewGroup viewGroup) {
        w.a aVar;
        View actionView = pVar.getActionView();
        int i = 0;
        if (actionView == null || pVar.c()) {
            if (view instanceof w.a) {
                aVar = (w.a) view;
            } else {
                aVar = (w.a) this.f418d.inflate(this.g, viewGroup, false);
            }
            aVar.a(pVar, 0);
            ActionMenuItemView actionMenuItemView = (ActionMenuItemView) aVar;
            actionMenuItemView.setItemInvoker((ActionMenuView) this.h);
            if (this.A == null) {
                this.A = new b();
            }
            actionMenuItemView.setPopupCallback(this.A);
            actionView = (View) aVar;
        }
        if (pVar.D) {
            i = 8;
        }
        actionView.setVisibility(i);
        ActionMenuView actionMenuView = (ActionMenuView) viewGroup;
        ViewGroup.LayoutParams layoutParams = actionView.getLayoutParams();
        if (!actionMenuView.checkLayoutParams(layoutParams)) {
            actionView.setLayoutParams(actionMenuView.generateLayoutParams(layoutParams));
        }
        return actionView;
    }

    @Override // c.a.e.a.AbstractC0030b, c.a.e.a.v
    public boolean a(C c2) {
        boolean z = false;
        if (!c2.hasVisibleItems()) {
            return false;
        }
        C c3 = c2;
        while (c3.B != this.f417c) {
            c3 = (C) c3.B;
        }
        p pVar = c3.C;
        ViewGroup viewGroup = (ViewGroup) this.h;
        View view = null;
        if (viewGroup != null) {
            int childCount = viewGroup.getChildCount();
            int i = 0;
            while (true) {
                if (i >= childCount) {
                    break;
                }
                View childAt = viewGroup.getChildAt(i);
                if ((childAt instanceof w.a) && ((w.a) childAt).getItemData() == pVar) {
                    view = childAt;
                    break;
                }
                i++;
            }
        }
        if (view == null) {
            return false;
        }
        c2.C.getItemId();
        int size = c2.size();
        int i2 = 0;
        while (true) {
            if (i2 >= size) {
                break;
            }
            MenuItem item = c2.getItem(i2);
            if (item.isVisible() && item.getIcon() != null) {
                z = true;
                break;
            }
            i2++;
        }
        this.y = new a(this.f416b, c2, view);
        a aVar = this.y;
        aVar.h = z;
        s sVar = aVar.j;
        if (sVar != null) {
            sVar.b(z);
        }
        if (this.y.e()) {
            v.a aVar2 = this.f419e;
            if (aVar2 != null) {
                aVar2.a(c2);
            }
            return true;
        }
        throw new IllegalStateException("MenuPopupHelper cannot be used without an anchor");
    }

    @Override // c.a.e.a.v
    public boolean a() {
        int i;
        ArrayList<p> arrayList;
        int i2;
        int i3;
        int i4;
        boolean z;
        ActionMenuPresenter actionMenuPresenter = this;
        l lVar = actionMenuPresenter.f417c;
        int i5 = 0;
        if (lVar != null) {
            arrayList = lVar.d();
            i = arrayList.size();
        } else {
            arrayList = null;
            i = 0;
        }
        int i6 = actionMenuPresenter.p;
        int i7 = actionMenuPresenter.o;
        int makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, 0);
        ViewGroup viewGroup = (ViewGroup) actionMenuPresenter.h;
        int i8 = i6;
        int i9 = 0;
        boolean z2 = false;
        int i10 = 0;
        int i11 = 0;
        while (true) {
            i2 = 2;
            if (i9 >= i) {
                break;
            }
            p pVar = arrayList.get(i9);
            if ((pVar.z & 2) == 2) {
                i10++;
            } else if ((pVar.z & 1) == 1) {
                i11++;
            } else {
                z2 = true;
            }
            if (actionMenuPresenter.t && pVar.D) {
                i8 = 0;
            }
            i9++;
        }
        if (actionMenuPresenter.l && (z2 || i11 + i10 > i8)) {
            i8--;
        }
        int i12 = i8 - i10;
        SparseBooleanArray sparseBooleanArray = actionMenuPresenter.v;
        sparseBooleanArray.clear();
        if (actionMenuPresenter.r) {
            int i13 = actionMenuPresenter.u;
            i3 = i7 / i13;
            i4 = i13 + ((i7 % i13) / i3);
        } else {
            i4 = 0;
            i3 = 0;
        }
        int i14 = i7;
        int i15 = 0;
        int i16 = 0;
        while (i15 < i) {
            p pVar2 = arrayList.get(i15);
            if ((pVar2.z & i2) == i2) {
                View a2 = actionMenuPresenter.a(pVar2, actionMenuPresenter.w, viewGroup);
                if (actionMenuPresenter.w == null) {
                    actionMenuPresenter.w = a2;
                }
                if (actionMenuPresenter.r) {
                    i3 -= ActionMenuView.a(a2, i4, i3, makeMeasureSpec, i5);
                } else {
                    a2.measure(makeMeasureSpec, makeMeasureSpec);
                }
                i16 = a2.getMeasuredWidth();
                i14 -= i16;
                if (i16 != 0) {
                    i16 = i16;
                }
                int i17 = pVar2.f461b;
                if (i17 != 0) {
                    z = true;
                    sparseBooleanArray.put(i17, true);
                } else {
                    z = true;
                }
                pVar2.c(z);
                i = i;
            } else if ((pVar2.z & 1) == 1) {
                int i18 = pVar2.f461b;
                boolean z3 = sparseBooleanArray.get(i18);
                boolean z4 = (i12 > 0 || z3) && i14 > 0 && (!actionMenuPresenter.r || i3 > 0);
                if (z4) {
                    boolean z5 = z4;
                    View a3 = actionMenuPresenter.a(pVar2, actionMenuPresenter.w, viewGroup);
                    i = i;
                    if (actionMenuPresenter.w == null) {
                        actionMenuPresenter.w = a3;
                    }
                    if (actionMenuPresenter.r) {
                        int a4 = ActionMenuView.a(a3, i4, i3, makeMeasureSpec, 0);
                        i3 -= a4;
                        if (a4 == 0) {
                            z5 = false;
                        }
                    } else {
                        a3.measure(makeMeasureSpec, makeMeasureSpec);
                    }
                    int measuredWidth = a3.getMeasuredWidth();
                    i14 -= measuredWidth;
                    if (i16 == 0) {
                        i16 = measuredWidth;
                    }
                    z4 = z5 & (!actionMenuPresenter.r ? i14 + i16 > 0 : i14 >= 0);
                } else {
                    i = i;
                }
                if (z4 && i18 != 0) {
                    sparseBooleanArray.put(i18, true);
                } else if (z3) {
                    sparseBooleanArray.put(i18, false);
                    for (int i19 = 0; i19 < i15; i19++) {
                        p pVar3 = arrayList.get(i19);
                        if (pVar3.f461b == i18) {
                            if (pVar3.d()) {
                                i12++;
                            }
                            pVar3.c(false);
                        }
                    }
                }
                if (z4) {
                    i12--;
                }
                pVar2.c(z4);
            } else {
                i = i;
                pVar2.c(false);
                i15++;
                i5 = 0;
                i2 = 2;
                actionMenuPresenter = this;
            }
            i15++;
            i5 = 0;
            i2 = 2;
            actionMenuPresenter = this;
        }
        return true;
    }

    @Override // c.a.e.a.v
    public void a(l lVar, boolean z) {
        b();
        v.a aVar = this.f419e;
        if (aVar != null) {
            aVar.a(lVar, z);
        }
    }
}
