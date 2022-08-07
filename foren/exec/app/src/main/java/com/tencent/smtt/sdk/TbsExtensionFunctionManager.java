package com.tencent.smtt.sdk;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.text.TextUtils;
import com.tencent.smtt.export.external.DexLoader;
import com.tencent.smtt.utils.TbsLog;
import com.tencent.smtt.utils.i;
import java.io.File;
import java.io.IOException;

/* loaded from: classes.dex */
public class TbsExtensionFunctionManager {
    public static final String BUGLY_SWITCH_FILE_NAME = "bugly_switch.txt";
    public static final String COOKIE_SWITCH_FILE_NAME = "cookie_switch.txt";
    public static final String DISABLE_GET_APK_VERSION_SWITCH_FILE_NAME = "disable_get_apk_version_switch.txt";
    public static final String DISABLE_UNPREINIT = "disable_unpreinit.txt";
    public static final String DISABLE_USE_HOST_BACKUP_CORE = "disable_use_host_backup_core.txt";
    public static final String SP_KEY_COOKIE_DB_VERSION = "cookie_db_version";
    public static final String SP_NAME_FOR_COOKIE = "cookie_compatiable";
    public static final int SWITCH_BYTE_COOKIE = 1;
    public static final int SWITCH_BYTE_DISABLE_GET_APK_VERSION = 2;
    public static final int SWITCH_BYTE_DISABLE_UNPREINIT = 4;
    public static final int SWITCH_BYTE_DISABLE_USE_HOST_BACKUPCORE = 8;
    public static final String USEX5_FILE_NAME = "usex5.txt";

    /* renamed from: b */
    public static TbsExtensionFunctionManager f1225b;

    /* renamed from: a */
    public boolean f1226a;

    public static TbsExtensionFunctionManager getInstance() {
        if (f1225b == null) {
            synchronized (TbsExtensionFunctionManager.class) {
                if (f1225b == null) {
                    f1225b = new TbsExtensionFunctionManager();
                }
            }
        }
        return f1225b;
    }

    public synchronized boolean canUseFunction(Context context, String str) {
        boolean z;
        File file = new File(context.getFilesDir(), str);
        if (file.exists()) {
            if (file.isFile()) {
                z = true;
            }
        }
        z = false;
        return z;
    }

    public synchronized int getRomCookieDBVersion(Context context) {
        int i = Build.VERSION.SDK_INT;
        SharedPreferences sharedPreferences = context.getSharedPreferences(SP_NAME_FOR_COOKIE, 4);
        if (sharedPreferences == null) {
            return -1;
        }
        return sharedPreferences.getInt(SP_KEY_COOKIE_DB_VERSION, -1);
    }

    public synchronized void initTbsBuglyIfNeed(Context context) {
        String str;
        if (!this.f1226a) {
            if (!canUseFunction(context, BUGLY_SWITCH_FILE_NAME)) {
                TbsLog.i("TbsExtensionFunMana", "bugly is forbiden!!");
                return;
            }
            if (TbsShareManager.isThirdPartyApp(context)) {
                TbsShareManager.j(context);
                str = TbsShareManager.f1266d;
            } else {
                File q = m.a().q(context);
                if (q == null) {
                    TbsLog.i("TbsExtensionFunMana", "getTbsCoreShareDir is null");
                }
                if (q.listFiles() != null && q.listFiles().length > 0) {
                    str = q.getAbsolutePath();
                }
                TbsLog.i("TbsExtensionFunMana", "getTbsCoreShareDir is empty!");
                return;
            }
            if (TextUtils.isEmpty(str)) {
                TbsLog.i("TbsExtensionFunMana", "bugly init ,corePath is null");
                return;
            }
            File q2 = m.a().q(context);
            if (q2 == null) {
                TbsLog.i("TbsExtensionFunMana", "bugly init ,optDir is null");
                return;
            }
            File file = new File(str, "tbs_bugly_dex.jar");
            WebView.getTbsSDKVersion(context);
            i.a(new DexLoader(file.getParent(), context, new String[]{file.getAbsolutePath()}, q2.getAbsolutePath(), QbSdk.n).loadClass("com.tencent.smtt.tbs.bugly.TBSBuglyManager"), "initBugly", (Class<?>[]) new Class[]{Context.class, String.class, String.class, String.class}, context, str, String.valueOf(43939), String.valueOf(WebView.getTbsCoreVersion(context)));
            this.f1226a = true;
            TbsLog.i("TbsExtensionFunMana", "initTbsBuglyIfNeed success!");
        }
    }

    public synchronized boolean setFunctionEnable(Context context, String str, boolean z) {
        if (context == null) {
            return false;
        }
        File file = new File(context.getFilesDir(), str);
        if (z) {
            if (!file.exists()) {
                try {
                    if (file.createNewFile()) {
                        return true;
                    }
                } catch (IOException e2) {
                    TbsLog.e("TbsExtensionFunMana", "setFunctionEnable,createNewFile fail:" + str);
                    e2.printStackTrace();
                    return false;
                }
            }
        } else if (file.exists()) {
            if (file.delete()) {
                return true;
            }
            TbsLog.e("TbsExtensionFunMana", "setFunctionEnable,file.delete fail:" + str);
            return false;
        }
        return true;
    }
}
