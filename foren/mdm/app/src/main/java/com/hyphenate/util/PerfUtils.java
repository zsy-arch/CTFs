package com.hyphenate.util;

/* loaded from: classes2.dex */
public class PerfUtils {
    public static int getSpeed(long j, long j2) {
        return (int) (((float) j) / ((float) (j2 / 1000)));
    }

    public static int getTimeSpendSecond(long j) {
        int currentTimeMillis = (int) (System.currentTimeMillis() - j);
        if (currentTimeMillis == 0) {
            return 1;
        }
        return currentTimeMillis;
    }
}
