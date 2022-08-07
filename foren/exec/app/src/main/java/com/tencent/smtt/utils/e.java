package com.tencent.smtt.utils;

import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.UnknownFormatConversionException;

/* loaded from: classes.dex */
public class e implements Closeable {

    /* renamed from: a  reason: collision with root package name */
    public static final char[] f1523a = {127, 'E', 'L', 'F', 0};

    /* renamed from: b  reason: collision with root package name */
    public final char[] f1524b = new char[16];

    /* renamed from: c  reason: collision with root package name */
    public boolean f1525c;

    /* renamed from: d  reason: collision with root package name */
    public j[] f1526d;

    /* renamed from: e  reason: collision with root package name */
    public l[] f1527e;
    public byte[] f;
    public final c g;
    public final a h;
    public final k[] i;
    public byte[] j;

    /* loaded from: classes.dex */
    public static abstract class a {

        /* renamed from: a  reason: collision with root package name */
        public short f1528a;

        /* renamed from: b  reason: collision with root package name */
        public short f1529b;

        /* renamed from: c  reason: collision with root package name */
        public int f1530c;

        /* renamed from: d  reason: collision with root package name */
        public int f1531d;

        /* renamed from: e  reason: collision with root package name */
        public short f1532e;
        public short f;
        public short g;
        public short h;
        public short i;
        public short j;

        public abstract long a();

        public abstract long b();
    }

    /* loaded from: classes.dex */
    static class b extends a {
        public int k;
        public int l;
        public int m;

        @Override // com.tencent.smtt.utils.e.a
        public long a() {
            return this.m;
        }

