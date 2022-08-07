package com.amap.api.services.a;

import android.content.Context;
import com.amap.api.services.core.AMapException;
import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.nearby.NearbyInfo;
import com.amap.api.services.nearby.NearbySearch;
import com.amap.api.services.nearby.NearbySearchResult;
import java.util.ArrayList;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: NearbySearchHandler.java */
/* loaded from: classes.dex */
public class s extends b<NearbySearch.NearbyQuery, NearbySearchResult> {
    private Context h;
    private NearbySearch.NearbyQuery i;

    public s(Context context, NearbySearch.NearbyQuery nearbyQuery) {
        super(context, nearbyQuery);
        this.h = context;
        this.i = nearbyQuery;
    }

    @Override // com.amap.api.services.a.b
    protected String e() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("key=").append(aw.f(this.h));
        LatLonPoint centerPoint = this.i.getCenterPoint();
        stringBuffer.append("&center=").append(centerPoint.getLongitude()).append(",").append(centerPoint.getLatitude());
        stringBuffer.append("&radius=").append(this.i.getRadius());
        stringBuffer.append("&searchtype=").append(this.i.getType());
        stringBuffer.append("&timerange=").append(this.i.getTimeRange());
        return stringBuffer.toString();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: d */
    public NearbySearchResult a(String str) throws AMapException {
        boolean z = true;
        try {
            JSONObject jSONObject = new JSONObject(str);
            if (this.i.getType() != 1) {
                z = false;
            }
            ArrayList<NearbyInfo> a = n.a(jSONObject, z);
            NearbySearchResult nearbySearchResult = new NearbySearchResult();
            nearbySearchResult.setNearbyInfoList(a);
            return nearbySearchResult;
        } catch (JSONException e) {
            i.a(e, "NearbySearchHandler", "paseJSON");
            return null;
        }
    }

    @Override // com.amap.api.services.a.cz
    public String g() {
        return h.b() + "/nearby/around";
    }
}
