package com.vsf2f.f2f.ui.qrcode.camera;

import android.graphics.Point;
import android.hardware.Camera;
import android.os.Handler;
import android.util.Log;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public final class PreviewCallback implements Camera.PreviewCallback {
    private static final String TAG = PreviewCallback.class.getSimpleName();
    private final CameraConfigurationManager configManager;
    private Handler previewHandler;
    private int previewMessage;
    private final boolean useOneShotPreviewCallback;

    /* JADX INFO: Access modifiers changed from: package-private */
    public PreviewCallback(CameraConfigurationManager configManager, boolean useOneShotPreviewCallback) {
        this.configManager = configManager;
        this.useOneShotPreviewCallback = useOneShotPreviewCallback;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setHandler(Handler previewHandler, int previewMessage) {
        this.previewHandler = previewHandler;
        this.previewMessage = previewMessage;
    }

    @Override // android.hardware.Camera.PreviewCallback
    public void onPreviewFrame(byte[] data, Camera camera) {
        Point cameraResolution = this.configManager.getCameraResolution();
        if (!this.useOneShotPreviewCallback) {
            camera.setPreviewCallback(null);
        }
        if (this.previewHandler != null) {
            this.previewHandler.obtainMessage(this.previewMessage, cameraResolution.x, cameraResolution.y, data).sendToTarget();
            this.previewHandler = null;
            return;
        }
        Log.d(TAG, "Got preview callback, but no handler for it");
    }
}
