package cn.jpush.android.helpers;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import cn.jpush.a.a.a.g;
import cn.jpush.a.a.a.i;
import cn.jpush.a.a.a.l;
import cn.jpush.android.a;
import cn.jpush.android.api.e;
import cn.jpush.android.data.d;
import cn.jpush.android.service.PushProtocol;
import cn.jpush.android.service.ServiceInterface;
import cn.jpush.android.util.ac;
import cn.jpush.android.util.af;
import cn.jpush.android.util.ak;
import cn.jpush.android.util.ao;
import cn.jpush.android.util.ap;
import cn.jpush.android.util.b;
import java.io.IOException;
import java.io.LineNumberReader;
import java.io.StringReader;
import java.util.Queue;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public final class j {
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
            case 5: goto L_0x006d;
            case 6: goto L_0x0075;
            case 7: goto L_0x007e;
            case 8: goto L_0x0088;
            case 9: goto L_0x0093;
            case 10: goto L_0x009f;
            case 11: goto L_0x00aa;
            case 12: goto L_0x00b5;
            case 13: goto L_0x00c0;
            case 14: goto L_0x00cc;
            case 15: goto L_0x00d7;
            case 16: goto L_0x00e2;
            case 17: goto L_0x00ed;
            case 18: goto L_0x00f8;
            case 19: goto L_0x0104;
            case 20: goto L_0x010f;
            case 21: goto L_0x011a;
            case 22: goto L_0x0126;
            case 23: goto L_0x0132;
            case 24: goto L_0x013e;
            case 25: goto L_0x0149;
            case 26: goto L_0x0154;
            case 27: goto L_0x015f;
            case 28: goto L_0x016a;
            case 29: goto L_0x0175;
            case 30: goto L_0x0180;
            case 31: goto L_0x018c;
            case 32: goto L_0x0198;
            case 33: goto L_0x01a3;
            case 34: goto L_0x01ae;
            case 35: goto L_0x01b9;
            case 36: goto L_0x01c5;
            case 37: goto L_0x01d0;
            case 38: goto L_0x01dc;
            case 39: goto L_0x01e8;
            case 40: goto L_0x01f4;
            case 41: goto L_0x01ff;
            case 42: goto L_0x020a;
            case 43: goto L_0x0215;
            case 44: goto L_0x0220;
            case 45: goto L_0x022b;
            case 46: goto L_0x0236;
            case 47: goto L_0x0241;
            case 48: goto L_0x024c;
            case 49: goto L_0x0257;
            case 50: goto L_0x0262;
            case 51: goto L_0x026d;
            case 52: goto L_0x0278;
            case 53: goto L_0x0283;
            case 54: goto L_0x028e;
            case 55: goto L_0x0299;
            case 56: goto L_0x02a5;
            case 57: goto L_0x02b0;
            case 58: goto L_0x02bc;
            case 59: goto L_0x02c8;
            case 60: goto L_0x02d4;
            case 61: goto L_0x02e0;
            case 62: goto L_0x02eb;
            case 63: goto L_0x02f6;
            case 64: goto L_0x0302;
            case 65: goto L_0x030d;
            case 66: goto L_0x0318;
            case 67: goto L_0x0323;
            case 68: goto L_0x032e;
            default: goto L_0x003c;
        };
     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x003c, code lost:
        r3[r2] = r1;
        r2 = 1;
        r1 = "h4Kr\u0019u:";
        r0 = 0;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x0044, code lost:
        r3[r2] = r1;
        r2 = 2;
        r1 = "m1ID\b}<XY\u001c9\u007fD]\u000b8(^S\u0016\u007f\u007f[U\fp\u007ffo7V\u001aT_\u001dh+ES\u0016";
        r0 = 1;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x004c, code lost:
        r3[r2] = r1;
        r2 = 3;
        r1 = "l&\\Y";
        r0 = 2;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:19:0x0054, code lost:
        r3[r2] = r1;
        r2 = 4;
        r1 = "k*O_\u001d};\fH\u00178,X]\nl\u007f[U\fp\u007fER\f}1X\u0006";
        r0 = 3;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x005c, code lost:
        r3[r2] = r1;
        r2 = 5;
        r1 = "k+MN\fL&\\YB";
        r0 = 4;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x0064, code lost:
        r3[r2] = r1;
        r2 = 6;
        r1 = "~>EP\u001d|\u007fXSXk+MN\f8(EH\u001086BH\u001dv+\u0016";
        r0 = 5;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x006d, code lost:
        r3[r2] = r1;
        r2 = 7;
        r1 = "k:^J\u0011{:b]\u0015}";
        r0 = 6;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x0075, code lost:
        r3[r2] = r1;
        r2 = '\b';
        r1 = "r,CR;w1XY\u0016le";
        r0 = 7;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x007e, code lost:
        r3[r2] = r1;
        r2 = '\t';
        r1 = "M1ID\b}<XY\u001c\"\u007fYR\u0013v0[RXl&\\YB";
        r0 = '\b';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x0088, code lost:
        r3[r2] = r1;
        r2 = '\n';
        r1 = "4\u007fOS\u0015h0BY\u0016l\u0011MQ\u001d\"";
        r0 = '\t';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x0093, code lost:
        r3[r2] = r1;
        r2 = 11;
        r1 = "{0BH\u001dv+";
        r0 = '\n';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x009f, code lost:
        r3[r2] = r1;
        r2 = '\f';
        r1 = "m1ID\b}<XY\u001c9";
        r0 = 11;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x00aa, code lost:
        r3[r2] = r1;
        r2 = '\r';
        r1 = "H*_T5},_]\u001f}\u000f^S\u001b},_S\n";
        r0 = '\f';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:29:0x00b5, code lost:
        r3[r2] = r1;
        r2 = 14;
        r1 = "\u0012\u007fML\bQ\u001b\u0016";
        r0 = '\r';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x00c0, code lost:
        r3[r2] = r1;
        r2 = 15;
        r1 = "{>^N\u0011}-";
        r0 = 14;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:31:0x00cc, code lost:
        r3[r2] = r1;
        r2 = 16;
        r1 = "\u0012\u007f^Y\bw-XR\ru=INB";
        r0 = 15;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x00d7, code lost:
        r3[r2] = r1;
        r2 = 17;
        r1 = "\u0012\u007fML\bK:ON\u001dle";
        r0 = 16;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x00e2, code lost:
        r3[r2] = r1;
        r2 = 18;
        r1 = "n:^O\u0011w1";
        r0 = 17;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:34:0x00ed, code lost:
        r3[r2] = r1;
        r2 = 19;
        r1 = "p+XLB7p";
        r0 = 18;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:35:0x00f8, code lost:
        r3[r2] = r1;
        r2 = com.tencent.open.GameAppOperation.PIC_SYMBOLE;
        r1 = "{>^N\u0011}-\u0016";
        r0 = 19;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:36:0x0104, code lost:
        r3[r2] = r1;
        r2 = 21;
        r1 = "m/@S\u0019|1YQ\u001a}-";
        r0 = com.tencent.open.GameAppOperation.PIC_SYMBOLE;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:37:0x010f, code lost:
        r3[r2] = r1;
        r2 = 22;
        r1 = "n:^O\u0011w1\u0016";
        r0 = 21;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:38:0x011a, code lost:
        r3[r2] = r1;
        r2 = 23;
        r1 = "{>^N\u0011}-_";
        r0 = 22;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:39:0x0126, code lost:
        r3[r2] = r1;
        r2 = 24;
        r1 = "y/\\c\u000b}<^Y\f";
        r0 = 23;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:40:0x0132, code lost:
        r3[r2] = r1;
        r2 = 25;
        r1 = "y/\\c\u0011|";
        r0 = 24;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:41:0x013e, code lost:
        r3[r2] = r1;
        r2 = 26;
        r1 = "8*^PB";
        r0 = 25;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:42:0x0149, code lost:
        r3[r2] = r1;
        r2 = 27;
        r1 = "8/^S\u000eq;IN\u0011v;IDB";
        r0 = 26;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:43:0x0154, code lost:
        r3[r2] = r1;
        r2 = 28;
        r1 = "m-@";
        r0 = 27;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:44:0x015f, code lost:
        r3[r2] = r1;
        r2 = 29;
        r1 = "O\u0016ju";
        r0 = 28;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:45:0x016a, code lost:
        r3[r2] = r1;
        r2 = 30;
        r1 = "t=_\u001c\n}/CN\f86_\u001c\u001cq,M^\u0014};\u0002\u0012V";
        r0 = 29;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:46:0x0175, code lost:
        r3[r2] = r1;
        r2 = 31;
        r1 = "M1ID\b}<XY\u001c\"\u007fYR\u0013v0[RXh*_TX{+^PX{2H\u0006X";
        r0 = 30;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:47:0x0180, code lost:
        r3[r2] = r1;
        r2 = ' ';
        r1 = "{2H";
        r0 = 31;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:48:0x018c, code lost:
        r3[r2] = r1;
        r2 = '!';
        r1 = "{+^PX5\u007fOQ\u001c\"";
        r0 = ' ';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:49:0x0198, code lost:
        r3[r2] = r1;
        r2 = '\"';
        r1 = "t=_\u001c\n}/CN\f86_\u001c\u001dv>NP\u001d|q\u0002\u0012";
        r0 = '!';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:4:0x0010, code lost:
        if (r5 <= 1) goto L_0x0012;
     */
    /* JADX WARN: Code restructure failed: missing block: B:50:0x01a3, code lost:
        r3[r2] = r1;
        r2 = '#';
        r1 = "^>EP\u001d|\u007fXSXj:\\S\nl\u007f^Y\u001b}6ZY\u001c8r\f";
        r0 = '\"';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:51:0x01ae, code lost:
        r3[r2] = r1;
        r2 = '$';
        r1 = "K*O_\u001d};\fH\u00178-IL\u0017j+\fN\u001d{:EJ\u001d|\u007f\u0001\u001c";
        r0 = '#';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:52:0x01b9, code lost:
        r3[r2] = r1;
        r2 = '%';
        r1 = "~-IM\r}1OE";
        r0 = '$';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:53:0x01c5, code lost:
        r3[r2] = r1;
        r2 = '&';
        r1 = "j:\\S\nl\u001cXN\u0014J:OY\u0011n:H\u001cU8-IHB";
        r0 = '%';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:54:0x01d0, code lost:
        r3[r2] = r1;
        r2 = '\'';
        r1 = "|6_]\u001at:";
        r0 = '&';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:55:0x01dc, code lost:
        r3[r2] = r1;
        r2 = '(';
        r1 = "y<XU\u0017v\u007f\u0001\u001c\bj0OY\u000bk\u0013C_\u0019l6CRT82_[;w1XY\u0016le";
        r0 = '\'';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:56:0x01e8, code lost:
        r3[r2] = r1;
        r2 = ')';
        r1 = "y<XU\u0017v\u007f\u0001\u001c\bj0OY\u000bk\u0013C_\u0019l6CRT8-IL\u0017j+jN\u001di*IR\u001bae";
        r0 = '(';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:57:0x01f4, code lost:
        r3[r2] = r1;
        r2 = '*';
        r1 = "R\fcr=`<IL\fq0B\u0006";
        r0 = ')';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:58:0x01ff, code lost:
        r3[r2] = r1;
        r2 = '+';
        r1 = "k:]I\u001dv<I";
        r0 = '*';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:59:0x020a, code lost:
        r3[r2] = r1;
        r2 = ',';
        r1 = "m1ID\b}<XY\u001c9~\r\u001c\fy8mP\u0011y,~Y\u000bh0BO\u001d8<CR\f}1X\u0006";
        r0 = '+';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:5:0x0012, code lost:
        r8 = r6;
        r5 = r1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:60:0x0215, code lost:
        r3[r2] = r1;
        r2 = '-';
        r1 = "U:_O\u0019\u007f:\fz\u0011}3HOX5\u007fML\bQ;\u0016";
        r0 = ',';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:61:0x0220, code lost:
        r3[r2] = r1;
        r2 = '.';
        r1 = "M1ID\b}<XY\u001c\"\u007fYR\u0013v0[RXh*_TXu,K\u001c\fa/I\u001cU";
        r0 = '-';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:62:0x022b, code lost:
        r3[r2] = r1;
        r2 = '/';
        r1 = "6/IN\u0015q,_U\u0017vqfl-K\u0017sq=K\fm{=";
        r0 = '.';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:63:0x0236, code lost:
        r3[r2] = r1;
        r2 = '0';
        r1 = "l>K]\u0014q>_c\u000b}.EX";
        r0 = '/';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:64:0x0241, code lost:
        r3[r2] = r1;
        r2 = '1';
        r1 = "4\u007f_Y\u0016|:^u\u001c\"";
        r0 = '0';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:65:0x024c, code lost:
        r3[r2] = r1;
        r2 = '2';
        r1 = "k:BX\u001dj\u0016H";
        r0 = '1';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:66:0x0257, code lost:
        r3[r2] = r1;
        r2 = '3';
        r1 = "l>K]\u0014q>_c\u001dj-CN\u001bw;I";
        r0 = '2';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:67:0x0262, code lost:
        r3[r2] = r1;
        r2 = '4';
        r1 = "u,Kh\u0001h:\f\u0001X";
        r0 = '3';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:68:0x026d, code lost:
        r3[r2] = r1;
        r2 = '5';
        r1 = "4\u007fAO\u001fQ;\f\u0001X";
        r0 = '4';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:69:0x0278, code lost:
        r3[r2] = r1;
        r2 = '6';
        r1 = "u,K\u007f\u0017v+IR\f\"\u007f&";
        r0 = '5';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:6:0x0017, code lost:
        r10 = r5[r6];
     */
    /* JADX WARN: Code restructure failed: missing block: B:70:0x0283, code lost:
        r3[r2] = r1;
        r2 = '7';
        r1 = "u,Kc\u0011|";
        r0 = '6';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:71:0x028e, code lost:
        r3[r2] = r1;
        r2 = '8';
        r1 = "l>K]\u0014q>_\u001c\u0015k8oS\u0016l:BHB";
        r0 = '7';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:72:0x0299, code lost:
        r3[r2] = r1;
        r2 = '9';
        r1 = "{1\u0002V\bm,D\u0012\u0019v;^S\u0011|qER\f}1X\u00126W\u000bez1[\u001exu7V\u0000~y;]\u0016zy<G\u000f~s A";
        r0 = '8';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:73:0x02a5, code lost:
        r3[r2] = r1;
        r2 = ':';
        r1 = "u:_O\u0019\u007f:";
        r0 = '9';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:74:0x02b0, code lost:
        r3[r2] = r1;
        r2 = ';';
        r1 = "y/\\u\u001c";
        r0 = ':';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:75:0x02bc, code lost:
        r3[r2] = r1;
        r2 = '<';
        r1 = "y<XU\u0017ve^Y\u001b}6ZY\u001cH*_T5},_]\u001f}\u007fAO\u001fQ;\f\u0001X";
        r0 = ';';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:76:0x02c8, code lost:
        r3[r2] = r1;
        r2 = '=';
        r1 = "{1\u0002V\bm,D\u0012\u0019v;^S\u0011|qbs,Q\u0019e\u007f9L\u0016cr'L\u0006|y";
        r0 = '<';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:77:0x02d4, code lost:
        r3[r2] = r1;
        r2 = '>';
        r1 = "{0HY";
        r0 = '=';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:78:0x02e0, code lost:
        r3[r2] = r1;
        r2 = '?';
        r1 = "\\*\\P\u0011{>XY\u001c82_[V8\u0018EJ\u001d8*\\\u001c\bj0OY\u000bk6B[X5\u007f";
        r0 = '>';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:79:0x02eb, code lost:
        r3[r2] = r1;
        r2 = '@';
        r1 = "L6AYXl0\fL\nw<IO\u000b8-I_\u001dq)IXXu,K\u0012";
        r0 = '?';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:7:0x001b, code lost:
        switch((r8 % 5)) {
            case 0: goto L_0x033a;
            case 1: goto L_0x033e;
            case 2: goto L_0x0342;
            case 3: goto L_0x0346;
            default: goto L_0x001e;
        };
     */
    /* JADX WARN: Code restructure failed: missing block: B:80:0x02f6, code lost:
        r3[r2] = r1;
        r2 = 'A';
        r1 = "{1\u0002V\bm,D\u0012\u0019v;^S\u0011|qER\f}1X\u0012,Y\u0018s}4Q\u001e\u007fc;Y\u0013`~9[\u0014";
        r0 = '@';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:81:0x0302, code lost:
        r3[r2] = r1;
        r2 = 'B';
        r1 = "/9IZNyhH\u000bN{h\u0014\u000e\u001a)9\u001cY\u001cyk\u0018\n\u001a*<\u001a_L(>";
        r0 = 'A';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:82:0x030d, code lost:
        r3[r2] = r1;
        r2 = 'C';
        r1 = "4\u007fAO\u001f[0BH\u001dv+\u0016";
        r0 = 'B';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:83:0x0318, code lost:
        r3[r2] = r1;
        r2 = 'D';
        r1 = "h-C_\u001dk,n]\u000bq<iR\fq+U\u001c\fa/I\u0006";
        r0 = 'C';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:84:0x0323, code lost:
        r3[r2] = r1;
        r2 = 'E';
        r1 = "h>^O\u001dV0^Q\u0019t\u007f\u0001\u001cXu,Ku\u001c\"";
        r0 = 'D';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:85:0x032e, code lost:
        r3[r2] = r1;
        cn.jpush.android.helpers.j.z = r3;
        cn.jpush.android.helpers.j.a = new java.util.concurrent.ConcurrentLinkedQueue();
     */
    /* JADX WARN: Code restructure failed: missing block: B:86:0x0339, code lost:
        return;
     */
    /* JADX WARN: Code restructure failed: missing block: B:87:0x033a, code lost:
        r9 = 24;
     */
    /* JADX WARN: Code restructure failed: missing block: B:88:0x033e, code lost:
        r9 = '_';
     */
    /* JADX WARN: Code restructure failed: missing block: B:89:0x0342, code lost:
        r9 = ',';
     */
    /* JADX WARN: Code restructure failed: missing block: B:8:0x001e, code lost:
        r9 = 'x';
     */
    /* JADX WARN: Code restructure failed: missing block: B:90:0x0346, code lost:
        r9 = '<';
     */
    /* JADX WARN: Code restructure failed: missing block: B:9:0x0020, code lost:
        r5[r6] = (char) (r9 ^ r10);
        r6 = r8 + 1;
     */
    static {
        /*
            Method dump skipped, instructions count: 996
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.jpush.android.helpers.j.<clinit>():void");
    }

    private static JSONObject a(String str) {
        JSONObject jSONObject;
        try {
            return new JSONObject(str);
        } catch (JSONException e) {
            try {
                jSONObject = new JSONObject(a.e(str, ""));
            } catch (Exception e2) {
                e.printStackTrace();
                jSONObject = null;
            }
            new StringBuilder(z[0]).append(jSONObject);
            ac.a();
            return jSONObject;
        } catch (Exception e3) {
            return null;
        }
    }

    private static void a(Context context, Handler handler, long j) {
        new StringBuilder(z[41]).append(j);
        ac.a();
        a.c();
        a.a(context, j);
        Message.obtain(handler, 1002).sendToTarget();
    }

    public static void a(Context context, Handler handler, long j, g gVar) {
        String str;
        int i = 0;
        i iVar = (i) gVar;
        int a2 = iVar.a();
        long c = iVar.c();
        if (PushProtocol.MsgResponse(j, 0, a.y(), (byte) a2, c, iVar.e().a().longValue(), a.l()) != 0) {
            ac.b();
        } else {
            new StringBuilder(z[36]).append(c);
            ac.b();
        }
        long c2 = iVar.c();
        int a3 = iVar.a();
        String f = iVar.f();
        new StringBuilder(z[52]).append(a3).append(z[53]).append(c2);
        ac.b();
        new StringBuilder(z[54]).append(f);
        ac.a();
        LineNumberReader lineNumberReader = new LineNumberReader(new StringReader(f));
        try {
            String readLine = lineNumberReader.readLine();
            if (readLine == null) {
                ac.e();
                return;
            }
            String readLine2 = lineNumberReader.readLine();
            if (readLine2 == null) {
                ac.e();
                return;
            }
            int length = readLine.length() + readLine2.length() + 2;
            if (f.length() > length + 1) {
                str = f.substring(length);
            } else {
                ac.b();
                str = "";
            }
            new StringBuilder(z[45]).append(readLine).append(z[49]).append(readLine2).append(z[67]).append(str);
            ac.a();
            switch (a3) {
                case 0:
                case 2:
                    try {
                        new StringBuilder(z[69]).append(c2);
                        ac.b();
                        if (ServiceInterface.e(context)) {
                            ac.c();
                            return;
                        }
                        ap apVar = new ap(z[13], z[64]);
                        if (TextUtils.isEmpty(readLine) || TextUtils.isEmpty(readLine2)) {
                            ac.d();
                        } else if (!TextUtils.isEmpty(str)) {
                            new StringBuilder(z[60]).append(c2);
                            ac.b();
                            cn.jpush.android.data.a a4 = h.a(context, str, readLine, readLine2, new StringBuilder().append(c2).toString());
                            if (a4 != null) {
                                d dVar = new d(a4, a4);
                                if (a.contains(dVar)) {
                                    new StringBuilder(z[63]).append(dVar);
                                    ac.e();
                                } else {
                                    if (a.size() >= 200) {
                                        a.poll();
                                    }
                                    a.offer(dVar);
                                    if (readLine2.equalsIgnoreCase(z[66])) {
                                        h.a(context, a4);
                                    } else if (a4.e) {
                                        i = 1;
                                        if (a4.b == 4) {
                                            i = 3;
                                        }
                                    } else {
                                        i = 2;
                                    }
                                    String sb = new StringBuilder().append(c2).toString();
                                    new StringBuilder(z[68]).append(i);
                                    ac.b();
                                    if ((i & 1) != 0) {
                                        ac.b();
                                        Intent intent = new Intent(z[57]);
                                        intent.putExtra(z[50], readLine2);
                                        intent.putExtra(z[59], readLine);
                                        intent.putExtra(z[58], str);
                                        intent.putExtra(z[55], sb);
                                        intent.putExtra(z[61], a4.g);
                                        intent.addCategory(readLine);
                                        context.sendOrderedBroadcast(intent, readLine + z[47]);
                                    }
                                    if ((i & 2) != 0) {
                                        ac.b();
                                        if (!ao.a(a4.i) || !ao.a(a4.l)) {
                                            b.b(context, a4);
                                        }
                                    }
                                }
                            }
                        } else {
                            ac.e();
                        }
                        apVar.a();
                        return;
                    } catch (Exception e) {
                        ac.i();
                        return;
                    }
                case 3:
                case 6:
                case 21:
                case 22:
                    return;
                case 4:
                    k.a(context);
                    return;
                case 5:
                    a(context, handler, 0L);
                    return;
                case 9:
                    k.b(context);
                    return;
                case 20:
                    Message.obtain(handler, 1009, new l(Long.valueOf(b(str)).longValue(), iVar.e().b(), 0, null, 0)).sendToTarget();
                    ac.b();
                    try {
                        JSONObject jSONObject = new JSONObject(str);
                        int optInt = jSONObject.optInt(z[62], e.i);
                        long optLong = jSONObject.optLong(z[43]);
                        Intent intent2 = new Intent();
                        intent2.addCategory(cn.jpush.android.e.c);
                        intent2.setAction(z[65]);
                        intent2.putExtra(z[51], optInt);
                        intent2.putExtra(z[48], optLong);
                        context.sendBroadcast(intent2);
                        return;
                    } catch (Exception e2) {
                        new StringBuilder(z[56]).append(str);
                        ac.d();
                        return;
                    }
                case 44:
                    ak.a(context);
                    return;
                default:
                    new StringBuilder(z[46]).append(a3);
                    ac.b();
                    return;
            }
        } catch (IOException e3) {
            ac.i();
        }
    }

    private static void a(Context context, JSONObject jSONObject) {
        ac.b();
        try {
            JSONObject jSONObject2 = jSONObject.getJSONObject(z[11]);
            new StringBuilder(z[8]).append(jSONObject2);
            ac.b();
            int optInt = jSONObject2.optInt(z[3]);
            new StringBuilder(z[5]).append(optInt);
            ac.a();
            switch (optInt) {
                case 1:
                    String optString = jSONObject2.optString(z[1]);
                    String optString2 = jSONObject2.optString(z[7]);
                    Intent intent = new Intent();
                    intent.setClassName(optString, optString2);
                    ComponentName startService = context.startService(intent);
                    if (startService != null) {
                        ac.b(z[13], z[4] + intent + z[10] + startService);
                        break;
                    } else {
                        ac.d(z[13], z[6] + intent + z[10] + startService);
                        break;
                    }
                case 2:
                    ac.b();
                    break;
                case 10:
                    ac.b();
                    break;
                default:
                    new StringBuilder(z[9]).append(optInt);
                    ac.b();
                    break;
            }
        } catch (JSONException e) {
            ac.b(z[13], z[2], e);
        } catch (Exception e2) {
            ac.b(z[13], z[12], e2);
        }
    }

    private static long b(String str) {
        try {
            return new JSONObject(str).optLong(z[43]);
        } catch (JSONException e) {
            new StringBuilder(z[44]).append(str);
            ac.b();
            ac.a(z[13], z[42], e);
            return 0L;
        }
    }

    public static void b(Context context, Handler handler, long j, g gVar) {
        ac.a();
        cn.jpush.a.a.a.b bVar = (cn.jpush.a.a.a.b) gVar;
        long a2 = bVar.a();
        int CtrlResponse = PushProtocol.CtrlResponse(j, 0, a.y(), a2, a.n(), a.l());
        new StringBuilder(z[38]).append(CtrlResponse);
        ac.a();
        if (CtrlResponse != 0) {
            ac.d(z[13], z[35] + a2);
        } else {
            new StringBuilder(z[36]).append(a2);
            ac.b();
        }
        if (CtrlResponse == 0) {
            String c = bVar.c();
            new StringBuilder(z[40]).append(c);
            ac.b();
            if (ao.a(c)) {
                ac.d();
                return;
            }
            try {
                JSONObject a3 = a(c);
                int optInt = a3.optInt(z[32]);
                new StringBuilder(z[33]).append(optInt);
                ac.b();
                switch (optInt) {
                    case 4:
                        k.a(context);
                        break;
                    case 5:
                        JSONObject jSONObject = a3.getJSONObject(z[11]);
                        new StringBuilder(z[8]).append(jSONObject);
                        ac.b();
                        if (!jSONObject.optBoolean(z[39])) {
                            ac.b(z[13], z[34]);
                            a.c(context, true);
                            a(context, handler, jSONObject.optLong(z[37]));
                            break;
                        } else {
                            ac.b(z[13], z[30]);
                            a.c(context, false);
                            break;
                        }
                    case 6:
                    case 21:
                    case 22:
                        break;
                    case 9:
                        k.b(context);
                        break;
                    case 44:
                        ak.a(context);
                        break;
                    case 50:
                        a(context, a3);
                        break;
                    case 51:
                        b(context, a3);
                        break;
                    default:
                        new StringBuilder(z[31]).append(optInt);
                        ac.d();
                        break;
                }
            } catch (JSONException e) {
                ac.a(z[13], z[2], e);
            } catch (Exception e2) {
                ac.b(z[13], z[12], e2);
            }
        }
    }

    private static void b(Context context, JSONObject jSONObject) {
        ac.b();
        try {
            JSONObject jSONObject2 = jSONObject.getJSONObject(z[11]);
            new StringBuilder(z[8]).append(jSONObject2);
            ac.b();
            boolean optBoolean = jSONObject2.optBoolean(z[21], false);
            String optString = jSONObject2.optString(z[18], "");
            String optString2 = jSONObject2.optString(z[25], "");
            String optString3 = jSONObject2.optString(z[24], "");
            JSONArray optJSONArray = jSONObject2.optJSONArray(z[23]);
            if (optJSONArray != null) {
                for (int i = 0; i < optJSONArray.length(); i++) {
                    JSONObject jSONObject3 = optJSONArray.getJSONObject(i);
                    if (jSONObject3 != null) {
                        String optString4 = jSONObject3.optString(z[15], "");
                        String optString5 = jSONObject3.optString(z[28], "");
                        if (!ao.a(optString4) && !ao.a(optString5)) {
                            int a2 = af.a(optString4);
                            if (!optString5.startsWith(z[19])) {
                                optString5 = z[19] + optString5;
                            }
                            if (!optString5.endsWith("/")) {
                                optString5 = optString5 + "/";
                            }
                            if (a2 != -1) {
                                a.a(context, a2, optString5);
                            }
                            new StringBuilder(z[20]).append(optString4).append(z[26]).append(optString5).append(z[27]).append(a2);
                            ac.a();
                        }
                    }
                }
            }
            if (!ao.a(optString)) {
                a.f(context, optString);
            }
            if (ao.a(optString2)) {
                a.g(context, optString2);
            }
            if (ao.a(optString3)) {
                a.h(context, optString3);
            }
            new StringBuilder(z[22]).append(optString).append(z[14]).append(optString2).append(z[17]).append(optString3).append(z[16]).append(optBoolean);
            ac.b();
            if (!optBoolean) {
                return;
            }
            if (!b.c(context).toUpperCase().startsWith(z[29]) || !ao.a(a.m(context))) {
                af.a(context);
            } else {
                a.d(context, true);
            }
        } catch (Exception e) {
            ac.b(z[13], z[12], e);
        }
    }
}
