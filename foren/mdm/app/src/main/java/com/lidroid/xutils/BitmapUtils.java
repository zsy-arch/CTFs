package com.lidroid.xutils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.view.View;
import android.view.animation.Animation;
import com.lidroid.xutils.bitmap.BitmapCacheListener;
import com.lidroid.xutils.bitmap.BitmapCommonUtils;
import com.lidroid.xutils.bitmap.BitmapDisplayConfig;
import com.lidroid.xutils.bitmap.BitmapGlobalConfig;
import com.lidroid.xutils.bitmap.callback.BitmapLoadCallBack;
import com.lidroid.xutils.bitmap.callback.BitmapLoadFrom;
import com.lidroid.xutils.bitmap.callback.DefaultBitmapLoadCallBack;
import com.lidroid.xutils.bitmap.core.AsyncDrawable;
import com.lidroid.xutils.bitmap.core.BitmapSize;
import com.lidroid.xutils.bitmap.download.Downloader;
import com.lidroid.xutils.cache.FileNameGenerator;
import com.lidroid.xutils.task.PriorityAsyncTask;
import com.lidroid.xutils.task.PriorityExecutor;
import com.lidroid.xutils.task.TaskHandler;
import java.io.File;
import java.lang.ref.WeakReference;

/* loaded from: classes2.dex */
public class BitmapUtils implements TaskHandler {
    private boolean cancelAllTask;
    private Context context;
    private BitmapDisplayConfig defaultDisplayConfig;
    private BitmapGlobalConfig globalConfig;
    private boolean pauseTask;
    private final Object pauseTaskLock;

    public BitmapUtils(Context context) {
        this(context, null);
    }

    public BitmapUtils(Context context, String diskCachePath) {
        this.pauseTask = false;
        this.cancelAllTask = false;
        this.pauseTaskLock = new Object();
        if (context == null) {
            throw new IllegalArgumentException("context may not be null");
        }
        this.context = context.getApplicationContext();
        this.globalConfig = BitmapGlobalConfig.getInstance(this.context, diskCachePath);
        this.defaultDisplayConfig = new BitmapDisplayConfig();
    }

    public BitmapUtils(Context context, String diskCachePath, int memoryCacheSize) {
        this(context, diskCachePath);
        this.globalConfig.setMemoryCacheSize(memoryCacheSize);
    }

    public BitmapUtils(Context context, String diskCachePath, int memoryCacheSize, int diskCacheSize) {
        this(context, diskCachePath);
        this.globalConfig.setMemoryCacheSize(memoryCacheSize);
        this.globalConfig.setDiskCacheSize(diskCacheSize);
    }

    public BitmapUtils(Context context, String diskCachePath, float memoryCachePercent) {
        this(context, diskCachePath);
        this.globalConfig.setMemCacheSizePercent(memoryCachePercent);
    }

    public BitmapUtils(Context context, String diskCachePath, float memoryCachePercent, int diskCacheSize) {
        this(context, diskCachePath);
        this.globalConfig.setMemCacheSizePercent(memoryCachePercent);
        this.globalConfig.setDiskCacheSize(diskCacheSize);
    }

    public BitmapUtils configDefaultLoadingImage(Drawable drawable) {
        this.defaultDisplayConfig.setLoadingDrawable(drawable);
        return this;
    }

    public BitmapUtils configDefaultLoadingImage(int resId) {
        if (resId != 0) {
            this.defaultDisplayConfig.setLoadingDrawable(this.context.getResources().getDrawable(resId));
        }
        return this;
    }

    public BitmapUtils configDefaultLoadingImage(Bitmap bitmap) {
        this.defaultDisplayConfig.setLoadingDrawable(new BitmapDrawable(this.context.getResources(), bitmap));
        return this;
    }

    public BitmapUtils configDefaultLoadFailedImage(Drawable drawable) {
        this.defaultDisplayConfig.setLoadFailedDrawable(drawable);
        return this;
    }

    public BitmapUtils configDefaultLoadFailedImage(int resId) {
        if (resId != 0) {
            this.defaultDisplayConfig.setLoadFailedDrawable(this.context.getResources().getDrawable(resId));
        }
        return this;
    }

