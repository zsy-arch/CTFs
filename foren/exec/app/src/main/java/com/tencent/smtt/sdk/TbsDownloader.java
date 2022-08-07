package com.tencent.smtt.sdk;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Log;
import com.tencent.smtt.sdk.TbsDownloadConfig;
import com.tencent.smtt.sdk.TbsDownloadUpload;
import com.tencent.smtt.sdk.TbsListener;
import com.tencent.smtt.utils.Apn;
import com.tencent.smtt.utils.FileUtil;
import com.tencent.smtt.utils.TbsLog;
import com.tencent.smtt.utils.a;
import com.tencent.smtt.utils.b;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.channels.FileLock;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class TbsDownloader {
    public static final boolean DEBUG_DISABLE_DOWNLOAD = false;
    public static boolean DOWNLOAD_OVERSEA_TBS = false;
    public static final String LOGTAG = "TbsDownload";
    public static final String TBS_METADATA = "com.tencent.mm.BuildInfo.CLIENT_VERSION";

    /* renamed from: a */
    public static boolean f1218a;

    /* renamed from: b */
    public static String f1219b;

    /* renamed from: c */
    public static Context f1220c;

    /* renamed from: d */
    public static Handler f1221d;

    /* renamed from: e */
    public static String f1222e;
    public static j g;
    public static HandlerThread h;
    public static Object f = new byte[0];
    public static boolean i = false;
    public static boolean j = false;
    public static boolean k = false;
    public static long l = -1;

    /* loaded from: classes.dex */
    public interface TbsDownloaderCallback {
        void onNeedDownloadFinish(boolean z, int i);
    }

    public static File a(int i2) {
        String str;
        String[] coreProviderAppList = TbsShareManager.getCoreProviderAppList();
        int length = coreProviderAppList.length;
        File file = null;
        int i3 = 0;
        while (true) {
            if (i3 >= length) {
                break;
            }
            String str2 = coreProviderAppList[i3];
            if (!str2.equals(f1220c.getApplicationInfo().packageName)) {
                file = new File(FileUtil.a(f1220c, str2, 4, false), getOverSea(f1220c) ? "x5.oversea.tbs.org" : getBackupFileName(false));
                if (!file.exists()) {
                    str = "can not find local backup core file";
                } else if (a.a(f1220c, file) == i2) {
                    StringBuilder a2 = e.a.a.a.a.a("local tbs version fond,path = ");
                    a2.append(file.getAbsolutePath());
                    TbsLog.i(LOGTAG, a2.toString());
                    break;
                } else {
                    str = "version is not match";
                }
                TbsLog.i(LOGTAG, str);
            }
            i3++;
        }
        return file;
    }

    public static String a(String str) {
        return str == null ? BuildConfig.FLAVOR : str;
    }

    public static JSONArray a(boolean z) {
        boolean z2;
        JSONArray jSONArray = new JSONArray();
        String[] coreProviderAppList = TbsShareManager.getCoreProviderAppList();
        for (String str : coreProviderAppList) {
            File file = z ? new File(FileUtil.a(f1220c, str, 4, false), getOverSea(f1220c) ? "x5.oversea.tbs.org" : getBackupFileName(false)) : new File(FileUtil.a(f1220c, str, 4, false), "x5.tbs.decouple");
            if (file.exists()) {
                long a2 = a.a(f1220c, file);
                if (a2 > 0) {
                    int i2 = 0;
                    while (true) {
                        if (i2 >= jSONArray.length()) {
                            z2 = false;
                            break;
                        } else if (jSONArray.optInt(i2) == a2) {
                            z2 = true;
                            break;
                        } else {
                            i2++;
                        }
                    }
                    if (!z2) {
                        jSONArray.put(a2);
                    }
                }
            }
        }
        return jSONArray;
    }

    public static JSONObject a(boolean z, boolean z2, boolean z3) {
        int i2;
        int i3;
        int i4;
        int i5;
        TbsLog.i(LOGTAG, "[TbsDownloader.postJsonData]isQuery: " + z + " forDecoupleCore is " + z3);
        TbsDownloadConfig instance = TbsDownloadConfig.getInstance(f1220c);
        String b2 = b(f1220c);
        String e2 = b.e(f1220c);
        String d2 = b.d(f1220c);
        String g2 = b.g(f1220c);
        String id = TimeZone.getDefault().getID();
        String str = id != null ? id : BuildConfig.FLAVOR;
        try {
            TelephonyManager telephonyManager = (TelephonyManager) f1220c.getSystemService("phone");
            if (telephonyManager != null) {
                id = telephonyManager.getSimCountryIso();
            }
        } catch (Exception e3) {
            e3.printStackTrace();
        }
        if (id == null) {
            id = BuildConfig.FLAVOR;
        }
        JSONObject jSONObject = new JSONObject();
        try {
            boolean z4 = false;
            if (k.a(f1220c).c("tpatch_num") < 5) {
                jSONObject.put("REQUEST_TPATCH", 1);
            } else {
                jSONObject.put("REQUEST_TPATCH", 0);
            }
            jSONObject.put("TIMEZONEID", str);
            jSONObject.put("COUNTRYISO", id);
            if (b.c()) {
                i2 = 1;
                jSONObject.put("REQUEST_64", 1);
            } else {
                i2 = 1;
            }
            jSONObject.put("PROTOCOLVERSION", i2);
            if (!TbsShareManager.isThirdPartyApp(f1220c)) {
                i3 = z3 ? m.a().i(f1220c) : m.a().m(f1220c);
                if (i3 == 0 && m.a().l(f1220c)) {
                    i3 = -1;
                    if (TbsConfig.APP_QQ.equals(f1220c.getApplicationInfo().packageName)) {
                        TbsDownloadUpload.clear();
                        TbsDownloadUpload instance2 = TbsDownloadUpload.getInstance(f1220c);
                        instance2.f1214a.put(TbsDownloadUpload.TbsUploadKey.KEY_LOCAL_CORE_VERSION, -1);
                        instance2.commit();
                        TbsPVConfig.releaseInstance();
                        if (TbsPVConfig.getInstance(f1220c).getLocalCoreVersionMoreTimes() == 1) {
                            i3 = m.a().i(f1220c);
                        }
                    }
                }
                TbsLog.i(LOGTAG, "[TbsDownloader.postJsonData] tbsLocalVersion=" + i3 + " isDownloadForeground=" + z2);
                if (z2 && !m.a().l(f1220c)) {
                    i3 = 0;
                }
            } else if (QbSdk.f1129c) {
                TbsShareManager.b(f1220c, false);
                i3 = TbsShareManager.f1267e;
            } else {
                i3 = TbsDownloadConfig.getInstance(f1220c).mPreferences.getInt(TbsDownloadConfig.TbsConfigKey.KEY_TBS_DOWNLOAD_V, 0);
            }
            jSONObject.put("FUNCTION", z ? 2 : i3 == 0 ? 0 : 1);
            if (TbsShareManager.isThirdPartyApp(f1220c)) {
                JSONArray g3 = g();
                jSONObject.put("TBSVLARR", g3);
                instance.mSyncMap.put(TbsDownloadConfig.TbsConfigKey.KEY_LAST_THIRDAPP_SENDREQUEST_COREVERSION, g3.toString());
                instance.commit();
                if (QbSdk.f1129c) {
                    jSONObject.put("THIRDREQ", 1);
                }
            } else {
                JSONArray a2 = a(z3);
                if (Apn.getApnType(f1220c) != 3 && a2.length() != 0 && i3 == 0 && z) {
                    jSONObject.put("TBSBACKUPARR", a2);
                }
            }
            jSONObject.put("APPN", f1220c.getPackageName());
            String string = instance.mPreferences.getString(TbsDownloadConfig.TbsConfigKey.KEY_APP_VERSIONNAME, null);
            if (string == null) {
                string = BuildConfig.FLAVOR;
            }
            jSONObject.put("APPVN", string);
            jSONObject.put("APPVC", instance.mPreferences.getInt(TbsDownloadConfig.TbsConfigKey.KEY_APP_VERSIONCODE, 0));
            String string2 = instance.mPreferences.getString(TbsDownloadConfig.TbsConfigKey.KEY_APP_METADATA, null);
            if (string2 == null) {
                string2 = BuildConfig.FLAVOR;
            }
            jSONObject.put("APPMETA", string2);
            jSONObject.put("TBSSDKV", 43939);
            jSONObject.put("TBSV", i3);
            jSONObject.put("DOWNLOADDECOUPLECORE", z3 ? 1 : 0);
            instance.mSyncMap.put(TbsDownloadConfig.TbsConfigKey.KEY_DOWNLOADDECOUPLECORE, Integer.valueOf(z3 ? 1 : 0));
            instance.commit();
            if (i3 != 0) {
                jSONObject.put("TBSBACKUPV", g.b(z3));
            }
            jSONObject.put("CPU", f1222e);
            jSONObject.put("UA", b2);
            if (e2 == null) {
                e2 = BuildConfig.FLAVOR;
            }
            jSONObject.put("IMSI", e2);
            if (d2 == null) {
                d2 = BuildConfig.FLAVOR;
            }
            jSONObject.put("IMEI", d2);
            if (g2 == null) {
                g2 = BuildConfig.FLAVOR;
            }
            jSONObject.put("ANDROID_ID", g2);
            jSONObject.put("GUID", b.c(f1220c));
            if (!TbsShareManager.isThirdPartyApp(f1220c)) {
                if (i3 != 0) {
                    jSONObject.put("STATUS", QbSdk.a(f1220c, i3) ? 0 : 1);
                } else {
                    jSONObject.put("STATUS", 0);
                }
                jSONObject.put("TBSDV", m.a().h(f1220c));
            }
            boolean z5 = TbsDownloadConfig.getInstance(f1220c).mPreferences.getBoolean(TbsDownloadConfig.TbsConfigKey.KEY_FULL_PACKAGE, false);
            Object a3 = QbSdk.a(f1220c, "can_unlzma", (Bundle) null);
            if ((a3 == null || !(a3 instanceof Boolean)) ? false : ((Boolean) a3).booleanValue()) {
                i4 = 1;
                z4 = !z5;
            } else {
                i4 = 1;
            }
            if (z4) {
                jSONObject.put("REQUEST_LZMA", i4);
            }
            if (getOverSea(f1220c)) {
                i5 = 1;
                jSONObject.put("OVERSEA", 1);
            } else {
                i5 = 1;
            }
            if (z2) {
                jSONObject.put("DOWNLOAD_FOREGROUND", i5);
            }
        } catch (Exception unused) {
        }
        StringBuilder a4 = e.a.a.a.a.a("[TbsDownloader.postJsonData] jsonData=");
        a4.append(jSONObject.toString());
        TbsLog.i(LOGTAG, a4.toString());
        return jSONObject;
    }

    public static void a(JSONArray jSONArray) {
        boolean z;
        String[] f2 = f();
        int length = f2.length;
        int i2 = 0;
        while (true) {
            boolean z2 = true;
            if (i2 >= length) {
                break;
            }
            String str = f2[i2];
            int sharedTbsCoreVersion = TbsShareManager.getSharedTbsCoreVersion(f1220c, str);
            if (sharedTbsCoreVersion > 0) {
                Context packageContext = TbsShareManager.getPackageContext(f1220c, str, true);
                if (packageContext != null && !m.a().f(packageContext)) {
                    TbsLog.e(LOGTAG, "host check failed,packageName = " + str);
                } else if (a(f1220c, sharedTbsCoreVersion)) {
                    TbsLog.i(LOGTAG, "add CoreVersionToJsonData,version+" + sharedTbsCoreVersion + " is in black list");
                } else {
                    int i3 = 0;
                    while (true) {
                        if (i3 >= jSONArray.length()) {
                            z2 = false;
                            break;
                        } else if (jSONArray.optInt(i3) == sharedTbsCoreVersion) {
                            break;
                        } else {
                            i3++;
                        }
                    }
                    if (!z2) {
                        jSONArray.put(sharedTbsCoreVersion);
                    }
                }
            }
            i2++;
        }
        String[] f3 = f();
        for (String str2 : f3) {
            int coreShareDecoupleCoreVersion = TbsShareManager.getCoreShareDecoupleCoreVersion(f1220c, str2);
            if (coreShareDecoupleCoreVersion > 0) {
                Context packageContext2 = TbsShareManager.getPackageContext(f1220c, str2, true);
                if (packageContext2 == null || m.a().f(packageContext2)) {
                    int i4 = 0;
                    while (true) {
                        if (i4 >= jSONArray.length()) {
                            z = false;
                            break;
                        } else if (jSONArray.optInt(i4) == coreShareDecoupleCoreVersion) {
                            z = true;
                            break;
                        } else {
                            i4++;
                        }
                    }
                    if (!z) {
                        jSONArray.put(coreShareDecoupleCoreVersion);
                    }
                } else {
                    TbsLog.e(LOGTAG, "host check failed,packageName = " + str2);
                }
            }
        }
    }

    public static void a(boolean z, TbsDownloaderCallback tbsDownloaderCallback, boolean z2) {
        TbsLog.i(LOGTAG, "[TbsDownloader.queryConfig]");
        f1221d.removeMessages(100);
        Message obtain = Message.obtain(f1221d, 100);
        if (tbsDownloaderCallback != null) {
            obtain.obj = tbsDownloaderCallback;
        }
        obtain.arg1 = 0;
        obtain.arg1 = z ? 1 : 0;
        obtain.arg2 = z2 ? 1 : 0;
        obtain.sendToTarget();
    }

    public static boolean a(Context context) {
        return TbsDownloadConfig.getInstance(context).mPreferences.getInt(TbsDownloadConfig.TbsConfigKey.KEY_DOWNLOADDECOUPLECORE, 0) == 1;
    }

    public static boolean a(Context context, int i2) {
        return Build.VERSION.SDK_INT > 28 && context.getApplicationInfo().targetSdkVersion > 28 && i2 > 0 && i2 < 45114;
    }

    public static boolean a(Context context, boolean z) {
        int i2;
        TbsDownloadConfig instance = TbsDownloadConfig.getInstance(context);
        int i3 = Build.VERSION.SDK_INT;
        if (!QbSdk.f1129c && TbsShareManager.isThirdPartyApp(f1220c) && !c()) {
            return false;
        }
        if (!instance.mPreferences.contains(TbsDownloadConfig.TbsConfigKey.KEY_IS_OVERSEA)) {
            if (z && !TbsConfig.APP_WX.equals(context.getApplicationInfo().packageName)) {
                TbsLog.i(LOGTAG, "needDownload-oversea is true, but not WX");
                z = false;
            }
            instance.mSyncMap.put(TbsDownloadConfig.TbsConfigKey.KEY_IS_OVERSEA, Boolean.valueOf(z));
            instance.commit();
            j = z;
            e.a.a.a.a.a("needDownload-first-called--isoversea = ", z, LOGTAG);
        }
        if (getOverSea(context)) {
            int i4 = Build.VERSION.SDK_INT;
            StringBuilder a2 = e.a.a.a.a.a("needDownload- return false,  because of  version is ");
            a2.append(Build.VERSION.SDK_INT);
            a2.append(", and overea");
            TbsLog.i(LOGTAG, a2.toString());
            i2 = -103;
        } else {
            Matcher matcher = null;
            f1222e = instance.mPreferences.getString(TbsDownloadConfig.TbsConfigKey.KEY_DEVICE_CPUABI, null);
            if (TextUtils.isEmpty(f1222e)) {
                return true;
            }
            try {
                matcher = Pattern.compile("i686|mips|x86_64").matcher(f1222e);
            } catch (Exception unused) {
            }
            if (matcher == null || !matcher.find()) {
                return true;
            }
            TbsLog.e(LOGTAG, "can not support x86 devices!!");
            i2 = -104;
        }
        instance.setDownloadInterruptCode(i2);
        return false;
    }

    /* JADX WARN: Code restructure failed: missing block: B:40:0x016d, code lost:
        if (r6.equals(r1) != false) goto L_0x01d8;
     */
    /* JADX WARN: Removed duplicated region for block: B:43:0x017c  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x01df  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static boolean a(android.content.Context r20, boolean r21, boolean r22) {
        /*
            Method dump skipped, instructions count: 517
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.sdk.TbsDownloader.a(android.content.Context, boolean, boolean):boolean");
    }

    public static File b(int i2) {
        StringBuilder sb;
        String[] coreProviderAppList = TbsShareManager.getCoreProviderAppList();
        int length = coreProviderAppList.length;
        File file = null;
        int i3 = 0;
        while (i3 < length) {
            String str = coreProviderAppList[i3];
            file = new File(FileUtil.a(f1220c, str, 4, false), getOverSea(f1220c) ? "x5.oversea.tbs.org" : getBackupFileName(false));
            if (!file.exists() || a.a(f1220c, file) != i2) {
                file = new File(FileUtil.a(f1220c, str, 4, false), "x5.tbs.decouple");
                if (!file.exists() || a.a(f1220c, file) != i2) {
                    i3++;
                    file = file;
                } else {
                    sb = new StringBuilder();
                }
            } else {
                sb = new StringBuilder();
            }
            sb.append("local tbs version fond,path = ");
            sb.append(file.getAbsolutePath());
            TbsLog.i(LOGTAG, sb.toString());
            break;
        }
        return file;
    }

    /* JADX WARN: Removed duplicated region for block: B:24:0x0066  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x008a  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x0095  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x009e  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.lang.String b(android.content.Context r6) {
        /*
            java.lang.String r6 = "ISO8859-1"
            java.lang.String r0 = "UTF-8"
            java.lang.String r1 = com.tencent.smtt.sdk.TbsDownloader.f1219b
            boolean r1 = android.text.TextUtils.isEmpty(r1)
            if (r1 != 0) goto L_0x000f
            java.lang.String r6 = com.tencent.smtt.sdk.TbsDownloader.f1219b
            return r6
        L_0x000f:
            java.util.Locale r1 = java.util.Locale.getDefault()
            java.lang.StringBuffer r2 = new java.lang.StringBuffer
            r2.<init>()
            java.lang.String r3 = android.os.Build.VERSION.RELEASE
            java.lang.String r4 = new java.lang.String     // Catch: Exception -> 0x0024
            byte[] r5 = r3.getBytes(r0)     // Catch: Exception -> 0x0024
            r4.<init>(r5, r6)     // Catch: Exception -> 0x0024
            r3 = r4
        L_0x0024:
            java.lang.String r4 = "1.0"
            if (r3 != 0) goto L_0x002c
        L_0x0028:
            r2.append(r4)
            goto L_0x0035
        L_0x002c:
            int r5 = r3.length()
            if (r5 <= 0) goto L_0x0028
            r2.append(r3)
        L_0x0035:
            java.lang.String r3 = "; "
            r2.append(r3)
            java.lang.String r4 = r1.getLanguage()
            if (r4 == 0) goto L_0x0057
            java.lang.String r4 = r4.toLowerCase()
            r2.append(r4)
            java.lang.String r1 = r1.getCountry()
            if (r1 == 0) goto L_0x005c
            java.lang.String r4 = "-"
            r2.append(r4)
            java.lang.String r1 = r1.toLowerCase()
            goto L_0x0059
        L_0x0057:
            java.lang.String r1 = "en"
        L_0x0059:
            r2.append(r1)
        L_0x005c:
            java.lang.String r1 = android.os.Build.VERSION.CODENAME
            java.lang.String r4 = "REL"
            boolean r1 = r4.equals(r1)
            if (r1 == 0) goto L_0x0084
            java.lang.String r1 = android.os.Build.MODEL
            java.lang.String r4 = new java.lang.String     // Catch: Exception -> 0x0072
            byte[] r0 = r1.getBytes(r0)     // Catch: Exception -> 0x0072
            r4.<init>(r0, r6)     // Catch: Exception -> 0x0072
            r1 = r4
        L_0x0072:
            if (r1 != 0) goto L_0x0078
            r2.append(r3)
            goto L_0x0084
        L_0x0078:
            int r6 = r1.length()
            if (r6 <= 0) goto L_0x0084
            r2.append(r3)
            r2.append(r1)
        L_0x0084:
            java.lang.String r6 = android.os.Build.ID
            java.lang.String r0 = ""
            if (r6 != 0) goto L_0x008b
            r6 = r0
        L_0x008b:
            java.lang.String r1 = "[一-龥]"
            java.lang.String r6 = r6.replaceAll(r1, r0)
            java.lang.String r0 = " Build/"
            if (r6 != 0) goto L_0x009e
            r2.append(r0)
            java.lang.String r6 = "00"
        L_0x009a:
            r2.append(r6)
            goto L_0x00a8
        L_0x009e:
            int r1 = r6.length()
            if (r1 <= 0) goto L_0x00a8
            r2.append(r0)
            goto L_0x009a
        L_0x00a8:
            r6 = 1
            java.lang.Object[] r6 = new java.lang.Object[r6]
            r0 = 0
            r6[r0] = r2
            java.lang.String r0 = "Mozilla/5.0 (Linux; U; Android %s) AppleWebKit/533.1 (KHTML, like Gecko)Version/4.0 Mobile Safari/533.1"
            java.lang.String r6 = java.lang.String.format(r0, r6)
            com.tencent.smtt.sdk.TbsDownloader.f1219b = r6
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.sdk.TbsDownloader.b(android.content.Context):java.lang.String");
    }

    public static void b(JSONArray jSONArray) {
        if (TbsShareManager.f1265c != null) {
            int a2 = m.a().a(TbsShareManager.f1265c);
            boolean z = false;
            int i2 = 0;
            while (true) {
                if (i2 >= jSONArray.length()) {
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

    /* JADX WARN: Can't wrap try/catch for region: R(23:30|(1:32)(1:33)|34|(1:36)(1:37)|38|(1:40)(1:41)|42|(1:44)|45|(2:47|(1:49)(2:50|(1:52)(2:53|(1:55))))|56|(1:58)|59|(4:61|128|62|(9:66|(3:68|(1:70)(1:71)|72)(1:(1:74)(1:75))|76|78|124|79|(4:84|(3:86|(1:88)|89)(1:90)|91|(3:(1:94)(1:(1:96)(1:131))|97|98)(1:99))(1:83)|100|(6:126|109|(1:111)(3:(1:113)|115|130)|114|115|130)(3:(1:104)(2:(1:106)|108)|107|108)))|77|78|124|79|(0)|84|(0)(0)|91|(0)(0)) */
    /* JADX WARN: Code restructure failed: missing block: B:80:0x021f, code lost:
        r11 = -1;
     */
    /* JADX WARN: Removed duplicated region for block: B:23:0x00a9  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x00d9  */
    /* JADX WARN: Removed duplicated region for block: B:86:0x0236  */
    /* JADX WARN: Removed duplicated region for block: B:90:0x0267  */
    /* JADX WARN: Removed duplicated region for block: B:93:0x02a2  */
    /* JADX WARN: Removed duplicated region for block: B:99:0x02d2  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static boolean b(final boolean r24, boolean r25, boolean r26, boolean r27) {
        /*
            Method dump skipped, instructions count: 929
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.sdk.TbsDownloader.b(boolean, boolean, boolean, boolean):boolean");
    }

    @TargetApi(11)
    public static void c(Context context) {
        TbsDownloadConfig.getInstance(context).clear();
        TbsLogReport.getInstance(context).clear();
        j.c(context);
        int i2 = Build.VERSION.SDK_INT;
        context.getSharedPreferences("tbs_extension_config", 4).edit().clear().commit();
        int i3 = Build.VERSION.SDK_INT;
        context.getSharedPreferences("tbs_preloadx5_check_cfg_file", 4).edit().clear().commit();
    }

    public static void c(JSONArray jSONArray) {
        StringBuilder sb;
        boolean z;
        if (!TbsPVConfig.getInstance(f1220c).isDisableHostBackupCore()) {
            String[] f2 = f();
            for (String str : f2) {
                int backupCoreVersion = TbsShareManager.getBackupCoreVersion(f1220c, str);
                boolean z2 = true;
                if (backupCoreVersion > 0) {
                    Context packageContext = TbsShareManager.getPackageContext(f1220c, str, false);
                    if (packageContext != null && !m.a().f(packageContext)) {
                        sb = new StringBuilder();
                        sb.append("host check failed,packageName = ");
                        sb.append(str);
                        TbsLog.e(LOGTAG, sb.toString());
                    } else if (a(f1220c, backupCoreVersion)) {
                        TbsLog.i(LOGTAG, "add addBackupVersionToJsonData,version+" + backupCoreVersion + " is in black list");
                    } else {
                        int i2 = 0;
                        while (true) {
                            if (i2 >= jSONArray.length()) {
                                z = false;
                                break;
                            } else if (jSONArray.optInt(i2) == backupCoreVersion) {
                                z = true;
                                break;
                            } else {
                                i2++;
                            }
                        }
                        if (!z) {
                            jSONArray.put(backupCoreVersion);
                        }
                    }
                }
                int backupDecoupleCoreVersion = TbsShareManager.getBackupDecoupleCoreVersion(f1220c, str);
                if (backupDecoupleCoreVersion > 0) {
                    Context packageContext2 = TbsShareManager.getPackageContext(f1220c, str, false);
                    if (packageContext2 == null || m.a().f(packageContext2)) {
                        int i3 = 0;
                        while (true) {
                            if (i3 >= jSONArray.length()) {
                                z2 = false;
                                break;
                            } else if (jSONArray.optInt(i3) == backupDecoupleCoreVersion) {
                                break;
                            } else {
                                i3++;
                            }
                        }
                        if (!z2) {
                            jSONArray.put(backupDecoupleCoreVersion);
                        }
                    } else {
                        sb = new StringBuilder();
                        sb.append("host check failed,packageName = ");
                        sb.append(str);
                        TbsLog.e(LOGTAG, sb.toString());
                    }
                }
            }
        }
    }

    public static boolean c() {
        try {
            for (String str : TbsShareManager.getCoreProviderAppList()) {
                if (TbsShareManager.getSharedTbsCoreVersion(f1220c, str) > 0) {
                    return true;
                }
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        return false;
    }

    public static synchronized void d() {
        synchronized (TbsDownloader.class) {
            if (h == null) {
                h = l.a();
                try {
                    g = new j(f1220c);
                    f1221d = new Handler(h.getLooper()) { // from class: com.tencent.smtt.sdk.TbsDownloader.1
                        @Override // android.os.Handler
                        public void handleMessage(Message message) {
                            FileOutputStream fileOutputStream;
                            String str;
                            int i2;
                            int i3 = message.what;
                            boolean z = true;
                            if (i3 != 108) {
                                switch (i3) {
                                    case 100:
                                        boolean z2 = message.arg1 == 1;
                                        boolean b2 = TbsDownloader.b(true, false, false, message.arg2 == 1);
                                        Object obj = message.obj;
                                        if (obj != null && (obj instanceof TbsDownloaderCallback)) {
                                            e.a.a.a.a.a("needDownload-onNeedDownloadFinish needStartDownload=", b2, TbsDownloader.LOGTAG);
                                            Context context = TbsDownloader.f1220c;
                                            String str2 = (context == null || context.getApplicationContext() == null || TbsDownloader.f1220c.getApplicationContext().getApplicationInfo() == null) ? BuildConfig.FLAVOR : TbsDownloader.f1220c.getApplicationContext().getApplicationInfo().packageName;
                                            if (b2 && !z2) {
                                                if (TbsConfig.APP_WX.equals(str2) || TbsConfig.APP_QQ.equals(str2)) {
                                                    e.a.a.a.a.a("needDownload-onNeedDownloadFinish in mm or QQ callback needStartDownload = ", b2, TbsDownloader.LOGTAG);
                                                }
                                            }
                                            ((TbsDownloaderCallback) message.obj).onNeedDownloadFinish(b2, TbsDownloadConfig.getInstance(TbsDownloader.f1220c).mPreferences.getInt(TbsDownloadConfig.TbsConfigKey.KEY_TBS_DOWNLOAD_V, 0));
                                        }
                                        if (TbsShareManager.isThirdPartyApp(TbsDownloader.f1220c) && b2) {
                                            TbsDownloader.startDownload(TbsDownloader.f1220c, false);
                                            return;
                                        }
                                        return;
                                    case 101:
                                        break;
                                    case 102:
                                        TbsLog.i(TbsDownloader.LOGTAG, "[TbsDownloader.handleMessage] MSG_REPORT_DOWNLOAD_STAT");
                                        if (TbsShareManager.isThirdPartyApp(TbsDownloader.f1220c)) {
                                            TbsShareManager.b(TbsDownloader.f1220c, false);
                                            i2 = TbsShareManager.f1267e;
                                        } else {
                                            i2 = m.a().m(TbsDownloader.f1220c);
                                        }
                                        TbsLog.i(TbsDownloader.LOGTAG, "[TbsDownloader.handleMessage] localTbsVersion=" + i2);
                                        TbsDownloader.g.a(i2);
                                        TbsLogReport.getInstance(TbsDownloader.f1220c).dailyReport();
                                        return;
                                    case 103:
                                        TbsLog.i(TbsDownloader.LOGTAG, "[TbsDownloader.handleMessage] MSG_CONTINUEINSTALL_TBSCORE");
                                        if (message.arg1 == 0) {
                                            m.a().a((Context) message.obj, true);
                                            return;
                                        } else {
                                            m.a().a((Context) message.obj, false);
                                            return;
                                        }
                                    case 104:
                                        TbsLog.i(TbsDownloader.LOGTAG, "[TbsDownloader.handleMessage] MSG_UPLOAD_TBSLOG");
                                        TbsLogReport.getInstance(TbsDownloader.f1220c).reportTbsLog();
                                        return;
                                    default:
                                        return;
                                }
                            }
                            FileLock fileLock = null;
                            if (!TbsShareManager.isThirdPartyApp(TbsDownloader.f1220c)) {
                                StringBuilder a2 = e.a.a.a.a.a("tbs_download_lock_file");
                                a2.append(TbsDownloadConfig.getInstance(TbsDownloader.f1220c).mPreferences.getInt(TbsDownloadConfig.TbsConfigKey.KEY_TBS_DOWNLOAD_V, 0));
                                a2.append(".txt");
                                fileOutputStream = FileUtil.b(TbsDownloader.f1220c, false, a2.toString());
                                if (fileOutputStream != null) {
                                    fileLock = FileUtil.a(TbsDownloader.f1220c, fileOutputStream);
                                    if (fileLock == null) {
                                        QbSdk.m.onDownloadFinish(TbsListener.ErrorCode.NONEEDDOWNLOAD_OTHER_PROCESS_DOWNLOADING);
                                        TbsLog.i(TbsDownloader.LOGTAG, "file lock locked,wx or qq is downloading");
                                        TbsDownloadConfig.getInstance(TbsDownloader.f1220c).setDownloadInterruptCode(-203);
                                        str = "MSG_START_DOWNLOAD_DECOUPLECORE return #1";
                                        TbsLog.i(TbsDownloader.LOGTAG, str);
                                        return;
                                    }
                                } else if (FileUtil.a(TbsDownloader.f1220c)) {
                                    TbsDownloadConfig.getInstance(TbsDownloader.f1220c).setDownloadInterruptCode(-204);
                                    str = "MSG_START_DOWNLOAD_DECOUPLECORE return #2";
                                    TbsLog.i(TbsDownloader.LOGTAG, str);
                                    return;
                                }
                            } else {
                                fileOutputStream = null;
                            }
                            boolean z3 = message.arg1 == 1;
                            TbsDownloadConfig instance = TbsDownloadConfig.getInstance(TbsDownloader.f1220c);
                            if (TbsDownloader.b(false, z3, 108 == message.what, true)) {
                                if (z3) {
                                    m a3 = m.a();
                                    Context context2 = TbsDownloader.f1220c;
                                    if (a3.b(context2, TbsDownloadConfig.getInstance(context2).mPreferences.getInt(TbsDownloadConfig.TbsConfigKey.KEY_TBS_DOWNLOAD_V, 0))) {
                                        QbSdk.m.onDownloadFinish(TbsListener.ErrorCode.DOWNLOAD_HAS_COPY_TBS_ERROR);
                                        instance.setDownloadInterruptCode(-213);
                                        TbsLog.i(TbsDownloader.LOGTAG, "------freeFileLock called :");
                                        FileUtil.a(fileLock, fileOutputStream);
                                    }
                                }
                                if (instance.mPreferences.getBoolean(TbsDownloadConfig.TbsConfigKey.KEY_NEEDDOWNLOAD, false)) {
                                    TbsDownloadConfig.getInstance(TbsDownloader.f1220c).setDownloadInterruptCode(-215);
                                    j jVar = TbsDownloader.g;
                                    if (108 != message.what) {
                                        z = false;
                                    }
                                    jVar.b(z3, z);
                                    TbsLog.i(TbsDownloader.LOGTAG, "------freeFileLock called :");
                                    FileUtil.a(fileLock, fileOutputStream);
                                }
                            }
                            QbSdk.m.onDownloadFinish(110);
                            TbsLog.i(TbsDownloader.LOGTAG, "------freeFileLock called :");
                            FileUtil.a(fileLock, fileOutputStream);
                        }
                    };
                } catch (Exception unused) {
                    i = true;
                    TbsLog.e(LOGTAG, "TbsApkDownloader init has Exception");
                }
            }
        }
    }

    public static boolean e() {
        try {
            return TbsDownloadConfig.getInstance(f1220c).mPreferences.getString(TbsDownloadConfig.TbsConfigKey.KEY_LAST_THIRDAPP_SENDREQUEST_COREVERSION, BuildConfig.FLAVOR).equals(g().toString());
        } catch (Exception unused) {
            return false;
        }
    }

    public static String[] f() {
        if (QbSdk.k) {
            return new String[]{f1220c.getApplicationContext().getPackageName()};
        }
        String[] coreProviderAppList = TbsShareManager.getCoreProviderAppList();
        String packageName = f1220c.getApplicationContext().getPackageName();
        if (!packageName.equals(TbsShareManager.f(f1220c))) {
            return coreProviderAppList;
        }
        int length = coreProviderAppList.length;
        String[] strArr = new String[length + 1];
        System.arraycopy(coreProviderAppList, 0, strArr, 0, length);
        strArr[length] = packageName;
        return strArr;
    }

    public static JSONArray g() {
        if (!TbsShareManager.isThirdPartyApp(f1220c)) {
            return null;
        }
        JSONArray jSONArray = new JSONArray();
        a(jSONArray);
        c(jSONArray);
        b(jSONArray);
        return jSONArray;
    }

    public static String getBackupFileName(boolean z) {
        return z ? b.c() ? "x5.tbs.decouple.64" : "x5.tbs.decouple" : b.c() ? "x5.tbs.org.64" : "x5.tbs.org";
    }

    public static int getCoreShareDecoupleCoreVersion() {
        return m.a().h(f1220c);
    }

    public static int getCoreShareDecoupleCoreVersionByContext(Context context) {
        return m.a().h(context);
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

    public static long getRetryIntervalInSeconds() {
        return l;
    }

    public static HandlerThread getsTbsHandlerThread() {
        return h;
    }

    public static boolean h() {
        int i2;
        TbsDownloadConfig instance = TbsDownloadConfig.getInstance(f1220c);
        if (instance.mPreferences.getInt(TbsDownloadConfig.TbsConfigKey.KEY_DOWNLOAD_SUCCESS_RETRYTIMES, 0) >= instance.getDownloadSuccessMaxRetrytimes()) {
            TbsLog.i(LOGTAG, "[TbsDownloader.needStartDownload] out of success retrytimes", true);
            i2 = -115;
        } else if (instance.mPreferences.getInt(TbsDownloadConfig.TbsConfigKey.KEY_DOWNLOAD_FAILED_RETRYTIMES, 0) >= instance.getDownloadFailedMaxRetrytimes()) {
            TbsLog.i(LOGTAG, "[TbsDownloader.needStartDownload] out of failed retrytimes", true);
            i2 = -116;
        } else if (!FileUtil.b(f1220c)) {
            TbsLog.i(LOGTAG, "[TbsDownloader.needStartDownload] local rom freespace limit", true);
            i2 = -117;
        } else {
            if (System.currentTimeMillis() - instance.mPreferences.getLong(TbsDownloadConfig.TbsConfigKey.KEY_TBSDOWNLOAD_STARTTIME, 0L) <= 86400000) {
                long j2 = instance.mPreferences.getLong(TbsDownloadConfig.TbsConfigKey.KEY_TBSDOWNLOAD_FLOW, 0L);
                TbsLog.i(LOGTAG, "[TbsDownloader.needStartDownload] downloadFlow=" + j2);
                if (j2 >= instance.getDownloadMaxflow()) {
                    TbsLog.i(LOGTAG, "[TbsDownloader.needStartDownload] failed because you exceeded max flow!", true);
                    i2 = -120;
                }
            }
            return true;
        }
        instance.setDownloadInterruptCode(i2);
        return false;
    }

    public static boolean isDownloadForeground() {
        j jVar = g;
        return jVar != null && jVar.d();
    }

    public static synchronized boolean isDownloading() {
        boolean z;
        synchronized (TbsDownloader.class) {
            TbsLog.i(LOGTAG, "[TbsDownloader.isDownloading] is " + f1218a);
            z = f1218a;
        }
        return z;
    }

    public static boolean needDownload(Context context, boolean z) {
        return needDownload(context, z, false, true, null);
    }

    public static boolean needDownload(Context context, boolean z, boolean z2, TbsDownloaderCallback tbsDownloaderCallback) {
        return needDownload(context, z, z2, true, tbsDownloaderCallback);
    }

    public static boolean needDownload(Context context, boolean z, boolean z2, boolean z3, TbsDownloaderCallback tbsDownloaderCallback) {
        boolean z4;
        boolean z5;
        int i2;
        StringBuilder a2 = e.a.a.a.a.a("needDownload,process=");
        a2.append(QbSdk.getCurrentProcessName(context));
        a2.append("stack=");
        a2.append(Log.getStackTraceString(new Throwable()));
        TbsLog.i(LOGTAG, a2.toString());
        TbsDownloadUpload.clear();
        TbsDownloadUpload instance = TbsDownloadUpload.getInstance(context);
        instance.f1214a.put(TbsDownloadUpload.TbsUploadKey.KEY_NEEDDOWNLOAD_CODE, Integer.valueOf((int) TbsListener.ErrorCode.NEEDDOWNLOAD_1));
        instance.commit();
        TbsLog.i(LOGTAG, "[TbsDownloader.needDownload] oversea=" + z + ",isDownloadForeground=" + z2);
        TbsLog.initIfNeed(context);
        if (m.f1372b) {
            if (tbsDownloaderCallback != null) {
                tbsDownloaderCallback.onNeedDownloadFinish(false, 0);
            }
            e.a.a.a.a.a("[TbsDownloader.needDownload]#1,return ", false, LOGTAG);
            instance.f1214a.put(TbsDownloadUpload.TbsUploadKey.KEY_NEEDDOWNLOAD_RETURN, Integer.valueOf((int) TbsListener.ErrorCode.NEEDDOWNLOAD_FALSE_1));
            instance.commit();
            return false;
        }
        TbsLog.app_extra(LOGTAG, context);
        f1220c = context.getApplicationContext();
        TbsDownloadConfig instance2 = TbsDownloadConfig.getInstance(f1220c);
        instance2.setDownloadInterruptCode(-100);
        if (!a(f1220c, z)) {
            e.a.a.a.a.a("[TbsDownloader.needDownload]#2,return ", false, LOGTAG);
            instance.f1214a.put(TbsDownloadUpload.TbsUploadKey.KEY_NEEDDOWNLOAD_CODE, Integer.valueOf((int) TbsListener.ErrorCode.NEEDDOWNLOAD_2));
            instance.commit();
            instance.f1214a.put(TbsDownloadUpload.TbsUploadKey.KEY_NEEDDOWNLOAD_RETURN, Integer.valueOf((int) TbsListener.ErrorCode.NEEDDOWNLOAD_FALSE_2));
            instance.commit();
            if (tbsDownloaderCallback != null) {
                tbsDownloaderCallback.onNeedDownloadFinish(false, 0);
            }
            return false;
        }
        d();
        if (i) {
            if (tbsDownloaderCallback != null) {
                tbsDownloaderCallback.onNeedDownloadFinish(false, 0);
            }
            instance2.setDownloadInterruptCode(-105);
            TbsLog.i(LOGTAG, "[TbsDownloader.needDownload]#3,return false");
            instance.f1214a.put(TbsDownloadUpload.TbsUploadKey.KEY_NEEDDOWNLOAD_CODE, Integer.valueOf((int) TbsListener.ErrorCode.NEEDDOWNLOAD_3));
            instance.commit();
            instance.f1214a.put(TbsDownloadUpload.TbsUploadKey.KEY_NEEDDOWNLOAD_RETURN, Integer.valueOf((int) TbsListener.ErrorCode.NEEDDOWNLOAD_FALSE_3));
            instance.commit();
            if (tbsDownloaderCallback != null) {
                tbsDownloaderCallback.onNeedDownloadFinish(false, 0);
            }
            return false;
        }
        boolean a3 = a(f1220c, z2, false);
        e.a.a.a.a.a("[TbsDownloader.needDownload],needSendRequest=", a3, LOGTAG);
        if (a3) {
            a(z2, tbsDownloaderCallback, z3);
            instance2.setDownloadInterruptCode(-114);
        } else {
            instance.f1214a.put(TbsDownloadUpload.TbsUploadKey.KEY_NEEDDOWNLOAD_CODE, Integer.valueOf((int) TbsListener.ErrorCode.NEEDDOWNLOAD_4));
            instance.commit();
        }
        f1221d.removeMessages(102);
        Message.obtain(f1221d, 102).sendToTarget();
        if (QbSdk.f1129c || !TbsShareManager.isThirdPartyApp(context)) {
            z4 = instance2.mPreferences.contains(TbsDownloadConfig.TbsConfigKey.KEY_NEEDDOWNLOAD);
            e.a.a.a.a.a("[TbsDownloader.needDownload] hasNeedDownloadKey=", z4, LOGTAG);
            z5 = (z4 || TbsShareManager.isThirdPartyApp(context)) ? instance2.mPreferences.getBoolean(TbsDownloadConfig.TbsConfigKey.KEY_NEEDDOWNLOAD, false) : true;
        } else {
            z5 = false;
            z4 = false;
        }
        TbsLog.i(LOGTAG, "[TbsDownloader.needDownload]#4,needDownload=" + z5 + ",hasNeedDownloadKey=" + z4);
        if (!z5) {
            int m = m.a().m(f1220c);
            TbsLog.i(LOGTAG, "[TbsDownloader.needDownload]#7,tbsLocalVersion=" + m + ",needSendRequest=" + a3);
            if (a3 || m <= 0) {
                f1221d.removeMessages(103);
                ((m > 0 || a3) ? Message.obtain(f1221d, 103, 1, 0, f1220c) : Message.obtain(f1221d, 103, 0, 0, f1220c)).sendToTarget();
                i2 = -121;
            } else {
                i2 = -119;
            }
            instance2.setDownloadInterruptCode(i2);
        } else if (!h()) {
            TbsLog.i(LOGTAG, "[TbsDownloader.needDownload]#5,set needDownload = false");
            z5 = false;
        } else {
            instance2.setDownloadInterruptCode(-118);
            TbsLog.i(LOGTAG, "[TbsDownloader.needDownload]#6");
        }
        if (!a3 && tbsDownloaderCallback != null) {
            tbsDownloaderCallback.onNeedDownloadFinish(false, 0);
        }
        e.a.a.a.a.a("[TbsDownloader.needDownload] needDownload=", z5, LOGTAG);
        instance.f1214a.put(TbsDownloadUpload.TbsUploadKey.KEY_NEEDDOWNLOAD_RETURN, Integer.valueOf(z5 ? TbsListener.ErrorCode.NEEDDOWNLOAD_TRUE : TbsListener.ErrorCode.NEEDDOWNLOAD_FALSE_4));
        instance.commit();
        return z5;
    }

    public static boolean needDownloadDecoupleCore() {
        int i2;
        if (TbsShareManager.isThirdPartyApp(f1220c) || a(f1220c)) {
            return false;
        }
        return System.currentTimeMillis() - TbsDownloadConfig.getInstance(f1220c).mPreferences.getLong(TbsDownloadConfig.TbsConfigKey.KEY_LAST_DOWNLOAD_DECOUPLE_CORE, 0L) >= TbsDownloadConfig.getInstance(f1220c).getRetryInterval() * 1000 && (i2 = TbsDownloadConfig.getInstance(f1220c).mPreferences.getInt(TbsDownloadConfig.TbsConfigKey.KEY_DECOUPLECOREVERSION, 0)) > 0 && i2 != m.a().h(f1220c) && TbsDownloadConfig.getInstance(f1220c).mPreferences.getInt(TbsDownloadConfig.TbsConfigKey.KEY_TBS_DOWNLOAD_V, 0) != i2;
    }

    public static boolean needSendRequest(Context context, boolean z) {
        f1220c = context.getApplicationContext();
        TbsLog.initIfNeed(context);
        if (!a(f1220c, z)) {
            return false;
        }
        int m = m.a().m(context);
        TbsLog.i(LOGTAG, "[TbsDownloader.needSendRequest] localTbsVersion=" + m);
        if (m > 0) {
            return false;
        }
        boolean z2 = true;
        if (a(f1220c, false, true)) {
            return true;
        }
        TbsDownloadConfig instance = TbsDownloadConfig.getInstance(f1220c);
        boolean contains = instance.mPreferences.contains(TbsDownloadConfig.TbsConfigKey.KEY_NEEDDOWNLOAD);
        e.a.a.a.a.a("[TbsDownloader.needSendRequest] hasNeedDownloadKey=", contains, LOGTAG);
        boolean z3 = !contains ? true : instance.mPreferences.getBoolean(TbsDownloadConfig.TbsConfigKey.KEY_NEEDDOWNLOAD, false);
        e.a.a.a.a.a("[TbsDownloader.needSendRequest] needDownload=", z3, LOGTAG);
        if (!z3 || !h()) {
            z2 = false;
        }
        e.a.a.a.a.a("[TbsDownloader.needSendRequest] ret=", z2, LOGTAG);
        return z2;
    }

    public static void setAppContext(Context context) {
        if (context != null && context.getApplicationContext() != null) {
            f1220c = context.getApplicationContext();
        }
    }

    public static void setRetryIntervalInSeconds(Context context, long j2) {
        if (context != null) {
            if (context.getApplicationInfo().packageName.equals("com.tencent.qqlive")) {
                l = j2;
            }
            StringBuilder a2 = e.a.a.a.a.a("mRetryIntervalInSeconds is ");
            a2.append(l);
            TbsLog.i(LOGTAG, a2.toString());
        }
    }

    public static boolean startDecoupleCoreIfNeeded() {
        StringBuilder sb;
        int i2;
        TbsLog.i(LOGTAG, "startDecoupleCoreIfNeeded ");
        if (TbsShareManager.isThirdPartyApp(f1220c)) {
            return false;
        }
        TbsLog.i(LOGTAG, "startDecoupleCoreIfNeeded #1");
        if (a(f1220c) || f1221d == null) {
            return false;
        }
        TbsLog.i(LOGTAG, "startDecoupleCoreIfNeeded #2");
        long j2 = TbsDownloadConfig.getInstance(f1220c).mPreferences.getLong(TbsDownloadConfig.TbsConfigKey.KEY_LAST_DOWNLOAD_DECOUPLE_CORE, 0L);
        if (System.currentTimeMillis() - j2 < TbsDownloadConfig.getInstance(f1220c).getRetryInterval() * 1000) {
            return false;
        }
        TbsLog.i(LOGTAG, "startDecoupleCoreIfNeeded #3");
        int i3 = TbsDownloadConfig.getInstance(f1220c).mPreferences.getInt(TbsDownloadConfig.TbsConfigKey.KEY_DECOUPLECOREVERSION, 0);
        if (i3 <= 0 || i3 == m.a().h(f1220c)) {
            sb = new StringBuilder();
            sb.append("startDecoupleCoreIfNeeded no need, deCoupleCoreVersion is ");
            sb.append(i3);
            sb.append(" getTbsCoreShareDecoupleCoreVersion is ");
            i2 = m.a().h(f1220c);
        } else if (TbsDownloadConfig.getInstance(f1220c).mPreferences.getInt(TbsDownloadConfig.TbsConfigKey.KEY_TBS_DOWNLOAD_V, 0) != i3 || TbsDownloadConfig.getInstance(f1220c).mPreferences.getInt(TbsDownloadConfig.TbsConfigKey.KEY_TBS_DOWNLOAD_V_TYPE, 0) == 1) {
            TbsLog.i(LOGTAG, "startDecoupleCoreIfNeeded #4");
            f1218a = true;
            f1221d.removeMessages(108);
            Message obtain = Message.obtain(f1221d, 108, QbSdk.m);
            obtain.arg1 = 0;
            obtain.sendToTarget();
            TbsDownloadConfig.getInstance(f1220c).mSyncMap.put(TbsDownloadConfig.TbsConfigKey.KEY_LAST_DOWNLOAD_DECOUPLE_CORE, Long.valueOf(System.currentTimeMillis()));
            return true;
        } else {
            sb = e.a.a.a.a.a("startDecoupleCoreIfNeeded no need, KEY_TBS_DOWNLOAD_V is ");
            sb.append(TbsDownloadConfig.getInstance(f1220c).mPreferences.getInt(TbsDownloadConfig.TbsConfigKey.KEY_TBS_DOWNLOAD_V, 0));
            sb.append(" deCoupleCoreVersion is ");
            sb.append(i3);
            sb.append(" KEY_TBS_DOWNLOAD_V_TYPE is ");
            i2 = TbsDownloadConfig.getInstance(f1220c).mPreferences.getInt(TbsDownloadConfig.TbsConfigKey.KEY_TBS_DOWNLOAD_V_TYPE, 0);
        }
        sb.append(i2);
        TbsLog.i(LOGTAG, sb.toString());
        return false;
    }

    public static void startDownload(Context context) {
        startDownload(context, false);
    }

    public static synchronized void startDownload(Context context, boolean z) {
        synchronized (TbsDownloader.class) {
            TbsDownloadUpload instance = TbsDownloadUpload.getInstance(context);
            instance.f1214a.put(TbsDownloadUpload.TbsUploadKey.KEY_STARTDOWNLOAD_CODE, Integer.valueOf((int) TbsListener.ErrorCode.STARTDOWNLOAD_1));
            instance.commit();
            TbsLog.i(LOGTAG, "[TbsDownloader.startDownload] sAppContext=" + f1220c);
            if (m.f1372b) {
                instance.f1214a.put(TbsDownloadUpload.TbsUploadKey.KEY_STARTDOWNLOAD_CODE, Integer.valueOf((int) TbsListener.ErrorCode.STARTDOWNLOAD_2));
                instance.commit();
                return;
            }
            int i2 = 1;
            f1218a = true;
            f1220c = context.getApplicationContext();
            TbsDownloadConfig.getInstance(f1220c).setDownloadInterruptCode(-200);
            int i3 = Build.VERSION.SDK_INT;
            d();
            if (i) {
                QbSdk.m.onDownloadFinish(TbsListener.ErrorCode.THREAD_INIT_ERROR);
                TbsDownloadConfig.getInstance(f1220c).setDownloadInterruptCode(-202);
                instance.f1214a.put(TbsDownloadUpload.TbsUploadKey.KEY_STARTDOWNLOAD_CODE, Integer.valueOf((int) TbsListener.ErrorCode.STARTDOWNLOAD_4));
                instance.commit();
                return;
            }
            if (z) {
                stopDownload();
            }
            f1221d.removeMessages(101);
            f1221d.removeMessages(100);
            Message obtain = Message.obtain(f1221d, 101, QbSdk.m);
            if (!z) {
                i2 = 0;
            }
            obtain.arg1 = i2;
            obtain.sendToTarget();
        }
    }

    public static void stopDownload() {
        if (!i) {
            TbsLog.i(LOGTAG, "[TbsDownloader.stopDownload]");
            j jVar = g;
            if (jVar != null) {
                jVar.b();
            }
            Handler handler = f1221d;
            if (handler != null) {
                handler.removeMessages(100);
                f1221d.removeMessages(101);
                f1221d.removeMessages(108);
            }
        }
    }

    /*  JADX ERROR: JadxRuntimeException in pass: BlockProcessor
        jadx.core.utils.exceptions.JadxRuntimeException: Unreachable block: B:34:0x0110
        	at jadx.core.dex.visitors.blocks.BlockProcessor.checkForUnreachableBlocks(BlockProcessor.java:86)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.processBlocksTree(BlockProcessor.java:52)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.visit(BlockProcessor.java:44)
        */
    @android.annotation.TargetApi(11)
    public static boolean a(java.lang.String r31, int r32, boolean r33, boolean r34, boolean r35) {
        /*
            Method dump skipped, instructions count: 1955
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.sdk.TbsDownloader.a(java.lang.String, int, boolean, boolean, boolean):boolean");
    }
}
