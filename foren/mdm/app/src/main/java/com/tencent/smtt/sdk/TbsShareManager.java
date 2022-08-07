package com.tencent.smtt.sdk;

import android.content.Context;
import android.content.pm.PackageManager;
import com.tencent.smtt.sdk.TbsListener;
import com.tencent.smtt.utils.TbsLog;
import com.tencent.smtt.utils.b;
import com.tencent.smtt.utils.k;
import com.umeng.analytics.social.e;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/* loaded from: classes2.dex */
public class TbsShareManager {
    private static Context a;
    private static boolean b;
    private static String c = null;
    private static int d = 0;
    private static String e = null;
    private static boolean f = false;
    private static boolean g = false;
    private static String h = null;
    private static boolean i = false;
    private static boolean j = false;

    public static int a(Context context, boolean z) {
        b(context, z);
        return d;
    }

    public static Context a(Context context, String str) {
        try {
            return context.createPackageContext(str, 2);
        } catch (PackageManager.NameNotFoundException e2) {
            return null;
        } catch (Exception e3) {
            e3.printStackTrace();
            return null;
        }
    }

    public static void a(Context context) {
        try {
            TbsLinuxToolsJni tbsLinuxToolsJni = new TbsLinuxToolsJni(context);
            a(context, tbsLinuxToolsJni, aj.a().h(context));
            tbsLinuxToolsJni.a(aj.a().i(context).getAbsolutePath(), "755");
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    private static void a(Context context, TbsLinuxToolsJni tbsLinuxToolsJni, File file) {
        if (file != null && file.exists() && file.isDirectory()) {
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

    private static File b(Context context, String str) {
        File i2 = aj.a().i(context);
        if (i2 == null) {
            return null;
        }
        File file = new File(i2, str);
        if (file != null && file.exists()) {
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

    public static String b(Context context) {
        h(context);
        return c;
    }

    static boolean b(Context context, boolean z) {
        if (g(context)) {
            return true;
        }
        if (z) {
            QbSdk.a(context, "TbsShareManager::isShareTbsCoreAvailable forceSysWebViewInner!");
        }
        return false;
    }

    public static int c(Context context) {
        return a(context, true);
    }

    /* JADX WARN: Removed duplicated region for block: B:56:0x0093 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:63:0x008e A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static void c(android.content.Context r8, boolean r9) {
        /*
            r1 = 0
            r0 = 0
            r2 = 0
            java.lang.String r3 = "core_info"
            java.io.File r4 = b(r8, r3)     // Catch: Throwable -> 0x007a, all -> 0x008a
            if (r4 != 0) goto L_0x0016
            if (r1 == 0) goto L_0x0010
            r0.close()     // Catch: Exception -> 0x009d
        L_0x0010:
            if (r1 == 0) goto L_0x0015
            r2.close()     // Catch: Exception -> 0x0078
        L_0x0015:
            return
        L_0x0016:
            java.io.FileInputStream r0 = new java.io.FileInputStream     // Catch: Throwable -> 0x007a, all -> 0x008a
            r0.<init>(r4)     // Catch: Throwable -> 0x007a, all -> 0x008a
            java.io.BufferedInputStream r3 = new java.io.BufferedInputStream     // Catch: Throwable -> 0x007a, all -> 0x008a
            r3.<init>(r0)     // Catch: Throwable -> 0x007a, all -> 0x008a
            java.util.Properties r0 = new java.util.Properties     // Catch: Throwable -> 0x00aa, all -> 0x00a2
            r0.<init>()     // Catch: Throwable -> 0x00aa, all -> 0x00a2
            r0.load(r3)     // Catch: Throwable -> 0x00aa, all -> 0x00a2
            java.lang.String r2 = "core_disabled"
            r5 = 0
            java.lang.String r5 = java.lang.String.valueOf(r5)     // Catch: Throwable -> 0x00aa, all -> 0x00a2
            r0.setProperty(r2, r5)     // Catch: Throwable -> 0x00aa, all -> 0x00a2
            if (r9 == 0) goto L_0x005f
            com.tencent.smtt.sdk.aj r2 = com.tencent.smtt.sdk.aj.a()     // Catch: Throwable -> 0x00aa, all -> 0x00a2
            java.io.File r2 = r2.h(r8)     // Catch: Throwable -> 0x00aa, all -> 0x00a2
            java.lang.String r2 = r2.getAbsolutePath()     // Catch: Throwable -> 0x00aa, all -> 0x00a2
            android.content.Context r5 = r8.getApplicationContext()     // Catch: Throwable -> 0x00aa, all -> 0x00a2
            java.lang.String r5 = r5.getPackageName()     // Catch: Throwable -> 0x00aa, all -> 0x00a2
            int r6 = com.tencent.smtt.utils.b.b(r8)     // Catch: Throwable -> 0x00aa, all -> 0x00a2
            java.lang.String r7 = "core_packagename"
            r0.setProperty(r7, r5)     // Catch: Throwable -> 0x00aa, all -> 0x00a2
            java.lang.String r5 = "core_path"
            r0.setProperty(r5, r2)     // Catch: Throwable -> 0x00aa, all -> 0x00a2
            java.lang.String r2 = "app_version"
            java.lang.String r5 = java.lang.String.valueOf(r6)     // Catch: Throwable -> 0x00aa, all -> 0x00a2
            r0.setProperty(r2, r5)     // Catch: Throwable -> 0x00aa, all -> 0x00a2
        L_0x005f:
            java.io.FileOutputStream r5 = new java.io.FileOutputStream     // Catch: Throwable -> 0x00aa, all -> 0x00a2
            r5.<init>(r4)     // Catch: Throwable -> 0x00aa, all -> 0x00a2
            java.io.BufferedOutputStream r2 = new java.io.BufferedOutputStream     // Catch: Throwable -> 0x00aa, all -> 0x00a2
            r2.<init>(r5)     // Catch: Throwable -> 0x00aa, all -> 0x00a2
            r1 = 0
            r0.store(r2, r1)     // Catch: Throwable -> 0x00ad, all -> 0x00a4
            if (r3 == 0) goto L_0x0072
            r3.close()     // Catch: Exception -> 0x00a0
        L_0x0072:
            if (r2 == 0) goto L_0x0015
            r2.close()     // Catch: Exception -> 0x0078
            goto L_0x0015
        L_0x0078:
            r0 = move-exception
            goto L_0x0015
        L_0x007a:
            r0 = move-exception
            r2 = r1
        L_0x007c:
            r0.printStackTrace()     // Catch: all -> 0x00a7
            if (r2 == 0) goto L_0x0084
            r2.close()     // Catch: Exception -> 0x009b
        L_0x0084:
            if (r1 == 0) goto L_0x0015
            r1.close()     // Catch: Exception -> 0x0078
            goto L_0x0015
        L_0x008a:
            r0 = move-exception
            r3 = r1
        L_0x008c:
            if (r3 == 0) goto L_0x0091
            r3.close()     // Catch: Exception -> 0x0097
        L_0x0091:
            if (r1 == 0) goto L_0x0096
            r1.close()     // Catch: Exception -> 0x0099
        L_0x0096:
            throw r0
        L_0x0097:
            r2 = move-exception
            goto L_0x0091
        L_0x0099:
            r1 = move-exception
            goto L_0x0096
        L_0x009b:
            r0 = move-exception
            goto L_0x0084
        L_0x009d:
            r0 = move-exception
            goto L_0x0010
        L_0x00a0:
            r0 = move-exception
            goto L_0x0072
        L_0x00a2:
            r0 = move-exception
            goto L_0x008c
        L_0x00a4:
            r0 = move-exception
            r1 = r2
            goto L_0x008c
        L_0x00a7:
            r0 = move-exception
            r3 = r2
            goto L_0x008c
        L_0x00aa:
            r0 = move-exception
            r2 = r3
            goto L_0x007c
        L_0x00ad:
            r0 = move-exception
            r1 = r2
            r2 = r3
            goto L_0x007c
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.sdk.TbsShareManager.c(android.content.Context, boolean):void");
    }

    public static Context d(Context context) {
        h(context);
        if (e == null) {
            return null;
        }
        Context a2 = a(context, e);
        if (!aj.a().c(a2)) {
            return null;
        }
        return a2;
    }

    public static synchronized String e(Context context) {
        BufferedInputStream bufferedInputStream;
        BufferedInputStream bufferedInputStream2;
        Throwable th;
        File b2;
        String str = null;
        synchronized (TbsShareManager.class) {
            try {
                try {
                    bufferedInputStream2 = null;
                } catch (Throwable th2) {
                    th = th2;
                }
                try {
                    b2 = b(context, "core_info");
                } catch (Throwable th3) {
                    th = th3;
                    bufferedInputStream = null;
                    if (bufferedInputStream != null) {
                        try {
                            bufferedInputStream.close();
                        } catch (Exception e2) {
                        }
                    }
                    throw th;
                }
            } catch (Exception e3) {
            }
            if (b2 != null) {
                bufferedInputStream = new BufferedInputStream(new FileInputStream(b2));
                try {
                    Properties properties = new Properties();
                    properties.load(bufferedInputStream);
                    String property = properties.getProperty("core_packagename", "");
                    if (!"".equals(property)) {
                        if (bufferedInputStream != null) {
                            try {
                                bufferedInputStream.close();
                            } catch (Exception e4) {
                            }
                        }
                        str = property;
                    } else if (bufferedInputStream != null) {
                        bufferedInputStream.close();
                    }
                } catch (Throwable th4) {
                    th = th4;
                    th.printStackTrace();
                    if (bufferedInputStream != null) {
                        bufferedInputStream.close();
                    }
                    return str;
                }
            } else if (0 != 0) {
                bufferedInputStream2.close();
            }
        }
        return str;
    }

    /* JADX WARN: Removed duplicated region for block: B:56:0x005d A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    static synchronized int f(android.content.Context r6) {
        /*
            r0 = 0
            java.lang.Class<com.tencent.smtt.sdk.TbsShareManager> r3 = com.tencent.smtt.sdk.TbsShareManager.class
            monitor-enter(r3)
            r2 = 0
            java.lang.String r1 = "core_info"
            java.io.File r1 = b(r6, r1)     // Catch: Throwable -> 0x004d, all -> 0x0059
            if (r1 != 0) goto L_0x0014
            if (r2 == 0) goto L_0x0012
            r2.close()     // Catch: Exception -> 0x006b, all -> 0x004a
        L_0x0012:
            monitor-exit(r3)
            return r0
        L_0x0014:
            java.io.FileInputStream r4 = new java.io.FileInputStream     // Catch: Throwable -> 0x004d, all -> 0x0059
            r4.<init>(r1)     // Catch: Throwable -> 0x004d, all -> 0x0059
            java.io.BufferedInputStream r1 = new java.io.BufferedInputStream     // Catch: Throwable -> 0x004d, all -> 0x0059
            r1.<init>(r4)     // Catch: Throwable -> 0x004d, all -> 0x0059
            java.util.Properties r2 = new java.util.Properties     // Catch: Throwable -> 0x007d, all -> 0x007b
            r2.<init>()     // Catch: Throwable -> 0x007d, all -> 0x007b
            r2.load(r1)     // Catch: Throwable -> 0x007d, all -> 0x007b
            java.lang.String r4 = "core_version"
            java.lang.String r5 = ""
            java.lang.String r2 = r2.getProperty(r4, r5)     // Catch: Throwable -> 0x007d, all -> 0x007b
            java.lang.String r4 = ""
            boolean r4 = r4.equals(r2)     // Catch: Throwable -> 0x007d, all -> 0x007b
            if (r4 != 0) goto L_0x0070
            int r0 = java.lang.Integer.parseInt(r2)     // Catch: Throwable -> 0x007d, all -> 0x007b
            r2 = 0
            int r0 = java.lang.Math.max(r0, r2)     // Catch: Throwable -> 0x007d, all -> 0x007b
            if (r1 == 0) goto L_0x0012
            r1.close()     // Catch: Exception -> 0x0045, all -> 0x004a
            goto L_0x0012
        L_0x0045:
            r1 = move-exception
            r1.printStackTrace()     // Catch: all -> 0x004a
            goto L_0x0012
        L_0x004a:
            r0 = move-exception
            monitor-exit(r3)
            throw r0
        L_0x004d:
            r0 = move-exception
            r1 = r2
        L_0x004f:
            r0.printStackTrace()     // Catch: all -> 0x007b
            if (r1 == 0) goto L_0x0057
            r1.close()     // Catch: Exception -> 0x0066, all -> 0x004a
        L_0x0057:
            r0 = -2
            goto L_0x0012
        L_0x0059:
            r0 = move-exception
            r1 = r2
        L_0x005b:
            if (r1 == 0) goto L_0x0060
            r1.close()     // Catch: Exception -> 0x0061, all -> 0x004a
        L_0x0060:
            throw r0     // Catch: all -> 0x004a
        L_0x0061:
            r1 = move-exception
            r1.printStackTrace()     // Catch: all -> 0x004a
            goto L_0x0060
        L_0x0066:
            r0 = move-exception
            r0.printStackTrace()     // Catch: all -> 0x004a
            goto L_0x0057
        L_0x006b:
            r1 = move-exception
            r1.printStackTrace()     // Catch: all -> 0x004a
            goto L_0x0012
        L_0x0070:
            if (r1 == 0) goto L_0x0012
            r1.close()     // Catch: Exception -> 0x0076, all -> 0x004a
            goto L_0x0012
        L_0x0076:
            r1 = move-exception
            r1.printStackTrace()     // Catch: all -> 0x004a
            goto L_0x0012
        L_0x007b:
            r0 = move-exception
            goto L_0x005b
        L_0x007d:
            r0 = move-exception
            goto L_0x004f
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.sdk.TbsShareManager.f(android.content.Context):int");
    }

    public static int findCoreForThirdPartyApp(Context context) {
        l(context);
        TbsLog.i("TbsShareManager", "core_info mAvailableCoreVersion is " + d + " mAvailableCorePath is " + c + " mSrcPackageName is " + e);
        if (!i(context) && !j(context)) {
            d = 0;
            c = null;
            e = null;
            TbsLog.i("TbsShareManager", "core_info error checkCoreInfo is false and checkCoreInOthers is false ");
        }
        if (d > 0 && (QbSdk.a(context, d) || f)) {
            d = 0;
            c = null;
            e = null;
            TbsLog.i("TbsShareManager", "core_info error QbSdk.isX5Disabled ");
        }
        return d;
    }

    public static boolean forceLoadX5FromTBSDemo(Context context) {
        int sharedTbsCoreVersion;
        if (context == null || aj.a().a(context, (File[]) null) || (sharedTbsCoreVersion = getSharedTbsCoreVersion(context, TbsConfig.APP_DEMO)) <= 0) {
            return false;
        }
        writeProperties(context, Integer.toString(sharedTbsCoreVersion), TbsConfig.APP_DEMO, aj.a().h(a(context, TbsConfig.APP_DEMO)).getAbsolutePath(), "1");
        return true;
    }

    public static void forceToLoadX5ForThirdApp(Context context, boolean z) {
        File i2;
        File file;
        try {
            if (!isThirdPartyApp(context) || (i2 = aj.a().i(context)) == null) {
                return;
            }
            if (!z || (file = new File(i2, "core_info")) == null || !file.exists()) {
                String[] coreProviderAppList = getCoreProviderAppList();
                for (String str : coreProviderAppList) {
                    int sharedTbsCoreVersion = getSharedTbsCoreVersion(context, str);
                    if (sharedTbsCoreVersion > 0) {
                        c = aj.a().b(context, a(context, str)).getAbsolutePath();
                        e = str;
                        d = sharedTbsCoreVersion;
                        if (QbSdk.canLoadX5FirstTimeThirdApp(context)) {
                            writeProperties(context, Integer.toString(d), e, c, Integer.toString(b.b(context)));
                            return;
                        }
                        d = 0;
                        c = null;
                        e = null;
                    }
                }
            }
        } catch (Exception e2) {
        }
    }

    public static boolean g(Context context) {
        try {
            if (d == 0) {
                findCoreForThirdPartyApp(context);
            }
            if (d == 0) {
                TbsLog.addLog(TbsLog.TBSLOG_CODE_SDK_NO_SHARE_X5CORE, null, new Object[0]);
                return false;
            }
            if ((d == 0 || getSharedTbsCoreVersion(context, e) != d) && !j(context)) {
                TbsCoreLoadStat.getInstance().a(context, TbsListener.ErrorCode.INFO_CORE_EXIST_NOT_LOAD, new Throwable("mAvailableCoreVersion=" + d + "; mSrcPackageName=" + e + "; getSharedTbsCoreVersion(ctx, mSrcPackageName) is " + getSharedTbsCoreVersion(context, e) + "; getHostCoreVersions is " + getHostCoreVersions(context)));
                c = null;
                d = 0;
                TbsLog.addLog(TbsLog.TBSLOG_CODE_SDK_CONFLICT_X5CORE, null, new Object[0]);
                QbSdk.a(context, "TbsShareManager::isShareTbsCoreAvailableInner forceSysWebViewInner!");
                return false;
            }
            return true;
        } catch (Throwable th) {
            th.printStackTrace();
            TbsLog.addLog(TbsLog.TBSLOG_CODE_SDK_UNAVAIL_X5CORE, null, new Object[0]);
            return false;
        }
    }

    public static boolean getCoreDisabled() {
        return f;
    }

    public static boolean getCoreFormOwn() {
        return i;
    }

    public static String[] getCoreProviderAppList() {
        return new String[]{TbsConfig.APP_DEMO, "com.tencent.mm", "com.tencent.mobileqq", "com.qzone"};
    }

    public static long getHostCoreVersions(Context context) {
        long j2 = 0;
        String[] coreProviderAppList = getCoreProviderAppList();
        for (String str : coreProviderAppList) {
            if (str.equalsIgnoreCase("com.tencent.mm")) {
                j2 += getSharedTbsCoreVersion(context, str) * 10000000000L;
            } else if (str.equalsIgnoreCase("com.tencent.mobileqq")) {
                j2 += getSharedTbsCoreVersion(context, str) * 100000;
            } else if (str.equalsIgnoreCase("com.qzone")) {
                j2 += getSharedTbsCoreVersion(context, str);
            }
        }
        return j2;
    }

    public static int getSharedTbsCoreVersion(Context context, String str) {
        Context a2 = a(context, str);
        if (a2 != null) {
            return aj.a().d(a2);
        }
        return 0;
    }

    public static boolean h(Context context) {
        return b(context, true);
    }

    private static boolean i(Context context) {
        return e != null && d == getSharedTbsCoreVersion(context, e);
    }

    public static boolean isThirdPartyApp(Context context) {
        try {
        } catch (Throwable th) {
            th.printStackTrace();
        }
        if (a != null && a.equals(context.getApplicationContext())) {
            return b;
        }
        a = context.getApplicationContext();
        String packageName = a.getPackageName();
        for (String str : getCoreProviderAppList()) {
            if (packageName.equals(str)) {
                b = false;
                return false;
            }
        }
        b = true;
        return true;
    }

    private static boolean j(Context context) {
        String[] coreProviderAppList = getCoreProviderAppList();
        for (String str : coreProviderAppList) {
            if (d > 0 && d == getSharedTbsCoreVersion(context, str)) {
                Context a2 = a(context, str);
                if (aj.a().c(context)) {
                    c = aj.a().b(context, a2).getAbsolutePath();
                    e = str;
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean k(Context context) {
        if (context == null) {
            return false;
        }
        writeProperties(context, Integer.toString(0), "", "", Integer.toString(0));
        return true;
    }

    /* JADX WARN: Removed duplicated region for block: B:66:0x00d3 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static synchronized void l(android.content.Context r5) {
        /*
            Method dump skipped, instructions count: 225
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.sdk.TbsShareManager.l(android.content.Context):void");
    }

    public static synchronized void writeCoreInfoForThirdPartyApp(Context context, int i2, boolean z) {
        int i3 = 0;
        synchronized (TbsShareManager.class) {
            if (i2 != 0) {
                int f2 = f(context);
                if (f2 >= 0) {
                    if (i2 != f2) {
                        if (i2 >= f2) {
                            String[] coreProviderAppList = getCoreProviderAppList();
                            if (z) {
                                coreProviderAppList = new String[]{context.getApplicationContext().getPackageName()};
                            }
                            int length = coreProviderAppList.length;
                            while (true) {
                                if (i3 >= length) {
                                    break;
                                }
                                String str = coreProviderAppList[i3];
                                if (i2 == getSharedTbsCoreVersion(context, str)) {
                                    Context a2 = a(context, str);
                                    String absolutePath = aj.a().h(a2).getAbsolutePath();
                                    int b2 = b.b(context);
                                    if (aj.a().c(a2)) {
                                        if (!str.equals(context.getApplicationContext().getPackageName())) {
                                            TbsLog.i("TbsShareManager", "thirdAPP pre--> delete old core_share Directory:" + i2);
                                            k.b(aj.a().h(context));
                                            TbsLog.i("TbsShareManager", "thirdAPP success--> delete old core_share Directory");
                                        }
                                        writeProperties(context, Integer.toString(i2), str, absolutePath, Integer.toString(b2));
                                        File b3 = b(context, "core_info");
                                        if (!g && b3 != null) {
                                            TbsLinuxToolsJni tbsLinuxToolsJni = new TbsLinuxToolsJni(a);
                                            tbsLinuxToolsJni.a(b3.getAbsolutePath(), "644");
                                            tbsLinuxToolsJni.a(aj.a().i(context).getAbsolutePath(), "755");
                                            g = true;
                                        }
                                    }
                                }
                                i3++;
                            }
                        } else {
                            k(context);
                            TbsDownloadConfig.getInstance(a).setDownloadInterruptCode(e.t);
                        }
                    } else {
                        c(context, z);
                        TbsDownloadConfig.getInstance(a).setDownloadInterruptCode(-403);
                    }
                } else {
                    TbsDownloadConfig.getInstance(a).setDownloadInterruptCode(-402);
                }
            } else {
                k(context);
                TbsDownloadConfig.getInstance(a).setDownloadInterruptCode(-401);
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:62:0x00a5 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:66:0x00aa A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Type inference failed for: r1v0 */
    /* JADX WARN: Type inference failed for: r1v10 */
    /* JADX WARN: Type inference failed for: r1v13 */
    /* JADX WARN: Type inference failed for: r1v14 */
    /* JADX WARN: Type inference failed for: r1v15 */
    /* JADX WARN: Type inference failed for: r1v2 */
    /* JADX WARN: Type inference failed for: r1v5 */
    /* JADX WARN: Type inference failed for: r1v6 */
    /* JADX WARN: Type inference failed for: r1v9 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static void writeProperties(android.content.Context r6, java.lang.String r7, java.lang.String r8, java.lang.String r9, java.lang.String r10) {
        /*
            Method dump skipped, instructions count: 219
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.sdk.TbsShareManager.writeProperties(android.content.Context, java.lang.String, java.lang.String, java.lang.String, java.lang.String):void");
    }
}
