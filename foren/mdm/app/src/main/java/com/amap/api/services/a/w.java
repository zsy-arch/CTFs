package com.amap.api.services.a;

import android.content.Context;
import com.alipay.sdk.util.h;
import com.amap.api.services.core.AMapException;
import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.core.PoiItem;
import com.amap.api.services.core.SuggestionCity;
import com.amap.api.services.poisearch.PoiResult;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: PoiSearchKeywordsHandler.java */
/* loaded from: classes.dex */
public class w extends u<z, PoiResult> {
    private int h = 0;
    private List<String> i = new ArrayList();
    private List<SuggestionCity> j = new ArrayList();

    public w(Context context, z zVar) {
        super(context, zVar);
    }

    @Override // com.amap.api.services.a.cz
    public String g() {
        String str = h.a() + "/place";
        if (((z) this.a).b == null) {
            return str + "/text?";
        }
        if (((z) this.a).b.getShape().equals("Bound")) {
            return str + "/around?";
        }
        return (((z) this.a).b.getShape().equals("Rectangle") || ((z) this.a).b.getShape().equals("Polygon")) ? str + "/polygon?" : str;
    }

    /* renamed from: e */
    public PoiResult a(String str) throws AMapException {
        JSONObject jSONObject;
        ArrayList<PoiItem> arrayList = new ArrayList<>();
        if (str == null) {
            return PoiResult.createPagedResult(((z) this.a).a, ((z) this.a).b, this.i, this.j, ((z) this.a).a.getPageSize(), this.h, arrayList);
        }
        try {
            jSONObject = new JSONObject(str);
            this.h = jSONObject.optInt("count");
            arrayList = n.c(jSONObject);
        } catch (JSONException e) {
            i.a(e, "PoiSearchKeywordHandler", "paseJSONJSONException");
        } catch (Exception e2) {
            i.a(e2, "PoiSearchKeywordHandler", "paseJSONException");
        }
        if (!jSONObject.has("suggestion")) {
            return PoiResult.createPagedResult(((z) this.a).a, ((z) this.a).b, this.i, this.j, ((z) this.a).a.getPageSize(), this.h, arrayList);
        }
        JSONObject optJSONObject = jSONObject.optJSONObject("suggestion");
        if (optJSONObject == null) {
            return PoiResult.createPagedResult(((z) this.a).a, ((z) this.a).b, this.i, this.j, ((z) this.a).a.getPageSize(), this.h, arrayList);
        }
        this.j = n.a(optJSONObject);
        this.i = n.b(optJSONObject);
        return PoiResult.createPagedResult(((z) this.a).a, ((z) this.a).b, this.i, this.j, ((z) this.a).a.getPageSize(), this.h, arrayList);
    }

    @Override // com.amap.api.services.a.b
    protected String e() {
        List<LatLonPoint> polyGonList;
        StringBuilder sb = new StringBuilder();
        sb.append("output=json");
        if (((z) this.a).b != null) {
            if (((z) this.a).b.getShape().equals("Bound")) {
                sb.append("&location=").append(i.a(((z) this.a).b.getCenter().getLongitude()) + "," + i.a(((z) this.a).b.getCenter().getLatitude()));
                sb.append("&radius=").append(((z) this.a).b.getRange());
                sb.append("&sortrule=").append(h());
            } else if (((z) this.a).b.getShape().equals("Rectangle")) {
                LatLonPoint lowerLeft = ((z) this.a).b.getLowerLeft();
                LatLonPoint upperRight = ((z) this.a).b.getUpperRight();
                sb.append("&polygon=" + i.a(lowerLeft.getLongitude()) + "," + i.a(lowerLeft.getLatitude()) + h.b + i.a(upperRight.getLongitude()) + "," + i.a(upperRight.getLatitude()));
            } else if (((z) this.a).b.getShape().equals("Polygon") && (polyGonList = ((z) this.a).b.getPolyGonList()) != null && polyGonList.size() > 0) {
                sb.append("&polygon=" + i.a(polyGonList));
            }
        }
        String city = ((z) this.a).a.getCity();
        if (!d(city)) {
            sb.append("&city=").append(b(city));
        }
        sb.append("&keywords=" + b(((z) this.a).a.getQueryString()));
        sb.append("&language=").append(h.c());
        sb.append("&offset=" + ((z) this.a).a.getPageSize());
        sb.append("&page=" + (((z) this.a).a.getPageNum() + 1));
        String building = ((z) this.a).a.getBuilding();
        if (building != null && building.trim().length() > 0) {
            sb.append("&building=" + ((z) this.a).a.getBuilding());
        }
        sb.append("&types=" + b(((z) this.a).a.getCategory()));
        sb.append("&extensions=all");
        sb.append("&key=" + aw.f(this.d));
        if (((z) this.a).a.getCityLimit()) {
            sb.append("&citylimit=true");
        } else {
            sb.append("&citylimit=false");
        }
        if (((z) this.a).a.isRequireSubPois()) {
            sb.append("&children=1");
        } else {
            sb.append("&children=0");
        }
        return sb.toString();
    }

    private String h() {
        return ((z) this.a).b.isDistanceSort() ? "distance" : "weight";
    }
}
