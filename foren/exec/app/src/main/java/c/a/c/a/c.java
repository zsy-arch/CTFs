package c.a.c.a;

/* loaded from: classes.dex */
class c implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ d f389a;

    public c(d dVar) {
        this.f389a = dVar;
    }

    @Override // java.lang.Runnable
    public void run() {
        this.f389a.a(true);
        this.f389a.invalidateSelf();
    }
}
