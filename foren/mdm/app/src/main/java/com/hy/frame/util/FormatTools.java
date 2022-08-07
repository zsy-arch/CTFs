package com.hy.frame.util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;

/* loaded from: classes2.dex */
public class FormatTools {
    private static FormatTools tools = new FormatTools();

    public static FormatTools getInstance() {
        if (tools != null) {
            return tools;
        }
        tools = new FormatTools();
        return tools;
    }

    public InputStream Byte2InputStream(byte[] b) {
        return new ByteArrayInputStream(b);
    }

    public byte[] InputStream2Bytes(InputStream is) {
        String str = "";
        byte[] readByte = new byte[1024];
        while (is.read(readByte, 0, 1024) != -1) {
            try {
                str = str + new String(readByte).trim();
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
        return str.getBytes();
    }

    public InputStream Bitmap2InputStream(Bitmap bm) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        return new ByteArrayInputStream(baos.toByteArray());
    }

    public InputStream Bitmap2InputStream(Bitmap bm, int quality) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.PNG, quality, baos);
        return new ByteArrayInputStream(baos.toByteArray());
    }

    public Bitmap InputStream2Bitmap(InputStream is) {
        return BitmapFactory.decodeStream(is);
    }

    public InputStream Drawable2InputStream(Drawable d) {
        return Bitmap2InputStream(drawable2Bitmap(d));
    }

    public Drawable InputStream2Drawable(InputStream is) {
        return bitmap2Drawable(InputStream2Bitmap(is));
    }

    public byte[] Drawable2Bytes(Drawable d) {
        return Bitmap2Bytes(drawable2Bitmap(d));
    }

    public Drawable Bytes2Drawable(byte[] b) {
        return bitmap2Drawable(Bytes2Bitmap(b));
    }

    public byte[] Bitmap2Bytes(Bitmap bm) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.PNG, 100, baos);
        return baos.toByteArray();
    }

    public Bitmap Bytes2Bitmap(byte[] b) {
        if (b.length != 0) {
            return BitmapFactory.decodeByteArray(b, 0, b.length);
        }
        return null;
    }

    public Bitmap drawable2Bitmap(Drawable drawable) {
        Bitmap bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), drawable.getOpacity() != -1 ? Bitmap.Config.ARGB_8888 : Bitmap.Config.RGB_565);
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
        drawable.draw(canvas);
        return bitmap;
    }

    public Drawable bitmap2Drawable(Bitmap bitmap) {
        return new BitmapDrawable(bitmap);
    }
}
