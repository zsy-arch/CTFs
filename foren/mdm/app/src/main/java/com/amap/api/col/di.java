package com.amap.api.col;

import android.opengl.GLSurfaceView;
import com.amap.api.col.j;
import javax.microedition.khronos.egl.EGL10;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.egl.EGLContext;
import javax.microedition.khronos.egl.EGLDisplay;

/* compiled from: GLMapSurfaceEGLContextFactory.java */
/* loaded from: classes.dex */
public class di implements GLSurfaceView.EGLContextFactory, j.f {
    @Override // android.opengl.GLSurfaceView.EGLContextFactory, com.amap.api.col.j.f
    public EGLContext createContext(EGL10 egl10, EGLDisplay eGLDisplay, EGLConfig eGLConfig) {
        return null;
    }

    @Override // android.opengl.GLSurfaceView.EGLContextFactory, com.amap.api.col.j.f
    public void destroyContext(EGL10 egl10, EGLDisplay eGLDisplay, EGLContext eGLContext) {
    }
}
