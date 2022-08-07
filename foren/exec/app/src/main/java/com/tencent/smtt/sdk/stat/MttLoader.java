package com.tencent.smtt.sdk.stat;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import com.tencent.smtt.sdk.BuildConfig;
import com.tencent.smtt.sdk.TbsConfig;
import com.tencent.smtt.utils.FileProvider;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.cert.Certificate;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

/* loaded from: classes.dex */
public class MttLoader {
    public static final String CHANNEL_ID = "ChannelID";
    public static final String ENTRY_ID = "entryId";
    @Deprecated
    public static final String KEY_ACTIVITY_NAME = "KEY_ACT";
    @Deprecated
    public static final String KEY_APP_NAME = "KEY_APPNAME";
    public static final String KEY_EUSESTAT = "KEY_EUSESTAT";
    @Deprecated
    public static final String KEY_PACKAGE = "KEY_PKG";
    public static final String KEY_PID = "KEY_PID";
    public static final String MTT_ACTION = "com.tencent.QQBrowser.action.VIEW";
    public static final String MTT_ACTION_SP = "com.tencent.QQBrowser.action.VIEWSP";
    public static final String PID_ARTICLE_NEWS = "21272";
    public static final String PID_MOBILE_QQ = "50079";
    public static final String PID_QQPIM = "50190";
    public static final String PID_QZONE = "10494";
    public static final String PID_WECHAT = "10318";
    public static final String POS_ID = "PosID";
    public static final String QQBROWSER_DIRECT_DOWNLOAD_URL = "https://mdc.html5.qq.com/d/directdown.jsp?channel_id=50079";
    public static final String QQBROWSER_DOWNLOAD_URL = "https://mdc.html5.qq.com/mh?channel_id=50079&u=";
    public static final String QQBROWSER_PARAMS_FROME = ",from=";
    public static final String QQBROWSER_PARAMS_PACKAGENAME = ",packagename=";
    public static final String QQBROWSER_PARAMS_PD = ",product=";
    public static final String QQBROWSER_PARAMS_VERSION = ",version=";
    public static final String QQBROWSER_SCHEME = "mttbrowser://url=";
    public static final int RESULT_INVALID_CONTEXT = 3;
    public static final int RESULT_INVALID_URL = 2;
    public static final int RESULT_NOT_INSTALL_QQBROWSER = 4;
    public static final int RESULT_OK = 0;
    public static final int RESULT_QQBROWSER_LOW = 5;
    public static final int RESULT_UNKNOWN = 1;
    public static final String STAT_KEY = "StatKey";

    /* loaded from: classes.dex */
    public static class BrowserInfo {
        public int browserType = -1;
        public int ver = -1;
        public String quahead = BuildConfig.FLAVOR;
        public String vn = "0";
        public String packageName = null;
    }

    /* loaded from: classes.dex */
    public static class a {

        /* renamed from: a */
        public String f1406a;

        /* renamed from: b */
        public String f1407b;

        public a() {
            this.f1406a = BuildConfig.FLAVOR;
            this.f1407b = BuildConfig.FLAVOR;
        }
    }

    public static int a(Context context) {
        String str = context.getApplicationInfo().processName;
        if (str.equals(TbsConfig.APP_QQ)) {
            return 13;
        }
        if (str.equals(TbsConfig.APP_QZONE)) {
            return 14;
        }
        if (str.equals("com.tencent.WBlog")) {
            return 15;
        }
        return str.equals(TbsConfig.APP_WX) ? 24 : 26;
    }

    public static Uri a(Context context, String str) {
        return FileProvider.a(context, str);
    }

