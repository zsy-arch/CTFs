package cn.jpush.android.a;

import android.content.Context;
import android.telephony.CellLocation;
import android.telephony.NeighboringCellInfo;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.telephony.cdma.CdmaCellLocation;
import cn.jpush.android.util.ac;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;

/* loaded from: classes.dex */
public final class b {
    private static final String[] z;
    private PhoneStateListener h;
    private TelephonyManager q;
    private String r;
    private Context t;
    private int a = 0;
    private int b = 0;
    private int c = 0;
    private boolean d = false;
    private boolean e = false;
    private int f = 0;
    private double g = 0.0d;
    private double i = 0.0d;
    private int j = 0;
    private int k = 0;
    private int l = 0;
    private int m = 0;
    private String n = "";
    private String o = "";
    private String p = "";
    private ArrayList<a> s = new ArrayList<>();

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
            case 7: goto L_0x007d;
            case 8: goto L_0x0087;
            case 9: goto L_0x0092;
            case 10: goto L_0x009d;
            case 11: goto L_0x00a8;
            case 12: goto L_0x00b3;
            case 13: goto L_0x00be;
            case 14: goto L_0x00c9;
            case 15: goto L_0x00d4;
            case 16: goto L_0x00df;
            case 17: goto L_0x00ea;
            case 18: goto L_0x00f5;
            case 19: goto L_0x0100;
            case 20: goto L_0x010b;
            case 21: goto L_0x0116;
            case 22: goto L_0x0121;
            case 23: goto L_0x012c;
            case 24: goto L_0x0137;
            default: goto L_0x003c;
        };
     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x003c, code lost:
        r3[r2] = r1;
        r2 = 1;
        r1 = "+g\f?3";
        r0 = 0;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x0044, code lost:
        r3[r2] = r1;
        r2 = 2;
        r1 = ":h\u0006";
        r0 = 1;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x004c, code lost:
        r3[r2] = r1;
        r2 = 3;
        r1 = "7`\u00000\"2`\r\u000e7)j\u0002\u000e54k\u0006";
        r0 = 2;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:19:0x0054, code lost:
        r3[r2] = r1;
        r2 = 4;
        r1 = "8j\u000f=\u001f?5";
        r0 = 3;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x005c, code lost:
        r3[r2] = r1;
        r2 = 5;
        r1 = "<j\u0017\u00137(j0%7/f\f?\u001f?";
        r0 = 4;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x0064, code lost:
        r3[r2] = r1;
        r2 = 6;
        r1 = "6`\u00018:>P\r4\",`\u0011:\t8`\u00074";
        r0 = 5;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x006c, code lost:
        r3[r2] = r1;
        r2 = 7;
        r1 = "<j\u0017\u00137(j0%7/f\f?\u001a:{\n%#?j";
        r0 = 6;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x0074, code lost:
        r3[r2] = r1;
        r2 = '\b';
        r1 = "<j\u0017\u001f3/x\f#=\u0012k";
        r0 = 7;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x007d, code lost:
        r3[r2] = r1;
        r2 = '\t';
        r1 = ")n\u000789\u000fv\u00134l";
        r0 = '\b';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x0087, code lost:
        r3[r2] = r1;
        r2 = '\n';
        r1 = "\u000ea\u0006)&>l\u001742z/\u00004:7C\f27/f\f?v2|C?#7cOq12y\u0006q#+/\u00114&4}\u0017q5>c\u000f|?5i\f";
        r0 = '\t';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x0092, code lost:
        r3[r2] = r1;
        r2 = 11;
        r1 = "\u0018j\u000f=\u001f5i\f\u001c75n\u00044$";
        r0 = '\n';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x009d, code lost:
        r3[r2] = r1;
        r2 = '\f';
        r1 = "(f\u0004?77P\u0010%$>a\u0004%>";
        r0 = 11;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x00a8, code lost:
        r3[r2] = r1;
        r2 = '\r';
        r1 = "w/\u00000$)f\u0006#l";
        r0 = '\f';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:29:0x00b3, code lost:
        r3[r2] = r1;
        r2 = 14;
        r1 = "8k\u000e0";
        r0 = '\r';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x00be, code lost:
        r3[r2] = r1;
        r2 = 15;
        r1 = "8j\u000f=\t2k";
        r0 = 14;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:31:0x00c9, code lost:
        r3[r2] = r1;
        r2 = 16;
        r1 = ")n\u000789\u0004{\u001a!3";
        r0 = 15;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x00d4, code lost:
        r3[r2] = r1;
        r2 = 17;
        r1 = "<j\u0017\u00137(j0%7/f\f?\u001a4a\u00048\".k\u0006";
        r0 = 16;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x00df, code lost:
        r3[r2] = r1;
        r2 = 18;
        r1 = "8n\u0011#?>}";
        r0 = 17;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:34:0x00ea, code lost:
        r3[r2] = r1;
        r2 = 19;
        r1 = "\u0004m\n5l";
        r0 = 18;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:35:0x00f5, code lost:
        r3[r2] = r1;
        r2 = com.tencent.open.GameAppOperation.PIC_SYMBOLE;
        r1 = "w/\u000448>}\u0002%?4aY";
        r0 = 19;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:36:0x0100, code lost:
        r3[r2] = r1;
        r2 = 21;
        r1 = "(f\u0007k";
        r0 = com.tencent.open.GameAppOperation.PIC_SYMBOLE;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:37:0x010b, code lost:
        r3[r2] = r1;
        r2 = 22;
        r1 = "<j\u0017\u0002/({\u0006<\u001f?";
        r0 = 21;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:38:0x0116, code lost:
        r3[r2] = r1;
        r2 = 23;
        r1 = "6`\u00018:>P\u0000>#5{\u0011(\t8`\u00074";
        r0 = 22;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:39:0x0121, code lost:
        r3[r2] = r1;
        r2 = 24;
        r1 = "<j\r4$:{\n>8";
        r0 = 23;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:40:0x012c, code lost:
        r3[r2] = r1;
        r2 = 25;
        r1 = "\u0004a\n5l";
        r0 = 24;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:41:0x0137, code lost:
        r3[r2] = r1;
        cn.jpush.android.a.b.z = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:42:0x013b, code lost:
        return;
     */
    /* JADX WARN: Code restructure failed: missing block: B:43:0x013c, code lost:
        r9 = '[';
     */
    /* JADX WARN: Code restructure failed: missing block: B:44:0x0140, code lost:
        r9 = 15;
     */
    /* JADX WARN: Code restructure failed: missing block: B:45:0x0144, code lost:
        r9 = 'c';
     */
    /* JADX WARN: Code restructure failed: missing block: B:46:0x0148, code lost:
        r9 = 'Q';
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
            case 0: goto L_0x013c;
            case 1: goto L_0x0140;
            case 2: goto L_0x0144;
            case 3: goto L_0x0148;
            default: goto L_0x001e;
        };
     */
    /* JADX WARN: Code restructure failed: missing block: B:8:0x001e, code lost:
        r9 = 'V';
     */
    /* JADX WARN: Code restructure failed: missing block: B:9:0x0020, code lost:
        r5[r6] = (char) (r9 ^ r10);
        r6 = r8 + 1;
     */
    static {
        /*
            Method dump skipped, instructions count: 398
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.jpush.android.a.b.<clinit>():void");
    }

    public b(Context context) {
        this.t = null;
        this.t = context;
        if (cn.jpush.android.util.b.c(context, z[0])) {
            try {
                this.h = new c(this);
                this.q = (TelephonyManager) context.getSystemService(z[1]);
                this.q.listen(this.h, 18);
            } catch (Exception e) {
                ac.h();
            }
        }
    }

    private String h() {
        if (this.t != null && !cn.jpush.android.util.b.c(this.t, z[0])) {
            return null;
        }
        try {
            CellLocation cellLocation = this.q.getCellLocation();
            String networkOperator = this.q.getNetworkOperator();
            int length = networkOperator.length();
            if (length == 5) {
                this.j = Integer.parseInt(networkOperator.substring(0, 3));
                this.k = Integer.parseInt(networkOperator.substring(3, length));
            } else if (length != 6) {
            }
            if (this.q.getPhoneType() == 2 && (cellLocation instanceof CdmaCellLocation) && cellLocation != null) {
                CdmaCellLocation cdmaCellLocation = (CdmaCellLocation) cellLocation;
                this.g = cdmaCellLocation.getBaseStationLatitude() / 14400.0d;
                this.i = cdmaCellLocation.getBaseStationLongitude() / 14400.0d;
                this.m = cdmaCellLocation.getSystemId();
                this.b = cdmaCellLocation.getBaseStationId();
                this.l = cdmaCellLocation.getNetworkId();
                new StringBuilder().append(this.m);
                ac.c();
                new StringBuilder().append(this.b);
                ac.c();
                new StringBuilder().append(this.l);
                ac.c();
                a aVar = new a();
                aVar.a(this.b);
                aVar.d(this.l);
                aVar.c(this.m);
                aVar.b(Integer.parseInt(networkOperator.substring(0, 3)));
                aVar.a(z[14]);
                this.s.add(aVar);
                new StringBuilder().append(aVar.a());
                ac.b();
                this.r = aVar.toString();
                return this.r;
            }
        } catch (Exception e) {
        }
        return null;
    }

    public final int a() {
        return this.b;
    }

    public final JSONArray b() {
        if (this.t != null && !cn.jpush.android.util.b.c(this.t, z[0])) {
            return null;
        }
        try {
            CellLocation cellLocation = this.q.getCellLocation();
            String networkOperator = this.q.getNetworkOperator();
            int length = networkOperator.length();
            if (length == 5) {
                this.j = Integer.parseInt(networkOperator.substring(0, 3));
                this.k = Integer.parseInt(networkOperator.substring(3, length));
            } else if (length != 6) {
            }
            if (this.q.getPhoneType() == 2 && (cellLocation instanceof CdmaCellLocation) && cellLocation != null) {
                CdmaCellLocation cdmaCellLocation = (CdmaCellLocation) cellLocation;
                this.g = cdmaCellLocation.getBaseStationLatitude() / 14400.0d;
                this.i = cdmaCellLocation.getBaseStationLongitude() / 14400.0d;
                this.m = cdmaCellLocation.getSystemId();
                this.b = cdmaCellLocation.getBaseStationId();
                this.l = cdmaCellLocation.getNetworkId();
                new StringBuilder().append(this.m);
                ac.c();
                new StringBuilder().append(this.b);
                ac.c();
                new StringBuilder().append(this.l);
                ac.c();
                a aVar = new a();
                aVar.a(this.b);
                aVar.d(this.l);
                aVar.c(this.m);
                aVar.b(Integer.parseInt(networkOperator.substring(0, 3)));
                aVar.a(z[14]);
                this.s.add(aVar);
                return aVar.b();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x0061  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x0113 A[Catch: Exception -> 0x01e7, TryCatch #3 {Exception -> 0x01e7, blocks: (B:35:0x0107, B:37:0x0113, B:39:0x012c, B:41:0x0130, B:43:0x01a7, B:45:0x01ad, B:46:0x01ba, B:52:0x01f4), top: B:73:0x0107 }] */
    /* JADX WARN: Removed duplicated region for block: B:39:0x012c A[Catch: Exception -> 0x01e7, TryCatch #3 {Exception -> 0x01e7, blocks: (B:35:0x0107, B:37:0x0113, B:39:0x012c, B:41:0x0130, B:43:0x01a7, B:45:0x01ad, B:46:0x01ba, B:52:0x01f4), top: B:73:0x0107 }] */
    /* JADX WARN: Removed duplicated region for block: B:78:0x0076 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final org.json.JSONArray c() {
        /*
            Method dump skipped, instructions count: 755
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.jpush.android.a.b.c():org.json.JSONArray");
    }

    public final int[] d() {
        if (this.c == 0) {
            return new int[0];
        }
        List<NeighboringCellInfo> neighboringCellInfo = this.q.getNeighboringCellInfo();
        if (neighboringCellInfo == null || neighboringCellInfo.size() == 0) {
            return new int[]{this.c};
        }
        int[] iArr = new int[(neighboringCellInfo.size() * 2) + 2];
        iArr[0] = this.c;
        int i = 2;
        iArr[1] = this.a;
        for (NeighboringCellInfo neighboringCellInfo2 : neighboringCellInfo) {
            int cid = neighboringCellInfo2.getCid();
            if (cid > 0 && cid != 65535) {
                int i2 = i + 1;
                iArr[i] = cid;
                i = i2 + 1;
                iArr[i2] = neighboringCellInfo2.getRssi();
            }
        }
        int[] iArr2 = new int[i];
        System.arraycopy(iArr, 0, iArr2, 0, i);
        return iArr2;
    }

    public final boolean e() {
        return this.d;
    }

    public final boolean f() {
        return this.e;
    }

    public final float g() {
        if (!this.d && this.e) {
            d();
        }
        return 1.06535322E9f;
    }
}
