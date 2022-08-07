package com.tencent.smtt.sdk;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.text.TextUtils;
import android.webkit.ValueCallback;
import android.webkit.WebView;
import com.tencent.smtt.sdk.TbsListener;
import com.tencent.smtt.sdk.TbsLogReport;
import com.tencent.smtt.utils.TbsLog;
import com.tencent.smtt.utils.i;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

/* loaded from: classes.dex */
public class CookieManager {
    public static String LOGTAG = "CookieManager";

    /* renamed from: d */
    public static CookieManager f1088d;

    /* renamed from: a */
    public CopyOnWriteArrayList<b> f1089a;

    /* renamed from: b */
    public String f1090b;

    /* renamed from: c */
    public a f1091c = a.MODE_NONE;

    /* renamed from: e */
    public boolean f1092e = false;
    public boolean f = false;

    /* loaded from: classes.dex */
    public enum a {
        MODE_NONE,
        MODE_KEYS,
        MODE_ALL
    }

    /* loaded from: classes.dex */
    public class b {

        /* renamed from: a */
        public int f1097a;

        /* renamed from: b */
        public String f1098b;

        /* renamed from: c */
        public String f1099c;

        /* renamed from: d */
        public ValueCallback<Boolean> f1100d;

        public b() {
            CookieManager.this = r1;
        }
    }

    public static CookieManager getInstance() {
        if (f1088d == null) {
            synchronized (CookieManager.class) {
                if (f1088d == null) {
                    f1088d = new CookieManager();
                }
            }
        }
        return f1088d;
    }

    public static int getROMCookieDBVersion(Context context) {
        int i = Build.VERSION.SDK_INT;
        return context.getSharedPreferences("cookiedb_info", 4).getInt("db_version", -1);
    }

    public static void setROMCookieDBVersion(Context context, int i) {
        int i2 = Build.VERSION.SDK_INT;
        SharedPreferences.Editor edit = context.getSharedPreferences("cookiedb_info", 4).edit();
        edit.putInt("db_version", i);
        edit.commit();
    }

    public synchronized void a() {
        this.f = true;
        if (!(this.f1089a == null || this.f1089a.size() == 0)) {
            u a2 = u.a();
            if (a2 == null || !a2.b()) {
                Iterator<b> it = this.f1089a.iterator();
                while (it.hasNext()) {
                    b next = it.next();
                    int i = next.f1097a;
                    if (i == 1) {
                        int i2 = Build.VERSION.SDK_INT;
                        i.a(android.webkit.CookieManager.getInstance(), "setCookie", new Class[]{String.class, String.class, ValueCallback.class}, next.f1098b, next.f1099c, next.f1100d);
                    } else if (i == 2) {
                        android.webkit.CookieManager.getInstance().setCookie(next.f1098b, next.f1099c);
                    }
                }
            } else {
                Iterator<b> it2 = this.f1089a.iterator();
                while (it2.hasNext()) {
                    b next2 = it2.next();
                    int i3 = next2.f1097a;
                    if (i3 == 1) {
                        setCookie(next2.f1098b, next2.f1099c, next2.f1100d);
                    } else if (i3 == 2) {
                        setCookie(next2.f1098b, next2.f1099c);
                    }
                }
            }
            this.f1089a.clear();
        }
    }

    public synchronized void a(Context context, boolean z, boolean z2) {
        int i;
        int i2;
        if (this.f1091c != a.MODE_NONE && context != null && TbsExtensionFunctionManager.getInstance().canUseFunction(context, TbsExtensionFunctionManager.COOKIE_SWITCH_FILE_NAME) && !this.f1092e) {
            long currentTimeMillis = System.currentTimeMillis();
            long j = 0;
            String str = LOGTAG;
            TbsLog.i(str, "compatiableCookieDatabaseIfNeed,isX5Inited:" + z + ",useX5:" + z2);
            if (!z && !QbSdk.f1128b && !QbSdk.f1127a) {
                u.a().a(context);
                return;
            }
            boolean z3 = false;
            if (QbSdk.f1128b || QbSdk.f1127a) {
                z2 = false;
            }
            boolean canUseFunction = TbsExtensionFunctionManager.getInstance().canUseFunction(context, TbsExtensionFunctionManager.USEX5_FILE_NAME);
            String str2 = LOGTAG;
            TbsLog.i(str2, "usex5 : mUseX5LastProcess->" + canUseFunction + ",useX5:" + z2);
            TbsExtensionFunctionManager.getInstance().setFunctionEnable(context, TbsExtensionFunctionManager.USEX5_FILE_NAME, z2);
            if (canUseFunction != z2) {
                TbsLogReport.TbsLogInfo tbsLogInfo = TbsLogReport.getInstance(context).tbsLogInfo();
                if (TextUtils.isEmpty(this.f1090b)) {
                    tbsLogInfo.setErrorCode(701);
                    i2 = 0;
                    i = 0;
                } else if (m.a().i(context) <= 0 || m.a().i(context) >= 36001) {
                    if (canUseFunction) {
                        i2 = h.d(context);
                        if (i2 > 0) {
                            i = getROMCookieDBVersion(context);
                            if (i <= 0) {
                                z3 = true;
                            }
                        }
                        i = 0;
                    } else {
                        i2 = h.d(context);
                        if (i2 > 0) {
                            String d2 = m.a().d(context, "cookies_database_version");
                            if (!TextUtils.isEmpty(d2)) {
                                try {
                                    i = Integer.parseInt(d2);
                                } catch (Exception unused) {
                                }
                            }
                        }
                        i = 0;
                    }
                    if (!z3 && (i2 <= 0 || i <= 0)) {
                        tbsLogInfo.setErrorCode(702);
                    } else if (i >= i2) {
                        tbsLogInfo.setErrorCode(703);
                    } else {
                        h.a(context, this.f1091c, this.f1090b, z3, z2);
                        tbsLogInfo.setErrorCode(TbsListener.ErrorCode.INFO_COOKIE_SWITCH_TRANSFER);
                        j = System.currentTimeMillis() - currentTimeMillis;
                    }
                } else {
                    return;
                }
                tbsLogInfo.setFailDetail("x5->sys:" + canUseFunction + " from:" + i2 + " to:" + i + ",timeused:" + j);
                TbsLogReport.getInstance(context).eventReport(TbsLogReport.EventType.TYPE_COOKIE_DB_SWITCH, tbsLogInfo);
            }
        }
    }

