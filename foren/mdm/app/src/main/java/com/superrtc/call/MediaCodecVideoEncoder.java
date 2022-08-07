package com.superrtc.call;

import android.annotation.TargetApi;
import android.media.MediaCodec;
import android.media.MediaCodecInfo;
import android.media.MediaCodecList;
import android.media.MediaCrypto;
import android.media.MediaFormat;
import android.opengl.GLES20;
import android.os.Build;
import android.os.Bundle;
import android.view.Surface;
import com.superrtc.call.EglBase14;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

@TargetApi(19)
/* loaded from: classes2.dex */
public class MediaCodecVideoEncoder {
    private static final int DEQUEUE_TIMEOUT = 0;
    private static final String H264_MIME_TYPE = "video/avc";
    private static final int MEDIA_CODEC_RELEASE_TIMEOUT_MS = 5000;
    private static final String TAG = "MediaCodecVideoEncoder";
    private static final int VIDEO_ControlRateConstant = 2;
    private static final String VP8_MIME_TYPE = "video/x-vnd.on2.vp8";
    private static final String VP9_MIME_TYPE = "video/x-vnd.on2.vp9";
    private int colorFormat;
    private ByteBuffer configData = null;
    private GlRectDrawer drawer;
    private EglBase14 eglBase;
    private int height;
    private Surface inputSurface;
    private MediaCodec mediaCodec;
    private Thread mediaCodecThread;
    private ByteBuffer[] outputBuffers;
    private VideoCodecType type;
    private int width;
    private static MediaCodecVideoEncoder runningInstance = null;
    private static MediaCodecVideoEncoderErrorCallback errorCallback = null;
    private static int codecErrors = 0;
    private static Set<String> hwEncoderDisabledTypes = new HashSet();
    private static final String[] supportedVp8HwCodecPrefixes = {"OMX.qcom.", "OMX.Intel."};
    private static final String[] supportedVp9HwCodecPrefixes = {"OMX.qcom."};
    private static final String[] supportedH264HwCodecPrefixes = {"OMX.qcom.", "OMX.hisi."};
    private static final String[] H264_HW_EXCEPTION_MODELS = {"SAMSUNG-SGH-I337", "Nexus 7", "Nexus 4"};
    private static final int COLOR_QCOM_FORMATYUV420PackedSemiPlanar32m = 2141391876;
    private static final int[] supportedColorList = {19, 21, 2141391872, COLOR_QCOM_FORMATYUV420PackedSemiPlanar32m};
    private static final int[] supportedSurfaceColorList = {2130708361};

    /* loaded from: classes2.dex */
    public interface MediaCodecVideoEncoderErrorCallback {
        void onMediaCodecVideoEncoderCriticalError(int i);
    }

    /* loaded from: classes2.dex */
    public enum VideoCodecType {
        VIDEO_CODEC_VP8,
        VIDEO_CODEC_VP9,
        VIDEO_CODEC_H264
    }

    public static void setErrorCallback(MediaCodecVideoEncoderErrorCallback errorCallback2) {
        Logging.d(TAG, "Set error callback");
        errorCallback = errorCallback2;
    }

    public static void disableVp8HwCodec() {
        Logging.w(TAG, "VP8 encoding is disabled by application.");
        hwEncoderDisabledTypes.add(VP8_MIME_TYPE);
    }

    public static void disableVp9HwCodec() {
        Logging.w(TAG, "VP9 encoding is disabled by application.");
        hwEncoderDisabledTypes.add(VP9_MIME_TYPE);
    }

    public static void disableH264HwCodec() {
        Logging.w(TAG, "H.264 encoding is disabled by application.");
        hwEncoderDisabledTypes.add(H264_MIME_TYPE);
    }

    public static boolean isVp8HwSupported() {
        return !hwEncoderDisabledTypes.contains(VP8_MIME_TYPE) && findHwEncoder(VP8_MIME_TYPE, supportedVp8HwCodecPrefixes, supportedColorList) != null;
    }

