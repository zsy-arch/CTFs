package com.amap.api.col;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.SurfaceTexture;
import android.opengl.GLDebugHelper;
import android.opengl.GLSurfaceView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.TextureView;
import java.io.Writer;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import javax.microedition.khronos.egl.EGL10;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.egl.EGLContext;
import javax.microedition.khronos.egl.EGLDisplay;
import javax.microedition.khronos.egl.EGLSurface;
import javax.microedition.khronos.opengles.GL;
import javax.microedition.khronos.opengles.GL10;

/* compiled from: GLTextureView.java */
@SuppressLint({"NewApi"})
/* loaded from: classes.dex */
public class j extends TextureView implements TextureView.SurfaceTextureListener {
    private static final C0016j a = new C0016j();
    private final WeakReference<j> b = new WeakReference<>(this);
    private i c;
    private GLSurfaceView.Renderer d;
    private boolean e;
    private e f;
    private f g;
    private g h;
    private k i;
    private int j;
    private int k;
    private boolean l;

    /* compiled from: GLTextureView.java */
    /* loaded from: classes.dex */
    public interface e {
        EGLConfig chooseConfig(EGL10 egl10, EGLDisplay eGLDisplay);
    }

    /* compiled from: GLTextureView.java */
    /* loaded from: classes.dex */
    public interface f {
        EGLContext createContext(EGL10 egl10, EGLDisplay eGLDisplay, EGLConfig eGLConfig);

        void destroyContext(EGL10 egl10, EGLDisplay eGLDisplay, EGLContext eGLContext);
    }

    /* compiled from: GLTextureView.java */
    /* loaded from: classes.dex */
    public interface g {
        EGLSurface a(EGL10 egl10, EGLDisplay eGLDisplay, EGLConfig eGLConfig, Object obj);

        void a(EGL10 egl10, EGLDisplay eGLDisplay, EGLSurface eGLSurface);
    }

    /* compiled from: GLTextureView.java */
    /* loaded from: classes.dex */
    public interface k {
        GL a(GL gl);
    }

    public j(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a();
    }

    protected void finalize() throws Throwable {
        try {
            if (this.c != null) {
                this.c.h();
            }
        } finally {
            super.finalize();
        }
    }

    private void a() {
        setSurfaceTextureListener(this);
    }

    public void setRenderer(GLSurfaceView.Renderer renderer) {
        e();
        if (this.f == null) {
            this.f = new m(true);
        }
        if (this.g == null) {
            this.g = new c();
        }
        if (this.h == null) {
            this.h = new d();
        }
        this.d = renderer;
        this.c = new i(this.b);
        this.c.start();
    }

    public void a(f fVar) {
        e();
        this.g = fVar;
    }

    public void a(e eVar) {
        e();
        this.f = eVar;
    }

    public void setRenderMode(int i2) {
        this.c.a(i2);
    }

    public void requestRender() {
        this.c.c();
    }

    public int getRenderMode() {
        return this.c.b();
    }

    public void b() {
        this.c.f();
    }

    public void c() {
        this.c.g();
    }

