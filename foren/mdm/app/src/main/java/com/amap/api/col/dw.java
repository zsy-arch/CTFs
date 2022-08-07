package com.amap.api.col;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import java.io.FileDescriptor;

/* compiled from: ImageResizer.java */
/* loaded from: classes.dex */
public class dw extends dx {
    protected int a;
    protected int b;

    public dw(Context context, int i, int i2) {
        super(context);
        a(i, i2);
    }

    public void a(int i, int i2) {
        this.a = i;
        this.b = i2;
    }

    private Bitmap a(int i) {
        return a(this.d, i, this.a, this.b, a());
    }

    @Override // com.amap.api.col.dx
    protected Bitmap a(Object obj) {
        return a(Integer.parseInt(String.valueOf(obj)));
    }

    public static Bitmap a(Resources resources, int i, int i2, int i3, du duVar) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(resources, i, options);
        options.inSampleSize = a(options, i2, i3);
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeResource(resources, i, options);
    }

    public static Bitmap a(FileDescriptor fileDescriptor, int i, int i2, du duVar) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFileDescriptor(fileDescriptor, null, options);
        options.inSampleSize = a(options, i, i2);
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeFileDescriptor(fileDescriptor, null, options);
    }

    public static int a(BitmapFactory.Options options, int i, int i2) {
        int i3 = options.outHeight;
        int i4 = options.outWidth;
        int i5 = 1;
        if (i3 > i2 || i4 > i) {
            i5 = Math.round(i3 / i2);
            int round = Math.round(i4 / i);
            if (i5 >= round) {
                i5 = round;
            }
            while ((i4 * i3) / (i5 * i5) > i * i2 * 2) {
                i5++;
            }
        }
        return i5;
    }
}
