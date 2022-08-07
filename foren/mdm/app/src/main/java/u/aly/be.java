package u.aly;

import com.alimama.mobile.csdk.umupdate.a.f;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Collections;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/* compiled from: Imprint.java */
/* loaded from: classes2.dex */
public class be implements Serializable, Cloneable, bw<be, e> {
    public static final Map<e, ci> d;
    private static final long e = 2846460275012375038L;
    private static final da f = new da("Imprint");
    private static final cq g = new cq("property", dc.k, 1);
    private static final cq h = new cq("version", (byte) 8, 2);
    private static final cq i = new cq("checksum", (byte) 11, 3);
    private static final Map<Class<? extends dd>, de> j = new HashMap();
    private static final int k = 0;
    public Map<String, bf> a;
    public int b;
    public String c;
    private byte l;

    static {
        j.put(df.class, new b());
        j.put(dg.class, new d());
        EnumMap enumMap = new EnumMap(e.class);
        enumMap.put((EnumMap) e.PROPERTY, (e) new ci("property", (byte) 1, new cl(dc.k, new cj((byte) 11), new cn((byte) 12, bf.class))));
        enumMap.put((EnumMap) e.VERSION, (e) new ci("version", (byte) 1, new cj((byte) 8)));
        enumMap.put((EnumMap) e.CHECKSUM, (e) new ci("checksum", (byte) 1, new cj((byte) 11)));
        d = Collections.unmodifiableMap(enumMap);
        ci.a(be.class, d);
    }

    /* compiled from: Imprint.java */
    /* loaded from: classes2.dex */
    public enum e implements cd {
        PROPERTY(1, "property"),
        VERSION(2, "version"),
        CHECKSUM(3, "checksum");
        
        private static final Map<String, e> d = new HashMap();
        private final short e;
        private final String f;

        static {
            Iterator it = EnumSet.allOf(e.class).iterator();
            while (it.hasNext()) {
                e eVar = (e) it.next();
                d.put(eVar.b(), eVar);
            }
        }

        public static e a(int i) {
            switch (i) {
                case 1:
                    return PROPERTY;
                case 2:
                    return VERSION;
                case 3:
                    return CHECKSUM;
                default:
                    return null;
            }
        }

        public static e b(int i) {
            e a = a(i);
            if (a != null) {
                return a;
            }
            throw new IllegalArgumentException("Field " + i + " doesn't exist!");
        }

        public static e a(String str) {
            return d.get(str);
        }

        e(short s, String str) {
            this.e = s;
            this.f = str;
        }

        @Override // u.aly.cd
        public short a() {
            return this.e;
        }

        @Override // u.aly.cd
        public String b() {
            return this.f;
        }
    }

    public be() {
        this.l = (byte) 0;
    }

    public be(Map<String, bf> map, int i2, String str) {
        this();
        this.a = map;
        this.b = i2;
        b(true);
        this.c = str;
    }

    public be(be beVar) {
        this.l = (byte) 0;
        this.l = beVar.l;
        if (beVar.f()) {
            HashMap hashMap = new HashMap();
            for (Map.Entry<String, bf> entry : beVar.a.entrySet()) {
                hashMap.put(entry.getKey(), new bf(entry.getValue()));
            }
            this.a = hashMap;
        }
        this.b = beVar.b;
        if (beVar.l()) {
            this.c = beVar.c;
        }
    }

    /* renamed from: a */
    public be p() {
        return new be(this);
    }

    @Override // u.aly.bw
    public void b() {
        this.a = null;
        b(false);
        this.b = 0;
        this.c = null;
    }

    public int c() {
        if (this.a == null) {
            return 0;
        }
        return this.a.size();
    }

    public void a(String str, bf bfVar) {
        if (this.a == null) {
            this.a = new HashMap();
        }
        this.a.put(str, bfVar);
    }

    public Map<String, bf> d() {
        return this.a;
    }

    public be a(Map<String, bf> map) {
        this.a = map;
        return this;
    }

    public void e() {
        this.a = null;
    }

    public boolean f() {
        return this.a != null;
    }

    public void a(boolean z) {
        if (!z) {
            this.a = null;
        }
    }

    public int g() {
        return this.b;
    }

    public be a(int i2) {
        this.b = i2;
        b(true);
        return this;
    }

    public void h() {
        this.l = bt.b(this.l, 0);
    }

    public boolean i() {
        return bt.a(this.l, 0);
    }

    public void b(boolean z) {
        this.l = bt.a(this.l, 0, z);
    }

    public String j() {
        return this.c;
    }

    public be a(String str) {
        this.c = str;
        return this;
    }

    public void k() {
        this.c = null;
    }

    public boolean l() {
        return this.c != null;
    }

    public void c(boolean z) {
        if (!z) {
            this.c = null;
        }
    }

    /* renamed from: c */
    public e b(int i2) {
        return e.a(i2);
    }

    @Override // u.aly.bw
    public void a(cv cvVar) throws cc {
        j.get(cvVar.D()).b().b(cvVar, this);
    }

