package u.aly;

import com.alimama.mobile.csdk.umupdate.a.f;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.BitSet;
import java.util.Collections;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/* compiled from: IdJournal.java */
/* loaded from: classes2.dex */
public class bb implements Serializable, Cloneable, bw<bb, e> {
    public static final Map<e, ci> e;
    private static final long f = 9132678615281394583L;
    private static final da g = new da("IdJournal");
    private static final cq h = new cq("domain", (byte) 11, 1);
    private static final cq i = new cq("old_id", (byte) 11, 2);
    private static final cq j = new cq("new_id", (byte) 11, 3);
    private static final cq k = new cq("ts", (byte) 10, 4);
    private static final Map<Class<? extends dd>, de> l = new HashMap();
    private static final int m = 0;
    public String a;
    public String b;
    public String c;
    public long d;
    private byte n;
    private e[] o;

    static {
        l.put(df.class, new b());
        l.put(dg.class, new d());
        EnumMap enumMap = new EnumMap(e.class);
        enumMap.put((EnumMap) e.DOMAIN, (e) new ci("domain", (byte) 1, new cj((byte) 11)));
        enumMap.put((EnumMap) e.OLD_ID, (e) new ci("old_id", (byte) 2, new cj((byte) 11)));
        enumMap.put((EnumMap) e.NEW_ID, (e) new ci("new_id", (byte) 1, new cj((byte) 11)));
        enumMap.put((EnumMap) e.TS, (e) new ci("ts", (byte) 1, new cj((byte) 10)));
        e = Collections.unmodifiableMap(enumMap);
        ci.a(bb.class, e);
    }

    /* compiled from: IdJournal.java */
    /* loaded from: classes2.dex */
    public enum e implements cd {
        DOMAIN(1, "domain"),
        OLD_ID(2, "old_id"),
        NEW_ID(3, "new_id"),
        TS(4, "ts");
        
        private static final Map<String, e> e = new HashMap();
        private final short f;
        private final String g;

        static {
            Iterator it = EnumSet.allOf(e.class).iterator();
            while (it.hasNext()) {
                e eVar = (e) it.next();
                e.put(eVar.b(), eVar);
            }
        }

        public static e a(int i) {
            switch (i) {
                case 1:
                    return DOMAIN;
                case 2:
                    return OLD_ID;
                case 3:
                    return NEW_ID;
                case 4:
                    return TS;
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
            return e.get(str);
        }

        e(short s, String str) {
            this.f = s;
            this.g = str;
        }

        @Override // u.aly.cd
        public short a() {
            return this.f;
        }

        @Override // u.aly.cd
        public String b() {
            return this.g;
        }
    }

    public bb() {
        this.n = (byte) 0;
        this.o = new e[]{e.OLD_ID};
    }

    public bb(String str, String str2, long j2) {
        this();
        this.a = str;
        this.c = str2;
        this.d = j2;
        d(true);
    }

    public bb(bb bbVar) {
        this.n = (byte) 0;
        this.o = new e[]{e.OLD_ID};
        this.n = bbVar.n;
        if (bbVar.e()) {
            this.a = bbVar.a;
        }
        if (bbVar.h()) {
            this.b = bbVar.b;
        }
        if (bbVar.k()) {
            this.c = bbVar.c;
        }
        this.d = bbVar.d;
    }

    /* renamed from: a */
    public bb p() {
        return new bb(this);
    }

    @Override // u.aly.bw
    public void b() {
        this.a = null;
        this.b = null;
        this.c = null;
        d(false);
        this.d = 0L;
    }

    public String c() {
        return this.a;
    }

    public bb a(String str) {
        this.a = str;
        return this;
    }

    public void d() {
        this.a = null;
    }

    public boolean e() {
        return this.a != null;
    }

    public void a(boolean z) {
        if (!z) {
            this.a = null;
        }
    }

    public String f() {
        return this.b;
    }

    public bb b(String str) {
        this.b = str;
        return this;
    }

    public void g() {
        this.b = null;
    }

    public boolean h() {
        return this.b != null;
    }

    public void b(boolean z) {
        if (!z) {
            this.b = null;
        }
    }

    public String i() {
        return this.c;
    }

    public bb c(String str) {
        this.c = str;
        return this;
    }

    public void j() {
        this.c = null;
    }

    public boolean k() {
        return this.c != null;
    }

    public void c(boolean z) {
        if (!z) {
            this.c = null;
        }
    }

    public long l() {
        return this.d;
    }

    public bb a(long j2) {
        this.d = j2;
        d(true);
        return this;
    }

    public void m() {
        this.n = bt.b(this.n, 0);
    }

    public boolean n() {
        return bt.a(this.n, 0);
    }

    public void d(boolean z) {
        this.n = bt.a(this.n, 0, z);
    }

    /* renamed from: a */
    public e b(int i2) {
        return e.a(i2);
    }

