package cn.jpush.android.util;

import cn.jpush.android.api.e;
import java.util.Set;
import java.util.regex.Pattern;

/* loaded from: classes.dex */
public final class ae {
    public static final Pattern a;
    public static final Pattern b;
    public static final Pattern c;
    public static final Pattern d;
    public static final Pattern e;
    public static final Pattern f;
    private static final String[] z;

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
        if (r5 > r6) goto L_0x0011;
     */
    /* JADX WARN: Code restructure failed: missing block: B:14:0x002f, code lost:
        r1 = new java.lang.String(r1).intern();
     */
    /* JADX WARN: Code restructure failed: missing block: B:15:0x0038, code lost:
        switch(r0) {
            case 0: goto L_0x0043;
            case 1: goto L_0x004b;
            default: goto L_0x003b;
        };
     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x003b, code lost:
        r3[r2] = r1;
        r2 = 1;
        r1 = "WJ丶C鿊9<\u000f\u000fBsP\u001b40(Q\u0015JI#:\u000b@\u0013￬´kEK";
        r0 = 0;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x0043, code lost:
        r3[r2] = r1;
        r2 = 2;
        r1 = "!9\u001eF4h<L/BS!\u001bWÏ$ퟮ虜Cﶠﷹ<\uffd934h<L/BS!\u001bWÏ$ퟮ虜Cﶠﷹ<\uffd92BT;\u001fD4h<L/BS!\u001bWÏ$ퟮ虜Cﶠﷹ<\uffd933'8\u001d";
        r0 = 1;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x004b, code lost:
        r3[r2] = r1;
        cn.jpush.android.util.ae.z = r3;
        r1 = "!9W\u000b\u001dfmW\u001c\u001fhmW\u001d\u0006hmW5\fmtP\t\u0006e|X\u0001\u001e{bB\u001b\u0018qkkG\u0013!s_\u0014\u0013kJW\f\u000blwQ\u0006\u0006c|X\u0001\u001dze@\u0019\u0016sL\u001f\u0012GjpB\u0012\ff|J\r\u0000faJ\r4hrR\b\bax]\u0002\u0002g~D\u001b\u0019qhL3Fuum\u000b\u0005b|Y\u00142u9S\n\u001autm\r\nncE\u001a\u001aT8J\b4`{]\u0003\u0000{LJF\bfgJ\t4hsR\u000b\tny_\u0002\u0002gaG\u001c\u001c}dA\u00172 m^5\u0004d\u007fD\u001a\u001aTm\u001e\u0007\u0001o~J\u0007\u0001}m_5\u000bl}[\u0000\u0000xcE\u001a2 m\u001e\u0004\u0000kbJ\u00044l|Y\u001e2 m]5\nny_\u0003\u0001ycA\u0017\u0015TmZ5\u000ekr_\u0005\u001dzeC\u0018\u0016Tm\u001e\u0003\u0006em[\u0001\r`m[\u001b\u001cld[\u0012\u0002RpU\n\nny]\u0002\u0002g~F\u001f\u001dzeC\u0018\u0018qhL3Fu9X\u000f\u0002lmX\u000b\u001bu\u007fm\u000f\flwQ\u0007\u0003faD\u001b\u0015T8JF\u0000{vJ\u0001\u0002 m\u001e\u001e\u001dfmF5\u000elwQ\u0006\u0004e|X\u001c\u001c}fO3Fu`W\u0012\u001dRtY\u001d\u001a~LJ\u001d4hsU\n\nny_\u0004\u0004e|X\u0001\u001d}d@\u0017\u0015Tm\u001e\u001a\nemB\u001c\u000e\u007ftZ\u0012\u001bRrR\b\ba{]\u0002\u0002g~F\u001c\u001b\u007ffL3Fudm\u000f\bbbO\u00142ugm\u000f\flv_\u0000\u001aTmA5\tzLJF\u0017gM\u001b2B9kA\u0003Z?uJ\u0016\u0001U<jC^8s\u0003\f\u001c:p\u000f\u000f\u0005?vJ\u0016\u0001U<jCW9p]\u0006\rpzX\u0004[omN\u00003$M\u001bW\u001b=s\u0007_\u0016`$W\u0012\u0017gM\u001b2BmtT\u000f_huJ\u0016\u0001U<jC\b?f\u0004[^mmN\u00003$M\u001b\u0006\bkz\u0000\u000f\u0005>w\u0003]\rkpJ\u0016\u0001U<jC\u0007er\\X\u000epp\u000f\u000b\u001cj&W\u0012\u0017gM\u001b2BciW\u0002\u001fm}F\u0012\u0017gM\u001b2BbvT\u000b\fae@\u0012\u0017gM\u001b2Bsr]\u0014\u000ea8J\u00174leC3\u0013sJW\u0003\u0018T8";
        r0 = 65535;
     */
    /* JADX WARN: Code restructure failed: missing block: B:19:0x0052, code lost:
        r1 = r1.toCharArray();
        r2 = r1.length;
        r3 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x0059, code lost:
        if (r2 > 1) goto L_0x0106;
     */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x005b, code lost:
        r5 = r3;
        r2 = r1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x0060, code lost:
        r7 = r2[r3];
     */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x0064, code lost:
        switch((r5 % 5)) {
            case 0: goto L_0x00f4;
            case 1: goto L_0x00f8;
            case 2: goto L_0x00fc;
            case 3: goto L_0x0100;
            default: goto L_0x0067;
        };
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x0067, code lost:
        r6 = 'o';
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x0069, code lost:
        r2[r3] = (char) (r6 ^ r7);
        r3 = r5 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x006f, code lost:
        if (r2 != 0) goto L_0x0104;
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x0071, code lost:
        r2 = r1;
        r5 = r3;
        r3 = r2;
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x0075, code lost:
        r9 = '\t';
     */
    /* JADX WARN: Code restructure failed: missing block: B:29:0x0078, code lost:
        r9 = 17;
     */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x007b, code lost:
        r9 = '6';
     */
    /* JADX WARN: Code restructure failed: missing block: B:31:0x007e, code lost:
        r9 = 'n';
     */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x0081, code lost:
        cn.jpush.android.util.ae.c = java.util.regex.Pattern.compile(r1);
        r6 = new java.lang.StringBuilder(cn.jpush.android.util.ae.z[2]).append(cn.jpush.android.util.ae.a);
        r0 = " m".toCharArray();
        r1 = r0.length;
        r2 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x00a0, code lost:
        if (r1 > 1) goto L_0x00ca;
     */
    /* JADX WARN: Code restructure failed: missing block: B:34:0x00a2, code lost:
        r4 = r2;
        r1 = r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:35:0x00a7, code lost:
        r7 = r1[r2];
     */
    /* JADX WARN: Code restructure failed: missing block: B:36:0x00ab, code lost:
        switch((r4 % 5)) {
            case 0: goto L_0x00bc;
            case 1: goto L_0x00bf;
            case 2: goto L_0x00c2;
            case 3: goto L_0x00c5;
            default: goto L_0x00ae;
        };
     */
    /* JADX WARN: Code restructure failed: missing block: B:37:0x00ae, code lost:
        r5 = 'o';
     */
    /* JADX WARN: Code restructure failed: missing block: B:38:0x00b0, code lost:
        r1[r2] = (char) (r5 ^ r7);
        r2 = r4 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:39:0x00b6, code lost:
        if (r1 != 0) goto L_0x00c8;
     */
    /* JADX WARN: Code restructure failed: missing block: B:40:0x00b8, code lost:
        r1 = r0;
        r4 = r2;
        r2 = r1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:41:0x00bc, code lost:
        r5 = '\t';
     */
    /* JADX WARN: Code restructure failed: missing block: B:42:0x00bf, code lost:
        r5 = 17;
     */
    /* JADX WARN: Code restructure failed: missing block: B:43:0x00c2, code lost:
        r5 = '6';
     */
    /* JADX WARN: Code restructure failed: missing block: B:44:0x00c5, code lost:
        r5 = 'n';
     */
    /* JADX WARN: Code restructure failed: missing block: B:45:0x00c8, code lost:
        r1 = r1;
        r0 = r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:46:0x00ca, code lost:
        if (r1 > r2) goto L_0x00a2;
     */
    /* JADX WARN: Code restructure failed: missing block: B:47:0x00cc, code lost:
        cn.jpush.android.util.ae.d = java.util.regex.Pattern.compile(r6.append(new java.lang.String(r0).intern()).append(cn.jpush.android.util.ae.c).append(")").toString());
        r1 = "Rp\u001b\u0014.$K\u0006CVU:j@3VM\u00132BU:k\u0015^%#\u0003X\u0012UQm\u000fBsP\u001b4_$(k5\u000e$kwC59<\u000f2BTj\u0006BY=l\u001e2ARp\u001b\u0014.$K\u0006CVTJWC\u0015H<l^B0M\u001b3\u00149=\u0004[\u0012 :";
        r0 = 2;
     */
    /* JADX WARN: Code restructure failed: missing block: B:48:0x00f4, code lost:
        r6 = '\t';
     */
    /* JADX WARN: Code restructure failed: missing block: B:49:0x00f8, code lost:
        r6 = 17;
     */
    /* JADX WARN: Code restructure failed: missing block: B:4:0x000f, code lost:
        if (r5 <= 1) goto L_0x0011;
     */
    /* JADX WARN: Code restructure failed: missing block: B:50:0x00fc, code lost:
        r6 = '6';
     */
    /* JADX WARN: Code restructure failed: missing block: B:51:0x0100, code lost:
        r6 = 'n';
     */
    /* JADX WARN: Code restructure failed: missing block: B:52:0x0104, code lost:
        r2 = r2;
        r1 = r1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:53:0x0106, code lost:
        if (r2 > r3) goto L_0x005b;
     */
    /* JADX WARN: Code restructure failed: missing block: B:54:0x0108, code lost:
        r1 = new java.lang.String(r1).intern();
     */
    /* JADX WARN: Code restructure failed: missing block: B:55:0x0111, code lost:
        switch(r0) {
            case 0: goto L_0x011f;
            case 1: goto L_0x0081;
            case 2: goto L_0x012a;
            case 3: goto L_0x0135;
            default: goto L_0x0114;
        };
     */
    /* JADX WARN: Code restructure failed: missing block: B:56:0x0114, code lost:
        cn.jpush.android.util.ae.a = java.util.regex.Pattern.compile(r1);
        r1 = "!9\tTGaeB\u001e\u0013aeB\u001e\u001cuYB\u001a\u001fuYB\u001a\u001fzmD\u001a\u001cymd\u001a\u001cy8\f2@U>\u001eQU!.\f5\u000e$kwC59<\u000f2KU<j13'M\u001d2NU;jI3!M\u001f2CU*jQ3/M\u000b3\u0013!.\f2JRp\u001b\b.$W\u0006CVTj\u0004\u0013F j\u0007BY=l\u001eQUU+\u001eQURp\u001b\u0014.$K\u0006CVU5jC3VM\u00182DU0jD3.M\u001e2FU=jU36M\u00102RTm\u001eQUU4m\u000fBoP\u001b(_$(k\u0015]t8\u001f\u0015^%#\u0003\u0013F6MvGP .\u001eFP39\tT4h<L/BS!\u001bWÏ$ퟮ虜Cﶠﷹ<\uffd934h<L/BS!\u001bWÏ$ퟮ虜Cﶠﷹ<\uffd92BTj\u0006BY=lj@F\"9\tTG6+W\u000b\u001dfmW\u001c\u001fhmW\u001d\u0006hmW5\fmtP\t\u0006e|X\u0001\u001e{bB\u001b\u0018qkkG\u0013!.\f\f\u0006smT5\u000ekuS\b\bax\\\u0003\u0001fcE\u001a\u0019~hL3Fu9\tT\fheJ\r\u0000dmU\u0001\u0000ymU5\u000ejuP\t\u0007`zZ\u0003\u0001fcC\u0018\u0017pkkG\u0013mJS\u0004\u0004d~L3\u0013!.\f\u000b\u000b|mS5\flvD\u001d\u001b|L\u001f\u0012\tRx\\\u0005\u0002fck\u0012G6+Q\u0001\u0019uvm\u000f\rmtP\t\u0007`}[\u0000\u001fxcE\u001a\u001a~hkG\u0013aJ]\u0003\u0001{eC3\u0013!.\f\u0007\u0001o~J\u0007\u0001}m_5\u000bl}[\u0000\u0000xcE\u001a2 m\u001eQUc~T\u001d\u0013cJS\u0003\u0000yL\u001f\u0012\u0004RtQ\u0006\u0006d\u007fF\u001c\u0018pkk\u0012\u0003RpT\r\u0006bcE\u001a\u001a\u007fhk\u0012G6+[\u0007\u0003u|Y\f\u0006u|C\u001d\n||J\u00034hrR\u000b\bazZ\u0003\u0001faG\u001c\u001c}d@\u0019\u0017pkkG\u0013!.\f\u0000\u000edtJ\u0000\n}mX5\u000ejtP\t\u0006e~F\u001c\u001asL\u001f\u0012G6+Y\u001c\bu~[G\u0013!.\f\u001e\u001dfmF5\u000elwQ\u0006\u0004e|X\u001c\u001c}fO3Fu`W\u0012\u001dRtY\u001d\u001a~LJ\u001d4hsU\n\nny_\u0004\u0004e|X\u0001\u001d}d@\u0017\u0015Tm\u001eQU}tZ\u0012\u001b{p@\u000b\u0003uem\r\u000bov^\u0004\u0004e|X\u0001\u001f{e@\u0019\u0015T8J\u001b4hv]\u001d\u0016sLJ\u00184hrS\t\u0006gdk\u0012\u0018RwE3\u0013!.\f\u0016\u0001U<jC_sf[[YmmN\u00003$M\u001b_^k$T\u001d\\h(W\u0004YnmN\u00003$M\u001bV_hz^\f\u0016b\u007f\\Z\tuiX2BU<\u000f\u001a[k \u0007\u0017\u0006<pJ\u0016\u0001U<jC\u000blsW^\u000emmN\u00003$M\u001b\tY~#\u0003_\u000buiX2BU<^\t\rb'W\u0004Xo$\u0005\f\rhmN\u00003$M\u001b\u0006\u0003j{\u0000\u000f\u0016h(S\u001d\f>pJ\u0016\u0001U<jC\u0005qpZ\u001e\u000beaJ\u0016\u0001U<jC\u0004nsS\r\u0007}gJ\u0016\u0001U<jC\u0015jzL\u000f\u0007 mO5\n}dk\u0012\u0015Rp[\u00192 8JFP39\tT]<J\u0006CZTm\u00045_$%k5_$(k\u001249<\u0007349<\u000f3\u0014;lJ5^$(k5_$(k\u001248<\u000f3FU?\u001eQU;$m^B<LJ\\49<\u0002349<\u000f3\u0013R!\u001b_2R!\u001bW2r#K\u001248<\u000f349<\u000f3\u0013R \u001bW2u!\u001f2A!.\f\\ZR!\u001b[2u#m^B=Lm^B0LJ5_$ k5_$(k\u0015]tmm_B0Lm^B0LJ5^$(k\u0012_ M\u0018FP3#\u00035_$$k\u0012]R!\u001bZ2R!\u001bW2uJ\u0006C^TJ\u0006CVTj\u0004\u0013\u0013R \u001bW2R!\u001bW2uJ\u0006CVT8\u001fGG6+jT3mj\u0007BZt8\tGGU>\u001eQU!.\f5\u000e$kwC59<\u000fÎBퟶ螺\u001bﶡﶟ$\ufffejU3&M\t2UUQjH34M\u00152\u0011U<j@3\"M\u00172EU6jF3 M\u001a20T8JFP3M\u00135\u000e$wwC)9<\u000f3\u0014;l\u001fGE .\u001eQUUsJJF";
        r0 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:57:0x011f, code lost:
        cn.jpush.android.util.ae.b = java.util.regex.Pattern.compile(r1);
        r1 = "!9\u0004[49<\u00033\u0013;J\u0006C[TJ\u0006CVTmm^B8Lm^B0LM\\\u0012uJ\u0007CVTJ\u0006CVTmm_B0L\u001f2A!#\u00035_$$k\u0012]R!\u001bZ2R!\u001bW2uJ\u0006C^TJ\u0006CVTj\u0004\u0013\u0013R \u001bW2R!\u001bW2uJ\u0007CVTm\u0006G3'9\u0004[49<\u00033\u0013;J\u0006C[TJ\u0006CVTmm^B8Lm^B0LM\\\u0012uJ\u0007CVTJ\u0006CVTmm_B0LJ^FU?\u001e\\ZR!\u001b[2u#m^B=Lm^B0LJ5_$ k5_$(k\u0015]tmm_B0Lm^B0LJ5_$(kGF";
        r0 = 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:58:0x012a, code lost:
        cn.jpush.android.util.ae.e = java.util.regex.Pattern.compile(r1);
        r1 = "!M\u001d5_$(kE4U<\u00162AT;\u001fQGU9m^B0L\u001d2FRM\u001bN3'L\u001cGP!J\u0006CVTJ\u0006CVU<\u00162ATJ\u0006CVU<\u00162AT:m^B0L\u001f";
        r0 = 3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:59:0x0135, code lost:
        cn.jpush.android.util.ae.f = java.util.regex.Pattern.compile(r1);
     */
    /* JADX WARN: Code restructure failed: missing block: B:5:0x0011, code lost:
        r8 = r6;
        r5 = r1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:60:0x013b, code lost:
        return;
     */
    /* JADX WARN: Code restructure failed: missing block: B:6:0x0016, code lost:
        r10 = r5[r6];
     */
    /* JADX WARN: Code restructure failed: missing block: B:7:0x001a, code lost:
        switch((r8 % 5)) {
            case 0: goto L_0x0075;
            case 1: goto L_0x0078;
            case 2: goto L_0x007b;
            case 3: goto L_0x007e;
            default: goto L_0x001d;
        };
     */
    /* JADX WARN: Code restructure failed: missing block: B:8:0x001d, code lost:
        r9 = 'o';
     */
    /* JADX WARN: Code restructure failed: missing block: B:9:0x001f, code lost:
        r5[r6] = (char) (r9 ^ r10);
        r6 = r8 + 1;
     */
    static {
        /*
            Method dump skipped, instructions count: 372
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.jpush.android.util.ae.<clinit>():void");
    }

    public static int a(Set<String> set) {
        if (set == null || set.isEmpty()) {
            return 0;
        }
        if (set.size() > 1000) {
            return e.g;
        }
        for (String str : set) {
            if (str == null) {
                return e.e;
            }
            if (str.getBytes().length > 40) {
                return e.f;
            }
            if (!Pattern.compile(z[1]).matcher(str).matches()) {
                return e.e;
            }
        }
        return 0;
    }

    public static boolean a(String str) {
        if (str == null) {
            return false;
        }
        return Pattern.compile(z[0]).matcher(str).matches();
    }

    public static int b(String str) {
        if (str == null || ao.a(str)) {
            return 0;
        }
        if (str.getBytes().length > 40) {
            return e.d;
        }
        if (!Pattern.compile(z[1]).matcher(str).matches()) {
            return e.c;
        }
        return 0;
    }
}
