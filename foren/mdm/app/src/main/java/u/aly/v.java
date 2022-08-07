package u.aly;

import android.content.Context;
import android.text.TextUtils;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/* compiled from: IdTracker.java */
/* loaded from: classes2.dex */
public class v {
    public static v a;
    private File c;
    private long e;
    private a h;
    private final String b = "umeng_it.cache";
    private bd d = null;
    private Set<r> g = new HashSet();
    private long f = com.umeng.analytics.a.j;

    v(Context context) {
        this.h = null;
        this.c = new File(context.getFilesDir(), "umeng_it.cache");
        this.h = new a(context);
        this.h.b();
    }

    public static synchronized v a(Context context) {
        v vVar;
        synchronized (v.class) {
            if (a == null) {
                a = new v(context);
                a.a(new w(context));
                a.a(new s(context));
                a.a(new ab(context));
                a.a(new aa(context));
                a.a(new u(context));
                a.a(new y(context));
                a.a(new z());
                a.a(new ac(context));
                a.e();
            }
            vVar = a;
        }
        return vVar;
    }

    public boolean a(r rVar) {
        if (this.h.a(rVar.b())) {
            return this.g.add(rVar);
        }
        return false;
    }

    public void a(long j) {
        this.f = j;
    }

    public void a() {
        long currentTimeMillis = System.currentTimeMillis();
        if (currentTimeMillis - this.e >= this.f) {
            boolean z = false;
            for (r rVar : this.g) {
                if (rVar.c()) {
                    if (rVar.a()) {
                        z = true;
                        if (!rVar.c()) {
                            this.h.b(rVar.b());
                        }
                    }
                }
            }
            if (z) {
                g();
                this.h.a();
                f();
            }
            this.e = currentTimeMillis;
        }
    }

    public bd b() {
        return this.d;
    }

    private void g() {
        bd bdVar = new bd();
        HashMap hashMap = new HashMap();
        ArrayList arrayList = new ArrayList();
        for (r rVar : this.g) {
            if (rVar.c()) {
                if (rVar.d() != null) {
                    hashMap.put(rVar.b(), rVar.d());
                }
                if (rVar.e() != null && !rVar.e().isEmpty()) {
                    arrayList.addAll(rVar.e());
                }
            }
        }
        bdVar.a(arrayList);
        bdVar.a(hashMap);
        synchronized (this) {
            this.d = bdVar;
        }
    }

    public String c() {
        return null;
    }

    public void d() {
        boolean z = false;
        for (r rVar : this.g) {
            if (rVar.c()) {
                if (rVar.e() != null && !rVar.e().isEmpty()) {
                    rVar.a((List<bb>) null);
                    z = true;
                }
            }
        }
        if (z) {
            this.d.b(false);
            f();
        }
    }

    public void e() {
        bd h = h();
        if (h != null) {
            ArrayList<r> arrayList = new ArrayList(this.g.size());
            synchronized (this) {
                this.d = h;
                for (r rVar : this.g) {
                    rVar.a(this.d);
                    if (!rVar.c()) {
                        arrayList.add(rVar);
                    }
                }
                for (r rVar2 : arrayList) {
                    this.g.remove(rVar2);
                }
            }
            g();
        }
    }

    public void f() {
        if (this.d != null) {
            a(this.d);
        }
    }

    private bd h() {
        FileInputStream fileInputStream;
        Exception e;
        bd bdVar = null;
        try {
            if (this.c.exists()) {
                try {
                    fileInputStream = new FileInputStream(this.c);
                    try {
                        byte[] b = bm.b(fileInputStream);
                        bd bdVar2 = new bd();
                        new bz().a(bdVar2, b);
                        bm.c(fileInputStream);
                        bdVar = bdVar2;
                    } catch (Exception e2) {
                        e = e2;
                        e.printStackTrace();
                        bm.c(fileInputStream);
                        return bdVar;
                    }
                } catch (Exception e3) {
                    e = e3;
                    fileInputStream = null;
                } catch (Throwable th) {
                    th = th;
                    fileInputStream = null;
                    bm.c(fileInputStream);
                    throw th;
                }
            }
            return bdVar;
        } catch (Throwable th2) {
            th = th2;
        }
    }

    private void a(bd bdVar) {
        byte[] a2;
        if (bdVar != null) {
            try {
                synchronized (this) {
                    a2 = new cf().a(bdVar);
                }
                if (a2 != null) {
                    bm.a(this.c, a2);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /* compiled from: IdTracker.java */
    /* loaded from: classes2.dex */
    public static class a {
        private Context a;
        private Set<String> b = new HashSet();

        public a(Context context) {
            this.a = context;
        }

        public boolean a(String str) {
            return !this.b.contains(str);
        }

        public void b(String str) {
            this.b.add(str);
        }

        public void c(String str) {
            this.b.remove(str);
        }

        public void a() {
            if (!this.b.isEmpty()) {
                StringBuilder sb = new StringBuilder();
                for (String str : this.b) {
                    sb.append(str);
                    sb.append(',');
                }
                sb.deleteCharAt(sb.length() - 1);
                aq.a(this.a).edit().putString("invld_id", sb.toString()).commit();
            }
        }

        public void b() {
            String[] split;
            String string = aq.a(this.a).getString("invld_id", null);
            if (!(TextUtils.isEmpty(string) || (split = string.split(",")) == null)) {
                for (String str : split) {
                    if (!TextUtils.isEmpty(str)) {
                        this.b.add(str);
                    }
                }
            }
        }
    }
}
