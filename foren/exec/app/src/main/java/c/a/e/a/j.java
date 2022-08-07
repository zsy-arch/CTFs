package c.a.e.a;

import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import androidx.appcompat.app.AlertController;
import androidx.appcompat.view.menu.ExpandedMenuView;
import c.a.a.C0027h;
import c.a.a.C0028i;
import c.a.a.k;
import c.a.a.l;
import c.a.e.a.v;
import c.a.e.a.w;
import c.a.g;
import java.util.ArrayList;

/* loaded from: classes.dex */
public class j implements v, AdapterView.OnItemClickListener {

    /* renamed from: a */
    public Context f438a;

    /* renamed from: b */
    public LayoutInflater f439b;

    /* renamed from: c */
    public l f440c;

    /* renamed from: d */
    public ExpandedMenuView f441d;

    /* renamed from: e */
    public int f442e;
    public int f = 0;
    public int g;
    public v.a h;
    public a i;

    /* loaded from: classes.dex */
    public class a extends BaseAdapter {

        /* renamed from: a */
        public int f443a = -1;

        public a() {
            j.this = r1;
            a();
        }

        public void a() {
            l lVar = j.this.f440c;
            p pVar = lVar.y;
            if (pVar != null) {
                lVar.a();
                ArrayList<p> arrayList = lVar.k;
                int size = arrayList.size();
                for (int i = 0; i < size; i++) {
                    if (arrayList.get(i) == pVar) {
                        this.f443a = i;
                        return;
                    }
                }
            }
            this.f443a = -1;
        }

        @Override // android.widget.Adapter
        public int getCount() {
            l lVar = j.this.f440c;
            lVar.a();
            int size = lVar.k.size() - j.this.f442e;
            return this.f443a < 0 ? size : size - 1;
        }

        @Override // android.widget.Adapter
        public long getItemId(int i) {
            return i;
        }

        @Override // android.widget.Adapter
        public View getView(int i, View view, ViewGroup viewGroup) {
            if (view == null) {
                j jVar = j.this;
                view = jVar.f439b.inflate(jVar.g, viewGroup, false);
            }
            ((w.a) view).a(getItem(i), 0);
            return view;
        }

        @Override // android.widget.BaseAdapter
        public void notifyDataSetChanged() {
            a();
            super.notifyDataSetChanged();
        }

        @Override // android.widget.Adapter
        public p getItem(int i) {
            l lVar = j.this.f440c;
            lVar.a();
            ArrayList<p> arrayList = lVar.k;
            int i2 = i + j.this.f442e;
            int i3 = this.f443a;
            if (i3 >= 0 && i2 >= i3) {
                i2++;
            }
            return arrayList.get(i2);
        }
    }

    public j(Context context, int i) {
        this.g = i;
        this.f438a = context;
        this.f439b = LayoutInflater.from(this.f438a);
    }

    @Override // c.a.e.a.v
    public void a(Context context, l lVar) {
        int i = this.f;
        if (i != 0) {
            this.f438a = new ContextThemeWrapper(context, i);
            this.f439b = LayoutInflater.from(this.f438a);
        } else if (this.f438a != null) {
            this.f438a = context;
            if (this.f439b == null) {
                this.f439b = LayoutInflater.from(this.f438a);
            }
        }
        this.f440c = lVar;
        a aVar = this.i;
        if (aVar != null) {
            aVar.notifyDataSetChanged();
        }
    }

    @Override // c.a.e.a.v
    public boolean a() {
        return false;
    }

    @Override // c.a.e.a.v
    public boolean a(l lVar, p pVar) {
        return false;
    }

    public ListAdapter b() {
        if (this.i == null) {
            this.i = new a();
        }
        return this.i;
    }

    @Override // c.a.e.a.v
    public boolean b(l lVar, p pVar) {
        return false;
    }

