package com.lidroid.xutils.bitmap.core;

import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.media.ExifInterface;
import com.lidroid.xutils.bitmap.BitmapDisplayConfig;
import com.lidroid.xutils.bitmap.BitmapGlobalConfig;
import com.lidroid.xutils.bitmap.factory.BitmapFactory;
import com.lidroid.xutils.cache.FileNameGenerator;
import com.lidroid.xutils.cache.LruDiskCache;
import com.lidroid.xutils.cache.LruMemoryCache;
import com.lidroid.xutils.util.IOUtils;
import com.lidroid.xutils.util.LogUtils;
import com.lidroid.xutils.util.OtherUtils;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/* loaded from: classes2.dex */
public class BitmapCache {
    private BitmapGlobalConfig globalConfig;
    private LruDiskCache mDiskLruCache;
    private LruMemoryCache<MemoryCacheKey, Bitmap> mMemoryCache;
    private final int DISK_CACHE_INDEX = 0;
    private final Object mDiskCacheLock = new Object();

    public BitmapCache(BitmapGlobalConfig globalConfig) {
        if (globalConfig == null) {
            throw new IllegalArgumentException("globalConfig may not be null");
        }
        this.globalConfig = globalConfig;
    }

    public void initMemoryCache() {
        if (this.globalConfig.isMemoryCacheEnabled()) {
            if (this.mMemoryCache != null) {
                try {
                    clearMemoryCache();
                } catch (Throwable th) {
                }
            }
            this.mMemoryCache = new LruMemoryCache<MemoryCacheKey, Bitmap>(this.globalConfig.getMemoryCacheSize()) { // from class: com.lidroid.xutils.bitmap.core.BitmapCache.1
                /* JADX INFO: Access modifiers changed from: protected */
                public int sizeOf(MemoryCacheKey key, Bitmap bitmap) {
                    if (bitmap == null) {
                        return 0;
                    }
                    return bitmap.getRowBytes() * bitmap.getHeight();
                }
            };
        }
    }

    public void initDiskCache() {
        synchronized (this.mDiskCacheLock) {
            if (this.globalConfig.isDiskCacheEnabled() && (this.mDiskLruCache == null || this.mDiskLruCache.isClosed())) {
                File diskCacheDir = new File(this.globalConfig.getDiskCachePath());
                if (diskCacheDir.exists() || diskCacheDir.mkdirs()) {
                    long availableSpace = OtherUtils.getAvailableSpace(diskCacheDir);
                    long diskCacheSize = this.globalConfig.getDiskCacheSize();
                    if (availableSpace <= diskCacheSize) {
                        diskCacheSize = availableSpace;
                    }
                    this.mDiskLruCache = LruDiskCache.open(diskCacheDir, 1, 1, diskCacheSize);
                    this.mDiskLruCache.setFileNameGenerator(this.globalConfig.getFileNameGenerator());
                    LogUtils.d("create disk cache success");
                }
            }
        }
    }

    public void setMemoryCacheSize(int maxSize) {
        if (this.mMemoryCache != null) {
            this.mMemoryCache.setMaxSize(maxSize);
        }
    }

    public void setDiskCacheSize(int maxSize) {
        synchronized (this.mDiskCacheLock) {
            if (this.mDiskLruCache != null) {
                this.mDiskLruCache.setMaxSize(maxSize);
            }
        }
    }

