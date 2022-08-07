package com.amap.api.col;

import android.graphics.Bitmap;
import android.opengl.GLES20;
import android.os.RemoteException;
import android.util.Log;
import com.amap.api.maps.model.BitmapDescriptor;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.LatLngBounds;
import com.autonavi.ae.gmap.GLMapState;
import com.autonavi.amap.mapcore.DPoint;
import com.autonavi.amap.mapcore.FPointBounds;
import com.autonavi.amap.mapcore.IPoint;
import com.autonavi.amap.mapcore.interfaces.IOverlay;
import com.hyphenate.util.EMPrivateConstant;
import java.nio.Buffer;
import java.nio.FloatBuffer;
import java.util.ArrayList;
import java.util.List;

/* compiled from: GroundOverlayDelegateImp.java */
/* loaded from: classes.dex */
public class bz implements cc {
    static a d;
    private k e;
    private BitmapDescriptor f;
    private LatLng g;
    private float h;
    private float i;
    private LatLngBounds j;
    private float k;
    private float l;
    private String r;
    private FloatBuffer t;

    /* renamed from: u  reason: collision with root package name */
    private int f7u;
    private boolean m = true;
    private float n = 0.0f;
    private float o = 1.0f;
    private float p = 0.5f;
    private float q = 0.5f;
    private FloatBuffer s = null;
    private boolean v = false;
    private boolean w = false;
    FPointBounds a = null;
    private List<Float> x = new ArrayList();
    private List<Float> y = new ArrayList();
    float[] b = null;
    int c = 10000;

