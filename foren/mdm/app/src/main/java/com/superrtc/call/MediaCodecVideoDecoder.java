package com.superrtc.call;

import android.media.MediaCodec;
import android.media.MediaCodecInfo;
import android.media.MediaCodecList;
import android.media.MediaCrypto;
import android.media.MediaFormat;
import android.os.Build;
import android.os.SystemClock;
import android.view.Surface;
import com.hyphenate.chat.MessageEncoder;
import com.superrtc.call.SurfaceTextureHelper;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/* loaded from: classes2.dex */
public class MediaCodecVideoDecoder {
    private static final int DEQUEUE_INPUT_TIMEOUT = 500000;
    private static final String H264_MIME_TYPE = "video/avc";
    private static final long MAX_DECODE_TIME_MS = 200;
    private static final int MAX_QUEUED_OUTPUTBUFFERS = 3;
    private static final int MEDIA_CODEC_RELEASE_TIMEOUT_MS = 5000;
    private static final String TAG = "MediaCodecVideoDecoder";
    private static final String VP8_MIME_TYPE = "video/x-vnd.on2.vp8";
    private static final String VP9_MIME_TYPE = "video/x-vnd.on2.vp9";
    private int colorFormat;
    private int droppedFrames;
    private boolean hasDecodedFirstFrame;
    private int height;
    private ByteBuffer[] inputBuffers;
    private MediaCodec mediaCodec;
    private Thread mediaCodecThread;
    private ByteBuffer[] outputBuffers;
    private int sliceHeight;
    private int stride;
    private TextureListener textureListener;
    private boolean useSurface;
    private int width;
    private static MediaCodecVideoDecoder runningInstance = null;
    private static MediaCodecVideoDecoderErrorCallback errorCallback = null;
    private static int codecErrors = 0;
    private static Set<String> hwDecoderDisabledTypes = new HashSet();
    private static final String[] supportedVp8HwCodecPrefixes = {"OMX.qcom.", "OMX.Nvidia.", "OMX.Exynos.", "OMX.Intel."};
    private static final String[] supportedVp9HwCodecPrefixes = {"OMX.qcom.", "OMX.Exynos."};
    private static final String[] supportedH264HwCodecPrefixes = {"OMX.qcom.", "OMX.Intel.", "OMX.hisi."};
    private static final int COLOR_QCOM_FORMATYUV420PackedSemiPlanar32m = 2141391876;
    private static final List<Integer> supportedColorList = Arrays.asList(19, 21, 2141391872, Integer.valueOf((int) COLOR_QCOM_FORMATYUV420PackedSemiPlanar32m), 17);
    private final Queue<TimeStamps> decodeStartTimeMs = new LinkedList();
    private Surface surface = null;
    private final Queue<DecodedOutputBuffer> dequeuedSurfaceOutputBuffers = new LinkedList();

    /* loaded from: classes2.dex */
    public interface MediaCodecVideoDecoderErrorCallback {
        void onMediaCodecVideoDecoderCriticalError(int i);
    }

    /* loaded from: classes2.dex */
    public enum VideoCodecType {
        VIDEO_CODEC_VP8,
        VIDEO_CODEC_VP9,
        VIDEO_CODEC_H264
    }

    public static void setErrorCallback(MediaCodecVideoDecoderErrorCallback errorCallback2) {
        Logging.d(TAG, "Set error callback");
        errorCallback = errorCallback2;
    }

    public static void disableVp8HwCodec() {
        Logging.w(TAG, "VP8 decoding is disabled by application.");
        hwDecoderDisabledTypes.add(VP8_MIME_TYPE);
    }

    public static void disableVp9HwCodec() {
        Logging.w(TAG, "VP9 decoding is disabled by application.");
        hwDecoderDisabledTypes.add(VP9_MIME_TYPE);
    }

    public static void disableH264HwCodec() {
        Logging.w(TAG, "H.264 decoding is disabled by application.");
        hwDecoderDisabledTypes.add(H264_MIME_TYPE);
    }

    public static boolean isVp8HwSupported() {
        return !hwDecoderDisabledTypes.contains(VP8_MIME_TYPE) && findDecoder(VP8_MIME_TYPE, supportedVp8HwCodecPrefixes) != null;
    }

