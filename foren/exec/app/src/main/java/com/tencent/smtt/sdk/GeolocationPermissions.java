package com.tencent.smtt.sdk;

import java.util.Set;

/* loaded from: classes.dex */
public class GeolocationPermissions {

    /* renamed from: a  reason: collision with root package name */
    public static GeolocationPermissions f1107a;

    public static synchronized GeolocationPermissions a() {
        GeolocationPermissions geolocationPermissions;
        synchronized (GeolocationPermissions.class) {
            if (f1107a == null) {
                f1107a = new GeolocationPermissions();
            }
            geolocationPermissions = f1107a;
        }
        return geolocationPermissions;
    }

    public static GeolocationPermissions getInstance() {
        return a();
    }

    public void allow(String str) {
        u a2 = u.a();
        if (a2 == null || !a2.b()) {
            android.webkit.GeolocationPermissions.getInstance().allow(str);
        } else {
            a2.c().g(str);
        }
    }

    public void clear(String str) {
        u a2 = u.a();
        if (a2 == null || !a2.b()) {
            android.webkit.GeolocationPermissions.getInstance().clear(str);
        } else {
            a2.c().f(str);
        }
    }

    public void clearAll() {
        u a2 = u.a();
        if (a2 == null || !a2.b()) {
            android.webkit.GeolocationPermissions.getInstance().clearAll();
        } else {
            a2.c().o();
        }
    }

    public void getAllowed(String str, ValueCallback<Boolean> valueCallback) {
        u a2 = u.a();
        if (a2 == null || !a2.b()) {
            android.webkit.GeolocationPermissions.getInstance().getAllowed(str, valueCallback);
        } else {
            a2.c().c(str, valueCallback);
        }
    }

    public void getOrigins(ValueCallback<Set<String>> valueCallback) {
        u a2 = u.a();
        if (a2 == null || !a2.b()) {
            android.webkit.GeolocationPermissions.getInstance().getOrigins(valueCallback);
        } else {
            a2.c().b(valueCallback);
        }
    }
}
