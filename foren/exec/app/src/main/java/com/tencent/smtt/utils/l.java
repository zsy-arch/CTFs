package com.tencent.smtt.utils;

import android.content.Context;
import android.os.Build;
import android.os.Looper;
import android.text.TextUtils;
import com.tencent.smtt.sdk.BuildConfig;
import com.tencent.smtt.sdk.QbSdk;
import e.a.a.a.a;
import java.io.File;
import java.io.FileFilter;

/* loaded from: classes.dex */
public class l {
    public static boolean a(Context context) {
        if (Looper.getMainLooper() != Looper.myLooper()) {
            return b(context);
        }
        return true;
    }

    public static boolean a(File file) {
        try {
            return !e.b(file);
        } catch (Throwable th) {
            a.b("isOatFileBroken exception: ", th);
            return false;
        }
    }

    public static boolean b(Context context) {
        File c2;
        try {
            int i = Build.VERSION.SDK_INT;
        } catch (Throwable th) {
            th.printStackTrace();
        }
        if (Build.VERSION.SDK_INT > 25 || (c2 = c(context)) == null) {
            return true;
        }
        File[] listFiles = c2.listFiles(new FileFilter() { // from class: com.tencent.smtt.utils.l.1
            @Override // java.io.FileFilter
            public boolean accept(File file) {
                String name = file.getName();
                return !TextUtils.isEmpty(name) && name.endsWith(".dex");
            }
        });
        for (File file : listFiles) {
            if (file.isFile() && file.exists()) {
                if (a(file)) {
                    TbsLog.w("TbsCheckUtils", BuildConfig.FLAVOR + file + " is invalid --> check failed!");
                    file.delete();
                    return false;
                }
                TbsLog.i("TbsCheckUtils", BuildConfig.FLAVOR + file + " #4 check success!");
            }
        }
        TbsLog.i("TbsCheckUtils", "checkTbsValidity -->#5 check ok!");
        return true;
    }

    public static File c(Context context) {
        File file = new File(QbSdk.getTbsFolderDir(context), "core_share");
        if (!file.isDirectory() || !file.exists()) {
            return null;
        }
        return file;
    }
}
