package com.tencent.smtt.sdk;

import android.annotation.SuppressLint;
import android.app.ActivityManager;
import android.app.AlertDialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Parcelable;
import android.os.Process;
import android.text.TextUtils;
import android.util.Log;
import android.webkit.ValueCallback;
import com.tencent.smtt.export.external.DexClassLoaderProvider;
import com.tencent.smtt.export.external.DexLoader;
import com.tencent.smtt.export.external.interfaces.IX5WebViewBase;
import com.tencent.smtt.sdk.TbsDownloadConfig;
import com.tencent.smtt.sdk.TbsDownloadUpload;
import com.tencent.smtt.sdk.TbsDownloader;
import com.tencent.smtt.sdk.TbsListener;
import com.tencent.smtt.sdk.stat.MttLoader;
import com.tencent.smtt.sdk.ui.dialog.d;
import com.tencent.smtt.sdk.ui.dialog.e;
import com.tencent.smtt.utils.FileProvider;
import com.tencent.smtt.utils.FileUtil;
import com.tencent.smtt.utils.TbsLog;
import com.tencent.smtt.utils.TbsLogClient;
import com.tencent.smtt.utils.b;
import com.tencent.smtt.utils.i;
import com.tencent.smtt.utils.l;
import e.a.a.a.a;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import org.json.JSONArray;
import org.json.JSONObject;

@SuppressLint({"NewApi"})
/* loaded from: classes.dex */
public class QbSdk {
    public static final int EXTENSION_INIT_FAILURE = -99999;
    public static final String FILERADER_MENUDATA = "menuData";
    public static final String LOGIN_TYPE_KEY_PARTNER_CALL_POS = "PosID";
    public static final String LOGIN_TYPE_KEY_PARTNER_ID = "ChannelID";
    public static final String PARAM_KEY_FEATUREID = "param_key_featureid";
    public static final String PARAM_KEY_FUNCTIONID = "param_key_functionid";
    public static final String PARAM_KEY_POSITIONID = "param_key_positionid";
    public static final int QBMODE = 2;
    public static final String SHARE_PREFERENCES_NAME = "tbs_file_open_dialog_config";
    public static final String SVNVERSION = "jnizz";
    public static final int TBSMODE = 1;
    public static final String TID_QQNumber_Prefix = "QQ:";
    public static final int VERSION = 1;

    /* renamed from: b */
    public static boolean f1128b = false;

    /* renamed from: c */
    public static boolean f1129c = true;

    /* renamed from: d */
    public static String f1130d = null;

    /* renamed from: e */
    public static boolean f1131e = false;
    public static long f = 0;
    public static long g = 0;
    public static int o = 0;
    public static String p = "";
    public static Class<?> q = null;
    public static Object r = null;
    public static boolean s = false;
    public static boolean sIsVersionPrinted = false;
    public static String[] t = null;
    public static String u = "NULL";
    public static String v = "UNKNOWN";
    public static Object h = new Object();
    public static boolean isDefaultDialog = false;
    public static boolean w = false;
    public static boolean i = true;
    public static boolean j = true;
    public static boolean k = false;
    public static int x = 0;
    public static int y = TbsListener.ErrorCode.NEEDDOWNLOAD_TRUE;
    public static String z = null;
    public static String A = null;

    /* renamed from: a */
    public static boolean f1127a = false;
    public static volatile boolean l = f1127a;
    public static boolean mDisableUseHostBackupCore = false;
    public static boolean B = false;
    public static boolean C = true;
    public static TbsListener D = null;
    public static TbsListener E = null;
    public static boolean F = false;
    public static boolean G = false;
    public static TbsListener m = new TbsListener() { // from class: com.tencent.smtt.sdk.QbSdk.7
        @Override // com.tencent.smtt.sdk.TbsListener
        public void onDownloadFinish(int i2) {
            if (TbsDownloader.needDownloadDecoupleCore()) {
                TbsLog.i("QbSdk", "onDownloadFinish needDownloadDecoupleCore is true", true);
                TbsDownloader.f1218a = true;
                return;
            }
            TbsLog.i("QbSdk", "onDownloadFinish needDownloadDecoupleCore is false", true);
            TbsDownloader.f1218a = false;
            TbsListener tbsListener = QbSdk.D;
            if (tbsListener != null) {
                tbsListener.onDownloadFinish(i2);
            }
            TbsListener tbsListener2 = QbSdk.E;
            if (tbsListener2 != null) {
                tbsListener2.onDownloadFinish(i2);
            }
        }

        @Override // com.tencent.smtt.sdk.TbsListener
        public void onDownloadProgress(int i2) {
            TbsListener tbsListener = QbSdk.E;
            if (tbsListener != null) {
                tbsListener.onDownloadProgress(i2);
            }
            TbsListener tbsListener2 = QbSdk.D;
            if (tbsListener2 != null) {
                tbsListener2.onDownloadProgress(i2);
            }
        }

        @Override // com.tencent.smtt.sdk.TbsListener
        public void onInstallFinish(int i2) {
            if (i2 != 200) {
            }
            boolean z2 = false;
            QbSdk.G = false;
            TbsDownloader.f1218a = false;
            if (TbsDownloader.startDecoupleCoreIfNeeded()) {
                z2 = true;
            }
            TbsDownloader.f1218a = z2;
            TbsListener tbsListener = QbSdk.D;
            if (tbsListener != null) {
                tbsListener.onInstallFinish(i2);
            }
            TbsListener tbsListener2 = QbSdk.E;
            if (tbsListener2 != null) {
                tbsListener2.onInstallFinish(i2);
            }
        }
    };
    public static String KEY_SET_SENDREQUEST_AND_UPLOAD = "SET_SENDREQUEST_AND_UPLOAD";
    public static Map<String, Object> n = null;

    /* loaded from: classes.dex */
    public interface PreInitCallback {
        void onCoreInitFinished();

        void onViewInitFinished(boolean z);
    }

    public static Bundle a(Context context, Bundle bundle) {
        TbsLogReport instance;
        String str;
        if (!a(context)) {
            instance = TbsLogReport.getInstance(context);
            str = "initForPatch return false!";
        } else {
            Object a2 = i.a(r, "incrUpdate", new Class[]{Context.class, Bundle.class}, context, bundle);
            if (a2 != null) {
                return (Bundle) a2;
            }
            instance = TbsLogReport.getInstance(context);
            str = "incrUpdate return null!";
        }
        instance.setInstallErrorCode(TbsListener.ErrorCode.INCR_UPDATE_ERROR, str);
        return null;
    }

