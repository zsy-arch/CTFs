package com.em.video.util;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.TransitionDrawable;
import android.os.AsyncTask;
import android.support.v4.app.FragmentManager;
import android.widget.ImageView;
import com.em.video.util.ImageCache;
import java.lang.ref.WeakReference;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/* loaded from: classes.dex */
public abstract class ImageWorker {
    public static final Executor DUAL_THREAD_EXECUTOR = Executors.newFixedThreadPool(2);
    private static final int FADE_IN_TIME = 200;
    private static final int MESSAGE_CLEAR = 0;
    private static final int MESSAGE_CLOSE = 3;
    private static final int MESSAGE_FLUSH = 2;
    private static final int MESSAGE_INIT_DISK_CACHE = 1;
    private static final String TAG = "ImageWorker";
    private ImageCache mImageCache;
    private Bitmap mLoadingBitmap;
    protected Resources mResources;
    private boolean mFadeInBitmap = true;
    private boolean mExitTasksEarly = false;
    protected boolean mPauseWork = false;
    private final Object mPauseWorkLock = new Object();

    protected abstract Bitmap processBitmap(Object obj);

    /* JADX INFO: Access modifiers changed from: protected */
    public ImageWorker(Context context) {
        this.mResources = context.getResources();
    }

    public void loadImage(Object data, ImageView imageView) {
        if (data != null) {
            BitmapDrawable value = null;
            if (this.mImageCache != null) {
                value = this.mImageCache.getBitmapFromMemCache(String.valueOf(data));
            }
            if (value != null) {
                imageView.setImageDrawable(value);
            } else if (cancelPotentialWork(data, imageView)) {
                BitmapWorkerTask task = new BitmapWorkerTask(data, imageView);
                imageView.setImageDrawable(new AsyncDrawable(this.mResources, this.mLoadingBitmap, task));
                task.executeOnExecutor(DUAL_THREAD_EXECUTOR, new Void[0]);
            }
        }
    }

    public void setLoadingImage(Bitmap bitmap) {
        this.mLoadingBitmap = bitmap;
    }

    public void setLoadingImage(int resId) {
        this.mLoadingBitmap = BitmapFactory.decodeResource(this.mResources, resId);
    }

    public void addImageCache(FragmentManager fragmentManager, ImageCache.ImageCacheParams cacheParams) {
        this.mImageCache = ImageCache.getInstance(fragmentManager, cacheParams);
        new CacheAsyncTask().execute(1);
    }

    public void setImageFadeIn(boolean fadeIn) {
        this.mFadeInBitmap = fadeIn;
    }

    public void setExitTasksEarly(boolean exitTasksEarly) {
        this.mExitTasksEarly = exitTasksEarly;
        setPauseWork(false);
    }

    protected ImageCache getImageCache() {
        return this.mImageCache;
    }

    public static void cancelWork(ImageView imageView) {
        BitmapWorkerTask bitmapWorkerTask = getBitmapWorkerTask(imageView);
        if (bitmapWorkerTask != null) {
            bitmapWorkerTask.cancel(true);
        }
    }

