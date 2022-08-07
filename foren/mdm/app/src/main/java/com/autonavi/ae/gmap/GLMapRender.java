package com.autonavi.ae.gmap;

import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import com.autonavi.amap.mapcore.interfaces.IAMap;
import java.util.concurrent.atomic.AtomicLong;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

/* loaded from: classes.dex */
public class GLMapRender implements GLSurfaceView.Renderer {
    public static final int ANIMATION_TICK_COUNT = 10;
    public static final int LONG_LONG_TICK_COUNT = 30;
    public static final int LONG_TICK_COUNT = 6;
    private static final int MAP_RENDER_MSG_RUNNABLE_ONGLTHREAD = 100;
    private static final int MAP_RENDER_MSG_SURFACE_RENDER = 10;
    public static final int NORMAL_TICK_COUNT = 2;
    public static final int RENDER_FPS_ANIMATION = 30;
    public static final int RENDER_FPS_GESTURE_ACTION = 40;
    static final int RENDER_FPS_MAX = 60;
    public static final int RENDER_FPS_NAVI = 10;
    public static final int RENDER_FPS_NORMAL = 15;
    static final long RENDER_TIMMER_DIFF_MIN = 16;
    static final String TAG = "render";
    public IAMap mGLMapView;
    private int mTargetFrameDurationMillis = 66;
    private float mTargetRenderFPS = 15.0f;
    private volatile boolean mIsRendPause = false;
    public volatile boolean mSurfacedestoryed = false;
    private HandlerThread mGLRenderThread = null;
    private Handler mGLRenderHandler = null;
    private long mLastFrameTime = System.currentTimeMillis();
    private boolean mIsTrafficMode = false;
    private volatile AtomicLong mDrawFrameTickCount = new AtomicLong(6);

    public GLMapRender(IAMap iAMap) {
        this.mGLMapView = iAMap;
    }

    public void setTrafficMode(boolean z) {
        this.mIsTrafficMode = z;
    }

    public void resetTickCount(int i) {
        long j = this.mDrawFrameTickCount.get();
        if (this.mIsRendPause || this.mGLRenderThread == null || this.mGLRenderHandler == null || !this.mGLRenderThread.isAlive()) {
            if (j < i) {
                this.mDrawFrameTickCount.set(i);
            }
        } else if (j <= 0) {
            this.mDrawFrameTickCount.set(i);
            this.mGLRenderHandler.removeMessages(10);
            this.mGLRenderHandler.sendEmptyMessage(10);
        } else if (j < i) {
            this.mDrawFrameTickCount.set(i);
        }
    }

    public boolean isRenderPause() {
        return this.mIsRendPause;
    }

    public void setRenderFps(float f) {
        if (this.mTargetRenderFPS != f && f > 0.0f) {
            this.mTargetFrameDurationMillis = (int) ((1.0f / f) * 1000.0f);
            this.mTargetRenderFPS = f;
        }
    }

    public void sendToRenderEvent(Runnable runnable) {
        if (this.mGLRenderHandler != null && this.mGLRenderThread != null && this.mGLRenderThread.isAlive()) {
            this.mGLRenderHandler.post(runnable);
        }
    }

    public void renderPause() {
        if (!(this.mGLRenderHandler == null || this.mGLRenderThread == null || !this.mGLRenderThread.isAlive())) {
            this.mGLRenderHandler.removeMessages(10);
        }
        this.mIsRendPause = true;
    }

    public void renderResume() {
        if (!(this.mGLRenderHandler == null || this.mGLRenderThread == null || !this.mGLRenderThread.isAlive())) {
            this.mGLRenderHandler.removeMessages(10);
        }
        this.mIsRendPause = false;
        this.mDrawFrameTickCount.set(-1L);
        resetTickCount(30);
    }

    public void onAttachedToWindow() {
        this.mGLRenderThread = new HandlerThread(" AMapGlRenderThread");
        this.mGLRenderThread.start();
        this.mGLRenderHandler = new Handler(this.mGLRenderThread.getLooper()) { // from class: com.autonavi.ae.gmap.GLMapRender.1
            @Override // android.os.Handler
            public void handleMessage(Message message) {
                switch (message.what) {
                    case 10:
                        if (!GLMapRender.this.mIsRendPause && GLMapRender.this.mGLMapView != null && GLMapRender.this.mGLMapView.getRenderMode() == 0) {
                            GLMapRender.this.mGLMapView.requestRender();
                            return;
                        }
                        return;
                    case 100:
                        ((Runnable) message.obj).run();
                        return;
                    default:
                        return;
                }
            }
        };
    }

    public void onDetachedFromWindow() {
        this.mGLRenderThread.quit();
        this.mGLRenderThread = null;
        this.mGLRenderHandler = null;
    }

    private void drawSingleFrame(GL10 gl10) {
        try {
            this.mGLMapView.drawFrame(gl10);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    @Override // android.opengl.GLSurfaceView.Renderer
    public void onDrawFrame(GL10 gl10) {
        long max;
        GLES20.glClearColor(0.9453125f, 0.93359f, 0.9101f, 1.0f);
        GLES20.glClear(16640);
        if (!this.mIsRendPause) {
            this.mLastFrameTime = System.currentTimeMillis();
            drawSingleFrame(gl10);
            long currentTimeMillis = System.currentTimeMillis();
            long j = currentTimeMillis - this.mLastFrameTime;
            this.mLastFrameTime = currentTimeMillis;
            long j2 = this.mDrawFrameTickCount.get();
            if (this.mGLMapView.getRenderMode() == 0 && this.mGLRenderHandler != null && this.mGLRenderThread != null && this.mGLRenderThread.isAlive()) {
                long j3 = j2 - 1;
                this.mDrawFrameTickCount.set(j3);
                if (j3 > 0) {
                    max = Math.max(16L, this.mTargetFrameDurationMillis - j);
                } else if (j3 > -5) {
                    max = 60;
                } else if (j3 > -7) {
                    max = 100;
                } else if (j3 > -9) {
                    max = 250;
                } else {
                    max = this.mIsTrafficMode ? 10000L : 0L;
                    this.mDrawFrameTickCount.set(-9L);
                }
                if (max > 0) {
                    this.mGLRenderHandler.removeMessages(10);
                    this.mGLRenderHandler.sendEmptyMessageDelayed(10, max);
                }
            }
        }
    }

    @Override // android.opengl.GLSurfaceView.Renderer
    public void onSurfaceChanged(GL10 gl10, int i, int i2) {
        if (this.mSurfacedestoryed) {
            onSurfaceCreated(gl10, null);
        }
        this.mGLMapView.changeSurface(gl10, i, i2);
        resetTickCount(30);
    }

    @Override // android.opengl.GLSurfaceView.Renderer
    public void onSurfaceCreated(GL10 gl10, EGLConfig eGLConfig) {
        this.mIsRendPause = false;
        this.mSurfacedestoryed = false;
        this.mGLMapView.createSurface(gl10, eGLConfig);
    }

    public void onSurfaceDestory() {
        this.mIsRendPause = true;
        if (!(this.mGLRenderHandler == null || this.mGLRenderThread == null)) {
            this.mGLRenderHandler.removeCallbacksAndMessages(null);
        }
        this.mGLMapView.destorySurface(1);
        this.mSurfacedestoryed = true;
    }
}
