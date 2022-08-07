package cn.jpush.android.a;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.telephony.CellLocation;
import android.widget.Toast;
import cn.jpush.android.util.ac;
import cn.jpush.android.util.b;
import java.util.Date;
import org.json.JSONArray;

/* loaded from: classes.dex */
public abstract class d {
    private static final String[] B;
    public static int b;
    public static boolean c;
    private static boolean f;
    private JSONArray A;
    public String a;
    protected boolean d;
    private boolean e;
    private int g;
    private b h;
    private g i;
    private Context j;
    private int[] k;
    private f l;
    private boolean m;
    private final BroadcastReceiver n;
    private long o;
    private int p;
    private boolean q;
    private boolean r;
    private j s;
    private int t;

    /* renamed from: u  reason: collision with root package name */
    private final Date f0u;
    private JSONArray v;
    private boolean w;
    private boolean x;
    private boolean y;
    private String z;

    /* JADX WARN: Removed duplicated region for block: B:12:0x002b A[LOOP:1: B:7:0x001a->B:12:0x002b, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:15:0x0033  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x0071  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x0074  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x0076  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0079  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x002f A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:9:0x0021  */
    static {
        /*
            r4 = 3
            r3 = 2
            r2 = 1
            r1 = 0
            r0 = 5
            java.lang.String[] r6 = new java.lang.String[r0]
            java.lang.String r5 = "XiQc\u0019Pc\u001ba\u0013Kj\\b\u0005Ph[?7zDpB%fA|_3fKzR7mNz_"
            r0 = -1
            r7 = r6
            r8 = r6
            r6 = r1
        L_0x000d:
            char[] r5 = r5.toCharArray()
            int r9 = r5.length
            if (r9 > r2) goto L_0x007c
            r10 = r1
        L_0x0015:
            r11 = r5
            r12 = r10
            r15 = r9
            r9 = r5
            r5 = r15
        L_0x001a:
            char r14 = r9[r10]
            int r13 = r12 % 5
            switch(r13) {
                case 0: goto L_0x0071;
                case 1: goto L_0x0074;
                case 2: goto L_0x0076;
                case 3: goto L_0x0079;
                default: goto L_0x0021;
            }
        L_0x0021:
            r13 = 118(0x76, float:1.65E-43)
        L_0x0023:
            r13 = r13 ^ r14
            char r13 = (char) r13
            r9[r10] = r13
            int r10 = r12 + 1
            if (r5 != 0) goto L_0x002f
            r9 = r11
            r12 = r10
            r10 = r5
            goto L_0x001a
        L_0x002f:
            r9 = r5
            r5 = r11
        L_0x0031:
            if (r9 > r10) goto L_0x0015
            java.lang.String r9 = new java.lang.String
            r9.<init>(r5)
            java.lang.String r5 = r9.intern()
            switch(r0) {
                case 0: goto L_0x0048;
                case 1: goto L_0x0051;
                case 2: goto L_0x005a;
                case 3: goto L_0x0064;
                default: goto L_0x003f;
            }
        L_0x003f:
            r7[r6] = r5
            java.lang.String r0 = "XiQc\u0019Pc\u001ba\u0013Kj\\b\u0005Ph[?7zDpB%fP|W?fTaP\"|"
            r5 = r0
            r6 = r2
            r7 = r8
            r0 = r1
            goto L_0x000d
        L_0x0048:
            r7[r6] = r5
            java.lang.String r0 = "XiQc\u0019Pc\u001ba\u0013Kj\\b\u0005Ph[?7zDpB%fDzP$jBj]9zFaX9w"
            r5 = r0
            r6 = r3
            r7 = r8
            r0 = r2
            goto L_0x000d
        L_0x0051:
            r7[r6] = r5
            java.lang.String r0 = "XiQc\u0019Pc\u001ba\u0013Kj\\b\u0005Ph[?5qF{V3fP|W?fTaP\"|"
            r5 = r0
            r6 = r4
            r7 = r8
            r0 = r3
            goto L_0x000d
        L_0x005a:
            r7[r6] = r5
            r5 = 4
            java.lang.String r0 = "XkY"
            r6 = r5
            r7 = r8
            r5 = r0
            r0 = r4
            goto L_0x000d
        L_0x0064:
            r7[r6] = r5
            cn.jpush.android.a.d.B = r8
            r0 = 15000(0x3a98, float:2.102E-41)
            cn.jpush.android.a.d.b = r0
            cn.jpush.android.a.d.c = r2
            cn.jpush.android.a.d.f = r1
            return
        L_0x0071:
            r13 = 57
            goto L_0x0023
        L_0x0074:
            r13 = 7
            goto L_0x0023
        L_0x0076:
            r13 = 53
            goto L_0x0023
        L_0x0079:
            r13 = 17
            goto L_0x0023
        L_0x007c:
            r10 = r1
            goto L_0x0031
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.jpush.android.a.d.<clinit>():void");
    }

    private d(Context context) {
        this.e = false;
        this.q = false;
        this.f0u = new Date();
        this.x = false;
        this.y = false;
        this.n = new e(this, (byte) 0);
        this.j = context.getApplicationContext();
        this.h = new b(context);
        this.s = new j(context);
        this.i = new g(context);
    }

    private d(Context context, String str) {
        this(context);
        if (str == null || "".equals(str)) {
            this.a = B[4];
        } else {
            this.a = str;
        }
    }

    private d(Context context, String str, boolean z) {
        this(context, str);
        this.x = z;
    }

    public d(Context context, String str, boolean z, boolean z2) {
        this(context, str, z);
        this.e = z2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void a(d dVar, Object obj) {
        if (f) {
            obj.toString();
            ac.b();
            Toast.makeText(dVar.j, String.valueOf(obj), 0).show();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean a(Context context) {
        return b.c(context, B[2]) && b.c(context, B[1]) && b.c(context, B[3]) && b.c(context, B[0]);
    }

    public final String a() {
        String d = this.i.d();
        return d == null ? "" : d;
    }

    public final void a(JSONArray jSONArray) {
        this.v = jSONArray;
    }

    public final JSONArray b() {
        if (!b.c(this.j, B[2])) {
            return null;
        }
        return this.h.e() ? this.h.b() : this.v;
    }

    public final void b(JSONArray jSONArray) {
        this.A = jSONArray;
    }

    public final JSONArray c() {
        if (this.A != null) {
            return this.A;
        }
        return null;
    }

    public abstract void d();

    public final void e() {
        if (this.p == 1) {
            CellLocation.requestLocationUpdate();
            this.p = 2;
            this.l.sendEmptyMessage(1);
            if (this.s.b().isWifiEnabled()) {
                this.s.b().startScan();
                this.r = false;
            } else if (!this.e) {
                this.l.sendEmptyMessageDelayed(5, 0L);
            } else {
                this.o = System.currentTimeMillis();
                if (!c) {
                    this.l.sendEmptyMessageDelayed(5, 8000L);
                } else {
                    this.r = true;
                }
            }
        }
    }

    public final void f() {
        if (!this.y) {
            this.y = true;
            this.e = false;
            this.x = false;
            if (b.c(this.j, B[2])) {
                this.v = this.h.c();
            } else {
                ac.d();
                this.v = null;
            }
            if (!b.c(this.j, B[1])) {
                ac.d();
                this.A = null;
            } else if (a(this.j) || b.w(this.j)) {
                this.w = this.s.a();
                if (!this.w) {
                    this.A = null;
                } else {
                    this.A = this.s.c();
                }
            } else {
                this.A = null;
            }
            if (!b.c(this.j, B[0])) {
                ac.d();
            } else if (this.i.a()) {
                this.i.b();
                if (!("" == this.i.d() || this.i.d() == null || System.currentTimeMillis() - this.i.e() >= 30000)) {
                    this.t = 0;
                    this.z = this.i.d();
                }
                if (!this.q && !this.x) {
                    d();
                    return;
                }
            }
            this.z = "";
            if (!this.q) {
            }
        }
    }

    public final void g() {
        this.y = false;
        if (b.c(this.j, B[0]) && this.i.a()) {
            this.i.c();
        }
    }
}
