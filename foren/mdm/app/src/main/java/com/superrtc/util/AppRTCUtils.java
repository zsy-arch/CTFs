package com.superrtc.util;

import android.os.Build;
import android.util.Log;

/* loaded from: classes2.dex */
public final class AppRTCUtils {
    private AppRTCUtils() {
    }

    /* loaded from: classes2.dex */
    public static class NonThreadSafe {
        private final Long threadId = Long.valueOf(Thread.currentThread().getId());

        public boolean calledOnValidThread() {
            return this.threadId.equals(Long.valueOf(Thread.currentThread().getId()));
        }
    }

    public static void assertIsTrue(boolean condition) {
        if (!condition) {
            throw new AssertionError("Expected condition to be true");
        }
    }

    public static String getThreadInfo() {
        return "@[name=" + Thread.currentThread().getName() + ", id=" + Thread.currentThread().getId() + "]";
    }

    public static void logDeviceInfo(String tag) {
        Log.d(tag, "Android SDK: " + Build.VERSION.SDK_INT + ", Release: " + Build.VERSION.RELEASE + ", Brand: " + Build.BRAND + ", Device: " + Build.DEVICE + ", Id: " + Build.ID + ", Hardware: " + Build.HARDWARE + ", Manufacturer: " + Build.MANUFACTURER + ", Model: " + Build.MODEL + ", Product: " + Build.PRODUCT);
    }
}
