package cn.jpush.android.api;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.view.PointerIconCompat;
import android.text.TextUtils;
import android.widget.RemoteViews;
import cn.jpush.android.a;
import cn.jpush.android.data.c;
import cn.jpush.android.data.m;
import cn.jpush.android.e;
import cn.jpush.android.helpers.d;
import cn.jpush.android.helpers.k;
import cn.jpush.android.service.PushReceiver;
import cn.jpush.android.service.PushService;
import cn.jpush.android.util.ac;
import cn.jpush.android.util.ao;
import cn.jpush.android.util.b;
import cn.jpush.android.util.p;
import cn.jpush.android.util.r;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.UUID;
import java.util.zip.Adler32;

/* loaded from: classes.dex */
public final class n {
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
            default: goto L_0x003c;
        };
     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x003c, code lost:
        r3[r2] = r1;
        r2 = 1;
        r1 = "%z)MR-pc^M4:\u0003PI-r$\\\\0}\"Q";
        r0 = 0;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x0044, code lost:
        r3[r2] = r1;
        r2 = 2;
        r1 = "'zcUM1g%\u0011\\*p?PT :\u0003pi\rR\u0004||\u0010]\u0002qb\rP";
        r0 = 1;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x004c, code lost:
        r3[r2] = r1;
        r2 = 3;
        r1 = ")a!KT\u001b`4OX";
        r0 = 2;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:19:0x0054, code lost:
        r3[r2] = r1;
        r2 = 4;
        r1 = "%d=";
        r0 = 3;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x005c, code lost:
        r3[r2] = r1;
        r2 = 5;
        r1 = "'zcUM1g%\u0011\\*p?PT :$QI!z9\u0011s\u000b@\u0004yt\u0007U\u0019vr\nK\u0002ox\nQ\t";
        r0 = 4;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x0064, code lost:
        r3[r2] = r1;
        r2 = 6;
        r1 = "'zcUM1g%\u0011\\*p?PT :$QI!z9\u0011p\u0011X\u0019vb\u0014F\u0002|x\u0017G";
        r0 = 5;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x006c, code lost:
        r3[r2] = r1;
        r2 = 7;
        r1 = "'zcUM1g%\u0011\\*p?PT :\u0003pi\rR\u0004||\u0010]\u0002qb\u0007[\u0003kx\n@\u0012kt\u0010X\b";
        r0 = 6;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x0074, code lost:
        r3[r2] = r1;
        r2 = '\b';
        r1 = "'zcUM1g%\u0011\\*p?PT :\u0000lz\u001b]\t";
        r0 = 7;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x007d, code lost:
        r3[r2] = r1;
        r2 = '\t';
        r1 = "'zcUM1g%\u0011\\*p?PT :\bgi\u0016U";
        r0 = '\b';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x0087, code lost:
        r3[r2] = r1;
        r2 = '\n';
        r1 = "'zcUM1g%\u0011\\*p?PT :\u0003pi\rR\u0004||\u0010]\u0002qb\u0000Q\u001bzq\u000bD\bmb\u0005F\n\u000f";
        r0 = '\t';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x0092, code lost:
        r3[r2] = r1;
        r2 = 11;
        r1 = "*{9V[-w,KT+z";
        r0 = '\n';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x009d, code lost:
        r3[r2] = r1;
        r2 = '\f';
        r1 = "\n{9V[-w,KT+z\u0005ZQ4q?";
        r0 = 11;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x00a8, code lost:
        r3[r2] = r1;
        r2 = '\r';
        r1 = "*{9V[-w,KT+z\u0012VY";
        r0 = '\f';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:29:0x00b3, code lost:
        r3[r2] = r1;
        r2 = 14;
        r1 = "'zcUM1g%\u0011\\*p?PT :\u0003pi\rR\u0004||\u0010]\u0002qb\u0010M\u001dz";
        r0 = '\r';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x00be, code lost:
        r3[r2] = r1;
        r2 = 15;
        r1 = "'zcUM1g%\u0011\\*p?PT :\fsx\u0016@";
        r0 = 14;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:31:0x00c9, code lost:
        r3[r2] = r1;
        r2 = 16;
        r1 = " q!VK!f4\u001fO-w%\u001fM1g%\u001fI=d(\u0005\u001d";
        r0 = 15;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x00d4, code lost:
        r3[r2] = r1;
        r2 = 17;
        r1 = "\u0003{9\u001fs\u0011X\u0001\u001fS+`$YT'u9VR*:mxT2qmJMd`\"\u001fN,{:\u0011";
        r0 = 16;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x00df, code lost:
        r3[r2] = r1;
        r2 = 18;
        r1 = "'zcUM1g%\u0011\\*p?PT :$QI!z9\u0011s\u000b@\u0004yt\u0007U\u0019vr\nK\u0002ox\nQ\t`m\u0016[\u0015f\u0013";
        r0 = 17;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:34:0x00ea, code lost:
        r3[r2] = r1;
        r2 = 19;
        r1 = "7`,KX\u001bv,Mb-y,XX\u001bb$ZJ";
        r0 = 18;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:35:0x00f5, code lost:
        r3[r2] = r1;
        r2 = com.tencent.open.GameAppOperation.PIC_SYMBOLE;
        r1 = "\u00160!^D+a9";
        r0 = 19;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:36:0x0100, code lost:
        r3[r2] = r1;
        r2 = 21;
        r1 = ")q>L\\#q\u0012VP%s(`N0u9Zb&u?`Q%m\"JI";
        r0 = com.tencent.open.GameAppOperation.PIC_SYMBOLE;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:37:0x010b, code lost:
        r3[r2] = r1;
        r2 = 22;
        r1 = "\u00160$[";
        r0 = 21;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:38:0x0116, code lost:
        r3[r2] = r1;
        r2 = 23;
        r1 = "%w9VR*..SX%z\u0003PI-r$\\\\0}\"Q\u001di4#PI-r$\\\\0}\"Qt .";
        r0 = 22;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:39:0x0121, code lost:
        r3[r2] = r1;
        r2 = 24;
        r1 = ".d8LU\u001bz\"KT\"}.^I-{#`T'{#";
        r0 = 23;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:40:0x012c, code lost:
        r3[r2] = r1;
        r2 = 25;
        r1 = "\u00160)M\\3u/SX";
        r0 = 24;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:41:0x0137, code lost:
        r3[r2] = r1;
        r2 = 26;
        r1 = "%w9VR*..SX%z\u0003PI-r$\\\\0}\"Q\u001di4 ZN7u*Zt .";
        r0 = 25;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:42:0x0142, code lost:
        r3[r2] = r1;
        r2 = 27;
        r1 = "'zcUM1g%\u0011\\*p?PT :\u0005kp\bK\u001fzn";
        r0 = 26;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:43:0x014d, code lost:
        r3[r2] = r1;
        r2 = 28;
        r1 = "'zcUM1g%\u0011\\*p?PT :$QI!z9\u0011s\u000b@\u0004yt\u0007U\u0019vr\nK\u001fz~\u0001]\u001bzy";
        r0 = 27;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:44:0x0158, code lost:
        r3[r2] = r1;
        r2 = 29;
        r1 = "'zcUM1g%\u0011\\*p?PT :\u0005kp\bK\u001d~i\f";
        r0 = 28;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:45:0x0163, code lost:
        r3[r2] = r1;
        r2 = 30;
        r1 = "\"}!Z\u0007k;";
        r0 = 29;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:46:0x016e, code lost:
        r3[r2] = r1;
        r2 = 31;
        r1 = ",`9O\u0007k;";
        r0 = 30;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:47:0x0179, code lost:
        r3[r2] = r1;
        r2 = ' ';
        r1 = "jd(MP-g>VR*:\u0007oh\u0017\\\u0012rx\u0017G\fxx";
        r0 = 31;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:48:0x0184, code lost:
        r3[r2] = r1;
        r2 = '!';
        r1 = "\u0017q#[\u001d4a>W\u001d6q.ZT2q)\u001f_6{,[^%g9\u001fI+4)ZK!x\"OX64)Z[-z([\u001d6q.ZT2q?";
        r0 = ' ';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:49:0x018f, code lost:
        r3[r2] = r1;
        r2 = '\"';
        r1 = "%w9VR*..SX%z\fSQ\n{9V[-w,KT+zm\u0012\u001d)q>L\\#q\u0004[\u0007";
        r0 = '!';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:4:0x0010, code lost:
        if (r5 <= 1) goto L_0x0012;
     */
    /* JADX WARN: Code restructure failed: missing block: B:50:0x019a, code lost:
        r3[r2] = r1;
        r2 = '#';
        r1 = "4u?^P!`(M\u001d6q>kD4qmPOdr$ZQ Z,RX74(MO+fc";
        r0 = '\"';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:51:0x01a5, code lost:
        r3[r2] = r1;
        r2 = '$';
        r1 = "jF";
        r0 = '#';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:52:0x01b0, code lost:
        r3[r2] = r1;
        cn.jpush.android.api.n.z = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:53:0x01b4, code lost:
        return;
     */
    /* JADX WARN: Code restructure failed: missing block: B:54:0x01b5, code lost:
        r9 = 'D';
     */
    /* JADX WARN: Code restructure failed: missing block: B:55:0x01b9, code lost:
        r9 = com.tencent.open.GameAppOperation.PIC_SYMBOLE;
     */
    /* JADX WARN: Code restructure failed: missing block: B:56:0x01bd, code lost:
        r9 = 'M';
     */
    /* JADX WARN: Code restructure failed: missing block: B:57:0x01c1, code lost:
        r9 = '?';
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
            case 0: goto L_0x01b5;
            case 1: goto L_0x01b9;
            case 2: goto L_0x01bd;
            case 3: goto L_0x01c1;
            default: goto L_0x001e;
        };
     */
    /* JADX WARN: Code restructure failed: missing block: B:8:0x001e, code lost:
        r9 = '=';
     */
    /* JADX WARN: Code restructure failed: missing block: B:9:0x0020, code lost:
        r5[r6] = (char) (r9 ^ r10);
        r6 = r8 + 1;
     */
    static {
        /*
            Method dump skipped, instructions count: 542
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.jpush.android.api.n.<clinit>():void");
    }

    public static int a(int i) {
        int i2;
        switch (i) {
            case -1:
                try {
                    i2 = a(z[25], new String[]{z[24]}).get(z[24]).intValue();
                } catch (Exception e) {
                    i2 = 0;
                    ac.e();
                }
                if (i2 <= 0) {
                    return 17301618;
                }
                return i2;
            case 0:
                return 17301647;
            case 1:
            default:
                return 17301586;
            case 2:
                return 17301618;
            case 3:
                return 17301567;
        }
    }

    public static int a(c cVar, int i) {
        String str = cVar.c;
        if (!ao.a(cVar.d)) {
            str = cVar.d;
        }
        return a(str, i);
    }

    private static int a(String str, int i) {
        if (TextUtils.isEmpty(str)) {
            ac.b();
            return 0;
        }
        try {
            return Integer.valueOf(str).intValue();
        } catch (Exception e) {
            ac.d();
            Adler32 adler32 = new Adler32();
            adler32.update(str.getBytes());
            int value = (int) adler32.getValue();
            if (value < 0) {
                value = Math.abs(value);
            }
            int i2 = value + (13889152 * i);
            return i2 < 0 ? Math.abs(i2) : i2;
        }
    }

    public static Notification a(Context context, int i, Intent intent, c cVar, boolean z2, boolean z3) {
        int i2;
        if (z2) {
            ac.b();
            try {
                i2 = context.getPackageManager().getPackageInfo(context.getPackageName(), 256).applicationInfo.icon;
            } catch (PackageManager.NameNotFoundException e) {
                i2 = -1;
                ac.g();
            }
        } else {
            i2 = -1;
        }
        if (i2 < 0) {
            ac.d();
            return null;
        }
        PendingIntent broadcast = z3 ? PendingIntent.getBroadcast(context, i, intent, 134217728) : PendingIntent.getActivity(context, i, intent, 134217728);
        if (Build.VERSION.SDK_INT >= 11) {
            Notification.Builder ticker = new Notification.Builder(context.getApplicationContext()).setWhen(System.currentTimeMillis()).setSmallIcon(i2).setTicker(cVar.t);
            if (cVar.h) {
                ticker.setDefaults(3);
                if (b.p(context)) {
                    ticker.setDefaults(0);
                }
            }
            if (!ao.a(cVar.A)) {
                try {
                    Bitmap decodeFile = BitmapFactory.decodeFile(cVar.A);
                    if (decodeFile != null) {
                        Integer num = a(z[22], new String[]{z[19]}).get(z[19]);
                        Integer num2 = a(z[20], new String[]{z[21]}).get(z[21]);
                        if (num == null || num2 == null || num.intValue() <= 0 || num2.intValue() <= 0) {
                            ac.d();
                            return null;
                        }
                        RemoteViews remoteViews = new RemoteViews(context.getPackageName(), num2.intValue());
                        remoteViews.setImageViewBitmap(num.intValue(), decodeFile);
                        ticker.setContent(remoteViews);
                        ticker.setContentIntent(broadcast);
                    } else {
                        ac.d();
                        return null;
                    }
                } catch (Exception e2) {
                    ac.h();
                    return null;
                }
            } else {
                ticker.setContentTitle(cVar.s).setContentText(cVar.t).setContentIntent(broadcast);
            }
            Notification notification = ticker.getNotification();
            notification.flags = b(cVar.r);
            return notification;
        }
        Notification notification2 = new Notification();
        notification2.when = System.currentTimeMillis();
        notification2.icon = i2;
        notification2.tickerText = cVar.t;
        notification2.flags = b(cVar.r);
        if (cVar.h) {
            notification2.defaults = 3;
            if (b.p(context)) {
                notification2.defaults = 0;
            }
        }
        if (!ao.a(cVar.A)) {
            try {
                Bitmap decodeFile2 = BitmapFactory.decodeFile(cVar.A);
                if (decodeFile2 != null) {
                    Integer num3 = a(z[22], new String[]{z[19]}).get(z[19]);
                    Integer num4 = a(z[20], new String[]{z[21]}).get(z[21]);
                    if (num3 == null || num4 == null || num3.intValue() <= 0 || num4.intValue() <= 0) {
                        ac.d();
                        return null;
                    }
                    RemoteViews remoteViews2 = new RemoteViews(context.getPackageName(), num4.intValue());
                    remoteViews2.setImageViewBitmap(num3.intValue(), decodeFile2);
                    notification2.contentView = remoteViews2;
                    notification2.contentIntent = broadcast;
                } else {
                    ac.d();
                    return null;
                }
            } catch (Exception e3) {
                ac.h();
                return null;
            }
        } else {
            a(notification2, context, cVar.s, cVar.t, broadcast);
        }
        return notification2;
    }

    /* JADX WARN: Code restructure failed: missing block: B:13:0x0050, code lost:
        r2 = r8.length;
     */
    /* JADX WARN: Code restructure failed: missing block: B:14:0x0051, code lost:
        if (r1 >= r2) goto L_0x006e;
     */
    /* JADX WARN: Code restructure failed: missing block: B:15:0x0053, code lost:
        r3 = r8[r1];
        r0.put(r3, java.lang.Integer.valueOf(r5.getDeclaredField(r3).getInt(r3)));
     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x0064, code lost:
        r1 = r1 + 1;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.util.HashMap<java.lang.String, java.lang.Integer> a(java.lang.String r7, java.lang.String[] r8) {
        /*
            r1 = 0
            boolean r0 = cn.jpush.android.util.ao.a(r7)
            if (r0 != 0) goto L_0x000a
            int r0 = r8.length
            if (r0 != 0) goto L_0x0016
        L_0x000a:
            java.lang.NullPointerException r0 = new java.lang.NullPointerException
            java.lang.String[] r1 = cn.jpush.android.api.n.z
            r2 = 35
            r1 = r1[r2]
            r0.<init>(r1)
            throw r0
        L_0x0016:
            java.util.HashMap r0 = new java.util.HashMap
            r0.<init>()
            android.content.Context r2 = cn.jpush.android.e.e     // Catch: Exception -> 0x006a
            java.lang.String r2 = r2.getPackageName()     // Catch: Exception -> 0x006a
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch: Exception -> 0x006a
            r3.<init>()     // Catch: Exception -> 0x006a
            java.lang.StringBuilder r2 = r3.append(r2)     // Catch: Exception -> 0x006a
            java.lang.String[] r3 = cn.jpush.android.api.n.z     // Catch: Exception -> 0x006a
            r4 = 36
            r3 = r3[r4]     // Catch: Exception -> 0x006a
            java.lang.StringBuilder r2 = r2.append(r3)     // Catch: Exception -> 0x006a
            java.lang.String r2 = r2.toString()     // Catch: Exception -> 0x006a
            java.lang.Class r2 = java.lang.Class.forName(r2)     // Catch: Exception -> 0x006a
            java.lang.Class[] r3 = r2.getDeclaredClasses()     // Catch: Exception -> 0x006a
            int r4 = r3.length     // Catch: Exception -> 0x006a
            r2 = r1
        L_0x0042:
            if (r2 >= r4) goto L_0x006e
            r5 = r3[r2]     // Catch: Exception -> 0x006a
            java.lang.String r6 = r5.getName()     // Catch: Exception -> 0x006a
            boolean r6 = r6.contains(r7)     // Catch: Exception -> 0x006a
            if (r6 == 0) goto L_0x0067
            int r2 = r8.length     // Catch: Exception -> 0x006a
        L_0x0051:
            if (r1 >= r2) goto L_0x006e
            r3 = r8[r1]     // Catch: Exception -> 0x006a
            java.lang.reflect.Field r4 = r5.getDeclaredField(r3)     // Catch: Exception -> 0x006a
            int r4 = r4.getInt(r3)     // Catch: Exception -> 0x006a
            java.lang.Integer r4 = java.lang.Integer.valueOf(r4)     // Catch: Exception -> 0x006a
            r0.put(r3, r4)     // Catch: Exception -> 0x006a
            int r1 = r1 + 1
            goto L_0x0051
        L_0x0067:
            int r2 = r2 + 1
            goto L_0x0042
        L_0x006a:
            r1 = move-exception
            cn.jpush.android.util.ac.h()
        L_0x006e:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.jpush.android.api.n.a(java.lang.String, java.lang.String[]):java.util.HashMap");
    }

    public static void a(Notification notification, Context context, String str, String str2, PendingIntent pendingIntent) {
        try {
            Class.forName(z[1]).getDeclaredMethod(z[0], Context.class, CharSequence.class, CharSequence.class, PendingIntent.class).invoke(notification, context, str, str2, pendingIntent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void a(Context context) {
        while (true) {
            Integer valueOf = Integer.valueOf(d.a());
            if (valueOf.intValue() != 0) {
                b(context, valueOf.intValue());
            } else {
                return;
            }
        }
    }

    public static void a(Context context, int i) {
        if (i > 0) {
            for (int i2 = 0; i2 < i; i2++) {
                Integer valueOf = Integer.valueOf(d.a());
                if (valueOf.intValue() != 0) {
                    b(context, valueOf.intValue());
                }
            }
        }
    }

    public static void a(Context context, c cVar) {
        if (Thread.currentThread().getId() == PushService.a) {
            ac.c();
            new Thread(new o(context, cVar)).start();
            return;
        }
        b(context, cVar);
    }

    public static void a(Context context, c cVar, int i) {
        new StringBuilder(z[26]).append(cVar.c);
        ac.b();
        if (context == null) {
            context = e.e;
        }
        ((NotificationManager) context.getSystemService(z[11])).cancel(a(cVar, i));
    }

    public static void a(Context context, String str) {
        new StringBuilder(z[34]).append(str);
        ac.b();
        if (context == null) {
            context = e.e;
        }
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(z[11]);
        notificationManager.cancel(a(str, 0));
        notificationManager.cancel(a(str, 1));
    }

    private static void a(Context context, Map<String, String> map, int i, String str, String str2, c cVar) {
        ac.b(z[12], z[33]);
        Intent intent = new Intent(z[28]);
        a(intent, map, i);
        if (!ao.a(str)) {
            intent.putExtra(z[10], str);
        }
        if (cVar.e() && (cVar instanceof m)) {
            m mVar = (m) cVar;
            if (!(mVar.G == 0 || mVar.G == 4)) {
                if (mVar.L != null && mVar.L.startsWith(z[30])) {
                    mVar.L = mVar.L.replaceFirst(z[30], "");
                    intent.putExtra(z[29], mVar.L);
                }
                if (mVar.I != null && mVar.I.size() > 0) {
                    StringBuilder sb = new StringBuilder();
                    String b = p.b(context, cVar.c);
                    Iterator<String> it = mVar.I.iterator();
                    while (it.hasNext()) {
                        String next = it.next();
                        if (next.startsWith(z[31])) {
                            next = r.c(next);
                        }
                        if (ao.a(sb.toString())) {
                            sb.append(b).append(next);
                        } else {
                            sb.append(",").append(b).append(next);
                        }
                    }
                    intent.putExtra(z[27], sb.toString());
                }
            }
        }
        intent.addCategory(str2);
        context.sendBroadcast(intent, str2 + z[32]);
    }

    private static void a(Intent intent, Map<String, String> map, int i) {
        for (String str : map.keySet()) {
            intent.putExtra(str, map.get(str));
        }
        if (i != 0) {
            intent.putExtra(z[2], i);
        }
    }

    private static int b(int i) {
        switch (i) {
            case 0:
            default:
                return 1;
            case 1:
                return 16;
            case 2:
                return 32;
        }
    }

    public static void b(Context context, int i) {
        new StringBuilder(z[23]).append(i);
        ac.b();
        if (context == null) {
            context = e.e;
        }
        ((NotificationManager) context.getSystemService(z[11])).cancel(i);
    }

    public static void b(Context context, c cVar) {
        PendingIntent activity;
        int a;
        Intent intent;
        ac.a();
        int a2 = a(cVar, 0);
        if (cVar.h && cVar.e) {
            NotificationManager notificationManager = (NotificationManager) context.getSystemService(z[11]);
            if (cVar instanceof m) {
                String str = cVar.t;
                String str2 = cVar.s;
                String str3 = cVar.l;
                String packageName = ao.a(cVar.m) ? context.getPackageName() : cVar.m;
                HashMap hashMap = new HashMap();
                hashMap.put(z[8], cVar.c);
                hashMap.put(z[15], str);
                if (!TextUtils.isEmpty(str2)) {
                    hashMap.put(z[7], str2);
                }
                if (!ao.a(str3)) {
                    hashMap.put(z[9], str3);
                }
                if (ao.a(str)) {
                    a(context, hashMap, 0, "", packageName, cVar);
                    return;
                }
                PushNotificationBuilder b = JPushInterface.b(cVar.f);
                String a3 = b.a();
                Notification a4 = b.a(hashMap);
                if (a4 == null || ao.a(str)) {
                    ac.d(z[12], z[17]);
                    return;
                }
                if (!cVar.e()) {
                    if (b.d(context, PushReceiver.class.getCanonicalName())) {
                        intent = new Intent(z[18] + UUID.randomUUID().toString());
                        intent.setClass(context, PushReceiver.class);
                        intent.putExtra(z[14], new StringBuilder().append(cVar.g).toString());
                    } else {
                        ac.c();
                        intent = new Intent(z[5]);
                        intent.addCategory(packageName);
                    }
                    a(intent, hashMap, a2);
                    intent.putExtra(z[4], packageName);
                    if (!ao.a(a3)) {
                        intent.putExtra(z[10], a3);
                    }
                    activity = PendingIntent.getBroadcast(context, 0, intent, 1073741824);
                } else {
                    new StringBuilder(z[16]).append(((m) cVar).G);
                    ac.c();
                    activity = PendingIntent.getActivity(context, a2, (3 == ((m) cVar).G || 4 == ((m) cVar).G || ((m) cVar).G == 0) ? b.a(context, cVar, false) : 2 == ((m) cVar).G ? b.a(context, cVar) : b.a(context, cVar, false), 134217728);
                }
                a4.contentIntent = activity;
                if (!JPushInterface.a(cVar.f)) {
                    if (1 == cVar.g) {
                        cVar.r = 1;
                    }
                    a4.flags = b(cVar.r);
                    if (a4.defaults == 0) {
                        a4.defaults = 3;
                    }
                }
                if (b.p(context)) {
                    a4.defaults = 0;
                }
                if (a4 != null) {
                    notificationManager.notify(a2, a4);
                }
                if (1 != cVar.g) {
                    if (e.o != null || !e.n) {
                        if (!d.b(a2)) {
                            d.a(a2);
                        }
                        if (d.b() > a.b(context) && (a = d.a()) != 0) {
                            b(context, a);
                        }
                    } else {
                        Intent intent2 = new Intent(context, PushService.class);
                        intent2.setAction(z[6]);
                        Bundle bundle = new Bundle();
                        bundle.putInt(z[3], 9);
                        bundle.putInt(z[13], a2);
                        intent2.putExtras(bundle);
                        context.startService(intent2);
                    }
                    k.a(cVar.c, PointerIconCompat.TYPE_ZOOM_IN, context);
                }
                a(context, hashMap, a2, a3, packageName, cVar);
            }
        }
    }
}
