package com.tencent.smtt.sdk;

import java.util.Map;

/* loaded from: classes.dex */
public class WebStorage {

    /* renamed from: a  reason: collision with root package name */
    public static WebStorage f1291a;

    @Deprecated
    /* loaded from: classes.dex */
    public interface QuotaUpdater {
        void updateQuota(long j);
    }

    public static synchronized WebStorage a() {
        WebStorage webStorage;
        synchronized (WebStorage.class) {
            if (f1291a == null) {
                f1291a = new WebStorage();
            }
            webStorage = f1291a;
        }
        return webStorage;
    }

    public static WebStorage getInstance() {
        return a();
    }

    public void deleteAllData() {
        u a2 = u.a();
        if (a2 == null || !a2.b()) {
            android.webkit.WebStorage.getInstance().deleteAllData();
        } else {
            a2.c().n();
        }
    }

    public void deleteOrigin(String str) {
        u a2 = u.a();
        if (a2 == null || !a2.b()) {
            android.webkit.WebStorage.getInstance().deleteOrigin(str);
        } else {
            a2.c().e(str);
        }
    }

    public void getOrigins(ValueCallback<Map> valueCallback) {
        u a2 = u.a();
        if (a2 == null || !a2.b()) {
            android.webkit.WebStorage.getInstance().getOrigins(valueCallback);
        } else {
            a2.c().a(valueCallback);
        }
    }

    public void getQuotaForOrigin(String str, ValueCallback<Long> valueCallback) {
        u a2 = u.a();
        if (a2 == null || !a2.b()) {
            android.webkit.WebStorage.getInstance().getQuotaForOrigin(str, valueCallback);
        } else {
            a2.c().b(str, valueCallback);
        }
    }

    public void getUsageForOrigin(String str, ValueCallback<Long> valueCallback) {
        u a2 = u.a();
        if (a2 == null || !a2.b()) {
            android.webkit.WebStorage.getInstance().getUsageForOrigin(str, valueCallback);
        } else {
            a2.c().a(str, valueCallback);
        }
    }

    @Deprecated
    public void setQuotaForOrigin(String str, long j) {
        u a2 = u.a();
        if (a2 == null || !a2.b()) {
            android.webkit.WebStorage.getInstance().setQuotaForOrigin(str, j);
        } else {
            a2.c().a(str, j);
        }
    }
}
