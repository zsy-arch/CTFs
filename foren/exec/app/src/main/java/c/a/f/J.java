package c.a.f;

import android.view.View;
import android.widget.AdapterView;
import androidx.appcompat.widget.ListPopupWindow;

/* loaded from: classes.dex */
public class J implements AdapterView.OnItemSelectedListener {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ ListPopupWindow f543a;

    public J(ListPopupWindow listPopupWindow) {
        this.f543a = listPopupWindow;
    }

    @Override // android.widget.AdapterView.OnItemSelectedListener
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long j) {
        F f;
        if (i != -1 && (f = this.f543a.f) != null) {
            f.setListSelectionHidden(false);
        }
    }

    @Override // android.widget.AdapterView.OnItemSelectedListener
    public void onNothingSelected(AdapterView<?> adapterView) {
    }
}
