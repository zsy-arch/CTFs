package com.tencent.smtt.sdk;

import android.content.Context;
import android.content.SharedPreferences;
import com.tencent.smtt.utils.TbsLog;
import java.io.File;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/* loaded from: classes.dex */
public class TbsDownloadConfig {
    public static final int CMD_ID_DOWNLOAD_FILE = 101;
    public static final int CMD_ID_FILE_UPLOAD = 100;
    public static final long DEFAULT_RETRY_INTERVAL_SEC = 86400;
    public static final int ERROR_DOWNLOAD = 2;
    public static final int ERROR_INSTALL = 5;
    public static final int ERROR_LOAD = 6;
    public static final int ERROR_NONE = 1;
    public static final int ERROR_REPORTED = 0;
    public static final int ERROR_UNZIP = 4;
    public static final int ERROR_VERIFY = 3;

    /* renamed from: a */
    public static TbsDownloadConfig f1211a;

    /* renamed from: b */
    public Context f1212b;
    public SharedPreferences mPreferences;
    public Map<String, Object> mSyncMap = new HashMap();

    /* loaded from: classes.dex */
    public interface TbsConfigKey {
        public static final String KEY_APP_METADATA = "app_metadata";
        public static final String KEY_APP_VERSIONCODE = "app_versioncode";
        public static final String KEY_APP_VERSIONCODE_FOR_SWITCH = "app_versioncode_for_switch";
        public static final String KEY_APP_VERSIONNAME = "app_versionname";
        public static final String KEY_BACKUPCORE_DELFILELIST = "backupcore_delfilelist";
        public static final String KEY_COUNT_REQUEST_FAIL_IN_24HOURS = "count_request_fail_in_24hours";
        public static final String KEY_DECOUPLECOREVERSION = "tbs_decouplecoreversion";
        public static final String KEY_DESkEY_TOKEN = "tbs_deskey_token";
        public static final String KEY_DEVICE_CPUABI = "device_cpuabi";
        public static final String KEY_DOWNLOADDECOUPLECORE = "tbs_downloaddecouplecore";
        public static final String KEY_DOWNLOADURL_LIST = "tbs_downloadurl_list";
        public static final String KEY_DOWNLOAD_FAILED_MAX_RETRYTIMES = "tbs_download_failed_max_retrytimes";
        public static final String KEY_DOWNLOAD_FAILED_RETRYTIMES = "tbs_download_failed_retrytimes";
        public static final String KEY_DOWNLOAD_INTERRUPT_CODE = "tbs_download_interrupt_code";
        public static final String KEY_DOWNLOAD_INTERRUPT_CODE_REASON = "tbs_download_interrupt_code_reason";
        public static final String KEY_DOWNLOAD_INTERRUPT_TIME = "tbs_download_interrupt_time";
        public static final String KEY_DOWNLOAD_MAXFLOW = "tbs_download_maxflow";
        public static final String KEY_DOWNLOAD_MIN_FREE_SPACE = "tbs_download_min_free_space";
        public static final String KEY_DOWNLOAD_SINGLE_TIMEOUT = "tbs_single_timeout";
        public static final String KEY_DOWNLOAD_SUCCESS_MAX_RETRYTIMES = "tbs_download_success_max_retrytimes";
        public static final String KEY_DOWNLOAD_SUCCESS_RETRYTIMES = "tbs_download_success_retrytimes";
        public static final String KEY_FULL_PACKAGE = "request_full_package";
        public static final String KEY_GUID = "tbs_guid";
        public static final String KEY_INSTALL_INTERRUPT_CODE = "tbs_install_interrupt_code";
        public static final String KEY_IS_OVERSEA = "is_oversea";
        public static final String KEY_LAST_CHECK = "last_check";
        public static final String KEY_LAST_DOWNLOAD_DECOUPLE_CORE = "last_download_decouple_core";
        public static final String KEY_LAST_REQUEST_SUCCESS = "last_request_success";
        public static final String KEY_LAST_THIRDAPP_SENDREQUEST_COREVERSION = "last_thirdapp_sendrequest_coreversion";
        public static final String KEY_NEEDDOWNLOAD = "tbs_needdownload";
        public static final String KEY_REQUEST_FAIL = "request_fail";
        public static final String KEY_RESPONSECODE = "tbs_responsecode";
        public static final String KEY_RETRY_INTERVAL = "retry_interval";
        public static final String KEY_STOP_PRE_OAT = "tbs_stop_preoat";
        public static final String KEY_SWITCH_BACKUPCORE_ENABLE = "switch_backupcore_enable";
        public static final String KEY_TBSAPKFILESIZE = "tbs_apkfilesize";
        public static final String KEY_TBSAPK_MD5 = "tbs_apk_md5";
        public static final String KEY_TBSDOWNLOADURL = "tbs_downloadurl";
        public static final String KEY_TBSDOWNLOAD_FLOW = "tbs_downloadflow";
        public static final String KEY_TBSDOWNLOAD_STARTTIME = "tbs_downloadstarttime";
        public static final String KEY_TBS_CORE_LOAD_RENAME_FILE_LOCK_ENABLE = "tbs_core_load_rename_file_lock_enable";
        public static final String KEY_TBS_CORE_LOAD_RENAME_FILE_LOCK_WAIT_ENABLE = "tbs_core_load_rename_file_lock_wait_enable";
        public static final String KEY_TBS_DOWNLOAD_V = "tbs_download_version";
        public static final String KEY_TBS_DOWNLOAD_V_TYPE = "tbs_download_version_type";
        public static final String KEY_USE_BACKUP_VERSION = "use_backup_version";
        public static final String KEY_USE_BUGLY = "tbs_use_bugly";
    }

