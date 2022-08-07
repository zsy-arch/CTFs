package com.cdlinglu.utils.Photo.util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/* loaded from: classes.dex */
public class Bimp {
    public static int max = 0;

    public static Bitmap revitionImageSize(String path) throws IOException {
        BufferedInputStream in = new BufferedInputStream(new FileInputStream(new File(path)));
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeStream(in, null, options);
        in.close();
        int i = 0;
        while (true) {
            if ((options.outWidth >> i) > 1000 || (options.outHeight >> i) > 1000) {
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
