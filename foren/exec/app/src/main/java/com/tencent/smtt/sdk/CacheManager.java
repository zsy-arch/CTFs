package com.tencent.smtt.sdk;

import com.tencent.smtt.utils.i;
import java.io.File;
import java.io.InputStream;
import java.util.Map;

/* loaded from: classes.dex */
public final class CacheManager {
    @Deprecated
    public static boolean cacheDisabled() {
        u a2 = u.a();
        if (a2 != null && a2.b()) {
            return ((Boolean) a2.c().c()).booleanValue();
        }
        Object a3 = i.a("android.webkit.CacheManager", "cacheDisabled");
        if (a3 == null) {
            return false;
        }
        return ((Boolean) a3).booleanValue();
    }

    public static InputStream getCacheFile(String str, boolean z) {
        u a2 = u.a();
        if (a2 == null || !a2.b()) {
            return null;
        }
        return a2.c().a(str, z);
    }

    public static Object getCacheFile(String str, Map<String, String> map) {
        u a2 = u.a();
        if (a2 != null && a2.b()) {
            return a2.c().g();
        }
        try {
            return i.a(Class.forName("android.webkit.CacheManager"), "getCacheFile", (Class<?>[]) new Class[]{String.class, Map.class}, str, map);
        } catch (Exception unused) {
            return null;
        }
    }

    @Deprecated
    public static File getCacheFileBaseDir() {
        u a2 = u.a();
        return (File) ((a2 == null || !a2.b()) ? i.a("android.webkit.CacheManager", "getCacheFileBaseDir") : a2.c().g());
    }
}
