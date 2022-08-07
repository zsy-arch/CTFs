package c.f.a;

import android.database.Cursor;
import android.widget.Filter;
import c.a.f.da;

/* loaded from: classes.dex */
public class b extends Filter {

    /* renamed from: a  reason: collision with root package name */
    public a f901a;

    /* loaded from: classes.dex */
    interface a {
    }

    public b(a aVar) {
        this.f901a = aVar;
    }

    @Override // android.widget.Filter
    public CharSequence convertResultToString(Object obj) {
        return ((da) this.f901a).b((Cursor) obj);
    }

    @Override // android.widget.Filter
    public Filter.FilterResults performFiltering(CharSequence charSequence) {
        Cursor a2 = ((da) this.f901a).a(charSequence);
        Filter.FilterResults filterResults = new Filter.FilterResults();
        if (a2 != null) {
            filterResults.count = a2.getCount();
            filterResults.values = a2;
        } else {
            filterResults.count = 0;
            filterResults.values = null;
        }
        return filterResults;
    }

    @Override // android.widget.Filter
    public void publishResults(CharSequence charSequence, Filter.FilterResults filterResults) {
        a aVar = this.f901a;
        Cursor cursor = ((a) aVar).f896c;
        Object obj = filterResults.values;
        if (obj != null && obj != cursor) {
            ((da) aVar).a((Cursor) obj);
        }
    }
}
