package u.aly;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.util.Base64;
import com.umeng.analytics.AnalyticsConfig;
import java.util.List;
import u.aly.aw;
import u.aly.bq;
import u.aly.x;

/* compiled from: CacheImpl.java */
/* loaded from: classes2.dex */
public final class ae implements ai, ap {
    private static Context p;
    String a;
    private al d;
    private bs e;
    private at f;
    private ay g;
    private ax h;
    private az i;
    private a j;
    private x.a k;
    private long m;
    private int n;
    private int o;
    private final long b = 28800000;
    private final int c = 5000;
    private int l = 10;

    public ae(Context context) {
        this.d = null;
        this.e = null;
        this.f = null;
        this.g = null;
        this.h = null;
        this.i = null;
        this.j = null;
        this.k = null;
        this.m = 0L;
        this.n = 0;
        this.o = 0;
        this.a = null;
        p = context;
        this.d = new al(context);
        this.f = new at(context);
        this.e = bs.a(context);
        this.k = x.a(context).b();
        this.j = new a();
        this.h = ax.a(p);
        this.g = ay.a(p);
        this.i = az.a(p, this.f);
        SharedPreferences a2 = aq.a(p);
        this.m = a2.getLong("thtstart", 0L);
        this.n = a2.getInt("gkvc", 0);
        this.o = a2.getInt("ekvc", 0);
        this.a = x.a(p).b().b((String) null);
    }

    @Override // u.aly.ai
    public void a() {
        if (bl.l(p)) {
            f();
        } else {
            bo.b("network is unavailable");
        }
    }

    @Override // u.aly.ai
    public void a(aj ajVar) {
        if (ajVar != null) {
            this.d.a(ajVar);
        }
        a(ajVar instanceof aw.o);
    }

    @Override // u.aly.ai
    public void b(aj ajVar) {
        this.d.a(ajVar);
    }

    @Override // u.aly.ai
    public void b() {
        if (this.d.b() > 0) {
            try {
                this.e.a(a(new int[0]));
            } catch (Throwable th) {
                bo.e(th);
                if (th instanceof OutOfMemoryError) {
                    this.e.h();
                }
                if (th != null) {
                    th.printStackTrace();
                }
            }
        }
        aq.a(p).edit().putLong("thtstart", this.m).putInt("gkvc", this.n).putInt("ekvc", this.o).commit();
    }

    @Override // u.aly.ai
    public void c() {
        a(a(new int[0]));
    }

    private void a(boolean z) {
        boolean f = this.f.f();
        if (f) {
            aw.c = this.f.l();
        }
        if (b(z)) {
            f();
        } else if (f || e()) {
            b();
        }
    }

    private void a(int i) {
        a(a(i, (int) (System.currentTimeMillis() - this.f.m())));
        bp.a(new br() { // from class: u.aly.ae.1
            @Override // u.aly.br
            public void a() {
                ae.this.a();
            }
        }, i);
    }

    private void a(aw awVar) {
        t a2;
        if (awVar != null) {
            try {
                v a3 = v.a(p);
                a3.a();
                try {
                    byte[] a4 = new cf().a(a3.b());
                    awVar.a.T = Base64.encodeToString(a4, 0);
                } catch (Exception e) {
                }
                byte[] b = bs.a(p).b(c(awVar));
                if (b != null && !bj.a(p, b)) {
                    if (g()) {
                        a2 = t.b(p, AnalyticsConfig.getAppkey(p), b);
                    } else {
                        a2 = t.a(p, AnalyticsConfig.getAppkey(p), b);
                    }
                    byte[] c = a2.c();
                    bs a5 = bs.a(p);
                    a5.h();
                    a5.a(c);
                    a3.d();
                    aw.c = 0L;
                }
            } catch (Exception e2) {
            }
        }
    }

    protected aw a(int... iArr) {
        boolean z = false;
        z = true;
        try {
            if (TextUtils.isEmpty(AnalyticsConfig.getAppkey(p))) {
                bo.e("Appkey is missing ,Please check AndroidManifest.xml");
                return null;
            }
            aw g = bs.a(p).g();
            if (g == null && this.d.b() == 0) {
                return null;
            }
            if (g == null) {
                g = new aw();
            }
            this.d.a(g);
            if (g.b.c != null && bo.a && g.b.c.size() > 0) {
                for (aw.o oVar : g.b.c) {
                    if (oVar.g.size() <= 0) {
                        z = z;
                    }
                }
                if (!z) {
                    bo.d("missing Activities or PageViews");
                }
            }
            this.g.a(g, p);
            if (iArr != null && iArr.length == 2) {
                g.b.e.a = Integer.valueOf(iArr[0] / 1000);
                g.b.e.b = iArr[1];
                g.b.e.c = true;
            }
            return g;
        } catch (Exception e) {
            bo.e("Fail to construct message ...", e);
            bs.a(p).h();
            bo.e(e);
            return null;
        }
    }

    private boolean b(boolean z) {
        if (!bl.l(p)) {
            bo.b("network is unavailable");
            return false;
        } else if (this.f.f()) {
            return true;
        } else {
            return this.j.b(z).a(z);
        }
    }

    private boolean e() {
        return this.d.b() > this.l;
    }

    private void f() {
        try {
            if (this.e.i()) {
                ar arVar = new ar(p, this.f);
                arVar.a(this);
                if (this.g.c()) {
                    arVar.b(true);
                }
                arVar.a();
                return;
            }
            aw a2 = a(new int[0]);
            if (b(a2)) {
                ar arVar2 = new ar(p, this.f);
                arVar2.a(this);
                if (this.g.c()) {
                    arVar2.b(true);
                }
                arVar2.a(c(a2));
                arVar2.a(g());
                arVar2.a();
            }
        } catch (Throwable th) {
            if (th instanceof OutOfMemoryError) {
            }
            if (th != null) {
                th.printStackTrace();
            }
        }
    }

