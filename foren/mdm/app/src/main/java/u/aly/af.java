package u.aly;

import android.content.Context;

/* compiled from: CacheService.java */
/* loaded from: classes2.dex */
public final class af implements ai {
    private static af c;
    private ai a;
    private Context b;

    private af(Context context) {
        this.b = context;
        this.a = new ae(this.b);
    }

    public synchronized ae a(Context context) {
        return (ae) this.a;
    }

    public static synchronized af b(Context context) {
        af afVar;
        synchronized (af.class) {
            if (c == null && context != null) {
                c = new af(context);
            }
            afVar = c;
        }
        return afVar;
    }

    public void a(ai aiVar) {
        this.a = aiVar;
    }

    @Override // u.aly.ai
    public void a(final aj ajVar) {
        bp.b(new br() { // from class: u.aly.af.1
            @Override // u.aly.br
            public void a() {
                af.this.a.a(ajVar);
            }
        });
    }

    @Override // u.aly.ai
    public void b(aj ajVar) {
        this.a.b(ajVar);
    }

    @Override // u.aly.ai
    public void a() {
        bp.b(new br() { // from class: u.aly.af.2
            @Override // u.aly.br
            public void a() {
                af.this.a.a();
            }
        });
    }

    @Override // u.aly.ai
    public void b() {
        bp.b(new br() { // from class: u.aly.af.3
            @Override // u.aly.br
            public void a() {
                af.this.a.b();
            }
        });
    }

    @Override // u.aly.ai
    public void c() {
        bp.c(new br() { // from class: u.aly.af.4
            @Override // u.aly.br
            public void a() {
                af.this.a.c();
            }
        });
    }
}
