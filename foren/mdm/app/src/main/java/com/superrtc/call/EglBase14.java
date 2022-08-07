package com.superrtc.call;

import android.annotation.TargetApi;
import android.graphics.SurfaceTexture;
import android.opengl.EGL14;
import android.opengl.EGLConfig;
import android.opengl.EGLContext;
import android.opengl.EGLDisplay;
import android.opengl.EGLExt;
import android.opengl.EGLSurface;
import android.os.Build;
import android.view.Surface;
import com.superrtc.call.EglBase;

@TargetApi(18)
/* loaded from: classes2.dex */
public final class EglBase14 extends EglBase {
    private static final int CURRENT_SDK_VERSION = Build.VERSION.SDK_INT;
    private static final int EGLExt_SDK_VERSION = 18;
    private static final String TAG = "EglBase14";
    private EGLConfig eglConfig;
    private EGLContext eglContext;
    private EGLSurface eglSurface = EGL14.EGL_NO_SURFACE;
    private EGLDisplay eglDisplay = getEglDisplay();

    public static boolean isEGL14Supported() {
        Logging.d(TAG, "SDK version: " + CURRENT_SDK_VERSION + ". isEGL14Supported: " + (CURRENT_SDK_VERSION >= 18));
        return CURRENT_SDK_VERSION >= 18;
    }

    /* loaded from: classes2.dex */
    public static class Context extends EglBase.Context {
        private final EGLContext egl14Context;

        public Context(EGLContext eglContext) {
            this.egl14Context = eglContext;
        }
    }

    public EglBase14(Context sharedContext, int[] configAttributes) {
        this.eglConfig = getEglConfig(this.eglDisplay, configAttributes);
        this.eglContext = createEglContext(sharedContext, this.eglDisplay, this.eglConfig);
    }

    @Override // com.superrtc.call.EglBase
    public void createSurface(Surface surface) {
        createSurfaceInternal(surface);
    }

    @Override // com.superrtc.call.EglBase
    public void createSurface(SurfaceTexture surfaceTexture) {
        createSurfaceInternal(surfaceTexture);
    }

    private void createSurfaceInternal(Object surface) {
        if ((surface instanceof Surface) || (surface instanceof SurfaceTexture)) {
            checkIsNotReleased();
            if (this.eglSurface != EGL14.EGL_NO_SURFACE) {
                throw new RuntimeException("Already has an EGLSurface");
            }
            this.eglSurface = EGL14.eglCreateWindowSurface(this.eglDisplay, this.eglConfig, surface, new int[]{12344}, 0);
            if (this.eglSurface == EGL14.EGL_NO_SURFACE) {
                throw new RuntimeException("Failed to create window surface");
            }
            return;
        }
        throw new IllegalStateException("Input must be either a Surface or SurfaceTexture");
    }

    @Override // com.superrtc.call.EglBase
    public void createDummyPbufferSurface() {
        createPbufferSurface(1, 1);
    }

    @Override // com.superrtc.call.EglBase
    public void createPbufferSurface(int width, int height) {
        checkIsNotReleased();
        if (this.eglSurface != EGL14.EGL_NO_SURFACE) {
            throw new RuntimeException("Already has an EGLSurface");
        }
        this.eglSurface = EGL14.eglCreatePbufferSurface(this.eglDisplay, this.eglConfig, new int[]{12375, width, 12374, height, 12344}, 0);
        if (this.eglSurface == EGL14.EGL_NO_SURFACE) {
            throw new RuntimeException("Failed to create pixel buffer surface");
        }
    }

    @Override // com.superrtc.call.EglBase
    public Context getEglBaseContext() {
        return new Context(this.eglContext);
    }

    @Override // com.superrtc.call.EglBase
    public boolean hasSurface() {
        return this.eglSurface != EGL14.EGL_NO_SURFACE;
    }

    @Override // com.superrtc.call.EglBase
    public int surfaceWidth() {
        int[] widthArray = new int[1];
        EGL14.eglQuerySurface(this.eglDisplay, this.eglSurface, 12375, widthArray, 0);
        return widthArray[0];
    }

    @Override // com.superrtc.call.EglBase
    public int surfaceHeight() {
        int[] heightArray = new int[1];
        EGL14.eglQuerySurface(this.eglDisplay, this.eglSurface, 12374, heightArray, 0);
        return heightArray[0];
    }

