package com.lidroid.xutils.bitmap;

import android.app.ActivityManager;
import android.content.Context;
import android.text.TextUtils;
import com.lidroid.xutils.bitmap.core.BitmapCache;
import com.lidroid.xutils.bitmap.download.DefaultDownloader;
import com.lidroid.xutils.bitmap.download.Downloader;
import com.lidroid.xutils.cache.FileNameGenerator;
import com.lidroid.xutils.task.Priority;
import com.lidroid.xutils.task.PriorityAsyncTask;
import com.lidroid.xutils.task.PriorityExecutor;
import com.lidroid.xutils.util.LogUtils;
import com.lidroid.xutils.util.OtherUtils;
import java.util.HashMap;

/* loaded from: classes2.dex */
public class BitmapGlobalConfig {
    private static final int DEFAULT_POOL_SIZE = 5;
    public static final int MIN_DISK_CACHE_SIZE = 10485760;
    public static final int MIN_MEMORY_CACHE_SIZE = 2097152;
    private BitmapCache bitmapCache;
    private BitmapCacheListener bitmapCacheListener;
    private String diskCachePath;
    private Downloader downloader;
    private FileNameGenerator fileNameGenerator;
    private Context mContext;
    private static final PriorityExecutor BITMAP_LOAD_EXECUTOR = new PriorityExecutor(5);
    private static final PriorityExecutor DISK_CACHE_EXECUTOR = new PriorityExecutor(2);
    private static final HashMap<String, BitmapGlobalConfig> configMap = new HashMap<>(1);
    private int memoryCacheSize = 4194304;
    private int diskCacheSize = 52428800;
    private boolean memoryCacheEnabled = true;
    private boolean diskCacheEnabled = true;
    private long defaultCacheExpiry = 2592000000L;
    private int defaultConnectTimeout = 15000;
    private int defaultReadTimeout = 15000;

    private BitmapGlobalConfig(Context context, String diskCachePath) {
        if (context == null) {
            throw new IllegalArgumentException("context may not be null");
        }
        this.mContext = context;
        this.diskCachePath = diskCachePath;
        initBitmapCache();
    }

    public static synchronized BitmapGlobalConfig getInstance(Context context, String diskCachePath) {
        BitmapGlobalConfig bitmapGlobalConfig;
        synchronized (BitmapGlobalConfig.class) {
            if (TextUtils.isEmpty(diskCachePath)) {
                diskCachePath = OtherUtils.getDiskCacheDir(context, "xBitmapCache");
            }
            if (configMap.containsKey(diskCachePath)) {
                bitmapGlobalConfig = configMap.get(diskCachePath);
            } else {
                BitmapGlobalConfig config = new BitmapGlobalConfig(context, diskCachePath);
                configMap.put(diskCachePath, config);
                bitmapGlobalConfig = config;
            }
        }
        return bitmapGlobalConfig;
    }

    private void initBitmapCache() {
        new BitmapCacheManagementTask().execute(0);
        new BitmapCacheManagementTask().execute(1);
    }

    public String getDiskCachePath() {
        return this.diskCachePath;
    }

    public Downloader getDownloader() {
        if (this.downloader == null) {
            this.downloader = new DefaultDownloader();
        }
        this.downloader.setContext(this.mContext);
        this.downloader.setDefaultExpiry(getDefaultCacheExpiry());
        this.downloader.setDefaultConnectTimeout(getDefaultConnectTimeout());
        this.downloader.setDefaultReadTimeout(getDefaultReadTimeout());
        return this.downloader;
    }

    public void setDownloader(Downloader downloader) {
        this.downloader = downloader;
    }

    public long getDefaultCacheExpiry() {
        return this.defaultCacheExpiry;
    }

    public void setDefaultCacheExpiry(long defaultCacheExpiry) {
        this.defaultCacheExpiry = defaultCacheExpiry;
    }

    public int getDefaultConnectTimeout() {
        return this.defaultConnectTimeout;
    }

    public void setDefaultConnectTimeout(int defaultConnectTimeout) {
        this.defaultConnectTimeout = defaultConnectTimeout;
    }

    public int getDefaultReadTimeout() {
        return this.defaultReadTimeout;
    }

