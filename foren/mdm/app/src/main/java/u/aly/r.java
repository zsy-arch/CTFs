package u.aly;

import android.support.v4.os.EnvironmentCompat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/* compiled from: AbstractIdTracker.java */
/* loaded from: classes2.dex */
public abstract class r {
    private final int a = 10;
    private final int b = 20;
    private final String c;
    private List<bb> d;
    private bc e;

    public abstract String f();

    public r(String str) {
        this.c = str;
    }

    public boolean a() {
        return g();
    }

    public String b() {
        return this.c;
    }

    public boolean c() {
        return this.e == null || this.e.i() <= 20;
    }

    private boolean g() {
        int i;
        bc bcVar = this.e;
        String c = bcVar == null ? null : bcVar.c();
        if (bcVar == null) {
            i = 0;
        } else {
            i = bcVar.i();
        }
        String a = a(f());
        if (a == null || a.equals(c)) {
            return false;
        }
        if (bcVar == null) {
            bcVar = new bc();
        }
        bcVar.a(a);
        bcVar.a(System.currentTimeMillis());
        bcVar.a(i + 1);
        bb bbVar = new bb();
        bbVar.a(this.c);
        bbVar.c(a);
        bbVar.b(c);
        bbVar.a(bcVar.f());
        if (this.d == null) {
            this.d = new ArrayList(2);
        }
        this.d.add(bbVar);
        if (this.d.size() > 10) {
            this.d.remove(0);
        }
        this.e = bcVar;
        return true;
    }

    public bc d() {
        return this.e;
    }

    public void a(bc bcVar) {
        this.e = bcVar;
    }

    public List<bb> e() {
        return this.d;
    }

    public void a(List<bb> list) {
        this.d = list;
    }

    public String a(String str) {
        if (str == null) {
            return null;
        }
        String trim = str.trim();
        if (trim.length() == 0 || "0".equals(trim) || EnvironmentCompat.MEDIA_UNKNOWN.equals(trim.toLowerCase(Locale.US))) {
            return null;
        }
        return trim;
    }

    public void a(bd bdVar) {
        this.e = bdVar.d().get(this.c);
        List<bb> i = bdVar.i();
        if (i != null && i.size() > 0) {
            if (this.d == null) {
                this.d = new ArrayList();
            }
            for (bb bbVar : i) {
                if (this.c.equals(bbVar.a)) {
                    this.d.add(bbVar);
                }
            }
        }
    }
}