    public BitmapUtils configDefaultLoadFailedImage(Bitmap bitmap) {
        this.defaultDisplayConfig.setLoadFailedDrawable(new BitmapDrawable(this.context.getResources(), bitmap));
        return this;
    }

    public BitmapUtils configDefaultBitmapMaxSize(int maxWidth, int maxHeight) {
        this.defaultDisplayConfig.setBitmapMaxSize(new BitmapSize(maxWidth, maxHeight));
        return this;
    }

    public BitmapUtils configDefaultBitmapMaxSize(BitmapSize maxSize) {
        this.defaultDisplayConfig.setBitmapMaxSize(maxSize);
        return this;
    }

    public BitmapUtils configDefaultImageLoadAnimation(Animation animation) {
        this.defaultDisplayConfig.setAnimation(animation);
        return this;
    }

    public BitmapUtils configDefaultAutoRotation(boolean autoRotation) {
        this.defaultDisplayConfig.setAutoRotation(autoRotation);
        return this;
    }

    public BitmapUtils configDefaultShowOriginal(boolean showOriginal) {
        this.defaultDisplayConfig.setShowOriginal(showOriginal);
        return this;
    }

    public BitmapUtils configDefaultBitmapConfig(Bitmap.Config config) {
        this.defaultDisplayConfig.setBitmapConfig(config);
        return this;
    }

    public BitmapUtils configDefaultDisplayConfig(BitmapDisplayConfig displayConfig) {
        this.defaultDisplayConfig = displayConfig;
        return this;
    }

    public BitmapUtils configDownloader(Downloader downloader) {
        this.globalConfig.setDownloader(downloader);
        return this;
    }

    public BitmapUtils configDefaultCacheExpiry(long defaultExpiry) {
        this.globalConfig.setDefaultCacheExpiry(defaultExpiry);
        return this;
    }

    public BitmapUtils configDefaultConnectTimeout(int connectTimeout) {
        this.globalConfig.setDefaultConnectTimeout(connectTimeout);
        return this;
    }

    public BitmapUtils configDefaultReadTimeout(int readTimeout) {
        this.globalConfig.setDefaultReadTimeout(readTimeout);
        return this;
    }

    public BitmapUtils configThreadPoolSize(int threadPoolSize) {
        this.globalConfig.setThreadPoolSize(threadPoolSize);
        return this;
    }

    public BitmapUtils configMemoryCacheEnabled(boolean enabled) {
        this.globalConfig.setMemoryCacheEnabled(enabled);
        return this;
    }

    public BitmapUtils configDiskCacheEnabled(boolean enabled) {
        this.globalConfig.setDiskCacheEnabled(enabled);
        return this;
    }

    public BitmapUtils configDiskCacheFileNameGenerator(FileNameGenerator fileNameGenerator) {
        this.globalConfig.setFileNameGenerator(fileNameGenerator);
        return this;
    }

    public BitmapUtils configBitmapCacheListener(BitmapCacheListener listener) {
        this.globalConfig.setBitmapCacheListener(listener);
        return this;
    }

    public <T extends View> void display(T container, String uri) {
        display(container, uri, null, null);
    }

    public <T extends View> void display(T container, String uri, BitmapDisplayConfig displayConfig) {
        display(container, uri, displayConfig, null);
    }

    public <T extends View> void display(T container, String uri, BitmapLoadCallBack<T> callBack) {
        display(container, uri, null, callBack);
    }