    public TbsDownloadConfig(Context context) {
        this.mPreferences = context.getSharedPreferences("tbs_download_config", 4);
        this.f1212b = context.getApplicationContext();
        if (this.f1212b == null) {
            this.f1212b = context;
        }
    }

    public static synchronized TbsDownloadConfig getInstance() {
        TbsDownloadConfig tbsDownloadConfig;
        synchronized (TbsDownloadConfig.class) {
            tbsDownloadConfig = f1211a;
        }
        return tbsDownloadConfig;
    }

    public static synchronized TbsDownloadConfig getInstance(Context context) {
        TbsDownloadConfig tbsDownloadConfig;
        synchronized (TbsDownloadConfig.class) {
            if (f1211a == null) {
                f1211a = new TbsDownloadConfig(context);
            }
            tbsDownloadConfig = f1211a;
        }
        return tbsDownloadConfig;
    }

    public void clear() {
        try {
            this.mSyncMap.clear();
            SharedPreferences.Editor edit = this.mPreferences.edit();
            edit.clear();
            edit.commit();
        } catch (Exception unused) {
        }
    }

    public synchronized void commit() {
        try {
            SharedPreferences.Editor edit = this.mPreferences.edit();
            for (String str : this.mSyncMap.keySet()) {
                Object obj = this.mSyncMap.get(str);
                if (obj instanceof String) {
                    edit.putString(str, (String) obj);
                } else if (obj instanceof Boolean) {
                    edit.putBoolean(str, ((Boolean) obj).booleanValue());
                } else if (obj instanceof Long) {
                    edit.putLong(str, ((Long) obj).longValue());
                } else if (obj instanceof Integer) {
                    edit.putInt(str, ((Integer) obj).intValue());
                } else if (obj instanceof Float) {
                    edit.putFloat(str, ((Float) obj).floatValue());
                }
            }
            edit.commit();
            this.mSyncMap.clear();
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public synchronized int getDownloadFailedMaxRetrytimes() {
        int i;
        i = this.mPreferences.getInt(TbsConfigKey.KEY_DOWNLOAD_FAILED_MAX_RETRYTIMES, 0);
        if (i == 0) {
            i = 100;
        }
        return i;
    }

    public synchronized int getDownloadInterruptCode() {
        int i;
        int i2;
        if (!this.mPreferences.contains(TbsConfigKey.KEY_DOWNLOAD_INTERRUPT_CODE)) {
            try {
                i = !new File(new File(this.f1212b.getFilesDir(), "shared_prefs"), "tbs_download_config").exists() ? -97 : !this.mPreferences.contains(TbsConfigKey.KEY_NEEDDOWNLOAD) ? -96 : -101;
            } catch (Throwable unused) {
                i = -95;
            }
        } else {
            i = this.mPreferences.getInt(TbsConfigKey.KEY_DOWNLOAD_INTERRUPT_CODE, -99);
            if (i == -119 || i == -121) {
                i = this.mPreferences.getInt(TbsConfigKey.KEY_DOWNLOAD_INTERRUPT_CODE_REASON, -119);
            }
            if (System.currentTimeMillis() - this.mPreferences.getLong(TbsConfigKey.KEY_DOWNLOAD_INTERRUPT_TIME, 0L) > 86400000) {
                i -= 98000;
            }
        }
        if (this.f1212b == null || !TbsConfig.APP_QQ.equals(this.f1212b.getApplicationInfo().packageName) || "CN".equals(Locale.getDefault().getCountry())) {
            i2 = (i * TbsLog.TBSLOG_CODE_SDK_BASE) + this.mPreferences.getInt(TbsConfigKey.KEY_INSTALL_INTERRUPT_CODE, -1);
        } else {
            i2 = -320;
        }
        return i2;
    }

    public synchronized long getDownloadMaxflow() {
        int i;
        i = this.mPreferences.getInt(TbsConfigKey.KEY_DOWNLOAD_MAXFLOW, 0);
        if (i == 0) {
            i = 20;
        }
        return i * 1024 * 1024;
    }

    public synchronized long getDownloadMinFreeSpace() {
        int i;
        i = this.mPreferences.getInt(TbsConfigKey.KEY_DOWNLOAD_MIN_FREE_SPACE, 0);
        if (i == 0) {
            i = 0;
        }
        return i * 1024 * 1024;
    }

    public synchronized long getDownloadSingleTimeout() {
        long j;
        j = this.mPreferences.getLong(TbsConfigKey.KEY_DOWNLOAD_SINGLE_TIMEOUT, 0L);
        if (j == 0) {
            j = 1200000;
        }
        return j;
    }

    public synchronized int getDownloadSuccessMaxRetrytimes() {
        int i;
        i = this.mPreferences.getInt(TbsConfigKey.KEY_DOWNLOAD_SUCCESS_MAX_RETRYTIMES, 0);
        if (i == 0) {
            i = 3;
        }
        return i;
    }

    public synchronized long getRetryInterval() {
        if (TbsDownloader.l >= 0) {
            return TbsDownloader.l;
        }
        return this.mPreferences.getLong(TbsConfigKey.KEY_RETRY_INTERVAL, DEFAULT_RETRY_INTERVAL_SEC);
    }

    public synchronized boolean getTbsCoreLoadRenameFileLockEnable() {
        boolean z;
        z = true;
        try {
            z = this.mPreferences.getBoolean(TbsConfigKey.KEY_TBS_CORE_LOAD_RENAME_FILE_LOCK_ENABLE, true);
        } catch (Exception unused) {
        }
        return z;
    }

    public synchronized boolean getTbsCoreLoadRenameFileLockWaitEnable() {
        boolean z;
        z = true;
        try {
            z = this.mPreferences.getBoolean(TbsConfigKey.KEY_TBS_CORE_LOAD_RENAME_FILE_LOCK_WAIT_ENABLE, true);
        } catch (Exception unused) {
        }
        return z;
    }

    public synchronized boolean isOverSea() {
        return this.mPreferences.getBoolean(TbsConfigKey.KEY_IS_OVERSEA, false);
    }

    public synchronized void setDownloadInterruptCode(int i) {
        try {
            SharedPreferences.Editor edit = this.mPreferences.edit();
            edit.putInt(TbsConfigKey.KEY_DOWNLOAD_INTERRUPT_CODE, i);
            edit.putLong(TbsConfigKey.KEY_DOWNLOAD_INTERRUPT_TIME, System.currentTimeMillis());
            edit.commit();
        } catch (Exception unused) {
        }
    }

    public synchronized void setInstallInterruptCode(int i) {
        SharedPreferences.Editor edit = this.mPreferences.edit();
        edit.putInt(TbsConfigKey.KEY_INSTALL_INTERRUPT_CODE, i);
        edit.commit();
    }

    public synchronized void setTbsCoreLoadRenameFileLockEnable(boolean z) {
        try {
            SharedPreferences.Editor edit = this.mPreferences.edit();
            edit.putBoolean(TbsConfigKey.KEY_TBS_CORE_LOAD_RENAME_FILE_LOCK_ENABLE, z);
            edit.commit();
        } catch (Exception unused) {
        }
    }

    public synchronized void setTbsCoreLoadRenameFileLockWaitEnable(boolean z) {
        try {
            SharedPreferences.Editor edit = this.mPreferences.edit();
            edit.putBoolean(TbsConfigKey.KEY_TBS_CORE_LOAD_RENAME_FILE_LOCK_WAIT_ENABLE, z);
            edit.commit();
        } catch (Exception unused) {
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:33:0x0078, code lost:
        r0 = com.tencent.smtt.sdk.TbsLogReport.getInstance(r6).tbsLogInfo();
        r0.setErrorCode(com.tencent.smtt.sdk.TbsListener.ErrorCode.DOWNLOAD_INTERRUPT);
        r0.setFailDetail(" " + r1);
        com.tencent.smtt.sdk.TbsLogReport.getInstance(r6).eventReport(com.tencent.smtt.sdk.TbsLogReport.EventType.TYPE_DOWNLOAD, r0);
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public synchronized void uploadDownloadInterruptCodeIfNeeded(android.content.Context r6) {
        /*
            r5 = this;
            monitor-enter(r5)
            if (r6 == 0) goto L_0x00a6
            java.lang.String r0 = "com.tencent.mm"
            android.content.Context r1 = r6.getApplicationContext()     // Catch: Throwable -> 0x00a6, all -> 0x00a3
            android.content.pm.ApplicationInfo r1 = r1.getApplicationInfo()     // Catch: Throwable -> 0x00a6, all -> 0x00a3
            java.lang.String r1 = r1.packageName     // Catch: Throwable -> 0x00a6, all -> 0x00a3
            boolean r0 = r0.equals(r1)     // Catch: Throwable -> 0x00a6, all -> 0x00a3
            if (r0 == 0) goto L_0x00a6
            r0 = 1
            android.content.SharedPreferences r1 = r5.mPreferences     // Catch: Throwable -> 0x00a6, all -> 0x00a3
            java.lang.String r2 = "tbs_download_interrupt_code"
            boolean r1 = r1.contains(r2)     // Catch: Throwable -> 0x00a6, all -> 0x00a3
            if (r1 != 0) goto L_0x0050
            java.io.File r1 = new java.io.File     // Catch: Throwable -> 0x004d, all -> 0x00a3
            java.io.File r2 = new java.io.File     // Catch: Throwable -> 0x004d, all -> 0x00a3
            android.content.Context r3 = r5.f1212b     // Catch: Throwable -> 0x004d, all -> 0x00a3
            java.io.File r3 = r3.getFilesDir()     // Catch: Throwable -> 0x004d, all -> 0x00a3
            java.lang.String r4 = "shared_prefs"
            r2.<init>(r3, r4)     // Catch: Throwable -> 0x004d, all -> 0x00a3
            java.lang.String r3 = "tbs_download_config"
            r1.<init>(r2, r3)     // Catch: Throwable -> 0x004d, all -> 0x00a3
            boolean r1 = r1.exists()     // Catch: Throwable -> 0x004d, all -> 0x00a3
            if (r1 != 0) goto L_0x003d
            r1 = -97
            goto L_0x0076
        L_0x003d:
            android.content.SharedPreferences r1 = r5.mPreferences     // Catch: Throwable -> 0x004d, all -> 0x00a3
            java.lang.String r2 = "tbs_needdownload"
            boolean r1 = r1.contains(r2)     // Catch: Throwable -> 0x004d, all -> 0x00a3
            if (r1 != 0) goto L_0x004a
            r1 = -96
            goto L_0x0076
        L_0x004a:
            r1 = -101(0xffffffffffffff9b, float:NaN)
            goto L_0x0076
        L_0x004d:
            r1 = -95
            goto L_0x0076
        L_0x0050:
            android.content.SharedPreferences r1 = r5.mPreferences     // Catch: Throwable -> 0x00a6, all -> 0x00a3
            java.lang.String r2 = "tbs_download_interrupt_code"
            r3 = -99
            int r1 = r1.getInt(r2, r3)     // Catch: Throwable -> 0x00a6, all -> 0x00a3
            r2 = -206(0xffffffffffffff32, float:NaN)
            if (r1 > r2) goto L_0x0063
            r2 = -219(0xffffffffffffff25, float:NaN)
            if (r1 < r2) goto L_0x0063
            goto L_0x0076
        L_0x0063:
            r2 = -302(0xfffffffffffffed2, float:NaN)
            if (r1 > r2) goto L_0x006c
            r2 = -316(0xfffffffffffffec4, float:NaN)
            if (r1 < r2) goto L_0x006c
            goto L_0x0076
        L_0x006c:
            r2 = -318(0xfffffffffffffec2, float:NaN)
            if (r1 > r2) goto L_0x0075
            r2 = -322(0xfffffffffffffebe, float:NaN)
            if (r1 < r2) goto L_0x0075
            goto L_0x0076
        L_0x0075:
            r0 = 0
        L_0x0076:
            if (r0 == 0) goto L_0x00a6
            com.tencent.smtt.sdk.TbsLogReport r0 = com.tencent.smtt.sdk.TbsLogReport.getInstance(r6)     // Catch: Throwable -> 0x00a6, all -> 0x00a3
            com.tencent.smtt.sdk.TbsLogReport$TbsLogInfo r0 = r0.tbsLogInfo()     // Catch: Throwable -> 0x00a6, all -> 0x00a3
            r2 = 128(0x80, float:1.794E-43)
            r0.setErrorCode(r2)     // Catch: Throwable -> 0x00a6, all -> 0x00a3
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch: Throwable -> 0x00a6, all -> 0x00a3
            r2.<init>()     // Catch: Throwable -> 0x00a6, all -> 0x00a3
            java.lang.String r3 = " "
            r2.append(r3)     // Catch: Throwable -> 0x00a6, all -> 0x00a3
            r2.append(r1)     // Catch: Throwable -> 0x00a6, all -> 0x00a3
            java.lang.String r1 = r2.toString()     // Catch: Throwable -> 0x00a6, all -> 0x00a3
            r0.setFailDetail(r1)     // Catch: Throwable -> 0x00a6, all -> 0x00a3
            com.tencent.smtt.sdk.TbsLogReport r6 = com.tencent.smtt.sdk.TbsLogReport.getInstance(r6)     // Catch: Throwable -> 0x00a6, all -> 0x00a3
            com.tencent.smtt.sdk.TbsLogReport$EventType r1 = com.tencent.smtt.sdk.TbsLogReport.EventType.TYPE_DOWNLOAD     // Catch: Throwable -> 0x00a6, all -> 0x00a3
            r6.eventReport(r1, r0)     // Catch: Throwable -> 0x00a6, all -> 0x00a3
            goto L_0x00a6
        L_0x00a3:
            r6 = move-exception
            monitor-exit(r5)
            throw r6
        L_0x00a6:
            monitor-exit(r5)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.sdk.TbsDownloadConfig.uploadDownloadInterruptCodeIfNeeded(android.content.Context):void");
    }
}
