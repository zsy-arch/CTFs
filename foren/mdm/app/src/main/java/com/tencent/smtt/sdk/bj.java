package com.tencent.smtt.sdk;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.webkit.ValueCallback;
import com.tencent.smtt.export.external.DexLoader;
import com.tencent.smtt.export.external.extension.interfaces.IX5WebViewClientExtension;
import com.tencent.smtt.export.external.interfaces.IX5WebChromeClient;
import com.tencent.smtt.export.external.interfaces.IX5WebViewClient;
import com.tencent.smtt.export.external.interfaces.IconListener;
import com.tencent.smtt.utils.TbsLog;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.Map;

/* loaded from: classes2.dex */
public class bj {
    private DexLoader a;

    public bj(DexLoader dexLoader) {
        this.a = dexLoader;
    }

    public int a(Context context, String str, Map<String, String> map, String str2, ValueCallback<String> valueCallback) {
        if (TbsDownloader.getOverSea(context)) {
            return -1;
        }
        if (str2 == null) {
            Object invokeStaticMethod = this.a.invokeStaticMethod("com.tencent.tbs.tbsshell.WebCoreProxy", "startMiniQB", new Class[]{Context.class, String.class, Map.class, ValueCallback.class}, context, str, map, valueCallback);
            if (invokeStaticMethod == null) {
                invokeStaticMethod = this.a.invokeStaticMethod("com.tencent.tbs.tbsshell.WebCoreProxy", "startMiniQB", new Class[]{Context.class, String.class, Map.class}, context, str, map);
            }
            if (invokeStaticMethod == null) {
                invokeStaticMethod = this.a.invokeStaticMethod("com.tencent.tbs.tbsshell.WebCoreProxy", "startMiniQB", new Class[]{Context.class, String.class}, context, str);
            }
            if (invokeStaticMethod == null) {
                return -3;
            }
            return ((Integer) invokeStaticMethod).intValue();
        }
        Object invokeStaticMethod2 = this.a.invokeStaticMethod("com.tencent.tbs.tbsshell.WebCoreProxy", "startMiniQB", new Class[]{Context.class, String.class, String.class}, context, str, str2);
        if (invokeStaticMethod2 == null) {
            return -3;
        }
        return ((Integer) invokeStaticMethod2).intValue();
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x0056 A[ORIG_RETURN, RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:25:0x007c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public com.tencent.smtt.export.external.interfaces.IX5WebViewBase a(android.content.Context r11) {
        /*
            r10 = this;
            r8 = 1
            r4 = 0
            r7 = 0
            com.tencent.smtt.export.external.DexLoader r1 = r10.a
            java.lang.String r2 = "com.tencent.tbs.tbsshell.WebCoreProxy"
            java.lang.String r3 = "createSDKWebview"
            java.lang.Class[] r5 = new java.lang.Class[r8]
            java.lang.Class<android.content.Context> r6 = android.content.Context.class
            r5[r7] = r6
            java.lang.Object[] r6 = new java.lang.Object[r8]
            r6[r7] = r11
            java.lang.Object r3 = r1.invokeStaticMethod(r2, r3, r5, r6)
            if (r3 != 0) goto L_0x0057
            com.tencent.smtt.export.external.DexLoader r1 = r10.a     // Catch: Exception -> 0x0075
            java.lang.String r2 = "com.tencent.tbs.tbsshell.TBSShell"
            java.lang.String r5 = "getLoadFailureDetails"
            r6 = 0
            java.lang.Class[] r6 = new java.lang.Class[r6]     // Catch: Exception -> 0x0075
            r7 = 0
            java.lang.Object[] r7 = new java.lang.Object[r7]     // Catch: Exception -> 0x0075
            java.lang.Object r2 = r1.invokeStaticMethod(r2, r5, r6, r7)     // Catch: Exception -> 0x0075
            if (r2 == 0) goto L_0x003c
            boolean r1 = r2 instanceof java.lang.Throwable     // Catch: Exception -> 0x0075
            if (r1 == 0) goto L_0x003c
            com.tencent.smtt.sdk.TbsCoreLoadStat r5 = com.tencent.smtt.sdk.TbsCoreLoadStat.getInstance()     // Catch: Exception -> 0x0075
            r6 = 325(0x145, float:4.55E-43)
            r0 = r2
            java.lang.Throwable r0 = (java.lang.Throwable) r0     // Catch: Exception -> 0x0075
            r1 = r0
            r5.a(r11, r6, r1)     // Catch: Exception -> 0x0075
        L_0x003c:
            if (r2 == 0) goto L_0x0052
            boolean r1 = r2 instanceof java.lang.String     // Catch: Exception -> 0x0075
            if (r1 == 0) goto L_0x0052
            com.tencent.smtt.sdk.TbsCoreLoadStat r1 = com.tencent.smtt.sdk.TbsCoreLoadStat.getInstance()     // Catch: Exception -> 0x0075
            r5 = 325(0x145, float:4.55E-43)
            java.lang.Throwable r6 = new java.lang.Throwable     // Catch: Exception -> 0x0075
            java.lang.String r2 = (java.lang.String) r2     // Catch: Exception -> 0x0075
            r6.<init>(r2)     // Catch: Exception -> 0x0075
            r1.a(r11, r5, r6)     // Catch: Exception -> 0x0075
        L_0x0052:
            r1 = r4
            r3 = r4
        L_0x0054:
            if (r3 != 0) goto L_0x007c
        L_0x0056:
            return r4
        L_0x0057:
            r0 = r3
            com.tencent.smtt.export.external.interfaces.IX5WebViewBase r0 = (com.tencent.smtt.export.external.interfaces.IX5WebViewBase) r0     // Catch: Exception -> 0x0075
            r1 = r0
            if (r1 == 0) goto L_0x0054
            android.view.View r2 = r1.getView()     // Catch: Exception -> 0x007e
            if (r2 != 0) goto L_0x0054
            com.tencent.smtt.sdk.TbsCoreLoadStat r2 = com.tencent.smtt.sdk.TbsCoreLoadStat.getInstance()     // Catch: Exception -> 0x007e
            r5 = 325(0x145, float:4.55E-43)
            java.lang.Throwable r6 = new java.lang.Throwable     // Catch: Exception -> 0x007e
            java.lang.String r7 = "x5webview.getView is null!"
            r6.<init>(r7)     // Catch: Exception -> 0x007e
            r2.a(r11, r5, r6)     // Catch: Exception -> 0x007e
            r3 = r4
            goto L_0x0054
        L_0x0075:
            r1 = move-exception
            r2 = r4
        L_0x0077:
            r1.printStackTrace()
            r1 = r2
            goto L_0x0054
        L_0x007c:
            r4 = r1
            goto L_0x0056
        L_0x007e:
            r2 = move-exception
            r9 = r2
            r2 = r1
            r1 = r9
            goto L_0x0077
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.sdk.bj.a(android.content.Context):com.tencent.smtt.export.external.interfaces.IX5WebViewBase");
    }

    public InputStream a(String str, boolean z) {
        Object invokeStaticMethod = this.a.invokeStaticMethod("com.tencent.tbs.tbsshell.WebCoreProxy", "getCacheFile", new Class[]{String.class, Boolean.TYPE}, str, Boolean.valueOf(z));
        if (invokeStaticMethod == null) {
            return null;
        }
        return (InputStream) invokeStaticMethod;
    }

    public String a(String str) {
        Object invokeStaticMethod = this.a.invokeStaticMethod("com.tencent.tbs.tbsshell.WebCoreProxy", "getCookie", new Class[]{String.class}, str);
        if (invokeStaticMethod == null) {
            return null;
        }
        return (String) invokeStaticMethod;
    }

    public void a(Context context, boolean z) {
        TbsLog.w("desktop", " tbsWizard clearAllX5Cache");
        if (z) {
            this.a.invokeStaticMethod("com.tencent.tbs.tbsshell.WebCoreProxy", "clearAllCache", new Class[]{Context.class}, context);
            return;
        }
        try {
            this.a.invokeStaticMethod("com.tencent.tbs.tbsshell.WebCoreProxy", "clearAllCache", new Class[]{Context.class, Boolean.TYPE}, context, Boolean.valueOf(z));
        } catch (Exception e) {
            this.a.invokeStaticMethod("com.tencent.tbs.tbsshell.WebCoreProxy", "webViewDatabaseClearUsernamePassword", new Class[]{Context.class}, context);
            this.a.invokeStaticMethod("com.tencent.tbs.tbsshell.WebCoreProxy", "webViewDatabaseClearHttpAuthUsernamePassword", new Class[]{Context.class}, context);
            this.a.invokeStaticMethod("com.tencent.tbs.tbsshell.WebCoreProxy", "webViewDatabaseClearFormData", new Class[]{Context.class}, context);
            this.a.invokeStaticMethod("com.tencent.smtt.webkit.CacheManager", "removeAllCacheFiles", null, new Object[0]);
            this.a.invokeStaticMethod("com.tencent.smtt.webkit.CacheManager", "clearLocalStorage", null, new Object[0]);
            Object invokeStaticMethod = this.a.invokeStaticMethod("com.tencent.smtt.net.http.DnsManager", "getInstance", null, new Object[0]);
            if (invokeStaticMethod != null) {
                this.a.invokeMethod(invokeStaticMethod, "com.tencent.smtt.net.http.DnsManager", "removeAllDns", null, new Object[0]);
            }
            Object invokeStaticMethod2 = this.a.invokeStaticMethod("com.tencent.smtt.webkit.SmttPermanentPermissions", "getInstance", null, new Object[0]);
            if (invokeStaticMethod2 != null) {
                this.a.invokeMethod(invokeStaticMethod2, "com.tencent.smtt.webkit.SmttPermanentPermissions", "clearAllPermanentPermission", null, new Object[0]);
            }
            this.a.invokeStaticMethod("com.tencent.tbs.tbsshell.WebCoreProxy", "removeAllIcons", null, new Object[0]);
        }
    }

    public void a(ValueCallback<Map> valueCallback) {
        this.a.invokeStaticMethod("com.tencent.tbs.tbsshell.WebCoreProxy", "webStorageGetOrigins", new Class[]{ValueCallback.class}, valueCallback);
    }

    public void a(String str, long j) {
        this.a.invokeStaticMethod("com.tencent.tbs.tbsshell.WebCoreProxy", "webStorageSetQuotaForOrigin", new Class[]{String.class, Long.TYPE}, str, Long.valueOf(j));
    }

    public void a(String str, ValueCallback<Long> valueCallback) {
        this.a.invokeStaticMethod("com.tencent.tbs.tbsshell.WebCoreProxy", "webStorageGetUsageForOrigin", new Class[]{String.class, ValueCallback.class}, str, valueCallback);
    }

    public void a(String str, IconListener iconListener) {
        this.a.invokeStaticMethod("com.tencent.tbs.tbsshell.WebCoreProxy", "requestIconForPageUrl", new Class[]{String.class, IconListener.class}, str, iconListener);
    }

    public boolean a() {
        Method method = this.a.getClassLoader().loadClass("com.tencent.tbs.tbsshell.WebCoreProxy").getMethod("canUseX5", new Class[0]);
        method.setAccessible(true);
        Object invoke = method.invoke(null, new Object[0]);
        return invoke instanceof Boolean ? ((Boolean) invoke).booleanValue() : ((Boolean) invoke).booleanValue();
    }

    public boolean a(Context context, String str) {
        Object invokeStaticMethod = this.a.invokeStaticMethod("com.tencent.tbs.tbsshell.WebCoreProxy", "canOpenFile", new Class[]{Context.class, String.class}, context, str);
        if (invokeStaticMethod instanceof Boolean) {
            return ((Boolean) invokeStaticMethod).booleanValue();
        }
        return false;
    }

    public boolean a(Map<String, String[]> map) {
        Object invokeStaticMethod = this.a.invokeStaticMethod("com.tencent.tbs.tbsshell.WebCoreProxy", "cookieManager_setCookies", new Class[]{Map.class}, map);
        if (invokeStaticMethod == null) {
            return false;
        }
        return ((Boolean) invokeStaticMethod).booleanValue();
    }

    public Uri[] a(int i, Intent intent) {
        Object invokeStaticMethod = this.a.invokeStaticMethod("com.tencent.tbs.tbsshell.WebCoreProxy", "parseFileChooserResult", new Class[]{Integer.TYPE, Intent.class}, Integer.valueOf(i), intent);
        if (invokeStaticMethod == null) {
            return null;
        }
        return (Uri[]) invokeStaticMethod;
    }

    public DexLoader b() {
        return this.a;
    }

    public void b(String str) {
        this.a.invokeStaticMethod("com.tencent.tbs.tbsshell.WebCoreProxy", "openIconDB", new Class[]{String.class}, str);
    }

    public void b(String str, ValueCallback<Long> valueCallback) {
        this.a.invokeStaticMethod("com.tencent.tbs.tbsshell.WebCoreProxy", "webStorageGetQuotaForOrigin", new Class[]{String.class, ValueCallback.class}, str, valueCallback);
    }

    public boolean b(Context context) {
        Object invokeStaticMethod = this.a.invokeStaticMethod("com.tencent.tbs.tbsshell.WebCoreProxy", "webViewDatabaseHasUsernamePassword", new Class[]{Context.class}, context);
        if (invokeStaticMethod == null) {
            return false;
        }
        return ((Boolean) invokeStaticMethod).booleanValue();
    }

    public Object c() {
        return this.a.invokeStaticMethod("com.tencent.tbs.tbsshell.WebCoreProxy", "cacheDisabled", new Class[0], new Object[0]);
    }

    public void c(Context context) {
        this.a.invokeStaticMethod("com.tencent.tbs.tbsshell.WebCoreProxy", "webViewDatabaseClearUsernamePassword", new Class[]{Context.class}, context);
    }

    public void c(String str) {
        this.a.invokeStaticMethod("com.tencent.tbs.tbsshell.WebCoreProxy", "retainIconForPageUrl", new Class[]{String.class}, str);
    }

    public void d(String str) {
        this.a.invokeStaticMethod("com.tencent.tbs.tbsshell.WebCoreProxy", "releaseIconForPageUrl", new Class[]{String.class}, str);
    }

    public boolean d() {
        Object invokeStaticMethod = this.a.invokeStaticMethod("com.tencent.tbs.tbsshell.WebCoreProxy", "cookieManager_acceptCookie", new Class[0], new Object[0]);
        if (invokeStaticMethod == null) {
            return false;
        }
        return ((Boolean) invokeStaticMethod).booleanValue();
    }

    public boolean d(Context context) {
        Object invokeStaticMethod = this.a.invokeStaticMethod("com.tencent.tbs.tbsshell.WebCoreProxy", "webViewDatabaseHasHttpAuthUsernamePassword", new Class[]{Context.class}, context);
        if (invokeStaticMethod == null) {
            return false;
        }
        return ((Boolean) invokeStaticMethod).booleanValue();
    }

    public void e() {
        this.a.invokeStaticMethod("com.tencent.tbs.tbsshell.WebCoreProxy", "cookieManager_removeAllCookie", new Class[0], new Object[0]);
    }

    public void e(Context context) {
        this.a.invokeStaticMethod("com.tencent.tbs.tbsshell.WebCoreProxy", "webViewDatabaseClearHttpAuthUsernamePassword", new Class[]{Context.class}, context);
    }

    public void e(String str) {
        this.a.invokeStaticMethod("com.tencent.tbs.tbsshell.WebCoreProxy", "webStorageDeleteOrigin", new Class[]{String.class}, str);
    }

    public String f() {
        Object invokeStaticMethod = this.a.invokeStaticMethod("com.tencent.tbs.tbsshell.WebCoreProxy", "getMiniQBVersion", new Class[0], new Object[0]);
        if (invokeStaticMethod == null) {
            return null;
        }
        return (String) invokeStaticMethod;
    }

    public boolean f(Context context) {
        Object invokeStaticMethod = this.a.invokeStaticMethod("com.tencent.tbs.tbsshell.WebCoreProxy", "webViewDatabaseHasFormData", new Class[]{Context.class}, context);
        if (invokeStaticMethod == null) {
            return false;
        }
        return ((Boolean) invokeStaticMethod).booleanValue();
    }

    public Object g() {
        return this.a.invokeStaticMethod("com.tencent.tbs.tbsshell.WebCoreProxy", "getCachFileBaseDir", new Class[0], new Object[0]);
    }

    public void g(Context context) {
        this.a.invokeStaticMethod("com.tencent.tbs.tbsshell.WebCoreProxy", "webViewDatabaseClearFormData", new Class[]{Context.class}, context);
    }

    public boolean h() {
        Object invokeStaticMethod = this.a.invokeStaticMethod("com.tencent.tbs.tbsshell.WebCoreProxy", "cookieManager_hasCookies", new Class[0], new Object[0]);
        if (invokeStaticMethod == null) {
            return false;
        }
        return ((Boolean) invokeStaticMethod).booleanValue();
    }

    public IX5WebChromeClient i() {
        Object invokeStaticMethod;
        if (!(this.a == null || (invokeStaticMethod = this.a.invokeStaticMethod("com.tencent.tbs.tbsshell.WebCoreProxy", "createDefaultX5WebChromeClient", new Class[0], new Object[0])) == null)) {
            return (IX5WebChromeClient) invokeStaticMethod;
        }
        return null;
    }

    public IX5WebViewClient j() {
        Object invokeStaticMethod = this.a.invokeStaticMethod("com.tencent.tbs.tbsshell.WebCoreProxy", "createDefaultX5WebViewClient", new Class[0], new Object[0]);
        if (invokeStaticMethod == null) {
            return null;
        }
        return (IX5WebViewClient) invokeStaticMethod;
    }

    public IX5WebViewClientExtension k() {
        Object invokeStaticMethod = this.a.invokeStaticMethod("com.tencent.tbs.tbsshell.WebCoreProxy", "createDefaultX5WebChromeClientExtension", new Class[0], new Object[0]);
        if (invokeStaticMethod == null) {
            return null;
        }
        return (IX5WebViewClientExtension) invokeStaticMethod;
    }

    public void l() {
        this.a.invokeStaticMethod("com.tencent.tbs.tbsshell.WebCoreProxy", "removeAllIcons", null, new Object[0]);
    }

    public void m() {
        this.a.invokeStaticMethod("com.tencent.tbs.tbsshell.WebCoreProxy", "closeIconDB", null, new Object[0]);
    }

    public void n() {
        this.a.invokeStaticMethod("com.tencent.tbs.tbsshell.WebCoreProxy", "webStorageDeleteAllData", null, new Object[0]);
    }

    public void o() {
        this.a.invokeStaticMethod("com.tencent.tbs.tbsshell.WebCoreProxy", "closeFileReader", new Class[0], new Object[0]);
    }
}
