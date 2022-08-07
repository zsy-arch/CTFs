package com.amap.api.services.busline;

import android.content.Context;
import com.amap.api.services.a.ak;
import com.amap.api.services.a.av;
import com.amap.api.services.a.cf;
import com.amap.api.services.a.h;
import com.amap.api.services.core.AMapException;
import com.amap.api.services.interfaces.IBusStationSearch;

/* loaded from: classes.dex */
public class BusStationSearch {
    private IBusStationSearch a;

    /* loaded from: classes.dex */
    public interface OnBusStationSearchListener {
        void onBusStationSearched(BusStationResult busStationResult, int i);
    }

    public BusStationSearch(Context context, BusStationQuery busStationQuery) {
        try {
            this.a = (IBusStationSearch) cf.a(context, h.a(true), "com.amap.api.services.dynamic.BusStationSearchWrapper", ak.class, new Class[]{Context.class, BusStationQuery.class}, new Object[]{context, busStationQuery});
        } catch (av e) {
            e.printStackTrace();
        }
        if (this.a == null) {
            try {
                this.a = new ak(context, busStationQuery);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    public BusStationResult searchBusStation() throws AMapException {
        if (this.a != null) {
            return this.a.searchBusStation();
        }
        return null;
    }

    public void setOnBusStationSearchListener(OnBusStationSearchListener onBusStationSearchListener) {
        if (this.a != null) {
            this.a.setOnBusStationSearchListener(onBusStationSearchListener);
        }
    }

    public void searchBusStationAsyn() {
        if (this.a != null) {
            this.a.searchBusStationAsyn();
        }
    }

    public void setQuery(BusStationQuery busStationQuery) {
        if (this.a != null) {
            this.a.setQuery(busStationQuery);
        }
    }

    public BusStationQuery getQuery() {
        if (this.a != null) {
            return this.a.getQuery();
        }
        return null;
    }
}