    public static Bundle a(Context context, Map<String, String> map) {
        if (map == null) {
            return null;
        }
        try {
            Bundle bundle = new Bundle();
            bundle.putString("style", map.get("style") == null ? "0" : map.get("style"));
            try {
                bundle.putInt("topBarBgColor", Color.parseColor(map.get("topBarBgColor")));
            } catch (Exception unused) {
            }
            if (map.containsKey(FILERADER_MENUDATA)) {
                JSONObject jSONObject = new JSONObject(map.get(FILERADER_MENUDATA));
                JSONArray jSONArray = jSONObject.getJSONArray("menuItems");
                if (jSONArray != null) {
                    ArrayList<? extends Parcelable> arrayList = new ArrayList<>();
                    for (int i2 = 0; i2 < jSONArray.length() && i2 < 5; i2++) {
                        try {
                            JSONObject jSONObject2 = (JSONObject) jSONArray.get(i2);
                            arrayList.add(i2, BitmapFactory.decodeResource(context.getResources(), jSONObject2.getInt("iconResId")));
                            jSONObject2.put("iconResId", i2);
                        } catch (Exception unused2) {
                        }
                    }
                    bundle.putParcelableArrayList("resArray", arrayList);
                }
                bundle.putString(FILERADER_MENUDATA, jSONObject.toString());
            }
            return bundle;
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public static Object a(Context context, String str, Bundle bundle) {
        if (!a(context)) {
            return Integer.valueOf((int) EXTENSION_INIT_FAILURE);
        }
        Object a2 = i.a(r, "miscCall", new Class[]{String.class, Bundle.class}, str, bundle);
        if (a2 != null) {
            return a2;
        }
        return null;
    }

    public static String a() {
        return p;
    }

    public static synchronized void a(Context context, String str) {
        synchronized (QbSdk.class) {
            if (!f1127a) {
                f1127a = true;
                v = "forceSysWebViewInner: " + str;
                TbsLog.e("QbSdk", "QbSdk.SysWebViewForcedInner..." + v);
                TbsCoreLoadStat.getInstance().a(context, TbsListener.ErrorCode.INFO_FORCE_SYSTEM_WEBVIEW_INNER, new Throwable(v));
            }
        }
    }

    public static boolean a(Context context) {
        try {
            if (q != null) {
                return true;
            }
            File q2 = m.a().q(context);
            if (q2 == null) {
                TbsLog.e("QbSdk", "QbSdk initExtension (false) optDir == null");
                return false;
            }
            File file = new File(q2, "tbs_sdk_extension_dex.jar");
            if (!file.exists()) {
                TbsLog.e("QbSdk", "QbSdk initExtension (false) dexFile.exists()=false", true);
                return false;
            }
            TbsLog.i("QbSdk", "new DexLoader #3 dexFile is " + file.getAbsolutePath());
            u.a().b(context);
            l.a(context);
            q = new DexLoader(file.getParent(), context, new String[]{file.getAbsolutePath()}, q2.getAbsolutePath(), n).loadClass("com.tencent.tbs.sdk.extension.TbsSDKExtension");
            loadTBSSDKExtension(context, file.getParent());
            return true;
        } catch (Throwable th) {
            StringBuilder a2 = a.a("initExtension sys WebView: ");
            a2.append(Log.getStackTraceString(th));
            TbsLog.e("QbSdk", a2.toString());
            return false;
        }
    }

    public static boolean a(Context context, int i2) {
        return a(context, i2, 20000);
    }

    public static boolean a(Context context, int i2, int i3) {
        Map<String, Object> map = n;
        if (map == null || !map.containsKey(KEY_SET_SENDREQUEST_AND_UPLOAD) || !n.get(KEY_SET_SENDREQUEST_AND_UPLOAD).equals("false")) {
            m.a().b(context, d.f1334a == 0);
            if (!c(context)) {
                return true;
            }
            Object obj = r;
            Class cls = Integer.TYPE;
            Object a2 = i.a(obj, "isX5Disabled", new Class[]{cls, cls, cls}, Integer.valueOf(i2), 43939, Integer.valueOf(i3));
            if (a2 == null) {
                Object obj2 = r;
                Class cls2 = Integer.TYPE;
                a2 = i.a(obj2, "isX5Disabled", new Class[]{cls2, cls2}, Integer.valueOf(i2), 43939);
                if (a2 == null) {
                    return true;
                }
            }
            return ((Boolean) a2).booleanValue();
        }
        TbsLog.i("QbSdk", "[QbSdk.isX5Disabled] -- SET_SENDREQUEST_AND_UPLOAD is false");
        return true;
    }

    public static boolean a(Context context, String str, String str2) {
        return isSuportOpenFile(str2, 2);
    }

    public static String b() {
        Object invokeStaticMethod;
        u a2 = u.a();
        if (a2 == null || !a2.b() || (invokeStaticMethod = a2.c().b().invokeStaticMethod("com.tencent.tbs.tbsshell.WebCoreProxy", "getGUID", new Class[0], new Object[0])) == null || !(invokeStaticMethod instanceof String)) {
            return null;
        }
        return (String) invokeStaticMethod;
    }

    public static boolean b(Context context) {
        SharedPreferences sharedPreferences;
        if (context == null) {
            return false;
        }
        try {
            if (!context.getApplicationInfo().packageName.contains("com.tencent.portfolio")) {
                return true;
            }
            TbsLog.i("QbSdk", "clearPluginConfigFile #1");
            String string = TbsDownloadConfig.getInstance(context).mPreferences.getString(TbsDownloadConfig.TbsConfigKey.KEY_APP_VERSIONNAME, null);
            String str = context.getPackageManager().getPackageInfo("com.tencent.portfolio", 0).versionName;
            TbsLog.i("QbSdk", "clearPluginConfigFile oldAppVersionName is " + string + " newAppVersionName is " + str);
            if (string == null || string.contains(str) || (sharedPreferences = context.getSharedPreferences("plugin_setting", 0)) == null) {
                return true;
            }
            SharedPreferences.Editor edit = sharedPreferences.edit();
            edit.clear();
            edit.commit();
            TbsLog.i("QbSdk", "clearPluginConfigFile done");
            return true;
        } catch (Throwable th) {
            StringBuilder a2 = a.a("clearPluginConfigFile error is ");
            a2.append(th.getMessage());
            TbsLog.i("QbSdk", a2.toString());
            return false;
        }
    }

    public static boolean c(Context context) {
        File file;
        try {
            if (q != null) {
                return true;
            }
            File q2 = m.a().q(context);
            if (q2 == null) {
                TbsLog.e("QbSdk", "QbSdk initForX5DisableConfig (false) optDir == null");
                return false;
            }
            if (!TbsShareManager.isThirdPartyApp(context)) {
                file = new File(m.a().q(context), "tbs_sdk_extension_dex.jar");
            } else if (TbsShareManager.j(context)) {
                TbsShareManager.j(context);
                file = new File(TbsShareManager.f1266d, "tbs_sdk_extension_dex.jar");
            } else {
                TbsCoreLoadStat.getInstance().a(context, TbsListener.ErrorCode.ERROR_HOST_UNAVAILABLE);
                return false;
            }
            if (!file.exists()) {
                TbsCoreLoadStat.getInstance().a(context, TbsListener.ErrorCode.INFO_MISS_SDKEXTENSION_JAR_OLD, new Exception("initForX5DisableConfig failure -- tbs_sdk_extension_dex.jar is not exist!"));
                return false;
            }
            String absolutePath = TbsShareManager.f1265c != null ? TbsShareManager.f1265c : q2.getAbsolutePath();
            TbsLog.i("QbSdk", "QbSdk init optDirExtension #3 is " + absolutePath);
            TbsLog.i("QbSdk", "new DexLoader #4 dexFile is " + file.getAbsolutePath());
            u.a().b(context);
            l.a(context);
            q = new DexLoader(file.getParent(), context, new String[]{file.getAbsolutePath()}, absolutePath, n).loadClass("com.tencent.tbs.sdk.extension.TbsSDKExtension");
            loadTBSSDKExtension(context, file.getParent());
            i.a(r, "setClientVersion", new Class[]{Integer.TYPE}, 1);
            return true;
        } catch (Throwable th) {
            StringBuilder a2 = a.a("initForX5DisableConfig sys WebView: ");
            a2.append(Log.getStackTraceString(th));
            TbsLog.e("QbSdk", a2.toString());
            return false;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0036  */
    /* JADX WARN: Removed duplicated region for block: B:12:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static boolean canLoadVideo(android.content.Context r5) {
        /*
            java.lang.Object r0 = com.tencent.smtt.sdk.QbSdk.r
            r1 = 1
            java.lang.Class[] r2 = new java.lang.Class[r1]
            java.lang.Class r3 = java.lang.Integer.TYPE
            r4 = 0
            r2[r4] = r3
            java.lang.Object[] r1 = new java.lang.Object[r1]
            java.lang.Integer r3 = java.lang.Integer.valueOf(r4)
            r1[r4] = r3
            java.lang.String r3 = "canLoadVideo"
            java.lang.Object r0 = com.tencent.smtt.utils.i.a(r0, r3, r2, r1)
            if (r0 == 0) goto L_0x002a
            r1 = r0
            java.lang.Boolean r1 = (java.lang.Boolean) r1
            boolean r1 = r1.booleanValue()
            if (r1 != 0) goto L_0x0033
            com.tencent.smtt.sdk.TbsCoreLoadStat r1 = com.tencent.smtt.sdk.TbsCoreLoadStat.getInstance()
            r2 = 313(0x139, float:4.39E-43)
            goto L_0x0030
        L_0x002a:
            com.tencent.smtt.sdk.TbsCoreLoadStat r1 = com.tencent.smtt.sdk.TbsCoreLoadStat.getInstance()
            r2 = 314(0x13a, float:4.4E-43)
        L_0x0030:
            r1.a(r5, r2)
        L_0x0033:
            if (r0 != 0) goto L_0x0036
            goto L_0x003c
        L_0x0036:
            java.lang.Boolean r0 = (java.lang.Boolean) r0
            boolean r4 = r0.booleanValue()
        L_0x003c:
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.sdk.QbSdk.canLoadVideo(android.content.Context):boolean");
    }

    public static boolean canLoadX5(Context context) {
        return a(context, false, false);
    }

    public static boolean canLoadX5FirstTimeThirdApp(Context context) {
        try {
            if (context.getApplicationInfo().packageName.contains("com.moji.mjweather")) {
                int i2 = Build.VERSION.SDK_INT;
            }
            if (q == null) {
                File q2 = m.a().q(context);
                if (q2 == null) {
                    TbsLog.e("QbSdk", "QbSdk canLoadX5FirstTimeThirdApp (false) optDir == null");
                    return false;
                }
                TbsShareManager.j(context);
                File file = new File(TbsShareManager.f1266d, "tbs_sdk_extension_dex.jar");
                if (!file.exists()) {
                    TbsLog.e("QbSdk", "QbSdk canLoadX5FirstTimeThirdApp (false) dexFile.exists()=false", true);
                    return false;
                }
                String absolutePath = TbsShareManager.f1265c != null ? TbsShareManager.f1265c : q2.getAbsolutePath();
                TbsLog.i("QbSdk", "QbSdk init optDirExtension #2 is " + absolutePath);
                TbsLog.i("QbSdk", "new DexLoader #2 dexFile is " + file.getAbsolutePath());
                u.a().b(context);
                l.a(context);
                q = new DexLoader(file.getParent(), context, new String[]{file.getAbsolutePath()}, absolutePath, n).loadClass("com.tencent.tbs.sdk.extension.TbsSDKExtension");
                if (r == null) {
                    if (TbsShareManager.e(context) == null && TbsShareManager.f1265c == null) {
                        TbsLogReport.getInstance(context.getApplicationContext()).setLoadErrorCode(TbsListener.ErrorCode.HOST_CONTEXT_IS_NULL, "host context is null!");
                        return false;
                    }
                    loadTBSSDKExtension(context, file.getParent());
                }
            }
            Object a2 = i.a(r, "canLoadX5CoreForThirdApp", new Class[0], new Object[0]);
            if (a2 == null || !(a2 instanceof Boolean)) {
                return false;
            }
            return ((Boolean) a2).booleanValue();
        } catch (Throwable th) {
            StringBuilder a3 = a.a("canLoadX5FirstTimeThirdApp sys WebView: ");
            a3.append(Log.getStackTraceString(th));
            TbsLog.e("QbSdk", a3.toString());
            return false;
        }
    }

    public static void canOpenFile(final Context context, final String str, final ValueCallback<Boolean> valueCallback) {
        new Thread() { // from class: com.tencent.smtt.sdk.QbSdk.1
            @Override // java.lang.Thread, java.lang.Runnable
            public void run() {
                u a2 = u.a();
                a2.a(context);
                final boolean a3 = a2.b() ? a2.c().a(context, str) : false;
                new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: com.tencent.smtt.sdk.QbSdk.1.1
                    @Override // java.lang.Runnable
                    public void run() {
                        valueCallback.onReceiveValue(Boolean.valueOf(a3));
                    }
                });
            }
        }.start();
    }

    public static boolean canOpenMimeFileType(Context context, String str) {
        if (!a(context, false)) {
        }
        return false;
    }

    public static boolean canOpenWebPlus(Context context) {
        Throwable th;
        Throwable th2;
        Throwable th3;
        if (x == 0) {
            x = a.a();
        }
        StringBuilder a2 = a.a("canOpenWebPlus - totalRAM: ");
        a2.append(x);
        TbsLog.i("QbSdk", a2.toString());
        int i2 = Build.VERSION.SDK_INT;
        boolean z2 = false;
        if (x < y || context == null) {
            return false;
        }
        BufferedInputStream bufferedInputStream = null;
        r0 = null;
        FileInputStream fileInputStream = null;
        bufferedInputStream = null;
        try {
            try {
                bufferedInputStream = new BufferedInputStream(new FileInputStream(new File(m.a().q(context), "tbs.conf")));
            } catch (Throwable th4) {
                th2 = th4;
            }
        } catch (Throwable th5) {
            th = th5;
        }
        try {
            Properties properties = new Properties();
            properties.load(bufferedInputStream);
            String property = properties.getProperty("android_sdk_max_supported");
            String property2 = properties.getProperty("android_sdk_min_supported");
            int parseInt = Integer.parseInt(property);
            int parseInt2 = Integer.parseInt(property2);
            int parseInt3 = Integer.parseInt(Build.VERSION.SDK);
            if (parseInt3 <= parseInt && parseInt3 >= parseInt2) {
                int parseInt4 = Integer.parseInt(properties.getProperty("tbs_core_version"));
                try {
                    bufferedInputStream.close();
                } catch (Exception unused) {
                }
                try {
                    try {
                        fileInputStream = new FileInputStream(new File(m.s(context), "tbs_extension.conf"));
                    } catch (Throwable th6) {
                        th3 = th6;
                    }
                } catch (Throwable unused2) {
                }
                try {
                    Properties properties2 = new Properties();
                    properties2.load(fileInputStream);
                    int parseInt5 = Integer.parseInt(properties2.getProperty("tbs_local_version"));
                    int parseInt6 = Integer.parseInt(properties2.getProperty(TbsDownloadConfig.TbsConfigKey.KEY_APP_VERSIONCODE_FOR_SWITCH));
                    if (parseInt4 != 88888888 && parseInt5 != 88888888 && parseInt4 <= parseInt5 && parseInt4 == parseInt5 && ((parseInt6 <= 0 || parseInt6 == b.b(context)) && Boolean.parseBoolean(properties2.getProperty("x5_disabled")))) {
                        if (!TbsDownloadConfig.getInstance(context.getApplicationContext()).mPreferences.getBoolean(TbsDownloadConfig.TbsConfigKey.KEY_SWITCH_BACKUPCORE_ENABLE, false)) {
                            z2 = true;
                        }
                    }
                    try {
                        fileInputStream.close();
                    } catch (Exception unused3) {
                        return !z2;
                    }
                } catch (Throwable th7) {
                    th3 = th7;
                    if (fileInputStream != null) {
                        try {
                            fileInputStream.close();
                        } catch (Exception unused4) {
                        }
                    }
                    throw th3;
                }
            }
            TbsLog.i("QbSdk", "canOpenWebPlus - sdkVersion: " + parseInt3);
            try {
                bufferedInputStream.close();
            } catch (Exception unused5) {
            }
            return false;
        } catch (Throwable th8) {
            th2 = th8;
            bufferedInputStream = bufferedInputStream;
            th2.printStackTrace();
            TbsLog.i("QbSdk", "canOpenWebPlus - canLoadX5 Exception");
            if (bufferedInputStream != null) {
                try {
                    bufferedInputStream.close();
                } catch (Exception unused6) {
                }
            }
            return false;
        }
    }

    public static boolean canUseVideoFeatrue(Context context, int i2) {
        Object a2 = i.a(r, "canUseVideoFeatrue", new Class[]{Integer.TYPE}, Integer.valueOf(i2));
        if (a2 == null || !(a2 instanceof Boolean)) {
            return false;
        }
        return ((Boolean) a2).booleanValue();
    }

    public static boolean checkApkExist(Context context, String str) {
        if (str != null && !BuildConfig.FLAVOR.equals(str)) {
            try {
                context.getPackageManager().getApplicationInfo(str, 8192);
                return true;
            } catch (PackageManager.NameNotFoundException unused) {
            }
        }
        return false;
    }

    public static boolean checkContentProviderPrivilage(Context context) {
        if (context == null || context.getApplicationInfo().targetSdkVersion < 24 || Build.VERSION.SDK_INT < 24 || TbsConfig.APP_QQ.equals(context.getApplicationInfo().packageName)) {
            return true;
        }
        try {
            if (!TextUtils.isEmpty(context.getPackageManager().getProviderInfo(new ComponentName(context.getPackageName(), "androidx.core.content.FileProvider"), 0).authority)) {
                return true;
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        PackageManager packageManager = context.getPackageManager();
        StringBuilder sb = new StringBuilder();
        sb.append(context.getApplicationInfo().packageName);
        sb.append(".provider");
        return packageManager.resolveContentProvider(sb.toString(), TbsListener.ErrorCode.DOWNLOAD_INTERRUPT) != null;
    }

    public static void checkTbsValidity(Context context) {
        if (context != null && !l.b(context)) {
            TbsLog.e("QbSdk", "sys WebView: SysWebViewForcedBy checkTbsValidity");
            TbsCoreLoadStat.getInstance().a(context, TbsListener.ErrorCode.INFO_CORE_CHECK_VALIDITY_FALSE);
            forceSysWebView();
        }
    }

    public static void clear(Context context) {
    }

    public static void clearAllDefaultBrowser(Context context) {
        context.getSharedPreferences(SHARE_PREFERENCES_NAME, 0).edit().clear().commit();
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x0062  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0068 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static void clearAllWebViewCache(android.content.Context r5, boolean r6) {
        /*
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "clearAllWebViewCache("
            r0.append(r1)
            r0.append(r5)
            java.lang.String r1 = ", "
            r0.append(r1)
            r0.append(r6)
            java.lang.String r1 = ")"
            r0.append(r1)
            java.lang.String r0 = r0.toString()
            java.lang.String r1 = "QbSdk"
            com.tencent.smtt.utils.TbsLog.i(r1, r0)
            r0 = 1
            r2 = 0
            com.tencent.smtt.sdk.WebView r3 = new com.tencent.smtt.sdk.WebView     // Catch: Throwable -> 0x0049
            r4 = 0
            r3.<init>(r5, r4, r2, r2)     // Catch: Throwable -> 0x0049
            com.tencent.smtt.export.external.extension.interfaces.IX5WebViewClientExtension r3 = r3.getWebViewClientExtension()     // Catch: Throwable -> 0x0049
            if (r3 == 0) goto L_0x0060
            com.tencent.smtt.sdk.u r2 = com.tencent.smtt.sdk.u.a()     // Catch: Throwable -> 0x0046
            if (r2 == 0) goto L_0x0044
            boolean r3 = r2.b()     // Catch: Throwable -> 0x0046
            if (r3 == 0) goto L_0x0044
            com.tencent.smtt.sdk.v r2 = r2.c()     // Catch: Throwable -> 0x0046
            r2.a(r5, r6)     // Catch: Throwable -> 0x0046
        L_0x0044:
            r2 = 1
            goto L_0x0060
        L_0x0046:
            r2 = move-exception
            r3 = 1
            goto L_0x004b
        L_0x0049:
            r2 = move-exception
            r3 = 0
        L_0x004b:
            java.lang.String r4 = "clearAllWebViewCache exception 2 -- "
            java.lang.StringBuilder r4 = e.a.a.a.a.a(r4)
            java.lang.String r2 = android.util.Log.getStackTraceString(r2)
            r4.append(r2)
            java.lang.String r2 = r4.toString()
            com.tencent.smtt.utils.TbsLog.e(r1, r2)
            r2 = r3
        L_0x0060:
            if (r2 == 0) goto L_0x0068
            java.lang.String r5 = "is_in_x5_mode --> no need to clear system webview!"
            com.tencent.smtt.utils.TbsLog.i(r1, r5)
            return
        L_0x0068:
            android.webkit.WebView r2 = new android.webkit.WebView     // Catch: Throwable -> 0x00b1
            r2.<init>(r5)     // Catch: Throwable -> 0x00b1
            int r3 = android.os.Build.VERSION.SDK_INT     // Catch: Throwable -> 0x00b1
            java.lang.String r3 = "searchBoxJavaBridge_"
            r2.removeJavascriptInterface(r3)     // Catch: Throwable -> 0x00b1
            java.lang.String r3 = "accessibility"
            r2.removeJavascriptInterface(r3)     // Catch: Throwable -> 0x00b1
            java.lang.String r3 = "accessibilityTraversal"
            r2.removeJavascriptInterface(r3)     // Catch: Throwable -> 0x00b1
            r2.clearCache(r0)     // Catch: Throwable -> 0x00b1
            if (r6 == 0) goto L_0x008d
            android.webkit.CookieSyncManager.createInstance(r5)     // Catch: Throwable -> 0x00b1
            android.webkit.CookieManager r6 = android.webkit.CookieManager.getInstance()     // Catch: Throwable -> 0x00b1
            r6.removeAllCookie()     // Catch: Throwable -> 0x00b1
        L_0x008d:
            android.webkit.WebViewDatabase r6 = android.webkit.WebViewDatabase.getInstance(r5)     // Catch: Throwable -> 0x00b1
            r6.clearUsernamePassword()     // Catch: Throwable -> 0x00b1
            android.webkit.WebViewDatabase r6 = android.webkit.WebViewDatabase.getInstance(r5)     // Catch: Throwable -> 0x00b1
            r6.clearHttpAuthUsernamePassword()     // Catch: Throwable -> 0x00b1
            android.webkit.WebViewDatabase r5 = android.webkit.WebViewDatabase.getInstance(r5)     // Catch: Throwable -> 0x00b1
            r5.clearFormData()     // Catch: Throwable -> 0x00b1
            android.webkit.WebStorage r5 = android.webkit.WebStorage.getInstance()     // Catch: Throwable -> 0x00b1
            r5.deleteAllData()     // Catch: Throwable -> 0x00b1
            android.webkit.WebIconDatabase r5 = android.webkit.WebIconDatabase.getInstance()     // Catch: Throwable -> 0x00b1
            r5.removeAllIcons()     // Catch: Throwable -> 0x00b1
            goto L_0x00c6
        L_0x00b1:
            r5 = move-exception
            java.lang.String r6 = "clearAllWebViewCache exception 1 -- "
            java.lang.StringBuilder r6 = e.a.a.a.a.a(r6)
            java.lang.String r5 = android.util.Log.getStackTraceString(r5)
            r6.append(r5)
            java.lang.String r5 = r6.toString()
            com.tencent.smtt.utils.TbsLog.e(r1, r5)
        L_0x00c6:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.sdk.QbSdk.clearAllWebViewCache(android.content.Context, boolean):void");
    }

    public static void clearDefaultBrowser(Context context, String str) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARE_PREFERENCES_NAME, 0);
        String d2 = e.d(str);
        if (TextUtils.isEmpty(d2)) {
            d2 = "*/*";
        }
        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.remove("key_tbs_picked_default_browser_" + d2).commit();
    }

    public static void closeFileReader(Context context) {
        u a2 = u.a();
        a2.a(context);
        if (a2.b()) {
            a2.c().p();
        }
    }

    public static String closeNetLogAndSavaToLocal() {
        u a2 = u.a();
        if (a2 != null && a2.b()) {
            try {
                Object invokeStaticMethod = a2.c().b().invokeStaticMethod("com.tencent.smtt.livelog.NetLogManager", "closeNetLogAndSavaToLocal", new Class[0], new Object[0]);
                if (invokeStaticMethod != null && (invokeStaticMethod instanceof String)) {
                    return (String) invokeStaticMethod;
                }
            } catch (Exception unused) {
            }
        }
        return BuildConfig.FLAVOR;
    }

    public static void continueLoadSo(Context context) {
        if (TbsConfig.APP_WX.equals(getCurrentProcessName(context)) && WebView.mWebViewCreated) {
            i.a(r, "continueLoadSo", new Class[0], new Object[0]);
        }
    }

    public static boolean createMiniQBShortCut(Context context, String str, String str2, Drawable drawable) {
        u a2;
        if (!(context == null || TbsDownloader.getOverSea(context) || isMiniQBShortCutExist(context, str, str2) || (a2 = u.a()) == null || !a2.b())) {
            Bitmap bitmap = null;
            if (drawable instanceof BitmapDrawable) {
                bitmap = ((BitmapDrawable) drawable).getBitmap();
            }
            DexLoader b2 = a2.c().b();
            TbsLog.e("QbSdk", "qbsdk createMiniQBShortCut");
            Object invokeStaticMethod = b2.invokeStaticMethod("com.tencent.tbs.tbsshell.WebCoreProxy", "createMiniQBShortCut", new Class[]{Context.class, String.class, String.class, Bitmap.class}, context, str, str2, bitmap);
            TbsLog.e("QbSdk", "qbsdk after createMiniQBShortCut ret: " + invokeStaticMethod);
            if (invokeStaticMethod != null) {
                return true;
            }
        }
        return false;
    }

    /* JADX WARN: Removed duplicated region for block: B:39:0x00e0  */
    /* JADX WARN: Removed duplicated region for block: B:52:0x006d A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static void d(android.content.Context r10) {
        /*
            Method dump skipped, instructions count: 308
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.sdk.QbSdk.d(android.content.Context):void");
    }

    public static boolean deleteMiniQBShortCut(Context context, String str, String str2) {
        u a2;
        return (context == null || TbsDownloader.getOverSea(context) || (a2 = u.a()) == null || !a2.b() || a2.c().b().invokeStaticMethod("com.tencent.tbs.tbsshell.WebCoreProxy", "deleteMiniQBShortCut", new Class[]{Context.class, String.class, String.class}, context, str, str2) == null) ? false : true;
    }

    public static void disAllowThirdAppDownload() {
        f1129c = false;
    }

    public static void disableAutoCreateX5Webview() {
        j = false;
    }

    public static void fileInfoDetect(Context context, String str, ValueCallback<String> valueCallback) {
        u a2 = u.a();
        if (a2 != null && a2.b()) {
            try {
                a2.c().b().invokeStaticMethod("com.tencent.tbs.tbsshell.WebCoreProxy", "fileInfoDetect", new Class[]{Context.class, String.class, ValueCallback.class}, context, str, valueCallback);
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
    }

    public static void forceSysWebView() {
        f1128b = true;
        StringBuilder a2 = a.a("SysWebViewForcedByOuter: ");
        a2.append(Log.getStackTraceString(new Throwable()));
        u = a2.toString();
        TbsLog.e("QbSdk", "sys WebView: SysWebViewForcedByOuter");
    }

    public static long getApkFileSize(Context context) {
        if (context != null) {
            return TbsDownloadConfig.getInstance(context.getApplicationContext()).mPreferences.getLong(TbsDownloadConfig.TbsConfigKey.KEY_TBSAPKFILESIZE, 0L);
        }
        return 0L;
    }

    public static String getCurrentProcessName(Context context) {
        try {
            int myPid = Process.myPid();
            String str = BuildConfig.FLAVOR;
            for (ActivityManager.RunningAppProcessInfo runningAppProcessInfo : ((ActivityManager) context.getApplicationContext().getSystemService("activity")).getRunningAppProcesses()) {
                if (runningAppProcessInfo.pid == myPid) {
                    str = runningAppProcessInfo.processName;
                }
            }
            return str;
        } catch (Throwable unused) {
            return BuildConfig.FLAVOR;
        }
    }

    public static String[] getDexLoaderFileList(Context context, Context context2, String str) {
        String[] strArr = t;
        if (strArr instanceof String[]) {
            int length = strArr.length;
            String[] strArr2 = new String[length];
            for (int i2 = 0; i2 < length; i2++) {
                StringBuilder a2 = a.a(str);
                a2.append(t[i2]);
                strArr2[i2] = a2.toString();
            }
            return strArr2;
        }
        Object a3 = i.a(r, "getJarFiles", new Class[]{Context.class, Context.class, String.class}, context, context2, str);
        if (!(a3 instanceof String[])) {
            a3 = new String[]{BuildConfig.FLAVOR};
        }
        return (String[]) a3;
    }

    public static boolean getDownloadWithoutWifi() {
        return F;
    }

    public static boolean getIsSysWebViewForcedByOuter() {
        return f1128b;
    }

    public static boolean getJarFilesAndLibraryPath(Context context) {
        String str;
        Object obj = r;
        if (obj == null) {
            str = "getJarFilesAndLibraryPath sExtensionObj is null";
        } else {
            Bundle bundle = (Bundle) i.a(obj, "canLoadX5CoreAndNotLoadSo", new Class[]{Integer.TYPE}, 43939);
            if (bundle == null) {
                StringBuilder a2 = a.a("getJarFilesAndLibraryPath bundle is null and coreverison is ");
                a2.append(m.a().a(true, context));
                str = a2.toString();
            } else {
                t = bundle.getStringArray("tbs_jarfiles");
                f1130d = bundle.getString("tbs_librarypath");
                return true;
            }
        }
        TbsLog.i("QbSdk", str);
        return false;
    }

    public static String getMiniQBVersion(Context context) {
        u a2 = u.a();
        a2.a(context);
        if (a2.b()) {
            return a2.c().f();
        }
        return null;
    }

    public static boolean getOnlyDownload() {
        return k;
    }

    public static String getQQBuildNumber() {
        return A;
    }

    public static Map<String, Object> getSettings() {
        return n;
    }

    public static boolean getTBSInstalling() {
        return G;
    }

    public static String getTID() {
        return z;
    }

    public static File getTbsFolderDir(Context context) {
        if (context == null) {
            return null;
        }
        try {
            if (b.c()) {
                return context.getDir("tbs_64", 0);
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        return context.getDir("tbs", 0);
    }

    public static String getTbsResourcesPath(Context context) {
        return TbsShareManager.g(context);
    }

    public static int getTbsSdkVersion() {
        return 43939;
    }

    public static int getTbsVersion(Context context) {
        if (!TbsShareManager.isThirdPartyApp(context)) {
            return m.a().i(context);
        }
        TbsShareManager.b(context, false);
        return TbsShareManager.f1267e;
    }

    public static int getTbsVersionForCrash(Context context) {
        if (TbsShareManager.isThirdPartyApp(context)) {
            TbsShareManager.b(context, false);
            return TbsShareManager.f1267e;
        }
        int j2 = m.a().j(context);
        if (j2 == 0 && k.a(context).c() == 3) {
            reset(context, false);
        }
        return j2;
    }

    public static int getTmpDirTbsVersion(Context context) {
        if (k.a(context).c() == 2) {
            return m.a().e(context, 0);
        }
        if (k.a(context).b("copy_status") == 1) {
            return m.a().e(context, 1);
        }
        return 0;
    }

    public static void initBuglyAsync(boolean z2) {
        i = z2;
    }

    public static void initForinitAndNotLoadSo(Context context) {
        File q2;
        if (q == null && (q2 = m.a().q(context)) != null) {
            File file = new File(q2, "tbs_sdk_extension_dex.jar");
            if (file.exists()) {
                String absolutePath = q2.getAbsolutePath();
                u.a().b(context);
                l.a(context);
                q = new DexLoader(file.getParent(), context, new String[]{file.getAbsolutePath()}, absolutePath, n).loadClass("com.tencent.tbs.sdk.extension.TbsSDKExtension");
            }
        }
    }

    public static void initTbsSettings(Map<String, Object> map) {
        Map<String, Object> map2 = n;
        if (map2 == null) {
            n = map;
            return;
        }
        try {
            map2.putAll(map);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public static void initX5Environment(final Context context, final PreInitCallback preInitCallback) {
        TbsLog.initIfNeed(context);
        if (context == null) {
            TbsLog.e("QbSdk", "initX5Environment,context=null");
            return;
        }
        b(context);
        E = new TbsListener() { // from class: com.tencent.smtt.sdk.QbSdk.5
            @Override // com.tencent.smtt.sdk.TbsListener
            public void onDownloadFinish(int i2) {
            }

            @Override // com.tencent.smtt.sdk.TbsListener
            public void onDownloadProgress(int i2) {
            }

            @Override // com.tencent.smtt.sdk.TbsListener
            public void onInstallFinish(int i2) {
                QbSdk.preInit(context, preInitCallback);
            }
        };
        if (TbsShareManager.isThirdPartyApp(context)) {
            m.a().b(context, d.f1334a == 0);
        }
        TbsDownloader.needDownload(context, false, false, true, new TbsDownloader.TbsDownloaderCallback() { // from class: com.tencent.smtt.sdk.QbSdk.6
            @Override // com.tencent.smtt.sdk.TbsDownloader.TbsDownloaderCallback
            public void onNeedDownloadFinish(boolean z2, int i2) {
                if (TbsShareManager.findCoreForThirdPartyApp(context) == 0 && !TbsShareManager.g) {
                    TbsShareManager.forceToLoadX5ForThirdApp(context, false);
                }
                if (QbSdk.i && TbsShareManager.isThirdPartyApp(context)) {
                    TbsExtensionFunctionManager.getInstance().initTbsBuglyIfNeed(context);
                }
                QbSdk.preInit(context, preInitCallback);
            }
        });
    }

    public static boolean installLocalQbApk(Context context, String str, String str2, Bundle bundle) {
        d a2 = d.a(true);
        a2.a(context, false, false);
        if (a2.b()) {
            return a2.a().a(context, str, str2, bundle);
        }
        return false;
    }

    public static boolean intentDispatch(WebView webView, Intent intent, String str, String str2) {
        String str3;
        if (webView == null) {
            return false;
        }
        if (str.startsWith("mttbrowser://miniqb/ch=icon?")) {
            Context context = webView.getContext();
            int indexOf = str.indexOf("url=");
            str = indexOf > 0 ? str.substring(indexOf + 4) : null;
            HashMap hashMap = new HashMap();
            try {
                str3 = context.getApplicationInfo().packageName;
            } catch (Exception e2) {
                e2.printStackTrace();
                str3 = "unknown";
            }
            hashMap.put("ChannelID", str3);
            hashMap.put("PosID", "14004");
            if (MttLoader.loadUrl(context, "miniqb://home".equals(str) ? "qb://navicard/addCard?cardId=168&cardName=168" : str, hashMap, "QbSdk.startMiniQBToLoadUrl", null) != 0) {
                u a2 = u.a();
                if (a2 != null && a2.b() && a2.c().a(context, str, null, str2, null) == 0) {
                    return true;
                }
            }
            return false;
        }
        webView.loadUrl(str);
        return false;
    }

    public static boolean isInDefaultBrowser(Context context, String str) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARE_PREFERENCES_NAME, 0);
        String d2 = e.d(str);
        if (TextUtils.isEmpty(d2)) {
            d2 = "*/*";
        }
        return sharedPreferences.contains("key_tbs_picked_default_browser_" + d2);
    }

