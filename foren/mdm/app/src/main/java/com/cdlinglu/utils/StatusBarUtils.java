package com.cdlinglu.utils;

import android.app.Activity;
import android.app.Dialog;
import android.os.Build;
import android.view.Window;

/* loaded from: classes.dex */
public class StatusBarUtils {
    public static void setWindowStatusBarColor(Activity activity, int colorResId) {
        try {
            if (Build.VERSION.SDK_INT >= 21) {
                Window window = activity.getWindow();
                window.addFlags(Integer.MIN_VALUE);
                window.setStatusBarColor(activity.getResources().getColor(colorResId));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void setWindowStatusBarColor(Dialog dialog, int colorResId) {
        try {
            if (Build.VERSION.SDK_INT >= 21) {
                Window window = dialog.getWindow();
                window.addFlags(Integer.MIN_VALUE);
                window.setStatusBarColor(dialog.getContext().getResources().getColor(colorResId));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
