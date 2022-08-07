package com.lidroid.xutils.http;

import android.text.TextUtils;
import com.lidroid.xutils.cache.LruMemoryCache;
import com.lidroid.xutils.http.client.HttpRequest;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: classes2.dex */
public class HttpCache {
    private static final int DEFAULT_CACHE_SIZE = 102400;
    private static final long DEFAULT_EXPIRY_TIME = 60000;
    private static long defaultExpiryTime = DEFAULT_EXPIRY_TIME;
    private static final ConcurrentHashMap<String, Boolean> httpMethod_enabled_map = new ConcurrentHashMap<>(10);
    private int cacheSize;
    private final LruMemoryCache<String, String> mMemoryCache;

    static {
        httpMethod_enabled_map.put(HttpRequest.HttpMethod.GET.toString(), true);
    }

    public HttpCache() {
        this(DEFAULT_CACHE_SIZE, DEFAULT_EXPIRY_TIME);
    }

    public HttpCache(int strLength, long defaultExpiryTime2) {
        this.cacheSize = DEFAULT_CACHE_SIZE;
        this.cacheSize = strLength;
        defaultExpiryTime = defaultExpiryTime2;
        this.mMemoryCache = new LruMemoryCache<String, String>(this.cacheSize) { // from class: com.lidroid.xutils.http.HttpCache.1
            public int sizeOf(String key, String value) {
                if (value == null) {
                    return 0;
                }
                return value.length();
            }
        };
    }

    public void setCacheSize(int strLength) {
        this.mMemoryCache.setMaxSize(strLength);
    }

    public static void setDefaultExpiryTime(long defaultExpiryTime2) {
        defaultExpiryTime = defaultExpiryTime2;
    }

    public static long getDefaultExpiryTime() {
        return defaultExpiryTime;
    }

    public void put(String url, String result) {
        put(url, result, defaultExpiryTime);
    }

    public void put(String url, String result, long expiry) {
        if (url != null && result != null && expiry >= 1) {
            this.mMemoryCache.put(url, result, System.currentTimeMillis() + expiry);
        }
    }

    public String get(String url) {
        if (url != null) {
            return this.mMemoryCache.get(url);
        }
        return null;
    }

    public void clear() {
        this.mMemoryCache.evictAll();
    }

    public boolean isEnabled(HttpRequest.HttpMethod method) {
        Boolean enabled;
        if (method == null || (enabled = httpMethod_enabled_map.get(method.toString())) == null) {
            return false;
        }
        return enabled.booleanValue();
    }

    public boolean isEnabled(String method) {
        Boolean enabled;
        if (!TextUtils.isEmpty(method) && (enabled = httpMethod_enabled_map.get(method.toUpperCase())) != null) {
            return enabled.booleanValue();
        }
        return false;
    }

    public void setEnabled(HttpRequest.HttpMethod method, boolean enabled) {
        httpMethod_enabled_map.put(method.toString(), Boolean.valueOf(enabled));
    }
}
