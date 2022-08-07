package d.a.a.d;

import android.view.View;
import d.a.a.c.a.e;
import d.a.a.d.d;

/* loaded from: classes.dex */
public class b implements View.OnClickListener {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ d.a f1624a;

    /* renamed from: b  reason: collision with root package name */
    public final /* synthetic */ d f1625b;

    public b(d dVar, d.a aVar) {
        this.f1625b = dVar;
        this.f1624a = aVar;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        ((e) this.f1624a).a(view);
        if (this.f1625b.isShowing()) {
            this.f1625b.dismiss();
        }
    }
}
