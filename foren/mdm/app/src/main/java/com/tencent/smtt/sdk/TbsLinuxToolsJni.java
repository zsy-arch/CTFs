package com.tencent.smtt.sdk;

import android.content.Context;
import com.tencent.smtt.utils.TbsLog;
import java.io.File;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public class TbsLinuxToolsJni {
    private static boolean a = false;
    private static boolean b = false;

    public TbsLinuxToolsJni(Context context) {
        a(context);
    }

    private native int ChmodInner(String str, String str2);

    private void a(Context context) {
        synchronized (TbsLinuxToolsJni.class) {
            if (!b) {
                b = true;
                File file = TbsShareManager.isThirdPartyApp(context) ? new File(TbsShareManager.b(context)) : aj.a().h(context);
                if (file != null) {
                    System.load(file.getAbsolutePath() + File.separator + "liblinuxtoolsfortbssdk_jni.so");
                    a = true;
                }
                ChmodInner("/checkChmodeExists", "700");
            }
        }
    }

    public int a(String str, String str2) {
        if (a) {
            return ChmodInner(str, str2);
        }
        TbsLog.e("TbsLinuxToolsJni", "jni not loaded!", true);
        return -1;
    }
}
