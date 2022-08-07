package com.amap.api.services.district;

import android.content.Context;
import com.amap.api.services.a.am;
import com.amap.api.services.a.av;
import com.amap.api.services.a.cf;
import com.amap.api.services.a.h;
import com.amap.api.services.core.AMapException;
import com.amap.api.services.interfaces.IDistrictSearch;

/* loaded from: classes.dex */
public class DistrictSearch {
    private IDistrictSearch a;

    /* loaded from: classes.dex */
    public interface OnDistrictSearchListener {
        void onDistrictSearched(DistrictResult districtResult);
    }

    public DistrictSearch(Context context) {
        try {
            this.a = (IDistrictSearch) cf.a(context, h.a(true), "com.amap.api.services.dynamic.DistrictSearchWrapper", am.class, new Class[]{Context.class}, new Object[]{context});
        } catch (av e) {
            e.printStackTrace();
        }
        if (this.a == null) {
            try {
                this.a = new am(context);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    public DistrictSearchQuery getQuery() {
        if (this.a != null) {
            return this.a.getQuery();
        }
        return null;
    }

    public void setQuery(DistrictSearchQuery districtSearchQuery) {
        if (this.a != null) {
            this.a.setQuery(districtSearchQuery);
        }
    }

    public DistrictResult searchDistrict() throws AMapException {
        if (this.a != null) {
            return this.a.searchDistrict();
        }
        return null;
    }

    public void searchDistrictAsyn() {
        if (this.a != null) {
            this.a.searchDistrictAsyn();
        }
    }

    public void searchDistrictAnsy() {
        if (this.a != null) {
            this.a.searchDistrictAnsy();
        }
    }

    public void setOnDistrictSearchListener(OnDistrictSearchListener onDistrictSearchListener) {
        if (this.a != null) {
            this.a.setOnDistrictSearchListener(onDistrictSearchListener);
        }
    }
}