    public static boolean isMiniQBShortCutExist(Context context, String str, String str2) {
        u a2;
        Object invokeStaticMethod;
        if (context == null || TbsDownloader.getOverSea(context) || (a2 = u.a()) == null || !a2.b() || (invokeStaticMethod = a2.c().b().invokeStaticMethod("com.tencent.tbs.tbsshell.WebCoreProxy", "isMiniQBShortCutExist", new Class[]{Context.class, String.class}, context, str)) == null) {
            return false;
        }
        Boolean bool = false;
        if (invokeStaticMethod instanceof Boolean) {
            bool = (Boolean) invokeStaticMethod;
        }
        return bool.booleanValue();
    }

    public static boolean isNeedInitX5FirstTime() {
        return w;
    }

    public static boolean isSuportOpenFile(String str, int i2) {
        List asList;
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        String[] strArr = {"rar", "zip", "tar", "bz2", "gz", "7z", "doc", "docx", "ppt", "pptx", "xls", "xlsx", "txt", "pdf", "epub", "chm", "html", "htm", "xml", "mht", TbsVideoCacheTask.KEY_VIDEO_CACHE_PARAM_URL, "ini", "log", "bat", "php", "js", "lrc", "jpg", "jpeg", "png", "gif", "bmp", "tiff", "webp", "mp3", "m4a", "aac", "amr", "wav", "ogg", "mid", "ra", "wma", "mpga", "ape", "flac", "RTSP", "RTP", "SDP", "RTMP", "mp4", "flv", "avi", "3gp", "3gpp", "webm", "ts", "ogv", "m3u8", "asf", "wmv", "rmvb", "rm", "f4v", "dat", "mov", "mpg", "mkv", "mpeg", "mpeg1", "mpeg2", "xvid", "dvd", "vcd", "vob", "divx"};
        String[] strArr2 = {"doc", "docx", "ppt", "pptx", "xls", "xlsx", "txt", "pdf", "epub"};
        if (i2 == 1) {
            asList = Arrays.asList(strArr2);
        } else if (i2 != 2) {
            return false;
        } else {
            asList = Arrays.asList(strArr);
        }
        return asList.contains(str.toLowerCase());
    }

