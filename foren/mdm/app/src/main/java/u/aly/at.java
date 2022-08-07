package u.aly;

import android.content.Context;
import android.content.SharedPreferences;

/* compiled from: StatTracer.java */
/* loaded from: classes2.dex */
public class at implements ak {
    private static final String h = "successful_request";
    private static final String i = "failed_requests ";
    private static final String j = "last_request_spent_ms";
    private static final String k = "last_request_time";
    private static final String l = "first_activate_time";
    private static final String m = "last_req";
    public int a;
    public int b;
    public long c;
    private int e;
    private Context n;
    private final int d = 3600000;
    private long f = 0;
    private long g = 0;

    public at(Context context) {
        a(context);
    }

    private void a(Context context) {
        this.n = context.getApplicationContext();
        SharedPreferences a = aq.a(context);
        this.a = a.getInt(h, 0);
        this.b = a.getInt(i, 0);
        this.e = a.getInt(j, 0);
        this.c = a.getLong(k, 0L);
        this.f = a.getLong(m, 0L);
    }

    public int e() {
        if (this.e > 3600000) {
            return 3600000;
        }
        return this.e;
    }

    public boolean f() {
        return ((this.c > 0L ? 1 : (this.c == 0L ? 0 : -1)) == 0) && (!bs.a(this.n).i());
    }

    public void g() {
        this.a++;
        this.c = this.f;
    }

    public void h() {
        this.b++;
    }

    public void i() {
        this.f = System.currentTimeMillis();
    }

    public void j() {
        this.e = (int) (System.currentTimeMillis() - this.f);
    }

    public void k() {
        aq.a(this.n).edit().putInt(h, this.a).putInt(i, this.b).putInt(j, this.e).putLong(k, this.c).putLong(m, this.f).commit();
    }

    public long l() {
        SharedPreferences a = aq.a(this.n);
        this.g = aq.a(this.n).getLong(l, 0L);
        if (this.g == 0) {
            this.g = System.currentTimeMillis();
            a.edit().putLong(l, this.g).commit();
        }
        return this.g;
    }

    public long m() {
        return this.f;
    }

    public static void a(Context context, aw awVar) {
        SharedPreferences a = aq.a(context);
        awVar.a.Q = a.getInt(i, 0);
        awVar.a.P = a.getInt(h, 0);
        awVar.a.R = a.getInt(j, 0);
    }

    @Override // u.aly.ak
    public void a() {
        i();
    }

    @Override // u.aly.ak
    public void b() {
        j();
    }

    @Override // u.aly.ak
    public void c() {
        g();
    }

    @Override // u.aly.ak
    public void d() {
        h();
    }
}
