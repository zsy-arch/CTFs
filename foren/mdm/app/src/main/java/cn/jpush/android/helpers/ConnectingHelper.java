package cn.jpush.android.helpers;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.os.Message;
import android.telephony.TelephonyManager;
import cn.jpush.a.a.a.c;
import cn.jpush.a.a.a.g;
import cn.jpush.a.a.a.h;
import cn.jpush.a.a.a.j;
import cn.jpush.android.a;
import cn.jpush.android.e;
import cn.jpush.android.service.PushProtocol;
import cn.jpush.android.service.SisInfo;
import cn.jpush.android.service.k;
import cn.jpush.android.service.o;
import cn.jpush.android.util.ac;
import cn.jpush.android.util.ao;
import cn.jpush.android.util.b;
import cn.jpush.android.util.u;
import com.hyphenate.util.HanziToPinyin;
import java.net.InetAddress;
import java.util.Iterator;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class ConnectingHelper {
    private static final List<String> a;
    private static final String[] z;

    /* JADX WARN: Code restructure failed: missing block: B:100:0x0381, code lost:
        r9 = '`';
     */
    /* JADX WARN: Code restructure failed: missing block: B:101:0x0385, code lost:
        r9 = 'U';
     */
    /* JADX WARN: Code restructure failed: missing block: B:102:0x0389, code lost:
        r5 = '#';
     */
    /* JADX WARN: Code restructure failed: missing block: B:103:0x038c, code lost:
        r5 = 'n';
     */
    /* JADX WARN: Code restructure failed: missing block: B:104:0x038f, code lost:
        r5 = '`';
     */
    /* JADX WARN: Code restructure failed: missing block: B:105:0x0392, code lost:
        r5 = 'U';
     */
    /* JADX WARN: Code restructure failed: missing block: B:106:0x0395, code lost:
        r1 = r1;
        r0 = r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:107:0x0397, code lost:
        if (r1 > r2) goto L_0x035f;
     */
    /* JADX WARN: Code restructure failed: missing block: B:108:0x0399, code lost:
        r6.add(new java.lang.String(r0).intern());
        r2 = cn.jpush.android.helpers.ConnectingHelper.a;
        r1 = "P\u0007\u0013{JS\u001b\u0013=\u000eJ\u0001";
        r0 = 65535;
     */
    /* JADX WARN: Code restructure failed: missing block: B:109:0x03aa, code lost:
        r1 = r1.toCharArray();
        r3 = r1.length;
        r4 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:10:0x0026, code lost:
        if (r5 != 0) goto L_0x002c;
     */
    /* JADX WARN: Code restructure failed: missing block: B:110:0x03b1, code lost:
        if (r3 > 1) goto L_0x03db;
     */
    /* JADX WARN: Code restructure failed: missing block: B:111:0x03b3, code lost:
        r6 = r4;
        r3 = r1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:112:0x03b8, code lost:
        r8 = r3[r4];
     */
    /* JADX WARN: Code restructure failed: missing block: B:113:0x03bc, code lost:
        switch((r6 % 5)) {
            case 0: goto L_0x03cd;
            case 1: goto L_0x03d0;
            case 2: goto L_0x03d3;
            case 3: goto L_0x03d6;
            default: goto L_0x03bf;
        };
     */
    /* JADX WARN: Code restructure failed: missing block: B:114:0x03bf, code lost:
        r7 = ' ';
     */
    /* JADX WARN: Code restructure failed: missing block: B:115:0x03c1, code lost:
        r3[r4] = (char) (r7 ^ r8);
        r4 = r6 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:116:0x03c7, code lost:
        if (r3 != 0) goto L_0x03d9;
     */
    /* JADX WARN: Code restructure failed: missing block: B:117:0x03c9, code lost:
        r3 = r1;
        r6 = r4;
        r4 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:118:0x03cd, code lost:
        r7 = '#';
     */
    /* JADX WARN: Code restructure failed: missing block: B:119:0x03d0, code lost:
        r7 = 'n';
     */
    /* JADX WARN: Code restructure failed: missing block: B:11:0x0028, code lost:
        r5 = r1;
        r8 = r6;
        r6 = r5;
     */
    /* JADX WARN: Code restructure failed: missing block: B:120:0x03d3, code lost:
        r7 = '`';
     */
    /* JADX WARN: Code restructure failed: missing block: B:121:0x03d6, code lost:
        r7 = 'U';
     */
    /* JADX WARN: Code restructure failed: missing block: B:122:0x03d9, code lost:
        r3 = r3;
        r1 = r1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:123:0x03db, code lost:
        if (r3 > r4) goto L_0x03b3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:124:0x03dd, code lost:
        r1 = new java.lang.String(r1).intern();
     */
    /* JADX WARN: Code restructure failed: missing block: B:125:0x03e6, code lost:
        switch(r0) {
            case 0: goto L_0x03f2;
            case 1: goto L_0x03fb;
            default: goto L_0x03e9;
        };
     */
    /* JADX WARN: Code restructure failed: missing block: B:126:0x03e9, code lost:
        r2.add(r1);
        r2 = cn.jpush.android.helpers.ConnectingHelper.a;
        r1 = "F\u000f\u0013,TL\u0003\u0005&SB\t\u0005{CL\u0003";
        r0 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:127:0x03f2, code lost:
        r2.add(r1);
        r2 = cn.jpush.android.helpers.ConnectingHelper.a;
        r1 = "\u0012_S{\u0013\u0012@Qb\u000e\u0012^X";
        r0 = 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:128:0x03fb, code lost:
        r2.add(r1);
     */
    /* JADX WARN: Code restructure failed: missing block: B:129:0x03fe, code lost:
        return;
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
            case 27: goto L_0x0159;
            case 28: goto L_0x0164;
            case 29: goto L_0x016f;
            case 30: goto L_0x017b;
            case 31: goto L_0x0186;
            case 32: goto L_0x0191;
            case 33: goto L_0x019c;
            case 34: goto L_0x01a7;
            case 35: goto L_0x01b2;
            case 36: goto L_0x01bd;
            case 37: goto L_0x01c8;
            case 38: goto L_0x01d3;
            case 39: goto L_0x01de;
            case 40: goto L_0x01e9;
            case 41: goto L_0x01f4;
            case 42: goto L_0x01ff;
            case 43: goto L_0x020a;
            case 44: goto L_0x0215;
            case 45: goto L_0x0220;
            case 46: goto L_0x022b;
            case 47: goto L_0x0236;
            case 48: goto L_0x0241;
            case 49: goto L_0x024c;
            case 50: goto L_0x0257;
            case 51: goto L_0x0262;
            case 52: goto L_0x026d;
            case 53: goto L_0x0278;
            case 54: goto L_0x0283;
            case 55: goto L_0x028e;
            case 56: goto L_0x0299;
            case 57: goto L_0x02a4;
            case 58: goto L_0x02af;
            case 59: goto L_0x02ba;
            case 60: goto L_0x02c5;
            case 61: goto L_0x02d0;
            case 62: goto L_0x02db;
            case 63: goto L_0x02e6;
            case 64: goto L_0x02f1;
            case 65: goto L_0x02fc;
            case 66: goto L_0x0307;
            case 67: goto L_0x0312;
            case 68: goto L_0x031d;
            case 69: goto L_0x0328;
            case 70: goto L_0x0333;
            case 71: goto L_0x033e;
            case 72: goto L_0x0349;
            default: goto L_0x003c;
        };
     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x003c, code lost:
        r3[r2] = r1;
        r2 = 1;
        r1 = "`\u0001\u000e;E@\u001a\t;Gk\u000b\f%EQ";
        r0 = 0;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x0044, code lost:
        r3[r2] = r1;
        r2 = 2;
        r1 = "\u000fN\u00069ADT";
        r0 = 1;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x004c, code lost:
        r3[r2] = r1;
        r2 = 3;
        r1 = "o\u0001\u0007<N\u0003\b\u0001<LF\n@\"IW\u0006@&EQ\u0018\u0005'\u0000F\u001c\u0012:R\u0003C@6OG\u000bZ";
        r0 = 2;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:19:0x0054, code lost:
        r3[r2] = r1;
        r2 = 4;
        r1 = "\u0011@Q{\u0019";
        r0 = 3;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x005c, code lost:
        r3[r2] = r1;
        r2 = 5;
        r1 = "\u000fN\u00131Ku\u000b\u0012&IL\u0000Z";
        r0 = 4;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x0064, code lost:
        r3[r2] = r1;
        r2 = 6;
        r1 = "\u000fN\u00130RU\u000b\u0012\u0001IN\u000b[";
        r0 = 5;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x006c, code lost:
        r3[r2] = r1;
        r2 = 7;
        r1 = "O\u0001\u0007<N\u0003C@?UJ\nZ";
        r0 = 6;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x0074, code lost:
        r3[r2] = r1;
        r2 = '\b';
        r1 = "\u000fN\u0005'RL\u001cZ";
        r0 = 7;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x007d, code lost:
        r3[r2] = r1;
        r2 = '\t';
        r1 = "o\u0001\u0007<N\u0003\b\u0001<LF\n@\"IW\u0006@'EW\u001b\u0012;\u0000@\u0001\u00040\u001a";
        r0 = '\b';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x0087, code lost:
        r3[r2] = r1;
        r2 = '\n';
        r1 = "o\u0001\u0007<N\u0003\u0019\t!H\u0003C@?UJ\nZ";
        r0 = '\t';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x0092, code lost:
        r3[r2] = r1;
        r2 = 11;
        r1 = "\u0003\u0002\u000f2IM<\u0005&PL\u0000\u00130\u0000J\u001d@;UO\u0002";
        r0 = '\n';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x009d, code lost:
        r3[r2] = r1;
        r2 = '\f';
        r1 = "o\u0001\u0007<N\u0003\b\u0001<LF\n@\"IW\u0006@\u0019O@\u000f\fuEQ\u001c\u000f'\u0000\u000eN\u0003:DFT";
        r0 = 11;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x00a8, code lost:
        r3[r2] = r1;
        r2 = '\r';
        r1 = "o\u0001\u0007<N\u0003\u001d\u00156CF\u000b\u0004u\r\u0003\u001d\t1\u001a";
        r0 = '\f';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:29:0x00b3, code lost:
        r3[r2] = r1;
        r2 = 14;
        r1 = "l\u001e\u0005;\u0000@\u0001\u000e;E@\u001a\t:N\u0003\b\u0001<LF\n@x\u0000Q\u000b\u0014o";
        r0 = '\r';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x00be, code lost:
        r3[r2] = r1;
        r2 = 15;
        r1 = "@\u0000N?PV\u001d\b{AM\n\u0012:IG@\t;TF\u0000\u0014{rf))\u0006tq/4\u001com";
        r0 = 14;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:31:0x00c9, code lost:
        r3[r2] = r1;
        r2 = 16;
        r1 = "v\u0000\u0005-PF\r\u00140D\u0019N\u00120GJ\u001d\u0014'AW\u0007\u000f;iGA\n IGN\u0013=OV\u0002\u0004uNL\u001a@7E\u0003\u000b\r%TZ@@";
        r0 = 15;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x00d4, code lost:
        r3[r2] = r1;
        r2 = 17;
        r1 = "\u000fN\u0001%Ku\u000b\u0012&IL\u0000Z";
        r0 = 16;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x00df, code lost:
        r3[r2] = r1;
        r2 = 18;
        r1 = "\u000fN\u0005-Th\u000b\u0019o";
        r0 = 17;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:34:0x00ea, code lost:
        r3[r2] = r1;
        r2 = 19;
        r1 = "q\u000b\u0007<SW\u000b\u0012uFB\u0007\f0D\u0003C@'EWT";
        r0 = 18;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:35:0x00f5, code lost:
        r3[r2] = r1;
        r2 = com.tencent.open.GameAppOperation.PIC_SYMBOLE;
        r1 = "o\u0001\u00034L\u0003\u000b\u0012'OQN\u00040S@\u001c\t%TJ\u0001\u000eo\u0000";
        r0 = 19;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:36:0x0100, code lost:
        r3[r2] = r1;
        r2 = 21;
        r1 = "v\u0000\b4NG\u0002\u00051\u0000P\u000b\u0012#EQN\u00120SS\u0001\u000e&E\u0003\u000b\u0012'OQN\u0003:DFNMu";
        r0 = com.tencent.open.GameAppOperation.PIC_SYMBOLE;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:37:0x010b, code lost:
        r3[r2] = r1;
        r2 = 22;
        r1 = "\u0007J";
        r0 = 21;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:38:0x0116, code lost:
        r3[r2] = r1;
        r2 = 23;
        r1 = "q\u000b\u0007<SW\u000b\u0012uSV\r\u00030EGNMuJV\u0007\u0004o";
        r0 = 22;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:39:0x0121, code lost:
        r3[r2] = r1;
        r2 = 24;
        r1 = "q\u000b\u0007<SW\u000b\u0012uWJ\u001a\bo\u0000H\u000b\u0019o";
        r0 = 23;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:40:0x012c, code lost:
        r3[r2] = r1;
        r2 = 25;
        r1 = "\u000fN\u00039IF\u0000\u0014\u001cNE\u0001Z";
        r0 = 24;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:41:0x0137, code lost:
        r3[r2] = r1;
        r2 = 26;
        r1 = "q\u000b\u0007<SW\u000b\u0012ufB\u0007\f0D\u0003\u0019\t!H\u0003\u001d\u0005'VF\u001c@0RQ\u0001\u0012u\r\u0003\r\u000f1E\u0019";
        r0 = 25;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:42:0x0142, code lost:
        r3[r2] = r1;
        r2 = 27;
        r1 = "@\u0000N?PV\u001d\b{AM\n\u0012:IG@2\u0010gj=4\u0007aw'/\u001b\u007fj*";
        r0 = 26;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:43:0x014d, code lost:
        r3[r2] = r1;
        r2 = 28;
        r1 = "丮南鄭";
        r0 = 27;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:44:0x0159, code lost:
        r3[r2] = r1;
        r2 = 29;
        r1 = "\u000fN\r0SP\u000f\u00070\u001a";
        r0 = 28;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:45:0x0164, code lost:
        r3[r2] = r1;
        r2 = 30;
        r1 = "V\u0000\u000b;OT\u0000";
        r0 = 29;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:46:0x016f, code lost:
        r3[r2] = r1;
        r2 = 31;
        r1 = "匦呣Zu";
        r0 = 30;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:47:0x017b, code lost:
        r3[r2] = r1;
        r2 = ' ';
        r1 = "\u000fN\u00120GJ\u001d\u0014'AW\u0007\u000f;iGT";
        r0 = 31;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:48:0x0186, code lost:
        r3[r2] = r1;
        r2 = '!';
        r1 = "\u0003习@\u0014PS%\u0005,\u001a";
        r0 = ' ';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:49:0x0191, code lost:
        r3[r2] = r1;
        r2 = '\"';
        r1 = "\u000fN\u00040VJ\r\u0005\u001cD\u0019";
        r0 = '!';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:4:0x0010, code lost:
        if (r5 <= 1) goto L_0x0012;
     */
    /* JADX WARN: Code restructure failed: missing block: B:50:0x019c, code lost:
        r3[r2] = r1;
        r2 = '#';
        r1 = "S\u000f\u0013&WL\u001c\u0004o";
        r0 = '\"';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:51:0x01a7, code lost:
        r3[r2] = r1;
        r2 = '$';
        r1 = "w\u0001@2EWN\u0013<S\u0003C@=OP\u001aZ";
        r0 = '#';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:52:0x01b2, code lost:
        r3[r2] = r1;
        r2 = '%';
        r1 = "p'3urF\r\u0005<VF\n@\u0006TQ\u0007\u000e2\u001a\u0003";
        r0 = '$';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:53:0x01bd, code lost:
        r3[r2] = r1;
        r2 = '&';
        r1 = "p'3urF\r\u0005<VJ\u0000\u0007{\u000e\r";
        r0 = '%';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:54:0x01c8, code lost:
        r3[r2] = r1;
        r2 = '\'';
        r1 = "e\u000f\t9EGN\u0014:\u0000Q\u000b\u0013:LU\u000b@=OP\u001a@1NPNMu";
        r0 = '&';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:55:0x01d3, code lost:
        r3[r2] = r1;
        r2 = '(';
        r1 = "\u000fN\u0010:RWT";
        r0 = '\'';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:56:0x01de, code lost:
        r3[r2] = r1;
        r2 = ')';
        r1 = "d\u000b\u0014uSJ\u001d@<NE\u0001@0RQ\u0001\u0012u\r\u0003\u001d\t&hL\u001d\u0014o";
        r0 = '(';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:57:0x01e9, code lost:
        r3[r2] = r1;
        r2 = '*';
        r1 = "\u000fN\u00130LF\r\u0014<OMT";
        r0 = ')';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:58:0x01f4, code lost:
        r3[r2] = r1;
        r2 = '+';
        r1 = "d\u000b\u0014uSJ\u001d@<NE\u0001@&U@\r\u00050D\u0003\u0019\t!H\u0003\u0006\u000f&T\u0019N";
        r0 = '*';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:59:0x01ff, code lost:
        r3[r2] = r1;
        r2 = ',';
        r1 = "f\u0016\u00030PW\u0007\u000f;\u0000T\u0006\u0005;\u0000@\u0002\u000f&E\u0003\u001b\u0004%\u0000P\u0001\u0003>EWNMu";
        r0 = '+';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:5:0x0012, code lost:
        r8 = r6;
        r5 = r1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:60:0x020a, code lost:
        r3[r2] = r1;
        r2 = '-';
        r1 = "`\u000f\u000euNL\u001a@2EWN\u0013<S\u0003\u0007\u000e3O\u0003\b\u0012:M\u0003\u0006\u000f&T\u0019N";
        r0 = ',';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:61:0x0215, code lost:
        r3[r2] = r1;
        r2 = '.';
        r1 = "@\u0001\u000e;E@\u001a\t:N";
        r0 = '-';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:62:0x0220, code lost:
        r3[r2] = r1;
        r2 = '/';
        r1 = "l\u001e\u0005;\u0000@\u0001\u000e;E@\u001a\t:N\u0003\u0019\t!H\u0003\u0002\u0001&T\u0003\t\u000f:D\u0003C@<P\u0019";
        r0 = '.';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:63:0x022b, code lost:
        r3[r2] = r1;
        r2 = '0';
        r1 = "e\u000f\t9EGN\u0017<TKN\u00019L\u0003\r\u000f;NP@";
        r0 = '/';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:64:0x0236, code lost:
        r3[r2] = r1;
        r2 = '1';
        r1 = "\u000fN\t;DF\u0016Z";
        r0 = '0';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:65:0x0241, code lost:
        r3[r2] = r1;
        r2 = '2';
        r1 = "j\u0000\u00164LJ\n@8AJ\u0000@6OM\u0000";
        r0 = '1';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:66:0x024c, code lost:
        r3[r2] = r1;
        r2 = '3';
        r1 = "p\u001b\u00036EF\n@!O\u0003\u0001\u00100N\u0003\r\u000f;NF\r\u0014<OMNMuIST";
        r0 = '2';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:67:0x0257, code lost:
        r3[r2] = r1;
        r2 = '4';
        r1 = "l\u001e\u0005;\u0000@\u0001\u000e;E@\u001a\t:N\u0003\u0019\t!H\u0003\u0001\u0010!IL\u0000\u0013u\r\u0003\u0007\u0010o";
        r0 = '3';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:68:0x0262, code lost:
        r3[r2] = r1;
        r2 = '5';
        r1 = "l\u001e\u0005;\u0000@\u0001\u000e;E@\u001a\t:N\u0003\u0019\t!H\u0003\u0003\u0001<N\u0003C@<P\u0019";
        r0 = '4';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:69:0x026d, code lost:
        r3[r2] = r1;
        r2 = '6';
        r1 = "j\u0000\u00164LJ\n@&IPN\u00120SS\u0001\u000e&E\u000fN\u00064IO\u000b\u0004uTLN\u00104RP\u000b@\u001fsl N";
        r0 = '5';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:6:0x0017, code lost:
        r10 = r5[r6];
     */
    /* JADX WARN: Code restructure failed: missing block: B:70:0x0278, code lost:
        r3[r2] = r1;
        r2 = '7';
        r1 = "S\u000f\u0012&Ep\u0007\u0013\u001cNE\u0001@6RB\u001d\bo";
        r0 = '6';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:71:0x0283, code lost:
        r3[r2] = r1;
        r2 = '8';
        r1 = "\u000fN\u0010:RWTSe\u0010\u0013";
        r0 = '7';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:72:0x028e, code lost:
        r3[r2] = r1;
        r2 = '9';
        r1 = "j\u0000\u00164LJ\n@1EE\u000f\u00159T\u0003\r\u000f;N\r";
        r0 = '8';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:73:0x0299, code lost:
        r3[r2] = r1;
        r2 = ':';
        r1 = "J\u0003Va\u000eI\u001e\u0015&H\r\r\u000e";
        r0 = '9';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:74:0x02a4, code lost:
        r3[r2] = r1;
        r2 = ';';
        r1 = "l\u001e\u0005;\u0000@\u0001\u000e;E@\u001a\t:N\u0003\u0019\t!H\u0003\n\u00053AV\u0002\u0014u\r\u0003\u0007\u0010o";
        r0 = ':';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:75:0x02af, code lost:
        r3[r2] = r1;
        r2 = '<';
        r1 = "l\u001e\u0005;\u0000@\u0001\u000e;E@\u001a\t:N\u0003\u0019\t!H\u0003\u0006\u0001'D@\u0001\u00040D\u0003\u0006\u000f&T\u0003C@<P\u0019";
        r0 = ';';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:76:0x02ba, code lost:
        r3[r2] = r1;
        r2 = '=';
        r1 = "P\u001a\u0001!E\u0003\u0007\u0013uNL\u001a@6HB\u0000\u00070D\u0003C@";
        r0 = '<';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:77:0x02c5, code lost:
        r3[r2] = r1;
        r2 = '>';
        r1 = "@\u0001\u000e;E@\u001a\u00051";
        r0 = '=';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:78:0x02d0, code lost:
        r3[r2] = r1;
        r2 = '?';
        r1 = "b\r\u0014<OMNMuSF\u0000\u0004\u0016OM\u0000\u00056TJ\u0001\u000e\u0016HB\u0000\u00070D";
        r0 = '>';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:79:0x02db, code lost:
        r3[r2] = r1;
        r2 = '@';
        r1 = "@\u0000N?PV\u001d\b{AM\n\u0012:IG@\t;TF\u0000\u0014{cl .\u0010cw'/\u001b";
        r0 = '?';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:7:0x001b, code lost:
        switch((r8 % 5)) {
            case 0: goto L_0x0379;
            case 1: goto L_0x037d;
            case 2: goto L_0x0381;
            case 3: goto L_0x0385;
            default: goto L_0x001e;
        };
     */
    /* JADX WARN: Code restructure failed: missing block: B:80:0x02e6, code lost:
        r3[r2] = r1;
        r2 = 'A';
        r1 = "@\u0000N?PV\u001d\b{AM\n\u0012:IG@#\u001anm+#\u0001il ?\u0016hb '\u0010";
        r0 = '@';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:81:0x02f1, code lost:
        r3[r2] = r1;
        r2 = 'B';
        r1 = "v(";
        r0 = 'A';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:82:0x02fc, code lost:
        r3[r2] = r1;
        r2 = 'C';
        r1 = "S\u0006\u000f;E";
        r0 = 'B';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:83:0x0307, code lost:
        r3[r2] = r1;
        r2 = 'D';
        r1 = "I\u001d\u000f;e[\r\u0005%TJ\u0001\u000eu\r\u0003";
        r0 = 'C';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:84:0x0312, code lost:
        r3[r2] = r1;
        r2 = 'E';
        r1 = "S\u001b\u0013=\u007fO\u0001\u0007<N|\u0002\u000f6AO1\u0014<MF";
        r0 = 'D';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:85:0x031d, code lost:
        r3[r2] = r1;
        r2 = 'F';
        r1 = "@\u0000N?PV\u001d\b{IN@\u0001;DQ\u0001\t1\u000eB\r\u0014<OM@)\u0018\u007fq+3\u0005om=%";
        r0 = 'E';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:86:0x0328, code lost:
        r3[r2] = r1;
        r2 = 'G';
        r1 = "S\u001b\u0013=\u007fW\u0001?<M|\n\u0001!A";
        r0 = 'F';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:87:0x0333, code lost:
        r3[r2] = r1;
        r2 = 'H';
        r1 = "S\u001b\u0013=\u007fO\u0001\u0007<N|\u001d\u0005'VF\u001c?!IN\u000b";
        r0 = 'G';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:88:0x033e, code lost:
        r3[r2] = r1;
        r2 = 'I';
        r1 = "b\r\u0014<OMNMuSF\u0000\u0004\u0006EQ\u0018\u0005'tJ\u0003\u0005'";
        r0 = 'H';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:89:0x0349, code lost:
        r3[r2] = r1;
        cn.jpush.android.helpers.ConnectingHelper.z = r3;
        r6 = new java.util.ArrayList();
        cn.jpush.android.helpers.ConnectingHelper.a = r6;
        r0 = "P@\n%UP\u0006N6N".toCharArray();
        r1 = r0.length;
        r2 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:8:0x001e, code lost:
        r9 = ' ';
     */
    /* JADX WARN: Code restructure failed: missing block: B:90:0x035d, code lost:
        if (r1 > 1) goto L_0x0397;
     */
    /* JADX WARN: Code restructure failed: missing block: B:91:0x035f, code lost:
        r4 = r2;
        r1 = r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:92:0x0364, code lost:
        r7 = r1[r2];
     */
    /* JADX WARN: Code restructure failed: missing block: B:93:0x0368, code lost:
        switch((r4 % 5)) {
            case 0: goto L_0x0389;
            case 1: goto L_0x038c;
            case 2: goto L_0x038f;
            case 3: goto L_0x0392;
            default: goto L_0x036b;
        };
     */
    /* JADX WARN: Code restructure failed: missing block: B:94:0x036b, code lost:
        r5 = ' ';
     */
    /* JADX WARN: Code restructure failed: missing block: B:95:0x036d, code lost:
        r1[r2] = (char) (r5 ^ r7);
        r2 = r4 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:96:0x0373, code lost:
        if (r1 != 0) goto L_0x0395;
     */
    /* JADX WARN: Code restructure failed: missing block: B:97:0x0375, code lost:
        r1 = r0;
        r4 = r2;
        r2 = r1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:98:0x0379, code lost:
        r9 = '#';
     */
    /* JADX WARN: Code restructure failed: missing block: B:99:0x037d, code lost:
        r9 = 'n';
     */
    /* JADX WARN: Code restructure failed: missing block: B:9:0x0020, code lost:
        r5[r6] = (char) (r9 ^ r10);
        r6 = r8 + 1;
     */
    static {
        /*
            Method dump skipped, instructions count: 1218
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.jpush.android.helpers.ConnectingHelper.<clinit>():void");
    }

    private static int a(k kVar, long j) {
        int i;
        InetAddress a2;
        try {
            a2 = a(z[58]);
        } catch (Exception e) {
            i = -1;
            ac.b();
        }
        if (a2 == null) {
            throw new Exception();
        }
        String hostAddress = a2.getHostAddress();
        new StringBuilder(z[60]).append(hostAddress).append(z[56]);
        ac.b();
        i = !ao.a(hostAddress) ? a(kVar, j, hostAddress, 3000) : -1;
        if (i != 0) {
            String t = a.t();
            int u2 = a.u();
            ac.b(z[1], z[59] + t + z[40] + u2);
            if (ao.a(t) || u2 == 0) {
                ac.b(z[1], z[57]);
                return -1;
            }
            i = a(kVar, j, t, u2);
        }
        return i;
    }

    private static int a(k kVar, long j, String str, int i) {
        if (kVar.b()) {
            ac.d();
            return -991;
        }
        int InitPush = PushProtocol.InitPush(j, str, i);
        if (InitPush == 0) {
            return InitPush;
        }
        if (kVar.b()) {
            new StringBuilder(z[14]).append(InitPush);
            ac.a();
            return InitPush;
        }
        ac.b(z[1], z[14] + InitPush);
        return InitPush;
    }

    /* JADX WARN: Removed duplicated region for block: B:28:0x00b8 A[LOOP:0: B:3:0x0003->B:28:0x00b8, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:69:0x01ca A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:84:0x01e3 A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static cn.jpush.android.service.SisInfo a(android.content.Context r10, boolean r11, int r12, int r13) {
        /*
            Method dump skipped, instructions count: 507
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.jpush.android.helpers.ConnectingHelper.a(android.content.Context, boolean, int, int):cn.jpush.android.service.SisInfo");
    }

    private static InetAddress a(String str) {
        a aVar = new a(str);
        try {
            aVar.start();
            aVar.join(3000L);
            return aVar.a();
        } catch (InterruptedException e) {
            ac.e();
            return null;
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    private static byte[] a(Context context, String str, long j) {
        int i;
        String c = b.c(context);
        String str2 = "";
        try {
            str2 = ((TelephonyManager) context.getSystemService(z[67])).getNetworkOperator();
        } catch (Exception e) {
            e.printStackTrace();
        }
        String str3 = z[66] + c;
        try {
            i = Integer.valueOf(str2).intValue();
        } catch (Exception e2) {
            i = 0;
        }
        byte[] bArr = new byte[128];
        System.arraycopy(new byte[]{0, Byte.MIN_VALUE}, 0, bArr, 0, 2);
        cn.jpush.a.a.b.a.a(bArr, str3, 2);
        cn.jpush.a.a.b.a.a(bArr, i, 34);
        cn.jpush.a.a.b.a.a(bArr, Integer.parseInt(new StringBuilder().append((int) (2147483647L & j)).toString()), 38);
        if (str.length() > 50) {
            str = str.substring(0, 49);
        }
        cn.jpush.a.a.b.a.a(bArr, str, 42);
        cn.jpush.a.a.b.a.a(bArr, z[4], 92);
        cn.jpush.a.a.b.a.a(bArr, 0, 102);
        return bArr;
    }

    public static short getIMLoginFlag() {
        return (short) 1;
    }

    public static boolean isServiceStopedByRegister() {
        return a.s();
    }

    public static int login(Context context, long j) {
        int i;
        byte[] bArr = new byte[128];
        long y = a.y();
        String b = ao.b(a.B());
        String F = a.F();
        int c = b.c(z[4]);
        ac.c(z[1], z[10] + y + z[0] + F + z[5] + c);
        long currentTimeMillis = System.currentTimeMillis();
        short iMLoginFlag = getIMLoginFlag();
        new StringBuilder(z[7]).append(y).append(z[2]).append((int) iMLoginFlag);
        ac.b();
        int LogPush = PushProtocol.LogPush(j, a.n(), bArr, y, b, F, c, iMLoginFlag);
        long currentTimeMillis2 = System.currentTimeMillis();
        int i2 = 0;
        if (LogPush == 0 || LogPush == 9999) {
            h hVar = (h) c.a(bArr);
            if (hVar == null) {
                ac.d(z[1], z[9] + LogPush + z[11]);
                u.a(context, LogPush, currentTimeMillis2 - currentTimeMillis, 1);
                return -1;
            }
            hVar.toString();
            ac.b();
            i2 = hVar.g;
            if (i2 == 0) {
                int a2 = hVar.a();
                long c2 = hVar.c() * 1000;
                a.b(a2);
                a.a(c2);
                ac.c(z[1], z[13] + a2 + z[6] + c2);
                sendServerTimer(context, c2);
                i = 0;
            } else if (i2 == 10000) {
                ac.d(z[1], z[12] + i2 + z[8] + hVar.h);
                i = 1;
            } else {
                ac.d(z[1], z[3] + i2 + z[8] + hVar.h);
                i = 0;
            }
        } else {
            i = 1;
            ac.d(z[1], z[9] + LogPush);
        }
        u.a(context, LogPush, currentTimeMillis2 - currentTimeMillis, i);
        if (i2 == 10000) {
            return -1;
        }
        return LogPush;
    }

    public static synchronized boolean openConnection(k kVar, Context context, long j, SisInfo sisInfo) {
        int i;
        boolean z2;
        synchronized (ConnectingHelper.class) {
            if (sisInfo == null) {
                String h = a.h();
                int i2 = a.i();
                if (h != null) {
                    ac.b(z[1], z[47] + h + z[40] + i2);
                    if (a(kVar, j, h, i2) == 0) {
                        z2 = true;
                    }
                }
                z2 = a(kVar, j) == 0;
            } else {
                String mainConnIp = sisInfo.getMainConnIp();
                int mainConnPort = sisInfo.getMainConnPort();
                ac.b(z[1], z[53] + mainConnIp + z[40] + mainConnPort);
                if (mainConnIp == null || mainConnPort == 0) {
                    i = -1;
                    ac.d(z[1], z[50]);
                } else {
                    i = a(kVar, j, mainConnIp, mainConnPort);
                }
                if (i != 0) {
                    int i3 = 0;
                    Iterator<String> it = sisInfo.getOptionConnIp().iterator();
                    while (true) {
                        if (!it.hasNext()) {
                            break;
                        }
                        String next = it.next();
                        int intValue = sisInfo.getOptionConnPort().get(i3).intValue();
                        ac.b(z[1], z[52] + next + z[40] + intValue + z[49] + i3);
                        int a2 = a(kVar, j, next, intValue);
                        if (a2 == 0) {
                            mainConnPort = intValue;
                            mainConnIp = next;
                            i = a2;
                            break;
                        }
                        i3++;
                        mainConnPort = intValue;
                        mainConnIp = next;
                        i = a2;
                    }
                }
                if (i != 0) {
                    i = a(kVar, j);
                }
                if (i == 0) {
                    a.a(mainConnIp);
                    a.a(mainConnPort);
                    ac.c(z[1], z[51] + mainConnIp + z[40] + mainConnPort);
                    z2 = true;
                } else {
                    ac.d(z[1], z[48]);
                    z2 = false;
                }
            }
        }
        return z2;
    }

    public static SisInfo parseSisInfo(String str) {
        SisInfo sisInfo = null;
        try {
            SisInfo sisInfo2 = new SisInfo();
            sisInfo2.parseSisInfo(str);
            if (sisInfo2 == null) {
                ac.d(z[1], z[54]);
            } else {
                sisInfo2.parseAndSet(str);
                if (!sisInfo2.isInvalidSis()) {
                    sisInfo = sisInfo2;
                }
            }
        } catch (Exception e) {
            ac.a(z[1], z[55], e);
        }
        return sisInfo;
    }

    public static boolean register(Context context, long j, boolean z2) {
        byte[] bArr = new byte[128];
        String a2 = l.a(context);
        String b = l.b(context);
        String a3 = b.a(context, z[4]);
        String j2 = b.j(context);
        String i = b.i(context);
        String g = b.g(context, HanziToPinyin.Token.SEPARATOR);
        String j3 = b.j(context, HanziToPinyin.Token.SEPARATOR);
        String str = Build.SERIAL;
        if (ao.a(j2)) {
            j2 = HanziToPinyin.Token.SEPARATOR;
        }
        if (ao.a(i)) {
            i = HanziToPinyin.Token.SEPARATOR;
        }
        if (ao.a(g)) {
            g = HanziToPinyin.Token.SEPARATOR;
        }
        if (ao.a(j3)) {
            j3 = HanziToPinyin.Token.SEPARATOR;
        }
        if (ao.a(str) || z[30].equalsIgnoreCase(str)) {
            str = HanziToPinyin.Token.SEPARATOR;
        }
        a.j(j3);
        a.k(i);
        a.l(g);
        String str2 = b.a + z[22] + j2 + z[22] + j3 + z[22] + i + z[22] + g + z[22] + str;
        new StringBuilder(z[24]).append(a2).append(z[17]).append(b).append(z[25]).append(a3).append(z[18]).append(str2);
        ac.b();
        if (PushProtocol.RegPush(j, a.n(), a2, b, a3, str2) == -991) {
            return false;
        }
        int RecvPush = PushProtocol.RecvPush(j, bArr, 30);
        if (RecvPush > 0) {
            g a4 = c.a(bArr);
            if (a4 == null) {
                ac.e();
                return false;
            }
            a4.toString();
            ac.b();
            if (a4.d() != 0) {
                ac.e();
                return false;
            }
            j jVar = (j) a4;
            int i2 = jVar.g;
            a.c(context, i2);
            if (i2 == 0) {
                long a5 = jVar.a();
                String c = jVar.c();
                String f = jVar.f();
                String g2 = jVar.g();
                ac.c(z[1], z[23] + a5 + z[32] + f + z[34] + g2);
                new StringBuilder(z[35]).append(c);
                ac.a();
                if (ao.a(f) || 0 == a5) {
                    ac.e(z[1], z[16]);
                }
                b.l(context, g2);
                a.a(a5, c, f, g2, e.f);
                e.g = a5;
                e.h = c;
                if (!z2) {
                    b.a(context, z[15], z[27], f);
                }
                return true;
            }
            ac.e(z[1], z[26] + i2 + z[29] + jVar.h);
            String a6 = o.a(i2);
            if (a6 != null) {
                ac.d(z[1], z[20] + a6);
            }
            if (1006 == i2) {
                ac.b();
                a.r();
            } else if (1007 == i2) {
                ac.c();
            } else if (1005 == i2) {
                String str3 = z[31] + e.c + z[33] + e.f + z[28];
                b.b(context, str3, str3, -1);
                ac.b();
                a.r();
            } else if (1009 == i2) {
                a.r();
            } else {
                new StringBuilder(z[21]).append(i2);
                ac.c();
            }
        } else {
            ac.e(z[1], z[19] + RecvPush);
        }
        return false;
    }

    public static void restoreRtcWhenRegisterSucceed() {
        ac.b();
        a.q();
    }

    public static void sendConnectionChanged(Context context, cn.jpush.android.service.a aVar) {
        boolean z2 = true;
        ac.b(z[1], z[63]);
        if (aVar == a.a(context)) {
            new StringBuilder(z[61]).append(aVar);
            ac.a();
            return;
        }
        a.a(context, aVar);
        Bundle bundle = new Bundle();
        String str = z[65];
        if (!aVar.name().equals(z[62])) {
            z2 = false;
        }
        bundle.putBoolean(str, z2);
        b.a(context, z[64], bundle);
    }

    public static void sendConnectionToHandler(Message message, long j) {
        Bundle bundle = new Bundle();
        bundle.putLong(z[46], j);
        message.setData(bundle);
        message.sendToTarget();
    }

    public static void sendServerTimer(Context context, long j) {
        ac.b(z[1], z[73]);
        try {
            Bundle bundle = new Bundle();
            JSONObject jSONObject = new JSONObject();
            jSONObject.put(z[72], j);
            jSONObject.put(z[69], System.currentTimeMillis());
            bundle.putString(z[71], jSONObject.toString());
            b.a(context, z[70], bundle);
        } catch (JSONException e) {
            new StringBuilder(z[68]).append(e.getMessage());
            ac.d();
        }
    }

    public static synchronized SisInfo sendSis(Context context) {
        SisInfo a2;
        synchronized (ConnectingHelper.class) {
            a2 = a(context, false, 0, 19000);
        }
        return a2;
    }
}
