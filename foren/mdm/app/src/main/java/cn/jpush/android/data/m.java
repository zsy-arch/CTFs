package cn.jpush.android.data;

import android.content.Context;
import cn.jpush.android.helpers.h;
import cn.jpush.android.util.ac;
import cn.jpush.android.util.l;
import java.util.ArrayList;
import org.json.JSONObject;

/* loaded from: classes.dex */
public final class m extends c {
    private static final String[] M;
    public String E;
    public int F;
    public int G;
    public int H;
    public ArrayList<String> I = new ArrayList<>();
    public String J = "";
    public String K = "";
    public String L;
    public String a;

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
        if (r5 > r6) goto L_0x0012;
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
            case 5: goto L_0x006b;
            case 6: goto L_0x0073;
            case 7: goto L_0x007c;
            case 8: goto L_0x0086;
            default: goto L_0x003b;
        };
     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x003b, code lost:
        r3[r2] = r1;
        r2 = 1;
        r1 = "OM\u00041k";
        r0 = 0;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x0043, code lost:
        r3[r2] = r1;
        r2 = 2;
        r1 = "L`\u001e.XDg\u001c";
        r0 = 1;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x004b, code lost:
        r3[r2] = r1;
        r2 = 3;
        r1 = "OM\u0003*dBM\u0005:wO";
        r0 = 2;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:19:0x0053, code lost:
        r3[r2] = r1;
        r2 = 4;
        r1 = "Bf\u00053=\u0005=";
        r0 = 3;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x005b, code lost:
        r3[r2] = r1;
        r2 = 5;
        r1 = "OM\u00141bY";
        r0 = 4;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x0063, code lost:
        r3[r2] = r1;
        r2 = 6;
        r1 = "OM\u0005*sFw";
        r0 = 5;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x006b, code lost:
        r3[r2] = r1;
        r2 = 7;
        r1 = "OM\u001b6jZM\u001c,cO";
        r0 = 6;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x0073, code lost:
        r3[r2] = r1;
        r2 = '\b';
        r1 = "OM\u0002+h]";
        r0 = 7;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x007c, code lost:
        r3[r2] = r1;
        r2 = '\t';
        r1 = "kv\u0015co^f\u0001csE2\u001f,i\u0007b\u0003&aCjQ6uF(Q";
        r0 = '\b';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x0086, code lost:
        r3[r2] = r1;
        cn.jpush.android.data.m.M = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x008a, code lost:
        return;
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x008b, code lost:
        r9 = '*';
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x008e, code lost:
        r9 = 18;
     */
    /* JADX WARN: Code restructure failed: missing block: B:29:0x0091, code lost:
        r9 = 'q';
     */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x0094, code lost:
        r9 = 'C';
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
            case 0: goto L_0x008b;
            case 1: goto L_0x008e;
            case 2: goto L_0x0091;
            case 3: goto L_0x0094;
            default: goto L_0x001e;
        };
     */
    /* JADX WARN: Code restructure failed: missing block: B:8:0x001e, code lost:
        r9 = 7;
     */
    /* JADX WARN: Code restructure failed: missing block: B:9:0x001f, code lost:
        r5[r6] = (char) (r9 ^ r10);
        r6 = r8 + 1;
     */
    static {
        /*
            Method dump skipped, instructions count: 186
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.jpush.android.data.m.<clinit>():void");
    }

    public m() {
        this.o = 0;
    }

    @Override // cn.jpush.android.data.c
    public final void a(Context context) {
        ac.a();
        new n(this, this, context).start();
    }

    @Override // cn.jpush.android.data.c
    public final boolean a(Context context, JSONObject jSONObject) {
        ac.a();
        this.a = jSONObject.optString(M[1], "").trim();
        this.E = jSONObject.optString(M[6], "").trim();
        if (!h.a(this.a)) {
            this.a = M[4] + this.a;
            new StringBuilder(M[9]).append(this.a);
            ac.c();
        }
        this.G = jSONObject.optInt(M[3], 0);
        this.F = jSONObject.optInt(M[7], 0);
        this.H = jSONObject.optInt(M[8], 0);
        if (3 == this.G || 2 == this.G || 1 == this.G) {
            this.I = l.a(jSONObject.optJSONArray(M[5]));
        }
        this.J = jSONObject.optString(M[2], "");
        this.K = jSONObject.optString(M[0], "");
        return true;
    }
}
