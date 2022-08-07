package c.a.f;

import android.view.View;
import android.view.Window;
import c.a.e.a.C0029a;

/* loaded from: classes.dex */
public class pa implements View.OnClickListener {

    /* renamed from: a  reason: collision with root package name */
    public final C0029a f621a;

    /* renamed from: b  reason: collision with root package name */
    public final /* synthetic */ ra f622b;

    public pa(ra raVar) {
        this.f622b = raVar;
        this.f621a = new C0029a(this.f622b.f635a.getContext(), 0, 16908332, 0, 0, this.f622b.i);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        ra raVar = this.f622b;
        Window.Callback callback = raVar.l;
        if (callback != null && raVar.m) {
            callback.onMenuItemSelected(0, this.f621a);
        }
    }
}
