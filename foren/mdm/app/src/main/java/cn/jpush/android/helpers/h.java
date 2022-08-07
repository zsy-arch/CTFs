package cn.jpush.android.helpers;

import android.content.Context;
import android.text.TextUtils;
import cn.jpush.android.data.a;
import cn.jpush.android.data.c;
import cn.jpush.android.data.d;
import cn.jpush.android.data.i;
import cn.jpush.android.data.m;
import cn.jpush.android.data.r;
import cn.jpush.android.data.s;
import cn.jpush.android.util.ac;
import cn.jpush.android.util.ao;
import cn.jpush.android.util.b;
import com.tencent.smtt.utils.TbsLog;
import java.util.Queue;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public final class h {
    private static Queue<d> a;
    private static final String[] z;

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
            case 11: goto L_0x00a8;
            case 12: goto L_0x00b3;
            case 13: goto L_0x00be;
            case 14: goto L_0x00c9;
            case 15: goto L_0x00d4;
            case 16: goto L_0x00df;
            case 17: goto L_0x00ea;
            case 18: goto L_0x00f5;
            case 19: goto L_0x0100;
            case 20: goto L_0x010b;
            case 21: goto L_0x0116;
            case 22: goto L_0x0121;
            case 23: goto L_0x012c;
            case 24: goto L_0x0137;
            case 25: goto L_0x0142;
            default: goto L_0x003c;
        };
     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x003c, code lost:
        r3[r2] = r1;
        r2 = 1;
        r1 = "5R<j~\u0017RwIB'\u001c'v~\u0014S4k}@J2vb\tS9*1'U!a1\u0015Lw)1";
        r0 = 0;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x0044, code lost:
        r3[r2] = r1;
        r2 = 2;
        r1 = "\u0001X\bp";
        r0 = 1;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x004c, code lost:
        r3[r2] = r1;
        r2 = 3;
        r1 = "5R<j~\u0017\u001c:wv@H.tt@]3[e@\u0001w";
        r0 = 2;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:19:0x0054, code lost:
        r3[r2] = r1;
        r2 = 4;
        r1 = "\rc4k\u007f\u0014Y9p";
        r0 = 3;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x005c, code lost:
        r3[r2] = r1;
        r2 = 5;
        r1 = ".i\u001bH1\u0003S9pt\u0018H";
        r0 = 4;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x0064, code lost:
        r3[r2] = r1;
        r2 = 6;
        r1 = ".swIB'u\u0013";
        r0 = 5;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x006c, code lost:
        r3[r2] = r1;
        r2 = 7;
        r1 = "\u0001X\bmu";
        r0 = 6;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x0074, code lost:
        r3[r2] = r1;
        r2 = '\b';
        r1 = "\u0001_#m~\u000e\u0006'ec\u0013Y\u0018vx\u0007U9e}-O0It\u0013O6ct@\u0011wkc\t[>jp\fv$k\u007fZ6";
        r0 = 7;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x007d, code lost:
        r3[r2] = r1;
        r2 = '\t';
        r1 = "\rO0[x\u0004";
        r0 = '\b';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x0087, code lost:
        r3[r2] = r1;
        r2 = '\n';
        r1 = "5R<j~\u0017\u001c:wv@H.tt@\u0011w";
        r0 = '\t';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x0092, code lost:
        r3[r2] = r1;
        r2 = 11;
        r1 = "&]>ht\u0004\u001c#k1\u0007Y#${\u0013S9$w\u0012S:$d\u0012Pwft\u0003]\"wt\u000fZwm\u007f\u0016];mu@I%h1M\u001c";
        r0 = '\n';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x009d, code lost:
        r3[r2] = r1;
        r2 = '\f';
        r1 = "\u0001_#m~\u000e\u0006;kp\u0004q$c[\u0013S9Bc\u000fQ\u0002v}@\u0011w";
        r0 = 11;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x00a8, code lost:
        r3[r2] = r1;
        r2 = '\r';
        r1 = "\u0013T8sN\u0014E'a";
        r0 = '\f';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:29:0x00b3, code lost:
        r3[r2] = r1;
        r2 = 14;
        r1 = "\u0005D#vp\u0013";
        r0 = '\r';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x00be, code lost:
        r3[r2] = r1;
        r2 = 15;
        r1 = "\u0010N2Tp\u0012O2Kc\t[>jp\fq$c\\\u0005O$ev\u0005\u001c:wv)Xw91";
        r0 = 14;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:31:0x00c9, code lost:
        r3[r2] = r1;
        r2 = 16;
        r1 = "\u000fJ2vc\tX2[|\u0013[\bmu";
        r0 = 15;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x00d4, code lost:
        r3[r2] = r1;
        r2 = 17;
        r1 = "\u0003S9pt\u000eH\bph\u0010Y";
        r0 = 16;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x00df, code lost:
        r3[r2] = r1;
        r2 = 18;
        r1 = "\rY$wp\u0007Y";
        r0 = 17;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:34:0x00ea, code lost:
        r3[r2] = r1;
        r2 = 19;
        r1 = "\u000ec5qx\fX2vN\tX";
        r0 = 18;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:35:0x00f5, code lost:
        r3[r2] = r1;
        r2 = com.tencent.open.GameAppOperation.PIC_SYMBOLE;
        r1 = "\u0014U#ht";
        r0 = 19;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:36:0x0100, code lost:
        r3[r2] = r1;
        r2 = 21;
        r1 = "\u000eS#mw\t_6m~\u000ec#}a\u0005";
        r0 = com.tencent.open.GameAppOperation.PIC_SYMBOLE;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:37:0x010b, code lost:
        r3[r2] = r1;
        r2 = 22;
        r1 = ".s\bIB'u\u0013";
        r0 = 21;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:38:0x0116, code lost:
        r3[r2] = r1;
        r2 = 23;
        r1 = "\u000ec8j}\u0019";
        r0 = 22;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:39:0x0121, code lost:
        r3[r2] = r1;
        r2 = 24;
        r1 = "\u0001_#m~\u000e\u0006'vt0]%wt/N>cx\u000e];Ib\u0007q2wb\u0001[2$<@S%mv\tR6h[\u0013S9>\u001b";
        r0 = 23;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:40:0x012c, code lost:
        r3[r2] = r1;
        r2 = 25;
        r1 = ")R!e}\tXwqc\f\u001cz$";
        r0 = 24;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:41:0x0137, code lost:
        r3[r2] = r1;
        r2 = 26;
        r1 = ">g?pe\u0010@?pe\u0010O\n/+O\u0013y.";
        r0 = 25;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:42:0x0142, code lost:
        r3[r2] = r1;
        cn.jpush.android.helpers.h.z = r3;
        cn.jpush.android.helpers.h.a = new java.util.LinkedList();
     */
    /* JADX WARN: Code restructure failed: missing block: B:43:0x014d, code lost:
        return;
     */
    /* JADX WARN: Code restructure failed: missing block: B:44:0x014e, code lost:
        r9 = '`';
     */
    /* JADX WARN: Code restructure failed: missing block: B:45:0x0152, code lost:
        r9 = '<';
     */
    /* JADX WARN: Code restructure failed: missing block: B:46:0x0156, code lost:
        r9 = 'W';
     */
    /* JADX WARN: Code restructure failed: missing block: B:47:0x015a, code lost:
        r9 = 4;
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
            case 0: goto L_0x014e;
            case 1: goto L_0x0152;
            case 2: goto L_0x0156;
            case 3: goto L_0x015a;
            default: goto L_0x001e;
        };
     */
    /* JADX WARN: Code restructure failed: missing block: B:8:0x001e, code lost:
        r9 = 17;
     */
    /* JADX WARN: Code restructure failed: missing block: B:9:0x0020, code lost:
        r5[r6] = (char) (r9 ^ r10);
        r6 = r8 + 1;
     */
    static {
        /*
            Method dump skipped, instructions count: 418
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.jpush.android.helpers.h.<clinit>():void");
    }

    public static a a(Context context, String str, String str2, String str3, String str4) {
        new StringBuilder(z[24]).append(str);
        ac.b();
        if (context == null) {
            throw new IllegalArgumentException(z[5]);
        } else if (TextUtils.isEmpty(str)) {
            ac.e();
            k.a(z[6], TbsLog.TBSLOG_CODE_SDK_SELF_MODE, context);
            return null;
        } else {
            JSONObject a2 = a(context, z[22], str);
            if (a2 == null) {
                ac.b();
                return null;
            }
            String optString = a2.optString(z[9], "");
            if (!ao.a(optString)) {
                str4 = optString;
            }
            if (ao.a(str4)) {
                str4 = a2.optString(z[7], "");
            }
            new StringBuilder(z[15]).append(str4);
            ac.b();
            boolean z2 = a2.optInt(z[23], 0) == 1;
            int optInt = z2 ? a2.optInt(z[19], 0) : 0;
            a aVar = new a();
            aVar.c = str4;
            aVar.a = a2;
            aVar.b = a2.optInt(z[13], 3);
            aVar.e = z2;
            aVar.f = optInt;
            aVar.g = a2.optInt(z[21], 0);
            aVar.i = a2.optString(z[18], "");
            aVar.j = a2.optString(z[17], "");
            aVar.k = a2.optString(z[20], "");
            aVar.l = a2.optString(z[14], "");
            aVar.m = str2;
            aVar.n = str3;
            aVar.d = a2.optString(z[16], "");
            return aVar;
        }
    }

    private static JSONObject a(Context context, String str, String str2) {
        try {
            return new JSONObject(str2);
        } catch (JSONException e) {
            ac.i();
            k.a(str, TbsLog.TBSLOG_CODE_SDK_SELF_MODE, context);
            return null;
        }
    }

    public static JSONObject a(Context context, String str, JSONObject jSONObject, String str2) {
        if (jSONObject == null) {
            ac.d();
            k.a(str, TbsLog.TBSLOG_CODE_SDK_SELF_MODE, context);
            return null;
        } else if (TextUtils.isEmpty(str2)) {
            ac.d();
            return null;
        } else {
            try {
                if (!jSONObject.isNull(str2)) {
                    return jSONObject.getJSONObject(str2);
                }
                return null;
            } catch (JSONException e) {
                ac.i();
                k.a(str, TbsLog.TBSLOG_CODE_SDK_SELF_MODE, context);
                return null;
            }
        }
    }

    public static void a(Context context, a aVar) {
        ac.a();
        if (context == null) {
            throw new IllegalArgumentException(z[5]);
        }
        int i = aVar.b;
        JSONObject jSONObject = aVar.a;
        String str = aVar.c;
        if (i == 3 || i == 4) {
            JSONObject a2 = a(context, str, jSONObject, z[4]);
            if (a2 == null) {
                ac.d();
                return;
            }
            int optInt = a2.optInt(z[2], -1);
            if (optInt == 0) {
                m mVar = new m();
                mVar.c = str;
                mVar.b = i;
                mVar.o = optInt;
                mVar.h = aVar.h;
                mVar.e = aVar.e;
                mVar.f = aVar.f;
                mVar.m = aVar.m;
                mVar.d = aVar.d;
                mVar.g = aVar.g;
                boolean b = mVar.b(context, a2);
                ac.a();
                if (b) {
                    mVar.a(context);
                    ac.a();
                    return;
                }
                ac.d();
                return;
            }
            new StringBuilder(z[3]).append(optInt);
            ac.d();
            k.a(str, TbsLog.TBSLOG_CODE_SDK_SELF_MODE, context);
            return;
        }
        new StringBuilder(z[1]).append(i);
        ac.b();
        k.a(str, TbsLog.TBSLOG_CODE_SDK_SELF_MODE, context);
    }

    public static void a(Context context, String str) {
        c rVar;
        new StringBuilder(z[8]).append(str);
        ac.a();
        if (context == null) {
            throw new IllegalArgumentException(z[5]);
        } else if (TextUtils.isEmpty(str)) {
            ac.e();
        } else {
            JSONObject a2 = a(context, z[6], str);
            if (a2 != null) {
                String optString = a2.optString(z[9], "");
                if (ao.a(optString)) {
                    optString = a2.optString(z[7], "");
                }
                int optInt = a2.optInt(z[13], -1);
                JSONObject jSONObject = null;
                if (optInt == 2) {
                    String trim = a2.optString(z[4], "").trim();
                    if (a(trim)) {
                        new StringBuilder(z[12]).append(trim);
                        ac.a();
                        if (context == null) {
                            throw new IllegalArgumentException(z[5]);
                        }
                        new i(trim, context, optString).start();
                        return;
                    }
                    new StringBuilder(z[11]).append(trim);
                    ac.b();
                    k.a(optString, TbsLog.TBSLOG_CODE_SDK_SELF_MODE, context);
                    return;
                }
                if (optInt == 1) {
                    jSONObject = a(context, optString, a2, z[4]);
                }
                if (jSONObject != null) {
                    int optInt2 = jSONObject.optInt(z[2], -1);
                    switch (optInt2) {
                        case 0:
                            rVar = new m();
                            break;
                        case 1:
                            rVar = new i();
                            break;
                        case 2:
                            rVar = new s();
                            break;
                        case 3:
                            rVar = new r();
                            break;
                        default:
                            new StringBuilder(z[10]).append(optInt2);
                            ac.d();
                            k.a(optString, TbsLog.TBSLOG_CODE_SDK_SELF_MODE, context);
                            return;
                    }
                    boolean b = rVar.b(context, jSONObject);
                    ac.a();
                    rVar.c = optString;
                    rVar.b = optInt;
                    rVar.o = optInt2;
                    if (b) {
                        rVar.a(context);
                        ac.a();
                        return;
                    }
                    ac.d();
                }
            }
        }
    }

    public static boolean a(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        String trim = str.trim();
        boolean matches = trim.matches(z[26]);
        if (matches) {
            return matches;
        }
        new StringBuilder(z[25]).append(trim);
        ac.d();
        return matches;
    }

    public static boolean a(boolean z2, int i, Context context) {
        boolean equalsIgnoreCase = z[0].equalsIgnoreCase(b.d(context));
        if (!z2 || i != 0) {
            return z2 && i == 1 && equalsIgnoreCase;
        }
        return true;
    }
}
