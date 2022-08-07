package cn.jpush.android.util;

import android.content.Context;
import android.database.Cursor;
import cn.jpush.android.a;
import cn.jpush.android.data.o;
import cn.jpush.android.data.q;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public final class u {
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
            default: goto L_0x003c;
        };
     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x003c, code lost:
        r3[r2] = r1;
        r2 = 1;
        r1 = "\u0002O\u0015C";
        r0 = 0;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x0044, code lost:
        r3[r2] = r1;
        r2 = 2;
        r1 = "\u0012W\u0011C";
        r0 = 1;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x004c, code lost:
        r3[r2] = r1;
        r2 = 3;
        r1 = "\u0005R\u000ey\u001e\u0018R\u0000^";
        r0 = 2;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:19:0x0054, code lost:
        r3[r2] = r1;
        r2 = 4;
        r1 = "\u0005C\u0006E\u0012\u0013R:R\u0018\u0006";
        r0 = 3;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x005c, code lost:
        r3[r2] = r1;
        r2 = 5;
        r1 = "\u0010W\fJ\u0012\u0012i\u0011I\u0007";
        r0 = 4;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x0064, code lost:
        r3[r2] = r1;
        r2 = 6;
        r1 = "\u000fO\u001c_Z;{HB\u0013";
        r0 = 5;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x006c, code lost:
        r3[r2] = r1;
        r2 = 7;
        r1 = "\u001aY\u0002O\u0019)P\u0004O\u001b\u0013R";
        r0 = 6;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x0074, code lost:
        r3[r2] = r1;
        r2 = '\b';
        r1 = "\u001aY\u0002O\u0019)B\nR\u0016\u001a";
        r0 = 7;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x007d, code lost:
        r3[r2] = r1;
        r2 = '\t';
        r1 = "V\u0016E\u0006\u001b\u0019Q\fH(\u0015Y\u0016R\u001e\u001bS_\u0006";
        r0 = '\b';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x0087, code lost:
        r3[r2] = r1;
        r2 = '\n';
        r1 = "V_\u0016\u0006\u0012\u000e_\u0016R\u0004V\fES\u0007\u0012W\u0011C";
        r0 = '\t';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x0092, code lost:
        r3[r2] = r1;
        r2 = 11;
        r1 = "VX\nRW\u0013N\fU\u0003\u0005\u0016_\u0006\u001e\u0018E\u0000T\u0003";
        r0 = '\n';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x009d, code lost:
        r3[r2] = r1;
        r2 = '\f';
        r1 = "\u0005Y\u0017R(\u001dS\u001c\u0006MV";
        r0 = 11;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x00a8, code lost:
        r3[r2] = r1;
        r2 = '\r';
        r1 = "\u0005Y\u0017R(\u001dS\u001c\u001c";
        r0 = '\f';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:29:0x00b3, code lost:
        r3[r2] = r1;
        r2 = 14;
        r1 = "\u0015Y\u000bH(\u001fF";
        r0 = '\r';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x00be, code lost:
        r3[r2] = r1;
        r2 = 15;
        r1 = "\u0002_\bC\u0004";
        r0 = 14;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:31:0x00c9, code lost:
        r3[r2] = r1;
        r2 = 16;
        r1 = "\u001aY\u0006G\u001b)R\u000bU";
        r0 = 15;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x00d4, code lost:
        r3[r2] = r1;
        r2 = 17;
        r1 = "\u0005Y\u0010T\u0014\u0013";
        r0 = 16;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x00df, code lost:
        r3[r2] = r1;
        r2 = 18;
        r1 = "\u0018S\u0011";
        r0 = 17;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:34:0x00ea, code lost:
        r3[r2] = r1;
        r2 = 19;
        r1 = "\u0015Y\u0010H\u0003)\u0005:";
        r0 = 18;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:35:0x00f5, code lost:
        r3[r2] = r1;
        r2 = com.tencent.open.GameAppOperation.PIC_SYMBOLE;
        r1 = "\u0005C\u0006E\u0012\u0005E:B\u0012\u0002W\fJ\u0004";
        r0 = 19;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:36:0x0100, code lost:
        r3[r2] = r1;
        r2 = 21;
        r1 = "\u0015Y\u0010H\u0003)\u0007:\u0015";
        r0 = com.tencent.open.GameAppOperation.PIC_SYMBOLE;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:37:0x010b, code lost:
        r3[r2] = r1;
        r2 = 22;
        r1 = "\u0015Y\u0010H\u0003)\u0006:\u0017";
        r0 = 21;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:38:0x0116, code lost:
        r3[r2] = r1;
        cn.jpush.android.util.u.z = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:39:0x011a, code lost:
        return;
     */
    /* JADX WARN: Code restructure failed: missing block: B:40:0x011b, code lost:
        r9 = 'v';
     */
    /* JADX WARN: Code restructure failed: missing block: B:41:0x011f, code lost:
        r9 = '6';
     */
    /* JADX WARN: Code restructure failed: missing block: B:42:0x0123, code lost:
        r9 = 'e';
     */
    /* JADX WARN: Code restructure failed: missing block: B:43:0x0127, code lost:
        r9 = '&';
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
            case 0: goto L_0x011b;
            case 1: goto L_0x011f;
            case 2: goto L_0x0123;
            case 3: goto L_0x0127;
            default: goto L_0x001e;
        };
     */
    /* JADX WARN: Code restructure failed: missing block: B:8:0x001e, code lost:
        r9 = 'w';
     */
    /* JADX WARN: Code restructure failed: missing block: B:9:0x0020, code lost:
        r5[r6] = (char) (r9 ^ r10);
        r6 = r8 + 1;
     */
    static {
        /*
            Method dump skipped, instructions count: 360
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.jpush.android.util.u.<clinit>():void");
    }

    private static JSONObject a(q qVar) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(z[18], qVar.b());
            jSONObject.put(z[14], qVar.c());
            jSONObject.put(z[16], qVar.d());
            jSONObject.put(z[17], qVar.e());
            jSONObject.put(z[15], qVar.g());
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put(z[22], qVar.h());
            jSONObject2.put(z[21], qVar.i());
            jSONObject2.put(z[19], qVar.j());
            jSONObject.put(z[20], jSONObject2);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jSONObject;
    }

    public static void a(Context context) {
        if (context != null) {
            b(context);
            c(context);
        }
    }

    public static synchronized void a(Context context, int i, long j, int i2) {
        Cursor cursor;
        Cursor cursor2;
        Throwable th;
        synchronized (u.class) {
            ac.c();
            String t = b.t(context);
            String h = a.h() == null ? "" : a.h();
            String str = t + "_" + i + "_" + h + "_" + b.d();
            new StringBuilder(z[12]).append(str).append(z[9]).append(j);
            ac.b();
            int i3 = 0;
            int i4 = 0;
            int i5 = 0;
            if (i2 == 0) {
                if (j <= 1000) {
                    i3 = 1;
                }
                if (j > 1000 && j <= 3000) {
                    i4 = 1;
                }
                if (j > 3000) {
                    i5 = 1;
                }
            }
            o a = o.a(context);
            if (a != null) {
                if (!a.a(true)) {
                    ac.e();
                } else {
                    if (a.a(str)) {
                        new StringBuilder(z[13]).append(str).append(z[10]);
                        ac.c();
                        try {
                            cursor2 = a.b(str);
                            if (cursor2 == null) {
                                try {
                                    ac.d();
                                } catch (Exception e) {
                                    cursor = cursor2;
                                    if (cursor != null) {
                                        cursor.close();
                                    }
                                    a.a();
                                } catch (Throwable th2) {
                                    th = th2;
                                    if (cursor2 != null) {
                                        cursor2.close();
                                    }
                                    throw th;
                                }
                            }
                            q a2 = a.a(cursor2);
                            if (a2 != null) {
                                a.b(str, t, h, b.d(), new StringBuilder().append(i).toString(), a2.f() + i2, a2.g() + 1, i3 + a2.h(), i4 + a2.i(), i5 + a2.j(), a2.k() + 0);
                            } else {
                                ac.d();
                            }
                            if (cursor2 != null) {
                                cursor2.close();
                            }
                        } catch (Exception e2) {
                            cursor = null;
                        } catch (Throwable th3) {
                            th = th3;
                            cursor2 = null;
                        }
                    } else {
                        new StringBuilder(z[13]).append(str).append(z[11]);
                        ac.c();
                        a.a(str, t, h, b.d(), new StringBuilder().append(i).toString(), i2, 1, i3, i4, i5, 0);
                    }
                    a.a();
                }
            }
        }
    }

    private static JSONObject b(q qVar) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(z[18], qVar.b());
            jSONObject.put(z[14], qVar.c());
            jSONObject.put(z[16], qVar.d());
            jSONObject.put(z[17], qVar.e());
            jSONObject.put(z[15], qVar.g());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jSONObject;
    }

    private static synchronized void b(Context context) {
        Throwable th;
        Exception e;
        Cursor cursor = null;
        synchronized (u.class) {
            if (a.z()) {
                try {
                    JSONObject jSONObject = new JSONObject();
                    o a = o.a(context);
                    try {
                        jSONObject.put(z[0], a.m());
                        jSONObject.put(z[1], z[3]);
                        jSONObject.put(z[2], new SimpleDateFormat(z[6]).format(new Date(a.g())));
                        if (!a.a(false)) {
                            ac.e();
                            if (a != null) {
                                a.a();
                            }
                        } else {
                            jSONObject.put(z[8], a.b(true));
                            jSONObject.put(z[7], a.b(false));
                            JSONArray jSONArray = new JSONArray();
                            Cursor c = a.c();
                            if (c != null) {
                                do {
                                    try {
                                        q a2 = a.a(c);
                                        if (a2 == null || ao.a(a2.a())) {
                                            ac.b();
                                        } else {
                                            jSONArray.put(b(a2));
                                        }
                                    } catch (JSONException e2) {
                                        e = e2;
                                        cursor = c;
                                        ac.e();
                                        e.printStackTrace();
                                        if (cursor != null) {
                                            cursor.close();
                                        }
                                        if (a != null) {
                                            a.a();
                                        }
                                    } catch (Exception e3) {
                                        e = e3;
                                        cursor = c;
                                        ac.e();
                                        e.printStackTrace();
                                        if (cursor != null) {
                                            cursor.close();
                                        }
                                        if (a != null) {
                                            a.a();
                                        }
                                    } catch (Throwable th2) {
                                        th = th2;
                                        cursor = c;
                                        if (cursor != null) {
                                            cursor.close();
                                        }
                                        if (a != null) {
                                            a.a();
                                        }
                                        throw th;
                                    }
                                } while (c.moveToNext());
                                c.close();
                            }
                            jSONObject.put(z[5], jSONArray);
                            JSONArray jSONArray2 = new JSONArray();
                            Cursor d = a.d();
                            if (d != null) {
                                do {
                                    q a3 = a.a(d);
                                    if (a3 == null || ao.a(a3.a())) {
                                        ac.b();
                                    } else {
                                        jSONArray2.put(a(a3));
                                    }
                                } while (d.moveToNext());
                                d.close();
                            }
                            jSONObject.put(z[4], jSONArray2);
                            a.a();
                            ah.a(context, jSONObject);
                            if (d != null) {
                                d.close();
                            }
                            if (a != null) {
                                a.a();
                            }
                        }
                    } catch (JSONException e4) {
                        e = e4;
                    } catch (Exception e5) {
                        e = e5;
                    }
                } catch (Throwable th3) {
                    th = th3;
                }
            }
        }
    }

    private static synchronized void c(Context context) {
        synchronized (u.class) {
            o a = o.a(context);
            if (a != null) {
                a.a(true);
                a.b();
                a.a();
            }
        }
    }
}
