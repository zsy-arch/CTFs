package c.a.f;

import androidx.appcompat.widget.SearchView;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class S implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ SearchView f564a;

    public S(SearchView searchView) {
        this.f564a = searchView;
    }

    @Override // java.lang.Runnable
    public void run() {
        this.f564a.k();
    }
}