    @Override // u.aly.bw
    public void a(cv cvVar) throws cc {
        l.get(cvVar.D()).b().b(cvVar, this);
    }

    @Override // u.aly.bw
    public void b(cv cvVar) throws cc {
        l.get(cvVar.D()).b().a(cvVar, this);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("IdJournal(");
        sb.append("domain:");
        if (this.a == null) {
            sb.append(f.b);
        } else {
            sb.append(this.a);
        }
        if (h()) {
            sb.append(", ");
            sb.append("old_id:");
            if (this.b == null) {
                sb.append(f.b);
            } else {
                sb.append(this.b);
            }
        }
        sb.append(", ");
        sb.append("new_id:");
        if (this.c == null) {
            sb.append(f.b);
        } else {
            sb.append(this.c);
        }
        sb.append(", ");
        sb.append("ts:");
        sb.append(this.d);
        sb.append(")");
        return sb.toString();
    }

    public void o() throws cc {
        if (this.a == null) {
            throw new cw("Required field 'domain' was not present! Struct: " + toString());
        } else if (this.c == null) {
            throw new cw("Required field 'new_id' was not present! Struct: " + toString());
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
            this.n = (byte) 0;
            a(new cp(new dh(objectInputStream)));
        } catch (cc e2) {
            throw new IOException(e2.getMessage());
        }
    }

    /* compiled from: IdJournal.java */
    /* loaded from: classes2.dex */
    private static class b implements de {
        private b() {
        }

        /* renamed from: a */
        public a b() {
            return new a();
        }
    }

    /* compiled from: IdJournal.java */
    /* loaded from: classes2.dex */
    public static class a extends df<bb> {
        private a() {
        }

        /* renamed from: a */
        public void b(cv cvVar, bb bbVar) throws cc {
            cvVar.j();
            while (true) {
                cq l = cvVar.l();
                if (l.b == 0) {
                    cvVar.k();
                    if (!bbVar.n()) {
                        throw new cw("Required field 'ts' was not found in serialized data! Struct: " + toString());
                    }
                    bbVar.o();
                    return;
                }
                switch (l.c) {
                    case 1:
                        if (l.b != 11) {
                            cy.a(cvVar, l.b);
                            break;
                        } else {
                            bbVar.a = cvVar.z();
                            bbVar.a(true);
                            break;
                        }
                    case 2:
                        if (l.b != 11) {
                            cy.a(cvVar, l.b);
                            break;
                        } else {
                            bbVar.b = cvVar.z();
                            bbVar.b(true);
                            break;
                        }
                    case 3:
                        if (l.b != 11) {
                            cy.a(cvVar, l.b);
                            break;
                        } else {
                            bbVar.c = cvVar.z();
                            bbVar.c(true);
                            break;
                        }
                    case 4:
                        if (l.b != 10) {
                            cy.a(cvVar, l.b);
                            break;
                        } else {
                            bbVar.d = cvVar.x();
                            bbVar.d(true);
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
        public void a(cv cvVar, bb bbVar) throws cc {
            bbVar.o();
            cvVar.a(bb.g);
            if (bbVar.a != null) {
                cvVar.a(bb.h);
                cvVar.a(bbVar.a);
                cvVar.c();
            }
            if (bbVar.b != null && bbVar.h()) {
                cvVar.a(bb.i);
                cvVar.a(bbVar.b);
                cvVar.c();
            }
            if (bbVar.c != null) {
                cvVar.a(bb.j);
                cvVar.a(bbVar.c);
                cvVar.c();
            }
            cvVar.a(bb.k);
            cvVar.a(bbVar.d);
            cvVar.c();
            cvVar.d();
            cvVar.b();
        }
    }

    /* compiled from: IdJournal.java */
    /* loaded from: classes2.dex */
    private static class d implements de {
        private d() {
        }

        /* renamed from: a */
        public c b() {
            return new c();
        }
    }

    /* compiled from: IdJournal.java */
    /* loaded from: classes2.dex */
    public static class c extends dg<bb> {
        private c() {
        }

        public void a(cv cvVar, bb bbVar) throws cc {
            db dbVar = (db) cvVar;
            dbVar.a(bbVar.a);
            dbVar.a(bbVar.c);
            dbVar.a(bbVar.d);
            BitSet bitSet = new BitSet();
            if (bbVar.h()) {
                bitSet.set(0);
            }
            dbVar.a(bitSet, 1);
            if (bbVar.h()) {
                dbVar.a(bbVar.b);
            }
        }

        public void b(cv cvVar, bb bbVar) throws cc {
            db dbVar = (db) cvVar;
            bbVar.a = dbVar.z();
            bbVar.a(true);
            bbVar.c = dbVar.z();
            bbVar.c(true);
            bbVar.d = dbVar.x();
            bbVar.d(true);
            if (dbVar.b(1).get(0)) {
                bbVar.b = dbVar.z();
                bbVar.b(true);
            }
        }
    }
}
