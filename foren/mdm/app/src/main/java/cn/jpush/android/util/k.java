package cn.jpush.android.util;

import java.io.File;

/* loaded from: classes.dex */
public final class k {
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
            case 1: goto L_0x004c;
            case 2: goto L_0x0054;
            case 3: goto L_0x005c;
            default: goto L_0x003b;
        };
     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x003b, code lost:
        r3[r2] = r1;
        r2 = 1;
        r1 = "(\u001f^\u001bf8B\u001b]y3\u001a\u001bHn.\u0001^I+5\u0004\u0001\u001b";
        r0 = 0;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x0043, code lost:
        r3[r2] = r1;
        r2 = 2;
        r1 = "|\u0013T\u001be3\u0003\u001bUn9\u0013\u001bXc9\u0014P\u001bF\u0018B\u001bXd8\u0012\u0017\u001by9\u0003NIe|\u0003INn";
        r0 = 1;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x004c, code lost:
        r3[r2] = r1;
        r2 = 3;
        r1 = "1\u0013\u000e\u001bm.\u0018V\u001bx9\u0005M^y|\u0004R_nfW";
        r0 = 2;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:19:0x0054, code lost:
        r3[r2] = r1;
        r2 = 4;
        r1 = "\u00113\u000e";
        r0 = 3;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x005c, code lost:
        r3[r2] = r1;
        cn.jpush.android.util.k.z = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x0060, code lost:
        return;
     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x0061, code lost:
        r9 = '\\';
     */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x0064, code lost:
        r9 = 'w';
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x0067, code lost:
        r9 = ';';
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x006a, code lost:
        r9 = ';';
     */
    /* JADX WARN: Code restructure failed: missing block: B:4:0x000f, code lost:
        if (r5 <= 1) goto L_0x0011;
     */
    /* JADX WARN: Code restructure failed: missing block: B:5:0x0011, code lost:
        r8 = r6;
        r5 = r1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:6:0x0016, code lost:
        r10 = r5[r6];
     */
    /* JADX WARN: Code restructure failed: missing block: B:7:0x001a, code lost:
        switch((r8 % 5)) {
            case 0: goto L_0x0061;
            case 1: goto L_0x0064;
            case 2: goto L_0x0067;
            case 3: goto L_0x006a;
            default: goto L_0x001d;
        };
     */
    /* JADX WARN: Code restructure failed: missing block: B:8:0x001d, code lost:
        r9 = 11;
     */
    /* JADX WARN: Code restructure failed: missing block: B:9:0x001f, code lost:
        r5[r6] = (char) (r9 ^ r10);
        r6 = r8 + 1;
     */
    static {
        /*
            r0 = 5
            java.lang.String[] r3 = new java.lang.String[r0]
            r2 = 0
            java.lang.String r1 = "1\u0013\u000e\u001bb2WOSn|\u0014WRn(W]Rg9M\u001b"
            r0 = -1
            r4 = r3
        L_0x0008:
            char[] r1 = r1.toCharArray()
            int r5 = r1.length
            r6 = 0
            r7 = 1
            if (r5 > r7) goto L_0x002d
        L_0x0011:
            r7 = r1
            r8 = r6
            r11 = r5
            r5 = r1
            r1 = r11
        L_0x0016:
            char r10 = r5[r6]
            int r9 = r8 % 5
            switch(r9) {
                case 0: goto L_0x0061;
                case 1: goto L_0x0064;
                case 2: goto L_0x0067;
                case 3: goto L_0x006a;
                default: goto L_0x001d;
            }
        L_0x001d:
            r9 = 11
        L_0x001f:
            r9 = r9 ^ r10
            char r9 = (char) r9
            r5[r6] = r9
            int r6 = r8 + 1
            if (r1 != 0) goto L_0x002b
            r5 = r7
            r8 = r6
            r6 = r1
            goto L_0x0016
        L_0x002b:
            r5 = r1
            r1 = r7
        L_0x002d:
            if (r5 > r6) goto L_0x0011
            java.lang.String r5 = new java.lang.String
            r5.<init>(r1)
            java.lang.String r1 = r5.intern()
            switch(r0) {
                case 0: goto L_0x0043;
                case 1: goto L_0x004c;
                case 2: goto L_0x0054;
                case 3: goto L_0x005c;
                default: goto L_0x003b;
            }
        L_0x003b:
            r3[r2] = r1
            r2 = 1
            java.lang.String r1 = "(\u001f^\u001bf8B\u001b]y3\u001a\u001bHn.\u0001^I+5\u0004\u0001\u001b"
            r0 = 0
            r3 = r4
            goto L_0x0008
        L_0x0043:
            r3[r2] = r1
            r2 = 2
            java.lang.String r1 = "|\u0013T\u001be3\u0003\u001bUn9\u0013\u001bXc9\u0014P\u001bF\u0018B\u001bXd8\u0012\u0017\u001by9\u0003NIe|\u0003INn"
            r0 = 1
            r3 = r4
            goto L_0x0008
        L_0x004c:
            r3[r2] = r1
            r2 = 3
            java.lang.String r1 = "1\u0013\u000e\u001bm.\u0018V\u001bx9\u0005M^y|\u0004R_nfW"
            r0 = 2
            r3 = r4
            goto L_0x0008
        L_0x0054:
            r3[r2] = r1
            r2 = 4
            java.lang.String r1 = "\u00113\u000e"
            r0 = 3
            r3 = r4
            goto L_0x0008
        L_0x005c:
            r3[r2] = r1
            cn.jpush.android.util.k.z = r4
            return
        L_0x0061:
            r9 = 92
            goto L_0x001f
        L_0x0064:
            r9 = 119(0x77, float:1.67E-43)
            goto L_0x001f
        L_0x0067:
            r9 = 59
            goto L_0x001f
        L_0x006a:
            r9 = 59
            goto L_0x001f
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.jpush.android.util.k.<clinit>():void");
    }

    public static boolean a(String str, File file) {
        new StringBuilder(z[3]).append(str);
        ac.b();
        if (str == null || "".equals(str)) {
            new StringBuilder(z[1]).append(str).append(z[2]);
            ac.b();
            return true;
        } else if (!file.exists() || file.length() == 0) {
            return false;
        } else {
            String b = b(file);
            new StringBuilder(z[0]).append(b);
            ac.b();
            if (b == null || "".equals(b) || !b.equals(str)) {
                ac.b();
                return false;
            }
            ac.b();
            return true;
        }
    }

    /*  JADX ERROR: JadxRuntimeException in pass: SSATransform
        jadx.core.utils.exceptions.JadxRuntimeException: Not initialized variable reg: 1, insn: 0x004e: MOVE  (r2 I:??[OBJECT, ARRAY]) = (r1 I:??[OBJECT, ARRAY]), block:B:32:0x004d
        	at jadx.core.dex.visitors.ssa.SSATransform.renameVarsInBlock(SSATransform.java:171)
        	at jadx.core.dex.visitors.ssa.SSATransform.renameVariables(SSATransform.java:143)
        	at jadx.core.dex.visitors.ssa.SSATransform.process(SSATransform.java:60)
        	at jadx.core.dex.visitors.ssa.SSATransform.visit(SSATransform.java:41)
        */
    private static byte[] a(
    /*  JADX ERROR: JadxRuntimeException in pass: SSATransform
        jadx.core.utils.exceptions.JadxRuntimeException: Not initialized variable reg: 1, insn: 0x004e: MOVE  (r2 I:??[OBJECT, ARRAY]) = (r1 I:??[OBJECT, ARRAY]), block:B:32:0x004d
        	at jadx.core.dex.visitors.ssa.SSATransform.renameVarsInBlock(SSATransform.java:171)
        	at jadx.core.dex.visitors.ssa.SSATransform.renameVariables(SSATransform.java:143)
        	at jadx.core.dex.visitors.ssa.SSATransform.process(SSATransform.java:60)
        */
    /*  JADX ERROR: Method generation error
        jadx.core.utils.exceptions.JadxRuntimeException: Code variable not set in r7v0 ??
        	at jadx.core.dex.instructions.args.SSAVar.getCodeVar(SSAVar.java:233)
        	at jadx.core.codegen.MethodGen.addMethodArguments(MethodGen.java:209)
        	at jadx.core.codegen.MethodGen.addDefinition(MethodGen.java:162)
        	at jadx.core.codegen.ClassGen.addMethodCode(ClassGen.java:364)
        	at jadx.core.codegen.ClassGen.addMethod(ClassGen.java:304)
        	at jadx.core.codegen.ClassGen.lambda$addInnerClsAndMethods$3(ClassGen.java:270)
        	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.accept(Unknown Source)
        	at java.base/java.util.ArrayList.forEach(Unknown Source)
        	at java.base/java.util.stream.SortedOps$RefSortingSink.end(Unknown Source)
        	at java.base/java.util.stream.Sink$ChainedReference.end(Unknown Source)
        */

    private static String b(File file) {
        byte[] a = a(file);
        String str = "";
        if (a != null && a.length > 0) {
            for (int i = 0; i < a.length; i++) {
                str = str + Integer.toString((a[i] & 255) + 256, 16).substring(1);
            }
        }
        return str;
    }
}
