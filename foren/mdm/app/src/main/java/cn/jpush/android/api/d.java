package cn.jpush.android.api;

import android.content.Context;
import cn.jpush.android.util.ac;
import cn.jpush.android.util.ah;
import cn.jpush.android.util.ai;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: classes.dex */
final class d extends Thread implements ai {
    private static final String z;
    final /* synthetic */ c a;

    /* JADX WARN: Code restructure failed: missing block: B:10:0x0021, code lost:
        r1 = r0;
        r4 = r2;
        r2 = r1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:11:0x0025, code lost:
        r1 = r1;
        r0 = r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:12:0x0027, code lost:
        if (r1 > r2) goto L_0x000b;
     */
    /* JADX WARN: Code restructure failed: missing block: B:13:0x0029, code lost:
        cn.jpush.android.api.d.z = new java.lang.String(r0).intern();
     */
    /* JADX WARN: Code restructure failed: missing block: B:14:0x0034, code lost:
        return;
     */
    /* JADX WARN: Code restructure failed: missing block: B:15:0x0035, code lost:
        r5 = ' ';
     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x0038, code lost:
        r5 = 'h';
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x003b, code lost:
        r5 = 'q';
     */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x003e, code lost:
        r5 = '^';
     */
    /* JADX WARN: Code restructure failed: missing block: B:3:0x0009, code lost:
        if (r1 <= 1) goto L_0x000b;
     */
    /* JADX WARN: Code restructure failed: missing block: B:4:0x000b, code lost:
        r4 = r2;
        r1 = r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:5:0x0010, code lost:
        r6 = r1[r2];
     */
    /* JADX WARN: Code restructure failed: missing block: B:6:0x0014, code lost:
        switch((r4 % 5)) {
            case 0: goto L_0x0035;
            case 1: goto L_0x0038;
            case 2: goto L_0x003b;
            case 3: goto L_0x003e;
            default: goto L_0x0017;
        };
     */
    /* JADX WARN: Code restructure failed: missing block: B:7:0x0017, code lost:
        r5 = 'l';
     */
    /* JADX WARN: Code restructure failed: missing block: B:8:0x0019, code lost:
        r1[r2] = (char) (r5 ^ r6);
        r2 = r4 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:9:0x001f, code lost:
        if (r1 != 0) goto L_0x0025;
     */
    static {
        /*
            java.lang.String r0 = "r\r\u00011\u001eT,\u0018,\tC\u001cQ;\u001eR\u0007\u0003\u001d\u0003D\rK"
            char[] r0 = r0.toCharArray()
            int r1 = r0.length
            r2 = 0
            r3 = 1
            if (r1 > r3) goto L_0x0027
        L_0x000b:
            r3 = r0
            r4 = r2
            r7 = r1
            r1 = r0
            r0 = r7
        L_0x0010:
            char r6 = r1[r2]
            int r5 = r4 % 5
            switch(r5) {
                case 0: goto L_0x0035;
                case 1: goto L_0x0038;
                case 2: goto L_0x003b;
                case 3: goto L_0x003e;
                default: goto L_0x0017;
            }
        L_0x0017:
            r5 = 108(0x6c, float:1.51E-43)
        L_0x0019:
            r5 = r5 ^ r6
            char r5 = (char) r5
            r1[r2] = r5
            int r2 = r4 + 1
            if (r0 != 0) goto L_0x0025
            r1 = r3
            r4 = r2
            r2 = r0
            goto L_0x0010
        L_0x0025:
            r1 = r0
            r0 = r3
        L_0x0027:
            if (r1 > r2) goto L_0x000b
            java.lang.String r1 = new java.lang.String
            r1.<init>(r0)
            java.lang.String r0 = r1.intern()
            cn.jpush.android.api.d.z = r0
            return
        L_0x0035:
            r5 = 32
            goto L_0x0019
        L_0x0038:
            r5 = 104(0x68, float:1.46E-43)
            goto L_0x0019
        L_0x003b:
            r5 = 113(0x71, float:1.58E-43)
            goto L_0x0019
        L_0x003e:
            r5 = 94
            goto L_0x0019
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.jpush.android.api.d.<clinit>():void");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public d(c cVar) {
        this.a = cVar;
    }

    @Override // cn.jpush.android.util.ai
    public final void a(int i) {
        Context context;
        new StringBuilder(z).append(i);
        ac.d();
        if (i == 0) {
            context = this.a.f;
            c.d(context);
        }
    }

    @Override // java.lang.Thread, java.lang.Runnable
    public final void run() {
        Context context;
        Context context2;
        boolean h;
        Context context3;
        Context context4;
        context = this.a.f;
        if (context == null) {
            ac.d();
            return;
        }
        c cVar = this.a;
        context2 = this.a.f;
        h = cVar.h(context2);
        if (h) {
            context3 = this.a.f;
            JSONObject f = c.f(context3);
            if (f != null) {
                context4 = this.a.f;
                ah.a(context4, new JSONArray().put(f), this);
            }
            this.a.b();
            return;
        }
        ac.a();
    }
}
