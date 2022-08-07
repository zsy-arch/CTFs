package com.tencent.smtt.sdk;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Looper;
import android.support.v4.os.EnvironmentCompat;
import android.support.v4.provider.FontsContractCompat;
import android.util.Log;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.ValueCallback;
import android.webkit.WebIconDatabase;
import android.webkit.WebStorage;
import android.webkit.WebView;
import android.webkit.WebViewDatabase;
import com.tencent.smtt.export.external.DexLoader;
import com.tencent.smtt.export.external.interfaces.IX5WebViewBase;
import com.tencent.smtt.sdk.TbsDownloadConfig;
import com.tencent.smtt.sdk.TbsListener;
import com.tencent.smtt.sdk.a.d;
import com.tencent.smtt.utils.TbsLog;
import com.tencent.smtt.utils.TbsLogClient;
import com.tencent.smtt.utils.k;
import com.tencent.smtt.utils.v;
import dalvik.system.DexClassLoader;
import java.io.File;
import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;

@SuppressLint({"NewApi"})
/* loaded from: classes2.dex */
public class QbSdk {
    public static final int EXTENSION_INIT_FAILURE = -99999;
    public static final String LOGIN_TYPE_KEY_PARTNER_CALL_POS = "PosID";
    public static final String LOGIN_TYPE_KEY_PARTNER_ID = "ChannelID";
    public static final String PARAM_KEY_FEATUREID = "param_key_featureid";
    public static final String PARAM_KEY_FUNCTIONID = "param_key_functionid";
    public static final String PARAM_KEY_POSITIONID = "param_key_positionid";
    public static final int SVNVERSION = 446038;
    public static final String TID_QQNumber_Prefix = "QQ:";
    public static final int VERSION = 1;
    static String d;
    private static Class<?> n;
    private static Object o;
    private static String[] q;
    public static boolean sIsVersionPrinted = false;
    private static int l = 0;
    private static String m = "";
    static boolean a = false;
    static boolean b = false;
    static boolean c = true;
    private static boolean p = false;
    private static String r = "NULL";
    private static String s = "UNKNOWN";
    static boolean e = false;
    static long f = 0;
    static long g = 0;
    static Object h = new Object();
    private static int t = 0;

    /* renamed from: u */
    private static int f45u = 170;
    private static String v = null;
    private static String w = null;
    static volatile boolean i = a;
    private static boolean x = true;
    private static TbsListener y = null;
    private static TbsListener z = null;
    private static boolean A = false;
    private static boolean B = false;
    static TbsListener j = new j();
    static Map<String, Object> k = null;

    /* loaded from: classes2.dex */
    public interface PreInitCallback {
        void onCoreInitFinished();

        void onViewInitFinished(boolean z);
    }

    public static Bundle a(Context context, Bundle bundle) {
        if (!a(context)) {
            TbsLogReport.a(context).a(TbsListener.ErrorCode.INCR_UPDATE_ERROR, "initForPatch return false!");
            return null;
        }
        Object a2 = v.a(o, "incrUpdate", new Class[]{Context.class, Bundle.class}, context, bundle);
        if (a2 != null) {
            return (Bundle) a2;
        }
        TbsLogReport.a(context).a(TbsListener.ErrorCode.INCR_UPDATE_ERROR, "incrUpdate return null!");
        return null;
    }

    public static Object a(Context context, String str, Bundle bundle) {
        if (!a(context)) {
            return Integer.valueOf((int) EXTENSION_INIT_FAILURE);
        }
        Object a2 = v.a(o, "miscCall", new Class[]{String.class, Bundle.class}, str, bundle);
        if (a2 == null) {
            return null;
        }
        return a2;
    }

    public static String a() {
        return m;
    }

    public static synchronized void a(Context context, String str) {
        synchronized (QbSdk.class) {
            if (!a) {
                a = true;
                s = "forceSysWebViewInner: " + str;
                TbsLog.e("QbSdk", "QbSdk.SysWebViewForcedInner..." + s);
                TbsCoreLoadStat.getInstance().a(context, 401, new Throwable(s));
            }
        }
    }

    static boolean a(Context context) {
        boolean z2 = true;
        try {
            if (n == null) {
                File h2 = aj.a().h(context);
                if (h2 == null) {
                    TbsLog.e("QbSdk", "QbSdk initExtension (false) optDir == null");
                    z2 = false;
                } else {
                    File file = new File(h2, "tbs_sdk_extension_dex.jar");
                    if (!file.exists()) {
                        TbsLog.e("QbSdk", "QbSdk initExtension (false) dexFile.exists()=false", true);
                        z2 = false;
                    } else {
                        n = new DexClassLoader(file.getAbsolutePath(), h2.getAbsolutePath(), file.getAbsolutePath(), QbSdk.class.getClassLoader()).loadClass("com.tencent.tbs.sdk.extension.TbsSDKExtension");
                        o = n.getConstructor(Context.class, Context.class).newInstance(context, context);
                    }
                }
            }
            return z2;
        } catch (Throwable th) {
            TbsLog.e("QbSdk", "initExtension sys WebView: " + Log.getStackTraceString(th));
            return false;
        }
    }

