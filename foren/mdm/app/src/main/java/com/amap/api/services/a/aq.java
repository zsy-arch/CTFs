package com.amap.api.services.a;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import com.amap.api.maps.model.MyLocationStyle;
import com.amap.api.services.a.q;
import com.amap.api.services.core.AMapException;
import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.core.PoiItem;
import com.amap.api.services.interfaces.IPoiSearch;
import com.amap.api.services.poisearch.PoiResult;
import com.amap.api.services.poisearch.PoiSearch;
import java.util.HashMap;
import java.util.List;

/* compiled from: PoiSearchCore.java */
/* loaded from: classes.dex */
public class aq implements IPoiSearch {
    private static HashMap<Integer, PoiResult> i;
    private PoiSearch.SearchBound a;
    private PoiSearch.Query b;
    private Context c;
    private PoiSearch.OnPoiSearchListener d;
    private String e = "zh-CN";
    private PoiSearch.Query f;
    private PoiSearch.SearchBound g;
    private int h;
    private Handler j;

    public aq(Context context, PoiSearch.Query query) {
        this.j = null;
        this.c = context.getApplicationContext();
        setQuery(query);
        this.j = q.a();
    }

    @Override // com.amap.api.services.interfaces.IPoiSearch
    public void setOnPoiSearchListener(PoiSearch.OnPoiSearchListener onPoiSearchListener) {
        this.d = onPoiSearchListener;
    }

    @Override // com.amap.api.services.interfaces.IPoiSearch
    public void setLanguage(String str) {
        if ("en".equals(str)) {
            this.e = "en";
        } else {
            this.e = "zh-CN";
        }
    }

    @Override // com.amap.api.services.interfaces.IPoiSearch
    public String getLanguage() {
        return this.e;
    }

    private boolean a() {
        if (this.b == null) {
            return false;
        }
        return !i.a(this.b.getQueryString()) || !i.a(this.b.getCategory());
    }

    private boolean b() {
        PoiSearch.SearchBound bound = getBound();
        if (bound != null && bound.getShape().equals("Bound")) {
            return true;
        }
        return false;
    }

