package u.aly;

import com.alimama.mobile.csdk.umupdate.a.f;
import com.tencent.open.GameAppOperation;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.ByteBuffer;
import java.util.BitSet;
import java.util.Collections;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/* compiled from: UMEnvelope.java */
/* loaded from: classes2.dex */
public class bh implements Serializable, Cloneable, bw<bh, e> {
    private static final int A = 2;
    private static final int B = 3;
    public static final Map<e, ci> k;
    private static final long l = 420342210744516016L;
    private static final da m = new da("UMEnvelope");
    private static final cq n = new cq("version", (byte) 11, 1);
    private static final cq o = new cq("address", (byte) 11, 2);
    private static final cq p = new cq(GameAppOperation.GAME_SIGNATURE, (byte) 11, 3);
    private static final cq q = new cq("serial_num", (byte) 8, 4);
    private static final cq r = new cq("ts_secs", (byte) 8, 5);
    private static final cq s = new cq("length", (byte) 8, 6);
    private static final cq t = new cq("entity", (byte) 11, 7);

    /* renamed from: u  reason: collision with root package name */
    private static final cq f56u = new cq("guid", (byte) 11, 8);
    private static final cq v = new cq("checksum", (byte) 11, 9);
    private static final cq w = new cq("codex", (byte) 8, 10);
    private static final Map<Class<? extends dd>, de> x = new HashMap();
    private static final int y = 0;
    private static final int z = 1;
    private byte C;
    private e[] D;
    public String a;
    public String b;
    public String c;
    public int d;
    public int e;
    public int f;
    public ByteBuffer g;
    public String h;
    public String i;
    public int j;

    static {
        x.put(df.class, new b());
        x.put(dg.class, new d());
        EnumMap enumMap = new EnumMap(e.class);
        enumMap.put((EnumMap) e.VERSION, (e) new ci("version", (byte) 1, new cj((byte) 11)));
        enumMap.put((EnumMap) e.ADDRESS, (e) new ci("address", (byte) 1, new cj((byte) 11)));
        enumMap.put((EnumMap) e.SIGNATURE, (e) new ci(GameAppOperation.GAME_SIGNATURE, (byte) 1, new cj((byte) 11)));
        enumMap.put((EnumMap) e.SERIAL_NUM, (e) new ci("serial_num", (byte) 1, new cj((byte) 8)));
        enumMap.put((EnumMap) e.TS_SECS, (e) new ci("ts_secs", (byte) 1, new cj((byte) 8)));
        enumMap.put((EnumMap) e.LENGTH, (e) new ci("length", (byte) 1, new cj((byte) 8)));
        enumMap.put((EnumMap) e.ENTITY, (e) new ci("entity", (byte) 1, new cj((byte) 11, true)));
        enumMap.put((EnumMap) e.GUID, (e) new ci("guid", (byte) 1, new cj((byte) 11)));
        enumMap.put((EnumMap) e.CHECKSUM, (e) new ci("checksum", (byte) 1, new cj((byte) 11)));
        enumMap.put((EnumMap) e.CODEX, (e) new ci("codex", (byte) 2, new cj((byte) 8)));
        k = Collections.unmodifiableMap(enumMap);
        ci.a(bh.class, k);
    }

    /* compiled from: UMEnvelope.java */
    /* loaded from: classes2.dex */
    public enum e implements cd {
        VERSION(1, "version"),
        ADDRESS(2, "address"),
        SIGNATURE(3, GameAppOperation.GAME_SIGNATURE),
        SERIAL_NUM(4, "serial_num"),
        TS_SECS(5, "ts_secs"),
        LENGTH(6, "length"),
        ENTITY(7, "entity"),
        GUID(8, "guid"),
        CHECKSUM(9, "checksum"),
        CODEX(10, "codex");
        
        private static final Map<String, e> k = new HashMap();
        private final short l;
        private final String m;

        static {
            Iterator it = EnumSet.allOf(e.class).iterator();
            while (it.hasNext()) {
                e eVar = (e) it.next();
                k.put(eVar.b(), eVar);
            }
        }

        public static e a(int i) {
            switch (i) {
                case 1:
                    return VERSION;
                case 2:
                    return ADDRESS;
                case 3:
                    return SIGNATURE;
                case 4:
                    return SERIAL_NUM;
                case 5:
                    return TS_SECS;
                case 6:
                    return LENGTH;
                case 7:
                    return ENTITY;
                case 8:
                    return GUID;
                case 9:
                    return CHECKSUM;
                case 10:
                    return CODEX;
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
            return k.get(str);
        }

        e(short s, String str) {
            this.l = s;
            this.m = str;
        }

        @Override // u.aly.cd
        public short a() {
            return this.l;
        }

        @Override // u.aly.cd
        public String b() {
            return this.m;
        }
    }

