package com.superrtc.call;

import android.content.Context;
import android.hardware.Camera;
import android.os.Handler;
import android.os.SystemClock;
import android.view.WindowManager;
import com.hyphenate.util.EMPrivateConstant;
import com.superrtc.call.CameraEnumerationAndroid;
import com.superrtc.call.EglBase;
import com.superrtc.call.SurfaceTextureHelper;
import com.superrtc.call.ThreadUtils;
import com.superrtc.call.VideoCapturer;
import com.umeng.analytics.a;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/* loaded from: classes2.dex */
public class VideoCapturerAndroid implements VideoCapturer, Camera.PreviewCallback, SurfaceTextureHelper.OnTextureFrameAvailableListener {
    private static final int CAMERA_FREEZE_REPORT_TIMOUT_MS = 4000;
    private static final int CAMERA_OBSERVER_PERIOD_MS = 2000;
    private static final int MAX_OPEN_CAMERA_ATTEMPTS = 3;
    private static final int NUMBER_OF_CAPTURE_BUFFERS = 3;
    private static final int OPEN_CAMERA_DELAY_MS = 500;
    private static final String TAG = "VideoCapturerAndroid";
    private Context applicationContext;
    private Camera camera;
    private final CameraStatistics cameraStatistics;
    private Thread cameraThread;
    private final Handler cameraThreadHandler;
    private CameraEnumerationAndroid.CaptureFormat captureFormat;
    private VideoCapturerDataProcessor dataprocessor;
    private final CameraEventsHandler eventsHandler;
    private boolean firstFrameReported;
    private int id;
    private Camera.CameraInfo info;
    private final boolean isCapturingToTexture;
    private int openCameraAttempts;
    private Runnable openCameraOnCodecThreadRunner;
    private volatile boolean pendingCameraSwitch;
    private int requestedFramerate;
    private int requestedHeight;
    private int requestedWidth;
    final SurfaceTextureHelper surfaceHelper;
    private final Object cameraIdLock = new Object();
    private final Object pendingCameraSwitchLock = new Object();
    private VideoCapturer.CapturerObserver frameObserver = null;
    private final Set<byte[]> queuedBuffers = new HashSet();
    private boolean dropNextFrame = false;
    private boolean enableCamera = true;
    private boolean supportedCamraFormats = true;
    private final Camera.ErrorCallback cameraErrorCallback = new Camera.ErrorCallback() { // from class: com.superrtc.call.VideoCapturerAndroid.1
        @Override // android.hardware.Camera.ErrorCallback
        public void onError(int error, Camera camera) {
            String errorMessage;
            if (error == 100) {
                errorMessage = "Camera server died!";
            } else {
                errorMessage = "Camera error: " + error;
            }
            Logging.e(VideoCapturerAndroid.TAG, errorMessage);
            if (VideoCapturerAndroid.this.eventsHandler != null) {
                VideoCapturerAndroid.this.eventsHandler.onCameraError(errorMessage);
            }
        }
    };
    private final Runnable cameraObserver = new Runnable() { // from class: com.superrtc.call.VideoCapturerAndroid.2
        private int freezePeriodCount;

        @Override // java.lang.Runnable
        public void run() {
            int cameraFramesCount = VideoCapturerAndroid.this.cameraStatistics.getAndResetFrameCount();
            Logging.d(VideoCapturerAndroid.TAG, "Camera fps: " + (((cameraFramesCount * 1000) + 1000) / 2000) + ".");
            if (cameraFramesCount == 0) {
                this.freezePeriodCount++;
                if (this.freezePeriodCount * 2000 >= 4000 && VideoCapturerAndroid.this.eventsHandler != null) {
                    Logging.e(VideoCapturerAndroid.TAG, "Camera freezed.");
                    if (VideoCapturerAndroid.this.surfaceHelper.isTextureInUse()) {
                        VideoCapturerAndroid.this.eventsHandler.onCameraFreezed("Camera failure. Client must return video buffers.");
                        return;
                    } else {
                        VideoCapturerAndroid.this.eventsHandler.onCameraFreezed("Camera failure.");
                        return;
                    }
                }
            } else {
                this.freezePeriodCount = 0;
            }
            VideoCapturerAndroid.this.cameraThreadHandler.postDelayed(this, 2000L);
        }
    };

