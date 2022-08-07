package u.aly;

import android.content.Context;
import android.text.TextUtils;
import java.io.File;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Map;
import java.util.TreeMap;

/* compiled from: ImprintHandler.java */
/* loaded from: classes2.dex */
public class x {
    private static final String a = ".imprint";
    private static final byte[] b = "pbl0".getBytes();
    private static x f;
    private ap c;
    private a d = new a();
    private be e = null;
    private Context g;

    x(Context context) {
        this.g = context;
    }

    public static synchronized x a(Context context) {
        x xVar;
        synchronized (x.class) {
            if (f == null) {
                f = new x(context);
                f.c();
            }
            xVar = f;
        }
        return xVar;
    }

    public void a(ap apVar) {
        this.c = apVar;
    }

    public String a(be beVar) {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry entry : new TreeMap(beVar.d()).entrySet()) {
            sb.append((String) entry.getKey());
            if (((bf) entry.getValue()).e()) {
                sb.append(((bf) entry.getValue()).c());
            }
            sb.append(((bf) entry.getValue()).f());
            sb.append(((bf) entry.getValue()).i());
        }
        sb.append(beVar.b);
        return bm.a(sb.toString()).toLowerCase(Locale.US);
    }

    private boolean c(be beVar) {
        if (!beVar.j().equals(a(beVar))) {
            return false;
        }
        for (bf bfVar : beVar.d().values()) {
            byte[] a2 = bj.a(bfVar.i());
            byte[] a3 = a(bfVar);
            for (int i = 0; i < 4; i++) {
                if (a2[i] != a3[i]) {
                    return false;
                }
            }
        }
        return true;
    }

    public byte[] a(bf bfVar) {
        ByteBuffer allocate = ByteBuffer.allocate(8);
        allocate.order(null);
        allocate.putLong(bfVar.f());
        byte[] array = allocate.array();
        byte[] bArr = b;
        byte[] bArr2 = new byte[4];
        for (int i = 0; i < 4; i++) {
            bArr2[i] = (byte) (array[i] ^ bArr[i]);
        }
        return bArr2;
    }

    public void b(be beVar) {
        String j;
        be a2;
        String str = null;
        if (beVar != null && c(beVar)) {
            boolean z = false;
            synchronized (this) {
                be beVar2 = this.e;
                if (beVar2 == null) {
                    j = null;
                } else {
                    j = beVar2.j();
                }
                if (beVar2 == null) {
                    a2 = d(beVar);
                } else {
                    a2 = a(beVar2, beVar);
                }
                this.e = a2;
                if (a2 != null) {
                    str = a2.j();
                }
                if (!a(j, str)) {
                    z = true;
                }
            }
            if (this.e != null && z) {
                this.d.a(this.e);
                if (this.c != null) {
                    this.c.a(this.d);
                }
            }
        }
    }

    private boolean a(String str, String str2) {
        if (str != null) {
            return str.equals(str2);
        }
        if (str2 != null) {
            return false;
        }
        return true;
    }

    private be a(be beVar, be beVar2) {
        if (beVar2 != null) {
            Map<String, bf> d = beVar.d();
            for (Map.Entry<String, bf> entry : beVar2.d().entrySet()) {
                if (entry.getValue().e()) {
                    d.put(entry.getKey(), entry.getValue());
                } else {
                    d.remove(entry.getKey());
                }
            }
            beVar.a(beVar2.g());
            beVar.a(a(beVar));
        }
        return beVar;
    }

    private be d(be beVar) {
        Map<String, bf> d = beVar.d();
        ArrayList<String> arrayList = new ArrayList(d.size() / 2);
        for (Map.Entry<String, bf> entry : d.entrySet()) {
            if (!entry.getValue().e()) {
                arrayList.add(entry.getKey());
            }
        }
        for (String str : arrayList) {
            d.remove(str);
        }
        return beVar;
    }

    public synchronized be a() {
        return this.e;
    }

    public a b() {
        return this.d;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:22:0x0026 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:30:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Type inference failed for: r1v10 */
    /* JADX WARN: Type inference failed for: r1v11 */
    /* JADX WARN: Type inference failed for: r1v5 */
    /* JADX WARN: Type inference failed for: r2v0 */
    /* JADX WARN: Type inference failed for: r2v1 */
    /* JADX WARN: Type inference failed for: r2v2, types: [java.io.InputStream] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void c() {
        /*
            r4 = this;
            r2 = 0
            java.io.File r0 = new java.io.File
            android.content.Context r1 = r4.g
            java.io.File r1 = r1.getFilesDir()
            java.lang.String r3 = ".imprint"
            r0.<init>(r1, r3)
            boolean r0 = r0.exists()
            if (r0 != 0) goto L_0x0015
        L_0x0014:
            return
        L_0x0015:
            android.content.Context r0 = r4.g     // Catch: Exception -> 0x0040, all -> 0x0049
            java.lang.String r1 = ".imprint"
            java.io.FileInputStream r1 = r0.openFileInput(r1)     // Catch: Exception -> 0x0040, all -> 0x0049
            byte[] r2 = u.aly.bm.b(r1)     // Catch: Exception -> 0x0051, all -> 0x004e
            u.aly.bm.c(r1)
        L_0x0024:
            if (r2 == 0) goto L_0x0014
            u.aly.be r0 = new u.aly.be     // Catch: Exception -> 0x003b
            r0.<init>()     // Catch: Exception -> 0x003b
            u.aly.bz r1 = new u.aly.bz     // Catch: Exception -> 0x003b
            r1.<init>()     // Catch: Exception -> 0x003b
            r1.a(r0, r2)     // Catch: Exception -> 0x003b
            r4.e = r0     // Catch: Exception -> 0x003b
            u.aly.x$a r1 = r4.d     // Catch: Exception -> 0x003b
            r1.a(r0)     // Catch: Exception -> 0x003b
            goto L_0x0014
        L_0x003b:
            r0 = move-exception
            r0.printStackTrace()
            goto L_0x0014
        L_0x0040:
            r0 = move-exception
            r1 = r2
        L_0x0042:
            r0.printStackTrace()     // Catch: all -> 0x004e
            u.aly.bm.c(r1)
            goto L_0x0024
        L_0x0049:
            r0 = move-exception
        L_0x004a:
            u.aly.bm.c(r2)
            throw r0
        L_0x004e:
            r0 = move-exception
            r2 = r1
            goto L_0x004a
        L_0x0051:
            r0 = move-exception
            goto L_0x0042
        */
        throw new UnsupportedOperationException("Method not decompiled: u.aly.x.c():void");
    }

    public void d() {
        if (this.e != null) {
            try {
                bm.a(new File(this.g.getFilesDir(), a), new cf().a(this.e));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public boolean e() {
        return new File(this.g.getFilesDir(), a).delete();
    }

    /* compiled from: ImprintHandler.java */
    /* loaded from: classes2.dex */
    public static class a {
        private int a = -1;
        private int b = -1;
        private int c = -1;
        private int d = -1;
        private int e = -1;
        private String f = null;
        private int g = -1;
        private String h = null;
        private int i = -1;
        private int j = -1;
        private String k = null;
        private String l = null;
        private String m = null;
        private String n = null;
        private String o = null;

        a() {
        }

        a(be beVar) {
            a(beVar);
        }

        public void a(be beVar) {
            if (beVar != null) {
                this.a = a(beVar, "defcon");
                this.b = a(beVar, av.an);
                this.c = a(beVar, "codex");
                this.d = a(beVar, "report_policy");
                this.e = a(beVar, "report_interval");
                this.f = b(beVar, "client_test");
                this.g = a(beVar, "test_report_interval");
                this.h = b(beVar, "umid");
                this.i = a(beVar, "integrated_test");
                this.j = a(beVar, "latent_hours");
                this.k = b(beVar, "country");
                this.l = b(beVar, "domain_p");
                this.m = b(beVar, "domain_s");
                this.n = b(beVar, av.Q);
                this.o = b(beVar, "track_list");
            }
        }

        public String a(String str) {
            if (this.n != null) {
                return this.n;
            }
            return str;
        }

        public String b(String str) {
            if (this.o != null) {
                return this.o;
            }
            return str;
        }

        public String c(String str) {
            if (this.m != null) {
                return this.m;
            }
            return str;
        }

        public String d(String str) {
            if (this.l != null) {
                return this.l;
            }
            return str;
        }

        public String e(String str) {
            if (this.k != null) {
                return this.k;
            }
            return str;
        }

        public int a(int i) {
            return (this.a != -1 && this.a <= 3 && this.a >= 0) ? this.a : i;
        }

        public int b(int i) {
            return (this.b != -1 && this.b >= 0 && this.b <= 1800) ? this.b * 1000 : i;
        }

        public int c(int i) {
            if (this.c == 0 || this.c == 1 || this.c == -1) {
                return this.c;
            }
            return i;
        }

        public int[] a(int i, int i2) {
            if (this.d == -1 || !bq.a(this.d)) {
                return new int[]{i, i2};
            }
            if (this.e == -1 || this.e < 90 || this.e > 86400) {
                this.e = 90;
            }
            return new int[]{this.d, this.e * 1000};
        }

        public String f(String str) {
            return (this.f == null || !ax.a(this.f)) ? str : this.f;
        }

        public int d(int i) {
            return (this.g == -1 || this.g < 90 || this.g > 86400) ? i : this.g * 1000;
        }

        public boolean a() {
            return this.g != -1;
        }

        public String g(String str) {
            return this.h;
        }

        public boolean b() {
            return this.i == 1;
        }

        public long a(long j) {
            return (this.j != -1 && this.j >= 48) ? com.umeng.analytics.a.k * this.j : j;
        }

        private int a(be beVar, String str) {
            if (beVar != null) {
                try {
                    if (beVar.f()) {
                        bf bfVar = beVar.d().get(str);
                        if (bfVar != null) {
                            if (!TextUtils.isEmpty(bfVar.c())) {
                                try {
                                    return Integer.parseInt(bfVar.c().trim());
                                } catch (Exception e) {
                                    return -1;
                                }
                            }
                        }
                        return -1;
                    }
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
            return -1;
        }

        private String b(be beVar, String str) {
            String str2;
            bf bfVar;
            if (beVar == null) {
                return null;
            }
            try {
            } catch (Exception e) {
                e.printStackTrace();
                str2 = null;
            }
            if (!beVar.f() || (bfVar = beVar.d().get(str)) == null || TextUtils.isEmpty(bfVar.c())) {
                return null;
            }
            str2 = bfVar.c();
            return str2;
        }
    }
}
