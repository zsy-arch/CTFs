package com.amap.api.services.busline;

import android.content.Context;
import com.amap.api.services.a.aj;
import com.amap.api.services.a.av;
import com.amap.api.services.a.cf;
import com.amap.api.services.a.h;
import com.amap.api.services.core.AMapException;
import com.amap.api.services.interfaces.IBusLineSearch;

/* loaded from: classes.dex */
public class BusLineSearch {
    private IBusLineSearch a;

    /* loaded from: classes.dex */
    public interface OnBusLineSearchListener {
        void onBusLineSearched(BusLineResult busLineResult, int i);
    }

    public BusLineSearch(Context context, BusLineQuery busLineQuery) {
        this.a = null;
        try {
            this.a = (IBusLineSearch) cf.a(context, h.a(true), "com.amap.api.services.dynamic.BusLineSearchWrapper", aj.class, new Class[]{Context.class, BusLineQuery.class}, new Object[]{context, busLineQuery});
        } catch (av e) {
            e.printStackTrace();
        }
        if (this.a == null) {
            try {
                this.a = new aj(context, busLineQuery);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    public BusLineResult searchBusLine() throws AMapException {
        if (this.a != null) {
            return this.a.searchBusLine();
        }
        return null;
    }

    public void setOnBusLineSearchListener(OnBusLineSearchListener onBusLineSearchListener) {
        if (this.a != null) {
            this.a.setOnBusLineSearchListener(onBusLineSearchListener);
        }
    }

    public void searchBusLineAsyn() {
        if (this.a != null) {
            this.a.searchBusLineAsyn();
        }
    }

    public void setQuery(BusLineQuery busLineQuery) {
        if (this.a != null) {
            this.a.setQuery(busLineQuery);
        }
    }

    public BusLineQuery getQuery() {
        if (this.a != null) {
            return this.a.getQuery();
        }
        return null;
    }
}
