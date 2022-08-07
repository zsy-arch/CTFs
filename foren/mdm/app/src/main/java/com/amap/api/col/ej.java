package com.amap.api.col;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.RemoteException;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.amap.api.col.eg;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: ZoomControllerView.java */
/* loaded from: classes.dex */
public class ej extends LinearLayout {
    private Bitmap a;
    private Bitmap b;
    private Bitmap c;
    private Bitmap d;
    private Bitmap e;
    private Bitmap f;
    private Bitmap g;
    private Bitmap h;
    private Bitmap i;
    private Bitmap j;
    private Bitmap k;
    private Bitmap l;
    private ImageView m;
    private ImageView n;
    private k o;

    public void a() {
        try {
            removeAllViews();
            this.a.recycle();
            this.b.recycle();
            this.c.recycle();
            this.d.recycle();
            this.e.recycle();
            this.f.recycle();
            this.a = null;
            this.b = null;
            this.c = null;
            this.d = null;
            this.e = null;
            this.f = null;
            if (this.g != null) {
                this.g.recycle();
                this.g = null;
            }
            if (this.h != null) {
                this.h.recycle();
                this.h = null;
            }
            if (this.i != null) {
                this.i.recycle();
                this.i = null;
            }
            if (this.j != null) {
                this.j.recycle();
                this.g = null;
            }
            if (this.k != null) {
                this.k.recycle();
                this.k = null;
            }
            if (this.l != null) {
                this.l.recycle();
                this.l = null;
            }
            this.m = null;
            this.n = null;
        } catch (Throwable th) {
            gr.b(th, "ZoomControllerView", "destory");
            th.printStackTrace();
        }
    }

    public ej(Context context, k kVar) {
        super(context);
        this.o = kVar;
        try {
            this.g = dt.a(context, "zoomin_selected.png");
            this.a = dt.a(this.g, g.a);
            this.h = dt.a(context, "zoomin_unselected.png");
            this.b = dt.a(this.h, g.a);
            this.i = dt.a(context, "zoomout_selected.png");
            this.c = dt.a(this.i, g.a);
            this.j = dt.a(context, "zoomout_unselected.png");
            this.d = dt.a(this.j, g.a);
            this.k = dt.a(context, "zoomin_pressed.png");
            this.e = dt.a(this.k, g.a);
            this.l = dt.a(context, "zoomout_pressed.png");
            this.f = dt.a(this.l, g.a);
            this.m = new ImageView(context);
            this.m.setImageBitmap(this.a);
            this.m.setClickable(true);
            this.n = new ImageView(context);
            this.n.setImageBitmap(this.c);
            this.n.setClickable(true);
            this.m.setOnTouchListener(new View.OnTouchListener() { // from class: com.amap.api.col.ej.1
                @Override // android.view.View.OnTouchListener
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    try {
                        if (ej.this.o.g() < ej.this.o.getMaxZoomLevel() && ej.this.o.isMaploaded()) {
                            if (motionEvent.getAction() == 0) {
                                ej.this.m.setImageBitmap(ej.this.e);
                            } else if (motionEvent.getAction() == 1) {
                                ej.this.m.setImageBitmap(ej.this.a);
                                try {
                                    ej.this.o.b(z.a());
                                } catch (RemoteException e) {
                                    gr.b(e, "ZoomControllerView", "zoomin ontouch");
                                    e.printStackTrace();
                                }
                            }
                        }
                    } catch (Throwable th) {
                        th.printStackTrace();
                    }
                    return false;
                }
            });
            this.n.setOnTouchListener(new View.OnTouchListener() { // from class: com.amap.api.col.ej.2
                @Override // android.view.View.OnTouchListener
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    try {
                        if (ej.this.o.g() > ej.this.o.getMinZoomLevel() && ej.this.o.isMaploaded()) {
                            if (motionEvent.getAction() == 0) {
                                ej.this.n.setImageBitmap(ej.this.f);
                            } else if (motionEvent.getAction() == 1) {
                                ej.this.n.setImageBitmap(ej.this.c);
                                ej.this.o.b(z.b());
                            }
                        }
                    } catch (Throwable th) {
                        gr.b(th, "ZoomControllerView", "zoomout ontouch");
                        th.printStackTrace();
                    }
                    return false;
                }
            });
            this.m.setPadding(0, 0, 20, -2);
            this.n.setPadding(0, 0, 20, 20);
            setOrientation(1);
            addView(this.m);
            addView(this.n);
        } catch (Throwable th) {
            gr.b(th, "ZoomControllerView", "create");
            th.printStackTrace();
        }
    }

    public void a(float f) {
        try {
            if (f < this.o.getMaxZoomLevel() && f > this.o.getMinZoomLevel()) {
                this.m.setImageBitmap(this.a);
                this.n.setImageBitmap(this.c);
            } else if (f == this.o.getMinZoomLevel()) {
                this.n.setImageBitmap(this.d);
                this.m.setImageBitmap(this.a);
            } else if (f == this.o.getMaxZoomLevel()) {
                this.m.setImageBitmap(this.b);
                this.n.setImageBitmap(this.c);
            }
        } catch (Throwable th) {
            gr.b(th, "ZoomControllerView", "setZoomBitmap");
            th.printStackTrace();
        }
    }

    public void a(int i) {
        try {
            eg.a aVar = (eg.a) getLayoutParams();
            if (i == 1) {
                aVar.d = 16;
            } else if (i == 2) {
                aVar.d = 80;
            }
            setLayoutParams(aVar);
        } catch (Throwable th) {
            gr.b(th, "ZoomControllerView", "setZoomPosition");
            th.printStackTrace();
        }
    }

    public void a(boolean z) {
        if (z) {
            setVisibility(0);
        } else {
            setVisibility(8);
        }
    }
}
