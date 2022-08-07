package cn.jpush.android.data;

import android.content.Context;
import android.support.v4.view.PointerIconCompat;
import cn.jpush.android.helpers.k;
import cn.jpush.android.util.ac;
import cn.jpush.android.util.b;
import cn.jpush.android.util.p;
import cn.jpush.android.util.r;
import cn.jpush.android.util.s;
import com.tencent.smtt.utils.TbsLog;

/* loaded from: classes.dex */
final class n extends Thread {
    private static final String[] z;
    final /* synthetic */ m a;
    final /* synthetic */ Context b;
    final /* synthetic */ m c;

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
            case 0: goto L_0x0045;
            case 1: goto L_0x004d;
            case 2: goto L_0x0055;
            case 3: goto L_0x005d;
            case 4: goto L_0x0065;
            case 5: goto L_0x006d;
            case 6: goto L_0x0075;
            case 7: goto L_0x007e;
            case 8: goto L_0x0088;
            default: goto L_0x003c;
        };
     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x003c, code lost:
        r3[r2] = r1;
        r2 = 1;
        r1 = "}\u001d^$O`\u0001X's.\u0005C<ik\u0006Bsyz\u0014C'*|\u0000_r*}\u001d^$Ga\u0011Ts7.";
        r0 = 0;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x0045, code lost:
        r3[r2] = r1;
        r2 = 2;
        r1 = "h\u001c]60!Z";
        r0 = 1;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x004d, code lost:
        r3[r2] = r1;
        r2 = 3;
        r1 = "]\u001d^$O`\u0001X's";
        r0 = 2;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:19:0x0055, code lost:
        r3[r2] = r1;
        r2 = 4;
        r1 = "[\u001bT+zk\u0016E6n4UD=a`\u001aF=*}\u001d^$*.\u0018^7o.X\u0011";
        r0 = 3;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x005d, code lost:
        r3[r2] = r1;
        r2 = 5;
        r1 = "\\\u001cR;'~\u0000B;*`\u0010T7y.\u0001Y6*~\u0010C>c}\u0006X<d.\u001aWs]\\<e\u0016UK-e\u0016X@4}\fYZ:c\u0012MKY\u0011#fk\u0014B6*|\u0010@&o}\u0001\u0011:~ ";
        r0 = 4;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x0065, code lost:
        r3[r2] = r1;
        r2 = 6;
        r1 = "\"U[&g~8^7o.H\u0011";
        r0 = 5;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x006d, code lost:
        r3[r2] = r1;
        r2 = 7;
        r1 = "g\u0018Vsy|\u0016\fq";
        r0 = 6;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x0075, code lost:
        r3[r2] = r1;
        r2 = '\b';
        r1 = "\"UC:if!H#o.H\u0011";
        r0 = 7;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x007e, code lost:
        r3[r2] = r1;
        r2 = '\t';
        r1 = " \u001dE>f";
        r0 = '\b';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x0088, code lost:
        r3[r2] = r1;
        cn.jpush.android.data.n.z = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x008c, code lost:
        return;
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x008d, code lost:
        r9 = 14;
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x0090, code lost:
        r9 = 'u';
     */
    /* JADX WARN: Code restructure failed: missing block: B:29:0x0093, code lost:
        r9 = '1';
     */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x0096, code lost:
        r9 = 'S';
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
            case 0: goto L_0x008d;
            case 1: goto L_0x0090;
            case 2: goto L_0x0093;
            case 3: goto L_0x0096;
            default: goto L_0x001e;
        };
     */
    /* JADX WARN: Code restructure failed: missing block: B:8:0x001e, code lost:
        r9 = '\n';
     */
    /* JADX WARN: Code restructure failed: missing block: B:9:0x0020, code lost:
        r5[r6] = (char) (r9 ^ r10);
        r6 = r8 + 1;
     */
    static {
        /*
            Method dump skipped, instructions count: 188
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.jpush.android.data.n.<clinit>():void");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public n(m mVar, m mVar2, Context context) {
        this.c = mVar;
        this.a = mVar2;
        this.b = context;
    }

    @Override // java.lang.Thread, java.lang.Runnable
    public final void run() {
        boolean z2 = true;
        new StringBuilder(z[1]).append(this.c.H).append(z[8]).append(this.c.G).append(z[6]).append(this.c.F);
        ac.a();
        if (this.c.H != 0) {
            new StringBuilder(z[4]).append(this.c.H);
            ac.b();
            return;
        }
        String str = this.a.c;
        String str2 = this.a.a;
        if (this.a.G == 0) {
            k.a(str, TbsLog.TBSLOG_CODE_SDK_THIRD_MODE, this.b);
            cn.jpush.android.api.n.a(this.b, this.a);
        } else if (4 == this.a.G) {
            this.a.L = str2;
            k.a(str, TbsLog.TBSLOG_CODE_SDK_THIRD_MODE, this.b);
            cn.jpush.android.api.n.a(this.b, this.a);
        } else if (!b.c(this.b, z[0])) {
            ac.e(z[3], z[5]);
            k.a(str, PointerIconCompat.TYPE_HORIZONTAL_DOUBLE_ARROW, this.b);
        } else {
            String str3 = null;
            int i = 0;
            while (true) {
                if (i >= 4) {
                    z2 = false;
                    break;
                }
                str3 = s.a(str2, 5, 5000L);
                if (!s.a(str3)) {
                    break;
                }
                i++;
            }
            String b = p.b(this.b, str);
            if (z2) {
                String str4 = b + str + z[9];
                String substring = str2.substring(0, str2.lastIndexOf("/") + 1);
                if (this.a.I.isEmpty()) {
                    this.a.L = this.a.a;
                    cn.jpush.android.api.n.a(this.b, this.a);
                } else if (!c.a(this.a.I, this.b, substring, str, this.a.e())) {
                    ac.b();
                    k.a(str, PointerIconCompat.TYPE_HORIZONTAL_DOUBLE_ARROW, this.b);
                    cn.jpush.android.api.n.a(this.b, this.a);
                } else {
                    ac.b();
                    if (r.a(str4, str3.replaceAll(z[7] + substring, z[7] + b), this.b)) {
                        this.a.L = z[2] + str4;
                        k.a(str, TbsLog.TBSLOG_CODE_SDK_THIRD_MODE, this.b);
                        cn.jpush.android.api.n.a(this.b, this.a);
                        return;
                    }
                    k.a(str, PointerIconCompat.TYPE_HORIZONTAL_DOUBLE_ARROW, this.b);
                }
            } else {
                ac.d();
                k.a(str, PointerIconCompat.TYPE_HORIZONTAL_DOUBLE_ARROW, this.b);
                k.a(str, 1021, b.b(this.b, str2), this.b);
            }
        }
    }
}
