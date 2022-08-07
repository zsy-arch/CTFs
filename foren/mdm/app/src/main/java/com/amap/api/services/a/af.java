package com.amap.api.services.a;

import android.content.Context;
import com.amap.api.services.core.AMapException;
import com.amap.api.services.weather.LocalWeatherForecast;
import com.amap.api.services.weather.WeatherSearchQuery;

/* compiled from: WeatherForecastHandler.java */
/* loaded from: classes.dex */
public class af extends ah<WeatherSearchQuery, LocalWeatherForecast> {
    private LocalWeatherForecast h = new LocalWeatherForecast();

    @Override // com.amap.api.services.a.ah, com.amap.api.services.a.cz
    public /* bridge */ /* synthetic */ String g() {
        return super.g();
    }

    public af(Context context, WeatherSearchQuery weatherSearchQuery) {
        super(context, weatherSearchQuery);
    }

    @Override // com.amap.api.services.a.b
    protected String e() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("output=json");
        String city = ((WeatherSearchQuery) this.a).getCity();
        if (!n.i(city)) {
            stringBuffer.append("&city=").append(b(city));
        }
        stringBuffer.append("&extensions=all");
        stringBuffer.append("&key=" + aw.f(this.d));
        return stringBuffer.toString();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: d */
    public LocalWeatherForecast a(String str) throws AMapException {
        this.h = n.e(str);
        return this.h;
    }
}
