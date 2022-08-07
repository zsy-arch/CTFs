package c.a.f;

import androidx.appcompat.widget.SearchView;
import c.f.a.a;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class T implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ SearchView f565a;

    public T(SearchView searchView) {
        this.f565a = searchView;
    }

    @Override // java.lang.Runnable
    public void run() {
        a aVar = this.f565a.P;
        if (aVar != null && (aVar instanceof da)) {
            aVar.a(null);
        }
    }
}
