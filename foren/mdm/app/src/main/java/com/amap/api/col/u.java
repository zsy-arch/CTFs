package com.amap.api.col;

import android.content.Context;
import android.os.RemoteException;
import com.amap.api.maps.model.CameraPosition;
import com.amap.api.maps.model.TileOverlay;
import com.amap.api.maps.model.TileOverlayOptions;
import com.lidroid.xutils.bitmap.BitmapGlobalConfig;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;

/* compiled from: TileOverlayView.java */
/* loaded from: classes.dex */
public class u {
    ct d;
    private k f;
    private Context g;
    List<cl> a = new ArrayList();
    a b = new a();
    List<Integer> c = new ArrayList();
    float[] e = new float[16];

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: TileOverlayView.java */
    /* loaded from: classes.dex */
    public static class a implements Serializable, Comparator<Object> {
        a() {
        }

        @Override // java.util.Comparator
        public int compare(Object obj, Object obj2) {
            cl clVar = (cl) obj;
            cl clVar2 = (cl) obj2;
            if (!(clVar == null || clVar2 == null)) {
                try {
                    if (clVar.getZIndex() > clVar2.getZIndex()) {
                        return 1;
                    }
                    if (clVar.getZIndex() < clVar2.getZIndex()) {
                        return -1;
                    }
                } catch (Throwable th) {
                    gr.b(th, "TileOverlayView", "compare");
                    th.printStackTrace();
                }
            }
            return 0;
        }
    }

    public u(Context context, k kVar) {
        this.d = null;
        this.f = kVar;
        this.g = context;
        TileOverlayOptions tileProvider = new TileOverlayOptions().tileProvider(new dc(256, 256) { // from class: com.amap.api.col.u.1
            @Override // com.amap.api.col.dc
            public String a(int i, int i2, int i3) {
                try {
                    return String.format(Locale.US, "http://grid.amap.com/grid/%d/%d/%d?dpiType=webrd&lang=zh_cn&pack=%s&ds=0", Integer.valueOf(i3), Integer.valueOf(i), Integer.valueOf(i2), g.b);
                } catch (Throwable th) {
                    return null;
                }
            }
        });
        tileProvider.memCacheSize(BitmapGlobalConfig.MIN_DISK_CACHE_SIZE);
        tileProvider.diskCacheSize(20480);
        this.d = new ct(tileProvider, this, true);
    }

    public k a() {
        return this.f;
    }

    public void b() {
        try {
            for (Integer num : this.c) {
                dt.b(num.intValue());
            }
            this.c.clear();
            if (g.c == 0 && this.d != null) {
                this.d.a();
            }
            synchronized (this.a) {
                int size = this.a.size();
                for (int i = 0; i < size; i++) {
                    cl clVar = this.a.get(i);
                    if (clVar.isVisible()) {
                        clVar.a();
                    }
                }
            }
        } catch (Throwable th) {
        }
    }

    public void c() {
        synchronized (this.a) {
            int size = this.a.size();
            for (int i = 0; i < size; i++) {
                cl clVar = this.a.get(i);
                if (clVar != null) {
                    clVar.destroy(false);
                }
            }
            this.a.clear();
        }
    }

    public void d() {
        synchronized (this.a) {
            Collections.sort(this.a, this.b);
        }
    }

    public TileOverlay a(TileOverlayOptions tileOverlayOptions) throws RemoteException {
        if (tileOverlayOptions == null || tileOverlayOptions.getTileProvider() == null) {
            return null;
        }
        try {
            ct ctVar = new ct(tileOverlayOptions, this);
            a(ctVar);
            ctVar.a(true);
            this.f.setRunLowFrame(false);
            return new TileOverlay(ctVar);
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
    }

    public void a(cl clVar) {
        synchronized (this.a) {
            b(clVar);
            this.a.add(clVar);
        }
        d();
    }

    public boolean b(cl clVar) {
        boolean remove;
        synchronized (this.a) {
            remove = this.a.remove(clVar);
        }
        return remove;
    }

    public void a(boolean z) {
        try {
            if (g.c == 0) {
                CameraPosition cameraPosition = this.f.getCameraPosition();
                if (cameraPosition == null || cameraPosition.zoom <= 10.0f || !cameraPosition.isAbroad || this.f.getMapType() != 1) {
                    if (this.d != null) {
                        this.d.b();
                    }
                } else if (this.d != null) {
                    this.d.a(z);
                }
            }
            synchronized (this.a) {
                int size = this.a.size();
                for (int i = 0; i < size; i++) {
                    cl clVar = this.a.get(i);
                    if (clVar != null && clVar.isVisible()) {
                        clVar.a(z);
                    }
                }
            }
        } catch (Throwable th) {
            gr.b(th, "TileOverlayView", "refresh");
        }
    }

    public void b(boolean z) {
        if (this.d != null) {
            this.d.b(z);
        }
        synchronized (this.a) {
            int size = this.a.size();
            for (int i = 0; i < size; i++) {
                cl clVar = this.a.get(i);
                if (clVar != null) {
                    clVar.b(z);
                }
            }
        }
    }

    public Context e() {
        return this.g;
    }

    public void a(int i) {
        this.c.add(Integer.valueOf(i));
    }

    public void f() {
        c();
        if (this.d != null) {
            this.d.remove();
        }
        this.d = null;
    }

    public float[] g() {
        return this.f != null ? this.f.u() : this.e;
    }
}