    private boolean b(aw awVar) {
        if (awVar != null && awVar.a()) {
            return true;
        }
        return false;
    }

    private aw c(aw awVar) {
        int i;
        int i2 = 5000;
        if (awVar.b.a != null) {
            i = 0;
            for (int i3 = 0; i3 < awVar.b.a.size(); i3++) {
                i += awVar.b.a.get(i3).b.size();
            }
        } else {
            i = 0;
        }
        if (awVar.b.b != null) {
            for (int i4 = 0; i4 < awVar.b.b.size(); i4++) {
                i += awVar.b.b.get(i4).b.size();
            }
        }
        long currentTimeMillis = System.currentTimeMillis();
        if (currentTimeMillis - this.m > 28800000) {
            int i5 = i - 5000;
            if (i5 > 0) {
                a(-5000, i5, awVar);
            }
            this.n = 0;
            if (i5 > 0) {
                i = 5000;
            }
            this.o = i;
            this.m = currentTimeMillis;
        } else {
            int i6 = this.n > 5000 ? 0 : (this.n + 0) - 5000;
            int i7 = this.o > 5000 ? i : (this.o + i) - 5000;
            if (i6 > 0 || i7 > 0) {
                a(i6, i7, awVar);
            }
            this.n = i6 > 0 ? 5000 : this.n + 0;
            if (i7 <= 0) {
                i2 = this.o + i;
            }
            this.o = i2;
        }
        return awVar;
    }

    private void a(int i, int i2, aw awVar) {
        if (i > 0) {
            List<aw.h> list = awVar.b.b;
            if (list.size() >= i) {
                int size = list.size() - i;
                for (int size2 = list.size() - 1; size2 >= size; size2--) {
                    list.remove(size2);
                }
            } else {
                int size3 = i - list.size();
                list.clear();
            }
        }
        if (i2 > 0) {
            List<aw.h> list2 = awVar.b.a;
            if (list2.size() >= i2) {
                int size4 = list2.size() - i2;
                for (int size5 = list2.size() - 1; size5 >= size4; size5--) {
                    list2.remove(size5);
                }
                return;
            }
            int size6 = i2 - list2.size();
            list2.clear();
        }
    }

    private boolean g() {
        switch (this.k.c(-1)) {
            case -1:
                return AnalyticsConfig.sEncrypt;
            case 0:
            default:
                return false;
            case 1:
                return true;
        }
    }

    public void b(int i) {
        a(i);
    }

    @Override // u.aly.ap
    public void a(x.a aVar) {
        this.h.a(aVar);
        this.g.a(aVar);
        this.i.a(aVar);
        this.j.a(aVar);
        this.a = x.a(p).b().b((String) null);
    }

    /* compiled from: CacheImpl.java */
    /* loaded from: classes2.dex */
    public class a {
        private bq.i b;
        private int c;
        private int d;
        private int e = -1;
        private int f = -1;

        public a() {
            ae.this = r3;
            this.c = -1;
            this.d = -1;
            int[] a = r3.k.a(-1, -1);
            this.c = a[0];
            this.d = a[1];
        }

        protected void a(boolean z) {
            boolean z2 = true;
            int i = 0;
            if (ae.this.g.c()) {
                if (!(this.b instanceof bq.b) || !this.b.a()) {
                    z2 = false;
                }
                this.b = z2 ? this.b : new bq.b(ae.this.f, ae.this.g);
                return;
            }
            if (!(this.b instanceof bq.c) || !this.b.a()) {
                z2 = false;
            }
            if (z2) {
                return;
            }
            if (z && ae.this.i.a()) {
                this.b = new bq.c((int) ae.this.i.b());
                ae.this.b((int) ae.this.i.b());
            } else if (bo.a && ae.this.k.b()) {
                bo.b("Debug: send log every 15 seconds");
                this.b = new bq.a(ae.this.f);
            } else if (ae.this.h.a()) {
                bo.b("Start A/B Test");
                if (ae.this.h.b() == 6) {
                    if (ae.this.k.a()) {
                        i = ae.this.k.d(90000);
                    } else if (this.d > 0) {
                        i = this.d;
                    } else {
                        i = this.f;
                    }
                }
                this.b = b(ae.this.h.b(), i);
            } else {
                int i2 = this.e;
                int i3 = this.f;
                if (this.c != -1) {
                    i2 = this.c;
                    i3 = this.d;
                }
                this.b = b(i2, i3);
            }
        }

        public bq.i b(boolean z) {
            a(z);
            return this.b;
        }

        private bq.i b(int i, int i2) {
            switch (i) {
                case 0:
                    return this.b instanceof bq.h ? this.b : new bq.h();
                case 1:
                    return this.b instanceof bq.d ? this.b : new bq.d();
                case 2:
                case 3:
                case 7:
                default:
                    return this.b instanceof bq.d ? this.b : new bq.d();
                case 4:
                    return this.b instanceof bq.g ? this.b : new bq.g(ae.this.f);
                case 5:
                    return this.b instanceof bq.j ? this.b : new bq.j(ae.p);
                case 6:
                    if (!(this.b instanceof bq.e)) {
                        return new bq.e(ae.this.f, i2);
                    }
                    bq.i iVar = this.b;
                    ((bq.e) iVar).a(i2);
                    return iVar;
                case 8:
                    return this.b instanceof bq.k ? this.b : new bq.k(ae.this.f);
            }
        }

        public void a(int i, int i2) {
            this.e = i;
            this.f = i2;
        }

        public void a(x.a aVar) {
            int[] a = aVar.a(-1, -1);
            this.c = a[0];
            this.d = a[1];
        }
    }
}
