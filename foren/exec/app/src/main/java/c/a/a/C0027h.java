package c.a.a;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import androidx.appcompat.app.AlertController;

/* renamed from: c.a.a.h  reason: case insensitive filesystem */
/* loaded from: classes.dex */
public class C0027h extends ArrayAdapter<CharSequence> {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ AlertController.RecycleListView f353a;

    /* renamed from: b  reason: collision with root package name */
    public final /* synthetic */ AlertController.a f354b;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0027h(AlertController.a aVar, Context context, int i, int i2, CharSequence[] charSequenceArr, AlertController.RecycleListView recycleListView) {
        super(context, i, i2, charSequenceArr);
        this.f354b = aVar;
        this.f353a = recycleListView;
    }

    @Override // android.widget.ArrayAdapter, android.widget.Adapter
    public View getView(int i, View view, ViewGroup viewGroup) {
        View view2 = super.getView(i, view, viewGroup);
        boolean[] zArr = this.f354b.F;
        if (zArr != null && zArr[i]) {
            this.f353a.setItemChecked(i, true);
        }
        return view2;
    }
}
