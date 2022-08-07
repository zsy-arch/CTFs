package c.e.e;

/* loaded from: classes.dex */
class h implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ Object f814a;

    /* renamed from: b  reason: collision with root package name */
    public final /* synthetic */ i f815b;

    public h(i iVar, Object obj) {
        this.f815b = iVar;
        this.f814a = obj;
    }

    @Override // java.lang.Runnable
    public void run() {
        this.f815b.f818c.a(this.f814a);
    }
}