    public static boolean isTbsCoreInited() {
        d a2 = d.a(false);
        return a2 != null && a2.g();
    }

    public static boolean isX5DisabledSync(Context context) {
        if (k.a(context).c() == 2) {
            return false;
        }
        if (!c(context)) {
            return true;
        }
        int i2 = m.a().i(context);
        Object obj = r;
        Class cls = Integer.TYPE;
        Object a2 = i.a(obj, "isX5DisabledSync", new Class[]{cls, cls}, Integer.valueOf(i2), 43939);
        if (a2 != null) {
            return ((Boolean) a2).booleanValue();
        }
        return true;
    }

    public static void loadTBSSDKExtension(Context context, String str) {
        Constructor<?> constructor;
        boolean z2;
        Object newInstance;
        if (r == null) {
            synchronized (QbSdk.class) {
                if (r == null) {
                    if (q == null) {
                        TbsLog.i("QbSdk", "QbSdk loadTBSSDKExtension sExtensionClass is null");
                    }
                    try {
                        constructor = q.getConstructor(Context.class, Context.class, String.class, String.class, String.class);
                        z2 = true;
                    } catch (Throwable unused) {
                        constructor = null;
                        z2 = false;
                    }
                    if (TbsShareManager.isThirdPartyApp(context)) {
                        Context e2 = TbsShareManager.e(context);
                        if (e2 == null && TbsShareManager.f1265c == null) {
                            TbsLogReport.getInstance(context.getApplicationContext()).setLoadErrorCode(TbsListener.ErrorCode.HOST_CONTEXT_IS_NULL, "host context is null!");
                            return;
                        } else if (!z2) {
                            newInstance = e2 == null ? q.getConstructor(Context.class, Context.class, String.class).newInstance(context, e2, TbsShareManager.f1265c, str, null) : q.getConstructor(Context.class, Context.class).newInstance(context, e2);
                        } else {
                            newInstance = constructor.newInstance(context, e2, TbsShareManager.f1265c, str, null);
                        }
                    } else if (!z2) {
                        Constructor<?> constructor2 = q.getConstructor(Context.class, Context.class);
                        if (context.getApplicationContext() != null) {
                            context = context.getApplicationContext();
                        }
                        newInstance = constructor2.newInstance(context, context);
                    } else {
                        String str2 = (!TbsConfig.APP_WX.equals(getCurrentProcessName(context)) || WebView.mWebViewCreated) ? null : "notLoadSo";
                        if (context.getApplicationContext() != null) {
                            context = context.getApplicationContext();
                        }
                        newInstance = constructor.newInstance(context, context, null, str, str2);
                    }
                    r = newInstance;
                }
            }
        }
    }

