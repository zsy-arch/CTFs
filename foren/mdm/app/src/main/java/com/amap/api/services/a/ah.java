package com.amap.api.services.a;

import android.content.Context;

/* compiled from: WeatherSearchHandler.java */
/* loaded from: classes.dex */
abstract class ah<T, V> extends b<T, V> {
    public ah(Context context, T t) {
        super(context, t);
    }

    public T h() {
        return (T) this.a;
    }

    @Override // com.amap.api.services.a.cz
    public String g() {
        return h.a() + "/weather/weatherInfo?";
    }
}
