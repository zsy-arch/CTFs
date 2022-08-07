package com.amap.api.col;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import com.amap.api.col.j;
import com.autonavi.ae.gmap.GLMapRender;

/* compiled from: AMapGLTextureView.java */
/* loaded from: classes.dex */
public class d extends j implements l {
    protected boolean a;
    private k b;
    private GLMapRender c;

    public d(Context context) {
        this(context, null);
    }

    public d(Context context, AttributeSet attributeSet) {
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

    @Override // com.amap.api.col.l
    public SurfaceHolder getHolder() {
        return null;
    }

    @Override // com.amap.api.col.l
    public void a(dh dhVar) {
        super.a((j.e) dhVar);
    }

    @Override // com.amap.api.col.l
    public void a(di diVar) {
        super.a((j.f) diVar);
    }

    @Override // com.amap.api.col.j, com.amap.api.col.l
    public void setRenderer(GLSurfaceView.Renderer renderer) {
        this.c = (GLMapRender) renderer;
        super.setRenderer(renderer);
    }

    @Override // com.amap.api.col.j
    public void b() {
        if (!this.c.mSurfacedestoryed) {
            queueEvent(new Runnable() { // from class: com.amap.api.col.d.1
                @Override // java.lang.Runnable
                public void run() {
                    try {
                        if (d.this.c != null) {
                            d.this.c.onSurfaceDestory();
                        }
                    } catch (Throwable th) {
                        th.printStackTrace();
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
        super.b();
    }

    @Override // com.amap.api.col.j
    public void c() {
        super.c();
    }

    @Override // android.view.View
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

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.amap.api.col.j, android.view.TextureView, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        try {
            if (this.c != null) {
                this.c.onAttachedToWindow();
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
        c();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.amap.api.col.j, android.view.View
    public void onDetachedFromWindow() {
        b();
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
