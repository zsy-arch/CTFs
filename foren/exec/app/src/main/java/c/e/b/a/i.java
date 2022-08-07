package c.e.b.a;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class i implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f763a;

    /* renamed from: b  reason: collision with root package name */
    public final /* synthetic */ j f764b;

    public i(j jVar, int i) {
        this.f764b = jVar;
        this.f763a = i;
    }

    @Override // java.lang.Runnable
    public void run() {
        this.f764b.a(this.f763a);
    }
}
