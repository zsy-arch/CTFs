package cn.jpush.android.data;

import android.content.Context;
import android.support.v4.view.PointerIconCompat;
import android.text.TextUtils;
import cn.jpush.android.e;
import cn.jpush.android.helpers.h;
import cn.jpush.android.helpers.k;
import cn.jpush.android.util.ac;
import cn.jpush.android.util.ao;
import cn.jpush.android.util.b;
import cn.jpush.android.util.p;
import cn.jpush.android.util.r;
import cn.jpush.android.util.s;
import com.tencent.smtt.utils.TbsLog;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.json.JSONObject;

/* loaded from: classes.dex */
public abstract class c implements Serializable {
    private static final String[] E;
    public String A;
    public String C;
    public String D;
    public int b;
    public String c;
    public String d;
    public boolean e;
    public int f;
    public boolean h;
    public String i;
    public String j;
    public String k;
    public String l;
    public String m;
    public String n;
    public int o;
    public boolean p;
    public int r;
    public String s;
    public String t;

    /* renamed from: u */
    public List<c> f1u;
    public int g = 0;
    public List<String> q = null;
    public boolean v = false;
    public boolean w = false;
    public boolean x = false;
    private boolean a = false;
    public boolean y = false;
    public int z = -1;
    public ArrayList<String> B = null;

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
            case 7: goto L_0x007d;
            case 8: goto L_0x0087;
            case 9: goto L_0x0092;
            case 10: goto L_0x009d;
            default: goto L_0x003c;
        };
     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x003c, code lost:
        r3[r2] = r1;
        r2 = 1;
        r1 = "g4g_Zhm\u007fYTb\u001e~Qgc$3\u001b\u0015s%\u007f\f";
        r0 = 0;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x0044, code lost:
        r3[r2] = r1;
        r2 = 2;
        r1 = "h\buZTa";
        r0 = 1;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x004c, code lost:
        r3[r2] = r1;
        r2 = 3;
        r1 = "h\bvNAt6`";
        r0 = 2;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:19:0x0054, code lost:
        r3[r2] = r1;
        r2 = 4;
        r1 = "`\"\u007fZju4aSPh";
        r0 = 3;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x005c, code lost:
        r3[r2] = r1;
        r2 = 5;
        r1 = "g3LUZh#vXA";
        r0 = 4;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x0064, code lost:
        r3[r2] = r1;
        r2 = 6;
        r1 = "h\bpY[r2}B";
        r0 = 5;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x006c, code lost:
        r3[r2] = r1;
        r2 = 7;
        r1 = "h\bg_Aj2";
        r0 = 6;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x0074, code lost:
        r3[r2] = r1;
        r2 = '\b';
        r1 = "C9g_A\u007f";
        r0 = 7;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x007d, code lost:
        r3[r2] = r1;
        r2 = '\t';
        r1 = "Q%zBP&$gYGg0v\u0016Pt%|D\u0019&wpDPg#v\u0016\\k03P\\j23PTo;=";
        r0 = '\b';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x0087, code lost:
        r3[r2] = r1;
        r2 = '\n';
        r1 = "g4g_Zhm\u007fYTb\u001fg[YO:rQPT2`Y@t4vE\u0015+wfDYV%vP\\~m";
        r0 = '\t';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x0092, code lost:
        r3[r2] = r1;
        r2 = 11;
        r1 = "n#gF\u000f)x";
        r0 = '\n';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x009d, code lost:
        r3[r2] = r1;
        cn.jpush.android.data.c.E = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x00a1, code lost:
        return;
     */
    /* JADX WARN: Code restructure failed: missing block: B:29:0x00a2, code lost:
        r9 = 6;
     */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x00a5, code lost:
        r9 = 'W';
     */
    /* JADX WARN: Code restructure failed: missing block: B:31:0x00a9, code lost:
        r9 = 19;
     */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x00ad, code lost:
        r9 = '6';
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
            case 0: goto L_0x00a2;
            case 1: goto L_0x00a5;
            case 2: goto L_0x00a9;
            case 3: goto L_0x00ad;
            default: goto L_0x001e;
        };
     */
    /* JADX WARN: Code restructure failed: missing block: B:8:0x001e, code lost:
        r9 = '5';
     */
    /* JADX WARN: Code restructure failed: missing block: B:9:0x0020, code lost:
        r5[r6] = (char) (r9 ^ r10);
        r6 = r8 + 1;
     */
    static {
        /*
            Method dump skipped, instructions count: 216
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.jpush.android.data.c.<clinit>():void");
    }

    public static String a(String str, String str2, String str3, Context context) {
        new StringBuilder(E[1]).append(str);
        ac.a();
        if (h.a(str) && context != null && !TextUtils.isEmpty(str2) && !TextUtils.isEmpty(str3)) {
            byte[] a = s.a(str, 5, 5000L, 4);
            if (a != null) {
                try {
                    String str4 = p.a(context, str2) + str3;
                    r.a(str4, a);
                    new StringBuilder(E[0]).append(str4);
                    ac.a();
                    return str4;
                } catch (IOException e) {
                    ac.i();
                    return "";
                }
            } else {
                k.a(str2, PointerIconCompat.TYPE_GRAB, b.b(context, str), context);
            }
        }
        return "";
    }

    public static boolean a(ArrayList<String> arrayList, Context context, String str, String str2, boolean z) {
        new StringBuilder(E[10]).append(str);
        ac.a();
        if (!h.a(str) || context == null || arrayList.size() <= 0 || TextUtils.isEmpty(str2)) {
            return true;
        }
        Iterator<String> it = arrayList.iterator();
        boolean z2 = true;
        while (it.hasNext()) {
            String next = it.next();
            String str3 = (next == null || next.startsWith(E[11])) ? next : str + next;
            byte[] a = s.a(str3, 5, 5000L, 4);
            if (a != null) {
                try {
                    if (next.startsWith(E[11])) {
                        next = r.c(next);
                    }
                    String str4 = !z ? p.a(context, str2) + next : p.b(context, str2) + next;
                    r.a(str4, a);
                    new StringBuilder(E[0]).append(str4);
                    ac.a();
                } catch (IOException e) {
                    ac.a(E[8], E[9], e);
                    z2 = false;
                }
            } else {
                k.a(str2, PointerIconCompat.TYPE_GRAB, b.b(context, str3), context);
                z2 = false;
            }
        }
        return z2;
    }

    public abstract void a(Context context);

    public final boolean a() {
        return this.o == 3 || this.o == 1;
    }

    protected abstract boolean a(Context context, JSONObject jSONObject);

    public final boolean b() {
        return this.o == 2;
    }

    public final boolean b(Context context, JSONObject jSONObject) {
        ac.a();
        this.p = jSONObject.optInt(E[4], 0) > 0;
        this.r = jSONObject.optInt(E[2], 0);
        this.s = jSONObject.optString(E[7], "");
        this.t = jSONObject.optString(E[6], "");
        this.l = jSONObject.optString(E[3], "");
        if (ao.a(this.s)) {
            if (!this.h) {
                ac.b();
                k.a(this.c, TbsLog.TBSLOG_CODE_SDK_SELF_MODE, context);
                return false;
            }
            ac.b();
            this.s = e.d;
        }
        JSONObject a = h.a(context, this.c, jSONObject, E[5]);
        if (a == null) {
            return this.h && this.e;
        }
        if (this.h && this.e) {
            this.a = true;
        }
        return a(context, a);
    }

    public final boolean c() {
        return this.o == 3;
    }

    public final String d() {
        return a() ? ((i) this).K : b() ? ((s) this).E : this.a ? this.D : "";
    }

    public final boolean e() {
        return this.a;
    }
}