    @Override // com.superrtc.call.EglBase
    public void releaseSurface() {
        if (this.eglSurface != EGL14.EGL_NO_SURFACE) {
            EGL14.eglDestroySurface(this.eglDisplay, this.eglSurface);
            this.eglSurface = EGL14.EGL_NO_SURFACE;
        }
    }

    private void checkIsNotReleased() {
        if (this.eglDisplay == EGL14.EGL_NO_DISPLAY || this.eglContext == EGL14.EGL_NO_CONTEXT || this.eglConfig == null) {
            throw new RuntimeException("This object has been released");
        }
    }

    @Override // com.superrtc.call.EglBase
    public void release() {
        checkIsNotReleased();
        releaseSurface();
        detachCurrent();
        EGL14.eglDestroyContext(this.eglDisplay, this.eglContext);
        EGL14.eglReleaseThread();
        EGL14.eglTerminate(this.eglDisplay);
        this.eglContext = EGL14.EGL_NO_CONTEXT;
        this.eglDisplay = EGL14.EGL_NO_DISPLAY;
        this.eglConfig = null;
    }

    @Override // com.superrtc.call.EglBase
    public void makeCurrent() {
        checkIsNotReleased();
        if (this.eglSurface == EGL14.EGL_NO_SURFACE) {
            throw new RuntimeException("No EGLSurface - can't make current");
        } else if (!EGL14.eglMakeCurrent(this.eglDisplay, this.eglSurface, this.eglSurface, this.eglContext)) {
            throw new RuntimeException("eglMakeCurrent failed");
        }
    }

    @Override // com.superrtc.call.EglBase
    public void detachCurrent() {
        if (!EGL14.eglMakeCurrent(this.eglDisplay, EGL14.EGL_NO_SURFACE, EGL14.EGL_NO_SURFACE, EGL14.EGL_NO_CONTEXT)) {
            throw new RuntimeException("eglMakeCurrent failed");
        }
    }

    @Override // com.superrtc.call.EglBase
    public void swapBuffers() {
        checkIsNotReleased();
        if (this.eglSurface == EGL14.EGL_NO_SURFACE) {
            throw new RuntimeException("No EGLSurface - can't swap buffers");
        }
        EGL14.eglSwapBuffers(this.eglDisplay, this.eglSurface);
    }

    public void swapBuffers(long timeStampNs) {
        checkIsNotReleased();
        if (this.eglSurface == EGL14.EGL_NO_SURFACE) {
            throw new RuntimeException("No EGLSurface - can't swap buffers");
        }
        EGLExt.eglPresentationTimeANDROID(this.eglDisplay, this.eglSurface, timeStampNs);
        EGL14.eglSwapBuffers(this.eglDisplay, this.eglSurface);
    }

    private static EGLDisplay getEglDisplay() {
        EGLDisplay eglDisplay = EGL14.eglGetDisplay(0);
        if (eglDisplay == EGL14.EGL_NO_DISPLAY) {
            throw new RuntimeException("Unable to get EGL14 display");
        }
        int[] version = new int[2];
        if (EGL14.eglInitialize(eglDisplay, version, 0, version, 1)) {
            return eglDisplay;
        }
        throw new RuntimeException("Unable to initialize EGL14");
    }

    private static EGLConfig getEglConfig(EGLDisplay eglDisplay, int[] configAttributes) {
        EGLConfig[] configs = new EGLConfig[1];
        if (EGL14.eglChooseConfig(eglDisplay, configAttributes, 0, configs, 0, configs.length, new int[1], 0)) {
            return configs[0];
        }
        throw new RuntimeException("Unable to find any matching EGL config");
    }

    private static EGLContext createEglContext(Context sharedContext, EGLDisplay eglDisplay, EGLConfig eglConfig) {
        if (sharedContext == null || sharedContext.egl14Context != EGL14.EGL_NO_CONTEXT) {
            EGLContext eglContext = EGL14.eglCreateContext(eglDisplay, eglConfig, sharedContext == null ? EGL14.EGL_NO_CONTEXT : sharedContext.egl14Context, new int[]{12440, 2, 12344}, 0);
            if (eglContext != EGL14.EGL_NO_CONTEXT) {
                return eglContext;
            }
            throw new RuntimeException("Failed to create EGL context");
        }
        throw new RuntimeException("Invalid sharedContext");
    }
}
