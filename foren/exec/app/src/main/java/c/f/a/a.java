package c.f.a;

import android.content.Context;
import android.database.ContentObserver;
import android.database.Cursor;
import android.database.DataSetObserver;
import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import c.f.a.b;

/* loaded from: classes.dex */
public abstract class a extends BaseAdapter implements Filterable, b.a {

    /* renamed from: a */
    public boolean f894a;

    /* renamed from: b */
    public boolean f895b;

    /* renamed from: c */
    public Cursor f896c;

    /* renamed from: d */
    public Context f897d;

    /* renamed from: e */
    public int f898e;
    public C0014a f;
    public DataSetObserver g;
    public b h;

    /* renamed from: c.f.a.a$a */
    /* loaded from: classes.dex */
    public class C0014a extends ContentObserver {
        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C0014a() {
            super(new Handler());
            a.this = r1;
        }

        @Override // android.database.ContentObserver
        public boolean deliverSelfNotifications() {
            return true;
        }

        @Override // android.database.ContentObserver
        public void onChange(boolean z) {
            Cursor cursor;
            a aVar = a.this;
            if (aVar.f895b && (cursor = aVar.f896c) != null && !cursor.isClosed()) {
                aVar.f894a = aVar.f896c.requery();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public class b extends DataSetObserver {
        public b() {
            a.this = r1;
        }

        @Override // android.database.DataSetObserver
        public void onChanged() {
            a aVar = a.this;
            aVar.f894a = true;
            aVar.notifyDataSetChanged();
        }

        @Override // android.database.DataSetObserver
        public void onInvalidated() {
            a aVar = a.this;
            aVar.f894a = false;
            aVar.notifyDataSetInvalidated();
        }
    }

    public a(Context context, Cursor cursor, boolean z) {
        boolean z2 = true;
        int i = z ? 1 : 2;
        if ((i & 1) == 1) {
            i |= 2;
            this.f895b = true;
        } else {
            this.f895b = false;
        }
        z2 = cursor == null ? false : z2;
        this.f896c = cursor;
        this.f894a = z2;
        this.f897d = context;
        this.f898e = z2 ? cursor.getColumnIndexOrThrow("_id") : -1;
        if ((i & 2) == 2) {
            this.f = new C0014a();
            this.g = new b();
        } else {
            this.f = null;
            this.g = null;
        }
        if (z2) {
            C0014a aVar = this.f;
            if (aVar != null) {
                cursor.registerContentObserver(aVar);
            }
            DataSetObserver dataSetObserver = this.g;
            if (dataSetObserver != null) {
                cursor.registerDataSetObserver(dataSetObserver);
            }
        }
    }

    public abstract View a(Context context, Cursor cursor, ViewGroup viewGroup);

    public void a(Cursor cursor) {
        Cursor cursor2 = this.f896c;
        if (cursor == cursor2) {
            cursor2 = null;
        } else {
            if (cursor2 != null) {
                C0014a aVar = this.f;
                if (aVar != null) {
                    cursor2.unregisterContentObserver(aVar);
                }
                DataSetObserver dataSetObserver = this.g;
                if (dataSetObserver != null) {
                    cursor2.unregisterDataSetObserver(dataSetObserver);
                }
            }
            this.f896c = cursor;
            if (cursor != null) {
                C0014a aVar2 = this.f;
                if (aVar2 != null) {
                    cursor.registerContentObserver(aVar2);
                }
                DataSetObserver dataSetObserver2 = this.g;
                if (dataSetObserver2 != null) {
                    cursor.registerDataSetObserver(dataSetObserver2);
                }
                this.f898e = cursor.getColumnIndexOrThrow("_id");
                this.f894a = true;
                notifyDataSetChanged();
            } else {
                this.f898e = -1;
                this.f894a = false;
                notifyDataSetInvalidated();
            }
        }
        if (cursor2 != null) {
            cursor2.close();
        }
    }

    public abstract void a(View view, Context context, Cursor cursor);

    public abstract CharSequence b(Cursor cursor);

    @Override // android.widget.Adapter
    public int getCount() {
        Cursor cursor;
        if (!this.f894a || (cursor = this.f896c) == null) {
            return 0;
        }
        return cursor.getCount();
    }

    @Override // android.widget.BaseAdapter, android.widget.SpinnerAdapter
    public View getDropDownView(int i, View view, ViewGroup viewGroup) {
        if (!this.f894a) {
            return null;
        }
        this.f896c.moveToPosition(i);
        if (view == null) {
            Context context = this.f897d;
            Cursor cursor = this.f896c;
            c cVar = (c) this;
            view = cVar.k.inflate(cVar.j, viewGroup, false);
        }
        a(view, this.f897d, this.f896c);
        return view;
    }

    @Override // android.widget.Filterable
    public Filter getFilter() {
        if (this.h == null) {
            this.h = new b(this);
        }
        return this.h;
    }

    @Override // android.widget.Adapter
    public Object getItem(int i) {
        Cursor cursor;
        if (!this.f894a || (cursor = this.f896c) == null) {
            return null;
        }
        cursor.moveToPosition(i);
        return this.f896c;
    }

    @Override // android.widget.Adapter
    public long getItemId(int i) {
        Cursor cursor;
        if (!this.f894a || (cursor = this.f896c) == null || !cursor.moveToPosition(i)) {
            return 0L;
        }
        return this.f896c.getLong(this.f898e);
    }

    @Override // android.widget.Adapter
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (!this.f894a) {
            throw new IllegalStateException("this should only be called when the cursor is valid");
        } else if (this.f896c.moveToPosition(i)) {
            if (view == null) {
                view = a(this.f897d, this.f896c, viewGroup);
            }
            a(view, this.f897d, this.f896c);
            return view;
        } else {
            throw new IllegalStateException("couldn't move cursor to position " + i);
        }
    }
}
