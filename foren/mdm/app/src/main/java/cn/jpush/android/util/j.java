package cn.jpush.android.util;

import cn.jpush.a.a.a.g;
import cn.jpush.android.api.c;
import java.nio.ByteBuffer;

/* loaded from: classes.dex */
public final class j {
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
            case 2: goto L_0x0053;
            case 3: goto L_0x005b;
            case 4: goto L_0x0063;
            default: goto L_0x003b;
        };
     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x003b, code lost:
        r3[r2] = r1;
        r2 = 1;
        r1 = "Rg>|\u0013Ex,|#\u0010w$\u007f>\n";
        r0 = 0;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x0043, code lost:
        r3[r2] = r1;
        r2 = 2;
        r1 = "Rg>|\u0013Ex,|#\u0010w99?Er&";
        r0 = 1;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x004b, code lost:
        r3[r2] = r1;
        r2 = 3;
        r1 = "rg>|\u0013Ex,|#ej#u\"";
        r0 = 2;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:19:0x0053, code lost:
        r3[r2] = r1;
        r2 = 4;
        r1 = "@\u007f8j4\u0010z+m0\u0010{8k>B>9m0Su\u001ek0S{p";
        r0 = 3;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x005b, code lost:
        r3[r2] = r1;
        r2 = 5;
        r1 = "ZL/j!_p9|qYmjw$\\r";
        r0 = 4;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x0063, code lost:
        r3[r2] = r1;
        cn.jpush.android.util.j.z = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x0067, code lost:
        return;
     */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x0068, code lost:
        r9 = '0';
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x006b, code lost:
        r9 = 30;
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x006e, code lost:
        r9 = 'J';
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x0071, code lost:
        r9 = 25;
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
            case 0: goto L_0x0068;
            case 1: goto L_0x006b;
            case 2: goto L_0x006e;
            case 3: goto L_0x0071;
            default: goto L_0x001d;
        };
     */
    /* JADX WARN: Code restructure failed: missing block: B:8:0x001d, code lost:
        r9 = 'Q';
     */
    /* JADX WARN: Code restructure failed: missing block: B:9:0x001f, code lost:
        r5[r6] = (char) (r9 ^ r10);
        r6 = r8 + 1;
     */
    static {
        /*
            r0 = 6
            java.lang.String[] r3 = new java.lang.String[r0]
            r2 = 0
            java.lang.String r1 = "L|3m4Rk,\u007f4B$"
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
                case 0: goto L_0x0068;
                case 1: goto L_0x006b;
                case 2: goto L_0x006e;
                case 3: goto L_0x0071;
                default: goto L_0x001d;
            }
        L_0x001d:
            r9 = 81
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
                case 1: goto L_0x004b;
                case 2: goto L_0x0053;
                case 3: goto L_0x005b;
                case 4: goto L_0x0063;
                default: goto L_0x003b;
            }
        L_0x003b:
            r3[r2] = r1
            r2 = 1
            java.lang.String r1 = "Rg>|\u0013Ex,|#\u0010w$\u007f>\n"
            r0 = 0
            r3 = r4
            goto L_0x0008
        L_0x0043:
            r3[r2] = r1
            r2 = 2
            java.lang.String r1 = "Rg>|\u0013Ex,|#\u0010w99?Er&"
            r0 = 1
            r3 = r4
            goto L_0x0008
        L_0x004b:
            r3[r2] = r1
            r2 = 3
            java.lang.String r1 = "rg>|\u0013Ex,|#ej#u\""
            r0 = 2
            r3 = r4
            goto L_0x0008
        L_0x0053:
            r3[r2] = r1
            r2 = 4
            java.lang.String r1 = "@\u007f8j4\u0010z+m0\u0010{8k>B>9m0Su\u001ek0S{p"
            r0 = 3
            r3 = r4
            goto L_0x0008
        L_0x005b:
            r3[r2] = r1
            r2 = 5
            java.lang.String r1 = "ZL/j!_p9|qYmjw$\\r"
            r0 = 4
            r3 = r4
            goto L_0x0008
        L_0x0063:
            r3[r2] = r1
            cn.jpush.android.util.j.z = r4
            return
        L_0x0068:
            r9 = 48
            goto L_0x001f
        L_0x006b:
            r9 = 30
            goto L_0x001f
        L_0x006e:
            r9 = 74
            goto L_0x001f
        L_0x0071:
            r9 = 25
            goto L_0x001f
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.jpush.android.util.j.<clinit>():void");
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x000f  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static int a(java.nio.ByteBuffer r1, cn.jpush.a.a.a.g r2) {
        /*
            int r0 = r1.getInt()     // Catch: BufferUnderflowException -> 0x0005, BufferOverflowException -> 0x0015, Exception -> 0x001e
        L_0x0004:
            return r0
        L_0x0005:
            r0 = move-exception
            java.lang.Throwable r0 = r0.fillInStackTrace()
            a(r0, r2, r1)
        L_0x000d:
            if (r2 == 0) goto L_0x0013
            r0 = 10000(0x2710, float:1.4013E-41)
            r2.g = r0
        L_0x0013:
            r0 = -1
            goto L_0x0004
        L_0x0015:
            r0 = move-exception
            java.lang.Throwable r0 = r0.fillInStackTrace()
            a(r0, r2, r1)
            goto L_0x000d
        L_0x001e:
            r0 = move-exception
            java.lang.Throwable r0 = r0.fillInStackTrace()
            a(r0, r2, r1)
            goto L_0x000d
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.jpush.android.util.j.a(java.nio.ByteBuffer, cn.jpush.a.a.a.g):int");
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x000f  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.nio.ByteBuffer a(java.nio.ByteBuffer r1, byte[] r2, cn.jpush.a.a.a.g r3) {
        /*
            java.nio.ByteBuffer r0 = r1.get(r2)     // Catch: BufferUnderflowException -> 0x0005, BufferOverflowException -> 0x0015, Exception -> 0x001e
        L_0x0004:
            return r0
        L_0x0005:
            r0 = move-exception
            java.lang.Throwable r0 = r0.fillInStackTrace()
            a(r0, r3, r1)
        L_0x000d:
            if (r3 == 0) goto L_0x0013
            r0 = 10000(0x2710, float:1.4013E-41)
            r3.g = r0
        L_0x0013:
            r0 = 0
            goto L_0x0004
        L_0x0015:
            r0 = move-exception
            java.lang.Throwable r0 = r0.fillInStackTrace()
            a(r0, r3, r1)
            goto L_0x000d
        L_0x001e:
            r0 = move-exception
            java.lang.Throwable r0 = r0.fillInStackTrace()
            a(r0, r3, r1)
            goto L_0x000d
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.jpush.android.util.j.a(java.nio.ByteBuffer, byte[], cn.jpush.a.a.a.g):java.nio.ByteBuffer");
    }

    private static void a(Throwable th, g gVar, ByteBuffer byteBuffer) {
        c a = c.a();
        StringBuilder sb = new StringBuilder();
        if (gVar != null) {
            sb.append(gVar == null ? z[5] : gVar.toString());
            sb.append(z[0] + (byteBuffer == null ? z[2] : byteBuffer.toString()));
        }
        ac.e(z[3], z[1] + sb.toString());
        ac.e(z[3], z[4] + th.getStackTrace().toString());
        a.a(th, sb.toString());
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x000f  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static short b(java.nio.ByteBuffer r1, cn.jpush.a.a.a.g r2) {
        /*
            short r0 = r1.getShort()     // Catch: BufferUnderflowException -> 0x0005, BufferOverflowException -> 0x0015, Exception -> 0x001e
        L_0x0004:
            return r0
        L_0x0005:
            r0 = move-exception
            java.lang.Throwable r0 = r0.fillInStackTrace()
            a(r0, r2, r1)
        L_0x000d:
            if (r2 == 0) goto L_0x0013
            r0 = 10000(0x2710, float:1.4013E-41)
            r2.g = r0
        L_0x0013:
            r0 = -1
            goto L_0x0004
        L_0x0015:
            r0 = move-exception
            java.lang.Throwable r0 = r0.fillInStackTrace()
            a(r0, r2, r1)
            goto L_0x000d
        L_0x001e:
            r0 = move-exception
            java.lang.Throwable r0 = r0.fillInStackTrace()
            a(r0, r2, r1)
            goto L_0x000d
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.jpush.android.util.j.b(java.nio.ByteBuffer, cn.jpush.a.a.a.g):short");
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.lang.Byte c(java.nio.ByteBuffer r1, cn.jpush.a.a.a.g r2) {
        /*
            byte r0 = r1.get()     // Catch: BufferUnderflowException -> 0x0009, BufferOverflowException -> 0x0019, Exception -> 0x0022
            java.lang.Byte r0 = java.lang.Byte.valueOf(r0)     // Catch: BufferUnderflowException -> 0x0009, BufferOverflowException -> 0x0019, Exception -> 0x0022
        L_0x0008:
            return r0
        L_0x0009:
            r0 = move-exception
            java.lang.Throwable r0 = r0.fillInStackTrace()
            a(r0, r2, r1)
        L_0x0011:
            if (r2 == 0) goto L_0x0017
            r0 = 10000(0x2710, float:1.4013E-41)
            r2.g = r0
        L_0x0017:
            r0 = 0
            goto L_0x0008
        L_0x0019:
            r0 = move-exception
            java.lang.Throwable r0 = r0.fillInStackTrace()
            a(r0, r2, r1)
            goto L_0x0011
        L_0x0022:
            r0 = move-exception
            java.lang.Throwable r0 = r0.fillInStackTrace()
            a(r0, r2, r1)
            goto L_0x0011
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.jpush.android.util.j.c(java.nio.ByteBuffer, cn.jpush.a.a.a.g):java.lang.Byte");
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x000f  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static long d(java.nio.ByteBuffer r2, cn.jpush.a.a.a.g r3) {
        /*
            long r0 = r2.getLong()     // Catch: BufferUnderflowException -> 0x0005, BufferOverflowException -> 0x0016, Exception -> 0x001f
        L_0x0004:
            return r0
        L_0x0005:
            r0 = move-exception
            java.lang.Throwable r0 = r0.fillInStackTrace()
            a(r0, r3, r2)
        L_0x000d:
            if (r3 == 0) goto L_0x0013
            r0 = 10000(0x2710, float:1.4013E-41)
            r3.g = r0
        L_0x0013:
            r0 = 0
            goto L_0x0004
        L_0x0016:
            r0 = move-exception
            java.lang.Throwable r0 = r0.fillInStackTrace()
            a(r0, r3, r2)
            goto L_0x000d
        L_0x001f:
            r0 = move-exception
            java.lang.Throwable r0 = r0.fillInStackTrace()
            a(r0, r3, r2)
            goto L_0x000d
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.jpush.android.util.j.d(java.nio.ByteBuffer, cn.jpush.a.a.a.g):long");
    }
}
