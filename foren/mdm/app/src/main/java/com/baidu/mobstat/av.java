package com.baidu.mobstat;

import android.content.Context;
import android.text.TextUtils;
import dalvik.system.DexClassLoader;
import java.io.File;
import java.util.jar.JarFile;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class av {
    private static volatile DexClassLoader a;
    private static volatile boolean b = false;

    private static synchronized DexClassLoader a(Context context) {
        DexClassLoader dexClassLoader = null;
        synchronized (av.class) {
            if (a != null) {
                dexClassLoader = a;
            } else {
                File fileStreamPath = context.getFileStreamPath(".remote.jar");
                if (fileStreamPath == null || fileStreamPath.isFile()) {
                    if (!b(context, fileStreamPath.getAbsolutePath())) {
                        bb.a("remote jar version lower than min limit, need delete");
                        if (fileStreamPath.isFile()) {
                            fileStreamPath.delete();
                        }
                    } else if (!c(context, fileStreamPath.getAbsolutePath())) {
                        bb.a("remote jar md5 is not right, need delete");
                        if (fileStreamPath.isFile()) {
                            fileStreamPath.delete();
                        }
                    } else {
                        try {
                            a = new DexClassLoader(fileStreamPath.getAbsolutePath(), context.getDir("outdex", 0).getAbsolutePath(), null, context.getClassLoader());
                        } catch (Exception e) {
                            bb.a(e);
                        }
                        dexClassLoader = a;
                    }
                }
            }
        }
        return dexClassLoader;
    }

    public static Class<?> a(Context context, String str) {
        DexClassLoader a2 = a(context);
        if (a2 == null) {
            return null;
        }
        return a2.loadClass(str);
    }

    public static synchronized void a(Context context, k kVar) {
        synchronized (av.class) {
            if (!b) {
                if (!cu.m(context)) {
                    bb.a("isWifiAvailable = false, will not to update");
                } else if (!kVar.a(context)) {
                    bb.a("check time, will not to update");
                } else {
                    bb.a("can start update config");
                    new aw(context, kVar).start();
                    b = true;
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String b(String str) {
        try {
            File file = new File(str);
            if (file.exists()) {
                bb.b("file size: " + file.length());
            }
            return new JarFile(str).getManifest().getMainAttributes().getValue("Plugin-Version");
        } catch (Exception e) {
            bb.a(e);
            bb.a("baidu remote sdk is not ready" + str);
            return "";
        }
    }

    private static boolean b(Context context, String str) {
        int i;
        String b2 = b(str);
        if (TextUtils.isEmpty(b2)) {
            return false;
        }
        try {
            i = Integer.valueOf(b2).intValue();
        } catch (Exception e) {
            bb.b(e);
            i = 0;
        }
        return i >= 4;
    }

    private static boolean c(Context context, String str) {
        String a2 = cp.a(new File(str));
        bb.a("remote.jar local file digest value digest = " + a2);
        if (TextUtils.isEmpty(a2)) {
            bb.a("remote.jar local file digest value fail");
            return false;
        }
        String b2 = b(str);
        bb.a("remote.jar local file digest value version = " + b2);
        if (TextUtils.isEmpty(b2)) {
            return false;
        }
        String d = d(context, b2);
        bb.a("remote.jar config digest value remoteJarMd5 = " + d);
        if (!TextUtils.isEmpty(d)) {
            return a2.equals(d);
        }
        bb.a("remote.jar config digest value lost");
        return false;
    }

    private static String d(Context context, String str) {
        return ax.a(context).c(str);
    }
}
