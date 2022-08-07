package cn.jpush.android.api;

import android.app.Activity;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import cn.jpush.android.a;
import cn.jpush.android.data.JPushLocalNotification;
import cn.jpush.android.e;
import cn.jpush.android.helpers.k;
import cn.jpush.android.service.PushService;
import cn.jpush.android.service.ServiceInterface;
import cn.jpush.android.service.h;
import cn.jpush.android.util.ac;
import cn.jpush.android.util.ae;
import cn.jpush.android.util.ao;
import cn.jpush.android.util.b;
import com.autonavi.ae.gmap.utils.GLMapStaticValue;
import com.tencent.smtt.sdk.TbsDownloadConfig;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.regex.Pattern;

/* loaded from: classes.dex */
public class JPushInterface {
    public static final String ACTION_ACTIVITY_OPENDED;
    public static final String ACTION_CONNECTION_CHANGE;
    public static final String ACTION_MESSAGE_RECEIVED;
    public static final String ACTION_NOTIFICATION_OPENED;
    public static final String ACTION_NOTIFICATION_RECEIVED;
    public static final String ACTION_NOTIFICATION_RECEIVED_PROXY;
    public static final String ACTION_REGISTRATION_ID;
    public static final String ACTION_RESTOREPUSH;
    public static final String ACTION_RICHPUSH_CALLBACK;
    public static final String ACTION_STATUS;
    public static final String ACTION_STOPPUSH;
    public static final String EXTRA_ACTIVITY_PARAM;
    public static final String EXTRA_ALERT;
    public static final String EXTRA_APP_KEY;
    public static final String EXTRA_CONNECTION_CHANGE;
    public static final String EXTRA_CONTENT_TYPE;
    public static final String EXTRA_EXTRA;
    public static final String EXTRA_MESSAGE;
    public static final String EXTRA_MSG_ID;
    public static final String EXTRA_NOTIFICATION_DEVELOPER_ARG0;
    public static final String EXTRA_NOTIFICATION_ID;
    public static final String EXTRA_NOTIFICATION_TITLE;
    public static final String EXTRA_NOTI_TYPE;
    public static final String EXTRA_PUSH_ID;
    public static final String EXTRA_REGISTRATION_ID;
    public static final String EXTRA_RICHPUSH_FILE_PATH;
    public static final String EXTRA_RICHPUSH_FILE_TYPE;
    public static final String EXTRA_RICHPUSH_HTML_PATH;
    public static final String EXTRA_RICHPUSH_HTML_RES;
    public static final String EXTRA_STATUS;
    public static final String EXTRA_TITLE;
    private static final Integer a;
    private static f b;
    private static ConcurrentLinkedQueue<Long> c;
    private static final String[] z;