    public bh() {
        this.C = (byte) 0;
        this.D = new e[]{e.CODEX};
    }

    public bh(String str, String str2, String str3, int i, int i2, int i3, ByteBuffer byteBuffer, String str4, String str5) {
        this();
        this.a = str;
        this.b = str2;
        this.c = str3;
        this.d = i;
        d(true);
        this.e = i2;
        e(true);
        this.f = i3;
        f(true);
        this.g = byteBuffer;
        this.h = str4;
        this.i = str5;
    }

    public bh(bh bhVar) {
        this.C = (byte) 0;
        this.D = new e[]{e.CODEX};
        this.C = bhVar.C;
        if (bhVar.e()) {
            this.a = bhVar.a;
        }
        if (bhVar.h()) {
            this.b = bhVar.b;
        }
        if (bhVar.k()) {
            this.c = bhVar.c;
        }
        this.d = bhVar.d;
        this.e = bhVar.e;
        this.f = bhVar.f;
        if (bhVar.y()) {
            this.g = bx.d(bhVar.g);
        }
        if (bhVar.B()) {
            this.h = bhVar.h;
        }
        if (bhVar.E()) {
            this.i = bhVar.i;
        }
        this.j = bhVar.j;
    }

    /* renamed from: a */
    public bh p() {
        return new bh(this);
    }

    @Override // u.aly.bw
    public void b() {
        this.a = null;
        this.b = null;
        this.c = null;
        d(false);
        this.d = 0;
        e(false);
        this.e = 0;
        f(false);
        this.f = 0;
        this.g = null;
        this.h = null;
        this.i = null;
        j(false);
        this.j = 0;
    }

    public String c() {
        return this.a;
    }

    public bh a(String str) {
        this.a = str;
        return this;
    }

    public void d() {
        this.a = null;
    }

    public boolean e() {
        return this.a != null;
    }

    public void a(boolean z2) {
        if (!z2) {
            this.a = null;
        }
    }

    public String f() {
        return this.b;
    }

    public bh b(String str) {
        this.b = str;
        return this;
    }

    public void g() {
        this.b = null;
    }

    public boolean h() {
        return this.b != null;
    }

    public void b(boolean z2) {
        if (!z2) {
            this.b = null;
        }
    }

    public String i() {
        return this.c;
    }

    public bh c(String str) {
        this.c = str;
        return this;
    }

    public void j() {
        this.c = null;
    }

    public boolean k() {
        return this.c != null;
    }

    public void c(boolean z2) {
        if (!z2) {
            this.c = null;
        }
    }

    public int l() {
        return this.d;
    }

    public bh a(int i) {
        this.d = i;
        d(true);
        return this;
    }

    public void m() {
        this.C = bt.b(this.C, 0);
    }

    public boolean n() {
        return bt.a(this.C, 0);
    }

    public void d(boolean z2) {
        this.C = bt.a(this.C, 0, z2);
    }

    public int o() {
        return this.e;
    }

    public bh c(int i) {
        this.e = i;
        e(true);
        return this;
    }

    public void q() {
        this.C = bt.b(this.C, 1);
    }

    public boolean r() {
        return bt.a(this.C, 1);
    }

    public void e(boolean z2) {
        this.C = bt.a(this.C, 1, z2);
    }

    public int s() {
        return this.f;
    }

    public bh d(int i) {
        this.f = i;
        f(true);
        return this;
    }

    public void t() {
        this.C = bt.b(this.C, 2);
    }

    public boolean u() {
        return bt.a(this.C, 2);
    }

    public void f(boolean z2) {
        this.C = bt.a(this.C, 2, z2);
    }

    public byte[] v() {
        a(bx.c(this.g));
        if (this.g == null) {
            return null;
        }
        return this.g.array();
    }

    public ByteBuffer w() {
        return this.g;
    }

    public bh a(byte[] bArr) {
        a(bArr == null ? null : ByteBuffer.wrap(bArr));
        return this;
    }

    public bh a(ByteBuffer byteBuffer) {
        this.g = byteBuffer;
        return this;
    }

    public void x() {
        this.g = null;
    }

    public boolean y() {
        return this.g != null;
    }

    public void g(boolean z2) {
        if (!z2) {
            this.g = null;
        }
    }

    public String z() {
        return this.h;
    }

    public bh d(String str) {
        this.h = str;
        return this;
    }

    public void A() {
        this.h = null;
    }

    public boolean B() {
        return this.h != null;
    }

    public void h(boolean z2) {
        if (!z2) {
            this.h = null;
        }
    }

    public String C() {
        return this.i;
    }

    public bh e(String str) {
        this.i = str;
        return this;
    }

    public void D() {
        this.i = null;
    }

