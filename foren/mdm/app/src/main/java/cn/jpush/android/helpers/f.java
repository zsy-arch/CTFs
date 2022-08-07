package cn.jpush.android.helpers;

import android.content.Context;
import cn.jpush.android.c;
import cn.jpush.android.service.PushService;
import cn.jpush.android.util.ac;
import cn.jpush.android.util.al;

/* loaded from: classes.dex */
public final class f extends c {
    private static final String[] z;
    private Context a;

    /* JADX WARN: Code restructure failed: missing block: B:10:0x0026, code lost:
        if (r5 != 0) goto L_0x002c;
     */
    /* JADX WARN: Code restructure failed: missing block: B:11:0x0028, code lost:
        r5 = r1;
        r8 = r6;
        r6 = r5;
     */
    /* JADX WARN: Code restructure failed: missing block: B:12:0x002c, code lost:
        r5 = r5;
        r1 = r1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:13:0x002e, code lost:
        if (r5 > r6) goto L_0x0012;
     */
    /* JADX WARN: Code restructure failed: missing block: B:14:0x0030, code lost:
        r1 = new java.lang.String(r1).intern();
     */
    /* JADX WARN: Code restructure failed: missing block: B:15:0x0039, code lost:
        switch(r0) {
            case 0: goto L_0x0044;
            case 1: goto L_0x004c;
            case 2: goto L_0x0054;
            case 3: goto L_0x005c;
            case 4: goto L_0x0064;
            case 5: goto L_0x006c;
            case 6: goto L_0x0074;
            case 7: goto L_0x007e;
            case 8: goto L_0x0088;
            case 9: goto L_0x0093;
            case 10: goto L_0x009f;
            case 11: goto L_0x00ab;
            case 12: goto L_0x00b7;
            default: goto L_0x003c;
        };
     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x003c, code lost:
        r3[r2] = r1;
        r2 = 1;
        r1 = "^p]7\u000eMg@y:\u0014qHc<\u0019wP7<PqE;}RpP-";
        r0 = 0;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x0044, code lost:
        r3[r2] = r1;
        r2 = 2;
        r1 = "^p]7?VzEr<W5Kn}X|M{q\u0019~Lng";
        r0 = 1;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x004c, code lost:
        r3[r2] = r1;
        r2 = 3;
        r1 = "ZzDz4M5Ex3^5Kn}X|M{q\u0019~Lng";
        r0 = 2;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:19:0x0054, code lost:
        r3[r2] = r1;
        r2 = 4;
        r1 = "ZzDz4M5Kx2UpHy}[l\tv4]y\u000576\\l\u0013";
        r0 = 3;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x005c, code lost:
        r3[r2] = r1;
        r2 = 5;
        r1 = "^p]74WaLp8K5Kn}X|M{q\u0019~Lng";
        r0 = 4;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x0064, code lost:
        r3[r2] = r1;
        r2 = 6;
        r1 = "^p]71V{N7?@5H~9U9\t|8@/";
        r0 = 5;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x006c, code lost:
        r3[r2] = r1;
        r2 = 7;
        r1 = "I`Z\u007f\u0011Vr@y}JaHc(J5Kn}X|M{g";
        r0 = 6;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x0074, code lost:
        r3[r2] = r1;
        r2 = '\b';
        r1 = "wz]~;PvHc4V{xb8Lp\tx;_p[7?@5H~9U9\ty2M|O~>Xa@x3pq\t-}";
        r0 = 7;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x007e, code lost:
        r3[r2] = r1;
        r2 = '\t';
        r1 = "ZzDz4M5zc/P{N7?@5H~9U9\t|8@/";
        r0 = '\b';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x0088, code lost:
        r3[r2] = r1;
        r2 = '\n';
        r1 = "ZzDz4M5@y)\\rLe}[l\tv4]y\u000576\\l\u0013";
        r0 = '\t';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x0093, code lost:
        r3[r2] = r1;
        r2 = 11;
        r1 = "wz]~;PvHc4V{xb8Lp\tp8M5Z~'\\5Kn}X|M{q\u0019f@m8\u0019/\t";
        r0 = '\n';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x009f, code lost:
        r3[r2] = r1;
        r2 = '\f';
        r1 = "wz]~;PvHc4V{xb8Lp\tt2WaH~3J5Kn}X|M{q\u0019{Fc4_|Jv)PzG^9\u0019/\t";
        r0 = 11;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x00ab, code lost:
        r3[r2] = r1;
        r2 = '\r';
        r1 = "wz]~;PvHc4V{xb8Lp\tg2Uy\tu$\u0019t@s1\u00155Gx)Ps@t<M|Fy\u0014]5\u00137";
        r0 = '\f';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:29:0x00b7, code lost:
        r3[r2] = r1;
        cn.jpush.android.helpers.f.z = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x00bb, code lost:
        return;
     */
    /* JADX WARN: Code restructure failed: missing block: B:31:0x00bc, code lost:
        r9 = '9';
     */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x00c0, code lost:
        r9 = 21;
     */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x00c4, code lost:
        r9 = ')';
     */
    /* JADX WARN: Code restructure failed: missing block: B:34:0x00c8, code lost:
        r9 = 23;
     */
    /* JADX WARN: Code restructure failed: missing block: B:4:0x0010, code lost:
        if (r5 <= 1) goto L_0x0012;
     */
    /* JADX WARN: Code restructure failed: missing block: B:5:0x0012, code lost:
        r8 = r6;
        r5 = r1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:6:0x0017, code lost:
        r10 = r5[r6];
     */
    /* JADX WARN: Code restructure failed: missing block: B:7:0x001b, code lost:
        switch((r8 % 5)) {
            case 0: goto L_0x00bc;
            case 1: goto L_0x00c0;
            case 2: goto L_0x00c4;
            case 3: goto L_0x00c8;
            default: goto L_0x001e;
        };
     */
    /* JADX WARN: Code restructure failed: missing block: B:8:0x001e, code lost:
        r9 = ']';
     */
    /* JADX WARN: Code restructure failed: missing block: B:9:0x0020, code lost:
        r5[r6] = (char) (r9 ^ r10);
        r6 = r8 + 1;
     */
    static {
        /*
            Method dump skipped, instructions count: 246
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.jpush.android.helpers.f.<clinit>():void");
    }

    public f(Context context) {
        this.a = context;
    }

    @Override // cn.jpush.android.b
    public final int a() {
        int a = g.a();
        new StringBuilder(z[13]).append(a);
        ac.a();
        return a;
    }

    @Override // cn.jpush.android.b
    public final int a(String str, int i) {
        int b = al.b(this.a, str, i);
        new StringBuilder(z[5]).append(str).append(z[0]).append(b);
        ac.a();
        return b;
    }

    @Override // cn.jpush.android.b
    public final long a(String str, long j) {
        long b = al.b(this.a, str, j);
        new StringBuilder(z[6]).append(str).append(z[0]).append(b);
        ac.a();
        return b;
    }

    @Override // cn.jpush.android.b
    public final String a(String str, String str2) {
        String b = al.b(this.a, str, str2);
        new StringBuilder(z[1]).append(str).append(z[0]).append(b);
        ac.a();
        return b;
    }

    @Override // cn.jpush.android.b
    public final void a(int i, long j, boolean z2, float f, double d, String str) {
    }

    @Override // cn.jpush.android.b
    public final boolean a(int i) {
        new StringBuilder(z[8]).append(i);
        ac.a();
        return g.a(i);
    }

    @Override // cn.jpush.android.b
    public final boolean a(String str, boolean z2) {
        boolean b = al.b(this.a, str, z2);
        new StringBuilder(z[2]).append(str).append(z[0]).append(b);
        ac.a();
        return b;
    }

    @Override // cn.jpush.android.b
    public final int b() {
        int b = g.b();
        new StringBuilder(z[11]).append(b);
        ac.a();
        return b;
    }

    @Override // cn.jpush.android.b
    public final void b(String str, int i) {
        new StringBuilder(z[10]).append(str).append(z[0]).append(i);
        ac.a();
        al.a(this.a, str, i);
    }

    @Override // cn.jpush.android.b
    public final void b(String str, long j) {
        new StringBuilder(z[3]).append(str).append(z[0]).append(j);
        ac.a();
        al.a(this.a, str, j);
    }

    @Override // cn.jpush.android.b
    public final void b(String str, String str2) {
        new StringBuilder(z[9]).append(str).append(z[0]).append(str2);
        ac.a();
        al.a(this.a, str, str2);
    }

    @Override // cn.jpush.android.b
    public final void b(String str, boolean z2) {
        new StringBuilder(z[4]).append(str).append(z[0]).append(z2);
        ac.a();
        al.a(this.a, str, z2);
    }

    @Override // cn.jpush.android.b
    public final boolean b(int i) {
        new StringBuilder(z[12]).append(i);
        ac.a();
        return g.b(i);
    }

    @Override // cn.jpush.android.b
    public final boolean c() {
        new StringBuilder(z[7]).append(PushService.a());
        ac.a();
        return PushService.a();
    }
}
