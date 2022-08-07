package com.tencent.smtt.sdk;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Process;
import android.text.TextUtils;
import android.util.Log;
import com.tencent.smtt.sdk.TbsDownloadConfig;
import com.tencent.smtt.sdk.TbsListener;
import com.tencent.smtt.utils.TbsLog;
import com.tencent.smtt.utils.aa;
import com.tencent.smtt.utils.k;
import dalvik.system.DexClassLoader;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileLock;
import java.util.Properties;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/* loaded from: classes2.dex */
public class aj {
    private FileLock e;
    private FileOutputStream f;
    private static aj c = null;
    private static final Lock h = new ReentrantLock();
    private static final Lock i = new ReentrantLock();
    public static ThreadLocal<Integer> a = new ak();
    private static Handler k = null;
    private static final Long[][] l = {new Long[]{25413L, 11460320L}, new Long[]{25436L, 12009376L}, new Long[]{25437L, 11489180L}, new Long[]{25438L, 11489180L}, new Long[]{25439L, 12013472L}, new Long[]{25440L, 11489180L}, new Long[]{25442L, 11489180L}};
    static boolean b = false;
    private static boolean m = false;
    private int d = 0;
    private boolean g = false;
    private boolean j = false;

    private aj() {
        if (k == null) {
            k = new al(this, ah.a().getLooper());
        }
    }

    public static synchronized aj a() {
        aj ajVar;
        synchronized (aj.class) {
            if (c == null) {
                synchronized (aj.class) {
                    if (c == null) {
                        c = new aj();
                    }
                }
            }
            ajVar = c;
        }
        return ajVar;
    }

