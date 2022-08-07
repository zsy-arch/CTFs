package com.tencent.smtt.sdk;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Looper;
import android.text.TextUtils;
import android.util.Log;
import com.tencent.smtt.sdk.TbsListener;
import com.tencent.smtt.utils.FileUtil;
import com.tencent.smtt.utils.TbsLog;
import com.tencent.smtt.utils.b;
import e.a.a.a.a;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/* loaded from: classes.dex */
public class TbsShareManager {

    /* renamed from: a */
    public static Context f1263a;

    /* renamed from: b */
    public static boolean f1264b;

    /* renamed from: c */
    public static String f1265c;

    /* renamed from: d */
    public static String f1266d;

    /* renamed from: e */
    public static int f1267e;
    public static String f;
    public static boolean g;
    public static boolean h;
    public static boolean i;
    public static String j;
    public static boolean k;
    public static boolean l;
    public static boolean mHasQueryed;

    public static int a(Context context, boolean z) {
        b(context, z);
        return f1267e;
    }

    public static String a() {
        return f1266d;
    }

    public static void a(Context context) {
        TbsLog.i("TbsShareManager", "shareTbsCore #1");
        try {
            TbsLinuxToolsJni tbsLinuxToolsJni = new TbsLinuxToolsJni(context);
            a(context, tbsLinuxToolsJni, m.a().q(context));
            File r = m.a().r(context);
            TbsLog.i("TbsShareManager", "shareTbsCore tbsShareDir is " + r.getAbsolutePath());
            tbsLinuxToolsJni.a(r.getAbsolutePath(), "755");
        } catch (Throwable th) {
            StringBuilder a2 = a.a("shareTbsCore tbsShareDir error is ");
            a2.append(th.getMessage());
            a2.append(" ## ");
            a2.append(th.getCause());
            TbsLog.i("TbsShareManager", a2.toString());
            th.printStackTrace();
        }
    }

    public static void a(Context context, int i2) {
        File file;
        String str;
        StringBuilder sb;
        if (!TbsPVConfig.getInstance(f1263a).isDisableHostBackupCore() && m.a().t(context)) {
            String[] strArr = {TbsConfig.APP_DEMO, TbsConfig.APP_WX, TbsConfig.APP_QQ, TbsConfig.APP_QZONE, context.getPackageName()};
            StringBuilder a2 = a.a("find host backup core to unzip #1");
            a2.append(Log.getStackTraceString(new Throwable()));
            TbsLog.i("TbsShareManager", a2.toString());
            for (String str2 : strArr) {
                if (i2 == getBackupCoreVersion(context, str2)) {
                    if (m.a().f(getPackageContext(context, str2, false))) {
                        file = getBackupCoreFile(context, str2);
                        if (com.tencent.smtt.utils.a.a(context, file, 0L, i2)) {
                            sb = new StringBuilder();
                            str = "find host backup core to unzip normal coreVersion is ";
                            sb.append(str);
                            sb.append(i2);
                            sb.append(" packageName is ");
                            sb.append(str2);
                            TbsLog.i("TbsShareManager", sb.toString());
                            m.a().b(context, file, i2);
                            break;
                        }
                    } else {
                        continue;
                    }
                } else {
                    if (i2 == getBackupDecoupleCoreVersion(context, str2) && m.a().f(getPackageContext(context, str2, false))) {
                        file = getBackupDecoupleCoreFile(context, str2);
                        if (com.tencent.smtt.utils.a.a(context, file, 0L, i2)) {
                            sb = new StringBuilder();
                            str = "find host backup core to unzip decouple coreVersion is ";
                            sb.append(str);
                            sb.append(i2);
                            sb.append(" packageName is ");
                            sb.append(str2);
                            TbsLog.i("TbsShareManager", sb.toString());
                            m.a().b(context, file, i2);
                            break;
                        }
                    }
                }
            }
            m.a().b();
        }
    }