    public boolean acceptCookie() {
        u a2 = u.a();
        return (a2 == null || !a2.b()) ? android.webkit.CookieManager.getInstance().acceptCookie() : a2.c().d();
    }

    public synchronized boolean acceptThirdPartyCookies(WebView webView) {
        u a2 = u.a();
        if (a2 == null || !a2.b()) {
            int i = Build.VERSION.SDK_INT;
            Object a3 = i.a(android.webkit.CookieManager.getInstance(), "acceptThirdPartyCookies", new Class[]{WebView.class}, webView.getView());
            if (a3 == null) {
                return false;
            }
            return ((Boolean) a3).booleanValue();
        }
        Object invokeStaticMethod = a2.c().b().invokeStaticMethod("com.tencent.tbs.tbsshell.WebCoreProxy", "cookieManager_acceptThirdPartyCookies", new Class[]{Object.class}, webView.getView());
        if (invokeStaticMethod == null) {
            return true;
        }
        return ((Boolean) invokeStaticMethod).booleanValue();
    }

    public void flush() {
        u a2 = u.a();
        if (a2 == null || !a2.b()) {
            int i = Build.VERSION.SDK_INT;
            i.a(android.webkit.CookieManager.getInstance(), "flush", new Class[0], new Object[0]);
            return;
        }
        a2.c().b().invokeStaticMethod("com.tencent.tbs.tbsshell.WebCoreProxy", "cookieManager_flush", new Class[0], new Object[0]);
    }

