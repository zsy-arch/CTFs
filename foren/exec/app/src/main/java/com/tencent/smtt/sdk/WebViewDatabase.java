package com.tencent.smtt.sdk;

import android.content.Context;

/* loaded from: classes.dex */
public class WebViewDatabase {

    /* renamed from: a  reason: collision with root package name */
    public static WebViewDatabase f1314a;

    /* renamed from: b  reason: collision with root package name */
    public Context f1315b;

    public WebViewDatabase(Context context) {
        this.f1315b = context;
    }

    public static synchronized WebViewDatabase a(Context context) {
        WebViewDatabase webViewDatabase;
        synchronized (WebViewDatabase.class) {
            if (f1314a == null) {
                f1314a = new WebViewDatabase(context);
            }
            webViewDatabase = f1314a;
        }
        return webViewDatabase;
    }

    public static WebViewDatabase getInstance(Context context) {
        return a(context);
    }

    public void clearFormData() {
        u a2 = u.a();
        if (a2 == null || !a2.b()) {
            android.webkit.WebViewDatabase.getInstance(this.f1315b).clearFormData();
        } else {
            a2.c().g(this.f1315b);
        }
    }

    public void clearHttpAuthUsernamePassword() {
        u a2 = u.a();
        if (a2 == null || !a2.b()) {
            android.webkit.WebViewDatabase.getInstance(this.f1315b).clearHttpAuthUsernamePassword();
        } else {
            a2.c().e(this.f1315b);
        }
    }

    @Deprecated
    public void clearUsernamePassword() {
        u a2 = u.a();
        if (a2 == null || !a2.b()) {
            android.webkit.WebViewDatabase.getInstance(this.f1315b).clearUsernamePassword();
        } else {
            a2.c().c(this.f1315b);
        }
    }

    public boolean hasFormData() {
        u a2 = u.a();
        return (a2 == null || !a2.b()) ? android.webkit.WebViewDatabase.getInstance(this.f1315b).hasFormData() : a2.c().f(this.f1315b);
    }

    public boolean hasHttpAuthUsernamePassword() {
        u a2 = u.a();
        return (a2 == null || !a2.b()) ? android.webkit.WebViewDatabase.getInstance(this.f1315b).hasHttpAuthUsernamePassword() : a2.c().d(this.f1315b);
    }

    @Deprecated
    public boolean hasUsernamePassword() {
        u a2 = u.a();
        return (a2 == null || !a2.b()) ? android.webkit.WebViewDatabase.getInstance(this.f1315b).hasUsernamePassword() : a2.c().b(this.f1315b);
    }
}
