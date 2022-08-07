package com.alibaba.sdk.android.oss.common.utils;

import java.net.URLEncoder;
import org.slf4j.Marker;

/* loaded from: classes.dex */
public class HttpUtil {
    public static String urlEncode(String value, String encoding) {
        if (value == null) {
            return "";
        }
        try {
            return URLEncoder.encode(value, encoding).replace(Marker.ANY_NON_NULL_MARKER, "%20").replace("*", "%2A").replace("%7E", "~").replace("%2F", "/");
        } catch (Exception e) {
            throw new IllegalArgumentException("failed to encode url!", e);
        }
    }
}
