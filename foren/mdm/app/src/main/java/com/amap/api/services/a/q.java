package com.amap.api.services.a;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.amap.api.maps.model.MyLocationStyle;
import com.amap.api.services.busline.BusLineResult;
import com.amap.api.services.busline.BusLineSearch;
import com.amap.api.services.busline.BusStationResult;
import com.amap.api.services.busline.BusStationSearch;
import com.amap.api.services.cloud.CloudItemDetail;
import com.amap.api.services.cloud.CloudResult;
import com.amap.api.services.cloud.CloudSearch;
import com.amap.api.services.core.AMapException;
import com.amap.api.services.core.PoiItem;
import com.amap.api.services.district.DistrictResult;
import com.amap.api.services.district.DistrictSearch;
import com.amap.api.services.geocoder.GeocodeResult;
import com.amap.api.services.geocoder.GeocodeSearch;
import com.amap.api.services.geocoder.RegeocodeResult;
import com.amap.api.services.help.Inputtips;
import com.amap.api.services.nearby.NearbySearch;
import com.amap.api.services.nearby.NearbySearchResult;
import com.amap.api.services.poisearch.PoiResult;
import com.amap.api.services.poisearch.PoiSearch;
import com.amap.api.services.route.BusRouteResult;
import com.amap.api.services.route.DriveRouteResult;
import com.amap.api.services.route.RideRouteResult;
import com.amap.api.services.route.RouteSearch;
import com.amap.api.services.route.WalkRouteResult;
import com.amap.api.services.routepoisearch.RoutePOISearch;
import com.amap.api.services.routepoisearch.RoutePOISearchResult;
import com.amap.api.services.share.ShareSearch;
import com.amap.api.services.weather.LocalWeatherForecastResult;
import com.amap.api.services.weather.LocalWeatherLiveResult;
import com.amap.api.services.weather.WeatherSearch;
import java.util.ArrayList;
import java.util.List;

/* compiled from: MessageHandler.java */
/* loaded from: classes.dex */
public class q extends Handler {
    private static q a;

    /* compiled from: MessageHandler.java */
    /* loaded from: classes.dex */
    public static class a {
        public BusLineResult a;
        public BusLineSearch.OnBusLineSearchListener b;
    }

    /* compiled from: MessageHandler.java */
    /* loaded from: classes.dex */
    public static class b {
        public BusStationResult a;
        public BusStationSearch.OnBusStationSearchListener b;
    }

    /* compiled from: MessageHandler.java */
    /* loaded from: classes.dex */
    public static class c {
        public CloudItemDetail a;
        public CloudSearch.OnCloudSearchListener b;
    }

    /* compiled from: MessageHandler.java */
    /* loaded from: classes.dex */
    public static class d {
        public CloudResult a;
        public CloudSearch.OnCloudSearchListener b;
    }

    /* compiled from: MessageHandler.java */
    /* loaded from: classes.dex */
    public static class e {
        public GeocodeResult a;
        public GeocodeSearch.OnGeocodeSearchListener b;
    }

    /* compiled from: MessageHandler.java */
    /* loaded from: classes.dex */
    public static class f {
        public List<NearbySearch.NearbyListener> a;
        public NearbySearchResult b;
    }

    /* compiled from: MessageHandler.java */
    /* loaded from: classes.dex */
    public static class g {
        public PoiItem a;
        public PoiSearch.OnPoiSearchListener b;
    }

    /* compiled from: MessageHandler.java */
    /* loaded from: classes.dex */
    public static class h {
        public PoiResult a;
        public PoiSearch.OnPoiSearchListener b;
    }

    /* compiled from: MessageHandler.java */
    /* loaded from: classes.dex */
    public static class i {
        public RegeocodeResult a;
        public GeocodeSearch.OnGeocodeSearchListener b;
    }

    /* compiled from: MessageHandler.java */
    /* loaded from: classes.dex */
    public static class j {
        public RoutePOISearchResult a;
        public RoutePOISearch.OnRoutePOISearchListener b;
    }

    /* compiled from: MessageHandler.java */
    /* loaded from: classes.dex */
    public static class k {
        public LocalWeatherForecastResult a;
        public WeatherSearch.OnWeatherSearchListener b;
    }

    /* compiled from: MessageHandler.java */
    /* loaded from: classes.dex */
    public static class l {
        public LocalWeatherLiveResult a;
        public WeatherSearch.OnWeatherSearchListener b;
    }

    public static synchronized q a() {
        q qVar;
        synchronized (q.class) {
            if (a == null) {
                if (Looper.myLooper() == null || Looper.myLooper() != Looper.getMainLooper()) {
                    a = new q(Looper.getMainLooper());
                } else {
                    a = new q();
                }
            }
            qVar = a;
        }
        return qVar;
    }

    q() {
    }

    q(Looper looper) {
        super(looper);
    }