    public static boolean a(Context context, int i2) {
        return a(context, i2, 20000);
    }

    static boolean a(Context context, int i2, int i3) {
        if (!b(context)) {
            return true;
        }
        Object a2 = v.a(o, "isX5Disabled", new Class[]{Integer.TYPE, Integer.TYPE, Integer.TYPE}, Integer.valueOf(i2), 43100, Integer.valueOf(i3));
        if (a2 != null) {
            return ((Boolean) a2).booleanValue();
        }
        Object a3 = v.a(o, "isX5Disabled", new Class[]{Integer.TYPE, Integer.TYPE}, Integer.valueOf(i2), 43100);
        if (a3 != null) {
            return ((Boolean) a3).booleanValue();
        }
        return true;
    }

    @SuppressLint({"NewApi"})
    private static boolean a(Context context, boolean z2) {
        int i2;
        File file;
        TbsLog.initIfNeed(context);
        if (!sIsVersionPrinted) {
            TbsLog.i("QbSdk", "svn revision: 446038; SDK_VERSION_CODE: 43100; SDK_VERSION_NAME: 3.1.0.1034");
            sIsVersionPrinted = true;
        }
        if (a && !z2) {
            TbsLog.e("QbSdk", "QbSdk init: " + s, false);
            TbsCoreLoadStat.getInstance().a(context, 414, new Throwable(s));
            return false;
        } else if (b) {
            TbsLog.e("QbSdk", "QbSdk init mIsSysWebViewForcedByOuter = true", true);
            TbsCoreLoadStat.getInstance().a(context, 402, new Throwable(r));
            return false;
        } else {
            if (!x) {
                c(context);
            }
            try {
                File h2 = aj.a().h(context);
                if (h2 == null) {
                    TbsLog.e("QbSdk", "QbSdk init (false) optDir == null");
                    TbsCoreLoadStat.getInstance().a(context, TbsListener.ErrorCode.ERROR_TBSCORE_SHARE_DIR, new Throwable("QbSdk.init (false) TbsCoreShareDir is null"));
                    return false;
                }
                if (!TbsShareManager.isThirdPartyApp(context)) {
                    if (l != 0) {
                        i2 = aj.a().a(true, context);
                        if (l != i2) {
                            n = null;
                            o = null;
                            TbsLog.e("QbSdk", "QbSdk init (false) not isThirdPartyApp tbsCoreInstalledVer=" + i2, true);
                            TbsLog.e("QbSdk", "QbSdk init (false) not isThirdPartyApp sTbsVersion=" + l, true);
                            TbsCoreLoadStat.getInstance().a(context, 303, new Throwable("sTbsVersion: " + l + "; tbsCoreInstalledVer: " + i2));
                            return false;
                        }
                    } else {
                        i2 = 0;
                    }
                    l = i2;
                }
                if (n != null) {
                    return true;
                }
                if (!TbsShareManager.isThirdPartyApp(context)) {
                    file = new File(aj.a().h(context), "tbs_sdk_extension_dex.jar");
                } else if (TbsShareManager.h(context)) {
                    file = new File(TbsShareManager.b(context), "tbs_sdk_extension_dex.jar");
                } else {
                    TbsCoreLoadStat.getInstance().a(context, 304, new Throwable("isShareTbsCoreAvailable false!"));
                    return false;
                }
                if (!file.exists()) {
                    TbsLog.e("QbSdk", "QbSdk init (false) tbs_sdk_extension_dex.jar is not exist!");
                    int d2 = aj.a().d(context);
                    if (new File(file.getParentFile(), "tbs_jars_fusion_dex.jar").exists()) {
                        if (d2 > 0) {
                            TbsCoreLoadStat.getInstance().a(context, TbsListener.ErrorCode.INFO_MISS_SDKEXTENSION_JAR_WITH_FUSION_DEX_WITH_CORE, new Exception("tbs_sdk_extension_dex not exist(with fusion dex)!" + d2));
                            return false;
                        }
                        TbsCoreLoadStat.getInstance().a(context, TbsListener.ErrorCode.INFO_MISS_SDKEXTENSION_JAR_WITH_FUSION_DEX_WITHOUT_CORE, new Exception("tbs_sdk_extension_dex not exist(with fusion dex)!" + d2));
                        return false;
                    } else if (d2 > 0) {
                        TbsCoreLoadStat.getInstance().a(context, TbsListener.ErrorCode.INFO_INFO_MISS_SDKEXTENSION_JAR_WITHOUT_FUSION_DEX_WITH_CORE, new Exception("tbs_sdk_extension_dex not exist(without fusion dex)!" + d2));
                        return false;
                    } else {
                        TbsCoreLoadStat.getInstance().a(context, TbsListener.ErrorCode.INFO_INFO_MISS_SDKEXTENSION_JAR_WITHOUT_FUSION_DEX_WITHOUT_CORE, new Exception("tbs_sdk_extension_dex not exist(without fusion dex)!" + d2));
                        return false;
                    }
                } else {
                    n = new DexClassLoader(file.getAbsolutePath(), h2.getAbsolutePath(), file.getAbsolutePath(), QbSdk.class.getClassLoader()).loadClass("com.tencent.tbs.sdk.extension.TbsSDKExtension");
                    Constructor<?> constructor = n.getConstructor(Context.class, Context.class);
                    if (TbsShareManager.isThirdPartyApp(context)) {
                        o = constructor.newInstance(context, TbsShareManager.d(context));
                    } else {
                        o = constructor.newInstance(context, context);
                    }
                    v.a(o, "setClientVersion", new Class[]{Integer.TYPE}, 1);
                    return true;
                }
            } catch (Throwable th) {
                TbsLog.e("QbSdk", "QbSdk init Throwable: " + Log.getStackTraceString(th));
                TbsCoreLoadStat.getInstance().a(context, TbsListener.ErrorCode.THROWABLE_QBSDK_INIT, th);
                return false;
            }
        }
    }

