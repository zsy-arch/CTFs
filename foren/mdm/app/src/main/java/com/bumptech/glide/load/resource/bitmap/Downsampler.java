package com.bumptech.glide.load.resource.bitmap;

import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.util.Log;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.ImageHeaderParser;
import com.bumptech.glide.util.ByteArrayPool;
import com.bumptech.glide.util.ExceptionCatchingInputStream;
import com.bumptech.glide.util.MarkEnforcingInputStream;
import com.bumptech.glide.util.Util;
import java.io.IOException;
import java.io.InputStream;
import java.util.EnumSet;
import java.util.Queue;
import java.util.Set;

/* loaded from: classes.dex */
public abstract class Downsampler implements BitmapDecoder<InputStream> {
    private static final int MARK_POSITION = 5242880;
    private static final String TAG = "Downsampler";
    private static final Set<ImageHeaderParser.ImageType> TYPES_THAT_USE_POOL = EnumSet.of(ImageHeaderParser.ImageType.JPEG, ImageHeaderParser.ImageType.PNG_A, ImageHeaderParser.ImageType.PNG);
    private static final Queue<BitmapFactory.Options> OPTIONS_QUEUE = Util.createQueue(0);
    public static final Downsampler AT_LEAST = new Downsampler() { // from class: com.bumptech.glide.load.resource.bitmap.Downsampler.1
        @Override // com.bumptech.glide.load.resource.bitmap.Downsampler, com.bumptech.glide.load.resource.bitmap.BitmapDecoder
        public /* bridge */ /* synthetic */ Bitmap decode(InputStream inputStream, BitmapPool x1, int x2, int x3, DecodeFormat x4) throws Exception {
            return Downsampler.super.decode(inputStream, x1, x2, x3, x4);
        }

        @Override // com.bumptech.glide.load.resource.bitmap.Downsampler
        protected int getSampleSize(int inWidth, int inHeight, int outWidth, int outHeight) {
            return Math.min(inHeight / outHeight, inWidth / outWidth);
        }

        @Override // com.bumptech.glide.load.resource.bitmap.BitmapDecoder
        public String getId() {
            return "AT_LEAST.com.bumptech.glide.load.data.bitmap";
        }
    };
    public static final Downsampler AT_MOST = new Downsampler() { // from class: com.bumptech.glide.load.resource.bitmap.Downsampler.2
        @Override // com.bumptech.glide.load.resource.bitmap.Downsampler, com.bumptech.glide.load.resource.bitmap.BitmapDecoder
        public /* bridge */ /* synthetic */ Bitmap decode(InputStream inputStream, BitmapPool x1, int x2, int x3, DecodeFormat x4) throws Exception {
            return Downsampler.super.decode(inputStream, x1, x2, x3, x4);
        }

        @Override // com.bumptech.glide.load.resource.bitmap.Downsampler
        protected int getSampleSize(int inWidth, int inHeight, int outWidth, int outHeight) {
            int i = 1;
            int maxIntegerFactor = (int) Math.ceil(Math.max(inHeight / outHeight, inWidth / outWidth));
            int lesserOrEqualSampleSize = Math.max(1, Integer.highestOneBit(maxIntegerFactor));
            if (lesserOrEqualSampleSize >= maxIntegerFactor) {
                i = 0;
            }
            return lesserOrEqualSampleSize << i;
        }

        @Override // com.bumptech.glide.load.resource.bitmap.BitmapDecoder
        public String getId() {
            return "AT_MOST.com.bumptech.glide.load.data.bitmap";
        }
    };
    public static final Downsampler NONE = new Downsampler() { // from class: com.bumptech.glide.load.resource.bitmap.Downsampler.3
        @Override // com.bumptech.glide.load.resource.bitmap.Downsampler, com.bumptech.glide.load.resource.bitmap.BitmapDecoder
        public /* bridge */ /* synthetic */ Bitmap decode(InputStream inputStream, BitmapPool x1, int x2, int x3, DecodeFormat x4) throws Exception {
            return Downsampler.super.decode(inputStream, x1, x2, x3, x4);
        }

        @Override // com.bumptech.glide.load.resource.bitmap.Downsampler
        protected int getSampleSize(int inWidth, int inHeight, int outWidth, int outHeight) {
            return 0;
        }

        @Override // com.bumptech.glide.load.resource.bitmap.BitmapDecoder
        public String getId() {
            return "NONE.com.bumptech.glide.load.data.bitmap";
        }
    };

    protected abstract int getSampleSize(int i, int i2, int i3, int i4);

