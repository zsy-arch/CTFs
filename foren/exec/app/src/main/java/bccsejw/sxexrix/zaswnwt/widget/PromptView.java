package bccsejw.sxexrix.zaswnwt.widget;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RoundRectShape;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ImageView;
import com.tencent.smtt.sdk.WebView;
import d.a.a.b.b;
import d.a.a.c;
import d.a.a.d.a;
import d.a.a.d.g;
import d.a.a.d.j;
import d.a.a.d.k;
import d.a.a.d.l;
import d.a.a.d.m;
import e.b.a.f;

@SuppressLint({"AppCompatCustomView"})
/* loaded from: classes.dex */
public class PromptView extends ImageView {

    /* renamed from: a */
    public j f307a;

    /* renamed from: b */
    public a f308b;

    /* renamed from: c */
    public int f309c;

    /* renamed from: d */
    public int f310d;

    /* renamed from: e */
    public ValueAnimator f311e;
    public Paint f;
    public float g;
    public Rect h;
    public int i;
    public int j;
    public RectF k;
    public int l;
    public g[] m;
    public RectF n;
    public float o;
    public float p;
    public boolean q;
    public float r;
    public float s;
    public Drawable t;
    public int u;
    public int v;
    public Bitmap w;
    public Matrix x;

    public PromptView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.m = new g[0];
    }

    public int c() {
        return this.l;
    }

    public void d() {
        ((AnimationDrawable) getDrawable()).stop();
    }

    @Override // android.widget.ImageView, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        setScaleType(ImageView.ScaleType.MATRIX);
        if (this.f == null) {
            this.f = new Paint();
        }
        if (this.h == null) {
            this.h = new Rect();
        }
        if (this.k == null) {
            this.n = new RectF();
        }
        float f = this.g;
        this.o = 120.0f * f;
        this.p = f * 44.0f;
    }

    @Override // android.widget.ImageView, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        Bitmap bitmap = this.w;
        if (bitmap != null) {
            bitmap.recycle();
        }
        this.w = null;
        ValueAnimator valueAnimator = this.f311e;
        if (valueAnimator != null) {
            valueAnimator.cancel();
        }
        this.f311e = null;
        this.m = null;
        this.f307a.j = false;
        this.l = 104;
    }

    @Override // android.widget.ImageView, android.view.View
    public void onDraw(Canvas canvas) {
        float f;
        float f2;
        int i;
        float f3;
        float f4;
        g gVar;
        float f5;
        if (this.f != null) {
            if (this.i == 0) {
                this.i = getWidth();
                this.j = getHeight();
            }
            this.f.reset();
            this.f.setAntiAlias(true);
            this.f.setColor(this.f308b.f1621c);
            this.f.setAlpha(this.f308b.f1622d);
            canvas.drawRect(0.0f, 0.0f, this.i, this.j, this.f);
            if (this.l == 109) {
                Drawable drawable = getDrawable();
                if (drawable != null) {
                    Rect bounds = drawable.getBounds();
                    this.u = (this.i / 2) - (bounds.width() / 2);
                    this.v = ((this.j / 2) - (bounds.height() / 2)) - (bounds.height() / 10);
                    canvas.translate(this.u, this.v);
                    if (this.w == null) {
                        Bitmap createBitmap = Bitmap.createBitmap(drawable.getMinimumWidth(), drawable.getMinimumHeight(), drawable.getOpacity() != -1 ? Bitmap.Config.ARGB_8888 : Bitmap.Config.RGB_565);
                        Canvas canvas2 = new Canvas(createBitmap);
                        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                        drawable.draw(canvas2);
                        Paint paint = new Paint();
                        paint.setAntiAlias(true);
                        Bitmap createBitmap2 = Bitmap.createBitmap(createBitmap.getWidth(), createBitmap.getHeight(), Bitmap.Config.ARGB_8888);
                        Canvas canvas3 = new Canvas(createBitmap2);
                        canvas3.drawRoundRect(new RectF(0.0f, 0.0f, createBitmap.getWidth(), createBitmap.getHeight()), 50.0f, 50.0f, paint);
                        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
                        canvas3.drawBitmap(createBitmap, 0.0f, 0.0f, paint);
                        this.w = createBitmap2;
                    }
                    canvas.drawBitmap(this.w, 0.0f, 0.0f, (Paint) null);
                    if (this.t == null) {
                        this.t = getResources().getDrawable(f.ic_prompt_close);
                    }
                    this.f309c = this.t.getMinimumWidth() / 2;
                    this.f310d = this.t.getMinimumHeight() / 2;
                    int width = (bounds.width() / 2) - this.f309c;
                    int height = bounds.height();
                    int i2 = this.f310d;
                    int i3 = height + i2;
                    this.t.setBounds(width, i3, (this.f309c * 2) + width, (i2 * 2) + i3);
                    this.t.draw(canvas);
                    canvas.save();
                }
            } else if (this.q) {
                String str = this.f308b.p;
                boolean z = str != null && str.length() > 0;
                if (this.n == null) {
                    this.n = new RectF();
                }
                RectF rectF = this.n;
                float f6 = this.j;
                rectF.set(0.0f, f6 - this.r, this.i, f6);
                canvas.translate(0.0f, this.j - this.r);
                this.f.reset();
                this.f.setAntiAlias(true);
                this.f.setColor(-1);
                this.f.setAlpha(this.f308b.j);
                a aVar = this.f308b;
                float f7 = aVar.t;
                float f8 = this.g;
                float f9 = f7 * f8;
                float f10 = this.s - f9;
                float f11 = f10 - (aVar.s * f8);
                float f12 = this.i - f9;
                float f13 = f8 * aVar.h;
                if (this.k == null) {
                    this.k = new RectF();
                }
                this.k.set(f9, f11, f12, f10);
                canvas.drawRoundRect(this.k, f13, f13, this.f);
                float f14 = f11 - (f9 / 2.0f);
                if (z) {
                    this.f.reset();
                    this.f.setColor(this.f308b.f1623e);
                    this.f.setStrokeWidth(this.g * 1.0f);
                    this.f.setTextSize(this.g * this.f308b.f);
                    this.f.setAntiAlias(true);
                    this.f.getTextBounds(str, 0, str.length(), this.h);
                    f5 = (-this.h.height()) - ((this.f308b.t * 1.5f) * this.g);
                } else {
                    f5 = 0.0f;
                }
                this.f.reset();
                this.f.setAntiAlias(true);
                this.f.setColor(-1);
                this.f.setAlpha(this.f308b.j);
                this.k.set(f9, f5, f12, f14);
                canvas.drawRoundRect(this.k, f13, f13, this.f);
                this.f.setColor(-7829368);
                this.f.setAlpha(100);
                this.f.setStrokeWidth(1.0f);
                this.f.setAntiAlias(true);
                float f15 = f14 - (this.f308b.s * this.g);
                canvas.drawLine(f9, f15, f12, f15, this.f);
                if (this.f308b.t == 0) {
                    canvas.drawLine(f9, f14, f12, f14, this.f);
                }
                if (z) {
                    canvas.drawLine(f9, 0.0f, f12, 0.0f, this.f);
                }
                g gVar2 = this.m[0];
                String str2 = gVar2.f1636b;
                this.f.reset();
                this.f.setColor(gVar2.f1639e);
                this.f.setStrokeWidth(this.g * 1.0f);
                this.f.setTextSize(this.g * gVar2.f);
                this.f.setAntiAlias(true);
                this.f.getTextBounds(str2, 0, str2.length(), this.h);
                float f16 = this.s;
                float f17 = this.g;
                float height2 = ((f16 - (f7 * f17)) - ((this.f308b.s * f17) / 2.0f)) + (this.h.height() / 2);
                float width2 = (this.i / 2) - (this.h.width() / 2);
                if (gVar2.g == null) {
                    float f18 = this.g;
                    float f19 = f7 * f18;
                    float f20 = this.j - f19;
                    gVar2.g = new RectF(f19, f20 - (this.f308b.s * f18), this.i - f19, f20);
                }
                canvas.drawText(str2, width2, height2, this.f);
                if (gVar2.f1638d) {
                    this.f.reset();
                    this.f.setAntiAlias(true);
                    this.f.setColor(WebView.NIGHT_MODE_COLOR);
                    this.f.setAlpha(this.f308b.r);
                    float f21 = this.g;
                    float f22 = f7 * f21;
                    float f23 = this.s - f22;
                    canvas.drawRoundRect(new RectF(f22, f23 - (this.f308b.s * f21), this.i - f22, f23), f13, f13, this.f);
                }
                g gVar3 = this.m[1];
                String str3 = gVar3.f1636b;
                this.f.reset();
                this.f.setColor(gVar3.f1639e);
                this.f.setStrokeWidth(this.g * 1.0f);
                this.f.setTextSize(this.g * gVar3.f);
                this.f.setAntiAlias(true);
                this.f.getTextBounds(str3, 0, str3.length(), this.h);
                float f24 = this.s;
                float f25 = f7 * 1.5f;
                float f26 = this.g;
                float height3 = ((f24 - (f25 * f26)) - ((this.f308b.s * f26) * 1.5f)) + (this.h.height() / 2);
                float width3 = (this.i / 2) - (this.h.width() / 2);
                if (gVar3.g == null) {
                    float f27 = this.g;
                    float f28 = f7 * f27;
                    float f29 = this.j - (f25 * f27);
                    float f30 = this.f308b.s;
                    gVar3.g = new RectF(f28, f29 - ((f30 * 2.0f) * f27), this.i - f28, f29 - (f30 * f27));
                }
                canvas.drawText(str3, width3, height3, this.f);
                if (gVar3.f1638d) {
                    ShapeDrawable shapeDrawable = new ShapeDrawable(new RoundRectShape(new float[]{0.0f, 0.0f, 0.0f, 0.0f, f13, f13, f13, f13}, null, null));
                    shapeDrawable.getPaint().setColor(WebView.NIGHT_MODE_COLOR);
                    shapeDrawable.getPaint().setAlpha(this.f308b.r);
                    RectF rectF2 = gVar3.g;
                    float f31 = rectF2.top;
                    float f32 = this.j;
                    float f33 = this.s;
                    shapeDrawable.setBounds(new Rect((int) rectF2.left, (int) ((f31 - f32) + f33), (int) rectF2.right, (int) ((rectF2.bottom - f32) + f33)));
                    shapeDrawable.draw(canvas);
                }
                int i4 = 2;
                while (true) {
                    g[] gVarArr = this.m;
                    if (i4 >= gVarArr.length) {
                        break;
                    }
                    g gVar4 = gVarArr[i4];
                    String str4 = gVar4.f1636b;
                    this.f.reset();
                    this.f.setColor(gVar4.f1639e);
                    this.f.setStrokeWidth(this.g * 1.0f);
                    this.f.setTextSize(gVar4.f * this.g);
                    this.f.setAntiAlias(true);
                    this.f.getTextBounds(str4, 0, str4.length(), this.h);
                    float f34 = this.s;
                    float f35 = this.g;
                    float f36 = i4;
                    float height4 = ((f34 - (f25 * f35)) - (((0.5f + f36) * this.f308b.s) * f35)) + (this.h.height() / 2);
                    float width4 = (this.i / 2) - (this.h.width() / 2);
                    if (gVar4.g == null) {
                        float f37 = this.g;
                        float f38 = f7 * f37;
                        float f39 = this.j - (f25 * f37);
                        int i5 = this.f308b.s;
                        f25 = f25;
                        gVar4.g = new RectF(f38, f39 - (((f36 + 1.0f) * i5) * f37), this.i - f38, f39 - ((i5 * i4) * f37));
                    } else {
                        f25 = f25;
                    }
                    canvas.drawText(str4, width4, height4, this.f);
                    if (i4 != this.m.length - 1) {
                        this.f.setColor(-7829368);
                        this.f.setAlpha(100);
                        this.f.setStrokeWidth(1.0f);
                        this.f.setAntiAlias(true);
                        float f40 = (this.s - (f9 * 1.5f)) - (((i4 + 1) * this.f308b.s) * this.g);
                        canvas.drawLine(f9, f40, this.i - f9, f40, this.f);
                    }
                    if (gVar4.f1638d) {
                        RectF rectF3 = gVar4.g;
                        float f41 = rectF3.top;
                        float f42 = this.j;
                        float f43 = this.s;
                        Rect rect = new Rect((int) rectF3.left, (int) ((f41 - f42) + f43), (int) rectF3.right, (int) ((rectF3.bottom - f42) + f43));
                        if (i4 != this.m.length - 1 || z) {
                            this.f.reset();
                            this.f.setAntiAlias(true);
                            this.f.setColor(WebView.NIGHT_MODE_COLOR);
                            this.f.setAlpha(this.f308b.r);
                            canvas.drawRect(rect, this.f);
                        } else {
                            ShapeDrawable shapeDrawable2 = new ShapeDrawable(new RoundRectShape(new float[]{f13, f13, f13, f13, 0.0f, 0.0f, 0.0f, 0.0f}, null, null));
                            shapeDrawable2.getPaint().setColor(WebView.NIGHT_MODE_COLOR);
                            shapeDrawable2.getPaint().setAlpha(this.f308b.r);
                            shapeDrawable2.setBounds(rect);
                            shapeDrawable2.draw(canvas);
                        }
                    }
                    i4++;
                }
                if (z) {
                    this.f.reset();
                    this.f.setColor(this.f308b.f1623e);
                    this.f.setStrokeWidth(this.g * 1.0f);
                    this.f.setTextSize(this.g * this.f308b.f);
                    this.f.setAntiAlias(true);
                    this.f.getTextBounds(str, 0, str.length(), this.h);
                    canvas.drawText(str, (this.i / 2) - (this.h.width() / 2), (((-this.h.height()) - ((this.f308b.t * 1.5f) * this.g)) / 2.0f) + (this.h.height() / 2), this.f);
                }
            } else {
                a aVar2 = this.f308b;
                String str5 = aVar2.p;
                float f44 = aVar2.g;
                float f45 = this.g;
                float f46 = f44 * f45;
                float f47 = aVar2.h * f45;
                this.f.reset();
                this.f.setColor(this.f308b.f1623e);
                this.f.setStrokeWidth(this.g * 1.0f);
                this.f.setTextSize(this.g * this.f308b.f);
                this.f.setAntiAlias(true);
                this.f.getTextBounds(str5, 0, str5.length(), this.h);
                if (this.l != 107) {
                    f = Math.max(this.g * 100.0f, (f46 * 2.0f) + this.h.width());
                    f2 = (3.0f * f46) + this.h.height() + (this.f310d * 2);
                    i = 2;
                } else {
                    float f48 = f46 * 2.0f;
                    f = Math.max(this.h.width() + f48, this.o * 2.0f);
                    if (this.o * 2.0f < this.h.width() + f48) {
                        this.o = (this.h.width() + f48) / 2.0f;
                    }
                    i = 2;
                    f2 = (3.0f * f46) + this.h.height() + (this.f310d * 2) + this.p;
                }
                float f49 = (this.j / i) - (f2 / 2.0f);
                float f50 = f / 2.0f;
                float f51 = (this.i / i) - f50;
                canvas.translate(f51, f49);
                this.f.reset();
                this.f.setAntiAlias(true);
                this.f.setColor(this.f308b.i);
                this.f.setAlpha(this.f308b.j);
                if (this.n == null) {
                    this.n = new RectF();
                }
                float f52 = f51 + f;
                this.n.set(f51, f49, f52, f49 + f2);
                if (this.k == null) {
                    f3 = 0.0f;
                    this.k = new RectF(0.0f, 0.0f, f, f2);
                } else {
                    f3 = 0.0f;
                }
                this.k.set(f3, f3, f, f2);
                canvas.drawRoundRect(this.k, f47, f47, this.f);
                this.f.reset();
                this.f.setColor(this.f308b.f1623e);
                this.f.setStrokeWidth(this.g * 1.0f);
                this.f.setTextSize(this.g * this.f308b.f);
                this.f.setAntiAlias(true);
                float height5 = (f46 * 2.0f) + (this.f310d * 2) + this.h.height();
                canvas.drawText(str5, f50 - (this.h.width() / 2), height5, this.f);
                if (this.l == 107) {
                    float f53 = height5 + f46;
                    this.f.setColor(-7829368);
                    this.f.setStrokeWidth(1.0f);
                    this.f.setAntiAlias(true);
                    canvas.drawLine(0.0f, f53, f, f53, this.f);
                    g[] gVarArr2 = this.m;
                    if (gVarArr2.length == 1) {
                        g gVar5 = gVarArr2[0];
                        if (gVar5.f1638d) {
                            this.f.reset();
                            this.f.setAntiAlias(true);
                            this.f.setColor(gVar5.i);
                            this.f.setStyle(Paint.Style.FILL);
                            f4 = f46;
                            gVar = gVar5;
                            canvas.drawRect(0.0f, f53, f, (f53 + this.p) - f47, this.f);
                            canvas.drawCircle(f47, (f53 + this.p) - f47, f47, this.f);
                            float f54 = f - f47;
                            canvas.drawCircle(f54, (f53 + this.p) - f47, f47, this.f);
                            float f55 = f53 + this.p;
                            canvas.drawRect(f47, f55 - f47, f54, f55, this.f);
                        } else {
                            f4 = f46;
                            gVar = gVar5;
                        }
                        String str6 = gVar.f1636b;
                        this.f.reset();
                        this.f.setColor(gVar.f1639e);
                        this.f.setStrokeWidth(this.g * 1.0f);
                        this.f.setTextSize(this.g * gVar.f);
                        this.f.setAntiAlias(true);
                        this.f.getTextBounds(str6, 0, str6.length(), this.h);
                        float f56 = f49 + f53;
                        gVar.g = new RectF(f51, f56, f52, this.p + f56);
                        canvas.drawText(str6, f50 - (this.h.width() / 2), (this.p / 2.0f) + f53 + (this.h.height() / 2), this.f);
                    } else {
                        f4 = f46;
                    }
                    if (this.m.length > 1) {
                        canvas.drawLine(f50, f53, f50, f2, this.f);
                        int i6 = 0;
                        while (true) {
                            g[] gVarArr3 = this.m;
                            if (i6 >= gVarArr3.length) {
                                break;
                            }
                            g gVar6 = gVarArr3[i6];
                            if (gVar6.f1638d) {
                                this.f.reset();
                                this.f.setAntiAlias(true);
                                this.f.setColor(gVar6.i);
                                this.f.setStyle(Paint.Style.FILL);
                                float f57 = this.o;
                                float f58 = f53 + 1.0f;
                                float f59 = i6 + 1;
                                canvas.drawRect(i6 * f57, f58, f57 * f59, (this.p + f58) - f47, this.f);
                                if (i6 == 0) {
                                    canvas.drawCircle(f47, (f53 + this.p) - f47, f47, this.f);
                                    float f60 = f53 + this.p;
                                    canvas.drawRect(f47, f60 - f47, this.o * f59, f60, this.f);
                                } else if (i6 == 1) {
                                    canvas.drawCircle((this.o * 2.0f) - f47, (f53 + this.p) - f47, f47, this.f);
                                    float f61 = this.o;
                                    float f62 = f53 + this.p;
                                    canvas.drawRect(f61, f62 - f47, (f61 * 2.0f) - f47, f62, this.f);
                                }
                            }
                            String str7 = gVar6.f1636b;
                            this.f.reset();
                            this.f.setColor(gVar6.f1639e);
                            this.f.setStrokeWidth(this.g * 1.0f);
                            this.f.setTextSize(gVar6.f * this.g);
                            this.f.setAntiAlias(true);
                            this.f.getTextBounds(str7, 0, str7.length(), this.h);
                            float f63 = i6;
                            float f64 = this.o;
                            float f65 = (f63 * f64) + f51;
                            float f66 = f49 + f53;
                            gVar6.g = new RectF(f65, f66, f64 + f65, this.p + f66);
                            canvas.drawText(str7, (f63 * this.o) + ((this.o / 2.0f) - (this.h.width() / 2)), (this.p / 2.0f) + f53 + (this.h.height() / 2), this.f);
                            i6++;
                        }
                    }
                } else {
                    f4 = f46;
                }
                canvas.translate(f50 - this.f309c, f4);
                super.onDraw(canvas);
            }
        }
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        float x = motionEvent.getX();
        float y = motionEvent.getY();
        int i = this.l;
        if (i == 107) {
            if (this.f308b.n && motionEvent.getAction() == 1 && !this.n.contains(x, y)) {
                this.f307a.a();
            }
            g[] gVarArr = this.m;
            for (g gVar : gVarArr) {
                if (gVar.g != null && gVar.g.contains(x, y)) {
                    if (motionEvent.getAction() == 0) {
                        gVar.f1638d = true;
                        invalidate();
                    }
                    if (motionEvent.getAction() == 1) {
                        gVar.f1638d = false;
                        invalidate();
                        if (gVar.j) {
                            this.f307a.a();
                        }
                        b bVar = gVar.h;
                        if (bVar != null) {
                            if (gVar.f1635a) {
                                postDelayed(new k(this, gVar), j.f1642a + 100);
                            } else {
                                ((c) bVar).a(gVar);
                            }
                        }
                    }
                    return true;
                }
            }
            if (motionEvent.getAction() == 1) {
                for (g gVar2 : this.m) {
                    gVar2.f1638d = false;
                    invalidate();
                }
            }
        } else if (i == 109 && motionEvent.getAction() == 1) {
            Drawable drawable = this.t;
            if ((drawable != null && drawable.getBounds().contains(((int) motionEvent.getX()) - this.u, ((int) motionEvent.getY()) - this.v)) || this.f308b.n) {
                this.f307a.a();
            } else if (getDrawable() != null && getDrawable().getBounds().contains(((int) motionEvent.getX()) - this.u, ((int) motionEvent.getY()) - this.v)) {
                this.f307a.b();
                this.f307a.a();
            }
        }
        return !this.f308b.k;
    }

    public a b() {
        return this.f308b;
    }

    public PromptView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.m = new g[0];
    }

    public void a(int i) {
        this.l = i;
        boolean z = false;
        if (i == 107) {
            if (this.m.length > 2) {
                z = true;
            }
            this.q = z;
        } else {
            this.q = false;
        }
        ValueAnimator valueAnimator = this.f311e;
        if (valueAnimator != null && valueAnimator.isRunning()) {
            this.f311e.end();
        }
        setImageDrawable(getResources().getDrawable(this.f308b.o));
        this.f309c = getDrawable().getMinimumWidth() / 2;
        this.f310d = getDrawable().getMinimumHeight() / 2;
        Matrix matrix = this.x;
        if (matrix != null) {
            matrix.setRotate(0.0f, this.f309c, this.f310d);
            setImageMatrix(this.x);
        }
        if (this.q) {
            a aVar = this.f308b;
            this.s = ((aVar.t * 1.5f) + (aVar.s * this.m.length)) * this.g;
            StringBuilder a2 = e.a.a.a.a.a("showSomthing: ");
            a2.append(this.s);
            a2.toString();
            ValueAnimator ofFloat = ObjectAnimator.ofFloat(0.0f, 1.0f);
            ofFloat.setDuration(300L);
            ofFloat.addUpdateListener(new l(this));
            ofFloat.start();
        }
        invalidate();
    }

    public PromptView(Activity activity, a aVar, j jVar) {
        super(activity);
        this.m = new g[0];
        this.g = getResources().getDisplayMetrics().density;
        this.f308b = aVar;
        this.f307a = jVar;
    }

    public void a(g... gVarArr) {
        this.m = gVarArr;
        a(107);
    }

    public void a(a aVar) {
        if (this.f308b != aVar) {
            this.f308b = aVar;
        }
    }

    public void a() {
        if (this.q) {
            ValueAnimator ofFloat = ObjectAnimator.ofFloat(1.0f, 0.0f);
            ofFloat.setDuration(300L);
            ofFloat.addUpdateListener(new m(this));
            ofFloat.start();
        }
    }
}
