package com.amap.api.col;

import android.location.Location;
import com.amap.api.maps.LocationSource;

/* compiled from: AMapOnLocationChangedListener.java */
/* loaded from: classes.dex */
class e implements LocationSource.OnLocationChangedListener {
    Location a;
    private k b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public e(k kVar) {
        this.b = kVar;
    }

    @Override // com.amap.api.maps.LocationSource.OnLocationChangedListener
    public void onLocationChanged(Location location) {
        this.a = location;
        try {
            if (this.b.isMyLocationEnabled()) {
                this.b.a(location);
            }
        } catch (Throwable th) {
            gr.b(th, "AMapOnLocationChangedListener", "onLocationChanged");
            th.printStackTrace();
        }
    }
}
