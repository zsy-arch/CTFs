package com.tencent.smtt.sdk.ui.dialog.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.view.View;

/* loaded from: classes.dex */
public class a extends View {

    /* renamed from: a  reason: collision with root package name */
    public int f1453a;

    /* renamed from: b  reason: collision with root package name */
    public int f1454b;

    /* renamed from: c  reason: collision with root package name */
    public Paint f1455c;

    /* renamed from: d  reason: collision with root package name */
    public Paint f1456d;

    /* renamed from: e  reason: collision with root package name */
    public Path f1457e;
    public Path f;
    public RectF g;
    public float[] h;
    public float i;
    public float j;

    public a(Context context, float f, float f2, float f3) {
        super(context, null, 0);
        a(context, f, f2, f3);
    }

    private int a(int i) {
        int mode = View.MeasureSpec.getMode(i);
        int size = View.MeasureSpec.getSize(i);
        if (mode == 1073741824) {
            return size;
        }
        if (mode == Integer.MIN_VALUE) {
            return Math.min(100, size);
        }
        return 100;
    }

    public static int a(Context context, float f) {
        return (int) ((f * context.getResources().getDisplayMetrics().density) + 0.5f);
    }

    private void a(Context context, float f, float f2, float f3) {
        this.i = f2;
        this.j = f3;
        int parseColor = Color.parseColor("#989DB4");
        this.f1455c = new Paint();
        this.f1456d = new Paint();
        this.f1456d.setColor(-1);
        this.f1456d.setStyle(Paint.Style.FILL);
        this.f1456d.setAntiAlias(true);
        this.f1455c.setColor(parseColor);
        this.f1455c.setStyle(Paint.Style.STROKE);
        this.f1455c.setAntiAlias(true);
        this.f1455c.setStrokeWidth(a(context, 6.0f));
        this.f1455c.setStrokeJoin(Paint.Join.ROUND);
        this.g = new RectF();
        this.h = new float[]{f, f, f, f, 0.0f, 0.0f, 0.0f, 0.0f};
    }

    @Override // android.view.View
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.translate(0.0f, 0.0f);
        canvas.rotate(0.0f);
        if (this.f == null) {
            this.f = new Path();
        }
        this.f.reset();
        this.f.addRoundRect(this.g, this.h, Path.Direction.CCW);
        this.f.close();
        canvas.drawPath(this.f, this.f1456d);
        canvas.translate(this.f1453a / 2.0f, (this.j / 2.0f) + (this.f1454b / 2.0f));
        if (this.f1457e == null) {
            this.f1457e = new Path();
        }
        this.f1457e.reset();
        this.f1457e.moveTo(0.0f, 0.0f);
        this.f1457e.lineTo((-this.i) / 2.0f, (-this.j) / 2.0f);
        this.f1457e.close();
        canvas.drawPath(this.f1457e, this.f1455c);
        this.f1457e.reset();
        this.f1457e.moveTo(0.0f, 0.0f);
        this.f1457e.lineTo(this.i / 2.0f, (-this.j) / 2.0f);
        this.f1457e.close();
        canvas.drawPath(this.f1457e, this.f1455c);
    }

    @Override // android.view.View
    public void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        setMeasuredDimension(a(i), a(i2));
    }

    @Override // android.view.View
    public void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        this.f1453a = i;
        this.f1454b = i2;
        RectF rectF = this.g;
        rectF.left = 0.0f;
        rectF.top = 0.0f;
        rectF.right = this.f1453a;
        rectF.bottom = this.f1454b;
    }
}
