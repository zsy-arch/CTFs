package com.vsf2f.f2f.ui.qrcode.decoding;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.Result;
import com.vsf2f.f2f.ui.qrcode.QrcodeActivity;
import com.vsf2f.f2f.ui.qrcode.ViewfinderResultPointCallback;
import com.vsf2f.f2f.ui.qrcode.camera.CameraManager;
import java.util.Vector;

/* loaded from: classes2.dex */
public final class CaptureActivityHandler extends Handler {
    private static final String TAG = CaptureActivityHandler.class.getSimpleName();
    private final QrcodeActivity activity;
    private final DecodeThread decodeThread;
    private State state = State.SUCCESS;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public enum State {
        PREVIEW,
        SUCCESS,
        DONE
    }

    public CaptureActivityHandler(QrcodeActivity activity, Vector<BarcodeFormat> decodeFormats, String characterSet) {
        this.activity = activity;
        this.decodeThread = new DecodeThread(activity, decodeFormats, characterSet, new ViewfinderResultPointCallback(activity.getViewfinderView()));
        this.decodeThread.start();
        CameraManager.get().startPreview();
        restartPreviewAndDecode();
    }

    @Override // android.os.Handler
    public void handleMessage(Message message) {
        switch (message.what) {
            case 101:
                if (this.state == State.PREVIEW) {
                    CameraManager.get().requestAutoFocus(this, 101);
                    return;
                }
                return;
            case 102:
            case 105:
            case 106:
            case 108:
            default:
                return;
            case 103:
                this.state = State.PREVIEW;
                CameraManager.get().requestPreviewFrame(this.decodeThread.getHandler(), 102);
                return;
            case 104:
                Log.d(TAG, "Got decode succeeded message");
                this.state = State.SUCCESS;
                Bundle bundle = message.getData();
                this.activity.handleDecode((Result) message.obj, bundle == null ? null : (Bitmap) bundle.getParcelable(DecodeThread.BARCODE_BITMAP));
                return;
            case 107:
                Log.d(TAG, "Got product query message");
                Intent intent = new Intent("android.intent.action.VIEW", Uri.parse((String) message.obj));
                intent.addFlags(524288);
                this.activity.startActivity(intent);
                return;
            case 109:
                Log.d(TAG, "Got restart preview message");
                restartPreviewAndDecode();
                return;
            case 110:
                Log.d(TAG, "Got return scan result message");
                this.activity.setResult(-1, (Intent) message.obj);
                this.activity.finish();
                return;
        }
    }

    public void quitSynchronously() {
        this.state = State.DONE;
        CameraManager.get().stopPreview();
        Message.obtain(this.decodeThread.getHandler(), 108).sendToTarget();
        try {
            this.decodeThread.join();
        } catch (InterruptedException e) {
        }
        removeMessages(104);
        removeMessages(103);
    }

    private void restartPreviewAndDecode() {
        if (this.state == State.SUCCESS) {
            this.state = State.PREVIEW;
            CameraManager.get().requestPreviewFrame(this.decodeThread.getHandler(), 102);
            CameraManager.get().requestAutoFocus(this, 101);
            this.activity.drawViewfinder();
        }
    }
}
