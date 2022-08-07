package com.superrtc.call;

import android.annotation.SuppressLint;
import android.graphics.Point;
import android.graphics.Rect;
import android.opengl.EGL14;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import com.superrtc.call.EglBase;
import com.superrtc.call.EglBase10;
import com.superrtc.call.EglBase14;
import com.superrtc.call.RendererCommon;
import com.superrtc.call.VideoRenderer;
import com.superrtc.sdk.VideoView;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.CountDownLatch;
import javax.microedition.khronos.egl.EGL10;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.egl.EGLContext;
import javax.microedition.khronos.opengles.GL10;

/* loaded from: classes2.dex */
public class VideoRendererGui2 implements GLSurfaceView.Renderer {
    private static final String TAG = "VideoRendererGui2";
    private Thread drawThread;
    private Runnable eglContextReady;
    private boolean onSurfaceCreatedCalled;
    private Thread renderFrameThread;
    private int screenHeight;
    private int screenWidth;
    private VideoView surface;
    private EglBase.Context eglContext = null;
    private boolean renderEnabled = true;
    private final ArrayList<YuvImageRenderer> yuvImageRenderers = new ArrayList<>();

    @SuppressLint({"NewApi"})
    public VideoRendererGui2(VideoView surface, Runnable eglContextReady) {
        this.eglContextReady = null;
        this.surface = surface;
        this.eglContextReady = eglContextReady;
        surface.setPreserveEGLContextOnPause(true);
        surface.setEGLContextClientVersion(2);
        surface.setRenderer(this);
        surface.setRenderMode(0);
    }

    public void setReadyCallback(Runnable eglContextReady) {
        this.eglContextReady = eglContextReady;
    }

    public int getframesReceived() {
        Iterator<YuvImageRenderer> it = this.yuvImageRenderers.iterator();
        if (it.hasNext()) {
            return it.next().framesReceived;
        }
        return 0;
    }

    /* loaded from: classes2.dex */
    public static class YuvImageRenderer implements VideoRenderer.Callbacks {
        private long copyTimeNs;
        private final Rect displayLayout;
        private long drawTimeNs;
        private final RendererCommon.GlDrawer drawer;
        private int framesDropped;
        private int framesReceived;
        private int framesRendered;
        private int id;
        private final Rect layoutInPercentage;
        private float[] layoutMatrix;
        private boolean mirror;
        RendererCommon.ScalingType newscalingtype;
        private VideoRenderer.I420Frame pendingFrame;
        private final Object pendingFrameLock;
        private RendererCommon.RendererEvents rendererEvents;
        private RendererType rendererType;
        private float[] rotatedSamplingMatrix;
        private int rotationDegree;
        private RendererCommon.ScalingType scalingType;
        private int screenHeight;
        private int screenWidth;
        boolean seenFrame;
        private long startTimeNs;
        private VideoView surface;
        private GlTextureFrameBuffer textureCopy;
        private final Object updateLayoutLock;
        private boolean updateLayoutProperties;
        private int videoHeight;
        private int videoWidth;
        private int[] yuvTextures;
        private final RendererCommon.YuvUploader yuvUploader;

        /* loaded from: classes2.dex */
        public enum RendererType {
            RENDERER_YUV,
            RENDERER_TEXTURE
        }

        private YuvImageRenderer(VideoView surface, int id, int x, int y, int width, int height, RendererCommon.ScalingType scalingType, boolean mirror, RendererCommon.GlDrawer drawer) {
            this.yuvTextures = new int[3];
            this.yuvUploader = new RendererCommon.YuvUploader();
            this.pendingFrameLock = new Object();
            this.scalingType = RendererCommon.ScalingType.SCALE_ASPECT_FIT;
            this.startTimeNs = -1L;
            this.displayLayout = new Rect();
            this.updateLayoutLock = new Object();
            Logging.d(VideoRendererGui2.TAG, "YuvImageRenderer.Create id: " + id);
            this.surface = surface;
            this.id = id;
            this.scalingType = surface.getScaleMode() == VideoView.EMCallViewScaleMode.EMCallViewScaleModeAspectFit ? RendererCommon.ScalingType.SCALE_ASPECT_FIT : RendererCommon.ScalingType.SCALE_ASPECT_FILL;
            this.mirror = mirror;
            this.drawer = drawer;
            this.layoutInPercentage = new Rect(x, y, Math.min(100, x + width), Math.min(100, y + height));
            this.updateLayoutProperties = false;
            this.rotationDegree = 0;
        }

