package com.amap.api.services.a;

import android.content.Context;
import com.amap.api.services.core.AMapException;
import com.amap.api.services.routepoisearch.RoutePOIItem;
import com.amap.api.services.routepoisearch.RoutePOISearchQuery;
import com.amap.api.services.routepoisearch.RoutePOISearchResult;
import java.util.ArrayList;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: RoutePOISearchHandler.java */
/* loaded from: classes.dex */
public class ac extends b<RoutePOISearchQuery, RoutePOISearchResult> {
    public ac(Context context, RoutePOISearchQuery routePOISearchQuery) {
        super(context, routePOISearchQuery);
    }

    @Override // com.amap.api.services.a.b
    protected String e() {
        String str;
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("key=").append(aw.f(this.d));
        stringBuffer.append("&origin=").append(i.a(((RoutePOISearchQuery) this.a).getFrom()));
        stringBuffer.append("&destination=").append(i.a(((RoutePOISearchQuery) this.a).getTo()));
        stringBuffer.append("&range=").append("" + ((RoutePOISearchQuery) this.a).getRange());
        try {
            switch (((RoutePOISearchQuery) this.a).getSearchType()) {
                case TypeGasStation:
                    str = "0101";
                    break;
                case TypeMaintenanceStation:
                    str = "0300";
                    break;
                case TypeATM:
                    str = "1603";
                    break;
                case TypeToilet:
                    str = "2003";
                    break;
                default:
                    str = "";
                    break;
            }
        } catch (Exception e) {
            str = "";
        }
        stringBuffer.append("&types=").append(str);
        stringBuffer.append("&strategy=").append("" + ((RoutePOISearchQuery) this.a).getMode());
        return stringBuffer.toString();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: d */
    public RoutePOISearchResult a(String str) throws AMapException {
        ArrayList<RoutePOIItem> arrayList;
        ArrayList<RoutePOIItem> arrayList2 = new ArrayList<>();
        try {
            arrayList = n.w(new JSONObject(str));
        } catch (JSONException e) {
            e.printStackTrace();
            arrayList = arrayList2;
        }
        return new RoutePOISearchResult(arrayList, (RoutePOISearchQuery) this.a);
    }

    @Override // com.amap.api.services.a.cz
    public String g() {
        return h.a() + "/place/route?";
    }
}
