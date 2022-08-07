package com.hyphenate.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.media.ExifInterface;
import android.media.ThumbnailUtils;
import com.alibaba.sdk.android.oss.common.OSSConstants;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

/* loaded from: classes2.dex */
public class ImageUtils {
    public static final int SCALE_IMAGE_HEIGHT = 960;
    public static final int SCALE_IMAGE_WIDTH = 640;

    public static int calculateInSampleSize(BitmapFactory.Options options, int i, int i2) {
        int i3 = options.outHeight;
        int i4 = options.outWidth;
        if (i3 <= i2 && i4 <= i) {
            return 1;
        }
        int round = Math.round(i3 / i2);
        int round2 = Math.round(i4 / i);
        return round > round2 ? round : round2;
    }

    public static Bitmap decodeScaleImage(Context context, int i, int i2, int i3) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(context.getResources(), i, options);
        options.inSampleSize = calculateInSampleSize(options, i2, i3);
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeResource(context.getResources(), i, options);
    }

    public static Bitmap decodeScaleImage(String str, int i, int i2) {
        BitmapFactory.Options bitmapOptions = getBitmapOptions(str);
        int calculateInSampleSize = calculateInSampleSize(bitmapOptions, i, i2);
        EMLog.d("img", "original wid" + bitmapOptions.outWidth + " original height:" + bitmapOptions.outHeight + " sample:" + calculateInSampleSize);
        bitmapOptions.inSampleSize = calculateInSampleSize;
        bitmapOptions.inJustDecodeBounds = false;
        Bitmap decodeFile = BitmapFactory.decodeFile(str, bitmapOptions);
        int readPictureDegree = readPictureDegree(str);
        if (decodeFile == null || readPictureDegree == 0) {
            return decodeFile;
        }
        Bitmap rotateImageView = rotateImageView(readPictureDegree, decodeFile);
        decodeFile.recycle();
        return rotateImageView;
    }

    public static BitmapFactory.Options getBitmapOptions(String str) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(str, options);
        return options;
    }

    public static Bitmap getRoundedCornerBitmap(Bitmap bitmap) {
        return getRoundedCornerBitmap(bitmap, 6.0f);
    }

    public static Bitmap getRoundedCornerBitmap(Bitmap bitmap, float f) {
        Bitmap createBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        Paint paint = new Paint();
        Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
        RectF rectF = new RectF(rect);
        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(-12434878);
        canvas.drawRoundRect(rectF, f, f, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint);
        return createBitmap;
    }

    public static String getScaledImage(Context context, String str) {
        File file = new File(str);
        if (!file.exists()) {
            return str;
        }
        long length = file.length();
        EMLog.d("img", "original img size:" + length);
        if (length <= OSSConstants.MIN_PART_SIZE_LIMIT) {
            EMLog.d("img", "use original small image");
            return str;
        }
        Bitmap decodeScaleImage = decodeScaleImage(str, SCALE_IMAGE_WIDTH, SCALE_IMAGE_HEIGHT);
        try {
            File createTempFile = File.createTempFile("image", ".jpg", context.getFilesDir());
            FileOutputStream fileOutputStream = new FileOutputStream(createTempFile);
            decodeScaleImage.compress(Bitmap.CompressFormat.JPEG, 70, fileOutputStream);
            fileOutputStream.close();
            EMLog.d("img", "compared to small fle" + createTempFile.getAbsolutePath() + " size:" + createTempFile.length());
            return createTempFile.getAbsolutePath();
        } catch (Exception e) {
            e.printStackTrace();
            return str;
        }
    }

    public static String getScaledImage(Context context, String str, int i) {
        File file = new File(str);
        if (!file.exists()) {
            return str;
        }
        long length = file.length();
        EMLog.d("img", "original img size:" + length);
        if (length <= OSSConstants.MIN_PART_SIZE_LIMIT) {
            return str;
        }
        Bitmap decodeScaleImage = decodeScaleImage(str, SCALE_IMAGE_WIDTH, SCALE_IMAGE_HEIGHT);
        try {
            File file2 = new File(context.getExternalCacheDir(), "eaemobTemp" + i + ".jpg");
            FileOutputStream fileOutputStream = new FileOutputStream(file2);
            decodeScaleImage.compress(Bitmap.CompressFormat.JPEG, 60, fileOutputStream);
            fileOutputStream.close();
            EMLog.d("img", "compared to small fle" + file2.getAbsolutePath() + " size:" + file2.length());
            return file2.getAbsolutePath();
        } catch (Exception e) {
            e.printStackTrace();
            return str;
        }
    }

    public static String getThumbnailImage(String str, int i) {
        Bitmap decodeScaleImage = decodeScaleImage(str, i, i);
        try {
            File createTempFile = File.createTempFile("image", ".jpg");
            FileOutputStream fileOutputStream = new FileOutputStream(createTempFile);
            decodeScaleImage.compress(Bitmap.CompressFormat.JPEG, 60, fileOutputStream);
            fileOutputStream.close();
            EMLog.d("img", "generate thumbnail image at:" + createTempFile.getAbsolutePath() + " size:" + createTempFile.length());
            return createTempFile.getAbsolutePath();
        } catch (Exception e) {
            e.printStackTrace();
            return str;
        }
    }

    public static Bitmap getVideoThumbnail(String str, int i, int i2, int i3) {
        Bitmap createVideoThumbnail = ThumbnailUtils.createVideoThumbnail(str, i3);
        EMLog.d("getVideoThumbnail", "video thumb width:" + createVideoThumbnail.getWidth());
        EMLog.d("getVideoThumbnail", "video thumb height:" + createVideoThumbnail.getHeight());
        return ThumbnailUtils.extractThumbnail(createVideoThumbnail, i, i2, 2);
    }

    public static Bitmap mergeImages(int i, int i2, List<Bitmap> list) {
        Bitmap createBitmap = Bitmap.createBitmap(i, i2, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        canvas.drawColor(-3355444);
        EMLog.d("img", "merge images to size:" + i + "*" + i2 + " with images:" + list.size());
        int i3 = list.size() <= 4 ? 2 : 3;
        int i4 = (i - 4) / i3;
        int i5 = 0;
        int i6 = 0;
        while (i5 < i3) {
            for (int i7 = 0; i7 < i3; i7++) {
                Bitmap createScaledBitmap = Bitmap.createScaledBitmap(list.get(i6), i4, i4, true);
                Bitmap roundedCornerBitmap = getRoundedCornerBitmap(createScaledBitmap, 2.0f);
                createScaledBitmap.recycle();
                canvas.drawBitmap(roundedCornerBitmap, (i7 * i4) + i7 + 2, (i5 * i4) + i5 + 2, (Paint) null);
                roundedCornerBitmap.recycle();
                i6++;
                if (i6 == list.size()) {
                    return createBitmap;
                }
            }
            i5++;
            i6 = i6;
        }
        return createBitmap;
    }

    public static int readPictureDegree(String str) {
        try {
            switch (new ExifInterface(str).getAttributeInt("Orientation", 1)) {
                case 3:
                    return 180;
                case 4:
                case 5:
                case 7:
                default:
                    return 0;
                case 6:
                    return 90;
                case 8:
                    return 270;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public static Bitmap rotateImageView(int i, Bitmap bitmap) {
        Matrix matrix = new Matrix();
        matrix.postRotate(i);
        return Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
    }

    public static String saveVideoThumb(File file, int i, int i2, int i3) {
        Bitmap videoThumbnail = getVideoThumbnail(file.getAbsolutePath(), i, i2, i3);
        File file2 = new File(PathUtil.getInstance().getVideoPath(), "th" + file.getName());
        try {
            file2.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(file2);
        } catch (FileNotFoundException e2) {
            e2.printStackTrace();
        }
        videoThumbnail.compress(Bitmap.CompressFormat.JPEG, 100, fileOutputStream);
        try {
            fileOutputStream.flush();
        } catch (IOException e3) {
            e3.printStackTrace();
        }
        try {
            fileOutputStream.close();
        } catch (IOException e4) {
            e4.printStackTrace();
        }
        return file2.getAbsolutePath();
    }
}
