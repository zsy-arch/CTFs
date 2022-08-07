package com.amap.api.col;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.view.View;

/* compiled from: WaterMarkerView.java */
/* loaded from: classes.dex */
public class ei extends View {
    private Bitmap a;
    private Bitmap b;
    private Bitmap c;
    private Bitmap d;
    private int g;
    private int h;
    private k i;
    private Paint e = new Paint();
    private boolean f = false;
    private int j = 0;
    private int k = 10;
    private int l = 0;
    private int m = 0;
    private int n = 10;
    private int o = 8;
    private int p = 0;
    private boolean q = false;
    private float r = 0.0f;
    private float s = 0.0f;
    private boolean t = true;

    public void a() {
        try {
            if (this.a != null) {
                this.a.recycle();
            }
            if (this.b != null) {
                this.b.recycle();
            }
            this.a = null;
            this.b = null;
            if (this.c != null) {
                this.c.recycle();
                this.c = null;
            }
            if (this.d != null) {
                this.d.recycle();
                this.d = null;
            }
            this.e = null;
        } catch (Throwable th) {
            gr.b(th, "WaterMarkerView", "destory");
            th.printStackTrace();
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Removed duplicated region for block: B:46:0x00c8 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:49:0x00cd A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public ei(android.content.Context r7, com.amap.api.col.k r8) {
        /*
            Method dump skipped, instructions count: 223
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amap.api.col.ei.<init>(android.content.Context, com.amap.api.col.k):void");
    }

    public Bitmap b() {
        return this.f ? this.b : this.a;
    }

    public Point c() {
        return new Point(this.k, this.l - 2);
    }

    public void a(int i) {
        this.m = 0;
        this.j = i;
        d();
    }

    public void b(int i) {
        this.m = 1;
        this.o = i;
        d();
    }

    public void c(int i) {
        this.m = 1;
        this.n = i;
        d();
    }

    public float d(int i) {
        switch (i) {
            case 0:
                return this.r;
            case 1:
                return 1.0f - this.r;
            case 2:
                return 1.0f - this.s;
            default:
                return 0.0f;
        }
    }

    public void a(int i, float f) {
        this.m = 2;
        this.p = i;
        float max = Math.max(0.0f, Math.min(f, 1.0f));
        switch (i) {
            case 0:
                this.r = max;
                this.t = true;
                break;
            case 1:
                this.r = 1.0f - max;
                this.t = false;
                break;
            case 2:
                this.s = 1.0f - max;
                break;
        }
        d();
    }

    public void d() {
        if (getWidth() != 0 && getHeight() != 0) {
            e();
            postInvalidate();
        }
    }

    @Override // android.view.View
    public void onDraw(Canvas canvas) {
        try {
            if (getWidth() != 0 && getHeight() != 0 && this.b != null) {
                if (!this.q) {
                    e();
                    this.q = true;
                }
                canvas.drawBitmap(b(), this.k, this.l, this.e);
            }
        } catch (Throwable th) {
            gr.b(th, "WaterMarkerView", "onDraw");
            th.printStackTrace();
        }
    }

    private void e() {
        switch (this.m) {
            case 0:
                g();
                break;
            case 2:
                f();
                break;
        }
        this.k = this.n;
        this.l = (getHeight() - this.o) - this.g;
        if (this.k < 0) {
            this.k = 0;
        }
        if (this.l < 0) {
            this.l = 0;
        }
    }

    private void f() {
        if (this.t) {
            this.n = (int) (getWidth() * this.r);
        } else {
            this.n = (int) ((getWidth() * this.r) - this.h);
        }
        this.o = (int) (getHeight() * this.s);
    }

    private void g() {
        if (this.j == 1) {
            this.n = (getWidth() - this.h) / 2;
        } else if (this.j == 2) {
            this.n = (getWidth() - this.h) - 10;
        } else {
            this.n = 10;
        }
        this.o = 8;
    }
}
