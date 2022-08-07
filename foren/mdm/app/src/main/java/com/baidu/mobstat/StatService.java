package com.baidu.mobstat;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/* loaded from: classes.dex */
public class StatService {
    public static final int EXCEPTION_LOG = 1;
    private static boolean a = false;
    private static long b;

    /* loaded from: classes.dex */
    public interface WearListener {
        boolean onSendLogData(String str);
    }

    private static void a(Context context) {
        if (a(context, "onError(...)")) {
            bn.a().a(context.getApplicationContext());
            bs.a().a(true, context.getApplicationContext());
        }
    }

    private static boolean a(Context context, String str) {
        if (context != null) {
            return true;
        }
        cr.b(str + ":context=null");
        return false;
    }

    static boolean a(Class<?> cls, String str) {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        boolean z = false;
        for (int i = 2; i < stackTrace.length; i++) {
            StackTraceElement stackTraceElement = stackTrace[i];
            if (stackTraceElement.getMethodName().equals(str)) {
                try {
                    for (Class<?> cls2 = Class.forName(stackTraceElement.getClassName()); cls2.getSuperclass() != null && cls2.getSuperclass() != cls; cls2 = cls2.getSuperclass()) {
                    }
                    z = true;
                } catch (Exception e) {
                    cr.a(e);
                }
            }
        }
        return z;
    }

    private static void b(Context context) {
        try {
            bp.a().a(context.getApplicationContext());
        } catch (Throwable th) {
        }
        bp.a().b(context.getApplicationContext());
    }

    public static void bindJSInterface(Context context, WebView webView) {
        bindJSInterface(context, webView, null);
    }

    @SuppressLint({"SetJavaScriptEnabled"})
    public static void bindJSInterface(Context context, WebView webView, WebViewClient webViewClient) {
        if (context == null) {
            throw new IllegalArgumentException("context can't be null.");
        } else if (webView == null) {
            throw new IllegalArgumentException("webview can't be null.");
        } else {
            WebSettings settings = webView.getSettings();
            settings.setJavaScriptEnabled(true);
            settings.setDefaultTextEncodingName("UTF-8");
            settings.setJavaScriptCanOpenWindowsAutomatically(true);
            webView.setWebViewClient(new bd(context, webViewClient));
        }
    }

    public static String getAppKey(Context context) {
        return DataCore.instance().getAppKey(context);
    }

    public static String getSdkVersion() {
        return CooperService.a().getMTJSDKVersion();
    }

    public static void onErised(Context context, String str, String str2, String str3) {
        if (bp.a().b() || !a(context, "onErised(...)")) {
            return;
        }
        if (str == null || "".equals(str)) {
            cr.c("AppKey is invalid");
            return;
        }
        bp.a().c(context);
        long currentTimeMillis = System.currentTimeMillis();
        bh.a().a(context, str2, str3, 1, currentTimeMillis, 0L);
        if (currentTimeMillis - b > 30000 && cu.m(context)) {
            bs.a().b(context);
            b = currentTimeMillis;
        }
    }

    public static void onEvent(Context context, String str, String str2) {
        onEvent(context, str, str2, 1);
    }

    public static void onEvent(Context context, String str, String str2, int i) {
        if (a(context, "onEvent(...)") && str != null && !str.equals("")) {
            b(context);
            bh.a().a(context.getApplicationContext(), str, str2, i, System.currentTimeMillis());
        }
    }

    public static void onEventDuration(Context context, String str, String str2, long j) {
        if (!a(context, "onEventDuration(...)") || str == null || str.equals("")) {
            return;
        }
        if (j <= 0) {
            cr.b("onEventDuration: duration must be greater than zero");
            return;
        }
        b(context);
        bh.a().c(context.getApplicationContext(), str, str2, j);
    }

    public static void onEventEnd(Context context, String str, String str2) {
        if (a(context, "onEventEnd(...)") && str != null && !str.equals("")) {
            b(context);
            bh.a().b(context.getApplicationContext(), str, str2, System.currentTimeMillis());
        }
    }

    public static void onEventStart(Context context, String str, String str2) {
        if (a(context, "onEventStart(...)") && str != null && !str.equals("")) {
            b(context);
            bh.a().a(context.getApplicationContext(), str, str2, System.currentTimeMillis());
        }
    }

    public static synchronized void onPageEnd(Context context, String str) {
        synchronized (StatService.class) {
            if (!(context == null || str == null)) {
                if (!str.equals("")) {
                    cr.a("*******onPageEnd=" + str);
                    ca.a().b(context, System.currentTimeMillis(), str);
                }
            }
            cr.c("onPageEnd :parame=null || empty");
        }
    }

    public static synchronized void onPageStart(Context context, String str) {
        synchronized (StatService.class) {
            if (!(context == null || str == null)) {
                if (!str.equals("")) {
                    cr.a("*******onPageStart=" + str);
                    b(context);
                    ca.a().a(context, System.currentTimeMillis(), str);
                }
            }
            cr.c("onPageStart :parame=null || empty");
        }
    }

