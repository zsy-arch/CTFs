package cn.jpush.android.ui;

import android.content.Context;
import android.widget.ArrayAdapter;
import cn.jpush.android.data.c;
import java.util.List;

/* loaded from: classes.dex */
public class e extends ArrayAdapter<c> {
    private static final String[] z;
    private Context a;

    /* JADX WARN: Removed duplicated region for block: B:12:0x002a A[LOOP:1: B:7:0x001a->B:12:0x002a, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:15:0x0032  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x0056  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x0058  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x005b  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x005e  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x002e A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:9:0x0021  */
    static {
        /*
            r12 = 113(0x71, float:1.58E-43)
            r2 = 1
            r1 = 0
            r0 = 3
            java.lang.String[] r4 = new java.lang.String[r0]
            java.lang.String r3 = "\u0013\fX\u0001\u0014\u0003<H\u0002_HMZ\u000b\u0016"
            r0 = -1
            r5 = r4
            r6 = r4
            r4 = r1
        L_0x000d:
            char[] r3 = r3.toCharArray()
            int r7 = r3.length
            if (r7 > r2) goto L_0x0061
            r8 = r1
        L_0x0015:
            r9 = r3
            r10 = r8
            r14 = r7
            r7 = r3
            r3 = r14
        L_0x001a:
            char r13 = r7[r8]
            int r11 = r10 % 5
            switch(r11) {
                case 0: goto L_0x0056;
                case 1: goto L_0x0058;
                case 2: goto L_0x005b;
                case 3: goto L_0x005e;
                default: goto L_0x0021;
            }
        L_0x0021:
            r11 = r12
        L_0x0022:
            r11 = r11 ^ r13
            char r11 = (char) r11
            r7[r8] = r11
            int r8 = r10 + 1
            if (r3 != 0) goto L_0x002e
            r7 = r9
            r10 = r8
            r8 = r3
            goto L_0x001a
        L_0x002e:
            r7 = r3
            r3 = r9
        L_0x0030:
            if (r7 > r8) goto L_0x0015
            java.lang.String r7 = new java.lang.String
            r7.<init>(r3)
            java.lang.String r3 = r7.intern()
            switch(r0) {
                case 0: goto L_0x0047;
                case 1: goto L_0x0051;
                default: goto L_0x003e;
            }
        L_0x003e:
            r5[r4] = r3
            java.lang.String r0 = "\u0017\u0016F\t.\u0002\u0017K\u0017_\u0001\rM"
            r3 = r0
            r4 = r2
            r5 = r6
            r0 = r1
            goto L_0x000d
        L_0x0047:
            r5[r4] = r3
            r3 = 2
            java.lang.String r0 = "\u0016\u0006^E\u0013\u0018\u0017G\u0004\u0001Q\u0005K\f\u001d\u0014\u0007\nHQ\u0013\fX\u0001\u0014\u0003<H\u0002_HMZ\u000b\u0016"
            r4 = r3
            r5 = r6
            r3 = r0
            r0 = r2
            goto L_0x000d
        L_0x0051:
            r5[r4] = r3
            cn.jpush.android.ui.e.z = r6
            return
        L_0x0056:
            r11 = r12
            goto L_0x0022
        L_0x0058:
            r11 = 99
            goto L_0x0022
        L_0x005b:
            r11 = 42
            goto L_0x0022
        L_0x005e:
            r11 = 101(0x65, float:1.42E-43)
            goto L_0x0022
        L_0x0061:
            r8 = r1
            goto L_0x0030
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.jpush.android.ui.e.<clinit>():void");
    }

    public e(Context context, int i, List<c> list) {
        super(context, Integer.MIN_VALUE, list);
        this.a = null;
        this.a = context;
    }

    /* JADX WARN: Code restructure failed: missing block: B:0:?, code lost:
        r11 = r11;
     */
    @Override // android.widget.ArrayAdapter, android.widget.Adapter
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public android.view.View getView(int r10, android.view.View r11, android.view.ViewGroup r12) {
        /*
            Method dump skipped, instructions count: 463
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.jpush.android.ui.e.getView(int, android.view.View, android.view.ViewGroup):android.view.View");
    }
}