    public static boolean isVp9HwSupported() {
        return !hwDecoderDisabledTypes.contains(VP9_MIME_TYPE) && findDecoder(VP9_MIME_TYPE, supportedVp9HwCodecPrefixes) != null;
    }

    public static boolean isH264HwSupported() {
        return !hwDecoderDisabledTypes.contains(H264_MIME_TYPE) && findDecoder(H264_MIME_TYPE, supportedH264HwCodecPrefixes) != null;
    }

    public static void printStackTrace() {
        if (!(runningInstance == null || runningInstance.mediaCodecThread == null)) {
            StackTraceElement[] mediaCodecStackTraces = runningInstance.mediaCodecThread.getStackTrace();
            if (mediaCodecStackTraces.length > 0) {
                Logging.d(TAG, "MediaCodecVideoDecoder stacks trace:");
                for (StackTraceElement stackTrace : mediaCodecStackTraces) {
                    Logging.d(TAG, stackTrace.toString());
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public static class DecoderProperties {
        public final String codecName;
        public final int colorFormat;

        public DecoderProperties(String codecName, int colorFormat) {
            this.codecName = codecName;
            this.colorFormat = colorFormat;
        }
    }

    private static DecoderProperties findDecoder(String mime, String[] supportedCodecPrefixes) {
        if (Build.VERSION.SDK_INT < 19) {
            return null;
        }
        Logging.d(TAG, "Trying to find HW decoder for mime " + mime);
        for (int i = 0; i < MediaCodecList.getCodecCount(); i++) {
            MediaCodecInfo info = MediaCodecList.getCodecInfoAt(i);
            if (!info.isEncoder()) {
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
                    Logging.d(TAG, "Found candidate decoder " + name);
                    boolean supportedCodec = false;
                    int length2 = supportedCodecPrefixes.length;
                    int i3 = 0;
                    while (true) {
                        if (i3 >= length2) {
                            break;
                        } else if (name.startsWith(supportedCodecPrefixes[i3])) {
                            supportedCodec = true;
                            break;
                        } else {
                            i3++;
                        }
                    }
                    Logging.d(TAG, "hw decoder supportedCodec  = " + supportedCodec);
                    if (supportedCodec) {
                        MediaCodecInfo.CodecCapabilities capabilities = info.getCapabilitiesForType(mime);
                        int[] iArr = capabilities.colorFormats;
                        int length3 = iArr.length;
                        for (int i4 = 0; i4 < length3; i4++) {
                            Logging.v(TAG, "   Color: 0x" + Integer.toHexString(iArr[i4]));
                        }
                        for (Integer num : supportedColorList) {
                            int supportedColorFormat = num.intValue();
                            int[] iArr2 = capabilities.colorFormats;
                            for (int codecColorFormat : iArr2) {
                                if (codecColorFormat == supportedColorFormat) {
                                    Logging.d(TAG, "Found target decoder " + name + ". Color: 0x" + Integer.toHexString(codecColorFormat));
                                    return new DecoderProperties(name, codecColorFormat);
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
        Logging.d(TAG, "No HW decoder found for mime " + mime);
        return null;
    }

    private void checkOnMediaCodecThread() throws IllegalStateException {
        if (this.mediaCodecThread.getId() != Thread.currentThread().getId()) {
            throw new IllegalStateException("MediaCodecVideoDecoder previously operated on " + this.mediaCodecThread + " but is now called on " + Thread.currentThread());
        }
    }

    private boolean initDecode(VideoCodecType type, int width, int height, SurfaceTextureHelper surfaceTextureHelper) {
        boolean z;
        String mime;
        String[] supportedCodecPrefixes;
        if (this.mediaCodecThread != null) {
            throw new RuntimeException("initDecode: Forgot to release()?");
        }
        if (surfaceTextureHelper != null) {
            z = true;
        } else {
            z = false;
        }
        this.useSurface = z;
        if (type == VideoCodecType.VIDEO_CODEC_VP8) {
            mime = VP8_MIME_TYPE;
            supportedCodecPrefixes = supportedVp8HwCodecPrefixes;
        } else if (type == VideoCodecType.VIDEO_CODEC_VP9) {
            mime = VP9_MIME_TYPE;
            supportedCodecPrefixes = supportedVp9HwCodecPrefixes;
        } else if (type == VideoCodecType.VIDEO_CODEC_H264) {
            mime = H264_MIME_TYPE;
            supportedCodecPrefixes = supportedH264HwCodecPrefixes;
        } else {
            throw new RuntimeException("initDecode: Non-supported codec " + type);
        }
        DecoderProperties properties = findDecoder(mime, supportedCodecPrefixes);
        if (properties == null) {
            throw new RuntimeException("Cannot find HW decoder for " + type);
        }
        Logging.d(TAG, "Java initDecode: " + type + " : " + width + " x " + height + ". Color: 0x" + Integer.toHexString(properties.colorFormat) + ". Use Surface: " + this.useSurface);
        runningInstance = this;
        this.mediaCodecThread = Thread.currentThread();
        try {
            this.width = width;
            this.height = height;
            this.stride = width;
            this.sliceHeight = height;
            if (this.useSurface) {
                this.textureListener = new TextureListener(surfaceTextureHelper);
                this.surface = new Surface(surfaceTextureHelper.getSurfaceTexture());
            }
            MediaFormat format = MediaFormat.createVideoFormat(mime, width, height);
            if (!this.useSurface) {
                format.setInteger("color-format", properties.colorFormat);
            }
            Logging.d(TAG, "  Format: " + format);
            this.mediaCodec = MediaCodecVideoEncoder.createByCodecName(properties.codecName);
            if (this.mediaCodec == null) {
                Logging.e(TAG, "Can not create media decoder");
                return false;
            }
            this.mediaCodec.configure(format, this.surface, (MediaCrypto) null, 0);
            this.mediaCodec.start();
            this.colorFormat = properties.colorFormat;
            this.outputBuffers = this.mediaCodec.getOutputBuffers();
            this.inputBuffers = this.mediaCodec.getInputBuffers();
            this.decodeStartTimeMs.clear();
            this.hasDecodedFirstFrame = false;
            this.dequeuedSurfaceOutputBuffers.clear();
            this.droppedFrames = 0;
            Logging.d(TAG, "Input buffers: " + this.inputBuffers.length + ". Output buffers: " + this.outputBuffers.length);
            return true;
        } catch (IllegalStateException e) {
            Logging.e(TAG, "initDecode failed", e);
            return false;
        }
    }

    private void reset(int width, int height) {
        if (this.mediaCodecThread == null || this.mediaCodec == null) {
            throw new RuntimeException("Incorrect reset call for non-initialized decoder.");
        }
        Logging.d(TAG, "Java reset: " + width + " x " + height);
        this.mediaCodec.flush();
        this.width = width;
        this.height = height;
        this.decodeStartTimeMs.clear();
        this.dequeuedSurfaceOutputBuffers.clear();
        this.hasDecodedFirstFrame = false;
        this.droppedFrames = 0;
    }

    private void release() {
        Logging.d(TAG, "Java releaseDecoder. Total number of dropped frames: " + this.droppedFrames);
        checkOnMediaCodecThread();
        final CountDownLatch releaseDone = new CountDownLatch(1);
        new Thread(new Runnable() { // from class: com.superrtc.call.MediaCodecVideoDecoder.1
            @Override // java.lang.Runnable
            public void run() {
                try {
                    Logging.d(MediaCodecVideoDecoder.TAG, "Java releaseDecoder on release thread");
                    MediaCodecVideoDecoder.this.mediaCodec.stop();
                    MediaCodecVideoDecoder.this.mediaCodec.release();
                    Logging.d(MediaCodecVideoDecoder.TAG, "Java releaseDecoder on release thread done");
                } catch (Exception e) {
                    Logging.e(MediaCodecVideoDecoder.TAG, "Media decoder release failed", e);
                }
                releaseDone.countDown();
            }
        }).start();
        if (!ThreadUtils.awaitUninterruptibly(releaseDone, 5000L)) {
            Logging.e(TAG, "Media decoder release timeout");
            codecErrors++;
            if (errorCallback != null) {
                Logging.e(TAG, "Invoke codec error callback. Errors: " + codecErrors);
                errorCallback.onMediaCodecVideoDecoderCriticalError(codecErrors);
            }
        }
        this.mediaCodec = null;
        this.mediaCodecThread = null;
        runningInstance = null;
        if (this.useSurface) {
            this.surface.release();
            this.surface = null;
            this.textureListener.release();
        }
        Logging.d(TAG, "Java releaseDecoder done");
    }

    private int dequeueInputBuffer() {
        checkOnMediaCodecThread();
        try {
            return this.mediaCodec.dequeueInputBuffer(500000L);
        } catch (IllegalStateException e) {
            Logging.e(TAG, "dequeueIntputBuffer failed", e);
            return -2;
        }
    }

    private boolean queueInputBuffer(int inputBufferIndex, int size, long presentationTimeStamUs, long timeStampMs, long ntpTimeStamp) {
        checkOnMediaCodecThread();
        try {
            this.inputBuffers[inputBufferIndex].position(0);
            this.inputBuffers[inputBufferIndex].limit(size);
            this.decodeStartTimeMs.add(new TimeStamps(SystemClock.elapsedRealtime(), timeStampMs, ntpTimeStamp));
            this.mediaCodec.queueInputBuffer(inputBufferIndex, 0, size, presentationTimeStamUs, 0);
            return true;
        } catch (IllegalStateException e) {
            Logging.e(TAG, "decode failed", e);
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public static class TimeStamps {
        private final long decodeStartTimeMs;
        private final long ntpTimeStampMs;
        private final long timeStampMs;

        public TimeStamps(long decodeStartTimeMs, long timeStampMs, long ntpTimeStampMs) {
            this.decodeStartTimeMs = decodeStartTimeMs;
            this.timeStampMs = timeStampMs;
            this.ntpTimeStampMs = ntpTimeStampMs;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public static class DecodedOutputBuffer {
        private final long decodeTimeMs;
        private final long endDecodeTimeMs;
        private final int index;
        private final long ntpTimeStampMs;
        private final int offset;
        private final long presentationTimeStampMs;
        private final int size;
        private final long timeStampMs;

        public DecodedOutputBuffer(int index, int offset, int size, long presentationTimeStampMs, long timeStampMs, long ntpTimeStampMs, long decodeTime, long endDecodeTime) {
            this.index = index;
            this.offset = offset;
            this.size = size;
            this.presentationTimeStampMs = presentationTimeStampMs;
            this.timeStampMs = timeStampMs;
            this.ntpTimeStampMs = ntpTimeStampMs;
            this.decodeTimeMs = decodeTime;
            this.endDecodeTimeMs = endDecodeTime;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public static class DecodedTextureBuffer {
        private final long decodeTimeMs;
        private final long frameDelayMs;
        private final long ntpTimeStampMs;
        private final long presentationTimeStampMs;
        private final int textureID;
        private final long timeStampMs;
        private final float[] transformMatrix;

        public DecodedTextureBuffer(int textureID, float[] transformMatrix, long presentationTimeStampMs, long timeStampMs, long ntpTimeStampMs, long decodeTimeMs, long frameDelay) {
            this.textureID = textureID;
            this.transformMatrix = transformMatrix;
            this.presentationTimeStampMs = presentationTimeStampMs;
            this.timeStampMs = timeStampMs;
            this.ntpTimeStampMs = ntpTimeStampMs;
            this.decodeTimeMs = decodeTimeMs;
            this.frameDelayMs = frameDelay;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public static class TextureListener implements SurfaceTextureHelper.OnTextureFrameAvailableListener {
        private DecodedOutputBuffer bufferToRender;
        private final Object newFrameLock = new Object();
        private DecodedTextureBuffer renderedBuffer;
        private final SurfaceTextureHelper surfaceTextureHelper;

        public TextureListener(SurfaceTextureHelper surfaceTextureHelper) {
            this.surfaceTextureHelper = surfaceTextureHelper;
            surfaceTextureHelper.startListening(this);
        }

        public void addBufferToRender(DecodedOutputBuffer buffer) {
            if (this.bufferToRender != null) {
                Logging.e(MediaCodecVideoDecoder.TAG, "Unexpected addBufferToRender() called while waiting for a texture.");
                throw new IllegalStateException("Waiting for a texture.");
            } else {
                this.bufferToRender = buffer;
            }
        }

        public boolean isWaitingForTexture() {
            boolean z;
            synchronized (this.newFrameLock) {
                z = this.bufferToRender != null;
            }
            return z;
        }

        @Override // com.superrtc.call.SurfaceTextureHelper.OnTextureFrameAvailableListener
        public void onTextureFrameAvailable(int oesTextureId, float[] transformMatrix, long timestampNs) {
            synchronized (this.newFrameLock) {
                if (this.renderedBuffer != null) {
                    Logging.e(MediaCodecVideoDecoder.TAG, "Unexpected onTextureFrameAvailable() called while already holding a texture.");
                    throw new IllegalStateException("Already holding a texture.");
                }
                this.renderedBuffer = new DecodedTextureBuffer(oesTextureId, transformMatrix, this.bufferToRender.presentationTimeStampMs, this.bufferToRender.timeStampMs, this.bufferToRender.ntpTimeStampMs, this.bufferToRender.decodeTimeMs, SystemClock.elapsedRealtime() - this.bufferToRender.endDecodeTimeMs);
                this.bufferToRender = null;
                this.newFrameLock.notifyAll();
            }
        }

        public DecodedTextureBuffer dequeueTextureBuffer(int timeoutMs) {
            DecodedTextureBuffer returnedBuffer;
            synchronized (this.newFrameLock) {
                if (this.renderedBuffer == null && timeoutMs > 0 && isWaitingForTexture()) {
                    try {
                        this.newFrameLock.wait(timeoutMs);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
                returnedBuffer = this.renderedBuffer;
                this.renderedBuffer = null;
            }
            return returnedBuffer;
        }

        public void release() {
            this.surfaceTextureHelper.dispose();
            synchronized (this.newFrameLock) {
                if (this.renderedBuffer != null) {
                    this.surfaceTextureHelper.returnTextureFrame();
                    this.renderedBuffer = null;
                }
            }
        }
    }

    private DecodedOutputBuffer dequeueOutputBuffer(int dequeueTimeoutMs) {
        int new_width;
        int new_height;
        checkOnMediaCodecThread();
        if (this.decodeStartTimeMs.isEmpty()) {
            return null;
        }
        MediaCodec.BufferInfo info = new MediaCodec.BufferInfo();
        while (true) {
            int result = this.mediaCodec.dequeueOutputBuffer(info, TimeUnit.MILLISECONDS.toMicros(dequeueTimeoutMs));
            switch (result) {
                case -3:
                    this.outputBuffers = this.mediaCodec.getOutputBuffers();
                    Logging.d(TAG, "Decoder output buffers changed: " + this.outputBuffers.length);
                    if (!this.hasDecodedFirstFrame) {
                        break;
                    } else {
                        throw new RuntimeException("Unexpected output buffer change event.");
                    }
                case -2:
                    MediaFormat format = this.mediaCodec.getOutputFormat();
                    Logging.d(TAG, "Decoder format changed: " + format.toString());
                    new_width = format.getInteger("width");
                    new_height = format.getInteger(MessageEncoder.ATTR_IMG_HEIGHT);
                    if (!this.hasDecodedFirstFrame || (new_width == this.width && new_height == this.height)) {
                        this.width = format.getInteger("width");
                        this.height = format.getInteger(MessageEncoder.ATTR_IMG_HEIGHT);
                        if (!this.useSurface && format.containsKey("color-format")) {
                            this.colorFormat = format.getInteger("color-format");
                            Logging.d(TAG, "Color: 0x" + Integer.toHexString(this.colorFormat));
                            if (!supportedColorList.contains(Integer.valueOf(this.colorFormat))) {
                                throw new IllegalStateException("Non supported color format: " + this.colorFormat);
                            }
                        }
                        if (format.containsKey("stride")) {
                            this.stride = format.getInteger("stride");
                        }
                        if (format.containsKey("slice-height")) {
                            this.sliceHeight = format.getInteger("slice-height");
                        }
                        Logging.d(TAG, "Frame stride and slice height: " + this.stride + " x " + this.sliceHeight);
                        this.stride = Math.max(this.width, this.stride);
                        this.sliceHeight = Math.max(this.height, this.sliceHeight);
                        break;
                    }
                    break;
                case -1:
                    return null;
                default:
                    this.hasDecodedFirstFrame = true;
                    TimeStamps timeStamps = this.decodeStartTimeMs.remove();
                    long decodeTimeMs = SystemClock.elapsedRealtime() - timeStamps.decodeStartTimeMs;
                    if (decodeTimeMs > MAX_DECODE_TIME_MS) {
                        Logging.e(TAG, "Very high decode time: " + decodeTimeMs + "ms. Might be caused by resuming H264 decoding after a pause.");
                        decodeTimeMs = MAX_DECODE_TIME_MS;
                    }
                    return new DecodedOutputBuffer(result, info.offset, info.size, TimeUnit.MICROSECONDS.toMillis(info.presentationTimeUs), timeStamps.timeStampMs, timeStamps.ntpTimeStampMs, decodeTimeMs, SystemClock.elapsedRealtime());
            }
        }
        throw new RuntimeException("Unexpected size change. Configured " + this.width + "*" + this.height + ". New " + new_width + "*" + new_height);
    }

    private DecodedTextureBuffer dequeueTextureBuffer(int dequeueTimeoutMs) {
        checkOnMediaCodecThread();
        if (!this.useSurface) {
            throw new IllegalStateException("dequeueTexture() called for byte buffer decoding.");
        }
        DecodedOutputBuffer outputBuffer = dequeueOutputBuffer(dequeueTimeoutMs);
        if (outputBuffer != null) {
            this.dequeuedSurfaceOutputBuffers.add(outputBuffer);
        }
        MaybeRenderDecodedTextureBuffer();
        DecodedTextureBuffer renderedBuffer = this.textureListener.dequeueTextureBuffer(dequeueTimeoutMs);
        if (renderedBuffer != null) {
            MaybeRenderDecodedTextureBuffer();
            return renderedBuffer;
        } else if (this.dequeuedSurfaceOutputBuffers.size() < Math.min(3, this.outputBuffers.length) && (dequeueTimeoutMs <= 0 || this.dequeuedSurfaceOutputBuffers.isEmpty())) {
            return null;
        } else {
            this.droppedFrames++;
            DecodedOutputBuffer droppedFrame = this.dequeuedSurfaceOutputBuffers.remove();
            if (dequeueTimeoutMs > 0) {
                Logging.w(TAG, "Draining decoder. Dropping frame with TS: " + droppedFrame.presentationTimeStampMs + ". Total number of dropped frames: " + this.droppedFrames);
            } else {
                Logging.w(TAG, "Too many output buffers " + this.dequeuedSurfaceOutputBuffers.size() + ". Dropping frame with TS: " + droppedFrame.presentationTimeStampMs + ". Total number of dropped frames: " + this.droppedFrames);
            }
            this.mediaCodec.releaseOutputBuffer(droppedFrame.index, false);
            return new DecodedTextureBuffer(0, null, droppedFrame.presentationTimeStampMs, droppedFrame.timeStampMs, droppedFrame.ntpTimeStampMs, droppedFrame.decodeTimeMs, SystemClock.elapsedRealtime() - droppedFrame.endDecodeTimeMs);
        }
    }

    private void MaybeRenderDecodedTextureBuffer() {
        if (!this.dequeuedSurfaceOutputBuffers.isEmpty() && !this.textureListener.isWaitingForTexture()) {
            DecodedOutputBuffer buffer = this.dequeuedSurfaceOutputBuffers.remove();
            this.textureListener.addBufferToRender(buffer);
            this.mediaCodec.releaseOutputBuffer(buffer.index, true);
        }
    }

    private void returnDecodedOutputBuffer(int index) throws IllegalStateException, MediaCodec.CodecException {
        checkOnMediaCodecThread();
        if (this.useSurface) {
            throw new IllegalStateException("returnDecodedOutputBuffer() called for surface decoding.");
        }
        this.mediaCodec.releaseOutputBuffer(index, false);
    }
}
