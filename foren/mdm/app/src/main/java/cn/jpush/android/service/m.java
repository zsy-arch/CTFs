package cn.jpush.android.service;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import cn.jpush.a.a.a.g;
import cn.jpush.android.helpers.ConnectingHelper;
import cn.jpush.android.helpers.b;
import cn.jpush.android.helpers.k;
import cn.jpush.android.util.ac;
import com.autonavi.ae.gmap.utils.GLMapStaticValue;
import java.lang.ref.WeakReference;
import java.util.concurrent.ExecutorService;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class m extends Handler {
    private static final String[] z;
    private final WeakReference<PushService> a;

    /* JADX WARN: Removed duplicated region for block: B:12:0x0029 A[LOOP:1: B:7:0x0018->B:12:0x0029, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:15:0x0031  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x0055  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x0058  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x005b  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x005e  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x002d A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:9:0x001f  */
    static {
        /*
            r2 = 1
            r1 = 0
            r0 = 3
            java.lang.String[] r4 = new java.lang.String[r0]
            java.lang.String r3 = "')'].\u0017$6@:Hg7K6\u0013)&I;\u0016g/V9Rjb"
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
                case 0: goto L_0x0055;
                case 1: goto L_0x0058;
                case 2: goto L_0x005b;
                case 3: goto L_0x005e;
                default: goto L_0x001f;
            }
        L_0x001f:
            r11 = 94
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
                case 1: goto L_0x0050;
                default: goto L_0x003d;
            }
        L_0x003d:
            r5[r4] = r3
            java.lang.String r0 = "\u001a&,A2\u0017\n'V-\u0013 '\u001f"
            r3 = r0
            r4 = r2
            r5 = r6
            r0 = r1
            goto L_0x000b
        L_0x0046:
            r5[r4] = r3
            r3 = 2
            java.lang.String r0 = "\u0011(,K;\u00113+J0"
            r4 = r3
            r5 = r6
            r3 = r0
            r0 = r2
            goto L_0x000b
        L_0x0050:
            r5[r4] = r3
            cn.jpush.android.service.m.z = r6
            return
        L_0x0055:
            r11 = 114(0x72, float:1.6E-43)
            goto L_0x0021
        L_0x0058:
            r11 = 71
            goto L_0x0021
        L_0x005b:
            r11 = 66
            goto L_0x0021
        L_0x005e:
            r11 = 37
            goto L_0x0021
        L_0x0061:
            r8 = r1
            goto L_0x002f
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.jpush.android.service.m.<clinit>():void");
    }

    public m(PushService pushService) {
        this.a = new WeakReference<>(pushService);
    }

    @Override // android.os.Handler
    public final void handleMessage(Message message) {
        b bVar;
        b bVar2;
        b bVar3;
        b bVar4;
        b bVar5;
        b bVar6;
        ExecutorService executorService;
        super.handleMessage(message);
        new StringBuilder(z[1]).append(message.toString());
        ac.a();
        PushService pushService = this.a.get();
        if (pushService == null) {
            ac.d();
            return;
        }
        Context applicationContext = pushService.getApplicationContext();
        switch (message.what) {
            case 1001:
                ac.b();
                pushService.stopSelf();
                cn.jpush.android.util.b.k(pushService.getApplicationContext());
                ConnectingHelper.sendConnectionChanged(applicationContext, a.b);
                return;
            case 1002:
                k.a(applicationContext, true, PushService.b, PushService.d, PushService.c);
                return;
            case 1003:
                pushService.stopSelf();
                return;
            case 1004:
                PushService.a(pushService, true);
                return;
            case 1005:
                PushService.a(pushService, false);
                return;
            case 1006:
                removeMessages(1011);
                removeMessages(1010);
                sendEmptyMessageDelayed(1011, 3000L);
                return;
            case 1007:
                sendEmptyMessageDelayed(1010, 200L);
                return;
            case 1009:
                bVar = pushService.f;
                if (bVar == null) {
                    ac.d();
                    return;
                }
                bVar2 = pushService.f;
                bVar2.a(k.a.get(), (g) message.obj);
                return;
            case 1010:
                executorService = pushService.g;
                PushService.a(executorService);
                return;
            case 1011:
                pushService.b();
                return;
            case GLMapStaticValue.AM_PARAMETERNAME_PROCESS_3DOBJECT /* 1022 */:
                PushService.c(pushService);
                return;
            case 1031:
                cn.jpush.android.util.b.k(pushService.getApplicationContext());
                return;
            case 7301:
                PushService.b(pushService, message.getData().getLong(z[2]));
                return;
            case 7303:
                PushService.c(pushService, message.getData().getLong(z[2]));
                return;
            case 7304:
                PushService.a(pushService, message.getData().getLong(z[2]));
                return;
            case 7306:
                ac.b(PushService.z[5], PushService.z[6] + message.getData().getLong(z[2]) + PushService.z[7] + message.arg2);
                return;
            case 7307:
                k.b.set(false);
                return;
            case 7403:
                bVar5 = pushService.f;
                if (bVar5 == null) {
                    ac.d();
                    return;
                }
                bVar6 = pushService.f;
                bVar6.b((Long) message.obj);
                return;
            case 7404:
                bVar3 = pushService.f;
                if (bVar3 == null) {
                    ac.d();
                    return;
                }
                bVar4 = pushService.f;
                bVar4.a((Long) message.obj);
                return;
            default:
                new StringBuilder(z[0]).append(message.what);
                ac.a();
                return;
        }
    }
}
