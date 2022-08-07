package u.aly;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/* compiled from: UMCCVerbatimObject.java */
/* loaded from: classes2.dex */
public class l implements Serializable {
    private static final long a = 1;
    private List<String> b;
    private String c;
    private long d;
    private long e;
    private String f;

    public l(List<String> list, long j, String str, long j2) {
        this.b = new ArrayList();
        this.b = list;
        this.d = j;
        this.c = str;
        this.e = j2;
        f();
    }

    private void f() {
        this.f = q.a(this.e);
    }

    public List<String> a() {
        return this.b;
    }

    public String b() {
        return this.c;
    }

    public long c() {
        return this.d;
    }

    public long d() {
        return this.e;
    }

    public String e() {
        return this.f;
    }
}