    public Bitmap decode(InputStream is, BitmapPool pool, int outWidth, int outHeight, DecodeFormat decodeFormat) {
        ByteArrayPool byteArrayPool = ByteArrayPool.get();
        byte[] bytesForOptions = byteArrayPool.getBytes();
        byte[] bytesForStream = byteArrayPool.getBytes();
        BitmapFactory.Options options = getDefaultOptions();
        RecyclableBufferedInputStream bufferedStream = new RecyclableBufferedInputStream(is, bytesForStream);
        ExceptionCatchingInputStream exceptionStream = ExceptionCatchingInputStream.obtain(bufferedStream);
        MarkEnforcingInputStream invalidatingStream = new MarkEnforcingInputStream(exceptionStream);
        try {
            exceptionStream.mark(MARK_POSITION);
            int orientation = 0;
            try {
                orientation = new ImageHeaderParser(exceptionStream).getOrientation();
                try {
                    exceptionStream.reset();
                } catch (IOException e) {
                    if (Log.isLoggable(TAG, 5)) {
                        Log.w(TAG, "Cannot reset the input stream", e);
                    }
                }
            } catch (IOException e2) {
                if (Log.isLoggable(TAG, 5)) {
                    Log.w(TAG, "Cannot determine the image orientation from header", e2);
                }
                try {
                    exceptionStream.reset();
                } catch (IOException e3) {
                    if (Log.isLoggable(TAG, 5)) {
                        Log.w(TAG, "Cannot reset the input stream", e3);
                    }
                }
            }
            options.inTempStorage = bytesForOptions;
            int[] inDimens = getDimensions(invalidatingStream, bufferedStream, options);
            int inWidth = inDimens[0];
            int inHeight = inDimens[1];
            Bitmap downsampled = downsampleWithSize(invalidatingStream, bufferedStream, options, pool, inWidth, inHeight, getRoundedSampleSize(TransformationUtils.getExifOrientationDegrees(orientation), inWidth, inHeight, outWidth, outHeight), decodeFormat);
            Exception streamException = exceptionStream.getException();
            if (streamException != null) {
                throw new RuntimeException(streamException);
            }
            Bitmap rotated = null;
            if (downsampled != null) {
                rotated = TransformationUtils.rotateImageExif(downsampled, pool, orientation);
                if (!downsampled.equals(rotated) && !pool.put(downsampled)) {
                    downsampled.recycle();
                }
            }
            return rotated;
        } finally {
            byteArrayPool.releaseBytes(bytesForOptions);
            byteArrayPool.releaseBytes(bytesForStream);
            exceptionStream.release();
            releaseOptions(options);
        }
    }

    private int getRoundedSampleSize(int degreesToRotate, int inWidth, int inHeight, int outWidth, int outHeight) {
        int exactSampleSize;
        int targetHeight = outHeight == Integer.MIN_VALUE ? inHeight : outHeight;
        int targetWidth = outWidth == Integer.MIN_VALUE ? inWidth : outWidth;
        if (degreesToRotate == 90 || degreesToRotate == 270) {
            exactSampleSize = getSampleSize(inHeight, inWidth, targetWidth, targetHeight);
        } else {
            exactSampleSize = getSampleSize(inWidth, inHeight, targetWidth, targetHeight);
        }
        return Math.max(1, exactSampleSize == 0 ? 0 : Integer.highestOneBit(exactSampleSize));
    }

    private Bitmap downsampleWithSize(MarkEnforcingInputStream is, RecyclableBufferedInputStream bufferedStream, BitmapFactory.Options options, BitmapPool pool, int inWidth, int inHeight, int sampleSize, DecodeFormat decodeFormat) {
        Bitmap.Config config = getConfig(is, decodeFormat);
        options.inSampleSize = sampleSize;
        options.inPreferredConfig = config;
        if ((options.inSampleSize == 1 || 19 <= Build.VERSION.SDK_INT) && shouldUsePool(is)) {
            setInBitmap(options, pool.getDirty((int) Math.ceil(inWidth / sampleSize), (int) Math.ceil(inHeight / sampleSize), config));
        }
        return decodeStream(is, bufferedStream, options);
    }

    private static boolean shouldUsePool(InputStream is) {
        if (19 <= Build.VERSION.SDK_INT) {
            return true;
        }
        try {
            is.mark(1024);
            try {
                boolean contains = TYPES_THAT_USE_POOL.contains(new ImageHeaderParser(is).getType());
                try {
                    is.reset();
                    return contains;
                } catch (IOException e) {
                    if (!Log.isLoggable(TAG, 5)) {
                        return contains;
                    }
                    Log.w(TAG, "Cannot reset the input stream", e);
                    return contains;
                }
            } catch (IOException e2) {
                if (Log.isLoggable(TAG, 5)) {
                    Log.w(TAG, "Cannot determine the image type from header", e2);
                }
                return false;
            }
        } finally {
            try {
                is.reset();
            } catch (IOException e3) {
                if (Log.isLoggable(TAG, 5)) {
                    Log.w(TAG, "Cannot reset the input stream", e3);
                }
            }
        }
    }

