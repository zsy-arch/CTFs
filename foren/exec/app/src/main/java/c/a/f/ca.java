package c.a.f;

import androidx.appcompat.widget.SearchView;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class ca implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ SearchView.SearchAutoComplete f583a;

    public ca(SearchView.SearchAutoComplete searchAutoComplete) {
        this.f583a = searchAutoComplete;
    }

    @Override // java.lang.Runnable
    public void run() {
        this.f583a.b();
    }
}