    public bz(k kVar) {
        this.e = kVar;
        try {
            this.r = getId();
        } catch (RemoteException e) {
            gr.b(e, "GroundOverlayDelegateImp", "create");
            e.printStackTrace();
        }
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IOverlay
    public void remove() throws RemoteException {
        this.e.a(getId());
        this.e.setRunLowFrame(false);
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IOverlay
    public String getId() throws RemoteException {
        if (this.r == null) {
            this.r = this.e.c("GroundOverlay");
        }
        return this.r;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IOverlay
    public void setZIndex(float f) throws RemoteException {
        this.l = f;
        this.e.f();
        this.e.setRunLowFrame(false);
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IOverlay
    public float getZIndex() throws RemoteException {
        return this.l;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IOverlay
    public void setVisible(boolean z) throws RemoteException {
        this.m = z;
        this.e.setRunLowFrame(false);
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IOverlay
    public boolean isVisible() throws RemoteException {
        return this.m;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IOverlay
    public boolean equalsRemote(IOverlay iOverlay) throws RemoteException {
        return equals(iOverlay) || iOverlay.getId().equals(getId());
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IOverlay
    public int hashCodeRemote() throws RemoteException {
        return super.hashCode();
    }

    public boolean b() throws RemoteException {
        this.w = false;
        if (this.g == null) {
            g();
            return true;
        } else if (this.j == null) {
            f();
            return true;
        } else {
            h();
            return true;
        }
    }

    private void f() {
        if (this.g != null) {
            double cos = this.h / ((6371000.79d * Math.cos(this.g.latitude * 0.01745329251994329d)) * 0.01745329251994329d);
            double d2 = this.i / 111194.94043265979d;
            try {
                this.j = new LatLngBounds(new LatLng(this.g.latitude - ((1.0f - this.q) * d2), this.g.longitude - (this.p * cos)), new LatLng((d2 * this.q) + this.g.latitude, (cos * (1.0f - this.p)) + this.g.longitude));
            } catch (Throwable th) {
                th.printStackTrace();
            }
            h();
        }
    }

    private void g() {
        if (this.j != null) {
            LatLng latLng = this.j.southwest;
            LatLng latLng2 = this.j.northeast;
            this.g = new LatLng(latLng.latitude + ((1.0f - this.q) * (latLng2.latitude - latLng.latitude)), latLng.longitude + (this.p * (latLng2.longitude - latLng.longitude)));
            this.h = (float) (6371000.79d * Math.cos(this.g.latitude * 0.01745329251994329d) * (latLng2.longitude - latLng.longitude) * 0.01745329251994329d);
            this.i = (float) ((latLng2.latitude - latLng.latitude) * 6371000.79d * 0.01745329251994329d);
            h();
        }
    }

    private synchronized void h() {
        if (this.j != null) {
            this.b = new float[16];
            IPoint obtain = IPoint.obtain();
            IPoint obtain2 = IPoint.obtain();
            IPoint obtain3 = IPoint.obtain();
            IPoint obtain4 = IPoint.obtain();
            GLMapState.lonlat2Geo(this.j.southwest.longitude, this.j.southwest.latitude, obtain);
            GLMapState.lonlat2Geo(this.j.northeast.longitude, this.j.southwest.latitude, obtain2);
            GLMapState.lonlat2Geo(this.j.northeast.longitude, this.j.northeast.latitude, obtain3);
            GLMapState.lonlat2Geo(this.j.southwest.longitude, this.j.northeast.latitude, obtain4);
            if (this.k != 0.0f) {
                double d2 = obtain2.x - obtain.x;
                double d3 = obtain2.y - obtain3.y;
                DPoint obtain5 = DPoint.obtain();
                obtain5.x = obtain.x + (this.p * d2);
                obtain5.y = obtain.y - ((1.0f - this.q) * d3);
                a(obtain5, 0.0d, 0.0d, d2, d3, obtain);
                a(obtain5, d2, 0.0d, d2, d3, obtain2);
                a(obtain5, d2, d3, d2, d3, obtain3);
                a(obtain5, 0.0d, d3, d2, d3, obtain4);
                obtain5.recycle();
            }
            this.b[0] = obtain.x / this.c;
            this.b[1] = obtain.y / this.c;
            this.b[2] = obtain.x % this.c;
            this.b[3] = obtain.y % this.c;
            this.b[4] = obtain2.x / this.c;
            this.b[5] = obtain2.y / this.c;
            this.b[6] = obtain2.x % this.c;
            this.b[7] = obtain2.y % this.c;
            this.b[8] = obtain3.x / this.c;
            this.b[9] = obtain3.y / this.c;
            this.b[10] = obtain3.x % this.c;
            this.b[11] = obtain3.y % this.c;
            this.b[12] = obtain4.x / this.c;
            this.b[13] = obtain4.y / this.c;
            this.b[14] = obtain4.x % this.c;
            this.b[15] = obtain4.y % this.c;
            if (this.s == null) {
                this.s = dt.a(this.b);
            } else {
                this.s = dt.a(this.b, this.s);
            }
            obtain4.recycle();
            obtain.recycle();
            obtain2.recycle();
            obtain3.recycle();
        }
    }

    private void a(DPoint dPoint, double d2, double d3, double d4, double d5, IPoint iPoint) {
        double d6 = d2 - (this.p * d4);
        double d7 = ((1.0f - this.q) * d5) - d3;
        double d8 = (-this.k) * 0.01745329251994329d;
        iPoint.x = (int) (dPoint.x + (Math.cos(d8) * d6) + (Math.sin(d8) * d7));
        iPoint.y = (int) (((d7 * Math.cos(d8)) - (d6 * Math.sin(d8))) + dPoint.y);
    }

    private void i() {
        if (this.f != null || (this.f != null && this.f.getBitmap() != null)) {
            int width = this.f.getWidth();
            int height = this.f.getHeight();
            int height2 = this.f.getBitmap().getHeight();
            float width2 = width / this.f.getBitmap().getWidth();
            float f = height / height2;
            this.t = dt.a(new float[]{0.0f, f, width2, f, width2, 0.0f, 0.0f, 0.0f});
        }
    }

    @Override // com.amap.api.col.cg
    public void c() throws RemoteException {
        if (!this.m) {
            return;
        }
        if ((this.g != null || this.j != null) && this.f != null) {
            if (this.b == null) {
                b();
            }
            if (!this.v) {
                Bitmap bitmap = this.f.getBitmap();
                if (bitmap != null && !bitmap.isRecycled()) {
                    if (this.f7u == 0) {
                        int[] iArr = {0};
                        GLES20.glGenTextures(1, iArr, 0);
                        this.f7u = iArr[0];
                    } else {
                        GLES20.glDeleteTextures(1, new int[]{this.f7u}, 0);
                    }
                    dt.b(this.f7u, bitmap, false);
                }
                this.v = true;
            }
            if (this.h != 0.0f || this.i != 0.0f) {
                a(this.f7u, this.s, this.t);
                this.w = true;
            }
        }
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IOverlay
    public void destroy() {
        Bitmap bitmap;
        try {
            remove();
            if (!(this.f == null || (bitmap = this.f.getBitmap()) == null)) {
                bitmap.recycle();
                this.f = null;
            }
            if (this.t != null) {
                this.t.clear();
                this.t = null;
            }
            if (this.s != null) {
                this.s.clear();
                this.s = null;
            }
            this.g = null;
            this.j = null;
        } catch (Throwable th) {
            gr.b(th, "GroundOverlayDelegateImp", EMPrivateConstant.EMMultiUserConstant.ITEM_DESTROY);
            th.printStackTrace();
        }
    }

    @Override // com.amap.api.col.cg
    public boolean a() {
        return true;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IGroundOverlay
    public void setPosition(LatLng latLng) throws RemoteException {
        this.g = latLng;
        f();
        this.e.setRunLowFrame(false);
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IGroundOverlay
    public LatLng getPosition() throws RemoteException {
        return this.g;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IGroundOverlay
    public void setDimensions(float f) throws RemoteException {
        if (f <= 0.0f) {
            Log.w("GroundOverlayDelegateImp", "Width must be non-negative");
        }
        if (!this.v || this.h == f) {
            this.h = f;
            this.i = f;
        } else {
            this.h = f;
            this.i = f;
            f();
        }
        this.e.setRunLowFrame(false);
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IGroundOverlay
    public void setDimensions(float f, float f2) throws RemoteException {
        if (f <= 0.0f || f2 <= 0.0f) {
            Log.w("GroundOverlayDelegateImp", "Width and Height must be non-negative");
        }
        if (!this.v || this.h == f || this.i == f2) {
            this.h = f;
            this.i = f2;
        } else {
            this.h = f;
            this.i = f2;
            f();
        }
        this.e.setRunLowFrame(false);
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IGroundOverlay
    public float getWidth() throws RemoteException {
        return this.h;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IGroundOverlay
    public float getHeight() throws RemoteException {
        return this.i;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IGroundOverlay
    public void setPositionFromBounds(LatLngBounds latLngBounds) throws RemoteException {
        this.j = latLngBounds;
        g();
        this.e.setRunLowFrame(false);
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IGroundOverlay
    public LatLngBounds getBounds() throws RemoteException {
        return this.j;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IGroundOverlay
    public void setBearing(float f) throws RemoteException {
        float f2 = ((f % 360.0f) + 360.0f) % 360.0f;
        if (Math.abs(this.k - f2) > 1.0E-7d) {
            this.k = f2;
            h();
        }
        this.e.setRunLowFrame(false);
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IGroundOverlay
    public float getBearing() throws RemoteException {
        return this.k;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IGroundOverlay
    public void setTransparency(float f) throws RemoteException {
        if (f < 0.0f) {
            Log.w("GroundOverlayDelegateImp", "Transparency must be in the range [0..1]");
        }
        this.n = f;
        this.o = 1.0f - f;
        this.e.setRunLowFrame(false);
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IGroundOverlay
    public float getTransparency() throws RemoteException {
        return this.n;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IGroundOverlay
    public void setImage(BitmapDescriptor bitmapDescriptor) throws RemoteException {
        if (bitmapDescriptor != null && bitmapDescriptor.getBitmap() != null && !bitmapDescriptor.getBitmap().isRecycled()) {
            this.f = bitmapDescriptor;
            i();
            if (this.v) {
                this.v = false;
            }
            this.e.setRunLowFrame(false);
        }
    }

    public void a(float f, float f2) throws RemoteException {
        this.p = f;
        this.q = f2;
        this.e.setRunLowFrame(false);
    }

    @Override // com.amap.api.col.cg
    public boolean d() {
        return this.w;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IOverlay
    public boolean isAboveMaskLayer() {
        return false;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IOverlay
    public void setAboveMaskLayer(boolean z) {
    }

    public static void e() {
        d = new a("texture_layer.glsl");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: GroundOverlayDelegateImp.java */
    /* loaded from: classes.dex */
    public static class a extends cu {
        int a;
        int b;
        int c;
        int d;
        int e;

        a(String str) {
            if (a(str)) {
                this.a = c("aMVP");
                this.b = b("aVertex");
                this.c = b("aTextureCoord");
                this.d = c("aTransform");
                this.e = c("aColor");
            }
        }
    }

    private void a(int i, FloatBuffer floatBuffer, FloatBuffer floatBuffer2) {
        if (this.b != null && floatBuffer != null && floatBuffer2 != null) {
            d.b();
            GLES20.glEnable(3042);
            GLES20.glBlendFunc(1, 771);
            GLES20.glBlendColor(1.0f * this.o, 1.0f * this.o, 1.0f * this.o, this.o);
            GLES20.glBindTexture(3553, i);
            GLES20.glEnableVertexAttribArray(d.b);
            GLES20.glVertexAttribPointer(d.b, 4, 5126, false, 16, (Buffer) floatBuffer);
            GLES20.glEnableVertexAttribArray(d.c);
            GLES20.glVertexAttribPointer(d.c, 2, 5126, false, 8, (Buffer) floatBuffer2);
            GLES20.glUniform4f(d.d, this.e.getMapConfig().getS_x() / this.c, this.e.getMapConfig().getS_y() / this.c, this.e.getMapConfig().getS_x() % this.c, this.e.getMapConfig().getS_y() % this.c);
            GLES20.glUniform4f(d.e, 1.0f * this.o, 1.0f * this.o, 1.0f * this.o, this.o);
            GLES20.glUniformMatrix4fv(d.a, 1, false, this.e.u(), 0);
            GLES20.glDrawArrays(6, 0, 4);
            GLES20.glBindTexture(3553, 0);
            GLES20.glDisableVertexAttribArray(d.b);
            GLES20.glDisableVertexAttribArray(d.c);
            GLES20.glDisable(3042);
            GLES20.glUseProgram(0);
        }
    }
}