    private static Bitmap.Config getConfig(InputStream is, DecodeFormat format) {
        if (format == DecodeFormat.ALWAYS_ARGB_8888 || format == DecodeFormat.PREFER_ARGB_8888 || Build.VERSION.SDK_INT == 16) {
            return Bitmap.Config.ARGB_8888;
        }
        try {
            boolean hasAlpha = false;
            is.mark(1024);
            try {
                hasAlpha = new ImageHeaderParser(is).hasAlpha();
            } catch (IOException e) {
                if (Log.isLoggable(TAG, 5)) {
                    Log.w(TAG, "Cannot determine whether the image has alpha or not from header for format " + format, e);
                }
                try {
                    is.reset();
                } catch (IOException e2) {
                    if (Log.isLoggable(TAG, 5)) {
                        Log.w(TAG, "Cannot reset the input stream", e2);
                    }
                }
            }
            return hasAlpha ? Bitmap.Config.ARGB_8888 : Bitmap.Config.RGB_565;
        } finally {
            try {
                is.reset();
            } catch (IOException e3) {
                if (Log.isLoggable(TAG, 5)) {
                    Log.w(TAG, "Cannot reset the input stream", e3);
                }
            }
        }
    }

    public int[] getDimensions(MarkEnforcingInputStream is, RecyclableBufferedInputStream bufferedStream, BitmapFactory.Options options) {
        options.inJustDecodeBounds = true;
        decodeStream(is, bufferedStream, options);
        options.inJustDecodeBounds = false;
        return new int[]{options.outWidth, options.outHeight};
    }

    private static Bitmap decodeStream(MarkEnforcingInputStream is, RecyclableBufferedInputStream bufferedStream, BitmapFactory.Options options) {
        if (options.inJustDecodeBounds) {
            is.mark(MARK_POSITION);
        } else {
            bufferedStream.fixMarkLimit();
        }
        Bitmap result = BitmapFactory.decodeStream(is, null, options);
        try {
            if (options.inJustDecodeBounds) {
                is.reset();
            }
        } catch (IOException e) {
            if (Log.isLoggable(TAG, 6)) {
                Log.e(TAG, "Exception loading inDecodeBounds=" + options.inJustDecodeBounds + " sample=" + options.inSampleSize, e);
            }
        }
        return result;
    }

    @TargetApi(11)
    private static void setInBitmap(BitmapFactory.Options options, Bitmap recycled) {
        if (11 <= Build.VERSION.SDK_INT) {
            options.inBitmap = recycled;
        }
    }

    @TargetApi(11)
    private static synchronized BitmapFactory.Options getDefaultOptions() {
        BitmapFactory.Options decodeBitmapOptions;
        synchronized (Downsampler.class) {
            synchronized (OPTIONS_QUEUE) {
                decodeBitmapOptions = OPTIONS_QUEUE.poll();
            }
            if (decodeBitmapOptions == null) {
                decodeBitmapOptions = new BitmapFactory.Options();
                resetOptions(decodeBitmapOptions);
            }
        }
        return decodeBitmapOptions;
    }

    private static void releaseOptions(BitmapFactory.Options decodeBitmapOptions) {
        resetOptions(decodeBitmapOptions);
        synchronized (OPTIONS_QUEUE) {
            OPTIONS_QUEUE.offer(decodeBitmapOptions);
        }
    }

    @TargetApi(11)
    private static void resetOptions(BitmapFactory.Options decodeBitmapOptions) {
        decodeBitmapOptions.inTempStorage = null;
        decodeBitmapOptions.inDither = false;
        decodeBitmapOptions.inScaled = false;
        decodeBitmapOptions.inSampleSize = 1;
        decodeBitmapOptions.inPreferredConfig = null;
        decodeBitmapOptions.inJustDecodeBounds = false;
        decodeBitmapOptions.outWidth = 0;
        decodeBitmapOptions.outHeight = 0;
        decodeBitmapOptions.outMimeType = null;
        if (11 <= Build.VERSION.SDK_INT) {
            decodeBitmapOptions.inBitmap = null;
            decodeBitmapOptions.inMutable = true;
        }
    }
}