    public void setDiskCacheFileNameGenerator(FileNameGenerator fileNameGenerator) {
        synchronized (this.mDiskCacheLock) {
            if (!(this.mDiskLruCache == null || fileNameGenerator == null)) {
                this.mDiskLruCache.setFileNameGenerator(fileNameGenerator);
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:36:0x00bb A[Catch: Throwable -> 0x00d2, all -> 0x00e3, TRY_LEAVE, TryCatch #0 {all -> 0x00e3, blocks: (B:3:0x0009, B:5:0x0011, B:7:0x0015, B:8:0x0018, B:10:0x001c, B:12:0x0024, B:14:0x002c, B:16:0x0047, B:19:0x0052, B:21:0x0062, B:23:0x0071, B:27:0x0081, B:32:0x00a0, B:34:0x00a9, B:36:0x00bb, B:39:0x00d3), top: B:49:0x0000 }] */
    /* JADX WARN: Removed duplicated region for block: B:48:0x00f1  */
    /* JADX WARN: Removed duplicated region for block: B:53:0x007c A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public android.graphics.Bitmap downloadBitmap(java.lang.String r15, com.lidroid.xutils.bitmap.BitmapDisplayConfig r16, com.lidroid.xutils.BitmapUtils.BitmapLoadTask<?> r17) {
        /*
            Method dump skipped, instructions count: 243
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.lidroid.xutils.bitmap.core.BitmapCache.downloadBitmap(java.lang.String, com.lidroid.xutils.bitmap.BitmapDisplayConfig, com.lidroid.xutils.BitmapUtils$BitmapLoadTask):android.graphics.Bitmap");
    }

    private Bitmap addBitmapToMemoryCache(String uri, BitmapDisplayConfig config, Bitmap bitmap, long expiryTimestamp) throws IOException {
        BitmapFactory bitmapFactory;
        if (!(config == null || (bitmapFactory = config.getBitmapFactory()) == null)) {
            bitmap = bitmapFactory.cloneNew().createBitmap(bitmap);
        }
        if (!(uri == null || bitmap == null || !this.globalConfig.isMemoryCacheEnabled() || this.mMemoryCache == null)) {
            this.mMemoryCache.put(new MemoryCacheKey(uri, config), bitmap, expiryTimestamp);
        }
        return bitmap;
    }

    public Bitmap getBitmapFromMemCache(String uri, BitmapDisplayConfig config) {
        if (this.mMemoryCache == null || !this.globalConfig.isMemoryCacheEnabled()) {
            return null;
        }
        return this.mMemoryCache.get(new MemoryCacheKey(uri, config));
    }

    public File getBitmapFileFromDiskCache(String uri) {
        File cacheFile;
        synchronized (this.mDiskCacheLock) {
            cacheFile = this.mDiskLruCache != null ? this.mDiskLruCache.getCacheFile(uri, 0) : null;
        }
        return cacheFile;
    }

    public Bitmap getBitmapFromDiskCache(String uri, BitmapDisplayConfig config) {
        Bitmap bitmap;
        if (uri == null || !this.globalConfig.isDiskCacheEnabled()) {
            return null;
        }
        if (this.mDiskLruCache == null) {
            initDiskCache();
        }
        if (this.mDiskLruCache != null) {
            LruDiskCache.Snapshot snapshot = null;
            try {
                snapshot = this.mDiskLruCache.get(uri);
                if (snapshot != null) {
                    if (config == null || config.isShowOriginal()) {
                        bitmap = BitmapDecoder.decodeFileDescriptor(snapshot.getInputStream(0).getFD());
                    } else {
                        bitmap = BitmapDecoder.decodeSampledBitmapFromDescriptor(snapshot.getInputStream(0).getFD(), config.getBitmapMaxSize(), config.getBitmapConfig());
                    }
                    return addBitmapToMemoryCache(uri, config, rotateBitmapIfNeeded(uri, config, bitmap), this.mDiskLruCache.getExpiryTimestamp(uri));
                }
            } finally {
                IOUtils.closeQuietly(snapshot);
            }
        }
        return null;
    }

    public void clearCache() {
        clearMemoryCache();
        clearDiskCache();
    }

    public void clearMemoryCache() {
        if (this.mMemoryCache != null) {
            this.mMemoryCache.evictAll();
        }
    }

    public void clearDiskCache() {
        synchronized (this.mDiskCacheLock) {
            if (this.mDiskLruCache != null && !this.mDiskLruCache.isClosed()) {
                this.mDiskLruCache.delete();
                this.mDiskLruCache.close();
                this.mDiskLruCache = null;
            }
        }
        initDiskCache();
    }

    public void clearCache(String uri) {
        clearMemoryCache(uri);
        clearDiskCache(uri);
    }

    public void clearMemoryCache(String uri) {
        MemoryCacheKey key = new MemoryCacheKey(uri, null);
        if (this.mMemoryCache != null) {
            while (this.mMemoryCache.containsKey(key)) {
                this.mMemoryCache.remove(key);
            }
        }
    }

    public void clearDiskCache(String uri) {
        synchronized (this.mDiskCacheLock) {
            if (this.mDiskLruCache != null && !this.mDiskLruCache.isClosed()) {
                this.mDiskLruCache.remove(uri);
            }
        }
    }

    public void flush() {
        synchronized (this.mDiskCacheLock) {
            if (this.mDiskLruCache != null) {
                this.mDiskLruCache.flush();
            }
        }
    }

    public void close() {
        synchronized (this.mDiskCacheLock) {
            if (this.mDiskLruCache != null) {
                if (!this.mDiskLruCache.isClosed()) {
                    this.mDiskLruCache.close();
                }
                this.mDiskLruCache = null;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public class BitmapMeta {
        public byte[] data;
        public long expiryTimestamp;
        public FileInputStream inputStream;

        private BitmapMeta() {
        }
    }

    private Bitmap decodeBitmapMeta(BitmapMeta bitmapMeta, BitmapDisplayConfig config) throws IOException {
        if (bitmapMeta == null) {
            return null;
        }
        if (bitmapMeta.inputStream != null) {
            if (config == null || config.isShowOriginal()) {
                return BitmapDecoder.decodeFileDescriptor(bitmapMeta.inputStream.getFD());
            }
            return BitmapDecoder.decodeSampledBitmapFromDescriptor(bitmapMeta.inputStream.getFD(), config.getBitmapMaxSize(), config.getBitmapConfig());
        } else if (bitmapMeta.data == null) {
            return null;
        } else {
            if (config == null || config.isShowOriginal()) {
                return BitmapDecoder.decodeByteArray(bitmapMeta.data);
            }
            return BitmapDecoder.decodeSampledBitmapFromByteArray(bitmapMeta.data, config.getBitmapMaxSize(), config.getBitmapConfig());
        }
    }

    private synchronized Bitmap rotateBitmapIfNeeded(String uri, BitmapDisplayConfig config, Bitmap bitmap) {
        File bitmapFile;
        Bitmap result;
        int angle;
        Bitmap result2 = bitmap;
        if (config != null) {
            if (config.isAutoRotation() && (bitmapFile = getBitmapFileFromDiskCache(uri)) != null && bitmapFile.exists()) {
                try {
                    switch (new ExifInterface(bitmapFile.getPath()).getAttributeInt("Orientation", 0)) {
                        case 3:
                            angle = 180;
                            break;
                        case 4:
                        case 5:
                        case 7:
                        default:
                            angle = 0;
                            break;
                        case 6:
                            angle = 90;
                            break;
                        case 8:
                            angle = 270;
                            break;
                    }
                    if (angle != 0) {
                        Matrix m = new Matrix();
                        m.postRotate(angle);
                        result2 = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), m, true);
                        bitmap.recycle();
                    }
                } catch (Throwable th) {
                    result = result2;
                }
            }
        }
        result = result2;
        return result;
    }

    /* loaded from: classes2.dex */
    public class MemoryCacheKey {
        private String subKey;
        private String uri;

        private MemoryCacheKey(String uri, BitmapDisplayConfig config) {
            this.uri = uri;
            this.subKey = config == null ? null : config.toString();
        }

        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (!(o instanceof MemoryCacheKey)) {
                return false;
            }
            MemoryCacheKey that = (MemoryCacheKey) o;
            if (!this.uri.equals(that.uri)) {
                return false;
            }
            if (this.subKey == null || that.subKey == null) {
                return true;
            }
            return this.subKey.equals(that.subKey);
        }

        public int hashCode() {
            return this.uri.hashCode();
        }
    }
}
