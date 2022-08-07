package com.amap.api.col;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.view.View;
import com.autonavi.amap.mapcore.DPoint;
import com.autonavi.amap.mapcore.VirtualEarthProjection;

/* compiled from: ScaleView.java */
/* loaded from: classes.dex */
public class eh extends View {
    private k c;
    private Point g;
    private float h;
    private String a = "";
    private int b = 0;
    private final int[] i = {10000000, 5000000, 2000000, 1000000, 500000, 200000, 100000, 50000, 30000, 20000, 10000, 5000, 2000, 1000, 500, 200, 100, 50, 25, 10, 5};
    private Paint d = new Paint();
    private Rect f = new Rect();
    private Paint e = new Paint();

    public void a() {
        this.d = null;
        this.e = null;
        this.f = null;
        this.a = null;
    }

    public eh(Context context, k kVar) {
        super(context);
        this.h = 0.0f;
        this.c = kVar;
        this.d.setAntiAlias(true);
        this.d.setColor(-16777216);
        this.d.setStrokeWidth(2.0f * g.a);
        this.d.setStyle(Paint.Style.STROKE);
        this.e.setAntiAlias(true);
        this.e.setColor(-16777216);
        this.e.setTextSize(20.0f * g.a);
        this.h = dq.a(context, 1.0f);
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        Point k;
        if (this.a != null && !this.a.equals("") && this.b != 0 && (k = this.c.k()) != null) {
            this.e.getTextBounds(this.a, 0, this.a.length(), this.f);
            int i = k.x;
            int height = (k.y - this.f.height()) + 5;
            canvas.drawText(this.a, ((this.b - this.f.width()) / 2) + i, height, this.e);
            int height2 = height + (this.f.height() - 5);
            canvas.drawLine(i, height2 - (this.h * 2.0f), i, g.a + height2, this.d);
            canvas.drawLine(i, height2, this.b + i, height2, this.d);
            canvas.drawLine(this.b + i, height2 - (this.h * 2.0f), this.b + i, g.a + height2, this.d);
        }
    }

    public void a(String str) {
        this.a = str;
    }

    public void a(int i) {
        this.b = i;
    }

    public void a(boolean z) {
        if (z) {
            setVisibility(0);
            b();
            return;
        }
        a("");
        a(0);
        setVisibility(8);
    }

    public void b() {
        if (this.c != null) {
            try {
                float a = this.c.a(1);
                this.g = this.c.n(1);
                if (this.g != null) {
                    DPoint PixelsToLatLong = VirtualEarthProjection.PixelsToLatLong(this.g.x, this.g.y, 20);
                    float s = this.c.s();
                    double cos = (float) ((((Math.cos((PixelsToLatLong.y * 3.141592653589793d) / 180.0d) * 2.0d) * 3.141592653589793d) * 6378137.0d) / (256.0d * Math.pow(2.0d, a)));
                    String a2 = dt.a(this.i[(int) a]);
                    a((int) (this.i[(int) a] / (s * cos)));
                    a(a2);
                    PixelsToLatLong.recycle();
                    invalidate();
                }
            } catch (Throwable th) {
                gr.b(th, "AMapDelegateImpGLSurfaceView", "changeScaleState");
                th.printStackTrace();
            }
        }
    }
}
