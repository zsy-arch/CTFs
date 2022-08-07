package cn.jpush.android.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.text.TextUtils;
import android.webkit.URLUtil;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.Proxy;
import java.net.URL;
import java.util.zip.GZIPOutputStream;
import org.json.JSONObject;

/* loaded from: classes.dex */
public final class s {
    public static boolean a;
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
            default: goto L_0x003c;
        };
     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x003c, code lost:
        r3[r2] = r1;
        r2 = 1;
        r1 = "\u0015\u00070hQ\u0015\u0017,";
        r0 = 0;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x0044, code lost:
        r3[r2] = r1;
        r2 = 2;
        r1 = "PC tIF";
        r0 = 1;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x004c, code lost:
        r3[r2] = r1;
        r2 = 3;
        r1 = "3\u0017=cW\\\u0014'iK\u001bC'cV\f\f;u@\\\u0010!gQ\t\u0010u+\u0005";
        r0 = 2;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:19:0x0054, code lost:
        r3[r2] = r1;
        r2 = 4;
        r1 = "?\f;h@\u001f\u0017<iK";
        r0 = 3;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x005c, code lost:
        r3[r2] = r1;
        r2 = 5;
        r1 = ".\u0006$s@\u000f\u0017uvD\b\u000bubJ\u0019\u0010uhJ\bC0~L\u000f\u0017o&\u0011LWu+\u0005";
        r0 = 4;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x0064, code lost:
        r3[r2] = r1;
        r2 = 6;
        r1 = "?\u0002!eM\\0\u0006Ju\u0019\u0006'SK\n\u0006'oC\u0015\u00061C]\u001f\u0006%rL\u0013\ry&M\b\u0017%&F\u0010\n0hQ\\\u0006-cF\t\u00170&@\u000e\u0011:t\u0004";
        r0 = 5;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x006c, code lost:
        r3[r2] = r1;
        r2 = 7;
        r1 = "\u001a\u00029u@";
        r0 = 6;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x0074, code lost:
        r3[r2] = r1;
        r2 = '\b';
        r1 = "\u0014\u0017!v\u000b\u0017\u00060vd\u0010\n#c";
        r0 = 7;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x007d, code lost:
        r3[r2] = r1;
        r2 = '\t';
        r1 = "?\u000f:u@";
        r0 = '\b';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x0087, code lost:
        r3[r2] = r1;
        r2 = '\n';
        r1 = "=\u00006cU\bN\u0010hF\u0013\u0007<hB";
        r0 = '\t';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x0092, code lost:
        r3[r2] = r1;
        r2 = 11;
        r1 = "4\u0017!vm\u0019\u000f%cW";
        r0 = '\n';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x009d, code lost:
        r3[r2] = r1;
        r2 = '\f';
        r1 = "\u001d\u0000!oJ\u0012Y=rQ\f$0r\u0005QC";
        r0 = 11;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x00a8, code lost:
        r3[r2] = r1;
        r2 = '\r';
        r1 = "\u000f\u00174rP\u000f :b@F";
        r0 = '\f';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:29:0x00b3, code lost:
        r3[r2] = r1;
        r2 = 14;
        r1 = "\u001d\r1tJ\u0015\u0007{v@\u000e\u000e<uV\u0015\f;(d? \u0010Uv#-\u0010Rr31\u001eYv(\"\u0001C";
        r0 = '\r';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x00be, code lost:
        r3[r2] = r1;
        r2 = 15;
        r1 = "MS{6\u000bLMd1\u0017";
        r0 = 14;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:31:0x00c9, code lost:
        r3[r2] = r1;
        r2 = 16;
        r1 = "\u001f\u000e\"gU";
        r0 = 15;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x00d4, code lost:
        r3[r2] = r1;
        r2 = 17;
        r1 = "\t\r<qD\f";
        r0 = 16;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x00df, code lost:
        r3[r2] = r1;
        r2 = 18;
        r1 = "O\u0004\"gU";
        r0 = 17;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:34:0x00ea, code lost:
        r3[r2] = r1;
        r2 = 19;
        r1 = "\u001f\f;h@\u001f\u0017<pL\b\u001a";
        r0 = 18;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:35:0x00f5, code lost:
        r3[r2] = r1;
        r2 = com.tencent.open.GameAppOperation.PIC_SYMBOLE;
        r1 = "@_;cQ\u000b\f'm@\u000e\u0011:t\u001bB";
        r0 = 19;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:36:0x0100, code lost:
        r3[r2] = r1;
        r2 = 21;
        r1 = "@_0tW\u0013\u0011k8";
        r0 = com.tencent.open.GameAppOperation.PIC_SYMBOLE;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:37:0x010b, code lost:
        r3[r2] = r1;
        r2 = 22;
        r1 = "@_3gL\u0010\u000618\u001b";
        r0 = 21;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:38:0x0116, code lost:
        r3[r2] = r1;
        r2 = 23;
        r1 = "@_3gL\u0010\u00061YR\u0015\u0017=YW\u0019\u0017'o@\u000f]k";
        r0 = 22;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:39:0x0121, code lost:
        r3[r2] = r1;
        r2 = 24;
        r1 = "/\u0006'p@\u000eC0tW\u0013\u0011u+\u0005";
        r0 = 23;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:40:0x012c, code lost:
        r3[r2] = r1;
        r2 = 25;
        r1 = "\u001d\u0000!oJ\u0012Y=rQ\f0<kU\u0010\u0006\u0012cQ\\Nu";
        r0 = 24;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:41:0x0137, code lost:
        r3[r2] = r1;
        r2 = 26;
        r1 = ".\u0006$s@\u000f\u0017uhJ\bC4sQ\u0014\f'o_\u0019\u0007o2\u0015MCx&";
        r0 = 25;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:42:0x0142, code lost:
        r3[r2] = r1;
        r2 = 27;
        r1 = "/\u0006'p@\u000eC'cV\f\f;u@\\\u00054oI\t\u00110<\u0011LSu+\u0005";
        r0 = 26;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:43:0x014d, code lost:
        r3[r2] = r1;
        r2 = 28;
        r1 = "\u000e\u0006$s@\u000f\u0017urL\u0011\u0006:sQFWe>\u0005QC";
        r0 = 27;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:44:0x0158, code lost:
        r3[r2] = r1;
        r2 = 29;
        r1 = ")7\u0013+\u001d";
        r0 = 28;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:45:0x0163, code lost:
        r3[r2] = r1;
        r2 = 30;
        r1 = "\u001f\f;`I\u0015\u0000!<\u0011LZu+\u0005";
        r0 = 29;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:46:0x016e, code lost:
        r3[r2] = r1;
        r2 = 31;
        r1 = "\u0012\f!&D\u001f\u00000vQ\u001d\u00019c\u001fHSc&\b\\";
        r0 = 30;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:47:0x0179, code lost:
        r3[r2] = r1;
        r2 = ' ';
        r1 = "?\u0002!eM\\\"&u@\u000e\u0017<iK9\u0011'iW\\\u0017:&D\n\f<b\u0005\u0014\u0017!v\u0005\u001f\u000f:u@\\\u0000'gV\u0014Cx&";
        r0 = 31;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:48:0x0184, code lost:
        r3[r2] = r1;
        r2 = '!';
        r1 = "=\u00006cU\bN\u0016nD\u000e\u00100r";
        r0 = ' ';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:49:0x018f, code lost:
        r3[r2] = r1;
        r2 = '\"';
        r1 = ",,\u0006R";
        r0 = '!';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:4:0x0010, code lost:
        if (r5 <= 1) goto L_0x0012;
     */
    /* JADX WARN: Code restructure failed: missing block: B:50:0x019a, code lost:
        r3[r2] = r1;
        r2 = '#';
        r1 = ")\u00100t\b=\u00040hQ";
        r0 = '\"';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:51:0x01a5, code lost:
        r3[r2] = r1;
        r2 = '$';
        r1 = "63\u0000UmQ0\u0011M";
        r0 = '#';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:52:0x01b0, code lost:
        r3[r2] = r1;
        r2 = '%';
        r1 = "\u001b\u0019<v";
        r0 = '$';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:53:0x01bb, code lost:
        r3[r2] = r1;
        r2 = '&';
        r1 = "\u000f\u00174rP\u000fC6iA\u0019Y";
        r0 = '%';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:54:0x01c6, code lost:
        r3[r2] = r1;
        r2 = '\'';
        r1 = "=\u0016!nJ\u000e\n/gQ\u0015\f;";
        r0 = '&';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:55:0x01d1, code lost:
        r3[r2] = r1;
        r2 = '(';
        r1 = "?\f;r@\u0012\u0017xCK\u001f\f1oK\u001b";
        r0 = '\'';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:56:0x01dc, code lost:
        r3[r2] = r1;
        r2 = ')';
        r1 = ",\u0011:rJ\u001f\f9C]\u001f\u0006%rL\u0013\ro";
        r0 = '(';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:57:0x01e7, code lost:
        r3[r2] = r1;
        r2 = '*';
        r1 = "?\u000b4tV\u0019\u0017";
        r0 = ')';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:58:0x01f2, code lost:
        r3[r2] = r1;
        r2 = '+';
        r1 = "$N\u0014vUQ(0\u007f";
        r0 = '*';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:59:0x01fd, code lost:
        r3[r2] = r1;
        r2 = ',';
        r1 = "\u001d\u0013%jL\u001f\u0002!oJ\u0012L?gV\u0013\r";
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
        r1 = "=\u00006cU\b";
        r0 = ',';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:61:0x0213, code lost:
        r3[r2] = r1;
        r2 = '.';
        r1 = "?\f;r@\u0012\u0017xJ@\u0012\u0004!n";
        r0 = '-';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:62:0x021e, code lost:
        r3[r2] = r1;
        r2 = '/';
        r1 = ">\u0002&oF\\";
        r0 = '.';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:63:0x0229, code lost:
        r3[r2] = r1;
        r2 = '0';
        r1 = "5,\u0010~F\u0019\u0013!oJ\u0012Y1cG\t\u0004";
        r0 = '/';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:64:0x0234, code lost:
        r3[r2] = r1;
        r2 = '1';
        r1 = "\t\u00119<";
        r0 = '0';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:65:0x023f, code lost:
        r3[r2] = r1;
        cn.jpush.android.util.s.z = r3;
        cn.jpush.android.util.s.a = false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:66:0x0246, code lost:
        return;
     */
    /* JADX WARN: Code restructure failed: missing block: B:67:0x0247, code lost:
        r9 = '|';
     */
    /* JADX WARN: Code restructure failed: missing block: B:68:0x024b, code lost:
        r9 = 'c';
     */
    /* JADX WARN: Code restructure failed: missing block: B:69:0x024f, code lost:
        r9 = 'U';
     */
    /* JADX WARN: Code restructure failed: missing block: B:6:0x0017, code lost:
        r10 = r5[r6];
     */
    /* JADX WARN: Code restructure failed: missing block: B:70:0x0253, code lost:
        r9 = 6;
     */
    /* JADX WARN: Code restructure failed: missing block: B:7:0x001b, code lost:
        switch((r8 % 5)) {
            case 0: goto L_0x0247;
            case 1: goto L_0x024b;
            case 2: goto L_0x024f;
            case 3: goto L_0x0253;
            default: goto L_0x001e;
        };
     */
    /* JADX WARN: Code restructure failed: missing block: B:8:0x001e, code lost:
        r9 = '%';
     */
    /* JADX WARN: Code restructure failed: missing block: B:9:0x0020, code lost:
        r5[r6] = (char) (r9 ^ r10);
        r6 = r8 + 1;
     */
    static {
        /*
            Method dump skipped, instructions count: 712
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.jpush.android.util.s.<clinit>():void");
    }

    public static int a(Context context, JSONObject jSONObject, boolean z2) {
        boolean z3;
        boolean z4;
        int i;
        String a2 = ah.a(2);
        new StringBuilder(z[49]).append(a2);
        ac.a();
        ac.a();
        if (ao.a(a2)) {
            z3 = false;
        } else if (ah.b(a2)) {
            if (!URLUtil.isHttpUrl(a2)) {
                ac.e();
                z3 = false;
            } else {
                z3 = true;
            }
        } else if (!URLUtil.isHttpsUrl(a2)) {
            ac.e();
            z3 = false;
        } else {
            z3 = true;
        }
        if (!z3) {
            return -1;
        }
        HttpURLConnection a3 = a(context, a2);
        a3.setConnectTimeout(30000);
        a3.setReadTimeout(30000);
        a3.setDoOutput(true);
        a3.setDoInput(true);
        a3.setUseCaches(false);
        if (Integer.parseInt(Build.VERSION.SDK) < 8) {
            System.setProperty(z[8], z[7]);
        }
        try {
            a3.setRequestMethod(z[34]);
        } catch (ProtocolException e) {
            e.printStackTrace();
        }
        String str = "";
        if (jSONObject != null) {
            str = jSONObject.toString();
        }
        if (ao.a(str)) {
            ac.b();
            return -2;
        }
        if (a3 == null) {
            ac.e();
            z4 = false;
        } else {
            a3.setRequestProperty(z[45], z[44]);
            a3.setRequestProperty(z[10], z[37]);
            a3.setRequestProperty(z[40], z[37]);
            a3.setRequestProperty(z[43], b.s(context));
            String a4 = str == null ? ah.a() : ah.a(str, 2);
            if (ao.a(a4)) {
                ac.b();
                z4 = false;
            } else {
                String c = ah.c(a4);
                if (ao.a(c)) {
                    ac.b();
                    z4 = false;
                } else {
                    a3.setRequestProperty(z[39], z[47] + c);
                    a3.setRequestProperty(z[42], z[29]);
                    z4 = true;
                }
            }
        }
        if (!z4) {
            ac.b();
            return -3;
        }
        try {
            try {
                byte[] bytes = str.getBytes(z[29]);
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                GZIPOutputStream gZIPOutputStream = new GZIPOutputStream(byteArrayOutputStream);
                gZIPOutputStream.write(bytes);
                gZIPOutputStream.close();
                byte[] byteArray = byteArrayOutputStream.toByteArray();
                byteArrayOutputStream.close();
                a3.setRequestProperty(z[46], String.valueOf(byteArray.length));
                OutputStream outputStream = a3.getOutputStream();
                outputStream.write(byteArray);
                outputStream.flush();
                outputStream.close();
                try {
                    try {
                        int responseCode = a3.getResponseCode();
                        new StringBuilder(z[38]).append(responseCode);
                        ac.b();
                        switch (responseCode) {
                            case 200:
                                if (a3 != null) {
                                    a3.disconnect();
                                }
                                i = 200;
                                break;
                            case 401:
                                ac.d();
                                if (a3 != null) {
                                    a3.disconnect();
                                }
                                i = 401;
                                break;
                            case 404:
                                if (a3 != null) {
                                    a3.disconnect();
                                }
                                i = 404;
                                break;
                            case 429:
                                if (a3 != null) {
                                    a3.disconnect();
                                }
                                i = 429;
                                break;
                            default:
                                if (responseCode / 100 != 5) {
                                    if (a3 != null) {
                                        a3.disconnect();
                                    }
                                    i = -5;
                                    break;
                                } else {
                                    if (a3 != null) {
                                        a3.disconnect();
                                    }
                                    i = 500;
                                    break;
                                }
                        }
                        return i;
                    } catch (IOException e2) {
                        new StringBuilder(z[48]).append(e2.getMessage());
                        ac.e();
                        if (a3 != null) {
                            a3.disconnect();
                        }
                        return -1;
                    }
                } catch (AssertionError e3) {
                    new StringBuilder(z[32]).append(e3.toString());
                    ac.e();
                    if (a3 != null) {
                        a3.disconnect();
                    }
                    return -1;
                } catch (ProtocolException e4) {
                    new StringBuilder(z[41]).append(e4.getMessage());
                    ac.e();
                    if (a3 != null) {
                        a3.disconnect();
                    }
                    return -1;
                }
            } catch (Throwable th) {
                if (a3 != null) {
                    a3.disconnect();
                }
                throw th;
            }
        } catch (UnsupportedEncodingException e5) {
            e5.printStackTrace();
            return -4;
        } catch (IOException e6) {
            e6.printStackTrace();
            return -4;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:148:0x0137 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:153:0x00c1 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:70:0x013c  */
    /* JADX WARN: Removed duplicated region for block: B:74:0x0145  */
    /* JADX WARN: Type inference failed for: r0v3 */
    /* JADX WARN: Type inference failed for: r4v1 */
    /* JADX WARN: Type inference failed for: r4v10, types: [java.lang.InterruptedException] */
    /* JADX WARN: Type inference failed for: r4v11 */
    /* JADX WARN: Type inference failed for: r4v18 */
    /* JADX WARN: Type inference failed for: r4v19 */
    /* JADX WARN: Type inference failed for: r4v2 */
    /* JADX WARN: Type inference failed for: r4v20 */
    /* JADX WARN: Type inference failed for: r4v3 */
    /* JADX WARN: Type inference failed for: r4v4 */
    /* JADX WARN: Type inference failed for: r4v5 */
    /* JADX WARN: Type inference failed for: r4v6 */
    /* JADX WARN: Type inference failed for: r4v7 */
    /* JADX WARN: Type inference failed for: r4v8 */
    /* JADX WARN: Type inference failed for: r4v9 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.lang.String a(java.lang.String r10, int r11, long r12) {
        /*
            Method dump skipped, instructions count: 667
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.jpush.android.util.s.a(java.lang.String, int, long):java.lang.String");
    }

    public static HttpURLConnection a(Context context, String str) {
        NetworkInfo activeNetworkInfo;
        String extraInfo;
        try {
            URL url = new URL(str);
            return (context.getPackageManager().checkPermission(z[14], context.getPackageName()) != 0 || (activeNetworkInfo = ((ConnectivityManager) context.getSystemService(z[19])).getActiveNetworkInfo()) == null || activeNetworkInfo.getType() == 1 || (extraInfo = activeNetworkInfo.getExtraInfo()) == null || (!extraInfo.equals(z[16]) && !extraInfo.equals(z[18]) && !extraInfo.equals(z[17]))) ? (HttpURLConnection) url.openConnection() : (HttpURLConnection) url.openConnection(new Proxy(Proxy.Type.HTTP, new InetSocketAddress(z[15], 80)));
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public static void a(HttpURLConnection httpURLConnection, boolean z2) {
        if (httpURLConnection == null) {
            ac.e();
            return;
        }
        if (z2) {
            try {
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                httpURLConnection.setRequestMethod(z[34]);
            } catch (ProtocolException e) {
                e.printStackTrace();
            }
        }
        httpURLConnection.setUseCaches(false);
        httpURLConnection.setRequestProperty(z[35], z[36]);
        httpURLConnection.setRequestProperty(z[33], z[29]);
        httpURLConnection.setConnectTimeout(30000);
        httpURLConnection.setReadTimeout(30000);
    }

    public static boolean a(String str) {
        return TextUtils.isEmpty(str) || str.equals(z[21]) || str.equals(z[22]) || str.equals(z[23]) || str.equals(z[20]);
    }

    public static byte[] a(String str, int i, long j, int i2) {
        byte[] bArr = null;
        for (int i3 = 0; i3 < 4 && (bArr = b(str, 5, 5000L)) == null; i3++) {
        }
        return bArr;
    }

    /* JADX WARN: Code restructure failed: missing block: B:21:0x0093, code lost:
        if (r3 == null) goto L_0x0098;
     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x0095, code lost:
        r3.close();
     */
    /* JADX WARN: Removed duplicated region for block: B:104:0x00ec A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:122:0x00b5 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:56:0x00f1  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x00f5  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static byte[] b(java.lang.String r12, int r13, long r14) {
        /*
            Method dump skipped, instructions count: 407
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.jpush.android.util.s.b(java.lang.String, int, long):byte[]");
    }
}
