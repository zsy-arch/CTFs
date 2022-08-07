package cn.jpush.android.util;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.pm.ServiceInfo;
import android.content.pm.Signature;
import android.graphics.Point;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Parcelable;
import android.os.PowerManager;
import android.provider.Settings;
import android.support.v4.view.PointerIconCompat;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.Display;
import android.webkit.WebSettings;
import cn.jpush.android.a;
import cn.jpush.android.api.InstrumentedActivity;
import cn.jpush.android.api.InstrumentedListActivity;
import cn.jpush.android.api.n;
import cn.jpush.android.data.c;
import cn.jpush.android.data.m;
import cn.jpush.android.e;
import cn.jpush.android.helpers.k;
import cn.jpush.android.service.AlarmReceiver;
import cn.jpush.android.service.DaemonService;
import cn.jpush.android.service.DownloadService;
import cn.jpush.android.service.PushReceiver;
import cn.jpush.android.service.PushService;
import cn.jpush.android.service.p;
import cn.jpush.android.service.q;
import cn.jpush.android.ui.PopWinActivity;
import cn.jpush.android.ui.PushActivity;
import com.alipay.sdk.util.h;
import com.hyphenate.util.HanziToPinyin;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.security.MessageDigest;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.UUID;
import java.util.regex.Pattern;
import javax.security.auth.x500.X500Principal;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public final class b {
    public static int a;
    private static List<String> b;
    private static final X500Principal c;
    private static long d;
    private static final ArrayList<String> e;
    private static final ArrayList<String> f;
    private static final ArrayList<String> g;
    private static PushReceiver h;
    private static final String[] z;

    /* JADX WARN: Code restructure failed: missing block: B:100:0x03c0, code lost:
        r3[r2] = r1;
        r2 = 'U';
        r1 = "\u001cl\u001c\t^\nqZMO\u0011f@\fG\u001b,s3~4Gk";
        r0 = 'T';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:101:0x03cb, code lost:
        r3[r2] = r1;
        r2 = 'V';
        r1 = "Zq\u001c\u0013K\ro[\u0010]\u0016m\\Md/Wa+q2Ga0o8G";
        r0 = 'U';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:102:0x03d6, code lost:
        r3[r2] = r1;
        r2 = 'W';
        r1 = "=w\\\u0007B\u001a\"A\u000bA\nnVC@\u0010v\u0012\u0001K_lG\u000fB_d]\u0011\u000e\fg\\\u0007l\rmS\u0007M\u001eqFM";
        r0 = 'V';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:103:0x03e1, code lost:
        r3[r2] = r1;
        r2 = 'X';
        r1 = "\u001elV\u0011A\u0016f\u001c\u0013K\ro[\u0010]\u0016m\\M|:Cv<k'Vw1`>Nm0z0Ps$k";
        r0 = 'W';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:104:0x03ec, code lost:
        r3[r2] = r1;
        r2 = 'Y';
        r1 = "\u001elV\u0011A\u0016f\u001c\n@\u000bg\\\u0017\u0000\u001eaF\nA\u0011,d*k(";
        r0 = 'X';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:105:0x03f7, code lost:
        r3[r2] = r1;
        r2 = 'Z';
        r1 = "\u001erB\u000fG\u001ccF\nA\u0011-D\rJQc\\\u0007\\\u0010kVM^\u001eaY\u0002I\u001a/S\u0011M\u0017kD\u0006";
        r0 = 'Y';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:106:0x0402, code lost:
        r3[r2] = r1;
        r2 = '[';
        r1 = "\u000fj]\rK";
        r0 = 'Z';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:107:0x040d, code lost:
        r3[r2] = r1;
        r2 = '\\';
        r1 = "\u001elV\u0011A\u0016f\u001c\u0013K\ro[\u0010]\u0016m\\M|:Cv<~7M|&q,Vs7k";
        r0 = '[';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:108:0x0418, code lost:
        r3[r2] = r1;
        r2 = ']';
        r1 = "\u0013vW";
        r0 = '\\';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:109:0x0423, code lost:
        r3[r2] = r1;
        r2 = '^';
        r1 = "\u001cf_\u0002";
        r0 = ']';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:10:0x0026, code lost:
        if (r5 != 0) goto L_0x002c;
     */
    /* JADX WARN: Code restructure failed: missing block: B:110:0x042e, code lost:
        r3[r2] = r1;
        r2 = '_';
        r1 = "\u0018q_";
        r0 = '^';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:111:0x0439, code lost:
        r3[r2] = r1;
        r2 = '`';
        r1 = "\u001cl\u001c\t^\nqZMO\u0011f@\fG\u001b,w;z-C";
        r0 = '_';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:112:0x0444, code lost:
        r3[r2] = r1;
        r2 = 'a';
        r1 = "\u001bgD\u0000G\u001a][\u0007q\u0018g\\\u0006\\\u001evW\u0007";
        r0 = '`';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:113:0x044f, code lost:
        r3[r2] = r1;
        r2 = 'b';
        r1 = "O(\u0002";
        r0 = 'a';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:114:0x045a, code lost:
        r3[r2] = r1;
        r2 = 'c';
        r1 = "\u001elV\u0011A\u0016f\u001c\n@\u000bg\\\u0017\u0000\u001ccF\u0006I\u0010pKMb>W| f:P";
        r0 = 'b';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:115:0x0465, code lost:
        r3[r2] = r1;
        r2 = 'd';
        r1 = "\u001elV\u0011A\u0016f\u001c\n@\u000bg\\\u0017\u0000\u001eaF\nA\u0011,\u007f\"g1";
        r0 = 'c';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:116:0x0470, code lost:
        r3[r2] = r1;
        r2 = 'e';
        r1 = ",g\\\u0007\u000e\u001dp]\u0002J\u001ccA\u0017\u000e\u000bm\u0012\u0002^\u000f8\u0012";
        r0 = 'd';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:117:0x047b, code lost:
        r3[r2] = r1;
        r2 = 'f';
        r1 = "\u001cl\u001c\t^\nqZMO\u0011f@\fG\u001b,q,`+G|7q+[b&";
        r0 = 'e';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:118:0x0486, code lost:
        r3[r2] = r1;
        r2 = 'g';
        r1 = "\u001cl\u001c\t^\nqZMO\u0011f@\fG\u001b,\u007f&},Cu&";
        r0 = 'f';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:119:0x0491, code lost:
        r3[r2] = r1;
        r2 = 'h';
        r1 = "\u001cl\u001c\t^\nqZMO\u0011f@\fG\u001b,\u007f0i Kv";
        r0 = 'g';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:11:0x0028, code lost:
        r5 = r1;
        r8 = r6;
        r6 = r5;
     */
    /* JADX WARN: Code restructure failed: missing block: B:120:0x049c, code lost:
        r3[r2] = r1;
        r2 = 'i';
        r1 = "\u001cl\u001c\t^\nqZMO\u0011f@\fG\u001b,t*b:]b\"z7";
        r0 = 'h';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:121:0x04a7, code lost:
        r3[r2] = r1;
        r2 = 'j';
        r1 = "\u001cl\u001c\t^\nqZMO\u0011f@\fG\u001b,f*z3G";
        r0 = 'i';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:122:0x04b2, code lost:
        r3[r2] = r1;
        r2 = 'k';
        r1 = "\u001cl\u001c\t^\nqZMO\u0011f@\fG\u001b,[\rZ\u001alFMc:Qa\"i:]`&m:Kd&j";
        r0 = 'j';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:123:0x04bd, code lost:
        r3[r2] = r1;
        r2 = 'l';
        r1 = "\u001elV\u0011A\u0016fm\nJ";
        r0 = 'k';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:124:0x04c8, code lost:
        r3[r2] = r1;
        r2 = 'm';
        r1 = "$c\u001f\u0005\u001eR;sNh\"y\u0003WS";
        r0 = 'l';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:125:0x04d3, code lost:
        r3[r2] = r1;
        r2 = 'n';
        r1 = "$2oI";
        r0 = 'm';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:126:0x04de, code lost:
        r3[r2] = r1;
        r2 = 'o';
        r1 = "$2\u001fZs\u00043\u0007\u001e";
        r0 = 'n';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:127:0x04e9, code lost:
        r3[r2] = r1;
        r2 = 'p';
        r1 = "\u001elV\u0011A\u0016f\u001c\n@\u000bg\\\u0017\u0000\u001eaF\nA\u0011,b\"m4Cu&q>Fv&j";
        r0 = 'o';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:128:0x04f4, code lost:
        r3[r2] = r1;
        r2 = 'q';
        r1 = "\u000fcQ\bO\u0018g";
        r0 = 'p';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:129:0x04ff, code lost:
        r3[r2] = r1;
        r2 = 'r';
        r1 = "\u001elV\u0011A\u0016f\u001c\rK\u000b,Q\f@\u0011,q,`1Gq7g)Kf:q<Js-i:";
        r0 = 'q';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:12:0x002c, code lost:
        r5 = r5;
        r1 = r1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:130:0x050a, code lost:
        r3[r2] = r1;
        r2 = 's';
        r1 = "\u001elV\u0011A\u0016f\u001c\n@\u000bg\\\u0017\u0000\u001eaF\nA\u0011,g0k-]b1k,G|7";
        r0 = 'r';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:131:0x0515, code lost:
        r3[r2] = r1;
        r2 = 't';
        r1 = "\u001elV\u0011A\u0016f\u001c\n@\u000bg\\\u0017\u0000\u001eaF\nA\u0011,b\"m4Cu&q-G\u007f,x:F";
        r0 = 's';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:132:0x0520, code lost:
        r3[r2] = r1;
        r2 = 'u';
        r1 = "-g^\u0006O\fgVCY\u001eiWCB\u0010aYC\u0003_o[\u000fB\u0016qW\u0000A\u0011fAY";
        r0 = 't';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:133:0x052b, code lost:
        r3[r2] = r1;
        r2 = 'v';
        r1 = "',\u0007S\u0017";
        r0 = 'u';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:134:0x0536, code lost:
        r3[r2] = r1;
        r2 = 'w';
        r1 = "2c[\r\u000e\u001cnS\u0010]_kAC";
        r0 = 'v';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:135:0x0541, code lost:
        r3[r2] = r1;
        r2 = 'x';
        r1 = " Hb\u0016]\u0017";
        r0 = 'w';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:136:0x054c, code lost:
        r3[r2] = r1;
        r2 = 'y';
        r1 = "\u000fmE\u0006\\";
        r0 = 'x';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:137:0x0557, code lost:
        r3[r2] = r1;
        r2 = 'z';
        r1 = "\u001enS\u0011C";
        r0 = 'y';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:138:0x0562, code lost:
        r3[r2] = r1;
        r2 = '{';
        r1 = "PfS\u0017OP";
        r0 = 'z';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:139:0x056d, code lost:
        r3[r2] = r1;
        r2 = '|';
        r1 = "\u000fp]\u0000K\fq\u0012\rO\u0012g\b";
        r0 = '{';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:13:0x002e, code lost:
        if (r5 > r6) goto L_0x0012;
     */
    /* JADX WARN: Code restructure failed: missing block: B:140:0x0578, code lost:
        r3[r2] = r1;
        r2 = '}';
        r1 = "\u001cc\\C@\u0010v\u0012\u0005G\u0011f\u0012";
        r0 = '|';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:141:0x0583, code lost:
        r3[r2] = r1;
        r2 = '~';
        r1 = "\u001elV\u0011A\u0016f\u001c\n@\u000bg\\\u0017\u0000\u001eaF\nA\u0011,p\"z+G`:q<Js-i:F";
        r0 = '}';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:142:0x058e, code lost:
        r3[r2] = r1;
        r2 = 127;
        r1 = "\fvS\u0017[\f";
        r0 = '~';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:143:0x0599, code lost:
        r3[r2] = r1;
        r2 = 128;
        r1 = "\u000fnG\u0004I\u001af";
        r0 = 127;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:144:0x05a4, code lost:
        r3[r2] = r1;
        r2 = 129;
        r1 = "S\"W\rJ2k\\Y";
        r0 = 128;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:145:0x05af, code lost:
        r3[r2] = r1;
        r2 = 130;
        r1 = "\u0011mE+A\np\b";
        r0 = 129;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:146:0x05ba, code lost:
        r3[r2] = r1;
        r2 = 131;
        r1 = "_|\u0012";
        r0 = 130;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:147:0x05c5, code lost:
        r3[r2] = r1;
        r2 = 132;
        r1 = "\u001alV+A\np";
        r0 = 131;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:148:0x05d0, code lost:
        r3[r2] = r1;
        r2 = 133;
        r1 = "S\"A\u0017O\rv\u007f\n@E";
        r0 = 132;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:149:0x05db, code lost:
        r3[r2] = r1;
        r2 = 134;
        r1 = "S\"A\u0017O\rvz\f[\r8";
        r0 = 133;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:14:0x0030, code lost:
        r1 = new java.lang.String(r1).intern();
     */
    /* JADX WARN: Code restructure failed: missing block: B:150:0x05e6, code lost:
        r3[r2] = r1;
        r2 = 135;
        r1 = "\fvS\u0011Z7mG\u0011";
        r0 = 134;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:151:0x05f1, code lost:
        r3[r2] = r1;
        r2 = 136;
        r1 = "S\"W\rJ7mG\u0011\u0014";
        r0 = 135;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:152:0x05fc, code lost:
        r3[r2] = r1;
        r2 = 137;
        r1 = "S\"\\\fY2k\\Y";
        r0 = 136;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:153:0x0607, code lost:
        r3[r2] = r1;
        r2 = 138;
        r1 = "\u001alV\u0017c\u0016lA";
        r0 = 137;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:154:0x0612, code lost:
        r3[r2] = r1;
        r2 = 139;
        r1 = "\fvS\u0011Z2k\\\u0010";
        r0 = 138;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:155:0x061d, code lost:
        r3[r2] = r1;
        r2 = 140;
        r1 = "<w@\u0011K\u0011v\u0012\u0017G\u0012g\u0012\n]_k\\CZ\u0017g\u0012\u0011O\u0011eWCA\u0019\"A\nB\u001alQ\u0006\u000e\u000bk_\u0006\u000eR\"";
        r0 = 139;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:156:0x0628, code lost:
        r3[r2] = r1;
        r2 = 141;
        r1 = "QrG\u0010F fW\u0015G\u001cg[\u0007";
        r0 = 140;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:157:0x0633, code lost:
        r3[r2] = r1;
        r2 = 142;
        r1 = "1W~/\u000e\u001cm\\\u0017K\u0007v";
        r0 = 141;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:158:0x063e, code lost:
        r3[r2] = r1;
        r2 = 143;
        r1 = "8kD\u0006\u000e\nr\u0012\u0017A_qF\u0002\\\u000b\"S\u0013^_d]\u0011\u000e\u0016lD\u0002B\u0016f\u0012\u0013O\rc_\u0010\u000eR\"B\u0002M\u0014cU\u0006`\u001eoWY";
        r0 = 142;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:159:0x0649, code lost:
        r3[r2] = r1;
        r2 = 144;
        r1 = "\nvTN\u0016";
        r0 = 143;
        r3 = r3;
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
            case 79: goto L_0x0394;
            case 80: goto L_0x039f;
            case 81: goto L_0x03aa;
            case 82: goto L_0x03b5;
            case 83: goto L_0x03c0;
            case 84: goto L_0x03cb;
            case 85: goto L_0x03d6;
            case 86: goto L_0x03e1;
            case 87: goto L_0x03ec;
            case 88: goto L_0x03f7;
            case 89: goto L_0x0402;
            case 90: goto L_0x040d;
            case 91: goto L_0x0418;
            case 92: goto L_0x0423;
            case 93: goto L_0x042e;
            case 94: goto L_0x0439;
            case 95: goto L_0x0444;
            case 96: goto L_0x044f;
            case 97: goto L_0x045a;
            case 98: goto L_0x0465;
            case 99: goto L_0x0470;
            case 100: goto L_0x047b;
            case 101: goto L_0x0486;
            case 102: goto L_0x0491;
            case 103: goto L_0x049c;
            case 104: goto L_0x04a7;
            case 105: goto L_0x04b2;
            case 106: goto L_0x04bd;
            case 107: goto L_0x04c8;
            case 108: goto L_0x04d3;
            case 109: goto L_0x04de;
            case 110: goto L_0x04e9;
            case 111: goto L_0x04f4;
            case 112: goto L_0x04ff;
            case 113: goto L_0x050a;
            case 114: goto L_0x0515;
            case 115: goto L_0x0520;
            case 116: goto L_0x052b;
            case 117: goto L_0x0536;
            case 118: goto L_0x0541;
            case 119: goto L_0x054c;
            case 120: goto L_0x0557;
            case 121: goto L_0x0562;
            case 122: goto L_0x056d;
            case 123: goto L_0x0578;
            case 124: goto L_0x0583;
            case 125: goto L_0x058e;
            case 126: goto L_0x0599;
            case 127: goto L_0x05a4;
            case 128: goto L_0x05af;
            case 129: goto L_0x05ba;
            case 130: goto L_0x05c5;
            case 131: goto L_0x05d0;
            case 132: goto L_0x05db;
            case 133: goto L_0x05e6;
            case 134: goto L_0x05f1;
            case 135: goto L_0x05fc;
            case 136: goto L_0x0607;
            case 137: goto L_0x0612;
            case 138: goto L_0x061d;
            case 139: goto L_0x0628;
            case 140: goto L_0x0633;
            case 141: goto L_0x063e;
            case 142: goto L_0x0649;
            case 143: goto L_0x0654;
            case 144: goto L_0x065f;
            case 145: goto L_0x066a;
            case 146: goto L_0x0675;
            case 147: goto L_0x0680;
            case 148: goto L_0x068b;
            case 149: goto L_0x0696;
            case 150: goto L_0x06a1;
            case 151: goto L_0x06ac;
            case 152: goto L_0x06b7;
            case 153: goto L_0x06c2;
            case 154: goto L_0x06cd;
            case 155: goto L_0x06d8;
            case 156: goto L_0x06e3;
            case 157: goto L_0x06ee;
            case 158: goto L_0x06f9;
            case 159: goto L_0x0704;
            case 160: goto L_0x070f;
            case 161: goto L_0x071a;
            case 162: goto L_0x0725;
            case 163: goto L_0x0730;
            case 164: goto L_0x073b;
            case 165: goto L_0x0746;
            case 166: goto L_0x0751;
            case 167: goto L_0x075c;
            case 168: goto L_0x0767;
            case 169: goto L_0x0772;
            case 170: goto L_0x077d;
            case 171: goto L_0x0788;
            case 172: goto L_0x0793;
            case 173: goto L_0x079e;
            case 174: goto L_0x07a9;
            case 175: goto L_0x07b5;
            case 176: goto L_0x07c0;
            case 177: goto L_0x07cb;
            case 178: goto L_0x07d6;
            case 179: goto L_0x07e1;
            case 180: goto L_0x07ec;
            case 181: goto L_0x07f8;
            case 182: goto L_0x0803;
            case 183: goto L_0x080e;
            default: goto L_0x003c;
        };
     */
    /* JADX WARN: Code restructure failed: missing block: B:160:0x0654, code lost:
        r3[r2] = r1;
        r2 = 145;
        r1 = "\u0012mG\rZ\u001af";
        r0 = 144;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:161:0x065f, code lost:
        r3[r2] = r1;
        r2 = 146;
        r1 = "\u001bwB\u000fG\u001ccF\u0006";
        r0 = 145;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:162:0x066a, code lost:
        r3[r2] = r1;
        r2 = 147;
        r1 = "\u001cm_MO\u0011f@\fG\u001b,^\u0002[\u0011aZ\u0006\\QcQ\u0017G\u0010l\u001c*`,Vs/b Qz,|+Ag7";
        r0 = 146;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:163:0x0675, code lost:
        r3[r2] = r1;
        r2 = 148;
        r1 = "\u001elV\u0011A\u0016f\u001c\n@\u000bg\\\u0017\u0000\u001azF\u0011OQqZ\f\\\u000baG\u0017\u00001C\u007f&";
        r0 = 147;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:164:0x0680, code lost:
        r3[r2] = r1;
        r2 = 149;
        r1 = "\u001elV\u0011A\u0016f\u001c\n@\u000bg\\\u0017\u0000\u001azF\u0011OQqZ\f\\\u000baG\u0017\u00006A}-q-Ga,{-Aw";
        r0 = 148;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:165:0x068b, code lost:
        r3[r2] = r1;
        r2 = 150;
        r1 = "\u001elV\u0011A\u0016f\u001c\n@\u000bg\\\u0017\u0000\u001azF\u0011OQqZ\f\\\u000baG\u0017\u00006Lf&`+";
        r0 = 149;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:166:0x0696, code lost:
        r3[r2] = r1;
        r2 = 151;
        r1 = "*lW\u001b^\u001aaF\u0006JE\"[\rX\u001en[\u0007\u000e\np^C\u0003_";
        r0 = 150;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:167:0x06a1, code lost:
        r3[r2] = r1;
        r2 = 152;
        r1 = "8mFC]\u001baS\u0011J_d[\u000fK_qS\u0015K\u001b\"G\u0007G\u001b\"\u001fC";
        r0 = 151;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:168:0x06ac, code lost:
        r3[r2] = r1;
        r2 = 153;
        r1 = "QrG\u0010F wV\nJ";
        r0 = 152;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:169:0x06b7, code lost:
        r3[r2] = r1;
        r2 = 154;
        r1 = "'kS\fC\u0016";
        r0 = 153;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x003c, code lost:
        r3[r2] = r1;
        r2 = 1;
        r1 = "\u001elV\u0011A\u0016f\u0012\u000eO\u001c\"S\u0007J\rgA\u0010\u0014";
        r0 = 0;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:170:0x06c2, code lost:
        r3[r2] = r1;
        r2 = 155;
        r1 = ")5\u001cR";
        r0 = 154;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:171:0x06cd, code lost:
        r3[r2] = r1;
        r2 = 156;
        r1 = "\rm\u001c\u0013\\\u0010fG\u0000ZQ`@\u0002@\u001b";
        r0 = 155;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:172:0x06d8, code lost:
        r3[r2] = r1;
        r2 = 157;
        r1 = "\u001dpS\rJ_?\u0012";
        r0 = 156;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:173:0x06e3, code lost:
        r3[r2] = r1;
        r2 = 158;
        r1 = "\rm\u001c\u000eG\nk\u001c\u0016GQtW\u0011]\u0016m\\M@\u001eoW";
        r0 = 157;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:174:0x06ee, code lost:
        r3[r2] = r1;
        r2 = 159;
        r1 = "\rm\u001c\u0001[\u0016nVMX\u001apA\nA\u0011,[\rM\rg_\u0006@\u000bc^";
        r0 = 158;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:175:0x06f9, code lost:
        r3[r2] = r1;
        r2 = 160;
        r1 = "#\\";
        r0 = 159;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:176:0x0704, code lost:
        r3[r2] = r1;
        r2 = 161;
        r1 = "<w@\u0011K\u0011v\u0012\u0017G\u0012g\u0012\n]_mG\u0017\u000e\u0010d\u0012\u0017F\u001a\"B\u0016]\u0017\"F\nC\u001a\"\u001fC";
        r0 = 160;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:177:0x070f, code lost:
        r3[r2] = r1;
        r2 = 162;
        r1 = "1mF\nH\u0016aS\u0017G\u0010l\u0012\u0014O\f\"V\n]\u001e`^\u0006J_`KCd/wA\u000bg\u0011vW\u0011H\u001eaWM]\u001avb\u0016]\u0017V[\u000eK_#";
        r0 = 161;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:178:0x071a, code lost:
        r3[r2] = r1;
        r2 = 163;
        r1 = "\u000fwA\u000b\u000e\u000bk_\u0006\u000e\u0016q\u0012ｹ";
        r0 = 162;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:179:0x0725, code lost:
        r3[r2] = r1;
        r2 = 164;
        r1 = "*VtN\u0016";
        r0 = 163;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x0044, code lost:
        r3[r2] = r1;
        r2 = 2;
        r1 = "\u001elV\u0011A\u0016f\u001c\u0013K\ro[\u0010]\u0016m\\Mo<Aw0} U{%g Qf\"z:";
        r0 = 1;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:180:0x0730, code lost:
        r3[r2] = r1;
        r2 = 165;
        r1 = "Pr@\fMPaB\u0016G\u0011d]";
        r0 = 164;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:181:0x073b, code lost:
        r3[r2] = r1;
        r2 = 166;
        r1 = "/p]\u0000K\fq]\u0011";
        r0 = 165;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:182:0x0746, code lost:
        r3[r2] = r1;
        r2 = 167;
        r1 = "\u0012q\u001c";
        r0 = 166;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:183:0x0751, code lost:
        r3[r2] = r1;
        r2 = 168;
        r1 = "7gS\u0011Z\u001dgS\u0017\u000e\u0016lF\u0006\\\tc^C\u0013_";
        r0 = 167;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:184:0x075c, code lost:
        r3[r2] = r1;
        r2 = 169;
        r1 = "\u0018gFCJ\u001at[\u0000K_kVC\u000e\ff\u0012\u0000O\rf\u0012\u0005G\u0013g\u0012\u0013O\u000bj\u0012\u0005O\u0016n";
        r0 = 168;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:185:0x0767, code lost:
        r3[r2] = r1;
        r2 = 170;
        r1 = "8mFC]\u001baS\u0011J_d[\u000fK_qS\u0015K\u001b\"V\u0006X\u0016aW*J_/\u0012";
        r0 = 169;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:186:0x0772, code lost:
        r3[r2] = r1;
        r2 = 171;
        r1 = "\u0016qg\u0013J\u001evW5K\rq[\f@";
        r0 = 170;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:187:0x077d, code lost:
        r3[r2] = r1;
        r2 = 172;
        r1 = "\u0019c[\u000fK\u001b\"F\f\u000e\u0018gFCO\u000fr^\nM\u001ev[\f@_k\\\u0005A_c\\\u0007\u000e\u0016a]\r\u0000";
        r0 = 171;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:188:0x0788, code lost:
        r3[r2] = r1;
        r2 = 173;
        r1 = "\u001eaF\nX\u0016vK";
        r0 = 172;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:189:0x0793, code lost:
        r3[r2] = r1;
        r2 = 174;
        r1 = "5RG\u0010F掯礸Ｈ卦吣哳CB\u0013e\u001a{丿博酣";
        r0 = 173;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x004c, code lost:
        r3[r2] = r1;
        r2 = 3;
        r1 = "\u001ccFC\u0001\f{ALM\u0013cA\u0010\u0001\u0011gFLY\u0013c\\S\u0001\u001efV\u0011K\fq";
        r0 = 2;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:190:0x079e, code lost:
        r3[r2] = r1;
        r2 = 175;
        r1 = "\u001bpS\u0014O\u001dnW";
        r0 = 174;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:191:0x07a9, code lost:
        r3[r2] = r1;
        r2 = 176;
        r1 = "讈刲\u00123A\rvS\u000f\u000e乵莵古惋皪卺吏咾\"^\u000fIW\u001a幘暋斲s\rJ\rm[\u0007c\u001el[\u0005K\fv益廷孹毊";
        r0 = 175;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:192:0x07b5, code lost:
        r3[r2] = r1;
        r2 = 177;
        r1 = "\u0011mF\nH\u0016aS\u0017G\u0010l";
        r0 = 176;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:193:0x07c0, code lost:
        r3[r2] = r1;
        r2 = 178;
        r1 = "5RG\u0010F掯礸Ｈ罙尿纠讣仑硢";
        r0 = 177;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:194:0x07cb, code lost:
        r3[r2] = r1;
        r2 = 179;
        r1 = "\u001cl\u001c\t^\nqZMO\u0011f@\fG\u001b,[\rZ\u001alFM`0V{%g<Cf*a1]}3k1Gv<~-Mj:";
        r0 = 178;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:195:0x07d6, code lost:
        r3[r2] = r1;
        r2 = 180;
        r1 = "\u001bgP\u0016I l]\u0017G\u0019kQ\u0002Z\u0016m\\";
        r0 = 179;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:196:0x07e1, code lost:
        r3[r2] = r1;
        r2 = 181;
        r1 = "\u0015rG\u0010F l]\u0017G\u0019kQ\u0002Z\u0016m\\<G\u001cm\\";
        r0 = 180;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:197:0x07ec, code lost:
        r3[r2] = r1;
        r2 = 182;
        r1 = "梿浉刂隥戾_Qv(\u000e杕淹劒纼讏亜砃〰烚凕枚眉诔悦";
        r0 = 181;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:198:0x07f8, code lost:
        r3[r2] = r1;
        r2 = 183;
        r1 = "\u000bmS\u0010Z+gJ\u0017";
        r0 = 182;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:199:0x0803, code lost:
        r3[r2] = r1;
        r2 = 184;
        r1 = "<L\u000f\"@\u001bp]\nJ_FW\u0001[\u0018.}^o\u0011f@\fG\u001b.q^{,";
        r0 = 183;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:19:0x0054, code lost:
        r3[r2] = r1;
        r2 = 4;
        r1 = "\bkT\n";
        r0 = 3;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:200:0x080e, code lost:
        r3[r2] = r1;
        cn.jpush.android.util.b.z = r3;
        cn.jpush.android.util.b.a = 1;
        r2 = new java.util.ArrayList<>();
        cn.jpush.android.util.b.b = r2;
        r1 = "L7\nU\u0019L2\u0003P\u0019F7\nZ\u001b";
        r0 = 65535;
     */
    /* JADX WARN: Code restructure failed: missing block: B:201:0x081f, code lost:
        r1 = r1.toCharArray();
        r3 = r1.length;
        r4 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:202:0x0826, code lost:
        if (r3 > 1) goto L_0x085e;
     */
    /* JADX WARN: Code restructure failed: missing block: B:203:0x0828, code lost:
        r6 = r4;
        r3 = r1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:204:0x082d, code lost:
        r8 = r3[r4];
     */
    /* JADX WARN: Code restructure failed: missing block: B:205:0x0831, code lost:
        switch((r6 % 5)) {
            case 0: goto L_0x0851;
            case 1: goto L_0x0854;
            case 2: goto L_0x0856;
            case 3: goto L_0x0859;
            default: goto L_0x0834;
        };
     */
    /* JADX WARN: Code restructure failed: missing block: B:206:0x0834, code lost:
        r7 = '.';
     */
    /* JADX WARN: Code restructure failed: missing block: B:207:0x0836, code lost:
        r3[r4] = (char) (r7 ^ r8);
        r4 = r6 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:208:0x083c, code lost:
        if (r3 != 0) goto L_0x085c;
     */
    /* JADX WARN: Code restructure failed: missing block: B:209:0x083e, code lost:
        r3 = r1;
        r6 = r4;
        r4 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x005c, code lost:
        r3[r2] = r1;
        r2 = 5;
        r1 = "\u001cl\u001c\t^\nqZMO\u0011f@\fG\u001b,[\rZ\u001alFMj\u001eg_\f@,g@\u0015G\u001cg";
        r0 = 4;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:210:0x0842, code lost:
        r9 = 127;
     */
    /* JADX WARN: Code restructure failed: missing block: B:211:0x0846, code lost:
        r9 = 2;
     */
    /* JADX WARN: Code restructure failed: missing block: B:212:0x0849, code lost:
        r9 = '2';
     */
    /* JADX WARN: Code restructure failed: missing block: B:213:0x084d, code lost:
        r9 = 'c';
     */
    /* JADX WARN: Code restructure failed: missing block: B:214:0x0851, code lost:
        r7 = 127;
     */
    /* JADX WARN: Code restructure failed: missing block: B:215:0x0854, code lost:
        r7 = 2;
     */
    /* JADX WARN: Code restructure failed: missing block: B:216:0x0856, code lost:
        r7 = '2';
     */
    /* JADX WARN: Code restructure failed: missing block: B:217:0x0859, code lost:
        r7 = 'c';
     */
    /* JADX WARN: Code restructure failed: missing block: B:218:0x085c, code lost:
        r3 = r3;
        r1 = r1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:219:0x085e, code lost:
        if (r3 > r4) goto L_0x0828;
     */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x0064, code lost:
        r3[r2] = r1;
        r2 = 6;
        r1 = "+m\u0012\u0010Z\u001epFCm\u0010oB\f@\u001alFY\u000e";
        r0 = 5;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:220:0x0860, code lost:
        r1 = new java.lang.String(r1).intern();
     */
    /* JADX WARN: Code restructure failed: missing block: B:221:0x0869, code lost:
        switch(r0) {
            case 0: goto L_0x08ed;
            case 1: goto L_0x08f7;
            case 2: goto L_0x0901;
            case 3: goto L_0x090b;
            case 4: goto L_0x0915;
            case 5: goto L_0x0924;
            case 6: goto L_0x092e;
            case 7: goto L_0x0938;
            default: goto L_0x086c;
        };
     */
    /* JADX WARN: Code restructure failed: missing block: B:222:0x086c, code lost:
        r2.add(r1);
        r2 = cn.jpush.android.util.b.b;
        r1 = "O2\u0006Z\u0017F2\u0003S\u0018K2\u0002S\u001e";
        r0 = 65535;
     */
    /* JADX WARN: Code restructure failed: missing block: B:223:0x0874, code lost:
        r1 = r1.toCharArray();
        r3 = r1.length;
        r4 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:224:0x087b, code lost:
        if (r3 > 1) goto L_0x08a4;
     */
    /* JADX WARN: Code restructure failed: missing block: B:225:0x087d, code lost:
        r6 = r4;
        r3 = r1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:226:0x0882, code lost:
        r8 = r3[r4];
     */
    /* JADX WARN: Code restructure failed: missing block: B:227:0x0886, code lost:
        switch((r6 % 5)) {
            case 0: goto L_0x0897;
            case 1: goto L_0x089a;
            case 2: goto L_0x089c;
            case 3: goto L_0x089f;
            default: goto L_0x0889;
        };
     */
    /* JADX WARN: Code restructure failed: missing block: B:228:0x0889, code lost:
        r7 = '.';
     */
    /* JADX WARN: Code restructure failed: missing block: B:229:0x088b, code lost:
        r3[r4] = (char) (r7 ^ r8);
        r4 = r6 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x006c, code lost:
        r3[r2] = r1;
        r2 = 7;
        r1 = "#,";
        r0 = 6;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:230:0x0891, code lost:
        if (r3 != 0) goto L_0x08a2;
     */
    /* JADX WARN: Code restructure failed: missing block: B:231:0x0893, code lost:
        r3 = r1;
        r6 = r4;
        r4 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:232:0x0897, code lost:
        r7 = 127;
     */
    /* JADX WARN: Code restructure failed: missing block: B:233:0x089a, code lost:
        r7 = 2;
     */
    /* JADX WARN: Code restructure failed: missing block: B:234:0x089c, code lost:
        r7 = '2';
     */
    /* JADX WARN: Code restructure failed: missing block: B:235:0x089f, code lost:
        r7 = 'c';
     */
    /* JADX WARN: Code restructure failed: missing block: B:236:0x08a2, code lost:
        r3 = r3;
        r1 = r1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:237:0x08a4, code lost:
        if (r3 > r4) goto L_0x087d;
     */
    /* JADX WARN: Code restructure failed: missing block: B:238:0x08a6, code lost:
        r1 = new java.lang.String(r1).intern();
     */
    /* JADX WARN: Code restructure failed: missing block: B:239:0x08af, code lost:
        switch(r0) {
            case 0: goto L_0x08bb;
            case 1: goto L_0x08c4;
            default: goto L_0x08b2;
        };
     */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x0074, code lost:
        r3[r2] = r1;
        r2 = '\b';
        r1 = "F5\u0005WJJ4VU\u0016Mg\u0007W\u0017\u001c";
        r0 = 7;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:240:0x08b2, code lost:
        r2.add(r1);
        r2 = cn.jpush.android.util.b.b;
        r1 = "O2\u0002S\u001eO2\u0002S\u001eO2\u0002S";
        r0 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:241:0x08bb, code lost:
        r2.add(r1);
        r2 = cn.jpush.android.util.b.b;
        r1 = "O2\u0002S\u001eO2\u0002S\u001eO2\u0002S\u001e";
        r0 = 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:242:0x08c4, code lost:
        r2.add(r1);
        cn.jpush.android.util.b.c = new javax.security.auth.x500.X500Principal(cn.jpush.android.util.b.z[184(0xb8, float:2.58E-43)]);
        cn.jpush.android.util.b.d = 0;
        cn.jpush.android.util.b.e = new java.util.ArrayList<>();
        cn.jpush.android.util.b.f = new java.util.ArrayList<>();
        r2 = cn.jpush.android.util.b.e;
        r1 = "\u001elV\u0011A\u0016f\u001c\u0013K\ro[\u0010]\u0016m\\Mg1Vw1`:V";
        r0 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:243:0x08ed, code lost:
        r2.add(r1);
        r2 = cn.jpush.android.util.b.e;
        r1 = "\u001elV\u0011A\u0016f\u001c\u0013K\ro[\u0010]\u0016m\\My>Iw<b0Ay";
        r0 = 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:244:0x08f7, code lost:
        r2.add(r1);
        r2 = cn.jpush.android.util.b.e;
        r1 = "\u001elV\u0011A\u0016f\u001c\u0013K\ro[\u0010]\u0016m\\Mo<Aw0} Lw7y0Py<}+Cf&";
        r0 = 2;
     */
    /* JADX WARN: Code restructure failed: missing block: B:245:0x0901, code lost:
        r2.add(r1);
        r2 = cn.jpush.android.util.b.f;
        r1 = "\u001elV\u0011A\u0016f\u001c\u0013K\ro[\u0010]\u0016m\\Mx6@`\"z:";
        r0 = 3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:246:0x090b, code lost:
        r2.add(r1);
        r2 = cn.jpush.android.util.b.f;
        r1 = "\u001elV\u0011A\u0016f\u001c\u0013K\ro[\u0010]\u0016m\\Mm7C|$k U{%g Qf\"z:";
        r0 = 4;
     */
    /* JADX WARN: Code restructure failed: missing block: B:247:0x0915, code lost:
        r2.add(r1);
        r2 = new java.util.ArrayList<>();
        cn.jpush.android.util.b.g = r2;
        r1 = "\u001elV\u0011A\u0016f\u001c\u0013K\ro[\u0010]\u0016m\\Mo<Aw0} D{-k N} o+K}-";
        r0 = 5;
     */
    /* JADX WARN: Code restructure failed: missing block: B:248:0x0924, code lost:
        r2.add(r1);
        r2 = cn.jpush.android.util.b.g;
        r1 = "\u001elV\u0011A\u0016f\u001c\u0013K\ro[\u0010]\u0016m\\Mo<Aw0} A}\"|,Gm/a<Cf*a1";
        r0 = 6;
     */
    /* JADX WARN: Code restructure failed: missing block: B:249:0x092e, code lost:
        r2.add(r1);
        r2 = cn.jpush.android.util.b.g;
        r1 = "\u001elV\u0011A\u0016f\u001c\u0013K\ro[\u0010]\u0016m\\Mo<Aw0} N} o+K}-q:Zf1o A}.c>Lv0";
        r0 = 7;
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x007d, code lost:
        r3[r2] = r1;
        r2 = '\t';
        r1 = "\u001cm\\\rK\u001cv[\u0015G\u000b{";
        r0 = '\b';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:250:0x0938, code lost:
        r2.add(r1);
        cn.jpush.android.util.b.g.add(cn.jpush.android.util.b.z[2]);
     */
    /* JADX WARN: Code restructure failed: missing block: B:251:0x0945, code lost:
        return;
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x0087, code lost:
        r3[r2] = r1;
        r2 = '\n';
        r1 = "5Rg0f Cb3e:[";
        r0 = '\t';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x0092, code lost:
        r3[r2] = r1;
        r2 = 11;
        r1 = "\u001aoB\u0017W_rS\u0011O\u0012q";
        r0 = '\n';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x009d, code lost:
        r3[r2] = r1;
        r2 = '\f';
        r1 = "\tg@<M\u0010fW";
        r0 = 11;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x00a8, code lost:
        r3[r2] = r1;
        r2 = '\r';
        r1 = "\u0011c_\u0006";
        r0 = '\f';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:29:0x00b3, code lost:
        r3[r2] = r1;
        r2 = 14;
        r1 = "\tg@<@\u001eoW";
        r0 = '\r';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x00be, code lost:
        r3[r2] = r1;
        r2 = 15;
        r1 = "\u000fiU";
        r0 = 14;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:31:0x00c9, code lost:
        r3[r2] = r1;
        r2 = 16;
        r1 = "Q\"S\u0013^\u0014gKY";
        r0 = 15;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x00d4, code lost:
        r3[r2] = r1;
        r2 = 17;
        r1 = "Q\"b\u0016]\u0017QW\u0011X\u0016aWY";
        r0 = 16;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x00df, code lost:
        r3[r2] = r1;
        r2 = 18;
        r1 = "$aZ\u0006M\u0014UZ\u0006Z\u0017g@7A,vS\u0011Z\"\"\u001fC^\u001ap_\n]\fk]\r\u0014";
        r0 = 17;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:34:0x00ea, code lost:
        r3[r2] = r1;
        r2 = 19;
        r1 = "\u001cl\u001c\t^\nqZMO\u0011f@\fG\u001b,A\u0006\\\tkQ\u0006\u0000/wA\u000b}\u001apD\nM\u001a";
        r0 = 18;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:35:0x00f5, code lost:
        r3[r2] = r1;
        r2 = com.tencent.open.GameAppOperation.PIC_SYMBOLE;
        r1 = "\u0011w^\u000f";
        r0 = 19;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:36:0x0100, code lost:
        r3[r2] = r1;
        r2 = 21;
        r1 = "<c\\C@\u0010v\u0012\u0004K\u000b\"S\u0013^\u0013kQ\u0002Z\u0016m\\CG\u0011d]CY\u0016vZC^\u001eaY\u0002I\u001a\"\\\u0002C\u001a\"\bC";
        r0 = com.tencent.open.GameAppOperation.PIC_SYMBOLE;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:37:0x010b, code lost:
        r3[r2] = r1;
        r2 = 22;
        r1 = "QrW\u0011C\u0016qA\nA\u0011,x3{,Jm.k,Qs$k";
        r0 = 21;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:38:0x0116, code lost:
        r3[r2] = r1;
        r2 = 23;
        r1 = "2CqCO\u001bf@CG\u0011d]N\u0003R/\u0012";
        r0 = 22;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:39:0x0121, code lost:
        r3[r2] = r1;
        r2 = 24;
        r1 = "*lY\rA\bl";
        r0 = 23;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:40:0x012c, code lost:
        r3[r2] = r1;
        r2 = 25;
        r1 = "\u001dmV\u001a";
        r0 = 24;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:41:0x0137, code lost:
        r3[r2] = r1;
        r2 = 26;
        r1 = "\u0015q]\rk\u0007aW\u0013Z\u0016m\\C\u0003_";
        r0 = 25;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:42:0x0142, code lost:
        r3[r2] = r1;
        r2 = 27;
        r1 = "\u001cl\u001c\t^\nqZMG\u0012,S\rJ\rm[\u0007\u0000\u001eaF\nA\u0011,{.q-Ga3a1Qw";
        r0 = 26;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:43:0x014d, code lost:
        r3[r2] = r1;
        r2 = 28;
        r1 = ">lV\u0011A\u0016fg\u0017G\u0013";
        r0 = 27;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:44:0x0158, code lost:
        r3[r2] = r1;
        r2 = 29;
        r1 = "\u000fwA\u000bq\u0011gF\u0014A\rim\u0000A\u0011lW\u0000Z\u001af";
        r0 = 28;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:45:0x0163, code lost:
        r3[r2] = r1;
        r2 = 30;
        r1 = ">aF\nA\u0011\"\u001fC]\u001alV-K\u000bu]\u0011E<jS\rI\u001aff\fg2";
        r0 = 29;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:46:0x016e, code lost:
        r3[r2] = r1;
        r2 = 31;
        r1 = "\u000fwA\u000bq\u000bmm\nC fS\u0017O";
        r0 = 30;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:47:0x0179, code lost:
        r3[r2] = r1;
        r2 = ' ';
        r1 = "\u0012mV\u0006B";
        r0 = 31;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:48:0x0184, code lost:
        r3[r2] = r1;
        r2 = '!';
        r1 = "\u001bgD\nM\u001a";
        r0 = ' ';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:49:0x018f, code lost:
        r3[r2] = r1;
        r2 = '\"';
        r1 = "\u001dcA\u0006L\u001elV";
        r0 = '!';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:4:0x0010, code lost:
        if (r5 <= 1) goto L_0x0012;
     */
    /* JADX WARN: Code restructure failed: missing block: B:50:0x019a, code lost:
        r3[r2] = r1;
        r2 = '#';
        r1 = "\u001cjS\r@\u001an";
        r0 = '\"';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:51:0x01a5, code lost:
        r3[r2] = r1;
        r2 = '$';
        r1 = "\u001elV\u0011A\u0016fa\u0007E)g@\u0010G\u0010l";
        r0 = '#';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:52:0x01b0, code lost:
        r3[r2] = r1;
        r2 = '%';
        r1 = "\u0018q_MX\u001apA\nA\u0011,P\u0002]\u001a`S\rJ";
        r0 = '$';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:53:0x01bb, code lost:
        r3[r2] = r1;
        r2 = '&';
        r1 = "\u0011gF\u0014A\ri";
        r0 = '%';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:54:0x01c6, code lost:
        r3[r2] = r1;
        r2 = '\'';
        r1 = "\np^";
        r0 = '&';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:55:0x01d1, code lost:
        r3[r2] = r1;
        r2 = '(';
        r1 = "\u000b{B\u0006";
        r0 = '\'';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:56:0x01dc, code lost:
        r3[r2] = r1;
        r2 = ')';
        r1 = "\u0016v[\u000eK";
        r0 = '(';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:57:0x01e7, code lost:
        r3[r2] = r1;
        r2 = '*';
        r1 = "\u001bcF\u0002";
        r0 = ')';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:58:0x01f2, code lost:
        r3[r2] = r1;
        r2 = '+';
        r1 = "\u001bo\u001c\u0014G\u001bvZ3G\u0007g^\u0010\u000eE\"";
        r0 = '*';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:59:0x01fd, code lost:
        r3[r2] = r1;
        r2 = ',';
        r1 = "_f_MW\u001br[Y";
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
        r1 = "\u000fm[\rZQ{";
        r0 = ',';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:61:0x0213, code lost:
        r3[r2] = r1;
        r2 = '.';
        r1 = ",a@\u0006K\u0011\"[\rM\u0017gAC\u0014_";
        r0 = '-';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:62:0x021e, code lost:
        r3[r2] = r1;
        r2 = '/';
        r1 = "\u001bo\u001c\u001bJ\u000fk\u0012Y\u000e";
        r0 = '.';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:63:0x0229, code lost:
        r3[r2] = r1;
        r2 = '0';
        r1 = "\u000fm[\rZQz\b";
        r0 = '/';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:64:0x0234, code lost:
        r3[r2] = r1;
        r2 = '1';
        r1 = "_f_MF\u001akU\u000bZ/kJ\u0006B\f8";
        r0 = '0';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:65:0x023f, code lost:
        r3[r2] = r1;
        r2 = '2';
        r1 = "\nlY\rA\bl";
        r0 = '1';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:66:0x024a, code lost:
        r3[r2] = r1;
        r2 = '3';
        r1 = "Le";
        r0 = '2';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:67:0x0255, code lost:
        r3[r2] = r1;
        r2 = '4';
        r1 = "Ke";
        r0 = '3';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:68:0x0260, code lost:
        r3[r2] = r1;
        r2 = '5';
        r1 = "Me";
        r0 = '4';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:69:0x026b, code lost:
        r3[r2] = r1;
        r2 = '6';
        r1 = "/pW\u0005]9k^\u0006";
        r0 = '5';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:6:0x0017, code lost:
        r10 = r5[r6];
     */
    /* JADX WARN: Code restructure failed: missing block: B:70:0x0276, code lost:
        r3[r2] = r1;
        r2 = '7';
        r1 = "\u0014gK";
        r0 = '6';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:71:0x0281, code lost:
        r3[r2] = r1;
        r2 = '8';
        r1 = "<w@\u0011K\u0011v\u0012\u0013E_k\\\u0010Z\u001en^\u0006J_rS\u0017FE\"";
        r0 = '7';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:72:0x028c, code lost:
        r3[r2] = r1;
        r2 = '9';
        r1 = "PfS\u0017OPcB\u0013\u0001";
        r0 = '8';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:73:0x0297, code lost:
        r3[r2] = r1;
        r2 = ':';
        r1 = "PqK\u0010Z\u001ao\u001d\u0002^\u000f-";
        r0 = '9';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:74:0x02a2, code lost:
        r3[r2] = r1;
        r2 = ';';
        r1 = "[&";
        r0 = ':';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:75:0x02ad, code lost:
        r3[r2] = r1;
        r2 = '<';
        r1 = "S\"]\u0017F\u001apE\n]\u001a\"K\f[_aS\r\u000e\u0011mFCB\u0010aS\u0017K_vZ\u0006\u000e\u001bgD\nM\u001aq\u001c";
        r0 = ';';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:76:0x02b8, code lost:
        r3[r2] = r1;
        r2 = '=';
        r1 = "\u001elV\u0011A\u0016f\u001c\u0013K\ro[\u0010]\u0016m\\My-Kf&q:Zf&|1C~<}+M`\"i:";
        r0 = '<';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:77:0x02c3, code lost:
        r3[r2] = r1;
        r2 = '>';
        r1 = "/wA\u000b|\u001aaW\nX\u001ap\u0012\u0010F\u0010w^\u0007\u000e\u0011mFCF\u001etWCG\u0011vW\rZ_d[\u000fZ\u001ap\u0012N\u0003_c\\\u0007\\\u0010kVMG\u0011vW\rZQcQ\u0017G\u0010l\u001c!a0Vm a2R~&z:F\u001eC~\u0013gS\u0010K_pW\u000eA\tg\u0012\u0017F\u001a\"[\rZ\u001alFCH\u0016nF\u0006\\_k\\Co\u0011f@\fG\u001bOS\rG\u0019gA\u0017\u0000\u0007o^";
        r0 = '=';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:78:0x02ce, code lost:
        r3[r2] = r1;
        r2 = '?';
        r1 = "\u001elV\u0011A\u0016f\u001c\u0013K\ro[\u0010]\u0016m\\My-Kf&q,Gf7g1Ea";
        r0 = '>';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:79:0x02d9, code lost:
        r3[r2] = r1;
        r2 = '@';
        r1 = ">lV\u0011A\u0016f\u007f\u0002@\u0016dW\u0010ZQz_\u000f\u000e\u0012kA\u0010G\u0011e\u0012\u0011K\u000ew[\u0011K\u001b\"A\u0006\\\tkQ\u0006\u0014_";
        r0 = '?';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:7:0x001b, code lost:
        switch((r8 % 5)) {
            case 0: goto L_0x0842;
            case 1: goto L_0x0846;
            case 2: goto L_0x0849;
            case 3: goto L_0x084d;
            default: goto L_0x001e;
        };
     */
    /* JADX WARN: Code restructure failed: missing block: B:80:0x02e4, code lost:
        r3[r2] = r1;
        r2 = 'A';
        r1 = ";gD\u0006B\u0010rW\u0011\u000e\fj]\u0016B\u001b\"A\u0006Z_CB\u0013e\u001a{\u0012\n@_C\\\u0007\\\u0010kV.O\u0011kT\u0006]\u000b,J\u000eB";
        r0 = '@';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:81:0x02ef, code lost:
        r3[r2] = r1;
        r2 = 'B';
        r1 = "\u001cl\u001c\t^\nqZMO\u0011f@\fG\u001b,[\rZ\u001alFM|:E{0z:P";
        r0 = 'A';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:82:0x02fa, code lost:
        r3[r2] = r1;
        r2 = 'C';
        r1 = "\u001elV\u0011A\u0016f\u001c\n@\u000bg\\\u0017\u0000\u001eaF\nA\u0011,p,a+]q,c/Nw7k;";
        r0 = 'B';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:83:0x0305, code lost:
        r3[r2] = r1;
        r2 = 'D';
        r1 = ">lV\u0011A\u0016f\u007f\u0002@\u0016dW\u0010ZQz_\u000f\u000e\u0012kA\u0010G\u0011e\u0012\u0011K\u000ew[\u0011K\u001b\"[\rZ\u001alFCH\u0016nF\u0006\\_d]\u0011\u000e/wA\u000bo\u001cv[\u0015G\u000b{\bCM\u0011,X\u0013[\fj\u001c\u0002@\u001bp]\nJQw[M~\nqZ\"M\u000bkD\nZ\u0006";
        r0 = 'C';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:84:0x0310, code lost:
        r3[r2] = r1;
        r2 = 'E';
        r1 = ">lV\u0011A\u0016f\u007f\u0002@\u0016dW\u0010ZQz_\u000f\u000e\u0012kA\u0010G\u0011e\u0012\u0011K\u000ew[\u0011K\u001b\"[\rZ\u001alFCH\u0016nF\u0006\\_8\u0012\u0000@QhB\u0016]\u0017,S\rJ\rm[\u0007\u0000\u0016lF\u0006@\u000b,`&~0Pf";
        r0 = 'D';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:85:0x031b, code lost:
        r3[r2] = r1;
        r2 = 'F';
        r1 = ">lV\u0011A\u0016f\u007f\u0002@\u0016dW\u0010ZQz_\u000f\u000e\u0012kA\u0010G\u0011e\u0012\u0011K\u000ew[\u0011K\u001b\"S\u0000Z\u0016t[\u0017WE\"";
        r0 = 'E';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:86:0x0326, code lost:
        r3[r2] = r1;
        r2 = 'G';
        r1 = "&mGC]\u0017mG\u000fJ_oS\bK_oS\n@_cQ\u0017G\tkF\u001a\u000e\u001azF\u0006@\u001bq\u0012*@\fv@\u0016C\u001alF\u0006J>aF\nX\u0016vKC\u00065RG\u0010FV.\u0012\fZ\u0017g@\u0014G\fg\u0012\u001aA\n\"E\nB\u0013\"\\\fZ_qW\u0006\u000e\nqW\u0011\u000e\u001cn[\u0000E_c\\\u0007\u000e\nqW\u0011\u000e\u001eaF\nX\u001a\"F\nC\u001a\"A\u0017O\rv\u0012\f@_pW\u0013A\rv\u0012\n@_R]\u0011Z\u001en\u001cC";
        r0 = 'F';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:87:0x0331, code lost:
        r3[r2] = r1;
        r2 = 'H';
        r1 = "\u001eaF\nA\u00118Q\u000bK\u001cid\u0002B\u0016f\u007f\u0002@\u0016dW\u0010Z";
        r0 = 'G';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:88:0x033c, code lost:
        r3[r2] = r1;
        r2 = 'I';
        r1 = ">lV\u0011A\u0016f\u007f\u0002@\u0016dW\u0010ZQz_\u000f\u000e\u0012kA\u0010G\u0011e\u0012\n@\u000bg\\\u0017\u000e\u0019k^\u0017K\r\"T\f\\_FS\u0006C\u0010la\u0006\\\tkQ\u0006\u0014_a\\MD\u000fwA\u000b\u0000\u001elV\u0011A\u0016f\u001c\n@\u000bg\\\u0017\u0000;cW\u000eA\u0011QW\u0011X\u0016aW";
        r0 = 'H';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:89:0x0347, code lost:
        r3[r2] = r1;
        r2 = 'J';
        r1 = "\u001cl\u001c\t^\nqZMO\u0011f@\fG\u001b,[\rZ\u001alFM`0V{%g<Cf*a1]`&m:Kd&j R`,v&";
        r0 = 'I';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:8:0x001e, code lost:
        r9 = '.';
     */
    /* JADX WARN: Code restructure failed: missing block: B:90:0x0352, code lost:
        r3[r2] = r1;
        r2 = 'K';
        r1 = ">lV\u0011A\u0016f\u007f\u0002@\u0016dW\u0010ZQz_\u000f\u000e\u0012kA\u0010G\u0011e\u0012\u0011K\u000ew[\u0011K\u001b\"[\rZ\u001alFCH\u0016nF\u0006\\_d]\u0011\u000e/wA\u000b|\u001aaW\nX\u001ap\bCM\u0011,X\u0013[\fj\u001c\u0002@\u001bp]\nJQk\\\u0017K\u0011v\u001c-a+Kt*m>V{,` Pw k6Tw'q/P};w";
        r0 = 'J';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:91:0x035d, code lost:
        r3[r2] = r1;
        r2 = 'L';
        r1 = "+jWC^\u001ap_\n]\fm[\r\u000e\u0016q\u0012\u0011K\u000ew[\u0011K\u001b\"\u001fCO\u0011f@\fG\u001b,B\u0006\\\u0012kA\u0010G\u0010l\u001c4|6Vw<}:Vf*`8Q";
        r0 = 'K';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:92:0x0368, code lost:
        r3[r2] = r1;
        r2 = 'M';
        r1 = "\u001cl\u001c\t^\nqZMO\u0011f@\fG\u001b,[\rZ\u001alFM|:R}1z";
        r0 = 'L';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:93:0x0373, code lost:
        r3[r2] = r1;
        r2 = 'N';
        r1 = "+jWC^\u001ap_\n]\fk]\r\u000e\fj]\u0016B\u001b\"P\u0006\u000e\u001bgT\n@\u001af\u0012N\u000e";
        r0 = 'M';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:94:0x037e, code lost:
        r3[r2] = r1;
        r2 = 'O';
        r1 = "(g\u0012\u0011K\u001cm_\u000eK\u0011f\u0012\u001aA\n\"S\u0007J_vZ\u0006\u000e\u000fg@\u000eG\fq[\f@_/\u0012";
        r0 = 'N';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:95:0x0389, code lost:
        r3[r2] = r1;
        r2 = 'P';
        r1 = ">lV\u0011A\u0016f\u007f\u0002@\u0016dW\u0010ZQz_\u000f\u000e\u0012kA\u0010G\u0011e\u0012\u0011K\u000ew[\u0011K\u001b\"@\u0006M\u001akD\u0006\\E\"";
        r0 = 'O';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:96:0x0394, code lost:
        r3[r2] = r1;
        r2 = 'Q';
        r1 = "+jWC^\u001ap_\n]\fm[\r\u000e\u0016q\u0012\u0011K\u000ew[\u0011K\u001b\"\u001fC";
        r0 = 'P';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:97:0x039f, code lost:
        r3[r2] = r1;
        r2 = 'R';
        r1 = "\u001cl\u001c\t^\nqZMO\u0011f@\fG\u001b,G\n\u0000/wA\u000bo\u001cv[\u0015G\u000b{";
        r0 = 'Q';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:98:0x03aa, code lost:
        r3[r2] = r1;
        r2 = 'S';
        r1 = ">lV\u0011A\u0016f\u007f\u0002@\u0016dW\u0010ZQz_\u000f\u000e\u0012kA\u0010G\u0011e\u0012\u0011K\u000ew[\u0011K\u001b\"[\rZ\u001alFCH\u0016nF\u0006\\_d]\u0011\u000e/wA\u000b}\u001apD\nM\u001a8\u0012\u0000@QhB\u0016]\u0017,S\rJ\rm[\u0007\u0000\u0016lF\u0006@\u000b,`&i6Qf&|";
        r0 = 'R';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:99:0x03b5, code lost:
        r3[r2] = r1;
        r2 = 'T';
        r1 = "+jWC^\u001ap_\n]\fm[\r\u000e\u0016q\u0012\u0011K\u000ew[\u0011K\u001b\"\u001fCO\u0011f@\fG\u001b,B\u0006\\\u0012kA\u0010G\u0010l\u001c4|6Vw<k'Vw1`>Nm0z0Ps$k";
        r0 = 'S';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:9:0x0020, code lost:
        r5[r6] = (char) (r9 ^ r10);
        r6 = r8 + 1;
     */
    static {
        /*
            Method dump skipped, instructions count: 2810
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.jpush.android.util.b.<clinit>():void");
    }

    private static String A(Context context) {
        ac.b();
        String string = context.getSharedPreferences(z[54], 0).getString(z[55], null);
        if (!ao.a(string)) {
            return string;
        }
        if (!a()) {
            return C(context);
        }
        String g2 = a.g(context);
        return g2 == null ? (Build.VERSION.SDK_INT < 23 || (c(context, z[61]) && c(context, z[88]))) ? B(context) : C(context) : g2;
    }

    /* JADX WARN: Removed duplicated region for block: B:53:0x00bf A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static java.lang.String B(android.content.Context r6) {
        /*
            r1 = 0
            java.lang.String r0 = e()
            if (r0 != 0) goto L_0x0047
            r0 = r1
        L_0x0008:
            boolean r2 = cn.jpush.android.util.ao.a(r0)
            if (r2 != 0) goto L_0x00cd
            java.io.File r2 = new java.io.File
            r2.<init>(r0)
            r3 = r2
        L_0x0014:
            if (r3 == 0) goto L_0x0063
            boolean r0 = r3.exists()     // Catch: Exception -> 0x005f
            if (r0 == 0) goto L_0x0063
            java.io.FileInputStream r0 = new java.io.FileInputStream     // Catch: Exception -> 0x005f
            r0.<init>(r3)     // Catch: Exception -> 0x005f
            java.util.ArrayList r0 = cn.jpush.android.util.r.a(r0)     // Catch: Exception -> 0x005f
            int r2 = r0.size()     // Catch: Exception -> 0x005f
            if (r2 <= 0) goto L_0x0063
            r2 = 0
            java.lang.Object r0 = r0.get(r2)     // Catch: Exception -> 0x005f
            java.lang.String r0 = (java.lang.String) r0     // Catch: Exception -> 0x005f
            cn.jpush.android.a.c(r6, r0)     // Catch: Exception -> 0x005f
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch: Exception -> 0x005f
            java.lang.String[] r4 = cn.jpush.android.util.b.z     // Catch: Exception -> 0x005f
            r5 = 152(0x98, float:2.13E-43)
            r4 = r4[r5]     // Catch: Exception -> 0x005f
            r2.<init>(r4)     // Catch: Exception -> 0x005f
            r2.append(r0)     // Catch: Exception -> 0x005f
            cn.jpush.android.util.ac.c()     // Catch: Exception -> 0x005f
        L_0x0046:
            return r0
        L_0x0047:
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.StringBuilder r0 = r2.append(r0)
            java.lang.String[] r2 = cn.jpush.android.util.b.z
            r3 = 153(0x99, float:2.14E-43)
            r2 = r2[r3]
            java.lang.StringBuilder r0 = r0.append(r2)
            java.lang.String r0 = r0.toString()
            goto L_0x0008
        L_0x005f:
            r0 = move-exception
            r0.printStackTrace()
        L_0x0063:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            long r4 = java.lang.System.currentTimeMillis()
            java.lang.StringBuilder r0 = r0.append(r4)
            java.lang.String r0 = r0.toString()
            byte[] r0 = r0.getBytes()
            java.util.UUID r0 = java.util.UUID.nameUUIDFromBytes(r0)
            java.lang.String r0 = r0.toString()
            java.lang.String r0 = cn.jpush.android.util.ao.b(r0)
            cn.jpush.android.a.c(r6, r0)
            if (r3 == 0) goto L_0x00a6
            r3.createNewFile()     // Catch: IOException -> 0x00aa
            java.io.FileOutputStream r2 = new java.io.FileOutputStream     // Catch: IOException -> 0x00af, all -> 0x00bb
            r2.<init>(r3)     // Catch: IOException -> 0x00af, all -> 0x00bb
            byte[] r1 = r0.getBytes()     // Catch: IOException -> 0x00ca, all -> 0x00c5
            r2.write(r1)     // Catch: IOException -> 0x00ca, all -> 0x00c5
            r2.flush()     // Catch: IOException -> 0x00ca, all -> 0x00c5
            cn.jpush.android.util.ac.c()     // Catch: IOException -> 0x00ca, all -> 0x00c5
            if (r2 == 0) goto L_0x0046
            r2.close()     // Catch: IOException -> 0x00a4
            goto L_0x0046
        L_0x00a4:
            r1 = move-exception
            goto L_0x0046
        L_0x00a6:
            cn.jpush.android.util.ac.e()     // Catch: IOException -> 0x00aa
            goto L_0x0046
        L_0x00aa:
            r1 = move-exception
            cn.jpush.android.util.ac.i()
            goto L_0x0046
        L_0x00af:
            r2 = move-exception
        L_0x00b0:
            cn.jpush.android.util.ac.i()     // Catch: all -> 0x00c7
            if (r1 == 0) goto L_0x0046
            r1.close()     // Catch: IOException -> 0x00b9
            goto L_0x0046
        L_0x00b9:
            r1 = move-exception
            goto L_0x0046
        L_0x00bb:
            r0 = move-exception
            r2 = r1
        L_0x00bd:
            if (r2 == 0) goto L_0x00c2
            r2.close()     // Catch: IOException -> 0x00c3
        L_0x00c2:
            throw r0
        L_0x00c3:
            r1 = move-exception
            goto L_0x00c2
        L_0x00c5:
            r0 = move-exception
            goto L_0x00bd
        L_0x00c7:
            r0 = move-exception
            r2 = r1
            goto L_0x00bd
        L_0x00ca:
            r1 = move-exception
            r1 = r2
            goto L_0x00b0
        L_0x00cd:
            r3 = r1
            goto L_0x0014
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.jpush.android.util.b.B(android.content.Context):java.lang.String");
    }

    private static String C(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(z[54], 0);
        String string = sharedPreferences.getString(z[55], null);
        if (string != null) {
            return string;
        }
        String uuid = UUID.randomUUID().toString();
        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.putString(z[55], uuid);
        edit.commit();
        return uuid;
    }

    private static boolean D(Context context) {
        Class<?> cls;
        try {
            Intent intent = new Intent(z[100]);
            intent.setPackage(context.getPackageName());
            intent.addCategory(z[99]);
            ActivityInfo activityInfo = context.getPackageManager().resolveActivity(intent, 0).activityInfo;
            if (activityInfo == null || (cls = Class.forName(activityInfo.name)) == null) {
                return false;
            }
            if (!InstrumentedActivity.class.isAssignableFrom(cls)) {
                if (!InstrumentedListActivity.class.isAssignableFrom(cls)) {
                    return false;
                }
            }
            return true;
        } catch (Exception e2) {
            return false;
        }
    }

    private static void E(Context context) {
        ac.c();
        if (h == null) {
            h = new PushReceiver();
        }
        context.registerReceiver(h, new IntentFilter(z[115]));
        context.registerReceiver(h, new IntentFilter(z[114]));
        try {
            IntentFilter intentFilter = new IntentFilter(z[112]);
            intentFilter.addDataScheme(z[113]);
            IntentFilter intentFilter2 = new IntentFilter(z[116]);
            intentFilter2.addDataScheme(z[113]);
            IntentFilter intentFilter3 = new IntentFilter(z[74]);
            intentFilter3.setPriority(1000);
            intentFilter3.addCategory(context.getPackageName());
            context.registerReceiver(h, intentFilter);
            context.registerReceiver(h, intentFilter2);
            context.registerReceiver(h, intentFilter3);
        } catch (Exception e2) {
        }
    }

    public static int a(Context context, float f2) {
        return (int) (context.getResources().getDisplayMetrics().density * f2);
    }

    public static Intent a(Context context, c cVar) {
        Intent intent = new Intent(context, PopWinActivity.class);
        intent.putExtra(z[25], cVar);
        intent.addFlags(335544320);
        return intent;
    }

    public static Intent a(Context context, c cVar, boolean z2) {
        Intent intent = new Intent();
        intent.putExtra(z[171], z2);
        intent.putExtra(z[25], cVar);
        intent.setAction(z[82]);
        intent.addCategory(context.getPackageName());
        if (((m) cVar).F == 0) {
            intent.addFlags(276824064);
        } else {
            intent.addFlags(335544320);
        }
        return intent;
    }

    public static String a(int i) {
        return (i == 1 || i == 2 || i == 8) ? z[95] : (i == 4 || i == 7 || i == 5 || i == 6) ? z[94] : i == 13 ? z[93] : z[50];
    }

    public static String a(Context context) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        if (displayMetrics == null) {
            return z[98];
        }
        int i = displayMetrics.widthPixels;
        return i + "*" + displayMetrics.heightPixels;
    }

    public static String a(Context context, String str) {
        boolean z2;
        String str2 = Build.VERSION.RELEASE + "," + Integer.toString(Build.VERSION.SDK_INT);
        String str3 = Build.MODEL;
        String a2 = p.a(context, z[37], z[34]);
        String str4 = Build.DEVICE;
        String G = a.G();
        if (ao.a(G)) {
            G = HanziToPinyin.Token.SEPARATOR;
        }
        StringBuilder append = new StringBuilder().append(str2).append(z[59]).append(str3).append(z[59]).append(a2).append(z[59]).append(str4).append(z[59]).append(G).append(z[59]).append(str).append(z[59]);
        String str5 = context.getApplicationInfo().sourceDir;
        if (ao.a(str5)) {
            ac.e();
            z2 = false;
        } else {
            new StringBuilder(z[56]).append(str5);
            ac.b();
            if (str5.startsWith(z[58])) {
                z2 = true;
            } else if (str5.startsWith(z[57])) {
                z2 = false;
            } else {
                ac.c();
                z2 = false;
            }
        }
        return append.append(z2 ? 1 : 0).append(z[59]).append(a(context)).toString();
    }

    public static String a(String str) {
        try {
            MessageDigest instance = MessageDigest.getInstance(z[0]);
            char[] charArray = str.toCharArray();
            byte[] bArr = new byte[charArray.length];
            for (int i = 0; i < charArray.length; i++) {
                bArr[i] = (byte) charArray[i];
            }
            byte[] digest = instance.digest(bArr);
            StringBuffer stringBuffer = new StringBuffer();
            for (byte b2 : digest) {
                int i2 = b2 & 255;
                if (i2 < 16) {
                    stringBuffer.append("0");
                }
                stringBuffer.append(Integer.toHexString(i2));
            }
            return stringBuffer.toString();
        } catch (Exception e2) {
            ac.b();
            return "";
        }
    }

    public static String a(byte[] bArr) {
        try {
            byte[] digest = MessageDigest.getInstance(z[0]).digest(bArr);
            StringBuffer stringBuffer = new StringBuffer();
            for (byte b2 : digest) {
                int i = b2 & 255;
                if (i < 16) {
                    stringBuffer.append("0");
                }
                stringBuffer.append(Integer.toHexString(i));
            }
            return stringBuffer.toString();
        } catch (Exception e2) {
            ac.b();
            return "";
        }
    }

    public static JSONObject a(String str, String str2) {
        if (str2 == null || str2.length() == 0) {
            return null;
        }
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(z[42], str2);
            jSONObject.put(z[40], str);
            jSONObject.put(z[41], a.m());
            return jSONObject;
        } catch (Exception e2) {
            e2.getMessage();
            ac.e();
            return null;
        }
    }

    public static JSONObject a(String str, JSONArray jSONArray) {
        if (jSONArray == null || jSONArray.length() == 0) {
            return null;
        }
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(z[42], jSONArray);
            jSONObject.put(z[40], str);
            jSONObject.put(z[41], a.m());
            return jSONObject;
        } catch (Exception e2) {
            e2.getMessage();
            ac.e();
            return null;
        }
    }

    public static void a(Context context, String str, Bundle bundle) {
        if (bundle == null) {
            ac.e(z[28], z[87]);
            return;
        }
        Intent intent = new Intent(str);
        bundle.putString(z[85], a.F());
        intent.putExtras(bundle);
        String packageName = context.getPackageName();
        intent.addCategory(packageName);
        context.sendBroadcast(intent, String.format(z[86], packageName));
    }

    public static void a(Context context, String str, String str2, int i) {
        Uri parse = Uri.parse(str2);
        if (parse == null) {
            new StringBuilder(z[151]).append(str2);
            ac.b();
            return;
        }
        Intent intent = new Intent(z[89], parse);
        intent.setFlags(335544320);
        Parcelable fromContext = Intent.ShortcutIconResource.fromContext(context, i);
        Intent intent2 = new Intent(z[147]);
        intent2.putExtra(z[146], false);
        intent2.putExtra(z[148], str);
        intent2.putExtra(z[150], intent);
        intent2.putExtra(z[149], fromContext);
        context.sendBroadcast(intent2);
    }

    public static void a(Context context, String str, String str2, String str3) {
        Bundle bundle = new Bundle();
        if (str2 != null) {
            bundle.putString(str2, str3);
        }
        a(context, str, bundle);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static void a(Context context, String str, String str2, byte[] bArr) {
        Bundle bundle = new Bundle();
        if (!(str2 == null || bArr == 0)) {
            bundle.putSerializable(str2, bArr);
        }
        a(context, str, bundle);
    }

    public static void a(Context context, boolean z2) {
        ac.b(z[28], z[30]);
        try {
            Bundle bundle = new Bundle();
            JSONObject jSONObject = new JSONObject();
            jSONObject.put(z[29], z2);
            bundle.putString(z[31], jSONObject.toString());
            a(context, z[27], bundle);
        } catch (JSONException e2) {
            new StringBuilder(z[26]).append(e2.getMessage());
            ac.d();
        }
    }

    public static void a(WebSettings webSettings) {
        webSettings.setJavaScriptEnabled(true);
        webSettings.setDefaultTextEncodingName(z[164]);
        webSettings.setSupportZoom(true);
        webSettings.setCacheMode(2);
        webSettings.setSaveFormData(false);
        webSettings.setSavePassword(false);
    }

    public static boolean a() {
        boolean equals = Environment.getExternalStorageState().equals(z[145]);
        if (!equals) {
            ac.b();
        }
        return equals;
    }

    private static boolean a(Context context, Class<?> cls) {
        return !context.getPackageManager().queryBroadcastReceivers(new Intent(context, cls), 0).isEmpty();
    }

    public static boolean a(Context context, String str, String str2) {
        if (context == null) {
            throw new IllegalArgumentException(z[142]);
        } else if (TextUtils.isEmpty(str)) {
            new StringBuilder(z[143]).append(str);
            ac.d();
            return false;
        } else {
            Intent n = n(context, str);
            if (n == null) {
                try {
                    if (TextUtils.isEmpty(str2)) {
                        ac.b();
                        return false;
                    }
                    n = new Intent();
                    n.setClassName(str, str2);
                    n.addCategory(z[99]);
                } catch (Exception e2) {
                    ac.g();
                    return false;
                }
            }
            n.setFlags(268435456);
            context.startActivity(n);
            return true;
        }
    }

    public static boolean a(Context context, String str, boolean z2) {
        PackageManager packageManager = context.getPackageManager();
        Intent intent = new Intent(str);
        intent.addCategory(context.getPackageName());
        return !packageManager.queryBroadcastReceivers(intent, 0).isEmpty();
    }

    private static boolean a(PackageManager packageManager, String str) {
        try {
            int checkPermission = packageManager.checkPermission(str + z[22], str);
            String string = packageManager.getApplicationInfo(str, 128).metaData.getString(z[10]);
            Intent intent = new Intent();
            intent.setClassName(str, z[19]);
            List<ResolveInfo> queryIntentServices = packageManager.queryIntentServices(intent, 0);
            new StringBuilder(z[18]).append(checkPermission).append(z[16]).append(string).append(z[17]).append(queryIntentServices == null ? z[20] : Integer.valueOf(queryIntentServices.size()));
            ac.c();
            if (checkPermission != 0 || queryIntentServices == null || queryIntentServices.size() == 0 || string.isEmpty()) {
                return false;
            }
            return string.length() == 24;
        } catch (PackageManager.NameNotFoundException e2) {
            new StringBuilder(z[21]).append(str);
            ac.d();
            return false;
        } catch (Exception e3) {
            e3.printStackTrace();
            return false;
        }
    }

    public static int b(byte[] bArr) {
        if (bArr == null || bArr.length < 20) {
            return -1;
        }
        return bArr[3] & 255;
    }

    public static String b(Context context, String str) {
        String str2 = Build.VERSION.RELEASE + "," + Integer.toString(Build.VERSION.SDK_INT);
        String str3 = Build.MODEL;
        String a2 = p.a(context, z[37], z[34]);
        String str4 = Build.DEVICE;
        String G = a.G();
        if (ao.a(G)) {
            G = HanziToPinyin.Token.SEPARATOR;
        }
        String c2 = c(context);
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(z[36], str2);
            jSONObject.put(z[32], str3);
            jSONObject.put(z[34], a2);
            jSONObject.put(z[33], str4);
            jSONObject.put(z[35], G);
            jSONObject.put(z[38], c2);
            jSONObject.put(z[39], str);
        } catch (JSONException e2) {
        }
        return jSONObject.toString();
    }

    public static String b(String str) {
        try {
            byte[] digest = MessageDigest.getInstance(z[0]).digest(str.getBytes(z[144]));
            StringBuffer stringBuffer = new StringBuffer();
            for (byte b2 : digest) {
                int i = b2 & 255;
                if (i < 16) {
                    stringBuffer.append("0");
                }
                stringBuffer.append(Integer.toHexString(i));
            }
            return stringBuffer.toString();
        } catch (Exception e2) {
            ac.b();
            return "";
        }
    }

    public static void b() {
        try {
            PowerManager.WakeLock b2 = q.a().b();
            if (b2 != null) {
                if (b2.isHeld()) {
                    try {
                        b2.release();
                        long currentTimeMillis = System.currentTimeMillis() - d;
                        d = 0L;
                        new StringBuilder(z[117]).append(currentTimeMillis);
                        ac.a();
                    } catch (RuntimeException e2) {
                        ac.h();
                    }
                } else {
                    ac.a();
                }
            }
        } catch (IllegalStateException e3) {
            e3.printStackTrace();
            ac.b();
        } catch (Exception e4) {
            e4.printStackTrace();
            ac.b();
        }
    }

    public static void b(Context context, c cVar) {
        try {
            Intent intent = new Intent(z[107]);
            intent.putExtra(z[85], cVar.n);
            intent.putExtra(z[103], cVar.i);
            intent.putExtra(z[102], cVar.j);
            intent.putExtra(z[106], cVar.k);
            intent.putExtra(z[96], cVar.l);
            intent.putExtra(z[104], cVar.c);
            if (cVar.e()) {
                intent.putExtra(z[105], cVar.C);
            }
            intent.addCategory(cVar.m);
            context.sendBroadcast(intent, String.format(z[86], cVar.m));
            new StringBuilder(z[101]).append(String.format(z[86], cVar.m));
            ac.c();
            k.a(cVar.c, PointerIconCompat.TYPE_ZOOM_IN, context);
        } catch (Exception e2) {
            e2.getMessage();
            ac.e();
        }
    }

    public static void b(Context context, String str, String str2) {
        a(context, str, z[96], str2);
    }

    public static void b(Context context, String str, String str2, int i) {
        int i2;
        String str3;
        String str4;
        Notification notification;
        if (g(context)) {
            ac.b();
            Intent intent = new Intent(context, PushReceiver.class);
            intent.setAction(z[179]);
            intent.putExtra(z[180], true);
            if (-1 == i) {
                intent.putExtra(z[183], str2);
            } else {
                intent.putExtra(z[173], str2);
            }
            intent.putExtra(z[40], i);
            PendingIntent broadcast = PendingIntent.getBroadcast(context, 0, intent, 134217728);
            NotificationManager notificationManager = (NotificationManager) context.getSystemService(z[177]);
            int identifier = context.getApplicationContext().getResources().getIdentifier(z[181], z[175], context.getApplicationContext().getPackageName());
            if (identifier == 0) {
                try {
                    i2 = context.getPackageManager().getApplicationInfo(context.getApplicationContext().getPackageName(), 0).icon;
                } catch (Exception e2) {
                    ac.b(z[28], z[172], e2);
                    i2 = 17301586;
                }
            } else {
                i2 = identifier;
            }
            if (-1 == i) {
                str3 = z[174];
                str4 = z[176];
            } else {
                str3 = z[178];
                str4 = z[182];
            }
            long currentTimeMillis = System.currentTimeMillis();
            if (Build.VERSION.SDK_INT >= 11) {
                notification = new Notification.Builder(context.getApplicationContext()).setContentTitle(str3).setContentText(str4).setContentIntent(broadcast).setSmallIcon(i2).setTicker(str).setWhen(currentTimeMillis).getNotification();
                notification.flags = 34;
            } else {
                Notification notification2 = new Notification(i2, str, currentTimeMillis);
                notification2.flags = 34;
                n.a(notification2, context, str3, str4, broadcast);
                notification = notification2;
            }
            if (notification != null) {
                notificationManager.notify(str.hashCode(), notification);
            }
        }
    }

    public static boolean b(Context context) {
        try {
            NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService(z[9])).getActiveNetworkInfo();
            if (activeNetworkInfo != null) {
                if (activeNetworkInfo.isConnected()) {
                    return true;
                }
            }
            return false;
        } catch (Exception e2) {
            e2.printStackTrace();
            return false;
        }
    }

    private static boolean b(Context context, Class<?> cls) {
        return !context.getPackageManager().queryIntentServices(new Intent(context, cls), 0).isEmpty();
    }

    private static boolean b(Context context, String str, boolean z2) {
        PackageManager packageManager = context.getPackageManager();
        Intent intent = new Intent(str);
        if (z2) {
            intent.addCategory(context.getPackageName());
        }
        return !packageManager.queryIntentServices(intent, 0).isEmpty();
    }

    public static int c(String str) {
        String[] split = str.split(z[7]);
        return Integer.parseInt(split[2]) + (Integer.parseInt(split[0]) << 16) + (Integer.parseInt(split[1]) << 8);
    }

    public static String c() {
        int indexOf;
        StringBuffer stringBuffer = new StringBuffer();
        if (new File(z[165]).exists()) {
            try {
                BufferedReader bufferedReader = new BufferedReader(new FileReader(new File(z[165])));
                while (true) {
                    String readLine = bufferedReader.readLine();
                    if (readLine == null) {
                        break;
                    } else if (readLine.contains(z[166]) && (indexOf = readLine.indexOf(":")) >= 0 && indexOf < readLine.length() - 1) {
                        stringBuffer.append(readLine.substring(indexOf + 1).trim());
                    }
                }
                bufferedReader.close();
            } catch (IOException e2) {
            }
        }
        return stringBuffer.toString();
    }

    public static String c(Context context) {
        String str;
        try {
            NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService(z[9])).getActiveNetworkInfo();
            if (activeNetworkInfo == null) {
                str = z[24];
            } else {
                str = activeNetworkInfo.getTypeName();
                String subtypeName = activeNetworkInfo.getSubtypeName();
                if (str == null) {
                    str = z[24];
                } else if (!ao.a(subtypeName)) {
                    str = str + "," + subtypeName;
                }
            }
            return str;
        } catch (Exception e2) {
            e2.printStackTrace();
            return z[24];
        }
    }

    public static boolean c(Context context, String str) {
        if (context != null) {
            try {
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            if (!TextUtils.isEmpty(str)) {
                return context.getPackageManager().checkPermission(str, context.getPackageName()) == 0;
            }
        }
        throw new IllegalArgumentException(z[11]);
    }

    private static boolean c(Context context, String str, String str2) {
        PackageManager packageManager = context.getPackageManager();
        Intent intent = new Intent(str2);
        intent.setPackage(context.getPackageName());
        for (ResolveInfo resolveInfo : packageManager.queryBroadcastReceivers(intent, 0)) {
            ActivityInfo activityInfo = resolveInfo.activityInfo;
            if (activityInfo != null && activityInfo.name.equals(str)) {
                return true;
            }
        }
        return false;
    }

    public static String d() {
        try {
            Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
            while (networkInterfaces.hasMoreElements()) {
                Enumeration<InetAddress> inetAddresses = networkInterfaces.nextElement().getInetAddresses();
                while (inetAddresses.hasMoreElements()) {
                    InetAddress nextElement = inetAddresses.nextElement();
                    if (!nextElement.isLoopbackAddress() && (nextElement instanceof Inet4Address)) {
                        return nextElement.getHostAddress().toString();
                    }
                }
            }
        } catch (Exception e2) {
            ac.d();
            e2.printStackTrace();
        }
        return "";
    }

    public static String d(Context context) {
        try {
            NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService(z[9])).getActiveNetworkInfo();
            return activeNetworkInfo == null ? "" : activeNetworkInfo.getTypeName().toUpperCase(Locale.getDefault());
        } catch (Exception e2) {
            e2.printStackTrace();
            return "";
        }
    }

    public static boolean d(Context context, String str) {
        try {
            context.getPackageManager().getReceiverInfo(new ComponentName(context.getPackageName(), str), 128);
            return true;
        } catch (PackageManager.NameNotFoundException e2) {
            return false;
        }
    }

    private static boolean d(String str) {
        if (ao.a(str) || str.length() < 10) {
            return false;
        }
        for (int i = 0; i < b.size(); i++) {
            if (str.equals(b.get(i)) || str.startsWith(b.get(i))) {
                return false;
            }
        }
        return true;
    }

    private static String e() {
        String str = null;
        try {
            str = Environment.getExternalStorageDirectory().getPath();
        } catch (ArrayIndexOutOfBoundsException e2) {
            e2.printStackTrace();
        } catch (Exception e3) {
        }
        return !ao.a(str) ? str + z[123] : str;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v10 */
    /* JADX WARN: Type inference failed for: r1v12 */
    /* JADX WARN: Type inference failed for: r1v13, types: [java.io.FileOutputStream] */
    /* JADX WARN: Type inference failed for: r1v14, types: [java.io.IOException] */
    /* JADX WARN: Type inference failed for: r1v15 */
    /* JADX WARN: Type inference failed for: r1v17 */
    /* JADX WARN: Type inference failed for: r1v18, types: [java.io.FileOutputStream] */
    /* JADX WARN: Type inference failed for: r1v6 */
    /* JADX WARN: Type inference failed for: r1v7, types: [java.io.FileOutputStream] */
    private static String e(String str) {
        String e2 = e();
        if (ao.a(e2)) {
            ac.e();
            return null;
        }
        File file = new File(e2);
        if (!file.exists()) {
            try {
                file.mkdir();
            } catch (Exception e3) {
                ac.i();
            }
        }
        if (ao.a(f())) {
            ac.e(z[28], z[169]);
            return null;
        }
        File file2 = new File(e2 + z[141]);
        boolean e4 = file2.exists();
        if (e4) {
            try {
                file2.delete();
            } catch (SecurityException e5) {
                return null;
            }
        }
        try {
            try {
                file2.createNewFile();
            } catch (Throwable th) {
                th = th;
            }
            try {
                e4 = new FileOutputStream(file2);
                try {
                    e4.write(str.getBytes());
                    e4.flush();
                    ac.c();
                    if (e4 != 0) {
                        try {
                            e4.close();
                        } catch (IOException e6) {
                        }
                    }
                } catch (IOException e7) {
                    ac.i();
                    if (e4 != 0) {
                        try {
                            e4.close();
                        } catch (IOException e8) {
                            e4 = e8;
                        }
                    }
                    str = null;
                    return str;
                }
            } catch (IOException e9) {
                e4 = 0;
            } catch (Throwable th2) {
                th = th2;
                e4 = 0;
                if (e4 != 0) {
                    try {
                        e4.close();
                    } catch (IOException e10) {
                    }
                }
                throw th;
            }
            return str;
        } catch (IOException e11) {
            ac.i();
            return null;
        }
    }

    public static void e(Context context) {
        h(context, null);
    }

    public static void e(Context context, String str) {
        Intent intent = new Intent();
        intent.addFlags(268435456);
        intent.setAction(z[89]);
        intent.setDataAndType(Uri.fromFile(new File(str)), z[90]);
        context.startActivity(intent);
    }

    public static int f(Context context) {
        Intent intent;
        if (context == null) {
            return -1;
        }
        try {
            intent = context.registerReceiver(null, new IntentFilter(z[126]));
        } catch (SecurityException e2) {
            e2.printStackTrace();
            intent = null;
        } catch (Exception e3) {
            e3.printStackTrace();
            intent = null;
        }
        if (intent == null) {
            return -1;
        }
        int intExtra = intent.getIntExtra(z[127], -1);
        if (intExtra == 2 || intExtra == 5) {
            return intent.getIntExtra(z[128], -1);
        }
        return -1;
    }

    private static int f(String str) {
        if (ao.a(str)) {
            ac.d();
            return 0;
        } else if (Pattern.matches(z[110], str)) {
            ac.c();
            return 0;
        } else if (Pattern.matches(z[111], str)) {
            ac.c();
            return 1;
        } else if (!Pattern.matches(z[109], str)) {
            return 0;
        } else {
            ac.c();
            return 2;
        }
    }

    private static String f() {
        String e2 = e();
        if (e2 == null) {
            return null;
        }
        return e2 + z[141];
    }

    public static boolean f(Context context, String str) {
        try {
            context.getPackageManager().getApplicationInfo(str, 0);
            return true;
        } catch (PackageManager.NameNotFoundException e2) {
            return false;
        }
    }

    private static String g() {
        String f2 = f();
        if (ao.a(f2)) {
            ac.e(z[28], z[169]);
            return null;
        }
        File file = new File(f2);
        if (file.exists()) {
            try {
                ArrayList<String> a2 = r.a(new FileInputStream(file));
                if (a2.size() > 0) {
                    String str = a2.get(0);
                    new StringBuilder(z[170]).append(str);
                    ac.c();
                    return str;
                }
            } catch (Exception e2) {
                return null;
            }
        }
        return null;
    }

    public static String g(Context context, String str) {
        if (Build.VERSION.SDK_INT >= 23) {
            String str2 = "";
            try {
                String readLine = new LineNumberReader(new InputStreamReader(Runtime.getRuntime().exec(z[3]).getInputStream())).readLine();
                if (!TextUtils.isEmpty(readLine)) {
                    str2 = readLine.trim();
                    new StringBuilder(z[1]).append(str2);
                    ac.b();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            return str2;
        } else if (!c(context, z[2])) {
            return str;
        } else {
            try {
                String macAddress = ((WifiManager) context.getSystemService(z[4])).getConnectionInfo().getMacAddress();
                return !ao.a(macAddress) ? macAddress : str;
            } catch (Exception e3) {
                ac.i();
                return str;
            }
        }
    }

    public static boolean g(Context context) {
        boolean z2 = false;
        try {
            Signature[] signatureArr = context.getPackageManager().getPackageInfo(context.getPackageName(), 64).signatures;
            CertificateFactory instance = CertificateFactory.getInstance(z[118]);
            int i = 0;
            while (i < signatureArr.length) {
                boolean equals = ((X509Certificate) instance.generateCertificate(new ByteArrayInputStream(signatureArr[i].toByteArray()))).getSubjectX500Principal().equals(c);
                if (equals) {
                    return equals;
                }
                i++;
                z2 = equals;
            }
            return z2;
        } catch (PackageManager.NameNotFoundException e2) {
            return z2;
        } catch (Exception e3) {
            return z2;
        }
    }

    public static String h(Context context) {
        String h2 = a.h(context);
        if (!ao.a(h2) && d(h2)) {
            return h2;
        }
        String z2 = z(context);
        a.d(context, z2);
        return z2;
    }

    public static void h(Context context, String str) {
        Intent intent = new Intent(z[100]);
        String packageName = context.getPackageName();
        intent.setPackage(packageName);
        if (!ao.a(str)) {
            intent.putExtra(z[96], str);
        }
        intent.addCategory(z[99]);
        ResolveInfo resolveActivity = context.getPackageManager().resolveActivity(intent, 0);
        new StringBuilder(z[119]).append(resolveActivity.activityInfo.name);
        ac.c();
        intent.setClassName(packageName, resolveActivity.activityInfo.name);
        intent.setFlags(268435456);
        context.startActivity(intent);
    }

    public static String i(Context context) {
        try {
            return Settings.Secure.getString(context.getContentResolver(), z[108]);
        } catch (SecurityException e2) {
            return "";
        } catch (Exception e3) {
            return "";
        }
    }

    public static String i(Context context, String str) {
        try {
            return c(context, z[92]) ? ((TelephonyManager) context.getSystemService(z[91])).getSimSerialNumber() : str;
        } catch (Exception e2) {
            e2.printStackTrace();
            return str;
        }
    }

    public static String j(Context context) {
        String str = null;
        String E = a.E();
        if (!ao.a(E)) {
            a = c.d - 1;
            return E;
        }
        String p = p(context, E);
        if (!ao.a(p)) {
            a = c.b - 1;
            o(context, p);
            a.g(p);
            return p;
        }
        if (!a()) {
            ac.e();
        } else if (c(context, z[61]) && (Build.VERSION.SDK_INT < 23 || (c(context, z[61]) && c(context, z[88])))) {
            str = g();
        }
        if (!ao.a(str)) {
            a = c.c - 1;
            q(context, str);
            a.g(str);
            return str;
        }
        String str2 = "";
        if (Build.VERSION.SDK_INT < 23) {
            str2 = j(context, str);
        }
        String i = i(context);
        String g2 = g(context, "");
        String uuid = UUID.randomUUID().toString();
        String a2 = a(str2 + i + g2 + uuid);
        if (ao.a(a2)) {
            a2 = uuid;
        }
        a.g(a2);
        a = c.a - 1;
        q(context, a2);
        o(context, a2);
        return a2;
    }

    public static String j(Context context, String str) {
        try {
            return c(context, z[92]) ? ((TelephonyManager) context.getSystemService(z[91])).getDeviceId() : str;
        } catch (Exception e2) {
            e2.printStackTrace();
            return str;
        }
    }

    public static String k(Context context, String str) {
        try {
            return c(context, z[92]) ? ((TelephonyManager) context.getSystemService(z[91])).getSubscriberId() : str;
        } catch (Exception e2) {
            e2.printStackTrace();
            return str;
        }
    }

    public static void k(Context context) {
        ac.b();
        long p = a.p() * 1000;
        new StringBuilder(z[168]).append(p).append(z[167]);
        ac.c();
        ((AlarmManager) context.getSystemService(z[122])).setInexactRepeating(0, System.currentTimeMillis() + p, p, PendingIntent.getBroadcast(context, 0, new Intent(context, AlarmReceiver.class), 0));
    }

    public static void l(Context context) {
        try {
            ((AlarmManager) context.getSystemService(z[122])).cancel(PendingIntent.getBroadcast(context, 0, new Intent(context, AlarmReceiver.class), 0));
        } catch (Exception e2) {
            ac.d();
            e2.printStackTrace();
        }
    }

    public static void l(Context context, String str) {
        if (!ao.a(str)) {
            q(context, str);
            o(context, str);
            a.g(str);
        }
    }

    public static void m(Context context) {
        try {
            PowerManager.WakeLock newWakeLock = ((PowerManager) context.getSystemService(z[121])).newWakeLock(1, e.c + z[120]);
            newWakeLock.setReferenceCounted(false);
            q.a().a(newWakeLock);
            if (!q.a().b().isHeld()) {
                q.a().b().acquire();
                d = System.currentTimeMillis();
                ac.a();
            } else {
                ac.a();
            }
        } catch (IllegalStateException e2) {
            e2.printStackTrace();
            ac.b();
        } catch (Exception e3) {
            e3.printStackTrace();
            ac.b();
        }
    }

    private static boolean m(Context context, String str) {
        if (context == null || TextUtils.isEmpty(str)) {
            throw new IllegalArgumentException(z[11]);
        }
        try {
            context.getPackageManager().getPermissionInfo(str, 128);
            return true;
        } catch (PackageManager.NameNotFoundException e2) {
            return false;
        }
    }

    private static Intent n(Context context, String str) {
        try {
            PackageManager packageManager = context.getPackageManager();
            if (packageManager.getPackageInfo(str, 256) != null) {
                return packageManager.getLaunchIntentForPackage(str);
            }
        } catch (PackageManager.NameNotFoundException e2) {
        }
        return null;
    }

    public static JSONArray n(Context context) {
        ArrayList<ad> a2 = v.a(context, true);
        JSONArray jSONArray = new JSONArray();
        try {
            Iterator<ad> it = a2.iterator();
            while (it.hasNext()) {
                ad next = it.next();
                JSONObject jSONObject = new JSONObject();
                jSONObject.put(z[13], next.a);
                jSONObject.put(z[15], next.b);
                jSONObject.put(z[14], next.c);
                jSONObject.put(z[12], next.d);
                jSONArray.put(jSONObject);
            }
        } catch (JSONException e2) {
        }
        return jSONArray;
    }

    private static String o(Context context, String str) {
        if (!a() || !c(context, z[61])) {
            return null;
        }
        if (Build.VERSION.SDK_INT < 23) {
            return e(str);
        }
        if (!c(context, z[61]) || !c(context, z[88])) {
            return null;
        }
        return e(str);
    }

    public static boolean o(Context context) {
        try {
            if (!a.f(context)) {
                ac.c(z[28], z[162]);
                return false;
            }
            String e2 = a.e(context);
            if (ao.a(e2)) {
                ac.c();
                return true;
            }
            new StringBuilder(z[163]).append(e2);
            ac.c();
            String[] split = e2.split("_");
            String str = split[0];
            String str2 = split[1];
            char[] charArray = str.toCharArray();
            String[] split2 = str2.split(z[160]);
            Calendar instance = Calendar.getInstance();
            int i = instance.get(7);
            int i2 = instance.get(11);
            for (char c2 : charArray) {
                if (i == Integer.valueOf(String.valueOf(c2)).intValue() + 1) {
                    int intValue = Integer.valueOf(split2[0]).intValue();
                    int intValue2 = Integer.valueOf(split2[1]).intValue();
                    if (i2 >= intValue && i2 <= intValue2) {
                        return true;
                    }
                }
            }
            ac.c(z[28], z[161] + e2);
            return false;
        } catch (Exception e3) {
            return true;
        }
    }

    private static String p(Context context, String str) {
        if (!c(context, z[63])) {
            return str;
        }
        try {
            return Settings.System.getString(context.getContentResolver(), z[97]);
        } catch (Exception e2) {
            ac.e();
            return str;
        }
    }

    public static boolean p(Context context) {
        ac.b();
        String d2 = a.d(context);
        if (TextUtils.isEmpty(d2)) {
            return false;
        }
        try {
            JSONObject jSONObject = new JSONObject(d2);
            int optInt = jSONObject.optInt(z[135], -1);
            int optInt2 = jSONObject.optInt(z[139], -1);
            int optInt3 = jSONObject.optInt(z[132], -1);
            int optInt4 = jSONObject.optInt(z[138], -1);
            if (optInt < 0 || optInt2 < 0 || optInt3 < 0 || optInt4 < 0 || optInt2 > 59 || optInt4 > 59 || optInt3 > 23 || optInt > 23) {
                return false;
            }
            Calendar instance = Calendar.getInstance();
            int i = instance.get(11);
            int i2 = instance.get(12);
            new StringBuilder(z[130]).append(i).append(z[137]).append(i2).append(z[134]).append(optInt).append(z[133]).append(optInt2).append(z[136]).append(optInt3).append(z[129]).append(optInt4);
            ac.a();
            if (optInt < optInt3) {
                if ((i <= optInt || i >= optInt3) && ((i != optInt || i2 < optInt2) && (i != optInt3 || i2 > optInt4))) {
                    return false;
                }
            } else if (optInt == optInt3) {
                if (optInt2 >= optInt4) {
                    if (i == optInt && i2 > optInt4 && i2 < optInt2) {
                        return false;
                    }
                } else if (i != optInt || i2 < optInt2 || i2 > optInt4) {
                    return false;
                }
            } else if (optInt <= optInt3) {
                return false;
            } else {
                if ((i <= optInt || i > 23) && ((i < 0 || i >= optInt3) && ((i != optInt || i2 < optInt2) && (i != optInt3 || i2 > optInt4)))) {
                    return false;
                }
            }
            ac.c(z[28], z[140] + optInt + ":" + optInt2 + z[131] + optInt3 + ":" + optInt4);
            return true;
        } catch (JSONException e2) {
            return false;
        }
    }

    private static String q(Context context, String str) {
        if (c(context, z[63])) {
            try {
                if (Settings.System.putString(context.getContentResolver(), z[97], str)) {
                    return str;
                }
            } catch (Exception e2) {
                ac.e();
            }
        }
        return null;
    }

    public static boolean q(Context context) {
        boolean z2;
        boolean z3;
        ac.b(z[28], z[72]);
        if (!b(context, PushService.class)) {
            ac.e(z[28], z[64] + PushService.class.getCanonicalName());
            z2 = false;
        } else {
            if (r(context, PushService.class.getCanonicalName())) {
                ac.a();
                e.n = true;
            } else {
                ac.a();
                e.n = false;
            }
            if (!b(context, z[66], false)) {
                ac.e(z[28], z[83]);
                z2 = false;
            } else if (!b(context, z[77], false)) {
                ac.e(z[28], z[69]);
                z2 = false;
            } else {
                if (!b(context, DaemonService.class)) {
                    ac.d(z[28], z[64] + DaemonService.class.getCanonicalName());
                    e.m = false;
                } else if (!b(context, z[5], true)) {
                    ac.d(z[28], z[73]);
                    e.m = false;
                } else {
                    e.m = true;
                }
                if (!b(context, DownloadService.class)) {
                    ac.d(z[28], z[64] + DownloadService.class.getCanonicalName());
                }
                if (!a(context, PushReceiver.class)) {
                    ac.e(z[28], z[80] + PushReceiver.class.getCanonicalName());
                    E(context);
                } else {
                    if (c(context, PushReceiver.class.getCanonicalName(), z[67])) {
                        ac.d(z[28], z[62]);
                    }
                    if (!a(context, z[74], true)) {
                        ac.e(z[28], z[75]);
                        z2 = false;
                    } else if (!a(context, AlarmReceiver.class)) {
                        ac.e(z[28], z[80] + AlarmReceiver.class.getCanonicalName());
                        z2 = false;
                    } else {
                        if (!(!context.getPackageManager().queryIntentActivities(new Intent(context, PushActivity.class), 0).isEmpty())) {
                            ac.e(z[28], z[70] + PushActivity.class.getCanonicalName());
                            z2 = false;
                        } else {
                            String str = z[82];
                            PackageManager packageManager = context.getPackageManager();
                            Intent intent = new Intent(str);
                            intent.addCategory(context.getPackageName());
                            if (!(!packageManager.queryIntentActivities(intent, 0).isEmpty())) {
                                ac.e(z[28], z[68]);
                                z2 = false;
                            } else {
                                String str2 = context.getPackageName() + z[22];
                                if (!m(context, str2)) {
                                    ac.e(z[28], z[78] + str2);
                                    z2 = false;
                                } else {
                                    e.add(str2);
                                    Iterator<String> it = e.iterator();
                                    while (true) {
                                        if (it.hasNext()) {
                                            String next = it.next();
                                            if (!c(context.getApplicationContext(), next)) {
                                                ac.e(z[28], z[81] + next);
                                                z2 = false;
                                                break;
                                            }
                                        } else {
                                            if (Build.VERSION.SDK_INT < 23) {
                                                if (!c(context.getApplicationContext(), z[61])) {
                                                    ac.e(z[28], z[84]);
                                                    z2 = false;
                                                } else if (!c(context.getApplicationContext(), z[63])) {
                                                    ac.e(z[28], z[76]);
                                                    z2 = false;
                                                }
                                            }
                                            Iterator<String> it2 = f.iterator();
                                            while (it2.hasNext()) {
                                                String next2 = it2.next();
                                                if (!c(context.getApplicationContext(), next2)) {
                                                    new StringBuilder(z[79]).append(next2);
                                                    ac.d();
                                                }
                                            }
                                            Iterator<String> it3 = g.iterator();
                                            while (it3.hasNext()) {
                                                String next3 = it3.next();
                                                if (!c(context.getApplicationContext(), next3)) {
                                                    new StringBuilder(z[79]).append(next3).append(z[60]);
                                                    ac.d();
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                z2 = true;
            }
        }
        if (!ao.a(a.F())) {
            z3 = true;
        } else if (!ao.a(e.f)) {
            a.h(e.f);
            z3 = true;
        } else {
            ac.d(z[28], z[65]);
            z3 = false;
        }
        if (!e.k && !D(context)) {
            ac.d(z[28], z[71]);
        }
        return z3 && z2;
    }

    public static void r(Context context) {
        if (h != null && !d(context, PushReceiver.class.getCanonicalName())) {
            try {
                context.unregisterReceiver(h);
            } catch (Exception e2) {
                e2.getMessage();
                ac.e();
            }
        }
    }

    private static boolean r(Context context, String str) {
        ServiceInfo serviceInfo;
        try {
            serviceInfo = context.getPackageManager().getServiceInfo(new ComponentName(context.getPackageName(), str), 128);
            new StringBuilder(z[124]).append(serviceInfo.processName);
            ac.a();
        } catch (PackageManager.NameNotFoundException e2) {
        } catch (NullPointerException e3) {
            new StringBuilder(z[125]).append(str);
            ac.a();
        }
        return serviceInfo.processName.contains(new StringBuilder().append(context.getPackageName()).append(":").toString());
    }

    public static String s(Context context) {
        String str = e.f;
        if (!ao.a(str)) {
            return str;
        }
        try {
            ApplicationInfo applicationInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128);
            return (applicationInfo == null || applicationInfo.metaData == null) ? str : applicationInfo.metaData.getString(z[10]);
        } catch (PackageManager.NameNotFoundException e2) {
            return str;
        }
    }

    public static String t(Context context) {
        NetworkInfo activeNetworkInfo;
        try {
            activeNetworkInfo = ((ConnectivityManager) context.getSystemService(z[9])).getActiveNetworkInfo();
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        if (activeNetworkInfo == null) {
            return z[50];
        }
        if (activeNetworkInfo.getType() == 1) {
            return z[4];
        }
        if (activeNetworkInfo.getType() == 0) {
            int subtype = activeNetworkInfo.getSubtype();
            if (subtype == 4 || subtype == 1 || subtype == 2) {
                return z[53];
            }
            if (subtype == 3 || subtype == 8 || subtype == 6 || subtype == 5 || subtype == 12) {
                return z[51];
            }
            if (subtype == 13) {
                return z[52];
            }
        }
        return "";
    }

    public static void u(Context context) {
        if (!a.H()) {
            String j = j(context, "");
            String I = a.I();
            String i = i(context);
            if (ao.a(i)) {
                i = HanziToPinyin.Token.SEPARATOR;
            }
            String g2 = g(context, "");
            if (ao.a(g2)) {
                g2 = HanziToPinyin.Token.SEPARATOR;
            }
            int f2 = f(I);
            int f3 = f(j);
            if (f2 == 0 || f3 == 0) {
                if (a.b(i, g2)) {
                    return;
                }
            } else if (1 != f2 || 2 != f3) {
                if (2 != f2 || 1 != f3) {
                    if (f2 == f3) {
                        if (j.equals(I)) {
                            if (a.a(j, i)) {
                                return;
                            }
                        } else if (a.b(i, g2)) {
                            return;
                        }
                    }
                } else {
                    return;
                }
            } else {
                return;
            }
            ac.b();
            a.A();
            q(context, "");
            o(context, "");
        }
    }

    public static List<ComponentName> v(Context context) {
        ac.b();
        ArrayList arrayList = new ArrayList();
        PackageManager packageManager = context.getPackageManager();
        Intent intent = new Intent();
        intent.setAction(z[5]);
        List<ResolveInfo> queryIntentServices = packageManager.queryIntentServices(intent, 0);
        if (queryIntentServices == null || queryIntentServices.size() == 0) {
            return null;
        }
        for (int i = 0; i < queryIntentServices.size(); i++) {
            ServiceInfo serviceInfo = queryIntentServices.get(i).serviceInfo;
            String str = serviceInfo.name;
            String str2 = serviceInfo.packageName;
            if (str != null && str2 != null && !ao.a(str) && !ao.a(str2) && serviceInfo.exported && serviceInfo.enabled && !e.c.equals(str2) && a(packageManager, str2)) {
                new StringBuilder(z[6]).append(str2).append("/").append(str).append(h.d);
                ac.b();
                arrayList.add(new ComponentName(str2, str));
            }
        }
        return arrayList;
    }

    public static boolean w(Context context) {
        String a2 = p.a(context, z[156]);
        new StringBuilder(z[157]).append(a2);
        ac.b();
        String a3 = p.a(context, z[158]);
        if (!TextUtils.isEmpty(a2) && z[154].equals(a2) && !TextUtils.isEmpty(a3)) {
            String a4 = p.a(context, z[159]);
            if (!TextUtils.isEmpty(a4) && a4.startsWith(z[155])) {
                ac.d();
                return false;
            }
        }
        return true;
    }

    public static double x(Context context) {
        double pow;
        double pow2;
        Point point = new Point();
        if (context instanceof Activity) {
            Display defaultDisplay = ((Activity) context).getWindowManager().getDefaultDisplay();
            if (Build.VERSION.SDK_INT >= 17) {
                defaultDisplay.getRealSize(point);
            } else if (Build.VERSION.SDK_INT >= 13) {
                defaultDisplay.getSize(point);
            } else {
                point.x = defaultDisplay.getWidth();
                point.y = defaultDisplay.getHeight();
            }
            new StringBuilder(z[48]).append(point.x).append(z[45]).append(point.y);
            ac.b();
        }
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        new StringBuilder(z[47]).append(displayMetrics.xdpi).append(z[44]).append(displayMetrics.ydpi);
        ac.b();
        if (context instanceof Activity) {
            pow = Math.pow(point.x / displayMetrics.xdpi, 2.0d);
            pow2 = Math.pow(point.y / displayMetrics.ydpi, 2.0d);
        } else {
            new StringBuilder(z[43]).append(displayMetrics.widthPixels).append(z[49]).append(displayMetrics.heightPixels);
            ac.b();
            pow = Math.pow(displayMetrics.widthPixels / displayMetrics.xdpi, 2.0d);
            pow2 = Math.pow(displayMetrics.heightPixels / displayMetrics.ydpi, 2.0d);
        }
        double sqrt = Math.sqrt(pow2 + pow);
        new StringBuilder(z[46]).append(sqrt);
        ac.b();
        return sqrt;
    }

    private static String y(Context context) {
        if (!c(context, z[2])) {
            return null;
        }
        try {
            String g2 = g(context, "");
            if (g2 == null || g2.equals("")) {
                return null;
            }
            new StringBuilder(z[23]).append(g2);
            ac.c();
            return a(g2 + Build.MODEL);
        } catch (Exception e2) {
            ac.i();
            return null;
        }
    }

    private static String z(Context context) {
        try {
            String j = j(context, "");
            if (d(j)) {
                return j;
            }
            String i = i(context);
            if (!ao.a(i) && d(i) && !z[8].equals(i.toLowerCase(Locale.getDefault()))) {
                return i;
            }
            String y = y(context);
            if (!ao.a(y) && d(y)) {
                return y;
            }
            String A = A(context);
            return A == null ? HanziToPinyin.Token.SEPARATOR : A;
        } catch (Exception e2) {
            ac.i();
            return A(context);
        }
    }
}
