package c.a.f;

import android.view.View;
import androidx.appcompat.widget.SearchView;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class W implements View.OnClickListener {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ SearchView f568a;

    public W(SearchView searchView) {
        this.f568a = searchView;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        SearchView searchView = this.f568a;
        if (view == searchView.u) {
            searchView.f();
        } else if (view == searchView.w) {
            searchView.e();
        } else if (view == searchView.v) {
            searchView.g();
        } else if (view == searchView.x) {
            searchView.i();
        } else if (view == searchView.q) {
            searchView.b();
        }
    }
}