    public <T extends View> void display(T container, String uri, BitmapDisplayConfig displayConfig, BitmapLoadCallBack<T> callBack) {
        if (container != null) {
            if (callBack == null) {
                callBack = new DefaultBitmapLoadCallBack<>();
            }
            if (displayConfig == null || displayConfig == this.defaultDisplayConfig) {
                displayConfig = this.defaultDisplayConfig.cloneNew();
            }
            BitmapSize size = displayConfig.getBitmapMaxSize();
            displayConfig.setBitmapMaxSize(BitmapCommonUtils.optimizeMaxSizeByView(container, size.getWidth(), size.getHeight()));
            container.clearAnimation();
            if (TextUtils.isEmpty(uri)) {
                callBack.onLoadFailed(container, uri, displayConfig.getLoadFailedDrawable());
                return;
            }
            callBack.onPreLoad(container, uri, displayConfig);
            Bitmap bitmap = this.globalConfig.getBitmapCache().getBitmapFromMemCache(uri, displayConfig);
            if (bitmap != null) {
                callBack.onLoadStarted(container, uri, displayConfig);
                callBack.onLoadCompleted(container, uri, bitmap, displayConfig, BitmapLoadFrom.MEMORY_CACHE);
            } else if (!bitmapLoadTaskExist(container, uri, callBack)) {
                BitmapLoadTask<T> loadTask = new BitmapLoadTask<>(container, uri, displayConfig, callBack);
                PriorityExecutor executor = this.globalConfig.getBitmapLoadExecutor();
                File diskCacheFile = getBitmapFileFromDiskCache(uri);
                if ((diskCacheFile != null && diskCacheFile.exists()) && executor.isBusy()) {
                    executor = this.globalConfig.getDiskCacheExecutor();
                }
                callBack.setDrawable(container, new AsyncDrawable(displayConfig.getLoadingDrawable(), loadTask));
                loadTask.setPriority(displayConfig.getPriority());
                loadTask.executeOnExecutor(executor, new Object[0]);
            }
        }
    }

    public void clearCache() {
        this.globalConfig.clearCache();
    }

    public void clearMemoryCache() {
        this.globalConfig.clearMemoryCache();
    }

    public void clearDiskCache() {
        this.globalConfig.clearDiskCache();
    }

    public void clearCache(String uri) {
        this.globalConfig.clearCache(uri);
    }

    public void clearMemoryCache(String uri) {
        this.globalConfig.clearMemoryCache(uri);
    }

    public void clearDiskCache(String uri) {
        this.globalConfig.clearDiskCache(uri);
    }

    public void flushCache() {
        this.globalConfig.flushCache();
    }

    public void closeCache() {
        this.globalConfig.closeCache();
    }

    public File getBitmapFileFromDiskCache(String uri) {
        return this.globalConfig.getBitmapCache().getBitmapFileFromDiskCache(uri);
    }

    public Bitmap getBitmapFromMemCache(String uri, BitmapDisplayConfig config) {
        if (config == null) {
            config = this.defaultDisplayConfig;
        }
        return this.globalConfig.getBitmapCache().getBitmapFromMemCache(uri, config);
    }

    @Override // com.lidroid.xutils.task.TaskHandler
    public boolean supportPause() {
        return true;
    }

    @Override // com.lidroid.xutils.task.TaskHandler
    public boolean supportResume() {
        return true;
    }

    @Override // com.lidroid.xutils.task.TaskHandler
    public boolean supportCancel() {
        return true;
    }

    @Override // com.lidroid.xutils.task.TaskHandler
    public void pause() {
        this.pauseTask = true;
        flushCache();
    }

    @Override // com.lidroid.xutils.task.TaskHandler
    public void resume() {
        this.pauseTask = false;
        synchronized (this.pauseTaskLock) {
            this.pauseTaskLock.notifyAll();
        }
    }

    @Override // com.lidroid.xutils.task.TaskHandler
    public void cancel() {
        this.pauseTask = true;
        this.cancelAllTask = true;
        synchronized (this.pauseTaskLock) {
            this.pauseTaskLock.notifyAll();
        }
    }

    @Override // com.lidroid.xutils.task.TaskHandler
    public boolean isPaused() {
        return this.pauseTask;
    }

    @Override // com.lidroid.xutils.task.TaskHandler
    public boolean isCancelled() {
        return this.cancelAllTask;
    }

    public static <T extends View> BitmapLoadTask<T> getBitmapTaskFromContainer(T container, BitmapLoadCallBack<T> callBack) {
        if (container != null) {
            Drawable drawable = callBack.getDrawable(container);
            if (drawable instanceof AsyncDrawable) {
                return ((AsyncDrawable) drawable).getBitmapWorkerTask();
            }
        }
        return null;
    }