    private boolean c() {
        PoiSearch.SearchBound bound = getBound();
        if (bound != null) {
            if (bound.getShape().equals("Bound")) {
                if (bound.getCenter() == null) {
                    return false;
                }
            } else if (bound.getShape().equals("Polygon")) {
                List<LatLonPoint> polyGonList = bound.getPolyGonList();
                for (int i2 = 0; i2 < polyGonList.size(); i2++) {
                    if (polyGonList.get(i2) == null) {
                        return false;
                    }
                }
            } else if (bound.getShape().equals("Rectangle")) {
                LatLonPoint lowerLeft = bound.getLowerLeft();
                LatLonPoint upperRight = bound.getUpperRight();
                if (lowerLeft == null || upperRight == null || lowerLeft.getLatitude() >= upperRight.getLatitude() || lowerLeft.getLongitude() >= upperRight.getLongitude()) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override // com.amap.api.services.interfaces.IPoiSearch
    public PoiResult searchPOI() throws AMapException {
        PoiSearch.SearchBound searchBound;
        try {
            o.a(this.c);
            if (!b() && !a()) {
                throw new AMapException("无效的参数 - IllegalArgumentException");
            } else if (!c()) {
                throw new AMapException("无效的参数 - IllegalArgumentException");
            } else if (this.b == null) {
                throw new AMapException("无效的参数 - IllegalArgumentException");
            } else {
                if ((!this.b.queryEquals(this.f) && this.a == null) || (!this.b.queryEquals(this.f) && !this.a.equals(this.g))) {
                    this.h = 0;
                    this.f = this.b.clone();
                    if (this.a != null) {
                        this.g = this.a.clone();
                    }
                    if (i != null) {
                        i.clear();
                    }
                }
                if (this.a != null) {
                    searchBound = this.a.clone();
                } else {
                    searchBound = null;
                }
                if (this.h == 0) {
                    PoiResult a = new w(this.c, new z(this.b.clone(), searchBound)).a();
                    a(a);
                    return a;
                }
                PoiResult a2 = a(this.b.getPageNum());
                if (a2 != null) {
                    return a2;
                }
                PoiResult a3 = new w(this.c, new z(this.b.clone(), searchBound)).a();
                i.put(Integer.valueOf(this.b.getPageNum()), a3);
                return a3;
            }
        } catch (AMapException e) {
            i.a(e, "PoiSearch", "searchPOI");
            throw new AMapException(e.getErrorMessage());
        }
    }

    /* JADX WARN: Type inference failed for: r0v1, types: [com.amap.api.services.a.aq$1] */
    @Override // com.amap.api.services.interfaces.IPoiSearch
    public void searchPOIAsyn() {
        try {
            new Thread() { // from class: com.amap.api.services.a.aq.1
                @Override // java.lang.Thread, java.lang.Runnable
                public void run() {
                    Message obtainMessage = aq.this.j.obtainMessage();
                    obtainMessage.arg1 = 6;
                    obtainMessage.what = 600;
                    Bundle bundle = new Bundle();
                    PoiResult poiResult = null;
                    try {
                        poiResult = aq.this.searchPOI();
                        bundle.putInt(MyLocationStyle.ERROR_CODE, 1000);
                    } catch (AMapException e) {
                        bundle.putInt(MyLocationStyle.ERROR_CODE, e.getErrorCode());
                    } finally {
                        q.h hVar = new q.h();
                        hVar.b = aq.this.d;
                        hVar.a = poiResult;
                        obtainMessage.obj = hVar;
                        obtainMessage.setData(bundle);
                        aq.this.j.sendMessage(obtainMessage);
                    }
                }
            }.start();
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    @Override // com.amap.api.services.interfaces.IPoiSearch
    public PoiItem searchPOIId(String str) throws AMapException {
        o.a(this.c);
        return new v(this.c, str).a();
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [com.amap.api.services.a.aq$2] */
    @Override // com.amap.api.services.interfaces.IPoiSearch
    public void searchPOIIdAsyn(final String str) {
        new Thread() { // from class: com.amap.api.services.a.aq.2
            @Override // java.lang.Thread, java.lang.Runnable
            public void run() {
                Message obtainMessage = q.a().obtainMessage();
                obtainMessage.arg1 = 6;
                obtainMessage.what = 602;
                Bundle bundle = new Bundle();
                PoiItem poiItem = null;
                try {
                    poiItem = aq.this.searchPOIId(str);
                    bundle.putInt(MyLocationStyle.ERROR_CODE, 1000);
                } catch (AMapException e) {
                    i.a(e, "PoiSearch", "searchPOIIdAsyn");
                    bundle.putInt(MyLocationStyle.ERROR_CODE, e.getErrorCode());
                } finally {
                    q.g gVar = new q.g();
                    gVar.b = aq.this.d;
                    gVar.a = poiItem;
                    obtainMessage.obj = gVar;
                    obtainMessage.setData(bundle);
                    aq.this.j.sendMessage(obtainMessage);
                }
            }
        }.start();
    }

    @Override // com.amap.api.services.interfaces.IPoiSearch
    public void setQuery(PoiSearch.Query query) {
        this.b = query;
    }

    @Override // com.amap.api.services.interfaces.IPoiSearch
    public void setBound(PoiSearch.SearchBound searchBound) {
        this.a = searchBound;
    }

    @Override // com.amap.api.services.interfaces.IPoiSearch
    public PoiSearch.Query getQuery() {
        return this.b;
    }

    @Override // com.amap.api.services.interfaces.IPoiSearch
    public PoiSearch.SearchBound getBound() {
        return this.a;
    }

    private void a(PoiResult poiResult) {
        i = new HashMap<>();
        if (this.b != null && poiResult != null && this.h > 0 && this.h > this.b.getPageNum()) {
            i.put(Integer.valueOf(this.b.getPageNum()), poiResult);
        }
    }

    protected PoiResult a(int i2) {
        if (b(i2)) {
            return i.get(Integer.valueOf(i2));
        }
        throw new IllegalArgumentException("page out of range");
    }

    private boolean b(int i2) {
        return i2 <= this.h && i2 >= 0;
    }
}