    @Override // android.os.Handler
    public void handleMessage(Message message) {
        try {
            switch (message.arg1) {
                case 1:
                    k(message);
                    break;
                case 2:
                    h(message);
                    break;
                case 3:
                    j(message);
                    break;
                case 4:
                    i(message);
                    break;
                case 5:
                    g(message);
                    break;
                case 6:
                    f(message);
                    break;
                case 7:
                    e(message);
                    break;
                case 8:
                    d(message);
                    break;
                case 9:
                    c(message);
                    break;
                case 10:
                    b(message);
                    break;
                case 11:
                    a(message);
                    break;
                case 12:
                    l(message);
                    break;
                case 13:
                    m(message);
                    break;
                case 14:
                    n(message);
                    break;
            }
        } catch (Throwable th) {
            i.a(th, "MessageHandler", "handleMessage");
        }
    }

    private void a(Message message) {
        int i2 = message.arg2;
        ShareSearch.OnShareSearchListener onShareSearchListener = (ShareSearch.OnShareSearchListener) message.obj;
        String string = message.getData().getString("shareurlkey");
        if (onShareSearchListener != null) {
            switch (message.what) {
                case AMapException.CODE_AMAP_ENGINE_RESPONSE_ERROR /* 1100 */:
                    onShareSearchListener.onPoiShareUrlSearched(string, i2);
                    return;
                case AMapException.CODE_AMAP_ENGINE_RESPONSE_DATA_ERROR /* 1101 */:
                    onShareSearchListener.onLocationShareUrlSearched(string, i2);
                    return;
                case AMapException.CODE_AMAP_ENGINE_CONNECT_TIMEOUT /* 1102 */:
                    onShareSearchListener.onNaviShareUrlSearched(string, i2);
                    return;
                case AMapException.CODE_AMAP_ENGINE_RETURN_TIMEOUT /* 1103 */:
                    onShareSearchListener.onBusRouteShareUrlSearched(string, i2);
                    return;
                case 1104:
                    onShareSearchListener.onDrivingRouteShareUrlSearched(string, i2);
                    return;
                case 1105:
                    onShareSearchListener.onWalkRouteShareUrlSearched(string, i2);
                    return;
                default:
                    return;
            }
        }
    }

    private void b(Message message) {
        List<NearbySearch.NearbyListener> list = (List) message.obj;
        if (!(list == null || list.size() == 0)) {
            for (NearbySearch.NearbyListener nearbyListener : list) {
                nearbyListener.onNearbyInfoUploaded(message.what);
            }
        }
    }

    private void c(Message message) {
        List<NearbySearch.NearbyListener> list;
        f fVar = (f) message.obj;
        if (!(fVar == null || (list = fVar.a) == null || list.size() == 0)) {
            NearbySearchResult nearbySearchResult = null;
            if (message.what == 1000) {
                nearbySearchResult = fVar.b;
            }
            for (NearbySearch.NearbyListener nearbyListener : list) {
                nearbyListener.onNearbyInfoSearched(nearbySearchResult, message.what);
            }
        }
    }

    private void d(Message message) {
        List<NearbySearch.NearbyListener> list = (List) message.obj;
        if (!(list == null || list.size() == 0)) {
            for (NearbySearch.NearbyListener nearbyListener : list) {
                nearbyListener.onUserInfoCleared(message.what);
            }
        }
    }

    private void e(Message message) {
        BusStationSearch.OnBusStationSearchListener onBusStationSearchListener;
        BusStationResult busStationResult;
        b bVar = (b) message.obj;
        if (bVar != null && (onBusStationSearchListener = bVar.b) != null) {
            if (message.what == 1000) {
                busStationResult = bVar.a;
            } else {
                busStationResult = null;
            }
            onBusStationSearchListener.onBusStationSearched(busStationResult, message.what);
        }
    }

    private void f(Message message) {
        g gVar;
        PoiSearch.OnPoiSearchListener onPoiSearchListener;
        Bundle data;
        if (message.what == 600) {
            h hVar = (h) message.obj;
            if (hVar != null && (onPoiSearchListener = hVar.b) != null && (data = message.getData()) != null) {
                onPoiSearchListener.onPoiSearched(hVar.a, data.getInt(MyLocationStyle.ERROR_CODE));
            }
        } else if (message.what == 602 && (gVar = (g) message.obj) != null) {
            PoiSearch.OnPoiSearchListener onPoiSearchListener2 = gVar.b;
            Bundle data2 = message.getData();
            if (data2 != null) {
                onPoiSearchListener2.onPoiItemSearched(gVar.a, data2.getInt(MyLocationStyle.ERROR_CODE));
            }
        }
    }

    private void g(Message message) {
        Inputtips.InputtipsListener inputtipsListener = (Inputtips.InputtipsListener) message.obj;
        if (inputtipsListener != null) {
            ArrayList arrayList = null;
            if (message.what == 1000) {
                arrayList = message.getData().getParcelableArrayList(com.alipay.sdk.util.j.c);
            }
            inputtipsListener.onGetInputtips(arrayList, message.what);
        }
    }