        /* synthetic */ YuvImageRenderer(VideoView videoView, int i, int i2, int i3, int i4, int i5, RendererCommon.ScalingType scalingType, boolean z, RendererCommon.GlDrawer glDrawer, YuvImageRenderer yuvImageRenderer) {
            this(videoView, i, i2, i3, i4, i5, scalingType, z, glDrawer);
        }

        public synchronized void reset() {
            this.seenFrame = false;
        }

        public synchronized void release() {
            this.surface = null;
            this.drawer.release();
            synchronized (this.pendingFrameLock) {
                if (this.pendingFrame != null) {
                    VideoRenderer.renderFrameDone(this.pendingFrame);
                    this.pendingFrame = null;
                }
            }
        }

        public void createTextures() {
            Logging.d(VideoRendererGui2.TAG, "  YuvImageRenderer.createTextures " + this.id + " on GL thread:" + Thread.currentThread().getId());
            for (int i = 0; i < 3; i++) {
                this.yuvTextures[i] = GlUtil.generateTexture(3553);
            }
            this.textureCopy = new GlTextureFrameBuffer(6407);
        }

        private void updateLayoutMatrix() {
            float videoAspectRatio;
            synchronized (this.updateLayoutLock) {
                this.newscalingtype = this.surface.getScaleMode() == VideoView.EMCallViewScaleMode.EMCallViewScaleModeAspectFit ? RendererCommon.ScalingType.SCALE_ASPECT_FIT : RendererCommon.ScalingType.SCALE_ASPECT_FILL;
                if (this.newscalingtype != this.scalingType) {
                    this.updateLayoutProperties = true;
                    this.scalingType = this.newscalingtype;
                }
                if (this.updateLayoutProperties) {
                    this.displayLayout.set(((this.screenWidth * this.layoutInPercentage.left) + 99) / 100, ((this.screenHeight * this.layoutInPercentage.top) + 99) / 100, (this.screenWidth * this.layoutInPercentage.right) / 100, (this.screenHeight * this.layoutInPercentage.bottom) / 100);
                    Logging.d(VideoRendererGui2.TAG, "ID: " + this.id + ". AdjustTextureCoords. Allowed display size: " + this.displayLayout.width() + " x " + this.displayLayout.height() + ". Video: " + this.videoWidth + " x " + this.videoHeight + ". Rotation: " + this.rotationDegree + ". Mirror: " + this.mirror);
                    if (this.rotationDegree % 180 == 0) {
                        videoAspectRatio = this.videoWidth / this.videoHeight;
                    } else {
                        videoAspectRatio = this.videoHeight / this.videoWidth;
                    }
                    Point displaySize = RendererCommon.getDisplaySize(this.scalingType, videoAspectRatio, this.displayLayout.width(), this.displayLayout.height());
                    this.displayLayout.inset((this.displayLayout.width() - displaySize.x) / 2, (this.displayLayout.height() - displaySize.y) / 2);
                    Logging.d(VideoRendererGui2.TAG, "  Adjusted display size: " + this.displayLayout.width() + " x " + this.displayLayout.height());
                    this.layoutMatrix = RendererCommon.getLayoutMatrix(this.mirror, videoAspectRatio, this.displayLayout.width() / this.displayLayout.height());
                    this.updateLayoutProperties = false;
                    Logging.d(VideoRendererGui2.TAG, "  AdjustTextureCoords done");
                }
            }
        }

