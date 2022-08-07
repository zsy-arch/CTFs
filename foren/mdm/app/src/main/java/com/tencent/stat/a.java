package com.tencent.stat;

import android.content.Context;
import android.os.Environment;
import android.provider.Settings;
import com.tencent.stat.common.StatLogger;
import com.tencent.stat.common.d;
import com.tencent.stat.common.k;
import com.tencent.stat.common.p;
import com.umeng.update.UpdateConfig;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;

/* loaded from: classes2.dex */
public class a {
    private static a b = null;
    private StatLogger a = k.b();
    private boolean c;
    private boolean d;
    private boolean e;
    private Context f;

    private a(Context context) {
        this.c = false;
        this.d = false;
        this.e = false;
        this.f = null;
        this.f = context.getApplicationContext();
        this.c = b(context);
        this.d = d(context);
        this.e = c(context);
    }

    public static synchronized a a(Context context) {
        a aVar;
        synchronized (a.class) {
            if (b == null) {
                b = new a(context);
            }
            aVar = b;
        }
        return aVar;
    }

    private boolean b(Context context) {
        if (k.a(context, UpdateConfig.f)) {
            return true;
        }
        this.a.e("Check permission failed: android.permission.WRITE_EXTERNAL_STORAGE");
        return false;
    }

    private boolean c(Context context) {
        if (k.a(context, "android.permission.WRITE_SETTINGS")) {
            return true;
        }
        this.a.e("Check permission failed: android.permission.WRITE_SETTINGS");
        return false;
    }

    private boolean d(Context context) {
        if (k.d() < 14) {
            return b(context);
        }
        return true;
    }

    public boolean a(String str, String str2) {
        p.b(this.f, str, str2);
        return true;
    }

    public String b(String str, String str2) {
        return p.a(this.f, str, str2);
    }

    public boolean c(String str, String str2) {
        if (!this.c) {
            return false;
        }
        try {
            d.a(Environment.getExternalStorageDirectory() + "/Tencent/mta");
            File file = new File(Environment.getExternalStorageDirectory(), "Tencent/mta/.mid.txt");
            if (file != null) {
                BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));
                bufferedWriter.write(str + "," + str2);
                bufferedWriter.write("\n");
                bufferedWriter.close();
            }
            return true;
        } catch (Throwable th) {
            this.a.w(th);
            return false;
        }
    }

    public String d(String str, String str2) {
        if (!this.c) {
            return null;
        }
        try {
            File file = new File(Environment.getExternalStorageDirectory(), "Tencent/mta/.mid.txt");
            if (file != null) {
                for (String str3 : d.a(file)) {
                    String[] split = str3.split(",");
                    if (split.length == 2 && split[0].equals(str)) {
                        return split[1];
                    }
                }
            }
        } catch (FileNotFoundException e) {
            this.a.w("Tencent/mta/.mid.txt not found.");
        } catch (Throwable th) {
            this.a.w(th);
        }
        return null;
    }

    public boolean e(String str, String str2) {
        if (!this.e) {
            return false;
        }
        Settings.System.putString(this.f.getContentResolver(), str, str2);
        return true;
    }

    public String f(String str, String str2) {
        return !this.e ? str2 : Settings.System.getString(this.f.getContentResolver(), str);
    }
}