    private void h(Message message) {
        e eVar;
        GeocodeSearch.OnGeocodeSearchListener onGeocodeSearchListener;
        GeocodeSearch.OnGeocodeSearchListener onGeocodeSearchListener2;
        if (message.what == 201) {
            i iVar = (i) message.obj;
            if (iVar != null && (onGeocodeSearchListener2 = iVar.b) != null) {
                onGeocodeSearchListener2.onRegeocodeSearched(iVar.a, message.arg2);
            }
        } else if (message.what == 200 && (eVar = (e) message.obj) != null && (onGeocodeSearchListener = eVar.b) != null) {
            onGeocodeSearchListener.onGeocodeSearched(eVar.a, message.arg2);
        }
    }

    private void i(Message message) {
        DistrictSearch.OnDistrictSearchListener onDistrictSearchListener = (DistrictSearch.OnDistrictSearchListener) message.obj;
        if (onDistrictSearchListener != null) {
            onDistrictSearchListener.onDistrictSearched((DistrictResult) message.getData().getParcelable(com.alipay.sdk.util.j.c));
        }
    }

    private void j(Message message) {
        BusLineSearch.OnBusLineSearchListener onBusLineSearchListener;
        BusLineResult busLineResult;
        a aVar = (a) message.obj;
        if (aVar != null && (onBusLineSearchListener = aVar.b) != null) {
            if (message.what == 1000) {
                busLineResult = aVar.a;
            } else {
                busLineResult = null;
            }
            onBusLineSearchListener.onBusLineSearched(busLineResult, message.what);
        }
    }

    private void k(Message message) {
        Bundle data;
        RouteSearch.OnRouteSearchListener onRouteSearchListener = (RouteSearch.OnRouteSearchListener) message.obj;
        if (onRouteSearchListener != null) {
            if (message.what == 100) {
                Bundle data2 = message.getData();
                if (data2 != null) {
                    onRouteSearchListener.onBusRouteSearched((BusRouteResult) message.getData().getParcelable(com.alipay.sdk.util.j.c), data2.getInt(MyLocationStyle.ERROR_CODE));
                }
            } else if (message.what == 101) {
                Bundle data3 = message.getData();
                if (data3 != null) {
                    onRouteSearchListener.onDriveRouteSearched((DriveRouteResult) message.getData().getParcelable(com.alipay.sdk.util.j.c), data3.getInt(MyLocationStyle.ERROR_CODE));
                }
            } else if (message.what == 102) {
                Bundle data4 = message.getData();
                if (data4 != null) {
                    onRouteSearchListener.onWalkRouteSearched((WalkRouteResult) message.getData().getParcelable(com.alipay.sdk.util.j.c), data4.getInt(MyLocationStyle.ERROR_CODE));
                }
            } else if (message.what == 103 && (data = message.getData()) != null) {
                onRouteSearchListener.onRideRouteSearched((RideRouteResult) message.getData().getParcelable(com.alipay.sdk.util.j.c), data.getInt(MyLocationStyle.ERROR_CODE));
            }
        }
    }

    private void l(Message message) {
        c cVar;
        if (message.what == 700) {
            d dVar = (d) message.obj;
            if (dVar != null) {
                dVar.b.onCloudSearched(dVar.a, message.arg2);
            }
        } else if (message.what == 701 && (cVar = (c) message.obj) != null) {
            cVar.b.onCloudItemDetailSearched(cVar.a, message.arg2);
        }
    }

    private void m(Message message) {
        k kVar;
        WeatherSearch.OnWeatherSearchListener onWeatherSearchListener;
        Bundle data;
        WeatherSearch.OnWeatherSearchListener onWeatherSearchListener2;
        Bundle data2;
        if (message.what == 1301) {
            l lVar = (l) message.obj;
            if (lVar != null && (onWeatherSearchListener2 = lVar.b) != null && (data2 = message.getData()) != null) {
                onWeatherSearchListener2.onWeatherLiveSearched(lVar.a, data2.getInt(MyLocationStyle.ERROR_CODE));
            }
        } else if (message.what == 1302 && (kVar = (k) message.obj) != null && (onWeatherSearchListener = kVar.b) != null && (data = message.getData()) != null) {
            onWeatherSearchListener.onWeatherForecastSearched(kVar.a, data.getInt(MyLocationStyle.ERROR_CODE));
        }
    }

    private void n(Message message) {
        RoutePOISearch.OnRoutePOISearchListener onRoutePOISearchListener;
        Bundle data;
        j jVar = (j) message.obj;
        if (jVar != null && (onRoutePOISearchListener = jVar.b) != null && (data = message.getData()) != null) {
            onRoutePOISearchListener.onRoutePoiSearched(jVar.a, data.getInt(MyLocationStyle.ERROR_CODE));
        }
    }
}