    private static <T extends View> boolean bitmapLoadTaskExist(T container, String uri, BitmapLoadCallBack<T> callBack) {
        BitmapLoadTask<T> oldLoadTask = getBitmapTaskFromContainer(container, callBack);
        if (oldLoadTask != null) {
            String oldUrl = ((BitmapLoadTask) oldLoadTask).uri;
            if (!TextUtils.isEmpty(oldUrl) && oldUrl.equals(uri)) {
                return true;
            }
            oldLoadTask.cancel(true);
        }
        return false;
    }

    /* loaded from: classes2.dex */
    public class BitmapLoadTask<T extends View> extends PriorityAsyncTask<Object, Object, Bitmap> {
        private static final int PROGRESS_LOADING = 1;
        private static final int PROGRESS_LOAD_STARTED = 0;
        private final BitmapLoadCallBack<T> callBack;
        private final WeakReference<T> containerReference;
        private final BitmapDisplayConfig displayConfig;
        private BitmapLoadFrom from = BitmapLoadFrom.DISK_CACHE;
        private final String uri;

        public BitmapLoadTask(T container, String uri, BitmapDisplayConfig config, BitmapLoadCallBack<T> callBack) {
            BitmapUtils.this = this$0;
            if (container == null || uri == null || config == null || callBack == null) {
                throw new IllegalArgumentException("args may not be null");
            }
            this.containerReference = new WeakReference<>(container);
            this.callBack = callBack;
            this.uri = uri;
            this.displayConfig = config;
        }

        @Override // com.lidroid.xutils.task.PriorityAsyncTask
        public Bitmap doInBackground(Object... params) {
            synchronized (BitmapUtils.this.pauseTaskLock) {
                while (BitmapUtils.this.pauseTask && !isCancelled()) {
                    try {
                        BitmapUtils.this.pauseTaskLock.wait();
                    } catch (Throwable th) {
                    }
                    if (BitmapUtils.this.cancelAllTask) {
                        return null;
                    }
                }
                Bitmap bitmap = null;
                if (!isCancelled() && getTargetContainer() != null) {
                    publishProgress(0);
                    bitmap = BitmapUtils.this.globalConfig.getBitmapCache().getBitmapFromDiskCache(this.uri, this.displayConfig);
                }
                if (bitmap != null || isCancelled() || getTargetContainer() == null) {
                    return bitmap;
                }
                Bitmap bitmap2 = BitmapUtils.this.globalConfig.getBitmapCache().downloadBitmap(this.uri, this.displayConfig, this);
                this.from = BitmapLoadFrom.URI;
                return bitmap2;
            }
        }

        public void updateProgress(long total, long current) {
            publishProgress(1, Long.valueOf(total), Long.valueOf(current));
        }

        @Override // com.lidroid.xutils.task.PriorityAsyncTask
        protected void onProgressUpdate(Object... values) {
            T container;
            if (values != null && values.length != 0 && (container = getTargetContainer()) != null) {
                switch (((Integer) values[0]).intValue()) {
                    case 0:
                        this.callBack.onLoadStarted(container, this.uri, this.displayConfig);
                        return;
                    case 1:
                        if (values.length == 3) {
                            this.callBack.onLoading(container, this.uri, this.displayConfig, ((Long) values[1]).longValue(), ((Long) values[2]).longValue());
                            return;
                        }
                        return;
                    default:
                        return;
                }
            }
        }

        public void onPostExecute(Bitmap bitmap) {
            T container = getTargetContainer();
            if (container == null) {
                return;
            }
            if (bitmap != null) {
                this.callBack.onLoadCompleted(container, this.uri, bitmap, this.displayConfig, this.from);
            } else {
                this.callBack.onLoadFailed(container, this.uri, this.displayConfig.getLoadFailedDrawable());
            }
        }

        public void onCancelled(Bitmap bitmap) {
            synchronized (BitmapUtils.this.pauseTaskLock) {
                BitmapUtils.this.pauseTaskLock.notifyAll();
            }
        }

        public T getTargetContainer() {
            T container = this.containerReference.get();
            if (this == BitmapUtils.getBitmapTaskFromContainer(container, this.callBack)) {
                return container;
            }
            return null;
        }
    }
}
