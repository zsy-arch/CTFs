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
import org.apache.http.protocol.HTTP;

/* compiled from: IdSnapshot.java */
/* loaded from: classes2.dex */
public class bc implements Serializable, Cloneable, bw<bc, e> {
    public static final Map<e, ci> d;
    private static final long e = -6496538196005191531L;
    private static final da f = new da("IdSnapshot");
    private static final cq g = new cq(HTTP.IDENTITY_CODING, (byte) 11, 1);
    private static final cq h = new cq("ts", (byte) 10, 2);
    private static final cq i = new cq("version", (byte) 8, 3);
    private static final Map<Class<? extends dd>, de> j = new HashMap();
    private static final int k = 0;
    private static final int l = 1;
    public String a;
    public long b;
    public int c;
    private byte m;

    static {
        j.put(df.class, new b());
        j.put(dg.class, new d());
        EnumMap enumMap = new EnumMap(e.class);
        enumMap.put((EnumMap) e.IDENTITY, (e) new ci(HTTP.IDENTITY_CODING, (byte) 1, new cj((byte) 11)));
        enumMap.put((EnumMap) e.TS, (e) new ci("ts", (byte) 1, new cj((byte) 10)));
        enumMap.put((EnumMap) e.VERSION, (e) new ci("version", (byte) 1, new cj((byte) 8)));
        d = Collections.unmodifiableMap(enumMap);
        ci.a(bc.class, d);
    }

    /* compiled from: IdSnapshot.java */
    /* loaded from: classes2.dex */
    public enum e implements cd {
        IDENTITY(1, HTTP.IDENTITY_CODING),
        TS(2, "ts"),
        VERSION(3, "version");
        
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
                    return IDENTITY;
                case 2:
                    return TS;
                case 3:
                    return VERSION;
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

    public bc() {
        this.m = (byte) 0;
    }

    public bc(String str, long j2, int i2) {
        this();
        this.a = str;
        this.b = j2;
        b(true);
        this.c = i2;
        c(true);
    }

    public bc(bc bcVar) {
        this.m = (byte) 0;
        this.m = bcVar.m;
        if (bcVar.e()) {
            this.a = bcVar.a;
        }
        this.b = bcVar.b;
        this.c = bcVar.c;
    }

    /* renamed from: a */
    public bc p() {
        return new bc(this);
    }

    @Override // u.aly.bw
    public void b() {
        this.a = null;
        b(false);
        this.b = 0L;
        c(false);
        this.c = 0;
    }

    public String c() {
        return this.a;
    }

    public bc a(String str) {
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

    public long f() {
        return this.b;
    }

    public bc a(long j2) {
        this.b = j2;
        b(true);
        return this;
    }

    public void g() {
        this.m = bt.b(this.m, 0);
    }

    public boolean h() {
        return bt.a(this.m, 0);
    }

    public void b(boolean z) {
        this.m = bt.a(this.m, 0, z);
    }

    public int i() {
        return this.c;
    }

    public bc a(int i2) {
        this.c = i2;
        c(true);
        return this;
    }

    public void j() {
        this.m = bt.b(this.m, 1);
    }

    public boolean k() {
        return bt.a(this.m, 1);
    }

    public void c(boolean z) {
        this.m = bt.a(this.m, 1, z);
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
        StringBuilder sb = new StringBuilder("IdSnapshot(");
        sb.append("identity:");
        if (this.a == null) {
            sb.append(f.b);
        } else {
            sb.append(this.a);
        }
        sb.append(", ");
        sb.append("ts:");
        sb.append(this.b);
        sb.append(", ");
        sb.append("version:");
        sb.append(this.c);
        sb.append(")");
        return sb.toString();
    }

    public void l() throws cc {
        if (this.a == null) {
            throw new cw("Required field 'identity' was not present! Struct: " + toString());
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
            this.m = (byte) 0;
            a(new cp(new dh(objectInputStream)));
        } catch (cc e2) {
            throw new IOException(e2.getMessage());
        }
    }

    /* compiled from: IdSnapshot.java */
    /* loaded from: classes2.dex */
    private static class b implements de {
        private b() {
        }

        /* renamed from: a */
        public a b() {
            return new a();
        }
    }

    /* compiled from: IdSnapshot.java */
    /* loaded from: classes2.dex */
    public static class a extends df<bc> {
        private a() {
        }

        /* renamed from: a */
        public void b(cv cvVar, bc bcVar) throws cc {
            cvVar.j();
            while (true) {
                cq l = cvVar.l();
                if (l.b == 0) {
                    cvVar.k();
                    if (!bcVar.h()) {
                        throw new cw("Required field 'ts' was not found in serialized data! Struct: " + toString());
                    } else if (!bcVar.k()) {
                        throw new cw("Required field 'version' was not found in serialized data! Struct: " + toString());
                    } else {
                        bcVar.l();
                        return;
                    }
                } else {
                    switch (l.c) {
                        case 1:
                            if (l.b != 11) {
                                cy.a(cvVar, l.b);
                                break;
                            } else {
                                bcVar.a = cvVar.z();
                                bcVar.a(true);
                                break;
                            }
                        case 2:
                            if (l.b != 10) {
                                cy.a(cvVar, l.b);
                                break;
                            } else {
                                bcVar.b = cvVar.x();
                                bcVar.b(true);
                                break;
                            }
                        case 3:
                            if (l.b != 8) {
                                cy.a(cvVar, l.b);
                                break;
                            } else {
                                bcVar.c = cvVar.w();
                                bcVar.c(true);
                                break;
                            }
                        default:
                            cy.a(cvVar, l.b);
                            break;
                    }
                    cvVar.m();
                }
            }
        }

        /* renamed from: b */
        public void a(cv cvVar, bc bcVar) throws cc {
            bcVar.l();
            cvVar.a(bc.f);
            if (bcVar.a != null) {
                cvVar.a(bc.g);
                cvVar.a(bcVar.a);
                cvVar.c();
            }
            cvVar.a(bc.h);
            cvVar.a(bcVar.b);
            cvVar.c();
            cvVar.a(bc.i);
            cvVar.a(bcVar.c);
            cvVar.c();
            cvVar.d();
            cvVar.b();
        }
    }

    /* compiled from: IdSnapshot.java */
    /* loaded from: classes2.dex */
    private static class d implements de {
        private d() {
        }

        /* renamed from: a */
        public c b() {
            return new c();
        }
    }

    /* compiled from: IdSnapshot.java */
    /* loaded from: classes2.dex */
    public static class c extends dg<bc> {
        private c() {
        }

        public void a(cv cvVar, bc bcVar) throws cc {
            db dbVar = (db) cvVar;
            dbVar.a(bcVar.a);
            dbVar.a(bcVar.b);
            dbVar.a(bcVar.c);
        }

        public void b(cv cvVar, bc bcVar) throws cc {
            db dbVar = (db) cvVar;
            bcVar.a = dbVar.z();
            bcVar.a(true);
            bcVar.b = dbVar.x();
            bcVar.b(true);
            bcVar.c = dbVar.w();
            bcVar.c(true);
        }
    }
}