    public static synchronized void onPause(Context context) {
        synchronized (StatService.class) {
            if (a(context, "onPause(...)")) {
                if (!a(Activity.class, "onPause")) {
                    throw new SecurityException("onPause(Context context)不在Activity.onPause()中被调用||onPause(Context context)is not called in Activity.onPause().");
                }
                ca.a().b(context, System.currentTimeMillis());
            }
        }
    }

    @Deprecated
    public static synchronized void onPause(Fragment fragment) {
        synchronized (StatService.class) {
            if (fragment == null) {
                cr.c("onResume :parame=null");
            } else if (!a(Fragment.class, "onPause")) {
                throw new SecurityException("Fragment onPause(Context context)不在Fragment.onPause()中被调用||onPause(Context context)is not called in Fragment.onPause().");
            } else {
                ca.a().b(fragment, System.currentTimeMillis());
            }
        }
    }

    @Deprecated
    public static synchronized void onPause(Object obj) {
        synchronized (StatService.class) {
            if (obj == null) {
                cr.c("android.app.Fragment onResume :parame=null");
            } else if (!a(obj.getClass(), "onPause")) {
                throw new SecurityException("android.app.Fragment onPause(Context context)不在android.app.Fragment.onPause()中被调用||onPause(Context context)is not called in android.app.Fragment.onPause().");
            } else {
                ca.a().b(obj, System.currentTimeMillis());
            }
        }
    }

    public static synchronized void onResume(Context context) {
        synchronized (StatService.class) {
            if (a(context, "onResume(...)")) {
                if (!a(Activity.class, "onResume")) {
                    throw new SecurityException("onResume(Context context)不在Activity.onResume()中被调用||onResume(Context context)is not called in Activity.onResume().");
                }
                b(context);
                ca.a().a(context, System.currentTimeMillis());
            }
        }
    }

    @Deprecated
    public static synchronized void onResume(Fragment fragment) {
        synchronized (StatService.class) {
            if (fragment == null) {
                cr.c("onResume :parame=null");
            } else if (!a(Fragment.class, "onResume")) {
                throw new SecurityException("onResume(Context context)不在Fragment.onResume()中被调用||onResume(Context context)is not called in Fragment.onResume().");
            } else {
                FragmentActivity activity = fragment.getActivity();
                if (activity == null) {
                    cr.c("can not get correct fragmentActivity, fragment may not attached to activity");
                } else {
                    b(activity);
                    ca.a().a(fragment, System.currentTimeMillis());
                }
            }
        }
    }

    @Deprecated
    public static synchronized void onResume(Object obj) {
        synchronized (StatService.class) {
            if (obj == null) {
                cr.c("onResume :parame=null");
            } else if (!a(obj.getClass(), "onResume")) {
                throw new SecurityException("onResume(Context context)不在Fragment.onResume()中被调用||onResume(Context context)is not called in Fragment.onResume().");
            } else {
                Context a2 = ca.a(obj);
                if (a2 == null) {
                    cr.c("can not get correct context, fragment may not attached to activity");
                } else {
                    b(a2);
                    ca.a().a(obj, System.currentTimeMillis());
                }
            }
        }
    }

    public static void setAppChannel(Context context, String str, boolean z) {
        DataCore.instance().setAppChannel(context, str, z);
    }

    @Deprecated
    public static void setAppChannel(String str) {
        DataCore.instance().setAppChannel(str);
    }

    public static void setAppKey(String str) {
        DataCore.instance().setAppKey(str);
    }

    public static void setDebugOn(boolean z) {
        cr.a = z ? 2 : 7;
    }

    public static void setForTv(Context context, boolean z) {
        be.a().d(context, z);
    }

    public static void setLogSenderDelayed(int i) {
        bs.a().a(i);
    }

    public static void setOn(Context context, int i) {
        if (a(context, "setOn(...)") && !a) {
            a = true;
            if ((i & 1) != 0) {
                a(context);
            }
        }
    }

    public static void setSendLogStrategy(Context context, SendStrategyEnum sendStrategyEnum, int i) {
        setSendLogStrategy(context, sendStrategyEnum, i, false);
    }

    public static void setSendLogStrategy(Context context, SendStrategyEnum sendStrategyEnum, int i, boolean z) {
        if (a(context, "setSendLogStrategy(...)")) {
            b(context);
            bs.a().a(context.getApplicationContext(), sendStrategyEnum, i, z);
        }
    }

    public static void setSessionTimeOut(int i) {
        if (i <= 0) {
            cr.b("SessionTimeOut is between 1 and 600. Default value[30] is used");
        } else if (i <= 600) {
            ca.a().a(i);
        } else {
            cr.b("SessionTimeOut is between 1 and 600. Value[600] is used");
            ca.a().a(600);
        }
    }
}