    @Override // android.widget.AdapterView.OnItemClickListener
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        this.f440c.a(this.i.getItem(i), this, 0);
    }

    @Override // c.a.e.a.v
    public void a(boolean z) {
        a aVar = this.i;
        if (aVar != null) {
            aVar.notifyDataSetChanged();
        }
    }

    @Override // c.a.e.a.v
    public void a(v.a aVar) {
        this.h = aVar;
    }

    @Override // c.a.e.a.v
    public boolean a(C c2) {
        ListAdapter listAdapter;
        int i;
        if (!c2.hasVisibleItems()) {
            return false;
        }
        m mVar = new m(c2);
        l lVar = mVar.f455a;
        Context context = lVar.f451b;
        int a2 = l.a(context, 0);
        AlertController.a aVar = new AlertController.a(new ContextThemeWrapper(context, l.a(context, a2)));
        mVar.f457c = new j(aVar.f18a, g.abc_list_menu_item_layout);
        mVar.f457c.a(mVar);
        l lVar2 = mVar.f455a;
        lVar2.a(mVar.f457c, lVar2.f451b);
        aVar.w = mVar.f457c.b();
        aVar.x = mVar;
        View view = lVar.q;
        if (view != null) {
            aVar.g = view;
        } else {
            aVar.f21d = lVar.p;
            aVar.f = lVar.o;
        }
        aVar.u = mVar;
        l lVar3 = new l(aVar.f18a, a2);
        AlertController alertController = lVar3.f365c;
        View view2 = aVar.g;
        if (view2 != null) {
            alertController.G = view2;
        } else {
            CharSequence charSequence = aVar.f;
            if (charSequence != null) {
                alertController.a(charSequence);
            }
            Drawable drawable = aVar.f21d;
            if (drawable != null) {
                alertController.C = drawable;
                alertController.B = 0;
                ImageView imageView = alertController.D;
                if (imageView != null) {
                    imageView.setVisibility(0);
                    alertController.D.setImageDrawable(drawable);
                }
            }
            int i2 = aVar.f20c;
            if (i2 != 0) {
                alertController.b(i2);
            }
            int i3 = aVar.f22e;
            if (i3 != 0) {
                alertController.b(alertController.a(i3));
            }
        }
        CharSequence charSequence2 = aVar.h;
        if (charSequence2 != null) {
            alertController.f = charSequence2;
            TextView textView = alertController.F;
            if (textView != null) {
                textView.setText(charSequence2);
            }
        }
        if (!(aVar.i == null && aVar.j == null)) {
            alertController.a(-1, aVar.i, aVar.k, null, aVar.j);
        }
        if (!(aVar.l == null && aVar.m == null)) {
            alertController.a(-2, aVar.l, aVar.n, null, aVar.m);
        }
        if (!(aVar.o == null && aVar.p == null)) {
            alertController.a(-3, aVar.o, aVar.q, null, aVar.p);
        }
        if (!(aVar.v == null && aVar.K == null && aVar.w == null)) {
            AlertController.RecycleListView recycleListView = (AlertController.RecycleListView) aVar.f19b.inflate(alertController.L, (ViewGroup) null);
            if (aVar.G) {
                Cursor cursor = aVar.K;
                if (cursor == null) {
                    listAdapter = new C0027h(aVar, aVar.f18a, alertController.M, 16908308, aVar.v, recycleListView);
                } else {
                    listAdapter = new C0028i(aVar, aVar.f18a, cursor, false, recycleListView, alertController);
                }
            } else {
                if (aVar.H) {
                    i = alertController.N;
                } else {
                    i = alertController.O;
                }
                Cursor cursor2 = aVar.K;
                if (cursor2 != null) {
                    listAdapter = new SimpleCursorAdapter(aVar.f18a, i, cursor2, new String[]{aVar.L}, new int[]{16908308});
                } else {
                    listAdapter = aVar.w;
                    if (listAdapter == null) {
                        listAdapter = new AlertController.c(aVar.f18a, i, 16908308, aVar.v);
                    }
                }
            }
            alertController.H = listAdapter;
            alertController.I = aVar.I;
            if (aVar.x != null) {
                recycleListView.setOnItemClickListener(new c.a.a.j(aVar, alertController));
            } else if (aVar.J != null) {
                recycleListView.setOnItemClickListener(new k(aVar, recycleListView, alertController));
            }
            AdapterView.OnItemSelectedListener onItemSelectedListener = aVar.N;
            if (onItemSelectedListener != null) {
                recycleListView.setOnItemSelectedListener(onItemSelectedListener);
            }
            if (aVar.H) {
                recycleListView.setChoiceMode(1);
            } else if (aVar.G) {
                recycleListView.setChoiceMode(2);
            }
            alertController.g = recycleListView;
        }
        View view3 = aVar.z;
        if (view3 == null) {
            int i4 = aVar.y;
            if (i4 != 0) {
                alertController.h = null;
                alertController.i = i4;
                alertController.n = false;
            }
        } else if (aVar.E) {
            int i5 = aVar.A;
            int i6 = aVar.B;
            int i7 = aVar.C;
            int i8 = aVar.D;
            alertController.h = view3;
            alertController.i = 0;
            alertController.n = true;
            alertController.j = i5;
            alertController.k = i6;
            alertController.l = i7;
            alertController.m = i8;
        } else {
            alertController.h = view3;
            alertController.i = 0;
            alertController.n = false;
        }
        lVar3.setCancelable(aVar.r);
        if (aVar.r) {
            lVar3.setCanceledOnTouchOutside(true);
        }
        lVar3.setOnCancelListener(aVar.s);
        lVar3.setOnDismissListener(aVar.t);
        DialogInterface.OnKeyListener onKeyListener = aVar.u;
        if (onKeyListener != null) {
            lVar3.setOnKeyListener(onKeyListener);
        }
        mVar.f456b = lVar3;
        mVar.f456b.setOnDismissListener(mVar);
        WindowManager.LayoutParams attributes = mVar.f456b.getWindow().getAttributes();
        attributes.type = 1003;
        attributes.flags |= 131072;
        mVar.f456b.show();
        v.a aVar2 = this.h;
        if (aVar2 != null) {
            aVar2.a(c2);
        }
        return true;
    }

    @Override // c.a.e.a.v
    public void a(l lVar, boolean z) {
        v.a aVar = this.h;
        if (aVar != null) {
            aVar.a(lVar, z);
        }
    }
}
