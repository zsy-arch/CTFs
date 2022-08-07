package com.loc;

import android.annotation.SuppressLint;
import android.net.wifi.ScanResult;
import com.loc.bu;
import java.util.ArrayList;

/* compiled from: Req.java */
@SuppressLint({"NewApi"})
/* loaded from: classes2.dex */
public final class cp {
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
    public String f35u = null;
    public String v = null;
    public String w = null;
    public String x = null;
    public String y = null;
    public int z = 0;
    public String A = null;
    public String B = null;
    public ArrayList<cb> C = new ArrayList<>();
    public String D = null;
    public String E = null;
    public ArrayList<ScanResult> F = new ArrayList<>();
    public ArrayList<bu.a> G = new ArrayList<>();
    public String H = null;
    public String I = null;
    public byte[] J = null;
    private byte[] L = null;
    private int M = 0;
    public String K = null;

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
            com.loc.f.a(r0, r1, r2)
            java.lang.String r0 = "00:00:00:00:00:00"
            byte[] r0 = r7.a(r0)
            goto L_0x0042
        */
        throw new UnsupportedOperationException("Method not decompiled: com.loc.cp.a(java.lang.String):byte[]");
    }

    private String b(String str) {
        if (!this.A.contains(str + ">")) {
            return "0";
        }
        int indexOf = this.A.indexOf(str + ">");
        return this.A.substring(indexOf + str.length() + 1, this.A.indexOf("</" + str));
    }

    /* JADX WARN: Can't wrap try/catch for region: R(122:2|(1:4)|5|(1:7)|8|(1:10)|11|(1:13)|14|(1:16)|17|(1:19)|20|(1:22)|23|(1:25)|26|(1:28)(2:183|(1:187))|29|(1:31)(2:188|(1:192))|32|(1:34)|35|(1:37)|38|(1:40)|41|(1:43)|44|(1:46)|47|(1:49)|50|(1:52)|53|(1:55)|56|(1:58)|59|(1:61)|62|(1:64)|65|(1:67)|68|(1:70)|71|(1:73)(2:193|(1:197))|74|(1:78)(1:198)|(1:80)|81|(1:83)|84|(1:86)|87|(1:89)|90|(1:92)|93|(1:95)|96|(1:98)|99|(1:101)|102|(1:104)|105|(1:109)(1:199)|110|(3:458|111|112)|(3:444|113|114)|(3:427|115|116)|411|117|118|(3:452|119|120)|(3:436|121|122)|(3:450|123|124)|(3:434|125|126)|(3:419|127|128)|(3:466|129|130)|415|131|(2:133|134)(2:220|221)|(3:456|135|136)|(3:438|137|138)|(3:418|139|(2:141|142)(2:228|229))|(3:464|143|144)|145|(4:147|(1:149)(2:234|(1:236))|150|(1:152)(3:237|(3:239|(2:241|473)(2:243|(2:245|475)(1:474))|242)|472))(2:246|(3:248|(1:250)(2:252|(1:254))|251))|153|(30:442|157|158|159|(1:272)(5:162|(1:164)|165|(5:167|(3:169|(6:175|(1:177)|178|(1:180)(2:257|(1:259))|181|469)(2:260|(6:262|(1:264)|265|(1:267)(2:269|(1:271))|268|470))|182)|408|471|182)|468)|273|(1:275)(10:308|462|309|310|311|312|313|314|(1:316)(2:318|(1:320))|317)|276|(1:278)(8:323|(1:325)(1:343)|326|(1:328)|329|(9:331|429|332|333|334|(1:336)(2:346|(1:348))|337|(2:341|477)(1:478)|342)|476|349)|279|432|280|(1:282)|(2:284|285)(2:350|351)|431|286|416|287|(1:289)|290|291|(3:425|293|294)|295|(4:297|421|298|299)(7:409|358|359|360|(31:362|413|363|364|423|365|(1:367)(1:391)|368|369|370|454|371|372|373|460|374|375|440|376|(1:378)(2:398|(1:400))|379|380|381|446|382|383|384|448|385|480|386)|479|407)|300|(1:302)|303|(1:305)|306|307)|256|159|(0)|272|273|(0)(0)|276|(0)(0)|279|432|280|(0)|(0)(0)|431|286|416|287|(0)|290|291|(0)|295|(0)(0)|300|(0)|303|(0)|306|307) */
    /* JADX WARN: Can't wrap try/catch for region: R(128:2|(1:4)|5|(1:7)|8|(1:10)|11|(1:13)|14|(1:16)|17|(1:19)|20|(1:22)|23|(1:25)|26|(1:28)(2:183|(1:187))|29|(1:31)(2:188|(1:192))|32|(1:34)|35|(1:37)|38|(1:40)|41|(1:43)|44|(1:46)|47|(1:49)|50|(1:52)|53|(1:55)|56|(1:58)|59|(1:61)|62|(1:64)|65|(1:67)|68|(1:70)|71|(1:73)(2:193|(1:197))|74|(1:78)(1:198)|(1:80)|81|(1:83)|84|(1:86)|87|(1:89)|90|(1:92)|93|(1:95)|96|(1:98)|99|(1:101)|102|(1:104)|105|(1:109)(1:199)|110|(3:458|111|112)|(3:444|113|114)|427|115|116|411|117|118|(3:452|119|120)|(3:436|121|122)|(3:450|123|124)|(3:434|125|126)|419|127|128|(3:466|129|130)|415|131|(2:133|134)(2:220|221)|(3:456|135|136)|(3:438|137|138)|418|139|(2:141|142)(2:228|229)|(3:464|143|144)|145|(4:147|(1:149)(2:234|(1:236))|150|(1:152)(3:237|(3:239|(2:241|473)(2:243|(2:245|475)(1:474))|242)|472))(2:246|(3:248|(1:250)(2:252|(1:254))|251))|153|(30:442|157|158|159|(1:272)(5:162|(1:164)|165|(5:167|(3:169|(6:175|(1:177)|178|(1:180)(2:257|(1:259))|181|469)(2:260|(6:262|(1:264)|265|(1:267)(2:269|(1:271))|268|470))|182)|408|471|182)|468)|273|(1:275)(10:308|462|309|310|311|312|313|314|(1:316)(2:318|(1:320))|317)|276|(1:278)(8:323|(1:325)(1:343)|326|(1:328)|329|(9:331|429|332|333|334|(1:336)(2:346|(1:348))|337|(2:341|477)(1:478)|342)|476|349)|279|432|280|(1:282)|(2:284|285)(2:350|351)|431|286|416|287|(1:289)|290|291|(3:425|293|294)|295|(4:297|421|298|299)(7:409|358|359|360|(31:362|413|363|364|423|365|(1:367)(1:391)|368|369|370|454|371|372|373|460|374|375|440|376|(1:378)(2:398|(1:400))|379|380|381|446|382|383|384|448|385|480|386)|479|407)|300|(1:302)|303|(1:305)|306|307)|256|159|(0)|272|273|(0)(0)|276|(0)(0)|279|432|280|(0)|(0)(0)|431|286|416|287|(0)|290|291|(0)|295|(0)(0)|300|(0)|303|(0)|306|307) */
    /* JADX WARN: Code restructure failed: missing block: B:353:0x0a5a, code lost:
        r3[r2] = 0;
        r2 = r2 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:355:0x0a62, code lost:
        r2 = r2 + 2;
     */
    /* JADX WARN: Removed duplicated region for block: B:275:0x0830  */
    /* JADX WARN: Removed duplicated region for block: B:278:0x0845  */
    /* JADX WARN: Removed duplicated region for block: B:282:0x085e  */
    /* JADX WARN: Removed duplicated region for block: B:284:0x0861 A[Catch: Throwable -> 0x0a59, TRY_LEAVE, TryCatch #13 {Throwable -> 0x0a59, blocks: (B:280:0x084f, B:284:0x0861, B:350:0x0a4a), top: B:432:0x084f }] */
    /* JADX WARN: Removed duplicated region for block: B:289:0x0874 A[Catch: Throwable -> 0x0a61, TryCatch #5 {Throwable -> 0x0a61, blocks: (B:287:0x086c, B:289:0x0874, B:290:0x087e), top: B:416:0x086c }] */
    /* JADX WARN: Removed duplicated region for block: B:297:0x08b1  */
    /* JADX WARN: Removed duplicated region for block: B:302:0x08c4  */
    /* JADX WARN: Removed duplicated region for block: B:305:0x08d7  */
    /* JADX WARN: Removed duplicated region for block: B:308:0x0910  */
    /* JADX WARN: Removed duplicated region for block: B:323:0x099b  */
    /* JADX WARN: Removed duplicated region for block: B:350:0x0a4a A[Catch: Throwable -> 0x0a59, TRY_ENTER, TRY_LEAVE, TryCatch #13 {Throwable -> 0x0a59, blocks: (B:280:0x084f, B:284:0x0861, B:350:0x0a4a), top: B:432:0x084f }] */
    /* JADX WARN: Removed duplicated region for block: B:409:0x0a6b A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:425:0x0887 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final byte[] a() {
        /*
            Method dump skipped, instructions count: 2905
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.loc.cp.a():byte[]");
    }
}
