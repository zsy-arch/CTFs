package com.tencent.smtt.sdk;

import android.os.Build;
import android.webkit.ValueCallback;
import android.webkit.WebView;
import com.tencent.smtt.utils.v;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

/* loaded from: classes2.dex */
public class CookieManager {
    private static CookieManager b;
    ArrayList<a> a;
    private boolean c = false;

    /* loaded from: classes2.dex */
    public class a {
        int a;
        String b;
        String c;
        ValueCallback<Boolean> d;

        a() {
            CookieManager.this = r1;
        }
    }

    private CookieManager() {
    }

    public static CookieManager getInstance() {
        if (b == null) {
            synchronized (CookieManager.class) {
                if (b == null) {
                    b = new CookieManager();
                }
            }
        }
        return b;
    }

    public synchronized void a() {
        this.c = true;
        if (!(this.a == null || this.a.size() == 0)) {
            bi b2 = bi.b();
            if (b2 != null && b2.c()) {
                Iterator<a> it = this.a.iterator();
                while (it.hasNext()) {
                    a next = it.next();
                    switch (next.a) {
                        case 1:
                            setCookie(next.b, next.c, next.d);
                            break;
                        case 2:
                            setCookie(next.b, next.c);
                            break;
                    }
                }
            } else {
                Iterator<a> it2 = this.a.iterator();
                while (it2.hasNext()) {
                    a next2 = it2.next();
                    switch (next2.a) {
                        case 1:
                            if (Build.VERSION.SDK_INT < 21) {
                                break;
                            } else {
                                v.a(android.webkit.CookieManager.getInstance(), "setCookie", new Class[]{String.class, String.class, ValueCallback.class}, next2.b, next2.c, next2.d);
                                break;
                            }
                        case 2:
                            android.webkit.CookieManager.getInstance().setCookie(next2.b, next2.c);
                            break;
                    }
                }
            }
            this.a.clear();
        }
    }

    public boolean acceptCookie() {
        bi b2 = bi.b();
        return (b2 == null || !b2.c()) ? android.webkit.CookieManager.getInstance().acceptCookie() : b2.d().d();
    }

    public synchronized boolean acceptThirdPartyCookies(WebView webView) {
        boolean z = false;
        synchronized (this) {
            bi b2 = bi.b();
            if (b2 != null && b2.c()) {
                Object invokeStaticMethod = b2.d().b().invokeStaticMethod("com.tencent.tbs.tbsshell.WebCoreProxy", "cookieManager_acceptThirdPartyCookies", new Class[]{Object.class}, webView.getView());
                z = invokeStaticMethod != null ? ((Boolean) invokeStaticMethod).booleanValue() : true;
            } else if (Build.VERSION.SDK_INT < 21) {
                z = true;
            } else {
                Object a2 = v.a(android.webkit.CookieManager.getInstance(), "acceptThirdPartyCookies", new Class[]{WebView.class}, webView.getView());
                if (a2 != null) {
                    z = ((Boolean) a2).booleanValue();
                }
            }
        }
        return z;
    }

    public void flush() {
        bi b2 = bi.b();
        if (b2 != null && b2.c()) {
            b2.d().b().invokeStaticMethod("com.tencent.tbs.tbsshell.WebCoreProxy", "cookieManager_flush", new Class[0], new Object[0]);
        } else if (Build.VERSION.SDK_INT >= 21) {
            v.a(android.webkit.CookieManager.getInstance(), "flush", new Class[0], new Object[0]);
        }
    }