    public String getCookie(String str) {
        u a2 = u.a();
        if (a2 != null && a2.b()) {
            return a2.c().a(str);
        }
        try {
            return android.webkit.CookieManager.getInstance().getCookie(str);
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
    }

    public boolean hasCookies() {
        u a2 = u.a();
        return (a2 == null || !a2.b()) ? android.webkit.CookieManager.getInstance().hasCookies() : a2.c().h();
    }

    public void removeAllCookie() {
        CopyOnWriteArrayList<b> copyOnWriteArrayList = this.f1089a;
        if (copyOnWriteArrayList != null) {
            copyOnWriteArrayList.clear();
        }
        u a2 = u.a();
        if (a2 == null || !a2.b()) {
            android.webkit.CookieManager.getInstance().removeAllCookie();
        } else {
            a2.c().e();
        }
    }

    public void removeAllCookies(ValueCallback<Boolean> valueCallback) {
        CopyOnWriteArrayList<b> copyOnWriteArrayList = this.f1089a;
        if (copyOnWriteArrayList != null) {
            copyOnWriteArrayList.clear();
        }
        u a2 = u.a();
        if (a2 == null || !a2.b()) {
            int i = Build.VERSION.SDK_INT;
            i.a(android.webkit.CookieManager.getInstance(), "removeAllCookies", new Class[]{ValueCallback.class}, valueCallback);
            return;
        }
        a2.c().b().invokeStaticMethod("com.tencent.tbs.tbsshell.WebCoreProxy", "cookieManager_removeAllCookies", new Class[]{ValueCallback.class}, valueCallback);
    }

    public void removeExpiredCookie() {
        u a2 = u.a();
        if (a2 == null || !a2.b()) {
            android.webkit.CookieManager.getInstance().removeExpiredCookie();
        } else {
            a2.c().b().invokeStaticMethod("com.tencent.tbs.tbsshell.WebCoreProxy", "cookieManager_removeExpiredCookie", new Class[0], new Object[0]);
        }
    }

    public void removeSessionCookie() {
        u a2 = u.a();
        if (a2 == null || !a2.b()) {
            android.webkit.CookieManager.getInstance().removeSessionCookie();
        } else {
            a2.c().b().invokeStaticMethod("com.tencent.tbs.tbsshell.WebCoreProxy", "cookieManager_removeSessionCookie", new Class[0], new Object[0]);
        }
    }

    public void removeSessionCookies(ValueCallback<Boolean> valueCallback) {
        u a2 = u.a();
        if (a2 == null || !a2.b()) {
            int i = Build.VERSION.SDK_INT;
            i.a(android.webkit.CookieManager.getInstance(), "removeSessionCookies", new Class[]{ValueCallback.class}, valueCallback);
            return;
        }
        a2.c().b().invokeStaticMethod("com.tencent.tbs.tbsshell.WebCoreProxy", "cookieManager_removeSessionCookies", new Class[]{ValueCallback.class}, valueCallback);
    }

    public synchronized void setAcceptCookie(boolean z) {
        u a2 = u.a();
        if (a2 == null || !a2.b()) {
            android.webkit.CookieManager.getInstance().setAcceptCookie(z);
        } else {
            a2.c().b().invokeStaticMethod("com.tencent.tbs.tbsshell.WebCoreProxy", "cookieManager_setAcceptCookie", new Class[]{Boolean.TYPE}, Boolean.valueOf(z));
        }
    }

    public synchronized void setAcceptThirdPartyCookies(WebView webView, boolean z) {
        u a2 = u.a();
        if (a2 == null || !a2.b()) {
            int i = Build.VERSION.SDK_INT;
            i.a(android.webkit.CookieManager.getInstance(), "setAcceptThirdPartyCookies", new Class[]{WebView.class, Boolean.TYPE}, webView.getView(), Boolean.valueOf(z));
        } else {
            a2.c().b().invokeStaticMethod("com.tencent.tbs.tbsshell.WebCoreProxy", "cookieManager_setAcceptThirdPartyCookies", new Class[]{Object.class, Boolean.TYPE}, webView.getView(), Boolean.valueOf(z));
        }
    }

    public synchronized void setCookie(String str, String str2) {
        setCookie(str, str2, false);
    }

    public synchronized void setCookie(String str, String str2, ValueCallback<Boolean> valueCallback) {
        u a2 = u.a();
        if (a2 == null || !a2.b()) {
            if (!u.a().d()) {
                b bVar = new b();
                bVar.f1097a = 1;
                bVar.f1098b = str;
                bVar.f1099c = str2;
                bVar.f1100d = valueCallback;
                if (this.f1089a == null) {
                    this.f1089a = new CopyOnWriteArrayList<>();
                }
                this.f1089a.add(bVar);
            }
            if (this.f) {
                int i = Build.VERSION.SDK_INT;
                i.a(android.webkit.CookieManager.getInstance(), "setCookie", new Class[]{String.class, String.class, ValueCallback.class}, str, str2, valueCallback);
            }
        } else {
            a2.c().b().invokeStaticMethod("com.tencent.tbs.tbsshell.WebCoreProxy", "cookieManager_setCookie", new Class[]{String.class, String.class, ValueCallback.class}, str, str2, valueCallback);
        }
    }

    public synchronized void setCookie(String str, String str2, boolean z) {
        u a2 = u.a();
        if (a2 == null || !a2.b()) {
            if (this.f || z) {
                android.webkit.CookieManager.getInstance().setCookie(str, str2);
            }
            if (!u.a().d()) {
                b bVar = new b();
                bVar.f1097a = 2;
                bVar.f1098b = str;
                bVar.f1099c = str2;
                bVar.f1100d = null;
                if (this.f1089a == null) {
                    this.f1089a = new CopyOnWriteArrayList<>();
                }
                this.f1089a.add(bVar);
            }
        } else {
            a2.c().b().invokeStaticMethod("com.tencent.tbs.tbsshell.WebCoreProxy", "cookieManager_setCookie", new Class[]{String.class, String.class}, str, str2);
        }
    }

    public boolean setCookieCompatialbeMode(Context context, a aVar, String str, boolean z) {
        System.currentTimeMillis();
        if (context == null || !TbsExtensionFunctionManager.getInstance().canUseFunction(context, TbsExtensionFunctionManager.COOKIE_SWITCH_FILE_NAME)) {
            return false;
        }
        this.f1091c = aVar;
        if (str != null) {
            this.f1090b = str;
        }
        if (this.f1091c == a.MODE_NONE || !z || u.a().d()) {
            return true;
        }
        u.a().a(context);
        return true;
    }

    public void setCookies(Map<String, String[]> map) {
        u a2 = u.a();
        if (!((a2 == null || !a2.b()) ? false : a2.c().a(map))) {
            for (String str : map.keySet()) {
                for (String str2 : map.get(str)) {
                    setCookie(str, str2);
                }
            }
        }
    }
}
