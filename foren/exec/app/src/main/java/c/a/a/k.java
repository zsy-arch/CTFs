package c.a.a;

import android.view.View;
import android.widget.AdapterView;
import androidx.appcompat.app.AlertController;

/* loaded from: classes.dex */
public class k implements AdapterView.OnItemClickListener {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ AlertController.RecycleListView f362a;

    /* renamed from: b  reason: collision with root package name */
    public final /* synthetic */ AlertController f363b;

    /* renamed from: c  reason: collision with root package name */
    public final /* synthetic */ AlertController.a f364c;

    public k(AlertController.a aVar, AlertController.RecycleListView recycleListView, AlertController alertController) {
        this.f364c = aVar;
        this.f362a = recycleListView;
        this.f363b = alertController;
    }

    @Override // android.widget.AdapterView.OnItemClickListener
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        boolean[] zArr = this.f364c.F;
        if (zArr != null) {
            zArr[i] = this.f362a.isItemChecked(i);
        }
        this.f364c.J.onClick(this.f363b.f12b, i, this.f362a.isItemChecked(i));
    }
}
