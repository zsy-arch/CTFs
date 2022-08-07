package com.easeui.model;

import android.graphics.Bitmap;
import android.support.v4.util.LruCache;

/* loaded from: classes.dex */
public class EaseImageCache {
    private static EaseImageCache imageCache = null;
    private LruCache<String, Bitmap> cache;

    private EaseImageCache() {
        this.cache = null;
        this.cache = new LruCache<String, Bitmap>((int) (Runtime.getRuntime().maxMemory() / 8)) { // from class: com.easeui.model.EaseImageCache.1
            /* JADX INFO: Access modifiers changed from: protected */
            public int sizeOf(String key, Bitmap value) {
                return value.getRowBytes() * value.getHeight();
            }
        };
    }

    public static synchronized EaseImageCache getInstance() {
        EaseImageCache easeImageCache;
        synchronized (EaseImageCache.class) {
            if (imageCache == null) {
                imageCache = new EaseImageCache();
            }
            easeImageCache = imageCache;
        }
        return easeImageCache;
    }

    public Bitmap put(String key, Bitmap value) {
        return this.cache.put(key, value);
    }

    public Bitmap get(String key) {
        return this.cache.get(key);
    }
}
