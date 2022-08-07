package com.superrtc.call;

import android.hardware.Camera;
import android.os.SystemClock;
import com.superrtc.call.CameraEnumerationAndroid;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes2.dex */
public class CameraEnumerator implements CameraEnumerationAndroid.Enumerator {
    private static final String TAG = "CameraEnumerator";
    private List<List<CameraEnumerationAndroid.CaptureFormat>> cachedSupportedFormats;

    @Override // com.superrtc.call.CameraEnumerationAndroid.Enumerator
    public List<CameraEnumerationAndroid.CaptureFormat> getSupportedFormats(int cameraId) {
        synchronized (this) {
            if (this.cachedSupportedFormats == null) {
                this.cachedSupportedFormats = new ArrayList();
                for (int i = 0; i < CameraEnumerationAndroid.getDeviceCount(); i++) {
                    this.cachedSupportedFormats.add(enumerateFormats(i));
                }
            }
        }
        return this.cachedSupportedFormats.get(cameraId);
    }

    private List<CameraEnumerationAndroid.CaptureFormat> enumerateFormats(int cameraId) {
        List<CameraEnumerationAndroid.CaptureFormat> formatList;
        Logging.d(TAG, "Get supported formats for camera index " + cameraId + ".");
        long startTimeMs = SystemClock.elapsedRealtime();
        Camera camera = null;
        try {
            try {
                Logging.d(TAG, "Opening camera with index " + cameraId);
                camera = Camera.open(cameraId);
                Camera.Parameters parameters = camera.getParameters();
                formatList = new ArrayList<>();
                int minFps = 0;
                int maxFps = 0;
                try {
                    List<int[]> listFpsRange = parameters.getSupportedPreviewFpsRange();
                    if (listFpsRange != null) {
                        int[] range = listFpsRange.get(listFpsRange.size() - 1);
                        minFps = range[0];
                        maxFps = range[1];
                    }
                    for (Camera.Size size : parameters.getSupportedPreviewSizes()) {
                        formatList.add(new CameraEnumerationAndroid.CaptureFormat(size.width, size.height, minFps, maxFps));
                    }
                } catch (Exception e) {
                    Logging.e(TAG, "getSupportedFormats() failed on camera index " + cameraId, e);
                }
                Logging.d(TAG, "Get supported formats for camera index " + cameraId + " done. Time spent: " + (SystemClock.elapsedRealtime() - startTimeMs) + " ms.");
            } catch (RuntimeException e2) {
                Logging.e(TAG, "Open camera failed on camera index " + cameraId, e2);
                formatList = new ArrayList<>();
                if (camera != null) {
                    camera.release();
                }
            }
            return formatList;
        } finally {
            if (camera != null) {
                camera.release();
            }
        }
    }
}
