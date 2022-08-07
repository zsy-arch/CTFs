package cn.jpush.android.a;

import android.content.Context;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import cn.jpush.android.util.ac;
import cn.jpush.android.util.b;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: classes.dex */
public final class j {
    private static final String[] z;
    private WifiManager a;
    private Context b;

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
            default: goto L_0x003b;
        };
     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x003b, code lost:
        r3[r2] = r1;
        r2 = 1;
        r1 = "\r#\\\u00149";
        r0 = 0;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x0043, code lost:
        r3[r2] = r1;
        r2 = 2;
        r1 = "\u001b$^\u000fl\u0013.\u0014\rf\b'S\u000ep\u0013%TSB9\t\u007f.P%\tu<Q)\u000fe1L9\u000bn4L4";
        r0 = 1;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x004b, code lost:
        r3[r2] = r1;
        r2 = 3;
        r1 = "\u000e+]";
        r0 = 2;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:19:0x0053, code lost:
        r3[r2] = r1;
        r2 = 4;
        r1 = "\u0019?H\u000ff\u0014>m\u0014e\u0013p";
        r0 = 3;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x005b, code lost:
        r3[r2] = r1;
        r2 = 5;
        r1 = "\t>H\u0012m\u001d/I\t";
        r0 = 4;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x0063, code lost:
        r3[r2] = r1;
        r2 = 6;
        r1 = "\u000f$_\u0005s\u001f)N\u0018g[";
        r0 = 5;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x006b, code lost:
        r3[r2] = r1;
        r2 = 7;
        r1 = "\r#\\\u0014#\u0019%O\u0013w@";
        r0 = 6;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x0073, code lost:
        r3[r2] = r1;
        r2 = '\b';
        r1 = "\r#\\\u0014";
        r0 = 7;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x007c, code lost:
        r3[r2] = r1;
        cn.jpush.android.a.j.z = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x0080, code lost:
        return;
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x0081, code lost:
        r9 = 'z';
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x0084, code lost:
        r9 = 'J';
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x0087, code lost:
        r9 = ':';
     */
    /* JADX WARN: Code restructure failed: missing block: B:29:0x008a, code lost:
        r9 = '}';
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
            case 0: goto L_0x0081;
            case 1: goto L_0x0084;
            case 2: goto L_0x0087;
            case 3: goto L_0x008a;
            default: goto L_0x001e;
        };
     */
    /* JADX WARN: Code restructure failed: missing block: B:8:0x001e, code lost:
        r9 = 3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:9:0x001f, code lost:
        r5[r6] = (char) (r9 ^ r10);
        r6 = r8 + 1;
     */
    static {
        /*
            r0 = 9
            java.lang.String[] r3 = new java.lang.String[r0]
            r2 = 0
            java.lang.String r1 = "\u0019%T\u0013f\u0019>"
            r0 = -1
            r4 = r3
        L_0x0009:
            char[] r1 = r1.toCharArray()
            int r5 = r1.length
            r6 = 0
            r7 = 1
            if (r5 > r7) goto L_0x002d
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
                case 0: goto L_0x0081;
                case 1: goto L_0x0084;
                case 2: goto L_0x0087;
                case 3: goto L_0x008a;
                default: goto L_0x001e;
            }
        L_0x001e:
            r9 = 3
        L_0x001f:
            r9 = r9 ^ r10
            char r9 = (char) r9
            r5[r6] = r9
            int r6 = r8 + 1
            if (r1 != 0) goto L_0x002b
            r5 = r7
            r8 = r6
            r6 = r1
            goto L_0x0017
        L_0x002b:
            r5 = r1
            r1 = r7
        L_0x002d:
            if (r5 > r6) goto L_0x0012
            java.lang.String r5 = new java.lang.String
            r5.<init>(r1)
            java.lang.String r1 = r5.intern()
            switch(r0) {
                case 0: goto L_0x0043;
                case 1: goto L_0x004b;
                case 2: goto L_0x0053;
                case 3: goto L_0x005b;
                case 4: goto L_0x0063;
                case 5: goto L_0x006b;
                case 6: goto L_0x0073;
                case 7: goto L_0x007c;
                default: goto L_0x003b;
            }
        L_0x003b:
            r3[r2] = r1
            r2 = 1
            java.lang.String r1 = "\r#\\\u00149"
            r0 = 0
            r3 = r4
            goto L_0x0009
        L_0x0043:
            r3[r2] = r1
            r2 = 2
            java.lang.String r1 = "\u001b$^\u000fl\u0013.\u0014\rf\b'S\u000ep\u0013%TSB9\t\u007f.P%\tu<Q)\u000fe1L9\u000bn4L4"
            r0 = 1
            r3 = r4
            goto L_0x0009
        L_0x004b:
            r3[r2] = r1
            r2 = 3
            java.lang.String r1 = "\u000e+]"
            r0 = 2
            r3 = r4
            goto L_0x0009
        L_0x0053:
            r3[r2] = r1
            r2 = 4
            java.lang.String r1 = "\u0019?H\u000ff\u0014>m\u0014e\u0013p"
            r0 = 3
            r3 = r4
            goto L_0x0009
        L_0x005b:
            r3[r2] = r1
            r2 = 5
            java.lang.String r1 = "\t>H\u0012m\u001d/I\t"
            r0 = 4
            r3 = r4
            goto L_0x0009
        L_0x0063:
            r3[r2] = r1
            r2 = 6
            java.lang.String r1 = "\u000f$_\u0005s\u001f)N\u0018g["
            r0 = 5
            r3 = r4
            goto L_0x0009
        L_0x006b:
            r3[r2] = r1
            r2 = 7
            java.lang.String r1 = "\r#\\\u0014#\u0019%O\u0013w@"
            r0 = 6
            r3 = r4
            goto L_0x0009
        L_0x0073:
            r3[r2] = r1
            r2 = 8
            java.lang.String r1 = "\r#\\\u0014"
            r0 = 7
            r3 = r4
            goto L_0x0009
        L_0x007c:
            r3[r2] = r1
            cn.jpush.android.a.j.z = r4
            return
        L_0x0081:
            r9 = 122(0x7a, float:1.71E-43)
            goto L_0x001f
        L_0x0084:
            r9 = 74
            goto L_0x001f
        L_0x0087:
            r9 = 58
            goto L_0x001f
        L_0x008a:
            r9 = 125(0x7d, float:1.75E-43)
            goto L_0x001f
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.jpush.android.a.j.<clinit>():void");
    }

    public j(Context context) {
        this.b = null;
        this.a = (WifiManager) context.getSystemService(z[8]);
        this.b = context;
    }

    private List<k> a(JSONArray jSONArray) {
        k kVar;
        ac.a();
        if (!a()) {
            return null;
        }
        WifiInfo connectionInfo = this.a.getConnectionInfo();
        if (connectionInfo != null) {
            k kVar2 = new k(this, connectionInfo.getBSSID(), connectionInfo.getRssi(), connectionInfo.getSSID());
            new StringBuilder(z[4]).append(kVar2.toString());
            kVar = kVar2;
            ac.a();
        } else {
            kVar = null;
        }
        ArrayList arrayList = new ArrayList();
        if (kVar != null) {
            JSONObject a = kVar.a();
            a.put(z[3], z[0]);
            jSONArray.put(a);
        }
        List<ScanResult> scanResults = Build.VERSION.SDK_INT < 23 ? this.a.getScanResults() : (this.b == null || !b.c(this.b, z[2])) ? null : this.a.getScanResults();
        if (scanResults != null && scanResults.size() > 0) {
            int i = -200;
            k kVar3 = null;
            for (ScanResult scanResult : scanResults) {
                k kVar4 = new k(this, scanResult);
                new StringBuilder(z[1]).append(kVar4.toString());
                ac.a();
                if (kVar.equals(kVar4)) {
                    ac.a();
                } else {
                    arrayList.add(kVar4);
                    if (kVar4.c.equals(kVar.c) || kVar4.b <= i) {
                        kVar3 = kVar3;
                        i = i;
                    } else {
                        i = kVar4.b;
                        kVar3 = kVar4;
                    }
                }
            }
            if (kVar3 != null) {
                JSONObject a2 = kVar3.a();
                a2.put(z[3], z[5]);
                jSONArray.put(a2);
            }
        }
        return arrayList;
    }

    public final boolean a() {
        try {
            return this.a.isWifiEnabled();
        } catch (Exception e) {
            ac.i();
            return false;
        }
    }

    public final WifiManager b() {
        return this.a;
    }

    public final JSONArray c() {
        JSONArray jSONArray = new JSONArray();
        try {
            List<k> a = a(jSONArray);
            new StringBuilder(z[7]).append(a == null ? 0 : a.size());
            ac.a();
            for (k kVar : a) {
                jSONArray.put(kVar.a());
            }
        } catch (Exception e) {
            ac.i();
        }
        return jSONArray;
    }
}
