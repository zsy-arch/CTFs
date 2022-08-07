package cn.jpush.android.util;

import android.content.Context;
import android.text.TextUtils;
import cn.jpush.android.a;
import cn.jpush.android.helpers.k;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.CookiePolicy;
import java.net.HttpCookie;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.atomic.AtomicInteger;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public final class af extends Thread {
    private static ExecutorService a;
    private static Object b;
    private static AtomicInteger c;
    private static CookieManager j;
    private static final String[] z;
    private String d;
    private String e;
    private String f;
    private Context g;
    private int h = 0;
    private String i;

    /* JADX WARN: Code restructure failed: missing block: B:100:0x03c1, code lost:
        r3[r2] = r1;
        r2 = 'U';
        r1 = "CC`\u001e\nAAu9U";
        r0 = 'T';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:101:0x03cc, code lost:
        r3[r2] = r1;
        r2 = 'V';
        r1 = "cPd$\u0000L\u0013=m\bGG@%\u0000LV^8\u0002@Vb\u001a\u0006V[Q#\u000eNJc$\u001cwA|m\u001aP_*";
        r0 = 'U';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:102:0x03d7, code lost:
        r3[r2] = r1;
        r2 = 'W';
        r1 = "PVc=0FRd,";
        r0 = 'V';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:103:0x03e2, code lost:
        r3[r2] = r1;
        r2 = 'X';
        r1 = "a[y#\u000eo\\r$\u0003G";
        r0 = 'W';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:104:0x03ed, code lost:
        r3[r2] = r1;
        r2 = 'Y';
        r1 = "a[y#\u000evV|(\fM^";
        r0 = 'X';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:105:0x03f8, code lost:
        r3[r2] = r1;
        r2 = 'Z';
        r1 = "a[y#\u000ew]y.\u0000O";
        r0 = 'Y';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:106:0x0403, code lost:
        r3[r2] = r1;
        r2 = '[';
        r1 = "G]S?\u001fVJS\"\u0001VV~9U";
        r0 = 'Z';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:107:0x040e, code lost:
        r3[r2] = r1;
        r2 = '\\';
        r1 = "LQ";
        r0 = '[';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:108:0x0419, code lost:
        r3[r2] = r1;
        r2 = ']';
        r1 = "PV`?\u0000V\u0013s\"\u0001VV~9U";
        r0 = '\\';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:109:0x0424, code lost:
        r3[r2] = r1;
        r2 = '^';
        r1 = "KGy \n";
        r0 = ']';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:10:0x0026, code lost:
        if (r5 != 0) goto L_0x002c;
     */
    /* JADX WARN: Code restructure failed: missing block: B:110:0x042f, code lost:
        r3[r2] = r1;
        r2 = '_';
        r1 = "A\\~9\nLG";
        r0 = '^';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:111:0x043a, code lost:
        r3[r2] = r1;
        r2 = '`';
        r1 = "VJ`(";
        r0 = '_';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:112:0x0445, code lost:
        r3[r2] = r1;
        r2 = 'a';
        r1 = "cPd\"\u0001\u0002\u001e0\"\u0001fVd.\u0007r[\u007f#\nlF}/\nP\u0013`%\u0000LV^8\u0002@Vbw";
        r0 = '`';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:113:0x0450, code lost:
        r3[r2] = r1;
        cn.jpush.android.util.af.z = r3;
        cn.jpush.android.util.af.a = java.util.concurrent.Executors.newSingleThreadExecutor();
        cn.jpush.android.util.af.b = new java.lang.Object();
        cn.jpush.android.util.af.c = new java.util.concurrent.atomic.AtomicInteger();
     */
    /* JADX WARN: Code restructure failed: missing block: B:114:0x0468, code lost:
        return;
     */
    /* JADX WARN: Code restructure failed: missing block: B:115:0x0469, code lost:
        r9 = '\"';
     */
    /* JADX WARN: Code restructure failed: missing block: B:116:0x046d, code lost:
        r9 = '3';
     */
    /* JADX WARN: Code restructure failed: missing block: B:117:0x0471, code lost:
        r9 = 16;
     */
    /* JADX WARN: Code restructure failed: missing block: B:118:0x0475, code lost:
        r9 = 'M';
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
            case 11: goto L_0x00a9;
            case 12: goto L_0x00b4;
            case 13: goto L_0x00bf;
            case 14: goto L_0x00ca;
            case 15: goto L_0x00d5;
            case 16: goto L_0x00e0;
            case 17: goto L_0x00eb;
            case 18: goto L_0x00f6;
            case 19: goto L_0x0101;
            case 20: goto L_0x010c;
            case 21: goto L_0x0117;
            case 22: goto L_0x0122;
            case 23: goto L_0x012d;
            case 24: goto L_0x0138;
            case 25: goto L_0x0143;
            case 26: goto L_0x014e;
            case 27: goto L_0x0159;
            case 28: goto L_0x0164;
            case 29: goto L_0x016f;
            case 30: goto L_0x017a;
            case 31: goto L_0x0185;
            case 32: goto L_0x0190;
            case 33: goto L_0x019b;
            case 34: goto L_0x01a6;
            case 35: goto L_0x01b1;
            case 36: goto L_0x01bc;
            case 37: goto L_0x01c7;
            case 38: goto L_0x01d2;
            case 39: goto L_0x01dd;
            case 40: goto L_0x01e8;
            case 41: goto L_0x01f3;
            case 42: goto L_0x01fe;
            case 43: goto L_0x0209;
            case 44: goto L_0x0214;
            case 45: goto L_0x021f;
            case 46: goto L_0x022a;
            case 47: goto L_0x0235;
            case 48: goto L_0x0240;
            case 49: goto L_0x024b;
            case 50: goto L_0x0256;
            case 51: goto L_0x0261;
            case 52: goto L_0x026c;
            case 53: goto L_0x0277;
            case 54: goto L_0x0282;
            case 55: goto L_0x028d;
            case 56: goto L_0x0298;
            case 57: goto L_0x02a3;
            case 58: goto L_0x02ae;
            case 59: goto L_0x02b9;
            case 60: goto L_0x02c4;
            case 61: goto L_0x02cf;
            case 62: goto L_0x02da;
            case 63: goto L_0x02e5;
            case 64: goto L_0x02f0;
            case 65: goto L_0x02fb;
            case 66: goto L_0x0306;
            case 67: goto L_0x0311;
            case 68: goto L_0x031c;
            case 69: goto L_0x0327;
            case 70: goto L_0x0332;
            case 71: goto L_0x033d;
            case 72: goto L_0x0348;
            case 73: goto L_0x0353;
            case 74: goto L_0x035e;
            case 75: goto L_0x0369;
            case 76: goto L_0x0374;
            case 77: goto L_0x037f;
            case 78: goto L_0x038a;
            case 79: goto L_0x0395;
            case 80: goto L_0x03a0;
            case 81: goto L_0x03ab;
            case 82: goto L_0x03b6;
            case 83: goto L_0x03c1;
            case 84: goto L_0x03cc;
            case 85: goto L_0x03d7;
            case 86: goto L_0x03e2;
            case 87: goto L_0x03ed;
            case 88: goto L_0x03f8;
            case 89: goto L_0x0403;
            case 90: goto L_0x040e;
            case 91: goto L_0x0419;
            case 92: goto L_0x0424;
            case 93: goto L_0x042f;
            case 94: goto L_0x043a;
            case 95: goto L_0x0445;
            case 96: goto L_0x0450;
            default: goto L_0x003c;
        };
     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x003c, code lost:
        r3[r2] = r1;
        r2 = 1;
        r1 = "dZ|(\u001fCGxm\nPA\u007f?OMU0\u0016";
        r0 = 0;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x0044, code lost:
        r3[r2] = r1;
        r2 = 2;
        r1 = "AR~j\u001b\u0002Db$\u001bG\u0013";
        r0 = 1;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x004c, code lost:
        r3[r2] = r1;
        r2 = 3;
        r1 = "QRf(ON\\wm\u0006L\u0013g?\u0006VVX$\u001cV\\b4#MT*G";
        r0 = 2;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:19:0x0054, code lost:
        r3[r2] = r1;
        r2 = 4;
        r1 = "\u0018\u0013";
        r0 = 3;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x005c, code lost:
        r3[r2] = r1;
        r2 = 5;
        r1 = "\u0002\u001f0*\u0006TV08\u001f\u0002@q;\n\u0002\t";
        r0 = 4;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x0064, code lost:
        r3[r2] = r1;
        r2 = 6;
        r1 = "PVc=APRg";
        r0 = 5;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x006c, code lost:
        r3[r2] = r1;
        r2 = 7;
        r1 = "wgV`W";
        r0 = 6;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x0074, code lost:
        r3[r2] = r1;
        r2 = '\b';
        r1 = "PVc=AXZ`";
        r0 = 7;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x007d, code lost:
        r3[r2] = r1;
        r2 = '\t';
        r1 = "AR~j\u001b\u0002\\`(\u0001\u0002";
        r0 = '\b';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x0087, code lost:
        r3[r2] = r1;
        r2 = '\n';
        r1 = "/9\u001dG";
        r0 = '\t';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x0092, code lost:
        r3[r2] = r1;
        r2 = 11;
        r1 = "\u0002\\e9\u001fWGC9\u001dGR}aOEZf(OWC0>\u000eTV0w";
        r0 = '\n';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x009d, code lost:
        r3[r2] = r1;
        r2 = '\f';
        r1 = "\u007f\u0013<m\bKEum\u001aR\u0013c,\u0019G\u0013*";
        r0 = 11;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x00a9, code lost:
        r3[r2] = r1;
        r2 = '\r';
        r1 = "AR~j\u001b\u0002V~.\u0000FZ~*O";
        r0 = '\f';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:29:0x00b4, code lost:
        r3[r2] = r1;
        r2 = 14;
        r1 = "\u000e\u0013";
        r0 = '\r';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x00bf, code lost:
        r3[r2] = r1;
        r2 = 15;
        r1 = "\u0016\u0005 }Z";
        r0 = 14;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:31:0x00ca, code lost:
        r3[r2] = r1;
        r2 = 16;
        r1 = "\u0016\u0005 |^";
        r0 = 15;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x00d5, code lost:
        r3[r2] = r1;
        r2 = 17;
        r1 = "\u0016\u0005 }Y";
        r0 = 16;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x00e0, code lost:
        r3[r2] = r1;
        r2 = 18;
        r1 = "\u0016\u0005 }_";
        r0 = 17;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:34:0x00eb, code lost:
        r3[r2] = r1;
        r2 = 19;
        r1 = "\u0016\u0005 }^";
        r0 = 18;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:35:0x00f6, code lost:
        r3[r2] = r1;
        r2 = com.tencent.open.GameAppOperation.PIC_SYMBOLE;
        r1 = "RA\u007f;\u0006FVb>&LWu5U";
        r0 = 19;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:36:0x0101, code lost:
        r3[r2] = r1;
        r2 = 21;
        r1 = "\u0016\u0005 }V";
        r0 = com.tencent.open.GameAppOperation.PIC_SYMBOLE;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:37:0x010c, code lost:
        r3[r2] = r1;
        r2 = 22;
        r1 = "\u0016\u0005 }\\";
        r0 = 21;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:38:0x0117, code lost:
        r3[r2] = r1;
        r2 = 23;
        r1 = "\u0016\u0005 }W";
        r0 = 22;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:39:0x0122, code lost:
        r3[r2] = r1;
        r2 = 24;
        r1 = "\u0016\u0005 }X";
        r0 = 23;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:40:0x012d, code lost:
        r3[r2] = r1;
        r2 = 25;
        r1 = "\u0016\u0005 }]";
        r0 = 24;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:41:0x0138, code lost:
        r3[r2] = r1;
        r2 = 26;
        r1 = "CC`\u0012\u0006F";
        r0 = 25;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:42:0x0143, code lost:
        r3[r2] = r1;
        r2 = 27;
        r1 = "QGq9\u0006QGy.@SFu?\u0016\u001d";
        r0 = 26;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:43:0x014e, code lost:
        r3[r2] = r1;
        r2 = 28;
        r1 = "cPd$\u0000L\u0013=m\bGG@%\u0000LV^8\u0002@Vbm\u0006OVyw";
        r0 = 27;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:44:0x0159, code lost:
        r3[r2] = r1;
        r2 = 29;
        r1 = "PVa\u0012\u001bK^u";
        r0 = 28;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:45:0x0164, code lost:
        r3[r2] = r1;
        r2 = 30;
        r1 = "\u0002Ac=+CGqw";
        r0 = 29;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:46:0x016f, code lost:
        r3[r2] = r1;
        r2 = 31;
        r1 = "A\\t(";
        r0 = 30;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:47:0x017a, code lost:
        r3[r2] = r1;
        r2 = ' ';
        r1 = "QZw#";
        r0 = 31;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:48:0x0185, code lost:
        r3[r2] = r1;
        r2 = '!';
        r1 = "WA|w";
        r0 = ' ';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:49:0x0190, code lost:
        r3[r2] = r1;
        r2 = '\"';
        r1 = "TVb>\u0006M]";
        r0 = '!';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:4:0x0010, code lost:
        if (r5 <= 1) goto L_0x0012;
     */
    /* JADX WARN: Code restructure failed: missing block: B:50:0x019b, code lost:
        r3[r2] = r1;
        r2 = '#';
        r1 = "\u0002Zs.\u0006F\t";
        r0 = '\"';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:51:0x01a6, code lost:
        r3[r2] = r1;
        r2 = '$';
        r1 = "K^c$";
        r0 = '#';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:52:0x01b1, code lost:
        r3[r2] = r1;
        r2 = '%';
        r1 = "\u0002Z}>\u0006\u0018";
        r0 = '$';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:53:0x01bc, code lost:
        r3[r2] = r1;
        r2 = '&';
        r1 = "KPs$\u000b";
        r0 = '%';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:54:0x01c7, code lost:
        r3[r2] = r1;
        r2 = '\'';
        r1 = "LF}";
        r0 = '&';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:55:0x01d2, code lost:
        r3[r2] = r1;
        r2 = '(';
        r1 = "\u0002@d,\u001bW@S\"\u000bG\t";
        r0 = '\'';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:56:0x01dd, code lost:
        r3[r2] = r1;
        r2 = ')';
        r1 = "K^u$";
        r0 = '(';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:57:0x01e8, code lost:
        r3[r2] = r1;
        r2 = '*';
        r1 = "FVs\"\u000bGcx\"\u0001G}e \rGA0)\nAAi=\u001b\u0002Wq9\u000e\u0018";
        r0 = ')';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:58:0x01f3, code lost:
        r3[r2] = r1;
        r2 = '+';
        r1 = "@Rc(OWA|w";
        r0 = '*';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:59:0x01fe, code lost:
        r3[r2] = r1;
        r2 = ',';
        r1 = "PVdw";
        r0 = '+';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:5:0x0012, code lost:
        r8 = r6;
        r5 = r1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:60:0x0209, code lost:
        r3[r2] = r1;
        r2 = '-';
        r1 = "DZ|(U";
        r0 = ',';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:61:0x0214, code lost:
        r3[r2] = r1;
        r2 = '.';
        r1 = "\u0019Q\u007f8\u0001FRb4R";
        r0 = '-';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:62:0x021f, code lost:
        r3[r2] = r1;
        r2 = '/';
        r1 = "P@`\u000e\u0000FV*";
        r0 = '.';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:63:0x022a, code lost:
        r3[r2] = r1;
        r2 = '0';
        r1 = "\u000e\u0013e?\u0003\u0018";
        r0 = '/';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:64:0x0235, code lost:
        r3[r2] = r1;
        r2 = '1';
        r1 = "r|C\u0019";
        r0 = '0';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:65:0x0240, code lost:
        r3[r2] = r1;
        r2 = '2';
        r1 = "\u0002Zc\u0019\nOC\u007f?\u000ePJB(\u000bKAu.\u001b\u0018";
        r0 = '1';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:66:0x024b, code lost:
        r3[r2] = r1;
        r2 = '3';
        r1 = "\u0002Xu4\u001cGG*";
        r0 = '2';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:67:0x0256, code lost:
        r3[r2] = r1;
        r2 = '4';
        r1 = "a[q?\u001cGG";
        r0 = '3';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:68:0x0261, code lost:
        r3[r2] = r1;
        r2 = '5';
        r1 = "mGx(\u001d\u0002Db\"\u0001E\u0013b(\u001cR\\~>\n\u0002@d,\u001bW@0`O";
        r0 = '4';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:69:0x026c, code lost:
        r3[r2] = r1;
        r2 = '6';
        r1 = "QVb;\nP\u0013v,\u0006N";
        r0 = '5';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:6:0x0017, code lost:
        r10 = r5[r6];
     */
    /* JADX WARN: Code restructure failed: missing block: B:70:0x0277, code lost:
        r3[r2] = r1;
        r2 = '7';
        r1 = "\u000f\u001e";
        r0 = '6';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:71:0x0282, code lost:
        r3[r2] = r1;
        r2 = '8';
        r1 = "WA|>U";
        r0 = '7';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:72:0x028d, code lost:
        r3[r2] = r1;
        r2 = '9';
        r1 = "qVb;\nP\u0013b(\u001cR\\~>\n\u0002Uq$\u0003WAuw[\u0012\u00030`O";
        r0 = '8';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:73:0x0298, code lost:
        r3[r2] = r1;
        r2 = ':';
        r1 = "(\u0013v$\u0003G\t";
        r0 = '9';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:74:0x02a3, code lost:
        r3[r2] = r1;
        r2 = ';';
        r1 = "aRd.\u0007\u0002rc>\nPGy\"\u0001gAb\"\u001d\u0002G\u007fm\u000eT\\y)OJGd=OA_\u007f>\n\u0002Pb,\u001cJ\u0013=m";
        r0 = ':';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:75:0x02ae, code lost:
        r3[r2] = r1;
        r2 = '<';
        r1 = "\u0002\u0013v$\u0003G]q \n\u0018";
        r0 = ';';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:76:0x02b9, code lost:
        r3[r2] = r1;
        r2 = '=';
        r1 = "\u001e\u000fu?\u001dMA.s";
        r0 = '<';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:77:0x02c4, code lost:
        r3[r2] = r1;
        r2 = '>';
        r1 = "PVa8\nQG09\u0006OV\u007f8\u001b\u0018\u0007 uO\u000f\u0013";
        r0 = '=';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:78:0x02cf, code lost:
        r3[r2] = r1;
        r2 = '?';
        r1 = "\u0000\b°+\u0006NV~,\u0002G\u000e2";
        r0 = '>';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:79:0x02da, code lost:
        r3[r2] = r1;
        r2 = '@';
        r1 = "N\\s,\u001bK\\~w";
        r0 = '?';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:7:0x001b, code lost:
        switch((r8 % 5)) {
            case 0: goto L_0x0469;
            case 1: goto L_0x046d;
            case 2: goto L_0x0471;
            case 3: goto L_0x0475;
            default: goto L_0x001e;
        };
     */
    /* JADX WARN: Code restructure failed: missing block: B:80:0x02e5, code lost:
        r3[r2] = r1;
        r2 = 'A';
        r1 = "pVa8\nQG0=\u000eV[0)\u0000G@0#\u0000V\u0013u5\u0006QG*m[\u0012\u00070`O";
        r0 = '@';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:81:0x02f0, code lost:
        r3[r2] = r1;
        r2 = 'B';
        r1 = "a\\\u007f&\u0006G";
        r0 = 'A';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:82:0x02fb, code lost:
        r3[r2] = r1;
        r2 = 'C';
        r1 = "OF|9\u0006RRb9@D\\b BFRd,";
        r0 = 'B';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:83:0x0306, code lost:
        r3[r2] = r1;
        r2 = 'D';
        r1 = "a\\~9\nLG=\t\u0006QC\u007f>\u0006VZ\u007f#U\u0082U\u007f?\u0002\u000fWq9\u000e\u0019\u0093~,\u0002G\u000e2";
        r0 = 'C';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:84:0x0311, code lost:
        r3[r2] = r1;
        r2 = 'E';
        r1 = "a\\~9\nLG=\u0019\u0016RV";
        r0 = 'D';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:85:0x031c, code lost:
        r3[r2] = r1;
        r2 = 'F';
        r1 = "/9";
        r0 = 'E';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:86:0x0327, code lost:
        r3[r2] = r1;
        r2 = 'G';
        r1 = "qVb;\nP\u0013u?\u001dMA0`O";
        r0 = 'F';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:87:0x0332, code lost:
        r3[r2] = r1;
        r2 = 'H';
        r1 = "pVa8\nQG0#\u0000V\u0013q8\u001bJ\\b$\u0015GW*y_\u0013\u0013=m";
        r0 = 'G';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:88:0x033d, code lost:
        r3[r2] = r1;
        r2 = 'I';
        r1 = "JGd=AIVu=.NZf(";
        r0 = 'H';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:89:0x0348, code lost:
        r3[r2] = r1;
        r2 = 'J';
        r1 = "L\\dm\u000eAPu=\u001bCQ|(U\u0016\u0003&mB\u0002";
        r0 = 'I';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:8:0x001e, code lost:
        r9 = 'o';
     */
    /* JADX WARN: Code restructure failed: missing block: B:90:0x0353, code lost:
        r3[r2] = r1;
        r2 = 'K';
        r1 = "w@u?BcTu#\u001b";
        r0 = 'J';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:91:0x035e, code lost:
        r3[r2] = r1;
        r2 = 'L';
        r1 = "o\\j$\u0003NR?xA\u0012\u00138\u0001\u0006LFhvOc]t?\u0000KW0xA\u0013\u001d!vOlVh8\u001c\u0002\u00050\u000f\u001aK_tb#{i\"u*\u000b\u0013Q=\u001fNVG(\riZdbZ\u0011\u0004>~Y\u0002\u001b[\u0005;o\u007f<m\u0003KXum(GP{\"F\u0002px?\u0000OV?yW\f\u0003>\u007fZ\u0014\u0007>\u007f\\\u0002~\u007f/\u0006NV0\u001e\u000eDRb$@\u0017\u0000'c\\\u0014";
        r0 = 'K';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:92:0x0369, code lost:
        r3[r2] = r1;
        r2 = 'M';
        r1 = "n\\s,\u001bK\\~";
        r0 = 'L';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:93:0x0374, code lost:
        r3[r2] = r1;
        r2 = 'N';
        r1 = "a\\~9\nLG=\u0019\u0016RV*í\u000eRC|$\fCGy\"\u0001\r\\s9\nV\u001ec9\u001dGR}vÏA[q?\u001cGG-\u0018;d\u001e(";
        r0 = 'M';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:94:0x037f, code lost:
        r3[r2] = r1;
        r2 = 'O';
        r1 = "DR|>\n";
        r0 = 'N';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:95:0x038a, code lost:
        r3[r2] = r1;
        r2 = 'P';
        r1 = "A\\~+\u0003KPdw[\u0012\n0`O";
        r0 = 'O';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:96:0x0395, code lost:
        r3[r2] = r1;
        r2 = 'Q';
        r1 = "\u001e\u000fv,\u0006NVt\u0012\u0018KGx\u0012\u001dGGb$\nQ\r.";
        r0 = 'P';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:97:0x03a0, code lost:
        r3[r2] = r1;
        r2 = 'R';
        r1 = "qVd`,M\\{$\n";
        r0 = 'Q';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:98:0x03ab, code lost:
        r3[r2] = r1;
        r2 = 'S';
        r1 = "QZw#U";
        r0 = 'R';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:99:0x03b6, code lost:
        r3[r2] = r1;
        r2 = 'T';
        r1 = "VAu(\"CCF,\u0003WV*";
        r0 = 'S';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:9:0x0020, code lost:
        r5[r6] = (char) (r9 ^ r10);
        r6 = r8 + 1;
     */
    static {
        /*
            Method dump skipped, instructions count: 1356
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.jpush.android.util.af.<clinit>():void");
    }

    private af() {
    }

    private af(Context context) {
        String j2 = b.j(context, this.d);
        String i = b.i(context, this.e);
        String k = b.k(context, this.f);
        CookieManager cookieManager = new CookieManager();
        j = cookieManager;
        cookieManager.setCookiePolicy(CookiePolicy.ACCEPT_ALL);
        CookieHandler.setDefault(j);
        this.g = context;
        this.d = j2;
        this.e = i;
        this.f = k;
    }

    public static int a(String str) {
        if (ao.a(str)) {
            return -1;
        }
        if (str.equalsIgnoreCase(z[89])) {
            return 2;
        }
        if (str.equalsIgnoreCase(z[88])) {
            return 0;
        }
        return str.equalsIgnoreCase(z[90]) ? 1 : -1;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:115:0x036f  */
    /* JADX WARN: Removed duplicated region for block: B:190:0x036a A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:221:0x0213 A[ADDED_TO_REGION, EDGE_INSN: B:221:0x0213->B:43:0x0213 ?: BREAK  , SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:39:0x0209  */
    /* JADX WARN: Type inference failed for: r15v2 */
    /* JADX WARN: Type inference failed for: r15v3 */
    /* JADX WARN: Type inference failed for: r15v4 */
    /* JADX WARN: Type inference failed for: r15v5, types: [java.net.HttpURLConnection] */
    /* JADX WARN: Type inference failed for: r15v6, types: [java.net.HttpURLConnection] */
    /* JADX WARN: Type inference failed for: r15v7, types: [java.net.HttpURLConnection] */
    /* JADX WARN: Type inference failed for: r3v15 */
    /* JADX WARN: Type inference failed for: r3v16 */
    /* JADX WARN: Type inference failed for: r3v32 */
    /* JADX WARN: Type inference failed for: r4v10 */
    /* JADX WARN: Type inference failed for: r4v17 */
    /* JADX WARN: Type inference failed for: r4v18 */
    /* JADX WARN: Type inference failed for: r4v19 */
    /* JADX WARN: Type inference failed for: r4v20 */
    /* JADX WARN: Type inference failed for: r4v36 */
    /* JADX WARN: Type inference failed for: r4v5 */
    /* JADX WARN: Type inference failed for: r4v54 */
    /* JADX WARN: Type inference failed for: r5v2 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private cn.jpush.android.util.ag a(android.content.Context r23, java.lang.String r24, int r25, long r26, boolean r28, java.io.File r29, java.lang.String r30) {
        /*
            Method dump skipped, instructions count: 1310
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.jpush.android.util.af.a(android.content.Context, java.lang.String, int, long, boolean, java.io.File, java.lang.String):cn.jpush.android.util.ag");
    }

    private String a(int i) {
        this.i = a.d(this.g, i);
        new StringBuilder(z[43]).append(this.i);
        ac.a();
        return this.i;
    }

    private String a(String str, ag agVar) {
        if (a(this.g, agVar)) {
            return d(str);
        }
        return null;
    }

    private String a(TreeMap<String, String> treeMap) {
        if (treeMap.size() == 0) {
            ac.d();
            return null;
        }
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, String> entry : treeMap.entrySet()) {
            sb.append((Object) entry.getValue());
        }
        String l = a.l(this.g);
        new StringBuilder(z[85]).append(l);
        ac.a();
        String upperCase = b.a(sb.toString() + l).toUpperCase();
        new StringBuilder(z[84]).append((Object) sb);
        ac.a();
        new StringBuilder(z[83]).append(upperCase);
        ac.a();
        return upperCase;
    }

    public static void a(Context context) {
        ac.a();
        if (c.get() >= 2) {
            ac.a();
        } else {
            a.execute(new af(context));
        }
    }

    private static void a(Map<String, List<String>> map) {
        List<String> list = map.get(z[82]);
        if (list != null) {
            for (String str : list) {
                j.getCookieStore().add(null, HttpCookie.parse(str).get(0));
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v2 */
    /* JADX WARN: Type inference failed for: r1v29 */
    /* JADX WARN: Type inference failed for: r1v3 */
    /* JADX WARN: Type inference failed for: r1v30 */
    /* JADX WARN: Type inference failed for: r1v31 */
    /* JADX WARN: Type inference failed for: r1v32 */
    /* JADX WARN: Type inference failed for: r1v33 */
    /* JADX WARN: Type inference failed for: r1v4 */
    /* JADX WARN: Type inference failed for: r1v5, types: [java.io.Closeable] */
    /* JADX WARN: Type inference failed for: r1v6 */
    /* JADX WARN: Unknown variable types count: 1 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static boolean a(android.content.Context r10, cn.jpush.android.util.ag r11) {
        /*
            Method dump skipped, instructions count: 493
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.jpush.android.util.af.a(android.content.Context, cn.jpush.android.util.ag):boolean");
    }

    private boolean a(String str, String str2, String str3) {
        String str4;
        new StringBuilder(z[28]).append(str).append(z[35]).append(str2).append(z[37]).append(str3);
        ac.a();
        TreeMap<String, String> treeMap = new TreeMap<>();
        if (!ao.a(str)) {
            treeMap.put(z[41], str);
        }
        if (!ao.a(str2)) {
            treeMap.put(z[38], str2);
        }
        if (!ao.a(str3)) {
            treeMap.put(z[36], str3);
        }
        treeMap.put(z[34], a.j(this.g));
        treeMap.put(z[26], a.k(this.g));
        treeMap.put(z[29], m.b());
        treeMap.put(z[32], a(treeMap));
        String str5 = "";
        for (Map.Entry<String, String> entry : treeMap.entrySet()) {
            try {
                str5 = str5 + com.alipay.sdk.sys.a.b + entry.getKey() + "=" + URLEncoder.encode(entry.getValue(), z[7]);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        new StringBuilder(z[33]).append(str5);
        ac.a();
        try {
            ag a2 = a(this.g, this.i + z[27] + str5, 10, 30000L, false, null, null);
            new StringBuilder(z[40]).append(a2.a).append(z[30]).append(a2.b);
            ac.a();
            if (a2.a != 200) {
                return false;
            }
            JSONObject c2 = c(a2.b);
            if (c2 != null) {
                if (c2.optInt(z[31], -1) != 200) {
                    return false;
                }
                str4 = b(c2.optString(z[39]));
            } else if (a2.c != null || !TextUtils.isEmpty(a2.b)) {
                synchronized (b) {
                    this.h = 0;
                    try {
                        str4 = a(str5, a2);
                    } catch (Throwable th) {
                        str4 = null;
                    }
                    this.g.deleteFile(z[6]);
                    this.g.deleteFile(z[8]);
                }
            } else {
                str4 = null;
            }
            if (TextUtils.isEmpty(str4)) {
                return false;
            }
            e(str4);
            return true;
        } catch (Throwable th2) {
            return false;
        }
    }

    public static String b(Context context) {
        if (context == null) {
            ac.d();
            return "";
        }
        String j2 = b.j(context, "");
        String i = b.i(context, "");
        String a2 = b.a(j2 + i + b.k(context, ""));
        new StringBuilder(z[44]).append(a2);
        ac.a();
        return a2;
    }

    private String b(String str) {
        String f = f(str);
        new StringBuilder(z[42]).append(f);
        ac.a();
        if (ao.a(f)) {
            ac.d();
            return null;
        } else if (ae.f.matcher(f).matches()) {
            return f;
        } else {
            ac.d();
            return null;
        }
    }

    private static JSONObject c(String str) {
        try {
            return new JSONObject(str);
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    private String d(String str) {
        String str2;
        new StringBuilder(z[86]).append(str);
        ac.b();
        if (TextUtils.isEmpty(str)) {
            ac.d();
            return null;
        }
        try {
            ag a2 = a(this.g, this.i + z[27] + str, 10, 30000L, false, new File(this.g.getFilesDir() + File.separator + z[8]), z[87]);
            new StringBuilder(z[40]).append(a2.a).append(z[30]).append(a2.b);
            ac.a();
            if (a2.a != 200) {
                return null;
            }
            JSONObject c2 = c(a2.b);
            if (c2 == null) {
                if (a2.c != null || !TextUtils.isEmpty(a2.b)) {
                    if (this.h > 4) {
                        return null;
                    }
                    this.h++;
                    try {
                        str2 = a(str, a2);
                    } catch (Throwable th) {
                    }
                }
                str2 = null;
            } else if (c2.optInt(z[31], -1) != 200) {
                return null;
            } else {
                str2 = b(c2.optString(z[39]));
            }
            return str2;
        } catch (Throwable th2) {
            return null;
        }
    }

    private void e(String str) {
        String o;
        new StringBuilder(z[97]).append(str);
        ac.a();
        a.i(this.g, str);
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put(z[39], str);
            if (!ao.a(this.d)) {
                jSONObject.put(z[41], this.d);
            }
            if (!ao.a(this.f)) {
                jSONObject.put(z[36], this.f);
            }
            if (!ao.a(this.e)) {
                jSONObject.put(z[38], this.e);
            }
            try {
                o = a.o(jSONObject.toString());
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (!ao.a(o)) {
                JSONObject jSONObject2 = new JSONObject();
                jSONObject2.put(z[95], o);
                jSONObject2.put(z[94], a.m());
                jSONObject2.put(z[96], z[92]);
                new StringBuilder(z[91]).append(o);
                ac.a();
                new StringBuilder(z[93]).append(jSONObject2.toString());
                ac.a();
                k.a(this.g, jSONObject2);
                a.d(this.g, false);
            }
        } catch (JSONException e2) {
            ac.i();
        }
    }

    private String f(String str) {
        try {
            return a.a(str, a.l(this.g).substring(0, 16));
        } catch (Exception e) {
            new StringBuilder(z[0]).append(e.getMessage());
            ac.d();
            return null;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:26:0x007e A[Catch: Exception -> 0x0115, TryCatch #0 {Exception -> 0x0115, blocks: (B:3:0x0007, B:5:0x000f, B:7:0x0017, B:9:0x001f, B:11:0x0023, B:13:0x002f, B:15:0x003a, B:17:0x0046, B:19:0x0052, B:21:0x005e, B:24:0x006b, B:26:0x007e, B:28:0x0089, B:30:0x0098, B:32:0x00a4, B:34:0x00b0, B:37:0x00be, B:39:0x00ca, B:41:0x00d6, B:48:0x00eb, B:50:0x0109, B:52:0x0111, B:55:0x0118, B:58:0x0128), top: B:59:0x0007 }] */
    /* JADX WARN: Removed duplicated region for block: B:45:0x00e6  */
    @Override // java.lang.Thread, java.lang.Runnable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void run() {
        /*
            Method dump skipped, instructions count: 301
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.jpush.android.util.af.run():void");
    }
}