        public void draw() {
            boolean isNewFrame = false;
            if (this.seenFrame) {
                long now = System.nanoTime();
                synchronized (this.pendingFrameLock) {
                    if (this.pendingFrame != null) {
                        isNewFrame = true;
                    }
                    if (isNewFrame && this.startTimeNs == -1) {
                        this.startTimeNs = now;
                    }
                    if (isNewFrame) {
                        this.rotatedSamplingMatrix = RendererCommon.rotateTextureMatrix(this.pendingFrame.samplingMatrix, this.pendingFrame.rotationDegree);
                        if (this.pendingFrame.yuvFrame) {
                            this.rendererType = RendererType.RENDERER_YUV;
                            this.yuvUploader.uploadYuvData(this.yuvTextures, this.pendingFrame.width, this.pendingFrame.height, this.pendingFrame.yuvStrides, this.pendingFrame.yuvPlanes);
                        } else {
                            this.rendererType = RendererType.RENDERER_TEXTURE;
                            this.textureCopy.setSize(this.pendingFrame.rotatedWidth(), this.pendingFrame.rotatedHeight());
                            GLES20.glBindFramebuffer(36160, this.textureCopy.getFrameBufferId());
                            GlUtil.checkNoGLES2Error("glBindFramebuffer");
                            this.drawer.drawOes(this.pendingFrame.textureId, this.rotatedSamplingMatrix, 0, 0, this.textureCopy.getWidth(), this.textureCopy.getHeight());
                            this.rotatedSamplingMatrix = RendererCommon.identityMatrix();
                            GLES20.glBindFramebuffer(36160, 0);
                            GLES20.glFinish();
                        }
                        this.copyTimeNs += System.nanoTime() - now;
                        VideoRenderer.renderFrameDone(this.pendingFrame);
                        this.pendingFrame = null;
                    }
                }
                updateLayoutMatrix();
                float[] texMatrix = RendererCommon.multiplyMatrices(this.rotatedSamplingMatrix, this.layoutMatrix);
                int viewportY = this.screenHeight - this.displayLayout.bottom;
                if (this.rendererType == RendererType.RENDERER_YUV) {
                    this.drawer.drawYuv(this.yuvTextures, texMatrix, this.displayLayout.left, viewportY, this.displayLayout.width(), this.displayLayout.height());
                } else {
                    this.drawer.drawRgb(this.textureCopy.getTextureId(), texMatrix, this.displayLayout.left, viewportY, this.displayLayout.width(), this.displayLayout.height());
                }
                if (isNewFrame) {
                    this.framesRendered++;
                    this.drawTimeNs += System.nanoTime() - now;
                    if (this.framesRendered % 300 == 0) {
                        logStatistics();
                    }
                }
            }
        }

        private void logStatistics() {
            long timeSinceFirstFrameNs = System.nanoTime() - this.startTimeNs;
            Logging.d(VideoRendererGui2.TAG, "ID: " + this.id + ". Type: " + this.rendererType + ". Frames received: " + this.framesReceived + ". Dropped: " + this.framesDropped + ". Rendered: " + this.framesRendered);
            if (this.framesReceived > 0 && this.framesRendered > 0) {
                Logging.d(VideoRendererGui2.TAG, "Duration: " + ((int) (timeSinceFirstFrameNs / 1000000.0d)) + " ms. FPS: " + ((this.framesRendered * 1.0E9d) / timeSinceFirstFrameNs));
                Logging.d(VideoRendererGui2.TAG, "Draw time: " + ((int) (this.drawTimeNs / (this.framesRendered * 1000))) + " us. Copy time: " + ((int) (this.copyTimeNs / (this.framesReceived * 1000))) + " us");
            }
        }

        public void setScreenSize(int screenWidth, int screenHeight) {
            synchronized (this.updateLayoutLock) {
                if (screenWidth != this.screenWidth || screenHeight != this.screenHeight) {
                    Logging.d(VideoRendererGui2.TAG, "ID: " + this.id + ". YuvImageRenderer.setScreenSize: " + screenWidth + " x " + screenHeight);
                    this.screenWidth = screenWidth;
                    this.screenHeight = screenHeight;
                    this.updateLayoutProperties = true;
                }
            }
        }

        public void setPosition(int x, int y, int width, int height, RendererCommon.ScalingType scalingType, boolean mirror) {
            Rect layoutInPercentage = new Rect(x, y, Math.min(100, x + width), Math.min(100, y + height));
            synchronized (this.updateLayoutLock) {
                if (!layoutInPercentage.equals(this.layoutInPercentage) || scalingType != this.scalingType || mirror != this.mirror) {
                    Logging.d(VideoRendererGui2.TAG, "ID: " + this.id + ". YuvImageRenderer.setPosition: (" + x + ", " + y + ") " + width + " x " + height + ". Scaling: " + scalingType + ". Mirror: " + mirror);
                    this.layoutInPercentage.set(layoutInPercentage);
                    this.mirror = mirror;
                    this.updateLayoutProperties = true;
                }
            }
        }

