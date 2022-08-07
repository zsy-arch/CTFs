package cn.jpush.android.service;

/* loaded from: classes.dex */
public class PushProtocol {
    private static final String z;

    /* JADX WARN: Removed duplicated region for block: B:12:0x0023 A[LOOP:1: B:7:0x0012->B:12:0x0023, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:15:0x002b  */
    /* JADX WARN: Removed duplicated region for block: B:19:0x003e  */
    /* JADX WARN: Removed duplicated region for block: B:20:0x0041  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x0043  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x0046  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x0027 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:9:0x0019  */
    static {
        /*
            r1 = 0
            java.lang.String r2 = "\u0001|=\u0001Y?+\"\u001a]6I'\u0017N3w7O\u00068u;\u0006T`4w"
            r0 = -1
        L_0x0004:
            char[] r2 = r2.toCharArray()
            int r3 = r2.length
            r4 = 1
            if (r3 > r4) goto L_0x005f
            r4 = r1
        L_0x000d:
            r5 = r2
            r6 = r4
            r9 = r3
            r3 = r2
            r2 = r9
        L_0x0012:
            char r8 = r3[r4]
            int r7 = r6 % 5
            switch(r7) {
                case 0: goto L_0x003e;
                case 1: goto L_0x0041;
                case 2: goto L_0x0043;
                case 3: goto L_0x0046;
                default: goto L_0x0019;
            }
        L_0x0019:
            r7 = 60
        L_0x001b:
            r7 = r7 ^ r8
            char r7 = (char) r7
            r3[r4] = r7
            int r4 = r6 + 1
            if (r2 != 0) goto L_0x0027
            r3 = r5
            r6 = r4
            r4 = r2
            goto L_0x0012
        L_0x0027:
            r3 = r2
            r2 = r5
        L_0x0029:
            if (r3 > r4) goto L_0x000d
            java.lang.String r3 = new java.lang.String
            r3.<init>(r2)
            java.lang.String r2 = r3.intern()
            switch(r0) {
                case 0: goto L_0x0049;
                default: goto L_0x0037;
            }
        L_0x0037:
            cn.jpush.android.service.PushProtocol.z = r2
            java.lang.String r0 = "8u;\u0006T`4w"
            r2 = r0
            r0 = r1
            goto L_0x0004
        L_0x003e:
            r7 = 82
            goto L_0x001b
        L_0x0041:
            r7 = 5
            goto L_0x001b
        L_0x0043:
            r7 = 78
            goto L_0x001b
        L_0x0046:
            r7 = 117(0x75, float:1.64E-43)
            goto L_0x001b
        L_0x0049:
            java.lang.System.loadLibrary(r2)     // Catch: Throwable -> 0x004d
        L_0x004c:
            return
        L_0x004d:
            r0 = move-exception
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r2 = cn.jpush.android.service.PushProtocol.z
            r1.<init>(r2)
            r1.append(r0)
            cn.jpush.android.util.ac.e()
            r0.printStackTrace()
            goto L_0x004c
        L_0x005f:
            r4 = r1
            goto L_0x0029
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.jpush.android.service.PushProtocol.<clinit>():void");
    }

    public static native synchronized int Close(long j);

    public static native int CtrlResponse(long j, int i, long j2, long j3, long j4, int i2);

    public static native int GetSdkVersion();

    public static native int HbJPush(long j, long j2, int i, long j3, short s);

    public static native int IMProtocol(long j, byte[] bArr, int i);

    public static native synchronized long InitConn();

    public static native int InitPush(long j, String str, int i);

    public static native int LogPush(long j, long j2, byte[] bArr, long j3, String str, String str2, long j4, short s);

    public static native int MsgResponse(long j, int i, long j2, byte b, long j3, long j4, int i2);

    public static native int RecvPush(long j, byte[] bArr, int i);

    public static native int RegPush(long j, long j2, String str, String str2, String str3, String str4);

    public static native int Stop(long j);

    public static native int TagAlias(long j, long j2, int i, long j3, String str, String str2);
}