    /* loaded from: classes2.dex */
    public interface CameraEventsHandler {
        void onCameraClosed();

        void onCameraError(String str);

        void onCameraFreezed(String str);

        void onCameraOpening(int i);

        void onFirstFrameAvailable();
    }

    /* loaded from: classes2.dex */
    public interface CameraSwitchHandler {
        void onCameraSwitchDone(boolean z);

        void onCameraSwitchError(String str);
    }

    /* loaded from: classes2.dex */
    public interface VideoCapturerDataProcessor {
        void onProcessData(byte[] bArr, Camera camera, int i, int i2, int i3);

        void setResolution(int i, int i2);
    }

    public void setCameraDataProcessor(VideoCapturerDataProcessor dataprocessor) {
        if (this.dataprocessor != null) {
            synchronized (this.dataprocessor) {
                this.dataprocessor = dataprocessor;
            }
            return;
        }
        this.dataprocessor = dataprocessor;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public static class CameraStatistics {
        private int frameCount = 0;
        private final ThreadUtils.ThreadChecker threadChecker = new ThreadUtils.ThreadChecker();

        CameraStatistics() {
            this.threadChecker.detachThread();
        }

        public void addFrame() {
            this.threadChecker.checkIsOnValidThread();
            this.frameCount++;
        }

        public int getAndResetFrameCount() {
            this.threadChecker.checkIsOnValidThread();
            int count = this.frameCount;
            this.frameCount = 0;
            return count;
        }
    }

    public static VideoCapturerAndroid create(String name, CameraEventsHandler eventsHandler) {
        return create(name, eventsHandler, null);
    }

    public static VideoCapturerAndroid create(String name, CameraEventsHandler eventsHandler, EglBase.Context sharedEglContext) {
        int cameraId = lookupDeviceName(name);
        if (cameraId == -1) {
            return null;
        }
        return new VideoCapturerAndroid(cameraId, eventsHandler, sharedEglContext);
    }

    public void printStackTrace() {
        if (this.cameraThread != null) {
            StackTraceElement[] cameraStackTraces = this.cameraThread.getStackTrace();
            if (cameraStackTraces.length > 0) {
                Logging.d(TAG, "VideoCapturerAndroid stacks trace:");
                for (StackTraceElement stackTrace : cameraStackTraces) {
                    Logging.d(TAG, stackTrace.toString());
                }
            }
        }
    }

    public void switchCamera(final CameraSwitchHandler handler) {
        if (Camera.getNumberOfCameras() >= 2) {
            synchronized (this.pendingCameraSwitchLock) {
                if (this.pendingCameraSwitch) {
                    Logging.w(TAG, "Ignoring camera switch request.");
                    if (handler != null) {
                        handler.onCameraSwitchError("Pending camera switch already in progress.");
                    }
                    return;
                }
                this.pendingCameraSwitch = true;
                this.cameraThreadHandler.post(new Runnable() { // from class: com.superrtc.call.VideoCapturerAndroid.3
                    @Override // java.lang.Runnable
                    public void run() {
                        boolean z = true;
                        z = false;
                        if (VideoCapturerAndroid.this.camera != null) {
                            VideoCapturerAndroid.this.switchCameraOnCameraThread();
                            synchronized (VideoCapturerAndroid.this.pendingCameraSwitchLock) {
                                VideoCapturerAndroid.this.pendingCameraSwitch = false;
                            }
                            if (handler != null) {
                                CameraSwitchHandler cameraSwitchHandler = handler;
                                if (VideoCapturerAndroid.this.info.facing != 1) {
                                }
                                cameraSwitchHandler.onCameraSwitchDone(z);
                            }
                        } else if (handler != null) {
                            handler.onCameraSwitchError("Camera is stopped.");
                        }
                    }
                });
            }
        } else if (handler != null) {
            handler.onCameraSwitchError("No camera to switch to.");
        }
    }

    public void onOutputFormatRequest(final int width, final int height, final int framerate) {
        this.cameraThreadHandler.post(new Runnable() { // from class: com.superrtc.call.VideoCapturerAndroid.4
            @Override // java.lang.Runnable
            public void run() {
                VideoCapturerAndroid.this.onOutputFormatRequestOnCameraThread(width, height, framerate);
            }
        });
    }

    public void changeCaptureFormat(final int width, final int height, final int framerate) {
        this.cameraThreadHandler.post(new Runnable() { // from class: com.superrtc.call.VideoCapturerAndroid.5
            @Override // java.lang.Runnable
            public void run() {
                VideoCapturerAndroid.this.startPreviewOnCameraThread(width, height, framerate);
            }
        });
    }

    int getCurrentCameraId() {
        int i;
        synchronized (this.cameraIdLock) {
            i = this.id;
        }
        return i;
    }

    @Override // com.superrtc.call.VideoCapturer
    public List<CameraEnumerationAndroid.CaptureFormat> getSupportedFormats() {
        return CameraEnumerationAndroid.getSupportedFormats(getCurrentCameraId());
    }

    public boolean isCapturingToTexture() {
        return this.isCapturingToTexture;
    }

    @Override // com.superrtc.call.VideoCapturer
    public SurfaceTextureHelper getSurfaceTextureHelper() {
        return this.surfaceHelper;
    }

    private VideoCapturerAndroid(int cameraId, CameraEventsHandler eventsHandler, EglBase.Context sharedContext) {
        boolean z = true;
        this.id = cameraId;
        this.eventsHandler = eventsHandler;
        this.isCapturingToTexture = sharedContext == null ? false : z;
        this.cameraStatistics = new CameraStatistics();
        this.surfaceHelper = SurfaceTextureHelper.create(sharedContext);
        this.cameraThreadHandler = this.surfaceHelper.getHandler();
        this.cameraThread = this.cameraThreadHandler.getLooper().getThread();
        Logging.d(TAG, "VideoCapturerAndroid isCapturingToTexture : " + this.isCapturingToTexture);
    }

    private void checkIsOnCameraThread() {
        if (Thread.currentThread() != this.cameraThread) {
            throw new IllegalStateException("Wrong thread");
        }
    }

    private static int lookupDeviceName(String deviceName) {
        Logging.d(TAG, "lookupDeviceName: " + deviceName);
        if (deviceName == null || Camera.getNumberOfCameras() == 0) {
            return -1;
        }
        if (deviceName.isEmpty()) {
            return 0;
        }
        for (int i = 0; i < Camera.getNumberOfCameras(); i++) {
            if (deviceName.equals(CameraEnumerationAndroid.getDeviceName(i))) {
                return i;
            }
        }
        return -1;
    }

    @Override // com.superrtc.call.VideoCapturer
    public void dispose() {
        Logging.d(TAG, "release");
        if (isDisposed()) {
            throw new IllegalStateException("Already released");
        }
        ThreadUtils.invokeUninterruptibly(this.cameraThreadHandler, new Runnable() { // from class: com.superrtc.call.VideoCapturerAndroid.6
            @Override // java.lang.Runnable
            public void run() {
                if (VideoCapturerAndroid.this.camera != null) {
                    throw new IllegalStateException("Release called while camera is running");
                }
            }
        });
        this.surfaceHelper.dispose();
        this.cameraThread = null;
    }

    public boolean isDisposed() {
        return this.cameraThread == null;
    }

    @Override // com.superrtc.call.VideoCapturer
    public boolean getSupportedGetCameraFormats() {
        return this.supportedCamraFormats;
    }

    @Override // com.superrtc.call.VideoCapturer
    public void startCapture(final int width, final int height, final int framerate, final Context applicationContext, final VideoCapturer.CapturerObserver frameObserver) {
        Logging.d(TAG, "startCapture requested: " + width + EMPrivateConstant.EMMultiUserConstant.MUC_ELEMENT_NAME + height + "@" + framerate);
        if (applicationContext == null) {
            throw new RuntimeException("applicationContext not set.");
        } else if (frameObserver == null) {
            throw new RuntimeException("frameObserver not set.");
        } else {
            this.cameraThreadHandler.post(new Runnable() { // from class: com.superrtc.call.VideoCapturerAndroid.7
                @Override // java.lang.Runnable
                public void run() {
                    VideoCapturerAndroid.this.startCaptureOnCameraThread(width, height, framerate, frameObserver, applicationContext);
                }
            });
        }
    }

    public void setEnableCameragetsuppoted(boolean enable) {
        this.supportedCamraFormats = enable;
    }

    public void setEnableCamera(boolean enable) {
        this.enableCamera = enable;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void startCaptureOnCameraThread(final int width, final int height, final int framerate, final VideoCapturer.CapturerObserver frameObserver, final Context applicationContext) {
        checkIsOnCameraThread();
        if (this.camera != null) {
            throw new RuntimeException("Camera has already been started.");
        }
        this.applicationContext = applicationContext;
        this.frameObserver = frameObserver;
        this.firstFrameReported = false;
        this.requestedWidth = width;
        this.requestedHeight = height;
        this.requestedFramerate = framerate;
        if (this.enableCamera) {
            try {
                try {
                    synchronized (this.cameraIdLock) {
                        Logging.d(TAG, "Opening camera " + this.id);
                        if (this.eventsHandler != null) {
                            this.eventsHandler.onCameraOpening(this.id);
                        }
                        this.camera = Camera.open(this.id);
                        this.info = new Camera.CameraInfo();
                        Camera.getCameraInfo(this.id, this.info);
                    }
                    try {
                        this.camera.setPreviewTexture(this.surfaceHelper.getSurfaceTexture());
                        Logging.e(TAG, "Camera orientation: " + this.info.orientation + " .Device orientation: " + getDeviceOrientation());
                        this.camera.setErrorCallback(this.cameraErrorCallback);
                        startPreviewOnCameraThread(width, height, framerate);
                        frameObserver.onCapturerStarted(true);
                        if (this.isCapturingToTexture) {
                            this.surfaceHelper.startListening(this);
                        }
                        this.cameraThreadHandler.postDelayed(this.cameraObserver, 2000L);
                    } catch (IOException e) {
                        Logging.e(TAG, "setPreviewTexture failed", null);
                        throw new RuntimeException(e);
                    }
                } catch (RuntimeException e2) {
                    this.openCameraAttempts++;
                    if (this.openCameraAttempts < 3) {
                        Logging.e(TAG, "Camera.open failed, retrying", e2);
                        this.openCameraOnCodecThreadRunner = new Runnable() { // from class: com.superrtc.call.VideoCapturerAndroid.8
                            @Override // java.lang.Runnable
                            public void run() {
                                VideoCapturerAndroid.this.startCaptureOnCameraThread(width, height, framerate, frameObserver, applicationContext);
                            }
                        };
                        this.cameraThreadHandler.postDelayed(this.openCameraOnCodecThreadRunner, 500L);
                        return;
                    }
                    this.openCameraAttempts = 0;
                    throw e2;
                }
            } catch (RuntimeException e3) {
                Logging.e(TAG, "startCapture failed", e3);
                stopCaptureOnCameraThread();
                frameObserver.onCapturerStarted(false);
                if (this.eventsHandler != null) {
                    this.eventsHandler.onCameraError("Camera can not be started.");
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void startPreviewOnCameraThread(int width, int height, int framerate) {
        checkIsOnCameraThread();
        Logging.d(TAG, "startPreviewOnCameraThread requested: " + width + EMPrivateConstant.EMMultiUserConstant.MUC_ELEMENT_NAME + height + "@" + framerate);
        if (this.camera == null) {
            Logging.e(TAG, "Calling startPreviewOnCameraThread on stopped camera.");
            return;
        }
        this.requestedWidth = width;
        this.requestedHeight = height;
        this.requestedFramerate = framerate;
        Camera.Parameters parameters = this.camera.getParameters();
        int[] range = CameraEnumerationAndroid.getFramerateRange(parameters, framerate * 1000);
        Camera.Size previewSize = CameraEnumerationAndroid.getClosestSupportedSize(parameters.getSupportedPreviewSizes(), width, height);
        CameraEnumerationAndroid.CaptureFormat captureFormat = new CameraEnumerationAndroid.CaptureFormat(previewSize.width, previewSize.height, range[0], range[1]);
        if (!captureFormat.isSameFormat(this.captureFormat)) {
            Logging.d(TAG, "isVideoStabilizationSupported: " + parameters.isVideoStabilizationSupported());
            if (parameters.isVideoStabilizationSupported()) {
                parameters.setVideoStabilization(true);
            }
            if (captureFormat.maxFramerate > 0) {
                parameters.setPreviewFpsRange(captureFormat.minFramerate, captureFormat.maxFramerate);
            }
            parameters.setPreviewSize(captureFormat.width, captureFormat.height);
            if (!this.isCapturingToTexture) {
                captureFormat.getClass();
                parameters.setPreviewFormat(17);
            }
            Camera.Size pictureSize = CameraEnumerationAndroid.getClosestSupportedSize(parameters.getSupportedPictureSizes(), width, height);
            parameters.setPictureSize(pictureSize.width, pictureSize.height);
            if (this.captureFormat != null) {
                this.camera.stopPreview();
                this.dropNextFrame = true;
                this.camera.setPreviewCallbackWithBuffer(null);
            }
            Logging.e(TAG, "Start capturing: " + captureFormat);
            this.captureFormat = captureFormat;
            if (parameters.getSupportedFocusModes().contains("continuous-video")) {
                parameters.setFocusMode("continuous-video");
            }
            this.camera.setParameters(parameters);
            if (!this.isCapturingToTexture) {
                this.queuedBuffers.clear();
                int frameSize = captureFormat.frameSize();
                for (int i = 0; i < 3; i++) {
                    ByteBuffer buffer = ByteBuffer.allocateDirect(frameSize);
                    this.queuedBuffers.add(buffer.array());
                    this.camera.addCallbackBuffer(buffer.array());
                }
                this.camera.setPreviewCallbackWithBuffer(this);
            }
            this.camera.startPreview();
        }
    }

    @Override // com.superrtc.call.VideoCapturer
    public void stopCapture() throws InterruptedException {
        Logging.d(TAG, "stopCapture");
        final CountDownLatch barrier = new CountDownLatch(1);
        this.cameraThreadHandler.post(new Runnable() { // from class: com.superrtc.call.VideoCapturerAndroid.9
            @Override // java.lang.Runnable
            public void run() {
                VideoCapturerAndroid.this.stopCaptureOnCameraThread();
                barrier.countDown();
            }
        });
        barrier.await();
        Logging.d(TAG, "stopCapture done");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void stopCaptureOnCameraThread() {
        checkIsOnCameraThread();
        Logging.d(TAG, "stopCaptureOnCameraThread");
        if (this.openCameraOnCodecThreadRunner != null) {
            this.cameraThreadHandler.removeCallbacks(this.openCameraOnCodecThreadRunner);
        }
        this.openCameraAttempts = 0;
        if (this.camera == null) {
            Logging.e(TAG, "Calling stopCapture() for already stopped camera.");
            return;
        }
        this.surfaceHelper.stopListening();
        this.cameraThreadHandler.removeCallbacks(this.cameraObserver);
        this.cameraStatistics.getAndResetFrameCount();
        Logging.d(TAG, "Stop preview.");
        this.camera.stopPreview();
        this.camera.setPreviewCallbackWithBuffer(null);
        this.queuedBuffers.clear();
        this.captureFormat = null;
        Logging.d(TAG, "Release camera.");
        this.camera.release();
        this.camera = null;
        if (this.eventsHandler != null) {
            this.eventsHandler.onCameraClosed();
        }
    }

    public void enableCameraThread() {
        if (!this.enableCamera) {
            this.enableCamera = true;
            Logging.d(TAG, "enableCameraThread");
            this.cameraThreadHandler.post(new Runnable() { // from class: com.superrtc.call.VideoCapturerAndroid.10
                @Override // java.lang.Runnable
                public void run() {
                    VideoCapturerAndroid.this.startCaptureOnCameraThread(VideoCapturerAndroid.this.requestedWidth, VideoCapturerAndroid.this.requestedHeight, VideoCapturerAndroid.this.requestedFramerate, VideoCapturerAndroid.this.frameObserver, VideoCapturerAndroid.this.applicationContext);
                }
            });
            Logging.d(TAG, "enableCameraThread done");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void switchCameraOnCameraThread() {
        checkIsOnCameraThread();
        Logging.d(TAG, "switchCameraOnCameraThread");
        stopCaptureOnCameraThread();
        synchronized (this.cameraIdLock) {
            this.id = (this.id + 1) % Camera.getNumberOfCameras();
        }
        this.dropNextFrame = true;
        startCaptureOnCameraThread(this.requestedWidth, this.requestedHeight, this.requestedFramerate, this.frameObserver, this.applicationContext);
        Logging.d(TAG, "switchCameraOnCameraThread done");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onOutputFormatRequestOnCameraThread(int width, int height, int framerate) {
        checkIsOnCameraThread();
        if (this.camera == null) {
            Logging.e(TAG, "Calling onOutputFormatRequest() on stopped camera.");
            return;
        }
        Logging.d(TAG, "onOutputFormatRequestOnCameraThread: " + width + EMPrivateConstant.EMMultiUserConstant.MUC_ELEMENT_NAME + height + "@" + framerate);
        this.frameObserver.onOutputFormatRequest(width, height, framerate);
    }

    Handler getCameraThreadHandler() {
        return this.cameraThreadHandler;
    }

    private int getDeviceOrientation() {
        switch (((WindowManager) this.applicationContext.getSystemService("window")).getDefaultDisplay().getRotation()) {
            case 1:
                return 90;
            case 2:
                return 180;
            case 3:
                return 270;
            default:
                return 0;
        }
    }

    private int getFrameOrientation() {
        int rotation = getDeviceOrientation();
        if (this.info.facing == 0) {
            rotation = 360 - rotation;
        }
        return (this.info.orientation + rotation) % a.q;
    }

    @Override // android.hardware.Camera.PreviewCallback
    public void onPreviewFrame(byte[] data, Camera callbackCamera) {
        checkIsOnCameraThread();
        if (this.camera != null && this.queuedBuffers.contains(data)) {
            if (this.camera != callbackCamera) {
                throw new RuntimeException("Unexpected camera in callback!");
            }
            long captureTimeNs = TimeUnit.MILLISECONDS.toNanos(SystemClock.elapsedRealtime());
            if (this.eventsHandler != null && !this.firstFrameReported) {
                this.eventsHandler.onFirstFrameAvailable();
                this.firstFrameReported = true;
            }
            this.cameraStatistics.addFrame();
            int rotation = getFrameOrientation();
            if (this.dataprocessor != null) {
                synchronized (this.dataprocessor) {
                    this.dataprocessor.onProcessData(data, callbackCamera, this.captureFormat.width, this.captureFormat.height, rotation);
                }
            }
            this.frameObserver.onByteBufferFrameCaptured(data, this.captureFormat.width, this.captureFormat.height, rotation, captureTimeNs);
            this.camera.addCallbackBuffer(data);
        }
    }

    @Override // com.superrtc.call.SurfaceTextureHelper.OnTextureFrameAvailableListener
    public void onTextureFrameAvailable(int oesTextureId, float[] transformMatrix, long timestampNs) {
        if (this.camera == null) {
            throw new RuntimeException("onTextureFrameAvailable() called after stopCapture().");
        }
        checkIsOnCameraThread();
        if (this.dropNextFrame) {
            this.surfaceHelper.returnTextureFrame();
            this.dropNextFrame = false;
            return;
        }
        if (this.eventsHandler != null && !this.firstFrameReported) {
            this.eventsHandler.onFirstFrameAvailable();
            this.firstFrameReported = true;
        }
        int rotation = getFrameOrientation();
        if (this.info.facing == 1) {
            transformMatrix = RendererCommon.multiplyMatrices(transformMatrix, RendererCommon.horizontalFlipMatrix());
        }
        this.cameraStatistics.addFrame();
        this.frameObserver.onTextureFrameCaptured(this.captureFormat.width, this.captureFormat.height, oesTextureId, transformMatrix, rotation, timestampNs);
    }
}