    public static void openBrowserList(Context context, String str, Bundle bundle, final ValueCallback<String> valueCallback) {
        String str2;
        if (context != null) {
            String string = bundle != null ? bundle.getString("ChannelId") : BuildConfig.FLAVOR;
            Intent intent = new Intent("android.intent.action.VIEW");
            intent.addCategory("android.intent.category.DEFAULT");
            intent.setData(Uri.parse(str));
            String d2 = e.d(str);
            isDefaultDialog = false;
            d dVar = new d(context, "选择其它应用打开", intent, bundle, valueCallback, d2, string);
            String a2 = dVar.a();
            if (a2 != null && !TextUtils.isEmpty(a2)) {
                if (TbsConfig.APP_QB.equals(a2)) {
                    intent.putExtra("ChannelID", context.getApplicationContext().getPackageName());
                    intent.putExtra("PosID", "4");
                }
                intent.setPackage(a2);
                intent.putExtra("big_brother_source_key", string);
                context.startActivity(intent);
                if (valueCallback != null) {
                    str2 = "default browser:" + a2;
                } else {
                    return;
                }
            } else if (isDefaultDialog) {
                new AlertDialog.Builder(context).setTitle("提示").setMessage("此文件无法打开").setPositiveButton("确定", new DialogInterface.OnClickListener() { // from class: com.tencent.smtt.sdk.QbSdk.10
                    @Override // android.content.DialogInterface.OnClickListener
                    public void onClick(DialogInterface dialogInterface, int i2) {
                    }
                }).show();
                if (valueCallback != null) {
                    str2 = "can not open";
                } else {
                    return;
                }
            } else {
                dVar.show();
                dVar.setOnDismissListener(new DialogInterface.OnDismissListener() { // from class: com.tencent.smtt.sdk.QbSdk.2
                    @Override // android.content.DialogInterface.OnDismissListener
                    public void onDismiss(DialogInterface dialogInterface) {
                        ValueCallback valueCallback2 = valueCallback;
                        if (valueCallback2 != null) {
                            valueCallback2.onReceiveValue("TbsReaderDialogClosed");
                        }
                    }
                });
                return;
            }
            valueCallback.onReceiveValue(str2);
        }
    }

    public static void openBrowserList(Context context, String str, ValueCallback<String> valueCallback) {
        openBrowserList(context, str, null, valueCallback);
    }

    public static int openFileReader(Context context, String str, HashMap<String, String> hashMap, ValueCallback<String> valueCallback) {
        TbsCoreLoadStat.getInstance().a(context, TbsListener.ErrorCode.INFO_CODE_FILEREADER_OPENFILEREADER_COUNTS);
        if (!checkContentProviderPrivilage(context)) {
            return -5;
        }
        if (str != null) {
            String substring = str.substring(str.lastIndexOf(".") + 1, str.length());
            if (substring != null) {
                substring = substring.toLowerCase();
            }
            if ("apk".equalsIgnoreCase(substring)) {
                Intent intent = new Intent("android.intent.action.VIEW");
                if (context != null && context.getApplicationInfo().targetSdkVersion >= 24 && Build.VERSION.SDK_INT >= 24) {
                    intent.addFlags(1);
                }
                Uri a2 = FileProvider.a(context, str);
                if (a2 == null) {
                    valueCallback.onReceiveValue("uri failed");
                    return -6;
                }
                intent.setDataAndType(a2, "application/vnd.android.package-archive");
                context.startActivity(intent);
                TbsCoreLoadStat.getInstance().a(context, TbsListener.ErrorCode.INFO_CODE_FILEREADER_OPENFILEREADER_APKFILE);
                return 4;
            }
            if (MttLoader.isBrowserInstalled(context)) {
                if (!a(context, str, substring)) {
                    openFileReaderListWithQBDownload(context, str, null, valueCallback);
                    TbsCoreLoadStat.getInstance().a(context, TbsListener.ErrorCode.INFO_CODE_FILEREADER_OPENFILEREADER_NOTSUPPORT);
                    return 3;
                } else if (startQBForDoc(context, str, 4, 0, substring, a(context, hashMap))) {
                    if (valueCallback != null) {
                        valueCallback.onReceiveValue("open QB");
                    }
                    TbsCoreLoadStat.getInstance().a(context, TbsListener.ErrorCode.INFO_CODE_FILEREADER_OPENFILEREADER_NOTSUPPORT);
                    return 1;
                }
            }
            if (hashMap == null) {
                hashMap = new HashMap<>();
            }
            hashMap.put("local", "true");
            TbsLog.setWriteLogJIT(true);
            int startMiniQBToLoadUrl = startMiniQBToLoadUrl(context, str, hashMap, valueCallback);
            if (startMiniQBToLoadUrl != 0) {
                openFileReaderListWithQBDownload(context, str, null, valueCallback);
                TbsLogReport instance = TbsLogReport.getInstance(context);
                instance.setLoadErrorCode(TbsListener.ErrorCode.INFO_CODE_FILEREADER_OPENFILEREADER_MINIQBFAILED, BuildConfig.FLAVOR + startMiniQBToLoadUrl);
                return 3;
            }
            TbsCoreLoadStat.getInstance().a(context, TbsListener.ErrorCode.INFO_CODE_FILEREADER_OPENFILEREADER_MINIQBSUCCESS);
            return 2;
        }
        if (valueCallback != null) {
            valueCallback.onReceiveValue("filepath error");
        }
        TbsCoreLoadStat.getInstance().a(context, TbsListener.ErrorCode.INFO_CODE_FILEREADER_OPENFILEREADER_FILEPATHISNULL);
        return -1;
    }

    public static void openFileReaderListWithQBDownload(Context context, String str, Bundle bundle, final ValueCallback<String> valueCallback) {
        String str2;
        if (context != null && !context.getApplicationInfo().packageName.equals("com.tencent.qim") && !context.getApplicationInfo().packageName.equals("com.tencent.tim") && !context.getApplicationInfo().packageName.equals("com.tencent.androidqqmail")) {
            String string = bundle != null ? bundle.getString("ChannelId") : BuildConfig.FLAVOR;
            Intent intent = new Intent("android.intent.action.VIEW");
            intent.addCategory("android.intent.category.DEFAULT");
            String d2 = e.d(str);
            if (context.getApplicationInfo().targetSdkVersion >= 24 && Build.VERSION.SDK_INT >= 24) {
                intent.addFlags(1);
            }
            Uri a2 = FileProvider.a(context, str);
            if (a2 == null) {
                TbsLog.i("QbSdk", "openFileReaderListWithQBDownload,uri failed");
                valueCallback.onReceiveValue("uri failed");
                return;
            }
            TbsLog.i("QbSdk", "openFileReaderListWithQBDownload,fileUri:" + str + ",mimeType:" + d2);
            intent.setDataAndType(a2, d2);
            isDefaultDialog = false;
            d dVar = new d(context, "选择其它应用打开", intent, bundle, valueCallback, d2, string);
            String a3 = dVar.a();
            TbsLog.i("QbSdk", "openFileReaderListWithQBDownload,defaultBrowser:" + a3);
            if (a3 != null && !TextUtils.isEmpty(a3) && a3.startsWith("extraMenuEvent:")) {
                TbsLog.i("QbSdk", "openFileReaderListWithQBDownload, is default extra menu action");
                valueCallback.onReceiveValue(a3);
            } else if (a3 != null && !TextUtils.isEmpty(a3) && checkApkExist(context, a3)) {
                TbsLog.i("QbSdk", "openFileReaderListWithQBDownload, is default normal menu action");
                if (TbsConfig.APP_QB.equals(a3)) {
                    intent.putExtra("ChannelID", context.getApplicationContext().getPackageName());
                    intent.putExtra("PosID", "4");
                }
                if (!TextUtils.isEmpty(string)) {
                    intent.putExtra("big_brother_source_key", string);
                }
                intent.setPackage(a3);
                context.startActivity(intent);
                if (valueCallback != null) {
                    valueCallback.onReceiveValue("default browser:" + a3);
                }
            } else if (!"com.tencent.rtxlite".equalsIgnoreCase(context.getApplicationContext().getPackageName()) || !isDefaultDialog) {
                if (isDefaultDialog) {
                    TbsLog.i("QbSdk", "isDefaultDialog=true");
                    if (valueCallback != null) {
                        TbsLog.i("QbSdk", "isDefaultDialog=true, can not open");
                        str2 = "can not open";
                        valueCallback.onReceiveValue(str2);
                    }
                } else {
                    try {
                        TbsLog.i("QbSdk", "isDefaultDialog=false,try to open dialog");
                        dVar.show();
                        dVar.setOnDismissListener(new DialogInterface.OnDismissListener() { // from class: com.tencent.smtt.sdk.QbSdk.9
                            @Override // android.content.DialogInterface.OnDismissListener
                            public void onDismiss(DialogInterface dialogInterface) {
                                ValueCallback valueCallback2 = valueCallback;
                                if (valueCallback2 != null) {
                                    valueCallback2.onReceiveValue("TbsReaderDialogClosed");
                                }
                            }
                        });
                    } catch (Exception e2) {
                        e2.printStackTrace();
                        TbsLog.i("QbSdk", "isDefaultDialog=false,try to open dialog, but failed");
                        str2 = "TbsReaderDialogClosed";
                    }
                }
                TbsLog.i("QbSdk", "unexpected return, dialogBuilder not show!");
            } else {
                new AlertDialog.Builder(context).setTitle("提示").setMessage("此文件无法打开").setPositiveButton("确定", new DialogInterface.OnClickListener() { // from class: com.tencent.smtt.sdk.QbSdk.8
                    @Override // android.content.DialogInterface.OnClickListener
                    public void onClick(DialogInterface dialogInterface, int i2) {
                    }
                }).show();
            }
        }
    }

