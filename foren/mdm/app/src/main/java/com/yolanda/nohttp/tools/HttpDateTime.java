package com.yolanda.nohttp.tools;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/* loaded from: classes2.dex */
public final class HttpDateTime {
    public static final String FORMAT_HTTP_DATA = "EEE, dd MMM y HH:mm:ss 'GMT'";
    public static final TimeZone GMT_TIME_ZONE = TimeZone.getTimeZone("GMT");

    public static long parseGMTToMillis(String gmtTime) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat(FORMAT_HTTP_DATA, Locale.US);
        formatter.setTimeZone(GMT_TIME_ZONE);
        return formatter.parse(gmtTime).getTime();
    }

    public static String formatMillisToGMT(long milliseconds) {
        Date date = new Date(milliseconds);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(FORMAT_HTTP_DATA, Locale.US);
        simpleDateFormat.setTimeZone(GMT_TIME_ZONE);
        return simpleDateFormat.format(date);
    }

    public static long getMaxExpiryMillis() {
        return System.currentTimeMillis() + 3153600000000L;
    }
}
