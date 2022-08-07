package c.a.f;

import android.view.KeyEvent;
import android.view.View;
import androidx.appcompat.widget.SearchView;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class X implements View.OnKeyListener {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ SearchView f569a;

    public X(SearchView searchView) {
        this.f569a = searchView;
    }

    @Override // android.view.View.OnKeyListener
    public boolean onKey(View view, int i, KeyEvent keyEvent) {
        SearchView searchView = this.f569a;
        if (searchView.da == null) {
            return false;
        }
        if (searchView.q.isPopupShowing() && this.f569a.q.getListSelection() != -1) {
            return this.f569a.a(view, i, keyEvent);
        }
        if (this.f569a.q.a() || !keyEvent.hasNoModifiers() || keyEvent.getAction() != 1 || i != 66) {
            return false;
        }
        view.cancelLongPress();
        SearchView searchView2 = this.f569a;
        searchView2.a(0, (String) null, searchView2.q.getText().toString());
        return true;
    }
}
