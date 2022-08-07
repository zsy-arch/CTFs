package com.amap.api.services.weather;

import android.content.Context;
import com.amap.api.services.a.au;
import com.amap.api.services.a.av;
import com.amap.api.services.a.cf;
import com.amap.api.services.a.h;
import com.amap.api.services.interfaces.IWeatherSearch;

/* loaded from: classes.dex */
public class WeatherSearch {
    private IWeatherSearch a;

    /* loaded from: classes.dex */
    public interface OnWeatherSearchListener {
        void onWeatherForecastSearched(LocalWeatherForecastResult localWeatherForecastResult, int i);

        void onWeatherLiveSearched(LocalWeatherLiveResult localWeatherLiveResult, int i);
    }

    public WeatherSearch(Context context) {
        this.a = null;
        try {
            this.a = (IWeatherSearch) cf.a(context, h.a(true), "com.amap.api.services.dynamic.WeatherSearchWrapper", au.class, new Class[]{Context.class}, new Object[]{context});
        } catch (av e) {
            e.printStackTrace();
        }
        if (this.a == null) {
            try {
                this.a = new au(context);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    public WeatherSearchQuery getQuery() {
        if (this.a != null) {
            return this.a.getQuery();
        }
        return null;
    }

    public void setQuery(WeatherSearchQuery weatherSearchQuery) {
        if (this.a != null) {
            this.a.setQuery(weatherSearchQuery);
        }
    }

    public void searchWeatherAsyn() {
        if (this.a != null) {
            this.a.searchWeatherAsyn();
        }
    }

    public void setOnWeatherSearchListener(OnWeatherSearchListener onWeatherSearchListener) {
        if (this.a != null) {
            this.a.setOnWeatherSearchListener(onWeatherSearchListener);
        }
    }
}
