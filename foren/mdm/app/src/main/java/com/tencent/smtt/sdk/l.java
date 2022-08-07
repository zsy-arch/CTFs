package com.tencent.smtt.sdk;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

/* loaded from: classes2.dex */
public class l {
    private ay b = null;
    private boolean d = false;
    private boolean e = false;
    private File i = null;
    private static l c = null;
    private static int f = 0;
    private static int g = 0;
    static boolean a = false;
    private static int h = 3;
    private static String j = null;

    private l() {
    }

    public static l a(boolean z) {
        if (c == null && z) {
            synchronized (l.class) {
                if (c == null) {
                    c = new l();
                }
            }
        }
        return c;
    }

    public static void a(int i) {
        f = i;
    }

    private void b(int i) {
        String valueOf = String.valueOf(i);
        Properties properties = new Properties();
        properties.setProperty(j, valueOf);
        try {
            properties.store(new FileOutputStream(new File(this.i, "count.prop")), (String) null);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e2) {
            e2.printStackTrace();
        }
    }

    public static int d() {
        return f;
    }

    private int i() {
        BufferedInputStream bufferedInputStream;
        int i;
        BufferedInputStream bufferedInputStream2;
        Exception e;
        File file;
        try {
            i = 0;
            bufferedInputStream2 = null;
        } catch (Throwable th) {
            th = th;
        }
        try {
            file = new File(this.i, "count.prop");
        } catch (Exception e2) {
            e = e2;
            bufferedInputStream = null;
        } catch (Throwable th2) {
            th = th2;
            bufferedInputStream = null;
            if (bufferedInputStream != null) {
                try {
                    bufferedInputStream.close();
                } catch (IOException e3) {
                    e3.printStackTrace();
                }
            }
            throw th;
        }
        if (!file.exists()) {
            if (0 != 0) {
                try {
                    bufferedInputStream2.close();
                } catch (IOException e4) {
                    e4.printStackTrace();
                }
            }
            return i;
        }
        bufferedInputStream = new BufferedInputStream(new FileInputStream(file));
        try {
            Properties properties = new Properties();
            properties.load(bufferedInputStream);
            i = Integer.valueOf(properties.getProperty(j, "1")).intValue();
            if (bufferedInputStream != null) {
                try {
                    bufferedInputStream.close();
                } catch (IOException e5) {
                    e5.printStackTrace();
                }
            }
        } catch (Exception e6) {
            e = e6;
            e.printStackTrace();
            if (bufferedInputStream != null) {
                try {
                    bufferedInputStream.close();
                } catch (IOException e7) {
                    e7.printStackTrace();
                }
            }
            return i;
        }
        return i;
    }

    public ay a() {
        if (this.d) {
            return this.b;
        }
        return null;
    }

    /* JADX WARN: Removed duplicated region for block: B:72:0x014d A[Catch: Throwable -> 0x00b9, all -> 0x0101, LOOP:0: B:70:0x014a->B:72:0x014d, LOOP_END, TryCatch #2 {, blocks: (B:5:0x0007, B:7:0x0017, B:10:0x001c, B:11:0x001e, B:13:0x0027, B:14:0x002d, B:17:0x0036, B:19:0x003b, B:20:0x0041, B:22:0x0047, B:23:0x004d, B:25:0x0053, B:26:0x0059, B:33:0x0066, B:41:0x0074, B:43:0x007a, B:45:0x0085, B:46:0x008b, B:48:0x0091, B:50:0x0099, B:52:0x00b0, B:53:0x00f6, B:57:0x0105, B:58:0x010e, B:60:0x0125, B:63:0x012d, B:65:0x0133, B:69:0x0142, B:70:0x014a, B:72:0x014d, B:73:0x0150, B:74:0x0172, B:76:0x01ab, B:78:0x01af), top: B:79:0x0007 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public synchronized void a(android.content.Context r9, boolean r10, boolean r11, com.tencent.smtt.sdk.ai r12) {
        /*
            Method dump skipped, instructions count: 450
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.sdk.l.a(android.content.Context, boolean, boolean, com.tencent.smtt.sdk.ai):void");
    }

    public void a(String str) {
        j = str;
    }

    public boolean b() {
        return this.d;
    }

    public boolean b(boolean z) {
        a = z;
        return z;
    }

    public ay c() {
        return this.b;
    }

    public String e() {
        return (this.b == null || QbSdk.a) ? "system webview get nothing..." : this.b.a();
    }

    public boolean f() {
        if (a) {
            if (j == null) {
                return false;
            }
            int i = i();
            if (i == 0) {
                b(1);
            } else if (i + 1 > h) {
                return false;
            } else {
                b(i + 1);
            }
        }
        return a;
    }

    public boolean g() {
        return this.e;
    }

    public boolean h() {
        return QbSdk.useSoftWare();
    }
}
