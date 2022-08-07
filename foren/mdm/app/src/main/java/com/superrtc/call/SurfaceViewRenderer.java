package com.superrtc.call;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Point;
import android.opengl.GLES20;
import android.os.Handler;
import android.os.HandlerThread;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import com.hyphenate.util.EMPrivateConstant;
import com.superrtc.call.EglBase;
import com.superrtc.call.RendererCommon;
import com.superrtc.call.VideoRenderer;
import java.util.concurrent.CountDownLatch;

/* loaded from: classes2.dex */
public class SurfaceViewRenderer extends SurfaceView implements SurfaceHolder.Callback, VideoRenderer.Callbacks {
    private static final String TAG = "SurfaceViewRenderer";
    private RendererCommon.GlDrawer drawer;
    private EglBase eglBase;
    private long firstFrameTimeNs;
    private int frameHeight;
    private int frameRotation;
    private int frameWidth;
    private int framesDropped;
    private int framesReceived;
    private int framesRendered;
    private boolean isSurfaceCreated;
    private boolean mirror;
    private VideoRenderer.I420Frame pendingFrame;
    private HandlerThread renderThread;
    private Handler renderThreadHandler;
    private long renderTimeNs;
    private RendererCommon.RendererEvents rendererEvents;
    private final Object handlerLock = new Object();
    private final RendererCommon.YuvUploader yuvUploader = new RendererCommon.YuvUploader();
    private int[] yuvTextures = null;
    private final Object frameLock = new Object();
    private final Object layoutLock = new Object();
    private Point desiredLayoutSize = new Point();
    private final Point layoutSize = new Point();
    private final Point surfaceSize = new Point();
    private RendererCommon.ScalingType scalingType = RendererCommon.ScalingType.SCALE_ASPECT_BALANCED;
    private final Object statisticsLock = new Object();
    private final Runnable renderFrameRunnable = new Runnable() { // from class: com.superrtc.call.SurfaceViewRenderer.1
        @Override // java.lang.Runnable
        public void run() {
            SurfaceViewRenderer.this.renderFrameOnRenderThread();
        }
    };
    private final Runnable makeBlackRunnable = new Runnable() { // from class: com.superrtc.call.SurfaceViewRenderer.2
        @Override // java.lang.Runnable
        public void run() {
            SurfaceViewRenderer.this.makeBlack();
        }
    };

    public SurfaceViewRenderer(Context context) {
        super(context);
        getHolder().addCallback(this);
    }

    public SurfaceViewRenderer(Context context, AttributeSet attrs) {
        super(context, attrs);
        getHolder().addCallback(this);
    }

    public void init(EglBase.Context sharedContext, RendererCommon.RendererEvents rendererEvents) {
        init(sharedContext, rendererEvents, EglBase.CONFIG_PLAIN, new GlRectDrawer());
    }

    public void init(EglBase.Context sharedContext, RendererCommon.RendererEvents rendererEvents, int[] configAttributes, RendererCommon.GlDrawer drawer) {
        synchronized (this.handlerLock) {
            if (this.renderThreadHandler != null) {
                throw new IllegalStateException(String.valueOf(getResourceName()) + "Already initialized");
            }
            Logging.d(TAG, String.valueOf(getResourceName()) + "Initializing.");
            this.rendererEvents = rendererEvents;
            this.drawer = drawer;
            this.renderThread = new HandlerThread(TAG);
            this.renderThread.start();
            this.eglBase = EglBase.create(sharedContext, configAttributes);
            this.renderThreadHandler = new Handler(this.renderThread.getLooper());
        }
        tryCreateEglSurface();
    }

    public void tryCreateEglSurface() {
        runOnRenderThread(new Runnable() { // from class: com.superrtc.call.SurfaceViewRenderer.3
            @Override // java.lang.Runnable
            public void run() {
                synchronized (SurfaceViewRenderer.this.layoutLock) {
                    if (SurfaceViewRenderer.this.isSurfaceCreated && !SurfaceViewRenderer.this.eglBase.hasSurface()) {
                        SurfaceViewRenderer.this.eglBase.createSurface(SurfaceViewRenderer.this.getHolder().getSurface());
                        SurfaceViewRenderer.this.eglBase.makeCurrent();
                        GLES20.glPixelStorei(3317, 1);
                    }
                }
            }
        });
    }