        private void setSize(int videoWidth, int videoHeight, int rotation) {
            if (videoWidth != this.videoWidth || videoHeight != this.videoHeight || rotation != this.rotationDegree) {
                if (this.rendererEvents != null) {
                    Logging.d(VideoRendererGui2.TAG, "ID: " + this.id + ". Reporting frame resolution changed to " + videoWidth + " x " + videoHeight);
                    this.rendererEvents.onFrameResolutionChanged(videoWidth, videoHeight, rotation);
                }
                synchronized (this.updateLayoutLock) {
                    Logging.d(VideoRendererGui2.TAG, "ID: " + this.id + ". YuvImageRenderer.setSize: " + videoWidth + " x " + videoHeight + " rotation " + rotation);
                    this.videoWidth = videoWidth;
                    this.videoHeight = videoHeight;
                    this.rotationDegree = rotation;
                    this.updateLayoutProperties = true;
                    Logging.d(VideoRendererGui2.TAG, "  YuvImageRenderer.setSize done.");
                }
            }
        }

        @Override // com.superrtc.call.VideoRenderer.Callbacks
        public synchronized void renderFrame(VideoRenderer.I420Frame frame) {
            if (this.surface == null) {
                VideoRenderer.renderFrameDone(frame);
            } else {
                if (!this.seenFrame && this.rendererEvents != null) {
                    Logging.d(VideoRendererGui2.TAG, "ID: " + this.id + ". Reporting first rendered frame.");
                    this.rendererEvents.onFirstFrameRendered();
                }
                this.framesReceived++;
                synchronized (this.pendingFrameLock) {
                    if (frame.yuvFrame && (frame.yuvStrides[0] < frame.width || frame.yuvStrides[1] < frame.width / 2 || frame.yuvStrides[2] < frame.width / 2)) {
                        Logging.e(VideoRendererGui2.TAG, "Incorrect strides " + frame.yuvStrides[0] + ", " + frame.yuvStrides[1] + ", " + frame.yuvStrides[2]);
                        VideoRenderer.renderFrameDone(frame);
                    } else if (this.pendingFrame != null) {
                        this.framesDropped++;
                        VideoRenderer.renderFrameDone(frame);
                        this.seenFrame = true;
                    } else {
                        this.pendingFrame = frame;
                        setSize(frame.width, frame.height, frame.rotationDegree);
                        this.seenFrame = true;
                        this.surface.requestRender();
                    }
                }
            }
        }
    }

    public synchronized EglBase.Context getEglBaseContext() {
        return this.eglContext;
    }

    public synchronized void dispose() {
        if (this != null) {
            Logging.d(TAG, "VideoRendererGui.dispose");
            synchronized (this.yuvImageRenderers) {
                Iterator<YuvImageRenderer> it = this.yuvImageRenderers.iterator();
                while (it.hasNext()) {
                    it.next().release();
                }
                this.yuvImageRenderers.clear();
            }
            this.renderFrameThread = null;
            this.drawThread = null;
            this.surface = null;
            this.eglContext = null;
            this.eglContextReady = null;
        }
    }

    public VideoRenderer createGui(int x, int y, int width, int height, RendererCommon.ScalingType scalingType, boolean mirror) throws Exception {
        return new VideoRenderer(create(x, y, width, height, scalingType, mirror));
    }

    public VideoRenderer.Callbacks createGuiRenderer(int x, int y, int width, int height, RendererCommon.ScalingType scalingType, boolean mirror) {
        return create(x, y, width, height, scalingType, mirror);
    }

    public synchronized YuvImageRenderer create(int x, int y, int width, int height, RendererCommon.ScalingType scalingType, boolean mirror) {
        return create(x, y, width, height, scalingType, mirror, new GlRectDrawer());
    }

