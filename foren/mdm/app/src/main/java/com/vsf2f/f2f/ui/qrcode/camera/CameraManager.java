package com.vsf2f.f2f.ui.qrcode.camera;

import android.content.Context;
import android.graphics.Point;
import android.graphics.Rect;
import android.hardware.Camera;
import android.os.Build;
import android.os.Handler;
import android.view.SurfaceHolder;
import java.io.IOException;

/* loaded from: classes2.dex */
public final class CameraManager {
    static final int SDK_INT;
    private static final String TAG = CameraManager.class.getSimpleName();
    private static CameraManager cameraManager;
    private final AutoFocusCallback autoFocusCallback;
    private Camera camera;
    private final CameraConfigurationManager configManager;
    private final Context context;
    private Rect framingRect;
    private Rect framingRectInPreview;
    private boolean initialized;
    private final PreviewCallback previewCallback;
    private boolean previewing;
    private final boolean useOneShotPreviewCallback;

    static {
        int sdkInt;
        try {
            sdkInt = Integer.parseInt(Build.VERSION.SDK);
        } catch (NumberFormatException e) {
            sdkInt = 10000;
        }
        SDK_INT = sdkInt;
    }

    public static void init(Context context) {
        if (cameraManager == null) {
            cameraManager = new CameraManager(context);
        }
    }

    public static CameraManager get() {
        return cameraManager;
    }

    private CameraManager(Context context) {
        this.context = context;
        this.configManager = new CameraConfigurationManager(context);
        this.useOneShotPreviewCallback = Integer.parseInt(Build.VERSION.SDK) > 3;
        this.previewCallback = new PreviewCallback(this.configManager, this.useOneShotPreviewCallback);
        this.autoFocusCallback = new AutoFocusCallback();
    }

    public void openDriver(SurfaceHolder holder) throws IOException {
        if (this.camera == null) {
            this.camera = Camera.open();
            if (this.camera == null) {
                throw new IOException();
            }
            this.camera.setPreviewDisplay(holder);
            if (!this.initialized) {
                this.initialized = true;
                this.configManager.initFromCameraParameters(this.camera);
            }
            this.configManager.setDesiredCameraParameters(this.camera);
            FlashlightManager.enableFlashlight();
        }
    }

    public void closeDriver() {
        if (this.camera != null) {
            FlashlightManager.disableFlashlight();
            this.camera.release();
            this.camera = null;
        }
    }

    public void startPreview() {
        if (this.camera != null && !this.previewing) {
            this.camera.startPreview();
            this.previewing = true;
        }
    }

    public void stopPreview() {
        if (this.camera != null && this.previewing) {
            if (!this.useOneShotPreviewCallback) {
                this.camera.setPreviewCallback(null);
            }
            this.camera.stopPreview();
            this.previewCallback.setHandler(null, 0);
            this.autoFocusCallback.setHandler(null, 0);
            this.previewing = false;
        }
    }

    public void requestPreviewFrame(Handler handler, int message) {
        if (this.camera != null && this.previewing) {
            this.previewCallback.setHandler(handler, message);
            if (this.useOneShotPreviewCallback) {
                this.camera.setOneShotPreviewCallback(this.previewCallback);
            } else {
                this.camera.setPreviewCallback(this.previewCallback);
            }
        }
    }

    public void requestAutoFocus(Handler handler, int message) {
        if (this.camera != null && this.previewing) {
            this.autoFocusCallback.setHandler(handler, message);
            this.camera.autoFocus(this.autoFocusCallback);
        }
    }

    public Rect getFramingRect() {
        Point screenResolution;
        if (this.framingRect == null) {
            if (this.camera == null || (screenResolution = this.configManager.getScreenResolution()) == null) {
                return null;
            }
            int width = (int) (this.context.getResources().getDisplayMetrics().widthPixels * 0.6d);
            int leftOffset = (screenResolution.x - width) / 2;
            int topOffset = (screenResolution.y - width) / 2;
            this.framingRect = new Rect(leftOffset, topOffset, leftOffset + width, topOffset + width);
        }
        return this.framingRect;
    }

    public Rect getFramingRectInPreview() {
        if (this.framingRectInPreview == null) {
            Rect rect = new Rect(getFramingRect());
            Point cameraResolution = this.configManager.getCameraResolution();
            Point screenResolution = this.configManager.getScreenResolution();
            rect.left = (rect.left * cameraResolution.y) / screenResolution.x;
            rect.right = (rect.right * cameraResolution.y) / screenResolution.x;
            rect.top = (rect.top * cameraResolution.x) / screenResolution.y;
            rect.bottom = (rect.bottom * cameraResolution.x) / screenResolution.y;
            this.framingRectInPreview = rect;
        }
        return this.framingRectInPreview;
    }

    public PlanarYUVLuminanceSource buildLuminanceSource(byte[] data, int width, int height) {
        Rect rect = getFramingRectInPreview();
        int previewFormat = this.configManager.getPreviewFormat();
        String previewFormatString = this.configManager.getPreviewFormatString();
        switch (previewFormat) {
            case 16:
            case 17:
                return new PlanarYUVLuminanceSource(data, width, height, rect.left, rect.top, rect.width(), rect.height());
            default:
                if ("yuv420p".equals(previewFormatString)) {
                    return new PlanarYUVLuminanceSource(data, width, height, rect.left, rect.top, rect.width(), rect.height());
                }
                throw new IllegalArgumentException("Unsupported picture format: " + previewFormat + '/' + previewFormatString);
        }
    }
}
