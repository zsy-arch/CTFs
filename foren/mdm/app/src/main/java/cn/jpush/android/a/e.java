package cn.jpush.android.a;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.WifiManager;
import cn.jpush.android.util.ac;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class e extends BroadcastReceiver {
    private static final String[] z;
    final /* synthetic */ d a;

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
            java.lang.String r3 = "*6\u000e\u007f^\"<DcT?v\u001ddW\"v9Np\u0005\u00078Hb\u001e\u0014>^"
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
            r11 = 49
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
            java.lang.String r0 = "*6\u000e\u007f^\"<DcT?v\u001ddW\"v=Dw\u0002\u00079Yp\u001f\u001d5Ny\n\u0016-Hu"
            r3 = r0
            r4 = r2
            r5 = r6
            r0 = r1
            goto L_0x000b
        L_0x0046:
            r5[r4] = r3
            r3 = 2
            java.lang.String r0 = "<1\fdn8,\u000byT"
            r4 = r3
            r5 = r6
            r3 = r0
            r0 = r2
            goto L_0x000b
        L_0x0050:
            r5[r4] = r3
            cn.jpush.android.a.e.z = r6
            return
        L_0x0055:
            r11 = 75
            goto L_0x0021
        L_0x0058:
            r11 = 88
            goto L_0x0021
        L_0x005b:
            r11 = 106(0x6a, float:1.49E-43)
            goto L_0x0021
        L_0x005e:
            r11 = 13
            goto L_0x0021
        L_0x0061:
            r8 = r1
            goto L_0x002f
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.jpush.android.a.e.<clinit>():void");
    }

    private e(d dVar) {
        this.a = dVar;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ e(d dVar, byte b) {
        this(dVar);
    }

    @Override // android.content.BroadcastReceiver
    public final void onReceive(Context context, Intent intent) {
        boolean z2;
        j jVar;
        j jVar2;
        Context context2;
        BroadcastReceiver broadcastReceiver;
        boolean z3;
        Context context3;
        boolean a;
        int i;
        boolean z4;
        f fVar;
        long j;
        f fVar2;
        f fVar3;
        boolean z5 = false;
        z2 = this.a.x;
        if (z2) {
            d dVar = this.a;
            context3 = this.a.j;
            a = d.a(context3);
            if (a) {
                i = this.a.p;
                if (i == 2) {
                    if (z[0].equals(intent.getAction())) {
                        fVar = this.a.l;
                        fVar.removeMessages(5);
                        long currentTimeMillis = System.currentTimeMillis();
                        j = this.a.o;
                        if (currentTimeMillis - j > 4000) {
                            fVar3 = this.a.l;
                            fVar3.sendEmptyMessageDelayed(5, 4000L);
                            return;
                        }
                        fVar2 = this.a.l;
                        fVar2.sendEmptyMessage(5);
                        return;
                    }
                    z4 = this.a.r;
                    if (z4) {
                        if (z[1].equals(intent.getAction()) && intent.getIntExtra(z[2], 4) == 2) {
                            this.a.d = true;
                            this.a.m = false;
                            ac.b();
                            return;
                        }
                        return;
                    }
                    return;
                }
                return;
            }
        }
        if (intent.getIntExtra(z[2], 4) == 3) {
            jVar = this.a.s;
            WifiManager b = jVar.b();
            if (b != null) {
                z5 = b.startScan();
            }
            if (z5) {
                try {
                    d dVar2 = this.a;
                    jVar2 = this.a.s;
                    dVar2.A = jVar2.c();
                    context2 = this.a.j;
                    broadcastReceiver = this.a.n;
                    context2.unregisterReceiver(broadcastReceiver);
                    z3 = this.a.x;
                    if (!z3) {
                        this.a.d();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
