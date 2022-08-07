package cn.jpush.android.a;

import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class h implements LocationListener {
    final /* synthetic */ g a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public h(g gVar) {
        this.a = gVar;
    }

    @Override // android.location.LocationListener
    public final void onLocationChanged(Location location) {
        this.a.a(location);
    }

    @Override // android.location.LocationListener
    public final void onProviderDisabled(String str) {
        this.a.a(null);
        this.a.c();
    }

    @Override // android.location.LocationListener
    public final void onProviderEnabled(String str) {
        this.a.b();
    }

    @Override // android.location.LocationListener
    public final void onStatusChanged(String str, int i, Bundle bundle) {
    }
}
