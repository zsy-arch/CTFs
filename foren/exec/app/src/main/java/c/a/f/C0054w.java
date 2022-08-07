package c.a.f;

import android.view.View;
import android.widget.AdapterView;
import androidx.appcompat.widget.AppCompatSpinner;

/* JADX INFO: Access modifiers changed from: package-private */
/* renamed from: c.a.f.w  reason: case insensitive filesystem */
/* loaded from: classes.dex */
public class C0054w implements AdapterView.OnItemClickListener {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ AppCompatSpinner.b f659a;

    public C0054w(AppCompatSpinner.b bVar, AppCompatSpinner appCompatSpinner) {
        this.f659a = bVar;
    }

    @Override // android.widget.AdapterView.OnItemClickListener
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        AppCompatSpinner.this.setSelection(i);
        if (AppCompatSpinner.this.getOnItemClickListener() != null) {
            AppCompatSpinner.b bVar = this.f659a;
            AppCompatSpinner.this.performItemClick(view, i, bVar.J.getItemId(i));
        }
        this.f659a.dismiss();
    }
}
