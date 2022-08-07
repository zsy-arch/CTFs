package com.em.video.util;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.ThumbnailUtils;
import java.io.FileDescriptor;

/* loaded from: classes.dex */
public class ImageResizer extends ImageWorker {
    private static final String TAG = "ImageResizer";
    protected int mImageHeight;
    protected int mImageWidth;

    public ImageResizer(Context context, int imageWidth, int imageHeight) {
        super(context);
        setImageSize(imageWidth, imageHeight);
    }

    public ImageResizer(Context context, int imageSize) {
        super(context);
        setImageSize(imageSize);
    }

    public void setImageSize(int width, int height) {
        this.mImageWidth = width;
        this.mImageHeight = height;
    }

    public void setImageSize(int size) {
        setImageSize(size, size);
    }

    private Bitmap processBitmap(int resId) {
        return decodeSampledBitmapFromResource(this.mResources, resId, this.mImageWidth, this.mImageHeight, getImageCache());
    }

    @Override // com.em.video.util.ImageWorker
    protected Bitmap processBitmap(Object data) {
        return ThumbnailUtils.createVideoThumbnail(String.valueOf(data), 3);
    }

    public static Bitmap decodeSampledBitmapFromResource(Resources res, int resId, int reqWidth, int reqHeight, ImageCache cache) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(res, resId, options);
        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);
        if (Utils.hasHoneycomb()) {
            addInBitmapOptions(options, cache);
        }
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeResource(res, resId, options);
    }

    public static Bitmap decodeSampledBitmapFromFile(String filename, int reqWidth, int reqHeight, ImageCache cache) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(filename, options);
        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);
        if (Utils.hasHoneycomb()) {
            addInBitmapOptions(options, cache);
        }
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeFile(filename, options);
    }

    public static Bitmap decodeSampledBitmapFromDescriptor(FileDescriptor fileDescriptor, int reqWidth, int reqHeight, ImageCache cache) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFileDescriptor(fileDescriptor, null, options);
        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);
        options.inJustDecodeBounds = false;
        if (Utils.hasHoneycomb()) {
            addInBitmapOptions(options, cache);
        }
        return BitmapFactory.decodeFileDescriptor(fileDescriptor, null, options);
    }

    @TargetApi(11)
    private static void addInBitmapOptions(BitmapFactory.Options options, ImageCache cache) {
        Bitmap inBitmap;
        options.inMutable = true;
        if (cache != null && (inBitmap = cache.getBitmapFromReusableSet(options)) != null) {
            options.inBitmap = inBitmap;
        }
    }

    public static int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
        int height = options.outHeight;
        int width = options.outWidth;
        int inSampleSize = 1;
        if (height > reqHeight || width > reqWidth) {
            int halfHeight = height / 2;
            int halfWidth = width / 2;
            while (halfHeight / inSampleSize > reqHeight && halfWidth / inSampleSize > reqWidth) {
                inSampleSize *= 2;
            }
            for (long totalPixels = (width * height) / inSampleSize; totalPixels > reqWidth * reqHeight * 2; totalPixels /= 2) {
                inSampleSize *= 2;
            }
        }
        return inSampleSize;
    }
}
