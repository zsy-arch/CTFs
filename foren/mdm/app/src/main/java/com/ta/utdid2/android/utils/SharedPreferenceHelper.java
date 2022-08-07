package com.ta.utdid2.android.utils;

import android.annotation.TargetApi;
import android.content.SharedPreferences;

/* loaded from: classes2.dex */
public class SharedPreferenceHelper {
    @TargetApi(9)
    public static void apply(SharedPreferences.Editor editor) {
        if (editor != null) {
            editor.apply();
        }
    }
}
