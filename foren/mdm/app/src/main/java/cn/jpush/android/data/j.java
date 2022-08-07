package cn.jpush.android.data;

import android.content.Context;
import android.support.v4.view.PointerIconCompat;
import cn.jpush.android.helpers.h;
import cn.jpush.android.helpers.k;
import cn.jpush.android.util.ac;
import cn.jpush.android.util.b;
import cn.jpush.android.util.p;
import cn.jpush.android.util.r;
import cn.jpush.android.util.s;
import com.tencent.smtt.utils.TbsLog;

/* loaded from: classes.dex */
final class j extends Thread {
    private static final String[] z;
    final /* synthetic */ i a;
    final /* synthetic */ Context b;
    final /* synthetic */ int c;
    final /* synthetic */ i d;

    /* JADX WARN: Removed duplicated region for block: B:12:0x0029 A[LOOP:1: B:7:0x0018->B:12:0x0029, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:15:0x0031  */
    /* JADX WARN: Removed duplicated region for block: B:20:0x004b  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x004e  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x0051  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x0054  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x002d A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:9:0x001f  */
    static {
        /*
            r4 = 1
            r1 = 0
            r0 = 2
            java.lang.String[] r3 = new java.lang.String[r0]
            java.lang.String r2 = "RG@iT"
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
            r13 = r7
            r7 = r2
            r2 = r13
        L_0x0018:
            char r12 = r7[r8]
            int r11 = r10 % 5
            switch(r11) {
                case 0: goto L_0x004b;
                case 1: goto L_0x004e;
                case 2: goto L_0x0051;
                case 3: goto L_0x0054;
                default: goto L_0x001f;
            }
        L_0x001f:
            r11 = 56
        L_0x0021:
            r11 = r11 ^ r12
            char r11 = (char) r11
            r7[r8] = r11
            int r8 = r10 + 1
            if (r2 != 0) goto L_0x002d
            r7 = r9
            r10 = r8
            r8 = r2
            goto L_0x0018
        L_0x002d:
            r7 = r2
            r2 = r9
        L_0x002f:
            if (r7 > r8) goto L_0x0013
            java.lang.String r7 = new java.lang.String
            r7.<init>(r2)
            java.lang.String r2 = r7.intern()
            switch(r0) {
                case 0: goto L_0x0046;
                default: goto L_0x003d;
            }
        L_0x003d:
            r5[r3] = r2
            java.lang.String r0 = "\u001aFXa\u0002S\u0000"
            r2 = r0
            r3 = r4
            r5 = r6
            r0 = r1
            goto L_0x000b
        L_0x0046:
            r5[r3] = r2
            cn.jpush.android.data.j.z = r6
            return
        L_0x004b:
            r11 = 124(0x7c, float:1.74E-43)
            goto L_0x0021
        L_0x004e:
            r11 = 47
            goto L_0x0021
        L_0x0051:
            r11 = 52
            goto L_0x0021
        L_0x0054:
            r11 = 4
            goto L_0x0021
        L_0x0056:
            r8 = r1
            goto L_0x002f
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.jpush.android.data.j.<clinit>():void");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public j(i iVar, i iVar2, Context context, int i) {
        this.d = iVar;
        this.a = iVar2;
        this.b = context;
        this.c = i;
    }

    @Override // java.lang.Thread, java.lang.Runnable
    public final void run() {
        boolean z2;
        String str = this.a.L.j;
        String str2 = this.a.c;
        if (!h.a(str)) {
            k.a(this.a.c, TbsLog.TBSLOG_CODE_SDK_SELF_MODE, this.b);
        } else if (!this.a.L.i) {
            k.a(str2, this.c, this.b);
            i.a(this.d, this.a, this.b);
        } else {
            String str3 = null;
            int i = 0;
            while (true) {
                if (i >= 4) {
                    z2 = false;
                    break;
                }
                str3 = s.a(str, 5, 5000L);
                if (!s.a(str3)) {
                    str3 = str3;
                    z2 = true;
                    break;
                }
                i++;
            }
            if (!z2) {
                k.a(str2, PointerIconCompat.TYPE_HORIZONTAL_DOUBLE_ARROW, this.b);
                k.a(str2, 1021, b.b(this.b, str), this.b);
                ac.b();
                return;
            }
            if (!c.a(this.a.L.k, this.b, str.substring(0, str.lastIndexOf("/") + 1), str2, this.a.e())) {
                ac.b();
                k.a(str2, PointerIconCompat.TYPE_HORIZONTAL_DOUBLE_ARROW, this.b);
                return;
            }
            String str4 = this.a.e() ? p.b(this.b, str2) + str2 + z[0] : p.a(this.b, str2) + str2;
            if (r.a(str4, str3, this.b)) {
                this.a.L.n = z[1] + str4;
                k.a(str2, this.c, this.b);
                i.a(this.d, this.a, this.b);
                return;
            }
            k.a(str2, PointerIconCompat.TYPE_HORIZONTAL_DOUBLE_ARROW, this.b);
        }
    }
}
