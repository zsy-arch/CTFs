package c.a.e.a;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import androidx.appcompat.view.menu.ListMenuItemView;
import c.a.e.a.w;
import java.util.ArrayList;

/* loaded from: classes.dex */
public class k extends BaseAdapter {

    /* renamed from: a */
    public l f445a;

    /* renamed from: b */
    public int f446b = -1;

    /* renamed from: c */
    public boolean f447c;

    /* renamed from: d */
    public final boolean f448d;

    /* renamed from: e */
    public final LayoutInflater f449e;
    public final int f;

    public k(l lVar, LayoutInflater layoutInflater, boolean z, int i) {
        this.f448d = z;
        this.f449e = layoutInflater;
        this.f445a = lVar;
        this.f = i;
        a();
    }

    public void a() {
        l lVar = this.f445a;
        p pVar = lVar.y;
        if (pVar != null) {
            lVar.a();
            ArrayList<p> arrayList = lVar.k;
            int size = arrayList.size();
            for (int i = 0; i < size; i++) {
                if (arrayList.get(i) == pVar) {
                    this.f446b = i;
                    return;
                }
            }
        }
        this.f446b = -1;
    }

    @Override // android.widget.Adapter
    public int getCount() {
        ArrayList<p> arrayList;
        if (this.f448d) {
            l lVar = this.f445a;
            lVar.a();
            arrayList = lVar.k;
        } else {
            arrayList = this.f445a.d();
        }
        if (this.f446b < 0) {
            return arrayList.size();
        }
        return arrayList.size() - 1;
    }

    @Override // android.widget.Adapter
    public long getItemId(int i) {
        return i;
    }

    @Override // android.widget.Adapter
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = this.f449e.inflate(this.f, viewGroup, false);
        }
        int i2 = getItem(i).f461b;
        int i3 = i - 1;
        ListMenuItemView listMenuItemView = (ListMenuItemView) view;
        listMenuItemView.setGroupDividerEnabled(this.f445a.e() && i2 != (i3 >= 0 ? getItem(i3).f461b : i2));
        w.a aVar = (w.a) view;
        if (this.f447c) {
            listMenuItemView.setForceShowIcon(true);
        }
        aVar.a(getItem(i), 0);
        return view;
    }

    @Override // android.widget.BaseAdapter
    public void notifyDataSetChanged() {
        a();
        super.notifyDataSetChanged();
    }

    @Override // android.widget.Adapter
    public p getItem(int i) {
        ArrayList<p> arrayList;
        if (this.f448d) {
            l lVar = this.f445a;
            lVar.a();
            arrayList = lVar.k;
        } else {
            arrayList = this.f445a.d();
        }
        int i2 = this.f446b;
        if (i2 >= 0 && i >= i2) {
            i++;
        }
        return arrayList.get(i);
    }
}
