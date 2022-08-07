package c.a.a;

import android.view.View;
import android.widget.AdapterView;
import androidx.appcompat.app.AlertController;

/* loaded from: classes.dex */
public class j implements AdapterView.OnItemClickListener {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ AlertController f360a;

    /* renamed from: b  reason: collision with root package name */
    public final /* synthetic */ AlertController.a f361b;

    public j(AlertController.a aVar, AlertController alertController) {
        this.f361b = aVar;
        this.f360a = alertController;
    }

    @Override // android.widget.AdapterView.OnItemClickListener
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        this.f361b.x.onClick(this.f360a.f12b, i);
        if (!this.f361b.H) {
            this.f360a.f12b.dismiss();
        }
    }
}