    public void queueEvent(Runnable runnable) {
        this.c.a(runnable);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.view.TextureView, android.view.View
    public void onAttachedToWindow() {
        int i2;
        super.onAttachedToWindow();
        if (this.e && this.d != null) {
            if (this.c != null) {
                i2 = this.c.b();
            } else {
                i2 = 1;
            }
            this.c = new i(this.b);
            if (i2 != 1) {
                this.c.a(i2);
            }
            this.c.start();
        }
        this.e = false;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.view.View
    public void onDetachedFromWindow() {
        if (this.c != null) {
            this.c.h();
        }
        this.e = true;
        super.onDetachedFromWindow();
    }

    /* compiled from: GLTextureView.java */
    /* loaded from: classes.dex */
    private class c implements f {
        private int b;

        private c() {
            this.b = 12440;
        }

        @Override // com.amap.api.col.j.f
        public EGLContext createContext(EGL10 egl10, EGLDisplay eGLDisplay, EGLConfig eGLConfig) {
            int[] iArr = {this.b, j.this.k, 12344};
            EGLContext eGLContext = EGL10.EGL_NO_CONTEXT;
            if (j.this.k == 0) {
                iArr = null;
            }
            return egl10.eglCreateContext(eGLDisplay, eGLConfig, eGLContext, iArr);
        }

        @Override // com.amap.api.col.j.f
        public void destroyContext(EGL10 egl10, EGLDisplay eGLDisplay, EGLContext eGLContext) {
            if (!egl10.eglDestroyContext(eGLDisplay, eGLContext)) {
                Log.e("DefaultContextFactory", "display:" + eGLDisplay + " context: " + eGLContext);
                h.a("eglDestroyContex", egl10.eglGetError());
            }
        }
    }

    /* compiled from: GLTextureView.java */
    /* loaded from: classes.dex */
    private static class d implements g {
        private d() {
        }

        @Override // com.amap.api.col.j.g
        public EGLSurface a(EGL10 egl10, EGLDisplay eGLDisplay, EGLConfig eGLConfig, Object obj) {
            try {
                return egl10.eglCreateWindowSurface(eGLDisplay, eGLConfig, obj, null);
            } catch (IllegalArgumentException e) {
                Log.e("GLSurfaceView", "eglCreateWindowSurface", e);
                return null;
            }
        }

        @Override // com.amap.api.col.j.g
        public void a(EGL10 egl10, EGLDisplay eGLDisplay, EGLSurface eGLSurface) {
            egl10.eglDestroySurface(eGLDisplay, eGLSurface);
        }
    }

    /* compiled from: GLTextureView.java */
    /* loaded from: classes.dex */
    private abstract class a implements e {
        protected int[] a;

        abstract EGLConfig a(EGL10 egl10, EGLDisplay eGLDisplay, EGLConfig[] eGLConfigArr);

        public a(int[] iArr) {
            this.a = a(iArr);
        }

        @Override // com.amap.api.col.j.e
        public EGLConfig chooseConfig(EGL10 egl10, EGLDisplay eGLDisplay) {
            int[] iArr = new int[1];
            if (!egl10.eglChooseConfig(eGLDisplay, this.a, null, 0, iArr)) {
                throw new IllegalArgumentException("eglChooseConfig failed");
            }
            int i = iArr[0];
            if (i <= 0) {
                throw new IllegalArgumentException("No configs match configSpec");
            }
            EGLConfig[] eGLConfigArr = new EGLConfig[i];
            if (!egl10.eglChooseConfig(eGLDisplay, this.a, eGLConfigArr, i, iArr)) {
                throw new IllegalArgumentException("eglChooseConfig#2 failed");
            }
            EGLConfig a = a(egl10, eGLDisplay, eGLConfigArr);
            if (a != null) {
                return a;
            }
            throw new IllegalArgumentException("No config chosen");
        }

        private int[] a(int[] iArr) {
            if (j.this.k != 2 && j.this.k != 3) {
                return iArr;
            }
            int length = iArr.length;
            int[] iArr2 = new int[length + 2];
            System.arraycopy(iArr, 0, iArr2, 0, length - 1);
            iArr2[length - 1] = 12352;
            if (j.this.k == 2) {
                iArr2[length] = 4;
            } else {
                iArr2[length] = 64;
            }
            iArr2[length + 1] = 12344;
            return iArr2;
        }
    }

    /* compiled from: GLTextureView.java */
    /* loaded from: classes.dex */
    private class b extends a {
        protected int c;
        protected int d;
        protected int e;
        protected int f;
        protected int g;
        protected int h;
        private int[] j = new int[1];

        public b(int i, int i2, int i3, int i4, int i5, int i6) {
            super(new int[]{12324, i, 12323, i2, 12322, i3, 12321, i4, 12325, i5, 12326, i6, 12344});
            this.c = i;
            this.d = i2;
            this.e = i3;
            this.f = i4;
            this.g = i5;
            this.h = i6;
        }

        @Override // com.amap.api.col.j.a
        public EGLConfig a(EGL10 egl10, EGLDisplay eGLDisplay, EGLConfig[] eGLConfigArr) {
            for (EGLConfig eGLConfig : eGLConfigArr) {
                int a = a(egl10, eGLDisplay, eGLConfig, 12325, 0);
                int a2 = a(egl10, eGLDisplay, eGLConfig, 12326, 0);
                if (a >= this.g && a2 >= this.h) {
                    int a3 = a(egl10, eGLDisplay, eGLConfig, 12324, 0);
                    int a4 = a(egl10, eGLDisplay, eGLConfig, 12323, 0);
                    int a5 = a(egl10, eGLDisplay, eGLConfig, 12322, 0);
                    int a6 = a(egl10, eGLDisplay, eGLConfig, 12321, 0);
                    if (a3 == this.c && a4 == this.d && a5 == this.e && a6 == this.f) {
                        return eGLConfig;
                    }
                }
            }
            return null;
        }

        private int a(EGL10 egl10, EGLDisplay eGLDisplay, EGLConfig eGLConfig, int i, int i2) {
            if (egl10.eglGetConfigAttrib(eGLDisplay, eGLConfig, i, this.j)) {
                return this.j[0];
            }
            return i2;
        }
    }

    /* compiled from: GLTextureView.java */
    /* loaded from: classes.dex */
    private class m extends b {
        public m(boolean z) {
            super(8, 8, 8, 0, z ? 16 : 0, 0);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: GLTextureView.java */
    /* loaded from: classes.dex */
    public static class h {
        EGL10 a;
        EGLDisplay b;
        EGLSurface c;
        EGLConfig d;
        EGLContext e;
        private WeakReference<j> f;

        public h(WeakReference<j> weakReference) {
            this.f = weakReference;
        }

        public void a() {
            this.a = (EGL10) EGLContext.getEGL();
            this.b = this.a.eglGetDisplay(EGL10.EGL_DEFAULT_DISPLAY);
            if (this.b == EGL10.EGL_NO_DISPLAY) {
                throw new RuntimeException("eglGetDisplay failed");
            }
            if (!this.a.eglInitialize(this.b, new int[2])) {
                throw new RuntimeException("eglInitialize failed");
            }
            j jVar = this.f.get();
            if (jVar == null) {
                this.d = null;
                this.e = null;
            } else {
                this.d = jVar.f.chooseConfig(this.a, this.b);
                this.e = jVar.g.createContext(this.a, this.b, this.d);
            }
            if (this.e == null || this.e == EGL10.EGL_NO_CONTEXT) {
                this.e = null;
                a("createContext");
            }
            this.c = null;
        }

        public boolean b() {
            if (this.a == null) {
                throw new RuntimeException("egl not initialized");
            } else if (this.b == null) {
                throw new RuntimeException("eglDisplay not initialized");
            } else if (this.d == null) {
                throw new RuntimeException("mEglConfig not initialized");
            } else {
                g();
                j jVar = this.f.get();
                if (jVar != null) {
                    this.c = jVar.h.a(this.a, this.b, this.d, jVar.getSurfaceTexture());
                } else {
                    this.c = null;
                }
                if (this.c == null || this.c == EGL10.EGL_NO_SURFACE) {
                    if (this.a.eglGetError() == 12299) {
                        Log.e("EglHelper", "createWindowSurface returned EGL_BAD_NATIVE_WINDOW.");
                    }
                    return false;
                } else if (this.a.eglMakeCurrent(this.b, this.c, this.c, this.e)) {
                    return true;
                } else {
                    a("EGLHelper", "eglMakeCurrent", this.a.eglGetError());
                    return false;
                }
            }
        }

        GL c() {
            l lVar;
            GL gl = this.e.getGL();
            j jVar = this.f.get();
            if (jVar == null) {
                return gl;
            }
            if (jVar.i != null) {
                gl = jVar.i.a(gl);
            }
            if ((jVar.j & 3) == 0) {
                return gl;
            }
            int i = 0;
            if ((jVar.j & 1) != 0) {
                i = 1;
            }
            if ((jVar.j & 2) != 0) {
                lVar = new l();
            } else {
                lVar = null;
            }
            return GLDebugHelper.wrap(gl, i, lVar);
        }

        public int d() {
            if (!this.a.eglSwapBuffers(this.b, this.c)) {
                return this.a.eglGetError();
            }
            return 12288;
        }

        public void e() {
            g();
        }

        private void g() {
            if (this.c != null && this.c != EGL10.EGL_NO_SURFACE) {
                this.a.eglMakeCurrent(this.b, EGL10.EGL_NO_SURFACE, EGL10.EGL_NO_SURFACE, EGL10.EGL_NO_CONTEXT);
                j jVar = this.f.get();
                if (jVar != null) {
                    jVar.h.a(this.a, this.b, this.c);
                }
                this.c = null;
            }
        }

        public void f() {
            if (this.e != null) {
                j jVar = this.f.get();
                if (jVar != null) {
                    jVar.g.destroyContext(this.a, this.b, this.e);
                }
                this.e = null;
            }
            if (this.b != null) {
                this.a.eglTerminate(this.b);
                this.b = null;
            }
        }

        private void a(String str) {
            a(str, this.a.eglGetError());
        }

        public static void a(String str, int i) {
            throw new RuntimeException(b(str, i));
        }

        public static void a(String str, String str2, int i) {
            Log.w(str, b(str2, i));
        }

        public static String b(String str, int i) {
            return str + " failed: " + i;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: GLTextureView.java */
    /* loaded from: classes.dex */
    public static class i extends Thread {
        private boolean a;
        private boolean b;
        private boolean c;
        private boolean d;
        private boolean e;
        private boolean f;
        private boolean g;
        private boolean h;
        private boolean i;
        private boolean j;
        private boolean k;
        private boolean p;
        private h s;
        private WeakReference<j> t;
        private ArrayList<Runnable> q = new ArrayList<>();
        private boolean r = true;
        private int l = 0;
        private int m = 0;
        private boolean o = true;
        private int n = 1;

        i(WeakReference<j> weakReference) {
            this.t = weakReference;
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            setName("GLThread " + getId());
            try {
                l();
            } catch (InterruptedException e) {
            } finally {
                j.a.a(this);
            }
        }

        private void j() {
            if (this.i) {
                this.i = false;
                this.s.e();
            }
        }

        private void k() {
            if (this.h) {
                this.s.f();
                this.h = false;
                j.a.c(this);
            }
        }

        private void l() throws InterruptedException {
            boolean z;
            int i;
            boolean z2;
            boolean z3;
            boolean z4;
            int i2;
            boolean z5;
            boolean z6;
            boolean z7;
            GL10 gl10;
            this.s = new h(this.t);
            this.h = false;
            this.i = false;
            boolean z8 = false;
            GL10 gl102 = null;
            int i3 = 0;
            boolean z9 = false;
            boolean z10 = false;
            boolean z11 = false;
            boolean z12 = false;
            boolean z13 = false;
            boolean z14 = false;
            Runnable runnable = null;
            int i4 = 0;
            boolean z15 = false;
            while (true) {
                try {
                    synchronized (j.a) {
                        while (!this.a) {
                            if (!this.q.isEmpty()) {
                                runnable = this.q.remove(0);
                                z = z15;
                                i = i4;
                                z2 = z14;
                                z13 = z13;
                                z12 = z12;
                                z11 = z11;
                                z3 = z10;
                                z4 = z9;
                                i2 = i3;
                            } else {
                                if (this.d != this.c) {
                                    boolean z16 = this.c;
                                    this.d = this.c;
                                    j.a.notifyAll();
                                    z5 = z16;
                                } else {
                                    z5 = false;
                                }
                                if (this.k) {
                                    j();
                                    k();
                                    this.k = false;
                                    z9 = true;
                                }
                                if (z12) {
                                    j();
                                    k();
                                    z12 = false;
                                }
                                if (z5 && this.i) {
                                    j();
                                }
                                if (z5 && this.h) {
                                    j jVar = this.t.get();
                                    if (!(jVar == null ? false : jVar.l) || j.a.a()) {
                                        k();
                                    }
                                }
                                if (z5 && j.a.b()) {
                                    this.s.f();
                                }
                                if (!this.e && !this.g) {
                                    if (this.i) {
                                        j();
                                    }
                                    this.g = true;
                                    this.f = false;
                                    j.a.notifyAll();
                                }
                                if (this.e && this.g) {
                                    this.g = false;
                                    j.a.notifyAll();
                                }
                                if (z15) {
                                    z10 = false;
                                    z15 = false;
                                    this.p = true;
                                    j.a.notifyAll();
                                }
                                if (m()) {
                                    if (!this.h) {
                                        if (z9) {
                                            z9 = false;
                                        } else if (j.a.b(this)) {
                                            try {
                                                this.s.a();
                                                this.h = true;
                                                z8 = true;
                                                j.a.notifyAll();
                                            } catch (RuntimeException e) {
                                                j.a.c(this);
                                                throw e;
                                            }
                                        }
                                    }
                                    if (!this.h || this.i) {
                                        z6 = z11;
                                        z7 = z13;
                                    } else {
                                        this.i = true;
                                        z14 = true;
                                        z6 = true;
                                        z7 = true;
                                    }
                                    if (this.i) {
                                        if (this.r) {
                                            z11 = true;
                                            i = this.l;
                                            i2 = this.m;
                                            z3 = true;
                                            z2 = true;
                                            this.r = false;
                                        } else {
                                            z2 = z14;
                                            z3 = z10;
                                            z11 = z6;
                                            i2 = i3;
                                            i = i4;
                                        }
                                        this.o = false;
                                        j.a.notifyAll();
                                        z13 = z7;
                                        z = z15;
                                        z12 = z12;
                                        z4 = z9;
                                    } else {
                                        z13 = z7;
                                        z11 = z6;
                                    }
                                }
                                j.a.wait();
                            }
                        }
                        synchronized (j.a) {
                            try {
                                j();
                                k();
                            } catch (Throwable th) {
                                throw th;
                            }
                        }
                        return;
                    }
                    if (runnable != null) {
                        runnable.run();
                        i3 = i2;
                        z9 = z4;
                        z10 = z3;
                        z11 = z11;
                        z12 = z12;
                        z13 = z13;
                        z14 = z2;
                        runnable = null;
                        i4 = i;
                        z15 = z;
                    } else {
                        if (!z2) {
                            z14 = z2;
                        } else if (this.s.b()) {
                            synchronized (j.a) {
                                this.j = true;
                                j.a.notifyAll();
                            }
                            z14 = false;
                        } else {
                            synchronized (j.a) {
                                this.j = true;
                                this.f = true;
                                j.a.notifyAll();
                            }
                            i3 = i2;
                            z9 = z4;
                            z10 = z3;
                            z11 = z11;
                            z12 = z12;
                            z13 = z13;
                            z14 = z2;
                            runnable = runnable;
                            i4 = i;
                            z15 = z;
                        }
                        if (z13) {
                            GL10 gl103 = (GL10) this.s.c();
                            j.a.a(gl103);
                            z13 = false;
                            gl10 = gl103;
                        } else {
                            gl10 = gl102;
                        }
                        if (z8) {
                            j jVar2 = this.t.get();
                            if (jVar2 != null) {
                                jVar2.d.onSurfaceCreated(gl10, this.s.d);
                            }
                            z8 = false;
                        }
                        if (z11) {
                            j jVar3 = this.t.get();
                            if (jVar3 != null) {
                                jVar3.d.onSurfaceChanged(gl10, i, i2);
                            }
                            z11 = false;
                        }
                        j jVar4 = this.t.get();
                        if (jVar4 != null) {
                            jVar4.d.onDrawFrame(gl10);
                        }
                        int d = this.s.d();
                        switch (d) {
                            case 12288:
                                break;
                            case 12302:
                                z12 = true;
                                break;
                            default:
                                h.a("GLThread", "eglSwapBuffers", d);
                                synchronized (j.a) {
                                    this.f = true;
                                    j.a.notifyAll();
                                    break;
                                }
                        }
                        z15 = z3 ? true : z;
                        runnable = runnable;
                        gl102 = gl10;
                        i4 = i;
                        z10 = z3;
                        i3 = i2;
                        z9 = z4;
                    }
                } catch (Throwable th2) {
                    synchronized (j.a) {
                        try {
                            j();
                            k();
                            throw th2;
                        } catch (Throwable th3) {
                            throw th3;
                        }
                    }
                }
            }
        }

        public boolean a() {
            return this.h && this.i && m();
        }

        private boolean m() {
            return !this.d && this.e && !this.f && this.l > 0 && this.m > 0 && (this.o || this.n == 1);
        }

        public void a(int i) {
            if (i < 0 || i > 1) {
                throw new IllegalArgumentException("renderMode");
            }
            synchronized (j.a) {
                this.n = i;
                j.a.notifyAll();
            }
        }

        public int b() {
            int i;
            synchronized (j.a) {
                i = this.n;
            }
            return i;
        }

        public void c() {
            synchronized (j.a) {
                this.o = true;
                j.a.notifyAll();
            }
        }

        public void d() {
            synchronized (j.a) {
                this.e = true;
                this.j = false;
                j.a.notifyAll();
                while (this.g && !this.j && !this.b) {
                    try {
                        j.a.wait();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
            }
        }

        public void e() {
            synchronized (j.a) {
                this.e = false;
                j.a.notifyAll();
                while (!this.g && !this.b) {
                    try {
                        j.a.wait();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
            }
        }

        public void f() {
            synchronized (j.a) {
                this.c = true;
                j.a.notifyAll();
                while (!this.b && !this.d) {
                    try {
                        j.a.wait();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
            }
        }

        public void g() {
            synchronized (j.a) {
                this.c = false;
                this.o = true;
                this.p = false;
                j.a.notifyAll();
                while (!this.b && this.d && !this.p) {
                    try {
                        j.a.wait();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
            }
        }

        public void a(int i, int i2) {
            synchronized (j.a) {
                this.l = i;
                this.m = i2;
                this.r = true;
                this.o = true;
                this.p = false;
                j.a.notifyAll();
                while (!this.b && !this.d && !this.p && a()) {
                    try {
                        j.a.wait();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
            }
        }

        public void h() {
            synchronized (j.a) {
                this.a = true;
                j.a.notifyAll();
                while (!this.b) {
                    try {
                        j.a.wait();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
            }
        }

        public void i() {
            this.k = true;
            j.a.notifyAll();
        }

        public void a(Runnable runnable) {
            if (runnable == null) {
                throw new IllegalArgumentException("r must not be null");
            }
            synchronized (j.a) {
                this.q.add(runnable);
                j.a.notifyAll();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: GLTextureView.java */
    /* loaded from: classes.dex */
    public static class l extends Writer {
        private StringBuilder a = new StringBuilder();

        l() {
        }

        @Override // java.io.Writer, java.io.Closeable, java.lang.AutoCloseable
        public void close() {
            a();
        }

        @Override // java.io.Writer, java.io.Flushable
        public void flush() {
            a();
        }

        @Override // java.io.Writer
        public void write(char[] cArr, int i, int i2) {
            for (int i3 = 0; i3 < i2; i3++) {
                char c = cArr[i + i3];
                if (c == '\n') {
                    a();
                } else {
                    this.a.append(c);
                }
            }
        }

        private void a() {
            if (this.a.length() > 0) {
                Log.v("GLSurfaceView", this.a.toString());
                this.a.delete(0, this.a.length());
            }
        }
    }

    private void e() {
        if (this.c != null) {
            throw new IllegalStateException("setRenderer has already been called for this instance.");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: GLTextureView.java */
    /* renamed from: com.amap.api.col.j$j  reason: collision with other inner class name */
    /* loaded from: classes.dex */
    public static class C0016j {
        private static String a = "GLThreadManager";
        private boolean b;
        private int c;
        private boolean d;
        private boolean e;
        private boolean f;
        private i g;

        private C0016j() {
        }

        public synchronized void a(i iVar) {
            iVar.b = true;
            if (this.g == iVar) {
                this.g = null;
            }
            notifyAll();
        }

        public boolean b(i iVar) {
            if (this.g == iVar || this.g == null) {
                this.g = iVar;
                notifyAll();
                return true;
            }
            c();
            if (this.e) {
                return true;
            }
            if (this.g != null) {
                this.g.i();
            }
            return false;
        }

        public void c(i iVar) {
            if (this.g == iVar) {
                this.g = null;
            }
            notifyAll();
        }

        public synchronized boolean a() {
            return this.f;
        }

        public synchronized boolean b() {
            c();
            return !this.e;
        }

        public synchronized void a(GL10 gl10) {
            boolean z = true;
            synchronized (this) {
                if (!this.d && gl10 != null) {
                    c();
                    String glGetString = gl10.glGetString(7937);
                    if (this.c < 131072) {
                        this.e = !glGetString.startsWith("Q3Dimension MSM7500 ");
                        notifyAll();
                    }
                    if (this.e) {
                        z = false;
                    }
                    this.f = z;
                    this.d = true;
                }
            }
        }

        private void c() {
            if (!this.b) {
                this.c = 131072;
                if (this.c >= 131072) {
                    this.e = true;
                }
                this.b = true;
            }
        }
    }

    @Override // android.view.TextureView.SurfaceTextureListener
    public void onSurfaceTextureAvailable(SurfaceTexture surfaceTexture, int i2, int i3) {
        this.c.d();
        onSurfaceTextureSizeChanged(surfaceTexture, i2, i3);
    }

    @Override // android.view.TextureView.SurfaceTextureListener
    public boolean onSurfaceTextureDestroyed(SurfaceTexture surfaceTexture) {
        this.c.e();
        return true;
    }

    @Override // android.view.TextureView.SurfaceTextureListener
    public void onSurfaceTextureSizeChanged(SurfaceTexture surfaceTexture, int i2, int i3) {
        this.c.a(i2, i3);
    }

    @Override // android.view.TextureView.SurfaceTextureListener
    public void onSurfaceTextureUpdated(SurfaceTexture surfaceTexture) {
    }

    @Override // android.view.View
    protected void onLayout(boolean z, int i2, int i3, int i4, int i5) {
        onSurfaceTextureSizeChanged(getSurfaceTexture(), i4 - i2, i5 - i3);
        super.onLayout(z, i2, i3, i4, i5);
    }
}
