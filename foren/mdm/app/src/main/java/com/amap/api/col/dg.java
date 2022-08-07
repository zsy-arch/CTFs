package com.amap.api.col;

import android.graphics.Color;
import android.opengl.GLES20;
import android.util.Log;
import android.view.SurfaceHolder;
import java.nio.Buffer;
import java.nio.FloatBuffer;
import javax.microedition.khronos.egl.EGL10;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.egl.EGLContext;
import javax.microedition.khronos.egl.EGLDisplay;

/* compiled from: GLESUtility.java */
/* loaded from: classes.dex */
public class dg {
    static d a;

    public static void a(String str) {
        int glGetError = GLES20.glGetError();
        if (glGetError != 0) {
            Log.e("amap", str + ": glError " + glGetError);
            throw new RuntimeException(str + ": glError " + glGetError);
        }
    }

    public static void a(int i, int i2, FloatBuffer floatBuffer, float f, FloatBuffer floatBuffer2, int i3, int i4, float[] fArr) {
        a(4, i, floatBuffer2, 1.0f, i4, fArr);
        a(2, i2, floatBuffer, f, i3, fArr);
    }

    public static void a(int i, FloatBuffer floatBuffer, float f, int i2, float[] fArr) {
        a(3, i, floatBuffer, f, i2, fArr);
    }

