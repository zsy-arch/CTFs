package com.amap.api.services.a;

import android.content.Context;
import com.amap.api.services.busline.BusLineQuery;
import com.amap.api.services.busline.BusLineResult;
import com.amap.api.services.busline.BusStationQuery;
import com.amap.api.services.busline.BusStationResult;
import com.amap.api.services.core.AMapException;
import com.amap.api.services.core.SuggestionCity;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONObject;

/* compiled from: BusSearchServerHandler.java */
/* loaded from: classes.dex */
public class d<T> extends b<T, Object> {
    private int h = 0;
    private List<String> i = new ArrayList();
    private List<SuggestionCity> j = new ArrayList();

    public d(Context context, T t) {
        super(context, t);
    }

    @Override // com.amap.api.services.a.cz
    public String g() {
        String str;
        if (!(this.a instanceof BusLineQuery)) {
            str = "stopname";
        } else if (((BusLineQuery) this.a).getCategory() == BusLineQuery.SearchType.BY_LINE_ID) {
            str = "lineid";
        } else if (((BusLineQuery) this.a).getCategory() == BusLineQuery.SearchType.BY_LINE_NAME) {
            str = "linename";
        } else {
            str = "";
        }
        return h.a() + "/bus/" + str + "?";
    }

    @Override // com.amap.api.services.a.a
    protected Object a(String str) throws AMapException {
        try {
            JSONObject jSONObject = new JSONObject(str);
            JSONObject optJSONObject = jSONObject.optJSONObject("suggestion");
            if (optJSONObject != null) {
                this.j = n.a(optJSONObject);
                this.i = n.b(optJSONObject);
            }
            this.h = jSONObject.optInt("count");
            if (this.a instanceof BusLineQuery) {
                return BusLineResult.createPagedResult((BusLineQuery) this.a, this.h, this.j, this.i, n.i(jSONObject));
            }
            return BusStationResult.createPagedResult((BusStationQuery) this.a, this.h, this.j, this.i, n.e(jSONObject));
        } catch (Exception e) {
            i.a(e, "BusSearchServerHandler", "paseJSON");
            return null;
        }
    }

    @Override // com.amap.api.services.a.b
    protected String e() {
        StringBuilder sb = new StringBuilder();
        sb.append("output=json");
        if (this.a instanceof BusLineQuery) {
            BusLineQuery busLineQuery = (BusLineQuery) this.a;
            sb.append("&extensions=all");
            if (busLineQuery.getCategory() == BusLineQuery.SearchType.BY_LINE_ID) {
                sb.append("&id=").append(b(((BusLineQuery) this.a).getQueryString()));
            } else {
                String city = busLineQuery.getCity();
                if (!n.i(city)) {
                    sb.append("&city=").append(b(city));
                }
                sb.append("&keywords=" + b(busLineQuery.getQueryString()));
                sb.append("&offset=" + busLineQuery.getPageSize());
                sb.append("&page=" + (busLineQuery.getPageNumber() + 1));
            }
        } else {
            BusStationQuery busStationQuery = (BusStationQuery) this.a;
            String city2 = busStationQuery.getCity();
            if (!n.i(city2)) {
                sb.append("&city=").append(b(city2));
            }
            sb.append("&keywords=" + b(busStationQuery.getQueryString()));
            sb.append("&offset=" + busStationQuery.getPageSize());
            sb.append("&page=" + (busStationQuery.getPageNumber() + 1));
        }
        sb.append("&key=" + aw.f(this.d));
        return sb.toString();
    }
}
