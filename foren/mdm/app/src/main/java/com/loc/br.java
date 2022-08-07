package com.loc;

import android.content.Context;
import android.text.TextUtils;

/* compiled from: StatisticsEntity.java */
/* loaded from: classes2.dex */
public final class br {
    private Context a;
    private String b;
    private String c;
    private String d;
    private String e;

    public br(Context context, String str, String str2, String str3) throws j {
        if (TextUtils.isEmpty(str3) || str3.length() > 256) {
            throw new j("无效的参数 - IllegalArgumentException");
        }
        this.a = context.getApplicationContext();
        this.c = str;
        this.d = str2;
        this.b = str3;
    }

    public final void a(String str) throws j {
        if (TextUtils.isEmpty(str) || str.length() > 65536) {
            throw new j("无效的参数 - IllegalArgumentException");
        }
        this.e = str;
    }

    /* JADX WARN: Removed duplicated region for block: B:33:0x00b1 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final byte[] a() {
        /*
            r6 = this;
            r0 = 0
            r3 = 0
            byte[] r0 = new byte[r0]
            java.io.ByteArrayOutputStream r2 = new java.io.ByteArrayOutputStream     // Catch: Throwable -> 0x00be, all -> 0x00ad
            r2.<init>()     // Catch: Throwable -> 0x00be, all -> 0x00ad
            java.lang.String r1 = r6.c     // Catch: Throwable -> 0x009a, all -> 0x00bc
            com.loc.t.a(r2, r1)     // Catch: Throwable -> 0x009a, all -> 0x00bc
            java.lang.String r1 = r6.d     // Catch: Throwable -> 0x009a, all -> 0x00bc
            com.loc.t.a(r2, r1)     // Catch: Throwable -> 0x009a, all -> 0x00bc
            java.lang.String r1 = r6.b     // Catch: Throwable -> 0x009a, all -> 0x00bc
            com.loc.t.a(r2, r1)     // Catch: Throwable -> 0x009a, all -> 0x00bc
            android.content.Context r1 = r6.a     // Catch: Throwable -> 0x009a, all -> 0x00bc
            int r1 = com.loc.n.m(r1)     // Catch: Throwable -> 0x009a, all -> 0x00bc
            java.lang.String r1 = java.lang.String.valueOf(r1)     // Catch: Throwable -> 0x009a, all -> 0x00bc
            com.loc.t.a(r2, r1)     // Catch: Throwable -> 0x009a, all -> 0x00bc
            java.text.SimpleDateFormat r1 = new java.text.SimpleDateFormat     // Catch: Throwable -> 0x009a, all -> 0x00bc
            java.lang.String r3 = "SSS"
            r1.<init>(r3)     // Catch: Throwable -> 0x009a, all -> 0x00bc
            java.util.Date r3 = new java.util.Date     // Catch: Throwable -> 0x009a, all -> 0x00bc
            r3.<init>()     // Catch: Throwable -> 0x009a, all -> 0x00bc
            r1.format(r3)     // Catch: Throwable -> 0x009a, all -> 0x00bc
            java.util.Calendar r1 = java.util.Calendar.getInstance()     // Catch: Throwable -> 0x009a, all -> 0x00bc
            r3 = 14
            int r1 = r1.get(r3)     // Catch: Throwable -> 0x009a, all -> 0x00bc
            r3 = 4
            byte[] r3 = new byte[r3]     // Catch: Throwable -> 0x009a, all -> 0x00bc
            r4 = 0
            int r5 = r1 >> 24
            r5 = r5 & 255(0xff, float:3.57E-43)
            byte r5 = (byte) r5     // Catch: Throwable -> 0x009a, all -> 0x00bc
            r3[r4] = r5     // Catch: Throwable -> 0x009a, all -> 0x00bc
            r4 = 1
            int r5 = r1 >> 16
            r5 = r5 & 255(0xff, float:3.57E-43)
            byte r5 = (byte) r5     // Catch: Throwable -> 0x009a, all -> 0x00bc
            r3[r4] = r5     // Catch: Throwable -> 0x009a, all -> 0x00bc
            r4 = 2
            int r5 = r1 >> 8
            r5 = r5 & 255(0xff, float:3.57E-43)
            byte r5 = (byte) r5     // Catch: Throwable -> 0x009a, all -> 0x00bc
            r3[r4] = r5     // Catch: Throwable -> 0x009a, all -> 0x00bc
            r4 = 3
            r1 = r1 & 255(0xff, float:3.57E-43)
            byte r1 = (byte) r1     // Catch: Throwable -> 0x009a, all -> 0x00bc
            r3[r4] = r1     // Catch: Throwable -> 0x009a, all -> 0x00bc
            r2.write(r3)     // Catch: Throwable -> 0x009a, all -> 0x00bc
            java.lang.String r1 = r6.e     // Catch: Throwable -> 0x009a, all -> 0x00bc
            boolean r3 = android.text.TextUtils.isEmpty(r1)     // Catch: Throwable -> 0x009a, all -> 0x00bc
            if (r3 == 0) goto L_0x0086
            r1 = 2
            byte[] r1 = new byte[r1]     // Catch: Throwable -> 0x009a, all -> 0x00bc
            r1 = {x00c2: FILL_ARRAY_DATA  , data: [0, 0} // fill-array     // Catch: Throwable -> 0x009a, all -> 0x00bc
        L_0x0070:
            r2.write(r1)     // Catch: Throwable -> 0x009a, all -> 0x00bc
            java.lang.String r1 = r6.e     // Catch: Throwable -> 0x009a, all -> 0x00bc
            byte[] r1 = com.loc.t.a(r1)     // Catch: Throwable -> 0x009a, all -> 0x00bc
            r2.write(r1)     // Catch: Throwable -> 0x009a, all -> 0x00bc
            byte[] r0 = r2.toByteArray()     // Catch: Throwable -> 0x009a, all -> 0x00bc
            if (r2 == 0) goto L_0x0085
            r2.close()     // Catch: Throwable -> 0x00ba
        L_0x0085:
            return r0
        L_0x0086:
            int r1 = r1.length()     // Catch: Throwable -> 0x009a, all -> 0x00bc
            int r3 = r1 / 256
            byte r3 = (byte) r3     // Catch: Throwable -> 0x009a, all -> 0x00bc
            int r1 = r1 % 256
            byte r4 = (byte) r1     // Catch: Throwable -> 0x009a, all -> 0x00bc
            r1 = 2
            byte[] r1 = new byte[r1]     // Catch: Throwable -> 0x009a, all -> 0x00bc
            r5 = 0
            r1[r5] = r3     // Catch: Throwable -> 0x009a, all -> 0x00bc
            r3 = 1
            r1[r3] = r4     // Catch: Throwable -> 0x009a, all -> 0x00bc
            goto L_0x0070
        L_0x009a:
            r1 = move-exception
        L_0x009b:
            java.lang.String r3 = "StatisticsEntity"
            java.lang.String r4 = "toDatas"
            com.loc.w.a(r1, r3, r4)     // Catch: all -> 0x00bc
            if (r2 == 0) goto L_0x0085
            r2.close()     // Catch: Throwable -> 0x00a8
            goto L_0x0085
        L_0x00a8:
            r1 = move-exception
        L_0x00a9:
            r1.printStackTrace()
            goto L_0x0085
        L_0x00ad:
            r0 = move-exception
            r2 = r3
        L_0x00af:
            if (r2 == 0) goto L_0x00b4
            r2.close()     // Catch: Throwable -> 0x00b5
        L_0x00b4:
            throw r0
        L_0x00b5:
            r1 = move-exception
            r1.printStackTrace()
            goto L_0x00b4
        L_0x00ba:
            r1 = move-exception
            goto L_0x00a9
        L_0x00bc:
            r0 = move-exception
            goto L_0x00af
        L_0x00be:
            r1 = move-exception
            r2 = r3
            goto L_0x009b
        */
        throw new UnsupportedOperationException("Method not decompiled: com.loc.br.a():byte[]");
    }
}
