package cn.jpush.android.data;

import android.content.Context;
import cn.jpush.android.api.n;
import cn.jpush.android.helpers.h;
import cn.jpush.android.helpers.k;
import cn.jpush.android.service.ServiceInterface;
import cn.jpush.android.util.ac;
import cn.jpush.android.util.ao;
import cn.jpush.android.util.b;
import cn.jpush.android.util.l;
import com.tencent.smtt.utils.TbsLog;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class i extends c {
    private static final String[] R;
    public String E;
    public boolean F;
    public boolean G;
    public int H;
    public boolean I;
    public int J;
    public String K;
    public String M;
    public boolean N;
    public String P;
    public boolean Q;
    public String a;
    public boolean O = true;
    public l L = new l(this);

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
            case 25: goto L_0x0142;
            case 26: goto L_0x014d;
            default: goto L_0x003c;
        };
     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x003c, code lost:
        r3[r2] = r1;
        r2 = 1;
        r1 = "PWj\u0010L_";
        r0 = 0;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x0044, code lost:
        r3[r2] = r1;
        r2 = 2;
        r1 = "PWj\u0010LC";
        r0 = 1;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x004c, code lost:
        r3[r2] = r1;
        r2 = 3;
        r1 = "PWj\u0010LCA";
        r0 = 2;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:19:0x0054, code lost:
        r3[r2] = r1;
        r2 = 4;
        r1 = "nK}\u0010}aCj\u0017";
        r0 = 3;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x005c, code lost:
        r3[r2] = r1;
        r2 = 5;
        r1 = "P}|\r|BU{\r";
        r0 = 4;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x0064, code lost:
        r3[r2] = r1;
        r2 = 6;
        r1 = "P}w\u0012rVGA\na]";
        r0 = 5;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x006c, code lost:
        r3[r2] = r1;
        r2 = 7;
        r1 = "PRu }";
        r0 = 6;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x0074, code lost:
        r3[r2] = r1;
        r2 = '\b';
        r1 = "PRu `YMi";
        r0 = 7;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x007d, code lost:
        r3[r2] = r1;
        r2 = '\t';
        r1 = "PRu f";
        r0 = '\b';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x0087, code lost:
        r3[r2] = r1;
        r2 = '\n';
        r1 = "nU{\u001dCPE{/rEJ";
        r0 = '\t';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x0092, code lost:
        r3[r2] = r1;
        r2 = 11;
        r1 = "PRu fCN";
        r0 = '\n';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x009d, code lost:
        r3[r2] = r1;
        r2 = '\f';
        r1 = "PRu `YMi uXLw\f{TFA\u0011|EK";
        r0 = 11;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x00a8, code lost:
        r3[r2] = r1;
        r2 = '\r';
        r1 = "P}j\u0006cT";
        r0 = '\f';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:29:0x00b3, code lost:
        r3[r2] = r1;
        r2 = 14;
        r1 = "PRu rDVq z_Qj\u001e\u007f]";
        r0 = '\r';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x00be, code lost:
        r3[r2] = r1;
        r2 = 15;
        r1 = "P}h\u001aa";
        r0 = 14;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:31:0x00c9, code lost:
        r3[r2] = r1;
        r2 = 16;
        r1 = "PRu ~U\u0017";
        r0 = 15;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x00d4, code lost:
        r3[r2] = r1;
        r2 = 17;
        r1 = "P}w\u001c|_}k\r\u007f";
        r0 = 16;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x00df, code lost:
        r3[r2] = r1;
        r2 = 18;
        r1 = "PRu vRMp";
        r0 = 17;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:34:0x00ea, code lost:
        r3[r2] = r1;
        r2 = 19;
        r1 = "PWj\u0010L\\";
        r0 = 18;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:35:0x00f5, code lost:
        r3[r2] = r1;
        r2 = com.tencent.open.GameAppOperation.PIC_SYMBOLE;
        r1 = "P}w\u0011u^";
        r0 = 19;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:36:0x0100, code lost:
        r3[r2] = r1;
        r2 = 21;
        r1 = "P}l\u001a`";
        r0 = com.tencent.open.GameAppOperation.PIC_SYMBOLE;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:37:0x010b, code lost:
        r3[r2] = r1;
        r2 = 22;
        r1 = "P}m\u001c|CG";
        r0 = 21;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:38:0x0116, code lost:
        r3[r2] = r1;
        r2 = 23;
        r1 = "nKs\u001etTr\u007f\u000b{";
        r0 = 22;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:39:0x0121, code lost:
        r3[r2] = r1;
        r2 = 24;
        r1 = "P}m\u0016iT";
        r0 = 23;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:40:0x012c, code lost:
        r3[r2] = r1;
        r2 = 25;
        r1 = "P}{\rvB";
        r0 = 24;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:41:0x0137, code lost:
        r3[r2] = r1;
        r2 = 26;
        r1 = "P}{\na]";
        r0 = 25;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:42:0x0142, code lost:
        r3[r2] = r1;
        r2 = 27;
        r1 = "dL{\u0007cTAj\u001aw\u000b\u0002k\u0011x_Mi\u00113AI>\f{^U>\u0012|UG>R3";
        r0 = 26;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:43:0x014d, code lost:
        r3[r2] = r1;
        cn.jpush.android.data.i.R = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:44:0x0151, code lost:
        return;
     */
    /* JADX WARN: Code restructure failed: missing block: B:45:0x0152, code lost:
        r9 = '1';
     */
    /* JADX WARN: Code restructure failed: missing block: B:46:0x0156, code lost:
        r9 = '\"';
     */
    /* JADX WARN: Code restructure failed: missing block: B:47:0x015a, code lost:
        r9 = 30;
     */
    /* JADX WARN: Code restructure failed: missing block: B:48:0x015e, code lost:
        r9 = 127;
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
            case 0: goto L_0x0152;
            case 1: goto L_0x0156;
            case 2: goto L_0x015a;
            case 3: goto L_0x015e;
            default: goto L_0x001e;
        };
     */
    /* JADX WARN: Code restructure failed: missing block: B:8:0x001e, code lost:
        r9 = 19;
     */
    /* JADX WARN: Code restructure failed: missing block: B:9:0x0020, code lost:
        r5[r6] = (char) (r9 ^ r10);
        r6 = r8 + 1;
     */
    static {
        /*
            Method dump skipped, instructions count: 424
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.jpush.android.data.i.<clinit>():void");
    }

    public i() {
        this.o = 1;
    }

    public static /* synthetic */ void a(i iVar, i iVar2, Context context) {
        if (h.a(iVar2.G, iVar2.H, context)) {
            if (b.b(context)) {
                ServiceInterface.a(context, iVar2);
                return;
            }
            iVar2.G = false;
        }
        n.a(context, iVar2);
    }

    @Override // cn.jpush.android.data.c
    public void a(Context context) {
        ac.a();
        boolean f = b.f(context, this.a);
        int i = TbsLog.TBSLOG_CODE_SDK_THIRD_MODE;
        if (this.F || !f) {
            if (this.F && f) {
                ac.b();
                i = TbsLog.TBSLOG_CODE_SDK_LOAD_ERROR;
            }
            if (this.J == 1) {
                new j(this, this, context, i).start();
            } else if (this.J == 0) {
                new k(this, context, i, this).start();
            } else {
                new StringBuilder(R[27]).append(this.J);
                ac.d();
            }
        } else {
            ac.b();
            k.a(this.c, TbsLog.TBSLOG_CODE_SDK_INVOKE_ERROR, context);
        }
    }

    @Override // cn.jpush.android.data.c
    public boolean a(Context context, JSONObject jSONObject) {
        boolean z = false;
        ac.a();
        this.a = jSONObject.optString(R[7], "");
        this.F = jSONObject.optInt(R[9], 0) > 0;
        this.G = jSONObject.optInt(R[19], 0) > 0;
        this.H = jSONObject.optInt(R[1], 0);
        this.I = jSONObject.optInt(R[2], 0) > 0;
        this.J = jSONObject.optInt(R[8], 1);
        this.K = jSONObject.optString(R[11], "").trim();
        this.M = jSONObject.optString(R[16], "");
        this.E = jSONObject.optString(R[3], "");
        this.N = jSONObject.optInt(R[12], 0) > 0;
        this.O = jSONObject.optInt(R[14], 1) == 1;
        if (this.o == 1) {
            JSONObject a = h.a(context, this.c, jSONObject, R[18]);
            if (a == null) {
                return false;
            }
            l lVar = this.L;
            ac.a();
            lVar.a = a.optString(R[0], "");
            lVar.b = a.optString(R[17], "").trim();
            lVar.c = a.optString(R[15], "");
            lVar.d = a.optString(R[13], "");
            lVar.e = a.optInt(R[22], 0) == 0;
            lVar.f = a.optString(R[24], "");
            lVar.g = a.optString(R[20], "");
            lVar.h = a.optString(R[6], "").trim();
            lVar.j = a.optString(R[26], "").trim();
            lVar.o.y = a.optInt(R[5], 0) == 1;
            if (a.optInt(R[21], 0) == 0) {
                z = true;
            }
            lVar.i = z;
            if (lVar.i) {
                lVar.k = l.a(a.optJSONArray(R[25]));
            }
            if (ao.a(lVar.m)) {
                lVar.m = a.optString(R[23], "").trim();
            }
            if (ao.a(lVar.l)) {
                lVar.l = a.optString(R[4], "").trim();
            }
            if (ao.a(lVar.l)) {
                lVar.l = a.optString(R[10], "").trim();
            }
        }
        return true;
    }
}
