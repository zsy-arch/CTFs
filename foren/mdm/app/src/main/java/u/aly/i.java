package u.aly;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/* compiled from: UMCCAggregatedObject.java */
/* loaded from: classes2.dex */
public class i implements Serializable {
    private static final long a = 1;
    private List<String> b;
    private List<String> c;
    private long d;
    private long e;
    private long f;
    private String g;

    public i() {
        this.b = new ArrayList();
        this.c = new ArrayList();
        this.d = 0L;
        this.e = 0L;
        this.f = 0L;
        this.g = null;
    }

    public i(List<String> list, long j, long j2, long j3, List<String> list2, String str) {
        this.b = new ArrayList();
        this.c = new ArrayList();
        this.d = 0L;
        this.e = 0L;
        this.f = 0L;
        this.g = null;
        this.b = list;
        this.c = list2;
        this.d = j;
        this.e = j2;
        this.f = j3;
        this.g = str;
    }

    public void a(String str) {
        try {
            if (this.c.size() < n.a().b()) {
                this.c.add(str);
            } else {
                this.c.remove(this.c.get(0));
                this.c.add(str);
            }
            if (this.c.size() > n.a().b()) {
                for (int i = 0; i < this.c.size() - n.a().b(); i++) {
                    this.c.remove(this.c.get(0));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void a(f fVar, l lVar) {
        a(lVar.b());
        this.f++;
        this.e += lVar.c();
        this.d += lVar.d();
        fVar.a(this, false);
    }

    public void a(l lVar) {
        this.f = 1L;
        this.b = lVar.a();
        a(lVar.b());
        this.e = lVar.c();
        this.d = System.currentTimeMillis();
        this.g = q.a(System.currentTimeMillis());
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("[key: ").append(this.b).append("] [label: ").append(this.c).append("][ totalTimeStamp").append(this.g).append("][ value").append(this.e).append("][ count").append(this.f).append("][ timeWindowNum").append(this.g).append("]");
        return stringBuffer.toString();
    }

    public String a() {
        return d.a(this.b);
    }

    public List<String> b() {
        return this.b;
    }

    public String c() {
        return d.a(this.c);
    }

    public List<String> d() {
        return this.c;
    }

    public long e() {
        return this.d;
    }

    public long f() {
        return this.e;
    }

    public long g() {
        return this.f;
    }

    public String h() {
        return this.g;
    }

    public void a(List<String> list) {
        this.b = list;
    }

    public void b(List<String> list) {
        this.c = list;
    }

    public void a(long j) {
        this.d = j;
    }

    public void b(long j) {
        this.e = j;
    }

    public void c(long j) {
        this.f = j;
    }

    public void b(String str) {
        this.g = str;
    }
}
