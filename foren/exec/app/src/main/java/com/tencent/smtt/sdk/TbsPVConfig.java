package com.tencent.smtt.sdk;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

/* loaded from: classes.dex */
public class TbsPVConfig extends TbsBaseConfig {

    /* renamed from: b  reason: collision with root package name */
    public static TbsPVConfig f1249b;
    public SharedPreferences mPreferences;

    /* loaded from: classes.dex */
    public interface TbsPVConfigKey {
        public static final String KEY_DISABLED_CORE_VERSION = "disabled_core_version";
        public static final String KEY_EMERGENT_CORE_VERSION = "emergent_core_version";
        public static final String KEY_ENABLE_NO_SHARE_GRAY = "enable_no_share_gray";
        public static final String KEY_GET_LOCALCOREVERSION_MORETIMES = "get_localcoreversion_moretimes";
        public static final String KEY_IS_DISABLE_HOST_BACKUP_CORE = "disable_host_backup";
        public static final String KEY_READ_APK = "read_apk";
        public static final String KEY_TBS_CORE_SANDBOX_MODE_ENABLE = "tbs_core_sandbox_mode_enable";
    }

    public static synchronized TbsPVConfig getInstance(Context context) {
        TbsPVConfig tbsPVConfig;
        synchronized (TbsPVConfig.class) {
            if (f1249b == null) {
                f1249b = new TbsPVConfig();
                f1249b.init(context);
            }
            tbsPVConfig = f1249b;
        }
        return tbsPVConfig;
    }

    public static synchronized void releaseInstance() {
        synchronized (TbsPVConfig.class) {
            f1249b = null;
        }
    }

    @Override // com.tencent.smtt.sdk.TbsBaseConfig
    public String getConfigFileName() {
        return "tbs_pv_config";
    }

    public synchronized int getDisabledCoreVersion() {
        int i;
        i = 0;
        try {
            String str = this.f1200a.get(TbsPVConfigKey.KEY_DISABLED_CORE_VERSION);
            if (!TextUtils.isEmpty(str)) {
                i = Integer.parseInt(str);
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        return i;
    }

    public synchronized int getEmergentCoreVersion() {
        int i;
        i = 0;
        try {
            String str = this.f1200a.get(TbsPVConfigKey.KEY_EMERGENT_CORE_VERSION);
            if (!TextUtils.isEmpty(str)) {
                i = Integer.parseInt(str);
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        return i;
    }

    public synchronized int getLocalCoreVersionMoreTimes() {
        int i;
        i = 0;
        try {
            String str = this.f1200a.get(TbsPVConfigKey.KEY_GET_LOCALCOREVERSION_MORETIMES);
            if (!TextUtils.isEmpty(str)) {
                i = Integer.parseInt(str);
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        return i;
    }

    public synchronized int getReadApk() {
        int i;
        i = 0;
        try {
            String str = this.f1200a.get(TbsPVConfigKey.KEY_READ_APK);
            if (!TextUtils.isEmpty(str)) {
                i = Integer.parseInt(str);
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        return i;
    }

    public synchronized String getSyncMapValue(String str) {
        return this.f1200a.get(str);
    }

    public synchronized boolean getTbsCoreSandboxModeEnable() {
        boolean z;
        try {
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        if ("true".equals(this.f1200a.get(TbsPVConfigKey.KEY_TBS_CORE_SANDBOX_MODE_ENABLE))) {
            z = true;
        }
        z = false;
        return z;
    }

    public synchronized boolean isDisableHostBackupCore() {
        boolean z;
        String str;
        try {
            str = this.f1200a.get(TbsPVConfigKey.KEY_IS_DISABLE_HOST_BACKUP_CORE);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        if (!TextUtils.isEmpty(str)) {
            if (str.equals("true")) {
                z = true;
            }
        }
        z = false;
        return z;
    }

    public synchronized boolean isEnableNoCoreGray() {
        boolean z;
        String str;
        try {
            str = this.f1200a.get(TbsPVConfigKey.KEY_ENABLE_NO_SHARE_GRAY);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        if (!TextUtils.isEmpty(str)) {
            if (str.equals("true")) {
                z = true;
            }
        }
        z = false;
        return z;
    }

    public synchronized void putData(String str, String str2) {
        this.f1200a.put(str, str2);
    }
}
