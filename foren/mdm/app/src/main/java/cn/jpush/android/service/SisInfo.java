package cn.jpush.android.service;

import cn.jpush.android.a;
import cn.jpush.android.util.ac;
import cn.jpush.android.util.ah;
import cn.jpush.android.util.ao;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class SisInfo {
    private static Pattern IPV4_PATTERN;
    private static final String IPV4_REGEX;
    private static final String TAG;
    private static SisInfo sisInfo;
    private static final String[] z;
    String mainConnIp;
    int mainConnPort;
    String originSis;
    List<String> ssl_ips;
    List<String> ssl_op_conns;
    List<String> udp_report;
    String user;
    List<String> ips = new ArrayList();
    List<String> op_conns = new ArrayList();
    List<String> optionConnIp = new ArrayList();
    List<Integer> optionConnPort = new ArrayList();
    boolean invalidSis = false;

    /* JADX WARN: Code restructure failed: missing block: B:10:0x0028, code lost:
        if (r5 != 0) goto L_0x003b;
     */
    /* JADX WARN: Code restructure failed: missing block: B:11:0x002a, code lost:
        r5 = r1;
        r8 = r6;
        r6 = r5;
     */
    /* JADX WARN: Code restructure failed: missing block: B:12:0x002e, code lost:
        cn.jpush.android.service.SisInfo.TAG = r1;
        r1 = "\u0003\fe$\f\u001ay\u0001O\u0011\u0006\u001dco\u0010\u0007\u0016CH\u000f\u0002X\u0016&z\u001b\t\nIz\u001b\t\u0007I}\u0005\rB<\u0013\u001e\u007f\u000e9\u0014vx\u0010=\bP\u0017C<\tp\u0014\u0013%|\u0014\u007f\u000e9\u0018v_\u000f8\u0013V\rB<\u0013p\u0014\u0013 |p\u0014\u0013-|\u0002X\u0016&\u0014p\u0014\u0013!|\u0002\r";
        r0 = 11;
     */
    /* JADX WARN: Code restructure failed: missing block: B:13:0x0035, code lost:
        cn.jpush.android.service.SisInfo.IPV4_REGEX = r1;
        r1 = "DTawNEJM.";
        r0 = 65535;
     */
    /* JADX WARN: Code restructure failed: missing block: B:14:0x003b, code lost:
        r5 = r5;
        r1 = r1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:15:0x003d, code lost:
        if (r5 > r6) goto L_0x0014;
     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x003f, code lost:
        r1 = new java.lang.String(r1).intern();
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x0048, code lost:
        switch(r0) {
            case 0: goto L_0x0053;
            case 1: goto L_0x005b;
            case 2: goto L_0x0063;
            case 3: goto L_0x006c;
            case 4: goto L_0x0074;
            case 5: goto L_0x007d;
            case 6: goto L_0x0085;
            case 7: goto L_0x008f;
            case 8: goto L_0x009a;
            case 9: goto L_0x00a5;
            case 10: goto L_0x002e;
            case 11: goto L_0x0035;
            default: goto L_0x004b;
        };
     */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x004b, code lost:
        r3[r2] = r1;
        r2 = 1;
        r1 = "BTM";
        r0 = 0;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:19:0x0053, code lost:
        r3[r2] = r1;
        r2 = 2;
        r1 = "BTM.";
        r0 = 1;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x005b, code lost:
        r3[r2] = r1;
        r2 = 3;
        r1 = "DTawNEJM";
        r0 = 2;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x0063, code lost:
        r3[r2] = r1;
        r2 = 4;
        r1 = "~J[lQNGJqE\u000b\t\u001e]O]ER}E\u000bWWg\u0001\u0006\u0004P{\u0001BTM4JN]\u0010";
        r0 = 3;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x006c, code lost:
        r3[r2] = r1;
        r2 = 5;
        r1 = "mEWxDO\u0004J{\u0001[ELgD\u000bMNg\f\u001a\u0004\u00134LJMP4H[\n";
        r0 = 4;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x0074, code lost:
        r3[r2] = r1;
        r2 = 6;
        r1 = "~J[lQNGJqE\u000b\t\u001e}O]ER}E\u000bWWg\u0001\u0006\u0004WdR\u000bELf@R\u0004RqO\u000bMM4\u0011";
        r0 = 5;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x007d, code lost:
        r3[r2] = r1;
        r2 = 7;
        r1 = "mEWxDO\u0004J{\u0001[ELgD\u000bKNKBDJP4\f\u000b";
        r0 = 6;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x0085, code lost:
        r3[r2] = r1;
        r2 = '\b';
        r1 = "mEWxDO\u0004J{\u0001[ELgD\u000bMNg\f\u0019\u0004\u00134ENB_aM_\u0004Wd\u000f";
        r0 = 7;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x008f, code lost:
        r3[r2] = r1;
        r2 = '\t';
        r1 = "eK\u001efD[KL`\u0001IE]\u007fT[\u0004Wd\u000f";
        r0 = '\b';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x009a, code lost:
        r3[r2] = r1;
        r2 = '\n';
        r1 = "dJRm\u0001FEWz\u0001BT\u001e}O\u000bWWg\u000f";
        r0 = '\t';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x00a5, code lost:
        r3[r2] = r1;
        cn.jpush.android.service.SisInfo.z = r3;
        cn.jpush.android.service.SisInfo.sisInfo = null;
        cn.jpush.android.service.SisInfo.IPV4_PATTERN = java.util.regex.Pattern.compile(cn.jpush.android.service.SisInfo.IPV4_REGEX);
     */
    /* JADX WARN: Code restructure failed: missing block: B:29:0x00b4, code lost:
        return;
     */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x00b5, code lost:
        r9 = '+';
     */
    /* JADX WARN: Code restructure failed: missing block: B:31:0x00b9, code lost:
        r9 = '$';
     */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x00bd, code lost:
        r9 = '>';
     */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x00c1, code lost:
        r9 = com.tencent.open.GameAppOperation.PIC_SYMBOLE;
     */
    /* JADX WARN: Code restructure failed: missing block: B:4:0x0012, code lost:
        if (r5 <= 1) goto L_0x0014;
     */
    /* JADX WARN: Code restructure failed: missing block: B:5:0x0014, code lost:
        r8 = r6;
        r5 = r1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:6:0x0019, code lost:
        r10 = r5[r6];
     */
    /* JADX WARN: Code restructure failed: missing block: B:7:0x001d, code lost:
        switch((r8 % 5)) {
            case 0: goto L_0x00b5;
            case 1: goto L_0x00b9;
            case 2: goto L_0x00bd;
            case 3: goto L_0x00c1;
            default: goto L_0x0020;
        };
     */
    /* JADX WARN: Code restructure failed: missing block: B:8:0x0020, code lost:
        r9 = '!';
     */
    /* JADX WARN: Code restructure failed: missing block: B:9:0x0022, code lost:
        r5[r6] = (char) (r9 ^ r10);
        r6 = r8 + 1;
     */
    static {
        /*
            Method dump skipped, instructions count: 238
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.jpush.android.service.SisInfo.<clinit>():void");
    }

    public static boolean isValidIPV4(String str) {
        return IPV4_PATTERN.matcher(str).matches();
    }

    public void configure() {
        int size = this.ips.size();
        if (size != 0) {
            a.f(this.originSis);
            if (size > 1) {
                try {
                    n nVar = new n(this.ips.get(1));
                    a.c(nVar.a);
                    a.c(nVar.b);
                } catch (Exception e) {
                    ac.a(TAG, z[8], e);
                }
            } else {
                ac.d(TAG, z[10]);
            }
            if (size > 2) {
                ah.a(this.ips.get(2));
                a.d(this.ips.get(2));
            } else {
                ac.d(TAG, z[9]);
            }
            if (this.user != null) {
                a.e(this.user);
            }
        }
    }

    public String getMainConnIp() {
        return this.mainConnIp;
    }

    public int getMainConnPort() {
        return this.mainConnPort;
    }

    public List<String> getOptionConnIp() {
        return this.optionConnIp;
    }

    public List<Integer> getOptionConnPort() {
        return this.optionConnPort;
    }

    public String getOriginSis() {
        return this.originSis;
    }

    public boolean isInvalidSis() {
        return this.invalidSis;
    }

    public void parseAndSet(String str) {
        this.originSis = str;
        if (this.ips == null) {
            ac.e(TAG, z[4]);
            this.invalidSis = true;
        } else if (this.ips.size() == 0) {
            ac.e(TAG, z[6]);
            this.invalidSis = true;
        } else {
            try {
                n nVar = new n(this.ips.get(0));
                this.mainConnIp = nVar.a;
                this.mainConnPort = nVar.b;
                if (this.op_conns == null) {
                    ac.b();
                    return;
                }
                for (String str2 : this.op_conns) {
                    try {
                        n nVar2 = new n(str2);
                        this.optionConnIp.add(nVar2.a);
                        this.optionConnPort.add(Integer.valueOf(nVar2.b));
                    } catch (Exception e) {
                        new StringBuilder(z[7]).append(str2);
                        ac.i();
                    }
                }
            } catch (Exception e2) {
                ac.a(TAG, z[5], e2);
                this.invalidSis = true;
            }
        }
    }

    public void parseSisInfo(String str) {
        if (ao.a(str)) {
            str = a.w();
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            JSONArray jSONArray = jSONObject.getJSONArray(z[1]);
            if (jSONArray == null || jSONArray.length() == 0) {
                ac.d();
                return;
            }
            new StringBuilder(z[2]).append(jSONArray.toString());
            ac.a();
            for (int i = 0; i < jSONArray.length(); i++) {
                this.ips.add(jSONArray.optString(i));
            }
            JSONArray jSONArray2 = jSONObject.getJSONArray(z[3]);
            if (jSONArray2 == null || jSONArray2.length() == 0) {
                ac.d();
            }
            new StringBuilder(z[0]).append(jSONArray2.toString());
            ac.a();
            for (int i2 = 0; i2 < jSONArray2.length(); i2++) {
                this.op_conns.add(jSONArray2.optString(i2));
            }
        } catch (JSONException e) {
            ac.h();
        }
    }
}
