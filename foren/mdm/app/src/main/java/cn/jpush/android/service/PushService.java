package cn.jpush.android.service;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Process;
import cn.jpush.android.a;
import cn.jpush.android.api.n;
import cn.jpush.android.c;
import cn.jpush.android.data.JPushLocalNotification;
import cn.jpush.android.e;
import cn.jpush.android.helpers.ConnectingHelper;
import cn.jpush.android.helpers.b;
import cn.jpush.android.helpers.d;
import cn.jpush.android.helpers.f;
import cn.jpush.android.helpers.k;
import cn.jpush.android.util.ac;
import cn.jpush.android.util.af;
import cn.jpush.android.util.ah;
import cn.jpush.android.util.ao;
import cn.jpush.android.util.o;
import cn.jpush.android.util.u;
import com.autonavi.ae.gmap.utils.GLMapStaticValue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class PushService extends Service {
    public static long a;
    public static String b;
    public static boolean c;
    public static boolean d;
    private static boolean j;
    private static final String[] z;
    private b f;
    private ExecutorService g;
    private k h;
    private m i;
    private boolean e = true;
    private int k = 0;
    private int l = 0;
    private long m = 0;
    private c n = null;

    /* JADX WARN: Code restructure failed: missing block: B:100:0x0390, code lost:
        r1 = r0;
        r4 = r2;
        r2 = r1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:101:0x0394, code lost:
        r9 = '$';
     */
    /* JADX WARN: Code restructure failed: missing block: B:102:0x0398, code lost:
        r9 = '+';
     */
    /* JADX WARN: Code restructure failed: missing block: B:103:0x039c, code lost:
        r9 = 'a';
     */
    /* JADX WARN: Code restructure failed: missing block: B:104:0x03a0, code lost:
        r9 = 'N';
     */
    /* JADX WARN: Code restructure failed: missing block: B:105:0x03a4, code lost:
        r5 = '$';
     */
    /* JADX WARN: Code restructure failed: missing block: B:106:0x03a7, code lost:
        r5 = '+';
     */
    /* JADX WARN: Code restructure failed: missing block: B:107:0x03aa, code lost:
        r5 = 'a';
     */
    /* JADX WARN: Code restructure failed: missing block: B:108:0x03ad, code lost:
        r5 = 'N';
     */
    /* JADX WARN: Code restructure failed: missing block: B:109:0x03b0, code lost:
        r1 = r1;
        r0 = r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:10:0x0026, code lost:
        if (r5 != 0) goto L_0x002c;
     */
    /* JADX WARN: Code restructure failed: missing block: B:110:0x03b2, code lost:
        if (r1 > r2) goto L_0x037a;
     */
    /* JADX WARN: Code restructure failed: missing block: B:111:0x03b4, code lost:
        cn.jpush.android.service.PushService.b = new java.lang.String(r0).intern();
        cn.jpush.android.service.PushService.c = false;
        cn.jpush.android.service.PushService.d = false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:112:0x03c5, code lost:
        return;
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
            case 26: goto L_0x014d;
            case 27: goto L_0x0158;
            case 28: goto L_0x0163;
            case 29: goto L_0x016e;
            case 30: goto L_0x0179;
            case 31: goto L_0x0184;
            case 32: goto L_0x018f;
            case 33: goto L_0x019a;
            case 34: goto L_0x01a5;
            case 35: goto L_0x01b0;
            case 36: goto L_0x01bb;
            case 37: goto L_0x01c6;
            case 38: goto L_0x01d1;
            case 39: goto L_0x01dc;
            case 40: goto L_0x01e7;
            case 41: goto L_0x01f2;
            case 42: goto L_0x01fd;
            case 43: goto L_0x0208;
            case 44: goto L_0x0213;
            case 45: goto L_0x021e;
            case 46: goto L_0x0229;
            case 47: goto L_0x0234;
            case 48: goto L_0x023f;
            case 49: goto L_0x024a;
            case 50: goto L_0x0255;
            case 51: goto L_0x0260;
            case 52: goto L_0x026b;
            case 53: goto L_0x0276;
            case 54: goto L_0x0281;
            case 55: goto L_0x028c;
            case 56: goto L_0x0297;
            case 57: goto L_0x02a2;
            case 58: goto L_0x02ad;
            case 59: goto L_0x02b8;
            case 60: goto L_0x02c3;
            case 61: goto L_0x02ce;
            case 62: goto L_0x02d9;
            case 63: goto L_0x02e4;
            case 64: goto L_0x02ef;
            case 65: goto L_0x02fa;
            case 66: goto L_0x0305;
            case 67: goto L_0x0310;
            case 68: goto L_0x031b;
            case 69: goto L_0x0326;
            case 70: goto L_0x0331;
            case 71: goto L_0x033c;
            case 72: goto L_0x0347;
            case 73: goto L_0x0352;
            case 74: goto L_0x035d;
            case 75: goto L_0x0368;
            default: goto L_0x003c;
        };
     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x003c, code lost:
        r3[r2] = r1;
        r2 = 1;
        r1 = "eH\u0015'\u000fJ\u0011A&\u0001JO\r+3PD\u0011\u001e\u0015WCAc@E[\u0011\u0005\u0005]\u0011";
        r0 = 0;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x0044, code lost:
        r3[r2] = r1;
        r2 = 2;
        r1 = "\b\u000b\u0007\"\u0001C\u0011";
        r0 = 1;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x004c, code lost:
        r3[r2] = r1;
        r2 = 3;
        r1 = "LN\u0000<\u0014FN\u0000:@\t\u000b\u000b;\t@\u0011";
        r0 = 2;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:19:0x0054, code lost:
        r3[r2] = r1;
        r2 = 4;
        r1 = "wN\u000f*@LN\u0000<\u0014\u0004I\u0004/\u0014";
        r0 = 3;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x005c, code lost:
        r3[r2] = r1;
        r2 = 5;
        r1 = "t^\u0012&3AY\u0017'\u0003A";
        r0 = 4;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x0064, code lost:
        r3[r2] = r1;
        r2 = 6;
        r1 = "KE-!\u0007ME'/\tHN\u0005nM\u0004H\u000e \u000eAH\u0015'\u000fJ\u0011";
        r0 = 5;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x006c, code lost:
        r3[r2] = r1;
        r2 = 7;
        r1 = "\b\u000b\u0013+\u0013Th\u000e*\u0005\u001e";
        r0 = 6;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x0074, code lost:
        r3[r2] = r1;
        r2 = '\b';
        r1 = "eH\u0015'\u000fJ\u000bLn\u000fJg\u000e)\u0007AO( @\t\u000b\u0002!\u000eJN\u0002:\tKE[";
        r0 = 7;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x007d, code lost:
        r3[r2] = r1;
        r2 = '\t';
        r1 = "jDA \u0005P\\\u000e<\u000b\u0004H\u000e \u000eAH\u0015'\u000fJ\u0005A\t\tRNA;\u0010\u0004_\u000en\u0013PJ\u0013:@GD\u000f \u0005G_\b!\u000e\u0004_\t<\u0005EOO";
        r0 = '\b';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x0087, code lost:
        r3[r2] = r1;
        r2 = '\n';
        r1 = "eH\u0015'\u000fJ\u000bLn\u0012AX\u0015/\u0012Pe\u0004:\u0017KY\n'\u000eCh\r'\u0005J_Mn\u0010MO[";
        r0 = '\t';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x0092, code lost:
        r3[r2] = r1;
        r2 = 11;
        r1 = "mXA-\u000fJE\u0004-\u0014ME\u0006n\u000eK\\On'M]\u0004n\u0015T\u000b\u0015!@VN\u0015<\u0019\n";
        r0 = '\n';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x009d, code lost:
        r3[r2] = r1;
        r2 = '\f';
        r1 = "eG\u0013+\u0001@RA\"\u000fCL\u0004*@MEOn'M]\u0004n\u0015T\u000b\u0015!@VN\u0015<\u0019\n";
        r0 = 11;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x00a8, code lost:
        r3[r2] = r1;
        r2 = '\r';
        r1 = "\u0019\u0016\\s]\u0019\u0016\\s]\u0019\u0016\\s]\u0019\u0016\\s]\u0019\u0016\\s]\u0019\u0016\\s]\u0019\u0016\\s]\u0019\u0016\\s]\u0019\u0016\\s]\u0019\u0016\\s]\u0019\u0016\\s]\u0019\u0016\\s]";
        r0 = '\f';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:29:0x00b3, code lost:
        r3[r2] = r1;
        r2 = 14;
        r1 = "eH\u0015'\u000fJ\u000bLn\u000fJc\u0004/\u0012PI\u0004/\u0014pB\f+\u000fQ_Ac@PB\f+\u000fQ_5'\rAX[";
        r0 = '\r';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x00be, code lost:
        r3[r2] = r1;
        r2 = 15;
        r1 = "GEO$\u0010QX\t`\u0001JO\u0013!\t@\u0005\b \u0014AE\u0015`2al(\u001d4ay";
        r0 = 14;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:31:0x00c9, code lost:
        r3[r2] = r1;
        r2 = 16;
        r1 = "HD\u0002/\f{E\u000e:\tBB\u0002/\u0014MD\u000f";
        r0 = 15;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x00d4, code lost:
        r3[r2] = r1;
        r2 = 17;
        r1 = "GEO$\u0010QX\t`\tI\u0005\u0000 \u0004VD\b*NEH\u0015'\u000fJ\u0005(\u0003?vn0\u001b%w\u007f";
        r0 = 16;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x00df, code lost:
        r3[r2] = r1;
        r2 = 18;
        r1 = "\b\u000b\u0011%\u0007\u001e";
        r0 = 17;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:34:0x00ea, code lost:
        r3[r2] = r1;
        r2 = 19;
        r1 = "I^\r:\t{_\u0018>\u0005";
        r0 = 18;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:35:0x00f5, code lost:
        r3[r2] = r1;
        r2 = com.tencent.open.GameAppOperation.PIC_SYMBOLE;
        r1 = "T^\u0012&?W_\u000e>\u0010AO";
        r0 = 19;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:36:0x0100, code lost:
        r3[r2] = r1;
        r2 = 21;
        r1 = "GEO$\u0010QX\t`\u0001JO\u0013!\t@\u0005\b \u0014AE\u0015`!hb \u001d?pj&\u001d";
        r0 = com.tencent.open.GameAppOperation.PIC_SYMBOLE;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:37:0x010b, code lost:
        r3[r2] = r1;
        r2 = 22;
        r1 = "qE\t/\u000e@G\u0004*@WN\u00138\tGNA/\u0003PB\u000e @\t\u000b";
        r0 = 21;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:38:0x0116, code lost:
        r3[r2] = r1;
        r2 = 23;
        r1 = "GEO$\u0010QX\t`\u0001JO\u0013!\t@\u0005\b \u0014AE\u0015`2ax5\u00012a{4\u001d(";
        r0 = 22;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:39:0x0121, code lost:
        r3[r2] = r1;
        r2 = 24;
        r1 = "WN\u0010\u0011\t@";
        r0 = 23;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:40:0x012c, code lost:
        r3[r2] = r1;
        r2 = 25;
        r1 = "JD\u0015'\u0006MH\u0000:\tKE>,\u0015MO\r+\u0012";
        r0 = 24;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:41:0x0137, code lost:
        r3[r2] = r1;
        r2 = 26;
        r1 = "GEO$\u0010QX\t`\u0001JO\u0013!\t@\u0005\b \u0014AE\u0015`2a{.\u001c4";
        r0 = 25;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:42:0x0142, code lost:
        r3[r2] = r1;
        r2 = 27;
        r1 = "eH\u0015'\u000fJ\u000bLn\bEE\u0005\"\u0005mE\u0007!2A[\u000e<\u0014\u0004Y\u0004>\u000fV_57\u0010A\u0011";
        r0 = 26;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:43:0x014d, code lost:
        r3[r2] = r1;
        r2 = 28;
        r1 = "GC\u0000 \u0007At\u0011/\u0003OJ\u0006+\u000eEF\u0004";
        r0 = 27;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:44:0x0158, code lost:
        r3[r2] = r1;
        r2 = 29;
        r1 = "GEO$\u0010QX\t`\u0001JO\u0013!\t@\u0005\b \u0014AE\u0015`)jb5";
        r0 = 28;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:45:0x0163, code lost:
        r3[r2] = r1;
        r2 = 30;
        r1 = "GEO$\u0010QX\t`\u0001JO\u0013!\t@\u0005\b \u0014AE\u0015`5wn3\u0011'vd4\u0000$";
        r0 = 29;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:46:0x016e, code lost:
        r3[r2] = r1;
        r2 = 31;
        r1 = "\b\u000b\u0002!\u000eJN\u0002:\tKE[";
        r0 = 30;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:47:0x0179, code lost:
        r3[r2] = r1;
        r2 = ' ';
        r1 = "MF><\u0005U^\u0004=\u0014{I\u0018:\u0005W";
        r0 = 31;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:48:0x0184, code lost:
        r3[r2] = r1;
        r2 = '!';
        r1 = "KE2:\u0001V_\"!\rIJ\u000f*@\t\u000b\u000f!\u0014\u0004]\u0000\"\t@\u000b+\u001e\u0015WCA<\u0015JE\b \u0007\u0004\u0006A\u001d\bK^\r*@JD\u0015n\u0002A\u000b\t+\u0012A\u0005";
        r0 = ' ';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:49:0x018f, code lost:
        r3[r2] = r1;
        r2 = '\"';
        r1 = "GEO$\u0010QX\t`\u0001JO\u0013!\t@\u0005\b \u0014AE\u0015`3pd1\u001e5wc";
        r0 = '!';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:4:0x0010, code lost:
        if (r5 <= 1) goto L_0x0012;
     */
    /* JADX WARN: Code restructure failed: missing block: B:50:0x019a, code lost:
        r3[r2] = r1;
        r2 = '#';
        r1 = "HD\u0002/\f{E\u000e:\tBB\u0002/\u0014MD\u000f\u0011\t@";
        r0 = '\"';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:51:0x01a5, code lost:
        r3[r2] = r1;
        r2 = '$';
        r1 = "AE\u0000,\fAt\u0011;\u0013Lt\u0015'\rA";
        r0 = '#';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:52:0x01b0, code lost:
        r3[r2] = r1;
        r2 = '%';
        r1 = "V_\u0002\u0011\u0004AG\u00007";
        r0 = '$';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:53:0x01bb, code lost:
        r3[r2] = r1;
        r2 = '&';
        r1 = "\u0004N\u0019:\u0012Eb\u000f(\u000f\u001e";
        r0 = '%';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:54:0x01c6, code lost:
        r3[r2] = r1;
        r2 = '\'';
        r1 = "GEO$\u0010QX\t`\u0001JO\u0013!\t@\u0005\b \u0014AE\u0015`2ph";
        r0 = '&';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:55:0x01d1, code lost:
        r3[r2] = r1;
        r2 = '(';
        r1 = "qE\u00046\u0010AH\u0015+\u0004\u0004\u0006A'\fHN\u0006/\f\u0004b,n\u0012AZ\u0014+\u0013P\u0005";
        r0 = '\'';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:56:0x01dc, code lost:
        r3[r2] = r1;
        r2 = ')';
        r1 = "eH\u0015'\u000fJ\u000bLn\bEE\u0005\"\u0005wN\u00138\tGN -\u0014MD\u000fnM\u0004J\u0002:\tKE[";
        r0 = '(';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:57:0x01e7, code lost:
        r3[r2] = r1;
        r2 = '*';
        r1 = "KE2:\u0001V_\"!\rIJ\u000f*@\t\u000b\b \u0014AE\u0015t";
        r0 = ')';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:58:0x01f2, code lost:
        r3[r2] = r1;
        r2 = '+';
        r1 = "wN\u00138\tGNA,\u0015JO\r+@\t\u000b";
        r0 = '*';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:59:0x01fd, code lost:
        r3[r2] = r1;
        r2 = ',';
        r1 = "PJ\u0006/\fMJ\u0012n\u0005\\H\u0004>\u0014MD\u000ft";
        r0 = '+';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:5:0x0012, code lost:
        r8 = r6;
        r5 = r1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:60:0x0208, code lost:
        r3[r2] = r1;
        r2 = '-';
        r1 = "GEO$\u0010QX\t`\u0001JO\u0013!\t@\u0005\b \u0014AE\u0015`-qg5\u0007?ty.\r%wx";
        r0 = ',';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:61:0x0213, code lost:
        r3[r2] = r1;
        r2 = '.';
        r1 = "JD\u0015'\u0006MH\u0000:\tKE>'\u0004";
        r0 = '-';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:62:0x021e, code lost:
        r3[r2] = r1;
        r2 = '/';
        r1 = "V_\u0002";
        r0 = '.';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:63:0x0229, code lost:
        r3[r2] = r1;
        r2 = '0';
        r1 = "PJ\u0006=";
        r0 = '/';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:64:0x0234, code lost:
        r3[r2] = r1;
        r2 = '1';
        r1 = "VN\u0011!\u0012P";
        r0 = '0';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:65:0x023f, code lost:
        r3[r2] = r1;
        r2 = '2';
        r1 = "@N\u0002<\u0005EX\u0004\u0000\u000fPB\u0007'\u0003E_\b!\u000e\u001e";
        r0 = '1';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:66:0x024a, code lost:
        r3[r2] = r1;
        r2 = '3';
        r1 = "GY\u0000=\b{G\u000e)";
        r0 = '2';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:67:0x0255, code lost:
        r3[r2] = r1;
        r2 = '4';
        r1 = "JD\u0015'\u0006MH\u0000:\tKE>,\u0015MO\r+\u0012{B\u0005";
        r0 = '3';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:68:0x0260, code lost:
        r3[r2] = r1;
        r2 = '5';
        r1 = "E[\u0011";
        r0 = '4';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:69:0x026b, code lost:
        r3[r2] = r1;
        r2 = '6';
        r1 = "GEO$\u0010QX\t`\u0001JO\u0013!\t@\u0005\b \u0014AE\u0015`#ke/\u000b#pb7\u00074}t\"\u0006!jl$";
        r0 = '5';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:6:0x0017, code lost:
        r10 = r5[r6];
     */
    /* JADX WARN: Code restructure failed: missing block: B:70:0x0276, code lost:
        r3[r2] = r1;
        r2 = '7';
        r1 = "lJ\u000f*\fA\u000b\u0000-\u0014MD\u000fn\u0006KYA#\u0015H_\bn\u0014][\u0004nZ\u0004";
        r0 = '6';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:71:0x0281, code lost:
        r3[r2] = r1;
        r2 = '8';
        r1 = "VN\u0011!\u0012P\u0005\u00046\u0014VJO'\u000eBD";
        r0 = '7';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:72:0x028c, code lost:
        r3[r2] = r1;
        r2 = '9';
        r1 = "W_\u000e>?PC\u0013+\u0001@";
        r0 = '8';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:73:0x0297, code lost:
        r3[r2] = r1;
        r2 = ':';
        r1 = "JD\u0015'\u0006MH\u0000:\tKE>#\u0001\\E\u0014#";
        r0 = '9';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:74:0x02a2, code lost:
        r3[r2] = r1;
        r2 = ';';
        r1 = "EG\b/\u0013";
        r0 = ':';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:75:0x02ad, code lost:
        r3[r2] = r1;
        r2 = '<';
        r1 = "WB\r+\u000eGN>>\u0015WC>:\tIN";
        r0 = ';';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:76:0x02b8, code lost:
        r3[r2] = r1;
        r2 = '=';
        r1 = "QX\u0004<?CY\u000e;\u000e@";
        r0 = '<';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:77:0x02c3, code lost:
        r3[r2] = r1;
        r2 = '>';
        r1 = "GD\u000f \u0005G_\b!\u000e\tX\u0015/\u0014A";
        r0 = '=';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:78:0x02ce, code lost:
        r3[r2] = r1;
        r2 = '?';
        r1 = "wN\u00138\tGN)+\fTN\u0013";
        r0 = '>';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:79:0x02d9, code lost:
        r3[r2] = r1;
        r2 = '@';
        r1 = "eH\u0015'\u000fJ\u0011A'\u000eM_A\u001e\u0015WC2+\u0012RB\u0002+";
        r0 = '?';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:7:0x001b, code lost:
        switch((r8 % 5)) {
            case 0: goto L_0x0394;
            case 1: goto L_0x0398;
            case 2: goto L_0x039c;
            case 3: goto L_0x03a0;
            default: goto L_0x001e;
        };
     */
    /* JADX WARN: Code restructure failed: missing block: B:80:0x02e4, code lost:
        r3[r2] = r1;
        r2 = 'A';
        r1 = "wN\u00138\tGNA#\u0001MEA:\bVN\u0000*@\t\u000b\u0015&\u0012AJ\u0005\u0007\u0004\u001e";
        r0 = '@';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:81:0x02ef, code lost:
        r3[r2] = r1;
        r2 = 'B';
        r1 = "n{\u0014=\b\u0004Y\u0014 \u000eME\u0006n\u0003LN\u0002%\u0005@\u000b\u0007/\tHN\u0005o";
        r0 = 'A';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:82:0x02fa, code lost:
        r3[r2] = r1;
        r2 = 'C';
        r1 = "J^\r\"";
        r0 = 'B';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:83:0x0305, code lost:
        r3[r2] = r1;
        r2 = 'D';
        r1 = "sNA(\u000fQE\u0005n\u0014LNA/\u0010T`\u00047@MXA-\bEE\u0006+\u0004\n\u000b6'\fH\u000b\u0013+MVN\u0006'\u0013PN\u0013`";
        r0 = 'C';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:84:0x0310, code lost:
        r3[r2] = r1;
        r2 = 'E';
        r1 = "KE%'\u0013GD\u000f \u0005G_\u0004*@EE\u0005n\u0012A_\u00137@VN\u0012:\u0001V_A-\u000fJEAc@@N\r/\u0019\u001e";
        r0 = 'D';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:85:0x031b, code lost:
        r3[r2] = r1;
        r2 = 'F';
        r1 = "eH\u0015'\u000fJ\u000bLn\u0012A_\u00137#KE\u000f+\u0003P\u000bLn\u0004MX\u0002!\u000eJN\u0002:\u0005@\u007f\b#\u0005W\u0011";
        r0 = 'E';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:86:0x0326, code lost:
        r3[r2] = r1;
        r2 = 'G';
        r1 = "eH\u0015'\u000fJ\u000bLn\u000fJc\u0004/\u0012PI\u0004/\u0014w^\u0002-\u0005AOAc@GD\u000f \u0005G_\b!\u000e\u001e";
        r0 = 'F';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:87:0x0331, code lost:
        r3[r2] = r1;
        r2 = 'H';
        r1 = "mXA-\u000fJE\u0004-\u0014ME\u0006n\u000eK\\On'M]\u0004n\u0015T\u000b\u0015!@VN\u0012:\u0001V_O";
        r0 = 'G';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:88:0x033c, code lost:
        r3[r2] = r1;
        r2 = 'I';
        r1 = "eH\u0015'\u000fJ\u000bLn\u0012AX\u0015/\u0012P\u007f\t+\u000elN\u0000<\u0014FN\u0000:";
        r0 = 'H';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:89:0x0347, code lost:
        r3[r2] = r1;
        r2 = 'J';
        r1 = "eG\u0013+\u0001@RA\"\u000fCL\u0004*@MEOn'M]\u0004n\u0015T\u000b\u0015!@VN\u0012:\u0001V_O";
        r0 = 'I';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:8:0x001e, code lost:
        r9 = '`';
     */
    /* JADX WARN: Code restructure failed: missing block: B:90:0x0352, code lost:
        r3[r2] = r1;
        r2 = 'K';
        r1 = "eH\u0015'\u000fJ\u000bLn\u000fJo\b=\u0003KE\u000f+\u0003PN\u0005nM\u0004H\u000e \u000eAH\u0015'\u000fJ\u0011";
        r0 = 'J';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:91:0x035d, code lost:
        r3[r2] = r1;
        r2 = 'L';
        r1 = "KE%+\u0013PY\u000e7@\t\u000b\u0011<\u000fGN\u0012=)@\u0011";
        r0 = 'K';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:92:0x0368, code lost:
        r3[r2] = r1;
        cn.jpush.android.service.PushService.z = r3;
        cn.jpush.android.service.PushService.j = false;
        r0 = "EG\r".toCharArray();
        r1 = r0.length;
        r2 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:93:0x0378, code lost:
        if (r1 > 1) goto L_0x03b2;
     */
    /* JADX WARN: Code restructure failed: missing block: B:94:0x037a, code lost:
        r4 = r2;
        r1 = r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:95:0x037f, code lost:
        r6 = r1[r2];
     */
    /* JADX WARN: Code restructure failed: missing block: B:96:0x0383, code lost:
        switch((r4 % 5)) {
            case 0: goto L_0x03a4;
            case 1: goto L_0x03a7;
            case 2: goto L_0x03aa;
            case 3: goto L_0x03ad;
            default: goto L_0x0386;
        };
     */
    /* JADX WARN: Code restructure failed: missing block: B:97:0x0386, code lost:
        r5 = '`';
     */
    /* JADX WARN: Code restructure failed: missing block: B:98:0x0388, code lost:
        r1[r2] = (char) (r5 ^ r6);
        r2 = r4 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:99:0x038e, code lost:
        if (r1 != 0) goto L_0x03b0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:9:0x0020, code lost:
        r5[r6] = (char) (r9 ^ r10);
        r6 = r8 + 1;
     */
    static {
        /*
            Method dump skipped, instructions count: 1146
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.jpush.android.service.PushService.<clinit>():void");
    }

    private void a(long j2) {
        ac.b();
        new Thread(new l(this, j2)).start();
    }

    public static /* synthetic */ void a(PushService pushService, long j2) {
        ac.b(z[5], z[8] + j2);
        j = true;
        pushService.k = 0;
        pushService.l = 0;
        ConnectingHelper.sendConnectionChanged(pushService.getApplicationContext(), a.a);
        pushService.i.sendEmptyMessageDelayed(1005, 15000L);
    }

    public static /* synthetic */ void a(PushService pushService, boolean z2) {
        if (z2 || System.currentTimeMillis() - pushService.m >= 30000) {
            ac.b(z[5], z[4]);
            pushService.i.removeMessages(1005);
            if (!k.b.get() && j) {
                Long valueOf = Long.valueOf(a.n());
                int l = a.l();
                long y = a.y();
                short iMLoginFlag = ConnectingHelper.getIMLoginFlag();
                new StringBuilder(z[3]).append(y).append(z[2]).append((int) iMLoginFlag);
                ac.b();
                PushProtocol.HbJPush(k.a.get(), valueOf.longValue(), l, y, iMLoginFlag);
                if (!pushService.i.hasMessages(GLMapStaticValue.AM_PARAMETERNAME_PROCESS_3DOBJECT)) {
                    pushService.i.sendEmptyMessageDelayed(GLMapStaticValue.AM_PARAMETERNAME_PROCESS_3DOBJECT, 10000L);
                    return;
                }
                return;
            }
        }
        ac.a();
    }

    private void a(String str, String str2) {
        try {
            new StringBuilder(z[1]).append(str2);
            ac.b();
            if (ao.a(str) || ao.a(str2)) {
                String.format(z[0], str, str2);
                ac.e();
                if (this.i.hasMessages(1005)) {
                    ac.a();
                    this.i.removeMessages(1005);
                }
                ConnectingHelper.sendConnectionChanged(getApplicationContext(), a.b);
                if (this.h == null) {
                    ac.d();
                } else {
                    this.h.a();
                }
                stopSelf();
                return;
            }
            if (this.i.hasMessages(1005)) {
                ac.a();
                this.i.removeMessages(1005);
            }
            ConnectingHelper.sendConnectionChanged(getApplicationContext(), a.b);
            if (this.h == null) {
                ac.d();
            } else {
                this.h.a();
            }
            stopSelf();
        } catch (Exception e) {
            if (this.i.hasMessages(1005)) {
                ac.a();
                this.i.removeMessages(1005);
            }
            ConnectingHelper.sendConnectionChanged(getApplicationContext(), a.b);
            if (this.h == null) {
                ac.d();
            } else {
                this.h.a();
            }
            stopSelf();
        } catch (Throwable th) {
            if (this.i.hasMessages(1005)) {
                ac.a();
                this.i.removeMessages(1005);
            }
            ConnectingHelper.sendConnectionChanged(getApplicationContext(), a.b);
            if (this.h == null) {
                ac.d();
            } else {
                this.h.a();
            }
            stopSelf();
            throw th;
        }
    }

    public static void a(ExecutorService executorService) {
        ac.a();
        if (executorService != null) {
            executorService.shutdown();
            try {
                if (!executorService.awaitTermination(100L, TimeUnit.MILLISECONDS)) {
                    executorService.shutdownNow();
                    if (!executorService.awaitTermination(100L, TimeUnit.MILLISECONDS)) {
                        ac.a();
                    }
                }
            } catch (InterruptedException e) {
                executorService.shutdownNow();
                ac.a();
                Thread.currentThread().interrupt();
            }
            ac.a();
        }
    }

    public static boolean a() {
        return j;
    }

    public void b() {
        ac.a(z[5], z[73]);
        if (f()) {
            ac.b(z[5], z[72]);
        } else if (!j || e()) {
            this.i.removeMessages(1011);
            this.i.removeMessages(1005);
            c();
        } else {
            ac.b(z[5], z[74]);
        }
    }

    public static /* synthetic */ void b(PushService pushService, long j2) {
        ac.b(z[5], z[75] + j2);
        j = false;
        boolean z2 = a.c(pushService.getApplicationContext()) > 0;
        if (k.a.get() != 0 || !z2) {
            pushService.l = 0;
            ConnectingHelper.sendConnectionChanged(pushService.getApplicationContext(), a.b);
            a(pushService.g);
            if (cn.jpush.android.util.b.b(pushService.getApplicationContext())) {
                pushService.d();
            }
            pushService.k++;
            return;
        }
        ac.b();
    }

    private synchronized void c() {
        ac.b(z[5], z[10] + Process.myPid());
        if (!cn.jpush.android.util.b.b(getApplicationContext())) {
            ac.c(z[5], z[9]);
        } else {
            if (this.g != null && !this.g.isShutdown()) {
                ac.b();
                a(this.g);
            }
            this.g = Executors.newSingleThreadExecutor();
            this.h = new k(getApplicationContext(), this.i, false);
            this.g.execute(this.h);
        }
    }

    public static /* synthetic */ void c(PushService pushService) {
        pushService.l++;
        ac.b(z[5], z[14] + pushService.l);
        ac.a(z[5], z[13]);
        if (f()) {
            ac.b(z[5], z[11]);
            pushService.i.sendEmptyMessageDelayed(1005, 10000L);
        } else if (!j || pushService.e()) {
            if (pushService.h != null) {
                pushService.h.a();
            }
            pushService.d();
        } else {
            ac.b(z[5], z[12]);
            pushService.i.sendEmptyMessageDelayed(1005, 5000L);
        }
    }

    public static /* synthetic */ void c(PushService pushService, long j2) {
        ac.b(z[5], z[71] + j2);
        if (j2 != k.a.get()) {
            ac.b();
            return;
        }
        if (pushService.i.hasMessages(GLMapStaticValue.AM_PARAMETERNAME_PROCESS_3DOBJECT)) {
            pushService.i.removeMessages(GLMapStaticValue.AM_PARAMETERNAME_PROCESS_3DOBJECT);
        }
        pushService.m = System.currentTimeMillis();
        pushService.l = 0;
        h.a(pushService.getApplicationContext()).d(pushService.getApplicationContext());
        if (a.a()) {
            o.c(pushService.getApplicationContext());
        }
        if (a.f()) {
            u.a(pushService.getApplicationContext());
        }
        if (a.i(pushService.getApplicationContext()) && a.b()) {
            k.a(pushService.getApplicationContext(), true, b, d, c);
        }
        if (a.d()) {
            k.a(pushService.getApplicationContext(), (String) null);
        }
        if (a.n(pushService.getApplicationContext())) {
            af.a(pushService.getApplicationContext());
        }
        if (e.m) {
            pushService.a(3600L);
        }
    }

    private void d() {
        ac.b(z[5], z[70] + this.k);
        if (!ServiceInterface.e(getApplicationContext()) && cn.jpush.android.util.b.b(getApplicationContext()) && !ConnectingHelper.isServiceStopedByRegister()) {
            int f = cn.jpush.android.util.b.f(getApplicationContext());
            int pow = (int) (Math.pow(2.0d, this.k) * 3.0d * 1000.0d);
            int p = a.p();
            if (pow > (p * 1000) / 2) {
                pow = (p * 1000) / 2;
            }
            if (this.k >= 5 && f != 1) {
                ac.b();
            } else if (!this.i.hasMessages(1011)) {
                ac.b(z[5], z[69] + pow);
                this.i.sendEmptyMessageDelayed(1011, pow);
            } else {
                ac.b();
            }
        }
    }

    private boolean e() {
        return this.l > 1;
    }

    private static boolean f() {
        return k.a.get() != 0 && !j;
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        ac.b();
        return this.n;
    }

    @Override // android.app.Service
    public void onCreate() {
        ac.c();
        new StringBuilder(z[65]).append(Thread.currentThread().getId());
        ac.a();
        e.l = true;
        this.n = new f(this);
        a = Thread.currentThread().getId();
        super.onCreate();
        this.i = new m(this);
        ac.b(z[5], z[64]);
        if (!e.a(getApplicationContext())) {
            this.e = false;
        } else {
            this.e = cn.jpush.android.util.b.q(getApplicationContext());
            if (!this.e) {
                ac.d(z[5], z[66]);
            } else {
                Context applicationContext = getApplicationContext();
                String D = a.D();
                if (!ao.a(D) && !z[67].equals(D) && !e.f.equalsIgnoreCase(D)) {
                    ac.b(z[63], z[68]);
                    a.A();
                    ah.a(applicationContext);
                }
                cn.jpush.android.util.b.u(getApplicationContext());
            }
        }
        ServiceInterface.b(getApplicationContext(), true);
        if (e.m) {
            a(0L);
        }
    }

    @Override // android.app.Service
    public void onDestroy() {
        super.onDestroy();
        new StringBuilder(z[76]).append(Process.myPid());
        ac.b();
        e.l = false;
        if (this.i != null) {
            this.i.removeCallbacksAndMessages(null);
        }
        cn.jpush.android.util.b.r(getApplicationContext());
        if (!(this.h == null || k.a.get() == 0)) {
            this.h.a();
        }
        if (this.g != null && !this.g.isShutdown()) {
            a(this.g);
        }
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int i, int i2) {
        int a2;
        ac.b(z[5], z[42] + intent + z[18] + e.c + z[31] + k.a.get());
        if (!this.e) {
            ac.b(z[5], z[33]);
            this.i.sendEmptyMessageDelayed(1003, 100L);
        } else {
            Bundle bundle = null;
            String str = null;
            if (intent != null) {
                str = intent.getAction();
                bundle = intent.getExtras();
            }
            if (bundle != null) {
                new StringBuilder(z[43]).append(bundle.toString());
                ac.a();
            }
            if (!(str == null || bundle == null)) {
                ac.b(z[5], z[41] + str);
                if (z[17].equals(str)) {
                    byte[] byteArray = bundle.getByteArray(z[32]);
                    if (byteArray == null || byteArray.length < 24) {
                        ac.e(z[5], z[40]);
                    } else {
                        PushProtocol.IMProtocol(k.a.get(), byteArray, 0);
                    }
                } else if (z[39].equals(str)) {
                    cn.jpush.android.util.b.m(this);
                    if (k.a.get() == 0) {
                        b();
                    } else {
                        int i3 = bundle.getInt(z[37], 0);
                        if (ao.a(bundle.getString(z[47]))) {
                            this.i.sendEmptyMessage(1005);
                        } else if (i3 == 0) {
                            this.i.removeMessages(1005);
                            if (!this.i.hasMessages(1004)) {
                                this.i.sendEmptyMessage(1005);
                            }
                        } else {
                            this.i.removeMessages(1005);
                            this.i.removeMessages(1004);
                            this.i.sendEmptyMessageDelayed(1004, i3);
                        }
                    }
                    cn.jpush.android.util.b.b();
                } else if (z[54].equals(str)) {
                    cn.jpush.android.util.b.m(this);
                    String string = bundle.getString(z[62]);
                    if (!ao.a(string)) {
                        if (string.equals(a.a.name())) {
                            ac.b();
                            if (k.a.get() == 0) {
                                b();
                            } else {
                                this.i.sendEmptyMessage(1006);
                            }
                            cn.jpush.android.util.b.b();
                        } else {
                            string.equals(a.b.name());
                        }
                    }
                    ac.b();
                    cn.jpush.android.util.b.b();
                } else if (z[29].equals(str)) {
                    a.b(getApplicationContext(), 0);
                    if (k.a.get() == 0) {
                        c();
                    } else {
                        ac.b();
                    }
                } else if (z[34].equals(str)) {
                    a.b(getApplicationContext(), 1);
                    a(bundle.getString(z[53]), e.f);
                } else if (z[26].equals(str)) {
                    String string2 = bundle.getString(z[49]);
                    String string3 = bundle.getString(z[56]);
                    if (string2 != null) {
                        new StringBuilder(z[27]).append(string2).append(z[38]).append(string3);
                        ac.a();
                        if (ao.a(string2)) {
                            ac.a();
                        } else if (string2.equals(z[51])) {
                            cn.jpush.android.api.c.a().e(getApplicationContext());
                        } else {
                            ac.d();
                        }
                    }
                } else if (z[30].equals(str)) {
                    if (bundle.getInt(z[61], -1) != -1) {
                        ac.b();
                    }
                } else if (z[21].equals(str)) {
                    if (k.a.get() == 0) {
                        c();
                    }
                    String string4 = bundle.getString(z[59]);
                    String string5 = bundle.getString(z[48]);
                    long j2 = bundle.getLong(z[24], 0L);
                    if (!(string4 == null && string5 == null)) {
                        this.f = b.a(getApplicationContext(), this.i);
                        JSONObject jSONObject = new JSONObject();
                        if (string4 != null) {
                            try {
                                jSONObject.put(z[59], string4);
                            } catch (JSONException e) {
                                new StringBuilder(z[44]).append(e.getMessage());
                                ac.d();
                            }
                        }
                        if (string5 != null) {
                            jSONObject.put(z[48], string5);
                        }
                        this.f.a(new cn.jpush.a.a.a.k(j2, e.f, jSONObject.toString()), 20000);
                    }
                } else if (z[23].equals(str)) {
                    a.b(getApplicationContext(), 0);
                    if (k.a.get() == 0) {
                        c();
                    } else {
                        ac.b();
                    }
                } else if (!z[57].equals(str) && !z[15].equals(str)) {
                    if (z[45].equals(str)) {
                        int i4 = bundle.getInt(z[19]);
                        new StringBuilder(z[55]).append(i4);
                        ac.a();
                        switch (i4) {
                            case 1:
                                a.a(getApplicationContext(), bundle.getString(z[52]), bundle.getString(z[25]));
                                break;
                            case 2:
                                int i5 = bundle.getInt(z[58]);
                                int b2 = d.b();
                                if (i5 < b2) {
                                    int i6 = b2 - i5;
                                    new StringBuilder(z[50]).append(i6);
                                    ac.a();
                                    n.a(getApplicationContext(), i6);
                                }
                                a.a(getApplicationContext(), i5);
                                break;
                            case 3:
                                a.b(getApplicationContext(), bundle.getString(z[36]));
                                break;
                            case 4:
                                a.a(getApplicationContext(), bundle.getString(z[60]));
                                break;
                            case 5:
                                a.a(getApplicationContext(), bundle.getBoolean(z[20]));
                                break;
                            case 6:
                                h.a(getApplicationContext()).a(getApplicationContext(), (JPushLocalNotification) bundle.getSerializable(z[16]));
                                break;
                            case 7:
                                h.a(getApplicationContext()).a(getApplicationContext(), bundle.getLong(z[35]));
                                break;
                            case 8:
                                h.a(getApplicationContext()).b(getApplicationContext());
                                break;
                            case 9:
                                int i7 = bundle.getInt(z[46]);
                                if (!d.b(i7)) {
                                    d.a(i7);
                                }
                                if (d.b() > a.b(getApplicationContext()) && (a2 = d.a()) != 0) {
                                    n.b(getApplicationContext(), a2);
                                    break;
                                }
                                break;
                            case 10:
                                n.a(getApplicationContext());
                                break;
                            case 11:
                                k.a(getApplicationContext(), bundle.getString(z[28]));
                                break;
                        }
                    } else {
                        new StringBuilder(z[22]).append(str);
                        ac.d();
                    }
                }
            }
        }
        return 1;
    }

    @Override // android.app.Service
    public boolean onUnbind(Intent intent) {
        ac.e();
        return super.onUnbind(intent);
    }
}
