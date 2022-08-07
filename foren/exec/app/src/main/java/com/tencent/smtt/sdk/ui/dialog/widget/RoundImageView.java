package com.tencent.smtt.sdk.ui.dialog.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.graphics.Xfermode;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ImageView;
import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.ui.dialog.c;
import java.lang.ref.WeakReference;

/* loaded from: classes.dex */
public class RoundImageView extends ImageView {

    /* renamed from: a  reason: collision with root package name */
    public Paint f1448a;

    /* renamed from: b  reason: collision with root package name */
    public Xfermode f1449b;

    /* renamed from: c  reason: collision with root package name */
    public Bitmap f1450c;

    /* renamed from: d  reason: collision with root package name */
    public float[] f1451d;

    /* renamed from: e  reason: collision with root package name */
    public RectF f1452e;
    public int f;
    public WeakReference<Bitmap> g;
    public float h;
    public Path i;

    public RoundImageView(Context context) {
        this(context, null);
    }

    public RoundImageView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f1449b = new PorterDuffXfermode(PorterDuff.Mode.DST_IN);
        this.f = Color.parseColor("#eaeaea");
        this.f1448a = new Paint();
        this.f1448a.setAntiAlias(true);
        this.i = new Path();
        this.f1451d = new float[8];
        this.f1452e = new RectF();
        this.h = c.a(context, 16.46f);
        int i = 0;
        while (true) {
            float[] fArr = this.f1451d;
            if (i < fArr.length) {
                fArr[i] = this.h;
                i++;
            } else {
                return;
            }
        }
    }

    private Bitmap a() {
        Bitmap bitmap = null;
        try {
            bitmap = Bitmap.createBitmap(getWidth(), getHeight(), Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(bitmap);
            Paint paint = new Paint(1);
            paint.setColor(WebView.NIGHT_MODE_COLOR);
            canvas.drawRoundRect(new RectF(0.0f, 0.0f, getWidth(), getHeight()), this.h, this.h, paint);
            return bitmap;
        } catch (Throwable th) {
            th.printStackTrace();
            return bitmap;
        }
    }

    private void a(int i, int i2) {
        this.i.reset();
        this.f1448a.setStrokeWidth(i);
        this.f1448a.setColor(i2);
        this.f1448a.setStyle(Paint.Style.STROKE);
    }

    private void a(Canvas canvas, int i, int i2, RectF rectF, float[] fArr) {
        a(i, i2);
        this.i.addRoundRect(rectF, fArr, Path.Direction.CCW);
        canvas.drawPath(this.i, this.f1448a);
    }

    @Override // android.view.View
    public void invalidate() {
        this.g = null;
        Bitmap bitmap = this.f1450c;
        if (bitmap != null) {
            bitmap.recycle();
            this.f1450c = null;
        }
        super.invalidate();
    }

    @Override // android.widget.ImageView, android.view.View
    @SuppressLint({"DrawAllocation"})
    public void onDraw(Canvas canvas) {
        WeakReference<Bitmap> weakReference = this.g;
        Bitmap bitmap = weakReference == null ? null : weakReference.get();
        if (bitmap == null || bitmap.isRecycled()) {
            Drawable drawable = getDrawable();
            if (drawable != null) {
                int intrinsicWidth = drawable.getIntrinsicWidth();
                int intrinsicHeight = drawable.getIntrinsicHeight();
                Bitmap createBitmap = Bitmap.createBitmap(getWidth(), getHeight(), Bitmap.Config.ARGB_8888);
                Canvas canvas2 = new Canvas(createBitmap);
                float f = intrinsicWidth;
                float f2 = intrinsicHeight;
                float max = Math.max((getWidth() * 1.0f) / f, (getHeight() * 1.0f) / f2);
                drawable.setBounds(0, 0, (int) (f * max), (int) (max * f2));
                drawable.draw(canvas2);
                Bitmap bitmap2 = this.f1450c;
                if (bitmap2 == null || bitmap2.isRecycled()) {
                    this.f1450c = a();
                }
                this.f1448a.reset();
                this.f1448a.setFilterBitmap(false);
                this.f1448a.setXfermode(this.f1449b);
                Bitmap bitmap3 = this.f1450c;
                if (bitmap3 != null) {
                    canvas2.drawBitmap(bitmap3, 0.0f, 0.0f, this.f1448a);
                }
                this.f1448a.setXfermode(null);
                canvas.drawBitmap(createBitmap, 0.0f, 0.0f, (Paint) null);
                this.g = new WeakReference<>(createBitmap);
            }
        } else {
            this.f1448a.setXfermode(null);
            canvas.drawBitmap(bitmap, 0.0f, 0.0f, this.f1448a);
        }
        a(canvas, 1, this.f, this.f1452e, this.f1451d);
    }

    @Override // android.widget.ImageView, android.view.View
    public void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
    }

    @Override // android.view.View
    public void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        this.f1452e.set(0.5f, 0.5f, i - 0.5f, i2 - 0.5f);
    }
}