    public static boolean a(Context context, boolean z2, boolean z3) {
        int i2 = -1;
        boolean z4 = true;
        boolean z5 = false;
        if (TbsShareManager.isThirdPartyApp(context) && !TbsShareManager.g(context)) {
            TbsCoreLoadStat.getInstance().a(context, 302);
        } else if (!a(context, z2)) {
            TbsLog.e("QbSdk", "QbSdk.init failure!");
        } else {
            Object a2 = v.a(o, "canLoadX5Core", new Class[]{Integer.TYPE}, 43100);
            if (a2 == null) {
                Object a3 = v.a(o, "canLoadX5", new Class[]{Integer.TYPE}, Integer.valueOf(a.a()));
                if (a3 == null) {
                    TbsCoreLoadStat.getInstance().a(context, 308);
                } else if (!(a3 instanceof String) || !((String) a3).equalsIgnoreCase("AuthenticationFail")) {
                    if (a3 instanceof Boolean) {
                        l = l.d();
                        boolean a4 = a(context, l.d());
                        if (((Boolean) a3).booleanValue() && !a4) {
                            z5 = true;
                        }
                        if (!z5) {
                            TbsLog.e(TbsListener.tag_load_error, "318");
                            TbsLog.w(TbsListener.tag_load_error, "isX5Disable:" + a4);
                            TbsLog.w(TbsListener.tag_load_error, "(Boolean) ret:" + ((Boolean) a3));
                        }
                    }
                }
            } else if (!(a2 instanceof String) || !((String) a2).equalsIgnoreCase("AuthenticationFail")) {
                if (!(a2 instanceof Bundle)) {
                    TbsCoreLoadStat.getInstance().a(context, TbsListener.ErrorCode.ERROR_QBSDK_INIT_ERROR_RET_TYPE_NOT_BUNDLE, new Throwable("" + a2));
                    TbsLog.e(TbsListener.tag_load_error, "ret not instance of bundle");
                } else {
                    Bundle bundle = (Bundle) a2;
                    if (bundle.isEmpty()) {
                        TbsCoreLoadStat.getInstance().a(context, TbsListener.ErrorCode.ERROR_QBSDK_INIT_ERROR_EMPTY_BUNDLE, new Throwable("" + a2));
                        TbsLog.e(TbsListener.tag_load_error, "empty bundle");
                    } else {
                        try {
                            i2 = bundle.getInt(FontsContractCompat.Columns.RESULT_CODE, -1);
                        } catch (Exception e2) {
                            TbsLog.e("QbSdk", "bundle.getInt(KEY_RESULT_CODE) error : " + e2.toString());
                        }
                        z5 = i2 == 0;
                        if (TbsShareManager.isThirdPartyApp(context)) {
                            l.a(TbsShareManager.c(context));
                            m = String.valueOf(TbsShareManager.c(context));
                            if (m.length() == 5) {
                                m = "0" + m;
                            }
                            if (m.length() != 6) {
                                m = "";
                            }
                        } else {
                            if (Build.VERSION.SDK_INT >= 12) {
                                m = bundle.getString("tbs_core_version", "0");
                            } else {
                                m = bundle.getString("tbs_core_version");
                                if (m == null) {
                                    m = "0";
                                }
                            }
                            try {
                                l = Integer.parseInt(m);
                            } catch (NumberFormatException e3) {
                                l = 0;
                            }
                            l.a(l);
                            if (l == 0) {
                                TbsCoreLoadStat.getInstance().a(context, 307, new Throwable("sTbsVersion is 0"));
                            } else {
                                if ((l <= 0 || l > 25442) && l != 25472) {
                                    z4 = false;
                                }
                                if (z4) {
                                    TbsLog.e(TbsDownloader.LOGTAG, "is_obsolete --> delete old core:" + l);
                                    k.b(aj.a().h(context));
                                    TbsCoreLoadStat.getInstance().a(context, 307, new Throwable("is_obsolete --> delete old core:" + l));
                                }
                            }
                        }
                        try {
                            q = bundle.getStringArray("tbs_jarfiles");
                            if (q instanceof String[]) {
                                d = bundle.getString("tbs_librarypath");
                                Object obj = null;
                                if (i2 != 0) {
                                    try {
                                        obj = v.a(o, "getErrorCodeForLogReport", new Class[0], new Object[0]);
                                    } catch (Exception e4) {
                                        e4.printStackTrace();
                                    }
                                }
                                switch (i2) {
                                    case -2:
                                        if (!(obj instanceof Integer)) {
                                            TbsCoreLoadStat.getInstance().a(context, 404, new Throwable("detail: " + obj));
                                            break;
                                        } else {
                                            TbsCoreLoadStat.getInstance().a(context, ((Integer) obj).intValue(), new Throwable("detail: " + obj));
                                            break;
                                        }
                                    case -1:
                                        if (!(obj instanceof Integer)) {
                                            TbsCoreLoadStat.getInstance().a(context, 307, new Throwable("detail: " + obj));
                                            break;
                                        } else {
                                            TbsCoreLoadStat.getInstance().a(context, ((Integer) obj).intValue(), new Throwable("detail: " + obj));
                                            break;
                                        }
                                    case 0:
                                        break;
                                    default:
                                        TbsCoreLoadStat.getInstance().a(context, 415, new Throwable("detail: " + obj + "errcode" + i2));
                                        break;
                                }
                            } else {
                                TbsCoreLoadStat.getInstance().a(context, 307, new Throwable("sJarFiles not instanceof String[]: " + q));
                            }
                        } catch (Throwable th) {
                            TbsCoreLoadStat.getInstance().a(context, TbsListener.ErrorCode.ERROR_GETSTRINGARRAY_JARFILE, th);
                        }
                    }
                }
            }
            if (!z5) {
                TbsLog.e(TbsListener.tag_load_error, "319");
            }
        }
        return z5;
    }

