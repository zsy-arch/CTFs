package cn.jpush.android.helpers;

import android.content.Context;
import cn.jpush.android.a;
import cn.jpush.android.a.i;
import cn.jpush.android.service.o;
import cn.jpush.android.util.ac;
import cn.jpush.android.util.ah;
import cn.jpush.android.util.ao;
import cn.jpush.android.util.b;
import cn.jpush.android.util.d;
import cn.jpush.android.util.v;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public final class k {
    private static int a;
    private static final String[] z;

    /* JADX WARN: Code restructure failed: missing block: B:10:0x0025, code lost:
        if (r5 != 0) goto L_0x002b;
     */
    /* JADX WARN: Code restructure failed: missing block: B:11:0x0027, code lost:
        r5 = r1;
        r8 = r6;
        r6 = r5;
     */
    /* JADX WARN: Code restructure failed: missing block: B:12:0x002b, code lost:
        r5 = r5;
        r1 = r1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:13:0x002d, code lost:
        if (r5 > r6) goto L_0x0012;
     */
    /* JADX WARN: Code restructure failed: missing block: B:14:0x002f, code lost:
        r1 = new java.lang.String(r1).intern();
     */
    /* JADX WARN: Code restructure failed: missing block: B:15:0x0038, code lost:
        switch(r0) {
            case 0: goto L_0x0043;
            case 1: goto L_0x004b;
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
            default: goto L_0x003b;
        };
     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x003b, code lost:
        r3[r2] = r1;
        r2 = 1;
        r1 = "\u001e)|\u0007wS)k\u000fyW38K#";
        r0 = 0;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x0043, code lost:
        r3[r2] = r1;
        r2 = 2;
        r1 = "Bh{\rbUl8\u000fmTf8\u0012lFhtFmGd8K#";
        r0 = 1;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x004b, code lost:
        r3[r2] = r1;
        r2 = 3;
        r1 = "|\\T*#Qfv\u0012fJ}";
        r0 = 2;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:19:0x0054, code lost:
        r3[r2] = r1;
        r2 = 4;
        r1 = "Sjl\u000fl\\3j\u0003s]{l)sW{y\u0012j]g8K#Qfv\u0012f\\}\"";
        r0 = 3;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x005c, code lost:
        r3[r2] = r1;
        r2 = 5;
        r1 = "\u001e+";
        r0 = 4;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x0064, code lost:
        r3[r2] = r1;
        r2 = 6;
        r1 = "I+l\twSe:\\&V%:\u0016bUl:\\&V%:\u0015f\\m}\u0014jV+\"D&A+4Dv[m:\\&A%:\u0016f@dq\u0015p[fv9o[zlD9\u0017ze";
        r0 = 5;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x006c, code lost:
        r3[r2] = r1;
        r2 = 7;
        r1 = "_z\u007f9pFhl\u0013p";
        r0 = 6;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x0074, code lost:
        r3[r2] = r1;
        r2 = '\b';
        r1 = "Sjl\u000fl\\3j\u0003s]{l'`F`w\bQWzm\nw\u0012$8\u000bfAzy\u0001f{m\"F";
        r0 = 7;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x007d, code lost:
        r3[r2] = r1;
        r2 = '\t';
        r1 = "\u0012{}\u0016l@}8\u0005l\\}}\bw\b)";
        r0 = '\b';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x0087, code lost:
        r3[r2] = r1;
        r2 = '\n';
        r1 = "\u001e){\tgW38";
        r0 = '\t';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x0092, code lost:
        r3[r2] = r1;
        r2 = 11;
        r1 = "_z\u007f9jV";
        r0 = '\n';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x009d, code lost:
        r3[r2] = r1;
        r2 = '\f';
        r1 = "Vhl\u0007";
        r0 = 11;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x00a8, code lost:
        r3[r2] = r1;
        r2 = '\r';
        r1 = "[}q\u000bf";
        r0 = '\f';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:29:0x00b3, code lost:
        r3[r2] = r1;
        r2 = 14;
        r1 = "@lk\u0013oF";
        r0 = '\r';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x00be, code lost:
        r3[r2] = r1;
        r2 = 15;
        r1 = "Fph\u0003";
        r0 = 14;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:31:0x00c9, code lost:
        r3[r2] = r1;
        cn.jpush.android.helpers.k.z = r3;
        cn.jpush.android.helpers.k.a = 20480;
     */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x00d1, code lost:
        return;
     */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x00d2, code lost:
        r9 = '2';
     */
    /* JADX WARN: Code restructure failed: missing block: B:34:0x00d6, code lost:
        r9 = '\t';
     */
    /* JADX WARN: Code restructure failed: missing block: B:35:0x00da, code lost:
        r9 = 24;
     */
    /* JADX WARN: Code restructure failed: missing block: B:36:0x00de, code lost:
        r9 = 'f';
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
            case 0: goto L_0x00d2;
            case 1: goto L_0x00d6;
            case 2: goto L_0x00da;
            case 3: goto L_0x00de;
            default: goto L_0x001e;
        };
     */
    /* JADX WARN: Code restructure failed: missing block: B:8:0x001e, code lost:
        r9 = 3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:9:0x001f, code lost:
        r5[r6] = (char) (r9 ^ r10);
        r6 = r8 + 1;
     */
    static {
        /*
            Method dump skipped, instructions count: 272
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.jpush.android.helpers.k.<clinit>():void");
    }

    public static void a(Context context) {
        if (!a.z()) {
            ac.d();
        } else if (context == null) {
            ac.d();
        } else {
            JSONArray n = b.n(context);
            if (!(n == null || n.length() == 0)) {
                int length = n.length();
                int length2 = n.toString().length();
                new StringBuilder(z[2]).append(length).append(z[1]).append(length2);
                ac.c();
                if (length2 <= a) {
                    JSONObject a2 = b.a(z[0], n);
                    if (a2 != null && a2.length() > 0) {
                        ah.a(context, a2);
                        return;
                    }
                    return;
                }
                JSONArray jSONArray = new JSONArray();
                for (int i = 0; i < length; i++) {
                    try {
                        jSONArray.put(n.getJSONObject(i));
                    } catch (JSONException e) {
                        ac.e();
                    }
                    if (jSONArray.toString().length() > a || length - 1 == i) {
                        JSONObject a3 = b.a(z[0], jSONArray);
                        if (a3 != null && a3.length() > 0) {
                            ah.a(context, a3);
                        }
                        jSONArray = new JSONArray();
                    }
                }
            }
        }
    }

    public static void a(Context context, String str) {
        ac.b();
        new d(context, str).start();
    }

    public static void a(Context context, JSONObject jSONObject) {
        if (a.z()) {
            if (context == null) {
                throw new IllegalArgumentException(z[3]);
            } else if (jSONObject != null && jSONObject.length() > 0) {
                ah.a(context, jSONObject);
                new StringBuilder(z[4]).append(jSONObject.toString());
                ac.b();
            }
        }
    }

    public static void a(Context context, boolean z2, String str, boolean z3, boolean z4) {
        ac.b();
        new i(context, true, str, z3, z4).f();
    }

    public static void a(String str, int i, Context context) {
        a(str, i, null, context);
    }

    public static void a(String str, int i, String str2, Context context) {
        if (!a.z()) {
            ac.a();
        } else if (context == null) {
            ac.b();
        } else {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append(z[8] + str + z[10] + i + "-" + o.b(i));
            if (!ao.a(str2)) {
                stringBuffer.append(z[9] + str2);
            }
            ac.b();
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put(z[11], str);
                jSONObject.put(z[14], i);
                if (!ao.a(str2)) {
                    jSONObject.put(z[12], str2);
                }
                jSONObject.put(z[13], a.m());
                jSONObject.put(z[15], z[7]);
                ah.a(context, jSONObject);
            } catch (JSONException e) {
            }
        }
    }

    public static void b(Context context) {
        ac.a();
        String[] a2 = v.a(context);
        if (a2 == null || a2.length == 0) {
            ac.e();
            return;
        }
        int length = a2.length;
        String str = "[";
        int i = 0;
        int i2 = 0;
        int i3 = 0;
        while (i < length) {
            String str2 = a2[i];
            str = i2 == 0 ? str + "\"" + str2 + "\"" : str + z[5] + str2 + "\"";
            int i4 = i + 1;
            i2++;
            if (i2 >= 50 || str.length() > 1000 || i4 == length) {
                String format = String.format(z[6], Integer.valueOf(length), Integer.valueOf(i3), a.F(), Long.valueOf(a.y()), str + "]");
                ac.b();
                ah.a(context, b.a(z[0], format));
                i3++;
                str = "[";
                i2 = 0;
            }
            i = i4;
        }
    }
}
