package c.a.f;

import android.view.View;
import android.widget.AdapterView;
import androidx.appcompat.widget.SearchView;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class Z implements AdapterView.OnItemClickListener {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ SearchView f571a;

    public Z(SearchView searchView) {
        this.f571a = searchView;
    }

    @Override // android.widget.AdapterView.OnItemClickListener
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        this.f571a.a(i, 0, (String) null);
    }
}