    public static String b() {
        Object invokeStaticMethod;
        bi b2 = bi.b();
        if (b2 == null || !b2.c() || (invokeStaticMethod = b2.d().b().invokeStaticMethod("com.tencent.tbs.tbsshell.WebCoreProxy", "getGUID", new Class[0], new Object[0])) == null || !(invokeStaticMethod instanceof String)) {
            return null;
        }
        return (String) invokeStaticMethod;
    }

    private static boolean b(Context context) {
        File file;
        try {
            if (n != null) {
                return true;
            }
            File h2 = aj.a().h(context);
            if (h2 == null) {
                TbsLog.e("QbSdk", "QbSdk initForX5DisableConfig (false) optDir == null");
                return false;
            }
            if (!TbsShareManager.isThirdPartyApp(context)) {
                file = new File(aj.a().h(context), "tbs_sdk_extension_dex.jar");
            } else if (TbsShareManager.h(context)) {
                file = new File(TbsShareManager.b(context), "tbs_sdk_extension_dex.jar");
            } else {
                TbsCoreLoadStat.getInstance().a(context, 304);
                return false;
            }
            if (!file.exists()) {
                TbsCoreLoadStat.getInstance().a(context, 406, new Exception("initForX5DisableConfig failure -- tbs_sdk_extension_dex.jar is not exist!"));
                return false;
            }
            n = new DexClassLoader(file.getAbsolutePath(), h2.getAbsolutePath(), file.getAbsolutePath(), QbSdk.class.getClassLoader()).loadClass("com.tencent.tbs.sdk.extension.TbsSDKExtension");
            Constructor<?> constructor = n.getConstructor(Context.class, Context.class);
            if (TbsShareManager.isThirdPartyApp(context)) {
                o = constructor.newInstance(context, TbsShareManager.d(context));
            } else {
                o = constructor.newInstance(context, context);
            }
            v.a(o, "setClientVersion", new Class[]{Integer.TYPE}, 1);
            return true;
        } catch (Throwable th) {
            TbsLog.e("QbSdk", "initForX5DisableConfig sys WebView: " + Log.getStackTraceString(th));
            return false;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:35:0x0113  */
    /* JADX WARN: Removed duplicated region for block: B:55:0x005f A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static void c(android.content.Context r11) {
        /*
            Method dump skipped, instructions count: 397
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.sdk.QbSdk.c(android.content.Context):void");
    }

    public static boolean canLoadVideo(Context context) {
        Object a2 = v.a(o, "canLoadVideo", new Class[]{Integer.TYPE}, 0);
        if (a2 == null) {
            TbsCoreLoadStat.getInstance().a(context, TbsListener.ErrorCode.ERROR_CANLOADVIDEO_RETURN_NULL);
        } else if (!((Boolean) a2).booleanValue()) {
            TbsCoreLoadStat.getInstance().a(context, TbsListener.ErrorCode.ERROR_CANLOADVIDEO_RETURN_FALSE);
        }
        if (a2 == null) {
            return false;
        }
        return ((Boolean) a2).booleanValue();
    }

    public static boolean canLoadX5(Context context) {
        return a(context, false, false);
    }

    public static boolean canLoadX5FirstTimeThirdApp(Context context) {
        try {
            if (n == null) {
                File h2 = aj.a().h(context);
                if (h2 == null) {
                    TbsLog.e("QbSdk", "QbSdk canLoadX5FirstTimeThirdApp (false) optDir == null");
                    return false;
                }
                File file = new File(TbsShareManager.b(context), "tbs_sdk_extension_dex.jar");
                if (!file.exists()) {
                    TbsLog.e("QbSdk", "QbSdk canLoadX5FirstTimeThirdApp (false) dexFile.exists()=false", true);
                    return false;
                }
                n = new DexClassLoader(file.getAbsolutePath(), h2.getAbsolutePath(), file.getAbsolutePath(), QbSdk.class.getClassLoader()).loadClass("com.tencent.tbs.sdk.extension.TbsSDKExtension");
            }
            if (o == null) {
                o = n.getConstructor(Context.class, Context.class).newInstance(context, context);
            }
            Object a2 = v.a(o, "canLoadX5CoreForThirdApp", new Class[0], new Object[0]);
            if (a2 == null || !(a2 instanceof Boolean)) {
                return false;
            }
            return ((Boolean) a2).booleanValue();
        } catch (Throwable th) {
            TbsLog.e("QbSdk", "canLoadX5FirstTimeThirdApp sys WebView: " + Log.getStackTraceString(th));
            return false;
        }
    }

    public static void canOpenFile(Context context, String str, ValueCallback<Boolean> valueCallback) {
        new d(context, str, valueCallback).start();
    }

    public static boolean canOpenMimeFileType(Context context, String str) {
        if (!a(context, false)) {
        }
        return false;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:67:0x014c  */
    /* JADX WARN: Removed duplicated region for block: B:87:0x0148 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:95:0x00fb A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Type inference failed for: r3v17, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r3v22, types: [java.io.FileInputStream, java.io.InputStream] */
    /* JADX WARN: Type inference failed for: r3v23, types: [java.lang.Exception] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static boolean canOpenWebPlus(android.content.Context r9) {
        /*
            Method dump skipped, instructions count: 363
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.sdk.QbSdk.canOpenWebPlus(android.content.Context):boolean");
    }

    public static boolean canUseVideoFeatrue(Context context, int i2) {
        Object a2 = v.a(o, "canUseVideoFeatrue", new Class[]{Integer.TYPE}, Integer.valueOf(i2));
        if (a2 == null || !(a2 instanceof Boolean)) {
            return false;
        }
        return ((Boolean) a2).booleanValue();
    }

    public static void clear(Context context) {
    }

    public static void clearAllWebViewCache(Context context, boolean z2) {
        bi b2;
        try {
            WebView webView = new WebView(context);
            if (Build.VERSION.SDK_INT >= 11) {
                webView.removeJavascriptInterface("searchBoxJavaBridge_");
                webView.removeJavascriptInterface("accessibility");
                webView.removeJavascriptInterface("accessibilityTraversal");
            }
            webView.clearCache(true);
            if (z2) {
                CookieSyncManager.createInstance(context);
                CookieManager.getInstance().removeAllCookie();
            }
            WebViewDatabase.getInstance(context).clearUsernamePassword();
            WebViewDatabase.getInstance(context).clearHttpAuthUsernamePassword();
            WebViewDatabase.getInstance(context).clearFormData();
            WebStorage.getInstance().deleteAllData();
            WebIconDatabase.getInstance().removeAllIcons();
        } catch (Throwable th) {
            TbsLog.e("QbSdk", "clearAllWebViewCache exception 1 -- " + Log.getStackTraceString(th));
        }
        try {
            if (new WebView(context).getWebViewClientExtension() != null && (b2 = bi.b()) != null && b2.c()) {
                b2.d().a(context, z2);
            }
        } catch (Throwable th2) {
        }
    }

    public static void closeFileReader(Context context) {
        bi b2 = bi.b();
        b2.a(context, null);
        if (b2.c()) {
            b2.d().o();
        }
    }

    public static boolean createMiniQBShortCut(Context context, String str, String str2, Drawable drawable) {
        if (!(context == null || TbsDownloader.getOverSea(context) || isMiniQBShortCutExist(context, str, str2))) {
            bi b2 = bi.b();
            if (b2 == null || !b2.c()) {
                return false;
            }
            Bitmap bitmap = null;
            if (drawable instanceof BitmapDrawable) {
                bitmap = ((BitmapDrawable) drawable).getBitmap();
            }
            DexLoader b3 = b2.d().b();
            TbsLog.e("QbSdk", "qbsdk createMiniQBShortCut");
            Object invokeStaticMethod = b3.invokeStaticMethod("com.tencent.tbs.tbsshell.WebCoreProxy", "createMiniQBShortCut", new Class[]{Context.class, String.class, String.class, Bitmap.class}, context, str, str2, bitmap);
            TbsLog.e("QbSdk", "qbsdk after createMiniQBShortCut ret: " + invokeStaticMethod);
            return invokeStaticMethod != null;
        }
        return false;
    }

    public static boolean deleteMiniQBShortCut(Context context, String str, String str2) {
        bi b2;
        return (context == null || TbsDownloader.getOverSea(context) || (b2 = bi.b()) == null || !b2.c() || b2.d().b().invokeStaticMethod("com.tencent.tbs.tbsshell.WebCoreProxy", "deleteMiniQBShortCut", new Class[]{Context.class, String.class, String.class}, context, str, str2) == null) ? false : true;
    }

    public static void forceSysWebView() {
        b = true;
        r = "SysWebViewForcedByOuter: " + Log.getStackTraceString(new Throwable());
        TbsLog.e("QbSdk", "sys WebView: SysWebViewForcedByOuter");
    }

    public static long getApkFileSize(Context context) {
        if (context != null) {
            return TbsDownloadConfig.getInstance(context.getApplicationContext()).mPreferences.getLong(TbsDownloadConfig.TbsConfigKey.KEY_TBSAPKFILESIZE, 0L);
        }
        return 0L;
    }

    public static String[] getDexLoaderFileList(Context context, Context context2, String str) {
        if (q instanceof String[]) {
            int length = q.length;
            String[] strArr = new String[length];
            for (int i2 = 0; i2 < length; i2++) {
                strArr[i2] = str + q[i2];
            }
            return strArr;
        }
        Object a2 = v.a(o, "getJarFiles", new Class[]{Context.class, Context.class, String.class}, context, context2, str);
        boolean z2 = a2 instanceof String[];
        String[] strArr2 = a2;
        if (!z2) {
            strArr2 = new String[]{""};
        }
        return (String[]) strArr2;
    }

    public static boolean getDownloadWithoutWifi() {
        return A;
    }

    public static String getMiniQBVersion(Context context) {
        bi b2 = bi.b();
        b2.a(context, null);
        if (b2 == null || !b2.c()) {
            return null;
        }
        return b2.d().f();
    }

    public static String getQQBuildNumber() {
        return w;
    }

    public static boolean getTBSInstalling() {
        return B;
    }

    public static String getTID() {
        return v;
    }

    public static int getTbsVersion(Context context) {
        if (TbsShareManager.isThirdPartyApp(context)) {
            return TbsShareManager.a(context, false);
        }
        int d2 = aj.a().d(context);
        if (d2 != 0 || ae.a(context).c() != 3) {
            return d2;
        }
        reset(context);
        return d2;
    }

    public static void initTbsSettings(Map<String, Object> map) {
        if (k == null) {
            k = map;
            return;
        }
        try {
            k.putAll(map);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public static void initX5Environment(Context context, PreInitCallback preInitCallback) {
        z = new h(context, preInitCallback);
        if (TbsShareManager.isThirdPartyApp(context)) {
            aj.a().b(context, true);
        }
        TbsDownloader.needDownload(context, false, false, new i(context, preInitCallback));
    }

    public static boolean installLocalQbApk(Context context, String str, String str2, Bundle bundle) {
        l a2 = l.a(true);
        a2.a(context, false, false, null);
        if (a2 == null || !a2.b()) {
            return false;
        }
        return a2.a().a(context, str, str2, bundle);
    }

    public static boolean intentDispatch(WebView webView, Intent intent, String str, String str2) {
        if (webView == null) {
            return false;
        }
        if (str.startsWith("mttbrowser://miniqb/ch=icon?")) {
            Context context = webView.getContext();
            int indexOf = str.indexOf("url=");
            String substring = indexOf > 0 ? str.substring(indexOf + 4) : null;
            HashMap hashMap = new HashMap();
            String str3 = EnvironmentCompat.MEDIA_UNKNOWN;
            try {
                str3 = context.getApplicationInfo().packageName;
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            hashMap.put(LOGIN_TYPE_KEY_PARTNER_ID, str3);
            hashMap.put(LOGIN_TYPE_KEY_PARTNER_CALL_POS, "14004");
            if (d.a(context, "miniqb://home".equals(substring) ? "qb://navicard/addCard?cardId=168&cardName=168" : substring, hashMap, "QbSdk.startMiniQBToLoadUrl", null) != 0) {
                bi b2 = bi.b();
                if (b2 != null && b2.c() && b2.d().a(context, substring, null, str2, null) == 0) {
                    return true;
                }
                webView.loadUrl(substring);
            }
        } else {
            webView.loadUrl(str);
        }
        return false;
    }

    public static boolean isMiniQBShortCutExist(Context context, String str, String str2) {
        if (context != null && !TbsDownloader.getOverSea(context)) {
            bi b2 = bi.b();
            if (b2 == null || !b2.c()) {
                return false;
            }
            Object invokeStaticMethod = b2.d().b().invokeStaticMethod("com.tencent.tbs.tbsshell.WebCoreProxy", "isMiniQBShortCutExist", new Class[]{Context.class, String.class}, context, str);
            if (invokeStaticMethod == null) {
                return false;
            }
            Boolean bool = false;
            if (invokeStaticMethod instanceof Boolean) {
                bool = (Boolean) invokeStaticMethod;
            }
            return bool.booleanValue();
        }
        return false;
    }

    public static boolean isTbsCoreInited() {
        l a2 = l.a(false);
        return a2 != null && a2.g();
    }

    public static boolean isX5DisabledSync(Context context) {
        if (ae.a(context).c() == 2) {
            return false;
        }
        if (!b(context)) {
            return true;
        }
        Object a2 = v.a(o, "isX5DisabledSync", new Class[]{Integer.TYPE, Integer.TYPE}, Integer.valueOf(aj.a().d(context)), 43100);
        if (a2 != null) {
            return ((Boolean) a2).booleanValue();
        }
        return true;
    }

    public static synchronized void preInit(Context context) {
        synchronized (QbSdk.class) {
            preInit(context, null);
        }
    }

    public static synchronized void preInit(Context context, PreInitCallback preInitCallback) {
        synchronized (QbSdk.class) {
            TbsLog.initIfNeed(context);
            i = a;
            ai aiVar = new ai();
            aiVar.a("init_all", (byte) 1);
            if (!p) {
                g gVar = new g(context, aiVar, new f(Looper.getMainLooper(), preInitCallback, context, aiVar));
                gVar.setName("tbs_preinit");
                gVar.setPriority(10);
                gVar.start();
                p = true;
            }
        }
    }

    public static void reset(Context context) {
        TbsLog.e("QbSdk", "QbSdk reset!", true);
        try {
            TbsDownloader.stopDownload();
            TbsDownloader.b(context);
            k.b(context.getDir("tbs", 0));
            TbsLog.i("QbSdk", "delete downloaded apk success", true);
            aj.a.set(0);
        } catch (Throwable th) {
            TbsLog.e("QbSdk", "QbSdk reset exception:" + Log.getStackTraceString(th));
        }
    }

    public static void setCurrentID(String str) {
        if (str != null && str.startsWith(TID_QQNumber_Prefix)) {
            String substring = str.substring(TID_QQNumber_Prefix.length());
            v = "0000000000000000".substring(substring.length()) + substring;
        }
    }

    public static void setDownloadWithoutWifi(boolean z2) {
        A = z2;
    }

    public static void setQQBuildNumber(String str) {
        w = str;
    }

    public static void setTBSInstallingStatus(boolean z2) {
        B = z2;
    }

    public static void setTbsListener(TbsListener tbsListener) {
        y = tbsListener;
    }

    public static void setTbsLogClient(TbsLogClient tbsLogClient) {
        TbsLog.setTbsLogClient(tbsLogClient);
    }

    public static int startMiniQBToLoadUrl(Context context, String str, HashMap<String, String> hashMap, ValueCallback<String> valueCallback) {
        TbsCoreLoadStat.getInstance().a(context, 501);
        if (context == null) {
            return -100;
        }
        bi b2 = bi.b();
        b2.a(context, null);
        if (!b2.c()) {
            TbsCoreLoadStat.getInstance().a(context, 502);
            return -102;
        } else if (context != null && context.getApplicationInfo().packageName.equals("com.nd.android.pandahome2") && getTbsVersion(context) < 25487) {
            return -101;
        } else {
            int a2 = b2.d().a(context, str, hashMap, null, valueCallback);
            if (a2 == 0) {
                TbsCoreLoadStat.getInstance().a(context, 503);
                return a2;
            }
            TbsLogReport.a(context).b(504, "" + a2);
            return a2;
        }
    }

    public static boolean startQBForDoc(Context context, String str, int i2, int i3, String str2, Bundle bundle) {
        HashMap hashMap = new HashMap();
        hashMap.put(LOGIN_TYPE_KEY_PARTNER_ID, context.getApplicationContext().getApplicationInfo().processName);
        hashMap.put(LOGIN_TYPE_KEY_PARTNER_CALL_POS, Integer.toString(i2));
        return d.a(context, str, i3, str2, hashMap, bundle);
    }

    public static boolean startQBForVideo(Context context, String str, int i2) {
        HashMap hashMap = new HashMap();
        hashMap.put(LOGIN_TYPE_KEY_PARTNER_ID, context.getApplicationInfo().processName);
        hashMap.put(LOGIN_TYPE_KEY_PARTNER_CALL_POS, Integer.toString(i2));
        return d.a(context, str, hashMap);
    }

    public static boolean startQBToLoadurl(Context context, String str, int i2, WebView webView) {
        bi b2;
        Object invokeStaticMethod;
        IX5WebViewBase iX5WebViewBase;
        HashMap hashMap = new HashMap();
        hashMap.put(LOGIN_TYPE_KEY_PARTNER_ID, context.getApplicationInfo().processName);
        hashMap.put(LOGIN_TYPE_KEY_PARTNER_CALL_POS, Integer.toString(i2));
        if (webView == null) {
            try {
                String str2 = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).packageName;
                if (!((str2 != "com.tencent.mm" && str2 != "com.tencent.mobileqq") || (b2 = bi.b()) == null || !b2.c() || (invokeStaticMethod = b2.d().b().invokeStaticMethod("com.tencent.smtt.webkit.WebViewList", "getCurrentMainWebviewJustForQQandWechat", new Class[0], new Object[0])) == null || (iX5WebViewBase = (IX5WebViewBase) invokeStaticMethod) == null)) {
                    webView = (WebView) iX5WebViewBase.getView().getParent();
                }
            } catch (Exception e2) {
            }
        }
        return d.a(context, str, hashMap, "QbSdk.startQBToLoadurl", webView) == 0;
    }

    public static boolean startQbOrMiniQBToLoadUrl(Context context, String str, HashMap<String, String> hashMap, ValueCallback<String> valueCallback) {
        if (context == null) {
            return false;
        }
        bi b2 = bi.b();
        b2.a(context, null);
        if (hashMap == null || !"5".equals(hashMap.get(LOGIN_TYPE_KEY_PARTNER_CALL_POS)) || !b2.c() || ((Bundle) b2.d().b().invokeStaticMethod("com.tencent.tbs.tbsshell.WebCoreProxy", "getAdWebViewInfoFromX5Core", new Class[0], new Object[0])) != null) {
        }
        if (d.a(context, str, hashMap, "QbSdk.startMiniQBToLoadUrl", null) == 0) {
            return true;
        }
        if (b2.c()) {
            if (context != null && context.getApplicationInfo().packageName.equals("com.nd.android.pandahome2") && getTbsVersion(context) < 25487) {
                return false;
            }
            if (b2.d().a(context, str, hashMap, null, valueCallback) == 0) {
                return true;
            }
        }
        return false;
    }

    public static void unForceSysWebView() {
        b = false;
        TbsLog.e("QbSdk", "sys WebView: unForceSysWebView called");
    }

    public static boolean useSoftWare() {
        if (o == null) {
            return false;
        }
        Object a2 = v.a(o, "useSoftWare", new Class[0], new Object[0]);
        if (a2 == null) {
            a2 = v.a(o, "useSoftWare", new Class[]{Integer.TYPE}, Integer.valueOf(a.a()));
        }
        return a2 == null ? false : ((Boolean) a2).booleanValue();
    }
}
