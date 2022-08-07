package com.amap.api.col;

import android.os.RemoteException;
import com.amap.api.maps.AMap;
import com.amap.api.maps.model.MultiPointItem;
import com.amap.api.maps.model.MultiPointOverlayOptions;
import com.autonavi.amap.mapcore.IPoint;
import com.autonavi.amap.mapcore.MapConfig;
import com.autonavi.amap.mapcore.interfaces.IMultiPointOverlay;
import java.util.ArrayList;
import java.util.List;

/* compiled from: MultiPointOverlayManagerLayer.java */
/* loaded from: classes.dex */
public class ah {
    private List<IMultiPointOverlay> a = new ArrayList();
    private AMap.OnMultiPointClickListener b;
    private k c;

    public ah(k kVar) {
        this.c = kVar;
    }

    public synchronized void a() {
        this.b = null;
        synchronized (this.a) {
            for (IMultiPointOverlay iMultiPointOverlay : this.a) {
                iMultiPointOverlay.destroy(false);
            }
            this.a.clear();
        }
    }

    public synchronized void b() {
        synchronized (this.a) {
            this.a.clear();
        }
    }

    public synchronized IMultiPointOverlay a(MultiPointOverlayOptions multiPointOverlayOptions) throws RemoteException {
        ag agVar;
        if (multiPointOverlayOptions == null) {
            agVar = null;
        } else {
            agVar = new ag(multiPointOverlayOptions, this);
            a((IMultiPointOverlay) agVar);
        }
        return agVar;
    }

    private void a(IMultiPointOverlay iMultiPointOverlay) throws RemoteException {
        synchronized (this.a) {
            this.a.add(iMultiPointOverlay);
        }
    }

    public void a(MapConfig mapConfig, float[] fArr, float[] fArr2) {
        try {
            synchronized (this.a) {
                for (IMultiPointOverlay iMultiPointOverlay : this.a) {
                    iMultiPointOverlay.draw(mapConfig, fArr, fArr2);
                }
            }
        } catch (Throwable th) {
            gr.b(th, "MultiPointOverlayManagerLayer", "draw");
            th.printStackTrace();
        }
    }

    public boolean a(IPoint iPoint) {
        MultiPointItem onClick;
        boolean z;
        if (this.b == null) {
            return false;
        }
        synchronized (this.a) {
            for (IMultiPointOverlay iMultiPointOverlay : this.a) {
                if (!(iMultiPointOverlay == null || (onClick = iMultiPointOverlay.onClick(iPoint)) == null)) {
                    if (this.b != null) {
                        z = this.b.onPointClick(onClick);
                    } else {
                        z = false;
                    }
                    return z;
                }
            }
            return false;
        }
    }

    public void a(AMap.OnMultiPointClickListener onMultiPointClickListener) {
        this.b = onMultiPointClickListener;
    }

    public void c() {
        if (this.c != null) {
            this.c.setRunLowFrame(false);
        }
    }

    public void a(ag agVar) {
        this.a.remove(agVar);
    }
}
