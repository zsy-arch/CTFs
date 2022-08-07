package c.j;

import androidx.lifecycle.LiveData;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class k implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ LiveData f1032a;

    public k(LiveData liveData) {
        this.f1032a = liveData;
    }

    @Override // java.lang.Runnable
    public void run() {
        Object obj;
        synchronized (this.f1032a.f269b) {
            obj = this.f1032a.f;
            this.f1032a.f = LiveData.f268a;
        }
        this.f1032a.a((LiveData) obj);
    }
}
