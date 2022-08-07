package c.b.a.a;

/* loaded from: classes.dex */
public class c extends f {

    /* renamed from: a */
    public static volatile c f667a;

    /* renamed from: c */
    public f f669c = new e();

    /* renamed from: b */
    public f f668b = this.f669c;

    static {
        new a();
        new b();
    }

    public static c b() {
        if (f667a != null) {
            return f667a;
        }
        synchronized (c.class) {
            if (f667a == null) {
                f667a = new c();
            }
        }
        return f667a;
    }

    @Override // c.b.a.a.f
    public void a(Runnable runnable) {
        this.f668b.a(runnable);
    }

    @Override // c.b.a.a.f
    public boolean a() {
        return this.f668b.a();
    }

    @Override // c.b.a.a.f
    public void b(Runnable runnable) {
        this.f668b.b(runnable);
    }
}
