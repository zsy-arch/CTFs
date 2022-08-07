package com.tencent.smtt.sdk.ui.dialog.widget;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;

/* loaded from: classes.dex */
public class c extends Drawable {

    /* renamed from: a  reason: collision with root package name */
    public float f1463a;

    /* renamed from: b  reason: collision with root package name */
    public float f1464b;

    /* renamed from: c  reason: collision with root package name */
    public float f1465c;

    /* renamed from: d  reason: collision with root package name */
    public float f1466d;

    /* renamed from: e  reason: collision with root package name */
    public Path f1467e;
    public Paint f = new Paint();
    public RectF g = new RectF();

    public c(int i, float f, float f2, float f3, float f4) {
        this.f1463a = f;
        this.f1464b = f2;
        this.f1466d = f3;
        this.f1465c = f4;
        this.f.setStyle(Paint.Style.FILL);
        this.f.setAntiAlias(true);
        this.f.setColor(i);
    }

    public void a(int i, int i2) {
        RectF rectF = this.g;
        rectF.left = 0.0f;
        rectF.top = 0.0f;
        rectF.right = i;
        rectF.bottom = i2;
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        if (this.f1467e == null) {
            this.f1467e = new Path();
        }
        this.f1467e.reset();
        Path path = this.f1467e;
        RectF rectF = this.g;
        float f = this.f1463a;
        float f2 = this.f1464b;
        float f3 = this.f1466d;
        float f4 = this.f1465c;
        path.addRoundRect(rectF, new float[]{f, f, f2, f2, f3, f3, f4, f4}, Path.Direction.CCW);
        this.f1467e.close();
        canvas.drawPath(this.f1467e, this.f);
    }

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        return -3;
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int i) {
        this.f.setAlpha(i);
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter colorFilter) {
        this.f.setColorFilter(colorFilter);
        invalidateSelf();
    }
}
