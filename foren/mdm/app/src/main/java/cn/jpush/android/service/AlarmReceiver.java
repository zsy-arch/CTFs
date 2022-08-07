package cn.jpush.android.service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import cn.jpush.android.e;
import cn.jpush.android.util.ac;

/* loaded from: classes.dex */
public class AlarmReceiver extends BroadcastReceiver {
    private static final String[] z;

    /* JADX WARN: Removed duplicated region for block: B:12:0x002a A[LOOP:1: B:7:0x0019->B:12:0x002a, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:15:0x0032  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x005f  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x0062  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x0064  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x0067  */
    /* JADX WARN: Removed duplicated region for block: B:35:0x002e A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:9:0x0020  */
    static {
        /*
            r3 = 2
            r1 = 0
            r2 = 1
            r0 = 4
            java.lang.String[] r5 = new java.lang.String[r0]
            java.lang.String r4 = "4uDd)#mFB"
            r0 = -1
            r6 = r5
            r7 = r5
            r5 = r1
        L_0x000c:
            char[] r4 = r4.toCharArray()
            int r8 = r4.length
            if (r8 > r2) goto L_0x006a
            r9 = r1
        L_0x0014:
            r10 = r4
            r11 = r9
            r14 = r8
            r8 = r4
            r4 = r14
        L_0x0019:
            char r13 = r8[r9]
            int r12 = r11 % 5
            switch(r12) {
                case 0: goto L_0x005f;
                case 1: goto L_0x0062;
                case 2: goto L_0x0064;
                case 3: goto L_0x0067;
                default: goto L_0x0020;
            }
        L_0x0020:
            r12 = 77
        L_0x0022:
            r12 = r12 ^ r13
            char r12 = (char) r12
            r8[r9] = r12
            int r9 = r11 + 1
            if (r4 != 0) goto L_0x002e
            r8 = r10
            r11 = r9
            r9 = r4
            goto L_0x0019
        L_0x002e:
            r8 = r4
            r4 = r10
        L_0x0030:
            if (r8 > r9) goto L_0x0014
            java.lang.String r8 = new java.lang.String
            r8.<init>(r4)
            java.lang.String r4 = r8.intern()
            switch(r0) {
                case 0: goto L_0x0047;
                case 1: goto L_0x0050;
                case 2: goto L_0x005a;
                default: goto L_0x003e;
            }
        L_0x003e:
            r6[r5] = r4
            java.lang.String r0 = ")ou^.#hQ^mL\u000b"
            r4 = r0
            r5 = r2
            r6 = r7
            r0 = r1
            goto L_0x000c
        L_0x0047:
            r6[r5] = r4
            java.lang.String r0 = "\u0007mFI \u0014dD^$0dU"
            r4 = r0
            r5 = r3
            r6 = r7
            r0 = r2
            goto L_0x000c
        L_0x0050:
            r6[r5] = r4
            r4 = 3
            java.lang.String r0 = "%o\tQ=3rO\u0015,(eUT$\"/NU9#oS\u0015\u001f\u0012B"
            r5 = r4
            r6 = r7
            r4 = r0
            r0 = r3
            goto L_0x000c
        L_0x005a:
            r6[r5] = r4
            cn.jpush.android.service.AlarmReceiver.z = r7
            return
        L_0x005f:
            r12 = 70
            goto L_0x0022
        L_0x0062:
            r12 = r2
            goto L_0x0022
        L_0x0064:
            r12 = 39
            goto L_0x0022
        L_0x0067:
            r12 = 59
            goto L_0x0022
        L_0x006a:
            r9 = r1
            goto L_0x0030
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.jpush.android.service.AlarmReceiver.<clinit>():void");
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        ac.b(z[2], z[1]);
        if (e.a(context.getApplicationContext())) {
            if (ServiceInterface.e(context)) {
                ServiceInterface.b(context, false);
                return;
            }
            try {
                Intent intent2 = new Intent(context, PushService.class);
                intent2.setAction(z[3]);
                intent2.putExtra(z[0], 0);
                context.startService(intent2);
            } catch (SecurityException e) {
                e.printStackTrace();
                ac.b();
            } catch (Exception e2) {
                e2.printStackTrace();
                ac.b();
            }
        }
    }
}
