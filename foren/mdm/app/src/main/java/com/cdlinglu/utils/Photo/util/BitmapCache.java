package com.cdlinglu.utils.Photo.util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.widget.ImageView;
import com.vsf2f.f2f.ui.utils.ThreadPool;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.ref.SoftReference;
import java.util.HashMap;

/* loaded from: classes.dex */
public class BitmapCache {
    public Handler h = new Handler();
    public final String TAG = getClass().getSimpleName();
    private HashMap<String, SoftReference<Bitmap>> imageCache = new HashMap<>();

    /* loaded from: classes.dex */
    public interface ImageCallback {
        void imageLoad(ImageView imageView, Bitmap bitmap, Object... objArr);
    }

    public void put(String path, Bitmap bmp) {
        if (!TextUtils.isEmpty(path) && bmp != null) {
            this.imageCache.put(path, new SoftReference<>(bmp));
        }
    }

    public void displayBmp(ImageView iv, String thumbPath, String sourcePath, ImageCallback callback) {
        String path;
        boolean isThumbPath;
        Bitmap bmp;
        if (!TextUtils.isEmpty(thumbPath) || !TextUtils.isEmpty(sourcePath)) {
            if (!TextUtils.isEmpty(thumbPath)) {
                path = thumbPath;
                isThumbPath = true;
            } else if (!TextUtils.isEmpty(sourcePath)) {
                path = sourcePath;
                isThumbPath = false;
            } else {
                return;
            }
            if (!this.imageCache.containsKey(path) || (bmp = this.imageCache.get(path).get()) == null) {
                iv.setImageBitmap(null);
                ThreadPool.newThreadPool(new AnonymousClass1(isThumbPath, thumbPath, sourcePath, path, callback, iv));
                return;
            }
            if (callback != null) {
                callback.imageLoad(iv, bmp, sourcePath);
            }
            iv.setImageBitmap(bmp);
            Log.d(this.TAG, "hit cache");
            return;
        }
        Log.e(this.TAG, "no paths pass in");
    }

    /* renamed from: com.cdlinglu.utils.Photo.util.BitmapCache$1  reason: invalid class name */
    /* loaded from: classes.dex */
    class AnonymousClass1 implements Runnable {
        Bitmap thumb;
        final /* synthetic */ ImageCallback val$callback;
        final /* synthetic */ boolean val$isThumbPath;
        final /* synthetic */ ImageView val$iv;
        final /* synthetic */ String val$path;
        final /* synthetic */ String val$sourcePath;
        final /* synthetic */ String val$thumbPath;

        AnonymousClass1(boolean z, String str, String str2, String str3, ImageCallback imageCallback, ImageView imageView) {
            this.val$isThumbPath = z;
            this.val$thumbPath = str;
            this.val$sourcePath = str2;
            this.val$path = str3;
            this.val$callback = imageCallback;
            this.val$iv = imageView;
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                if (this.val$isThumbPath) {
                    this.thumb = BitmapFactory.decodeFile(this.val$thumbPath);
                    if (this.thumb == null) {
                        this.thumb = BitmapCache.this.revitionImageSize(this.val$sourcePath);
                    }
                } else {
                    this.thumb = BitmapCache.this.revitionImageSize(this.val$sourcePath);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            Log.e(BitmapCache.this.TAG, "-------thumb------" + this.thumb);
            BitmapCache.this.put(this.val$path, this.thumb);
            if (this.val$callback != null) {
                BitmapCache.this.h.post(new Runnable() { // from class: com.cdlinglu.utils.Photo.util.BitmapCache.1.1
                    @Override // java.lang.Runnable
                    public void run() {
                        AnonymousClass1.this.val$callback.imageLoad(AnonymousClass1.this.val$iv, AnonymousClass1.this.thumb, AnonymousClass1.this.val$sourcePath);
                    }
                });
            }
        }
    }

    public Bitmap revitionImageSize(String path) throws IOException {
        BufferedInputStream in = new BufferedInputStream(new FileInputStream(new File(path)));
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeStream(in, null, options);
        in.close();
        int i = 0;
        while (true) {
            if ((options.outWidth >> i) > 256 || (options.outHeight >> i) > 256) {
                i++;
            } else {
                BufferedInputStream in2 = new BufferedInputStream(new FileInputStream(new File(path)));
                options.inSampleSize = (int) Math.pow(2.0d, i);
                options.inJustDecodeBounds = false;
                return BitmapFactory.decodeStream(in2, null, options);
            }
        }
    }
}
