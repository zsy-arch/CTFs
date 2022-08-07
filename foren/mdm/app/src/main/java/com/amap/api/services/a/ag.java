package com.amap.api.services.a;

import android.content.Context;
import com.amap.api.services.core.AMapException;
import com.amap.api.services.weather.LocalWeatherLive;
import com.amap.api.services.weather.WeatherSearchQuery;

/* compiled from: WeatherLiveHandler.java */
/* loaded from: classes.dex */
public class ag extends ah<WeatherSearchQuery, LocalWeatherLive> {
    private LocalWeatherLive h = new LocalWeatherLive();

    @Override // com.amap.api.services.a.ah, com.amap.api.services.a.cz
    public /* bridge */ /* synthetic */ String g() {
        return super.g();
    }

    public ag(Context context, WeatherSearchQuery weatherSearchQuery) {
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
        stringBuffer.append("&extensions=base");
        stringBuffer.append("&key=" + aw.f(this.d));
        return stringBuffer.toString();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: d */
    public LocalWeatherLive a(String str) throws AMapException {
        this.h = n.d(str);
        return this.h;
    }
}
