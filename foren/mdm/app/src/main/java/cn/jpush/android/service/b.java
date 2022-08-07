package cn.jpush.android.service;

import android.content.Context;
import android.os.Bundle;
import cn.jpush.android.data.c;
import cn.jpush.android.g;
import cn.jpush.android.util.ac;
import cn.jpush.android.util.s;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/* loaded from: classes.dex */
public final class b {
    public static boolean b;
    private static final String[] z;
    public boolean a;
    private c c;
    private long d = 0;
    private long e = 0;
    private Bundle f;

    /* JADX WARN: Code restructure failed: missing block: B:10:0x0027, code lost:
        if (r5 != 0) goto L_0x002d;
     */
    /* JADX WARN: Code restructure failed: missing block: B:11:0x0029, code lost:
        r5 = r1;
        r8 = r6;
        r6 = r5;
     */
    /* JADX WARN: Code restructure failed: missing block: B:12:0x002d, code lost:
        r5 = r5;
        r1 = r1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:13:0x002f, code lost:
        if (r5 > r6) goto L_0x0013;
     */
    /* JADX WARN: Code restructure failed: missing block: B:14:0x0031, code lost:
        r1 = new java.lang.String(r1).intern();
     */
    /* JADX WARN: Code restructure failed: missing block: B:15:0x003a, code lost:
        switch(r0) {
            case 0: goto L_0x0045;
            case 1: goto L_0x004d;
            case 2: goto L_0x0055;
            case 3: goto L_0x005d;
            case 4: goto L_0x0065;
            case 5: goto L_0x006d;
            case 6: goto L_0x0075;
            case 7: goto L_0x007e;
            case 8: goto L_0x0088;
            case 9: goto L_0x0093;
            case 10: goto L_0x009e;
            case 11: goto L_0x00a9;
            case 12: goto L_0x00b5;
            case 13: goto L_0x00c0;
            case 14: goto L_0x00cc;
            case 15: goto L_0x00d8;
            case 16: goto L_0x00e3;
            case 17: goto L_0x00ee;
            case 18: goto L_0x00fa;
            case 19: goto L_0x0105;
            case 20: goto L_0x0111;
            default: goto L_0x003d;
        };
     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x003d, code lost:
        r3[r2] = r1;
        r2 = 1;
        r1 = "\u0016MoR";
        r0 = 0;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x0045, code lost:
        r3[r2] = r1;
        r2 = 2;
        r1 = "lDz\u00195]_pL5[I?](]_?W(L\fzA.KX?\u0014g";
        r0 = 1;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x004d, code lost:
        r3[r2] = r1;
        r2 = 3;
        r1 = "YOkP(V\u0016{V0V@pX#\u0018\u0001?L5T\u0016";
        r0 = 2;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:19:0x0055, code lost:
        r3[r2] = r1;
        r2 = 4;
        r1 = "\u0014\fyP+]b~T\"\u0002";
        r0 = 3;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x005d, code lost:
        r3[r2] = r1;
        r2 = 5;
        r1 = "\u0014\fyP+]xpM&T`zW LD%";
        r0 = 4;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x0065, code lost:
        r3[r2] = r1;
        r2 = 6;
        r1 = "\u0018JvU\"vMr\\}";
        r0 = 5;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x006d, code lost:
        r3[r2] = r1;
        r2 = 7;
        r1 = "hMmX*\u0018ImK(J\f>\u0018gM^s\u0003";
        r0 = 6;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x0075, code lost:
        r3[r2] = r1;
        r2 = '\b';
        r1 = "\u0014\flX1]jvU\"hMkQ}";
        r0 = 7;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x007e, code lost:
        r3[r2] = r1;
        r2 = '\t';
        r1 = "\u0018_~O\"^Es\\\u0017YXw\u0003";
        r0 = '\b';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x0088, code lost:
        r3[r2] = r1;
        r2 = '\n';
        r1 = "VIkN(JG?Z(VBzZ3\u0018_kX3M_?Z(\\I?L)]To\\$LI{\u0019j\u0018";
        r0 = '\t';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x0093, code lost:
        r3[r2] = r1;
        r2 = 11;
        r1 = "KX~K3hClM.WB%\u0019";
        r0 = '\n';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x009e, code lost:
        r3[r2] = r1;
        r2 = '\f';
        r1 = "KXpIg\\ChW+WM{\u0019%A\fjJ\"J\u0002";
        r0 = 11;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x00a9, code lost:
        r3[r2] = r1;
        r2 = '\r';
        r1 = "{CqM\"VX2u\"VKkQ";
        r0 = '\f';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:29:0x00b5, code lost:
        r3[r2] = r1;
        r2 = 14;
        r1 = "_Ik\u00193PI?_.TI?M(LMs\u0019+]BxM/\u0018JmV*\u0018DkM7\u0018El\u0019w\u0016";
        r0 = '\r';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x00c0, code lost:
        r3[r2] = r1;
        r2 = 15;
        r1 = "|ChW+WM{\u0019&_MvWk\u0018Xm@gJIlMg\u0015\f";
        r0 = 14;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:31:0x00cc, code lost:
        r3[r2] = r1;
        r2 = 16;
        r1 = "{CqW\"[X?M.UI?V2L\u0000?M5A\fm\\4L\f2\u0019";
        r0 = 15;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x00d8, code lost:
        r3[r2] = r1;
        r2 = 17;
        r1 = "UH*\u0019$PI|Rg]^mV5\u0014\fkK>\u0018MxX.V\f2\u0019";
        r0 = 16;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x00e3, code lost:
        r3[r2] = r1;
        r2 = 18;
        r1 = "jMq^\"";
        r0 = 17;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:34:0x00ee, code lost:
        r3[r2] = r1;
        r2 = 19;
        r1 = "{@pJ\"";
        r0 = 18;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:35:0x00fa, code lost:
        r3[r2] = r1;
        r2 = com.tencent.open.GameAppOperation.PIC_SYMBOLE;
        r1 = "ZUk\\4\u0005";
        r0 = 19;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:36:0x0105, code lost:
        r3[r2] = r1;
        r2 = 21;
        r1 = "{CqW\"[XvV)";
        r0 = com.tencent.open.GameAppOperation.PIC_SYMBOLE;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:37:0x0111, code lost:
        r3[r2] = r1;
        cn.jpush.android.service.b.z = r3;
        cn.jpush.android.service.b.b = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:38:0x0118, code lost:
        return;
     */
    /* JADX WARN: Code restructure failed: missing block: B:39:0x0119, code lost:
        r9 = '8';
     */
    /* JADX WARN: Code restructure failed: missing block: B:40:0x011d, code lost:
        r9 = ',';
     */
    /* JADX WARN: Code restructure failed: missing block: B:41:0x0121, code lost:
        r9 = 31;
     */
    /* JADX WARN: Code restructure failed: missing block: B:42:0x0125, code lost:
        r9 = '9';
     */
    /* JADX WARN: Code restructure failed: missing block: B:4:0x0011, code lost:
        if (r5 <= 1) goto L_0x0013;
     */
    /* JADX WARN: Code restructure failed: missing block: B:5:0x0013, code lost:
        r8 = r6;
        r5 = r1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:6:0x0018, code lost:
        r10 = r5[r6];
     */
    /* JADX WARN: Code restructure failed: missing block: B:7:0x001c, code lost:
        switch((r8 % 5)) {
            case 0: goto L_0x0119;
            case 1: goto L_0x011d;
            case 2: goto L_0x0121;
            case 3: goto L_0x0125;
            default: goto L_0x001f;
        };
     */
    /* JADX WARN: Code restructure failed: missing block: B:8:0x001f, code lost:
        r9 = 'G';
     */
    /* JADX WARN: Code restructure failed: missing block: B:9:0x0021, code lost:
        r5[r6] = (char) (r9 ^ r10);
        r6 = r8 + 1;
     */
    static {
        /*
            Method dump skipped, instructions count: 356
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.jpush.android.service.b.<clinit>():void");
    }

    public b(Context context, c cVar, Bundle bundle, d dVar, int i) {
        int i2 = 0;
        this.c = null;
        this.a = false;
        ac.a();
        this.a = false;
        this.f = bundle;
        this.c = new c(this, context.getMainLooper(), dVar);
        this.c.sendEmptyMessageDelayed(0, 2000L);
        while (b) {
            if (this.a) {
                ac.c();
                this.c.removeCallbacksAndMessages(null);
                dVar.a(1);
                return;
            } else if (cVar.z == 0) {
                ac.d();
                if (dVar != null) {
                    this.a = true;
                    DownloadService.a.remove(cVar);
                    this.c.removeCallbacksAndMessages(null);
                    dVar.a(2);
                    return;
                }
                return;
            } else if (i2 >= 3) {
                ac.d();
                if (dVar != null) {
                    this.a = true;
                    DownloadService.a.remove(cVar);
                    this.c.removeCallbacksAndMessages(null);
                    dVar.a(2);
                    return;
                }
                return;
            } else {
                int a = a(context, dVar, cVar);
                cVar.z--;
                if (a == -1) {
                    new StringBuilder(z[16]).append(cVar.z);
                    ac.b();
                    try {
                        Thread.sleep(3000L);
                    } catch (InterruptedException e) {
                    }
                } else if (a == 0) {
                    new StringBuilder(z[15]).append(cVar.z);
                    ac.b();
                    try {
                        Thread.sleep(3000L);
                    } catch (InterruptedException e2) {
                    }
                } else if (a == 1) {
                    ac.b();
                    this.c.removeCallbacksAndMessages(null);
                    this.a = true;
                    return;
                } else if (a == 2) {
                    new StringBuilder(z[17]).append(cVar.z);
                    ac.b();
                    i2++;
                } else if (a == -3) {
                    this.a = true;
                    DownloadService.a.remove(cVar);
                    this.c.removeCallbacksAndMessages(null);
                    dVar.a(3);
                    return;
                } else {
                    ac.b();
                    this.a = true;
                    DownloadService.a.remove(cVar);
                    this.c.removeCallbacksAndMessages(null);
                    dVar.a(2);
                    return;
                }
            }
        }
        ac.c();
        this.c.removeCallbacksAndMessages(null);
        this.a = true;
        dVar.a(1);
    }

    private static int a(long j) {
        long j2 = j / 10485760;
        return (int) ((j2 < 1 ? 10 : j2 > 5 ? 50 : (int) (j2 * 10)) * 1.1d);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:461:0x02dc A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Type inference failed for: r10v10, types: [java.io.BufferedInputStream] */
    /* JADX WARN: Type inference failed for: r10v11 */
    /* JADX WARN: Type inference failed for: r10v14 */
    /* JADX WARN: Type inference failed for: r10v29 */
    /* JADX WARN: Type inference failed for: r10v35 */
    /* JADX WARN: Type inference failed for: r10v62 */
    /* JADX WARN: Type inference failed for: r10v63 */
    /* JADX WARN: Type inference failed for: r10v64 */
    /* JADX WARN: Type inference failed for: r10v65 */
    /* JADX WARN: Type inference failed for: r10v66 */
    /* JADX WARN: Type inference failed for: r10v9 */
    /* JADX WARN: Type inference failed for: r13v15 */
    /* JADX WARN: Type inference failed for: r13v37 */
    /* JADX WARN: Type inference failed for: r13v55 */
    /* JADX WARN: Type inference failed for: r13v56 */
    /* JADX WARN: Type inference failed for: r13v71 */
    /* JADX WARN: Type inference failed for: r13v78 */
    /* JADX WARN: Type inference failed for: r13v88 */
    /* JADX WARN: Type inference failed for: r13v89 */
    /* JADX WARN: Type inference failed for: r13v90 */
    /* JADX WARN: Type inference failed for: r13v91 */
    /* JADX WARN: Type inference failed for: r13v92 */
    /* JADX WARN: Type inference failed for: r13v93 */
    /* JADX WARN: Type inference failed for: r19v2 */
    /* JADX WARN: Type inference failed for: r19v3, types: [java.net.HttpURLConnection] */
    /* JADX WARN: Type inference failed for: r19v4, types: [java.net.HttpURLConnection] */
    /* JADX WARN: Type inference failed for: r5v66, types: [int] */
    /* JADX WARN: Unknown variable types count: 2 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private int a(android.content.Context r22, cn.jpush.android.service.d r23, cn.jpush.android.data.c r24) {
        /*
            Method dump skipped, instructions count: 1933
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.jpush.android.service.b.a(android.content.Context, cn.jpush.android.service.d, cn.jpush.android.data.c):int");
    }

    private static long a(HttpURLConnection httpURLConnection) {
        long longValue = Long.valueOf(httpURLConnection.getHeaderField(z[13])).longValue();
        if (longValue > 0) {
            return longValue;
        }
        throw new g(z[14]);
    }

    private static HttpURLConnection a(String str, long j) {
        IOException e;
        HttpURLConnection httpURLConnection = null;
        try {
            httpURLConnection = (HttpURLConnection) new URL(str).openConnection();
        } catch (IOException e2) {
            e = e2;
        }
        try {
            httpURLConnection.setRequestProperty(z[21], z[19]);
            if (j >= 0) {
                httpURLConnection.setRequestProperty(z[18], z[20] + j + "-");
            }
            s.a(httpURLConnection, false);
        } catch (IOException e3) {
            e = e3;
            e.printStackTrace();
            return httpURLConnection;
        }
        return httpURLConnection;
    }

    private static void a(InputStream inputStream, BufferedInputStream bufferedInputStream, FileOutputStream fileOutputStream, BufferedOutputStream bufferedOutputStream, HttpURLConnection httpURLConnection) {
        if (bufferedOutputStream != null) {
            try {
                bufferedOutputStream.close();
            } catch (IOException e) {
            }
        }
        if (fileOutputStream != null) {
            try {
                fileOutputStream.close();
            } catch (IOException e2) {
            }
        }
        if (bufferedInputStream != null) {
            try {
                bufferedInputStream.close();
            } catch (IOException e3) {
            }
        }
        if (inputStream != null) {
            try {
                inputStream.close();
            } catch (IOException e4) {
            }
        }
        if (httpURLConnection != null) {
            httpURLConnection.disconnect();
        }
    }

    public static boolean a(int i) {
        return 2 == i || 3 == i;
    }
}
