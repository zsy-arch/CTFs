package com.tencent.smtt.sdk;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

/* loaded from: classes.dex */
public class d {

    /* renamed from: a */
    public static int f1334a = 0;

    /* renamed from: b */
    public static boolean f1335b = false;

    /* renamed from: e */
    public static d f1336e = null;
    public static int h = 0;
    public static int i = 3;
    public static String k;

    /* renamed from: c */
    public s f1337c = null;

    /* renamed from: d */
    public s f1338d = null;
    public boolean f = false;
    public boolean g = false;
    public File j = null;

    public static d a(boolean z) {
        if (f1336e == null && z) {
            synchronized (d.class) {
                if (f1336e == null) {
                    f1336e = new d();
                }
            }
        }
        return f1336e;
    }

    public static void a(int i2) {
        h = i2;
    }

    private void b(int i2) {
        String valueOf = String.valueOf(i2);
        Properties properties = new Properties();
        properties.setProperty(k, valueOf);
        try {
            properties.store(new FileOutputStream(new File(this.j, "count.prop")), (String) null);
        } catch (FileNotFoundException e2) {
            e2.printStackTrace();
        } catch (IOException e3) {
            e3.printStackTrace();
        }
    }

    public static int d() {
        return h;
    }

    private int i() {
        Throwable th;
        Exception e2;
        try {
            BufferedInputStream bufferedInputStream = null;
            try {
                File file = new File(this.j, "count.prop");
                if (!file.exists()) {
                    return 0;
                }
                BufferedInputStream bufferedInputStream2 = new BufferedInputStream(new FileInputStream(file));
                try {
                    Properties properties = new Properties();
                    properties.load(bufferedInputStream2);
                    int intValue = Integer.valueOf(properties.getProperty(k, "1")).intValue();
                    try {
                        bufferedInputStream2.close();
                    } catch (IOException e3) {
                        e3.printStackTrace();
                    }
                    return intValue;
                } catch (Exception e4) {
                    e2 = e4;
                    bufferedInputStream = bufferedInputStream2;
                    e2.printStackTrace();
                    if (bufferedInputStream != null) {
                        try {
                            bufferedInputStream.close();
                        } catch (IOException e5) {
                            e5.printStackTrace();
                        }
                    }
                    return 0;
                } catch (Throwable th2) {
                    th = th2;
                    if (bufferedInputStream2 != null) {
                        try {
                            bufferedInputStream2.close();
                        } catch (IOException e6) {
                            e6.printStackTrace();
                        }
                    }
                    throw th;
                }
            } catch (Exception e7) {
                e2 = e7;
            }
        } catch (Throwable th3) {
            th = th3;
        }
    }

    public s a() {
        if (this.f) {
            return this.f1337c;
        }
        return null;
    }

    /* JADX WARN: Removed duplicated region for block: B:41:0x00f6 A[Catch: Throwable -> 0x0163, all -> 0x01ec, TryCatch #1 {, blocks: (B:4:0x0004, B:8:0x0043, B:14:0x005f, B:16:0x0092, B:20:0x0098, B:22:0x009e, B:24:0x00ab, B:26:0x00c3, B:29:0x00cc, B:33:0x00d7, B:35:0x00ea, B:41:0x00f6, B:44:0x00ff, B:49:0x010a, B:50:0x0113, B:52:0x0116, B:53:0x0119, B:55:0x011d, B:56:0x0120, B:57:0x0124, B:59:0x013f, B:60:0x0150, B:61:0x015f, B:62:0x019a, B:64:0x01cd, B:67:0x01d2, B:68:0x01e2), top: B:73:0x0004 }] */
    /* JADX WARN: Removed duplicated region for block: B:42:0x00fc  */
    /* JADX WARN: Removed duplicated region for block: B:44:0x00ff A[Catch: Throwable -> 0x0163, all -> 0x01ec, TRY_LEAVE, TryCatch #1 {, blocks: (B:4:0x0004, B:8:0x0043, B:14:0x005f, B:16:0x0092, B:20:0x0098, B:22:0x009e, B:24:0x00ab, B:26:0x00c3, B:29:0x00cc, B:33:0x00d7, B:35:0x00ea, B:41:0x00f6, B:44:0x00ff, B:49:0x010a, B:50:0x0113, B:52:0x0116, B:53:0x0119, B:55:0x011d, B:56:0x0120, B:57:0x0124, B:59:0x013f, B:60:0x0150, B:61:0x015f, B:62:0x019a, B:64:0x01cd, B:67:0x01d2, B:68:0x01e2), top: B:73:0x0004 }] */
    /* JADX WARN: Removed duplicated region for block: B:47:0x0108  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public synchronized void a(android.content.Context r11, boolean r12, boolean r13) {
        /*
            Method dump skipped, instructions count: 495
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.sdk.d.a(android.content.Context, boolean, boolean):void");
    }

    public void a(String str) {
        k = str;
    }

    public boolean b() {
        return this.f;
    }

    public boolean b(boolean z) {
        f1335b = z;
        return z;
    }

    public s c() {
        return this.f1337c;
    }

    public String e() {
        s sVar = this.f1337c;
        return (sVar == null || QbSdk.f1127a) ? "system webview get nothing..." : sVar.a();
    }

    public boolean f() {
        if (f1335b) {
            if (k == null) {
                return false;
            }
            int i2 = i();
            if (i2 == 0) {
                b(1);
            } else {
                int i3 = i2 + 1;
                if (i3 > i) {
                    return false;
                }
                b(i3);
            }
        }
        return f1335b;
    }

    public boolean g() {
        return this.g;
    }

    public boolean h() {
        return QbSdk.useSoftWare();
    }
}
