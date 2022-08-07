package com.yolanda.nohttp.tools;

import android.text.TextUtils;
import com.alipay.sdk.util.h;
import com.yolanda.nohttp.Headers;
import com.yolanda.nohttp.cache.CacheEntity;
import java.util.Locale;
import java.util.StringTokenizer;

/* loaded from: classes2.dex */
public class HeaderParser {
    public static String parseHeadValue(String content, String key, String defaultValue) {
        if (TextUtils.isEmpty(content) || TextUtils.isEmpty(key)) {
            return defaultValue;
        }
        StringTokenizer stringTokenizer = new StringTokenizer(content, h.b);
        while (stringTokenizer.hasMoreElements()) {
            String valuePair = stringTokenizer.nextToken();
            int index = valuePair.indexOf(61);
            if (index > 0 && key.equalsIgnoreCase(valuePair.substring(0, index).trim())) {
                return valuePair.substring(index + 1).trim();
            }
        }
        return defaultValue;
    }

    public static boolean isGzipContent(String contentEncoding) {
        return contentEncoding != null && contentEncoding.toLowerCase(Locale.getDefault()).contains("gzip");
    }

    public static CacheEntity parseCacheHeaders(Headers responseHeaders, byte[] responseBody, boolean forceCache) {
        long now = System.currentTimeMillis();
        long date = responseHeaders.getDate();
        long expires = responseHeaders.getExpiration();
        long maxAge = 0;
        long staleWhileRevalidate = 0;
        boolean mustRevalidate = false;
        String cacheControl = responseHeaders.getCacheControl();
        if (!TextUtils.isEmpty(cacheControl)) {
            StringTokenizer tokens = new StringTokenizer(cacheControl, ",");
            while (tokens.hasMoreTokens()) {
                String token = tokens.nextToken().trim().toLowerCase(Locale.getDefault());
                if ((token.equals("no-cache") || token.equals("no-store")) && !forceCache) {
                    return null;
                }
                if (token.startsWith("max-age=")) {
                    try {
                        maxAge = Long.parseLong(token.substring(8));
                    } catch (Exception e) {
                    }
                } else if (token.startsWith("stale-while-revalidate=")) {
                    try {
                        staleWhileRevalidate = Long.parseLong(token.substring(23));
                    } catch (Exception e2) {
                    }
                } else if (token.equals("must-revalidate") || token.equals("proxy-revalidate")) {
                    mustRevalidate = true;
                }
            }
        }
        CacheEntity cacheEntity = new CacheEntity();
        long localExpire = 0;
        if (!TextUtils.isEmpty(cacheControl)) {
            localExpire = now + (1000 * maxAge);
            if (mustRevalidate) {
                localExpire += 1000 * staleWhileRevalidate;
            }
        } else if (date > 0 && expires >= date) {
            localExpire = now + (expires - date);
        }
        cacheEntity.setData(responseBody);
        cacheEntity.setLocalExpire(localExpire);
        cacheEntity.setResponseHeaders(responseHeaders);
        return cacheEntity;
    }
}
