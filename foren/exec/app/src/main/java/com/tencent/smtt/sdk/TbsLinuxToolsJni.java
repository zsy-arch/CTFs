package com.tencent.smtt.sdk;

import android.content.Context;
import com.tencent.smtt.utils.TbsLog;
import java.io.File;

/* loaded from: classes.dex */
public class TbsLinuxToolsJni {

    /* renamed from: a  reason: collision with root package name */
    public static boolean f1227a;

    /* renamed from: b  reason: collision with root package name */
    public static boolean f1228b;

    public TbsLinuxToolsJni(Context context) {
        a(context);
    }

    private native int ChmodInner(String str, String str2);

    private void a(Context context) {
        File file;
        synchronized (TbsLinuxToolsJni.class) {
            TbsLog.i("TbsLinuxToolsJni", "TbsLinuxToolsJni init mbIsInited is " + f1228b);
            if (!f1228b) {
                f1228b = true;
                if (TbsShareManager.isThirdPartyApp(context)) {
                    String str = TbsShareManager.f1266d;
                    if (str == null) {
                        TbsShareManager.j(context);
                        str = TbsShareManager.f1266d;
                    }
                    file = new File(str);
                } else {
                    file = m.a().q(context);
                }
                if (file != null) {
                    if (!new File(file.getAbsolutePath() + File.separator + "liblinuxtoolsfortbssdk_jni.so").exists() && !TbsShareManager.isThirdPartyApp(context)) {
                        file = m.a().p(context);
                    }
                    if (file != null) {
                        TbsLog.i("TbsLinuxToolsJni", "TbsLinuxToolsJni init tbsSharePath is " + file.getAbsolutePath());
                        System.load(file.getAbsolutePath() + File.separator + "liblinuxtoolsfortbssdk_jni.so");
                        f1227a = true;
                    }
                }
                ChmodInner("/checkChmodeExists", "700");
            }
        }
    }

    public int a(String str, String str2) {
        if (f1227a) {
            return ChmodInner(str, str2);
        }
        TbsLog.e("TbsLinuxToolsJni", "jni not loaded!", true);
        return -1;
    }
}
