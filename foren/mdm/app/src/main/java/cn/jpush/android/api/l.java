package cn.jpush.android.api;

import android.app.Application;
import java.util.HashMap;

/* loaded from: classes.dex */
public final class l {
    private static String c;
    private static HashMap<String, Integer> d;
    private static String e;
    private static String f;
    private static String g;
    private static boolean b = true;
    public static boolean a = false;

    /* JADX WARN: Code restructure failed: missing block: B:10:0x0027, code lost:
        r1 = r0;
        r4 = r2;
        r2 = r1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:11:0x002b, code lost:
        r5 = 25;
     */
    /* JADX WARN: Code restructure failed: missing block: B:12:0x002e, code lost:
        r5 = '|';
     */
    /* JADX WARN: Code restructure failed: missing block: B:13:0x0031, code lost:
        r5 = 'n';
     */
    /* JADX WARN: Code restructure failed: missing block: B:14:0x0034, code lost:
        r5 = '\n';
     */
    /* JADX WARN: Code restructure failed: missing block: B:15:0x0037, code lost:
        r1 = r1;
        r0 = r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x0039, code lost:
        if (r1 > r2) goto L_0x0011;
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x003b, code lost:
        cn.jpush.android.api.l.c = new java.lang.String(r0).intern();
        cn.jpush.android.api.l.d = new java.util.HashMap<>();
        cn.jpush.android.api.l.e = null;
        cn.jpush.android.api.l.f = null;
        cn.jpush.android.api.l.g = null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x0053, code lost:
        return;
     */
    /* JADX WARN: Code restructure failed: missing block: B:3:0x000f, code lost:
        if (r1 <= 1) goto L_0x0011;
     */
    /* JADX WARN: Code restructure failed: missing block: B:4:0x0011, code lost:
        r4 = r2;
        r1 = r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:5:0x0016, code lost:
        r6 = r1[r2];
     */
    /* JADX WARN: Code restructure failed: missing block: B:6:0x001a, code lost:
        switch((r4 % 5)) {
            case 0: goto L_0x002b;
            case 1: goto L_0x002e;
            case 2: goto L_0x0031;
            case 3: goto L_0x0034;
            default: goto L_0x001d;
        };
     */
    /* JADX WARN: Code restructure failed: missing block: B:7:0x001d, code lost:
        r5 = 17;
     */
    /* JADX WARN: Code restructure failed: missing block: B:8:0x001f, code lost:
        r1[r2] = (char) (r5 ^ r6);
        r2 = r4 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:9:0x0025, code lost:
        if (r1 != 0) goto L_0x0037;
     */
    static {
        /*
            r3 = 1
            r2 = 0
            r7 = 0
            cn.jpush.android.api.l.b = r3
            cn.jpush.android.api.l.a = r2
            java.lang.String r0 = "诮坔惆毅主X\u001f\u001acgp\b\u0017皎~w.\u000bydt\u0019F#咝v\u0012>kdj\u0019F#皕j\t\u001eoc1U呠谉甹盡儏纱讫斨泌ｦ$Zdj\u0014'de|\u000e\bkr|R\u0001dC|\u000f\u001bgt1UN咆1S,\u001byyP\u0012\u001aoc\u007f\u001d\ro?v\u0012>kdj\u0019F#"
            char[] r0 = r0.toCharArray()
            int r1 = r0.length
            if (r1 > r3) goto L_0x0039
        L_0x0011:
            r3 = r0
            r4 = r2
            r8 = r1
            r1 = r0
            r0 = r8
        L_0x0016:
            char r6 = r1[r2]
            int r5 = r4 % 5
            switch(r5) {
                case 0: goto L_0x002b;
                case 1: goto L_0x002e;
                case 2: goto L_0x0031;
                case 3: goto L_0x0034;
                default: goto L_0x001d;
            }
        L_0x001d:
            r5 = 17
        L_0x001f:
            r5 = r5 ^ r6
            char r5 = (char) r5
            r1[r2] = r5
            int r2 = r4 + 1
            if (r0 != 0) goto L_0x0037
            r1 = r3
            r4 = r2
            r2 = r0
            goto L_0x0016
        L_0x002b:
            r5 = 25
            goto L_0x001f
        L_0x002e:
            r5 = 124(0x7c, float:1.74E-43)
            goto L_0x001f
        L_0x0031:
            r5 = 110(0x6e, float:1.54E-43)
            goto L_0x001f
        L_0x0034:
            r5 = 10
            goto L_0x001f
        L_0x0037:
            r1 = r0
            r0 = r3
        L_0x0039:
            if (r1 > r2) goto L_0x0011
            java.lang.String r1 = new java.lang.String
            r1.<init>(r0)
            java.lang.String r0 = r1.intern()
            cn.jpush.android.api.l.c = r0
            java.util.HashMap r0 = new java.util.HashMap
            r0.<init>()
            cn.jpush.android.api.l.d = r0
            cn.jpush.android.api.l.e = r7
            cn.jpush.android.api.l.f = r7
            cn.jpush.android.api.l.g = r7
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.jpush.android.api.l.<clinit>():void");
    }

    public static void a(Application application) {
        m mVar = new m();
        application.unregisterActivityLifecycleCallbacks(mVar);
        application.registerActivityLifecycleCallbacks(mVar);
    }
}
