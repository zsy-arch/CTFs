package com.superrtc.call;

import android.annotation.TargetApi;
import android.content.Context;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CameraManager;
import android.hardware.camera2.params.StreamConfigurationMap;
import android.os.Build;
import android.os.SystemClock;
import android.util.Range;
import android.util.Size;
import com.superrtc.call.CameraEnumerationAndroid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@TargetApi(21)
/* loaded from: classes2.dex */
public class Camera2Enumerator implements CameraEnumerationAndroid.Enumerator {
    private static final double NANO_SECONDS_PER_SECOND = 1.0E9d;
    private static final String TAG = "Camera2Enumerator";
    private final Map<Integer, List<CameraEnumerationAndroid.CaptureFormat>> cachedSupportedFormats = new HashMap();
    private final CameraManager cameraManager;

    public static boolean isSupported() {
        return Build.VERSION.SDK_INT >= 21;
    }

    public Camera2Enumerator(Context context) {
        this.cameraManager = (CameraManager) context.getSystemService("camera");
    }

    @Override // com.superrtc.call.CameraEnumerationAndroid.Enumerator
    public List<CameraEnumerationAndroid.CaptureFormat> getSupportedFormats(int cameraId) {
        synchronized (this.cachedSupportedFormats) {
            if (this.cachedSupportedFormats.containsKey(Integer.valueOf(cameraId))) {
                return this.cachedSupportedFormats.get(Integer.valueOf(cameraId));
            }
            Logging.d(TAG, "Get supported formats for camera index " + cameraId + ".");
            long startTimeMs = SystemClock.elapsedRealtime();
            try {
                CameraCharacteristics cameraCharacteristics = this.cameraManager.getCameraCharacteristics(Integer.toString(cameraId));
                int defaultMaxFps = 0;
                for (Range<Integer> fpsRange : (Range[]) cameraCharacteristics.get(CameraCharacteristics.CONTROL_AE_AVAILABLE_TARGET_FPS_RANGES)) {
                    defaultMaxFps = Math.max(defaultMaxFps, fpsRange.getUpper().intValue());
                }
                StreamConfigurationMap streamMap = (StreamConfigurationMap) cameraCharacteristics.get(CameraCharacteristics.SCALER_STREAM_CONFIGURATION_MAP);
                Size[] sizes = streamMap.getOutputSizes(35);
                if (sizes == null) {
                    throw new RuntimeException("ImageFormat.YUV_420_888 not supported.");
                }
                List<CameraEnumerationAndroid.CaptureFormat> formatList = new ArrayList<>();
                for (Size size : sizes) {
                    long minFrameDurationNs = 0;
                    try {
                        minFrameDurationNs = streamMap.getOutputMinFrameDuration(35, size);
                    } catch (Exception e) {
                    }
                    formatList.add(new CameraEnumerationAndroid.CaptureFormat(size.getWidth(), size.getHeight(), 0, (minFrameDurationNs == 0 ? defaultMaxFps : (int) Math.round(NANO_SECONDS_PER_SECOND / minFrameDurationNs)) * 1000));
                }
                this.cachedSupportedFormats.put(Integer.valueOf(cameraId), formatList);
                Logging.d(TAG, "Get supported formats for camera index " + cameraId + " done. Time spent: " + (SystemClock.elapsedRealtime() - startTimeMs) + " ms.");
                return formatList;
            } catch (Exception ex) {
                Logging.e(TAG, "getCameraCharacteristics(): " + ex);
                return new ArrayList();
            }
        }
    }
}
