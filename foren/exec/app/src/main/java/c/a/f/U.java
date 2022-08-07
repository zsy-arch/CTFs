package c.a.f;

import android.view.View;
import androidx.appcompat.widget.SearchView;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class U implements View.OnFocusChangeListener {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ SearchView f566a;

    public U(SearchView searchView) {
        this.f566a = searchView;
    }

    @Override // android.view.View.OnFocusChangeListener
    public void onFocusChange(View view, boolean z) {
        SearchView searchView = this.f566a;
        View.OnFocusChangeListener onFocusChangeListener = searchView.L;
        if (onFocusChangeListener != null) {
            onFocusChangeListener.onFocusChange(searchView, z);
        }
    }
}