    public static void openFileReaderListWithQBDownload(Context context, String str, ValueCallback<String> valueCallback) {
        openFileReaderListWithQBDownload(context, str, null, valueCallback);
    }

    public static int openFileWithQB(Context context, String str, String str2) {
        TbsLog.i("QbSdk", "open openFileReader startMiniQBToLoadUrl filepath = " + str);
        if (!checkContentProviderPrivilage(context)) {
            return -1;
        }
        if (str != null) {
            String lowerCase = str.substring(str.lastIndexOf(".") + 1).toLowerCase();
            if (!MttLoader.isBrowserInstalled(context)) {
                TbsLog.i("QbSdk", "openFileReader QQ browser not installed");
                return -4;
            } else if (!a(context, str, lowerCase)) {
                TbsLog.i("QbSdk", "openFileReader open in QB isQBSupport: false");
                return -2;
            } else {
                HashMap hashMap = new HashMap();
                hashMap.put("ChannelID", context.getApplicationContext().getApplicationInfo().processName);
                hashMap.put("PosID", Integer.toString(4));
                if (MttLoader.openDocWithQb(context, str, 0, lowerCase, str2, hashMap, null)) {
                    return 0;
                }
                TbsLog.i("QbSdk", "openFileReader startQBForDoc return false");
                return -3;
            }
        } else {
            TbsLog.i("QbSdk", "open openFileReader filepath error");
            return -5;
        }
    }

    public static void openNetLog(String str) {
        u a2 = u.a();
        if (a2 != null && a2.b()) {
            try {
                a2.c().b().invokeStaticMethod("com.tencent.smtt.livelog.NetLogManager", "openNetLog", new Class[]{String.class}, str);
            } catch (Exception unused) {
            }
        }
    }

    public static synchronized void preInit(Context context) {
        synchronized (QbSdk.class) {
            preInit(context, null);
        }
    }

    public static synchronized void preInit(final Context context, final PreInitCallback preInitCallback) {
        synchronized (QbSdk.class) {
            TbsLog.initIfNeed(context);
            TbsLog.i("QbSdk", "preInit -- processName: " + getCurrentProcessName(context));
            TbsLog.i("QbSdk", "preInit -- stack: " + Log.getStackTraceString(new Throwable("#")));
            l = f1127a;
            if (!s) {
                final Handler handler = new Handler(Looper.getMainLooper()) { // from class: com.tencent.smtt.sdk.QbSdk.3
                    /* JADX WARN: Code restructure failed: missing block: B:22:0x0043, code lost:
                        if (r4 != null) goto L_0x0019;
                     */
                    @Override // android.os.Handler
                    /*
                        Code decompiled incorrectly, please refer to instructions dump.
                        To view partially-correct code enable 'Show inconsistent code' option in preferences
                    */
                    public void handleMessage(android.os.Message r4) {
                        /*
                            r3 = this;
                            int r4 = r4.what
                            r0 = 1
                            if (r4 == r0) goto L_0x0020
                            r0 = 2
                            if (r4 == r0) goto L_0x0014
                            r0 = 3
                            if (r4 == r0) goto L_0x000c
                            goto L_0x0046
                        L_0x000c:
                            com.tencent.smtt.sdk.QbSdk$PreInitCallback r4 = r2
                            if (r4 == 0) goto L_0x0046
                            r4.onCoreInitFinished()
                            goto L_0x0046
                        L_0x0014:
                            com.tencent.smtt.sdk.QbSdk$PreInitCallback r4 = r2
                            if (r4 == 0) goto L_0x001c
                            r0 = 0
                        L_0x0019:
                            r4.onViewInitFinished(r0)
                        L_0x001c:
                            com.tencent.smtt.utils.TbsLog.writeLogToDisk()
                            goto L_0x0046
                        L_0x0020:
                            com.tencent.smtt.sdk.TbsExtensionFunctionManager r4 = com.tencent.smtt.sdk.TbsExtensionFunctionManager.getInstance()
                            android.content.Context r1 = r3
                            java.lang.String r2 = "disable_unpreinit.txt"
                            boolean r4 = r4.canUseFunction(r1, r2)
                            com.tencent.smtt.sdk.QbSdk.B = r4
                            boolean r4 = com.tencent.smtt.sdk.QbSdk.j
                            if (r4 == 0) goto L_0x0041
                            com.tencent.smtt.sdk.u r4 = com.tencent.smtt.sdk.u.a()
                            com.tencent.smtt.sdk.v r4 = r4.c()
                            if (r4 == 0) goto L_0x0041
                            android.content.Context r1 = r3
                            r4.a(r1)
                        L_0x0041:
                            com.tencent.smtt.sdk.QbSdk$PreInitCallback r4 = r2
                            if (r4 == 0) goto L_0x001c
                            goto L_0x0019
                        L_0x0046:
                            return
                        */
                        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.sdk.QbSdk.AnonymousClass3.handleMessage(android.os.Message):void");
                    }
                };
                Thread thread = new Thread() { // from class: com.tencent.smtt.sdk.QbSdk.4
                    @Override // java.lang.Thread, java.lang.Runnable
                    public void run() {
                        int a2 = m.a().a(true, context);
                        TbsDownloader.setAppContext(context);
                        TbsLog.i("QbSdk", "QbSdk preinit ver is " + a2);
                        if (a2 == 0) {
                            m.a().b(context, true);
                        }
                        TbsLog.i("QbSdk", "preInit -- prepare initAndLoadSo");
                        d.a(true).a(context, false, false);
                        u a3 = u.a();
                        a3.a(context);
                        boolean b2 = a3.b();
                        handler.sendEmptyMessage(3);
                        if (!b2) {
                            handler.sendEmptyMessage(2);
                        } else {
                            handler.sendEmptyMessage(1);
                        }
                    }
                };
                thread.setName("tbs_preinit");
                thread.setPriority(10);
                thread.start();
                s = true;
            }
        }
    }

