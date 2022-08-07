package com.hyphenate.util;

import android.os.Build;

/* loaded from: classes2.dex */
public class Utils {
    public static boolean isSdk14() {
        return Build.VERSION.SDK_INT >= 14;
    }

    public static boolean isSdk21() {
        return Build.VERSION.SDK_INT >= 21;
    }
}
