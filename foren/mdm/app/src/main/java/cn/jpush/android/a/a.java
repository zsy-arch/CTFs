package cn.jpush.android.a;

import cn.jpush.android.util.ac;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class a {
    private static final String z;
    private int a;
    private int b;
    private int c;
    private int d;
    private String e;
    private double f;
    private double g;

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
        cn.jpush.android.a.a.z = new java.lang.String(r0).intern();
     */
    /* JADX WARN: Code restructure failed: missing block: B:14:0x0034, code lost:
        return;
     */
    /* JADX WARN: Code restructure failed: missing block: B:15:0x0035, code lost:
        r5 = ':';
     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x0038, code lost:
        r5 = '7';
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x003b, code lost:
        r5 = 'm';
     */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x003e, code lost:
        r5 = 'p';
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
        r5 = ' ';
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
            java.lang.String r0 = "A\u0015\u000e\u0015LV~\tR\u001a\u001fSARMUU\u0004\u001cEyX\u0018\u001eTHN.\u001fD_\u0015WUD\u0016\u0015\u0001\u001fC[C\u0004\u001fN{E\b\u0011cUS\bR\u001a\u001fSARMUU\u0004\u001cEtR\u0019\u0007OH\\.\u001fD_\u0015WUD\u0016\u0015\u001f\u0011DSX9\tP_\u0015WR\u0005I\u0015ARL[COJ\u0005\\\u001bO\u001cN]\u0015WUFG"
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
            r5 = 32
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
            cn.jpush.android.a.a.z = r0
            return
        L_0x0035:
            r5 = 58
            goto L_0x0019
        L_0x0038:
            r5 = 55
            goto L_0x0019
        L_0x003b:
            r5 = 109(0x6d, float:1.53E-43)
            goto L_0x0019
        L_0x003e:
            r5 = 112(0x70, float:1.57E-43)
            goto L_0x0019
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.jpush.android.a.a.<clinit>():void");
    }

    public final int a() {
        return this.a;
    }

    public final void a(int i) {
        this.a = i;
    }

    public final void a(String str) {
        this.e = str;
    }

    public final JSONArray b() {
        JSONArray jSONArray = new JSONArray();
        try {
            jSONArray.put(new JSONObject(toString()));
            return jSONArray;
        } catch (JSONException e) {
            a.class.getSimpleName();
            e.getMessage();
            ac.e();
            return null;
        }
    }

    public final void b(int i) {
        this.b = i;
    }

    public final void c(int i) {
        this.c = i;
    }

    public final void d(int i) {
        this.d = i;
    }

    public String toString() {
        try {
            return String.format(z, Integer.valueOf(this.a), Integer.valueOf(this.b), Integer.valueOf(this.d), Integer.valueOf(this.c), this.e, Double.valueOf(this.f), Double.valueOf(this.g));
        } catch (Exception e) {
            return "";
        }
    }
}
