package com.litepal.util;

import android.content.SharedPreferences;
import com.litepal.LitePalApplication;

/* loaded from: classes2.dex */
public class SharedUtil {
    private static final String LITEPAL_PREPS = "litepal_prefs";
    private static final String VERSION = "litepal_version";

    public static void updateVersion(int newVersion) {
        SharedPreferences.Editor sEditor = LitePalApplication.getContext().getSharedPreferences(LITEPAL_PREPS, 0).edit();
        sEditor.putInt(VERSION, newVersion);
        sEditor.apply();
    }

    public static int getLastVersion() {
        return LitePalApplication.getContext().getSharedPreferences(LITEPAL_PREPS, 0).getInt(VERSION, 0);
    }
}
