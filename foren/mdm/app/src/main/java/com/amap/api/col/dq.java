package com.amap.api.col;

import android.content.Context;
import android.content.res.AssetManager;
import java.io.File;

/* compiled from: ResourcesUtil.java */
/* loaded from: classes.dex */
public class dq {
    private static boolean a;

    static {
        a = false;
        a = new File("/system/framework/amap.jar").exists();
    }

    public static AssetManager a(Context context) {
        if (context == null) {
            return null;
        }
        AssetManager assets = context.getAssets();
        if (!a) {
            return assets;
        }
        try {
            assets.getClass().getDeclaredMethod("addAssetPath", String.class).invoke(assets, "/system/framework/amap.jar");
            return assets;
        } catch (Throwable th) {
            gr.b(th, "ResourcesUtil", "getSelfAssets");
            return assets;
        }
    }

    public static int a(Context context, float f) {
        return (int) ((context.getResources().getDisplayMetrics().density * f) + 0.5f);
    }
}
