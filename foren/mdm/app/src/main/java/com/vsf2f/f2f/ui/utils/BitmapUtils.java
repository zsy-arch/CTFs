package com.vsf2f.f2f.ui.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/* loaded from: classes2.dex */
public class BitmapUtils {
    private static String getImageName(String path) {
        if (path == null) {
            return null;
        }
        String[] strs = path.split("/");
        return strs[strs.length - 1];
    }

    private static byte[] getAudioInputStream(String filePath) {
        try {
            FileInputStream fis = new FileInputStream(new File(filePath));
            ByteArrayOutputStream bos = new ByteArrayOutputStream(1000);
            byte[] b = new byte[1000];
            while (true) {
                int n = fis.read(b);
                if (n != -1) {
                    bos.write(b, 0, n);
                } else {
                    fis.close();
                    bos.close();
                    return bos.toByteArray();
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public static byte[] getInputStream(String srcFile) {
        String picName = getImageName(srcFile);
        String prefix = picName.substring(picName.lastIndexOf(".") + 1);
        if ("amr".equals(prefix)) {
            return getAudioInputStream(srcFile);
        }
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        Bitmap bm = getSmallBitmap(srcFile);
        if (bm == null) {
            return baos.toByteArray();
        }
        if ("png".equals(prefix)) {
            bm.compress(Bitmap.CompressFormat.PNG, 50, baos);
        }
        if ("jpg".equals(prefix)) {
            bm.compress(Bitmap.CompressFormat.JPEG, 50, baos);
        }
        bm.recycle();
        return baos.toByteArray();
    }

    public static Bitmap getSmallBitmap(String filePath) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(filePath, options);
        options.inSampleSize = calculateInSampleSize(options, 480, 800);
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeFile(filePath, options);
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
        }
        return inSampleSize;
    }
}