    public static void a(int i, int i2, FloatBuffer floatBuffer, float f, int i3, float[] fArr, int i4, int i5) {
        a(6, i, floatBuffer, 1.0f, i3, fArr);
        a(2, i2, floatBuffer, f, 1, i3 - 1, fArr);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: GLESUtility.java */
    /* loaded from: classes.dex */
    public static class d extends cu {
        int a;
        int b;
        int c;

        d(String str) {
            if (a(str)) {
                this.a = c("aMVPMatrix");
                this.c = c("aColor");
                this.b = b("aVertex");
            }
        }
    }

    public static void a() {
        a = new d("point.glsl");
    }

    private static void a(int i, int i2, FloatBuffer floatBuffer, float f, int i3, float[] fArr) {
        a(i, i2, floatBuffer, f, 0, i3, fArr);
    }

    private static void a(int i, int i2, FloatBuffer floatBuffer, float f, int i3, int i4, float[] fArr) {
        if (f != 0.0f && a != null) {
            a.b();
            GLES20.glEnable(3042);
            GLES20.glDisable(2929);
            GLES20.glBlendFunc(770, 771);
            float[] fArr2 = {Color.red(i2) / 255.0f, Color.green(i2) / 255.0f, Color.blue(i2) / 255.0f, Color.alpha(i2) / 255.0f};
            GLES20.glLineWidth(f);
            GLES20.glEnableVertexAttribArray(a.b);
            GLES20.glVertexAttribPointer(a.b, 3, 5126, false, 0, (Buffer) floatBuffer);
            GLES20.glUniform4fv(a.c, 1, fArr2, 0);
            GLES20.glUniformMatrix4fv(a.a, 1, false, fArr, 0);
            GLES20.glDrawArrays(i, i3, i4);
            GLES20.glDisableVertexAttribArray(a.b);
            GLES20.glDisable(3042);
            GLES20.glUseProgram(0);
        }
    }

    public static void a(l lVar, int i, int i2, int i3, int i4, int i5, int i6) {
        SurfaceHolder holder;
        if (i4 > 0 && (holder = lVar.getHolder()) != null) {
            holder.setFormat(-3);
        }
        lVar.a(new b());
        lVar.a(new a(i, i2, i3, i4, i5, i6));
    }

    /* compiled from: GLESUtility.java */
    /* loaded from: classes.dex */
    public static class b extends di {
        private static int a = 12440;

        @Override // com.amap.api.col.di, android.opengl.GLSurfaceView.EGLContextFactory, com.amap.api.col.j.f
        public EGLContext createContext(EGL10 egl10, EGLDisplay eGLDisplay, EGLConfig eGLConfig) {
            try {
                return egl10.eglCreateContext(eGLDisplay, eGLConfig, EGL10.EGL_NO_CONTEXT, new int[]{a, 2, 12344});
            } catch (Throwable th) {
                th.printStackTrace();
                return null;
            }
        }

        @Override // com.amap.api.col.di, android.opengl.GLSurfaceView.EGLContextFactory, com.amap.api.col.j.f
        public void destroyContext(EGL10 egl10, EGLDisplay eGLDisplay, EGLContext eGLContext) {
            egl10.eglDestroyContext(eGLDisplay, eGLContext);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: GLESUtility.java */
    /* loaded from: classes.dex */
    public static class c {
        public int[] a;
        public int[] b;

        private c() {
            this.a = null;
            this.b = new int[1];
        }
    }

    /* compiled from: GLESUtility.java */
    /* loaded from: classes.dex */
    public static class a extends dh {
        private static int g = 4;
        protected int a;
        protected int b;
        protected int c;
        protected int d;
        protected int e;
        protected int f;
        private int[] h = new int[1];

        public a(int i, int i2, int i3, int i4, int i5, int i6) {
            this.a = i;
            this.b = i2;
            this.c = i3;
            this.d = i4;
            this.e = i5;
            this.f = i6;
        }

        private int[] a(int i, boolean z) {
            int i2 = 1;
            if (i != 2) {
                return z ? new int[]{12324, this.a, 12323, this.b, 12322, this.c, 12338, 1, 12344} : new int[]{12324, this.a, 12323, this.b, 12322, this.c, 12344};
            }
            int[] iArr = new int[17];
            iArr[0] = 12324;
            iArr[1] = this.a;
            iArr[2] = 12323;
            iArr[3] = this.b;
            iArr[4] = 12322;
            iArr[5] = this.c;
            iArr[6] = 12321;
            iArr[7] = this.d;
            iArr[8] = 12325;
            iArr[9] = this.e;
            iArr[10] = 12326;
            iArr[11] = this.f;
            iArr[12] = 12338;
            if (!z) {
                i2 = 0;
            }
            iArr[13] = i2;
            iArr[14] = 12352;
            iArr[15] = g;
            iArr[16] = 12344;
            return iArr;
        }

        private c a(EGL10 egl10, EGLDisplay eGLDisplay) {
            c cVar = new c();
            cVar.a = a(2, true);
            egl10.eglChooseConfig(eGLDisplay, cVar.a, null, 0, cVar.b);
            if (cVar.b[0] <= 0) {
                cVar.a = a(2, false);
                egl10.eglChooseConfig(eGLDisplay, cVar.a, null, 0, cVar.b);
                if (cVar.b[0] <= 0) {
                    return null;
                }
            }
            return cVar;
        }

        @Override // com.amap.api.col.dh, android.opengl.GLSurfaceView.EGLConfigChooser, com.amap.api.col.j.e
        public EGLConfig chooseConfig(EGL10 egl10, EGLDisplay eGLDisplay) {
            c a = a(egl10, eGLDisplay);
            if (a == null || a.a == null) {
                return null;
            }
            EGLConfig[] eGLConfigArr = new EGLConfig[a.b[0]];
            egl10.eglChooseConfig(eGLDisplay, a.a, eGLConfigArr, a.b[0], a.b);
            EGLConfig a2 = a(egl10, eGLDisplay, eGLConfigArr);
            if (a2 != null) {
                return a2;
            }
            this.a = 8;
            this.b = 8;
            this.c = 8;
            c a3 = a(egl10, eGLDisplay);
            if (a3 == null || a3.a == null) {
                return a2;
            }
            EGLConfig[] eGLConfigArr2 = new EGLConfig[a3.b[0]];
            egl10.eglChooseConfig(eGLDisplay, a3.a, eGLConfigArr2, a3.b[0], a3.b);
            return a(egl10, eGLDisplay, eGLConfigArr2);
        }

        public EGLConfig a(EGL10 egl10, EGLDisplay eGLDisplay, EGLConfig[] eGLConfigArr) {
            for (EGLConfig eGLConfig : eGLConfigArr) {
                int a = a(egl10, eGLDisplay, eGLConfig, 12325, 0);
                int a2 = a(egl10, eGLDisplay, eGLConfig, 12326, 0);
                if (a >= this.e && a2 >= this.f) {
                    int a3 = a(egl10, eGLDisplay, eGLConfig, 12324, 0);
                    int a4 = a(egl10, eGLDisplay, eGLConfig, 12323, 0);
                    int a5 = a(egl10, eGLDisplay, eGLConfig, 12322, 0);
                    int a6 = a(egl10, eGLDisplay, eGLConfig, 12321, 0);
                    if (a3 == this.a && a4 == this.b && a5 == this.c && a6 == this.d) {
                        return eGLConfig;
                    }
                }
            }
            return null;
        }

        private int a(EGL10 egl10, EGLDisplay eGLDisplay, EGLConfig eGLConfig, int i, int i2) {
            if (egl10.eglGetConfigAttrib(eGLDisplay, eGLConfig, i, this.h)) {
                return this.h[0];
            }
            return i2;
        }
    }
}
