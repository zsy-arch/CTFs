package com.superrtc.call;

import android.graphics.ImageFormat;
import android.hardware.Camera;
import com.hyphenate.util.EMPrivateConstant;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/* loaded from: classes2.dex */
public class CameraEnumerationAndroid {
    private static final String TAG = "CameraEnumerationAndroid";
    private static Enumerator enumerator = new CameraEnumerator();

    /* loaded from: classes2.dex */
    public interface Enumerator {
        List<CaptureFormat> getSupportedFormats(int i);
    }

    public static synchronized void setEnumerator(Enumerator enumerator2) {
        synchronized (CameraEnumerationAndroid.class) {
            enumerator = enumerator2;
        }
    }

    public static synchronized List<CaptureFormat> getSupportedFormats(int cameraId) {
        List<CaptureFormat> formats;
        synchronized (CameraEnumerationAndroid.class) {
            formats = enumerator.getSupportedFormats(cameraId);
            Logging.d(TAG, "Supported formats for camera " + cameraId + ": " + formats);
        }
        return formats;
    }

    /* loaded from: classes2.dex */
    public static class CaptureFormat {
        public final int height;
        public final int imageFormat = 17;
        public final int maxFramerate;
        public final int minFramerate;
        public final int width;

        public CaptureFormat(int width, int height, int minFramerate, int maxFramerate) {
            this.width = width;
            this.height = height;
            this.minFramerate = minFramerate;
            this.maxFramerate = maxFramerate;
        }

        public int frameSize() {
            return frameSize(this.width, this.height, 17);
        }

        public static int frameSize(int width, int height, int imageFormat) {
            if (imageFormat == 17) {
                return ((width * height) * ImageFormat.getBitsPerPixel(imageFormat)) / 8;
            }
            throw new UnsupportedOperationException("Don't know how to calculate the frame size of non-NV21 image formats.");
        }

        public String toString() {
            return String.valueOf(this.width) + EMPrivateConstant.EMMultiUserConstant.MUC_ELEMENT_NAME + this.height + "@[" + this.minFramerate + ":" + this.maxFramerate + "]";
        }

        public boolean isSameFormat(CaptureFormat that) {
            if (that != null && this.width == that.width && this.height == that.height && this.maxFramerate == that.maxFramerate && this.minFramerate == that.minFramerate) {
                return true;
            }
            return false;
        }
    }

    public static String[] getDeviceNames() {
        String[] names = new String[Camera.getNumberOfCameras()];
        for (int i = 0; i < Camera.getNumberOfCameras(); i++) {
            names[i] = getDeviceName(i);
        }
        return names;
    }

    public static int getDeviceCount() {
        return Camera.getNumberOfCameras();
    }

    public static String getDeviceName(int index) {
        Camera.CameraInfo info = new Camera.CameraInfo();
        try {
            Camera.getCameraInfo(index, info);
            return "Camera " + index + ", Facing " + (info.facing == 1 ? "front" : "back") + ", Orientation " + info.orientation;
        } catch (Exception e) {
            Logging.e(TAG, "getCameraInfo failed on index " + index, e);
            return null;
        }
    }

    public static String getNameOfFrontFacingDevice() {
        return getNameOfDevice(1);
    }

    public static String getNameOfBackFacingDevice() {
        return getNameOfDevice(0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public static abstract class ClosestComparator<T> implements Comparator<T> {
        abstract int diff(T t);

        private ClosestComparator() {
        }

        /* synthetic */ ClosestComparator(ClosestComparator closestComparator) {
            this();
        }

        @Override // java.util.Comparator
        public int compare(T t1, T t2) {
            return diff(t1) - diff(t2);
        }
    }

    public static int[] getFramerateRange(Camera.Parameters parameters, final int framerate) {
        List<int[]> listFpsRange = parameters.getSupportedPreviewFpsRange();
        if (!listFpsRange.isEmpty()) {
            return (int[]) Collections.min(listFpsRange, new ClosestComparator<int[]>() { // from class: com.superrtc.call.CameraEnumerationAndroid.1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(null);
                }

                public int diff(int[] range) {
                    return range[0] + (Math.abs(framerate - range[1]) * 10);
                }
            });
        }
        Logging.w(TAG, "No supported preview fps range");
        return new int[2];
    }

    public static Camera.Size getClosestSupportedSize(List<Camera.Size> supportedSizes, final int requestedWidth, final int requestedHeight) {
        return (Camera.Size) Collections.min(supportedSizes, new ClosestComparator<Camera.Size>() { // from class: com.superrtc.call.CameraEnumerationAndroid.2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(null);
            }

            public int diff(Camera.Size size) {
                return Math.abs(requestedWidth - size.width) + Math.abs(requestedHeight - size.height);
            }
        });
    }

    private static String getNameOfDevice(int facing) {
        Camera.CameraInfo info = new Camera.CameraInfo();
        for (int i = 0; i < Camera.getNumberOfCameras(); i++) {
            try {
                Camera.getCameraInfo(i, info);
            } catch (Exception e) {
                Logging.e(TAG, "getCameraInfo() failed on index " + i, e);
            }
            if (info.facing == facing) {
                return getDeviceName(i);
            }
            continue;
        }
        return null;
    }
}
