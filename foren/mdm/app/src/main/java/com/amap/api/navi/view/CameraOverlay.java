package com.amap.api.navi.view;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import com.amap.api.col.fn;
import com.amap.api.col.fo;
import com.amap.api.col.gr;
import com.amap.api.maps.AMap;
import com.amap.api.maps.model.BitmapDescriptor;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;

/* loaded from: classes.dex */
public class CameraOverlay {
    private BitmapDescriptor mBitmapDescriptor;
    private Marker mCameraMarker;
    private LatLng mLastLatLng = null;

    public CameraOverlay() {
        this.mBitmapDescriptor = null;
        try {
            this.mBitmapDescriptor = BitmapDescriptorFactory.fromBitmap(BitmapFactory.decodeResource(fo.a(), 1191313411));
        } catch (Throwable th) {
            fn.a(th);
            gr.b(th, "CameraOverlay", "CameraOverlay()");
        }
    }

    public CameraOverlay(BitmapDescriptor bitmapDescriptor) {
        this.mBitmapDescriptor = null;
        this.mBitmapDescriptor = bitmapDescriptor;
    }

    public void draw(AMap aMap, LatLng latLng) {
        if (aMap != null) {
            try {
                if (this.mCameraMarker == null) {
                    this.mCameraMarker = aMap.addMarker(new MarkerOptions().position(latLng).anchor(0.5f, 0.5f).icon(this.mBitmapDescriptor));
                } else if (!latLng.equals(this.mLastLatLng)) {
                    this.mCameraMarker.setPosition(latLng);
                    this.mCameraMarker.setVisible(true);
                } else {
                    return;
                }
                this.mLastLatLng = latLng;
            } catch (Throwable th) {
                fn.a(th);
                gr.b(th, "CameraOverlay", "draw(AMap aMap, LatLng latLng)");
            }
        }
    }

    public void setVisible(boolean z) {
        if (this.mCameraMarker != null) {
            this.mCameraMarker.setVisible(z);
        }
    }

    public void destroy() {
        if (this.mCameraMarker != null) {
            this.mCameraMarker.remove();
        }
        if (this.mBitmapDescriptor != null) {
            this.mBitmapDescriptor.recycle();
        }
        this.mBitmapDescriptor = null;
    }

    public void setCameraBitmap(Bitmap bitmap) {
        if (bitmap != null) {
            this.mBitmapDescriptor = BitmapDescriptorFactory.fromBitmap(bitmap);
        }
    }
}
