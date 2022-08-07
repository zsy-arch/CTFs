package cn.jpush.android.data;

import cn.jpush.android.util.ao;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public final class e {
    private static final String[] z;
    public int a;
    public String b;
    public String c;
    public String d;
    public String e;

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
            case 0: goto L_0x0044;
            case 1: goto L_0x004c;
            case 2: goto L_0x0054;
            case 3: goto L_0x005c;
            case 4: goto L_0x0064;
            case 5: goto L_0x006c;
            case 6: goto L_0x0074;
            default: goto L_0x003c;
        };
     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x003c, code lost:
        r3[r2] = r1;
        r2 = 1;
        r1 = "1pK\u0004";
        r0 = 0;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x0044, code lost:
        r3[r2] = r1;
        r2 = 2;
        r1 = "1xA";
        r0 = 1;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x004c, code lost:
        r3[r2] = r1;
        r2 = 3;
        r1 = "(jA";
        r0 = 2;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:19:0x0054, code lost:
        r3[r2] = r1;
        r2 = 4;
        r1 = ")|P\u0004S";
        r0 = 3;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x005c, code lost:
        r3[r2] = r1;
        r2 = 5;
        r1 = "e9";
        r0 = 4;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x0064, code lost:
        r3[r2] = r1;
        r2 = 6;
        r1 = "fsV\u0014L-:";
        r0 = 5;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x006c, code lost:
        r3[r2] = r1;
        r2 = 7;
        r1 = "\u0019w";
        r0 = 6;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x0074, code lost:
        r3[r2] = r1;
        cn.jpush.android.data.e.z = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x0078, code lost:
        return;
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x0079, code lost:
        r9 = 'E';
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x007c, code lost:
        r9 = 25;
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x007f, code lost:
        r9 = '&';
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x0082, code lost:
        r9 = 'a';
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
            case 0: goto L_0x0079;
            case 1: goto L_0x007c;
            case 2: goto L_0x007f;
            case 3: goto L_0x0082;
            default: goto L_0x001e;
        };
     */
    /* JADX WARN: Code restructure failed: missing block: B:8:0x001e, code lost:
        r9 = '?';
     */
    /* JADX WARN: Code restructure failed: missing block: B:9:0x0020, code lost:
        r5[r6] = (char) (r9 ^ r10);
        r6 = r8 + 1;
     */
    static {
        /*
            r0 = 8
            java.lang.String[] r3 = new java.lang.String[r0]
            r2 = 0
            java.lang.String r1 = ")|P\u0004S6mT"
            r0 = -1
            r4 = r3
        L_0x0009:
            char[] r1 = r1.toCharArray()
            int r5 = r1.length
            r6 = 0
            r7 = 1
            if (r5 > r7) goto L_0x002e
        L_0x0012:
            r7 = r1
            r8 = r6
            r11 = r5
            r5 = r1
            r1 = r11
        L_0x0017:
            char r10 = r5[r6]
            int r9 = r8 % 5
            switch(r9) {
                case 0: goto L_0x0079;
                case 1: goto L_0x007c;
                case 2: goto L_0x007f;
                case 3: goto L_0x0082;
                default: goto L_0x001e;
            }
        L_0x001e:
            r9 = 63
        L_0x0020:
            r9 = r9 ^ r10
            char r9 = (char) r9
            r5[r6] = r9
            int r6 = r8 + 1
            if (r1 != 0) goto L_0x002c
            r5 = r7
            r8 = r6
            r6 = r1
            goto L_0x0017
        L_0x002c:
            r5 = r1
            r1 = r7
        L_0x002e:
            if (r5 > r6) goto L_0x0012
            java.lang.String r5 = new java.lang.String
            r5.<init>(r1)
            java.lang.String r1 = r5.intern()
            switch(r0) {
                case 0: goto L_0x0044;
                case 1: goto L_0x004c;
                case 2: goto L_0x0054;
                case 3: goto L_0x005c;
                case 4: goto L_0x0064;
                case 5: goto L_0x006c;
                case 6: goto L_0x0074;
                default: goto L_0x003c;
            }
        L_0x003c:
            r3[r2] = r1
            r2 = 1
            java.lang.String r1 = "1pK\u0004"
            r0 = 0
            r3 = r4
            goto L_0x0009
        L_0x0044:
            r3[r2] = r1
            r2 = 2
            java.lang.String r1 = "1xA"
            r0 = 1
            r3 = r4
            goto L_0x0009
        L_0x004c:
            r3[r2] = r1
            r2 = 3
            java.lang.String r1 = "(jA"
            r0 = 2
            r3 = r4
            goto L_0x0009
        L_0x0054:
            r3[r2] = r1
            r2 = 4
            java.lang.String r1 = ")|P\u0004S"
            r0 = 3
            r3 = r4
            goto L_0x0009
        L_0x005c:
            r3[r2] = r1
            r2 = 5
            java.lang.String r1 = "e9"
            r0 = 4
            r3 = r4
            goto L_0x0009
        L_0x0064:
            r3[r2] = r1
            r2 = 6
            java.lang.String r1 = "fsV\u0014L-:"
            r0 = 5
            r3 = r4
            goto L_0x0009
        L_0x006c:
            r3[r2] = r1
            r2 = 7
            java.lang.String r1 = "\u0019w"
            r0 = 6
            r3 = r4
            goto L_0x0009
        L_0x0074:
            r3[r2] = r1
            cn.jpush.android.data.e.z = r4
            return
        L_0x0079:
            r9 = 69
            goto L_0x0020
        L_0x007c:
            r9 = 25
            goto L_0x0020
        L_0x007f:
            r9 = 38
            goto L_0x0020
        L_0x0082:
            r9 = 97
            goto L_0x0020
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.jpush.android.data.e.<clinit>():void");
    }

    public e() {
    }

    public e(int i, String str, String str2, String str3, String str4) {
        this.a = i;
        this.e = str;
        this.c = str3;
        this.b = str2;
        this.d = str4;
    }

    public final int a() {
        return toString().getBytes().length;
    }

    public final JSONObject b() {
        boolean z2 = true;
        z2 = false;
        JSONObject jSONObject = new JSONObject();
        if (ao.a(this.d) || ao.a(this.e) || ao.a(this.b) || ao.a(this.c)) {
        }
        if (!z2) {
            return null;
        }
        try {
            jSONObject.put(z[4], this.a);
            jSONObject.put(z[0], this.e);
            jSONObject.put(z[1], this.d);
            jSONObject.put(z[2], this.b);
            jSONObject.put(z[3], this.c);
            return jSONObject;
        } catch (JSONException e) {
            return null;
        }
    }

    public final boolean equals(Object obj) {
        return super.equals(obj);
    }

    public final int hashCode() {
        return super.hashCode();
    }

    public final String toString() {
        if (this.c != null && this.c.contains(z[7])) {
            this.c.replaceAll(z[7], z[6]);
        }
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(this.a).append(z[5]);
        stringBuffer.append(this.e).append(z[5]);
        stringBuffer.append(this.d).append(z[5]);
        stringBuffer.append(this.b).append(z[5]);
        stringBuffer.append(this.c).append(z[5]);
        return stringBuffer.toString();
    }
}
