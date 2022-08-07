package com.tencent.smtt.sdk;

import android.content.Context;
import java.lang.reflect.Field;

/* loaded from: classes2.dex */
public class CookieSyncManager {
    private static android.webkit.CookieSyncManager a;
    private static CookieSyncManager b;
    private static boolean c = false;

    private CookieSyncManager(Context context) {
        bi b2 = bi.b();
        if (b2 != null && b2.c()) {
            b2.d().b().invokeStaticMethod("com.tencent.tbs.tbsshell.WebCoreProxy", "cookieSyncManager_createInstance", new Class[]{Context.class}, context);
            c = true;
        }
    }

    public static synchronized CookieSyncManager createInstance(Context context) {
        CookieSyncManager cookieSyncManager;
        synchronized (CookieSyncManager.class) {
            a = android.webkit.CookieSyncManager.createInstance(context);
            if (b == null || !c) {
                b = new CookieSyncManager(context.getApplicationContext());
            }
            cookieSyncManager = b;
        }
        return cookieSyncManager;
    }

    public static synchronized CookieSyncManager getInstance() {
        CookieSyncManager cookieSyncManager;
        synchronized (CookieSyncManager.class) {
            if (b == null) {
                throw new IllegalStateException("CookieSyncManager::createInstance() needs to be called before CookieSyncManager::getInstance()");
            }
            cookieSyncManager = b;
        }
        return cookieSyncManager;
    }

    public void startSync() {
        bi b2 = bi.b();
        if (b2 == null || !b2.c()) {
            a.startSync();
            try {
                Field declaredField = Class.forName("android.webkit.WebSyncManager").getDeclaredField("mSyncThread");
                declaredField.setAccessible(true);
                ((Thread) declaredField.get(a)).setUncaughtExceptionHandler(new m());
            } catch (Exception e) {
            }
        } else {
            b2.d().b().invokeStaticMethod("com.tencent.tbs.tbsshell.WebCoreProxy", "cookieSyncManager_startSync", new Class[0], new Object[0]);
        }
    }

    public void stopSync() {
        bi b2 = bi.b();
        if (b2 == null || !b2.c()) {
            a.stopSync();
        } else {
            b2.d().b().invokeStaticMethod("com.tencent.tbs.tbsshell.WebCoreProxy", "cookieSyncManager_stopSync", new Class[0], new Object[0]);
        }
    }

    public void sync() {
        bi b2 = bi.b();
        if (b2 == null || !b2.c()) {
            a.sync();
        } else {
            b2.d().b().invokeStaticMethod("com.tencent.tbs.tbsshell.WebCoreProxy", "cookieSyncManager_Sync", new Class[0], new Object[0]);
        }
    }
}
