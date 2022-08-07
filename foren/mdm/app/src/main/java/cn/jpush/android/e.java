package cn.jpush.android;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import cn.jpush.android.api.c;
import cn.jpush.android.api.l;
import cn.jpush.android.helpers.m;
import cn.jpush.android.service.PushService;
import cn.jpush.android.service.ServiceInterface;
import cn.jpush.android.util.ac;
import cn.jpush.android.util.b;
import cn.jpush.android.util.i;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: classes.dex */
public final class e {
    public static boolean a;
    public static int b;
    public static String c;
    public static String d;
    public static Context e;
    public static String f;
    public static long g;
    public static String h;
    public static int i;
    public static String j;
    public static boolean k;
    public static boolean l;
    public static boolean m;
    public static boolean n;
    public static b o;
    private static AtomicBoolean p;
    private static ServiceConnection q;
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
            default: goto L_0x003c;
        };
     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x003c, code lost:
        r3[r2] = r1;
        r2 = 1;
        r1 = "*d|5\u0006'Xf$\u000f\f";
        r0 = 0;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x0044, code lost:
        r3[r2] = r1;
        r2 = 2;
        r1 = "\u0013[_#\u001c\u0013]f(T";
        r0 = 1;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x004c, code lost:
        r3[r2] = r1;
        r2 = 3;
        r1 = ")Z\u007f'\u0002\tP)'\u001e\u0010\u007fl?NZ\u0014";
        r0 = 2;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:19:0x0054, code lost:
        r3[r2] = r1;
        r2 = 4;
        r1 = "\rQ}'\n\u0001@h|N*d|5\u0006@Uy6%\u0005M)kN\u000e[}f\n\u0005R`(\u000b\u0004\u0014`(N\rUg/\b\u0005G}";
        r0 = 3;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x005c, code lost:
        r3[r2] = r1;
        r2 = 5;
        r1 = "\rQ}'\n\u0001@h|N\u0003\\h(\u0000\u0005X)kN\u000e[}f\n\u0005R`(\u000b\u0004\u0014`(N\rUg/\b\u0005G}";
        r0 = 4;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x0064, code lost:
        r3[r2] = r1;
        r2 = 6;
        r1 = "\rQ}'\n\u0001@h|N\u0001Dy\r\u000b\u0019\u0014$f";
        r0 = 5;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x006c, code lost:
        r3[r2] = r1;
        r2 = 7;
        r1 = "5Zl>\u001e\u0005W}#\nZ\u0014o'\u0007\fQmf\u001a\u000f\u0014n#\u001a@W|4\u001c\u0005Z}f\u000f\u0010De/\r\u0001@`)\u0000@]g \u0001";
        r0 = 6;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x0074, code lost:
        r3[r2] = r1;
        r2 = '\b';
        r1 = "*d\\\u0015&?wA\u0007 .qE";
        r0 = 7;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x007d, code lost:
        r3[r2] = r1;
        r2 = '\t';
        r1 = "L\u0014Y*\u000b\u0001Glf\t\u0005@)?\u0001\u0015F)\u0007\u001e\u0010_l?N\u0006Ff+N*d|5\u0006@Cl$N\u0003[g5\u0001\fQ(";
        r0 = '\b';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x0087, code lost:
        r3[r2] = r1;
        r2 = '\n';
        r1 = ".{)+\u000b\u0014U)\"\u000f\u0014U)\"\u000b\u0006]g#\n@]gf\u0003\u0001Z` \u000b\u0013@'";
        r0 = '\t';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x0092, code lost:
        r3[r2] = r1;
        r2 = 11;
        r1 = "\rQ}'\n\u0001@h|N#Ugf\u0000\u000f@)!\u000b\u0014\u0014d#\u001a\u0001ph2\u000f@R{)\u0003@uy6\u0002\tWh2\u0007\u000fZ@(\b\u000f";
        r0 = '\n';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x009d, code lost:
        r3[r2] = r1;
        r2 = '\f';
        r1 = "\rQ}'\n\u0001@h|N\u0003\\h(\u0000\u0005X)kN";
        r0 = 11;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x00a8, code lost:
        r3[r2] = r1;
        r2 = '\r';
        r1 = "*d\\\u0015&?uY\u0016%%m";
        r0 = '\f';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:29:0x00b3, code lost:
        r3[r2] = r1;
        r2 = 14;
        r1 = "\nU\u007f'@\u000eQ}h\u001e\u0012Qo#\u001c)d\u007fp/\u0004P{#\u001d\u0013Qz";
        r0 = '\r';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x00be, code lost:
        r3[r2] = r1;
        r2 = 15;
        r1 = "*d|5\u0006@Wh(\u0000\u000f@)$\u000b@]g/\u001a\tUe/\u0014\u0005P)%\u0001\rDe#\u001a\u0005Xpf\n\u0015Q)2\u0001@z\\\n\"@Uy6'\u000eRfh";
        r0 = 14;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:31:0x00c9, code lost:
        r3[r2] = r1;
        r2 = 16;
        r1 = "\rQ}'\n\u0001@h|N#Ugf\u0000\u000f@)!\u000b\u0014\u0014H6\u001e\f]j'\u001a\t[gf\u0007\u0003[gjN\u0019[|f\u0019\tXef\f\u0005\u0014g)\u001a@Uk*\u000b@@ff\u001d\b[~f\u0000\u000f@` \u0007\u0003U}/\u0001\u000e\u0014m3\u000b@@ff\u001a\bQ)'\u001e\u0010X`%\u000f\u0014]f(N\tWf(N\tG)(\u001b\fX'";
        r0 = 15;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x00d4, code lost:
        r3[r2] = r1;
        r2 = 17;
        r1 = "\u0006Ue5\u000b";
        r0 = 16;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x00df, code lost:
        r3[r2] = r1;
        r2 = 18;
        r1 = ".{)0\u000b\u0012G`)\u0000#[m#N\u000fF)0\u000b\u0012G`)\u0000.Ud#N\u0004Qo/\u0000\u0005P)/\u0000@Yh(\u0007\u0006Qz2@";
        r0 = 17;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:34:0x00ea, code lost:
        r3[r2] = r1;
        r2 = 19;
        r1 = "2Qd)\u001a\u0005\u0014Z#\u001c\u0016]j#N\u0002]g\"N\u0006U`*\u000b\u0004\u0014j'\u001b\u0013Qmf\f\u0019\u0014Z#\r\u0015F`2\u0017%Lj#\u001e\u0014]f(O";
        r0 = 18;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:35:0x00f5, code lost:
        r3[r2] = r1;
        r2 = com.tencent.open.GameAppOperation.PIC_SYMBOLE;
        r1 = "\u0014F|#";
        r0 = 19;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:36:0x0100, code lost:
        r3[r2] = r1;
        r2 = 21;
        r1 = "\nU\u007f'@\u000eQ}h\u001e\u0012Qo#\u001c)d\u007fr=\u0014Uj-";
        r0 = com.tencent.open.GameAppOperation.PIC_SYMBOLE;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:37:0x010b, code lost:
        r3[r2] = r1;
        r2 = 22;
        r1 = "\u0003Fh5\u0006?Xf!";
        r0 = 21;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:38:0x0116, code lost:
        r3[r2] = r1;
        r2 = 23;
        r1 = "*d|5\u0006@\u001az)N\u0006]e#N\u0004[)(\u0001\u0014\u0014d'\u001a\u0003\\)\f>\u0015Gaf@\nU{f\b\tXlf\u0007\u000e\u0014}.\u000b@D{)\u0004\u0005W}jN&U`*\u000b\u0004\u0014})N\tZ`2N*d|5\u0006";
        r0 = 22;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:39:0x0121, code lost:
        r3[r2] = r1;
        cn.jpush.android.e.z = r3;
        cn.jpush.android.e.a = false;
        cn.jpush.android.e.p = new java.util.concurrent.atomic.AtomicBoolean(false);
        cn.jpush.android.e.g = 0;
        cn.jpush.android.e.h = "";
        cn.jpush.android.e.k = false;
        cn.jpush.android.e.l = false;
        cn.jpush.android.e.m = false;
        cn.jpush.android.e.n = true;
        cn.jpush.android.e.q = new cn.jpush.android.f();
     */
    /* JADX WARN: Code restructure failed: missing block: B:40:0x014b, code lost:
        return;
     */
    /* JADX WARN: Code restructure failed: missing block: B:41:0x014c, code lost:
        r9 = '`';
     */
    /* JADX WARN: Code restructure failed: missing block: B:42:0x0150, code lost:
        r9 = '4';
     */
    /* JADX WARN: Code restructure failed: missing block: B:43:0x0154, code lost:
        r9 = '\t';
     */
    /* JADX WARN: Code restructure failed: missing block: B:44:0x0158, code lost:
        r9 = 'F';
     */
    /* JADX WARN: Code restructure failed: missing block: B:4:0x0010, code lost:
        if (r5 <= 1) goto L_0x0012;
     */
    /* JADX WARN: Code restructure failed: missing block: B:5:0x0012, code lost:
        r8 = r6;
        r5 = r1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:6:0x0017, code lost:
        r10 = r5[r6];
     */
    /* JADX WARN: Code restructure failed: missing block: B:7:0x001b, code lost:
        switch((r8 % 5)) {
            case 0: goto L_0x014c;
            case 1: goto L_0x0150;
            case 2: goto L_0x0154;
            case 3: goto L_0x0158;
            default: goto L_0x001e;
        };
     */
    /* JADX WARN: Code restructure failed: missing block: B:8:0x001e, code lost:
        r9 = 'n';
     */
    /* JADX WARN: Code restructure failed: missing block: B:9:0x0020, code lost:
        r5[r6] = (char) (r9 ^ r10);
        r6 = r8 + 1;
     */
    static {
        /*
            Method dump skipped, instructions count: 410
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.jpush.android.e.<clinit>():void");
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x002c  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x001a A[ORIG_RETURN, RETURN] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static boolean a() {
        /*
            r0 = 1
            r1 = 0
            int r3 = cn.jpush.android.service.PushProtocol.GetSdkVersion()     // Catch: UnsatisfiedLinkError -> 0x001b
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch: UnsatisfiedLinkError -> 0x002e
            java.lang.String[] r4 = cn.jpush.android.e.z     // Catch: UnsatisfiedLinkError -> 0x002e
            r5 = 2
            r4 = r4[r5]     // Catch: UnsatisfiedLinkError -> 0x002e
            r2.<init>(r4)     // Catch: UnsatisfiedLinkError -> 0x002e
            r2.append(r3)     // Catch: UnsatisfiedLinkError -> 0x002e
            cn.jpush.android.util.ac.a()     // Catch: UnsatisfiedLinkError -> 0x002e
        L_0x0016:
            r2 = 200(0xc8, float:2.8E-43)
            if (r3 < r2) goto L_0x002c
        L_0x001a:
            return r0
        L_0x001b:
            r2 = move-exception
            r3 = r1
        L_0x001d:
            java.lang.String[] r4 = cn.jpush.android.e.z
            r4 = r4[r0]
            java.lang.String[] r5 = cn.jpush.android.e.z
            r5 = r5[r1]
            cn.jpush.android.util.ac.e(r4, r5)
            r2.printStackTrace()
            goto L_0x0016
        L_0x002c:
            r0 = r1
            goto L_0x001a
        L_0x002e:
            r2 = move-exception
            goto L_0x001d
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.jpush.android.e.a():boolean");
    }

    public static synchronized boolean a(Context context) {
        boolean z2 = false;
        synchronized (e.class) {
            if (p.get()) {
                z2 = true;
            } else {
                ac.b();
                c.a().a(context.getApplicationContext());
                i.p(context.getApplicationContext());
                m.a(context);
                if (!a()) {
                    ac.e(z[1], z[23]);
                } else {
                    c = context.getPackageName();
                    e = context.getApplicationContext();
                    g = a.y();
                    h = a.B();
                    ApplicationInfo c2 = c(context);
                    if (c2 == null) {
                        ac.e(z[1], z[15]);
                    } else {
                        b = c2.icon;
                        if (b == 0) {
                            ac.e(z[1], z[16]);
                        }
                        d = context.getPackageManager().getApplicationLabel(c2).toString();
                        try {
                            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
                            i = packageInfo.versionCode;
                            String str = packageInfo.versionName;
                            j = str;
                            if (str.length() > 30) {
                                j = j.substring(0, 30);
                            }
                        } catch (Exception e2) {
                            ac.b(z[1], z[18]);
                        }
                        if (b(context)) {
                            if (Build.VERSION.SDK_INT >= 14 && (context instanceof Application)) {
                                boolean g2 = b.g(context);
                                l.a = g2;
                                if (g2) {
                                    l.a((Application) context.getApplicationContext());
                                }
                            }
                            if (Build.VERSION.SDK_INT == 8) {
                                System.setProperty(z[21], z[20]);
                                System.setProperty(z[14], z[17]);
                            }
                            p.set(true);
                            Context applicationContext = context.getApplicationContext();
                            if (o != null) {
                                ac.b();
                            } else {
                                Intent intent = new Intent();
                                intent.setClass(applicationContext, PushService.class);
                                try {
                                    if (applicationContext.bindService(intent, q, 1)) {
                                        ac.a();
                                    } else {
                                        ac.a();
                                    }
                                } catch (SecurityException e3) {
                                    ac.d(z[1], z[19]);
                                }
                            }
                            ServiceInterface.a(context.getApplicationContext(), z[22], "");
                            z2 = true;
                        }
                    }
                }
            }
        }
        return z2;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v0 */
    /* JADX WARN: Type inference failed for: r1v1 */
    /* JADX WARN: Type inference failed for: r1v13 */
    /* JADX WARN: Type inference failed for: r1v14 */
    /* JADX WARN: Type inference failed for: r1v3 */
    /* JADX WARN: Type inference failed for: r1v6 */
    /* JADX WARN: Type inference failed for: r1v8 */
    /* JADX WARN: Type inference failed for: r1v9 */
    /* JADX WARN: Unknown variable types count: 1 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static boolean b(android.content.Context r6) {
        /*
            Method dump skipped, instructions count: 259
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.jpush.android.e.b(android.content.Context):boolean");
    }

    private static ApplicationInfo c(Context context) {
        try {
            return context.getPackageManager().getApplicationInfo(context.getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e2) {
            ac.b(z[1], z[7], e2);
            return null;
        }
    }
}
