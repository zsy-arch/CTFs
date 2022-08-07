package com.amap.api.services.a;

import android.content.Context;
import com.alipay.sdk.util.h;
import com.amap.api.services.cloud.CloudItem;
import com.amap.api.services.cloud.CloudItemDetail;
import com.amap.api.services.cloud.CloudResult;
import com.amap.api.services.cloud.CloudSearch;
import com.amap.api.services.core.AMapException;
import com.amap.api.services.core.LatLonPoint;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Marker;

/* compiled from: CloudSearchKeywordsHandler.java */
/* loaded from: classes.dex */
public class g extends e<CloudSearch.Query, CloudResult> {
    private int h = 0;

    public g(Context context, CloudSearch.Query query) {
        super(context, query);
    }

    @Override // com.amap.api.services.a.cz
    public String g() {
        String str = h.b() + "/datasearch";
        String shape = ((CloudSearch.Query) this.a).getBound().getShape();
        if (shape.equals("Bound")) {
            return str + "/around?";
        }
        if (shape.equals("Polygon") || shape.equals("Rectangle")) {
            return str + "/polygon?";
        }
        return shape.equals(CloudSearch.SearchBound.LOCAL_SHAPE) ? str + "/local?" : str;
    }

    /* renamed from: e */
    public CloudResult a(String str) throws AMapException {
        ArrayList<CloudItem> arrayList = null;
        if (str == null || str.equals("")) {
            return CloudResult.createPagedResult((CloudSearch.Query) this.a, this.h, ((CloudSearch.Query) this.a).getBound(), ((CloudSearch.Query) this.a).getPageSize(), null);
        }
        try {
            arrayList = b(new JSONObject(str));
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        return CloudResult.createPagedResult((CloudSearch.Query) this.a, this.h, ((CloudSearch.Query) this.a).getBound(), ((CloudSearch.Query) this.a).getPageSize(), arrayList);
    }

    private ArrayList<CloudItem> b(JSONObject jSONObject) throws JSONException {
        ArrayList<CloudItem> arrayList = new ArrayList<>();
        if (!jSONObject.has("datas")) {
            return arrayList;
        }
        JSONArray optJSONArray = jSONObject.optJSONArray("datas");
        this.h = jSONObject.getInt("count");
        for (int i = 0; i < optJSONArray.length(); i++) {
            JSONObject optJSONObject = optJSONArray.optJSONObject(i);
            CloudItemDetail a = a(optJSONObject);
            a(a, optJSONObject);
            arrayList.add(a);
        }
        return arrayList;
    }

    @Override // com.amap.api.services.a.b
    protected String e() {
        StringBuilder sb = new StringBuilder();
        sb.append("output=json");
        if (((CloudSearch.Query) this.a).getBound() != null) {
            if (((CloudSearch.Query) this.a).getBound().getShape().equals("Bound")) {
                double a = i.a(((CloudSearch.Query) this.a).getBound().getCenter().getLongitude());
                sb.append("&center=").append(a + "," + i.a(((CloudSearch.Query) this.a).getBound().getCenter().getLatitude()));
                sb.append("&radius=").append(((CloudSearch.Query) this.a).getBound().getRange());
            } else if (((CloudSearch.Query) this.a).getBound().getShape().equals("Rectangle")) {
                LatLonPoint lowerLeft = ((CloudSearch.Query) this.a).getBound().getLowerLeft();
                LatLonPoint upperRight = ((CloudSearch.Query) this.a).getBound().getUpperRight();
                double a2 = i.a(lowerLeft.getLatitude());
                double a3 = i.a(lowerLeft.getLongitude());
                sb.append("&polygon=" + a3 + "," + a2 + h.b + i.a(upperRight.getLongitude()) + "," + i.a(upperRight.getLatitude()));
            } else if (((CloudSearch.Query) this.a).getBound().getShape().equals("Polygon")) {
                List<LatLonPoint> polyGonList = ((CloudSearch.Query) this.a).getBound().getPolyGonList();
                if (polyGonList != null && polyGonList.size() > 0) {
                    sb.append("&polygon=" + i.a(polyGonList));
                }
            } else if (((CloudSearch.Query) this.a).getBound().getShape().equals(CloudSearch.SearchBound.LOCAL_SHAPE)) {
                sb.append("&city=").append(b(((CloudSearch.Query) this.a).getBound().getCity()));
            }
        }
        sb.append("&tableid=" + ((CloudSearch.Query) this.a).getTableID());
        if (!i.a(i())) {
            i();
            sb.append("&filter=").append(b(i()));
        }
        if (!i.a(h())) {
            sb.append("&sortrule=").append(h());
        }
        String b = b(((CloudSearch.Query) this.a).getQueryString());
        if (((CloudSearch.Query) this.a).getQueryString() == null || ((CloudSearch.Query) this.a).getQueryString().equals("")) {
            sb.append("&keywords=");
        } else {
            sb.append("&keywords=" + b);
        }
        sb.append("&limit=" + ((CloudSearch.Query) this.a).getPageSize());
        sb.append("&page=" + (((CloudSearch.Query) this.a).getPageNum() + 1));
        sb.append("&key=" + aw.f(this.d));
        return sb.toString();
    }

    private String h() {
        return ((CloudSearch.Query) this.a).getSortingrules() != null ? ((CloudSearch.Query) this.a).getSortingrules().toString() : "";
    }

    private String i() {
        StringBuffer stringBuffer = new StringBuffer();
        String filterString = ((CloudSearch.Query) this.a).getFilterString();
        String filterNumString = ((CloudSearch.Query) this.a).getFilterNumString();
        stringBuffer.append(filterString);
        if (!i.a(filterString) && !i.a(filterNumString)) {
            stringBuffer.append(Marker.ANY_NON_NULL_MARKER);
        }
        stringBuffer.append(filterNumString);
        return stringBuffer.toString();
    }
}
