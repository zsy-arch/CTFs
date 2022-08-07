package com.em.video.util;

import android.annotation.SuppressLint;
import android.hardware.Camera;
import android.os.Build;
import android.os.StrictMode;
import com.em.ui.ImageGridActivity;
import java.util.Comparator;
import java.util.List;

/* loaded from: classes.dex */
public class Utils {
    private Utils() {
    }

    @SuppressLint({"NewApi"})
    public static void enableStrictMode() {
        if (hasGingerbread()) {
            StrictMode.ThreadPolicy.Builder threadPolicyBuilder = new StrictMode.ThreadPolicy.Builder().detectAll().penaltyLog();
            StrictMode.VmPolicy.Builder vmPolicyBuilder = new StrictMode.VmPolicy.Builder().detectAll().penaltyLog();
            if (hasHoneycomb()) {
                threadPolicyBuilder.penaltyFlashScreen();
                vmPolicyBuilder.setClassInstanceLimit(ImageGridActivity.class, 1);
            }
            StrictMode.setThreadPolicy(threadPolicyBuilder.build());
            StrictMode.setVmPolicy(vmPolicyBuilder.build());
        }
    }

    public static boolean hasFroyo() {
        return Build.VERSION.SDK_INT >= 8;
    }

    public static boolean hasGingerbread() {
        return Build.VERSION.SDK_INT >= 9;
    }

    public static boolean hasHoneycomb() {
        return Build.VERSION.SDK_INT >= 11;
    }

    public static boolean hasHoneycombMR1() {
        return Build.VERSION.SDK_INT >= 12;
    }

    public static boolean hasJellyBean() {
        return Build.VERSION.SDK_INT >= 16;
    }

    public static boolean hasKitKat() {
        return Build.VERSION.SDK_INT >= 19;
    }

    public static List<Camera.Size> getResolutionList(Camera camera) {
        return camera.getParameters().getSupportedPreviewSizes();
    }

    /* loaded from: classes.dex */
    public static class ResolutionComparator implements Comparator<Camera.Size> {
        public int compare(Camera.Size lhs, Camera.Size rhs) {
            return lhs.height != rhs.height ? lhs.height - rhs.height : lhs.width - rhs.width;
        }
    }
}
