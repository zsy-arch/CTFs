package cn.jpush.android.service;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Handler;
import android.os.Looper;
import cn.jpush.android.data.JPushLocalNotification;
import cn.jpush.android.data.f;
import cn.jpush.android.util.ac;
import java.util.concurrent.ExecutorService;

/* loaded from: classes.dex */
public final class h {
    private static h a;
    private static ExecutorService b;
    private static f f;
    private static cn.jpush.android.data.h g;
    private static Object h;
    private static final String[] z;
    private Handler c;
    private Context d;
    private String e;

    /* JADX WARN: Code restructure failed: missing block: B:10:0x0027, code lost:
        if (r5 != 0) goto L_0x002d;
     */
    /* JADX WARN: Code restructure failed: missing block: B:11:0x0029, code lost:
        r5 = r1;
        r8 = r6;
        r6 = r5;
     */
    /* JADX WARN: Code restructure failed: missing block: B:12:0x002d, code lost:
        r5 = r5;
        r1 = r1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:13:0x002f, code lost:
        if (r5 > r6) goto L_0x0013;
     */
    /* JADX WARN: Code restructure failed: missing block: B:14:0x0031, code lost:
        r1 = new java.lang.String(r1).intern();
     */
    /* JADX WARN: Code restructure failed: missing block: B:15:0x003a, code lost:
        switch(r0) {
            case 0: goto L_0x0045;
            case 1: goto L_0x004e;
            case 2: goto L_0x0056;
            case 3: goto L_0x005e;
            case 4: goto L_0x0066;
            case 5: goto L_0x006e;
            case 6: goto L_0x0076;
            case 7: goto L_0x0080;
            case 8: goto L_0x008a;
            case 9: goto L_0x0095;
            case 10: goto L_0x00a1;
            case 11: goto L_0x00ac;
            case 12: goto L_0x00b8;
            default: goto L_0x003d;
        };
     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x003d, code lost:
        r3[r2] = r1;
        r2 = 1;
        r1 = "EG+vPCx=dTAx*l^ft?qQ`y\u001d`V{r,";
        r0 = 0;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x0045, code lost:
        r3[r2] = r1;
        r2 = 2;
        r1 = "}r3jNj72j[n{~fWzy*%\u0002/";
        r0 = 1;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x004e, code lost:
        r3[r2] = r1;
        r2 = 3;
        r1 = "!g;wUfd-lWa9\u0014Um\\_\u0001H}\\D\u001fB}";
        r0 = 2;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:19:0x0056, code lost:
        r3[r2] = r1;
        r2 = 4;
        r1 = "ax*l^ft?lWaH*|Hj";
        r0 = 3;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x005e, code lost:
        r3[r2] = r1;
        r2 = 5;
        r1 = "lypoHzd6+Yas,jQk97kLjy*+v@C\u0017CqLV\nLwAH\f@{J^\b@|PG\fJ`V";
        r0 = 4;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x0066, code lost:
        r3[r2] = r1;
        r2 = 6;
        r1 = "br-vYhr";
        r0 = 5;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x006e, code lost:
        r3[r2] = r1;
        r2 = 7;
        r1 = "ng.L\\";
        r0 = 6;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x0076, code lost:
        r3[r2] = r1;
        r2 = '\b';
        r1 = "|r0a]}^:";
        r0 = 7;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x0080, code lost:
        r3[r2] = r1;
        r2 = '\t';
        r1 = "ns:%t`t?iv`c7cQlv*lWa";
        r0 = '\b';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x008a, code lost:
        r3[r2] = r1;
        r2 = '\n';
        r1 = "l{;dJ/v2i\u0018cx=dT/y1qQi~=dLfx0%";
        r0 = '\t';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x0095, code lost:
        r3[r2] = r1;
        r2 = 11;
        r1 = "\u007fx-q\u0018kr2dAjs~?\u0018";
        r0 = '\n';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x00a1, code lost:
        r3[r2] = r1;
        r2 = '\f';
        r1 = "fy7q\u0018Cx=dTAx*l^ft?qQ`y~fY|c~`@\u007fcd";
        r0 = 11;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x00ac, code lost:
        r3[r2] = r1;
        r2 = '\r';
        r1 = "{e7b_je\u0012Ksf{2UJ`t;vK57";
        r0 = '\f';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:29:0x00b8, code lost:
        r3[r2] = r1;
        cn.jpush.android.service.h.z = r3;
        cn.jpush.android.service.h.a = null;
        cn.jpush.android.service.h.b = java.util.concurrent.Executors.newSingleThreadExecutor();
        cn.jpush.android.service.h.f = null;
        cn.jpush.android.service.h.g = new cn.jpush.android.data.h();
        cn.jpush.android.service.h.h = new java.lang.Object();
     */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x00d6, code lost:
        return;
     */
    /* JADX WARN: Code restructure failed: missing block: B:31:0x00d7, code lost:
        r9 = 15;
     */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x00db, code lost:
        r9 = 23;
     */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x00df, code lost:
        r9 = '^';
     */
    /* JADX WARN: Code restructure failed: missing block: B:34:0x00e3, code lost:
        r9 = 5;
     */
    /* JADX WARN: Code restructure failed: missing block: B:4:0x0011, code lost:
        if (r5 <= 1) goto L_0x0013;
     */
    /* JADX WARN: Code restructure failed: missing block: B:5:0x0013, code lost:
        r8 = r6;
        r5 = r1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:6:0x0018, code lost:
        r10 = r5[r6];
     */
    /* JADX WARN: Code restructure failed: missing block: B:7:0x001c, code lost:
        switch((r8 % 5)) {
            case 0: goto L_0x00d7;
            case 1: goto L_0x00db;
            case 2: goto L_0x00df;
            case 3: goto L_0x00e3;
            default: goto L_0x001f;
        };
     */
    /* JADX WARN: Code restructure failed: missing block: B:8:0x001f, code lost:
        r9 = '8';
     */
    /* JADX WARN: Code restructure failed: missing block: B:9:0x0021, code lost:
        r5[r6] = (char) (r9 ^ r10);
        r6 = r8 + 1;
     */
    static {
        /*
            Method dump skipped, instructions count: 272
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.jpush.android.service.h.<clinit>():void");
    }

    private h(Context context) {
        this.c = null;
        this.d = null;
        this.e = "";
        ac.b();
        this.c = new Handler(Looper.getMainLooper());
        this.d = context;
        this.e = this.d.getPackageName();
    }

    public static h a(Context context) {
        ac.b();
        synchronized (h) {
            if (a == null) {
                a = new h(context);
            }
        }
        return a;
    }

    private synchronized void a(long j, long j2) {
        ac.b();
        if (j < 0) {
            ac.e();
        } else if (this.c != null) {
            j jVar = new j(this, j);
            if (j2 <= 0) {
                ac.b();
                this.c.post(jVar);
            } else {
                new StringBuilder(z[11]).append(j2);
                ac.b();
                this.c.postDelayed(jVar, j2);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(Context context, String str, String str2, String str3) {
        ac.b();
        Intent intent = new Intent(z[5]);
        intent.putExtra(z[8], str3);
        intent.putExtra(z[7], str2);
        intent.putExtra(z[6], str);
        intent.putExtra(z[4], 1);
        intent.addCategory(str2);
        context.sendOrderedBroadcast(intent, str2 + z[3]);
        ac.b();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:15:0x0068 A[Catch: all -> 0x0096, TRY_ENTER, TRY_LEAVE, TryCatch #3 {, blocks: (B:3:0x0001, B:15:0x0068, B:21:0x0092, B:27:0x009d, B:28:0x00a0), top: B:36:0x0001 }] */
    /* JADX WARN: Removed duplicated region for block: B:27:0x009d A[Catch: all -> 0x0096, TRY_ENTER, TryCatch #3 {, blocks: (B:3:0x0001, B:15:0x0068, B:21:0x0092, B:27:0x009d, B:28:0x00a0), top: B:36:0x0001 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public synchronized void e(android.content.Context r14) {
        /*
            r13 = this;
            monitor-enter(r13)
            cn.jpush.android.util.ac.b()     // Catch: all -> 0x0096
            r1 = 0
            cn.jpush.android.data.f r0 = cn.jpush.android.service.h.f     // Catch: Exception -> 0x006d, all -> 0x0099
            if (r0 != 0) goto L_0x0010
            cn.jpush.android.data.f r0 = new cn.jpush.android.data.f     // Catch: Exception -> 0x006d, all -> 0x0099
            r0.<init>(r14)     // Catch: Exception -> 0x006d, all -> 0x0099
            cn.jpush.android.service.h.f = r0     // Catch: Exception -> 0x006d, all -> 0x0099
        L_0x0010:
            cn.jpush.android.data.f r0 = cn.jpush.android.service.h.f     // Catch: Exception -> 0x006d, all -> 0x0099
            r2 = 1
            r0.a(r2)     // Catch: Exception -> 0x006d, all -> 0x0099
            long r2 = java.lang.System.currentTimeMillis()     // Catch: Exception -> 0x006d, all -> 0x0099
            cn.jpush.android.data.f r0 = cn.jpush.android.service.h.f     // Catch: Exception -> 0x006d, all -> 0x0099
            r4 = 1
            android.database.Cursor r12 = r0.a(r4, r2)     // Catch: Exception -> 0x006d, all -> 0x0099
            boolean r0 = r12.moveToFirst()     // Catch: Exception -> 0x00a6, all -> 0x00a1
            if (r0 == 0) goto L_0x0061
        L_0x0027:
            cn.jpush.android.data.f r0 = cn.jpush.android.service.h.f     // Catch: Exception -> 0x00a6, all -> 0x00a1
            cn.jpush.android.data.h r1 = cn.jpush.android.service.h.g     // Catch: Exception -> 0x00a6, all -> 0x00a1
            r0.a(r12, r1)     // Catch: Exception -> 0x00a6, all -> 0x00a1
            cn.jpush.android.data.h r0 = cn.jpush.android.service.h.g     // Catch: Exception -> 0x00a6, all -> 0x00a1
            java.lang.String r0 = r0.d()     // Catch: Exception -> 0x00a6, all -> 0x00a1
            java.lang.String r1 = r13.e     // Catch: Exception -> 0x00a6, all -> 0x00a1
            java.lang.String r2 = ""
            r13.a(r14, r0, r1, r2)     // Catch: Exception -> 0x00a6, all -> 0x00a1
            cn.jpush.android.data.f r1 = cn.jpush.android.service.h.f     // Catch: Exception -> 0x00a6, all -> 0x00a1
            cn.jpush.android.data.h r0 = cn.jpush.android.service.h.g     // Catch: Exception -> 0x00a6, all -> 0x00a1
            long r2 = r0.a()     // Catch: Exception -> 0x00a6, all -> 0x00a1
            r4 = 0
            r5 = 0
            r6 = 0
            cn.jpush.android.data.h r0 = cn.jpush.android.service.h.g     // Catch: Exception -> 0x00a6, all -> 0x00a1
            java.lang.String r7 = r0.d()     // Catch: Exception -> 0x00a6, all -> 0x00a1
            cn.jpush.android.data.h r0 = cn.jpush.android.service.h.g     // Catch: Exception -> 0x00a6, all -> 0x00a1
            long r8 = r0.f()     // Catch: Exception -> 0x00a6, all -> 0x00a1
            cn.jpush.android.data.h r0 = cn.jpush.android.service.h.g     // Catch: Exception -> 0x00a6, all -> 0x00a1
            long r10 = r0.e()     // Catch: Exception -> 0x00a6, all -> 0x00a1
            r1.b(r2, r4, r5, r6, r7, r8, r10)     // Catch: Exception -> 0x00a6, all -> 0x00a1
            boolean r0 = r12.moveToNext()     // Catch: Exception -> 0x00a6, all -> 0x00a1
            if (r0 != 0) goto L_0x0027
        L_0x0061:
            cn.jpush.android.data.f r0 = cn.jpush.android.service.h.f     // Catch: Exception -> 0x00a6, all -> 0x00a1
            r0.a()     // Catch: Exception -> 0x00a6, all -> 0x00a1
            if (r12 == 0) goto L_0x006b
            r12.close()     // Catch: all -> 0x0096
        L_0x006b:
            monitor-exit(r13)
            return
        L_0x006d:
            r0 = move-exception
        L_0x006e:
            java.lang.String[] r2 = cn.jpush.android.service.h.z     // Catch: all -> 0x00a3
            r3 = 1
            r2 = r2[r3]     // Catch: all -> 0x00a3
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch: all -> 0x00a3
            java.lang.String[] r4 = cn.jpush.android.service.h.z     // Catch: all -> 0x00a3
            r5 = 13
            r4 = r4[r5]     // Catch: all -> 0x00a3
            r3.<init>(r4)     // Catch: all -> 0x00a3
            java.lang.String r4 = r0.getMessage()     // Catch: all -> 0x00a3
            java.lang.StringBuilder r3 = r3.append(r4)     // Catch: all -> 0x00a3
            java.lang.String r3 = r3.toString()     // Catch: all -> 0x00a3
            cn.jpush.android.util.ac.d(r2, r3)     // Catch: all -> 0x00a3
            r0.printStackTrace()     // Catch: all -> 0x00a3
            if (r1 == 0) goto L_0x006b
            r1.close()     // Catch: all -> 0x0096
            goto L_0x006b
        L_0x0096:
            r0 = move-exception
            monitor-exit(r13)
            throw r0
        L_0x0099:
            r0 = move-exception
            r12 = r1
        L_0x009b:
            if (r12 == 0) goto L_0x00a0
            r12.close()     // Catch: all -> 0x0096
        L_0x00a0:
            throw r0     // Catch: all -> 0x0096
        L_0x00a1:
            r0 = move-exception
            goto L_0x009b
        L_0x00a3:
            r0 = move-exception
            r12 = r1
            goto L_0x009b
        L_0x00a6:
            r0 = move-exception
            r1 = r12
            goto L_0x006e
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.jpush.android.service.h.e(android.content.Context):void");
    }

    public final synchronized boolean a(Context context, long j) {
        Cursor cursor;
        Throwable th;
        Cursor a2;
        ac.b(z[1], z[0]);
        if (f == null) {
            f = new f(context);
        }
        Cursor cursor2 = null;
        try {
            try {
                f.a(true);
                a2 = f.a(j, 0);
            } catch (Throwable th2) {
                th = th2;
                cursor = null;
            }
        } catch (Exception e) {
            if (0 != 0) {
                cursor2.close();
            }
        }
        try {
            f.a(a2, g);
            if (g.b() > 0) {
                new StringBuilder(z[2]).append(g.b());
                ac.b();
                f.b(j, 0, 1, 0, g.d(), g.f(), g.e());
            }
            f.a();
            if (a2 != null) {
                a2.close();
            }
        } catch (Throwable th3) {
            th = th3;
            cursor = a2;
            if (cursor != null) {
                cursor.close();
            }
            throw th;
        }
        return true;
    }

    public final synchronized boolean a(Context context, JPushLocalNotification jPushLocalNotification) {
        Cursor cursor;
        Throwable th;
        boolean z2;
        ac.b(z[1], z[9]);
        long currentTimeMillis = System.currentTimeMillis();
        long broadcastTime = jPushLocalNotification.getBroadcastTime() - currentTimeMillis;
        if (ServiceInterface.e(context)) {
            ac.b();
        }
        if (f == null) {
            f = new f(context);
        }
        Cursor cursor2 = null;
        try {
            try {
                f.a(true);
                Cursor a2 = f.a(jPushLocalNotification.getNotificationId(), 0);
                try {
                    f.a(a2, g);
                    if (g.a() != jPushLocalNotification.getNotificationId()) {
                        f.a(jPushLocalNotification.getNotificationId(), 1, 0, 0, jPushLocalNotification.toJSON(), jPushLocalNotification.getBroadcastTime(), currentTimeMillis);
                    } else {
                        f.b(jPushLocalNotification.getNotificationId(), 1, 0, 0, jPushLocalNotification.toJSON(), jPushLocalNotification.getBroadcastTime(), currentTimeMillis);
                    }
                    f.a();
                    if (a2 != null) {
                        a2.close();
                    }
                } catch (Throwable th2) {
                    th = th2;
                    cursor = a2;
                    if (cursor != null) {
                        cursor.close();
                    }
                    throw th;
                }
            } catch (Exception e) {
                if (0 != 0) {
                    cursor2.close();
                }
            }
            if (broadcastTime < 300000) {
                a(jPushLocalNotification.getNotificationId(), broadcastTime);
                z2 = true;
            } else {
                z2 = true;
            }
        } catch (Throwable th3) {
            th = th3;
            cursor = null;
        }
        return z2;
    }

    public final synchronized void b(Context context) {
        ac.b(z[1], z[10]);
        if (f == null) {
            f = new f(context);
        }
        f.a(true);
        if (f.b()) {
            ac.b();
        }
        f.a();
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x004d A[Catch: Exception -> 0x0059, all -> 0x006f, TRY_ENTER, TRY_LEAVE, TryCatch #1 {Exception -> 0x0059, blocks: (B:4:0x0004, B:6:0x0008, B:15:0x004d, B:20:0x0055, B:28:0x0078, B:29:0x007b), top: B:34:0x0004, outer: #4 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final synchronized void c(android.content.Context r10) {
        /*
            r9 = this;
            monitor-enter(r9)
            cn.jpush.android.util.ac.b()     // Catch: all -> 0x006f
            cn.jpush.android.data.f r0 = cn.jpush.android.service.h.f     // Catch: Exception -> 0x0059, all -> 0x006f
            if (r0 != 0) goto L_0x000f
            cn.jpush.android.data.f r0 = new cn.jpush.android.data.f     // Catch: Exception -> 0x0059, all -> 0x006f
            r0.<init>(r10)     // Catch: Exception -> 0x0059, all -> 0x006f
            cn.jpush.android.service.h.f = r0     // Catch: Exception -> 0x0059, all -> 0x006f
        L_0x000f:
            r0 = 0
            cn.jpush.android.data.f r1 = cn.jpush.android.service.h.f     // Catch: Exception -> 0x0052, all -> 0x0072
            r2 = 0
            r1.a(r2)     // Catch: Exception -> 0x0052, all -> 0x0072
            long r2 = java.lang.System.currentTimeMillis()     // Catch: Exception -> 0x0052, all -> 0x0072
            cn.jpush.android.data.f r1 = cn.jpush.android.service.h.f     // Catch: Exception -> 0x0052, all -> 0x0072
            r4 = 300000(0x493e0, double:1.482197E-318)
            android.database.Cursor r0 = r1.a(r2, r4)     // Catch: Exception -> 0x0052, all -> 0x0072
            boolean r1 = r0.moveToFirst()     // Catch: Exception -> 0x0052, all -> 0x007c
            if (r1 == 0) goto L_0x0046
        L_0x0029:
            cn.jpush.android.data.f r1 = cn.jpush.android.service.h.f     // Catch: Exception -> 0x0052, all -> 0x007c
            cn.jpush.android.data.h r4 = cn.jpush.android.service.h.g     // Catch: Exception -> 0x0052, all -> 0x007c
            r1.a(r0, r4)     // Catch: Exception -> 0x0052, all -> 0x007c
            cn.jpush.android.data.h r1 = cn.jpush.android.service.h.g     // Catch: Exception -> 0x0052, all -> 0x007c
            long r4 = r1.a()     // Catch: Exception -> 0x0052, all -> 0x007c
            cn.jpush.android.data.h r1 = cn.jpush.android.service.h.g     // Catch: Exception -> 0x0052, all -> 0x007c
            long r6 = r1.f()     // Catch: Exception -> 0x0052, all -> 0x007c
            long r6 = r6 - r2
            r9.a(r4, r6)     // Catch: Exception -> 0x0052, all -> 0x007c
            boolean r1 = r0.moveToNext()     // Catch: Exception -> 0x0052, all -> 0x007c
            if (r1 != 0) goto L_0x0029
        L_0x0046:
            cn.jpush.android.data.f r1 = cn.jpush.android.service.h.f     // Catch: Exception -> 0x0052, all -> 0x007c
            r1.a()     // Catch: Exception -> 0x0052, all -> 0x007c
            if (r0 == 0) goto L_0x0050
            r0.close()     // Catch: Exception -> 0x0059, all -> 0x006f
        L_0x0050:
            monitor-exit(r9)
            return
        L_0x0052:
            r1 = move-exception
            if (r0 == 0) goto L_0x0050
            r0.close()     // Catch: Exception -> 0x0059, all -> 0x006f
            goto L_0x0050
        L_0x0059:
            r0 = move-exception
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch: all -> 0x006f
            java.lang.String[] r2 = cn.jpush.android.service.h.z     // Catch: all -> 0x006f
            r3 = 12
            r2 = r2[r3]     // Catch: all -> 0x006f
            r1.<init>(r2)     // Catch: all -> 0x006f
            r1.append(r0)     // Catch: all -> 0x006f
            cn.jpush.android.util.ac.b()     // Catch: all -> 0x006f
            r0.printStackTrace()     // Catch: all -> 0x006f
            goto L_0x0050
        L_0x006f:
            r0 = move-exception
            monitor-exit(r9)
            throw r0
        L_0x0072:
            r1 = move-exception
            r8 = r1
            r1 = r0
            r0 = r8
        L_0x0076:
            if (r1 == 0) goto L_0x007b
            r1.close()     // Catch: Exception -> 0x0059, all -> 0x006f
        L_0x007b:
            throw r0     // Catch: Exception -> 0x0059, all -> 0x006f
        L_0x007c:
            r1 = move-exception
            r8 = r1
            r1 = r0
            r0 = r8
            goto L_0x0076
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.jpush.android.service.h.c(android.content.Context):void");
    }

    public final void d(Context context) {
        ac.b();
        b.execute(new i(this, context));
    }
}
