package com.superrtc.call;

import android.content.Context;
import com.superrtc.call.CameraEnumerationAndroid;
import java.util.List;

/* loaded from: classes2.dex */
public interface VideoCapturer {

    /* loaded from: classes2.dex */
    public interface CapturerObserver {
        void onByteBufferFrameCaptured(byte[] bArr, int i, int i2, int i3, long j);

        void onCapturerStarted(boolean z);

        void onOutputFormatRequest(int i, int i2, int i3);

        void onTextureFrameCaptured(int i, int i2, int i3, float[] fArr, int i4, long j);
    }

    void dispose();

    List<CameraEnumerationAndroid.CaptureFormat> getSupportedFormats();

    boolean getSupportedGetCameraFormats();

    SurfaceTextureHelper getSurfaceTextureHelper();

    void startCapture(int i, int i2, int i3, Context context, CapturerObserver capturerObserver);

    void stopCapture() throws InterruptedException;

    /* loaded from: classes2.dex */
    public static class NativeObserver implements CapturerObserver {
        private final long nativeCapturer;

        private native void nativeCapturerStarted(long j, boolean z);

        private native void nativeOnByteBufferFrameCaptured(long j, byte[] bArr, int i, int i2, int i3, int i4, long j2);

        private native void nativeOnOutputFormatRequest(long j, int i, int i2, int i3);

        private native void nativeOnTextureFrameCaptured(long j, int i, int i2, int i3, float[] fArr, int i4, long j2);

        public NativeObserver(long nativeCapturer) {
            this.nativeCapturer = nativeCapturer;
        }

        @Override // com.superrtc.call.VideoCapturer.CapturerObserver
        public void onCapturerStarted(boolean success) {
            nativeCapturerStarted(this.nativeCapturer, success);
        }

        @Override // com.superrtc.call.VideoCapturer.CapturerObserver
        public void onByteBufferFrameCaptured(byte[] data, int width, int height, int rotation, long timeStamp) {
            nativeOnByteBufferFrameCaptured(this.nativeCapturer, data, data.length, width, height, rotation, timeStamp);
        }

        @Override // com.superrtc.call.VideoCapturer.CapturerObserver
        public void onTextureFrameCaptured(int width, int height, int oesTextureId, float[] transformMatrix, int rotation, long timestamp) {
            nativeOnTextureFrameCaptured(this.nativeCapturer, width, height, oesTextureId, transformMatrix, rotation, timestamp);
        }

        @Override // com.superrtc.call.VideoCapturer.CapturerObserver
        public void onOutputFormatRequest(int width, int height, int framerate) {
            nativeOnOutputFormatRequest(this.nativeCapturer, width, height, framerate);
        }
    }
}
