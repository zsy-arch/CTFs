package com.alipay.sdk.sys;

import android.content.Context;
import android.text.TextUtils;
import com.alipay.sdk.app.statistic.a;
import com.alipay.sdk.data.c;
import com.ta.utdid2.device.UTDevice;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;

/* loaded from: classes.dex */
public final class b {
    private static b b;
    public Context a;

    private b() {
    }

    public static b a() {
        if (b == null) {
            b = new b();
        }
        return b;
    }

    private Context d() {
        return this.a;
    }

    public final void a(Context context) {
        this.a = context.getApplicationContext();
    }

    private static c e() {
        return c.a();
    }

    public static boolean b() {
        String[] strArr = {"/system/xbin/", "/system/bin/", "/system/sbin/", "/sbin/", "/vendor/bin/"};
        for (int i = 0; i < 5; i++) {
            try {
                String str = strArr[i] + "su";
                if (new File(str).exists()) {
                    String a = a(new String[]{"ls", "-l", str});
                    if (!TextUtils.isEmpty(a)) {
                        if (a.indexOf("root") != a.lastIndexOf("root")) {
                            return true;
                        }
                    }
                    return false;
                }
            } catch (Exception e) {
            }
        }
        return false;
    }

    private static String a(String[] strArr) {
        Process process;
        Throwable th;
        String str;
        DataOutputStream dataOutputStream;
        try {
            ProcessBuilder processBuilder = new ProcessBuilder(strArr);
            processBuilder.redirectErrorStream(false);
            process = processBuilder.start();
            try {
                try {
                    dataOutputStream = new DataOutputStream(process.getOutputStream());
                    str = new DataInputStream(process.getInputStream()).readLine();
                } catch (Throwable th2) {
                    str = "";
                }
                try {
                    dataOutputStream.writeBytes("exit\n");
                    dataOutputStream.flush();
                    process.waitFor();
                    try {
                        process.destroy();
                    } catch (Exception e) {
                    }
                } catch (Throwable th3) {
                    try {
                        process.destroy();
                    } catch (Exception e2) {
                    }
                    return str;
                }
            } catch (Throwable th4) {
                th = th4;
                try {
                    process.destroy();
                } catch (Exception e3) {
                }
                throw th;
            }
        } catch (Throwable th5) {
            process = null;
            th = th5;
        }
        return str;
    }

    public final String c() {
        try {
            return UTDevice.getUtdid(this.a);
        } catch (Throwable th) {
            a.a(com.alipay.sdk.app.statistic.c.e, com.alipay.sdk.app.statistic.c.j, th);
            return "";
        }
    }
}