    @Override // u.aly.bw
    public void b(cv cvVar) throws cc {
        j.get(cvVar.D()).b().a(cvVar, this);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("Imprint(");
        sb.append("property:");
        if (this.a == null) {
            sb.append(f.b);
        } else {
            sb.append(this.a);
        }
        sb.append(", ");
        sb.append("version:");
        sb.append(this.b);
        sb.append(", ");
        sb.append("checksum:");
        if (this.c == null) {
            sb.append(f.b);
        } else {
            sb.append(this.c);
        }
        sb.append(")");
        return sb.toString();
    }

    public void m() throws cc {
        if (this.a == null) {
            throw new cw("Required field 'property' was not present! Struct: " + toString());
        } else if (this.c == null) {
            throw new cw("Required field 'checksum' was not present! Struct: " + toString());
        }
    }

    private void a(ObjectOutputStream objectOutputStream) throws IOException {
        try {
            b(new cp(new dh(objectOutputStream)));
        } catch (cc e2) {
            throw new IOException(e2.getMessage());
        }
    }

    private void a(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        try {
            this.l = (byte) 0;
            a(new cp(new dh(objectInputStream)));
        } catch (cc e2) {
            throw new IOException(e2.getMessage());
        }
    }

    /* compiled from: Imprint.java */
    /* loaded from: classes2.dex */
    private static class b implements de {
        private b() {
        }

        /* renamed from: a */
        public a b() {
            return new a();
        }
    }

    /* compiled from: Imprint.java */
    /* loaded from: classes2.dex */
    public static class a extends df<be> {
        private a() {
        }

        /* renamed from: a */
        public void b(cv cvVar, be beVar) throws cc {
            cvVar.j();
            while (true) {
                cq l = cvVar.l();
                if (l.b == 0) {
                    cvVar.k();
                    if (!beVar.i()) {
                        throw new cw("Required field 'version' was not found in serialized data! Struct: " + toString());
                    }
                    beVar.m();
                    return;
                }
                switch (l.c) {
                    case 1:
                        if (l.b == 13) {
                            cs n = cvVar.n();
                            beVar.a = new HashMap(n.c * 2);
                            for (int i = 0; i < n.c; i++) {
                                String z = cvVar.z();
                                bf bfVar = new bf();
                                bfVar.a(cvVar);
                                beVar.a.put(z, bfVar);
                            }
                            cvVar.o();
                            beVar.a(true);
                            break;
                        } else {
                            cy.a(cvVar, l.b);
                            break;
                        }
                    case 2:
                        if (l.b == 8) {
                            beVar.b = cvVar.w();
                            beVar.b(true);
                            break;
                        } else {
                            cy.a(cvVar, l.b);
                            break;
                        }
                    case 3:
                        if (l.b == 11) {
                            beVar.c = cvVar.z();
                            beVar.c(true);
                            break;
                        } else {
                            cy.a(cvVar, l.b);
                            break;
                        }
                    default:
                        cy.a(cvVar, l.b);
                        break;
                }
                cvVar.m();
            }
        }

        /* renamed from: b */
        public void a(cv cvVar, be beVar) throws cc {
            beVar.m();
            cvVar.a(be.f);
            if (beVar.a != null) {
                cvVar.a(be.g);
                cvVar.a(new cs((byte) 11, (byte) 12, beVar.a.size()));
                for (Map.Entry<String, bf> entry : beVar.a.entrySet()) {
                    cvVar.a(entry.getKey());
                    entry.getValue().b(cvVar);
                }
                cvVar.e();
                cvVar.c();
            }
            cvVar.a(be.h);
            cvVar.a(beVar.b);
            cvVar.c();
            if (beVar.c != null) {
                cvVar.a(be.i);
                cvVar.a(beVar.c);
                cvVar.c();
            }
            cvVar.d();
            cvVar.b();
        }
    }

    /* compiled from: Imprint.java */
    /* loaded from: classes2.dex */
    private static class d implements de {
        private d() {
        }

        /* renamed from: a */
        public c b() {
            return new c();
        }
    }

    /* compiled from: Imprint.java */
    /* loaded from: classes2.dex */
    public static class c extends dg<be> {
        private c() {
        }

        public void a(cv cvVar, be beVar) throws cc {
            db dbVar = (db) cvVar;
            dbVar.a(beVar.a.size());
            for (Map.Entry<String, bf> entry : beVar.a.entrySet()) {
                dbVar.a(entry.getKey());
                entry.getValue().b(dbVar);
            }
            dbVar.a(beVar.b);
            dbVar.a(beVar.c);
        }

        public void b(cv cvVar, be beVar) throws cc {
            db dbVar = (db) cvVar;
            cs csVar = new cs((byte) 11, (byte) 12, dbVar.w());
            beVar.a = new HashMap(csVar.c * 2);
            for (int i = 0; i < csVar.c; i++) {
                String z = dbVar.z();
                bf bfVar = new bf();
                bfVar.a(dbVar);
                beVar.a.put(z, bfVar);
            }
            beVar.a(true);
            beVar.b = dbVar.w();
            beVar.b(true);
            beVar.c = dbVar.z();
            beVar.c(true);
        }
    }
}