    public static a a(Context context, Uri uri) {
        Intent intent = new Intent(MTT_ACTION);
        intent.setData(uri);
        List<ResolveInfo> queryIntentActivities = context.getPackageManager().queryIntentActivities(intent, 0);
        if (queryIntentActivities.size() <= 0) {
            return null;
        }
        a aVar = new a();
        for (ResolveInfo resolveInfo : queryIntentActivities) {
            String str = resolveInfo.activityInfo.packageName;
            if (str.contains(TbsConfig.APP_QB)) {
                aVar.f1406a = resolveInfo.activityInfo.name;
                aVar.f1407b = resolveInfo.activityInfo.packageName;
                return aVar;
            } else if (str.contains("com.tencent.qbx")) {
                aVar.f1406a = resolveInfo.activityInfo.name;
                aVar.f1407b = resolveInfo.activityInfo.packageName;
            }
        }
        return aVar;
    }

    public static String a(Certificate certificate) {
        byte[] encoded = certificate.getEncoded();
        int length = encoded.length;
        char[] cArr = new char[length * 2];
        for (int i = 0; i < length; i++) {
            byte b2 = encoded[i];
            int i2 = (b2 >> 4) & 15;
            int i3 = i * 2;
            cArr[i3] = (char) (i2 >= 10 ? (i2 + 97) - 10 : i2 + 48);
            int i4 = b2 & 15;
            cArr[i3 + 1] = (char) (i4 >= 10 ? (i4 + 97) - 10 : i4 + 48);
        }
        return new String(cArr);
    }

    public static boolean a(String str) {
        if (str == null || str.length() == 0) {
            return false;
        }
        String trim = str.trim();
        int indexOf = trim.toLowerCase().indexOf("://");
        int indexOf2 = trim.toLowerCase().indexOf(46);
        if (indexOf <= 0 || indexOf2 <= 0 || indexOf <= indexOf2) {
            return trim.toLowerCase().contains("://");
        }
        return false;
    }

    public static BrowserInfo getBrowserInfo(Context context) {
        int i;
        boolean z = context.getApplicationContext().getSharedPreferences("x5_proxy_setting", 0).getBoolean("qb_install_status", false);
        BrowserInfo browserInfo = new BrowserInfo();
        if (z) {
            return browserInfo;
        }
        try {
            PackageManager packageManager = context.getPackageManager();
            PackageInfo packageInfo = null;
            try {
                packageInfo = packageManager.getPackageInfo(TbsConfig.APP_QB, 0);
                browserInfo.browserType = 2;
                browserInfo.packageName = TbsConfig.APP_QB;
                browserInfo.quahead = "ADRQB_";
                if (packageInfo != null && (i = packageInfo.versionCode) > 420000) {
                    browserInfo.ver = i;
                    browserInfo.quahead += packageInfo.versionName.replaceAll("\\.", BuildConfig.FLAVOR);
                    browserInfo.vn = packageInfo.versionName.replaceAll("\\.", BuildConfig.FLAVOR);
                    return browserInfo;
                }
            } catch (PackageManager.NameNotFoundException unused) {
            }
            try {
                try {
                    try {
                        try {
                            try {
                                packageInfo = packageManager.getPackageInfo("com.tencent.qbx", 0);
                                browserInfo.browserType = 0;
                                browserInfo.packageName = "com.tencent.qbx";
                                browserInfo.quahead = "ADRQBX_";
                            } catch (PackageManager.NameNotFoundException unused2) {
                                packageInfo = packageManager.getPackageInfo("com.tencent.mtt.x86", 0);
                                browserInfo.packageName = "com.tencent.mtt.x86";
                                browserInfo.browserType = 2;
                                browserInfo.quahead = "ADRQB_";
                            }
                        } catch (Exception unused3) {
                        }
                    } catch (PackageManager.NameNotFoundException unused4) {
                        packageInfo = packageManager.getPackageInfo("com.tencent.qbx5", 0);
                        browserInfo.browserType = 1;
                        browserInfo.packageName = "com.tencent.qbx5";
                        browserInfo.quahead = "ADRQBX5_";
                    }
                } catch (Exception unused5) {
                    a a2 = a(context, Uri.parse(QQBROWSER_DOWNLOAD_URL));
                    if (a2 != null && !TextUtils.isEmpty(a2.f1407b)) {
                        packageInfo = packageManager.getPackageInfo(a2.f1407b, 0);
                        try {
                            browserInfo.packageName = a2.f1407b;
                            browserInfo.browserType = 2;
                            browserInfo.quahead = "ADRQB_";
                        } catch (Exception unused6) {
                        }
                    }
                }
            } catch (PackageManager.NameNotFoundException unused7) {
                packageInfo = packageManager.getPackageInfo(TbsConfig.APP_QB, 0);
                browserInfo.packageName = TbsConfig.APP_QB;
                browserInfo.browserType = 2;
                browserInfo.quahead = "ADRQB_";
            }
            if (packageInfo != null) {
                browserInfo.ver = packageInfo.versionCode;
                browserInfo.quahead += packageInfo.versionName.replaceAll("\\.", BuildConfig.FLAVOR);
                browserInfo.vn = packageInfo.versionName.replaceAll("\\.", BuildConfig.FLAVOR);
            }
        } catch (Exception unused8) {
        }
        return browserInfo;
    }

