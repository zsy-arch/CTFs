package u.aly;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import u.aly.aw;

/* compiled from: UMCCAggregatedManager.java */
/* loaded from: classes2.dex */
public class m {
    private static final int i = 48;
    private static final int j = 49;
    private static Context k;
    private h a;
    private o b;
    private p c;
    private boolean d;
    private boolean e;
    private long f;
    private final String g;
    private final String h;
    private List<String> l;
    private a m;
    private final Thread n;

    /* compiled from: UMCCAggregatedManager.java */
    /* loaded from: classes2.dex */
    public static class b {
        private static final m a = new m();

        private b() {
        }
    }

    /* compiled from: UMCCAggregatedManager.java */
    /* loaded from: classes2.dex */
    public static class a extends Handler {
        private final WeakReference<m> a;

        public a(m mVar) {
            this.a = new WeakReference<>(mVar);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            if (this.a != null) {
                switch (message.what) {
                    case 48:
                        sendEmptyMessageDelayed(48, q.b(System.currentTimeMillis()));
                        m.a(m.k).n();
                        return;
                    case 49:
                        sendEmptyMessageDelayed(49, q.c(System.currentTimeMillis()));
                        m.a(m.k).m();
                        return;
                    default:
                        return;
                }
            }
        }
    }

    public boolean a() {
        return this.d;
    }

    private m() {
        this.a = null;
        this.b = null;
        this.c = null;
        this.d = false;
        this.e = false;
        this.f = 0L;
        this.g = "main_fest_mode";
        this.h = "main_fest_timestamp";
        this.l = new ArrayList();
        this.m = null;
        this.n = new Thread(new Runnable() { // from class: u.aly.m.1
            @Override // java.lang.Runnable
            public void run() {
                Looper.prepare();
                if (m.this.m == null) {
                    m.this.m = new a(m.this);
                }
                m.this.f();
            }
        });
        if (k != null) {
            if (this.a == null) {
                this.a = new h();
            }
            if (this.b == null) {
                this.b = o.a(k);
            }
            if (this.c == null) {
                this.c = new p();
            }
        }
        this.n.start();
    }

    public void f() {
        long currentTimeMillis = System.currentTimeMillis();
        this.m.sendEmptyMessageDelayed(48, q.b(currentTimeMillis));
        this.m.sendEmptyMessageDelayed(49, q.c(currentTimeMillis));
    }

    public static final m a(Context context) {
        k = context;
        return b.a;
    }