    public void release() {
        final CountDownLatch eglCleanupBarrier = new CountDownLatch(1);
        synchronized (this.handlerLock) {
            if (this.renderThreadHandler == null) {
                Logging.d(TAG, String.valueOf(getResourceName()) + "Already released");
                return;
            }
            this.renderThreadHandler.postAtFrontOfQueue(new Runnable() { // from class: com.superrtc.call.SurfaceViewRenderer.4
                @Override // java.lang.Runnable
                public void run() {
                    SurfaceViewRenderer.this.drawer.release();
                    SurfaceViewRenderer.this.drawer = null;
                    if (SurfaceViewRenderer.this.yuvTextures != null) {
                        GLES20.glDeleteTextures(3, SurfaceViewRenderer.this.yuvTextures, 0);
                        SurfaceViewRenderer.this.yuvTextures = null;
                    }
                    SurfaceViewRenderer.this.makeBlack();
                    SurfaceViewRenderer.this.eglBase.release();
                    SurfaceViewRenderer.this.eglBase = null;
                    eglCleanupBarrier.countDown();
                }
            });
            this.renderThreadHandler = null;
            ThreadUtils.awaitUninterruptibly(eglCleanupBarrier);
            this.renderThread.quit();
            synchronized (this.frameLock) {
                if (this.pendingFrame != null) {
                    VideoRenderer.renderFrameDone(this.pendingFrame);
                    this.pendingFrame = null;
                }
            }
            ThreadUtils.joinUninterruptibly(this.renderThread);
            this.renderThread = null;
            synchronized (this.layoutLock) {
                this.frameWidth = 0;
                this.frameHeight = 0;
                this.frameRotation = 0;
                this.rendererEvents = null;
            }
            resetStatistics();
        }
    }

    public void resetStatistics() {
        synchronized (this.statisticsLock) {
            this.framesReceived = 0;
            this.framesDropped = 0;
            this.framesRendered = 0;
            this.firstFrameTimeNs = 0L;
            this.renderTimeNs = 0L;
        }
    }

    public void setMirror(boolean mirror) {
        synchronized (this.layoutLock) {
            this.mirror = mirror;
        }
    }

    public void setScalingType(RendererCommon.ScalingType scalingType) {
        synchronized (this.layoutLock) {
            this.scalingType = scalingType;
        }
    }

    @Override // com.superrtc.call.VideoRenderer.Callbacks
    public void renderFrame(VideoRenderer.I420Frame frame) {
        synchronized (this.statisticsLock) {
            this.framesReceived++;
        }
        synchronized (this.handlerLock) {
            if (this.renderThreadHandler == null) {
                Logging.d(TAG, String.valueOf(getResourceName()) + "Dropping frame - Not initialized or already released.");
                VideoRenderer.renderFrameDone(frame);
                return;
            }
            synchronized (this.frameLock) {
                if (this.pendingFrame != null) {
                    synchronized (this.statisticsLock) {
                        this.framesDropped++;
                    }
                    VideoRenderer.renderFrameDone(this.pendingFrame);
                }
                this.pendingFrame = frame;
                updateFrameDimensionsAndReportEvents(frame);
                this.renderThreadHandler.post(this.renderFrameRunnable);
            }
        }
    }

    private Point getDesiredLayoutSize(int widthSpec, int heightSpec) {
        Point size;
        synchronized (this.layoutLock) {
            int maxWidth = getDefaultSize(Integer.MAX_VALUE, widthSpec);
            int maxHeight = getDefaultSize(Integer.MAX_VALUE, heightSpec);
            size = RendererCommon.getDisplaySize(this.scalingType, frameAspectRatio(), maxWidth, maxHeight);
            if (View.MeasureSpec.getMode(widthSpec) == 1073741824) {
                size.x = maxWidth;
            }
            if (View.MeasureSpec.getMode(heightSpec) == 1073741824) {
                size.y = maxHeight;
            }
        }
        return size;
    }

