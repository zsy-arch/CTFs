package cn.jpush.android;

import android.content.Context;
import android.content.SharedPreferences;
import cn.jpush.android.helpers.e;
import cn.jpush.android.util.ac;
import cn.jpush.android.util.af;
import cn.jpush.android.util.ao;
import cn.jpush.android.util.b;
import cn.jpush.android.util.i;
import java.util.Random;

/* loaded from: classes.dex */
public final class a extends i {
    private static final String[] z;

    /* JADX WARN: Code restructure failed: missing block: B:100:0x039a, code lost:
        r9 = 'y';
     */
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
            case 76: goto L_0x0373;
            case 77: goto L_0x037e;
            case 78: goto L_0x0389;
            default: goto L_0x003c;
        };
     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x003c, code lost:
        r3[r2] = r1;
        r2 = 1;
        r1 = "SBcW^";
        r0 = 0;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x0044, code lost:
        r3[r2] = r1;
        r2 = 2;
        r1 = "\f\u0019=\u001b\u000b\u00103&\u001c\u001c\u0011\u0005?\u0017";
        r0 = 1;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x004c, code lost:
        r3[r2] = r1;
        r2 = 3;
        r1 = "\u0006\t&\u0010\r\u000731\t\u001e\t\t)";
        r0 = 2;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:19:0x0054, code lost:
        r3[r2] = r1;
        r2 = 4;
        r1 = "\u000b\u00015\u0010";
        r0 = 3;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x005c, code lost:
        r3[r2] = r1;
        r2 = 5;
        r1 = "\u0006\t&\u0010\r\u00073%\u0010\n";
        r0 = 4;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x0064, code lost:
        r3[r2] = r1;
        r2 = 6;
        r1 = "\u000e\r#\r1\u0010\t \u0016\u001c\u00163<\u0016\r\u0003\u00189\u0016\u0000";
        r0 = 5;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x006c, code lost:
        r3[r2] = r1;
        r2 = 7;
        r1 = "\u0011\t\"\u000f\u0007\u0001\t#&\u0002\u0003\u0019>\u001a\u0006\u0007\b\u000f\r\u0007\u000f\t";
        r0 = 6;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x0074, code lost:
        r3[r2] = r1;
        r2 = '\b';
        r1 = "\u0011\t$\r\u0007\f\u000b\u000f\n\u0007\u000e\t>\u001a\u000b=\u001c%\n\u0006=\u00189\u0014\u000b";
        r0 = 7;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x007d, code lost:
        r3[r2] = r1;
        r2 = '\t';
        r1 = "\u0001\u0003>\u0017\u000b\u0001\u00189\u0017\t=\u001f$\u0018\u001a\u0007";
        r0 = '\b';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x0087, code lost:
        r3[r2] = r1;
        r2 = '\n';
        r1 = "\u0011\b;&\u0018\u0007\u001e#\u0010\u0001\f";
        r0 = '\t';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x0092, code lost:
        r3[r2] = r1;
        r2 = 11;
        r1 = "\u000e\r#\r1\u0005\u0003?\u001d1\u0001\u0003>\u00171\u0012\u0003\"\r";
        r0 = '\n';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x009d, code lost:
        r3[r2] = r1;
        r2 = '\f';
        r1 = "\u0011\t$\r\u0007\f\u000b\u000f\t\u001b\u0011\u0004\u000f\r\u0007\u000f\t";
        r0 = 11;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x00a8, code lost:
        r3[r2] = r1;
        r2 = '\r';
        r1 = "\u000e\u00037\u0010\u0000=\u0000?\u001a\u000f\u000e3$\u0010\u0003\u0007";
        r0 = '\f';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:29:0x00b3, code lost:
        r3[r2] = r1;
        r2 = 14;
        r1 = "\u000e\u00037\u0010\u0000=\u001f5\u000b\u0018\u0007\u001e\u000f\r\u0007\u000f\t";
        r0 = '\r';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x00be, code lost:
        r3[r2] = r1;
        r2 = 15;
        r1 = "\b\u001c%\n\u0006=\u001e5\u001e\u0007\u0011\u00185\u000b1\u0001\u00034\u001c";
        r0 = 14;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:31:0x00c9, code lost:
        r3[r2] = r1;
        r2 = 16;
        r1 = "\u0006\t6\u0018\u001b\u000e\u0018\u000f\u001a\u0001\f\u0002\u000f\t\u0001\u0010\u0018";
        r0 = 15;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x00d4, code lost:
        r3[r2] = r1;
        r2 = 17;
        r1 = "\u0011\t\"\u000f\u0007\u0001\t\u000f\n\u001a\r\u001c5\u001d";
        r0 = 16;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x00df, code lost:
        r3[r2] = r1;
        r2 = 18;
        r1 = "\f\u0019=\u001b\u000b\u001031\t\u001e\u000b\b";
        r0 = 17;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:34:0x00ea, code lost:
        r3[r2] = r1;
        r2 = 19;
        r1 = "\u000e\u000e#&\u001c\u0007\u001c?\u000b\u001a=\t>\u0018\f\u000e\t";
        r0 = 18;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:35:0x00f5, code lost:
        r3[r2] = r1;
        r2 = com.tencent.open.GameAppOperation.PIC_SYMBOLE;
        r1 = "\u000e\r#\r1\u0010\t \u0016\u001c\u001634\u001c\u0018\u000b\u000f5&\u0007\f\n?";
        r0 = 19;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:36:0x0100, code lost:
        r3[r2] = r1;
        r2 = 21;
        r1 = "\f\u0019=\u001b\u000b\u00103>\f\u0003";
        r0 = com.tencent.open.GameAppOperation.PIC_SYMBOLE;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:37:0x010b, code lost:
        r3[r2] = r1;
        r2 = 22;
        r1 = "\f\u0019=\u001b\u000b\u001031\t\u001e\u0011\t3\u000b\u000b\u0016";
        r0 = 21;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:38:0x0116, code lost:
        r3[r2] = r1;
        r2 = 23;
        r1 = "P\u000eiI\n\u0007\\6H\bZT5\u0018\bVUe@]\u0004]4A\\U\u000ea@\rT_";
        r0 = 22;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:39:0x0121, code lost:
        r3[r2] = r1;
        r2 = 24;
        r1 = "\u000e\r#\r1\u0011\u0005#&\u001c\u0007\u001d%\u001c\u001d\u00163$\u0010\u0003\u0007";
        r0 = 23;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:40:0x012c, code lost:
        r3[r2] = r1;
        r2 = 25;
        r1 = "\u0000\r3\u0012\u001b\u00123\"\u001c\u001e\r\u001e$&\u000f\u0006\b\"";
        r0 = 24;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:41:0x0137, code lost:
        r3[r2] = r1;
        r2 = 26;
        r1 = "\u000e\r#\r1\u0005\u0003?\u001d1\u0011\u0005#";
        r0 = 25;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:42:0x0142, code lost:
        r3[r2] = r1;
        r2 = 27;
        r1 = "\u0010\t \u0016\u001c\u00163<\u0016\r\u0003\u00189\u0016\u0000=\n\"\u001c\u001f\u0017\t>\u001a\u0017";
        r0 = 26;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:43:0x014d, code lost:
        r3[r2] = r1;
        r2 = 28;
        r1 = "\u000e\r#\r1\u0001\u00045\u001a\u0005=\u0019#\u001c\u001c\u0003\u001c &\u001d\u0016\r$\f\u001d";
        r0 = 27;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:44:0x0158, code lost:
        r3[r2] = r1;
        r2 = 29;
        r1 = "\u0000\r3\u0012\u001b\u00123%\n\u000b\u001031\u001d\n\u0010";
        r0 = 28;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:45:0x0163, code lost:
        r3[r2] = r1;
        r2 = 30;
        r1 = "\f\u000e\u000f\u0015\u000f\u0011\u0018$\u0010\u0003\u0007";
        r0 = 29;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:46:0x016e, code lost:
        r3[r2] = r1;
        r2 = 31;
        r1 = "\u0006\t6\u0018\u001b\u000e\u0018\u000f\u001a\u0001\f\u0002\u000f\u0010\u001e";
        r0 = 30;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:47:0x0179, code lost:
        r3[r2] = r1;
        r2 = ' ';
        r1 = "S]cW]SBaN@S\\f";
        r0 = 31;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:48:0x0184, code lost:
        r3[r2] = r1;
        r2 = '!';
        r1 = "\f\t(\r1\u0010\u00054";
        r0 = ' ';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:49:0x018f, code lost:
        r3[r2] = r1;
        r2 = '\"';
        r1 = "\f\u0003$\u0010\b\u000b\u000f1\r\u0007\r\u0002\u000f\u001c\u0000\u0003\u000e<\u001c\n";
        r0 = '!';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:4:0x0010, code lost:
        if (r5 <= 1) goto L_0x0012;
     */
    /* JADX WARN: Code restructure failed: missing block: B:50:0x019a, code lost:
        r3[r2] = r1;
        r2 = '#';
        r1 = "\n\t1\u000b\u001a\u0000\t1\r1\u000b\u0002$\u001c\u001c\u0014\r<";
        r0 = '\"';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:51:0x01a5, code lost:
        r3[r2] = r1;
        r2 = '$';
        r1 = "\u000e\r#\r1\u0001\u0003>\u0017\u000b\u0001\u00189\u0016\u0000=\u0018)\t\u000b";
        r0 = '#';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:52:0x01b0, code lost:
        r3[r2] = r1;
        r2 = '%';
        r1 = "\f\u000e\u000f\f\u001e\u000e\u00031\u001d";
        r0 = '$';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:53:0x01bb, code lost:
        r3[r2] = r1;
        r2 = '&';
        r1 = "5%\u00160";
        r0 = '%';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:54:0x01c6, code lost:
        r3[r2] = r1;
        r2 = '\'';
        r1 = "\u0006\t&\u001a\u0007\u000739\u001d1\u0005\t>\u001c\u001c\u0003\u00185\u001d";
        r0 = '&';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:55:0x01d1, code lost:
        r3[r2] = r1;
        r2 = '(';
        r1 = "\f\u0019=\u001b\u000b\u00103%\u000b\u0002";
        r0 = '\'';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:56:0x01dc, code lost:
        r3[r2] = r1;
        r2 = ')';
        r1 = "\n\u0018$\tTMCaA\\LUbW\\RBaAWXU`@WM";
        r0 = '(';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:57:0x01e7, code lost:
        r3[r2] = r1;
        r2 = '*';
        r1 = "\u0006\t&\u0010\r\u00073=\u0018\u0007\f39\u0014\u000b\u000b";
        r0 = ')';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:58:0x01f2, code lost:
        r3[r2] = r1;
        r2 = '+';
        r1 = "\u000e\r#\r1\u0010\t \u0016\u001c\u001639\u0017\n\u0007\u0014";
        r0 = '*';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:59:0x01fd, code lost:
        r3[r2] = r1;
        r2 = ',';
        r1 = "\u000e\r#\r1\u0005\u0003?\u001d1\u0001\u0003>\u00171\u000b\u001c";
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
        r1 = "\f\u0003$\u0010\b\u000b\u000f1\r\u0007\r\u0002\u000f\u0017\u001b\u000f";
        r0 = ',';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:61:0x0213, code lost:
        r3[r2] = r1;
        r2 = '.';
        r1 = "\u0006\t&\u0010\r\u00073=\u0018\u0007\f3=\u0018\r";
        r0 = '-';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:62:0x021e, code lost:
        r3[r2] = r1;
        r2 = '/';
        r1 = "\u0006\t&\u0010\r\u00073=\u0018\u0007\f31\u0017\n\u0010\u00039\u001d1\u000b\b";
        r0 = '.';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:63:0x0229, code lost:
        r3[r2] = r1;
        r2 = '0';
        r1 = "\b\u001c%\n\u0006=\u001f1\u000f\u000b=\u000f%\n\u001a\r\u0001\u000f\u001b\u001b\u000b\u00004\u001c\u001c";
        r0 = '/';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:64:0x0234, code lost:
        r3[r2] = r1;
        r2 = '1';
        r1 = "\u0006\t&\u0010\r\u00073\"\u001c\t\u000b\u001f$\u001c\u001c\u0007\b\u000f\u0018\u001e\u0012\u00075\u0000";
        r0 = '0';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:65:0x023f, code lost:
        r3[r2] = r1;
        r2 = '2';
        r1 = "\u0006\t&\u0010\r\u000733\u0011\u000f\f\u00025\u0015";
        r0 = '1';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:66:0x024a, code lost:
        r3[r2] = r1;
        r2 = '3';
        r1 = "\u0011\t#\n\u0007\r\u0002\u000f\u0010\n";
        r0 = '2';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:67:0x0255, code lost:
        r3[r2] = r1;
        r2 = '4';
        r1 = "\u000f\r(Y\u0000\r\u00189\u001f\u0007\u0001\r$\u0010\u0001\fV";
        r0 = '3';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:68:0x0260, code lost:
        r3[r2] = r1;
        r2 = '5';
        r1 = "\u0012\u0019#\u00111\u0017\b9\u001d";
        r0 = '4';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:69:0x026b, code lost:
        r3[r2] = r1;
        r2 = '6';
        r1 = "\b\u001c%\n\u0006=\u001f9\n1\u0010\t3\u001c\u0007\u0014\t\"&\u001d\u0016\u001e9\u0017\t";
        r0 = '5';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:6:0x0017, code lost:
        r10 = r5[r6];
     */
    /* JADX WARN: Code restructure failed: missing block: B:70:0x0276, code lost:
        r3[r2] = r1;
        r2 = '7';
        r1 = "\u0011\u0005<\u001c\u0000\u0001\t\u0000\f\u001d\n89\u0014\u000b";
        r0 = '6';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:71:0x0281, code lost:
        r3[r2] = r1;
        r2 = '8';
        r1 = "\u0001\u0002~\u0013\u001e\u0017\u001f8W\u001d\u0007\u001e&\u001c\u001c\u0001\u0003>\u001f\u0007\u0005";
        r0 = '7';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:72:0x028c, code lost:
        r3[r2] = r1;
        r2 = '9';
        r1 = "\f\u0003$\u0010\b\u0003\u000f$\u0010\u0001\f3>\f\u0003";
        r0 = '8';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:73:0x0297, code lost:
        r3[r2] = r1;
        r2 = ':';
        r1 = "\u0006\t&&\u0007\f\n?&\u001c\u0007\u001c\u000f\r\u0007\u000f\t";
        r0 = '9';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:74:0x02a2, code lost:
        r3[r2] = r1;
        r2 = ';';
        r1 = "\u0011\t\"\u000f\u0007\u0001\t\u000f\u001a\u0001\f\u00025\u001a\u000b\u0016";
        r0 = ':';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:75:0x02ad, code lost:
        r3[r2] = r1;
        r2 = '<';
        r1 = "\u0010\t7\u0010\u001d\u0016\t\"&\n\u0007\u001a9\u001a\u000b=\u0005=\u001c\u0007";
        r0 = ';';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:76:0x02b8, code lost:
        r3[r2] = r1;
        r2 = '=';
        r1 = "\u000f<?\u000b\u001a";
        r0 = '<';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:77:0x02c3, code lost:
        r3[r2] = r1;
        r2 = '>';
        r1 = "\n\u0018$\t1\u0010\t \u0016\u001c\u00163#\u0010\u001d=\u0019\"\u0015";
        r0 = '=';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:78:0x02ce, code lost:
        r3[r2] = r1;
        r2 = '?';
        r1 = "\u0006\t&\u0010\r\u00073=\u0018\u0007\f39\u001d\u001d";
        r0 = '>';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:79:0x02d9, code lost:
        r3[r2] = r1;
        r2 = '@';
        r1 = "\b\u001c%\n\u0006=\u001f9\n1\f\t$\r\u0017\u0012\t";
        r0 = '?';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:7:0x001b, code lost:
        switch((r8 % 5)) {
            case 0: goto L_0x038e;
            case 1: goto L_0x0392;
            case 2: goto L_0x0396;
            case 3: goto L_0x039a;
            default: goto L_0x001e;
        };
     */
    /* JADX WARN: Code restructure failed: missing block: B:80:0x02e4, code lost:
        r3[r2] = r1;
        r2 = 'A';
        r1 = "\u000e\u000f$\u0010\u0003\u0007";
        r0 = '@';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:81:0x02ef, code lost:
        r3[r2] = r1;
        r2 = 'B';
        r1 = "\u000f%\u0000";
        r0 = 'A';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:82:0x02fa, code lost:
        r3[r2] = r1;
        r2 = 'C';
        r1 = "\b\u001c%\n\u0006=\u000f?\u0017\u0000=\u0001 \u0016\u001c\u0016";
        r0 = 'B';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:83:0x0305, code lost:
        r3[r2] = r1;
        r2 = 'D';
        r1 = "\u0012\u0019#\u0011\u001a\u000b\u00015";
        r0 = 'C';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:84:0x0310, code lost:
        r3[r2] = r1;
        r2 = 'E';
        r1 = "(<%\n\u0006=(5\u000f\u0007\u0001\t\u0019\u001d";
        r0 = 'D';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:85:0x031b, code lost:
        r3[r2] = r1;
        r2 = 'F';
        r1 = "\u0001\u0002~\u0013\u001e\u0017\u001f8W\u000f\f\b\"\u0016\u0007\u0006B\u0011)>))\t";
        r0 = 'E';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:86:0x0326, code lost:
        r3[r2] = r1;
        r2 = 'G';
        r1 = "\u0010\t7\u0010\u001d\u0016\t\"&\n\u0007\u001a9\u001a\u000b=\u0005>\u001f\u0001";
        r0 = 'F';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:87:0x0331, code lost:
        r3[r2] = r1;
        r2 = 'H';
        r1 = "\u0010\t7\u0010\u001d\u0016\u001e1\r\u0007\r\u0002\u000f\u0010\n";
        r0 = 'G';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:88:0x033c, code lost:
        r3[r2] = r1;
        r2 = 'I';
        r1 = "\b\u001c%\n\u0006=\u000f?\u0017\u0000=\u00019\t";
        r0 = 'H';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:89:0x0347, code lost:
        r3[r2] = r1;
        r2 = 'J';
        r1 = "\u000e\u00037\u0010\u0000=\u001e5\t\u0001\u0010\u0018\u000f\r\u0007\u000f\t";
        r0 = 'I';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:8:0x001e, code lost:
        r9 = 'n';
     */
    /* JADX WARN: Code restructure failed: missing block: B:90:0x0352, code lost:
        r3[r2] = r1;
        r2 = 'K';
        r1 = "\u0010\t7\u0010\u001d\u0016\t\"&\n\u0007\u001a9\u001a\u000b=\r>\u001d\u001c\r\u00054&\u0007\u0006";
        r0 = 'J';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:91:0x035d, code lost:
        r3[r2] = r1;
        r2 = 'L';
        r1 = "\u0010\t7\u0010\u001d\u0016\t\"&\n\u0007\u001a9\u001a\u000b=\u00011\u001a";
        r0 = 'K';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:92:0x0368, code lost:
        r3[r2] = r1;
        r2 = 'M';
        r1 = "\u0006\t&\u0010\r\u00073 \u0018\u001d\u0011\u001b?\u000b\n";
        r0 = 'L';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:93:0x0373, code lost:
        r3[r2] = r1;
        r2 = 'N';
        r1 = "\u0011\u0018?\t1\u0007\u00145\u001a\u001b\u0016\t4&\u0001\f39\u0014\u0002\r\u000b9\u0017";
        r0 = 'M';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:94:0x037e, code lost:
        r3[r2] = r1;
        r2 = 'O';
        r1 = "\u000b\u001f\u000f\u0010\u0003=\u0000?\u001e\t\u0007\b\u000f\u0010\u0000";
        r0 = 'N';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:95:0x0389, code lost:
        r3[r2] = r1;
        cn.jpush.android.a.z = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:96:0x038d, code lost:
        return;
     */
    /* JADX WARN: Code restructure failed: missing block: B:97:0x038e, code lost:
        r9 = 'b';
     */
    /* JADX WARN: Code restructure failed: missing block: B:98:0x0392, code lost:
        r9 = 'l';
     */
    /* JADX WARN: Code restructure failed: missing block: B:99:0x0396, code lost:
        r9 = 'P';
     */
    /* JADX WARN: Code restructure failed: missing block: B:9:0x0020, code lost:
        r5[r6] = (char) (r9 ^ r10);
        r6 = r8 + 1;
     */
    static {
        /*
            Method dump skipped, instructions count: 1100
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.jpush.android.a.<clinit>():void");
    }

    public static void A() {
        a((Long) 0L);
        p("");
        q("");
        g("");
        n(z[49]);
    }

    public static String B() {
        String str = e.h;
        if (!ao.a(str)) {
            return str;
        }
        String b = e.b(e.e, z[77], "");
        e.h = b;
        return b;
    }

    public static String C() {
        return e.b(e.e, z[0], "");
    }

    public static String D() {
        return d(z[49], (String) null);
    }

    public static String E() {
        return e.b(e.e, z[39], "");
    }

    public static String F() {
        return e.b(e.e, z[3], "");
    }

    public static String G() {
        return d(z[50], (String) null);
    }

    public static boolean H() {
        String d = d(z[42], "");
        String d2 = d(z[47], "");
        String d3 = d(z[46], "");
        if (!d.isEmpty() || !d2.isEmpty() || !d3.isEmpty()) {
            return false;
        }
        ac.c();
        return true;
    }

    public static String I() {
        return d(z[42], "");
    }

    public static String J() {
        return d(z[10], "");
    }

    public static void K() {
        a(z[30], System.currentTimeMillis());
    }

    private static long L() {
        long b = e.b(e.e, z[27], (long) com.umeng.analytics.a.k);
        return b > 0 ? b : com.umeng.analytics.a.k;
    }

    public static cn.jpush.android.service.a a(Context context) {
        return cn.jpush.android.service.a.valueOf(e.b(context, z[9], cn.jpush.android.service.a.b.name()));
    }

    public static void a(int i) {
        a(z[11], i);
    }

    public static void a(long j) {
        a(z[14], j);
        a(z[13], System.currentTimeMillis());
    }

    public static void a(long j, String str, String str2, String str3, String str4) {
        a(Long.valueOf(j));
        p(str);
        q(str2);
        if (!ao.a(str3)) {
            g(str3);
        }
        c(z[49], str4);
    }

    public static void a(Context context, int i) {
        e.a(context, z[45], i);
    }

    public static void a(Context context, int i, String str) {
        if (i >= 0 && i < 3) {
            d(context, z[40] + i, str);
        }
    }

    public static void a(Context context, long j) {
        e.a(context, z[27], j > 0 ? 1000 * j : L());
    }

    public static void a(Context context, cn.jpush.android.service.a aVar) {
        e.a(context, z[9], aVar.name());
    }

    public static void a(Context context, String str) {
        e.a(context, z[8], str);
    }

    public static void a(Context context, String str, String str2) {
        e.a(context, z[48] + str, str2);
    }

    public static void a(Context context, boolean z2) {
        e.a(context, z[78], z2);
    }

    private static void a(Long l) {
        e.g = l.longValue();
        e.a(e.e, z[5], l.longValue());
    }

    public static void a(String str) {
        c(z[44], str);
    }

    public static boolean a() {
        long b = b(z[20], 0L);
        long currentTimeMillis = System.currentTimeMillis();
        if (currentTimeMillis - b <= com.umeng.analytics.a.j) {
            return false;
        }
        a(z[20], currentTimeMillis);
        return true;
    }

    public static boolean a(String str, String str2) {
        String d = d(z[42], "");
        String d2 = d(z[47], "");
        if (!str.equals(d) || !str2.equals(d2)) {
            ac.c();
            return false;
        }
        ac.c();
        return true;
    }

    public static int b(Context context) {
        int b = e.b(context, z[45], 5);
        new StringBuilder(z[52]).append(b);
        ac.a();
        return b;
    }

    public static void b(int i) {
        e.a(e.e, z[51], i);
    }

    public static void b(long j) {
        a(z[7], j);
    }

    public static void b(Context context, int i) {
        e.a(context, z[17], i);
    }

    public static void b(Context context, String str) {
        e.a(context, z[12], str);
    }

    public static void b(Context context, boolean z2) {
        e.a(context, z[34], z2);
    }

    public static boolean b() {
        long b = b(z[6], 0L);
        long currentTimeMillis = System.currentTimeMillis();
        if (currentTimeMillis - b <= L()) {
            return false;
        }
        a(z[6], currentTimeMillis);
        return true;
    }

    public static boolean b(String str) {
        if (str == null) {
            return true;
        }
        if (str.equalsIgnoreCase(d(z[36], (String) null))) {
            return false;
        }
        c(z[36], str);
        return true;
    }

    public static boolean b(String str, String str2) {
        String d = d(z[47], "");
        String d2 = d(z[46], "");
        if (ao.a(str2) || ao.a(d2)) {
            return str.equals(d);
        }
        if (!str.equals(d) || !str2.equals(d2)) {
            ac.c();
            return false;
        }
        ac.c();
        return true;
    }

    public static int c(Context context) {
        return e.b(context, z[17], 0);
    }

    public static void c() {
        a(z[6], System.currentTimeMillis());
    }

    public static void c(int i) {
        a(z[16], i);
    }

    public static void c(Context context, int i) {
        e.a(context, z[15], i);
    }

    public static void c(Context context, String str) {
        b(context, z[53], str);
    }

    public static void c(Context context, boolean z2) {
        e.a(context, z[19], z2);
    }

    public static void c(String str) {
        c(z[31], str);
    }

    public static String d(Context context) {
        return e.b(context, z[8], "");
    }

    public static String d(Context context, int i) {
        return (i < 0 || i >= 3) ? z[41] : e(context, z[40] + i, z[41]);
    }

    public static void d(Context context, String str) {
        b(context, z[4], str);
    }

    private static void d(Context context, String str, String str2) {
        e.a(context, str, o(str2));
    }

    public static void d(Context context, boolean z2) {
        e.a(context, z[37], z2);
    }

    public static void d(String str) {
        e.a(e.e, z[25], str);
    }

    public static boolean d() {
        long b = b(z[28], 0L);
        long currentTimeMillis = System.currentTimeMillis();
        if (currentTimeMillis - b <= com.umeng.analytics.a.k) {
            return false;
        }
        a(z[28], currentTimeMillis);
        return true;
    }

    public static String e(Context context) {
        return e.b(context, z[12], "");
    }

    public static String e(Context context, String str) {
        return e.b(context, z[48] + str, "");
    }

    private static String e(Context context, String str, String str2) {
        String b = e.b(context, str, "");
        return ao.a(b) ? str2 : e(b, str2);
    }

    public static void e() {
        a(z[28], System.currentTimeMillis());
    }

    public static void e(String str) {
        c(z[29], str);
    }

    public static void f(Context context, String str) {
        d(context, z[2], str);
    }

    public static void f(String str) {
        c(z[26], str);
    }

    public static synchronized boolean f() {
        boolean z2;
        synchronized (a.class) {
            long b = b(z[43], 0L);
            long currentTimeMillis = System.currentTimeMillis();
            if (currentTimeMillis - b > com.umeng.analytics.a.j) {
                a(z[43], currentTimeMillis);
                z2 = true;
            } else {
                z2 = false;
            }
        }
        return z2;
    }

    public static boolean f(Context context) {
        return e.b(context, z[34], true);
    }

    public static long g() {
        return b(z[43], 0L);
    }

    public static String g(Context context) {
        return c(context, z[53], "");
    }

    public static void g(Context context, String str) {
        d(context, z[18], str);
    }

    public static void g(String str) {
        e.a(e.e, z[39], str);
    }

    public static String h() {
        return d(z[44], (String) null);
    }

    public static String h(Context context) {
        return c(context, z[4], "");
    }

    public static void h(Context context, String str) {
        d(context, z[22], str);
    }

    public static void h(String str) {
        e.a(e.e, z[3], str);
    }

    public static int i() {
        return b(z[11], 0);
    }

    public static void i(Context context, String str) {
        String b = af.b(context);
        if (ao.a(b)) {
            b = z[21];
        }
        d(context, b, str);
    }

    public static void i(String str) {
        c(z[50], str);
    }

    public static boolean i(Context context) {
        return e.b(context, z[19], true);
    }

    public static String j(Context context) {
        return e(context, z[2], z[1]);
    }

    public static void j(String str) {
        c(z[42], str);
    }

    public static boolean j() {
        return System.currentTimeMillis() - b(z[24], 0L) > 180000;
    }

    public static String k(Context context) {
        return e(context, z[18], "7");
    }

    public static void k() {
        a(z[24], System.currentTimeMillis());
    }

    public static void k(String str) {
        c(z[47], str);
    }

    public static int l() {
        return e.b(e.e, z[51], 0);
    }

    public static String l(Context context) {
        return e(context, z[22], z[23]);
    }

    public static void l(String str) {
        c(z[46], str);
    }

    public static long m() {
        long b = b(z[13], 0L);
        return ((b(z[14], 0L) - b) + System.currentTimeMillis()) / 1000;
    }

    public static String m(Context context) {
        String b = af.b(context);
        if (ao.a(b)) {
            b = z[21];
        }
        return e(context, b, "");
    }

    public static void m(String str) {
        c(z[10], str);
    }

    public static synchronized long n() {
        long j;
        synchronized (a.class) {
            String str = z[33];
            long abs = Math.abs(new Random().nextInt(32767));
            if (abs % 2 == 0) {
                abs++;
            }
            long b = b(str, abs) % 32767;
            a(z[33], b + 2);
            j = b + 2;
        }
        return j;
    }

    public static boolean n(Context context) {
        if (b.c(context).toUpperCase().startsWith(z[38])) {
            return false;
        }
        if (!e.b(context, z[37], false) && !ao.a(m(context))) {
            return false;
        }
        return System.currentTimeMillis() - b(z[30], 0L) > com.umeng.analytics.a.k;
    }

    public static void o(Context context) {
        p(context);
        SharedPreferences sharedPreferences = context.getSharedPreferences(z[56], 0);
        b(context, z[9], sharedPreferences.getInt(z[59], 0) == 1 ? cn.jpush.android.service.a.a.name() : cn.jpush.android.service.a.b.name());
        b(context, z[63], sharedPreferences.getString(z[71], ""));
        b(context, z[42], sharedPreferences.getString(z[60], ""));
        b(context, z[47], sharedPreferences.getString(z[75], ""));
        b(context, z[46], sharedPreferences.getString(z[76], ""));
        b(context, z[36], sharedPreferences.getString(z[64], ""));
        b(context, z[26], sharedPreferences.getString(z[54], ""));
        b(context, z[31], sharedPreferences.getString(z[66], ""));
        b(context, z[25], sharedPreferences.getString(z[62], ""));
        b(context, z[44], sharedPreferences.getString(z[73], ""));
        b(context, z[53], sharedPreferences.getString(z[53], ""));
        b(context, z[4], sharedPreferences.getString(z[4], ""));
        a(context, z[16], sharedPreferences.getInt(z[61], 0));
        a(context, z[11], sharedPreferences.getInt(z[67], 0));
        a(context, z[13], sharedPreferences.getLong(z[65], 0L));
        a(context, z[20], sharedPreferences.getLong(z[58], 0L));
        a(context, z[14], sharedPreferences.getLong(z[14], 0L));
        a(context, z[6], sharedPreferences.getLong(z[74], 0L));
        q(sharedPreferences.getString(z[72], ""));
        h(sharedPreferences.getString(z[70], ""));
        g(sharedPreferences.getString(z[69], ""));
        e.a(context, z[8], sharedPreferences.getString(z[55], ""));
        e.a(context, z[12], sharedPreferences.getString(z[68], ""));
        e.a(context, z[45], sharedPreferences.getInt(z[57], 5));
        e.a(context, z[17], sharedPreferences.getInt(z[17], 0));
    }

    public static boolean o() {
        return e.b(e.e, z[79], false);
    }

    public static int p() {
        return b(z[35], 290);
    }

    private static void p(String str) {
        e.h = str;
        e.a(e.e, z[77], str);
    }

    public static void q() {
        a(z[35], 290);
    }

    private static void q(String str) {
        e.a(e.e, z[0], str);
    }

    public static void r() {
        a(z[35], 86400);
    }

    public static boolean s() {
        return p() == 86400;
    }

    public static String t() {
        return d(z[31], z[32]);
    }

    public static int u() {
        return b(z[16], 7000);
    }

    public static String v() {
        return e.b(e.e, z[25], (String) null);
    }

    public static String w() {
        return d(z[26], (String) null);
    }

    public static long x() {
        return b(z[7], -1L);
    }

    public static long y() {
        long j = e.g;
        if (j != 0) {
            return j;
        }
        long b = e.b(e.e, z[5], 0L);
        e.g = b;
        return b;
    }

    public static boolean z() {
        return y() > 0 && !ao.a(C());
    }
}