    public void a(final f fVar) {
        if (!this.d) {
            bp.b(new br() { // from class: u.aly.m.6
                @Override // u.aly.br
                public void a() {
                    try {
                        m.this.b.a(new f() { // from class: u.aly.m.6.1
                            @Override // u.aly.f, u.aly.g
                            public void a(Object obj, boolean z) {
                                if (obj instanceof Map) {
                                    m.this.a.a((Map) obj);
                                } else if ((obj instanceof String) || (obj instanceof Boolean)) {
                                }
                                m.this.d = true;
                            }
                        });
                        m.this.j();
                        m.this.o();
                        fVar.a("success", false);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }

    public void a(final f fVar, Map<List<String>, l> map) {
        l lVar = (l) map.values().toArray()[0];
        List<String> a2 = lVar.a();
        if (this.l.size() > 0 && this.l.contains(d.a(a2))) {
            this.a.a(new f() { // from class: u.aly.m.7
                @Override // u.aly.f, u.aly.g
                public void a(Object obj, boolean z) {
                    if (obj instanceof h) {
                        m.this.a = (h) obj;
                    }
                    fVar.a("success", false);
                }
            }, lVar);
        } else if (this.e) {
            a(lVar, a2);
        } else if (g()) {
            String a3 = d.a(a2);
            if (!this.l.contains(a3)) {
                this.l.add(a3);
            }
            this.a.a(new f() { // from class: u.aly.m.8
                @Override // u.aly.f, u.aly.g
                public void a(Object obj, boolean z) {
                    m.this.a = (h) obj;
                }
            }, a2, lVar);
        } else {
            a(lVar, a2);
            h();
        }
    }

    private void a(l lVar, List<String> list) {
        this.a.a(new f() { // from class: u.aly.m.9
            @Override // u.aly.f, u.aly.g
            public void a(Object obj, boolean z) {
                if (obj instanceof h) {
                    m.this.a = (h) obj;
                } else if (obj instanceof Boolean) {
                    m.this.l();
                }
            }
        }, lVar, list, this.l);
    }

    private boolean g() {
        return this.l.size() < n.a().d();
    }

    private void h() {
        SharedPreferences a2 = aq.a(k);
        if (!a2.getBoolean("main_fest_mode", false)) {
            this.e = true;
            SharedPreferences.Editor edit = a2.edit();
            edit.putBoolean("main_fest_mode", true);
            edit.putLong("main_fest_timestamp", System.currentTimeMillis());
            edit.commit();
        }
    }

    private void i() {
        SharedPreferences.Editor edit = aq.a(k).edit();
        edit.putBoolean("main_fest_mode", false);
        edit.putLong("main_fest_timestamp", 0L);
        edit.commit();
        this.e = false;
    }

    public void j() {
        SharedPreferences a2 = aq.a(k);
        this.e = a2.getBoolean("main_fest_mode", false);
        this.f = a2.getLong("main_fest_timestamp", 0L);
    }

    public void a(aw awVar) {
        if (awVar.b.h != null) {
            awVar.b.h.a = b(new f());
            awVar.b.h.b = c(new f());
        }
    }

    public Map<String, List<aw.e>> b(f fVar) {
        Map<String, List<aw.e>> a2 = this.b.a();
        HashMap hashMap = new HashMap();
        if (a2 == null || a2.size() <= 0) {
            return null;
        }
        for (String str : this.l) {
            if (a2.containsKey(str)) {
                hashMap.put(str, a2.get(str));
            }
        }
        return hashMap;
    }

    public Map<String, List<aw.f>> c(f fVar) {
        if (this.c.a().size() > 0) {
            this.b.b(new f() { // from class: u.aly.m.10
                @Override // u.aly.f, u.aly.g
                public void a(Object obj, boolean z) {
                    if (obj instanceof String) {
                        m.this.c.b();
                    }
                }
            }, this.c.a());
        }
        return this.b.b(new f());
    }

    public void d(f fVar) {
        boolean z = false;
        if (this.e) {
            if (this.f == 0) {
                j();
            }
            z = q.a(System.currentTimeMillis(), this.f);
        }
        if (!z) {
            i();
            this.l.clear();
        }
        this.c.b();
        this.b.a(new f() { // from class: u.aly.m.11
            @Override // u.aly.f, u.aly.g
            public void a(Object obj, boolean z2) {
                if (obj.equals("success")) {
                    m.this.k();
                }
            }
        }, z);
    }

    public void k() {
        for (Map.Entry<List<String>, i> entry : this.a.a().entrySet()) {
            List<String> key = entry.getKey();
            if (!this.l.contains(key)) {
                this.l.add(d.a(key));
            }
        }
        if (this.l.size() > 0) {
            this.b.a(new f(), this.l);
        }
    }

    public void l() {
        this.c.a(new f() { // from class: u.aly.m.12
            @Override // u.aly.f, u.aly.g
            public void a(Object obj, boolean z) {
                m.this.c = (p) obj;
            }
        }, com.umeng.analytics.a.r);
    }

    public void a(long j2, long j3, String str) {
        this.b.a(new f() { // from class: u.aly.m.13
            @Override // u.aly.f, u.aly.g
            public void a(Object obj, boolean z) {
                if (obj.equals("success")) {
                }
            }
        }, str, j2, j3);
    }

    public void m() {
        try {
            if (this.a.a().size() > 0) {
                this.b.c(new f() { // from class: u.aly.m.2
                    @Override // u.aly.f, u.aly.g
                    public void a(Object obj, boolean z) {
                        if (obj instanceof String) {
                            m.this.a.d();
                        }
                    }
                }, this.a.a());
            }
            if (this.c.a().size() > 0) {
                this.b.b(new f() { // from class: u.aly.m.3
                    @Override // u.aly.f, u.aly.g
                    public void a(Object obj, boolean z) {
                        if (obj instanceof String) {
                            m.this.c.b();
                        }
                    }
                }, this.c.a());
            }
            if (this.l.size() > 0) {
                this.b.a(new f(), this.l);
            }
        } catch (Throwable th) {
            bo.b("converyMemoryToDataTable happen error: " + th.toString());
        }
    }

    public void n() {
        try {
            if (this.a.a().size() > 0) {
                this.b.a(new f() { // from class: u.aly.m.4
                    @Override // u.aly.f, u.aly.g
                    public void a(Object obj, boolean z) {
                    }
                }, this.a.a());
            }
            if (this.c.a().size() > 0) {
                this.b.b(new f() { // from class: u.aly.m.5
                    @Override // u.aly.f, u.aly.g
                    public void a(Object obj, boolean z) {
                        if (obj instanceof String) {
                            m.this.c.b();
                        }
                    }
                }, this.c.a());
            }
            if (this.l.size() > 0) {
                this.b.a(new f(), this.l);
            }
        } catch (Throwable th) {
            bo.b("convertMemoryToCacheTable happen error: " + th.toString());
        }
    }

    public void o() {
        List<String> b2 = this.b.b();
        if (b2 != null) {
            this.l = b2;
        }
    }

    public void b() {
        n();
    }

    public void c() {
        n();
    }

    public void d() {
        n();
    }
}
