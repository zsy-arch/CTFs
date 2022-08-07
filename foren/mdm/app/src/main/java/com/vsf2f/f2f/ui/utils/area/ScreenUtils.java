package com.vsf2f.f2f.ui.utils.area;

import android.app.Activity;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.Window;
import android.view.WindowManager;
import com.hy.frame.util.MyLog;

/* loaded from: classes2.dex */
public final class ScreenUtils {
    private static boolean isFullScreen = false;

    public static DisplayMetrics displayMetrics(Context context) {
        DisplayMetrics dm = new DisplayMetrics();
        ((WindowManager) context.getSystemService("window")).getDefaultDisplay().getMetrics(dm);
        MyLog.d("screen width=" + dm.widthPixels + "px, screen height=" + dm.heightPixels + "px, densityDpi=" + dm.densityDpi + ", density=" + dm.density);
        return dm;
    }

    public static int widthPixels(Context context) {
        return displayMetrics(context).widthPixels;
    }

    public static int heightPixels(Context context) {
        return displayMetrics(context).heightPixels;
    }

    public static float density(Context context) {
        return displayMetrics(context).density;
    }

    public static int densityDpi(Context context) {
        return displayMetrics(context).densityDpi;
    }

    public static boolean isFullScreen() {
        return isFullScreen;
    }

    public static void toggleFullScreen(Activity activity) {
        Window window = activity.getWindow();
        if (isFullScreen) {
            window.clearFlags(1024);
            isFullScreen = false;
            return;
        }
        window.setFlags(1024, 1024);
        isFullScreen = true;
    }

    public static void keepBright(Activity activity) {
        activity.getWindow().setFlags(128, 128);
    }
}