    public boolean E() {
        return this.i != null;
    }

    public void i(boolean z2) {
        if (!z2) {
            this.i = null;
        }
    }

    public int F() {
        return this.j;
    }

    public bh e(int i) {
        this.j = i;
        j(true);
        return this;
    }

    public void G() {
        this.C = bt.b(this.C, 3);
    }

    public boolean H() {
        return bt.a(this.C, 3);
    }

    public void j(boolean z2) {
        this.C = bt.a(this.C, 3, z2);
    }

    /* renamed from: f */
    public e b(int i) {
        return e.a(i);
    }

    @Override // u.aly.bw
    public void a(cv cvVar) throws cc {
        x.get(cvVar.D()).b().b(cvVar, this);
    }

    @Override // u.aly.bw
    public void b(cv cvVar) throws cc {
        x.get(cvVar.D()).b().a(cvVar, this);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("UMEnvelope(");
        sb.append("version:");
        if (this.a == null) {
            sb.append(f.b);
        } else {
            sb.append(this.a);
        }
        sb.append(", ");
        sb.append("address:");
        if (this.b == null) {
            sb.append(f.b);
        } else {
            sb.append(this.b);
        }
        sb.append(", ");
        sb.append("signature:");
        if (this.c == null) {
            sb.append(f.b);
        } else {
            sb.append(this.c);
        }
        sb.append(", ");
        sb.append("serial_num:");
        sb.append(this.d);
        sb.append(", ");
        sb.append("ts_secs:");
        sb.append(this.e);
        sb.append(", ");
        sb.append("length:");
        sb.append(this.f);
        sb.append(", ");
        sb.append("entity:");
        if (this.g == null) {
            sb.append(f.b);
        } else {
            bx.a(this.g, sb);
        }
        sb.append(", ");
        sb.append("guid:");
        if (this.h == null) {
            sb.append(f.b);
        } else {
            sb.append(this.h);
        }
        sb.append(", ");
        sb.append("checksum:");
        if (this.i == null) {
            sb.append(f.b);
        } else {
            sb.append(this.i);
        }
        if (H()) {
            sb.append(", ");
            sb.append("codex:");
            sb.append(this.j);
        }
        sb.append(")");
        return sb.toString();
    }

    public void I() throws cc {
        if (this.a == null) {
            throw new cw("Required field 'version' was not present! Struct: " + toString());
        } else if (this.b == null) {
            throw new cw("Required field 'address' was not present! Struct: " + toString());
        } else if (this.c == null) {
            throw new cw("Required field 'signature' was not present! Struct: " + toString());
        } else if (this.g == null) {
            throw new cw("Required field 'entity' was not present! Struct: " + toString());
        } else if (this.h == null) {
            throw new cw("Required field 'guid' was not present! Struct: " + toString());
        } else if (this.i == null) {
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
            this.C = (byte) 0;
            a(new cp(new dh(objectInputStream)));
        } catch (cc e2) {
            throw new IOException(e2.getMessage());
        }
    }

    /* compiled from: UMEnvelope.java */
    /* loaded from: classes2.dex */
    private static class b implements de {
        private b() {
        }

