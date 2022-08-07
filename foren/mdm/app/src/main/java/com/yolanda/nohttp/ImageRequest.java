package com.yolanda.nohttp;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.ImageView;
import java.util.Locale;

/* loaded from: classes2.dex */
public class ImageRequest extends RestRequest<Bitmap> {
    private static final Object DECODE_LOCK = new Object();
    private final Bitmap.Config mDecodeConfig;
    private final int mMaxHeight;
    private final int mMaxWidth;
    private ImageView.ScaleType mScaleType;

    public ImageRequest(String url, RequestMethod requestMethod, int maxWidth, int maxHeight, Bitmap.Config decodeConfig, ImageView.ScaleType scaleType) {
        super(url, requestMethod);
        this.mMaxWidth = maxWidth;
        this.mMaxHeight = maxHeight;
        this.mDecodeConfig = decodeConfig;
        this.mScaleType = scaleType;
    }

    @Override // com.yolanda.nohttp.ImplServerRequest
    public String getAccept() {
        return "image/*,*/*;q=1";
    }

    @Override // com.yolanda.nohttp.Request
    public Bitmap parseResponse(String url, Headers responseHeaders, byte[] responseBody) {
        Bitmap bitmap;
        synchronized (DECODE_LOCK) {
            bitmap = null;
            if (responseBody != null) {
                try {
                    bitmap = doResponse(responseBody);
                } catch (OutOfMemoryError e) {
                    Logger.wtf(e, String.format(Locale.getDefault(), "Caught OOM for %d byte image, url=%s", Integer.valueOf(responseBody.length), url));
                }
            }
        }
        return bitmap;
    }

    private Bitmap doResponse(byte[] byteArray) throws OutOfMemoryError {
        BitmapFactory.Options decodeOptions = new BitmapFactory.Options();
        if (this.mMaxWidth == 0 && this.mMaxHeight == 0) {
            decodeOptions.inPreferredConfig = this.mDecodeConfig;
            return BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length, decodeOptions);
        }
        decodeOptions.inJustDecodeBounds = true;
        BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length, decodeOptions);
        int actualWidth = decodeOptions.outWidth;
        int actualHeight = decodeOptions.outHeight;
        int desiredWidth = getResizedDimension(this.mMaxWidth, this.mMaxHeight, actualWidth, actualHeight, this.mScaleType);
        int desiredHeight = getResizedDimension(this.mMaxHeight, this.mMaxWidth, actualHeight, actualWidth, this.mScaleType);
        decodeOptions.inJustDecodeBounds = false;
        decodeOptions.inSampleSize = findBestSampleSize(actualWidth, actualHeight, desiredWidth, desiredHeight);
        Bitmap tempBitmap = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length, decodeOptions);
        if (tempBitmap == null || (tempBitmap.getWidth() <= desiredWidth && tempBitmap.getHeight() <= desiredHeight)) {
            return tempBitmap;
        }
        Bitmap bitmap = Bitmap.createScaledBitmap(tempBitmap, desiredWidth, desiredHeight, true);
        tempBitmap.recycle();
        return bitmap;
    }

    private static int getResizedDimension(int maxPrimary, int maxSecondary, int actualPrimary, int actualSecondary, ImageView.ScaleType scaleType) {
        if (maxPrimary == 0 && maxSecondary == 0) {
            return actualPrimary;
        }
        if (scaleType == ImageView.ScaleType.FIT_XY) {
            return maxPrimary != 0 ? maxPrimary : actualPrimary;
        }
        if (maxPrimary == 0) {
            return (int) (actualPrimary * (maxSecondary / actualSecondary));
        } else if (maxSecondary == 0) {
            return maxPrimary;
        } else {
            double ratio = actualSecondary / actualPrimary;
            int resized = maxPrimary;
            if (scaleType == ImageView.ScaleType.CENTER_CROP) {
                if (resized * ratio < maxSecondary) {
                    resized = (int) (maxSecondary / ratio);
                }
                return resized;
            }
            if (resized * ratio > maxSecondary) {
                resized = (int) (maxSecondary / ratio);
            }
            return resized;
        }
    }

    public static int findBestSampleSize(int actualWidth, int actualHeight, int desiredWidth, int desiredHeight) {
        float n = 1.0f;
        while (2.0f * n <= Math.min(actualWidth / desiredWidth, actualHeight / desiredHeight)) {
            n *= 2.0f;
        }
        return (int) n;
    }
}
