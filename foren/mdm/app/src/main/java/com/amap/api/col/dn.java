package com.amap.api.col;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.NinePatch;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.NinePatchDrawable;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Field;
import u.aly.dc;

/* compiled from: NinePatchTool.java */
/* loaded from: classes.dex */
public class dn {
    public static Drawable a(Context context, String str) throws Exception {
        Bitmap b = b(context, str);
        if (b.getNinePatchChunk() == null) {
            return new BitmapDrawable(b);
        }
        Rect rect = new Rect();
        a(b.getNinePatchChunk(), rect);
        return new NinePatchDrawable(context.getResources(), b, b.getNinePatchChunk(), rect, null);
    }

    private static Bitmap a(InputStream inputStream) throws Exception {
        Bitmap decodeStream = BitmapFactory.decodeStream(inputStream);
        byte[] a = a(decodeStream);
        if (!NinePatch.isNinePatchChunk(a)) {
            return decodeStream;
        }
        Bitmap createBitmap = Bitmap.createBitmap(decodeStream, 1, 1, decodeStream.getWidth() - 2, decodeStream.getHeight() - 2);
        decodeStream.recycle();
        Field declaredField = createBitmap.getClass().getDeclaredField("mNinePatchChunk");
        declaredField.setAccessible(true);
        declaredField.set(createBitmap, a);
        return createBitmap;
    }

    private static Bitmap b(Context context, String str) throws Exception {
        InputStream open = dq.a(context).open(str);
        Bitmap a = a(open);
        open.close();
        return a;
    }

    private static void a(byte[] bArr, Rect rect) {
        rect.left = a(bArr, 12);
        rect.right = a(bArr, 16);
        rect.top = a(bArr, 20);
        rect.bottom = a(bArr, 24);
    }

    private static byte[] a(Bitmap bitmap) throws IOException {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        for (int i = 0; i < 32; i++) {
            byteArrayOutputStream.write(0);
        }
        int[] iArr = new int[width - 2];
        bitmap.getPixels(iArr, 0, width, 1, 0, width - 2, 1);
        boolean z = iArr[0] == -16777216;
        boolean z2 = iArr[iArr.length + (-1)] == -16777216;
        int length = iArr.length;
        int i2 = 0;
        int i3 = 0;
        for (int i4 = 0; i4 < length; i4++) {
            if (i2 != iArr[i4]) {
                i3++;
                a(byteArrayOutputStream, i4);
                i2 = iArr[i4];
            }
        }
        if (z2) {
            i3++;
            a(byteArrayOutputStream, iArr.length);
        }
        int i5 = i3 + 1;
        int i6 = z ? i5 - 1 : i5;
        int i7 = z2 ? i6 - 1 : i6;
        int[] iArr2 = new int[height - 2];
        bitmap.getPixels(iArr2, 0, 1, 0, 1, 1, height - 2);
        boolean z3 = iArr2[0] == -16777216;
        boolean z4 = iArr2[iArr2.length + (-1)] == -16777216;
        int length2 = iArr2.length;
        int i8 = 0;
        int i9 = 0;
        for (int i10 = 0; i10 < length2; i10++) {
            if (i8 != iArr2[i10]) {
                i9++;
                a(byteArrayOutputStream, i10);
                i8 = iArr2[i10];
            }
        }
        if (z4) {
            i9++;
            a(byteArrayOutputStream, iArr2.length);
        }
        int i11 = i9 + 1;
        int i12 = z3 ? i11 - 1 : i11;
        if (z4) {
            i12--;
        }
        for (int i13 = 0; i13 < i7 * i12; i13++) {
            a(byteArrayOutputStream, 1);
        }
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        byteArray[0] = 1;
        byteArray[1] = (byte) i3;
        byteArray[2] = (byte) i9;
        byteArray[3] = (byte) (i12 * i7);
        a(bitmap, byteArray);
        return byteArray;
    }

    private static void a(Bitmap bitmap, byte[] bArr) {
        int i = 0;
        int[] iArr = new int[bitmap.getWidth() - 2];
        bitmap.getPixels(iArr, 0, iArr.length, 1, bitmap.getHeight() - 1, iArr.length, 1);
        int i2 = 0;
        while (true) {
            if (i2 >= iArr.length) {
                break;
            } else if (-16777216 == iArr[i2]) {
                a(bArr, 12, i2);
                break;
            } else {
                i2++;
            }
        }
        int length = iArr.length - 1;
        while (true) {
            if (length < 0) {
                break;
            } else if (-16777216 == iArr[length]) {
                a(bArr, 16, (iArr.length - length) - 2);
                break;
            } else {
                length--;
            }
        }
        int[] iArr2 = new int[bitmap.getHeight() - 2];
        bitmap.getPixels(iArr2, 0, 1, bitmap.getWidth() - 1, 0, 1, iArr2.length);
        while (true) {
            if (i >= iArr2.length) {
                break;
            } else if (-16777216 == iArr2[i]) {
                a(bArr, 20, i);
                break;
            } else {
                i++;
            }
        }
        for (int length2 = iArr2.length - 1; length2 >= 0; length2--) {
            if (-16777216 == iArr2[length2]) {
                a(bArr, 24, (iArr2.length - length2) - 2);
                return;
            }
        }
    }

    private static void a(OutputStream outputStream, int i) throws IOException {
        outputStream.write((i >> 0) & 255);
        outputStream.write((i >> 8) & 255);
        outputStream.write((i >> 16) & 255);
        outputStream.write((i >> 24) & 255);
    }

    private static void a(byte[] bArr, int i, int i2) {
        bArr[i + 0] = (byte) (i2 >> 0);
        bArr[i + 1] = (byte) (i2 >> 8);
        bArr[i + 2] = (byte) (i2 >> 16);
        bArr[i + 3] = (byte) (i2 >> 24);
    }

    private static int a(byte[] bArr, int i) {
        return (bArr[i + 0] & 255) | (bArr[i + 1] << 8) | (bArr[i + 2] << dc.n) | (bArr[i + 3] << 24);
    }
}
