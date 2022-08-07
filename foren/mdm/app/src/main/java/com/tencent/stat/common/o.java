package com.tencent.stat.common;

import java.io.File;

/* loaded from: classes2.dex */
class o {
    private static int a = -1;

    public static boolean a() {
        if (a == 1) {
            return true;
        }
        if (a == 0) {
            return false;
        }
        String[] strArr = {"/bin", "/system/bin/", "/system/xbin/", "/system/sbin/", "/sbin/", "/vendor/bin/"};
        for (int i = 0; i < strArr.length; i++) {
            try {
                File file = new File(strArr[i] + "su");
                if (file != null && file.exists()) {
                    a = 1;
                    return true;
                }
            } catch (Exception e) {
            }
        }
        a = 0;
        return false;
    }
}
