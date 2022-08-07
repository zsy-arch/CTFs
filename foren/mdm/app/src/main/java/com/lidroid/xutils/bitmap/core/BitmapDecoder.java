package com.lidroid.xutils.bitmap.core;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import java.io.FileDescriptor;

/* loaded from: classes2.dex */
public class BitmapDecoder {
    private static final Object lock = new Object();

    private BitmapDecoder() {
    }

    public static Bitmap decodeSampledBitmapFromResource(Resources res, int resId, BitmapSize maxSize, Bitmap.Config config) {
        Bitmap decodeResource;
        synchronized (lock) {
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;
            options.inPurgeable = true;
            options.inInputShareable = true;
            BitmapFactory.decodeResource(res, resId, options);
            options.inSampleSize = calculateInSampleSize(options, maxSize.getWidth(), maxSize.getHeight());
            options.inJustDecodeBounds = false;
            if (config != null) {
                options.inPreferredConfig = config;
            }
            decodeResource = BitmapFactory.decodeResource(res, resId, options);
        }
        return decodeResource;
    }

    public static Bitmap decodeSampledBitmapFromFile(String filename, BitmapSize maxSize, Bitmap.Config config) {
        Bitmap decodeFile;
        synchronized (lock) {
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;
            options.inPurgeable = true;
            options.inInputShareable = true;
            BitmapFactory.decodeFile(filename, options);
            options.inSampleSize = calculateInSampleSize(options, maxSize.getWidth(), maxSize.getHeight());
            options.inJustDecodeBounds = false;
            if (config != null) {
                options.inPreferredConfig = config;
            }
            decodeFile = BitmapFactory.decodeFile(filename, options);
        }
        return decodeFile;
    }

    public static Bitmap decodeSampledBitmapFromDescriptor(FileDescriptor fileDescriptor, BitmapSize maxSize, Bitmap.Config config) {
        Bitmap decodeFileDescriptor;
        synchronized (lock) {
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;
            options.inPurgeable = true;
            options.inInputShareable = true;
            BitmapFactory.decodeFileDescriptor(fileDescriptor, null, options);
            options.inSampleSize = calculateInSampleSize(options, maxSize.getWidth(), maxSize.getHeight());
            options.inJustDecodeBounds = false;
            if (config != null) {
                options.inPreferredConfig = config;
            }
            decodeFileDescriptor = BitmapFactory.decodeFileDescriptor(fileDescriptor, null, options);
        }
        return decodeFileDescriptor;
    }

    public static Bitmap decodeSampledBitmapFromByteArray(byte[] data, BitmapSize maxSize, Bitmap.Config config) {
        Bitmap decodeByteArray;
        synchronized (lock) {
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;
            options.inPurgeable = true;
            options.inInputShareable = true;
            BitmapFactory.decodeByteArray(data, 0, data.length, options);
            options.inSampleSize = calculateInSampleSize(options, maxSize.getWidth(), maxSize.getHeight());
            options.inJustDecodeBounds = false;
            if (config != null) {
                options.inPreferredConfig = config;
            }
            decodeByteArray = BitmapFactory.decodeByteArray(data, 0, data.length, options);
        }
        return decodeByteArray;
    }

    public static Bitmap decodeResource(Resources res, int resId) {
        Bitmap decodeResource;
        synchronized (lock) {
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inPurgeable = true;
            options.inInputShareable = true;
            decodeResource = BitmapFactory.decodeResource(res, resId, options);
        }
        return decodeResource;
    }

    public static Bitmap decodeFile(String filename) {
        Bitmap decodeFile;
        synchronized (lock) {
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inPurgeable = true;
            options.inInputShareable = true;
            decodeFile = BitmapFactory.decodeFile(filename, options);
        }
        return decodeFile;
    }

    public static Bitmap decodeFileDescriptor(FileDescriptor fileDescriptor) {
        Bitmap decodeFileDescriptor;
        synchronized (lock) {
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inPurgeable = true;
            options.inInputShareable = true;
            decodeFileDescriptor = BitmapFactory.decodeFileDescriptor(fileDescriptor, null, options);
        }
        return decodeFileDescriptor;
    }

    public static Bitmap decodeByteArray(byte[] data) {
        Bitmap decodeByteArray;
        synchronized (lock) {
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inPurgeable = true;
            options.inInputShareable = true;
            decodeByteArray = BitmapFactory.decodeByteArray(data, 0, data.length, options);
        }
        return decodeByteArray;
    }

    public static int calculateInSampleSize(BitmapFactory.Options options, int maxWidth, int maxHeight) {
        int height = options.outHeight;
        int width = options.outWidth;
        int inSampleSize = 1;
        if (width > maxWidth || height > maxHeight) {
            if (width > height) {
                inSampleSize = Math.round(height / maxHeight);
            } else {
                inSampleSize = Math.round(width / maxWidth);
            }
            while ((width * height) / (inSampleSize * inSampleSize) > maxWidth * maxHeight * 2) {
                inSampleSize++;
            }
        }
        return inSampleSize;
    }
}