        @Override // com.tencent.smtt.utils.e.a
        public long b() {
            return this.l;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static class c extends j {

        /* renamed from: a  reason: collision with root package name */
        public int f1533a;

        /* renamed from: b  reason: collision with root package name */
        public int f1534b;

        /* renamed from: c  reason: collision with root package name */
        public int f1535c;

        /* renamed from: d  reason: collision with root package name */
        public int f1536d;

        /* renamed from: e  reason: collision with root package name */
        public int f1537e;
        public int f;
    }

    /* loaded from: classes.dex */
    static class d extends k {

        /* renamed from: a  reason: collision with root package name */
        public int f1538a;

        /* renamed from: b  reason: collision with root package name */
        public int f1539b;

        /* renamed from: c  reason: collision with root package name */
        public int f1540c;

        /* renamed from: d  reason: collision with root package name */
        public int f1541d;

        /* renamed from: e  reason: collision with root package name */
        public int f1542e;
        public int f;

        @Override // com.tencent.smtt.utils.e.k
        public int a() {
            return this.f1541d;
        }

        @Override // com.tencent.smtt.utils.e.k
        public long b() {
            return this.f1540c;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.tencent.smtt.utils.e$e  reason: collision with other inner class name */
    /* loaded from: classes.dex */
    public static class C0019e extends l {

        /* renamed from: a  reason: collision with root package name */
        public int f1543a;

        /* renamed from: b  reason: collision with root package name */
        public int f1544b;
    }

    /* loaded from: classes.dex */
    static class f extends a {
        public long k;
        public long l;
        public long m;

        @Override // com.tencent.smtt.utils.e.a
        public long a() {
            return this.m;
        }

        @Override // com.tencent.smtt.utils.e.a
        public long b() {
            return this.l;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static class g extends j {

        /* renamed from: a  reason: collision with root package name */
        public long f1545a;

        /* renamed from: b  reason: collision with root package name */
        public long f1546b;

        /* renamed from: c  reason: collision with root package name */
        public long f1547c;

        /* renamed from: d  reason: collision with root package name */
        public long f1548d;

        /* renamed from: e  reason: collision with root package name */
        public long f1549e;
        public long f;
    }

    /* loaded from: classes.dex */
    static class h extends k {

        /* renamed from: a  reason: collision with root package name */
        public long f1550a;

        /* renamed from: b  reason: collision with root package name */
        public long f1551b;

        /* renamed from: c  reason: collision with root package name */
        public long f1552c;

        /* renamed from: d  reason: collision with root package name */
        public long f1553d;

        /* renamed from: e  reason: collision with root package name */
        public long f1554e;
        public long f;

        @Override // com.tencent.smtt.utils.e.k
        public int a() {
            return (int) this.f1553d;
        }

        @Override // com.tencent.smtt.utils.e.k
        public long b() {
            return this.f1552c;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static class i extends l {

        /* renamed from: a  reason: collision with root package name */
        public long f1555a;

        /* renamed from: b  reason: collision with root package name */
        public long f1556b;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static abstract class j {
        public int g;
        public int h;
    }

    /* loaded from: classes.dex */
    public static abstract class k {
        public int g;
        public int h;
        public int i;
        public int j;

        public abstract int a();

        public abstract long b();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static abstract class l {

        /* renamed from: c  reason: collision with root package name */
        public int f1557c;

        /* renamed from: d  reason: collision with root package name */
        public char f1558d;

        /* renamed from: e  reason: collision with root package name */
        public char f1559e;
        public short f;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public e(File file) {
        b bVar;
        c cVar = new c(file);
        this.g = cVar;
        cVar.a(this.f1524b);
        if (a()) {
            cVar.a(e());
            boolean d2 = d();
            if (d2) {
                f fVar = new f();
                fVar.f1528a = cVar.a();
                fVar.f1529b = cVar.a();
                fVar.f1530c = cVar.b();
                fVar.k = cVar.c();
                fVar.l = cVar.c();
                fVar.m = cVar.c();
                bVar = fVar;
            } else {
                b bVar2 = new b();
                bVar2.f1528a = cVar.a();
                bVar2.f1529b = cVar.a();
                bVar2.f1530c = cVar.b();
                bVar2.k = cVar.b();
                bVar2.l = cVar.b();
                bVar2.m = cVar.b();
                bVar = bVar2;
            }
            this.h = bVar;
            a aVar = this.h;
            aVar.f1531d = cVar.b();
            aVar.f1532e = cVar.a();
            aVar.f = cVar.a();
            aVar.g = cVar.a();
            aVar.h = cVar.a();
            aVar.i = cVar.a();
            aVar.j = cVar.a();
            this.i = new k[aVar.i];
            for (int i2 = 0; i2 < aVar.i; i2++) {
                cVar.a(aVar.a() + (aVar.h * i2));
                if (d2) {
                    h hVar = new h();
                    hVar.g = cVar.b();
                    hVar.h = cVar.b();
                    hVar.f1550a = cVar.c();
                    hVar.f1551b = cVar.c();
                    hVar.f1552c = cVar.c();
                    hVar.f1553d = cVar.c();
                    hVar.i = cVar.b();
                    hVar.j = cVar.b();
                    hVar.f1554e = cVar.c();
                    hVar.f = cVar.c();
                    this.i[i2] = hVar;
                } else {
                    d dVar = new d();
                    dVar.g = cVar.b();
                    dVar.h = cVar.b();
                    dVar.f1538a = cVar.b();
                    dVar.f1539b = cVar.b();
                    dVar.f1540c = cVar.b();
                    dVar.f1541d = cVar.b();
                    dVar.i = cVar.b();
                    dVar.j = cVar.b();
                    dVar.f1542e = cVar.b();
                    dVar.f = cVar.b();
                    this.i[i2] = dVar;
                }
            }
            short s = aVar.j;
            if (s > -1) {
                k[] kVarArr = this.i;
                if (s < kVarArr.length) {
                    k kVar = kVarArr[s];
                    if (kVar.h == 3) {
                        this.j = new byte[kVar.a()];
                        cVar.a(kVar.b());
                        cVar.a(this.j);
                        if (this.f1525c) {
                            f();
                            return;
                        }
                        return;
                    }
                    StringBuilder a2 = e.a.a.a.a.a("Wrong string section e_shstrndx=");
                    a2.append((int) aVar.j);
                    throw new UnknownFormatConversionException(a2.toString());
                }
            }
            StringBuilder a3 = e.a.a.a.a.a("Invalid e_shstrndx=");
            a3.append((int) aVar.j);
            throw new UnknownFormatConversionException(a3.toString());
        }
        throw new UnknownFormatConversionException(e.a.a.a.a.a("Invalid elf magic: ", file));
    }

    public static boolean a(File file) {
        try {
            RandomAccessFile randomAccessFile = new RandomAccessFile(file, "r");
            long readInt = randomAccessFile.readInt();
            randomAccessFile.close();
            return readInt == 2135247942;
        } catch (Throwable th) {
            th.printStackTrace();
            return false;
        }
    }

    public static boolean b(File file) {
        Object th;
        String str;
        StringBuilder sb;
        if (!g() || !a(file)) {
            return true;
        }
        try {
            new e(file);
            return true;
        } catch (IOException e2) {
            e.a.a.a.a.b("checkElfFile IOException: ", e2);
            return false;
        } catch (UnknownFormatConversionException e3) {
            th = e3;
            sb = new StringBuilder();
            str = "checkElfFile UnknownFormatConversionException: ";
            sb.append(str);
            sb.append(th);
            sb.toString();
            return true;
        } catch (Throwable th2) {
            th = th2;
            sb = new StringBuilder();
            str = "checkElfFile Throwable: ";
            sb.append(str);
            sb.append(th);
            sb.toString();
            return true;
        }
    }

    private void f() {
        a aVar = this.h;
        c cVar = this.g;
        boolean d2 = d();
        k a2 = a(".dynsym");
        if (a2 != null) {
            cVar.a(a2.b());
            int a3 = a2.a() / (d2 ? 24 : 16);
            this.f1527e = new l[a3];
            char[] cArr = new char[1];
            for (int i2 = 0; i2 < a3; i2++) {
                if (d2) {
                    i iVar = new i();
                    iVar.f1557c = cVar.b();
                    cVar.a(cArr);
                    iVar.f1558d = cArr[0];
                    cVar.a(cArr);
                    iVar.f1559e = cArr[0];
                    iVar.f1555a = cVar.c();
                    iVar.f1556b = cVar.c();
                    iVar.f = cVar.a();
                    this.f1527e[i2] = iVar;
                } else {
                    C0019e eVar = new C0019e();
                    eVar.f1557c = cVar.b();
                    eVar.f1543a = cVar.b();
                    eVar.f1544b = cVar.b();
                    cVar.a(cArr);
                    eVar.f1558d = cArr[0];
                    cVar.a(cArr);
                    eVar.f1559e = cArr[0];
                    eVar.f = cVar.a();
                    this.f1527e[i2] = eVar;
                }
            }
            k kVar = this.i[a2.i];
            cVar.a(kVar.b());
            this.f = new byte[kVar.a()];
            cVar.a(this.f);
        }
        this.f1526d = new j[aVar.g];
        for (int i3 = 0; i3 < aVar.g; i3++) {
            cVar.a(aVar.b() + (aVar.f * i3));
            if (d2) {
                g gVar = new g();
                gVar.g = cVar.b();
                gVar.h = cVar.b();
                gVar.f1545a = cVar.c();
                gVar.f1546b = cVar.c();
                gVar.f1547c = cVar.c();
                gVar.f1548d = cVar.c();
                gVar.f1549e = cVar.c();
                gVar.f = cVar.c();
                this.f1526d[i3] = gVar;
            } else {
                c cVar2 = new c();
                cVar2.g = cVar.b();
                cVar2.h = cVar.b();
                cVar2.f1533a = cVar.b();
                cVar2.f1534b = cVar.b();
                cVar2.f1535c = cVar.b();
                cVar2.f1536d = cVar.b();
                cVar2.f1537e = cVar.b();
                cVar2.f = cVar.b();
                this.f1526d[i3] = cVar2;
            }
        }
    }

    public static boolean g() {
        String property = System.getProperty("java.vm.version");
        return property != null && property.startsWith("2");
    }

    public final k a(String str) {
        k[] kVarArr = this.i;
        for (k kVar : kVarArr) {
            if (str.equals(a(kVar.g))) {
                return kVar;
            }
        }
        return null;
    }

    public final String a(int i2) {
        if (i2 == 0) {
            return "SHN_UNDEF";
        }
        int i3 = i2;
        while (true) {
            byte[] bArr = this.j;
            if (bArr[i3] == 0) {
                return new String(bArr, i2, i3 - i2);
            }
            i3++;
        }
    }

    public final boolean a() {
        return this.f1524b[0] == f1523a[0];
    }

    public final char b() {
        return this.f1524b[4];
    }

    public final char c() {
        return this.f1524b[5];
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        this.g.close();
    }

    public final boolean d() {
        return b() == 2;
    }

    public final boolean e() {
        return c() == 1;
    }
}
