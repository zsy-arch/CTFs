package com.tencent.smtt.sdk;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Dialog;
import android.content.ComponentCallbacks;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Picture;
import android.graphics.Rect;
import android.net.http.SslCertificate;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.DownloadListener;
import android.webkit.ValueCallback;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.FrameLayout;
import com.tencent.smtt.export.external.TbsCoreSettings;
import com.tencent.smtt.export.external.extension.interfaces.IX5WebChromeClientExtension;
import com.tencent.smtt.export.external.extension.interfaces.IX5WebSettingsExtension;
import com.tencent.smtt.export.external.extension.interfaces.IX5WebViewClientExtension;
import com.tencent.smtt.export.external.extension.interfaces.IX5WebViewExtension;
import com.tencent.smtt.export.external.extension.proxy.X5ProxyWebViewClientExtension;
import com.tencent.smtt.export.external.interfaces.IX5WebViewBase;
import com.tencent.smtt.sdk.TbsDownloadConfig;
import com.tencent.smtt.sdk.TbsListener;
import com.tencent.smtt.sdk.stat.MttLoader;
import com.tencent.smtt.sdk.stat.b;
import com.tencent.smtt.utils.FileUtil;
import com.tencent.smtt.utils.TbsLog;
import com.tencent.smtt.utils.d;
import com.tencent.smtt.utils.i;
import com.tencent.smtt.utils.n;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.nio.channels.FileLock;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class WebView extends FrameLayout implements View.OnLongClickListener {
    public static final int GETPVERROR = -1;
    public static final int NIGHT_MODE_COLOR = -16777216;
    public static final int NORMAL_MODE_ALPHA = 255;
    public static final String SCHEME_GEO = "geo:0,0?q=";
    public static final String SCHEME_MAILTO = "mailto:";
    public static final String SCHEME_TEL = "tel:";

    /* renamed from: a */
    public int f1294a;

    /* renamed from: b */
    public final String f1295b;

    /* renamed from: e */
    public boolean f1296e;
    public IX5WebViewBase f;
    public a g;
    public WebSettings h;
    public Context i;
    public boolean k;
    public WebViewCallbackClient mWebViewCallbackClient;
    public WebViewClient n;
    public WebChromeClient o;
    public final int q;
    public final int r;
    public final int s;
    public final String t;
    public final String u;
    public Object x;
    public View.OnLongClickListener y;

    /* renamed from: c */
    public static final Lock f1292c = new ReentrantLock();

    /* renamed from: d */
    public static OutputStream f1293d = null;
    public static Context j = null;
    public static boolean mWebViewCreated = false;
    public static n l = null;
    public static Method m = null;
    public static String p = null;
    public static boolean mSysWebviewCreated = false;
    public static Paint v = null;
    public static boolean w = true;
    public static int NIGHT_MODE_ALPHA = 153;

    /* loaded from: classes.dex */
    public static class HitTestResult {
        @Deprecated
        public static final int ANCHOR_TYPE = 1;
        public static final int EDIT_TEXT_TYPE = 9;
        public static final int EMAIL_TYPE = 4;
        public static final int GEO_TYPE = 3;
        @Deprecated
        public static final int IMAGE_ANCHOR_TYPE = 6;
        public static final int IMAGE_TYPE = 5;
        public static final int PHONE_TYPE = 2;
        public static final int SRC_ANCHOR_TYPE = 7;
        public static final int SRC_IMAGE_ANCHOR_TYPE = 8;
        public static final int UNKNOWN_TYPE = 0;

        /* renamed from: a */
        public IX5WebViewBase.HitTestResult f1308a;

        /* renamed from: b */
        public WebView.HitTestResult f1309b;

        public HitTestResult() {
            this.f1309b = null;
            this.f1308a = null;
            this.f1309b = null;
        }

        public HitTestResult(WebView.HitTestResult hitTestResult) {
            this.f1309b = null;
            this.f1308a = null;
            this.f1309b = hitTestResult;
        }

        public HitTestResult(IX5WebViewBase.HitTestResult hitTestResult) {
            this.f1309b = null;
            this.f1308a = hitTestResult;
            this.f1309b = null;
        }

        public String getExtra() {
            IX5WebViewBase.HitTestResult hitTestResult = this.f1308a;
            if (hitTestResult != null) {
                return hitTestResult.getExtra();
            }
            WebView.HitTestResult hitTestResult2 = this.f1309b;
            return hitTestResult2 != null ? hitTestResult2.getExtra() : BuildConfig.FLAVOR;
        }

        public int getType() {
            IX5WebViewBase.HitTestResult hitTestResult = this.f1308a;
            if (hitTestResult != null) {
                return hitTestResult.getType();
            }
            WebView.HitTestResult hitTestResult2 = this.f1309b;
            if (hitTestResult2 != null) {
                return hitTestResult2.getType();
            }
            return 0;
        }
    }

    /* loaded from: classes.dex */
    public interface PictureListener {
        void onNewPicture(WebView webView, Picture picture);
    }

    /* loaded from: classes.dex */
    public class WebViewTransport {

        /* renamed from: b */
        public WebView f1311b;

        public WebViewTransport() {
            WebView.this = r1;
        }

        public synchronized WebView getWebView() {
            return this.f1311b;
        }

        public synchronized void setWebView(WebView webView) {
            this.f1311b = webView;
        }
    }

    /* loaded from: classes.dex */
    public class a extends android.webkit.WebView {
        public a(WebView webView, Context context) {
            this(context, null);
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public a(Context context, AttributeSet attributeSet) {
            super(r2.d(context), attributeSet);
            WebView.this = r2;
            if (!QbSdk.f1128b || !TbsShareManager.isThirdPartyApp(context)) {
                CookieSyncManager.createInstance(r2.i).startSync();
                try {
                    Method declaredMethod = Class.forName("android.webkit.WebViewWorker").getDeclaredMethod("getHandler", new Class[0]);
                    declaredMethod.setAccessible(true);
                    ((Handler) declaredMethod.invoke(null, new Object[0])).getLooper().getThread().setUncaughtExceptionHandler(new e());
                    WebView.mSysWebviewCreated = true;
                } catch (Exception unused) {
                }
            }
        }

        public void a() {
            super.computeScroll();
        }

        public void a(int i, int i2, int i3, int i4) {
            super.onScrollChanged(i, i2, i3, i4);
        }

        @TargetApi(9)
        public void a(int i, int i2, boolean z, boolean z2) {
            int i3 = Build.VERSION.SDK_INT;
            super.onOverScrolled(i, i2, z, z2);
        }

        @TargetApi(9)
        public boolean a(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, boolean z) {
            int i9 = Build.VERSION.SDK_INT;
            return super.overScrollBy(i, i2, i3, i4, i5, i6, i7, i8, z);
        }

        public boolean a(MotionEvent motionEvent) {
            return super.onTouchEvent(motionEvent);
        }

        public boolean b(MotionEvent motionEvent) {
            return super.dispatchTouchEvent(motionEvent);
        }

        public boolean c(MotionEvent motionEvent) {
            return super.onInterceptTouchEvent(motionEvent);
        }

        @Override // android.webkit.WebView, android.view.View
        public void computeScroll() {
            WebViewCallbackClient webViewCallbackClient = WebView.this.mWebViewCallbackClient;
            if (webViewCallbackClient != null) {
                webViewCallbackClient.computeScroll(this);
            } else {
                super.computeScroll();
            }
        }

        @Override // android.webkit.WebView, android.view.ViewGroup, android.view.View
        public void dispatchDraw(Canvas canvas) {
            try {
                super.dispatchDraw(canvas);
                if (!WebView.w && WebView.v != null) {
                    canvas.save();
                    canvas.drawPaint(WebView.v);
                    canvas.restore();
                }
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }

        @Override // android.view.ViewGroup, android.view.View
        public boolean dispatchTouchEvent(MotionEvent motionEvent) {
            WebViewCallbackClient webViewCallbackClient = WebView.this.mWebViewCallbackClient;
            return webViewCallbackClient != null ? webViewCallbackClient.dispatchTouchEvent(motionEvent, this) : super.dispatchTouchEvent(motionEvent);
        }

        @Override // android.webkit.WebView
        public WebSettings getSettings() {
            try {
                return super.getSettings();
            } catch (Exception e2) {
                e2.printStackTrace();
                return null;
            }
        }

        @Override // android.view.View
        public void invalidate() {
            super.invalidate();
            WebViewCallbackClient webViewCallbackClient = WebView.this.mWebViewCallbackClient;
            if (webViewCallbackClient != null) {
                webViewCallbackClient.invalidate();
            }
        }

        @Override // android.view.ViewGroup
        public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
            WebViewCallbackClient webViewCallbackClient = WebView.this.mWebViewCallbackClient;
            return webViewCallbackClient != null ? webViewCallbackClient.onInterceptTouchEvent(motionEvent, this) : super.onInterceptTouchEvent(motionEvent);
        }

        @Override // android.webkit.WebView, android.view.View
        @TargetApi(9)
        public void onOverScrolled(int i, int i2, boolean z, boolean z2) {
            WebViewCallbackClient webViewCallbackClient = WebView.this.mWebViewCallbackClient;
            if (webViewCallbackClient != null) {
                webViewCallbackClient.onOverScrolled(i, i2, z, z2, this);
                return;
            }
            int i3 = Build.VERSION.SDK_INT;
            super.onOverScrolled(i, i2, z, z2);
        }

        @Override // android.webkit.WebView, android.view.View
        public void onScrollChanged(int i, int i2, int i3, int i4) {
            WebViewCallbackClient webViewCallbackClient = WebView.this.mWebViewCallbackClient;
            if (webViewCallbackClient != null) {
                webViewCallbackClient.onScrollChanged(i, i2, i3, i4, this);
                return;
            }
            super.onScrollChanged(i, i2, i3, i4);
            WebView.this.onScrollChanged(i, i2, i3, i4);
        }

        @Override // android.webkit.WebView, android.view.View
        @SuppressLint({"ClickableViewAccessibility"})
        public boolean onTouchEvent(MotionEvent motionEvent) {
            if (!hasFocus()) {
                requestFocus();
            }
            WebViewCallbackClient webViewCallbackClient = WebView.this.mWebViewCallbackClient;
            if (webViewCallbackClient != null) {
                return webViewCallbackClient.onTouchEvent(motionEvent, this);
            }
            try {
                return super.onTouchEvent(motionEvent);
            } catch (Exception e2) {
                e2.printStackTrace();
                return false;
            }
        }

        @Override // android.view.View
        @TargetApi(9)
        public boolean overScrollBy(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, boolean z) {
            WebViewCallbackClient webViewCallbackClient = WebView.this.mWebViewCallbackClient;
            if (webViewCallbackClient != null) {
                return webViewCallbackClient.overScrollBy(i, i2, i3, i4, i5, i6, i7, i8, z, this);
            }
            int i9 = Build.VERSION.SDK_INT;
            return super.overScrollBy(i, i2, i3, i4, i5, i6, i7, i8, z);
        }

        @Override // android.webkit.WebView, android.view.View
        public void setOverScrollMode(int i) {
            try {
                super.setOverScrollMode(i);
            } catch (Exception unused) {
            }
        }
    }

    public WebView(Context context) {
        this(context, null, 0, false);
    }

    public WebView(Context context, AttributeSet attributeSet, int i) {
        this(context, attributeSet, i, false);
    }

    public WebView(Context context, AttributeSet attributeSet, int i, boolean z) {
        this(context, attributeSet, i, null, z);
    }

    public WebView(Context context, boolean z) {
        super(context);
        this.f1295b = "WebView";
        this.f1296e = false;
        this.h = null;
        this.i = null;
        this.f1294a = 0;
        this.k = false;
        this.n = null;
        this.o = null;
        this.q = 1;
        this.r = 2;
        this.s = 3;
        this.t = "javascript:document.getElementsByTagName('HEAD').item(0).removeChild(document.getElementById('QQBrowserSDKNightMode'));";
        this.u = "javascript:var style = document.createElement('style');style.type='text/css';style.id='QQBrowserSDKNightMode';style.innerHTML='html,body{background:none !important;background-color: #1d1e2a !important;}html *{background-color: #1d1e2a !important; color:#888888 !important;border-color:#3e4f61 !important;text-shadow:none !important;box-shadow:none !important;}a,a *{border-color:#4c5b99 !important; color:#2d69b3 !important;text-decoration:none !important;}a:visited,a:visited *{color:#a600a6 !important;}a:active,a:active *{color:#5588AA !important;}input,select,textarea,option,button{background-image:none !important;color:#AAAAAA !important;border-color:#4c5b99 !important;}form,div,button,span{background-color:#1d1e2a !important; border-color:#4c5b99 !important;}img{opacity:0.5}';document.getElementsByTagName('HEAD').item(0).appendChild(style);";
        this.x = null;
        this.y = null;
    }

    private void a(AttributeSet attributeSet) {
        View view;
        if (attributeSet != null) {
            try {
                int attributeCount = attributeSet.getAttributeCount();
                for (int i = 0; i < attributeCount; i++) {
                    if (attributeSet.getAttributeName(i).equalsIgnoreCase("scrollbars")) {
                        int[] intArray = getResources().getIntArray(16842974);
                        int attributeIntValue = attributeSet.getAttributeIntValue(i, -1);
                        if (attributeIntValue == intArray[1]) {
                            this.f.getView().setVerticalScrollBarEnabled(false);
                            view = this.f.getView();
                        } else if (attributeIntValue == intArray[2]) {
                            this.f.getView().setVerticalScrollBarEnabled(false);
                        } else if (attributeIntValue == intArray[3]) {
                            view = this.f.getView();
                        }
                        view.setHorizontalScrollBarEnabled(false);
                    }
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    private boolean a(View view) {
        Object a2;
        Context context = this.i;
        if ((context == null || QbSdk.getTbsVersion(context) <= 36200) && (a2 = i.a(this.x, "onLongClick", new Class[]{View.class}, view)) != null) {
            return ((Boolean) a2).booleanValue();
        }
        return false;
    }

    private boolean b(Context context) {
        try {
            return context.getPackageName().indexOf(TbsConfig.APP_QQ) >= 0;
        } catch (Throwable th) {
            th.printStackTrace();
            return false;
        }
    }

    private void c(Context context) {
        if (QbSdk.i && TbsShareManager.isThirdPartyApp(context)) {
            TbsExtensionFunctionManager.getInstance().initTbsBuglyIfNeed(context);
        }
        u a2 = u.a();
        a2.a(context);
        this.f1296e = a2.b();
    }

    public Context d(Context context) {
        return Build.VERSION.SDK_INT <= 22 ? context.createConfigurationContext(new Configuration()) : context;
    }

    public static void d() {
        try {
            new Thread(new Runnable() { // from class: com.tencent.smtt.sdk.WebView.8
                @Override // java.lang.Runnable
                public void run() {
                    if (WebView.j == null) {
                        TbsLog.d("TbsNeedReboot", "WebView.updateNeeeRebootStatus--mAppContext == null");
                        return;
                    }
                    d a2 = d.a(true);
                    if (d.f1335b) {
                        TbsLog.d("TbsNeedReboot", "WebView.updateNeeeRebootStatus--needReboot = true");
                        return;
                    }
                    k a3 = k.a(WebView.j);
                    int c2 = a3.c();
                    TbsLog.d("TbsNeedReboot", "WebView.updateNeeeRebootStatus--installStatus = " + c2);
                    if (c2 == 2) {
                        TbsLog.d("TbsNeedReboot", "WebView.updateNeeeRebootStatus--install setTbsNeedReboot true");
                        a2.a(String.valueOf(a3.b()));
                        a2.b(true);
                        return;
                    }
                    int b2 = a3.b("copy_status");
                    TbsLog.d("TbsNeedReboot", "WebView.updateNeeeRebootStatus--copyStatus = " + b2);
                    if (b2 == 1) {
                        TbsLog.d("TbsNeedReboot", "WebView.updateNeeeRebootStatus--copy setTbsNeedReboot true");
                        a2.a(String.valueOf(a3.c("copy_core_ver")));
                        a2.b(true);
                    } else if (u.a().b()) {
                    } else {
                        if (c2 == 3 || b2 == 3) {
                            TbsLog.d("TbsNeedReboot", "WebView.updateNeeeRebootStatus--setTbsNeedReboot true");
                            a2.a(String.valueOf(d.h));
                            a2.b(true);
                        }
                    }
                }
            }).start();
        } catch (Throwable th) {
            TbsLog.e("webview", "updateRebootStatus excpetion: " + th);
        }
    }

    @Deprecated
    public static void disablePlatformNotifications() {
        if (!u.a().b()) {
            i.a("android.webkit.WebView", "disablePlatformNotifications");
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r5v11 */
    /* JADX WARN: Type inference failed for: r5v12 */
    /* JADX WARN: Type inference failed for: r5v13 */
    /* JADX WARN: Type inference failed for: r5v14 */
    /* JADX WARN: Type inference failed for: r5v4 */
    private int e(Context context) {
        FileLock a2;
        Throwable th;
        IOException e2;
        StringBuilder sb;
        Exception e3;
        FileOutputStream b2 = FileUtil.b(context, true, "tbslock.txt");
        if (b2 == null || (a2 = FileUtil.a(context, b2)) == null) {
            return -1;
        }
        FileInputStream tryLock = f1292c.tryLock();
        if (tryLock) {
            try {
                FileInputStream fileInputStream = null;
                tryLock = 0;
                try {
                    File tbsFolderDir = QbSdk.getTbsFolderDir(context);
                    File file = new File(tbsFolderDir + File.separator + "core_private", "pv.db");
                    if (file.exists()) {
                        Properties properties = new Properties();
                        tryLock = new FileInputStream(file);
                        try {
                            properties.load(tryLock);
                            tryLock.close();
                            String str = "PV";
                            String property = properties.getProperty(str);
                            if (property == null) {
                                try {
                                    tryLock.close();
                                    tryLock = str;
                                } catch (IOException e4) {
                                    e2 = e4;
                                    sb = new StringBuilder();
                                    sb.append("TbsInstaller--getTbsCorePV IOException=");
                                    sb.append(e2.toString());
                                    TbsLog.e("getTbsCorePV", sb.toString());
                                    f1292c.unlock();
                                    FileUtil.a(a2, b2);
                                    return -1;
                                }
                            } else {
                                int parseInt = Integer.parseInt(property);
                                try {
                                    tryLock.close();
                                } catch (IOException e5) {
                                    StringBuilder a3 = e.a.a.a.a.a("TbsInstaller--getTbsCorePV IOException=");
                                    a3.append(e5.toString());
                                    TbsLog.e("getTbsCorePV", a3.toString());
                                }
                                f1292c.unlock();
                                FileUtil.a(a2, b2);
                                return parseInt;
                            }
                        } catch (Exception e6) {
                            e3 = e6;
                            fileInputStream = tryLock;
                            TbsLog.e("getTbsCorePV", "TbsInstaller--getTbsCorePV Exception=" + e3.toString());
                            tryLock = fileInputStream;
                            if (fileInputStream != null) {
                                try {
                                    fileInputStream.close();
                                    tryLock = fileInputStream;
                                } catch (IOException e7) {
                                    e2 = e7;
                                    sb = new StringBuilder();
                                    sb.append("TbsInstaller--getTbsCorePV IOException=");
                                    sb.append(e2.toString());
                                    TbsLog.e("getTbsCorePV", sb.toString());
                                    f1292c.unlock();
                                    FileUtil.a(a2, b2);
                                    return -1;
                                }
                            }
                            f1292c.unlock();
                            FileUtil.a(a2, b2);
                            return -1;
                        } catch (Throwable th2) {
                            th = th2;
                            if (tryLock != 0) {
                                try {
                                    tryLock.close();
                                } catch (IOException e8) {
                                    StringBuilder a4 = e.a.a.a.a.a("TbsInstaller--getTbsCorePV IOException=");
                                    a4.append(e8.toString());
                                    TbsLog.e("getTbsCorePV", a4.toString());
                                }
                            }
                            f1292c.unlock();
                            FileUtil.a(a2, b2);
                            throw th;
                        }
                    }
                } catch (Exception e9) {
                    e3 = e9;
                }
                f1292c.unlock();
                FileUtil.a(a2, b2);
                return -1;
            } catch (Throwable th3) {
                th = th3;
            }
        } else {
            FileUtil.a(a2, b2);
            return -1;
        }
    }

    @Deprecated
    public static void enablePlatformNotifications() {
        if (!u.a().b()) {
            i.a("android.webkit.WebView", "enablePlatformNotifications");
        }
    }

    private void f(Context context) {
        try {
            File tbsFolderDir = QbSdk.getTbsFolderDir(context);
            File file = new File(tbsFolderDir + File.separator + "core_private", "pv.db");
            if (file.exists()) {
                file.delete();
            }
        } catch (Exception e2) {
            StringBuilder a2 = e.a.a.a.a.a("TbsInstaller--getTbsCorePV Exception=");
            a2.append(e2.toString());
            TbsLog.i("getTbsCorePV", a2.toString());
        }
    }

    public static String findAddress(String str) {
        if (!u.a().b()) {
            return android.webkit.WebView.findAddress(str);
        }
        return null;
    }

    public static String getCrashExtraMessage(Context context) {
        Map<String, Object> map;
        if (context == null) {
            return BuildConfig.FLAVOR;
        }
        StringBuilder a2 = e.a.a.a.a.a("tbs_core_version:");
        a2.append(QbSdk.getTbsVersionForCrash(context));
        a2.append(";");
        a2.append("tbs_sdk_version:");
        a2.append(43939);
        a2.append(";");
        String sb = a2.toString();
        boolean z = false;
        if (TbsConfig.APP_WX.equals(context.getApplicationInfo().packageName)) {
            try {
                Class.forName("de.robv.android.xposed.XposedBridge");
                z = true;
            } catch (ClassNotFoundException unused) {
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
        if (z) {
            return e.a.a.a.a.a(sb, "isXposed=true;");
        }
        StringBuilder sb2 = new StringBuilder();
        sb2.append(d.a(true).e());
        sb2.append("\n");
        sb2.append(sb);
        if (!TbsShareManager.isThirdPartyApp(context) && (map = QbSdk.n) != null && map.containsKey(TbsCoreSettings.TBS_SETTINGS_WEAPP_ID_KEY) && QbSdk.n.containsKey(TbsCoreSettings.TBS_SETTINGS_WEAPP_NAME_KEY)) {
            StringBuilder a3 = e.a.a.a.a.a("weapp_id:");
            a3.append(QbSdk.n.get(TbsCoreSettings.TBS_SETTINGS_WEAPP_ID_KEY));
            a3.append(";");
            a3.append(TbsCoreSettings.TBS_SETTINGS_WEAPP_NAME_KEY);
            a3.append(":");
            a3.append(QbSdk.n.get(TbsCoreSettings.TBS_SETTINGS_WEAPP_NAME_KEY));
            a3.append(";");
            String sb3 = a3.toString();
            sb2.append("\n");
            sb2.append(sb3);
        }
        return sb2.length() > 8192 ? sb2.substring(sb2.length() - 8192) : sb2.toString();
    }

    public static PackageInfo getCurrentWebViewPackage() {
        if (u.a().b() || Build.VERSION.SDK_INT < 26) {
            return null;
        }
        try {
            return (PackageInfo) i.a("android.webkit.WebView", "getCurrentWebViewPackage");
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    @Deprecated
    public static synchronized Object getPluginList() {
        synchronized (WebView.class) {
            if (u.a().b()) {
                return null;
            }
            return i.a("android.webkit.WebView", "getPluginList");
        }
    }

    public static int getTbsCoreVersion(Context context) {
        return QbSdk.getTbsVersion(context);
    }

    public static boolean getTbsNeedReboot() {
        d();
        return d.a(true).f();
    }

    public static int getTbsSDKVersion(Context context) {
        return 43939;
    }

    private long h() {
        long j2;
        synchronized (QbSdk.h) {
            if (QbSdk.f1131e) {
                QbSdk.g = (System.currentTimeMillis() - QbSdk.f) + QbSdk.g;
                TbsLog.d("sdkreport", "pv report, WebView.getWifiConnectedTime QbSdk.sWifiConnectedTime=" + QbSdk.g);
            }
            j2 = QbSdk.g / 1000;
            QbSdk.g = 0L;
            QbSdk.f = System.currentTimeMillis();
        }
        return j2;
    }

    public static void setDataDirectorySuffix(String str) {
        if (Build.VERSION.SDK_INT >= 28) {
            try {
                i.a(Class.forName("android.webkit.WebView"), "setDataDirectorySuffix", (Class<?>[]) new Class[]{String.class}, str);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        HashMap hashMap = new HashMap();
        hashMap.put("data_directory_suffix", str);
        QbSdk.initTbsSettings(hashMap);
    }

    public static synchronized void setSysDayOrNight(boolean z) {
        int i;
        Paint paint;
        synchronized (WebView.class) {
            if (z != w) {
                w = z;
                if (v == null) {
                    v = new Paint();
                    v.setColor(NIGHT_MODE_COLOR);
                }
                if (!z) {
                    if (v.getAlpha() != NIGHT_MODE_ALPHA) {
                        paint = v;
                        i = NIGHT_MODE_ALPHA;
                        paint.setAlpha(i);
                    }
                    return;
                }
                int alpha = v.getAlpha();
                i = NORMAL_MODE_ALPHA;
                if (alpha != 255) {
                    paint = v;
                    paint.setAlpha(i);
                }
                return;
            }
        }
    }

    public static void setWebContentsDebuggingEnabled(boolean z) {
        u a2 = u.a();
        if (a2 == null || !a2.b()) {
            int i = Build.VERSION.SDK_INT;
            try {
                m = Class.forName("android.webkit.WebView").getDeclaredMethod("setWebContentsDebuggingEnabled", Boolean.TYPE);
                if (m != null) {
                    m.setAccessible(true);
                    m.invoke(null, Boolean.valueOf(z));
                }
            } catch (Exception e2) {
                StringBuilder a3 = e.a.a.a.a.a("Exception:");
                a3.append(e2.getStackTrace());
                TbsLog.e("QbSdk", a3.toString());
                e2.printStackTrace();
            }
        } else {
            a2.c().a(z);
        }
    }

    public void a() {
        String str;
        String str2;
        boolean z;
        Bundle sdkQBStatisticsInfo;
        if (!this.k && this.f1294a != 0) {
            this.k = true;
            boolean z2 = this.f1296e;
            String str3 = BuildConfig.FLAVOR;
            if (!z2 || (sdkQBStatisticsInfo = this.f.getX5WebViewExtension().getSdkQBStatisticsInfo()) == null) {
                str2 = str3;
                str = str2;
            } else {
                str3 = sdkQBStatisticsInfo.getString("guid");
                str2 = sdkQBStatisticsInfo.getString("qua2");
                str = sdkQBStatisticsInfo.getString("lc");
            }
            if (TbsConfig.APP_QZONE.equals(this.i.getApplicationInfo().packageName)) {
                int e2 = e(this.i);
                if (e2 == -1) {
                    e2 = this.f1294a;
                }
                this.f1294a = e2;
                f(this.i);
            }
            try {
                z = this.f.getX5WebViewExtension().isX5CoreSandboxMode();
            } catch (Throwable th) {
                TbsLog.w("tbsOnDetachedFromWindow", "exception: " + th);
                z = false;
            }
            b.a(this.i, str3, str2, str, this.f1294a, this.f1296e, h(), z);
            this.f1294a = 0;
            this.k = false;
        }
        super.onDetachedFromWindow();
    }

    public void a(Context context) {
        String str;
        int e2 = e(context);
        if (e2 != -1) {
            StringBuilder a2 = e.a.a.a.a.a("PV=");
            a2.append(String.valueOf(e2 + 1));
            str = a2.toString();
        } else {
            str = "PV=1";
        }
        File tbsFolderDir = QbSdk.getTbsFolderDir(context);
        File file = new File(tbsFolderDir + File.separator + "core_private", "pv.db");
        try {
            file.getParentFile().mkdirs();
            if (!file.isFile() || !file.exists()) {
                file.createNewFile();
            }
            f1293d = new FileOutputStream(file, false);
            f1293d.write(str.getBytes());
            if (f1293d != null) {
                f1293d.flush();
            }
        } catch (Throwable unused) {
        }
    }

    public void a(android.webkit.WebView webView) {
        boolean z = this.f1296e;
    }

    public void a(IX5WebViewBase iX5WebViewBase) {
        this.f = iX5WebViewBase;
    }

    public void addJavascriptInterface(Object obj, String str) {
        if (!this.f1296e) {
            this.g.addJavascriptInterface(obj, str);
        } else {
            this.f.addJavascriptInterface(obj, str);
        }
    }

    @Override // android.view.ViewGroup
    public void addView(View view) {
        if (!this.f1296e) {
            this.g.addView(view);
            return;
        }
        View view2 = this.f.getView();
        try {
            Method a2 = i.a(view2, "addView", View.class);
            a2.setAccessible(true);
            a2.invoke(view2, view);
        } catch (Throwable unused) {
        }
    }

    public android.webkit.WebView b() {
        if (!this.f1296e) {
            return this.g;
        }
        return null;
    }

    public IX5WebViewBase c() {
        return this.f;
    }

    public boolean canGoBack() {
        return !this.f1296e ? this.g.canGoBack() : this.f.canGoBack();
    }

    public boolean canGoBackOrForward(int i) {
        return !this.f1296e ? this.g.canGoBackOrForward(i) : this.f.canGoBackOrForward(i);
    }

    public boolean canGoForward() {
        return !this.f1296e ? this.g.canGoForward() : this.f.canGoForward();
    }

    @Deprecated
    public boolean canZoomIn() {
        if (this.f1296e) {
            return this.f.canZoomIn();
        }
        int i = Build.VERSION.SDK_INT;
        Object a2 = i.a(this.g, "canZoomIn");
        if (a2 == null) {
            return false;
        }
        return ((Boolean) a2).booleanValue();
    }

    @Deprecated
    public boolean canZoomOut() {
        if (this.f1296e) {
            return this.f.canZoomOut();
        }
        int i = Build.VERSION.SDK_INT;
        Object a2 = i.a(this.g, "canZoomOut");
        if (a2 == null) {
            return false;
        }
        return ((Boolean) a2).booleanValue();
    }

    @Deprecated
    public Picture capturePicture() {
        if (this.f1296e) {
            return this.f.capturePicture();
        }
        Object a2 = i.a(this.g, "capturePicture");
        if (a2 == null) {
            return null;
        }
        return (Picture) a2;
    }

    public void clearCache(boolean z) {
        if (!this.f1296e) {
            this.g.clearCache(z);
        } else {
            this.f.clearCache(z);
        }
    }

    public void clearFormData() {
        if (!this.f1296e) {
            this.g.clearFormData();
        } else {
            this.f.clearFormData();
        }
    }

    public void clearHistory() {
        if (!this.f1296e) {
            this.g.clearHistory();
        } else {
            this.f.clearHistory();
        }
    }

    @TargetApi(3)
    public void clearMatches() {
        if (!this.f1296e) {
            this.g.clearMatches();
        } else {
            this.f.clearMatches();
        }
    }

    public void clearSslPreferences() {
        if (!this.f1296e) {
            this.g.clearSslPreferences();
        } else {
            this.f.clearSslPreferences();
        }
    }

    @Deprecated
    public void clearView() {
        if (!this.f1296e) {
            i.a(this.g, "clearView");
        } else {
            this.f.clearView();
        }
    }

    @Override // android.view.View
    public int computeHorizontalScrollExtent() {
        try {
            if (this.f1296e) {
                Method a2 = i.a(this.f.getView(), "computeHorizontalScrollExtent", new Class[0]);
                a2.setAccessible(true);
                return ((Integer) a2.invoke(this.f.getView(), new Object[0])).intValue();
            }
            Method a3 = i.a(this.g, "computeHorizontalScrollExtent", new Class[0]);
            a3.setAccessible(true);
            return ((Integer) a3.invoke(this.g, new Object[0])).intValue();
        } catch (Exception e2) {
            e2.printStackTrace();
            return -1;
        }
    }

    @Override // android.view.View
    public int computeHorizontalScrollOffset() {
        try {
            if (this.f1296e) {
                Method a2 = i.a(this.f.getView(), "computeHorizontalScrollOffset", new Class[0]);
                a2.setAccessible(true);
                return ((Integer) a2.invoke(this.f.getView(), new Object[0])).intValue();
            }
            Method a3 = i.a(this.g, "computeHorizontalScrollOffset", new Class[0]);
            a3.setAccessible(true);
            return ((Integer) a3.invoke(this.g, new Object[0])).intValue();
        } catch (Exception e2) {
            e2.printStackTrace();
            return -1;
        }
    }

    @Override // android.view.View
    public int computeHorizontalScrollRange() {
        try {
            if (this.f1296e) {
                return ((Integer) i.a(this.f.getView(), "computeHorizontalScrollRange", new Class[0], new Object[0])).intValue();
            }
            Method a2 = i.a(this.g, "computeHorizontalScrollRange", new Class[0]);
            a2.setAccessible(true);
            return ((Integer) a2.invoke(this.g, new Object[0])).intValue();
        } catch (Exception e2) {
            e2.printStackTrace();
            return -1;
        }
    }

    @Override // android.view.View
    public void computeScroll() {
        if (!this.f1296e) {
            this.g.computeScroll();
        } else {
            this.f.computeScroll();
        }
    }

    @Override // android.view.View
    public int computeVerticalScrollExtent() {
        try {
            if (this.f1296e) {
                Method a2 = i.a(this.f.getView(), "computeVerticalScrollExtent", new Class[0]);
                a2.setAccessible(true);
                return ((Integer) a2.invoke(this.f.getView(), new Object[0])).intValue();
            }
            Method a3 = i.a(this.g, "computeVerticalScrollExtent", new Class[0]);
            a3.setAccessible(true);
            return ((Integer) a3.invoke(this.g, new Object[0])).intValue();
        } catch (Exception e2) {
            e2.printStackTrace();
            return -1;
        }
    }

    @Override // android.view.View
    public int computeVerticalScrollOffset() {
        try {
            if (this.f1296e) {
                Method a2 = i.a(this.f.getView(), "computeVerticalScrollOffset", new Class[0]);
                a2.setAccessible(true);
                return ((Integer) a2.invoke(this.f.getView(), new Object[0])).intValue();
            }
            Method a3 = i.a(this.g, "computeVerticalScrollOffset", new Class[0]);
            a3.setAccessible(true);
            return ((Integer) a3.invoke(this.g, new Object[0])).intValue();
        } catch (Exception e2) {
            e2.printStackTrace();
            return -1;
        }
    }

    @Override // android.view.View
    public int computeVerticalScrollRange() {
        try {
            if (this.f1296e) {
                return ((Integer) i.a(this.f.getView(), "computeVerticalScrollRange", new Class[0], new Object[0])).intValue();
            }
            Method a2 = i.a(this.g, "computeVerticalScrollRange", new Class[0]);
            a2.setAccessible(true);
            return ((Integer) a2.invoke(this.g, new Object[0])).intValue();
        } catch (Exception e2) {
            e2.printStackTrace();
            return -1;
        }
    }

    public WebBackForwardList copyBackForwardList() {
        return this.f1296e ? WebBackForwardList.a(this.f.copyBackForwardList()) : WebBackForwardList.a(this.g.copyBackForwardList());
    }

    public Object createPrintDocumentAdapter(String str) {
        if (this.f1296e) {
            try {
                return this.f.createPrintDocumentAdapter(str);
            } catch (Throwable th) {
                th.printStackTrace();
                return null;
            }
        } else {
            int i = Build.VERSION.SDK_INT;
            return i.a(this.g, "createPrintDocumentAdapter", new Class[]{String.class}, str);
        }
    }

    public void customDiskCachePathEnabled(boolean z, String str) {
        if (this.f1296e && getX5WebViewExtension() != null) {
            Bundle bundle = new Bundle();
            bundle.putBoolean("enabled", z);
            bundle.putString("path", str);
            getX5WebViewExtension().invokeMiscMethod("customDiskCachePathEnabled", bundle);
        }
    }

    public void destroy() {
        try {
            if ("com.xunmeng.pinduoduo".equals(this.i.getApplicationInfo().packageName)) {
                new Thread("WebviewDestroy") { // from class: com.tencent.smtt.sdk.WebView.2
                    @Override // java.lang.Thread, java.lang.Runnable
                    public void run() {
                        WebView.this.tbsWebviewDestroy(false);
                    }
                }.start();
                if (this.f1296e) {
                    this.f.destroy();
                } else {
                    this.g.destroy();
                }
            } else {
                tbsWebviewDestroy(true);
            }
        } catch (Throwable unused) {
            tbsWebviewDestroy(true);
        }
    }

    public void documentHasImages(Message message) {
        if (!this.f1296e) {
            this.g.documentHasImages(message);
        } else {
            this.f.documentHasImages(message);
        }
    }

    public void dumpViewHierarchyWithProperties(BufferedWriter bufferedWriter, int i) {
        if (!this.f1296e) {
            i.a(this.g, "dumpViewHierarchyWithProperties", new Class[]{BufferedWriter.class, Integer.TYPE}, bufferedWriter, Integer.valueOf(i));
        } else {
            this.f.dumpViewHierarchyWithProperties(bufferedWriter, i);
        }
    }

    public void evaluateJavascript(String str, ValueCallback<String> valueCallback) {
        if (this.f1296e) {
            try {
                Method a2 = i.a(this.f.getView(), "evaluateJavascript", String.class, ValueCallback.class);
                a2.setAccessible(true);
                a2.invoke(this.f.getView(), str, valueCallback);
            } catch (Exception e2) {
                e2.printStackTrace();
                loadUrl(str);
            }
        } else {
            int i = Build.VERSION.SDK_INT;
            try {
                Method declaredMethod = Class.forName("android.webkit.WebView").getDeclaredMethod("evaluateJavascript", String.class, ValueCallback.class);
                declaredMethod.setAccessible(true);
                declaredMethod.invoke(this.g, str, valueCallback);
            } catch (Exception e3) {
                e3.printStackTrace();
            }
        }
    }

    @Deprecated
    public int findAll(String str) {
        if (this.f1296e) {
            return this.f.findAll(str);
        }
        Object a2 = i.a(this.g, "findAll", new Class[]{String.class}, str);
        if (a2 == null) {
            return 0;
        }
        return ((Integer) a2).intValue();
    }

    @TargetApi(16)
    public void findAllAsync(String str) {
        if (!this.f1296e) {
            int i = Build.VERSION.SDK_INT;
            i.a(this.g, "findAllAsync", new Class[]{String.class}, str);
            return;
        }
        this.f.findAllAsync(str);
    }

    public View findHierarchyView(String str, int i) {
        return !this.f1296e ? (View) i.a(this.g, "findHierarchyView", new Class[]{String.class, Integer.TYPE}, str, Integer.valueOf(i)) : this.f.findHierarchyView(str, i);
    }

    @TargetApi(3)
    public void findNext(boolean z) {
        if (!this.f1296e) {
            this.g.findNext(z);
        } else {
            this.f.findNext(z);
        }
    }

    public void flingScroll(int i, int i2) {
        if (!this.f1296e) {
            this.g.flingScroll(i, i2);
        } else {
            this.f.flingScroll(i, i2);
        }
    }

    @Deprecated
    public void freeMemory() {
        if (!this.f1296e) {
            i.a(this.g, "freeMemory");
        } else {
            this.f.freeMemory();
        }
    }

    public SslCertificate getCertificate() {
        return !this.f1296e ? this.g.getCertificate() : this.f.getCertificate();
    }

    public int getContentHeight() {
        return !this.f1296e ? this.g.getContentHeight() : this.f.getContentHeight();
    }

    public int getContentWidth() {
        if (this.f1296e) {
            return this.f.getContentWidth();
        }
        Object a2 = i.a(this.g, "getContentWidth");
        if (a2 == null) {
            return 0;
        }
        return ((Integer) a2).intValue();
    }

    public Bitmap getFavicon() {
        return !this.f1296e ? this.g.getFavicon() : this.f.getFavicon();
    }

    public HitTestResult getHitTestResult() {
        return !this.f1296e ? new HitTestResult(this.g.getHitTestResult()) : new HitTestResult(this.f.getHitTestResult());
    }

    public String[] getHttpAuthUsernamePassword(String str, String str2) {
        return !this.f1296e ? this.g.getHttpAuthUsernamePassword(str, str2) : this.f.getHttpAuthUsernamePassword(str, str2);
    }

    @TargetApi(3)
    public String getOriginalUrl() {
        return !this.f1296e ? this.g.getOriginalUrl() : this.f.getOriginalUrl();
    }

    public int getProgress() {
        return !this.f1296e ? this.g.getProgress() : this.f.getProgress();
    }

    public boolean getRendererPriorityWaivedWhenNotVisible() {
        Object a2;
        try {
            if (!this.f1296e && Build.VERSION.SDK_INT >= 26 && (a2 = i.a(this.g, "getRendererPriorityWaivedWhenNotVisible")) != null) {
                return ((Boolean) a2).booleanValue();
            }
            return false;
        } catch (Exception e2) {
            e2.printStackTrace();
            return false;
        }
    }

    public int getRendererRequestedPriority() {
        Object a2;
        try {
            if (!this.f1296e && Build.VERSION.SDK_INT >= 26 && (a2 = i.a(this.g, "getRendererRequestedPriority")) != null) {
                return ((Integer) a2).intValue();
            }
            return 0;
        } catch (Exception e2) {
            e2.printStackTrace();
            return 0;
        }
    }

    @Deprecated
    public float getScale() {
        if (this.f1296e) {
            return this.f.getScale();
        }
        Object a2 = i.a(this.g, "getScale");
        if (a2 == null) {
            return 0.0f;
        }
        return ((Float) a2).floatValue();
    }

    @Override // android.view.View
    public int getScrollBarDefaultDelayBeforeFade() {
        if (getView() == null) {
            return 0;
        }
        return getView().getScrollBarDefaultDelayBeforeFade();
    }

    @Override // android.view.View
    public int getScrollBarFadeDuration() {
        if (getView() == null) {
            return 0;
        }
        return getView().getScrollBarFadeDuration();
    }

    @Override // android.view.View
    public int getScrollBarSize() {
        if (getView() == null) {
            return 0;
        }
        return getView().getScrollBarSize();
    }

    @Override // android.view.View
    public int getScrollBarStyle() {
        if (getView() == null) {
            return 0;
        }
        return getView().getScrollBarStyle();
    }

    public WebSettings getSettings() {
        WebSettings webSettings = this.h;
        if (webSettings != null) {
            return webSettings;
        }
        WebSettings webSettings2 = this.f1296e ? new WebSettings(this.f.getSettings()) : new WebSettings(this.g.getSettings());
        this.h = webSettings2;
        return webSettings2;
    }

    public IX5WebSettingsExtension getSettingsExtension() {
        if (!this.f1296e) {
            return null;
        }
        return this.f.getX5WebViewExtension().getSettingsExtension();
    }

    public int getSysNightModeAlpha() {
        return NIGHT_MODE_ALPHA;
    }

    public String getTitle() {
        return !this.f1296e ? this.g.getTitle() : this.f.getTitle();
    }

    public String getUrl() {
        return !this.f1296e ? this.g.getUrl() : this.f.getUrl();
    }

    public View getView() {
        return !this.f1296e ? this.g : this.f.getView();
    }

    public int getVisibleTitleHeight() {
        if (this.f1296e) {
            return this.f.getVisibleTitleHeight();
        }
        Object a2 = i.a(this.g, "getVisibleTitleHeight");
        if (a2 == null) {
            return 0;
        }
        return ((Integer) a2).intValue();
    }

    public WebChromeClient getWebChromeClient() {
        return this.o;
    }

    public IX5WebChromeClientExtension getWebChromeClientExtension() {
        if (!this.f1296e) {
            return null;
        }
        return this.f.getX5WebViewExtension().getWebChromeClientExtension();
    }

    public int getWebScrollX() {
        return this.f1296e ? this.f.getView().getScrollX() : this.g.getScrollX();
    }

    public int getWebScrollY() {
        return this.f1296e ? this.f.getView().getScrollY() : this.g.getScrollY();
    }

    public WebViewClient getWebViewClient() {
        return this.n;
    }

    public IX5WebViewClientExtension getWebViewClientExtension() {
        if (!this.f1296e) {
            return null;
        }
        return this.f.getX5WebViewExtension().getWebViewClientExtension();
    }

    public IX5WebViewBase.HitTestResult getX5HitTestResult() {
        if (!this.f1296e) {
            return null;
        }
        return this.f.getHitTestResult();
    }

    public IX5WebViewExtension getX5WebViewExtension() {
        if (!this.f1296e) {
            return null;
        }
        return this.f.getX5WebViewExtension();
    }

    @Deprecated
    public View getZoomControls() {
        return !this.f1296e ? (View) i.a(this.g, "getZoomControls") : this.f.getZoomControls();
    }

    public void goBack() {
        if (!this.f1296e) {
            this.g.goBack();
        } else {
            this.f.goBack();
        }
    }

    public void goBackOrForward(int i) {
        if (!this.f1296e) {
            this.g.goBackOrForward(i);
        } else {
            this.f.goBackOrForward(i);
        }
    }

    public void goForward() {
        if (!this.f1296e) {
            this.g.goForward();
        } else {
            this.f.goForward();
        }
    }

    public void invokeZoomPicker() {
        if (!this.f1296e) {
            this.g.invokeZoomPicker();
        } else {
            this.f.invokeZoomPicker();
        }
    }

    public boolean isDayMode() {
        return w;
    }

    public boolean isPrivateBrowsingEnabled() {
        if (this.f1296e) {
            return this.f.isPrivateBrowsingEnable();
        }
        int i = Build.VERSION.SDK_INT;
        Object a2 = i.a(this.g, "isPrivateBrowsingEnabled");
        if (a2 == null) {
            return false;
        }
        return ((Boolean) a2).booleanValue();
    }

    public void loadData(String str, String str2, String str3) {
        if (!this.f1296e) {
            this.g.loadData(str, str2, str3);
        } else {
            this.f.loadData(str, str2, str3);
        }
    }

    public void loadDataWithBaseURL(String str, String str2, String str3, String str4, String str5) {
        if (!this.f1296e) {
            this.g.loadDataWithBaseURL(str, str2, str3, str4, str5);
        } else {
            this.f.loadDataWithBaseURL(str, str2, str3, str4, str5);
        }
    }

    public void loadUrl(String str) {
        boolean z = this.f1296e;
        if (str != null && !showDebugView(str)) {
            if (!this.f1296e) {
                this.g.loadUrl(str);
            } else {
                this.f.loadUrl(str);
            }
        }
    }

    @TargetApi(8)
    public void loadUrl(String str, Map<String, String> map) {
        boolean z = this.f1296e;
        if (str != null && !showDebugView(str)) {
            if (!this.f1296e) {
                int i = Build.VERSION.SDK_INT;
                this.g.loadUrl(str, map);
                return;
            }
            this.f.loadUrl(str, map);
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        try {
            if ("com.xunmeng.pinduoduo".equals(this.i.getApplicationInfo().packageName)) {
                new Thread("onDetachedFromWindow") { // from class: com.tencent.smtt.sdk.WebView.7
                    @Override // java.lang.Thread, java.lang.Runnable
                    public void run() {
                        try {
                            WebView.this.a();
                        } catch (Exception e2) {
                            e2.printStackTrace();
                        }
                    }
                }.start();
            } else {
                a();
            }
        } catch (Throwable unused) {
            a();
        }
    }

    @Override // android.view.View.OnLongClickListener
    public boolean onLongClick(View view) {
        View.OnLongClickListener onLongClickListener = this.y;
        if (onLongClickListener == null || !onLongClickListener.onLongClick(view)) {
            return a(view);
        }
        return true;
    }

    public void onPause() {
        if (!this.f1296e) {
            i.a(this.g, "onPause");
        } else {
            this.f.onPause();
        }
    }

    public void onResume() {
        if (!this.f1296e) {
            i.a(this.g, "onResume");
        } else {
            this.f.onResume();
        }
    }

    @Override // android.view.View
    @TargetApi(11)
    public void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        int i5 = Build.VERSION.SDK_INT;
        if (b(this.i) && isHardwareAccelerated() && i > 0 && i2 > 0) {
            getLayerType();
        }
    }

    @Override // android.view.View
    public void onVisibilityChanged(View view, int i) {
        String str;
        String str2;
        boolean z;
        Bundle sdkQBStatisticsInfo;
        Context context = this.i;
        if (context == null) {
            super.onVisibilityChanged(view, i);
            return;
        }
        if (p == null) {
            p = context.getApplicationInfo().packageName;
        }
        String str3 = p;
        if (str3 == null || (!str3.equals(TbsConfig.APP_WX) && !p.equals(TbsConfig.APP_QQ))) {
            if (!(i == 0 || this.k || this.f1294a == 0)) {
                this.k = true;
                boolean z2 = this.f1296e;
                String str4 = BuildConfig.FLAVOR;
                if (!z2 || (sdkQBStatisticsInfo = this.f.getX5WebViewExtension().getSdkQBStatisticsInfo()) == null) {
                    str2 = str4;
                    str = str2;
                } else {
                    str4 = sdkQBStatisticsInfo.getString("guid");
                    str2 = sdkQBStatisticsInfo.getString("qua2");
                    str = sdkQBStatisticsInfo.getString("lc");
                }
                if (TbsConfig.APP_QZONE.equals(this.i.getApplicationInfo().packageName)) {
                    int e2 = e(this.i);
                    if (e2 == -1) {
                        e2 = this.f1294a;
                    }
                    this.f1294a = e2;
                    f(this.i);
                }
                try {
                    z = this.f.getX5WebViewExtension().isX5CoreSandboxMode();
                } catch (Throwable th) {
                    TbsLog.w("onVisibilityChanged", "exception: " + th);
                    z = false;
                }
                b.a(this.i, str4, str2, str, this.f1294a, this.f1296e, h(), z);
                this.f1294a = 0;
                this.k = false;
            }
            super.onVisibilityChanged(view, i);
            return;
        }
        super.onVisibilityChanged(view, i);
    }

    public boolean overlayHorizontalScrollbar() {
        return !this.f1296e ? this.g.overlayHorizontalScrollbar() : this.f.overlayHorizontalScrollbar();
    }

    public boolean overlayVerticalScrollbar() {
        return this.f1296e ? this.f.overlayVerticalScrollbar() : this.g.overlayVerticalScrollbar();
    }

    public boolean pageDown(boolean z) {
        return !this.f1296e ? this.g.pageDown(z) : this.f.pageDown(z, -1);
    }

    public boolean pageUp(boolean z) {
        return !this.f1296e ? this.g.pageUp(z) : this.f.pageUp(z, -1);
    }

    public void pauseTimers() {
        if (!this.f1296e) {
            this.g.pauseTimers();
        } else {
            this.f.pauseTimers();
        }
    }

    @TargetApi(5)
    public void postUrl(String str, byte[] bArr) {
        if (!this.f1296e) {
            this.g.postUrl(str, bArr);
        } else {
            this.f.postUrl(str, bArr);
        }
    }

    @Deprecated
    public void refreshPlugins(boolean z) {
        if (!this.f1296e) {
            i.a(this.g, "refreshPlugins", new Class[]{Boolean.TYPE}, Boolean.valueOf(z));
        } else {
            this.f.refreshPlugins(z);
        }
    }

    public void reload() {
        if (!this.f1296e) {
            this.g.reload();
        } else {
            this.f.reload();
        }
    }

    @TargetApi(11)
    public void removeJavascriptInterface(String str) {
        if (!this.f1296e) {
            int i = Build.VERSION.SDK_INT;
            i.a(this.g, "removeJavascriptInterface", new Class[]{String.class}, str);
            return;
        }
        this.f.removeJavascriptInterface(str);
    }

    @Override // android.view.ViewGroup, android.view.ViewManager
    public void removeView(View view) {
        if (!this.f1296e) {
            this.g.removeView(view);
            return;
        }
        View view2 = this.f.getView();
        try {
            Method a2 = i.a(view2, "removeView", View.class);
            a2.setAccessible(true);
            a2.invoke(view2, view);
        } catch (Throwable unused) {
        }
    }

    public JSONObject reportInitPerformance(long j2, int i, long j3, long j4) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("IS_X5", this.f1296e);
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        return jSONObject;
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public boolean requestChildRectangleOnScreen(View view, Rect rect, boolean z) {
        if (this.f1296e) {
            View view2 = this.f.getView();
            if (!(view2 instanceof ViewGroup)) {
                return false;
            }
            ViewGroup viewGroup = (ViewGroup) view2;
            if (view == this) {
                view = view2;
            }
            return viewGroup.requestChildRectangleOnScreen(view, rect, z);
        }
        a aVar = this.g;
        if (view == this) {
            view = aVar;
        }
        return aVar.requestChildRectangleOnScreen(view, rect, z);
    }

    public void requestFocusNodeHref(Message message) {
        if (!this.f1296e) {
            this.g.requestFocusNodeHref(message);
        } else {
            this.f.requestFocusNodeHref(message);
        }
    }

    public void requestImageRef(Message message) {
        if (!this.f1296e) {
            this.g.requestImageRef(message);
        } else {
            this.f.requestImageRef(message);
        }
    }

    @Deprecated
    public boolean restorePicture(Bundle bundle, File file) {
        if (this.f1296e) {
            return this.f.restorePicture(bundle, file);
        }
        Object a2 = i.a(this.g, "restorePicture", new Class[]{Bundle.class, File.class}, bundle, file);
        if (a2 == null) {
            return false;
        }
        return ((Boolean) a2).booleanValue();
    }

    public WebBackForwardList restoreState(Bundle bundle) {
        return !this.f1296e ? WebBackForwardList.a(this.g.restoreState(bundle)) : WebBackForwardList.a(this.f.restoreState(bundle));
    }

    public void resumeTimers() {
        if (!this.f1296e) {
            this.g.resumeTimers();
        } else {
            this.f.resumeTimers();
        }
    }

    @Deprecated
    public void savePassword(String str, String str2, String str3) {
        if (!this.f1296e) {
            i.a(this.g, "savePassword", new Class[]{String.class, String.class, String.class}, str, str2, str3);
        } else {
            this.f.savePassword(str, str2, str3);
        }
    }

    @Deprecated
    public boolean savePicture(Bundle bundle, File file) {
        if (this.f1296e) {
            return this.f.savePicture(bundle, file);
        }
        Object a2 = i.a(this.g, "savePicture", new Class[]{Bundle.class, File.class}, bundle, file);
        if (a2 == null) {
            return false;
        }
        return ((Boolean) a2).booleanValue();
    }

    public WebBackForwardList saveState(Bundle bundle) {
        return !this.f1296e ? WebBackForwardList.a(this.g.saveState(bundle)) : WebBackForwardList.a(this.f.saveState(bundle));
    }

    @TargetApi(11)
    public void saveWebArchive(String str) {
        if (!this.f1296e) {
            int i = Build.VERSION.SDK_INT;
            i.a(this.g, "saveWebArchive", new Class[]{String.class}, str);
            return;
        }
        this.f.saveWebArchive(str);
    }

    @TargetApi(11)
    public void saveWebArchive(String str, boolean z, ValueCallback<String> valueCallback) {
        if (!this.f1296e) {
            int i = Build.VERSION.SDK_INT;
            i.a(this.g, "saveWebArchive", new Class[]{String.class, Boolean.TYPE, ValueCallback.class}, str, Boolean.valueOf(z), valueCallback);
            return;
        }
        this.f.saveWebArchive(str, z, valueCallback);
    }

    public void setARModeEnable(boolean z) {
        try {
            if (this.f1296e) {
                getSettingsExtension().setARModeEnable(z);
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    @Override // android.view.View
    public void setBackgroundColor(int i) {
        if (!this.f1296e) {
            this.g.setBackgroundColor(i);
        } else {
            this.f.setBackgroundColor(i);
        }
        super.setBackgroundColor(i);
    }

    @Deprecated
    public void setCertificate(SslCertificate sslCertificate) {
        if (!this.f1296e) {
            this.g.setCertificate(sslCertificate);
        } else {
            this.f.setCertificate(sslCertificate);
        }
    }

    public void setDayOrNight(boolean z) {
        try {
            if (this.f1296e) {
                getSettingsExtension().setDayOrNight(z);
            }
            setSysDayOrNight(z);
            getView().postInvalidate();
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public void setDownloadListener(final DownloadListener downloadListener) {
        boolean z = this.f1296e;
        if (!z) {
            this.g.setDownloadListener(new DownloadListener() { // from class: com.tencent.smtt.sdk.WebView.4
                @Override // android.webkit.DownloadListener
                public void onDownloadStart(String str, String str2, String str3, String str4, long j2) {
                    DownloadListener downloadListener2 = downloadListener;
                    if (downloadListener2 == null) {
                        ApplicationInfo applicationInfo = WebView.this.i == null ? null : WebView.this.i.getApplicationInfo();
                        if (applicationInfo == null || !TbsConfig.APP_WX.equals(applicationInfo.packageName)) {
                            MttLoader.loadUrl(WebView.this.i, str, null, null);
                            return;
                        }
                        return;
                    }
                    downloadListener2.onDownloadStart(str, str2, str3, str4, j2);
                }
            });
        } else {
            this.f.setDownloadListener(new b(this, downloadListener, z));
        }
    }

    @TargetApi(16)
    public void setFindListener(final IX5WebViewBase.FindListener findListener) {
        if (!this.f1296e) {
            int i = Build.VERSION.SDK_INT;
            this.g.setFindListener(new WebView.FindListener() { // from class: com.tencent.smtt.sdk.WebView.3
                @Override // android.webkit.WebView.FindListener
                public void onFindResultReceived(int i2, int i3, boolean z) {
                    findListener.onFindResultReceived(i2, i3, z);
                }
            });
            return;
        }
        this.f.setFindListener(findListener);
    }

    public void setHorizontalScrollbarOverlay(boolean z) {
        if (!this.f1296e) {
            this.g.setHorizontalScrollbarOverlay(z);
        } else {
            this.f.setHorizontalScrollbarOverlay(z);
        }
    }

    public void setHttpAuthUsernamePassword(String str, String str2, String str3, String str4) {
        if (!this.f1296e) {
            this.g.setHttpAuthUsernamePassword(str, str2, str3, str4);
        } else {
            this.f.setHttpAuthUsernamePassword(str, str2, str3, str4);
        }
    }

    public void setInitialScale(int i) {
        if (!this.f1296e) {
            this.g.setInitialScale(i);
        } else {
            this.f.setInitialScale(i);
        }
    }

    @Deprecated
    public void setMapTrackballToArrowKeys(boolean z) {
        if (!this.f1296e) {
            i.a(this.g, "setMapTrackballToArrowKeys", new Class[]{Boolean.TYPE}, Boolean.valueOf(z));
        } else {
            this.f.setMapTrackballToArrowKeys(z);
        }
    }

    public void setNetworkAvailable(boolean z) {
        if (!this.f1296e) {
            int i = Build.VERSION.SDK_INT;
            this.g.setNetworkAvailable(z);
            return;
        }
        this.f.setNetworkAvailable(z);
    }

    @Override // android.view.View
    public void setOnLongClickListener(View.OnLongClickListener onLongClickListener) {
        if (!this.f1296e) {
            this.g.setOnLongClickListener(onLongClickListener);
            return;
        }
        View view = this.f.getView();
        try {
            if (this.x == null) {
                Method a2 = i.a(view, "getListenerInfo", new Class[0]);
                a2.setAccessible(true);
                Object invoke = a2.invoke(view, null);
                Field declaredField = invoke.getClass().getDeclaredField("mOnLongClickListener");
                declaredField.setAccessible(true);
                this.x = declaredField.get(invoke);
            }
            this.y = onLongClickListener;
            getView().setOnLongClickListener(this);
        } catch (Throwable unused) {
        }
    }

    @Override // android.view.View
    public void setOnTouchListener(View.OnTouchListener onTouchListener) {
        getView().setOnTouchListener(onTouchListener);
    }

    public void setPictureListener(final PictureListener pictureListener) {
        if (!this.f1296e) {
            if (pictureListener == null) {
                this.g.setPictureListener(null);
            } else {
                this.g.setPictureListener(new WebView.PictureListener() { // from class: com.tencent.smtt.sdk.WebView.5
                    @Override // android.webkit.WebView.PictureListener
                    public void onNewPicture(android.webkit.WebView webView, Picture picture) {
                        WebView.this.a(webView);
                        pictureListener.onNewPicture(WebView.this, picture);
                    }
                });
            }
        } else if (pictureListener == null) {
            this.f.setPictureListener(null);
        } else {
            this.f.setPictureListener(new IX5WebViewBase.PictureListener() { // from class: com.tencent.smtt.sdk.WebView.6
                @Override // com.tencent.smtt.export.external.interfaces.IX5WebViewBase.PictureListener
                public void onNewPicture(IX5WebViewBase iX5WebViewBase, Picture picture, boolean z) {
                    WebView.this.a(iX5WebViewBase);
                    pictureListener.onNewPicture(WebView.this, picture);
                }

                @Override // com.tencent.smtt.export.external.interfaces.IX5WebViewBase.PictureListener
                public void onNewPictureIfHaveContent(IX5WebViewBase iX5WebViewBase, Picture picture) {
                }
            });
        }
    }

    public void setRendererPriorityPolicy(int i, boolean z) {
        try {
            if (!this.f1296e && Build.VERSION.SDK_INT >= 26) {
                i.a(this.g, "setRendererPriorityPolicy", new Class[]{Integer.TYPE, Boolean.TYPE}, Integer.valueOf(i), Boolean.valueOf(z));
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    @Override // android.view.View
    public void setScrollBarStyle(int i) {
        if (this.f1296e) {
            this.f.getView().setScrollBarStyle(i);
        } else {
            this.g.setScrollBarStyle(i);
        }
    }

    public void setSysNightModeAlpha(int i) {
        NIGHT_MODE_ALPHA = i;
    }

    public void setVerticalScrollbarOverlay(boolean z) {
        if (!this.f1296e) {
            this.g.setVerticalScrollbarOverlay(z);
        } else {
            this.f.setVerticalScrollbarOverlay(z);
        }
    }

    public boolean setVideoFullScreen(Context context, boolean z) {
        if (!context.getApplicationInfo().processName.contains("com.tencent.android.qqdownloader") || this.f == null) {
            return false;
        }
        Bundle bundle = new Bundle();
        if (z) {
            bundle.putInt("DefaultVideoScreen", 2);
        } else {
            bundle.putInt("DefaultVideoScreen", 1);
        }
        this.f.getX5WebViewExtension().invokeMiscMethod("setVideoParams", bundle);
        return true;
    }

    @Override // android.view.View
    public void setVisibility(int i) {
        super.setVisibility(i);
        if (getView() != null) {
            getView().setVisibility(i);
        }
    }

    public void setWebChromeClient(WebChromeClient webChromeClient) {
        SystemWebChromeClient systemWebChromeClient = null;
        f fVar = null;
        if (this.f1296e) {
            IX5WebViewBase iX5WebViewBase = this.f;
            if (webChromeClient != null) {
                fVar = new f(u.a().a(true).i(), this, webChromeClient);
            }
            iX5WebViewBase.setWebChromeClient(fVar);
        } else {
            a aVar = this.g;
            if (webChromeClient != null) {
                systemWebChromeClient = new SystemWebChromeClient(this, webChromeClient);
            }
            aVar.setWebChromeClient(systemWebChromeClient);
        }
        this.o = webChromeClient;
    }

    public void setWebChromeClientExtension(IX5WebChromeClientExtension iX5WebChromeClientExtension) {
        if (this.f1296e) {
            this.f.getX5WebViewExtension().setWebChromeClientExtension(iX5WebChromeClientExtension);
        }
    }

    public void setWebViewCallbackClient(WebViewCallbackClient webViewCallbackClient) {
        this.mWebViewCallbackClient = webViewCallbackClient;
        if (this.f1296e && getX5WebViewExtension() != null) {
            Bundle bundle = new Bundle();
            bundle.putBoolean("flag", true);
            getX5WebViewExtension().invokeMiscMethod("setWebViewCallbackClientFlag", bundle);
        }
    }

    public void setWebViewClient(WebViewClient webViewClient) {
        SystemWebViewClient systemWebViewClient = null;
        g gVar = null;
        if (this.f1296e) {
            IX5WebViewBase iX5WebViewBase = this.f;
            if (webViewClient != null) {
                gVar = new g(u.a().a(true).j(), this, webViewClient);
            }
            iX5WebViewBase.setWebViewClient(gVar);
        } else {
            a aVar = this.g;
            if (webViewClient != null) {
                systemWebViewClient = new SystemWebViewClient(this, webViewClient);
            }
            aVar.setWebViewClient(systemWebViewClient);
        }
        this.n = webViewClient;
    }

    public void setWebViewClientExtension(IX5WebViewClientExtension iX5WebViewClientExtension) {
        if (this.f1296e) {
            this.f.getX5WebViewExtension().setWebViewClientExtension(iX5WebViewClientExtension);
        }
    }

    @SuppressLint({"NewApi"})
    public boolean showDebugView(String str) {
        String lowerCase = str.toLowerCase();
        if (lowerCase.startsWith("https://debugtbs.qq.com")) {
            getView().setVisibility(4);
            d.a(this.i).a(lowerCase, this, this.i, l.a().getLooper());
            return true;
        } else if (!lowerCase.startsWith("https://debugx5.qq.com") || this.f1296e) {
            return false;
        } else {
            loadDataWithBaseURL(null, "<!DOCTYPE html><html><body><head><title>无法打开debugx5</title><meta name=\"viewport\" content=\"width=device-width, user-scalable=no\" /></head><br/><br /><h2>debugx5页面仅在使用了X5内核时有效，由于当前没有使用X5内核，无法打开debugx5！</h2><br />尝试<a href=\"http://debugtbs.qq.com?10000\">进入DebugTbs安装或打开X5内核</a></body></html>", "text/html", "utf-8", null);
            return true;
        }
    }

    public boolean showFindDialog(String str, boolean z) {
        return false;
    }

    public void stopLoading() {
        if (!this.f1296e) {
            this.g.stopLoading();
        } else {
            this.f.stopLoading();
        }
    }

    public void super_computeScroll() {
        if (!this.f1296e) {
            this.g.a();
            return;
        }
        try {
            i.a(this.f.getView(), "super_computeScroll");
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public boolean super_dispatchTouchEvent(MotionEvent motionEvent) {
        if (!this.f1296e) {
            return this.g.b(motionEvent);
        }
        try {
            Object a2 = i.a(this.f.getView(), "super_dispatchTouchEvent", new Class[]{MotionEvent.class}, motionEvent);
            if (a2 == null) {
                return false;
            }
            return ((Boolean) a2).booleanValue();
        } catch (Throwable unused) {
            return false;
        }
    }

    public boolean super_onInterceptTouchEvent(MotionEvent motionEvent) {
        if (!this.f1296e) {
            return this.g.c(motionEvent);
        }
        try {
            Object a2 = i.a(this.f.getView(), "super_onInterceptTouchEvent", new Class[]{MotionEvent.class}, motionEvent);
            if (a2 == null) {
                return false;
            }
            return ((Boolean) a2).booleanValue();
        } catch (Throwable unused) {
            return false;
        }
    }

    public void super_onOverScrolled(int i, int i2, boolean z, boolean z2) {
        if (!this.f1296e) {
            this.g.a(i, i2, z, z2);
            return;
        }
        try {
            i.a(this.f.getView(), "super_onOverScrolled", new Class[]{Integer.TYPE, Integer.TYPE, Boolean.TYPE, Boolean.TYPE}, Integer.valueOf(i), Integer.valueOf(i2), Boolean.valueOf(z), Boolean.valueOf(z2));
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public void super_onScrollChanged(int i, int i2, int i3, int i4) {
        if (!this.f1296e) {
            this.g.a(i, i2, i3, i4);
            return;
        }
        try {
            i.a(this.f.getView(), "super_onScrollChanged", new Class[]{Integer.TYPE, Integer.TYPE, Integer.TYPE, Integer.TYPE}, Integer.valueOf(i), Integer.valueOf(i2), Integer.valueOf(i3), Integer.valueOf(i4));
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public boolean super_onTouchEvent(MotionEvent motionEvent) {
        if (!this.f1296e) {
            return this.g.a(motionEvent);
        }
        try {
            Object a2 = i.a(this.f.getView(), "super_onTouchEvent", new Class[]{MotionEvent.class}, motionEvent);
            if (a2 == null) {
                return false;
            }
            return ((Boolean) a2).booleanValue();
        } catch (Throwable unused) {
            return false;
        }
    }

    public boolean super_overScrollBy(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, boolean z) {
        if (!this.f1296e) {
            return this.g.a(i, i2, i3, i4, i5, i6, i7, i8, z);
        }
        try {
            Object a2 = i.a(this.f.getView(), "super_overScrollBy", new Class[]{Integer.TYPE, Integer.TYPE, Integer.TYPE, Integer.TYPE, Integer.TYPE, Integer.TYPE, Integer.TYPE, Integer.TYPE, Boolean.TYPE}, Integer.valueOf(i), Integer.valueOf(i2), Integer.valueOf(i3), Integer.valueOf(i4), Integer.valueOf(i5), Integer.valueOf(i6), Integer.valueOf(i7), Integer.valueOf(i8), Boolean.valueOf(z));
            if (a2 == null) {
                return false;
            }
            return ((Boolean) a2).booleanValue();
        } catch (Throwable unused) {
            return false;
        }
    }

    public void switchNightMode(boolean z) {
        String str;
        if (z != w) {
            w = z;
            if (w) {
                TbsLog.e("QB_SDK", "deleteNightMode");
                str = "javascript:document.getElementsByTagName('HEAD').item(0).removeChild(document.getElementById('QQBrowserSDKNightMode'));";
            } else {
                TbsLog.e("QB_SDK", "nightMode");
                str = "javascript:var style = document.createElement('style');style.type='text/css';style.id='QQBrowserSDKNightMode';style.innerHTML='html,body{background:none !important;background-color: #1d1e2a !important;}html *{background-color: #1d1e2a !important; color:#888888 !important;border-color:#3e4f61 !important;text-shadow:none !important;box-shadow:none !important;}a,a *{border-color:#4c5b99 !important; color:#2d69b3 !important;text-decoration:none !important;}a:visited,a:visited *{color:#a600a6 !important;}a:active,a:active *{color:#5588AA !important;}input,select,textarea,option,button{background-image:none !important;color:#AAAAAA !important;border-color:#4c5b99 !important;}form,div,button,span{background-color:#1d1e2a !important; border-color:#4c5b99 !important;}img{opacity:0.5}';document.getElementsByTagName('HEAD').item(0).appendChild(style);";
            }
            loadUrl(str);
        }
    }

    public void switchToNightMode() {
        TbsLog.e("QB_SDK", "switchToNightMode 01");
        if (!w) {
            TbsLog.e("QB_SDK", "switchToNightMode");
            loadUrl("javascript:var style = document.createElement('style');style.type='text/css';style.id='QQBrowserSDKNightMode';style.innerHTML='html,body{background:none !important;background-color: #1d1e2a !important;}html *{background-color: #1d1e2a !important; color:#888888 !important;border-color:#3e4f61 !important;text-shadow:none !important;box-shadow:none !important;}a,a *{border-color:#4c5b99 !important; color:#2d69b3 !important;text-decoration:none !important;}a:visited,a:visited *{color:#a600a6 !important;}a:active,a:active *{color:#5588AA !important;}input,select,textarea,option,button{background-image:none !important;color:#AAAAAA !important;border-color:#4c5b99 !important;}form,div,button,span{background-color:#1d1e2a !important; border-color:#4c5b99 !important;}img{opacity:0.5}';document.getElementsByTagName('HEAD').item(0).appendChild(style);");
        }
    }

    public void tbsWebviewDestroy(boolean z) {
        String str;
        String str2;
        String str3;
        boolean z2;
        Bundle sdkQBStatisticsInfo;
        if (!this.k && this.f1294a != 0) {
            this.k = true;
            if (!this.f1296e || (sdkQBStatisticsInfo = this.f.getX5WebViewExtension().getSdkQBStatisticsInfo()) == null) {
                str2 = BuildConfig.FLAVOR;
                str = BuildConfig.FLAVOR;
                str3 = BuildConfig.FLAVOR;
            } else {
                str2 = sdkQBStatisticsInfo.getString("guid");
                str = sdkQBStatisticsInfo.getString("qua2");
                str3 = sdkQBStatisticsInfo.getString("lc");
            }
            if (TbsConfig.APP_QZONE.equals(this.i.getApplicationInfo().packageName)) {
                int e2 = e(this.i);
                if (e2 == -1) {
                    e2 = this.f1294a;
                }
                this.f1294a = e2;
                f(this.i);
            }
            try {
                z2 = this.f.getX5WebViewExtension().isX5CoreSandboxMode();
            } catch (Throwable th) {
                TbsLog.w("tbsWebviewDestroy", "exception: " + th);
                z2 = false;
            }
            b.a(this.i, str2, str, str3, this.f1294a, this.f1296e, h(), z2);
            this.f1294a = 0;
            this.k = false;
        }
        if (!this.f1296e) {
            try {
                Class<?> cls = Class.forName("android.webkit.WebViewClassic");
                Method method = cls.getMethod("fromWebView", android.webkit.WebView.class);
                method.setAccessible(true);
                Object invoke = method.invoke(null, this.g);
                if (invoke != null) {
                    Field declaredField = cls.getDeclaredField("mListBoxDialog");
                    declaredField.setAccessible(true);
                    Object obj = declaredField.get(invoke);
                    if (obj != null) {
                        Dialog dialog = (Dialog) obj;
                        dialog.setOnCancelListener(null);
                        Class<?> cls2 = Class.forName("android.app.Dialog");
                        Field declaredField2 = cls2.getDeclaredField("CANCEL");
                        declaredField2.setAccessible(true);
                        int intValue = ((Integer) declaredField2.get(dialog)).intValue();
                        Field declaredField3 = cls2.getDeclaredField("mListenersHandler");
                        declaredField3.setAccessible(true);
                        ((Handler) declaredField3.get(dialog)).removeMessages(intValue);
                    }
                }
            } catch (Exception unused) {
            }
            if (z) {
                this.g.destroy();
            }
            try {
                Field declaredField4 = Class.forName("android.webkit.BrowserFrame").getDeclaredField("sConfigCallback");
                declaredField4.setAccessible(true);
                ComponentCallbacks componentCallbacks = (ComponentCallbacks) declaredField4.get(null);
                if (componentCallbacks != null) {
                    declaredField4.set(null, null);
                    Field declaredField5 = Class.forName("android.view.ViewRoot").getDeclaredField("sConfigCallbacks");
                    declaredField5.setAccessible(true);
                    Object obj2 = declaredField5.get(null);
                    if (obj2 != null) {
                        List list = (List) obj2;
                        synchronized (list) {
                            list.remove(componentCallbacks);
                        }
                    }
                }
            } catch (Exception unused2) {
            }
        } else if (z) {
            this.f.destroy();
        }
        StringBuilder a2 = e.a.a.a.a.a("X5 GUID = ");
        a2.append(QbSdk.b());
        TbsLog.i("WebView", a2.toString());
    }

    public boolean zoomIn() {
        return !this.f1296e ? this.g.zoomIn() : this.f.zoomIn();
    }

    public boolean zoomOut() {
        return !this.f1296e ? this.g.zoomOut() : this.f.zoomOut();
    }

    public WebView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0, false);
    }

    @TargetApi(11)
    public WebView(Context context, AttributeSet attributeSet, int i, Map<String, Object> map, boolean z) {
        super(context, attributeSet, i);
        this.f1295b = "WebView";
        this.f1296e = false;
        this.h = null;
        this.i = null;
        this.f1294a = 0;
        this.k = false;
        this.n = null;
        this.o = null;
        this.q = 1;
        this.r = 2;
        this.s = 3;
        this.t = "javascript:document.getElementsByTagName('HEAD').item(0).removeChild(document.getElementById('QQBrowserSDKNightMode'));";
        this.u = "javascript:var style = document.createElement('style');style.type='text/css';style.id='QQBrowserSDKNightMode';style.innerHTML='html,body{background:none !important;background-color: #1d1e2a !important;}html *{background-color: #1d1e2a !important; color:#888888 !important;border-color:#3e4f61 !important;text-shadow:none !important;box-shadow:none !important;}a,a *{border-color:#4c5b99 !important; color:#2d69b3 !important;text-decoration:none !important;}a:visited,a:visited *{color:#a600a6 !important;}a:active,a:active *{color:#5588AA !important;}input,select,textarea,option,button{background-image:none !important;color:#AAAAAA !important;border-color:#4c5b99 !important;}form,div,button,span{background-color:#1d1e2a !important; border-color:#4c5b99 !important;}img{opacity:0.5}';document.getElementsByTagName('HEAD').item(0).appendChild(style);";
        this.x = null;
        this.y = null;
        mWebViewCreated = true;
        if (!QbSdk.f1128b || !TbsShareManager.isThirdPartyApp(context)) {
            if (TbsShareManager.isThirdPartyApp(context)) {
                TbsLog.setWriteLogJIT(true);
            } else {
                TbsLog.setWriteLogJIT(false);
            }
            TbsLog.initIfNeed(context);
            if (context != null) {
                if (l == null) {
                    l = n.a(context);
                }
                if (l.f1586a) {
                    TbsLog.e("WebView", "sys WebView: debug.conf force syswebview", true);
                    QbSdk.a(context, "debug.conf force syswebview!");
                }
                c(context);
                this.i = context;
                j = context.getApplicationContext();
                if (!this.f1296e || QbSdk.f1127a) {
                    this.f = null;
                    if (TbsShareManager.isThirdPartyApp(this.i)) {
                        this.g = new a(context, attributeSet);
                    } else {
                        this.g = new a(context, null);
                    }
                    TbsLog.i("WebView", "SystemWebView Created Success! #2");
                    CookieManager.getInstance().a(context, true, false);
                    CookieManager.getInstance().a();
                    this.g.setFocusableInTouchMode(true);
                    addView(this.g, new FrameLayout.LayoutParams(-1, -1));
                    setDownloadListener(null);
                    TbsLog.writeLogToDisk();
                    m.a(context);
                } else {
                    this.f = u.a().a(true).a(context);
                    IX5WebViewBase iX5WebViewBase = this.f;
                    if (iX5WebViewBase == null || iX5WebViewBase.getView() == null) {
                        TbsLog.e("WebView", "sys WebView: failed to createTBSWebview", true);
                        this.f = null;
                        this.f1296e = false;
                        QbSdk.a(context, "failed to createTBSWebview!");
                        c(context);
                        if (TbsShareManager.isThirdPartyApp(this.i)) {
                            this.g = new a(context, attributeSet);
                        } else {
                            this.g = new a(context, null);
                        }
                        TbsLog.i("WebView", "SystemWebView Created Success! #1");
                        CookieManager.getInstance().a(context, true, false);
                        CookieManager.getInstance().a();
                        this.g.setFocusableInTouchMode(true);
                        addView(this.g, new FrameLayout.LayoutParams(-1, -1));
                        try {
                            int i2 = Build.VERSION.SDK_INT;
                            removeJavascriptInterface("searchBoxJavaBridge_");
                            removeJavascriptInterface("accessibility");
                            removeJavascriptInterface("accessibilityTraversal");
                        } catch (Throwable th) {
                            th.printStackTrace();
                        }
                        TbsLog.writeLogToDisk();
                        m.a(context);
                        return;
                    }
                    TbsLog.i("WebView", "X5 WebView Created Success!!");
                    this.f.getView().setFocusableInTouchMode(true);
                    a(attributeSet);
                    addView(this.f.getView(), new FrameLayout.LayoutParams(-1, -1));
                    this.f.setDownloadListener(new b(this, null, this.f1296e));
                    this.f.getX5WebViewExtension().setWebViewClientExtension(new X5ProxyWebViewClientExtension(u.a().a(true).k()) { // from class: com.tencent.smtt.sdk.WebView.1
                        @Override // com.tencent.smtt.export.external.extension.proxy.ProxyWebViewClientExtension, com.tencent.smtt.export.external.extension.interfaces.IX5WebViewClientExtension
                        public void invalidate() {
                        }

                        @Override // com.tencent.smtt.export.external.extension.proxy.ProxyWebViewClientExtension, com.tencent.smtt.export.external.extension.interfaces.IX5WebViewClientExtension
                        public void onScrollChanged(int i3, int i4, int i5, int i6) {
                            IX5WebViewClientExtension iX5WebViewClientExtension = this.mWebViewClientExt;
                            if (iX5WebViewClientExtension != null) {
                                iX5WebViewClientExtension.onScrollChanged(i3, i4, i5, i6);
                            }
                            WebView.this.onScrollChanged(i5, i6, i3, i4);
                        }
                    });
                }
                try {
                    int i3 = Build.VERSION.SDK_INT;
                    removeJavascriptInterface("searchBoxJavaBridge_");
                    removeJavascriptInterface("accessibility");
                    removeJavascriptInterface("accessibilityTraversal");
                } catch (Throwable th2) {
                    th2.printStackTrace();
                }
                if ((TbsConfig.APP_QQ.equals(this.i.getApplicationInfo().packageName) || TbsConfig.APP_WX.equals(this.i.getApplicationInfo().packageName)) && d.a(true).h()) {
                    int i4 = Build.VERSION.SDK_INT;
                    setLayerType(1, null);
                }
                if (this.f != null) {
                    TbsLog.writeLogToDisk();
                    if (!TbsShareManager.isThirdPartyApp(context)) {
                        int i5 = TbsDownloadConfig.getInstance(context).mPreferences.getInt(TbsDownloadConfig.TbsConfigKey.KEY_DECOUPLECOREVERSION, 0);
                        if (i5 <= 0 || i5 == m.a().h(context) || i5 != m.a().i(context)) {
                            TbsLog.i("WebView", "webview construction #1 deCoupleCoreVersion is " + i5 + " getTbsCoreShareDecoupleCoreVersion is " + m.a().h(context) + " getTbsCoreInstalledVerInNolock is " + m.a().i(context));
                        } else {
                            m.a().n(context);
                        }
                    }
                }
                QbSdk.continueLoadSo(context);
                return;
            }
            throw new IllegalArgumentException("Invalid context argument");
        }
        this.i = context;
        this.f = null;
        this.f1296e = false;
        QbSdk.a(context, "failed to createTBSWebview!");
        this.g = new a(context, attributeSet);
        CookieManager.getInstance().a(context, true, false);
        CookieSyncManager.createInstance(this.i).startSync();
        try {
            Method declaredMethod = Class.forName("android.webkit.WebViewWorker").getDeclaredMethod("getHandler", new Class[0]);
            declaredMethod.setAccessible(true);
            ((Handler) declaredMethod.invoke(null, new Object[0])).getLooper().getThread().setUncaughtExceptionHandler(new e());
            mSysWebviewCreated = true;
        } catch (Exception unused) {
        }
        CookieManager.getInstance().a();
        this.g.setFocusableInTouchMode(true);
        addView(this.g, new FrameLayout.LayoutParams(-1, -1));
        TbsLog.i("WebView", "SystemWebView Created Success! #3");
        TbsLog.e("WebView", "sys WebView: IsSysWebViewForcedByOuter = true", true);
        TbsCoreLoadStat.getInstance().a(context, TbsListener.ErrorCode.INFO_FORCE_SYSTEM_WEBVIEW_OUTER, new Throwable());
    }
}
