package cn.jpush.android.data;

import android.content.Context;
import android.support.v4.view.PointerIconCompat;
import cn.jpush.android.helpers.h;
import cn.jpush.android.util.ao;

/* loaded from: classes.dex */
final class k extends Thread {
    private static final String[] z;
    final /* synthetic */ Context a;
    final /* synthetic */ int b;
    final /* synthetic */ i c;
    final /* synthetic */ i d;

    /* JADX WARN: Removed duplicated region for block: B:12:0x0028 A[LOOP:1: B:7:0x0018->B:12:0x0028, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:15:0x0030  */
    /* JADX WARN: Removed duplicated region for block: B:20:0x004a  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x004d  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x0050  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x0053  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x002c A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:9:0x001f  */
    static {
        /*
            r12 = 2
            r4 = 1
            r1 = 0
            java.lang.String[] r3 = new java.lang.String[r12]
            java.lang.String r2 = "a V\u001c]~*"
            r0 = -1
            r5 = r3
            r6 = r3
            r3 = r1
        L_0x000b:
            char[] r2 = r2.toCharArray()
            int r7 = r2.length
            if (r7 > r4) goto L_0x0056
            r8 = r1
        L_0x0013:
            r9 = r2
            r10 = r8
            r14 = r7
            r7 = r2
            r2 = r14
        L_0x0018:
            char r13 = r7[r8]
            int r11 = r10 % 5
            switch(r11) {
                case 0: goto L_0x004a;
                case 1: goto L_0x004d;
                case 2: goto L_0x0050;
                case 3: goto L_0x0053;
                default: goto L_0x001f;
            }
        L_0x001f:
            r11 = r12
        L_0x0020:
            r11 = r11 ^ r13
            char r11 = (char) r11
            r7[r8] = r11
            int r8 = r10 + 1
            if (r2 != 0) goto L_0x002c
            r7 = r9
            r10 = r8
            r8 = r2
            goto L_0x0018
        L_0x002c:
            r7 = r2
            r2 = r9
        L_0x002e:
            if (r7 > r8) goto L_0x0013
            java.lang.String r7 = new java.lang.String
            r7.<init>(r2)
            java.lang.String r2 = r7.intern()
            switch(r0) {
                case 0: goto L_0x0045;
                default: goto L_0x003c;
            }
        L_0x003c:
            r5[r3] = r2
            java.lang.String r0 = "a V\u001c]~."
            r2 = r0
            r3 = r4
            r5 = r6
            r0 = r1
            goto L_0x000b
        L_0x0045:
            r5[r3] = r2
            cn.jpush.android.data.k.z = r6
            return
        L_0x004a:
            r11 = 23
            goto L_0x0020
        L_0x004d:
            r11 = 73
            goto L_0x0020
        L_0x0050:
            r11 = 51
            goto L_0x0020
        L_0x0053:
            r11 = 107(0x6b, float:1.5E-43)
            goto L_0x0020
        L_0x0056:
            r8 = r1
            goto L_0x002e
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.jpush.android.data.k.<clinit>():void");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public k(i iVar, Context context, int i, i iVar2) {
        this.d = iVar;
        this.a = context;
        this.b = i;
        this.c = iVar2;
    }

    @Override // java.lang.Thread, java.lang.Runnable
    public final void run() {
        if (h.a(this.d.L.b)) {
            this.d.L.l = c.a(this.d.L.b, this.d.c, z[0], this.a);
        }
        if (h.a(this.d.L.h)) {
            this.d.L.m = c.a(this.d.L.h, this.d.c, z[1], this.a);
        }
        if (ao.a(this.d.L.l) || ao.a(this.d.L.m)) {
            cn.jpush.android.helpers.k.a(this.d.c, PointerIconCompat.TYPE_HORIZONTAL_DOUBLE_ARROW, this.a);
            return;
        }
        cn.jpush.android.helpers.k.a(this.d.c, this.b, this.a);
        i.a(this.d, this.c, this.a);
    }
}
