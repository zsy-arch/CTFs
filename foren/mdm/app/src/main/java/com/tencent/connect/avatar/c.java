package com.tencent.connect.avatar;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;

/* compiled from: ProGuard */
/* loaded from: classes2.dex */
public class c extends ImageView {
    private Bitmap h;
    private float j;
    private float k;
    private Matrix c = new Matrix();
    private Matrix d = new Matrix();
    private int e = 0;
    private float f = 1.0f;
    private float g = 1.0f;
    private boolean i = false;
    final String a = "TouchView";
    private PointF l = new PointF();
    private PointF m = new PointF();
    private float n = 1.0f;
    private float o = 0.0f;
    public boolean b = false;
    private Rect p = new Rect();

    public c(Context context) {
        super(context);
        getDrawingRect(this.p);
        a();
    }

    @Override // android.widget.ImageView
    public void setImageBitmap(Bitmap bitmap) {
        super.setImageBitmap(bitmap);
        this.h = bitmap;
        if (bitmap != null) {
            this.h = bitmap;
        }
    }

    private float a(MotionEvent motionEvent) {
        if (motionEvent.getPointerCount() < 2) {
            return 0.0f;
        }
        float x = motionEvent.getX(0) - motionEvent.getX(1);
        float y = motionEvent.getY(0) - motionEvent.getY(1);
        return (float) Math.sqrt((x * x) + (y * y));
    }

    private void a() {
    }

    public void a(Rect rect) {
        this.p = rect;
        if (this.h != null) {
            c();
        }
    }

    private void a(PointF pointF) {
        float f = 1.0f;
        if (this.h != null) {
            float[] fArr = new float[9];
            this.c.getValues(fArr);
            float f2 = fArr[2];
            float f3 = fArr[5];
            float f4 = fArr[0];
            float width = this.h.getWidth() * f4;
            float height = this.h.getHeight() * f4;
            float f5 = this.p.left - f2;
            if (f5 <= 1.0f) {
                f5 = 1.0f;
            }
            float f6 = (f2 + width) - this.p.right;
            if (f6 <= 1.0f) {
                f6 = 1.0f;
            }
            float width2 = ((f5 * this.p.width()) / (f6 + f5)) + this.p.left;
            float f7 = this.p.top - f3;
            float f8 = (f3 + height) - this.p.bottom;
            if (f7 <= 1.0f) {
                f7 = 1.0f;
            }
            if (f8 > 1.0f) {
                f = f8;
            }
            pointF.set(width2, ((this.p.height() * f7) / (f7 + f)) + this.p.top);
        }
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (!this.i) {
            switch (motionEvent.getAction() & 255) {
                case 0:
                    this.c.set(getImageMatrix());
                    this.d.set(this.c);
                    this.l.set(motionEvent.getX(), motionEvent.getY());
                    this.e = 1;
                    break;
                case 1:
                case 6:
                    b();
                    this.e = 0;
                    break;
                case 2:
                    if (this.e != 1) {
                        if (this.e == 2) {
                            this.c.set(this.c);
                            float a = a(motionEvent);
                            if (a > 10.0f) {
                                this.c.set(this.d);
                                float f = a / this.n;
                                this.c.postScale(f, f, this.m.x, this.m.y);
                            }
                            setImageMatrix(this.c);
                            break;
                        }
                    } else {
                        this.c.set(this.d);
                        this.c.postTranslate(motionEvent.getX() - this.l.x, motionEvent.getY() - this.l.y);
                        setImageMatrix(this.c);
                        break;
                    }
                    break;
                case 5:
                    this.n = a(motionEvent);
                    if (this.n > 10.0f) {
                        this.d.set(this.c);
                        a(this.m);
                        this.e = 2;
                        break;
                    }
                    break;
            }
            this.b = true;
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b() {
        if (this.h != null) {
            float width = this.p.width();
            float height = this.p.height();
            float[] fArr = new float[9];
            this.c.getValues(fArr);
            float f = fArr[2];
            float f2 = fArr[5];
            float f3 = fArr[0];
            Animation animation = null;
            if (f3 > this.f) {
                this.o = this.f / f3;
                this.c.postScale(this.o, this.o, this.m.x, this.m.y);
                setImageMatrix(this.c);
                animation = new ScaleAnimation(1.0f / this.o, 1.0f, 1.0f / this.o, 1.0f, this.m.x, this.m.y);
            } else if (f3 < this.g) {
                this.o = this.g / f3;
                this.c.postScale(this.o, this.o, this.m.x, this.m.y);
                animation = new ScaleAnimation(1.0f, this.o, 1.0f, this.o, this.m.x, this.m.y);
            } else {
                boolean z = false;
                float width2 = this.h.getWidth() * f3;
                float height2 = f3 * this.h.getHeight();
                float f4 = this.p.left - f;
                float f5 = this.p.top - f2;
                if (f4 < 0.0f) {
                    f = this.p.left;
                    z = true;
                }
                if (f5 < 0.0f) {
                    f2 = this.p.top;
                    z = true;
                }
                float f6 = height2 - f5;
                if (width2 - f4 < width) {
                    f = this.p.left - (width2 - width);
                    z = true;
                }
                if (f6 < height) {
                    f2 = this.p.top - (height2 - height);
                    z = true;
                }
                if (z) {
                    float f7 = fArr[2] - f;
                    float f8 = fArr[5] - f2;
                    fArr[2] = f;
                    fArr[5] = f2;
                    this.c.setValues(fArr);
                    setImageMatrix(this.c);
                    animation = new TranslateAnimation(f7, 0.0f, f8, 0.0f);
                } else {
                    setImageMatrix(this.c);
                }
            }
            if (animation != null) {
                this.i = true;
                animation.setDuration(300L);
                startAnimation(animation);
                new Thread(new Runnable() { // from class: com.tencent.connect.avatar.c.1
                    @Override // java.lang.Runnable
                    public void run() {
                        try {
                            Thread.sleep(300L);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        c.this.post(new Runnable() { // from class: com.tencent.connect.avatar.c.1.1
                            @Override // java.lang.Runnable
                            public void run() {
                                c.this.clearAnimation();
                                c.this.b();
                            }
                        });
                        c.this.i = false;
                    }
                }).start();
            }
        }
    }

    private void c() {
        if (this.h != null) {
            float[] fArr = new float[9];
            this.c.getValues(fArr);
            float max = Math.max(this.p.width() / this.h.getWidth(), this.p.height() / this.h.getHeight());
            this.j = this.p.left - (((this.h.getWidth() * max) - this.p.width()) / 2.0f);
            this.k = this.p.top - (((this.h.getHeight() * max) - this.p.height()) / 2.0f);
            fArr[2] = this.j;
            fArr[5] = this.k;
            fArr[4] = max;
            fArr[0] = max;
            this.c.setValues(fArr);
            this.f = Math.min(2048.0f / this.h.getWidth(), 2048.0f / this.h.getHeight());
            this.g = max;
            if (this.f < this.g) {
                this.f = this.g;
            }
            setImageMatrix(this.c);
        }
    }
}