    @Override // android.view.SurfaceView, android.view.View
    protected void onMeasure(int widthSpec, int heightSpec) {
        synchronized (this.layoutLock) {
            if (this.frameWidth == 0 || this.frameHeight == 0) {
                super.onMeasure(widthSpec, heightSpec);
                return;
            }
            this.desiredLayoutSize = getDesiredLayoutSize(widthSpec, heightSpec);
            if (!(this.desiredLayoutSize.x == getMeasuredWidth() && this.desiredLayoutSize.y == getMeasuredHeight())) {
                synchronized (this.handlerLock) {
                    if (this.renderThreadHandler != null) {
                        this.renderThreadHandler.postAtFrontOfQueue(this.makeBlackRunnable);
                    }
                }
            }
            setMeasuredDimension(this.desiredLayoutSize.x, this.desiredLayoutSize.y);
        }
    }

    @Override // android.view.View
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        synchronized (this.layoutLock) {
            this.layoutSize.x = right - left;
            this.layoutSize.y = bottom - top;
        }
        runOnRenderThread(this.renderFrameRunnable);
    }

    @Override // android.view.SurfaceHolder.Callback
    public void surfaceCreated(SurfaceHolder holder) {
        Logging.d(TAG, String.valueOf(getResourceName()) + "Surface created.");
        synchronized (this.layoutLock) {
            this.isSurfaceCreated = true;
        }
        tryCreateEglSurface();
    }

    @Override // android.view.SurfaceHolder.Callback
    public void surfaceDestroyed(SurfaceHolder holder) {
        Logging.d(TAG, String.valueOf(getResourceName()) + "Surface destroyed.");
        synchronized (this.layoutLock) {
            this.isSurfaceCreated = false;
            this.surfaceSize.x = 0;
            this.surfaceSize.y = 0;
        }
        runOnRenderThread(new Runnable() { // from class: com.superrtc.call.SurfaceViewRenderer.5
            @Override // java.lang.Runnable
            public void run() {
                SurfaceViewRenderer.this.eglBase.releaseSurface();
            }
        });
    }

    @Override // android.view.SurfaceHolder.Callback
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        Logging.d(TAG, String.valueOf(getResourceName()) + "Surface changed: " + width + EMPrivateConstant.EMMultiUserConstant.MUC_ELEMENT_NAME + height);
        synchronized (this.layoutLock) {
            this.surfaceSize.x = width;
            this.surfaceSize.y = height;
        }
        runOnRenderThread(this.renderFrameRunnable);
    }

    private void runOnRenderThread(Runnable runnable) {
        synchronized (this.handlerLock) {
            if (this.renderThreadHandler != null) {
                this.renderThreadHandler.post(runnable);
            }
        }
    }

    private String getResourceName() {
        try {
            return String.valueOf(getResources().getResourceEntryName(getId())) + ": ";
        } catch (Resources.NotFoundException e) {
            return "";
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void makeBlack() {
        if (Thread.currentThread() != this.renderThread) {
            throw new IllegalStateException(String.valueOf(getResourceName()) + "Wrong thread.");
        } else if (this.eglBase != null && this.eglBase.hasSurface()) {
            GLES20.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
            GLES20.glClear(16384);
            this.eglBase.swapBuffers();
        }
    }

    private boolean checkConsistentLayout() {
        boolean z;
        if (Thread.currentThread() != this.renderThread) {
            throw new IllegalStateException(String.valueOf(getResourceName()) + "Wrong thread.");
        }
        synchronized (this.layoutLock) {
            z = this.layoutSize.equals(this.desiredLayoutSize) && this.surfaceSize.equals(this.layoutSize);
        }
        return z;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void renderFrameOnRenderThread() {
        float[] texMatrix;
        if (Thread.currentThread() != this.renderThread) {
            throw new IllegalStateException(String.valueOf(getResourceName()) + "Wrong thread.");
        }
        synchronized (this.frameLock) {
            if (this.pendingFrame != null) {
                VideoRenderer.I420Frame frame = this.pendingFrame;
                this.pendingFrame = null;
                if (this.eglBase == null || !this.eglBase.hasSurface()) {
                    Logging.d(TAG, String.valueOf(getResourceName()) + "No surface to draw on");
                    VideoRenderer.renderFrameDone(frame);
                } else if (!checkConsistentLayout()) {
                    makeBlack();
                    VideoRenderer.renderFrameDone(frame);
                } else {
                    synchronized (this.layoutLock) {
                        if (!(this.eglBase.surfaceWidth() == this.surfaceSize.x && this.eglBase.surfaceHeight() == this.surfaceSize.y)) {
                            makeBlack();
                        }
                    }
                    long startTimeNs = System.nanoTime();
                    synchronized (this.layoutLock) {
                        texMatrix = RendererCommon.multiplyMatrices(RendererCommon.rotateTextureMatrix(frame.samplingMatrix, frame.rotationDegree), RendererCommon.getLayoutMatrix(this.mirror, frameAspectRatio(), this.layoutSize.x / this.layoutSize.y));
                    }
                    GLES20.glClear(16384);
                    if (frame.yuvFrame) {
                        if (this.yuvTextures == null) {
                            this.yuvTextures = new int[3];
                            for (int i = 0; i < 3; i++) {
                                this.yuvTextures[i] = GlUtil.generateTexture(3553);
                            }
                        }
                        this.yuvUploader.uploadYuvData(this.yuvTextures, frame.width, frame.height, frame.yuvStrides, frame.yuvPlanes);
                        this.drawer.drawYuv(this.yuvTextures, texMatrix, 0, 0, this.surfaceSize.x, this.surfaceSize.y);
                    } else {
                        this.drawer.drawOes(frame.textureId, texMatrix, 0, 0, this.surfaceSize.x, this.surfaceSize.y);
                    }
                    this.eglBase.swapBuffers();
                    VideoRenderer.renderFrameDone(frame);
                    synchronized (this.statisticsLock) {
                        if (this.framesRendered == 0) {
                            this.firstFrameTimeNs = startTimeNs;
                            synchronized (this.layoutLock) {
                                Logging.d(TAG, String.valueOf(getResourceName()) + "Reporting first rendered frame.");
                                if (this.rendererEvents != null) {
                                    this.rendererEvents.onFirstFrameRendered();
                                }
                            }
                        }
                        this.framesRendered++;
                        this.renderTimeNs += System.nanoTime() - startTimeNs;
                        if (this.framesRendered % 300 == 0) {
                            logStatistics();
                        }
                    }
                }
            }
        }
    }

    private float frameAspectRatio() {
        float f;
        synchronized (this.layoutLock) {
            if (this.frameWidth == 0 || this.frameHeight == 0) {
                return 0.0f;
            }
            if (this.frameRotation % 180 == 0) {
                f = this.frameWidth / this.frameHeight;
            } else {
                f = this.frameHeight / this.frameWidth;
            }
            return f;
        }
    }

    private void updateFrameDimensionsAndReportEvents(VideoRenderer.I420Frame frame) {
        synchronized (this.layoutLock) {
            if (!(this.frameWidth == frame.width && this.frameHeight == frame.height && this.frameRotation == frame.rotationDegree)) {
                Logging.d(TAG, String.valueOf(getResourceName()) + "Reporting frame resolution changed to " + frame.width + EMPrivateConstant.EMMultiUserConstant.MUC_ELEMENT_NAME + frame.height + " with rotation " + frame.rotationDegree);
                if (this.rendererEvents != null) {
                    this.rendererEvents.onFrameResolutionChanged(frame.width, frame.height, frame.rotationDegree);
                }
                this.frameWidth = frame.width;
                this.frameHeight = frame.height;
                this.frameRotation = frame.rotationDegree;
                post(new Runnable() { // from class: com.superrtc.call.SurfaceViewRenderer.6
                    @Override // java.lang.Runnable
                    public void run() {
                        SurfaceViewRenderer.this.requestLayout();
                    }
                });
            }
        }
    }

    private void logStatistics() {
        synchronized (this.statisticsLock) {
            Logging.d(TAG, String.valueOf(getResourceName()) + "Frames received: " + this.framesReceived + ". Dropped: " + this.framesDropped + ". Rendered: " + this.framesRendered);
            if (this.framesReceived > 0 && this.framesRendered > 0) {
                long timeSinceFirstFrameNs = System.nanoTime() - this.firstFrameTimeNs;
                Logging.d(TAG, String.valueOf(getResourceName()) + "Duration: " + ((int) (timeSinceFirstFrameNs / 1000000.0d)) + " ms. FPS: " + ((this.framesRendered * 1.0E9d) / timeSinceFirstFrameNs));
                Logging.d(TAG, String.valueOf(getResourceName()) + "Average render time: " + ((int) (this.renderTimeNs / (this.framesRendered * 1000))) + " us.");
            }
        }
    }
}
