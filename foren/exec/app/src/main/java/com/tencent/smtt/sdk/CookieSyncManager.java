package com.tencent.smtt.sdk;

import android.content.Context;
import java.lang.reflect.Field;

/* loaded from: classes.dex */
public class CookieSyncManager {

    /* renamed from: a  reason: collision with root package name */
    public static android.webkit.CookieSyncManager f1102a;

    /* renamed from: b  reason: collision with root package name */
    public static CookieSyncManager f1103b;

    /* renamed from: c  reason: collision with root package name */
    public static boolean f1104c;

    public CookieSyncManager(Context context) {
        u a2 = u.a();
        if (a2 != null && a2.b()) {
            a2.c().b().invokeStaticMethod("com.tencent.tbs.tbsshell.WebCoreProxy", "cookieSyncManager_createInstance", new Class[]{Context.class}, context);
            f1104c = true;
        }
    }

    public static synchronized CookieSyncManager createInstance(Context context) {
        CookieSyncManager cookieSyncManager;
        synchronized (CookieSyncManager.class) {
            f1102a = android.webkit.CookieSyncManager.createInstance(context);
            if (f1103b == null || !f1104c) {
                f1103b = new CookieSyncManager(context.getApplicationContext());
            }
            cookieSyncManager = f1103b;
        }
        return cookieSyncManager;
    }

    public static synchronized CookieSyncManager getInstance() {
        CookieSyncManager cookieSyncManager;
        synchronized (CookieSyncManager.class) {
            if (f1103b != null) {
                cookieSyncManager = f1103b;
            } else {
                throw new IllegalStateException("CookieSyncManager::createInstance() needs to be called before CookieSyncManager::getInstance()");
            }
        }
        return cookieSyncManager;
    }

    public void startSync() {
        u a2 = u.a();
        if (a2 == null || !a2.b()) {
            f1102a.startSync();
            try {
                Field declaredField = Class.forName("android.webkit.WebSyncManager").getDeclaredField("mSyncThread");
                declaredField.setAccessible(true);
                ((Thread) declaredField.get(f1102a)).setUncaughtExceptionHandler(new e());
            } catch (Exception unused) {
            }
        } else {
            a2.c().b().invokeStaticMethod("com.tencent.tbs.tbsshell.WebCoreProxy", "cookieSyncManager_startSync", new Class[0], new Object[0]);
        }
    }

    public void stopSync() {
        u a2 = u.a();
        if (a2 == null || !a2.b()) {
            f1102a.stopSync();
        } else {
            a2.c().b().invokeStaticMethod("com.tencent.tbs.tbsshell.WebCoreProxy", "cookieSyncManager_stopSync", new Class[0], new Object[0]);
        }
    }

    public void sync() {
        u a2 = u.a();
        if (a2 == null || !a2.b()) {
            f1102a.sync();
        } else {
            a2.c().b().invokeStaticMethod("com.tencent.tbs.tbsshell.WebCoreProxy", "cookieSyncManager_Sync", new Class[0], new Object[0]);
        }
    }
}
