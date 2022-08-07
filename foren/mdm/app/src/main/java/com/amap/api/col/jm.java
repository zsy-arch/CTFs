package com.amap.api.col;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiInfo;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import com.alimama.mobile.csdk.umupdate.a.f;
import java.util.ArrayList;

/* compiled from: Req.java */
@SuppressLint({"NewApi"})
/* loaded from: classes.dex */
public final class jm {
    public static String K = null;
    public String a = "1";
    public short b = 0;
    public String c = null;
    public String d = null;
    public String e = null;
    public String f = null;
    public String g = null;
    public String h = null;
    public String i = null;
    public String j = null;
    public String k = null;
    public String l = null;
    public String m = null;
    public String n = null;
    public String o = null;
    public String p = null;
    public String q = null;
    public String r = null;
    public String s = null;
    public String t = null;

    /* renamed from: u  reason: collision with root package name */
    public String f23u = null;
    public String v = null;
    public String w = null;
    public String x = null;
    public String y = null;
    public int z = 0;
    public String A = null;
    public String B = null;
    public ArrayList<jg> C = new ArrayList<>();
    public String D = null;
    public String E = null;
    public ArrayList<ScanResult> F = new ArrayList<>();
    public ArrayList<je> G = new ArrayList<>();
    public String H = null;
    public String I = null;
    public byte[] J = null;
    private byte[] M = null;
    private int N = 0;
    public String L = null;

    private String a(String str, int i) {
        String[] split = this.B.split("\\*")[i].split(",");
        if (str.equals("lac")) {
            return split[0];
        }
        if (str.equals("cellid")) {
            return split[1];
        }
        if (str.equals("signal")) {
            return split[2];
        }
        return null;
    }

