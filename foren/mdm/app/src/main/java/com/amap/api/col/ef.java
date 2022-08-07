package com.amap.api.col;

import android.content.Context;
import android.graphics.Bitmap;
import android.location.Location;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.amap.api.maps.model.LatLng;
import com.hyphenate.util.EMPrivateConstant;

/* compiled from: LocationView.java */
/* loaded from: classes.dex */
public class ef extends LinearLayout {
    Bitmap a;
    Bitmap b;
    Bitmap c;
    Bitmap d;
    Bitmap e;
    Bitmap f;
    ImageView g;
    k h;
    boolean i = false;

    public void a() {
        try {
            removeAllViews();
            if (this.a != null) {
                this.a.recycle();
            }
            if (this.b != null) {
                this.b.recycle();
            }
            if (this.b != null) {
                this.c.recycle();
            }
            this.a = null;
            this.b = null;
            this.c = null;
            if (this.d != null) {
                this.d.recycle();
                this.d = null;
            }
            if (this.e != null) {
                this.e.recycle();
                this.e = null;
            }
            if (this.f != null) {
                this.f.recycle();
                this.f = null;
            }
        } catch (Throwable th) {
            gr.b(th, "LocationView", EMPrivateConstant.EMMultiUserConstant.ITEM_DESTROY);
            th.printStackTrace();
        }
    }

    public ef(Context context, k kVar) {
        super(context);
        this.h = kVar;
        try {
            this.d = dt.a(context, "location_selected.png");
            this.a = dt.a(this.d, g.a);
            this.e = dt.a(context, "location_pressed.png");
            this.b = dt.a(this.e, g.a);
            this.f = dt.a(context, "location_unselected.png");
            this.c = dt.a(this.f, g.a);
            this.g = new ImageView(context);
            this.g.setImageBitmap(this.a);
            this.g.setClickable(true);
            this.g.setPadding(0, 20, 20, 0);
            this.g.setOnTouchListener(new View.OnTouchListener() { // from class: com.amap.api.col.ef.1
                @Override // android.view.View.OnTouchListener
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    if (ef.this.i) {
                        if (motionEvent.getAction() == 0) {
                            ef.this.g.setImageBitmap(ef.this.b);
                        } else if (motionEvent.getAction() == 1) {
                            try {
                                ef.this.g.setImageBitmap(ef.this.a);
                                ef.this.h.setMyLocationEnabled(true);
                                Location myLocation = ef.this.h.getMyLocation();
                                if (myLocation != null) {
                                    LatLng latLng = new LatLng(myLocation.getLatitude(), myLocation.getLongitude());
                                    ef.this.h.a(myLocation);
                                    ef.this.h.a(z.a(latLng, ef.this.h.g()));
                                }
                            } catch (Throwable th) {
                                gr.b(th, "LocationView", "onTouch");
                                th.printStackTrace();
                            }
                        }
                    }
                    return false;
                }
            });
            addView(this.g);
        } catch (Throwable th) {
            gr.b(th, "LocationView", "create");
            th.printStackTrace();
        }
    }

    public void a(boolean z) {
        this.i = z;
        try {
            if (z) {
                this.g.setImageBitmap(this.a);
            } else {
                this.g.setImageBitmap(this.c);
            }
            this.g.invalidate();
        } catch (Throwable th) {
            gr.b(th, "LocationView", "showSelect");
            th.printStackTrace();
        }
    }
}
