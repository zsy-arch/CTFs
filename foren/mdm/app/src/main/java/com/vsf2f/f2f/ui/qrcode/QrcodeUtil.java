package com.vsf2f.f2f.ui.qrcode;

import android.graphics.Bitmap;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.hy.frame.util.HyUtil;
import java.util.Hashtable;

/* loaded from: classes2.dex */
public class QrcodeUtil {
    public static final int auto_focus = 101;
    public static final int decode = 102;
    public static final int decode_failed = 103;
    public static final int decode_succeeded = 104;
    public static final int encode_failed = 105;
    public static final int encode_succeeded = 106;
    public static final int launch_product_query = 107;
    public static final int quit = 108;
    public static final int restart_preview = 109;
    public static final int return_scan_result = 110;
    public static final int search_book_contents_failed = 111;
    public static final int search_book_contents_succeeded = 112;

    public static Bitmap createQrImage(String str, int width, int height) {
        if (HyUtil.isEmpty(str)) {
            return null;
        }
        try {
            QRCodeWriter writer = new QRCodeWriter();
            Hashtable<EncodeHintType, Object> hints = new Hashtable<>();
            hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
            hints.put(EncodeHintType.MARGIN, 0);
            return toBitmap(writer.encode(str, BarcodeFormat.QR_CODE, width, height, hints));
        } catch (WriterException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static Bitmap toBitmap(BitMatrix bitMatrix) {
        int width = bitMatrix.getWidth();
        int height = bitMatrix.getHeight();
        int[] pixels = new int[width * height];
        for (int y = 0; y < height; y++) {
            int offset = y * width;
            for (int x = 0; x < width; x++) {
                pixels[offset + x] = bitMatrix.get(x, y) ? -16777216 : -1;
            }
        }
        Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        bitmap.setPixels(pixels, 0, width, 0, 0, width, height);
        return bitmap;
    }
}
