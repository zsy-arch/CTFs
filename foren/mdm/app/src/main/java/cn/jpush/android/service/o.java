package cn.jpush.android.service;

import android.text.TextUtils;
import cn.jpush.android.a;
import cn.jpush.android.util.ac;
import java.util.HashMap;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public final class o {
    private static final HashMap<Integer, String> a;
    private static final HashMap<Integer, String> b;
    private static long c;
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
            default: goto L_0x003c;
        };
     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x003c, code lost:
        r3[r2] = r1;
        r2 = 1;
        r1 = "]1\r\u000bWX%\"&[J";
        r0 = 0;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x0044, code lost:
        r3[r2] = r1;
        r2 = 2;
        r1 = "]%\u0019";
        r0 = 1;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x004c, code lost:
        r3[r2] = r1;
        r2 = 3;
        r1 = "]1\r=R";
        r0 = 2;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:19:0x0054, code lost:
        r3[r2] = r1;
        r2 = 4;
        r1 = "U5\u00149S";
        r0 = 3;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x005c, code lost:
        r3[r2] = r1;
        r2 = 5;
        r1 = "]\"\t=YR";
        r0 = 4;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x0064, code lost:
        r3[r2] = r1;
        r2 = 6;
        r1 = "i/\u0016:YK/]1DN.\u000ftUS%\u0018t\u001b\u001c";
        r0 = 5;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x006c, code lost:
        r3[r2] = r1;
        r2 = 7;
        r1 = "N,\u000b";
        r0 = 6;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x0074, code lost:
        r3[r2] = r1;
        r2 = '\b';
        r1 = "i/\u0016:YK/]&SL.\u000f \u0016_.\u00191\u0016\u0011a";
        r0 = 7;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x007d, code lost:
        r3[r2] = r1;
        cn.jpush.android.service.o.z = r3;
        r3 = new java.util.HashMap<>();
        cn.jpush.android.service.o.a = r3;
        r2 = 0;
        r1 = "s\n";
        r0 = 65535;
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x0090, code lost:
        r1 = r1.toCharArray();
        r4 = r1.length;
        r5 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x0097, code lost:
        if (r4 > 1) goto L_0x00d1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x0099, code lost:
        r7 = r5;
        r4 = r1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x009e, code lost:
        r9 = r4[r5];
     */
    /* JADX WARN: Code restructure failed: missing block: B:29:0x00a2, code lost:
        switch((r7 % 5)) {
            case 0: goto L_0x00c3;
            case 1: goto L_0x00c6;
            case 2: goto L_0x00c9;
            case 3: goto L_0x00cc;
            default: goto L_0x00a5;
        };
     */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x00a5, code lost:
        r8 = '6';
     */
    /* JADX WARN: Code restructure failed: missing block: B:31:0x00a7, code lost:
        r4[r5] = (char) (r8 ^ r9);
        r5 = r7 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x00ad, code lost:
        if (r4 != 0) goto L_0x00cf;
     */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x00af, code lost:
        r4 = r1;
        r7 = r5;
        r5 = r4;
     */
    /* JADX WARN: Code restructure failed: missing block: B:34:0x00b3, code lost:
        r9 = '<';
     */
    /* JADX WARN: Code restructure failed: missing block: B:35:0x00b7, code lost:
        r9 = 'A';
     */
    /* JADX WARN: Code restructure failed: missing block: B:36:0x00bb, code lost:
        r9 = '}';
     */
    /* JADX WARN: Code restructure failed: missing block: B:37:0x00bf, code lost:
        r9 = 'T';
     */
    /* JADX WARN: Code restructure failed: missing block: B:38:0x00c3, code lost:
        r8 = '<';
     */
    /* JADX WARN: Code restructure failed: missing block: B:39:0x00c6, code lost:
        r8 = 'A';
     */
    /* JADX WARN: Code restructure failed: missing block: B:40:0x00c9, code lost:
        r8 = '}';
     */
    /* JADX WARN: Code restructure failed: missing block: B:41:0x00cc, code lost:
        r8 = 'T';
     */
    /* JADX WARN: Code restructure failed: missing block: B:42:0x00cf, code lost:
        r4 = r4;
        r1 = r1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:43:0x00d1, code lost:
        if (r4 > r5) goto L_0x0099;
     */
    /* JADX WARN: Code restructure failed: missing block: B:44:0x00d3, code lost:
        r1 = new java.lang.String(r1).intern();
     */
    /* JADX WARN: Code restructure failed: missing block: B:45:0x00dc, code lost:
        switch(r0) {
            case 0: goto L_0x00ef;
            case 1: goto L_0x00ff;
            case 2: goto L_0x010e;
            case 3: goto L_0x011e;
            case 4: goto L_0x012f;
            case 5: goto L_0x013f;
            case 6: goto L_0x014f;
            case 7: goto L_0x0160;
            case 8: goto L_0x0171;
            case 9: goto L_0x0182;
            case 10: goto L_0x0193;
            case 11: goto L_0x01a4;
            case 12: goto L_0x01b5;
            case 13: goto L_0x01cb;
            case 14: goto L_0x01dc;
            case 15: goto L_0x01ed;
            case 16: goto L_0x01fe;
            case 17: goto L_0x020f;
            case 18: goto L_0x0220;
            case 19: goto L_0x0231;
            case 20: goto L_0x0242;
            case 21: goto L_0x0253;
            case 22: goto L_0x0264;
            case 23: goto L_0x0275;
            case 24: goto L_0x0286;
            case 25: goto L_0x0297;
            case 26: goto L_0x02a9;
            case 27: goto L_0x02ba;
            case 28: goto L_0x02cb;
            case 29: goto L_0x02dc;
            case 30: goto L_0x02ee;
            case 31: goto L_0x02ff;
            case 32: goto L_0x0310;
            case 33: goto L_0x0321;
            case 34: goto L_0x0332;
            case 35: goto L_0x0344;
            case 36: goto L_0x0356;
            case 37: goto L_0x0368;
            case 38: goto L_0x037a;
            case 39: goto L_0x038c;
            case 40: goto L_0x039d;
            case 41: goto L_0x03ae;
            default: goto L_0x00df;
        };
     */
    /* JADX WARN: Code restructure failed: missing block: B:46:0x00df, code lost:
        r3.put(r2, r1);
        r3 = cn.jpush.android.service.o.a;
        r2 = -1001;
        r1 = "y9\u001e1SXa\u001f!PZ$\u000ftEU;\u0018z\u0016l-\u00185EYa\u001e;XH \u001e \u0016O4\r$YN5S";
        r0 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:47:0x00ef, code lost:
        r3.put(r2, r1);
        r3 = cn.jpush.android.service.o.a;
        r2 = java.lang.Integer.valueOf((int) android.support.v4.app.NotificationManagerCompat.IMPORTANCE_UNSPECIFIED);
        r1 = "\u007f.\u0013:S_5\u0014;X\u001c'\u001c=ZY%StfP$\u001c'S\u001c\"\u00151UWa\u0004;CNa\u001e;XR$\u001e _S/]5XXa\u000f1BN8]8WH$\u000fu";
        r0 = 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:48:0x00ff, code lost:
        r3.put(r2, r1);
        r3 = cn.jpush.android.service.o.a;
        r2 = -998;
        r1 = "o$\u00130_R&]2WU-\u00180\u0016S3] _Q$\u0012!B\u0012a-8S]2\u0018tdY5\u000f-\u0016P \t1D\u001d";
        r0 = 2;
     */
    /* JADX WARN: Code restructure failed: missing block: B:49:0x010e, code lost:
        r3.put(r2, r1);
        r3 = cn.jpush.android.service.o.a;
        r2 = -997;
        r1 = "n$\u001e1_J(\u00133\u0016Z \u00148SXa\u0012&\u0016H(\u00101YI5StfP$\u001c'S\u001c\u0013\u0018 DEa\u00115BY3\\";
        r0 = 3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:4:0x0010, code lost:
        if (r5 <= 1) goto L_0x0012;
     */
    /* JADX WARN: Code restructure failed: missing block: B:50:0x011e, code lost:
        r3.put(r2, r1);
        r3 = cn.jpush.android.service.o.a;
        r2 = -996;
        r1 = "\u007f.\u0013:S_5\u0014;X\u001c(\u000etUP.\u000e1R\u0012a-8S]2\u0018tdY5\u000f-\u0016P \t1D\u001d";
        r0 = 4;
     */
    /* JADX WARN: Code restructure failed: missing block: B:51:0x012f, code lost:
        r3.put(r2, r1);
        r3 = cn.jpush.android.service.o.a;
        r2 = -994;
        r1 = "n$\u000e$YR2\u0018tBU,\u0018;CHo]\u0004ZY \u000e1\u0016n$\t&O\u001c-\u001c SN`";
        r0 = 5;
     */
    /* JADX WARN: Code restructure failed: missing block: B:52:0x013f, code lost:
        r3.put(r2, r1);
        r3 = cn.jpush.android.service.o.a;
        r2 = -993;
        r1 = "u/\u000b5ZU%]'Y_*\u0018 \u0018\u001c\u0011\u00111WO$]\u0006SH3\u0004tZ]5\u0018&\u0017";
        r0 = 6;
     */
    /* JADX WARN: Code restructure failed: missing block: B:53:0x014f, code lost:
        r3.put(r2, r1);
        r3 = cn.jpush.android.service.o.a;
        r2 = 11;
        r1 = "z \u00148SXa\t;\u0016N$\u001a=EH$\u000fu";
        r0 = 7;
     */
    /* JADX WARN: Code restructure failed: missing block: B:54:0x0160, code lost:
        r3.put(r2, r1);
        r3 = cn.jpush.android.service.o.a;
        r2 = 1005;
        r1 = "e.\b&\u0016]1\r\u001fSEa\u001c:R\u001c \u00130DS(\u0019tF]\"\u00165QYa\u00135[Ya\u001c&S\u001c/\u0012 \u0016Q \t7^Y%StfP$\u001c'S\u001c%\u0012!TP$]7^Y\"\u0016tBT$\u0010tW_\"\u0012&RU/\u001atBSa<$FP(\u001e5BU.\u0013tOS4]7DY \t1R\u001c.\u0013tfS3\t5Z\u0012";
        r0 = '\b';
     */
    /* JADX WARN: Code restructure failed: missing block: B:55:0x0171, code lost:
        r3.put(r2, r1);
        r3 = cn.jpush.android.service.o.a;
        r2 = 1006;
        r1 = "e.\btWR%\u000f;_Xa\r5UW \u001a1\u0016R \u00101\u0016U2]:YHa\u0018,_O5QtfP$\u001c'S\u001c3\u00183_O5\u0018&\u0016E.\b&\u0016L \u001e5][$]:WQ$]=X\u001c\u0011\u0012&B]-S";
        r0 = '\t';
     */
    /* JADX WARN: Code restructure failed: missing block: B:56:0x0182, code lost:
        r3.put(r2, r1);
        r3 = cn.jpush.android.service.o.a;
        r2 = 1007;
        r1 = "u/\u000b5ZU%]\u001d[Y(QtdY&\u0014'BY3]5Q](\u0013z";
        r0 = '\n';
     */
    /* JADX WARN: Code restructure failed: missing block: B:57:0x0193, code lost:
        r3.put(r2, r1);
        r3 = cn.jpush.android.service.o.a;
        r2 = 1009;
        r1 = "e.\b&\u0016]1\r\u001fSEa\u0014'\u0016N$\u00115BY%] Y\u001c ]:YRl<:RN.\u00140\u0016}1\rzfP$\u001c'S\u001c4\u000e1\u0016E.\b&\u0016}/\u0019&YU%]\u0015FLf\u000etWL161O\u0010a\u0012&\u0016]%\u0019tWRa<:RN.\u00140\u0016]1\rtPS3] ^U2]5FL\n\u0018-\u0018";
        r0 = 11;
     */
    /* JADX WARN: Code restructure failed: missing block: B:58:0x01a4, code lost:
        r3.put(r2, r1);
        r3 = cn.jpush.android.service.o.a;
        r2 = 10000;
        r1 = "n$\u001e1_J$\u000ftR]5\u001ctF]3\u000e1\u0016Y3\u000f;D";
        r0 = '\f';
     */
    /* JADX WARN: Code restructure failed: missing block: B:59:0x01b5, code lost:
        r3.put(r2, r1);
        r3 = new java.util.HashMap<>();
        cn.jpush.android.service.o.b = r3;
        r2 = java.lang.Integer.valueOf((int) com.tencent.smtt.utils.TbsLog.TBSLOG_CODE_SDK_THIRD_MODE);
        r1 = "q$\u000e'W[$]\u001ees\u000f]$WN2\u0014:Q\u001c2\b7UY$\u0019";
        r0 = '\r';
     */
    /* JADX WARN: Code restructure failed: missing block: B:5:0x0012, code lost:
        r8 = r6;
        r5 = r1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:60:0x01cb, code lost:
        r3.put(r2, r1);
        r3 = cn.jpush.android.service.o.b;
        r2 = java.lang.Integer.valueOf((int) com.tencent.smtt.utils.TbsLog.TBSLOG_CODE_SDK_SELF_MODE);
        r1 = "q$\u000e'W[$]\u001ees\u000f]$WN2\u0014:Q\u001c'\u001c=ZY%";
        r0 = 14;
     */
    /* JADX WARN: Code restructure failed: missing block: B:61:0x01dc, code lost:
        r3.put(r2, r1);
        r3 = cn.jpush.android.service.o.b;
        r2 = java.lang.Integer.valueOf((int) com.tencent.smtt.utils.TbsLog.TBSLOG_CODE_SDK_INVOKE_ERROR);
        r1 = "q$\u000e'W[$]5ZN$\u001c0O\u001c3\u00187SU7\u00180\u001a\u001c&\u0014\"S\u001c4\r";
        r0 = 15;
     */
    /* JADX WARN: Code restructure failed: missing block: B:62:0x01ed, code lost:
        r3.put(r2, r1);
        r3 = cn.jpush.android.service.o.b;
        r2 = java.lang.Integer.valueOf((int) com.tencent.smtt.utils.TbsLog.TBSLOG_CODE_SDK_LOAD_ERROR);
        r1 = "q$\u000e'W[$]5ZN$\u001c0O\u001c3\u00187SU7\u00180\u001a\u001c2\t=ZPa\r&Y_$\u000e'";
        r0 = 16;
     */
    /* JADX WARN: Code restructure failed: missing block: B:63:0x01fe, code lost:
        r3.put(r2, r1);
        r3 = cn.jpush.android.service.o.b;
        r2 = 1000;
        r1 = "i2\u0018&\u0016_-\u00147]Y%]5XXa\u0012$SR$\u0019tBT$]\u0019SO2\u001c3S";
        r0 = 17;
     */
    /* JADX WARN: Code restructure failed: missing block: B:64:0x020f, code lost:
        r3.put(r2, r1);
        r3 = cn.jpush.android.service.o.b;
        r2 = 1001;
        r1 = "q$\u000e'W[$]0YK/\u0011;WXa\u000e!U_$\u00180";
        r0 = 18;
     */
    /* JADX WARN: Code restructure failed: missing block: B:65:0x0220, code lost:
        r3.put(r2, r1);
        r3 = cn.jpush.android.service.o.b;
        r2 = 1002;
        r1 = "q$\u000e'W[$]&S_$\u0014\"SXa\u000e!U_$\u00180";
        r0 = 19;
     */
    /* JADX WARN: Code restructure failed: missing block: B:66:0x0231, code lost:
        r3.put(r2, r1);
        r3 = cn.jpush.android.service.o.b;
        r2 = 1003;
        r1 = "q$\u000e'W[$]'_P$\u00137S\u001c%\u0012#XP.\u001c0\u0016O4\u001e7SY%";
        r0 = com.tencent.open.GameAppOperation.PIC_SYMBOLE;
     */
    /* JADX WARN: Code restructure failed: missing block: B:67:0x0242, code lost:
        r3.put(r2, r1);
        r3 = cn.jpush.android.service.o.b;
        r2 = 1004;
        r1 = "j(\u00191Y\u001c2\u00148SR\"\u0018tRS6\u00138WS%]'C_\"\u00181R";
        r0 = 21;
     */
    /* JADX WARN: Code restructure failed: missing block: B:68:0x0253, code lost:
        r3.put(r2, r1);
        r3 = cn.jpush.android.service.o.b;
        r2 = 1005;
        r1 = "i2\u0018&\u0016_-\u00147]Y%]\"_X$\u0012tWR%]>CQ1\u00180\u0016H.]!DPa01EO \u001a1\u0016\u0014#\u000f;AO$\u000f}";
        r0 = 22;
     */
    /* JADX WARN: Code restructure failed: missing block: B:69:0x0264, code lost:
        r3.put(r2, r1);
        r3 = cn.jpush.android.service.o.b;
        r2 = 1008;
        r1 = "j(\u00191Y\u001c(\u000etPS3\u001e1\u0016_-\u0012'SXa\u001f-\u0016I2\u0018&";
        r0 = 23;
     */
    /* JADX WARN: Code restructure failed: missing block: B:6:0x0017, code lost:
        r10 = r5[r6];
     */
    /* JADX WARN: Code restructure failed: missing block: B:70:0x0275, code lost:
        r3.put(r2, r1);
        r3 = cn.jpush.android.service.o.b;
        r2 = 1007;
        r1 = "i2\u0018&\u0016_-\u00147]Y%]sywf";
        r0 = 24;
     */
    /* JADX WARN: Code restructure failed: missing block: B:71:0x0286, code lost:
        r3.put(r2, r1);
        r3 = cn.jpush.android.service.o.b;
        r2 = 1006;
        r1 = "i2\u0018&\u0016_-\u00147]Y%]su]/\u001e1Z\u001b";
        r0 = 25;
     */
    /* JADX WARN: Code restructure failed: missing block: B:72:0x0297, code lost:
        r3.put(r2, r1);
        r3 = cn.jpush.android.service.o.b;
        r2 = 1011;
        r1 = "x.\n:ZS \u0019tP](\u00111R";
        r0 = 26;
     */
    /* JADX WARN: Code restructure failed: missing block: B:73:0x02a9, code lost:
        r3.put(r2, r1);
        r3 = cn.jpush.android.service.o.b;
        r2 = 1012;
        r1 = "i2\u0018&\u0016_-\u00147]Y%] Y\u001c%\u0012#XP.\u001c0\u0016]&\u001c=X";
        r0 = 27;
     */
    /* JADX WARN: Code restructure failed: missing block: B:74:0x02ba, code lost:
        r3.put(r2, r1);
        r3 = cn.jpush.android.service.o.b;
        r2 = 1013;
        r1 = "h)\u0018tPU-\u0018tWP3\u00185REa\u0018,_O5]5XXa\u000e5[Ya\u000e=LYo]\u0010YRf\ttRS6\u00138Y]%]5Q](\u0013z";
        r0 = 28;
     */
    /* JADX WARN: Code restructure failed: missing block: B:75:0x02cb, code lost:
        r3.put(r2, r1);
        r3 = cn.jpush.android.service.o.b;
        r2 = java.lang.Integer.valueOf((int) com.amap.api.services.core.AMapException.CODE_AMAP_ENGINE_RESPONSE_ERROR);
        r1 = "u/\u000b5ZU%]$WN \u0010tYNa\b:SD1\u00187BY%]&SO4\u0011 \u0018";
        r0 = 29;
     */
    /* JADX WARN: Code restructure failed: missing block: B:76:0x02dc, code lost:
        r3.put(r2, r1);
        r3 = cn.jpush.android.service.o.b;
        r2 = java.lang.Integer.valueOf((int) android.support.v4.view.PointerIconCompat.TYPE_HORIZONTAL_DOUBLE_ARROW);
        r1 = "z \u00148SXa\t;\u0016L3\u00188Y]%]&SM4\u0014&SXa\u000f1ES4\u000f7S";
        r0 = 30;
     */
    /* JADX WARN: Code restructure failed: missing block: B:77:0x02ee, code lost:
        r3.put(r2, r1);
        r3 = cn.jpush.android.service.o.b;
        r2 = java.lang.Integer.valueOf((int) android.support.v4.view.PointerIconCompat.TYPE_VERTICAL_DOUBLE_ARROW);
        r1 = "i2\u0018&\u0016_-\u00147]Y%]=XO5\u001c8Z\u001c \u00111DHa\u0012:\u0016O5\u001c COa\u001f5D\u001c \u001b SNa\u0019;AR-\u00125RU/\u001atPU/\u0014'^Y%S";
        r0 = 31;
     */
    /* JADX WARN: Code restructure failed: missing block: B:78:0x02ff, code lost:
        r3.put(r2, r1);
        r3 = cn.jpush.android.service.o.b;
        r2 = java.lang.Integer.valueOf((int) android.support.v4.view.PointerIconCompat.TYPE_TOP_RIGHT_DIAGONAL_DOUBLE_ARROW);
        r1 = "i2\u0018&\u0016_-\u00147]Y%] ^Ya\n1TJ(\u0018#\u0011Oa\b&Z";
        r0 = ' ';
     */
    /* JADX WARN: Code restructure failed: missing block: B:79:0x0310, code lost:
        r3.put(r2, r1);
        r3 = cn.jpush.android.service.o.b;
        r2 = java.lang.Integer.valueOf((int) android.support.v4.view.PointerIconCompat.TYPE_TOP_LEFT_DIAGONAL_DOUBLE_ARROW);
        r1 = "i2\u0018&\u0016_-\u00147]Y%]7WP-]5UH(\u0012:";
        r0 = '!';
     */
    /* JADX WARN: Code restructure failed: missing block: B:7:0x001b, code lost:
        switch((r8 % 5)) {
            case 0: goto L_0x00b3;
            case 1: goto L_0x00b7;
            case 2: goto L_0x00bb;
            case 3: goto L_0x00bf;
            default: goto L_0x001e;
        };
     */
    /* JADX WARN: Code restructure failed: missing block: B:80:0x0321, code lost:
        r3.put(r2, r1);
        r3 = cn.jpush.android.service.o.b;
        r2 = java.lang.Integer.valueOf((int) android.support.v4.view.PointerIconCompat.TYPE_ZOOM_IN);
        r1 = "h)\u0018t{Y2\u000e5QYa\u000e<YKa\u0014:\u0016H)\u0018tEH \t!E\u001c#\u001c&";
        r0 = '\"';
     */
    /* JADX WARN: Code restructure failed: missing block: B:81:0x0332, code lost:
        r3.put(r2, r1);
        r3 = cn.jpush.android.service.o.b;
        r2 = java.lang.Integer.valueOf((int) android.support.v4.view.PointerIconCompat.TYPE_ZOOM_OUT);
        r1 = "\u007f-\u00147]\u001c \r$ZU2\ttWR%]'^S6] ^Ya01EO \u001a1";
        r0 = '#';
     */
    /* JADX WARN: Code restructure failed: missing block: B:82:0x0344, code lost:
        r3.put(r2, r1);
        r3 = cn.jpush.android.service.o.b;
        r2 = java.lang.Integer.valueOf((int) android.support.v4.view.PointerIconCompat.TYPE_GRAB);
        r1 = "x.\n:\u0016U,\u001c3S\u001c'\u001c=ZY%";
        r0 = '$';
     */
    /* JADX WARN: Code restructure failed: missing block: B:83:0x0356, code lost:
        r3.put(r2, r1);
        r3 = cn.jpush.android.service.o.b;
        r2 = 1021;
        r1 = "x.\n:\u0016T5\u00108\u0016Z \u00148SX";
        r0 = '%';
     */
    /* JADX WARN: Code restructure failed: missing block: B:84:0x0368, code lost:
        r3.put(r2, r1);
        r3 = cn.jpush.android.service.o.b;
        r2 = java.lang.Integer.valueOf((int) com.autonavi.ae.gmap.utils.GLMapStaticValue.AM_PARAMETERNAME_PROCESS_3DOBJECT);
        r1 = "x.\n:\u0016q$\u000e'W[$]2WU-\u00180";
        r0 = '&';
     */
    /* JADX WARN: Code restructure failed: missing block: B:85:0x037a, code lost:
        r3.put(r2, r1);
        r3 = cn.jpush.android.service.o.b;
        r2 = 1030;
        r1 = "x(\u000e7WN%] ^Ya\u00101EO \u001a1\u0016^$\u001e5CO$]=B\u001c(\u000etXS5]=X\u001c5\u00151\u0016L4\u000e<\u0016H(\u00101";
        r0 = '\'';
     */
    /* JADX WARN: Code restructure failed: missing block: B:86:0x038c, code lost:
        r3.put(r2, r1);
        r3 = cn.jpush.android.service.o.b;
        r2 = 1031;
        r1 = "o5\u0012$\u0016L4\u000e<\u0016O$\u000f\"__$";
        r0 = '(';
     */
    /* JADX WARN: Code restructure failed: missing block: B:87:0x039d, code lost:
        r3.put(r2, r1);
        r3 = cn.jpush.android.service.o.b;
        r2 = 1032;
        r1 = "n$\u000e![Ya\r!ETa\u000e1DJ(\u001e1";
        r0 = ')';
     */
    /* JADX WARN: Code restructure failed: missing block: B:88:0x03ae, code lost:
        r3.put(r2, r1);
        cn.jpush.android.service.o.c = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:89:0x03b5, code lost:
        return;
     */
    /* JADX WARN: Code restructure failed: missing block: B:8:0x001e, code lost:
        r9 = '6';
     */
    /* JADX WARN: Code restructure failed: missing block: B:9:0x0020, code lost:
        r5[r6] = (char) (r9 ^ r10);
        r6 = r8 + 1;
     */
    static {
        /*
            Method dump skipped, instructions count: 1082
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.jpush.android.service.o.<clinit>():void");
    }

    public static String a(int i) {
        if (a.containsKey(Integer.valueOf(i))) {
            return a.get(Integer.valueOf(i));
        }
        new StringBuilder(z[6]).append(i);
        ac.b();
        return null;
    }

    public static JSONObject a(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put(z[5], z[2]);
            jSONObject.put(z[3], str);
            jSONObject.put(z[4], a.m());
            jSONObject.put(z[0], z[1]);
            return jSONObject;
        } catch (JSONException e) {
            return null;
        }
    }

    public static String b(int i) {
        if (b.containsKey(Integer.valueOf(i))) {
            return b.get(Integer.valueOf(i));
        }
        new StringBuilder(z[8]).append(i);
        ac.b();
        return "";
    }

    public static JSONObject b(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put(z[5], z[7]);
            jSONObject.put(z[3], str);
            jSONObject.put(z[4], a.m());
            jSONObject.put(z[0], z[1]);
            return jSONObject;
        } catch (JSONException e) {
            return null;
        }
    }
}
