package cn.jpush.a.a.b;

import cn.jpush.a.a.a.g;
import cn.jpush.android.util.ac;
import cn.jpush.android.util.j;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;

/* loaded from: classes.dex */
public final class a {
    private static final String z;

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
        cn.jpush.a.a.b.a.z = new java.lang.String(r0).intern();
     */
    /* JADX WARN: Code restructure failed: missing block: B:14:0x0034, code lost:
        return;
     */
    /* JADX WARN: Code restructure failed: missing block: B:15:0x0035, code lost:
        r5 = '2';
     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x0038, code lost:
        r5 = 'g';
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x003b, code lost:
        r5 = 'l';
     */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x003e, code lost:
        r5 = '\n';
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
        r5 = '$';
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
            java.lang.String r0 = "g3*'\u001c"
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
            r5 = 36
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
            cn.jpush.a.a.b.a.z = r0
            return
        L_0x0035:
            r5 = 50
            goto L_0x0019
        L_0x0038:
            r5 = 103(0x67, float:1.44E-43)
            goto L_0x0019
        L_0x003b:
            r5 = 108(0x6c, float:1.51E-43)
            goto L_0x0019
        L_0x003e:
            r5 = 10
            goto L_0x0019
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.jpush.a.a.b.a.<clinit>():void");
    }

    public static String a(ByteBuffer byteBuffer) {
        try {
            byte[] bArr = new byte[byteBuffer.getShort()];
            byteBuffer.get(bArr);
            return new String(bArr, z);
        } catch (UnsupportedEncodingException | Exception e) {
            return null;
        }
    }

    public static String a(ByteBuffer byteBuffer, g gVar) {
        int b = j.b(byteBuffer, gVar);
        if (b < 0) {
            ac.d();
            return null;
        }
        byte[] bArr = new byte[b];
        j.a(byteBuffer, bArr, gVar);
        try {
            return new String(bArr, z);
        } catch (UnsupportedEncodingException e) {
            return null;
        }
    }

    public static void a(byte[] bArr, int i, int i2) {
        System.arraycopy(new byte[]{(byte) (i >>> 24), (byte) (i >>> 16), (byte) (i >>> 8), (byte) i}, 0, bArr, i2, 4);
    }

    public static void a(byte[] bArr, String str, int i) {
        byte[] bytes = str.getBytes();
        System.arraycopy(bytes, 0, bArr, i, bytes.length);
    }
}
