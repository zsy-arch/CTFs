package com.amap.api.col;

import android.opengl.GLES20;
import android.os.Handler;
import android.os.Looper;
import android.os.RemoteException;
import com.amap.api.maps.model.ArcOptions;
import com.amap.api.maps.model.CircleOptions;
import com.amap.api.maps.model.GroundOverlayOptions;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.NavigateArrowOptions;
import com.amap.api.maps.model.PolygonOptions;
import com.amap.api.maps.model.PolylineOptions;
import java.io.Serializable;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

/* compiled from: GLOverlayLayer.java */
/* loaded from: classes.dex */
public class i {
    k a;
    private int c = 0;
    private List<cg> d = new Vector(500);
    private List<Integer> e = new Vector();
    private Handler f = new Handler(Looper.getMainLooper());
    private Runnable g = new Runnable() { // from class: com.amap.api.col.i.1
        @Override // java.lang.Runnable
        public synchronized void run() {
            synchronized (i.this) {
                if (i.this.d != null && i.this.d.size() > 0) {
                    Collections.sort(i.this.d, i.this.b);
                }
            }
        }
    };
    a b = new a();

    public synchronized String a(String str) {
        this.c++;
        return str + this.c;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: GLOverlayLayer.java */
    /* loaded from: classes.dex */
    public static class a implements Serializable, Comparator<Object> {
        a() {
        }

        @Override // java.util.Comparator
        public int compare(Object obj, Object obj2) {
            cg cgVar = (cg) obj;
            cg cgVar2 = (cg) obj2;
            if (!(cgVar == null || cgVar2 == null)) {
                try {
                    if (cgVar.getZIndex() > cgVar2.getZIndex()) {
                        return 1;
                    }
                    if (cgVar.getZIndex() < cgVar2.getZIndex()) {
                        return -1;
                    }
                } catch (Throwable th) {
                    gr.b(th, "GLOverlayLayer", "compare");
                    th.printStackTrace();
                }
            }
            return 0;
        }
    }

    public i(k kVar) {
        this.a = kVar;
    }

    public synchronized void b(String str) {
        cg cgVar;
        if (str != null) {
            if (str.trim().length() != 0) {
                Iterator<cg> it = this.d.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        cgVar = null;
                        break;
                    }
                    cgVar = it.next();
                    if (str.equals(cgVar.getId())) {
                        break;
                    }
                }
                this.d.clear();
                if (cgVar != null) {
                    this.d.add(cgVar);
                }
            }
        }
        this.d.clear();
        a();
    }

    public synchronized void a() {
        this.c = 0;
    }

    public synchronized void b() {
        for (cg cgVar : this.d) {
            cgVar.destroy();
        }
        b(null);
    }

    synchronized cg c(String str) throws RemoteException {
        cg cgVar;
        Iterator<cg> it = this.d.iterator();
        while (true) {
            if (!it.hasNext()) {
                cgVar = null;
                break;
            }
            cgVar = it.next();
            if (cgVar != null && cgVar.getId().equals(str)) {
                break;
            }
        }
        return cgVar;
    }

    public synchronized cj a(PolylineOptions polylineOptions) throws RemoteException {
        cq cqVar;
        if (polylineOptions == null) {
            cqVar = null;
        } else {
            cqVar = new cq(this, polylineOptions);
            a(cqVar);
        }
        return cqVar;
    }

    public synchronized cf a(NavigateArrowOptions navigateArrowOptions) throws RemoteException {
        co coVar;
        if (navigateArrowOptions == null) {
            coVar = null;
        } else {
            coVar = new co(this.a);
            coVar.setTopColor(navigateArrowOptions.getTopColor());
            coVar.setPoints(navigateArrowOptions.getPoints());
            coVar.setVisible(navigateArrowOptions.isVisible());
            coVar.setWidth(navigateArrowOptions.getWidth());
            coVar.setZIndex(navigateArrowOptions.getZIndex());
            a(coVar);
        }
        return coVar;
    }

    public synchronized ci a(PolygonOptions polygonOptions) throws RemoteException {
        cp cpVar;
        if (polygonOptions == null) {
            cpVar = null;
        } else {
            cpVar = new cp(this.a);
            cpVar.setFillColor(polygonOptions.getFillColor());
            cpVar.setPoints(polygonOptions.getPoints());
            cpVar.setVisible(polygonOptions.isVisible());
            cpVar.setStrokeWidth(polygonOptions.getStrokeWidth());
            cpVar.setZIndex(polygonOptions.getZIndex());
            cpVar.setStrokeColor(polygonOptions.getStrokeColor());
            a(cpVar);
        }
        return cpVar;
    }

    public synchronized cb a(CircleOptions circleOptions) throws RemoteException {
        bx bxVar;
        if (circleOptions == null) {
            bxVar = null;
        } else {
            bxVar = new bx(this.a);
            bxVar.setFillColor(circleOptions.getFillColor());
            bxVar.setCenter(circleOptions.getCenter());
            bxVar.setVisible(circleOptions.isVisible());
            bxVar.setStrokeWidth(circleOptions.getStrokeWidth());
            bxVar.setZIndex(circleOptions.getZIndex());
            bxVar.setStrokeColor(circleOptions.getStrokeColor());
            bxVar.setRadius(circleOptions.getRadius());
            a(bxVar);
        }
        return bxVar;
    }

    public synchronized ca a(ArcOptions arcOptions) throws RemoteException {
        bw bwVar;
        if (arcOptions == null) {
            bwVar = null;
        } else {
            bwVar = new bw(this.a);
            bwVar.setStrokeColor(arcOptions.getStrokeColor());
            bwVar.a(arcOptions.getStart());
            bwVar.b(arcOptions.getPassed());
            bwVar.c(arcOptions.getEnd());
            bwVar.setVisible(arcOptions.isVisible());
            bwVar.setStrokeWidth(arcOptions.getStrokeWidth());
            bwVar.setZIndex(arcOptions.getZIndex());
            a(bwVar);
        }
        return bwVar;
    }

    public synchronized cc a(GroundOverlayOptions groundOverlayOptions) throws RemoteException {
        bz bzVar;
        if (groundOverlayOptions == null) {
            bzVar = null;
        } else {
            bzVar = new bz(this.a);
            bzVar.a(groundOverlayOptions.getAnchorU(), groundOverlayOptions.getAnchorV());
            bzVar.setDimensions(groundOverlayOptions.getWidth(), groundOverlayOptions.getHeight());
            bzVar.setImage(groundOverlayOptions.getImage());
            bzVar.setPosition(groundOverlayOptions.getLocation());
            bzVar.setPositionFromBounds(groundOverlayOptions.getBounds());
            bzVar.setBearing(groundOverlayOptions.getBearing());
            bzVar.setTransparency(groundOverlayOptions.getTransparency());
            bzVar.setVisible(groundOverlayOptions.isVisible());
            bzVar.setZIndex(groundOverlayOptions.getZIndex());
            a(bzVar);
        }
        return bzVar;
    }

    private void a(cg cgVar) throws RemoteException {
        this.d.add(cgVar);
        c();
    }

    public synchronized boolean d(String str) throws RemoteException {
        cg c;
        c = c(str);
        return c != null ? this.d.remove(c) : false;
    }

    public synchronized void c() {
        this.f.removeCallbacks(this.g);
        this.f.postDelayed(this.g, 10L);
    }

    public void a(boolean z, int i) {
        Iterator<Integer> it = this.e.iterator();
        while (it.hasNext()) {
            GLES20.glDeleteTextures(1, new int[]{it.next().intValue()}, 0);
        }
        this.e.clear();
        int size = this.d.size();
        for (cg cgVar : this.d) {
            try {
                if (cgVar.isVisible()) {
                    if (size > 20) {
                        if (cgVar.a()) {
                            if (z) {
                                if (cgVar.getZIndex() <= i) {
                                    cgVar.c();
                                }
                            } else if (cgVar.getZIndex() > i) {
                                cgVar.c();
                            }
                        }
                    } else if (z) {
                        if (cgVar.getZIndex() <= i) {
                            cgVar.c();
                        }
                    } else if (cgVar.getZIndex() > i) {
                        cgVar.c();
                    }
                }
            } catch (RemoteException e) {
                gr.b(e, "GLOverlayLayer", "draw");
                e.printStackTrace();
            }
        }
    }

    public void a(Integer num) {
        if (num.intValue() != 0) {
            this.e.add(num);
        }
    }

    public synchronized cg a(LatLng latLng) {
        cg cgVar;
        Iterator<cg> it = this.d.iterator();
        while (true) {
            if (!it.hasNext()) {
                cgVar = null;
                break;
            }
            cgVar = it.next();
            if (cgVar != null && cgVar.d() && (cgVar instanceof cj) && ((cj) cgVar).a(latLng)) {
                break;
            }
        }
        return cgVar;
    }

    public k d() {
        return this.a;
    }

    public float[] e() {
        return this.a != null ? this.a.u() : new float[16];
    }
}