    public static boolean isVp9HwSupported() {
        return !hwEncoderDisabledTypes.contains(VP9_MIME_TYPE) && findHwEncoder(VP9_MIME_TYPE, supportedVp9HwCodecPrefixes, supportedColorList) != null;
    }

    public static boolean isH264HwSupported() {
        return !hwEncoderDisabledTypes.contains(H264_MIME_TYPE) && findHwEncoder(H264_MIME_TYPE, supportedH264HwCodecPrefixes, supportedColorList) != null;
    }

    public static boolean isVp8HwSupportedUsingTextures() {
        return !hwEncoderDisabledTypes.contains(VP8_MIME_TYPE) && findHwEncoder(VP8_MIME_TYPE, supportedVp8HwCodecPrefixes, supportedSurfaceColorList) != null;
    }

    public static boolean isVp9HwSupportedUsingTextures() {
        return !hwEncoderDisabledTypes.contains(VP9_MIME_TYPE) && findHwEncoder(VP9_MIME_TYPE, supportedVp9HwCodecPrefixes, supportedSurfaceColorList) != null;
    }

    public static boolean isH264HwSupportedUsingTextures() {
        return !hwEncoderDisabledTypes.contains(H264_MIME_TYPE) && findHwEncoder(H264_MIME_TYPE, supportedH264HwCodecPrefixes, supportedSurfaceColorList) != null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public static class EncoderProperties {
        public final String codecName;
        public final int colorFormat;

        public EncoderProperties(String codecName, int colorFormat) {
            this.codecName = codecName;
            this.colorFormat = colorFormat;
        }
    }

    private static EncoderProperties findHwEncoder(String mime, String[] supportedHwCodecPrefixes, int[] colorList) {
        if (Build.VERSION.SDK_INT < 19) {
            return null;
        }
        if (!mime.equals(H264_MIME_TYPE) || !Arrays.asList(H264_HW_EXCEPTION_MODELS).contains(Build.MODEL)) {
            for (int i = 0; i < MediaCodecList.getCodecCount(); i++) {
                MediaCodecInfo info = MediaCodecList.getCodecInfoAt(i);
                if (info.isEncoder()) {
                    String name = null;
                    String[] supportedTypes = info.getSupportedTypes();
                    int length = supportedTypes.length;
                    int i2 = 0;
                    while (true) {
                        if (i2 >= length) {
                            break;
                        } else if (supportedTypes[i2].equals(mime)) {
                            name = info.getName();
                            break;
                        } else {
                            i2++;
                        }
                    }
                    if (name != null) {
                        Logging.v(TAG, "Found candidate encoder " + name);
                        boolean supportedCodec = false;
                        int length2 = supportedHwCodecPrefixes.length;
                        int i3 = 0;
                        while (true) {
                            if (i3 >= length2) {
                                break;
                            } else if (name.startsWith(supportedHwCodecPrefixes[i3])) {
                                supportedCodec = true;
                                break;
                            } else {
                                i3++;
                            }
                        }
                        Logging.d(TAG, "hw encoder supportedCodec  = " + supportedCodec);
                        if (supportedCodec) {
                            MediaCodecInfo.CodecCapabilities capabilities = info.getCapabilitiesForType(mime);
                            int[] iArr = capabilities.colorFormats;
                            int length3 = iArr.length;
                            for (int i4 = 0; i4 < length3; i4++) {
                                Logging.v(TAG, "   Color: 0x" + Integer.toHexString(iArr[i4]));
                            }
                            for (int supportedColorFormat : colorList) {
                                int[] iArr2 = capabilities.colorFormats;
                                for (int codecColorFormat : iArr2) {
                                    if (codecColorFormat == supportedColorFormat) {
                                        Logging.d(TAG, "Found target encoder for mime " + mime + " : " + name + ". Color: 0x" + Integer.toHexString(codecColorFormat));
                                        return new EncoderProperties(name, codecColorFormat);
                                    }
                                }
                            }
                            continue;
                        } else {
                            continue;
                        }
                    } else {
                        continue;
                    }
                }
            }
            return null;
        }
        Logging.w(TAG, "Model: " + Build.MODEL + " has black listed H.264 encoder.");
        return null;
    }

    private void checkOnMediaCodecThread() {
        if (this.mediaCodecThread.getId() != Thread.currentThread().getId()) {
            throw new RuntimeException("MediaCodecVideoEncoder previously operated on " + this.mediaCodecThread + " but is now called on " + Thread.currentThread());
        }
    }

    public static void printStackTrace() {
        if (!(runningInstance == null || runningInstance.mediaCodecThread == null)) {
            StackTraceElement[] mediaCodecStackTraces = runningInstance.mediaCodecThread.getStackTrace();
            if (mediaCodecStackTraces.length > 0) {
                Logging.d(TAG, "MediaCodecVideoEncoder stacks trace:");
                for (StackTraceElement stackTrace : mediaCodecStackTraces) {
                    Logging.d(TAG, stackTrace.toString());
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static MediaCodec createByCodecName(String codecName) {
        try {
            return MediaCodec.createByCodecName(codecName);
        } catch (Exception e) {
            return null;
        }
    }

    boolean initEncode(VideoCodecType type, int width, int height, int kbps, int fps, EglBase14.Context sharedContext) {
        boolean useSurface = sharedContext != null;
        Logging.d(TAG, "Java initEncode: " + type + " : " + width + " x " + height + ". @ " + kbps + " kbps. Fps: " + fps + ". Encode from texture : " + useSurface);
        this.width = width;
        this.height = height;
        if (this.mediaCodecThread != null) {
            throw new RuntimeException("Forgot to release()?");
        }
        EncoderProperties properties = null;
        String mime = null;
        int keyFrameIntervalSec = 0;
        if (type == VideoCodecType.VIDEO_CODEC_VP8) {
            mime = VP8_MIME_TYPE;
            properties = findHwEncoder(VP8_MIME_TYPE, supportedVp8HwCodecPrefixes, useSurface ? supportedSurfaceColorList : supportedColorList);
            keyFrameIntervalSec = 100;
        } else if (type == VideoCodecType.VIDEO_CODEC_VP9) {
            mime = VP9_MIME_TYPE;
            properties = findHwEncoder(VP9_MIME_TYPE, supportedH264HwCodecPrefixes, useSurface ? supportedSurfaceColorList : supportedColorList);
            keyFrameIntervalSec = 100;
        } else if (type == VideoCodecType.VIDEO_CODEC_H264) {
            mime = H264_MIME_TYPE;
            properties = findHwEncoder(H264_MIME_TYPE, supportedH264HwCodecPrefixes, useSurface ? supportedSurfaceColorList : supportedColorList);
            keyFrameIntervalSec = 20;
        }
        if (properties == null) {
            throw new RuntimeException("Can not find HW encoder for " + type);
        }
        runningInstance = this;
        this.colorFormat = properties.colorFormat;
        Logging.d(TAG, "Color format: " + this.colorFormat);
        this.mediaCodecThread = Thread.currentThread();
        try {
            MediaFormat format = MediaFormat.createVideoFormat(mime, width, height);
            format.setInteger("bitrate", kbps * 1000);
            format.setInteger("bitrate-mode", 2);
            format.setInteger("color-format", properties.colorFormat);
            format.setInteger("frame-rate", fps);
            format.setInteger("i-frame-interval", keyFrameIntervalSec);
            Logging.d(TAG, "  Format: " + format);
            this.mediaCodec = createByCodecName(properties.codecName);
            this.type = type;
            if (this.mediaCodec == null) {
                Logging.e(TAG, "Can not create media encoder");
                return false;
            }
            this.mediaCodec.configure(format, (Surface) null, (MediaCrypto) null, 1);
            if (useSurface) {
                this.eglBase = new EglBase14(sharedContext, EglBase.CONFIG_RECORDABLE);
                this.inputSurface = this.mediaCodec.createInputSurface();
                this.eglBase.createSurface(this.inputSurface);
                this.drawer = new GlRectDrawer();
            }
            this.mediaCodec.start();
            this.outputBuffers = this.mediaCodec.getOutputBuffers();
            Logging.d(TAG, "Output buffers: " + this.outputBuffers.length);
            return true;
        } catch (IllegalStateException e) {
            Logging.e(TAG, "initEncode failed", e);
            return false;
        }
    }

    ByteBuffer[] getInputBuffers() {
        ByteBuffer[] inputBuffers = this.mediaCodec.getInputBuffers();
        Logging.d(TAG, "Input buffers: " + inputBuffers.length);
        return inputBuffers;
    }

    boolean encodeBuffer(boolean isKeyframe, int inputBuffer, int size, long presentationTimestampUs) {
        checkOnMediaCodecThread();
        if (isKeyframe) {
            try {
                Logging.d(TAG, "Sync frame request");
                Bundle b = new Bundle();
                b.putInt("request-sync", 0);
                this.mediaCodec.setParameters(b);
            } catch (IllegalStateException e) {
                Logging.e(TAG, "encodeBuffer failed", e);
                return false;
            }
        }
        this.mediaCodec.queueInputBuffer(inputBuffer, 0, size, presentationTimestampUs, 0);
        return true;
    }

    boolean encodeTexture(boolean isKeyframe, int oesTextureId, float[] transformationMatrix, long presentationTimestampUs) {
        checkOnMediaCodecThread();
        if (isKeyframe) {
            try {
                Logging.d(TAG, "Sync frame request");
                Bundle b = new Bundle();
                b.putInt("request-sync", 0);
                this.mediaCodec.setParameters(b);
            } catch (RuntimeException e) {
                Logging.e(TAG, "encodeTexture failed", e);
                return false;
            }
        }
        this.eglBase.makeCurrent();
        GLES20.glClear(16384);
        this.drawer.drawOes(oesTextureId, transformationMatrix, 0, 0, this.width, this.height);
        this.eglBase.swapBuffers(TimeUnit.MICROSECONDS.toNanos(presentationTimestampUs));
        return true;
    }

    void release() {
        Logging.d(TAG, "Java releaseEncoder");
        checkOnMediaCodecThread();
        final CountDownLatch releaseDone = new CountDownLatch(1);
        new Thread(new Runnable() { // from class: com.superrtc.call.MediaCodecVideoEncoder.1
            @Override // java.lang.Runnable
            public void run() {
                try {
                    Logging.d(MediaCodecVideoEncoder.TAG, "Java releaseEncoder on release thread");
                    MediaCodecVideoEncoder.this.mediaCodec.stop();
                    MediaCodecVideoEncoder.this.mediaCodec.release();
                    Logging.d(MediaCodecVideoEncoder.TAG, "Java releaseEncoder on release thread done");
                } catch (Exception e) {
                    Logging.e(MediaCodecVideoEncoder.TAG, "Media encoder release failed", e);
                }
                releaseDone.countDown();
            }
        }).start();
        if (!ThreadUtils.awaitUninterruptibly(releaseDone, 5000L)) {
            Logging.e(TAG, "Media encoder release timeout");
            codecErrors++;
            if (errorCallback != null) {
                Logging.e(TAG, "Invoke codec error callback. Errors: " + codecErrors);
                errorCallback.onMediaCodecVideoEncoderCriticalError(codecErrors);
            }
        }
        this.mediaCodec = null;
        this.mediaCodecThread = null;
        if (this.drawer != null) {
            this.drawer.release();
            this.drawer = null;
        }
        if (this.eglBase != null) {
            this.eglBase.release();
            this.eglBase = null;
        }
        if (this.inputSurface != null) {
            this.inputSurface.release();
            this.inputSurface = null;
        }
        runningInstance = null;
        Logging.d(TAG, "Java releaseEncoder done");
    }

    private boolean setRates(int kbps, int frameRateIgnored) {
        checkOnMediaCodecThread();
        Logging.v(TAG, "setRates: " + kbps + " kbps. Fps: " + frameRateIgnored);
        try {
            Bundle params = new Bundle();
            params.putInt("video-bitrate", kbps * 1000);
            this.mediaCodec.setParameters(params);
            return true;
        } catch (IllegalStateException e) {
            Logging.e(TAG, "setRates failed", e);
            return false;
        }
    }

    int dequeueInputBuffer() {
        checkOnMediaCodecThread();
        try {
            return this.mediaCodec.dequeueInputBuffer(0L);
        } catch (IllegalStateException e) {
            Logging.e(TAG, "dequeueIntputBuffer failed", e);
            return -2;
        }
    }

    /* loaded from: classes2.dex */
    static class OutputBufferInfo {
        public final ByteBuffer buffer;
        public final int index;
        public final boolean isKeyFrame;
        public final long presentationTimestampUs;

        public OutputBufferInfo(int index, ByteBuffer buffer, boolean isKeyFrame, long presentationTimestampUs) {
            this.index = index;
            this.buffer = buffer;
            this.isKeyFrame = isKeyFrame;
            this.presentationTimestampUs = presentationTimestampUs;
        }
    }

    OutputBufferInfo dequeueOutputBuffer() {
        checkOnMediaCodecThread();
        try {
            MediaCodec.BufferInfo info = new MediaCodec.BufferInfo();
            int result = this.mediaCodec.dequeueOutputBuffer(info, 0L);
            if (result >= 0) {
                if ((info.flags & 2) != 0) {
                    Logging.d(TAG, "Config frame generated. Offset: " + info.offset + ". Size: " + info.size);
                    this.configData = ByteBuffer.allocateDirect(info.size);
                    this.outputBuffers[result].position(info.offset);
                    this.outputBuffers[result].limit(info.offset + info.size);
                    this.configData.put(this.outputBuffers[result]);
                    this.mediaCodec.releaseOutputBuffer(result, false);
                    result = this.mediaCodec.dequeueOutputBuffer(info, 0L);
                }
            }
            if (result >= 0) {
                ByteBuffer outputBuffer = this.outputBuffers[result].duplicate();
                outputBuffer.position(info.offset);
                outputBuffer.limit(info.offset + info.size);
                boolean isKeyFrame = (info.flags & 1) != 0;
                if (isKeyFrame) {
                    Logging.d(TAG, "Sync frame generated");
                }
                if (!isKeyFrame || this.type != VideoCodecType.VIDEO_CODEC_H264) {
                    return new OutputBufferInfo(result, outputBuffer.slice(), isKeyFrame, info.presentationTimeUs);
                }
                Logging.d(TAG, "Appending config frame of size " + this.configData.capacity() + " to output buffer with offset " + info.offset + ", size " + info.size);
                ByteBuffer keyFrameBuffer = ByteBuffer.allocateDirect(this.configData.capacity() + info.size);
                this.configData.rewind();
                keyFrameBuffer.put(this.configData);
                keyFrameBuffer.put(outputBuffer);
                keyFrameBuffer.position(0);
                return new OutputBufferInfo(result, keyFrameBuffer, isKeyFrame, info.presentationTimeUs);
            } else if (result == -3) {
                this.outputBuffers = this.mediaCodec.getOutputBuffers();
                return dequeueOutputBuffer();
            } else if (result == -2) {
                return dequeueOutputBuffer();
            } else {
                if (result == -1) {
                    return null;
                }
                throw new RuntimeException("dequeueOutputBuffer: " + result);
            }
        } catch (IllegalStateException e) {
            Logging.e(TAG, "dequeueOutputBuffer failed", e);
            return new OutputBufferInfo(-1, null, false, -1L);
        }
    }

    boolean releaseOutputBuffer(int index) {
        checkOnMediaCodecThread();
        try {
            this.mediaCodec.releaseOutputBuffer(index, false);
            return true;
        } catch (IllegalStateException e) {
            Logging.e(TAG, "releaseOutputBuffer failed", e);
            return false;
        }
    }
}