    public void setDefaultReadTimeout(int defaultReadTimeout) {
        this.defaultReadTimeout = defaultReadTimeout;
    }

    public BitmapCache getBitmapCache() {
        if (this.bitmapCache == null) {
            this.bitmapCache = new BitmapCache(this);
        }
        return this.bitmapCache;
    }

    public int getMemoryCacheSize() {
        return this.memoryCacheSize;
    }

    public void setMemoryCacheSize(int memoryCacheSize) {
        if (memoryCacheSize >= 2097152) {
            this.memoryCacheSize = memoryCacheSize;
            if (this.bitmapCache != null) {
                this.bitmapCache.setMemoryCacheSize(this.memoryCacheSize);
                return;
            }
            return;
        }
        setMemCacheSizePercent(0.3f);
    }

    public void setMemCacheSizePercent(float percent) {
        if (percent < 0.05f || percent > 0.8f) {
            throw new IllegalArgumentException("percent must be between 0.05 and 0.8 (inclusive)");
        }
        this.memoryCacheSize = Math.round(getMemoryClass() * percent * 1024.0f * 1024.0f);
        if (this.bitmapCache != null) {
            this.bitmapCache.setMemoryCacheSize(this.memoryCacheSize);
        }
    }

    public int getDiskCacheSize() {
        return this.diskCacheSize;
    }

    public void setDiskCacheSize(int diskCacheSize) {
        if (diskCacheSize >= 10485760) {
            this.diskCacheSize = diskCacheSize;
            if (this.bitmapCache != null) {
                this.bitmapCache.setDiskCacheSize(this.diskCacheSize);
            }
        }
    }

    public int getThreadPoolSize() {
        return BITMAP_LOAD_EXECUTOR.getPoolSize();
    }

    public void setThreadPoolSize(int threadPoolSize) {
        BITMAP_LOAD_EXECUTOR.setPoolSize(threadPoolSize);
    }

    public PriorityExecutor getBitmapLoadExecutor() {
        return BITMAP_LOAD_EXECUTOR;
    }

    public PriorityExecutor getDiskCacheExecutor() {
        return DISK_CACHE_EXECUTOR;
    }

    public boolean isMemoryCacheEnabled() {
        return this.memoryCacheEnabled;
    }

    public void setMemoryCacheEnabled(boolean memoryCacheEnabled) {
        this.memoryCacheEnabled = memoryCacheEnabled;
    }

    public boolean isDiskCacheEnabled() {
        return this.diskCacheEnabled;
    }

    public void setDiskCacheEnabled(boolean diskCacheEnabled) {
        this.diskCacheEnabled = diskCacheEnabled;
    }

    public FileNameGenerator getFileNameGenerator() {
        return this.fileNameGenerator;
    }

    public void setFileNameGenerator(FileNameGenerator fileNameGenerator) {
        this.fileNameGenerator = fileNameGenerator;
        if (this.bitmapCache != null) {
            this.bitmapCache.setDiskCacheFileNameGenerator(fileNameGenerator);
        }
    }

    public BitmapCacheListener getBitmapCacheListener() {
        return this.bitmapCacheListener;
    }

    public void setBitmapCacheListener(BitmapCacheListener bitmapCacheListener) {
        this.bitmapCacheListener = bitmapCacheListener;
    }

    private int getMemoryClass() {
        return ((ActivityManager) this.mContext.getSystemService("activity")).getMemoryClass();
    }

    /* loaded from: classes2.dex */
    public class BitmapCacheManagementTask extends PriorityAsyncTask<Object, Void, Object[]> {
        public static final int MESSAGE_CLEAR = 4;
        public static final int MESSAGE_CLEAR_BY_KEY = 7;
        public static final int MESSAGE_CLEAR_DISK = 6;
        public static final int MESSAGE_CLEAR_DISK_BY_KEY = 9;
        public static final int MESSAGE_CLEAR_MEMORY = 5;
        public static final int MESSAGE_CLEAR_MEMORY_BY_KEY = 8;
        public static final int MESSAGE_CLOSE = 3;
        public static final int MESSAGE_FLUSH = 2;
        public static final int MESSAGE_INIT_DISK_CACHE = 1;
        public static final int MESSAGE_INIT_MEMORY_CACHE = 0;

