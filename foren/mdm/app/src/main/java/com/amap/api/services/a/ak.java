package com.amap.api.services.a;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import com.amap.api.services.a.q;
import com.amap.api.services.busline.BusStationQuery;
import com.amap.api.services.busline.BusStationResult;
import com.amap.api.services.busline.BusStationSearch;
import com.amap.api.services.core.AMapException;
import com.amap.api.services.interfaces.IBusStationSearch;
import java.util.ArrayList;

/* compiled from: BusStationSearchCore.java */
/* loaded from: classes.dex */
public class ak implements IBusStationSearch {
    private Context a;
    private BusStationSearch.OnBusStationSearchListener b;
    private BusStationQuery c;
    private BusStationQuery d;
    private int f;
    private ArrayList<BusStationResult> e = new ArrayList<>();
    private Handler g = q.a();

    public ak(Context context, BusStationQuery busStationQuery) {
        this.a = context.getApplicationContext();
        this.c = busStationQuery;
    }

    @Override // com.amap.api.services.interfaces.IBusStationSearch
    public BusStationResult searchBusStation() throws AMapException {
        try {
            o.a(this.a);
            if (!a()) {
                throw new AMapException("无效的参数 - IllegalArgumentException");
            }
            if (!this.c.weakEquals(this.d)) {
                this.d = this.c.clone();
                this.f = 0;
                if (this.e != null) {
                    this.e.clear();
                }
            }
            if (this.f == 0) {
                BusStationResult busStationResult = (BusStationResult) new d(this.a, this.c).a();
                this.f = busStationResult.getPageCount();
                a(busStationResult);
                return busStationResult;
            }
            BusStationResult b = b(this.c.getPageNumber());
            if (b != null) {
                return b;
            }
            BusStationResult busStationResult2 = (BusStationResult) new d(this.a, this.c).a();
            this.e.set(this.c.getPageNumber(), busStationResult2);
            return busStationResult2;
        } catch (AMapException e) {
            i.a(e, "BusStationSearch", "searchBusStation");
            throw new AMapException(e.getErrorMessage());
        }
    }

    private void a(BusStationResult busStationResult) {
        this.e = new ArrayList<>();
        for (int i = 0; i <= this.f; i++) {
            this.e.add(null);
        }
        if (this.f > 0) {
            this.e.set(this.c.getPageNumber(), busStationResult);
        }
    }

    private boolean a(int i) {
        return i <= this.f && i >= 0;
    }

    private BusStationResult b(int i) {
        if (a(i)) {
            return this.e.get(i);
        }
        throw new IllegalArgumentException("page out of range");
    }

    @Override // com.amap.api.services.interfaces.IBusStationSearch
    public void setOnBusStationSearchListener(BusStationSearch.OnBusStationSearchListener onBusStationSearchListener) {
        this.b = onBusStationSearchListener;
    }

    @Override // com.amap.api.services.interfaces.IBusStationSearch
    public void searchBusStationAsyn() {
        try {
            new Thread(new Runnable() { // from class: com.amap.api.services.a.ak.1
                @Override // java.lang.Runnable
                public void run() {
                    Message obtainMessage;
                    try {
                        obtainMessage = q.a().obtainMessage();
                        try {
                            obtainMessage.arg1 = 7;
                            q.b bVar = new q.b();
                            bVar.b = ak.this.b;
                            obtainMessage.obj = bVar;
                            BusStationResult searchBusStation = ak.this.searchBusStation();
                            obtainMessage.what = 1000;
                            bVar.a = searchBusStation;
                            ak.this.g.sendMessage(obtainMessage);
                        } catch (AMapException e) {
                            obtainMessage.what = e.getErrorCode();
                            ak.this.g.sendMessage(obtainMessage);
                        }
                    } catch (Throwable th) {
                        ak.this.g.sendMessage(obtainMessage);
                        throw th;
                    }
                }
            }).start();
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    @Override // com.amap.api.services.interfaces.IBusStationSearch
    public void setQuery(BusStationQuery busStationQuery) {
        if (!busStationQuery.weakEquals(this.c)) {
            this.c = busStationQuery;
        }
    }

    @Override // com.amap.api.services.interfaces.IBusStationSearch
    public BusStationQuery getQuery() {
        return this.c;
    }

    private boolean a() {
        return this.c != null && !i.a(this.c.getQueryString());
    }
}
