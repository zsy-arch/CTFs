package c.a.f;

import android.text.Editable;
import android.text.TextWatcher;
import androidx.appcompat.widget.SearchView;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class Q implements TextWatcher {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ SearchView f563a;

    public Q(SearchView searchView) {
        this.f563a = searchView;
    }

    @Override // android.text.TextWatcher
    public void afterTextChanged(Editable editable) {
    }

    @Override // android.text.TextWatcher
    public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    @Override // android.text.TextWatcher
    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        this.f563a.b(charSequence);
    }
}
