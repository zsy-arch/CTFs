package cn.jpush.android.util;

import android.content.Context;
import android.content.SharedPreferences;
import cn.jpush.android.a;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class o {
    private static final String a;
    private static Map<String, String> b;
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
            default: goto L_0x003c;
        };
     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x003c, code lost:
        r3[r2] = r1;
        r2 = 1;
        r1 = "D\u0000\u000f{9C\u0011\u0015{;";
        r0 = 0;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x0044, code lost:
        r3[r2] = r1;
        r2 = 2;
        r1 = "_\u0011\u0015y0";
        r0 = 1;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x004c, code lost:
        r3[r2] = r1;
        r2 = 3;
        r1 = "B\u001c\fq";
        r0 = 2;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:19:0x0054, code lost:
        r3[r2] = r1;
        r2 = 4;
        r1 = "\\\u0015\tg=i\u0001\u0019b<U\u0000#};P\n";
        r0 = 3;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x005c, code lost:
        r3[r2] = r1;
        r2 = 5;
        r1 = "R\u0000\n}6S:\u0015z3Y";
        r0 = 4;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x0064, code lost:
        r3[r2] = r1;
        r2 = 6;
        r1 = "B\f\u0011q/Y\u000b\u0019";
        r0 = 5;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x006c, code lost:
        r3[r2] = r1;
        r2 = 7;
        r1 = "E\u0006\u000eq0X\u0016\u0015n0";
        r0 = 6;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x0074, code lost:
        r3[r2] = r1;
        r2 = '\b';
        r1 = "E\u0001\u0017K#S\u0017\u000f}:X";
        r0 = 7;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x007d, code lost:
        r3[r2] = r1;
        r2 = '\t';
        r1 = "Z\u0004\u0012s W\u0002\u0019";
        r0 = '\b';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x0087, code lost:
        r3[r2] = r1;
        r2 = '\n';
        r1 = "W\u0015\fK#S\u0017\u000f}:X\u000b\u001dy0";
        r0 = '\t';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x0092, code lost:
        r3[r2] = r1;
        r2 = 11;
        r1 = "Y\u0016#b0D\u0016\u0015{;";
        r0 = '\n';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x009d, code lost:
        r3[r2] = r1;
        r2 = '\f';
        r1 = "W\u0015\fK#S\u0017\u000f}:X\u0006\u0013p0";
        r0 = 11;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x00a8, code lost:
        r3[r2] = r1;
        r2 = '\r';
        r1 = "U\r\u001dz;S\t";
        r0 = '\f';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:29:0x00b3, code lost:
        r3[r2] = r1;
        r2 = 14;
        r1 = "[\n\u0018q9";
        r0 = '\r';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x00be, code lost:
        r3[r2] = r1;
        r2 = 15;
        r1 = "W\u0015\fK>S\u001c";
        r0 = 14;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:31:0x00c9, code lost:
        r3[r2] = r1;
        r2 = 16;
        r1 = "\u0015KL";
        r0 = 15;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x00d4, code lost:
        r3[r2] = r1;
        r2 = 17;
        r1 = "\u0004KM:l";
        r0 = 16;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x00df, code lost:
        r3[r2] = r1;
        r2 = 18;
        r1 = "m;\u001d9/wH&$x\u000f:RI";
        r0 = 17;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:34:0x00ea, code lost:
        r3[r2] = r1;
        cn.jpush.android.util.o.z = r3;
        cn.jpush.android.util.o.a = cn.jpush.android.util.o.class.getSimpleName();
        cn.jpush.android.util.o.b = null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:35:0x00f9, code lost:
        return;
     */
    /* JADX WARN: Code restructure failed: missing block: B:36:0x00fa, code lost:
        r9 = '6';
     */
    /* JADX WARN: Code restructure failed: missing block: B:37:0x00fe, code lost:
        r9 = 'e';
     */
    /* JADX WARN: Code restructure failed: missing block: B:38:0x0102, code lost:
        r9 = '|';
     */
    /* JADX WARN: Code restructure failed: missing block: B:39:0x0106, code lost:
        r9 = com.tencent.open.GameAppOperation.PIC_SYMBOLE;
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
            case 0: goto L_0x00fa;
            case 1: goto L_0x00fe;
            case 2: goto L_0x0102;
            case 3: goto L_0x0106;
            default: goto L_0x001e;
        };
     */
    /* JADX WARN: Code restructure failed: missing block: B:8:0x001e, code lost:
        r9 = 'U';
     */
    /* JADX WARN: Code restructure failed: missing block: B:9:0x0020, code lost:
        r5[r6] = (char) (r9 ^ r10);
        r6 = r8 + 1;
     */
    static {
        /*
            Method dump skipped, instructions count: 318
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.jpush.android.util.o.<clinit>():void");
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x00b5  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x00c4  */
    /* JADX WARN: Removed duplicated region for block: B:17:0x00d2  */
    /* JADX WARN: Removed duplicated region for block: B:20:0x00e1  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x00f0  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x00ff  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x010e  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x011d  */
    /* JADX WARN: Removed duplicated region for block: B:35:0x012c  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x013a  */
    /* JADX WARN: Removed duplicated region for block: B:41:0x0149  */
    /* JADX WARN: Removed duplicated region for block: B:44:0x0158  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.util.Map<java.lang.String, java.lang.String> a(android.content.Context r21) {
        /*
            Method dump skipped, instructions count: 430
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.jpush.android.util.o.a(android.content.Context):java.util.Map");
    }

    public static void a(Context context, String str) {
        SharedPreferences.Editor edit = context.getSharedPreferences(z[4], 0).edit();
        edit.putString(z[8], str);
        edit.commit();
    }

    private static void a(Context context, Map<String, String> map) {
        Set<String> keySet;
        if (!(map == null || (keySet = map.keySet()) == null || keySet.size() <= 0)) {
            SharedPreferences.Editor edit = context.getSharedPreferences(z[4], 0).edit();
            for (String str : keySet) {
                edit.putString(str, map.get(str));
            }
            edit.commit();
        }
    }

    public static String b(Context context) {
        return context.getSharedPreferences(z[4], 0).getString(z[8], null);
    }

    public static void c(Context context) {
        Map<String, String> a2;
        ac.b();
        if (a.z() && (a2 = a(context)) != null && !a2.isEmpty()) {
            if (b == null) {
                HashMap hashMap = new HashMap();
                SharedPreferences sharedPreferences = context.getSharedPreferences(z[4], 0);
                String string = sharedPreferences.getString(z[0], null);
                String string2 = sharedPreferences.getString(z[1], null);
                String string3 = sharedPreferences.getString(z[13], null);
                String string4 = sharedPreferences.getString(z[15], null);
                String string5 = sharedPreferences.getString(z[12], null);
                String string6 = sharedPreferences.getString(z[10], null);
                String string7 = sharedPreferences.getString(z[9], null);
                String string8 = sharedPreferences.getString(z[11], null);
                String string9 = sharedPreferences.getString(z[6], null);
                String string10 = sharedPreferences.getString(z[8], null);
                String string11 = sharedPreferences.getString(z[14], null);
                String string12 = sharedPreferences.getString(z[7], null);
                if (!ao.a(string)) {
                    hashMap.put(z[0], string);
                }
                if (!ao.a(string2)) {
                    hashMap.put(z[1], string2);
                }
                if (!ao.a(string3)) {
                    hashMap.put(z[13], string3);
                }
                if (!ao.a(string4)) {
                    hashMap.put(z[15], string4);
                }
                if (!ao.a(string5)) {
                    hashMap.put(z[12], string5);
                }
                if (!ao.a(string6)) {
                    hashMap.put(z[10], string6);
                }
                if (!ao.a(string8)) {
                    hashMap.put(z[11], string8);
                }
                if (!ao.a(string7)) {
                    hashMap.put(z[9], string7);
                }
                if (!ao.a(string9)) {
                    hashMap.put(z[6], string9);
                }
                if (!ao.a(string10)) {
                    hashMap.put(z[8], string10);
                }
                if (!ao.a(string11)) {
                    hashMap.put(z[14], string11);
                }
                if (!ao.a(string12)) {
                    hashMap.put(z[7], string12);
                }
                b = hashMap;
            }
            Map<String, String> map = b;
            if ((map == null || map.isEmpty()) ? true : !a2.equals(map)) {
                b = a2;
                a(context, a2);
                try {
                    JSONObject jSONObject = new JSONObject(a2);
                    jSONObject.put(z[2], a.m());
                    jSONObject.put(z[3], z[5]);
                    ah.a(context, jSONObject);
                } catch (JSONException e) {
                    e.getMessage();
                    ac.e();
                }
            }
        }
    }
}