        /* renamed from: a */
        public a b() {
            return new a();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: UMEnvelope.java */
    /* loaded from: classes2.dex */
    public static class a extends df<bh> {
        private a() {
        }

        /* renamed from: a */
        public void b(cv cvVar, bh bhVar) throws cc {
            cvVar.j();
            while (true) {
                cq l = cvVar.l();
                if (l.b == 0) {
                    cvVar.k();
                    if (!bhVar.n()) {
                        throw new cw("Required field 'serial_num' was not found in serialized data! Struct: " + toString());
                    } else if (!bhVar.r()) {
                        throw new cw("Required field 'ts_secs' was not found in serialized data! Struct: " + toString());
                    } else if (!bhVar.u()) {
                        throw new cw("Required field 'length' was not found in serialized data! Struct: " + toString());
                    } else {
                        bhVar.I();
                        return;
                    }
                } else {
                    switch (l.c) {
                        case 1:
                            if (l.b != 11) {
                                cy.a(cvVar, l.b);
                                break;
                            } else {
                                bhVar.a = cvVar.z();
                                bhVar.a(true);
                                break;
                            }
                        case 2:
                            if (l.b != 11) {
                                cy.a(cvVar, l.b);
                                break;
                            } else {
                                bhVar.b = cvVar.z();
                                bhVar.b(true);
                                break;
                            }
                        case 3:
                            if (l.b != 11) {
                                cy.a(cvVar, l.b);
                                break;
                            } else {
                                bhVar.c = cvVar.z();
                                bhVar.c(true);
                                break;
                            }
                        case 4:
                            if (l.b != 8) {
                                cy.a(cvVar, l.b);
                                break;
                            } else {
                                bhVar.d = cvVar.w();
                                bhVar.d(true);
                                break;
                            }
                        case 5:
                            if (l.b != 8) {
                                cy.a(cvVar, l.b);
                                break;
                            } else {
                                bhVar.e = cvVar.w();
                                bhVar.e(true);
                                break;
                            }
                        case 6:
                            if (l.b != 8) {
                                cy.a(cvVar, l.b);
                                break;
                            } else {
                                bhVar.f = cvVar.w();
                                bhVar.f(true);
                                break;
                            }
                        case 7:
                            if (l.b != 11) {
                                cy.a(cvVar, l.b);
                                break;
                            } else {
                                bhVar.g = cvVar.A();
                                bhVar.g(true);
                                break;
                            }
                        case 8:
                            if (l.b != 11) {
                                cy.a(cvVar, l.b);
                                break;
                            } else {
                                bhVar.h = cvVar.z();
                                bhVar.h(true);
                                break;
                            }
                        case 9:
                            if (l.b != 11) {
                                cy.a(cvVar, l.b);
                                break;
                            } else {
                                bhVar.i = cvVar.z();
                                bhVar.i(true);
                                break;
                            }
                        case 10:
                            if (l.b != 8) {
                                cy.a(cvVar, l.b);
                                break;
                            } else {
                                bhVar.j = cvVar.w();
                                bhVar.j(true);
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
        public void a(cv cvVar, bh bhVar) throws cc {
            bhVar.I();
            cvVar.a(bh.m);
            if (bhVar.a != null) {
                cvVar.a(bh.n);
                cvVar.a(bhVar.a);
                cvVar.c();
            }
            if (bhVar.b != null) {
                cvVar.a(bh.o);
                cvVar.a(bhVar.b);
                cvVar.c();
            }
            if (bhVar.c != null) {
                cvVar.a(bh.p);
                cvVar.a(bhVar.c);
                cvVar.c();
            }
            cvVar.a(bh.q);
            cvVar.a(bhVar.d);
            cvVar.c();
            cvVar.a(bh.r);
            cvVar.a(bhVar.e);
            cvVar.c();
            cvVar.a(bh.s);
            cvVar.a(bhVar.f);
            cvVar.c();
            if (bhVar.g != null) {
                cvVar.a(bh.t);
                cvVar.a(bhVar.g);
                cvVar.c();
            }
            if (bhVar.h != null) {
                cvVar.a(bh.f56u);
                cvVar.a(bhVar.h);
                cvVar.c();
            }
            if (bhVar.i != null) {
                cvVar.a(bh.v);
                cvVar.a(bhVar.i);
                cvVar.c();
            }
            if (bhVar.H()) {
                cvVar.a(bh.w);
                cvVar.a(bhVar.j);
                cvVar.c();
            }
            cvVar.d();
            cvVar.b();
        }
    }

    /* compiled from: UMEnvelope.java */
    /* loaded from: classes2.dex */
    private static class d implements de {
        private d() {
        }

        /* renamed from: a */
        public c b() {
            return new c();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: UMEnvelope.java */
    /* loaded from: classes2.dex */
    public static class c extends dg<bh> {
        private c() {
        }

        public void a(cv cvVar, bh bhVar) throws cc {
            db dbVar = (db) cvVar;
            dbVar.a(bhVar.a);
            dbVar.a(bhVar.b);
            dbVar.a(bhVar.c);
            dbVar.a(bhVar.d);
            dbVar.a(bhVar.e);
            dbVar.a(bhVar.f);
            dbVar.a(bhVar.g);
            dbVar.a(bhVar.h);
            dbVar.a(bhVar.i);
            BitSet bitSet = new BitSet();
            if (bhVar.H()) {
                bitSet.set(0);
            }
            dbVar.a(bitSet, 1);
            if (bhVar.H()) {
                dbVar.a(bhVar.j);
            }
        }

        public void b(cv cvVar, bh bhVar) throws cc {
            db dbVar = (db) cvVar;
            bhVar.a = dbVar.z();
            bhVar.a(true);
            bhVar.b = dbVar.z();
            bhVar.b(true);
            bhVar.c = dbVar.z();
            bhVar.c(true);
            bhVar.d = dbVar.w();
            bhVar.d(true);
            bhVar.e = dbVar.w();
            bhVar.e(true);
            bhVar.f = dbVar.w();
            bhVar.f(true);
            bhVar.g = dbVar.A();
            bhVar.g(true);
            bhVar.h = dbVar.z();
            bhVar.h(true);
            bhVar.i = dbVar.z();
            bhVar.i(true);
            if (dbVar.b(1).get(0)) {
                bhVar.j = dbVar.w();
                bhVar.j(true);
            }
        }
    }
}
