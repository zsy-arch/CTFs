package cn.jpush.android.a;

import android.content.Context;
import cn.jpush.android.a;
import cn.jpush.android.util.ac;
import cn.jpush.android.util.ah;
import cn.jpush.android.util.ao;
import cn.jpush.android.util.b;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public final class i extends d {
    private static final String[] B;
    private Context e;
    private String f;
    private boolean g;
    private boolean h;
    private boolean i;
    private String j = null;
    private String k = null;
    private String l = null;

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
            case 2: goto L_0x0055;
            case 3: goto L_0x005d;
            case 4: goto L_0x0066;
            case 5: goto L_0x006e;
            case 6: goto L_0x0077;
            case 7: goto L_0x0080;
            case 8: goto L_0x008a;
            case 9: goto L_0x0096;
            case 10: goto L_0x00a1;
            case 11: goto L_0x00ad;
            case 12: goto L_0x00b8;
            case 13: goto L_0x00c4;
            case 14: goto L_0x00d0;
            case 15: goto L_0x00db;
            case 16: goto L_0x00e6;
            default: goto L_0x003c;
        };
     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x003c, code lost:
        r3[r2] = r1;
        r2 = 1;
        r1 = "@\n.(G{\u0006&&\u0013\u007fYh";
        r0 = 0;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x0044, code lost:
        r3[r2] = r1;
        r2 = 2;
        r1 = "t\u0006$-";
        r0 = 1;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x004c, code lost:
        r3[r2] = r1;
        r2 = 3;
        r1 = "{\f+\u001e\u000ey\u0005'";
        r0 = 2;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:19:0x0055, code lost:
        r3[r2] = r1;
        r2 = 4;
        r1 = "`\n.(8c\f?$\u0015d";
        r0 = 3;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x005d, code lost:
        r3[r2] = r1;
        r2 = 5;
        r1 = "y\u0006<6\be\b\u00175\u001eg\u0006";
        r0 = 4;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x0066, code lost:
        r3[r2] = r1;
        r2 = 6;
        r1 = "[\f+ \u0013~\f&{G";
        r0 = 5;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x006e, code lost:
        r3[r2] = r1;
        r2 = 7;
        r1 = "{\f+\u001e\u0004r\u000f$";
        r0 = 6;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x0077, code lost:
        r3[r2] = r1;
        r2 = '\b';
        r1 = "v\u000f$";
        r0 = 7;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x0080, code lost:
        r3[r2] = r1;
        r2 = '\t';
        r1 = "c\u001a8$";
        r0 = '\b';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x008a, code lost:
        r3[r2] = r1;
        r2 = '\n';
        r1 = "{\f+\u001e\u0010~\u0005!";
        r0 = '\t';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x0096, code lost:
        r3[r2] = r1;
        r2 = 11;
        r1 = "p\u0013;";
        r0 = '\n';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x00a1, code lost:
        r3[r2] = r1;
        r2 = '\f';
        r1 = "{\f+\u001e\u0000g\u0010";
        r0 = 11;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x00ad, code lost:
        r3[r2] = r1;
        r2 = '\r';
        r1 = "d\u0010!%";
        r0 = '\f';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:29:0x00b8, code lost:
        r3[r2] = r1;
        r2 = 14;
        r1 = "{\f+ \u000bH\u0007&2";
        r0 = '\r';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x00c4, code lost:
        r3[r2] = r1;
        r2 = 15;
        r1 = "~\u0017!,\u0002";
        r0 = 14;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:31:0x00d0, code lost:
        r3[r2] = r1;
        r2 = 16;
        r1 = "p\u0013;\u0000\u0003s\u0011-2\u0014-";
        r0 = 15;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x00db, code lost:
        r3[r2] = r1;
        r2 = 17;
        r1 = "t\u0006$-8c\f?$\u0015d";
        r0 = 16;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x00e6, code lost:
        r3[r2] = r1;
        cn.jpush.android.a.i.B = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:34:0x00ea, code lost:
        return;
     */
    /* JADX WARN: Code restructure failed: missing block: B:35:0x00eb, code lost:
        r9 = 23;
     */
    /* JADX WARN: Code restructure failed: missing block: B:36:0x00ef, code lost:
        r9 = 'c';
     */
    /* JADX WARN: Code restructure failed: missing block: B:37:0x00f3, code lost:
        r9 = 'H';
     */
    /* JADX WARN: Code restructure failed: missing block: B:38:0x00f7, code lost:
        r9 = 'A';
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
            case 0: goto L_0x00eb;
            case 1: goto L_0x00ef;
            case 2: goto L_0x00f3;
            case 3: goto L_0x00f7;
            default: goto L_0x001e;
        };
     */
    /* JADX WARN: Code restructure failed: missing block: B:8:0x001e, code lost:
        r9 = 'g';
     */
    /* JADX WARN: Code restructure failed: missing block: B:9:0x0020, code lost:
        r5[r6] = (char) (r9 ^ r10);
        r6 = r8 + 1;
     */
    static {
        /*
            Method dump skipped, instructions count: 302
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.jpush.android.a.i.<clinit>():void");
    }

    public i(Context context, boolean z, String str, boolean z2, boolean z3) {
        super(context, str, z2, z3);
        this.e = context;
        this.f = str;
        this.g = z2;
        this.h = z3;
        this.i = z;
    }

    private boolean a(JSONArray jSONArray, JSONArray jSONArray2, String str) {
        if (ao.a(str)) {
            if (!ao.a(this.l)) {
                return false;
            }
        } else if (!str.equals(this.l)) {
            return false;
        }
        if (!ao.a(this.k)) {
            if (!(jSONArray2 == null || jSONArray2.length() == 0 || !this.k.equals(jSONArray2.toString()))) {
                ac.c();
            }
            return false;
        } else if (!(jSONArray2 == null || jSONArray2.length() == 0)) {
            return false;
        }
        if (!ao.a(this.j)) {
            if (!(jSONArray == null || jSONArray.length() == 0)) {
                try {
                    String optString = ((JSONObject) jSONArray.get(0)).optString(B[13]);
                    if (!ao.a(optString)) {
                        if (!optString.equals(this.j)) {
                            return false;
                        }
                    }
                } catch (Exception e) {
                    return false;
                }
            }
            return false;
        } else if (!(jSONArray == null || jSONArray.length() == 0)) {
            return false;
        }
        return true;
    }

    @Override // cn.jpush.android.a.d
    public final void d() {
        JSONArray jSONArray;
        String a;
        JSONObject a2;
        JSONObject a3;
        try {
            if (this.i) {
                if (this.f.equals(B[17])) {
                    if (a.z() && (a3 = b.a(B[7], b())) != null && a3.length() > 0) {
                        ah.a(this.e, a3);
                        new StringBuilder(B[6]).append(a3);
                        ac.c();
                    }
                } else if (this.f.equals(B[4])) {
                    if (a.z() && (a2 = b.a(B[10], c())) != null && a2.length() > 0) {
                        ah.a(this.e, a2);
                        new StringBuilder(B[1]).append(a2.toString().getBytes().length);
                        ac.c();
                        new StringBuilder(B[6]).append(a2);
                        ac.c();
                    }
                } else if (this.f.equals(B[11])) {
                    if (a.z() && (a = a()) != null && !"".equals(a)) {
                        try {
                            JSONObject jSONObject = new JSONObject(a);
                            JSONArray jSONArray2 = new JSONArray();
                            jSONArray2.put(jSONObject);
                            JSONObject a4 = b.a(B[12], jSONArray2);
                            if (a4 != null && a4.length() > 0) {
                                ah.a(this.e, a4);
                                new StringBuilder(B[6]).append(a4);
                                ac.c();
                            }
                        } catch (JSONException e) {
                            e.getMessage();
                            ac.e();
                        }
                    }
                } else if (this.f.equals(B[8]) && a.z()) {
                    JSONArray c = c();
                    JSONArray b = b();
                    JSONArray jSONArray3 = new JSONArray();
                    String a5 = a();
                    new StringBuilder(B[16]).append(a5);
                    ac.b();
                    if (a(c, b, a5)) {
                        ac.c();
                    } else {
                        if (a5 == null || "".equals(a5)) {
                            jSONArray = jSONArray3;
                        } else {
                            try {
                                jSONArray3.put(new JSONObject(a5));
                                jSONArray = jSONArray3;
                            } catch (Exception e2) {
                                jSONArray = null;
                            }
                        }
                        JSONObject jSONObject2 = new JSONObject();
                        try {
                            jSONObject2.put(B[9], B[3]);
                            jSONObject2.put(B[15], a.m());
                            jSONObject2.put(B[5], b.c(this.e));
                            jSONObject2.put(B[14], b.d());
                            if (c != null && c.length() > 0) {
                                jSONObject2.put(B[0], c);
                                this.j = ((JSONObject) c.get(0)).optString(B[13]);
                            }
                            if (b != null && b.length() > 0) {
                                jSONObject2.put(B[2], b);
                                this.k = b.toString();
                            }
                            if (jSONArray != null && jSONArray.length() > 0) {
                                jSONObject2.put(B[11], jSONArray);
                                this.l = a5;
                            }
                            ah.a(this.e, jSONObject2);
                        } catch (JSONException e3) {
                        }
                    }
                }
            }
        } catch (Exception e4) {
            ac.i();
        } finally {
            g();
        }
    }
}
