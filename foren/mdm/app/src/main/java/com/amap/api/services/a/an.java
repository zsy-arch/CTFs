package com.amap.api.services.a;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import com.amap.api.services.a.q;
import com.amap.api.services.core.AMapException;
import com.amap.api.services.geocoder.GeocodeAddress;
import com.amap.api.services.geocoder.GeocodeQuery;
import com.amap.api.services.geocoder.GeocodeResult;
import com.amap.api.services.geocoder.GeocodeSearch;
import com.amap.api.services.geocoder.RegeocodeAddress;
import com.amap.api.services.geocoder.RegeocodeQuery;
import com.amap.api.services.geocoder.RegeocodeResult;
import com.amap.api.services.interfaces.IGeocodeSearch;
import java.util.List;

/* compiled from: GeocodeSearchCore.java */
/* loaded from: classes.dex */
public final class an implements IGeocodeSearch {
    private Context a;
    private GeocodeSearch.OnGeocodeSearchListener b;
    private Handler c = q.a();

    public an(Context context) {
        this.a = context.getApplicationContext();
    }

    @Override // com.amap.api.services.interfaces.IGeocodeSearch
    public RegeocodeAddress getFromLocation(RegeocodeQuery regeocodeQuery) throws AMapException {
        try {
            o.a(this.a);
            if (a(regeocodeQuery)) {
                return new aa(this.a, regeocodeQuery).a();
            }
            throw new AMapException("无效的参数 - IllegalArgumentException");
        } catch (AMapException e) {
            i.a(e, "GeocodeSearch", "getFromLocationAsyn");
            throw e;
        }
    }

    @Override // com.amap.api.services.interfaces.IGeocodeSearch
    public List<GeocodeAddress> getFromLocationName(GeocodeQuery geocodeQuery) throws AMapException {
        try {
            o.a(this.a);
            if (geocodeQuery != null) {
                return new l(this.a, geocodeQuery).a();
            }
            throw new AMapException("无效的参数 - IllegalArgumentException");
        } catch (AMapException e) {
            i.a(e, "GeocodeSearch", "getFromLocationName");
            throw e;
        }
    }

    @Override // com.amap.api.services.interfaces.IGeocodeSearch
    public void setOnGeocodeSearchListener(GeocodeSearch.OnGeocodeSearchListener onGeocodeSearchListener) {
        this.b = onGeocodeSearchListener;
    }

    @Override // com.amap.api.services.interfaces.IGeocodeSearch
    public void getFromLocationAsyn(final RegeocodeQuery regeocodeQuery) {
        try {
            new Thread(new Runnable() { // from class: com.amap.api.services.a.an.1
                @Override // java.lang.Runnable
                public void run() {
                    Message obtainMessage = q.a().obtainMessage();
                    try {
                        obtainMessage.arg1 = 2;
                        obtainMessage.what = 201;
                        q.i iVar = new q.i();
                        iVar.b = an.this.b;
                        obtainMessage.obj = iVar;
                        iVar.a = new RegeocodeResult(regeocodeQuery, an.this.getFromLocation(regeocodeQuery));
                        obtainMessage.arg2 = 1000;
                    } catch (AMapException e) {
                        obtainMessage.arg2 = e.getErrorCode();
                    } finally {
                        an.this.c.sendMessage(obtainMessage);
                    }
                }
            }).start();
        } catch (Throwable th) {
            i.a(th, "GeocodeSearch", "getFromLocationAsyn_threadcreate");
        }
    }

    @Override // com.amap.api.services.interfaces.IGeocodeSearch
    public void getFromLocationNameAsyn(final GeocodeQuery geocodeQuery) {
        try {
            new Thread(new Runnable() { // from class: com.amap.api.services.a.an.2
                @Override // java.lang.Runnable
                public void run() {
                    Message obtainMessage = q.a().obtainMessage();
                    try {
                        obtainMessage.what = 200;
                        obtainMessage.arg1 = 2;
                        obtainMessage.arg2 = 1000;
                        q.e eVar = new q.e();
                        eVar.b = an.this.b;
                        obtainMessage.obj = eVar;
                        eVar.a = new GeocodeResult(geocodeQuery, an.this.getFromLocationName(geocodeQuery));
                    } catch (AMapException e) {
                        obtainMessage.arg2 = e.getErrorCode();
                    } finally {
                        an.this.c.sendMessage(obtainMessage);
                    }
                }
            }).start();
        } catch (Throwable th) {
            i.a(th, "GeocodeSearch", "getFromLocationNameAsynThrowable");
        }
    }

    private boolean a(RegeocodeQuery regeocodeQuery) {
        if (regeocodeQuery == null || regeocodeQuery.getPoint() == null || regeocodeQuery.getLatLonType() == null) {
            return false;
        }
        return true;
    }
}