    public String getCookie(String str) {
        bi b2 = bi.b();
        if (b2 != null && b2.c()) {
            return b2.d().a(str);
        }
        try {
            return android.webkit.CookieManager.getInstance().getCookie(str);
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
    }

    public boolean hasCookies() {
        bi b2 = bi.b();
        return (b2 == null || !b2.c()) ? android.webkit.CookieManager.getInstance().hasCookies() : b2.d().h();
    }

    public void removeAllCookie() {
        bi b2 = bi.b();
        if (b2 == null || !b2.c()) {
            android.webkit.CookieManager.getInstance().removeAllCookie();
        } else {
            b2.d().e();
        }
    }

    public void removeAllCookies(ValueCallback<Boolean> valueCallback) {
        bi b2 = bi.b();
        if (b2 != null && b2.c()) {
            b2.d().b().invokeStaticMethod("com.tencent.tbs.tbsshell.WebCoreProxy", "cookieManager_removeAllCookies", new Class[]{ValueCallback.class}, valueCallback);
        } else if (Build.VERSION.SDK_INT >= 21) {
            v.a(android.webkit.CookieManager.getInstance(), "removeAllCookies", new Class[]{ValueCallback.class}, valueCallback);
        }
    }

    public void removeExpiredCookie() {
        bi b2 = bi.b();
        if (b2 == null || !b2.c()) {
            android.webkit.CookieManager.getInstance().removeExpiredCookie();
        } else {
            b2.d().b().invokeStaticMethod("com.tencent.tbs.tbsshell.WebCoreProxy", "cookieManager_removeExpiredCookie", new Class[0], new Object[0]);
        }
    }

    public void removeSessionCookie() {
        bi b2 = bi.b();
        if (b2 == null || !b2.c()) {
            android.webkit.CookieManager.getInstance().removeSessionCookie();
        } else {
            b2.d().b().invokeStaticMethod("com.tencent.tbs.tbsshell.WebCoreProxy", "cookieManager_removeSessionCookie", new Class[0], new Object[0]);
        }
    }

    public void removeSessionCookies(ValueCallback<Boolean> valueCallback) {
        bi b2 = bi.b();
        if (b2 != null && b2.c()) {
            b2.d().b().invokeStaticMethod("com.tencent.tbs.tbsshell.WebCoreProxy", "cookieManager_removeSessionCookies", new Class[]{ValueCallback.class}, valueCallback);
        } else if (Build.VERSION.SDK_INT >= 21) {
            v.a(android.webkit.CookieManager.getInstance(), "removeSessionCookies", new Class[]{ValueCallback.class}, valueCallback);
        }
    }

    public synchronized void setAcceptCookie(boolean z) {
        bi b2 = bi.b();
        if (b2 == null || !b2.c()) {
            android.webkit.CookieManager.getInstance().setAcceptCookie(z);
        } else {
            b2.d().b().invokeStaticMethod("com.tencent.tbs.tbsshell.WebCoreProxy", "cookieManager_setAcceptCookie", new Class[]{Boolean.TYPE}, Boolean.valueOf(z));
        }
    }

    public synchronized void setAcceptThirdPartyCookies(WebView webView, boolean z) {
        bi b2 = bi.b();
        if (b2 != null && b2.c()) {
            b2.d().b().invokeStaticMethod("com.tencent.tbs.tbsshell.WebCoreProxy", "cookieManager_setAcceptThirdPartyCookies", new Class[]{Object.class, Boolean.TYPE}, webView.getView(), Boolean.valueOf(z));
        } else if (Build.VERSION.SDK_INT >= 21) {
            v.a(android.webkit.CookieManager.getInstance(), "setAcceptThirdPartyCookies", new Class[]{WebView.class, Boolean.TYPE}, webView.getView(), Boolean.valueOf(z));
        }
    }

    public synchronized void setCookie(String str, String str2) {
        bi b2 = bi.b();
        if (b2 == null || !b2.c()) {
            if (this.c) {
                android.webkit.CookieManager.getInstance().setCookie(str, str2);
            }
            if (!bi.b().e()) {
                a aVar = new a();
                aVar.a = 2;
                aVar.b = str;
                aVar.c = str2;
                aVar.d = null;
                if (this.a == null) {
                    this.a = new ArrayList<>();
                }
                this.a.add(aVar);
            }
        } else {
            b2.d().b().invokeStaticMethod("com.tencent.tbs.tbsshell.WebCoreProxy", "cookieManager_setCookie", new Class[]{String.class, String.class}, str, str2);
        }
    }

    public synchronized void setCookie(String str, String str2, ValueCallback<Boolean> valueCallback) {
        bi b2 = bi.b();
        if (b2 == null || !b2.c()) {
            if (!bi.b().e()) {
                a aVar = new a();
                aVar.a = 1;
                aVar.b = str;
                aVar.c = str2;
                aVar.d = valueCallback;
                if (this.a == null) {
                    this.a = new ArrayList<>();
                }
                this.a.add(aVar);
            }
            if (this.c && Build.VERSION.SDK_INT >= 21) {
                v.a(android.webkit.CookieManager.getInstance(), "setCookie", new Class[]{String.class, String.class, ValueCallback.class}, str, str2, valueCallback);
            }
        } else {
            b2.d().b().invokeStaticMethod("com.tencent.tbs.tbsshell.WebCoreProxy", "cookieManager_setCookie", new Class[]{String.class, String.class, ValueCallback.class}, str, str2, valueCallback);
        }
    }

    public void setCookies(Map<String, String[]> map) {
        bi b2 = bi.b();
        if (!((b2 == null || !b2.c()) ? false : b2.d().a(map))) {
            for (String str : map.keySet()) {
                for (String str2 : map.get(str)) {
                    setCookie(str, str2);
                }
            }
        }
    }
}