    public static String getDownloadUrlWithQb(String str) {
        try {
            return QQBROWSER_DOWNLOAD_URL + URLEncoder.encode(str, "UTF-8");
        } catch (UnsupportedEncodingException e2) {
            e2.printStackTrace();
            return QQBROWSER_DOWNLOAD_URL;
        }
    }

    public static String getValidQBUrl(Context context, String str) {
        if (str.toLowerCase().startsWith("qb://")) {
            BrowserInfo browserInfo = getBrowserInfo(context);
            int i = browserInfo.browserType;
            boolean z = true;
            if (i != -1 && (i != 2 || browserInfo.ver >= 33)) {
                z = false;
            }
            if (z) {
                return getDownloadUrlWithQb(str);
            }
        }
        return str;
    }

    public static boolean isBrowserInstalled(Context context) {
        return getBrowserInfo(context).browserType != -1;
    }

    public static boolean isBrowserInstalledEx(Context context) {
        BrowserInfo browserInfo = getBrowserInfo(context);
        boolean z = false;
        try {
            if (Long.valueOf(browserInfo.vn).longValue() >= 6001500) {
                z = true;
            }
        } catch (NumberFormatException e2) {
            e2.printStackTrace();
        }
        if (browserInfo.ver >= 601500) {
            return true;
        }
        return z;
    }

    public static boolean isGreatBrowserVer(Context context, long j, long j2) {
        BrowserInfo browserInfo = getBrowserInfo(context);
        boolean z = false;
        try {
            if (Long.valueOf(browserInfo.vn).longValue() >= j) {
                z = true;
            }
        } catch (NumberFormatException e2) {
            e2.printStackTrace();
        }
        if (browserInfo.ver >= j2) {
            return true;
        }
        return z;
    }

    public static boolean isSupportQBScheme(Context context) {
        BrowserInfo browserInfo = getBrowserInfo(context);
        int i = browserInfo.browserType;
        if (i == -1) {
            return false;
        }
        return i != 2 || browserInfo.ver >= 42;
    }

    public static boolean isSupportingTbsTips(Context context) {
        BrowserInfo browserInfo = getBrowserInfo(context);
        return browserInfo.browserType == 2 && browserInfo.ver >= 580000;
    }

