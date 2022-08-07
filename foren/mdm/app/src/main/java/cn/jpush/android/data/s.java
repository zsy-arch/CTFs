package cn.jpush.android.data;

import android.content.Context;
import cn.jpush.android.api.n;
import cn.jpush.android.helpers.k;
import cn.jpush.android.service.ServiceInterface;
import cn.jpush.android.util.ac;
import cn.jpush.android.util.b;
import com.tencent.smtt.utils.TbsLog;
import org.json.JSONObject;

/* loaded from: classes.dex */
public final class s extends c {
    private static final String[] J;
    public String E;
    public String F;
    public String G;
    public String H;
    public String I;
    public int a;

    /* JADX WARN: Code restructure failed: missing block: B:10:0x0025, code lost:
        if (r5 != 0) goto L_0x002b;
     */
    /* JADX WARN: Code restructure failed: missing block: B:11:0x0027, code lost:
        r5 = r1;
        r8 = r6;
        r6 = r5;
     */
    /* JADX WARN: Code restructure failed: missing block: B:12:0x002b, code lost:
        r5 = r5;
        r1 = r1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:13:0x002d, code lost:
        if (r5 > r6) goto L_0x0011;
     */
    /* JADX WARN: Code restructure failed: missing block: B:14:0x002f, code lost:
        r1 = new java.lang.String(r1).intern();
     */
    /* JADX WARN: Code restructure failed: missing block: B:15:0x0038, code lost:
        switch(r0) {
            case 0: goto L_0x0043;
            case 1: goto L_0x004b;
            case 2: goto L_0x0053;
            case 3: goto L_0x005b;
            case 4: goto L_0x0063;
            default: goto L_0x003b;
        };
     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x003b, code lost:
        r3[r2] = r1;
        r2 = 1;
        r1 = "i\u0003=[\u001d";
        r0 = 0;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x0043, code lost:
        r3[r2] = r1;
        r2 = 2;
        r1 = "i\u0003!G\u0017p";
        r0 = 1;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x004b, code lost:
        r3[r2] = r1;
        r2 = 3;
        r1 = "i\u0003<P\u0001z";
        r0 = 2;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:19:0x0053, code lost:
        r3[r2] = r1;
        r2 = 4;
        r1 = "i\u0003-\\\u0003s";
        r0 = 3;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x005b, code lost:
        r3[r2] = r1;
        r2 = 5;
        r1 = "J2#G\u001eh2h_\u0018{9'\t\u0005f,-\t\\?";
        r0 = 4;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x0063, code lost:
        r3[r2] = r1;
        cn.jpush.android.data.s.J = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x0067, code lost:
        return;
     */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x0068, code lost:
        r9 = 31;
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x006b, code lost:
        r9 = '\\';
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x006e, code lost:
        r9 = 'H';
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x0071, code lost:
        r9 = ')';
     */
    /* JADX WARN: Code restructure failed: missing block: B:4:0x000f, code lost:
        if (r5 <= 1) goto L_0x0011;
     */
    /* JADX WARN: Code restructure failed: missing block: B:5:0x0011, code lost:
        r8 = r6;
        r5 = r1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:6:0x0016, code lost:
        r10 = r5[r6];
     */
    /* JADX WARN: Code restructure failed: missing block: B:7:0x001a, code lost:
        switch((r8 % 5)) {
            case 0: goto L_0x0068;
            case 1: goto L_0x006b;
            case 2: goto L_0x006e;
            case 3: goto L_0x0071;
            default: goto L_0x001d;
        };
     */
    /* JADX WARN: Code restructure failed: missing block: B:8:0x001d, code lost:
        r9 = 'q';
     */
    /* JADX WARN: Code restructure failed: missing block: B:9:0x001f, code lost:
        r5[r6] = (char) (r9 ^ r10);
        r6 = r8 + 1;
     */
    static {
        /*
            r0 = 6
            java.lang.String[] r3 = new java.lang.String[r0]
            r2 = 0
            java.lang.String r1 = "i\u0003%MD"
            r0 = -1
            r4 = r3
        L_0x0008:
            char[] r1 = r1.toCharArray()
            int r5 = r1.length
            r6 = 0
            r7 = 1
            if (r5 > r7) goto L_0x002d
        L_0x0011:
            r7 = r1
            r8 = r6
            r11 = r5
            r5 = r1
            r1 = r11
        L_0x0016:
            char r10 = r5[r6]
            int r9 = r8 % 5
            switch(r9) {
                case 0: goto L_0x0068;
                case 1: goto L_0x006b;
                case 2: goto L_0x006e;
                case 3: goto L_0x0071;
                default: goto L_0x001d;
            }
        L_0x001d:
            r9 = 113(0x71, float:1.58E-43)
        L_0x001f:
            r9 = r9 ^ r10
            char r9 = (char) r9
            r5[r6] = r9
            int r6 = r8 + 1
            if (r1 != 0) goto L_0x002b
            r5 = r7
            r8 = r6
            r6 = r1
            goto L_0x0016
        L_0x002b:
            r5 = r1
            r1 = r7
        L_0x002d:
            if (r5 > r6) goto L_0x0011
            java.lang.String r5 = new java.lang.String
            r5.<init>(r1)
            java.lang.String r1 = r5.intern()
            switch(r0) {
                case 0: goto L_0x0043;
                case 1: goto L_0x004b;
                case 2: goto L_0x0053;
                case 3: goto L_0x005b;
                case 4: goto L_0x0063;
                default: goto L_0x003b;
            }
        L_0x003b:
            r3[r2] = r1
            r2 = 1
            java.lang.String r1 = "i\u0003=[\u001d"
            r0 = 0
            r3 = r4
            goto L_0x0008
        L_0x0043:
            r3[r2] = r1
            r2 = 2
            java.lang.String r1 = "i\u0003!G\u0017p"
            r0 = 1
            r3 = r4
            goto L_0x0008
        L_0x004b:
            r3[r2] = r1
            r2 = 3
            java.lang.String r1 = "i\u0003<P\u0001z"
            r0 = 2
            r3 = r4
            goto L_0x0008
        L_0x0053:
            r3[r2] = r1
            r2 = 4
            java.lang.String r1 = "i\u0003-\\\u0003s"
            r0 = 3
            r3 = r4
            goto L_0x0008
        L_0x005b:
            r3[r2] = r1
            r2 = 5
            java.lang.String r1 = "J2#G\u001eh2h_\u0018{9'\t\u0005f,-\t\\?"
            r0 = 4
            r3 = r4
            goto L_0x0008
        L_0x0063:
            r3[r2] = r1
            cn.jpush.android.data.s.J = r4
            return
        L_0x0068:
            r9 = 31
            goto L_0x001f
        L_0x006b:
            r9 = 92
            goto L_0x001f
        L_0x006e:
            r9 = 72
            goto L_0x001f
        L_0x0071:
            r9 = 41
            goto L_0x001f
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.jpush.android.data.s.<clinit>():void");
    }

    public s() {
        this.o = 2;
    }

    @Override // cn.jpush.android.data.c
    public final void a(Context context) {
        ac.a();
        k.a(this.c, TbsLog.TBSLOG_CODE_SDK_THIRD_MODE, context);
        if (this.a == 0) {
            if (b.b(context)) {
                ServiceInterface.a(context, this);
            }
        } else if (this.a == 1) {
            n.a(context, this);
        } else {
            new StringBuilder(J[5]).append(this.a);
            ac.b();
        }
    }

    @Override // cn.jpush.android.data.c
    public final boolean a(Context context, JSONObject jSONObject) {
        ac.a();
        this.a = jSONObject.optInt(J[3], 0);
        this.E = jSONObject.optString(J[1], "");
        this.F = jSONObject.optString(J[4], "");
        this.H = jSONObject.optString(J[0], "");
        this.G = jSONObject.optString(J[2], "");
        return true;
    }
}