    /* JADX WARN: Code restructure failed: missing block: B:100:0x0360, code lost:
        r3[r2] = r1;
        r2 = '6';
        r1 = "3UQ\u0018^\u001dIX\tUPBW\u0005]\u0014EPLW\u0002OOLB\u0011VG\b\u0011\u0000RG\nT\u0002EL\u000fTP\r\u0002";
        r0 = '5';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:101:0x036b, code lost:
        r3[r2] = r1;
        r2 = '7';
        r1 = "#EVLb\u0019LG\u0002R\u0015\u0000r\u0019B\u0018tK\u0001TPfC\u0005]\u0015D";
        r0 = '6';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:102:0x0376, code lost:
        r3[r2] = r1;
        r2 = '8';
        r1 = "9NT\r]\u0019D\u0002\u001cP\u0002AO\tE\u0015R\u0002\n^\u0002MC\u0018\u001dPSV\rC\u0004hM\u0019CPAL\b\u0011\u0015NF$^\u0005R\u0002\u001fY\u001fUN\b\u0011\u0012EV\u001bT\u0015N\u0002\\\u0011\u000e\u0000\u0010_\u001dPSV\rC\u0004mK\u0002BPAL\b\u0011\u0015NF!X\u001eS\u0002\u001fY\u001fUN\b\u0011\u0012EV\u001bT\u0015N\u0002\\\u0011\u000e\u0000\u0017U\u001fP";
        r0 = '7';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:103:0x0381, code lost:
        r3[r2] = r1;
        r2 = '9';
        r1 = "\"EO\u0003G\u0015\u0000V\u0004TPSK\u0000T\u001eCGLE\u0019MGM";
        r0 = '8';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:104:0x038c, code lost:
        r3[r2] = r1;
        r2 = ':';
        r1 = "P\r\u000fL";
        r0 = '9';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:105:0x0397, code lost:
        r3[r2] = r1;
        r2 = ';';
        r1 = "P\u001a\u0002";
        r0 = ':';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:106:0x03a2, code lost:
        r3[r2] = r1;
        r2 = '<';
        r1 = "#EVLb\u0019LG\u0002R\u0015\u0000r\u0019B\u0018tK\u0001TP\r\u0002";
        r0 = ';';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:107:0x03ad, code lost:
        r3[r2] = r1;
        r2 = '=';
        r1 = "#YQ\u0018T\u001d\u000eN\u0003P\u0014lK\u000eC\u0011R[V\u000b\u001aPW\u001fYB\u0011\u001b";
        r0 = '<';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:108:0x03b8, code lost:
        r3[r2] = r1;
        r2 = '>';
        r1 = "\\\u0000@\u0019X\u001cDk\b\u000bC\u0012\u0014";
        r0 = '=';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:109:0x03c3, code lost:
        r3[r2] = r1;
        r2 = '?';
        r1 = "\u001aPW\u001fYB\u0011\u001b";
        r0 = '>';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:10:0x0027, code lost:
        if (r5 != 0) goto L_0x0117;
     */
    /* JADX WARN: Code restructure failed: missing block: B:110:0x03ce, code lost:
        r3[r2] = r1;
        r2 = '@';
        r1 = "\u0011CV\u0005^\u001e\u001aK\u0002X\u0004\u0000\u000fLB\u0014Kt\tC\u0003IM\u0002\u000b";
        r0 = '?';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:111:0x03d9, code lost:
        r3[r2] = r1;
        r2 = 'A';
        r1 = "\u001cOA\r]/NM\u0018X\u0016IA\rE\u0019OL";
        r0 = '@';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:112:0x03e4, code lost:
        r3[r2] = r1;
        cn.jpush.android.api.JPushInterface.z = r3;
        cn.jpush.android.api.JPushInterface.a = 0;
        cn.jpush.android.api.JPushInterface.b = cn.jpush.android.api.f.b();
        cn.jpush.android.api.JPushInterface.c = new java.util.concurrent.ConcurrentLinkedQueue<>();
     */
    /* JADX WARN: Code restructure failed: missing block: B:113:0x03fc, code lost:
        return;
     */
    /* JADX WARN: Code restructure failed: missing block: B:114:0x03fd, code lost:
        r9 = 'p';
     */
    /* JADX WARN: Code restructure failed: missing block: B:115:0x0401, code lost:
        r9 = ' ';
     */
    /* JADX WARN: Code restructure failed: missing block: B:116:0x0405, code lost:
        r9 = '\"';
     */
    /* JADX WARN: Code restructure failed: missing block: B:117:0x0409, code lost:
        r9 = 'l';
     */
    /* JADX WARN: Code restructure failed: missing block: B:11:0x0029, code lost:
        r5 = r1;
        r8 = r6;
        r6 = r5;
     */
    /* JADX WARN: Code restructure failed: missing block: B:12:0x002d, code lost:
        cn.jpush.android.api.JPushInterface.ACTION_RICHPUSH_CALLBACK = r1;
        r1 = "\u0013N\f\u0006A\u0005SJBP\u001eDP\u0003X\u0014\u000eK\u0002E\u0015NVBc5sv#c5pw?y";
        r0 = 'B';
     */
    /* JADX WARN: Code restructure failed: missing block: B:13:0x0034, code lost:
        cn.jpush.android.api.JPushInterface.ACTION_RESTOREPUSH = r1;
        r1 = "\u0013N\f\u0006A\u0005SJBP\u001eDP\u0003X\u0014\u000eq8p$uq";
        r0 = 'C';
     */
    /* JADX WARN: Code restructure failed: missing block: B:14:0x003b, code lost:
        cn.jpush.android.api.JPushInterface.EXTRA_STATUS = r1;
        r1 = "\u0013N\f\u0006A\u0005SJBP\u001eDP\u0003X\u0014\u000ec<a;e{";
        r0 = 'D';
     */
    /* JADX WARN: Code restructure failed: missing block: B:15:0x0042, code lost:
        cn.jpush.android.api.JPushInterface.EXTRA_APP_KEY = r1;
        r1 = "\u0013N\f\u0006A\u0005SJBP\u001eDP\u0003X\u0014\u000eK\u0002E\u0015NVBr?nl)r$im\"";
        r0 = 'E';
     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x0049, code lost:
        cn.jpush.android.api.JPushInterface.ACTION_CONNECTION_CHANGE = r1;
        r1 = "\u0013N\f\u0006A\u0005SJBP\u001eDP\u0003X\u0014\u000ev%e<e";
        r0 = 'F';
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x0050, code lost:
        cn.jpush.android.api.JPushInterface.EXTRA_TITLE = r1;
        r1 = "\u0013N\f\u0006A\u0005SJBP\u001eDP\u0003X\u0014\u000ej8|<\u007fr-e8";
        r0 = 'G';
     */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x0057, code lost:
        cn.jpush.android.api.JPushInterface.EXTRA_RICHPUSH_HTML_PATH = r1;
        r1 = "\u0013N\f\u0006A\u0005SJBP\u001eDP\u0003X\u0014\u000eK\u0002E\u0015NVB\u007f?tk*x3av%~>\u007fm<t>ef";
        r0 = 'H';
     */
    /* JADX WARN: Code restructure failed: missing block: B:19:0x005e, code lost:
        cn.jpush.android.api.JPushInterface.ACTION_NOTIFICATION_OPENED = r1;
        r1 = "\u0013N\f\u0006A\u0005SJBP\u001eDP\u0003X\u0014\u000ed%}5\u007fv5a5";
        r0 = 'I';
     */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x0065, code lost:
        cn.jpush.android.api.JPushInterface.EXTRA_RICHPUSH_FILE_TYPE = r1;
        r1 = "\u0013N\f\u0006A\u0005SJBP\u001eDP\u0003X\u0014\u000eK\u0002E\u0015NVBb$av9b";
        r0 = 'J';
     */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x006c, code lost:
        cn.jpush.android.api.JPushInterface.ACTION_STATUS = r1;
        r1 = "\u0013N\f\u0006A\u0005SJBP\u001eDP\u0003X\u0014\u000el#e9fk/p$im\"n9d";
        r0 = 'K';
     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x0073, code lost:
        cn.jpush.android.api.JPushInterface.EXTRA_NOTIFICATION_ID = r1;
        r1 = "\u0013N\f\u0006A\u0005SJBP\u001eDP\u0003X\u0014\u000ec/e9vk8h/pc>p=";
        r0 = 'L';
     */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x007a, code lost:
        cn.jpush.android.api.JPushInterface.EXTRA_ACTIVITY_PARAM = r1;
        r1 = "\u0013N\f\u0006A\u0005SJBP\u001eDP\u0003X\u0014\u000er9b8\u007fk(";
        r0 = 'M';
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x0081, code lost:
        cn.jpush.android.api.JPushInterface.EXTRA_PUSH_ID = r1;
        r1 = "\u0013N\f\u0006A\u0005SJBP\u001eDP\u0003X\u0014\u000eo)b#ae)";
        r0 = 'N';
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x0088, code lost:
        cn.jpush.android.api.JPushInterface.EXTRA_MESSAGE = r1;
        r1 = "\u0013N\f\u0006A\u0005SJBP\u001eDP\u0003X\u0014\u000eo?v/if";
        r0 = 'O';
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x0090, code lost:
        cn.jpush.android.api.JPushInterface.EXTRA_MSG_ID = r1;
        r1 = "\u0013N\f\u0006A\u0005SJBP\u001eDP\u0003X\u0014\u000eK\u0002E\u0015NVB\u007f?tk*x3av%~>\u007fp)r5it)u/pp#i)";
        r0 = 'P';
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x0098, code lost:
        cn.jpush.android.api.JPushInterface.ACTION_NOTIFICATION_RECEIVED_PROXY = r1;
        r1 = "\u0013N\f\u0006A\u0005SJBP\u001eDP\u0003X\u0014\u000ea#\u007f$el8n$yr)";
        r0 = 'Q';
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x00a0, code lost:
        cn.jpush.android.api.JPushInterface.EXTRA_CONTENT_TYPE = r1;
        r1 = "\u0013N\f\u0006A\u0005SJBP\u001eDP\u0003X\u0014\u000eK\u0002E\u0015NVB|5sq-v5\u007fp)r5it)u";
        r0 = 'R';
     */
    /* JADX WARN: Code restructure failed: missing block: B:29:0x00a8, code lost:
        cn.jpush.android.api.JPushInterface.ACTION_MESSAGE_RECEIVED = r1;
        r1 = "\u0013N\f\u0006A\u0005SJBP\u001eDP\u0003X\u0014\u000eg4e\"a";
        r0 = 'S';
     */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x00b0, code lost:
        cn.jpush.android.api.JPushInterface.EXTRA_EXTRA = r1;
        r1 = "\u0013N\f\u0006A\u0005SJBP\u001eDP\u0003X\u0014\u000ea#\u007f>ea8x?n}/y1ne)";
        r0 = 'T';
     */
    /* JADX WARN: Code restructure failed: missing block: B:31:0x00b8, code lost:
        cn.jpush.android.api.JPushInterface.EXTRA_CONNECTION_CHANGE = r1;
        r1 = "\u0013N\f\u0006A\u0005SJBP\u001eDP\u0003X\u0014\u000eK\u0002E\u0015NVB\u007f?tk*x3av%~>\u007fp)r5it)u";
        r0 = 'U';
     */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x00c0, code lost:
        cn.jpush.android.api.JPushInterface.ACTION_NOTIFICATION_RECEIVED = r1;
        r1 = "\u0013N\f\u0006A\u0005SJBP\u001eDP\u0003X\u0014\u000el#e9fk/p$im\"n$yr)";
        r0 = 'V';
     */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x00c8, code lost:
        cn.jpush.android.api.JPushInterface.EXTRA_NOTI_TYPE = r1;
        r1 = "\u0013N\f\u0006A\u0005SJBP\u001eDP\u0003X\u0014\u000ep)v9sv>p$im\"n9d";
        r0 = 'W';
     */
    /* JADX WARN: Code restructure failed: missing block: B:34:0x00d0, code lost:
        cn.jpush.android.api.JPushInterface.EXTRA_REGISTRATION_ID = r1;
        r1 = "\u0013N\f\u0006A\u0005SJBP\u001eDP\u0003X\u0014\u000el#e9fk/p$im\"n4et)}?pg>n1re\\";
        r0 = 'X';
     */
    /* JADX WARN: Code restructure failed: missing block: B:35:0x00d8, code lost:
        cn.jpush.android.api.JPushInterface.EXTRA_NOTIFICATION_DEVELOPER_ARG0 = r1;
        r1 = "\u0013N\f\u0006A\u0005SJBP\u001eDP\u0003X\u0014\u000ed%}5\u007fr-e8";
        r0 = 'Y';
     */
    /* JADX WARN: Code restructure failed: missing block: B:36:0x00e0, code lost:
        cn.jpush.android.api.JPushInterface.EXTRA_RICHPUSH_FILE_PATH = r1;
        r1 = "\u0013N\f\u0006A\u0005SJBP\u001eDP\u0003X\u0014\u000el#e9fk/p$im\"n3ol8t>t}8x$lg";
        r0 = 'Z';
     */
    /* JADX WARN: Code restructure failed: missing block: B:37:0x00e8, code lost:
        cn.jpush.android.api.JPushInterface.EXTRA_NOTIFICATION_TITLE = r1;
        r1 = "\u0013N\f\u0006A\u0005SJBP\u001eDP\u0003X\u0014\u000ej8|<\u007fp)b";
        r0 = '[';
     */
    /* JADX WARN: Code restructure failed: missing block: B:38:0x00f0, code lost:
        cn.jpush.android.api.JPushInterface.EXTRA_RICHPUSH_HTML_RES = r1;
        r1 = "\u0013N\f\u0006A\u0005SJBP\u001eDP\u0003X\u0014\u000eK\u0002E\u0015NVBc5gk?e\"av%~>";
        r0 = '\\';
     */
    /* JADX WARN: Code restructure failed: missing block: B:39:0x00f8, code lost:
        cn.jpush.android.api.JPushInterface.ACTION_REGISTRATION_ID = r1;
        r1 = "\u0013N\f\u0006A\u0005SJBP\u001eDP\u0003X\u0014\u000eK\u0002E\u0015NVBp3tk#\u007f/aa8x&iv5n?pg\"u5d";
        r0 = ']';
     */
    /* JADX WARN: Code restructure failed: missing block: B:40:0x0100, code lost:
        cn.jpush.android.api.JPushInterface.ACTION_ACTIVITY_OPENDED = r1;
        r1 = "\u0013N\f\u0006A\u0005SJBP\u001eDP\u0003X\u0014\u000eK\u0002E\u0015NVBb$or<d#h";
        r0 = '^';
     */
    /* JADX WARN: Code restructure failed: missing block: B:41:0x0108, code lost:
        cn.jpush.android.api.JPushInterface.ACTION_STOPPUSH = r1;
        r1 = "\u0013N\f\u0006A\u0005SJBP\u001eDP\u0003X\u0014\u000ec t\"t";
        r0 = '_';
     */
    /* JADX WARN: Code restructure failed: missing block: B:42:0x0110, code lost:
        cn.jpush.android.api.JPushInterface.EXTRA_ALERT = r1;
        r1 = ":pW\u001fY9NV\tC\u0016AA\t";
        r0 = 65535;
     */
    /* JADX WARN: Code restructure failed: missing block: B:43:0x0117, code lost:
        r5 = r5;
        r1 = r1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:44:0x0119, code lost:
        if (r5 > r6) goto L_0x0013;
     */
    /* JADX WARN: Code restructure failed: missing block: B:45:0x011b, code lost:
        r1 = new java.lang.String(r1).intern();
     */
    /* JADX WARN: Code restructure failed: missing block: B:46:0x0124, code lost:
        switch(r0) {
            case 0: goto L_0x0130;
            case 1: goto L_0x0139;
            case 2: goto L_0x0142;
            case 3: goto L_0x014b;
            case 4: goto L_0x0154;
            case 5: goto L_0x015d;
            case 6: goto L_0x0166;
            case 7: goto L_0x0170;
            case 8: goto L_0x017b;
            case 9: goto L_0x0186;
            case 10: goto L_0x0191;
            case 11: goto L_0x019c;
            case 12: goto L_0x01a7;
            case 13: goto L_0x01b2;
            case 14: goto L_0x01bd;
            case 15: goto L_0x01c8;
            case 16: goto L_0x01d3;
            case 17: goto L_0x01de;
            case 18: goto L_0x01e9;
            case 19: goto L_0x01f4;
            case 20: goto L_0x01ff;
            case 21: goto L_0x020a;
            case 22: goto L_0x0215;
            case 23: goto L_0x0220;
            case 24: goto L_0x022b;
            case 25: goto L_0x0236;
            case 26: goto L_0x0241;
            case 27: goto L_0x024c;
            case 28: goto L_0x0257;
            case 29: goto L_0x0262;
            case 30: goto L_0x026e;
            case 31: goto L_0x0279;
            case 32: goto L_0x0284;
            case 33: goto L_0x028f;
            case 34: goto L_0x029a;
            case 35: goto L_0x02a5;
            case 36: goto L_0x02b0;
            case 37: goto L_0x02bb;
            case 38: goto L_0x02c6;
            case 39: goto L_0x02d1;
            case 40: goto L_0x02dc;
            case 41: goto L_0x02e7;
            case 42: goto L_0x02f2;
            case 43: goto L_0x02fd;
            case 44: goto L_0x0308;
            case 45: goto L_0x0313;
            case 46: goto L_0x031e;
            case 47: goto L_0x0329;
            case 48: goto L_0x0334;
            case 49: goto L_0x033f;
            case 50: goto L_0x034a;
            case 51: goto L_0x0355;
            case 52: goto L_0x0360;
            case 53: goto L_0x036b;
            case 54: goto L_0x0376;
            case 55: goto L_0x0381;
            case 56: goto L_0x038c;
            case 57: goto L_0x0397;
            case 58: goto L_0x03a2;
            case 59: goto L_0x03ad;
            case 60: goto L_0x03b8;
            case 61: goto L_0x03c3;
            case 62: goto L_0x03ce;
            case 63: goto L_0x03d9;
            case 64: goto L_0x03e4;
            case 65: goto L_0x002d;
            case 66: goto L_0x0034;
            case 67: goto L_0x003b;
            case 68: goto L_0x0042;
            case 69: goto L_0x0049;
            case 70: goto L_0x0050;
            case 71: goto L_0x0057;
            case 72: goto L_0x005e;
            case 73: goto L_0x0065;
            case 74: goto L_0x006c;
            case 75: goto L_0x0073;
            case 76: goto L_0x007a;
            case 77: goto L_0x0081;
            case 78: goto L_0x0088;
            case 79: goto L_0x0090;
            case 80: goto L_0x0098;
            case 81: goto L_0x00a0;
            case 82: goto L_0x00a8;
            case 83: goto L_0x00b0;
            case 84: goto L_0x00b8;
            case 85: goto L_0x00c0;
            case 86: goto L_0x00c8;
            case 87: goto L_0x00d0;
            case 88: goto L_0x00d8;
            case 89: goto L_0x00e0;
            case 90: goto L_0x00e8;
            case 91: goto L_0x00f0;
            case 92: goto L_0x00f8;
            case 93: goto L_0x0100;
            case 94: goto L_0x0108;
            case 95: goto L_0x0110;
            default: goto L_0x0127;
        };
     */
    /* JADX WARN: Code restructure failed: missing block: B:47:0x0127, code lost:
        r3[r2] = r1;
        r2 = 1;
        r1 = "\u0011NF\u001e^\u0019D\f\u0003B^bW\u0005]\u0014\u000et)c#im\"\u001f#di3x>t\u001e^\u0002";
        r0 = 0;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:48:0x0130, code lost:
        r3[r2] = r1;
        r2 = 2;
        r1 = "\u0011NF\u001e^\u0019D\f\rA\u0000\u000ec\u000fE\u0019VK\u0018H";
        r0 = 1;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:49:0x0139, code lost:
        r3[r2] = r1;
        r2 = 3;
        r1 = "\u0011NF\u001e^\u0019D\f\u001cT\u0002MK\u001fB\u0019OLBp3cg?b/fk\"t/lm/p$im\"";
        r0 = 2;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:4:0x0011, code lost:
        if (r5 <= 1) goto L_0x0013;
     */
    /* JADX WARN: Code restructure failed: missing block: B:50:0x0142, code lost:
        r3[r2] = r1;
        r2 = 4;
        r1 = "\u0002ES\u0019T\u0003Tr\tC\u001dIQ\u001fX\u001fNQ";
        r0 = 3;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:51:0x014b, code lost:
        r3[r2] = r1;
        r2 = 5;
        r1 = "\u0011NF\u001e^\u0019D\f\u001cT\u0002MK\u001fB\u0019OLBc5af3a8ol)n#tc8t";
        r0 = 4;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:52:0x0154, code lost:
        r3[r2] = r1;
        r2 = 6;
        r1 = "\u0011NF\u001e^\u0019D\f\u001cT\u0002MK\u001fB\u0019OLBf\"iv)n5xv)c>an3b$op-v5";
        r0 = 5;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:53:0x015d, code lost:
        r3[r2] = r1;
        r2 = 7;
        r1 = "Y\t";
        r0 = 6;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:54:0x0166, code lost:
        r3[r2] = r1;
        r2 = '\b';
        r1 = "X{\u0012A\b-\\\u00137\u0001]\u0019\u007f\u0010\u0003+\u0010\u000f_lY||Dj@\r\u001b1MA{\u0012A\b-\\\u00107\u0001]\u0013\u007fE";
        r0 = 7;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:55:0x0170, code lost:
        r3[r2] = r1;
        r2 = '\t';
        r1 = "Y\\\n";
        r0 = '\b';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:56:0x017b, code lost:
        r3[r2] = r1;
        r2 = '\n';
        r1 = "9NT\r]\u0019D\u0002\u0018X\u001dE\u0002\n^\u0002MC\u0018\u0011]\u0000";
        r0 = '\t';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:57:0x0186, code lost:
        r3[r2] = r1;
        r2 = 11;
        r1 = "\u0011CV\u0005^\u001e\u001aQ\tE UQ\u0004e\u0019MGL\u001cPEL\rS\u001cEFV";
        r0 = '\n';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:58:0x0191, code lost:
        r3[r2] = r1;
        r2 = '\f';
        r1 = "\u001dUN\u0018X/T[\u001cT";
        r0 = 11;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:59:0x019c, code lost:
        r3[r2] = r1;
        r2 = '\r';
        r1 = "\\\u0000R\u0019B\u0018tK\u0001TJ";
        r0 = '\f';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:5:0x0013, code lost:
        r8 = r6;
        r5 = r1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:60:0x01a7, code lost:
        r3[r2] = r1;
        r2 = 14;
        r1 = "\u0011CV\u0005^\u001e\u001aQ\tE UQ\u0004e\u0019MGL\u001cPCN\u0003B\u0015D";
        r0 = '\r';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:61:0x01b2, code lost:
        r3[r2] = r1;
        r2 = 15;
        r1 = "]\t\tD";
        r0 = 14;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:62:0x01bd, code lost:
        r3[r2] = r1;
        r2 = 16;
        r1 = "\u0015NC\u000e]\u0015\u007fR\u0019B\u0018\u007fV\u0005\\\u0015";
        r0 = 15;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:63:0x01c8, code lost:
        r3[r2] = r1;
        r2 = 17;
        r1 = "\u0013N\f\u0006A\u0005SJBP\u001eDP\u0003X\u0014\u000eK\u0002E\u0015NVB|%lv%n rm/t#s";
        r0 = 16;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:64:0x01d3, code lost:
        r3[r2] = r1;
        r2 = 18;
        r1 = "X{\u0012A\u0007-[\u0012@\u0006\r\t}D\u0019";
        r0 = 17;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:65:0x01de, code lost:
        r3[r2] = r1;
        r2 = 19;
        r1 = "1LP\tP\u0014Y\u0002?T\u0004pW\u001fY$IO\t\u001dPGK\u001aTPURL\u001cP";
        r0 = 18;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:66:0x01e9, code lost:
        r3[r2] = r1;
        r2 = com.tencent.open.GameAppOperation.PIC_SYMBOLE;
        r1 = ">un \u0011\u001eOV\u0005W\u0019CC\u0018X\u001fN";
        r0 = 19;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:67:0x01f4, code lost:
        r3[r2] = r1;
        r2 = 21;
        r1 = "PHC\u001f\u0011\u001eOVLS\u0015ELLB\u0015T\u0002\u0005_PYM\u0019CPAR\u001c\u001dPUQ\t\u0011\u0014ED\rD\u001cT\u0002\u000eD\u0019LF\tCQ";
        r0 = com.tencent.open.GameAppOperation.PIC_SYMBOLE;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:68:0x01ff, code lost:
        r3[r2] = r1;
        r2 = 22;
        r1 = "$HGLS\u0005IN\bT\u0002\u0000U\u0005E\u0018\u0000K\b\u000b";
        r0 = 21;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:69:0x020a, code lost:
        r3[r2] = r1;
        r2 = 23;
        r1 = "\u0011CV\u0005^\u001e\u001aQ\u0018^\u0000pW\u001fY";
        r0 = 22;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:6:0x0018, code lost:
        r10 = r5[r6];
     */
    /* JADX WARN: Code restructure failed: missing block: B:70:0x0215, code lost:
        r3[r2] = r1;
        r2 = 24;
        r1 = "$HGL\\\u0003Gk\b\u0011\u0019S\u0002\u0002^\u0004\u0000T\r]\u0019D\u0002A\u0011";
        r0 = 23;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:71:0x0220, code lost:
        r3[r2] = r1;
        r2 = 25;
        r1 = "\u0019D\u0002\u001fY\u001fUN\b\u0011\u0012E\u0002\u0000P\u0002GG\u001e\u0011\u0004HC\u0002\u0011@";
        r0 = 24;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:72:0x022b, code lost:
        r3[r2] = r1;
        r2 = 26;
        r1 = ">un \u0011\u0000UQ\u0004\u007f\u001fTK\nX\u0013AV\u0005^\u001ebW\u0005]\u0014EP";
        r0 = 25;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:73:0x0236, code lost:
        r3[r2] = r1;
        r2 = 27;
        r1 = "\u0011CV\u0005^\u001e\u001aQ\tE UQ\u0004\u007f\u001fTK\nX\u0013AV\u0005^\u001ebW\u0005]\u0014EPL\u001cPIFV";
        r0 = 26;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:74:0x0241, code lost:
        r3[r2] = r1;
        r2 = 28;
        r1 = "9NT\r]\u0019D\u0002\bP\t\u0000D\u0003C\u001dAVL\u001cP";
        r0 = 27;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:75:0x024c, code lost:
        r3[r2] = r1;
        r2 = 29;
        r1 = "@\u0011\u0010_\u0005E\u0016}\\oB\u0013";
        r0 = 28;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:76:0x0257, code lost:
        r3[r2] = r1;
        r2 = 30;
        r1 = "9NT\r]\u0019D\u0002\u0018X\u001dE\u0002\n^\u0002MC\u0018\u0011]\u0000Q\u0018P\u0002Tj\u0003D\u0002\u0000Q\u0004^\u0005LFL]\u0015SQLE\u0018ALLT\u001eDj\u0003D\u0002";
        r0 = 29;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:77:0x0262, code lost:
        r3[r2] = r1;
        r2 = 31;
        r1 = "械浫划弿剼泑朩罳纰〳欔劈佾屪圙杹罱绾斚臛勘织经戋衽ひ";
        r0 = 30;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:78:0x026e, code lost:
        r3[r2] = r1;
        r2 = ' ';
        r1 = "\u0011CV\u0005^\u001e\u001aE\tE UQ\u0004\u007f\u001fTK\nX\u0013AV\u0005^\u001ebW\u0005]\u0014EPL\u000bP";
        r0 = 31;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:79:0x0279, code lost:
        r3[r2] = r1;
        r2 = '!';
        r1 = "\u0011CV\u0005^\u001e\u001aQ\tE1LK\rB1NF8P\u0017S\u0002A\u0011\u0011LK\rBJ";
        r0 = ' ';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:7:0x001c, code lost:
        switch((r8 % 5)) {
            case 0: goto L_0x03fd;
            case 1: goto L_0x0401;
            case 2: goto L_0x0405;
            case 3: goto L_0x0409;
            default: goto L_0x001f;
        };
     */
    /* JADX WARN: Code restructure failed: missing block: B:80:0x0284, code lost:
        r3[r2] = r1;
        r2 = '\"';
        r1 = "\\\u0000U\u0005]\u001c\u0000L\u0003EPSG\u0018\u0011\u0011LK\rBPTJ\u0005BPTK\u0001T^";
        r0 = '!';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:81:0x028f, code lost:
        r3[r2] = r1;
        r2 = '#';
        r1 = "9NT\r]\u0019D\u0002\u0018P\u0017S\u000eLF\u0019LNL_\u001fT\u0002\u001fT\u0004\u0000V\rV\u0003\u0000V\u0004X\u0003\u0000V\u0005\\\u0015\u000e";
        r0 = '\"';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:82:0x029a, code lost:
        r3[r2] = r1;
        r2 = '$';
        r1 = ">un \u0011\u0013OL\u0018T\bT";
        r0 = '#';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:83:0x02a5, code lost:
        r3[r2] = r1;
        r2 = '%';
        r1 = ">un \u0011\u0011LK\rBPAL\b\u0011\u0004AE\u001f\u001fPgK\u001aTPURLP\u0013TK\u0003_^";
        r0 = '$';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:84:0x02b0, code lost:
        r3[r2] = r1;
        r2 = '&';
        r1 = "9NT\r]\u0019D\u0002\r]\u0019AQV\u0011";
        r0 = '%';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:85:0x02bb, code lost:
        r3[r2] = r1;
        r2 = '\'';
        r1 = "\u0004AE\u001f\u0011\u001cEL\u000bE\u0018\u001a";
        r0 = '&';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:86:0x02c6, code lost:
        r3[r2] = r1;
        r2 = '(';
        r1 = "$HGL]\u0015NE\u0018YPODLE\u0011GQLB\u0018OW\u0000UPBGL]\u0015SQLE\u0018ALL\u0006@\u0010\u0012LS\tTG\u001f\u001f";
        r0 = '\'';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:87:0x02d1, code lost:
        r3[r2] = r1;
        r2 = ')';
        r1 = "\\\u0000V\rV\u0003\u001a";
        r0 = '(';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:88:0x02dc, code lost:
        r3[r2] = r1;
        r2 = '*';
        r1 = "\u0003EQ\u001fT\u0019OLLE\u0019MG\u0003D\u0004\u0000N\rC\u0017EPLE\u0018ALL\u0000\u0014A[";
        r0 = ')';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:89:0x02e7, code lost:
        r3[r2] = r1;
        r2 = '+';
        r1 = "\u0003EQ\u001fT\u0019OLLE\u0019MG\u0003D\u0004\u0000N\tB\u0003\u0000V\u0004P\u001e\u0000\u0013\\B";
        r0 = '*';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:8:0x001f, code lost:
        r9 = '1';
     */
    /* JADX WARN: Code restructure failed: missing block: B:90:0x02f2, code lost:
        r3[r2] = r1;
        r2 = ',';
        r1 = "\u001dAZ\"D\u001d\u0000Q\u0004^\u0005LFL\u000fP\u0010\u000eLv\u0019VGLD\u0000\u0000C\u000fE\u0019OLB\u001f";
        r0 = '+';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:91:0x02fd, code lost:
        r3[r2] = r1;
        r2 = '-';
        r1 = "\u0011CV\u0005^\u001e\u001aQ\tE<AV\tB\u0004nM\u0018X\u0016IA\rE\u0019OL\"D\u001dBG\u001e\u0011J\u0000";
        r0 = ',';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:92:0x0308, code lost:
        r3[r2] = r1;
        r2 = '.';
        r1 = "\u001eOV\u0005W\u0019CC\u0018X\u001fN";
        r0 = '-';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:93:0x0313, code lost:
        r3[r2] = r1;
        r2 = '/';
        r1 = "9NT\r]\u0019D\u0002\u0002^\u0004ID\u0005R\u0011TK\u0003_9D\u000eLv\u0019VGLD\u0000\u0000C\u000fE\u0019OLB\u001f";
        r0 = '.';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:94:0x031e, code lost:
        r3[r2] = r1;
        r2 = '0';
        r1 = "\u0011CV\u0005^\u001e\u001aP\tB\u0005MG<D\u0003H";
        r0 = '/';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:95:0x0329, code lost:
        r3[r2] = r1;
        r2 = '1';
        r1 = "9NT\r]\u0019D\u0002\u0018P\u0017\u001a\u0002";
        r0 = '0';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:96:0x0334, code lost:
        r3[r2] = r1;
        r2 = '2';
        r1 = "\u001cOA\r]/NM\u0018X\u0016IA\rE\u0019OL3X\u0014";
        r0 = '1';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:97:0x033f, code lost:
        r3[r2] = r1;
        r2 = '3';
        r1 = "$HGL]\u0015NE\u0004EPODLE\u0011GQL\\\u0011Y@\t\u0011\u001dOP\t\u0011\u0004HC\u0002\u0011A\u0010\u0012\\\u001f";
        r0 = '2';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:98:0x034a, code lost:
        r3[r2] = r1;
        r2 = '4';
        r1 = "9NT\r]\u0019D\u0002\u0018P\u0017\u0000\u0018L";
        r0 = '3';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:99:0x0355, code lost:
        r3[r2] = r1;
        r2 = '5';
        r1 = "\u0017EV>T\u0013OP\ba\u0005SJ\"^\u0004ID\u0005R\u0011TK\u0003_2UK\u0000U\u0015R\u0002A\u0011";
        r0 = '4';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:9:0x0021, code lost:
        r5[r6] = (char) (r9 ^ r10);
        r6 = r8 + 1;
     */
    static {
        /*
            Method dump skipped, instructions count: 1246
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.jpush.android.api.JPushInterface.<clinit>():void");
    }

    private static PushNotificationBuilder a(String str) {
        new StringBuilder(z[53]).append(str);
        ac.a();
        String e = a.e(e.e, str);
        if (ao.a(e)) {
            return null;
        }
        new StringBuilder(z[54]).append(e);
        ac.a();
        return BasicPushNotificationBuilder.a(e);
    }

    private static void a(Context context) {
        if (context == null) {
            throw new IllegalArgumentException(z[36]);
        }
        a.p(context);
    }

    private static void a(Context context, String str, Set<String> set, TagAliasCallback tagAliasCallback, boolean z2) {
        int a2;
        int b2;
        if (context == null) {
            throw new IllegalArgumentException(z[36]);
        }
        if (e.a && !b.b(context)) {
            ac.b(z[0], z[31]);
        }
        if (str == null || (b2 = ae.b(str)) == 0) {
            if (tagAliasCallback == null && !z2) {
                set = filterValidTags(set);
            }
            if (set == null || (a2 = ae.a(set)) == 0) {
                String stringTags = getStringTags(set);
                if (str == null && stringTags == null) {
                    ac.e(z[0], z[37]);
                    if (tagAliasCallback != null) {
                        tagAliasCallback.gotResult(e.a, str, set);
                        return;
                    }
                    return;
                }
                if (stringTags != null) {
                    String replaceAll = stringTags.replaceAll(",", "");
                    int length = !ao.a(replaceAll) ? replaceAll.getBytes().length + 0 : 0;
                    new StringBuilder(z[39]).append(length);
                    ac.a();
                    if (!(length <= 7000)) {
                        if (tagAliasCallback != null) {
                            tagAliasCallback.gotResult(e.h, str, set);
                        }
                        ac.e(z[0], z[40]);
                        return;
                    }
                }
                ac.b(z[0], z[33] + str + z[41] + stringTags);
                ServiceInterface.a(context, str, stringTags, new b(str, set, tagAliasCallback));
                return;
            }
            ac.b(z[0], z[35]);
            if (tagAliasCallback != null) {
                tagAliasCallback.gotResult(a2, str, set);
                return;
            }
            return;
        }
        ac.b(z[0], z[38] + str + z[34]);
        if (tagAliasCallback != null) {
            tagAliasCallback.gotResult(b2, str, set);
        }
    }

    private static void a(Context context, boolean z2, String str) {
        ac.b();
        a.b(context, z2);
        if (!z2) {
            ac.b(z[0], z[14]);
            return;
        }
        String str2 = z[8];
        if (Pattern.compile(z[18] + str2 + z[9] + str2 + z[15] + str2 + z[7]).matcher(str).matches()) {
            String e = a.e(context);
            if (str.equals(e)) {
                ac.b(z[0], z[19] + e);
                return;
            }
            ac.b(z[0], z[11] + z2 + z[13] + str);
            if (ServiceInterface.b() || !e.n) {
                a.b(context, str);
                return;
            }
            Intent intent = new Intent(context, PushService.class);
            intent.setAction(z[17]);
            Bundle bundle = new Bundle();
            bundle.putInt(z[12], 3);
            bundle.putString(z[16], str);
            intent.putExtras(bundle);
            context.startService(intent);
            return;
        }
        ac.e(z[0], z[10] + str);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean a(int i) {
        if (i <= 0) {
            return false;
        }
        if (a(new StringBuilder().append(i).toString()) != null) {
            return true;
        }
        ac.d(z[0], z[22] + i + z[21]);
        return false;
    }

    public static void addLocalNotification(Context context, JPushLocalNotification jPushLocalNotification) {
        a(context);
        if (!e.n) {
            h.a(context).a(context, jPushLocalNotification);
            return;
        }
        Intent intent = new Intent(context, PushService.class);
        Bundle bundle = new Bundle();
        bundle.putInt(z[12], 6);
        bundle.putSerializable(z[65], jPushLocalNotification);
        intent.putExtras(bundle);
        intent.setAction(z[17]);
        context.startService(intent);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static PushNotificationBuilder b(int i) {
        ac.a(z[0], z[32] + i);
        if (i <= 0) {
            i = a.intValue();
        }
        PushNotificationBuilder pushNotificationBuilder = null;
        try {
            pushNotificationBuilder = a(new StringBuilder().append(i).toString());
        } catch (Exception e) {
            ac.g();
        }
        if (pushNotificationBuilder != null) {
            return pushNotificationBuilder;
        }
        ac.b();
        return new DefaultPushNotificationBuilder();
    }

    public static void clearAllNotifications(Context context) {
        a(context);
        ServiceInterface.c(context);
    }

    public static void clearLocalNotifications(Context context) {
        a(context);
        if (!e.n) {
            h.a(context).b(context);
            return;
        }
        Intent intent = new Intent(context, PushService.class);
        Bundle bundle = new Bundle();
        bundle.putInt(z[12], 8);
        intent.putExtras(bundle);
        intent.setAction(z[17]);
        context.startService(intent);
    }

    public static void clearNotificationById(Context context, int i) {
        a(context);
        if (i <= 0) {
            ac.e(z[0], z[47]);
        } else {
            ((NotificationManager) context.getSystemService(z[46])).cancel(i);
        }
    }

    public static Set<String> filterValidTags(Set<String> set) {
        if (set == null) {
            return null;
        }
        if (set.isEmpty()) {
            return set;
        }
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        int i = 0;
        for (String str : set) {
            if (ao.a(str) || !ae.a(str)) {
                ac.e(z[0], z[52] + str);
                i = i;
            } else {
                linkedHashSet.add(str);
                i++;
                if (i >= 1000) {
                    ac.d(z[0], z[51]);
                    return linkedHashSet;
                }
            }
        }
        return linkedHashSet;
    }

    public static boolean getConnectionState(Context context) {
        a(context);
        return cn.jpush.android.service.a.a == a.a(context);
    }

    public static String getRegistrationID(Context context) {
        a(context);
        return a.C();
    }

    public static String getStringTags(Set<String> set) {
        String str = null;
        if (set == null) {
            return null;
        }
        if (set.isEmpty()) {
            return "";
        }
        int i = 0;
        for (String str2 : set) {
            if (ao.a(str2) || !ae.a(str2)) {
                ac.e(z[0], z[49] + str2);
                i = i;
            } else {
                str = str == null ? str2 : str + "," + str2;
                i++;
                if (i >= 1000) {
                    return str;
                }
            }
        }
        return str;
    }

    public static String getUdid(Context context) {
        a(context);
        return b.j(context);
    }

    public static void init(Context context) {
        ac.b(z[0], z[64] + ServiceInterface.a() + z[62]);
        try {
            System.loadLibrary(z[63]);
        } catch (Throwable th) {
            ac.e(z[0], z[61] + th);
            th.printStackTrace();
        }
        a(context);
        if (e.a && !b.b(context)) {
            ac.b(z[0], z[31]);
        }
        if (e.a(context)) {
            if (a.b(context) == -1) {
                setLatestNotificationNumber(context, 5);
            }
            ServiceInterface.a(context);
        }
    }

    public static void initCrashHandler(Context context) {
        a(context);
        c.a().b(context);
    }

    public static boolean isPushStopped(Context context) {
        a(context);
        return ServiceInterface.d(context);
    }

    public static void onFragmentPause(Context context, String str) {
        a(context);
        b.b(context, str);
    }

    public static void onFragmentResume(Context context, String str) {
        a(context);
        b.a(context, str);
    }

    public static void onKillProcess(Context context) {
        b.c(context);
    }

    public static void onPause(Context context) {
        a(context);
        b.b(context);
    }

    public static void onResume(Context context) {
        a(context);
        b.a(context);
    }

    public static void removeLocalNotification(Context context, long j) {
        a(context);
        if (!e.n) {
            h.a(context).a(context, j);
            return;
        }
        Intent intent = new Intent(context, PushService.class);
        Bundle bundle = new Bundle();
        bundle.putInt(z[12], 7);
        bundle.putLong(z[50], j);
        intent.putExtras(bundle);
        intent.setAction(z[17]);
        context.startService(intent);
    }

    public static void reportNotificationOpened(Context context, String str) {
        a(context);
        if (ao.a(str)) {
            ac.e(z[0], z[24] + str);
        }
        k.a(str, GLMapStaticValue.AM_PARAMETERNAME_SCENIC_WIDGET_FILTER_INDEX, context);
    }

    public static void requestPermission(Context context) {
        if (Build.VERSION.SDK_INT < 23 || !(context instanceof Activity)) {
            ac.b(z[0], z[1]);
            return;
        }
        String[] strArr = {z[6], z[3], z[5]};
        String str = z[6];
        String str2 = z[3];
        String str3 = z[5];
        boolean c2 = b.c(context, str);
        boolean c3 = b.c(context, str2);
        boolean c4 = b.c(context, str3);
        if (!c2 || !c3 || !c4) {
            try {
                Class.forName(z[2]).getDeclaredMethod(z[4], String[].class, Integer.TYPE).invoke(context, strArr, 1);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void resumePush(Context context) {
        ac.b(z[0], z[48]);
        a(context);
        if (a.o()) {
            ac.b();
            ServiceInterface.a(context, false);
            return;
        }
        ServiceInterface.b(context, 1);
    }

    public static void setAlias(Context context, String str, TagAliasCallback tagAliasCallback) {
        a(context);
        setAliasAndTags(context, str, null, tagAliasCallback);
    }

    @Deprecated
    public static void setAliasAndTags(Context context, String str, Set<String> set) {
        a(context);
        a(context, str, set, null, false);
    }

    public static void setAliasAndTags(Context context, String str, Set<String> set, TagAliasCallback tagAliasCallback) {
        boolean z2;
        a(context);
        long currentTimeMillis = System.currentTimeMillis();
        if (c.size() < 10) {
            c.offer(Long.valueOf(currentTimeMillis));
            z2 = true;
        } else if (currentTimeMillis - c.element().longValue() > 10000) {
            while (c.size() >= 10) {
                c.poll();
            }
            c.offer(Long.valueOf(currentTimeMillis));
            z2 = true;
        } else {
            z2 = false;
        }
        if (!z2) {
            ac.d();
            if (tagAliasCallback != null) {
                tagAliasCallback.gotResult(e.k, str, set);
                return;
            }
            return;
        }
        a(context, str, set, tagAliasCallback, true);
    }

    public static void setDebugMode(boolean z2) {
        e.a = z2;
    }

    public static void setDefaultPushNotificationBuilder(BasicPushNotificationBuilder basicPushNotificationBuilder) {
        if (basicPushNotificationBuilder == null) {
            throw new IllegalArgumentException(z[20]);
        }
        ServiceInterface.a(e.e, a, basicPushNotificationBuilder);
    }

    public static void setLatestNotificationNumber(Context context, int i) {
        a(context);
        ac.b(z[0], z[45] + i);
        if (i <= 0) {
            ac.e(z[0], z[44]);
        } else {
            ServiceInterface.d(context, i);
        }
    }

    public static void setPushNotificationBuilder(Integer num, BasicPushNotificationBuilder basicPushNotificationBuilder) {
        ac.a(z[0], z[27] + num);
        if (basicPushNotificationBuilder == null) {
            throw new IllegalArgumentException(z[26]);
        } else if (num.intValue() <= 0) {
            ac.e(z[0], z[25]);
        } else {
            ServiceInterface.a(e.e, num, basicPushNotificationBuilder);
        }
    }

    public static void setPushTime(Context context, Set<Integer> set, int i, int i2) {
        a(context);
        if (e.a && !b.b(context)) {
            ac.b(z[0], z[31]);
        }
        if (set == null) {
            a(context, true, z[29]);
        } else if (set.size() == 0 || set.isEmpty()) {
            a(context, false, z[29]);
        } else if (i > i2) {
            ac.e(z[0], z[30]);
        } else {
            StringBuilder sb = new StringBuilder();
            for (Integer num : set) {
                if (num.intValue() > 6 || num.intValue() < 0) {
                    ac.e(z[0], z[28] + num);
                    return;
                }
                sb.append(num);
            }
            sb.append("_");
            sb.append(i);
            sb.append("^");
            sb.append(i2);
            a(context, true, sb.toString());
        }
    }

    public static void setSilenceTime(Context context, int i, int i2, int i3, int i4) {
        a(context);
        if (i < 0 || i2 < 0 || i3 < 0 || i4 < 0 || i2 > 59 || i4 > 59 || i3 > 23 || i > 23) {
            ac.e(z[0], z[56]);
        } else if (i == 0 && i2 == 0 && i3 == 0 && i4 == 0) {
            ServiceInterface.a(context, "");
            ac.b(z[0], z[57]);
        } else if (ServiceInterface.a(context, i, i2, i3, i4)) {
            ac.b(z[0], z[60] + i + z[59] + i2 + z[58] + i3 + z[59] + i4);
        } else {
            ac.e(z[0], z[55]);
        }
    }

    public static void setStatisticsEnable(boolean z2) {
        b.a(z2);
    }

    public static void setStatisticsSessionTimeout(long j) {
        if (j < 10) {
            ac.d(z[0], z[43]);
        } else if (j > TbsDownloadConfig.DEFAULT_RETRY_INTERVAL_SEC) {
            ac.d(z[0], z[42]);
        } else {
            b.a(j);
        }
    }

    public static void setTags(Context context, Set<String> set, TagAliasCallback tagAliasCallback) {
        a(context);
        setAliasAndTags(context, null, set, tagAliasCallback);
    }

    public static void stopCrashHandler(Context context) {
        a(context);
        c.a().c(context);
    }

    public static void stopPush(Context context) {
        ac.b(z[0], z[23]);
        a(context);
        if (a.o()) {
            ac.b();
            ServiceInterface.a(context, true);
            return;
        }
        ServiceInterface.a(context, 1);
    }
}
