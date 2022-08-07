package cn.jpush.android.a;

import android.net.wifi.ScanResult;
import cn.jpush.android.util.ac;
import cn.jpush.android.util.ao;
import org.json.JSONObject;

/* loaded from: classes.dex */
public final class k implements Comparable<k> {
    private static final String[] z;
    public final String a;
    public final int b;
    public final String c;
    final /* synthetic */ j d;

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
            case 2: goto L_0x0055;
            case 3: goto L_0x005d;
            case 4: goto L_0x0065;
            case 5: goto L_0x006d;
            default: goto L_0x003c;
        };
     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x003c, code lost:
        r3[r2] = r1;
        r2 = 1;
        r1 = "f_Y*BoZH\u0010Px";
        r0 = 0;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x0044, code lost:
        r3[r2] = r1;
        r2 = 2;
        r1 = "jY_";
        r0 = 1;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x004c, code lost:
        r3[r2] = r1;
        r2 = 3;
        r1 = "xW]\u001bBgaI\u0001QnP]\u0001K";
        r0 = 2;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:19:0x0055, code lost:
        r3[r2] = r1;
        r2 = 4;
        r1 = "'\u001eI\u0006Jo\u0003\u001d";
        r0 = 3;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x005d, code lost:
        r3[r2] = r1;
        r2 = 5;
        r1 = "'\u001e^7N6";
        r0 = 4;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x0065, code lost:
        r3[r2] = r1;
        r2 = 6;
        r1 = "\\W\\\u001cjeXU\u000eAxMS\u0011\u001e,";
        r0 = 5;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x006d, code lost:
        r3[r2] = r1;
        cn.jpush.android.a.k.z = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x0071, code lost:
        return;
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x0072, code lost:
        r9 = 11;
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x0075, code lost:
        r9 = '>';
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x0078, code lost:
        r9 = ':';
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x007b, code lost:
        r9 = 'u';
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
            case 0: goto L_0x0072;
            case 1: goto L_0x0075;
            case 2: goto L_0x0078;
            case 3: goto L_0x007b;
            default: goto L_0x001e;
        };
     */
    /* JADX WARN: Code restructure failed: missing block: B:8:0x001e, code lost:
        r9 = '#';
     */
    /* JADX WARN: Code restructure failed: missing block: B:9:0x0020, code lost:
        r5[r6] = (char) (r9 ^ r10);
        r6 = r8 + 1;
     */
    static {
        /*
            r0 = 7
            java.lang.String[] r3 = new java.lang.String[r0]
            r2 = 0
            java.lang.String r1 = "xMS\u0011"
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
                case 0: goto L_0x0072;
                case 1: goto L_0x0075;
                case 2: goto L_0x0078;
                case 3: goto L_0x007b;
                default: goto L_0x001e;
            }
        L_0x001e:
            r9 = 35
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
                case 2: goto L_0x0055;
                case 3: goto L_0x005d;
                case 4: goto L_0x0065;
                case 5: goto L_0x006d;
                default: goto L_0x003c;
            }
        L_0x003c:
            r3[r2] = r1
            r2 = 1
            java.lang.String r1 = "f_Y*BoZH\u0010Px"
            r0 = 0
            r3 = r4
            goto L_0x0009
        L_0x0044:
            r3[r2] = r1
            r2 = 2
            java.lang.String r1 = "jY_"
            r0 = 1
            r3 = r4
            goto L_0x0009
        L_0x004c:
            r3[r2] = r1
            r2 = 3
            java.lang.String r1 = "xW]\u001bBgaI\u0001QnP]\u0001K"
            r0 = 2
            r3 = r4
            goto L_0x0009
        L_0x0055:
            r3[r2] = r1
            r2 = 4
            java.lang.String r1 = "'\u001eI\u0006Jo\u0003\u001d"
            r0 = 3
            r3 = r4
            goto L_0x0009
        L_0x005d:
            r3[r2] = r1
            r2 = 5
            java.lang.String r1 = "'\u001e^7N6"
            r0 = 4
            r3 = r4
            goto L_0x0009
        L_0x0065:
            r3[r2] = r1
            r2 = 6
            java.lang.String r1 = "\\W\\\u001cjeXU\u000eAxMS\u0011\u001e,"
            r0 = 5
            r3 = r4
            goto L_0x0009
        L_0x006d:
            r3[r2] = r1
            cn.jpush.android.a.k.z = r4
            return
        L_0x0072:
            r9 = 11
            goto L_0x0020
        L_0x0075:
            r9 = 62
            goto L_0x0020
        L_0x0078:
            r9 = 58
            goto L_0x0020
        L_0x007b:
            r9 = 117(0x75, float:1.64E-43)
            goto L_0x0020
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.jpush.android.a.k.<clinit>():void");
    }

    public k(j jVar, ScanResult scanResult) {
        this.d = jVar;
        this.a = scanResult.BSSID;
        this.b = scanResult.level;
        this.c = ao.c(scanResult.SSID);
    }

    public k(j jVar, String str, int i, String str2) {
        this.d = jVar;
        this.a = str;
        this.b = i;
        this.c = ao.c(str2);
    }

    public final JSONObject a() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(z[1], this.a);
            jSONObject.put(z[3], this.b);
            jSONObject.put(z[0], this.c);
            jSONObject.put(z[2], 0);
        } catch (Exception e) {
            ac.i();
        }
        return jSONObject;
    }

    @Override // java.lang.Comparable
    public final /* bridge */ /* synthetic */ int compareTo(k kVar) {
        return kVar.b - this.b;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof k) {
            k kVar = (k) obj;
            if (kVar.b == this.b && kVar.a.equals(this.a)) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        return this.b ^ this.a.hashCode();
    }

    public final String toString() {
        return z[6] + this.a + '\'' + z[5] + this.b + z[4] + this.c + "'}";
    }
}
