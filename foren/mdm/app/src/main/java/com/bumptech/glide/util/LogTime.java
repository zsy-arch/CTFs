package com.bumptech.glide.util;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.SystemClock;

/* loaded from: classes.dex */
public final class LogTime {
    private static final double MILLIS_MULTIPLIER;

    static {
        double d = 1.0d;
        if (17 <= Build.VERSION.SDK_INT) {
            d = 1.0d / Math.pow(10.0d, 6.0d);
        }
        MILLIS_MULTIPLIER = d;
    }

    private LogTime() {
    }

    @TargetApi(17)
    public static long getLogTime() {
        return 17 <= Build.VERSION.SDK_INT ? SystemClock.elapsedRealtimeNanos() : System.currentTimeMillis();
    }

    public static double getElapsedMillis(long logTime) {
        return (getLogTime() - logTime) * MILLIS_MULTIPLIER;
    }
}
