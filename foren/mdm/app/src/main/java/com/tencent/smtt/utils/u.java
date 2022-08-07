package com.tencent.smtt.utils;

import android.content.Context;
import android.provider.Settings;
import android.text.TextUtils;
import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;

/* loaded from: classes2.dex */
public class u {
    private static u a = null;
    private static Context b = null;

    private u() {
    }

    public static u a(Context context) {
        b = context.getApplicationContext();
        if (a == null) {
            a = new u();
        }
        return a;
    }

    private void b(String str) {
        Throwable th;
        Exception e;
        DataOutputStream dataOutputStream;
        if (k.b()) {
            DataOutputStream dataOutputStream2 = null;
            try {
                try {
                    File file = new File(k.c(), "ins.dat");
                    if (!file.exists()) {
                        file.createNewFile();
                    }
                    dataOutputStream = new DataOutputStream(new BufferedOutputStream(k.d(file)));
                } catch (Exception e2) {
                    e = e2;
                }
            } catch (Throwable th2) {
                th = th2;
            }
            try {
                dataOutputStream.writeUTF(str);
                dataOutputStream.flush();
                if (dataOutputStream != null) {
                    try {
                        dataOutputStream.close();
                    } catch (IOException e3) {
                        e = e3;
                        e.printStackTrace();
                    }
                }
            } catch (Exception e4) {
                e = e4;
                dataOutputStream2 = dataOutputStream;
                e.printStackTrace();
                if (dataOutputStream2 != null) {
                    try {
                        dataOutputStream2.close();
                    } catch (IOException e5) {
                        e = e5;
                        e.printStackTrace();
                    }
                }
            } catch (Throwable th3) {
                th = th3;
                dataOutputStream2 = dataOutputStream;
                if (dataOutputStream2 != null) {
                    try {
                        dataOutputStream2.close();
                    } catch (IOException e6) {
                        e6.printStackTrace();
                    }
                }
                throw th;
            }
        }
    }

    private void c(String str) {
        try {
            Settings.System.putString(b.getContentResolver(), "sys_setting_tbs_qb_installer", str);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void a(String str) {
        if (!TextUtils.isEmpty(str)) {
            b(str);
            c(str);
        }
    }
}
