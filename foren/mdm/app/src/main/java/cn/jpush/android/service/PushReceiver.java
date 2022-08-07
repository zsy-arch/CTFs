package cn.jpush.android.service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.NetworkInfo;
import android.net.Uri;
import android.support.v4.view.PointerIconCompat;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import cn.jpush.android.api.n;
import cn.jpush.android.data.a;
import cn.jpush.android.data.b;
import cn.jpush.android.data.c;
import cn.jpush.android.data.i;
import cn.jpush.android.e;
import cn.jpush.android.helpers.h;
import cn.jpush.android.helpers.k;
import cn.jpush.android.util.ac;
import cn.jpush.android.util.ao;
import cn.jpush.android.util.p;
import com.amap.api.services.core.AMapException;
import java.io.File;

/* loaded from: classes.dex */
public class PushReceiver extends BroadcastReceiver {
    public static c a;
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
            case 49: goto L_0x024b;
            case 50: goto L_0x0256;
            default: goto L_0x003c;
        };
     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x003c, code lost:
        r3[r2] = r1;
        r2 = 1;
        r1 = ":yeA%,d#\u000547s9D<=9\"E!<y?\u0005\u001b\u0016C\u0002m\u001c\u001aV\u001fb\u001a\u0017H\u0019n\u0016\u001c^\u001dn\u0011\u0006G\u0019d\r\u0000";
        r0 = 0;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x0044, code lost:
        r3[r2] = r1;
        r2 = 2;
        r1 = ":yeA%,d#\u000547s9D<=9\u0006x\u0012\u0006^\u000f";
        r0 = 1;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x004c, code lost:
        r3[r2] = r1;
        r2 = 3;
        r1 = "6y\u0019N6<~=Nut7";
        r0 = 2;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:19:0x0054, code lost:
        r3[r2] = r1;
        r2 = 4;
        r1 = "7x?t4,c$Y 7";
        r0 = 3;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x005c, code lost:
        r3[r2] = r1;
        r2 = 5;
        r1 = "*r%O0+^/";
        r0 = 4;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x0064, code lost:
        r3[r2] = r1;
        r2 = 6;
        r1 = "8g;b1";
        r0 = 5;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x006c, code lost:
        r3[r2] = r1;
        r2 = 7;
        r1 = ")v(@4>rq";
        r0 = 6;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x0074, code lost:
        r3[r2] = r1;
        r2 = '\b';
        r1 = "\u001ax%E0:c\"D;yd?J!<7(C47p.Ou-xk\u0006u";
        r0 = 7;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x007d, code lost:
        r3[r2] = r1;
        r2 = '\t';
        r1 = ":yeA%,d#\u000547s9D<=9\"E!<y?\u0005\u001b\u0016C\u0002m\u001c\u001aV\u001fb\u001a\u0017H\u0004{\u0010\u0017R\u000ft\u0005\u000bX\u0013r";
        r0 = '\b';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x0087, code lost:
        r3[r2] = r1;
        r2 = '\n';
        r1 = "\u0017xkj\u0016\r^\u0004e\n\u0017X\u001fb\u0013\u0010T\n\u007f\u001c\u0016Y\u0014d\u0005\u001cY\u000eou=r-B;<skB;yz*E<?r8_yyx;N;yc#Nu=r-J 5ckF40ykJ6-~=B! ";
        r0 = '\t';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x0092, code lost:
        r3[r2] = r1;
        r2 = 11;
        r1 = "7x\bD;7r(_</~?R";
        r0 = '\n';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x009d, code lost:
        r3[r2] = r1;
        r2 = '\f';
        r1 = "8y/Y:0seE0-9(D;79\bd\u001b\u0017R\b\u007f\u001c\u000f^\u001fr\n\u001a_\ne\u0012\u001c";
        r0 = 11;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x00a8, code lost:
        r3[r2] = r1;
        r2 = '\r';
        r1 = "-n;N";
        r0 = '\f';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:29:0x00b3, code lost:
        r3[r2] = r1;
        r2 = 14;
        r1 = "\u001ex?\u000b477.F%-nkE:-~-B68c\"D;u7/D;~ckX=6`kB!x";
        r0 = '\r';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x00be, code lost:
        r3[r2] = r1;
        r2 = 15;
        r1 = "8y/Y:0seB;-r%_{8t?B:79\u001ex\u0010\u000bH\u001by\u0010\nR\u0005\u007f";
        r0 = 14;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:31:0x00c9, code lost:
        r3[r2] = r1;
        r2 = 16;
        r1 = ";x/R";
        r0 = 15;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x00d4, code lost:
        r3[r2] = r1;
        r2 = 17;
        r1 = "*c9e\u0001 g.\u000bhy";
        r0 = 16;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x00df, code lost:
        r3[r2] = r1;
        r2 = 18;
        r1 = "6c#N'yy._\"6e \u000b&-v?Nut7";
        r0 = 17;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:34:0x00ea, code lost:
        r3[r2] = r1;
        r2 = 19;
        r1 = "=r)^2\u0006y$_<?~(J!0x%";
        r0 = 18;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:35:0x00f5, code lost:
        r3[r2] = r1;
        r2 = com.tencent.open.GameAppOperation.PIC_SYMBOLE;
        r1 = "8y/Y:0seB;-r%_{8t?B:79\u001db\u0010\u000e";
        r0 = 19;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:36:0x0100, code lost:
        r3[r2] = r1;
        r2 = 21;
        r1 = "S皓AD;\u000br8^8<?b!";
        r0 = com.tencent.open.GameAppOperation.PIC_SYMBOLE;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:37:0x010b, code lost:
        r3[r2] = r1;
        r2 = 22;
        r1 = "\nr%Ou;e$J1:v8_u-xkJ%)-k";
        r0 = 21;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:38:0x0116, code lost:
        r3[r2] = r1;
        r2 = 23;
        r1 = "8g;";
        r0 = 22;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:39:0x0121, code lost:
        r3[r2] = r1;
        r2 = 24;
        r1 = ":yeA%,d#\u000547s9D<=9\"E!<y?\u0005\u001b\u0016C\u0002m\u001c\u001aV\u001fb\u001a\u0017H\u0004{\u0010\u0017R\u000f";
        r0 = 23;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:40:0x012c, code lost:
        r3[r2] = r1;
        r2 = 25;
        r1 = "8t?B#0c2";
        r0 = 24;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:41:0x0137, code lost:
        r3[r2] = r1;
        r2 = 26;
        r1 = "8y/Y:0seB;-r%_{8t?B:79\u001bj\u0016\u0012V\fn\n\u000bR\u0006d\u0003\u001cS";
        r0 = 25;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:42:0x0142, code lost:
        r3[r2] = r1;
        r2 = 27;
        r1 = "\u0018T\u001fb\u001a\u0017H\u0005d\u0001\u0010Q\u0002h\u0014\r^\u0004e\n\u000bR\bn\u001c\u000fR\u000ft\u0005\u000bX\u0013ruy7%D!0q\"H4-~$E\n-n;Nud7";
        r0 = 26;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:43:0x014d, code lost:
        r3[r2] = r1;
        r2 = 28;
        r1 = ":yeA%,d#\u000547s9D<=9\"E!<y?\u0005\u001b\u0016C\u0002m\u001c\u001aV\u001fb\u001a\u0017H\u0002e\u0006\rV\u0007g\n\u001a[\u0002h\u001e\u001cS";
        r0 = 27;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:44:0x0158, code lost:
        r3[r2] = r1;
        r2 = 29;
        r1 = "S皓AD;\tv>X0q>A";
        r0 = 28;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:45:0x0163, code lost:
        r3[r2] = r1;
        r2 = 30;
        r1 = "4d,t<=";
        r0 = 29;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:46:0x016e, code lost:
        r3[r2] = r1;
        r2 = 31;
        r1 = "S]\u001b^&1^%_0+q*H0wx%{4,d.\u0003|";
        r0 = 30;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:47:0x0179, code lost:
        r3[r2] = r1;
        r2 = ' ';
        r1 = "c7\fN!yy$\u000b18c*\u000b3+x&\u000b<7c.E!w";
        r0 = 31;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:48:0x0184, code lost:
        r3[r2] = r1;
        r2 = '!';
        r1 = "8g;G<:v?B:78=E1wv%O'6~/\u0005%8t J2<:*Y61~=N";
        r0 = ' ';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:49:0x018f, code lost:
        r3[r2] = r1;
        r2 = '\"';
        r1 = "4r8X4>r";
        r0 = '!';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:4:0x0010, code lost:
        if (r5 <= 1) goto L_0x0012;
     */
    /* JADX WARN: Code restructure failed: missing block: B:50:0x019a, code lost:
        r3[r2] = r1;
        r2 = '#';
        r1 = "S皓AD;\u000br8^8<?b!:7G*^&<?b!";
        r0 = '\"';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:51:0x01a5, code lost:
        r3[r2] = r1;
        r2 = '$';
        r1 = "S\u001d杧揻祯厳圿彋叺戆卜犡恊丠冯珩;厚帨戆卜业佑凑珥w";
        r0 = '#';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:52:0x01b0, code lost:
        r3[r2] = r1;
        r2 = '%';
        r1 = "7r?\\:+|\u0002E36";
        r0 = '$';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:53:0x01bb, code lost:
        r3[r2] = r1;
        r2 = '&';
        r1 = "wg.Y80d8B:79\u0001{\u0000\n_\u0014f\u0010\nD\nl\u0010";
        r0 = '%';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:54:0x01c6, code lost:
        r3[r2] = r1;
        r2 = '\'';
        r1 = "8y/Y:0seB;-r%_{8t?B:79\u001bj\u0016\u0012V\fn\n\u0018S\u000fn\u0011";
        r0 = '&';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:55:0x01d1, code lost:
        r3[r2] = r1;
        r2 = '(';
        r1 = "7x?B30t*B:7H?R%<";
        r0 = '\'';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:56:0x01dc, code lost:
        r3[r2] = r1;
        r2 = ')';
        r1 = "-x*X!\rr3_";
        r0 = '(';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:57:0x01e7, code lost:
        r3[r2] = r1;
        r2 = '*';
        r1 = "S]\u001b^&1^%_0+q*H0wx%y0*b&N}p";
        r0 = ')';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:58:0x01f2, code lost:
        r3[r2] = r1;
        r2 = '+';
        r1 = "S]\u001b^&1^%_0+q*H0wx%y0*b&N}p\u001d\u0001{ *\u007f\u0002E!<e-J6<9$E\u00058b8N}p";
        r0 = '*';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:59:0x01fd, code lost:
        r3[r2] = r1;
        r2 = ',';
        r1 = "w7\u000fDu7x?C<7pe";
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
        r1 = "\u000br(N</r/\u000b;67*H!0x%\u000b<7c.E!yu9D4=t*X!w7\fB#<7>[u)e$H0*d\"E2w";
        r0 = ',';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:61:0x0213, code lost:
        r3[r2] = r1;
        r2 = '.';
        r1 = "8y/Y:0seB;-r%_{8t?B:79\td\u001a\rH\bd\u0018\t[\u000e\u007f\u0010\u001d";
        r0 = '-';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:62:0x021e, code lost:
        r3[r2] = r1;
        r2 = '/';
        r1 = "\u0013G>X=y廭该雭扅yD\u000f`u斯劷乁维说人砖亮误佥揱逖攃枷しy\u001dA棫洞剩圿A";
        r0 = '.';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:63:0x0229, code lost:
        r3[r2] = r1;
        r2 = '0';
        r1 = "\tb8C\u0007<t.B#<e";
        r0 = '/';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:64:0x0234, code lost:
        r3[r2] = r1;
        r2 = '1';
        r1 = "\u000br(N</r/\u000b;,{'\u000b<7c.E!yu9D4=t*X!w7\fB#<7>[u)e$H0*d\"E2w";
        r0 = '0';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:65:0x023f, code lost:
        r3[r2] = r1;
        r2 = '2';
        r1 = "无泂书沊杜豚甿";
        r0 = '1';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:66:0x024b, code lost:
        r3[r2] = r1;
        r2 = '3';
        r1 = "\u001er?\u000b\"+x%Lu=v?Ju*c9B;>7-Y:47\"E!<y?\u0011u";
        r0 = '2';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:67:0x0256, code lost:
        r3[r2] = r1;
        cn.jpush.android.service.PushReceiver.z = r3;
        cn.jpush.android.service.PushReceiver.a = null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:68:0x025d, code lost:
        return;
     */
    /* JADX WARN: Code restructure failed: missing block: B:69:0x025e, code lost:
        r9 = 'Y';
     */
    /* JADX WARN: Code restructure failed: missing block: B:6:0x0017, code lost:
        r10 = r5[r6];
     */
    /* JADX WARN: Code restructure failed: missing block: B:70:0x0262, code lost:
        r9 = 23;
     */
    /* JADX WARN: Code restructure failed: missing block: B:71:0x0266, code lost:
        r9 = 'K';
     */
    /* JADX WARN: Code restructure failed: missing block: B:72:0x026a, code lost:
        r9 = '+';
     */
    /* JADX WARN: Code restructure failed: missing block: B:7:0x001b, code lost:
        switch((r8 % 5)) {
            case 0: goto L_0x025e;
            case 1: goto L_0x0262;
            case 2: goto L_0x0266;
            case 3: goto L_0x026a;
            default: goto L_0x001e;
        };
     */
    /* JADX WARN: Code restructure failed: missing block: B:8:0x001e, code lost:
        r9 = 'U';
     */
    /* JADX WARN: Code restructure failed: missing block: B:9:0x0020, code lost:
        r5[r6] = (char) (r9 ^ r10);
        r6 = r8 + 1;
     */
    static {
        /*
            Method dump skipped, instructions count: 740
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.jpush.android.service.PushReceiver.<clinit>():void");
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        boolean z2;
        boolean z3 = false;
        if (intent == null) {
            ac.d(z[48], z[49]);
            return;
        }
        try {
            String action = intent.getAction();
            ac.b(z[48], z[3] + action);
            if (e.a(context.getApplicationContext()) && action != null) {
                if (action.equals(z[46])) {
                    p.c(context);
                    ServiceInterface.c(context, 500);
                } else if (action.equals(z[39])) {
                    String dataString = intent.getDataString();
                    if (dataString == null) {
                        ac.d(z[48], action + z[32]);
                    } else if (dataString.trim().length() <= 8 || !dataString.startsWith(z[7])) {
                        ac.d(z[48], z[51] + dataString);
                    } else {
                        String substring = dataString.substring(8);
                        String a2 = b.a(context, substring);
                        k.a(context, o.a(substring));
                        ServiceInterface.b(context.getApplicationContext(), substring);
                        try {
                            if (!TextUtils.isEmpty(a2)) {
                                String[] split = a2.split(",");
                                if (split.length > 0) {
                                    String str = split[0];
                                    k.a(str, 1002, context);
                                    String str2 = "";
                                    if (split.length >= 2) {
                                        str2 = split[1];
                                    }
                                    String str3 = "";
                                    if (split.length >= 3) {
                                        str3 = split[2];
                                    }
                                    ac.e();
                                    if (!str2.equals(z[4]) && !cn.jpush.android.util.b.a(context, substring, str2)) {
                                        ac.b();
                                        k.a(str, AMapException.CODE_AMAP_ENGINE_RESPONSE_ERROR, context);
                                    }
                                    if (ao.a(str3)) {
                                        str3 = str;
                                    }
                                    n.a(context, str3);
                                }
                            }
                        } catch (Exception e) {
                            e.getMessage();
                            ac.e();
                        }
                    }
                } else if (action.equals(z[26])) {
                    String dataString2 = intent.getDataString();
                    if (dataString2 == null) {
                        ac.d(z[48], action + z[32]);
                    } else if (dataString2.trim().length() <= 8 || !dataString2.startsWith(z[7])) {
                        ac.d(z[48], z[51] + dataString2);
                    } else {
                        String substring2 = dataString2.substring(8);
                        k.a(context, o.b(substring2));
                        ServiceInterface.b(context.getApplicationContext(), substring2);
                    }
                } else if (action.equals(z[15])) {
                    ServiceInterface.b(context);
                } else if (action.equals(z[28])) {
                    c cVar = (c) intent.getSerializableExtra(z[16]);
                    if (cVar != null && cVar.a()) {
                        k.a(cVar.c, PointerIconCompat.TYPE_VERTICAL_DOUBLE_ARROW, context);
                        Intent intent2 = new Intent();
                        intent2.addFlags(268435456);
                        intent2.setAction(z[20]);
                        intent2.setDataAndType(Uri.fromFile(new File(((i) cVar).P)), z[33]);
                        context.startActivity(intent2);
                    }
                } else if (action.startsWith(z[1])) {
                    int intExtra = intent.getIntExtra(z[40], 0);
                    new StringBuilder(z[27]).append(intExtra);
                    ac.b();
                    if (intExtra == 0) {
                        if (ServiceInterface.e(context)) {
                            ac.c();
                            return;
                        } else if (!cn.jpush.android.util.b.o(context)) {
                            ac.c();
                            return;
                        }
                    }
                    String stringExtra = intent.getStringExtra(z[34]);
                    if (ao.a(stringExtra)) {
                        ac.d(z[48], z[14]);
                        return;
                    }
                    a a3 = h.a(context, stringExtra, intent.getStringExtra(z[6]), intent.getStringExtra(z[5]), intent.getStringExtra(z[30]));
                    if (a3 == null) {
                        ac.d();
                        return;
                    }
                    a3.h = true;
                    h.a(context, a3);
                    abortBroadcast();
                } else if (action.startsWith(z[9])) {
                    if (intent.getBooleanExtra(z[19], false)) {
                        String stringExtra2 = intent.getStringExtra(z[25]);
                        int intExtra2 = intent.getIntExtra(z[13], -1);
                        if (intExtra2 == -1) {
                            String stringExtra3 = intent.getStringExtra(z[41]);
                            Toast makeText = Toast.makeText(context, stringExtra2, 1);
                            View view = makeText.getView();
                            if (view instanceof LinearLayout) {
                                View childAt = ((LinearLayout) view).getChildAt(0);
                                if (childAt instanceof TextView) {
                                    TextView textView = (TextView) childAt;
                                    if (!ao.a(stringExtra3)) {
                                        textView.setText(stringExtra3);
                                    }
                                    textView.setTextSize(13.0f);
                                }
                            }
                            makeText.show();
                        } else if (!ao.a(stringExtra2)) {
                            String str4 = z[47];
                            String str5 = z[21];
                            String str6 = z[50];
                            String str7 = z[42];
                            switch (intExtra2) {
                                case 1:
                                    str5 = z[29];
                                    str6 = z[50];
                                    str7 = z[31];
                                    break;
                                case 2:
                                    str5 = z[35];
                                    str6 = z[50];
                                    str7 = z[43];
                                    break;
                            }
                            StringBuilder sb = new StringBuilder();
                            sb.append(str4).append(stringExtra2).append(str5).append(str6).append(str7).append(z[36]);
                            SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(sb);
                            int length = str4.length();
                            int length2 = stringExtra2.length() + length;
                            spannableStringBuilder.setSpan(new ForegroundColorSpan(-13527041), length, length2, 33);
                            int i = length2 + 2;
                            int length3 = (str5.length() + i) - 2;
                            spannableStringBuilder.setSpan(new ForegroundColorSpan(-13527041), i, length3, 33);
                            int length4 = str6.length() + length3;
                            spannableStringBuilder.setSpan(new ForegroundColorSpan(-13527041), length4, str7.length() + length4, 33);
                            Toast makeText2 = Toast.makeText(context, stringExtra2, 1);
                            View view2 = makeText2.getView();
                            if (view2 instanceof LinearLayout) {
                                View childAt2 = ((LinearLayout) view2).getChildAt(0);
                                if (childAt2 instanceof TextView) {
                                    TextView textView2 = (TextView) childAt2;
                                    if (spannableStringBuilder != null) {
                                        textView2.setText(spannableStringBuilder);
                                    }
                                    textView2.setTextSize(13.0f);
                                }
                            }
                            makeText2.show();
                        }
                    } else {
                        String stringExtra4 = intent.getStringExtra(z[2]);
                        if (!ao.a(stringExtra4)) {
                            String stringExtra5 = intent.getStringExtra(z[0]);
                            new StringBuilder(z[17]).append(stringExtra5);
                            ac.b();
                            if (stringExtra5 != null && "1".equals(stringExtra5)) {
                                z3 = true;
                            }
                            if (true != z3) {
                                k.a(stringExtra4, 1000, context);
                            }
                        }
                        if (!cn.jpush.android.util.b.a(context, z[24], true)) {
                            ac.b(z[48], z[10]);
                            cn.jpush.android.util.b.e(context);
                            return;
                        }
                        Intent intent3 = new Intent(z[24]);
                        intent3.putExtras(intent.getExtras());
                        String stringExtra6 = intent.getStringExtra(z[23]);
                        if (ao.a(stringExtra6)) {
                            stringExtra6 = context.getPackageName();
                        }
                        intent3.addCategory(stringExtra6);
                        new StringBuilder(z[22]).append(stringExtra6);
                        ac.c();
                        context.sendBroadcast(intent3, stringExtra6 + z[38]);
                    }
                } else if (action.equalsIgnoreCase(z[12])) {
                    NetworkInfo networkInfo = (NetworkInfo) intent.getParcelableExtra(z[37]);
                    if (networkInfo == null) {
                        ac.d();
                        return;
                    }
                    new StringBuilder(z[8]).append(networkInfo.toString());
                    ac.b();
                    if (2 == networkInfo.getType() || 3 == networkInfo.getType()) {
                        ac.b();
                        return;
                    }
                    if (intent.getBooleanExtra(z[11], false)) {
                        ac.b();
                        b.b = false;
                        ServiceInterface.g(context);
                        z2 = false;
                    } else if (NetworkInfo.State.CONNECTED == networkInfo.getState()) {
                        ac.b();
                        ServiceInterface.f(context);
                        b.b = true;
                        if (DownloadService.a()) {
                            DownloadService.a(context);
                            z2 = true;
                        } else {
                            z2 = true;
                        }
                    } else if (NetworkInfo.State.DISCONNECTED == networkInfo.getState()) {
                        ac.b();
                        b.b = false;
                        ServiceInterface.g(context);
                        z2 = false;
                    } else {
                        new StringBuilder(z[18]).append(networkInfo.getState()).append(z[44]);
                        z2 = false;
                        ac.b();
                    }
                    cn.jpush.android.util.b.a(context, z2);
                }
            }
        } catch (NullPointerException e2) {
            ac.d(z[48], z[45]);
        }
    }
}
