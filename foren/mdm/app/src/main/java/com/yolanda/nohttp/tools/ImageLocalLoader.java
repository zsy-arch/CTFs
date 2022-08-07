package com.yolanda.nohttp.tools;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.util.DisplayMetrics;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.yolanda.nohttp.Logger;
import com.yolanda.nohttp.NoHttp;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/* loaded from: classes2.dex */
public class ImageLocalLoader {
    private static final Object SINGLE_OBJECT = new Object();
    private static ImageLocalLoader mInstance;
    private Drawable mDefaultDrawable = new ColorDrawable(-7829368);
    private ExecutorService mExecutorService = Executors.newSingleThreadExecutor();
    private LruCache<String, Bitmap> mLruCache = new LruCache<String, Bitmap>((int) (Runtime.getRuntime().maxMemory() / 8)) { // from class: com.yolanda.nohttp.tools.ImageLocalLoader.1
        /* JADX INFO: Access modifiers changed from: protected */
        @SuppressLint({"NewApi"})
        public int sizeOf(String key, Bitmap value) {
            return Build.VERSION.SDK_INT >= 19 ? value.getByteCount() : value.getRowBytes() * value.getHeight();
        }
    };
    private Handler mPosterHandler;

    /* loaded from: classes2.dex */
    public interface ImageLoadListener {
        void onLoadFailed(ImageView imageView, String str);

        void onLoadSucceed(ImageView imageView, Bitmap bitmap, String str);
    }

    public static ImageLocalLoader getInstance() {
        synchronized (SINGLE_OBJECT) {
            if (mInstance == null) {
                mInstance = new ImageLocalLoader();
            }
        }
        return mInstance;
    }

    private ImageLocalLoader() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Handler getPostHandler() {
        synchronized (SINGLE_OBJECT) {
            if (this.mPosterHandler == null) {
                this.mPosterHandler = new Handler(Looper.getMainLooper());
            }
        }
        return this.mPosterHandler;
    }

    public Bitmap readImage(String imagePath, int maxWidth, int maxHeight) {
        File imageFile = new File(imagePath);
        if (imageFile.exists()) {
            try {
                BufferedInputStream inputStream = new BufferedInputStream(new FileInputStream(imageFile));
                BitmapFactory.Options options = new BitmapFactory.Options();
                options.inJustDecodeBounds = true;
                BitmapFactory.decodeStream(inputStream, null, options);
                inputStream.close();
                int i = 0;
                while (true) {
                    if ((options.outWidth >> i) > maxWidth || (options.outHeight >> i) > maxHeight) {
                        i++;
                    } else {
                        BufferedInputStream inputStream2 = new BufferedInputStream(new FileInputStream(new File(imagePath)));
                        options.inSampleSize = (int) Math.pow(2.0d, i);
                        options.inJustDecodeBounds = false;
                        Bitmap bitmap = BitmapFactory.decodeStream(inputStream2, null, options);
                        inputStream2.close();
                        return bitmap;
                    }
                }
            } catch (IOException e) {
                Logger.e(e, "This path does not exist" + imagePath + ".");
            }
        }
        return null;
    }

    public void measureSize(ImageView imageView, int[] viewSizes) {
        DisplayMetrics displayMetrics = NoHttp.getContext().getResources().getDisplayMetrics();
        ViewGroup.LayoutParams params = imageView.getLayoutParams();
        int width = params.width == -2 ? 0 : imageView.getWidth();
        if (width <= 0) {
            width = params.width;
        }
        if (width <= 0) {
            width = displayMetrics.widthPixels;
        }
        int height = params.height == -2 ? 0 : imageView.getHeight();
        if (height <= 0) {
            height = params.height;
        }
        if (height <= 0) {
            height = displayMetrics.heightPixels;
        }
        viewSizes[0] = width;
        viewSizes[1] = height;
    }

    @SuppressLint({"NewApi"})
    public void setDefaultImage(int resId) {
        this.mDefaultDrawable = ResCompat.getDrawable(resId);
    }

    public void setDefaultImageColor(int color) {
        this.mDefaultDrawable = new ColorDrawable(color);
    }

    public void loadImage(ImageView imageView, String imagePath) {
        loadImage(imageView, imagePath, 0, 0, null);
    }

    public void loadImage(ImageView imageView, String imagePath, ImageLoadListener imageLoadListener) {
        loadImage(imageView, imagePath, 0, 0, imageLoadListener);
    }

    public void loadImage(ImageView imageView, String imagePath, int width, int height) {
        loadImage(imageView, imagePath, width, height, null);
    }

    public void loadImage(ImageView imageView, String imagePath, int width, int height, ImageLoadListener imageLoadListener) {
        if (imageLoadListener == null) {
            imageView.setTag(imagePath);
        }
        Bitmap bitmap = getImageFromCache(imagePath + width + height);
        if (bitmap != null) {
            ImgBeanHolder holder = new ImgBeanHolder();
            holder.imageView = imageView;
            holder.imagePath = imagePath;
            holder.bitmap = bitmap;
            holder.imageLoadListener = imageLoadListener;
            getPostHandler().post(holder);
            return;
        }
        imageView.setImageDrawable(this.mDefaultDrawable);
        this.mExecutorService.execute(new TaskThread(imageView, imagePath, width, height, imageLoadListener));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Bitmap getImageFromCache(String key) {
        return this.mLruCache.get(key);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addImageToCache(String key, Bitmap bitmap) {
        if (getImageFromCache(key) == null && bitmap != null) {
            this.mLruCache.put(key, bitmap);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public class TaskThread implements Runnable {
        private int height;
        private ImageLoadListener imageLoadListener;
        private String mImagePath;
        private ImageView mImageView;
        private int width;

        TaskThread(ImageView imageView, String imagePath, int width, int height, ImageLoadListener imageLoadListener) {
            this.mImagePath = imagePath;
            this.mImageView = imageView;
            this.width = width;
            this.height = height;
            this.imageLoadListener = imageLoadListener;
        }

        @Override // java.lang.Runnable
        public void run() {
            Bitmap bitmap;
            if (this.width == 0 || this.height == 0) {
                int[] viewSizes = new int[2];
                ImageLocalLoader.this.measureSize(this.mImageView, viewSizes);
                bitmap = ImageLocalLoader.this.readImage(this.mImagePath, viewSizes[0], viewSizes[1]);
            } else {
                bitmap = ImageLocalLoader.this.readImage(this.mImagePath, this.width, this.height);
            }
            ImageLocalLoader.this.addImageToCache(this.mImagePath + this.width + this.height, bitmap);
            ImgBeanHolder holder = new ImgBeanHolder();
            holder.bitmap = ImageLocalLoader.this.getImageFromCache(this.mImagePath + this.width + this.height);
            holder.imageView = this.mImageView;
            holder.imagePath = this.mImagePath;
            holder.imageLoadListener = this.imageLoadListener;
            ImageLocalLoader.this.getPostHandler().post(holder);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public class ImgBeanHolder implements Runnable {
        Bitmap bitmap;
        ImageLoadListener imageLoadListener;
        String imagePath;
        ImageView imageView;

        private ImgBeanHolder() {
        }

        @Override // java.lang.Runnable
        public void run() {
            if (this.imageLoadListener == null) {
                if (this.imagePath.equals(this.imageView.getTag())) {
                    this.imageView.setImageBitmap(this.bitmap);
                }
            } else if (this.bitmap != null) {
                this.imageLoadListener.onLoadSucceed(this.imageView, this.bitmap, this.imagePath);
            } else {
                this.imageLoadListener.onLoadFailed(this.imageView, this.imagePath);
            }
        }
    }
}