    public static boolean cancelPotentialWork(Object data, ImageView imageView) {
        BitmapWorkerTask bitmapWorkerTask = getBitmapWorkerTask(imageView);
        if (bitmapWorkerTask == null) {
            return true;
        }
        Object bitmapData = bitmapWorkerTask.mData;
        if (bitmapData != null && bitmapData.equals(data)) {
            return false;
        }
        bitmapWorkerTask.cancel(true);
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static BitmapWorkerTask getBitmapWorkerTask(ImageView imageView) {
        if (imageView != null) {
            Drawable drawable = imageView.getDrawable();
            if (drawable instanceof AsyncDrawable) {
                return ((AsyncDrawable) drawable).getBitmapWorkerTask();
            }
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public class BitmapWorkerTask extends AsyncTask<Void, Void, BitmapDrawable> {
        private final WeakReference<ImageView> imageViewReference;
        private Object mData;

        public BitmapWorkerTask(Object data, ImageView imageView) {
            this.mData = data;
            this.imageViewReference = new WeakReference<>(imageView);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        public BitmapDrawable doInBackground(Void... params) {
            String dataString = String.valueOf(this.mData);
            Bitmap bitmap = null;
            BitmapDrawable drawable = null;
            synchronized (ImageWorker.this.mPauseWorkLock) {
                while (ImageWorker.this.mPauseWork && !isCancelled()) {
                    try {
                        ImageWorker.this.mPauseWorkLock.wait();
                    } catch (InterruptedException e) {
                    }
                }
            }
            if (0 == 0 && !isCancelled() && getAttachedImageView() != null && !ImageWorker.this.mExitTasksEarly) {
                bitmap = ImageWorker.this.processBitmap(this.mData);
            }
            if (bitmap != null) {
                if (Utils.hasHoneycomb()) {
                    drawable = new BitmapDrawable(ImageWorker.this.mResources, bitmap);
                } else {
                    drawable = new RecyclingBitmapDrawable(ImageWorker.this.mResources, bitmap);
                }
                if (ImageWorker.this.mImageCache != null) {
                    ImageWorker.this.mImageCache.addBitmapToCache(dataString, drawable);
                }
            }
            return drawable;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        public void onPostExecute(BitmapDrawable value) {
            if (isCancelled() || ImageWorker.this.mExitTasksEarly) {
                value = null;
            }
            ImageView imageView = getAttachedImageView();
            if (value != null && imageView != null) {
                ImageWorker.this.setImageDrawable(imageView, value);
            }
        }

        /* JADX INFO: Access modifiers changed from: protected */
        public void onCancelled(BitmapDrawable value) {
            super.onCancelled((BitmapWorkerTask) value);
            synchronized (ImageWorker.this.mPauseWorkLock) {
                ImageWorker.this.mPauseWorkLock.notifyAll();
            }
        }

        private ImageView getAttachedImageView() {
            ImageView imageView = this.imageViewReference.get();
            if (this == ImageWorker.getBitmapWorkerTask(imageView)) {
                return imageView;
            }
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class AsyncDrawable extends BitmapDrawable {
        private final WeakReference<BitmapWorkerTask> bitmapWorkerTaskReference;

        public AsyncDrawable(Resources res, Bitmap bitmap, BitmapWorkerTask bitmapWorkerTask) {
            super(res, bitmap);
            this.bitmapWorkerTaskReference = new WeakReference<>(bitmapWorkerTask);
        }

        public BitmapWorkerTask getBitmapWorkerTask() {
            return this.bitmapWorkerTaskReference.get();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setImageDrawable(ImageView imageView, Drawable drawable) {
        if (this.mFadeInBitmap) {
            TransitionDrawable td = new TransitionDrawable(new Drawable[]{new ColorDrawable(17170445), drawable});
            imageView.setBackgroundDrawable(new BitmapDrawable(this.mResources, this.mLoadingBitmap));
            imageView.setImageDrawable(td);
            td.startTransition(200);
            return;
        }
        imageView.setImageDrawable(drawable);
    }

    public void setPauseWork(boolean pauseWork) {
        synchronized (this.mPauseWorkLock) {
            this.mPauseWork = pauseWork;
            if (!this.mPauseWork) {
                this.mPauseWorkLock.notifyAll();
            }
        }
    }

    /* loaded from: classes.dex */
    protected class CacheAsyncTask extends AsyncTask<Object, Void, Void> {
        protected CacheAsyncTask() {
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public Void doInBackground(Object... params) {
            switch (((Integer) params[0]).intValue()) {
                case 0:
                    ImageWorker.this.clearCacheInternal();
                    return null;
                default:
                    return null;
            }
        }
    }

    protected void clearCacheInternal() {
        if (this.mImageCache != null) {
            this.mImageCache.clearCache();
        }
    }

    public void clearCache() {
        new CacheAsyncTask().execute(0);
    }

    public void flushCache() {
        new CacheAsyncTask().execute(2);
    }

    public void closeCache() {
        new CacheAsyncTask().execute(3);
    }
}