    public static void reset(Context context) {
        reset(context, false);
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x0059 A[Catch: Throwable -> 0x0079, TryCatch #0 {Throwable -> 0x0079, blocks: (B:3:0x0008, B:5:0x000e, B:7:0x0014, B:12:0x002e, B:14:0x0059, B:16:0x005e), top: B:20:0x0008 }] */
    /* JADX WARN: Removed duplicated region for block: B:16:0x005e A[Catch: Throwable -> 0x0079, TRY_LEAVE, TryCatch #0 {Throwable -> 0x0079, blocks: (B:3:0x0008, B:5:0x000e, B:7:0x0014, B:12:0x002e, B:14:0x0059, B:16:0x005e), top: B:20:0x0008 }] */
    /* JADX WARN: Removed duplicated region for block: B:22:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static void reset(android.content.Context r5, boolean r6) {
        /*
            r0 = 1
            java.lang.String r1 = "QbSdk"
            java.lang.String r2 = "QbSdk reset!"
            com.tencent.smtt.utils.TbsLog.e(r1, r2, r0)
            com.tencent.smtt.sdk.TbsDownloader.stopDownload()     // Catch: Throwable -> 0x0079
            r2 = 0
            if (r6 == 0) goto L_0x002d
            boolean r6 = com.tencent.smtt.sdk.TbsShareManager.isThirdPartyApp(r5)     // Catch: Throwable -> 0x0079
            if (r6 != 0) goto L_0x002d
            com.tencent.smtt.sdk.m r6 = com.tencent.smtt.sdk.m.a()     // Catch: Throwable -> 0x0079
            int r6 = r6.h(r5)     // Catch: Throwable -> 0x0079
            com.tencent.smtt.sdk.m r3 = com.tencent.smtt.sdk.m.a()     // Catch: Throwable -> 0x0079
            int r3 = r3.i(r5)     // Catch: Throwable -> 0x0079
            r4 = 43300(0xa924, float:6.0676E-41)
            if (r6 <= r4) goto L_0x002d
            if (r6 == r3) goto L_0x002d
            r6 = 1
            goto L_0x002e
        L_0x002d:
            r6 = 0
        L_0x002e:
            com.tencent.smtt.sdk.TbsDownloader.c(r5)     // Catch: Throwable -> 0x0079
            java.io.File r3 = getTbsFolderDir(r5)     // Catch: Throwable -> 0x0079
            java.lang.String r4 = "core_share_decouple"
            com.tencent.smtt.utils.FileUtil.a(r3, r2, r4)     // Catch: Throwable -> 0x0079
            java.lang.String r3 = "delete downloaded apk success"
            com.tencent.smtt.utils.TbsLog.i(r1, r3, r0)     // Catch: Throwable -> 0x0079
            java.lang.ThreadLocal<java.lang.Integer> r0 = com.tencent.smtt.sdk.m.f1371a     // Catch: Throwable -> 0x0079
            java.lang.Integer r3 = java.lang.Integer.valueOf(r2)     // Catch: Throwable -> 0x0079
            r0.set(r3)     // Catch: Throwable -> 0x0079
            java.io.File r0 = new java.io.File     // Catch: Throwable -> 0x0079
            java.io.File r3 = r5.getFilesDir()     // Catch: Throwable -> 0x0079
            java.lang.String r4 = "bugly_switch.txt"
            r0.<init>(r3, r4)     // Catch: Throwable -> 0x0079
            boolean r3 = r0.exists()     // Catch: Throwable -> 0x0079
            if (r3 == 0) goto L_0x005c
            r0.delete()     // Catch: Throwable -> 0x0079
        L_0x005c:
            if (r6 == 0) goto L_0x008e
            com.tencent.smtt.sdk.m r6 = com.tencent.smtt.sdk.m.a()     // Catch: Throwable -> 0x0079
            java.io.File r6 = r6.p(r5)     // Catch: Throwable -> 0x0079
            com.tencent.smtt.sdk.m r0 = com.tencent.smtt.sdk.m.a()     // Catch: Throwable -> 0x0079
            java.io.File r0 = r0.f(r5, r2)     // Catch: Throwable -> 0x0079
            com.tencent.smtt.utils.FileUtil.b(r6, r0)     // Catch: Throwable -> 0x0079
            com.tencent.smtt.sdk.m r6 = com.tencent.smtt.sdk.m.a()     // Catch: Throwable -> 0x0079
            r6.b(r5)     // Catch: Throwable -> 0x0079
            goto L_0x008e
        L_0x0079:
            r5 = move-exception
            java.lang.String r6 = "QbSdk reset exception:"
            java.lang.StringBuilder r6 = e.a.a.a.a.a(r6)
            java.lang.String r5 = android.util.Log.getStackTraceString(r5)
            r6.append(r5)
            java.lang.String r5 = r6.toString()
            com.tencent.smtt.utils.TbsLog.e(r1, r5)
        L_0x008e:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.sdk.QbSdk.reset(android.content.Context, boolean):void");
    }

    public static void resetDecoupleCore(Context context) {
        TbsLog.e("QbSdk", "QbSdk resetDecoupleCore!", true);
        try {
            FileUtil.a(m.a().p(context), false);
        } catch (Throwable th) {
            StringBuilder a2 = a.a("QbSdk resetDecoupleCore exception:");
            a2.append(Log.getStackTraceString(th));
            TbsLog.e("QbSdk", a2.toString());
        }
    }

    public static void setCurrentID(String str) {
        if (str != null && str.startsWith(TID_QQNumber_Prefix)) {
            String substring = str.substring(3);
            z = "0000000000000000".substring(substring.length()) + substring;
        }
    }

    public static void setDisableUnpreinitBySwitch(boolean z2) {
        B = z2;
        StringBuilder a2 = a.a("setDisableUnpreinitBySwitch -- mDisableUnpreinitBySwitch is ");
        a2.append(B);
        TbsLog.i("QbSdk", a2.toString());
    }

    public static void setDisableUseHostBackupCoreBySwitch(boolean z2) {
        mDisableUseHostBackupCore = z2;
        StringBuilder a2 = a.a("setDisableUseHostBackupCoreBySwitch -- mDisableUseHostBackupCore is ");
        a2.append(mDisableUseHostBackupCore);
        TbsLog.i("QbSdk", a2.toString());
    }

    public static void setDownloadWithoutWifi(boolean z2) {
        F = z2;
    }

    public static void setNeedInitX5FirstTime(boolean z2) {
        w = z2;
    }

    public static void setNetLogEncryptionKey(String str) {
        u a2 = u.a();
        if (a2 != null && a2.b()) {
            try {
                a2.c().b().invokeStaticMethod("com.tencent.smtt.livelog.NetLogManager", "setNetLogEncryptionKey", new Class[]{String.class}, str);
            } catch (Exception unused) {
            }
        }
    }

    public static void setOnlyDownload(boolean z2) {
        k = z2;
    }

    public static void setQQBuildNumber(String str) {
        A = str;
    }

    public static void setTBSInstallingStatus(boolean z2) {
        G = z2;
    }

    public static void setTbsListener(TbsListener tbsListener) {
        D = tbsListener;
    }

    public static void setTbsLogClient(TbsLogClient tbsLogClient) {
        TbsLog.setTbsLogClient(tbsLogClient);
    }

    public static void setUploadCode(Context context, int i2) {
        TbsDownloadUpload instance;
        Map<String, Object> map;
        Integer valueOf;
        String str;
        if (i2 >= 130 && i2 <= 139) {
            instance = TbsDownloadUpload.getInstance(context);
            map = instance.f1214a;
            valueOf = Integer.valueOf(i2);
            str = TbsDownloadUpload.TbsUploadKey.KEY_NEEDDOWNLOAD_CODE;
        } else if (i2 >= 150 && i2 <= 159) {
            instance = TbsDownloadUpload.getInstance(context);
            map = instance.f1214a;
            valueOf = Integer.valueOf(i2);
            str = TbsDownloadUpload.TbsUploadKey.KEY_STARTDOWNLOAD_CODE;
        } else {
            return;
        }
        map.put(str, valueOf);
        instance.commit();
    }

    public static int startMiniQBToLoadUrl(Context context, String str, HashMap<String, String> hashMap, ValueCallback<String> valueCallback) {
        TbsCoreLoadStat.getInstance().a(context, TbsListener.ErrorCode.INFO_CODE_MINIQB_STARTMINIQBTOLOADURL_COUNTS);
        if (context == null) {
            return -100;
        }
        u a2 = u.a();
        a2.a(context);
        if (!a2.b()) {
            TbsCoreLoadStat.getInstance().a(context, TbsListener.ErrorCode.INFO_CODE_MINIQB_STARTMINIQBTOLOADURL_ISNOTX5CORE);
            return -102;
        } else if (context.getApplicationInfo().packageName.equals("com.nd.android.pandahome2") && getTbsVersion(context) < 25487) {
            return -101;
        } else {
            int a3 = a2.c().a(context, str, hashMap, null, valueCallback);
            if (a3 == 0) {
                TbsCoreLoadStat.getInstance().a(context, TbsListener.ErrorCode.INFO_CODE_MINIQB_STARTMINIQBTOLOADURL_SUCCESS);
            } else {
                TbsLogReport.getInstance(context).setLoadErrorCode(TbsListener.ErrorCode.INFO_CODE_MINIQB_STARTMINIQBTOLOADURL_FAILED, BuildConfig.FLAVOR + a3);
            }
            String str2 = "startMiniQBToLoadUrl  ret = " + a3;
            return a3;
        }
    }

    public static boolean startQBForDoc(Context context, String str, int i2, int i3, String str2, Bundle bundle) {
        HashMap hashMap = new HashMap();
        hashMap.put("ChannelID", context.getApplicationContext().getApplicationInfo().processName);
        hashMap.put("PosID", Integer.toString(i2));
        return MttLoader.openDocWithQb(context, str, i3, str2, hashMap, bundle);
    }

    public static boolean startQBForVideo(Context context, String str, int i2) {
        HashMap hashMap = new HashMap();
        hashMap.put("ChannelID", context.getApplicationInfo().processName);
        hashMap.put("PosID", Integer.toString(i2));
        return MttLoader.openVideoWithQb(context, str, hashMap);
    }

    public static boolean startQBToLoadurl(Context context, String str, int i2, WebView webView) {
        u a2;
        Object invokeStaticMethod;
        HashMap hashMap = new HashMap();
        hashMap.put("ChannelID", context.getApplicationInfo().processName);
        hashMap.put("PosID", Integer.toString(i2));
        if (webView == null) {
            try {
                String str2 = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).packageName;
                if ((str2 == TbsConfig.APP_WX || str2 == TbsConfig.APP_QQ) && (a2 = u.a()) != null && a2.b() && (invokeStaticMethod = a2.c().b().invokeStaticMethod("com.tencent.smtt.webkit.WebViewList", "getCurrentMainWebviewJustForQQandWechat", new Class[0], new Object[0])) != null) {
                    webView = (WebView) ((IX5WebViewBase) invokeStaticMethod).getView().getParent();
                }
            } catch (Exception unused) {
            }
        }
        return MttLoader.loadUrl(context, str, hashMap, "QbSdk.startQBToLoadurl", webView) == 0;
    }

    public static boolean startQBWithBrowserlist(Context context, String str, int i2) {
        boolean startQBToLoadurl = startQBToLoadurl(context, str, i2, null);
        if (!startQBToLoadurl) {
            openBrowserList(context, str, null, null);
        }
        return startQBToLoadurl;
    }

    public static boolean startQbOrMiniQBToLoadUrl(Context context, String str, HashMap<String, String> hashMap, ValueCallback<String> valueCallback) {
        if (context == null) {
            return false;
        }
        u a2 = u.a();
        a2.a(context);
        if (hashMap != null && "5".equals(hashMap.get("PosID")) && a2.b()) {
            Bundle bundle = (Bundle) a2.c().b().invokeStaticMethod("com.tencent.tbs.tbsshell.WebCoreProxy", "getAdWebViewInfoFromX5Core", new Class[0], new Object[0]);
        }
        if (MttLoader.loadUrl(context, str, hashMap, "QbSdk.startMiniQBToLoadUrl", null) != 0) {
            return a2.b() && (!context.getApplicationInfo().packageName.equals("com.nd.android.pandahome2") || getTbsVersion(context) >= 25487) && a2.c().a(context, str, hashMap, null, valueCallback) == 0;
        }
        return true;
    }

    public static void unForceSysWebView() {
        f1128b = false;
        TbsLog.e("QbSdk", "sys WebView: unForceSysWebView called");
    }

    public static synchronized boolean unPreInit(Context context) {
        synchronized (QbSdk.class) {
        }
        return true;
    }

    public static void uploadNetLog(String str) {
        u a2 = u.a();
        if (a2 != null && a2.b()) {
            try {
                a2.c().b().invokeStaticMethod("com.tencent.smtt.livelog.NetLogManager", "uploadNetLog", new Class[]{String.class}, str);
            } catch (Exception unused) {
            }
        }
    }

    public static boolean useSoftWare() {
        Object obj = r;
        if (obj == null) {
            return false;
        }
        Object a2 = i.a(obj, "useSoftWare", new Class[0], new Object[0]);
        if (a2 == null) {
            a2 = i.a(r, "useSoftWare", new Class[]{Integer.TYPE}, Integer.valueOf(a.a()));
        }
        if (a2 == null) {
            return false;
        }
        return ((Boolean) a2).booleanValue();
    }

    @SuppressLint({"NewApi"})
    public static boolean a(Context context, boolean z2) {
        int i2;
        File file;
        Exception exc;
        int i3;
        TbsCoreLoadStat tbsCoreLoadStat;
        TbsLog.initIfNeed(context);
        if (!sIsVersionPrinted) {
            TbsLog.i("QbSdk", "svn revision: jnizz; SDK_VERSION_CODE: 43939; SDK_VERSION_NAME: 4.3.0.39");
            sIsVersionPrinted = true;
        }
        if (f1127a && !z2) {
            StringBuilder a2 = a.a("QbSdk init: ");
            a2.append(v);
            TbsLog.e("QbSdk", a2.toString(), false);
            TbsCoreLoadStat.getInstance().a(context, TbsListener.ErrorCode.INFO_SDKINIT_IS_SYS_FORCED, new Throwable(v));
            return false;
        } else if (f1128b) {
            TbsLog.e("QbSdk", "QbSdk init mIsSysWebViewForcedByOuter = true", true);
            TbsCoreLoadStat.getInstance().a(context, TbsListener.ErrorCode.INFO_FORCE_SYSTEM_WEBVIEW_OUTER, new Throwable(u));
            return false;
        } else {
            if (!C) {
                d(context);
            }
            try {
                File q2 = m.a().q(context);
                if (q2 == null) {
                    TbsLog.e("QbSdk", "QbSdk init (false) optDir == null");
                    TbsCoreLoadStat.getInstance().a(context, TbsListener.ErrorCode.ERROR_TBSCORE_SHARE_DIR, new Throwable("QbSdk.init (false) TbsCoreShareDir is null"));
                    return false;
                }
                if (TbsShareManager.isThirdPartyApp(context)) {
                    if (o == 0 || o == TbsShareManager.d(context)) {
                        i2 = TbsShareManager.d(context);
                    } else {
                        q = null;
                        r = null;
                        TbsLog.e("QbSdk", "QbSdk init (false) ERROR_UNMATCH_TBSCORE_VER_THIRDPARTY!");
                        TbsCoreLoadStat instance = TbsCoreLoadStat.getInstance();
                        instance.a(context, TbsListener.ErrorCode.ERROR_UNMATCH_TBSCORE_VER_THIRDPARTY, new Throwable("sTbsVersion: " + o + "; AvailableTbsCoreVersion: " + TbsShareManager.d(context)));
                        return false;
                    }
                } else if (o != 0) {
                    i2 = m.a().a(true, context);
                    if (o != i2) {
                        q = null;
                        r = null;
                        TbsLog.e("QbSdk", "QbSdk init (false) not isThirdPartyApp tbsCoreInstalledVer=" + i2, true);
                        TbsLog.e("QbSdk", "QbSdk init (false) not isThirdPartyApp sTbsVersion=" + o, true);
                        TbsCoreLoadStat instance2 = TbsCoreLoadStat.getInstance();
                        instance2.a(context, TbsListener.ErrorCode.ERROR_UNMATCH_TBSCORE_VER, new Throwable("sTbsVersion: " + o + "; tbsCoreInstalledVer: " + i2));
                        return false;
                    }
                } else {
                    i2 = 0;
                }
                o = i2;
                if (TbsDownloader.a(context, o)) {
                    TbsLog.i("QbSdk", "version " + o + " is in blacklist,can not load! return");
                    return false;
                } else if (q != null && r != null) {
                    return true;
                } else {
                    if (!TbsShareManager.isThirdPartyApp(context)) {
                        file = new File(m.a().q(context), "tbs_sdk_extension_dex.jar");
                    } else if (TbsShareManager.j(context)) {
                        TbsShareManager.j(context);
                        file = new File(TbsShareManager.f1266d, "tbs_sdk_extension_dex.jar");
                    } else {
                        TbsCoreLoadStat.getInstance().a(context, TbsListener.ErrorCode.ERROR_HOST_UNAVAILABLE, new Throwable("isShareTbsCoreAvailable false!"));
                        return false;
                    }
                    if (!file.exists()) {
                        TbsLog.e("QbSdk", "QbSdk init (false) tbs_sdk_extension_dex.jar is not exist!");
                        int i4 = m.a().i(context);
                        if (new File(file.getParentFile(), DexClassLoaderProvider.LAST_DEX_NAME).exists()) {
                            if (i4 > 0) {
                                tbsCoreLoadStat = TbsCoreLoadStat.getInstance();
                                i3 = TbsListener.ErrorCode.INFO_MISS_SDKEXTENSION_JAR_WITH_FUSION_DEX_WITH_CORE;
                                exc = new Exception("tbs_sdk_extension_dex not exist(with fusion dex)!" + i4);
                            } else {
                                tbsCoreLoadStat = TbsCoreLoadStat.getInstance();
                                i3 = TbsListener.ErrorCode.INFO_MISS_SDKEXTENSION_JAR_WITH_FUSION_DEX_WITHOUT_CORE;
                                exc = new Exception("tbs_sdk_extension_dex not exist(with fusion dex)!" + i4);
                            }
                        } else if (i4 > 0) {
                            tbsCoreLoadStat = TbsCoreLoadStat.getInstance();
                            i3 = TbsListener.ErrorCode.INFO_INFO_MISS_SDKEXTENSION_JAR_WITHOUT_FUSION_DEX_WITH_CORE;
                            exc = new Exception("tbs_sdk_extension_dex not exist(without fusion dex)!" + i4);
                        } else {
                            tbsCoreLoadStat = TbsCoreLoadStat.getInstance();
                            i3 = TbsListener.ErrorCode.INFO_INFO_MISS_SDKEXTENSION_JAR_WITHOUT_FUSION_DEX_WITHOUT_CORE;
                            exc = new Exception("tbs_sdk_extension_dex not exist(without fusion dex)!" + i4);
                        }
                        tbsCoreLoadStat.a(context, i3, exc);
                        return false;
                    }
                    String absolutePath = TbsShareManager.f1265c != null ? TbsShareManager.f1265c : q2.getAbsolutePath();
                    TbsLog.i("QbSdk", "QbSdk init optDirExtension #1 is " + absolutePath);
                    TbsLog.i("QbSdk", "new DexLoader #1 dexFile is " + file.getAbsolutePath());
                    u.a().b(context);
                    l.a(context);
                    q = new DexLoader(file.getParent(), context, new String[]{file.getAbsolutePath()}, absolutePath, n).loadClass("com.tencent.tbs.sdk.extension.TbsSDKExtension");
                    loadTBSSDKExtension(context, file.getParent());
                    i.a(r, "setClientVersion", new Class[]{Integer.TYPE}, 1);
                    return true;
                }
            } catch (Throwable th) {
                StringBuilder a3 = a.a("QbSdk init Throwable: ");
                a3.append(Log.getStackTraceString(th));
                TbsLog.e("QbSdk", a3.toString());
                TbsCoreLoadStat.getInstance().a(context, TbsListener.ErrorCode.THROWABLE_QBSDK_INIT, th);
                return false;
            }
        }
    }

    public static boolean a(Context context, boolean z2, boolean z3) {
        int i2;
        TbsCoreLoadStat tbsCoreLoadStat;
        Throwable th;
        int i3;
        int disabledCoreVersion = TbsPVConfig.getInstance(context).getDisabledCoreVersion();
        boolean z4 = false;
        if (disabledCoreVersion != 0 && disabledCoreVersion == m.a().i(context)) {
            TbsLog.e("QbSdk", "force use sys by remote switch");
            return false;
        } else if (TbsShareManager.isThirdPartyApp(context) && !TbsShareManager.i(context)) {
            TbsCoreLoadStat.getInstance().a(context, TbsListener.ErrorCode.ERROR_UNMATCH_TBSCORE_VER_THIRDPARTY);
            return false;
        } else if (!a(context, z2)) {
            TbsLog.e("QbSdk", "QbSdk.init failure!");
            return false;
        } else {
            boolean z5 = true;
            Object a2 = i.a(r, "canLoadX5Core", new Class[]{Integer.TYPE}, 43939);
            if (a2 == null) {
                Object a3 = i.a(r, "canLoadX5", new Class[]{Integer.TYPE}, Integer.valueOf(a.a()));
                if (a3 == null) {
                    TbsCoreLoadStat.getInstance().a(context, TbsListener.ErrorCode.ERROR_CANLOADX5_RETURN_NULL);
                } else if ((a3 instanceof String) && ((String) a3).equalsIgnoreCase("AuthenticationFail")) {
                    return false;
                } else {
                    if (a3 instanceof Boolean) {
                        int i4 = d.h;
                        o = i4;
                        boolean a4 = a(context, i4);
                        Boolean bool = (Boolean) a3;
                        if (!bool.booleanValue() || a4) {
                            z5 = false;
                        }
                        if (!z5) {
                            TbsLog.e(TbsListener.tag_load_error, "318");
                            TbsLog.w(TbsListener.tag_load_error, "isX5Disable:" + a4);
                            TbsLog.w(TbsListener.tag_load_error, "(Boolean) ret:" + bool);
                        }
                        return z5;
                    }
                }
            } else if ((a2 instanceof String) && ((String) a2).equalsIgnoreCase("AuthenticationFail")) {
                return false;
            } else {
                if (!(a2 instanceof Bundle)) {
                    TbsCoreLoadStat.getInstance().a(context, TbsListener.ErrorCode.ERROR_QBSDK_INIT_ERROR_RET_TYPE_NOT_BUNDLE, new Throwable(a.a(BuildConfig.FLAVOR, a2)));
                    TbsLog.e(TbsListener.tag_load_error, "ret not instance of bundle");
                    return false;
                }
                Bundle bundle = (Bundle) a2;
                if (bundle.isEmpty()) {
                    TbsCoreLoadStat.getInstance().a(context, TbsListener.ErrorCode.ERROR_QBSDK_INIT_ERROR_EMPTY_BUNDLE, new Throwable(a.a(BuildConfig.FLAVOR, a2)));
                    TbsLog.e(TbsListener.tag_load_error, "empty bundle");
                    return false;
                }
                try {
                    i2 = bundle.getInt("result_code", -1);
                } catch (Exception e2) {
                    StringBuilder a5 = a.a("bundle.getInt(KEY_RESULT_CODE) error : ");
                    a5.append(e2.toString());
                    TbsLog.e("QbSdk", a5.toString());
                    i2 = -1;
                }
                z4 = i2 == 0;
                if (TbsShareManager.isThirdPartyApp(context)) {
                    d.h = TbsShareManager.d(context);
                    p = String.valueOf(TbsShareManager.d(context));
                    if (p.length() == 5) {
                        StringBuilder a6 = a.a("0");
                        a6.append(p);
                        p = a6.toString();
                    }
                    if (p.length() != 6) {
                        p = BuildConfig.FLAVOR;
                    }
                } else {
                    try {
                        int i5 = Build.VERSION.SDK_INT;
                        p = bundle.getString("tbs_core_version", "0");
                    } catch (Exception unused) {
                        p = "0";
                    }
                    try {
                        o = Integer.parseInt(p);
                    } catch (NumberFormatException unused2) {
                        o = 0;
                    }
                    d.h = o;
                    int i6 = o;
                    if (i6 == 0) {
                        TbsCoreLoadStat.getInstance().a(context, TbsListener.ErrorCode.ERROR_CANLOADX5_RETURN_FALSE, new Throwable("sTbsVersion is 0"));
                        return false;
                    }
                    if ((i6 <= 0 || i6 > 25442) && o != 25472) {
                        z5 = false;
                    }
                    if (z5) {
                        StringBuilder a7 = a.a("is_obsolete --> delete old core:");
                        a7.append(o);
                        TbsLog.e(TbsDownloader.LOGTAG, a7.toString());
                        FileUtil.a(m.a().q(context), false);
                        TbsCoreLoadStat instance = TbsCoreLoadStat.getInstance();
                        StringBuilder a8 = a.a("is_obsolete --> delete old core:");
                        a8.append(o);
                        instance.a(context, TbsListener.ErrorCode.ERROR_CANLOADX5_RETURN_FALSE, new Throwable(a8.toString()));
                        return false;
                    }
                }
                try {
                    t = bundle.getStringArray("tbs_jarfiles");
                    if (!(t instanceof String[])) {
                        TbsCoreLoadStat instance2 = TbsCoreLoadStat.getInstance();
                        StringBuilder a9 = a.a("sJarFiles not instanceof String[]: ");
                        a9.append(t);
                        instance2.a(context, TbsListener.ErrorCode.ERROR_CANLOADX5_RETURN_FALSE, new Throwable(a9.toString()));
                        return false;
                    }
                    try {
                        f1130d = bundle.getString("tbs_librarypath");
                        Object obj = null;
                        if (i2 != 0) {
                            try {
                                obj = i.a(r, "getErrorCodeForLogReport", new Class[0], new Object[0]);
                            } catch (Exception e3) {
                                e3.printStackTrace();
                            }
                        }
                        if (i2 != -2) {
                            if (i2 != -1) {
                                if (i2 != 0) {
                                    TbsCoreLoadStat instance3 = TbsCoreLoadStat.getInstance();
                                    instance3.a(context, TbsListener.ErrorCode.INFO_INITX5_FALSE_DEFAULT, new Throwable("detail: " + obj + "errcode" + i2));
                                }
                            } else if (obj instanceof Integer) {
                                tbsCoreLoadStat = TbsCoreLoadStat.getInstance();
                                i3 = ((Integer) obj).intValue();
                                th = new Throwable(a.a("detail: ", obj));
                            } else {
                                TbsCoreLoadStat.getInstance().a(context, TbsListener.ErrorCode.ERROR_CANLOADX5_RETURN_FALSE, new Throwable(a.a("detail: ", obj)));
                            }
                        } else if (obj instanceof Integer) {
                            tbsCoreLoadStat = TbsCoreLoadStat.getInstance();
                            i3 = ((Integer) obj).intValue();
                            th = new Throwable(a.a("detail: ", obj));
                        } else {
                            tbsCoreLoadStat = TbsCoreLoadStat.getInstance();
                            i3 = TbsListener.ErrorCode.INFO_DISABLE_X5;
                            th = new Throwable(a.a("detail: ", obj));
                        }
                        tbsCoreLoadStat.a(context, i3, th);
                    } catch (Exception unused3) {
                        return false;
                    }
                } catch (Throwable th2) {
                    TbsCoreLoadStat.getInstance().a(context, TbsListener.ErrorCode.ERROR_GETSTRINGARRAY_JARFILE, th2);
                    return false;
                }
            }
            if (!z4) {
                TbsLog.e(TbsListener.tag_load_error, "319");
            }
            return z4;
        }
    }
}
