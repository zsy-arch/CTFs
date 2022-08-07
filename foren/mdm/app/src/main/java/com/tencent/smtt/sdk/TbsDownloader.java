package com.tencent.smtt.sdk;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.text.TextUtils;
import com.tencent.smtt.sdk.TbsDownloadConfig;
import com.tencent.smtt.sdk.TbsLogReport;
import com.tencent.smtt.utils.TbsLog;
import com.tencent.smtt.utils.a;
import com.tencent.smtt.utils.b;
import com.tencent.smtt.utils.k;
import com.tencent.smtt.utils.n;
import com.tencent.smtt.utils.x;
import java.io.File;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public class TbsDownloader {
    public static final boolean DEBUG_DISABLE_DOWNLOAD = false;
    public static final String LOGTAG = "TbsDownload";
    static boolean a;
    private static String b;
    private static Context c;
    private static Handler d;
    private static String e;
    private static ac g;
    private static HandlerThread h;
    public static boolean DOWNLOAD_OVERSEA_TBS = false;
    private static Object f = new byte[0];
    private static boolean i = false;
    private static boolean j = false;
    private static boolean k = false;

    /* loaded from: classes2.dex */
    public interface TbsDownloaderCallback {
        void onNeedDownloadFinish(boolean z, int i);
    }

    public static File a(int i2) {
        String[] coreProviderAppList = TbsShareManager.getCoreProviderAppList();
        File file = null;
        int length = coreProviderAppList.length;
        int i3 = 0;
        while (true) {
            if (i3 >= length) {
                break;
            }
            String str = coreProviderAppList[i3];
            if (!str.equals(c.getApplicationInfo().packageName)) {
                file = new File(k.a(c, str, 4, false), getOverSea(c) ? "x5.oversea.tbs.org" : "x5.tbs.org");
                if (file == null || !file.exists()) {
                    TbsLog.i(LOGTAG, "can not find local backup core file");
                } else if (a.a(c, file) == i2) {
                    TbsLog.i(LOGTAG, "local tbs version fond,path = " + file.getAbsolutePath());
                    break;
                } else {
                    TbsLog.i(LOGTAG, "version is not match");
                }
            }
            i3++;
        }
        return file;
    }

    public static String a(Context context) {
        String str;
        String str2;
        if (!TextUtils.isEmpty(b)) {
            return b;
        }
        Locale locale = Locale.getDefault();
        StringBuffer stringBuffer = new StringBuffer();
        String str3 = Build.VERSION.RELEASE;
        try {
            str = new String(str3.getBytes("UTF-8"), "ISO8859-1");
        } catch (Exception e2) {
            str = str3;
        }
        if (str.length() > 0) {
            stringBuffer.append(str);
        } else {
            stringBuffer.append("1.0");
        }
        stringBuffer.append("; ");
        String language = locale.getLanguage();
        if (language != null) {
            stringBuffer.append(language.toLowerCase());
            String country = locale.getCountry();
            if (country != null) {
                stringBuffer.append("-");
                stringBuffer.append(country.toLowerCase());
            }
        } else {
            stringBuffer.append("en");
        }
        if ("REL".equals(Build.VERSION.CODENAME)) {
            String str4 = Build.MODEL;
            try {
                str2 = new String(str4.getBytes("UTF-8"), "ISO8859-1");
            } catch (Exception e3) {
                str2 = str4;
            }
            if (str2.length() > 0) {
                stringBuffer.append("; ");
                stringBuffer.append(str2);
            }
        }
        String replaceAll = Build.ID.replaceAll("[一-龥]", "");
        if (replaceAll.length() > 0) {
            stringBuffer.append(" Build/");
            stringBuffer.append(replaceAll);
        }
        String format = String.format("Mozilla/5.0 (Linux; U; Android %s) AppleWebKit/533.1 (KHTML, like Gecko)Version/4.0 Mobile Safari/533.1", stringBuffer);
        b = format;
        return format;
    }

    private static String a(String str) {
        return str == null ? "" : str;
    }

    private static void a(boolean z, TbsDownloaderCallback tbsDownloaderCallback) {
        int i2 = 0;
        TbsLog.i(LOGTAG, "[TbsDownloader.queryConfig]");
        d.removeMessages(100);
        Message obtain = Message.obtain(d, 100);
        if (tbsDownloaderCallback != null) {
            obtain.obj = tbsDownloaderCallback;
        }
        obtain.arg1 = 0;
        if (z) {
            i2 = 1;
        }
        obtain.arg1 = i2;
        obtain.sendToTarget();
    }

    private static boolean a(Context context, boolean z, TbsDownloaderCallback tbsDownloaderCallback) {
        Matcher matcher = null;
        TbsDownloadConfig instance = TbsDownloadConfig.getInstance(context);
        if (Build.VERSION.SDK_INT < 8) {
            instance.setDownloadInterruptCode(-102);
            return false;
        } else if (QbSdk.c || !TbsShareManager.isThirdPartyApp(c) || c()) {
            if (!instance.mPreferences.contains(TbsDownloadConfig.TbsConfigKey.KEY_IS_OVERSEA)) {
                if (z && !"com.tencent.mm".equals(context.getApplicationInfo().packageName)) {
                    TbsLog.i(LOGTAG, "needDownload-oversea is true, but not WX");
                    z = false;
                }
                instance.a.put(TbsDownloadConfig.TbsConfigKey.KEY_IS_OVERSEA, Boolean.valueOf(z));
                instance.commit();
                j = z;
                TbsLog.i(LOGTAG, "needDownload-first-called--isoversea = " + z);
            }
            if (!getOverSea(context) || Build.VERSION.SDK_INT == 16 || Build.VERSION.SDK_INT == 17 || Build.VERSION.SDK_INT == 18) {
                e = instance.mPreferences.getString(TbsDownloadConfig.TbsConfigKey.KEY_DEVICE_CPUABI, null);
                if (!TextUtils.isEmpty(e)) {
                    try {
                        matcher = Pattern.compile("i686|mips|x86_64").matcher(e);
                    } catch (Exception e2) {
                    }
                    if (matcher != null && matcher.find()) {
                        if (tbsDownloaderCallback != null) {
                            tbsDownloaderCallback.onNeedDownloadFinish(false, 0);
                        }
                        instance.setDownloadInterruptCode(-104);
                        return false;
                    }
                }
                return true;
            }
            TbsLog.i(LOGTAG, "needDownload- return false,  because of  version is " + Build.VERSION.SDK_INT + ", and overea");
            if (tbsDownloaderCallback != null) {
                tbsDownloaderCallback.onNeedDownloadFinish(false, 0);
            }
            instance.setDownloadInterruptCode(-103);
            return false;
        } else if (tbsDownloaderCallback == null) {
            return false;
        } else {
            tbsDownloaderCallback.onNeedDownloadFinish(false, 0);
            return false;
        }
    }

    private static boolean a(Context context, boolean z, boolean z2) {
        boolean z3;
        String str;
        TbsDownloadConfig instance = TbsDownloadConfig.getInstance(context);
        if (!z) {
            String string = instance.mPreferences.getString(TbsDownloadConfig.TbsConfigKey.KEY_APP_VERSIONNAME, null);
            int i2 = instance.mPreferences.getInt(TbsDownloadConfig.TbsConfigKey.KEY_APP_VERSIONCODE, 0);
            String string2 = instance.mPreferences.getString(TbsDownloadConfig.TbsConfigKey.KEY_APP_METADATA, null);
            String a2 = b.a(c);
            int b2 = b.b(c);
            String a3 = b.a(c, "com.tencent.mm.BuildInfo.CLIENT_VERSION");
            TbsLog.i(LOGTAG, "[TbsDownloader.needSendQueryRequest] appVersionName=" + a2 + " oldAppVersionName=" + string + " appVersionCode=" + b2 + " oldAppVersionCode=" + i2 + " appMetadata=" + a3 + " oldAppVersionMetadata=" + string2);
            long currentTimeMillis = System.currentTimeMillis();
            long j2 = instance.mPreferences.getLong(TbsDownloadConfig.TbsConfigKey.KEY_LAST_CHECK, 0L);
            TbsLog.i(LOGTAG, "[TbsDownloader.needSendQueryRequest] timeLastCheck=" + j2 + " timeNow=" + currentTimeMillis);
            if (z2) {
                boolean contains = instance.mPreferences.contains(TbsDownloadConfig.TbsConfigKey.KEY_LAST_CHECK);
                TbsLog.i(LOGTAG, "[TbsDownloader.needSendQueryRequest] hasLaskCheckKey=" + contains);
                if (contains && j2 == 0) {
                    j2 = currentTimeMillis;
                }
            }
            long retryInterval = instance.getRetryInterval();
            TbsLog.i(LOGTAG, "retryInterval = " + retryInterval + " s");
            if (currentTimeMillis - j2 > retryInterval * 1000) {
                z3 = true;
                str = null;
            } else if (TbsShareManager.isThirdPartyApp(c) && TbsShareManager.findCoreForThirdPartyApp(c) == 0 && !e()) {
                z3 = true;
                k.b(c.getDir("tbs", 0));
                aj.a.set(0);
                str = null;
            } else if (a2 == null || b2 == 0 || a3 == null) {
                if (TbsShareManager.isThirdPartyApp(c)) {
                    str = "timeNow - timeLastCheck is " + (currentTimeMillis - j2) + " TbsShareManager.findCoreForThirdPartyApp(sAppContext) is " + TbsShareManager.findCoreForThirdPartyApp(c) + " sendRequestWithSameHostCoreVersion() is " + e() + " appVersionName is " + a2 + " appVersionCode is " + b2 + " appMetadata is " + a3 + " oldAppVersionName is " + string + " oldAppVersionCode is " + i2 + " oldAppVersionMetadata is " + string2;
                    z3 = false;
                }
                str = null;
                z3 = false;
            } else {
                if (!a2.equals(string) || b2 != i2 || !a3.equals(string2)) {
                    z3 = true;
                    str = null;
                }
                str = null;
                z3 = false;
            }
        } else {
            z3 = true;
            str = null;
        }
        if (!z3 && TbsShareManager.isThirdPartyApp(c)) {
            TbsLogReport.a(c).h(-119);
            TbsLogReport.a(c).e(str);
            TbsLogReport.a(c).a(TbsLogReport.EventType.TYPE_DOWNLOAD);
        }
        return z3;
    }

    /* JADX WARN: Code restructure failed: missing block: B:60:0x0188, code lost:
        if (r2 > 0) goto L_0x018a;
     */
    /* JADX WARN: Removed duplicated region for block: B:30:0x00f1  */
    @android.annotation.TargetApi(11)
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static boolean a(java.lang.String r28, int r29, boolean r30, boolean r31) {
        /*
            Method dump skipped, instructions count: 1096
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.sdk.TbsDownloader.a(java.lang.String, int, boolean, boolean):boolean");
    }

    /* JADX WARN: Can't wrap try/catch for region: R(27:2|(1:4)(1:84)|(2:87|5)|(22:7|(1:83)|9|85|10|(2:12|(1:14)(1:52))(4:53|(1:58)|59|(3:61|(1:64)|63)(1:82))|(1:16)(3:65|(1:67)(1:71)|68)|17|(2:19|(1:21))(2:72|(1:78))|22|(1:24)|25|(1:(3:28|(1:30)(1:79)|31)(1:80))|32|(1:81)(1:36)|(1:39)|(1:41)|42|(1:44)|(1:46)|47|48)|51|(0)|9|85|10|(0)(0)|(0)(0)|17|(0)(0)|22|(0)|25|(0)|32|(1:34)|81|(1:39)|(0)|42|(0)|(0)|47|48) */
    /* JADX WARN: Removed duplicated region for block: B:12:0x0062 A[Catch: Exception -> 0x020c, TryCatch #0 {Exception -> 0x020c, blocks: (B:10:0x004a, B:12:0x0062, B:14:0x0066, B:16:0x0070, B:17:0x0076, B:19:0x007e, B:21:0x0099, B:22:0x009f, B:24:0x00eb, B:25:0x00f6, B:28:0x0127, B:31:0x0132, B:32:0x0135, B:34:0x014f, B:36:0x0153, B:41:0x0160, B:42:0x0166, B:44:0x016e, B:46:0x0176, B:52:0x01a0, B:53:0x01b2, B:56:0x01c0, B:59:0x01cd, B:61:0x01f1, B:68:0x0207, B:72:0x0211, B:74:0x021e, B:78:0x0228, B:80:0x0232), top: B:85:0x004a }] */
    /* JADX WARN: Removed duplicated region for block: B:16:0x0070 A[Catch: Exception -> 0x020c, TryCatch #0 {Exception -> 0x020c, blocks: (B:10:0x004a, B:12:0x0062, B:14:0x0066, B:16:0x0070, B:17:0x0076, B:19:0x007e, B:21:0x0099, B:22:0x009f, B:24:0x00eb, B:25:0x00f6, B:28:0x0127, B:31:0x0132, B:32:0x0135, B:34:0x014f, B:36:0x0153, B:41:0x0160, B:42:0x0166, B:44:0x016e, B:46:0x0176, B:52:0x01a0, B:53:0x01b2, B:56:0x01c0, B:59:0x01cd, B:61:0x01f1, B:68:0x0207, B:72:0x0211, B:74:0x021e, B:78:0x0228, B:80:0x0232), top: B:85:0x004a }] */
    /* JADX WARN: Removed duplicated region for block: B:19:0x007e A[Catch: Exception -> 0x020c, TryCatch #0 {Exception -> 0x020c, blocks: (B:10:0x004a, B:12:0x0062, B:14:0x0066, B:16:0x0070, B:17:0x0076, B:19:0x007e, B:21:0x0099, B:22:0x009f, B:24:0x00eb, B:25:0x00f6, B:28:0x0127, B:31:0x0132, B:32:0x0135, B:34:0x014f, B:36:0x0153, B:41:0x0160, B:42:0x0166, B:44:0x016e, B:46:0x0176, B:52:0x01a0, B:53:0x01b2, B:56:0x01c0, B:59:0x01cd, B:61:0x01f1, B:68:0x0207, B:72:0x0211, B:74:0x021e, B:78:0x0228, B:80:0x0232), top: B:85:0x004a }] */
    /* JADX WARN: Removed duplicated region for block: B:24:0x00eb A[Catch: Exception -> 0x020c, TryCatch #0 {Exception -> 0x020c, blocks: (B:10:0x004a, B:12:0x0062, B:14:0x0066, B:16:0x0070, B:17:0x0076, B:19:0x007e, B:21:0x0099, B:22:0x009f, B:24:0x00eb, B:25:0x00f6, B:28:0x0127, B:31:0x0132, B:32:0x0135, B:34:0x014f, B:36:0x0153, B:41:0x0160, B:42:0x0166, B:44:0x016e, B:46:0x0176, B:52:0x01a0, B:53:0x01b2, B:56:0x01c0, B:59:0x01cd, B:61:0x01f1, B:68:0x0207, B:72:0x0211, B:74:0x021e, B:78:0x0228, B:80:0x0232), top: B:85:0x004a }] */
    /* JADX WARN: Removed duplicated region for block: B:27:0x0125  */
    /* JADX WARN: Removed duplicated region for block: B:41:0x0160 A[Catch: Exception -> 0x020c, TryCatch #0 {Exception -> 0x020c, blocks: (B:10:0x004a, B:12:0x0062, B:14:0x0066, B:16:0x0070, B:17:0x0076, B:19:0x007e, B:21:0x0099, B:22:0x009f, B:24:0x00eb, B:25:0x00f6, B:28:0x0127, B:31:0x0132, B:32:0x0135, B:34:0x014f, B:36:0x0153, B:41:0x0160, B:42:0x0166, B:44:0x016e, B:46:0x0176, B:52:0x01a0, B:53:0x01b2, B:56:0x01c0, B:59:0x01cd, B:61:0x01f1, B:68:0x0207, B:72:0x0211, B:74:0x021e, B:78:0x0228, B:80:0x0232), top: B:85:0x004a }] */
    /* JADX WARN: Removed duplicated region for block: B:44:0x016e A[Catch: Exception -> 0x020c, TryCatch #0 {Exception -> 0x020c, blocks: (B:10:0x004a, B:12:0x0062, B:14:0x0066, B:16:0x0070, B:17:0x0076, B:19:0x007e, B:21:0x0099, B:22:0x009f, B:24:0x00eb, B:25:0x00f6, B:28:0x0127, B:31:0x0132, B:32:0x0135, B:34:0x014f, B:36:0x0153, B:41:0x0160, B:42:0x0166, B:44:0x016e, B:46:0x0176, B:52:0x01a0, B:53:0x01b2, B:56:0x01c0, B:59:0x01cd, B:61:0x01f1, B:68:0x0207, B:72:0x0211, B:74:0x021e, B:78:0x0228, B:80:0x0232), top: B:85:0x004a }] */
    /* JADX WARN: Removed duplicated region for block: B:46:0x0176 A[Catch: Exception -> 0x020c, TRY_LEAVE, TryCatch #0 {Exception -> 0x020c, blocks: (B:10:0x004a, B:12:0x0062, B:14:0x0066, B:16:0x0070, B:17:0x0076, B:19:0x007e, B:21:0x0099, B:22:0x009f, B:24:0x00eb, B:25:0x00f6, B:28:0x0127, B:31:0x0132, B:32:0x0135, B:34:0x014f, B:36:0x0153, B:41:0x0160, B:42:0x0166, B:44:0x016e, B:46:0x0176, B:52:0x01a0, B:53:0x01b2, B:56:0x01c0, B:59:0x01cd, B:61:0x01f1, B:68:0x0207, B:72:0x0211, B:74:0x021e, B:78:0x0228, B:80:0x0232), top: B:85:0x004a }] */
    /* JADX WARN: Removed duplicated region for block: B:53:0x01b2 A[Catch: Exception -> 0x020c, TryCatch #0 {Exception -> 0x020c, blocks: (B:10:0x004a, B:12:0x0062, B:14:0x0066, B:16:0x0070, B:17:0x0076, B:19:0x007e, B:21:0x0099, B:22:0x009f, B:24:0x00eb, B:25:0x00f6, B:28:0x0127, B:31:0x0132, B:32:0x0135, B:34:0x014f, B:36:0x0153, B:41:0x0160, B:42:0x0166, B:44:0x016e, B:46:0x0176, B:52:0x01a0, B:53:0x01b2, B:56:0x01c0, B:59:0x01cd, B:61:0x01f1, B:68:0x0207, B:72:0x0211, B:74:0x021e, B:78:0x0228, B:80:0x0232), top: B:85:0x004a }] */
    /* JADX WARN: Removed duplicated region for block: B:65:0x0202  */
    /* JADX WARN: Removed duplicated region for block: B:72:0x0211 A[Catch: Exception -> 0x020c, TryCatch #0 {Exception -> 0x020c, blocks: (B:10:0x004a, B:12:0x0062, B:14:0x0066, B:16:0x0070, B:17:0x0076, B:19:0x007e, B:21:0x0099, B:22:0x009f, B:24:0x00eb, B:25:0x00f6, B:28:0x0127, B:31:0x0132, B:32:0x0135, B:34:0x014f, B:36:0x0153, B:41:0x0160, B:42:0x0166, B:44:0x016e, B:46:0x0176, B:52:0x01a0, B:53:0x01b2, B:56:0x01c0, B:59:0x01cd, B:61:0x01f1, B:68:0x0207, B:72:0x0211, B:74:0x021e, B:78:0x0228, B:80:0x0232), top: B:85:0x004a }] */
    /* JADX WARN: Removed duplicated region for block: B:83:0x0240  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static org.json.JSONObject b(boolean r13, boolean r14) {
        /*
            Method dump skipped, instructions count: 582
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.sdk.TbsDownloader.b(boolean, boolean):org.json.JSONObject");
    }

    @TargetApi(11)
    public static void b(Context context) {
        TbsDownloadConfig.getInstance(context).clear();
        TbsLogReport.a(context).d();
        ac.c(context);
        (Build.VERSION.SDK_INT >= 11 ? context.getSharedPreferences("tbs_extension_config", 4) : context.getSharedPreferences("tbs_extension_config", 0)).edit().clear().commit();
        (Build.VERSION.SDK_INT >= 11 ? context.getSharedPreferences("tbs_preloadx5_check_cfg_file", 4) : context.getSharedPreferences("tbs_preloadx5_check_cfg_file", 0)).edit().clear().commit();
    }

    private static boolean c() {
        try {
            for (String str : TbsShareManager.getCoreProviderAppList()) {
                if (TbsShareManager.getSharedTbsCoreVersion(c, str) > 0) {
                    return true;
                }
            }
            return false;
        } catch (Exception e2) {
            e2.printStackTrace();
            return false;
        }
    }

    public static boolean c(boolean z, boolean z2) {
        int i2;
        boolean z3;
        TbsLog.i(LOGTAG, "[TbsDownloader.sendRequest]isQuery: " + z);
        if (aj.a().b(c)) {
            TbsLog.i(LOGTAG, "[TbsDownloader.sendRequest] -- isTbsLocalInstalled!");
            return false;
        }
        TbsDownloadConfig instance = TbsDownloadConfig.getInstance(c);
        File file = new File(k.a(c, 1), getOverSea(c) ? "x5.oversea.tbs.org" : "x5.tbs.org");
        File file2 = new File(k.a(c, 2), getOverSea(c) ? "x5.oversea.tbs.org" : "x5.tbs.org");
        File file3 = new File(k.a(c, 3), getOverSea(c) ? "x5.oversea.tbs.org" : "x5.tbs.org");
        File file4 = new File(k.a(c, 4), getOverSea(c) ? "x5.oversea.tbs.org" : "x5.tbs.org");
        if (!file4.exists()) {
            if (file3.exists()) {
                file3.renameTo(file4);
            } else if (file2.exists()) {
                file2.renameTo(file4);
            } else if (file.exists()) {
                file.renameTo(file4);
            }
        }
        instance.a.put(TbsDownloadConfig.TbsConfigKey.KEY_LAST_CHECK, Long.valueOf(System.currentTimeMillis()));
        instance.a.put(TbsDownloadConfig.TbsConfigKey.KEY_APP_VERSIONNAME, b.a(c));
        instance.a.put(TbsDownloadConfig.TbsConfigKey.KEY_APP_VERSIONCODE, Integer.valueOf(b.b(c)));
        instance.a.put(TbsDownloadConfig.TbsConfigKey.KEY_APP_METADATA, b.a(c, "com.tencent.mm.BuildInfo.CLIENT_VERSION"));
        instance.commit();
        if (e == null) {
            e = b.a();
            instance.a.put(TbsDownloadConfig.TbsConfigKey.KEY_DEVICE_CPUABI, e);
            instance.commit();
        }
        if (!TextUtils.isEmpty(e)) {
            Matcher matcher = null;
            try {
                matcher = Pattern.compile("i686|mips|x86_64").matcher(e);
            } catch (Exception e2) {
            }
            if (matcher != null && matcher.find()) {
                if (TbsShareManager.isThirdPartyApp(c)) {
                    if (z) {
                        instance.setDownloadInterruptCode(-104);
                        TbsLogReport.a(c).h(-104);
                    } else {
                        instance.setDownloadInterruptCode(-205);
                        TbsLogReport.a(c).h(-205);
                    }
                    TbsLogReport.a(c).e("mycpu is " + e);
                    TbsLogReport.a(c).a(TbsLogReport.EventType.TYPE_DOWNLOAD);
                    return false;
                } else if (z) {
                    instance.setDownloadInterruptCode(-104);
                    return false;
                } else {
                    instance.setDownloadInterruptCode(-205);
                    return false;
                }
            }
        }
        JSONObject b2 = b(z, z2);
        try {
            i2 = b2.getInt("TBSV");
        } catch (Exception e3) {
            i2 = -1;
        }
        if (i2 != -1) {
            try {
                String d2 = x.a(c).d();
                TbsLog.i(LOGTAG, "[TbsDownloader.sendRequest] postUrl=" + d2);
                z3 = a(n.a(d2, b2.toString().getBytes("utf-8"), new ag(z, instance), false), i2, z, z2);
            } catch (Throwable th) {
                th.printStackTrace();
                if (z) {
                    instance.setDownloadInterruptCode(-106);
                    z3 = false;
                } else {
                    instance.setDownloadInterruptCode(-206);
                }
            }
            return z3;
        }
        z3 = false;
        return z3;
    }

    private static synchronized void d() {
        synchronized (TbsDownloader.class) {
            if (h == null) {
                h = ah.a();
                try {
                    g = new ac(c);
                    d = new af(h.getLooper());
                } catch (Exception e2) {
                    i = true;
                    TbsLog.e(LOGTAG, "TbsApkDownloader init has Exception");
                }
            }
        }
    }

    private static boolean e() {
        try {
            return TbsDownloadConfig.getInstance(c).mPreferences.getString(TbsDownloadConfig.TbsConfigKey.KEY_LAST_THIRDAPP_SENDREQUEST_COREVERSION, "").equals(f().toString());
        } catch (Exception e2) {
            return false;
        }
    }

    private static JSONArray f() {
        String[] strArr;
        boolean z;
        if (!TbsShareManager.isThirdPartyApp(c)) {
            return null;
        }
        JSONArray jSONArray = new JSONArray();
        String[] coreProviderAppList = TbsShareManager.getCoreProviderAppList();
        String packageName = c.getApplicationContext().getPackageName();
        if (packageName.equals(TbsShareManager.e(c))) {
            int length = coreProviderAppList.length;
            strArr = new String[length + 1];
            System.arraycopy(coreProviderAppList, 0, strArr, 0, length);
            strArr[length] = packageName;
        } else {
            strArr = coreProviderAppList;
        }
        for (String str : strArr) {
            int sharedTbsCoreVersion = TbsShareManager.getSharedTbsCoreVersion(c, str);
            if (sharedTbsCoreVersion > 0) {
                Context a2 = TbsShareManager.a(c, str);
                if (a2 == null || aj.a().c(a2)) {
                    int i2 = 0;
                    while (true) {
                        if (i2 >= jSONArray.length()) {
                            z = false;
                            break;
                        } else if (jSONArray.optInt(i2) == sharedTbsCoreVersion) {
                            z = true;
                            break;
                        } else {
                            i2++;
                        }
                    }
                    if (!z) {
                        jSONArray.put(sharedTbsCoreVersion);
                    }
                } else {
                    TbsLog.e(LOGTAG, "host check failed,packageName = " + str);
                }
            }
        }
        return jSONArray;
    }

    private static boolean g() {
        TbsDownloadConfig instance = TbsDownloadConfig.getInstance(c);
        if (instance.mPreferences.getInt(TbsDownloadConfig.TbsConfigKey.KEY_DOWNLOAD_SUCCESS_RETRYTIMES, 0) >= instance.getDownloadSuccessMaxRetrytimes()) {
            TbsLog.i(LOGTAG, "[TbsDownloader.needStartDownload] out of success retrytimes", true);
            instance.setDownloadInterruptCode(-115);
            return false;
        } else if (instance.mPreferences.getInt(TbsDownloadConfig.TbsConfigKey.KEY_DOWNLOAD_FAILED_RETRYTIMES, 0) >= instance.getDownloadFailedMaxRetrytimes()) {
            TbsLog.i(LOGTAG, "[TbsDownloader.needStartDownload] out of failed retrytimes", true);
            instance.setDownloadInterruptCode(-116);
            return false;
        } else if (!k.b(c)) {
            TbsLog.i(LOGTAG, "[TbsDownloader.needStartDownload] local rom freespace limit", true);
            instance.setDownloadInterruptCode(-117);
            return false;
        } else {
            if (System.currentTimeMillis() - instance.mPreferences.getLong(TbsDownloadConfig.TbsConfigKey.KEY_TBSDOWNLOAD_STARTTIME, 0L) <= com.umeng.analytics.a.j) {
                long j2 = instance.mPreferences.getLong(TbsDownloadConfig.TbsConfigKey.KEY_TBSDOWNLOAD_FLOW, 0L);
                TbsLog.i(LOGTAG, "[TbsDownloader.needStartDownload] downloadFlow=" + j2);
                if (j2 >= instance.getDownloadMaxflow()) {
                    TbsLog.i(LOGTAG, "[TbsDownloader.needStartDownload] failed because you exceeded max flow!", true);
                    instance.setDownloadInterruptCode(-120);
                    return false;
                }
            }
            return true;
        }
    }

    public static synchronized boolean getOverSea(Context context) {
        boolean z;
        synchronized (TbsDownloader.class) {
            if (!k) {
                k = true;
                TbsDownloadConfig instance = TbsDownloadConfig.getInstance(context);
                if (instance.mPreferences.contains(TbsDownloadConfig.TbsConfigKey.KEY_IS_OVERSEA)) {
                    j = instance.mPreferences.getBoolean(TbsDownloadConfig.TbsConfigKey.KEY_IS_OVERSEA, false);
                    TbsLog.i(LOGTAG, "[TbsDownloader.getOverSea]  first called. sOverSea = " + j);
                }
                TbsLog.i(LOGTAG, "[TbsDownloader.getOverSea]  sOverSea = " + j);
            }
            z = j;
        }
        return z;
    }

    public static HandlerThread getsTbsHandlerThread() {
        return h;
    }

    private static JSONArray h() {
        boolean z;
        JSONArray jSONArray = new JSONArray();
        for (String str : TbsShareManager.getCoreProviderAppList()) {
            File file = new File(k.a(c, str, 4, false), getOverSea(c) ? "x5.oversea.tbs.org" : "x5.tbs.org");
            if (file != null && file.exists()) {
                long a2 = a.a(c, file);
                if (a2 > 0) {
                    int i2 = 0;
                    while (true) {
                        if (i2 >= jSONArray.length()) {
                            z = false;
                            break;
                        } else if (jSONArray.optInt(i2) == a2) {
                            z = true;
                            break;
                        } else {
                            i2++;
                        }
                    }
                    if (!z) {
                        jSONArray.put(a2);
                    }
                }
            }
        }
        return jSONArray;
    }

    public static boolean isDownloadForeground() {
        return g != null && g.d();
    }

    public static synchronized boolean isDownloading() {
        boolean z;
        synchronized (TbsDownloader.class) {
            TbsLog.i(LOGTAG, "[TbsDownloader.isDownloading]");
            z = a;
        }
        return z;
    }

    public static boolean needDownload(Context context, boolean z) {
        return needDownload(context, z, false, null);
    }

    public static boolean needDownload(Context context, boolean z, boolean z2, TbsDownloaderCallback tbsDownloaderCallback) {
        boolean z3;
        TbsLog.initIfNeed(context);
        if (!aj.b) {
            TbsLog.app_extra(LOGTAG, context);
            c = context.getApplicationContext();
            TbsDownloadConfig instance = TbsDownloadConfig.getInstance(c);
            instance.setDownloadInterruptCode(-100);
            if (!a(c, z, tbsDownloaderCallback)) {
                return false;
            }
            d();
            if (i) {
                if (tbsDownloaderCallback != null) {
                    tbsDownloaderCallback.onNeedDownloadFinish(false, 0);
                }
                instance.setDownloadInterruptCode(-105);
                return false;
            }
            boolean a2 = a(c, z2, false);
            if (a2) {
                a(z2, tbsDownloaderCallback);
                instance.setDownloadInterruptCode(-114);
            }
            d.removeMessages(102);
            Message.obtain(d, 102).sendToTarget();
            if (QbSdk.c || !TbsShareManager.isThirdPartyApp(context)) {
                boolean contains = instance.mPreferences.contains(TbsDownloadConfig.TbsConfigKey.KEY_NEEDDOWNLOAD);
                TbsLog.i(LOGTAG, "[TbsDownloader.needDownload] hasNeedDownloadKey=" + contains);
                z3 = (contains || TbsShareManager.isThirdPartyApp(context)) ? instance.mPreferences.getBoolean(TbsDownloadConfig.TbsConfigKey.KEY_NEEDDOWNLOAD, false) : true;
            } else {
                z3 = false;
            }
            if (!z3) {
                int f2 = aj.a().f(c);
                if (a2 || f2 <= 0) {
                    d.removeMessages(103);
                    if (f2 > 0 || a2) {
                        Message.obtain(d, 103, 1, 0, c).sendToTarget();
                    } else {
                        Message.obtain(d, 103, 0, 0, c).sendToTarget();
                    }
                    instance.setDownloadInterruptCode(-121);
                } else {
                    instance.setDownloadInterruptCode(-119);
                }
            } else if (!g()) {
                z3 = false;
            } else {
                instance.setDownloadInterruptCode(-118);
            }
            if (!a2 && tbsDownloaderCallback != null) {
                tbsDownloaderCallback.onNeedDownloadFinish(false, 0);
            }
            TbsLog.i(LOGTAG, "[TbsDownloader.needDownload] needDownload=" + z3);
            return z3;
        } else if (tbsDownloaderCallback == null) {
            return false;
        } else {
            tbsDownloaderCallback.onNeedDownloadFinish(false, 0);
            return false;
        }
    }

    public static boolean needSendRequest(Context context, boolean z) {
        boolean z2 = true;
        c = context.getApplicationContext();
        TbsLog.initIfNeed(context);
        if (!a(c, z, (TbsDownloaderCallback) null)) {
            return false;
        }
        int f2 = aj.a().f(context);
        TbsLog.i(LOGTAG, "[TbsDownloader.needSendRequest] localTbsVersion=" + f2);
        if (f2 > 0) {
            return false;
        }
        if (a(c, false, true)) {
            return true;
        }
        TbsDownloadConfig instance = TbsDownloadConfig.getInstance(c);
        boolean contains = instance.mPreferences.contains(TbsDownloadConfig.TbsConfigKey.KEY_NEEDDOWNLOAD);
        TbsLog.i(LOGTAG, "[TbsDownloader.needSendRequest] hasNeedDownloadKey=" + contains);
        boolean z3 = !contains ? true : instance.mPreferences.getBoolean(TbsDownloadConfig.TbsConfigKey.KEY_NEEDDOWNLOAD, false);
        TbsLog.i(LOGTAG, "[TbsDownloader.needSendRequest] needDownload=" + z3);
        if (!z3 || !g()) {
            z2 = false;
        }
        TbsLog.i(LOGTAG, "[TbsDownloader.needSendRequest] ret=" + z2);
        return z2;
    }

    public static void startDownload(Context context) {
        startDownload(context, false);
    }

    public static synchronized void startDownload(Context context, boolean z) {
        int i2 = 1;
        synchronized (TbsDownloader.class) {
            TbsLog.i(LOGTAG, "[TbsDownloader.startDownload] sAppContext=" + c);
            if (!aj.b) {
                a = true;
                c = context.getApplicationContext();
                TbsDownloadConfig.getInstance(c).setDownloadInterruptCode(-200);
                if (Build.VERSION.SDK_INT < 8) {
                    QbSdk.j.onDownloadFinish(110);
                    TbsDownloadConfig.getInstance(c).setDownloadInterruptCode(-201);
                } else {
                    d();
                    if (i) {
                        QbSdk.j.onDownloadFinish(121);
                        TbsDownloadConfig.getInstance(c).setDownloadInterruptCode(-202);
                    } else {
                        if (z) {
                            stopDownload();
                        }
                        d.removeMessages(101);
                        d.removeMessages(100);
                        Message obtain = Message.obtain(d, 101, QbSdk.j);
                        if (!z) {
                            i2 = 0;
                        }
                        obtain.arg1 = i2;
                        obtain.sendToTarget();
                    }
                }
            }
        }
    }

    public static void stopDownload() {
        if (!i) {
            TbsLog.i(LOGTAG, "[TbsDownloader.stopDownload]");
            if (g != null) {
                g.b();
            }
            if (d != null) {
                d.removeMessages(100);
                d.removeMessages(101);
            }
        }
    }
}
