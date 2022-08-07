package com.amap.api.services.geocoder;

import android.content.Context;
import com.amap.api.services.a.an;
import com.amap.api.services.a.av;
import com.amap.api.services.a.cf;
import com.amap.api.services.a.h;
import com.amap.api.services.core.AMapException;
import com.amap.api.services.interfaces.IGeocodeSearch;
import java.util.List;

/* loaded from: classes.dex */
public final class GeocodeSearch {
    public static final String AMAP = "autonavi";
    public static final String GPS = "gps";
    private IGeocodeSearch a;

    /* loaded from: classes.dex */
    public interface OnGeocodeSearchListener {
        void onGeocodeSearched(GeocodeResult geocodeResult, int i);

        void onRegeocodeSearched(RegeocodeResult regeocodeResult, int i);
    }

    public GeocodeSearch(Context context) {
        try {
            this.a = (IGeocodeSearch) cf.a(context, h.a(true), "com.amap.api.services.dynamic.GeocodeSearchWrapper", an.class, new Class[]{Context.class}, new Object[]{context});
        } catch (av e) {
            e.printStackTrace();
        }
        if (this.a == null) {
            try {
                this.a = new an(context);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    public RegeocodeAddress getFromLocation(RegeocodeQuery regeocodeQuery) throws AMapException {
        if (this.a != null) {
            return this.a.getFromLocation(regeocodeQuery);
        }
        return null;
    }

    public List<GeocodeAddress> getFromLocationName(GeocodeQuery geocodeQuery) throws AMapException {
        if (this.a != null) {
            return this.a.getFromLocationName(geocodeQuery);
        }
        return null;
    }

    public void setOnGeocodeSearchListener(OnGeocodeSearchListener onGeocodeSearchListener) {
        if (this.a != null) {
            this.a.setOnGeocodeSearchListener(onGeocodeSearchListener);
        }
    }

    public void getFromLocationAsyn(RegeocodeQuery regeocodeQuery) {
        if (this.a != null) {
            this.a.getFromLocationAsyn(regeocodeQuery);
        }
    }

    public void getFromLocationNameAsyn(GeocodeQuery geocodeQuery) {
        if (this.a != null) {
            this.a.getFromLocationNameAsyn(geocodeQuery);
        }
    }
}