    public synchronized YuvImageRenderer create(int x, int y, int width, int height, RendererCommon.ScalingType scalingType, boolean mirror, RendererCommon.GlDrawer drawer) {
        final YuvImageRenderer yuvImageRenderer;
        if (x < 0 || x > 100 || y < 0 || y > 100 || width < 0 || width > 100 || height < 0 || height > 100 || x + width > 100 || y + height > 100) {
            throw new RuntimeException("Incorrect window parameters.");
        } else if (this == null) {
            throw new RuntimeException("Attempt to create yuv renderer before setting GLSurfaceView");
        } else {
            yuvImageRenderer = new YuvImageRenderer(this.surface, this.yuvImageRenderers.size(), x, y, width, height, scalingType, mirror, drawer, null);
            if (this.onSurfaceCreatedCalled) {
                final CountDownLatch countDownLatch = new CountDownLatch(1);
                this.surface.queueEvent(new Runnable() { // from class: com.superrtc.call.VideoRendererGui2.1
                    @Override // java.lang.Runnable
                    public void run() {
                        yuvImageRenderer.createTextures();
                        yuvImageRenderer.setScreenSize(instance.screenWidth, instance.screenHeight);
                        countDownLatch.countDown();
                    }
                });
                try {
                    countDownLatch.await();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            synchronized (this.yuvImageRenderers) {
                this.yuvImageRenderers.add(yuvImageRenderer);
            }
        }
        return yuvImageRenderer;
    }

    public synchronized YuvImageRenderer createRenderer(int x, int y, int width, int height, RendererCommon.ScalingType scalingType, boolean mirror, RendererCommon.GlDrawer drawer) {
        final YuvImageRenderer yuvImageRenderer;
        if (x < 0 || x > 100 || y < 0 || y > 100 || width < 0 || width > 100 || height < 0 || height > 100 || x + width > 100 || y + height > 100) {
            throw new RuntimeException("Incorrect window parameters.");
        }
        yuvImageRenderer = new YuvImageRenderer(this.surface, this.yuvImageRenderers.size(), x, y, width, height, scalingType, mirror, drawer, null);
        synchronized (this.yuvImageRenderers) {
            if (this.onSurfaceCreatedCalled) {
                final CountDownLatch countDownLatch = new CountDownLatch(1);
                this.surface.queueEvent(new Runnable() { // from class: com.superrtc.call.VideoRendererGui2.2
                    @Override // java.lang.Runnable
                    public void run() {
                        yuvImageRenderer.createTextures();
                        yuvImageRenderer.setScreenSize(inst.screenWidth, inst.screenHeight);
                        countDownLatch.countDown();
                    }
                });
                try {
                    countDownLatch.await();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            this.yuvImageRenderers.add(yuvImageRenderer);
        }
        return yuvImageRenderer;
    }

    public synchronized void update(VideoRenderer.Callbacks renderer, int x, int y, int width, int height, RendererCommon.ScalingType scalingType, boolean mirror) {
        Logging.d(TAG, "VideoRendererGui.update");
        if (this == null) {
            throw new RuntimeException("Attempt to update yuv renderer before setting GLSurfaceView");
        }
        synchronized (this.yuvImageRenderers) {
            Iterator<YuvImageRenderer> it = this.yuvImageRenderers.iterator();
            while (it.hasNext()) {
                YuvImageRenderer yuvImageRenderer = it.next();
                if (yuvImageRenderer == renderer) {
                    yuvImageRenderer.setPosition(x, y, width, height, scalingType, mirror);
                }
            }
        }
    }

    public synchronized void setRendererEvents(VideoRenderer.Callbacks renderer, RendererCommon.RendererEvents rendererEvents) {
        Logging.d(TAG, "VideoRendererGui.setRendererEvents");
        if (this == null) {
            throw new RuntimeException("Attempt to set renderer events before setting GLSurfaceView");
        }
        synchronized (this.yuvImageRenderers) {
            Iterator<YuvImageRenderer> it = this.yuvImageRenderers.iterator();
            while (it.hasNext()) {
                YuvImageRenderer yuvImageRenderer = it.next();
                if (yuvImageRenderer == renderer) {
                    yuvImageRenderer.rendererEvents = rendererEvents;
                }
            }
        }
    }

    public synchronized void remove(VideoRenderer.Callbacks renderer) {
        Logging.d(TAG, "VideoRendererGui.remove");
        if (this == null) {
            throw new RuntimeException("Attempt to remove renderer before setting GLSurfaceView");
        }
        synchronized (this.yuvImageRenderers) {
            int index = this.yuvImageRenderers.indexOf(renderer);
            if (index == -1) {
                Logging.w(TAG, "Couldn't remove renderer (not present in current list)");
            } else {
                this.yuvImageRenderers.remove(index).release();
            }
        }
    }

    public synchronized void reset(VideoRenderer.Callbacks renderer) {
        Logging.d(TAG, "VideoRendererGui.reset");
        if (this == null) {
            throw new RuntimeException("Attempt to reset renderer before setting GLSurfaceView");
        }
        synchronized (this.yuvImageRenderers) {
            Iterator<YuvImageRenderer> it = this.yuvImageRenderers.iterator();
            while (it.hasNext()) {
                YuvImageRenderer yuvImageRenderer = it.next();
                if (yuvImageRenderer == renderer) {
                    yuvImageRenderer.reset();
                }
            }
        }
    }

    private static void printStackTrace(Thread thread, String threadName) {
        if (thread != null) {
            StackTraceElement[] stackTraces = thread.getStackTrace();
            if (stackTraces.length > 0) {
                Logging.d(TAG, String.valueOf(threadName) + " stacks trace:");
                for (StackTraceElement stackTrace : stackTraces) {
                    Logging.d(TAG, stackTrace.toString());
                }
            }
        }
    }

    public synchronized void printStackTraces() {
        printStackTrace(this.renderFrameThread, "Render frame thread");
        printStackTrace(this.drawThread, "Draw thread");
    }

    @Override // android.opengl.GLSurfaceView.Renderer
    @SuppressLint({"NewApi"})
    public void onSurfaceCreated(GL10 unused, EGLConfig config) {
        Logging.d(TAG, "VideoRendererGui.onSurfaceCreated");
        synchronized (VideoRendererGui.class) {
            if (EglBase14.isEGL14Supported()) {
                this.eglContext = new EglBase14.Context(EGL14.eglGetCurrentContext());
            } else {
                this.eglContext = new EglBase10.Context(((EGL10) EGLContext.getEGL()).eglGetCurrentContext());
            }
            Logging.d(TAG, "VideoRendererGui EGL Context: " + this.eglContext);
        }
        synchronized (this.yuvImageRenderers) {
            Iterator<YuvImageRenderer> it = this.yuvImageRenderers.iterator();
            while (it.hasNext()) {
                it.next().createTextures();
            }
            this.onSurfaceCreatedCalled = true;
        }
        GlUtil.checkNoGLES2Error("onSurfaceCreated done");
        GLES20.glPixelStorei(3317, 1);
        GLES20.glClearColor(0.15f, 0.15f, 0.15f, 1.0f);
        synchronized (VideoRendererGui2.class) {
            if (this.eglContextReady != null) {
                this.eglContextReady.run();
            }
        }
    }

    @Override // android.opengl.GLSurfaceView.Renderer
    public void onSurfaceChanged(GL10 unused, int width, int height) {
        Logging.d(TAG, "VideoRendererGui.onSurfaceChanged: " + width + " x " + height + "  ");
        this.screenWidth = width;
        this.screenHeight = height;
        synchronized (this.yuvImageRenderers) {
            Iterator<YuvImageRenderer> it = this.yuvImageRenderers.iterator();
            while (it.hasNext()) {
                it.next().setScreenSize(this.screenWidth, this.screenHeight);
            }
        }
    }

    @Override // android.opengl.GLSurfaceView.Renderer
    public void onDrawFrame(GL10 unused) {
        synchronized (this.yuvImageRenderers) {
            if (this.onSurfaceCreatedCalled) {
                if (this.drawThread == null) {
                    this.drawThread = Thread.currentThread();
                }
                GLES20.glViewport(0, 0, this.screenWidth, this.screenHeight);
                GLES20.glClear(16384);
                synchronized (this.yuvImageRenderers) {
                    if (this.renderEnabled) {
                        Iterator<YuvImageRenderer> it = this.yuvImageRenderers.iterator();
                        while (it.hasNext()) {
                            it.next().draw();
                        }
                    }
                }
            }
        }
    }

    public void setRenderEnable(boolean enabled) {
        synchronized (this.yuvImageRenderers) {
            this.renderEnabled = enabled;
        }
    }

    public boolean getRenderEnable() {
        boolean z;
        synchronized (this.yuvImageRenderers) {
            z = this.renderEnabled;
        }
        return z;
    }
}