    public static void a(Context context, TbsLinuxToolsJni tbsLinuxToolsJni, File file) {
        TbsLog.i("TbsShareManager", "shareAllDirsAndFiles #1");
        if (file != null && file.exists() && file.isDirectory()) {
            StringBuilder a2 = a.a("shareAllDirsAndFiles dir is ");
            a2.append(file.getAbsolutePath());
            TbsLog.i("TbsShareManager", a2.toString());
            tbsLinuxToolsJni.a(file.getAbsolutePath(), "755");
            File[] listFiles = file.listFiles();
            for (File file2 : listFiles) {
                if (file2.isFile()) {
                    if (file2.getAbsolutePath().indexOf(".so") > 0) {
                        tbsLinuxToolsJni.a(file2.getAbsolutePath(), "755");
                    } else {
                        tbsLinuxToolsJni.a(file2.getAbsolutePath(), "644");
                    }
                } else if (file2.isDirectory()) {
                    a(context, tbsLinuxToolsJni, file2);
                } else {
                    TbsLog.e("TbsShareManager", "unknown file type.", true);
                }
            }
        }
    }

    public static void b(Context context) {
        try {
            a(context, new TbsLinuxToolsJni(context), m.a().p(context));
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public static boolean b(Context context, boolean z) {
        if (i(context)) {
            return true;
        }
        if (!z) {
            return false;
        }
        QbSdk.a(context, "TbsShareManager::isShareTbsCoreAvailable forceSysWebViewInner!");
        return false;
    }

    public static String c(Context context) {
        j(context);
        return f1266d;
    }

    /* JADX WARN: Removed duplicated region for block: B:42:0x008c A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:48:0x0087 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static void c(android.content.Context r6, boolean r7) {
        /*
            r0 = 0
            java.lang.String r1 = "core_info"
            java.io.File r1 = getTbsShareFile(r6, r1)     // Catch: Throwable -> 0x0074, all -> 0x0071
            if (r1 != 0) goto L_0x000a
            return
        L_0x000a:
            java.io.FileInputStream r2 = new java.io.FileInputStream     // Catch: Throwable -> 0x0074, all -> 0x0071
            r2.<init>(r1)     // Catch: Throwable -> 0x0074, all -> 0x0071
            java.io.BufferedInputStream r3 = new java.io.BufferedInputStream     // Catch: Throwable -> 0x0074, all -> 0x0071
            r3.<init>(r2)     // Catch: Throwable -> 0x0074, all -> 0x0071
            java.util.Properties r2 = new java.util.Properties     // Catch: Throwable -> 0x006d, all -> 0x006b
            r2.<init>()     // Catch: Throwable -> 0x006d, all -> 0x006b
            r2.load(r3)     // Catch: Throwable -> 0x006d, all -> 0x006b
            java.lang.String r4 = "core_disabled"
            r5 = 0
            java.lang.String r5 = java.lang.String.valueOf(r5)     // Catch: Throwable -> 0x006d, all -> 0x006b
            r2.setProperty(r4, r5)     // Catch: Throwable -> 0x006d, all -> 0x006b
            if (r7 == 0) goto L_0x0053
            com.tencent.smtt.sdk.m r7 = com.tencent.smtt.sdk.m.a()     // Catch: Throwable -> 0x006d, all -> 0x006b
            java.io.File r7 = r7.q(r6)     // Catch: Throwable -> 0x006d, all -> 0x006b
            java.lang.String r7 = r7.getAbsolutePath()     // Catch: Throwable -> 0x006d, all -> 0x006b
            android.content.Context r4 = r6.getApplicationContext()     // Catch: Throwable -> 0x006d, all -> 0x006b
            java.lang.String r4 = r4.getPackageName()     // Catch: Throwable -> 0x006d, all -> 0x006b
            int r6 = com.tencent.smtt.utils.b.b(r6)     // Catch: Throwable -> 0x006d, all -> 0x006b
            java.lang.String r5 = "core_packagename"
            r2.setProperty(r5, r4)     // Catch: Throwable -> 0x006d, all -> 0x006b
            java.lang.String r4 = "core_path"
            r2.setProperty(r4, r7)     // Catch: Throwable -> 0x006d, all -> 0x006b
            java.lang.String r7 = "app_version"
            java.lang.String r6 = java.lang.String.valueOf(r6)     // Catch: Throwable -> 0x006d, all -> 0x006b
            r2.setProperty(r7, r6)     // Catch: Throwable -> 0x006d, all -> 0x006b
        L_0x0053:
            java.io.FileOutputStream r6 = new java.io.FileOutputStream     // Catch: Throwable -> 0x006d, all -> 0x006b
            r6.<init>(r1)     // Catch: Throwable -> 0x006d, all -> 0x006b
            java.io.BufferedOutputStream r7 = new java.io.BufferedOutputStream     // Catch: Throwable -> 0x006d, all -> 0x006b
            r7.<init>(r6)     // Catch: Throwable -> 0x006d, all -> 0x006b
            r2.store(r7, r0)     // Catch: Throwable -> 0x0069, all -> 0x0067
            r3.close()     // Catch: Exception -> 0x0063
        L_0x0063:
            r7.close()     // Catch: Exception -> 0x0081
            goto L_0x0081
        L_0x0067:
            r6 = move-exception
            goto L_0x0084
        L_0x0069:
            r6 = move-exception
            goto L_0x006f
        L_0x006b:
            r6 = move-exception
            goto L_0x0085
        L_0x006d:
            r6 = move-exception
            r7 = r0
        L_0x006f:
            r0 = r3
            goto L_0x0076
        L_0x0071:
            r6 = move-exception
            r3 = r0
            goto L_0x0085
        L_0x0074:
            r6 = move-exception
            r7 = r0
        L_0x0076:
            r6.printStackTrace()     // Catch: all -> 0x0082
            if (r0 == 0) goto L_0x007e
            r0.close()     // Catch: Exception -> 0x007e
        L_0x007e:
            if (r7 == 0) goto L_0x0081
            goto L_0x0063
        L_0x0081:
            return
        L_0x0082:
            r6 = move-exception
            r3 = r0
        L_0x0084:
            r0 = r7
        L_0x0085:
            if (r3 == 0) goto L_0x008a
            r3.close()     // Catch: Exception -> 0x008a
        L_0x008a:
            if (r0 == 0) goto L_0x008f
            r0.close()     // Catch: Exception -> 0x008f
        L_0x008f:
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.sdk.TbsShareManager.c(android.content.Context, boolean):void");
    }

    public static int d(Context context) {
        b(context, true);
        return f1267e;
    }

    public static Context e(Context context) {
        j(context);
        String str = f;
        Context context2 = null;
        if (str != null) {
            Context packageContext = getPackageContext(context, str, true);
            if (m.a().f(packageContext)) {
                context2 = packageContext;
            }
        }
        return f1265c != null ? f1263a : context2;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r6v6 */
    public static synchronized String f(Context context) {
        Throwable th;
        BufferedInputStream bufferedInputStream;
        Throwable th2;
        synchronized (TbsShareManager.class) {
            try {
            } catch (Throwable th3) {
                th = th3;
            }
            try {
                File tbsShareFile = getTbsShareFile(context, "core_info");
                if (tbsShareFile == null) {
                    return null;
                }
                bufferedInputStream = new BufferedInputStream(new FileInputStream(tbsShareFile));
                try {
                    Properties properties = new Properties();
                    properties.load(bufferedInputStream);
                    String property = properties.getProperty("core_packagename", BuildConfig.FLAVOR);
                    if (!BuildConfig.FLAVOR.equals(property)) {
                        try {
                            bufferedInputStream.close();
                        } catch (Exception unused) {
                        }
                        return property;
                    }
                    try {
                        bufferedInputStream.close();
                    } catch (Exception unused2) {
                    }
                    return null;
                } catch (Throwable th4) {
                    th2 = th4;
                    th2.printStackTrace();
                    if (bufferedInputStream != null) {
                        try {
                            bufferedInputStream.close();
                        } catch (Exception unused3) {
                        }
                    }
                    return null;
                }
            } catch (Throwable th5) {
                th = th5;
                context = 0;
                if (context != 0) {
                    try {
                        context.close();
                    } catch (Exception unused4) {
                    }
                }
                throw th;
            }
        }
    }

    public static int findCoreForThirdPartyApp(Context context) {
        String str;
        n(context);
        TbsLog.i("TbsShareManager", "core_info mAvailableCoreVersion is " + f1267e + " mAvailableCorePath is " + f1266d + " mSrcPackageName is " + f);
        if (f == null) {
            TbsLog.e("TbsShareManager", "mSrcPackageName is null !!!");
        }
        String str2 = f;
        if (str2 == null || !str2.equals("AppDefined")) {
            if (!k(context) && !l(context)) {
                f1267e = 0;
                f1266d = null;
                f = null;
                str = "core_info error checkCoreInfo is false and checkCoreInOthers is false ";
                TbsLog.i("TbsShareManager", str);
            }
        } else if (f1267e != m.a().a(f1265c)) {
            f1267e = 0;
            f1266d = null;
            f = null;
            StringBuilder a2 = a.a("check AppDefined core is error src is ");
            a2.append(f1267e);
            a2.append(" dest is ");
            a2.append(m.a().a(f1265c));
            str = a2.toString();
            TbsLog.i("TbsShareManager", str);
        }
        if (f1267e > 0) {
            ApplicationInfo applicationInfo = context.getApplicationInfo();
            if ((!("com.tencent.android.qqdownloader".equals(applicationInfo.packageName) || "com.jd.jrapp".equals(applicationInfo.packageName)) ? QbSdk.a(context, f1267e) : false) || g) {
                f1267e = 0;
                f1266d = null;
                f = null;
                TbsLog.i("TbsShareManager", "core_info error QbSdk.isX5Disabled ");
            }
        }
        return f1267e;
    }

    public static boolean forceLoadX5FromTBSDemo(Context context) {
        int sharedTbsCoreVersion;
        if (context == null || m.a().a(context, (File[]) null) || (sharedTbsCoreVersion = getSharedTbsCoreVersion(context, TbsConfig.APP_DEMO)) <= 0) {
            return false;
        }
        writeProperties(context, Integer.toString(sharedTbsCoreVersion), TbsConfig.APP_DEMO, m.a().q(getPackageContext(context, TbsConfig.APP_DEMO, true)).getAbsolutePath(), "1");
        return true;
    }

    public static void forceToLoadX5ForThirdApp(Context context, boolean z) {
        File r;
        int a2;
        try {
            if (!QbSdk.w || !isThirdPartyApp(context) || QbSdk.k || (r = m.a().r(context)) == null) {
                return;
            }
            if (z && new File(r, "core_info").exists()) {
                return;
            }
            if (f1265c == null || (a2 = m.a().a(f1265c)) <= 0) {
                TbsLog.i("TbsShareManager", "forceToLoadX5ForThirdApp #1");
                int h2 = h(context);
                int i2 = m.a().i(context);
                TbsLog.i("TbsShareManager", "forceToLoadX5ForThirdApp coreVersionFromConfig is " + h2);
                TbsLog.i("TbsShareManager", "forceToLoadX5ForThirdApp coreVersionFromCoreShare is " + i2);
                String[] coreProviderAppList = getCoreProviderAppList();
                for (String str : coreProviderAppList) {
                    int coreShareDecoupleCoreVersion = getCoreShareDecoupleCoreVersion(context, str);
                    if (coreShareDecoupleCoreVersion >= h2 && coreShareDecoupleCoreVersion >= i2 && coreShareDecoupleCoreVersion > 0) {
                        f1266d = m.a().c(context, getPackageContext(context, str, true)).getAbsolutePath();
                        f = str;
                        f1267e = coreShareDecoupleCoreVersion;
                        TbsLog.i("TbsShareManager", "forceToLoadX5ForThirdApp #2 -- mAvailableCoreVersion: " + f1267e + " " + Log.getStackTraceString(new Throwable("#")));
                        if (QbSdk.canLoadX5FirstTimeThirdApp(context)) {
                            int b2 = b.b(context);
                            TbsLog.i("TbsShareManager", "forceToLoadX5ForThirdApp #2");
                            writeProperties(context, Integer.toString(f1267e), f, f1266d, Integer.toString(b2));
                            return;
                        }
                        f1267e = 0;
                        f1266d = null;
                        f = null;
                    }
                }
                for (String str2 : coreProviderAppList) {
                    int sharedTbsCoreVersion = getSharedTbsCoreVersion(context, str2);
                    if (sharedTbsCoreVersion >= h2 && sharedTbsCoreVersion >= i2 && sharedTbsCoreVersion > 0) {
                        f1266d = m.a().b(context, getPackageContext(context, str2, true)).getAbsolutePath();
                        f = str2;
                        f1267e = sharedTbsCoreVersion;
                        TbsLog.i("TbsShareManager", "forceToLoadX5ForThirdApp #3 -- mAvailableCoreVersion: " + f1267e + " " + Log.getStackTraceString(new Throwable("#")));
                        if (QbSdk.canLoadX5FirstTimeThirdApp(context)) {
                            writeProperties(context, Integer.toString(f1267e), f, f1266d, Integer.toString(b.b(context)));
                            return;
                        }
                        f1267e = 0;
                        f1266d = null;
                        f = null;
                    }
                }
                if (TbsPVConfig.getInstance(f1263a).isDisableHostBackupCore()) {
                    return;
                }
                if (Thread.currentThread() != Looper.getMainLooper().getThread()) {
                    for (String str3 : coreProviderAppList) {
                        int backupCoreVersion = getBackupCoreVersion(context, str3);
                        if (backupCoreVersion < h2 || backupCoreVersion < i2 || backupCoreVersion <= 0) {
                            int backupDecoupleCoreVersion = getBackupDecoupleCoreVersion(context, str3);
                            if (backupDecoupleCoreVersion >= h2 && backupDecoupleCoreVersion >= i2 && backupDecoupleCoreVersion > 0) {
                                TbsLog.i("TbsShareManager", "find host backup core to unzip forceload decouple coreVersion is " + backupDecoupleCoreVersion + " packageName is " + str3);
                                m.a().a(context, getBackupCoreFile(context, str3), backupDecoupleCoreVersion);
                                TbsLog.i("TbsShareManager", "find host backup decouple core to unzip forceload after unzip ");
                                return;
                            }
                        } else {
                            TbsLog.i("TbsShareManager", "find host backup core to unzip forceload coreVersion is " + backupCoreVersion + " packageName is " + str3);
                            m.a().a(context, getBackupCoreFile(context, str3), backupCoreVersion);
                            TbsLog.i("TbsShareManager", "find host backup core to unzip forceload after unzip ");
                            return;
                        }
                    }
                    return;
                }
                TbsLog.i("TbsShareManager", "in mainthread so do not find host backup core to install ");
                return;
            }
            f1266d = f1265c;
            f = "AppDefined";
            f1267e = a2;
            TbsLog.i("TbsShareManager", "forceToLoadX5ForThirdApp #1 -- mAvailableCoreVersion: " + f1267e + " " + Log.getStackTraceString(new Throwable("#")));
            writeProperties(context, Integer.toString(f1267e), f, f1266d, Integer.toString(1));
        } catch (Exception unused) {
        }
    }

    public static String g(Context context) {
        try {
            n(context);
            if (f1266d != null && !TextUtils.isEmpty(f1266d)) {
                return f1266d + File.separator + "res.apk";
            }
            return null;
        } catch (Throwable th) {
            StringBuilder a2 = a.a("getTbsResourcesPath exception: ");
            a2.append(Log.getStackTraceString(th));
            a2.toString();
            return null;
        }
    }

    public static File getBackupCoreFile(Context context, String str) {
        try {
            File file = new File(new File(FileUtil.a(getPackageContext(context, str, false), 4)), TbsDownloader.getBackupFileName(false));
            if (file.exists()) {
                return file;
            }
            return null;
        } catch (Throwable unused) {
            return null;
        }
    }

    public static int getBackupCoreVersion(Context context, String str) {
        try {
            File file = new File(new File(FileUtil.a(getPackageContext(context, str, false), 4)), TbsDownloader.getBackupFileName(false));
            if (file.exists()) {
                return com.tencent.smtt.utils.a.b(file);
            }
        } catch (Throwable unused) {
        }
        return 0;
    }

    public static File getBackupDecoupleCoreFile(Context context, String str) {
        try {
            File file = new File(new File(FileUtil.a(getPackageContext(context, str, true), 4)), TbsDownloader.getBackupFileName(true));
            if (file.exists()) {
                return file;
            }
            return null;
        } catch (Throwable unused) {
            return null;
        }
    }

    public static int getBackupDecoupleCoreVersion(Context context, String str) {
        try {
            File file = new File(new File(FileUtil.a(getPackageContext(context, str, false), 4)), TbsDownloader.getBackupFileName(true));
            if (file.exists()) {
                return com.tencent.smtt.utils.a.b(file);
            }
        } catch (Throwable unused) {
        }
        return 0;
    }

    public static boolean getCoreDisabled() {
        return g;
    }

    public static boolean getCoreFormOwn() {
        return k;
    }

    public static String[] getCoreProviderAppList() {
        return new String[]{TbsConfig.APP_DEMO, TbsConfig.APP_WX, TbsConfig.APP_QQ, TbsConfig.APP_QZONE, "com.tencent.qqlite"};
    }

    public static int getCoreShareDecoupleCoreVersion(Context context, String str) {
        Context packageContext = getPackageContext(context, str, true);
        if (packageContext != null) {
            return m.a().h(packageContext);
        }
        return 0;
    }

    public static String getHostCorePathAppDefined() {
        return f1265c;
    }

    public static long getHostCoreVersions(Context context) {
        long sharedTbsCoreVersion;
        long j2;
        String[] coreProviderAppList = getCoreProviderAppList();
        long j3 = 0;
        for (String str : coreProviderAppList) {
            if (str.equalsIgnoreCase(TbsConfig.APP_WX)) {
                sharedTbsCoreVersion = getSharedTbsCoreVersion(context, str);
                j2 = 10000000000L;
            } else if (str.equalsIgnoreCase(TbsConfig.APP_QQ)) {
                sharedTbsCoreVersion = getSharedTbsCoreVersion(context, str);
                j2 = 100000;
            } else {
                if (str.equalsIgnoreCase(TbsConfig.APP_QZONE)) {
                    j3 += getSharedTbsCoreVersion(context, str);
                }
            }
            j3 = (sharedTbsCoreVersion * j2) + j3;
        }
        return j3;
    }

    public static Context getPackageContext(Context context, String str, boolean z) {
        if (z) {
            try {
                if (!context.getPackageName().equals(str) && TbsPVConfig.getInstance(context).isEnableNoCoreGray()) {
                    TbsLog.i("TbsShareManager", "gray no core app,skip get context");
                    return null;
                }
            } catch (PackageManager.NameNotFoundException unused) {
                return null;
            } catch (Exception e2) {
                e2.printStackTrace();
                return null;
            }
        }
        return context.createPackageContext(str, 2);
    }

    public static int getSharedTbsCoreVersion(Context context, String str) {
        Context packageContext = getPackageContext(context, str, true);
        if (packageContext != null) {
            return m.a().i(packageContext);
        }
        return 0;
    }

    public static File getTbsShareFile(Context context, String str) {
        File r = m.a().r(context);
        if (r == null) {
            return null;
        }
        File file = new File(r, str);
        if (file.exists()) {
            return file;
        }
        try {
            file.createNewFile();
            return file;
        } catch (IOException e2) {
            e2.printStackTrace();
            return null;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r6v11 */
    public static synchronized int h(Context context) {
        Throwable th;
        BufferedInputStream bufferedInputStream;
        Throwable th2;
        synchronized (TbsShareManager.class) {
            TbsLog.i("TbsShareManager", "readCoreVersionFromConfig #1");
            try {
            } catch (Throwable th3) {
                th = th3;
            }
            try {
                File tbsShareFile = getTbsShareFile(context, "core_info");
                if (tbsShareFile == null) {
                    TbsLog.i("TbsShareManager", "readCoreVersionFromConfig #2");
                    return 0;
                }
                bufferedInputStream = new BufferedInputStream(new FileInputStream(tbsShareFile));
                try {
                    Properties properties = new Properties();
                    properties.load(bufferedInputStream);
                    String property = properties.getProperty("core_version", BuildConfig.FLAVOR);
                    if (!BuildConfig.FLAVOR.equals(property)) {
                        TbsLog.i("TbsShareManager", "readCoreVersionFromConfig #3");
                        int max = Math.max(Integer.parseInt(property), 0);
                        try {
                            bufferedInputStream.close();
                        } catch (Exception e2) {
                            e2.printStackTrace();
                        }
                        return max;
                    }
                    TbsLog.i("TbsShareManager", "readCoreVersionFromConfig #4");
                    try {
                        bufferedInputStream.close();
                    } catch (Exception e3) {
                        e3.printStackTrace();
                    }
                    return 0;
                } catch (Throwable th4) {
                    th2 = th4;
                    th2.printStackTrace();
                    if (bufferedInputStream != null) {
                        try {
                            bufferedInputStream.close();
                        } catch (Exception e4) {
                            e4.printStackTrace();
                        }
                    }
                    TbsLog.i("TbsShareManager", "readCoreVersionFromConfig #5");
                    return -2;
                }
            } catch (Throwable th5) {
                th = th5;
                context = 0;
                if (context != 0) {
                    try {
                        context.close();
                    } catch (Exception e5) {
                        e5.printStackTrace();
                    }
                }
                throw th;
            }
        }
    }

    public static boolean i(Context context) {
        try {
            if (f1267e == 0) {
                findCoreForThirdPartyApp(context);
            }
            if (f1267e == 0) {
                TbsLog.addLog(TbsLog.TBSLOG_CODE_SDK_NO_SHARE_X5CORE, null, new Object[0]);
                return false;
            }
            if (f1265c == null) {
                if (f1267e != 0 && getSharedTbsCoreVersion(context, f) == f1267e) {
                    return true;
                }
            } else if (f1267e != 0 && m.a().a(f1265c) == f1267e) {
                return true;
            }
            if (l(context)) {
                return true;
            }
            TbsCoreLoadStat instance = TbsCoreLoadStat.getInstance();
            instance.a(context, TbsListener.ErrorCode.INFO_CORE_EXIST_NOT_LOAD, new Throwable("mAvailableCoreVersion=" + f1267e + "; mSrcPackageName=" + f + "; getSharedTbsCoreVersion(ctx, mSrcPackageName) is " + getSharedTbsCoreVersion(context, f) + "; getHostCoreVersions is " + getHostCoreVersions(context)));
            f1266d = null;
            f1267e = 0;
            TbsLog.addLog(TbsLog.TBSLOG_CODE_SDK_CONFLICT_X5CORE, null, new Object[0]);
            QbSdk.a(context, "TbsShareManager::isShareTbsCoreAvailableInner forceSysWebViewInner!");
            return false;
        } catch (Throwable th) {
            th.printStackTrace();
            TbsLog.addLog(TbsLog.TBSLOG_CODE_SDK_UNAVAIL_X5CORE, null, new Object[0]);
            return false;
        }
    }

    public static boolean isThirdPartyApp(Context context) {
        try {
        } catch (Throwable th) {
            th.printStackTrace();
        }
        if (f1263a != null && f1263a.equals(context.getApplicationContext())) {
            return f1264b;
        }
        f1263a = context.getApplicationContext();
        String packageName = f1263a.getPackageName();
        for (String str : getCoreProviderAppList()) {
            if (packageName.equals(str)) {
                f1264b = false;
                return false;
            }
        }
        f1264b = true;
        return true;
    }

    public static boolean j(Context context) {
        return b(context, true);
    }

    public static boolean k(Context context) {
        String str = f;
        if (str == null) {
            return false;
        }
        return f1267e == getSharedTbsCoreVersion(context, str) || f1267e == getCoreShareDecoupleCoreVersion(context, f);
    }

    public static boolean l(Context context) {
        String str;
        File c2;
        if (QbSdk.k) {
            return false;
        }
        String[] coreProviderAppList = getCoreProviderAppList();
        int length = coreProviderAppList.length;
        int i2 = 0;
        while (true) {
            if (i2 < length) {
                str = coreProviderAppList[i2];
                int i3 = f1267e;
                if (i3 > 0 && i3 == getSharedTbsCoreVersion(context, str)) {
                    Context packageContext = getPackageContext(context, str, true);
                    if (m.a().f(context)) {
                        c2 = m.a().b(context, packageContext);
                        break;
                    }
                }
                i2++;
            } else {
                int length2 = coreProviderAppList.length;
                for (int i4 = 0; i4 < length2; i4++) {
                    str = coreProviderAppList[i4];
                    int i5 = f1267e;
                    if (i5 > 0 && i5 == getCoreShareDecoupleCoreVersion(context, str)) {
                        Context packageContext2 = getPackageContext(context, str, true);
                        if (m.a().f(context)) {
                            c2 = m.a().c(context, packageContext2);
                        }
                    }
                }
                return false;
            }
        }
        f1266d = c2.getAbsolutePath();
        f = str;
        return true;
    }

    public static boolean m(Context context) {
        if (context == null) {
            return false;
        }
        writeProperties(context, Integer.toString(0), BuildConfig.FLAVOR, BuildConfig.FLAVOR, Integer.toString(0));
        return true;
    }

    /* JADX WARN: Removed duplicated region for block: B:67:0x00f7 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static void n(android.content.Context r8) {
        /*
            Method dump skipped, instructions count: 259
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.sdk.TbsShareManager.n(android.content.Context):void");
    }

    public static void setHostCorePathAppDefined(String str) {
        f1265c = str;
    }

    /* JADX WARN: Code restructure failed: missing block: B:68:0x01d7, code lost:
        if (r6.equals(r11.getApplicationContext().getPackageName()) != false) goto L_0x01fa;
     */
    /* JADX WARN: Code restructure failed: missing block: B:69:0x01d9, code lost:
        com.tencent.smtt.utils.TbsLog.i("TbsShareManager", "thirdAPP pre--> delete old core_share Directory:" + r12);
        com.tencent.smtt.sdk.k.a(com.tencent.smtt.sdk.TbsShareManager.f1263a).a("remove_old_core", 1);
     */
    /* JADX WARN: Code restructure failed: missing block: B:70:0x01fa, code lost:
        writeProperties(r11, java.lang.Integer.toString(r12), r6, r8, java.lang.Integer.toString(r9));
     */
    /* JADX WARN: Code restructure failed: missing block: B:71:0x0205, code lost:
        r1 = getTbsShareFile(r11, "core_info");
     */
    /* JADX WARN: Code restructure failed: missing block: B:72:0x020d, code lost:
        if (com.tencent.smtt.sdk.TbsShareManager.i != false) goto L_0x02e0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:73:0x020f, code lost:
        if (r1 == null) goto L_0x02e0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:74:0x0211, code lost:
        r2 = new com.tencent.smtt.sdk.TbsLinuxToolsJni(com.tencent.smtt.sdk.TbsShareManager.f1263a);
        r2.a(r1.getAbsolutePath(), "644");
        r2.a(com.tencent.smtt.sdk.m.a().r(r11).getAbsolutePath(), "755");
        com.tencent.smtt.sdk.TbsShareManager.i = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:75:0x0236, code lost:
        r1 = th;
     */
    /* JADX WARN: Code restructure failed: missing block: B:76:0x0237, code lost:
        r1.printStackTrace();
     */
    /* JADX WARN: Code restructure failed: missing block: B:83:0x026e, code lost:
        if (r6.equals(r11.getApplicationContext().getPackageName()) != false) goto L_0x029d;
     */
    /* JADX WARN: Code restructure failed: missing block: B:84:0x0270, code lost:
        com.tencent.smtt.utils.TbsLog.i("TbsShareManager", "thirdAPP pre--> delete old core_share Directory:" + r12);
     */
    /* JADX WARN: Code restructure failed: missing block: B:85:0x028e, code lost:
        com.tencent.smtt.utils.FileUtil.a(com.tencent.smtt.sdk.m.a().q(r11), false);
        com.tencent.smtt.utils.TbsLog.i("TbsShareManager", "thirdAPP success--> delete old core_share Directory");
     */
    /* JADX WARN: Code restructure failed: missing block: B:86:0x029d, code lost:
        writeProperties(r11, java.lang.Integer.toString(r12), r6, r8, java.lang.Integer.toString(r9));
     */
    /* JADX WARN: Code restructure failed: missing block: B:87:0x02a8, code lost:
        r1 = getTbsShareFile(r11, "core_info");
     */
    /* JADX WARN: Code restructure failed: missing block: B:88:0x02b0, code lost:
        if (com.tencent.smtt.sdk.TbsShareManager.i != false) goto L_0x02e0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:89:0x02b2, code lost:
        if (r1 == null) goto L_0x02e0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:90:0x02b4, code lost:
        r2 = new com.tencent.smtt.sdk.TbsLinuxToolsJni(com.tencent.smtt.sdk.TbsShareManager.f1263a);
        r2.a(r1.getAbsolutePath(), "644");
        r2.a(com.tencent.smtt.sdk.m.a().r(r11).getAbsolutePath(), "755");
        com.tencent.smtt.sdk.TbsShareManager.i = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:91:0x02d8, code lost:
        r1 = th;
     */
    /* JADX WARN: Removed duplicated region for block: B:95:0x02e2 A[ADDED_TO_REGION] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static synchronized void writeCoreInfoForThirdPartyApp(android.content.Context r11, int r12, boolean r13) {
        /*
            Method dump skipped, instructions count: 748
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.sdk.TbsShareManager.writeCoreInfoForThirdPartyApp(android.content.Context, int, boolean):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:61:0x00f3 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:67:0x00fd A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static void writeProperties(android.content.Context r7, java.lang.String r8, java.lang.String r9, java.lang.String r10, java.lang.String r11) {
        /*
            Method dump skipped, instructions count: 262
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.sdk.TbsShareManager.writeProperties(android.content.Context, java.lang.String, java.lang.String, java.lang.String, java.lang.String):void");
    }

    public static String[] d(Context context, boolean z) {
        if (QbSdk.k) {
            return new String[]{context.getApplicationContext().getPackageName()};
        }
        return z ? new String[]{context.getApplicationContext().getPackageName()} : getCoreProviderAppList();
    }
}