    /* JADX WARN: Code restructure failed: missing block: B:36:0x006b, code lost:
        if (android.text.TextUtils.isEmpty(r0.f1406a) == false) goto L_0x00be;
     */
    /* JADX WARN: Code restructure failed: missing block: B:55:0x00a8, code lost:
        if (android.text.TextUtils.isEmpty(r0.f1406a) == false) goto L_0x00be;
     */
    /* JADX WARN: Code restructure failed: missing block: B:59:0x00bc, code lost:
        if (android.text.TextUtils.isEmpty(r0.f1406a) == false) goto L_0x00be;
     */
    /* JADX WARN: Code restructure failed: missing block: B:60:0x00be, code lost:
        r2.setClassName(r0.f1407b, r0.f1406a);
     */
    /* JADX WARN: Removed duplicated region for block: B:63:0x00ca  */
    /* JADX WARN: Removed duplicated region for block: B:68:0x00da  */
    /* JADX WARN: Removed duplicated region for block: B:73:0x0100 A[Catch: ActivityNotFoundException -> 0x0129, TryCatch #1 {ActivityNotFoundException -> 0x0129, blocks: (B:71:0x00f0, B:73:0x0100, B:74:0x0124), top: B:80:0x00f0 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static int loadUrl(android.content.Context r7, java.lang.String r8, java.util.HashMap<java.lang.String, java.lang.String> r9, com.tencent.smtt.sdk.WebView r10) {
        /*
            Method dump skipped, instructions count: 299
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.sdk.stat.MttLoader.loadUrl(android.content.Context, java.lang.String, java.util.HashMap, com.tencent.smtt.sdk.WebView):int");
    }

    /* JADX WARN: Can't wrap try/catch for region: R(12:2|23|3|(3:7|8|(8:10|25|12|(1:14)|15|(1:17)(1:18)|19|20))|11|25|12|(0)|15|(0)(0)|19|20) */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0026  */
    /* JADX WARN: Removed duplicated region for block: B:17:0x002a  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x002d  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static int loadUrl(android.content.Context r4, java.lang.String r5, java.util.HashMap<java.lang.String, java.lang.String> r6, java.lang.String r7, com.tencent.smtt.sdk.WebView r8) {
        /*
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            r1 = 0
            android.content.pm.PackageManager r2 = r4.getPackageManager()     // Catch: Throwable -> 0x001d
            if (r2 == 0) goto L_0x001d
            java.lang.String r3 = "com.tencent.mtt"
            android.content.pm.PackageInfo r2 = r2.getPackageInfo(r3, r1)     // Catch: Throwable -> 0x001d
            if (r2 == 0) goto L_0x001d
            int r2 = r2.versionCode     // Catch: Throwable -> 0x001d
            r3 = 601000(0x92ba8, float:8.4218E-40)
            if (r2 <= r3) goto L_0x001d
            r2 = 1
            goto L_0x001e
        L_0x001d:
            r2 = 0
        L_0x001e:
            java.lang.String r3 = "UTF-8"
            java.lang.String r1 = java.net.URLEncoder.encode(r5, r3)     // Catch: Exception -> 0x0028
            if (r2 == 0) goto L_0x0027
            r5 = r1
        L_0x0027:
            r1 = r2
        L_0x0028:
            if (r1 == 0) goto L_0x002d
            java.lang.String r1 = ",encoded=1"
            goto L_0x002f
        L_0x002d:
            java.lang.String r1 = ""
        L_0x002f:
            java.lang.String r2 = "mttbrowser://url="
            r0.append(r2)
            r0.append(r5)
            java.lang.String r5 = ",product="
            r0.append(r5)
            java.lang.String r5 = "TBS"
            r0.append(r5)
            java.lang.String r5 = ",packagename="
            r0.append(r5)
            java.lang.String r5 = r4.getPackageName()
            r0.append(r5)
            java.lang.String r5 = ",from="
            r0.append(r5)
            r0.append(r7)
            java.lang.String r5 = ",version="
            r0.append(r5)
            java.lang.String r5 = "4.3.0.39"
            r0.append(r5)
            r0.append(r1)
            java.lang.String r5 = r0.toString()
            int r4 = loadUrl(r4, r5, r6, r8)
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.sdk.stat.MttLoader.loadUrl(android.content.Context, java.lang.String, java.util.HashMap, java.lang.String, com.tencent.smtt.sdk.WebView):int");
    }

    /* JADX WARN: Removed duplicated region for block: B:31:0x007c A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:32:0x007d A[Catch: Exception -> 0x00a9, TryCatch #0 {Exception -> 0x00a9, blocks: (B:3:0x0001, B:5:0x000a, B:7:0x0010, B:8:0x0014, B:10:0x001a, B:12:0x002c, B:13:0x0030, B:15:0x0046, B:19:0x0050, B:22:0x0059, B:24:0x0060, B:26:0x006a, B:28:0x006e, B:29:0x0071, B:32:0x007d, B:34:0x00a0, B:35:0x00a5), top: B:40:0x0001 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static boolean openDocWithQb(android.content.Context r6, java.lang.String r7, int r8, java.lang.String r9, java.lang.String r10, java.util.HashMap<java.lang.String, java.lang.String> r11, android.os.Bundle r12) {
        /*
            r0 = 0
            android.content.Intent r1 = new android.content.Intent     // Catch: Exception -> 0x00a9
            java.lang.String r2 = "com.tencent.QQBrowser.action.sdk.document"
            r1.<init>(r2)     // Catch: Exception -> 0x00a9
            if (r11 == 0) goto L_0x0030
            java.util.Set r2 = r11.keySet()     // Catch: Exception -> 0x00a9
            if (r2 == 0) goto L_0x0030
            java.util.Iterator r2 = r2.iterator()     // Catch: Exception -> 0x00a9
        L_0x0014:
            boolean r3 = r2.hasNext()     // Catch: Exception -> 0x00a9
            if (r3 == 0) goto L_0x0030
            java.lang.Object r3 = r2.next()     // Catch: Exception -> 0x00a9
            java.lang.String r3 = (java.lang.String) r3     // Catch: Exception -> 0x00a9
            java.lang.Object r4 = r11.get(r3)     // Catch: Exception -> 0x00a9
            java.lang.String r4 = (java.lang.String) r4     // Catch: Exception -> 0x00a9
            boolean r5 = android.text.TextUtils.isEmpty(r4)     // Catch: Exception -> 0x00a9
            if (r5 != 0) goto L_0x0014
            r1.putExtra(r3, r4)     // Catch: Exception -> 0x00a9
            goto L_0x0014
        L_0x0030:
            java.io.File r11 = new java.io.File     // Catch: Exception -> 0x00a9
            r11.<init>(r7)     // Catch: Exception -> 0x00a9
            java.lang.String r11 = "key_reader_sdk_id"
            r2 = 3
            r1.putExtra(r11, r2)     // Catch: Exception -> 0x00a9
            java.lang.String r11 = "key_reader_sdk_type"
            r1.putExtra(r11, r8)     // Catch: Exception -> 0x00a9
            boolean r11 = android.text.TextUtils.isEmpty(r10)     // Catch: Exception -> 0x00a9
            if (r11 != 0) goto L_0x004b
            java.lang.String r11 = "big_brother_source_key"
            r1.putExtra(r11, r10)     // Catch: Exception -> 0x00a9
        L_0x004b:
            r10 = 1
            if (r8 != 0) goto L_0x0054
            java.lang.String r8 = "key_reader_sdk_path"
        L_0x0050:
            r1.putExtra(r8, r7)     // Catch: Exception -> 0x00a9
            goto L_0x0059
        L_0x0054:
            if (r8 != r10) goto L_0x0059
            java.lang.String r8 = "key_reader_sdk_url"
            goto L_0x0050
        L_0x0059:
            java.lang.String r8 = "key_reader_sdk_format"
            r1.putExtra(r8, r9)     // Catch: Exception -> 0x00a9
            if (r6 == 0) goto L_0x0071
            android.content.pm.ApplicationInfo r8 = r6.getApplicationInfo()     // Catch: Exception -> 0x00a9
            int r8 = r8.targetSdkVersion     // Catch: Exception -> 0x00a9
            r11 = 24
            if (r8 < r11) goto L_0x0071
            int r8 = android.os.Build.VERSION.SDK_INT     // Catch: Exception -> 0x00a9
            if (r8 < r11) goto L_0x0071
            r1.addFlags(r10)     // Catch: Exception -> 0x00a9
        L_0x0071:
            r8 = 268435456(0x10000000, float:2.5243549E-29)
            r1.addFlags(r8)     // Catch: Exception -> 0x00a9
            android.net.Uri r7 = com.tencent.smtt.utils.FileProvider.a(r6, r7)     // Catch: Exception -> 0x00a9
            if (r7 != 0) goto L_0x007d
            return r0
        L_0x007d:
            java.lang.StringBuilder r8 = new java.lang.StringBuilder     // Catch: Exception -> 0x00a9
            r8.<init>()     // Catch: Exception -> 0x00a9
            java.lang.String r11 = "mtt/"
            r8.append(r11)     // Catch: Exception -> 0x00a9
            r8.append(r9)     // Catch: Exception -> 0x00a9
            java.lang.String r8 = r8.toString()     // Catch: Exception -> 0x00a9
            r1.setDataAndType(r7, r8)     // Catch: Exception -> 0x00a9
            java.lang.String r7 = "loginType"
            android.content.Context r8 = r6.getApplicationContext()     // Catch: Exception -> 0x00a9
            int r8 = a(r8)     // Catch: Exception -> 0x00a9
            r1.putExtra(r7, r8)     // Catch: Exception -> 0x00a9
            if (r12 == 0) goto L_0x00a5
            java.lang.String r7 = "key_reader_sdk_extrals"
            r1.putExtra(r7, r12)     // Catch: Exception -> 0x00a9
        L_0x00a5:
            r6.startActivity(r1)     // Catch: Exception -> 0x00a9
            return r10
        L_0x00a9:
            r6 = move-exception
            r6.printStackTrace()
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.sdk.stat.MttLoader.openDocWithQb(android.content.Context, java.lang.String, int, java.lang.String, java.lang.String, java.util.HashMap, android.os.Bundle):boolean");
    }

    public static boolean openDocWithQb(Context context, String str, int i, String str2, HashMap<String, String> hashMap) {
        return openDocWithQb(context, str, i, str2, hashMap, null);
    }

    public static boolean openDocWithQb(Context context, String str, int i, String str2, HashMap<String, String> hashMap, Bundle bundle) {
        return openDocWithQb(context, str, i, str2, BuildConfig.FLAVOR, hashMap, null);
    }

    public static boolean openVideoWithQb(Context context, String str, HashMap<String, String> hashMap) {
        boolean z;
        Set<String> keySet;
        Uri parse = Uri.parse(str);
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.setFlags(268435456);
        intent.setDataAndType(parse, "video/*");
        if (!(hashMap == null || (keySet = hashMap.keySet()) == null)) {
            for (String str2 : keySet) {
                String str3 = hashMap.get(str2);
                if (!TextUtils.isEmpty(str3)) {
                    intent.putExtra(str2, str3);
                }
            }
        }
        try {
            intent.putExtra("loginType", a(context));
            intent.setComponent(new ComponentName(TbsConfig.APP_QB, "com.tencent.mtt.browser.video.H5VideoThrdcallActivity"));
            context.startActivity(intent);
            z = true;
        } catch (Throwable unused) {
            z = false;
        }
        if (!z) {
            try {
                intent.setComponent(null);
                context.startActivity(intent);
            } catch (Throwable th) {
                th.printStackTrace();
                return false;
            }
        }
        return true;
    }

    /* JADX WARN: Code restructure failed: missing block: B:37:0x0067, code lost:
        if (r2 == null) goto L_0x006a;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static boolean verifySignature(java.io.File r6) {
        /*
            r0 = 0
            r1 = 0
            java.util.jar.JarFile r2 = new java.util.jar.JarFile     // Catch: Throwable -> 0x0061, all -> 0x0054
            r2.<init>(r6)     // Catch: Throwable -> 0x0061, all -> 0x0054
            java.lang.String r6 = "AndroidManifest.xml"
            java.util.jar.JarEntry r6 = r2.getJarEntry(r6)     // Catch: Throwable -> 0x0062, all -> 0x0052
            if (r6 != 0) goto L_0x0013
            r2.close()     // Catch: IOException -> 0x0012
        L_0x0012:
            return r1
        L_0x0013:
            r3 = 8192(0x2000, float:1.14794E-41)
            byte[] r3 = new byte[r3]     // Catch: Throwable -> 0x0062, all -> 0x0052
            java.io.InputStream r0 = r2.getInputStream(r6)     // Catch: Throwable -> 0x0062, all -> 0x0052
        L_0x001b:
            int r4 = r3.length     // Catch: Throwable -> 0x0062, all -> 0x0052
            int r4 = r0.read(r3, r1, r4)     // Catch: Throwable -> 0x0062, all -> 0x0052
            r5 = -1
            if (r4 == r5) goto L_0x0024
            goto L_0x001b
        L_0x0024:
            r0.close()     // Catch: Throwable -> 0x0062, all -> 0x0052
            java.security.cert.Certificate[] r6 = r6.getCertificates()     // Catch: Throwable -> 0x0062, all -> 0x0052
            int r3 = r6.length     // Catch: Throwable -> 0x0062, all -> 0x0052
            r4 = 1
            if (r3 >= r4) goto L_0x0036
            r0.close()     // Catch: IOException -> 0x0032
        L_0x0032:
            r2.close()     // Catch: IOException -> 0x0035
        L_0x0035:
            return r1
        L_0x0036:
            r6 = r6[r1]     // Catch: Throwable -> 0x0062, all -> 0x0052
            java.lang.String r6 = a(r6)     // Catch: Throwable -> 0x0062, all -> 0x0052
            java.lang.String r3 = "3082023f308201a8a00302010202044c46914a300d06092a864886f70d01010505003064310b30090603550406130238363110300e060355040813074265696a696e673110300e060355040713074265696a696e673110300e060355040a130754656e63656e74310c300a060355040b13035753443111300f0603550403130873616d75656c6d6f301e170d3130303732313036313835305a170d3430303731333036313835305a3064310b30090603550406130238363110300e060355040813074265696a696e673110300e060355040713074265696a696e673110300e060355040a130754656e63656e74310c300a060355040b13035753443111300f0603550403130873616d75656c6d6f30819f300d06092a864886f70d010101050003818d0030818902818100c209077044bd0d63ea00ede5b839914cabcc912a87f0f8b390877e0f7a2583f0d5933443c40431c35a4433bc4c965800141961adc44c9625b1d321385221fd097e5bdc2f44a1840d643ab59dc070cf6c4b4b4d98bed5cbb8046e0a7078ae134da107cdf2bfc9b440fe5cb2f7549b44b73202cc6f7c2c55b8cfb0d333a021f01f0203010001300d06092a864886f70d010105050003818100b007db9922774ef4ccfee81ba514a8d57c410257e7a2eba64bfa17c9e690da08106d32f637ac41fbc9f205176c71bde238c872c3ee2f8313502bee44c80288ea4ef377a6f2cdfe4d3653c145c4acfedbfbadea23b559d41980cc3cdd35d79a68240693739aabf5c5ed26148756cf88264226de394c8a24ac35b712b120d4d23a"
            boolean r6 = r6.equals(r3)     // Catch: Throwable -> 0x0062, all -> 0x0052
            if (r6 == 0) goto L_0x004b
            r0.close()     // Catch: IOException -> 0x0047
        L_0x0047:
            r2.close()     // Catch: IOException -> 0x004a
        L_0x004a:
            return r4
        L_0x004b:
            r0.close()     // Catch: IOException -> 0x004e
        L_0x004e:
            r2.close()     // Catch: IOException -> 0x006a
            goto L_0x006a
        L_0x0052:
            r6 = move-exception
            goto L_0x0056
        L_0x0054:
            r6 = move-exception
            r2 = r0
        L_0x0056:
            if (r0 == 0) goto L_0x005b
            r0.close()     // Catch: IOException -> 0x005b
        L_0x005b:
            if (r2 == 0) goto L_0x0060
            r2.close()     // Catch: IOException -> 0x0060
        L_0x0060:
            throw r6
        L_0x0061:
            r2 = r0
        L_0x0062:
            if (r0 == 0) goto L_0x0067
            r0.close()     // Catch: IOException -> 0x0067
        L_0x0067:
            if (r2 == 0) goto L_0x006a
            goto L_0x004e
        L_0x006a:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.sdk.stat.MttLoader.verifySignature(java.io.File):boolean");
    }
}
