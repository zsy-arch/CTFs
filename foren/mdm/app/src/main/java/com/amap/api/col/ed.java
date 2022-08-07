package com.amap.api.col;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.amap.api.maps.model.CameraPosition;
import com.hyphenate.util.EMPrivateConstant;

/* compiled from: CompassView.java */
/* loaded from: classes.dex */
public class ed extends LinearLayout {
    Bitmap a;
    Bitmap b;
    Bitmap c;
    ImageView d;
    k e;
    Matrix f = new Matrix();

    public void a() {
        try {
            removeAllViews();
            if (this.a != null) {
                this.a.recycle();
            }
            if (this.b != null) {
                this.b.recycle();
            }
            if (this.c != null) {
                this.c.recycle();
            }
            if (this.f != null) {
                this.f.reset();
                this.f = null;
            }
            this.c = null;
            this.a = null;
            this.b = null;
        } catch (Throwable th) {
            gr.b(th, "CompassView", EMPrivateConstant.EMMultiUserConstant.ITEM_DESTROY);
            th.printStackTrace();
        }
    }

    public ed(Context context, k kVar) {
        super(context);
        this.e = kVar;
        try {
            this.c = dt.a(context, "maps_dav_compass_needle_large.png");
            this.b = dt.a(this.c, g.a * 0.8f);
            this.c = dt.a(this.c, g.a * 0.7f);
            if (this.b != null || this.c != null) {
                this.a = Bitmap.createBitmap(this.b.getWidth(), this.b.getHeight(), Bitmap.Config.ARGB_8888);
                Canvas canvas = new Canvas(this.a);
                Paint paint = new Paint();
                paint.setAntiAlias(true);
                paint.setFilterBitmap(true);
                canvas.drawBitmap(this.c, (this.b.getWidth() - this.c.getWidth()) / 2.0f, (this.b.getHeight() - this.c.getHeight()) / 2.0f, paint);
                this.d = new ImageView(context);
                this.d.setScaleType(ImageView.ScaleType.MATRIX);
                this.d.setImageBitmap(this.a);
                this.d.setClickable(true);
                b();
                this.d.setOnTouchListener(new View.OnTouchListener() { // from class: com.amap.api.col.ed.1
                    @Override // android.view.View.OnTouchListener
                    public boolean onTouch(View view, MotionEvent motionEvent) {
                        try {
                            if (ed.this.e.isMaploaded()) {
                                if (motionEvent.getAction() == 0) {
                                    ed.this.d.setImageBitmap(ed.this.b);
                                } else if (motionEvent.getAction() == 1) {
                                    ed.this.d.setImageBitmap(ed.this.a);
                                    CameraPosition cameraPosition = ed.this.e.getCameraPosition();
                                    ed.this.e.b(z.a(new CameraPosition(cameraPosition.target, cameraPosition.zoom, 0.0f, 0.0f)));
                                }
                            }
                        } catch (Throwable th) {
                            gr.b(th, "CompassView", "onTouch");
                            th.printStackTrace();
                        }
                        return false;
                    }
                });
                addView(this.d);
            }
        } catch (Throwable th) {
            gr.b(th, "CompassView", "create");
            th.printStackTrace();
        }
    }

    public void b() {
        try {
            if (this.e != null && this.d != null) {
                float o = this.e.o(1);
                float m = this.e.m(1);
                if (this.f == null) {
                    this.f = new Matrix();
                }
                this.f.reset();
                this.f.postRotate(-m, this.d.getDrawable().getBounds().width() / 2.0f, this.d.getDrawable().getBounds().height() / 2.0f);
                this.f.postScale(1.0f, (float) Math.cos((o * 3.141592653589793d) / 180.0d), this.d.getDrawable().getBounds().width() / 2.0f, this.d.getDrawable().getBounds().height() / 2.0f);
                this.d.setImageMatrix(this.f);
            }
        } catch (Throwable th) {
            gr.b(th, "CompassView", "invalidateAngle");
            th.printStackTrace();
        }
    }

    public void a(boolean z) {
        if (z) {
            setVisibility(0);
            b();
            return;
        }
        setVisibility(8);
    }
}
