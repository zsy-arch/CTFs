package cn.jpush.android.service;

import android.content.ComponentName;
import android.content.Intent;
import android.os.Build;
import cn.jpush.android.a;
import cn.jpush.android.util.ac;
import cn.jpush.android.util.b;
import java.util.List;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class l implements Runnable {
    private static final String[] z;
    final /* synthetic */ long a;
    final /* synthetic */ PushService b;

    /* JADX WARN: Removed duplicated region for block: B:12:0x002b A[LOOP:1: B:7:0x001a->B:12:0x002b, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:15:0x0033  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x0069  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x006b  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x006d  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0070  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x002f A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:9:0x0021  */
    static {
        /*
            r4 = 3
            r3 = 2
            r2 = 1
            r1 = 0
            r0 = 5
            java.lang.String[] r6 = new java.lang.String[r0]
            java.lang.String r5 = "ki\\0CSoR4\u000f*&S0\\sJ^$Adnk8Bb&\u0002q"
            r0 = -1
            r7 = r6
            r8 = r6
            r6 = r1
        L_0x000d:
            char[] r5 = r5.toCharArray()
            int r9 = r5.length
            if (r9 > r2) goto L_0x0073
            r10 = r1
        L_0x0015:
            r11 = r5
            r12 = r10
            r15 = r9
            r9 = r5
            r5 = r15
        L_0x001a:
            char r14 = r9[r10]
            int r13 = r12 % 5
            switch(r13) {
                case 0: goto L_0x0069;
                case 1: goto L_0x006b;
                case 2: goto L_0x006d;
                case 3: goto L_0x0070;
                default: goto L_0x0021;
            }
        L_0x0021:
            r13 = 47
        L_0x0023:
            r13 = r13 ^ r14
            char r13 = (char) r13
            r9[r10] = r13
            int r10 = r12 + 1
            if (r5 != 0) goto L_0x002f
            r9 = r11
            r12 = r10
            r10 = r5
            goto L_0x001a
        L_0x002f:
            r9 = r5
            r5 = r11
        L_0x0031:
            if (r9 > r10) goto L_0x0015
            java.lang.String r9 = new java.lang.String
            r9.<init>(r5)
            java.lang.String r5 = r9.intern()
            switch(r0) {
                case 0: goto L_0x0048;
                case 1: goto L_0x0051;
                case 2: goto L_0x005a;
                case 3: goto L_0x0064;
                default: goto L_0x003f;
            }
        L_0x003f:
            r7[r6] = r5
            java.lang.String r0 = "+&S0\\s&S0ZieW4K'rV<J="
            r5 = r0
            r6 = r2
            r7 = r8
            r0 = r1
            goto L_0x000d
        L_0x0048:
            r7[r6] = r5
            java.lang.String r0 = "DgQv['uK0]s&P%Gbt\u001f!Ztn\u001f\"JupV2Jt&[$@'rPq\\beJ#Fs\u007f\u001e"
            r5 = r0
            r6 = r3
            r7 = r8
            r0 = r2
            goto L_0x000d
        L_0x0051:
            r7[r6] = r5
            java.lang.String r0 = "soR4\u000fiiHk"
            r5 = r0
            r6 = r4
            r7 = r8
            r0 = r3
            goto L_0x000d
        L_0x005a:
            r7[r6] = r5
            r5 = 4
            java.lang.String r0 = "WsL9|btI8Lb"
            r6 = r5
            r7 = r8
            r5 = r0
            r0 = r4
            goto L_0x000d
        L_0x0064:
            r7[r6] = r5
            cn.jpush.android.service.l.z = r8
            return
        L_0x0069:
            r13 = 7
            goto L_0x0023
        L_0x006b:
            r13 = 6
            goto L_0x0023
        L_0x006d:
            r13 = 63
            goto L_0x0023
        L_0x0070:
            r13 = 81
            goto L_0x0023
        L_0x0073:
            r10 = r1
            goto L_0x0031
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.jpush.android.service.l.<clinit>():void");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public l(PushService pushService, long j) {
        this.b = pushService;
        this.a = j;
    }

    @Override // java.lang.Runnable
    public final void run() {
        try {
            long currentTimeMillis = System.currentTimeMillis() / 1000;
            long x = a.x();
            new StringBuilder(z[3]).append(currentTimeMillis).append(z[1]).append(x);
            ac.b();
            if (-1 == x || Math.abs(currentTimeMillis - x) > this.a) {
                List<ComponentName> v = b.v(this.b.getApplicationContext());
                a.b(currentTimeMillis);
                int size = v != null ? v.size() : 0;
                for (int i = 0; i < size; i++) {
                    Intent intent = new Intent();
                    intent.setComponent(v.get(i));
                    if (Build.VERSION.SDK_INT >= 12) {
                        intent.setFlags(32);
                    }
                    this.b.startService(intent);
                }
                return;
            }
            new StringBuilder(z[0]).append(currentTimeMillis - x);
            ac.a();
        } catch (SecurityException e) {
            ac.d(z[4], z[2]);
            e.printStackTrace();
        }
    }
}
