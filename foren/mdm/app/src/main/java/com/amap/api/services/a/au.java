package com.amap.api.services.a;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import com.amap.api.maps.model.MyLocationStyle;
import com.amap.api.services.a.q;
import com.amap.api.services.core.AMapException;
import com.amap.api.services.interfaces.IWeatherSearch;
import com.amap.api.services.weather.LocalWeatherForecastResult;
import com.amap.api.services.weather.LocalWeatherLiveResult;
import com.amap.api.services.weather.WeatherSearch;
import com.amap.api.services.weather.WeatherSearchQuery;

/* compiled from: WeatherSearchCore.java */
/* loaded from: classes.dex */
public class au implements IWeatherSearch {
    private Context a;
    private WeatherSearchQuery b;
    private WeatherSearch.OnWeatherSearchListener c;
    private LocalWeatherLiveResult d;
    private LocalWeatherForecastResult e;
    private Handler f;

    public au(Context context) {
        this.f = null;
        this.a = context.getApplicationContext();
        this.f = q.a();
    }

    @Override // com.amap.api.services.interfaces.IWeatherSearch
    public WeatherSearchQuery getQuery() {
        return this.b;
    }

    @Override // com.amap.api.services.interfaces.IWeatherSearch
    public void setQuery(WeatherSearchQuery weatherSearchQuery) {
        this.b = weatherSearchQuery;
    }

    @Override // com.amap.api.services.interfaces.IWeatherSearch
    public void searchWeatherAsyn() {
        try {
            new Thread(new Runnable() { // from class: com.amap.api.services.a.au.1
                @Override // java.lang.Runnable
                public void run() {
                    Message obtainMessage = q.a().obtainMessage();
                    obtainMessage.arg1 = 13;
                    Bundle bundle = new Bundle();
                    if (au.this.b == null) {
                        try {
                            throw new AMapException("无效的参数 - IllegalArgumentException");
                        } catch (AMapException e) {
                            i.a(e, "WeatherSearch", "searchWeatherAsyn");
                        }
                    } else {
                        try {
                        } catch (AMapException e2) {
                            bundle.putInt(MyLocationStyle.ERROR_CODE, e2.getErrorCode());
                            i.a(e2, "WeatherSearch", "searchWeatherAsyn");
                        } catch (Throwable th) {
                            i.a(th, "WeatherSearch", "searchWeatherAnsyThrowable");
                        } finally {
                            q.l lVar = new q.l();
                            obtainMessage.what = 1301;
                            lVar.b = au.this.c;
                            lVar.a = au.this.d;
                            obtainMessage.obj = lVar;
                            obtainMessage.setData(bundle);
                            au.this.f.sendMessage(obtainMessage);
                        }
                        if (au.this.b.getType() == 1) {
                            au.this.d = au.this.a();
                            bundle.putInt(MyLocationStyle.ERROR_CODE, 1000);
                            return;
                        }
                        try {
                        } catch (AMapException e3) {
                            bundle.putInt(MyLocationStyle.ERROR_CODE, e3.getErrorCode());
                            i.a(e3, "WeatherSearch", "searchWeatherAsyn");
                        } catch (Throwable th2) {
                            i.a(th2, "WeatherSearch", "searchWeatherAnsyThrowable");
                        } finally {
                            q.k kVar = new q.k();
                            obtainMessage.what = 1302;
                            kVar.b = au.this.c;
                            kVar.a = au.this.e;
                            obtainMessage.obj = kVar;
                            obtainMessage.setData(bundle);
                            au.this.f.sendMessage(obtainMessage);
                        }
                        if (au.this.b.getType() == 2) {
                            au.this.e = au.this.b();
                            bundle.putInt(MyLocationStyle.ERROR_CODE, 1000);
                        }
                    }
                }
            }).start();
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public LocalWeatherLiveResult a() throws AMapException {
        o.a(this.a);
        if (this.b == null) {
            throw new AMapException("无效的参数 - IllegalArgumentException");
        }
        ag agVar = new ag(this.a, this.b);
        return LocalWeatherLiveResult.createPagedResult(agVar.h(), agVar.a());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public LocalWeatherForecastResult b() throws AMapException {
        o.a(this.a);
        if (this.b == null) {
            throw new AMapException("无效的参数 - IllegalArgumentException");
        }
        af afVar = new af(this.a, this.b);
        return LocalWeatherForecastResult.createPagedResult(afVar.h(), afVar.a());
    }

    @Override // com.amap.api.services.interfaces.IWeatherSearch
    public void setOnWeatherSearchListener(WeatherSearch.OnWeatherSearchListener onWeatherSearchListener) {
        this.c = onWeatherSearchListener;
    }
}
