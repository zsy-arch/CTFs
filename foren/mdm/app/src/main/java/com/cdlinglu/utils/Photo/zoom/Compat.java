package com.cdlinglu.utils.Photo.zoom;

import android.os.Build;
import android.view.View;

/* loaded from: classes.dex */
public class Compat {
    private static final int SIXTY_FPS_INTERVAL = 16;

    public static void postOnAnimation(View view, Runnable runnable) {
        if (Build.VERSION.SDK_INT >= 16) {
            SDK16.postOnAnimation(view, runnable);
        } else {
            view.postDelayed(runnable, 16L);
        }
    }
}