    /* JADX WARN: Removed duplicated region for block: B:52:0x00ac A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:58:0x00a7 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void a(int r7, java.lang.String r8, android.content.Context r9) {
        /*
            Method dump skipped, instructions count: 202
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.sdk.aj.a(int, java.lang.String, android.content.Context):void");
    }

    public static void a(Context context) {
        if (n(context)) {
            return;
        }
        if (c(context, "core_unzip_tmp")) {
            TbsCoreLoadStat.getInstance().a(context, 417, new Throwable("TMP_TBS_UNZIP_FOLDER_NAME"));
            TbsLog.e("TbsInstaller", "TbsInstaller-UploadIfTempCoreExistConfError INFO_TEMP_CORE_EXIST_CONF_ERROR TMP_TBS_UNZIP_FOLDER_NAME");
        } else if (c(context, "core_share_backup_tmp")) {
            TbsCoreLoadStat.getInstance().a(context, 417, new Throwable("TMP_BACKUP_TBSCORE_FOLDER_NAME"));
            TbsLog.e("TbsInstaller", "TbsInstaller-UploadIfTempCoreExistConfError INFO_TEMP_CORE_EXIST_CONF_ERROR TMP_BACKUP_TBSCORE_FOLDER_NAME");
        } else if (c(context, "core_copy_tmp")) {
            TbsCoreLoadStat.getInstance().a(context, 417, new Throwable("TMP_TBS_COPY_FOLDER_NAME"));
            TbsLog.e("TbsInstaller", "TbsInstaller-UploadIfTempCoreExistConfError INFO_TEMP_CORE_EXIST_CONF_ERROR TMP_TBS_COPY_FOLDER_NAME");
        }
    }

    /* JADX WARN: Can't wrap try/catch for region: R(19:153|61|(2:154|62)|(3:163|64|(9:67|158|68|69|(2:165|71)|72|(3:74|(4:77|(2:98|(1:106)(2:102|174))|83|75)|170)(0)|107|(9:112|(3:116|(1:118)(1:129)|119)|120|(1:122)(1:130)|123|(1:125)(1:131)|156|126|127)(2:110|111)))|84|(0)|72|(0)(0)|107|(0)|112|(4:114|116|(0)(0)|119)|120|(0)(0)|123|(0)(0)|156|126|127) */
    /* JADX WARN: Code restructure failed: missing block: B:132:0x042f, code lost:
        r0 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:133:0x0430, code lost:
        com.tencent.smtt.utils.TbsLog.e("TbsInstaller", "Init tbs_preload_x5_counter#2 exception:" + android.util.Log.getStackTraceString(r0));
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:109:0x0358 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:114:0x0396 A[Catch: Exception -> 0x02aa, all -> 0x030c, TryCatch #5 {Exception -> 0x02aa, blocks: (B:18:0x007b, B:20:0x0091, B:23:0x00b0, B:25:0x00ce, B:27:0x00e9, B:32:0x00f9, B:39:0x0108, B:40:0x0114, B:42:0x011a, B:47:0x0171, B:49:0x017f, B:51:0x019f, B:52:0x01aa, B:55:0x01b6, B:57:0x01f7, B:59:0x0200, B:71:0x0253, B:74:0x0259, B:75:0x0267, B:77:0x026a, B:79:0x0278, B:81:0x0284, B:89:0x029e, B:93:0x02a6, B:94:0x02a9, B:98:0x02cd, B:100:0x02e3, B:102:0x02e9, B:106:0x031b, B:107:0x033e, B:110:0x035a, B:112:0x0385, B:114:0x0396, B:116:0x039c, B:119:0x03a6, B:120:0x03ac, B:122:0x03b8, B:123:0x03c3, B:125:0x03ea, B:126:0x03f1, B:127:0x0409, B:130:0x041b, B:131:0x0427, B:133:0x0430, B:134:0x044d, B:136:0x0477, B:138:0x048d, B:141:0x04b2, B:143:0x04b8, B:145:0x04be), top: B:160:0x007b, outer: #10 }] */
    /* JADX WARN: Removed duplicated region for block: B:118:0x03a4  */
    /* JADX WARN: Removed duplicated region for block: B:122:0x03b8 A[Catch: Exception -> 0x02aa, all -> 0x030c, TryCatch #5 {Exception -> 0x02aa, blocks: (B:18:0x007b, B:20:0x0091, B:23:0x00b0, B:25:0x00ce, B:27:0x00e9, B:32:0x00f9, B:39:0x0108, B:40:0x0114, B:42:0x011a, B:47:0x0171, B:49:0x017f, B:51:0x019f, B:52:0x01aa, B:55:0x01b6, B:57:0x01f7, B:59:0x0200, B:71:0x0253, B:74:0x0259, B:75:0x0267, B:77:0x026a, B:79:0x0278, B:81:0x0284, B:89:0x029e, B:93:0x02a6, B:94:0x02a9, B:98:0x02cd, B:100:0x02e3, B:102:0x02e9, B:106:0x031b, B:107:0x033e, B:110:0x035a, B:112:0x0385, B:114:0x0396, B:116:0x039c, B:119:0x03a6, B:120:0x03ac, B:122:0x03b8, B:123:0x03c3, B:125:0x03ea, B:126:0x03f1, B:127:0x0409, B:130:0x041b, B:131:0x0427, B:133:0x0430, B:134:0x044d, B:136:0x0477, B:138:0x048d, B:141:0x04b2, B:143:0x04b8, B:145:0x04be), top: B:160:0x007b, outer: #10 }] */
    /* JADX WARN: Removed duplicated region for block: B:125:0x03ea A[Catch: Exception -> 0x02aa, all -> 0x030c, TRY_LEAVE, TryCatch #5 {Exception -> 0x02aa, blocks: (B:18:0x007b, B:20:0x0091, B:23:0x00b0, B:25:0x00ce, B:27:0x00e9, B:32:0x00f9, B:39:0x0108, B:40:0x0114, B:42:0x011a, B:47:0x0171, B:49:0x017f, B:51:0x019f, B:52:0x01aa, B:55:0x01b6, B:57:0x01f7, B:59:0x0200, B:71:0x0253, B:74:0x0259, B:75:0x0267, B:77:0x026a, B:79:0x0278, B:81:0x0284, B:89:0x029e, B:93:0x02a6, B:94:0x02a9, B:98:0x02cd, B:100:0x02e3, B:102:0x02e9, B:106:0x031b, B:107:0x033e, B:110:0x035a, B:112:0x0385, B:114:0x0396, B:116:0x039c, B:119:0x03a6, B:120:0x03ac, B:122:0x03b8, B:123:0x03c3, B:125:0x03ea, B:126:0x03f1, B:127:0x0409, B:130:0x041b, B:131:0x0427, B:133:0x0430, B:134:0x044d, B:136:0x0477, B:138:0x048d, B:141:0x04b2, B:143:0x04b8, B:145:0x04be), top: B:160:0x007b, outer: #10 }] */
    /* JADX WARN: Removed duplicated region for block: B:129:0x0418  */
    /* JADX WARN: Removed duplicated region for block: B:130:0x041b A[Catch: Exception -> 0x02aa, all -> 0x030c, TryCatch #5 {Exception -> 0x02aa, blocks: (B:18:0x007b, B:20:0x0091, B:23:0x00b0, B:25:0x00ce, B:27:0x00e9, B:32:0x00f9, B:39:0x0108, B:40:0x0114, B:42:0x011a, B:47:0x0171, B:49:0x017f, B:51:0x019f, B:52:0x01aa, B:55:0x01b6, B:57:0x01f7, B:59:0x0200, B:71:0x0253, B:74:0x0259, B:75:0x0267, B:77:0x026a, B:79:0x0278, B:81:0x0284, B:89:0x029e, B:93:0x02a6, B:94:0x02a9, B:98:0x02cd, B:100:0x02e3, B:102:0x02e9, B:106:0x031b, B:107:0x033e, B:110:0x035a, B:112:0x0385, B:114:0x0396, B:116:0x039c, B:119:0x03a6, B:120:0x03ac, B:122:0x03b8, B:123:0x03c3, B:125:0x03ea, B:126:0x03f1, B:127:0x0409, B:130:0x041b, B:131:0x0427, B:133:0x0430, B:134:0x044d, B:136:0x0477, B:138:0x048d, B:141:0x04b2, B:143:0x04b8, B:145:0x04be), top: B:160:0x007b, outer: #10 }] */
    /* JADX WARN: Removed duplicated region for block: B:131:0x0427 A[Catch: Exception -> 0x02aa, all -> 0x030c, TryCatch #5 {Exception -> 0x02aa, blocks: (B:18:0x007b, B:20:0x0091, B:23:0x00b0, B:25:0x00ce, B:27:0x00e9, B:32:0x00f9, B:39:0x0108, B:40:0x0114, B:42:0x011a, B:47:0x0171, B:49:0x017f, B:51:0x019f, B:52:0x01aa, B:55:0x01b6, B:57:0x01f7, B:59:0x0200, B:71:0x0253, B:74:0x0259, B:75:0x0267, B:77:0x026a, B:79:0x0278, B:81:0x0284, B:89:0x029e, B:93:0x02a6, B:94:0x02a9, B:98:0x02cd, B:100:0x02e3, B:102:0x02e9, B:106:0x031b, B:107:0x033e, B:110:0x035a, B:112:0x0385, B:114:0x0396, B:116:0x039c, B:119:0x03a6, B:120:0x03ac, B:122:0x03b8, B:123:0x03c3, B:125:0x03ea, B:126:0x03f1, B:127:0x0409, B:130:0x041b, B:131:0x0427, B:133:0x0430, B:134:0x044d, B:136:0x0477, B:138:0x048d, B:141:0x04b2, B:143:0x04b8, B:145:0x04be), top: B:160:0x007b, outer: #10 }] */
    /* JADX WARN: Removed duplicated region for block: B:165:0x0253 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:74:0x0259 A[Catch: Exception -> 0x02aa, all -> 0x030c, TRY_ENTER, TryCatch #5 {Exception -> 0x02aa, blocks: (B:18:0x007b, B:20:0x0091, B:23:0x00b0, B:25:0x00ce, B:27:0x00e9, B:32:0x00f9, B:39:0x0108, B:40:0x0114, B:42:0x011a, B:47:0x0171, B:49:0x017f, B:51:0x019f, B:52:0x01aa, B:55:0x01b6, B:57:0x01f7, B:59:0x0200, B:71:0x0253, B:74:0x0259, B:75:0x0267, B:77:0x026a, B:79:0x0278, B:81:0x0284, B:89:0x029e, B:93:0x02a6, B:94:0x02a9, B:98:0x02cd, B:100:0x02e3, B:102:0x02e9, B:106:0x031b, B:107:0x033e, B:110:0x035a, B:112:0x0385, B:114:0x0396, B:116:0x039c, B:119:0x03a6, B:120:0x03ac, B:122:0x03b8, B:123:0x03c3, B:125:0x03ea, B:126:0x03f1, B:127:0x0409, B:130:0x041b, B:131:0x0427, B:133:0x0430, B:134:0x044d, B:136:0x0477, B:138:0x048d, B:141:0x04b2, B:143:0x04b8, B:145:0x04be), top: B:160:0x007b, outer: #10 }] */
    @android.annotation.TargetApi(11)
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void a(android.content.Context r13, android.content.Context r14, int r15) {
        /*
            Method dump skipped, instructions count: 1230
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.sdk.aj.a(android.content.Context, android.content.Context, int):void");
    }

    private boolean a(Context context, File file) {
        String str;
        String str2;
        boolean z = true;
        TbsLog.i("TbsInstaller", "TbsInstaller-unzipTbs start");
        if (!k.c(file)) {
            TbsLogReport.a(context).a(204, "apk is invalid!");
            TbsDownloadConfig.getInstance(context).setInstallInterruptCode(-520);
            return false;
        }
        try {
            File file2 = new File(context.getDir("tbs", 0), "core_unzip_tmp");
            if (file2 != null && file2.exists()) {
                k.b(file2);
            }
        } catch (Throwable th) {
            TbsLog.e("TbsInstaller", "TbsInstaller-unzipTbs -- delete unzip folder if exists exception" + Log.getStackTraceString(th));
        }
        File k2 = k(context);
        try {
            if (k2 == null) {
                TbsLogReport.a(context).a(205, "tmp unzip dir is null!");
                TbsDownloadConfig.getInstance(context).setInstallInterruptCode(-521);
                return false;
            }
            try {
                try {
                    k.a(k2);
                    boolean a2 = k.a(file, k2);
                    if (!a2) {
                        k.b(k2);
                        TbsDownloadConfig.getInstance(context).setInstallInterruptCode(-522);
                        TbsLog.e("TbsInstaller", "copyFileIfChanged -- delete tmpTbsCoreUnzipDir#1! exist:" + k2.exists());
                    } else {
                        f(context, true);
                    }
                    return a2;
                } catch (Exception e) {
                    TbsDownloadConfig.getInstance(context).setInstallInterruptCode(-523);
                    TbsLogReport.a(context).a(207, e);
                    if (k2 == null || !k2.exists()) {
                        z = false;
                    }
                    if (z && k2 != null) {
                        try {
                            k.b(k2);
                            TbsLog.e("TbsInstaller", "copyFileIfChanged -- delete tmpTbsCoreUnzipDir#2! exist:" + k2.exists());
                        } catch (Throwable th2) {
                            TbsLog.e("TbsInstaller", "copyFileIfChanged -- delete tmpTbsCoreUnzipDir#2! exception:" + Log.getStackTraceString(th2));
                        }
                    }
                    str = "TbsInstaller";
                    str2 = "TbsInstaller-unzipTbs done";
                    TbsLog.i(str, str2);
                    return false;
                }
            } catch (IOException e2) {
                TbsDownloadConfig.getInstance(context).setInstallInterruptCode(-523);
                TbsLogReport.a(context).a(206, e2);
                if (k2 == null || !k2.exists()) {
                    z = false;
                }
                if (z && k2 != null) {
                    try {
                        k.b(k2);
                        TbsLog.e("TbsInstaller", "copyFileIfChanged -- delete tmpTbsCoreUnzipDir#2! exist:" + k2.exists());
                    } catch (Throwable th3) {
                        TbsLog.e("TbsInstaller", "copyFileIfChanged -- delete tmpTbsCoreUnzipDir#2! exception:" + Log.getStackTraceString(th3));
                    }
                }
                str = "TbsInstaller";
                str2 = "TbsInstaller-unzipTbs done";
                TbsLog.i(str, str2);
                return false;
            }
        } finally {
            TbsLog.i("TbsInstaller", "TbsInstaller-unzipTbs done");
        }
    }

    @TargetApi(11)
    public void b(Context context, String str, int i2) {
        boolean z;
        String str2;
        int i3 = 200;
        int i4 = 0;
        TbsDownloadConfig.getInstance(context).setInstallInterruptCode(-501);
        if (b(context)) {
            TbsLog.i("TbsInstaller", "isTbsLocalInstalled --> no installation!", true);
            TbsDownloadConfig.getInstance(context).setInstallInterruptCode(-502);
            return;
        }
        TbsLog.i("TbsInstaller", "TbsInstaller-installTbsCoreInThread tbsApkPath=" + str);
        TbsLog.i("TbsInstaller", "TbsInstaller-installTbsCoreInThread tbsCoreTargetVer=" + i2);
        TbsLog.i("TbsInstaller", "TbsInstaller-continueInstallTbsCore currentProcessName=" + context.getApplicationInfo().processName);
        TbsLog.i("TbsInstaller", "TbsInstaller-installTbsCoreInThread currentProcessId=" + Process.myPid());
        TbsLog.i("TbsInstaller", "TbsInstaller-installTbsCoreInThread currentThreadName=" + Thread.currentThread().getName());
        if ((Build.VERSION.SDK_INT >= 11 ? context.getSharedPreferences("tbs_preloadx5_check_cfg_file", 4) : context.getSharedPreferences("tbs_preloadx5_check_cfg_file", 0)).getInt("tbs_precheck_disable_version", -1) == i2) {
            TbsLog.e("TbsInstaller", "TbsInstaller-installTbsCoreInThread -- version:" + i2 + " is disabled by preload_x5_check!");
            TbsDownloadConfig.getInstance(context).setInstallInterruptCode(-503);
        } else if (!k.b(context)) {
            long a2 = aa.a();
            long downloadMinFreeSpace = TbsDownloadConfig.getInstance(context).getDownloadMinFreeSpace();
            TbsDownloadConfig.getInstance(context).setInstallInterruptCode(-504);
            TbsLogReport.a(context).a(210, "rom is not enough when installing tbs core! curAvailROM=" + a2 + ",minReqRom=" + downloadMinFreeSpace);
        } else if (!m(context)) {
            TbsDownloadConfig.getInstance(context).setInstallInterruptCode(-505);
        } else {
            boolean tryLock = i.tryLock();
            TbsLog.i("TbsInstaller", "TbsInstaller-installTbsCoreInThread locked =" + tryLock);
            if (tryLock) {
                TbsDownloadConfig.getInstance(context).setInstallInterruptCode(-507);
                h.lock();
                try {
                    int c2 = ae.a(context).c("copy_core_ver");
                    int b2 = ae.a(context).b();
                    TbsLog.i("TbsInstaller", "TbsInstaller-installTbsCoreInThread tbsCoreCopyVer =" + c2);
                    TbsLog.i("TbsInstaller", "TbsInstaller-installTbsCoreInThread tbsCoreInstallVer =" + b2);
                    TbsLog.i("TbsInstaller", "TbsInstaller-installTbsCoreInThread tbsCoreTargetVer =" + i2);
                    if ((b2 > 0 && i2 > b2) || (c2 > 0 && i2 > c2)) {
                        g(context);
                    }
                    int c3 = ae.a(context).c();
                    int d = d(context);
                    TbsLog.i("TbsInstaller", "TbsInstaller-installTbsCoreInThread installStatus1=" + c3);
                    TbsLog.i("TbsInstaller", "TbsInstaller-installTbsCoreInThread tbsCoreInstalledVer=" + d);
                    if (c3 < 0 || c3 >= 2) {
                        if (c3 == 3 && d > 0 && (i2 > d || i2 == 88888888)) {
                            c3 = -1;
                            g(context);
                            TbsLog.i("TbsInstaller", "TbsInstaller-installTbsCoreInThread -- update TBS.....", true);
                        }
                        z = false;
                    } else {
                        TbsLog.i("TbsInstaller", "TbsInstaller-installTbsCoreInThread -- retry.....", true);
                        c3 = c3;
                        z = true;
                    }
                    TbsDownloadConfig.getInstance(context).setInstallInterruptCode(-508);
                    TbsLog.i("TbsInstaller", "TbsInstaller-installTbsCoreInThread installStatus2=" + c3);
                    if (c3 < 1) {
                        TbsLog.i("TbsInstaller", "STEP 2/2 begin installation.....", true);
                        TbsDownloadConfig.getInstance(context).setInstallInterruptCode(-509);
                        if (z) {
                            int c4 = ae.a(context).c("unzip_retry_num");
                            if (c4 > 10) {
                                TbsLogReport.a(context).a(201, "exceed unzip retry num!");
                                t(context);
                                TbsDownloadConfig.getInstance(context).setInstallInterruptCode(-510);
                                return;
                            }
                            ae.a(context).b(c4 + 1);
                        }
                        if (str == null) {
                            str2 = ae.a(context).d("install_apk_path");
                            if (str2 == null) {
                                TbsLogReport.a(context).a(202, "apk path is null!");
                                TbsDownloadConfig.getInstance(context).setInstallInterruptCode(-511);
                                return;
                            }
                        } else {
                            str2 = str;
                        }
                        TbsLog.i("TbsInstaller", "TbsInstaller-installTbsCoreInThread apkPath =" + str2);
                        int b3 = b(context, str2);
                        if (b3 == 0) {
                            TbsDownloadConfig.getInstance(context).setInstallInterruptCode(-512);
                            TbsLogReport.a(context).a(203, "apk version is 0!");
                            return;
                        }
                        ae.a(context).a("install_apk_path", str2);
                        ae.a(context).b(b3, 0);
                        TbsDownloadConfig.getInstance(context).setInstallInterruptCode(-548);
                        if (!a(context, new File(str2))) {
                            TbsLogReport.a(context).a(207, "unzipTbsApk failed");
                            return;
                        }
                        if (z) {
                            int b4 = ae.a(context).b("unlzma_status");
                            if (b4 > 5) {
                                TbsLogReport.a(context).a(TbsListener.ErrorCode.EXCEED_LZMA_RETRY_NUM, "exceed unlzma retry num!");
                                TbsDownloadConfig.getInstance(context).setInstallInterruptCode(-553);
                                t(context);
                                ac.c(context);
                                TbsDownloadConfig.getInstance(context).a.put(TbsDownloadConfig.TbsConfigKey.KEY_NEEDDOWNLOAD, true);
                                TbsDownloadConfig.getInstance(context).a.put(TbsDownloadConfig.TbsConfigKey.KEY_FULL_PACKAGE, true);
                                TbsDownloadConfig.getInstance(context).commit();
                                return;
                            }
                            ae.a(context).d(b4 + 1);
                        }
                        TbsLog.i("TbsInstaller", "unlzma begin");
                        int i5 = TbsDownloadConfig.getInstance().mPreferences.getInt(TbsDownloadConfig.TbsConfigKey.KEY_RESPONSECODE, 0);
                        if (d(context) != 0) {
                            Object a3 = QbSdk.a(context, "can_unlzma", (Bundle) null);
                            if ((a3 == null || !(a3 instanceof Boolean)) ? false : ((Boolean) a3).booleanValue()) {
                                Bundle bundle = new Bundle();
                                bundle.putInt("responseCode", i5);
                                bundle.putString("unzip_temp_path", k(context).getAbsolutePath());
                                Object a4 = QbSdk.a(context, "unlzma", bundle);
                                if (a4 == null) {
                                    TbsLog.i("TbsInstaller", "unlzma return null");
                                    TbsLogReport.a(context).a(222, "unlzma is null");
                                } else if (a4 instanceof Boolean) {
                                    if (((Boolean) a4).booleanValue()) {
                                        TbsLog.i("TbsInstaller", "unlzma success");
                                        i4 = 1;
                                    } else {
                                        TbsLog.i("TbsInstaller", "unlzma return false");
                                        TbsLogReport.a(context).a(222, "unlzma return false");
                                        i4 = 0;
                                    }
                                } else if (a4 instanceof Bundle) {
                                    i4 = 1;
                                } else if (a4 instanceof Throwable) {
                                    TbsLog.i("TbsInstaller", "unlzma failure because Throwable" + Log.getStackTraceString((Throwable) a4));
                                    TbsLogReport.a(context).a(222, (Throwable) a4);
                                }
                                if (i4 == 0) {
                                    return;
                                }
                            }
                        }
                        TbsLog.i("TbsInstaller", "unlzma finished");
                        ae.a(context).b(b3, 1);
                        i4 = b3;
                    }
                    if (c3 < 2) {
                        if (z) {
                            int c5 = ae.a(context).c("dexopt_retry_num");
                            if (c5 > 10) {
                                TbsLogReport.a(context).a(208, "exceed dexopt retry num!");
                                TbsDownloadConfig.getInstance(context).setInstallInterruptCode(-514);
                                t(context);
                                return;
                            }
                            ae.a(context).a(c5 + 1);
                        }
                        TbsDownloadConfig.getInstance(context).setInstallInterruptCode(-549);
                        if (!c(context, 0)) {
                            TbsDownloadConfig.getInstance(context).setInstallInterruptCode(-515);
                            return;
                        }
                        ae.a(context).b(i4, 2);
                        TbsLog.i("TbsInstaller", "STEP 2/2 installation completed! you can restart!", true);
                        TbsLog.i("TbsInstaller", "STEP 2/2 installation completed! you can restart! version:" + i2);
                        TbsDownloadConfig.getInstance(context).setInstallInterruptCode(-516);
                        SharedPreferences.Editor edit = (Build.VERSION.SDK_INT >= 11 ? context.getSharedPreferences("tbs_preloadx5_check_cfg_file", 4) : context.getSharedPreferences("tbs_preloadx5_check_cfg_file", 0)).edit();
                        edit.putInt("tbs_preload_x5_counter", 0);
                        edit.putInt("tbs_preload_x5_recorder", 0);
                        edit.putInt("tbs_preload_x5_version", i2);
                        edit.commit();
                        TbsDownloadConfig.getInstance(context).setInstallInterruptCode(-517);
                        if (i2 == 88888888) {
                            a(i2, str, context);
                        }
                        if (this.j) {
                            TbsLogReport a5 = TbsLogReport.a(context);
                            if (ae.a(context).d() == 1) {
                                i3 = TbsListener.ErrorCode.INCRUPDATE_INSTALL_SUCCESS;
                            }
                            a5.a(i3, "continueInstallWithout core success");
                        } else {
                            TbsLogReport.a(context).a(ae.a(context).d() == 1 ? TbsListener.ErrorCode.INCRUPDATE_INSTALL_SUCCESS : 200, "success");
                        }
                    } else if (c3 == 2) {
                        QbSdk.j.onInstallFinish(200);
                    }
                } finally {
                    h.unlock();
                    i.unlock();
                    b();
                }
            } else {
                TbsDownloadConfig.getInstance(context).setInstallInterruptCode(-519);
                b();
            }
        }
    }

    private boolean b(Context context, File file) {
        try {
            File[] listFiles = file.listFiles(new ap(this));
            int length = listFiles.length;
            if (Build.VERSION.SDK_INT < 16 && context.getPackageName() != null && context.getPackageName().equalsIgnoreCase(TbsConfig.APP_DEMO)) {
                try {
                    Thread.sleep(5000L);
                } catch (Exception e) {
                }
            }
            ClassLoader classLoader = context.getClassLoader();
            for (int i2 = 0; i2 < length; i2++) {
                TbsLog.i("TbsInstaller", "jarFile: " + listFiles[i2].getAbsolutePath());
                new DexClassLoader(listFiles[i2].getAbsolutePath(), file.getAbsolutePath(), null, classLoader);
            }
            return true;
        } catch (Exception e2) {
            e2.printStackTrace();
            TbsLogReport.a(context).a(209, e2.toString());
            TbsLog.i("TbsInstaller", "TbsInstaller-doTbsDexOpt done");
            return false;
        }
    }

    private boolean c(Context context, int i2) {
        File h2;
        boolean z = false;
        TbsLog.i("TbsInstaller", "TbsInstaller-doTbsDexOpt start - dirMode: " + i2);
        try {
            switch (i2) {
                case 0:
                    h2 = k(context);
                    break;
                case 1:
                    h2 = l(context);
                    break;
                case 2:
                    h2 = h(context);
                    break;
                default:
                    TbsLog.e("TbsInstaller", "doDexoptOrDexoat mode error: " + i2);
                    return false;
            }
            String property = System.getProperty("java.vm.version");
            boolean z2 = property != null && property.startsWith("2");
            boolean z3 = Build.VERSION.SDK_INT == 23;
            boolean z4 = TbsDownloadConfig.getInstance(context).mPreferences.getBoolean(TbsDownloadConfig.TbsConfigKey.KEY_STOP_PRE_OAT, false);
            if (z2 && z3 && !z4) {
                z = true;
            }
            if (!z || !c(context, h2)) {
                return b(context, h2);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            TbsLogReport.a(context).a(209, e.toString());
            TbsLog.i("TbsInstaller", "TbsInstaller-doTbsDexOpt done");
            return true;
        }
    }

    private boolean c(Context context, File file) {
        try {
            File file2 = new File(file, "tbs_sdk_extension_dex.jar");
            File file3 = new File(file, "tbs_sdk_extension_dex.dex");
            new DexClassLoader(file2.getAbsolutePath(), file.getAbsolutePath(), null, context.getClassLoader());
            String a2 = c.a(context, file3.getAbsolutePath());
            if (TextUtils.isEmpty(a2)) {
                TbsLogReport.a(context).a(TbsListener.ErrorCode.DEXOAT_EXCEPTION, "can not find oat command");
                return false;
            }
            File[] listFiles = file.listFiles(new aq(this));
            for (File file4 : listFiles) {
                String substring = file4.getName().substring(0, file4.getName().length() - 4);
                Runtime.getRuntime().exec("/system/bin/dex2oat " + a2.replaceAll("tbs_sdk_extension_dex", substring) + " --dex-location=" + a().h(context) + File.separator + substring + ".jar").waitFor();
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            TbsLogReport.a(context).a(TbsListener.ErrorCode.DEXOAT_EXCEPTION, e);
            return false;
        }
    }

    private static boolean c(Context context, String str) {
        File file;
        File file2 = new File(context.getDir("tbs", 0), str);
        return file2 != null && file2.exists() && (file = new File(file2, "tbs.conf")) != null && file.exists();
    }

    private synchronized boolean c(Context context, boolean z) {
        Throwable th;
        boolean z2 = false;
        boolean z3 = true;
        synchronized (this) {
            TbsLog.i("TbsInstaller", "TbsInstaller-enableTbsCoreFromCopy");
            try {
                if (m(context)) {
                    boolean tryLock = h.tryLock();
                    TbsLog.i("TbsInstaller", "TbsInstaller-enableTbsCoreFromCopy Locked =" + tryLock);
                    if (tryLock) {
                        try {
                            int b2 = ae.a(context).b("copy_status");
                            int a2 = a(false, context);
                            TbsLog.i("TbsInstaller", "TbsInstaller-enableTbsCoreFromCopy copyStatus =" + b2);
                            TbsLog.i("TbsInstaller", "TbsInstaller-enableTbsCoreFromCopy tbsCoreInstalledVer =" + a2);
                            try {
                                if (b2 == 1) {
                                    if (a2 == 0) {
                                        TbsLog.i("TbsInstaller", "TbsInstaller-enableTbsCoreFromCopy tbsCoreInstalledVer = 0", true);
                                        p(context);
                                    } else if (z) {
                                        TbsLog.i("TbsInstaller", "TbsInstaller-enableTbsCoreFromCopy tbsCoreInstalledVer != 0", true);
                                        p(context);
                                    }
                                    z2 = z3;
                                }
                                z2 = z3;
                            } catch (Throwable th2) {
                                th = th2;
                                z2 = z3;
                                TbsLogReport.a(context).a(TbsListener.ErrorCode.COPY_EXCEPTION, th.toString());
                                QbSdk.a(context, "TbsInstaller::enableTbsCoreFromCopy exception:" + Log.getStackTraceString(th));
                                return z2;
                            }
                            z3 = false;
                        } finally {
                            h.unlock();
                        }
                    }
                    b();
                }
            } catch (Throwable th3) {
                th = th3;
            }
        }
        return z2;
    }

    private Context d(Context context, int i2) {
        Context a2;
        TbsLog.i("TbsInstaller", "TbsInstaller--getTbsCoreHostContext tbsCoreTargetVer=" + i2);
        if (i2 <= 0) {
            return null;
        }
        String[] coreProviderAppList = TbsShareManager.getCoreProviderAppList();
        for (int i3 = 0; i3 < coreProviderAppList.length; i3++) {
            if (!context.getPackageName().equalsIgnoreCase(coreProviderAppList[i3]) && d(context, coreProviderAppList[i3]) && (a2 = a(context, coreProviderAppList[i3])) != null) {
                if (!c(a2)) {
                    TbsLog.e("TbsInstaller", "TbsInstaller--getTbsCoreHostContext " + coreProviderAppList[i3] + " illegal signature go on next");
                } else {
                    int d = d(a2);
                    TbsLog.i("TbsInstaller", "TbsInstaller-getTbsCoreHostContext hostTbsCoreVer=" + d);
                    if (d != 0 && d == i2) {
                        TbsLog.i("TbsInstaller", "TbsInstaller-getTbsCoreHostContext targetApp=" + coreProviderAppList[i3]);
                        return a2;
                    }
                }
            }
        }
        return null;
    }

    private boolean d(Context context, String str) {
        PackageInfo packageInfo;
        try {
            packageInfo = context.getPackageManager().getPackageInfo(str, 0);
        } catch (PackageManager.NameNotFoundException e) {
            packageInfo = null;
        }
        return packageInfo != null;
    }

    private synchronized boolean d(Context context, boolean z) {
        Exception e;
        boolean z2 = true;
        boolean z3 = false;
        synchronized (this) {
            TbsLog.i("TbsInstaller", "TbsInstaller-enableTbsCoreFromUnzip canRenameTmpDir =" + z);
            try {
                if (m(context)) {
                    boolean tryLock = h.tryLock();
                    TbsLog.i("TbsInstaller", "TbsInstaller-enableTbsCoreFromUnzip locked=" + tryLock);
                    if (tryLock) {
                        try {
                            int c2 = ae.a(context).c();
                            TbsLog.i("TbsInstaller", "TbsInstaller-enableTbsCoreFromUnzip installStatus=" + c2);
                            int a2 = a(false, context);
                            try {
                                if (c2 == 2) {
                                    if (a2 == 0) {
                                        TbsLog.i("TbsInstaller", "TbsInstaller-enableTbsCoreFromUnzip tbsCoreInstalledVer = 0", false);
                                        o(context);
                                    } else if (z) {
                                        TbsLog.i("TbsInstaller", "TbsInstaller-enableTbsCoreFromUnzip tbsCoreInstalledVer != 0", false);
                                        o(context);
                                    }
                                    z3 = z2;
                                }
                                z3 = z2;
                            } catch (Exception e2) {
                                e = e2;
                                z3 = z2;
                                QbSdk.a(context, "TbsInstaller::enableTbsCoreFromUnzip Exception: " + e);
                                e.printStackTrace();
                                return z3;
                            }
                            z2 = false;
                        } finally {
                            h.unlock();
                        }
                    }
                    b();
                }
            } catch (Exception e3) {
                e = e3;
            }
        }
        return z3;
    }

    private synchronized boolean e(Context context, boolean z) {
        return false;
    }

    private void f(Context context, boolean z) {
        if (context == null) {
            TbsLogReport.a(context).a(TbsListener.ErrorCode.CREATE_TEMP_CONF_ERROR, "setTmpFolderCoreToRead context is null");
            return;
        }
        try {
            File file = new File(context.getDir("tbs", 0), "tmp_folder_core_to_read.conf");
            if (!z) {
                k.b(file);
            } else if (file == null || !file.exists()) {
                file.createNewFile();
            }
        } catch (Exception e) {
            TbsLogReport.a(context).a(TbsListener.ErrorCode.CREATE_TEMP_CONF_ERROR, "setTmpFolderCoreToRead Exception message is " + e.getMessage() + " Exception cause is " + e.getCause());
        }
    }

    public static File j(Context context) {
        File file = new File(context.getDir("tbs", 0), "core_private");
        if (file == null) {
            return null;
        }
        if (file.isDirectory() || file.mkdir()) {
            return file;
        }
        return null;
    }

    private static boolean n(Context context) {
        if (context == null) {
            return true;
        }
        try {
            return new File(context.getDir("tbs", 0), "tmp_folder_core_to_read.conf").exists();
        } catch (Exception e) {
            return true;
        }
    }

    private void o(Context context) {
        TbsLog.i("TbsInstaller", "TbsInstaller--generateNewTbsCoreFromUnzip");
        if (bi.b().a() == null && bi.b().a(context) == null) {
            TbsLog.e("TbsInstaller", "generateNewTbsCoreFromUnzip -- failed to get rename fileLock#2!");
            return;
        }
        try {
            q(context);
            r(context);
            if (!TbsShareManager.isThirdPartyApp(context)) {
                TbsShareManager.a(context);
            }
            ae.a(context).a(0);
            ae.a(context).b(0);
            ae.a(context).d(0);
            ae.a(context).a("incrupdate_retry_num", 0);
            ae.a(context).b(0, 3);
            ae.a(context).a("");
            ae.a(context).c(-1);
            if (TbsShareManager.isThirdPartyApp(context)) {
                TbsShareManager.writeCoreInfoForThirdPartyApp(context, f(context), true);
            }
            a.set(0);
        } catch (Throwable th) {
            th.printStackTrace();
            TbsLogReport.a(context).a(TbsListener.ErrorCode.RENAME_EXCEPTION, "exception when renameing from unzip:" + th.toString());
            TbsLog.e("TbsInstaller", "TbsInstaller--generateNewTbsCoreFromUnzip Exception", true);
        }
    }

    private void p(Context context) {
        TbsLog.i("TbsInstaller", "TbsInstaller--generateNewTbsCoreFromCopy");
        if (bi.b().a() == null && bi.b().a(context) == null) {
            TbsLog.e("TbsInstaller", "generateNewTbsCoreFromCopy -- failed to get rename fileLock#2!");
            return;
        }
        try {
            q(context);
            s(context);
            TbsShareManager.a(context);
            ae.a(context).a(0, 3);
            a.set(0);
        } catch (Exception e) {
            e.printStackTrace();
            TbsLogReport.a(context).a(TbsListener.ErrorCode.RENAME_EXCEPTION, "exception when renameing from copy:" + e.toString());
        }
    }

    private void q(Context context) {
        TbsLog.i("TbsInstaller", "TbsInstaller--deleteOldCore");
        k.a(h(context), false);
    }

    private void r(Context context) {
        TbsLog.i("TbsInstaller", "TbsInstaller--renameShareDir");
        File k2 = k(context);
        File h2 = h(context);
        if (k2 != null && h2 != null) {
            k2.renameTo(h2);
            f(context, false);
        }
    }

    private void s(Context context) {
        TbsLog.i("TbsInstaller", "TbsInstaller--renameTbsCoreCopyDir");
        File l2 = l(context);
        File h2 = h(context);
        if (l2 != null && h2 != null) {
            l2.renameTo(h2);
            f(context, false);
        }
    }

    private void t(Context context) {
        TbsLog.i("TbsInstaller", "TbsInstaller--clearNewTbsCore");
        File k2 = k(context);
        if (k2 != null) {
            k.a(k2, false);
        }
        ae.a(context).b(0, 5);
        ae.a(context).c(-1);
        QbSdk.a(context, "TbsInstaller::clearNewTbsCore forceSysWebViewInner!");
    }

    public int a(boolean z, Context context) {
        if (z || a.get().intValue() <= 0) {
            a.set(Integer.valueOf(d(context)));
        }
        return a.get().intValue();
    }

    Context a(Context context, String str) {
        try {
            return context.createPackageContext(str, 2);
        } catch (Exception e) {
            return null;
        }
    }

    void a(Context context, Bundle bundle) {
        if (bundle != null && context != null) {
            Message message = new Message();
            message.what = 3;
            message.obj = new Object[]{context, bundle};
            k.sendMessage(message);
        }
    }

    public void a(Context context, String str, int i2) {
        TbsLog.i("TbsInstaller", "TbsInstaller-installTbsCore tbsApkPath=" + str);
        TbsLog.i("TbsInstaller", "TbsInstaller-installTbsCore tbsCoreTargetVer=" + i2);
        TbsLog.i("TbsInstaller", "TbsInstaller-continueInstallTbsCore currentProcessName=" + context.getApplicationInfo().processName);
        TbsLog.i("TbsInstaller", "TbsInstaller-installTbsCore currentProcessId=" + Process.myPid());
        TbsLog.i("TbsInstaller", "TbsInstaller-installTbsCore currentThreadName=" + Thread.currentThread().getName());
        Object[] objArr = {context, str, Integer.valueOf(i2)};
        Message message = new Message();
        message.what = 1;
        message.obj = objArr;
        k.sendMessage(message);
    }

    public void a(Context context, boolean z) {
        int c2;
        int b2;
        int c3;
        int b3;
        boolean z2 = false;
        if (z) {
            this.j = true;
        }
        TbsLog.i("TbsInstaller", "TbsInstaller-continueInstallTbsCore currentProcessName=" + context.getApplicationInfo().processName);
        TbsLog.i("TbsInstaller", "TbsInstaller-continueInstallTbsCore currentProcessId=" + Process.myPid());
        TbsLog.i("TbsInstaller", "TbsInstaller-continueInstallTbsCore currentThreadName=" + Thread.currentThread().getName());
        if (m(context)) {
            String d = null;
            if (h.tryLock()) {
                try {
                    c2 = ae.a(context).c();
                    b2 = ae.a(context).b();
                    d = ae.a(context).d("install_apk_path");
                    c3 = ae.a(context).c("copy_core_ver");
                    b3 = ae.a(context).b("copy_status");
                } finally {
                    h.unlock();
                }
            } else {
                b3 = -1;
                b2 = 0;
                c2 = -1;
                c3 = 0;
            }
            b();
            TbsLog.i("TbsInstaller", "TbsInstaller-continueInstallTbsCore installStatus=" + c2);
            TbsLog.i("TbsInstaller", "TbsInstaller-continueInstallTbsCore tbsCoreInstallVer=" + b2);
            TbsLog.i("TbsInstaller", "TbsInstaller-continueInstallTbsCore tbsApkPath=" + d);
            TbsLog.i("TbsInstaller", "TbsInstaller-continueInstallTbsCore tbsCoreCopyVer=" + c3);
            TbsLog.i("TbsInstaller", "TbsInstaller-continueInstallTbsCore copyStatus=" + b3);
            if (TbsShareManager.isThirdPartyApp(context)) {
                b(context, TbsShareManager.a(context, false));
                return;
            }
            int i2 = TbsDownloadConfig.getInstance(context).mPreferences.getInt(TbsDownloadConfig.TbsConfigKey.KEY_RESPONSECODE, 0);
            if (i2 == 1 || i2 == 2 || i2 == 4) {
                z2 = true;
            }
            if (!z2) {
                Bundle bundle = new Bundle();
                bundle.putInt("operation", 10001);
                a(context, bundle);
            }
            if (c2 > -1 && c2 < 2) {
                a(context, d, b2);
            }
            if (b3 == 0) {
                a(context, c3);
            }
        }
    }

    public boolean a(Context context, int i2) {
        if (TbsDownloader.getOverSea(context)) {
            return false;
        }
        TbsLog.i("TbsInstaller", "TbsInstaller-installLocalTbsCore targetTbsCoreVer=" + i2);
        TbsLog.i("TbsInstaller", "TbsInstaller-continueInstallTbsCore currentProcessName=" + context.getApplicationInfo().processName);
        TbsLog.i("TbsInstaller", "TbsInstaller-installLocalTbsCore currentProcessId=" + Process.myPid());
        TbsLog.i("TbsInstaller", "TbsInstaller-installLocalTbsCore currentThreadName=" + Thread.currentThread().getName());
        Context d = d(context, i2);
        if (d != null) {
            Object[] objArr = {d, context, Integer.valueOf(i2)};
            Message message = new Message();
            message.what = 2;
            message.obj = objArr;
            k.sendMessage(message);
            return true;
        }
        TbsLog.i("TbsInstaller", "TbsInstaller--installLocalTbsCore copy from null");
        return false;
    }

    public synchronized boolean a(Context context, Context context2) {
        TbsLog.i("TbsInstaller", "TbsInstaller--quickDexOptForThirdPartyApp");
        if (!m) {
            m = true;
            new am(this, context2, context).start();
        }
        return true;
    }

    public boolean a(Context context, File[] fileArr) {
        return false;
    }

    int b(Context context, String str) {
        PackageInfo packageArchiveInfo = context.getPackageManager().getPackageArchiveInfo(str, 0);
        if (packageArchiveInfo != null) {
            return packageArchiveInfo.versionCode;
        }
        return 0;
    }

    public File b(Context context, Context context2) {
        File file = new File(context2.getDir("tbs", 0), "core_share");
        if (file == null) {
            return null;
        }
        if (file.isDirectory() || ((context != null && TbsShareManager.isThirdPartyApp(context)) || file.mkdir())) {
            return file;
        }
        return null;
    }

    synchronized void b() {
        int i2 = this.d;
        this.d = i2 - 1;
        if (i2 > 1 || !this.g) {
            TbsLog.i("TbsInstaller", "releaseTbsInstallingFileLock with skip");
        } else {
            TbsLog.i("TbsInstaller", "releaseTbsInstallingFileLock without skip");
            k.a(this.e, this.f);
            this.g = false;
        }
    }

    void b(Context context, int i2) {
        int d;
        TbsLog.i("TbsInstaller", "TbsInstaller-installTbsCoreForThirdPartyApp");
        if (i2 > 0 && (d = d(context)) != i2) {
            Context d2 = TbsShareManager.d(context);
            if (d2 != null) {
                TbsLog.i("TbsInstaller", "TbsInstaller--quickDexOptForThirdPartyApp hostContext != null");
                a(context, d2);
            } else if (d <= 0) {
                TbsLog.i("TbsInstaller", "TbsInstaller--installTbsCoreForThirdPartyApp hostContext == null");
                QbSdk.a(context, "TbsInstaller::installTbsCoreForThirdPartyApp forceSysWebViewInner #2");
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:48:0x0185  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x023c  */
    /* JADX WARN: Removed duplicated region for block: B:62:0x028d  */
    /* JADX WARN: Removed duplicated region for block: B:65:0x02d6  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void b(android.content.Context r11, android.os.Bundle r12) {
        /*
            Method dump skipped, instructions count: 814
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.sdk.aj.b(android.content.Context, android.os.Bundle):void");
    }

    public void b(Context context, boolean z) {
        if (!QbSdk.b) {
            if (Build.VERSION.SDK_INT < 8) {
                TbsLog.e("TbsInstaller", "android version < 2.1 no need install X5 core", true);
            } else if (!n(context)) {
            } else {
                if (c(context, "core_unzip_tmp") && d(context, z)) {
                    TbsLog.i("TbsInstaller", "TbsInstaller-installTbsCoreIfNeeded, enableTbsCoreFromUnzip!!", true);
                } else if (c(context, "core_share_backup_tmp") && e(context, z)) {
                    TbsLog.i("TbsInstaller", "TbsInstaller-installTbsCoreIfNeeded, enableTbsCoreFromBackup!!", true);
                } else if (c(context, "core_copy_tmp") && c(context, z)) {
                    TbsLog.i("TbsInstaller", "TbsInstaller-installTbsCoreIfNeeded, enableTbsCoreFromCopy!!", true);
                } else {
                    TbsLog.i("TbsInstaller", "TbsInstaller-installTbsCoreIfNeeded, error !!", true);
                }
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:56:0x0098 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean b(android.content.Context r11) {
        /*
            r10 = this;
            r1 = 1
            r2 = 0
            java.io.File r0 = r10.h(r11)
            java.io.File r3 = new java.io.File
            java.lang.String r4 = "tbs.conf"
            r3.<init>(r0, r4)
            if (r3 == 0) goto L_0x0015
            boolean r4 = r3.exists()
            if (r4 != 0) goto L_0x0017
        L_0x0015:
            r0 = r2
        L_0x0016:
            return r0
        L_0x0017:
            java.util.Properties r5 = new java.util.Properties
            r5.<init>()
            r4 = 0
            java.io.FileInputStream r6 = new java.io.FileInputStream     // Catch: Throwable -> 0x0082, all -> 0x0094
            r6.<init>(r3)     // Catch: Throwable -> 0x0082, all -> 0x0094
            java.io.BufferedInputStream r3 = new java.io.BufferedInputStream     // Catch: Throwable -> 0x0082, all -> 0x0094
            r3.<init>(r6)     // Catch: Throwable -> 0x0082, all -> 0x0094
            r5.load(r3)     // Catch: Throwable -> 0x00a6, all -> 0x00a1
            java.lang.String r4 = "tbs_local_installation"
            java.lang.String r6 = "false"
            java.lang.String r4 = r5.getProperty(r4, r6)     // Catch: Throwable -> 0x00a6, all -> 0x00a1
            java.lang.Boolean r4 = java.lang.Boolean.valueOf(r4)     // Catch: Throwable -> 0x00a6, all -> 0x00a1
            boolean r4 = r4.booleanValue()     // Catch: Throwable -> 0x00a6, all -> 0x00a1
            if (r4 == 0) goto L_0x00b0
            long r6 = java.lang.System.currentTimeMillis()     // Catch: Throwable -> 0x00ab, all -> 0x00a1
            long r8 = r0.lastModified()     // Catch: Throwable -> 0x00ab, all -> 0x00a1
            long r6 = r6 - r8
            r8 = 259200000(0xf731400, double:1.280618154E-315)
            int r0 = (r6 > r8 ? 1 : (r6 == r8 ? 0 : -1))
            if (r0 <= 0) goto L_0x007e
            r0 = r1
        L_0x004d:
            java.lang.String r5 = "TbsInstaller"
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch: Throwable -> 0x00ab, all -> 0x00a1
            r6.<init>()     // Catch: Throwable -> 0x00ab, all -> 0x00a1
            java.lang.String r7 = "TBS_LOCAL_INSTALLATION is:"
            java.lang.StringBuilder r6 = r6.append(r7)     // Catch: Throwable -> 0x00ab, all -> 0x00a1
            java.lang.StringBuilder r6 = r6.append(r4)     // Catch: Throwable -> 0x00ab, all -> 0x00a1
            java.lang.String r7 = " expired="
            java.lang.StringBuilder r6 = r6.append(r7)     // Catch: Throwable -> 0x00ab, all -> 0x00a1
            java.lang.StringBuilder r6 = r6.append(r0)     // Catch: Throwable -> 0x00ab, all -> 0x00a1
            java.lang.String r6 = r6.toString()     // Catch: Throwable -> 0x00ab, all -> 0x00a1
            com.tencent.smtt.utils.TbsLog.i(r5, r6)     // Catch: Throwable -> 0x00ab, all -> 0x00a1
            if (r0 != 0) goto L_0x0080
        L_0x0071:
            r0 = r4 & r1
            if (r3 == 0) goto L_0x0016
            r3.close()     // Catch: IOException -> 0x0079
            goto L_0x0016
        L_0x0079:
            r1 = move-exception
            r1.printStackTrace()
            goto L_0x0016
        L_0x007e:
            r0 = r2
            goto L_0x004d
        L_0x0080:
            r1 = r2
            goto L_0x0071
        L_0x0082:
            r0 = move-exception
            r1 = r0
            r0 = r2
            r2 = r4
        L_0x0086:
            r1.printStackTrace()     // Catch: all -> 0x00a3
            if (r2 == 0) goto L_0x0016
            r2.close()     // Catch: IOException -> 0x008f
            goto L_0x0016
        L_0x008f:
            r1 = move-exception
            r1.printStackTrace()
            goto L_0x0016
        L_0x0094:
            r0 = move-exception
            r3 = r4
        L_0x0096:
            if (r3 == 0) goto L_0x009b
            r3.close()     // Catch: IOException -> 0x009c
        L_0x009b:
            throw r0
        L_0x009c:
            r1 = move-exception
            r1.printStackTrace()
            goto L_0x009b
        L_0x00a1:
            r0 = move-exception
            goto L_0x0096
        L_0x00a3:
            r0 = move-exception
            r3 = r2
            goto L_0x0096
        L_0x00a6:
            r0 = move-exception
            r1 = r0
            r0 = r2
            r2 = r3
            goto L_0x0086
        L_0x00ab:
            r0 = move-exception
            r1 = r0
            r2 = r3
            r0 = r4
            goto L_0x0086
        L_0x00b0:
            r0 = r2
            goto L_0x004d
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.sdk.aj.b(android.content.Context):boolean");
    }

    /* JADX WARN: Code restructure failed: missing block: B:11:0x0043, code lost:
        if (r1.toCharsString().equals("308202eb30820254a00302010202044d36f7a4300d06092a864886f70d01010505003081b9310b300906035504061302383631123010060355040813094775616e67646f6e673111300f060355040713085368656e7a68656e31353033060355040a132c54656e63656e7420546563686e6f6c6f6779285368656e7a68656e2920436f6d70616e79204c696d69746564313a3038060355040b133154656e63656e74204775616e677a686f7520526573656172636820616e6420446576656c6f706d656e742043656e7465723110300e0603550403130754656e63656e74301e170d3131303131393134333933325a170d3431303131313134333933325a3081b9310b300906035504061302383631123010060355040813094775616e67646f6e673111300f060355040713085368656e7a68656e31353033060355040a132c54656e63656e7420546563686e6f6c6f6779285368656e7a68656e2920436f6d70616e79204c696d69746564313a3038060355040b133154656e63656e74204775616e677a686f7520526573656172636820616e6420446576656c6f706d656e742043656e7465723110300e0603550403130754656e63656e7430819f300d06092a864886f70d010101050003818d0030818902818100c05f34b231b083fb1323670bfbe7bdab40c0c0a6efc87ef2072a1ff0d60cc67c8edb0d0847f210bea6cbfaa241be70c86daf56be08b723c859e52428a064555d80db448cdcacc1aea2501eba06f8bad12a4fa49d85cacd7abeb68945a5cb5e061629b52e3254c373550ee4e40cb7c8ae6f7a8151ccd8df582d446f39ae0c5e930203010001300d06092a864886f70d0101050500038181009c8d9d7f2f908c42081b4c764c377109a8b2c70582422125ce545842d5f520aea69550b6bd8bfd94e987b75a3077eb04ad341f481aac266e89d3864456e69fba13df018acdc168b9a19dfd7ad9d9cc6f6ace57c746515f71234df3a053e33ba93ece5cd0fc15f3e389a3f365588a9fcb439e069d3629cd7732a13fff7b891499") != false) goto L_0x0045;
     */
    /* JADX WARN: Code restructure failed: missing block: B:31:0x00a8, code lost:
        if (r1.toCharsString().equals("30820239308201a2a00302010202044c96f48f300d06092a864886f70d01010505003060310b300906035504061302434e310b300906035504081302474431123010060355040713094775616e677a686f753110300e060355040a130754656e63656e74310b3009060355040b130233473111300f0603550403130857696c736f6e57753020170d3130303932303035343334335a180f32303635303632333035343334335a3060310b300906035504061302434e310b300906035504081302474431123010060355040713094775616e677a686f753110300e060355040a130754656e63656e74310b3009060355040b130233473111300f0603550403130857696c736f6e577530819f300d06092a864886f70d010101050003818d0030818902818100b56e79dbb1185a79e52d792bb3d0bb3da8010d9b87da92ec69f7dc5ad66ab6bfdff2a6a1ed285dd2358f28b72a468be7c10a2ce30c4c27323ed4edcc936080e5bedc2cbbca0b7e879c08a631182793f44bb3ea284179b263410c298e5f6831032c9702ba4a74e2ccfc9ef857f12201451602fc8e774ac59d6398511586c83d1d0203010001300d06092a864886f70d0101050500038181002475615bb65b8d8786b890535802948840387d06b1692ff3ea47ef4c435719ba1865b81e6bfa6293ce31747c3cd6b34595b485cc1563fd90107ba5845c28b95c79138f0dec288940395bc10f92f2b69d8dc410999deb38900974ce9984b678030edfba8816582f56160d87e38641288d8588d2a31e20b89f223d788dd35cc9c8") == false) goto L_0x002c;
     */
    /* JADX WARN: Code restructure failed: missing block: B:6:0x002a, code lost:
        if (r1.toCharsString().equals("3082023f308201a8a00302010202044c46914a300d06092a864886f70d01010505003064310b30090603550406130238363110300e060355040813074265696a696e673110300e060355040713074265696a696e673110300e060355040a130754656e63656e74310c300a060355040b13035753443111300f0603550403130873616d75656c6d6f301e170d3130303732313036313835305a170d3430303731333036313835305a3064310b30090603550406130238363110300e060355040813074265696a696e673110300e060355040713074265696a696e673110300e060355040a130754656e63656e74310c300a060355040b13035753443111300f0603550403130873616d75656c6d6f30819f300d06092a864886f70d010101050003818d0030818902818100c209077044bd0d63ea00ede5b839914cabcc912a87f0f8b390877e0f7a2583f0d5933443c40431c35a4433bc4c965800141961adc44c9625b1d321385221fd097e5bdc2f44a1840d643ab59dc070cf6c4b4b4d98bed5cbb8046e0a7078ae134da107cdf2bfc9b440fe5cb2f7549b44b73202cc6f7c2c55b8cfb0d333a021f01f0203010001300d06092a864886f70d010105050003818100b007db9922774ef4ccfee81ba514a8d57c410257e7a2eba64bfa17c9e690da08106d32f637ac41fbc9f205176c71bde238c872c3ee2f8313502bee44c80288ea4ef377a6f2cdfe4d3653c145c4acfedbfbadea23b559d41980cc3cdd35d79a68240693739aabf5c5ed26148756cf88264226de394c8a24ac35b712b120d4d23a") == false) goto L_0x002c;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean c(android.content.Context r5) {
        /*
            r4 = this;
            r0 = 0
            android.content.pm.PackageManager r1 = r5.getPackageManager()     // Catch: Exception -> 0x00ab
            java.lang.String r2 = r5.getPackageName()     // Catch: Exception -> 0x00ab
            r3 = 64
            android.content.pm.PackageInfo r1 = r1.getPackageInfo(r2, r3)     // Catch: Exception -> 0x00ab
            android.content.pm.Signature[] r1 = r1.signatures     // Catch: Exception -> 0x00ab
            r2 = 0
            r1 = r1[r2]     // Catch: Exception -> 0x00ab
            java.lang.String r2 = r5.getPackageName()     // Catch: Exception -> 0x00ab
            java.lang.String r3 = "com.tencent.mtt"
            boolean r2 = r2.equals(r3)     // Catch: Exception -> 0x00ab
            if (r2 == 0) goto L_0x002d
            java.lang.String r1 = r1.toCharsString()     // Catch: Exception -> 0x00ab
            java.lang.String r2 = "3082023f308201a8a00302010202044c46914a300d06092a864886f70d01010505003064310b30090603550406130238363110300e060355040813074265696a696e673110300e060355040713074265696a696e673110300e060355040a130754656e63656e74310c300a060355040b13035753443111300f0603550403130873616d75656c6d6f301e170d3130303732313036313835305a170d3430303731333036313835305a3064310b30090603550406130238363110300e060355040813074265696a696e673110300e060355040713074265696a696e673110300e060355040a130754656e63656e74310c300a060355040b13035753443111300f0603550403130873616d75656c6d6f30819f300d06092a864886f70d010101050003818d0030818902818100c209077044bd0d63ea00ede5b839914cabcc912a87f0f8b390877e0f7a2583f0d5933443c40431c35a4433bc4c965800141961adc44c9625b1d321385221fd097e5bdc2f44a1840d643ab59dc070cf6c4b4b4d98bed5cbb8046e0a7078ae134da107cdf2bfc9b440fe5cb2f7549b44b73202cc6f7c2c55b8cfb0d333a021f01f0203010001300d06092a864886f70d010105050003818100b007db9922774ef4ccfee81ba514a8d57c410257e7a2eba64bfa17c9e690da08106d32f637ac41fbc9f205176c71bde238c872c3ee2f8313502bee44c80288ea4ef377a6f2cdfe4d3653c145c4acfedbfbadea23b559d41980cc3cdd35d79a68240693739aabf5c5ed26148756cf88264226de394c8a24ac35b712b120d4d23a"
            boolean r1 = r1.equals(r2)     // Catch: Exception -> 0x00ab
            if (r1 != 0) goto L_0x0045
        L_0x002c:
            return r0
        L_0x002d:
            java.lang.String r2 = r5.getPackageName()     // Catch: Exception -> 0x00ab
            java.lang.String r3 = "com.tencent.mm"
            boolean r2 = r2.equals(r3)     // Catch: Exception -> 0x00ab
            if (r2 == 0) goto L_0x0047
            java.lang.String r1 = r1.toCharsString()     // Catch: Exception -> 0x00ab
            java.lang.String r2 = "308202eb30820254a00302010202044d36f7a4300d06092a864886f70d01010505003081b9310b300906035504061302383631123010060355040813094775616e67646f6e673111300f060355040713085368656e7a68656e31353033060355040a132c54656e63656e7420546563686e6f6c6f6779285368656e7a68656e2920436f6d70616e79204c696d69746564313a3038060355040b133154656e63656e74204775616e677a686f7520526573656172636820616e6420446576656c6f706d656e742043656e7465723110300e0603550403130754656e63656e74301e170d3131303131393134333933325a170d3431303131313134333933325a3081b9310b300906035504061302383631123010060355040813094775616e67646f6e673111300f060355040713085368656e7a68656e31353033060355040a132c54656e63656e7420546563686e6f6c6f6779285368656e7a68656e2920436f6d70616e79204c696d69746564313a3038060355040b133154656e63656e74204775616e677a686f7520526573656172636820616e6420446576656c6f706d656e742043656e7465723110300e0603550403130754656e63656e7430819f300d06092a864886f70d010101050003818d0030818902818100c05f34b231b083fb1323670bfbe7bdab40c0c0a6efc87ef2072a1ff0d60cc67c8edb0d0847f210bea6cbfaa241be70c86daf56be08b723c859e52428a064555d80db448cdcacc1aea2501eba06f8bad12a4fa49d85cacd7abeb68945a5cb5e061629b52e3254c373550ee4e40cb7c8ae6f7a8151ccd8df582d446f39ae0c5e930203010001300d06092a864886f70d0101050500038181009c8d9d7f2f908c42081b4c764c377109a8b2c70582422125ce545842d5f520aea69550b6bd8bfd94e987b75a3077eb04ad341f481aac266e89d3864456e69fba13df018acdc168b9a19dfd7ad9d9cc6f6ace57c746515f71234df3a053e33ba93ece5cd0fc15f3e389a3f365588a9fcb439e069d3629cd7732a13fff7b891499"
            boolean r1 = r1.equals(r2)     // Catch: Exception -> 0x00ab
            if (r1 == 0) goto L_0x002c
        L_0x0045:
            r0 = 1
            goto L_0x002c
        L_0x0047:
            java.lang.String r2 = r5.getPackageName()     // Catch: Exception -> 0x00ab
            java.lang.String r3 = "com.tencent.mobileqq"
            boolean r2 = r2.equals(r3)     // Catch: Exception -> 0x00ab
            if (r2 == 0) goto L_0x0060
            java.lang.String r1 = r1.toCharsString()     // Catch: Exception -> 0x00ab
            java.lang.String r2 = "30820253308201bca00302010202044bbb0361300d06092a864886f70d0101050500306d310e300c060355040613054368696e61310f300d06035504080c06e58c97e4baac310f300d06035504070c06e58c97e4baac310f300d060355040a0c06e885bee8aeaf311b3019060355040b0c12e697a0e7babfe4b89ae58aa1e7b3bbe7bb9f310b30090603550403130251513020170d3130303430363039343831375a180f32323834303132303039343831375a306d310e300c060355040613054368696e61310f300d06035504080c06e58c97e4baac310f300d06035504070c06e58c97e4baac310f300d060355040a0c06e885bee8aeaf311b3019060355040b0c12e697a0e7babfe4b89ae58aa1e7b3bbe7bb9f310b300906035504031302515130819f300d06092a864886f70d010101050003818d0030818902818100a15e9756216f694c5915e0b529095254367c4e64faeff07ae13488d946615a58ddc31a415f717d019edc6d30b9603d3e2a7b3de0ab7e0cf52dfee39373bc472fa997027d798d59f81d525a69ecf156e885fd1e2790924386b2230cc90e3b7adc95603ddcf4c40bdc72f22db0f216a99c371d3bf89cba6578c60699e8a0d536950203010001300d06092a864886f70d01010505000381810094a9b80e80691645dd42d6611775a855f71bcd4d77cb60a8e29404035a5e00b21bcc5d4a562482126bd91b6b0e50709377ceb9ef8c2efd12cc8b16afd9a159f350bb270b14204ff065d843832720702e28b41491fbc3a205f5f2f42526d67f17614d8a974de6487b2c866efede3b4e49a0f916baa3c1336fd2ee1b1629652049"
            boolean r1 = r1.equals(r2)     // Catch: Exception -> 0x00ab
            if (r1 != 0) goto L_0x0045
            goto L_0x002c
        L_0x0060:
            java.lang.String r2 = r5.getPackageName()     // Catch: Exception -> 0x00ab
            java.lang.String r3 = "com.tencent.tbs"
            boolean r2 = r2.equals(r3)     // Catch: Exception -> 0x00ab
            if (r2 == 0) goto L_0x0079
            java.lang.String r1 = r1.toCharsString()     // Catch: Exception -> 0x00ab
            java.lang.String r2 = "3082023f308201a8a00302010202044c46914a300d06092a864886f70d01010505003064310b30090603550406130238363110300e060355040813074265696a696e673110300e060355040713074265696a696e673110300e060355040a130754656e63656e74310c300a060355040b13035753443111300f0603550403130873616d75656c6d6f301e170d3130303732313036313835305a170d3430303731333036313835305a3064310b30090603550406130238363110300e060355040813074265696a696e673110300e060355040713074265696a696e673110300e060355040a130754656e63656e74310c300a060355040b13035753443111300f0603550403130873616d75656c6d6f30819f300d06092a864886f70d010101050003818d0030818902818100c209077044bd0d63ea00ede5b839914cabcc912a87f0f8b390877e0f7a2583f0d5933443c40431c35a4433bc4c965800141961adc44c9625b1d321385221fd097e5bdc2f44a1840d643ab59dc070cf6c4b4b4d98bed5cbb8046e0a7078ae134da107cdf2bfc9b440fe5cb2f7549b44b73202cc6f7c2c55b8cfb0d333a021f01f0203010001300d06092a864886f70d010105050003818100b007db9922774ef4ccfee81ba514a8d57c410257e7a2eba64bfa17c9e690da08106d32f637ac41fbc9f205176c71bde238c872c3ee2f8313502bee44c80288ea4ef377a6f2cdfe4d3653c145c4acfedbfbadea23b559d41980cc3cdd35d79a68240693739aabf5c5ed26148756cf88264226de394c8a24ac35b712b120d4d23a"
            boolean r1 = r1.equals(r2)     // Catch: Exception -> 0x00ab
            if (r1 != 0) goto L_0x0045
            goto L_0x002c
        L_0x0079:
            java.lang.String r2 = r5.getPackageName()     // Catch: Exception -> 0x00ab
            java.lang.String r3 = "com.qzone"
            boolean r2 = r2.equals(r3)     // Catch: Exception -> 0x00ab
            if (r2 == 0) goto L_0x0092
            java.lang.String r1 = r1.toCharsString()     // Catch: Exception -> 0x00ab
            java.lang.String r2 = "308202ad30820216a00302010202044c26cea2300d06092a864886f70d010105050030819a310b3009060355040613023836311530130603550408130c4265696a696e672043697479311530130603550407130c4265696a696e67204369747931263024060355040a131d515a6f6e65205465616d206f662054656e63656e7420436f6d70616e7931183016060355040b130f54656e63656e7420436f6d70616e79311b301906035504031312416e64726f696420515a6f6e65205465616d301e170d3130303632373034303830325a170d3335303632313034303830325a30819a310b3009060355040613023836311530130603550408130c4265696a696e672043697479311530130603550407130c4265696a696e67204369747931263024060355040a131d515a6f6e65205465616d206f662054656e63656e7420436f6d70616e7931183016060355040b130f54656e63656e7420436f6d70616e79311b301906035504031312416e64726f696420515a6f6e65205465616d30819f300d06092a864886f70d010101050003818d003081890281810082d6aca037a9843fbbe88b6dd19f36e9c24ce174c1b398f3a529e2a7fe02de99c27539602c026edf96ad8d43df32a85458bca1e6fbf11958658a7d6751a1d9b782bf43a8c19bd1c06bdbfd94c0516326ae3cf638ac42bb470580e340c46e6f306a772c1ef98f10a559edf867f3f31fe492808776b7bd953b2cba2d2b2d66a44f0203010001300d06092a864886f70d0101050500038181006003b04a8a8c5be9650f350cda6896e57dd13e6e83e7f891fc70f6a3c2eaf75cfa4fc998365deabbd1b9092159edf4b90df5702a0d101f8840b5d4586eb92a1c3cd19d95fbc1c2ac956309eda8eef3944baf08c4a49d3b9b3ffb06bc13dab94ecb5b8eb74e8789aa0ba21cb567f538bbc59c2a11e6919924a24272eb79251677"
            boolean r1 = r1.equals(r2)     // Catch: Exception -> 0x00ab
            if (r1 != 0) goto L_0x0045
            goto L_0x002c
        L_0x0092:
            java.lang.String r2 = r5.getPackageName()     // Catch: Exception -> 0x00ab
            java.lang.String r3 = "com.tencent.qqpimsecure"
            boolean r2 = r2.equals(r3)     // Catch: Exception -> 0x00ab
            if (r2 == 0) goto L_0x0045
            java.lang.String r1 = r1.toCharsString()     // Catch: Exception -> 0x00ab
            java.lang.String r2 = "30820239308201a2a00302010202044c96f48f300d06092a864886f70d01010505003060310b300906035504061302434e310b300906035504081302474431123010060355040713094775616e677a686f753110300e060355040a130754656e63656e74310b3009060355040b130233473111300f0603550403130857696c736f6e57753020170d3130303932303035343334335a180f32303635303632333035343334335a3060310b300906035504061302434e310b300906035504081302474431123010060355040713094775616e677a686f753110300e060355040a130754656e63656e74310b3009060355040b130233473111300f0603550403130857696c736f6e577530819f300d06092a864886f70d010101050003818d0030818902818100b56e79dbb1185a79e52d792bb3d0bb3da8010d9b87da92ec69f7dc5ad66ab6bfdff2a6a1ed285dd2358f28b72a468be7c10a2ce30c4c27323ed4edcc936080e5bedc2cbbca0b7e879c08a631182793f44bb3ea284179b263410c298e5f6831032c9702ba4a74e2ccfc9ef857f12201451602fc8e774ac59d6398511586c83d1d0203010001300d06092a864886f70d0101050500038181002475615bb65b8d8786b890535802948840387d06b1692ff3ea47ef4c435719ba1865b81e6bfa6293ce31747c3cd6b34595b485cc1563fd90107ba5845c28b95c79138f0dec288940395bc10f92f2b69d8dc410999deb38900974ce9984b678030edfba8816582f56160d87e38641288d8588d2a31e20b89f223d788dd35cc9c8"
            boolean r1 = r1.equals(r2)     // Catch: Exception -> 0x00ab
            if (r1 != 0) goto L_0x0045
            goto L_0x002c
        L_0x00ab:
            r1 = move-exception
            java.lang.String r1 = "TbsInstaller"
            java.lang.String r2 = "TbsInstaller-installLocalTbsCore getPackageInfo fail"
            com.tencent.smtt.utils.TbsLog.i(r1, r2)
            goto L_0x002c
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.sdk.aj.c(android.content.Context):boolean");
    }

    public int d(Context context) {
        BufferedInputStream bufferedInputStream;
        int i2;
        BufferedInputStream bufferedInputStream2;
        Exception e;
        String str;
        String str2;
        File file;
        try {
            i2 = 0;
            bufferedInputStream2 = null;
        } catch (Throwable th) {
            th = th;
        }
        try {
            file = new File(h(context), "tbs.conf");
        } catch (Exception e2) {
            e = e2;
            bufferedInputStream = null;
        } catch (Throwable th2) {
            th = th2;
            bufferedInputStream = null;
            if (bufferedInputStream != null) {
                try {
                    bufferedInputStream.close();
                } catch (IOException e3) {
                    TbsLog.i("TbsInstaller", "TbsInstaller--getTbsCoreInstalledVerInNolock IOException=" + e3.toString());
                }
            }
            throw th;
        }
        if (file == null || !file.exists()) {
            if (0 != 0) {
                try {
                    bufferedInputStream2.close();
                } catch (IOException e4) {
                    str = "TbsInstaller";
                    str2 = "TbsInstaller--getTbsCoreInstalledVerInNolock IOException=" + e4.toString();
                }
            }
            return i2;
        }
        Properties properties = new Properties();
        bufferedInputStream = new BufferedInputStream(new FileInputStream(file));
        try {
            properties.load(bufferedInputStream);
            bufferedInputStream.close();
            String property = properties.getProperty("tbs_core_version");
            if (property != null) {
                i2 = Integer.parseInt(property);
                if (bufferedInputStream != null) {
                    try {
                        bufferedInputStream.close();
                    } catch (IOException e5) {
                        str = "TbsInstaller";
                        str2 = "TbsInstaller--getTbsCoreInstalledVerInNolock IOException=" + e5.toString();
                    }
                }
            } else if (bufferedInputStream != null) {
                try {
                    bufferedInputStream.close();
                } catch (IOException e6) {
                    str = "TbsInstaller";
                    str2 = "TbsInstaller--getTbsCoreInstalledVerInNolock IOException=" + e6.toString();
                }
            }
        } catch (Exception e7) {
            e = e7;
            TbsLog.i("TbsInstaller", "TbsInstaller--getTbsCoreInstalledVerInNolock Exception=" + e.toString());
            if (bufferedInputStream != null) {
                try {
                    bufferedInputStream.close();
                } catch (IOException e8) {
                    str = "TbsInstaller";
                    str2 = "TbsInstaller--getTbsCoreInstalledVerInNolock IOException=" + e8.toString();
                }
            }
            return i2;
        }
        return i2;
        TbsLog.i(str, str2);
        return i2;
    }

    public boolean e(Context context) {
        File file = new File(h(context), "tbs.conf");
        return file != null && file.exists();
    }

    /* JADX WARN: Removed duplicated region for block: B:52:0x00d2 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public int f(android.content.Context r7) {
        /*
            Method dump skipped, instructions count: 387
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.sdk.aj.f(android.content.Context):int");
    }

    public void g(Context context) {
        TbsLog.i("TbsInstaller", "TbsInstaller--cleanStatusAndTmpDir");
        ae.a(context).a(0);
        ae.a(context).b(0);
        ae.a(context).d(0);
        ae.a(context).a("incrupdate_retry_num", 0);
        ae.a(context).b(0, -1);
        ae.a(context).a("");
        ae.a(context).a("copy_retry_num", 0);
        ae.a(context).a(0, -1);
        ae.a(context).c(-1);
        k.a(k(context), true);
        k.a(l(context), true);
    }

    public File h(Context context) {
        return b((Context) null, context);
    }

    public File i(Context context) {
        File file = new File(context.getDir("tbs", 0), "share");
        if (file == null) {
            return null;
        }
        if (file.isDirectory() || file.mkdir()) {
            return file;
        }
        return null;
    }

    File k(Context context) {
        File file = new File(context.getDir("tbs", 0), "core_unzip_tmp");
        if (file == null) {
            return null;
        }
        if (file.isDirectory() || file.mkdir()) {
            return file;
        }
        return null;
    }

    File l(Context context) {
        File file = new File(context.getDir("tbs", 0), "core_copy_tmp");
        if (file == null) {
            return null;
        }
        if (file.isDirectory() || file.mkdir()) {
            return file;
        }
        return null;
    }

    synchronized boolean m(Context context) {
        boolean z = true;
        synchronized (this) {
            this.d++;
            if (this.g) {
                TbsLog.i("TbsInstaller", "getTbsInstallingFileLock success,is cached= true");
            } else {
                this.f = k.b(context, true, "tbslock.txt");
                if (this.f != null) {
                    this.e = k.a(context, this.f);
                    if (this.e == null) {
                        z = false;
                    } else {
                        TbsLog.i("TbsInstaller", "getTbsInstallingFileLock success,is cached= false");
                        this.g = true;
                    }
                } else {
                    z = false;
                }
            }
        }
        return z;
    }
}
