package c.a.e.a;

import android.content.Context;
import android.graphics.Rect;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.HeaderViewListAdapter;
import android.widget.ListAdapter;
import android.widget.PopupWindow;

/* loaded from: classes.dex */
public abstract class s implements y, v, AdapterView.OnItemClickListener {

    /* renamed from: a */
    public Rect f472a;

    public static int a(ListAdapter listAdapter, ViewGroup viewGroup, Context context, int i) {
        int makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, 0);
        int makeMeasureSpec2 = View.MeasureSpec.makeMeasureSpec(0, 0);
        int count = listAdapter.getCount();
        ViewGroup viewGroup2 = viewGroup;
        View view = null;
        int i2 = 0;
        int i3 = 0;
        for (int i4 = 0; i4 < count; i4++) {
            int itemViewType = listAdapter.getItemViewType(i4);
            if (itemViewType != i3) {
                view = null;
                i3 = itemViewType;
            }
            if (viewGroup2 == null) {
                viewGroup2 = new FrameLayout(context);
            }
            view = listAdapter.getView(i4, view, viewGroup2);
            view.measure(makeMeasureSpec, makeMeasureSpec2);
            int measuredWidth = view.getMeasuredWidth();
            if (measuredWidth >= i) {
                return i;
            }
            if (measuredWidth > i2) {
                i2 = measuredWidth;
            }
        }
        return i2;
    }

    public static boolean b(l lVar) {
        int size = lVar.size();
        for (int i = 0; i < size; i++) {
            MenuItem item = lVar.getItem(i);
            if (item.isVisible() && item.getIcon() != null) {
                return true;
            }
        }
        return false;
    }

    public abstract void a(int i);

    @Override // c.a.e.a.v
    public void a(Context context, l lVar) {
    }

    public abstract void a(View view);

    public abstract void a(PopupWindow.OnDismissListener onDismissListener);

    public abstract void a(l lVar);

    @Override // c.a.e.a.v
    public boolean a(l lVar, p pVar) {
        return false;
    }

    public abstract void b(int i);

    public abstract void b(boolean z);

    @Override // c.a.e.a.v
    public boolean b(l lVar, p pVar) {
        return false;
    }

    public abstract void c(int i);

    public abstract void c(boolean z);

    public boolean e() {
        return true;
    }

    public Rect f() {
        return this.f472a;
    }

    @Override // android.widget.AdapterView.OnItemClickListener
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        ListAdapter listAdapter = (ListAdapter) adapterView.getAdapter();
        a(listAdapter).f445a.a((MenuItem) listAdapter.getItem(i), this, e() ? 0 : 4);
    }

    public static k a(ListAdapter listAdapter) {
        if (listAdapter instanceof HeaderViewListAdapter) {
            return (k) ((HeaderViewListAdapter) listAdapter).getWrappedAdapter();
        }
        return (k) listAdapter;
    }
}
