package cn.jpush.android.service;

import android.app.NotificationManager;
import cn.jpush.android.data.c;
import cn.jpush.android.data.i;
import cn.jpush.android.helpers.k;
import cn.jpush.android.util.ac;
import cn.jpush.android.util.b;
import com.autonavi.ae.gmap.utils.GLMapStaticValue;

/* loaded from: classes.dex */
final class e implements d {
    private static final String[] z;
    final /* synthetic */ boolean a;
    final /* synthetic */ int b;
    final /* synthetic */ DownloadService c;

    /* JADX WARN: Removed duplicated region for block: B:12:0x0029 A[LOOP:1: B:7:0x0018->B:12:0x0029, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:15:0x0031  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x0056  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x0059  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x005c  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x005e  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x002d A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:9:0x001f  */
    static {
        /*
            r1 = 0
            r2 = 1
            r0 = 3
            java.lang.String[] r4 = new java.lang.String[r0]
            java.lang.String r3 = "%.uWlhb;"
            r0 = -1
            r5 = r4
            r6 = r4
            r4 = r1
        L_0x000b:
            char[] r3 = r3.toCharArray()
            int r7 = r3.length
            if (r7 > r2) goto L_0x0061
            r8 = r1
        L_0x0013:
            r9 = r3
            r10 = r8
            r13 = r7
            r7 = r3
            r3 = r13
        L_0x0018:
            char r12 = r7[r8]
            int r11 = r10 % 5
            switch(r11) {
                case 0: goto L_0x0056;
                case 1: goto L_0x0059;
                case 2: goto L_0x005c;
                case 3: goto L_0x005e;
                default: goto L_0x001f;
            }
        L_0x001f:
            r11 = 24
        L_0x0021:
            r11 = r11 ^ r12
            char r11 = (char) r11
            r7[r8] = r11
            int r8 = r10 + 1
            if (r3 != 0) goto L_0x002d
            r7 = r9
            r10 = r8
            r8 = r3
            goto L_0x0018
        L_0x002d:
            r7 = r3
            r3 = r9
        L_0x002f:
            if (r7 > r8) goto L_0x0013
            java.lang.String r7 = new java.lang.String
            r7.<init>(r3)
            java.lang.String r3 = r7.intern()
            switch(r0) {
                case 0: goto L_0x0046;
                case 1: goto L_0x0051;
                default: goto L_0x003d;
            }
        L_0x003d:
            r5[r4] = r3
            java.lang.String r0 = "%.eWogbnY|lj;"
            r3 = r0
            r4 = r2
            r5 = r6
            r0 = r1
            goto L_0x000b
        L_0x0046:
            r5[r4] = r3
            r3 = 2
            java.lang.String r0 = "ykb]v}4"
            r4 = r3
            r5 = r6
            r3 = r0
            r0 = r2
            goto L_0x000b
        L_0x0051:
            r5[r4] = r3
            cn.jpush.android.service.e.z = r6
            return
        L_0x0056:
            r11 = 9
            goto L_0x0021
        L_0x0059:
            r11 = 14
            goto L_0x0021
        L_0x005c:
            r11 = r2
            goto L_0x0021
        L_0x005e:
            r11 = 56
            goto L_0x0021
        L_0x0061:
            r8 = r1
            goto L_0x002f
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.jpush.android.service.e.<clinit>():void");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public e(DownloadService downloadService, boolean z2, int i) {
        this.c = downloadService;
        this.a = z2;
        this.b = i;
    }

    @Override // cn.jpush.android.service.d
    public final void a(int i) {
        NotificationManager notificationManager;
        c cVar;
        c cVar2;
        c cVar3;
        c cVar4;
        c cVar5;
        c cVar6;
        notificationManager = this.c.c;
        notificationManager.cancel(this.b);
        if (b.a(i)) {
            cVar3 = this.c.d;
            cVar3.v = true;
            cVar4 = this.c.d;
            k.a(cVar4.c, 1011, this.c);
            String str = "";
            try {
                cVar6 = this.c.d;
                str = ((i) cVar6).K;
            } catch (Exception e) {
            }
            cVar5 = this.c.d;
            k.a(cVar5.c, GLMapStaticValue.AM_PARAMETERNAME_PROCESS_3DOBJECT, b.b(this.c, str), this.c);
        }
        cVar = this.c.d;
        cVar.w = true;
        DownloadService downloadService = this.c;
        int i2 = this.b;
        cVar2 = this.c.d;
        DownloadService.a(downloadService, i2, cVar2, i);
    }

    @Override // cn.jpush.android.service.d
    public final void a(long j, long j2) {
        c cVar;
        new StringBuilder(z[2]).append((int) ((((float) j) / ((float) j2)) * 100.0f)).append(z[1]).append(j).append(z[0]).append(j2);
        ac.b();
        if (!this.a) {
            DownloadService downloadService = this.c;
            cVar = this.c.d;
            downloadService.a(cVar, this.b, j, j2);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x005d  */
    /* JADX WARN: Removed duplicated region for block: B:9:0x0042  */
    @Override // cn.jpush.android.service.d
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void a(java.lang.String r7, boolean r8) {
        /*
            r6 = this;
            r5 = 1
            cn.jpush.android.service.DownloadService r0 = r6.c
            cn.jpush.android.data.c r0 = cn.jpush.android.service.DownloadService.a(r0)
            r0.x = r5
            java.util.concurrent.ConcurrentLinkedQueue<cn.jpush.android.data.c> r0 = cn.jpush.android.service.DownloadService.a
            cn.jpush.android.service.DownloadService r1 = r6.c
            cn.jpush.android.data.c r1 = cn.jpush.android.service.DownloadService.a(r1)
            r0.remove(r1)
            r1 = 1001(0x3e9, float:1.403E-42)
            cn.jpush.android.service.DownloadService r0 = r6.c
            cn.jpush.android.data.c r0 = cn.jpush.android.service.DownloadService.a(r0)
            boolean r0 = r0.a()
            if (r0 == 0) goto L_0x007e
            cn.jpush.android.service.DownloadService r0 = r6.c
            cn.jpush.android.data.c r0 = cn.jpush.android.service.DownloadService.a(r0)
            cn.jpush.android.data.i r0 = (cn.jpush.android.data.i) r0
            r0.P = r7
            r2 = 0
            r0.Q = r2
            boolean r2 = r0.G
            int r3 = r0.H
            cn.jpush.android.service.DownloadService r4 = r6.c
            boolean r2 = cn.jpush.android.helpers.h.a(r2, r3, r4)
            if (r2 == 0) goto L_0x003f
            r1 = 1003(0x3eb, float:1.406E-42)
            r0.Q = r5
        L_0x003f:
            r0 = r1
        L_0x0040:
            if (r8 == 0) goto L_0x0044
            r0 = 1013(0x3f5, float:1.42E-42)
        L_0x0044:
            cn.jpush.android.service.DownloadService r1 = r6.c
            cn.jpush.android.data.c r1 = cn.jpush.android.service.DownloadService.a(r1)
            java.lang.String r1 = r1.c
            cn.jpush.android.service.DownloadService r2 = r6.c
            cn.jpush.android.helpers.k.a(r1, r0, r2)
            cn.jpush.android.service.DownloadService r0 = r6.c
            cn.jpush.android.data.c r0 = cn.jpush.android.service.DownloadService.a(r0)
            boolean r0 = r0.e()
            if (r0 != 0) goto L_0x006a
            cn.jpush.android.service.DownloadService r0 = r6.c
            android.os.Handler r0 = cn.jpush.android.service.DownloadService.b(r0)
            int r1 = r6.b
            r2 = 500(0x1f4, double:2.47E-321)
            r0.sendEmptyMessageDelayed(r1, r2)
        L_0x006a:
            cn.jpush.android.service.DownloadService r0 = r6.c
            cn.jpush.android.data.c r0 = cn.jpush.android.service.DownloadService.a(r0)
            r0.w = r5
            cn.jpush.android.service.DownloadService r0 = r6.c
            cn.jpush.android.service.DownloadService r1 = r6.c
            cn.jpush.android.data.c r1 = cn.jpush.android.service.DownloadService.a(r1)
            cn.jpush.android.service.DownloadService.a(r0, r1)
            return
        L_0x007e:
            cn.jpush.android.service.DownloadService r0 = r6.c
            cn.jpush.android.data.c r0 = cn.jpush.android.service.DownloadService.a(r0)
            boolean r0 = r0.b()
            if (r0 == 0) goto L_0x0097
            cn.jpush.android.service.DownloadService r0 = r6.c
            cn.jpush.android.data.c r0 = cn.jpush.android.service.DownloadService.a(r0)
            cn.jpush.android.data.s r0 = (cn.jpush.android.data.s) r0
            r0.I = r7
            r0 = 1004(0x3ec, float:1.407E-42)
            goto L_0x0040
        L_0x0097:
            cn.jpush.android.service.DownloadService r0 = r6.c
            cn.jpush.android.data.c r0 = cn.jpush.android.service.DownloadService.a(r0)
            boolean r0 = r0.e()
            if (r0 == 0) goto L_0x003f
            cn.jpush.android.service.DownloadService r0 = r6.c
            cn.jpush.android.data.c r0 = cn.jpush.android.service.DownloadService.a(r0)
            r0.C = r7
            goto L_0x003f
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.jpush.android.service.e.a(java.lang.String, boolean):void");
    }
}
