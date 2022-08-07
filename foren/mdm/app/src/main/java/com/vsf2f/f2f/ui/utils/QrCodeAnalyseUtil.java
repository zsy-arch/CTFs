package com.vsf2f.f2f.ui.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.ChecksumException;
import com.google.zxing.FormatException;
import com.google.zxing.NotFoundException;
import com.google.zxing.RGBLuminanceSource;
import com.google.zxing.Result;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.QRCodeReader;

/* loaded from: classes2.dex */
public class QrCodeAnalyseUtil {
    public static String getContentByCode(String path) {
        byte[] bb = BitmapUtils.getInputStream(path);
        Bitmap bitmap = BitmapFactory.decodeByteArray(bb, 0, bb.length);
        if (bitmap == null) {
            return "";
        }
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        int[] data = new int[width * height];
        bitmap.getPixels(data, 0, width, 0, 0, width, height);
        Result re = null;
        try {
            re = new QRCodeReader().decode(new BinaryBitmap(new HybridBinarizer(new RGBLuminanceSource(width, height, data))));
        } catch (ChecksumException e) {
            e.printStackTrace();
        } catch (FormatException e2) {
            e2.printStackTrace();
        } catch (NotFoundException e3) {
            e3.printStackTrace();
        }
        return re == null ? "" : re.getText();
    }
}
