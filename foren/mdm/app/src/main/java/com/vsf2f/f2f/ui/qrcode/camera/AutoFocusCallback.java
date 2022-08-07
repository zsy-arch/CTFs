package com.vsf2f.f2f.ui.qrcode.camera;

import android.hardware.Camera;
import android.os.Handler;
import android.util.Log;

/* loaded from: classes2.dex */
final class AutoFocusCallback implements Camera.AutoFocusCallback {
    private static final long AUTOFOCUS_INTERVAL_MS = 1500;
    private static final String TAG = AutoFocusCallback.class.getSimpleName();
    private Handler autoFocusHandler;
    private int autoFocusMessage;

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setHandler(Handler autoFocusHandler, int autoFocusMessage) {
        this.autoFocusHandler = autoFocusHandler;
        this.autoFocusMessage = autoFocusMessage;
    }

    @Override // android.hardware.Camera.AutoFocusCallback
    public void onAutoFocus(boolean success, Camera camera) {
        if (this.autoFocusHandler != null) {
            this.autoFocusHandler.sendMessageDelayed(this.autoFocusHandler.obtainMessage(this.autoFocusMessage, Boolean.valueOf(success)), AUTOFOCUS_INTERVAL_MS);
            this.autoFocusHandler = null;
            return;
        }
        Log.d(TAG, "Got auto-focus callback, but no handler for it");
    }
}
