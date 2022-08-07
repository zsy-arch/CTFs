package com.amap.api.services.a;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import com.amap.api.services.a.q;
import com.amap.api.services.busline.BusLineQuery;
import com.amap.api.services.busline.BusLineResult;
import com.amap.api.services.busline.BusLineSearch;
import com.amap.api.services.core.AMapException;
import com.amap.api.services.interfaces.IBusLineSearch;
import java.util.ArrayList;

/* compiled from: BusLineSearchCore.java */
/* loaded from: classes.dex */
public class aj implements IBusLineSearch {
    private Context a;
    private BusLineSearch.OnBusLineSearchListener b;
    private BusLineQuery c;
    private BusLineQuery d;
    private int e;
    private ArrayList<BusLineResult> f = new ArrayList<>();
    private Handler g;

    public aj(Context context, BusLineQuery busLineQuery) {
        this.g = null;
        this.a = context.getApplicationContext();
        this.c = busLineQuery;
        if (busLineQuery != null) {
            this.d = busLineQuery.clone();
        }
        this.g = q.a();
    }

    @Override // com.amap.api.services.interfaces.IBusLineSearch
    public BusLineResult searchBusLine() throws AMapException {
        try {
            o.a(this.a);
            if (this.d == null || !a()) {
                throw new AMapException("无效的参数 - IllegalArgumentException");
            }
            if (!this.c.weakEquals(this.d)) {
                this.d = this.c.clone();
                this.e = 0;
                if (this.f != null) {
                    this.f.clear();
                }
            }
            if (this.e == 0) {
                BusLineResult busLineResult = (BusLineResult) new d(this.a, this.c.clone()).a();
                a(busLineResult);
                return busLineResult;
            }
            BusLineResult b = b(this.c.getPageNumber());
            if (b != null) {
                return b;
            }
            BusLineResult busLineResult2 = (BusLineResult) new d(this.a, this.c).a();
            this.f.set(this.c.getPageNumber(), busLineResult2);
            return busLineResult2;
        } catch (AMapException e) {
            i.a(e, "BusLineSearch", "searchBusLine");
            throw new AMapException(e.getErrorMessage());
        }
    }

    private void a(BusLineResult busLineResult) {
        this.f = new ArrayList<>();
        for (int i = 0; i < this.e; i++) {
            this.f.add(null);
        }
        if (this.e >= 0 && a(this.c.getPageNumber())) {
            this.f.set(this.c.getPageNumber(), busLineResult);
        }
    }

    private boolean a(int i) {
        return i < this.e && i >= 0;
    }

    private BusLineResult b(int i) {
        if (a(i)) {
            return this.f.get(i);
        }
        throw new IllegalArgumentException("page out of range");
    }

    @Override // com.amap.api.services.interfaces.IBusLineSearch
    public void setOnBusLineSearchListener(BusLineSearch.OnBusLineSearchListener onBusLineSearchListener) {
        this.b = onBusLineSearchListener;
    }

    @Override // com.amap.api.services.interfaces.IBusLineSearch
    public void searchBusLineAsyn() {
        try {
            new Thread(new Runnable() { // from class: com.amap.api.services.a.aj.1
                @Override // java.lang.Runnable
                public void run() {
                    Message obtainMessage;
                    try {
                        obtainMessage = q.a().obtainMessage();
                        try {
                            obtainMessage.arg1 = 3;
                            obtainMessage.what = 1000;
                            q.a aVar = new q.a();
                            obtainMessage.obj = aVar;
                            aVar.b = aj.this.b;
                            aVar.a = aj.this.searchBusLine();
                            aj.this.g.sendMessage(obtainMessage);
                        } catch (AMapException e) {
                            obtainMessage.what = e.getErrorCode();
                            aj.this.g.sendMessage(obtainMessage);
                        }
                    } catch (Throwable th) {
                        aj.this.g.sendMessage(obtainMessage);
                        throw th;
                    }
                }
            }).start();
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    @Override // com.amap.api.services.interfaces.IBusLineSearch
    public void setQuery(BusLineQuery busLineQuery) {
        if (!this.c.weakEquals(busLineQuery)) {
            this.c = busLineQuery;
            this.d = busLineQuery.clone();
        }
    }

    @Override // com.amap.api.services.interfaces.IBusLineSearch
    public BusLineQuery getQuery() {
        return this.c;
    }

    private boolean a() {
        return this.c != null && !i.a(this.c.getQueryString());
    }
}
