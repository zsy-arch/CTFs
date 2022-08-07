package cn.jpush.android.util;

import android.content.Context;
import cn.jpush.android.a;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.zip.GZIPOutputStream;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class ah {
    public static JSONObject a;
    private static final String b;
    private static String c;
    private static String d;
    private static String e;
    private static final String f;
    private static ExecutorService g;
    private static Object h;
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
            case 0: goto L_0x0045;
            case 1: goto L_0x004e;
            case 2: goto L_0x0056;
            case 3: goto L_0x005f;
            case 4: goto L_0x0067;
            case 5: goto L_0x006f;
            case 6: goto L_0x0077;
            case 7: goto L_0x0080;
            case 8: goto L_0x008a;
            case 9: goto L_0x0095;
            case 10: goto L_0x00a0;
            case 11: goto L_0x00ab;
            case 12: goto L_0x00b7;
            case 13: goto L_0x00c2;
            case 14: goto L_0x00cd;
            case 15: goto L_0x00d9;
            case 16: goto L_0x00e4;
            case 17: goto L_0x00ef;
            case 18: goto L_0x00fa;
            case 19: goto L_0x0105;
            case 20: goto L_0x0110;
            case 21: goto L_0x011b;
            case 22: goto L_0x0126;
            case 23: goto L_0x0131;
            case 24: goto L_0x013c;
            case 25: goto L_0x0147;
            case 26: goto L_0x0152;
            case 27: goto L_0x015d;
            case 28: goto L_0x0168;
            case 29: goto L_0x0173;
            case 30: goto L_0x017e;
            case 31: goto L_0x0189;
            case 32: goto L_0x0194;
            case 33: goto L_0x019f;
            case 34: goto L_0x01aa;
            case 35: goto L_0x01b5;
            case 36: goto L_0x01c0;
            case 37: goto L_0x01cb;
            case 38: goto L_0x01d6;
            case 39: goto L_0x01e2;
            case 40: goto L_0x01ee;
            case 41: goto L_0x01f9;
            default: goto L_0x003c;
        };
     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x003c, code lost:
        r3[r2] = r1;
        r2 = 1;
        r1 = "~\u0002$HrK\u0001%ZnK\u00110Xrq-9Ri`\u001d#B4~\u0001>U";
        r0 = 0;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x0045, code lost:
        r3[r2] = r1;
        r2 = 2;
        r1 = "~\u0002$HrK\u0001%ZnK\u00110Xrq\\;Huz";
        r0 = 1;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x004e, code lost:
        r3[r2] = r1;
        r2 = 3;
        r1 = "g\u001b\"\u001bhq\u0002>In4\u0007#W ";
        r0 = 2;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:19:0x0056, code lost:
        r3[r2] = r1;
        r2 = 4;
        r1 = "|\u0006%K ;]";
        r0 = 3;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x005f, code lost:
        r3[r2] = r1;
        r2 = 5;
        r1 = "w\u001e4Zh\\\u001b\"Ou`\u000b\u0017Rvq1>Unq\u001c%\u001b\u007f.";
        r0 = 4;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x0067, code lost:
        r3[r2] = r1;
        r2 = 6;
        r1 = "p\u0017=^nqR7RvqR\"Nyw\u0017\"H:r\u001b=^tu\u001f4\u0001";
        r0 = 5;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x006f, code lost:
        r3[r2] = r1;
        r2 = 7;
        r1 = "A&\u0017\u0016\"";
        r0 = 6;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x0077, code lost:
        r3[r2] = r1;
        r2 = '\b';
        r1 = "f\u0017!Th`R'R{4\u00018U}x\u0017qXru\u001c?^v.";
        r0 = 7;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x0080, code lost:
        r3[r2] = r1;
        r2 = '\t';
        r1 = "a\u00067\u0016\"";
        r0 = '\b';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x008a, code lost:
        r3[r2] = r1;
        r2 = '\n';
        r1 = "d\u0007%\u001by{\u001c%^t`R4Cyq\u0002%Ruz^q\\sb\u0017qNj4\u00014U~4\u001e>\\ ";
        r0 = '\t';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x0095, code lost:
        r3[r2] = r1;
        r2 = 11;
        r1 = "4\u001b%^wgH";
        r0 = '\n';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x00a0, code lost:
        r3[r2] = r1;
        r2 = '\f';
        r1 = "c\u00000K:w\u001d?O{}\u001c4I:q\n2^j`\u001b>U64\u00158M\u007f4\u0007!\u001biq\u001c5\u001bv{\u0015k";
        r0 = 11;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x00ab, code lost:
        r3[r2] = r1;
        r2 = '\r';
        r1 = "x\u001d6\u001bi}\b4\u0001";
        r0 = '\f';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:29:0x00b7, code lost:
        r3[r2] = r1;
        r2 = 14;
        r1 = "g\u0017?_:x\u001d6\u001bix\u001b2^ ";
        r0 = '\r';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x00c2, code lost:
        r3[r2] = r1;
        r2 = 15;
        r1 = "w\u001d?O\u007fl\u0006k";
        r0 = 14;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:31:0x00cd, code lost:
        r3[r2] = r1;
        r2 = 16;
        r1 = "|\u001b\"Ouf\u000b\u000e]sx\u0017";
        r0 = 15;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x00d9, code lost:
        r3[r2] = r1;
        r2 = 17;
        r1 = "w\u0007#I\u007fz\u0006\u000eH\u007fg\u00018TtK\u00148W\u007f";
        r0 = 16;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x00e4, code lost:
        r3[r2] = r1;
        r2 = 18;
        r1 = "w\u0013?\u001cn4\u001d!^t4";
        r0 = 17;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:34:0x00ef, code lost:
        r3[r2] = r1;
        r2 = 19;
        r1 = "4\u001d$Oja\u0006\u0002Ohq\u0013<\u0017:s\u001b'^:a\u0002qH{b\u0017q\u0001";
        r0 = 18;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:35:0x00fa, code lost:
        r3[r2] = r1;
        r2 = com.tencent.open.GameAppOperation.PIC_SYMBOLE;
        r1 = "4^q\\sb\u0017qNj4\u00010M\u007f4H";
        r0 = 19;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:36:0x0105, code lost:
        r3[r2] = r1;
        r2 = 21;
        r1 = "w\u0013?\u001cn4\u0017?Xup\u001b?\\:";
        r0 = com.tencent.open.GameAppOperation.PIC_SYMBOLE;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:37:0x0110, code lost:
        r3[r2] = r1;
        r2 = 22;
        r1 = "R\u001b=^ju\u00069\u001b\u007ff\u0000>I:{\u0014q`";
        r0 = 21;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:38:0x011b, code lost:
        r3[r2] = r1;
        r2 = 23;
        r1 = "IR}\u001b}}\u00044\u001bodR\"ZlqRk";
        r0 = 22;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:39:0x0126, code lost:
        r3[r2] = r1;
        r2 = 24;
        r1 = "w\u0013?\u001cn4\u0005#RnqR";
        r0 = 23;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:40:0x0131, code lost:
        r3[r2] = r1;
        r2 = 25;
        r1 = "g\u0013'^:x\u001d6\u001bszR&Is`\u0017\u0019Ri`\u001d#BV{\u0015k1";
        r0 = 24;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:41:0x013c, code lost:
        r3[r2] = r1;
        r2 = 26;
        r1 = "w\u001d?O\u007fl\u0006qRi4\u001c$Wv4^q\\sb\u0017qNj4\u00010M\u007f4";
        r0 = 25;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:42:0x0147, code lost:
        r3[r2] = r1;
        r2 = 27;
        r1 = "8R6RlqR$K:f\u00170_:.";
        r0 = 26;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:43:0x0152, code lost:
        r3[r2] = r1;
        r2 = 28;
        r1 = "w\u001d?O\u007fl\u0006qRi4\u001c$Wv4^q\\sb\u0017qNj4\u00004Z~4";
        r0 = 27;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:44:0x015d, code lost:
        r3[r2] = r1;
        r2 = 29;
        r1 = "4\u001b\"\u001bta\u001e=\u0017:f\u0017%NhzR?Nvx";
        r0 = 28;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:45:0x0168, code lost:
        r3[r2] = r1;
        r2 = 30;
        r1 = "w\u0013?\u001cn4\u00004Z~4";
        r0 = 29;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:46:0x0173, code lost:
        r3[r2] = r1;
        r2 = 31;
        r1 = "4\u001b?Ko`!%I\u007fu\u001f}\u001b}}\u00044\u001bodR#^{pRq\u0001";
        r0 = 30;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:47:0x017e, code lost:
        r3[r2] = r1;
        r2 = ' ';
        r1 = "w\u0013?\u001cn4\u0010$RvpR";
        r0 = 31;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:48:0x0189, code lost:
        r3[r2] = r1;
        r2 = '!';
        r1 = "4\u001b?Ou48\"Tt[\u0010;^y`^q\\sb\u0017qNj4\u00004Z~4H";
        r0 = ' ';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:49:0x0194, code lost:
        r3[r2] = r1;
        r2 = '\"';
        r1 = "a\u001b5";
        r0 = '!';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:4:0x0010, code lost:
        if (r5 <= 1) goto L_0x0012;
     */
    /* JADX WARN: Code restructure failed: missing block: B:50:0x019f, code lost:
        r3[r2] = r1;
        r2 = '#';
        r1 = "&\\`\u0015#";
        r0 = '\"';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:51:0x01aa, code lost:
        r3[r2] = r1;
        r2 = '$';
        r1 = "u\u0002!dqq\u000b";
        r0 = '#';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:52:0x01b5, code lost:
        r3[r2] = r1;
        r2 = '%';
        r1 = "d\u001e0O|{\u0000<";
        r0 = '$';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:53:0x01c0, code lost:
        r3[r2] = r1;
        r2 = '&';
        r1 = "g\u0016:dlq\u0000";
        r0 = '%';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:54:0x01cb, code lost:
        r3[r2] = r1;
        r2 = '\'';
        r1 = "4\u00020Ing";
        r0 = '&';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:55:0x01d6, code lost:
        r3[r2] = r1;
        r2 = '(';
        r1 = "x\u001d6\u001b~}\u00048_\u007fpR8Un{R";
        r0 = '\'';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:56:0x01e2, code lost:
        r3[r2] = r1;
        r2 = ')';
        r1 = "|\u0006%Ki.]~";
        r0 = '(';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:57:0x01ee, code lost:
        r3[r2] = r1;
        r2 = '*';
        r1 = "g\u00060Oi:\u0018!Ni|\\2U";
        r0 = ')';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:58:0x01f9, code lost:
        r3[r2] = r1;
        cn.jpush.android.util.ah.z = r3;
        cn.jpush.android.util.ah.b = cn.jpush.android.util.ah.class.getSimpleName();
        cn.jpush.android.util.ah.c = "";
        r1 = ";\u0004`\u0014hq\u0002>In";
        r0 = 65535;
     */
    /* JADX WARN: Code restructure failed: missing block: B:59:0x020c, code lost:
        r1 = r1.toCharArray();
        r2 = r1.length;
        r3 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:5:0x0012, code lost:
        r8 = r6;
        r5 = r1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:60:0x0213, code lost:
        if (r2 > 1) goto L_0x024d;
     */
    /* JADX WARN: Code restructure failed: missing block: B:61:0x0215, code lost:
        r5 = r3;
        r2 = r1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:62:0x021a, code lost:
        r7 = r2[r3];
     */
    /* JADX WARN: Code restructure failed: missing block: B:63:0x021e, code lost:
        switch((r5 % 5)) {
            case 0: goto L_0x023f;
            case 1: goto L_0x0242;
            case 2: goto L_0x0245;
            case 3: goto L_0x0248;
            default: goto L_0x0221;
        };
     */
    /* JADX WARN: Code restructure failed: missing block: B:64:0x0221, code lost:
        r6 = 26;
     */
    /* JADX WARN: Code restructure failed: missing block: B:65:0x0223, code lost:
        r2[r3] = (char) (r6 ^ r7);
        r3 = r5 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:66:0x0229, code lost:
        if (r2 != 0) goto L_0x024b;
     */
    /* JADX WARN: Code restructure failed: missing block: B:67:0x022b, code lost:
        r2 = r1;
        r5 = r3;
        r3 = r2;
     */
    /* JADX WARN: Code restructure failed: missing block: B:68:0x022f, code lost:
        r9 = com.tencent.open.GameAppOperation.PIC_SYMBOLE;
     */
    /* JADX WARN: Code restructure failed: missing block: B:69:0x0233, code lost:
        r9 = 'r';
     */
    /* JADX WARN: Code restructure failed: missing block: B:6:0x0017, code lost:
        r10 = r5[r6];
     */
    /* JADX WARN: Code restructure failed: missing block: B:70:0x0237, code lost:
        r9 = 'Q';
     */
    /* JADX WARN: Code restructure failed: missing block: B:71:0x023b, code lost:
        r9 = ';';
     */
    /* JADX WARN: Code restructure failed: missing block: B:72:0x023f, code lost:
        r6 = com.tencent.open.GameAppOperation.PIC_SYMBOLE;
     */
    /* JADX WARN: Code restructure failed: missing block: B:73:0x0242, code lost:
        r6 = 'r';
     */
    /* JADX WARN: Code restructure failed: missing block: B:74:0x0245, code lost:
        r6 = 'Q';
     */
    /* JADX WARN: Code restructure failed: missing block: B:75:0x0248, code lost:
        r6 = ';';
     */
    /* JADX WARN: Code restructure failed: missing block: B:76:0x024b, code lost:
        r2 = r2;
        r1 = r1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:77:0x024d, code lost:
        if (r2 > r3) goto L_0x0215;
     */
    /* JADX WARN: Code restructure failed: missing block: B:78:0x024f, code lost:
        r1 = new java.lang.String(r1).intern();
     */
    /* JADX WARN: Code restructure failed: missing block: B:79:0x0258, code lost:
        switch(r0) {
            case 0: goto L_0x0261;
            default: goto L_0x025b;
        };
     */
    /* JADX WARN: Code restructure failed: missing block: B:7:0x001b, code lost:
        switch((r8 % 5)) {
            case 0: goto L_0x022f;
            case 1: goto L_0x0233;
            case 2: goto L_0x0237;
            case 3: goto L_0x023b;
            default: goto L_0x001e;
        };
     */
    /* JADX WARN: Code restructure failed: missing block: B:80:0x025b, code lost:
        cn.jpush.android.util.ah.d = r1;
        r1 = ";\u0004c\u0014hq\u0002>In";
        r0 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:81:0x0261, code lost:
        cn.jpush.android.util.ah.e = r1;
        cn.jpush.android.util.ah.f = cn.jpush.android.util.ah.z[42] + cn.jpush.android.util.ah.d;
        cn.jpush.android.util.ah.g = java.util.concurrent.Executors.newSingleThreadExecutor();
        cn.jpush.android.util.ah.a = null;
        cn.jpush.android.util.ah.h = new java.lang.Object();
     */
    /* JADX WARN: Code restructure failed: missing block: B:82:0x028a, code lost:
        return;
     */
    /* JADX WARN: Code restructure failed: missing block: B:8:0x001e, code lost:
        r9 = 26;
     */
    /* JADX WARN: Code restructure failed: missing block: B:9:0x0020, code lost:
        r5[r6] = (char) (r9 ^ r10);
        r6 = r8 + 1;
     */
    static {
        /*
            Method dump skipped, instructions count: 770
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.jpush.android.util.ah.<clinit>():void");
    }

    public static String a() {
        long y = a.y();
        if (y == 0) {
            ac.b();
            return null;
        }
        String b2 = b.b(y + b.b(a.B()));
        if (!ao.a(b2)) {
            return y + ":" + b2;
        }
        return null;
    }

    public static String a(int i) {
        try {
            InetAddress.getByName(z[42]);
            return z[41] + z[42] + e;
        } catch (Exception e2) {
            ac.h();
            return b();
        }
    }

    public static String a(String str) {
        if (ao.a(str)) {
            ac.d();
            return "";
        }
        if (!str.startsWith(z[4])) {
            str = z[4] + str;
        }
        if (!str.endsWith(e)) {
            str = str + e;
        }
        c = str;
        new StringBuilder(z[3]).append(c);
        ac.a();
        return c;
    }

    public static String a(String str, int i) {
        if (ao.a(str)) {
            ac.b();
            return null;
        }
        String d2 = d(str);
        long y = a.y();
        if (y == 0) {
            ac.b();
            return null;
        }
        String b2 = b.b(y + b.b(a.B()) + d2);
        if (!ao.a(b2)) {
            return y + ":" + b2;
        }
        return null;
    }

    private static ArrayList<JSONArray> a(JSONArray jSONArray, int i) {
        UnsupportedEncodingException e2;
        ArrayList<JSONArray> arrayList = new ArrayList<>();
        JSONArray jSONArray2 = new JSONArray();
        if (jSONArray != null && jSONArray.length() > 0) {
            int i2 = 0;
            int i3 = 0;
            for (int length = jSONArray.length() - 1; length >= 0; length--) {
                JSONObject optJSONObject = jSONArray.optJSONObject(length);
                if (optJSONObject != null) {
                    try {
                        int length2 = optJSONObject.toString().getBytes(z[7]).length;
                        int i4 = i3 + length2;
                        if (i4 > 204800 && length > 1) {
                            if (length > 1) {
                                return arrayList;
                            }
                            if (length == 1) {
                                try {
                                    jSONArray2.put(optJSONObject);
                                    arrayList.add(jSONArray2);
                                    return arrayList;
                                } catch (UnsupportedEncodingException e3) {
                                    e2 = e3;
                                    jSONArray2 = jSONArray2;
                                    i3 = i4;
                                    i2 = i2;
                                    e2.getMessage();
                                    ac.e();
                                }
                            }
                        }
                        if (i2 + length2 > 20480) {
                            try {
                                arrayList.add(jSONArray2);
                                jSONArray2 = new JSONArray();
                                try {
                                    jSONArray2.put(optJSONObject);
                                    i3 = i4;
                                    i2 = length2;
                                } catch (UnsupportedEncodingException e4) {
                                    e2 = e4;
                                    i3 = i4;
                                    i2 = length2;
                                    e2.getMessage();
                                    ac.e();
                                }
                            } catch (UnsupportedEncodingException e5) {
                                e2 = e5;
                                jSONArray2 = jSONArray2;
                                i3 = i4;
                                i2 = length2;
                            }
                        } else {
                            int i5 = i2 + length2;
                            try {
                                jSONArray2.put(optJSONObject);
                                i3 = i4;
                                i2 = i5;
                                jSONArray2 = jSONArray2;
                            } catch (UnsupportedEncodingException e6) {
                                e2 = e6;
                                i3 = i4;
                                i2 = i5;
                                jSONArray2 = jSONArray2;
                                e2.getMessage();
                                ac.e();
                            }
                        }
                    } catch (UnsupportedEncodingException e7) {
                        e2 = e7;
                        i2 = i2;
                        jSONArray2 = jSONArray2;
                        i3 = i3;
                    }
                } else {
                    i2 = i2;
                    jSONArray2 = jSONArray2;
                    i3 = i3;
                }
            }
            if (jSONArray2.length() > 0) {
                arrayList.add(jSONArray2);
            }
        }
        return arrayList;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static JSONObject a(Context context, String str) {
        FileInputStream fileInputStream;
        FileInputStream fileInputStream2;
        IOException e2;
        FileNotFoundException e3;
        char c2 = 27;
        JSONObject jSONObject = null;
        if (str == null || str.length() <= 0) {
            ac.b();
        } else {
            String e4 = e(str);
            try {
                if (context == null) {
                    new StringBuilder(z[28]).append(e4);
                    ac.b();
                } else {
                    try {
                        fileInputStream2 = context.openFileInput(str);
                        try {
                            byte[] bArr = new byte[fileInputStream2.available() + 1];
                            fileInputStream2.read(bArr);
                            a(fileInputStream2);
                            try {
                                String str2 = new String(bArr, z[7]);
                                if (ao.a(str2)) {
                                    StringBuilder append = new StringBuilder().append(e4);
                                    String str3 = z[29];
                                    append.append(str3);
                                    ac.b();
                                    fileInputStream = str3;
                                    e4 = e4;
                                } else {
                                    jSONObject = new JSONObject(str2);
                                    fileInputStream = str2;
                                    e4 = e4;
                                }
                            } catch (UnsupportedEncodingException e5) {
                                StringBuilder append2 = new StringBuilder(z[21]).append(e4);
                                String str4 = z[c2];
                                StringBuilder append3 = append2.append(str4);
                                append3.append(e5.getMessage());
                                ac.b();
                                fileInputStream = append3;
                                e4 = str4;
                            } catch (JSONException e6) {
                                StringBuilder append4 = new StringBuilder(z[32]).append(e4);
                                String str5 = z[33];
                                StringBuilder append5 = append4.append(str5);
                                append5.append(e6.getMessage());
                                ac.b();
                                fileInputStream = append5;
                                e4 = str5;
                            }
                        } catch (FileNotFoundException e7) {
                            e3 = e7;
                            c2 = 18;
                            StringBuilder append6 = new StringBuilder(z[18]).append(e4).append(z[31]);
                            append6.append(e3.getMessage());
                            ac.b();
                            a(fileInputStream2);
                            fileInputStream = fileInputStream2;
                            e4 = append6;
                            return jSONObject;
                        } catch (IOException e8) {
                            e2 = e8;
                            c2 = 30;
                            StringBuilder append7 = new StringBuilder(z[30]).append(e4).append(z[27]);
                            append7.append(e2.getMessage());
                            ac.b();
                            a(fileInputStream2);
                            fileInputStream = fileInputStream2;
                            e4 = append7;
                            return jSONObject;
                        }
                    } catch (FileNotFoundException e9) {
                        e3 = e9;
                        fileInputStream2 = null;
                    } catch (IOException e10) {
                        e2 = e10;
                        fileInputStream2 = null;
                    } catch (Throwable th) {
                        th = th;
                        fileInputStream = null;
                        a(fileInputStream);
                        throw th;
                    }
                }
            } catch (Throwable th2) {
                th = th2;
            }
        }
        return jSONObject;
    }

    public static void a(Context context) {
        ac.b();
        a(context, z[2], (JSONObject) null);
        b(context);
    }

    public static void a(Context context, int i) {
        int i2;
        JSONArray optJSONArray;
        JSONObject jSONObject;
        int i3 = 0;
        if (a != null) {
            JSONObject jSONObject2 = a;
            if (i >= 204800) {
                b(context);
                return;
            }
            try {
                i2 = jSONObject2.toString().getBytes(z[9]).length;
            } catch (UnsupportedEncodingException e2) {
                i2 = 0;
            }
            int i4 = (i2 + i) - 204800;
            if (i4 > 0 && (optJSONArray = jSONObject2.optJSONArray(z[0])) != null && optJSONArray.length() > 0) {
                try {
                    JSONArray jSONArray = new JSONArray();
                    for (int i5 = 0; i5 < optJSONArray.length(); i5++) {
                        JSONObject jSONObject3 = optJSONArray.getJSONObject(i5);
                        if (jSONObject3 != null) {
                            if (i3 >= i4) {
                                jSONArray.put(jSONObject3);
                            }
                            i3 += jSONObject3.toString().getBytes(z[9]).length;
                        }
                    }
                    if (jSONArray.length() > 0) {
                        jSONObject2.put(z[0], jSONArray);
                        jSONObject = jSONObject2;
                    } else {
                        jSONObject = null;
                    }
                    a = jSONObject;
                    a(context, z[1], jSONObject);
                } catch (UnsupportedEncodingException e3) {
                } catch (JSONException e4) {
                }
            }
        }
    }

    public static void a(Context context, JSONArray jSONArray) {
        if (context == null) {
            ac.b();
        } else if (jSONArray == null || jSONArray.length() <= 0) {
            ac.b();
        } else {
            g.execute(new aj(context, jSONArray));
        }
    }

    public static void a(Context context, JSONArray jSONArray, ai aiVar) {
        int i = 0;
        ac.a();
        JSONObject jSONObject = new JSONObject();
        if (context == null || jSONArray == null || jSONArray.length() <= 0) {
            new StringBuilder(z[15]).append(context).append(z[11]).append(jSONArray);
            ac.d();
            if (aiVar != null) {
                aiVar.a(-1);
                return;
            }
            return;
        }
        try {
            new StringBuilder(z[13]).append(jSONArray.toString().getBytes(z[9]).length);
            ac.b();
        } catch (UnsupportedEncodingException e2) {
        }
        try {
            jSONObject.put(z[0], jSONArray);
            try {
                if (a(jSONObject, context)) {
                    try {
                        new StringBuilder(z[14]).append(jSONObject.toString(1));
                        ac.c();
                    } catch (JSONException e3) {
                        new StringBuilder(z[14]).append(jSONObject.toString());
                        ac.c();
                    }
                    int a2 = s.a(context, jSONObject, true);
                    if (aiVar != null) {
                        if (a2 != 200) {
                            i = -1;
                        }
                        aiVar.a(i);
                    }
                } else if (aiVar != null) {
                    aiVar.a(-1);
                }
            } catch (Exception e4) {
                new StringBuilder(z[12]).append(e4);
                ac.b();
                if (aiVar != null) {
                    aiVar.a(-1);
                }
            }
        } catch (JSONException e5) {
            new StringBuilder(z[10]).append(e5);
            ac.b();
            if (aiVar != null) {
                aiVar.a(-1);
            }
        }
    }

    public static void a(Context context, JSONObject jSONObject) {
        a(context, new JSONArray().put(jSONObject));
    }

    private static void a(Context context, JSONObject jSONObject, ArrayList<JSONArray> arrayList) {
        if (arrayList.size() <= 0) {
            b(context);
        }
        JSONArray jSONArray = new JSONArray();
        for (int i = 0; i < arrayList.size(); i++) {
            JSONArray jSONArray2 = arrayList.get(i);
            for (int i2 = 0; i2 < jSONArray2.length(); i2++) {
                if (jSONArray2.optJSONObject(i2) != null) {
                    jSONArray.put(jSONArray2.optJSONObject(i2));
                }
            }
        }
        try {
            jSONObject.put(z[0], jSONArray);
        } catch (JSONException e2) {
        }
        a = jSONObject;
        a(context, z[1], jSONObject);
    }

    private static void a(Context context, JSONObject jSONObject, JSONArray jSONArray, ArrayList<JSONArray> arrayList) {
        if (arrayList.size() == 1) {
            b(context);
        } else if (jSONArray != null && arrayList.size() > 1) {
            arrayList.remove(jSONArray);
            JSONArray jSONArray2 = new JSONArray();
            for (int i = 0; i < arrayList.size(); i++) {
                JSONArray jSONArray3 = arrayList.get(i);
                for (int i2 = 0; i2 < jSONArray3.length(); i2++) {
                    if (jSONArray3.optJSONObject(i2) != null) {
                        jSONArray2.put(jSONArray3.optJSONObject(i2));
                    }
                }
            }
            try {
                jSONObject.put(z[0], jSONArray2);
            } catch (JSONException e2) {
            }
            a = jSONObject;
            a(context, z[1], jSONObject);
        }
    }

    public static void a(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException e2) {
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r3v3, types: [java.lang.Exception] */
    /* JADX WARN: Type inference failed for: r3v4, types: [java.lang.StringBuilder] */
    /* JADX WARN: Unknown variable types count: 2 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static boolean a(android.content.Context r8, java.lang.String r9, org.json.JSONObject r10) {
        /*
            Method dump skipped, instructions count: 299
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.jpush.android.util.ah.a(android.content.Context, java.lang.String, org.json.JSONObject):boolean");
    }

    private static boolean a(JSONObject jSONObject, Context context) {
        jSONObject.put(z[37], "a");
        long y = a.y();
        if (y == 0) {
            ac.e();
            return false;
        }
        jSONObject.put(z[34], y);
        String s = b.s(context);
        if (ao.a(s)) {
            ac.e();
            return false;
        }
        jSONObject.put(z[36], s);
        jSONObject.put(z[38], z[35]);
        return true;
    }

    private static String b() {
        if (ao.a(c)) {
            a(a.v());
        }
        return c;
    }

    private static void b(Context context) {
        a = null;
        if (!a(context, z[1], (JSONObject) null)) {
            try {
                if (context.deleteFile(e(z[1]))) {
                    new StringBuilder(z[6]).append(e(z[1]));
                    ac.e();
                }
            } catch (IllegalArgumentException e2) {
                new StringBuilder(z[5]).append(e2.getMessage());
                ac.e();
            } catch (Exception e3) {
                new StringBuilder(z[5]).append(e3.getMessage());
                ac.e();
            }
        }
    }

    public static /* synthetic */ void b(Context context, JSONArray jSONArray) {
        String str = z[1];
        if (a == null) {
            a = a(context, str);
        }
        JSONObject jSONObject = a;
        JSONObject jSONObject2 = jSONObject == null ? new JSONObject() : jSONObject;
        JSONArray optJSONArray = jSONObject2.optJSONArray(z[0]);
        JSONArray jSONArray2 = optJSONArray == null ? new JSONArray() : optJSONArray;
        if (jSONArray != null) {
            try {
                if (jSONArray.length() > 0) {
                    for (int i = 0; i < jSONArray.length(); i++) {
                        jSONArray2.put(jSONArray.get(i));
                    }
                }
            } catch (JSONException e2) {
            }
        }
        if (!b.b(context)) {
            jSONObject2.put(z[0], jSONArray2);
            a(context, z[1], jSONObject2);
            return;
        }
        if (jSONArray2.length() > 0) {
            ArrayList<JSONArray> a2 = a(jSONArray2, 20480);
            ArrayList arrayList = new ArrayList();
            arrayList.addAll(a2);
            try {
                new StringBuilder(z[13]).append(jSONArray2.toString().getBytes(z[9]).length);
                ac.b();
            } catch (UnsupportedEncodingException e3) {
            }
            new StringBuilder(z[40]).append(a2.size()).append(z[39]);
            ac.b();
            boolean z2 = false;
            for (int i2 = 0; i2 < a2.size(); i2++) {
                JSONArray jSONArray3 = a2.get(i2);
                if (!z2) {
                    if (jSONArray3.length() <= 0) {
                        arrayList.remove(jSONArray3);
                    } else {
                        try {
                            jSONObject2.put(z[0], jSONArray3);
                            try {
                                if (!a(jSONObject2, context)) {
                                    c(context, jSONObject2);
                                    return;
                                }
                                if (jSONObject2 != 0) {
                                    try {
                                        new StringBuilder(z[14]).append(jSONObject2.toString(1));
                                        ac.c();
                                    } catch (JSONException e4) {
                                        new StringBuilder(z[14]).append(jSONObject2.toString());
                                        ac.c();
                                    }
                                }
                                switch (s.a(context, jSONObject2, true)) {
                                    case -5:
                                    case -4:
                                    case -3:
                                    case -2:
                                        a(context, jSONObject2, jSONArray3, arrayList);
                                        continue;
                                    case -1:
                                    case 404:
                                    case 429:
                                    case 500:
                                        a(context, jSONObject2, arrayList);
                                        continue;
                                    case 200:
                                        a(context, jSONObject2, jSONArray3, arrayList);
                                        continue;
                                    case 401:
                                        b(context);
                                        z2 = true;
                                        continue;
                                }
                            } catch (Exception e5) {
                                new StringBuilder(z[12]).append(e5);
                                ac.b();
                                c(context, jSONObject2);
                                return;
                            }
                        } catch (JSONException e6) {
                            new StringBuilder(z[10]).append(e6);
                            ac.b();
                            a(context, jSONObject2, jSONArray3, arrayList);
                        }
                    }
                } else {
                    return;
                }
            }
        }
    }

    public static void b(Context context, JSONObject jSONObject) {
        try {
            JSONArray jSONArray = new JSONArray();
            jSONArray.put(jSONObject);
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put(z[0], jSONArray);
            if (a(jSONObject2, context)) {
                new StringBuilder(z[8]).append(jSONObject.toString());
                ac.b();
                if (s.a(context, jSONObject2, true) == 200) {
                    ac.b();
                }
            }
        } catch (JSONException e2) {
        } catch (Exception e3) {
        }
    }

    public static boolean b(String str) {
        c = b();
        if (ao.a(str) || ao.a(c)) {
            return false;
        }
        return str.equals(c);
    }

    public static String c(String str) {
        try {
            return e.a(str.getBytes(), 10);
        } catch (Exception e2) {
            ac.e();
            return null;
        }
    }

    private static void c(Context context, JSONObject jSONObject) {
        a = jSONObject;
        a(context, z[1], jSONObject);
    }

    private static String d(String str) {
        try {
            byte[] bytes = str.getBytes(z[7]);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            GZIPOutputStream gZIPOutputStream = new GZIPOutputStream(byteArrayOutputStream);
            gZIPOutputStream.write(bytes);
            gZIPOutputStream.close();
            byte[] byteArray = byteArrayOutputStream.toByteArray();
            byteArrayOutputStream.close();
            return b.a(byteArray);
        } catch (UnsupportedEncodingException e2) {
            e2.printStackTrace();
            return null;
        } catch (IOException e3) {
            e3.printStackTrace();
            return null;
        }
    }

    private static String e(String str) {
        if (!ao.a(str)) {
            return str.equals(z[1]) ? z[16] : z[17];
        }
        ac.b();
        return null;
    }
}
