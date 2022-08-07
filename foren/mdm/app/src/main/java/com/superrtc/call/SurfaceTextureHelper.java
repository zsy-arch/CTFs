package com.superrtc.call;

import android.graphics.SurfaceTexture;
import android.opengl.GLES20;
import android.os.Build;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.SystemClock;
import com.superrtc.call.EglBase;
import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public class SurfaceTextureHelper {
    private static final String TAG = "SurfaceTextureHelper";
    private final EglBase eglBase;
    private final Handler handler;
    private boolean hasPendingTexture;
    private boolean isQuitting;
    private volatile boolean isTextureInUse;
    private OnTextureFrameAvailableListener listener;
    private final int oesTextureId;
    private final SurfaceTexture surfaceTexture;
    private YuvConverter yuvConverter;

    /* loaded from: classes2.dex */
    public interface OnTextureFrameAvailableListener {
        void onTextureFrameAvailable(int i, float[] fArr, long j);
    }

    public static SurfaceTextureHelper create(final EglBase.Context sharedContext) {
        HandlerThread thread = new HandlerThread(TAG);
        thread.start();
        final Handler handler = new Handler(thread.getLooper());
        return (SurfaceTextureHelper) ThreadUtils.invokeUninterruptibly(handler, new Callable<SurfaceTextureHelper>() { // from class: com.superrtc.call.SurfaceTextureHelper.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // java.util.concurrent.Callable
            public SurfaceTextureHelper call() {
                return new SurfaceTextureHelper(EglBase.Context.this, handler, null);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public static class YuvConverter {
        private static final String FRAGMENT_SHADER = "#extension GL_OES_EGL_image_external : require\nprecision mediump float;\nvarying vec2 interp_tc;\n\nuniform samplerExternalOES oesTex;\nuniform vec2 xUnit;\nuniform vec4 coeffs;\n\nvoid main() {\n  gl_FragColor.r = coeffs.a + dot(coeffs.rgb,\n      texture2D(oesTex, interp_tc - 1.5 * xUnit).rgb);\n  gl_FragColor.g = coeffs.a + dot(coeffs.rgb,\n      texture2D(oesTex, interp_tc - 0.5 * xUnit).rgb);\n  gl_FragColor.b = coeffs.a + dot(coeffs.rgb,\n      texture2D(oesTex, interp_tc + 0.5 * xUnit).rgb);\n  gl_FragColor.a = coeffs.a + dot(coeffs.rgb,\n      texture2D(oesTex, interp_tc + 1.5 * xUnit).rgb);\n}\n";
        private static final String VERTEX_SHADER = "varying vec2 interp_tc;\nattribute vec4 in_pos;\nattribute vec4 in_tc;\n\nuniform mat4 texMatrix;\n\nvoid main() {\n    gl_Position = in_pos;\n    interp_tc = (texMatrix * in_tc).xy;\n}\n";
        private final EglBase eglBase;
        private static final FloatBuffer DEVICE_RECTANGLE = GlUtil.createFloatBuffer(new float[]{-1.0f, -1.0f, 1.0f, -1.0f, -1.0f, 1.0f, 1.0f, 1.0f});
        private static final FloatBuffer TEXTURE_RECTANGLE = GlUtil.createFloatBuffer(new float[]{0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f});
        private boolean released = false;
        private final GlShader shader = new GlShader(VERTEX_SHADER, FRAGMENT_SHADER);
        private int texMatrixLoc = this.shader.getUniformLocation("texMatrix");
        private int xUnitLoc = this.shader.getUniformLocation("xUnit");
        private int coeffsLoc = this.shader.getUniformLocation("coeffs");

        YuvConverter(EglBase.Context sharedContext) {
            this.eglBase = EglBase.create(sharedContext, EglBase.CONFIG_PIXEL_RGBA_BUFFER);
            this.eglBase.createDummyPbufferSurface();
            this.eglBase.makeCurrent();
            this.shader.useProgram();
            GLES20.glUniform1i(this.shader.getUniformLocation("oesTex"), 0);
            GlUtil.checkNoGLES2Error("Initialize fragment shader uniform values.");
            this.shader.setVertexAttribArray("in_pos", 2, DEVICE_RECTANGLE);
            this.shader.setVertexAttribArray("in_tc", 2, TEXTURE_RECTANGLE);
            this.eglBase.detachCurrent();
        }

        synchronized void convert(ByteBuffer buf, int width, int height, int stride, int textureId, float[] transformMatrix) {
            if (this.released) {
                throw new IllegalStateException("YuvConverter.convert called on released object");
            } else if (stride % 8 != 0) {
                throw new IllegalArgumentException("Invalid stride, must be a multiple of 8");
            } else if (stride < width) {
                throw new IllegalArgumentException("Invalid stride, must >= width");
            } else {
                int y_width = (width + 3) / 4;
                int uv_width = (width + 7) / 8;
                int uv_height = (height + 1) / 2;
                int total_height = height + uv_height;
                if (buf.capacity() < stride * total_height) {
                    throw new IllegalArgumentException("YuvConverter.convert called with too small buffer");
                }
                float[] transformMatrix2 = RendererCommon.multiplyMatrices(transformMatrix, RendererCommon.verticalFlipMatrix());
                if (!this.eglBase.hasSurface()) {
                    this.eglBase.createPbufferSurface(stride / 4, total_height);
                } else if (!(this.eglBase.surfaceWidth() == stride / 4 && this.eglBase.surfaceHeight() == total_height)) {
                    this.eglBase.releaseSurface();
                    this.eglBase.createPbufferSurface(stride / 4, total_height);
                }
                this.eglBase.makeCurrent();
                GLES20.glActiveTexture(33984);
                GLES20.glBindTexture(36197, textureId);
                GLES20.glUniformMatrix4fv(this.texMatrixLoc, 1, false, transformMatrix2, 0);
                GLES20.glViewport(0, 0, y_width, height);
                GLES20.glUniform2f(this.xUnitLoc, transformMatrix2[0] / width, transformMatrix2[1] / width);
                GLES20.glUniform4f(this.coeffsLoc, 0.299f, 0.587f, 0.114f, 0.0f);
                GLES20.glDrawArrays(5, 0, 4);
                GLES20.glViewport(0, height, uv_width, uv_height);
                GLES20.glUniform2f(this.xUnitLoc, transformMatrix2[0] / (2.0f * width), transformMatrix2[1] / (2.0f * width));
                GLES20.glUniform4f(this.coeffsLoc, -0.169f, -0.331f, 0.499f, 0.5f);
                GLES20.glDrawArrays(5, 0, 4);
                GLES20.glViewport(stride / 8, height, uv_width, uv_height);
                GLES20.glUniform4f(this.coeffsLoc, 0.499f, -0.418f, -0.0813f, 0.5f);
                GLES20.glDrawArrays(5, 0, 4);
                GLES20.glReadPixels(0, 0, stride / 4, total_height, 6408, 5121, buf);
                GlUtil.checkNoGLES2Error("YuvConverter.convert");
                GLES20.glBindTexture(36197, 0);
                this.eglBase.detachCurrent();
            }
        }

        synchronized void release() {
            this.released = true;
            this.eglBase.makeCurrent();
            this.shader.release();
            this.eglBase.release();
        }
    }

    private SurfaceTextureHelper(EglBase.Context sharedContext, Handler handler) {
        this.hasPendingTexture = false;
        this.isTextureInUse = false;
        this.isQuitting = false;
        if (handler.getLooper().getThread() != Thread.currentThread()) {
            throw new IllegalStateException("SurfaceTextureHelper must be created on the handler thread");
        }
        this.handler = handler;
        this.eglBase = EglBase.create(sharedContext, EglBase.CONFIG_PIXEL_BUFFER);
        this.eglBase.createDummyPbufferSurface();
        this.eglBase.makeCurrent();
        this.oesTextureId = GlUtil.generateTexture(36197);
        this.surfaceTexture = new SurfaceTexture(this.oesTextureId);
        this.surfaceTexture.setOnFrameAvailableListener(new SurfaceTexture.OnFrameAvailableListener() { // from class: com.superrtc.call.SurfaceTextureHelper.2
            @Override // android.graphics.SurfaceTexture.OnFrameAvailableListener
            public void onFrameAvailable(SurfaceTexture surfaceTexture) {
                SurfaceTextureHelper.this.hasPendingTexture = true;
                SurfaceTextureHelper.this.tryDeliverTextureFrame();
            }
        });
    }

    /* synthetic */ SurfaceTextureHelper(EglBase.Context context, Handler handler, SurfaceTextureHelper surfaceTextureHelper) {
        this(context, handler);
    }

    private YuvConverter getYuvConverter() {
        YuvConverter yuvConverter;
        if (this.yuvConverter != null) {
            return this.yuvConverter;
        }
        synchronized (this) {
            if (this.yuvConverter == null) {
                this.yuvConverter = new YuvConverter(this.eglBase.getEglBaseContext());
            }
            yuvConverter = this.yuvConverter;
        }
        return yuvConverter;
    }

    public void startListening(final OnTextureFrameAvailableListener listener) {
        if (this.listener != null) {
            throw new IllegalStateException("SurfaceTextureHelper listener has already been set.");
        }
        this.handler.post(new Runnable() { // from class: com.superrtc.call.SurfaceTextureHelper.3
            @Override // java.lang.Runnable
            public void run() {
                SurfaceTextureHelper.this.listener = listener;
                SurfaceTextureHelper.this.tryDeliverTextureFrame();
            }
        });
    }

    public void stopListening() {
        if (this.handler.getLooper().getThread() != Thread.currentThread()) {
            throw new IllegalStateException("Wrong thread.");
        }
        this.listener = null;
    }

    public SurfaceTexture getSurfaceTexture() {
        return this.surfaceTexture;
    }

    public Handler getHandler() {
        return this.handler;
    }

    public void returnTextureFrame() {
        this.handler.post(new Runnable() { // from class: com.superrtc.call.SurfaceTextureHelper.4
            @Override // java.lang.Runnable
            public void run() {
                SurfaceTextureHelper.this.isTextureInUse = false;
                if (SurfaceTextureHelper.this.isQuitting) {
                    SurfaceTextureHelper.this.release();
                } else {
                    SurfaceTextureHelper.this.tryDeliverTextureFrame();
                }
            }
        });
    }

    public boolean isTextureInUse() {
        return this.isTextureInUse;
    }

    public void dispose() {
        if (this.handler.getLooper().getThread() == Thread.currentThread()) {
            this.isQuitting = true;
            if (!this.isTextureInUse) {
                release();
                return;
            }
            return;
        }
        final CountDownLatch barrier = new CountDownLatch(1);
        this.handler.postAtFrontOfQueue(new Runnable() { // from class: com.superrtc.call.SurfaceTextureHelper.5
            @Override // java.lang.Runnable
            public void run() {
                SurfaceTextureHelper.this.isQuitting = true;
                barrier.countDown();
                if (!SurfaceTextureHelper.this.isTextureInUse) {
                    SurfaceTextureHelper.this.release();
                }
            }
        });
        ThreadUtils.awaitUninterruptibly(barrier);
    }

    public void textureToYUV(ByteBuffer buf, int width, int height, int stride, int textureId, float[] transformMatrix) {
        if (textureId != this.oesTextureId) {
            throw new IllegalStateException("textureToByteBuffer called with unexpected textureId");
        }
        getYuvConverter().convert(buf, width, height, stride, textureId, transformMatrix);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void tryDeliverTextureFrame() {
        long timestampNs;
        if (this.handler.getLooper().getThread() != Thread.currentThread()) {
            throw new IllegalStateException("Wrong thread.");
        } else if (!this.isQuitting && this.hasPendingTexture && !this.isTextureInUse && this.listener != null) {
            this.isTextureInUse = true;
            this.hasPendingTexture = false;
            this.eglBase.makeCurrent();
            this.surfaceTexture.updateTexImage();
            float[] transformMatrix = new float[16];
            this.surfaceTexture.getTransformMatrix(transformMatrix);
            if (Build.VERSION.SDK_INT >= 14) {
                timestampNs = this.surfaceTexture.getTimestamp();
            } else {
                timestampNs = TimeUnit.MILLISECONDS.toNanos(SystemClock.elapsedRealtime());
            }
            this.listener.onTextureFrameAvailable(this.oesTextureId, transformMatrix, timestampNs);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void release() {
        if (this.handler.getLooper().getThread() != Thread.currentThread()) {
            throw new IllegalStateException("Wrong thread.");
        } else if (this.isTextureInUse || !this.isQuitting) {
            throw new IllegalStateException("Unexpected release.");
        } else {
            synchronized (this) {
                if (this.yuvConverter != null) {
                    this.yuvConverter.release();
                }
            }
            this.eglBase.makeCurrent();
            GLES20.glDeleteTextures(1, new int[]{this.oesTextureId}, 0);
            this.surfaceTexture.release();
            this.eglBase.release();
            this.handler.getLooper().quit();
        }
    }
}
