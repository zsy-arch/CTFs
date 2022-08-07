package com.amap.api.services.a;

import android.content.Context;
import com.amap.api.services.core.AMapException;
import com.amap.api.services.district.DistrictResult;
import com.amap.api.services.district.DistrictSearchQuery;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: DistrictServerHandler.java */
/* loaded from: classes.dex */
public class j extends b<DistrictSearchQuery, DistrictResult> {
    public j(Context context, DistrictSearchQuery districtSearchQuery) {
        super(context, districtSearchQuery);
    }

    @Override // com.amap.api.services.a.b
    protected String e() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("output=json");
        stringBuffer.append("&page=").append(((DistrictSearchQuery) this.a).getPageNum() + 1);
        stringBuffer.append("&offset=").append(((DistrictSearchQuery) this.a).getPageSize());
        stringBuffer.append("&showChild=").append(((DistrictSearchQuery) this.a).isShowChild());
        stringBuffer.append("&showbiz=").append(((DistrictSearchQuery) this.a).isShowBusinessArea());
        if (((DistrictSearchQuery) this.a).isShowBoundary()) {
            stringBuffer.append("&extensions=all");
        } else {
            stringBuffer.append("&extensions=base");
        }
        if (((DistrictSearchQuery) this.a).checkKeyWords()) {
            stringBuffer.append("&keywords=").append(b(((DistrictSearchQuery) this.a).getKeywords()));
        }
        stringBuffer.append("&key=" + aw.f(this.d));
        return stringBuffer.toString();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: d */
    public DistrictResult a(String str) throws AMapException {
        JSONArray optJSONArray;
        ArrayList arrayList = new ArrayList();
        DistrictResult districtResult = new DistrictResult((DistrictSearchQuery) this.a, arrayList);
        try {
            JSONObject jSONObject = new JSONObject(str);
            districtResult.setPageCount(jSONObject.optInt("count"));
            optJSONArray = jSONObject.optJSONArray("districts");
        } catch (JSONException e) {
            i.a(e, "DistrictServerHandler", "paseJSONJSONException");
        } catch (Exception e2) {
            i.a(e2, "DistrictServerHandler", "paseJSONException");
        }
        if (optJSONArray == null) {
            return districtResult;
        }
        n.a(optJSONArray, arrayList, null);
        return districtResult;
    }

    @Override // com.amap.api.services.a.cz
    public String g() {
        return h.a() + "/config/district?";
    }
}
