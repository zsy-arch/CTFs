package com.alibaba.sdk.android.oss.common.utils;

import android.os.Build;

/* loaded from: classes.dex */
public class VersionInfoUtils {
    private static String userAgent = null;

    public static String getUserAgent() {
        if (userAgent == null) {
            userAgent = "aliyun-sdk-android/" + getVersion() + "/" + getDefaultUserAgent();
        }
        return userAgent;
    }

    public static String getVersion() {
        return "2.4.4";
    }

    public static String getDefaultUserAgent() {
        String result = System.getProperty("http.agent");
        if (OSSUtils.isEmptyString(result)) {
            result = "(" + System.getProperty("os.name") + "/Android " + Build.VERSION.RELEASE + "/" + Build.MODEL + "/" + Build.ID + ")";
        }
        return result.replaceAll("[^\\p{ASCII}]", "?");
    }
}
