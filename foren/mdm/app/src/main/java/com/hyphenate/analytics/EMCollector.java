package com.hyphenate.analytics;

import android.annotation.SuppressLint;
import java.text.SimpleDateFormat;
import java.util.Date;

/* loaded from: classes2.dex */
public class EMCollector {
    static boolean collectorEnabled = false;

    public static String getTagPrefix(String str) {
        return "[" + str + "]";
    }

    @SuppressLint({"SimpleDateFormat"})
    public static String timeToString(long j) {
        return new SimpleDateFormat("mm:ss:SSS").format(new Date(j));
    }

    public void enableCollector(boolean z) {
        collectorEnabled = z;
    }
}
