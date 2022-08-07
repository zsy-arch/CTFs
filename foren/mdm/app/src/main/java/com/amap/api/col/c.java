package com.amap.api.col;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.util.AttributeSet;
import android.view.MotionEvent;
import com.autonavi.ae.gmap.GLMapRender;

/* compiled from: AMapGLSurfaceView.java */
/* loaded from: classes.dex */
public class c extends GLSurfaceView implements l {
    protected boolean a;
    private k b;
    private GLMapRender c;

    public c(Context context) {
        this(context, null);
    }

    public c(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.b = null;
        this.c = null;
        this.a = false;
        dg.a(this, 5, 6, 5, 0, 16, 8);
        this.b = new a(this, context, attributeSet);
    }

    public k a() {
        return this.b;
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        super.onTouchEvent(motionEvent);
        try {
            return this.b.onTouchEvent(motionEvent);
        } catch (Throwable th) {
            th.printStackTrace();
            return false;
        }
    }

    @Override // android.opengl.GLSurfaceView, com.amap.api.col.l
    public void setRenderer(GLSurfaceView.Renderer renderer) {
        this.c = (GLMapRender) renderer;
        super.setRenderer(renderer);
    }

    @Override // com.amap.api.col.l
    public void a(dh dhVar) {
        super.setEGLConfigChooser(dhVar);
    }

    @Override // com.amap.api.col.l
    public void a(di diVar) {
        super.setEGLContextFactory(diVar);
    }

    @Override // android.opengl.GLSurfaceView
    public void onPause() {
        if (!this.c.mSurfacedestoryed) {
            queueEvent(new Runnable() { // from class: com.amap.api.col.c.1
                @Override // java.lang.Runnable
                public void run() {
                    if (c.this.c != null) {
                        try {
                            c.this.c.onSurfaceDestory();
                        } catch (Throwable th) {
                            th.printStackTrace();
                        }
                    }
                }
            });
            int i = 0;
            while (!this.c.mSurfacedestoryed) {
                int i2 = i + 1;
                if (i >= 20) {
                    break;
                }
                try {
                    Thread.sleep(50L);
                    i = i2;
                } catch (InterruptedException e) {
                    i = i2;
                }
            }
        }
        super.onPause();
    }

    @Override // android.opengl.GLSurfaceView
    public void onResume() {
        super.onResume();
    }

    @Override // android.view.SurfaceView, android.view.View
    protected void onWindowVisibilityChanged(int i) {
        super.onWindowVisibilityChanged(i);
        try {
            if (i == 8 || i == 4) {
                if (this.c != null) {
                    this.c.renderPause();
                    this.a = false;
                }
            } else if (i != 0) {
            } else {
                if (this.c != null) {
                    this.c.renderResume();
                }
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    @Override // android.opengl.GLSurfaceView, android.view.SurfaceView, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        try {
            if (this.c != null) {
                this.c.onAttachedToWindow();
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
        onResume();
    }

    @Override // android.opengl.GLSurfaceView, android.view.SurfaceView, android.view.View
    protected void onDetachedFromWindow() {
        onPause();
        try {
            if (this.c != null) {
                this.c.onDetachedFromWindow();
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
        super.onDetachedFromWindow();
    }
}