        private BitmapCacheManagementTask() {
            BitmapGlobalConfig.this = r2;
            setPriority(Priority.UI_TOP);
        }

        @Override // com.lidroid.xutils.task.PriorityAsyncTask
        public Object[] doInBackground(Object... params) {
            BitmapCache cache;
            if (!(params == null || params.length == 0 || (cache = BitmapGlobalConfig.this.getBitmapCache()) == null)) {
                try {
                    switch (((Integer) params[0]).intValue()) {
                        case 0:
                            cache.initMemoryCache();
                            break;
                        case 1:
                            cache.initDiskCache();
                            break;
                        case 2:
                            cache.flush();
                            break;
                        case 3:
                            cache.clearMemoryCache();
                            cache.close();
                            break;
                        case 4:
                            cache.clearCache();
                            break;
                        case 5:
                            cache.clearMemoryCache();
                            break;
                        case 6:
                            cache.clearDiskCache();
                            break;
                        case 7:
                            if (params.length == 2) {
                                cache.clearCache(String.valueOf(params[1]));
                                break;
                            }
                            break;
                        case 8:
                            if (params.length == 2) {
                                cache.clearMemoryCache(String.valueOf(params[1]));
                                break;
                            }
                            break;
                        case 9:
                            if (params.length == 2) {
                                cache.clearDiskCache(String.valueOf(params[1]));
                                break;
                            }
                            break;
                    }
                } catch (Throwable e) {
                    LogUtils.e(e.getMessage(), e);
                }
            }
            return params;
        }

        public void onPostExecute(Object[] params) {
            if (BitmapGlobalConfig.this.bitmapCacheListener != null && params != null && params.length != 0) {
                try {
                    switch (((Integer) params[0]).intValue()) {
                        case 0:
                            BitmapGlobalConfig.this.bitmapCacheListener.onInitMemoryCacheFinished();
                            break;
                        case 1:
                            BitmapGlobalConfig.this.bitmapCacheListener.onInitDiskFinished();
                            break;
                        case 2:
                            BitmapGlobalConfig.this.bitmapCacheListener.onFlushCacheFinished();
                            break;
                        case 3:
                            BitmapGlobalConfig.this.bitmapCacheListener.onCloseCacheFinished();
                            break;
                        case 4:
                            BitmapGlobalConfig.this.bitmapCacheListener.onClearCacheFinished();
                            break;
                        case 5:
                            BitmapGlobalConfig.this.bitmapCacheListener.onClearMemoryCacheFinished();
                            break;
                        case 6:
                            BitmapGlobalConfig.this.bitmapCacheListener.onClearDiskCacheFinished();
                            break;
                        case 7:
                            if (params.length == 2) {
                                BitmapGlobalConfig.this.bitmapCacheListener.onClearCacheFinished(String.valueOf(params[1]));
                                break;
                            }
                            break;
                        case 8:
                            if (params.length == 2) {
                                BitmapGlobalConfig.this.bitmapCacheListener.onClearMemoryCacheFinished(String.valueOf(params[1]));
                                break;
                            }
                            break;
                        case 9:
                            if (params.length == 2) {
                                BitmapGlobalConfig.this.bitmapCacheListener.onClearDiskCacheFinished(String.valueOf(params[1]));
                                break;
                            }
                            break;
                    }
                } catch (Throwable e) {
                    LogUtils.e(e.getMessage(), e);
                }
            }
        }
    }

    public void clearCache() {
        new BitmapCacheManagementTask().execute(4);
    }

    public void clearMemoryCache() {
        new BitmapCacheManagementTask().execute(5);
    }

    public void clearDiskCache() {
        new BitmapCacheManagementTask().execute(6);
    }

    public void clearCache(String uri) {
        new BitmapCacheManagementTask().execute(7, uri);
    }

    public void clearMemoryCache(String uri) {
        new BitmapCacheManagementTask().execute(8, uri);
    }

    public void clearDiskCache(String uri) {
        new BitmapCacheManagementTask().execute(9, uri);
    }

    public void flushCache() {
        new BitmapCacheManagementTask().execute(2);
    }

    public void closeCache() {
        new BitmapCacheManagementTask().execute(3);
    }
}
