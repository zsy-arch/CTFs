package com.vsf2f.f2f.ui.qrcode.decoding;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.DecodeHintType;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.ReaderException;
import com.google.zxing.Result;
import com.google.zxing.common.HybridBinarizer;
import com.vsf2f.f2f.ui.qrcode.QrcodeActivity;
import com.vsf2f.f2f.ui.qrcode.camera.CameraManager;
import com.vsf2f.f2f.ui.qrcode.camera.PlanarYUVLuminanceSource;
import java.util.Hashtable;

/* loaded from: classes2.dex */
final class DecodeHandler extends Handler {
    private static final String TAG = DecodeHandler.class.getSimpleName();
    private final QrcodeActivity activity;
    private final MultiFormatReader multiFormatReader = new MultiFormatReader();

    /* JADX INFO: Access modifiers changed from: package-private */
    public DecodeHandler(QrcodeActivity activity, Hashtable<DecodeHintType, Object> hints) {
        this.multiFormatReader.setHints(hints);
        this.activity = activity;
    }

    @Override // android.os.Handler
    public void handleMessage(Message message) {
        switch (message.what) {
            case 102:
                decode((byte[]) message.obj, message.arg1, message.arg2);
                return;
            case 108:
                Looper.myLooper().quit();
                return;
            default:
                return;
        }
    }

    private void decode(byte[] data, int width, int height) {
        byte[] rotatedData = new byte[data.length];
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                rotatedData[(((x * height) + height) - y) - 1] = data[(y * width) + x];
            }
        }
        long start = System.currentTimeMillis();
        Result rawResult = null;
        PlanarYUVLuminanceSource source = CameraManager.get().buildLuminanceSource(rotatedData, height, width);
        try {
            rawResult = this.multiFormatReader.decodeWithState(new BinaryBitmap(new HybridBinarizer(source)));
        } catch (ReaderException e) {
        } finally {
            this.multiFormatReader.reset();
        }
        if (rawResult != null) {
            Log.d(TAG, "Found barcode (" + (System.currentTimeMillis() - start) + " ms):\n" + rawResult.toString());
            Message message = Message.obtain(this.activity.getHandler(), 104, rawResult);
            Bundle bundle = new Bundle();
            bundle.putParcelable(DecodeThread.BARCODE_BITMAP, source.renderCroppedGreyscaleBitmap());
            message.setData(bundle);
            message.sendToTarget();
            return;
        }
        Message.obtain(this.activity.getHandler(), 103).sendToTarget();
    }
}