    /* JADX WARN: Code restructure failed: missing block: B:5:0x000e, code lost:
        if (r0.length != 6) goto L_0x0010;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private byte[] a(java.lang.String r8) {
        /*
            r7 = this;
            r6 = 2
            r4 = 6
            r2 = 0
            java.lang.String r0 = ":"
            java.lang.String[] r0 = r8.split(r0)
            byte[] r1 = new byte[r4]
            if (r0 == 0) goto L_0x0010
            int r3 = r0.length     // Catch: Throwable -> 0x0043
            if (r3 == r4) goto L_0x001e
        L_0x0010:
            r0 = 6
            java.lang.String[] r0 = new java.lang.String[r0]     // Catch: Throwable -> 0x0043
            r3 = r2
        L_0x0014:
            int r4 = r0.length     // Catch: Throwable -> 0x0043
            if (r3 >= r4) goto L_0x001e
            java.lang.String r4 = "0"
            r0[r3] = r4     // Catch: Throwable -> 0x0043
            int r3 = r3 + 1
            goto L_0x0014
        L_0x001e:
            int r3 = r0.length     // Catch: Throwable -> 0x0043
            if (r2 >= r3) goto L_0x0041
            r3 = r0[r2]     // Catch: Throwable -> 0x0043
            int r3 = r3.length()     // Catch: Throwable -> 0x0043
            if (r3 <= r6) goto L_0x0033
            r3 = r0[r2]     // Catch: Throwable -> 0x0043
            r4 = 0
            r5 = 2
            java.lang.String r3 = r3.substring(r4, r5)     // Catch: Throwable -> 0x0043
            r0[r2] = r3     // Catch: Throwable -> 0x0043
        L_0x0033:
            r3 = r0[r2]     // Catch: Throwable -> 0x0043
            r4 = 16
            int r3 = java.lang.Integer.parseInt(r3, r4)     // Catch: Throwable -> 0x0043
            byte r3 = (byte) r3     // Catch: Throwable -> 0x0043
            r1[r2] = r3     // Catch: Throwable -> 0x0043
            int r2 = r2 + 1
            goto L_0x001e
        L_0x0041:
            r0 = r1
        L_0x0042:
            return r0
        L_0x0043:
            r0 = move-exception
            java.lang.String r1 = "Req"
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            java.lang.String r3 = "getMacBa "
            r2.<init>(r3)
            java.lang.StringBuilder r2 = r2.append(r8)
            java.lang.String r2 = r2.toString()
            com.amap.api.col.jn.a(r0, r1, r2)
            java.lang.String r0 = "00:00:00:00:00:00"
            byte[] r0 = r7.a(r0)
            goto L_0x0042
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amap.api.col.jm.a(java.lang.String):byte[]");
    }

    private String b(String str) {
        if (!this.A.contains(str + ">")) {
            return "0";
        }
        int indexOf = this.A.indexOf(str + ">");
        return this.A.substring(indexOf + str.length() + 1, this.A.indexOf("</" + str));
    }

    private void b() {
        if (TextUtils.isEmpty(this.a)) {
            this.a = "";
        }
        if (TextUtils.isEmpty(this.c)) {
            this.c = "";
        }
        if (TextUtils.isEmpty(this.d)) {
            this.d = "";
        }
        if (TextUtils.isEmpty(this.e)) {
            this.e = "";
        }
        if (TextUtils.isEmpty(this.f)) {
            this.f = "";
        }
        if (TextUtils.isEmpty(this.g)) {
            this.g = "";
        }
        if (TextUtils.isEmpty(this.h)) {
            this.h = "";
        }
        if (TextUtils.isEmpty(this.i)) {
            this.i = "";
        }
        if (TextUtils.isEmpty(this.j)) {
            this.j = "0";
        } else if (!this.j.equals("1") && !this.j.equals("2")) {
            this.j = "0";
        }
        if (TextUtils.isEmpty(this.k)) {
            this.k = "0";
        } else if (!this.k.equals("0") && !this.k.equals("1")) {
            this.k = "0";
        }
        if (TextUtils.isEmpty(this.l)) {
            this.l = "";
        }
        if (TextUtils.isEmpty(this.m)) {
            this.m = "";
        }
        if (TextUtils.isEmpty(this.n)) {
            this.n = "";
        }
        if (TextUtils.isEmpty(this.o)) {
            this.o = "";
        }
        if (TextUtils.isEmpty(this.p)) {
            this.p = "";
        }
        if (TextUtils.isEmpty(this.q)) {
            this.q = "";
        }
        if (TextUtils.isEmpty(this.r)) {
            this.r = "";
        }
        if (TextUtils.isEmpty(this.s)) {
            this.s = "";
        }
        if (TextUtils.isEmpty(this.t)) {
            this.t = "";
        }
        if (TextUtils.isEmpty(this.f23u)) {
            this.f23u = "";
        }
        if (TextUtils.isEmpty(this.v)) {
            this.v = "";
        }
        if (TextUtils.isEmpty(this.w)) {
            this.w = "";
        }
        if (TextUtils.isEmpty(this.x)) {
            this.x = "";
        }
        if (TextUtils.isEmpty(this.y)) {
            this.y = "0";
        } else if (!this.y.equals("1") && !this.y.equals("2")) {
            this.y = "0";
        }
        if (!jh.a(this.z)) {
            this.z = 0;
        }
        if (TextUtils.isEmpty(this.A)) {
            this.A = "";
        }
        if (TextUtils.isEmpty(this.B)) {
            this.B = "";
        }
        if (TextUtils.isEmpty(this.E)) {
            this.E = "";
        }
        if (TextUtils.isEmpty(this.H)) {
            this.H = "";
        }
        if (TextUtils.isEmpty(this.I)) {
            this.I = "";
        }
        if (TextUtils.isEmpty(K)) {
            K = "";
        }
        if (this.J == null) {
            this.J = new byte[0];
        }
    }

    public final void a(Context context, boolean z, boolean z2, jh jhVar, ji jiVar, ConnectivityManager connectivityManager, String str, String str2) {
        String str3;
        String str4 = "0";
        String f = ga.f(context);
        int f2 = jq.f();
        String str5 = "";
        String str6 = "";
        this.L = str2;
        String str7 = "api_serverSDK_130905";
        String str8 = "S128DF1572465B890OE3F7A13167KLEI";
        if (!z2) {
            str7 = "UC_nlp_20131029";
            str8 = "BKZCHMBBSSUK7U8GLUKHBB56CCFF78U";
        }
        StringBuilder sb = new StringBuilder();
        StringBuilder sb2 = new StringBuilder();
        int c = jhVar.c();
        int d = jhVar.d();
        TelephonyManager e = jhVar.e();
        ArrayList<jg> a = jh.a();
        ArrayList<jg> b = jh.b();
        ArrayList<ScanResult> a2 = jiVar.a();
        if (d == 2) {
            str4 = "1";
        }
        if (e != null) {
            if (TextUtils.isEmpty(jn.a)) {
                try {
                    jn.a = ge.q(context);
                } catch (Throwable th) {
                    jn.a(th, "APS", "getApsReq part4");
                }
            }
            if (TextUtils.isEmpty(jn.a)) {
                jn.a = "888888888888888";
            }
            if (TextUtils.isEmpty(jn.b)) {
                try {
                    jn.b = e.getSubscriberId();
                } catch (Throwable th2) {
                    jn.a(th2, "APS", "getApsReq part2");
                }
            }
            if (TextUtils.isEmpty(jn.b)) {
                jn.b = "888888888888888";
            }
        }
        NetworkInfo networkInfo = null;
        try {
            networkInfo = connectivityManager.getActiveNetworkInfo();
        } catch (Throwable th3) {
            jn.a(th3, "APS", "getApsReq part");
        }
        WifiInfo f3 = jiVar.f();
        boolean a3 = ji.a(f3);
        if (jq.a(networkInfo) != -1) {
            String b2 = jq.b(e);
            if (!a3 || !jiVar.e()) {
                str6 = "1";
                str5 = b2;
            } else {
                str6 = "2";
                str5 = b2;
            }
        }
        if (!a.isEmpty()) {
            StringBuilder sb3 = new StringBuilder();
            switch (d) {
                case 1:
                    jg jgVar = a.get(0);
                    sb3.delete(0, sb3.length());
                    sb3.append("<mcc>").append(jgVar.a).append("</mcc>");
                    sb3.append("<mnc>").append(jgVar.b).append("</mnc>");
                    sb3.append("<lac>").append(jgVar.c).append("</lac>");
                    sb3.append("<cellid>").append(jgVar.d);
                    sb3.append("</cellid>");
                    sb3.append("<signal>").append(jgVar.j);
                    sb3.append("</signal>");
                    str3 = sb3.toString();
                    for (int i = 1; i < a.size(); i++) {
                        jg jgVar2 = a.get(i);
                        sb.append(jgVar2.c).append(",");
                        sb.append(jgVar2.d).append(",");
                        sb.append(jgVar2.j);
                        if (i < a.size() - 1) {
                            sb.append("*");
                        }
                    }
                    break;
                case 2:
                    jg jgVar3 = a.get(0);
                    sb3.delete(0, sb3.length());
                    sb3.append("<mcc>").append(jgVar3.a).append("</mcc>");
                    sb3.append("<sid>").append(jgVar3.g).append("</sid>");
                    sb3.append("<nid>").append(jgVar3.h).append("</nid>");
                    sb3.append("<bid>").append(jgVar3.i).append("</bid>");
                    if (jgVar3.f > 0 && jgVar3.e > 0) {
                        sb3.append("<lon>").append(jgVar3.f).append("</lon>");
                        sb3.append("<lat>").append(jgVar3.e).append("</lat>");
                    }
                    sb3.append("<signal>").append(jgVar3.j).append("</signal>");
                    str3 = sb3.toString();
                    break;
                default:
                    str3 = "";
                    break;
            }
            sb3.delete(0, sb3.length());
        } else {
            str3 = "";
        }
        if ((c & 4) != 4 || b.isEmpty()) {
            this.C.clear();
        } else {
            this.C.clear();
            this.C.addAll(b);
        }
        if (jiVar.e()) {
            if (a3) {
                sb2.append(jiVar.f().getBSSID()).append(",");
                int rssi = jiVar.f().getRssi();
                if (rssi < -128) {
                    rssi = 0;
                } else if (rssi > 127) {
                    rssi = 0;
                }
                sb2.append(rssi).append(",");
                String ssid = f3.getSSID();
                int i2 = 32;
                try {
                    i2 = f3.getSSID().getBytes("UTF-8").length;
                } catch (Exception e2) {
                }
                if (i2 >= 32) {
                    ssid = "unkwn";
                }
                sb2.append(ssid.replace("*", "."));
            }
            if (!(a2 == null || this.F == null)) {
                this.F.clear();
                this.F.addAll(a2);
            }
        } else {
            jiVar.b();
            if (this.F != null) {
                this.F.clear();
            }
        }
        if (!z) {
            this.b = (short) 2;
        } else {
            this.b = (short) 0;
        }
        this.c = str7;
        this.d = str8;
        this.f = jq.d();
        this.g = f.a + jq.e();
        this.h = jq.b(context);
        this.i = str4;
        this.j = "0";
        this.k = "0";
        this.l = "0";
        this.m = "0";
        this.n = "0";
        this.o = f;
        this.p = jn.a;
        this.q = jn.b;
        this.s = String.valueOf(f2);
        this.t = str;
        this.v = "3.4.0";
        this.w = null;
        this.f23u = "";
        this.x = str5;
        this.y = str6;
        this.z = c;
        this.A = str3;
        this.B = sb.toString();
        this.D = jhVar.h();
        this.H = ji.i();
        this.E = sb2.toString();
        try {
            if (TextUtils.isEmpty(K)) {
                K = ge.f(context);
            }
        } catch (Throwable th4) {
        }
        sb.delete(0, sb.length());
        sb2.delete(0, sb2.length());
    }

    /* JADX WARN: Can't wrap try/catch for region: R(73:2|(1:4)|5|(1:9)(1:83)|10|309|11|12|293|13|14|(3:344|15|16)|323|17|18|305|19|20|321|21|22|301|23|24|(3:348|25|26)|(3:338|27|28)|315|29|30|327|31|(2:33|34)(2:104|105)|307|35|36|(3:350|37|38)|(3:333|39|(2:41|42)(2:112|113))|313|43|44|45|(4:47|(1:49)(2:118|(1:120))|50|(1:52)(3:121|(3:123|(2:125|357)(2:127|(2:129|359)(1:358))|126)|356))(2:130|(3:132|(1:134)(2:136|(1:138))|135))|53|(30:295|57|58|59|(1:156)(5:62|(1:64)|65|(5:67|(3:69|(6:75|(1:77)|78|(1:80)(2:141|(1:143))|81|353)(2:144|(6:146|(1:148)|149|(1:151)(2:153|(1:155))|152|354))|82)|292|355|82)|352)|157|(1:159)(10:192|319|193|194|195|196|197|198|(1:200)(2:202|(1:204))|201)|160|(1:162)(8:207|(1:209)(1:227)|210|(1:212)|213|(9:215|331|216|217|218|(1:220)(2:230|(1:232))|221|(2:225|361)(1:362)|226)|360|233)|163|329|164|(1:166)|(2:168|169)(2:234|235)|328|170|336|171|(1:173)|174|175|(3:346|177|178)|179|(4:181|340|182|183)(7:325|242|243|244|(31:246|334|247|248|303|249|(1:251)(1:275)|252|253|254|311|255|256|257|317|258|259|297|260|(1:262)(2:282|(1:284))|263|264|265|299|266|267|268|342|269|364|270)|363|291)|184|(1:186)|187|(1:189)|190|191)|140|59|(0)|156|157|(0)(0)|160|(0)(0)|163|329|164|(0)|(0)(0)|328|170|336|171|(0)|174|175|(0)|179|(0)(0)|184|(0)|187|(0)|190|191) */
    /* JADX WARN: Can't wrap try/catch for region: R(75:2|(1:4)|5|(1:9)(1:83)|10|309|11|12|293|13|14|(3:344|15|16)|323|17|18|305|19|20|321|21|22|301|23|24|(3:348|25|26)|(3:338|27|28)|315|29|30|327|31|(2:33|34)(2:104|105)|307|35|36|(3:350|37|38)|333|39|(2:41|42)(2:112|113)|313|43|44|45|(4:47|(1:49)(2:118|(1:120))|50|(1:52)(3:121|(3:123|(2:125|357)(2:127|(2:129|359)(1:358))|126)|356))(2:130|(3:132|(1:134)(2:136|(1:138))|135))|53|(30:295|57|58|59|(1:156)(5:62|(1:64)|65|(5:67|(3:69|(6:75|(1:77)|78|(1:80)(2:141|(1:143))|81|353)(2:144|(6:146|(1:148)|149|(1:151)(2:153|(1:155))|152|354))|82)|292|355|82)|352)|157|(1:159)(10:192|319|193|194|195|196|197|198|(1:200)(2:202|(1:204))|201)|160|(1:162)(8:207|(1:209)(1:227)|210|(1:212)|213|(9:215|331|216|217|218|(1:220)(2:230|(1:232))|221|(2:225|361)(1:362)|226)|360|233)|163|329|164|(1:166)|(2:168|169)(2:234|235)|328|170|336|171|(1:173)|174|175|(3:346|177|178)|179|(4:181|340|182|183)(7:325|242|243|244|(31:246|334|247|248|303|249|(1:251)(1:275)|252|253|254|311|255|256|257|317|258|259|297|260|(1:262)(2:282|(1:284))|263|264|265|299|266|267|268|342|269|364|270)|363|291)|184|(1:186)|187|(1:189)|190|191)|140|59|(0)|156|157|(0)(0)|160|(0)(0)|163|329|164|(0)|(0)(0)|328|170|336|171|(0)|174|175|(0)|179|(0)(0)|184|(0)|187|(0)|190|191) */
    /* JADX WARN: Code restructure failed: missing block: B:237:0x07fd, code lost:
        r3[r2] = 0;
        r2 = r2 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:239:0x0805, code lost:
        r2 = r2 + 2;
     */
    /* JADX WARN: Removed duplicated region for block: B:159:0x05cd  */
    /* JADX WARN: Removed duplicated region for block: B:162:0x05e2  */
    /* JADX WARN: Removed duplicated region for block: B:166:0x05fb  */
    /* JADX WARN: Removed duplicated region for block: B:168:0x05fe A[Catch: Throwable -> 0x07fc, TRY_LEAVE, TryCatch #19 {Throwable -> 0x07fc, blocks: (B:164:0x05ec, B:168:0x05fe, B:234:0x07ed), top: B:329:0x05ec }] */
    /* JADX WARN: Removed duplicated region for block: B:173:0x0613 A[Catch: Throwable -> 0x0804, TryCatch #24 {Throwable -> 0x0804, blocks: (B:171:0x0609, B:173:0x0613, B:174:0x061f), top: B:336:0x0609 }] */
    /* JADX WARN: Removed duplicated region for block: B:181:0x0654  */
    /* JADX WARN: Removed duplicated region for block: B:186:0x0667  */
    /* JADX WARN: Removed duplicated region for block: B:189:0x067a  */
    /* JADX WARN: Removed duplicated region for block: B:192:0x06b3  */
    /* JADX WARN: Removed duplicated region for block: B:207:0x073e  */
    /* JADX WARN: Removed duplicated region for block: B:234:0x07ed A[Catch: Throwable -> 0x07fc, TRY_ENTER, TRY_LEAVE, TryCatch #19 {Throwable -> 0x07fc, blocks: (B:164:0x05ec, B:168:0x05fe, B:234:0x07ed), top: B:329:0x05ec }] */
    /* JADX WARN: Removed duplicated region for block: B:325:0x080e A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:346:0x0628 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final byte[] a() {
        /*
            Method dump skipped, instructions count: 2301
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amap.api.col.jm.a():byte[]");
    }
}
