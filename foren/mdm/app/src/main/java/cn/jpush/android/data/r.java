package cn.jpush.android.data;

import android.content.Context;
import cn.jpush.android.api.n;
import cn.jpush.android.helpers.h;
import cn.jpush.android.service.ServiceInterface;
import cn.jpush.android.util.ac;
import cn.jpush.android.util.b;
import org.json.JSONObject;

/* loaded from: classes.dex */
public final class r extends i {
    private static final String[] R;

    /* JADX WARN: Removed duplicated region for block: B:12:0x0029 A[LOOP:1: B:7:0x0018->B:12:0x0029, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:15:0x0031  */
    /* JADX WARN: Removed duplicated region for block: B:20:0x004b  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x004e  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x0050  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x0053  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x002d A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:9:0x001f  */
    static {
        /*
            r4 = 1
            r1 = 0
            r0 = 2
            java.lang.String[] r3 = new java.lang.String[r0]
            java.lang.String r2 = "\\i[hb~i\u0010uefp\u0010kbmb\u0010+-"
            r0 = -1
            r5 = r3
            r6 = r3
            r3 = r1
        L_0x000b:
            char[] r2 = r2.toCharArray()
            int r7 = r2.length
            if (r7 > r4) goto L_0x0055
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
                case 2: goto L_0x0050;
                case 3: goto L_0x0053;
                default: goto L_0x001f;
            }
        L_0x001f:
            r11 = 13
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
            java.lang.String r0 = "hw[Y~ahG"
            r2 = r0
            r3 = r4
            r5 = r6
            r0 = r1
            goto L_0x000b
        L_0x0046:
            r5[r3] = r2
            cn.jpush.android.data.r.R = r6
            return
        L_0x004b:
            r11 = 9
            goto L_0x0021
        L_0x004e:
            r11 = 7
            goto L_0x0021
        L_0x0050:
            r11 = 48
            goto L_0x0021
        L_0x0053:
            r11 = 6
            goto L_0x0021
        L_0x0055:
            r8 = r1
            goto L_0x002f
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.jpush.android.data.r.<clinit>():void");
    }

    public r() {
        this.o = 3;
        this.L = null;
    }

    @Override // cn.jpush.android.data.i, cn.jpush.android.data.c
    public final void a(Context context) {
        ac.a();
        if (h.a(this.G, this.H, context)) {
            ServiceInterface.a(context, this);
        } else if (this.J == 1) {
            context.startActivity(b.a(context, (c) this, true));
        } else if (this.J == 0) {
            n.b(context, this);
        } else {
            new StringBuilder(R[0]).append(this.J);
            ac.b();
        }
    }

    @Override // cn.jpush.android.data.i, cn.jpush.android.data.c
    public final boolean a(Context context, JSONObject jSONObject) {
        ac.a();
        boolean a = super.a(context, jSONObject);
        this.J = jSONObject.optInt(R[1], 0);
        return a;
    }
}
